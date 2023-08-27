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
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
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
import simrskhanza.DlgCariPoli;
import simrskhanza.DlgPasien;

public class DlgFrekuensiPenyakitRalan extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1;
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Jurnal jur = new Jurnal();
    private DlgCariPoli poli = new DlgCariPoli(null, false);
    private Connection koneksi = koneksiDB.condb();
    private PreparedStatement ps, ps2, ps3, ps4, ps5, ps6, ps7, ps8, ps9;
    private ResultSet rs, rs2, rs3, rs4, rs5, rs6, rs7, rs8, rs9;
    private String diagnosa = "", kdpnj = "";
    private DlgPasien pasien = new DlgPasien(null, false);

    /** Creates new form DlgProgramStudi
     * @param parent
     * @param modal */
    public DlgFrekuensiPenyakitRalan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        Object[] row={"Kode Penyakit","Nama Penyakit","Diagnosa Lain","Lk2(Hidup)","Pr(Hidup)","Lk2(Mati)","Pr(Mati)","Jumlah"};
        tabMode=new DefaultTableModel(null,row){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
              Class[] types = new Class[] {
                java.lang.Object.class, java.lang.Object.class,java.lang.Object.class, java.lang.Integer.class, 
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbFrekuensi.setModel(tabMode);

        tbFrekuensi.setPreferredScrollableViewportSize(new Dimension(800,800));
        tbFrekuensi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int m = 0; m < 8; m++) {
            TableColumn column = tbFrekuensi.getColumnModel().getColumn(m);
            if(m==0){
                column.setPreferredWidth(100);
            }else if(m==1){
                column.setPreferredWidth(300);
            }else if(m==2){
                column.setPreferredWidth(400);
            }else{
                column.setPreferredWidth(100);
            }
        }
        tbFrekuensi.setDefaultRenderer(Object.class, new WarnaTable());   
        
        tabMode1=new DefaultTableModel(null,new Object[]{"Tgl. Kunjungan", "No. RM", "Nama Pasien", "Poliklinik/Inst.",
            "Cara Bayar", "Kode ICD-10", "Deskripsi Diagnosa", "Status Diagnosa"}) {
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class
            };
        };
        
        tbDiagnosa.setModel(tabMode1);
        tbDiagnosa.setPreferredScrollableViewportSize(new Dimension(800,800));
        tbDiagnosa.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int m = 0; m < 8; m++) {
            TableColumn column = tbDiagnosa.getColumnModel().getColumn(m);
            if (m == 0) {
                column.setPreferredWidth(82);
            } else if (m == 1) {
                column.setPreferredWidth(75);
            } else if (m == 2) {
                column.setPreferredWidth(200);
            } else if (m == 3) {
                column.setPreferredWidth(150);
            } else if (m == 4) {
                column.setPreferredWidth(160);
            } else if (m == 5) {
                column.setPreferredWidth(75);
            } else if (m == 6) {
                column.setPreferredWidth(350);
            } else if (m == 7) {
                column.setPreferredWidth(90);
            }
        }
        tbDiagnosa.setDefaultRenderer(Object.class, new WarnaTable());
        
        kdpenyakit.setDocument(new batasInput((byte)20).getKata(kdpenyakit));
        
        pasien.penjab.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgFrekuensiPenyakitRalan")) {
                    if (pasien.penjab.getTable().getSelectedRow() != -1) {
                        kdpnj = pasien.penjab.getTable().getValueAt(pasien.penjab.getTable().getSelectedRow(), 1).toString();
                        nmpnj.setText(pasien.penjab.getTable().getValueAt(pasien.penjab.getTable().getSelectedRow(), 2).toString());
                    }
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
                if (akses.getform().equals("DlgFrekuensiPenyakitRalan")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        pasien.penjab.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        
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
        

        poli.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgFrekuensiPenyakitRalan")) {
                    if (poli.getTable().getSelectedRow() != -1) {
                        kdpoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 0).toString());
                        TPoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 1).toString());
                        cekSemuaPoli.setSelected(false);
                    }
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
        
        try {
            
            ps = koneksi.prepareStatement("select penyakit.kd_penyakit,SUBSTRING(penyakit.nm_penyakit,1,80) as penyakit from penyakit inner join diagnosa_pasien inner join reg_periksa "
                    + "on penyakit.kd_penyakit=diagnosa_pasien.kd_penyakit and reg_periksa.no_rawat=diagnosa_pasien.no_rawat "
                    + "where diagnosa_pasien.status='Ralan' and diagnosa_pasien.prioritas='1' and penyakit.kd_penyakit like ? and reg_periksa.tgl_registrasi between ? and ? "
                    + "group by penyakit.kd_penyakit order by penyakit.kd_penyakit");
            ps2 = koneksi.prepareStatement("select diagnosa_pasien.no_rawat as jumlah from diagnosa_pasien inner join reg_periksa on reg_periksa.no_rawat=diagnosa_pasien.no_rawat "
                    + "where diagnosa_pasien.status='Ralan' and diagnosa_pasien.prioritas='1' and reg_periksa.tgl_registrasi between ? and ? and diagnosa_pasien.kd_penyakit=? group by diagnosa_pasien.no_rawat");
            ps7 = koneksi.prepareStatement("select diagnosa_pasien.kd_penyakit from diagnosa_pasien where diagnosa_pasien.prioritas<>'1' and diagnosa_pasien.status='Ralan' and diagnosa_pasien.no_rawat=?");
            ps8 = koneksi.prepareStatement("select temporary_surveilens_penyakit.kd_penyakit2 from temporary_surveilens_penyakit where temporary_surveilens_penyakit.kd_penyakit=? group by temporary_surveilens_penyakit.kd_penyakit2");
            ps3 = koneksi.prepareStatement("select diagnosa_pasien.no_rawat as jumlah from diagnosa_pasien inner join reg_periksa inner join pasien_mati "
                    + "inner join pasien on reg_periksa.no_rawat=diagnosa_pasien.no_rawat and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "and pasien_mati.no_rkm_medis=pasien.no_rkm_medis where diagnosa_pasien.status='Ralan' and diagnosa_pasien.prioritas='1' and pasien.jk='L' and reg_periksa.tgl_registrasi between ? and ? "
                    + "and diagnosa_pasien.kd_penyakit=? group by diagnosa_pasien.no_rawat");
            ps4 = koneksi.prepareStatement("select diagnosa_pasien.no_rawat as jumlah from diagnosa_pasien inner join reg_periksa inner join pasien_mati "
                    + "inner join pasien on reg_periksa.no_rawat=diagnosa_pasien.no_rawat and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "and pasien_mati.no_rkm_medis=pasien.no_rkm_medis where diagnosa_pasien.status='Ralan' and diagnosa_pasien.prioritas='1' and pasien.jk='P' and reg_periksa.tgl_registrasi between ? and ? "
                    + "and diagnosa_pasien.kd_penyakit=? group by diagnosa_pasien.no_rawat");
            ps5 = koneksi.prepareStatement("select diagnosa_pasien.no_rawat as jumlah from diagnosa_pasien inner join reg_periksa "
                    + "inner join pasien on reg_periksa.no_rawat=diagnosa_pasien.no_rawat and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "where diagnosa_pasien.status='Ralan' and diagnosa_pasien.prioritas='1' and pasien.jk='L' and reg_periksa.tgl_registrasi between ? and ? "
                    + "and diagnosa_pasien.kd_penyakit=? group by diagnosa_pasien.no_rawat");
            ps6 = koneksi.prepareStatement("select diagnosa_pasien.no_rawat as jumlah from diagnosa_pasien inner join reg_periksa "
                    + "inner join pasien on reg_periksa.no_rawat=diagnosa_pasien.no_rawat and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "where diagnosa_pasien.status='Ralan' and diagnosa_pasien.prioritas='1' and pasien.jk='P' and reg_periksa.tgl_registrasi between ? and ? "
                    + "and diagnosa_pasien.kd_penyakit=? group by diagnosa_pasien.no_rawat");
             
        }catch (SQLException e) {
            System.out.println(e);
        }
        
        jlhPenyakit.setDocument(new batasInput((byte) 2).getOnlyAngka(jlhPenyakit));
        emptText();     
    }
    
    private Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    private DlgCariPenyakit penyakit = new DlgCariPenyakit(null, false);
    private int i = 0, a = 0, b = 0, c = 0, d = 0;

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
        jPopupMenu1 = new javax.swing.JPopupMenu();
        ppGrafikTerbanyakBatang = new javax.swing.JMenuItem();
        ppGrafikTerbanyakPie = new javax.swing.JMenuItem();
        ppGrafikTerkecilBatang = new javax.swing.JMenuItem();
        ppGrafikTerkecilPie = new javax.swing.JMenuItem();
        ppDaftarTerbanyakPerPoli = new javax.swing.JMenuItem();
        ppDaftarTerbanyakSemuaPoli = new javax.swing.JMenuItem();
        ppLapRL54RawatJalan = new javax.swing.JMenuItem();
        ppLapRL53RawatJalan = new javax.swing.JMenuItem();
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
        kdpoli = new widget.TextBox();
        TPoli = new widget.TextBox();
        BtnUnit = new widget.Button();
        cekSemuaPoli = new widget.CekBox();
        panelisi1 = new widget.panelisi();
        label20 = new widget.Label();
        jlhPenyakit = new widget.TextBox();
        label21 = new widget.Label();
        BtnBatal = new widget.Button();
        BtnAll = new widget.Button();
        BtnCari = new widget.Button();
        BtnKeluar = new widget.Button();
        label9 = new widget.Label();
        TabRawat = new javax.swing.JTabbedPane();
        scrollPane1 = new widget.ScrollPane();
        tbFrekuensi = new widget.Table();
        scrollPane2 = new widget.ScrollPane();
        tbDiagnosa = new widget.Table();

        Kd2.setName("Kd2"); // NOI18N
        Kd2.setPreferredSize(new java.awt.Dimension(207, 23));

        userBerizin.setName("userBerizin"); // NOI18N
        userBerizin.setPreferredSize(new java.awt.Dimension(207, 23));

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        ppGrafikTerbanyakBatang.setBackground(new java.awt.Color(242, 242, 242));
        ppGrafikTerbanyakBatang.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikTerbanyakBatang.setForeground(new java.awt.Color(0, 0, 0));
        ppGrafikTerbanyakBatang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Create-Ticket24.png"))); // NOI18N
        ppGrafikTerbanyakBatang.setText("Grafik Batang 10 Penyakit Terbanyak");
        ppGrafikTerbanyakBatang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikTerbanyakBatang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikTerbanyakBatang.setIconTextGap(8);
        ppGrafikTerbanyakBatang.setName("ppGrafikTerbanyakBatang"); // NOI18N
        ppGrafikTerbanyakBatang.setPreferredSize(new java.awt.Dimension(300, 25));
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
        ppGrafikTerbanyakPie.setIconTextGap(8);
        ppGrafikTerbanyakPie.setName("ppGrafikTerbanyakPie"); // NOI18N
        ppGrafikTerbanyakPie.setPreferredSize(new java.awt.Dimension(300, 25));
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
        ppGrafikTerkecilBatang.setIconTextGap(8);
        ppGrafikTerkecilBatang.setName("ppGrafikTerkecilBatang"); // NOI18N
        ppGrafikTerkecilBatang.setPreferredSize(new java.awt.Dimension(300, 25));
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
        ppGrafikTerkecilPie.setIconTextGap(8);
        ppGrafikTerkecilPie.setName("ppGrafikTerkecilPie"); // NOI18N
        ppGrafikTerkecilPie.setPreferredSize(new java.awt.Dimension(300, 25));
        ppGrafikTerkecilPie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikTerkecilPieActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppGrafikTerkecilPie);

        ppDaftarTerbanyakPerPoli.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppDaftarTerbanyakPerPoli.setForeground(new java.awt.Color(0, 0, 0));
        ppDaftarTerbanyakPerPoli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept_page.png"))); // NOI18N
        ppDaftarTerbanyakPerPoli.setText("Daftar Penyakit Terbanyak Per Unit/Poliklinik");
        ppDaftarTerbanyakPerPoli.setName("ppDaftarTerbanyakPerPoli"); // NOI18N
        ppDaftarTerbanyakPerPoli.setPreferredSize(new java.awt.Dimension(300, 25));
        ppDaftarTerbanyakPerPoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppDaftarTerbanyakPerPoliActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppDaftarTerbanyakPerPoli);

        ppDaftarTerbanyakSemuaPoli.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppDaftarTerbanyakSemuaPoli.setForeground(new java.awt.Color(0, 0, 0));
        ppDaftarTerbanyakSemuaPoli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept_page.png"))); // NOI18N
        ppDaftarTerbanyakSemuaPoli.setText("Daftar Penyakit Terbanyak Semua Unit/Poliklinik");
        ppDaftarTerbanyakSemuaPoli.setName("ppDaftarTerbanyakSemuaPoli"); // NOI18N
        ppDaftarTerbanyakSemuaPoli.setPreferredSize(new java.awt.Dimension(300, 25));
        ppDaftarTerbanyakSemuaPoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppDaftarTerbanyakSemuaPoliActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppDaftarTerbanyakSemuaPoli);

        ppLapRL54RawatJalan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppLapRL54RawatJalan.setForeground(new java.awt.Color(0, 0, 0));
        ppLapRL54RawatJalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept_page.png"))); // NOI18N
        ppLapRL54RawatJalan.setText("RL 5.4 Daftar 10 Besar Penyakit Rawat Jalan");
        ppLapRL54RawatJalan.setName("ppLapRL54RawatJalan"); // NOI18N
        ppLapRL54RawatJalan.setPreferredSize(new java.awt.Dimension(300, 25));
        ppLapRL54RawatJalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppLapRL54RawatJalanActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppLapRL54RawatJalan);

        ppLapRL53RawatJalan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppLapRL53RawatJalan.setForeground(new java.awt.Color(0, 0, 0));
        ppLapRL53RawatJalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept_page.png"))); // NOI18N
        ppLapRL53RawatJalan.setText("RL 5.3 Penyakit Rawat Jalan");
        ppLapRL53RawatJalan.setName("ppLapRL53RawatJalan"); // NOI18N
        ppLapRL53RawatJalan.setPreferredSize(new java.awt.Dimension(300, 25));
        ppLapRL53RawatJalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppLapRL53RawatJalanActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppLapRL53RawatJalan);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Rekap Frekuensi Penyakit Di Rawat Jalan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelisi4.setName("panelisi4"); // NOI18N
        panelisi4.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        label11.setForeground(new java.awt.Color(0, 0, 0));
        label11.setText("Tgl. Perawatan :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(90, 23));
        panelisi4.add(label11);

        Tgl1.setEditable(false);
        Tgl1.setDisplayFormat("dd-MM-yyyy");
        Tgl1.setName("Tgl1"); // NOI18N
        Tgl1.setPreferredSize(new java.awt.Dimension(100, 23));
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
        panelisi4.add(Tgl2);

        label17.setForeground(new java.awt.Color(0, 0, 0));
        label17.setText("Penyakit :");
        label17.setName("label17"); // NOI18N
        label17.setPreferredSize(new java.awt.Dimension(65, 23));
        panelisi4.add(label17);

        kdpenyakit.setEditable(false);
        kdpenyakit.setForeground(new java.awt.Color(0, 0, 0));
        kdpenyakit.setName("kdpenyakit"); // NOI18N
        kdpenyakit.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi4.add(kdpenyakit);

        nmpenyakit.setEditable(false);
        nmpenyakit.setForeground(new java.awt.Color(0, 0, 0));
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
        label19.setText("Nama Unit/Poliklinik :");
        label19.setName("label19"); // NOI18N
        label19.setPreferredSize(new java.awt.Dimension(110, 23));
        panelisi4.add(label19);

        kdpoli.setEditable(false);
        kdpoli.setForeground(new java.awt.Color(0, 0, 0));
        kdpoli.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        kdpoli.setHighlighter(null);
        kdpoli.setName("kdpoli"); // NOI18N
        kdpoli.setPreferredSize(new java.awt.Dimension(55, 23));
        panelisi4.add(kdpoli);

        TPoli.setEditable(false);
        TPoli.setForeground(new java.awt.Color(0, 0, 0));
        TPoli.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        TPoli.setName("TPoli"); // NOI18N
        TPoli.setPreferredSize(new java.awt.Dimension(203, 23));
        panelisi4.add(TPoli);
        TPoli.getAccessibleContext().setAccessibleName("");
        TPoli.getAccessibleContext().setAccessibleDescription("");

        BtnUnit.setForeground(new java.awt.Color(0, 0, 0));
        BtnUnit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnUnit.setMnemonic('4');
        BtnUnit.setToolTipText("ALt+4");
        BtnUnit.setName("BtnUnit"); // NOI18N
        BtnUnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUnitActionPerformed(evt);
            }
        });
        panelisi4.add(BtnUnit);

        cekSemuaPoli.setBackground(new java.awt.Color(255, 255, 250));
        cekSemuaPoli.setBorder(null);
        cekSemuaPoli.setForeground(new java.awt.Color(0, 0, 0));
        cekSemuaPoli.setText("Semua Poli/Unit");
        cekSemuaPoli.setBorderPainted(true);
        cekSemuaPoli.setBorderPaintedFlat(true);
        cekSemuaPoli.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cekSemuaPoli.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cekSemuaPoli.setName("cekSemuaPoli"); // NOI18N
        cekSemuaPoli.setOpaque(false);
        cekSemuaPoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cekSemuaPoliActionPerformed(evt);
            }
        });
        panelisi4.add(cekSemuaPoli);

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

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('2');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+2");
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(140, 23));
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
        panelisi1.add(BtnCari);

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
        label9.setPreferredSize(new java.awt.Dimension(415, 30));
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

        tbFrekuensi.setAutoCreateRowSorter(true);
        tbFrekuensi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbFrekuensi.setToolTipText("");
        tbFrekuensi.setComponentPopupMenu(jPopupMenu1);
        tbFrekuensi.setName("tbFrekuensi"); // NOI18N
        scrollPane1.setViewportView(tbFrekuensi);

        TabRawat.addTab("Frekuensi Penyakit", scrollPane1);

        scrollPane2.setName("scrollPane2"); // NOI18N
        scrollPane2.setOpaque(true);

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
        tbDiagnosa.setToolTipText("");
        tbDiagnosa.setName("tbDiagnosa"); // NOI18N
        scrollPane2.setViewportView(tbDiagnosa);

        TabRawat.addTab("Rincian Diagnosa", scrollPane2);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
