package laporan;
import keuangan.*;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import simrskhanza.DlgCariPenyakit;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import simrskhanza.DlgPasien;

public class DlgFrekuensiPenyakitRanap extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode2, tabMode3, tabMode4;
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Jurnal jur = new Jurnal();
    private Connection koneksi = koneksiDB.condb();
    private PreparedStatement ps, ps2, ps3, ps4, ps5, ps6;
    private ResultSet rs, rs2, rs3, rs4, rs5, rs6;
    private String diagnosa = "";
    private DlgPasien pasien = new DlgPasien(null, false);
    
    /** Creates new form DlgProgramStudi
     * @param parent
     * @param modal */
    public DlgFrekuensiPenyakitRanap(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        tabMode=new DefaultTableModel(null,new Object[]{"Kode Penyakit","Nama Penyakit","Diagnosa Lain","Jumlah"}){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
              Class[] types = new Class[] {
                java.lang.Object.class, java.lang.Object.class,java.lang.Object.class, java.lang.Integer.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbDokter.setModel(tabMode);

        tbDokter.setPreferredScrollableViewportSize(new Dimension(800,800));
        tbDokter.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int m = 0; m < 4; m++) {
            TableColumn column = tbDokter.getColumnModel().getColumn(m);
            if(m==0){
                column.setPreferredWidth(100);
            }else if(m==1){
                column.setPreferredWidth(300);
            }else if(m==2){
                column.setPreferredWidth(400);
            }else if(m==3){
                column.setPreferredWidth(100);
            }
        }
        tbDokter.setDefaultRenderer(Object.class, new WarnaTable());   
        
        tabMode2=new DefaultTableModel(null,new Object[]{"Kode Penyakit","Nama Penyakit","Diagnosa Lain","Jumlah"}){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
              Class[] types = new Class[] {
                java.lang.Object.class, java.lang.Object.class,java.lang.Object.class, java.lang.Integer.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbDokter2.setModel(tabMode2);

        tbDokter2.setPreferredScrollableViewportSize(new Dimension(800,800));
        tbDokter2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int m = 0; m < 4; m++) {
            TableColumn column = tbDokter2.getColumnModel().getColumn(m);
            if(m==0){
                column.setPreferredWidth(100);
            }else if(m==1){
                column.setPreferredWidth(300);
            }else if(m==2){
                column.setPreferredWidth(400);
            }else if(m==3){
                column.setPreferredWidth(100);
            }
        }
        tbDokter2.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode3=new DefaultTableModel(null,new Object[]{"Tgl. Masuk", "Tgl. Keluar", "Status Pulang", "No. RM",
            "Nama Pasien", "Ruang Perawatan", "Cara Bayar", "Kode ICD-10", "Deskripsi Diagnosa", "Status Diagnosa"}) {
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class
            };
        };
        tbDiagnosa.setModel(tabMode3);

        tbDiagnosa.setPreferredScrollableViewportSize(new Dimension(800,800));
        tbDiagnosa.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int m = 0; m < 10; m++) {
            TableColumn column = tbDiagnosa.getColumnModel().getColumn(m);
            if (m == 0) {
                column.setPreferredWidth(75);
            } else if (m == 1) {
                column.setPreferredWidth(75);
            } else if (m == 2) {
                column.setPreferredWidth(100);
            } else if (m == 3) {
                column.setPreferredWidth(60);
            } else if (m == 4) {
                column.setPreferredWidth(200);
            } else if (m == 5) {
                column.setPreferredWidth(300);
            } else if (m == 6) {
                column.setPreferredWidth(160);
            } else if (m == 7) {
                column.setPreferredWidth(75);
            } else if (m == 8) {
                column.setPreferredWidth(350);
            } else if (m == 9) {
                column.setPreferredWidth(90);
            }
        }
        tbDiagnosa.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode4=new DefaultTableModel(null,new Object[]{"Tgl. Masuk", "Tgl. Keluar", "Status Pulang", "No. RM",
            "Nama Pasien", "Ruang Perawatan", "Cara Bayar", "Kode ICD-10", "Deskripsi Diagnosa", "Status Diagnosa"}) {
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class
            };
        };
        tbDiagnosa1.setModel(tabMode4);

        tbDiagnosa1.setPreferredScrollableViewportSize(new Dimension(800,800));
        tbDiagnosa1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int m = 0; m < 10; m++) {
            TableColumn column = tbDiagnosa1.getColumnModel().getColumn(m);
            if (m == 0) {
                column.setPreferredWidth(75);
            } else if (m == 1) {
                column.setPreferredWidth(75);
            } else if (m == 2) {
                column.setPreferredWidth(100);
            } else if (m == 3) {
                column.setPreferredWidth(60);
            } else if (m == 4) {
                column.setPreferredWidth(200);
            } else if (m == 5) {
                column.setPreferredWidth(300);
            } else if (m == 6) {
                column.setPreferredWidth(160);
            } else if (m == 7) {
                column.setPreferredWidth(75);
            } else if (m == 8) {
                column.setPreferredWidth(350);
            } else if (m == 9) {
                column.setPreferredWidth(90);
            }
        }
        tbDiagnosa1.setDefaultRenderer(Object.class, new WarnaTable());
        
        kdpenyakit.setDocument(new batasInput((byte)20).getKata(kdpenyakit));
                
        penyakit.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if( penyakit.getTable().getSelectedRow()!= -1){                   
                    kdpenyakit.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(),0).toString());
                    nmpenyakit.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(),1).toString());
                    TCari.setText("");
                } 
                kdpenyakit.requestFocus();
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
        
        pasien.penjab.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgFrekuensiPenyakitRanap")) {
                    if (pasien.penjab.getTable().getSelectedRow() != -1) {
                        kdpnj.setText(pasien.penjab.getTable().getValueAt(pasien.penjab.getTable().getSelectedRow(), 1).toString());
                        nmpnj.setText(pasien.penjab.getTable().getValueAt(pasien.penjab.getTable().getSelectedRow(), 2).toString());
                    }
                    kdpnj.requestFocus();
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

        pasien.penjab.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (akses.getform().equals("DlgFrekuensiPenyakitRanap")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        pasien.penjab.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        
        jlhPenyakit.setDocument(new batasInput((byte) 2).getOnlyAngka(jlhPenyakit));
        emptText();        
    }
    
    private Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
    private DlgCariPenyakit penyakit=new DlgCariPenyakit(null,false);
    private int i=0;

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Kd2 = new widget.TextBox();
        userBerizin = new widget.TextBox();
        kdAkses = new widget.TextBox();
        ruangDicetak = new widget.TextBox();
        judulRuangan = new widget.TextBox();
        kdpnj = new widget.TextBox();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        ppGrafikTerbanyakBatang = new javax.swing.JMenuItem();
        ppGrafikTerbanyakPie = new javax.swing.JMenuItem();
        ppGrafikTerkecilBatang = new javax.swing.JMenuItem();
        ppGrafikTerkecilPie = new javax.swing.JMenuItem();
        MnBerdasarTglMsk = new javax.swing.JMenu();
        ppDaftarTerbanyakPerRuangan1 = new javax.swing.JMenuItem();
        ppDaftarTerbanyakSemuaRuangan1 = new javax.swing.JMenuItem();
        ppLapRL53RawatInap1 = new javax.swing.JMenuItem();
        ppDaftarTerbanyakPerRuanganMati1 = new javax.swing.JMenuItem();
        ppDaftarTerbanyakSemuaRuanganMati1 = new javax.swing.JMenuItem();
        ppRincianPerDiagnosaTglMsk = new javax.swing.JMenuItem();
        MnBerdasarTglKlr = new javax.swing.JMenu();
        ppDaftarTerbanyakPerRuangan = new javax.swing.JMenuItem();
        ppDaftarTerbanyakSemuaRuangan = new javax.swing.JMenuItem();
        ppLapRL53RawatInap = new javax.swing.JMenuItem();
        ppDaftarTerbanyakPerRuanganMati = new javax.swing.JMenuItem();
        ppDaftarTerbanyakSemuaRuanganMati = new javax.swing.JMenuItem();
        ppRincianPerDiagnosaTglKlr = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        panelisi4 = new widget.panelisi();
        label11 = new widget.Label();
        Tgl1 = new widget.Tanggal();
        label18 = new widget.Label();
        Tgl2 = new widget.Tanggal();
        label17 = new widget.Label();
        kdpenyakit = new widget.TextBox();
        nmpenyakit = new widget.TextBox();
        btnPenyakit = new widget.Button();
        jLabel23 = new widget.Label();
        nmpnj = new widget.TextBox();
        btnPenjab = new widget.Button();
        label19 = new widget.Label();
        NmRuangan = new widget.ComboBox();
        cmbRuangKhusus1 = new widget.ComboBox();
        cmbRuangKhusus2 = new widget.ComboBox();
        cmbRuangKhusus3 = new widget.ComboBox();
        cmbRuangKhusus4 = new widget.ComboBox();
        panelisi1 = new widget.panelisi();
        label20 = new widget.Label();
        jlhPenyakit = new widget.TextBox();
        label21 = new widget.Label();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnAll = new widget.Button();
        btnCari = new widget.Button();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();
        label9 = new widget.Label();
        TabRawat = new javax.swing.JTabbedPane();
        scrollPane1 = new widget.ScrollPane();
        tbDokter = new widget.Table();
        scrollPane2 = new widget.ScrollPane();
        tbDokter2 = new widget.Table();
        scrollPane3 = new widget.ScrollPane();
        tbDiagnosa = new widget.Table();
        scrollPane4 = new widget.ScrollPane();
        tbDiagnosa1 = new widget.Table();

        Kd2.setName("Kd2"); // NOI18N
        Kd2.setPreferredSize(new java.awt.Dimension(207, 23));

        userBerizin.setName("userBerizin"); // NOI18N
        userBerizin.setPreferredSize(new java.awt.Dimension(207, 23));

        kdAkses.setName("kdAkses"); // NOI18N
        kdAkses.setPreferredSize(new java.awt.Dimension(207, 23));

        ruangDicetak.setName("ruangDicetak"); // NOI18N
        ruangDicetak.setPreferredSize(new java.awt.Dimension(207, 23));

        judulRuangan.setName("judulRuangan"); // NOI18N
        judulRuangan.setPreferredSize(new java.awt.Dimension(207, 23));

        kdpnj.setEditable(false);
        kdpnj.setForeground(new java.awt.Color(0, 0, 0));
        kdpnj.setCaretColor(new java.awt.Color(0, 0, 0));
        kdpnj.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        kdpnj.setEnabled(false);
        kdpnj.setHighlighter(null);
        kdpnj.setName("kdpnj"); // NOI18N
        kdpnj.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdpnjKeyPressed(evt);
            }
        });

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        ppGrafikTerbanyakBatang.setBackground(new java.awt.Color(242, 242, 242));
        ppGrafikTerbanyakBatang.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikTerbanyakBatang.setForeground(new java.awt.Color(0, 0, 0));
        ppGrafikTerbanyakBatang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Create-Ticket24.png"))); // NOI18N
        ppGrafikTerbanyakBatang.setText("Grafik Batang 10 Penyakit Terbanyak");
        ppGrafikTerbanyakBatang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikTerbanyakBatang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikTerbanyakBatang.setName("ppGrafikTerbanyakBatang"); // NOI18N
        ppGrafikTerbanyakBatang.setPreferredSize(new java.awt.Dimension(270, 25));
        ppGrafikTerbanyakBatang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikTerbanyakBatangActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppGrafikTerbanyakBatang);

        ppGrafikTerbanyakPie.setBackground(new java.awt.Color(242, 242, 242));
        ppGrafikTerbanyakPie.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikTerbanyakPie.setForeground(new java.awt.Color(0, 0, 0));
        ppGrafikTerbanyakPie.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Create-Ticket24.png"))); // NOI18N
        ppGrafikTerbanyakPie.setText("Grafik Pie 10 Penyakit Terbanyak");
        ppGrafikTerbanyakPie.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikTerbanyakPie.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikTerbanyakPie.setName("ppGrafikTerbanyakPie"); // NOI18N
        ppGrafikTerbanyakPie.setPreferredSize(new java.awt.Dimension(270, 25));
        ppGrafikTerbanyakPie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikTerbanyakPieActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppGrafikTerbanyakPie);

        ppGrafikTerkecilBatang.setBackground(new java.awt.Color(242, 242, 242));
        ppGrafikTerkecilBatang.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikTerkecilBatang.setForeground(new java.awt.Color(0, 0, 0));
        ppGrafikTerkecilBatang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Create-Ticket24.png"))); // NOI18N
        ppGrafikTerkecilBatang.setText("Grafik Batang 10 Penyakit Tersedikit");
        ppGrafikTerkecilBatang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikTerkecilBatang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikTerkecilBatang.setName("ppGrafikTerkecilBatang"); // NOI18N
        ppGrafikTerkecilBatang.setPreferredSize(new java.awt.Dimension(270, 25));
        ppGrafikTerkecilBatang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikTerkecilBatangActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppGrafikTerkecilBatang);

        ppGrafikTerkecilPie.setBackground(new java.awt.Color(242, 242, 242));
        ppGrafikTerkecilPie.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikTerkecilPie.setForeground(new java.awt.Color(0, 0, 0));
        ppGrafikTerkecilPie.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Create-Ticket24.png"))); // NOI18N
        ppGrafikTerkecilPie.setText("Grafik Pie 10 Penyakit Tersedikit");
        ppGrafikTerkecilPie.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikTerkecilPie.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikTerkecilPie.setName("ppGrafikTerkecilPie"); // NOI18N
        ppGrafikTerkecilPie.setPreferredSize(new java.awt.Dimension(270, 25));
        ppGrafikTerkecilPie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikTerkecilPieActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppGrafikTerkecilPie);

        MnBerdasarTglMsk.setForeground(new java.awt.Color(0, 0, 0));
        MnBerdasarTglMsk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnBerdasarTglMsk.setText("10 Besar Penyakit Berdasarkan Tgl. Masuk");
        MnBerdasarTglMsk.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBerdasarTglMsk.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnBerdasarTglMsk.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnBerdasarTglMsk.setName("MnBerdasarTglMsk"); // NOI18N
        MnBerdasarTglMsk.setPreferredSize(new java.awt.Dimension(270, 25));

        ppDaftarTerbanyakPerRuangan1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppDaftarTerbanyakPerRuangan1.setForeground(new java.awt.Color(0, 0, 0));
        ppDaftarTerbanyakPerRuangan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppDaftarTerbanyakPerRuangan1.setText("Daftar Penyakit Terbanyak Per Ruangan");
        ppDaftarTerbanyakPerRuangan1.setName("ppDaftarTerbanyakPerRuangan1"); // NOI18N
        ppDaftarTerbanyakPerRuangan1.setPreferredSize(new java.awt.Dimension(330, 25));
        ppDaftarTerbanyakPerRuangan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppDaftarTerbanyakPerRuangan1ActionPerformed(evt);
            }
        });
        MnBerdasarTglMsk.add(ppDaftarTerbanyakPerRuangan1);

        ppDaftarTerbanyakSemuaRuangan1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppDaftarTerbanyakSemuaRuangan1.setForeground(new java.awt.Color(0, 0, 0));
        ppDaftarTerbanyakSemuaRuangan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppDaftarTerbanyakSemuaRuangan1.setText("Daftar Penyakit Terbanyak Semua Ruangan");
        ppDaftarTerbanyakSemuaRuangan1.setName("ppDaftarTerbanyakSemuaRuangan1"); // NOI18N
        ppDaftarTerbanyakSemuaRuangan1.setPreferredSize(new java.awt.Dimension(330, 25));
        ppDaftarTerbanyakSemuaRuangan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppDaftarTerbanyakSemuaRuangan1ActionPerformed(evt);
            }
        });
        MnBerdasarTglMsk.add(ppDaftarTerbanyakSemuaRuangan1);

        ppLapRL53RawatInap1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppLapRL53RawatInap1.setForeground(new java.awt.Color(0, 0, 0));
        ppLapRL53RawatInap1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppLapRL53RawatInap1.setText("RL 5.3 Daftar 10 Besar Penyakit Rawat Inap");
        ppLapRL53RawatInap1.setName("ppLapRL53RawatInap1"); // NOI18N
        ppLapRL53RawatInap1.setPreferredSize(new java.awt.Dimension(330, 25));
        ppLapRL53RawatInap1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppLapRL53RawatInap1ActionPerformed(evt);
            }
        });
        MnBerdasarTglMsk.add(ppLapRL53RawatInap1);

        ppDaftarTerbanyakPerRuanganMati1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppDaftarTerbanyakPerRuanganMati1.setForeground(new java.awt.Color(0, 0, 0));
        ppDaftarTerbanyakPerRuanganMati1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppDaftarTerbanyakPerRuanganMati1.setText("Daftar Penyakit Terbanyak Kematian Per Ruangan");
        ppDaftarTerbanyakPerRuanganMati1.setName("ppDaftarTerbanyakPerRuanganMati1"); // NOI18N
        ppDaftarTerbanyakPerRuanganMati1.setPreferredSize(new java.awt.Dimension(330, 25));
        ppDaftarTerbanyakPerRuanganMati1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppDaftarTerbanyakPerRuanganMati1ActionPerformed(evt);
            }
        });
        MnBerdasarTglMsk.add(ppDaftarTerbanyakPerRuanganMati1);

        ppDaftarTerbanyakSemuaRuanganMati1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppDaftarTerbanyakSemuaRuanganMati1.setForeground(new java.awt.Color(0, 0, 0));
        ppDaftarTerbanyakSemuaRuanganMati1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppDaftarTerbanyakSemuaRuanganMati1.setText("Daftar Penyakit Terbanyak Kematian Semua Ruangan");
        ppDaftarTerbanyakSemuaRuanganMati1.setName("ppDaftarTerbanyakSemuaRuanganMati1"); // NOI18N
        ppDaftarTerbanyakSemuaRuanganMati1.setPreferredSize(new java.awt.Dimension(330, 25));
        ppDaftarTerbanyakSemuaRuanganMati1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppDaftarTerbanyakSemuaRuanganMati1ActionPerformed(evt);
            }
        });
        MnBerdasarTglMsk.add(ppDaftarTerbanyakSemuaRuanganMati1);

        ppRincianPerDiagnosaTglMsk.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppRincianPerDiagnosaTglMsk.setForeground(new java.awt.Color(0, 0, 0));
        ppRincianPerDiagnosaTglMsk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppRincianPerDiagnosaTglMsk.setText("Rincian PerDiagnosa Pasien Rawat Inap");
        ppRincianPerDiagnosaTglMsk.setName("ppRincianPerDiagnosaTglMsk"); // NOI18N
        ppRincianPerDiagnosaTglMsk.setPreferredSize(new java.awt.Dimension(330, 25));
        ppRincianPerDiagnosaTglMsk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppRincianPerDiagnosaTglMskActionPerformed(evt);
            }
        });
        MnBerdasarTglMsk.add(ppRincianPerDiagnosaTglMsk);

        jPopupMenu1.add(MnBerdasarTglMsk);

        MnBerdasarTglKlr.setForeground(new java.awt.Color(0, 0, 0));
        MnBerdasarTglKlr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnBerdasarTglKlr.setText("10 Besar Penyakit Berdasarkan Tgl. Keluar");
        MnBerdasarTglKlr.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBerdasarTglKlr.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnBerdasarTglKlr.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnBerdasarTglKlr.setName("MnBerdasarTglKlr"); // NOI18N
        MnBerdasarTglKlr.setPreferredSize(new java.awt.Dimension(270, 25));

        ppDaftarTerbanyakPerRuangan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppDaftarTerbanyakPerRuangan.setForeground(new java.awt.Color(0, 0, 0));
        ppDaftarTerbanyakPerRuangan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppDaftarTerbanyakPerRuangan.setText("Daftar Penyakit Terbanyak Per Ruangan");
        ppDaftarTerbanyakPerRuangan.setName("ppDaftarTerbanyakPerRuangan"); // NOI18N
        ppDaftarTerbanyakPerRuangan.setPreferredSize(new java.awt.Dimension(330, 25));
        ppDaftarTerbanyakPerRuangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppDaftarTerbanyakPerRuanganActionPerformed(evt);
            }
        });
        MnBerdasarTglKlr.add(ppDaftarTerbanyakPerRuangan);

        ppDaftarTerbanyakSemuaRuangan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppDaftarTerbanyakSemuaRuangan.setForeground(new java.awt.Color(0, 0, 0));
        ppDaftarTerbanyakSemuaRuangan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppDaftarTerbanyakSemuaRuangan.setText("Daftar Penyakit Terbanyak Semua Ruangan");
        ppDaftarTerbanyakSemuaRuangan.setName("ppDaftarTerbanyakSemuaRuangan"); // NOI18N
        ppDaftarTerbanyakSemuaRuangan.setPreferredSize(new java.awt.Dimension(330, 25));
        ppDaftarTerbanyakSemuaRuangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppDaftarTerbanyakSemuaRuanganActionPerformed(evt);
            }
        });
        MnBerdasarTglKlr.add(ppDaftarTerbanyakSemuaRuangan);

        ppLapRL53RawatInap.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppLapRL53RawatInap.setForeground(new java.awt.Color(0, 0, 0));
        ppLapRL53RawatInap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppLapRL53RawatInap.setText("RL 5.3 Daftar 10 Besar Penyakit Rawat Inap");
        ppLapRL53RawatInap.setName("ppLapRL53RawatInap"); // NOI18N
        ppLapRL53RawatInap.setPreferredSize(new java.awt.Dimension(330, 25));
        ppLapRL53RawatInap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppLapRL53RawatInapActionPerformed(evt);
            }
        });
        MnBerdasarTglKlr.add(ppLapRL53RawatInap);

        ppDaftarTerbanyakPerRuanganMati.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppDaftarTerbanyakPerRuanganMati.setForeground(new java.awt.Color(0, 0, 0));
        ppDaftarTerbanyakPerRuanganMati.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppDaftarTerbanyakPerRuanganMati.setText("Daftar Penyakit Terbanyak Kematian Per Ruangan");
        ppDaftarTerbanyakPerRuanganMati.setName("ppDaftarTerbanyakPerRuanganMati"); // NOI18N
        ppDaftarTerbanyakPerRuanganMati.setPreferredSize(new java.awt.Dimension(330, 25));
        ppDaftarTerbanyakPerRuanganMati.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppDaftarTerbanyakPerRuanganMatiActionPerformed(evt);
            }
        });
        MnBerdasarTglKlr.add(ppDaftarTerbanyakPerRuanganMati);

        ppDaftarTerbanyakSemuaRuanganMati.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppDaftarTerbanyakSemuaRuanganMati.setForeground(new java.awt.Color(0, 0, 0));
        ppDaftarTerbanyakSemuaRuanganMati.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppDaftarTerbanyakSemuaRuanganMati.setText("Daftar Penyakit Terbanyak Kematian Semua Ruangan");
        ppDaftarTerbanyakSemuaRuanganMati.setName("ppDaftarTerbanyakSemuaRuanganMati"); // NOI18N
        ppDaftarTerbanyakSemuaRuanganMati.setPreferredSize(new java.awt.Dimension(330, 25));
        ppDaftarTerbanyakSemuaRuanganMati.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppDaftarTerbanyakSemuaRuanganMatiActionPerformed(evt);
            }
        });
        MnBerdasarTglKlr.add(ppDaftarTerbanyakSemuaRuanganMati);

        ppRincianPerDiagnosaTglKlr.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppRincianPerDiagnosaTglKlr.setForeground(new java.awt.Color(0, 0, 0));
        ppRincianPerDiagnosaTglKlr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppRincianPerDiagnosaTglKlr.setText("Rincian PerDiagnosa Pasien Rawat Inap");
        ppRincianPerDiagnosaTglKlr.setName("ppRincianPerDiagnosaTglKlr"); // NOI18N
        ppRincianPerDiagnosaTglKlr.setPreferredSize(new java.awt.Dimension(330, 25));
        ppRincianPerDiagnosaTglKlr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppRincianPerDiagnosaTglKlrActionPerformed(evt);
            }
        });
        MnBerdasarTglKlr.add(ppRincianPerDiagnosaTglKlr);

        jPopupMenu1.add(MnBerdasarTglKlr);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Rekap Frekuensi Penyakit Di Rawat Inap ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelisi4.setName("panelisi4"); // NOI18N
        panelisi4.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        label11.setForeground(new java.awt.Color(0, 0, 0));
        label11.setText("Tanggal :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(55, 23));
        panelisi4.add(label11);

        Tgl1.setEditable(false);
        Tgl1.setDisplayFormat("dd-MM-yyyy");
        Tgl1.setName("Tgl1"); // NOI18N
        Tgl1.setPreferredSize(new java.awt.Dimension(100, 23));
        Tgl1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tgl1MouseClicked(evt);
            }
        });
        panelisi4.add(Tgl1);

        label18.setForeground(new java.awt.Color(0, 0, 0));
        label18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label18.setText("s.d.");
        label18.setName("label18"); // NOI18N
        label18.setPreferredSize(new java.awt.Dimension(30, 23));
        panelisi4.add(label18);

        Tgl2.setEditable(false);
        Tgl2.setDisplayFormat("dd-MM-yyyy");
        Tgl2.setName("Tgl2"); // NOI18N
        Tgl2.setPreferredSize(new java.awt.Dimension(100, 23));
        Tgl2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tgl2MouseClicked(evt);
            }
        });
        panelisi4.add(Tgl2);

        label17.setForeground(new java.awt.Color(0, 0, 0));
        label17.setText("Penyakit :");
        label17.setName("label17"); // NOI18N
        label17.setPreferredSize(new java.awt.Dimension(60, 23));
        panelisi4.add(label17);

        kdpenyakit.setEditable(false);
        kdpenyakit.setForeground(new java.awt.Color(0, 0, 0));
        kdpenyakit.setName("kdpenyakit"); // NOI18N
        kdpenyakit.setPreferredSize(new java.awt.Dimension(70, 23));
        kdpenyakit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdpenyakitKeyPressed(evt);
            }
        });
        panelisi4.add(kdpenyakit);

        nmpenyakit.setEditable(false);
        nmpenyakit.setForeground(new java.awt.Color(0, 0, 0));
        nmpenyakit.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        nmpenyakit.setName("nmpenyakit"); // NOI18N
        nmpenyakit.setPreferredSize(new java.awt.Dimension(203, 23));
        panelisi4.add(nmpenyakit);

        btnPenyakit.setForeground(new java.awt.Color(0, 0, 0));
        btnPenyakit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPenyakit.setMnemonic('3');
        btnPenyakit.setToolTipText("Alt+3");
        btnPenyakit.setName("btnPenyakit"); // NOI18N
        btnPenyakit.setPreferredSize(new java.awt.Dimension(28, 23));
        btnPenyakit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPenyakitActionPerformed(evt);
            }
        });
        btnPenyakit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnPenyakitKeyPressed(evt);
            }
        });
        panelisi4.add(btnPenyakit);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Cara Bayar :");
        jLabel23.setName("jLabel23"); // NOI18N
        jLabel23.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi4.add(jLabel23);

        nmpnj.setEditable(false);
        nmpnj.setForeground(new java.awt.Color(0, 0, 0));
        nmpnj.setCaretColor(new java.awt.Color(0, 0, 0));
        nmpnj.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        nmpnj.setEnabled(false);
        nmpnj.setName("nmpnj"); // NOI18N
        nmpnj.setPreferredSize(new java.awt.Dimension(200, 23));
        panelisi4.add(nmpnj);

        btnPenjab.setForeground(new java.awt.Color(0, 0, 0));
        btnPenjab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPenjab.setMnemonic('2');
        btnPenjab.setToolTipText("ALt+2");
        btnPenjab.setName("btnPenjab"); // NOI18N
        btnPenjab.setPreferredSize(new java.awt.Dimension(28, 23));
        btnPenjab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPenjabActionPerformed(evt);
            }
        });
        panelisi4.add(btnPenjab);

        label19.setForeground(new java.awt.Color(0, 0, 0));
        label19.setText("Ruangan Inap :");
        label19.setName("label19"); // NOI18N
        label19.setPreferredSize(new java.awt.Dimension(80, 23));
        panelisi4.add(label19);

        NmRuangan.setForeground(new java.awt.Color(0, 0, 0));
        NmRuangan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SEMUA RUANG", "AS-SAMI/1", "AS-SAMI/2", "INTERNIST", "AR-RAUDAH" }));
        NmRuangan.setName("NmRuangan"); // NOI18N
        NmRuangan.setPreferredSize(new java.awt.Dimension(150, 23));
        NmRuangan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                NmRuanganMouseClicked(evt);
            }
        });
        NmRuangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NmRuanganActionPerformed(evt);
            }
        });
        panelisi4.add(NmRuangan);

        cmbRuangKhusus1.setForeground(new java.awt.Color(0, 0, 0));
        cmbRuangKhusus1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "AS-SAMI/1", "AS-SAMI/2" }));
        cmbRuangKhusus1.setName("cmbRuangKhusus1"); // NOI18N
        cmbRuangKhusus1.setPreferredSize(new java.awt.Dimension(90, 23));
        cmbRuangKhusus1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbRuangKhusus1MouseClicked(evt);
            }
        });
        cmbRuangKhusus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRuangKhusus1ActionPerformed(evt);
            }
        });
        panelisi4.add(cmbRuangKhusus1);

        cmbRuangKhusus2.setForeground(new java.awt.Color(0, 0, 0));
        cmbRuangKhusus2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "INTERNIST", "RKPD", "ZAAL" }));
        cmbRuangKhusus2.setName("cmbRuangKhusus2"); // NOI18N
        cmbRuangKhusus2.setPreferredSize(new java.awt.Dimension(90, 23));
        cmbRuangKhusus2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbRuangKhusus2MouseClicked(evt);
            }
        });
        cmbRuangKhusus2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRuangKhusus2ActionPerformed(evt);
            }
        });
        panelisi4.add(cmbRuangKhusus2);

        cmbRuangKhusus3.setForeground(new java.awt.Color(0, 0, 0));
        cmbRuangKhusus3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "PERINATOLOGI", "BAYI SEHAT" }));
        cmbRuangKhusus3.setName("cmbRuangKhusus3"); // NOI18N
        cmbRuangKhusus3.setPreferredSize(new java.awt.Dimension(120, 23));
        cmbRuangKhusus3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbRuangKhusus3MouseClicked(evt);
            }
        });
        cmbRuangKhusus3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRuangKhusus3ActionPerformed(evt);
            }
        });
        cmbRuangKhusus3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbRuangKhusus3KeyPressed(evt);
            }
        });
        panelisi4.add(cmbRuangKhusus3);

        cmbRuangKhusus4.setForeground(new java.awt.Color(0, 0, 0));
        cmbRuangKhusus4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "AR-RAUDAH", "AR-RAUDAH ATAS", "AR-RAUDAH BAWAH" }));
        cmbRuangKhusus4.setName("cmbRuangKhusus4"); // NOI18N
        cmbRuangKhusus4.setPreferredSize(new java.awt.Dimension(137, 23));
        cmbRuangKhusus4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbRuangKhusus4MouseClicked(evt);
            }
        });
        cmbRuangKhusus4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRuangKhusus4ActionPerformed(evt);
            }
        });
        panelisi4.add(cmbRuangKhusus4);

        internalFrame1.add(panelisi4, java.awt.BorderLayout.PAGE_START);

        panelisi1.setName("panelisi1"); // NOI18N
        panelisi1.setPreferredSize(new java.awt.Dimension(100, 56));
        panelisi1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        label20.setForeground(new java.awt.Color(0, 0, 0));
        label20.setText("Jumlah : ");
        label20.setName("label20"); // NOI18N
        label20.setPreferredSize(new java.awt.Dimension(60, 23));
        panelisi1.add(label20);

        jlhPenyakit.setForeground(new java.awt.Color(0, 0, 0));
        jlhPenyakit.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jlhPenyakit.setName("jlhPenyakit"); // NOI18N
        jlhPenyakit.setPreferredSize(new java.awt.Dimension(40, 23));
        panelisi1.add(jlhPenyakit);

        label21.setForeground(new java.awt.Color(0, 0, 0));
        label21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label21.setText("Besar Penyakit.");
        label21.setName("label21"); // NOI18N
        label21.setPreferredSize(new java.awt.Dimension(80, 23));
        panelisi1.add(label21);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi1.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(240, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelisi1.add(TCari);

        BtnAll.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setText("Semua Data");
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(120, 30));
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
        panelisi1.add(BtnAll);

        btnCari.setForeground(new java.awt.Color(0, 0, 0));
        btnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        btnCari.setMnemonic('2');
        btnCari.setText("Tampilkan Data");
        btnCari.setToolTipText("Alt+2");
        btnCari.setName("btnCari"); // NOI18N
        btnCari.setPreferredSize(new java.awt.Dimension(130, 30));
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });
        btnCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnCariKeyPressed(evt);
            }
        });
        panelisi1.add(btnCari);

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
        panelisi1.add(BtnPrint);

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
        panelisi1.add(BtnKeluar);

        label9.setForeground(new java.awt.Color(0, 0, 0));
        label9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label9.setName("label9"); // NOI18N
        label9.setPreferredSize(new java.awt.Dimension(300, 30));
        panelisi1.add(label9);

        internalFrame1.add(panelisi1, java.awt.BorderLayout.PAGE_END);

        TabRawat.setBackground(new java.awt.Color(250, 255, 245));
        TabRawat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 235, 225)));
        TabRawat.setForeground(new java.awt.Color(0, 0, 0));
        TabRawat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        scrollPane1.setComponentPopupMenu(jPopupMenu1);
        scrollPane1.setName("scrollPane1"); // NOI18N
        scrollPane1.setOpaque(true);

        tbDokter.setAutoCreateRowSorter(true);
        tbDokter.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbDokter.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbDokter.setComponentPopupMenu(jPopupMenu1);
        tbDokter.setName("tbDokter"); // NOI18N
        scrollPane1.setViewportView(tbDokter);

        TabRawat.addTab("Berdasar Tanggal Masuk", scrollPane1);

        scrollPane2.setComponentPopupMenu(jPopupMenu1);
        scrollPane2.setName("scrollPane2"); // NOI18N
        scrollPane2.setOpaque(true);

        tbDokter2.setAutoCreateRowSorter(true);
        tbDokter2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbDokter2.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbDokter2.setComponentPopupMenu(jPopupMenu1);
        tbDokter2.setName("tbDokter2"); // NOI18N
        scrollPane2.setViewportView(tbDokter2);

        TabRawat.addTab("Berdasar Tanggal Keluar", scrollPane2);

        scrollPane3.setComponentPopupMenu(jPopupMenu1);
        scrollPane3.setName("scrollPane3"); // NOI18N
        scrollPane3.setOpaque(true);

        tbDiagnosa.setAutoCreateRowSorter(true);
        tbDiagnosa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbDiagnosa.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbDiagnosa.setComponentPopupMenu(jPopupMenu1);
        tbDiagnosa.setName("tbDiagnosa"); // NOI18N
        scrollPane3.setViewportView(tbDiagnosa);

        TabRawat.addTab("Rincian Diagnosa Berdasar Tgl. Masuk", scrollPane3);

        scrollPane4.setComponentPopupMenu(jPopupMenu1);
        scrollPane4.setName("scrollPane4"); // NOI18N
        scrollPane4.setOpaque(true);

        tbDiagnosa1.setAutoCreateRowSorter(true);
        tbDiagnosa1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbDiagnosa1.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbDiagnosa1.setComponentPopupMenu(jPopupMenu1);
        tbDiagnosa1.setName("tbDiagnosa1"); // NOI18N
        scrollPane4.setViewportView(tbDiagnosa1);

        TabRawat.addTab("Rincian Diagnosa Berdasar Tgl. Pulang", scrollPane4);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
/*
private void KdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKdKeyPressed
    Valid.pindah(evt,BtnCari,Nm);
}//GEN-LAST:event_TKdKeyPressed
*/

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (tbDokter.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            //TCari.requestFocus();
        } else if (tbDokter.getRowCount() != 0) {
            Sequel.queryu("delete from temporary");
            if (TabRawat.getSelectedIndex() == 0) {
                int row = tbDokter.getRowCount();
                for (int r = 0; r < row; r++) {
                    Sequel.menyimpan("temporary", "'0','"
                            + tbDokter.getValueAt(r, 0).toString().replaceAll("'", "`") + "','"
                            + tbDokter.getValueAt(r, 1).toString().replaceAll("'", "`") + "','"
                            + tbDokter.getValueAt(r, 2).toString().replaceAll("'", "`") + "','"
                            + tbDokter.getValueAt(r, 3).toString() + "','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Rekap Frekuensi Penyakit");
                }
            } else if (TabRawat.getSelectedIndex() == 1) {
                int row = tbDokter2.getRowCount();
                for (int r = 0; r < row; r++) {
                    Sequel.menyimpan("temporary", "'0','"
                            + tbDokter2.getValueAt(r, 0).toString().replaceAll("'", "`") + "','"
                            + tbDokter2.getValueAt(r, 1).toString().replaceAll("'", "`") + "','"
                            + tbDokter2.getValueAt(r, 2).toString().replaceAll("'", "`") + "','"
                            + tbDokter2.getValueAt(r, 3).toString() + "','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Rekap Frekuensi Penyakit");
                }
            }
            this.setCursor(Cursor.getDefaultCursor());
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("periode", "PERIODE TGL. " + Tgl1.getSelectedItem() + " S.D " + Tgl2.getSelectedItem());
            param.put("nama_ruangan", "DIRUANGAN " + judulRuangan.getText());
            Valid.MyReport("rptFrekuensiPenyakitRanap.jasper", "report", "[ Frekuensi Penyakit Di Rawat Inap ]",
                    "select no, temp1, temp2, temp3, temp4, temp5, temp6, temp7, temp8, temp9, temp10, temp11, temp12, temp13, temp14 from temporary order by no asc",param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPrintActionPerformed(null);
        }else{
            Valid.pindah(evt,Tgl2,BtnKeluar);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnPrint,Tgl1);}
    }//GEN-LAST:event_BtnKeluarKeyPressed

    private void kdpenyakitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdpenyakitKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            Sequel.cariIsi("select nm_penyakit from penyakit where kd_penyakit=? ",nmpenyakit,kdpenyakit.getText());  
           // TCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            Sequel.cariIsi("select nm_penyakit from penyakit where kd_penyakit=? ",nmpenyakit,kdpenyakit.getText());  
            Tgl2.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            Sequel.cariIsi("select nm_penyakit from penyakit where kd_penyakit=? ",nmpenyakit,kdpenyakit.getText());  
            BtnAll.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            btnPenyakitActionPerformed(null);
        }
    }//GEN-LAST:event_kdpenyakitKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        kdpenyakit.setText("");
        nmpenyakit.setText("");
        kdpnj.setText("");
        nmpnj.setText("");
        TCari.setText("");

        if (TabRawat.getSelectedIndex() == 0) {
            prosesCari();
        } else if (TabRawat.getSelectedIndex() == 1) {
            prosesCari2();
        } else if (TabRawat.getSelectedIndex() == 2) {
            tampilDiag1();
        } else if (TabRawat.getSelectedIndex() == 3) {
            tampilDiag2();
        }
    }//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnAllActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnPrint, BtnKeluar);
        }
    }//GEN-LAST:event_BtnAllKeyPressed

    private void ppGrafikTerbanyakBatangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikTerbanyakBatangActionPerformed
        if (akses.getkode().equals("Admin Utama") || akses.getpenyakit() == true) {
        if(TabRawat.getSelectedIndex()==0){
            if((tbDokter.getRowCount()>9)&&(Integer.parseInt(tbDokter.getValueAt(0,3).toString())>=Integer.parseInt(tbDokter.getValueAt(1,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(1,3).toString())>=Integer.parseInt(tbDokter.getValueAt(2,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(2,3).toString())>=Integer.parseInt(tbDokter.getValueAt(3,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(3,3).toString())>=Integer.parseInt(tbDokter.getValueAt(4,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(4,3).toString())>=Integer.parseInt(tbDokter.getValueAt(5,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(5,3).toString())>=Integer.parseInt(tbDokter.getValueAt(6,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(6,3).toString())>=Integer.parseInt(tbDokter.getValueAt(7,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(7,3).toString())>=Integer.parseInt(tbDokter.getValueAt(8,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(8,3).toString())>=Integer.parseInt(tbDokter.getValueAt(9,3).toString()))){
                DefaultCategoryDataset dcd = new DefaultCategoryDataset();
                dcd.setValue(Integer.parseInt(tbDokter.getValueAt(0,3).toString()),tbDokter.getValueAt(0,0).toString()+", "+tbDokter.getValueAt(0,1).toString()+", "+tbDokter.getValueAt(0,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter.getValueAt(1,3).toString()),tbDokter.getValueAt(1,0).toString()+", "+tbDokter.getValueAt(1,1).toString()+", "+tbDokter.getValueAt(1,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter.getValueAt(2,3).toString()),tbDokter.getValueAt(2,0).toString()+", "+tbDokter.getValueAt(2,1).toString()+", "+tbDokter.getValueAt(2,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter.getValueAt(3,3).toString()),tbDokter.getValueAt(3,0).toString()+", "+tbDokter.getValueAt(3,1).toString()+", "+tbDokter.getValueAt(3,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter.getValueAt(4,3).toString()),tbDokter.getValueAt(4,0).toString()+", "+tbDokter.getValueAt(4,1).toString()+", "+tbDokter.getValueAt(4,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter.getValueAt(5,3).toString()),tbDokter.getValueAt(5,0).toString()+", "+tbDokter.getValueAt(5,1).toString()+", "+tbDokter.getValueAt(5,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter.getValueAt(6,3).toString()),tbDokter.getValueAt(6,0).toString()+", "+tbDokter.getValueAt(6,1).toString()+", "+tbDokter.getValueAt(6,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter.getValueAt(7,3).toString()),tbDokter.getValueAt(7,0).toString()+", "+tbDokter.getValueAt(7,1).toString()+", "+tbDokter.getValueAt(7,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter.getValueAt(8,3).toString()),tbDokter.getValueAt(8,0).toString()+", "+tbDokter.getValueAt(8,1).toString()+", "+tbDokter.getValueAt(8,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter.getValueAt(9,3).toString()),tbDokter.getValueAt(9,0).toString()+", "+tbDokter.getValueAt(9,1).toString()+", "+tbDokter.getValueAt(9,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));


                JFreeChart freeChart = ChartFactory.createBarChart("10 Besar Penyakit Di Rawat Inap","Periode","Jumlah Penyakit", dcd, PlotOrientation.VERTICAL,true, true,true); //String arg0,String arg1,String arg2,Category Datasheet,Plot Orientation,boolean arg4,boolean arg5,boolean arg6
                ChartFrame cf = new ChartFrame("Frafik 10 Besar Penyakit Di Rawat Inap",freeChart);

                cf.setSize(internalFrame1.getWidth()-50,internalFrame1.getHeight()-50);
                cf.setLocationRelativeTo(internalFrame1);
                cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                cf.setVisible(true);

            }else if((tbDokter.getRowCount()>8)&&(Integer.parseInt(tbDokter.getValueAt(0,3).toString())>=Integer.parseInt(tbDokter.getValueAt(1,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(1,3).toString())>=Integer.parseInt(tbDokter.getValueAt(2,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(2,3).toString())>=Integer.parseInt(tbDokter.getValueAt(3,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(3,3).toString())>=Integer.parseInt(tbDokter.getValueAt(4,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(4,3).toString())>=Integer.parseInt(tbDokter.getValueAt(5,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(5,3).toString())>=Integer.parseInt(tbDokter.getValueAt(6,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(6,3).toString())>=Integer.parseInt(tbDokter.getValueAt(7,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(7,3).toString())>=Integer.parseInt(tbDokter.getValueAt(8,3).toString()))){
                DefaultCategoryDataset dcd = new DefaultCategoryDataset();
                dcd.setValue(Integer.parseInt(tbDokter.getValueAt(0,3).toString()),tbDokter.getValueAt(0,0).toString()+", "+tbDokter.getValueAt(0,1).toString()+", "+tbDokter.getValueAt(0,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter.getValueAt(1,3).toString()),tbDokter.getValueAt(1,0).toString()+", "+tbDokter.getValueAt(1,1).toString()+", "+tbDokter.getValueAt(1,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter.getValueAt(2,3).toString()),tbDokter.getValueAt(2,0).toString()+", "+tbDokter.getValueAt(2,1).toString()+", "+tbDokter.getValueAt(2,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter.getValueAt(3,3).toString()),tbDokter.getValueAt(3,0).toString()+", "+tbDokter.getValueAt(3,1).toString()+", "+tbDokter.getValueAt(3,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter.getValueAt(4,3).toString()),tbDokter.getValueAt(4,0).toString()+", "+tbDokter.getValueAt(4,1).toString()+", "+tbDokter.getValueAt(4,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter.getValueAt(5,3).toString()),tbDokter.getValueAt(5,0).toString()+", "+tbDokter.getValueAt(5,1).toString()+", "+tbDokter.getValueAt(5,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter.getValueAt(6,3).toString()),tbDokter.getValueAt(6,0).toString()+", "+tbDokter.getValueAt(6,1).toString()+", "+tbDokter.getValueAt(6,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter.getValueAt(7,3).toString()),tbDokter.getValueAt(7,0).toString()+", "+tbDokter.getValueAt(7,1).toString()+", "+tbDokter.getValueAt(7,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter.getValueAt(8,3).toString()),tbDokter.getValueAt(8,0).toString()+", "+tbDokter.getValueAt(8,1).toString()+", "+tbDokter.getValueAt(8,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));

                JFreeChart freeChart = ChartFactory.createBarChart("10 Besar Penyakit Di Rawat Inap","Periode","Jumlah Penyakit", dcd, PlotOrientation.VERTICAL,true, true,true); //String arg0,String arg1,String arg2,Category Datasheet,Plot Orientation,boolean arg4,boolean arg5,boolean arg6
                ChartFrame cf = new ChartFrame("Frafik 10 Besar Penyakit Di Rawat Inap",freeChart);

                cf.setSize(internalFrame1.getWidth()-50,internalFrame1.getHeight()-50);
                cf.setLocationRelativeTo(internalFrame1);
                cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                cf.setVisible(true);

            }else if((tbDokter.getRowCount()>7)&&(Integer.parseInt(tbDokter.getValueAt(0,3).toString())>=Integer.parseInt(tbDokter.getValueAt(1,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(1,3).toString())>=Integer.parseInt(tbDokter.getValueAt(2,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(2,3).toString())>=Integer.parseInt(tbDokter.getValueAt(3,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(3,3).toString())>=Integer.parseInt(tbDokter.getValueAt(4,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(4,3).toString())>=Integer.parseInt(tbDokter.getValueAt(5,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(5,3).toString())>=Integer.parseInt(tbDokter.getValueAt(6,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(6,3).toString())>=Integer.parseInt(tbDokter.getValueAt(7,3).toString()))){
                DefaultCategoryDataset dcd = new DefaultCategoryDataset();
                dcd.setValue(Integer.parseInt(tbDokter.getValueAt(0,3).toString()),tbDokter.getValueAt(0,0).toString()+", "+tbDokter.getValueAt(0,1).toString()+", "+tbDokter.getValueAt(0,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter.getValueAt(1,3).toString()),tbDokter.getValueAt(1,0).toString()+", "+tbDokter.getValueAt(1,1).toString()+", "+tbDokter.getValueAt(1,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter.getValueAt(2,3).toString()),tbDokter.getValueAt(2,0).toString()+", "+tbDokter.getValueAt(2,1).toString()+", "+tbDokter.getValueAt(2,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter.getValueAt(3,3).toString()),tbDokter.getValueAt(3,0).toString()+", "+tbDokter.getValueAt(3,1).toString()+", "+tbDokter.getValueAt(3,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter.getValueAt(4,3).toString()),tbDokter.getValueAt(4,0).toString()+", "+tbDokter.getValueAt(4,1).toString()+", "+tbDokter.getValueAt(4,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter.getValueAt(5,3).toString()),tbDokter.getValueAt(5,0).toString()+", "+tbDokter.getValueAt(5,1).toString()+", "+tbDokter.getValueAt(5,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter.getValueAt(6,3).toString()),tbDokter.getValueAt(6,0).toString()+", "+tbDokter.getValueAt(6,1).toString()+", "+tbDokter.getValueAt(6,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter.getValueAt(7,3).toString()),tbDokter.getValueAt(7,0).toString()+", "+tbDokter.getValueAt(7,1).toString()+", "+tbDokter.getValueAt(7,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));

                JFreeChart freeChart = ChartFactory.createBarChart("10 Besar Penyakit Di Rawat Inap","Periode","Jumlah Penyakit", dcd, PlotOrientation.VERTICAL,true, true,true); //String arg0,String arg1,String arg2,Category Datasheet,Plot Orientation,boolean arg4,boolean arg5,boolean arg6
                ChartFrame cf = new ChartFrame("Frafik 10 Besar Penyakit Di Rawat Inap",freeChart);

                cf.setSize(internalFrame1.getWidth()-50,internalFrame1.getHeight()-50);
                cf.setLocationRelativeTo(internalFrame1);
                cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                cf.setVisible(true);

            }else if((tbDokter.getRowCount()>6)&&(Integer.parseInt(tbDokter.getValueAt(0,3).toString())>=Integer.parseInt(tbDokter.getValueAt(1,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(1,3).toString())>=Integer.parseInt(tbDokter.getValueAt(2,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(2,3).toString())>=Integer.parseInt(tbDokter.getValueAt(3,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(3,3).toString())>=Integer.parseInt(tbDokter.getValueAt(4,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(4,3).toString())>=Integer.parseInt(tbDokter.getValueAt(5,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(5,3).toString())>=Integer.parseInt(tbDokter.getValueAt(6,3).toString()))){
                DefaultCategoryDataset dcd = new DefaultCategoryDataset();
                dcd.setValue(Integer.parseInt(tbDokter.getValueAt(0,3).toString()),tbDokter.getValueAt(0,0).toString()+", "+tbDokter.getValueAt(0,1).toString()+", "+tbDokter.getValueAt(0,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter.getValueAt(1,3).toString()),tbDokter.getValueAt(1,0).toString()+", "+tbDokter.getValueAt(1,1).toString()+", "+tbDokter.getValueAt(1,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter.getValueAt(2,3).toString()),tbDokter.getValueAt(2,0).toString()+", "+tbDokter.getValueAt(2,1).toString()+", "+tbDokter.getValueAt(2,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter.getValueAt(3,3).toString()),tbDokter.getValueAt(3,0).toString()+", "+tbDokter.getValueAt(3,1).toString()+", "+tbDokter.getValueAt(3,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter.getValueAt(4,3).toString()),tbDokter.getValueAt(4,0).toString()+", "+tbDokter.getValueAt(4,1).toString()+", "+tbDokter.getValueAt(4,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter.getValueAt(5,3).toString()),tbDokter.getValueAt(5,0).toString()+", "+tbDokter.getValueAt(5,1).toString()+", "+tbDokter.getValueAt(5,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter.getValueAt(6,3).toString()),tbDokter.getValueAt(6,0).toString()+", "+tbDokter.getValueAt(6,1).toString()+", "+tbDokter.getValueAt(6,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));

                JFreeChart freeChart = ChartFactory.createBarChart("10 Besar Penyakit Di Rawat Inap","Periode","Jumlah Penyakit", dcd, PlotOrientation.VERTICAL,true, true,true); //String arg0,String arg1,String arg2,Category Datasheet,Plot Orientation,boolean arg4,boolean arg5,boolean arg6
                ChartFrame cf = new ChartFrame("Frafik 10 Besar Penyakit Di Rawat Inap",freeChart);

                cf.setSize(internalFrame1.getWidth()-50,internalFrame1.getHeight()-50);
                cf.setLocationRelativeTo(internalFrame1);
                cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                cf.setVisible(true);

            }else if((tbDokter.getRowCount()>5)&&(Integer.parseInt(tbDokter.getValueAt(0,3).toString())>=Integer.parseInt(tbDokter.getValueAt(1,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(1,3).toString())>=Integer.parseInt(tbDokter.getValueAt(2,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(2,3).toString())>=Integer.parseInt(tbDokter.getValueAt(3,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(3,3).toString())>=Integer.parseInt(tbDokter.getValueAt(4,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(4,3).toString())>=Integer.parseInt(tbDokter.getValueAt(5,3).toString()))){
                DefaultCategoryDataset dcd = new DefaultCategoryDataset();
                dcd.setValue(Integer.parseInt(tbDokter.getValueAt(0,3).toString()),tbDokter.getValueAt(0,0).toString()+", "+tbDokter.getValueAt(0,1).toString()+", "+tbDokter.getValueAt(0,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter.getValueAt(1,3).toString()),tbDokter.getValueAt(1,0).toString()+", "+tbDokter.getValueAt(1,1).toString()+", "+tbDokter.getValueAt(1,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter.getValueAt(2,3).toString()),tbDokter.getValueAt(2,0).toString()+", "+tbDokter.getValueAt(2,1).toString()+", "+tbDokter.getValueAt(2,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter.getValueAt(3,3).toString()),tbDokter.getValueAt(3,0).toString()+", "+tbDokter.getValueAt(3,1).toString()+", "+tbDokter.getValueAt(3,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter.getValueAt(4,3).toString()),tbDokter.getValueAt(4,0).toString()+", "+tbDokter.getValueAt(4,1).toString()+", "+tbDokter.getValueAt(4,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter.getValueAt(5,3).toString()),tbDokter.getValueAt(5,0).toString()+", "+tbDokter.getValueAt(5,1).toString()+", "+tbDokter.getValueAt(5,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));

                JFreeChart freeChart = ChartFactory.createBarChart("10 Besar Penyakit Di Rawat Inap","Periode","Jumlah Penyakit", dcd, PlotOrientation.VERTICAL,true, true,true); //String arg0,String arg1,String arg2,Category Datasheet,Plot Orientation,boolean arg4,boolean arg5,boolean arg6
                ChartFrame cf = new ChartFrame("Frafik 10 Besar Penyakit Di Rawat Inap",freeChart);

                cf.setSize(internalFrame1.getWidth()-50,internalFrame1.getHeight()-50);
                cf.setLocationRelativeTo(internalFrame1);
                cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                cf.setVisible(true);

            }else if((tbDokter.getRowCount()>4)&&(Integer.parseInt(tbDokter.getValueAt(0,3).toString())>=Integer.parseInt(tbDokter.getValueAt(1,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(1,3).toString())>=Integer.parseInt(tbDokter.getValueAt(2,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(2,3).toString())>=Integer.parseInt(tbDokter.getValueAt(3,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(3,3).toString())>=Integer.parseInt(tbDokter.getValueAt(4,3).toString()))){
                DefaultCategoryDataset dcd = new DefaultCategoryDataset();
                dcd.setValue(Integer.parseInt(tbDokter.getValueAt(0,3).toString()),tbDokter.getValueAt(0,0).toString()+", "+tbDokter.getValueAt(0,1).toString()+", "+tbDokter.getValueAt(0,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter.getValueAt(1,3).toString()),tbDokter.getValueAt(1,0).toString()+", "+tbDokter.getValueAt(1,1).toString()+", "+tbDokter.getValueAt(1,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter.getValueAt(2,3).toString()),tbDokter.getValueAt(2,0).toString()+", "+tbDokter.getValueAt(2,1).toString()+", "+tbDokter.getValueAt(2,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter.getValueAt(3,3).toString()),tbDokter.getValueAt(3,0).toString()+", "+tbDokter.getValueAt(3,1).toString()+", "+tbDokter.getValueAt(3,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter.getValueAt(4,3).toString()),tbDokter.getValueAt(4,0).toString()+", "+tbDokter.getValueAt(4,1).toString()+", "+tbDokter.getValueAt(4,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));

                JFreeChart freeChart = ChartFactory.createBarChart("10 Besar Penyakit Di Rawat Inap","Periode","Jumlah Penyakit", dcd, PlotOrientation.VERTICAL,true, true,true); //String arg0,String arg1,String arg2,Category Datasheet,Plot Orientation,boolean arg4,boolean arg5,boolean arg6
                ChartFrame cf = new ChartFrame("Frafik 10 Besar Penyakit Di Rawat Inap",freeChart);

                cf.setSize(internalFrame1.getWidth()-50,internalFrame1.getHeight()-50);
                cf.setLocationRelativeTo(internalFrame1);
                cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                cf.setVisible(true);

            }else if((tbDokter.getRowCount()>3)&&(Integer.parseInt(tbDokter.getValueAt(0,3).toString())>=Integer.parseInt(tbDokter.getValueAt(1,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(1,3).toString())>=Integer.parseInt(tbDokter.getValueAt(2,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(2,3).toString())>=Integer.parseInt(tbDokter.getValueAt(3,3).toString()))){
                DefaultCategoryDataset dcd = new DefaultCategoryDataset();
                dcd.setValue(Integer.parseInt(tbDokter.getValueAt(0,3).toString()),tbDokter.getValueAt(0,0).toString()+", "+tbDokter.getValueAt(0,1).toString()+", "+tbDokter.getValueAt(0,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter.getValueAt(1,3).toString()),tbDokter.getValueAt(1,0).toString()+", "+tbDokter.getValueAt(1,1).toString()+", "+tbDokter.getValueAt(1,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter.getValueAt(2,3).toString()),tbDokter.getValueAt(2,0).toString()+", "+tbDokter.getValueAt(2,1).toString()+", "+tbDokter.getValueAt(2,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter.getValueAt(3,3).toString()),tbDokter.getValueAt(3,0).toString()+", "+tbDokter.getValueAt(3,1).toString()+", "+tbDokter.getValueAt(3,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));

                JFreeChart freeChart = ChartFactory.createBarChart("10 Besar Penyakit Di Rawat Inap","Periode","Jumlah Penyakit", dcd, PlotOrientation.VERTICAL,true, true,true); //String arg0,String arg1,String arg2,Category Datasheet,Plot Orientation,boolean arg4,boolean arg5,boolean arg6
                ChartFrame cf = new ChartFrame("Frafik 10 Besar Penyakit Di Rawat Inap",freeChart);

                cf.setSize(internalFrame1.getWidth()-50,internalFrame1.getHeight()-50);
                cf.setLocationRelativeTo(internalFrame1);
                cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                cf.setVisible(true);          

            }else if((tbDokter.getRowCount()>2)&&(Integer.parseInt(tbDokter.getValueAt(0,3).toString())>=Integer.parseInt(tbDokter.getValueAt(1,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(1,3).toString())>=Integer.parseInt(tbDokter.getValueAt(2,3).toString()))){
                DefaultCategoryDataset dcd = new DefaultCategoryDataset();
                dcd.setValue(Integer.parseInt(tbDokter.getValueAt(0,3).toString()),tbDokter.getValueAt(0,0).toString()+", "+tbDokter.getValueAt(0,1).toString()+", "+tbDokter.getValueAt(0,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter.getValueAt(1,3).toString()),tbDokter.getValueAt(1,0).toString()+", "+tbDokter.getValueAt(1,1).toString()+", "+tbDokter.getValueAt(1,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter.getValueAt(2,3).toString()),tbDokter.getValueAt(2,0).toString()+", "+tbDokter.getValueAt(2,1).toString()+", "+tbDokter.getValueAt(2,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));

                JFreeChart freeChart = ChartFactory.createBarChart("10 Besar Penyakit Di Rawat Inap","Periode","Jumlah Penyakit", dcd, PlotOrientation.VERTICAL,true, true,true); //String arg0,String arg1,String arg2,Category Datasheet,Plot Orientation,boolean arg4,boolean arg5,boolean arg6
                ChartFrame cf = new ChartFrame("Frafik 10 Besar Penyakit Di Rawat Inap",freeChart);

                cf.setSize(internalFrame1.getWidth()-50,internalFrame1.getHeight()-50);
                cf.setLocationRelativeTo(internalFrame1);
                cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                cf.setVisible(true);           

            }else if((tbDokter.getRowCount()>1)&&(Integer.parseInt(tbDokter.getValueAt(0,3).toString())>=Integer.parseInt(tbDokter.getValueAt(1,3).toString()))){
                DefaultCategoryDataset dcd = new DefaultCategoryDataset();
                dcd.setValue(Integer.parseInt(tbDokter.getValueAt(0,3).toString()),tbDokter.getValueAt(0,0).toString()+", "+tbDokter.getValueAt(0,1).toString()+", "+tbDokter.getValueAt(0,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter.getValueAt(1,3).toString()),tbDokter.getValueAt(1,0).toString()+", "+tbDokter.getValueAt(1,1).toString()+", "+tbDokter.getValueAt(1,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));

                JFreeChart freeChart = ChartFactory.createBarChart("10 Besar Penyakit Di Rawat Inap","Periode","Jumlah Penyakit", dcd, PlotOrientation.VERTICAL,true, true,true); //String arg0,String arg1,String arg2,Category Datasheet,Plot Orientation,boolean arg4,boolean arg5,boolean arg6
                ChartFrame cf = new ChartFrame("Frafik 10 Besar Penyakit Di Rawat Inap",freeChart);

                cf.setSize(internalFrame1.getWidth()-50,internalFrame1.getHeight()-50);
                cf.setLocationRelativeTo(internalFrame1);
                cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                cf.setVisible(true);            

            }else{
                JOptionPane.showMessageDialog(null,"Maaf, Silahkan urutkan secara Descanding/Besar Ke Kecil dengan menklik pada kolom jumlah..!!!!");
            }
        }else if(TabRawat.getSelectedIndex()==1){
            if((tbDokter2.getRowCount()>9)&&(Integer.parseInt(tbDokter2.getValueAt(0,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(1,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(1,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(2,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(2,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(3,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(3,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(4,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(4,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(5,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(5,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(6,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(6,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(7,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(7,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(8,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(8,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(9,3).toString()))){
                DefaultCategoryDataset dcd = new DefaultCategoryDataset();
                dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(0,3).toString()),tbDokter2.getValueAt(0,0).toString()+", "+tbDokter2.getValueAt(0,1).toString()+", "+tbDokter2.getValueAt(0,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(1,3).toString()),tbDokter2.getValueAt(1,0).toString()+", "+tbDokter2.getValueAt(1,1).toString()+", "+tbDokter2.getValueAt(1,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(2,3).toString()),tbDokter2.getValueAt(2,0).toString()+", "+tbDokter2.getValueAt(2,1).toString()+", "+tbDokter2.getValueAt(2,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(3,3).toString()),tbDokter2.getValueAt(3,0).toString()+", "+tbDokter2.getValueAt(3,1).toString()+", "+tbDokter2.getValueAt(3,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(4,3).toString()),tbDokter2.getValueAt(4,0).toString()+", "+tbDokter2.getValueAt(4,1).toString()+", "+tbDokter2.getValueAt(4,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(5,3).toString()),tbDokter2.getValueAt(5,0).toString()+", "+tbDokter2.getValueAt(5,1).toString()+", "+tbDokter2.getValueAt(5,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(6,3).toString()),tbDokter2.getValueAt(6,0).toString()+", "+tbDokter2.getValueAt(6,1).toString()+", "+tbDokter2.getValueAt(6,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(7,3).toString()),tbDokter2.getValueAt(7,0).toString()+", "+tbDokter2.getValueAt(7,1).toString()+", "+tbDokter2.getValueAt(7,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(8,3).toString()),tbDokter2.getValueAt(8,0).toString()+", "+tbDokter2.getValueAt(8,1).toString()+", "+tbDokter2.getValueAt(8,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(9,3).toString()),tbDokter2.getValueAt(9,0).toString()+", "+tbDokter2.getValueAt(9,1).toString()+", "+tbDokter2.getValueAt(9,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));


                JFreeChart freeChart = ChartFactory.createBarChart("10 Besar Penyakit Di Rawat Inap","Periode","Jumlah Penyakit", dcd, PlotOrientation.VERTICAL,true, true,true); //String arg0,String arg1,String arg2,Category Datasheet,Plot Orientation,boolean arg4,boolean arg5,boolean arg6
                ChartFrame cf = new ChartFrame("Frafik 10 Besar Penyakit Di Rawat Inap",freeChart);

                cf.setSize(internalFrame1.getWidth()-50,internalFrame1.getHeight()-50);
                cf.setLocationRelativeTo(internalFrame1);
                cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                cf.setVisible(true);

            }else if((tbDokter2.getRowCount()>8)&&(Integer.parseInt(tbDokter2.getValueAt(0,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(1,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(1,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(2,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(2,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(3,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(3,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(4,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(4,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(5,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(5,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(6,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(6,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(7,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(7,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(8,3).toString()))){
                DefaultCategoryDataset dcd = new DefaultCategoryDataset();
                dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(0,3).toString()),tbDokter2.getValueAt(0,0).toString()+", "+tbDokter2.getValueAt(0,1).toString()+", "+tbDokter2.getValueAt(0,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(1,3).toString()),tbDokter2.getValueAt(1,0).toString()+", "+tbDokter2.getValueAt(1,1).toString()+", "+tbDokter2.getValueAt(1,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(2,3).toString()),tbDokter2.getValueAt(2,0).toString()+", "+tbDokter2.getValueAt(2,1).toString()+", "+tbDokter2.getValueAt(2,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(3,3).toString()),tbDokter2.getValueAt(3,0).toString()+", "+tbDokter2.getValueAt(3,1).toString()+", "+tbDokter2.getValueAt(3,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(4,3).toString()),tbDokter2.getValueAt(4,0).toString()+", "+tbDokter2.getValueAt(4,1).toString()+", "+tbDokter2.getValueAt(4,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(5,3).toString()),tbDokter2.getValueAt(5,0).toString()+", "+tbDokter2.getValueAt(5,1).toString()+", "+tbDokter2.getValueAt(5,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(6,3).toString()),tbDokter2.getValueAt(6,0).toString()+", "+tbDokter2.getValueAt(6,1).toString()+", "+tbDokter2.getValueAt(6,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(7,3).toString()),tbDokter2.getValueAt(7,0).toString()+", "+tbDokter2.getValueAt(7,1).toString()+", "+tbDokter2.getValueAt(7,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(8,3).toString()),tbDokter2.getValueAt(8,0).toString()+", "+tbDokter2.getValueAt(8,1).toString()+", "+tbDokter2.getValueAt(8,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));

                JFreeChart freeChart = ChartFactory.createBarChart("10 Besar Penyakit Di Rawat Inap","Periode","Jumlah Penyakit", dcd, PlotOrientation.VERTICAL,true, true,true); //String arg0,String arg1,String arg2,Category Datasheet,Plot Orientation,boolean arg4,boolean arg5,boolean arg6
                ChartFrame cf = new ChartFrame("Frafik 10 Besar Penyakit Di Rawat Inap",freeChart);

                cf.setSize(internalFrame1.getWidth()-50,internalFrame1.getHeight()-50);
                cf.setLocationRelativeTo(internalFrame1);
                cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                cf.setVisible(true);

            }else if((tbDokter2.getRowCount()>7)&&(Integer.parseInt(tbDokter2.getValueAt(0,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(1,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(1,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(2,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(2,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(3,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(3,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(4,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(4,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(5,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(5,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(6,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(6,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(7,3).toString()))){
                DefaultCategoryDataset dcd = new DefaultCategoryDataset();
                dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(0,3).toString()),tbDokter2.getValueAt(0,0).toString()+", "+tbDokter2.getValueAt(0,1).toString()+", "+tbDokter2.getValueAt(0,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(1,3).toString()),tbDokter2.getValueAt(1,0).toString()+", "+tbDokter2.getValueAt(1,1).toString()+", "+tbDokter2.getValueAt(1,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(2,3).toString()),tbDokter2.getValueAt(2,0).toString()+", "+tbDokter2.getValueAt(2,1).toString()+", "+tbDokter2.getValueAt(2,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(3,3).toString()),tbDokter2.getValueAt(3,0).toString()+", "+tbDokter2.getValueAt(3,1).toString()+", "+tbDokter2.getValueAt(3,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(4,3).toString()),tbDokter2.getValueAt(4,0).toString()+", "+tbDokter2.getValueAt(4,1).toString()+", "+tbDokter2.getValueAt(4,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(5,3).toString()),tbDokter2.getValueAt(5,0).toString()+", "+tbDokter2.getValueAt(5,1).toString()+", "+tbDokter2.getValueAt(5,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(6,3).toString()),tbDokter2.getValueAt(6,0).toString()+", "+tbDokter2.getValueAt(6,1).toString()+", "+tbDokter2.getValueAt(6,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(7,3).toString()),tbDokter2.getValueAt(7,0).toString()+", "+tbDokter2.getValueAt(7,1).toString()+", "+tbDokter2.getValueAt(7,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));

                JFreeChart freeChart = ChartFactory.createBarChart("10 Besar Penyakit Di Rawat Inap","Periode","Jumlah Penyakit", dcd, PlotOrientation.VERTICAL,true, true,true); //String arg0,String arg1,String arg2,Category Datasheet,Plot Orientation,boolean arg4,boolean arg5,boolean arg6
                ChartFrame cf = new ChartFrame("Frafik 10 Besar Penyakit Di Rawat Inap",freeChart);

                cf.setSize(internalFrame1.getWidth()-50,internalFrame1.getHeight()-50);
                cf.setLocationRelativeTo(internalFrame1);
                cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                cf.setVisible(true);

            }else if((tbDokter2.getRowCount()>6)&&(Integer.parseInt(tbDokter2.getValueAt(0,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(1,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(1,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(2,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(2,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(3,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(3,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(4,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(4,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(5,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(5,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(6,3).toString()))){
                DefaultCategoryDataset dcd = new DefaultCategoryDataset();
                dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(0,3).toString()),tbDokter2.getValueAt(0,0).toString()+", "+tbDokter2.getValueAt(0,1).toString()+", "+tbDokter2.getValueAt(0,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(1,3).toString()),tbDokter2.getValueAt(1,0).toString()+", "+tbDokter2.getValueAt(1,1).toString()+", "+tbDokter2.getValueAt(1,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(2,3).toString()),tbDokter2.getValueAt(2,0).toString()+", "+tbDokter2.getValueAt(2,1).toString()+", "+tbDokter2.getValueAt(2,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(3,3).toString()),tbDokter2.getValueAt(3,0).toString()+", "+tbDokter2.getValueAt(3,1).toString()+", "+tbDokter2.getValueAt(3,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(4,3).toString()),tbDokter2.getValueAt(4,0).toString()+", "+tbDokter2.getValueAt(4,1).toString()+", "+tbDokter2.getValueAt(4,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(5,3).toString()),tbDokter2.getValueAt(5,0).toString()+", "+tbDokter2.getValueAt(5,1).toString()+", "+tbDokter2.getValueAt(5,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(6,3).toString()),tbDokter2.getValueAt(6,0).toString()+", "+tbDokter2.getValueAt(6,1).toString()+", "+tbDokter2.getValueAt(6,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));

                JFreeChart freeChart = ChartFactory.createBarChart("10 Besar Penyakit Di Rawat Inap","Periode","Jumlah Penyakit", dcd, PlotOrientation.VERTICAL,true, true,true); //String arg0,String arg1,String arg2,Category Datasheet,Plot Orientation,boolean arg4,boolean arg5,boolean arg6
                ChartFrame cf = new ChartFrame("Frafik 10 Besar Penyakit Di Rawat Inap",freeChart);

                cf.setSize(internalFrame1.getWidth()-50,internalFrame1.getHeight()-50);
                cf.setLocationRelativeTo(internalFrame1);
                cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                cf.setVisible(true);

            }else if((tbDokter2.getRowCount()>5)&&(Integer.parseInt(tbDokter2.getValueAt(0,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(1,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(1,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(2,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(2,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(3,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(3,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(4,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(4,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(5,3).toString()))){
                DefaultCategoryDataset dcd = new DefaultCategoryDataset();
                dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(0,3).toString()),tbDokter2.getValueAt(0,0).toString()+", "+tbDokter2.getValueAt(0,1).toString()+", "+tbDokter2.getValueAt(0,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(1,3).toString()),tbDokter2.getValueAt(1,0).toString()+", "+tbDokter2.getValueAt(1,1).toString()+", "+tbDokter2.getValueAt(1,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(2,3).toString()),tbDokter2.getValueAt(2,0).toString()+", "+tbDokter2.getValueAt(2,1).toString()+", "+tbDokter2.getValueAt(2,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(3,3).toString()),tbDokter2.getValueAt(3,0).toString()+", "+tbDokter2.getValueAt(3,1).toString()+", "+tbDokter2.getValueAt(3,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(4,3).toString()),tbDokter2.getValueAt(4,0).toString()+", "+tbDokter2.getValueAt(4,1).toString()+", "+tbDokter2.getValueAt(4,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(5,3).toString()),tbDokter2.getValueAt(5,0).toString()+", "+tbDokter2.getValueAt(5,1).toString()+", "+tbDokter2.getValueAt(5,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));

                JFreeChart freeChart = ChartFactory.createBarChart("10 Besar Penyakit Di Rawat Inap","Periode","Jumlah Penyakit", dcd, PlotOrientation.VERTICAL,true, true,true); //String arg0,String arg1,String arg2,Category Datasheet,Plot Orientation,boolean arg4,boolean arg5,boolean arg6
                ChartFrame cf = new ChartFrame("Frafik 10 Besar Penyakit Di Rawat Inap",freeChart);

                cf.setSize(internalFrame1.getWidth()-50,internalFrame1.getHeight()-50);
                cf.setLocationRelativeTo(internalFrame1);
                cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                cf.setVisible(true);

            }else if((tbDokter2.getRowCount()>4)&&(Integer.parseInt(tbDokter2.getValueAt(0,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(1,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(1,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(2,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(2,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(3,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(3,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(4,3).toString()))){
                DefaultCategoryDataset dcd = new DefaultCategoryDataset();
                dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(0,3).toString()),tbDokter2.getValueAt(0,0).toString()+", "+tbDokter2.getValueAt(0,1).toString()+", "+tbDokter2.getValueAt(0,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(1,3).toString()),tbDokter2.getValueAt(1,0).toString()+", "+tbDokter2.getValueAt(1,1).toString()+", "+tbDokter2.getValueAt(1,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(2,3).toString()),tbDokter2.getValueAt(2,0).toString()+", "+tbDokter2.getValueAt(2,1).toString()+", "+tbDokter2.getValueAt(2,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(3,3).toString()),tbDokter2.getValueAt(3,0).toString()+", "+tbDokter2.getValueAt(3,1).toString()+", "+tbDokter2.getValueAt(3,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(4,3).toString()),tbDokter2.getValueAt(4,0).toString()+", "+tbDokter2.getValueAt(4,1).toString()+", "+tbDokter2.getValueAt(4,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));

                JFreeChart freeChart = ChartFactory.createBarChart("10 Besar Penyakit Di Rawat Inap","Periode","Jumlah Penyakit", dcd, PlotOrientation.VERTICAL,true, true,true); //String arg0,String arg1,String arg2,Category Datasheet,Plot Orientation,boolean arg4,boolean arg5,boolean arg6
                ChartFrame cf = new ChartFrame("Frafik 10 Besar Penyakit Di Rawat Inap",freeChart);

                cf.setSize(internalFrame1.getWidth()-50,internalFrame1.getHeight()-50);
                cf.setLocationRelativeTo(internalFrame1);
                cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                cf.setVisible(true);

            }else if((tbDokter2.getRowCount()>3)&&(Integer.parseInt(tbDokter2.getValueAt(0,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(1,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(1,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(2,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(2,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(3,3).toString()))){
                DefaultCategoryDataset dcd = new DefaultCategoryDataset();
                dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(0,3).toString()),tbDokter2.getValueAt(0,0).toString()+", "+tbDokter2.getValueAt(0,1).toString()+", "+tbDokter2.getValueAt(0,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(1,3).toString()),tbDokter2.getValueAt(1,0).toString()+", "+tbDokter2.getValueAt(1,1).toString()+", "+tbDokter2.getValueAt(1,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(2,3).toString()),tbDokter2.getValueAt(2,0).toString()+", "+tbDokter2.getValueAt(2,1).toString()+", "+tbDokter2.getValueAt(2,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(3,3).toString()),tbDokter2.getValueAt(3,0).toString()+", "+tbDokter2.getValueAt(3,1).toString()+", "+tbDokter2.getValueAt(3,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));

                JFreeChart freeChart = ChartFactory.createBarChart("10 Besar Penyakit Di Rawat Inap","Periode","Jumlah Penyakit", dcd, PlotOrientation.VERTICAL,true, true,true); //String arg0,String arg1,String arg2,Category Datasheet,Plot Orientation,boolean arg4,boolean arg5,boolean arg6
                ChartFrame cf = new ChartFrame("Frafik 10 Besar Penyakit Di Rawat Inap",freeChart);

                cf.setSize(internalFrame1.getWidth()-50,internalFrame1.getHeight()-50);
                cf.setLocationRelativeTo(internalFrame1);
                cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                cf.setVisible(true);          

            }else if((tbDokter2.getRowCount()>2)&&(Integer.parseInt(tbDokter2.getValueAt(0,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(1,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(1,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(2,3).toString()))){
                DefaultCategoryDataset dcd = new DefaultCategoryDataset();
                dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(0,3).toString()),tbDokter2.getValueAt(0,0).toString()+", "+tbDokter2.getValueAt(0,1).toString()+", "+tbDokter2.getValueAt(0,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(1,3).toString()),tbDokter2.getValueAt(1,0).toString()+", "+tbDokter2.getValueAt(1,1).toString()+", "+tbDokter2.getValueAt(1,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(2,3).toString()),tbDokter2.getValueAt(2,0).toString()+", "+tbDokter2.getValueAt(2,1).toString()+", "+tbDokter2.getValueAt(2,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));

                JFreeChart freeChart = ChartFactory.createBarChart("10 Besar Penyakit Di Rawat Inap","Periode","Jumlah Penyakit", dcd, PlotOrientation.VERTICAL,true, true,true); //String arg0,String arg1,String arg2,Category Datasheet,Plot Orientation,boolean arg4,boolean arg5,boolean arg6
                ChartFrame cf = new ChartFrame("Frafik 10 Besar Penyakit Di Rawat Inap",freeChart);

                cf.setSize(internalFrame1.getWidth()-50,internalFrame1.getHeight()-50);
                cf.setLocationRelativeTo(internalFrame1);
                cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                cf.setVisible(true);           

            }else if((tbDokter2.getRowCount()>1)&&(Integer.parseInt(tbDokter2.getValueAt(0,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(1,3).toString()))){
                DefaultCategoryDataset dcd = new DefaultCategoryDataset();
                dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(0,3).toString()),tbDokter2.getValueAt(0,0).toString()+", "+tbDokter2.getValueAt(0,1).toString()+", "+tbDokter2.getValueAt(0,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(1,3).toString()),tbDokter2.getValueAt(1,0).toString()+", "+tbDokter2.getValueAt(1,1).toString()+", "+tbDokter2.getValueAt(1,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));

                JFreeChart freeChart = ChartFactory.createBarChart("10 Besar Penyakit Di Rawat Inap","Periode","Jumlah Penyakit", dcd, PlotOrientation.VERTICAL,true, true,true); //String arg0,String arg1,String arg2,Category Datasheet,Plot Orientation,boolean arg4,boolean arg5,boolean arg6
                ChartFrame cf = new ChartFrame("Frafik 10 Besar Penyakit Di Rawat Inap",freeChart);

                cf.setSize(internalFrame1.getWidth()-50,internalFrame1.getHeight()-50);
                cf.setLocationRelativeTo(internalFrame1);
                cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                cf.setVisible(true);            

            }else{
                JOptionPane.showMessageDialog(null,"Maaf, Silahkan urutkan secara Descanding/Besar Ke Kecil dengan menklik pada kolom jumlah..!!!!");
            }
        }
        
        } else {
            JOptionPane.showMessageDialog(null, "Untuk mencetak grafik batang 10 penyakit terbanyak, silakan koordinasi dg. Inst. Rekam Medik...!!!");
            Tgl1.requestFocus();
        }        
    }//GEN-LAST:event_ppGrafikTerbanyakBatangActionPerformed

private void btnPenyakitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPenyakitActionPerformed
        penyakit.isCek();
        penyakit.setSize(1031,425);
        penyakit.setLocationRelativeTo(internalFrame1);
        penyakit.setVisible(true);
}//GEN-LAST:event_btnPenyakitActionPerformed

private void btnPenyakitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPenyakitKeyPressed
   //Valid.pindah(evt,DTPCari2,TCari);
}//GEN-LAST:event_btnPenyakitKeyPressed

private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
    if (TabRawat.getSelectedIndex() == 0) {
        prosesCari();
    } else if (TabRawat.getSelectedIndex() == 1) {
        prosesCari2();
    } else if (TabRawat.getSelectedIndex() == 2) {
        tampilDiag1();
    } else if (TabRawat.getSelectedIndex() == 3) {
        tampilDiag2();
    }
}//GEN-LAST:event_btnCariActionPerformed

private void btnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            btnCariActionPerformed(null);
        }else{
            Valid.pindah(evt, kdpenyakit, BtnAll);
        }
}//GEN-LAST:event_btnCariKeyPressed

private void ppGrafikTerbanyakPieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikTerbanyakPieActionPerformed
    if (akses.getkode().equals("Admin Utama") || akses.getpenyakit() == true) {
    if(TabRawat.getSelectedIndex()==0){
            if((tbDokter.getRowCount()>9)&&(Integer.parseInt(tbDokter.getValueAt(0,3).toString())>=Integer.parseInt(tbDokter.getValueAt(1,3).toString()))
                       && (Integer.parseInt(tbDokter.getValueAt(1,3).toString())>=Integer.parseInt(tbDokter.getValueAt(2,3).toString()))
                       && (Integer.parseInt(tbDokter.getValueAt(2,3).toString())>=Integer.parseInt(tbDokter.getValueAt(3,3).toString()))
                       && (Integer.parseInt(tbDokter.getValueAt(3,3).toString())>=Integer.parseInt(tbDokter.getValueAt(4,3).toString()))
                       && (Integer.parseInt(tbDokter.getValueAt(4,3).toString())>=Integer.parseInt(tbDokter.getValueAt(5,3).toString()))
                       && (Integer.parseInt(tbDokter.getValueAt(5,3).toString())>=Integer.parseInt(tbDokter.getValueAt(6,3).toString()))
                       && (Integer.parseInt(tbDokter.getValueAt(6,3).toString())>=Integer.parseInt(tbDokter.getValueAt(7,3).toString()))
                       && (Integer.parseInt(tbDokter.getValueAt(7,3).toString())>=Integer.parseInt(tbDokter.getValueAt(8,3).toString()))
                       && (Integer.parseInt(tbDokter.getValueAt(8,3).toString())>=Integer.parseInt(tbDokter.getValueAt(9,3).toString()))){
                   DefaultPieDataset dpd = new DefaultPieDataset();
                   dpd.setValue(tbDokter.getValueAt(0,0).toString()+", "+tbDokter.getValueAt(0,1).toString()+", "+tbDokter.getValueAt(0,3).toString(),Integer.parseInt(tbDokter.getValueAt(0,3).toString()));
                   dpd.setValue(tbDokter.getValueAt(1,0).toString()+", "+tbDokter.getValueAt(1,1).toString()+", "+tbDokter.getValueAt(1,3).toString(),Integer.parseInt(tbDokter.getValueAt(1,3).toString()));
                   dpd.setValue(tbDokter.getValueAt(2,0).toString()+", "+tbDokter.getValueAt(2,1).toString()+", "+tbDokter.getValueAt(2,3).toString(),Integer.parseInt(tbDokter.getValueAt(2,3).toString()));
                   dpd.setValue(tbDokter.getValueAt(3,0).toString()+", "+tbDokter.getValueAt(3,1).toString()+", "+tbDokter.getValueAt(3,3).toString(),Integer.parseInt(tbDokter.getValueAt(3,3).toString()));
                   dpd.setValue(tbDokter.getValueAt(4,0).toString()+", "+tbDokter.getValueAt(4,1).toString()+", "+tbDokter.getValueAt(4,3).toString(),Integer.parseInt(tbDokter.getValueAt(4,3).toString()));
                   dpd.setValue(tbDokter.getValueAt(5,0).toString()+", "+tbDokter.getValueAt(5,1).toString()+", "+tbDokter.getValueAt(5,3).toString(),Integer.parseInt(tbDokter.getValueAt(5,3).toString()));
                   dpd.setValue(tbDokter.getValueAt(6,0).toString()+", "+tbDokter.getValueAt(6,1).toString()+", "+tbDokter.getValueAt(6,3).toString(),Integer.parseInt(tbDokter.getValueAt(6,3).toString()));
                   dpd.setValue(tbDokter.getValueAt(7,0).toString()+", "+tbDokter.getValueAt(7,1).toString()+", "+tbDokter.getValueAt(7,3).toString(),Integer.parseInt(tbDokter.getValueAt(7,3).toString()));
                   dpd.setValue(tbDokter.getValueAt(8,0).toString()+", "+tbDokter.getValueAt(8,1).toString()+", "+tbDokter.getValueAt(8,3).toString(),Integer.parseInt(tbDokter.getValueAt(8,3).toString()));
                   dpd.setValue(tbDokter.getValueAt(9,0).toString()+", "+tbDokter.getValueAt(9,1).toString()+", "+tbDokter.getValueAt(9,3).toString(),Integer.parseInt(tbDokter.getValueAt(9,3).toString()));

                   JFreeChart freeChart = ChartFactory.createPieChart("10 Besar Penyakit Di Rawat Inap Periode "+Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""),dpd,true,true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url 
                   ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Inap ",freeChart);
                   cf.setSize(internalFrame1.getWidth()-50, internalFrame1.getHeight()-50);
                   cf.setLocationRelativeTo(internalFrame1);
                   cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                   cf.setVisible(true);
               }else if((tbDokter.getRowCount()>8)&&(Integer.parseInt(tbDokter.getValueAt(0,3).toString())>=Integer.parseInt(tbDokter.getValueAt(1,3).toString()))
                       && (Integer.parseInt(tbDokter.getValueAt(1,3).toString())>=Integer.parseInt(tbDokter.getValueAt(2,3).toString()))
                       && (Integer.parseInt(tbDokter.getValueAt(2,3).toString())>=Integer.parseInt(tbDokter.getValueAt(3,3).toString()))
                       && (Integer.parseInt(tbDokter.getValueAt(3,3).toString())>=Integer.parseInt(tbDokter.getValueAt(4,3).toString()))
                       && (Integer.parseInt(tbDokter.getValueAt(4,3).toString())>=Integer.parseInt(tbDokter.getValueAt(5,3).toString()))
                       && (Integer.parseInt(tbDokter.getValueAt(5,3).toString())>=Integer.parseInt(tbDokter.getValueAt(6,3).toString()))
                       && (Integer.parseInt(tbDokter.getValueAt(6,3).toString())>=Integer.parseInt(tbDokter.getValueAt(7,3).toString()))
                       && (Integer.parseInt(tbDokter.getValueAt(7,3).toString())>=Integer.parseInt(tbDokter.getValueAt(8,3).toString()))){
                   DefaultPieDataset dpd = new DefaultPieDataset();
                   dpd.setValue(tbDokter.getValueAt(0,0).toString()+", "+tbDokter.getValueAt(0,1).toString()+", "+tbDokter.getValueAt(0,3).toString(),Integer.parseInt(tbDokter.getValueAt(0,3).toString()));
                   dpd.setValue(tbDokter.getValueAt(1,0).toString()+", "+tbDokter.getValueAt(1,1).toString()+", "+tbDokter.getValueAt(1,3).toString(),Integer.parseInt(tbDokter.getValueAt(1,3).toString()));
                   dpd.setValue(tbDokter.getValueAt(2,0).toString()+", "+tbDokter.getValueAt(2,1).toString()+", "+tbDokter.getValueAt(2,3).toString(),Integer.parseInt(tbDokter.getValueAt(2,3).toString()));
                   dpd.setValue(tbDokter.getValueAt(3,0).toString()+", "+tbDokter.getValueAt(3,1).toString()+", "+tbDokter.getValueAt(3,3).toString(),Integer.parseInt(tbDokter.getValueAt(3,3).toString()));
                   dpd.setValue(tbDokter.getValueAt(4,0).toString()+", "+tbDokter.getValueAt(4,1).toString()+", "+tbDokter.getValueAt(4,3).toString(),Integer.parseInt(tbDokter.getValueAt(4,3).toString()));
                   dpd.setValue(tbDokter.getValueAt(5,0).toString()+", "+tbDokter.getValueAt(5,1).toString()+", "+tbDokter.getValueAt(5,3).toString(),Integer.parseInt(tbDokter.getValueAt(5,3).toString()));
                   dpd.setValue(tbDokter.getValueAt(6,0).toString()+", "+tbDokter.getValueAt(6,1).toString()+", "+tbDokter.getValueAt(6,3).toString(),Integer.parseInt(tbDokter.getValueAt(6,3).toString()));
                   dpd.setValue(tbDokter.getValueAt(7,0).toString()+", "+tbDokter.getValueAt(7,1).toString()+", "+tbDokter.getValueAt(7,3).toString(),Integer.parseInt(tbDokter.getValueAt(7,3).toString()));
                   dpd.setValue(tbDokter.getValueAt(8,0).toString()+", "+tbDokter.getValueAt(8,1).toString()+", "+tbDokter.getValueAt(8,3).toString(),Integer.parseInt(tbDokter.getValueAt(8,3).toString()));

                   JFreeChart freeChart = ChartFactory.createPieChart("10 Besar Penyakit Di Rawat Inap Periode "+Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""),dpd,true,true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url 
                   ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Inap ",freeChart);
                   cf.setSize(internalFrame1.getWidth()-50, internalFrame1.getHeight()-50);
                   cf.setLocationRelativeTo(internalFrame1);
                   cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                   cf.setVisible(true);

               }else if((tbDokter.getRowCount()>7)&&(Integer.parseInt(tbDokter.getValueAt(0,3).toString())>=Integer.parseInt(tbDokter.getValueAt(1,3).toString()))
                       && (Integer.parseInt(tbDokter.getValueAt(1,3).toString())>=Integer.parseInt(tbDokter.getValueAt(2,3).toString()))
                       && (Integer.parseInt(tbDokter.getValueAt(2,3).toString())>=Integer.parseInt(tbDokter.getValueAt(3,3).toString()))
                       && (Integer.parseInt(tbDokter.getValueAt(3,3).toString())>=Integer.parseInt(tbDokter.getValueAt(4,3).toString()))
                       && (Integer.parseInt(tbDokter.getValueAt(4,3).toString())>=Integer.parseInt(tbDokter.getValueAt(5,3).toString()))
                       && (Integer.parseInt(tbDokter.getValueAt(5,3).toString())>=Integer.parseInt(tbDokter.getValueAt(6,3).toString()))
                       && (Integer.parseInt(tbDokter.getValueAt(6,3).toString())>=Integer.parseInt(tbDokter.getValueAt(7,3).toString()))){
                   DefaultPieDataset dpd = new DefaultPieDataset();
                   dpd.setValue(tbDokter.getValueAt(0,0).toString()+", "+tbDokter.getValueAt(0,1).toString()+", "+tbDokter.getValueAt(0,3).toString(),Integer.parseInt(tbDokter.getValueAt(0,3).toString()));
                   dpd.setValue(tbDokter.getValueAt(1,0).toString()+", "+tbDokter.getValueAt(1,1).toString()+", "+tbDokter.getValueAt(1,3).toString(),Integer.parseInt(tbDokter.getValueAt(1,3).toString()));
                   dpd.setValue(tbDokter.getValueAt(2,0).toString()+", "+tbDokter.getValueAt(2,1).toString()+", "+tbDokter.getValueAt(2,3).toString(),Integer.parseInt(tbDokter.getValueAt(2,3).toString()));
                   dpd.setValue(tbDokter.getValueAt(3,0).toString()+", "+tbDokter.getValueAt(3,1).toString()+", "+tbDokter.getValueAt(3,3).toString(),Integer.parseInt(tbDokter.getValueAt(3,3).toString()));
                   dpd.setValue(tbDokter.getValueAt(4,0).toString()+", "+tbDokter.getValueAt(4,1).toString()+", "+tbDokter.getValueAt(4,3).toString(),Integer.parseInt(tbDokter.getValueAt(4,3).toString()));
                   dpd.setValue(tbDokter.getValueAt(5,0).toString()+", "+tbDokter.getValueAt(5,1).toString()+", "+tbDokter.getValueAt(5,3).toString(),Integer.parseInt(tbDokter.getValueAt(5,3).toString()));
                   dpd.setValue(tbDokter.getValueAt(6,0).toString()+", "+tbDokter.getValueAt(6,1).toString()+", "+tbDokter.getValueAt(6,3).toString(),Integer.parseInt(tbDokter.getValueAt(6,3).toString()));
                   dpd.setValue(tbDokter.getValueAt(7,0).toString()+", "+tbDokter.getValueAt(7,1).toString()+", "+tbDokter.getValueAt(7,3).toString(),Integer.parseInt(tbDokter.getValueAt(7,3).toString()));

                   JFreeChart freeChart = ChartFactory.createPieChart("10 Besar Penyakit Di Rawat Inap Periode "+Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""),dpd,true,true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url 
                   ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Inap ",freeChart);
                   cf.setSize(internalFrame1.getWidth()-50, internalFrame1.getHeight()-50);
                   cf.setLocationRelativeTo(internalFrame1);
                   cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                   cf.setVisible(true);

               }else if((tbDokter.getRowCount()>6)&&(Integer.parseInt(tbDokter.getValueAt(0,3).toString())>=Integer.parseInt(tbDokter.getValueAt(1,3).toString()))
                       && (Integer.parseInt(tbDokter.getValueAt(1,3).toString())>=Integer.parseInt(tbDokter.getValueAt(2,3).toString()))
                       && (Integer.parseInt(tbDokter.getValueAt(2,3).toString())>=Integer.parseInt(tbDokter.getValueAt(3,3).toString()))
                       && (Integer.parseInt(tbDokter.getValueAt(3,3).toString())>=Integer.parseInt(tbDokter.getValueAt(4,3).toString()))
                       && (Integer.parseInt(tbDokter.getValueAt(4,3).toString())>=Integer.parseInt(tbDokter.getValueAt(5,3).toString()))
                       && (Integer.parseInt(tbDokter.getValueAt(5,3).toString())>=Integer.parseInt(tbDokter.getValueAt(6,3).toString()))){
                   DefaultPieDataset dpd = new DefaultPieDataset();
                   dpd.setValue(tbDokter.getValueAt(0,0).toString()+", "+tbDokter.getValueAt(0,1).toString()+", "+tbDokter.getValueAt(0,3).toString(),Integer.parseInt(tbDokter.getValueAt(0,3).toString()));
                   dpd.setValue(tbDokter.getValueAt(1,0).toString()+", "+tbDokter.getValueAt(1,1).toString()+", "+tbDokter.getValueAt(1,3).toString(),Integer.parseInt(tbDokter.getValueAt(1,3).toString()));
                   dpd.setValue(tbDokter.getValueAt(2,0).toString()+", "+tbDokter.getValueAt(2,1).toString()+", "+tbDokter.getValueAt(2,3).toString(),Integer.parseInt(tbDokter.getValueAt(2,3).toString()));
                   dpd.setValue(tbDokter.getValueAt(3,0).toString()+", "+tbDokter.getValueAt(3,1).toString()+", "+tbDokter.getValueAt(3,3).toString(),Integer.parseInt(tbDokter.getValueAt(3,3).toString()));
                   dpd.setValue(tbDokter.getValueAt(4,0).toString()+", "+tbDokter.getValueAt(4,1).toString()+", "+tbDokter.getValueAt(4,3).toString(),Integer.parseInt(tbDokter.getValueAt(4,3).toString()));
                   dpd.setValue(tbDokter.getValueAt(5,0).toString()+", "+tbDokter.getValueAt(5,1).toString()+", "+tbDokter.getValueAt(5,3).toString(),Integer.parseInt(tbDokter.getValueAt(5,3).toString()));
                   dpd.setValue(tbDokter.getValueAt(6,0).toString()+", "+tbDokter.getValueAt(6,1).toString()+", "+tbDokter.getValueAt(6,3).toString(),Integer.parseInt(tbDokter.getValueAt(6,3).toString()));

                   JFreeChart freeChart = ChartFactory.createPieChart("10 Besar Penyakit Di Rawat Inap Periode "+Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""),dpd,true,true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url 
                   ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Inap ",freeChart);
                   cf.setSize(internalFrame1.getWidth()-50, internalFrame1.getHeight()-50);
                   cf.setLocationRelativeTo(internalFrame1);
                   cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                   cf.setVisible(true);

               }else if((tbDokter.getRowCount()>5)&&(Integer.parseInt(tbDokter.getValueAt(0,3).toString())>=Integer.parseInt(tbDokter.getValueAt(1,3).toString()))
                       && (Integer.parseInt(tbDokter.getValueAt(1,3).toString())>=Integer.parseInt(tbDokter.getValueAt(2,3).toString()))
                       && (Integer.parseInt(tbDokter.getValueAt(2,3).toString())>=Integer.parseInt(tbDokter.getValueAt(3,3).toString()))
                       && (Integer.parseInt(tbDokter.getValueAt(3,3).toString())>=Integer.parseInt(tbDokter.getValueAt(4,3).toString()))
                       && (Integer.parseInt(tbDokter.getValueAt(4,3).toString())>=Integer.parseInt(tbDokter.getValueAt(5,3).toString()))){
                   DefaultPieDataset dpd = new DefaultPieDataset();
                   dpd.setValue(tbDokter.getValueAt(0,0).toString()+", "+tbDokter.getValueAt(0,1).toString()+", "+tbDokter.getValueAt(0,3).toString(),Integer.parseInt(tbDokter.getValueAt(0,3).toString()));
                   dpd.setValue(tbDokter.getValueAt(1,0).toString()+", "+tbDokter.getValueAt(1,1).toString()+", "+tbDokter.getValueAt(1,3).toString(),Integer.parseInt(tbDokter.getValueAt(1,3).toString()));
                   dpd.setValue(tbDokter.getValueAt(2,0).toString()+", "+tbDokter.getValueAt(2,1).toString()+", "+tbDokter.getValueAt(2,3).toString(),Integer.parseInt(tbDokter.getValueAt(2,3).toString()));
                   dpd.setValue(tbDokter.getValueAt(3,0).toString()+", "+tbDokter.getValueAt(3,1).toString()+", "+tbDokter.getValueAt(3,3).toString(),Integer.parseInt(tbDokter.getValueAt(3,3).toString()));
                   dpd.setValue(tbDokter.getValueAt(4,0).toString()+", "+tbDokter.getValueAt(4,1).toString()+", "+tbDokter.getValueAt(4,3).toString(),Integer.parseInt(tbDokter.getValueAt(4,3).toString()));
                   dpd.setValue(tbDokter.getValueAt(5,0).toString()+", "+tbDokter.getValueAt(5,1).toString()+", "+tbDokter.getValueAt(5,3).toString(),Integer.parseInt(tbDokter.getValueAt(5,3).toString()));

                   JFreeChart freeChart = ChartFactory.createPieChart("10 Besar Penyakit Di Rawat Inap Periode "+Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""),dpd,true,true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url 
                   ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Inap ",freeChart);
                   cf.setSize(internalFrame1.getWidth()-50, internalFrame1.getHeight()-50);
                   cf.setLocationRelativeTo(internalFrame1);
                   cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                   cf.setVisible(true);

               }else if((tbDokter.getRowCount()>4)&&(Integer.parseInt(tbDokter.getValueAt(0,3).toString())>=Integer.parseInt(tbDokter.getValueAt(1,3).toString()))
                       && (Integer.parseInt(tbDokter.getValueAt(1,3).toString())>=Integer.parseInt(tbDokter.getValueAt(2,3).toString()))
                       && (Integer.parseInt(tbDokter.getValueAt(2,3).toString())>=Integer.parseInt(tbDokter.getValueAt(3,3).toString()))
                       && (Integer.parseInt(tbDokter.getValueAt(3,3).toString())>=Integer.parseInt(tbDokter.getValueAt(4,3).toString()))){
                   DefaultPieDataset dpd = new DefaultPieDataset();
                   dpd.setValue(tbDokter.getValueAt(0,0).toString()+", "+tbDokter.getValueAt(0,1).toString()+", "+tbDokter.getValueAt(0,3).toString(),Integer.parseInt(tbDokter.getValueAt(0,3).toString()));
                   dpd.setValue(tbDokter.getValueAt(1,0).toString()+", "+tbDokter.getValueAt(1,1).toString()+", "+tbDokter.getValueAt(1,3).toString(),Integer.parseInt(tbDokter.getValueAt(1,3).toString()));
                   dpd.setValue(tbDokter.getValueAt(2,0).toString()+", "+tbDokter.getValueAt(2,1).toString()+", "+tbDokter.getValueAt(2,3).toString(),Integer.parseInt(tbDokter.getValueAt(2,3).toString()));
                   dpd.setValue(tbDokter.getValueAt(3,0).toString()+", "+tbDokter.getValueAt(3,1).toString()+", "+tbDokter.getValueAt(3,3).toString(),Integer.parseInt(tbDokter.getValueAt(3,3).toString()));
                   dpd.setValue(tbDokter.getValueAt(4,0).toString()+", "+tbDokter.getValueAt(4,1).toString()+", "+tbDokter.getValueAt(4,3).toString(),Integer.parseInt(tbDokter.getValueAt(4,3).toString()));

                   JFreeChart freeChart = ChartFactory.createPieChart("10 Besar Penyakit Di Rawat Inap Periode "+Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""),dpd,true,true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url 
                   ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Inap ",freeChart);
                   cf.setSize(internalFrame1.getWidth()-50, internalFrame1.getHeight()-50);
                   cf.setLocationRelativeTo(internalFrame1);
                   cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                   cf.setVisible(true);

               }else if((tbDokter.getRowCount()>3)&&(Integer.parseInt(tbDokter.getValueAt(0,3).toString())>=Integer.parseInt(tbDokter.getValueAt(1,3).toString()))
                       && (Integer.parseInt(tbDokter.getValueAt(1,3).toString())>=Integer.parseInt(tbDokter.getValueAt(2,3).toString()))
                       && (Integer.parseInt(tbDokter.getValueAt(2,3).toString())>=Integer.parseInt(tbDokter.getValueAt(3,3).toString()))){
                   DefaultPieDataset dpd = new DefaultPieDataset();
                   dpd.setValue(tbDokter.getValueAt(0,0).toString()+", "+tbDokter.getValueAt(0,1).toString()+", "+tbDokter.getValueAt(0,3).toString(),Integer.parseInt(tbDokter.getValueAt(0,3).toString()));
                   dpd.setValue(tbDokter.getValueAt(1,0).toString()+", "+tbDokter.getValueAt(1,1).toString()+", "+tbDokter.getValueAt(1,3).toString(),Integer.parseInt(tbDokter.getValueAt(1,3).toString()));
                   dpd.setValue(tbDokter.getValueAt(2,0).toString()+", "+tbDokter.getValueAt(2,1).toString()+", "+tbDokter.getValueAt(2,3).toString(),Integer.parseInt(tbDokter.getValueAt(2,3).toString()));
                   dpd.setValue(tbDokter.getValueAt(3,0).toString()+", "+tbDokter.getValueAt(3,1).toString()+", "+tbDokter.getValueAt(3,3).toString(),Integer.parseInt(tbDokter.getValueAt(3,3).toString()));

                   JFreeChart freeChart = ChartFactory.createPieChart("10 Besar Penyakit Di Rawat Inap Periode "+Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""),dpd,true,true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url 
                   ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Inap ",freeChart);
                   cf.setSize(internalFrame1.getWidth()-50, internalFrame1.getHeight()-50);
                   cf.setLocationRelativeTo(internalFrame1);
                   cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                   cf.setVisible(true);       

               }else if((tbDokter.getRowCount()>2)&&(Integer.parseInt(tbDokter.getValueAt(0,3).toString())>=Integer.parseInt(tbDokter.getValueAt(1,3).toString()))
                       && (Integer.parseInt(tbDokter.getValueAt(1,3).toString())>=Integer.parseInt(tbDokter.getValueAt(2,3).toString()))){
                   DefaultPieDataset dpd = new DefaultPieDataset();
                   dpd.setValue(tbDokter.getValueAt(0,0).toString()+", "+tbDokter.getValueAt(0,1).toString()+", "+tbDokter.getValueAt(0,3).toString(),Integer.parseInt(tbDokter.getValueAt(0,3).toString()));
                   dpd.setValue(tbDokter.getValueAt(1,0).toString()+", "+tbDokter.getValueAt(1,1).toString()+", "+tbDokter.getValueAt(1,3).toString(),Integer.parseInt(tbDokter.getValueAt(1,3).toString()));
                   dpd.setValue(tbDokter.getValueAt(2,0).toString()+", "+tbDokter.getValueAt(2,1).toString()+", "+tbDokter.getValueAt(2,3).toString(),Integer.parseInt(tbDokter.getValueAt(2,3).toString()));

                   JFreeChart freeChart = ChartFactory.createPieChart("10 Besar Penyakit Di Rawat Inap Periode "+Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""),dpd,true,true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url 
                   ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Inap ",freeChart);
                   cf.setSize(internalFrame1.getWidth()-50, internalFrame1.getHeight()-50);
                   cf.setLocationRelativeTo(internalFrame1);
                   cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                   cf.setVisible(true);          

               }else if((tbDokter.getRowCount()>1)&&(Integer.parseInt(tbDokter.getValueAt(0,3).toString())>=Integer.parseInt(tbDokter.getValueAt(1,3).toString()))){
                   DefaultPieDataset dpd = new DefaultPieDataset();
                   dpd.setValue(tbDokter.getValueAt(0,0).toString()+", "+tbDokter.getValueAt(0,1).toString()+", "+tbDokter.getValueAt(0,3).toString(),Integer.parseInt(tbDokter.getValueAt(0,3).toString()));
                   dpd.setValue(tbDokter.getValueAt(1,0).toString()+", "+tbDokter.getValueAt(1,1).toString()+", "+tbDokter.getValueAt(1,3).toString(),Integer.parseInt(tbDokter.getValueAt(1,3).toString()));

                   JFreeChart freeChart = ChartFactory.createPieChart("10 Besar Penyakit Di Rawat Inap Periode "+Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""),dpd,true,true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url 
                   ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Inap ",freeChart);
                   cf.setSize(internalFrame1.getWidth()-50, internalFrame1.getHeight()-50);
                   cf.setLocationRelativeTo(internalFrame1);
                   cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                   cf.setVisible(true);        

               }else{
                   JOptionPane.showMessageDialog(null,"Maaf, Silahkan urutkan secara Descanding/Besar Ke Kecil dengan menklik pada kolom jumlah..!!!!");
               }
        }else if(TabRawat.getSelectedIndex()==1){
            if((tbDokter2.getRowCount()>9)&&(Integer.parseInt(tbDokter2.getValueAt(0,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(1,3).toString()))
                       && (Integer.parseInt(tbDokter2.getValueAt(1,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(2,3).toString()))
                       && (Integer.parseInt(tbDokter2.getValueAt(2,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(3,3).toString()))
                       && (Integer.parseInt(tbDokter2.getValueAt(3,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(4,3).toString()))
                       && (Integer.parseInt(tbDokter2.getValueAt(4,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(5,3).toString()))
                       && (Integer.parseInt(tbDokter2.getValueAt(5,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(6,3).toString()))
                       && (Integer.parseInt(tbDokter2.getValueAt(6,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(7,3).toString()))
                       && (Integer.parseInt(tbDokter2.getValueAt(7,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(8,3).toString()))
                       && (Integer.parseInt(tbDokter2.getValueAt(8,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(9,3).toString()))){
                   DefaultPieDataset dpd = new DefaultPieDataset();
                   dpd.setValue(tbDokter2.getValueAt(0,0).toString()+", "+tbDokter2.getValueAt(0,1).toString()+", "+tbDokter2.getValueAt(0,3).toString(),Integer.parseInt(tbDokter2.getValueAt(0,3).toString()));
                   dpd.setValue(tbDokter2.getValueAt(1,0).toString()+", "+tbDokter2.getValueAt(1,1).toString()+", "+tbDokter2.getValueAt(1,3).toString(),Integer.parseInt(tbDokter2.getValueAt(1,3).toString()));
                   dpd.setValue(tbDokter2.getValueAt(2,0).toString()+", "+tbDokter2.getValueAt(2,1).toString()+", "+tbDokter2.getValueAt(2,3).toString(),Integer.parseInt(tbDokter2.getValueAt(2,3).toString()));
                   dpd.setValue(tbDokter2.getValueAt(3,0).toString()+", "+tbDokter2.getValueAt(3,1).toString()+", "+tbDokter2.getValueAt(3,3).toString(),Integer.parseInt(tbDokter2.getValueAt(3,3).toString()));
                   dpd.setValue(tbDokter2.getValueAt(4,0).toString()+", "+tbDokter2.getValueAt(4,1).toString()+", "+tbDokter2.getValueAt(4,3).toString(),Integer.parseInt(tbDokter2.getValueAt(4,3).toString()));
                   dpd.setValue(tbDokter2.getValueAt(5,0).toString()+", "+tbDokter2.getValueAt(5,1).toString()+", "+tbDokter2.getValueAt(5,3).toString(),Integer.parseInt(tbDokter2.getValueAt(5,3).toString()));
                   dpd.setValue(tbDokter2.getValueAt(6,0).toString()+", "+tbDokter2.getValueAt(6,1).toString()+", "+tbDokter2.getValueAt(6,3).toString(),Integer.parseInt(tbDokter2.getValueAt(6,3).toString()));
                   dpd.setValue(tbDokter2.getValueAt(7,0).toString()+", "+tbDokter2.getValueAt(7,1).toString()+", "+tbDokter2.getValueAt(7,3).toString(),Integer.parseInt(tbDokter2.getValueAt(7,3).toString()));
                   dpd.setValue(tbDokter2.getValueAt(8,0).toString()+", "+tbDokter2.getValueAt(8,1).toString()+", "+tbDokter2.getValueAt(8,3).toString(),Integer.parseInt(tbDokter2.getValueAt(8,3).toString()));
                   dpd.setValue(tbDokter2.getValueAt(9,0).toString()+", "+tbDokter2.getValueAt(9,1).toString()+", "+tbDokter2.getValueAt(9,3).toString(),Integer.parseInt(tbDokter2.getValueAt(9,3).toString()));

                   JFreeChart freeChart = ChartFactory.createPieChart("10 Besar Penyakit Di Rawat Inap Periode "+Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""),dpd,true,true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url 
                   ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Inap ",freeChart);
                   cf.setSize(internalFrame1.getWidth()-50, internalFrame1.getHeight()-50);
                   cf.setLocationRelativeTo(internalFrame1);
                   cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                   cf.setVisible(true);
               }else if((tbDokter2.getRowCount()>8)&&(Integer.parseInt(tbDokter2.getValueAt(0,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(1,3).toString()))
                       && (Integer.parseInt(tbDokter2.getValueAt(1,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(2,3).toString()))
                       && (Integer.parseInt(tbDokter2.getValueAt(2,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(3,3).toString()))
                       && (Integer.parseInt(tbDokter2.getValueAt(3,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(4,3).toString()))
                       && (Integer.parseInt(tbDokter2.getValueAt(4,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(5,3).toString()))
                       && (Integer.parseInt(tbDokter2.getValueAt(5,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(6,3).toString()))
                       && (Integer.parseInt(tbDokter2.getValueAt(6,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(7,3).toString()))
                       && (Integer.parseInt(tbDokter2.getValueAt(7,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(8,3).toString()))){
                   DefaultPieDataset dpd = new DefaultPieDataset();
                   dpd.setValue(tbDokter2.getValueAt(0,0).toString()+", "+tbDokter2.getValueAt(0,1).toString()+", "+tbDokter2.getValueAt(0,3).toString(),Integer.parseInt(tbDokter2.getValueAt(0,3).toString()));
                   dpd.setValue(tbDokter2.getValueAt(1,0).toString()+", "+tbDokter2.getValueAt(1,1).toString()+", "+tbDokter2.getValueAt(1,3).toString(),Integer.parseInt(tbDokter2.getValueAt(1,3).toString()));
                   dpd.setValue(tbDokter2.getValueAt(2,0).toString()+", "+tbDokter2.getValueAt(2,1).toString()+", "+tbDokter2.getValueAt(2,3).toString(),Integer.parseInt(tbDokter2.getValueAt(2,3).toString()));
                   dpd.setValue(tbDokter2.getValueAt(3,0).toString()+", "+tbDokter2.getValueAt(3,1).toString()+", "+tbDokter2.getValueAt(3,3).toString(),Integer.parseInt(tbDokter2.getValueAt(3,3).toString()));
                   dpd.setValue(tbDokter2.getValueAt(4,0).toString()+", "+tbDokter2.getValueAt(4,1).toString()+", "+tbDokter2.getValueAt(4,3).toString(),Integer.parseInt(tbDokter2.getValueAt(4,3).toString()));
                   dpd.setValue(tbDokter2.getValueAt(5,0).toString()+", "+tbDokter2.getValueAt(5,1).toString()+", "+tbDokter2.getValueAt(5,3).toString(),Integer.parseInt(tbDokter2.getValueAt(5,3).toString()));
                   dpd.setValue(tbDokter2.getValueAt(6,0).toString()+", "+tbDokter2.getValueAt(6,1).toString()+", "+tbDokter2.getValueAt(6,3).toString(),Integer.parseInt(tbDokter2.getValueAt(6,3).toString()));
                   dpd.setValue(tbDokter2.getValueAt(7,0).toString()+", "+tbDokter2.getValueAt(7,1).toString()+", "+tbDokter2.getValueAt(7,3).toString(),Integer.parseInt(tbDokter2.getValueAt(7,3).toString()));
                   dpd.setValue(tbDokter2.getValueAt(8,0).toString()+", "+tbDokter2.getValueAt(8,1).toString()+", "+tbDokter2.getValueAt(8,3).toString(),Integer.parseInt(tbDokter2.getValueAt(8,3).toString()));

                   JFreeChart freeChart = ChartFactory.createPieChart("10 Besar Penyakit Di Rawat Inap Periode "+Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""),dpd,true,true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url 
                   ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Inap ",freeChart);
                   cf.setSize(internalFrame1.getWidth()-50, internalFrame1.getHeight()-50);
                   cf.setLocationRelativeTo(internalFrame1);
                   cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                   cf.setVisible(true);

               }else if((tbDokter2.getRowCount()>7)&&(Integer.parseInt(tbDokter2.getValueAt(0,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(1,3).toString()))
                       && (Integer.parseInt(tbDokter2.getValueAt(1,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(2,3).toString()))
                       && (Integer.parseInt(tbDokter2.getValueAt(2,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(3,3).toString()))
                       && (Integer.parseInt(tbDokter2.getValueAt(3,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(4,3).toString()))
                       && (Integer.parseInt(tbDokter2.getValueAt(4,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(5,3).toString()))
                       && (Integer.parseInt(tbDokter2.getValueAt(5,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(6,3).toString()))
                       && (Integer.parseInt(tbDokter2.getValueAt(6,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(7,3).toString()))){
                   DefaultPieDataset dpd = new DefaultPieDataset();
                   dpd.setValue(tbDokter2.getValueAt(0,0).toString()+", "+tbDokter2.getValueAt(0,1).toString()+", "+tbDokter2.getValueAt(0,3).toString(),Integer.parseInt(tbDokter2.getValueAt(0,3).toString()));
                   dpd.setValue(tbDokter2.getValueAt(1,0).toString()+", "+tbDokter2.getValueAt(1,1).toString()+", "+tbDokter2.getValueAt(1,3).toString(),Integer.parseInt(tbDokter2.getValueAt(1,3).toString()));
                   dpd.setValue(tbDokter2.getValueAt(2,0).toString()+", "+tbDokter2.getValueAt(2,1).toString()+", "+tbDokter2.getValueAt(2,3).toString(),Integer.parseInt(tbDokter2.getValueAt(2,3).toString()));
                   dpd.setValue(tbDokter2.getValueAt(3,0).toString()+", "+tbDokter2.getValueAt(3,1).toString()+", "+tbDokter2.getValueAt(3,3).toString(),Integer.parseInt(tbDokter2.getValueAt(3,3).toString()));
                   dpd.setValue(tbDokter2.getValueAt(4,0).toString()+", "+tbDokter2.getValueAt(4,1).toString()+", "+tbDokter2.getValueAt(4,3).toString(),Integer.parseInt(tbDokter2.getValueAt(4,3).toString()));
                   dpd.setValue(tbDokter2.getValueAt(5,0).toString()+", "+tbDokter2.getValueAt(5,1).toString()+", "+tbDokter2.getValueAt(5,3).toString(),Integer.parseInt(tbDokter2.getValueAt(5,3).toString()));
                   dpd.setValue(tbDokter2.getValueAt(6,0).toString()+", "+tbDokter2.getValueAt(6,1).toString()+", "+tbDokter2.getValueAt(6,3).toString(),Integer.parseInt(tbDokter2.getValueAt(6,3).toString()));
                   dpd.setValue(tbDokter2.getValueAt(7,0).toString()+", "+tbDokter2.getValueAt(7,1).toString()+", "+tbDokter2.getValueAt(7,3).toString(),Integer.parseInt(tbDokter2.getValueAt(7,3).toString()));

                   JFreeChart freeChart = ChartFactory.createPieChart("10 Besar Penyakit Di Rawat Inap Periode "+Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""),dpd,true,true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url 
                   ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Inap ",freeChart);
                   cf.setSize(internalFrame1.getWidth()-50, internalFrame1.getHeight()-50);
                   cf.setLocationRelativeTo(internalFrame1);
                   cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                   cf.setVisible(true);

               }else if((tbDokter2.getRowCount()>6)&&(Integer.parseInt(tbDokter2.getValueAt(0,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(1,3).toString()))
                       && (Integer.parseInt(tbDokter2.getValueAt(1,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(2,3).toString()))
                       && (Integer.parseInt(tbDokter2.getValueAt(2,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(3,3).toString()))
                       && (Integer.parseInt(tbDokter2.getValueAt(3,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(4,3).toString()))
                       && (Integer.parseInt(tbDokter2.getValueAt(4,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(5,3).toString()))
                       && (Integer.parseInt(tbDokter2.getValueAt(5,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(6,3).toString()))){
                   DefaultPieDataset dpd = new DefaultPieDataset();
                   dpd.setValue(tbDokter2.getValueAt(0,0).toString()+", "+tbDokter2.getValueAt(0,1).toString()+", "+tbDokter2.getValueAt(0,3).toString(),Integer.parseInt(tbDokter2.getValueAt(0,3).toString()));
                   dpd.setValue(tbDokter2.getValueAt(1,0).toString()+", "+tbDokter2.getValueAt(1,1).toString()+", "+tbDokter2.getValueAt(1,3).toString(),Integer.parseInt(tbDokter2.getValueAt(1,3).toString()));
                   dpd.setValue(tbDokter2.getValueAt(2,0).toString()+", "+tbDokter2.getValueAt(2,1).toString()+", "+tbDokter2.getValueAt(2,3).toString(),Integer.parseInt(tbDokter2.getValueAt(2,3).toString()));
                   dpd.setValue(tbDokter2.getValueAt(3,0).toString()+", "+tbDokter2.getValueAt(3,1).toString()+", "+tbDokter2.getValueAt(3,3).toString(),Integer.parseInt(tbDokter2.getValueAt(3,3).toString()));
                   dpd.setValue(tbDokter2.getValueAt(4,0).toString()+", "+tbDokter2.getValueAt(4,1).toString()+", "+tbDokter2.getValueAt(4,3).toString(),Integer.parseInt(tbDokter2.getValueAt(4,3).toString()));
                   dpd.setValue(tbDokter2.getValueAt(5,0).toString()+", "+tbDokter2.getValueAt(5,1).toString()+", "+tbDokter2.getValueAt(5,3).toString(),Integer.parseInt(tbDokter2.getValueAt(5,3).toString()));
                   dpd.setValue(tbDokter2.getValueAt(6,0).toString()+", "+tbDokter2.getValueAt(6,1).toString()+", "+tbDokter2.getValueAt(6,3).toString(),Integer.parseInt(tbDokter2.getValueAt(6,3).toString()));

                   JFreeChart freeChart = ChartFactory.createPieChart("10 Besar Penyakit Di Rawat Inap Periode "+Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""),dpd,true,true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url 
                   ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Inap ",freeChart);
                   cf.setSize(internalFrame1.getWidth()-50, internalFrame1.getHeight()-50);
                   cf.setLocationRelativeTo(internalFrame1);
                   cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                   cf.setVisible(true);

               }else if((tbDokter2.getRowCount()>5)&&(Integer.parseInt(tbDokter2.getValueAt(0,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(1,3).toString()))
                       && (Integer.parseInt(tbDokter2.getValueAt(1,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(2,3).toString()))
                       && (Integer.parseInt(tbDokter2.getValueAt(2,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(3,3).toString()))
                       && (Integer.parseInt(tbDokter2.getValueAt(3,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(4,3).toString()))
                       && (Integer.parseInt(tbDokter2.getValueAt(4,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(5,3).toString()))){
                   DefaultPieDataset dpd = new DefaultPieDataset();
                   dpd.setValue(tbDokter2.getValueAt(0,0).toString()+", "+tbDokter2.getValueAt(0,1).toString()+", "+tbDokter2.getValueAt(0,3).toString(),Integer.parseInt(tbDokter2.getValueAt(0,3).toString()));
                   dpd.setValue(tbDokter2.getValueAt(1,0).toString()+", "+tbDokter2.getValueAt(1,1).toString()+", "+tbDokter2.getValueAt(1,3).toString(),Integer.parseInt(tbDokter2.getValueAt(1,3).toString()));
                   dpd.setValue(tbDokter2.getValueAt(2,0).toString()+", "+tbDokter2.getValueAt(2,1).toString()+", "+tbDokter2.getValueAt(2,3).toString(),Integer.parseInt(tbDokter2.getValueAt(2,3).toString()));
                   dpd.setValue(tbDokter2.getValueAt(3,0).toString()+", "+tbDokter2.getValueAt(3,1).toString()+", "+tbDokter2.getValueAt(3,3).toString(),Integer.parseInt(tbDokter2.getValueAt(3,3).toString()));
                   dpd.setValue(tbDokter2.getValueAt(4,0).toString()+", "+tbDokter2.getValueAt(4,1).toString()+", "+tbDokter2.getValueAt(4,3).toString(),Integer.parseInt(tbDokter2.getValueAt(4,3).toString()));
                   dpd.setValue(tbDokter2.getValueAt(5,0).toString()+", "+tbDokter2.getValueAt(5,1).toString()+", "+tbDokter2.getValueAt(5,3).toString(),Integer.parseInt(tbDokter2.getValueAt(5,3).toString()));

                   JFreeChart freeChart = ChartFactory.createPieChart("10 Besar Penyakit Di Rawat Inap Periode "+Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""),dpd,true,true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url 
                   ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Inap ",freeChart);
                   cf.setSize(internalFrame1.getWidth()-50, internalFrame1.getHeight()-50);
                   cf.setLocationRelativeTo(internalFrame1);
                   cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                   cf.setVisible(true);

               }else if((tbDokter2.getRowCount()>4)&&(Integer.parseInt(tbDokter2.getValueAt(0,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(1,3).toString()))
                       && (Integer.parseInt(tbDokter2.getValueAt(1,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(2,3).toString()))
                       && (Integer.parseInt(tbDokter2.getValueAt(2,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(3,3).toString()))
                       && (Integer.parseInt(tbDokter2.getValueAt(3,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(4,3).toString()))){
                   DefaultPieDataset dpd = new DefaultPieDataset();
                   dpd.setValue(tbDokter2.getValueAt(0,0).toString()+", "+tbDokter2.getValueAt(0,1).toString()+", "+tbDokter2.getValueAt(0,3).toString(),Integer.parseInt(tbDokter2.getValueAt(0,3).toString()));
                   dpd.setValue(tbDokter2.getValueAt(1,0).toString()+", "+tbDokter2.getValueAt(1,1).toString()+", "+tbDokter2.getValueAt(1,3).toString(),Integer.parseInt(tbDokter2.getValueAt(1,3).toString()));
                   dpd.setValue(tbDokter2.getValueAt(2,0).toString()+", "+tbDokter2.getValueAt(2,1).toString()+", "+tbDokter2.getValueAt(2,3).toString(),Integer.parseInt(tbDokter2.getValueAt(2,3).toString()));
                   dpd.setValue(tbDokter2.getValueAt(3,0).toString()+", "+tbDokter2.getValueAt(3,1).toString()+", "+tbDokter2.getValueAt(3,3).toString(),Integer.parseInt(tbDokter2.getValueAt(3,3).toString()));
                   dpd.setValue(tbDokter2.getValueAt(4,0).toString()+", "+tbDokter2.getValueAt(4,1).toString()+", "+tbDokter2.getValueAt(4,3).toString(),Integer.parseInt(tbDokter2.getValueAt(4,3).toString()));

                   JFreeChart freeChart = ChartFactory.createPieChart("10 Besar Penyakit Di Rawat Inap Periode "+Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""),dpd,true,true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url 
                   ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Inap ",freeChart);
                   cf.setSize(internalFrame1.getWidth()-50, internalFrame1.getHeight()-50);
                   cf.setLocationRelativeTo(internalFrame1);
                   cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                   cf.setVisible(true);

               }else if((tbDokter2.getRowCount()>3)&&(Integer.parseInt(tbDokter2.getValueAt(0,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(1,3).toString()))
                       && (Integer.parseInt(tbDokter2.getValueAt(1,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(2,3).toString()))
                       && (Integer.parseInt(tbDokter2.getValueAt(2,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(3,3).toString()))){
                   DefaultPieDataset dpd = new DefaultPieDataset();
                   dpd.setValue(tbDokter2.getValueAt(0,0).toString()+", "+tbDokter2.getValueAt(0,1).toString()+", "+tbDokter2.getValueAt(0,3).toString(),Integer.parseInt(tbDokter2.getValueAt(0,3).toString()));
                   dpd.setValue(tbDokter2.getValueAt(1,0).toString()+", "+tbDokter2.getValueAt(1,1).toString()+", "+tbDokter2.getValueAt(1,3).toString(),Integer.parseInt(tbDokter2.getValueAt(1,3).toString()));
                   dpd.setValue(tbDokter2.getValueAt(2,0).toString()+", "+tbDokter2.getValueAt(2,1).toString()+", "+tbDokter2.getValueAt(2,3).toString(),Integer.parseInt(tbDokter2.getValueAt(2,3).toString()));
                   dpd.setValue(tbDokter2.getValueAt(3,0).toString()+", "+tbDokter2.getValueAt(3,1).toString()+", "+tbDokter2.getValueAt(3,3).toString(),Integer.parseInt(tbDokter2.getValueAt(3,3).toString()));

                   JFreeChart freeChart = ChartFactory.createPieChart("10 Besar Penyakit Di Rawat Inap Periode "+Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""),dpd,true,true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url 
                   ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Inap ",freeChart);
                   cf.setSize(internalFrame1.getWidth()-50, internalFrame1.getHeight()-50);
                   cf.setLocationRelativeTo(internalFrame1);
                   cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                   cf.setVisible(true);       

               }else if((tbDokter2.getRowCount()>2)&&(Integer.parseInt(tbDokter2.getValueAt(0,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(1,3).toString()))
                       && (Integer.parseInt(tbDokter2.getValueAt(1,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(2,3).toString()))){
                   DefaultPieDataset dpd = new DefaultPieDataset();
                   dpd.setValue(tbDokter2.getValueAt(0,0).toString()+", "+tbDokter2.getValueAt(0,1).toString()+", "+tbDokter2.getValueAt(0,3).toString(),Integer.parseInt(tbDokter2.getValueAt(0,3).toString()));
                   dpd.setValue(tbDokter2.getValueAt(1,0).toString()+", "+tbDokter2.getValueAt(1,1).toString()+", "+tbDokter2.getValueAt(1,3).toString(),Integer.parseInt(tbDokter2.getValueAt(1,3).toString()));
                   dpd.setValue(tbDokter2.getValueAt(2,0).toString()+", "+tbDokter2.getValueAt(2,1).toString()+", "+tbDokter2.getValueAt(2,3).toString(),Integer.parseInt(tbDokter2.getValueAt(2,3).toString()));

                   JFreeChart freeChart = ChartFactory.createPieChart("10 Besar Penyakit Di Rawat Inap Periode "+Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""),dpd,true,true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url 
                   ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Inap ",freeChart);
                   cf.setSize(internalFrame1.getWidth()-50, internalFrame1.getHeight()-50);
                   cf.setLocationRelativeTo(internalFrame1);
                   cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                   cf.setVisible(true);          

               }else if((tbDokter2.getRowCount()>1)&&(Integer.parseInt(tbDokter2.getValueAt(0,3).toString())>=Integer.parseInt(tbDokter2.getValueAt(1,3).toString()))){
                   DefaultPieDataset dpd = new DefaultPieDataset();
                   dpd.setValue(tbDokter2.getValueAt(0,0).toString()+", "+tbDokter2.getValueAt(0,1).toString()+", "+tbDokter2.getValueAt(0,3).toString(),Integer.parseInt(tbDokter2.getValueAt(0,3).toString()));
                   dpd.setValue(tbDokter2.getValueAt(1,0).toString()+", "+tbDokter2.getValueAt(1,1).toString()+", "+tbDokter2.getValueAt(1,3).toString(),Integer.parseInt(tbDokter2.getValueAt(1,3).toString()));

                   JFreeChart freeChart = ChartFactory.createPieChart("10 Besar Penyakit Di Rawat Inap Periode "+Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""),dpd,true,true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url 
                   ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Inap ",freeChart);
                   cf.setSize(internalFrame1.getWidth()-50, internalFrame1.getHeight()-50);
                   cf.setLocationRelativeTo(internalFrame1);
                   cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                   cf.setVisible(true);        

               }else{
                   JOptionPane.showMessageDialog(null,"Maaf, Silahkan urutkan secara Descanding/Besar Ke Kecil dengan menklik pada kolom jumlah..!!!!");
               }
        }
        
        } else {
            JOptionPane.showMessageDialog(null, "Untuk mencetak grafik pie 10 penyakit terbanyak, silakan koordinasi dg. Inst. Rekam Medik...!!!");
            Tgl1.requestFocus();
        }       
}//GEN-LAST:event_ppGrafikTerbanyakPieActionPerformed

private void ppGrafikTerkecilBatangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikTerkecilBatangActionPerformed
    if (akses.getkode().equals("Admin Utama") || akses.getpenyakit() == true) {
    if(TabRawat.getSelectedIndex()==0){
            if((tbDokter.getRowCount()>9)&&(Integer.parseInt(tbDokter.getValueAt(0,3).toString())<=Integer.parseInt(tbDokter.getValueAt(1,3).toString()))
                        && (Integer.parseInt(tbDokter.getValueAt(1,3).toString())<=Integer.parseInt(tbDokter.getValueAt(2,3).toString()))
                        && (Integer.parseInt(tbDokter.getValueAt(2,3).toString())<=Integer.parseInt(tbDokter.getValueAt(3,3).toString()))
                        && (Integer.parseInt(tbDokter.getValueAt(3,3).toString())<=Integer.parseInt(tbDokter.getValueAt(4,3).toString()))
                        && (Integer.parseInt(tbDokter.getValueAt(4,3).toString())<=Integer.parseInt(tbDokter.getValueAt(5,3).toString()))
                        && (Integer.parseInt(tbDokter.getValueAt(5,3).toString())<=Integer.parseInt(tbDokter.getValueAt(6,3).toString()))
                        && (Integer.parseInt(tbDokter.getValueAt(6,3).toString())<=Integer.parseInt(tbDokter.getValueAt(7,3).toString()))
                        && (Integer.parseInt(tbDokter.getValueAt(7,3).toString())<=Integer.parseInt(tbDokter.getValueAt(8,3).toString()))
                        && (Integer.parseInt(tbDokter.getValueAt(8,3).toString())<=Integer.parseInt(tbDokter.getValueAt(9,3).toString()))){
                    /*DefaultPieDataset dpd = new DefaultPieDataset();
                    dpd.setValue(tbDokter.getValueAt(0,0).toString()+", "+tbDokter.getValueAt(0,1).toString(),Integer.parseInt(tbDokter.getValueAt(0,3).toString()));
                    dpd.setValue(tbDokter.getValueAt(1,0).toString()+", "+tbDokter.getValueAt(1,1).toString(),Integer.parseInt(tbDokter.getValueAt(1,3).toString()));
                    dpd.setValue(tbDokter.getValueAt(2,0).toString()+", "+tbDokter.getValueAt(2,1).toString(),Integer.parseInt(tbDokter.getValueAt(2,3).toString()));
                    dpd.setValue(tbDokter.getValueAt(3,0).toString()+", "+tbDokter.getValueAt(3,1).toString(),Integer.parseInt(tbDokter.getValueAt(3,3).toString()));
                    dpd.setValue(tbDokter.getValueAt(4,0).toString()+", "+tbDokter.getValueAt(4,1).toString(),Integer.parseInt(tbDokter.getValueAt(4,3).toString()));
                    dpd.setValue(tbDokter.getValueAt(5,0).toString()+", "+tbDokter.getValueAt(5,1).toString(),Integer.parseInt(tbDokter.getValueAt(5,3).toString()));
                    dpd.setValue(tbDokter.getValueAt(6,0).toString()+", "+tbDokter.getValueAt(6,1).toString(),Integer.parseInt(tbDokter.getValueAt(6,3).toString()));
                    dpd.setValue(tbDokter.getValueAt(7,0).toString()+", "+tbDokter.getValueAt(7,1).toString(),Integer.parseInt(tbDokter.getValueAt(7,3).toString()));
                    dpd.setValue(tbDokter.getValueAt(8,0).toString()+", "+tbDokter.getValueAt(8,1).toString(),Integer.parseInt(tbDokter.getValueAt(8,3).toString()));
                    dpd.setValue(tbDokter.getValueAt(9,0).toString()+", "+tbDokter.getValueAt(9,1).toString(),Integer.parseInt(tbDokter.getValueAt(9,3).toString()));

                    JFreeChart freeChart = ChartFactory.createPieChart("10 Besar Penyakit Di Rawat Inap Periode "+Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""),dpd,true,true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url 
                    ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Inap ",freeChart);
                    cf.setSize(internalFrame1.getWidth()-50, internalFrame1.getHeight()-50);
                    cf.setLocationRelativeTo(internalFrame1);
                    cf.setVisible(true);*/
                    DefaultCategoryDataset dcd = new DefaultCategoryDataset();
                    dcd.setValue(Integer.parseInt(tbDokter.getValueAt(0,3).toString()),tbDokter.getValueAt(0,0).toString()+", "+tbDokter.getValueAt(0,1).toString()+", "+tbDokter.getValueAt(0,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter.getValueAt(1,3).toString()),tbDokter.getValueAt(1,0).toString()+", "+tbDokter.getValueAt(1,1).toString()+", "+tbDokter.getValueAt(1,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter.getValueAt(2,3).toString()),tbDokter.getValueAt(2,0).toString()+", "+tbDokter.getValueAt(2,1).toString()+", "+tbDokter.getValueAt(2,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter.getValueAt(3,3).toString()),tbDokter.getValueAt(3,0).toString()+", "+tbDokter.getValueAt(3,1).toString()+", "+tbDokter.getValueAt(3,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter.getValueAt(4,3).toString()),tbDokter.getValueAt(4,0).toString()+", "+tbDokter.getValueAt(4,1).toString()+", "+tbDokter.getValueAt(4,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter.getValueAt(5,3).toString()),tbDokter.getValueAt(5,0).toString()+", "+tbDokter.getValueAt(5,1).toString()+", "+tbDokter.getValueAt(5,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter.getValueAt(6,3).toString()),tbDokter.getValueAt(6,0).toString()+", "+tbDokter.getValueAt(6,1).toString()+", "+tbDokter.getValueAt(6,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter.getValueAt(7,3).toString()),tbDokter.getValueAt(7,0).toString()+", "+tbDokter.getValueAt(7,1).toString()+", "+tbDokter.getValueAt(7,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter.getValueAt(8,3).toString()),tbDokter.getValueAt(8,0).toString()+", "+tbDokter.getValueAt(8,1).toString()+", "+tbDokter.getValueAt(8,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter.getValueAt(9,3).toString()),tbDokter.getValueAt(9,0).toString()+", "+tbDokter.getValueAt(9,1).toString()+", "+tbDokter.getValueAt(9,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));


                    JFreeChart freeChart = ChartFactory.createBarChart("10 Besar Penyakit Di Rawat Inap","Periode","Jumlah Penyakit", dcd, PlotOrientation.VERTICAL,true, true,true); //String arg0,String arg1,String arg2,Category Datasheet,Plot Orientation,boolean arg4,boolean arg5,boolean arg6
                    ChartFrame cf = new ChartFrame("Frafik 10 Besar Penyakit Di Rawat Inap",freeChart);

                    cf.setSize(internalFrame1.getWidth()-50,internalFrame1.getHeight()-50);
                    cf.setLocationRelativeTo(internalFrame1);
                    cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                    cf.setVisible(true);

                }else if((tbDokter.getRowCount()>8)&&(Integer.parseInt(tbDokter.getValueAt(0,3).toString())<=Integer.parseInt(tbDokter.getValueAt(1,3).toString()))
                        && (Integer.parseInt(tbDokter.getValueAt(1,3).toString())<=Integer.parseInt(tbDokter.getValueAt(2,3).toString()))
                        && (Integer.parseInt(tbDokter.getValueAt(2,3).toString())<=Integer.parseInt(tbDokter.getValueAt(3,3).toString()))
                        && (Integer.parseInt(tbDokter.getValueAt(3,3).toString())<=Integer.parseInt(tbDokter.getValueAt(4,3).toString()))
                        && (Integer.parseInt(tbDokter.getValueAt(4,3).toString())<=Integer.parseInt(tbDokter.getValueAt(5,3).toString()))
                        && (Integer.parseInt(tbDokter.getValueAt(5,3).toString())<=Integer.parseInt(tbDokter.getValueAt(6,3).toString()))
                        && (Integer.parseInt(tbDokter.getValueAt(6,3).toString())<=Integer.parseInt(tbDokter.getValueAt(7,3).toString()))
                        && (Integer.parseInt(tbDokter.getValueAt(7,3).toString())<=Integer.parseInt(tbDokter.getValueAt(8,3).toString()))){
                    DefaultCategoryDataset dcd = new DefaultCategoryDataset();
                    dcd.setValue(Integer.parseInt(tbDokter.getValueAt(0,3).toString()),tbDokter.getValueAt(0,0).toString()+", "+tbDokter.getValueAt(0,1).toString()+", "+tbDokter.getValueAt(0,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter.getValueAt(1,3).toString()),tbDokter.getValueAt(1,0).toString()+", "+tbDokter.getValueAt(1,1).toString()+", "+tbDokter.getValueAt(1,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter.getValueAt(2,3).toString()),tbDokter.getValueAt(2,0).toString()+", "+tbDokter.getValueAt(2,1).toString()+", "+tbDokter.getValueAt(2,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter.getValueAt(3,3).toString()),tbDokter.getValueAt(3,0).toString()+", "+tbDokter.getValueAt(3,1).toString()+", "+tbDokter.getValueAt(3,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter.getValueAt(4,3).toString()),tbDokter.getValueAt(4,0).toString()+", "+tbDokter.getValueAt(4,1).toString()+", "+tbDokter.getValueAt(4,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter.getValueAt(5,3).toString()),tbDokter.getValueAt(5,0).toString()+", "+tbDokter.getValueAt(5,1).toString()+", "+tbDokter.getValueAt(5,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter.getValueAt(6,3).toString()),tbDokter.getValueAt(6,0).toString()+", "+tbDokter.getValueAt(6,1).toString()+", "+tbDokter.getValueAt(6,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter.getValueAt(7,3).toString()),tbDokter.getValueAt(7,0).toString()+", "+tbDokter.getValueAt(7,1).toString()+", "+tbDokter.getValueAt(7,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter.getValueAt(8,3).toString()),tbDokter.getValueAt(8,0).toString()+", "+tbDokter.getValueAt(8,1).toString()+", "+tbDokter.getValueAt(8,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));

                    JFreeChart freeChart = ChartFactory.createBarChart("10 Besar Penyakit Di Rawat Inap","Periode","Jumlah Penyakit", dcd, PlotOrientation.VERTICAL,true, true,true); //String arg0,String arg1,String arg2,Category Datasheet,Plot Orientation,boolean arg4,boolean arg5,boolean arg6
                    ChartFrame cf = new ChartFrame("Frafik 10 Besar Penyakit Di Rawat Inap",freeChart);

                    cf.setSize(internalFrame1.getWidth()-50,internalFrame1.getHeight()-50);
                    cf.setLocationRelativeTo(internalFrame1);
                    cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                    cf.setVisible(true);

                }else if((tbDokter.getRowCount()>7)&&(Integer.parseInt(tbDokter.getValueAt(0,3).toString())<=Integer.parseInt(tbDokter.getValueAt(1,3).toString()))
                        && (Integer.parseInt(tbDokter.getValueAt(1,3).toString())<=Integer.parseInt(tbDokter.getValueAt(2,3).toString()))
                        && (Integer.parseInt(tbDokter.getValueAt(2,3).toString())<=Integer.parseInt(tbDokter.getValueAt(3,3).toString()))
                        && (Integer.parseInt(tbDokter.getValueAt(3,3).toString())<=Integer.parseInt(tbDokter.getValueAt(4,3).toString()))
                        && (Integer.parseInt(tbDokter.getValueAt(4,3).toString())<=Integer.parseInt(tbDokter.getValueAt(5,3).toString()))
                        && (Integer.parseInt(tbDokter.getValueAt(5,3).toString())<=Integer.parseInt(tbDokter.getValueAt(6,3).toString()))
                        && (Integer.parseInt(tbDokter.getValueAt(6,3).toString())<=Integer.parseInt(tbDokter.getValueAt(7,3).toString()))){
                    DefaultCategoryDataset dcd = new DefaultCategoryDataset();
                    dcd.setValue(Integer.parseInt(tbDokter.getValueAt(0,3).toString()),tbDokter.getValueAt(0,0).toString()+", "+tbDokter.getValueAt(0,1).toString()+", "+tbDokter.getValueAt(0,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter.getValueAt(1,3).toString()),tbDokter.getValueAt(1,0).toString()+", "+tbDokter.getValueAt(1,1).toString()+", "+tbDokter.getValueAt(1,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter.getValueAt(2,3).toString()),tbDokter.getValueAt(2,0).toString()+", "+tbDokter.getValueAt(2,1).toString()+", "+tbDokter.getValueAt(2,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter.getValueAt(3,3).toString()),tbDokter.getValueAt(3,0).toString()+", "+tbDokter.getValueAt(3,1).toString()+", "+tbDokter.getValueAt(3,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter.getValueAt(4,3).toString()),tbDokter.getValueAt(4,0).toString()+", "+tbDokter.getValueAt(4,1).toString()+", "+tbDokter.getValueAt(4,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter.getValueAt(5,3).toString()),tbDokter.getValueAt(5,0).toString()+", "+tbDokter.getValueAt(5,1).toString()+", "+tbDokter.getValueAt(5,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter.getValueAt(6,3).toString()),tbDokter.getValueAt(6,0).toString()+", "+tbDokter.getValueAt(6,1).toString()+", "+tbDokter.getValueAt(6,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter.getValueAt(7,3).toString()),tbDokter.getValueAt(7,0).toString()+", "+tbDokter.getValueAt(7,1).toString()+", "+tbDokter.getValueAt(7,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));

                    JFreeChart freeChart = ChartFactory.createBarChart("10 Besar Penyakit Di Rawat Inap","Periode","Jumlah Penyakit", dcd, PlotOrientation.VERTICAL,true, true,true); //String arg0,String arg1,String arg2,Category Datasheet,Plot Orientation,boolean arg4,boolean arg5,boolean arg6
                    ChartFrame cf = new ChartFrame("Frafik 10 Besar Penyakit Di Rawat Inap",freeChart);

                    cf.setSize(internalFrame1.getWidth()-50,internalFrame1.getHeight()-50);
                    cf.setLocationRelativeTo(internalFrame1);
                    cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                    cf.setVisible(true);

                }else if((tbDokter.getRowCount()>6)&&(Integer.parseInt(tbDokter.getValueAt(0,3).toString())<=Integer.parseInt(tbDokter.getValueAt(1,3).toString()))
                        && (Integer.parseInt(tbDokter.getValueAt(1,3).toString())<=Integer.parseInt(tbDokter.getValueAt(2,3).toString()))
                        && (Integer.parseInt(tbDokter.getValueAt(2,3).toString())<=Integer.parseInt(tbDokter.getValueAt(3,3).toString()))
                        && (Integer.parseInt(tbDokter.getValueAt(3,3).toString())<=Integer.parseInt(tbDokter.getValueAt(4,3).toString()))
                        && (Integer.parseInt(tbDokter.getValueAt(4,3).toString())<=Integer.parseInt(tbDokter.getValueAt(5,3).toString()))
                        && (Integer.parseInt(tbDokter.getValueAt(5,3).toString())<=Integer.parseInt(tbDokter.getValueAt(6,3).toString()))){
                    DefaultCategoryDataset dcd = new DefaultCategoryDataset();
                    dcd.setValue(Integer.parseInt(tbDokter.getValueAt(0,3).toString()),tbDokter.getValueAt(0,0).toString()+", "+tbDokter.getValueAt(0,1).toString()+", "+tbDokter.getValueAt(0,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter.getValueAt(1,3).toString()),tbDokter.getValueAt(1,0).toString()+", "+tbDokter.getValueAt(1,1).toString()+", "+tbDokter.getValueAt(1,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter.getValueAt(2,3).toString()),tbDokter.getValueAt(2,0).toString()+", "+tbDokter.getValueAt(2,1).toString()+", "+tbDokter.getValueAt(2,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter.getValueAt(3,3).toString()),tbDokter.getValueAt(3,0).toString()+", "+tbDokter.getValueAt(3,1).toString()+", "+tbDokter.getValueAt(3,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter.getValueAt(4,3).toString()),tbDokter.getValueAt(4,0).toString()+", "+tbDokter.getValueAt(4,1).toString()+", "+tbDokter.getValueAt(4,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter.getValueAt(5,3).toString()),tbDokter.getValueAt(5,0).toString()+", "+tbDokter.getValueAt(5,1).toString()+", "+tbDokter.getValueAt(5,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter.getValueAt(6,3).toString()),tbDokter.getValueAt(6,0).toString()+", "+tbDokter.getValueAt(6,1).toString()+", "+tbDokter.getValueAt(6,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));

                    JFreeChart freeChart = ChartFactory.createBarChart("10 Besar Penyakit Di Rawat Inap","Periode","Jumlah Penyakit", dcd, PlotOrientation.VERTICAL,true, true,true); //String arg0,String arg1,String arg2,Category Datasheet,Plot Orientation,boolean arg4,boolean arg5,boolean arg6
                    ChartFrame cf = new ChartFrame("Frafik 10 Besar Penyakit Di Rawat Inap",freeChart);

                    cf.setSize(internalFrame1.getWidth()-50,internalFrame1.getHeight()-50);
                    cf.setLocationRelativeTo(internalFrame1);
                    cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                    cf.setVisible(true);

                }else if((tbDokter.getRowCount()>5)&&(Integer.parseInt(tbDokter.getValueAt(0,3).toString())<=Integer.parseInt(tbDokter.getValueAt(1,3).toString()))
                        && (Integer.parseInt(tbDokter.getValueAt(1,3).toString())<=Integer.parseInt(tbDokter.getValueAt(2,3).toString()))
                        && (Integer.parseInt(tbDokter.getValueAt(2,3).toString())<=Integer.parseInt(tbDokter.getValueAt(3,3).toString()))
                        && (Integer.parseInt(tbDokter.getValueAt(3,3).toString())<=Integer.parseInt(tbDokter.getValueAt(4,3).toString()))
                        && (Integer.parseInt(tbDokter.getValueAt(4,3).toString())<=Integer.parseInt(tbDokter.getValueAt(5,3).toString()))){
                    DefaultCategoryDataset dcd = new DefaultCategoryDataset();
                    dcd.setValue(Integer.parseInt(tbDokter.getValueAt(0,3).toString()),tbDokter.getValueAt(0,0).toString()+", "+tbDokter.getValueAt(0,1).toString()+", "+tbDokter.getValueAt(0,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter.getValueAt(1,3).toString()),tbDokter.getValueAt(1,0).toString()+", "+tbDokter.getValueAt(1,1).toString()+", "+tbDokter.getValueAt(1,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter.getValueAt(2,3).toString()),tbDokter.getValueAt(2,0).toString()+", "+tbDokter.getValueAt(2,1).toString()+", "+tbDokter.getValueAt(2,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter.getValueAt(3,3).toString()),tbDokter.getValueAt(3,0).toString()+", "+tbDokter.getValueAt(3,1).toString()+", "+tbDokter.getValueAt(3,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter.getValueAt(4,3).toString()),tbDokter.getValueAt(4,0).toString()+", "+tbDokter.getValueAt(4,1).toString()+", "+tbDokter.getValueAt(4,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter.getValueAt(5,3).toString()),tbDokter.getValueAt(5,0).toString()+", "+tbDokter.getValueAt(5,1).toString()+", "+tbDokter.getValueAt(5,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));

                    JFreeChart freeChart = ChartFactory.createBarChart("10 Besar Penyakit Di Rawat Inap","Periode","Jumlah Penyakit", dcd, PlotOrientation.VERTICAL,true, true,true); //String arg0,String arg1,String arg2,Category Datasheet,Plot Orientation,boolean arg4,boolean arg5,boolean arg6
                    ChartFrame cf = new ChartFrame("Frafik 10 Besar Penyakit Di Rawat Inap",freeChart);

                    cf.setSize(internalFrame1.getWidth()-50,internalFrame1.getHeight()-50);
                    cf.setLocationRelativeTo(internalFrame1);
                    cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                    cf.setVisible(true);

                }else if((tbDokter.getRowCount()>4)&&(Integer.parseInt(tbDokter.getValueAt(0,3).toString())<=Integer.parseInt(tbDokter.getValueAt(1,3).toString()))
                        && (Integer.parseInt(tbDokter.getValueAt(1,3).toString())<=Integer.parseInt(tbDokter.getValueAt(2,3).toString()))
                        && (Integer.parseInt(tbDokter.getValueAt(2,3).toString())<=Integer.parseInt(tbDokter.getValueAt(3,3).toString()))
                        && (Integer.parseInt(tbDokter.getValueAt(3,3).toString())<=Integer.parseInt(tbDokter.getValueAt(4,3).toString()))){
                    DefaultCategoryDataset dcd = new DefaultCategoryDataset();
                    dcd.setValue(Integer.parseInt(tbDokter.getValueAt(0,3).toString()),tbDokter.getValueAt(0,0).toString()+", "+tbDokter.getValueAt(0,1).toString()+", "+tbDokter.getValueAt(0,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter.getValueAt(1,3).toString()),tbDokter.getValueAt(1,0).toString()+", "+tbDokter.getValueAt(1,1).toString()+", "+tbDokter.getValueAt(1,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter.getValueAt(2,3).toString()),tbDokter.getValueAt(2,0).toString()+", "+tbDokter.getValueAt(2,1).toString()+", "+tbDokter.getValueAt(2,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter.getValueAt(3,3).toString()),tbDokter.getValueAt(3,0).toString()+", "+tbDokter.getValueAt(3,1).toString()+", "+tbDokter.getValueAt(3,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter.getValueAt(4,3).toString()),tbDokter.getValueAt(4,0).toString()+", "+tbDokter.getValueAt(4,1).toString()+", "+tbDokter.getValueAt(4,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));

                    JFreeChart freeChart = ChartFactory.createBarChart("10 Besar Penyakit Di Rawat Inap","Periode","Jumlah Penyakit", dcd, PlotOrientation.VERTICAL,true, true,true); //String arg0,String arg1,String arg2,Category Datasheet,Plot Orientation,boolean arg4,boolean arg5,boolean arg6
                    ChartFrame cf = new ChartFrame("Frafik 10 Besar Penyakit Di Rawat Inap",freeChart);

                    cf.setSize(internalFrame1.getWidth()-50,internalFrame1.getHeight()-50);
                    cf.setLocationRelativeTo(internalFrame1);
                    cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                    cf.setVisible(true);

                }else if((tbDokter.getRowCount()>3)&&(Integer.parseInt(tbDokter.getValueAt(0,3).toString())<=Integer.parseInt(tbDokter.getValueAt(1,3).toString()))
                        && (Integer.parseInt(tbDokter.getValueAt(1,3).toString())<=Integer.parseInt(tbDokter.getValueAt(2,3).toString()))
                        && (Integer.parseInt(tbDokter.getValueAt(2,3).toString())<=Integer.parseInt(tbDokter.getValueAt(3,3).toString()))){
                    DefaultCategoryDataset dcd = new DefaultCategoryDataset();
                    dcd.setValue(Integer.parseInt(tbDokter.getValueAt(0,3).toString()),tbDokter.getValueAt(0,0).toString()+", "+tbDokter.getValueAt(0,1).toString()+", "+tbDokter.getValueAt(0,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter.getValueAt(1,3).toString()),tbDokter.getValueAt(1,0).toString()+", "+tbDokter.getValueAt(1,1).toString()+", "+tbDokter.getValueAt(1,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter.getValueAt(2,3).toString()),tbDokter.getValueAt(2,0).toString()+", "+tbDokter.getValueAt(2,1).toString()+", "+tbDokter.getValueAt(2,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter.getValueAt(3,3).toString()),tbDokter.getValueAt(3,0).toString()+", "+tbDokter.getValueAt(3,1).toString()+", "+tbDokter.getValueAt(3,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));

                    JFreeChart freeChart = ChartFactory.createBarChart("10 Besar Penyakit Di Rawat Inap","Periode","Jumlah Penyakit", dcd, PlotOrientation.VERTICAL,true, true,true); //String arg0,String arg1,String arg2,Category Datasheet,Plot Orientation,boolean arg4,boolean arg5,boolean arg6
                    ChartFrame cf = new ChartFrame("Frafik 10 Besar Penyakit Di Rawat Inap",freeChart);

                    cf.setSize(internalFrame1.getWidth()-50,internalFrame1.getHeight()-50);
                    cf.setLocationRelativeTo(internalFrame1);
                    cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                    cf.setVisible(true);          

                }else if((tbDokter.getRowCount()>2)&&(Integer.parseInt(tbDokter.getValueAt(0,3).toString())<=Integer.parseInt(tbDokter.getValueAt(1,3).toString()))
                        && (Integer.parseInt(tbDokter.getValueAt(1,3).toString())<=Integer.parseInt(tbDokter.getValueAt(2,3).toString()))){
                    DefaultCategoryDataset dcd = new DefaultCategoryDataset();
                    dcd.setValue(Integer.parseInt(tbDokter.getValueAt(0,3).toString()),tbDokter.getValueAt(0,0).toString()+", "+tbDokter.getValueAt(0,1).toString()+", "+tbDokter.getValueAt(0,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter.getValueAt(1,3).toString()),tbDokter.getValueAt(1,0).toString()+", "+tbDokter.getValueAt(1,1).toString()+", "+tbDokter.getValueAt(1,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter.getValueAt(2,3).toString()),tbDokter.getValueAt(2,0).toString()+", "+tbDokter.getValueAt(2,1).toString()+", "+tbDokter.getValueAt(2,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));

                    JFreeChart freeChart = ChartFactory.createBarChart("10 Besar Penyakit Di Rawat Inap","Periode","Jumlah Penyakit", dcd, PlotOrientation.VERTICAL,true, true,true); //String arg0,String arg1,String arg2,Category Datasheet,Plot Orientation,boolean arg4,boolean arg5,boolean arg6
                    ChartFrame cf = new ChartFrame("Frafik 10 Besar Penyakit Di Rawat Inap",freeChart);

                    cf.setSize(internalFrame1.getWidth()-50,internalFrame1.getHeight()-50);
                    cf.setLocationRelativeTo(internalFrame1);
                    cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                    cf.setVisible(true);           

                }else if((tbDokter.getRowCount()>1)&&(Integer.parseInt(tbDokter.getValueAt(0,3).toString())<=Integer.parseInt(tbDokter.getValueAt(1,3).toString()))){
                    DefaultCategoryDataset dcd = new DefaultCategoryDataset();
                    dcd.setValue(Integer.parseInt(tbDokter.getValueAt(0,3).toString()),tbDokter.getValueAt(0,0).toString()+", "+tbDokter.getValueAt(0,1).toString()+", "+tbDokter.getValueAt(0,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter.getValueAt(1,3).toString()),tbDokter.getValueAt(1,0).toString()+", "+tbDokter.getValueAt(1,1).toString()+", "+tbDokter.getValueAt(1,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));

                    JFreeChart freeChart = ChartFactory.createBarChart("10 Besar Penyakit Di Rawat Inap","Periode","Jumlah Penyakit", dcd, PlotOrientation.VERTICAL,true, true,true); //String arg0,String arg1,String arg2,Category Datasheet,Plot Orientation,boolean arg4,boolean arg5,boolean arg6
                    ChartFrame cf = new ChartFrame("Frafik 10 Besar Penyakit Di Rawat Inap",freeChart);

                    cf.setSize(internalFrame1.getWidth()-50,internalFrame1.getHeight()-50);
                    cf.setLocationRelativeTo(internalFrame1);
                    cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                    cf.setVisible(true);            

                }else{
                    JOptionPane.showMessageDialog(null,"Maaf, Silahkan urutkan secara Descanding/Besar Ke Kecil dengan menklik pada kolom jumlah..!!!!");
                }
        }else if(TabRawat.getSelectedIndex()==1){
            if((tbDokter2.getRowCount()>9)&&(Integer.parseInt(tbDokter2.getValueAt(0,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(1,3).toString()))
                        && (Integer.parseInt(tbDokter2.getValueAt(1,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(2,3).toString()))
                        && (Integer.parseInt(tbDokter2.getValueAt(2,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(3,3).toString()))
                        && (Integer.parseInt(tbDokter2.getValueAt(3,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(4,3).toString()))
                        && (Integer.parseInt(tbDokter2.getValueAt(4,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(5,3).toString()))
                        && (Integer.parseInt(tbDokter2.getValueAt(5,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(6,3).toString()))
                        && (Integer.parseInt(tbDokter2.getValueAt(6,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(7,3).toString()))
                        && (Integer.parseInt(tbDokter2.getValueAt(7,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(8,3).toString()))
                        && (Integer.parseInt(tbDokter2.getValueAt(8,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(9,3).toString()))){
                    /*DefaultPieDataset dpd = new DefaultPieDataset();
                    dpd.setValue(tbDokter2.getValueAt(0,0).toString()+", "+tbDokter2.getValueAt(0,1).toString(),Integer.parseInt(tbDokter2.getValueAt(0,3).toString()));
                    dpd.setValue(tbDokter2.getValueAt(1,0).toString()+", "+tbDokter2.getValueAt(1,1).toString(),Integer.parseInt(tbDokter2.getValueAt(1,3).toString()));
                    dpd.setValue(tbDokter2.getValueAt(2,0).toString()+", "+tbDokter2.getValueAt(2,1).toString(),Integer.parseInt(tbDokter2.getValueAt(2,3).toString()));
                    dpd.setValue(tbDokter2.getValueAt(3,0).toString()+", "+tbDokter2.getValueAt(3,1).toString(),Integer.parseInt(tbDokter2.getValueAt(3,3).toString()));
                    dpd.setValue(tbDokter2.getValueAt(4,0).toString()+", "+tbDokter2.getValueAt(4,1).toString(),Integer.parseInt(tbDokter2.getValueAt(4,3).toString()));
                    dpd.setValue(tbDokter2.getValueAt(5,0).toString()+", "+tbDokter2.getValueAt(5,1).toString(),Integer.parseInt(tbDokter2.getValueAt(5,3).toString()));
                    dpd.setValue(tbDokter2.getValueAt(6,0).toString()+", "+tbDokter2.getValueAt(6,1).toString(),Integer.parseInt(tbDokter2.getValueAt(6,3).toString()));
                    dpd.setValue(tbDokter2.getValueAt(7,0).toString()+", "+tbDokter2.getValueAt(7,1).toString(),Integer.parseInt(tbDokter2.getValueAt(7,3).toString()));
                    dpd.setValue(tbDokter2.getValueAt(8,0).toString()+", "+tbDokter2.getValueAt(8,1).toString(),Integer.parseInt(tbDokter2.getValueAt(8,3).toString()));
                    dpd.setValue(tbDokter2.getValueAt(9,0).toString()+", "+tbDokter2.getValueAt(9,1).toString(),Integer.parseInt(tbDokter2.getValueAt(9,3).toString()));

                    JFreeChart freeChart = ChartFactory.createPieChart("10 Besar Penyakit Di Rawat Inap Periode "+Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""),dpd,true,true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url 
                    ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Inap ",freeChart);
                    cf.setSize(internalFrame1.getWidth()-50, internalFrame1.getHeight()-50);
                    cf.setLocationRelativeTo(internalFrame1);
                    cf.setVisible(true);*/
                    DefaultCategoryDataset dcd = new DefaultCategoryDataset();
                    dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(0,3).toString()),tbDokter2.getValueAt(0,0).toString()+", "+tbDokter2.getValueAt(0,1).toString()+", "+tbDokter2.getValueAt(0,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(1,3).toString()),tbDokter2.getValueAt(1,0).toString()+", "+tbDokter2.getValueAt(1,1).toString()+", "+tbDokter2.getValueAt(1,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(2,3).toString()),tbDokter2.getValueAt(2,0).toString()+", "+tbDokter2.getValueAt(2,1).toString()+", "+tbDokter2.getValueAt(2,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(3,3).toString()),tbDokter2.getValueAt(3,0).toString()+", "+tbDokter2.getValueAt(3,1).toString()+", "+tbDokter2.getValueAt(3,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(4,3).toString()),tbDokter2.getValueAt(4,0).toString()+", "+tbDokter2.getValueAt(4,1).toString()+", "+tbDokter2.getValueAt(4,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(5,3).toString()),tbDokter2.getValueAt(5,0).toString()+", "+tbDokter2.getValueAt(5,1).toString()+", "+tbDokter2.getValueAt(5,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(6,3).toString()),tbDokter2.getValueAt(6,0).toString()+", "+tbDokter2.getValueAt(6,1).toString()+", "+tbDokter2.getValueAt(6,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(7,3).toString()),tbDokter2.getValueAt(7,0).toString()+", "+tbDokter2.getValueAt(7,1).toString()+", "+tbDokter2.getValueAt(7,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(8,3).toString()),tbDokter2.getValueAt(8,0).toString()+", "+tbDokter2.getValueAt(8,1).toString()+", "+tbDokter2.getValueAt(8,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(9,3).toString()),tbDokter2.getValueAt(9,0).toString()+", "+tbDokter2.getValueAt(9,1).toString()+", "+tbDokter2.getValueAt(9,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));


                    JFreeChart freeChart = ChartFactory.createBarChart("10 Besar Penyakit Di Rawat Inap","Periode","Jumlah Penyakit", dcd, PlotOrientation.VERTICAL,true, true,true); //String arg0,String arg1,String arg2,Category Datasheet,Plot Orientation,boolean arg4,boolean arg5,boolean arg6
                    ChartFrame cf = new ChartFrame("Frafik 10 Besar Penyakit Di Rawat Inap",freeChart);

                    cf.setSize(internalFrame1.getWidth()-50,internalFrame1.getHeight()-50);
                    cf.setLocationRelativeTo(internalFrame1);
                    cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                    cf.setVisible(true);

                }else if((tbDokter2.getRowCount()>8)&&(Integer.parseInt(tbDokter2.getValueAt(0,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(1,3).toString()))
                        && (Integer.parseInt(tbDokter2.getValueAt(1,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(2,3).toString()))
                        && (Integer.parseInt(tbDokter2.getValueAt(2,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(3,3).toString()))
                        && (Integer.parseInt(tbDokter2.getValueAt(3,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(4,3).toString()))
                        && (Integer.parseInt(tbDokter2.getValueAt(4,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(5,3).toString()))
                        && (Integer.parseInt(tbDokter2.getValueAt(5,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(6,3).toString()))
                        && (Integer.parseInt(tbDokter2.getValueAt(6,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(7,3).toString()))
                        && (Integer.parseInt(tbDokter2.getValueAt(7,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(8,3).toString()))){
                    DefaultCategoryDataset dcd = new DefaultCategoryDataset();
                    dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(0,3).toString()),tbDokter2.getValueAt(0,0).toString()+", "+tbDokter2.getValueAt(0,1).toString()+", "+tbDokter2.getValueAt(0,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(1,3).toString()),tbDokter2.getValueAt(1,0).toString()+", "+tbDokter2.getValueAt(1,1).toString()+", "+tbDokter2.getValueAt(1,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(2,3).toString()),tbDokter2.getValueAt(2,0).toString()+", "+tbDokter2.getValueAt(2,1).toString()+", "+tbDokter2.getValueAt(2,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(3,3).toString()),tbDokter2.getValueAt(3,0).toString()+", "+tbDokter2.getValueAt(3,1).toString()+", "+tbDokter2.getValueAt(3,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(4,3).toString()),tbDokter2.getValueAt(4,0).toString()+", "+tbDokter2.getValueAt(4,1).toString()+", "+tbDokter2.getValueAt(4,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(5,3).toString()),tbDokter2.getValueAt(5,0).toString()+", "+tbDokter2.getValueAt(5,1).toString()+", "+tbDokter2.getValueAt(5,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(6,3).toString()),tbDokter2.getValueAt(6,0).toString()+", "+tbDokter2.getValueAt(6,1).toString()+", "+tbDokter2.getValueAt(6,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(7,3).toString()),tbDokter2.getValueAt(7,0).toString()+", "+tbDokter2.getValueAt(7,1).toString()+", "+tbDokter2.getValueAt(7,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(8,3).toString()),tbDokter2.getValueAt(8,0).toString()+", "+tbDokter2.getValueAt(8,1).toString()+", "+tbDokter2.getValueAt(8,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));

                    JFreeChart freeChart = ChartFactory.createBarChart("10 Besar Penyakit Di Rawat Inap","Periode","Jumlah Penyakit", dcd, PlotOrientation.VERTICAL,true, true,true); //String arg0,String arg1,String arg2,Category Datasheet,Plot Orientation,boolean arg4,boolean arg5,boolean arg6
                    ChartFrame cf = new ChartFrame("Frafik 10 Besar Penyakit Di Rawat Inap",freeChart);

                    cf.setSize(internalFrame1.getWidth()-50,internalFrame1.getHeight()-50);
                    cf.setLocationRelativeTo(internalFrame1);
                    cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                    cf.setVisible(true);

                }else if((tbDokter2.getRowCount()>7)&&(Integer.parseInt(tbDokter2.getValueAt(0,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(1,3).toString()))
                        && (Integer.parseInt(tbDokter2.getValueAt(1,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(2,3).toString()))
                        && (Integer.parseInt(tbDokter2.getValueAt(2,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(3,3).toString()))
                        && (Integer.parseInt(tbDokter2.getValueAt(3,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(4,3).toString()))
                        && (Integer.parseInt(tbDokter2.getValueAt(4,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(5,3).toString()))
                        && (Integer.parseInt(tbDokter2.getValueAt(5,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(6,3).toString()))
                        && (Integer.parseInt(tbDokter2.getValueAt(6,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(7,3).toString()))){
                    DefaultCategoryDataset dcd = new DefaultCategoryDataset();
                    dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(0,3).toString()),tbDokter2.getValueAt(0,0).toString()+", "+tbDokter2.getValueAt(0,1).toString()+", "+tbDokter2.getValueAt(0,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(1,3).toString()),tbDokter2.getValueAt(1,0).toString()+", "+tbDokter2.getValueAt(1,1).toString()+", "+tbDokter2.getValueAt(1,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(2,3).toString()),tbDokter2.getValueAt(2,0).toString()+", "+tbDokter2.getValueAt(2,1).toString()+", "+tbDokter2.getValueAt(2,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(3,3).toString()),tbDokter2.getValueAt(3,0).toString()+", "+tbDokter2.getValueAt(3,1).toString()+", "+tbDokter2.getValueAt(3,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(4,3).toString()),tbDokter2.getValueAt(4,0).toString()+", "+tbDokter2.getValueAt(4,1).toString()+", "+tbDokter2.getValueAt(4,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(5,3).toString()),tbDokter2.getValueAt(5,0).toString()+", "+tbDokter2.getValueAt(5,1).toString()+", "+tbDokter2.getValueAt(5,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(6,3).toString()),tbDokter2.getValueAt(6,0).toString()+", "+tbDokter2.getValueAt(6,1).toString()+", "+tbDokter2.getValueAt(6,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(7,3).toString()),tbDokter2.getValueAt(7,0).toString()+", "+tbDokter2.getValueAt(7,1).toString()+", "+tbDokter2.getValueAt(7,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));

                    JFreeChart freeChart = ChartFactory.createBarChart("10 Besar Penyakit Di Rawat Inap","Periode","Jumlah Penyakit", dcd, PlotOrientation.VERTICAL,true, true,true); //String arg0,String arg1,String arg2,Category Datasheet,Plot Orientation,boolean arg4,boolean arg5,boolean arg6
                    ChartFrame cf = new ChartFrame("Frafik 10 Besar Penyakit Di Rawat Inap",freeChart);

                    cf.setSize(internalFrame1.getWidth()-50,internalFrame1.getHeight()-50);
                    cf.setLocationRelativeTo(internalFrame1);
                    cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                    cf.setVisible(true);

                }else if((tbDokter2.getRowCount()>6)&&(Integer.parseInt(tbDokter2.getValueAt(0,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(1,3).toString()))
                        && (Integer.parseInt(tbDokter2.getValueAt(1,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(2,3).toString()))
                        && (Integer.parseInt(tbDokter2.getValueAt(2,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(3,3).toString()))
                        && (Integer.parseInt(tbDokter2.getValueAt(3,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(4,3).toString()))
                        && (Integer.parseInt(tbDokter2.getValueAt(4,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(5,3).toString()))
                        && (Integer.parseInt(tbDokter2.getValueAt(5,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(6,3).toString()))){
                    DefaultCategoryDataset dcd = new DefaultCategoryDataset();
                    dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(0,3).toString()),tbDokter2.getValueAt(0,0).toString()+", "+tbDokter2.getValueAt(0,1).toString()+", "+tbDokter2.getValueAt(0,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(1,3).toString()),tbDokter2.getValueAt(1,0).toString()+", "+tbDokter2.getValueAt(1,1).toString()+", "+tbDokter2.getValueAt(1,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(2,3).toString()),tbDokter2.getValueAt(2,0).toString()+", "+tbDokter2.getValueAt(2,1).toString()+", "+tbDokter2.getValueAt(2,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(3,3).toString()),tbDokter2.getValueAt(3,0).toString()+", "+tbDokter2.getValueAt(3,1).toString()+", "+tbDokter2.getValueAt(3,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(4,3).toString()),tbDokter2.getValueAt(4,0).toString()+", "+tbDokter2.getValueAt(4,1).toString()+", "+tbDokter2.getValueAt(4,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(5,3).toString()),tbDokter2.getValueAt(5,0).toString()+", "+tbDokter2.getValueAt(5,1).toString()+", "+tbDokter2.getValueAt(5,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(6,3).toString()),tbDokter2.getValueAt(6,0).toString()+", "+tbDokter2.getValueAt(6,1).toString()+", "+tbDokter2.getValueAt(6,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));

                    JFreeChart freeChart = ChartFactory.createBarChart("10 Besar Penyakit Di Rawat Inap","Periode","Jumlah Penyakit", dcd, PlotOrientation.VERTICAL,true, true,true); //String arg0,String arg1,String arg2,Category Datasheet,Plot Orientation,boolean arg4,boolean arg5,boolean arg6
                    ChartFrame cf = new ChartFrame("Frafik 10 Besar Penyakit Di Rawat Inap",freeChart);

                    cf.setSize(internalFrame1.getWidth()-50,internalFrame1.getHeight()-50);
                    cf.setLocationRelativeTo(internalFrame1);
                    cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                    cf.setVisible(true);

                }else if((tbDokter2.getRowCount()>5)&&(Integer.parseInt(tbDokter2.getValueAt(0,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(1,3).toString()))
                        && (Integer.parseInt(tbDokter2.getValueAt(1,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(2,3).toString()))
                        && (Integer.parseInt(tbDokter2.getValueAt(2,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(3,3).toString()))
                        && (Integer.parseInt(tbDokter2.getValueAt(3,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(4,3).toString()))
                        && (Integer.parseInt(tbDokter2.getValueAt(4,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(5,3).toString()))){
                    DefaultCategoryDataset dcd = new DefaultCategoryDataset();
                    dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(0,3).toString()),tbDokter2.getValueAt(0,0).toString()+", "+tbDokter2.getValueAt(0,1).toString()+", "+tbDokter2.getValueAt(0,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(1,3).toString()),tbDokter2.getValueAt(1,0).toString()+", "+tbDokter2.getValueAt(1,1).toString()+", "+tbDokter2.getValueAt(1,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(2,3).toString()),tbDokter2.getValueAt(2,0).toString()+", "+tbDokter2.getValueAt(2,1).toString()+", "+tbDokter2.getValueAt(2,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(3,3).toString()),tbDokter2.getValueAt(3,0).toString()+", "+tbDokter2.getValueAt(3,1).toString()+", "+tbDokter2.getValueAt(3,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(4,3).toString()),tbDokter2.getValueAt(4,0).toString()+", "+tbDokter2.getValueAt(4,1).toString()+", "+tbDokter2.getValueAt(4,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(5,3).toString()),tbDokter2.getValueAt(5,0).toString()+", "+tbDokter2.getValueAt(5,1).toString()+", "+tbDokter2.getValueAt(5,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));

                    JFreeChart freeChart = ChartFactory.createBarChart("10 Besar Penyakit Di Rawat Inap","Periode","Jumlah Penyakit", dcd, PlotOrientation.VERTICAL,true, true,true); //String arg0,String arg1,String arg2,Category Datasheet,Plot Orientation,boolean arg4,boolean arg5,boolean arg6
                    ChartFrame cf = new ChartFrame("Frafik 10 Besar Penyakit Di Rawat Inap",freeChart);

                    cf.setSize(internalFrame1.getWidth()-50,internalFrame1.getHeight()-50);
                    cf.setLocationRelativeTo(internalFrame1);
                    cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                    cf.setVisible(true);

                }else if((tbDokter2.getRowCount()>4)&&(Integer.parseInt(tbDokter2.getValueAt(0,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(1,3).toString()))
                        && (Integer.parseInt(tbDokter2.getValueAt(1,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(2,3).toString()))
                        && (Integer.parseInt(tbDokter2.getValueAt(2,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(3,3).toString()))
                        && (Integer.parseInt(tbDokter2.getValueAt(3,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(4,3).toString()))){
                    DefaultCategoryDataset dcd = new DefaultCategoryDataset();
                    dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(0,3).toString()),tbDokter2.getValueAt(0,0).toString()+", "+tbDokter2.getValueAt(0,1).toString()+", "+tbDokter2.getValueAt(0,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(1,3).toString()),tbDokter2.getValueAt(1,0).toString()+", "+tbDokter2.getValueAt(1,1).toString()+", "+tbDokter2.getValueAt(1,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(2,3).toString()),tbDokter2.getValueAt(2,0).toString()+", "+tbDokter2.getValueAt(2,1).toString()+", "+tbDokter2.getValueAt(2,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(3,3).toString()),tbDokter2.getValueAt(3,0).toString()+", "+tbDokter2.getValueAt(3,1).toString()+", "+tbDokter2.getValueAt(3,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(4,3).toString()),tbDokter2.getValueAt(4,0).toString()+", "+tbDokter2.getValueAt(4,1).toString()+", "+tbDokter2.getValueAt(4,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));

                    JFreeChart freeChart = ChartFactory.createBarChart("10 Besar Penyakit Di Rawat Inap","Periode","Jumlah Penyakit", dcd, PlotOrientation.VERTICAL,true, true,true); //String arg0,String arg1,String arg2,Category Datasheet,Plot Orientation,boolean arg4,boolean arg5,boolean arg6
                    ChartFrame cf = new ChartFrame("Frafik 10 Besar Penyakit Di Rawat Inap",freeChart);

                    cf.setSize(internalFrame1.getWidth()-50,internalFrame1.getHeight()-50);
                    cf.setLocationRelativeTo(internalFrame1);
                    cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                    cf.setVisible(true);

                }else if((tbDokter2.getRowCount()>3)&&(Integer.parseInt(tbDokter2.getValueAt(0,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(1,3).toString()))
                        && (Integer.parseInt(tbDokter2.getValueAt(1,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(2,3).toString()))
                        && (Integer.parseInt(tbDokter2.getValueAt(2,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(3,3).toString()))){
                    DefaultCategoryDataset dcd = new DefaultCategoryDataset();
                    dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(0,3).toString()),tbDokter2.getValueAt(0,0).toString()+", "+tbDokter2.getValueAt(0,1).toString()+", "+tbDokter2.getValueAt(0,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(1,3).toString()),tbDokter2.getValueAt(1,0).toString()+", "+tbDokter2.getValueAt(1,1).toString()+", "+tbDokter2.getValueAt(1,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(2,3).toString()),tbDokter2.getValueAt(2,0).toString()+", "+tbDokter2.getValueAt(2,1).toString()+", "+tbDokter2.getValueAt(2,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(3,3).toString()),tbDokter2.getValueAt(3,0).toString()+", "+tbDokter2.getValueAt(3,1).toString()+", "+tbDokter2.getValueAt(3,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));

                    JFreeChart freeChart = ChartFactory.createBarChart("10 Besar Penyakit Di Rawat Inap","Periode","Jumlah Penyakit", dcd, PlotOrientation.VERTICAL,true, true,true); //String arg0,String arg1,String arg2,Category Datasheet,Plot Orientation,boolean arg4,boolean arg5,boolean arg6
                    ChartFrame cf = new ChartFrame("Frafik 10 Besar Penyakit Di Rawat Inap",freeChart);

                    cf.setSize(internalFrame1.getWidth()-50,internalFrame1.getHeight()-50);
                    cf.setLocationRelativeTo(internalFrame1);
                    cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                    cf.setVisible(true);          

                }else if((tbDokter2.getRowCount()>2)&&(Integer.parseInt(tbDokter2.getValueAt(0,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(1,3).toString()))
                        && (Integer.parseInt(tbDokter2.getValueAt(1,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(2,3).toString()))){
                    DefaultCategoryDataset dcd = new DefaultCategoryDataset();
                    dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(0,3).toString()),tbDokter2.getValueAt(0,0).toString()+", "+tbDokter2.getValueAt(0,1).toString()+", "+tbDokter2.getValueAt(0,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(1,3).toString()),tbDokter2.getValueAt(1,0).toString()+", "+tbDokter2.getValueAt(1,1).toString()+", "+tbDokter2.getValueAt(1,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(2,3).toString()),tbDokter2.getValueAt(2,0).toString()+", "+tbDokter2.getValueAt(2,1).toString()+", "+tbDokter2.getValueAt(2,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));

                    JFreeChart freeChart = ChartFactory.createBarChart("10 Besar Penyakit Di Rawat Inap","Periode","Jumlah Penyakit", dcd, PlotOrientation.VERTICAL,true, true,true); //String arg0,String arg1,String arg2,Category Datasheet,Plot Orientation,boolean arg4,boolean arg5,boolean arg6
                    ChartFrame cf = new ChartFrame("Frafik 10 Besar Penyakit Di Rawat Inap",freeChart);

                    cf.setSize(internalFrame1.getWidth()-50,internalFrame1.getHeight()-50);
                    cf.setLocationRelativeTo(internalFrame1);
                    cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                    cf.setVisible(true);           

                }else if((tbDokter2.getRowCount()>1)&&(Integer.parseInt(tbDokter2.getValueAt(0,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(1,3).toString()))){
                    DefaultCategoryDataset dcd = new DefaultCategoryDataset();
                    dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(0,3).toString()),tbDokter2.getValueAt(0,0).toString()+", "+tbDokter2.getValueAt(0,1).toString()+", "+tbDokter2.getValueAt(0,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    dcd.setValue(Integer.parseInt(tbDokter2.getValueAt(1,3).toString()),tbDokter2.getValueAt(1,0).toString()+", "+tbDokter2.getValueAt(1,1).toString()+", "+tbDokter2.getValueAt(1,3).toString(),Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""));

                    JFreeChart freeChart = ChartFactory.createBarChart("10 Besar Penyakit Di Rawat Inap","Periode","Jumlah Penyakit", dcd, PlotOrientation.VERTICAL,true, true,true); //String arg0,String arg1,String arg2,Category Datasheet,Plot Orientation,boolean arg4,boolean arg5,boolean arg6
                    ChartFrame cf = new ChartFrame("Frafik 10 Besar Penyakit Di Rawat Inap",freeChart);

                    cf.setSize(internalFrame1.getWidth()-50,internalFrame1.getHeight()-50);
                    cf.setLocationRelativeTo(internalFrame1);
                    cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                    cf.setVisible(true);            

                }else{
                    JOptionPane.showMessageDialog(null,"Maaf, Silahkan urutkan secara Descanding/Besar Ke Kecil dengan menklik pada kolom jumlah..!!!!");
                }
        }
    } else {
        JOptionPane.showMessageDialog(null, "Untuk mencetak grafik batang 10 penyakit tersedikit, silakan koordinasi dg. Inst. Rekam Medik...!!!");
        Tgl1.requestFocus();
    }           
}//GEN-LAST:event_ppGrafikTerkecilBatangActionPerformed

private void ppGrafikTerkecilPieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikTerkecilPieActionPerformed
    if (akses.getkode().equals("Admin Utama") || akses.getpenyakit() == true) {
    if(TabRawat.getSelectedIndex()==0){
            if((tbDokter.getRowCount()>9)&&(Integer.parseInt(tbDokter.getValueAt(0,3).toString())<=Integer.parseInt(tbDokter.getValueAt(1,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(1,3).toString())<=Integer.parseInt(tbDokter.getValueAt(2,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(2,3).toString())<=Integer.parseInt(tbDokter.getValueAt(3,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(3,3).toString())<=Integer.parseInt(tbDokter.getValueAt(4,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(4,3).toString())<=Integer.parseInt(tbDokter.getValueAt(5,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(5,3).toString())<=Integer.parseInt(tbDokter.getValueAt(6,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(6,3).toString())<=Integer.parseInt(tbDokter.getValueAt(7,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(7,3).toString())<=Integer.parseInt(tbDokter.getValueAt(8,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(8,3).toString())<=Integer.parseInt(tbDokter.getValueAt(9,3).toString()))){
                DefaultPieDataset dpd = new DefaultPieDataset();
                dpd.setValue(tbDokter.getValueAt(0,0).toString()+", "+tbDokter.getValueAt(0,1).toString()+", "+tbDokter.getValueAt(0,3).toString(),Integer.parseInt(tbDokter.getValueAt(0,3).toString()));
                dpd.setValue(tbDokter.getValueAt(1,0).toString()+", "+tbDokter.getValueAt(1,1).toString()+", "+tbDokter.getValueAt(1,3).toString(),Integer.parseInt(tbDokter.getValueAt(1,3).toString()));
                dpd.setValue(tbDokter.getValueAt(2,0).toString()+", "+tbDokter.getValueAt(2,1).toString()+", "+tbDokter.getValueAt(2,3).toString(),Integer.parseInt(tbDokter.getValueAt(2,3).toString()));
                dpd.setValue(tbDokter.getValueAt(3,0).toString()+", "+tbDokter.getValueAt(3,1).toString()+", "+tbDokter.getValueAt(3,3).toString(),Integer.parseInt(tbDokter.getValueAt(3,3).toString()));
                dpd.setValue(tbDokter.getValueAt(4,0).toString()+", "+tbDokter.getValueAt(4,1).toString()+", "+tbDokter.getValueAt(4,3).toString(),Integer.parseInt(tbDokter.getValueAt(4,3).toString()));
                dpd.setValue(tbDokter.getValueAt(5,0).toString()+", "+tbDokter.getValueAt(5,1).toString()+", "+tbDokter.getValueAt(5,3).toString(),Integer.parseInt(tbDokter.getValueAt(5,3).toString()));
                dpd.setValue(tbDokter.getValueAt(6,0).toString()+", "+tbDokter.getValueAt(6,1).toString()+", "+tbDokter.getValueAt(6,3).toString(),Integer.parseInt(tbDokter.getValueAt(6,3).toString()));
                dpd.setValue(tbDokter.getValueAt(7,0).toString()+", "+tbDokter.getValueAt(7,1).toString()+", "+tbDokter.getValueAt(7,3).toString(),Integer.parseInt(tbDokter.getValueAt(7,3).toString()));
                dpd.setValue(tbDokter.getValueAt(8,0).toString()+", "+tbDokter.getValueAt(8,1).toString()+", "+tbDokter.getValueAt(8,3).toString(),Integer.parseInt(tbDokter.getValueAt(8,3).toString()));
                dpd.setValue(tbDokter.getValueAt(9,0).toString()+", "+tbDokter.getValueAt(9,1).toString()+", "+tbDokter.getValueAt(9,3).toString(),Integer.parseInt(tbDokter.getValueAt(9,3).toString()));

                JFreeChart freeChart = ChartFactory.createPieChart("10 Besar Penyakit Di Rawat Inap Periode "+Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""),dpd,true,true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url 
                ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Inap ",freeChart);
                cf.setSize(internalFrame1.getWidth()-50, internalFrame1.getHeight()-50);
                cf.setLocationRelativeTo(internalFrame1);
                cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                cf.setVisible(true);
            }else if((tbDokter.getRowCount()>8)&&(Integer.parseInt(tbDokter.getValueAt(0,3).toString())<=Integer.parseInt(tbDokter.getValueAt(1,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(1,3).toString())<=Integer.parseInt(tbDokter.getValueAt(2,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(2,3).toString())<=Integer.parseInt(tbDokter.getValueAt(3,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(3,3).toString())<=Integer.parseInt(tbDokter.getValueAt(4,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(4,3).toString())<=Integer.parseInt(tbDokter.getValueAt(5,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(5,3).toString())<=Integer.parseInt(tbDokter.getValueAt(6,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(6,3).toString())<=Integer.parseInt(tbDokter.getValueAt(7,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(7,3).toString())<=Integer.parseInt(tbDokter.getValueAt(8,3).toString()))){
                DefaultPieDataset dpd = new DefaultPieDataset();
                dpd.setValue(tbDokter.getValueAt(0,0).toString()+", "+tbDokter.getValueAt(0,1).toString()+", "+tbDokter.getValueAt(0,3).toString(),Integer.parseInt(tbDokter.getValueAt(0,3).toString()));
                dpd.setValue(tbDokter.getValueAt(1,0).toString()+", "+tbDokter.getValueAt(1,1).toString()+", "+tbDokter.getValueAt(1,3).toString(),Integer.parseInt(tbDokter.getValueAt(1,3).toString()));
                dpd.setValue(tbDokter.getValueAt(2,0).toString()+", "+tbDokter.getValueAt(2,1).toString()+", "+tbDokter.getValueAt(2,3).toString(),Integer.parseInt(tbDokter.getValueAt(2,3).toString()));
                dpd.setValue(tbDokter.getValueAt(3,0).toString()+", "+tbDokter.getValueAt(3,1).toString()+", "+tbDokter.getValueAt(3,3).toString(),Integer.parseInt(tbDokter.getValueAt(3,3).toString()));
                dpd.setValue(tbDokter.getValueAt(4,0).toString()+", "+tbDokter.getValueAt(4,1).toString()+", "+tbDokter.getValueAt(4,3).toString(),Integer.parseInt(tbDokter.getValueAt(4,3).toString()));
                dpd.setValue(tbDokter.getValueAt(5,0).toString()+", "+tbDokter.getValueAt(5,1).toString()+", "+tbDokter.getValueAt(5,3).toString(),Integer.parseInt(tbDokter.getValueAt(5,3).toString()));
                dpd.setValue(tbDokter.getValueAt(6,0).toString()+", "+tbDokter.getValueAt(6,1).toString()+", "+tbDokter.getValueAt(6,3).toString(),Integer.parseInt(tbDokter.getValueAt(6,3).toString()));
                dpd.setValue(tbDokter.getValueAt(7,0).toString()+", "+tbDokter.getValueAt(7,1).toString()+", "+tbDokter.getValueAt(7,3).toString(),Integer.parseInt(tbDokter.getValueAt(7,3).toString()));
                dpd.setValue(tbDokter.getValueAt(8,0).toString()+", "+tbDokter.getValueAt(8,1).toString()+", "+tbDokter.getValueAt(8,3).toString(),Integer.parseInt(tbDokter.getValueAt(8,3).toString()));

                JFreeChart freeChart = ChartFactory.createPieChart("10 Besar Penyakit Di Rawat Inap Periode "+Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""),dpd,true,true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url 
                ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Inap ",freeChart);
                cf.setSize(internalFrame1.getWidth()-50, internalFrame1.getHeight()-50);
                cf.setLocationRelativeTo(internalFrame1);
                cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                cf.setVisible(true);

            }else if((tbDokter.getRowCount()>7)&&(Integer.parseInt(tbDokter.getValueAt(0,3).toString())<=Integer.parseInt(tbDokter.getValueAt(1,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(1,3).toString())<=Integer.parseInt(tbDokter.getValueAt(2,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(2,3).toString())<=Integer.parseInt(tbDokter.getValueAt(3,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(3,3).toString())<=Integer.parseInt(tbDokter.getValueAt(4,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(4,3).toString())<=Integer.parseInt(tbDokter.getValueAt(5,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(5,3).toString())<=Integer.parseInt(tbDokter.getValueAt(6,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(6,3).toString())<=Integer.parseInt(tbDokter.getValueAt(7,3).toString()))){
                DefaultPieDataset dpd = new DefaultPieDataset();
                dpd.setValue(tbDokter.getValueAt(0,0).toString()+", "+tbDokter.getValueAt(0,1).toString()+", "+tbDokter.getValueAt(0,3).toString(),Integer.parseInt(tbDokter.getValueAt(0,3).toString()));
                dpd.setValue(tbDokter.getValueAt(1,0).toString()+", "+tbDokter.getValueAt(1,1).toString()+", "+tbDokter.getValueAt(1,3).toString(),Integer.parseInt(tbDokter.getValueAt(1,3).toString()));
                dpd.setValue(tbDokter.getValueAt(2,0).toString()+", "+tbDokter.getValueAt(2,1).toString()+", "+tbDokter.getValueAt(2,3).toString(),Integer.parseInt(tbDokter.getValueAt(2,3).toString()));
                dpd.setValue(tbDokter.getValueAt(3,0).toString()+", "+tbDokter.getValueAt(3,1).toString()+", "+tbDokter.getValueAt(3,3).toString(),Integer.parseInt(tbDokter.getValueAt(3,3).toString()));
                dpd.setValue(tbDokter.getValueAt(4,0).toString()+", "+tbDokter.getValueAt(4,1).toString()+", "+tbDokter.getValueAt(4,3).toString(),Integer.parseInt(tbDokter.getValueAt(4,3).toString()));
                dpd.setValue(tbDokter.getValueAt(5,0).toString()+", "+tbDokter.getValueAt(5,1).toString()+", "+tbDokter.getValueAt(5,3).toString(),Integer.parseInt(tbDokter.getValueAt(5,3).toString()));
                dpd.setValue(tbDokter.getValueAt(6,0).toString()+", "+tbDokter.getValueAt(6,1).toString()+", "+tbDokter.getValueAt(6,3).toString(),Integer.parseInt(tbDokter.getValueAt(6,3).toString()));
                dpd.setValue(tbDokter.getValueAt(7,0).toString()+", "+tbDokter.getValueAt(7,1).toString()+", "+tbDokter.getValueAt(7,3).toString(),Integer.parseInt(tbDokter.getValueAt(7,3).toString()));

                JFreeChart freeChart = ChartFactory.createPieChart("10 Besar Penyakit Di Rawat Inap Periode "+Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""),dpd,true,true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url 
                ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Inap ",freeChart);
                cf.setSize(internalFrame1.getWidth()-50, internalFrame1.getHeight()-50);
                cf.setLocationRelativeTo(internalFrame1);
                cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                cf.setVisible(true);

            }else if((tbDokter.getRowCount()>6)&&(Integer.parseInt(tbDokter.getValueAt(0,3).toString())<=Integer.parseInt(tbDokter.getValueAt(1,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(1,3).toString())<=Integer.parseInt(tbDokter.getValueAt(2,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(2,3).toString())<=Integer.parseInt(tbDokter.getValueAt(3,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(3,3).toString())<=Integer.parseInt(tbDokter.getValueAt(4,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(4,3).toString())<=Integer.parseInt(tbDokter.getValueAt(5,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(5,3).toString())<=Integer.parseInt(tbDokter.getValueAt(6,3).toString()))){
                DefaultPieDataset dpd = new DefaultPieDataset();
                dpd.setValue(tbDokter.getValueAt(0,0).toString()+", "+tbDokter.getValueAt(0,1).toString()+", "+tbDokter.getValueAt(0,3).toString(),Integer.parseInt(tbDokter.getValueAt(0,3).toString()));
                dpd.setValue(tbDokter.getValueAt(1,0).toString()+", "+tbDokter.getValueAt(1,1).toString()+", "+tbDokter.getValueAt(1,3).toString(),Integer.parseInt(tbDokter.getValueAt(1,3).toString()));
                dpd.setValue(tbDokter.getValueAt(2,0).toString()+", "+tbDokter.getValueAt(2,1).toString()+", "+tbDokter.getValueAt(2,3).toString(),Integer.parseInt(tbDokter.getValueAt(2,3).toString()));
                dpd.setValue(tbDokter.getValueAt(3,0).toString()+", "+tbDokter.getValueAt(3,1).toString()+", "+tbDokter.getValueAt(3,3).toString(),Integer.parseInt(tbDokter.getValueAt(3,3).toString()));
                dpd.setValue(tbDokter.getValueAt(4,0).toString()+", "+tbDokter.getValueAt(4,1).toString()+", "+tbDokter.getValueAt(4,3).toString(),Integer.parseInt(tbDokter.getValueAt(4,3).toString()));
                dpd.setValue(tbDokter.getValueAt(5,0).toString()+", "+tbDokter.getValueAt(5,1).toString()+", "+tbDokter.getValueAt(5,3).toString(),Integer.parseInt(tbDokter.getValueAt(5,3).toString()));
                dpd.setValue(tbDokter.getValueAt(6,0).toString()+", "+tbDokter.getValueAt(6,1).toString()+", "+tbDokter.getValueAt(6,3).toString(),Integer.parseInt(tbDokter.getValueAt(6,3).toString()));

                JFreeChart freeChart = ChartFactory.createPieChart("10 Besar Penyakit Di Rawat Inap Periode "+Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""),dpd,true,true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url 
                ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Inap ",freeChart);
                cf.setSize(internalFrame1.getWidth()-50, internalFrame1.getHeight()-50);
                cf.setLocationRelativeTo(internalFrame1);
                cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                cf.setVisible(true);

            }else if((tbDokter.getRowCount()>5)&&(Integer.parseInt(tbDokter.getValueAt(0,3).toString())<=Integer.parseInt(tbDokter.getValueAt(1,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(1,3).toString())<=Integer.parseInt(tbDokter.getValueAt(2,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(2,3).toString())<=Integer.parseInt(tbDokter.getValueAt(3,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(3,3).toString())<=Integer.parseInt(tbDokter.getValueAt(4,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(4,3).toString())<=Integer.parseInt(tbDokter.getValueAt(5,3).toString()))){
                DefaultPieDataset dpd = new DefaultPieDataset();
                dpd.setValue(tbDokter.getValueAt(0,0).toString()+", "+tbDokter.getValueAt(0,1).toString()+", "+tbDokter.getValueAt(0,3).toString(),Integer.parseInt(tbDokter.getValueAt(0,3).toString()));
                dpd.setValue(tbDokter.getValueAt(1,0).toString()+", "+tbDokter.getValueAt(1,1).toString()+", "+tbDokter.getValueAt(1,3).toString(),Integer.parseInt(tbDokter.getValueAt(1,3).toString()));
                dpd.setValue(tbDokter.getValueAt(2,0).toString()+", "+tbDokter.getValueAt(2,1).toString()+", "+tbDokter.getValueAt(2,3).toString(),Integer.parseInt(tbDokter.getValueAt(2,3).toString()));
                dpd.setValue(tbDokter.getValueAt(3,0).toString()+", "+tbDokter.getValueAt(3,1).toString()+", "+tbDokter.getValueAt(3,3).toString(),Integer.parseInt(tbDokter.getValueAt(3,3).toString()));
                dpd.setValue(tbDokter.getValueAt(4,0).toString()+", "+tbDokter.getValueAt(4,1).toString()+", "+tbDokter.getValueAt(4,3).toString(),Integer.parseInt(tbDokter.getValueAt(4,3).toString()));
                dpd.setValue(tbDokter.getValueAt(5,0).toString()+", "+tbDokter.getValueAt(5,1).toString()+", "+tbDokter.getValueAt(5,3).toString(),Integer.parseInt(tbDokter.getValueAt(5,3).toString()));

                JFreeChart freeChart = ChartFactory.createPieChart("10 Besar Penyakit Di Rawat Inap Periode "+Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""),dpd,true,true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url 
                ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Inap ",freeChart);
                cf.setSize(internalFrame1.getWidth()-50, internalFrame1.getHeight()-50);
                cf.setLocationRelativeTo(internalFrame1);
                cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                cf.setVisible(true);

            }else if((tbDokter.getRowCount()>4)&&(Integer.parseInt(tbDokter.getValueAt(0,3).toString())<=Integer.parseInt(tbDokter.getValueAt(1,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(1,3).toString())<=Integer.parseInt(tbDokter.getValueAt(2,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(2,3).toString())<=Integer.parseInt(tbDokter.getValueAt(3,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(3,3).toString())<=Integer.parseInt(tbDokter.getValueAt(4,3).toString()))){
                DefaultPieDataset dpd = new DefaultPieDataset();
                dpd.setValue(tbDokter.getValueAt(0,0).toString()+", "+tbDokter.getValueAt(0,1).toString()+", "+tbDokter.getValueAt(0,3).toString(),Integer.parseInt(tbDokter.getValueAt(0,3).toString()));
                dpd.setValue(tbDokter.getValueAt(1,0).toString()+", "+tbDokter.getValueAt(1,1).toString()+", "+tbDokter.getValueAt(1,3).toString(),Integer.parseInt(tbDokter.getValueAt(1,3).toString()));
                dpd.setValue(tbDokter.getValueAt(2,0).toString()+", "+tbDokter.getValueAt(2,1).toString()+", "+tbDokter.getValueAt(2,3).toString(),Integer.parseInt(tbDokter.getValueAt(2,3).toString()));
                dpd.setValue(tbDokter.getValueAt(3,0).toString()+", "+tbDokter.getValueAt(3,1).toString()+", "+tbDokter.getValueAt(3,3).toString(),Integer.parseInt(tbDokter.getValueAt(3,3).toString()));
                dpd.setValue(tbDokter.getValueAt(4,0).toString()+", "+tbDokter.getValueAt(4,1).toString()+", "+tbDokter.getValueAt(4,3).toString(),Integer.parseInt(tbDokter.getValueAt(4,3).toString()));

                JFreeChart freeChart = ChartFactory.createPieChart("10 Besar Penyakit Di Rawat Inap Periode "+Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""),dpd,true,true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url 
                ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Inap ",freeChart);
                cf.setSize(internalFrame1.getWidth()-50, internalFrame1.getHeight()-50);
                cf.setLocationRelativeTo(internalFrame1);
                cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                cf.setVisible(true);

            }else if((tbDokter.getRowCount()>3)&&(Integer.parseInt(tbDokter.getValueAt(0,3).toString())<=Integer.parseInt(tbDokter.getValueAt(1,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(1,3).toString())<=Integer.parseInt(tbDokter.getValueAt(2,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(2,3).toString())<=Integer.parseInt(tbDokter.getValueAt(3,3).toString()))){
                DefaultPieDataset dpd = new DefaultPieDataset();
                dpd.setValue(tbDokter.getValueAt(0,0).toString()+", "+tbDokter.getValueAt(0,1).toString()+", "+tbDokter.getValueAt(0,3).toString(),Integer.parseInt(tbDokter.getValueAt(0,3).toString()));
                dpd.setValue(tbDokter.getValueAt(1,0).toString()+", "+tbDokter.getValueAt(1,1).toString()+", "+tbDokter.getValueAt(1,3).toString(),Integer.parseInt(tbDokter.getValueAt(1,3).toString()));
                dpd.setValue(tbDokter.getValueAt(2,0).toString()+", "+tbDokter.getValueAt(2,1).toString()+", "+tbDokter.getValueAt(2,3).toString(),Integer.parseInt(tbDokter.getValueAt(2,3).toString()));
                dpd.setValue(tbDokter.getValueAt(3,0).toString()+", "+tbDokter.getValueAt(3,1).toString()+", "+tbDokter.getValueAt(3,3).toString(),Integer.parseInt(tbDokter.getValueAt(3,3).toString()));

                JFreeChart freeChart = ChartFactory.createPieChart("10 Besar Penyakit Di Rawat Inap Periode "+Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""),dpd,true,true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url 
                ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Inap ",freeChart);
                cf.setSize(internalFrame1.getWidth()-50, internalFrame1.getHeight()-50);
                cf.setLocationRelativeTo(internalFrame1);
                cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                cf.setVisible(true);      

            }else if((tbDokter.getRowCount()>2)&&(Integer.parseInt(tbDokter.getValueAt(0,3).toString())<=Integer.parseInt(tbDokter.getValueAt(1,3).toString()))
                    && (Integer.parseInt(tbDokter.getValueAt(1,3).toString())<=Integer.parseInt(tbDokter.getValueAt(2,3).toString()))){
                DefaultPieDataset dpd = new DefaultPieDataset();
                dpd.setValue(tbDokter.getValueAt(0,0).toString()+", "+tbDokter.getValueAt(0,1).toString()+", "+tbDokter.getValueAt(0,3).toString(),Integer.parseInt(tbDokter.getValueAt(0,3).toString()));
                dpd.setValue(tbDokter.getValueAt(1,0).toString()+", "+tbDokter.getValueAt(1,1).toString()+", "+tbDokter.getValueAt(1,3).toString(),Integer.parseInt(tbDokter.getValueAt(1,3).toString()));
                dpd.setValue(tbDokter.getValueAt(2,0).toString()+", "+tbDokter.getValueAt(2,1).toString()+", "+tbDokter.getValueAt(2,3).toString(),Integer.parseInt(tbDokter.getValueAt(2,3).toString()));

                JFreeChart freeChart = ChartFactory.createPieChart("10 Besar Penyakit Di Rawat Inap Periode "+Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""),dpd,true,true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url 
                ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Inap ",freeChart);
                cf.setSize(internalFrame1.getWidth()-50, internalFrame1.getHeight()-50);
                cf.setLocationRelativeTo(internalFrame1);
                cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                cf.setVisible(true);          

            }else if((tbDokter.getRowCount()>1)&&(Integer.parseInt(tbDokter.getValueAt(0,3).toString())<=Integer.parseInt(tbDokter.getValueAt(1,3).toString()))){
                DefaultPieDataset dpd = new DefaultPieDataset();
                dpd.setValue(tbDokter.getValueAt(0,0).toString()+", "+tbDokter.getValueAt(0,1).toString()+", "+tbDokter.getValueAt(0,3).toString(),Integer.parseInt(tbDokter.getValueAt(0,3).toString()));
                dpd.setValue(tbDokter.getValueAt(1,0).toString()+", "+tbDokter.getValueAt(1,1).toString()+", "+tbDokter.getValueAt(1,3).toString(),Integer.parseInt(tbDokter.getValueAt(1,3).toString()));

                JFreeChart freeChart = ChartFactory.createPieChart("10 Besar Penyakit Di Rawat Inap Periode "+Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""),dpd,true,true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url 
                ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Inap ",freeChart);
                cf.setSize(internalFrame1.getWidth()-50, internalFrame1.getHeight()-50);
                cf.setLocationRelativeTo(internalFrame1);
                cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                cf.setVisible(true);         

            }else{
                JOptionPane.showMessageDialog(null,"Maaf, Silahkan urutkan secara Descanding/Besar Ke Kecil dengan menklik pada kolom jumlah..!!!!");
            }
        }else if(TabRawat.getSelectedIndex()==1){
            if((tbDokter2.getRowCount()>9)&&(Integer.parseInt(tbDokter2.getValueAt(0,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(1,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(1,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(2,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(2,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(3,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(3,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(4,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(4,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(5,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(5,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(6,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(6,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(7,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(7,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(8,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(8,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(9,3).toString()))){
                DefaultPieDataset dpd = new DefaultPieDataset();
                dpd.setValue(tbDokter2.getValueAt(0,0).toString()+", "+tbDokter2.getValueAt(0,1).toString()+", "+tbDokter2.getValueAt(0,3).toString(),Integer.parseInt(tbDokter2.getValueAt(0,3).toString()));
                dpd.setValue(tbDokter2.getValueAt(1,0).toString()+", "+tbDokter2.getValueAt(1,1).toString()+", "+tbDokter2.getValueAt(1,3).toString(),Integer.parseInt(tbDokter2.getValueAt(1,3).toString()));
                dpd.setValue(tbDokter2.getValueAt(2,0).toString()+", "+tbDokter2.getValueAt(2,1).toString()+", "+tbDokter2.getValueAt(2,3).toString(),Integer.parseInt(tbDokter2.getValueAt(2,3).toString()));
                dpd.setValue(tbDokter2.getValueAt(3,0).toString()+", "+tbDokter2.getValueAt(3,1).toString()+", "+tbDokter2.getValueAt(3,3).toString(),Integer.parseInt(tbDokter2.getValueAt(3,3).toString()));
                dpd.setValue(tbDokter2.getValueAt(4,0).toString()+", "+tbDokter2.getValueAt(4,1).toString()+", "+tbDokter2.getValueAt(4,3).toString(),Integer.parseInt(tbDokter2.getValueAt(4,3).toString()));
                dpd.setValue(tbDokter2.getValueAt(5,0).toString()+", "+tbDokter2.getValueAt(5,1).toString()+", "+tbDokter2.getValueAt(5,3).toString(),Integer.parseInt(tbDokter2.getValueAt(5,3).toString()));
                dpd.setValue(tbDokter2.getValueAt(6,0).toString()+", "+tbDokter2.getValueAt(6,1).toString()+", "+tbDokter2.getValueAt(6,3).toString(),Integer.parseInt(tbDokter2.getValueAt(6,3).toString()));
                dpd.setValue(tbDokter2.getValueAt(7,0).toString()+", "+tbDokter2.getValueAt(7,1).toString()+", "+tbDokter2.getValueAt(7,3).toString(),Integer.parseInt(tbDokter2.getValueAt(7,3).toString()));
                dpd.setValue(tbDokter2.getValueAt(8,0).toString()+", "+tbDokter2.getValueAt(8,1).toString()+", "+tbDokter2.getValueAt(8,3).toString(),Integer.parseInt(tbDokter2.getValueAt(8,3).toString()));
                dpd.setValue(tbDokter2.getValueAt(9,0).toString()+", "+tbDokter2.getValueAt(9,1).toString()+", "+tbDokter2.getValueAt(9,3).toString(),Integer.parseInt(tbDokter2.getValueAt(9,3).toString()));

                JFreeChart freeChart = ChartFactory.createPieChart("10 Besar Penyakit Di Rawat Inap Periode "+Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""),dpd,true,true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url 
                ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Inap ",freeChart);
                cf.setSize(internalFrame1.getWidth()-50, internalFrame1.getHeight()-50);
                cf.setLocationRelativeTo(internalFrame1);
                cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                cf.setVisible(true);
            }else if((tbDokter2.getRowCount()>8)&&(Integer.parseInt(tbDokter2.getValueAt(0,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(1,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(1,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(2,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(2,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(3,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(3,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(4,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(4,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(5,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(5,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(6,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(6,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(7,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(7,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(8,3).toString()))){
                DefaultPieDataset dpd = new DefaultPieDataset();
                dpd.setValue(tbDokter2.getValueAt(0,0).toString()+", "+tbDokter2.getValueAt(0,1).toString()+", "+tbDokter2.getValueAt(0,3).toString(),Integer.parseInt(tbDokter2.getValueAt(0,3).toString()));
                dpd.setValue(tbDokter2.getValueAt(1,0).toString()+", "+tbDokter2.getValueAt(1,1).toString()+", "+tbDokter2.getValueAt(1,3).toString(),Integer.parseInt(tbDokter2.getValueAt(1,3).toString()));
                dpd.setValue(tbDokter2.getValueAt(2,0).toString()+", "+tbDokter2.getValueAt(2,1).toString()+", "+tbDokter2.getValueAt(2,3).toString(),Integer.parseInt(tbDokter2.getValueAt(2,3).toString()));
                dpd.setValue(tbDokter2.getValueAt(3,0).toString()+", "+tbDokter2.getValueAt(3,1).toString()+", "+tbDokter2.getValueAt(3,3).toString(),Integer.parseInt(tbDokter2.getValueAt(3,3).toString()));
                dpd.setValue(tbDokter2.getValueAt(4,0).toString()+", "+tbDokter2.getValueAt(4,1).toString()+", "+tbDokter2.getValueAt(4,3).toString(),Integer.parseInt(tbDokter2.getValueAt(4,3).toString()));
                dpd.setValue(tbDokter2.getValueAt(5,0).toString()+", "+tbDokter2.getValueAt(5,1).toString()+", "+tbDokter2.getValueAt(5,3).toString(),Integer.parseInt(tbDokter2.getValueAt(5,3).toString()));
                dpd.setValue(tbDokter2.getValueAt(6,0).toString()+", "+tbDokter2.getValueAt(6,1).toString()+", "+tbDokter2.getValueAt(6,3).toString(),Integer.parseInt(tbDokter2.getValueAt(6,3).toString()));
                dpd.setValue(tbDokter2.getValueAt(7,0).toString()+", "+tbDokter2.getValueAt(7,1).toString()+", "+tbDokter2.getValueAt(7,3).toString(),Integer.parseInt(tbDokter2.getValueAt(7,3).toString()));
                dpd.setValue(tbDokter2.getValueAt(8,0).toString()+", "+tbDokter2.getValueAt(8,1).toString()+", "+tbDokter2.getValueAt(8,3).toString(),Integer.parseInt(tbDokter2.getValueAt(8,3).toString()));

                JFreeChart freeChart = ChartFactory.createPieChart("10 Besar Penyakit Di Rawat Inap Periode "+Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""),dpd,true,true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url 
                ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Inap ",freeChart);
                cf.setSize(internalFrame1.getWidth()-50, internalFrame1.getHeight()-50);
                cf.setLocationRelativeTo(internalFrame1);
                cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                cf.setVisible(true);

            }else if((tbDokter2.getRowCount()>7)&&(Integer.parseInt(tbDokter2.getValueAt(0,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(1,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(1,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(2,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(2,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(3,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(3,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(4,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(4,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(5,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(5,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(6,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(6,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(7,3).toString()))){
                DefaultPieDataset dpd = new DefaultPieDataset();
                dpd.setValue(tbDokter2.getValueAt(0,0).toString()+", "+tbDokter2.getValueAt(0,1).toString()+", "+tbDokter2.getValueAt(0,3).toString(),Integer.parseInt(tbDokter2.getValueAt(0,3).toString()));
                dpd.setValue(tbDokter2.getValueAt(1,0).toString()+", "+tbDokter2.getValueAt(1,1).toString()+", "+tbDokter2.getValueAt(1,3).toString(),Integer.parseInt(tbDokter2.getValueAt(1,3).toString()));
                dpd.setValue(tbDokter2.getValueAt(2,0).toString()+", "+tbDokter2.getValueAt(2,1).toString()+", "+tbDokter2.getValueAt(2,3).toString(),Integer.parseInt(tbDokter2.getValueAt(2,3).toString()));
                dpd.setValue(tbDokter2.getValueAt(3,0).toString()+", "+tbDokter2.getValueAt(3,1).toString()+", "+tbDokter2.getValueAt(3,3).toString(),Integer.parseInt(tbDokter2.getValueAt(3,3).toString()));
                dpd.setValue(tbDokter2.getValueAt(4,0).toString()+", "+tbDokter2.getValueAt(4,1).toString()+", "+tbDokter2.getValueAt(4,3).toString(),Integer.parseInt(tbDokter2.getValueAt(4,3).toString()));
                dpd.setValue(tbDokter2.getValueAt(5,0).toString()+", "+tbDokter2.getValueAt(5,1).toString()+", "+tbDokter2.getValueAt(5,3).toString(),Integer.parseInt(tbDokter2.getValueAt(5,3).toString()));
                dpd.setValue(tbDokter2.getValueAt(6,0).toString()+", "+tbDokter2.getValueAt(6,1).toString()+", "+tbDokter2.getValueAt(6,3).toString(),Integer.parseInt(tbDokter2.getValueAt(6,3).toString()));
                dpd.setValue(tbDokter2.getValueAt(7,0).toString()+", "+tbDokter2.getValueAt(7,1).toString()+", "+tbDokter2.getValueAt(7,3).toString(),Integer.parseInt(tbDokter2.getValueAt(7,3).toString()));

                JFreeChart freeChart = ChartFactory.createPieChart("10 Besar Penyakit Di Rawat Inap Periode "+Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""),dpd,true,true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url 
                ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Inap ",freeChart);
                cf.setSize(internalFrame1.getWidth()-50, internalFrame1.getHeight()-50);
                cf.setLocationRelativeTo(internalFrame1);
                cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                cf.setVisible(true);

            }else if((tbDokter2.getRowCount()>6)&&(Integer.parseInt(tbDokter2.getValueAt(0,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(1,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(1,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(2,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(2,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(3,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(3,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(4,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(4,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(5,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(5,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(6,3).toString()))){
                DefaultPieDataset dpd = new DefaultPieDataset();
                dpd.setValue(tbDokter2.getValueAt(0,0).toString()+", "+tbDokter2.getValueAt(0,1).toString()+", "+tbDokter2.getValueAt(0,3).toString(),Integer.parseInt(tbDokter2.getValueAt(0,3).toString()));
                dpd.setValue(tbDokter2.getValueAt(1,0).toString()+", "+tbDokter2.getValueAt(1,1).toString()+", "+tbDokter2.getValueAt(1,3).toString(),Integer.parseInt(tbDokter2.getValueAt(1,3).toString()));
                dpd.setValue(tbDokter2.getValueAt(2,0).toString()+", "+tbDokter2.getValueAt(2,1).toString()+", "+tbDokter2.getValueAt(2,3).toString(),Integer.parseInt(tbDokter2.getValueAt(2,3).toString()));
                dpd.setValue(tbDokter2.getValueAt(3,0).toString()+", "+tbDokter2.getValueAt(3,1).toString()+", "+tbDokter2.getValueAt(3,3).toString(),Integer.parseInt(tbDokter2.getValueAt(3,3).toString()));
                dpd.setValue(tbDokter2.getValueAt(4,0).toString()+", "+tbDokter2.getValueAt(4,1).toString()+", "+tbDokter2.getValueAt(4,3).toString(),Integer.parseInt(tbDokter2.getValueAt(4,3).toString()));
                dpd.setValue(tbDokter2.getValueAt(5,0).toString()+", "+tbDokter2.getValueAt(5,1).toString()+", "+tbDokter2.getValueAt(5,3).toString(),Integer.parseInt(tbDokter2.getValueAt(5,3).toString()));
                dpd.setValue(tbDokter2.getValueAt(6,0).toString()+", "+tbDokter2.getValueAt(6,1).toString()+", "+tbDokter2.getValueAt(6,3).toString(),Integer.parseInt(tbDokter2.getValueAt(6,3).toString()));

                JFreeChart freeChart = ChartFactory.createPieChart("10 Besar Penyakit Di Rawat Inap Periode "+Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""),dpd,true,true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url 
                ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Inap ",freeChart);
                cf.setSize(internalFrame1.getWidth()-50, internalFrame1.getHeight()-50);
                cf.setLocationRelativeTo(internalFrame1);
                cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                cf.setVisible(true);

            }else if((tbDokter2.getRowCount()>5)&&(Integer.parseInt(tbDokter2.getValueAt(0,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(1,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(1,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(2,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(2,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(3,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(3,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(4,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(4,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(5,3).toString()))){
                DefaultPieDataset dpd = new DefaultPieDataset();
                dpd.setValue(tbDokter2.getValueAt(0,0).toString()+", "+tbDokter2.getValueAt(0,1).toString()+", "+tbDokter2.getValueAt(0,3).toString(),Integer.parseInt(tbDokter2.getValueAt(0,3).toString()));
                dpd.setValue(tbDokter2.getValueAt(1,0).toString()+", "+tbDokter2.getValueAt(1,1).toString()+", "+tbDokter2.getValueAt(1,3).toString(),Integer.parseInt(tbDokter2.getValueAt(1,3).toString()));
                dpd.setValue(tbDokter2.getValueAt(2,0).toString()+", "+tbDokter2.getValueAt(2,1).toString()+", "+tbDokter2.getValueAt(2,3).toString(),Integer.parseInt(tbDokter2.getValueAt(2,3).toString()));
                dpd.setValue(tbDokter2.getValueAt(3,0).toString()+", "+tbDokter2.getValueAt(3,1).toString()+", "+tbDokter2.getValueAt(3,3).toString(),Integer.parseInt(tbDokter2.getValueAt(3,3).toString()));
                dpd.setValue(tbDokter2.getValueAt(4,0).toString()+", "+tbDokter2.getValueAt(4,1).toString()+", "+tbDokter2.getValueAt(4,3).toString(),Integer.parseInt(tbDokter2.getValueAt(4,3).toString()));
                dpd.setValue(tbDokter2.getValueAt(5,0).toString()+", "+tbDokter2.getValueAt(5,1).toString()+", "+tbDokter2.getValueAt(5,3).toString(),Integer.parseInt(tbDokter2.getValueAt(5,3).toString()));

                JFreeChart freeChart = ChartFactory.createPieChart("10 Besar Penyakit Di Rawat Inap Periode "+Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""),dpd,true,true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url 
                ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Inap ",freeChart);
                cf.setSize(internalFrame1.getWidth()-50, internalFrame1.getHeight()-50);
                cf.setLocationRelativeTo(internalFrame1);
                cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                cf.setVisible(true);

            }else if((tbDokter2.getRowCount()>4)&&(Integer.parseInt(tbDokter2.getValueAt(0,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(1,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(1,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(2,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(2,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(3,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(3,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(4,3).toString()))){
                DefaultPieDataset dpd = new DefaultPieDataset();
                dpd.setValue(tbDokter2.getValueAt(0,0).toString()+", "+tbDokter2.getValueAt(0,1).toString()+", "+tbDokter2.getValueAt(0,3).toString(),Integer.parseInt(tbDokter2.getValueAt(0,3).toString()));
                dpd.setValue(tbDokter2.getValueAt(1,0).toString()+", "+tbDokter2.getValueAt(1,1).toString()+", "+tbDokter2.getValueAt(1,3).toString(),Integer.parseInt(tbDokter2.getValueAt(1,3).toString()));
                dpd.setValue(tbDokter2.getValueAt(2,0).toString()+", "+tbDokter2.getValueAt(2,1).toString()+", "+tbDokter2.getValueAt(2,3).toString(),Integer.parseInt(tbDokter2.getValueAt(2,3).toString()));
                dpd.setValue(tbDokter2.getValueAt(3,0).toString()+", "+tbDokter2.getValueAt(3,1).toString()+", "+tbDokter2.getValueAt(3,3).toString(),Integer.parseInt(tbDokter2.getValueAt(3,3).toString()));
                dpd.setValue(tbDokter2.getValueAt(4,0).toString()+", "+tbDokter2.getValueAt(4,1).toString()+", "+tbDokter2.getValueAt(4,3).toString(),Integer.parseInt(tbDokter2.getValueAt(4,3).toString()));

                JFreeChart freeChart = ChartFactory.createPieChart("10 Besar Penyakit Di Rawat Inap Periode "+Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""),dpd,true,true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url 
                ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Inap ",freeChart);
                cf.setSize(internalFrame1.getWidth()-50, internalFrame1.getHeight()-50);
                cf.setLocationRelativeTo(internalFrame1);
                cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                cf.setVisible(true);

            }else if((tbDokter2.getRowCount()>3)&&(Integer.parseInt(tbDokter2.getValueAt(0,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(1,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(1,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(2,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(2,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(3,3).toString()))){
                DefaultPieDataset dpd = new DefaultPieDataset();
                dpd.setValue(tbDokter2.getValueAt(0,0).toString()+", "+tbDokter2.getValueAt(0,1).toString()+", "+tbDokter2.getValueAt(0,3).toString(),Integer.parseInt(tbDokter2.getValueAt(0,3).toString()));
                dpd.setValue(tbDokter2.getValueAt(1,0).toString()+", "+tbDokter2.getValueAt(1,1).toString()+", "+tbDokter2.getValueAt(1,3).toString(),Integer.parseInt(tbDokter2.getValueAt(1,3).toString()));
                dpd.setValue(tbDokter2.getValueAt(2,0).toString()+", "+tbDokter2.getValueAt(2,1).toString()+", "+tbDokter2.getValueAt(2,3).toString(),Integer.parseInt(tbDokter2.getValueAt(2,3).toString()));
                dpd.setValue(tbDokter2.getValueAt(3,0).toString()+", "+tbDokter2.getValueAt(3,1).toString()+", "+tbDokter2.getValueAt(3,3).toString(),Integer.parseInt(tbDokter2.getValueAt(3,3).toString()));

                JFreeChart freeChart = ChartFactory.createPieChart("10 Besar Penyakit Di Rawat Inap Periode "+Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""),dpd,true,true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url 
                ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Inap ",freeChart);
                cf.setSize(internalFrame1.getWidth()-50, internalFrame1.getHeight()-50);
                cf.setLocationRelativeTo(internalFrame1);
                cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                cf.setVisible(true);      

            }else if((tbDokter2.getRowCount()>2)&&(Integer.parseInt(tbDokter2.getValueAt(0,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(1,3).toString()))
                    && (Integer.parseInt(tbDokter2.getValueAt(1,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(2,3).toString()))){
                DefaultPieDataset dpd = new DefaultPieDataset();
                dpd.setValue(tbDokter2.getValueAt(0,0).toString()+", "+tbDokter2.getValueAt(0,1).toString()+", "+tbDokter2.getValueAt(0,3).toString(),Integer.parseInt(tbDokter2.getValueAt(0,3).toString()));
                dpd.setValue(tbDokter2.getValueAt(1,0).toString()+", "+tbDokter2.getValueAt(1,1).toString()+", "+tbDokter2.getValueAt(1,3).toString(),Integer.parseInt(tbDokter2.getValueAt(1,3).toString()));
                dpd.setValue(tbDokter2.getValueAt(2,0).toString()+", "+tbDokter2.getValueAt(2,1).toString()+", "+tbDokter2.getValueAt(2,3).toString(),Integer.parseInt(tbDokter2.getValueAt(2,3).toString()));

                JFreeChart freeChart = ChartFactory.createPieChart("10 Besar Penyakit Di Rawat Inap Periode "+Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""),dpd,true,true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url 
                ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Inap ",freeChart);
                cf.setSize(internalFrame1.getWidth()-50, internalFrame1.getHeight()-50);
                cf.setLocationRelativeTo(internalFrame1);
                cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                cf.setVisible(true);          

            }else if((tbDokter2.getRowCount()>1)&&(Integer.parseInt(tbDokter2.getValueAt(0,3).toString())<=Integer.parseInt(tbDokter2.getValueAt(1,3).toString()))){
                DefaultPieDataset dpd = new DefaultPieDataset();
                dpd.setValue(tbDokter2.getValueAt(0,0).toString()+", "+tbDokter2.getValueAt(0,1).toString()+", "+tbDokter2.getValueAt(0,3).toString(),Integer.parseInt(tbDokter2.getValueAt(0,3).toString()));
                dpd.setValue(tbDokter2.getValueAt(1,0).toString()+", "+tbDokter2.getValueAt(1,1).toString()+", "+tbDokter2.getValueAt(1,3).toString(),Integer.parseInt(tbDokter2.getValueAt(1,3).toString()));

                JFreeChart freeChart = ChartFactory.createPieChart("10 Besar Penyakit Di Rawat Inap Periode "+Valid.SetTgl(Tgl1.getSelectedItem()+"")+" s.d. "+Valid.SetTgl(Tgl2.getSelectedItem()+""),dpd,true,true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url 
                ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Inap ",freeChart);
                cf.setSize(internalFrame1.getWidth()-50, internalFrame1.getHeight()-50);
                cf.setLocationRelativeTo(internalFrame1);
                cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                cf.setVisible(true);         

            }else{
                JOptionPane.showMessageDialog(null,"Maaf, Silahkan urutkan secara Descanding/Besar Ke Kecil dengan menklik pada kolom jumlah..!!!!");
            }
        }
        } else {
        JOptionPane.showMessageDialog(null, "Untuk mencetak grafik pie 10 penyakit tersedikit, silakan koordinasi dg. Inst. Rekam Medik...!!!");
        Tgl1.requestFocus();
    }
}//GEN-LAST:event_ppGrafikTerkecilPieActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Sequel.cariIsiComboDB("SELECT nm_gedung FROM bangsal WHERE nm_gedung<>'igd' and nm_gedung<>'-' and status='1' and nm_gedung<>'as-sami' GROUP BY nm_gedung ORDER BY nm_gedung", NmRuangan);
    }//GEN-LAST:event_formWindowOpened

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if (TabRawat.getSelectedIndex() == 0) {
            prosesCari();
        } else if (TabRawat.getSelectedIndex() == 1) {
            prosesCari2();
        } else if (TabRawat.getSelectedIndex() == 2) {
            tampilDiag1();
        } else if (TabRawat.getSelectedIndex() == 3) {
            tampilDiag2();
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void ppDaftarTerbanyakPerRuanganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppDaftarTerbanyakPerRuanganActionPerformed
        if (ruangDicetak.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu ruangan inap terlebih dulu...");
            Tgl1.requestFocus();
        } else {
            if (ruangDicetak.getText().equals("SEMUA RUANG")) {
                JOptionPane.showMessageDialog(null, "Silakan pilih salah satu nama ruangan inapnya dulu...!!!");
                Tgl1.requestFocus();
            } else if (ruangDicetak.getText().equals("AS-SAMI/1") || ruangDicetak.getText().equals("AS-SAMI/2")) {
                ctkRuangAsSamiTglKeluar();
            } else if (ruangDicetak.getText().equals("INTERNIST")) {
                ctkRuangRKPDzaalTglKeluar();
            } else if (ruangDicetak.getText().equals("AR-RAUDAH") || ruangDicetak.getText().equals("AR-RAUDAH ATAS") || ruangDicetak.getText().equals("AR-RAUDAH BAWAH")) {
                ctkRuangArRaudahTglKeluar();
            } else {
                ctkPerRuanganTglKeluar();
            }
        }
    }//GEN-LAST:event_ppDaftarTerbanyakPerRuanganActionPerformed

    private void ppDaftarTerbanyakSemuaRuanganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppDaftarTerbanyakSemuaRuanganActionPerformed
        if (akses.getkode().equals("Admin Utama") || akses.getpenyakit() == true) {
            if (NmRuangan.getSelectedItem().equals("SEMUA RUANG")) {
                if (jlhPenyakit.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "Silakan isi dulu jumlah besar penyakit yang akan diambil datanya...!!!");
                    jlhPenyakit.requestFocus();
                } else {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    Map<String, Object> param = new HashMap<>();
                    param.put("namars", akses.getnamars());
                    param.put("alamatrs", akses.getalamatrs());
                    param.put("kotars", akses.getkabupatenrs());
                    param.put("propinsirs", akses.getpropinsirs());
                    param.put("kontakrs", akses.getkontakrs());
                    param.put("emailrs", akses.getemailrs());
                    param.put("logo", Sequel.cariGambar("select logo from setting"));
                    param.put("periode", "PERIODE TGL. KELUAR " + Tgl1.getSelectedItem() + " S.D " + Tgl2.getSelectedItem());
                    param.put("jlhData", jlhPenyakit.getText());
                    Valid.MyReport("rpDaftar10BesarDiagnosaSI.jasper", "report", "::[ Daftar " + jlhPenyakit.getText() + " Besar Penyakit Terbanyak Semua Ruangan Inap Berdasarkan Tgl. Keluar ]::",
                            " SELECT a.kd_penyakit AS KD_ICD_10, a.ciri_ciri AS Deskripsi_Diagnosa, IFNULL(b.qty, 0) LK, ifnull(c.qty, 0) PR, IFNULL(b.qty, 0) + IFNULL(c.qty, 0) Total "
                            + " FROM ((SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, count(*) AS qty "
                            + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                            + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                            + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat "
                            + " LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar "
                            + " LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal "
                            + " LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                            + " WHERE diagnosa_pasien. STATUS = 'Ranap' AND bangsal.nm_bangsal NOT LIKE '%apotek%' "
                            + " AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                            + " AND kamar_inap.tgl_keluar BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' GROUP BY diagnosa_pasien.kd_penyakit ORDER BY qty DESC) AS a "
                            + " LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, count(*) AS qty "
                            + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                            + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar "
                            + " LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                            + " WHERE diagnosa_pasien. STATUS = 'Ranap' AND bangsal.nm_bangsal NOT LIKE '%apotek%' "
                            + " AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                            + " AND kamar_inap.tgl_keluar BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND pasien.jk = 'L' GROUP BY diagnosa_pasien.kd_penyakit "
                            + " ORDER BY qty DESC) AS b ON b.kd_penyakit = a.kd_penyakit LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, count(*) AS qty "
                            + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                            + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar "
                            + " LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                            + " WHERE diagnosa_pasien. STATUS = 'Ranap' AND bangsal.nm_bangsal NOT LIKE '%apotek%' "
                            + " AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                            + " AND kamar_inap.tgl_keluar BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND pasien.jk = 'P' GROUP BY diagnosa_pasien.kd_penyakit "
                            + " ORDER BY qty DESC) AS c ON c.kd_penyakit = a.kd_penyakit) LIMIT " + jlhPenyakit.getText() + "", param);
                    this.setCursor(Cursor.getDefaultCursor());
                }
            } else if (!NmRuangan.getSelectedItem().equals("SEMUA RUANG")) {
                JOptionPane.showMessageDialog(null, "Silakan pilih dulu SEMUA RUANG untuk nama ruangan inapnya...!!!");
                NmRuangan.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Untuk mencetak lap. penyakit terbanyak semua ruangan inap, silakan koordinasi dg. Inst. Rekam Medik...!!!");
            Tgl1.requestFocus();
        }
    }//GEN-LAST:event_ppDaftarTerbanyakSemuaRuanganActionPerformed

    private void ppLapRL53RawatInapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppLapRL53RawatInapActionPerformed
        if (akses.getkode().equals("Admin Utama") || akses.getpenyakit() == true) {
            if (NmRuangan.getSelectedItem().equals("SEMUA RUANG")) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Map<String, Object> param = new HashMap<>();
                param.put("namars", akses.getnamars());
                param.put("alamatrs", akses.getalamatrs());
                param.put("kotars", akses.getkabupatenrs());
                param.put("propinsirs", akses.getpropinsirs());
                param.put("kontakrs", akses.getkontakrs());
                param.put("emailrs", akses.getemailrs());
                param.put("logo", Sequel.cariGambar("select logo from setting"));
                param.put("periode", "PERIODE TGL. KELUAR " + Tgl1.getSelectedItem() + " S.D " + Tgl2.getSelectedItem());
                Valid.MyReport("rpDaftar10BesarDiagnosaRL53Inap.jasper", "report", "::[ Laporan - SIRS Online RL 5.3 Daftar 10 Besar Penyakit Rawat Inap Berdasarkan Tgl. Keluar ]::",
                        " SELECT a.kd_penyakit AS KD_ICD_10, a.ciri_ciri AS Deskripsi_Diagnosa, IFNULL(b.qty, 0) - ifnull(d.qty, 0) LK, ifnull(c.qty, 0) - ifnull(e.qty, 0) PR, "
                        + " ifnull(d.qty, 0) mati_LK, ifnull(e.qty, 0) mati_PR, ifnull(a.qty, 0) Total FROM ((SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, count(*) AS qty "
                        + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                        + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar "
                        + " LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                        + " WHERE diagnosa_pasien. STATUS = 'Ranap' AND bangsal.nm_bangsal NOT LIKE '%apotek%' AND bangsal.nm_bangsal NOT LIKE '%gudang%' "
                        + " AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                        + " AND kamar_inap.tgl_keluar BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + " GROUP BY diagnosa_pasien.kd_penyakit ORDER BY qty DESC) AS a "
                        + " LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, count(*) AS qty "
                        + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                        + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar  "
                        + " LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                        + " WHERE diagnosa_pasien. STATUS = 'Ranap' AND bangsal.nm_bangsal NOT LIKE '%apotek%' AND bangsal.nm_bangsal NOT LIKE '%gudang%' "
                        + " AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                        + " AND kamar_inap.tgl_keluar BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND pasien.jk = 'L' "
                        + " GROUP BY diagnosa_pasien.kd_penyakit ORDER BY qty DESC) AS b "
                        + " ON b.kd_penyakit = a.kd_penyakit LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, count(*) AS qty "
                        + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                        + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar "
                        + " LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                        + " WHERE diagnosa_pasien. STATUS = 'Ranap' AND bangsal.nm_bangsal NOT LIKE '%apotek%' AND bangsal.nm_bangsal NOT LIKE '%gudang%' "
                        + " AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                        + " AND kamar_inap.tgl_keluar BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND pasien.jk = 'P' "
                        + " GROUP BY diagnosa_pasien.kd_penyakit ORDER BY qty DESC) AS c "
                        + " ON c.kd_penyakit = a.kd_penyakit LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, count(*) AS qty "
                        + " FROM pasien_mati LEFT JOIN pasien ON pasien.no_rkm_medis = pasien_mati.no_rkm_medis "
                        + " LEFT JOIN reg_periksa ON pasien.no_rkm_medis=reg_periksa.no_rkm_medis and pasien_mati.no_rkm_medis=reg_periksa.no_rkm_medis "
                        + " LEFT JOIN diagnosa_pasien ON reg_periksa.no_rawat=diagnosa_pasien.no_rawat "
                        + " LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat "
                        + " LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal "
                        + " WHERE diagnosa_pasien. STATUS = 'Ranap' AND bangsal.nm_bangsal NOT LIKE '%apotek%' AND bangsal.nm_bangsal NOT LIKE '%gudang%' "
                        + " AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                        + " AND kamar_inap.tgl_keluar BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "'	AND pasien.jk = 'L' "
                        + " GROUP BY diagnosa_pasien.kd_penyakit ORDER BY qty DESC) AS d "
                        + " ON d.kd_penyakit = a.kd_penyakit LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, count(*) AS qty "
                        + " FROM pasien_mati LEFT JOIN pasien ON pasien.no_rkm_medis = pasien_mati.no_rkm_medis "
                        + " LEFT JOIN reg_periksa ON pasien.no_rkm_medis=reg_periksa.no_rkm_medis and pasien_mati.no_rkm_medis=reg_periksa.no_rkm_medis "
                        + " LEFT JOIN diagnosa_pasien ON reg_periksa.no_rawat=diagnosa_pasien.no_rawat "
                        + " LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat "
                        + " LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal "
                        + " WHERE diagnosa_pasien. STATUS = 'Ranap' AND bangsal.nm_bangsal NOT LIKE '%apotek%' AND bangsal.nm_bangsal NOT LIKE '%gudang%' "
                        + " AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                        + " AND kamar_inap.tgl_keluar BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND pasien.jk = 'P' "
                        + " GROUP BY diagnosa_pasien.kd_penyakit ORDER BY qty DESC) AS e ON e.kd_penyakit = a.kd_penyakit) LIMIT 10", param);
                this.setCursor(Cursor.getDefaultCursor());
            } else if (!NmRuangan.getSelectedItem().equals("SEMUA RUANG")) {
                JOptionPane.showMessageDialog(null, "Silakan pilih dulu SEMUA RUANG untuk nama ruangan inapnya...!!!");
                NmRuangan.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Untuk mencetak laporan RL 5.3 Penyakit Rawat Inap, silakan koordinasi dg. Inst. Rekam Medik...!!!");
            Tgl1.requestFocus();
        }
    }//GEN-LAST:event_ppLapRL53RawatInapActionPerformed

    private void ppDaftarTerbanyakPerRuanganMatiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppDaftarTerbanyakPerRuanganMatiActionPerformed
        if (ruangDicetak.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu ruangan inap terlebih dulu...");
            Tgl1.requestFocus();
        } else {
            if (ruangDicetak.getText().equals("SEMUA RUANG")) {
                JOptionPane.showMessageDialog(null, "Silakan pilih salah satu nama ruangan inapnya dulu...!!!");
                Tgl1.requestFocus();
            } else if (ruangDicetak.getText().equals("AS-SAMI/1") || ruangDicetak.getText().equals("AS-SAMI/2")) {
                ctkPerRuangAsSamiMatiTglKeluar();
            } else if (ruangDicetak.getText().equals("INTERNIST")) {
                ctkRuangRKPDzaalMatiTglKeluar();
            } else if (ruangDicetak.getText().equals("AR-RAUDAH") || ruangDicetak.getText().equals("AR-RAUDAH ATAS") || ruangDicetak.getText().equals("AR-RAUDAH BAWAH")) {
                ctkPerRuangArRaudahMatiTglKeluar();
            } else {
                ctkPerRuangMatiTglKeluar();
            }
        }
    }//GEN-LAST:event_ppDaftarTerbanyakPerRuanganMatiActionPerformed

    private void ppDaftarTerbanyakSemuaRuanganMatiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppDaftarTerbanyakSemuaRuanganMatiActionPerformed
        if (akses.getkode().equals("Admin Utama") || akses.getpenyakit() == true) {
            if (NmRuangan.getSelectedItem().equals("SEMUA RUANG")) {
                if (jlhPenyakit.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "Silakan isi dulu jumlah besar penyakit yang akan diambil datanya...!!!");
                    jlhPenyakit.requestFocus();
                } else {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    Map<String, Object> param = new HashMap<>();
                    param.put("namars", akses.getnamars());
                    param.put("alamatrs", akses.getalamatrs());
                    param.put("kotars", akses.getkabupatenrs());
                    param.put("propinsirs", akses.getpropinsirs());
                    param.put("kontakrs", akses.getkontakrs());
                    param.put("emailrs", akses.getemailrs());
                    param.put("periode", "PERIODE TGL. KELUAR " + Tgl1.getSelectedItem() + " S.D " + Tgl2.getSelectedItem());
                    param.put("logo", Sequel.cariGambar("select logo from setting"));
                    param.put("jlhData", jlhPenyakit.getText());
                    Valid.MyReport("rpDaftar10BesarDiagnosaSIMati.jasper", "report", "::[ Daftar " + jlhPenyakit.getText() + " Besar Penyakit Terbanyak Penyebab Kematian Semua Ruangan Inap Berdasarkan Tgl. Keluar ]::",
                            " SELECT a.kd_penyakit AS KD_ICD_10, a.ciri_ciri AS Deskripsi_Diagnosa, IFNULL(b.qty, 0) LK, ifnull(c.qty, 0) PR, "
                            + " IFNULL(b.qty, 0) + IFNULL(c.qty, 0) Total FROM ((SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, count(*) AS qty "
                            + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                            + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat "
                            + " LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal "
                            + " LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                            + " WHERE diagnosa_pasien. STATUS = 'Ranap' AND bangsal.nm_bangsal NOT LIKE '%apotek%' AND bangsal.nm_bangsal NOT LIKE '%gudang%' "
                            + " AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                            + " AND kamar_inap.tgl_keluar BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                            + " AND kamar_inap.stts_pulang like 'Meninggal%' GROUP BY diagnosa_pasien.kd_penyakit "
                            + " ORDER BY qty DESC) AS a LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, count(*) AS qty "
                            + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                            + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat "
                            + " LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal "
                            + " LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis WHERE diagnosa_pasien.STATUS = 'Ranap' "
                            + " AND bangsal.nm_bangsal NOT LIKE '%apotek%' AND bangsal.nm_bangsal NOT LIKE '%gudang%' "
                            + " AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                            + " AND kamar_inap.tgl_keluar BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                            + " AND kamar_inap.stts_pulang like 'Meninggal%' AND pasien.jk = 'L' GROUP BY diagnosa_pasien.kd_penyakit "
                            + " ORDER BY qty DESC) AS b ON b.kd_penyakit = a.kd_penyakit LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, count(*) AS qty "
                            + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                            + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat "
                            + " LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal "
                            + " LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis WHERE diagnosa_pasien. STATUS = 'Ranap' "
                            + " AND bangsal.nm_bangsal NOT LIKE '%apotek%' AND bangsal.nm_bangsal NOT LIKE '%gudang%'  "
                            + " AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%'  "
                            + " AND kamar_inap.tgl_keluar BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                            + " AND kamar_inap.stts_pulang like 'Meninggal%' AND pasien.jk = 'P' "
                            + " GROUP BY diagnosa_pasien.kd_penyakit ORDER BY qty DESC) AS c ON c.kd_penyakit = a.kd_penyakit) LIMIT " + jlhPenyakit.getText() + "", param);
                    this.setCursor(Cursor.getDefaultCursor());
                }
            } else if (!NmRuangan.getSelectedItem().equals("SEMUA RUANG")) {
                JOptionPane.showMessageDialog(null, "Silakan pilih dulu SEMUA RUANG untuk nama ruangan inapnya...!!!");
                NmRuangan.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Untuk mencetak lap. penyakit terbanyak kematian semua ruangan, silakan koordinasi dg. Inst. Rekam Medik...!!!");
            Tgl1.requestFocus();
        }
    }//GEN-LAST:event_ppDaftarTerbanyakSemuaRuanganMatiActionPerformed

    private void NmRuanganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NmRuanganMouseClicked
        NmRuanganActionPerformed(null);
    }//GEN-LAST:event_NmRuanganMouseClicked

    private void Tgl1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tgl1MouseClicked
        Tgl1.setEditable(false);
    }//GEN-LAST:event_Tgl1MouseClicked

    private void Tgl2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tgl2MouseClicked
        Tgl2.setEditable(false);
    }//GEN-LAST:event_Tgl2MouseClicked

    private void cmbRuangKhusus1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbRuangKhusus1MouseClicked
        cmbRuangKhusus1ActionPerformed(null);
    }//GEN-LAST:event_cmbRuangKhusus1MouseClicked

    private void cmbRuangKhusus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRuangKhusus1ActionPerformed
        judulRuangan.setText(cmbRuangKhusus1.getSelectedItem().toString());
        ruangDicetak.setText(cmbRuangKhusus1.getSelectedItem().toString());
    }//GEN-LAST:event_cmbRuangKhusus1ActionPerformed

    private void cmbRuangKhusus2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbRuangKhusus2MouseClicked
        cmbRuangKhusus2ActionPerformed(null);
    }//GEN-LAST:event_cmbRuangKhusus2MouseClicked

    private void cmbRuangKhusus2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRuangKhusus2ActionPerformed
        judulRuangan.setText(cmbRuangKhusus2.getSelectedItem().toString());
        ruangDicetak.setText(cmbRuangKhusus2.getSelectedItem().toString());        
    }//GEN-LAST:event_cmbRuangKhusus2ActionPerformed

    private void cmbRuangKhusus3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbRuangKhusus3MouseClicked
        judulRuangan.setText(cmbRuangKhusus3.getSelectedItem().toString());
        ruangDicetak.setText(cmbRuangKhusus3.getSelectedItem().toString());        
    }//GEN-LAST:event_cmbRuangKhusus3MouseClicked

    private void cmbRuangKhusus3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRuangKhusus3ActionPerformed
        judulRuangan.setText(cmbRuangKhusus3.getSelectedItem().toString());
        ruangDicetak.setText(cmbRuangKhusus3.getSelectedItem().toString());        
    }//GEN-LAST:event_cmbRuangKhusus3ActionPerformed

    private void cmbRuangKhusus3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbRuangKhusus3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbRuangKhusus3KeyPressed

    private void cmbRuangKhusus4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbRuangKhusus4MouseClicked
        cmbRuangKhusus4ActionPerformed(null);     
    }//GEN-LAST:event_cmbRuangKhusus4MouseClicked

    private void cmbRuangKhusus4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRuangKhusus4ActionPerformed
        judulRuangan.setText(cmbRuangKhusus4.getSelectedItem().toString());
        ruangDicetak.setText(cmbRuangKhusus4.getSelectedItem().toString());        
    }//GEN-LAST:event_cmbRuangKhusus4ActionPerformed

    private void NmRuanganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NmRuanganActionPerformed
        judulRuangan.setText(NmRuangan.getSelectedItem().toString());
        ruangDicetak.setText(NmRuangan.getSelectedItem().toString());
    }//GEN-LAST:event_NmRuanganActionPerformed

    private void ppDaftarTerbanyakPerRuangan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppDaftarTerbanyakPerRuangan1ActionPerformed
        if (ruangDicetak.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu ruangan inap terlebih dulu...");
            Tgl1.requestFocus();
        } else {
            if (ruangDicetak.getText().equals("SEMUA RUANG")) {
                JOptionPane.showMessageDialog(null, "Silakan pilih salah satu nama ruangan inapnya dulu...!!!");
                Tgl1.requestFocus();
            } else if (ruangDicetak.getText().equals("AS-SAMI/1") || ruangDicetak.getText().equals("AS-SAMI/2")) {
                ctkRuangAsSami();
            } else if (ruangDicetak.getText().equals("INTERNIST")) {
                ctkRuangRKPDzaalTglMasuk();
            } else if (ruangDicetak.getText().equals("AR-RAUDAH") || ruangDicetak.getText().equals("AR-RAUDAH ATAS") || ruangDicetak.getText().equals("AR-RAUDAH BAWAH")) {
                ctkRuangArRaudah();
            } else {
                ctkPerRuanganTglMasuk();
            }
        }
    }//GEN-LAST:event_ppDaftarTerbanyakPerRuangan1ActionPerformed

    private void ppDaftarTerbanyakSemuaRuangan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppDaftarTerbanyakSemuaRuangan1ActionPerformed
        if (akses.getkode().equals("Admin Utama") || akses.getpenyakit() == true) {
            if (NmRuangan.getSelectedItem().equals("SEMUA RUANG")) {
                if (jlhPenyakit.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "Silakan isi dulu jumlah besar penyakit yang akan diambil datanya...!!!");
                    jlhPenyakit.requestFocus();
                } else {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    Map<String, Object> param = new HashMap<>();
                    param.put("namars", akses.getnamars());
                    param.put("alamatrs", akses.getalamatrs());
                    param.put("kotars", akses.getkabupatenrs());
                    param.put("propinsirs", akses.getpropinsirs());
                    param.put("kontakrs", akses.getkontakrs());
                    param.put("emailrs", akses.getemailrs());
                    param.put("logo", Sequel.cariGambar("select logo from setting"));
                    param.put("periode", "PERIODE TGL. MASUK " + Tgl1.getSelectedItem() + " S.D " + Tgl2.getSelectedItem());
                    param.put("jlhData", jlhPenyakit.getText());
                    Valid.MyReport("rpDaftar10BesarDiagnosaSI.jasper", "report", "::[ Daftar " + jlhPenyakit.getText() + " Besar Penyakit Terbanyak Semua Ruangan Inap Berdasarkan Tgl. Masuk ]::",
                            " SELECT a.kd_penyakit AS KD_ICD_10, a.ciri_ciri AS Deskripsi_Diagnosa, IFNULL(b.qty, 0) LK, ifnull(c.qty, 0) PR, IFNULL(b.qty, 0) + IFNULL(c.qty, 0) Total "
                            + " FROM ((SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, count(*) AS qty "
                            + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                            + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                            + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat "
                            + " LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar "
                            + " LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal "
                            + " LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                            + " WHERE diagnosa_pasien. STATUS = 'Ranap' AND bangsal.nm_bangsal NOT LIKE '%apotek%' "
                            + " AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                            + " AND kamar_inap.tgl_masuk BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' GROUP BY diagnosa_pasien.kd_penyakit ORDER BY qty DESC) AS a "
                            + " LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, count(*) AS qty "
                            + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                            + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar "
                            + " LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                            + " WHERE diagnosa_pasien. STATUS = 'Ranap' AND bangsal.nm_bangsal NOT LIKE '%apotek%' "
                            + " AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                            + " AND kamar_inap.tgl_masuk BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND pasien.jk = 'L' GROUP BY diagnosa_pasien.kd_penyakit "
                            + " ORDER BY qty DESC) AS b ON b.kd_penyakit = a.kd_penyakit LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, count(*) AS qty "
                            + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                            + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar "
                            + " LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                            + " WHERE diagnosa_pasien. STATUS = 'Ranap' AND bangsal.nm_bangsal NOT LIKE '%apotek%' "
                            + " AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                            + " AND kamar_inap.tgl_masuk BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND pasien.jk = 'P' GROUP BY diagnosa_pasien.kd_penyakit "
                            + " ORDER BY qty DESC) AS c ON c.kd_penyakit = a.kd_penyakit) LIMIT " + jlhPenyakit.getText() + "", param);
                    this.setCursor(Cursor.getDefaultCursor());
                }
            } else if (!NmRuangan.getSelectedItem().equals("SEMUA RUANG")) {
                JOptionPane.showMessageDialog(null, "Silakan pilih dulu SEMUA RUANG untuk nama ruangan inapnya...!!!");
                NmRuangan.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Untuk mencetak lap. penyakit terbanyak semua ruangan inap, silakan koordinasi dg. Inst. Rekam Medik...!!!");
            Tgl1.requestFocus();
        }
    }//GEN-LAST:event_ppDaftarTerbanyakSemuaRuangan1ActionPerformed

    private void ppLapRL53RawatInap1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppLapRL53RawatInap1ActionPerformed
        if (akses.getkode().equals("Admin Utama") || akses.getpenyakit() == true) {
            if (NmRuangan.getSelectedItem().equals("SEMUA RUANG")) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Map<String, Object> param = new HashMap<>();
                param.put("namars", akses.getnamars());
                param.put("alamatrs", akses.getalamatrs());
                param.put("kotars", akses.getkabupatenrs());
                param.put("propinsirs", akses.getpropinsirs());
                param.put("kontakrs", akses.getkontakrs());
                param.put("emailrs", akses.getemailrs());
                param.put("logo", Sequel.cariGambar("select logo from setting"));
                param.put("periode", "PERIODE TGL. MASUK " + Tgl1.getSelectedItem() + " S.D " + Tgl2.getSelectedItem());
                Valid.MyReport("rpDaftar10BesarDiagnosaRL53Inap.jasper", "report", "::[ Laporan - SIRS Online RL 5.3 Daftar 10 Besar Penyakit Rawat Inap Berdasarkan Tgl. Masuk ]::",
                        " SELECT a.kd_penyakit AS KD_ICD_10, a.ciri_ciri AS Deskripsi_Diagnosa, IFNULL(b.qty, 0) - ifnull(d.qty, 0) LK, ifnull(c.qty, 0) - ifnull(e.qty, 0) PR, "
                        + " ifnull(d.qty, 0) mati_LK, ifnull(e.qty, 0) mati_PR, ifnull(a.qty, 0) Total FROM ((SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, count(*) AS qty "
                        + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                        + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar "
                        + " LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                        + " WHERE diagnosa_pasien. STATUS = 'Ranap' AND bangsal.nm_bangsal NOT LIKE '%apotek%' AND bangsal.nm_bangsal NOT LIKE '%gudang%' "
                        + " AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                        + " AND kamar_inap.tgl_masuk BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + " GROUP BY diagnosa_pasien.kd_penyakit ORDER BY qty DESC) AS a "
                        + " LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, count(*) AS qty "
                        + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                        + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar  "
                        + " LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                        + " WHERE diagnosa_pasien. STATUS = 'Ranap' AND bangsal.nm_bangsal NOT LIKE '%apotek%' AND bangsal.nm_bangsal NOT LIKE '%gudang%' "
                        + " AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                        + " AND kamar_inap.tgl_masuk BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND pasien.jk = 'L' "
                        + " GROUP BY diagnosa_pasien.kd_penyakit ORDER BY qty DESC) AS b "
                        + " ON b.kd_penyakit = a.kd_penyakit LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, count(*) AS qty "
                        + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                        + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar "
                        + " LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                        + " WHERE diagnosa_pasien. STATUS = 'Ranap' AND bangsal.nm_bangsal NOT LIKE '%apotek%' AND bangsal.nm_bangsal NOT LIKE '%gudang%' "
                        + " AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                        + " AND kamar_inap.tgl_masuk BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND pasien.jk = 'P' "
                        + " GROUP BY diagnosa_pasien.kd_penyakit ORDER BY qty DESC) AS c "
                        + " ON c.kd_penyakit = a.kd_penyakit LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, count(*) AS qty "
                        + " FROM pasien_mati LEFT JOIN pasien ON pasien.no_rkm_medis = pasien_mati.no_rkm_medis "
                        + " LEFT JOIN reg_periksa ON pasien.no_rkm_medis=reg_periksa.no_rkm_medis and pasien_mati.no_rkm_medis=reg_periksa.no_rkm_medis "
                        + " LEFT JOIN diagnosa_pasien ON reg_periksa.no_rawat=diagnosa_pasien.no_rawat "
                        + " LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat "
                        + " LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal "
                        + " WHERE diagnosa_pasien. STATUS = 'Ranap' AND bangsal.nm_bangsal NOT LIKE '%apotek%' AND bangsal.nm_bangsal NOT LIKE '%gudang%' "
                        + " AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                        + " AND kamar_inap.tgl_masuk BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "'	AND pasien.jk = 'L' "
                        + " GROUP BY diagnosa_pasien.kd_penyakit ORDER BY qty DESC) AS d "
                        + " ON d.kd_penyakit = a.kd_penyakit LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, count(*) AS qty "
                        + " FROM pasien_mati LEFT JOIN pasien ON pasien.no_rkm_medis = pasien_mati.no_rkm_medis "
                        + " LEFT JOIN reg_periksa ON pasien.no_rkm_medis=reg_periksa.no_rkm_medis and pasien_mati.no_rkm_medis=reg_periksa.no_rkm_medis "
                        + " LEFT JOIN diagnosa_pasien ON reg_periksa.no_rawat=diagnosa_pasien.no_rawat "
                        + " LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat "
                        + " LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal "
                        + " WHERE diagnosa_pasien. STATUS = 'Ranap' AND bangsal.nm_bangsal NOT LIKE '%apotek%' AND bangsal.nm_bangsal NOT LIKE '%gudang%' "
                        + " AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                        + " AND kamar_inap.tgl_masuk BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND pasien.jk = 'P' "
                        + " GROUP BY diagnosa_pasien.kd_penyakit ORDER BY qty DESC) AS e ON e.kd_penyakit = a.kd_penyakit) LIMIT 10", param);
                this.setCursor(Cursor.getDefaultCursor());
            } else if (!NmRuangan.getSelectedItem().equals("SEMUA RUANG")) {
                JOptionPane.showMessageDialog(null, "Silakan pilih dulu SEMUA RUANG untuk nama ruangan inapnya...!!!");
                NmRuangan.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Untuk mencetak laporan RL 5.3 Penyakit Rawat Inap, silakan koordinasi dg. Inst. Rekam Medik...!!!");
            Tgl1.requestFocus();
        }
    }//GEN-LAST:event_ppLapRL53RawatInap1ActionPerformed

    private void ppDaftarTerbanyakPerRuanganMati1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppDaftarTerbanyakPerRuanganMati1ActionPerformed
        if (ruangDicetak.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu ruangan inap terlebih dulu...");
            Tgl1.requestFocus();
        } else {            
            if (ruangDicetak.getText().equals("SEMUA RUANG")) {
                JOptionPane.showMessageDialog(null, "Silakan pilih salah satu nama ruangan inapnya dulu...!!!");
                Tgl1.requestFocus();
            } else if (ruangDicetak.getText().equals("AS-SAMI/1") || ruangDicetak.getText().equals("AS-SAMI/2")) {
                ctkRuangAsSamiMatiTglMsk();
            } else if (ruangDicetak.getText().equals("INTERNIST")) {
                ctkRuangRKPDzaalMatiTglMasuk();
            } else if (ruangDicetak.getText().equals("AR-RAUDAH") || ruangDicetak.getText().equals("AR-RAUDAH ATAS") || ruangDicetak.getText().equals("AR-RAUDAH BAWAH")) {
                ctkRuangArRaudahMatiTglMsk();
            } else {
                ctkPerRuangMatiTglMasuk();
            }
        }
    }//GEN-LAST:event_ppDaftarTerbanyakPerRuanganMati1ActionPerformed

    private void ppDaftarTerbanyakSemuaRuanganMati1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppDaftarTerbanyakSemuaRuanganMati1ActionPerformed
        if (akses.getkode().equals("Admin Utama") || akses.getpenyakit() == true) {
            if (NmRuangan.getSelectedItem().equals("SEMUA RUANG")) {
                if (jlhPenyakit.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "Silakan isi dulu jumlah besar penyakit yang akan diambil datanya...!!!");
                    jlhPenyakit.requestFocus();
                } else {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    Map<String, Object> param = new HashMap<>();
                    param.put("namars", akses.getnamars());
                    param.put("alamatrs", akses.getalamatrs());
                    param.put("kotars", akses.getkabupatenrs());
                    param.put("propinsirs", akses.getpropinsirs());
                    param.put("kontakrs", akses.getkontakrs());
                    param.put("emailrs", akses.getemailrs());
                    param.put("periode", "PERIODE TGL. MASUK " + Tgl1.getSelectedItem() + " S.D " + Tgl2.getSelectedItem());
                    param.put("logo", Sequel.cariGambar("select logo from setting"));
                    param.put("jlhData", jlhPenyakit.getText());
                    Valid.MyReport("rpDaftar10BesarDiagnosaSIMati.jasper", "report", "::[ Daftar " + jlhPenyakit.getText() + " Besar Penyakit Terbanyak Penyebab Kematian Semua Ruangan Inap Berdasarkan Tgl. Masuk ]::",
                            " SELECT a.kd_penyakit AS KD_ICD_10, a.ciri_ciri AS Deskripsi_Diagnosa, IFNULL(b.qty, 0) LK, ifnull(c.qty, 0) PR, "
                            + " IFNULL(b.qty, 0) + IFNULL(c.qty, 0) Total FROM ((SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, count(*) AS qty "
                            + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                            + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat "
                            + " LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal "
                            + " LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                            + " WHERE diagnosa_pasien. STATUS = 'Ranap' AND bangsal.nm_bangsal NOT LIKE '%apotek%' AND bangsal.nm_bangsal NOT LIKE '%gudang%' "
                            + " AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                            + " AND kamar_inap.tgl_masuk BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                            + " AND kamar_inap.stts_pulang like 'Meninggal%' GROUP BY diagnosa_pasien.kd_penyakit "
                            + " ORDER BY qty DESC) AS a LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, count(*) AS qty "
                            + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                            + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat "
                            + " LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal "
                            + " LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis WHERE diagnosa_pasien.STATUS = 'Ranap' "
                            + " AND bangsal.nm_bangsal NOT LIKE '%apotek%' AND bangsal.nm_bangsal NOT LIKE '%gudang%' "
                            + " AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                            + " AND kamar_inap.tgl_masuk BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                            + " AND kamar_inap.stts_pulang like 'Meninggal%' AND pasien.jk = 'L' GROUP BY diagnosa_pasien.kd_penyakit "
                            + " ORDER BY qty DESC) AS b ON b.kd_penyakit = a.kd_penyakit LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, count(*) AS qty "
                            + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                            + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat "
                            + " LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal "
                            + " LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis WHERE diagnosa_pasien. STATUS = 'Ranap' "
                            + " AND bangsal.nm_bangsal NOT LIKE '%apotek%' AND bangsal.nm_bangsal NOT LIKE '%gudang%'  "
                            + " AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%'  "
                            + " AND kamar_inap.tgl_masuk BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                            + " AND kamar_inap.stts_pulang like 'Meninggal%' AND pasien.jk = 'P' "
                            + " GROUP BY diagnosa_pasien.kd_penyakit ORDER BY qty DESC) AS c ON c.kd_penyakit = a.kd_penyakit) LIMIT " + jlhPenyakit.getText() + "", param);
                    this.setCursor(Cursor.getDefaultCursor());
                }
            } else if (!NmRuangan.getSelectedItem().equals("SEMUA RUANG")) {
                JOptionPane.showMessageDialog(null, "Silakan pilih dulu SEMUA RUANG untuk nama ruangan inapnya...!!!");
                NmRuangan.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Untuk mencetak lap. penyakit terbanyak kematian semua ruangan, silakan koordinasi dg. Inst. Rekam Medik...!!!");
            Tgl1.requestFocus();
        }
    }//GEN-LAST:event_ppDaftarTerbanyakSemuaRuanganMati1ActionPerformed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnCariActionPerformed(null);
            kdpenyakit.setText("");
            nmpenyakit.setText("");
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            btnCari.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        }
    }//GEN-LAST:event_TCariKeyPressed

    private void ppRincianPerDiagnosaTglMskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppRincianPerDiagnosaTglMskActionPerformed
        if (ruangDicetak.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu ruangan inap terlebih dulu...");
            Tgl1.requestFocus();
        } else {            
            if (ruangDicetak.getText().equals("AS-SAMI/1") || ruangDicetak.getText().equals("AS-SAMI/2")) {
                prinRincianDiagnosaAsSamiTglMasuk();
            } else if (ruangDicetak.getText().equals("INTERNIST")) {
                prinRincianRKPDTglMasuk();
            } else if (ruangDicetak.getText().equals("AR-RAUDAH") || ruangDicetak.getText().equals("AR-RAUDAH ATAS") || ruangDicetak.getText().equals("AR-RAUDAH BAWAH")) {
                prinRincianDiagnosaArRaudahTglMasuk();
            } else {
                prinSemuaRincianTglMasuk();
            }
        }
    }//GEN-LAST:event_ppRincianPerDiagnosaTglMskActionPerformed

    private void ppRincianPerDiagnosaTglKlrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppRincianPerDiagnosaTglKlrActionPerformed
        if (ruangDicetak.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu ruangan inap terlebih dulu...");
            Tgl1.requestFocus();
        } else {
            if (ruangDicetak.getText().equals("AS-SAMI/1") || ruangDicetak.getText().equals("AS-SAMI/2")) {
                prinRincianDiagnosaAsSamiTglPulang();
            } else if (ruangDicetak.getText().equals("INTERNIST")) {
                prinRincianRKPDTglPulang();
            } else if (ruangDicetak.getText().equals("AR-RAUDAH") || ruangDicetak.getText().equals("AR-RAUDAH ATAS") || ruangDicetak.getText().equals("AR-RAUDAH BAWAH")) {
                prinRincianDiagnosaArRaudahTglPulang();
            } else {
                prinSemuaRincianTglPulang();
            }
        }
    }//GEN-LAST:event_ppRincianPerDiagnosaTglKlrActionPerformed

    private void kdpnjKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdpnjKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            Sequel.cariIsi("select png_jawab from penjab where kd_pj=?", nmpnj, kdpnj.getText());
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            btnPenjab.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnPenjab.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            btnPenjabActionPerformed(null);
        }
    }//GEN-LAST:event_kdpnjKeyPressed

    private void btnPenjabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPenjabActionPerformed
        akses.setform("DlgFrekuensiPenyakitRanap");
        pasien.penjab.onCari();
        pasien.penjab.isCek();
        pasien.penjab.setSize(882, 593);
        pasien.penjab.setLocationRelativeTo(internalFrame1);
        pasien.penjab.setVisible(true);
        pasien.penjab.onCari();
    }//GEN-LAST:event_btnPenjabActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgFrekuensiPenyakitRanap dialog = new DlgFrekuensiPenyakitRanap(new javax.swing.JFrame(), true);
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
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.TextBox Kd2;
    private javax.swing.JMenu MnBerdasarTglKlr;
    private javax.swing.JMenu MnBerdasarTglMsk;
    private widget.ComboBox NmRuangan;
    private widget.TextBox TCari;
    private javax.swing.JTabbedPane TabRawat;
    private widget.Tanggal Tgl1;
    private widget.Tanggal Tgl2;
    private widget.Button btnCari;
    private widget.Button btnPenjab;
    private widget.Button btnPenyakit;
    private widget.ComboBox cmbRuangKhusus1;
    private widget.ComboBox cmbRuangKhusus2;
    private widget.ComboBox cmbRuangKhusus3;
    private widget.ComboBox cmbRuangKhusus4;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel23;
    private widget.Label jLabel6;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.TextBox jlhPenyakit;
    private widget.TextBox judulRuangan;
    private widget.TextBox kdAkses;
    private widget.TextBox kdpenyakit;
    private widget.TextBox kdpnj;
    private widget.Label label11;
    private widget.Label label17;
    private widget.Label label18;
    private widget.Label label19;
    private widget.Label label20;
    private widget.Label label21;
    private widget.Label label9;
    private widget.TextBox nmpenyakit;
    private widget.TextBox nmpnj;
    private widget.panelisi panelisi1;
    private widget.panelisi panelisi4;
    private javax.swing.JMenuItem ppDaftarTerbanyakPerRuangan;
    private javax.swing.JMenuItem ppDaftarTerbanyakPerRuangan1;
    private javax.swing.JMenuItem ppDaftarTerbanyakPerRuanganMati;
    private javax.swing.JMenuItem ppDaftarTerbanyakPerRuanganMati1;
    private javax.swing.JMenuItem ppDaftarTerbanyakSemuaRuangan;
    private javax.swing.JMenuItem ppDaftarTerbanyakSemuaRuangan1;
    private javax.swing.JMenuItem ppDaftarTerbanyakSemuaRuanganMati;
    private javax.swing.JMenuItem ppDaftarTerbanyakSemuaRuanganMati1;
    private javax.swing.JMenuItem ppGrafikTerbanyakBatang;
    private javax.swing.JMenuItem ppGrafikTerbanyakPie;
    private javax.swing.JMenuItem ppGrafikTerkecilBatang;
    private javax.swing.JMenuItem ppGrafikTerkecilPie;
    private javax.swing.JMenuItem ppLapRL53RawatInap;
    private javax.swing.JMenuItem ppLapRL53RawatInap1;
    private javax.swing.JMenuItem ppRincianPerDiagnosaTglKlr;
    private javax.swing.JMenuItem ppRincianPerDiagnosaTglMsk;
    private widget.TextBox ruangDicetak;
    private widget.ScrollPane scrollPane1;
    private widget.ScrollPane scrollPane2;
    private widget.ScrollPane scrollPane3;
    private widget.ScrollPane scrollPane4;
    private widget.Table tbDiagnosa;
    private widget.Table tbDiagnosa1;
    private widget.Table tbDokter;
    private widget.Table tbDokter2;
    private widget.TextBox userBerizin;
    // End of variables declaration//GEN-END:variables

    private void prosesCari() {
        Valid.tabelKosong(tabMode);
        try {
            if (NmRuangan.getSelectedItem().equals("SEMUA RUANG")) {
                ps = koneksi.prepareStatement(
                        "select penyakit.kd_penyakit,SUBSTRING(penyakit.nm_penyakit,1,80) as penyakit "
                        + "from penyakit inner join diagnosa_pasien inner join reg_periksa inner join kamar_inap inner join kamar inner join bangsal "
                        + "on penyakit.kd_penyakit=diagnosa_pasien.kd_penyakit and reg_periksa.no_rawat=diagnosa_pasien.no_rawat "
                        + "and reg_periksa.no_rawat=kamar_inap.no_rawat and kamar.kd_kamar=kamar_inap.kd_kamar and kamar.kd_bangsal=bangsal.kd_bangsal "
                        + "where diagnosa_pasien.status='Ranap' and diagnosa_pasien.prioritas='1' and "
                        + "penyakit.kd_penyakit like ? and reg_periksa.tgl_registrasi between ? and ? "
                        + "group by penyakit.kd_penyakit order by penyakit.kd_penyakit");
            } 
            
            if (!NmRuangan.getSelectedItem().equals("SEMUA RUANG") || (!ruangDicetak.getText().equals("SEMUA RUANG"))) {
                ps = koneksi.prepareStatement(
                        "select penyakit.kd_penyakit,SUBSTRING(penyakit.nm_penyakit,1,80) as penyakit "
                        + "from penyakit inner join diagnosa_pasien inner join reg_periksa inner join kamar_inap inner join kamar inner join bangsal "
                        + "on penyakit.kd_penyakit=diagnosa_pasien.kd_penyakit and reg_periksa.no_rawat=diagnosa_pasien.no_rawat "
                        + "and reg_periksa.no_rawat=kamar_inap.no_rawat and kamar.kd_kamar=kamar_inap.kd_kamar and kamar.kd_bangsal=bangsal.kd_bangsal "
                        + "where diagnosa_pasien.status='Ranap' and diagnosa_pasien.prioritas='1' and "
                        + "penyakit.kd_penyakit like ? and reg_periksa.tgl_registrasi between ? and ? and bangsal.nm_bangsal like ? "
                        + "group by penyakit.kd_penyakit order by penyakit.kd_penyakit");
            }

            try {
                if (NmRuangan.getSelectedItem().equals("SEMUA RUANG")) {
                    ps.setString(1, "%" + kdpenyakit.getText() + "%");
                    ps.setString(2, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                    ps.setString(3, Valid.SetTgl(Tgl2.getSelectedItem() + ""));                    
                }
                
                if (!NmRuangan.getSelectedItem().equals("SEMUA RUANG") || (!ruangDicetak.getText().equals("SEMUA RUANG"))) {
                    ps.setString(1, "%" + kdpenyakit.getText() + "%");
                    ps.setString(2, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                    ps.setString(3, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                    ps.setString(4, "%" + ruangDicetak.getText() + "%");
                }

                rs = ps.executeQuery();
                while (rs.next()) {
                    i = 0;
                    ps2 = koneksi.prepareStatement(
                            "select diagnosa_pasien.no_rawat from pasien inner join reg_periksa inner join diagnosa_pasien "
                            + "on pasien.no_rkm_medis=reg_periksa.no_rkm_medis and reg_periksa.no_rawat=diagnosa_pasien.no_rawat where "
                            + "diagnosa_pasien.status='Ranap' and diagnosa_pasien.prioritas='1' and reg_periksa.tgl_registrasi between ? and ? "
                            + "and diagnosa_pasien.kd_penyakit=? group by diagnosa_pasien.no_rawat");
                    try {
                        ps2.setString(1, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                        ps2.setString(2, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                        ps2.setString(3, rs.getString("kd_penyakit"));
                        rs2 = ps2.executeQuery();
                        Sequel.queryu("delete from temporary_surveilens_penyakit");
                        while (rs2.next()) {
                            ps3 = koneksi.prepareStatement("select diagnosa_pasien.kd_penyakit from diagnosa_pasien where diagnosa_pasien.prioritas<>'1' and diagnosa_pasien.status='Ranap' and diagnosa_pasien.no_rawat=?");
                            try {
                                ps3.setString(1, rs2.getString(1));
                                rs3 = ps3.executeQuery();
                                while (rs3.next()) {
                                    Sequel.menyimpan("temporary_surveilens_penyakit", "?,?", 2, new String[]{
                                        rs.getString("kd_penyakit"), rs3.getString("kd_penyakit")
                                    });
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
                        }
                        diagnosa = "";
                        rs2.last();
                        if (rs2.getRow() > 0) {
                            ps4 = koneksi.prepareStatement("select temporary_surveilens_penyakit.kd_penyakit2 from temporary_surveilens_penyakit where temporary_surveilens_penyakit.kd_penyakit=? group by temporary_surveilens_penyakit.kd_penyakit2");
                            try {
                                ps4.setString(1, rs.getString("kd_penyakit"));
                                rs4 = ps4.executeQuery();
                                while (rs4.next()) {
                                    if (diagnosa.equals("")) {
                                        diagnosa = rs4.getString(1);
                                    } else {
                                        diagnosa = diagnosa + ", " + rs4.getString(1);
                                    }
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
                        }
                        i = rs2.getRow();
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rs2 != null) {
                            rs2.close();
                        }
                        if (ps2 != null) {
                            ps2.close();
                        }
                    }
                    tabMode.addRow(new Object[]{rs.getString("kd_penyakit"), rs.getString("penyakit"), diagnosa, i});
                }
                label9.setText("      Record : " + tabMode.getRowCount());
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Catatan  " + e);
        }        
    }
    
    private void prosesCari2() {
        Valid.tabelKosong(tabMode2);
        try {
            if (NmRuangan.getSelectedItem().equals("SEMUA RUANG")) {
                ps = koneksi.prepareStatement(
                        "select penyakit.kd_penyakit,SUBSTRING(penyakit.nm_penyakit,1,80) as penyakit "
                        + "from penyakit inner join diagnosa_pasien inner join reg_periksa inner join kamar_inap inner join kamar inner join bangsal "
                        + "on penyakit.kd_penyakit=diagnosa_pasien.kd_penyakit and reg_periksa.no_rawat=diagnosa_pasien.no_rawat "
                        + "and reg_periksa.no_rawat=kamar_inap.no_rawat and kamar_inap.kd_kamar=kamar.kd_kamar and kamar.kd_bangsal=bangsal.kd_bangsal "
                        + "where diagnosa_pasien.status='Ranap' and diagnosa_pasien.prioritas='1' and "
                        + "penyakit.kd_penyakit like ? and kamar_inap.tgl_keluar between ? and ? "
                        + "group by penyakit.kd_penyakit order by penyakit.kd_penyakit");                
            }
            
            if (!NmRuangan.getSelectedItem().equals("SEMUA RUANG") || (!ruangDicetak.getText().equals("SEMUA RUANG"))) {
                ps = koneksi.prepareStatement(
                        "select penyakit.kd_penyakit,SUBSTRING(penyakit.nm_penyakit,1,80) as penyakit "
                        + "from penyakit inner join diagnosa_pasien inner join reg_periksa inner join kamar_inap inner join kamar inner join bangsal "
                        + "on penyakit.kd_penyakit=diagnosa_pasien.kd_penyakit and reg_periksa.no_rawat=diagnosa_pasien.no_rawat "
                        + "and reg_periksa.no_rawat=kamar_inap.no_rawat and kamar_inap.kd_kamar=kamar.kd_kamar and kamar.kd_bangsal=bangsal.kd_bangsal "
                        + "where diagnosa_pasien.status='Ranap' and diagnosa_pasien.prioritas='1' and "
                        + "penyakit.kd_penyakit like ? and kamar_inap.tgl_keluar between ? and ? and bangsal.nm_bangsal like ? "
                        + "group by penyakit.kd_penyakit order by penyakit.kd_penyakit");
            }

            try {
                if (NmRuangan.getSelectedItem().equals("SEMUA RUANG")) {
                    ps.setString(1, "%" + kdpenyakit.getText() + "%");
                    ps.setString(2, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                    ps.setString(3, Valid.SetTgl(Tgl2.getSelectedItem() + ""));                    
                }
                
                if (!NmRuangan.getSelectedItem().equals("SEMUA RUANG") || (!ruangDicetak.getText().equals("SEMUA RUANG"))) {
                    ps.setString(1, "%" + kdpenyakit.getText() + "%");
                    ps.setString(2, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                    ps.setString(3, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                    ps.setString(4, "%" + ruangDicetak.getText() + "%");
                }

                rs = ps.executeQuery();
                while (rs.next()) {
                    i = 0;
                    ps2 = koneksi.prepareStatement(
                            "select diagnosa_pasien.no_rawat from pasien inner join reg_periksa inner join diagnosa_pasien inner join kamar_inap "
                            + "on pasien.no_rkm_medis=reg_periksa.no_rkm_medis and reg_periksa.no_rawat=diagnosa_pasien.no_rawat and reg_periksa.no_rawat=kamar_inap.no_rawat where "
                            + "diagnosa_pasien.status='Ranap' and diagnosa_pasien.prioritas='1' and kamar_inap.tgl_keluar between ? and ? "
                            + "and diagnosa_pasien.kd_penyakit=? group by diagnosa_pasien.no_rawat");
                    try {
                        ps2.setString(1, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                        ps2.setString(2, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                        ps2.setString(3, rs.getString("kd_penyakit"));
                        rs2 = ps2.executeQuery();
                        Sequel.queryu("delete from temporary_surveilens_penyakit");
                        while (rs2.next()) {
                            ps3 = koneksi.prepareStatement("select diagnosa_pasien.kd_penyakit from diagnosa_pasien where diagnosa_pasien.prioritas<>'1' and diagnosa_pasien.status='Ranap' and diagnosa_pasien.no_rawat=?");
                            try {
                                ps3.setString(1, rs2.getString(1));
                                rs3 = ps3.executeQuery();
                                while (rs3.next()) {
                                    Sequel.menyimpan("temporary_surveilens_penyakit", "?,?", 2, new String[]{
                                        rs.getString("kd_penyakit"), rs3.getString("kd_penyakit")
                                    });
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
                        }
                        diagnosa = "";
                        rs2.last();
                        if (rs2.getRow() > 0) {
                            ps4 = koneksi.prepareStatement("select temporary_surveilens_penyakit.kd_penyakit2 from temporary_surveilens_penyakit where temporary_surveilens_penyakit.kd_penyakit=? group by temporary_surveilens_penyakit.kd_penyakit2");
                            try {
                                ps4.setString(1, rs.getString("kd_penyakit"));
                                rs4 = ps4.executeQuery();
                                while (rs4.next()) {
                                    if (diagnosa.equals("")) {
                                        diagnosa = rs4.getString(1);
                                    } else {
                                        diagnosa = diagnosa + ", " + rs4.getString(1);
                                    }
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
                        }
                        i = rs2.getRow();
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rs2 != null) {
                            rs2.close();
                        }
                        if (ps2 != null) {
                            ps2.close();
                        }
                    }
                    tabMode2.addRow(new Object[]{rs.getString("kd_penyakit"), rs.getString("penyakit"), diagnosa, i});
                }
                label9.setText("      Record : " + tabMode2.getRowCount());
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Catatan  " + e);
        }
    }
    
    private void tampilDiag1() {
        Valid.tabelKosong(tabMode3);
        try {
            if (NmRuangan.getSelectedItem().equals("SEMUA RUANG")) {
                ps5 = koneksi.prepareStatement(
                        "SELECT DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y') tgl_masuk, DATE_FORMAT(ki.tgl_keluar,'%d-%m-%Y') tgl_pulang, "
                        + "ki.stts_pulang, p.no_rkm_medis no_rm, p.nm_pasien, b.nm_bangsal, pj.png_jawab cr_byr, "
                        + "py.kd_penyakit icd_10, py.ciri_ciri desk_diagnosa, if(dp.prioritas='1','Primer','Sekunder') status_diag FROM diagnosa_pasien dp "
                        + "INNER JOIN penyakit py on py.kd_penyakit=dp.kd_penyakit "
                        + "INNER JOIN reg_periksa rp on rp.no_rawat=dp.no_rawat "
                        + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                        + "INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj "
                        + "INNER JOIN kamar_inap ki on ki.no_rawat=dp.no_rawat "
                        + "INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar "
                        + "INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal "
                        + "WHERE rp.tgl_registrasi BETWEEN ? and ? and ki.stts_pulang not in ('-','pindah kamar') and "
                        + "py.ciri_ciri like ? and dp.kd_penyakit like ? and pj.kd_pj like ? ORDER BY p.no_rkm_medis, rp.tgl_registrasi, dp.prioritas");
            } 
            
            if (!NmRuangan.getSelectedItem().equals("SEMUA RUANG") || (!ruangDicetak.getText().equals("SEMUA RUANG"))) {
                ps5 = koneksi.prepareStatement(
                        "SELECT DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y') tgl_masuk, DATE_FORMAT(ki.tgl_keluar,'%d-%m-%Y') tgl_pulang, "
                        + "ki.stts_pulang, p.no_rkm_medis no_rm, p.nm_pasien, b.nm_bangsal, pj.png_jawab cr_byr, "
                        + "py.kd_penyakit icd_10, py.ciri_ciri desk_diagnosa, if(dp.prioritas='1','Primer','Sekunder') status_diag FROM diagnosa_pasien dp "
                        + "INNER JOIN penyakit py on py.kd_penyakit=dp.kd_penyakit "
                        + "INNER JOIN reg_periksa rp on rp.no_rawat=dp.no_rawat "
                        + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                        + "INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj "
                        + "INNER JOIN kamar_inap ki on ki.no_rawat=dp.no_rawat "
                        + "INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar "
                        + "INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal "
                        + "WHERE rp.tgl_registrasi BETWEEN ? and ? and ki.stts_pulang not in ('-','pindah kamar') and "
                        + "py.ciri_ciri like ? and dp.kd_penyakit like ? and pj.kd_pj like ? and b.nm_bangsal like ? ORDER BY p.no_rkm_medis, rp.tgl_registrasi, dp.prioritas");
            }

            try {
                ps5.setString(1, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps5.setString(2, Valid.SetTgl(Tgl2.getSelectedItem() + ""));

                if (NmRuangan.getSelectedItem().equals("SEMUA RUANG")) {
                    if (!TCari.getText().trim().equals("")) {
                        ps5.setString(3, "%" + TCari.getText() + "%");
                        ps5.setString(4, "%%");
                        ps5.setString(5, "%%");
                    }                    
                    if (!kdpenyakit.getText().trim().equals("")) {
                        ps5.setString(3, "%%");
                        ps5.setString(4, "%" + kdpenyakit.getText() + "%");
                        ps5.setString(5, "%%");
                    }                    
                    if (!kdpnj.getText().trim().equals("")) {
                        ps5.setString(3, "%%");
                        ps5.setString(4, "%%");
                        ps5.setString(5, "%" + kdpnj.getText() + "%");
                    }                    
                    if (!TCari.getText().trim().equals("") && (!kdpnj.getText().trim().equals(""))) {
                        ps5.setString(3, "%" + TCari.getText() + "%");
                        ps5.setString(4, "%%");
                        ps5.setString(5, "%" + kdpnj.getText() + "%");
                    }                    
                    if (!kdpenyakit.getText().trim().equals("") && (!kdpnj.getText().trim().equals(""))) {
                        ps5.setString(3, "%%");
                        ps5.setString(4, "%" + kdpenyakit.getText() + "%");
                        ps5.setString(5, "%" + kdpnj.getText() + "%");
                    }                    
                    if (TCari.getText().trim().equals("") && (kdpenyakit.getText().trim().equals("") && (kdpnj.getText().trim().equals("")))) {
                        ps5.setString(3, "%%");
                        ps5.setString(4, "%%");
                        ps5.setString(5, "%%");
                    }                    
                } 
                
                if (!NmRuangan.getSelectedItem().equals("SEMUA RUANG") || (!ruangDicetak.getText().equals("SEMUA RUANG"))) {
                    if (!TCari.getText().trim().equals("")) {
                        ps5.setString(3, "%" + TCari.getText() + "%");
                        ps5.setString(4, "%%");
                        ps5.setString(5, "%%");
                    }                    
                    if (!kdpenyakit.getText().trim().equals("")) {
                        ps5.setString(3, "%%");
                        ps5.setString(4, "%" + kdpenyakit.getText() + "%");
                        ps5.setString(5, "%%");
                    }                    
                    if (!kdpnj.getText().trim().equals("")) {
                        ps5.setString(3, "%%");
                        ps5.setString(4, "%%");
                        ps5.setString(5, "%" + kdpnj.getText() + "%");
                    }                    
                    if (!TCari.getText().trim().equals("") && (!kdpnj.getText().trim().equals(""))) {
                        ps5.setString(3, "%" + TCari.getText() + "%");
                        ps5.setString(4, "%%");
                        ps5.setString(5, "%" + kdpnj.getText() + "%");
                    }                    
                    if (!kdpenyakit.getText().trim().equals("") && (!kdpnj.getText().trim().equals(""))) {
                        ps5.setString(3, "%%");
                        ps5.setString(4, "%" + kdpenyakit.getText() + "%");
                        ps5.setString(5, "%" + kdpnj.getText() + "%");
                    }                    
                    if (TCari.getText().trim().equals("") && (kdpenyakit.getText().trim().equals("") && (kdpnj.getText().trim().equals("")))) {
                        ps5.setString(3, "%%");
                        ps5.setString(4, "%%");
                        ps5.setString(5, "%%");
                    }    
                    ps5.setString(6, "%" + ruangDicetak.getText() + "%");
                }

                rs5 = ps5.executeQuery();
                while (rs5.next()) {
                    tabMode3.addRow(new Object[]{
                        rs5.getString("tgl_masuk"),
                        rs5.getString("tgl_pulang"),
                        rs5.getString("stts_pulang"),
                        rs5.getString("no_rm"),
                        rs5.getString("nm_pasien"),
                        rs5.getString("nm_bangsal"),
                        rs5.getString("cr_byr"),
                        rs5.getString("icd_10"),
                        rs5.getString("desk_diagnosa"),
                        rs5.getString("status_diag")
                    });
                }

            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs5 != null) {
                    rs5.close();
                }
                if (ps5 != null) {
                    ps5.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Catatan  " + e);
        }
        label9.setText("      Record : " + tabMode3.getRowCount());
    }
    
    private void tampilDiag2() {
        Valid.tabelKosong(tabMode4);
        try {
            if (NmRuangan.getSelectedItem().equals("SEMUA RUANG")) {
                ps6 = koneksi.prepareStatement(
                        "SELECT DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y') tgl_masuk, DATE_FORMAT(ki.tgl_keluar,'%d-%m-%Y') tgl_pulang, "
                        + "ki.stts_pulang, p.no_rkm_medis no_rm, p.nm_pasien, b.nm_bangsal, pj.png_jawab cr_byr, "
                        + "py.kd_penyakit icd_10, py.ciri_ciri desk_diagnosa, if(dp.prioritas='1','Primer','Sekunder') status_diag FROM diagnosa_pasien dp "
                        + "INNER JOIN penyakit py on py.kd_penyakit=dp.kd_penyakit "
                        + "INNER JOIN reg_periksa rp on rp.no_rawat=dp.no_rawat "
                        + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                        + "INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj "
                        + "INNER JOIN kamar_inap ki on ki.no_rawat=dp.no_rawat "
                        + "INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar "
                        + "INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal "
                        + "WHERE ki.tgl_keluar BETWEEN ? and ? and ki.stts_pulang not in ('-','pindah kamar') and "
                        + "py.ciri_ciri like ? and dp.kd_penyakit like ? and pj.kd_pj like ? ORDER BY p.no_rkm_medis, rp.tgl_registrasi, dp.prioritas");
            }
            
            if (!NmRuangan.getSelectedItem().equals("SEMUA RUANG") || (!ruangDicetak.getText().equals("SEMUA RUANG"))) {
                ps6 = koneksi.prepareStatement(
                        "SELECT DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y') tgl_masuk, DATE_FORMAT(ki.tgl_keluar,'%d-%m-%Y') tgl_pulang, "
                        + "ki.stts_pulang, p.no_rkm_medis no_rm, p.nm_pasien, b.nm_bangsal, pj.png_jawab cr_byr, "
                        + "py.kd_penyakit icd_10, py.ciri_ciri desk_diagnosa, if(dp.prioritas='1','Primer','Sekunder') status_diag FROM diagnosa_pasien dp "
                        + "INNER JOIN penyakit py on py.kd_penyakit=dp.kd_penyakit "
                        + "INNER JOIN reg_periksa rp on rp.no_rawat=dp.no_rawat "
                        + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                        + "INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj "
                        + "INNER JOIN kamar_inap ki on ki.no_rawat=dp.no_rawat "
                        + "INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar "
                        + "INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal "
                        + "WHERE ki.tgl_keluar BETWEEN ? and ? and ki.stts_pulang not in ('-','pindah kamar') and "
                        + "py.ciri_ciri like ? and dp.kd_penyakit like ? and pj.kd_pj like ? and b.nm_bangsal like ? ORDER BY p.no_rkm_medis, rp.tgl_registrasi, dp.prioritas");
            }

            try {
                ps6.setString(1, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps6.setString(2, Valid.SetTgl(Tgl2.getSelectedItem() + ""));

                if (NmRuangan.getSelectedItem().equals("SEMUA RUANG")) {
                    if (!TCari.getText().trim().equals("")) {
                        ps6.setString(3, "%" + TCari.getText() + "%");
                        ps6.setString(4, "%%");
                        ps6.setString(5, "%%");
                    }                    
                    if (!kdpenyakit.getText().trim().equals("")) {
                        ps6.setString(3, "%%");
                        ps6.setString(4, "%" + kdpenyakit.getText() + "%");
                        ps6.setString(5, "%%");
                    }                    
                    if (!kdpnj.getText().trim().equals("")) {
                        ps6.setString(3, "%%");
                        ps6.setString(4, "%%");
                        ps6.setString(5, "%" + kdpnj.getText() + "%");
                    }                    
                    if (!TCari.getText().trim().equals("") && (!kdpnj.getText().trim().equals(""))) {
                        ps6.setString(3, "%" + TCari.getText() + "%");
                        ps6.setString(4, "%%");
                        ps6.setString(5, "%" + kdpnj.getText() + "%");
                    }                    
                    if (!kdpenyakit.getText().trim().equals("") && (!kdpnj.getText().trim().equals(""))) {
                        ps6.setString(3, "%%");
                        ps6.setString(4, "%" + kdpenyakit.getText() + "%");
                        ps6.setString(5, "%" + kdpnj.getText() + "%");
                    }                    
                    if (TCari.getText().trim().equals("") && (kdpenyakit.getText().trim().equals("") && (kdpnj.getText().trim().equals("")))) {
                        ps6.setString(3, "%%");
                        ps6.setString(4, "%%");
                        ps6.setString(5, "%%");
                    }                 
                }
                
                if (!NmRuangan.getSelectedItem().equals("SEMUA RUANG") || (!ruangDicetak.getText().equals("SEMUA RUANG"))) {
                    if (!TCari.getText().trim().equals("")) {
                        ps6.setString(3, "%" + TCari.getText() + "%");
                        ps6.setString(4, "%%");
                        ps6.setString(5, "%%");
                    }                    
                    if (!kdpenyakit.getText().trim().equals("")) {
                        ps6.setString(3, "%%");
                        ps6.setString(4, "%" + kdpenyakit.getText() + "%");
                        ps6.setString(5, "%%");
                    }                    
                    if (!kdpnj.getText().trim().equals("")) {
                        ps6.setString(3, "%%");
                        ps6.setString(4, "%%");
                        ps6.setString(5, "%" + kdpnj.getText() + "%");
                    }                    
                    if (!TCari.getText().trim().equals("") && (!kdpnj.getText().trim().equals(""))) {
                        ps6.setString(3, "%" + TCari.getText() + "%");
                        ps6.setString(4, "%%");
                        ps6.setString(5, "%" + kdpnj.getText() + "%");
                    }                    
                    if (!kdpenyakit.getText().trim().equals("") && (!kdpnj.getText().trim().equals(""))) {
                        ps6.setString(3, "%%");
                        ps6.setString(4, "%" + kdpenyakit.getText() + "%");
                        ps6.setString(5, "%" + kdpnj.getText() + "%");
                    }                    
                    if (TCari.getText().trim().equals("") && (kdpenyakit.getText().trim().equals("") && (kdpnj.getText().trim().equals("")))) {
                        ps6.setString(3, "%%");
                        ps6.setString(4, "%%");
                        ps6.setString(5, "%%");
                    }
                    ps6.setString(6, "%" + ruangDicetak.getText() + "%");
                }

                rs6 = ps6.executeQuery();
                while (rs6.next()) {
                    tabMode4.addRow(new Object[]{
                        rs6.getString("tgl_masuk"),
                        rs6.getString("tgl_pulang"),
                        rs6.getString("stts_pulang"),
                        rs6.getString("no_rm"),
                        rs6.getString("nm_pasien"),
                        rs6.getString("nm_bangsal"),
                        rs6.getString("cr_byr"),
                        rs6.getString("icd_10"),
                        rs6.getString("desk_diagnosa"),
                        rs6.getString("status_diag")
                    });
                }

            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs6 != null) {
                    rs6.close();
                }
                if (ps6 != null) {
                    ps6.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Catatan  " + e);
        }
        label9.setText("      Record : " + tabMode4.getRowCount());
    }
    
    public void isCek(){
        BtnPrint.setEnabled(akses.getpenyakit_ranap());
    }
    
    public void emptText() {
        userBerizin.setText("");
        kdAkses.setText("");
        ruangDicetak.setText("");
        judulRuangan.setText("");
        jlhPenyakit.setText("10");
        kdpnj.setText("");
    }
    
    public void UserValid() {        
        userBerizin.setText(Sequel.cariIsi("SELECT ifnull(nip,'') FROM hak_akses_unit WHERE nip='" + akses.getkode() + "'"));

        if (akses.getadmin() == true || akses.getpenyakit() == true) {
            btnCari.setEnabled(true);            
            NmRuangan.setSelectedIndex(0);
            NmRuangan.setEnabled(true);
            NmRuangan.setVisible(true);
            cmbRuangKhusus1.setVisible(false);
            cmbRuangKhusus2.setVisible(false);
            cmbRuangKhusus3.setVisible(false);
            cmbRuangKhusus4.setVisible(false);
            prosesCari();
            
        } else if (!userBerizin.getText().equals("") || akses.getadmin() == false) {
            
            if (userBerizin.getText().equals("PR04")) {
                btnCari.setEnabled(true);
                cmbRuangKhusus1.setVisible(true);
                cmbRuangKhusus1.setSelectedIndex(0);
                NmRuangan.setVisible(false);
                cmbRuangKhusus2.setVisible(false);
                cmbRuangKhusus3.setVisible(false);
                cmbRuangKhusus4.setVisible(false);
                kdAkses.setText("");
            
            } else if (userBerizin.getText().equals("PR06")) {
                btnCari.setEnabled(true);
                cmbRuangKhusus2.setVisible(true);
                cmbRuangKhusus2.setSelectedIndex(0);
                NmRuangan.setVisible(false);
                cmbRuangKhusus1.setVisible(false);
                cmbRuangKhusus3.setVisible(false);
                cmbRuangKhusus4.setVisible(false);
                kdAkses.setText("");
            
            } else if (userBerizin.getText().equals("PR10")) {
                btnCari.setEnabled(true);
                cmbRuangKhusus3.setVisible(true);
                cmbRuangKhusus3.setSelectedIndex(0);
                NmRuangan.setVisible(false);
                cmbRuangKhusus1.setVisible(false);
                cmbRuangKhusus2.setVisible(false);
                cmbRuangKhusus4.setVisible(false);
                kdAkses.setText("");
            
            } else if (userBerizin.getText().equals("PR07")) {
                btnCari.setEnabled(true);
                cmbRuangKhusus4.setVisible(true);
                cmbRuangKhusus4.setSelectedIndex(0);
                NmRuangan.setVisible(false);
                cmbRuangKhusus1.setVisible(false);
                cmbRuangKhusus2.setVisible(false);
                cmbRuangKhusus3.setVisible(false);
                kdAkses.setText("");

            } else {
                btnCari.setEnabled(true);
                NmRuangan.setEnabled(false);
                NmRuangan.setVisible(true);
                cmbRuangKhusus1.setVisible(false);
                cmbRuangKhusus2.setVisible(false);
                cmbRuangKhusus3.setVisible(false);
                cmbRuangKhusus4.setVisible(false);                
                
                formWindowOpened(null);
                kdAkses.setText(Sequel.cariIsi("SELECT kode_unit FROM hak_akses_unit WHERE nip='" + akses.getkode() + "'"));
                NmRuangan.setSelectedItem(kdAkses.getText());
                prosesCari();
            }
        }
    }
    
    public void ctkPerRuanganTglKeluar() {
        if (jlhPenyakit.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Silakan isi dulu jumlah besar penyakit yang akan diambil datanya...!!!");
            jlhPenyakit.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("periode", "PERIODE TGL. KELUAR " + Tgl1.getSelectedItem() + " S.D " + Tgl2.getSelectedItem());
            param.put("jlhData", jlhPenyakit.getText());
            param.put("nama_ruangan", ruangDicetak.getText());

            Valid.MyReport("rpDaftar10BesarDiagnosaInap.jasper", "report", "::[ Daftar " + jlhPenyakit.getText() + " Besar Penyakit Terbanyak Per Ruangan Inap Berdasarkan Tgl. Keluar ]::",
                    " SELECT a.kd_penyakit AS KD_ICD_10, a.ciri_ciri AS Deskripsi_Diagnosa, IFNULL(b.qty, 0) LK, ifnull(c.qty, 0) PR, IFNULL(b.qty, 0) + IFNULL(c.qty, 0) Total "
                    + " FROM ((SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, count(*) AS qty "
                    + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                    + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                    + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat "
                    + " LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar "
                    + " LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal "
                    + " LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " WHERE diagnosa_pasien. STATUS = 'Ranap' AND bangsal.nm_gedung = '" + ruangDicetak.getText() + "' AND bangsal.nm_bangsal NOT LIKE '%apotek%' "
                    + " AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                    + " AND kamar_inap.tgl_keluar BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' GROUP BY diagnosa_pasien.kd_penyakit ORDER BY qty DESC) AS a "
                    + " LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, count(*) AS qty "
                    + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                    + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar "
                    + " LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " WHERE diagnosa_pasien. STATUS = 'Ranap' AND bangsal.nm_gedung = '" + ruangDicetak.getText() + "' AND bangsal.nm_bangsal NOT LIKE '%apotek%' "
                    + " AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                    + " AND kamar_inap.tgl_keluar BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND pasien.jk = 'L' GROUP BY diagnosa_pasien.kd_penyakit "
                    + " ORDER BY qty DESC) AS b ON b.kd_penyakit = a.kd_penyakit LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, count(*) AS qty "
                    + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                    + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar "
                    + " LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " WHERE diagnosa_pasien. STATUS = 'Ranap' AND bangsal.nm_gedung = '" + ruangDicetak.getText() + "' AND bangsal.nm_bangsal NOT LIKE '%apotek%' "
                    + " AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                    + " AND kamar_inap.tgl_keluar BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND pasien.jk = 'P' GROUP BY diagnosa_pasien.kd_penyakit "
                    + " ORDER BY qty DESC) AS c ON c.kd_penyakit = a.kd_penyakit) LIMIT " + jlhPenyakit.getText() + "", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }
    
    public void ctkRuangArRaudah() {
        if (jlhPenyakit.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Silakan isi dulu jumlah besar penyakit yang akan diambil datanya...!!!");
            jlhPenyakit.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("periode", "PERIODE TGL. MASUK " + Tgl1.getSelectedItem() + " S.D " + Tgl2.getSelectedItem());
            param.put("jlhData", jlhPenyakit.getText());
            if (ruangDicetak.getText().equals("AR-RAUDAH")) {
                param.put("nama_ruangan", "AR-RAUDAH");
            } else if (ruangDicetak.getText().equals("AR-RAUDAH ATAS")) {
                param.put("nama_ruangan", "AR-RAUDAH ATAS (MATA, THT, KULKEL)");
            } else if (ruangDicetak.getText().equals("AR-RAUDAH BAWAH")) {
                param.put("nama_ruangan", "AR-RAUDAH BAWAH (SARAF)");
            }
           
            if (ruangDicetak.getText().equals("AR-RAUDAH")) {
                Valid.MyReport("rpDaftar10BesarDiagnosaInap.jasper", "report", "::[ Daftar " + jlhPenyakit.getText() + " Besar Penyakit Terbanyak Per Ruangan Inap Berdasarkan Tgl. Masuk ]::",
                        " SELECT a.kd_penyakit AS KD_ICD_10, a.ciri_ciri AS Deskripsi_Diagnosa, IFNULL(b.qty, 0) LK, ifnull(c.qty, 0) PR, IFNULL(b.qty, 0) + IFNULL(c.qty, 0) Total "
                        + " FROM ((SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, count(*) AS qty "
                        + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                        + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                        + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat "
                        + " LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar "
                        + " LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal "
                        + " LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                        + " WHERE diagnosa_pasien.STATUS = 'Ranap' AND bangsal.nm_gedung = '" + ruangDicetak.getText() + "' AND bangsal.nm_bangsal NOT LIKE '%apotek%' "
                        + " AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                        + " AND kamar_inap.tgl_masuk BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' GROUP BY diagnosa_pasien.kd_penyakit ORDER BY qty DESC) AS a "
                        + " LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, count(*) AS qty "
                        + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                        + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar "
                        + " LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                        + " WHERE diagnosa_pasien.STATUS = 'Ranap' AND bangsal.nm_gedung = '" + ruangDicetak.getText() + "' AND bangsal.nm_bangsal NOT LIKE '%apotek%' "
                        + " AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                        + " AND kamar_inap.tgl_masuk BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND pasien.jk = 'L' GROUP BY diagnosa_pasien.kd_penyakit "
                        + " ORDER BY qty DESC) AS b ON b.kd_penyakit = a.kd_penyakit LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, count(*) AS qty "
                        + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                        + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar "
                        + " LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                        + " WHERE diagnosa_pasien.STATUS = 'Ranap' AND bangsal.nm_gedung = '" + ruangDicetak.getText() + "' AND bangsal.nm_bangsal NOT LIKE '%apotek%' "
                        + " AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                        + " AND kamar_inap.tgl_masuk BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND pasien.jk = 'P' GROUP BY diagnosa_pasien.kd_penyakit "
                        + " ORDER BY qty DESC) AS c ON c.kd_penyakit = a.kd_penyakit) LIMIT " + jlhPenyakit.getText() + "", param);
                
            } else if (ruangDicetak.getText().equals("AR-RAUDAH ATAS")) {
                Valid.MyReport("rpDaftar10BesarDiagnosaInap.jasper", "report", "::[ Daftar " + jlhPenyakit.getText() + " Besar Penyakit Terbanyak Per Ruangan Inap Berdasarkan Tgl. Masuk ]::",
                        " SELECT a.kd_penyakit AS KD_ICD_10, a.ciri_ciri AS Deskripsi_Diagnosa, IFNULL(b.qty, 0) LK, ifnull(c.qty, 0) PR, IFNULL(b.qty, 0) + IFNULL(c.qty, 0) Total "
                        + " FROM ((SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, count(*) AS qty "
                        + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                        + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                        + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat "
                        + " LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar "
                        + " LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal "
                        + " LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                        + " WHERE diagnosa_pasien.STATUS = 'Ranap' AND bangsal.nm_bangsal like '%Ar-Raudah/Atas%' AND bangsal.nm_bangsal NOT LIKE '%apotek%' "
                        + " AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                        + " AND kamar_inap.tgl_masuk BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' GROUP BY diagnosa_pasien.kd_penyakit ORDER BY qty DESC) AS a "
                        + " LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, count(*) AS qty "
                        + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                        + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar "
                        + " LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                        + " WHERE diagnosa_pasien.STATUS = 'Ranap' AND bangsal.nm_bangsal like '%Ar-Raudah/Atas%' AND bangsal.nm_bangsal NOT LIKE '%apotek%' "
                        + " AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                        + " AND kamar_inap.tgl_masuk BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND pasien.jk = 'L' GROUP BY diagnosa_pasien.kd_penyakit "
                        + " ORDER BY qty DESC) AS b ON b.kd_penyakit = a.kd_penyakit LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, count(*) AS qty "
                        + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                        + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar "
                        + " LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                        + " WHERE diagnosa_pasien.STATUS = 'Ranap' AND bangsal.nm_bangsal like '%Ar-Raudah/Atas%' AND bangsal.nm_bangsal NOT LIKE '%apotek%' "
                        + " AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                        + " AND kamar_inap.tgl_masuk BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND pasien.jk = 'P' GROUP BY diagnosa_pasien.kd_penyakit "
                        + " ORDER BY qty DESC) AS c ON c.kd_penyakit = a.kd_penyakit) LIMIT " + jlhPenyakit.getText() + "", param);

            } else if (ruangDicetak.getText().equals("AR-RAUDAH BAWAH")) {
                Valid.MyReport("rpDaftar10BesarDiagnosaInap.jasper", "report", "::[ Daftar " + jlhPenyakit.getText() + " Besar Penyakit Terbanyak Per Ruangan Inap Berdasarkan Tgl. Masuk ]::",
                        " SELECT a.kd_penyakit AS KD_ICD_10, a.ciri_ciri AS Deskripsi_Diagnosa, IFNULL(b.qty, 0) LK, ifnull(c.qty, 0) PR, IFNULL(b.qty, 0) + IFNULL(c.qty, 0) Total "
                        + " FROM ((SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, count(*) AS qty "
                        + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                        + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                        + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat "
                        + " LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar "
                        + " LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal "
                        + " LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                        + " WHERE diagnosa_pasien.STATUS = 'Ranap' AND bangsal.nm_bangsal like '%Ar-Raudah/Bawah%' AND bangsal.nm_bangsal NOT LIKE '%apotek%' "
                        + " AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                        + " AND kamar_inap.tgl_masuk BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' GROUP BY diagnosa_pasien.kd_penyakit ORDER BY qty DESC) AS a "
                        + " LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, count(*) AS qty "
                        + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                        + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar "
                        + " LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                        + " WHERE diagnosa_pasien.STATUS = 'Ranap' AND bangsal.nm_bangsal like '%Ar-Raudah/Bawah%' AND bangsal.nm_bangsal NOT LIKE '%apotek%' "
                        + " AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                        + " AND kamar_inap.tgl_masuk BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND pasien.jk = 'L' GROUP BY diagnosa_pasien.kd_penyakit "
                        + " ORDER BY qty DESC) AS b ON b.kd_penyakit = a.kd_penyakit LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, count(*) AS qty "
                        + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                        + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar "
                        + " LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                        + " WHERE diagnosa_pasien.STATUS = 'Ranap' AND bangsal.nm_bangsal like '%Ar-Raudah/Bawah%' AND bangsal.nm_bangsal NOT LIKE '%apotek%' "
                        + " AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                        + " AND kamar_inap.tgl_masuk BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND pasien.jk = 'P' GROUP BY diagnosa_pasien.kd_penyakit "
                        + " ORDER BY qty DESC) AS c ON c.kd_penyakit = a.kd_penyakit) LIMIT " + jlhPenyakit.getText() + "", param);
            }
            this.setCursor(Cursor.getDefaultCursor());
        }
    }
    
    public void ctkRuangAsSami() {
        if (jlhPenyakit.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Silakan isi dulu jumlah besar penyakit yang akan diambil datanya...!!!");
            jlhPenyakit.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("periode", "PERIODE TGL. MASUK " + Tgl1.getSelectedItem() + " S.D " + Tgl2.getSelectedItem());
            param.put("jlhData", jlhPenyakit.getText());
            if (ruangDicetak.getText().equals("AS-SAMI/1")) {
                param.put("nama_ruangan", "AS-SAMI/JANTUNG DAN LAINNYA");
            } else if (ruangDicetak.getText().equals("AS-SAMI/2")) {
                param.put("nama_ruangan", "AS-SAMI/KEMOTERAPI");
            }

            Valid.MyReport("rpDaftar10BesarDiagnosaInap.jasper", "report", "::[ Daftar " + jlhPenyakit.getText() + " Besar Penyakit Terbanyak Per Ruangan Inap Berdasarkan Tgl. Masuk ]::",
                    " SELECT a.kd_penyakit AS KD_ICD_10, a.ciri_ciri AS Deskripsi_Diagnosa, IFNULL(b.qty, 0) LK, ifnull(c.qty, 0) PR, IFNULL(b.qty, 0) + IFNULL(c.qty, 0) Total "
                    + " FROM ((SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, count(*) AS qty "
                    + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                    + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                    + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat "
                    + " LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar "
                    + " LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal "
                    + " LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " WHERE diagnosa_pasien.STATUS = 'Ranap' AND bangsal.nm_bangsal like '%" + ruangDicetak.getText() + "%' AND bangsal.nm_bangsal NOT LIKE '%apotek%' "
                    + " AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                    + " AND kamar_inap.tgl_masuk BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' GROUP BY diagnosa_pasien.kd_penyakit ORDER BY qty DESC) AS a "
                    + " LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, count(*) AS qty "
                    + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                    + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar "
                    + " LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " WHERE diagnosa_pasien.STATUS = 'Ranap' AND bangsal.nm_bangsal like '%" + ruangDicetak.getText() + "%' AND bangsal.nm_bangsal NOT LIKE '%apotek%' "
                    + " AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                    + " AND kamar_inap.tgl_masuk BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND pasien.jk = 'L' GROUP BY diagnosa_pasien.kd_penyakit "
                    + " ORDER BY qty DESC) AS b ON b.kd_penyakit = a.kd_penyakit LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, count(*) AS qty "
                    + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                    + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar "
                    + " LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " WHERE diagnosa_pasien.STATUS = 'Ranap' AND bangsal.nm_bangsal like '%" + ruangDicetak.getText() + "%' AND bangsal.nm_bangsal NOT LIKE '%apotek%' "
                    + " AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                    + " AND kamar_inap.tgl_masuk BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND pasien.jk = 'P' GROUP BY diagnosa_pasien.kd_penyakit "
                    + " ORDER BY qty DESC) AS c ON c.kd_penyakit = a.kd_penyakit) LIMIT " + jlhPenyakit.getText() + "", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }
    
    public void ctkPerRuanganTglMasuk() {
        if (jlhPenyakit.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Silakan isi dulu jumlah besar penyakit yang akan diambil datanya...!!!");
            jlhPenyakit.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("periode", "PERIODE TGL. MASUK " + Tgl1.getSelectedItem() + " S.D " + Tgl2.getSelectedItem());
            param.put("jlhData", jlhPenyakit.getText());
            param.put("nama_ruangan", ruangDicetak.getText());

            Valid.MyReport("rpDaftar10BesarDiagnosaInap.jasper", "report", "::[ Daftar " + jlhPenyakit.getText() + " Besar Penyakit Terbanyak Per Ruangan Inap Berdasarkan Tgl. Masuk ]::",
                    " SELECT a.kd_penyakit AS KD_ICD_10, a.ciri_ciri AS Deskripsi_Diagnosa, IFNULL(b.qty, 0) LK, ifnull(c.qty, 0) PR, IFNULL(b.qty, 0) + IFNULL(c.qty, 0) Total "
                    + " FROM ((SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, count(*) AS qty "
                    + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                    + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                    + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat "
                    + " LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar "
                    + " LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal "
                    + " LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " WHERE diagnosa_pasien.STATUS = 'Ranap' AND bangsal.nm_gedung = '" + ruangDicetak.getText() + "' AND bangsal.nm_bangsal NOT LIKE '%apotek%' "
                    + " AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                    + " AND kamar_inap.tgl_masuk BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' GROUP BY diagnosa_pasien.kd_penyakit ORDER BY qty DESC) AS a "
                    + " LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, count(*) AS qty "
                    + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                    + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar "
                    + " LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " WHERE diagnosa_pasien.STATUS = 'Ranap' AND bangsal.nm_gedung = '" + ruangDicetak.getText() + "' AND bangsal.nm_bangsal NOT LIKE '%apotek%' "
                    + " AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                    + " AND kamar_inap.tgl_masuk BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND pasien.jk = 'L' GROUP BY diagnosa_pasien.kd_penyakit "
                    + " ORDER BY qty DESC) AS b ON b.kd_penyakit = a.kd_penyakit LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, count(*) AS qty "
                    + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                    + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar "
                    + " LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " WHERE diagnosa_pasien.STATUS = 'Ranap' AND bangsal.nm_gedung = '" + ruangDicetak.getText() + "' AND bangsal.nm_bangsal NOT LIKE '%apotek%' "
                    + " AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                    + " AND kamar_inap.tgl_masuk BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND pasien.jk = 'P' GROUP BY diagnosa_pasien.kd_penyakit "
                    + " ORDER BY qty DESC) AS c ON c.kd_penyakit = a.kd_penyakit) LIMIT " + jlhPenyakit.getText() + "", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }
    
    public void ctkRuangArRaudahTglKeluar() {
        if (jlhPenyakit.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Silakan isi dulu jumlah besar penyakit yang akan diambil datanya...!!!");
            jlhPenyakit.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("periode", "PERIODE TGL. KELUAR " + Tgl1.getSelectedItem() + " S.D " + Tgl2.getSelectedItem());
            param.put("jlhData", jlhPenyakit.getText());
            if (ruangDicetak.getText().equals("AR-RAUDAH")) {
                param.put("nama_ruangan", "AR-RAUDAH");
            } else if (ruangDicetak.getText().equals("AR-RAUDAH ATAS")) {
                param.put("nama_ruangan", "AR-RAUDAH ATAS (MATA, THT, KULKEL)");
            } else if (ruangDicetak.getText().equals("AR-RAUDAH BAWAH")) {
                param.put("nama_ruangan", "AR-RAUDAH BAWAH (SARAF)");
            }

            if (ruangDicetak.getText().equals("AR-RAUDAH")) {
                Valid.MyReport("rpDaftar10BesarDiagnosaInap.jasper", "report", "::[ Daftar " + jlhPenyakit.getText() + " Besar Penyakit Terbanyak Per Ruangan Inap Berdasarkan Tgl. Keluar ]::",
                        " SELECT a.kd_penyakit AS KD_ICD_10, a.ciri_ciri AS Deskripsi_Diagnosa, IFNULL(b.qty, 0) LK, ifnull(c.qty, 0) PR, IFNULL(b.qty, 0) + IFNULL(c.qty, 0) Total "
                        + " FROM ((SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, count(*) AS qty "
                        + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                        + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                        + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat "
                        + " LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar "
                        + " LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal "
                        + " LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                        + " WHERE bangsal.nm_gedung = '" + ruangDicetak.getText() + "' and diagnosa_pasien.STATUS = 'Ranap' AND bangsal.nm_bangsal NOT LIKE '%apotek%' "
                        + " AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                        + " AND kamar_inap.tgl_keluar BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' GROUP BY diagnosa_pasien.kd_penyakit ORDER BY qty DESC) AS a "
                        + " LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, count(*) AS qty "
                        + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                        + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar "
                        + " LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                        + " WHERE bangsal.nm_gedung = '" + ruangDicetak.getText() + "' and diagnosa_pasien.STATUS = 'Ranap' AND bangsal.nm_bangsal NOT LIKE '%apotek%' "
                        + " AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                        + " AND kamar_inap.tgl_keluar BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND pasien.jk = 'L' GROUP BY diagnosa_pasien.kd_penyakit "
                        + " ORDER BY qty DESC) AS b ON b.kd_penyakit = a.kd_penyakit LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, count(*) AS qty "
                        + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                        + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar "
                        + " LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                        + " WHERE bangsal.nm_gedung = '" + ruangDicetak.getText() + "' and diagnosa_pasien. STATUS = 'Ranap' AND bangsal.nm_bangsal NOT LIKE '%apotek%' "
                        + " AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                        + " AND kamar_inap.tgl_keluar BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + " AND pasien.jk = 'P' GROUP BY diagnosa_pasien.kd_penyakit ORDER BY qty DESC) AS c ON c.kd_penyakit = a.kd_penyakit) LIMIT " + jlhPenyakit.getText() + "", param);
              
            } else if (ruangDicetak.getText().equals("AR-RAUDAH ATAS")) {
                Valid.MyReport("rpDaftar10BesarDiagnosaInap.jasper", "report", "::[ Daftar " + jlhPenyakit.getText() + " Besar Penyakit Terbanyak Per Ruangan Inap Berdasarkan Tgl. Keluar ]::",
                        " SELECT a.kd_penyakit AS KD_ICD_10, a.ciri_ciri AS Deskripsi_Diagnosa, IFNULL(b.qty, 0) LK, ifnull(c.qty, 0) PR, IFNULL(b.qty, 0) + IFNULL(c.qty, 0) Total "
                        + " FROM ((SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, count(*) AS qty "
                        + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                        + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                        + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat "
                        + " LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar "
                        + " LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal "
                        + " LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                        + " WHERE bangsal.nm_bangsal like '%Ar-Raudah/Atas%' and diagnosa_pasien.STATUS = 'Ranap' AND bangsal.nm_bangsal NOT LIKE '%apotek%' "
                        + " AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                        + " AND kamar_inap.tgl_keluar BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' GROUP BY diagnosa_pasien.kd_penyakit ORDER BY qty DESC) AS a "
                        + " LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, count(*) AS qty "
                        + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                        + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar "
                        + " LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                        + " WHERE bangsal.nm_bangsal like '%Ar-Raudah/Atas%' and diagnosa_pasien.STATUS = 'Ranap' AND bangsal.nm_bangsal NOT LIKE '%apotek%' "
                        + " AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                        + " AND kamar_inap.tgl_keluar BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND pasien.jk = 'L' GROUP BY diagnosa_pasien.kd_penyakit "
                        + " ORDER BY qty DESC) AS b ON b.kd_penyakit = a.kd_penyakit LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, count(*) AS qty "
                        + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                        + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar "
                        + " LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                        + " WHERE bangsal.nm_bangsal like '%Ar-Raudah/Atas%' and diagnosa_pasien. STATUS = 'Ranap' AND bangsal.nm_bangsal NOT LIKE '%apotek%' "
                        + " AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                        + " AND kamar_inap.tgl_keluar BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + " AND pasien.jk = 'P' GROUP BY diagnosa_pasien.kd_penyakit ORDER BY qty DESC) AS c ON c.kd_penyakit = a.kd_penyakit) LIMIT " + jlhPenyakit.getText() + "", param);
           
            } else if (ruangDicetak.getText().equals("AR-RAUDAH BAWAH")) {
                Valid.MyReport("rpDaftar10BesarDiagnosaInap.jasper", "report", "::[ Daftar " + jlhPenyakit.getText() + " Besar Penyakit Terbanyak Per Ruangan Inap Berdasarkan Tgl. Keluar ]::",
                        " SELECT a.kd_penyakit AS KD_ICD_10, a.ciri_ciri AS Deskripsi_Diagnosa, IFNULL(b.qty, 0) LK, ifnull(c.qty, 0) PR, IFNULL(b.qty, 0) + IFNULL(c.qty, 0) Total "
                        + " FROM ((SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, count(*) AS qty "
                        + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                        + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                        + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat "
                        + " LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar "
                        + " LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal "
                        + " LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                        + " WHERE bangsal.nm_bangsal like '%Ar-Raudah/Bawah%' and diagnosa_pasien.STATUS = 'Ranap' AND bangsal.nm_bangsal NOT LIKE '%apotek%' "
                        + " AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                        + " AND kamar_inap.tgl_keluar BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' GROUP BY diagnosa_pasien.kd_penyakit ORDER BY qty DESC) AS a "
                        + " LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, count(*) AS qty "
                        + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                        + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar "
                        + " LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                        + " WHERE bangsal.nm_bangsal like '%Ar-Raudah/Bawah%' and diagnosa_pasien.STATUS = 'Ranap' AND bangsal.nm_bangsal NOT LIKE '%apotek%' "
                        + " AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                        + " AND kamar_inap.tgl_keluar BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND pasien.jk = 'L' GROUP BY diagnosa_pasien.kd_penyakit "
                        + " ORDER BY qty DESC) AS b ON b.kd_penyakit = a.kd_penyakit LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, count(*) AS qty "
                        + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                        + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar "
                        + " LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                        + " WHERE bangsal.nm_bangsal like '%Ar-Raudah/Bawah%' and diagnosa_pasien. STATUS = 'Ranap' AND bangsal.nm_bangsal NOT LIKE '%apotek%' "
                        + " AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                        + " AND kamar_inap.tgl_keluar BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + " AND pasien.jk = 'P' GROUP BY diagnosa_pasien.kd_penyakit ORDER BY qty DESC) AS c ON c.kd_penyakit = a.kd_penyakit) LIMIT " + jlhPenyakit.getText() + "", param);
            }
            this.setCursor(Cursor.getDefaultCursor());
        }
    }
    
    public void ctkRuangAsSamiTglKeluar() {
        if (jlhPenyakit.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Silakan isi dulu jumlah besar penyakit yang akan diambil datanya...!!!");
            jlhPenyakit.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("periode", "PERIODE TGL. KELUAR " + Tgl1.getSelectedItem() + " S.D " + Tgl2.getSelectedItem());
            param.put("jlhData", jlhPenyakit.getText());
            if (ruangDicetak.getText().equals("AS-SAMI/1")) {
                param.put("nama_ruangan", "AS-SAMI/JANTUNG DAN LAINNYA");
            } else if (ruangDicetak.getText().equals("AS-SAMI/2")) {
                param.put("nama_ruangan", "AS-SAMI/KEMOTERAPI");
            }

            Valid.MyReport("rpDaftar10BesarDiagnosaInap.jasper", "report", "::[ Daftar " + jlhPenyakit.getText() + " Besar Penyakit Terbanyak Per Ruangan Inap Berdasarkan Tgl. Keluar ]::",
                    " SELECT a.kd_penyakit AS KD_ICD_10, a.ciri_ciri AS Deskripsi_Diagnosa, IFNULL(b.qty, 0) LK, ifnull(c.qty, 0) PR, IFNULL(b.qty, 0) + IFNULL(c.qty, 0) Total "
                    + " FROM ((SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, count(*) AS qty "
                    + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                    + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                    + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat "
                    + " LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar "
                    + " LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal "
                    + " LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " WHERE bangsal.nm_bangsal like '%" + ruangDicetak.getText() + "%' and diagnosa_pasien.STATUS = 'Ranap' AND bangsal.nm_bangsal NOT LIKE '%apotek%' "
                    + " AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                    + " AND kamar_inap.tgl_keluar BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' GROUP BY diagnosa_pasien.kd_penyakit ORDER BY qty DESC) AS a "
                    + " LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, count(*) AS qty "
                    + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                    + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar "
                    + " LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " WHERE bangsal.nm_bangsal like '%" + ruangDicetak.getText() + "%' and diagnosa_pasien.STATUS = 'Ranap' AND bangsal.nm_bangsal NOT LIKE '%apotek%' "
                    + " AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                    + " AND kamar_inap.tgl_keluar BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND pasien.jk = 'L' GROUP BY diagnosa_pasien.kd_penyakit "
                    + " ORDER BY qty DESC) AS b ON b.kd_penyakit = a.kd_penyakit LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, count(*) AS qty "
                    + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                    + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar "
                    + " LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " WHERE bangsal.nm_bangsal like '%" + ruangDicetak.getText() + "%' and diagnosa_pasien. STATUS = 'Ranap' AND bangsal.nm_bangsal NOT LIKE '%apotek%' "
                    + " AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                    + " AND kamar_inap.tgl_keluar BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                    + " AND pasien.jk = 'P' GROUP BY diagnosa_pasien.kd_penyakit ORDER BY qty DESC) AS c ON c.kd_penyakit = a.kd_penyakit) LIMIT " + jlhPenyakit.getText() + "", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }
    
    public void ctkRuangRKPDzaalTglKeluar() {
        if (jlhPenyakit.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Silakan isi dulu jumlah besar penyakit yang akan diambil datanya...!!!");
            jlhPenyakit.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("periode", "PERIODE TGL. KELUAR " + Tgl1.getSelectedItem() + " S.D " + Tgl2.getSelectedItem());
            param.put("jlhData", jlhPenyakit.getText());
            param.put("nama_ruangan", ruangDicetak.getText());

            Valid.MyReport("rpDaftar10BesarDiagnosaInap.jasper", "report", "::[ Daftar " + jlhPenyakit.getText() + " Besar Penyakit Terbanyak Per Ruangan Inap Berdasarkan Tgl. Keluar ]::",
                    " SELECT a.kd_penyakit AS KD_ICD_10, a.ciri_ciri AS Deskripsi_Diagnosa, IFNULL(b.qty, 0) LK, ifnull(c.qty, 0) PR, IFNULL(b.qty, 0) + IFNULL(c.qty, 0) Total "
                    + " FROM ((SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, count(*) AS qty "
                    + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                    + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                    + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat "
                    + " LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar "
                    + " LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal "
                    + " LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " WHERE bangsal.nm_gedung in ('RKPD','ZAAL') and diagnosa_pasien.STATUS = 'Ranap' AND bangsal.nm_bangsal NOT LIKE '%apotek%' "
                    + " AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                    + " AND kamar_inap.tgl_keluar BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' GROUP BY diagnosa_pasien.kd_penyakit ORDER BY qty DESC) AS a "
                    + " LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, count(*) AS qty "
                    + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                    + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar "
                    + " LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " WHERE bangsal.nm_gedung in ('RKPD','ZAAL') and diagnosa_pasien.STATUS = 'Ranap' AND bangsal.nm_bangsal NOT LIKE '%apotek%' "
                    + " AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                    + " AND kamar_inap.tgl_keluar BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND pasien.jk = 'L' GROUP BY diagnosa_pasien.kd_penyakit "
                    + " ORDER BY qty DESC) AS b ON b.kd_penyakit = a.kd_penyakit LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, count(*) AS qty "
                    + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                    + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar "
                    + " LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " WHERE bangsal.nm_gedung in ('RKPD','ZAAL') and diagnosa_pasien. STATUS = 'Ranap' AND bangsal.nm_bangsal NOT LIKE '%apotek%' "
                    + " AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                    + " AND kamar_inap.tgl_keluar BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                    + " AND pasien.jk = 'P' GROUP BY diagnosa_pasien.kd_penyakit ORDER BY qty DESC) AS c ON c.kd_penyakit = a.kd_penyakit) LIMIT " + jlhPenyakit.getText() + "", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }
    
    public void ctkRuangRKPDzaalTglMasuk() {
        if (jlhPenyakit.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Silakan isi dulu jumlah besar penyakit yang akan diambil datanya...!!!");
            jlhPenyakit.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("periode", "PERIODE TGL. MASUK " + Tgl1.getSelectedItem() + " S.D " + Tgl2.getSelectedItem());
            param.put("jlhData", jlhPenyakit.getText());
            param.put("nama_ruangan", ruangDicetak.getText());           

            Valid.MyReport("rpDaftar10BesarDiagnosaInap.jasper", "report", "::[ Daftar " + jlhPenyakit.getText() + " Besar Penyakit Terbanyak Per Ruangan Inap Berdasarkan Tgl. Masuk ]::",
                    "SELECT a.kd_penyakit AS KD_ICD_10, a.ciri_ciri AS Deskripsi_Diagnosa, IFNULL(b.qty, 0) LK, ifnull(c.qty, 0) PR, IFNULL(b.qty, 0) + IFNULL(c.qty, 0) Total "
                    + "FROM ((SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, count(*) AS qty "
                    + "FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                    + "LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                    + "LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat "
                    + "LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar "
                    + "LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal "
                    + "LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + "WHERE bangsal.nm_gedung in ('RKPD','ZAAL') and diagnosa_pasien.STATUS = 'Ranap' AND bangsal.nm_bangsal NOT LIKE '%apotek%' "
                    + "AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                    + "AND kamar_inap.tgl_masuk BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' GROUP BY diagnosa_pasien.kd_penyakit ORDER BY qty DESC) AS a "
                    + "LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, count(*) AS qty "
                    + "FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                    + "LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar "
                    + "LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + "WHERE bangsal.nm_gedung in ('RKPD','ZAAL') and diagnosa_pasien.STATUS = 'Ranap' AND bangsal.nm_bangsal NOT LIKE '%apotek%' "
                    + "AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                    + "AND kamar_inap.tgl_masuk BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND pasien.jk = 'L' GROUP BY diagnosa_pasien.kd_penyakit "
                    + "ORDER BY qty DESC) AS b ON b.kd_penyakit = a.kd_penyakit LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, count(*) AS qty "
                    + "FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                    + "LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar "
                    + "LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + "WHERE bangsal.nm_gedung in ('RKPD','ZAAL') and diagnosa_pasien.STATUS = 'Ranap' AND bangsal.nm_bangsal NOT LIKE '%apotek%' "
                    + "AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                    + "AND kamar_inap.tgl_masuk BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND pasien.jk = 'P' GROUP BY diagnosa_pasien.kd_penyakit "
                    + "ORDER BY qty DESC) AS c ON c.kd_penyakit = a.kd_penyakit) LIMIT " + jlhPenyakit.getText() + "", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }
    
    public void ctkPerRuangArRaudahMatiTglKeluar() {
        if (jlhPenyakit.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Silakan isi dulu jumlah besar penyakit yang akan diambil datanya...!!!");
            jlhPenyakit.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("periode", "PERIODE TGL. KELUAR " + Tgl1.getSelectedItem() + " S.D " + Tgl2.getSelectedItem());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("jlhData", jlhPenyakit.getText());
            if (ruangDicetak.getText().equals("AR-RAUDAH")) {
                param.put("nama_ruangan", "AR-RAUDAH");
            } else if (ruangDicetak.getText().equals("AR-RAUDAH ATAS")) {
                param.put("nama_ruangan", "AR-RAUDAH ATAS (MATA, THT, KULKEL)");
            } else if (ruangDicetak.getText().equals("AR-RAUDAH BAWAH")) {
                param.put("nama_ruangan", "AR-RAUDAH BAWAH (SARAF)");
            }

            if (ruangDicetak.getText().equals("AR-RAUDAH")) {
                Valid.MyReport("rpDaftar10BesarDiagnosaInapMati.jasper", "report", "::[ Daftar " + jlhPenyakit.getText() + " Besar Penyakit Terbanyak Penyebab Kematian Per Ruangan Inap Berdasarkan Tgl. Keluar ]::",
                        " SELECT a.kd_penyakit AS KD_ICD_10, a.ciri_ciri AS Deskripsi_Diagnosa, IFNULL(b.qty, 0) LK, ifnull(c.qty, 0) PR, IFNULL(b.qty, 0) + IFNULL(c.qty, 0) Total "
                        + " FROM ((SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, count(*) AS qty "
                        + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                        + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                        + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat "
                        + " LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal "
                        + " LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis  "
                        + " WHERE diagnosa_pasien. STATUS = 'Ranap' AND bangsal.nm_gedung = '" + ruangDicetak.getText() + "' "
                        + " AND bangsal.nm_bangsal NOT LIKE '%apotek%' AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 "
                        + " AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%'  "
                        + " AND kamar_inap.tgl_keluar BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + " AND kamar_inap.stts_pulang like 'Meninggal%' GROUP BY diagnosa_pasien.kd_penyakit ORDER BY qty DESC) AS a "
                        + " LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, count(*) AS qty	"
                        + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                        + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                        + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar "
                        + " LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                        + " WHERE diagnosa_pasien. STATUS = 'Ranap' AND bangsal.nm_gedung = '" + ruangDicetak.getText() + "' "
                        + " AND bangsal.nm_bangsal NOT LIKE '%apotek%' AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 "
                        + " AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%'  "
                        + " AND kamar_inap.tgl_keluar BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + " AND kamar_inap.stts_pulang like 'Meninggal%' AND pasien.jk = 'L' GROUP BY diagnosa_pasien.kd_penyakit "
                        + " ORDER BY qty DESC) AS b ON b.kd_penyakit = a.kd_penyakit LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, "
                        + " count(*) AS qty FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                        + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat "
                        + " LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal "
                        + " LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis WHERE diagnosa_pasien. STATUS = 'Ranap' "
                        + " AND bangsal.nm_gedung = '" + ruangDicetak.getText() + "' AND bangsal.nm_bangsal NOT LIKE '%apotek%' "
                        + " AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                        + " AND kamar_inap.tgl_keluar BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + " AND kamar_inap.stts_pulang like 'Meninggal%' AND pasien.jk = 'P' GROUP BY diagnosa_pasien.kd_penyakit "
                        + " ORDER BY qty DESC) AS c ON c.kd_penyakit = a.kd_penyakit) LIMIT " + jlhPenyakit.getText() + "", param);
                
            } else if (ruangDicetak.getText().equals("AR-RAUDAH ATAS")) {
                Valid.MyReport("rpDaftar10BesarDiagnosaInapMati.jasper", "report", "::[ Daftar " + jlhPenyakit.getText() + " Besar Penyakit Terbanyak Penyebab Kematian Per Ruangan Inap Berdasarkan Tgl. Keluar ]::",
                        " SELECT a.kd_penyakit AS KD_ICD_10, a.ciri_ciri AS Deskripsi_Diagnosa, IFNULL(b.qty, 0) LK, ifnull(c.qty, 0) PR, IFNULL(b.qty, 0) + IFNULL(c.qty, 0) Total "
                        + " FROM ((SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, count(*) AS qty "
                        + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                        + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                        + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat "
                        + " LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal "
                        + " LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis  "
                        + " WHERE diagnosa_pasien. STATUS = 'Ranap' AND bangsal.nm_bangsal like '%Ar-Raudah/Atas%' "
                        + " AND bangsal.nm_bangsal NOT LIKE '%apotek%' AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 "
                        + " AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%'  "
                        + " AND kamar_inap.tgl_keluar BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + " AND kamar_inap.stts_pulang like 'Meninggal%' GROUP BY diagnosa_pasien.kd_penyakit ORDER BY qty DESC) AS a "
                        + " LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, count(*) AS qty	"
                        + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                        + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                        + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar "
                        + " LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                        + " WHERE diagnosa_pasien. STATUS = 'Ranap' AND bangsal.nm_bangsal like '%Ar-Raudah/Atas%' "
                        + " AND bangsal.nm_bangsal NOT LIKE '%apotek%' AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 "
                        + " AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%'  "
                        + " AND kamar_inap.tgl_keluar BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + " AND kamar_inap.stts_pulang like 'Meninggal%' AND pasien.jk = 'L' GROUP BY diagnosa_pasien.kd_penyakit "
                        + " ORDER BY qty DESC) AS b ON b.kd_penyakit = a.kd_penyakit LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, "
                        + " count(*) AS qty FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                        + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat "
                        + " LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal "
                        + " LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis WHERE diagnosa_pasien. STATUS = 'Ranap' "
                        + " AND bangsal.nm_bangsal like '%Ar-Raudah/Atas%' AND bangsal.nm_bangsal NOT LIKE '%apotek%' "
                        + " AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                        + " AND kamar_inap.tgl_keluar BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + " AND kamar_inap.stts_pulang like 'Meninggal%' AND pasien.jk = 'P' GROUP BY diagnosa_pasien.kd_penyakit "
                        + " ORDER BY qty DESC) AS c ON c.kd_penyakit = a.kd_penyakit) LIMIT " + jlhPenyakit.getText() + "", param);
                
            } else if (ruangDicetak.getText().equals("AR-RAUDAH BAWAH")) {
                Valid.MyReport("rpDaftar10BesarDiagnosaInapMati.jasper", "report", "::[ Daftar " + jlhPenyakit.getText() + " Besar Penyakit Terbanyak Penyebab Kematian Per Ruangan Inap Berdasarkan Tgl. Keluar ]::",
                        " SELECT a.kd_penyakit AS KD_ICD_10, a.ciri_ciri AS Deskripsi_Diagnosa, IFNULL(b.qty, 0) LK, ifnull(c.qty, 0) PR, IFNULL(b.qty, 0) + IFNULL(c.qty, 0) Total "
                        + " FROM ((SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, count(*) AS qty "
                        + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                        + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                        + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat "
                        + " LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal "
                        + " LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis  "
                        + " WHERE diagnosa_pasien. STATUS = 'Ranap' AND bangsal.nm_bangsal like '%Ar-Raudah/Bawah%' "
                        + " AND bangsal.nm_bangsal NOT LIKE '%apotek%' AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 "
                        + " AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%'  "
                        + " AND kamar_inap.tgl_keluar BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + " AND kamar_inap.stts_pulang like 'Meninggal%' GROUP BY diagnosa_pasien.kd_penyakit ORDER BY qty DESC) AS a "
                        + " LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, count(*) AS qty	"
                        + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                        + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                        + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar "
                        + " LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                        + " WHERE diagnosa_pasien. STATUS = 'Ranap' AND bangsal.nm_bangsal like '%Ar-Raudah/Bawah%' "
                        + " AND bangsal.nm_bangsal NOT LIKE '%apotek%' AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 "
                        + " AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%'  "
                        + " AND kamar_inap.tgl_keluar BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + " AND kamar_inap.stts_pulang like 'Meninggal%' AND pasien.jk = 'L' GROUP BY diagnosa_pasien.kd_penyakit "
                        + " ORDER BY qty DESC) AS b ON b.kd_penyakit = a.kd_penyakit LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, "
                        + " count(*) AS qty FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                        + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat "
                        + " LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal "
                        + " LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis WHERE diagnosa_pasien. STATUS = 'Ranap' "
                        + " AND bangsal.nm_bangsal like '%Ar-Raudah/Bawah%' AND bangsal.nm_bangsal NOT LIKE '%apotek%' "
                        + " AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                        + " AND kamar_inap.tgl_keluar BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + " AND kamar_inap.stts_pulang like 'Meninggal%' AND pasien.jk = 'P' GROUP BY diagnosa_pasien.kd_penyakit "
                        + " ORDER BY qty DESC) AS c ON c.kd_penyakit = a.kd_penyakit) LIMIT " + jlhPenyakit.getText() + "", param);
            }
            this.setCursor(Cursor.getDefaultCursor());
        }
    }
    
    public void ctkPerRuangAsSamiMatiTglKeluar() {
        if (jlhPenyakit.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Silakan isi dulu jumlah besar penyakit yang akan diambil datanya...!!!");
            jlhPenyakit.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("periode", "PERIODE TGL. KELUAR " + Tgl1.getSelectedItem() + " S.D " + Tgl2.getSelectedItem());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("jlhData", jlhPenyakit.getText());
            if (ruangDicetak.getText().equals("AS-SAMI/1")) {
                param.put("nama_ruangan", "AS-SAMI/JANTUNG DAN LAINNYA");
            } else if (ruangDicetak.getText().equals("AS-SAMI/2")) {
                param.put("nama_ruangan", "AS-SAMI/KEMOTERAPI");
            }

            Valid.MyReport("rpDaftar10BesarDiagnosaInapMati.jasper", "report", "::[ Daftar " + jlhPenyakit.getText() + " Besar Penyakit Terbanyak Penyebab Kematian Per Ruangan Inap Berdasarkan Tgl. Keluar ]::",
                    " SELECT a.kd_penyakit AS KD_ICD_10, a.ciri_ciri AS Deskripsi_Diagnosa, IFNULL(b.qty, 0) LK, ifnull(c.qty, 0) PR, IFNULL(b.qty, 0) + IFNULL(c.qty, 0) Total "
                    + " FROM ((SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, count(*) AS qty "
                    + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                    + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                    + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat "
                    + " LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal "
                    + " LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis  "
                    + " WHERE diagnosa_pasien. STATUS = 'Ranap' AND bangsal.nm_bangsal like '%" + ruangDicetak.getText() + "%' "
                    + " AND bangsal.nm_bangsal NOT LIKE '%apotek%' AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 "
                    + " AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%'  "
                    + " AND kamar_inap.tgl_keluar BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                    + " AND kamar_inap.stts_pulang like 'Meninggal%' GROUP BY diagnosa_pasien.kd_penyakit ORDER BY qty DESC) AS a "
                    + " LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, count(*) AS qty	"
                    + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                    + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                    + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar "
                    + " LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " WHERE diagnosa_pasien. STATUS = 'Ranap' AND bangsal.nm_bangsal like '%" + ruangDicetak.getText() + "%' "
                    + " AND bangsal.nm_bangsal NOT LIKE '%apotek%' AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 "
                    + " AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%'  "
                    + " AND kamar_inap.tgl_keluar BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                    + " AND kamar_inap.stts_pulang like 'Meninggal%' AND pasien.jk = 'L' GROUP BY diagnosa_pasien.kd_penyakit "
                    + " ORDER BY qty DESC) AS b ON b.kd_penyakit = a.kd_penyakit LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, "
                    + " count(*) AS qty FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                    + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat "
                    + " LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal "
                    + " LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis WHERE diagnosa_pasien. STATUS = 'Ranap' "
                    + " AND bangsal.nm_bangsal like '%" + ruangDicetak.getText() + "%' AND bangsal.nm_bangsal NOT LIKE '%apotek%' "
                    + " AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                    + " AND kamar_inap.tgl_keluar BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                    + " AND kamar_inap.stts_pulang like 'Meninggal%' AND pasien.jk = 'P' GROUP BY diagnosa_pasien.kd_penyakit "
                    + " ORDER BY qty DESC) AS c ON c.kd_penyakit = a.kd_penyakit) LIMIT " + jlhPenyakit.getText() + "", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }
    
    public void ctkPerRuangMatiTglKeluar() {
        if (jlhPenyakit.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Silakan isi dulu jumlah besar penyakit yang akan diambil datanya...!!!");
            jlhPenyakit.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("periode", "PERIODE TGL. KELUAR " + Tgl1.getSelectedItem() + " S.D " + Tgl2.getSelectedItem());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("jlhData", jlhPenyakit.getText());
            param.put("nama_ruangan", ruangDicetak.getText());

            Valid.MyReport("rpDaftar10BesarDiagnosaInapMati.jasper", "report", "::[ Daftar " + jlhPenyakit.getText() + " Besar Penyakit Terbanyak Penyebab Kematian Per Ruangan Inap Berdasarkan Tgl. Keluar ]::",
                    " SELECT a.kd_penyakit AS KD_ICD_10, a.ciri_ciri AS Deskripsi_Diagnosa, IFNULL(b.qty, 0) LK, ifnull(c.qty, 0) PR, IFNULL(b.qty, 0) + IFNULL(c.qty, 0) Total "
                    + " FROM ((SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, count(*) AS qty "
                    + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                    + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                    + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat "
                    + " LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal "
                    + " LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis  "
                    + " WHERE diagnosa_pasien. STATUS = 'Ranap' AND bangsal.nm_gedung = '" + ruangDicetak.getText() + "' "
                    + " AND bangsal.nm_bangsal NOT LIKE '%apotek%' AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 "
                    + " AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%'  "
                    + " AND kamar_inap.tgl_keluar BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                    + " AND kamar_inap.stts_pulang like 'Meninggal%' GROUP BY diagnosa_pasien.kd_penyakit ORDER BY qty DESC) AS a "
                    + " LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, count(*) AS qty	"
                    + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                    + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                    + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar "
                    + " LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " WHERE diagnosa_pasien. STATUS = 'Ranap' AND bangsal.nm_gedung = '" + ruangDicetak.getText() + "' "
                    + " AND bangsal.nm_bangsal NOT LIKE '%apotek%' AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 "
                    + " AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%'  "
                    + " AND kamar_inap.tgl_keluar BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                    + " AND kamar_inap.stts_pulang like 'Meninggal%' AND pasien.jk = 'L' GROUP BY diagnosa_pasien.kd_penyakit "
                    + " ORDER BY qty DESC) AS b ON b.kd_penyakit = a.kd_penyakit LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, "
                    + " count(*) AS qty FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                    + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat "
                    + " LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal "
                    + " LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis WHERE diagnosa_pasien. STATUS = 'Ranap' "
                    + " AND bangsal.nm_gedung = '" + ruangDicetak.getText() + "' AND bangsal.nm_bangsal NOT LIKE '%apotek%' "
                    + " AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                    + " AND kamar_inap.tgl_keluar BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                    + " AND kamar_inap.stts_pulang like 'Meninggal%' AND pasien.jk = 'P' GROUP BY diagnosa_pasien.kd_penyakit "
                    + " ORDER BY qty DESC) AS c ON c.kd_penyakit = a.kd_penyakit) LIMIT " + jlhPenyakit.getText() + "", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }
    
    public void ctkRuangArRaudahMatiTglMsk() {
        if (jlhPenyakit.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Silakan isi dulu jumlah besar penyakit yang akan diambil datanya...!!!");
            jlhPenyakit.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("periode", "PERIODE TGL. MASUK " + Tgl1.getSelectedItem() + " S.D " + Tgl2.getSelectedItem());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("jlhData", jlhPenyakit.getText());
            if (ruangDicetak.getText().equals("AR-RAUDAH")) {
                param.put("nama_ruangan", "AR-RAUDAH");
            } else if (ruangDicetak.getText().equals("AR-RAUDAH ATAS")) {
                param.put("nama_ruangan", "AR-RAUDAH ATAS (MATA, THT, KULKEL)");
            } else if (ruangDicetak.getText().equals("AR-RAUDAH BAWAH")) {
                param.put("nama_ruangan", "AR-RAUDAH BAWAH (SARAF)");
            }
            
            if (ruangDicetak.getText().equals("AR-RAUDAH")) {
                Valid.MyReport("rpDaftar10BesarDiagnosaInapMati.jasper", "report", "::[ Daftar " + jlhPenyakit.getText() + " Besar Penyakit Terbanyak Penyebab Kematian Per Ruangan Inap Berdasarkan Tgl. Masuk ]::",
                        " SELECT a.kd_penyakit AS KD_ICD_10, a.ciri_ciri AS Deskripsi_Diagnosa, IFNULL(b.qty, 0) LK, ifnull(c.qty, 0) PR, IFNULL(b.qty, 0) + IFNULL(c.qty, 0) Total "
                        + " FROM ((SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, count(*) AS qty "
                        + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                        + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                        + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat "
                        + " LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal "
                        + " LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis  "
                        + " WHERE diagnosa_pasien.STATUS = 'Ranap' AND bangsal.nm_gedung = '" + ruangDicetak.getText() + "' "
                        + " AND bangsal.nm_bangsal NOT LIKE '%apotek%' AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 "
                        + " AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                        + " AND kamar_inap.tgl_masuk BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + " AND kamar_inap.stts_pulang like 'Meninggal%' GROUP BY diagnosa_pasien.kd_penyakit ORDER BY qty DESC) AS a "
                        + " LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, count(*) AS qty	"
                        + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                        + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                        + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar "
                        + " LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                        + " WHERE diagnosa_pasien.STATUS = 'Ranap' AND bangsal.nm_gedung = '" + ruangDicetak.getText() + "' "
                        + " AND bangsal.nm_bangsal NOT LIKE '%apotek%' AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 "
                        + " AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                        + " AND kamar_inap.tgl_masuk BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + " AND kamar_inap.stts_pulang like 'Meninggal%' AND pasien.jk = 'L' GROUP BY diagnosa_pasien.kd_penyakit "
                        + " ORDER BY qty DESC) AS b ON b.kd_penyakit = a.kd_penyakit LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, "
                        + " count(*) AS qty FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                        + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat "
                        + " LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal "
                        + " LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis WHERE diagnosa_pasien.STATUS = 'Ranap' "
                        + " AND bangsal.nm_gedung = '" + ruangDicetak.getText() + "' AND bangsal.nm_bangsal NOT LIKE '%apotek%' "
                        + " AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                        + " AND kamar_inap.tgl_masuk BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + " AND kamar_inap.stts_pulang like 'Meninggal%' AND pasien.jk = 'P' GROUP BY diagnosa_pasien.kd_penyakit "
                        + " ORDER BY qty DESC) AS c ON c.kd_penyakit = a.kd_penyakit) LIMIT " + jlhPenyakit.getText() + "", param);
                
            } else if (ruangDicetak.getText().equals("AR-RAUDAH ATAS")) {
                Valid.MyReport("rpDaftar10BesarDiagnosaInapMati.jasper", "report", "::[ Daftar " + jlhPenyakit.getText() + " Besar Penyakit Terbanyak Penyebab Kematian Per Ruangan Inap Berdasarkan Tgl. Masuk ]::",
                        " SELECT a.kd_penyakit AS KD_ICD_10, a.ciri_ciri AS Deskripsi_Diagnosa, IFNULL(b.qty, 0) LK, ifnull(c.qty, 0) PR, IFNULL(b.qty, 0) + IFNULL(c.qty, 0) Total "
                        + " FROM ((SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, count(*) AS qty "
                        + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                        + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                        + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat "
                        + " LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal "
                        + " LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis  "
                        + " WHERE diagnosa_pasien.STATUS = 'Ranap' AND bangsal.nm_bangsal like '%Ar-Raudah/Atas%' "
                        + " AND bangsal.nm_bangsal NOT LIKE '%apotek%' AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 "
                        + " AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                        + " AND kamar_inap.tgl_masuk BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + " AND kamar_inap.stts_pulang like 'Meninggal%' GROUP BY diagnosa_pasien.kd_penyakit ORDER BY qty DESC) AS a "
                        + " LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, count(*) AS qty	"
                        + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                        + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                        + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar "
                        + " LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                        + " WHERE diagnosa_pasien.STATUS = 'Ranap' AND bangsal.nm_bangsal like '%Ar-Raudah/Atas%' "
                        + " AND bangsal.nm_bangsal NOT LIKE '%apotek%' AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 "
                        + " AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                        + " AND kamar_inap.tgl_masuk BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + " AND kamar_inap.stts_pulang like 'Meninggal%' AND pasien.jk = 'L' GROUP BY diagnosa_pasien.kd_penyakit "
                        + " ORDER BY qty DESC) AS b ON b.kd_penyakit = a.kd_penyakit LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, "
                        + " count(*) AS qty FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                        + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat "
                        + " LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal "
                        + " LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis WHERE diagnosa_pasien.STATUS = 'Ranap' "
                        + " AND bangsal.nm_bangsal like '%Ar-Raudah/Atas%' AND bangsal.nm_bangsal NOT LIKE '%apotek%' "
                        + " AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                        + " AND kamar_inap.tgl_masuk BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + " AND kamar_inap.stts_pulang like 'Meninggal%' AND pasien.jk = 'P' GROUP BY diagnosa_pasien.kd_penyakit "
                        + " ORDER BY qty DESC) AS c ON c.kd_penyakit = a.kd_penyakit) LIMIT " + jlhPenyakit.getText() + "", param);

            } else if (ruangDicetak.getText().equals("AR-RAUDAH BAWAH")) {
                Valid.MyReport("rpDaftar10BesarDiagnosaInapMati.jasper", "report", "::[ Daftar " + jlhPenyakit.getText() + " Besar Penyakit Terbanyak Penyebab Kematian Per Ruangan Inap Berdasarkan Tgl. Masuk ]::",
                        " SELECT a.kd_penyakit AS KD_ICD_10, a.ciri_ciri AS Deskripsi_Diagnosa, IFNULL(b.qty, 0) LK, ifnull(c.qty, 0) PR, IFNULL(b.qty, 0) + IFNULL(c.qty, 0) Total "
                        + " FROM ((SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, count(*) AS qty "
                        + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                        + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                        + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat "
                        + " LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal "
                        + " LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis  "
                        + " WHERE diagnosa_pasien.STATUS = 'Ranap' AND bangsal.nm_bangsal like '%Ar-Raudah/Bawah%' "
                        + " AND bangsal.nm_bangsal NOT LIKE '%apotek%' AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 "
                        + " AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                        + " AND kamar_inap.tgl_masuk BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + " AND kamar_inap.stts_pulang like 'Meninggal%' GROUP BY diagnosa_pasien.kd_penyakit ORDER BY qty DESC) AS a "
                        + " LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, count(*) AS qty	"
                        + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                        + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                        + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar "
                        + " LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                        + " WHERE diagnosa_pasien.STATUS = 'Ranap' AND bangsal.nm_bangsal like '%Ar-Raudah/Bawah%' "
                        + " AND bangsal.nm_bangsal NOT LIKE '%apotek%' AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 "
                        + " AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                        + " AND kamar_inap.tgl_masuk BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + " AND kamar_inap.stts_pulang like 'Meninggal%' AND pasien.jk = 'L' GROUP BY diagnosa_pasien.kd_penyakit "
                        + " ORDER BY qty DESC) AS b ON b.kd_penyakit = a.kd_penyakit LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, "
                        + " count(*) AS qty FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                        + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat "
                        + " LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal "
                        + " LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis WHERE diagnosa_pasien.STATUS = 'Ranap' "
                        + " AND bangsal.nm_bangsal like '%Ar-Raudah/Bawah%' AND bangsal.nm_bangsal NOT LIKE '%apotek%' "
                        + " AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                        + " AND kamar_inap.tgl_masuk BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + " AND kamar_inap.stts_pulang like 'Meninggal%' AND pasien.jk = 'P' GROUP BY diagnosa_pasien.kd_penyakit "
                        + " ORDER BY qty DESC) AS c ON c.kd_penyakit = a.kd_penyakit) LIMIT " + jlhPenyakit.getText() + "", param);
            }
            this.setCursor(Cursor.getDefaultCursor());
        }
    }
    
    public void ctkRuangAsSamiMatiTglMsk() {
        if (jlhPenyakit.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Silakan isi dulu jumlah besar penyakit yang akan diambil datanya...!!!");
            jlhPenyakit.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("periode", "PERIODE TGL. MASUK " + Tgl1.getSelectedItem() + " S.D " + Tgl2.getSelectedItem());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("jlhData", jlhPenyakit.getText());            
            if (ruangDicetak.getText().equals("AS-SAMI/1")) {
                param.put("nama_ruangan", "AS-SAMI/JANTUNG DAN LAINNYA");
            } else if (ruangDicetak.getText().equals("AS-SAMI/2")) {
                param.put("nama_ruangan", "AS-SAMI/KEMOTERAPI");
            }

            Valid.MyReport("rpDaftar10BesarDiagnosaInapMati.jasper", "report", "::[ Daftar " + jlhPenyakit.getText() + " Besar Penyakit Terbanyak Penyebab Kematian Per Ruangan Inap Berdasarkan Tgl. Masuk ]::",
                    " SELECT a.kd_penyakit AS KD_ICD_10, a.ciri_ciri AS Deskripsi_Diagnosa, IFNULL(b.qty, 0) LK, ifnull(c.qty, 0) PR, IFNULL(b.qty, 0) + IFNULL(c.qty, 0) Total "
                    + " FROM ((SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, count(*) AS qty "
                    + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                    + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                    + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat "
                    + " LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal "
                    + " LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis  "
                    + " WHERE diagnosa_pasien.STATUS = 'Ranap' AND bangsal.nm_bangsal like '%" + ruangDicetak.getText() + "%' "
                    + " AND bangsal.nm_bangsal NOT LIKE '%apotek%' AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 "
                    + " AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                    + " AND kamar_inap.tgl_masuk BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                    + " AND kamar_inap.stts_pulang like 'Meninggal%' GROUP BY diagnosa_pasien.kd_penyakit ORDER BY qty DESC) AS a "
                    + " LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, count(*) AS qty	"
                    + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                    + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                    + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar "
                    + " LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " WHERE diagnosa_pasien.STATUS = 'Ranap' AND bangsal.nm_bangsal like '%" + ruangDicetak.getText() + "%' "
                    + " AND bangsal.nm_bangsal NOT LIKE '%apotek%' AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 "
                    + " AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                    + " AND kamar_inap.tgl_masuk BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                    + " AND kamar_inap.stts_pulang like 'Meninggal%' AND pasien.jk = 'L' GROUP BY diagnosa_pasien.kd_penyakit "
                    + " ORDER BY qty DESC) AS b ON b.kd_penyakit = a.kd_penyakit LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, "
                    + " count(*) AS qty FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                    + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat "
                    + " LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal "
                    + " LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis WHERE diagnosa_pasien.STATUS = 'Ranap' "
                    + " AND bangsal.nm_bangsal like '%" + ruangDicetak.getText() + "%' AND bangsal.nm_bangsal NOT LIKE '%apotek%' "
                    + " AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                    + " AND kamar_inap.tgl_masuk BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                    + " AND kamar_inap.stts_pulang like 'Meninggal%' AND pasien.jk = 'P' GROUP BY diagnosa_pasien.kd_penyakit "
                    + " ORDER BY qty DESC) AS c ON c.kd_penyakit = a.kd_penyakit) LIMIT " + jlhPenyakit.getText() + "", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }
    
    public void ctkPerRuangMatiTglMasuk() {
        if (jlhPenyakit.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Silakan isi dulu jumlah besar penyakit yang akan diambil datanya...!!!");
            jlhPenyakit.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("periode", "PERIODE TGL. MASUK " + Tgl1.getSelectedItem() + " S.D " + Tgl2.getSelectedItem());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("jlhData", jlhPenyakit.getText());
            param.put("nama_ruangan", ruangDicetak.getText());            

            Valid.MyReport("rpDaftar10BesarDiagnosaInapMati.jasper", "report", "::[ Daftar " + jlhPenyakit.getText() + " Besar Penyakit Terbanyak Penyebab Kematian Per Ruangan Inap Berdasarkan Tgl. Masuk ]::",
                    " SELECT a.kd_penyakit AS KD_ICD_10, a.ciri_ciri AS Deskripsi_Diagnosa, IFNULL(b.qty, 0) LK, ifnull(c.qty, 0) PR, IFNULL(b.qty, 0) + IFNULL(c.qty, 0) Total "
                    + " FROM ((SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, count(*) AS qty "
                    + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                    + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                    + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat "
                    + " LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal "
                    + " LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis  "
                    + " WHERE diagnosa_pasien.STATUS = 'Ranap' AND bangsal.nm_gedung = '" + ruangDicetak.getText() + "' "
                    + " AND bangsal.nm_bangsal NOT LIKE '%apotek%' AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 "
                    + " AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                    + " AND kamar_inap.tgl_masuk BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                    + " AND kamar_inap.stts_pulang like 'Meninggal%' GROUP BY diagnosa_pasien.kd_penyakit ORDER BY qty DESC) AS a "
                    + " LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, count(*) AS qty	"
                    + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                    + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                    + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar "
                    + " LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " WHERE diagnosa_pasien.STATUS = 'Ranap' AND bangsal.nm_gedung = '" + ruangDicetak.getText() + "' "
                    + " AND bangsal.nm_bangsal NOT LIKE '%apotek%' AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 "
                    + " AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                    + " AND kamar_inap.tgl_masuk BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                    + " AND kamar_inap.stts_pulang like 'Meninggal%' AND pasien.jk = 'L' GROUP BY diagnosa_pasien.kd_penyakit "
                    + " ORDER BY qty DESC) AS b ON b.kd_penyakit = a.kd_penyakit LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, "
                    + " count(*) AS qty FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                    + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat "
                    + " LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal "
                    + " LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis WHERE diagnosa_pasien.STATUS = 'Ranap' "
                    + " AND bangsal.nm_gedung = '" + ruangDicetak.getText() + "' AND bangsal.nm_bangsal NOT LIKE '%apotek%' "
                    + " AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                    + " AND kamar_inap.tgl_masuk BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                    + " AND kamar_inap.stts_pulang like 'Meninggal%' AND pasien.jk = 'P' GROUP BY diagnosa_pasien.kd_penyakit "
                    + " ORDER BY qty DESC) AS c ON c.kd_penyakit = a.kd_penyakit) LIMIT " + jlhPenyakit.getText() + "", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }
    
    public void ctkRuangRKPDzaalMatiTglKeluar() {
        if (jlhPenyakit.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Silakan isi dulu jumlah besar penyakit yang akan diambil datanya...!!!");
            jlhPenyakit.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("periode", "PERIODE TGL. KELUAR " + Tgl1.getSelectedItem() + " S.D " + Tgl2.getSelectedItem());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("jlhData", jlhPenyakit.getText());
            param.put("nama_ruangan", ruangDicetak.getText());

            Valid.MyReport("rpDaftar10BesarDiagnosaInapMati.jasper", "report", "::[ Daftar " + jlhPenyakit.getText() + " Besar Penyakit Terbanyak Penyebab Kematian Per Ruangan Inap Berdasarkan Tgl. Keluar ]::",
                    " SELECT a.kd_penyakit AS KD_ICD_10, a.ciri_ciri AS Deskripsi_Diagnosa, IFNULL(b.qty, 0) LK, ifnull(c.qty, 0) PR, IFNULL(b.qty, 0) + IFNULL(c.qty, 0) Total "
                    + " FROM ((SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, count(*) AS qty "
                    + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                    + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                    + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat "
                    + " LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal "
                    + " LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis  "
                    + " WHERE bangsal.nm_gedung in ('RKPD','ZAAL') and diagnosa_pasien.STATUS = 'Ranap' "
                    + " AND bangsal.nm_bangsal NOT LIKE '%apotek%' AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 "
                    + " AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%'  "
                    + " AND kamar_inap.tgl_keluar BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                    + " AND kamar_inap.stts_pulang like 'Meninggal%' GROUP BY diagnosa_pasien.kd_penyakit ORDER BY qty DESC) AS a "
                    + " LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, count(*) AS qty	"
                    + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                    + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                    + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar "
                    + " LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " WHERE bangsal.nm_gedung in ('RKPD','ZAAL') and diagnosa_pasien.STATUS = 'Ranap' "
                    + " AND bangsal.nm_bangsal NOT LIKE '%apotek%' AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 "
                    + " AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%'  "
                    + " AND kamar_inap.tgl_keluar BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                    + " AND kamar_inap.stts_pulang like 'Meninggal%' AND pasien.jk = 'L' GROUP BY diagnosa_pasien.kd_penyakit "
                    + " ORDER BY qty DESC) AS b ON b.kd_penyakit = a.kd_penyakit LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, "
                    + " count(*) AS qty FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                    + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat "
                    + " LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal "
                    + " LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis WHERE bangsal.nm_gedung in ('RKPD','ZAAL') and diagnosa_pasien.STATUS = 'Ranap' "
                    + " AND bangsal.nm_bangsal NOT LIKE '%apotek%' AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                    + " AND kamar_inap.tgl_keluar BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                    + " AND kamar_inap.stts_pulang like 'Meninggal%' AND pasien.jk = 'P' GROUP BY diagnosa_pasien.kd_penyakit "
                    + " ORDER BY qty DESC) AS c ON c.kd_penyakit = a.kd_penyakit) LIMIT " + jlhPenyakit.getText() + "", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }
    
    public void ctkRuangRKPDzaalMatiTglMasuk() {
        if (jlhPenyakit.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Silakan isi dulu jumlah besar penyakit yang akan diambil datanya...!!!");
            jlhPenyakit.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("periode", "PERIODE TGL. MASUK " + Tgl1.getSelectedItem() + " S.D " + Tgl2.getSelectedItem());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("jlhData", jlhPenyakit.getText());
            param.put("nama_ruangan", ruangDicetak.getText());

            Valid.MyReport("rpDaftar10BesarDiagnosaInapMati.jasper", "report", "::[ Daftar " + jlhPenyakit.getText() + " Besar Penyakit Terbanyak Penyebab Kematian Per Ruangan Inap Berdasarkan Tgl. Masuk ]::",
                    " SELECT a.kd_penyakit AS KD_ICD_10, a.ciri_ciri AS Deskripsi_Diagnosa, IFNULL(b.qty, 0) LK, ifnull(c.qty, 0) PR, IFNULL(b.qty, 0) + IFNULL(c.qty, 0) Total "
                    + " FROM ((SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, count(*) AS qty "
                    + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                    + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                    + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat "
                    + " LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal "
                    + " LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis  "
                    + " WHERE bangsal.nm_gedung in ('RKPD','ZAAL') and diagnosa_pasien.STATUS = 'Ranap' "
                    + " AND bangsal.nm_bangsal NOT LIKE '%apotek%' AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 "
                    + " AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%'  "
                    + " AND kamar_inap.tgl_masuk BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                    + " AND kamar_inap.stts_pulang like 'Meninggal%' GROUP BY diagnosa_pasien.kd_penyakit ORDER BY qty DESC) AS a "
                    + " LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, count(*) AS qty	"
                    + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                    + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                    + " LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar "
                    + " LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " WHERE bangsal.nm_gedung in ('RKPD','ZAAL') and diagnosa_pasien.STATUS = 'Ranap' "
                    + " AND bangsal.nm_bangsal NOT LIKE '%apotek%' AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 "
                    + " AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%'  "
                    + " AND kamar_inap.tgl_masuk BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                    + " AND kamar_inap.stts_pulang like 'Meninggal%' AND pasien.jk = 'L' GROUP BY diagnosa_pasien.kd_penyakit "
                    + " ORDER BY qty DESC) AS b ON b.kd_penyakit = a.kd_penyakit LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, "
                    + " count(*) AS qty FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                    + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat LEFT JOIN kamar_inap ON diagnosa_pasien.no_rawat = kamar_inap.no_rawat "
                    + " LEFT JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar LEFT JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal "
                    + " LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis WHERE bangsal.nm_gedung in ('RKPD','ZAAL') and diagnosa_pasien.STATUS = 'Ranap' "
                    + " AND bangsal.nm_bangsal NOT LIKE '%apotek%' AND bangsal.nm_bangsal NOT LIKE '%gudang%' AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                    + " AND kamar_inap.tgl_masuk BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                    + " AND kamar_inap.stts_pulang like 'Meninggal%' AND pasien.jk = 'P' GROUP BY diagnosa_pasien.kd_penyakit "
                    + " ORDER BY qty DESC) AS c ON c.kd_penyakit = a.kd_penyakit) LIMIT " + jlhPenyakit.getText() + "", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }
    
    public void prinRincianRKPDTglMasuk() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("periode", "PERIODE TGL. MASUK " + Tgl1.getSelectedItem() + " S.D " + Tgl2.getSelectedItem());
        param.put("nama_ruangan", ruangDicetak.getText());
        
        Valid.MyReport("rptPerdiagnosaRanap.jasper", "report", "::[ Rincian Perdiagnosa Berdasarkan Tgl. Masuk ]::",
                "SELECT DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y') tgl_masuk, DATE_FORMAT(ki.tgl_keluar,'%d-%m-%Y') tgl_pulang, "
                + "ki.stts_pulang, p.no_rkm_medis no_rm, p.nm_pasien, b.nm_bangsal, pj.png_jawab cr_byr, "
                + "py.kd_penyakit icd_10, py.ciri_ciri desk_diagnosa, if(dp.prioritas='1','Primer','Sekunder') status_diag FROM diagnosa_pasien dp "
                + "INNER JOIN penyakit py on py.kd_penyakit=dp.kd_penyakit INNER JOIN reg_periksa rp on rp.no_rawat=dp.no_rawat "
                + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj "
                + "INNER JOIN kamar_inap ki on ki.no_rawat=dp.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar "
                + "INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal "
                + "WHERE rp.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                + "and ki.stts_pulang not in ('-','pindah kamar') and py.ciri_ciri like '%" + TCari.getText() + "%' and dp.kd_penyakit like '%" + kdpenyakit.getText() + "%' "
                + "and pj.kd_pj like '%" + kdpnj.getText() + "%' and b.nm_gedung in ('RKPD','ZAAL') ORDER BY p.no_rkm_medis, rp.tgl_registrasi, dp.prioritas", param);
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    public void prinRincianDiagnosaArRaudahTglMasuk() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("periode", "PERIODE TGL. MASUK " + Tgl1.getSelectedItem() + " S.D " + Tgl2.getSelectedItem());
        if (ruangDicetak.getText().equals("AR-RAUDAH")) {
            param.put("nama_ruangan", "AR-RAUDAH");
        } else if (ruangDicetak.getText().equals("AR-RAUDAH ATAS")) {
            param.put("nama_ruangan", "AR-RAUDAH ATAS (MATA, THT, KULKEL)");
        } else if (ruangDicetak.getText().equals("AR-RAUDAH BAWAH")) {
            param.put("nama_ruangan", "AR-RAUDAH BAWAH (SARAF)");
        }

        if (ruangDicetak.getText().equals("AR-RAUDAH")) {
            Valid.MyReport("rptPerdiagnosaRanap.jasper", "report", "::[ Rincian Perdiagnosa Berdasarkan Tgl. Masuk ]::",
                    "SELECT DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y') tgl_masuk, DATE_FORMAT(ki.tgl_keluar,'%d-%m-%Y') tgl_pulang, "
                    + "ki.stts_pulang, p.no_rkm_medis no_rm, p.nm_pasien, b.nm_bangsal, pj.png_jawab cr_byr, "
                    + "py.kd_penyakit icd_10, py.ciri_ciri desk_diagnosa, if(dp.prioritas='1','Primer','Sekunder') status_diag FROM diagnosa_pasien dp "
                    + "INNER JOIN penyakit py on py.kd_penyakit=dp.kd_penyakit INNER JOIN reg_periksa rp on rp.no_rawat=dp.no_rawat "
                    + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj "
                    + "INNER JOIN kamar_inap ki on ki.no_rawat=dp.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar "
                    + "INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal "
                    + "WHERE rp.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                    + "and ki.stts_pulang not in ('-','pindah kamar') and py.ciri_ciri like '%" + TCari.getText() + "%' and dp.kd_penyakit like '%" + kdpenyakit.getText() + "%' "
                    + "and pj.kd_pj like '%" + kdpnj.getText() + "%' and b.nm_gedung = '" + ruangDicetak.getText() + "' ORDER BY p.no_rkm_medis, rp.tgl_registrasi, dp.prioritas", param);

        } else if (ruangDicetak.getText().equals("AR-RAUDAH ATAS")) {
            Valid.MyReport("rptPerdiagnosaRanap.jasper", "report", "::[ Rincian Perdiagnosa Berdasarkan Tgl. Masuk ]::",
                    "SELECT DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y') tgl_masuk, DATE_FORMAT(ki.tgl_keluar,'%d-%m-%Y') tgl_pulang, "
                    + "ki.stts_pulang, p.no_rkm_medis no_rm, p.nm_pasien, b.nm_bangsal, pj.png_jawab cr_byr, "
                    + "py.kd_penyakit icd_10, py.ciri_ciri desk_diagnosa, if(dp.prioritas='1','Primer','Sekunder') status_diag FROM diagnosa_pasien dp "
                    + "INNER JOIN penyakit py on py.kd_penyakit=dp.kd_penyakit INNER JOIN reg_periksa rp on rp.no_rawat=dp.no_rawat "
                    + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj "
                    + "INNER JOIN kamar_inap ki on ki.no_rawat=dp.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar "
                    + "INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal "
                    + "WHERE rp.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                    + "and ki.stts_pulang not in ('-','pindah kamar') and py.ciri_ciri like '%" + TCari.getText() + "%' and dp.kd_penyakit like '%" + kdpenyakit.getText() + "%' "
                    + "and pj.kd_pj like '%" + kdpnj.getText() + "%' and b.nm_bangsal like '%Ar-Raudah/Atas%' ORDER BY p.no_rkm_medis, rp.tgl_registrasi, dp.prioritas", param);
            
        } else if (ruangDicetak.getText().equals("AR-RAUDAH BAWAH")) {
            Valid.MyReport("rptPerdiagnosaRanap.jasper", "report", "::[ Rincian Perdiagnosa Berdasarkan Tgl. Masuk ]::",
                    "SELECT DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y') tgl_masuk, DATE_FORMAT(ki.tgl_keluar,'%d-%m-%Y') tgl_pulang, "
                    + "ki.stts_pulang, p.no_rkm_medis no_rm, p.nm_pasien, b.nm_bangsal, pj.png_jawab cr_byr, "
                    + "py.kd_penyakit icd_10, py.ciri_ciri desk_diagnosa, if(dp.prioritas='1','Primer','Sekunder') status_diag FROM diagnosa_pasien dp "
                    + "INNER JOIN penyakit py on py.kd_penyakit=dp.kd_penyakit INNER JOIN reg_periksa rp on rp.no_rawat=dp.no_rawat "
                    + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj "
                    + "INNER JOIN kamar_inap ki on ki.no_rawat=dp.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar "
                    + "INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal "
                    + "WHERE rp.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                    + "and ki.stts_pulang not in ('-','pindah kamar') and py.ciri_ciri like '%" + TCari.getText() + "%' and dp.kd_penyakit like '%" + kdpenyakit.getText() + "%' "
                    + "and pj.kd_pj like '%" + kdpnj.getText() + "%' and b.nm_bangsal like '%Ar-Raudah/Bawah%' ORDER BY p.no_rkm_medis, rp.tgl_registrasi, dp.prioritas", param);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    public void prinRincianDiagnosaAsSamiTglMasuk() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("periode", "PERIODE TGL. MASUK " + Tgl1.getSelectedItem() + " S.D " + Tgl2.getSelectedItem());
        if (ruangDicetak.getText().equals("AS-SAMI/1")) {
            param.put("nama_ruangan", "AS-SAMI/JANTUNG DAN LAINNYA");
        } else if (ruangDicetak.getText().equals("AS-SAMI/2")) {
            param.put("nama_ruangan", "AS-SAMI/KEMOTERAPI");
        }
        
        Valid.MyReport("rptPerdiagnosaRanap.jasper", "report", "::[ Rincian Perdiagnosa Berdasarkan Tgl. Masuk ]::",
                "SELECT DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y') tgl_masuk, DATE_FORMAT(ki.tgl_keluar,'%d-%m-%Y') tgl_pulang, "
                + "ki.stts_pulang, p.no_rkm_medis no_rm, p.nm_pasien, b.nm_bangsal, pj.png_jawab cr_byr, "
                + "py.kd_penyakit icd_10, py.ciri_ciri desk_diagnosa, if(dp.prioritas='1','Primer','Sekunder') status_diag FROM diagnosa_pasien dp "
                + "INNER JOIN penyakit py on py.kd_penyakit=dp.kd_penyakit INNER JOIN reg_periksa rp on rp.no_rawat=dp.no_rawat "
                + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj "
                + "INNER JOIN kamar_inap ki on ki.no_rawat=dp.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar "
                + "INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal "
                + "WHERE rp.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                + "and ki.stts_pulang not in ('-','pindah kamar') and py.ciri_ciri like '%" + TCari.getText() + "%' and dp.kd_penyakit like '%" + kdpenyakit.getText() + "%' "
                + "and pj.kd_pj like '%" + kdpnj.getText() + "%' and b.nm_bangsal like '%" + ruangDicetak.getText() + "%' ORDER BY p.no_rkm_medis, rp.tgl_registrasi, dp.prioritas", param);
        this.setCursor(Cursor.getDefaultCursor());
    } 
    
    public void prinSemuaRincianTglMasuk() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("periode", "PERIODE TGL. MASUK " + Tgl1.getSelectedItem() + " S.D " + Tgl2.getSelectedItem());
        if (ruangDicetak.getText().equals("SEMUA RUANG")) {
            param.put("nama_ruangan", "SEMUA RUANGAN");
        } else {
            param.put("nama_ruangan", ruangDicetak.getText());
        }
        
        if (ruangDicetak.getText().equals("SEMUA RUANG")) {
            Valid.MyReport("rptPerdiagnosaRanap.jasper", "report", "::[ Rincian Perdiagnosa Semua Ruangan Inap Berdasarkan Tgl. Masuk ]::",
                    "SELECT DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y') tgl_masuk, DATE_FORMAT(ki.tgl_keluar,'%d-%m-%Y') tgl_pulang, "
                    + "ki.stts_pulang, p.no_rkm_medis no_rm, p.nm_pasien, b.nm_bangsal, pj.png_jawab cr_byr, "
                    + "py.kd_penyakit icd_10, py.ciri_ciri desk_diagnosa, if(dp.prioritas='1','Primer','Sekunder') status_diag FROM diagnosa_pasien dp "
                    + "INNER JOIN penyakit py on py.kd_penyakit=dp.kd_penyakit INNER JOIN reg_periksa rp on rp.no_rawat=dp.no_rawat "
                    + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj "
                    + "INNER JOIN kamar_inap ki on ki.no_rawat=dp.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar "
                    + "INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal "
                    + "WHERE rp.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                    + "and ki.stts_pulang not in ('-','pindah kamar') and py.ciri_ciri like '%" + TCari.getText() + "%' and dp.kd_penyakit like '%" + kdpenyakit.getText() + "%' "
                    + "and pj.kd_pj like '%" + kdpnj.getText() + "%' ORDER BY p.no_rkm_medis, rp.tgl_registrasi, dp.prioritas", param);
        } else {
            Valid.MyReport("rptPerdiagnosaRanap.jasper", "report", "::[ Rincian Perdiagnosa Semua Ruangan Inap Berdasarkan Tgl. Masuk ]::",
                    "SELECT DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y') tgl_masuk, DATE_FORMAT(ki.tgl_keluar,'%d-%m-%Y') tgl_pulang, "
                    + "ki.stts_pulang, p.no_rkm_medis no_rm, p.nm_pasien, b.nm_bangsal, pj.png_jawab cr_byr, "
                    + "py.kd_penyakit icd_10, py.ciri_ciri desk_diagnosa, if(dp.prioritas='1','Primer','Sekunder') status_diag FROM diagnosa_pasien dp "
                    + "INNER JOIN penyakit py on py.kd_penyakit=dp.kd_penyakit INNER JOIN reg_periksa rp on rp.no_rawat=dp.no_rawat "
                    + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj "
                    + "INNER JOIN kamar_inap ki on ki.no_rawat=dp.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar "
                    + "INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal "
                    + "WHERE rp.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                    + "and ki.stts_pulang not in ('-','pindah kamar') and py.ciri_ciri like '%" + TCari.getText() + "%' and dp.kd_penyakit like '%" + kdpenyakit.getText() + "%' "
                    + "and pj.kd_pj like '%" + kdpnj.getText() + "%' and b.nm_gedung = '" + ruangDicetak.getText() + "' ORDER BY p.no_rkm_medis, rp.tgl_registrasi, dp.prioritas", param);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    public void prinRincianRKPDTglPulang() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("periode", "PERIODE TGL. PULANG " + Tgl1.getSelectedItem() + " S.D " + Tgl2.getSelectedItem());
        param.put("nama_ruangan", ruangDicetak.getText());
        
        Valid.MyReport("rptPerdiagnosaRanap.jasper", "report", "::[ Rincian Perdiagnosa Berdasarkan Tgl. Pulang ]::",
                "SELECT DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y') tgl_masuk, DATE_FORMAT(ki.tgl_keluar,'%d-%m-%Y') tgl_pulang, "
                + "ki.stts_pulang, p.no_rkm_medis no_rm, p.nm_pasien, b.nm_bangsal, pj.png_jawab cr_byr, "
                + "py.kd_penyakit icd_10, py.ciri_ciri desk_diagnosa, if(dp.prioritas='1','Primer','Sekunder') status_diag FROM diagnosa_pasien dp "
                + "INNER JOIN penyakit py on py.kd_penyakit=dp.kd_penyakit INNER JOIN reg_periksa rp on rp.no_rawat=dp.no_rawat "
                + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj "
                + "INNER JOIN kamar_inap ki on ki.no_rawat=dp.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar "
                + "INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal "
                + "WHERE ki.tgl_keluar BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                + "and ki.stts_pulang not in ('-','pindah kamar') and py.ciri_ciri like '%" + TCari.getText() + "%' and dp.kd_penyakit like '%" + kdpenyakit.getText() + "%' "
                + "and pj.kd_pj like '%" + kdpnj.getText() + "%' and b.nm_gedung in ('RKPD','ZAAL') ORDER BY p.no_rkm_medis, rp.tgl_registrasi, dp.prioritas", param);
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    public void prinRincianDiagnosaAsSamiTglPulang() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("periode", "PERIODE TGL. PULANG " + Tgl1.getSelectedItem() + " S.D " + Tgl2.getSelectedItem());
        if (ruangDicetak.getText().equals("AS-SAMI/1")) {
            param.put("nama_ruangan", "AS-SAMI/JANTUNG DAN LAINNYA");
        } else if (ruangDicetak.getText().equals("AS-SAMI/2")) {
            param.put("nama_ruangan", "AS-SAMI/KEMOTERAPI");
        }

        Valid.MyReport("rptPerdiagnosaRanap.jasper", "report", "::[ Rincian Perdiagnosa Semua Ruangan Inap Berdasarkan Tgl. Pulang ]::",
                "SELECT DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y') tgl_masuk, DATE_FORMAT(ki.tgl_keluar,'%d-%m-%Y') tgl_pulang, "
                + "ki.stts_pulang, p.no_rkm_medis no_rm, p.nm_pasien, b.nm_bangsal, pj.png_jawab cr_byr, "
                + "py.kd_penyakit icd_10, py.ciri_ciri desk_diagnosa, if(dp.prioritas='1','Primer','Sekunder') status_diag FROM diagnosa_pasien dp "
                + "INNER JOIN penyakit py on py.kd_penyakit=dp.kd_penyakit INNER JOIN reg_periksa rp on rp.no_rawat=dp.no_rawat "
                + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj "
                + "INNER JOIN kamar_inap ki on ki.no_rawat=dp.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar "
                + "INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal "
                + "WHERE ki.tgl_keluar BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                + "and ki.stts_pulang not in ('-','pindah kamar') and py.ciri_ciri like '%" + TCari.getText() + "%' and dp.kd_penyakit like '%" + kdpenyakit.getText() + "%' "
                + "and pj.kd_pj like '%" + kdpnj.getText() + "%' and b.nm_bangsal like '%" + ruangDicetak.getText() + "%' ORDER BY p.no_rkm_medis, rp.tgl_registrasi, dp.prioritas", param);
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    public void prinRincianDiagnosaArRaudahTglPulang() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("periode", "PERIODE TGL. PULANG " + Tgl1.getSelectedItem() + " S.D " + Tgl2.getSelectedItem());
        if (ruangDicetak.getText().equals("AR-RAUDAH")) {
            param.put("nama_ruangan", "AR-RAUDAH");
        } else if (ruangDicetak.getText().equals("AR-RAUDAH ATAS")) {
            param.put("nama_ruangan", "AR-RAUDAH ATAS (MATA, THT, KULKEL)");
        } else if (ruangDicetak.getText().equals("AR-RAUDAH BAWAH")) {
            param.put("nama_ruangan", "AR-RAUDAH BAWAH (SARAF)");
        }
        
        if (ruangDicetak.getText().equals("AR-RAUDAH")) {
            Valid.MyReport("rptPerdiagnosaRanap.jasper", "report", "::[ Rincian Perdiagnosa Berdasarkan Tgl. Pulang ]::",
                    "SELECT DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y') tgl_masuk, DATE_FORMAT(ki.tgl_keluar,'%d-%m-%Y') tgl_pulang, "
                    + "ki.stts_pulang, p.no_rkm_medis no_rm, p.nm_pasien, b.nm_bangsal, pj.png_jawab cr_byr, "
                    + "py.kd_penyakit icd_10, py.ciri_ciri desk_diagnosa, if(dp.prioritas='1','Primer','Sekunder') status_diag FROM diagnosa_pasien dp "
                    + "INNER JOIN penyakit py on py.kd_penyakit=dp.kd_penyakit INNER JOIN reg_periksa rp on rp.no_rawat=dp.no_rawat "
                    + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj "
                    + "INNER JOIN kamar_inap ki on ki.no_rawat=dp.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar "
                    + "INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal "
                    + "WHERE ki.tgl_keluar BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                    + "and ki.stts_pulang not in ('-','pindah kamar') and py.ciri_ciri like '%" + TCari.getText() + "%' and dp.kd_penyakit like '%" + kdpenyakit.getText() + "%' "
                    + "and pj.kd_pj like '%" + kdpnj.getText() + "%' and b.nm_gedung = '" + ruangDicetak.getText() + "' ORDER BY p.no_rkm_medis, rp.tgl_registrasi, dp.prioritas", param);

        } else if (ruangDicetak.getText().equals("AR-RAUDAH ATAS")) {
            Valid.MyReport("rptPerdiagnosaRanap.jasper", "report", "::[ Rincian Perdiagnosa Berdasarkan Tgl. Pulang ]::",
                    "SELECT DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y') tgl_masuk, DATE_FORMAT(ki.tgl_keluar,'%d-%m-%Y') tgl_pulang, "
                    + "ki.stts_pulang, p.no_rkm_medis no_rm, p.nm_pasien, b.nm_bangsal, pj.png_jawab cr_byr, "
                    + "py.kd_penyakit icd_10, py.ciri_ciri desk_diagnosa, if(dp.prioritas='1','Primer','Sekunder') status_diag FROM diagnosa_pasien dp "
                    + "INNER JOIN penyakit py on py.kd_penyakit=dp.kd_penyakit INNER JOIN reg_periksa rp on rp.no_rawat=dp.no_rawat "
                    + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj "
                    + "INNER JOIN kamar_inap ki on ki.no_rawat=dp.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar "
                    + "INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal "
                    + "WHERE ki.tgl_keluar BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                    + "and ki.stts_pulang not in ('-','pindah kamar') and py.ciri_ciri like '%" + TCari.getText() + "%' and dp.kd_penyakit like '%" + kdpenyakit.getText() + "%' "
                    + "and pj.kd_pj like '%" + kdpnj.getText() + "%' and b.nm_bangsal like '%Ar-Raudah/Atas%' ORDER BY p.no_rkm_medis, rp.tgl_registrasi, dp.prioritas", param);
         
        } else if (ruangDicetak.getText().equals("AR-RAUDAH BAWAH")) {
            Valid.MyReport("rptPerdiagnosaRanap.jasper", "report", "::[ Rincian Perdiagnosa Berdasarkan Tgl. Pulang ]::",
                    "SELECT DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y') tgl_masuk, DATE_FORMAT(ki.tgl_keluar,'%d-%m-%Y') tgl_pulang, "
                    + "ki.stts_pulang, p.no_rkm_medis no_rm, p.nm_pasien, b.nm_bangsal, pj.png_jawab cr_byr, "
                    + "py.kd_penyakit icd_10, py.ciri_ciri desk_diagnosa, if(dp.prioritas='1','Primer','Sekunder') status_diag FROM diagnosa_pasien dp "
                    + "INNER JOIN penyakit py on py.kd_penyakit=dp.kd_penyakit INNER JOIN reg_periksa rp on rp.no_rawat=dp.no_rawat "
                    + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj "
                    + "INNER JOIN kamar_inap ki on ki.no_rawat=dp.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar "
                    + "INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal "
                    + "WHERE ki.tgl_keluar BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                    + "and ki.stts_pulang not in ('-','pindah kamar') and py.ciri_ciri like '%" + TCari.getText() + "%' and dp.kd_penyakit like '%" + kdpenyakit.getText() + "%' "
                    + "and pj.kd_pj like '%" + kdpnj.getText() + "%' and b.nm_bangsal like '%Ar-Raudah/Bawah%' ORDER BY p.no_rkm_medis, rp.tgl_registrasi, dp.prioritas", param);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    public void prinRincianDiagnosaTglPulang() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("periode", "PERIODE TGL. PULANG " + Tgl1.getSelectedItem() + " S.D " + Tgl2.getSelectedItem());
        if (cmbRuangKhusus1.getSelectedItem().equals("AS-SAMI/UMUM") || (NmRuangan.getSelectedItem().equals("AS-SAMI/UMUM"))) {
            param.put("nama_ruangan", "AS-SAMI/UMUM");
        } else {
            param.put("nama_ruangan", ruangDicetak.getText());
        }
        
        Valid.MyReport("rptPerdiagnosaRanap.jasper", "report", "::[ Rincian Perdiagnosa Berdasarkan Tgl. Pulang ]::",
                "SELECT DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y') tgl_masuk, DATE_FORMAT(ki.tgl_keluar,'%d-%m-%Y') tgl_pulang, "
                + "ki.stts_pulang, p.no_rkm_medis no_rm, p.nm_pasien, b.nm_bangsal, pj.png_jawab cr_byr, "
                + "py.kd_penyakit icd_10, py.ciri_ciri desk_diagnosa, if(dp.prioritas='1','Primer','Sekunder') status_diag FROM diagnosa_pasien dp "
                + "INNER JOIN penyakit py on py.kd_penyakit=dp.kd_penyakit INNER JOIN reg_periksa rp on rp.no_rawat=dp.no_rawat "
                + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj "
                + "INNER JOIN kamar_inap ki on ki.no_rawat=dp.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar "
                + "INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal "
                + "WHERE ki.tgl_keluar BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                + "and ki.stts_pulang not in ('-','pindah kamar') and py.ciri_ciri like '%" + TCari.getText() + "%' and dp.kd_penyakit like '%" + kdpenyakit.getText() + "%' "
                + "and pj.kd_pj like '%" + kdpnj.getText() + "%' and b.nm_bangsal like '%" + ruangDicetak.getText() + "%' ORDER BY p.no_rkm_medis, rp.tgl_registrasi, dp.prioritas", param);
        this.setCursor(Cursor.getDefaultCursor());
    } 
    
    public void prinSemuaRincianTglPulang() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("periode", "PERIODE TGL. PULANG " + Tgl1.getSelectedItem() + " S.D " + Tgl2.getSelectedItem());
        if (ruangDicetak.getText().equals("SEMUA RUANG")) {
            param.put("nama_ruangan", "SEMUA RUANGAN");
        } else {
            param.put("nama_ruangan", ruangDicetak.getText());
        }
        
        if (ruangDicetak.getText().equals("SEMUA RUANG")) {
            Valid.MyReport("rptPerdiagnosaRanap.jasper", "report", "::[ Rincian Perdiagnosa Semua Ruangan Inap Berdasarkan Tgl. Pulang ]::",
                    "SELECT DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y') tgl_masuk, DATE_FORMAT(ki.tgl_keluar,'%d-%m-%Y') tgl_pulang, "
                    + "ki.stts_pulang, p.no_rkm_medis no_rm, p.nm_pasien, b.nm_bangsal, pj.png_jawab cr_byr, "
                    + "py.kd_penyakit icd_10, py.ciri_ciri desk_diagnosa, if(dp.prioritas='1','Primer','Sekunder') status_diag FROM diagnosa_pasien dp "
                    + "INNER JOIN penyakit py on py.kd_penyakit=dp.kd_penyakit INNER JOIN reg_periksa rp on rp.no_rawat=dp.no_rawat "
                    + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj "
                    + "INNER JOIN kamar_inap ki on ki.no_rawat=dp.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar "
                    + "INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal "
                    + "WHERE ki.tgl_keluar BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                    + "and ki.stts_pulang not in ('-','pindah kamar') and py.ciri_ciri like '%" + TCari.getText() + "%' and dp.kd_penyakit like '%" + kdpenyakit.getText() + "%' "
                    + "and pj.kd_pj like '%" + kdpnj.getText() + "%' ORDER BY p.no_rkm_medis, rp.tgl_registrasi, dp.prioritas", param);
        } else {
            Valid.MyReport("rptPerdiagnosaRanap.jasper", "report", "::[ Rincian Perdiagnosa Semua Ruangan Inap Berdasarkan Tgl. Pulang ]::",
                    "SELECT DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y') tgl_masuk, DATE_FORMAT(ki.tgl_keluar,'%d-%m-%Y') tgl_pulang, "
                    + "ki.stts_pulang, p.no_rkm_medis no_rm, p.nm_pasien, b.nm_bangsal, pj.png_jawab cr_byr, "
                    + "py.kd_penyakit icd_10, py.ciri_ciri desk_diagnosa, if(dp.prioritas='1','Primer','Sekunder') status_diag FROM diagnosa_pasien dp "
                    + "INNER JOIN penyakit py on py.kd_penyakit=dp.kd_penyakit INNER JOIN reg_periksa rp on rp.no_rawat=dp.no_rawat "
                    + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj "
                    + "INNER JOIN kamar_inap ki on ki.no_rawat=dp.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar "
                    + "INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal "
                    + "WHERE ki.tgl_keluar BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                    + "and ki.stts_pulang not in ('-','pindah kamar') and py.ciri_ciri like '%" + TCari.getText() + "%' and dp.kd_penyakit like '%" + kdpenyakit.getText() + "%' "
                    + "and pj.kd_pj like '%" + kdpnj.getText() + "%' and b.nm_gedung = '" + ruangDicetak.getText() + "' ORDER BY p.no_rkm_medis, rp.tgl_registrasi, dp.prioritas", param);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }
}
