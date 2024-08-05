package keuangan;
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
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import simrskhanza.DlgCariDokter;
import simrskhanza.DlgReg;
import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import simrskhanza.DlgPenanggungJawab;

public class DlgFeeVisitDokter extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1, tabMode2, tabMode3,
            tabMode4, tabMode5, tabMode6, tabMode7, tabMode8;
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Jurnal jur = new Jurnal();
    private Connection koneksi = koneksiDB.condb();
    private Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    private DlgPenanggungJawab penjab = new DlgPenanggungJawab(null, false);
    private int i = 0, jmlvisit = 0, jmlbyphone = 0, ttljmlvisit = 0, ttljmlbyphone = 0, x = 0;
    private double visit = 0, ttlvisit = 0, byphone = 0, ttlbyphone = 0, bruto = 0, ttlbruto = 0,
            jasa = 0, ttljasa = 0, uangrs = 0, ttluangrs = 0, tarifvisit = 0, tarifbyphone;
    private PreparedStatement ps, ps1, ps2, pskamar, psvisit, psvisit1, psbyphone, psrekap, psrekap1, psrekap2, psrekap3, psrekap4, psrekap5;
    private ResultSet rs, rs1, rs2, rskamar, rsvisit, rsvisit1, rsbyphone, rsrekap, rsrekap1, rsrekap2, rsrekap3, rsrekap4, rsrekap5;
    private String sjmlvisit = "", sjmlbyphone = "", svisit = "", sbyphone = "", sbruto = "",  
            sjasa = "", suangrs = "", starifvisit = "", starifbyphone = "", ruangDipilih = "", dialog_simpan = "";

    /** Creates new form DlgProgramStudi
     * @param parent
     * @param modal */
    public DlgFeeVisitDokter(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        Object[] row = {"Tgl. Visite", "Pemeriksaan Visite", "Nama Dokter Yg. Menangani", "No. RM", "Nama Pasien", "Ruang Inap/Perawatan", "Cara Bayar"};

        tabMode = new DefaultTableModel(null, row) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbVisit.setModel(tabMode);

        tbVisit.setPreferredScrollableViewportSize(new Dimension(800,800));
        tbVisit.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 7; i++) {
            TableColumn column = tbVisit.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(75);
            } else if (i == 1) {
                column.setPreferredWidth(315);
            } else if (i == 2) {
                column.setPreferredWidth(290);
            } else if (i == 3) {
                column.setPreferredWidth(70);
            } else if (i == 4) {
                column.setPreferredWidth(250);
            } else if (i == 5) {
                column.setPreferredWidth(300);
            } else if (i == 6) {
                column.setPreferredWidth(200);
            }
        }
        tbVisit.setDefaultRenderer(Object.class, new WarnaTable());  
        
        tabMode1 = new DefaultTableModel(null, new Object[]{"Tgl. Visite", "Pemeriksaan Visite", "Nama Dokter Umum/Yg. Mewakili", 
            "No. RM", "Nama Pasien", "Ruang Inap/Perawatan","Cara Bayar"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbVisit1.setModel(tabMode1);

        tbVisit1.setPreferredScrollableViewportSize(new Dimension(800,800));
        tbVisit1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 7; i++) {
            TableColumn column = tbVisit1.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(75);
            } else if (i == 1) {
                column.setPreferredWidth(315);
            } else if (i == 2) {
                column.setPreferredWidth(290);
            } else if (i == 3) {
                column.setPreferredWidth(70);
            } else if (i == 4) {
                column.setPreferredWidth(250);
            } else if (i == 5) {
                column.setPreferredWidth(300);
            } else if (i == 6) {
                column.setPreferredWidth(200);
            }
        }
        tbVisit1.setDefaultRenderer(Object.class, new WarnaTable()); 
        
        tabMode2 = new DefaultTableModel(null, new Object[]{"No.", "Nama Dokter Yg. Menangani", "Kelas 1", "Kelas 2",
            "Kelas 3", "Kelas VIP", "Kelas Super VIP", "Rawat Khusus", "Intensif", "High Care", "Isolasi", "Total Visite"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbRekap0.setModel(tabMode2);
        tbRekap0.setPreferredScrollableViewportSize(new Dimension(800,800));
        tbRekap0.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 12; i++) {
            TableColumn column = tbRekap0.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(290);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setPreferredWidth(70);
            } else if (i == 4) {
                column.setPreferredWidth(70);
            } else if (i == 5) {
                column.setPreferredWidth(75);
            } else if (i == 6) {
                column.setPreferredWidth(110);
            } else if (i == 7) {
                column.setPreferredWidth(95);
            } else if (i == 8) {
                column.setPreferredWidth(85);
            } else if (i == 9) {
                column.setPreferredWidth(85);
            } else if (i == 10) {
                column.setPreferredWidth(85);
            } else if (i == 11) {
                column.setPreferredWidth(75);
            }
        }
        tbRekap0.setDefaultRenderer(Object.class, new WarnaTable());  
        
        tabMode4 = new DefaultTableModel(null, new Object[]{"No.", "Nama Dokter Yg. Menangani", "Kelas 1", "Kelas 2",
            "Kelas 3", "Kelas VIP", "Kelas Super VIP", "Rawat Khusus", "Intensif", "High Care", "Isolasi", "Total Visite"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbRekap2.setModel(tabMode4);
        tbRekap2.setPreferredScrollableViewportSize(new Dimension(800,800));
        tbRekap2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 12; i++) {
            TableColumn column = tbRekap2.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(290);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setPreferredWidth(70);
            } else if (i == 4) {
                column.setPreferredWidth(70);
            } else if (i == 5) {
                column.setPreferredWidth(75);
            } else if (i == 6) {
                column.setPreferredWidth(110);
            } else if (i == 7) {
                column.setPreferredWidth(95);
            } else if (i == 8) {
                column.setPreferredWidth(85);
            } else if (i == 9) {
                column.setPreferredWidth(85);
            } else if (i == 10) {
                column.setPreferredWidth(85);
            } else if (i == 11) {
                column.setPreferredWidth(75);
            }
        }
        tbRekap2.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode5 = new DefaultTableModel(null, new Object[]{"No.", "Nama Dokter Yg. Menangani", "Kelas 1", "Kelas 2",
            "Kelas 3", "Kelas VIP", "Kelas Super VIP", "Rawat Khusus", "Intensif", "High Care", "Isolasi", "Total Visite"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbRekap3.setModel(tabMode5);
        tbRekap3.setPreferredScrollableViewportSize(new Dimension(800,800));
        tbRekap3.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 12; i++) {
            TableColumn column = tbRekap3.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(290);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setPreferredWidth(70);
            } else if (i == 4) {
                column.setPreferredWidth(70);
            } else if (i == 5) {
                column.setPreferredWidth(75);
            } else if (i == 6) {
                column.setPreferredWidth(110);
            } else if (i == 7) {
                column.setPreferredWidth(95);
            } else if (i == 8) {
                column.setPreferredWidth(85);
            } else if (i == 9) {
                column.setPreferredWidth(85);
            } else if (i == 10) {
                column.setPreferredWidth(85);
            } else if (i == 11) {
                column.setPreferredWidth(75);
            }
        }
        tbRekap3.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode3 = new DefaultTableModel(null, new Object[]{"No.", "Nama Dokter Umum/Yg. Mewakili", "Kelas 1", "Kelas 2",
            "Kelas 3", "Kelas VIP", "Kelas Super VIP", "Rawat Khusus", "Intensif", "High Care", "Isolasi", "Total Visite"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbRekap1.setModel(tabMode3);
        tbRekap1.setPreferredScrollableViewportSize(new Dimension(800,800));
        tbRekap1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 12; i++) {
            TableColumn column = tbRekap1.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(290);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setPreferredWidth(70);
            } else if (i == 4) {
                column.setPreferredWidth(70);
            } else if (i == 5) {
                column.setPreferredWidth(75);
            } else if (i == 6) {
                column.setPreferredWidth(110);
            } else if (i == 7) {
                column.setPreferredWidth(95);
            } else if (i == 8) {
                column.setPreferredWidth(85);
            } else if (i == 9) {
                column.setPreferredWidth(85);
            } else if (i == 10) {
                column.setPreferredWidth(85);
            } else if (i == 11) {
                column.setPreferredWidth(75);
            }
        }
        tbRekap1.setDefaultRenderer(Object.class, new WarnaTable());  
        
        tabMode6 = new DefaultTableModel(null, new Object[]{"No.", "Nama Dokter Umum/Yg. Mewakili", "Kelas 1", "Kelas 2",
            "Kelas 3", "Kelas VIP", "Kelas Super VIP", "Rawat Khusus", "Intensif", "High Care", "Isolasi", "Total Visite"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbRekap4.setModel(tabMode6);
        tbRekap4.setPreferredScrollableViewportSize(new Dimension(800,800));
        tbRekap4.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 12; i++) {
            TableColumn column = tbRekap4.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(290);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setPreferredWidth(70);
            } else if (i == 4) {
                column.setPreferredWidth(70);
            } else if (i == 5) {
                column.setPreferredWidth(75);
            } else if (i == 6) {
                column.setPreferredWidth(110);
            } else if (i == 7) {
                column.setPreferredWidth(95);
            } else if (i == 8) {
                column.setPreferredWidth(85);
            } else if (i == 9) {
                column.setPreferredWidth(85);
            } else if (i == 10) {
                column.setPreferredWidth(85);
            } else if (i == 11) {
                column.setPreferredWidth(75);
            }
        }
        tbRekap4.setDefaultRenderer(Object.class, new WarnaTable());  
        
        tabMode7 = new DefaultTableModel(null, new Object[]{"No.", "Nama Dokter Umum/Yg. Mewakili", "Kelas 1", "Kelas 2",
            "Kelas 3", "Kelas VIP", "Kelas Super VIP", "Rawat Khusus", "Intensif", "High Care", "Isolasi", "Total Visite"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbRekap5.setModel(tabMode7);
        tbRekap5.setPreferredScrollableViewportSize(new Dimension(800,800));
        tbRekap5.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 12; i++) {
            TableColumn column = tbRekap5.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(290);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setPreferredWidth(70);
            } else if (i == 4) {
                column.setPreferredWidth(70);
            } else if (i == 5) {
                column.setPreferredWidth(75);
            } else if (i == 6) {
                column.setPreferredWidth(110);
            } else if (i == 7) {
                column.setPreferredWidth(95);
            } else if (i == 8) {
                column.setPreferredWidth(85);
            } else if (i == 9) {
                column.setPreferredWidth(85);
            } else if (i == 10) {
                column.setPreferredWidth(85);
            } else if (i == 11) {
                column.setPreferredWidth(75);
            }
        }
        tbRekap5.setDefaultRenderer(Object.class, new WarnaTable()); 
        
        tabMode8 = new DefaultTableModel(null, new Object[]{
            "No.", "No. SEP", "Tgl. Masuk", "Tgl. Pulang", "No. Kartu", "Nama Pasien", "No. RM", "Ruang Perawatan", "DPJP", "", "Dokter Visite", "Jmlh. Visite"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbHitung.setModel(tabMode8);
        tbHitung.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbHitung.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 12; i++) {
            TableColumn column = tbHitung.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(130);
            } else if (i == 2) {
                column.setPreferredWidth(75);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else if (i == 4) {
                column.setPreferredWidth(100);
            } else if (i == 5) {
                column.setPreferredWidth(250);
            } else if (i == 6) {
                column.setPreferredWidth(58);                
            } else if (i == 7) {
                column.setPreferredWidth(125);
            } else if (i == 8) {
                column.setPreferredWidth(250);
            } else if (i == 9) {
                column.setPreferredWidth(10);
            } else if (i == 10) {
                column.setPreferredWidth(250);
            } else if (i == 11) {
                column.setPreferredWidth(65);
            }
        }
        tbHitung.setDefaultRenderer(Object.class, new WarnaTable());
        
        kddokter.setDocument(new batasInput((byte)10).getKata(kddokter));
                
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(dokter.getTable().getSelectedRow()!= -1){
                    kddokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                    nmdokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                    prosesCari();
                }   
                kddokter.requestFocus();
            }
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {dokter.emptTeks();}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
        
        penjab.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgFeeVisitDokter")) {
                    if (penjab.getTable().getSelectedRow() != -1) {
                        kdpenjab.setText(penjab.getTable().getValueAt(penjab.getTable().getSelectedRow(), 1).toString());
                        nmpenjab.setText(penjab.getTable().getValueAt(penjab.getTable().getSelectedRow(), 2).toString());
                    }
                    kdpenjab.requestFocus();
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

        penjab.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (akses.getform().equals("DlgFeeVisitDokter")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        penjab.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        
//        try {            
//            pskamar=koneksi.prepareStatement(
//                    "select kamar_inap.no_rawat,pasien.nm_pasien,penjab.png_jawab,kamar_inap.kd_kamar,bangsal.kd_bangsal,bangsal.nm_bangsal,kamar_inap.tgl_masuk,kamar_inap.tgl_keluar "+
//                    "from kamar_inap inner join kamar inner join bangsal inner join reg_periksa inner join pasien inner join penjab on kamar_inap.no_rawat=reg_periksa.no_rawat and "+
//                    "reg_periksa.no_rkm_medis=pasien.no_rkm_medis and reg_periksa.kd_pj=penjab.kd_pj and kamar_inap.kd_kamar=kamar.kd_kamar and kamar.kd_bangsal=bangsal.kd_bangsal "+
//                    "where kamar_inap.tgl_keluar between ? and ? and kamar_inap.tgl_keluar<>'0000-00-00' group by kamar_inap.kd_kamar, kamar_inap.no_rawat");
//            psvisit=koneksi.prepareStatement(
//                    "select count(rawat_inap_dr.kd_jenis_prw) as jml,"+
//                    "sum(rawat_inap_dr.bhp)as bhp,"+
//                    "sum(rawat_inap_dr.material)as material,"+
//                    "rawat_inap_dr.biaya_rawat as tarif,"+
//                    "sum(rawat_inap_dr.tarif_tindakandr)as bayardokter,"+
//                    "sum(rawat_inap_dr.biaya_rawat) as totalbiaya  "+
//                    "from rawat_inap_dr inner join jns_perawatan_inap "+
//                    "on jns_perawatan_inap.kd_jenis_prw=rawat_inap_dr.kd_jenis_prw where "+
//                    "rawat_inap_dr.tarif_tindakandr>0 and rawat_inap_dr.kd_dokter=? "+
//                    "and rawat_inap_dr.no_rawat=? and jns_perawatan_inap.nm_perawatan like '%visit%' ");
//            psbyphone=koneksi.prepareStatement(
//                    "select count(rawat_inap_dr.kd_jenis_prw) as jml,"+
//                    "sum(rawat_inap_dr.bhp)as bhp,"+
//                    "sum(rawat_inap_dr.material)as material,"+
//                    "rawat_inap_dr.biaya_rawat as tarif,"+
//                    "sum(rawat_inap_dr.tarif_tindakandr)as bayardokter,"+
//                    "sum(rawat_inap_dr.biaya_rawat) as totalbiaya  "+
//                    "from rawat_inap_dr inner join jns_perawatan_inap "+
//                    "on jns_perawatan_inap.kd_jenis_prw=rawat_inap_dr.kd_jenis_prw where "+
//                    "rawat_inap_dr.tarif_tindakandr>0 and rawat_inap_dr.kd_dokter=? "+
//                    "and rawat_inap_dr.no_rawat=? and jns_perawatan_inap.nm_perawatan like '%konsul%' "+
//                    "and jns_perawatan_inap.nm_perawatan like '%phone%' ");
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
     
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        internalFrame1 = new widget.InternalFrame();
        panelisi4 = new widget.panelisi();
        label11 = new widget.Label();
        Tgl1 = new widget.Tanggal();
        label18 = new widget.Label();
        Tgl2 = new widget.Tanggal();
        label17 = new widget.Label();
        kddokter = new widget.TextBox();
        nmdokter = new widget.TextBox();
        BtnSeek2 = new widget.Button();
        kdpenjab = new widget.TextBox();
        nmpenjab = new widget.TextBox();
        btnBayar = new widget.Button();
        jLabel17 = new widget.Label();
        jLabel8 = new widget.Label();
        cmbRuangan = new widget.ComboBox();
        panelisi1 = new widget.panelisi();
        panelisi2 = new widget.panelisi();
        BtnBatal = new widget.Button();
        jLabel9 = new widget.Label();
        cmbPrin = new widget.ComboBox();
        BtnPrint = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        panelisi3 = new widget.panelisi();
        jLabel10 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        TabRawat = new javax.swing.JTabbedPane();
        internalFrame2 = new widget.InternalFrame();
        scrollPane1 = new widget.ScrollPane();
        tbVisit = new widget.Table();
        internalFrame3 = new widget.InternalFrame();
        scrollPane2 = new widget.ScrollPane();
        tbVisit1 = new widget.Table();
        internalFrame4 = new widget.InternalFrame();
        scrollPane3 = new widget.ScrollPane();
        tbRekap0 = new widget.Table();
        scrollPane5 = new widget.ScrollPane();
        tbRekap2 = new widget.Table();
        scrollPane6 = new widget.ScrollPane();
        tbRekap3 = new widget.Table();
        internalFrame5 = new widget.InternalFrame();
        scrollPane4 = new widget.ScrollPane();
        tbRekap1 = new widget.Table();
        scrollPane7 = new widget.ScrollPane();
        tbRekap4 = new widget.Table();
        scrollPane8 = new widget.ScrollPane();
        tbRekap5 = new widget.Table();
        internalFrame6 = new widget.InternalFrame();
        scrollPane9 = new widget.ScrollPane();
        tbHitung = new widget.Table();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Rekap Rincian Visite Dokter Rawat Inap ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelisi4.setName("panelisi4"); // NOI18N
        panelisi4.setPreferredSize(new java.awt.Dimension(100, 76));
        panelisi4.setLayout(null);

        label11.setForeground(new java.awt.Color(0, 0, 0));
        label11.setText("Tanggal : ");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(85, 23));
        panelisi4.add(label11);
        label11.setBounds(0, 11, 95, 23);

        Tgl1.setEditable(false);
        Tgl1.setDisplayFormat("dd-MM-yyyy");
        Tgl1.setName("Tgl1"); // NOI18N
        Tgl1.setPreferredSize(new java.awt.Dimension(100, 23));
        Tgl1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tgl1KeyPressed(evt);
            }
        });
        panelisi4.add(Tgl1);
        Tgl1.setBounds(96, 11, 100, 23);

        label18.setForeground(new java.awt.Color(0, 0, 0));
        label18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label18.setText("s.d.");
        label18.setName("label18"); // NOI18N
        label18.setPreferredSize(new java.awt.Dimension(30, 23));
        panelisi4.add(label18);
        label18.setBounds(201, 11, 30, 23);

        Tgl2.setEditable(false);
        Tgl2.setDisplayFormat("dd-MM-yyyy");
        Tgl2.setName("Tgl2"); // NOI18N
        Tgl2.setPreferredSize(new java.awt.Dimension(100, 23));
        Tgl2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tgl2KeyPressed(evt);
            }
        });
        panelisi4.add(Tgl2);
        Tgl2.setBounds(236, 11, 100, 23);

        label17.setForeground(new java.awt.Color(0, 0, 0));
        label17.setText("Dokter :");
        label17.setName("label17"); // NOI18N
        label17.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi4.add(label17);
        label17.setBounds(341, 11, 70, 23);

        kddokter.setEditable(false);
        kddokter.setForeground(new java.awt.Color(0, 0, 0));
        kddokter.setName("kddokter"); // NOI18N
        kddokter.setPreferredSize(new java.awt.Dimension(70, 23));
        kddokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kddokterKeyPressed(evt);
            }
        });
        panelisi4.add(kddokter);
        kddokter.setBounds(416, 11, 70, 23);

        nmdokter.setEditable(false);
        nmdokter.setForeground(new java.awt.Color(0, 0, 0));
        nmdokter.setName("nmdokter"); // NOI18N
        nmdokter.setPreferredSize(new java.awt.Dimension(203, 23));
        panelisi4.add(nmdokter);
        nmdokter.setBounds(491, 11, 250, 23);

        BtnSeek2.setForeground(new java.awt.Color(0, 0, 0));
        BtnSeek2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
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
        BtnSeek2.setBounds(746, 11, 28, 23);

        kdpenjab.setEditable(false);
        kdpenjab.setForeground(new java.awt.Color(0, 0, 0));
        kdpenjab.setHighlighter(null);
        kdpenjab.setName("kdpenjab"); // NOI18N
        kdpenjab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdpenjabKeyPressed(evt);
            }
        });
        panelisi4.add(kdpenjab);
        kdpenjab.setBounds(416, 40, 60, 23);

        nmpenjab.setEditable(false);
        nmpenjab.setForeground(new java.awt.Color(0, 0, 0));
        nmpenjab.setName("nmpenjab"); // NOI18N
        panelisi4.add(nmpenjab);
        nmpenjab.setBounds(480, 40, 260, 23);

        btnBayar.setForeground(new java.awt.Color(0, 0, 0));
        btnBayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnBayar.setMnemonic('7');
        btnBayar.setToolTipText("ALt+7");
        btnBayar.setName("btnBayar"); // NOI18N
        btnBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBayarActionPerformed(evt);
            }
        });
        panelisi4.add(btnBayar);
        btnBayar.setBounds(746, 40, 28, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Jenis Bayar :");
        jLabel17.setName("jLabel17"); // NOI18N
        panelisi4.add(jLabel17);
        jLabel17.setBounds(340, 40, 70, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Ruangan Inap : ");
        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.setPreferredSize(new java.awt.Dimension(90, 30));
        panelisi4.add(jLabel8);
        jLabel8.setBounds(0, 40, 95, 23);

        cmbRuangan.setForeground(new java.awt.Color(0, 0, 0));
        cmbRuangan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SEMUA RUANG", "INTERNIST", "PERINATOLOGI & BAYI SEHAT", "AR-RAUDAH", "AS-SAMI/1", "AS-SAMI/2" }));
        cmbRuangan.setName("cmbRuangan"); // NOI18N
        cmbRuangan.setPreferredSize(new java.awt.Dimension(120, 20));
        cmbRuangan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbRuanganMouseClicked(evt);
            }
        });
        cmbRuangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRuanganActionPerformed(evt);
            }
        });
        panelisi4.add(cmbRuangan);
        cmbRuangan.setBounds(96, 40, 180, 23);

        internalFrame1.add(panelisi4, java.awt.BorderLayout.PAGE_START);

        panelisi1.setName("panelisi1"); // NOI18N
        panelisi1.setPreferredSize(new java.awt.Dimension(100, 107));
        panelisi1.setLayout(new java.awt.BorderLayout());

        panelisi2.setName("panelisi2"); // NOI18N
        panelisi2.setPreferredSize(new java.awt.Dimension(100, 56));
        panelisi2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

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
        panelisi2.add(BtnBatal);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Yang mau diprint : ");
        jLabel9.setName("jLabel9"); // NOI18N
        jLabel9.setPreferredSize(new java.awt.Dimension(100, 23));
        panelisi2.add(jLabel9);

        cmbPrin.setForeground(new java.awt.Color(0, 0, 0));
        cmbPrin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Detail Visite Semua Hari Perawatan Dokter Yang Menangani", "Detail Visite Semua Hari Perawatan Dokter Umum/Yang Mewakili", "Rekap Visite Semua Hari Perawatan Dokter Yang Menangani", "Rekap Visite Semua Hari Perawatan Dokter Umum/Yang Mewakili", "Export File Excel Visite Dokter Umum/Yang Mewakili", "Export File Excel Visite Dokter Pasien COVID-19", "Export File Excel Konsul Dokter Pasien COVID-19", "Detail Visite Hari Normal Dokter Yang Menangani", "Detail Visite Hari Normal Dokter Umum/Yang Mewakili", "Rekap Visite Hari Normal Dokter Yang Menangani", "Rekap Visite Hari Normal Dokter Umum/Yang Mewakili", "Detail Visite Hari Libur Dokter Yang Menangani", "Detail Visite Hari Libur Dokter Umum/Yang Mewakili", "Rekap Visite Hari Libur Dokter Yang Menangani", "Rekap Visite Hari Libur Dokter Umum/Yang Mewakili", "Export File Excel Hitung Visite PerDokter Pasien BPJS" }));
        cmbPrin.setName("cmbPrin"); // NOI18N
        cmbPrin.setPreferredSize(new java.awt.Dimension(350, 23));
        panelisi2.add(cmbPrin);

        BtnPrint.setForeground(new java.awt.Color(0, 0, 0));
        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint.setMnemonic('T');
        BtnPrint.setText("Cetak");
        BtnPrint.setToolTipText("Alt+T");
        BtnPrint.setName("BtnPrint"); // NOI18N
        BtnPrint.setPreferredSize(new java.awt.Dimension(80, 30));
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
        panelisi2.add(BtnPrint);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(65, 23));
        panelisi2.add(jLabel7);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(100, 23));
        panelisi2.add(LCount);

        panelisi1.add(panelisi2, java.awt.BorderLayout.PAGE_START);

        panelisi3.setName("panelisi3"); // NOI18N
        panelisi3.setPreferredSize(new java.awt.Dimension(100, 56));
        panelisi3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Key Word : ");
        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setPreferredSize(new java.awt.Dimension(80, 23));
        panelisi3.add(jLabel10);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelisi3.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('2');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+2");
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
        panelisi3.add(BtnAll);

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

        panelisi1.add(panelisi3, java.awt.BorderLayout.CENTER);

        internalFrame1.add(panelisi1, java.awt.BorderLayout.PAGE_END);

        TabRawat.setBackground(new java.awt.Color(204, 255, 204));
        TabRawat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 235, 225)));
        TabRawat.setTabPlacement(javax.swing.JTabbedPane.RIGHT);
        TabRawat.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        internalFrame2.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        scrollPane1.setName("scrollPane1"); // NOI18N
        scrollPane1.setOpaque(true);

        tbVisit.setAutoCreateRowSorter(true);
        tbVisit.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbVisit.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbVisit.setName("tbVisit"); // NOI18N
        scrollPane1.setViewportView(tbVisit);

        internalFrame2.add(scrollPane1, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("RINCIAN Visit Dokter Yg. Menangani", internalFrame2);

        internalFrame3.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame3.setBorder(null);
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        scrollPane2.setName("scrollPane2"); // NOI18N
        scrollPane2.setOpaque(true);

        tbVisit1.setAutoCreateRowSorter(true);
        tbVisit1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbVisit1.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbVisit1.setName("tbVisit1"); // NOI18N
        scrollPane2.setViewportView(tbVisit1);

        internalFrame3.add(scrollPane2, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("RINCIAN Visit Dokter Umum/Yg. Mewakili", internalFrame3);

        internalFrame4.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame4.setBorder(null);
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout(1, 1));

        scrollPane3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Rekap Visite Semua Hari Perawatan Dokter Yang Menangani ]  ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(0, 0, 0))); // NOI18N
        scrollPane3.setName("scrollPane3"); // NOI18N
        scrollPane3.setOpaque(true);
        scrollPane3.setPreferredSize(new java.awt.Dimension(460, 210));

        tbRekap0.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbRekap0.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbRekap0.setName("tbRekap0"); // NOI18N
        scrollPane3.setViewportView(tbRekap0);

        internalFrame4.add(scrollPane3, java.awt.BorderLayout.PAGE_START);

        scrollPane5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Rekap Visite Hari NORMAL Dokter Yang Menangani ]  ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(0, 0, 0))); // NOI18N
        scrollPane5.setName("scrollPane5"); // NOI18N
        scrollPane5.setOpaque(true);
        scrollPane5.setPreferredSize(new java.awt.Dimension(460, 160));

        tbRekap2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbRekap2.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbRekap2.setName("tbRekap2"); // NOI18N
        scrollPane5.setViewportView(tbRekap2);

        internalFrame4.add(scrollPane5, java.awt.BorderLayout.CENTER);

        scrollPane6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Rekap Visite Hari LIBUR Dokter Yang Menangani ]  ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(0, 0, 0))); // NOI18N
        scrollPane6.setName("scrollPane6"); // NOI18N
        scrollPane6.setOpaque(true);
        scrollPane6.setPreferredSize(new java.awt.Dimension(460, 200));

        tbRekap3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbRekap3.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbRekap3.setName("tbRekap3"); // NOI18N
        scrollPane6.setViewportView(tbRekap3);

        internalFrame4.add(scrollPane6, java.awt.BorderLayout.PAGE_END);

        TabRawat.addTab("REKAP Visite Dokter Yg. Menangani", internalFrame4);

        internalFrame5.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame5.setBorder(null);
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setLayout(new java.awt.BorderLayout(1, 1));

        scrollPane4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Rekap Visite Semua Hari Perawatan Dokter Umum/Yg. Mewakili ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(0, 0, 0))); // NOI18N
        scrollPane4.setName("scrollPane4"); // NOI18N
        scrollPane4.setOpaque(true);
        scrollPane4.setPreferredSize(new java.awt.Dimension(460, 210));

        tbRekap1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbRekap1.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbRekap1.setName("tbRekap1"); // NOI18N
        scrollPane4.setViewportView(tbRekap1);

        internalFrame5.add(scrollPane4, java.awt.BorderLayout.PAGE_START);

        scrollPane7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Rekap Visite Hari NORMAL Dokter Umum/Yg. Mewakili ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(0, 0, 0))); // NOI18N
        scrollPane7.setName("scrollPane7"); // NOI18N
        scrollPane7.setOpaque(true);
        scrollPane7.setPreferredSize(new java.awt.Dimension(460, 160));

        tbRekap4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbRekap4.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbRekap4.setName("tbRekap4"); // NOI18N
        scrollPane7.setViewportView(tbRekap4);

        internalFrame5.add(scrollPane7, java.awt.BorderLayout.CENTER);

        scrollPane8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Rekap Visite Hari LIBUR Dokter Umum/Yg. Mewakili ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(0, 0, 0))); // NOI18N
        scrollPane8.setName("scrollPane8"); // NOI18N
        scrollPane8.setOpaque(true);
        scrollPane8.setPreferredSize(new java.awt.Dimension(460, 200));

        tbRekap5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbRekap5.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbRekap5.setName("tbRekap5"); // NOI18N
        scrollPane8.setViewportView(tbRekap5);

        internalFrame5.add(scrollPane8, java.awt.BorderLayout.PAGE_END);

        TabRawat.addTab("REKAP Visite Dokter Umum/Yg. Mewakili", internalFrame5);

        internalFrame6.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame6.setBorder(null);
        internalFrame6.setName("internalFrame6"); // NOI18N
        internalFrame6.setLayout(new java.awt.BorderLayout(1, 1));

        scrollPane9.setName("scrollPane9"); // NOI18N
        scrollPane9.setOpaque(true);

        tbHitung.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbHitung.setToolTipText("");
        tbHitung.setName("tbHitung"); // NOI18N
        scrollPane9.setViewportView(tbHitung);

        internalFrame6.add(scrollPane9, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Hitung Visite PerDokter Pasien BPJS", internalFrame6);

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
        if (cmbPrin.getSelectedItem().toString().equals(" ")) {
            JOptionPane.showMessageDialog(null, "Pilih dulu salah satu jenis laporan yang akan diprint...!!");
            cmbPrin.requestFocus();
        } else {
            if (cmbPrin.getSelectedIndex() == 1) {
                if (tabMode.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
                    BtnBatal.requestFocus();
                } else if (tabMode.getRowCount() != 0) {
                    prinDetailVisitMenangani();
                }
            } else if (cmbPrin.getSelectedIndex() == 2) {
                if (tabMode1.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
                    BtnBatal.requestFocus();
                } else if (tabMode1.getRowCount() != 0) {
                    prinDetailVisitMewakili();
                }
            } else if (cmbPrin.getSelectedIndex() == 3) {
                prinRekapDokterMenangani();
            } else if (cmbPrin.getSelectedIndex() == 4) {
                prinRekapDokterMewakili();
            } else if (cmbPrin.getSelectedIndex() == 5) {
                exportFileExcelRekapDokterMewakili();
            } else if (cmbPrin.getSelectedIndex() == 6) {
                exportFileExcelVisitCovid();
            } else if (cmbPrin.getSelectedIndex() == 7) {
                exportFileExcelKonsulCovid();            
            } else if (cmbPrin.getSelectedIndex() == 8) {
                if (tabMode.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
                    BtnBatal.requestFocus();
                } else if (tabMode.getRowCount() != 0) {
                    prinDetailVisitMenangani();
                }            
            } else if (cmbPrin.getSelectedIndex() == 9) {
                if (tabMode1.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
                    BtnBatal.requestFocus();
                } else if (tabMode1.getRowCount() != 0) {
                    prinDetailVisitMewakili();
                }
            } else if (cmbPrin.getSelectedIndex() == 10) {                
                prinRekapDokterMenangani();                
            } else if (cmbPrin.getSelectedIndex() == 11) {
                prinRekapDokterMewakili();
            } else if (cmbPrin.getSelectedIndex() == 12) {
                if (tabMode.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
                    BtnBatal.requestFocus();
                } else if (tabMode.getRowCount() != 0) {
                    prinDetailVisitMenangani();
                }
            } else if (cmbPrin.getSelectedIndex() == 13) {
                if (tabMode1.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
                    BtnBatal.requestFocus();
                } else if (tabMode1.getRowCount() != 0) {
                    prinDetailVisitMewakili();
                }
            } else if (cmbPrin.getSelectedIndex() == 14) {
                prinRekapDokterMenangani();
            } else if (cmbPrin.getSelectedIndex() == 15) {
                prinRekapDokterMewakili();
            } else if (cmbPrin.getSelectedIndex() == 16) {
                if (cmbRuangan.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu ruangan inapnya..!!");
                    cmbRuangan.requestFocus();
                } else {
                    exportFileExcelHitungVisit();
                }
            }
        }
        
//        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//        if(tabMode.getRowCount()==0){
//            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
//            //TCari.requestFocus();
//        }else if(tabMode.getRowCount()!=0){
//            Sequel.AutoComitFalse();
//            Sequel.queryu("delete from temporary");
//            for(i=0;i<tabMode.getRowCount();i++){  
//                try {
//                    sjmlvisit=Valid.SetAngka(Double.parseDouble(tabMode.getValueAt(i,6).toString()));
//                } catch (Exception e) {
//                    sjmlvisit="";
//                }
//                
//                try {
//                    sjmlbyphone=Valid.SetAngka(Double.parseDouble(tabMode.getValueAt(i,7).toString()));
//                } catch (Exception e) {
//                    sjmlbyphone="";
//                }
//                
//                try {
//                    starifvisit=Valid.SetAngka(Double.parseDouble(tabMode.getValueAt(i,8).toString()));
//                } catch (Exception e) {
//                    starifvisit="";
//                }
//                
//                try {
//                    starifbyphone=Valid.SetAngka(Double.parseDouble(tabMode.getValueAt(i,9).toString()));
//                } catch (Exception e) {
//                    starifbyphone="";
//                }
//                
//                try {
//                    svisit=Valid.SetAngka(Double.parseDouble(tabMode.getValueAt(i,10).toString()));
//                } catch (Exception e) {
//                    svisit="";
//                }
//                
//                try {
//                    sbyphone=Valid.SetAngka(Double.parseDouble(tabMode.getValueAt(i,11).toString()));
//                } catch (Exception e) {
//                    sbyphone="";
//                }
//                
//                try {
//                    sbruto=Valid.SetAngka(Double.parseDouble(tabMode.getValueAt(i,12).toString()));
//                } catch (Exception e) {
//                    sbruto="";
//                }
//                
//                try {
//                    sjasa=Valid.SetAngka(Double.parseDouble(tabMode.getValueAt(i,13).toString()));
//                } catch (Exception e) {
//                    sjasa="";
//                }
//                
//                try {
//                    suangrs=Valid.SetAngka(Double.parseDouble(tabMode.getValueAt(i,14).toString()));
//                } catch (Exception e) {
//                    suangrs="";
//                }
//                Sequel.menyimpan("temporary","'0','"+
//                                tabMode.getValueAt(i,0).toString().replaceAll("'","`") +"','"+
//                                tabMode.getValueAt(i,1).toString().replaceAll("'","`")+"','"+
//                                tabMode.getValueAt(i,2).toString().replaceAll("'","`")+"','"+
//                                tabMode.getValueAt(i,3).toString().replaceAll("'","`")+"','"+
//                                tabMode.getValueAt(i,4).toString().replaceAll("'","`")+"','"+
//                                tabMode.getValueAt(i,5).toString().replaceAll("'","`")+"','"+
//                                sjmlvisit+"','"+
//                                sjmlbyphone+"','"+
//                                starifvisit+"','"+
//                                starifbyphone+"','"+
//                                svisit+"','"+
//                                sbyphone+"','"+
//                                sbruto+"','"+
//                                sjasa+"','"+
//                                suangrs+"','','','','','','','','','','','','','','','','','','','','','',''","JM Dokter"); 
//            }
//            Sequel.AutoComitTrue();
//            Map<String, Object> param = new HashMap<>();   
//                param.put("namars",var.getnamars());
//                param.put("alamatrs",var.getalamatrs());
//                param.put("kotars",var.getkabupatenrs());
//                param.put("propinsirs",var.getpropinsirs());
//                param.put("kontakrs",var.getkontakrs());
//                param.put("emailrs",var.getemailrs()); 
//                param.put("dokter",nmdokter.getText());
//                param.put("periode",Tgl1.getSelectedItem()+" s/d "+Tgl2.getSelectedItem());   
//                param.put("logo",Sequel.cariGambar("select logo from setting")); 
//            Valid.MyReport("rptFeeVisitDokter.jasper","report","[ Rekap Jasa Visit Dokter ]",
//                "select * from temporary order by no asc",param);
//        }
//        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPrintActionPerformed(null);
        }else{
            Valid.pindah(evt,kddokter,BtnKeluar);
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

    private void kddokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kddokterKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", nmdokter,kddokter.getText()); 
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", nmdokter,kddokter.getText()); 
            Tgl2.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", nmdokter,kddokter.getText()); 
            BtnPrint.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            BtnSeek2ActionPerformed(null);
        }
    }//GEN-LAST:event_kddokterKeyPressed