/*
private void KdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKdKeyPressed
    Valid.pindah(evt,BtnCari,Nm);
}//GEN-LAST:event_TKdKeyPressed
*/

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnCari,Tgl1);}
    }//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        if (TabRawat.getSelectedIndex() == 0) {
            emptText();
            tampil1();
        } else if (TabRawat.getSelectedIndex() == 1) {
            emptText();
            tampil2();
        }
    }//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnAllActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnCari, BtnKeluar);
        }
    }//GEN-LAST:event_BtnAllKeyPressed

    private void ppGrafikTerbanyakBatangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikTerbanyakBatangActionPerformed
        if (akses.getkode().equals("Admin Utama") || akses.getpenyakit() == true) {         
            if ((tbFrekuensi.getRowCount() > 9) && (Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()))
                    && (Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()))
                    && (Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()))
                    && (Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()))
                    && (Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(5, 7).toString()))
                    && (Integer.parseInt(tbFrekuensi.getValueAt(5, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(6, 7).toString()))
                    && (Integer.parseInt(tbFrekuensi.getValueAt(6, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(7, 7).toString()))
                    && (Integer.parseInt(tbFrekuensi.getValueAt(7, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(8, 7).toString()))
                    && (Integer.parseInt(tbFrekuensi.getValueAt(8, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(9, 7).toString()))) {
                DefaultCategoryDataset dcd = new DefaultCategoryDataset();
                dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()), tbFrekuensi.getValueAt(0, 0).toString() + ", " + tbFrekuensi.getValueAt(0, 1).toString() + ", " + tbFrekuensi.getValueAt(0, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()), tbFrekuensi.getValueAt(1, 0).toString() + ", " + tbFrekuensi.getValueAt(1, 1).toString() + ", " + tbFrekuensi.getValueAt(1, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()), tbFrekuensi.getValueAt(2, 0).toString() + ", " + tbFrekuensi.getValueAt(2, 1).toString() + ", " + tbFrekuensi.getValueAt(2, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()), tbFrekuensi.getValueAt(3, 0).toString() + ", " + tbFrekuensi.getValueAt(3, 1).toString() + ", " + tbFrekuensi.getValueAt(3, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()), tbFrekuensi.getValueAt(4, 0).toString() + ", " + tbFrekuensi.getValueAt(4, 1).toString() + ", " + tbFrekuensi.getValueAt(4, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(5, 7).toString()), tbFrekuensi.getValueAt(5, 0).toString() + ", " + tbFrekuensi.getValueAt(5, 1).toString() + ", " + tbFrekuensi.getValueAt(5, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(6, 7).toString()), tbFrekuensi.getValueAt(6, 0).toString() + ", " + tbFrekuensi.getValueAt(6, 1).toString() + ", " + tbFrekuensi.getValueAt(6, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(7, 7).toString()), tbFrekuensi.getValueAt(7, 0).toString() + ", " + tbFrekuensi.getValueAt(7, 1).toString() + ", " + tbFrekuensi.getValueAt(7, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(8, 7).toString()), tbFrekuensi.getValueAt(8, 0).toString() + ", " + tbFrekuensi.getValueAt(8, 1).toString() + ", " + tbFrekuensi.getValueAt(8, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(9, 7).toString()), tbFrekuensi.getValueAt(9, 0).toString() + ", " + tbFrekuensi.getValueAt(9, 1).toString() + ", " + tbFrekuensi.getValueAt(9, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));

                JFreeChart freeChart = ChartFactory.createBarChart("10 Besar Penyakit Di Rawat Jalan", "Periode", "Jumlah Penyakit", dcd, PlotOrientation.VERTICAL, true, true, true); //String arg0,String arg1,String arg2,Category Datasheet,Plot Orientation,boolean arg4,boolean arg5,boolean arg6
                ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Jalan", freeChart);

                cf.setSize(internalFrame1.getWidth() - 50, internalFrame1.getHeight() - 50);
                cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                cf.setLocationRelativeTo(internalFrame1);
                cf.setAlwaysOnTop(false);
                cf.setVisible(true);

            } else if ((tbFrekuensi.getRowCount() > 8) && (Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()))
                    && (Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()))
                    && (Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()))
                    && (Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()))
                    && (Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(5, 7).toString()))
                    && (Integer.parseInt(tbFrekuensi.getValueAt(5, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(6, 7).toString()))
                    && (Integer.parseInt(tbFrekuensi.getValueAt(6, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(7, 7).toString()))
                    && (Integer.parseInt(tbFrekuensi.getValueAt(7, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(8, 7).toString()))) {
                DefaultCategoryDataset dcd = new DefaultCategoryDataset();
                dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()), tbFrekuensi.getValueAt(0, 0).toString() + ", " + tbFrekuensi.getValueAt(0, 1).toString() + ", " + tbFrekuensi.getValueAt(0, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()), tbFrekuensi.getValueAt(1, 0).toString() + ", " + tbFrekuensi.getValueAt(1, 1).toString() + ", " + tbFrekuensi.getValueAt(1, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()), tbFrekuensi.getValueAt(2, 0).toString() + ", " + tbFrekuensi.getValueAt(2, 1).toString() + ", " + tbFrekuensi.getValueAt(2, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()), tbFrekuensi.getValueAt(3, 0).toString() + ", " + tbFrekuensi.getValueAt(3, 1).toString() + ", " + tbFrekuensi.getValueAt(3, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()), tbFrekuensi.getValueAt(4, 0).toString() + ", " + tbFrekuensi.getValueAt(4, 1).toString() + ", " + tbFrekuensi.getValueAt(4, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(5, 7).toString()), tbFrekuensi.getValueAt(5, 0).toString() + ", " + tbFrekuensi.getValueAt(5, 1).toString() + ", " + tbFrekuensi.getValueAt(5, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(6, 7).toString()), tbFrekuensi.getValueAt(6, 0).toString() + ", " + tbFrekuensi.getValueAt(6, 1).toString() + ", " + tbFrekuensi.getValueAt(6, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(7, 7).toString()), tbFrekuensi.getValueAt(7, 0).toString() + ", " + tbFrekuensi.getValueAt(7, 1).toString() + ", " + tbFrekuensi.getValueAt(7, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(8, 7).toString()), tbFrekuensi.getValueAt(8, 0).toString() + ", " + tbFrekuensi.getValueAt(8, 1).toString() + ", " + tbFrekuensi.getValueAt(8, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));

                JFreeChart freeChart = ChartFactory.createBarChart("10 Besar Penyakit Di Rawat Jalan", "Periode", "Jumlah Penyakit", dcd, PlotOrientation.VERTICAL, true, true, true); //String arg0,String arg1,String arg2,Category Datasheet,Plot Orientation,boolean arg4,boolean arg5,boolean arg6
                ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Jalan", freeChart);

                cf.setSize(internalFrame1.getWidth() - 50, internalFrame1.getHeight() - 50);
                cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                cf.setLocationRelativeTo(internalFrame1);
                cf.setAlwaysOnTop(false);
                cf.setVisible(true);

            } else if ((tbFrekuensi.getRowCount() > 7) && (Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()))
                    && (Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()))
                    && (Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()))
                    && (Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()))
                    && (Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(5, 7).toString()))
                    && (Integer.parseInt(tbFrekuensi.getValueAt(5, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(6, 7).toString()))
                    && (Integer.parseInt(tbFrekuensi.getValueAt(6, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(7, 7).toString()))) {
                DefaultCategoryDataset dcd = new DefaultCategoryDataset();
                dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()), tbFrekuensi.getValueAt(0, 0).toString() + ", " + tbFrekuensi.getValueAt(0, 1).toString() + ", " + tbFrekuensi.getValueAt(0, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()), tbFrekuensi.getValueAt(1, 0).toString() + ", " + tbFrekuensi.getValueAt(1, 1).toString() + ", " + tbFrekuensi.getValueAt(1, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()), tbFrekuensi.getValueAt(2, 0).toString() + ", " + tbFrekuensi.getValueAt(2, 1).toString() + ", " + tbFrekuensi.getValueAt(2, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()), tbFrekuensi.getValueAt(3, 0).toString() + ", " + tbFrekuensi.getValueAt(3, 1).toString() + ", " + tbFrekuensi.getValueAt(3, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()), tbFrekuensi.getValueAt(4, 0).toString() + ", " + tbFrekuensi.getValueAt(4, 1).toString() + ", " + tbFrekuensi.getValueAt(4, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(5, 7).toString()), tbFrekuensi.getValueAt(5, 0).toString() + ", " + tbFrekuensi.getValueAt(5, 1).toString() + ", " + tbFrekuensi.getValueAt(5, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(6, 7).toString()), tbFrekuensi.getValueAt(6, 0).toString() + ", " + tbFrekuensi.getValueAt(6, 1).toString() + ", " + tbFrekuensi.getValueAt(6, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(7, 7).toString()), tbFrekuensi.getValueAt(7, 0).toString() + ", " + tbFrekuensi.getValueAt(7, 1).toString() + ", " + tbFrekuensi.getValueAt(7, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));

                JFreeChart freeChart = ChartFactory.createBarChart("10 Besar Penyakit Di Rawat Jalan", "Periode", "Jumlah Penyakit", dcd, PlotOrientation.VERTICAL, true, true, true); //String arg0,String arg1,String arg2,Category Datasheet,Plot Orientation,boolean arg4,boolean arg5,boolean arg6
                ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Jalan", freeChart);

                cf.setSize(internalFrame1.getWidth() - 50, internalFrame1.getHeight() - 50);
                cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                cf.setLocationRelativeTo(internalFrame1);
                cf.setAlwaysOnTop(false);
                cf.setVisible(true);

            } else if ((tbFrekuensi.getRowCount() > 6) && (Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()))
                    && (Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()))
                    && (Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()))
                    && (Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()))
                    && (Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(5, 7).toString()))
                    && (Integer.parseInt(tbFrekuensi.getValueAt(5, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(6, 7).toString()))) {
                DefaultCategoryDataset dcd = new DefaultCategoryDataset();
                dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()), tbFrekuensi.getValueAt(0, 0).toString() + ", " + tbFrekuensi.getValueAt(0, 1).toString() + ", " + tbFrekuensi.getValueAt(0, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()), tbFrekuensi.getValueAt(1, 0).toString() + ", " + tbFrekuensi.getValueAt(1, 1).toString() + ", " + tbFrekuensi.getValueAt(1, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()), tbFrekuensi.getValueAt(2, 0).toString() + ", " + tbFrekuensi.getValueAt(2, 1).toString() + ", " + tbFrekuensi.getValueAt(2, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()), tbFrekuensi.getValueAt(3, 0).toString() + ", " + tbFrekuensi.getValueAt(3, 1).toString() + ", " + tbFrekuensi.getValueAt(3, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()), tbFrekuensi.getValueAt(4, 0).toString() + ", " + tbFrekuensi.getValueAt(4, 1).toString() + ", " + tbFrekuensi.getValueAt(4, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(5, 7).toString()), tbFrekuensi.getValueAt(5, 0).toString() + ", " + tbFrekuensi.getValueAt(5, 1).toString() + ", " + tbFrekuensi.getValueAt(5, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(6, 7).toString()), tbFrekuensi.getValueAt(6, 0).toString() + ", " + tbFrekuensi.getValueAt(6, 1).toString() + ", " + tbFrekuensi.getValueAt(6, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));

                JFreeChart freeChart = ChartFactory.createBarChart("10 Besar Penyakit Di Rawat Jalan", "Periode", "Jumlah Penyakit", dcd, PlotOrientation.VERTICAL, true, true, true); //String arg0,String arg1,String arg2,Category Datasheet,Plot Orientation,boolean arg4,boolean arg5,boolean arg6
                ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Jalan", freeChart);

                cf.setSize(internalFrame1.getWidth() - 50, internalFrame1.getHeight() - 50);
                cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                cf.setLocationRelativeTo(internalFrame1);
                cf.setAlwaysOnTop(false);
                cf.setVisible(true);

            } else if ((tbFrekuensi.getRowCount() > 5) && (Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()))
                    && (Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()))
                    && (Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()))
                    && (Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()))
                    && (Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(5, 7).toString()))) {
                DefaultCategoryDataset dcd = new DefaultCategoryDataset();
                dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()), tbFrekuensi.getValueAt(0, 0).toString() + ", " + tbFrekuensi.getValueAt(0, 1).toString() + ", " + tbFrekuensi.getValueAt(0, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()), tbFrekuensi.getValueAt(1, 0).toString() + ", " + tbFrekuensi.getValueAt(1, 1).toString() + ", " + tbFrekuensi.getValueAt(1, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()), tbFrekuensi.getValueAt(2, 0).toString() + ", " + tbFrekuensi.getValueAt(2, 1).toString() + ", " + tbFrekuensi.getValueAt(2, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()), tbFrekuensi.getValueAt(3, 0).toString() + ", " + tbFrekuensi.getValueAt(3, 1).toString() + ", " + tbFrekuensi.getValueAt(3, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()), tbFrekuensi.getValueAt(4, 0).toString() + ", " + tbFrekuensi.getValueAt(4, 1).toString() + ", " + tbFrekuensi.getValueAt(4, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(5, 7).toString()), tbFrekuensi.getValueAt(5, 0).toString() + ", " + tbFrekuensi.getValueAt(5, 1).toString() + ", " + tbFrekuensi.getValueAt(5, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));

                JFreeChart freeChart = ChartFactory.createBarChart("10 Besar Penyakit Di Rawat Jalan", "Periode", "Jumlah Penyakit", dcd, PlotOrientation.VERTICAL, true, true, true); //String arg0,String arg1,String arg2,Category Datasheet,Plot Orientation,boolean arg4,boolean arg5,boolean arg6
                ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Jalan", freeChart);

                cf.setSize(internalFrame1.getWidth() - 50, internalFrame1.getHeight() - 50);
                cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                cf.setLocationRelativeTo(internalFrame1);
                cf.setAlwaysOnTop(false);
                cf.setVisible(true);

            } else if ((tbFrekuensi.getRowCount() > 4) && (Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()))
                    && (Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()))
                    && (Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()))
                    && (Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()))) {
                DefaultCategoryDataset dcd = new DefaultCategoryDataset();
                dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()), tbFrekuensi.getValueAt(0, 0).toString() + ", " + tbFrekuensi.getValueAt(0, 1).toString() + ", " + tbFrekuensi.getValueAt(0, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()), tbFrekuensi.getValueAt(1, 0).toString() + ", " + tbFrekuensi.getValueAt(1, 1).toString() + ", " + tbFrekuensi.getValueAt(1, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()), tbFrekuensi.getValueAt(2, 0).toString() + ", " + tbFrekuensi.getValueAt(2, 1).toString() + ", " + tbFrekuensi.getValueAt(2, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()), tbFrekuensi.getValueAt(3, 0).toString() + ", " + tbFrekuensi.getValueAt(3, 1).toString() + ", " + tbFrekuensi.getValueAt(3, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()), tbFrekuensi.getValueAt(4, 0).toString() + ", " + tbFrekuensi.getValueAt(4, 1).toString() + ", " + tbFrekuensi.getValueAt(4, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));

                JFreeChart freeChart = ChartFactory.createBarChart("10 Besar Penyakit Di Rawat Jalan", "Periode", "Jumlah Penyakit", dcd, PlotOrientation.VERTICAL, true, true, true); //String arg0,String arg1,String arg2,Category Datasheet,Plot Orientation,boolean arg4,boolean arg5,boolean arg6
                ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Jalan", freeChart);

                cf.setSize(internalFrame1.getWidth() - 50, internalFrame1.getHeight() - 50);
                cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                cf.setLocationRelativeTo(internalFrame1);
                cf.setAlwaysOnTop(false);
                cf.setVisible(true);

            } else if ((tbFrekuensi.getRowCount() > 3) && (Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()))
                    && (Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()))
                    && (Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()))) {
                DefaultCategoryDataset dcd = new DefaultCategoryDataset();
                dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()), tbFrekuensi.getValueAt(0, 0).toString() + ", " + tbFrekuensi.getValueAt(0, 1).toString() + ", " + tbFrekuensi.getValueAt(0, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()), tbFrekuensi.getValueAt(1, 0).toString() + ", " + tbFrekuensi.getValueAt(1, 1).toString() + ", " + tbFrekuensi.getValueAt(1, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()), tbFrekuensi.getValueAt(2, 0).toString() + ", " + tbFrekuensi.getValueAt(2, 1).toString() + ", " + tbFrekuensi.getValueAt(2, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()), tbFrekuensi.getValueAt(3, 0).toString() + ", " + tbFrekuensi.getValueAt(3, 1).toString() + ", " + tbFrekuensi.getValueAt(3, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));

                JFreeChart freeChart = ChartFactory.createBarChart("10 Besar Penyakit Di Rawat Jalan", "Periode", "Jumlah Penyakit", dcd, PlotOrientation.VERTICAL, true, true, true); //String arg0,String arg1,String arg2,Category Datasheet,Plot Orientation,boolean arg4,boolean arg5,boolean arg6
                ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Jalan", freeChart);

                cf.setSize(internalFrame1.getWidth() - 50, internalFrame1.getHeight() - 50);
                cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                cf.setLocationRelativeTo(internalFrame1);
                cf.setAlwaysOnTop(false);
                cf.setVisible(true);

            } else if ((tbFrekuensi.getRowCount() > 2) && (Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()))
                    && (Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()))) {
                DefaultCategoryDataset dcd = new DefaultCategoryDataset();
                dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()), tbFrekuensi.getValueAt(0, 0).toString() + ", " + tbFrekuensi.getValueAt(0, 1).toString() + ", " + tbFrekuensi.getValueAt(0, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()), tbFrekuensi.getValueAt(1, 0).toString() + ", " + tbFrekuensi.getValueAt(1, 1).toString() + ", " + tbFrekuensi.getValueAt(1, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()), tbFrekuensi.getValueAt(2, 0).toString() + ", " + tbFrekuensi.getValueAt(2, 1).toString() + ", " + tbFrekuensi.getValueAt(2, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));

                JFreeChart freeChart = ChartFactory.createBarChart("10 Besar Penyakit Di Rawat Jalan", "Periode", "Jumlah Penyakit", dcd, PlotOrientation.VERTICAL, true, true, true); //String arg0,String arg1,String arg2,Category Datasheet,Plot Orientation,boolean arg4,boolean arg5,boolean arg6
                ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Jalan", freeChart);

                cf.setSize(internalFrame1.getWidth() - 50, internalFrame1.getHeight() - 50);
                cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                cf.setLocationRelativeTo(internalFrame1);
                cf.setAlwaysOnTop(false);
                cf.setVisible(true);

            } else if ((tbFrekuensi.getRowCount() > 1) && (Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()))) {
                DefaultCategoryDataset dcd = new DefaultCategoryDataset();
                dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()), tbFrekuensi.getValueAt(0, 0).toString() + ", " + tbFrekuensi.getValueAt(0, 1).toString() + ", " + tbFrekuensi.getValueAt(0, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()), tbFrekuensi.getValueAt(1, 0).toString() + ", " + tbFrekuensi.getValueAt(1, 1).toString() + ", " + tbFrekuensi.getValueAt(1, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));

                JFreeChart freeChart = ChartFactory.createBarChart("10 Besar Penyakit Di Rawat Jalan", "Periode", "Jumlah Penyakit", dcd, PlotOrientation.VERTICAL, true, true, true); //String arg0,String arg1,String arg2,Category Datasheet,Plot Orientation,boolean arg4,boolean arg5,boolean arg6
                ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Jalan", freeChart);

                cf.setSize(internalFrame1.getWidth() - 50, internalFrame1.getHeight() - 50);
                cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
                cf.setLocationRelativeTo(internalFrame1);
                cf.setAlwaysOnTop(false);
                cf.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(null, "Maaf, Silahkan urutkan secara Descanding/Besar Ke Kecil dengan menklik pada kolom jumlah..!!!!");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Untuk mencetak grafik batang 10 penyakit terbanyak, silakan koordinasi dg. Inst. Rekam Medik...!!!");
            Tgl1.requestFocus();
        }
    }//GEN-LAST:event_ppGrafikTerbanyakBatangActionPerformed

