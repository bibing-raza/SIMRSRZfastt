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
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author dosen
 */
public class RMSamplingPemanfaatanRM extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1, tabMode2, tabMode3, tabMode4, tabMode5, tabMode6, tabMode7, tabMode8,
            tabMode9, tabMode10, tabMode11, tabMode12, tabMode13, tabMode14, tabMode15, tabMode16, tabMode17, tabMode18, tabMode19, tabMode20;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps, psPas, ps1, ps2, ps3, ps4, ps5, ps6, ps7, ps8;
    private ResultSet rs, rsPas, rs1, rs2, rs3, rs4, rs5, rs6, rs7, rs8;
    private int i = 0, x = 0;
    private String pilihan = "", dialog_simpan = "";
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public RMSamplingPemanfaatanRM(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //data di tabel grid rata tengah
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);

        tabMode=new DefaultTableModel(null,new String[]{
            "No.", "NIP/NR", "Nama Perawat", "Jml. Askep IGD PerPasien"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbPerawatIgd.setModel(tabMode);
        tbPerawatIgd.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPerawatIgd.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 4; i++) {
            TableColumn column = tbPerawatIgd.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(130);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(150);
            } 
        }
        tbPerawatIgd.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbPerawatIgd.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbPerawatIgd.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        
        tabMode8=new DefaultTableModel(null,new String[]{
            "No.", "Tgl. Kunjungan", "No. RM", "Nama Pasien", "Cara Bayar", "Alamat", "Dokter IGD"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbPasienIgd.setModel(tabMode8);
        tbPasienIgd.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPasienIgd.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 7; i++) {
            TableColumn column = tbPasienIgd.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(80);
            } else if (i == 2) {
                column.setPreferredWidth(60);
            } else if (i == 3) {
                column.setPreferredWidth(220);
            } else if (i == 4) {
                column.setPreferredWidth(110);
            } else if (i == 5) {
                column.setPreferredWidth(250);
            } else if (i == 6) {
                column.setPreferredWidth(220);
            }  
        }
        tbPasienIgd.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbPasienIgd.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbPasienIgd.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbPasienIgd.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        
        tabMode1=new DefaultTableModel(null,new String[]{
            "No.", "NIP/NR", "Nama Petugas", "Jml. Triase Ponek PerPasien"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbBidanPonek.setModel(tabMode1);
        tbBidanPonek.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbBidanPonek.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 4; i++) {
            TableColumn column = tbBidanPonek.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(130);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(150);
            } 
        }
        tbBidanPonek.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbBidanPonek.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbBidanPonek.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        
        tabMode9=new DefaultTableModel(null,new String[]{
            "No.", "Tgl. Kunjungan", "No. RM", "Nama Pasien", "Cara Bayar", "Alamat", "Nama Dokter"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbPasienPonek.setModel(tabMode9);
        tbPasienPonek.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPasienPonek.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 7; i++) {
            TableColumn column = tbPasienPonek.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(80);
            } else if (i == 2) {
                column.setPreferredWidth(60);
            } else if (i == 3) {
                column.setPreferredWidth(220);
            } else if (i == 4) {
                column.setPreferredWidth(110);
            } else if (i == 5) {
                column.setPreferredWidth(250);
            } else if (i == 6) {
                column.setPreferredWidth(220);
            }  
        }
        tbPasienPonek.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbPasienPonek.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbPasienPonek.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbPasienPonek.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        
        tabMode2=new DefaultTableModel(null,new String[]{
            "No.", "NIP/NR", "Nama Perawat", "CPPT", "Assesmen Keperawatan Dewasa", "Unit Kerja"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbPerawatDewasa.setModel(tabMode2);
        tbPerawatDewasa.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPerawatDewasa.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 6; i++) {
            TableColumn column = tbPerawatDewasa.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(130);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(50);
            } else if (i == 4) {
                column.setPreferredWidth(190);
            } else if (i == 5) {
                column.setPreferredWidth(270);
            } 
        }
        tbPerawatDewasa.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbPerawatDewasa.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbPerawatDewasa.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbPerawatDewasa.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        
        tabMode10=new DefaultTableModel(null,new String[]{
            "No.", "Tgl. MRS", "No. RM", "Nama Pasien", "Cara Bayar", "Alamat", "Rg. Rawat Terakhir", "Nama DPJP"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbPasCpptDws.setModel(tabMode10);
        tbPasCpptDws.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPasCpptDws.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 8; i++) {
            TableColumn column = tbPasCpptDws.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(80);
            } else if (i == 2) {
                column.setPreferredWidth(60);
            } else if (i == 3) {
                column.setPreferredWidth(220);
            } else if (i == 4) {
                column.setPreferredWidth(110);
            } else if (i == 5) {
                column.setPreferredWidth(250);
            } else if (i == 6) {
                column.setPreferredWidth(190);
            } else if (i == 7) {
                column.setPreferredWidth(200);
            }  
        }
        tbPasCpptDws.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbPasCpptDws.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbPasCpptDws.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbPasCpptDws.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        
        tabMode11=new DefaultTableModel(null,new String[]{
            "No.", "Tgl. MRS", "No. RM", "Nama Pasien", "Cara Bayar", "Alamat", "Rg. Rawat Terakhir", "Nama DPJP"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbPasAskepDws.setModel(tabMode11);
        tbPasAskepDws.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPasAskepDws.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 8; i++) {
            TableColumn column = tbPasAskepDws.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(80);
            } else if (i == 2) {
                column.setPreferredWidth(60);
            } else if (i == 3) {
                column.setPreferredWidth(220);
            } else if (i == 4) {
                column.setPreferredWidth(110);
            } else if (i == 5) {
                column.setPreferredWidth(250);
            } else if (i == 6) {
                column.setPreferredWidth(190);
            } else if (i == 7) {
                column.setPreferredWidth(200);
            }  
        }
        tbPasAskepDws.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbPasAskepDws.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbPasAskepDws.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbPasAskepDws.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        
        tabMode3=new DefaultTableModel(null,new String[]{
            "No.", "NIP/NR", "Nama Petugas", "Jml. CPPT PerPasien"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbFarmasi.setModel(tabMode3);
        tbFarmasi.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbFarmasi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 4; i++) {
            TableColumn column = tbFarmasi.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(130);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(150);
            } 
        }
        tbFarmasi.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbFarmasi.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbFarmasi.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        
        tabMode12=new DefaultTableModel(null,new String[]{
            "No.", "Tgl. MRS", "No. RM", "Nama Pasien", "Cara Bayar", "Alamat", "Rg. Rawat Terakhir", "Nama DPJP"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbPasienFarmasi.setModel(tabMode12);
        tbPasienFarmasi.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPasienFarmasi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 8; i++) {
            TableColumn column = tbPasienFarmasi.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(80);
            } else if (i == 2) {
                column.setPreferredWidth(60);
            } else if (i == 3) {
                column.setPreferredWidth(220);
            } else if (i == 4) {
                column.setPreferredWidth(110);
            } else if (i == 5) {
                column.setPreferredWidth(250);
            } else if (i == 6) {
                column.setPreferredWidth(190);
            } else if (i == 7) {
                column.setPreferredWidth(200);
            }  
        }
        tbPasienFarmasi.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbPasienFarmasi.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbPasienFarmasi.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbPasienFarmasi.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        
        tabMode4=new DefaultTableModel(null,new String[]{
            "No.", "NIP/NR", "Nama Petugas", "CPPT", "Asuhan Gizi"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbNutrisionis.setModel(tabMode4);
        tbNutrisionis.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbNutrisionis.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 5; i++) {
            TableColumn column = tbNutrisionis.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(130);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(50);
            } else if (i == 4) {
                column.setPreferredWidth(70);
            } 
        }
        tbNutrisionis.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbNutrisionis.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbNutrisionis.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbNutrisionis.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        
        tabMode13=new DefaultTableModel(null,new String[]{
            "No.", "Tgl. MRS", "No. RM", "Nama Pasien", "Cara Bayar", "Alamat", "Rg. Rawat Terakhir", "Nama DPJP"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbPasCpptNutri.setModel(tabMode13);
        tbPasCpptNutri.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPasCpptNutri.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 8; i++) {
            TableColumn column = tbPasCpptNutri.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(80);
            } else if (i == 2) {
                column.setPreferredWidth(60);
            } else if (i == 3) {
                column.setPreferredWidth(220);
            } else if (i == 4) {
                column.setPreferredWidth(110);
            } else if (i == 5) {
                column.setPreferredWidth(250);
            } else if (i == 6) {
                column.setPreferredWidth(190);
            } else if (i == 7) {
                column.setPreferredWidth(200);
            }  
        }
        tbPasCpptNutri.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbPasCpptNutri.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbPasCpptNutri.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbPasCpptNutri.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        
        tabMode14=new DefaultTableModel(null,new String[]{
            "No.", "Tgl. MRS", "No. RM", "Nama Pasien", "Cara Bayar", "Alamat", "Rg. Rawat Terakhir", "Nama DPJP"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbPasAsuhan.setModel(tabMode14);
        tbPasAsuhan.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPasAsuhan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 8; i++) {
            TableColumn column = tbPasAsuhan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(80);
            } else if (i == 2) {
                column.setPreferredWidth(60);
            } else if (i == 3) {
                column.setPreferredWidth(220);
            } else if (i == 4) {
                column.setPreferredWidth(110);
            } else if (i == 5) {
                column.setPreferredWidth(250);
            } else if (i == 6) {
                column.setPreferredWidth(190);
            } else if (i == 7) {
                column.setPreferredWidth(200);
            }  
        }
        tbPasAsuhan.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbPasAsuhan.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbPasAsuhan.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbPasAsuhan.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        
        tabMode5=new DefaultTableModel(null,new String[]{
            "No.", "NIP/NR", "Nama Perawat", "CPPT", "Asesmen Keperawatan Anak"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbPerawatAnak.setModel(tabMode5);
        tbPerawatAnak.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPerawatAnak.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 5; i++) {
            TableColumn column = tbPerawatAnak.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(130);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(50);
            } else if (i == 4) {
                column.setPreferredWidth(160);
            } 
        }
        tbPerawatAnak.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbPerawatAnak.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbPerawatAnak.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbPerawatAnak.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        
        tabMode15=new DefaultTableModel(null,new String[]{
            "No.", "Tgl. MRS", "No. RM", "Nama Pasien", "Cara Bayar", "Alamat", "Rg. Rawat Terakhir", "Nama DPJP"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbPasAnakCppt.setModel(tabMode15);
        tbPasAnakCppt.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPasAnakCppt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 8; i++) {
            TableColumn column = tbPasAnakCppt.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(80);
            } else if (i == 2) {
                column.setPreferredWidth(60);
            } else if (i == 3) {
                column.setPreferredWidth(220);
            } else if (i == 4) {
                column.setPreferredWidth(110);
            } else if (i == 5) {
                column.setPreferredWidth(250);
            } else if (i == 6) {
                column.setPreferredWidth(190);
            } else if (i == 7) {
                column.setPreferredWidth(200);
            }  
        }
        tbPasAnakCppt.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbPasAnakCppt.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbPasAnakCppt.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbPasAnakCppt.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        
        tabMode16=new DefaultTableModel(null,new String[]{
            "No.", "Tgl. MRS", "No. RM", "Nama Pasien", "Cara Bayar", "Alamat", "Rg. Rawat Terakhir", "Nama DPJP"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbPasAnakAskep.setModel(tabMode16);
        tbPasAnakAskep.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPasAnakAskep.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 8; i++) {
            TableColumn column = tbPasAnakAskep.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(80);
            } else if (i == 2) {
                column.setPreferredWidth(60);
            } else if (i == 3) {
                column.setPreferredWidth(220);
            } else if (i == 4) {
                column.setPreferredWidth(110);
            } else if (i == 5) {
                column.setPreferredWidth(250);
            } else if (i == 6) {
                column.setPreferredWidth(190);
            } else if (i == 7) {
                column.setPreferredWidth(200);
            }  
        }
        tbPasAnakAskep.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbPasAnakAskep.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbPasAnakAskep.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbPasAnakAskep.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        
        tabMode6=new DefaultTableModel(null,new String[]{
            "No.", "NIP/NR", "Nama Perawat", "CPPT", "Asesmen Keperawatan Anak", "Unit Kerja"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbPerawatAnak1.setModel(tabMode6);
        tbPerawatAnak1.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPerawatAnak1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 6; i++) {
            TableColumn column = tbPerawatAnak1.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(130);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(50);
            } else if (i == 4) {
                column.setPreferredWidth(160);
            } else if (i == 5) {
                column.setPreferredWidth(240);
            } 
        }
        tbPerawatAnak1.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbPerawatAnak1.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbPerawatAnak1.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbPerawatAnak1.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        
        tabMode17=new DefaultTableModel(null,new String[]{
            "No.", "Tgl. MRS", "No. RM", "Nama Pasien", "Cara Bayar", "Alamat", "Rg. Rawat Terakhir", "Nama DPJP"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbPasRanapAnakCppt.setModel(tabMode17);
        tbPasRanapAnakCppt.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPasRanapAnakCppt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 8; i++) {
            TableColumn column = tbPasRanapAnakCppt.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(80);
            } else if (i == 2) {
                column.setPreferredWidth(60);
            } else if (i == 3) {
                column.setPreferredWidth(220);
            } else if (i == 4) {
                column.setPreferredWidth(110);
            } else if (i == 5) {
                column.setPreferredWidth(250);
            } else if (i == 6) {
                column.setPreferredWidth(190);
            } else if (i == 7) {
                column.setPreferredWidth(200);
            }  
        }
        tbPasRanapAnakCppt.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbPasRanapAnakCppt.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbPasRanapAnakCppt.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbPasRanapAnakCppt.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        
        tabMode18=new DefaultTableModel(null,new String[]{
            "No.", "Tgl. MRS", "No. RM", "Nama Pasien", "Cara Bayar", "Alamat", "Rg. Rawat Terakhir", "Nama DPJP"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbPasRanapAnakAskep.setModel(tabMode18);
        tbPasRanapAnakAskep.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPasRanapAnakAskep.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 8; i++) {
            TableColumn column = tbPasRanapAnakAskep.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(80);
            } else if (i == 2) {
                column.setPreferredWidth(60);
            } else if (i == 3) {
                column.setPreferredWidth(220);
            } else if (i == 4) {
                column.setPreferredWidth(110);
            } else if (i == 5) {
                column.setPreferredWidth(250);
            } else if (i == 6) {
                column.setPreferredWidth(190);
            } else if (i == 7) {
                column.setPreferredWidth(200);
            }  
        }
        tbPasRanapAnakAskep.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbPasRanapAnakAskep.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbPasRanapAnakAskep.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbPasRanapAnakAskep.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        
        tabMode7=new DefaultTableModel(null,new String[]{
            "No.", "NIP/NR", "Nama Petugas", "Jml. CPPT PerPasien", "Ruang Perawatan"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbBidan.setModel(tabMode7);
        tbBidan.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbBidan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 5; i++) {
            TableColumn column = tbBidan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(130);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(120);
            } else if (i == 4) {
                column.setPreferredWidth(120);
            }            
        }
        tbBidan.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbBidan.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbBidan.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        
        tabMode19=new DefaultTableModel(null,new String[]{
            "No.", "Tgl. MRS", "No. RM", "Nama Pasien", "Cara Bayar", "Alamat", "Rg. Rawat Terakhir", "Nama DPJP"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbPasienBidanRanap.setModel(tabMode19);
        tbPasienBidanRanap.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPasienBidanRanap.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 8; i++) {
            TableColumn column = tbPasienBidanRanap.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(80);
            } else if (i == 2) {
                column.setPreferredWidth(60);
            } else if (i == 3) {
                column.setPreferredWidth(220);
            } else if (i == 4) {
                column.setPreferredWidth(110);
            } else if (i == 5) {
                column.setPreferredWidth(250);
            } else if (i == 6) {
                column.setPreferredWidth(190);
            } else if (i == 7) {
                column.setPreferredWidth(200);
            }  
        }
        tbPasienBidanRanap.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbPasienBidanRanap.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbPasienBidanRanap.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbPasienBidanRanap.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        
        tabMode20=new DefaultTableModel(null,new String[]{
            "No.", "NIP/NR", "Nama Dokter", "Spesialis", "Sbg. DPJP", "AsMed Bedah", "AsMed Dewasa", "AsMed Anak", "AsMed Perinatologi", "CPPT", "e-Resep", "Resume Medis",
            "Pemberian Inf. Edukasi", "Surat Konsul", "Surat Jwbn. Konsul", "Perstjuan./Penlokaan. Tndkn.", "Portokol Kemoterapi", "Total RM Terisi", "Persentase"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbDpjp.setModel(tabMode20);
        tbDpjp.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbDpjp.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 19; i++) {
            TableColumn column = tbDpjp.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(130);
            } else if (i == 2) {
                column.setPreferredWidth(220);
            } else if (i == 3) {
                column.setPreferredWidth(160);
            } else if (i == 4) {
                column.setPreferredWidth(65);
            } else if (i == 5) {
                column.setPreferredWidth(85);
            } else if (i == 6) {
                column.setPreferredWidth(85);
            } else if (i == 7) {
                column.setPreferredWidth(85);
            } else if (i == 8) {
                column.setPreferredWidth(110);
            } else if (i == 9) {
                column.setPreferredWidth(40);
            } else if (i == 10) {
                column.setPreferredWidth(60);
            } else if (i == 11) {
                column.setPreferredWidth(80);
            } else if (i == 12) {
                column.setPreferredWidth(130);
            } else if (i == 13) {
                column.setPreferredWidth(75);
            } else if (i == 14) {
                column.setPreferredWidth(110);
            } else if (i == 15) {
                column.setPreferredWidth(155);
            } else if (i == 16) {
                column.setPreferredWidth(110);
            } else if (i == 17) {
                column.setPreferredWidth(90);
            } else if (i == 18) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbDpjp.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbDpjp.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbDpjp.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tbDpjp.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        tbDpjp.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        tbDpjp.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
        tbDpjp.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
        tbDpjp.getColumnModel().getColumn(9).setCellRenderer(centerRenderer);
        tbDpjp.getColumnModel().getColumn(10).setCellRenderer(centerRenderer);
        tbDpjp.getColumnModel().getColumn(11).setCellRenderer(centerRenderer);
        tbDpjp.getColumnModel().getColumn(12).setCellRenderer(centerRenderer);
        tbDpjp.getColumnModel().getColumn(13).setCellRenderer(centerRenderer);
        tbDpjp.getColumnModel().getColumn(14).setCellRenderer(centerRenderer);
        tbDpjp.getColumnModel().getColumn(15).setCellRenderer(centerRenderer);
        tbDpjp.getColumnModel().getColumn(16).setCellRenderer(centerRenderer);
        tbDpjp.getColumnModel().getColumn(17).setCellRenderer(centerRenderer);
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
        MnDownloadData1 = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        MnDownloadData2 = new javax.swing.JMenuItem();
        jPopupMenu3 = new javax.swing.JPopupMenu();
        MnDownloadData3 = new javax.swing.JMenuItem();
        jPopupMenu4 = new javax.swing.JPopupMenu();
        MnDownloadData4 = new javax.swing.JMenuItem();
        jPopupMenu5 = new javax.swing.JPopupMenu();
        MnDownloadData5 = new javax.swing.JMenuItem();
        jPopupMenu6 = new javax.swing.JPopupMenu();
        MnDownloadData6 = new javax.swing.JMenuItem();
        jPopupMenu7 = new javax.swing.JPopupMenu();
        MnDownloadData7 = new javax.swing.JMenuItem();
        jPopupMenu8 = new javax.swing.JPopupMenu();
        MnDownloadData8 = new javax.swing.JMenuItem();
        jPopupMenu9 = new javax.swing.JPopupMenu();
        MnDownloadData9 = new javax.swing.JMenuItem();
        jPopupMenu10 = new javax.swing.JPopupMenu();
        MnDownloadData10 = new javax.swing.JMenuItem();
        jPopupMenu11 = new javax.swing.JPopupMenu();
        MnDownloadData11 = new javax.swing.JMenuItem();
        jPopupMenu12 = new javax.swing.JPopupMenu();
        MnDownloadData12 = new javax.swing.JMenuItem();
        jPopupMenu13 = new javax.swing.JPopupMenu();
        MnDownloadData13 = new javax.swing.JMenuItem();
        jPopupMenu14 = new javax.swing.JPopupMenu();
        MnDownloadData14 = new javax.swing.JMenuItem();
        jPopupMenu15 = new javax.swing.JPopupMenu();
        MnDownloadData15 = new javax.swing.JMenuItem();
        jPopupMenu16 = new javax.swing.JPopupMenu();
        MnDownloadData16 = new javax.swing.JMenuItem();
        jPopupMenu17 = new javax.swing.JPopupMenu();
        MnDownloadData17 = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        internalFrame2 = new widget.InternalFrame();
        TabRM = new javax.swing.JTabbedPane();
        panelGlass9 = new widget.panelisi();
        Scroll = new widget.ScrollPane();
        tbPerawatIgd = new widget.Table();
        panelGlass10 = new widget.panelisi();
        panelGlass11 = new widget.panelisi();
        label_key1 = new widget.Label();
        TnipPerawatIgd = new widget.TextBox();
        label_key2 = new widget.Label();
        TnmPerawatIgd = new widget.TextBox();
        Scroll10 = new widget.ScrollPane();
        tbPasienIgd = new widget.Table();
        panelGlass12 = new widget.panelisi();
        Scroll1 = new widget.ScrollPane();
        tbBidanPonek = new widget.Table();
        panelGlass13 = new widget.panelisi();
        panelGlass14 = new widget.panelisi();
        label_key3 = new widget.Label();
        TnipBidanPonek = new widget.TextBox();
        label_key4 = new widget.Label();
        TnmBidanPonek = new widget.TextBox();
        Scroll11 = new widget.ScrollPane();
        tbPasienPonek = new widget.Table();
        panelGlass15 = new widget.panelisi();
        Scroll2 = new widget.ScrollPane();
        tbPerawatDewasa = new widget.Table();
        panelGlass16 = new widget.panelisi();
        panelGlass17 = new widget.panelisi();
        label_key5 = new widget.Label();
        TnipPerawatDws = new widget.TextBox();
        label_key6 = new widget.Label();
        TnmPerawatDws = new widget.TextBox();
        TabPerawatDws = new javax.swing.JTabbedPane();
        Scroll17 = new widget.ScrollPane();
        tbPasCpptDws = new widget.Table();
        Scroll18 = new widget.ScrollPane();
        tbPasAskepDws = new widget.Table();
        panelGlass18 = new widget.panelisi();
        Scroll3 = new widget.ScrollPane();
        tbFarmasi = new widget.Table();
        panelGlass19 = new widget.panelisi();
        panelGlass20 = new widget.panelisi();
        label_key7 = new widget.Label();
        TnipPetFarmasi = new widget.TextBox();
        label_key8 = new widget.Label();
        TnmPetFarmasi = new widget.TextBox();
        Scroll12 = new widget.ScrollPane();
        tbPasienFarmasi = new widget.Table();
        panelGlass21 = new widget.panelisi();
        Scroll4 = new widget.ScrollPane();
        tbNutrisionis = new widget.Table();
        panelGlass22 = new widget.panelisi();
        panelGlass23 = new widget.panelisi();
        label_key9 = new widget.Label();
        TnipPetNutri = new widget.TextBox();
        label_key10 = new widget.Label();
        TnmPetNutri = new widget.TextBox();
        TabPetugasNutri = new javax.swing.JTabbedPane();
        Scroll19 = new widget.ScrollPane();
        tbPasCpptNutri = new widget.Table();
        Scroll20 = new widget.ScrollPane();
        tbPasAsuhan = new widget.Table();
        panelGlass24 = new widget.panelisi();
        Scroll5 = new widget.ScrollPane();
        tbPerawatAnak = new widget.Table();
        panelGlass25 = new widget.panelisi();
        panelGlass26 = new widget.panelisi();
        label_key11 = new widget.Label();
        TnipPerawatAnak = new widget.TextBox();
        label_key12 = new widget.Label();
        TnmPerawatAnak = new widget.TextBox();
        TabPerawatAnak = new javax.swing.JTabbedPane();
        Scroll21 = new widget.ScrollPane();
        tbPasAnakCppt = new widget.Table();
        Scroll22 = new widget.ScrollPane();
        tbPasAnakAskep = new widget.Table();
        panelGlass27 = new widget.panelisi();
        Scroll9 = new widget.ScrollPane();
        tbPerawatAnak1 = new widget.Table();
        panelGlass28 = new widget.panelisi();
        panelGlass29 = new widget.panelisi();
        label_key13 = new widget.Label();
        TnipPerawatRanapAnak = new widget.TextBox();
        label_key14 = new widget.Label();
        TnmPerawatRanapAnak = new widget.TextBox();
        TabPerawatRanapAnak = new javax.swing.JTabbedPane();
        Scroll23 = new widget.ScrollPane();
        tbPasRanapAnakCppt = new widget.Table();
        Scroll24 = new widget.ScrollPane();
        tbPasRanapAnakAskep = new widget.Table();
        panelGlass30 = new widget.panelisi();
        Scroll6 = new widget.ScrollPane();
        tbBidan = new widget.Table();
        panelGlass31 = new widget.panelisi();
        panelGlass32 = new widget.panelisi();
        label_key15 = new widget.Label();
        TnipBidanRanap = new widget.TextBox();
        label_key16 = new widget.Label();
        TnmBidanRanap = new widget.TextBox();
        Scroll13 = new widget.ScrollPane();
        tbPasienBidanRanap = new widget.Table();
        Scroll7 = new widget.ScrollPane();
        tbDpjp = new widget.Table();
        Scroll8 = new widget.ScrollPane();
        tbPerawatIgd8 = new widget.Table();
        panelGlass8 = new widget.panelisi();
        jLabel5 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel17 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        label_key = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        jLabel6 = new widget.Label();

        jPopupMenu1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jPopupMenu1.setName("jPopupMenu1"); // NOI18N
        jPopupMenu1.setPreferredSize(new java.awt.Dimension(140, 28));

        MnDownloadData1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDownloadData1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/export-excel.png"))); // NOI18N
        MnDownloadData1.setText("Download Data");
        MnDownloadData1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDownloadData1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDownloadData1.setIconTextGap(5);
        MnDownloadData1.setName("MnDownloadData1"); // NOI18N
        MnDownloadData1.setPreferredSize(new java.awt.Dimension(140, 28));
        MnDownloadData1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDownloadData1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnDownloadData1);

        jPopupMenu2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jPopupMenu2.setName("jPopupMenu2"); // NOI18N
        jPopupMenu2.setPreferredSize(new java.awt.Dimension(140, 28));

        MnDownloadData2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDownloadData2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/export-excel.png"))); // NOI18N
        MnDownloadData2.setText("Download Data");
        MnDownloadData2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDownloadData2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDownloadData2.setIconTextGap(5);
        MnDownloadData2.setName("MnDownloadData2"); // NOI18N
        MnDownloadData2.setPreferredSize(new java.awt.Dimension(140, 28));
        MnDownloadData2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDownloadData2ActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnDownloadData2);

        jPopupMenu3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jPopupMenu3.setName("jPopupMenu3"); // NOI18N
        jPopupMenu3.setPreferredSize(new java.awt.Dimension(140, 28));

        MnDownloadData3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDownloadData3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/export-excel.png"))); // NOI18N
        MnDownloadData3.setText("Download Data");
        MnDownloadData3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDownloadData3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDownloadData3.setIconTextGap(5);
        MnDownloadData3.setName("MnDownloadData3"); // NOI18N
        MnDownloadData3.setPreferredSize(new java.awt.Dimension(140, 28));
        MnDownloadData3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDownloadData3ActionPerformed(evt);
            }
        });
        jPopupMenu3.add(MnDownloadData3);

        jPopupMenu4.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jPopupMenu4.setName("jPopupMenu4"); // NOI18N
        jPopupMenu4.setPreferredSize(new java.awt.Dimension(140, 28));

        MnDownloadData4.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDownloadData4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/export-excel.png"))); // NOI18N
        MnDownloadData4.setText("Download Data");
        MnDownloadData4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDownloadData4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDownloadData4.setIconTextGap(5);
        MnDownloadData4.setName("MnDownloadData4"); // NOI18N
        MnDownloadData4.setPreferredSize(new java.awt.Dimension(140, 28));
        MnDownloadData4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDownloadData4ActionPerformed(evt);
            }
        });
        jPopupMenu4.add(MnDownloadData4);

        jPopupMenu5.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jPopupMenu5.setName("jPopupMenu5"); // NOI18N
        jPopupMenu5.setPreferredSize(new java.awt.Dimension(140, 28));

        MnDownloadData5.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDownloadData5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/export-excel.png"))); // NOI18N
        MnDownloadData5.setText("Download Data");
        MnDownloadData5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDownloadData5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDownloadData5.setIconTextGap(5);
        MnDownloadData5.setName("MnDownloadData5"); // NOI18N
        MnDownloadData5.setPreferredSize(new java.awt.Dimension(140, 28));
        MnDownloadData5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDownloadData5ActionPerformed(evt);
            }
        });
        jPopupMenu5.add(MnDownloadData5);

        jPopupMenu6.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jPopupMenu6.setName("jPopupMenu6"); // NOI18N
        jPopupMenu6.setPreferredSize(new java.awt.Dimension(140, 28));

        MnDownloadData6.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDownloadData6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/export-excel.png"))); // NOI18N
        MnDownloadData6.setText("Download Data");
        MnDownloadData6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDownloadData6.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDownloadData6.setIconTextGap(5);
        MnDownloadData6.setName("MnDownloadData6"); // NOI18N
        MnDownloadData6.setPreferredSize(new java.awt.Dimension(140, 28));
        MnDownloadData6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDownloadData6ActionPerformed(evt);
            }
        });
        jPopupMenu6.add(MnDownloadData6);

        jPopupMenu7.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jPopupMenu7.setName("jPopupMenu7"); // NOI18N
        jPopupMenu7.setPreferredSize(new java.awt.Dimension(140, 28));

        MnDownloadData7.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDownloadData7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/export-excel.png"))); // NOI18N
        MnDownloadData7.setText("Download Data");
        MnDownloadData7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDownloadData7.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDownloadData7.setIconTextGap(5);
        MnDownloadData7.setName("MnDownloadData7"); // NOI18N
        MnDownloadData7.setPreferredSize(new java.awt.Dimension(140, 28));
        MnDownloadData7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDownloadData7ActionPerformed(evt);
            }
        });
        jPopupMenu7.add(MnDownloadData7);

        jPopupMenu8.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jPopupMenu8.setName("jPopupMenu8"); // NOI18N
        jPopupMenu8.setPreferredSize(new java.awt.Dimension(140, 28));

        MnDownloadData8.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDownloadData8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/export-excel.png"))); // NOI18N
        MnDownloadData8.setText("Download Data");
        MnDownloadData8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDownloadData8.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDownloadData8.setIconTextGap(5);
        MnDownloadData8.setName("MnDownloadData8"); // NOI18N
        MnDownloadData8.setPreferredSize(new java.awt.Dimension(140, 28));
        MnDownloadData8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDownloadData8ActionPerformed(evt);
            }
        });
        jPopupMenu8.add(MnDownloadData8);

        jPopupMenu9.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jPopupMenu9.setName("jPopupMenu9"); // NOI18N
        jPopupMenu9.setPreferredSize(new java.awt.Dimension(140, 28));

        MnDownloadData9.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDownloadData9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/export-excel.png"))); // NOI18N
        MnDownloadData9.setText("Download Data");
        MnDownloadData9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDownloadData9.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDownloadData9.setIconTextGap(5);
        MnDownloadData9.setName("MnDownloadData9"); // NOI18N
        MnDownloadData9.setPreferredSize(new java.awt.Dimension(140, 28));
        MnDownloadData9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDownloadData9ActionPerformed(evt);
            }
        });
        jPopupMenu9.add(MnDownloadData9);

        jPopupMenu10.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jPopupMenu10.setName("jPopupMenu10"); // NOI18N
        jPopupMenu10.setPreferredSize(new java.awt.Dimension(140, 28));

        MnDownloadData10.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDownloadData10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/export-excel.png"))); // NOI18N
        MnDownloadData10.setText("Download Data");
        MnDownloadData10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDownloadData10.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDownloadData10.setIconTextGap(5);
        MnDownloadData10.setName("MnDownloadData10"); // NOI18N
        MnDownloadData10.setPreferredSize(new java.awt.Dimension(140, 28));
        MnDownloadData10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDownloadData10ActionPerformed(evt);
            }
        });
        jPopupMenu10.add(MnDownloadData10);

        jPopupMenu11.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jPopupMenu11.setName("jPopupMenu11"); // NOI18N
        jPopupMenu11.setPreferredSize(new java.awt.Dimension(140, 28));

        MnDownloadData11.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDownloadData11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/export-excel.png"))); // NOI18N
        MnDownloadData11.setText("Download Data");
        MnDownloadData11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDownloadData11.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDownloadData11.setIconTextGap(5);
        MnDownloadData11.setName("MnDownloadData11"); // NOI18N
        MnDownloadData11.setPreferredSize(new java.awt.Dimension(140, 28));
        MnDownloadData11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDownloadData11ActionPerformed(evt);
            }
        });
        jPopupMenu11.add(MnDownloadData11);

        jPopupMenu12.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jPopupMenu12.setName("jPopupMenu12"); // NOI18N
        jPopupMenu12.setPreferredSize(new java.awt.Dimension(140, 28));

        MnDownloadData12.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDownloadData12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/export-excel.png"))); // NOI18N
        MnDownloadData12.setText("Download Data");
        MnDownloadData12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDownloadData12.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDownloadData12.setIconTextGap(5);
        MnDownloadData12.setName("MnDownloadData12"); // NOI18N
        MnDownloadData12.setPreferredSize(new java.awt.Dimension(140, 28));
        MnDownloadData12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDownloadData12ActionPerformed(evt);
            }
        });
        jPopupMenu12.add(MnDownloadData12);

        jPopupMenu13.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jPopupMenu13.setName("jPopupMenu13"); // NOI18N
        jPopupMenu13.setPreferredSize(new java.awt.Dimension(140, 28));

        MnDownloadData13.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDownloadData13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/export-excel.png"))); // NOI18N
        MnDownloadData13.setText("Download Data");
        MnDownloadData13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDownloadData13.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDownloadData13.setIconTextGap(5);
        MnDownloadData13.setName("MnDownloadData13"); // NOI18N
        MnDownloadData13.setPreferredSize(new java.awt.Dimension(140, 28));
        MnDownloadData13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDownloadData13ActionPerformed(evt);
            }
        });
        jPopupMenu13.add(MnDownloadData13);

        jPopupMenu14.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jPopupMenu14.setName("jPopupMenu14"); // NOI18N
        jPopupMenu14.setPreferredSize(new java.awt.Dimension(140, 28));

        MnDownloadData14.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDownloadData14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/export-excel.png"))); // NOI18N
        MnDownloadData14.setText("Download Data");
        MnDownloadData14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDownloadData14.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDownloadData14.setIconTextGap(5);
        MnDownloadData14.setName("MnDownloadData14"); // NOI18N
        MnDownloadData14.setPreferredSize(new java.awt.Dimension(140, 28));
        MnDownloadData14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDownloadData14ActionPerformed(evt);
            }
        });
        jPopupMenu14.add(MnDownloadData14);

        jPopupMenu15.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jPopupMenu15.setName("jPopupMenu15"); // NOI18N
        jPopupMenu15.setPreferredSize(new java.awt.Dimension(140, 28));

        MnDownloadData15.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDownloadData15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/export-excel.png"))); // NOI18N
        MnDownloadData15.setText("Download Data");
        MnDownloadData15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDownloadData15.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDownloadData15.setIconTextGap(5);
        MnDownloadData15.setName("MnDownloadData15"); // NOI18N
        MnDownloadData15.setPreferredSize(new java.awt.Dimension(140, 28));
        MnDownloadData15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDownloadData15ActionPerformed(evt);
            }
        });
        jPopupMenu15.add(MnDownloadData15);

        jPopupMenu16.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jPopupMenu16.setName("jPopupMenu16"); // NOI18N
        jPopupMenu16.setPreferredSize(new java.awt.Dimension(140, 28));

        MnDownloadData16.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDownloadData16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/export-excel.png"))); // NOI18N
        MnDownloadData16.setText("Download Data");
        MnDownloadData16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDownloadData16.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDownloadData16.setIconTextGap(5);
        MnDownloadData16.setName("MnDownloadData16"); // NOI18N
        MnDownloadData16.setPreferredSize(new java.awt.Dimension(140, 28));
        MnDownloadData16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDownloadData16ActionPerformed(evt);
            }
        });
        jPopupMenu16.add(MnDownloadData16);

        jPopupMenu17.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jPopupMenu17.setName("jPopupMenu17"); // NOI18N
        jPopupMenu17.setPreferredSize(new java.awt.Dimension(140, 28));

        MnDownloadData17.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDownloadData17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/export-excel.png"))); // NOI18N
        MnDownloadData17.setText("Download Data");
        MnDownloadData17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDownloadData17.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDownloadData17.setIconTextGap(5);
        MnDownloadData17.setName("MnDownloadData17"); // NOI18N
        MnDownloadData17.setPreferredSize(new java.awt.Dimension(140, 28));
        MnDownloadData17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDownloadData17ActionPerformed(evt);
            }
        });
        jPopupMenu17.add(MnDownloadData17);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Sampling Pemanfaatan e-Rekam Medis ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        TabRM.setBackground(new java.awt.Color(250, 255, 245));
        TabRM.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 235, 225)));
        TabRM.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TabRM.setName("TabRM"); // NOI18N
        TabRM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRMMouseClicked(evt);
            }
        });

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass9.setLayout(new java.awt.GridLayout(1, 2));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbPerawatIgd.setComponentPopupMenu(jPopupMenu1);
        tbPerawatIgd.setName("tbPerawatIgd"); // NOI18N
        tbPerawatIgd.getTableHeader().setReorderingAllowed(false);
        tbPerawatIgd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPerawatIgdMouseClicked(evt);
            }
        });
        tbPerawatIgd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPerawatIgdKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbPerawatIgd);

        panelGlass9.add(Scroll);

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass10.setLayout(new java.awt.BorderLayout());

        panelGlass11.setName("panelGlass11"); // NOI18N
        panelGlass11.setPreferredSize(new java.awt.Dimension(55, 45));
        panelGlass11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        label_key1.setForeground(new java.awt.Color(0, 0, 0));
        label_key1.setText("NIP / NR : ");
        label_key1.setName("label_key1"); // NOI18N
        label_key1.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass11.add(label_key1);

        TnipPerawatIgd.setEditable(false);
        TnipPerawatIgd.setForeground(new java.awt.Color(0, 0, 0));
        TnipPerawatIgd.setName("TnipPerawatIgd"); // NOI18N
        TnipPerawatIgd.setPreferredSize(new java.awt.Dimension(130, 23));
        panelGlass11.add(TnipPerawatIgd);

        label_key2.setForeground(new java.awt.Color(0, 0, 0));
        label_key2.setText("Nama Perawat :");
        label_key2.setName("label_key2"); // NOI18N
        label_key2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass11.add(label_key2);

        TnmPerawatIgd.setEditable(false);
        TnmPerawatIgd.setForeground(new java.awt.Color(0, 0, 0));
        TnmPerawatIgd.setName("TnmPerawatIgd"); // NOI18N
        TnmPerawatIgd.setPreferredSize(new java.awt.Dimension(260, 23));
        panelGlass11.add(TnmPerawatIgd);

        panelGlass10.add(panelGlass11, java.awt.BorderLayout.PAGE_START);

        Scroll10.setName("Scroll10"); // NOI18N
        Scroll10.setOpaque(true);

        tbPasienIgd.setComponentPopupMenu(jPopupMenu2);
        tbPasienIgd.setName("tbPasienIgd"); // NOI18N
        tbPasienIgd.getTableHeader().setReorderingAllowed(false);
        Scroll10.setViewportView(tbPasienIgd);

        panelGlass10.add(Scroll10, java.awt.BorderLayout.CENTER);

        panelGlass9.add(panelGlass10);

        TabRM.addTab("Perawat IGD", panelGlass9);

        panelGlass12.setName("panelGlass12"); // NOI18N
        panelGlass12.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass12.setLayout(new java.awt.GridLayout(1, 2));

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbBidanPonek.setComponentPopupMenu(jPopupMenu3);
        tbBidanPonek.setName("tbBidanPonek"); // NOI18N
        tbBidanPonek.getTableHeader().setReorderingAllowed(false);
        tbBidanPonek.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbBidanPonekMouseClicked(evt);
            }
        });
        tbBidanPonek.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbBidanPonekKeyPressed(evt);
            }
        });
        Scroll1.setViewportView(tbBidanPonek);

        panelGlass12.add(Scroll1);

        panelGlass13.setName("panelGlass13"); // NOI18N
        panelGlass13.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass13.setLayout(new java.awt.BorderLayout());

        panelGlass14.setName("panelGlass14"); // NOI18N
        panelGlass14.setPreferredSize(new java.awt.Dimension(55, 45));
        panelGlass14.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        label_key3.setForeground(new java.awt.Color(0, 0, 0));
        label_key3.setText("NIP / NR : ");
        label_key3.setName("label_key3"); // NOI18N
        label_key3.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass14.add(label_key3);

        TnipBidanPonek.setEditable(false);
        TnipBidanPonek.setForeground(new java.awt.Color(0, 0, 0));
        TnipBidanPonek.setName("TnipBidanPonek"); // NOI18N
        TnipBidanPonek.setPreferredSize(new java.awt.Dimension(130, 23));
        panelGlass14.add(TnipBidanPonek);

        label_key4.setForeground(new java.awt.Color(0, 0, 0));
        label_key4.setText("Nama Bidan :");
        label_key4.setName("label_key4"); // NOI18N
        label_key4.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass14.add(label_key4);

        TnmBidanPonek.setEditable(false);
        TnmBidanPonek.setForeground(new java.awt.Color(0, 0, 0));
        TnmBidanPonek.setName("TnmBidanPonek"); // NOI18N
        TnmBidanPonek.setPreferredSize(new java.awt.Dimension(260, 23));
        panelGlass14.add(TnmBidanPonek);

        panelGlass13.add(panelGlass14, java.awt.BorderLayout.PAGE_START);

        Scroll11.setName("Scroll11"); // NOI18N
        Scroll11.setOpaque(true);

        tbPasienPonek.setComponentPopupMenu(jPopupMenu4);
        tbPasienPonek.setName("tbPasienPonek"); // NOI18N
        tbPasienPonek.getTableHeader().setReorderingAllowed(false);
        Scroll11.setViewportView(tbPasienPonek);

        panelGlass13.add(Scroll11, java.awt.BorderLayout.CENTER);

        panelGlass12.add(panelGlass13);

        TabRM.addTab("Bidan Ponek", panelGlass12);

        panelGlass15.setName("panelGlass15"); // NOI18N
        panelGlass15.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass15.setLayout(new java.awt.GridLayout(1, 2));

        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);

        tbPerawatDewasa.setComponentPopupMenu(jPopupMenu5);
        tbPerawatDewasa.setName("tbPerawatDewasa"); // NOI18N
        tbPerawatDewasa.getTableHeader().setReorderingAllowed(false);
        tbPerawatDewasa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPerawatDewasaMouseClicked(evt);
            }
        });
        tbPerawatDewasa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPerawatDewasaKeyPressed(evt);
            }
        });
        Scroll2.setViewportView(tbPerawatDewasa);

        panelGlass15.add(Scroll2);

        panelGlass16.setName("panelGlass16"); // NOI18N
        panelGlass16.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass16.setLayout(new java.awt.BorderLayout());

        panelGlass17.setName("panelGlass17"); // NOI18N
        panelGlass17.setPreferredSize(new java.awt.Dimension(55, 45));
        panelGlass17.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        label_key5.setForeground(new java.awt.Color(0, 0, 0));
        label_key5.setText("NIP / NR : ");
        label_key5.setName("label_key5"); // NOI18N
        label_key5.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass17.add(label_key5);

        TnipPerawatDws.setEditable(false);
        TnipPerawatDws.setForeground(new java.awt.Color(0, 0, 0));
        TnipPerawatDws.setName("TnipPerawatDws"); // NOI18N
        TnipPerawatDws.setPreferredSize(new java.awt.Dimension(130, 23));
        panelGlass17.add(TnipPerawatDws);

        label_key6.setForeground(new java.awt.Color(0, 0, 0));
        label_key6.setText("Nama Perawat :");
        label_key6.setName("label_key6"); // NOI18N
        label_key6.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass17.add(label_key6);

        TnmPerawatDws.setEditable(false);
        TnmPerawatDws.setForeground(new java.awt.Color(0, 0, 0));
        TnmPerawatDws.setName("TnmPerawatDws"); // NOI18N
        TnmPerawatDws.setPreferredSize(new java.awt.Dimension(260, 23));
        panelGlass17.add(TnmPerawatDws);

        panelGlass16.add(panelGlass17, java.awt.BorderLayout.PAGE_START);

        TabPerawatDws.setBackground(new java.awt.Color(250, 255, 245));
        TabPerawatDws.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 235, 225)));
        TabPerawatDws.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TabPerawatDws.setName("TabPerawatDws"); // NOI18N
        TabPerawatDws.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabPerawatDwsMouseClicked(evt);
            }
        });

        Scroll17.setName("Scroll17"); // NOI18N
        Scroll17.setOpaque(true);

        tbPasCpptDws.setComponentPopupMenu(jPopupMenu6);
        tbPasCpptDws.setName("tbPasCpptDws"); // NOI18N
        tbPasCpptDws.getTableHeader().setReorderingAllowed(false);
        Scroll17.setViewportView(tbPasCpptDws);

        TabPerawatDws.addTab("CPPT", Scroll17);

        Scroll18.setName("Scroll18"); // NOI18N
        Scroll18.setOpaque(true);

        tbPasAskepDws.setComponentPopupMenu(jPopupMenu6);
        tbPasAskepDws.setName("tbPasAskepDws"); // NOI18N
        tbPasAskepDws.getTableHeader().setReorderingAllowed(false);
        Scroll18.setViewportView(tbPasAskepDws);

        TabPerawatDws.addTab("Assesmen Keperawatan Dewasa", Scroll18);

        panelGlass16.add(TabPerawatDws, java.awt.BorderLayout.CENTER);

        panelGlass15.add(panelGlass16);

        TabRM.addTab("Perawat Rg. Inap (Dewasa)", panelGlass15);

        panelGlass18.setName("panelGlass18"); // NOI18N
        panelGlass18.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass18.setLayout(new java.awt.GridLayout(1, 2));

        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);

        tbFarmasi.setComponentPopupMenu(jPopupMenu7);
        tbFarmasi.setName("tbFarmasi"); // NOI18N
        tbFarmasi.getTableHeader().setReorderingAllowed(false);
        tbFarmasi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbFarmasiMouseClicked(evt);
            }
        });
        tbFarmasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbFarmasiKeyPressed(evt);
            }
        });
        Scroll3.setViewportView(tbFarmasi);

        panelGlass18.add(Scroll3);

        panelGlass19.setName("panelGlass19"); // NOI18N
        panelGlass19.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass19.setLayout(new java.awt.BorderLayout());

        panelGlass20.setName("panelGlass20"); // NOI18N
        panelGlass20.setPreferredSize(new java.awt.Dimension(55, 45));
        panelGlass20.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        label_key7.setForeground(new java.awt.Color(0, 0, 0));
        label_key7.setText("NIP / NR : ");
        label_key7.setName("label_key7"); // NOI18N
        label_key7.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass20.add(label_key7);

        TnipPetFarmasi.setEditable(false);
        TnipPetFarmasi.setForeground(new java.awt.Color(0, 0, 0));
        TnipPetFarmasi.setName("TnipPetFarmasi"); // NOI18N
        TnipPetFarmasi.setPreferredSize(new java.awt.Dimension(130, 23));
        panelGlass20.add(TnipPetFarmasi);

        label_key8.setForeground(new java.awt.Color(0, 0, 0));
        label_key8.setText("Nama Petugas :");
        label_key8.setName("label_key8"); // NOI18N
        label_key8.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass20.add(label_key8);

        TnmPetFarmasi.setEditable(false);
        TnmPetFarmasi.setForeground(new java.awt.Color(0, 0, 0));
        TnmPetFarmasi.setName("TnmPetFarmasi"); // NOI18N
        TnmPetFarmasi.setPreferredSize(new java.awt.Dimension(260, 23));
        panelGlass20.add(TnmPetFarmasi);

        panelGlass19.add(panelGlass20, java.awt.BorderLayout.PAGE_START);

        Scroll12.setName("Scroll12"); // NOI18N
        Scroll12.setOpaque(true);

        tbPasienFarmasi.setComponentPopupMenu(jPopupMenu8);
        tbPasienFarmasi.setName("tbPasienFarmasi"); // NOI18N
        tbPasienFarmasi.getTableHeader().setReorderingAllowed(false);
        Scroll12.setViewportView(tbPasienFarmasi);

        panelGlass19.add(Scroll12, java.awt.BorderLayout.CENTER);

        panelGlass18.add(panelGlass19);

        TabRM.addTab("Petugas Farmasi", panelGlass18);

        panelGlass21.setName("panelGlass21"); // NOI18N
        panelGlass21.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass21.setLayout(new java.awt.GridLayout(1, 2));

        Scroll4.setName("Scroll4"); // NOI18N
        Scroll4.setOpaque(true);

        tbNutrisionis.setComponentPopupMenu(jPopupMenu9);
        tbNutrisionis.setName("tbNutrisionis"); // NOI18N
        tbNutrisionis.getTableHeader().setReorderingAllowed(false);
        tbNutrisionis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbNutrisionisMouseClicked(evt);
            }
        });
        tbNutrisionis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbNutrisionisKeyPressed(evt);
            }
        });
        Scroll4.setViewportView(tbNutrisionis);

        panelGlass21.add(Scroll4);

        panelGlass22.setName("panelGlass22"); // NOI18N
        panelGlass22.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass22.setLayout(new java.awt.BorderLayout());

        panelGlass23.setName("panelGlass23"); // NOI18N
        panelGlass23.setPreferredSize(new java.awt.Dimension(55, 45));
        panelGlass23.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        label_key9.setForeground(new java.awt.Color(0, 0, 0));
        label_key9.setText("NIP / NR : ");
        label_key9.setName("label_key9"); // NOI18N
        label_key9.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass23.add(label_key9);

        TnipPetNutri.setEditable(false);
        TnipPetNutri.setForeground(new java.awt.Color(0, 0, 0));
        TnipPetNutri.setName("TnipPetNutri"); // NOI18N
        TnipPetNutri.setPreferredSize(new java.awt.Dimension(130, 23));
        panelGlass23.add(TnipPetNutri);

        label_key10.setForeground(new java.awt.Color(0, 0, 0));
        label_key10.setText("Nama Petugas :");
        label_key10.setName("label_key10"); // NOI18N
        label_key10.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass23.add(label_key10);

        TnmPetNutri.setEditable(false);
        TnmPetNutri.setForeground(new java.awt.Color(0, 0, 0));
        TnmPetNutri.setName("TnmPetNutri"); // NOI18N
        TnmPetNutri.setPreferredSize(new java.awt.Dimension(260, 23));
        panelGlass23.add(TnmPetNutri);

        panelGlass22.add(panelGlass23, java.awt.BorderLayout.PAGE_START);

        TabPetugasNutri.setBackground(new java.awt.Color(250, 255, 245));
        TabPetugasNutri.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 235, 225)));
        TabPetugasNutri.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TabPetugasNutri.setName("TabPetugasNutri"); // NOI18N
        TabPetugasNutri.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabPetugasNutriMouseClicked(evt);
            }
        });

        Scroll19.setName("Scroll19"); // NOI18N
        Scroll19.setOpaque(true);

        tbPasCpptNutri.setComponentPopupMenu(jPopupMenu10);
        tbPasCpptNutri.setName("tbPasCpptNutri"); // NOI18N
        tbPasCpptNutri.getTableHeader().setReorderingAllowed(false);
        Scroll19.setViewportView(tbPasCpptNutri);

        TabPetugasNutri.addTab("CPPT", Scroll19);

        Scroll20.setName("Scroll20"); // NOI18N
        Scroll20.setOpaque(true);

        tbPasAsuhan.setComponentPopupMenu(jPopupMenu10);
        tbPasAsuhan.setName("tbPasAsuhan"); // NOI18N
        tbPasAsuhan.getTableHeader().setReorderingAllowed(false);
        Scroll20.setViewportView(tbPasAsuhan);

        TabPetugasNutri.addTab("Asuhan Gizi", Scroll20);

        panelGlass22.add(TabPetugasNutri, java.awt.BorderLayout.CENTER);

        panelGlass21.add(panelGlass22);

        TabRM.addTab("Petugas Nutrisionis/Gizi", panelGlass21);

        panelGlass24.setName("panelGlass24"); // NOI18N
        panelGlass24.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass24.setLayout(new java.awt.GridLayout(1, 2));

        Scroll5.setName("Scroll5"); // NOI18N
        Scroll5.setOpaque(true);

        tbPerawatAnak.setComponentPopupMenu(jPopupMenu11);
        tbPerawatAnak.setName("tbPerawatAnak"); // NOI18N
        tbPerawatAnak.getTableHeader().setReorderingAllowed(false);
        tbPerawatAnak.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPerawatAnakMouseClicked(evt);
            }
        });
        tbPerawatAnak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPerawatAnakKeyPressed(evt);
            }
        });
        Scroll5.setViewportView(tbPerawatAnak);

        panelGlass24.add(Scroll5);

        panelGlass25.setName("panelGlass25"); // NOI18N
        panelGlass25.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass25.setLayout(new java.awt.BorderLayout());

        panelGlass26.setName("panelGlass26"); // NOI18N
        panelGlass26.setPreferredSize(new java.awt.Dimension(55, 45));
        panelGlass26.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        label_key11.setForeground(new java.awt.Color(0, 0, 0));
        label_key11.setText("NIP / NR : ");
        label_key11.setName("label_key11"); // NOI18N
        label_key11.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass26.add(label_key11);

        TnipPerawatAnak.setEditable(false);
        TnipPerawatAnak.setForeground(new java.awt.Color(0, 0, 0));
        TnipPerawatAnak.setName("TnipPerawatAnak"); // NOI18N
        TnipPerawatAnak.setPreferredSize(new java.awt.Dimension(130, 23));
        panelGlass26.add(TnipPerawatAnak);

        label_key12.setForeground(new java.awt.Color(0, 0, 0));
        label_key12.setText("Nama Perawat :");
        label_key12.setName("label_key12"); // NOI18N
        label_key12.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass26.add(label_key12);

        TnmPerawatAnak.setEditable(false);
        TnmPerawatAnak.setForeground(new java.awt.Color(0, 0, 0));
        TnmPerawatAnak.setName("TnmPerawatAnak"); // NOI18N
        TnmPerawatAnak.setPreferredSize(new java.awt.Dimension(260, 23));
        panelGlass26.add(TnmPerawatAnak);

        panelGlass25.add(panelGlass26, java.awt.BorderLayout.PAGE_START);

        TabPerawatAnak.setBackground(new java.awt.Color(250, 255, 245));
        TabPerawatAnak.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 235, 225)));
        TabPerawatAnak.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TabPerawatAnak.setName("TabPerawatAnak"); // NOI18N
        TabPerawatAnak.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabPerawatAnakMouseClicked(evt);
            }
        });

        Scroll21.setName("Scroll21"); // NOI18N
        Scroll21.setOpaque(true);

        tbPasAnakCppt.setComponentPopupMenu(jPopupMenu12);
        tbPasAnakCppt.setName("tbPasAnakCppt"); // NOI18N
        tbPasAnakCppt.getTableHeader().setReorderingAllowed(false);
        Scroll21.setViewportView(tbPasAnakCppt);

        TabPerawatAnak.addTab("CPPT", Scroll21);

        Scroll22.setName("Scroll22"); // NOI18N
        Scroll22.setOpaque(true);

        tbPasAnakAskep.setComponentPopupMenu(jPopupMenu12);
        tbPasAnakAskep.setName("tbPasAnakAskep"); // NOI18N
        tbPasAnakAskep.getTableHeader().setReorderingAllowed(false);
        Scroll22.setViewportView(tbPasAnakAskep);

        TabPerawatAnak.addTab("Asesmen Keperawatan Anak", Scroll22);

        panelGlass25.add(TabPerawatAnak, java.awt.BorderLayout.CENTER);

        panelGlass24.add(panelGlass25);

        TabRM.addTab("Perawat Rg. Anak", panelGlass24);

        panelGlass27.setName("panelGlass27"); // NOI18N
        panelGlass27.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass27.setLayout(new java.awt.GridLayout(1, 2));

        Scroll9.setName("Scroll9"); // NOI18N
        Scroll9.setOpaque(true);

        tbPerawatAnak1.setComponentPopupMenu(jPopupMenu13);
        tbPerawatAnak1.setName("tbPerawatAnak1"); // NOI18N
        tbPerawatAnak1.getTableHeader().setReorderingAllowed(false);
        tbPerawatAnak1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPerawatAnak1MouseClicked(evt);
            }
        });
        tbPerawatAnak1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPerawatAnak1KeyPressed(evt);
            }
        });
        Scroll9.setViewportView(tbPerawatAnak1);

        panelGlass27.add(Scroll9);

        panelGlass28.setName("panelGlass28"); // NOI18N
        panelGlass28.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass28.setLayout(new java.awt.BorderLayout());

        panelGlass29.setName("panelGlass29"); // NOI18N
        panelGlass29.setPreferredSize(new java.awt.Dimension(55, 45));
        panelGlass29.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        label_key13.setForeground(new java.awt.Color(0, 0, 0));
        label_key13.setText("NIP / NR : ");
        label_key13.setName("label_key13"); // NOI18N
        label_key13.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass29.add(label_key13);

        TnipPerawatRanapAnak.setEditable(false);
        TnipPerawatRanapAnak.setForeground(new java.awt.Color(0, 0, 0));
        TnipPerawatRanapAnak.setName("TnipPerawatRanapAnak"); // NOI18N
        TnipPerawatRanapAnak.setPreferredSize(new java.awt.Dimension(130, 23));
        panelGlass29.add(TnipPerawatRanapAnak);

        label_key14.setForeground(new java.awt.Color(0, 0, 0));
        label_key14.setText("Nama Perawat :");
        label_key14.setName("label_key14"); // NOI18N
        label_key14.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass29.add(label_key14);

        TnmPerawatRanapAnak.setEditable(false);
        TnmPerawatRanapAnak.setForeground(new java.awt.Color(0, 0, 0));
        TnmPerawatRanapAnak.setName("TnmPerawatRanapAnak"); // NOI18N
        TnmPerawatRanapAnak.setPreferredSize(new java.awt.Dimension(260, 23));
        panelGlass29.add(TnmPerawatRanapAnak);

        panelGlass28.add(panelGlass29, java.awt.BorderLayout.PAGE_START);

        TabPerawatRanapAnak.setBackground(new java.awt.Color(250, 255, 245));
        TabPerawatRanapAnak.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 235, 225)));
        TabPerawatRanapAnak.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TabPerawatRanapAnak.setName("TabPerawatRanapAnak"); // NOI18N
        TabPerawatRanapAnak.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabPerawatRanapAnakMouseClicked(evt);
            }
        });

        Scroll23.setName("Scroll23"); // NOI18N
        Scroll23.setOpaque(true);

        tbPasRanapAnakCppt.setComponentPopupMenu(jPopupMenu14);
        tbPasRanapAnakCppt.setName("tbPasRanapAnakCppt"); // NOI18N
        tbPasRanapAnakCppt.getTableHeader().setReorderingAllowed(false);
        Scroll23.setViewportView(tbPasRanapAnakCppt);

        TabPerawatRanapAnak.addTab("CPPT", Scroll23);

        Scroll24.setName("Scroll24"); // NOI18N
        Scroll24.setOpaque(true);

        tbPasRanapAnakAskep.setComponentPopupMenu(jPopupMenu14);
        tbPasRanapAnakAskep.setName("tbPasRanapAnakAskep"); // NOI18N
        tbPasRanapAnakAskep.getTableHeader().setReorderingAllowed(false);
        Scroll24.setViewportView(tbPasRanapAnakAskep);

        TabPerawatRanapAnak.addTab("Asesmen Keperawatan Anak", Scroll24);

        panelGlass28.add(TabPerawatRanapAnak, java.awt.BorderLayout.CENTER);

        panelGlass27.add(panelGlass28);

        TabRM.addTab("Perawat R. Inap (Pasien Anak)", panelGlass27);

        panelGlass30.setName("panelGlass30"); // NOI18N
        panelGlass30.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass30.setLayout(new java.awt.GridLayout(1, 2));

        Scroll6.setName("Scroll6"); // NOI18N
        Scroll6.setOpaque(true);

        tbBidan.setComponentPopupMenu(jPopupMenu15);
        tbBidan.setName("tbBidan"); // NOI18N
        tbBidan.getTableHeader().setReorderingAllowed(false);
        tbBidan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbBidanMouseClicked(evt);
            }
        });
        tbBidan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbBidanKeyPressed(evt);
            }
        });
        Scroll6.setViewportView(tbBidan);

        panelGlass30.add(Scroll6);

        panelGlass31.setName("panelGlass31"); // NOI18N
        panelGlass31.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass31.setLayout(new java.awt.BorderLayout());

        panelGlass32.setName("panelGlass32"); // NOI18N
        panelGlass32.setPreferredSize(new java.awt.Dimension(55, 45));
        panelGlass32.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        label_key15.setForeground(new java.awt.Color(0, 0, 0));
        label_key15.setText("NIP / NR : ");
        label_key15.setName("label_key15"); // NOI18N
        label_key15.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass32.add(label_key15);

        TnipBidanRanap.setEditable(false);
        TnipBidanRanap.setForeground(new java.awt.Color(0, 0, 0));
        TnipBidanRanap.setName("TnipBidanRanap"); // NOI18N
        TnipBidanRanap.setPreferredSize(new java.awt.Dimension(130, 23));
        panelGlass32.add(TnipBidanRanap);

        label_key16.setForeground(new java.awt.Color(0, 0, 0));
        label_key16.setText("Nama Bidan :");
        label_key16.setName("label_key16"); // NOI18N
        label_key16.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass32.add(label_key16);

        TnmBidanRanap.setEditable(false);
        TnmBidanRanap.setForeground(new java.awt.Color(0, 0, 0));
        TnmBidanRanap.setName("TnmBidanRanap"); // NOI18N
        TnmBidanRanap.setPreferredSize(new java.awt.Dimension(260, 23));
        panelGlass32.add(TnmBidanRanap);

        panelGlass31.add(panelGlass32, java.awt.BorderLayout.PAGE_START);

        Scroll13.setName("Scroll13"); // NOI18N
        Scroll13.setOpaque(true);

        tbPasienBidanRanap.setComponentPopupMenu(jPopupMenu16);
        tbPasienBidanRanap.setName("tbPasienBidanRanap"); // NOI18N
        tbPasienBidanRanap.getTableHeader().setReorderingAllowed(false);
        Scroll13.setViewportView(tbPasienBidanRanap);

        panelGlass31.add(Scroll13, java.awt.BorderLayout.CENTER);

        panelGlass30.add(panelGlass31);

        TabRM.addTab("Bidan Rg. Inap", panelGlass30);

        Scroll7.setName("Scroll7"); // NOI18N
        Scroll7.setOpaque(true);

        tbDpjp.setComponentPopupMenu(jPopupMenu17);
        tbDpjp.setName("tbDpjp"); // NOI18N
        tbDpjp.getTableHeader().setReorderingAllowed(false);
        Scroll7.setViewportView(tbDpjp);

        TabRM.addTab("Dokter Spesialis (DPJP)", Scroll7);

        Scroll8.setName("Scroll8"); // NOI18N
        Scroll8.setOpaque(true);

        tbPerawatIgd8.setName("tbPerawatIgd8"); // NOI18N
        tbPerawatIgd8.getTableHeader().setReorderingAllowed(false);
        Scroll8.setViewportView(tbPerawatIgd8);

        TabRM.addTab("Dokter Umum", Scroll8);

        internalFrame2.add(TabRM, java.awt.BorderLayout.CENTER);

        internalFrame1.add(internalFrame2, java.awt.BorderLayout.CENTER);

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Periode Tgl. :");
        jLabel5.setName("jLabel5"); // NOI18N
        jLabel5.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass8.add(jLabel5);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "09-04-2026" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass8.add(DTPCari1);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("s.d");
        jLabel17.setName("jLabel17"); // NOI18N
        jLabel17.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass8.add(jLabel17);

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "09-04-2026" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass8.add(DTPCari2);

        label_key.setForeground(new java.awt.Color(0, 0, 0));
        label_key.setText("Key Word :");
        label_key.setName("label_key"); // NOI18N
        label_key.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass8.add(label_key);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(200, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass8.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('2');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnCari);

        BtnAll.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setText("Semua Data");
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(110, 30));
        BtnAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAllActionPerformed(evt);
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

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("Catatan : Angka dalam setiap rekam medis adalah jumlah pasien");
        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(370, 30));
        panelGlass8.add(jLabel6);

        internalFrame1.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void TabRMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRMMouseClicked
        BtnCariActionPerformed(null);
    }//GEN-LAST:event_TabRMMouseClicked

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        Valid.tabelKosong(tabMode8);
        Valid.tabelKosong(tabMode9);
        Valid.tabelKosong(tabMode10);
        Valid.tabelKosong(tabMode11);
        Valid.tabelKosong(tabMode12);
        Valid.tabelKosong(tabMode13);
        Valid.tabelKosong(tabMode14);
        Valid.tabelKosong(tabMode15);
        Valid.tabelKosong(tabMode16);
        Valid.tabelKosong(tabMode17);
        Valid.tabelKosong(tabMode18);
        Valid.tabelKosong(tabMode19);
        TnipPerawatIgd.setText("");
        TnmPerawatIgd.setText("");
        TnipBidanPonek.setText("");
        TnmBidanPonek.setText("");
        TnipPerawatDws.setText("");
        TnmPerawatDws.setText("");
        TnipPetFarmasi.setText("");
        TnmPetFarmasi.setText("");
        TnipPetNutri.setText("");
        TnmPetNutri.setText("");
        TnipPerawatAnak.setText("");
        TnmPerawatAnak.setText("");
        TnipPerawatRanapAnak.setText("");
        TnmPerawatRanapAnak.setText("");
        TnipBidanRanap.setText("");
        TnmBidanRanap.setText("");
        
        if (TabRM.getSelectedIndex() == 0) {
            label_key.setVisible(false);
            TCari.setVisible(false);
            tampilPerawatIgd();
        } else if (TabRM.getSelectedIndex() == 1) {
            label_key.setVisible(false);
            TCari.setVisible(false);
            tampilBidanPonek();
        } else if (TabRM.getSelectedIndex() == 2) {
            label_key.setVisible(true);
            TCari.setVisible(true);
            tampilPerawatDewasa();
        } else if (TabRM.getSelectedIndex() == 3) {
            label_key.setVisible(false);
            TCari.setVisible(false);
            tampilPetugasFarmasi();
        } else if (TabRM.getSelectedIndex() == 4) {
            label_key.setVisible(false);
            TCari.setVisible(false);
            tampilPetugasNutrisionis();
        } else if (TabRM.getSelectedIndex() == 5) {
            label_key.setVisible(true);
            TCari.setVisible(true);
            tampilPerawatAnak();
        } else if (TabRM.getSelectedIndex() == 6) {
            label_key.setVisible(false);
            TCari.setVisible(false);
            tampilPerawatPasienAnak();
        } else if (TabRM.getSelectedIndex() == 7) {
            label_key.setVisible(true);
            TCari.setVisible(true);
            tampilBidanRanap();
        } else if (TabRM.getSelectedIndex() == 8) {
            label_key.setVisible(false);
            TCari.setVisible(false);
            tampilDpjp();
        }
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

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        label_key.setVisible(false);
        TCari.setVisible(false);
        TCari.setText("");
        Valid.tabelKosong(tabMode8);
        TnipPerawatIgd.setText("");
        TnmPerawatIgd.setText("");
    }//GEN-LAST:event_formWindowOpened

    private void tbPerawatIgdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPerawatIgdMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getDataPerawatIgd();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbPerawatIgdMouseClicked

    private void tbPerawatIgdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPerawatIgdKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataPerawatIgd();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbPerawatIgdKeyPressed

    private void tbBidanPonekKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbBidanPonekKeyPressed
        if (tabMode1.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataBidanPonek();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbBidanPonekKeyPressed

    private void tbBidanPonekMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBidanPonekMouseClicked
        if (tabMode1.getRowCount() != 0) {
            try {
                getDataBidanPonek();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbBidanPonekMouseClicked

    private void TabPerawatDwsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabPerawatDwsMouseClicked
        if (TabPerawatDws.getSelectedIndex() == 0) {
            tampilPasien(TnipPerawatDws.getText(), "perawat dewasa cppt");
        } else {
            tampilPasien(TnipPerawatDws.getText(), "perawat dewasa askep");
        }
    }//GEN-LAST:event_TabPerawatDwsMouseClicked

    private void tbPerawatDewasaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPerawatDewasaKeyPressed
        if (tabMode2.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataPerawatDws();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbPerawatDewasaKeyPressed

    private void tbPerawatDewasaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPerawatDewasaMouseClicked
        if (tabMode2.getRowCount() != 0) {
            try {
                getDataPerawatDws();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbPerawatDewasaMouseClicked

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        BtnCariActionPerformed(null);
    }//GEN-LAST:event_BtnAllActionPerformed

    private void tbFarmasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbFarmasiKeyPressed
        if (tabMode3.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataPetugasFarmasi();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbFarmasiKeyPressed

    private void tbFarmasiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbFarmasiMouseClicked
        if (tabMode3.getRowCount() != 0) {
            try {
                getDataPetugasFarmasi();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbFarmasiMouseClicked

    private void tbNutrisionisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbNutrisionisKeyPressed
        if (tabMode4.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataPetugasNutrisionis();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbNutrisionisKeyPressed

    private void tbNutrisionisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbNutrisionisMouseClicked
        if (tabMode4.getRowCount() != 0) {
            try {
                getDataPetugasNutrisionis();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbNutrisionisMouseClicked

    private void TabPetugasNutriMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabPetugasNutriMouseClicked
        if (TabPetugasNutri.getSelectedIndex() == 0) {
            tampilPasien(TnipPetNutri.getText(), "petugas nutrisionis cppt");
        } else {
            tampilPasien(TnipPetNutri.getText(), "petugas nutrisionis asuhan");
        }
    }//GEN-LAST:event_TabPetugasNutriMouseClicked

    private void TabPerawatAnakMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabPerawatAnakMouseClicked
        if (TabPerawatAnak.getSelectedIndex() == 0) {
            tampilPasien(TnipPerawatAnak.getText(), "perawat ruang anak cppt");
        } else {
            tampilPasien(TnipPerawatAnak.getText(), "perawat ruang anak askep");
        }
    }//GEN-LAST:event_TabPerawatAnakMouseClicked

    private void tbPerawatAnakKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPerawatAnakKeyPressed
        if (tabMode5.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataPerawatRuangAnak();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbPerawatAnakKeyPressed

    private void tbPerawatAnakMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPerawatAnakMouseClicked
        if (tabMode5.getRowCount() != 0) {
            try {
                getDataPerawatRuangAnak();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbPerawatAnakMouseClicked

    private void TabPerawatRanapAnakMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabPerawatRanapAnakMouseClicked
        if (TabPerawatRanapAnak.getSelectedIndex() == 0) {
            tampilPasien(TnipPerawatRanapAnak.getText(), "perawat ranap anak cppt");
        } else {
            tampilPasien(TnipPerawatRanapAnak.getText(), "perawat ranap anak askep");
        }
    }//GEN-LAST:event_TabPerawatRanapAnakMouseClicked

    private void tbPerawatAnak1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPerawatAnak1KeyPressed
        if (tabMode6.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataPerawatRanapAnak();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbPerawatAnak1KeyPressed

    private void tbPerawatAnak1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPerawatAnak1MouseClicked
        if (tabMode6.getRowCount() != 0) {
            try {
                getDataPerawatRanapAnak();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbPerawatAnak1MouseClicked

    private void tbBidanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbBidanKeyPressed
        if (tabMode7.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataBidanRanap();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbBidanKeyPressed

    private void tbBidanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBidanMouseClicked
        if (tabMode7.getRowCount() != 0) {
            try {
                getDataBidanRanap();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbBidanMouseClicked

    private void MnDownloadData1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDownloadData1ActionPerformed
        if (tbPerawatIgd.getRowCount() != 0) {
            downloadData("perawat igd");
        } else {
            JOptionPane.showMessageDialog(null, "Data masih kosong,..!!!");
        }
    }//GEN-LAST:event_MnDownloadData1ActionPerformed

    private void MnDownloadData2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDownloadData2ActionPerformed
        if (tbPasienIgd.getRowCount() != 0) {
            downloadData("askep perawat igd");
        } else {
            JOptionPane.showMessageDialog(null, "Data masih kosong,..!!!");
        }
    }//GEN-LAST:event_MnDownloadData2ActionPerformed

    private void MnDownloadData3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDownloadData3ActionPerformed
        if (tbBidanPonek.getRowCount() != 0) {
            downloadData("bidan ponek");
        } else {
            JOptionPane.showMessageDialog(null, "Data masih kosong,..!!!");
        }
    }//GEN-LAST:event_MnDownloadData3ActionPerformed

    private void MnDownloadData4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDownloadData4ActionPerformed
        if (tbPasienPonek.getRowCount() != 0) {
            downloadData("triase ponek bidan");
        } else {
            JOptionPane.showMessageDialog(null, "Data masih kosong,..!!!");
        }
    }//GEN-LAST:event_MnDownloadData4ActionPerformed

    private void MnDownloadData5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDownloadData5ActionPerformed
        if (tbPerawatDewasa.getRowCount() != 0) {
            downloadData("perawat ranap dewasa");
        } else {
            JOptionPane.showMessageDialog(null, "Data masih kosong,..!!!");
        }
    }//GEN-LAST:event_MnDownloadData5ActionPerformed

    private void MnDownloadData6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDownloadData6ActionPerformed
        if (TabPerawatDws.getSelectedIndex() == 0) {
            if (tbPasCpptDws.getRowCount() != 0) {
                downloadData("cppt ranap dewasa");
            } else {                
                JOptionPane.showMessageDialog(null, "Data cppt pasien masih kosong,..!!!");
            }
        } else {
            if (tbPasAskepDws.getRowCount() != 0) {
                downloadData("askep ranap dewasa");
            } else {
                JOptionPane.showMessageDialog(null, "Data assesmen keperawatan rawat inap pasien masih kosong,..!!!");
            }
        }
    }//GEN-LAST:event_MnDownloadData6ActionPerformed

    private void MnDownloadData7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDownloadData7ActionPerformed
        if (tbFarmasi.getRowCount() != 0) {
            downloadData("petugas farmasi");
        } else {
            JOptionPane.showMessageDialog(null, "Data masih kosong,..!!!");
        }
    }//GEN-LAST:event_MnDownloadData7ActionPerformed

    private void MnDownloadData8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDownloadData8ActionPerformed
        if (tbPasienFarmasi.getRowCount() != 0) {
            downloadData("cppt farmasi");
        } else {
            JOptionPane.showMessageDialog(null, "Data masih kosong,..!!!");
        }
    }//GEN-LAST:event_MnDownloadData8ActionPerformed

    private void MnDownloadData9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDownloadData9ActionPerformed
        if (tbNutrisionis.getRowCount() != 0) {
            downloadData("petugas nutrisionis");
        } else {
            JOptionPane.showMessageDialog(null, "Data masih kosong,..!!!");
        }
    }//GEN-LAST:event_MnDownloadData9ActionPerformed

    private void MnDownloadData10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDownloadData10ActionPerformed
        if (TabPetugasNutri.getSelectedIndex() == 0) {
            if (tbPasCpptNutri.getRowCount() != 0) {
                downloadData("cppt nutrisionis");
            } else {                
                JOptionPane.showMessageDialog(null, "Data cppt pasien masih kosong,..!!!");
            }
        } else {
            if (tbPasAsuhan.getRowCount() != 0) {
                downloadData("asuhan nutrisionis");
            } else {
                JOptionPane.showMessageDialog(null, "Data asuhan gizi pasien masih kosong,..!!!");
            }
        }
    }//GEN-LAST:event_MnDownloadData10ActionPerformed

    private void MnDownloadData11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDownloadData11ActionPerformed
        if (tbPerawatAnak.getRowCount() != 0) {
            downloadData("perawat ruang anak");
        } else {
            JOptionPane.showMessageDialog(null, "Data masih kosong,..!!!");
        }
    }//GEN-LAST:event_MnDownloadData11ActionPerformed

    private void MnDownloadData12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDownloadData12ActionPerformed
        if (TabPerawatAnak.getSelectedIndex() == 0) {
            if (tbPasAnakCppt.getRowCount() != 0) {
                downloadData("cppt perawat rg anak");
            } else {                
                JOptionPane.showMessageDialog(null, "Data cppt pasien masih kosong,..!!!");
            }
        } else {
            if (tbPasAnakAskep.getRowCount() != 0) {
                downloadData("askep perawat rg anak");
            } else {
                JOptionPane.showMessageDialog(null, "Data assesmen keperawatan rawat inap pasien masih kosong,..!!!");
            }
        }
    }//GEN-LAST:event_MnDownloadData12ActionPerformed

    private void MnDownloadData13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDownloadData13ActionPerformed
        if (tbPerawatAnak1.getRowCount() != 0) {
            downloadData("perawat ranap anak");
        } else {
            JOptionPane.showMessageDialog(null, "Data masih kosong,..!!!");
        }
    }//GEN-LAST:event_MnDownloadData13ActionPerformed

    private void MnDownloadData14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDownloadData14ActionPerformed
        if (TabPerawatRanapAnak.getSelectedIndex() == 0) {
            if (tbPasRanapAnakCppt.getRowCount() != 0) {
                downloadData("cppt perawat ranap anak");
            } else {                
                JOptionPane.showMessageDialog(null, "Data cppt pasien masih kosong,..!!!");
            }
        } else {
            if (tbPasRanapAnakAskep.getRowCount() != 0) {
                downloadData("askep perawat ranap anak");
            } else {
                JOptionPane.showMessageDialog(null, "Data assesmen keperawatan rawat inap pasien masih kosong,..!!!");
            }
        }
    }//GEN-LAST:event_MnDownloadData14ActionPerformed

    private void MnDownloadData15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDownloadData15ActionPerformed
        if (tbBidan.getRowCount() != 0) {
            downloadData("bidan ranap");
        } else {
            JOptionPane.showMessageDialog(null, "Data masih kosong,..!!!");
        }
    }//GEN-LAST:event_MnDownloadData15ActionPerformed

    private void MnDownloadData16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDownloadData16ActionPerformed
        if (tbPasienBidanRanap.getRowCount() != 0) {
            downloadData("cppt bidan ranap");
        } else {
            JOptionPane.showMessageDialog(null, "Data masih kosong,..!!!");
        }
    }//GEN-LAST:event_MnDownloadData16ActionPerformed

    private void MnDownloadData17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDownloadData17ActionPerformed
        if (tbDpjp.getRowCount() != 0) {
            downloadData("dpjp");
        } else {
            JOptionPane.showMessageDialog(null, "Data masih kosong,..!!!");
        }
    }//GEN-LAST:event_MnDownloadData17ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMSamplingPemanfaatanRM dialog = new RMSamplingPemanfaatanRM(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCari;
    private widget.Button BtnKeluar;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private javax.swing.JMenuItem MnDownloadData1;
    private javax.swing.JMenuItem MnDownloadData10;
    private javax.swing.JMenuItem MnDownloadData11;
    private javax.swing.JMenuItem MnDownloadData12;
    private javax.swing.JMenuItem MnDownloadData13;
    private javax.swing.JMenuItem MnDownloadData14;
    private javax.swing.JMenuItem MnDownloadData15;
    private javax.swing.JMenuItem MnDownloadData16;
    private javax.swing.JMenuItem MnDownloadData17;
    private javax.swing.JMenuItem MnDownloadData2;
    private javax.swing.JMenuItem MnDownloadData3;
    private javax.swing.JMenuItem MnDownloadData4;
    private javax.swing.JMenuItem MnDownloadData5;
    private javax.swing.JMenuItem MnDownloadData6;
    private javax.swing.JMenuItem MnDownloadData7;
    private javax.swing.JMenuItem MnDownloadData8;
    private javax.swing.JMenuItem MnDownloadData9;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll10;
    private widget.ScrollPane Scroll11;
    private widget.ScrollPane Scroll12;
    private widget.ScrollPane Scroll13;
    private widget.ScrollPane Scroll17;
    private widget.ScrollPane Scroll18;
    private widget.ScrollPane Scroll19;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll20;
    private widget.ScrollPane Scroll21;
    private widget.ScrollPane Scroll22;
    private widget.ScrollPane Scroll23;
    private widget.ScrollPane Scroll24;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll4;
    private widget.ScrollPane Scroll5;
    private widget.ScrollPane Scroll6;
    private widget.ScrollPane Scroll7;
    private widget.ScrollPane Scroll8;
    private widget.ScrollPane Scroll9;
    public widget.TextBox TCari;
    private javax.swing.JTabbedPane TabPerawatAnak;
    private javax.swing.JTabbedPane TabPerawatDws;
    private javax.swing.JTabbedPane TabPerawatRanapAnak;
    private javax.swing.JTabbedPane TabPetugasNutri;
    private javax.swing.JTabbedPane TabRM;
    public widget.TextBox TnipBidanPonek;
    public widget.TextBox TnipBidanRanap;
    public widget.TextBox TnipPerawatAnak;
    public widget.TextBox TnipPerawatDws;
    public widget.TextBox TnipPerawatIgd;
    public widget.TextBox TnipPerawatRanapAnak;
    public widget.TextBox TnipPetFarmasi;
    public widget.TextBox TnipPetNutri;
    public widget.TextBox TnmBidanPonek;
    public widget.TextBox TnmBidanRanap;
    public widget.TextBox TnmPerawatAnak;
    public widget.TextBox TnmPerawatDws;
    public widget.TextBox TnmPerawatIgd;
    public widget.TextBox TnmPerawatRanapAnak;
    public widget.TextBox TnmPetFarmasi;
    public widget.TextBox TnmPetNutri;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.Label jLabel17;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu10;
    private javax.swing.JPopupMenu jPopupMenu11;
    private javax.swing.JPopupMenu jPopupMenu12;
    private javax.swing.JPopupMenu jPopupMenu13;
    private javax.swing.JPopupMenu jPopupMenu14;
    private javax.swing.JPopupMenu jPopupMenu15;
    private javax.swing.JPopupMenu jPopupMenu16;
    private javax.swing.JPopupMenu jPopupMenu17;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JPopupMenu jPopupMenu3;
    private javax.swing.JPopupMenu jPopupMenu4;
    private javax.swing.JPopupMenu jPopupMenu5;
    private javax.swing.JPopupMenu jPopupMenu6;
    private javax.swing.JPopupMenu jPopupMenu7;
    private javax.swing.JPopupMenu jPopupMenu8;
    private javax.swing.JPopupMenu jPopupMenu9;
    private widget.Label label_key;
    private widget.Label label_key1;
    private widget.Label label_key10;
    private widget.Label label_key11;
    private widget.Label label_key12;
    private widget.Label label_key13;
    private widget.Label label_key14;
    private widget.Label label_key15;
    private widget.Label label_key16;
    private widget.Label label_key2;
    private widget.Label label_key3;
    private widget.Label label_key4;
    private widget.Label label_key5;
    private widget.Label label_key6;
    private widget.Label label_key7;
    private widget.Label label_key8;
    private widget.Label label_key9;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass12;
    private widget.panelisi panelGlass13;
    private widget.panelisi panelGlass14;
    private widget.panelisi panelGlass15;
    private widget.panelisi panelGlass16;
    private widget.panelisi panelGlass17;
    private widget.panelisi panelGlass18;
    private widget.panelisi panelGlass19;
    private widget.panelisi panelGlass20;
    private widget.panelisi panelGlass21;
    private widget.panelisi panelGlass22;
    private widget.panelisi panelGlass23;
    private widget.panelisi panelGlass24;
    private widget.panelisi panelGlass25;
    private widget.panelisi panelGlass26;
    private widget.panelisi panelGlass27;
    private widget.panelisi panelGlass28;
    private widget.panelisi panelGlass29;
    private widget.panelisi panelGlass30;
    private widget.panelisi panelGlass31;
    private widget.panelisi panelGlass32;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.Table tbBidan;
    private widget.Table tbBidanPonek;
    private widget.Table tbDpjp;
    private widget.Table tbFarmasi;
    private widget.Table tbNutrisionis;
    private widget.Table tbPasAnakAskep;
    private widget.Table tbPasAnakCppt;
    private widget.Table tbPasAskepDws;
    private widget.Table tbPasAsuhan;
    private widget.Table tbPasCpptDws;
    private widget.Table tbPasCpptNutri;
    private widget.Table tbPasRanapAnakAskep;
    private widget.Table tbPasRanapAnakCppt;
    private widget.Table tbPasienBidanRanap;
    private widget.Table tbPasienFarmasi;
    private widget.Table tbPasienIgd;
    private widget.Table tbPasienPonek;
    private widget.Table tbPerawatAnak;
    private widget.Table tbPerawatAnak1;
    private widget.Table tbPerawatDewasa;
    private widget.Table tbPerawatIgd;
    private widget.Table tbPerawatIgd8;
    // End of variables declaration//GEN-END:variables

    private void tampilPerawatIgd() {     
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT pg.nik `NIP/NR`, pg.nama `Nama Perawat`, COUNT(DISTINCT pa.no_rawat) `Jml. Askep IGD PerPasien` "
                    + "FROM pegawai pg INNER JOIN penilaian_awal_keperawatan_igdrz pa ON pa.nip_perawat = pg.nik WHERE pg.nik NOT IN ('-', '--') AND "
                    + "DATE(pa.waktu_simpan) BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + "GROUP BY pg.nik, pg.nama HAVING COUNT(DISTINCT pa.no_rawat) > 0 ORDER BY COUNT(DISTINCT pa.no_rawat) desc, pg.nama");
            try {
                rs = ps.executeQuery();
                x = 1;
                while (rs.next()) {
                    tabMode.addRow(new String[]{                        
                        x + ".",
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3)
                    });
                    x++;
                }                
            } catch (Exception e) {
                System.out.println("tampilPerawatIgd() : " + e);
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
    
    private void tampilBidanPonek() {     
        Valid.tabelKosong(tabMode1);
        try {
            ps1 = koneksi.prepareStatement("SELECT pg.nik `NIP/NR`, pg.nama `Nama Petugas`, COUNT(DISTINCT tp.no_rawat) `Jml. Triase Ponek PerPasien` "
                    + "FROM pegawai pg INNER JOIN triase_ponek tp ON tp.nip_petugas  = pg.nik WHERE pg.nik NOT IN ('-', '--') AND "
                    + "DATE(tp.waktu_simpan) BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + "GROUP BY pg.nik, pg.nama HAVING COUNT(DISTINCT tp.no_rawat) > 0 ORDER BY COUNT(DISTINCT tp.no_rawat) desc, pg.nama");
            try {
                rs1 = ps1.executeQuery();
                x = 1;
                while (rs1.next()) {
                    tabMode1.addRow(new String[]{                        
                        x + ".",
                        rs1.getString(1),
                        rs1.getString(2),
                        rs1.getString(3)
                    });
                    x++;
                }                
            } catch (Exception e) {
                System.out.println("tampilBidanPonek() : " + e);
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
    
    private void tampilPerawatDewasa() {     
        Valid.tabelKosong(tabMode2);
        try {
            ps2 = koneksi.prepareStatement("SELECT z.`NIP/NR`, z.`Nama Perawat`, z.`CPPT`, z.`Assesmen Keperawatan Dewasa`, z.`Unit Kerja` "
                    + "FROM (SELECT y.nik `NIP/NR`, y.nama `Nama Perawat`, y.cppt `CPPT`, y.askep `Assesmen Keperawatan Dewasa`, y.nm_dep `Unit Kerja` "
                    + "FROM (SELECT * FROM (SELECT pg.nik, pg.nama, d.nama nm_dep, "
                    + "/* CPPT */ "
                    + "(SELECT COUNT(DISTINCT c.no_rawat) FROM cppt c "
                    + "INNER JOIN reg_periksa rp ON rp.no_rawat = c.no_rawat "
                    + "INNER JOIN penilaian_awal_keperawatan_dewasa_ranap pa1 on pa1.no_rawat=c.no_rawat WHERE "
                    + "c.nip_ppa = pg.nik AND c.status = 'Ranap' AND c.jenis_ppa = 'Perawat' and c.flag_hapus='tidak' "
                    + "AND DATE(c.waktu_simpan) BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') cppt, "
                    + "/* Askep */ "
                    + "(SELECT COUNT(DISTINCT pa.no_rawat) FROM penilaian_awal_keperawatan_dewasa_ranap pa "
                    + "INNER JOIN reg_periksa rp ON rp.no_rawat = pa.no_rawat WHERE "
                    + "pa.nip_perawat = pg.nik AND DATE(pa.waktu_simpan) BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') askep "
                    + "FROM pegawai pg INNER JOIN departemen d ON d.dep_id=pg.departemen "
                    + "WHERE pg.nik NOT IN ('-', '--') and pg.nama not like '%dr.%') x WHERE x.askep > 0) y) z where "
                    + "z.`NIP/NR` like ? or z.`Nama Perawat` like ? or z.`Unit Kerja` like ? "
                    + "ORDER BY z.`Unit Kerja`, z.`Nama Perawat`");
            try {
                ps2.setString(1, "%" + TCari.getText() + "%");
                ps2.setString(2, "%" + TCari.getText() + "%");
                ps2.setString(3, "%" + TCari.getText() + "%");
                rs2 = ps2.executeQuery();
                x = 1;
                while (rs2.next()) {
                    tabMode2.addRow(new String[]{                        
                        x + ".",
                        rs2.getString(1),
                        rs2.getString(2),
                        rs2.getString(3),
                        rs2.getString(4),
                        rs2.getString(5)
                    });
                    x++;
                }                
            } catch (Exception e) {
                System.out.println("tampilPerawatDewasa() : " + e);
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
    
    private void tampilPetugasFarmasi() {     
        Valid.tabelKosong(tabMode3);
        try {
            ps3 = koneksi.prepareStatement("SELECT pg.nik `NIP/NR`, pg.nama `Nama Petugas`, COUNT(DISTINCT c.no_rawat) `Jml. CPPT PerPasien` "
                    + "FROM pegawai pg INNER JOIN cppt c ON c.nip_ppa  = pg.nik WHERE "
                    + "pg.nik NOT IN ('-', '--') AND c.nip_ppa = pg.nik AND c.jenis_ppa = 'Apoteker' and c.flag_hapus='tidak' and "
                    + "DATE(c.waktu_simpan) BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + "GROUP BY pg.nik, pg.nama HAVING COUNT(DISTINCT c.no_rawat) > 0 ORDER BY COUNT(DISTINCT c.no_rawat) desc, pg.nama");
            try {
                rs3 = ps3.executeQuery();
                x = 1;
                while (rs3.next()) {
                    tabMode3.addRow(new String[]{                        
                        x + ".",
                        rs3.getString(1),
                        rs3.getString(2),
                        rs3.getString(3)
                    });
                    x++;
                }                
            } catch (Exception e) {
                System.out.println("tampilPetugasFarmasi() : " + e);
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
    
    private void tampilPetugasNutrisionis() {     
        Valid.tabelKosong(tabMode4);
        try {
            ps4 = koneksi.prepareStatement("SELECT z.`NIP/NR`, z.`Nama Petugas`, z.`CPPT`, z.`Asuhan Gizi`  "
                    + "FROM (SELECT y.nik `NIP/NR`, y.nama `Nama Petugas`, y.cppt `CPPT`, y.asuhan `Asuhan Gizi` "
                    + "FROM (SELECT * FROM (SELECT pg.nik, pg.nama, "
                    + "/* CPPT */ "
                    + "(SELECT COUNT(DISTINCT c.no_rawat) FROM cppt c "
                    + "INNER JOIN reg_periksa rp ON rp.no_rawat = c.no_rawat WHERE "
                    + "c.nip_ppa = pg.nik AND c.jenis_ppa = 'Nutrisionis' and c.flag_hapus='tidak' "
                    + "AND DATE(c.waktu_simpan) BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') cppt, "
                    + "/* Asuhan Gizi*/ "
                    + "(SELECT COUNT(DISTINCT ag.no_rawat) FROM asuhan_gizi_ranap ag "
                    + "INNER JOIN reg_periksa rp ON rp.no_rawat = ag.no_rawat WHERE "
                    + "ag.nip_petugas = pg.nik AND ag.tgl_asuhan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') asuhan "
                    + "FROM pegawai pg WHERE pg.nik NOT IN ('-', '--')) x WHERE x.asuhan > 0) y) z ORDER BY z.`Nama Petugas`");
            try {
                rs4 = ps4.executeQuery();
                x = 1;
                while (rs4.next()) {
                    tabMode4.addRow(new String[]{                        
                        x + ".",
                        rs4.getString(1),
                        rs4.getString(2),
                        rs4.getString(3),
                        rs4.getString(4)
                    });
                    x++;
                }                
            } catch (Exception e) {
                System.out.println("tampilPetugasNutrisionis() : " + e);
            } finally {
                if (rs4 != null) {
                    rs4.close();
                }
                if (ps4 != null) {
                    ps4.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }        
    }
    
    private void tampilPerawatAnak() {     
        Valid.tabelKosong(tabMode5);
        try {
            ps5 = koneksi.prepareStatement("SELECT z.`NIP/NR`, z.`Nama Perawat`, z.`CPPT`, z.`Asesmen Keperawatan Anak` "
                    + "FROM (SELECT y.nik `NIP/NR`, y.nama `Nama Perawat`, y.cppt `CPPT`, y.askep `Asesmen Keperawatan Anak` "
                    + "FROM (SELECT * FROM (SELECT pg.nik, pg.nama, "
                    + "/* CPPT */ "
                    + "(SELECT COUNT(DISTINCT c.no_rawat) FROM cppt c "
                    + "INNER JOIN reg_periksa rp ON rp.no_rawat = c.no_rawat "
                    + "inner join penilaian_awal_keperawatan_anak_ranap pa1 on pa1.no_rawat=c.no_rawat "
                    + "inner join pegawai pg1 on pg1.nik=c.nip_ppa "
                    + "inner join departemen d1 on d1.dep_id=pg1.departemen WHERE "
                    + "c.nip_ppa = pg.nik AND c.status = 'Ranap' AND c.jenis_ppa = 'Perawat' and c.flag_hapus='tidak' and d1.nama like '%anak%' "
                    + "AND DATE(c.waktu_simpan) BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') cppt, "
                    + "/* Askep */ "
                    + "(SELECT COUNT(DISTINCT pa.no_rawat) FROM penilaian_awal_keperawatan_anak_ranap pa "
                    + "INNER JOIN reg_periksa rp ON rp.no_rawat = pa.no_rawat "
                    + "inner join pegawai pg2 on pg2.nik=pa.nip_perawat "
                    + "inner join departemen d2 on d2.dep_id=pg2.departemen WHERE "
                    + "pa.nip_perawat = pg.nik AND d2.nama like '%anak%' "
                    + "and DATE(pa.waktu_simpan) BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') askep "
                    + "FROM pegawai pg WHERE pg.nik NOT IN ('-', '--') and pg.nama not like '%dr.%') x WHERE x.askep > 0) y) z where "
                    + "z.`NIP/NR` like ? or z.`Nama Perawat` like ? "
                    + "ORDER BY z.`Nama Perawat`");
            try {
                ps5.setString(1, "%" + TCari.getText() + "%");
                ps5.setString(2, "%" + TCari.getText() + "%");
                rs5 = ps5.executeQuery();
                x = 1;
                while (rs5.next()) {
                    tabMode5.addRow(new String[]{                        
                        x + ".",
                        rs5.getString(1),
                        rs5.getString(2),
                        rs5.getString(3),
                        rs5.getString(4)
                    });
                    x++;
                }                
            } catch (Exception e) {
                System.out.println("tampilPerawatAnak() : " + e);
            } finally {
                if (rs5 != null) {
                    rs5.close();
                }
                if (ps5 != null) {
                    ps5.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }        
    }
    
    private void tampilPerawatPasienAnak() {     
        Valid.tabelKosong(tabMode6);
        try {
            ps6 = koneksi.prepareStatement("SELECT z.`NIP/NR`, z.`Nama Perawat`, z.`CPPT`, z.`Asesmen Keperawatan Anak`, z.`Unit Kerja` "
                    + "FROM (SELECT y.nik `NIP/NR`, y.nama `Nama Perawat`, y.cppt `CPPT`, y.askep `Asesmen Keperawatan Anak`, y.unit_kerja `Unit Kerja` "
                    + "FROM (SELECT * FROM (SELECT pg.nik, pg.nama, d3.nama unit_kerja, "
                    + "/* CPPT */ "
                    + "(SELECT COUNT(DISTINCT c.no_rawat) FROM cppt c "
                    + "INNER JOIN reg_periksa rp ON rp.no_rawat = c.no_rawat "
                    + "inner join penilaian_awal_keperawatan_anak_ranap pa1 on pa1.no_rawat=c.no_rawat "
                    + "inner join pegawai pg1 on pg1.nik=c.nip_ppa "
                    + "inner join departemen d1 on d1.dep_id=pg1.departemen WHERE "
                    + "c.nip_ppa = pg.nik AND c.status = 'Ranap' AND c.jenis_ppa = 'Perawat' and c.flag_hapus='tidak' and d1.nama not like '%anak%' "
                    + "AND DATE(c.waktu_simpan) BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') cppt, "
                    + "/* Askep */ "
                    + "(SELECT COUNT(DISTINCT pa.no_rawat) FROM penilaian_awal_keperawatan_anak_ranap pa "
                    + "INNER JOIN reg_periksa rp ON rp.no_rawat = pa.no_rawat "
                    + "inner join pegawai pg2 on pg2.nik=pa.nip_perawat "
                    + "inner join departemen d2 on d2.dep_id=pg2.departemen WHERE "
                    + "pa.nip_perawat = pg.nik AND d2.nama not like '%anak%' "
                    + "and DATE(pa.waktu_simpan) BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "') askep "
                    + "FROM pegawai pg inner join departemen d3 on d3.dep_id=pg.departemen WHERE pg.nik NOT IN ('-', '--') and pg.nama not like '%dr.%') x WHERE x.askep > 0) y) z "
                    + "ORDER BY z.`Nama Perawat`");
            try {
                rs6 = ps6.executeQuery();
                x = 1;
                while (rs6.next()) {
                    tabMode6.addRow(new String[]{                        
                        x + ".",
                        rs6.getString(1),
                        rs6.getString(2),
                        rs6.getString(3),
                        rs6.getString(4),
                        rs6.getString(5)
                    });
                    x++;
                }                
            } catch (Exception e) {
                System.out.println("tampilPerawatPasienAnak() : " + e);
            } finally {
                if (rs6 != null) {
                    rs6.close();
                }
                if (ps6 != null) {
                    ps6.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }        
    }
    
    private void tampilBidanRanap() {     
        Valid.tabelKosong(tabMode7);
        try {
            ps7 = koneksi.prepareStatement("SELECT pg.nik `NIP/NR`, pg.nama `Nama Petugas`, COUNT(DISTINCT c.no_rawat) `Jml. CPPT PerPasien`, if(b.nm_gedung is null,'-',b.nm_gedung) 'Ruang Perawatan' "
                    + "FROM pegawai pg INNER JOIN cppt c ON c.nip_ppa  = pg.nik left JOIN kamar k on k.kd_kamar=c.bagian "
                    + "left JOIN bangsal b on b.kd_bangsal=k.kd_bangsal WHERE "
                    + "pg.nik NOT IN ('-', '--') AND pg.nama not like '%dr.%' and c.nip_ppa = pg.nik AND c.status = 'Ranap' AND c.jenis_ppa = 'Bidan' and c.flag_hapus='tidak' and "
                    + "DATE(c.waktu_simpan) BETWEEN ? AND ? and pg.nik like ? or "
                    + "pg.nik NOT IN ('-', '--') AND pg.nama not like '%dr.%' and c.nip_ppa = pg.nik AND c.status = 'Ranap' AND c.jenis_ppa = 'Bidan' and c.flag_hapus='tidak' and "
                    + "DATE(c.waktu_simpan) BETWEEN ? AND ? and pg.nama like ? "
                    + "GROUP BY pg.nik, pg.nama HAVING COUNT(DISTINCT c.no_rawat) > 0 ORDER BY COUNT(DISTINCT c.no_rawat) desc, pg.nama");
            try {
                ps7.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps7.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps7.setString(3, "%" + TCari.getText().trim() + "%");
                ps7.setString(4, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps7.setString(5, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps7.setString(6, "%" + TCari.getText().trim() + "%");                
                rs7 = ps7.executeQuery();
                x = 1;
                while (rs7.next()) {
                    tabMode7.addRow(new String[]{                        
                        x + ".",
                        rs7.getString(1),
                        rs7.getString(2),
                        rs7.getString(3),
                        rs7.getString(4)
                    });
                    x++;
                }                
            } catch (Exception e) {
                System.out.println("tampilBidanRanap() : " + e);
            } finally {
                if (rs7 != null) {
                    rs7.close();
                }
                if (ps7 != null) {
                    ps7.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilDpjp() {     
        Valid.tabelKosong(tabMode20);
        try {
            ps8 = koneksi.prepareStatement("SELECT z.`NIP/NR`, z.`Nama Dokter`, z.`Spesialis`, z.`Sbg. DPJP`, z.`Asesmen Medik Bedah`, z.`Asesmen Medik Dewasa`, z.`Asesmen Medik Anak`, "
                    + "z.`Asesmen Medik Perinatologi`, z.`CPPT`, z.`e-Resep R.Inap`, z.`Resume Medis`, z.`Pemberian Inf. Edukasi`, z.`Surat Konsul Antar Unit`, "
                    + "z.`Surat Jbwn. Konsul Antar Unit`, z.`Persetujuan/Penolakan Tndkn.`, z.`Portokol Kemoterapi`, z.`Total RM Terisi`, "
                    + "CONCAT(CASE WHEN MOD(z.`persen`,1)=0 THEN FORMAT(z.`persen`,0) ELSE FORMAT(z.`persen`,2) END,' %') AS `Persentase` "
                    + "FROM (SELECT y.nik `NIP/NR`, y.nama `Nama Dokter`, y.nm_spesialis `Spesialis`, y.sebagai_dpjp `Sbg. DPJP`, y.asmed_bedah `Asesmen Medik Bedah`, y.asmed_dewasa `Asesmen Medik Dewasa`, "
                    + "y.asmed_anak `Asesmen Medik Anak`, y.asmed_peri `Asesmen Medik Perinatologi`, y.cppt `CPPT`, y.resep_ranap `e-Resep R.Inap`, "
                    + "y.resum_medis `Resume Medis`, y.pemberian_inf_edukasi `Pemberian Inf. Edukasi`, y.surat_konsul `Surat Konsul Antar Unit`, "
                    + "y.surat_jwb_konsul `Surat Jbwn. Konsul Antar Unit`, y.persetujuan_penolakan_tindakan `Persetujuan/Penolakan Tndkn.`, y.protokol_kemo `Portokol Kemoterapi`, "
                    + "    (CASE WHEN y.asmed_bedah > 0 THEN 1 ELSE 0 END + "
                    + "        CASE WHEN y.asmed_dewasa > 0 THEN 1 ELSE 0 END + "
                    + "        CASE WHEN y.asmed_anak > 0 THEN 1 ELSE 0 END + "
                    + "        CASE WHEN y.asmed_peri > 0 THEN 1 ELSE 0 END + "
                    + "        CASE WHEN y.cppt > 0 THEN 1 ELSE 0 END + "
                    + "        CASE WHEN y.resep_ranap > 0 THEN 1 ELSE 0 END + "
                    + "        CASE WHEN y.resum_medis > 0 THEN 1 ELSE 0 END + "
                    + "	CASE WHEN y.pemberian_inf_edukasi > 0 THEN 1 ELSE 0 END + "
                    + "        CASE WHEN y.surat_konsul > 0 THEN 1 ELSE 0 END + "
                    + "        CASE WHEN y.surat_jwb_konsul > 0 THEN 1 ELSE 0 END + "
                    + "        CASE WHEN y.persetujuan_penolakan_tindakan > 0 THEN 1 ELSE 0 END + "
                    + "        CASE WHEN y.protokol_kemo > 0 THEN 1 ELSE 0 END) `Total RM Terisi`, "
                    + "    ((CASE WHEN y.asmed_bedah > 0 THEN 1 ELSE 0 END + "
                    + "        CASE WHEN y.asmed_dewasa > 0 THEN 1 ELSE 0 END + "
                    + "        CASE WHEN y.asmed_anak > 0 THEN 1 ELSE 0 END + "
                    + "        CASE WHEN y.asmed_peri > 0 THEN 1 ELSE 0 END + "
                    + "        CASE WHEN y.cppt > 0 THEN 1 ELSE 0 END + "
                    + "        CASE WHEN y.resep_ranap > 0 THEN 1 ELSE 0 END + "
                    + "        CASE WHEN y.resum_medis > 0 THEN 1 ELSE 0 END + "
                    + "	CASE WHEN y.pemberian_inf_edukasi > 0 THEN 1 ELSE 0 END + "
                    + "        CASE WHEN y.surat_konsul > 0 THEN 1 ELSE 0 END + "
                    + "        CASE WHEN y.surat_jwb_konsul > 0 THEN 1 ELSE 0 END + "
                    + "        CASE WHEN y.persetujuan_penolakan_tindakan > 0 THEN 1 ELSE 0 END + "
                    + "        CASE WHEN y.protokol_kemo > 0 THEN 1 ELSE 0 END)/12)*100 `persen` "
                    + "FROM (SELECT * FROM (SELECT pg.nik, pg.nama, (SELECT s.nm_sps FROM dokter d INNER JOIN spesialis s ON s.kd_sps = d.kd_sps where pg.nik=d.kd_dokter) nm_spesialis, "
                    + "            /* Sebagai DPJP */ "
                    + "            (SELECT COUNT(DISTINCT drp.no_rawat) "
                    + "                FROM dpjp_ranap drp "
                    + "                INNER JOIN reg_periksa rp ON rp.no_rawat = drp.no_rawat "
                    + "                inner join kamar_inap ki4 on ki4.no_rawat=drp.no_rawat "
                    + "                WHERE drp.kd_dokter = pg.nik "
                    + "                  AND DATE(ki4.tgl_keluar) BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and ki4.stts_pulang not in ('-','Pindah Kamar') "
                    + "            ) sebagai_dpjp, "
                    + "            /* Asesmen Medik Bedah */ "
                    + "            (SELECT COUNT(DISTINCT amb.no_rawat) "
                    + "                FROM asesmen_medik_bedah_ranap amb "
                    + "                INNER JOIN reg_periksa rp ON rp.no_rawat = amb.no_rawat "
                    + "                WHERE amb.nip_dpjp = pg.nik "
                    + "                  AND DATE(amb.waktu_simpan) BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + "            ) asmed_bedah, "
                    + "            /* Asesmen Medik Dewasa */ "
                    + "            (SELECT COUNT(DISTINCT amd.no_rawat) "
                    + "                FROM asesmen_medik_dewasa_ranap amd "
                    + "                INNER JOIN reg_periksa rp ON rp.no_rawat = amd.no_rawat "
                    + "                WHERE amd.nip_dpjp = pg.nik "
                    + "                  AND DATE(amd.waktu_simpan) BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + "            ) asmed_dewasa, "
                    + "            /* Asesmen Medik Anak */ "
                    + "            (SELECT COUNT(DISTINCT ama.no_rawat) "
                    + "                FROM asesmen_medik_anak_ranap ama "
                    + "                INNER JOIN reg_periksa rp ON rp.no_rawat = ama.no_rawat "
                    + "                WHERE ama.nip_dpjp = pg.nik "
                    + "                  AND DATE(ama.waktu_simpan) BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + "            ) asmed_anak, "
                    + "            /* Asesmen Medik Perinatologi */ "
                    + "            (SELECT COUNT(DISTINCT amp.no_rawat) "
                    + "                FROM asesmen_medik_perinatologi amp "
                    + "                INNER JOIN reg_periksa rp ON rp.no_rawat = amp.no_rawat "
                    + "                WHERE amp.nip_dpjp = pg.nik "
                    + "                  AND DATE(amp.waktu_simpan) BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + "            ) asmed_peri, "
                    + "            /* CPPT */ "
                    + "            (SELECT COUNT(DISTINCT c.no_rawat) "
                    + "                FROM cppt c "
                    + "                INNER JOIN reg_periksa rp ON rp.no_rawat = c.no_rawat "
                    + "                WHERE c.nip_ppa = pg.nik AND c.status = 'Ranap' and c.flag_hapus='tidak' "
                    + "                  AND c.jenis_bagian in ('DPJP','DPJP (K)','DPJP Raber') "
                    + "                  AND (c.bagian not like '%IGDK%' or c.bagian not like '%PON%') "
                    + "                  AND DATE(c.waktu_simpan) BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + "            ) cppt, "
                    + "            /* Catatan Resep Ranap */ "
                    + "            (SELECT COUNT(DISTINCT crr.no_rawat) "
                    + "                FROM catatan_resep_ranap crr "
                    + "                INNER JOIN reg_periksa rp ON rp.no_rawat = crr.no_rawat "
                    + "                inner join kamar_inap ki2 on ki2.no_rawat=crr.no_rawat "
                    + "                WHERE crr.kd_dokter = pg.nik "
                    + "                  AND DATE(crr.tgl_perawatan) BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + "            ) resep_ranap, "
                    + "            /* Resume Medis */ "
                    + "            (SELECT COUNT(DISTINCT rpr.no_rawat) "
                    + "                FROM ringkasan_pulang_ranap rpr "
                    + "                INNER JOIN reg_periksa rp ON rp.no_rawat = rpr.no_rawat "
                    + "                inner join dpjp_ranap dranap on dranap.no_rawat=rpr.no_rawat "
                    + "                inner join kamar_inap ki1 on ki1.no_rawat=rpr.no_rawat "
                    + "                WHERE dranap.kd_dokter = pg.nik "
                    + "                  AND DATE(ki1.tgl_keluar) BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and ki1.stts_pulang not in ('-','Pindah Kamar') "
                    + "            ) resum_medis, "
                    + "            /* Pemberian Informasi Edukasi */ "
                    + "            (SELECT COUNT(DISTINCT pi.no_rawat) "
                    + "                FROM pemberian_informasi_edukasi pi "
                    + "                INNER JOIN reg_periksa rp ON rp.no_rawat = pi.no_rawat "
                    + "                inner join kamar_inap ki5 on ki5.no_rawat=pi.no_rawat "
                    + "                WHERE pi.nip_petugas = pg.nik "
                    + "                  AND DATE(pi.waktu_simpan) BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + "            ) pemberian_inf_edukasi, "
                    + "            /* Surat Konsul Antar Unit */ "
                    + "            (SELECT COUNT(DISTINCT sku.no_rawat) "
                    + "                FROM surat_konsul_unit_ranap sku "
                    + "                INNER JOIN reg_periksa rp ON rp.no_rawat = sku.no_rawat "
                    + "                WHERE sku.nip_dokter_minta = pg.nik "
                    + "                  AND DATE(sku.waktu_simpan) BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + "            ) surat_konsul, "
                    + "            /* Surat Jawaban Konsul Antar Unit */ "
                    + "            (SELECT COUNT(DISTINCT sku.no_rawat) "
                    + "                FROM surat_konsul_unit_ranap sku "
                    + "                INNER JOIN reg_periksa rp ON rp.no_rawat = sku.no_rawat "
                    + "                WHERE sku.nip_dokter_jawab = pg.nik "
                    + "                  AND DATE(sku.waktu_simpan) BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + "            ) surat_jwb_konsul, "
                    + "            /* Surat Tindakan Kedokteran */ "
                    + "            (SELECT COUNT(DISTINCT st.no_rawat) "
                    + "                FROM surat_tindakan_kedokteran st "
                    + "                INNER JOIN reg_periksa rp ON rp.no_rawat = st.no_rawat "
                    + "                WHERE st.nip_pemberi_info = pg.nik and st.kasus_tindakan='Ranap' "
                    + "                  AND DATE(st.waktu_simpan) BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + "            ) persetujuan_penolakan_tindakan, "
                    + "            /* Protokol Kemoterapi */ "
                    + "            (SELECT COUNT(DISTINCT pk.no_rawat) "
                    + "                FROM protokol_kemoterapi pk "
                    + "                INNER JOIN reg_periksa rp ON rp.no_rawat = pk.no_rawat "
                    + "                inner join kamar_inap ki3 on ki3.no_rawat=pk.no_rawat "
                    + "                WHERE pk.nip_dokter = pg.nik "
                    + "                  AND DATE(pk.waktu_simpan) BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + "            ) protokol_kemo "
                    + "            FROM pegawai pg WHERE pg.nik NOT IN ('-', '--')) x WHERE x.resum_medis > 0) y) z "
                    + "            ORDER BY (z.`Total RM Terisi` / 12) DESC, z.`Resume Medis` desc");
            try {                             
                rs8 = ps8.executeQuery();
                x = 1;
                while (rs8.next()) {
                    tabMode20.addRow(new String[]{                        
                        x + ".",
                        rs8.getString(1),
                        rs8.getString(2),
                        rs8.getString(3),
                        rs8.getString(4),
                        rs8.getString(5),
                        rs8.getString(6),
                        rs8.getString(7),
                        rs8.getString(8),
                        rs8.getString(9),
                        rs8.getString(10),
                        rs8.getString(11),
                        rs8.getString(12),
                        rs8.getString(13),
                        rs8.getString(14),
                        rs8.getString(15),
                        rs8.getString(16),
                        rs8.getString(17)
                    });
                    x++;
                }                
            } catch (Exception e) {
                System.out.println("tampilDpjp() : " + e);
            } finally {
                if (rs8 != null) {
                    rs8.close();
                }
                if (ps8 != null) {
                    ps8.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilPasien(String nip, String nmTab) {
        Valid.tabelKosong(tabMode8);
        Valid.tabelKosong(tabMode9);
        Valid.tabelKosong(tabMode10);
        Valid.tabelKosong(tabMode11);
        Valid.tabelKosong(tabMode12);
        Valid.tabelKosong(tabMode13);
        Valid.tabelKosong(tabMode14);
        Valid.tabelKosong(tabMode15);
        Valid.tabelKosong(tabMode16);
        Valid.tabelKosong(tabMode17);
        Valid.tabelKosong(tabMode18);
        Valid.tabelKosong(tabMode19);
        try {
            if (nmTab.equals("perawat igd")) {
                psPas = koneksi.prepareStatement("select date_format(rp.tgl_registrasi,'%d-%m-%Y') tglKun, p.no_rkm_medis, p.nm_pasien, pj.png_jawab, "
                        + "concat(p.alamat,', Kel. ',kel.nm_kel,', Kec. ',kec.nm_kec,', Kab. ',kab.nm_kab) almt, pg.nama nmDokter from penilaian_awal_keperawatan_igdrz pa "
                        + "inner join reg_periksa rp on rp.no_rawat=pa.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                        + "inner join pegawai pg on pg.nik=rp.kd_dokter inner join penjab pj on pj.kd_pj=rp.kd_pj "
                        + "inner join kelurahan kel on kel.kd_kel=p.kd_kel inner join kecamatan kec on kec.kd_kec=p.kd_kec "
                        + "inner join kabupaten kab on kab.kd_kab=p.kd_kab where pa.nip_perawat='" + nip + "' AND "
                        + "DATE(pa.waktu_simpan) BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                        + "order by rp.tgl_registrasi");
            } else if (nmTab.equals("bidan ponek")) {
                psPas = koneksi.prepareStatement("select date_format(rp.tgl_registrasi,'%d-%m-%Y') tglKun, p.no_rkm_medis, p.nm_pasien, pj.png_jawab, "
                        + "concat(p.alamat,', Kel. ',kel.nm_kel,', Kec. ',kec.nm_kec,', Kab. ',kab.nm_kab) almt, pg.nama nmDokter from triase_ponek tp "
                        + "inner join reg_periksa rp on rp.no_rawat=tp.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                        + "inner join pegawai pg on pg.nik=rp.kd_dokter inner join penjab pj on pj.kd_pj=rp.kd_pj "
                        + "inner join kelurahan kel on kel.kd_kel=p.kd_kel inner join kecamatan kec on kec.kd_kec=p.kd_kec "
                        + "inner join kabupaten kab on kab.kd_kab=p.kd_kab where tp.nip_petugas='" + nip + "' AND "
                        + "DATE(tp.waktu_simpan) BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                        + "order by rp.tgl_registrasi");
            } else if (nmTab.equals("perawat dewasa cppt")) {
                psPas = koneksi.prepareStatement("select date_format(rp.tgl_registrasi,'%d-%m-%Y') tglKun, p.no_rkm_medis, p.nm_pasien, pj.png_jawab, "
                        + "concat(p.alamat,', Kel. ',kel.nm_kel,', Kec. ',kec.nm_kec,', Kab. ',kab.nm_kab) almt, b.nm_bangsal, if(pg.nama is null,'-',pg.nama) nmdpjp "
                        + "from cppt c inner join penilaian_awal_keperawatan_dewasa_ranap pa on pa.no_rawat=c.no_rawat "
                        + "inner join reg_periksa rp on rp.no_rawat=c.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                        + "inner join penjab pj on pj.kd_pj=rp.kd_pj inner join kelurahan kel on kel.kd_kel=p.kd_kel "
                        + "inner join kecamatan kec on kec.kd_kec=p.kd_kec inner join kabupaten kab on kab.kd_kab=p.kd_kab "
                        + "INNER JOIN kamar_inap ki ON ki.no_rawat=pa.no_rawat AND (ki.tgl_masuk,ki.jam_masuk) = "
                        + "(SELECT tgl_masuk, jam_masuk FROM kamar_inap WHERE no_rawat=pa.no_rawat ORDER BY tgl_masuk DESC, jam_masuk DESC LIMIT 1) "
                        + "INNER JOIN kamar k ON k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b ON b.kd_bangsal=k.kd_bangsal "
                        + "left join dpjp_ranap dr on dr.no_rawat=c.no_rawat left join pegawai pg on pg.nik=dr.kd_dokter where "
                        + "c.nip_ppa='" + nip + "' AND c.status = 'Ranap' AND c.jenis_ppa = 'Perawat' and c.flag_hapus='tidak' and "
                        + "DATE(c.waktu_simpan) BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                        + "group by c.no_rawat order by rp.tgl_registrasi");
            } else if (nmTab.equals("perawat dewasa askep")) {
                psPas = koneksi.prepareStatement("select date_format(rp.tgl_registrasi,'%d-%m-%Y') tglKun, p.no_rkm_medis, p.nm_pasien, pj.png_jawab, "
                        + "concat(p.alamat,', Kel. ',kel.nm_kel,', Kec. ',kec.nm_kec,', Kab. ',kab.nm_kab) almt, b.nm_bangsal, if(pg.nama is null,'-',pg.nama) nmdpjp "
                        + "from penilaian_awal_keperawatan_dewasa_ranap pa inner join reg_periksa rp on rp.no_rawat=pa.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis inner join penjab pj on pj.kd_pj=rp.kd_pj "
                        + "inner join kelurahan kel on kel.kd_kel=p.kd_kel inner join kecamatan kec on kec.kd_kec=p.kd_kec "
                        + "inner join kabupaten kab on kab.kd_kab=p.kd_kab INNER JOIN kamar_inap ki ON ki.no_rawat=pa.no_rawat AND (ki.tgl_masuk,ki.jam_masuk) = "
                        + "(SELECT tgl_masuk, jam_masuk FROM kamar_inap WHERE no_rawat=pa.no_rawat ORDER BY tgl_masuk DESC, jam_masuk DESC LIMIT 1) "
                        + "INNER JOIN kamar k ON k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b ON b.kd_bangsal=k.kd_bangsal "
                        + "left join dpjp_ranap dr on dr.no_rawat=pa.no_rawat left join pegawai pg on pg.nik=dr.kd_dokter where pa.nip_perawat='" + nip + "' AND "
                        + "DATE(pa.waktu_simpan) BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                        + "order by rp.tgl_registrasi");
            } else if (nmTab.equals("petugas farmasi")) {
                psPas = koneksi.prepareStatement("select date_format(rp.tgl_registrasi,'%d-%m-%Y') tglKun, p.no_rkm_medis, p.nm_pasien, pj.png_jawab, "
                        + "concat(p.alamat,', Kel. ',kel.nm_kel,', Kec. ',kec.nm_kec,', Kab. ',kab.nm_kab) almt, b.nm_bangsal, if(pg.nama is null,'-',pg.nama) nmdpjp "
                        + "from cppt c inner join reg_periksa rp on rp.no_rawat=c.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                        + "inner join penjab pj on pj.kd_pj=rp.kd_pj inner join kelurahan kel on kel.kd_kel=p.kd_kel "
                        + "inner join kecamatan kec on kec.kd_kec=p.kd_kec inner join kabupaten kab on kab.kd_kab=p.kd_kab "
                        + "INNER JOIN kamar_inap ki ON ki.no_rawat=c.no_rawat AND (ki.tgl_masuk,ki.jam_masuk) = "
                        + "(SELECT tgl_masuk, jam_masuk FROM kamar_inap WHERE no_rawat=c.no_rawat ORDER BY tgl_masuk DESC, jam_masuk DESC LIMIT 1) "
                        + "INNER JOIN kamar k ON k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b ON b.kd_bangsal=k.kd_bangsal "
                        + "left join dpjp_ranap dr on dr.no_rawat=c.no_rawat left join pegawai pg on pg.nik=dr.kd_dokter where "
                        + "c.nip_ppa='" + nip + "' AND c.jenis_ppa = 'Apoteker' and c.flag_hapus='tidak' and "
                        + "DATE(c.waktu_simpan) BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                        + "group by c.no_rawat order by rp.tgl_registrasi");
            } else if (nmTab.equals("petugas nutrisionis cppt")) {
                psPas = koneksi.prepareStatement("select date_format(rp.tgl_registrasi,'%d-%m-%Y') tglKun, p.no_rkm_medis, p.nm_pasien, pj.png_jawab, "
                        + "concat(p.alamat,', Kel. ',kel.nm_kel,', Kec. ',kec.nm_kec,', Kab. ',kab.nm_kab) almt, b.nm_bangsal, if(pg.nama is null,'-',pg.nama) nmdpjp "
                        + "from cppt c inner join reg_periksa rp on rp.no_rawat=c.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                        + "inner join penjab pj on pj.kd_pj=rp.kd_pj inner join kelurahan kel on kel.kd_kel=p.kd_kel "
                        + "inner join kecamatan kec on kec.kd_kec=p.kd_kec inner join kabupaten kab on kab.kd_kab=p.kd_kab "
                        + "INNER JOIN kamar_inap ki ON ki.no_rawat=c.no_rawat AND (ki.tgl_masuk,ki.jam_masuk) = "
                        + "(SELECT tgl_masuk, jam_masuk FROM kamar_inap WHERE no_rawat=c.no_rawat ORDER BY tgl_masuk DESC, jam_masuk DESC LIMIT 1) "
                        + "INNER JOIN kamar k ON k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b ON b.kd_bangsal=k.kd_bangsal "
                        + "left join dpjp_ranap dr on dr.no_rawat=c.no_rawat left join pegawai pg on pg.nik=dr.kd_dokter where "
                        + "c.nip_ppa='" + nip + "' AND c.jenis_ppa = 'Nutrisionis' and c.flag_hapus='tidak' and "
                        + "DATE(c.waktu_simpan) BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                        + "group by c.no_rawat order by rp.tgl_registrasi");
            } else if (nmTab.equals("petugas nutrisionis asuhan")) {
                psPas = koneksi.prepareStatement("select date_format(rp.tgl_registrasi,'%d-%m-%Y') tglKun, p.no_rkm_medis, p.nm_pasien, pj.png_jawab, "
                        + "concat(p.alamat,', Kel. ',kel.nm_kel,', Kec. ',kec.nm_kec,', Kab. ',kab.nm_kab) almt, b.nm_bangsal, if(pg.nama is null,'-',pg.nama) nmdpjp "
                        + "from asuhan_gizi_ranap ag inner join reg_periksa rp on rp.no_rawat=ag.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis inner join penjab pj on pj.kd_pj=rp.kd_pj "
                        + "inner join kelurahan kel on kel.kd_kel=p.kd_kel inner join kecamatan kec on kec.kd_kec=p.kd_kec "
                        + "inner join kabupaten kab on kab.kd_kab=p.kd_kab INNER JOIN kamar_inap ki ON ki.no_rawat=ag.no_rawat AND (ki.tgl_masuk,ki.jam_masuk) = "
                        + "(SELECT tgl_masuk, jam_masuk FROM kamar_inap WHERE no_rawat=ag.no_rawat ORDER BY tgl_masuk DESC, jam_masuk DESC LIMIT 1) "
                        + "INNER JOIN kamar k ON k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b ON b.kd_bangsal=k.kd_bangsal "
                        + "left join dpjp_ranap dr on dr.no_rawat=ag.no_rawat left join pegawai pg on pg.nik=dr.kd_dokter where ag.nip_petugas='" + nip + "' and "
                        + "ag.tgl_asuhan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                        + "order by rp.tgl_registrasi");
            } else if (nmTab.equals("perawat ruang anak cppt")) {
                psPas = koneksi.prepareStatement("select date_format(rp.tgl_registrasi,'%d-%m-%Y') tglKun, p.no_rkm_medis, p.nm_pasien, pj.png_jawab, "
                        + "concat(p.alamat,', Kel. ',kel.nm_kel,', Kec. ',kec.nm_kec,', Kab. ',kab.nm_kab) almt, b.nm_bangsal, if(pg.nama is null,'-',pg.nama) nmdpjp "
                        + "from cppt c inner join penilaian_awal_keperawatan_anak_ranap pa on pa.no_rawat=c.no_rawat "
                        + "inner join reg_periksa rp on rp.no_rawat=c.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                        + "inner join penjab pj on pj.kd_pj=rp.kd_pj inner join kelurahan kel on kel.kd_kel=p.kd_kel "
                        + "inner join kecamatan kec on kec.kd_kec=p.kd_kec inner join kabupaten kab on kab.kd_kab=p.kd_kab "
                        + "INNER JOIN kamar_inap ki ON ki.no_rawat=pa.no_rawat AND (ki.tgl_masuk,ki.jam_masuk) = "
                        + "(SELECT tgl_masuk, jam_masuk FROM kamar_inap WHERE no_rawat=pa.no_rawat ORDER BY tgl_masuk DESC, jam_masuk DESC LIMIT 1) "
                        + "INNER JOIN kamar k ON k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b ON b.kd_bangsal=k.kd_bangsal "
                        + "inner join pegawai pg1 on pg1.nik=c.nip_ppa inner join departemen d on d.dep_id=pg1.departemen "
                        +" left join dpjp_ranap dr on dr.no_rawat=c.no_rawat left join pegawai pg on pg.nik=dr.kd_dokter where "
                        + "c.nip_ppa='" + nip + "' AND c.status = 'Ranap' AND c.jenis_ppa = 'Perawat' and c.flag_hapus='tidak' and d.nama like '%anak%' and "
                        + "DATE(c.waktu_simpan) BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                        + "group by c.no_rawat order by rp.tgl_registrasi");
            } else if (nmTab.equals("perawat ruang anak askep")) {
                psPas = koneksi.prepareStatement("select date_format(rp.tgl_registrasi,'%d-%m-%Y') tglKun, p.no_rkm_medis, p.nm_pasien, pj.png_jawab, "
                        + "concat(p.alamat,', Kel. ',kel.nm_kel,', Kec. ',kec.nm_kec,', Kab. ',kab.nm_kab) almt, b.nm_bangsal, if(pg.nama is null,'-',pg.nama) nmdpjp "
                        + "from penilaian_awal_keperawatan_anak_ranap pa inner join reg_periksa rp on rp.no_rawat=pa.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis inner join penjab pj on pj.kd_pj=rp.kd_pj "
                        + "inner join kelurahan kel on kel.kd_kel=p.kd_kel inner join kecamatan kec on kec.kd_kec=p.kd_kec "
                        + "inner join kabupaten kab on kab.kd_kab=p.kd_kab INNER JOIN kamar_inap ki ON ki.no_rawat=pa.no_rawat AND (ki.tgl_masuk,ki.jam_masuk) = "
                        + "(SELECT tgl_masuk, jam_masuk FROM kamar_inap WHERE no_rawat=pa.no_rawat ORDER BY tgl_masuk DESC, jam_masuk DESC LIMIT 1) "
                        + "INNER JOIN kamar k ON k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b ON b.kd_bangsal=k.kd_bangsal "
                        + "inner join pegawai pg1 on pg1.nik=pa.nip_perawat inner join departemen d on d.dep_id=pg1.departemen "
                        + "left join dpjp_ranap dr on dr.no_rawat=pa.no_rawat left join pegawai pg on pg.nik=dr.kd_dokter where "
                        + "pa.nip_perawat='" + nip + "' and d.nama like '%anak%' and "
                        + "DATE(pa.waktu_simpan) BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                        + "order by rp.tgl_registrasi");
            } else if (nmTab.equals("perawat ranap anak cppt")) {
                psPas = koneksi.prepareStatement("select date_format(rp.tgl_registrasi,'%d-%m-%Y') tglKun, p.no_rkm_medis, p.nm_pasien, pj.png_jawab, "
                        + "concat(p.alamat,', Kel. ',kel.nm_kel,', Kec. ',kec.nm_kec,', Kab. ',kab.nm_kab) almt, b.nm_bangsal, if(pg.nama is null,'-',pg.nama) nmdpjp "
                        + "from cppt c inner join penilaian_awal_keperawatan_anak_ranap pa on pa.no_rawat=c.no_rawat "
                        + "inner join reg_periksa rp on rp.no_rawat=c.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                        + "inner join penjab pj on pj.kd_pj=rp.kd_pj inner join kelurahan kel on kel.kd_kel=p.kd_kel "
                        + "inner join kecamatan kec on kec.kd_kec=p.kd_kec inner join kabupaten kab on kab.kd_kab=p.kd_kab "
                        + "INNER JOIN kamar_inap ki ON ki.no_rawat=pa.no_rawat AND (ki.tgl_masuk,ki.jam_masuk) = "
                        + "(SELECT tgl_masuk, jam_masuk FROM kamar_inap WHERE no_rawat=pa.no_rawat ORDER BY tgl_masuk DESC, jam_masuk DESC LIMIT 1) "
                        + "INNER JOIN kamar k ON k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b ON b.kd_bangsal=k.kd_bangsal "
                        + "inner join pegawai pg1 on pg1.nik=c.nip_ppa inner join departemen d on d.dep_id=pg1.departemen "
                        + "left join dpjp_ranap dr on dr.no_rawat=c.no_rawat left join pegawai pg on pg.nik=dr.kd_dokter where "
                        + "c.nip_ppa='" + nip + "' AND c.status = 'Ranap' AND c.jenis_ppa = 'Perawat' and c.flag_hapus='tidak' and d.nama not like '%anak%' and "
                        + "DATE(c.waktu_simpan) BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                        + "group by c.no_rawat order by rp.tgl_registrasi");
            } else if (nmTab.equals("perawat ranap anak askep")) {
                psPas = koneksi.prepareStatement("select date_format(rp.tgl_registrasi,'%d-%m-%Y') tglKun, p.no_rkm_medis, p.nm_pasien, pj.png_jawab, "
                        + "concat(p.alamat,', Kel. ',kel.nm_kel,', Kec. ',kec.nm_kec,', Kab. ',kab.nm_kab) almt, b.nm_bangsal, if(pg.nama is null,'-',pg.nama) nmdpjp "
                        + "from penilaian_awal_keperawatan_anak_ranap pa inner join reg_periksa rp on rp.no_rawat=pa.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis inner join penjab pj on pj.kd_pj=rp.kd_pj "
                        + "inner join kelurahan kel on kel.kd_kel=p.kd_kel inner join kecamatan kec on kec.kd_kec=p.kd_kec "
                        + "inner join kabupaten kab on kab.kd_kab=p.kd_kab INNER JOIN kamar_inap ki ON ki.no_rawat=pa.no_rawat AND (ki.tgl_masuk,ki.jam_masuk) = "
                        + "(SELECT tgl_masuk, jam_masuk FROM kamar_inap WHERE no_rawat=pa.no_rawat ORDER BY tgl_masuk DESC, jam_masuk DESC LIMIT 1) "
                        + "INNER JOIN kamar k ON k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b ON b.kd_bangsal=k.kd_bangsal "
                        + "inner join pegawai pg1 on pg1.nik=pa.nip_perawat inner join departemen d on d.dep_id=pg1.departemen "
                        + "left join dpjp_ranap dr on dr.no_rawat=pa.no_rawat left join pegawai pg on pg.nik=dr.kd_dokter where "
                        + "pa.nip_perawat='" + nip + "' and d.nama not like '%anak%' and "
                        + "DATE(pa.waktu_simpan) BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                        + "order by rp.tgl_registrasi");
            } else if (nmTab.equals("bidan ranap")) {
                psPas = koneksi.prepareStatement("select date_format(rp.tgl_registrasi,'%d-%m-%Y') tglKun, p.no_rkm_medis, p.nm_pasien, pj.png_jawab, "
                        + "concat(p.alamat,', Kel. ',kel.nm_kel,', Kec. ',kec.nm_kec,', Kab. ',kab.nm_kab) almt, b.nm_bangsal, if(pg.nama is null,'-',pg.nama) nmdpjp "
                        + "from cppt c inner join reg_periksa rp on rp.no_rawat=c.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis inner join penjab pj on pj.kd_pj=rp.kd_pj "
                        + "inner join kelurahan kel on kel.kd_kel=p.kd_kel inner join kecamatan kec on kec.kd_kec=p.kd_kec "
                        + "inner join kabupaten kab on kab.kd_kab=p.kd_kab left join dpjp_ranap dr on dr.no_rawat=c.no_rawat "
                        + "left join pegawai pg on pg.nik=dr.kd_dokter left JOIN kamar_inap ki ON ki.no_rawat=c.no_rawat AND (ki.tgl_masuk,ki.jam_masuk) = "
                        + "(SELECT tgl_masuk, jam_masuk FROM kamar_inap WHERE no_rawat=c.no_rawat ORDER BY tgl_masuk DESC, jam_masuk DESC LIMIT 1) "
                        + "left JOIN kamar k ON k.kd_kamar=ki.kd_kamar left JOIN bangsal b ON b.kd_bangsal=k.kd_bangsal where "
                        + "c.nip_ppa='" + nip + "' AND c.status = 'Ranap' AND c.jenis_ppa = 'Bidan' and c.flag_hapus='tidak' and "
                        + "DATE(c.waktu_simpan) BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                        + "group by c.no_rawat order by rp.tgl_registrasi");
            }
            try {
                rsPas = psPas.executeQuery();
                x = 1;
                while (rsPas.next()) {
                    if (nmTab.equals("perawat igd")) {
                        tabMode8.addRow(new String[]{
                            x + ".",
                            rsPas.getString(1),
                            rsPas.getString(2),
                            rsPas.getString(3),
                            rsPas.getString(4),
                            rsPas.getString(5),
                            rsPas.getString(6)
                        });
                    } else if (nmTab.equals("bidan ponek")) {
                        tabMode9.addRow(new String[]{
                            x + ".",
                            rsPas.getString(1),
                            rsPas.getString(2),
                            rsPas.getString(3),
                            rsPas.getString(4),
                            rsPas.getString(5),
                            rsPas.getString(6)
                        });
                    } else if (nmTab.equals("perawat dewasa cppt")) {
                        tabMode10.addRow(new String[]{
                            x + ".",
                            rsPas.getString(1),
                            rsPas.getString(2),
                            rsPas.getString(3),
                            rsPas.getString(4),
                            rsPas.getString(5),
                            rsPas.getString(6),
                            rsPas.getString(7)
                        });
                    } else if (nmTab.equals("perawat dewasa askep")) {
                        tabMode11.addRow(new String[]{
                            x + ".",
                            rsPas.getString(1),
                            rsPas.getString(2),
                            rsPas.getString(3),
                            rsPas.getString(4),
                            rsPas.getString(5),
                            rsPas.getString(6),
                            rsPas.getString(7)
                        });
                    } else if (nmTab.equals("petugas farmasi")) {
                        tabMode12.addRow(new String[]{
                            x + ".",
                            rsPas.getString(1),
                            rsPas.getString(2),
                            rsPas.getString(3),
                            rsPas.getString(4),
                            rsPas.getString(5),
                            rsPas.getString(6),
                            rsPas.getString(7)
                        });
                    } else if (nmTab.equals("petugas nutrisionis cppt")) {
                        tabMode13.addRow(new String[]{
                            x + ".",
                            rsPas.getString(1),
                            rsPas.getString(2),
                            rsPas.getString(3),
                            rsPas.getString(4),
                            rsPas.getString(5),
                            rsPas.getString(6),
                            rsPas.getString(7)
                        });
                    } else if (nmTab.equals("petugas nutrisionis asuhan")) {
                        tabMode14.addRow(new String[]{
                            x + ".",
                            rsPas.getString(1),
                            rsPas.getString(2),
                            rsPas.getString(3),
                            rsPas.getString(4),
                            rsPas.getString(5),
                            rsPas.getString(6),
                            rsPas.getString(7)
                        });
                    } else if (nmTab.equals("perawat ruang anak cppt")) {
                        tabMode15.addRow(new String[]{
                            x + ".",
                            rsPas.getString(1),
                            rsPas.getString(2),
                            rsPas.getString(3),
                            rsPas.getString(4),
                            rsPas.getString(5),
                            rsPas.getString(6),
                            rsPas.getString(7)
                        });
                    } else if (nmTab.equals("perawat ruang anak askep")) {
                        tabMode16.addRow(new String[]{
                            x + ".",
                            rsPas.getString(1),
                            rsPas.getString(2),
                            rsPas.getString(3),
                            rsPas.getString(4),
                            rsPas.getString(5),
                            rsPas.getString(6),
                            rsPas.getString(7)
                        });
                    } else if (nmTab.equals("perawat ranap anak cppt")) {
                        tabMode17.addRow(new String[]{
                            x + ".",
                            rsPas.getString(1),
                            rsPas.getString(2),
                            rsPas.getString(3),
                            rsPas.getString(4),
                            rsPas.getString(5),
                            rsPas.getString(6),
                            rsPas.getString(7)
                        });
                    } else if (nmTab.equals("perawat ranap anak askep")) {
                        tabMode18.addRow(new String[]{
                            x + ".",
                            rsPas.getString(1),
                            rsPas.getString(2),
                            rsPas.getString(3),
                            rsPas.getString(4),
                            rsPas.getString(5),
                            rsPas.getString(6),
                            rsPas.getString(7)
                        });
                    } else if (nmTab.equals("bidan ranap")) {
                        tabMode19.addRow(new String[]{
                            x + ".",
                            rsPas.getString(1),
                            rsPas.getString(2),
                            rsPas.getString(3),
                            rsPas.getString(4),
                            rsPas.getString(5),
                            rsPas.getString(6),
                            rsPas.getString(7)
                        });
                    }
                    x++;
                }
            } catch (Exception e) {
                System.out.println("tampilPasien() : " + e);
            } finally {
                if (rsPas != null) {
                    rsPas.close();
                }
                if (psPas != null) {
                    psPas.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void getDataPerawatIgd() {
        if (tbPerawatIgd.getSelectedRow() != -1) {
            TnipPerawatIgd.setText(tbPerawatIgd.getValueAt(tbPerawatIgd.getSelectedRow(), 1).toString());
            TnmPerawatIgd.setText(tbPerawatIgd.getValueAt(tbPerawatIgd.getSelectedRow(), 2).toString());
            tampilPasien(TnipPerawatIgd.getText(), "perawat igd");
        }
    }
    
    private void getDataBidanPonek() {
        if (tbBidanPonek.getSelectedRow() != -1) {
            TnipBidanPonek.setText(tbBidanPonek.getValueAt(tbBidanPonek.getSelectedRow(), 1).toString());
            TnmBidanPonek.setText(tbBidanPonek.getValueAt(tbBidanPonek.getSelectedRow(), 2).toString());
            tampilPasien(TnipBidanPonek.getText(), "bidan ponek");
        }
    }
    
    private void getDataPerawatDws() {
        if (tbPerawatDewasa.getSelectedRow() != -1) {
            TnipPerawatDws.setText(tbPerawatDewasa.getValueAt(tbPerawatDewasa.getSelectedRow(), 1).toString());
            TnmPerawatDws.setText(tbPerawatDewasa.getValueAt(tbPerawatDewasa.getSelectedRow(), 2).toString());
            TabPerawatDwsMouseClicked(null);
        }
    }
    
    private void getDataPetugasFarmasi() {
        if (tbFarmasi.getSelectedRow() != -1) {
            TnipPetFarmasi.setText(tbFarmasi.getValueAt(tbFarmasi.getSelectedRow(), 1).toString());
            TnmPetFarmasi.setText(tbFarmasi.getValueAt(tbFarmasi.getSelectedRow(), 2).toString());
            tampilPasien(TnipPetFarmasi.getText(), "petugas farmasi");
        }
    }
    
    private void getDataPetugasNutrisionis() {
        if (tbNutrisionis.getSelectedRow() != -1) {
            TnipPetNutri.setText(tbNutrisionis.getValueAt(tbNutrisionis.getSelectedRow(), 1).toString());
            TnmPetNutri.setText(tbNutrisionis.getValueAt(tbNutrisionis.getSelectedRow(), 2).toString());
            TabPetugasNutriMouseClicked(null);
        }
    }
    
    private void getDataPerawatRuangAnak() {
        if (tbPerawatAnak.getSelectedRow() != -1) {
            TnipPerawatAnak.setText(tbPerawatAnak.getValueAt(tbPerawatAnak.getSelectedRow(), 1).toString());
            TnmPerawatAnak.setText(tbPerawatAnak.getValueAt(tbPerawatAnak.getSelectedRow(), 2).toString());
            TabPerawatAnakMouseClicked(null);
        }
    }
    
    private void getDataPerawatRanapAnak() {
        if (tbPerawatAnak1.getSelectedRow() != -1) {
            TnipPerawatRanapAnak.setText(tbPerawatAnak1.getValueAt(tbPerawatAnak1.getSelectedRow(), 1).toString());
            TnmPerawatRanapAnak.setText(tbPerawatAnak1.getValueAt(tbPerawatAnak1.getSelectedRow(), 2).toString());
            TabPerawatRanapAnakMouseClicked(null);
        }
    }
    
    private void getDataBidanRanap() {
        if (tbBidan.getSelectedRow() != -1) {
            TnipBidanRanap.setText(tbBidan.getValueAt(tbBidan.getSelectedRow(), 1).toString());
            TnmBidanRanap.setText(tbBidan.getValueAt(tbBidan.getSelectedRow(), 2).toString());
            tampilPasien(TnipBidanRanap.getText(), "bidan ranap");
        }
    }

    private void downloadData(String nilai) {
        if (nilai.equals("perawat igd")) {
            Sequel.AutoComitFalse();
            Sequel.queryu("delete from temporary1");
            int row = tabMode.getRowCount();
            for (int r = 0; r < row; r++) {
                Sequel.menyimpan("temporary1", "'"
                        + tabMode.getValueAt(r, 0).toString() + "','"
                        + tabMode.getValueAt(r, 1).toString() + "','"
                        + tabMode.getValueAt(r, 2).toString().replaceAll("'", "") + "','"
                        + tabMode.getValueAt(r, 3).toString() + "',"
                        + "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Data Perawat IGD");
            }
            Sequel.AutoComitTrue();

            dialog_simpan = Valid.openDialog();
            if (Valid.MyReportToExcelBoolean("SELECT temp1 'No.', temp2 'NIP/NR', temp3 'Nama Perawat', temp4 'Jml. Askep IGD PerPasien' from temporary1", dialog_simpan) == true) {
                JOptionPane.showMessageDialog(null, "Data perawat IGD berhasil diexport menjadi file excel,..!!!");
            } else {
                JOptionPane.showMessageDialog(null, "Data perawat IGD gagal diexport menjadi file excel,..!!!");
            }
        } else if (nilai.equals("askep perawat igd")) {
            Sequel.AutoComitFalse();
            Sequel.queryu("delete from temporary1");
            int row = tabMode8.getRowCount();
            for (int r = 0; r < row; r++) {
                Sequel.menyimpan("temporary1", "'"
                        + tabMode8.getValueAt(r, 0).toString() + "','"
                        + tabMode8.getValueAt(r, 1).toString() + "','"
                        + tabMode8.getValueAt(r, 2).toString() + "','"
                        + tabMode8.getValueAt(r, 3).toString().replaceAll("'", "") + "','"
                        + tabMode8.getValueAt(r, 4).toString() + "','"
                        + tabMode8.getValueAt(r, 5).toString() + "','"
                        + tabMode8.getValueAt(r, 6).toString() + "','"
                        + TnipPerawatIgd.getText() + "','"
                        + TnmPerawatIgd.getText().replaceAll("'", "") + "','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Data Askep Perawat IGD");
            }
            Sequel.AutoComitTrue();

            dialog_simpan = Valid.openDialog();
            if (Valid.MyReportToExcelBoolean("SELECT temp1 'No.', temp2 'Tgl. Kunjungan', temp3 'No. RM', temp4 'Nama Pasien', "
                    + "temp5 'Cara Bayar', temp6 'Alamat', temp7 'Dokter IGD', temp8 'NIP/NR', temp9 'Nama Perawat' from temporary1", dialog_simpan) == true) {
                JOptionPane.showMessageDialog(null, "Data Askep perawat IGD berhasil diexport menjadi file excel,..!!!");
            } else {
                JOptionPane.showMessageDialog(null, "Data Askep perawat IGD gagal diexport menjadi file excel,..!!!");
            }
        } else if (nilai.equals("bidan ponek")) {
            Sequel.AutoComitFalse();
            Sequel.queryu("delete from temporary1");
            int row = tabMode1.getRowCount();
            for (int r = 0; r < row; r++) {
                Sequel.menyimpan("temporary1", "'"
                        + tabMode1.getValueAt(r, 0).toString() + "','"
                        + tabMode1.getValueAt(r, 1).toString() + "','"
                        + tabMode1.getValueAt(r, 2).toString().replaceAll("'", "") + "','"
                        + tabMode1.getValueAt(r, 3).toString() + "',"
                        + "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Data Bidan Ponek");
            }
            Sequel.AutoComitTrue();

            dialog_simpan = Valid.openDialog();
            if (Valid.MyReportToExcelBoolean("SELECT temp1 'No.', temp2 'NIP/NR', temp3 'Nama Petugas', temp4 'Jml. Triase Ponek PerPasien' from temporary1", dialog_simpan) == true) {
                JOptionPane.showMessageDialog(null, "Data bidan ponek berhasil diexport menjadi file excel,..!!!");
            } else {
                JOptionPane.showMessageDialog(null, "Data bidan ponek gagal diexport menjadi file excel,..!!!");
            }
        } else if (nilai.equals("triase ponek bidan")) {
            Sequel.AutoComitFalse();
            Sequel.queryu("delete from temporary1");
            int row = tabMode9.getRowCount();
            for (int r = 0; r < row; r++) {
                Sequel.menyimpan("temporary1", "'"
                        + tabMode9.getValueAt(r, 0).toString() + "','"
                        + tabMode9.getValueAt(r, 1).toString() + "','"
                        + tabMode9.getValueAt(r, 2).toString() + "','"
                        + tabMode9.getValueAt(r, 3).toString().replaceAll("'", "") + "','"
                        + tabMode9.getValueAt(r, 4).toString() + "','"
                        + tabMode9.getValueAt(r, 5).toString() + "','"
                        + tabMode9.getValueAt(r, 6).toString() + "','"
                        + TnipBidanPonek.getText() + "','"
                        + TnmBidanPonek.getText().replaceAll("'", "") + "','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Data Triase Ponek Bidan");
            }
            Sequel.AutoComitTrue();

            dialog_simpan = Valid.openDialog();
            if (Valid.MyReportToExcelBoolean("SELECT temp1 'No.', temp2 'Tgl. Kunjungan', temp3 'No. RM', temp4 'Nama Pasien', "
                    + "temp5 'Cara Bayar', temp6 'Alamat', temp7 'Nama Dokter', temp8 'NIP/NR', temp9 'Nama Bidan' from temporary1", dialog_simpan) == true) {
                JOptionPane.showMessageDialog(null, "Data triase ponek berhasil diexport menjadi file excel,..!!!");
            } else {
                JOptionPane.showMessageDialog(null, "Data triase ponek gagal diexport menjadi file excel,..!!!");
            }
        } else if (nilai.equals("perawat ranap dewasa")) {
            Sequel.AutoComitFalse();
            Sequel.queryu("delete from temporary1");
            int row = tabMode2.getRowCount();
            for (int r = 0; r < row; r++) {
                Sequel.menyimpan("temporary1", "'"
                        + tabMode2.getValueAt(r, 0).toString() + "','"
                        + tabMode2.getValueAt(r, 1).toString() + "','"
                        + tabMode2.getValueAt(r, 2).toString().replaceAll("'", "") + "','"
                        + tabMode2.getValueAt(r, 3).toString() + "','"
                        + tabMode2.getValueAt(r, 4).toString() + "','"
                        + tabMode2.getValueAt(r, 5).toString() + "','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Data Perawat Rg. Inap (Dewasa)");
            }
            Sequel.AutoComitTrue();

            dialog_simpan = Valid.openDialog();
            if (Valid.MyReportToExcelBoolean("SELECT temp1 'No.', temp2 'NIP/NR', temp3 'Nama Perawat', temp4 'CPPT', "
                    + "temp5 'Assesmen Keperawatan Dewasa', temp6 'Unit Kerja' from temporary1", dialog_simpan) == true) {
                JOptionPane.showMessageDialog(null, "Data perawat rg. inap (dewasa) berhasil diexport menjadi file excel,..!!!");
            } else {
                JOptionPane.showMessageDialog(null, "Data perawat rg. inap (dewasa) gagal diexport menjadi file excel,..!!!");
            }            
        } else if (nilai.equals("cppt ranap dewasa")) {
            Sequel.AutoComitFalse();
            Sequel.queryu("delete from temporary1");
            int row = tabMode10.getRowCount();
            for (int r = 0; r < row; r++) {
                Sequel.menyimpan("temporary1", "'"
                        + tabMode10.getValueAt(r, 0).toString() + "','"
                        + tabMode10.getValueAt(r, 1).toString() + "','"
                        + tabMode10.getValueAt(r, 2).toString() + "','"
                        + tabMode10.getValueAt(r, 3).toString().replaceAll("'", "") + "','"
                        + tabMode10.getValueAt(r, 4).toString() + "','"
                        + tabMode10.getValueAt(r, 5).toString() + "','"
                        + tabMode10.getValueAt(r, 6).toString() + "','"
                        + tabMode10.getValueAt(r, 7).toString() + "','"
                        + TnipPerawatDws.getText() + "','"
                        + TnmPerawatDws.getText().replaceAll("'", "") + "','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Data CPPT Perawat Ranap Dewasa");
            }
            Sequel.AutoComitTrue();

            dialog_simpan = Valid.openDialog();
            if (Valid.MyReportToExcelBoolean("SELECT temp1 'No.', temp2 'Tgl. MRS', temp3 'No. RM', temp4 'Nama Pasien', "
                    + "temp5 'Cara Bayar', temp6 'Alamat', temp7 'Rg. Rawat Terakhir', temp8 'Nama DPJP', temp9 'NIP/NR', temp10 'Nama Perawat' from temporary1", dialog_simpan) == true) {
                JOptionPane.showMessageDialog(null, "Data cppt perawat rg. inap (dewasa) berhasil diexport menjadi file excel,..!!!");
            } else {
                JOptionPane.showMessageDialog(null, "Data cppt perawat rg. inap (dewasa) gagal diexport menjadi file excel,..!!!");
            }
        } else if (nilai.equals("askep ranap dewasa")) {
            Sequel.AutoComitFalse();
            Sequel.queryu("delete from temporary1");
            int row = tabMode11.getRowCount();
            for (int r = 0; r < row; r++) {
                Sequel.menyimpan("temporary1", "'"
                        + tabMode11.getValueAt(r, 0).toString() + "','"
                        + tabMode11.getValueAt(r, 1).toString() + "','"
                        + tabMode11.getValueAt(r, 2).toString() + "','"
                        + tabMode11.getValueAt(r, 3).toString().replaceAll("'", "") + "','"
                        + tabMode11.getValueAt(r, 4).toString() + "','"
                        + tabMode11.getValueAt(r, 5).toString() + "','"
                        + tabMode11.getValueAt(r, 6).toString() + "','"
                        + tabMode11.getValueAt(r, 7).toString() + "','"
                        + TnipPerawatDws.getText() + "','"
                        + TnmPerawatDws.getText().replaceAll("'", "") + "','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Data Askep Perawat Ranap Dewasa");
            }
            Sequel.AutoComitTrue();

            dialog_simpan = Valid.openDialog();
            if (Valid.MyReportToExcelBoolean("SELECT temp1 'No.', temp2 'Tgl. MRS', temp3 'No. RM', temp4 'Nama Pasien', "
                    + "temp5 'Cara Bayar', temp6 'Alamat', temp7 'Rg. Rawat Terakhir', temp8 'Nama DPJP', temp9 'NIP/NR', temp10 'Nama Perawat' from temporary1", dialog_simpan) == true) {
                JOptionPane.showMessageDialog(null, "Data askep perawat rg. inap (dewasa) berhasil diexport menjadi file excel,..!!!");
            } else {
                JOptionPane.showMessageDialog(null, "Data askep perawat rg. inap (dewasa) gagal diexport menjadi file excel,..!!!");
            }            
        } else if (nilai.equals("petugas farmasi")) {
            Sequel.AutoComitFalse();
            Sequel.queryu("delete from temporary1");
            int row = tabMode3.getRowCount();
            for (int r = 0; r < row; r++) {
                Sequel.menyimpan("temporary1", "'"
                        + tabMode3.getValueAt(r, 0).toString() + "','"
                        + tabMode3.getValueAt(r, 1).toString() + "','"
                        + tabMode3.getValueAt(r, 2).toString().replaceAll("'", "") + "','"
                        + tabMode3.getValueAt(r, 3).toString() + "','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Data Petugas Farmasi");
            }
            Sequel.AutoComitTrue();

            dialog_simpan = Valid.openDialog();
            if (Valid.MyReportToExcelBoolean("SELECT temp1 'No.', temp2 'NIP/NR', temp3 'Nama Petugas', temp4 'Jml. CPPT PerPasien' from temporary1", dialog_simpan) == true) {
                JOptionPane.showMessageDialog(null, "Data petugas farmasi berhasil diexport menjadi file excel,..!!!");
            } else {
                JOptionPane.showMessageDialog(null, "Data petugas farmasi gagal diexport menjadi file excel,..!!!");
            }            
        } else if (nilai.equals("cppt farmasi")) {
            Sequel.AutoComitFalse();
            Sequel.queryu("delete from temporary1");
            int row = tabMode12.getRowCount();
            for (int r = 0; r < row; r++) {
                Sequel.menyimpan("temporary1", "'"
                        + tabMode12.getValueAt(r, 0).toString() + "','"
                        + tabMode12.getValueAt(r, 1).toString() + "','"
                        + tabMode12.getValueAt(r, 2).toString() + "','"
                        + tabMode12.getValueAt(r, 3).toString().replaceAll("'", "") + "','"
                        + tabMode12.getValueAt(r, 4).toString() + "','"
                        + tabMode12.getValueAt(r, 5).toString() + "','"
                        + tabMode12.getValueAt(r, 6).toString() + "','"
                        + tabMode12.getValueAt(r, 7).toString() + "','"
                        + TnipPetFarmasi.getText() + "','"
                        + TnmPetFarmasi.getText().replaceAll("'", "") + "','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Data CPPT Petugas Farmasi");
            }
            Sequel.AutoComitTrue();

            dialog_simpan = Valid.openDialog();
            if (Valid.MyReportToExcelBoolean("SELECT temp1 'No.', temp2 'Tgl. MRS', temp3 'No. RM', temp4 'Nama Pasien', "
                    + "temp5 'Cara Bayar', temp6 'Alamat', temp7 'Rg. Rawat Terakhir', temp8 'Nama DPJP', temp9 'NIP/NR', temp10 'Nama Petugas' from temporary1", dialog_simpan) == true) {
                JOptionPane.showMessageDialog(null, "Data cppt petugas farmasi berhasil diexport menjadi file excel,..!!!");
            } else {
                JOptionPane.showMessageDialog(null, "Data cppt petugas farmasi gagal diexport menjadi file excel,..!!!");
            }
        } else if (nilai.equals("petugas nutrisionis")) {
            Sequel.AutoComitFalse();
            Sequel.queryu("delete from temporary1");
            int row = tabMode4.getRowCount();
            for (int r = 0; r < row; r++) {
                Sequel.menyimpan("temporary1", "'"
                        + tabMode4.getValueAt(r, 0).toString() + "','"
                        + tabMode4.getValueAt(r, 1).toString() + "','"
                        + tabMode4.getValueAt(r, 2).toString().replaceAll("'", "") + "','"
                        + tabMode4.getValueAt(r, 3).toString() + "','"
                        + tabMode4.getValueAt(r, 4).toString() + "','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Data Petugas Nutrisionis");
            }
            Sequel.AutoComitTrue();

            dialog_simpan = Valid.openDialog();
            if (Valid.MyReportToExcelBoolean("SELECT temp1 'No.', temp2 'NIP/NR', temp3 'Nama Petugas', temp4 'CPPT', temp5 'Asuhan Gizi' from temporary1", dialog_simpan) == true) {
                JOptionPane.showMessageDialog(null, "Data petugas nutrisionis berhasil diexport menjadi file excel,..!!!");
            } else {
                JOptionPane.showMessageDialog(null, "Data petugas nutrisionis gagal diexport menjadi file excel,..!!!");
            }
        } else if (nilai.equals("cppt nutrisionis")) {
            Sequel.AutoComitFalse();
            Sequel.queryu("delete from temporary1");
            int row = tabMode13.getRowCount();
            for (int r = 0; r < row; r++) {
                Sequel.menyimpan("temporary1", "'"
                        + tabMode13.getValueAt(r, 0).toString() + "','"
                        + tabMode13.getValueAt(r, 1).toString() + "','"
                        + tabMode13.getValueAt(r, 2).toString() + "','"
                        + tabMode13.getValueAt(r, 3).toString().replaceAll("'", "") + "','"
                        + tabMode13.getValueAt(r, 4).toString() + "','"
                        + tabMode13.getValueAt(r, 5).toString() + "','"
                        + tabMode13.getValueAt(r, 6).toString() + "','"
                        + tabMode13.getValueAt(r, 7).toString() + "','"
                        + TnipPetNutri.getText() + "','"
                        + TnmPetNutri.getText().replaceAll("'", "") + "','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Data CPPT Petugas Nutrisionis");
            }
            Sequel.AutoComitTrue();

            dialog_simpan = Valid.openDialog();
            if (Valid.MyReportToExcelBoolean("SELECT temp1 'No.', temp2 'Tgl. MRS', temp3 'No. RM', temp4 'Nama Pasien', "
                    + "temp5 'Cara Bayar', temp6 'Alamat', temp7 'Rg. Rawat Terakhir', temp8 'Nama DPJP', temp9 'NIP/NR', temp10 'Nama Petugas' from temporary1", dialog_simpan) == true) {
                JOptionPane.showMessageDialog(null, "Data cppt petugas nutrisionis berhasil diexport menjadi file excel,..!!!");
            } else {
                JOptionPane.showMessageDialog(null, "Data cppt petugas nutrisionis gagal diexport menjadi file excel,..!!!");
            }
        } else if (nilai.equals("asuhan nutrisionis")) {
            Sequel.AutoComitFalse();
            Sequel.queryu("delete from temporary1");
            int row = tabMode14.getRowCount();
            for (int r = 0; r < row; r++) {
                Sequel.menyimpan("temporary1", "'"
                        + tabMode14.getValueAt(r, 0).toString() + "','"
                        + tabMode14.getValueAt(r, 1).toString() + "','"
                        + tabMode14.getValueAt(r, 2).toString() + "','"
                        + tabMode14.getValueAt(r, 3).toString().replaceAll("'", "") + "','"
                        + tabMode14.getValueAt(r, 4).toString() + "','"
                        + tabMode14.getValueAt(r, 5).toString() + "','"
                        + tabMode14.getValueAt(r, 6).toString() + "','"
                        + tabMode14.getValueAt(r, 7).toString() + "','"
                        + TnipPetNutri.getText() + "','"
                        + TnmPetNutri.getText().replaceAll("'", "") + "','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Data Asuhan Gizi Petugas Nutrisionis");
            }
            Sequel.AutoComitTrue();

            dialog_simpan = Valid.openDialog();
            if (Valid.MyReportToExcelBoolean("SELECT temp1 'No.', temp2 'Tgl. MRS', temp3 'No. RM', temp4 'Nama Pasien', "
                    + "temp5 'Cara Bayar', temp6 'Alamat', temp7 'Rg. Rawat Terakhir', temp8 'Nama DPJP', temp9 'NIP/NR', temp10 'Nama Petugas' from temporary1", dialog_simpan) == true) {
                JOptionPane.showMessageDialog(null, "Data asuhan petugas nutrisionis berhasil diexport menjadi file excel,..!!!");
            } else {
                JOptionPane.showMessageDialog(null, "Data asuhan petugas nutrisionis gagal diexport menjadi file excel,..!!!");
            }
        } else if (nilai.equals("perawat ruang anak")) {
            Sequel.AutoComitFalse();
            Sequel.queryu("delete from temporary1");
            int row = tabMode5.getRowCount();
            for (int r = 0; r < row; r++) {
                Sequel.menyimpan("temporary1", "'"
                        + tabMode5.getValueAt(r, 0).toString() + "','"
                        + tabMode5.getValueAt(r, 1).toString() + "','"
                        + tabMode5.getValueAt(r, 2).toString().replaceAll("'", "") + "','"
                        + tabMode5.getValueAt(r, 3).toString() + "','"
                        + tabMode5.getValueAt(r, 4).toString() + "','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Data Perawat Rg. Anak");
            }
            Sequel.AutoComitTrue();

            dialog_simpan = Valid.openDialog();
            if (Valid.MyReportToExcelBoolean("SELECT temp1 'No.', temp2 'NIP/NR', temp3 'Nama Perawat', temp4 'CPPT', temp5 'Asesmen Keperawatan Anak' from temporary1", dialog_simpan) == true) {
                JOptionPane.showMessageDialog(null, "Data perawat rg. anak berhasil diexport menjadi file excel,..!!!");
            } else {
                JOptionPane.showMessageDialog(null, "Data perawat rg. anak gagal diexport menjadi file excel,..!!!");
            }
        } else if (nilai.equals("cppt perawat rg anak")) {
            Sequel.AutoComitFalse();
            Sequel.queryu("delete from temporary1");
            int row = tabMode15.getRowCount();
            for (int r = 0; r < row; r++) {
                Sequel.menyimpan("temporary1", "'"
                        + tabMode15.getValueAt(r, 0).toString() + "','"
                        + tabMode15.getValueAt(r, 1).toString() + "','"
                        + tabMode15.getValueAt(r, 2).toString() + "','"
                        + tabMode15.getValueAt(r, 3).toString().replaceAll("'", "") + "','"
                        + tabMode15.getValueAt(r, 4).toString() + "','"
                        + tabMode15.getValueAt(r, 5).toString() + "','"
                        + tabMode15.getValueAt(r, 6).toString() + "','"
                        + tabMode15.getValueAt(r, 7).toString() + "','"
                        + TnipPerawatAnak.getText() + "','"
                        + TnmPerawatAnak.getText().replaceAll("'", "") + "','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Data CPPT Perawat Rg. Anak");
            }
            Sequel.AutoComitTrue();

            dialog_simpan = Valid.openDialog();
            if (Valid.MyReportToExcelBoolean("SELECT temp1 'No.', temp2 'Tgl. MRS', temp3 'No. RM', temp4 'Nama Pasien', "
                    + "temp5 'Cara Bayar', temp6 'Alamat', temp7 'Rg. Rawat Terakhir', temp8 'Nama DPJP', temp9 'NIP/NR', temp10 'Nama Perawat' from temporary1", dialog_simpan) == true) {
                JOptionPane.showMessageDialog(null, "Data cppt perawat rg. anak berhasil diexport menjadi file excel,..!!!");
            } else {
                JOptionPane.showMessageDialog(null, "Data cppt perawat rg. anak gagal diexport menjadi file excel,..!!!");
            }
        } else if (nilai.equals("askep perawat rg anak")) {
            Sequel.AutoComitFalse();
            Sequel.queryu("delete from temporary1");
            int row = tabMode16.getRowCount();
            for (int r = 0; r < row; r++) {
                Sequel.menyimpan("temporary1", "'"
                        + tabMode16.getValueAt(r, 0).toString() + "','"
                        + tabMode16.getValueAt(r, 1).toString() + "','"
                        + tabMode16.getValueAt(r, 2).toString() + "','"
                        + tabMode16.getValueAt(r, 3).toString().replaceAll("'", "") + "','"
                        + tabMode16.getValueAt(r, 4).toString() + "','"
                        + tabMode16.getValueAt(r, 5).toString() + "','"
                        + tabMode16.getValueAt(r, 6).toString() + "','"
                        + tabMode16.getValueAt(r, 7).toString() + "','"
                        + TnipPerawatAnak.getText() + "','"
                        + TnmPerawatAnak.getText().replaceAll("'", "") + "','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Data CPPT Perawat Rg. Anak");
            }
            Sequel.AutoComitTrue();

            dialog_simpan = Valid.openDialog();
            if (Valid.MyReportToExcelBoolean("SELECT temp1 'No.', temp2 'Tgl. MRS', temp3 'No. RM', temp4 'Nama Pasien', "
                    + "temp5 'Cara Bayar', temp6 'Alamat', temp7 'Rg. Rawat Terakhir', temp8 'Nama DPJP', temp9 'NIP/NR', temp10 'Nama Perawat' from temporary1", dialog_simpan) == true) {
                JOptionPane.showMessageDialog(null, "Data askep perawat rg. anak berhasil diexport menjadi file excel,..!!!");
            } else {
                JOptionPane.showMessageDialog(null, "Data askep perawat rg. anak gagal diexport menjadi file excel,..!!!");
            }
        } else if (nilai.equals("perawat ranap anak")) {
            Sequel.AutoComitFalse();
            Sequel.queryu("delete from temporary1");
            int row = tabMode6.getRowCount();
            for (int r = 0; r < row; r++) {
                Sequel.menyimpan("temporary1", "'"
                        + tabMode6.getValueAt(r, 0).toString() + "','"
                        + tabMode6.getValueAt(r, 1).toString() + "','"
                        + tabMode6.getValueAt(r, 2).toString().replaceAll("'", "") + "','"
                        + tabMode6.getValueAt(r, 3).toString() + "','"
                        + tabMode6.getValueAt(r, 4).toString() + "','"
                        + tabMode6.getValueAt(r, 5).toString() + "','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Data Perawat R.Inap (Pasien Anak)");
            }
            Sequel.AutoComitTrue();

            dialog_simpan = Valid.openDialog();
            if (Valid.MyReportToExcelBoolean("SELECT temp1 'No.', temp2 'NIP/NR', temp3 'Nama Perawat', temp4 'CPPT', "
                    + "temp5 'Asesmen Keperawatan Anak', temp6 'Unit Kerja' from temporary1", dialog_simpan) == true) {
                JOptionPane.showMessageDialog(null, "Data perawat rwt. inap (pasien anak) berhasil diexport menjadi file excel,..!!!");
            } else {
                JOptionPane.showMessageDialog(null, "Data perawat rwt. inap (pasien anak) gagal diexport menjadi file excel,..!!!");
            }
        } else if (nilai.equals("cppt perawat ranap anak")) {
            Sequel.AutoComitFalse();
            Sequel.queryu("delete from temporary1");
            int row = tabMode17.getRowCount();
            for (int r = 0; r < row; r++) {
                Sequel.menyimpan("temporary1", "'"
                        + tabMode17.getValueAt(r, 0).toString() + "','"
                        + tabMode17.getValueAt(r, 1).toString() + "','"
                        + tabMode17.getValueAt(r, 2).toString() + "','"
                        + tabMode17.getValueAt(r, 3).toString().replaceAll("'", "") + "','"
                        + tabMode17.getValueAt(r, 4).toString() + "','"
                        + tabMode17.getValueAt(r, 5).toString() + "','"
                        + tabMode17.getValueAt(r, 6).toString() + "','"
                        + tabMode17.getValueAt(r, 7).toString() + "','"
                        + TnipPerawatRanapAnak.getText() + "','"
                        + TnmPerawatRanapAnak.getText().replaceAll("'", "") + "','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Data CPPT Perawat R.Inap (Pasien Anak)");
            }
            Sequel.AutoComitTrue();

            dialog_simpan = Valid.openDialog();
            if (Valid.MyReportToExcelBoolean("SELECT temp1 'No.', temp2 'Tgl. MRS', temp3 'No. RM', temp4 'Nama Pasien', "
                    + "temp5 'Cara Bayar', temp6 'Alamat', temp7 'Rg. Rawat Terakhir', temp8 'Nama DPJP', temp9 'NIP/NR', temp10 'Nama Perawat' from temporary1", dialog_simpan) == true) {
                JOptionPane.showMessageDialog(null, "Data cppt perawat rwt. inap (pasien anak) berhasil diexport menjadi file excel,..!!!");
            } else {
                JOptionPane.showMessageDialog(null, "Data cppt perawat rwt. inap (pasien anak) gagal diexport menjadi file excel,..!!!");
            }
        } else if (nilai.equals("askep perawat ranap anak")) {
            Sequel.AutoComitFalse();
            Sequel.queryu("delete from temporary1");
            int row = tabMode18.getRowCount();
            for (int r = 0; r < row; r++) {
                Sequel.menyimpan("temporary1", "'"
                        + tabMode18.getValueAt(r, 0).toString() + "','"
                        + tabMode18.getValueAt(r, 1).toString() + "','"
                        + tabMode18.getValueAt(r, 2).toString() + "','"
                        + tabMode18.getValueAt(r, 3).toString().replaceAll("'", "") + "','"
                        + tabMode18.getValueAt(r, 4).toString() + "','"
                        + tabMode18.getValueAt(r, 5).toString() + "','"
                        + tabMode18.getValueAt(r, 6).toString() + "','"
                        + tabMode18.getValueAt(r, 7).toString() + "','"
                        + TnipPerawatRanapAnak.getText() + "','"
                        + TnmPerawatRanapAnak.getText().replaceAll("'", "") + "','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Data AsKep Perawat R.Inap (Pasien Anak)");
            }
            Sequel.AutoComitTrue();

            dialog_simpan = Valid.openDialog();
            if (Valid.MyReportToExcelBoolean("SELECT temp1 'No.', temp2 'Tgl. MRS', temp3 'No. RM', temp4 'Nama Pasien', "
                    + "temp5 'Cara Bayar', temp6 'Alamat', temp7 'Rg. Rawat Terakhir', temp8 'Nama DPJP', temp9 'NIP/NR', temp10 'Nama Perawat' from temporary1", dialog_simpan) == true) {
                JOptionPane.showMessageDialog(null, "Data askep perawat rwt. inap (pasien anak) berhasil diexport menjadi file excel,..!!!");
            } else {
                JOptionPane.showMessageDialog(null, "Data askep perawat rwt. inap (pasien anak) gagal diexport menjadi file excel,..!!!");
            }
        } else if (nilai.equals("bidan ranap")) {
            Sequel.AutoComitFalse();
            Sequel.queryu("delete from temporary1");
            int row = tabMode7.getRowCount();
            for (int r = 0; r < row; r++) {
                Sequel.menyimpan("temporary1", "'"
                        + tabMode7.getValueAt(r, 0).toString() + "','"
                        + tabMode7.getValueAt(r, 1).toString() + "','"
                        + tabMode7.getValueAt(r, 2).toString().replaceAll("'", "") + "','"
                        + tabMode7.getValueAt(r, 3).toString() + "','"
                        + tabMode7.getValueAt(r, 4).toString() + "',"
                        + "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Data Bidan R.Inap");
            }
            Sequel.AutoComitTrue();

            dialog_simpan = Valid.openDialog();
            if (Valid.MyReportToExcelBoolean("SELECT temp1 'No.', temp2 'NIP/NR', temp3 'Nama Petugas', temp4 'Jml. CPPT PerPasien', "
                    + "temp5 'Ruang Perawatan' from temporary1", dialog_simpan) == true) {
                JOptionPane.showMessageDialog(null, "Data bidan rwt. inap berhasil diexport menjadi file excel,..!!!");
            } else {
                JOptionPane.showMessageDialog(null, "Data bidan rwt. inap gagal diexport menjadi file excel,..!!!");
            }
        } else if (nilai.equals("cppt bidan ranap")) {
            Sequel.AutoComitFalse();
            Sequel.queryu("delete from temporary1");
            int row = tabMode19.getRowCount();
            for (int r = 0; r < row; r++) {
                Sequel.menyimpan("temporary1", "'"
                        + tabMode19.getValueAt(r, 0).toString() + "','"
                        + tabMode19.getValueAt(r, 1).toString() + "','"
                        + tabMode19.getValueAt(r, 2).toString() + "','"
                        + tabMode19.getValueAt(r, 3).toString().replaceAll("'", "") + "','"
                        + tabMode19.getValueAt(r, 4).toString() + "','"
                        + tabMode19.getValueAt(r, 5).toString() + "','"
                        + tabMode19.getValueAt(r, 6).toString() + "','"
                        + tabMode19.getValueAt(r, 7).toString() + "','"
                        + TnipBidanRanap.getText() + "','"
                        + TnmBidanRanap.getText().replaceAll("'", "") + "','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Data CPPT Bidan R.Inap");
            }
            Sequel.AutoComitTrue();

            dialog_simpan = Valid.openDialog();
            if (Valid.MyReportToExcelBoolean("SELECT temp1 'No.', temp2 'Tgl. MRS', temp3 'No. RM', temp4 'Nama Pasien', "
                    + "temp5 'Cara Bayar', temp6 'Alamat', temp7 'Rg. Rawat Terakhir', temp8 'Nama DPJP', temp9 'NIP/NR', temp10 'Nama Petugas' from temporary1", dialog_simpan) == true) {
                JOptionPane.showMessageDialog(null, "Data cppt bidan rwt. inap berhasil diexport menjadi file excel,..!!!");
            } else {
                JOptionPane.showMessageDialog(null, "Data cppt bidan rwt. inap gagal diexport menjadi file excel,..!!!");
            }
        } else if (nilai.equals("dpjp")) {
            Sequel.AutoComitFalse();
            Sequel.queryu("delete from temporary1");
            int row = tabMode20.getRowCount();
            for (int r = 0; r < row; r++) {
                Sequel.menyimpan("temporary1", "'"
                        + tabMode20.getValueAt(r, 0).toString() + "','"
                        + tabMode20.getValueAt(r, 1).toString() + "','"
                        + tabMode20.getValueAt(r, 2).toString().replaceAll("'", "") + "','"
                        + tabMode20.getValueAt(r, 3).toString() + "','"
                        + tabMode20.getValueAt(r, 4).toString() + "','"
                        + tabMode20.getValueAt(r, 5).toString() + "','"
                        + tabMode20.getValueAt(r, 6).toString() + "','"
                        + tabMode20.getValueAt(r, 7).toString() + "','"
                        + tabMode20.getValueAt(r, 8).toString() + "','"
                        + tabMode20.getValueAt(r, 9).toString() + "','"
                        + tabMode20.getValueAt(r, 10).toString() + "','"
                        + tabMode20.getValueAt(r, 11).toString() + "','"
                        + tabMode20.getValueAt(r, 12).toString() + "','"
                        + tabMode20.getValueAt(r, 13).toString() + "','"
                        + tabMode20.getValueAt(r, 14).toString() + "','"
                        + tabMode20.getValueAt(r, 15).toString() + "','"
                        + tabMode20.getValueAt(r, 16).toString() + "','"
                        + tabMode20.getValueAt(r, 17).toString() + "','','','','','','','','','','','','','','','','','','',''", "Data Bidan R.Inap");
            }
            Sequel.AutoComitTrue();

            dialog_simpan = Valid.openDialog();
            if (Valid.MyReportToExcelBoolean("SELECT temp1 'No.', temp2 'NIP/NR', temp3 'Nama Dokter', temp4 'Spesialis', "
                    + "temp5 'Sbg. DPJP', temp6 'AsMed Bedah', temp7 'AsMed Dewasa', temp8 'AsMed Anak', temp9 'AsMed Perinatologi', temp10 'CPPT', "
                    + "temp11 'e-Resep', temp12 'Resume Medis', temp13 'Pemberian Inf. Edukasi', temp14 'Surat Konsul', temp15 'Surat Jwbn. Konsul', "
                    + "temp16 'Perstjuan./Penlokaan. Tndkn.', temp17 'Portokol Kemoterapi', temp18 'Total RM Terisi' from temporary1", dialog_simpan) == true) {
                JOptionPane.showMessageDialog(null, "Data pemanfaatan e-RM dokter spesialis berhasil diexport menjadi file excel,..!!!");
            } else {
                JOptionPane.showMessageDialog(null, "Data pemanfaatan e-RM dokter spesialis gagal diexport menjadi file excel,..!!!");
            }
        }
    }
}