private void BtnSeek2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeek2ActionPerformed
        dokter.isCek();
        dokter.setSize(1039,390);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
}//GEN-LAST:event_BtnSeek2ActionPerformed

private void BtnSeek2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSeek2KeyPressed
   //Valid.pindah(evt,DTPCari2,TCari);
}//GEN-LAST:event_BtnSeek2KeyPressed

private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
    cmbPrin.setSelectedIndex(0);
    
    if (TabRawat.getSelectedIndex() == 0) {
        prosesCari();
    } else if (TabRawat.getSelectedIndex() == 1) {
        prosesCari1();
    } else if (TabRawat.getSelectedIndex() == 2) {
        rekapVisit();
        rekapVisitNormal1();
        rekapVisitLibur1();
    } else if (TabRawat.getSelectedIndex() == 3) {
        rekapVisit1();
        rekapVisitNormal2();
        rekapVisitLibur2();
    } else if (TabRawat.getSelectedIndex() == 4) {
        if (cmbRuangan.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu ruangan inapnya..!!");
            cmbRuangan.requestFocus();
        } else {
            insertDataKeTabeltemporary1();
            tampilHitungVisit();
        }
    }
}//GEN-LAST:event_BtnCariActionPerformed

private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
        BtnCariActionPerformed(null);
    } else {
        Valid.pindah(evt, kddokter, BtnPrint);
    }
}//GEN-LAST:event_BtnCariKeyPressed

    private void Tgl1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tgl1KeyPressed
        Valid.pindah(evt, BtnKeluar,Tgl2);
    }//GEN-LAST:event_Tgl1KeyPressed

    private void Tgl2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tgl2KeyPressed
        Valid.pindah(evt, Tgl1,kddokter);
    }//GEN-LAST:event_Tgl2KeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Sequel.cariIsiComboDB("SELECT nm_gedung FROM bangsal WHERE nm_gedung<>'igd' and nm_gedung<>'-' and status='1' GROUP BY nm_gedung ORDER BY nm_gedung", cmbRuangan);

        Tgl1.requestFocus();
        
        if (TabRawat.getSelectedIndex() == 0) {
            prosesCari();
        } else if (TabRawat.getSelectedIndex() == 1) {
            prosesCari1();
        } else if (TabRawat.getSelectedIndex() == 2) {
            rekapVisit();
        } else if (TabRawat.getSelectedIndex() == 3) {
            rekapVisit1();
        }
    }//GEN-LAST:event_formWindowOpened

    private void kdpenjabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdpenjabKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            Sequel.cariIsi("select nm_poli from poliklinik where kd_poli=?", nmpenjab, kdpenjab.getText());
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            btnBayarActionPerformed(null);
        } 
    }//GEN-LAST:event_kdpenjabKeyPressed

    private void btnBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBayarActionPerformed
        akses.setform("DlgFeeVisitDokter");
        penjab.emptTeks();
        penjab.isCek();
        penjab.setSize(882, 470);
        penjab.setLocationRelativeTo(internalFrame1);
        penjab.setVisible(true);
    }//GEN-LAST:event_btnBayarActionPerformed

    private void cmbRuanganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbRuanganMouseClicked
        cmbRuanganActionPerformed(null);
    }//GEN-LAST:event_cmbRuanganMouseClicked

    private void cmbRuanganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRuanganActionPerformed
        ruangDipilih = "";

        if (cmbRuangan.getSelectedIndex() == 1) {
            ruangDipilih = "b.nm_gedung in ('RKPD','ZAAL')";
        } else if (cmbRuangan.getSelectedIndex() == 2) {
            ruangDipilih = "b.nm_gedung in ('PERINATOLOGI','BAYI SEHAT')";
        } else if (cmbRuangan.getSelectedItem().equals("AR-RAUDAH BAWAH")) {
            ruangDipilih = "b.nm_bangsal like '%Ar-Raudah/Bawah%'";
        } else if (cmbRuangan.getSelectedItem().equals("AR-RAUDAH ATAS")) {
            ruangDipilih = "b.nm_bangsal like '%Ar-Raudah/Atas%'";
        } else if (cmbRuangan.getSelectedItem().equals("AS-SAMI/1") || cmbRuangan.getSelectedItem().equals("AS-SAMI/2")) {
            ruangDipilih = "b.nm_bangsal like '%" + cmbRuangan.getSelectedItem().toString() + "%'";
        } else if (cmbRuangan.getSelectedItem().equals("AS-SAMI") || cmbRuangan.getSelectedIndex() == 3) {
            ruangDipilih = "b.nm_gedung = '" + cmbRuangan.getSelectedItem().toString() + "'";
        } else {
            ruangDipilih = "b.nm_gedung = '" + cmbRuangan.getSelectedItem().toString() + "'";
        }
    }//GEN-LAST:event_cmbRuanganActionPerformed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        empttext();
    }//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnBatalActionPerformed(null);
        }
    }//GEN-LAST:event_BtnBatalKeyPressed

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        cmbPrin.setSelectedIndex(0);
        if (TabRawat.getSelectedIndex() == 0) {
            prosesCari();
        } else if (TabRawat.getSelectedIndex() == 1) {
            prosesCari1();
        } else if (TabRawat.getSelectedIndex() == 2) {
            rekapVisit();
            rekapVisitNormal1();
            rekapVisitLibur1();
        } else if (TabRawat.getSelectedIndex() == 3) {
            rekapVisit1();
            rekapVisitNormal2();
            rekapVisitLibur2();
        } else if (TabRawat.getSelectedIndex() == 4) {
            if (cmbRuangan.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu ruangan inapnya..!!");
                cmbRuangan.requestFocus();
            } else {
                insertDataKeTabeltemporary1();
                tampilHitungVisit();
            }
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        if (TabRawat.getSelectedIndex() == 4) {
            TCari.setText("");
            BtnCariActionPerformed(null);
        } else {
            TCari.setText("");
            empttext();
            BtnCariActionPerformed(null);
        }
    }//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            if (TabRawat.getSelectedIndex() == 4) {
                TCari.setText("");
                BtnCariActionPerformed(null);
            } else {
                TCari.setText("");
                empttext();
                BtnCariActionPerformed(null);
            }
        } else {
            Valid.pindah(evt, TCari, BtnKeluar);
        }
    }//GEN-LAST:event_BtnAllKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        }
    }//GEN-LAST:event_TCariKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgFeeVisitDokter dialog = new DlgFeeVisitDokter(new javax.swing.JFrame(), true);
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
    private widget.Button BtnPrint;
    private widget.Button BtnSeek2;
    private widget.Label LCount;
    private widget.TextBox TCari;
    private javax.swing.JTabbedPane TabRawat;
    private widget.Tanggal Tgl1;
    private widget.Tanggal Tgl2;
    private widget.Button btnBayar;
    private widget.ComboBox cmbPrin;
    private widget.ComboBox cmbRuangan;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame4;
    private widget.InternalFrame internalFrame5;
    private widget.InternalFrame internalFrame6;
    private widget.Label jLabel10;
    private widget.Label jLabel17;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private widget.TextBox kddokter;
    private widget.TextBox kdpenjab;
    private widget.Label label11;
    private widget.Label label17;
    private widget.Label label18;
    private widget.TextBox nmdokter;
    private widget.TextBox nmpenjab;
    private widget.panelisi panelisi1;
    private widget.panelisi panelisi2;
    private widget.panelisi panelisi3;
    private widget.panelisi panelisi4;
    private widget.ScrollPane scrollPane1;
    private widget.ScrollPane scrollPane2;
    private widget.ScrollPane scrollPane3;
    private widget.ScrollPane scrollPane4;
    private widget.ScrollPane scrollPane5;
    private widget.ScrollPane scrollPane6;
    private widget.ScrollPane scrollPane7;
    private widget.ScrollPane scrollPane8;
    private widget.ScrollPane scrollPane9;
    private widget.Table tbHitung;
    private widget.Table tbRekap0;
    private widget.Table tbRekap1;
    private widget.Table tbRekap2;
    private widget.Table tbRekap3;
    private widget.Table tbRekap4;
    private widget.Table tbRekap5;
    private widget.Table tbVisit;
    private widget.Table tbVisit1;
    // End of variables declaration//GEN-END:variables

    private void prosesCari(){
        Valid.tabelKosong(tabMode);    
        try {
            if (cmbRuangan.getSelectedItem().toString().equals("SEMUA RUANG")) {
                psvisit = koneksi.prepareStatement("SELECT date_format(rd.tgl_perawatan,'%d-%m-%Y') tgl_visit, ji.nm_perawatan, d.nm_dokter, "
                        + "rp.no_rkm_medis no_rm, p.nm_pasien, b.nm_bangsal, pj.png_jawab cr_byr FROM rawat_inap_dr rd "
                        + "INNER JOIN jns_perawatan_inap ji on ji.kd_jenis_prw=rd.kd_jenis_prw and ji.nm_perawatan like '%visit%' "
                        + "INNER JOIN dokter d on d.kd_dokter=rd.kd_dokter INNER JOIN reg_periksa rp on rp.no_rawat=rd.no_rawat "
                        + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat "
                        + "INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj WHERE "
                        + "rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') and rd.kd_dokter not in ('-','--') and "
                        + "pj.png_jawab like ? and rd.kd_dokter like ? and rp.no_rkm_medis like ? or "
                        + "rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') and rd.kd_dokter not in ('-','--') and "
                        + "pj.png_jawab like ? and rd.kd_dokter like ? and p.nm_pasien like ? or "
                        + "rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') and rd.kd_dokter not in ('-','--') and "
                        + "pj.png_jawab like ? and rd.kd_dokter like ? and ji.nm_perawatan like ? "
                        + "ORDER BY d.nm_dokter, rd.tgl_perawatan");
            } else {
                psvisit = koneksi.prepareStatement("SELECT date_format(rd.tgl_perawatan,'%d-%m-%Y') tgl_visit, ji.nm_perawatan, d.nm_dokter, "
                        + "rp.no_rkm_medis no_rm, p.nm_pasien, b.nm_bangsal, pj.png_jawab cr_byr FROM rawat_inap_dr rd "
                        + "INNER JOIN jns_perawatan_inap ji on ji.kd_jenis_prw=rd.kd_jenis_prw and ji.nm_perawatan like '%visit%' "
                        + "INNER JOIN dokter d on d.kd_dokter=rd.kd_dokter INNER JOIN reg_periksa rp on rp.no_rawat=rd.no_rawat "
                        + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat "
                        + "INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj WHERE "
                        + "rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') and rd.kd_dokter not in ('-','--') and " + ruangDipilih + " and "
                        + "pj.png_jawab like ? and rd.kd_dokter like ? and rp.no_rkm_medis like ? or "
                        + "rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') and rd.kd_dokter not in ('-','--') and " + ruangDipilih + " and "
                        + "pj.png_jawab like ? and rd.kd_dokter like ? and p.nm_pasien like ? or "
                        + "rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') and rd.kd_dokter not in ('-','--') and " + ruangDipilih + " and "
                        + "pj.png_jawab like ? and rd.kd_dokter like ? and ji.nm_perawatan like ? "
                        + "ORDER BY d.nm_dokter, rd.tgl_perawatan");
            }

            try {
                psvisit.setString(1, "%" + nmpenjab.getText().trim() + "%");
                psvisit.setString(2, "%" + kddokter.getText().trim() + "%");
                psvisit.setString(3, "%" + TCari.getText().trim() + "%");
                psvisit.setString(4, "%" + nmpenjab.getText().trim() + "%");
                psvisit.setString(5, "%" + kddokter.getText().trim() + "%");
                psvisit.setString(6, "%" + TCari.getText().trim() + "%");
                psvisit.setString(7, "%" + nmpenjab.getText().trim() + "%");
                psvisit.setString(8, "%" + kddokter.getText().trim() + "%");
                psvisit.setString(9, "%" + TCari.getText().trim() + "%");
                rsvisit = psvisit.executeQuery();
                while (rsvisit.next()) {
                    tabMode.addRow(new Object[]{
                        rsvisit.getString("tgl_visit"),
                        rsvisit.getString("nm_perawatan"),
                        rsvisit.getString("nm_dokter"),
                        rsvisit.getString("no_rm"),
                        rsvisit.getString("nm_pasien"),
                        rsvisit.getString("nm_bangsal"),
                        rsvisit.getString("cr_byr")
                    });
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println("simrskhanza.DlgFeeVisitDokter.prosesCari() : " + e);
            } finally {
                if (rsvisit != null) {
                    rsvisit.close();
                }
                if (psvisit != null) {
                    psvisit.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText(""+tabMode.getRowCount());
        
//        try{  
//            pskamar.setString(1,Valid.SetTgl(Tgl1.getSelectedItem()+""));
//            pskamar.setString(2,Valid.SetTgl(Tgl2.getSelectedItem()+""));
//            rskamar=pskamar.executeQuery();
//            i=1;ttljmlvisit=0;ttljmlbyphone=0;ttlvisit=0;ttlbyphone=0;ttlbruto=0;ttljasa=0;ttluangrs=0;
//            while(rskamar.next()){
//                jmlvisit=0;jmlbyphone=0;visit=0;byphone=0;bruto=0;jasa=0;uangrs=0;
//                
//                psvisit.setString(1,kddokter.getText());
//                psvisit.setString(2,rskamar.getString("no_rawat"));
//                rsvisit=psvisit.executeQuery();
//                if(rsvisit.next()){
//                    jmlvisit=rsvisit.getInt("jml");
//                    visit=rsvisit.getDouble("totalbiaya");
//                    tarifvisit=rsvisit.getDouble("tarif");
//                    bruto=rsvisit.getDouble("totalbiaya");
//                    jasa=rsvisit.getDouble("bayardokter");
//                    uangrs=rsvisit.getDouble("material")+rsvisit.getDouble("bhp");
//                }
//                
//                psbyphone.setString(1,kddokter.getText());
//                psbyphone.setString(2,rskamar.getString("no_rawat"));
//                rsbyphone=psbyphone.executeQuery();
//                if(rsbyphone.next()){
//                    jmlbyphone=rsbyphone.getInt("jml");
//                    byphone=rsbyphone.getDouble("totalbiaya");
//                    tarifbyphone=rsbyphone.getDouble("tarif");
//                    bruto=bruto+rsbyphone.getDouble("totalbiaya");
//                    jasa=jasa+rsbyphone.getDouble("bayardokter");
//                    uangrs=uangrs+rsbyphone.getDouble("material")+rsbyphone.getDouble("bhp");
//                }
//                
//                if(bruto>0){
//                    tabMode.addRow(new Object[]{
//                        i,rskamar.getString("tgl_masuk"),rskamar.getString("tgl_keluar"),
//                        rskamar.getString("nm_pasien"),rskamar.getString("kd_kamar")+
//                        " "+rskamar.getString("nm_bangsal"),rskamar.getString("png_jawab"),
//                        jmlvisit,jmlbyphone,tarifvisit,tarifbyphone,visit,byphone,bruto,jasa,uangrs
//                    });
//                }
//                i++;
//                ttljmlvisit=ttljmlvisit+jmlvisit;
//                ttljmlbyphone=ttljmlbyphone+jmlbyphone;
//                ttlvisit=ttlvisit+visit;
//                ttlbyphone=ttlbyphone+byphone;
//                ttlbruto=ttlbruto+bruto;
//                ttljasa=ttljasa+jasa;
//                ttluangrs=ttluangrs+uangrs;
//            }
//            
//            if(ttlbruto>0){
//                tabMode.addRow(new Object[]{
//                    "","","","Jumlah :","","",ttljmlvisit,ttljmlbyphone,
//                    0,0,ttlvisit,ttlbyphone,ttlbruto,ttljasa,ttluangrs
//                });
//            }
//        }catch(Exception e){
//            System.out.println("Catatan  "+e);
//        }        
    }
    
    private void prosesCari1(){
        Valid.tabelKosong(tabMode1);    
        try {
            if (cmbRuangan.getSelectedItem().toString().equals("SEMUA RUANG")) {
                psvisit1 = koneksi.prepareStatement("SELECT date_format(rd.tgl_perawatan,'%d-%m-%Y') tgl_visit, ji.nm_perawatan, d.nm_dokter, "
                        + "rp.no_rkm_medis no_rm, p.nm_pasien, b.nm_bangsal, pj.png_jawab cr_byr FROM rawat_inap_dr rd "
                        + "INNER JOIN jns_perawatan_inap ji on ji.kd_jenis_prw=rd.kd_jenis_prw and ji.nm_perawatan like '%visit%' "
                        + "INNER JOIN dokter d on d.kd_dokter=rd.kd_dokter_mewakili INNER JOIN reg_periksa rp on rp.no_rawat=rd.no_rawat "
                        + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat "
                        + "INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj WHERE "
                        + "rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') and rd.kd_dokter_mewakili not in ('-','--') and "
                        + "pj.png_jawab like ? and rd.kd_dokter_mewakili like ? and rp.no_rkm_medis like ? or "
                        + "rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') and rd.kd_dokter_mewakili not in ('-','--') and "
                        + "pj.png_jawab like ? and rd.kd_dokter_mewakili like ? and p.nm_pasien like ? or "
                        + "rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') and rd.kd_dokter_mewakili not in ('-','--') and "
                        + "pj.png_jawab like ? and rd.kd_dokter_mewakili like ? and ji.nm_perawatan like ? "
                        + "ORDER BY d.nm_dokter, rd.tgl_perawatan");
            } else {
                psvisit1 = koneksi.prepareStatement("SELECT date_format(rd.tgl_perawatan,'%d-%m-%Y') tgl_visit, ji.nm_perawatan, d.nm_dokter, "
                        + "rp.no_rkm_medis no_rm, p.nm_pasien, b.nm_bangsal, pj.png_jawab cr_byr FROM rawat_inap_dr rd "
                        + "INNER JOIN jns_perawatan_inap ji on ji.kd_jenis_prw=rd.kd_jenis_prw and ji.nm_perawatan like '%visit%' "
                        + "INNER JOIN dokter d on d.kd_dokter=rd.kd_dokter_mewakili INNER JOIN reg_periksa rp on rp.no_rawat=rd.no_rawat "
                        + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat "
                        + "INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj WHERE "
                        + "rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') and rd.kd_dokter_mewakili not in ('-','--') and " + ruangDipilih + " and "
                        + "pj.png_jawab like ? and rd.kd_dokter_mewakili like ? and rp.no_rkm_medis like ? or "
                        + "rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') and rd.kd_dokter_mewakili not in ('-','--') and " + ruangDipilih + " and "
                        + "pj.png_jawab like ? and rd.kd_dokter_mewakili like ? and p.nm_pasien like ? or "
                        + "rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') and rd.kd_dokter_mewakili not in ('-','--') and " + ruangDipilih + " and "
                        + "pj.png_jawab like ? and rd.kd_dokter_mewakili like ? and ji.nm_perawatan like ? "
                        + "ORDER BY d.nm_dokter, rd.tgl_perawatan");
            }

            try {
                psvisit1.setString(1, "%" + nmpenjab.getText().trim() + "%");
                psvisit1.setString(2, "%" + kddokter.getText().trim() + "%");
                psvisit1.setString(3, "%" + TCari.getText().trim() + "%");
                psvisit1.setString(4, "%" + nmpenjab.getText().trim() + "%");
                psvisit1.setString(5, "%" + kddokter.getText().trim() + "%");
                psvisit1.setString(6, "%" + TCari.getText().trim() + "%");
                psvisit1.setString(7, "%" + nmpenjab.getText().trim() + "%");
                psvisit1.setString(8, "%" + kddokter.getText().trim() + "%");
                psvisit1.setString(9, "%" + TCari.getText().trim() + "%");

                rsvisit1 = psvisit1.executeQuery();
                while (rsvisit1.next()) {
                    tabMode1.addRow(new Object[]{
                        rsvisit1.getString("tgl_visit"),
                        rsvisit1.getString("nm_perawatan"),
                        rsvisit1.getString("nm_dokter"),
                        rsvisit1.getString("no_rm"),
                        rsvisit1.getString("nm_pasien"),
                        rsvisit1.getString("nm_bangsal"),
                        rsvisit1.getString("cr_byr")
                    });
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println("simrskhanza.DlgFeeVisitDokter.prosesCari1() : " + e);
            } finally {
                if (rsvisit1 != null) {
                    rsvisit1.close();
                }
                if (psvisit1 != null) {
                    psvisit1.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText(""+tabMode1.getRowCount());
    }
    
    private void rekapVisit(){
        Valid.tabelKosong(tabMode2);    
        try {
            if (cmbRuangan.getSelectedItem().toString().equals("SEMUA RUANG")) {
                psrekap = koneksi.prepareStatement("select a.nm_dokter, "
                        + "CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT) Kelas_1, "
                        + "CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT) Kelas_2, "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT) Kelas_3, "
                        + "CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT) Kelas_vip, "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT) Kelas_vvip, "
                        + "CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end),INT) rwt_khusus, "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT) intensif, "
                        + "CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT) high_care, "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT) isolasi, "
                        + "CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end)+ "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT),INT) total_visit "
                        + "from (SELECT d.nm_dokter,k.kelas,COUNT(rd.kd_dokter) total FROM rawat_inap_dr rd "
                        + "INNER JOIN jns_perawatan_inap ji on ji.kd_jenis_prw=rd.kd_jenis_prw INNER JOIN dokter d on d.kd_dokter=rd.kd_dokter "
                        + "INNER JOIN reg_periksa rp on rp.no_rawat=rd.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                        + "INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar "
                        + "INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj "
                        + "WHERE rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and ji.nm_perawatan like '%visit%' and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') and rd.kd_dokter not in ('-','--') "
                        + "and pj.png_jawab like ? GROUP BY rd.kd_dokter, k.kelas ORDER BY d.nm_dokter, k.kelas) as a group by a.nm_dokter");
            } else {
                psrekap = koneksi.prepareStatement("select a.nm_dokter, "
                        + "CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT) Kelas_1, "
                        + "CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT) Kelas_2, "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT) Kelas_3, "
                        + "CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT) Kelas_vip, "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT) Kelas_vvip, "
                        + "CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end),INT) rwt_khusus, "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT) intensif, "
                        + "CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT) high_care, "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT) isolasi, "
                        + "CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end)+ "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT),INT) total_visit "
                        + "from (SELECT d.nm_dokter,k.kelas,COUNT(rd.kd_dokter) total FROM rawat_inap_dr rd "
                        + "INNER JOIN jns_perawatan_inap ji on ji.kd_jenis_prw=rd.kd_jenis_prw INNER JOIN dokter d on d.kd_dokter=rd.kd_dokter "
                        + "INNER JOIN reg_periksa rp on rp.no_rawat=rd.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                        + "INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar "
                        + "INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj "
                        + "WHERE rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and ji.nm_perawatan like '%visit%' and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') and rd.kd_dokter not in ('-','--') "
                        + "and " + ruangDipilih + " and pj.png_jawab like ? GROUP BY rd.kd_dokter, k.kelas ORDER BY d.nm_dokter, k.kelas) as a group by a.nm_dokter");
            }

            try {
                psrekap.setString(1, "%" + nmpenjab.getText().trim() + "%");
                rsrekap = psrekap.executeQuery();
                x = 1;
                while (rsrekap.next()) {
                    tabMode2.addRow(new Object[]{
                        x + ".",
                        rsrekap.getString("nm_dokter"),
                        rsrekap.getString("Kelas_1"),
                        rsrekap.getString("Kelas_2"),
                        rsrekap.getString("Kelas_3"),
                        rsrekap.getString("Kelas_vip"),
                        rsrekap.getString("Kelas_vvip"),
                        rsrekap.getString("rwt_khusus"),
                        rsrekap.getString("intensif"),
                        rsrekap.getString("high_care"),
                        rsrekap.getString("isolasi"),
                        rsrekap.getString("total_visit")
                    });
                    x++;
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println("simrskhanza.DlgFeeVisitDokter.rekapVisit() : " + e);
            } finally {
                if (rsrekap != null) {
                    rsrekap.close();
                }
                if (psrekap != null) {
                    psrekap.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("-");
    }
    
    private void rekapVisitNormal1(){
        Valid.tabelKosong(tabMode4);    
        try {
            if (cmbRuangan.getSelectedItem().toString().equals("SEMUA RUANG")) {
                psrekap2 = koneksi.prepareStatement("select a.nm_dokter, "
                        + "CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT) Kelas_1, "
                        + "CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT) Kelas_2, "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT) Kelas_3, "
                        + "CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT) Kelas_vip, "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT) Kelas_vvip, "
                        + "CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end),INT) rwt_khusus, "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT) intensif, "
                        + "CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT) high_care, "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT) isolasi, "
                        + "CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end)+ "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT),INT) total_visit "
                        + "from (SELECT d.nm_dokter,k.kelas,COUNT(rd.kd_dokter) total FROM rawat_inap_dr rd "
                        + "INNER JOIN jns_perawatan_inap ji on ji.kd_jenis_prw=rd.kd_jenis_prw and ji.nm_perawatan like '%visit%' "
                        + "INNER JOIN dokter d on d.kd_dokter=rd.kd_dokter "
                        + "INNER JOIN reg_periksa rp on rp.no_rawat=rd.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                        + "INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar "
                        + "INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj "
                        + "WHERE rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and ji.nm_perawatan not like '%libur%' and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') and rd.kd_dokter not in ('-','--') "
                        + "and pj.png_jawab like ? GROUP BY rd.kd_dokter, k.kelas ORDER BY d.nm_dokter, k.kelas) as a group by a.nm_dokter");
            } else {
                psrekap2 = koneksi.prepareStatement("select a.nm_dokter, "
                        + "CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT) Kelas_1, "
                        + "CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT) Kelas_2, "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT) Kelas_3, "
                        + "CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT) Kelas_vip, "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT) Kelas_vvip, "
                        + "CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end),INT) rwt_khusus, "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT) intensif, "
                        + "CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT) high_care, "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT) isolasi, "
                        + "CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end)+ "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT),INT) total_visit "
                        + "from (SELECT d.nm_dokter,k.kelas,COUNT(rd.kd_dokter) total FROM rawat_inap_dr rd "
                        + "INNER JOIN jns_perawatan_inap ji on ji.kd_jenis_prw=rd.kd_jenis_prw and ji.nm_perawatan like '%visit%' "
                        + "INNER JOIN dokter d on d.kd_dokter=rd.kd_dokter "
                        + "INNER JOIN reg_periksa rp on rp.no_rawat=rd.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                        + "INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar "
                        + "INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj "
                        + "WHERE rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and ji.nm_perawatan not like '%libur%' and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') and rd.kd_dokter not in ('-','--') "
                        + "and " + ruangDipilih + " and pj.png_jawab like ? GROUP BY rd.kd_dokter, k.kelas ORDER BY d.nm_dokter, k.kelas) as a group by a.nm_dokter");
            }

            try {
                psrekap2.setString(1, "%" + nmpenjab.getText().trim() + "%");
                rsrekap2 = psrekap2.executeQuery();
                x = 1;
                while (rsrekap2.next()) {
                    tabMode4.addRow(new Object[]{
                        x + ".",
                        rsrekap2.getString("nm_dokter"),
                        rsrekap2.getString("Kelas_1"),
                        rsrekap2.getString("Kelas_2"),
                        rsrekap2.getString("Kelas_3"),
                        rsrekap2.getString("Kelas_vip"),
                        rsrekap2.getString("Kelas_vvip"),
                        rsrekap2.getString("rwt_khusus"),
                        rsrekap2.getString("intensif"),
                        rsrekap2.getString("high_care"),
                        rsrekap2.getString("isolasi"),
                        rsrekap2.getString("total_visit")
                    });
                    x++;
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println("simrskhanza.DlgFeeVisitDokter.rekapVisitNormal1() : " + e);
            } finally {
                if (rsrekap2 != null) {
                    rsrekap2.close();
                }
                if (psrekap2 != null) {
                    psrekap2.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("-");
    }
    
    private void rekapVisitLibur1(){
        Valid.tabelKosong(tabMode5);    
        try {
            if (cmbRuangan.getSelectedItem().toString().equals("SEMUA RUANG")) {
                psrekap3 = koneksi.prepareStatement("select a.nm_dokter, "
                        + "CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT) Kelas_1, "
                        + "CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT) Kelas_2, "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT) Kelas_3, "
                        + "CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT) Kelas_vip, "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT) Kelas_vvip, "
                        + "CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end),INT) rwt_khusus, "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT) intensif, "
                        + "CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT) high_care, "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT) isolasi, "
                        + "CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end)+ "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT),INT) total_visit "
                        + "from (SELECT d.nm_dokter,k.kelas,COUNT(rd.kd_dokter) total FROM rawat_inap_dr rd "
                        + "INNER JOIN jns_perawatan_inap ji on ji.kd_jenis_prw=rd.kd_jenis_prw and ji.nm_perawatan like '%visit%' "
                        + "INNER JOIN dokter d on d.kd_dokter=rd.kd_dokter "
                        + "INNER JOIN reg_periksa rp on rp.no_rawat=rd.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                        + "INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar "
                        + "INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj "
                        + "WHERE rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and ji.nm_perawatan like '%libur%' and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') and rd.kd_dokter not in ('-','--') "
                        + "and pj.png_jawab like ? GROUP BY rd.kd_dokter, k.kelas ORDER BY d.nm_dokter, k.kelas) as a group by a.nm_dokter");
            } else {
                psrekap3 = koneksi.prepareStatement("select a.nm_dokter, "
                        + "CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT) Kelas_1, "
                        + "CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT) Kelas_2, "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT) Kelas_3, "
                        + "CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT) Kelas_vip, "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT) Kelas_vvip, "
                        + "CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end),INT) rwt_khusus, "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT) intensif, "
                        + "CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT) high_care, "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT) isolasi, "
                        + "CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end)+ "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT),INT) total_visit "
                        + "from (SELECT d.nm_dokter,k.kelas,COUNT(rd.kd_dokter) total FROM rawat_inap_dr rd "
                        + "INNER JOIN jns_perawatan_inap ji on ji.kd_jenis_prw=rd.kd_jenis_prw and ji.nm_perawatan like '%visit%' "
                        + "INNER JOIN dokter d on d.kd_dokter=rd.kd_dokter "
                        + "INNER JOIN reg_periksa rp on rp.no_rawat=rd.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                        + "INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar "
                        + "INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj "
                        + "WHERE rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and ji.nm_perawatan like '%libur%' and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') and rd.kd_dokter not in ('-','--') "
                        + "and " + ruangDipilih + " and pj.png_jawab like ? GROUP BY rd.kd_dokter, k.kelas ORDER BY d.nm_dokter, k.kelas) as a group by a.nm_dokter");
            }

            try {
                psrekap3.setString(1, "%" + nmpenjab.getText().trim() + "%");
                rsrekap3 = psrekap3.executeQuery();
                x = 1;
                while (rsrekap3.next()) {
                    tabMode5.addRow(new Object[]{
                        x + ".",
                        rsrekap3.getString("nm_dokter"),
                        rsrekap3.getString("Kelas_1"),
                        rsrekap3.getString("Kelas_2"),
                        rsrekap3.getString("Kelas_3"),
                        rsrekap3.getString("Kelas_vip"),
                        rsrekap3.getString("Kelas_vvip"),
                        rsrekap3.getString("rwt_khusus"),
                        rsrekap3.getString("intensif"),
                        rsrekap3.getString("high_care"),
                        rsrekap3.getString("isolasi"),
                        rsrekap3.getString("total_visit")
                    });
                    x++;
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println("simrskhanza.DlgFeeVisitDokter.rekapVisitLibur1() : " + e);
            } finally {
                if (rsrekap3 != null) {
                    rsrekap3.close();
                }
                if (psrekap3 != null) {
                    psrekap3.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("-");
    }
    
    private void rekapVisit1(){
        Valid.tabelKosong(tabMode3);    
        try {
            if (cmbRuangan.getSelectedItem().toString().equals("SEMUA RUANG")) {
                psrekap1 = koneksi.prepareStatement("select a.nm_dokter, "
                        + "CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT) Kelas_1, "
                        + "CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT) Kelas_2, "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT) Kelas_3, "
                        + "CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT) Kelas_vip, "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT) Kelas_vvip, "
                        + "CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end),INT) rwt_khusus, "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT) intensif, "
                        + "CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT) high_care, "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT) isolasi, "
                        + "CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end)+ "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT),INT) total_visit "
                        + "from (SELECT d.nm_dokter,k.kelas,COUNT(rd.kd_dokter_mewakili) total FROM rawat_inap_dr rd "
                        + "INNER JOIN jns_perawatan_inap ji on ji.kd_jenis_prw=rd.kd_jenis_prw INNER JOIN dokter d on d.kd_dokter=rd.kd_dokter_mewakili "
                        + "INNER JOIN reg_periksa rp on rp.no_rawat=rd.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                        + "INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar "
                        + "INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj "
                        + "WHERE rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and ji.nm_perawatan like '%visit%' and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') and rd.kd_dokter_mewakili not in ('-','--') "
                        + "and pj.png_jawab like ? GROUP BY rd.kd_dokter_mewakili, k.kelas ORDER BY d.nm_dokter, k.kelas) as a group by a.nm_dokter");
            } else {
                psrekap1 = koneksi.prepareStatement("select a.nm_dokter, "
                        + "CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT) Kelas_1, "
                        + "CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT) Kelas_2, "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT) Kelas_3, "
                        + "CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT) Kelas_vip, "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT) Kelas_vvip, "
                        + "CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end),INT) rwt_khusus, "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT) intensif, "
                        + "CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT) high_care, "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT) isolasi, "
                        + "CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end)+ "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT),INT) total_visit "
                        + "from (SELECT d.nm_dokter,k.kelas,COUNT(rd.kd_dokter_mewakili) total FROM rawat_inap_dr rd "
                        + "INNER JOIN jns_perawatan_inap ji on ji.kd_jenis_prw=rd.kd_jenis_prw INNER JOIN dokter d on d.kd_dokter=rd.kd_dokter_mewakili "
                        + "INNER JOIN reg_periksa rp on rp.no_rawat=rd.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                        + "INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar "
                        + "INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj "
                        + "WHERE rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and ji.nm_perawatan like '%visit%' and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') and rd.kd_dokter_mewakili not in ('-','--') "
                        + "and " + ruangDipilih + " and pj.png_jawab like ? GROUP BY rd.kd_dokter_mewakili, k.kelas ORDER BY d.nm_dokter, k.kelas) as a group by a.nm_dokter");
            }

            try {
                psrekap1.setString(1, "%" + nmpenjab.getText().trim() + "%");
                rsrekap1 = psrekap1.executeQuery();
                x = 1;
                while (rsrekap1.next()) {
                    tabMode3.addRow(new Object[]{
                        x + ".",
                        rsrekap1.getString("nm_dokter"),
                        rsrekap1.getString("Kelas_1"),
                        rsrekap1.getString("Kelas_2"),
                        rsrekap1.getString("Kelas_3"),
                        rsrekap1.getString("Kelas_vip"),
                        rsrekap1.getString("Kelas_vvip"),
                        rsrekap1.getString("rwt_khusus"),
                        rsrekap1.getString("intensif"),
                        rsrekap1.getString("high_care"),
                        rsrekap1.getString("isolasi"),
                        rsrekap1.getString("total_visit")
                    });
                    x++;
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println("simrskhanza.DlgFeeVisitDokter.rekapVisit1() : " + e);
            } finally {
                if (rsrekap1 != null) {
                    rsrekap1.close();
                }
                if (psrekap1 != null) {
                    psrekap1.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("-");
    }
    
    private void rekapVisitNormal2(){
        Valid.tabelKosong(tabMode6);    
        try {
            if (cmbRuangan.getSelectedItem().toString().equals("SEMUA RUANG")) {
                psrekap4 = koneksi.prepareStatement("select a.nm_dokter, "
                        + "CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT) Kelas_1, "
                        + "CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT) Kelas_2, "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT) Kelas_3, "
                        + "CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT) Kelas_vip, "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT) Kelas_vvip, "
                        + "CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end),INT) rwt_khusus, "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT) intensif, "
                        + "CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT) high_care, "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT) isolasi, "
                        + "CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end)+ "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT),INT) total_visit "
                        + "from (SELECT d.nm_dokter,k.kelas,COUNT(rd.kd_dokter_mewakili) total FROM rawat_inap_dr rd "
                        + "INNER JOIN jns_perawatan_inap ji on ji.kd_jenis_prw=rd.kd_jenis_prw and ji.nm_perawatan like '%visit%' "
                        + "INNER JOIN dokter d on d.kd_dokter=rd.kd_dokter_mewakili "
                        + "INNER JOIN reg_periksa rp on rp.no_rawat=rd.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                        + "INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar "
                        + "INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj "
                        + "WHERE rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and ji.nm_perawatan not like '%libur%' and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') and rd.kd_dokter_mewakili not in ('-','--') "
                        + "and pj.png_jawab like ? GROUP BY rd.kd_dokter_mewakili, k.kelas ORDER BY d.nm_dokter, k.kelas) as a group by a.nm_dokter");
            } else {
                psrekap4 = koneksi.prepareStatement("select a.nm_dokter, "
                        + "CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT) Kelas_1, "
                        + "CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT) Kelas_2, "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT) Kelas_3, "
                        + "CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT) Kelas_vip, "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT) Kelas_vvip, "
                        + "CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end),INT) rwt_khusus, "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT) intensif, "
                        + "CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT) high_care, "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT) isolasi, "
                        + "CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end)+ "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT),INT) total_visit "
                        + "from (SELECT d.nm_dokter,k.kelas,COUNT(rd.kd_dokter_mewakili) total FROM rawat_inap_dr rd "
                        + "INNER JOIN jns_perawatan_inap ji on ji.kd_jenis_prw=rd.kd_jenis_prw and ji.nm_perawatan like '%visit%' "
                        + "INNER JOIN dokter d on d.kd_dokter=rd.kd_dokter_mewakili "
                        + "INNER JOIN reg_periksa rp on rp.no_rawat=rd.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                        + "INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar "
                        + "INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj "
                        + "WHERE rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and ji.nm_perawatan not like '%libur%' and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') and rd.kd_dokter_mewakili not in ('-','--') "
                        + "and " + ruangDipilih + " and pj.png_jawab like ? GROUP BY rd.kd_dokter_mewakili, k.kelas ORDER BY d.nm_dokter, k.kelas) as a group by a.nm_dokter");
            }

            try {
                psrekap4.setString(1, "%" + nmpenjab.getText().trim() + "%");
                rsrekap4 = psrekap4.executeQuery();
                x = 1;
                while (rsrekap4.next()) {
                    tabMode6.addRow(new Object[]{
                        x + ".",
                        rsrekap4.getString("nm_dokter"),
                        rsrekap4.getString("Kelas_1"),
                        rsrekap4.getString("Kelas_2"),
                        rsrekap4.getString("Kelas_3"),
                        rsrekap4.getString("Kelas_vip"),
                        rsrekap4.getString("Kelas_vvip"),
                        rsrekap4.getString("rwt_khusus"),
                        rsrekap4.getString("intensif"),
                        rsrekap4.getString("high_care"),
                        rsrekap4.getString("isolasi"),
                        rsrekap4.getString("total_visit")
                    });
                    x++;
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println("simrskhanza.DlgFeeVisitDokter.rekapVisitNormal2() : " + e);
            } finally {
                if (rsrekap4 != null) {
                    rsrekap4.close();
                }
                if (psrekap4 != null) {
                    psrekap4.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("-");
    }
    
    private void rekapVisitLibur2(){
        Valid.tabelKosong(tabMode7);    
        try {
            if (cmbRuangan.getSelectedItem().toString().equals("SEMUA RUANG")) {
                psrekap5 = koneksi.prepareStatement("select a.nm_dokter, "
                        + "CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT) Kelas_1, "
                        + "CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT) Kelas_2, "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT) Kelas_3, "
                        + "CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT) Kelas_vip, "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT) Kelas_vvip, "
                        + "CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end),INT) rwt_khusus, "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT) intensif, "
                        + "CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT) high_care, "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT) isolasi, "
                        + "CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end)+ "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT),INT) total_visit "
                        + "from (SELECT d.nm_dokter,k.kelas,COUNT(rd.kd_dokter_mewakili) total FROM rawat_inap_dr rd "
                        + "INNER JOIN jns_perawatan_inap ji on ji.kd_jenis_prw=rd.kd_jenis_prw and ji.nm_perawatan like '%visit%' "
                        + "INNER JOIN dokter d on d.kd_dokter=rd.kd_dokter_mewakili "
                        + "INNER JOIN reg_periksa rp on rp.no_rawat=rd.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                        + "INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar "
                        + "INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj "
                        + "WHERE rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and ji.nm_perawatan like '%libur%' and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') and rd.kd_dokter_mewakili not in ('-','--') "
                        + "and pj.png_jawab like ? GROUP BY rd.kd_dokter_mewakili, k.kelas ORDER BY d.nm_dokter, k.kelas) as a group by a.nm_dokter");
            } else {
                psrekap5 = koneksi.prepareStatement("select a.nm_dokter, "
                        + "CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT) Kelas_1, "
                        + "CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT) Kelas_2, "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT) Kelas_3, "
                        + "CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT) Kelas_vip, "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT) Kelas_vvip, "
                        + "CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end),INT) rwt_khusus, "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT) intensif, "
                        + "CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT) high_care, "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT) isolasi, "
                        + "CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end)+ "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT),INT) total_visit "
                        + "from (SELECT d.nm_dokter,k.kelas,COUNT(rd.kd_dokter_mewakili) total FROM rawat_inap_dr rd "
                        + "INNER JOIN jns_perawatan_inap ji on ji.kd_jenis_prw=rd.kd_jenis_prw and ji.nm_perawatan like '%visit%' "
                        + "INNER JOIN dokter d on d.kd_dokter=rd.kd_dokter_mewakili "
                        + "INNER JOIN reg_periksa rp on rp.no_rawat=rd.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                        + "INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar "
                        + "INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj "
                        + "WHERE rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and ji.nm_perawatan like '%libur%' and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') and rd.kd_dokter_mewakili not in ('-','--') "
                        + "and " + ruangDipilih + " and pj.png_jawab like ? GROUP BY rd.kd_dokter_mewakili, k.kelas ORDER BY d.nm_dokter, k.kelas) as a group by a.nm_dokter");
            }

            try {
                psrekap5.setString(1, "%" + nmpenjab.getText().trim() + "%");
                rsrekap5 = psrekap5.executeQuery();
                x = 1;
                while (rsrekap5.next()) {
                    tabMode7.addRow(new Object[]{
                        x + ".",
                        rsrekap5.getString("nm_dokter"),
                        rsrekap5.getString("Kelas_1"),
                        rsrekap5.getString("Kelas_2"),
                        rsrekap5.getString("Kelas_3"),
                        rsrekap5.getString("Kelas_vip"),
                        rsrekap5.getString("Kelas_vvip"),
                        rsrekap5.getString("rwt_khusus"),
                        rsrekap5.getString("intensif"),
                        rsrekap5.getString("high_care"),
                        rsrekap5.getString("isolasi"),
                        rsrekap5.getString("total_visit")
                    });
                    x++;
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println("simrskhanza.DlgFeeVisitDokter.rekapVisitLibur2() : " + e);
            } finally {
                if (rsrekap5 != null) {
                    rsrekap5.close();
                }
                if (psrekap5 != null) {
                    psrekap5.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("-");
    }
    
    public void empttext() {
        kddokter.setText("");
        nmdokter.setText("");
        kdpenjab.setText("");
        nmpenjab.setText("");
        cmbRuangan.setSelectedIndex(0);
        cmbPrin.setSelectedIndex(0);
        ruangDipilih = "";
    }
    
    private void prinDetailVisitMenangani() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("periode", "Periode Tgl. " + Tgl1.getSelectedItem() + " S.D " + Tgl2.getSelectedItem()+" Untuk Dokter Yang Menangani");
       
        if (cmbPrin.getSelectedIndex() == 1) {
            param.put("judul", "Detail Visite Semua Hari Perawatan Dokter Yang Menangani");
            if (cmbRuangan.getSelectedItem().toString().equals("SEMUA RUANG")) {
                param.put("ruangan", "SEMUA RUANG PERAWATAN");
            } else {
                param.put("ruangan", "RUANG PERAWATAN " + cmbRuangan.getSelectedItem());
            }

            Valid.MyReport("rptDetailVisitInap.jasper", "report", "::[ Laporan Rincian Detail Visite Semua Hari Perawatan Dokter Rawat Inap ]::",
                    "SELECT date_format(rd.tgl_perawatan,'%d-%m-%Y') tgl_visit, ji.nm_perawatan, d.nm_dokter, "
                    + "rp.no_rkm_medis no_rm, p.nm_pasien, b.nm_bangsal, pj.png_jawab cr_byr FROM rawat_inap_dr rd "
                    + "INNER JOIN jns_perawatan_inap ji on ji.kd_jenis_prw=rd.kd_jenis_prw INNER JOIN dokter d on d.kd_dokter=rd.kd_dokter "
                    + "INNER JOIN reg_periksa rp on rp.no_rawat=rd.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar "
                    + "INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj WHERE "
                    + "rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                    + "and ji.nm_perawatan like '%visit%' and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') and rd.kd_dokter not in ('-','--') and "
                    + "pj.png_jawab like '%" + nmpenjab.getText() + "%' and rd.kd_dokter like '%" + kddokter.getText() + "%' "
                    + "ORDER BY d.nm_dokter, rd.tgl_perawatan", param);
            
        } else if (cmbPrin.getSelectedIndex() == 8) {
            param.put("judul", "Detail Visite Hari Normal Dokter Yang Menangani");
            if (cmbRuangan.getSelectedItem().toString().equals("SEMUA RUANG")) {
                param.put("ruangan", "SEMUA RUANG PERAWATAN");
                Valid.MyReport("rptDetailVisitInap.jasper", "report", "::[ Laporan Rincian Detail Visite Hari Normal Dokter Rawat Inap ]::",
                        "SELECT date_format(rd.tgl_perawatan,'%d-%m-%Y') tgl_visit, ji.nm_perawatan, d.nm_dokter, "
                        + "rp.no_rkm_medis no_rm, p.nm_pasien, b.nm_bangsal, pj.png_jawab cr_byr FROM rawat_inap_dr rd "
                        + "INNER JOIN jns_perawatan_inap ji on ji.kd_jenis_prw=rd.kd_jenis_prw and ji.nm_perawatan like '%visit%' "
                        + "INNER JOIN dokter d on d.kd_dokter=rd.kd_dokter INNER JOIN reg_periksa rp on rp.no_rawat=rd.no_rawat "
                        + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat "
                        + "INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj WHERE "
                        + "rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and ji.nm_perawatan not like '%libur%' and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') and rd.kd_dokter not in ('-','--') and "
                        + "pj.png_jawab like '%" + nmpenjab.getText() + "%' and rd.kd_dokter like '%" + kddokter.getText() + "%' "
                        + "ORDER BY d.nm_dokter, rd.tgl_perawatan", param);
            } else {
                param.put("ruangan", "RUANG PERAWATAN " + cmbRuangan.getSelectedItem());
                Valid.MyReport("rptDetailVisitInap.jasper", "report", "::[ Laporan Rincian Detail Visite Hari Normal Dokter Rawat Inap ]::",
                        "SELECT date_format(rd.tgl_perawatan,'%d-%m-%Y') tgl_visit, ji.nm_perawatan, d.nm_dokter, "
                        + "rp.no_rkm_medis no_rm, p.nm_pasien, b.nm_bangsal, pj.png_jawab cr_byr FROM rawat_inap_dr rd "
                        + "INNER JOIN jns_perawatan_inap ji on ji.kd_jenis_prw=rd.kd_jenis_prw and ji.nm_perawatan like '%visit%' "
                        + "INNER JOIN dokter d on d.kd_dokter=rd.kd_dokter INNER JOIN reg_periksa rp on rp.no_rawat=rd.no_rawat "
                        + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat "
                        + "INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj WHERE "
                        + "rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and ji.nm_perawatan not like '%libur%' and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') "
                        + "and rd.kd_dokter not in ('-','--') and " + ruangDipilih + " and pj.png_jawab like '%" + nmpenjab.getText() + "%' and "
                        + "rd.kd_dokter like '%" + kddokter.getText() + "%' ORDER BY d.nm_dokter, rd.tgl_perawatan", param);
            } 
        } else if (cmbPrin.getSelectedIndex() == 12) {
            param.put("judul", "Detail Visite Hari Libur Dokter Yang Menangani");
            if (cmbRuangan.getSelectedItem().toString().equals("SEMUA RUANG")) {
                param.put("ruangan", "SEMUA RUANG PERAWATAN");
                Valid.MyReport("rptDetailVisitInap.jasper", "report", "::[ Laporan Rincian Detail Visite Hari Libur Dokter Rawat Inap ]::",
                        "SELECT date_format(rd.tgl_perawatan,'%d-%m-%Y') tgl_visit, ji.nm_perawatan, d.nm_dokter, "
                        + "rp.no_rkm_medis no_rm, p.nm_pasien, b.nm_bangsal, pj.png_jawab cr_byr FROM rawat_inap_dr rd "
                        + "INNER JOIN jns_perawatan_inap ji on ji.kd_jenis_prw=rd.kd_jenis_prw and ji.nm_perawatan like '%visit%' "
                        + "INNER JOIN dokter d on d.kd_dokter=rd.kd_dokter INNER JOIN reg_periksa rp on rp.no_rawat=rd.no_rawat "
                        + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat "
                        + "INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj WHERE "
                        + "rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and ji.nm_perawatan like '%libur%' and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') and rd.kd_dokter not in ('-','--') and "
                        + "pj.png_jawab like '%" + nmpenjab.getText() + "%' and rd.kd_dokter like '%" + kddokter.getText() + "%' "
                        + "ORDER BY d.nm_dokter, rd.tgl_perawatan", param);
            } else {
                param.put("ruangan", "RUANG PERAWATAN " + cmbRuangan.getSelectedItem());
                Valid.MyReport("rptDetailVisitInap.jasper", "report", "::[ Laporan Rincian Detail Visite Hari Libur Dokter Rawat Inap ]::",
                        "SELECT date_format(rd.tgl_perawatan,'%d-%m-%Y') tgl_visit, ji.nm_perawatan, d.nm_dokter, "
                        + "rp.no_rkm_medis no_rm, p.nm_pasien, b.nm_bangsal, pj.png_jawab cr_byr FROM rawat_inap_dr rd "
                        + "INNER JOIN jns_perawatan_inap ji on ji.kd_jenis_prw=rd.kd_jenis_prw and ji.nm_perawatan like '%visit%' "
                        + "INNER JOIN dokter d on d.kd_dokter=rd.kd_dokter INNER JOIN reg_periksa rp on rp.no_rawat=rd.no_rawat "
                        + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat "
                        + "INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj WHERE "
                        + "rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and ji.nm_perawatan like '%libur%' and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') "
                        + "and rd.kd_dokter not in ('-','--') and " + ruangDipilih + " and pj.png_jawab like '%" + nmpenjab.getText() + "%' and "
                        + "rd.kd_dokter like '%" + kddokter.getText() + "%' ORDER BY d.nm_dokter, rd.tgl_perawatan", param);
            } 
        }

        this.setCursor(Cursor.getDefaultCursor());
        prosesCari();
    }
    
    private void prinDetailVisitMewakili() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("periode", "Periode Tgl. " + Tgl1.getSelectedItem() + " S.D " + Tgl2.getSelectedItem()+" Untuk Dokter Umum/Yang Mewakili");
        
        if (cmbPrin.getSelectedIndex() == 2) {
            param.put("judul", "Detail Visite Semua Hari Perawatan Dokter Umum/Yang Mewakili");
            if (cmbRuangan.getSelectedItem().toString().equals("SEMUA RUANG")) {
                param.put("ruangan", "SEMUA RUANG PERAWATAN");
                Valid.MyReport("rptDetailVisitInap1.jasper", "report", "::[ Laporan Rincian Detail Visite Semua Hari Perawatan Dokter Rawat Inap ]::",
                        "SELECT date_format(rd.tgl_perawatan,'%d-%m-%Y') tgl_visit, ji.nm_perawatan, d.nm_dokter, "
                        + "rp.no_rkm_medis no_rm, p.nm_pasien, b.nm_bangsal, pj.png_jawab cr_byr FROM rawat_inap_dr rd "
                        + "INNER JOIN jns_perawatan_inap ji on ji.kd_jenis_prw=rd.kd_jenis_prw INNER JOIN dokter d on d.kd_dokter=rd.kd_dokter_mewakili "
                        + "INNER JOIN reg_periksa rp on rp.no_rawat=rd.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                        + "INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar "
                        + "INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj WHERE "
                        + "rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and ji.nm_perawatan like '%visit%' and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') and rd.kd_dokter_mewakili not in ('-','--') and "
                        + "pj.png_jawab like '%" + nmpenjab.getText() + "%' and rd.kd_dokter_mewakili like '%" + kddokter.getText() + "%' "
                        + "ORDER BY d.nm_dokter, rd.tgl_perawatan", param);
            } else {
                param.put("ruangan", "RUANG PERAWATAN " + cmbRuangan.getSelectedItem());
                Valid.MyReport("rptDetailVisitInap1.jasper", "report", "::[ Laporan Rincian Detail Visite Semua Hari Perawatan Dokter Rawat Inap ]::",
                        "SELECT date_format(rd.tgl_perawatan,'%d-%m-%Y') tgl_visit, ji.nm_perawatan, d.nm_dokter, "
                        + "rp.no_rkm_medis no_rm, p.nm_pasien, b.nm_bangsal, pj.png_jawab cr_byr FROM rawat_inap_dr rd "
                        + "INNER JOIN jns_perawatan_inap ji on ji.kd_jenis_prw=rd.kd_jenis_prw INNER JOIN dokter d on d.kd_dokter=rd.kd_dokter_mewakili "
                        + "INNER JOIN reg_periksa rp on rp.no_rawat=rd.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                        + "INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar "
                        + "INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj WHERE "
                        + "rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and ji.nm_perawatan like '%visit%' and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') and "
                        + "rd.kd_dokter_mewakili not in ('-','--') and " + ruangDipilih + " and pj.png_jawab like '%" + nmpenjab.getText() + "%' "
                        + "and rd.kd_dokter_mewakili like '%" + kddokter.getText() + "%' ORDER BY d.nm_dokter, rd.tgl_perawatan", param);
            }
        } else if (cmbPrin.getSelectedIndex() == 9) {
            param.put("judul", "Detail Visite Hari Normal Dokter Umum/Yang Mewakili");
            if (cmbRuangan.getSelectedItem().toString().equals("SEMUA RUANG")) {
                param.put("ruangan", "SEMUA RUANG PERAWATAN");
                Valid.MyReport("rptDetailVisitInap1.jasper", "report", "::[ Laporan Rincian Detail Visite Hari Normal Dokter Rawat Inap ]::",
                        "SELECT date_format(rd.tgl_perawatan,'%d-%m-%Y') tgl_visit, ji.nm_perawatan, d.nm_dokter, "
                        + "rp.no_rkm_medis no_rm, p.nm_pasien, b.nm_bangsal, pj.png_jawab cr_byr FROM rawat_inap_dr rd "
                        + "INNER JOIN jns_perawatan_inap ji on ji.kd_jenis_prw=rd.kd_jenis_prw and ji.nm_perawatan like '%visit%' "
                        + "INNER JOIN dokter d on d.kd_dokter=rd.kd_dokter_mewakili INNER JOIN reg_periksa rp on rp.no_rawat=rd.no_rawat "
                        + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat "
                        + "INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj WHERE "
                        + "rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and ji.nm_perawatan not like '%libur%' and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') and rd.kd_dokter_mewakili not in ('-','--') and "
                        + "pj.png_jawab like '%" + nmpenjab.getText() + "%' and rd.kd_dokter_mewakili like '%" + kddokter.getText() + "%' "
                        + "ORDER BY d.nm_dokter, rd.tgl_perawatan", param);
            } else {
                param.put("ruangan", "RUANG PERAWATAN " + cmbRuangan.getSelectedItem());
                Valid.MyReport("rptDetailVisitInap1.jasper", "report", "::[ Laporan Rincian Detail Visite Hari Normal Dokter Rawat Inap ]::",
                        "SELECT date_format(rd.tgl_perawatan,'%d-%m-%Y') tgl_visit, ji.nm_perawatan, d.nm_dokter, "
                        + "rp.no_rkm_medis no_rm, p.nm_pasien, b.nm_bangsal, pj.png_jawab cr_byr FROM rawat_inap_dr rd "
                        + "INNER JOIN jns_perawatan_inap ji on ji.kd_jenis_prw=rd.kd_jenis_prw and ji.nm_perawatan like '%visit%' "
                        + "INNER JOIN dokter d on d.kd_dokter=rd.kd_dokter_mewakili INNER JOIN reg_periksa rp on rp.no_rawat=rd.no_rawat "
                        + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat "
                        + "INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj WHERE "
                        + "rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and ji.nm_perawatan not like '%libur%' and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') and "
                        + "rd.kd_dokter_mewakili not in ('-','--') and " + ruangDipilih + " and pj.png_jawab like '%" + nmpenjab.getText() + "%' "
                        + "and rd.kd_dokter_mewakili like '%" + kddokter.getText() + "%' ORDER BY d.nm_dokter, rd.tgl_perawatan", param);
            }
        } else if (cmbPrin.getSelectedIndex() == 13) {
            param.put("judul", "Detail Visite Hari Libur Dokter Umum/Yang Mewakili");
            if (cmbRuangan.getSelectedItem().toString().equals("SEMUA RUANG")) {
                param.put("ruangan", "SEMUA RUANG PERAWATAN");
                Valid.MyReport("rptDetailVisitInap1.jasper", "report", "::[ Laporan Rincian Detail Visite Hari Libur Dokter Rawat Inap ]::",
                        "SELECT date_format(rd.tgl_perawatan,'%d-%m-%Y') tgl_visit, ji.nm_perawatan, d.nm_dokter, "
                        + "rp.no_rkm_medis no_rm, p.nm_pasien, b.nm_bangsal, pj.png_jawab cr_byr FROM rawat_inap_dr rd "
                        + "INNER JOIN jns_perawatan_inap ji on ji.kd_jenis_prw=rd.kd_jenis_prw and ji.nm_perawatan like '%visit%' "
                        + "INNER JOIN dokter d on d.kd_dokter=rd.kd_dokter_mewakili INNER JOIN reg_periksa rp on rp.no_rawat=rd.no_rawat "
                        + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat "
                        + "INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj WHERE "
                        + "rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and ji.nm_perawatan like '%libur%' and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') and rd.kd_dokter_mewakili not in ('-','--') and "
                        + "pj.png_jawab like '%" + nmpenjab.getText() + "%' and rd.kd_dokter_mewakili like '%" + kddokter.getText() + "%' "
                        + "ORDER BY d.nm_dokter, rd.tgl_perawatan", param);
            } else {
                param.put("ruangan", "RUANG PERAWATAN " + cmbRuangan.getSelectedItem());
                Valid.MyReport("rptDetailVisitInap1.jasper", "report", "::[ Laporan Rincian Detail Visite Hari Libur Dokter Rawat Inap ]::",
                        "SELECT date_format(rd.tgl_perawatan,'%d-%m-%Y') tgl_visit, ji.nm_perawatan, d.nm_dokter, "
                        + "rp.no_rkm_medis no_rm, p.nm_pasien, b.nm_bangsal, pj.png_jawab cr_byr FROM rawat_inap_dr rd "
                        + "INNER JOIN jns_perawatan_inap ji on ji.kd_jenis_prw=rd.kd_jenis_prw and ji.nm_perawatan like '%visit%' "
                        + "INNER JOIN dokter d on d.kd_dokter=rd.kd_dokter_mewakili INNER JOIN reg_periksa rp on rp.no_rawat=rd.no_rawat "
                        + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat "
                        + "INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj WHERE "
                        + "rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and ji.nm_perawatan like '%libur%' and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') and "
                        + "rd.kd_dokter_mewakili not in ('-','--') and " + ruangDipilih + " and pj.png_jawab like '%" + nmpenjab.getText() + "%' "
                        + "and rd.kd_dokter_mewakili like '%" + kddokter.getText() + "%' ORDER BY d.nm_dokter, rd.tgl_perawatan", param);
            }
        }

        this.setCursor(Cursor.getDefaultCursor());
        prosesCari1();
    }
    
    private void prinRekapDokterMenangani() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("periode", "Periode Tgl. " + Tgl1.getSelectedItem() + " S.D " + Tgl2.getSelectedItem());

        if (cmbPrin.getSelectedIndex() == 3) {
            param.put("judul", "Rekap Visite Semua Hari Perawatan Dokter Yang Menangani");
            if (kdpenjab.getText().equals("") && (cmbRuangan.getSelectedItem().toString().equals("SEMUA RUANG"))) {
                param.put("cara_byr", "SEMUA JENIS BAYAR UNTUK DOKTER YANG MENANGANI");
                param.put("ruangan", "UNTUK " + cmbRuangan.getSelectedItem().toString() + " PERAWATAN");
                Valid.MyReport("rptRekapVisiteInap.jasper", "report", "::[ Laporan Rekap Detail Visite Semua Hari Perawatan Dokter Rawat Inap ]::",
                        "select a.nm_dokter, CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT) Kelas_1, "
                        + "CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT) Kelas_2, "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT) Kelas_3, "
                        + "CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT) Kelas_vip, "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT) Kelas_vvip, "
                        + "CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end),INT) rwt_khusus, "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT) intensif, "
                        + "CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT) high_care, "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT) isolasi, "
                        + "CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end)+ "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT),INT) total_visit "
                        + "from (SELECT d.nm_dokter,k.kelas,COUNT(rd.kd_dokter) total FROM rawat_inap_dr rd INNER JOIN jns_perawatan_inap ji on ji.kd_jenis_prw=rd.kd_jenis_prw "
                        + "INNER JOIN dokter d on d.kd_dokter=rd.kd_dokter INNER JOIN reg_periksa rp on rp.no_rawat=rd.no_rawat "
                        + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat "
                        + "INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj WHERE "
                        + "rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and ji.nm_perawatan like '%visit%' and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') and rd.kd_dokter not in ('-','--') "
                        + "and pj.png_jawab like '%" + nmpenjab.getText() + "%' GROUP BY rd.kd_dokter, k.kelas ORDER BY d.nm_dokter, k.kelas) as a group by a.nm_dokter", param);
            } else if (!kdpenjab.getText().equals("") && (!cmbRuangan.getSelectedItem().toString().equals("SEMUA RUANG"))) {
                param.put("cara_byr", "UNTUK JENIS BAYAR PASIEN " + nmpenjab.getText() + " KHUSUS DOKTER YANG MENANGANI");
                param.put("ruangan", "UNTUK RUANG PERAWATAN " + cmbRuangan.getSelectedItem());
                Valid.MyReport("rptRekapVisiteInap.jasper", "report", "::[ Laporan Rekap Detail Visite Semua Hari Perawatan Dokter Rawat Inap ]::",
                        "select a.nm_dokter, CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT) Kelas_1, "
                        + "CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT) Kelas_2, "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT) Kelas_3, "
                        + "CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT) Kelas_vip, "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT) Kelas_vvip, "
                        + "CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end),INT) rwt_khusus, "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT) intensif, "
                        + "CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT) high_care, "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT) isolasi, "
                        + "CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end)+ "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT),INT) total_visit "
                        + "from (SELECT d.nm_dokter,k.kelas,COUNT(rd.kd_dokter) total FROM rawat_inap_dr rd INNER JOIN jns_perawatan_inap ji on ji.kd_jenis_prw=rd.kd_jenis_prw "
                        + "INNER JOIN dokter d on d.kd_dokter=rd.kd_dokter INNER JOIN reg_periksa rp on rp.no_rawat=rd.no_rawat "
                        + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat "
                        + "INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj WHERE "
                        + "rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and " + ruangDipilih + " and ji.nm_perawatan like '%visit%' and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') "
                        + "and rd.kd_dokter not in ('-','--') and pj.png_jawab like '%" + nmpenjab.getText() + "%' GROUP BY rd.kd_dokter, k.kelas ORDER BY d.nm_dokter, k.kelas) as a group by a.nm_dokter", param);
            } else if (!kdpenjab.getText().equals("") && (cmbRuangan.getSelectedItem().toString().equals("SEMUA RUANG"))) {
                param.put("cara_byr", "UNTUK JENIS BAYAR PASIEN " + nmpenjab.getText() + " KHUSUS DOKTER YANG MENANGANI");
                param.put("ruangan", "UNTUK " + cmbRuangan.getSelectedItem().toString() + " PERAWATAN");
                Valid.MyReport("rptRekapVisiteInap.jasper", "report", "::[ Laporan Rekap Detail Visite Semua Hari Perawatan Dokter Rawat Inap ]::",
                        "select a.nm_dokter, CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT) Kelas_1, "
                        + "CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT) Kelas_2, "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT) Kelas_3, "
                        + "CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT) Kelas_vip, "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT) Kelas_vvip, "
                        + "CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end),INT) rwt_khusus, "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT) intensif, "
                        + "CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT) high_care, "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT) isolasi, "
                        + "CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end)+ "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT),INT) total_visit "
                        + "from (SELECT d.nm_dokter,k.kelas,COUNT(rd.kd_dokter) total FROM rawat_inap_dr rd INNER JOIN jns_perawatan_inap ji on ji.kd_jenis_prw=rd.kd_jenis_prw "
                        + "INNER JOIN dokter d on d.kd_dokter=rd.kd_dokter INNER JOIN reg_periksa rp on rp.no_rawat=rd.no_rawat "
                        + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat "
                        + "INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj WHERE "
                        + "rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and ji.nm_perawatan like '%visit%' and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') and rd.kd_dokter not in ('-','--') "
                        + "and pj.png_jawab like '%" + nmpenjab.getText() + "%' GROUP BY rd.kd_dokter, k.kelas ORDER BY d.nm_dokter, k.kelas) as a group by a.nm_dokter", param);
            } else if (kdpenjab.getText().equals("") && (!cmbRuangan.getSelectedItem().toString().equals("SEMUA RUANG"))) {
                param.put("cara_byr", "SEMUA JENIS BAYAR UNTUK DOKTER YANG MENANGANI");
                param.put("ruangan", "UNTUK RUANG PERAWATAN " + cmbRuangan.getSelectedItem());
                Valid.MyReport("rptRekapVisiteInap.jasper", "report", "::[ Laporan Rekap Detail Visite Semua Hari Perawatan Dokter Rawat Inap ]::",
                        "select a.nm_dokter, CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT) Kelas_1, "
                        + "CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT) Kelas_2, "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT) Kelas_3, "
                        + "CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT) Kelas_vip, "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT) Kelas_vvip, "
                        + "CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end),INT) rwt_khusus, "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT) intensif, "
                        + "CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT) high_care, "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT) isolasi, "
                        + "CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end)+ "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT),INT) total_visit "
                        + "from (SELECT d.nm_dokter,k.kelas,COUNT(rd.kd_dokter) total FROM rawat_inap_dr rd INNER JOIN jns_perawatan_inap ji on ji.kd_jenis_prw=rd.kd_jenis_prw "
                        + "INNER JOIN dokter d on d.kd_dokter=rd.kd_dokter INNER JOIN reg_periksa rp on rp.no_rawat=rd.no_rawat "
                        + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat "
                        + "INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj WHERE "
                        + "rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and " + ruangDipilih + " and ji.nm_perawatan like '%visit%' and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') "
                        + "and rd.kd_dokter not in ('-','--') and pj.png_jawab like '%" + nmpenjab.getText() + "%' GROUP BY rd.kd_dokter, k.kelas ORDER BY d.nm_dokter, k.kelas) as a group by a.nm_dokter", param);
            }
        } else if (cmbPrin.getSelectedIndex() == 10) {
            param.put("judul", "Rekap Visite Hari Normal Dokter Yang Menangani");
            if (kdpenjab.getText().equals("") && (cmbRuangan.getSelectedItem().toString().equals("SEMUA RUANG"))) {
                param.put("cara_byr", "SEMUA JENIS BAYAR UNTUK DOKTER YANG MENANGANI");
                param.put("ruangan", "UNTUK " + cmbRuangan.getSelectedItem().toString() + " PERAWATAN");
                Valid.MyReport("rptRekapVisiteInap.jasper", "report", "::[ Laporan Rekap Detail Visite Hari Normal Dokter Rawat Inap ]::",
                        "select a.nm_dokter, CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT) Kelas_1, "
                        + "CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT) Kelas_2, "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT) Kelas_3, "
                        + "CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT) Kelas_vip, "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT) Kelas_vvip, "
                        + "CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end),INT) rwt_khusus, "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT) intensif, "
                        + "CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT) high_care, "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT) isolasi, "
                        + "CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end)+ "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT),INT) total_visit "
                        + "from (SELECT d.nm_dokter,k.kelas,COUNT(rd.kd_dokter) total FROM rawat_inap_dr rd "
                        + "INNER JOIN jns_perawatan_inap ji on ji.kd_jenis_prw=rd.kd_jenis_prw and ji.nm_perawatan like '%visit%' "
                        + "INNER JOIN dokter d on d.kd_dokter=rd.kd_dokter INNER JOIN reg_periksa rp on rp.no_rawat=rd.no_rawat "
                        + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat "
                        + "INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj WHERE "
                        + "rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and ji.nm_perawatan not like '%libur%' and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') and rd.kd_dokter not in ('-','--') "
                        + "and pj.png_jawab like '%" + nmpenjab.getText() + "%' GROUP BY rd.kd_dokter, k.kelas ORDER BY d.nm_dokter, k.kelas) as a group by a.nm_dokter", param);
            } else if (!kdpenjab.getText().equals("") && (!cmbRuangan.getSelectedItem().toString().equals("SEMUA RUANG"))) {
                param.put("cara_byr", "UNTUK JENIS BAYAR PASIEN " + nmpenjab.getText() + " KHUSUS DOKTER YANG MENANGANI");
                param.put("ruangan", "UNTUK RUANG PERAWATAN " + cmbRuangan.getSelectedItem());
                Valid.MyReport("rptRekapVisiteInap.jasper", "report", "::[ Laporan Rekap Detail Visite Hari Normal Dokter Rawat Inap ]::",
                        "select a.nm_dokter, CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT) Kelas_1, "
                        + "CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT) Kelas_2, "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT) Kelas_3, "
                        + "CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT) Kelas_vip, "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT) Kelas_vvip, "
                        + "CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end),INT) rwt_khusus, "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT) intensif, "
                        + "CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT) high_care, "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT) isolasi, "
                        + "CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end)+ "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT),INT) total_visit "
                        + "from (SELECT d.nm_dokter,k.kelas,COUNT(rd.kd_dokter) total FROM rawat_inap_dr rd "
                        + "INNER JOIN jns_perawatan_inap ji on ji.kd_jenis_prw=rd.kd_jenis_prw and ji.nm_perawatan like '%visit%' "
                        + "INNER JOIN dokter d on d.kd_dokter=rd.kd_dokter INNER JOIN reg_periksa rp on rp.no_rawat=rd.no_rawat "
                        + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat "
                        + "INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj WHERE "
                        + "rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and " + ruangDipilih + " and ji.nm_perawatan not like '%libur%' and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') "
                        + "and rd.kd_dokter not in ('-','--') and pj.png_jawab like '%" + nmpenjab.getText() + "%' "
                        + "GROUP BY rd.kd_dokter, k.kelas ORDER BY d.nm_dokter, k.kelas) as a group by a.nm_dokter", param);
            } else if (!kdpenjab.getText().equals("") && (cmbRuangan.getSelectedItem().toString().equals("SEMUA RUANG"))) {
                param.put("cara_byr", "UNTUK JENIS BAYAR PASIEN " + nmpenjab.getText() + " KHUSUS DOKTER YANG MENANGANI");
                param.put("ruangan", "UNTUK " + cmbRuangan.getSelectedItem().toString() + " PERAWATAN");
                Valid.MyReport("rptRekapVisiteInap.jasper", "report", "::[ Laporan Rekap Detail Visite Hari Normal Dokter Rawat Inap ]::",
                        "select a.nm_dokter, CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT) Kelas_1, "
                        + "CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT) Kelas_2, "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT) Kelas_3, "
                        + "CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT) Kelas_vip, "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT) Kelas_vvip, "
                        + "CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end),INT) rwt_khusus, "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT) intensif, "
                        + "CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT) high_care, "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT) isolasi, "
                        + "CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end)+ "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT),INT) total_visit "
                        + "from (SELECT d.nm_dokter,k.kelas,COUNT(rd.kd_dokter) total FROM rawat_inap_dr rd "
                        + "INNER JOIN jns_perawatan_inap ji on ji.kd_jenis_prw=rd.kd_jenis_prw and ji.nm_perawatan like '%visit%' "
                        + "INNER JOIN dokter d on d.kd_dokter=rd.kd_dokter INNER JOIN reg_periksa rp on rp.no_rawat=rd.no_rawat "
                        + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat "
                        + "INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj WHERE "
                        + "rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and ji.nm_perawatan not like '%libur%' and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') and rd.kd_dokter not in ('-','--') "
                        + "and pj.png_jawab like '%" + nmpenjab.getText() + "%' GROUP BY rd.kd_dokter, k.kelas ORDER BY d.nm_dokter, k.kelas) as a group by a.nm_dokter", param);
            } else if (kdpenjab.getText().equals("") && (!cmbRuangan.getSelectedItem().toString().equals("SEMUA RUANG"))) {
                param.put("cara_byr", "SEMUA JENIS BAYAR UNTUK DOKTER YANG MENANGANI");
                param.put("ruangan", "UNTUK RUANG PERAWATAN " + cmbRuangan.getSelectedItem());
                Valid.MyReport("rptRekapVisiteInap.jasper", "report", "::[ Laporan Rekap Detail Visite Hari Normal Dokter Rawat Inap ]::",
                        "select a.nm_dokter, CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT) Kelas_1, "
                        + "CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT) Kelas_2, "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT) Kelas_3, "
                        + "CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT) Kelas_vip, "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT) Kelas_vvip, "
                        + "CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end),INT) rwt_khusus, "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT) intensif, "
                        + "CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT) high_care, "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT) isolasi, "
                        + "CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end)+ "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT),INT) total_visit "
                        + "from (SELECT d.nm_dokter,k.kelas,COUNT(rd.kd_dokter) total FROM rawat_inap_dr rd "
                        + "INNER JOIN jns_perawatan_inap ji on ji.kd_jenis_prw=rd.kd_jenis_prw and ji.nm_perawatan like '%visit%' "
                        + "INNER JOIN dokter d on d.kd_dokter=rd.kd_dokter INNER JOIN reg_periksa rp on rp.no_rawat=rd.no_rawat "
                        + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat "
                        + "INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj WHERE "
                        + "rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and " + ruangDipilih + " and ji.nm_perawatan not like '%libur%' and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') "
                        + "and rd.kd_dokter not in ('-','--') and pj.png_jawab like '%" + nmpenjab.getText() + "%' GROUP BY rd.kd_dokter, k.kelas ORDER BY d.nm_dokter, k.kelas) as a group by a.nm_dokter", param);
            }
        } else if (cmbPrin.getSelectedIndex() == 14) {
            param.put("judul", "Rekap Visite Hari Libur Dokter Yang Menangani");
            if (kdpenjab.getText().equals("") && (cmbRuangan.getSelectedItem().toString().equals("SEMUA RUANG"))) {
                param.put("cara_byr", "SEMUA JENIS BAYAR UNTUK DOKTER YANG MENANGANI");
                param.put("ruangan", "UNTUK " + cmbRuangan.getSelectedItem().toString() + " PERAWATAN");
                Valid.MyReport("rptRekapVisiteInap.jasper", "report", "::[ Laporan Rekap Detail Visite Hari Libur Dokter Rawat Inap ]::",
                        "select a.nm_dokter, CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT) Kelas_1, "
                        + "CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT) Kelas_2, "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT) Kelas_3, "
                        + "CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT) Kelas_vip, "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT) Kelas_vvip, "
                        + "CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end),INT) rwt_khusus, "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT) intensif, "
                        + "CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT) high_care, "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT) isolasi, "
                        + "CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end)+ "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT),INT) total_visit "
                        + "from (SELECT d.nm_dokter,k.kelas,COUNT(rd.kd_dokter) total FROM rawat_inap_dr rd "
                        + "INNER JOIN jns_perawatan_inap ji on ji.kd_jenis_prw=rd.kd_jenis_prw and ji.nm_perawatan like '%visit%' "
                        + "INNER JOIN dokter d on d.kd_dokter=rd.kd_dokter INNER JOIN reg_periksa rp on rp.no_rawat=rd.no_rawat "
                        + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat "
                        + "INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj WHERE "
                        + "rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and ji.nm_perawatan like '%libur%' and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') and rd.kd_dokter not in ('-','--') "
                        + "and pj.png_jawab like '%" + nmpenjab.getText() + "%' GROUP BY rd.kd_dokter, k.kelas ORDER BY d.nm_dokter, k.kelas) as a group by a.nm_dokter", param);
            } else if (!kdpenjab.getText().equals("") && (!cmbRuangan.getSelectedItem().toString().equals("SEMUA RUANG"))) {
                param.put("cara_byr", "UNTUK JENIS BAYAR PASIEN " + nmpenjab.getText() + " KHUSUS DOKTER YANG MENANGANI");
                param.put("ruangan", "UNTUK RUANG PERAWATAN " + cmbRuangan.getSelectedItem());
                Valid.MyReport("rptRekapVisiteInap.jasper", "report", "::[ Laporan Rekap Detail Visite Hari Libur Dokter Rawat Inap ]::",
                        "select a.nm_dokter, CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT) Kelas_1, "
                        + "CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT) Kelas_2, "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT) Kelas_3, "
                        + "CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT) Kelas_vip, "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT) Kelas_vvip, "
                        + "CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end),INT) rwt_khusus, "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT) intensif, "
                        + "CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT) high_care, "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT) isolasi, "
                        + "CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end)+ "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT),INT) total_visit "
                        + "from (SELECT d.nm_dokter,k.kelas,COUNT(rd.kd_dokter) total FROM rawat_inap_dr rd "
                        + "INNER JOIN jns_perawatan_inap ji on ji.kd_jenis_prw=rd.kd_jenis_prw and ji.nm_perawatan like '%visit%' "
                        + "INNER JOIN dokter d on d.kd_dokter=rd.kd_dokter INNER JOIN reg_periksa rp on rp.no_rawat=rd.no_rawat "
                        + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat "
                        + "INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj WHERE "
                        + "rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and " + ruangDipilih + " and ji.nm_perawatan like '%libur%' and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') "
                        + "and rd.kd_dokter not in ('-','--') and pj.png_jawab like '%" + nmpenjab.getText() + "%' GROUP BY rd.kd_dokter, k.kelas ORDER BY d.nm_dokter, k.kelas) as a group by a.nm_dokter", param);
            } else if (!kdpenjab.getText().equals("") && (cmbRuangan.getSelectedItem().toString().equals("SEMUA RUANG"))) {
                param.put("cara_byr", "UNTUK JENIS BAYAR PASIEN " + nmpenjab.getText() + " KHUSUS DOKTER YANG MENANGANI");
                param.put("ruangan", "UNTUK " + cmbRuangan.getSelectedItem().toString() + " PERAWATAN");
                Valid.MyReport("rptRekapVisiteInap.jasper", "report", "::[ Laporan Rekap Detail Visite Hari Libur Dokter Rawat Inap ]::",
                        "select a.nm_dokter, CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT) Kelas_1, "
                        + "CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT) Kelas_2, "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT) Kelas_3, "
                        + "CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT) Kelas_vip, "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT) Kelas_vvip, "
                        + "CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end),INT) rwt_khusus, "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT) intensif, "
                        + "CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT) high_care, "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT) isolasi, "
                        + "CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end)+ "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT),INT) total_visit "
                        + "from (SELECT d.nm_dokter,k.kelas,COUNT(rd.kd_dokter) total FROM rawat_inap_dr rd "
                        + "INNER JOIN jns_perawatan_inap ji on ji.kd_jenis_prw=rd.kd_jenis_prw and ji.nm_perawatan like '%visit%' "
                        + "INNER JOIN dokter d on d.kd_dokter=rd.kd_dokter INNER JOIN reg_periksa rp on rp.no_rawat=rd.no_rawat "
                        + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat "
                        + "INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj WHERE "
                        + "rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and ji.nm_perawatan like '%libur%' and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') and rd.kd_dokter not in ('-','--') "
                        + "and pj.png_jawab like '%" + nmpenjab.getText() + "%' GROUP BY rd.kd_dokter, k.kelas ORDER BY d.nm_dokter, k.kelas) as a group by a.nm_dokter", param);
            } else if (kdpenjab.getText().equals("") && (!cmbRuangan.getSelectedItem().toString().equals("SEMUA RUANG"))) {
                param.put("cara_byr", "SEMUA JENIS BAYAR UNTUK DOKTER YANG MENANGANI");
                param.put("ruangan", "UNTUK RUANG PERAWATAN " + cmbRuangan.getSelectedItem());
                Valid.MyReport("rptRekapVisiteInap.jasper", "report", "::[ Laporan Rekap Detail Visite Hari Libur Dokter Rawat Inap ]::",
                        "select a.nm_dokter, CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT) Kelas_1, "
                        + "CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT) Kelas_2, "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT) Kelas_3, "
                        + "CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT) Kelas_vip, "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT) Kelas_vvip, "
                        + "CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end),INT) rwt_khusus, "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT) intensif, "
                        + "CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT) high_care, "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT) isolasi, "
                        + "CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end)+ "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT),INT) total_visit "
                        + "from (SELECT d.nm_dokter,k.kelas,COUNT(rd.kd_dokter) total FROM rawat_inap_dr rd "
                        + "INNER JOIN jns_perawatan_inap ji on ji.kd_jenis_prw=rd.kd_jenis_prw and ji.nm_perawatan like '%visit%' "
                        + "INNER JOIN dokter d on d.kd_dokter=rd.kd_dokter INNER JOIN reg_periksa rp on rp.no_rawat=rd.no_rawat "
                        + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat "
                        + "INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj WHERE "
                        + "rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and " + ruangDipilih + " and ji.nm_perawatan like '%libur%' and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') "
                        + "and rd.kd_dokter not in ('-','--') and pj.png_jawab like '%" + nmpenjab.getText() + "%' GROUP BY rd.kd_dokter, k.kelas ORDER BY d.nm_dokter, k.kelas) as a group by a.nm_dokter", param);
            }
        }
        
        this.setCursor(Cursor.getDefaultCursor());
        rekapVisit();
        rekapVisitNormal1();
        rekapVisitLibur1();
    }
    
    private void prinRekapDokterMewakili() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("periode", "Periode Tgl. " + Tgl1.getSelectedItem() + " S.D " + Tgl2.getSelectedItem());

        if (cmbPrin.getSelectedIndex() == 4) {
            param.put("judul", "Rekap Visite Semua Hari Perawatan Dokter Umum/Yang Mewakili");
            if (kdpenjab.getText().equals("") && (cmbRuangan.getSelectedItem().toString().equals("SEMUA RUANG"))) {
                param.put("cara_byr", "SEMUA JENIS BAYAR UNTUK DOKTER UMUM/YANG MEWAKILI");
                param.put("ruangan", "UNTUK " + cmbRuangan.getSelectedItem().toString() + " PERAWATAN");
                Valid.MyReport("rptRekapVisiteInap1.jasper", "report", "::[ Laporan Rekap Detail Visite Semua Hari Perawatan Dokter Rawat Inap ]::",
                        "select a.nm_dokter, CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT) Kelas_1, "
                        + "CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT) Kelas_2, "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT) Kelas_3, "
                        + "CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT) Kelas_vip, "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT) Kelas_vvip, "
                        + "CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end),INT) rwt_khusus, "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT) intensif, "
                        + "CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT) high_care, "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT) isolasi, "
                        + "CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end)+ "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT),INT) total_visit "
                        + "from (SELECT d.nm_dokter,k.kelas,COUNT(rd.kd_dokter_mewakili) total "
                        + "FROM rawat_inap_dr rd INNER JOIN jns_perawatan_inap ji on ji.kd_jenis_prw=rd.kd_jenis_prw "
                        + "INNER JOIN dokter d on d.kd_dokter=rd.kd_dokter_mewakili INNER JOIN reg_periksa rp on rp.no_rawat=rd.no_rawat "
                        + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat "
                        + "INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj WHERE "
                        + "rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and ji.nm_perawatan like '%visit%' and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') and rd.kd_dokter_mewakili not in ('-','--') "
                        + "and pj.png_jawab like '%" + nmpenjab.getText() + "%' GROUP BY rd.kd_dokter_mewakili, k.kelas ORDER BY d.nm_dokter, k.kelas) as a group by a.nm_dokter", param);
            } else if (!kdpenjab.getText().equals("") && (!cmbRuangan.getSelectedItem().toString().equals("SEMUA RUANG"))) {
                param.put("cara_byr", "UNTUK JENIS BAYAR PASIEN " + nmpenjab.getText() + " KHUSUS DOKTER UMUM/YANG MEWAKILI");
                param.put("ruangan", "UNTUK RUANG PERAWATAN " + cmbRuangan.getSelectedItem());
                Valid.MyReport("rptRekapVisiteInap1.jasper", "report", "::[ Laporan Rekap Detail Visite Semua Hari Perawatan Dokter Rawat Inap ]::",
                        "select a.nm_dokter, CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT) Kelas_1, "
                        + "CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT) Kelas_2, "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT) Kelas_3, "
                        + "CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT) Kelas_vip, "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT) Kelas_vvip, "
                        + "CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end),INT) rwt_khusus, "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT) intensif, "
                        + "CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT) high_care, "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT) isolasi, "
                        + "CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end)+ "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT),INT) total_visit "
                        + "from (SELECT d.nm_dokter,k.kelas,COUNT(rd.kd_dokter_mewakili) total "
                        + "FROM rawat_inap_dr rd INNER JOIN jns_perawatan_inap ji on ji.kd_jenis_prw=rd.kd_jenis_prw "
                        + "INNER JOIN dokter d on d.kd_dokter=rd.kd_dokter_mewakili INNER JOIN reg_periksa rp on rp.no_rawat=rd.no_rawat "
                        + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat "
                        + "INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj WHERE "
                        + "rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and " + ruangDipilih + " and ji.nm_perawatan like '%visit%' and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') "
                        + "and rd.kd_dokter_mewakili not in ('-','--') and pj.png_jawab like '%" + nmpenjab.getText() + "%' GROUP BY rd.kd_dokter_mewakili, k.kelas ORDER BY d.nm_dokter, k.kelas) as a group by a.nm_dokter", param);
            } else if (!kdpenjab.getText().equals("") && (cmbRuangan.getSelectedItem().toString().equals("SEMUA RUANG"))) {
                param.put("cara_byr", "UNTUK JENIS BAYAR PASIEN " + nmpenjab.getText() + " KHUSUS DOKTER UMUM/YANG MEWAKILI");
                param.put("ruangan", "UNTUK " + cmbRuangan.getSelectedItem().toString() + " PERAWATAN");
                Valid.MyReport("rptRekapVisiteInap1.jasper", "report", "::[ Laporan Rekap Detail Visite Semua Hari Perawatan Dokter Rawat Inap ]::",
                        "select a.nm_dokter, CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT) Kelas_1, "
                        + "CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT) Kelas_2, "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT) Kelas_3, "
                        + "CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT) Kelas_vip, "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT) Kelas_vvip, "
                        + "CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end),INT) rwt_khusus, "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT) intensif, "
                        + "CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT) high_care, "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT) isolasi, "
                        + "CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end)+ "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT),INT) total_visit "
                        + "from (SELECT d.nm_dokter,k.kelas,COUNT(rd.kd_dokter_mewakili) total "
                        + "FROM rawat_inap_dr rd INNER JOIN jns_perawatan_inap ji on ji.kd_jenis_prw=rd.kd_jenis_prw "
                        + "INNER JOIN dokter d on d.kd_dokter=rd.kd_dokter_mewakili INNER JOIN reg_periksa rp on rp.no_rawat=rd.no_rawat "
                        + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat "
                        + "INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj WHERE "
                        + "rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and ji.nm_perawatan like '%visit%' and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') and rd.kd_dokter_mewakili not in ('-','--') "
                        + "and pj.png_jawab like '%" + nmpenjab.getText() + "%' GROUP BY rd.kd_dokter_mewakili, k.kelas ORDER BY d.nm_dokter, k.kelas) as a group by a.nm_dokter", param);
            } else if (kdpenjab.getText().equals("") && (!cmbRuangan.getSelectedItem().toString().equals("SEMUA RUANG"))) {
                param.put("cara_byr", "SEMUA JENIS BAYAR UNTUK DOKTER UMUM/YANG MEWAKILI");
                param.put("ruangan", "UNTUK RUANG PERAWATAN " + cmbRuangan.getSelectedItem());
                Valid.MyReport("rptRekapVisiteInap1.jasper", "report", "::[ Laporan Rekap Detail Visite Semua Hari Perawatan Dokter Rawat Inap ]::",
                        "select a.nm_dokter, CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT) Kelas_1, "
                        + "CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT) Kelas_2, "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT) Kelas_3, "
                        + "CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT) Kelas_vip, "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT) Kelas_vvip, "
                        + "CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end),INT) rwt_khusus, "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT) intensif, "
                        + "CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT) high_care, "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT) isolasi, "
                        + "CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end)+ "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT),INT) total_visit "
                        + "from (SELECT d.nm_dokter,k.kelas,COUNT(rd.kd_dokter_mewakili) total "
                        + "FROM rawat_inap_dr rd INNER JOIN jns_perawatan_inap ji on ji.kd_jenis_prw=rd.kd_jenis_prw "
                        + "INNER JOIN dokter d on d.kd_dokter=rd.kd_dokter_mewakili INNER JOIN reg_periksa rp on rp.no_rawat=rd.no_rawat "
                        + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat "
                        + "INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj WHERE "
                        + "rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and " + ruangDipilih + " and ji.nm_perawatan like '%visit%' and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') "
                        + "and rd.kd_dokter_mewakili not in ('-','--') and pj.png_jawab like '%" + nmpenjab.getText() + "%' GROUP BY rd.kd_dokter_mewakili, k.kelas ORDER BY d.nm_dokter, k.kelas) as a group by a.nm_dokter", param);
            }
        } else if (cmbPrin.getSelectedIndex() == 11) {
            param.put("judul", "Rekap Visite Hari Normal Dokter Umum/Yang Mewakili");
            if (kdpenjab.getText().equals("") && (cmbRuangan.getSelectedItem().toString().equals("SEMUA RUANG"))) {
                param.put("cara_byr", "SEMUA JENIS BAYAR UNTUK DOKTER UMUM/YANG MEWAKILI");
                param.put("ruangan", "UNTUK " + cmbRuangan.getSelectedItem().toString() + " PERAWATAN");
                Valid.MyReport("rptRekapVisiteInap1.jasper", "report", "::[ Laporan Rekap Detail Visite Hari Normal Dokter Rawat Inap ]::",
                        "select a.nm_dokter, CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT) Kelas_1, "
                        + "CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT) Kelas_2, "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT) Kelas_3, "
                        + "CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT) Kelas_vip, "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT) Kelas_vvip, "
                        + "CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end),INT) rwt_khusus, "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT) intensif, "
                        + "CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT) high_care, "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT) isolasi, "
                        + "CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end)+ "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT),INT) total_visit "
                        + "from (SELECT d.nm_dokter,k.kelas,COUNT(rd.kd_dokter_mewakili) total FROM rawat_inap_dr rd "
                        + "INNER JOIN jns_perawatan_inap ji on ji.kd_jenis_prw=rd.kd_jenis_prw and ji.nm_perawatan like '%visit%' "
                        + "INNER JOIN dokter d on d.kd_dokter=rd.kd_dokter_mewakili INNER JOIN reg_periksa rp on rp.no_rawat=rd.no_rawat "
                        + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat "
                        + "INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj WHERE "
                        + "rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and ji.nm_perawatan not like '%libur%' and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') and rd.kd_dokter_mewakili not in ('-','--') "
                        + "and pj.png_jawab like '%" + nmpenjab.getText() + "%' GROUP BY rd.kd_dokter_mewakili, k.kelas ORDER BY d.nm_dokter, k.kelas) as a group by a.nm_dokter", param);
            } else if (!kdpenjab.getText().equals("") && (!cmbRuangan.getSelectedItem().toString().equals("SEMUA RUANG"))) {
                param.put("cara_byr", "UNTUK JENIS BAYAR PASIEN " + nmpenjab.getText() + " KHUSUS DOKTER UMUM/YANG MEWAKILI");
                param.put("ruangan", "UNTUK RUANG PERAWATAN " + cmbRuangan.getSelectedItem());
                Valid.MyReport("rptRekapVisiteInap1.jasper", "report", "::[ Laporan Rekap Detail Visite Hari Normal Dokter Rawat Inap ]::",
                        "select a.nm_dokter, CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT) Kelas_1, "
                        + "CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT) Kelas_2, "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT) Kelas_3, "
                        + "CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT) Kelas_vip, "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT) Kelas_vvip, "
                        + "CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end),INT) rwt_khusus, "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT) intensif, "
                        + "CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT) high_care, "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT) isolasi, "
                        + "CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end)+ "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT),INT) total_visit "
                        + "from (SELECT d.nm_dokter,k.kelas,COUNT(rd.kd_dokter_mewakili) total FROM rawat_inap_dr rd "
                        + "INNER JOIN jns_perawatan_inap ji on ji.kd_jenis_prw=rd.kd_jenis_prw and ji.nm_perawatan like '%visit%' "
                        + "INNER JOIN dokter d on d.kd_dokter=rd.kd_dokter_mewakili INNER JOIN reg_periksa rp on rp.no_rawat=rd.no_rawat "
                        + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat "
                        + "INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj WHERE "
                        + "rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and " + ruangDipilih + " and ji.nm_perawatan not like '%libur%' and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') "
                        + "and rd.kd_dokter_mewakili not in ('-','--') and pj.png_jawab like '%" + nmpenjab.getText() + "%' GROUP BY rd.kd_dokter_mewakili, k.kelas ORDER BY d.nm_dokter, k.kelas) as a group by a.nm_dokter", param);
            } else if (!kdpenjab.getText().equals("") && (cmbRuangan.getSelectedItem().toString().equals("SEMUA RUANG"))) {
                param.put("cara_byr", "UNTUK JENIS BAYAR PASIEN " + nmpenjab.getText() + " KHUSUS DOKTER UMUM/YANG MEWAKILI");
                param.put("ruangan", "UNTUK " + cmbRuangan.getSelectedItem().toString() + " PERAWATAN");
                Valid.MyReport("rptRekapVisiteInap1.jasper", "report", "::[ Laporan Rekap Detail Visite Hari Normal Dokter Rawat Inap ]::",
                        "select a.nm_dokter, CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT) Kelas_1, "
                        + "CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT) Kelas_2, "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT) Kelas_3, "
                        + "CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT) Kelas_vip, "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT) Kelas_vvip, "
                        + "CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end),INT) rwt_khusus, "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT) intensif, "
                        + "CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT) high_care, "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT) isolasi, "
                        + "CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end)+ "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT),INT) total_visit "
                        + "from (SELECT d.nm_dokter,k.kelas,COUNT(rd.kd_dokter_mewakili) total FROM rawat_inap_dr rd "
                        + "INNER JOIN jns_perawatan_inap ji on ji.kd_jenis_prw=rd.kd_jenis_prw and ji.nm_perawatan like '%visit%' "
                        + "INNER JOIN dokter d on d.kd_dokter=rd.kd_dokter_mewakili INNER JOIN reg_periksa rp on rp.no_rawat=rd.no_rawat "
                        + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat "
                        + "INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj WHERE "
                        + "rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and ji.nm_perawatan not like '%libur%' and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') and rd.kd_dokter_mewakili not in ('-','--') "
                        + "and pj.png_jawab like '%" + nmpenjab.getText() + "%' GROUP BY rd.kd_dokter_mewakili, k.kelas ORDER BY d.nm_dokter, k.kelas) as a group by a.nm_dokter", param);
            } else if (kdpenjab.getText().equals("") && (!cmbRuangan.getSelectedItem().toString().equals("SEMUA RUANG"))) {
                param.put("cara_byr", "SEMUA JENIS BAYAR UNTUK DOKTER UMUM/YANG MEWAKILI");
                param.put("ruangan", "UNTUK RUANG PERAWATAN " + cmbRuangan.getSelectedItem());
                Valid.MyReport("rptRekapVisiteInap1.jasper", "report", "::[ Laporan Rekap Detail Visite Hari Normal Dokter Rawat Inap ]::",
                        "select a.nm_dokter, CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT) Kelas_1, "
                        + "CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT) Kelas_2, "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT) Kelas_3, "
                        + "CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT) Kelas_vip, "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT) Kelas_vvip, "
                        + "CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end),INT) rwt_khusus, "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT) intensif, "
                        + "CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT) high_care, "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT) isolasi, "
                        + "CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end)+ "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT),INT) total_visit "
                        + "from (SELECT d.nm_dokter,k.kelas,COUNT(rd.kd_dokter_mewakili) total FROM rawat_inap_dr rd "
                        + "INNER JOIN jns_perawatan_inap ji on ji.kd_jenis_prw=rd.kd_jenis_prw and ji.nm_perawatan like '%visit%' "
                        + "INNER JOIN dokter d on d.kd_dokter=rd.kd_dokter_mewakili INNER JOIN reg_periksa rp on rp.no_rawat=rd.no_rawat "
                        + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat "
                        + "INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj WHERE "
                        + "rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and " + ruangDipilih + " and ji.nm_perawatan not like '%libur%' and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') "
                        + "and rd.kd_dokter_mewakili not in ('-','--') and pj.png_jawab like '%" + nmpenjab.getText() + "%' GROUP BY rd.kd_dokter_mewakili, k.kelas ORDER BY d.nm_dokter, k.kelas) as a group by a.nm_dokter", param);
            }
        } else if (cmbPrin.getSelectedIndex() == 15) {
            param.put("judul", "Rekap Visite Hari Libur Dokter Umum/Yang Mewakili");
            if (kdpenjab.getText().equals("") && (cmbRuangan.getSelectedItem().toString().equals("SEMUA RUANG"))) {
                param.put("cara_byr", "SEMUA JENIS BAYAR UNTUK DOKTER UMUM/YANG MEWAKILI");
                param.put("ruangan", "UNTUK " + cmbRuangan.getSelectedItem().toString() + " PERAWATAN");
                Valid.MyReport("rptRekapVisiteInap1.jasper", "report", "::[ Laporan Rekap Detail Visite Hari Libur Dokter Rawat Inap ]::",
                        "select a.nm_dokter, CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT) Kelas_1, "
                        + "CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT) Kelas_2, "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT) Kelas_3, "
                        + "CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT) Kelas_vip, "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT) Kelas_vvip, "
                        + "CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end),INT) rwt_khusus, "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT) intensif, "
                        + "CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT) high_care, "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT) isolasi, "
                        + "CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end)+ "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT),INT) total_visit "
                        + "from (SELECT d.nm_dokter,k.kelas,COUNT(rd.kd_dokter_mewakili) total FROM rawat_inap_dr rd "
                        + "INNER JOIN jns_perawatan_inap ji on ji.kd_jenis_prw=rd.kd_jenis_prw and ji.nm_perawatan like '%visit%' "
                        + "INNER JOIN dokter d on d.kd_dokter=rd.kd_dokter_mewakili INNER JOIN reg_periksa rp on rp.no_rawat=rd.no_rawat "
                        + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat "
                        + "INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj WHERE "
                        + "rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and ji.nm_perawatan like '%libur%' and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') and rd.kd_dokter_mewakili not in ('-','--') "
                        + "and pj.png_jawab like '%" + nmpenjab.getText() + "%' GROUP BY rd.kd_dokter_mewakili, k.kelas ORDER BY d.nm_dokter, k.kelas) as a group by a.nm_dokter", param);
            } else if (!kdpenjab.getText().equals("") && (!cmbRuangan.getSelectedItem().toString().equals("SEMUA RUANG"))) {
                param.put("cara_byr", "UNTUK JENIS BAYAR PASIEN " + nmpenjab.getText() + " KHUSUS DOKTER UMUM/YANG MEWAKILI");
                param.put("ruangan", "UNTUK RUANG PERAWATAN " + cmbRuangan.getSelectedItem());
                Valid.MyReport("rptRekapVisiteInap1.jasper", "report", "::[ Laporan Rekap Detail Visite Hari Libur Dokter Rawat Inap ]::",
                        "select a.nm_dokter, CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT) Kelas_1, "
                        + "CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT) Kelas_2, "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT) Kelas_3, "
                        + "CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT) Kelas_vip, "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT) Kelas_vvip, "
                        + "CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end),INT) rwt_khusus, "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT) intensif, "
                        + "CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT) high_care, "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT) isolasi, "
                        + "CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end)+ "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT),INT) total_visit "
                        + "from (SELECT d.nm_dokter,k.kelas,COUNT(rd.kd_dokter_mewakili) total FROM rawat_inap_dr rd "
                        + "INNER JOIN jns_perawatan_inap ji on ji.kd_jenis_prw=rd.kd_jenis_prw and ji.nm_perawatan like '%visit%' "
                        + "INNER JOIN dokter d on d.kd_dokter=rd.kd_dokter_mewakili INNER JOIN reg_periksa rp on rp.no_rawat=rd.no_rawat "
                        + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat "
                        + "INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj WHERE "
                        + "rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and " + ruangDipilih + " and ji.nm_perawatan like '%libur%' and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') "
                        + "and rd.kd_dokter_mewakili not in ('-','--') and pj.png_jawab like '%" + nmpenjab.getText() + "%' GROUP BY rd.kd_dokter_mewakili, k.kelas ORDER BY d.nm_dokter, k.kelas) as a group by a.nm_dokter", param);
            } else if (!kdpenjab.getText().equals("") && (cmbRuangan.getSelectedItem().toString().equals("SEMUA RUANG"))) {
                param.put("cara_byr", "UNTUK JENIS BAYAR PASIEN " + nmpenjab.getText() + " KHUSUS DOKTER UMUM/YANG MEWAKILI");
                param.put("ruangan", "UNTUK " + cmbRuangan.getSelectedItem().toString() + " PERAWATAN");
                Valid.MyReport("rptRekapVisiteInap1.jasper", "report", "::[ Laporan Rekap Detail Visite Hari Libur Dokter Rawat Inap ]::",
                        "select a.nm_dokter, CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT) Kelas_1, "
                        + "CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT) Kelas_2, "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT) Kelas_3, "
                        + "CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT) Kelas_vip, "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT) Kelas_vvip, "
                        + "CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end),INT) rwt_khusus, "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT) intensif, "
                        + "CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT) high_care, "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT) isolasi, "
                        + "CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end)+ "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT),INT) total_visit "
                        + "from (SELECT d.nm_dokter,k.kelas,COUNT(rd.kd_dokter_mewakili) total FROM rawat_inap_dr rd "
                        + "INNER JOIN jns_perawatan_inap ji on ji.kd_jenis_prw=rd.kd_jenis_prw and ji.nm_perawatan like '%visit%' "
                        + "INNER JOIN dokter d on d.kd_dokter=rd.kd_dokter_mewakili INNER JOIN reg_periksa rp on rp.no_rawat=rd.no_rawat "
                        + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat "
                        + "INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj WHERE "
                        + "rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and ji.nm_perawatan like '%libur%' and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') and rd.kd_dokter_mewakili not in ('-','--') "
                        + "and pj.png_jawab like '%" + nmpenjab.getText() + "%' GROUP BY rd.kd_dokter_mewakili, k.kelas ORDER BY d.nm_dokter, k.kelas) as a group by a.nm_dokter", param);
            } else if (kdpenjab.getText().equals("") && (!cmbRuangan.getSelectedItem().toString().equals("SEMUA RUANG"))) {
                param.put("cara_byr", "SEMUA JENIS BAYAR UNTUK DOKTER UMUM/YANG MEWAKILI");
                param.put("ruangan", "UNTUK RUANG PERAWATAN " + cmbRuangan.getSelectedItem());
                Valid.MyReport("rptRekapVisiteInap1.jasper", "report", "::[ Laporan Rekap Detail Visite Hari Libur Dokter Rawat Inap ]::",
                        "select a.nm_dokter, CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT) Kelas_1, "
                        + "CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT) Kelas_2, "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT) Kelas_3, "
                        + "CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT) Kelas_vip, "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT) Kelas_vvip, "
                        + "CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end),INT) rwt_khusus, "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT) intensif, "
                        + "CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT) high_care, "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT) isolasi, "
                        + "CONVERT(sum(case when kelas = 'Kelas 1' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas 2' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas 3' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Kelas VIP' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Kelas VVIP' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'Rawat Khusus' then total else 0 end)+ "
                        + "CONVERT(sum(case when kelas = 'Intensif' then total else 0 end),INT)+CONVERT(sum(case when kelas = 'High Care' then total else 0 end),INT)+ "
                        + "CONVERT(sum(case when kelas = 'Isolasi' then total else 0 end),INT),INT) total_visit "
                        + "from (SELECT d.nm_dokter,k.kelas,COUNT(rd.kd_dokter_mewakili) total FROM rawat_inap_dr rd "
                        + "INNER JOIN jns_perawatan_inap ji on ji.kd_jenis_prw=rd.kd_jenis_prw and ji.nm_perawatan like '%visit%' "
                        + "INNER JOIN dokter d on d.kd_dokter=rd.kd_dokter_mewakili INNER JOIN reg_periksa rp on rp.no_rawat=rd.no_rawat "
                        + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat "
                        + "INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj WHERE "
                        + "rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and " + ruangDipilih + " and ji.nm_perawatan like '%libur%' and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') "
                        + "and rd.kd_dokter_mewakili not in ('-','--') and pj.png_jawab like '%" + nmpenjab.getText() + "%' GROUP BY rd.kd_dokter_mewakili, k.kelas ORDER BY d.nm_dokter, k.kelas) as a group by a.nm_dokter", param);
            }
        }
        this.setCursor(Cursor.getDefaultCursor());
        rekapVisit1();
        rekapVisitNormal2();
        rekapVisitLibur2();
    }
    
    private void exportFileExcelRekapDokterMewakili() {
        dialog_simpan = "";

        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (cmbRuangan.getSelectedItem().toString().equals("SEMUA RUANG")) {
            dialog_simpan = Valid.openDialog();
            Valid.MyReportToExcel("SELECT d1.nm_dokter 'DOKTER MEWAKILI', DATE_FORMAT(rid.tgl_perawatan,'%d/%m/%Y') 'TGL. VISIT DR. MEWAKILI', "
                    + "d2.nm_dokter 'DPJP', rp.no_rkm_medis 'NO. RM', p.nm_pasien 'NAMA PASIEN', b.nm_bangsal 'RUANG PERAWATAN', pj.png_jawab 'CARA BAYAR' FROM dpjp_ranap dr "
                    + "INNER JOIN kamar_inap ki on ki.no_rawat=dr.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar "
                    + "INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal INNER JOIN rawat_inap_dr rid on rid.no_rawat=ki.no_rawat "
                    + "INNER JOIN reg_periksa rp on rp.no_rawat=ki.no_rawat INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj "
                    + "INNER JOIN dokter d1 on d1.kd_dokter=rid.kd_dokter_mewakili INNER JOIN dokter d2 on d2.kd_dokter=dr.kd_dokter INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis WHERE "
                    + "rid.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                    + "and ki.stts_pulang not in ('-','pindah kamar') and d1.kd_dokter not in ('-','--') and pj.png_jawab like '%" + nmpenjab.getText() + "%' "
                    + "ORDER BY d1.nm_dokter, p.nm_pasien, rid.tgl_perawatan", dialog_simpan);

            JOptionPane.showMessageDialog(null, "Data telah berhasil diexport menjadi file excel,..!!!");

        } else if (!cmbRuangan.getSelectedItem().toString().equals("SEMUA RUANG")) {
            dialog_simpan = Valid.openDialog();
            Valid.MyReportToExcel("SELECT d1.nm_dokter 'DOKTER MEWAKILI', DATE_FORMAT(rid.tgl_perawatan,'%d/%m/%Y') 'TGL. VISIT DR. MEWAKILI', "
                    + "d2.nm_dokter 'DPJP', rp.no_rkm_medis 'NO. RM', p.nm_pasien 'NAMA PASIEN', b.nm_bangsal 'RUANG PERAWATAN', pj.png_jawab 'CARA BAYAR' FROM dpjp_ranap dr "
                    + "INNER JOIN kamar_inap ki on ki.no_rawat=dr.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar "
                    + "INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal INNER JOIN rawat_inap_dr rid on rid.no_rawat=ki.no_rawat "
                    + "INNER JOIN reg_periksa rp on rp.no_rawat=ki.no_rawat INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj "
                    + "INNER JOIN dokter d1 on d1.kd_dokter=rid.kd_dokter_mewakili INNER JOIN dokter d2 on d2.kd_dokter=dr.kd_dokter INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis WHERE "
                    + "rid.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                    + "and " + ruangDipilih + " and ki.stts_pulang not in ('-','pindah kamar') and d1.kd_dokter not in ('-','--') "
                    + "and pj.png_jawab like '%" + nmpenjab.getText() + "%' ORDER BY d1.nm_dokter, p.nm_pasien, rid.tgl_perawatan", dialog_simpan);

            JOptionPane.showMessageDialog(null, "Data telah berhasil diexport menjadi file excel,..!!!");
        }

        this.setCursor(Cursor.getDefaultCursor());
        rekapVisit1();
    }
    
    private void exportFileExcelVisitCovid() {
        dialog_simpan = "";

        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        dialog_simpan = Valid.openDialog();
        Valid.MyReportToExcel("SELECT DATE_FORMAT(ki.tgl_masuk,'%d-%m-%Y') 'TGL. MASUK', DATE_FORMAT(ki.tgl_keluar,'%d-%m-%Y') 'TGL. KELUAR', rp.no_rkm_medis 'NO. RM', p.nm_pasien 'NAMA PASIEN', "
                + "b.nm_bangsal 'RUANG PERAWATAN', ki.stts_pulang 'STATUS PULANG', ji.nm_perawatan 'NAMA PERAWATAN', DATE_FORMAT(rd.tgl_perawatan,'%d-%m-%Y') 'TGL. RAWAT', "
                + "DATE_FORMAT(rd.jam_rawat,'%h:%i %p') 'JAM RAWAT', d.nm_dokter 'NAMA DOKTER' FROM rawat_inap_dr rd INNER JOIN jns_perawatan_inap ji on ji.kd_jenis_prw=rd.kd_jenis_prw "
                + "INNER JOIN dokter d on d.kd_dokter=rd.kd_dokter INNER JOIN reg_periksa rp on rp.no_rawat=rd.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                + "INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal "
                + "INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj WHERE rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                + "and ji.nm_perawatan like '%visit%' and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') and rd.kd_dokter not in ('-','--') and b.nm_bangsal like '%covid%' "
                + "ORDER BY b.nm_bangsal,rd.tgl_perawatan", dialog_simpan);

        JOptionPane.showMessageDialog(null, "Data Visite COVID-19 telah berhasil diexport menjadi file excel,..!!!");
        this.setCursor(Cursor.getDefaultCursor());
        empttext();
    }
    
    private void exportFileExcelKonsulCovid() {
        dialog_simpan = "";

        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        dialog_simpan = Valid.openDialog();
        Valid.MyReportToExcel("SELECT DATE_FORMAT(ki.tgl_masuk,'%d-%m-%Y') 'TGL. MASUK', DATE_FORMAT(ki.tgl_keluar,'%d-%m-%Y') 'TGL. KELUAR', rp.no_rkm_medis 'NO. RM', p.nm_pasien 'NAMA PASIEN', "
                + "b.nm_bangsal 'RUANG PERAWATAN', ki.stts_pulang 'STATUS PULANG', ji.nm_perawatan 'NAMA PERAWATAN', DATE_FORMAT(rd.tgl_perawatan,'%d-%m-%Y') 'TGL. RAWAT', "
                + "DATE_FORMAT(rd.jam_rawat,'%h:%i %p') 'JAM RAWAT', d.nm_dokter 'NAMA DOKTER' FROM rawat_inap_dr rd INNER JOIN jns_perawatan_inap ji on ji.kd_jenis_prw=rd.kd_jenis_prw "
                + "INNER JOIN dokter d on d.kd_dokter=rd.kd_dokter INNER JOIN reg_periksa rp on rp.no_rawat=rd.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                + "INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal "
                + "INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj WHERE rd.tgl_perawatan BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                + "and ji.nm_perawatan like '%konsul%' and ji.status='1' and ki.stts_pulang not in ('-','pindah kamar') and rd.kd_dokter not in ('-','--') and b.nm_bangsal like '%covid%' "
                + "ORDER BY b.nm_bangsal,rd.tgl_perawatan", dialog_simpan);

        JOptionPane.showMessageDialog(null, "Data Konsul COVID-19 telah berhasil diexport menjadi file excel,..!!!");
        this.setCursor(Cursor.getDefaultCursor());
        empttext();
    }
    
    private void insertDataKeTabeltemporary1() {        
        try {
            Sequel.queryu("delete from temporary1");
            ps = koneksi.prepareStatement("select bs.no_sep, date_format(rp.tgl_registrasi,'%d-%m-%Y') tglmsk, date_format(ki.tgl_keluar, '%d-%m-%Y') tglpulang, bs.no_kartu, "
                    + "bs.nama_pasien, bs.nomr, b.nm_gedung gedung, d.nm_dokter dpjp from rawat_inap_dr r inner join jns_perawatan_inap jp on jp.kd_jenis_prw=r.kd_jenis_prw "
                    + "inner join dpjp_ranap dr on dr.no_rawat=r.no_rawat INNER JOIN dokter d on d.kd_dokter=dr.kd_dokter "
                    + "inner join bridging_sep bs on bs.no_rawat=r.no_rawat inner join reg_periksa rp on rp.no_rawat=r.no_rawat "
                    + "inner join kamar_inap ki on ki.no_rawat=r.no_rawat inner join kamar k on k.kd_kamar=ki.kd_kamar inner join bangsal b on b.kd_bangsal=k.kd_bangsal where "
                    + "rp.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                    + "and jp.nm_perawatan like '%visit%' and ki.stts_pulang not in ('-','pindah kamar') and bs.jnspelayanan=1 and " + ruangDipilih + " and bs.no_sep like ? or "
                    + "rp.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                    + "and jp.nm_perawatan like '%visit%' and ki.stts_pulang not in ('-','pindah kamar') and bs.jnspelayanan=1 and " + ruangDipilih + " and bs.no_kartu like ? or "                            
                    + "rp.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                    + "and jp.nm_perawatan like '%visit%' and ki.stts_pulang not in ('-','pindah kamar') and bs.jnspelayanan=1 and " + ruangDipilih + " and bs.nama_pasien like ? or "                    
                    + "rp.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                    + "and jp.nm_perawatan like '%visit%' and ki.stts_pulang not in ('-','pindah kamar') and bs.jnspelayanan=1 and " + ruangDipilih + " and bs.nomr like ? or "                            
                    + "rp.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                    + "and jp.nm_perawatan like '%visit%' and ki.stts_pulang not in ('-','pindah kamar') and bs.jnspelayanan=1 and " + ruangDipilih + " and d.nm_dokter like ? "                                                      
                    + "group by bs.no_sep order by b.nm_gedung, bs.no_sep");
            try {
                ps.setString(1, "%" + TCari.getText().trim() + "%");
                ps.setString(2, "%" + TCari.getText().trim() + "%");
                ps.setString(3, "%" + TCari.getText().trim() + "%");
                ps.setString(4, "%" + TCari.getText().trim() + "%");
                ps.setString(5, "%" + TCari.getText().trim() + "%");
                rs = ps.executeQuery();
                x = 1;
                while (rs.next()) {
                    Sequel.AutoComitFalse();
                    if (Sequel.menyimpantf2("temporary1", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "Data Pasien", 37, new String[]{
                        x + ".", 
                        rs.getString("no_sep"), 
                        rs.getString("tglmsk"), 
                        rs.getString("tglpulang"), 
                        rs.getString("no_kartu"),
                        rs.getString("nama_pasien"), 
                        rs.getString("nomr"), 
                        rs.getString("gedung"), 
                        rs.getString("dpjp"), 
                        ":",
                        "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""
                    }) == true) {
                        //menghitung jumlah visite perdokter
                        ps1 = koneksi.prepareStatement("select d.nm_dokter dr_visit, count(r.kd_jenis_prw) jml from rawat_inap_dr r "
                                + "inner join jns_perawatan_inap jp on jp.kd_jenis_prw=r.kd_jenis_prw INNER JOIN dokter d on d.kd_dokter=r.kd_dokter "
                                + "inner join bridging_sep bs on bs.no_rawat=r.no_rawat inner join reg_periksa rp on rp.no_rawat=r.no_rawat "
                                + "inner join kamar_inap ki on ki.no_rawat=r.no_rawat inner join kamar k on k.kd_kamar=ki.kd_kamar inner join bangsal b on b.kd_bangsal=k.kd_bangsal where "
                                + "rp.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                                + "and jp.nm_perawatan like '%visit%' and ki.stts_pulang not in ('-','pindah kamar') and bs.jnspelayanan=1 "
                                + "and bs.no_sep='" + rs.getString("no_sep") + "' and " + ruangDipilih + " and r.kd_dokter not in ('-','--') "
                                + "group by bs.no_sep, r.kd_dokter order by b.nm_gedung, bs.no_sep, count(r.kd_jenis_prw) desc");
                        try {
                            rs1 = ps1.executeQuery();
                            while (rs1.next()) {
                                Sequel.AutoComitFalse();
                                if (Sequel.menyimpantf2("temporary1", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "Hitung Visite", 37, new String[]{
                                    "", "", "", "", "", "", "", "", "", "",
                                    rs1.getString("dr_visit"),
                                    rs1.getString("jml"),
                                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""
                                }) == true) {
                                    cmbPrin.setEnabled(true);
                                }
                                Sequel.AutoComitTrue();
                            }
                        } catch (Exception e) {
                            System.out.println("Notifikasi Penghitungan Visit : " + e);
                        } finally {
                            if (rs1 != null) {
                                rs1.close();
                            }
                            if (ps1 != null) {
                                ps1.close();
                            }
                        }
                    }
                    x++;
                    Sequel.AutoComitTrue();
                }
            } catch (Exception e) {
                System.out.println("Notifikasi Pasien : " + e);
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
        
        int jlh_pasien = 0;
        jlh_pasien = x - 1;
        LCount.setText("" + jlh_pasien);
    }
    
    private void tampilHitungVisit() {
        Valid.tabelKosong(tabMode8);
        try {
            ps2 = koneksi.prepareStatement("SELECT * from temporary1");
            try {                
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    tabMode8.addRow(new Object[]{
                        rs2.getString("temp1"),
                        rs2.getString("temp2"),
                        rs2.getString("temp3"),
                        rs2.getString("temp4"),
                        rs2.getString("temp5"),
                        rs2.getString("temp6"),
                        rs2.getString("temp7"),
                        rs2.getString("temp8"),
                        rs2.getString("temp9"),
                        rs2.getString("temp10"),
                        rs2.getString("temp11"),
                        rs2.getString("temp12")
                    });
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println("simrskhanza.DlgFeeVisitDokter.tampilHitungVisit() : " + e);
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
   
    private void exportFileExcelHitungVisit() {
        dialog_simpan = "";

        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        dialog_simpan = Valid.openDialog();
        Valid.MyReportToExcel("SELECT temp1 'No.', temp2 'No. SEP', temp3 'Tgl. Masuk', temp4 'Tgl. Pulang', temp5 'No. Kartu', "
                + "temp6 'Nama Pasien', temp7 'No. RM', temp8 'Ruang Perawatan', temp9 'DPJP', temp10 '', temp11 'Dokter Visite', "
                + "temp12 'Jmlh. Visite' from temporary1", dialog_simpan);

        JOptionPane.showMessageDialog(null, "Data Hitung Visite PerDokter Pasien BPJS telah berhasil diexport menjadi file excel,..!!!");
        this.setCursor(Cursor.getDefaultCursor());
    }
}