private void btnPenyakitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPenyakitActionPerformed
        penyakit.isCek();
        penyakit.setSize(internalFrame1.getWidth()-40,internalFrame1.getHeight()-40);
        penyakit.setLocationRelativeTo(internalFrame1);
        penyakit.setVisible(true);
}//GEN-LAST:event_btnPenyakitActionPerformed

private void btnPenyakitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPenyakitKeyPressed
   //Valid.pindah(evt,DTPCari2,TCari);
}//GEN-LAST:event_btnPenyakitKeyPressed

private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
    if (TabRawat.getSelectedIndex() == 0) {
        tampil1();
    } else if (TabRawat.getSelectedIndex() == 1) {
        tampil2();
    }
}//GEN-LAST:event_BtnCariActionPerformed

private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCariActionPerformed(null);
        }else{
            //Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

private void ppGrafikTerbanyakPieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikTerbanyakPieActionPerformed
    if (akses.getkode().equals("Admin Utama") || akses.getpenyakit() == true) {

        if ((tbFrekuensi.getRowCount() > 9) && (Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(5, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(5, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(6, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(6, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(7, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(7, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(8, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(8, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(9, 7).toString()))) {
            DefaultPieDataset dpd = new DefaultPieDataset();
            dpd.setValue(tbFrekuensi.getValueAt(0, 0).toString() + ", " + tbFrekuensi.getValueAt(0, 1).toString() + ", " + tbFrekuensi.getValueAt(0, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(1, 0).toString() + ", " + tbFrekuensi.getValueAt(1, 1).toString() + ", " + tbFrekuensi.getValueAt(1, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(2, 0).toString() + ", " + tbFrekuensi.getValueAt(2, 1).toString() + ", " + tbFrekuensi.getValueAt(2, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(3, 0).toString() + ", " + tbFrekuensi.getValueAt(3, 1).toString() + ", " + tbFrekuensi.getValueAt(3, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(4, 0).toString() + ", " + tbFrekuensi.getValueAt(4, 1).toString() + ", " + tbFrekuensi.getValueAt(4, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(5, 0).toString() + ", " + tbFrekuensi.getValueAt(5, 1).toString() + ", " + tbFrekuensi.getValueAt(5, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(5, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(6, 0).toString() + ", " + tbFrekuensi.getValueAt(6, 1).toString() + ", " + tbFrekuensi.getValueAt(6, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(6, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(7, 0).toString() + ", " + tbFrekuensi.getValueAt(7, 1).toString() + ", " + tbFrekuensi.getValueAt(7, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(7, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(8, 0).toString() + ", " + tbFrekuensi.getValueAt(8, 1).toString() + ", " + tbFrekuensi.getValueAt(8, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(8, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(9, 0).toString() + ", " + tbFrekuensi.getValueAt(9, 1).toString() + ", " + tbFrekuensi.getValueAt(9, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(9, 7).toString()));

            JFreeChart freeChart = ChartFactory.createPieChart("10 Besar Penyakit Di Rawat Jalan Periode " + Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""), dpd, true, true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url 
            ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Jalan ", freeChart);
            cf.setSize(internalFrame1.getWidth() - 50, internalFrame1.getHeight() - 50);
            cf.setLocationRelativeTo(internalFrame1);
            cf.setAlwaysOnTop(false);
            cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
            cf.setVisible(true);
        } else if ((tbFrekuensi.getRowCount() > 8) && (Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(5, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(5, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(6, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(6, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(7, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(7, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(8, 7).toString()))) {
            DefaultPieDataset dpd = new DefaultPieDataset();
            dpd.setValue(tbFrekuensi.getValueAt(0, 0).toString() + ", " + tbFrekuensi.getValueAt(0, 1).toString() + ", " + tbFrekuensi.getValueAt(0, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(1, 0).toString() + ", " + tbFrekuensi.getValueAt(1, 1).toString() + ", " + tbFrekuensi.getValueAt(1, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(2, 0).toString() + ", " + tbFrekuensi.getValueAt(2, 1).toString() + ", " + tbFrekuensi.getValueAt(2, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(3, 0).toString() + ", " + tbFrekuensi.getValueAt(3, 1).toString() + ", " + tbFrekuensi.getValueAt(3, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(4, 0).toString() + ", " + tbFrekuensi.getValueAt(4, 1).toString() + ", " + tbFrekuensi.getValueAt(4, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(5, 0).toString() + ", " + tbFrekuensi.getValueAt(5, 1).toString() + ", " + tbFrekuensi.getValueAt(5, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(5, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(6, 0).toString() + ", " + tbFrekuensi.getValueAt(6, 1).toString() + ", " + tbFrekuensi.getValueAt(6, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(6, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(7, 0).toString() + ", " + tbFrekuensi.getValueAt(7, 1).toString() + ", " + tbFrekuensi.getValueAt(7, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(7, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(8, 0).toString() + ", " + tbFrekuensi.getValueAt(8, 1).toString() + ", " + tbFrekuensi.getValueAt(8, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(8, 7).toString()));

            JFreeChart freeChart = ChartFactory.createPieChart("10 Besar Penyakit Di Rawat Jalan Periode " + Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""), dpd, true, true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url 
            ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Jalan ", freeChart);
            cf.setSize(internalFrame1.getWidth() - 50, internalFrame1.getHeight() - 50);
            cf.setLocationRelativeTo(internalFrame1);
            cf.setAlwaysOnTop(false);
            cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
            cf.setVisible(true);

        } else if ((tbFrekuensi.getRowCount() > 7) && (Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(5, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(5, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(6, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(6, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(7, 7).toString()))) {
            DefaultPieDataset dpd = new DefaultPieDataset();
            dpd.setValue(tbFrekuensi.getValueAt(0, 0).toString() + ", " + tbFrekuensi.getValueAt(0, 1).toString() + ", " + tbFrekuensi.getValueAt(0, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(1, 0).toString() + ", " + tbFrekuensi.getValueAt(1, 1).toString() + ", " + tbFrekuensi.getValueAt(1, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(2, 0).toString() + ", " + tbFrekuensi.getValueAt(2, 1).toString() + ", " + tbFrekuensi.getValueAt(2, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(3, 0).toString() + ", " + tbFrekuensi.getValueAt(3, 1).toString() + ", " + tbFrekuensi.getValueAt(3, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(4, 0).toString() + ", " + tbFrekuensi.getValueAt(4, 1).toString() + ", " + tbFrekuensi.getValueAt(4, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(5, 0).toString() + ", " + tbFrekuensi.getValueAt(5, 1).toString() + ", " + tbFrekuensi.getValueAt(5, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(5, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(6, 0).toString() + ", " + tbFrekuensi.getValueAt(6, 1).toString() + ", " + tbFrekuensi.getValueAt(6, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(6, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(7, 0).toString() + ", " + tbFrekuensi.getValueAt(7, 1).toString() + ", " + tbFrekuensi.getValueAt(7, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(7, 7).toString()));

            JFreeChart freeChart = ChartFactory.createPieChart("10 Besar Penyakit Di Rawat Jalan Periode " + Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""), dpd, true, true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url 
            ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Jalan ", freeChart);
            cf.setSize(internalFrame1.getWidth() - 50, internalFrame1.getHeight() - 50);
            cf.setLocationRelativeTo(internalFrame1);
            cf.setAlwaysOnTop(false);
            cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
            cf.setVisible(true);

        } else if ((tbFrekuensi.getRowCount() > 6) && (Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(5, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(5, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(6, 7).toString()))) {
            DefaultPieDataset dpd = new DefaultPieDataset();
            dpd.setValue(tbFrekuensi.getValueAt(0, 0).toString() + ", " + tbFrekuensi.getValueAt(0, 1).toString() + ", " + tbFrekuensi.getValueAt(0, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(1, 0).toString() + ", " + tbFrekuensi.getValueAt(1, 1).toString() + ", " + tbFrekuensi.getValueAt(1, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(2, 0).toString() + ", " + tbFrekuensi.getValueAt(2, 1).toString() + ", " + tbFrekuensi.getValueAt(2, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(3, 0).toString() + ", " + tbFrekuensi.getValueAt(3, 1).toString() + ", " + tbFrekuensi.getValueAt(3, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(4, 0).toString() + ", " + tbFrekuensi.getValueAt(4, 1).toString() + ", " + tbFrekuensi.getValueAt(4, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(5, 0).toString() + ", " + tbFrekuensi.getValueAt(5, 1).toString() + ", " + tbFrekuensi.getValueAt(5, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(5, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(6, 0).toString() + ", " + tbFrekuensi.getValueAt(6, 1).toString() + ", " + tbFrekuensi.getValueAt(6, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(6, 7).toString()));

            JFreeChart freeChart = ChartFactory.createPieChart("10 Besar Penyakit Di Rawat Jalan Periode " + Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""), dpd, true, true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url 
            ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Jalan ", freeChart);
            cf.setSize(internalFrame1.getWidth() - 50, internalFrame1.getHeight() - 50);
            cf.setLocationRelativeTo(internalFrame1);
            cf.setAlwaysOnTop(false);
            cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
            cf.setVisible(true);

        } else if ((tbFrekuensi.getRowCount() > 5) && (Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(5, 7).toString()))) {
            DefaultPieDataset dpd = new DefaultPieDataset();
            dpd.setValue(tbFrekuensi.getValueAt(0, 0).toString() + ", " + tbFrekuensi.getValueAt(0, 1).toString() + ", " + tbFrekuensi.getValueAt(0, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(1, 0).toString() + ", " + tbFrekuensi.getValueAt(1, 1).toString() + ", " + tbFrekuensi.getValueAt(1, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(2, 0).toString() + ", " + tbFrekuensi.getValueAt(2, 1).toString() + ", " + tbFrekuensi.getValueAt(2, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(3, 0).toString() + ", " + tbFrekuensi.getValueAt(3, 1).toString() + ", " + tbFrekuensi.getValueAt(3, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(4, 0).toString() + ", " + tbFrekuensi.getValueAt(4, 1).toString() + ", " + tbFrekuensi.getValueAt(4, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(5, 0).toString() + ", " + tbFrekuensi.getValueAt(5, 1).toString() + ", " + tbFrekuensi.getValueAt(5, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(5, 7).toString()));

            JFreeChart freeChart = ChartFactory.createPieChart("10 Besar Penyakit Di Rawat Jalan Periode " + Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""), dpd, true, true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url 
            ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Jalan ", freeChart);
            cf.setSize(internalFrame1.getWidth() - 50, internalFrame1.getHeight() - 50);
            cf.setLocationRelativeTo(internalFrame1);
            cf.setAlwaysOnTop(false);
            cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
            cf.setVisible(true);

        } else if ((tbFrekuensi.getRowCount() > 4) && (Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()))) {
            DefaultPieDataset dpd = new DefaultPieDataset();
            dpd.setValue(tbFrekuensi.getValueAt(0, 0).toString() + ", " + tbFrekuensi.getValueAt(0, 1).toString() + ", " + tbFrekuensi.getValueAt(0, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(1, 0).toString() + ", " + tbFrekuensi.getValueAt(1, 1).toString() + ", " + tbFrekuensi.getValueAt(1, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(2, 0).toString() + ", " + tbFrekuensi.getValueAt(2, 1).toString() + ", " + tbFrekuensi.getValueAt(2, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(3, 0).toString() + ", " + tbFrekuensi.getValueAt(3, 1).toString() + ", " + tbFrekuensi.getValueAt(3, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(4, 0).toString() + ", " + tbFrekuensi.getValueAt(4, 1).toString() + ", " + tbFrekuensi.getValueAt(4, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()));

            JFreeChart freeChart = ChartFactory.createPieChart("10 Besar Penyakit Di Rawat Jalan Periode " + Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""), dpd, true, true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url 
            ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Jalan ", freeChart);
            cf.setSize(internalFrame1.getWidth() - 50, internalFrame1.getHeight() - 50);
            cf.setLocationRelativeTo(internalFrame1);
            cf.setAlwaysOnTop(false);
            cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
            cf.setVisible(true);

        } else if ((tbFrekuensi.getRowCount() > 3) && (Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()))) {
            DefaultPieDataset dpd = new DefaultPieDataset();
            dpd.setValue(tbFrekuensi.getValueAt(0, 0).toString() + ", " + tbFrekuensi.getValueAt(0, 1).toString() + ", " + tbFrekuensi.getValueAt(0, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(1, 0).toString() + ", " + tbFrekuensi.getValueAt(1, 1).toString() + ", " + tbFrekuensi.getValueAt(1, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(2, 0).toString() + ", " + tbFrekuensi.getValueAt(2, 1).toString() + ", " + tbFrekuensi.getValueAt(2, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(3, 0).toString() + ", " + tbFrekuensi.getValueAt(3, 1).toString() + ", " + tbFrekuensi.getValueAt(3, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()));

            JFreeChart freeChart = ChartFactory.createPieChart("10 Besar Penyakit Di Rawat Jalan Periode " + Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""), dpd, true, true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url 
            ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Jalan ", freeChart);
            cf.setSize(internalFrame1.getWidth() - 50, internalFrame1.getHeight() - 50);
            cf.setLocationRelativeTo(internalFrame1);
            cf.setAlwaysOnTop(false);
            cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
            cf.setVisible(true);

        } else if ((tbFrekuensi.getRowCount() > 2) && (Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()))) {
            DefaultPieDataset dpd = new DefaultPieDataset();
            dpd.setValue(tbFrekuensi.getValueAt(0, 0).toString() + ", " + tbFrekuensi.getValueAt(0, 1).toString() + ", " + tbFrekuensi.getValueAt(0, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(1, 0).toString() + ", " + tbFrekuensi.getValueAt(1, 1).toString() + ", " + tbFrekuensi.getValueAt(1, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(2, 0).toString() + ", " + tbFrekuensi.getValueAt(2, 1).toString() + ", " + tbFrekuensi.getValueAt(2, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()));

            JFreeChart freeChart = ChartFactory.createPieChart("10 Besar Penyakit Di Rawat Jalan Periode " + Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""), dpd, true, true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url 
            ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Jalan ", freeChart);
            cf.setSize(internalFrame1.getWidth() - 50, internalFrame1.getHeight() - 50);
            cf.setLocationRelativeTo(internalFrame1);
            cf.setAlwaysOnTop(false);
            cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
            cf.setVisible(true);

        } else if ((tbFrekuensi.getRowCount() > 1) && (Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()) >= Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()))) {
            DefaultPieDataset dpd = new DefaultPieDataset();
            dpd.setValue(tbFrekuensi.getValueAt(0, 0).toString() + ", " + tbFrekuensi.getValueAt(0, 1).toString() + ", " + tbFrekuensi.getValueAt(0, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(1, 0).toString() + ", " + tbFrekuensi.getValueAt(1, 1).toString() + ", " + tbFrekuensi.getValueAt(1, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()));

            JFreeChart freeChart = ChartFactory.createPieChart("10 Besar Penyakit Di Rawat Jalan Periode " + Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""), dpd, true, true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url 
            ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Jalan ", freeChart);
            cf.setSize(internalFrame1.getWidth() - 50, internalFrame1.getHeight() - 50);
            cf.setLocationRelativeTo(internalFrame1);
            cf.setAlwaysOnTop(false);
            cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
            cf.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan urutkan secara Descanding/Besar Ke Kecil dengan menklik pada kolom jumlah..!!!!");
        }
    } else {
        JOptionPane.showMessageDialog(null, "Untuk mencetak grafik pie 10 penyakit terbanyak, silakan koordinasi dg. Inst. Rekam Medik...!!!");
        Tgl1.requestFocus();
    }
}//GEN-LAST:event_ppGrafikTerbanyakPieActionPerformed

private void ppGrafikTerkecilBatangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikTerkecilBatangActionPerformed
    if (akses.getkode().equals("Admin Utama") || akses.getpenyakit() == true) {
        if ((tbFrekuensi.getRowCount() > 9) && (Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(5, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(5, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(6, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(6, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(7, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(7, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(8, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(8, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(9, 7).toString()))) {
            DefaultCategoryDataset dcd = new DefaultCategoryDataset();
            dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()), tbFrekuensi.getValueAt(0, 0).toString() + ", " + tbFrekuensi.getValueAt(0, 1).toString() + ", " + tbFrekuensi.getValueAt(0, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
            dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()), tbFrekuensi.getValueAt(1, 0).toString() + ", " + tbFrekuensi.getValueAt(1, 1).toString() + ", " + tbFrekuensi.getValueAt(1, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
            dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()), tbFrekuensi.getValueAt(2, 0).toString() + ", " + tbFrekuensi.getValueAt(2, 1).toString() + ", " + tbFrekuensi.getValueAt(2, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
            dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()), tbFrekuensi.getValueAt(3, 0).toString() + ", " + tbFrekuensi.getValueAt(3, 1).toString() + ", " + tbFrekuensi.getValueAt(3, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
            dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()), tbFrekuensi.getValueAt(4, 0).toString() + ", " + tbFrekuensi.getValueAt(4, 1).toString() + ", " + tbFrekuensi.getValueAt(4, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
            dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(5, 7).toString()), tbFrekuensi.getValueAt(5, 0).toString() + ", " + tbFrekuensi.getValueAt(5, 1).toString() + ", " + tbFrekuensi.getValueAt(5, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
            dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(6, 7).toString()), tbFrekuensi.getValueAt(6, 0).toString() + ", " + tbFrekuensi.getValueAt(6, 1).toString() + ", " + tbFrekuensi.getValueAt(6, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
            dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(7, 7).toString()), tbFrekuensi.getValueAt(7, 0).toString() + ", " + tbFrekuensi.getValueAt(7, 1).toString() + ", " + tbFrekuensi.getValueAt(7, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
            dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(8, 7).toString()), tbFrekuensi.getValueAt(8, 0).toString() + ", " + tbFrekuensi.getValueAt(8, 1).toString() + ", " + tbFrekuensi.getValueAt(8, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
            dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(9, 7).toString()), tbFrekuensi.getValueAt(9, 0).toString() + ", " + tbFrekuensi.getValueAt(9, 1).toString() + ", " + tbFrekuensi.getValueAt(9, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));

            JFreeChart freeChart = ChartFactory.createBarChart("10 Besar Penyakit Di Rawat Jalan", "Periode", "Jumlah Penyakit", dcd, PlotOrientation.VERTICAL, true, true, true); //String arg0,String arg1,String arg2,Category Datasheet,Plot Orientation,boolean arg4,boolean arg5,boolean arg6
            ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Jalan", freeChart);

            cf.setSize(internalFrame1.getWidth() - 50, internalFrame1.getHeight() - 50);
            cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
            cf.setLocationRelativeTo(internalFrame1);
            cf.setAlwaysOnTop(false);
            cf.setVisible(true);

        } else if ((tbFrekuensi.getRowCount() > 8) && (Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(5, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(5, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(6, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(6, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(7, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(7, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(8, 7).toString()))) {
            DefaultCategoryDataset dcd = new DefaultCategoryDataset();
            dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()), tbFrekuensi.getValueAt(0, 0).toString() + ", " + tbFrekuensi.getValueAt(0, 1).toString() + ", " + tbFrekuensi.getValueAt(0, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
            dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()), tbFrekuensi.getValueAt(1, 0).toString() + ", " + tbFrekuensi.getValueAt(1, 1).toString() + ", " + tbFrekuensi.getValueAt(1, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
            dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()), tbFrekuensi.getValueAt(2, 0).toString() + ", " + tbFrekuensi.getValueAt(2, 1).toString() + ", " + tbFrekuensi.getValueAt(2, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
            dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()), tbFrekuensi.getValueAt(3, 0).toString() + ", " + tbFrekuensi.getValueAt(3, 1).toString() + ", " + tbFrekuensi.getValueAt(3, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
            dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()), tbFrekuensi.getValueAt(4, 0).toString() + ", " + tbFrekuensi.getValueAt(4, 1).toString() + ", " + tbFrekuensi.getValueAt(4, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
            dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(5, 7).toString()), tbFrekuensi.getValueAt(5, 0).toString() + ", " + tbFrekuensi.getValueAt(5, 1).toString() + ", " + tbFrekuensi.getValueAt(5, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
            dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(6, 7).toString()), tbFrekuensi.getValueAt(6, 0).toString() + ", " + tbFrekuensi.getValueAt(6, 1).toString() + ", " + tbFrekuensi.getValueAt(6, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
            dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(7, 7).toString()), tbFrekuensi.getValueAt(7, 0).toString() + ", " + tbFrekuensi.getValueAt(7, 1).toString() + ", " + tbFrekuensi.getValueAt(7, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
            dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(8, 7).toString()), tbFrekuensi.getValueAt(8, 0).toString() + ", " + tbFrekuensi.getValueAt(8, 1).toString() + ", " + tbFrekuensi.getValueAt(8, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));

            JFreeChart freeChart = ChartFactory.createBarChart("10 Besar Penyakit Di Rawat Jalan", "Periode", "Jumlah Penyakit", dcd, PlotOrientation.VERTICAL, true, true, true); //String arg0,String arg1,String arg2,Category Datasheet,Plot Orientation,boolean arg4,boolean arg5,boolean arg6
            ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Jalan", freeChart);

            cf.setSize(internalFrame1.getWidth() - 50, internalFrame1.getHeight() - 50);
            cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
            cf.setLocationRelativeTo(internalFrame1);
            cf.setAlwaysOnTop(false);
            cf.setVisible(true);

        } else if ((tbFrekuensi.getRowCount() > 7) && (Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(5, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(5, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(6, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(6, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(7, 7).toString()))) {
            DefaultCategoryDataset dcd = new DefaultCategoryDataset();
            dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()), tbFrekuensi.getValueAt(0, 0).toString() + ", " + tbFrekuensi.getValueAt(0, 1).toString() + ", " + tbFrekuensi.getValueAt(0, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
            dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()), tbFrekuensi.getValueAt(1, 0).toString() + ", " + tbFrekuensi.getValueAt(1, 1).toString() + ", " + tbFrekuensi.getValueAt(1, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
            dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()), tbFrekuensi.getValueAt(2, 0).toString() + ", " + tbFrekuensi.getValueAt(2, 1).toString() + ", " + tbFrekuensi.getValueAt(2, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
            dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()), tbFrekuensi.getValueAt(3, 0).toString() + ", " + tbFrekuensi.getValueAt(3, 1).toString() + ", " + tbFrekuensi.getValueAt(3, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
            dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()), tbFrekuensi.getValueAt(4, 0).toString() + ", " + tbFrekuensi.getValueAt(4, 1).toString() + ", " + tbFrekuensi.getValueAt(4, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
            dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(5, 7).toString()), tbFrekuensi.getValueAt(5, 0).toString() + ", " + tbFrekuensi.getValueAt(5, 1).toString() + ", " + tbFrekuensi.getValueAt(5, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
            dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(6, 7).toString()), tbFrekuensi.getValueAt(6, 0).toString() + ", " + tbFrekuensi.getValueAt(6, 1).toString() + ", " + tbFrekuensi.getValueAt(6, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
            dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(7, 7).toString()), tbFrekuensi.getValueAt(7, 0).toString() + ", " + tbFrekuensi.getValueAt(7, 1).toString() + ", " + tbFrekuensi.getValueAt(7, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));

            JFreeChart freeChart = ChartFactory.createBarChart("10 Besar Penyakit Di Rawat Jalan", "Periode", "Jumlah Penyakit", dcd, PlotOrientation.VERTICAL, true, true, true); //String arg0,String arg1,String arg2,Category Datasheet,Plot Orientation,boolean arg4,boolean arg5,boolean arg6
            ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Jalan", freeChart);

            cf.setSize(internalFrame1.getWidth() - 50, internalFrame1.getHeight() - 50);
            cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
            cf.setLocationRelativeTo(internalFrame1);
            cf.setAlwaysOnTop(false);
            cf.setVisible(true);

        } else if ((tbFrekuensi.getRowCount() > 6) && (Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(5, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(5, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(6, 7).toString()))) {
            DefaultCategoryDataset dcd = new DefaultCategoryDataset();
            dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()), tbFrekuensi.getValueAt(0, 0).toString() + ", " + tbFrekuensi.getValueAt(0, 1).toString() + ", " + tbFrekuensi.getValueAt(0, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
            dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()), tbFrekuensi.getValueAt(1, 0).toString() + ", " + tbFrekuensi.getValueAt(1, 1).toString() + ", " + tbFrekuensi.getValueAt(1, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
            dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()), tbFrekuensi.getValueAt(2, 0).toString() + ", " + tbFrekuensi.getValueAt(2, 1).toString() + ", " + tbFrekuensi.getValueAt(2, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
            dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()), tbFrekuensi.getValueAt(3, 0).toString() + ", " + tbFrekuensi.getValueAt(3, 1).toString() + ", " + tbFrekuensi.getValueAt(3, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
            dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()), tbFrekuensi.getValueAt(4, 0).toString() + ", " + tbFrekuensi.getValueAt(4, 1).toString() + ", " + tbFrekuensi.getValueAt(4, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
            dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(5, 7).toString()), tbFrekuensi.getValueAt(5, 0).toString() + ", " + tbFrekuensi.getValueAt(5, 1).toString() + ", " + tbFrekuensi.getValueAt(5, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
            dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(6, 7).toString()), tbFrekuensi.getValueAt(6, 0).toString() + ", " + tbFrekuensi.getValueAt(6, 1).toString() + ", " + tbFrekuensi.getValueAt(6, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));

            JFreeChart freeChart = ChartFactory.createBarChart("10 Besar Penyakit Di Rawat Jalan", "Periode", "Jumlah Penyakit", dcd, PlotOrientation.VERTICAL, true, true, true); //String arg0,String arg1,String arg2,Category Datasheet,Plot Orientation,boolean arg4,boolean arg5,boolean arg6
            ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Jalan", freeChart);

            cf.setSize(internalFrame1.getWidth() - 50, internalFrame1.getHeight() - 50);
            cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
            cf.setLocationRelativeTo(internalFrame1);
            cf.setAlwaysOnTop(false);
            cf.setVisible(true);

        } else if ((tbFrekuensi.getRowCount() > 5) && (Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(5, 7).toString()))) {
            DefaultCategoryDataset dcd = new DefaultCategoryDataset();
            dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()), tbFrekuensi.getValueAt(0, 0).toString() + ", " + tbFrekuensi.getValueAt(0, 1).toString() + ", " + tbFrekuensi.getValueAt(0, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
            dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()), tbFrekuensi.getValueAt(1, 0).toString() + ", " + tbFrekuensi.getValueAt(1, 1).toString() + ", " + tbFrekuensi.getValueAt(1, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
            dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()), tbFrekuensi.getValueAt(2, 0).toString() + ", " + tbFrekuensi.getValueAt(2, 1).toString() + ", " + tbFrekuensi.getValueAt(2, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
            dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()), tbFrekuensi.getValueAt(3, 0).toString() + ", " + tbFrekuensi.getValueAt(3, 1).toString() + ", " + tbFrekuensi.getValueAt(3, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
            dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()), tbFrekuensi.getValueAt(4, 0).toString() + ", " + tbFrekuensi.getValueAt(4, 1).toString() + ", " + tbFrekuensi.getValueAt(4, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
            dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(5, 7).toString()), tbFrekuensi.getValueAt(5, 0).toString() + ", " + tbFrekuensi.getValueAt(5, 1).toString() + ", " + tbFrekuensi.getValueAt(5, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));

            JFreeChart freeChart = ChartFactory.createBarChart("10 Besar Penyakit Di Rawat Jalan", "Periode", "Jumlah Penyakit", dcd, PlotOrientation.VERTICAL, true, true, true); //String arg0,String arg1,String arg2,Category Datasheet,Plot Orientation,boolean arg4,boolean arg5,boolean arg6
            ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Jalan", freeChart);

            cf.setSize(internalFrame1.getWidth() - 50, internalFrame1.getHeight() - 50);
            cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
            cf.setLocationRelativeTo(internalFrame1);
            cf.setAlwaysOnTop(false);
            cf.setVisible(true);

        } else if ((tbFrekuensi.getRowCount() > 4) && (Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()))) {
            DefaultCategoryDataset dcd = new DefaultCategoryDataset();
            dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()), tbFrekuensi.getValueAt(0, 0).toString() + ", " + tbFrekuensi.getValueAt(0, 1).toString() + ", " + tbFrekuensi.getValueAt(0, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
            dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()), tbFrekuensi.getValueAt(1, 0).toString() + ", " + tbFrekuensi.getValueAt(1, 1).toString() + ", " + tbFrekuensi.getValueAt(1, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
            dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()), tbFrekuensi.getValueAt(2, 0).toString() + ", " + tbFrekuensi.getValueAt(2, 1).toString() + ", " + tbFrekuensi.getValueAt(2, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
            dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()), tbFrekuensi.getValueAt(3, 0).toString() + ", " + tbFrekuensi.getValueAt(3, 1).toString() + ", " + tbFrekuensi.getValueAt(3, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
            dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()), tbFrekuensi.getValueAt(4, 0).toString() + ", " + tbFrekuensi.getValueAt(4, 1).toString() + ", " + tbFrekuensi.getValueAt(4, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));

            JFreeChart freeChart = ChartFactory.createBarChart("10 Besar Penyakit Di Rawat Jalan", "Periode", "Jumlah Penyakit", dcd, PlotOrientation.VERTICAL, true, true, true); //String arg0,String arg1,String arg2,Category Datasheet,Plot Orientation,boolean arg4,boolean arg5,boolean arg6
            ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Jalan", freeChart);

            cf.setSize(internalFrame1.getWidth() - 50, internalFrame1.getHeight() - 50);
            cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
            cf.setLocationRelativeTo(internalFrame1);
            cf.setAlwaysOnTop(false);
            cf.setVisible(true);

        } else if ((tbFrekuensi.getRowCount() > 3) && (Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()))) {
            DefaultCategoryDataset dcd = new DefaultCategoryDataset();
            dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()), tbFrekuensi.getValueAt(0, 0).toString() + ", " + tbFrekuensi.getValueAt(0, 1).toString() + ", " + tbFrekuensi.getValueAt(0, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
            dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()), tbFrekuensi.getValueAt(1, 0).toString() + ", " + tbFrekuensi.getValueAt(1, 1).toString() + ", " + tbFrekuensi.getValueAt(1, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
            dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()), tbFrekuensi.getValueAt(2, 0).toString() + ", " + tbFrekuensi.getValueAt(2, 1).toString() + ", " + tbFrekuensi.getValueAt(2, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
            dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()), tbFrekuensi.getValueAt(3, 0).toString() + ", " + tbFrekuensi.getValueAt(3, 1).toString() + ", " + tbFrekuensi.getValueAt(3, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));

            JFreeChart freeChart = ChartFactory.createBarChart("10 Besar Penyakit Di Rawat Jalan", "Periode", "Jumlah Penyakit", dcd, PlotOrientation.VERTICAL, true, true, true); //String arg0,String arg1,String arg2,Category Datasheet,Plot Orientation,boolean arg4,boolean arg5,boolean arg6
            ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Jalan", freeChart);

            cf.setSize(internalFrame1.getWidth() - 50, internalFrame1.getHeight() - 50);
            cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
            cf.setLocationRelativeTo(internalFrame1);
            cf.setAlwaysOnTop(false);
            cf.setVisible(true);

        } else if ((tbFrekuensi.getRowCount() > 2) && (Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()))) {
            DefaultCategoryDataset dcd = new DefaultCategoryDataset();
            dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()), tbFrekuensi.getValueAt(0, 0).toString() + ", " + tbFrekuensi.getValueAt(0, 1).toString() + ", " + tbFrekuensi.getValueAt(0, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
            dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()), tbFrekuensi.getValueAt(1, 0).toString() + ", " + tbFrekuensi.getValueAt(1, 1).toString() + ", " + tbFrekuensi.getValueAt(1, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
            dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()), tbFrekuensi.getValueAt(2, 0).toString() + ", " + tbFrekuensi.getValueAt(2, 1).toString() + ", " + tbFrekuensi.getValueAt(2, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));

            JFreeChart freeChart = ChartFactory.createBarChart("10 Besar Penyakit Di Rawat Jalan", "Periode", "Jumlah Penyakit", dcd, PlotOrientation.VERTICAL, true, true, true); //String arg0,String arg1,String arg2,Category Datasheet,Plot Orientation,boolean arg4,boolean arg5,boolean arg6
            ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Jalan", freeChart);

            cf.setSize(internalFrame1.getWidth() - 50, internalFrame1.getHeight() - 50);
            cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
            cf.setLocationRelativeTo(internalFrame1);
            cf.setAlwaysOnTop(false);
            cf.setVisible(true);

        } else if ((tbFrekuensi.getRowCount() > 1) && (Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()))) {
            DefaultCategoryDataset dcd = new DefaultCategoryDataset();
            dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()), tbFrekuensi.getValueAt(0, 0).toString() + ", " + tbFrekuensi.getValueAt(0, 1).toString() + ", " + tbFrekuensi.getValueAt(0, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
            dcd.setValue(Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()), tbFrekuensi.getValueAt(1, 0).toString() + ", " + tbFrekuensi.getValueAt(1, 1).toString() + ", " + tbFrekuensi.getValueAt(1, 7).toString(), Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""));

            JFreeChart freeChart = ChartFactory.createBarChart("10 Besar Penyakit Di Rawat Jalan", "Periode", "Jumlah Penyakit", dcd, PlotOrientation.VERTICAL, true, true, true); //String arg0,String arg1,String arg2,Category Datasheet,Plot Orientation,boolean arg4,boolean arg5,boolean arg6
            ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Jalan", freeChart);

            cf.setSize(internalFrame1.getWidth() - 50, internalFrame1.getHeight() - 50);
            cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
            cf.setLocationRelativeTo(internalFrame1);
            cf.setAlwaysOnTop(false);
            cf.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan urutkan secara Descanding/Besar Ke Kecil dengan menklik pada kolom jumlah..!!!!");
        }
    } else {
        JOptionPane.showMessageDialog(null, "Untuk mencetak grafik batang 10 penyakit tersedikit, silakan koordinasi dg. Inst. Rekam Medik...!!!");
        Tgl1.requestFocus();
    }
}//GEN-LAST:event_ppGrafikTerkecilBatangActionPerformed

private void ppGrafikTerkecilPieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikTerkecilPieActionPerformed
        if (akses.getkode().equals("Admin Utama") || akses.getpenyakit() == true) {
        if ((tbFrekuensi.getRowCount() > 9) && (Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(5, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(5, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(6, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(6, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(7, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(7, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(8, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(8, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(9, 7).toString()))) {
            DefaultPieDataset dpd = new DefaultPieDataset();
            dpd.setValue(tbFrekuensi.getValueAt(0, 0).toString() + ", " + tbFrekuensi.getValueAt(0, 1).toString() + ", " + tbFrekuensi.getValueAt(0, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(1, 0).toString() + ", " + tbFrekuensi.getValueAt(1, 1).toString() + ", " + tbFrekuensi.getValueAt(1, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(2, 0).toString() + ", " + tbFrekuensi.getValueAt(2, 1).toString() + ", " + tbFrekuensi.getValueAt(2, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(3, 0).toString() + ", " + tbFrekuensi.getValueAt(3, 1).toString() + ", " + tbFrekuensi.getValueAt(3, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(4, 0).toString() + ", " + tbFrekuensi.getValueAt(4, 1).toString() + ", " + tbFrekuensi.getValueAt(4, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(5, 0).toString() + ", " + tbFrekuensi.getValueAt(5, 1).toString() + ", " + tbFrekuensi.getValueAt(5, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(5, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(6, 0).toString() + ", " + tbFrekuensi.getValueAt(6, 1).toString() + ", " + tbFrekuensi.getValueAt(6, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(6, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(7, 0).toString() + ", " + tbFrekuensi.getValueAt(7, 1).toString() + ", " + tbFrekuensi.getValueAt(7, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(7, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(8, 0).toString() + ", " + tbFrekuensi.getValueAt(8, 1).toString() + ", " + tbFrekuensi.getValueAt(8, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(8, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(9, 0).toString() + ", " + tbFrekuensi.getValueAt(9, 1).toString() + ", " + tbFrekuensi.getValueAt(9, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(9, 7).toString()));

            JFreeChart freeChart = ChartFactory.createPieChart("10 Besar Penyakit Di Rawat Jalan Periode " + Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""), dpd, true, true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url 
            ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Jalan ", freeChart);
            cf.setSize(internalFrame1.getWidth() - 50, internalFrame1.getHeight() - 50);
            cf.setLocationRelativeTo(internalFrame1);
            cf.setAlwaysOnTop(false);
            cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
            cf.setVisible(true);
        } else if ((tbFrekuensi.getRowCount() > 8) && (Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(5, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(5, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(6, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(6, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(7, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(7, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(8, 7).toString()))) {
            DefaultPieDataset dpd = new DefaultPieDataset();
            dpd.setValue(tbFrekuensi.getValueAt(0, 0).toString() + ", " + tbFrekuensi.getValueAt(0, 1).toString() + ", " + tbFrekuensi.getValueAt(0, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(1, 0).toString() + ", " + tbFrekuensi.getValueAt(1, 1).toString() + ", " + tbFrekuensi.getValueAt(1, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(2, 0).toString() + ", " + tbFrekuensi.getValueAt(2, 1).toString() + ", " + tbFrekuensi.getValueAt(2, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(3, 0).toString() + ", " + tbFrekuensi.getValueAt(3, 1).toString() + ", " + tbFrekuensi.getValueAt(3, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(4, 0).toString() + ", " + tbFrekuensi.getValueAt(4, 1).toString() + ", " + tbFrekuensi.getValueAt(4, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(5, 0).toString() + ", " + tbFrekuensi.getValueAt(5, 1).toString() + ", " + tbFrekuensi.getValueAt(5, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(5, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(6, 0).toString() + ", " + tbFrekuensi.getValueAt(6, 1).toString() + ", " + tbFrekuensi.getValueAt(6, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(6, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(7, 0).toString() + ", " + tbFrekuensi.getValueAt(7, 1).toString() + ", " + tbFrekuensi.getValueAt(7, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(7, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(8, 0).toString() + ", " + tbFrekuensi.getValueAt(8, 1).toString() + ", " + tbFrekuensi.getValueAt(8, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(8, 7).toString()));

            JFreeChart freeChart = ChartFactory.createPieChart("10 Besar Penyakit Di Rawat Jalan Periode " + Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""), dpd, true, true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url 
            ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Jalan ", freeChart);
            cf.setSize(internalFrame1.getWidth() - 50, internalFrame1.getHeight() - 50);
            cf.setLocationRelativeTo(internalFrame1);
            cf.setAlwaysOnTop(false);
            cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
            cf.setVisible(true);

        } else if ((tbFrekuensi.getRowCount() > 7) && (Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(5, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(5, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(6, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(6, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(7, 7).toString()))) {
            DefaultPieDataset dpd = new DefaultPieDataset();
            dpd.setValue(tbFrekuensi.getValueAt(0, 0).toString() + ", " + tbFrekuensi.getValueAt(0, 1).toString() + ", " + tbFrekuensi.getValueAt(0, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(1, 0).toString() + ", " + tbFrekuensi.getValueAt(1, 1).toString() + ", " + tbFrekuensi.getValueAt(1, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(2, 0).toString() + ", " + tbFrekuensi.getValueAt(2, 1).toString() + ", " + tbFrekuensi.getValueAt(2, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(3, 0).toString() + ", " + tbFrekuensi.getValueAt(3, 1).toString() + ", " + tbFrekuensi.getValueAt(3, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(4, 0).toString() + ", " + tbFrekuensi.getValueAt(4, 1).toString() + ", " + tbFrekuensi.getValueAt(4, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(5, 0).toString() + ", " + tbFrekuensi.getValueAt(5, 1).toString() + ", " + tbFrekuensi.getValueAt(5, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(5, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(6, 0).toString() + ", " + tbFrekuensi.getValueAt(6, 1).toString() + ", " + tbFrekuensi.getValueAt(6, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(6, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(7, 0).toString() + ", " + tbFrekuensi.getValueAt(7, 1).toString() + ", " + tbFrekuensi.getValueAt(7, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(7, 7).toString()));

            JFreeChart freeChart = ChartFactory.createPieChart("10 Besar Penyakit Di Rawat Jalan Periode " + Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""), dpd, true, true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url 
            ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Jalan ", freeChart);
            cf.setSize(internalFrame1.getWidth() - 50, internalFrame1.getHeight() - 50);
            cf.setLocationRelativeTo(internalFrame1);
            cf.setAlwaysOnTop(false);
            cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
            cf.setVisible(true);

        } else if ((tbFrekuensi.getRowCount() > 6) && (Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(5, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(5, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(6, 7).toString()))) {
            DefaultPieDataset dpd = new DefaultPieDataset();
            dpd.setValue(tbFrekuensi.getValueAt(0, 0).toString() + ", " + tbFrekuensi.getValueAt(0, 1).toString() + ", " + tbFrekuensi.getValueAt(0, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(1, 0).toString() + ", " + tbFrekuensi.getValueAt(1, 1).toString() + ", " + tbFrekuensi.getValueAt(1, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(2, 0).toString() + ", " + tbFrekuensi.getValueAt(2, 1).toString() + ", " + tbFrekuensi.getValueAt(2, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(3, 0).toString() + ", " + tbFrekuensi.getValueAt(3, 1).toString() + ", " + tbFrekuensi.getValueAt(3, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(4, 0).toString() + ", " + tbFrekuensi.getValueAt(4, 1).toString() + ", " + tbFrekuensi.getValueAt(4, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(5, 0).toString() + ", " + tbFrekuensi.getValueAt(5, 1).toString() + ", " + tbFrekuensi.getValueAt(5, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(5, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(6, 0).toString() + ", " + tbFrekuensi.getValueAt(6, 1).toString() + ", " + tbFrekuensi.getValueAt(6, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(6, 7).toString()));

            JFreeChart freeChart = ChartFactory.createPieChart("10 Besar Penyakit Di Rawat Jalan Periode " + Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""), dpd, true, true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url 
            ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Jalan ", freeChart);
            cf.setSize(internalFrame1.getWidth() - 50, internalFrame1.getHeight() - 50);
            cf.setLocationRelativeTo(internalFrame1);
            cf.setAlwaysOnTop(false);
            cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
            cf.setVisible(true);

        } else if ((tbFrekuensi.getRowCount() > 5) && (Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(5, 7).toString()))) {
            DefaultPieDataset dpd = new DefaultPieDataset();
            dpd.setValue(tbFrekuensi.getValueAt(0, 0).toString() + ", " + tbFrekuensi.getValueAt(0, 1).toString() + ", " + tbFrekuensi.getValueAt(0, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(1, 0).toString() + ", " + tbFrekuensi.getValueAt(1, 1).toString() + ", " + tbFrekuensi.getValueAt(1, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(2, 0).toString() + ", " + tbFrekuensi.getValueAt(2, 1).toString() + ", " + tbFrekuensi.getValueAt(2, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(3, 0).toString() + ", " + tbFrekuensi.getValueAt(3, 1).toString() + ", " + tbFrekuensi.getValueAt(3, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(4, 0).toString() + ", " + tbFrekuensi.getValueAt(4, 1).toString() + ", " + tbFrekuensi.getValueAt(4, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(5, 0).toString() + ", " + tbFrekuensi.getValueAt(5, 1).toString() + ", " + tbFrekuensi.getValueAt(5, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(5, 7).toString()));

            JFreeChart freeChart = ChartFactory.createPieChart("10 Besar Penyakit Di Rawat Jalan Periode " + Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""), dpd, true, true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url 
            ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Jalan ", freeChart);
            cf.setSize(internalFrame1.getWidth() - 50, internalFrame1.getHeight() - 50);
            cf.setLocationRelativeTo(internalFrame1);
            cf.setAlwaysOnTop(false);
            cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
            cf.setVisible(true);

        } else if ((tbFrekuensi.getRowCount() > 4) && (Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()))) {
            DefaultPieDataset dpd = new DefaultPieDataset();
            dpd.setValue(tbFrekuensi.getValueAt(0, 0).toString() + ", " + tbFrekuensi.getValueAt(0, 1).toString() + ", " + tbFrekuensi.getValueAt(0, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(1, 0).toString() + ", " + tbFrekuensi.getValueAt(1, 1).toString() + ", " + tbFrekuensi.getValueAt(1, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(2, 0).toString() + ", " + tbFrekuensi.getValueAt(2, 1).toString() + ", " + tbFrekuensi.getValueAt(2, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(3, 0).toString() + ", " + tbFrekuensi.getValueAt(3, 1).toString() + ", " + tbFrekuensi.getValueAt(3, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(4, 0).toString() + ", " + tbFrekuensi.getValueAt(4, 1).toString() + ", " + tbFrekuensi.getValueAt(4, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(4, 7).toString()));

            JFreeChart freeChart = ChartFactory.createPieChart("10 Besar Penyakit Di Rawat Jalan Periode " + Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""), dpd, true, true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url 
            ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Jalan ", freeChart);
            cf.setSize(internalFrame1.getWidth() - 50, internalFrame1.getHeight() - 50);
            cf.setLocationRelativeTo(internalFrame1);
            cf.setAlwaysOnTop(false);
            cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
            cf.setVisible(true);

        } else if ((tbFrekuensi.getRowCount() > 3) && (Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()))) {
            DefaultPieDataset dpd = new DefaultPieDataset();
            dpd.setValue(tbFrekuensi.getValueAt(0, 0).toString() + ", " + tbFrekuensi.getValueAt(0, 1).toString() + ", " + tbFrekuensi.getValueAt(0, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(1, 0).toString() + ", " + tbFrekuensi.getValueAt(1, 1).toString() + ", " + tbFrekuensi.getValueAt(1, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(2, 0).toString() + ", " + tbFrekuensi.getValueAt(2, 1).toString() + ", " + tbFrekuensi.getValueAt(2, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(3, 0).toString() + ", " + tbFrekuensi.getValueAt(3, 1).toString() + ", " + tbFrekuensi.getValueAt(3, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(3, 7).toString()));

            JFreeChart freeChart = ChartFactory.createPieChart("10 Besar Penyakit Di Rawat Jalan Periode " + Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""), dpd, true, true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url 
            ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Jalan ", freeChart);
            cf.setSize(internalFrame1.getWidth() - 50, internalFrame1.getHeight() - 50);
            cf.setLocationRelativeTo(internalFrame1);
            cf.setAlwaysOnTop(false);
            cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
            cf.setVisible(true);

        } else if ((tbFrekuensi.getRowCount() > 2) && (Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()))
                && (Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()))) {
            DefaultPieDataset dpd = new DefaultPieDataset();
            dpd.setValue(tbFrekuensi.getValueAt(0, 0).toString() + ", " + tbFrekuensi.getValueAt(0, 1).toString() + ", " + tbFrekuensi.getValueAt(0, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(1, 0).toString() + ", " + tbFrekuensi.getValueAt(1, 1).toString() + ", " + tbFrekuensi.getValueAt(1, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(2, 0).toString() + ", " + tbFrekuensi.getValueAt(2, 1).toString() + ", " + tbFrekuensi.getValueAt(2, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(2, 7).toString()));

            JFreeChart freeChart = ChartFactory.createPieChart("10 Besar Penyakit Di Rawat Jalan Periode " + Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""), dpd, true, true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url 
            ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Jalan ", freeChart);
            cf.setSize(internalFrame1.getWidth() - 50, internalFrame1.getHeight() - 50);
            cf.setLocationRelativeTo(internalFrame1);
            cf.setAlwaysOnTop(false);
            cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
            cf.setVisible(true);

        } else if ((tbFrekuensi.getRowCount() > 1) && (Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()) <= Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()))) {
            DefaultPieDataset dpd = new DefaultPieDataset();
            dpd.setValue(tbFrekuensi.getValueAt(0, 0).toString() + ", " + tbFrekuensi.getValueAt(0, 1).toString() + ", " + tbFrekuensi.getValueAt(0, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(0, 7).toString()));
            dpd.setValue(tbFrekuensi.getValueAt(1, 0).toString() + ", " + tbFrekuensi.getValueAt(1, 1).toString() + ", " + tbFrekuensi.getValueAt(1, 7).toString(), Integer.parseInt(tbFrekuensi.getValueAt(1, 7).toString()));

            JFreeChart freeChart = ChartFactory.createPieChart("10 Besar Penyakit Di Rawat Jalan Periode " + Valid.SetTgl(Tgl1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(Tgl2.getSelectedItem() + ""), dpd, true, true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url 
            ChartFrame cf = new ChartFrame("Grafik 10 Besar Penyakit Di Rawat Jalan ", freeChart);
            cf.setSize(internalFrame1.getWidth() - 50, internalFrame1.getHeight() - 50);
            cf.setLocationRelativeTo(internalFrame1);
            cf.setAlwaysOnTop(false);
            cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
            cf.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan urutkan secara Descanding/Besar Ke Kecil dengan menklik pada kolom jumlah..!!!!");
        }
    } else {
        JOptionPane.showMessageDialog(null, "Untuk mencetak grafik pie 10 penyakit tersedikit, silakan koordinasi dg. Inst. Rekam Medik...!!!");
        Tgl1.requestFocus();
    }
}//GEN-LAST:event_ppGrafikTerkecilPieActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        //prosesCari();
    }//GEN-LAST:event_formWindowOpened

    private void ppDaftarTerbanyakPerPoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppDaftarTerbanyakPerPoliActionPerformed
        if (kdpoli.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silakan pilih dulu salah satu poliklinik/unitnya...!!!");
            BtnUnit.requestFocus();
        } else if (jlhPenyakit.getText().trim().equals("")) {
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
            param.put("nama_poli", TPoli.getText());
            param.put("periode", Tgl1.getSelectedItem() + " s.d " + Tgl2.getSelectedItem());
            param.put("jlhData", jlhPenyakit.getText());
            Valid.MyReport("rpDaftar10BesarDiagnosaJalan.jasper", "report", "::[ Daftar " + jlhPenyakit.getText() + " Besar Penyakit Terbanyak Per Unit/Poliklinik ]::",
                    " SELECT a.kd_penyakit AS KD_ICD_10, a.ciri_ciri AS Deskripsi_Diagnosa, IFNULL(b.qty, 0) LK, ifnull(c.qty, 0) PR, IFNULL(b.qty, 0) + IFNULL(c.qty, 0) Total "
                    + " FROM ((SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, count(*) AS qty FROM diagnosa_pasien "
                    + " LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                    + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                    + " LEFT JOIN poliklinik ON reg_periksa.kd_poli = poliklinik.kd_poli "
                    + " LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " WHERE diagnosa_pasien. STATUS = 'Ralan' AND poliklinik.kd_poli NOT LIKE '%LAA%' AND poliklinik.kd_poli NOT LIKE '%LAB%' AND poliklinik.kd_poli NOT LIKE '%RAD%' "
                    + " AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' AND reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                    + " AND poliklinik.kd_poli='" + kdpoli.getText() + "' GROUP BY diagnosa_pasien.kd_penyakit ORDER BY qty DESC) AS a "
                    + " LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, count(*) AS qty FROM diagnosa_pasien "
                    + " LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                    + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                    + " LEFT JOIN poliklinik ON reg_periksa.kd_poli = poliklinik.kd_poli "
                    + " LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " WHERE diagnosa_pasien. STATUS = 'Ralan' AND poliklinik.kd_poli NOT LIKE '%LAA%' AND poliklinik.kd_poli NOT LIKE '%LAB%' AND poliklinik.kd_poli NOT LIKE '%RAD%' "
                    + " AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' AND reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                    + " AND poliklinik.kd_poli='" + kdpoli.getText() + "' AND pasien.jk = 'L' GROUP BY diagnosa_pasien.kd_penyakit ORDER BY qty DESC) AS b ON b.kd_penyakit = a.kd_penyakit "
                    + " LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, count(*) AS qty FROM diagnosa_pasien "
                    + " LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                    + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                    + " LEFT JOIN poliklinik ON reg_periksa.kd_poli = poliklinik.kd_poli "
                    + " LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " WHERE diagnosa_pasien. STATUS = 'Ralan' AND poliklinik.kd_poli NOT LIKE '%LAA%' AND poliklinik.kd_poli NOT LIKE '%LAB%' AND poliklinik.kd_poli NOT LIKE '%RAD%' "
                    + " AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' AND reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                    + " AND poliklinik.kd_poli='" + kdpoli.getText() + "' AND pasien.jk = 'P' GROUP BY diagnosa_pasien.kd_penyakit ORDER BY qty DESC) AS c ON c.kd_penyakit = a.kd_penyakit) LIMIT " + jlhPenyakit.getText() + "", param);
            this.setCursor(Cursor.getDefaultCursor());
            Tgl1.requestFocus();
        }
    }//GEN-LAST:event_ppDaftarTerbanyakPerPoliActionPerformed

    private void BtnUnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUnitActionPerformed
        TPoli.setText("");
        kdpoli.setText("");

        akses.setform("DlgFrekuensiPenyakitRalan");
        poli.isCek();
        poli.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        poli.setLocationRelativeTo(internalFrame1);
        poli.setVisible(true);
    }//GEN-LAST:event_BtnUnitActionPerformed

    private void ppDaftarTerbanyakSemuaPoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppDaftarTerbanyakSemuaPoliActionPerformed
        if (akses.getkode().equals("Admin Utama") || akses.getpenyakit() == true) {
            if (cekSemuaPoli.isSelected() == true) {
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
                    param.put("periode", Tgl1.getSelectedItem() + " s.d " + Tgl2.getSelectedItem());
                    param.put("jlhData", jlhPenyakit.getText());
                    Valid.MyReport("rpDaftar10BesarDiagnosaSP.jasper", "report", "::[ Daftar " + jlhPenyakit.getText() + " Besar Penyakit Terbanyak Semua Unit/Poliklinik ]::",
                            " SELECT a.kd_penyakit AS KD_ICD_10, a.ciri_ciri AS Deskripsi_Diagnosa, IFNULL(b.qty, 0) LK, ifnull(c.qty, 0) PR, IFNULL(b.qty, 0) + IFNULL(c.qty, 0) Total "
                            + " FROM ((SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, count(*) AS qty FROM diagnosa_pasien "
                            + " LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                            + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                            + " LEFT JOIN poliklinik ON reg_periksa.kd_poli = poliklinik.kd_poli "
                            + " LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                            + " WHERE diagnosa_pasien. STATUS = 'Ralan' AND poliklinik.kd_poli NOT LIKE '%LAA%' AND poliklinik.kd_poli NOT LIKE '%LAB%' AND poliklinik.kd_poli NOT LIKE '%RAD%' "
                            + " AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' AND reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                            + " GROUP BY diagnosa_pasien.kd_penyakit ORDER BY qty DESC) AS a "
                            + " LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, count(*) AS qty FROM diagnosa_pasien "
                            + " LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                            + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                            + " LEFT JOIN poliklinik ON reg_periksa.kd_poli = poliklinik.kd_poli "
                            + " LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                            + " WHERE diagnosa_pasien. STATUS = 'Ralan' AND poliklinik.kd_poli NOT LIKE '%LAA%' AND poliklinik.kd_poli NOT LIKE '%LAB%' AND poliklinik.kd_poli NOT LIKE '%RAD%' "
                            + " AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' AND reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                            + " AND pasien.jk = 'L' GROUP BY diagnosa_pasien.kd_penyakit ORDER BY qty DESC) AS b ON b.kd_penyakit = a.kd_penyakit "
                            + " LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, count(*) AS qty FROM diagnosa_pasien "
                            + " LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                            + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                            + " LEFT JOIN poliklinik ON reg_periksa.kd_poli = poliklinik.kd_poli "
                            + " LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                            + " WHERE diagnosa_pasien. STATUS = 'Ralan' AND poliklinik.kd_poli NOT LIKE '%LAA%' AND poliklinik.kd_poli NOT LIKE '%LAB%' AND poliklinik.kd_poli NOT LIKE '%RAD%' "
                            + " AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' AND reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                            + " AND pasien.jk = 'P' GROUP BY diagnosa_pasien.kd_penyakit ORDER BY qty DESC) AS c ON c.kd_penyakit = a.kd_penyakit) LIMIT " + jlhPenyakit.getText() + "", param);
                    this.setCursor(Cursor.getDefaultCursor());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Silakan conteng dulu semua poli/unit...!!!");
                cekSemuaPoli.requestFocus();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Untuk mencetak lap. penyakit terbanyak semua poli/unit, silakan koordinasi dg. Inst. Rekam Medik...!!!");
            Tgl1.requestFocus();
        }
    }//GEN-LAST:event_ppDaftarTerbanyakSemuaPoliActionPerformed

    private void ppLapRL54RawatJalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppLapRL54RawatJalanActionPerformed
        if (akses.getkode().equals("Admin Utama") || akses.getpenyakit() == true) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("periode", Tgl1.getSelectedItem() + " s.d " + Tgl2.getSelectedItem());
            Valid.MyReport("rpDaftar10BesarDiagnosaRL54Jalan.jasper", "report", "::[ Laporan - SIRS Online RL 5.4 Daftar 10 Besar Penyakit Rawat Jalan ]::",
                    " SELECT a.kd_penyakit AS KD_ICD_10, a.ciri_ciri AS Deskripsi_Diagnosa, IFNULL(b.qty, 0) LK_Baru, ifnull(c.qty, 0) PR_Baru, IFNULL(b.qty, 0) + IFNULL(c.qty, 0) Total_Pasien_Baru, "
                    + " IFNULL(a.qty, 0) Pasien_Baru_dan_Lama FROM ((SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, count(*) AS qty "
                    + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                    + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat LEFT JOIN poliklinik ON reg_periksa.kd_poli = poliklinik.kd_poli "
                    + " LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " WHERE diagnosa_pasien. STATUS = 'Ralan' AND poliklinik.kd_poli NOT LIKE '%LAA%' AND poliklinik.kd_poli NOT LIKE '%LAB%' "
                    + " AND poliklinik.kd_poli NOT LIKE '%RAD%' AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                    + " AND reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                    + " GROUP BY diagnosa_pasien.kd_penyakit ORDER BY qty DESC) AS a "
                    + " LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, count(*) AS qty "
                    + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit "
                    + " LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat LEFT JOIN poliklinik ON reg_periksa.kd_poli = poliklinik.kd_poli "
                    + " LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis  "
                    + " WHERE diagnosa_pasien. STATUS = 'Ralan' AND poliklinik.kd_poli NOT LIKE '%LAA%' AND poliklinik.kd_poli NOT LIKE '%LAB%' "
                    + " AND poliklinik.kd_poli NOT LIKE '%RAD%' AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                    + " AND reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND reg_periksa.stts_daftar='Baru' AND pasien.jk = 'L' "
                    + " GROUP BY diagnosa_pasien.kd_penyakit ORDER BY qty DESC) AS b "
                    + " ON b.kd_penyakit = a.kd_penyakit LEFT JOIN (SELECT diagnosa_pasien.kd_penyakit, penyakit.ciri_ciri, pasien.jk, count(*) AS qty "
                    + " FROM diagnosa_pasien LEFT JOIN penyakit ON diagnosa_pasien.kd_penyakit = penyakit.kd_penyakit LEFT JOIN reg_periksa ON diagnosa_pasien.no_rawat = reg_periksa.no_rawat "
                    + " LEFT JOIN poliklinik ON reg_periksa.kd_poli = poliklinik.kd_poli LEFT JOIN pasien ON pasien.no_rkm_medis = reg_periksa.no_rkm_medis "
                    + " WHERE diagnosa_pasien. STATUS = 'Ralan' AND poliklinik.kd_poli NOT LIKE '%LAA%' AND poliklinik.kd_poli NOT LIKE '%LAB%' "
                    + " AND poliklinik.kd_poli NOT LIKE '%RAD%' AND diagnosa_pasien.prioritas = 1 AND diagnosa_pasien.kd_penyakit NOT LIKE 'Z%' "
                    + " AND reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND reg_periksa.stts_daftar='Baru' AND pasien.jk = 'P' "
                    + " GROUP BY diagnosa_pasien.kd_penyakit ORDER BY qty DESC) AS c ON c.kd_penyakit = a.kd_penyakit) LIMIT 10", param);
            this.setCursor(Cursor.getDefaultCursor());

        } else {
            JOptionPane.showMessageDialog(null, "Untuk mencetak laporan RL 5.4 Daftar 10 Besar Penyakit Rawat Jalan, silakan koordinasi dg. Inst. Rekam Medik...!!!");
            Tgl1.requestFocus();
        }
    }//GEN-LAST:event_ppLapRL54RawatJalanActionPerformed

    private void cekSemuaPoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cekSemuaPoliActionPerformed
        if (cekSemuaPoli.isSelected() == true) {
            kdpoli.setText("");
            TPoli.setText("");
        }
    }//GEN-LAST:event_cekSemuaPoliActionPerformed

    private void ppLapRL53RawatJalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppLapRL53RawatJalanActionPerformed
        if (akses.getkode().equals("Admin Utama") || akses.getpenyakit() == true) {
            
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            if (tbFrekuensi.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            } else if (tbFrekuensi.getRowCount() != 0) {
                Sequel.queryu("delete from temporary");
                int row = tbFrekuensi.getRowCount();
                for (int r = 0; r < row; r++) {
                    Sequel.menyimpan("temporary", "'0','"
                            + tbFrekuensi.getValueAt(r, 0).toString().replaceAll("'", "`") + "','"
                            + tbFrekuensi.getValueAt(r, 1).toString().replaceAll("'", "`") + "','"
                            + tbFrekuensi.getValueAt(r, 2).toString().replaceAll("'", "`") + "','"
                            + tbFrekuensi.getValueAt(r, 3).toString() + "','"
                            + tbFrekuensi.getValueAt(r, 4).toString() + "','"
                            + tbFrekuensi.getValueAt(r, 5).toString() + "','"
                            + tbFrekuensi.getValueAt(r, 6).toString() + "','"
                            + tbFrekuensi.getValueAt(r, 7).toString() + "','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Rekap Frekuensi Penyakit");
                }
                Valid.panggilUrl("billing/LaporanPenyakitRalan.php?tanggal1=" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "&tanggal2=" + Valid.SetTgl(Tgl2.getSelectedItem() + ""));
            }
            this.setCursor(Cursor.getDefaultCursor());
            
        } else {
            JOptionPane.showMessageDialog(null, "Untuk mencetak laporan RL 5.3 Penyakit Rawat Jalan, silakan koordinasi dg. Inst. Rekam Medik...!!!");
            Tgl1.requestFocus();
        }
    }//GEN-LAST:event_ppLapRL53RawatJalanActionPerformed

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if (TabRawat.getSelectedIndex() == 0) {
            tampil1();
        } else if (TabRawat.getSelectedIndex() == 1) {
            tampil2();
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void btnPenjabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPenjabActionPerformed
        akses.setform("DlgFrekuensiPenyakitRalan");
        pasien.penjab.onCari();
        pasien.penjab.isCek();
        pasien.penjab.setSize(882, 593);
        pasien.penjab.setLocationRelativeTo(internalFrame1);
        pasien.penjab.setVisible(true);
        pasien.penjab.onCari();
    }//GEN-LAST:event_btnPenjabActionPerformed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptText();
    }//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnBatalActionPerformed(null);
        }
    }//GEN-LAST:event_BtnBatalKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgFrekuensiPenyakitRalan dialog = new DlgFrekuensiPenyakitRalan(new javax.swing.JFrame(), true);
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
    private widget.Button BtnKeluar;
    private widget.Button BtnUnit;
    private widget.TextBox Kd2;
    private widget.TextBox TPoli;
    private javax.swing.JTabbedPane TabRawat;
    private widget.Tanggal Tgl1;
    private widget.Tanggal Tgl2;
    private widget.Button btnPenjab;
    private widget.Button btnPenyakit;
    private widget.CekBox cekSemuaPoli;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel23;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.TextBox jlhPenyakit;
    private widget.TextBox kdpenyakit;
    private widget.TextBox kdpoli;
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
    private javax.swing.JMenuItem ppDaftarTerbanyakPerPoli;
    private javax.swing.JMenuItem ppDaftarTerbanyakSemuaPoli;
    private javax.swing.JMenuItem ppGrafikTerbanyakBatang;
    private javax.swing.JMenuItem ppGrafikTerbanyakPie;
    private javax.swing.JMenuItem ppGrafikTerkecilBatang;
    private javax.swing.JMenuItem ppGrafikTerkecilPie;
    private javax.swing.JMenuItem ppLapRL53RawatJalan;
    private javax.swing.JMenuItem ppLapRL54RawatJalan;
    private widget.ScrollPane scrollPane1;
    private widget.ScrollPane scrollPane2;
    private widget.Table tbDiagnosa;
    private widget.Table tbFrekuensi;
    private widget.TextBox userBerizin;
    // End of variables declaration//GEN-END:variables

    public void tampil1() {
       Valid.tabelKosong(tabMode);      
       try{   
           if (kdpoli.getText().equals("")) {
               ps = koneksi.prepareStatement("select penyakit.kd_penyakit,SUBSTRING(penyakit.nm_penyakit,1,80) as penyakit from penyakit inner join diagnosa_pasien inner join reg_periksa "
                       + "on penyakit.kd_penyakit=diagnosa_pasien.kd_penyakit and reg_periksa.no_rawat=diagnosa_pasien.no_rawat "
                       + "where diagnosa_pasien.status='Ralan' and diagnosa_pasien.prioritas='1' and penyakit.kd_penyakit like ? and reg_periksa.tgl_registrasi between ? and ? "
                       + "group by penyakit.kd_penyakit order by penyakit.kd_penyakit");

           } else if (!kdpoli.getText().equals("")) {
               ps = koneksi.prepareStatement("select penyakit.kd_penyakit,SUBSTRING(penyakit.nm_penyakit,1,80) as penyakit from penyakit inner join diagnosa_pasien inner join reg_periksa "
                       + "on penyakit.kd_penyakit=diagnosa_pasien.kd_penyakit and reg_periksa.no_rawat=diagnosa_pasien.no_rawat "
                       + "where diagnosa_pasien.status='Ralan' and diagnosa_pasien.prioritas='1' and penyakit.kd_penyakit like ? and reg_periksa.tgl_registrasi between ? and ? "
                       + "and reg_periksa.kd_poli=? group by penyakit.kd_penyakit order by penyakit.kd_penyakit");
           }

           if (kdpoli.getText().equals("")) {
               ps.setString(1, "%" + kdpenyakit.getText() + "%");
               ps.setString(2, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
               ps.setString(3, Valid.SetTgl(Tgl2.getSelectedItem() + ""));

           } else if (!kdpoli.getText().equals("")) {
               ps.setString(1, "%" + kdpenyakit.getText() + "%");
               ps.setString(2, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
               ps.setString(3, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
               ps.setString(4, kdpoli.getText());
           }
           
            rs=ps.executeQuery();            
            while(rs.next()){
               i=0;
               ps2.setString(1,Valid.SetTgl(Tgl1.getSelectedItem()+""));
               ps2.setString(2,Valid.SetTgl(Tgl2.getSelectedItem()+""));
               ps2.setString(3,rs.getString("kd_penyakit"));
               rs2=ps2.executeQuery();
               Sequel.queryu("delete from temporary_surveilens_penyakit");
               while(rs2.next()){
                   ps7.setString(1,rs2.getString(1));
                   rs7=ps7.executeQuery();
                   while(rs7.next()){
                       Sequel.menyimpan("temporary_surveilens_penyakit","?,?",2,new String[]{
                           rs.getString("kd_penyakit"),rs7.getString("kd_penyakit")
                       });
                   }
               }
               diagnosa="";
               rs2.last();
               if(rs2.getRow()>0){
                   ps8.setString(1,rs.getString("kd_penyakit"));
                   rs8=ps8.executeQuery();
                   while(rs8.next()){
                       if(diagnosa.equals("")){
                           diagnosa=rs8.getString(1);
                       }else{
                           diagnosa=diagnosa+", "+rs8.getString(1);
                       }
                   }
                   i=rs2.getRow();
               }
               
               a=0;
               ps3.setString(1,Valid.SetTgl(Tgl1.getSelectedItem()+""));
               ps3.setString(2,Valid.SetTgl(Tgl2.getSelectedItem()+""));
               ps3.setString(3,rs.getString("kd_penyakit"));
               rs3=ps3.executeQuery();
               rs3.last();
               if(rs3.getRow()>0) a=rs3.getRow();

               b=0;
               ps4.setString(1,Valid.SetTgl(Tgl1.getSelectedItem()+""));
               ps4.setString(2,Valid.SetTgl(Tgl2.getSelectedItem()+""));
               ps4.setString(3,rs.getString("kd_penyakit"));
               rs4=ps4.executeQuery();
               rs4.last();       
               if(rs4.getRow()>0) b=rs4.getRow();
               
               c=0;
               ps5.setString(1,Valid.SetTgl(Tgl1.getSelectedItem()+""));
               ps5.setString(2,Valid.SetTgl(Tgl2.getSelectedItem()+""));
               ps5.setString(3,rs.getString("kd_penyakit"));
               rs5=ps5.executeQuery();
               rs5.last();
               if(rs5.getRow()>0)  c=rs5.getRow()-a;
                          
               d=0;
               ps6.setString(1,Valid.SetTgl(Tgl1.getSelectedItem()+""));
               ps6.setString(2,Valid.SetTgl(Tgl2.getSelectedItem()+""));
               ps6.setString(3,rs.getString("kd_penyakit"));
               rs6=ps6.executeQuery();
               rs6.last();
               if(rs6.getRow()>0) d=rs6.getRow()-b;
                 
               tabMode.addRow(new Object[]{rs.getString("kd_penyakit"),rs.getString("penyakit"),diagnosa,c,d,a,b,i});                  
            } 
            //rs.close();
            label9.setText("      Record : "+tabMode.getRowCount());                        
        }catch(SQLException e){
            System.out.println("Catatan  "+e);
        }        
    }
    
    public void isCek(){
        ppLapRL53RawatJalan.setEnabled(akses.getpenyakit());
    }
    
    public void UserValid() {
        userBerizin.setText(Sequel.cariIsi("SELECT nip FROM hak_akses_unit WHERE nip='" + akses.getkode() + "' "));

        if (akses.getadmin() == true || akses.getpenyakit() == true) {
            BtnUnit.setEnabled(true);
            BtnCari.setEnabled(true);
            BtnAll.setEnabled(true);
            cekSemuaPoli.setSelected(true);
            cekSemuaPoli.setEnabled(true);
            kdpoli.setText("");
            TPoli.setText("");
            tampil1();

        } else if (!userBerizin.getText().equals("") || akses.getadmin() == false) {
            BtnUnit.setEnabled(false);
            BtnCari.setEnabled(true);
            BtnAll.setEnabled(true);
            cekSemuaPoli.setSelected(false);
            cekSemuaPoli.setEnabled(false);
            kdpoli.setText(Sequel.cariIsi("SELECT kode_unit FROM hak_akses_unit WHERE nip='" + akses.getkode() + "' "));
            TPoli.setText(Sequel.cariIsi("SELECT nm_poli FROM poliklinik WHERE kd_poli='" + kdpoli.getText() + "' "));
            tampil1();
        }
    }
    
    private void tampil2() {
        Valid.tabelKosong(tabMode1);
        try {
            if (kdpoli.getText().equals("")) {
                ps9 = koneksi.prepareStatement(
                        "SELECT DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y') tgl_reg, p.no_rkm_medis no_rm, p.nm_pasien, pl.nm_poli, pj.png_jawab cr_byr, "
                        + "py.kd_penyakit icd_10, py.ciri_ciri desk_diagnosa, if(dp.prioritas='1','Primer','Sekunder') status_diag FROM diagnosa_pasien dp "
                        + "INNER JOIN penyakit py on py.kd_penyakit=dp.kd_penyakit INNER JOIN reg_periksa rp on rp.no_rawat=dp.no_rawat "
                        + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj INNER JOIN poliklinik pl on pl.kd_poli=rp.kd_poli WHERE "
                        + "rp.tgl_registrasi BETWEEN ? and ? and rp.status_lanjut='Ralan' and "
                        + "dp.kd_penyakit like ? and pj.kd_pj like ? ORDER BY p.no_rkm_medis, rp.tgl_registrasi, dp.prioritas");
            } 
            
           if (!kdpoli.getText().equals("")) {
                ps9 = koneksi.prepareStatement(
                        "SELECT DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y') tgl_reg, p.no_rkm_medis no_rm, p.nm_pasien, pl.nm_poli, pj.png_jawab cr_byr, "
                        + "py.kd_penyakit icd_10, py.ciri_ciri desk_diagnosa, if(dp.prioritas='1','Primer','Sekunder') status_diag FROM diagnosa_pasien dp "
                        + "INNER JOIN penyakit py on py.kd_penyakit=dp.kd_penyakit INNER JOIN reg_periksa rp on rp.no_rawat=dp.no_rawat "
                        + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj INNER JOIN poliklinik pl on pl.kd_poli=rp.kd_poli WHERE "
                        + "rp.tgl_registrasi BETWEEN ? and ? and rp.status_lanjut='Ralan' and rp.kd_poli like ? and "
                        + "dp.kd_penyakit like ? and pj.kd_pj like ? ORDER BY p.no_rkm_medis, rp.tgl_registrasi, dp.prioritas");
            }

            try {
                ps9.setString(1, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps9.setString(2, Valid.SetTgl(Tgl2.getSelectedItem() + ""));

                if (kdpoli.getText().equals("")) {
                    ps9.setString(3, "%" + kdpenyakit.getText() + "%");
                    ps9.setString(4, "%" + kdpnj + "%");
                }
                
                if (!kdpoli.getText().equals("")) {
                    ps9.setString(3, "%" + kdpoli.getText() + "%");
                    ps9.setString(4, "%" + kdpenyakit.getText() + "%");
                    ps9.setString(5, "%" + kdpnj + "%");
                }

                rs9 = ps9.executeQuery();
                while (rs9.next()) {
                    tabMode1.addRow(new Object[]{
                        rs9.getString("tgl_reg"),
                        rs9.getString("no_rm"),
                        rs9.getString("nm_pasien"),
                        rs9.getString("nm_poli"),
                        rs9.getString("cr_byr"),
                        rs9.getString("icd_10"),
                        rs9.getString("desk_diagnosa"),
                        rs9.getString("status_diag")
                    });
                }

            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs9 != null) {
                    rs9.close();
                }
                if (ps9 != null) {
                    ps9.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Catatan  " + e);
        }
        label9.setText("      Record : " + tabMode1.getRowCount());
    }
    
    public void emptText() {
        userBerizin.setText("");
        kdpenyakit.setText("");
        nmpenyakit.setText("");
        kdpnj = "";
        nmpnj.setText("");
        jlhPenyakit.setText("10");
        UserValid();
    }
    
}
