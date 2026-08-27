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
package inventory;
import fungsi.WarnaTable;
import fungsi.WarnaTableResepRanap1;
import fungsi.WarnaTableResepRanap2;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import simrskhanza.DlgCariDokter;
import simrskhanza.DlgNotepad;

/**
 *
 * @author perpustakaan
 */
public class DlgCatatanResepBiasaAntibiotik extends javax.swing.JDialog {
    private final DefaultTableModel tabMode2, tabModeResepObat, tabModeFarmasi, tabModeTglBeriObat, tabModeRiwItemObat,
            tabModeResep2, tabModeResepA, tabModeResepB,
            tabMode2An, tabModeResepObatAn, tabModeTglBeriObatAn, tabModeRiwItemObatAn,
            tabModeResep2An, tabModeResepAAn, tabModeResepBAn;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps1, ps2, ps3, psFar, psTglBO, psRiwIO, psR2, psrestor, psR11, psR22,
            ps1Anti, ps2Anti, ps3Anti, psTglBOAnti, psRiwIOAnti, psR2Anti, psrestorAnti, psR11Anti, psR22Anti;
    private ResultSet rs1, rs2, rs3, rsFar, rsTglBO, rsRiwIO, rsR2, rsrestor, rsR11, rsR22,
            rs1Anti, rs2Anti, rs3Anti, rsTglBOAnti, rsRiwIOAnti, rsR2Anti, rsrestorAnti, rsR11Anti, rsR22Anti;
    private int i = 0, x = 0, j = 0, cekResep = 0, cito = 0, iniResep = 0, pilihan = 0;
    public DlgCariDokter dokter = new DlgCariDokter(null, false);
    private String tglPemberianObat = "", resepDipilih = "", tglResep = "", kodepoli = "", status = "",
            jnsKunjungan = "", jamberiobat = "", user = "", riwayatData = "", jenisResep = "", tglResepRiwayat = "", resepPulang = "",
            tglPemberianObatAnti = "", resepDipilihAnti = "", tglResepAnti = "", kodepoliAnti = "", statusAnti = "",
            jnsKunjunganAnti = "", jamberiobatAnti = "", userAnti = "", riwayatDataAnti = "", jenisResepAnti = "", tglResepRiwayatAnti = "", resepPulangAnti = "";

    /**
     * Creates new form DlgPemberianObat
     *
     * @param parent
     * @param modal
     */
    public DlgCatatanResepBiasaAntibiotik(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //data di tabel grid rata tengah
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        
        this.setLocation(8, 1);
        setSize(885, 674);

        tabModeResepObat = new DefaultTableModel(null, new Object[]{
            "P", "No.Rawat", "Tgl.Input", "Jam Input", "Nama Obat", "Status", "Nama Dokter", "Id", "kddokter", 
            "Jns. Resep", "Ini Resep"
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
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbResepObat.setModel(tabModeResepObat);
        tbResepObat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbResepObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 11; i++) {
            TableColumn column = tbResepObat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(105);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setPreferredWidth(80);
            } else if (i == 4) {
                column.setPreferredWidth(240);
            } else if (i == 5) {
                column.setPreferredWidth(50);
            } else if (i == 6) {
                column.setPreferredWidth(260);
            } else if (i == 7) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 8) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 9) {
                column.setPreferredWidth(70);
            } else if (i == 10) {
                column.setPreferredWidth(98);
            }
        }
        tbResepObat.setDefaultRenderer(Object.class, new WarnaTableResepRanap1());
        
        tabModeResepObatAn = new DefaultTableModel(null, new Object[]{
            "P", "No.Rawat", "Tgl.Input", "Jam Input", "Nama Obat", "Status", "Nama Dokter", "Id", "kddokter", 
            "Jns. Resep", "Ini Resep", "Keterangan", "Hari Ke"
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
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbResepObat1.setModel(tabModeResepObatAn);
        tbResepObat1.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbResepObat1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 13; i++) {
            TableColumn column = tbResepObat1.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(105);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setPreferredWidth(80);
            } else if (i == 4) {
                column.setPreferredWidth(240);
            } else if (i == 5) {
                column.setPreferredWidth(50);
            } else if (i == 6) {
                column.setPreferredWidth(260);
            } else if (i == 7) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 8) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 9) {
                column.setPreferredWidth(70);
            } else if (i == 10) {
                column.setPreferredWidth(98);
            } else if (i == 11) {
                column.setPreferredWidth(250);
            } else if (i == 12) {
                column.setPreferredWidth(60);
            }
        }
        tbResepObat1.setDefaultRenderer(Object.class, new WarnaTableResepRanap1());
        
        tabModeFarmasi = new DefaultTableModel(null, new String[]{
            "Nama Obat/Alkes", "Satuan", "Harga (Rp.)", "Stok Apt. IGD (UMUM)", "Stok Apt. Sentral (BPJS)"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbObat.setModel(tabModeFarmasi);
        tbObat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 5; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(350);
            } else if (i == 1) {
                column.setPreferredWidth(60);
            } else if (i == 2) {
                column.setPreferredWidth(75);
            } else if (i == 3) {
                column.setPreferredWidth(130);
            } else if (i == 4) {
                column.setPreferredWidth(130);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbObat.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);        
        tbObat.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbObat.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        
        tabModeTglBeriObat = new DefaultTableModel(null, new Object[]{
            "tgl", "Tanggal", "Jam", "Jlh. Item", "Poli/Inst./Rg. Rawat", "Dokter Peresep"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbTglBeriObat.setModel(tabModeTglBeriObat);
        tbTglBeriObat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbTglBeriObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 6; i++) {
            TableColumn column = tbTglBeriObat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);                
            } else if (i == 1) {
                column.setPreferredWidth(75);
            } else if (i == 2) {
                column.setPreferredWidth(60);
            } else if (i == 3) {
                column.setPreferredWidth(56);
            } else if (i == 4) {
                column.setPreferredWidth(120);
            } else if (i == 5) {
                column.setPreferredWidth(250);
            } 
        }
        tbTglBeriObat.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeTglBeriObatAn = new DefaultTableModel(null, new Object[]{
            "tgl", "Tanggal", "Jam", "Jlh. Item", "Poli/Inst./Rg. Rawat", "Dokter Peresep"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbTglBeriObat1.setModel(tabModeTglBeriObatAn);
        tbTglBeriObat1.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbTglBeriObat1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 6; i++) {
            TableColumn column = tbTglBeriObat1.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);                
            } else if (i == 1) {
                column.setPreferredWidth(75);
            } else if (i == 2) {
                column.setPreferredWidth(60);
            } else if (i == 3) {
                column.setPreferredWidth(56);
            } else if (i == 4) {
                column.setPreferredWidth(120);
            } else if (i == 5) {
                column.setPreferredWidth(250);
            } 
        }
        tbTglBeriObat1.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeRiwItemObat = new DefaultTableModel(null, new Object[]{
            "No.","Tanggal","Nama Obat/Alkes/BHP", "Jumlah"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbItemObat.setModel(tabModeRiwItemObat);
        tbItemObat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbItemObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 4; i++) {
            TableColumn column = tbItemObat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);            
            } else if (i == 1) {
                column.setPreferredWidth(64);            
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(56);
            } 
        }
        tbItemObat.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeRiwItemObatAn = new DefaultTableModel(null, new Object[]{
            "No.","Tanggal","Nama Obat/Alkes/BHP", "Jumlah"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbItemObat1.setModel(tabModeRiwItemObatAn);
        tbItemObat1.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbItemObat1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 4; i++) {
            TableColumn column = tbItemObat1.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);            
            } else if (i == 1) {
                column.setPreferredWidth(64);            
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(56);
            } 
        }
        tbItemObat1.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeResep2 = new DefaultTableModel(null, new Object[]{
            "Cek", "no_rawat", "Tgl. Resep", "jam_input", "Nama Item Obat", "status", "nm_dokter", "id", 
            "Jns. Resep", "ini_resep"
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
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbItemResep.setModel(tabModeResep2);
        tbItemResep.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbItemResep.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 10; i++) {
            TableColumn column = tbItemResep.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(32);
            } else if (i == 1) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 4) {
                column.setPreferredWidth(350);
            } else if (i == 5) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 6) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 7) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 8) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 9) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbItemResep.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeResep2An = new DefaultTableModel(null, new Object[]{
            "Cek", "no_rawat", "Tgl. Resep", "jam_input", "Nama Item Obat", "status", "nm_dokter", "id", 
            "Jns. Resep", "ini_resep", "Keterangan", "Hari Ke"
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
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbItemResepAnti.setModel(tabModeResep2An);
        tbItemResepAnti.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbItemResepAnti.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 12; i++) {
            TableColumn column = tbItemResepAnti.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(32);
            } else if (i == 1) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 4) {
                column.setPreferredWidth(350);
            } else if (i == 5) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 6) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 7) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 8) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 9) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 10) {
                column.setPreferredWidth(250);
            } else if (i == 11) {
                column.setPreferredWidth(60);
            }
        }
        tbItemResepAnti.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode2 = new DefaultTableModel(null, new Object[]{
            "Dilakukan Oleh", "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Resep", "Jam Resep",
            "Tgl. Eksekusi", "Status Data", "noId", "Nama Obat"}) {
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
                column.setPreferredWidth(350);
            } 
        }
        tbRiwayat.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode2An = new DefaultTableModel(null, new Object[]{
            "Dilakukan Oleh", "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Resep", "Jam Resep",
            "Tgl. Eksekusi", "Status Data", "noId", "Nama Obat", "Keterangan", "Hari Ke"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbRiwayat1.setModel(tabMode2An);
        tbRiwayat1.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbRiwayat1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 12; i++) {
            TableColumn column = tbRiwayat1.getColumnModel().getColumn(i);
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
                column.setPreferredWidth(350);
            } else if (i == 10) {
                column.setPreferredWidth(250);
            } else if (i == 11) {
                column.setPreferredWidth(60);
            } 
        }
        tbRiwayat1.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeResepA = new DefaultTableModel(null, new String[]{
            "Tgl. Resep", "Dokter Yang Meresepkan", "tgl_resep", "kdunit"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbPemberianResep.setModel(tabModeResepA);
        tbPemberianResep.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbPemberianResep.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 4; i++) {
            TableColumn column = tbPemberianResep.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(75);
            } else if (i == 1) {
                column.setPreferredWidth(375);
            } else if (i == 2) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 3) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbPemberianResep.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeResepAAn = new DefaultTableModel(null, new String[]{
            "Tgl. Resep", "Dokter Yang Meresepkan", "tgl_resep", "kdunit"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbPemberianResep1.setModel(tabModeResepAAn);
        tbPemberianResep1.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbPemberianResep1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 4; i++) {
            TableColumn column = tbPemberianResep1.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(75);
            } else if (i == 1) {
                column.setPreferredWidth(375);
            } else if (i == 2) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 3) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbPemberianResep1.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeResepB = new DefaultTableModel(null, new Object[]{
            "Cek", "no_rawat", "Tgl. Resep", "Jam Input", "Nama Item Obat", "status", "id", "jnsResep", "tgl_perawatan", "ini_resep"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbItemResep1.setModel(tabModeResepB);
        tbItemResep1.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbItemResep1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 10; i++) {
            TableColumn column = tbItemResep1.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(32);
            } else if (i == 1) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setPreferredWidth(60);
            } else if (i == 4) {
                column.setPreferredWidth(290);
            } else if (i == 5) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 6) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 7) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 8) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 9) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbItemResep1.setDefaultRenderer(Object.class, new WarnaTableResepRanap2());
        
        tabModeResepBAn = new DefaultTableModel(null, new Object[]{
            "Cek", "no_rawat", "Tgl. Resep", "Jam Input", "Nama Item Obat", "status", "id", "jnsResep", "tgl_perawatan", 
            "ini_resep", "Keterangan", "Hari Ke"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbItemResep2.setModel(tabModeResepBAn);
        tbItemResep2.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbItemResep2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 12; i++) {
            TableColumn column = tbItemResep2.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(32);
            } else if (i == 1) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setPreferredWidth(60);
            } else if (i == 4) {
                column.setPreferredWidth(290);
            } else if (i == 5) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 6) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 7) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 8) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 9) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 10) {
                column.setPreferredWidth(250);
            } else if (i == 11) {
                column.setPreferredWidth(60);
            }
        }
        tbItemResep2.setDefaultRenderer(Object.class, new WarnaTableResepRanap2());
        
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {;
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (pilihan == 1) {
                    if (akses.getform().equals("DlgCatatanResepBiasaAntibiotik")) {
                        if (dokter.getTable().getSelectedRow() != -1) {
                            kddokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                            TDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                            BtnSimpan1.requestFocus();
                        }
                    }
                } else if (pilihan == 2) {
                    if (akses.getform().equals("DlgCatatanResepBiasaAntibiotik")) {
                        if (dokter.getTable().getSelectedRow() != -1) {
                            kddokterAn.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                            TDokterAn.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                            BtnSimpan2.requestFocus();
                        }
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
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnSemuanya = new javax.swing.JMenuItem();
        MnDibatalkan = new javax.swing.JMenuItem();
        MnDiCopy = new javax.swing.JMenuItem();
        MnGantiIniResep = new javax.swing.JMenuItem();
        MnGantiDokter = new javax.swing.JMenuItem();
        jPopupMenu1Anti = new javax.swing.JPopupMenu();
        MnSemuanya1 = new javax.swing.JMenuItem();
        MnDibatalkan1 = new javax.swing.JMenuItem();
        MnDiCopy1 = new javax.swing.JMenuItem();
        MnGantiIniResep1 = new javax.swing.JMenuItem();
        MnGantiDokter1 = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        MnRiwayatData = new javax.swing.JMenuItem();
        jPopupMenu2Anti = new javax.swing.JPopupMenu();
        MnRiwayatData1 = new javax.swing.JMenuItem();
        jPopupMenu3 = new javax.swing.JPopupMenu();
        MnCopyObat = new javax.swing.JMenuItem();
        jPopupMenu3Anti = new javax.swing.JPopupMenu();
        MnCopyObat1 = new javax.swing.JMenuItem();
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
        WindowRiwayatResep = new javax.swing.JDialog();
        internalFrame15 = new widget.InternalFrame();
        panelGlass6 = new widget.panelisi();
        chkRanap = new widget.RadioButton();
        chkRalan = new widget.RadioButton();
        jLabel68 = new widget.Label();
        cmbConteng = new widget.ComboBox();
        jLabel13 = new widget.Label();
        LCount2 = new widget.Label();
        BtnCopyResep = new widget.Button();
        BtnCloseIn8 = new widget.Button();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        Scroll34 = new widget.ScrollPane();
        tbPemberianResep = new widget.Table();
        jPanel7 = new javax.swing.JPanel();
        Scroll36 = new widget.ScrollPane();
        tbItemResep1 = new widget.Table();
        WindowGantiDokter = new javax.swing.JDialog();
        internalFrame3 = new widget.InternalFrame();
        panelisi3 = new widget.panelisi();
        jLabel14 = new widget.Label();
        kddokter = new widget.TextBox();
        TDokter = new widget.TextBox();
        btnCariDokter = new widget.Button();
        panelisi5 = new widget.panelisi();
        BtnSimpan1 = new widget.Button();
        BtnCloseIn1 = new widget.Button();
        WindowRiwayatAnti = new javax.swing.JDialog();
        internalFrame14 = new widget.InternalFrame();
        internalFrame20 = new widget.InternalFrame();
        internalFrame21 = new widget.InternalFrame();
        jLabel34 = new widget.Label();
        DTPCari5 = new widget.Tanggal();
        jLabel35 = new widget.Label();
        DTPCari6 = new widget.Tanggal();
        jLabel36 = new widget.Label();
        TCari3 = new widget.TextBox();
        BtnCari3 = new widget.Button();
        jLabel37 = new widget.Label();
        LCount3 = new widget.Label();
        internalFrame22 = new widget.InternalFrame();
        BtnAll2 = new widget.Button();
        BtnRestor1 = new widget.Button();
        BtnCloseIn11 = new widget.Button();
        Scroll7 = new widget.ScrollPane();
        tbRiwayat1 = new widget.Table();
        WindowRiwayatResepAnti = new javax.swing.JDialog();
        internalFrame16 = new widget.InternalFrame();
        panelGlass7 = new widget.panelisi();
        chkRanapAn = new widget.RadioButton();
        chkRalanAn = new widget.RadioButton();
        jLabel69 = new widget.Label();
        cmbContengAn = new widget.ComboBox();
        jLabel16 = new widget.Label();
        LCount4 = new widget.Label();
        BtnCopyResepAn = new widget.Button();
        BtnCloseIn9 = new widget.Button();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        Scroll39 = new widget.ScrollPane();
        tbPemberianResep1 = new widget.Table();
        jPanel12 = new javax.swing.JPanel();
        Scroll40 = new widget.ScrollPane();
        tbItemResep2 = new widget.Table();
        WindowGantiDokterAnti = new javax.swing.JDialog();
        internalFrame4 = new widget.InternalFrame();
        panelisi7 = new widget.panelisi();
        jLabel17 = new widget.Label();
        kddokterAn = new widget.TextBox();
        TDokterAn = new widget.TextBox();
        btnCariDokter1 = new widget.Button();
        panelisi8 = new widget.panelisi();
        BtnSimpan3 = new widget.Button();
        BtnCloseIn2 = new widget.Button();
        noIdObat = new widget.TextBox();
        TIdObat = new widget.TextBox();
        noIdObatCopy = new widget.TextBox();
        Scroll35 = new widget.ScrollPane();
        tbItemResep = new widget.Table();
        buttonGroup1 = new javax.swing.ButtonGroup();
        noIdObatAnti = new widget.TextBox();
        TIdObatAnti = new widget.TextBox();
        noIdObatCopyAnti = new widget.TextBox();
        Scroll38 = new widget.ScrollPane();
        tbItemResepAnti = new widget.Table();
        buttonGroupAnti = new javax.swing.ButtonGroup();
        internalFrame1 = new widget.InternalFrame();
        panelGlass13 = new widget.panelisi();
        jLabel54 = new widget.Label();
        DTPCariA = new widget.Tanggal();
        jLabel55 = new widget.Label();
        DTPCariB = new widget.Tanggal();
        jLabel56 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCariObat = new widget.Button();
        jLabel57 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel5 = new widget.Label();
        TtglLahir = new widget.TextBox();
        jLabel9 = new widget.Label();
        Tjk = new widget.TextBox();
        jLabel10 = new widget.Label();
        Tcara_byr = new widget.TextBox();
        jLabel11 = new widget.Label();
        LCount = new widget.Label();
        BtnResep = new widget.Button();
        panelGlass12 = new widget.panelisi();
        TabResep = new javax.swing.JTabbedPane();
        panelGlass9 = new widget.panelisi();
        jPanel4 = new javax.swing.JPanel();
        panelGlass16 = new widget.panelisi();
        jLabel53 = new widget.Label();
        TResepObat = new widget.TextBox();
        BtnHapusNmObat = new widget.Button();
        ChkCito = new widget.CekBox();
        jLabel58 = new widget.Label();
        cmbIniResep = new widget.ComboBox();
        BtnCopyResepTerakhir = new widget.Button();
        Scroll4 = new widget.ScrollPane();
        tbResepObat = new widget.Table();
        PanelInput1 = new javax.swing.JPanel();
        ChkInput1 = new widget.CekBox();
        PanelRiwayatObat = new javax.swing.JPanel();
        panelGlass18 = new widget.panelisi();
        Scroll44 = new widget.ScrollPane();
        tbTglBeriObat = new widget.Table();
        panelGlass17 = new widget.panelisi();
        ChkPoli1 = new widget.CekBox();
        Scroll45 = new widget.ScrollPane();
        tbItemObat = new widget.Table();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        jLabel105 = new widget.Label();
        cmbPilihCetak = new widget.ComboBox();
        BtnPrint = new widget.Button();
        BtnNotepad = new widget.Button();
        BtnKeluar = new widget.Button();
        jLabel12 = new widget.Label();
        cmbJnsResep = new widget.ComboBox();
        BtnSetuju = new widget.Button();
        panelGlass10 = new widget.panelisi();
        jPanel8 = new javax.swing.JPanel();
        panelGlass19 = new widget.panelisi();
        jLabel59 = new widget.Label();
        TResepObatAn = new widget.TextBox();
        ChkCitoAn = new widget.CekBox();
        jLabel60 = new widget.Label();
        cmbIniResepAn = new widget.ComboBox();
        BtnCopyResepTerakhir1 = new widget.Button();
        jLabel61 = new widget.Label();
        Tket = new widget.TextBox();
        cmbHari = new widget.ComboBox();
        jLabel62 = new widget.Label();
        BtnHapusNmObatAnti = new widget.Button();
        Scroll5 = new widget.ScrollPane();
        tbResepObat1 = new widget.Table();
        PanelInputAn = new javax.swing.JPanel();
        ChkInputAn = new widget.CekBox();
        PanelRiwayatObatAn = new javax.swing.JPanel();
        panelGlass20 = new widget.panelisi();
        Scroll46 = new widget.ScrollPane();
        tbTglBeriObat1 = new widget.Table();
        panelGlass21 = new widget.panelisi();
        ChkPoliAn = new widget.CekBox();
        Scroll47 = new widget.ScrollPane();
        tbItemObat1 = new widget.Table();
        panelGlass11 = new widget.panelisi();
        BtnSimpan2 = new widget.Button();
        BtnHapus1 = new widget.Button();
        BtnEdit1 = new widget.Button();
        jLabel106 = new widget.Label();
        cmbPilihCetakAn = new widget.ComboBox();
        BtnPrint1 = new widget.Button();
        BtnNotepad1 = new widget.Button();
        BtnKeluar1 = new widget.Button();
        jLabel15 = new widget.Label();
        cmbJnsResep1 = new widget.ComboBox();
        BtnSetuju1 = new widget.Button();
        jPanel2 = new javax.swing.JPanel();
        panelisi4 = new widget.panelisi();
        BtnPilih = new widget.Button();
        cmbObat = new widget.ComboBox();
        jLabel64 = new widget.Label();
        TCariObat = new widget.TextBox();
        BtnCekObat = new widget.Button();
        Scroll33 = new widget.ScrollPane();
        tbObat = new widget.Table();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnSemuanya.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSemuanya.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSemuanya.setText("Conteng Semua Item");
        MnSemuanya.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSemuanya.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSemuanya.setIconTextGap(5);
        MnSemuanya.setName("MnSemuanya"); // NOI18N
        MnSemuanya.setPreferredSize(new java.awt.Dimension(185, 26));
        MnSemuanya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSemuanyaActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnSemuanya);

        MnDibatalkan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDibatalkan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDibatalkan.setText("Hilangkan Semua Conteng");
        MnDibatalkan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDibatalkan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDibatalkan.setIconTextGap(5);
        MnDibatalkan.setName("MnDibatalkan"); // NOI18N
        MnDibatalkan.setPreferredSize(new java.awt.Dimension(185, 26));
        MnDibatalkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDibatalkanActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnDibatalkan);

        MnDiCopy.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDiCopy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/paste.png"))); // NOI18N
        MnDiCopy.setText("Resep Di Copy");
        MnDiCopy.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDiCopy.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDiCopy.setIconTextGap(5);
        MnDiCopy.setName("MnDiCopy"); // NOI18N
        MnDiCopy.setPreferredSize(new java.awt.Dimension(185, 26));
        MnDiCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDiCopyActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnDiCopy);

        MnGantiIniResep.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnGantiIniResep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnGantiIniResep.setText("Ganti Semua Ini Resep");
        MnGantiIniResep.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnGantiIniResep.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnGantiIniResep.setIconTextGap(5);
        MnGantiIniResep.setName("MnGantiIniResep"); // NOI18N
        MnGantiIniResep.setPreferredSize(new java.awt.Dimension(185, 26));
        MnGantiIniResep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnGantiIniResepActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnGantiIniResep);

        MnGantiDokter.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnGantiDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnGantiDokter.setText("Ganti Dokter Meresepkan");
        MnGantiDokter.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnGantiDokter.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnGantiDokter.setIconTextGap(5);
        MnGantiDokter.setName("MnGantiDokter"); // NOI18N
        MnGantiDokter.setPreferredSize(new java.awt.Dimension(185, 26));
        MnGantiDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnGantiDokterActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnGantiDokter);

        jPopupMenu1Anti.setName("jPopupMenu1Anti"); // NOI18N

        MnSemuanya1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSemuanya1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSemuanya1.setText("Conteng Semua Item");
        MnSemuanya1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSemuanya1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSemuanya1.setIconTextGap(5);
        MnSemuanya1.setName("MnSemuanya1"); // NOI18N
        MnSemuanya1.setPreferredSize(new java.awt.Dimension(185, 26));
        MnSemuanya1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSemuanya1ActionPerformed(evt);
            }
        });
        jPopupMenu1Anti.add(MnSemuanya1);

        MnDibatalkan1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDibatalkan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDibatalkan1.setText("Hilangkan Semua Conteng");
        MnDibatalkan1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDibatalkan1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDibatalkan1.setIconTextGap(5);
        MnDibatalkan1.setName("MnDibatalkan1"); // NOI18N
        MnDibatalkan1.setPreferredSize(new java.awt.Dimension(185, 26));
        MnDibatalkan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDibatalkan1ActionPerformed(evt);
            }
        });
        jPopupMenu1Anti.add(MnDibatalkan1);

        MnDiCopy1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDiCopy1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/paste.png"))); // NOI18N
        MnDiCopy1.setText("Resep Di Copy");
        MnDiCopy1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDiCopy1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDiCopy1.setIconTextGap(5);
        MnDiCopy1.setName("MnDiCopy1"); // NOI18N
        MnDiCopy1.setPreferredSize(new java.awt.Dimension(185, 26));
        MnDiCopy1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDiCopy1ActionPerformed(evt);
            }
        });
        jPopupMenu1Anti.add(MnDiCopy1);

        MnGantiIniResep1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnGantiIniResep1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnGantiIniResep1.setText("Ganti Semua Ini Resep");
        MnGantiIniResep1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnGantiIniResep1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnGantiIniResep1.setIconTextGap(5);
        MnGantiIniResep1.setName("MnGantiIniResep1"); // NOI18N
        MnGantiIniResep1.setPreferredSize(new java.awt.Dimension(185, 26));
        MnGantiIniResep1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnGantiIniResep1ActionPerformed(evt);
            }
        });
        jPopupMenu1Anti.add(MnGantiIniResep1);

        MnGantiDokter1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnGantiDokter1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnGantiDokter1.setText("Ganti Dokter Meresepkan");
        MnGantiDokter1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnGantiDokter1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnGantiDokter1.setIconTextGap(5);
        MnGantiDokter1.setName("MnGantiDokter1"); // NOI18N
        MnGantiDokter1.setPreferredSize(new java.awt.Dimension(185, 26));
        MnGantiDokter1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnGantiDokter1ActionPerformed(evt);
            }
        });
        jPopupMenu1Anti.add(MnGantiDokter1);

        jPopupMenu2.setName("jPopupMenu2"); // NOI18N

        MnRiwayatData.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRiwayatData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRiwayatData.setText("Riwayat Data Terhapus");
        MnRiwayatData.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRiwayatData.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRiwayatData.setIconTextGap(5);
        MnRiwayatData.setName("MnRiwayatData"); // NOI18N
        MnRiwayatData.setPreferredSize(new java.awt.Dimension(170, 26));
        MnRiwayatData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRiwayatDataActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnRiwayatData);

        jPopupMenu2Anti.setName("jPopupMenu2Anti"); // NOI18N

        MnRiwayatData1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRiwayatData1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRiwayatData1.setText("Riwayat Data Terhapus");
        MnRiwayatData1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRiwayatData1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRiwayatData1.setIconTextGap(5);
        MnRiwayatData1.setName("MnRiwayatData1"); // NOI18N
        MnRiwayatData1.setPreferredSize(new java.awt.Dimension(170, 26));
        MnRiwayatData1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRiwayatData1ActionPerformed(evt);
            }
        });
        jPopupMenu2Anti.add(MnRiwayatData1);

        jPopupMenu3.setName("jPopupMenu3"); // NOI18N

        MnCopyObat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCopyObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCopyObat.setText("Copy Resep");
        MnCopyObat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCopyObat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCopyObat.setIconTextGap(5);
        MnCopyObat.setName("MnCopyObat"); // NOI18N
        MnCopyObat.setPreferredSize(new java.awt.Dimension(100, 26));
        MnCopyObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCopyObatActionPerformed(evt);
            }
        });
        jPopupMenu3.add(MnCopyObat);

        jPopupMenu3Anti.setName("jPopupMenu3Anti"); // NOI18N

        MnCopyObat1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCopyObat1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCopyObat1.setText("Copy Resep");
        MnCopyObat1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCopyObat1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCopyObat1.setIconTextGap(5);
        MnCopyObat1.setName("MnCopyObat1"); // NOI18N
        MnCopyObat1.setPreferredSize(new java.awt.Dimension(100, 26));
        MnCopyObat1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCopyObat1ActionPerformed(evt);
            }
        });
        jPopupMenu3Anti.add(MnCopyObat1);

        WindowRiwayat.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowRiwayat.setName("WindowRiwayat"); // NOI18N
        WindowRiwayat.setUndecorated(true);
        WindowRiwayat.setResizable(false);

        internalFrame13.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Riwayat Catatan Resep Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
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

        DTPCari3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "26-07-2026" }));
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

        DTPCari4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "26-07-2026" }));
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
        tbRiwayat.getTableHeader().setReorderingAllowed(false);
        Scroll6.setViewportView(tbRiwayat);

        internalFrame13.add(Scroll6, java.awt.BorderLayout.CENTER);

        WindowRiwayat.getContentPane().add(internalFrame13, java.awt.BorderLayout.CENTER);

        WindowRiwayatResep.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowRiwayatResep.setName("WindowRiwayatResep"); // NOI18N
        WindowRiwayatResep.setUndecorated(true);
        WindowRiwayatResep.setResizable(false);

        internalFrame15.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Riwayat Resep Pasien Dirawat Saat Ini ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame15.setName("internalFrame15"); // NOI18N
        internalFrame15.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame15.setLayout(new java.awt.BorderLayout());

        panelGlass6.setName("panelGlass6"); // NOI18N
        panelGlass6.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 5, 9));

        chkRanap.setBackground(new java.awt.Color(242, 242, 242));
        chkRanap.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup1.add(chkRanap);
        chkRanap.setText("Resep Rawat Inap");
        chkRanap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkRanap.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkRanap.setName("chkRanap"); // NOI18N
        chkRanap.setPreferredSize(new java.awt.Dimension(125, 23));
        chkRanap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkRanapActionPerformed(evt);
            }
        });
        panelGlass6.add(chkRanap);

        chkRalan.setBackground(new java.awt.Color(242, 242, 242));
        chkRalan.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup1.add(chkRalan);
        chkRalan.setText("Resep Rawat Jalan/IGD");
        chkRalan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkRalan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkRalan.setName("chkRalan"); // NOI18N
        chkRalan.setPreferredSize(new java.awt.Dimension(145, 23));
        chkRalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkRalanActionPerformed(evt);
            }
        });
        panelGlass6.add(chkRalan);

        jLabel68.setForeground(new java.awt.Color(0, 0, 0));
        jLabel68.setText("Conteng Item Resep : ");
        jLabel68.setName("jLabel68"); // NOI18N
        jLabel68.setPreferredSize(new java.awt.Dimension(120, 23));
        panelGlass6.add(jLabel68);

        cmbConteng.setForeground(new java.awt.Color(0, 0, 0));
        cmbConteng.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Semuanya", "Dibatalkan" }));
        cmbConteng.setName("cmbConteng"); // NOI18N
        cmbConteng.setPreferredSize(new java.awt.Dimension(96, 23));
        cmbConteng.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbContengItemStateChanged(evt);
            }
        });
        panelGlass6.add(cmbConteng);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Record :");
        jLabel13.setName("jLabel13"); // NOI18N
        jLabel13.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass6.add(jLabel13);

        LCount2.setForeground(new java.awt.Color(0, 0, 0));
        LCount2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount2.setText("0");
        LCount2.setName("LCount2"); // NOI18N
        LCount2.setPreferredSize(new java.awt.Dimension(40, 23));
        panelGlass6.add(LCount2);

        BtnCopyResep.setForeground(new java.awt.Color(0, 0, 0));
        BtnCopyResep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        BtnCopyResep.setMnemonic('R');
        BtnCopyResep.setText("Copy Resep");
        BtnCopyResep.setToolTipText("Alt+R");
        BtnCopyResep.setName("BtnCopyResep"); // NOI18N
        BtnCopyResep.setPreferredSize(new java.awt.Dimension(115, 30));
        BtnCopyResep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCopyResepActionPerformed(evt);
            }
        });
        BtnCopyResep.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCopyResepKeyPressed(evt);
            }
        });
        panelGlass6.add(BtnCopyResep);

        BtnCloseIn8.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn8.setMnemonic('U');
        BtnCloseIn8.setText("Tutup");
        BtnCloseIn8.setToolTipText("Alt+U");
        BtnCloseIn8.setName("BtnCloseIn8"); // NOI18N
        BtnCloseIn8.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnCloseIn8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn8ActionPerformed(evt);
            }
        });
        panelGlass6.add(BtnCloseIn8);

        internalFrame15.add(panelGlass6, java.awt.BorderLayout.PAGE_END);

        jPanel5.setName("jPanel5"); // NOI18N
        jPanel5.setOpaque(false);
        jPanel5.setPreferredSize(new java.awt.Dimension(816, 102));
        jPanel5.setLayout(new java.awt.GridLayout(1, 2));

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(237, 242, 232)), ".: Pemberian Resep", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        jPanel6.setName("jPanel6"); // NOI18N
        jPanel6.setOpaque(false);
        jPanel6.setPreferredSize(new java.awt.Dimension(250, 102));
        jPanel6.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll34.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(245, 255, 235)));
        Scroll34.setToolTipText("Silahkan Klik salah satu tgl. resep utk. melihat resep yg. pernah diberikan");
        Scroll34.setName("Scroll34"); // NOI18N
        Scroll34.setOpaque(true);

        tbPemberianResep.setToolTipText("Silahkan Klik salah satu tgl. resep utk. melihat resep yg. pernah diberikan");
        tbPemberianResep.setName("tbPemberianResep"); // NOI18N
        tbPemberianResep.getTableHeader().setReorderingAllowed(false);
        tbPemberianResep.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPemberianResepMouseClicked(evt);
            }
        });
        tbPemberianResep.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPemberianResepKeyPressed(evt);
            }
        });
        Scroll34.setViewportView(tbPemberianResep);

        jPanel6.add(Scroll34, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel6);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(237, 242, 232)), ".: Daftar Item Resep Obat", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        jPanel7.setName("jPanel7"); // NOI18N
        jPanel7.setOpaque(false);
        jPanel7.setPreferredSize(new java.awt.Dimension(350, 102));
        jPanel7.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll36.setName("Scroll36"); // NOI18N
        Scroll36.setOpaque(true);

        tbItemResep1.setToolTipText("Silahkan conteng item resep obat yg. dipilih / gunakan fitur conteng.");
        tbItemResep1.setName("tbItemResep1"); // NOI18N
        tbItemResep1.getTableHeader().setReorderingAllowed(false);
        tbItemResep1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbItemResep1MouseClicked(evt);
            }
        });
        tbItemResep1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbItemResep1KeyPressed(evt);
            }
        });
        Scroll36.setViewportView(tbItemResep1);

        jPanel7.add(Scroll36, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel7);

        internalFrame15.add(jPanel5, java.awt.BorderLayout.CENTER);

        WindowRiwayatResep.getContentPane().add(internalFrame15, java.awt.BorderLayout.CENTER);

        WindowGantiDokter.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowGantiDokter.setName("WindowGantiDokter"); // NOI18N
        WindowGantiDokter.setUndecorated(true);
        WindowGantiDokter.setResizable(false);

        internalFrame3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Ganti Dokter Yang Meresepkan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame3.setLayout(new java.awt.BorderLayout());

        panelisi3.setBackground(new java.awt.Color(255, 150, 255));
        panelisi3.setName("panelisi3"); // NOI18N
        panelisi3.setPreferredSize(new java.awt.Dimension(100, 45));
        panelisi3.setLayout(null);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Nama Dokter : ");
        jLabel14.setName("jLabel14"); // NOI18N
        panelisi3.add(jLabel14);
        jLabel14.setBounds(0, 10, 100, 23);

        kddokter.setEditable(false);
        kddokter.setForeground(new java.awt.Color(0, 0, 0));
        kddokter.setName("kddokter"); // NOI18N
        panelisi3.add(kddokter);
        kddokter.setBounds(100, 10, 90, 23);

        TDokter.setEditable(false);
        TDokter.setForeground(new java.awt.Color(0, 0, 0));
        TDokter.setName("TDokter"); // NOI18N
        panelisi3.add(TDokter);
        TDokter.setBounds(193, 10, 350, 23);

        btnCariDokter.setForeground(new java.awt.Color(0, 0, 0));
        btnCariDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/download24.png"))); // NOI18N
        btnCariDokter.setMnemonic('7');
        btnCariDokter.setToolTipText("ALt+7");
        btnCariDokter.setName("btnCariDokter"); // NOI18N
        btnCariDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariDokterActionPerformed(evt);
            }
        });
        panelisi3.add(btnCariDokter);
        btnCariDokter.setBounds(550, 10, 28, 23);

        internalFrame3.add(panelisi3, java.awt.BorderLayout.CENTER);

        panelisi5.setBackground(new java.awt.Color(255, 150, 255));
        panelisi5.setName("panelisi5"); // NOI18N
        panelisi5.setPreferredSize(new java.awt.Dimension(100, 47));
        panelisi5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 4, 9));

        BtnSimpan1.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan1.setMnemonic('S');
        BtnSimpan1.setText("Simpan");
        BtnSimpan1.setToolTipText("Alt+S");
        BtnSimpan1.setName("BtnSimpan1"); // NOI18N
        BtnSimpan1.setPreferredSize(new java.awt.Dimension(110, 30));
        BtnSimpan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan1ActionPerformed(evt);
            }
        });
        panelisi5.add(BtnSimpan1);

        BtnCloseIn1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn1.setMnemonic('U');
        BtnCloseIn1.setText("Tutup");
        BtnCloseIn1.setToolTipText("Alt+U");
        BtnCloseIn1.setName("BtnCloseIn1"); // NOI18N
        BtnCloseIn1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnCloseIn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn1ActionPerformed(evt);
            }
        });
        panelisi5.add(BtnCloseIn1);

        internalFrame3.add(panelisi5, java.awt.BorderLayout.PAGE_END);

        WindowGantiDokter.getContentPane().add(internalFrame3, java.awt.BorderLayout.CENTER);

        WindowRiwayatAnti.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowRiwayatAnti.setName("WindowRiwayatAnti"); // NOI18N
        WindowRiwayatAnti.setUndecorated(true);
        WindowRiwayatAnti.setResizable(false);

        internalFrame14.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Riwayat Catatan Resep Antibiotik Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame14.setName("internalFrame14"); // NOI18N
        internalFrame14.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame14.setLayout(new java.awt.BorderLayout());

        internalFrame20.setMinimumSize(new java.awt.Dimension(0, 50));
        internalFrame20.setName("internalFrame20"); // NOI18N
        internalFrame20.setPreferredSize(new java.awt.Dimension(400, 88));
        internalFrame20.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame20.setLayout(new java.awt.BorderLayout());

        internalFrame21.setMinimumSize(new java.awt.Dimension(0, 50));
        internalFrame21.setName("internalFrame21"); // NOI18N
        internalFrame21.setPreferredSize(new java.awt.Dimension(400, 44));
        internalFrame21.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame21.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 7, 9));

        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Tanggal :");
        jLabel34.setName("jLabel34"); // NOI18N
        jLabel34.setPreferredSize(new java.awt.Dimension(60, 23));
        internalFrame21.add(jLabel34);

        DTPCari5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "26-07-2026" }));
        DTPCari5.setDisplayFormat("dd-MM-yyyy");
        DTPCari5.setName("DTPCari5"); // NOI18N
        DTPCari5.setOpaque(false);
        DTPCari5.setPreferredSize(new java.awt.Dimension(90, 23));
        internalFrame21.add(DTPCari5);

        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("s.d.");
        jLabel35.setName("jLabel35"); // NOI18N
        jLabel35.setPreferredSize(new java.awt.Dimension(23, 23));
        internalFrame21.add(jLabel35);

        DTPCari6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "26-07-2026" }));
        DTPCari6.setDisplayFormat("dd-MM-yyyy");
        DTPCari6.setName("DTPCari6"); // NOI18N
        DTPCari6.setOpaque(false);
        DTPCari6.setPreferredSize(new java.awt.Dimension(90, 23));
        internalFrame21.add(DTPCari6);

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Key Word :");
        jLabel36.setName("jLabel36"); // NOI18N
        jLabel36.setPreferredSize(new java.awt.Dimension(60, 23));
        internalFrame21.add(jLabel36);

        TCari3.setForeground(new java.awt.Color(0, 0, 0));
        TCari3.setName("TCari3"); // NOI18N
        TCari3.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari3KeyPressed(evt);
            }
        });
        internalFrame21.add(TCari3);

        BtnCari3.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari3.setMnemonic('1');
        BtnCari3.setText("Tampilkan Data");
        BtnCari3.setToolTipText("Alt+1");
        BtnCari3.setName("BtnCari3"); // NOI18N
        BtnCari3.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCari3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari3ActionPerformed(evt);
            }
        });
        BtnCari3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCari3KeyPressed(evt);
            }
        });
        internalFrame21.add(BtnCari3);

        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setText("Record :");
        jLabel37.setName("jLabel37"); // NOI18N
        jLabel37.setPreferredSize(new java.awt.Dimension(65, 23));
        internalFrame21.add(jLabel37);

        LCount3.setForeground(new java.awt.Color(0, 0, 0));
        LCount3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount3.setText("0");
        LCount3.setName("LCount3"); // NOI18N
        LCount3.setPreferredSize(new java.awt.Dimension(50, 23));
        internalFrame21.add(LCount3);

        internalFrame20.add(internalFrame21, java.awt.BorderLayout.CENTER);

        internalFrame22.setMinimumSize(new java.awt.Dimension(0, 50));
        internalFrame22.setName("internalFrame22"); // NOI18N
        internalFrame22.setPreferredSize(new java.awt.Dimension(400, 44));
        internalFrame22.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame22.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 7, 9));

        BtnAll2.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll2.setMnemonic('2');
        BtnAll2.setText("Semua Data");
        BtnAll2.setToolTipText("Alt+2");
        BtnAll2.setName("BtnAll2"); // NOI18N
        BtnAll2.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnAll2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAll2ActionPerformed(evt);
            }
        });
        BtnAll2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAll2KeyPressed(evt);
            }
        });
        internalFrame22.add(BtnAll2);

        BtnRestor1.setForeground(new java.awt.Color(0, 0, 0));
        BtnRestor1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/42a.png"))); // NOI18N
        BtnRestor1.setMnemonic('U');
        BtnRestor1.setText("Restore");
        BtnRestor1.setToolTipText("Alt+U");
        BtnRestor1.setName("BtnRestor1"); // NOI18N
        BtnRestor1.setPreferredSize(new java.awt.Dimension(100, 23));
        BtnRestor1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRestor1ActionPerformed(evt);
            }
        });
        internalFrame22.add(BtnRestor1);

        BtnCloseIn11.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn11.setMnemonic('U');
        BtnCloseIn11.setText("Tutup");
        BtnCloseIn11.setToolTipText("Alt+U");
        BtnCloseIn11.setName("BtnCloseIn11"); // NOI18N
        BtnCloseIn11.setPreferredSize(new java.awt.Dimension(90, 23));
        BtnCloseIn11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn11ActionPerformed(evt);
            }
        });
        internalFrame22.add(BtnCloseIn11);

        internalFrame20.add(internalFrame22, java.awt.BorderLayout.PAGE_END);

        internalFrame14.add(internalFrame20, java.awt.BorderLayout.PAGE_END);

        Scroll7.setName("Scroll7"); // NOI18N
        Scroll7.setOpaque(true);

        tbRiwayat1.setToolTipText("Silahkan pilih salah satu data yang mau dihapus/direstore");
        tbRiwayat1.setName("tbRiwayat1"); // NOI18N
        tbRiwayat1.getTableHeader().setReorderingAllowed(false);
        Scroll7.setViewportView(tbRiwayat1);

        internalFrame14.add(Scroll7, java.awt.BorderLayout.CENTER);

        WindowRiwayatAnti.getContentPane().add(internalFrame14, java.awt.BorderLayout.CENTER);

        WindowRiwayatResepAnti.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowRiwayatResepAnti.setName("WindowRiwayatResepAnti"); // NOI18N
        WindowRiwayatResepAnti.setUndecorated(true);
        WindowRiwayatResepAnti.setResizable(false);

        internalFrame16.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Riwayat Resep Pasien Dirawat Saat Ini ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame16.setName("internalFrame16"); // NOI18N
        internalFrame16.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame16.setLayout(new java.awt.BorderLayout());

        panelGlass7.setName("panelGlass7"); // NOI18N
        panelGlass7.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 5, 9));

        chkRanapAn.setBackground(new java.awt.Color(242, 242, 242));
        chkRanapAn.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroupAnti.add(chkRanapAn);
        chkRanapAn.setText("Resep Rawat Inap");
        chkRanapAn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkRanapAn.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkRanapAn.setName("chkRanapAn"); // NOI18N
        chkRanapAn.setPreferredSize(new java.awt.Dimension(125, 23));
        chkRanapAn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkRanapAnActionPerformed(evt);
            }
        });
        panelGlass7.add(chkRanapAn);

        chkRalanAn.setBackground(new java.awt.Color(242, 242, 242));
        chkRalanAn.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroupAnti.add(chkRalanAn);
        chkRalanAn.setText("Resep Rawat Jalan/IGD");
        chkRalanAn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkRalanAn.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkRalanAn.setName("chkRalanAn"); // NOI18N
        chkRalanAn.setPreferredSize(new java.awt.Dimension(145, 23));
        chkRalanAn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkRalanAnActionPerformed(evt);
            }
        });
        panelGlass7.add(chkRalanAn);

        jLabel69.setForeground(new java.awt.Color(0, 0, 0));
        jLabel69.setText("Conteng Item Resep : ");
        jLabel69.setName("jLabel69"); // NOI18N
        jLabel69.setPreferredSize(new java.awt.Dimension(120, 23));
        panelGlass7.add(jLabel69);

        cmbContengAn.setForeground(new java.awt.Color(0, 0, 0));
        cmbContengAn.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Semuanya", "Dibatalkan" }));
        cmbContengAn.setName("cmbContengAn"); // NOI18N
        cmbContengAn.setPreferredSize(new java.awt.Dimension(96, 23));
        cmbContengAn.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbContengAnItemStateChanged(evt);
            }
        });
        panelGlass7.add(cmbContengAn);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Record :");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass7.add(jLabel16);

        LCount4.setForeground(new java.awt.Color(0, 0, 0));
        LCount4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount4.setText("0");
        LCount4.setName("LCount4"); // NOI18N
        LCount4.setPreferredSize(new java.awt.Dimension(40, 23));
        panelGlass7.add(LCount4);

        BtnCopyResepAn.setForeground(new java.awt.Color(0, 0, 0));
        BtnCopyResepAn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        BtnCopyResepAn.setMnemonic('R');
        BtnCopyResepAn.setText("Copy Resep");
        BtnCopyResepAn.setToolTipText("Alt+R");
        BtnCopyResepAn.setName("BtnCopyResepAn"); // NOI18N
        BtnCopyResepAn.setPreferredSize(new java.awt.Dimension(115, 30));
        BtnCopyResepAn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCopyResepAnActionPerformed(evt);
            }
        });
        BtnCopyResepAn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCopyResepAnKeyPressed(evt);
            }
        });
        panelGlass7.add(BtnCopyResepAn);

        BtnCloseIn9.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn9.setMnemonic('U');
        BtnCloseIn9.setText("Tutup");
        BtnCloseIn9.setToolTipText("Alt+U");
        BtnCloseIn9.setName("BtnCloseIn9"); // NOI18N
        BtnCloseIn9.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnCloseIn9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn9ActionPerformed(evt);
            }
        });
        panelGlass7.add(BtnCloseIn9);

        internalFrame16.add(panelGlass7, java.awt.BorderLayout.PAGE_END);

        jPanel10.setName("jPanel10"); // NOI18N
        jPanel10.setOpaque(false);
        jPanel10.setPreferredSize(new java.awt.Dimension(816, 102));
        jPanel10.setLayout(new java.awt.GridLayout(1, 2));

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(237, 242, 232)), ".: Pemberian Resep", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        jPanel11.setName("jPanel11"); // NOI18N
        jPanel11.setOpaque(false);
        jPanel11.setPreferredSize(new java.awt.Dimension(250, 102));
        jPanel11.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll39.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(245, 255, 235)));
        Scroll39.setToolTipText("Silahkan Klik salah satu tgl. resep utk. melihat resep yg. pernah diberikan");
        Scroll39.setName("Scroll39"); // NOI18N
        Scroll39.setOpaque(true);

        tbPemberianResep1.setToolTipText("Silahkan Klik salah satu tgl. resep utk. melihat resep yg. pernah diberikan");
        tbPemberianResep1.setName("tbPemberianResep1"); // NOI18N
        tbPemberianResep1.getTableHeader().setReorderingAllowed(false);
        tbPemberianResep1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPemberianResep1MouseClicked(evt);
            }
        });
        tbPemberianResep1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPemberianResep1KeyPressed(evt);
            }
        });
        Scroll39.setViewportView(tbPemberianResep1);

        jPanel11.add(Scroll39, java.awt.BorderLayout.CENTER);

        jPanel10.add(jPanel11);

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(237, 242, 232)), ".: Daftar Item Resep Obat", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        jPanel12.setName("jPanel12"); // NOI18N
        jPanel12.setOpaque(false);
        jPanel12.setPreferredSize(new java.awt.Dimension(350, 102));
        jPanel12.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll40.setName("Scroll40"); // NOI18N
        Scroll40.setOpaque(true);

        tbItemResep2.setToolTipText("Silahkan conteng item resep obat yg. dipilih / gunakan fitur conteng.");
        tbItemResep2.setName("tbItemResep2"); // NOI18N
        tbItemResep2.getTableHeader().setReorderingAllowed(false);
        tbItemResep2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbItemResep2MouseClicked(evt);
            }
        });
        tbItemResep2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbItemResep2KeyPressed(evt);
            }
        });
        Scroll40.setViewportView(tbItemResep2);

        jPanel12.add(Scroll40, java.awt.BorderLayout.CENTER);

        jPanel10.add(jPanel12);

        internalFrame16.add(jPanel10, java.awt.BorderLayout.CENTER);

        WindowRiwayatResepAnti.getContentPane().add(internalFrame16, java.awt.BorderLayout.CENTER);

        WindowGantiDokterAnti.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowGantiDokterAnti.setName("WindowGantiDokterAnti"); // NOI18N
        WindowGantiDokterAnti.setUndecorated(true);
        WindowGantiDokterAnti.setResizable(false);

        internalFrame4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Ganti Dokter Yang Meresepkan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame4.setLayout(new java.awt.BorderLayout());

        panelisi7.setBackground(new java.awt.Color(255, 150, 255));
        panelisi7.setName("panelisi7"); // NOI18N
        panelisi7.setPreferredSize(new java.awt.Dimension(100, 45));
        panelisi7.setLayout(null);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Nama Dokter : ");
        jLabel17.setName("jLabel17"); // NOI18N
        panelisi7.add(jLabel17);
        jLabel17.setBounds(0, 10, 100, 23);

        kddokterAn.setEditable(false);
        kddokterAn.setForeground(new java.awt.Color(0, 0, 0));
        kddokterAn.setName("kddokterAn"); // NOI18N
        panelisi7.add(kddokterAn);
        kddokterAn.setBounds(100, 10, 90, 23);

        TDokterAn.setEditable(false);
        TDokterAn.setForeground(new java.awt.Color(0, 0, 0));
        TDokterAn.setName("TDokterAn"); // NOI18N
        panelisi7.add(TDokterAn);
        TDokterAn.setBounds(193, 10, 350, 23);

        btnCariDokter1.setForeground(new java.awt.Color(0, 0, 0));
        btnCariDokter1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/download24.png"))); // NOI18N
        btnCariDokter1.setMnemonic('7');
        btnCariDokter1.setToolTipText("ALt+7");
        btnCariDokter1.setName("btnCariDokter1"); // NOI18N
        btnCariDokter1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariDokter1ActionPerformed(evt);
            }
        });
        panelisi7.add(btnCariDokter1);
        btnCariDokter1.setBounds(550, 10, 28, 23);

        internalFrame4.add(panelisi7, java.awt.BorderLayout.CENTER);

        panelisi8.setBackground(new java.awt.Color(255, 150, 255));
        panelisi8.setName("panelisi8"); // NOI18N
        panelisi8.setPreferredSize(new java.awt.Dimension(100, 47));
        panelisi8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 4, 9));

        BtnSimpan3.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan3.setMnemonic('S');
        BtnSimpan3.setText("Simpan");
        BtnSimpan3.setToolTipText("Alt+S");
        BtnSimpan3.setName("BtnSimpan3"); // NOI18N
        BtnSimpan3.setPreferredSize(new java.awt.Dimension(110, 30));
        BtnSimpan3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan3ActionPerformed(evt);
            }
        });
        panelisi8.add(BtnSimpan3);

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
        panelisi8.add(BtnCloseIn2);

        internalFrame4.add(panelisi8, java.awt.BorderLayout.PAGE_END);

        WindowGantiDokterAnti.getContentPane().add(internalFrame4, java.awt.BorderLayout.CENTER);

        noIdObat.setForeground(new java.awt.Color(0, 0, 0));
        noIdObat.setHighlighter(null);
        noIdObat.setName("noIdObat"); // NOI18N

        TIdObat.setEnabled(false);
        TIdObat.setHighlighter(null);
        TIdObat.setName("TIdObat"); // NOI18N
        TIdObat.setPreferredSize(new java.awt.Dimension(1, 1));

        noIdObatCopy.setEnabled(false);
        noIdObatCopy.setHighlighter(null);
        noIdObatCopy.setName("noIdObatCopy"); // NOI18N
        noIdObatCopy.setPreferredSize(new java.awt.Dimension(1, 1));

        Scroll35.setName("Scroll35"); // NOI18N
        Scroll35.setOpaque(true);

        tbItemResep.setName("tbItemResep"); // NOI18N
        tbItemResep.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbItemResepMouseClicked(evt);
            }
        });
        tbItemResep.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbItemResepKeyPressed(evt);
            }
        });
        Scroll35.setViewportView(tbItemResep);

        noIdObatAnti.setForeground(new java.awt.Color(0, 0, 0));
        noIdObatAnti.setHighlighter(null);
        noIdObatAnti.setName("noIdObatAnti"); // NOI18N

        TIdObatAnti.setEnabled(false);
        TIdObatAnti.setHighlighter(null);
        TIdObatAnti.setName("TIdObatAnti"); // NOI18N
        TIdObatAnti.setPreferredSize(new java.awt.Dimension(1, 1));

        noIdObatCopyAnti.setEnabled(false);
        noIdObatCopyAnti.setHighlighter(null);
        noIdObatCopyAnti.setName("noIdObatCopyAnti"); // NOI18N
        noIdObatCopyAnti.setPreferredSize(new java.awt.Dimension(1, 1));

        Scroll38.setName("Scroll38"); // NOI18N
        Scroll38.setOpaque(true);

        tbItemResepAnti.setName("tbItemResepAnti"); // NOI18N
        tbItemResepAnti.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbItemResepAntiMouseClicked(evt);
            }
        });
        tbItemResepAnti.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbItemResepAntiKeyPressed(evt);
            }
        });
        Scroll38.setViewportView(tbItemResepAnti);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Pemberian / Catatan Resep Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setComponentPopupMenu(jPopupMenu2);
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass13.setName("panelGlass13"); // NOI18N
        panelGlass13.setPreferredSize(new java.awt.Dimension(44, 105));
        panelGlass13.setLayout(null);

        jLabel54.setForeground(new java.awt.Color(0, 0, 0));
        jLabel54.setText("Tgl. Resep : ");
        jLabel54.setName("jLabel54"); // NOI18N
        jLabel54.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass13.add(jLabel54);
        jLabel54.setBounds(0, 66, 105, 23);

        DTPCariA.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "26-07-2026" }));
        DTPCariA.setDisplayFormat("dd-MM-yyyy");
        DTPCariA.setName("DTPCariA"); // NOI18N
        DTPCariA.setOpaque(false);
        DTPCariA.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass13.add(DTPCariA);
        DTPCariA.setBounds(105, 66, 90, 23);

        jLabel55.setForeground(new java.awt.Color(0, 0, 0));
        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel55.setText("s.d.");
        jLabel55.setName("jLabel55"); // NOI18N
        jLabel55.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass13.add(jLabel55);
        jLabel55.setBounds(200, 66, 23, 23);

        DTPCariB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "26-07-2026" }));
        DTPCariB.setDisplayFormat("dd-MM-yyyy");
        DTPCariB.setName("DTPCariB"); // NOI18N
        DTPCariB.setOpaque(false);
        DTPCariB.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass13.add(DTPCariB);
        DTPCariB.setBounds(230, 66, 90, 23);

        jLabel56.setForeground(new java.awt.Color(0, 0, 0));
        jLabel56.setText("Key Word : ");
        jLabel56.setName("jLabel56"); // NOI18N
        jLabel56.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass13.add(jLabel56);
        jLabel56.setBounds(320, 66, 80, 23);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(205, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass13.add(TCari);
        TCari.setBounds(404, 66, 270, 23);

        BtnCariObat.setForeground(new java.awt.Color(0, 0, 0));
        BtnCariObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCariObat.setMnemonic('3');
        BtnCariObat.setText("Tampilkan Data");
        BtnCariObat.setToolTipText("Alt+3");
        BtnCariObat.setName("BtnCariObat"); // NOI18N
        BtnCariObat.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCariObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariObatActionPerformed(evt);
            }
        });
        panelGlass13.add(BtnCariObat);
        BtnCariObat.setBounds(680, 66, 130, 23);

        jLabel57.setForeground(new java.awt.Color(0, 0, 0));
        jLabel57.setText("Pasien : ");
        jLabel57.setName("jLabel57"); // NOI18N
        jLabel57.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass13.add(jLabel57);
        jLabel57.setBounds(6, 10, 97, 23);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        panelGlass13.add(TNoRw);
        TNoRw.setBounds(105, 10, 131, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        panelGlass13.add(TNoRM);
        TNoRM.setBounds(240, 10, 70, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setName("TPasien"); // NOI18N
        panelGlass13.add(TPasien);
        TPasien.setBounds(315, 10, 360, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Tgl. Lahir : ");
        jLabel5.setName("jLabel5"); // NOI18N
        panelGlass13.add(jLabel5);
        jLabel5.setBounds(0, 38, 105, 23);

        TtglLahir.setEditable(false);
        TtglLahir.setForeground(new java.awt.Color(0, 0, 0));
        TtglLahir.setName("TtglLahir"); // NOI18N
        panelGlass13.add(TtglLahir);
        TtglLahir.setBounds(105, 38, 100, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Jenis Kelamin : ");
        jLabel9.setName("jLabel9"); // NOI18N
        panelGlass13.add(jLabel9);
        jLabel9.setBounds(204, 38, 90, 23);

        Tjk.setEditable(false);
        Tjk.setForeground(new java.awt.Color(0, 0, 0));
        Tjk.setName("Tjk"); // NOI18N
        panelGlass13.add(Tjk);
        Tjk.setBounds(297, 38, 90, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Cara Bayar : ");
        jLabel10.setName("jLabel10"); // NOI18N
        panelGlass13.add(jLabel10);
        jLabel10.setBounds(387, 38, 76, 23);

        Tcara_byr.setEditable(false);
        Tcara_byr.setForeground(new java.awt.Color(0, 0, 0));
        Tcara_byr.setName("Tcara_byr"); // NOI18N
        panelGlass13.add(Tcara_byr);
        Tcara_byr.setBounds(465, 38, 210, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Record :");
        jLabel11.setName("jLabel11"); // NOI18N
        jLabel11.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass13.add(jLabel11);
        jLabel11.setBounds(820, 66, 65, 23);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass13.add(LCount);
        LCount.setBounds(889, 66, 50, 23);

        BtnResep.setForeground(new java.awt.Color(0, 0, 0));
        BtnResep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/tasksgroup.png"))); // NOI18N
        BtnResep.setMnemonic('8');
        BtnResep.setText("Riwayat Resep");
        BtnResep.setToolTipText("Alt+8");
        BtnResep.setName("BtnResep"); // NOI18N
        BtnResep.setPreferredSize(new java.awt.Dimension(140, 23));
        BtnResep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnResepActionPerformed(evt);
            }
        });
        panelGlass13.add(BtnResep);
        BtnResep.setBounds(680, 10, 145, 23);

        internalFrame1.add(panelGlass13, java.awt.BorderLayout.PAGE_START);

        panelGlass12.setName("panelGlass12"); // NOI18N
        panelGlass12.setPreferredSize(new java.awt.Dimension(100, 48));
        panelGlass12.setLayout(new java.awt.BorderLayout());

        TabResep.setBackground(new java.awt.Color(254, 255, 254));
        TabResep.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TabResep.setName("TabResep"); // NOI18N
        TabResep.setPreferredSize(new java.awt.Dimension(0, 2000));
        TabResep.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabResepMouseClicked(evt);
            }
        });

        panelGlass9.setComponentPopupMenu(jPopupMenu2);
        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(100, 56));
        panelGlass9.setLayout(new java.awt.BorderLayout());

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(237, 242, 232)), ".: Item Obat/Resep yang diberikan ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        jPanel4.setComponentPopupMenu(jPopupMenu2);
        jPanel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jPanel4.setName("jPanel4"); // NOI18N
        jPanel4.setOpaque(false);
        jPanel4.setPreferredSize(new java.awt.Dimension(660, 102));
        jPanel4.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass16.setComponentPopupMenu(jPopupMenu2);
        panelGlass16.setName("panelGlass16"); // NOI18N
        panelGlass16.setPreferredSize(new java.awt.Dimension(44, 40));
        panelGlass16.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 8));

        jLabel53.setForeground(new java.awt.Color(0, 0, 0));
        jLabel53.setText("Nama Obat :");
        jLabel53.setName("jLabel53"); // NOI18N
        jLabel53.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass16.add(jLabel53);

        TResepObat.setForeground(new java.awt.Color(0, 0, 0));
        TResepObat.setName("TResepObat"); // NOI18N
        TResepObat.setPreferredSize(new java.awt.Dimension(370, 24));
        TResepObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TResepObatKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TResepObatKeyTyped(evt);
            }
        });
        panelGlass16.add(TResepObat);

        BtnHapusNmObat.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusNmObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/delete-16x16.png"))); // NOI18N
        BtnHapusNmObat.setMnemonic('P');
        BtnHapusNmObat.setToolTipText("Hapus nama obat.");
        BtnHapusNmObat.setName("BtnHapusNmObat"); // NOI18N
        BtnHapusNmObat.setPreferredSize(new java.awt.Dimension(30, 23));
        BtnHapusNmObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusNmObatActionPerformed(evt);
            }
        });
        panelGlass16.add(BtnHapusNmObat);

        ChkCito.setBackground(new java.awt.Color(255, 255, 250));
        ChkCito.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkCito.setForeground(new java.awt.Color(0, 0, 0));
        ChkCito.setText("Resep CITO");
        ChkCito.setBorderPainted(true);
        ChkCito.setBorderPaintedFlat(true);
        ChkCito.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkCito.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkCito.setName("ChkCito"); // NOI18N
        ChkCito.setOpaque(false);
        ChkCito.setPreferredSize(new java.awt.Dimension(85, 23));
        panelGlass16.add(ChkCito);

        jLabel58.setForeground(new java.awt.Color(0, 0, 0));
        jLabel58.setText("Ini Resep :");
        jLabel58.setName("jLabel58"); // NOI18N
        jLabel58.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass16.add(jLabel58);

        cmbIniResep.setForeground(new java.awt.Color(0, 0, 0));
        cmbIniResep.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Dalam Perawatan", "Pulang" }));
        cmbIniResep.setName("cmbIniResep"); // NOI18N
        cmbIniResep.setPreferredSize(new java.awt.Dimension(117, 24));
        panelGlass16.add(cmbIniResep);

        BtnCopyResepTerakhir.setForeground(new java.awt.Color(0, 0, 0));
        BtnCopyResepTerakhir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/paste.png"))); // NOI18N
        BtnCopyResepTerakhir.setMnemonic('P');
        BtnCopyResepTerakhir.setText("Copy Resep Terakhir");
        BtnCopyResepTerakhir.setToolTipText("");
        BtnCopyResepTerakhir.setName("BtnCopyResepTerakhir"); // NOI18N
        BtnCopyResepTerakhir.setPreferredSize(new java.awt.Dimension(170, 23));
        BtnCopyResepTerakhir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCopyResepTerakhirActionPerformed(evt);
            }
        });
        panelGlass16.add(BtnCopyResepTerakhir);

        jPanel4.add(panelGlass16, java.awt.BorderLayout.PAGE_START);

        Scroll4.setName("Scroll4"); // NOI18N
        Scroll4.setOpaque(true);

        tbResepObat.setAutoCreateRowSorter(true);
        tbResepObat.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbResepObat.setComponentPopupMenu(jPopupMenu1);
        tbResepObat.setName("tbResepObat"); // NOI18N
        tbResepObat.getTableHeader().setReorderingAllowed(false);
        tbResepObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbResepObatMouseClicked(evt);
            }
        });
        tbResepObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbResepObatKeyPressed(evt);
            }
        });
        Scroll4.setViewportView(tbResepObat);

        jPanel4.add(Scroll4, java.awt.BorderLayout.CENTER);

        PanelInput1.setName("PanelInput1"); // NOI18N
        PanelInput1.setOpaque(false);
        PanelInput1.setPreferredSize(new java.awt.Dimension(816, 260));
        PanelInput1.setLayout(new java.awt.BorderLayout(1, 1));

        ChkInput1.setForeground(new java.awt.Color(0, 0, 0));
        ChkInput1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput1.setText(".: Riwayat Obat Farmasi");
        ChkInput1.setToolTipText("");
        ChkInput1.setBorderPainted(true);
        ChkInput1.setBorderPaintedFlat(true);
        ChkInput1.setComponentPopupMenu(jPopupMenu2);
        ChkInput1.setFocusable(false);
        ChkInput1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        ChkInput1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInput1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput1.setName("ChkInput1"); // NOI18N
        ChkInput1.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInput1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput1.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInput1ActionPerformed(evt);
            }
        });
        PanelInput1.add(ChkInput1, java.awt.BorderLayout.PAGE_START);

        PanelRiwayatObat.setName("PanelRiwayatObat"); // NOI18N
        PanelRiwayatObat.setOpaque(false);
        PanelRiwayatObat.setPreferredSize(new java.awt.Dimension(816, 150));
        PanelRiwayatObat.setLayout(new javax.swing.BoxLayout(PanelRiwayatObat, javax.swing.BoxLayout.LINE_AXIS));

        panelGlass18.setName("panelGlass18"); // NOI18N
        panelGlass18.setPreferredSize(new java.awt.Dimension(340, 40));
        panelGlass18.setLayout(new java.awt.BorderLayout());

        Scroll44.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ":: Tgl. Pemberian Obat ::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        Scroll44.setComponentPopupMenu(jPopupMenu2);
        Scroll44.setName("Scroll44"); // NOI18N
        Scroll44.setOpaque(true);
        Scroll44.setPreferredSize(new java.awt.Dimension(150, 130));

        tbTglBeriObat.setAutoCreateRowSorter(true);
        tbTglBeriObat.setToolTipText("Silahkan klik salah satu tgl. utk melihat obatnya");
        tbTglBeriObat.setName("tbTglBeriObat"); // NOI18N
        tbTglBeriObat.getTableHeader().setReorderingAllowed(false);
        tbTglBeriObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTglBeriObatMouseClicked(evt);
            }
        });
        tbTglBeriObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbTglBeriObatKeyPressed(evt);
            }
        });
        Scroll44.setViewportView(tbTglBeriObat);

        panelGlass18.add(Scroll44, java.awt.BorderLayout.CENTER);

        panelGlass17.setName("panelGlass17"); // NOI18N
        panelGlass17.setPreferredSize(new java.awt.Dimension(44, 33));
        panelGlass17.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 3));

        ChkPoli1.setBorder(null);
        ChkPoli1.setForeground(new java.awt.Color(0, 0, 0));
        ChkPoli1.setSelected(true);
        ChkPoli1.setText("Hanya dipoli/inst. ini");
        ChkPoli1.setBorderPainted(true);
        ChkPoli1.setBorderPaintedFlat(true);
        ChkPoli1.setComponentPopupMenu(jPopupMenu2);
        ChkPoli1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ChkPoli1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkPoli1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkPoli1.setName("ChkPoli1"); // NOI18N
        ChkPoli1.setOpaque(false);
        ChkPoli1.setPreferredSize(new java.awt.Dimension(400, 23));
        ChkPoli1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkPoli1ActionPerformed(evt);
            }
        });
        panelGlass17.add(ChkPoli1);

        panelGlass18.add(panelGlass17, java.awt.BorderLayout.PAGE_START);

        PanelRiwayatObat.add(panelGlass18);

        Scroll45.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ":: Item Obat Sesuai Yang Diresepkan ::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        Scroll45.setComponentPopupMenu(jPopupMenu2);
        Scroll45.setName("Scroll45"); // NOI18N
        Scroll45.setOpaque(true);
        Scroll45.setPreferredSize(new java.awt.Dimension(500, 423));

        tbItemObat.setAutoCreateRowSorter(true);
        tbItemObat.setToolTipText("Silahkan klik untuk memilih data obatnya");
        tbItemObat.setComponentPopupMenu(jPopupMenu3);
        tbItemObat.setName("tbItemObat"); // NOI18N
        tbItemObat.getTableHeader().setReorderingAllowed(false);
        tbItemObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbItemObatMouseClicked(evt);
            }
        });
        tbItemObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbItemObatKeyPressed(evt);
            }
        });
        Scroll45.setViewportView(tbItemObat);

        PanelRiwayatObat.add(Scroll45);

        PanelInput1.add(PanelRiwayatObat, java.awt.BorderLayout.CENTER);

        jPanel4.add(PanelInput1, java.awt.BorderLayout.PAGE_END);

        panelGlass9.add(jPanel4, java.awt.BorderLayout.CENTER);

        panelGlass8.setComponentPopupMenu(jPopupMenu2);
        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(100, 48));
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

        jLabel105.setForeground(new java.awt.Color(0, 0, 0));
        jLabel105.setText("Cetak Dalam Bentuk :");
        jLabel105.setName("jLabel105"); // NOI18N
        jLabel105.setPreferredSize(new java.awt.Dimension(120, 23));
        panelGlass8.add(jLabel105);

        cmbPilihCetak.setForeground(new java.awt.Color(0, 0, 0));
        cmbPilihCetak.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TTE (QR Code)", "TTD Basah" }));
        cmbPilihCetak.setName("cmbPilihCetak"); // NOI18N
        cmbPilihCetak.setPreferredSize(new java.awt.Dimension(105, 23));
        panelGlass8.add(cmbPilihCetak);

        BtnPrint.setForeground(new java.awt.Color(0, 0, 0));
        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/PrinterSettings.png"))); // NOI18N
        BtnPrint.setMnemonic('T');
        BtnPrint.setText("Cetak Resep");
        BtnPrint.setToolTipText("Alt+T");
        BtnPrint.setName("BtnPrint"); // NOI18N
        BtnPrint.setPreferredSize(new java.awt.Dimension(125, 30));
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

        BtnNotepad.setForeground(new java.awt.Color(0, 0, 0));
        BtnNotepad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        BtnNotepad.setMnemonic('N');
        BtnNotepad.setText("Notepad");
        BtnNotepad.setToolTipText("Alt+N");
        BtnNotepad.setName("BtnNotepad"); // NOI18N
        BtnNotepad.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnNotepad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNotepadActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnNotepad);

        BtnKeluar.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnKeluar.setMnemonic('T');
        BtnKeluar.setText("Tutup");
        BtnKeluar.setToolTipText("Alt+T");
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

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Ganti Semua Resep Menjadi :");
        jLabel12.setName("jLabel12"); // NOI18N
        jLabel12.setPreferredSize(new java.awt.Dimension(160, 23));
        panelGlass8.add(jLabel12);

        cmbJnsResep.setForeground(new java.awt.Color(0, 0, 0));
        cmbJnsResep.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "CITO", "BIASA" }));
        cmbJnsResep.setName("cmbJnsResep"); // NOI18N
        cmbJnsResep.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass8.add(cmbJnsResep);

        BtnSetuju.setForeground(new java.awt.Color(0, 0, 0));
        BtnSetuju.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/checked.png"))); // NOI18N
        BtnSetuju.setMnemonic('U');
        BtnSetuju.setText("Setuju");
        BtnSetuju.setToolTipText("Alt+U");
        BtnSetuju.setGlassColor(new java.awt.Color(0, 153, 0));
        BtnSetuju.setName("BtnSetuju"); // NOI18N
        BtnSetuju.setPreferredSize(new java.awt.Dimension(90, 30));
        BtnSetuju.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSetujuActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnSetuju);

        panelGlass9.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        TabResep.addTab("Resep OBAT LAINYA", panelGlass9);

        panelGlass10.setComponentPopupMenu(jPopupMenu2);
        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(100, 48));
        panelGlass10.setLayout(new java.awt.BorderLayout());

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(237, 242, 232)), ".: Item Obat/Resep yang diberikan ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        jPanel8.setName("jPanel8"); // NOI18N
        jPanel8.setOpaque(false);
        jPanel8.setPreferredSize(new java.awt.Dimension(660, 102));
        jPanel8.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass19.setComponentPopupMenu(jPopupMenu2Anti);
        panelGlass19.setName("panelGlass19"); // NOI18N
        panelGlass19.setPreferredSize(new java.awt.Dimension(44, 75));
        panelGlass19.setLayout(null);

        jLabel59.setForeground(new java.awt.Color(0, 0, 0));
        jLabel59.setText("Nama Obat :");
        jLabel59.setName("jLabel59"); // NOI18N
        jLabel59.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass19.add(jLabel59);
        jLabel59.setBounds(0, 9, 75, 23);

        TResepObatAn.setForeground(new java.awt.Color(0, 0, 0));
        TResepObatAn.setName("TResepObatAn"); // NOI18N
        TResepObatAn.setPreferredSize(new java.awt.Dimension(370, 24));
        TResepObatAn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TResepObatAnKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TResepObatAnKeyTyped(evt);
            }
        });
        panelGlass19.add(TResepObatAn);
        TResepObatAn.setBounds(81, 9, 370, 24);

        ChkCitoAn.setBackground(new java.awt.Color(255, 255, 250));
        ChkCitoAn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkCitoAn.setForeground(new java.awt.Color(0, 0, 0));
        ChkCitoAn.setText("Resep CITO");
        ChkCitoAn.setBorderPainted(true);
        ChkCitoAn.setBorderPaintedFlat(true);
        ChkCitoAn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkCitoAn.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkCitoAn.setName("ChkCitoAn"); // NOI18N
        ChkCitoAn.setOpaque(false);
        ChkCitoAn.setPreferredSize(new java.awt.Dimension(85, 23));
        panelGlass19.add(ChkCitoAn);
        ChkCitoAn.setBounds(490, 9, 85, 23);

        jLabel60.setForeground(new java.awt.Color(0, 0, 0));
        jLabel60.setText("Ini Resep :");
        jLabel60.setName("jLabel60"); // NOI18N
        jLabel60.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass19.add(jLabel60);
        jLabel60.setBounds(584, 9, 65, 23);

        cmbIniResepAn.setForeground(new java.awt.Color(0, 0, 0));
        cmbIniResepAn.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Dalam Perawatan", "Pulang" }));
        cmbIniResepAn.setName("cmbIniResepAn"); // NOI18N
        cmbIniResepAn.setPreferredSize(new java.awt.Dimension(117, 24));
        panelGlass19.add(cmbIniResepAn);
        cmbIniResepAn.setBounds(655, 9, 117, 24);

        BtnCopyResepTerakhir1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCopyResepTerakhir1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/paste.png"))); // NOI18N
        BtnCopyResepTerakhir1.setMnemonic('P');
        BtnCopyResepTerakhir1.setText("Copy Resep Terakhir");
        BtnCopyResepTerakhir1.setToolTipText("");
        BtnCopyResepTerakhir1.setName("BtnCopyResepTerakhir1"); // NOI18N
        BtnCopyResepTerakhir1.setPreferredSize(new java.awt.Dimension(170, 23));
        BtnCopyResepTerakhir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCopyResepTerakhir1ActionPerformed(evt);
            }
        });
        panelGlass19.add(BtnCopyResepTerakhir1);
        BtnCopyResepTerakhir1.setBounds(780, 9, 170, 23);

        jLabel61.setForeground(new java.awt.Color(0, 0, 0));
        jLabel61.setText("Hari Ke :");
        jLabel61.setName("jLabel61"); // NOI18N
        jLabel61.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass19.add(jLabel61);
        jLabel61.setBounds(0, 37, 75, 23);

        Tket.setForeground(new java.awt.Color(0, 0, 153));
        Tket.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Tket.setName("Tket"); // NOI18N
        Tket.setPreferredSize(new java.awt.Dimension(370, 24));
        Tket.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketKeyPressed(evt);
            }
        });
        panelGlass19.add(Tket);
        Tket.setBounds(221, 37, 680, 24);

        cmbHari.setForeground(new java.awt.Color(0, 0, 0));
        cmbHari.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        cmbHari.setName("cmbHari"); // NOI18N
        cmbHari.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbHari.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbHariMouseReleased(evt);
            }
        });
        cmbHari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbHariActionPerformed(evt);
            }
        });
        panelGlass19.add(cmbHari);
        cmbHari.setBounds(81, 37, 45, 23);

        jLabel62.setForeground(new java.awt.Color(255, 0, 51));
        jLabel62.setText("Keterangan :");
        jLabel62.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel62.setName("jLabel62"); // NOI18N
        jLabel62.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass19.add(jLabel62);
        jLabel62.setBounds(126, 37, 90, 23);

        BtnHapusNmObatAnti.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusNmObatAnti.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/delete-16x16.png"))); // NOI18N
        BtnHapusNmObatAnti.setMnemonic('P');
        BtnHapusNmObatAnti.setToolTipText("Hapus nama obat.");
        BtnHapusNmObatAnti.setName("BtnHapusNmObatAnti"); // NOI18N
        BtnHapusNmObatAnti.setPreferredSize(new java.awt.Dimension(170, 23));
        BtnHapusNmObatAnti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusNmObatAntiActionPerformed(evt);
            }
        });
        panelGlass19.add(BtnHapusNmObatAnti);
        BtnHapusNmObatAnti.setBounds(454, 9, 30, 23);

        jPanel8.add(panelGlass19, java.awt.BorderLayout.PAGE_START);

        Scroll5.setName("Scroll5"); // NOI18N
        Scroll5.setOpaque(true);

        tbResepObat1.setAutoCreateRowSorter(true);
        tbResepObat1.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbResepObat1.setComponentPopupMenu(jPopupMenu1Anti);
        tbResepObat1.setName("tbResepObat1"); // NOI18N
        tbResepObat1.getTableHeader().setReorderingAllowed(false);
        tbResepObat1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbResepObat1MouseClicked(evt);
            }
        });
        tbResepObat1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbResepObat1KeyPressed(evt);
            }
        });
        Scroll5.setViewportView(tbResepObat1);

        jPanel8.add(Scroll5, java.awt.BorderLayout.CENTER);

        PanelInputAn.setName("PanelInputAn"); // NOI18N
        PanelInputAn.setOpaque(false);
        PanelInputAn.setPreferredSize(new java.awt.Dimension(816, 260));
        PanelInputAn.setLayout(new java.awt.BorderLayout(1, 1));

        ChkInputAn.setForeground(new java.awt.Color(0, 0, 0));
        ChkInputAn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInputAn.setText(".: Riwayat Obat Farmasi");
        ChkInputAn.setToolTipText("");
        ChkInputAn.setBorderPainted(true);
        ChkInputAn.setBorderPaintedFlat(true);
        ChkInputAn.setFocusable(false);
        ChkInputAn.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        ChkInputAn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInputAn.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInputAn.setName("ChkInputAn"); // NOI18N
        ChkInputAn.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInputAn.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInputAn.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInputAn.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInputAn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInputAnActionPerformed(evt);
            }
        });
        PanelInputAn.add(ChkInputAn, java.awt.BorderLayout.PAGE_START);

        PanelRiwayatObatAn.setName("PanelRiwayatObatAn"); // NOI18N
        PanelRiwayatObatAn.setOpaque(false);
        PanelRiwayatObatAn.setPreferredSize(new java.awt.Dimension(816, 150));
        PanelRiwayatObatAn.setLayout(new javax.swing.BoxLayout(PanelRiwayatObatAn, javax.swing.BoxLayout.LINE_AXIS));

        panelGlass20.setName("panelGlass20"); // NOI18N
        panelGlass20.setPreferredSize(new java.awt.Dimension(340, 40));
        panelGlass20.setLayout(new java.awt.BorderLayout());

        Scroll46.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ":: Tgl. Pemberian Obat ::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        Scroll46.setComponentPopupMenu(jPopupMenu2Anti);
        Scroll46.setName("Scroll46"); // NOI18N
        Scroll46.setOpaque(true);
        Scroll46.setPreferredSize(new java.awt.Dimension(150, 130));

        tbTglBeriObat1.setAutoCreateRowSorter(true);
        tbTglBeriObat1.setToolTipText("Silahkan klik salah satu tgl. utk melihat obatnya");
        tbTglBeriObat1.setName("tbTglBeriObat1"); // NOI18N
        tbTglBeriObat1.getTableHeader().setReorderingAllowed(false);
        tbTglBeriObat1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTglBeriObat1MouseClicked(evt);
            }
        });
        tbTglBeriObat1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbTglBeriObat1KeyPressed(evt);
            }
        });
        Scroll46.setViewportView(tbTglBeriObat1);

        panelGlass20.add(Scroll46, java.awt.BorderLayout.CENTER);

        panelGlass21.setName("panelGlass21"); // NOI18N
        panelGlass21.setPreferredSize(new java.awt.Dimension(44, 33));
        panelGlass21.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 3));

        ChkPoliAn.setBorder(null);
        ChkPoliAn.setForeground(new java.awt.Color(0, 0, 0));
        ChkPoliAn.setSelected(true);
        ChkPoliAn.setText("Hanya dipoli/inst. ini");
        ChkPoliAn.setBorderPainted(true);
        ChkPoliAn.setBorderPaintedFlat(true);
        ChkPoliAn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ChkPoliAn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkPoliAn.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkPoliAn.setName("ChkPoliAn"); // NOI18N
        ChkPoliAn.setOpaque(false);
        ChkPoliAn.setPreferredSize(new java.awt.Dimension(400, 23));
        ChkPoliAn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkPoliAnActionPerformed(evt);
            }
        });
        panelGlass21.add(ChkPoliAn);

        panelGlass20.add(panelGlass21, java.awt.BorderLayout.PAGE_START);

        PanelRiwayatObatAn.add(panelGlass20);

        Scroll47.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ":: Item Obat Sesuai Yang Diresepkan ::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        Scroll47.setComponentPopupMenu(jPopupMenu2Anti);
        Scroll47.setName("Scroll47"); // NOI18N
        Scroll47.setOpaque(true);
        Scroll47.setPreferredSize(new java.awt.Dimension(500, 423));

        tbItemObat1.setAutoCreateRowSorter(true);
        tbItemObat1.setToolTipText("Silahkan klik untuk memilih data obatnya");
        tbItemObat1.setComponentPopupMenu(jPopupMenu3Anti);
        tbItemObat1.setName("tbItemObat1"); // NOI18N
        tbItemObat1.getTableHeader().setReorderingAllowed(false);
        tbItemObat1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbItemObat1MouseClicked(evt);
            }
        });
        tbItemObat1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbItemObat1KeyPressed(evt);
            }
        });
        Scroll47.setViewportView(tbItemObat1);

        PanelRiwayatObatAn.add(Scroll47);

        PanelInputAn.add(PanelRiwayatObatAn, java.awt.BorderLayout.CENTER);

        jPanel8.add(PanelInputAn, java.awt.BorderLayout.PAGE_END);

        panelGlass10.add(jPanel8, java.awt.BorderLayout.CENTER);

        panelGlass11.setName("panelGlass11"); // NOI18N
        panelGlass11.setPreferredSize(new java.awt.Dimension(100, 48));
        panelGlass11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnSimpan2.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan2.setMnemonic('S');
        BtnSimpan2.setText("Simpan");
        BtnSimpan2.setToolTipText("Alt+S");
        BtnSimpan2.setName("BtnSimpan2"); // NOI18N
        BtnSimpan2.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnSimpan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan2ActionPerformed(evt);
            }
        });
        panelGlass11.add(BtnSimpan2);

        BtnHapus1.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapus1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapus1.setMnemonic('H');
        BtnHapus1.setText("Hapus");
        BtnHapus1.setToolTipText("Alt+H");
        BtnHapus1.setName("BtnHapus1"); // NOI18N
        BtnHapus1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnHapus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapus1ActionPerformed(evt);
            }
        });
        panelGlass11.add(BtnHapus1);

        BtnEdit1.setForeground(new java.awt.Color(0, 0, 0));
        BtnEdit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnEdit1.setMnemonic('G');
        BtnEdit1.setText("Ganti");
        BtnEdit1.setToolTipText("Alt+G");
        BtnEdit1.setName("BtnEdit1"); // NOI18N
        BtnEdit1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnEdit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEdit1ActionPerformed(evt);
            }
        });
        BtnEdit1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnEdit1KeyPressed(evt);
            }
        });
        panelGlass11.add(BtnEdit1);

        jLabel106.setForeground(new java.awt.Color(0, 0, 0));
        jLabel106.setText("Cetak Dalam Bentuk :");
        jLabel106.setName("jLabel106"); // NOI18N
        jLabel106.setPreferredSize(new java.awt.Dimension(120, 23));
        panelGlass11.add(jLabel106);

        cmbPilihCetakAn.setForeground(new java.awt.Color(0, 0, 0));
        cmbPilihCetakAn.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TTE (QR Code)", "TTD Basah" }));
        cmbPilihCetakAn.setName("cmbPilihCetakAn"); // NOI18N
        cmbPilihCetakAn.setPreferredSize(new java.awt.Dimension(105, 23));
        panelGlass11.add(cmbPilihCetakAn);

        BtnPrint1.setForeground(new java.awt.Color(0, 0, 0));
        BtnPrint1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/PrinterSettings.png"))); // NOI18N
        BtnPrint1.setMnemonic('T');
        BtnPrint1.setText("Cetak Resep");
        BtnPrint1.setToolTipText("Alt+T");
        BtnPrint1.setName("BtnPrint1"); // NOI18N
        BtnPrint1.setPreferredSize(new java.awt.Dimension(125, 30));
        BtnPrint1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrint1ActionPerformed(evt);
            }
        });
        BtnPrint1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPrint1KeyPressed(evt);
            }
        });
        panelGlass11.add(BtnPrint1);

        BtnNotepad1.setForeground(new java.awt.Color(0, 0, 0));
        BtnNotepad1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        BtnNotepad1.setMnemonic('N');
        BtnNotepad1.setText("Notepad");
        BtnNotepad1.setToolTipText("Alt+N");
        BtnNotepad1.setName("BtnNotepad1"); // NOI18N
        BtnNotepad1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnNotepad1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNotepad1ActionPerformed(evt);
            }
        });
        panelGlass11.add(BtnNotepad1);

        BtnKeluar1.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnKeluar1.setMnemonic('T');
        BtnKeluar1.setText("Tutup");
        BtnKeluar1.setToolTipText("Alt+T");
        BtnKeluar1.setName("BtnKeluar1"); // NOI18N
        BtnKeluar1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar1ActionPerformed(evt);
            }
        });
        BtnKeluar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluar1KeyPressed(evt);
            }
        });
        panelGlass11.add(BtnKeluar1);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Ganti Semua Resep Menjadi :");
        jLabel15.setName("jLabel15"); // NOI18N
        jLabel15.setPreferredSize(new java.awt.Dimension(160, 23));
        panelGlass11.add(jLabel15);

        cmbJnsResep1.setForeground(new java.awt.Color(0, 0, 0));
        cmbJnsResep1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "CITO", "BIASA" }));
        cmbJnsResep1.setName("cmbJnsResep1"); // NOI18N
        cmbJnsResep1.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass11.add(cmbJnsResep1);

        BtnSetuju1.setForeground(new java.awt.Color(0, 0, 0));
        BtnSetuju1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/checked.png"))); // NOI18N
        BtnSetuju1.setMnemonic('U');
        BtnSetuju1.setText("Setuju");
        BtnSetuju1.setToolTipText("Alt+U");
        BtnSetuju1.setGlassColor(new java.awt.Color(0, 153, 0));
        BtnSetuju1.setName("BtnSetuju1"); // NOI18N
        BtnSetuju1.setPreferredSize(new java.awt.Dimension(90, 30));
        BtnSetuju1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSetuju1ActionPerformed(evt);
            }
        });
        panelGlass11.add(BtnSetuju1);

        panelGlass10.add(panelGlass11, java.awt.BorderLayout.PAGE_END);

        TabResep.addTab("Resep ANTIBIOTIK", panelGlass10);

        panelGlass12.add(TabResep, java.awt.BorderLayout.CENTER);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(237, 242, 232)), ".: Daftar Obat/Alkes Farmasi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel2.setComponentPopupMenu(jPopupMenu2);
        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(550, 502));
        jPanel2.setLayout(new java.awt.BorderLayout(1, 1));

        panelisi4.setBorder(null);
        panelisi4.setComponentPopupMenu(jPopupMenu2);
        panelisi4.setName("panelisi4"); // NOI18N
        panelisi4.setPreferredSize(new java.awt.Dimension(100, 80));
        panelisi4.setLayout(null);

        BtnPilih.setForeground(new java.awt.Color(0, 0, 0));
        BtnPilih.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnPilih.setMnemonic('P');
        BtnPilih.setText("Pilih Obat/Alkes :");
        BtnPilih.setToolTipText("Alt+P");
        BtnPilih.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        BtnPilih.setGlassColor(new java.awt.Color(51, 204, 255));
        BtnPilih.setName("BtnPilih"); // NOI18N
        BtnPilih.setPreferredSize(new java.awt.Dimension(65, 30));
        BtnPilih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPilihActionPerformed(evt);
            }
        });
        panelisi4.add(BtnPilih);
        BtnPilih.setBounds(8, 43, 125, 23);

        cmbObat.setForeground(new java.awt.Color(0, 0, 0));
        cmbObat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        cmbObat.setName("cmbObat"); // NOI18N
        cmbObat.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbObatMouseReleased(evt);
            }
        });
        panelisi4.add(cmbObat);
        cmbObat.setBounds(140, 43, 330, 23);

        jLabel64.setForeground(new java.awt.Color(0, 0, 0));
        jLabel64.setText("Cari Obat :");
        jLabel64.setName("jLabel64"); // NOI18N
        jLabel64.setPreferredSize(new java.awt.Dimension(90, 23));
        panelisi4.add(jLabel64);
        jLabel64.setBounds(0, 10, 80, 23);

        TCariObat.setForeground(new java.awt.Color(0, 0, 0));
        TCariObat.setName("TCariObat"); // NOI18N
        TCariObat.setPreferredSize(new java.awt.Dimension(250, 23));
        TCariObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariObatKeyPressed(evt);
            }
        });
        panelisi4.add(TCariObat);
        TCariObat.setBounds(85, 10, 250, 23);

        BtnCekObat.setForeground(new java.awt.Color(0, 0, 0));
        BtnCekObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnCekObat.setText("Cek");
        BtnCekObat.setName("BtnCekObat"); // NOI18N
        BtnCekObat.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnCekObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCekObatActionPerformed(evt);
            }
        });
        panelisi4.add(BtnCekObat);
        BtnCekObat.setBounds(340, 10, 65, 23);

        jPanel2.add(panelisi4, java.awt.BorderLayout.PAGE_START);

        Scroll33.setName("Scroll33"); // NOI18N
        Scroll33.setOpaque(true);

        tbObat.setAutoCreateRowSorter(true);
        tbObat.setToolTipText("Silahkan klik salah satu nama obat sebagai resepnya ...");
        tbObat.setName("tbObat"); // NOI18N
        tbObat.getTableHeader().setReorderingAllowed(false);
        tbObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbObatMouseClicked(evt);
            }
        });
        tbObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbObatKeyPressed(evt);
            }
        });
        Scroll33.setViewportView(tbObat);

        jPanel2.add(Scroll33, java.awt.BorderLayout.CENTER);

        panelGlass12.add(jPanel2, java.awt.BorderLayout.EAST);

        internalFrame1.add(panelGlass12, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data pasiennya dulu...");
        } else if (TResepObat.getText().trim().equals("")) {
            Valid.textKosong(TResepObat, "nama obat");
            TResepObat.requestFocus();
        } else if (akses.getadmin() == true) {
            JOptionPane.showMessageDialog(null, "Meskipun anda admin utama, tetaplah seorang dokter yang boleh meresepkan obat...");
        } else {
            if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)") || status.equals("ralan")) {
                Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(noId,6),signed)),0) from catatan_resep where "
                        + "tgl_perawatan like '%" + Sequel.cariIsi("select year(now())") + "%' ",
                        Sequel.cariIsi("select year(now())"), 6, noIdObat);
                
                if (Sequel.menyimpantf("catatan_resep", "?,?,?,?,?,?,?", "Data", 7, new String[]{
                    noIdObat.getText(), TNoRw.getText(), Sequel.cariIsi("SELECT date(NOW())"), Sequel.cariIsi("SELECT TIME(NOW())"),
                    TResepObat.getText(), "BELUM", akses.getkode()
                }) == true) {
                    Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Catatan Resep Ralan Pasien", "Simpan");
                    Sequel.mengedit("reg_periksa", "no_rawat='" + TNoRw.getText() + "'", "stts='Sudah Diperiksa Dokter'");
                    TResepObat.setText("");
                    TResepObat.requestFocus();
                    tampilResepObat();
                }
            } else if (status.equals("ranap") || status.equals("vk bersalin")) {
                if (Sequel.cariInteger("select count(-1) from kamar_inap where no_rawat='" + TNoRw.getText() + "' and stts_pulang not in ('-','Pindah Kamar')") > 0) {
                    JOptionPane.showMessageDialog(null, "Maaf, pasien sudah dipulangkan dari ruangan, seluruh perawatan rawat inap           \n"
                            + "sudah selesai, jika belum selesai batalkan dulu status pulangnya..");
                } else {
                    jenisResep = "";
                    if (ChkCito.isSelected() == true) {
                        jenisResep = "CITO";
                    } else {
                        jenisResep = "BIASA";
                    }

                    Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(noId,6),signed)),0) from catatan_resep_ranap where "
                            + "tgl_perawatan like '%" + Sequel.cariIsi("select year(now())") + "%' ",
                            Sequel.cariIsi("select year(now())"), 6, noIdObat);

                    if (Sequel.menyimpantf("catatan_resep_ranap", "?,?,?,?,?,?,?,?,?", "Data", 9, new String[]{
                        noIdObat.getText(), TNoRw.getText(), Sequel.cariIsi("SELECT date(NOW())"), Sequel.cariIsi("SELECT TIME(NOW())"),
                        TResepObat.getText(), "BELUM", akses.getkode(), jenisResep, cmbIniResep.getSelectedItem().toString()
                    }) == true) {
                        Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Catatan Resep Ranap Pasien", "Simpan");
                        TResepObat.setText("");
                        TResepObat.requestFocus();
                        tampilResepObat();
                    }
                }
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
        WindowRiwayat.dispose();
        WindowGantiDokter.dispose();
        WindowRiwayatAnti.dispose();
        WindowGantiDokterAnti.dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnKeluarActionPerformed(null);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariObatActionPerformed(null);
        }
    }//GEN-LAST:event_TCariKeyPressed

    private void BtnCariObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariObatActionPerformed
        if (TabResep.getSelectedIndex() == 0) {
            tampilResepObat();
            ChkInput1.setSelected(false);
            isFormRiwayatObat();

            if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)") || status.equals("ralan")) {
                Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(noId,6),signed)),0) from catatan_resep where "
                        + "tgl_perawatan like '%" + Sequel.cariIsi("select year(now())") + "%' ",
                        Sequel.cariIsi("select year(now())"), 6, noIdObat);
            } else if (status.equals("ranap") || status.equals("vk bersalin")) {
                Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(noId,6),signed)),0) from catatan_resep_ranap where "
                        + "tgl_perawatan like '%" + Sequel.cariIsi("select year(now())") + "%' ",
                        Sequel.cariIsi("select year(now())"), 6, noIdObat);
            }
        } else {
            tampilResepObatAnti();
            ChkInputAn.setSelected(false);
            isFormRiwayatObatAnti();

            if (statusAnti.equals("IGD (Ralan)") || statusAnti.equals("IGD (Ranap)") || statusAnti.equals("ralan")) {
                Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(noId,6),signed)),0) from catatan_resep_antibiotik where "
                        + "tgl_perawatan like '%" + Sequel.cariIsi("select year(now())") + "%' ",
                        Sequel.cariIsi("select year(now())"), 6, noIdObatAnti);
            } else if (statusAnti.equals("ranap") || statusAnti.equals("vk bersalin")) {
                Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(noId,6),signed)),0) from catatan_resep_ranap_antibiotik where "
                        + "tgl_perawatan like '%" + Sequel.cariIsi("select year(now())") + "%' ",
                        Sequel.cariIsi("select year(now())"), 6, noIdObatAnti);
            }
        }
    }//GEN-LAST:event_BtnCariObatActionPerformed

    private void TResepObatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TResepObatKeyTyped
        evt.setKeyChar(Character.toUpperCase(evt.getKeyChar()));
    }//GEN-LAST:event_TResepObatKeyTyped

    private void TResepObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TResepObatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnSimpanActionPerformed(null);
        }
    }//GEN-LAST:event_TResepObatKeyPressed

    private void tbResepObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbResepObatMouseClicked
        if (tabModeResepObat.getRowCount() != 0) {
            try {
                getDataCatatanResep();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbResepObatMouseClicked

    private void tbResepObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbResepObatKeyPressed
        if (tabModeResepObat.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataCatatanResep();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbResepObatKeyPressed

    private void ChkInput1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInput1ActionPerformed
        isFormRiwayatObat();
    }//GEN-LAST:event_ChkInput1ActionPerformed

    private void tbTglBeriObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTglBeriObatMouseClicked
        if (tabModeTglBeriObat.getRowCount() != 0) {
            try {
                getDataRiwObat();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbTglBeriObatMouseClicked

    private void tbTglBeriObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbTglBeriObatKeyPressed
        if (tabModeTglBeriObat.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataRiwObat();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbTglBeriObatKeyPressed

    private void ChkPoli1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkPoli1ActionPerformed
        Valid.tabelKosong(tabModeRiwItemObat);
        if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)") || status.equals("ralan") || status.equals("vk bersalin")) {
            if (ChkPoli1.isSelected() == true) {
                ChkPoli1.setText("Hanya dipoli/inst. ini untuk 5 kunjungan terakhir");
                tampilTglBeriObat();
            } else if (ChkPoli1.isSelected() == false) {
                ChkPoli1.setText("Semua Poli/Inst. untuk 5 kunjungan terakhir");
                tampilTglBeriObat();
            }
        } else if (status.equals("ranap")) {
            if (ChkPoli1.isSelected() == true) {
                ChkPoli1.setText("Semua resep ditampilkan selama perawatan saat ini");
                tampilTglBeriObat();
            } else if (ChkPoli1.isSelected() == false) {
                ChkPoli1.setText("Semua resep ditampilkan selama perawatan saat ini");
                tampilTglBeriObat();
            }
        }
    }//GEN-LAST:event_ChkPoli1ActionPerformed

    private void tbItemObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbItemObatMouseClicked
        if (tabModeRiwItemObat.getRowCount() != 0) {
            try {
                getDataItemObat();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbItemObatMouseClicked

    private void tbItemObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbItemObatKeyPressed
        if (tabModeRiwItemObat.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataItemObat();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbItemObatKeyPressed

    private void MnSemuanyaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSemuanyaActionPerformed
        if (tabModeResepObat.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, Item resep obat masih kosong...");
            tbResepObat.requestFocus();
        } else {
            tampilResepObat();
            contengResep();
        }
    }//GEN-LAST:event_MnSemuanyaActionPerformed

    private void MnDibatalkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDibatalkanActionPerformed
        if (tabModeResepObat.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, Item resep obat masih kosong...");
            tbResepObat.requestFocus();
        } else {
            tampilResepObat();

            for (i = 0; i < tbResepObat.getRowCount(); i++) {
                if (tbResepObat.getValueAt(i, 1).equals(TNoRw.getText())) {
                    tbResepObat.setValueAt(Boolean.FALSE, i, 0);
                }
            }
        }
    }//GEN-LAST:event_MnDibatalkanActionPerformed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tabModeResepObat.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...");
            TNoRw.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Gagal menghapus. Pilih dulu data yang mau dihapus. Klik data pada tabel untuk memilih...");
        } else if (akses.getadmin() == true) {
            JOptionPane.showMessageDialog(null, "Meskipun anda admin utama, tetaplah seorang dokter yang boleh menghapus resepnya...");
        } else if (!TPasien.getText().trim().equals("")) {
            riwayatData = "";
            riwayatData = "hapus";
            if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)") || status.equals("ralan")) {
                //cek conteng
                x = 0;
                for (i = 0; i < tbResepObat.getRowCount(); i++) {
                    if (tbResepObat.getValueAt(i, 0).toString().equals("true")) {
                        x++;
                    }
                }

                if (x == 0) {
                    JOptionPane.showMessageDialog(null, "Silahkan conteng dulu item resep obatnya..");
                    tbResepObat.requestFocus();
                } else {
                    for (i = 0; i < tbResepObat.getRowCount(); i++) {
                        if (tbResepObat.getValueAt(i, 0).toString().equals("true")
                                && (tbResepObat.getValueAt(i, 5).toString().equals("SUDAH") || tbResepObat.getValueAt(i, 5).toString().equals("DILUAR"))) {
                            JOptionPane.showMessageDialog(null, "Mohon maaf, untuk resep " + tbResepObat.getValueAt(i, 4).toString() + " sudah diverifikasi apotek,     \n"
                                    + "data tdk. bisa dihapus, Silakan input lagi sbg. resep baru/lanjutan...");
                        } else if (tbResepObat.getValueAt(i, 0).toString().equals("true") && tbResepObat.getValueAt(i, 5).toString().equals("BELUM")) {
                            simpanHistoriResepRalan();
                        }
                    }

                    TResepObat.setText("");
                    tampilResepObat();
                }

            } else if (status.equals("ranap") || status.equals("vk bersalin")) {
                //cek conteng
                x = 0;
                for (i = 0; i < tbResepObat.getRowCount(); i++) {
                    if (tbResepObat.getValueAt(i, 0).toString().equals("true")) {
                        x++;
                    }
                }

                if (x == 0) {
                    JOptionPane.showMessageDialog(null, "Silahkan conteng dulu item resep obatnya..");
                    tbResepObat.requestFocus();
                } else {
                    for (i = 0; i < tbResepObat.getRowCount(); i++) {
                        if (tbResepObat.getValueAt(i, 0).toString().equals("true")
                                && (tbResepObat.getValueAt(i, 5).toString().equals("SUDAH") || tbResepObat.getValueAt(i, 5).toString().equals("DILUAR"))) {
                            JOptionPane.showMessageDialog(null, "Mohon maaf, untuk resep " + tbResepObat.getValueAt(i, 4).toString() + " sudah diverifikasi apotek,     \n"
                                    + "data tdk. bisa dihapus, Silakan input lagi sbg. resep baru/lanjutan...");
                        } else if (tbResepObat.getValueAt(i, 0).toString().equals("true") && tbResepObat.getValueAt(i, 5).toString().equals("BELUM")) {
                            simpanHistoriResepRanap();
                        }
                    }

                    TResepObat.setText("");
                    tampilResepObat();
                }
            }
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (tbResepObat.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Tidak ada data yang akan diperbaiki..");
        } else if (TResepObat.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Silakan pilih dulu salah satu datanya pada tabel..");
        } else if (akses.getadmin() == true) {
            JOptionPane.showMessageDialog(null, "Meskipun anda admin utama, tetaplah seorang dokter yang boleh merubah resepnya...");
        } else {
            if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)") || status.equals("ralan")) {
                if (tbResepObat.getSelectedRow() > -1) {
                    if (tbResepObat.getValueAt(tbResepObat.getSelectedRow(), 5).toString().equals("SUDAH")
                            || tbResepObat.getValueAt(tbResepObat.getSelectedRow(), 5).toString().equals("DILUAR")) {
                        JOptionPane.showMessageDialog(null, "Untuk resep yang sudah diverifikasi apotek tdk. bisa diperbaiki,     \n"
                                + "Silakan klik tombol simpan sbg. resep baru/lanjutan...");
                    } else {
                        if (Sequel.mengedittf("catatan_resep", "noId=?", "no_rawat=?, tgl_perawatan=?, jam_perawatan=?, nama_obat=?", 5, new String[]{
                            TNoRw.getText(), Sequel.cariIsi("SELECT date(NOW())"), Sequel.cariIsi("SELECT TIME(NOW())"), TResepObat.getText(),
                            TIdObat.getText()
                        }) == true) {
                            Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Catatan Resep Ralan Pasien", "Ganti");
                            TResepObat.setText("");
                            tampilResepObat();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Silahkan pilih data yang mau diganti..");
                    TCari.requestFocus();
                }
            } else if (status.equals("ranap") || status.equals("vk bersalin")) {
                if (tbResepObat.getSelectedRow() > -1) {
                    if (tbResepObat.getValueAt(tbResepObat.getSelectedRow(), 5).toString().equals("SUDAH")
                            || tbResepObat.getValueAt(tbResepObat.getSelectedRow(), 5).toString().equals("DILUAR")) {
                        JOptionPane.showMessageDialog(null, "Untuk resep yang sudah diverifikasi apotek tdk. bisa diperbaiki,     \n"
                                + "Silakan klik tombol simpan sbg. resep baru/lanjutan...");
                    } else {
                        jenisResep = "";
                        if (ChkCito.isSelected() == true) {
                            jenisResep = "CITO";
                        } else {
                            jenisResep = "BIASA";
                        }
                      
                        if (Sequel.mengedittf("catatan_resep_ranap", "noId=?", "no_rawat=?, tgl_perawatan=?, jam_perawatan=?, nama_obat=?, jenis_resep=?, resep_untuk=?", 7, new String[]{
                            TNoRw.getText(), Sequel.cariIsi("SELECT date(NOW())"), Sequel.cariIsi("SELECT TIME(NOW())"), TResepObat.getText(), jenisResep,
                            cmbIniResep.getSelectedItem().toString(),
                            TIdObat.getText()
                        }) == true) {
                            Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Catatan Resep Ranap Pasien", "Ganti");
                            TResepObat.setText("");
                            tampilResepObat();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Silahkan pilih data yang mau diganti..");
                    TCari.requestFocus();
                }
            }
        }
    }//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnEditActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnPrint);
        }
    }//GEN-LAST:event_BtnEditKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tbResepObat.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Resep obat untuk pasien tersebut belum ada ditabel...");
            BtnCariObat.requestFocus();
        } else if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "No. rawat pasien tidak ditemukan...");
            tbResepObat.requestFocus();
        } else {
            //cek contengnya
            x = 0;
            for (i = 0; i < tbResepObat.getRowCount(); i++) {
                if (tbResepObat.getValueAt(i, 0).toString().equals("true")) {
                    x++;
                }
            }

            if (x == 0) {
                JOptionPane.showMessageDialog(null, "Item resep yg. akan dicetak masih belum diconteng...");
                contengResep();
                BtnPrint.requestFocus();
            } else {
                resepDipilih = "";
                jenisResep = "";
                cito = 0;
                iniResep = 0;
                for (i = 0; i < tbResepObat.getRowCount(); i++) {
                    if (tbResepObat.getValueAt(i, 0).toString().equals("true")) {
                        if (resepDipilih.equals("")) {
                            resepDipilih = "'" + tbResepObat.getValueAt(i, 7).toString() + "'";
                        } else {
                            resepDipilih = resepDipilih + ",'" + tbResepObat.getValueAt(i, 7).toString() + "'";
                        }
                    }
                }
                
                //cek resep cito
                for (i = 0; i < tbResepObat.getRowCount(); i++) {
                    if (tbResepObat.getValueAt(i, 0).toString().equals("true")
                            && tbResepObat.getValueAt(i, 9).toString().equals("CITO")) {
                        cito++;
                    }
                }
                
                if (cito == 0) {
                    jenisResep = "BIASA";
                } else {
                    jenisResep = "CITO";
                }
                
                //cek resep pulang
                for (i = 0; i < tbResepObat.getRowCount(); i++) {
                    if (tbResepObat.getValueAt(i, 0).toString().equals("true")
                            && tbResepObat.getValueAt(i, 10).toString().equals("Pulang")) {
                        iniResep++;
                    }
                }
                
                if (iniResep == 0) {
                    resepPulang = "Dalam Perawatan";
                } else {
                    resepPulang = "Pulang";
                }

                if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)") || status.equals("ralan")) {
                    if (Sequel.cariInteger("SELECT COUNT(-1) FROM catatan_resep WHERE no_rawat ='" + TNoRw.getText() + "' AND noId IN (" + resepDipilih + ")") == 0) {
                        JOptionPane.showMessageDialog(null, "Maaf, hanya resep utk. hari ini yg. bisa dicetak, krn. pasien sdh. dilayani/diresepkan obatnya...");
                        tbResepObat.requestFocus();
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

                        if (Sequel.cariInteger("select count(-1) from reg_konsul_internal where no_rawat='" + TNoRw.getText() + "'") == 0) {
                            param.put("nosep", Sequel.cariIsi("select ifnull(no_sep,'-') from bridging_sep where no_rawat='" + TNoRw.getText() + "' and jnspelayanan='2'") + " "
                                    + Sequel.cariIsi("select if(count(-1)>0,'(Program PRB BPJS)','') from bridging_srb_bpjs where no_srb='" + TNoRw.getText() + "'"));
                        } else {
                            param.put("nosep", Sequel.cariIsi("select no_sep from reg_konsul_internal where no_rawat='" + TNoRw.getText() + "'") + " "
                                    + Sequel.cariIsi("select if(count(-1)>0,'(Program PRB BPJS)','') from bridging_srb_bpjs where no_srb='" + TNoRw.getText() + "'"));
                        }

                        if (cmbPilihCetak.getSelectedIndex() == 0) {
                            String isi = "";
                            isi = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                                    + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='009'"),
                                            "Resep Rawat Jalan", Sequel.cariIsi("select d.nm_dokter from catatan_resep c "
                                                    + "inner join dokter d on d.kd_dokter = c.kd_dokter where c.no_rawat ='" + TNoRw.getText() + "' order by noId desc limit 1"),
                                            Sequel.cariIsi("select date_format(tgl_perawatan,'%d/%m/%Y') from catatan_resep where "
                                                    + "no_rawat='" + TNoRw.getText() + "' order by noId desc limit 1"),
                                            Sequel.cariIsi("select time(jam_perawatan) from catatan_resep where "
                                                    + "no_rawat='" + TNoRw.getText() + "'  order by noId desc limit 1")) + "') from kalimat_tte where kode='009'");
                            
                            Valid.cetakQrTte(isi, Sequel.cariFolderTte(), "QRTte.jpg", "select logo from setting");
                            Sequel.queryu("delete from setting_qr where judul = 'QRTte'");
                            Sequel.menyimpanQr("setting_qr", "'QRTte'", "file QRCode TTE Resep", Sequel.cariFolderPrintTte());
                            param.put("lokasiQr", Sequel.cariGambar("select gambar from setting_qr where judul = 'QRTte'"));
                            param.put("kalimatTte", Sequel.cariIsi("select replace(kalimat_footer,'##jns_dokumen##',jenis_dokumen) from kalimat_tte where kode='009'"));
                            
                            Valid.MyReport("rptResepRalanQr.jasper", "report", "::[ Resep Dokter Poliklinik/Unit Rawat Jalan ]::",
                                    " select c.no_rawat, pl.nm_poli, d.nm_dokter, CONCAT(if(iob.no_rawat is null,'','(RESEP ITER) '),'Martapura, ',DATE_FORMAT(c.tgl_perawatan,'%d/%m/%Y')) tgl_resep, "
                                    + "c.nama_obat, r.no_rkm_medis, p.nm_pasien, CONCAT(r.umurdaftar,' ',r.sttsumur) umur, "
                                    + "CONCAT(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat, d.no_ijn_praktek no_sip, ifnull(p.no_tlp,'-') noHP from catatan_resep c "
                                    + "inner join reg_periksa r on r.no_rawat = c.no_rawat inner join dokter d on d.kd_dokter = c.kd_dokter "
                                    + "INNER JOIN poliklinik pl on pl.kd_poli=r.kd_poli INNER JOIN pasien p on p.no_rkm_medis=r.no_rkm_medis "
                                    + "INNER JOIN kelurahan kl on kl.kd_kel=p.kd_kel INNER JOIN kecamatan kc on kc.kd_kec=p.kd_kec "
                                    + "INNER JOIN kabupaten kb on kb.kd_kab=p.kd_kab left join iter_obat_bpjs iob on iob.no_rawat=c.no_rawat where "
                                    + "c.no_rawat ='" + TNoRw.getText() + "' and c.noId in (" + resepDipilih + ") order by c.noId", param);
                            Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);
                        } else {
                            Valid.MyReport("rptResepRalan.jasper", "report", "::[ Resep Dokter Poliklinik/Unit Rawat Jalan ]::",
                                    " select c.no_rawat, pl.nm_poli, d.nm_dokter, CONCAT(if(iob.no_rawat is null,'','(RESEP ITER) '),'Martapura, ',DATE_FORMAT(c.tgl_perawatan,'%d/%m/%Y')) tgl_resep, "
                                    + "c.nama_obat, r.no_rkm_medis, p.nm_pasien, CONCAT(r.umurdaftar,' ',r.sttsumur) umur, "
                                    + "CONCAT(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat, d.no_ijn_praktek no_sip, ifnull(p.no_tlp,'-') noHP from catatan_resep c "
                                    + "inner join reg_periksa r on r.no_rawat = c.no_rawat inner join dokter d on d.kd_dokter = c.kd_dokter "
                                    + "INNER JOIN poliklinik pl on pl.kd_poli=r.kd_poli INNER JOIN pasien p on p.no_rkm_medis=r.no_rkm_medis "
                                    + "INNER JOIN kelurahan kl on kl.kd_kel=p.kd_kel INNER JOIN kecamatan kc on kc.kd_kec=p.kd_kec "
                                    + "INNER JOIN kabupaten kb on kb.kd_kab=p.kd_kab left join iter_obat_bpjs iob on iob.no_rawat=c.no_rawat where "
                                    + "c.no_rawat ='" + TNoRw.getText() + "' and c.noId in (" + resepDipilih + ") order by c.noId", param);
                        }
                        this.setCursor(Cursor.getDefaultCursor());
                    }
                } else if (status.equals("ranap") || status.equals("vk bersalin")) {
                    if (Sequel.cariInteger("SELECT COUNT(-1) FROM catatan_resep_ranap WHERE no_rawat ='" + TNoRw.getText() + "' AND noId IN (" + resepDipilih + ")") == 0) {
                        JOptionPane.showMessageDialog(null, "Maaf, hanya resep utk. hari ini yg. bisa dicetak, krn. pasien sdh. dilayani/diresepkan obatnya...");
                        tbResepObat.requestFocus();
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
                        
                        if (Sequel.cariInteger("select count(-1) from bridging_sep where no_rawat='" + TNoRw.getText() + "' and jnspelayanan='1'") == 0) {
                            param.put("nosep", "-");
                        } else {
                            param.put("nosep", Sequel.cariIsi("select no_sep from bridging_sep where no_rawat='" + TNoRw.getText() + "' and jnspelayanan='1' order by tglsep desc limit 1"));
                        }
                        
                        param.put("ruangan", Sequel.cariIsi("select b.nm_bangsal from kamar_inap ki inner join kamar k on k.kd_kamar=ki.kd_kamar "
                                + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal where ki.no_rawat='" + TNoRw.getText() + "' "
                                + "order by ki.tgl_masuk desc, ki.jam_masuk desc limit 1") + " (Resep : " + jenisResep + " - " + resepPulang + ")");

                        if (cmbPilihCetak.getSelectedIndex() == 0) {
                            String isi = "";
                            isi = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                                    + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='009'"),
                                            "Resep Rawat Inap", Sequel.cariIsi("select d.nm_dokter from catatan_resep_ranap c "
                                                    + "inner join dokter d on d.kd_dokter = c.kd_dokter where c.no_rawat ='" + TNoRw.getText() + "' order by noId desc limit 1"),
                                            Sequel.cariIsi("select date_format(tgl_perawatan,'%d/%m/%Y') from catatan_resep_ranap where "
                                                    + "no_rawat='" + TNoRw.getText() + "' order by noId desc limit 1"),
                                            Sequel.cariIsi("select time(jam_perawatan) from catatan_resep_ranap where "
                                                    + "no_rawat='" + TNoRw.getText() + "'  order by noId desc limit 1")) + "') from kalimat_tte where kode='009'");
                            
                            Valid.cetakQrTte(isi, Sequel.cariFolderTte(), "QRTte.jpg", "select logo from setting");
                            Sequel.queryu("delete from setting_qr where judul = 'QRTte'");
                            Sequel.menyimpanQr("setting_qr", "'QRTte'", "file QRCode TTE Resep", Sequel.cariFolderPrintTte());
                            param.put("lokasiQr", Sequel.cariGambar("select gambar from setting_qr where judul = 'QRTte'"));
                            param.put("kalimatTte", Sequel.cariIsi("select replace(kalimat_footer,'##jns_dokumen##',jenis_dokumen) from kalimat_tte where kode='009'"));
                            
                            Valid.MyReport("rptResepRanapQr.jasper", "report", "::[ Resep Dokter Rawat Inap ]::",
                                    "SELECT c.no_rawat, d.nm_dokter, CONCAT('Martapura, ',DATE_FORMAT(c.tgl_perawatan, '%d/%m/%Y')) tgl_resep, "
                                    + "c.nama_obat, r.no_rkm_medis, p.nm_pasien, CONCAT(r.umurdaftar,' ',r.sttsumur) umur, "
                                    + "CONCAT(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat, d.no_ijn_praktek no_sip, ifnull(p.no_tlp, '-') noHP "
                                    + "FROM catatan_resep_ranap c INNER JOIN reg_periksa r ON r.no_rawat = c.no_rawat "
                                    + "INNER JOIN dokter d ON d.kd_dokter = c.kd_dokter INNER JOIN pasien p ON p.no_rkm_medis = r.no_rkm_medis "
                                    + "INNER JOIN kelurahan kl ON kl.kd_kel = p.kd_kel INNER JOIN kecamatan kc ON kc.kd_kec = p.kd_kec "
                                    + "INNER JOIN kabupaten kb ON kb.kd_kab = p.kd_kab WHERE "
                                    + "c.no_rawat = '" + TNoRw.getText() + "' AND c.noId IN (" + resepDipilih + ") ORDER BY c.noId", param);
                            Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);
                        } else {
                            Valid.MyReport("rptResepRanap.jasper", "report", "::[ Resep Dokter Rawat Inap ]::",
                                    "SELECT c.no_rawat, d.nm_dokter, CONCAT('Martapura, ',DATE_FORMAT(c.tgl_perawatan, '%d/%m/%Y')) tgl_resep, "
                                    + "c.nama_obat, r.no_rkm_medis, p.nm_pasien, CONCAT(r.umurdaftar,' ',r.sttsumur) umur, "
                                    + "CONCAT(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat, d.no_ijn_praktek no_sip, ifnull(p.no_tlp, '-') noHP "
                                    + "FROM catatan_resep_ranap c INNER JOIN reg_periksa r ON r.no_rawat = c.no_rawat "
                                    + "INNER JOIN dokter d ON d.kd_dokter = c.kd_dokter INNER JOIN pasien p ON p.no_rkm_medis = r.no_rkm_medis "
                                    + "INNER JOIN kelurahan kl ON kl.kd_kel = p.kd_kel INNER JOIN kecamatan kc ON kc.kd_kec = p.kd_kec "
                                    + "INNER JOIN kabupaten kb ON kb.kd_kab = p.kd_kab WHERE "
                                    + "c.no_rawat = '" + TNoRw.getText() + "' AND c.noId IN (" + resepDipilih + ") ORDER BY c.noId", param);
                        }
                        this.setCursor(Cursor.getDefaultCursor());
                    }
                }
            }
        }
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnEdit, BtnKeluar);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        TCari.setText("");
        TResepObat.setText("");
        cmbObat.setSelectedIndex(0);
        ChkInput1.setSelected(false);
        isFormRiwayatObat();
        tampilResepObat();
        Sequel.cariIsiComboDB("SELECT db.nama_brng FROM gudangbarang gd INNER JOIN databarang db on db.kode_brng=gd.kode_brng where "
                + "gd.kd_bangsal in ('APT01','APT02') and db.nama_brng not like '(FR)%' and db.nama_brng not like '-%'group by gd.kode_brng "
                + "order by db.nama_brng", cmbObat);
    }//GEN-LAST:event_formWindowOpened

    private void BtnCopyResepTerakhirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCopyResepTerakhirActionPerformed
        cekResep = 0 ;
        tglResep = "";

        if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)") || status.equals("ralan")) {
            cekResep = Sequel.cariInteger("SELECT count(-1) FROM catatan_resep cr INNER JOIN reg_periksa rp ON rp.no_rawat=cr.no_rawat "
                    + "WHERE rp.kd_poli='" + kodepoli + "' and rp.no_rkm_medis='" + TNoRM.getText() + "'");

            if (cekResep == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, tidak ada resep terakhir sesuai kunjungan yg. tersimpan didalam sistem...");
            } else if (akses.getadmin() == true) {
                JOptionPane.showMessageDialog(null, "Meskipun anda admin utama, tetaplah seorang dokter yang boleh meresepkan obat...");
            } else {
                tglResep = Sequel.cariIsi("SELECT cr.tgl_perawatan FROM catatan_resep cr INNER JOIN reg_periksa rp ON rp.no_rawat=cr.no_rawat "
                        + "WHERE rp.kd_poli='" + kodepoli + "' and rp.no_rkm_medis='" + TNoRM.getText() + "' "
                        + "GROUP BY cr.tgl_perawatan, rp.no_rkm_medis ORDER BY cr.tgl_perawatan DESC LIMIT 1");

                x = JOptionPane.showConfirmDialog(null, "Resep terakhir pada tgl. " + Valid.SetTglINDONESIA(tglResep) + " apakah akan dicopy...?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    tampilItemResepRalan(tglResep, TNoRM.getText(), kodepoli);

                    for (i = 0; i < tbItemResep.getRowCount(); i++) {
                        tbItemResep.setValueAt(Boolean.TRUE, i, 0);
                    }
                    copyResepnya();
                }
            }
        } else if (status.equals("ranap") || status.equals("vk bersalin")) {
            cekResep = Sequel.cariInteger("SELECT count(-1) FROM catatan_resep_ranap WHERE no_rawat='" + TNoRw.getText() + "'");

            if (cekResep == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, tidak ada resep terakhir sesuai kunjungan yg. tersimpan didalam sistem...");
            } else if (akses.getadmin() == true) {
                JOptionPane.showMessageDialog(null, "Meskipun anda admin utama, tetaplah seorang dokter yang boleh meresepkan obat...");
            } else {
                tglResep = Sequel.cariIsi("SELECT tgl_perawatan FROM catatan_resep_ranap where no_rawat='" + TNoRw.getText() + "' ORDER BY noId DESC LIMIT 1");

                x = JOptionPane.showConfirmDialog(null, "Resep terakhir pada tgl. " + Valid.SetTglINDONESIA(tglResep) + " apakah akan dicopy...?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    tampilItemResepRanap(tglResep, TNoRw.getText());

                    for (i = 0; i < tbItemResep.getRowCount(); i++) {
                        tbItemResep.setValueAt(Boolean.TRUE, i, 0);
                    }
                    copyResepnya();
                }
            }
        }
    }//GEN-LAST:event_BtnCopyResepTerakhirActionPerformed

    private void tbItemResepMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbItemResepMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbItemResepMouseClicked

    private void tbItemResepKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbItemResepKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbItemResepKeyPressed

    private void MnDiCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDiCopyActionPerformed
        if (tabModeResepObat.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, Item resep obat masih kosong...");
            tbResepObat.requestFocus();
        } else {
            //cek conteng
            x = 0;
            for (i = 0; i < tbResepObat.getRowCount(); i++) {
                if (tbResepObat.getValueAt(i, 0).toString().equals("true")) {
                    x++;
                }
            }

            if (x == 0) {
                JOptionPane.showMessageDialog(null, "Silahkan conteng dulu resep yang dipilih utk. di copy..");
                tbResepObat.requestFocus();
            } else {
                try {
                    for (i = 0; i < tbResepObat.getRowCount(); i++) {
                        if (tbResepObat.getValueAt(i, 0).toString().equals("true")) {
                            if (resepDipilih.equals("")) {
                                resepDipilih = tbResepObat.getValueAt(i, 4).toString();
                            } else {
                                resepDipilih = resepDipilih + "\n" + tbResepObat.getValueAt(i, 4).toString();
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                }

                akses.setCopyData(resepDipilih);
                JOptionPane.showMessageDialog(null, "Resep yang dipilih berhasil di copy..");
                BtnKeluarActionPerformed(null);
            }
        }
    }//GEN-LAST:event_MnDiCopyActionPerformed

    private void BtnNotepadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNotepadActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        akses.setform("DlgCatatanResepBiasaAntibiotik");
        DlgNotepad form = new DlgNotepad(null, false);
        form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        form.setLocationRelativeTo(internalFrame1);
        form.setData(akses.getkode());
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnNotepadActionPerformed

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
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data yang dipilih akan dikembalikan/restore..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                kembalikanData();
                TCari.setText("");
                Valid.SetTgl(DTPCariA, tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 4).toString());
                BtnCloseIn10ActionPerformed(null);
                tampilResepObat();
                TResepObat.setText("");
                TResepObat.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih salah satu datanya terlebih dahulu..");
        }
    }//GEN-LAST:event_BtnRestorActionPerformed

    private void BtnCloseIn10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn10ActionPerformed
        WindowRiwayat.dispose();
        TCari2.setText("");
    }//GEN-LAST:event_BtnCloseIn10ActionPerformed

    private void MnRiwayatDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRiwayatDataActionPerformed
        Valid.SetTgl(DTPCari3, Valid.SetTgl(DTPCariA.getSelectedItem() + ""));
        DTPCari4.setDate(new Date());
        TCari2.setText(TNoRM.getText());
        BtnCari2ActionPerformed(null);
        WindowRiwayat.setSize(985, internalFrame1.getHeight() - 40);
        WindowRiwayat.setLocationRelativeTo(internalFrame1);
        WindowRiwayat.setAlwaysOnTop(false);
        WindowRiwayat.setVisible(true);
    }//GEN-LAST:event_MnRiwayatDataActionPerformed

    private void BtnSetujuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSetujuActionPerformed
        if (tbResepObat.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Maaf, belum ada item/resep obat yang diberikan, simpan dulu resepnya..");
        } else if (cmbJnsResep.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu jenis resepnya..");
            cmbJnsResep.requestFocus();
        } else {
            resepDipilih = "";
            for (i = 0; i < tbResepObat.getRowCount(); i++) {
                if (resepDipilih.equals("")) {
                    resepDipilih = "'" + tbResepObat.getValueAt(i, 7).toString() + "'";
                } else {
                    resepDipilih = resepDipilih + ",'" + tbResepObat.getValueAt(i, 7).toString() + "'";
                }
            }
            
            if (Sequel.cariInteger("select count(-1) from catatan_resep_ranap where noId in (" + resepDipilih + ") and (status='SUDAH' or status='DILUAR')") > 0) {
                JOptionPane.showMessageDialog(null, "Mohon maaf, untuk resep yang sudah diverifikasi apotek tdk. bisa diganti,     \n"
                            + "Silakan input lagi sbg. resep baru/lanjutan...");
            } else {
                for (i = 0; i < tbResepObat.getRowCount(); i++) {
                    Sequel.mengedit("catatan_resep_ranap", "noId='" + tbResepObat.getValueAt(i, 7).toString() + "'",
                            "jenis_resep='" + cmbJnsResep.getSelectedItem().toString() + "'");
                }
            }

            ChkCito.setSelected(false);
            cmbJnsResep.setSelectedIndex(0);
            TResepObat.setText("");
            tampilResepObat();
        }
    }//GEN-LAST:event_BtnSetujuActionPerformed

    private void MnCopyObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCopyObatActionPerformed
        if (tbItemObat.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan klik dulu salah satu data tgl. pemberian obatnya...");
            tbTglBeriObat.requestFocus();
        } else {
            String resep;
            resep = "";
            if (tbTglBeriObat.getSelectedRow() > -1) {
                try {
                    for (i = 0; i < tbItemObat.getRowCount(); i++) {
                        if (resep.equals("")) {
                            resep = tbItemObat.getValueAt(i, 2).toString() + " Jlh. "
                                    + tbItemObat.getValueAt(i, 3).toString();
                        } else {
                            resep = resep + "\n" + tbItemObat.getValueAt(i, 2).toString() + " Jlh. "
                                    + tbItemObat.getValueAt(i, 3).toString();
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                }
                akses.setCopyData("Resep Tgl. " + tbTglBeriObat.getValueAt(tbTglBeriObat.getSelectedRow(), 1).toString() + " :\n" + resep);
            }
        }
    }//GEN-LAST:event_MnCopyObatActionPerformed

    private void BtnResepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnResepActionPerformed
        if (TabResep.getSelectedIndex() == 0) {
            WindowRiwayatResep.setSize(957, internalFrame1.getHeight() - 40);
            WindowRiwayatResep.setLocationRelativeTo(internalFrame1);
            WindowRiwayatResep.setVisible(true);
            cmbConteng.setSelectedIndex(0);
            tampilTglResep();
            Valid.tabelKosong(tabModeResepB);
        } else {
            WindowRiwayatResepAnti.setSize(957, internalFrame1.getHeight() - 40);
            WindowRiwayatResepAnti.setLocationRelativeTo(internalFrame1);
            WindowRiwayatResepAnti.setVisible(true);
            cmbContengAn.setSelectedIndex(0);
            tampilTglResepAnti();
            Valid.tabelKosong(tabModeResepBAn);
        }
    }//GEN-LAST:event_BtnResepActionPerformed

    private void cmbContengItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbContengItemStateChanged
        if (tabModeResepB.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, Item resep obat masih kosong...");
            tbPemberianResep.requestFocus();
        } else if (cmbConteng.getSelectedItem().equals("Semuanya")) {
            for (i = 0; i < tbItemResep1.getRowCount(); i++) {
                tbItemResep1.setValueAt(Boolean.TRUE, i, 0);
            }
            hitungItemResepDiconteng();
        } else if (cmbConteng.getSelectedItem().equals(" ") || cmbConteng.getSelectedItem().equals("Dibatalkan")) {
            for (i = 0; i < tbItemResep1.getRowCount(); i++) {
                tbItemResep1.setValueAt(Boolean.FALSE, i, 0);
            }
            LCount2.setText("0");
        }
    }//GEN-LAST:event_cmbContengItemStateChanged

    private void BtnCopyResepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCopyResepActionPerformed
        if (tabModeResepB.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, Item resep obat masih kosong...");
            tbPemberianResep.requestFocus();
        } else if (akses.getadmin() == true) {
            JOptionPane.showMessageDialog(null, "Meskipun anda admin utama, tetaplah seorang dokter yang boleh meresepkan obat...");
        } else {
            try {
                noIdObatCopy.setText("");
                j = 0;
                //cek conteng item obat
                for (i = 0; i < tbItemResep1.getRowCount(); i++) {
                    if (tbItemResep1.getValueAt(i, 0).toString().equals("true")) {
                        j++;
                    }
                }

                if (j == 0) {
                    JOptionPane.showMessageDialog(null, "Silahkan conteng dulu item resep obatnya,..");
                    tbItemResep1.requestFocus();
                } else {
                    for (i = 0; i < tbItemResep1.getRowCount(); i++) {
                        if (tbItemResep1.getValueAt(i, 0).toString().equals("true")) {
                            if (chkRanap.isSelected() == true) {
                                Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(noId,6),signed)),0) from catatan_resep_ranap where "
                                        + "tgl_perawatan like '%" + Sequel.cariIsi("select year(now())") + "%' ",
                                        Sequel.cariIsi("select year(now())"), 6, noIdObatCopy);

                                Sequel.menyimpan("catatan_resep_ranap", "'" + noIdObatCopy.getText() + "','" + TNoRw.getText() + "', "
                                        + "'" + Sequel.cariIsi("select date(now())") + "', '" + Sequel.cariIsi("SELECT TIME(NOW())") + "',"
                                        + "'" + tbItemResep1.getValueAt(i, 4).toString() + "','BELUM','" + akses.getkode() + "',"
                                        + "'" + tbItemResep1.getValueAt(i, 7).toString() + "','" + tbItemResep1.getValueAt(i, 9).toString() + "'", "Copy Resep Sebelumnya");
                                
                            } else if (chkRalan.isSelected() == true) {
                                Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(noId,6),signed)),0) from catatan_resep where "
                                        + "tgl_perawatan like '%" + Sequel.cariIsi("select year(now())") + "%' ",
                                        Sequel.cariIsi("select year(now())"), 6, noIdObatCopy);

                                Sequel.menyimpan("catatan_resep", "'" + noIdObatCopy.getText() + "','" + TNoRw.getText() + "', "
                                        + "'" + Sequel.cariIsi("select date(now())") + "', '" + Sequel.cariIsi("SELECT TIME(NOW())") + "',"
                                        + "'" + tbItemResep1.getValueAt(i, 4).toString() + "','BELUM','" + akses.getkode() + "'", "Copy Resep Sebelumnya");
                            }
                        }
                    }

                    DTPCariA.setDate(new Date());
                    DTPCariB.setDate(new Date());
                    WindowRiwayatResep.dispose();
                    TResepObat.setText("");
                    TResepObat.requestFocus();
                    tampilResepObat();
                    JOptionPane.showMessageDialog(null, "Resep sebelumnya pada tgl. " + Valid.SetTglINDONESIA(tglResepRiwayat) + " berhasil tercopy,..");
                }

            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }
        }
    }//GEN-LAST:event_BtnCopyResepActionPerformed

    private void BtnCopyResepKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCopyResepKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCopyResepActionPerformed(null);
        }
    }//GEN-LAST:event_BtnCopyResepKeyPressed

    private void BtnCloseIn8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn8ActionPerformed
        WindowRiwayatResep.dispose();
        TResepObat.requestFocus();
    }//GEN-LAST:event_BtnCloseIn8ActionPerformed

    private void tbPemberianResepMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPemberianResepMouseClicked
        tglResepRiwayat = "";

        if (tabModeResepA.getRowCount() != 0) {
            try {
                tampilItemResepRiwayat(tbPemberianResep.getValueAt(tbPemberianResep.getSelectedRow(), 2).toString(), TNoRw.getText(),
                        tbPemberianResep.getValueAt(tbPemberianResep.getSelectedRow(), 3).toString());
                tglResepRiwayat = tbPemberianResep.getValueAt(tbPemberianResep.getSelectedRow(), 2).toString();
                cmbConteng.setSelectedIndex(0);
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbPemberianResepMouseClicked

    private void tbPemberianResepKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPemberianResepKeyPressed
        if (tabModeResepA.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    tampilItemResepRiwayat(tbPemberianResep.getValueAt(tbPemberianResep.getSelectedRow(), 2).toString(), TNoRw.getText(),
                    tbPemberianResep.getValueAt(tbPemberianResep.getSelectedRow(), 3).toString());
                    cmbConteng.setSelectedIndex(0);
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbPemberianResepKeyPressed

    private void tbItemResep1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbItemResep1MouseClicked
        if (tabModeResepB.getRowCount() != 0) {
            hitungItemResepDiconteng();
        }
    }//GEN-LAST:event_tbItemResep1MouseClicked

    private void tbItemResep1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbItemResep1KeyPressed
        if (tabModeResepB.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                hitungItemResepDiconteng();
            }
        }
    }//GEN-LAST:event_tbItemResep1KeyPressed

    private void chkRanapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkRanapActionPerformed
        tampilTglResep();
        Valid.tabelKosong(tabModeResepB);
        LCount2.setText("0");
    }//GEN-LAST:event_chkRanapActionPerformed

    private void chkRalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkRalanActionPerformed
        tampilTglResep();
        Valid.tabelKosong(tabModeResepB);
        LCount2.setText("0");
    }//GEN-LAST:event_chkRalanActionPerformed

    private void MnGantiIniResepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnGantiIniResepActionPerformed
        if (tabModeResepObat.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, Item resep obat masih kosong...");
            tbResepObat.requestFocus();
        } else {
            //cek conteng
            x = 0;
            for (i = 0; i < tbResepObat.getRowCount(); i++) {
                if (tbResepObat.getValueAt(i, 0).toString().equals("true")) {
                    x++;
                }
            }

            if (x == 0) {
                JOptionPane.showMessageDialog(null, "Silahkan conteng dulu resep yang dipilih..");
                tbResepObat.requestFocus();
            } else {
                try {
                    for (i = 0; i < tbResepObat.getRowCount(); i++) {
                        if (tbResepObat.getValueAt(i, 0).toString().equals("true")) {
                            Sequel.mengedit("catatan_resep_ranap", "noId='" + tbResepObat.getValueAt(i, 7).toString() + "'",
                                    "resep_untuk='" + cmbIniResep.getSelectedItem().toString() + "'");
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                }

                TResepObat.setText("");
                TResepObat.requestFocus();
                tampilResepObat();
            }
        }
    }//GEN-LAST:event_MnGantiIniResepActionPerformed

    private void MnGantiDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnGantiDokterActionPerformed
        if (tabModeResepObat.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, Item resep obat masih kosong...");
            tbResepObat.requestFocus();
        } else {
            //cek conteng
            x = 0;
            for (i = 0; i < tbResepObat.getRowCount(); i++) {
                if (tbResepObat.getValueAt(i, 0).toString().equals("true")) {
                    x++;
                }
            }

            if (x == 0) {
                JOptionPane.showMessageDialog(null, "Silahkan conteng dulu resep yang dipilih utk. di ganti dokter peresepnya..");
                tbResepObat.requestFocus();
            } else {
                WindowGantiDokter.setSize(627, 118);
                WindowGantiDokter.setLocationRelativeTo(internalFrame1);
                WindowGantiDokter.setVisible(true);
                kddokter.setText("");
                TDokter.setText("");
                btnCariDokter.requestFocus();
            }
        }
    }//GEN-LAST:event_MnGantiDokterActionPerformed

    private void btnCariDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariDokterActionPerformed
        pilihan = 0;
        pilihan = 1;
        akses.setform("DlgCatatanResepBiasaAntibiotik");
        dokter.emptTeks();
        dokter.isCek();
        dokter.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);
    }//GEN-LAST:event_btnCariDokterActionPerformed

    private void BtnSimpan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan1ActionPerformed
        if (Sequel.cariRegistrasi(TNoRw.getText()) > 0) {
            JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi..");
        } else {
            if (TNoRw.getText().trim().equals("")) {
                Valid.textKosong(TNoRw, "No.Rawat");
            } else if (kddokter.getText().trim().equals("") || kddokter.getText().trim().equals("-") || kddokter.getText().trim().equals("--")) {
                Valid.textKosong(kddokter, "Dokter");
            } else {
                try {
                    for (i = 0; i < tbResepObat.getRowCount(); i++) {
                        if (tbResepObat.getValueAt(i, 0).toString().equals("true")) {
                            if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)") || status.equals("ralan")) {
                                Sequel.mengedit("catatan_resep", "noId='" + tbResepObat.getValueAt(i, 7).toString() + "'", "kd_dokter='" + kddokter.getText() + "'");
                            } else if (status.equals("ranap") || status.equals("vk bersalin")) {
                                Sequel.mengedit("catatan_resep_ranap", "noId='" + tbResepObat.getValueAt(i, 7).toString() + "'", "kd_dokter='" + kddokter.getText() + "'");
                            }
                        }
                    }
                    tampilResepObat();
                    WindowGantiDokter.dispose();
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                }
            }
        }
    }//GEN-LAST:event_BtnSimpan1ActionPerformed

    private void BtnCloseIn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn1ActionPerformed
        WindowGantiDokter.dispose();
    }//GEN-LAST:event_BtnCloseIn1ActionPerformed

    private void tbObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObatMouseClicked
        if (tabModeFarmasi.getRowCount() != 0) {
            try {
                getDataFarmasi();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbObatMouseClicked

    private void tbObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObatKeyPressed
        if (tabModeFarmasi.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataFarmasi();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbObatKeyPressed

    private void BtnPilihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPilihActionPerformed
        if (cmbObat.getSelectedItem().equals("-")) {
            JOptionPane.showMessageDialog(null, "Obat/alkes belum dipilih dengan benar...");
            cmbObat.requestFocus();
        } else {
            if (TabResep.getSelectedIndex() == 0) {
                if (TResepObat.getText().equals("")) {
                    TResepObat.setText(cmbObat.getSelectedItem().toString() + " ");
                } else {
                    TResepObat.setText(TResepObat.getText() + " " + cmbObat.getSelectedItem().toString());
                }
                TResepObat.requestFocus();
            } else if (TabResep.getSelectedIndex() == 1) {
                if (TResepObatAn.getText().equals("")) {
                    TResepObatAn.setText(cmbObat.getSelectedItem().toString() + " ");
                } else {
                    TResepObatAn.setText(TResepObatAn.getText() + " " + cmbObat.getSelectedItem().toString());
                }
                TResepObatAn.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnPilihActionPerformed

    private void cmbObatMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbObatMouseReleased
        AutoCompleteDecorator.decorate(cmbObat);
    }//GEN-LAST:event_cmbObatMouseReleased

    private void TCariObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariObatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCekObatActionPerformed(null);
        }
    }//GEN-LAST:event_TCariObatKeyPressed

    private void BtnCekObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCekObatActionPerformed
        tampilFarmasi();
        cmbObat.setSelectedIndex(0);
    }//GEN-LAST:event_BtnCekObatActionPerformed

    private void TabResepMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabResepMouseClicked
        BtnCariObatActionPerformed(null);
    }//GEN-LAST:event_TabResepMouseClicked

    private void TResepObatAnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TResepObatAnKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbHari.requestFocus();
        }
    }//GEN-LAST:event_TResepObatAnKeyPressed

    private void TResepObatAnKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TResepObatAnKeyTyped
        evt.setKeyChar(Character.toUpperCase(evt.getKeyChar()));
    }//GEN-LAST:event_TResepObatAnKeyTyped

    private void BtnCopyResepTerakhir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCopyResepTerakhir1ActionPerformed
        cekResep = 0 ;
        tglResepAnti = "";

        if (statusAnti.equals("IGD (Ralan)") || statusAnti.equals("IGD (Ranap)") || statusAnti.equals("ralan")) {
            cekResep = Sequel.cariInteger("SELECT count(-1) FROM catatan_resep_antibiotik cr INNER JOIN reg_periksa rp ON rp.no_rawat=cr.no_rawat "
                + "WHERE rp.kd_poli='" + kodepoliAnti + "' and rp.no_rkm_medis='" + TNoRM.getText() + "'");

            if (cekResep == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, tidak ada resep antibiotik terakhir sesuai kunjungan yg. tersimpan didalam sistem...");
            } else if (akses.getadmin() == true) {
                JOptionPane.showMessageDialog(null, "Meskipun anda admin utama, tetaplah seorang dokter yang boleh meresepkan obat...");
            } else {
                tglResepAnti = Sequel.cariIsi("SELECT cr.tgl_perawatan FROM catatan_resep_antibiotik cr INNER JOIN reg_periksa rp ON rp.no_rawat=cr.no_rawat "
                    + "WHERE rp.kd_poli='" + kodepoliAnti + "' and rp.no_rkm_medis='" + TNoRM.getText() + "' "
                    + "GROUP BY cr.tgl_perawatan, rp.no_rkm_medis ORDER BY cr.tgl_perawatan DESC LIMIT 1");

                x = JOptionPane.showConfirmDialog(null, "Resep antibiotik terakhir pada tgl. " + Valid.SetTglINDONESIA(tglResepAnti) + " apakah akan dicopy...?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    tampilItemResepRalanAnti(tglResepAnti, TNoRM.getText(), kodepoliAnti);

                    for (i = 0; i < tbItemResepAnti.getRowCount(); i++) {
                        tbItemResepAnti.setValueAt(Boolean.TRUE, i, 0);
                    }
                    copyResepnyaAnti();
                }
            }
        } else if (statusAnti.equals("ranap") || statusAnti.equals("vk bersalin")) {
            cekResep = Sequel.cariInteger("SELECT count(-1) FROM catatan_resep_ranap_antibiotik WHERE no_rawat='" + TNoRw.getText() + "'");

            if (cekResep == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, tidak ada resep antibiotik terakhir sesuai kunjungan yg. tersimpan didalam sistem...");
            } else if (akses.getadmin() == true) {
                JOptionPane.showMessageDialog(null, "Meskipun anda admin utama, tetaplah seorang dokter yang boleh meresepkan obat...");
            } else {
                tglResepAnti = Sequel.cariIsi("SELECT tgl_perawatan FROM catatan_resep_ranap_antibiotik where no_rawat='" + TNoRw.getText() + "' ORDER BY noId DESC LIMIT 1");

                x = JOptionPane.showConfirmDialog(null, "Resep antibiotik terakhir pada tgl. " + Valid.SetTglINDONESIA(tglResepAnti) + " apakah akan dicopy...?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    tampilItemResepRanapAnti(tglResepAnti, TNoRw.getText());

                    for (i = 0; i < tbItemResepAnti.getRowCount(); i++) {
                        tbItemResepAnti.setValueAt(Boolean.TRUE, i, 0);
                    }
                    copyResepnyaAnti();
                }
            }
        }
    }//GEN-LAST:event_BtnCopyResepTerakhir1ActionPerformed

    private void TketKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnSimpan2ActionPerformed(null);
        }
    }//GEN-LAST:event_TketKeyPressed

    private void cmbHariMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbHariMouseReleased
        AutoCompleteDecorator.decorate(cmbHari);
    }//GEN-LAST:event_cmbHariMouseReleased

    private void cmbHariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbHariActionPerformed
        if (cmbHari.getSelectedItem().equals("1") || cmbHari.getSelectedItem().equals("6") || cmbHari.getSelectedItem().equals("11")
            || cmbHari.getSelectedItem().equals("16") || cmbHari.getSelectedItem().equals("21") || cmbHari.getSelectedItem().equals("26")) {
            Tket.setEnabled(true);
            Tket.requestFocus();
        } else {
            Tket.setEnabled(false);
        }
    }//GEN-LAST:event_cmbHariActionPerformed

    private void tbResepObat1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbResepObat1MouseClicked
        if (tabModeResepObatAn.getRowCount() != 0) {
            try {
                getDataCatatanResepAnti();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbResepObat1MouseClicked

    private void tbResepObat1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbResepObat1KeyPressed
        if (tabModeResepObatAn.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataCatatanResepAnti();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbResepObat1KeyPressed

    private void ChkInputAnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputAnActionPerformed
        isFormRiwayatObatAnti();
    }//GEN-LAST:event_ChkInputAnActionPerformed

    private void tbTglBeriObat1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTglBeriObat1MouseClicked
        if (tabModeTglBeriObatAn.getRowCount() != 0) {
            try {
                getDataRiwObatAnti();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbTglBeriObat1MouseClicked

    private void tbTglBeriObat1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbTglBeriObat1KeyPressed
        if (tabModeTglBeriObatAn.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataRiwObatAnti();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbTglBeriObat1KeyPressed

    private void ChkPoliAnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkPoliAnActionPerformed
        Valid.tabelKosong(tabModeRiwItemObatAn);
        if (statusAnti.equals("IGD (Ralan)") || statusAnti.equals("IGD (Ranap)") || statusAnti.equals("ralan") || statusAnti.equals("vk bersalin")) {
            if (ChkPoliAn.isSelected() == true) {
                ChkPoliAn.setText("Hanya dipoli/inst. ini untuk 5 kunjungan terakhir");
                tampilTglBeriObatAnti();
            } else if (ChkPoliAn.isSelected() == false) {
                ChkPoliAn.setText("Semua Poli/Inst. untuk 5 kunjungan terakhir");
                tampilTglBeriObatAnti();
            }
        } else if (statusAnti.equals("ranap")) {
            if (ChkPoliAn.isSelected() == true) {
                ChkPoliAn.setText("Semua resep ditampilkan selama perawatan saat ini");
                tampilTglBeriObatAnti();
            } else if (ChkPoliAn.isSelected() == false) {
                ChkPoliAn.setText("Semua resep ditampilkan selama perawatan saat ini");
                tampilTglBeriObatAnti();
            }
        }
    }//GEN-LAST:event_ChkPoliAnActionPerformed

    private void tbItemObat1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbItemObat1MouseClicked
        if (tabModeRiwItemObatAn.getRowCount() != 0) {
            try {
                getDataItemObatAnti();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbItemObat1MouseClicked

    private void tbItemObat1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbItemObat1KeyPressed
        if (tabModeRiwItemObatAn.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataItemObatAnti();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbItemObat1KeyPressed

    private void BtnSimpan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan2ActionPerformed
        if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data pasiennya dulu...");
        } else if (TResepObatAn.getText().trim().equals("")) {
            Valid.textKosong(TResepObatAn, "nama obat");
            TResepObatAn.requestFocus();
        } else if (akses.getadmin() == true) {
            JOptionPane.showMessageDialog(null, "Meskipun anda admin utama, tetaplah seorang dokter yang boleh meresepkan obat...");
        } else if (cmbHari.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih dulu utk. resep hari keberapanya dg. benar...");
            cmbHari.requestFocus();
        } else if ((cmbHari.getSelectedItem().equals("1") || cmbHari.getSelectedItem().equals("6") || cmbHari.getSelectedItem().equals("11")
            || cmbHari.getSelectedItem().equals("16") || cmbHari.getSelectedItem().equals("21") || cmbHari.getSelectedItem().equals("26")) && Tket.getText().equals("")) {
        JOptionPane.showMessageDialog(null, "Maaf, untuk resep hari ke " + cmbHari.getSelectedItem().toString() + " kalimat keterangan harus diisi dulu...");
        Tket.requestFocus();
        } else if ((cmbHari.getSelectedItem().equals("1") || cmbHari.getSelectedItem().equals("6") || cmbHari.getSelectedItem().equals("11")
            || cmbHari.getSelectedItem().equals("16") || cmbHari.getSelectedItem().equals("21") || cmbHari.getSelectedItem().equals("26")) && Tket.getText().length() <= 5) {
        JOptionPane.showMessageDialog(null, "Maaf, kalimat keterangan minimal diisi 6 digit...");
        Tket.requestFocus();
        } else {
            if (cmbHari.getSelectedItem().equals("1") || cmbHari.getSelectedItem().equals("6") || cmbHari.getSelectedItem().equals("11")
                || cmbHari.getSelectedItem().equals("16") || cmbHari.getSelectedItem().equals("21") || cmbHari.getSelectedItem().equals("26")) {
                Tket.setText(Tket.getText());
            } else {
                Tket.setText("");
            }

            if (statusAnti.equals("IGD (Ralan)") || statusAnti.equals("IGD (Ranap)") || statusAnti.equals("ralan")) {
                Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(noId,6),signed)),0) from catatan_resep_antibiotik where "
                    + "tgl_perawatan like '%" + Sequel.cariIsi("select year(now())") + "%' ",
                    Sequel.cariIsi("select year(now())"), 6, noIdObatAnti);
                
                if (Sequel.menyimpantf("catatan_resep_antibiotik", "?,?,?,?,?,?,?,?,?", "Data", 9, new String[]{
                    noIdObatAnti.getText(), TNoRw.getText(), Sequel.cariIsi("SELECT date(NOW())"), Sequel.cariIsi("SELECT TIME(NOW())"),
                    TResepObatAn.getText(), "BELUM", akses.getkode(), Tket.getText(), cmbHari.getSelectedItem().toString()
                }) == true) {
                    Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Catatan Resep Antibiotik Ralan Pasien", "Simpan");
                    Sequel.mengedit("reg_periksa", "no_rawat='" + TNoRw.getText() + "'", "stts='Sudah Diperiksa Dokter'");
                    TResepObatAn.setText("");
                    Tket.setText("");
                    TResepObatAn.requestFocus();
                    tampilResepObatAnti();
                }
            } else if (statusAnti.equals("ranap") || statusAnti.equals("vk bersalin")) {
                if (Sequel.cariInteger("select count(-1) from kamar_inap where no_rawat='" + TNoRw.getText() + "' and stts_pulang not in ('-','Pindah Kamar')") > 0) {
                    JOptionPane.showMessageDialog(null, "Maaf, pasien sudah dipulangkan dari ruangan, seluruh perawatan rawat inap           \n"
                        + "sudah selesai, jika belum selesai batalkan dulu status pulangnya..");
                } else {
                    jenisResepAnti = "";
                    if (ChkCitoAn.isSelected() == true) {
                        jenisResepAnti = "CITO";
                    } else {
                        jenisResepAnti = "BIASA";
                    }

                    Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(noId,6),signed)),0) from catatan_resep_ranap_antibiotik where "
                            + "tgl_perawatan like '%" + Sequel.cariIsi("select year(now())") + "%' ",
                            Sequel.cariIsi("select year(now())"), 6, noIdObatAnti);

                    if (Sequel.menyimpantf("catatan_resep_ranap_antibiotik", "?,?,?,?,?,?,?,?,?,?,?", "Data", 11, new String[]{
                        noIdObatAnti.getText(), TNoRw.getText(), Sequel.cariIsi("SELECT date(NOW())"), Sequel.cariIsi("SELECT TIME(NOW())"),
                        TResepObatAn.getText(), "BELUM", akses.getkode(), jenisResepAnti, cmbIniResepAn.getSelectedItem().toString(), Tket.getText(),
                        cmbHari.getSelectedItem().toString()
                    }) == true) {
                        Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Catatan Resep Antibiotik Ranap Pasien", "Simpan");
                        TResepObatAn.setText("");
                        Tket.setText("");
                        TResepObatAn.requestFocus();
                        tampilResepObatAnti();
                    }
                }
            }
        }
    }//GEN-LAST:event_BtnSimpan2ActionPerformed

    private void BtnHapus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapus1ActionPerformed
        if (tabModeResepObatAn.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...");
            TNoRw.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Gagal menghapus. Pilih dulu data yang mau dihapus. Klik data pada tabel untuk memilih...");
        } else if (akses.getadmin() == true) {
            JOptionPane.showMessageDialog(null, "Meskipun anda admin utama, tetaplah seorang dokter yang boleh menghapus resepnya...");
        } else if (!TPasien.getText().trim().equals("")) {
            riwayatDataAnti = "";
            riwayatDataAnti = "hapus";
            if (statusAnti.equals("IGD (Ralan)") || statusAnti.equals("IGD (Ranap)") || statusAnti.equals("ralan")) {
                //cek conteng
                x = 0;
                for (i = 0; i < tbResepObat1.getRowCount(); i++) {
                    if (tbResepObat1.getValueAt(i, 0).toString().equals("true")) {
                        x++;
                    }
                }

                if (x == 0) {
                    JOptionPane.showMessageDialog(null, "Silahkan conteng dulu item resep obatnya..");
                    tbResepObat1.requestFocus();
                } else {
                    for (i = 0; i < tbResepObat1.getRowCount(); i++) {
                        if (tbResepObat1.getValueAt(i, 0).toString().equals("true")
                            && (tbResepObat1.getValueAt(i, 5).toString().equals("SUDAH") || tbResepObat1.getValueAt(i, 5).toString().equals("DILUAR"))) {
                            JOptionPane.showMessageDialog(null, "Mohon maaf, untuk resep " + tbResepObat1.getValueAt(i, 4).toString() + " sudah diverifikasi apotek,     \n"
                                + "data tdk. bisa dihapus, Silakan input lagi sbg. resep baru/lanjutan...");
                        } else if (tbResepObat1.getValueAt(i, 0).toString().equals("true") && tbResepObat1.getValueAt(i, 5).toString().equals("BELUM")) {
                            simpanHistoriResepRalanAnti();
                        }
                    }

                    TResepObatAn.setText("");
                    cmbHari.setSelectedIndex(0);
                    Tket.setText("");
                    tampilResepObatAnti();
                }

            } else if (statusAnti.equals("ranap") || statusAnti.equals("vk bersalin")) {
                //cek conteng
                x = 0;
                for (i = 0; i < tbResepObat1.getRowCount(); i++) {
                    if (tbResepObat1.getValueAt(i, 0).toString().equals("true")) {
                        x++;
                    }
                }

                if (x == 0) {
                    JOptionPane.showMessageDialog(null, "Silahkan conteng dulu item resep obatnya..");
                    tbResepObat1.requestFocus();
                } else {
                    for (i = 0; i < tbResepObat1.getRowCount(); i++) {
                        if (tbResepObat1.getValueAt(i, 0).toString().equals("true")
                            && (tbResepObat1.getValueAt(i, 5).toString().equals("SUDAH") || tbResepObat1.getValueAt(i, 5).toString().equals("DILUAR"))) {
                            JOptionPane.showMessageDialog(null, "Mohon maaf, untuk resep " + tbResepObat1.getValueAt(i, 4).toString() + " sudah diverifikasi apotek,     \n"
                                + "data tdk. bisa dihapus, Silakan input lagi sbg. resep baru/lanjutan...");
                        } else if (tbResepObat1.getValueAt(i, 0).toString().equals("true") && tbResepObat1.getValueAt(i, 5).toString().equals("BELUM")) {
                            simpanHistoriResepRanapAnti();
                        }
                    }

                    TResepObatAn.setText("");
                    cmbHari.setSelectedIndex(0);
                    Tket.setText("");
                    tampilResepObatAnti();
                }
            }
        }
    }//GEN-LAST:event_BtnHapus1ActionPerformed

    private void BtnEdit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEdit1ActionPerformed
        if (tbResepObat1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Tidak ada data yang akan diperbaiki..");
        } else if (TResepObatAn.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Silakan pilih dulu salah satu datanya pada tabel..");
        } else if (akses.getadmin() == true) {
            JOptionPane.showMessageDialog(null, "Meskipun anda admin utama, tetaplah seorang dokter yang boleh merubah resepnya...");
        } else if (cmbHari.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih dulu utk. resep hari keberapanya dg. benar...");
            cmbHari.requestFocus();
        } else if ((cmbHari.getSelectedItem().equals("1") || cmbHari.getSelectedItem().equals("6") || cmbHari.getSelectedItem().equals("11")
            || cmbHari.getSelectedItem().equals("16") || cmbHari.getSelectedItem().equals("21") || cmbHari.getSelectedItem().equals("26")) && Tket.getText().equals("")) {
        JOptionPane.showMessageDialog(null, "Maaf, untuk resep hari ke " + cmbHari.getSelectedItem().toString() + " kalimat keterangan harus diisi dulu...");
        Tket.requestFocus();
        } else if ((cmbHari.getSelectedItem().equals("1") || cmbHari.getSelectedItem().equals("6") || cmbHari.getSelectedItem().equals("11")
            || cmbHari.getSelectedItem().equals("16") || cmbHari.getSelectedItem().equals("21") || cmbHari.getSelectedItem().equals("26")) && Tket.getText().length() <= 5) {
        JOptionPane.showMessageDialog(null, "Maaf, kalimat keterangan minimal diisi 6 digit...");
        Tket.requestFocus();
        } else {
            if (cmbHari.getSelectedItem().equals("1") || cmbHari.getSelectedItem().equals("6") || cmbHari.getSelectedItem().equals("11")
                || cmbHari.getSelectedItem().equals("16") || cmbHari.getSelectedItem().equals("21") || cmbHari.getSelectedItem().equals("26")) {
                Tket.setText(Tket.getText());
            } else {
                Tket.setText("");
            }

            if (statusAnti.equals("IGD (Ralan)") || statusAnti.equals("IGD (Ranap)") || statusAnti.equals("ralan")) {
                if (tbResepObat1.getSelectedRow() > -1) {
                    if (tbResepObat1.getValueAt(tbResepObat1.getSelectedRow(), 5).toString().equals("SUDAH")
                        || tbResepObat1.getValueAt(tbResepObat1.getSelectedRow(), 5).toString().equals("DILUAR")) {
                        JOptionPane.showMessageDialog(null, "Untuk resep yang sudah diverifikasi apotek tdk. bisa diperbaiki,     \n"
                            + "Silakan klik tombol simpan sbg. resep baru/lanjutan...");
                    } else {
                        if (Sequel.mengedittf("catatan_resep_antibiotik", "noId=?", "no_rawat=?, tgl_perawatan=?, jam_perawatan=?, nama_obat=?, keterangan=?, hari_ke=?", 7, new String[]{
                            TNoRw.getText(), Sequel.cariIsi("SELECT date(NOW())"), Sequel.cariIsi("SELECT TIME(NOW())"),
                            TResepObatAn.getText(), Tket.getText(), cmbHari.getSelectedItem().toString(),
                            TIdObatAnti.getText()
                        }) == true) {
                            Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Catatan Resep Antibiotik Ralan Pasien", "Ganti");
                            TResepObatAn.setText("");
                            Tket.setText("");
                            tampilResepObatAnti();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Silahkan pilih data yang mau diganti..");
                    TCari.requestFocus();
                }
            } else if (statusAnti.equals("ranap") || statusAnti.equals("vk bersalin")) {
                if (tbResepObat1.getSelectedRow() > -1) {
                    if (tbResepObat1.getValueAt(tbResepObat1.getSelectedRow(), 5).toString().equals("SUDAH")
                        || tbResepObat1.getValueAt(tbResepObat1.getSelectedRow(), 5).toString().equals("DILUAR")) {
                        JOptionPane.showMessageDialog(null, "Untuk resep yang sudah diverifikasi apotek tdk. bisa diperbaiki,     \n"
                            + "Silakan klik tombol simpan sbg. resep baru/lanjutan...");
                    } else {
                        jenisResepAnti = "";
                        if (ChkCitoAn.isSelected() == true) {
                            jenisResepAnti = "CITO";
                        } else {
                            jenisResepAnti = "BIASA";
                        }

                        if (Sequel.mengedittf("catatan_resep_ranap_antibiotik", "noId=?", "no_rawat=?, tgl_perawatan=?, "
                                + "jam_perawatan=?, nama_obat=?, jenis_resep=?, resep_untuk=?, keterangan=?, hari_ke=?", 9, new String[]{
                                    TNoRw.getText(), Sequel.cariIsi("SELECT date(NOW())"), Sequel.cariIsi("SELECT TIME(NOW())"),
                                    TResepObatAn.getText(), jenisResepAnti, cmbIniResepAn.getSelectedItem().toString(), Tket.getText(),
                                    cmbHari.getSelectedItem().toString(),
                                    TIdObatAnti.getText()
                                }) == true) {
                            Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Catatan Resep Antibiotik Ranap Pasien", "Ganti");
                            TResepObatAn.setText("");
                            Tket.setText("");
                            tampilResepObatAnti();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Silahkan pilih data yang mau diganti..");
                    TCari.requestFocus();
                }
            }
        }
    }//GEN-LAST:event_BtnEdit1ActionPerformed

    private void BtnEdit1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEdit1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnEdit1ActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus1, BtnPrint1);
        }
    }//GEN-LAST:event_BtnEdit1KeyPressed

    private void BtnPrint1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrint1ActionPerformed
        if (tbResepObat1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Resep obat untuk pasien tersebut belum ada ditabel...");
            BtnCariObat.requestFocus();
        } else if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "No. rawat pasien tidak ditemukan...");
            tbResepObat1.requestFocus();
        } else {
            //cek contengnya
            x = 0;
            for (i = 0; i < tbResepObat1.getRowCount(); i++) {
                if (tbResepObat1.getValueAt(i, 0).toString().equals("true")) {
                    x++;
                }
            }

            if (x == 0) {
                JOptionPane.showMessageDialog(null, "Item resep yg. akan dicetak masih belum diconteng...");
                contengResep();
                BtnPrint1.requestFocus();
            } else {
                resepDipilihAnti = "";
                jenisResepAnti = "";
                cito = 0;
                iniResep = 0;
                for (i = 0; i < tbResepObat1.getRowCount(); i++) {
                    if (tbResepObat1.getValueAt(i, 0).toString().equals("true")) {
                        if (resepDipilihAnti.equals("")) {
                            resepDipilihAnti = "'" + tbResepObat1.getValueAt(i, 7).toString() + "'";
                        } else {
                            resepDipilihAnti = resepDipilihAnti + ",'" + tbResepObat1.getValueAt(i, 7).toString() + "'";
                        }
                    }
                }

                //cek resep cito
                for (i = 0; i < tbResepObat1.getRowCount(); i++) {
                    if (tbResepObat1.getValueAt(i, 0).toString().equals("true")
                            && tbResepObat1.getValueAt(i, 9).toString().equals("CITO")) {
                        cito++;
                    }
                }

                if (cito == 0) {
                    jenisResepAnti = "BIASA";
                } else {
                    jenisResepAnti = "CITO";
                }

                //cek resep pulang
                for (i = 0; i < tbResepObat1.getRowCount(); i++) {
                    if (tbResepObat1.getValueAt(i, 0).toString().equals("true")
                            && tbResepObat1.getValueAt(i, 10).toString().equals("Pulang")) {
                        iniResep++;
                    }
                }

                if (iniResep == 0) {
                    resepPulangAnti = "Dalam Perawatan";
                } else {
                    resepPulangAnti = "Pulang";
                }

                if (statusAnti.equals("IGD (Ralan)") || statusAnti.equals("IGD (Ranap)") || statusAnti.equals("ralan")) {
                    if (Sequel.cariInteger("SELECT COUNT(-1) FROM catatan_resep_antibiotik WHERE no_rawat ='" + TNoRw.getText() + "' AND noId IN (" + resepDipilihAnti + ")") == 0) {
                        JOptionPane.showMessageDialog(null, "Maaf, hanya resep antibiotik utk. hari ini yg. bisa dicetak, krn. pasien sdh. dilayani/diresepkan obatnya...");
                        tbResepObat1.requestFocus();
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

                        if (Sequel.cariInteger("select count(-1) from reg_konsul_internal where no_rawat='" + TNoRw.getText() + "'") == 0) {
                            param.put("nosep", Sequel.cariIsi("select ifnull(no_sep,'-') from bridging_sep where no_rawat='" + TNoRw.getText() + "' and jnspelayanan='2'") + " "
                                    + Sequel.cariIsi("select if(count(-1)>0,'(Program PRB BPJS)','') from bridging_srb_bpjs where no_srb='" + TNoRw.getText() + "'"));
                        } else {
                            param.put("nosep", Sequel.cariIsi("select no_sep from reg_konsul_internal where no_rawat='" + TNoRw.getText() + "'") + " "
                                    + Sequel.cariIsi("select if(count(-1)>0,'(Program PRB BPJS)','') from bridging_srb_bpjs where no_srb='" + TNoRw.getText() + "'"));
                        }

                        if (cmbPilihCetakAn.getSelectedIndex() == 0) {
                            String isi = "";
                            isi = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                                    + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='009'"),
                                            "Resep Antibiotik Rawat Jalan", Sequel.cariIsi("select d.nm_dokter from catatan_resep_antibiotik c "
                                                    + "inner join dokter d on d.kd_dokter = c.kd_dokter where c.no_rawat ='" + TNoRw.getText() + "' order by noId desc limit 1"),
                                            Sequel.cariIsi("select date_format(tgl_perawatan,'%d/%m/%Y') from catatan_resep_antibiotik where "
                                                    + "no_rawat='" + TNoRw.getText() + "' order by noId desc limit 1"),
                                            Sequel.cariIsi("select time(jam_perawatan) from catatan_resep_antibiotik where "
                                                    + "no_rawat='" + TNoRw.getText() + "'  order by noId desc limit 1")) + "') from kalimat_tte where kode='009'");

                            Valid.cetakQrTte(isi, Sequel.cariFolderTte(), "QRTte.jpg", "select logo from setting");
                            Sequel.queryu("delete from setting_qr where judul = 'QRTte'");
                            Sequel.menyimpanQr("setting_qr", "'QRTte'", "file QRCode TTE Resep", Sequel.cariFolderPrintTte());
                            param.put("lokasiQr", Sequel.cariGambar("select gambar from setting_qr where judul = 'QRTte'"));
                            param.put("kalimatTte", Sequel.cariIsi("select replace(kalimat_footer,'##jns_dokumen##',jenis_dokumen) from kalimat_tte where kode='009'"));

                            Valid.MyReport("rptResepRalanAntibiotikQr.jasper", "report", "::[ Resep Dokter Poliklinik/Unit Rawat Jalan ]::",
                                    " select c.no_rawat, pl.nm_poli, d.nm_dokter, CONCAT(if(iob.no_rawat is null,'','(RESEP ITER) '),'Martapura, ',DATE_FORMAT(c.tgl_perawatan,'%d/%m/%Y')) tgl_resep, "
                                    + "if(c.hari_ke not in ('1','6','11','16','21','26'),concat(c.nama_obat,' (Hari ke ',c.hari_ke,')'),concat(c.nama_obat,' (ket. ',c.keterangan,', Hari ke ',c.hari_ke,')')) nama_obat, "
                                    + "r.no_rkm_medis, p.nm_pasien, CONCAT(r.umurdaftar,' ',r.sttsumur) umur, "
                                    + "CONCAT(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat, d.no_ijn_praktek no_sip, ifnull(p.no_tlp,'-') noHP from catatan_resep_antibiotik c "
                                    + "inner join reg_periksa r on r.no_rawat = c.no_rawat inner join dokter d on d.kd_dokter = c.kd_dokter "
                                    + "INNER JOIN poliklinik pl on pl.kd_poli=r.kd_poli INNER JOIN pasien p on p.no_rkm_medis=r.no_rkm_medis "
                                    + "INNER JOIN kelurahan kl on kl.kd_kel=p.kd_kel INNER JOIN kecamatan kc on kc.kd_kec=p.kd_kec "
                                    + "INNER JOIN kabupaten kb on kb.kd_kab=p.kd_kab left join iter_obat_bpjs iob on iob.no_rawat=c.no_rawat where "
                                    + "c.no_rawat ='" + TNoRw.getText() + "' and c.noId in (" + resepDipilihAnti + ") order by c.noId", param);
                            Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);
                        } else {
                            Valid.MyReport("rptResepRalanAntibiotik.jasper", "report", "::[ Resep Dokter Poliklinik/Unit Rawat Jalan ]::",
                                    " select c.no_rawat, pl.nm_poli, d.nm_dokter, CONCAT(if(iob.no_rawat is null,'','(RESEP ITER) '),'Martapura, ',DATE_FORMAT(c.tgl_perawatan,'%d/%m/%Y')) tgl_resep, "
                                    + "if(c.hari_ke not in ('1','6','11','16','21','26'),concat(c.nama_obat,' (Hari ke ',c.hari_ke,')'),concat(c.nama_obat,' (ket. ',c.keterangan,', Hari ke ',c.hari_ke,')')) nama_obat, "
                                    + "r.no_rkm_medis, p.nm_pasien, CONCAT(r.umurdaftar,' ',r.sttsumur) umur, "
                                    + "CONCAT(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat, d.no_ijn_praktek no_sip, ifnull(p.no_tlp,'-') noHP from catatan_resep_antibiotik c "
                                    + "inner join reg_periksa r on r.no_rawat = c.no_rawat inner join dokter d on d.kd_dokter = c.kd_dokter "
                                    + "INNER JOIN poliklinik pl on pl.kd_poli=r.kd_poli INNER JOIN pasien p on p.no_rkm_medis=r.no_rkm_medis "
                                    + "INNER JOIN kelurahan kl on kl.kd_kel=p.kd_kel INNER JOIN kecamatan kc on kc.kd_kec=p.kd_kec "
                                    + "INNER JOIN kabupaten kb on kb.kd_kab=p.kd_kab left join iter_obat_bpjs iob on iob.no_rawat=c.no_rawat where "
                                    + "c.no_rawat ='" + TNoRw.getText() + "' and c.noId in (" + resepDipilihAnti + ") order by c.noId", param);
                        }
                        this.setCursor(Cursor.getDefaultCursor());
                    }
                } else if (statusAnti.equals("ranap") || statusAnti.equals("vk bersalin")) {
                    if (Sequel.cariInteger("SELECT COUNT(-1) FROM catatan_resep_ranap_antibiotik WHERE no_rawat ='" + TNoRw.getText() + "' AND noId IN (" + resepDipilihAnti + ")") == 0) {
                        JOptionPane.showMessageDialog(null, "Maaf, hanya resep utk. hari ini yg. bisa dicetak, krn. pasien sdh. dilayani/diresepkan obatnya...");
                        tbResepObat1.requestFocus();
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

                        if (Sequel.cariInteger("select count(-1) from bridging_sep where no_rawat='" + TNoRw.getText() + "' and jnspelayanan='1'") == 0) {
                            param.put("nosep", "-");
                        } else {
                            param.put("nosep", Sequel.cariIsi("select no_sep from bridging_sep where no_rawat='" + TNoRw.getText() + "' and jnspelayanan='1' order by tglsep desc limit 1"));
                        }

                        param.put("ruangan", Sequel.cariIsi("select b.nm_bangsal from kamar_inap ki inner join kamar k on k.kd_kamar=ki.kd_kamar "
                                + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal where ki.no_rawat='" + TNoRw.getText() + "' "
                                + "order by ki.tgl_masuk desc, ki.jam_masuk desc limit 1") + " (Resep : " + jenisResepAnti + " - " + resepPulangAnti + ")");

                        if (cmbPilihCetakAn.getSelectedIndex() == 0) {
                            String isi = "";
                            isi = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                                    + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='009'"),
                                            "Resep Antibiotik Rawat Inap", Sequel.cariIsi("select d.nm_dokter from catatan_resep_ranap_antibiotik c "
                                                    + "inner join dokter d on d.kd_dokter = c.kd_dokter where c.no_rawat ='" + TNoRw.getText() + "' order by noId desc limit 1"),
                                            Sequel.cariIsi("select date_format(tgl_perawatan,'%d/%m/%Y') from catatan_resep_ranap_antibiotik where "
                                                    + "no_rawat='" + TNoRw.getText() + "' order by noId desc limit 1"),
                                            Sequel.cariIsi("select time(jam_perawatan) from catatan_resep_ranap_antibiotik where "
                                                    + "no_rawat='" + TNoRw.getText() + "'  order by noId desc limit 1")) + "') from kalimat_tte where kode='009'");

                            Valid.cetakQrTte(isi, Sequel.cariFolderTte(), "QRTte.jpg", "select logo from setting");
                            Sequel.queryu("delete from setting_qr where judul = 'QRTte'");
                            Sequel.menyimpanQr("setting_qr", "'QRTte'", "file QRCode TTE Resep", Sequel.cariFolderPrintTte());
                            param.put("lokasiQr", Sequel.cariGambar("select gambar from setting_qr where judul = 'QRTte'"));
                            param.put("kalimatTte", Sequel.cariIsi("select replace(kalimat_footer,'##jns_dokumen##',jenis_dokumen) from kalimat_tte where kode='009'"));

                            Valid.MyReport("rptResepRanapAntibiotikQr.jasper", "report", "::[ Resep Dokter Rawat Inap ]::",
                                    "SELECT c.no_rawat, d.nm_dokter, CONCAT('Martapura, ',DATE_FORMAT(c.tgl_perawatan, '%d/%m/%Y')) tgl_resep, "
                                    + "if(c.hari_ke not in ('1','6','11','16','21','26'),concat(c.nama_obat,' (Hari ke ',c.hari_ke,')'),concat(c.nama_obat,' (ket. ',c.keterangan,', Hari ke ',c.hari_ke,')')) nama_obat, "
                                    + "r.no_rkm_medis, p.nm_pasien, CONCAT(r.umurdaftar,' ',r.sttsumur) umur, "
                                    + "CONCAT(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat, d.no_ijn_praktek no_sip, ifnull(p.no_tlp, '-') noHP "
                                    + "FROM catatan_resep_ranap_antibiotik c INNER JOIN reg_periksa r ON r.no_rawat = c.no_rawat "
                                    + "INNER JOIN dokter d ON d.kd_dokter = c.kd_dokter INNER JOIN pasien p ON p.no_rkm_medis = r.no_rkm_medis "
                                    + "INNER JOIN kelurahan kl ON kl.kd_kel = p.kd_kel INNER JOIN kecamatan kc ON kc.kd_kec = p.kd_kec "
                                    + "INNER JOIN kabupaten kb ON kb.kd_kab = p.kd_kab WHERE "
                                    + "c.no_rawat = '" + TNoRw.getText() + "' AND c.noId IN (" + resepDipilihAnti + ") ORDER BY c.noId", param);
                            Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);
                        } else {
                            Valid.MyReport("rptResepRanapAntibiotik.jasper", "report", "::[ Resep Dokter Rawat Inap ]::",
                                    "SELECT c.no_rawat, d.nm_dokter, CONCAT('Martapura, ',DATE_FORMAT(c.tgl_perawatan, '%d/%m/%Y')) tgl_resep, "
                                    + "if(c.hari_ke not in ('1','6','11','16','21','26'),concat(c.nama_obat,' (Hari ke ',c.hari_ke,')'),concat(c.nama_obat,' (ket. ',c.keterangan,', Hari ke ',c.hari_ke,')')) nama_obat, "
                                    + "r.no_rkm_medis, p.nm_pasien, CONCAT(r.umurdaftar,' ',r.sttsumur) umur, "
                                    + "CONCAT(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat, d.no_ijn_praktek no_sip, ifnull(p.no_tlp, '-') noHP "
                                    + "FROM catatan_resep_ranap_antibiotik c INNER JOIN reg_periksa r ON r.no_rawat = c.no_rawat "
                                    + "INNER JOIN dokter d ON d.kd_dokter = c.kd_dokter INNER JOIN pasien p ON p.no_rkm_medis = r.no_rkm_medis "
                                    + "INNER JOIN kelurahan kl ON kl.kd_kel = p.kd_kel INNER JOIN kecamatan kc ON kc.kd_kec = p.kd_kec "
                                    + "INNER JOIN kabupaten kb ON kb.kd_kab = p.kd_kab WHERE "
                                    + "c.no_rawat = '" + TNoRw.getText() + "' AND c.noId IN (" + resepDipilihAnti + ") ORDER BY c.noId", param);
                        }
                        this.setCursor(Cursor.getDefaultCursor());
                    }
                }
            }
        }
    }//GEN-LAST:event_BtnPrint1ActionPerformed

    private void BtnPrint1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrint1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnEdit, BtnKeluar);
        }
    }//GEN-LAST:event_BtnPrint1KeyPressed

    private void BtnNotepad1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNotepad1ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        akses.setform("DlgCatatanResepBiasaAntibiotik");
        DlgNotepad form = new DlgNotepad(null, false);
        form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        form.setLocationRelativeTo(internalFrame1);
        form.setData(akses.getkode());
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnNotepad1ActionPerformed

    private void BtnKeluar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar1ActionPerformed
        BtnKeluarActionPerformed(null);
    }//GEN-LAST:event_BtnKeluar1ActionPerformed

    private void BtnKeluar1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluar1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnKeluarActionPerformed(null);
        }
    }//GEN-LAST:event_BtnKeluar1KeyPressed

    private void BtnSetuju1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSetuju1ActionPerformed
        if (tbResepObat1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Maaf, belum ada item/resep obat yang diberikan, simpan dulu resepnya..");
        } else if (cmbJnsResep1.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu jenis resepnya..");
            cmbJnsResep1.requestFocus();
        } else {
            resepDipilihAnti = "";
            for (i = 0; i < tbResepObat1.getRowCount(); i++) {
                if (resepDipilihAnti.equals("")) {
                    resepDipilihAnti = "'" + tbResepObat1.getValueAt(i, 7).toString() + "'";
                } else {
                    resepDipilihAnti = resepDipilihAnti + ",'" + tbResepObat1.getValueAt(i, 7).toString() + "'";
                }
            }

            if (Sequel.cariInteger("select count(-1) from catatan_resep_ranap_antibiotik where noId in (" + resepDipilihAnti + ") and (status='SUDAH' or status='DILUAR')") > 0) {
                JOptionPane.showMessageDialog(null, "Mohon maaf, untuk resep yang sudah diverifikasi apotek tdk. bisa diganti,     \n"
                    + "Silakan input lagi sbg. resep baru/lanjutan...");
            } else {
                for (i = 0; i < tbResepObat1.getRowCount(); i++) {
                    Sequel.mengedit("catatan_resep_ranap_antibiotik", "noId='" + tbResepObat1.getValueAt(i, 7).toString() + "'",
                        "jenis_resep='" + cmbJnsResep1.getSelectedItem().toString() + "'");
                }
            }

            ChkCitoAn.setSelected(false);
            cmbJnsResep1.setSelectedIndex(0);
            TResepObatAn.setText("");
            tampilResepObatAnti();
        }
    }//GEN-LAST:event_BtnSetuju1ActionPerformed

    private void tbItemResepAntiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbItemResepAntiMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbItemResepAntiMouseClicked

    private void tbItemResepAntiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbItemResepAntiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbItemResepAntiKeyPressed

    private void TCari3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari3KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari3ActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari3.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnCloseIn11.requestFocus();
        }
    }//GEN-LAST:event_TCari3KeyPressed

    private void BtnCari3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari3ActionPerformed
        tampilRiwayatAnti();
    }//GEN-LAST:event_BtnCari3ActionPerformed

    private void BtnCari3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari3KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCari3ActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari3, BtnAll2);
        }
    }//GEN-LAST:event_BtnCari3KeyPressed

    private void BtnAll2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAll2ActionPerformed
        TCari3.setText("");
        tampilRiwayatAnti();
    }//GEN-LAST:event_BtnAll2ActionPerformed

    private void BtnAll2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAll2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAll2ActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnCari3, TCari3);
        }
    }//GEN-LAST:event_BtnAll2KeyPressed

    private void BtnRestor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRestor1ActionPerformed
        if (tbRiwayat1.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data yang dipilih akan dikembalikan/restore..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                kembalikanDataAnti();
                TCari.setText("");
                Valid.SetTgl(DTPCariA, tbRiwayat1.getValueAt(tbRiwayat1.getSelectedRow(), 4).toString());
                BtnCloseIn11ActionPerformed(null);
                tampilResepObatAnti();
                TResepObatAn.setText("");
                TResepObatAn.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih salah satu datanya terlebih dahulu..");
        }
    }//GEN-LAST:event_BtnRestor1ActionPerformed

    private void BtnCloseIn11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn11ActionPerformed
        WindowRiwayatAnti.dispose();
        TCari3.setText("");
    }//GEN-LAST:event_BtnCloseIn11ActionPerformed

    private void chkRanapAnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkRanapAnActionPerformed
        tampilTglResepAnti();
        Valid.tabelKosong(tabModeResepBAn);
        LCount4.setText("0");
    }//GEN-LAST:event_chkRanapAnActionPerformed

    private void chkRalanAnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkRalanAnActionPerformed
        tampilTglResepAnti();
        Valid.tabelKosong(tabModeResepBAn);
        LCount4.setText("0");
    }//GEN-LAST:event_chkRalanAnActionPerformed

    private void cmbContengAnItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbContengAnItemStateChanged
        if (tabModeResepBAn.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, Item resep obat masih kosong...");
            tbPemberianResep1.requestFocus();
        } else if (cmbContengAn.getSelectedItem().equals("Semuanya")) {
            for (i = 0; i < tbItemResep2.getRowCount(); i++) {
                tbItemResep2.setValueAt(Boolean.TRUE, i, 0);
            }
            hitungItemResepDicontengAnti();
        } else if (cmbContengAn.getSelectedItem().equals(" ") || cmbContengAn.getSelectedItem().equals("Dibatalkan")) {
            for (i = 0; i < tbItemResep2.getRowCount(); i++) {
                tbItemResep2.setValueAt(Boolean.FALSE, i, 0);
            }
            LCount4.setText("0");
        }
    }//GEN-LAST:event_cmbContengAnItemStateChanged

    private void BtnCopyResepAnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCopyResepAnActionPerformed
        if (tabModeResepBAn.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, Item resep obat masih kosong...");
            tbPemberianResep1.requestFocus();
        } else if (akses.getadmin() == true) {
            JOptionPane.showMessageDialog(null, "Meskipun anda admin utama, tetaplah seorang dokter yang boleh meresepkan obat...");
        } else {
            try {
                noIdObatCopyAnti.setText("");
                j = 0;
                //cek conteng item obat
                for (i = 0; i < tbItemResep2.getRowCount(); i++) {
                    if (tbItemResep2.getValueAt(i, 0).toString().equals("true")) {
                        j++;
                    }
                }

                if (j == 0) {
                    JOptionPane.showMessageDialog(null, "Silahkan conteng dulu item resep obatnya.");
                    tbItemResep2.requestFocus();
                } else {
                    for (i = 0; i < tbItemResep2.getRowCount(); i++) {
                        if (tbItemResep2.getValueAt(i, 0).toString().equals("true")) {
                            if (chkRanapAn.isSelected() == true) {
                                Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(noId,6),signed)),0) from catatan_resep_ranap_antibiotik where "
                                    + "tgl_perawatan like '%" + Sequel.cariIsi("select year(now())") + "%' ",
                                    Sequel.cariIsi("select year(now())"), 6, noIdObatCopyAnti);

                                Sequel.menyimpan("catatan_resep_ranap_antibiotik", "'" + noIdObatCopyAnti.getText() + "','" + TNoRw.getText() + "', "
                                    + "'" + Sequel.cariIsi("select date(now())") + "', '" + Sequel.cariIsi("SELECT TIME(NOW())") + "',"
                                    + "'" + tbItemResep2.getValueAt(i, 4).toString() + "','BELUM','" + akses.getkode() + "',"
                                    + "'" + tbItemResep2.getValueAt(i, 7).toString() + "','" + tbItemResep2.getValueAt(i, 9).toString() + "',"
                                    + "'" + tbItemResep2.getValueAt(i, 10).toString() + "',"
                                    + "'" + tbItemResep2.getValueAt(i, 11).toString() + "'", "Copy Resep Antibiotik Sebelumnya");

                            } else if (chkRalanAn.isSelected() == true) {
                                Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(noId,6),signed)),0) from catatan_resep_antibiotik where "
                                    + "tgl_perawatan like '%" + Sequel.cariIsi("select year(now())") + "%' ",
                                    Sequel.cariIsi("select year(now())"), 6, noIdObatCopyAnti);

                                Sequel.menyimpan("catatan_resep_antibiotik", "'" + noIdObatCopyAnti.getText() + "','" + TNoRw.getText() + "', "
                                    + "'" + Sequel.cariIsi("select date(now())") + "', '" + Sequel.cariIsi("SELECT TIME(NOW())") + "',"
                                    + "'" + tbItemResep2.getValueAt(i, 4).toString() + "','BELUM','" + akses.getkode() + "',"
                                    + "'" + tbItemResep2.getValueAt(i, 10).toString() + "',"
                                    + "'" + tbItemResep2.getValueAt(i, 11).toString() + "'", "Copy Resep Antibiotik Sebelumnya");
                            }
                        }
                    }

                    DTPCariA.setDate(new Date());
                    DTPCariB.setDate(new Date());
                    WindowRiwayatResepAnti.dispose();
                    TResepObatAn.setText("");
                    Tket.setText("");
                    cmbHari.setSelectedIndex(0);
                    TResepObatAn.requestFocus();
                    tampilResepObatAnti();
                    JOptionPane.showMessageDialog(null, "Resep antibiotik sebelumnya pada tgl. " + Valid.SetTglINDONESIA(tglResepRiwayatAnti) + " berhasil tercopy,..");
                }

            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }
        }
    }//GEN-LAST:event_BtnCopyResepAnActionPerformed

    private void BtnCopyResepAnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCopyResepAnKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCopyResepAnActionPerformed(null);
        }
    }//GEN-LAST:event_BtnCopyResepAnKeyPressed

    private void BtnCloseIn9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn9ActionPerformed
        WindowRiwayatResepAnti.dispose();
        TResepObatAn.requestFocus();
    }//GEN-LAST:event_BtnCloseIn9ActionPerformed

    private void tbPemberianResep1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPemberianResep1MouseClicked
        tglResepRiwayatAnti = "";

        if(tabModeResepAAn.getRowCount()!=0){
            try {
                tampilItemResepRiwayatAnti(tbPemberianResep1.getValueAt(tbPemberianResep1.getSelectedRow(), 2).toString(), TNoRw.getText(),
                    tbPemberianResep1.getValueAt(tbPemberianResep1.getSelectedRow(), 3).toString());
                tglResepRiwayatAnti = tbPemberianResep1.getValueAt(tbPemberianResep1.getSelectedRow(), 2).toString();
                cmbContengAn.setSelectedIndex(0);
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbPemberianResep1MouseClicked

    private void tbPemberianResep1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPemberianResep1KeyPressed
        if (tabModeResepAAn.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    tampilItemResepRiwayatAnti(tbPemberianResep1.getValueAt(tbPemberianResep1.getSelectedRow(), 2).toString(), TNoRw.getText(),
                        tbPemberianResep1.getValueAt(tbPemberianResep1.getSelectedRow(), 3).toString());
                    cmbContengAn.setSelectedIndex(0);
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbPemberianResep1KeyPressed

    private void btnCariDokter1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariDokter1ActionPerformed
        pilihan = 0;
        pilihan = 2;
        akses.setform("DlgCatatanResepBiasaAntibiotik");
        dokter.emptTeks();
        dokter.isCek();
        dokter.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);
    }//GEN-LAST:event_btnCariDokter1ActionPerformed

    private void BtnSimpan3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan3ActionPerformed
        if (Sequel.cariRegistrasi(TNoRw.getText()) > 0) {
            JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi..");
        } else {
            if (TNoRw.getText().trim().equals("")) {
                Valid.textKosong(TNoRw, "No.Rawat");
            } else if (kddokterAn.getText().trim().equals("") || kddokterAn.getText().trim().equals("-") || kddokterAn.getText().trim().equals("--")) {
                Valid.textKosong(kddokterAn, "Dokter");
            } else {
                try {
                    for (i = 0; i < tbResepObat1.getRowCount(); i++) {
                        if (tbResepObat1.getValueAt(i, 0).toString().equals("true")) {
                            if (statusAnti.equals("IGD (Ralan)") || statusAnti.equals("IGD (Ranap)") || statusAnti.equals("ralan")) {
                                Sequel.mengedit("catatan_resep_antibiotik", "noId='" + tbResepObat1.getValueAt(i, 7).toString() + "'", "kd_dokter='" + kddokterAn.getText() + "'");
                            } else if (statusAnti.equals("ranap") || statusAnti.equals("vk bersalin")) {
                                Sequel.mengedit("catatan_resep_ranap_antibiotik", "noId='" + tbResepObat1.getValueAt(i, 7).toString() + "'", "kd_dokter='" + kddokterAn.getText() + "'");
                            }
                        }
                    }
                    tampilResepObatAnti();
                    WindowGantiDokterAnti.dispose();
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                }
            }
        }
    }//GEN-LAST:event_BtnSimpan3ActionPerformed

    private void BtnCloseIn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn2ActionPerformed
        WindowGantiDokterAnti.dispose();
    }//GEN-LAST:event_BtnCloseIn2ActionPerformed

    private void MnSemuanya1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSemuanya1ActionPerformed
        if (tabModeResepObatAn.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, Item resep obat masih kosong...");
            tbResepObat1.requestFocus();
        } else {
            tampilResepObatAnti();
            contengResepAnti();
        }
    }//GEN-LAST:event_MnSemuanya1ActionPerformed

    private void MnDibatalkan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDibatalkan1ActionPerformed
        if (tabModeResepObatAn.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, Item resep obat masih kosong...");
            tbResepObat1.requestFocus();
        } else {
            tampilResepObatAnti();

            for (i = 0; i < tbResepObat1.getRowCount(); i++) {
                if (tbResepObat1.getValueAt(i, 1).equals(TNoRw.getText())) {
                    tbResepObat1.setValueAt(Boolean.FALSE, i, 0);
                }
            }
        }
    }//GEN-LAST:event_MnDibatalkan1ActionPerformed

    private void MnDiCopy1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDiCopy1ActionPerformed
        if (tabModeResepObatAn.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, Item resep obat masih kosong...");
            tbResepObat1.requestFocus();
        } else {
            //cek conteng
            x = 0;
            for (i = 0; i < tbResepObat1.getRowCount(); i++) {
                if (tbResepObat1.getValueAt(i, 0).toString().equals("true")) {
                    x++;
                }
            }

            if (x == 0) {
                JOptionPane.showMessageDialog(null, "Silahkan conteng dulu resep yang dipilih utk. di copy..");
                tbResepObat1.requestFocus();
            } else {
                try {
                    for (i = 0; i < tbResepObat1.getRowCount(); i++) {
                        if (tbResepObat1.getValueAt(i, 0).toString().equals("true")) {
                            if (resepDipilihAnti.equals("")) {
                                resepDipilihAnti = tbResepObat1.getValueAt(i, 4).toString();
                            } else {
                                resepDipilihAnti = resepDipilihAnti + "\n" + tbResepObat1.getValueAt(i, 4).toString();
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                }

                akses.setCopyData(resepDipilihAnti);
                JOptionPane.showMessageDialog(null, "Resep yang dipilih berhasil di copy..");
                BtnKeluar1ActionPerformed(null);
            }
        }
    }//GEN-LAST:event_MnDiCopy1ActionPerformed

    private void MnGantiIniResep1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnGantiIniResep1ActionPerformed
        if (tabModeResepObatAn.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, Item resep obat masih kosong...");
            tbResepObat1.requestFocus();
        } else {
            //cek conteng
            x = 0;
            for (i = 0; i < tbResepObat1.getRowCount(); i++) {
                if (tbResepObat1.getValueAt(i, 0).toString().equals("true")) {
                    x++;
                }
            }

            if (x == 0) {
                JOptionPane.showMessageDialog(null, "Silahkan conteng dulu resep yang dipilih..");
                tbResepObat1.requestFocus();
            } else {
                try {
                    for (i = 0; i < tbResepObat1.getRowCount(); i++) {
                        if (tbResepObat1.getValueAt(i, 0).toString().equals("true")) {
                            Sequel.mengedit("catatan_resep_ranap_antibiotik", "noId='" + tbResepObat1.getValueAt(i, 7).toString() + "'",
                                "resep_untuk='" + cmbIniResepAn.getSelectedItem().toString() + "'");
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                }

                TResepObatAn.setText("");
                TResepObatAn.requestFocus();
                tampilResepObatAnti();
            }
        }
    }//GEN-LAST:event_MnGantiIniResep1ActionPerformed

    private void MnGantiDokter1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnGantiDokter1ActionPerformed
        if (tabModeResepObatAn.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, Item resep obat masih kosong...");
            tbResepObat1.requestFocus();
        } else {
            //cek conteng
            x = 0;
            for (i = 0; i < tbResepObat1.getRowCount(); i++) {
                if (tbResepObat1.getValueAt(i, 0).toString().equals("true")) {
                    x++;
                }
            }

            if (x == 0) {
                JOptionPane.showMessageDialog(null, "Silahkan conteng dulu resep yang dipilih utk. di ganti dokter peresepnya..");
                tbResepObat1.requestFocus();
            } else {
                WindowGantiDokterAnti.setSize(627, 118);
                WindowGantiDokterAnti.setLocationRelativeTo(internalFrame1);
                WindowGantiDokterAnti.setVisible(true);
                kddokterAn.setText("");
                TDokterAn.setText("");
                btnCariDokter1.requestFocus();
            }
        }
    }//GEN-LAST:event_MnGantiDokter1ActionPerformed

    private void MnRiwayatData1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRiwayatData1ActionPerformed
        Valid.SetTgl(DTPCari5, Valid.SetTgl(DTPCariA.getSelectedItem() + ""));
        DTPCari6.setDate(new Date());
        TCari3.setText(TNoRM.getText());
        BtnCari3ActionPerformed(null);
        WindowRiwayatAnti.setSize(985, internalFrame1.getHeight() - 40);
        WindowRiwayatAnti.setLocationRelativeTo(internalFrame1);
        WindowRiwayatAnti.setAlwaysOnTop(false);
        WindowRiwayatAnti.setVisible(true);
    }//GEN-LAST:event_MnRiwayatData1ActionPerformed

    private void MnCopyObat1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCopyObat1ActionPerformed
        if (tbItemObat1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan klik dulu salah satu data tgl. pemberian obatnya...");
            tbTglBeriObat1.requestFocus();
        } else {
            String resep;
            resep = "";
            if (tbTglBeriObat1.getSelectedRow() > -1) {
                try {
                    for (i = 0; i < tbItemObat1.getRowCount(); i++) {
                        if (resep.equals("")) {
                            resep = tbItemObat1.getValueAt(i, 2).toString() + " Jlh. "
                            + tbItemObat1.getValueAt(i, 3).toString();
                        } else {
                            resep = resep + "\n" + tbItemObat1.getValueAt(i, 2).toString() + " Jlh. "
                            + tbItemObat1.getValueAt(i, 3).toString();
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                }
                akses.setCopyData("Resep Tgl. " + tbTglBeriObat1.getValueAt(tbTglBeriObat1.getSelectedRow(), 1).toString() + " :\n" + resep);
            }
        }
    }//GEN-LAST:event_MnCopyObat1ActionPerformed

    private void BtnHapusNmObatAntiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusNmObatAntiActionPerformed
        TResepObatAn.setText("");
        TResepObatAn.requestFocus();
    }//GEN-LAST:event_BtnHapusNmObatAntiActionPerformed

    private void BtnHapusNmObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusNmObatActionPerformed
        TResepObat.setText("");
        TResepObat.requestFocus();
    }//GEN-LAST:event_BtnHapusNmObatActionPerformed

    private void tbItemResep2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbItemResep2KeyPressed
        if (tabModeResepBAn.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                hitungItemResepDicontengAnti();
            }
        }
    }//GEN-LAST:event_tbItemResep2KeyPressed

    private void tbItemResep2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbItemResep2MouseClicked
        if (tabModeResepBAn.getRowCount() != 0) {
            hitungItemResepDicontengAnti();
        }
    }//GEN-LAST:event_tbItemResep2MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgCatatanResepBiasaAntibiotik dialog = new DlgCatatanResepBiasaAntibiotik(new javax.swing.JFrame(), true);
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
    private widget.Button BtnAll1;
    private widget.Button BtnAll2;
    private widget.Button BtnCari2;
    private widget.Button BtnCari3;
    private widget.Button BtnCariObat;
    private widget.Button BtnCekObat;
    private widget.Button BtnCloseIn1;
    private widget.Button BtnCloseIn10;
    private widget.Button BtnCloseIn11;
    private widget.Button BtnCloseIn2;
    private widget.Button BtnCloseIn8;
    private widget.Button BtnCloseIn9;
    private widget.Button BtnCopyResep;
    private widget.Button BtnCopyResepAn;
    private widget.Button BtnCopyResepTerakhir;
    private widget.Button BtnCopyResepTerakhir1;
    private widget.Button BtnEdit;
    private widget.Button BtnEdit1;
    private widget.Button BtnHapus;
    private widget.Button BtnHapus1;
    private widget.Button BtnHapusNmObat;
    private widget.Button BtnHapusNmObatAnti;
    private widget.Button BtnKeluar;
    private widget.Button BtnKeluar1;
    private widget.Button BtnNotepad;
    private widget.Button BtnNotepad1;
    private widget.Button BtnPilih;
    private widget.Button BtnPrint;
    private widget.Button BtnPrint1;
    private widget.Button BtnResep;
    private widget.Button BtnRestor;
    private widget.Button BtnRestor1;
    private widget.Button BtnSetuju;
    private widget.Button BtnSetuju1;
    private widget.Button BtnSimpan;
    private widget.Button BtnSimpan1;
    private widget.Button BtnSimpan2;
    private widget.Button BtnSimpan3;
    public widget.CekBox ChkCito;
    public widget.CekBox ChkCitoAn;
    private widget.CekBox ChkInput1;
    private widget.CekBox ChkInputAn;
    private widget.CekBox ChkPoli1;
    private widget.CekBox ChkPoliAn;
    private widget.Tanggal DTPCari3;
    private widget.Tanggal DTPCari4;
    private widget.Tanggal DTPCari5;
    private widget.Tanggal DTPCari6;
    private widget.Tanggal DTPCariA;
    private widget.Tanggal DTPCariB;
    private widget.Label LCount;
    private widget.Label LCount1;
    private widget.Label LCount2;
    private widget.Label LCount3;
    private widget.Label LCount4;
    private javax.swing.JMenuItem MnCopyObat;
    private javax.swing.JMenuItem MnCopyObat1;
    private javax.swing.JMenuItem MnDiCopy;
    private javax.swing.JMenuItem MnDiCopy1;
    private javax.swing.JMenuItem MnDibatalkan;
    private javax.swing.JMenuItem MnDibatalkan1;
    private javax.swing.JMenuItem MnGantiDokter;
    private javax.swing.JMenuItem MnGantiDokter1;
    private javax.swing.JMenuItem MnGantiIniResep;
    private javax.swing.JMenuItem MnGantiIniResep1;
    private javax.swing.JMenuItem MnRiwayatData;
    private javax.swing.JMenuItem MnRiwayatData1;
    private javax.swing.JMenuItem MnSemuanya;
    private javax.swing.JMenuItem MnSemuanya1;
    private javax.swing.JPanel PanelInput1;
    private javax.swing.JPanel PanelInputAn;
    private javax.swing.JPanel PanelRiwayatObat;
    private javax.swing.JPanel PanelRiwayatObatAn;
    private widget.ScrollPane Scroll33;
    private widget.ScrollPane Scroll34;
    private widget.ScrollPane Scroll35;
    private widget.ScrollPane Scroll36;
    private widget.ScrollPane Scroll38;
    private widget.ScrollPane Scroll39;
    private widget.ScrollPane Scroll4;
    private widget.ScrollPane Scroll40;
    private widget.ScrollPane Scroll44;
    private widget.ScrollPane Scroll45;
    private widget.ScrollPane Scroll46;
    private widget.ScrollPane Scroll47;
    private widget.ScrollPane Scroll5;
    private widget.ScrollPane Scroll6;
    private widget.ScrollPane Scroll7;
    private widget.TextBox TCari;
    private widget.TextBox TCari2;
    private widget.TextBox TCari3;
    private widget.TextBox TCariObat;
    private widget.TextBox TDokter;
    private widget.TextBox TDokterAn;
    private widget.TextBox TIdObat;
    private widget.TextBox TIdObatAnti;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox TResepObat;
    private widget.TextBox TResepObatAn;
    private javax.swing.JTabbedPane TabResep;
    private widget.TextBox Tcara_byr;
    private widget.TextBox Tjk;
    private widget.TextBox Tket;
    private widget.TextBox TtglLahir;
    private javax.swing.JDialog WindowGantiDokter;
    private javax.swing.JDialog WindowGantiDokterAnti;
    private javax.swing.JDialog WindowRiwayat;
    private javax.swing.JDialog WindowRiwayatAnti;
    private javax.swing.JDialog WindowRiwayatResep;
    private javax.swing.JDialog WindowRiwayatResepAnti;
    private widget.Button btnCariDokter;
    private widget.Button btnCariDokter1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroupAnti;
    private widget.RadioButton chkRalan;
    private widget.RadioButton chkRalanAn;
    private widget.RadioButton chkRanap;
    private widget.RadioButton chkRanapAn;
    private widget.ComboBox cmbConteng;
    private widget.ComboBox cmbContengAn;
    private widget.ComboBox cmbHari;
    private widget.ComboBox cmbIniResep;
    private widget.ComboBox cmbIniResepAn;
    private widget.ComboBox cmbJnsResep;
    private widget.ComboBox cmbJnsResep1;
    private widget.ComboBox cmbObat;
    private widget.ComboBox cmbPilihCetak;
    private widget.ComboBox cmbPilihCetakAn;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame13;
    private widget.InternalFrame internalFrame14;
    private widget.InternalFrame internalFrame15;
    private widget.InternalFrame internalFrame16;
    private widget.InternalFrame internalFrame17;
    private widget.InternalFrame internalFrame18;
    private widget.InternalFrame internalFrame19;
    private widget.InternalFrame internalFrame20;
    private widget.InternalFrame internalFrame21;
    private widget.InternalFrame internalFrame22;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame4;
    private widget.Label jLabel10;
    private widget.Label jLabel105;
    private widget.Label jLabel106;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.Label jLabel30;
    private widget.Label jLabel31;
    private widget.Label jLabel32;
    private widget.Label jLabel33;
    private widget.Label jLabel34;
    private widget.Label jLabel35;
    private widget.Label jLabel36;
    private widget.Label jLabel37;
    private widget.Label jLabel5;
    private widget.Label jLabel53;
    private widget.Label jLabel54;
    private widget.Label jLabel55;
    private widget.Label jLabel56;
    private widget.Label jLabel57;
    private widget.Label jLabel58;
    private widget.Label jLabel59;
    private widget.Label jLabel60;
    private widget.Label jLabel61;
    private widget.Label jLabel62;
    private widget.Label jLabel64;
    private widget.Label jLabel68;
    private widget.Label jLabel69;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu1Anti;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JPopupMenu jPopupMenu2Anti;
    private javax.swing.JPopupMenu jPopupMenu3;
    private javax.swing.JPopupMenu jPopupMenu3Anti;
    private widget.TextBox kddokter;
    private widget.TextBox kddokterAn;
    private widget.TextBox noIdObat;
    private widget.TextBox noIdObatAnti;
    private widget.TextBox noIdObatCopy;
    private widget.TextBox noIdObatCopyAnti;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass12;
    private widget.panelisi panelGlass13;
    private widget.panelisi panelGlass16;
    private widget.panelisi panelGlass17;
    private widget.panelisi panelGlass18;
    private widget.panelisi panelGlass19;
    private widget.panelisi panelGlass20;
    private widget.panelisi panelGlass21;
    private widget.panelisi panelGlass6;
    private widget.panelisi panelGlass7;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.panelisi panelisi3;
    private widget.panelisi panelisi4;
    private widget.panelisi panelisi5;
    private widget.panelisi panelisi7;
    private widget.panelisi panelisi8;
    private widget.Table tbItemObat;
    private widget.Table tbItemObat1;
    private widget.Table tbItemResep;
    private widget.Table tbItemResep1;
    private widget.Table tbItemResep2;
    private widget.Table tbItemResepAnti;
    private widget.Table tbObat;
    private widget.Table tbPemberianResep;
    private widget.Table tbPemberianResep1;
    private widget.Table tbResepObat;
    private widget.Table tbResepObat1;
    private widget.Table tbRiwayat;
    private widget.Table tbRiwayat1;
    private widget.Table tbTglBeriObat;
    private widget.Table tbTglBeriObat1;
    // End of variables declaration//GEN-END:variables

    public void setData(String norw, String sttsrwt) {
        TNoRw.setText(norw);        
        ChkCito.setSelected(false);
        ChkCitoAn.setSelected(false);
        cmbJnsResep.setSelectedIndex(0);
        cmbJnsResep1.setSelectedIndex(0);
        cmbIniResep.setSelectedIndex(0);
        cmbIniResepAn.setSelectedIndex(0);
        Valid.tabelKosong(tabModeFarmasi);
        cmbObat.setSelectedIndex(0);
        TResepObat.setText("");
        TResepObatAn.setText("");
        TCariObat.setText("");
        TabResep.setSelectedIndex(0);
        
        if (sttsrwt.equals("IGD (Ralan)")) {
            status = "IGD (Ralan)";
            statusAnti = "IGD (Ralan)";
        } else if (sttsrwt.equals("IGD (Ranap)")) {
            status = "IGD (Ranap)";
            statusAnti = "IGD (Ranap)";
        } else if (sttsrwt.equals("ranap")) {
            status = "ranap";
            statusAnti = "ranap";
        } else if (sttsrwt.equals("vk bersalin")) {
            status = "vk bersalin";
            statusAnti = "vk bersalin";
        } else {
            status = "ralan";
            statusAnti = "ralan";
        }
        
        if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)") || status.equals("ralan")) {
            Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(noId,6),signed)),0) from catatan_resep where "
                    + "tgl_perawatan like '%" + Sequel.cariIsi("select year(now())") + "%' ",
                    Sequel.cariIsi("select year(now())"), 6, noIdObat);
        } else if (status.equals("ranap") || status.equals("vk bersalin")) {
            Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(noId,6),signed)),0) from catatan_resep_ranap where "
                    + "tgl_perawatan like '%" + Sequel.cariIsi("select year(now())") + "%' ",
                    Sequel.cariIsi("select year(now())"), 6, noIdObat);
        }
        
        if (statusAnti.equals("IGD (Ralan)") || statusAnti.equals("IGD (Ranap)") || statusAnti.equals("ralan")) {
            Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(noId,6),signed)),0) from catatan_resep_antibiotik where "
                    + "tgl_perawatan like '%" + Sequel.cariIsi("select year(now())") + "%' ",
                    Sequel.cariIsi("select year(now())"), 6, noIdObatAnti);
        } else if (statusAnti.equals("ranap") || statusAnti.equals("vk bersalin")) {
            Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(noId,6),signed)),0) from catatan_resep_ranap_antibiotik where "
                    + "tgl_perawatan like '%" + Sequel.cariIsi("select year(now())") + "%' ",
                    Sequel.cariIsi("select year(now())"), 6, noIdObatAnti);
        }
        
        try {
            ps2 = koneksi.prepareStatement("select rp.no_rawat, p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllhr, "
                    + "if(p.jk='L','Laki-laki','Perempuan') jk, rp.tgl_registrasi, pj.png_jawab, rp.status_lanjut, rp.kd_poli from reg_periksa rp "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis inner join penjab pj on pj.kd_pj=rp.kd_pj "
                    + "where rp.no_rawat='" + norw + "'");
            try {
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    TNoRM.setText(rs2.getString("no_rkm_medis"));
                    TPasien.setText(rs2.getString("nm_pasien"));
                    TtglLahir.setText(rs2.getString("tgllhr"));
                    Tjk.setText(rs2.getString("jk"));
                    Tcara_byr.setText(rs2.getString("png_jawab"));
                    jnsKunjungan = rs2.getString("status_lanjut");
                    kodepoli = rs2.getString("kd_poli");
                    kodepoliAnti = rs2.getString("kd_poli");

                    if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)") || status.equals("ralan")
                            || statusAnti.equals("IGD (Ralan)") || statusAnti.equals("IGD (Ranap)") || statusAnti.equals("ralan")) {
                        ChkCito.setEnabled(false);
                        ChkCitoAn.setEnabled(false);
                        cmbIniResep.setEnabled(false);
                        cmbIniResepAn.setEnabled(false);
                        cmbJnsResep.setEnabled(false);
                        cmbJnsResep1.setEnabled(false);
                        BtnSetuju.setEnabled(false);
                        BtnSetuju1.setEnabled(false);
                        
                        DTPCariA.setDate(rs2.getDate("tgl_registrasi"));
                        chkRanap.setSelected(false);
                        chkRanapAn.setSelected(false);
                        chkRalan.setSelected(true);
                        chkRalanAn.setSelected(true);
                    } else if (status.equals("ranap") || status.equals("vk bersalin")
                            || statusAnti.equals("ranap") || statusAnti.equals("vk bersalin")) {
                        ChkCito.setEnabled(true);
                        ChkCitoAn.setEnabled(true);
                        cmbIniResep.setEnabled(true);
                        cmbIniResepAn.setEnabled(true);
                        cmbJnsResep.setEnabled(true);
                        cmbJnsResep1.setEnabled(true);
                        BtnSetuju.setEnabled(true);
                        BtnSetuju1.setEnabled(true);
                        
                        DTPCariA.setDate(new Date());
                        chkRanap.setSelected(true);
                        chkRanapAn.setSelected(true);
                        chkRalan.setSelected(false);
                        chkRalanAn.setSelected(false);
                    }
                }
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
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void isCek() {
        BtnSimpan.setEnabled(akses.getresep_dokter());
        BtnSimpan2.setEnabled(akses.getresep_dokter());
        BtnHapus.setEnabled(akses.getresep_dokter());
        BtnHapus1.setEnabled(akses.getresep_dokter());
        BtnEdit.setEnabled(akses.getresep_dokter());
        BtnEdit1.setEnabled(akses.getresep_dokter());
        BtnCopyResepTerakhir.setEnabled(akses.getresep_dokter());
        BtnCopyResepTerakhir1.setEnabled(akses.getresep_dokter());
        MnRiwayatData.setEnabled(akses.getadmin());
        MnRiwayatData1.setEnabled(akses.getadmin());
    }

    private void tampilResepObat() {
        Valid.tabelKosong(tabModeResepObat);
        try {
            if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)") || status.equals("ralan")) {
                ps1 = koneksi.prepareStatement("select c.no_rawat, c.tgl_perawatan, c.jam_perawatan, c.nama_obat, "
                        + "c.status, d.nm_dokter, c.noID, c.kd_dokter, '-' jenis_resep, '-' resep_untuk from catatan_resep c "
                        + "inner join reg_periksa r on r.no_rawat = c.no_rawat "
                        + "inner join dokter d on d.kd_dokter = c.kd_dokter where "
                        + "c.tgl_perawatan between ? and ? and c.no_rawat = ? and c.nama_obat like ? order by c.noId");
                
            } else if (status.equals("ranap") || status.equals("vk bersalin")) {
                ps1 = koneksi.prepareStatement("select c.no_rawat, c.tgl_perawatan, c.jam_perawatan, c.nama_obat, "
                        + "c.status, d.nm_dokter, c.noID, c.kd_dokter, c.jenis_resep, c.resep_untuk from catatan_resep_ranap c "
                        + "inner join reg_periksa r on r.no_rawat = c.no_rawat "
                        + "inner join dokter d on d.kd_dokter = c.kd_dokter where "
                        + "c.tgl_perawatan between ? and ? and c.no_rawat = ? and c.nama_obat like ? "
                        + "order by c.noId");
            }
            try {
                ps1.setString(1, Valid.SetTgl(DTPCariA.getSelectedItem() + ""));
                ps1.setString(2, Valid.SetTgl(DTPCariB.getSelectedItem() + ""));
                ps1.setString(3, TNoRw.getText().trim());
                ps1.setString(4, "%" + TCari.getText().trim() + "%");
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    tabModeResepObat.addRow(new Object[]{
                        false,
                        rs1.getString(1),
                        rs1.getString(2),
                        rs1.getString(3),
                        rs1.getString(4),
                        rs1.getString(5),
                        rs1.getString(6),
                        rs1.getString(7),
                        rs1.getString(8),
                        rs1.getString(9),
                        rs1.getString(10)
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs1 != null) {
                    rs1.close();
                }
                if (ps1 != null) {
                    ps1.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabModeResepObat.getRowCount());
    }
    
    private void isFormRiwayatObat() {
        if (ChkInput1.isSelected() == true) {
            ChkInput1.setVisible(false);
            PanelInput1.setPreferredSize(new Dimension(WIDTH, 220));
            PanelRiwayatObat.setVisible(true);
            ChkInput1.setVisible(true);
            ChkPoli1.setSelected(true);
            
            if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)") || status.equals("ralan")) {
                if (ChkPoli1.isSelected() == true) {
                    ChkPoli1.setText("Hanya dipoli/inst. ini untuk 5 kunjungan terakhir");
                } else if (ChkPoli1.isSelected() == false) {
                    ChkPoli1.setText("Semua Poli/Inst. untuk 5 kunjungan terakhir");
                }
            } else if (status.equals("ranap") || status.equals("vk bersalin")) {
                if (ChkPoli1.isSelected() == true) {
                    ChkPoli1.setText("Semua resep ditampilkan selama perawatan saat ini");
                } else if (ChkPoli1.isSelected() == false) {
                    ChkPoli1.setText("Semua resep ditampilkan selama perawatan saat ini");                    
                }
            }
            tampilTglBeriObat();
        } else if (ChkInput1.isSelected() == false) {
            ChkInput1.setVisible(false);
            PanelInput1.setPreferredSize(new Dimension(WIDTH, 20));
            PanelRiwayatObat.setVisible(false);
            ChkInput1.setVisible(true);
            Valid.tabelKosong(tabModeTglBeriObat);
            Valid.tabelKosong(tabModeRiwItemObat);
        }
    }
    
    private void tampilFarmasi() {
        Valid.tabelKosong(tabModeFarmasi);
        try {
            psFar = koneksi.prepareStatement("SELECT db.nama_brng, db.kode_sat, sum(case when gd.kd_bangsal = 'APT01' then ifnull(format(gd.stok,0),0) END) apotek_igd, "
                    + "sum(case when gd.kd_bangsal = 'APT02' then ifnull(format(gd.stok,0),0) END) apotek_sentral, format(db.ralan,0) harga FROM gudangbarang gd "
                    + "INNER JOIN databarang db on db.kode_brng=gd.kode_brng where "
                    + "db.nama_brng<>'-' and db.nama_brng like ? and gd.kd_bangsal in ('APT01','APT02') and db.nama_brng not like '(FR)%' group by gd.kode_brng "
                    + "order by db.nama_brng");
            try {
                psFar.setString(1, "%" + TCariObat.getText().trim() + "%");
                rsFar = psFar.executeQuery();
                while (rsFar.next()) {
                    tabModeFarmasi.addRow(new String[]{
                        rsFar.getString("nama_brng"),
                        rsFar.getString("kode_sat"),
                        rsFar.getString("harga").replaceAll(",", "."),
                        rsFar.getString("apotek_igd"),
                        rsFar.getString("apotek_sentral")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsFar != null) {
                    rsFar.close();
                }
                if (psFar != null) {
                    psFar.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void getDataCatatanResep() {
        jenisResep = "";
        if (tbResepObat.getSelectedRow() != -1) {
            TNoRw.setText(tbResepObat.getValueAt(tbResepObat.getSelectedRow(), 1).toString());
            TNoRM.setText(Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat='" + TNoRw.getText() + "'"));
            TPasien.setText(Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
            TtglLahir.setText(Sequel.cariIsi("select date_format(tgl_lahir,'%d-%m-%Y') from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
            Tjk.setText(Sequel.cariIsi("select if(jk='L','Laki-laki','Perempuan') from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
            Tcara_byr.setText(Sequel.cariIsi("select pj.png_jawab from reg_periksa rp inner join penjab pj on pj.kd_pj=rp.kd_pj where rp.no_rawat='" + TNoRw.getText() + "'"));            
            TResepObat.setText(tbResepObat.getValueAt(tbResepObat.getSelectedRow(), 4).toString());            
            TIdObat.setText(tbResepObat.getValueAt(tbResepObat.getSelectedRow(), 7).toString());
            jenisResep = tbResepObat.getValueAt(tbResepObat.getSelectedRow(), 9).toString();
            
            if (jenisResep.equals("CITO")) {
                ChkCito.setSelected(true);
            } else if (jenisResep.equals("BIASA")) {
                ChkCito.setSelected(false);
            } else {
                ChkCito.setSelected(false);
            }
        }
    }
    
    private void getDataFarmasi() {
        if (tbObat.getSelectedRow() != -1) {
            if (TabResep.getSelectedIndex() == 0) {
                if (TResepObat.getText().equals("")) {
                    TResepObat.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString() + " ");
                } else {
                    TResepObat.setText(TResepObat.getText() + " " + tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString());
                }
                TResepObat.requestFocus();
            } else if (TabResep.getSelectedIndex() == 1) {
                if (TResepObatAn.getText().equals("")) {
                    TResepObatAn.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString() + " ");
                } else {
                    TResepObatAn.setText(TResepObatAn.getText() + " " + tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString());
                }
                TResepObatAn.requestFocus();
            }
        }        
    }
    
    private void tampilTglBeriObat() {
        Valid.tabelKosong(tabModeTglBeriObat);
        try {
            if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)") || status.equals("ralan") || status.equals("vk bersalin")) {
                if (ChkPoli1.isSelected() == true) {
                    psTglBO = koneksi.prepareStatement("select dpo.tgl_perawatan tglAsli, date_format(dpo.tgl_perawatan,'%d-%m-%Y') tanggal, "
                            + "count(dpo.kode_brng) jlhItem, pl.nm_poli, d.nm_dokter, dpo.jam from detail_pemberian_obat dpo "
                            + "inner join reg_periksa rp on rp.no_rawat=dpo.no_rawat "
                            + "inner join poliklinik pl on pl.kd_poli=rp.kd_poli "
                            + "INNER JOIN resep_obat ro ON ro.no_rawat = dpo.no_rawat AND ro.tgl_peresepan = dpo.tgl_perawatan AND ro.jam_peresepan = dpo.jam "
                            + "inner join dokter d on d.kd_dokter=rp.kd_dokter where "
                            + "rp.no_rkm_medis='" + TNoRM.getText() + "' and rp.kd_poli='" + kodepoli + "' and dpo.status='ralan' "
                            + "group by dpo.tgl_perawatan, dpo.jam order by dpo.tgl_perawatan desc, dpo.jam desc limit 5");
                } else if (ChkPoli1.isSelected() == false) {
                    psTglBO = koneksi.prepareStatement("select dpo.tgl_perawatan tglAsli, date_format(dpo.tgl_perawatan,'%d-%m-%Y') tanggal, "
                            + "count(dpo.kode_brng) jlhItem, pl.nm_poli, d.nm_dokter, dpo.jam from detail_pemberian_obat dpo "
                            + "inner join reg_periksa rp on rp.no_rawat=dpo.no_rawat "
                            + "inner join poliklinik pl on pl.kd_poli=rp.kd_poli "
                            + "INNER JOIN resep_obat ro ON ro.no_rawat = dpo.no_rawat AND ro.tgl_peresepan = dpo.tgl_perawatan AND ro.jam_peresepan = dpo.jam "
                            + "inner join dokter d on d.kd_dokter=rp.kd_dokter where "
                            + "rp.no_rkm_medis='" + TNoRM.getText() + "' and dpo.status='ralan' "
                            + "group by dpo.tgl_perawatan, dpo.jam order by dpo.tgl_perawatan desc, dpo.jam desc limit 5");
                }
            } else if (status.equals("ranap")) {
                psTglBO = koneksi.prepareStatement("SELECT dpo.tgl_perawatan tglAsli, date_format(dpo.tgl_perawatan,'%d-%m-%Y') tanggal, "
                        + "count(dpo.kode_brng) jlhItem, IF(dpo.STATUS='Ralan','IGD','R. Inap') nm_unit, d.nm_dokter, dpo.jam "
                        + "FROM detail_pemberian_obat dpo INNER JOIN reg_periksa rp ON rp.no_rawat = dpo.no_rawat "
                        + "INNER JOIN resep_obat ro ON ro.no_rawat = dpo.no_rawat AND ro.tgl_peresepan = dpo.tgl_perawatan AND ro.jam_peresepan = dpo.jam "
                        + "INNER JOIN dokter d ON d.kd_dokter = ro.kd_dokter WHERE dpo.no_rawat='" + TNoRw.getText() + "' "
                        + "GROUP BY dpo.tgl_perawatan, dpo.jam, dpo.STATUS, ro.kd_dokter ORDER BY dpo.tgl_perawatan, dpo.jam");
            }

            try {
                rsTglBO = psTglBO.executeQuery();
                while (rsTglBO.next()) {
                    if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)") || status.equals("ralan") || status.equals("vk bersalin")) {
                        tabModeTglBeriObat.addRow(new Object[]{
                            rsTglBO.getString("tglAsli"),
                            rsTglBO.getString("tanggal"),
                            rsTglBO.getString("jam"),
                            rsTglBO.getString("jlhItem"),
                            rsTglBO.getString("nm_poli"),
                            rsTglBO.getString("nm_dokter")                            
                        });
                    } else if (status.equals("ranap")) {
                        tabModeTglBeriObat.addRow(new Object[]{
                            rsTglBO.getString("tglAsli"),
                            rsTglBO.getString("tanggal"),
                            rsTglBO.getString("jam"),
                            rsTglBO.getString("jlhItem"),
                            rsTglBO.getString("nm_unit"),
                            rsTglBO.getString("nm_dokter")
                        });
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsTglBO != null) {
                    rsTglBO.close();
                }
                if (psTglBO != null) {
                    psTglBO.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilRiwItemObat() {
        Valid.tabelKosong(tabModeRiwItemObat);
        try {
            if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)") || status.equals("ralan") || status.equals("vk bersalin")) {
                if (ChkPoli1.isSelected() == true) {
                    psRiwIO = koneksi.prepareStatement("SELECT DATE_FORMAT(dpo.tgl_perawatan,'%d-%m-%Y') tanggal, db.nama_brng,"
                            + "CONCAT(dpo.jml ,' ',LOWER(db.kode_sat)) jlh FROM detail_pemberian_obat dpo "
                            + "INNER JOIN databarang db ON dpo.kode_brng=db.kode_brng "
                            + "INNER JOIN reg_periksa rp ON rp.no_rawat=dpo.no_rawat WHERE "
                            + "dpo.tgl_perawatan='" + tglPemberianObat + "' and dpo.jam='" + jamberiobat + "' and rp.no_rkm_medis='" + TNoRM.getText() + "' and "
                            + "rp.kd_poli='" + kodepoli + "' and dpo.status='ralan'");
                } else if (ChkPoli1.isSelected() == false) {
                    psRiwIO = koneksi.prepareStatement("SELECT DATE_FORMAT(dpo.tgl_perawatan,'%d-%m-%Y') tanggal, db.nama_brng,"
                            + "CONCAT(dpo.jml ,' ',LOWER(db.kode_sat)) jlh FROM detail_pemberian_obat dpo "
                            + "INNER JOIN databarang db ON dpo.kode_brng=db.kode_brng "
                            + "INNER JOIN reg_periksa rp ON rp.no_rawat=dpo.no_rawat WHERE "
                            + "dpo.tgl_perawatan='" + tglPemberianObat + "' and dpo.jam='" + jamberiobat + "'and rp.no_rkm_medis='" + TNoRM.getText() + "' "
                            + "and dpo.status='ralan'");
                }
            } else if (status.equals("ranap")) {
                if (ChkPoli1.isSelected() == true) {
                    psRiwIO = koneksi.prepareStatement("SELECT DATE_FORMAT(dpo.tgl_perawatan,'%d-%m-%Y') tanggal, db.nama_brng,"
                            + "CONCAT(dpo.jml,' ',LOWER(db.kode_sat)) jlh FROM detail_pemberian_obat dpo "
                            + "INNER JOIN databarang db ON dpo.kode_brng=db.kode_brng WHERE "
                            + "dpo.tgl_perawatan='" + tglPemberianObat + "' and dpo.jam='" + jamberiobat + "' and dpo.no_rawat='" + TNoRw.getText() + "'");
                } else if (ChkPoli1.isSelected() == false) {
                    psRiwIO = koneksi.prepareStatement("SELECT DATE_FORMAT(dpo.tgl_perawatan,'%d-%m-%Y') tanggal, db.nama_brng,"
                            + "CONCAT(dpo.jml,' ',LOWER(db.kode_sat)) jlh FROM detail_pemberian_obat dpo "
                            + "INNER JOIN databarang db ON dpo.kode_brng=db.kode_brng WHERE "
                            + "dpo.tgl_perawatan='" + tglPemberianObat + "' and dpo.jam='" + jamberiobat + "' and dpo.no_rawat='" + TNoRw.getText() + "'");
                }
            }
            
            try {
                rsRiwIO = psRiwIO.executeQuery();
                x = 1;
                while (rsRiwIO.next()) {
                    tabModeRiwItemObat.addRow(new Object[]{
                        x + ".",
                        rsRiwIO.getString("tanggal"),
                        rsRiwIO.getString("nama_brng"),
                        rsRiwIO.getString("jlh")
                    });
                    x++;
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsRiwIO != null) {
                    rsRiwIO.close();
                }
                if (psRiwIO != null) {
                    psRiwIO.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void getDataRiwObat() {
        tglPemberianObat = "";
        jamberiobat = "";
        if (tbTglBeriObat.getSelectedRow() != -1) {
            tglPemberianObat = tbTglBeriObat.getValueAt(tbTglBeriObat.getSelectedRow(), 0).toString();
            jamberiobat = tbTglBeriObat.getValueAt(tbTglBeriObat.getSelectedRow(), 2).toString();
            tampilRiwItemObat();
        }
    }
    
    private void getDataItemObat() {
        if (tbItemObat.getSelectedRow() != -1) {
            TResepObat.setText(tbItemObat.getValueAt(tbItemObat.getSelectedRow(), 2).toString());
            TResepObat.requestFocus();
        }
    }
    
    private void contengResep() {
        for (i = 0; i < tbResepObat.getRowCount(); i++) {
            if (tbResepObat.getValueAt(i, 1).equals(TNoRw.getText())) {
                tbResepObat.setValueAt(Boolean.TRUE, i, 0);
            }
        }
    }
    
    private void tampilItemResepRanap(String tglresep, String norw) {
        Valid.tabelKosong(tabModeResep2);
        try {
            psR2 = koneksi.prepareStatement("select c.no_rawat, c.tgl_perawatan, c.jam_perawatan, c.nama_obat, c.status, "
                    + "d.nm_dokter, c.noID, c.jenis_resep, c.resep_untuk FROM catatan_resep_ranap c "
                    + "INNER JOIN reg_periksa r ON r.no_rawat = c.no_rawat "
                    + "INNER JOIN dokter d ON d.kd_dokter = c.kd_dokter	WHERE "
                    + "r.status_lanjut='ranap' AND c.tgl_perawatan='" + tglresep + "' AND c.no_rawat = '" + norw + "' ORDER BY c.noId");
            try {
                rsR2 = psR2.executeQuery();
                while (rsR2.next()) {
                    tabModeResep2.addRow(new Object[]{
                        false,
                        rsR2.getString(1),
                        rsR2.getString(2),
                        rsR2.getString(3),
                        rsR2.getString(4),
                        rsR2.getString(5),
                        rsR2.getString(6),
                        rsR2.getString(7),
                        rsR2.getString(8),
                        rsR2.getString(9)
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsR2 != null) {
                    rsR2.close();
                }
                if (psR2 != null) {
                    psR2.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilItemResepRalan(String tglresep, String norm, String kdpoli) {
        Valid.tabelKosong(tabModeResep2);
        try {
            psR2 = koneksi.prepareStatement("select c.no_rawat, c.tgl_perawatan, c.jam_perawatan, c.nama_obat, c.status, d.nm_dokter, "
                    + "c.noID, '-' jenis_resep, '-' resep_untuk from catatan_resep c "
                    + "inner join reg_periksa r on r.no_rawat = c.no_rawat inner join dokter d on d.kd_dokter = c.kd_dokter where "
                    + "c.tgl_perawatan='" + tglresep + "' and r.no_rkm_medis='" + norm + "' and r.kd_poli='" + kdpoli + "' order by c.noId");
            try {
                rsR2 = psR2.executeQuery();
                while (rsR2.next()) {
                    tabModeResep2.addRow(new Object[]{
                        false,
                        rsR2.getString(1),
                        rsR2.getString(2),
                        rsR2.getString(3),
                        rsR2.getString(4),
                        rsR2.getString(5),
                        rsR2.getString(6),
                        rsR2.getString(7),
                        rsR2.getString(8),
                        rsR2.getString(9)
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsR2 != null) {
                    rsR2.close();
                }
                if (psR2 != null) {
                    psR2.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void copyResepnya() {
        if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)") || status.equals("ralan")) {
            try {
                j = 0;
                for (i = 0; i < tbItemResep.getRowCount(); i++) {
                    if (tbItemResep.getValueAt(i, 0).toString().equals("true")) {
                        j++;
                    }
                }

                for (i = 0; i < tbItemResep.getRowCount(); i++) {
                    if (tbItemResep.getValueAt(i, 0).toString().equals("true")) {
                        Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(noId,6),signed)),0) from catatan_resep where "
                                + "tgl_perawatan like '%" + Sequel.cariIsi("select year(now())") + "%' ",
                                Sequel.cariIsi("select year(now())"), 6, noIdObat);

                        Sequel.menyimpan("catatan_resep", "'" + noIdObat.getText() + "','" + TNoRw.getText() + "', "
                                + "'" + Sequel.cariIsi("select date(now())") + "',"
                                + "'" + Sequel.cariIsi("SELECT TIME(NOW())") + "',"
                                + "'" + tbItemResep.getValueAt(i, 4).toString() + "','BELUM','" + akses.getkode() + "'", "Copy Resep Sebelumnya");
                    }
                }

                Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + TNoRw.getText() + "' and status_lanjut='ralan'", DTPCariA);
                DTPCariB.setDate(new Date());
                TResepObat.setText("");
                TResepObat.requestFocus();
                tampilResepObat();
                Sequel.mengedit("reg_periksa", "no_rawat='" + TNoRw.getText() + "'", "stts='Sudah Diperiksa Dokter'");
                JOptionPane.showMessageDialog(null, "Resep sebelumnya pada tgl. " + Valid.SetTglINDONESIA(tglResep) + " berhasil tercopy,..");

            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }
            
        } else if (status.equals("ranap") || status.equals("vk bersalin")) {
            try {
                j = 0;
                for (i = 0; i < tbItemResep.getRowCount(); i++) {
                    if (tbItemResep.getValueAt(i, 0).toString().equals("true")) {
                        j++;
                    }
                }

                for (i = 0; i < tbItemResep.getRowCount(); i++) {
                    if (tbItemResep.getValueAt(i, 0).toString().equals("true")) {
                        Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(noId,6),signed)),0) from catatan_resep_ranap where "
                                + "tgl_perawatan like '%" + Sequel.cariIsi("select year(now())") + "%' ",
                                Sequel.cariIsi("select year(now())"), 6, noIdObat);

                        Sequel.menyimpan("catatan_resep_ranap", "'" + noIdObat.getText() + "','" + TNoRw.getText() + "', "
                                + "'" + Sequel.cariIsi("select date(now())") + "',"
                                + "'" + Sequel.cariIsi("SELECT TIME(NOW())") + "',"
                                + "'" + tbItemResep.getValueAt(i, 4).toString() + "','BELUM','" + akses.getkode() + "',"
                                + "'" + tbItemResep.getValueAt(i, 8).toString() + "',"
                                + "'" + tbItemResep.getValueAt(i, 9).toString() + "'", "Copy Resep Sebelumnya");
                    }
                }

                DTPCariA.setDate(new Date());
                DTPCariB.setDate(new Date());
                TResepObat.setText("");
                TResepObat.requestFocus();
                tampilResepObat();
                JOptionPane.showMessageDialog(null, "Resep sebelumnya pada tgl. " + Valid.SetTglINDONESIA(tglResep) + " berhasil tercopy,..");

            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }
        }
    }
    
    private void simpanHistoriResepRalan() {
        user = "";        
        if (akses.getadmin() == true) {
            user = "-";
        } else {
            user = akses.getkode();
        }

        try {
            for (i = 0; i < tbResepObat.getRowCount(); i++) {
                if (tbResepObat.getValueAt(i, 0).toString().equals("true") && tbResepObat.getValueAt(i, 5).toString().equals("BELUM")) {
                    Sequel.menyimpanPesanGagalnyaDiTerminal("catatan_resep_histori", "?,?,?,?,?,?,?,?,?,?", "Data", 10, new String[]{
                        tbResepObat.getValueAt(i, 7).toString(),
                        tbResepObat.getValueAt(i, 1).toString(),
                        tbResepObat.getValueAt(i, 2).toString(),
                        tbResepObat.getValueAt(i, 3).toString(),
                        tbResepObat.getValueAt(i, 4).toString(),
                        tbResepObat.getValueAt(i, 5).toString(),
                        tbResepObat.getValueAt(i, 8).toString(),
                        riwayatData,
                        user,
                        Sequel.cariIsi("select now()")
                    });

                    //jeda 1 detik
                    Thread.sleep(1000);

                    Sequel.queryu("delete from catatan_resep where no_rawat='" + tbResepObat.getValueAt(i, 1).toString()
                            + "' and tgl_perawatan='" + tbResepObat.getValueAt(i, 2).toString()
                            + "' and jam_perawatan='" + tbResepObat.getValueAt(i, 3).toString()
                            + "' and noId='" + tbResepObat.getValueAt(i, 7).toString() + "'");
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void simpanHistoriResepRanap() {
        user = "";        
        if (akses.getadmin() == true) {
            user = "-";
        } else {
            user = akses.getkode();
        }

        try {
            for (i = 0; i < tbResepObat.getRowCount(); i++) {
                if (tbResepObat.getValueAt(i, 0).toString().equals("true") && tbResepObat.getValueAt(i, 5).toString().equals("BELUM")) {
                    Sequel.menyimpanPesanGagalnyaDiTerminal("catatan_resep_ranap_histori", "?,?,?,?,?,?,?,?,?,?,?,?", "Data", 12, new String[]{
                        tbResepObat.getValueAt(i, 7).toString(),
                        tbResepObat.getValueAt(i, 1).toString(),
                        tbResepObat.getValueAt(i, 2).toString(),
                        tbResepObat.getValueAt(i, 3).toString(),
                        tbResepObat.getValueAt(i, 4).toString(),
                        tbResepObat.getValueAt(i, 5).toString(),
                        tbResepObat.getValueAt(i, 8).toString(),
                        riwayatData,
                        user,
                        Sequel.cariIsi("select now()"),
                        tbResepObat.getValueAt(i, 9).toString(),
                        tbResepObat.getValueAt(i, 10).toString()
                    });

                    //jeda 1 detik
                    Thread.sleep(1000);

                    Sequel.queryu("delete from catatan_resep_ranap where no_rawat='" + tbResepObat.getValueAt(i, 1).toString()
                            + "' and tgl_perawatan='" + tbResepObat.getValueAt(i, 2).toString()
                            + "' and jam_perawatan='" + tbResepObat.getValueAt(i, 3).toString()
                            + "' and noId='" + tbResepObat.getValueAt(i, 7).toString() + "'");
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilRiwayat() {
        Valid.tabelKosong(tabMode2);
        try {
            if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)") || status.equals("ralan")) {
                psrestor = koneksi.prepareStatement("SELECT IF(pg.nama = '-', 'Admin Utama', pg.nama ) pelaku, a.no_rawat, p.no_rkm_medis, p.nm_pasien, "
                        + "a.tgl_perawatan, a.jam_perawatan, a.waktu_eksekusi, upper(concat( 'DI', a.status_data )) sttsdata, a.noId, a.nama_obat "
                        + "FROM catatan_resep_histori a INNER JOIN reg_periksa rp ON rp.no_rawat = a.no_rawat "
                        + "INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis INNER JOIN pegawai pg ON pg.nik = a.nik_eksekutor WHERE "
                        + "a.tgl_perawatan between ? and ? and pg.nama like ? or "
                        + "a.tgl_perawatan between ? and ? and a.no_rawat like ? or "
                        + "a.tgl_perawatan between ? and ? and p.no_rkm_medis like ? or "
                        + "a.tgl_perawatan between ? and ? and a.status_data like ? or "
                        + "a.tgl_perawatan between ? and ? and p.nm_pasien like ? order by a.tgl_perawatan desc");
                
            } else if (status.equals("ranap") || status.equals("vk bersalin")) {
                psrestor = koneksi.prepareStatement("SELECT IF(pg.nama = '-', 'Admin Utama', pg.nama ) pelaku, a.no_rawat, p.no_rkm_medis, p.nm_pasien, "
                        + "a.tgl_perawatan, a.jam_perawatan, a.waktu_eksekusi, upper(concat( 'DI', a.status_data )) sttsdata, a.noId, a.nama_obat "
                        + "FROM catatan_resep_ranap_histori a INNER JOIN reg_periksa rp ON rp.no_rawat = a.no_rawat "
                        + "INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis INNER JOIN pegawai pg ON pg.nik = a.nik_eksekutor WHERE "
                        + "a.tgl_perawatan between ? and ? and pg.nama like ? or "
                        + "a.tgl_perawatan between ? and ? and a.no_rawat like ? or "
                        + "a.tgl_perawatan between ? and ? and p.no_rkm_medis like ? or "
                        + "a.tgl_perawatan between ? and ? and a.status_data like ? or "
                        + "a.tgl_perawatan between ? and ? and p.nm_pasien like ? order by a.tgl_perawatan desc");
            }            
            
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
                        rsrestor.getString("tgl_perawatan"),
                        rsrestor.getString("jam_perawatan"),
                        rsrestor.getString("waktu_eksekusi"),
                        rsrestor.getString("sttsdata"),                        
                        rsrestor.getString("noId"),
                        rsrestor.getString("nama_obat")
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
            if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)") || status.equals("ralan")) {
                ps3 = koneksi.prepareStatement("select * from catatan_resep_histori where "
                        + "no_rawat='" + tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 1).toString() + "'");
            } else if (status.equals("ranap") || status.equals("vk bersalin")) {
                ps3 = koneksi.prepareStatement("select * from catatan_resep_ranap_histori where "
                        + "no_rawat='" + tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 1).toString() + "'");
            }
            try {
                rs3 = ps3.executeQuery();
                while (rs3.next()) {
                    if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)") || status.equals("ralan")) {
                        try {
                            Sequel.menyimpanIgnore("catatan_resep",
                                    "'" + rs3.getString("noId") + "',"
                                    + "'" + TNoRw.getText() + "',"
                                    + "'" + rs3.getString("tgl_perawatan") + "',"
                                    + "'" + rs3.getString("jam_perawatan") + "',"
                                    + "'" + rs3.getString("nama_obat") + "',"
                                    + "'" + rs3.getString("status") + "',"
                                    + "'" + rs3.getString("kd_dokter") + "'", "Catatan Resep Rawat Jalan");
                        } catch (Exception e) {
                            System.out.println("Simpan : " + e);
                        }
                        
                    } else if (status.equals("ranap") || status.equals("vk bersalin")) {
                        try {
                            Sequel.menyimpanIgnore("catatan_resep_ranap",
                                    "'" + rs3.getString("noId") + "',"
                                    + "'" + TNoRw.getText() + "',"
                                    + "'" + rs3.getString("tgl_perawatan") + "',"
                                    + "'" + rs3.getString("jam_perawatan") + "',"
                                    + "'" + rs3.getString("nama_obat") + "',"
                                    + "'" + rs3.getString("status") + "',"
                                    + "'" + rs3.getString("kd_dokter") + "',"
                                    + "'" + rs3.getString("jenis_resep") + "',"
                                    + "'" + rs3.getString("resep_untuk") + "'", "Catatan Resep Rawat Inap");
                        } catch (Exception e) {
                            System.out.println("Simpan : " + e);
                        }
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
    
    private void tampilTglResep() {
        Valid.tabelKosong(tabModeResepA);
        try {
            if (chkRanap.isSelected() == true) {
                psR11 = koneksi.prepareStatement("select c.*, date_format(c.tgl_perawatan,'%d-%m-%Y') tglnya, d.nm_dokter "
                        + "from catatan_resep_ranap c inner join dokter d on d.kd_dokter = c.kd_dokter where c.no_rawat='" + TNoRw.getText() + "' "
                        + "GROUP BY c.tgl_perawatan, c.kd_dokter order by c.tgl_perawatan DESC LIMIT 100");
            } else if (chkRalan.isSelected() == true) {
                psR11 = koneksi.prepareStatement("select c.*, date_format(c.tgl_perawatan,'%d-%m-%Y') tglnya, d.nm_dokter "
                        + "from catatan_resep c inner join dokter d on d.kd_dokter = c.kd_dokter where c.no_rawat='" + TNoRw.getText() + "' "
                        + "GROUP BY c.tgl_perawatan, c.kd_dokter order by c.tgl_perawatan DESC LIMIT 100");
            }
            try {
                rsR11 = psR11.executeQuery();
                while (rsR11.next()) {
                    tabModeResepA.addRow(new String[]{
                        rsR11.getString("tglnya"),
                        rsR11.getString("nm_dokter"),
                        rsR11.getString("tgl_perawatan"),
                        rsR11.getString("kd_dokter")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsR11 != null) {
                    rsR11.close();
                }
                if (psR11 != null) {
                    psR11.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilItemResepRiwayat(String tglresep, String norawat, String kddokter) {
        Valid.tabelKosong(tabModeResepB);
        try {
            if (chkRanap.isSelected() == true) {
                psR22 = koneksi.prepareStatement("select *, date_format(tgl_perawatan,'%d-%m-%Y') tgl from catatan_resep_ranap where "
                        + "tgl_perawatan='" + tglresep + "' and no_rawat='" + norawat + "' and kd_dokter='" + kddokter + "' order by noId");
            } else if (chkRalan.isSelected() == true) {
                psR22 = koneksi.prepareStatement("select *, date_format(tgl_perawatan,'%d-%m-%Y') tgl, 'BIASA' jenis_resep, '-' resep_untuk "
                        + "from catatan_resep where tgl_perawatan='" + tglresep + "' and no_rawat='" + norawat + "' "
                        + "and kd_dokter='" + kddokter + "' order by noId");
            }
            try {
                rsR22 = psR22.executeQuery();
                while (rsR22.next()) {
                    tabModeResepB.addRow(new Object[]{
                        false,
                        rsR22.getString("no_rawat"),
                        rsR22.getString("tgl"),
                        rsR22.getString("jam_perawatan"),
                        rsR22.getString("nama_obat"),
                        rsR22.getString("status"),
                        rsR22.getString("noId"), 
                        rsR22.getString("jenis_resep"),
                        rsR22.getString("tgl_perawatan"),
                        rsR22.getString("resep_untuk")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsR22 != null) {
                    rsR22.close();
                }
                if (psR22 != null) {
                    psR22.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount2.setText("" + tabModeResepB.getRowCount());
    }
    
    private void tampilResepObatAnti() {
        Valid.tabelKosong(tabModeResepObatAn);
        try {
            if (statusAnti.equals("IGD (Ralan)") || statusAnti.equals("IGD (Ranap)") || statusAnti.equals("ralan")) {
                ps1Anti = koneksi.prepareStatement("select c.no_rawat, c.tgl_perawatan, c.jam_perawatan, c.nama_obat, "
                        + "c.status, d.nm_dokter, c.noID, c.kd_dokter, '-' jenis_resep, '-' resep_untuk, c.keterangan, c.hari_ke from catatan_resep_antibiotik c "
                        + "inner join reg_periksa r on r.no_rawat = c.no_rawat "
                        + "inner join dokter d on d.kd_dokter = c.kd_dokter where "
                        + "c.tgl_perawatan between ? and ? and c.no_rawat = ? and c.nama_obat like ? order by c.noId");
                
            } else if (statusAnti.equals("ranap") || statusAnti.equals("vk bersalin")) {
                ps1Anti = koneksi.prepareStatement("select c.no_rawat, c.tgl_perawatan, c.jam_perawatan, c.nama_obat, "
                        + "c.status, d.nm_dokter, c.noID, c.kd_dokter, c.jenis_resep, c.resep_untuk, c.keterangan, c.hari_ke from catatan_resep_ranap_antibiotik c "
                        + "inner join reg_periksa r on r.no_rawat = c.no_rawat "
                        + "inner join dokter d on d.kd_dokter = c.kd_dokter where "
                        + "c.tgl_perawatan between ? and ? and c.no_rawat = ? and c.nama_obat like ? "
                        + "order by c.noId");
            }
            try {
                ps1Anti.setString(1, Valid.SetTgl(DTPCariA.getSelectedItem() + ""));
                ps1Anti.setString(2, Valid.SetTgl(DTPCariB.getSelectedItem() + ""));
                ps1Anti.setString(3, TNoRw.getText().trim());
                ps1Anti.setString(4, "%" + TCari.getText().trim() + "%");
                rs1Anti = ps1Anti.executeQuery();
                while (rs1Anti.next()) {
                    tabModeResepObatAn.addRow(new Object[]{
                        false,
                        rs1Anti.getString(1),
                        rs1Anti.getString(2),
                        rs1Anti.getString(3),
                        rs1Anti.getString(4),
                        rs1Anti.getString(5),
                        rs1Anti.getString(6),
                        rs1Anti.getString(7),
                        rs1Anti.getString(8),
                        rs1Anti.getString(9),
                        rs1Anti.getString(10),
                        rs1Anti.getString(11),
                        rs1Anti.getString(12)
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs1Anti != null) {
                    rs1Anti.close();
                }
                if (ps1Anti != null) {
                    ps1Anti.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabModeResepObatAn.getRowCount());
    }
    
    private void isFormRiwayatObatAnti() {
        if (ChkInputAn.isSelected() == true) {
            ChkInputAn.setVisible(false);
            PanelInputAn.setPreferredSize(new Dimension(WIDTH, 220));
            PanelRiwayatObatAn.setVisible(true);
            ChkInputAn.setVisible(true);
            ChkPoliAn.setSelected(true);
            
            if (statusAnti.equals("IGD (Ralan)") || statusAnti.equals("IGD (Ranap)") || statusAnti.equals("ralan")) {
                if (ChkPoliAn.isSelected() == true) {
                    ChkPoliAn.setText("Hanya dipoli/inst. ini untuk 5 kunjungan terakhir");
                } else if (ChkPoliAn.isSelected() == false) {
                    ChkPoliAn.setText("Semua Poli/Inst. untuk 5 kunjungan terakhir");
                }
            } else if (statusAnti.equals("ranap") || statusAnti.equals("vk bersalin")) {
                if (ChkPoliAn.isSelected() == true) {
                    ChkPoliAn.setText("Semua resep ditampilkan selama perawatan saat ini");
                } else if (ChkPoliAn.isSelected() == false) {
                    ChkPoliAn.setText("Semua resep ditampilkan selama perawatan saat ini");                    
                }
            }
            tampilTglBeriObatAnti();
        } else if (ChkInputAn.isSelected() == false) {
            ChkInputAn.setVisible(false);
            PanelInputAn.setPreferredSize(new Dimension(WIDTH, 20));
            PanelRiwayatObatAn.setVisible(false);
            ChkInputAn.setVisible(true);
            Valid.tabelKosong(tabModeTglBeriObatAn);
            Valid.tabelKosong(tabModeRiwItemObatAn);
        }
    }
    
    private void tampilTglBeriObatAnti() {
        Valid.tabelKosong(tabModeTglBeriObatAn);
        try {
            if (statusAnti.equals("IGD (Ralan)") || statusAnti.equals("IGD (Ranap)") || statusAnti.equals("ralan") || statusAnti.equals("vk bersalin")) {
                if (ChkPoliAn.isSelected() == true) {
                    psTglBOAnti = koneksi.prepareStatement("select dpo.tgl_perawatan tglAsli, date_format(dpo.tgl_perawatan,'%d-%m-%Y') tanggal, "
                            + "count(dpo.kode_brng) jlhItem, pl.nm_poli, d.nm_dokter, dpo.jam from detail_pemberian_obat dpo "
                            + "inner join reg_periksa rp on rp.no_rawat=dpo.no_rawat "
                            + "inner join poliklinik pl on pl.kd_poli=rp.kd_poli "
                            + "INNER JOIN resep_obat ro ON ro.no_rawat = dpo.no_rawat AND ro.tgl_peresepan = dpo.tgl_perawatan AND ro.jam_peresepan = dpo.jam "
                            + "inner join dokter d on d.kd_dokter=rp.kd_dokter where "
                            + "rp.no_rkm_medis='" + TNoRM.getText() + "' and rp.kd_poli='" + kodepoli + "' and dpo.status='ralan' "
                            + "group by dpo.tgl_perawatan, dpo.jam order by dpo.tgl_perawatan desc, dpo.jam desc limit 5");
                } else if (ChkPoliAn.isSelected() == false) {
                    psTglBOAnti = koneksi.prepareStatement("select dpo.tgl_perawatan tglAsli, date_format(dpo.tgl_perawatan,'%d-%m-%Y') tanggal, "
                            + "count(dpo.kode_brng) jlhItem, pl.nm_poli, d.nm_dokter, dpo.jam from detail_pemberian_obat dpo "
                            + "inner join reg_periksa rp on rp.no_rawat=dpo.no_rawat "
                            + "inner join poliklinik pl on pl.kd_poli=rp.kd_poli "
                            + "INNER JOIN resep_obat ro ON ro.no_rawat = dpo.no_rawat AND ro.tgl_peresepan = dpo.tgl_perawatan AND ro.jam_peresepan = dpo.jam "
                            + "inner join dokter d on d.kd_dokter=rp.kd_dokter where "
                            + "rp.no_rkm_medis='" + TNoRM.getText() + "' and dpo.status='ralan' "
                            + "group by dpo.tgl_perawatan, dpo.jam order by dpo.tgl_perawatan desc, dpo.jam desc limit 5");
                }
            } else if (statusAnti.equals("ranap")) {
                psTglBOAnti = koneksi.prepareStatement("SELECT dpo.tgl_perawatan tglAsli, date_format(dpo.tgl_perawatan,'%d-%m-%Y') tanggal, "
                        + "count(dpo.kode_brng) jlhItem, IF(dpo.STATUS='Ralan','IGD','R. Inap') nm_unit, d.nm_dokter, dpo.jam "
                        + "FROM detail_pemberian_obat dpo INNER JOIN reg_periksa rp ON rp.no_rawat = dpo.no_rawat "
                        + "INNER JOIN resep_obat ro ON ro.no_rawat = dpo.no_rawat AND ro.tgl_peresepan = dpo.tgl_perawatan AND ro.jam_peresepan = dpo.jam "
                        + "INNER JOIN dokter d ON d.kd_dokter = ro.kd_dokter WHERE dpo.no_rawat='" + TNoRw.getText() + "' "
                        + "GROUP BY dpo.tgl_perawatan, dpo.jam, dpo.STATUS, ro.kd_dokter ORDER BY dpo.tgl_perawatan, dpo.jam");
            }

            try {
                rsTglBOAnti = psTglBOAnti.executeQuery();
                while (rsTglBOAnti.next()) {
                    if (statusAnti.equals("IGD (Ralan)") || statusAnti.equals("IGD (Ranap)") || statusAnti.equals("ralan") || statusAnti.equals("vk bersalin")) {
                        tabModeTglBeriObatAn.addRow(new Object[]{
                            rsTglBOAnti.getString("tglAsli"),
                            rsTglBOAnti.getString("tanggal"),
                            rsTglBOAnti.getString("jam"),
                            rsTglBOAnti.getString("jlhItem"),
                            rsTglBOAnti.getString("nm_poli"),
                            rsTglBOAnti.getString("nm_dokter")                            
                        });
                    } else if (statusAnti.equals("ranap")) {
                        tabModeTglBeriObatAn.addRow(new Object[]{
                            rsTglBOAnti.getString("tglAsli"),
                            rsTglBOAnti.getString("tanggal"),
                            rsTglBOAnti.getString("jam"),
                            rsTglBOAnti.getString("jlhItem"),
                            rsTglBOAnti.getString("nm_unit"),
                            rsTglBOAnti.getString("nm_dokter")
                        });
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsTglBOAnti != null) {
                    rsTglBOAnti.close();
                }
                if (psTglBOAnti != null) {
                    psTglBOAnti.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void getDataCatatanResepAnti() {
        jenisResepAnti = "";
        if (tbResepObat1.getSelectedRow() != -1) {
            TNoRw.setText(tbResepObat1.getValueAt(tbResepObat1.getSelectedRow(), 1).toString());
            TNoRM.setText(Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat='" + TNoRw.getText() + "'"));
            TPasien.setText(Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
            TtglLahir.setText(Sequel.cariIsi("select date_format(tgl_lahir,'%d-%m-%Y') from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
            Tjk.setText(Sequel.cariIsi("select if(jk='L','Laki-laki','Perempuan') from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
            Tcara_byr.setText(Sequel.cariIsi("select pj.png_jawab from reg_periksa rp inner join penjab pj on pj.kd_pj=rp.kd_pj where rp.no_rawat='" + TNoRw.getText() + "'"));            
            TResepObatAn.setText(tbResepObat1.getValueAt(tbResepObat1.getSelectedRow(), 4).toString());            
            TIdObatAnti.setText(tbResepObat1.getValueAt(tbResepObat1.getSelectedRow(), 7).toString());
            jenisResepAnti = tbResepObat1.getValueAt(tbResepObat1.getSelectedRow(), 9).toString();
            Tket.setText(tbResepObat1.getValueAt(tbResepObat1.getSelectedRow(), 11).toString());
            cmbHari.setSelectedItem(tbResepObat1.getValueAt(tbResepObat1.getSelectedRow(), 12).toString());
            
            if (jenisResepAnti.equals("CITO")) {
                ChkCitoAn.setSelected(true);
            } else if (jenisResepAnti.equals("BIASA")) {
                ChkCitoAn.setSelected(false);
            } else {
                ChkCitoAn.setSelected(false);
            }
        }
    }
    
    private void getDataRiwObatAnti() {
        tglPemberianObatAnti = "";
        jamberiobatAnti = "";
        if (tbTglBeriObat1.getSelectedRow() != -1) {
            tglPemberianObatAnti = tbTglBeriObat1.getValueAt(tbTglBeriObat1.getSelectedRow(), 0).toString();
            jamberiobatAnti = tbTglBeriObat1.getValueAt(tbTglBeriObat1.getSelectedRow(), 2).toString();
            tampilRiwItemObatAnti();
        }
    }
    
    private void tampilRiwItemObatAnti() {
        Valid.tabelKosong(tabModeRiwItemObatAn);
        try {
            if (statusAnti.equals("IGD (Ralan)") || statusAnti.equals("IGD (Ranap)") || statusAnti.equals("ralan") || statusAnti.equals("vk bersalin")) {
                if (ChkPoliAn.isSelected() == true) {
                    psRiwIOAnti = koneksi.prepareStatement("SELECT DATE_FORMAT(dpo.tgl_perawatan,'%d-%m-%Y') tanggal, db.nama_brng,"
                            + "CONCAT(dpo.jml ,' ',LOWER(db.kode_sat)) jlh FROM detail_pemberian_obat dpo "
                            + "INNER JOIN databarang db ON dpo.kode_brng=db.kode_brng "
                            + "INNER JOIN reg_periksa rp ON rp.no_rawat=dpo.no_rawat WHERE "
                            + "dpo.tgl_perawatan='" + tglPemberianObatAnti + "' and dpo.jam='" + jamberiobatAnti + "' and rp.no_rkm_medis='" + TNoRM.getText() + "' and "
                            + "rp.kd_poli='" + kodepoliAnti + "' and dpo.status='ralan'");
                } else if (ChkPoliAn.isSelected() == false) {
                    psRiwIOAnti = koneksi.prepareStatement("SELECT DATE_FORMAT(dpo.tgl_perawatan,'%d-%m-%Y') tanggal, db.nama_brng,"
                            + "CONCAT(dpo.jml ,' ',LOWER(db.kode_sat)) jlh FROM detail_pemberian_obat dpo "
                            + "INNER JOIN databarang db ON dpo.kode_brng=db.kode_brng "
                            + "INNER JOIN reg_periksa rp ON rp.no_rawat=dpo.no_rawat WHERE "
                            + "dpo.tgl_perawatan='" + tglPemberianObatAnti + "' and dpo.jam='" + jamberiobatAnti + "'and rp.no_rkm_medis='" + TNoRM.getText() + "' "
                            + "and dpo.status='ralan'");
                }
            } else if (statusAnti.equals("ranap")) {
                if (ChkPoliAn.isSelected() == true) {
                    psRiwIOAnti = koneksi.prepareStatement("SELECT DATE_FORMAT(dpo.tgl_perawatan,'%d-%m-%Y') tanggal, db.nama_brng,"
                            + "CONCAT(dpo.jml,' ',LOWER(db.kode_sat)) jlh FROM detail_pemberian_obat dpo "
                            + "INNER JOIN databarang db ON dpo.kode_brng=db.kode_brng WHERE "
                            + "dpo.tgl_perawatan='" + tglPemberianObatAnti + "' and dpo.jam='" + jamberiobatAnti + "' and dpo.no_rawat='" + TNoRw.getText() + "'");
                } else if (ChkPoliAn.isSelected() == false) {
                    psRiwIOAnti = koneksi.prepareStatement("SELECT DATE_FORMAT(dpo.tgl_perawatan,'%d-%m-%Y') tanggal, db.nama_brng,"
                            + "CONCAT(dpo.jml,' ',LOWER(db.kode_sat)) jlh FROM detail_pemberian_obat dpo "
                            + "INNER JOIN databarang db ON dpo.kode_brng=db.kode_brng WHERE "
                            + "dpo.tgl_perawatan='" + tglPemberianObatAnti + "' and dpo.jam='" + jamberiobatAnti + "' and dpo.no_rawat='" + TNoRw.getText() + "'");
                }
            }
            
            try {
                rsRiwIOAnti = psRiwIOAnti.executeQuery();
                x = 1;
                while (rsRiwIOAnti.next()) {
                    tabModeRiwItemObatAn.addRow(new Object[]{
                        x + ".",
                        rsRiwIOAnti.getString("tanggal"),
                        rsRiwIOAnti.getString("nama_brng"),
                        rsRiwIOAnti.getString("jlh")
                    });
                    x++;
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsRiwIOAnti != null) {
                    rsRiwIOAnti.close();
                }
                if (psRiwIOAnti != null) {
                    psRiwIOAnti.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void getDataItemObatAnti() {
        if (tbItemObat1.getSelectedRow() != -1) {
            TResepObatAn.setText(tbItemObat1.getValueAt(tbItemObat1.getSelectedRow(), 2).toString());
            TResepObatAn.requestFocus();
        }
    }
    
    private void simpanHistoriResepRanapAnti() {
        userAnti = "";        
        if (akses.getadmin() == true) {
            userAnti = "-";
        } else {
            userAnti = akses.getkode();
        }

        try {
            for (i = 0; i < tbResepObat1.getRowCount(); i++) {
                if (tbResepObat1.getValueAt(i, 0).toString().equals("true") && tbResepObat1.getValueAt(i, 5).toString().equals("BELUM")) {
                    Sequel.menyimpanPesanGagalnyaDiTerminal("catatan_resep_ranap_antibiotik_histori", "?,?,?,?,?,?,?,?,?,?,?,?,?,?", "Data", 14, new String[]{
                        tbResepObat1.getValueAt(i, 7).toString(),
                        tbResepObat1.getValueAt(i, 1).toString(),
                        tbResepObat1.getValueAt(i, 2).toString(),
                        tbResepObat1.getValueAt(i, 3).toString(),
                        tbResepObat1.getValueAt(i, 4).toString(),
                        tbResepObat1.getValueAt(i, 5).toString(),
                        tbResepObat1.getValueAt(i, 8).toString(),
                        tbResepObat1.getValueAt(i, 11).toString(),
                        tbResepObat1.getValueAt(i, 12).toString(),
                        riwayatDataAnti,
                        userAnti,
                        Sequel.cariIsi("select now()"),
                        tbResepObat1.getValueAt(i, 9).toString(),
                        tbResepObat1.getValueAt(i, 10).toString()
                    });

                    //jeda 1 detik
                    Thread.sleep(1000);

                    Sequel.queryu("delete from catatan_resep_ranap_antibiotik where no_rawat='" + tbResepObat1.getValueAt(i, 1).toString()
                            + "' and tgl_perawatan='" + tbResepObat1.getValueAt(i, 2).toString()
                            + "' and jam_perawatan='" + tbResepObat1.getValueAt(i, 3).toString()
                            + "' and noId='" + tbResepObat1.getValueAt(i, 7).toString() + "'");
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void simpanHistoriResepRalanAnti() {
        userAnti = "";        
        if (akses.getadmin() == true) {
            userAnti = "-";
        } else {
            userAnti = akses.getkode();
        }

        try {
            for (i = 0; i < tbResepObat1.getRowCount(); i++) {
                if (tbResepObat1.getValueAt(i, 0).toString().equals("true") && tbResepObat1.getValueAt(i, 5).toString().equals("BELUM")) {
                    Sequel.menyimpanPesanGagalnyaDiTerminal("catatan_resep_antibiotik_histori", "?,?,?,?,?,?,?,?,?,?,?,?", "Data", 12, new String[]{
                        tbResepObat1.getValueAt(i, 7).toString(),
                        tbResepObat1.getValueAt(i, 1).toString(),
                        tbResepObat1.getValueAt(i, 2).toString(),
                        tbResepObat1.getValueAt(i, 3).toString(),
                        tbResepObat1.getValueAt(i, 4).toString(),
                        tbResepObat1.getValueAt(i, 5).toString(),
                        tbResepObat1.getValueAt(i, 8).toString(),
                        tbResepObat1.getValueAt(i, 11).toString(),
                        tbResepObat1.getValueAt(i, 12).toString(),
                        riwayatDataAnti,
                        userAnti,
                        Sequel.cariIsi("select now()")
                    });

                    //jeda 1 detik
                    Thread.sleep(1000);

                    Sequel.queryu("delete from catatan_resep_antibiotik where no_rawat='" + tbResepObat1.getValueAt(i, 1).toString()
                            + "' and tgl_perawatan='" + tbResepObat1.getValueAt(i, 2).toString()
                            + "' and jam_perawatan='" + tbResepObat1.getValueAt(i, 3).toString()
                            + "' and noId='" + tbResepObat1.getValueAt(i, 7).toString() + "'");
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilRiwayatAnti() {
        Valid.tabelKosong(tabMode2An);
        try {
            if (statusAnti.equals("IGD (Ralan)") || statusAnti.equals("IGD (Ranap)") || statusAnti.equals("ralan")) {
                psrestorAnti = koneksi.prepareStatement("SELECT IF(pg.nama = '-', 'Admin Utama', pg.nama ) pelaku, a.no_rawat, p.no_rkm_medis, p.nm_pasien, "
                        + "a.tgl_perawatan, a.jam_perawatan, a.waktu_eksekusi, upper(concat( 'DI', a.status_data )) sttsdata, a.noId, a.nama_obat, a.keterangan, a.hari_ke "
                        + "FROM catatan_resep_antibiotik_histori a INNER JOIN reg_periksa rp ON rp.no_rawat = a.no_rawat "
                        + "INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis INNER JOIN pegawai pg ON pg.nik = a.nik_eksekutor WHERE "
                        + "a.tgl_perawatan between ? and ? and pg.nama like ? or "
                        + "a.tgl_perawatan between ? and ? and a.no_rawat like ? or "
                        + "a.tgl_perawatan between ? and ? and p.no_rkm_medis like ? or "
                        + "a.tgl_perawatan between ? and ? and a.status_data like ? or "
                        + "a.tgl_perawatan between ? and ? and p.nm_pasien like ? order by a.tgl_perawatan desc");
                
            } else if (statusAnti.equals("ranap") || statusAnti.equals("vk bersalin")) {
                psrestorAnti = koneksi.prepareStatement("SELECT IF(pg.nama = '-', 'Admin Utama', pg.nama ) pelaku, a.no_rawat, p.no_rkm_medis, p.nm_pasien, "
                        + "a.tgl_perawatan, a.jam_perawatan, a.waktu_eksekusi, upper(concat( 'DI', a.status_data )) sttsdata, a.noId, a.nama_obat, a.keterangan, a.hari_ke "
                        + "FROM catatan_resep_ranap_antibiotik_histori a INNER JOIN reg_periksa rp ON rp.no_rawat = a.no_rawat "
                        + "INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis INNER JOIN pegawai pg ON pg.nik = a.nik_eksekutor WHERE "
                        + "a.tgl_perawatan between ? and ? and pg.nama like ? or "
                        + "a.tgl_perawatan between ? and ? and a.no_rawat like ? or "
                        + "a.tgl_perawatan between ? and ? and p.no_rkm_medis like ? or "
                        + "a.tgl_perawatan between ? and ? and a.status_data like ? or "
                        + "a.tgl_perawatan between ? and ? and p.nm_pasien like ? order by a.tgl_perawatan desc");
            }            
            
            try {
                psrestorAnti.setString(1, Valid.SetTgl(DTPCari5.getSelectedItem() + ""));
                psrestorAnti.setString(2, Valid.SetTgl(DTPCari6.getSelectedItem() + ""));
                psrestorAnti.setString(3, "%" + TCari3.getText().trim() + "%");
                psrestorAnti.setString(4, Valid.SetTgl(DTPCari5.getSelectedItem() + ""));
                psrestorAnti.setString(5, Valid.SetTgl(DTPCari6.getSelectedItem() + ""));
                psrestorAnti.setString(6, "%" + TCari3.getText().trim() + "%");
                psrestorAnti.setString(7, Valid.SetTgl(DTPCari5.getSelectedItem() + ""));
                psrestorAnti.setString(8, Valid.SetTgl(DTPCari6.getSelectedItem() + ""));
                psrestorAnti.setString(9, "%" + TCari3.getText().trim() + "%");
                psrestorAnti.setString(10, Valid.SetTgl(DTPCari5.getSelectedItem() + ""));
                psrestorAnti.setString(11, Valid.SetTgl(DTPCari6.getSelectedItem() + ""));
                psrestorAnti.setString(12, "%" + TCari3.getText().trim() + "%");
                psrestorAnti.setString(13, Valid.SetTgl(DTPCari5.getSelectedItem() + ""));
                psrestorAnti.setString(14, Valid.SetTgl(DTPCari6.getSelectedItem() + ""));
                psrestorAnti.setString(15, "%" + TCari3.getText().trim() + "%");
                rsrestorAnti = psrestorAnti.executeQuery();
                while (rsrestorAnti.next()) {
                    tabMode2An.addRow(new String[]{
                        rsrestorAnti.getString("pelaku"),
                        rsrestorAnti.getString("no_rawat"),
                        rsrestorAnti.getString("no_rkm_medis"),
                        rsrestorAnti.getString("nm_pasien"),
                        rsrestorAnti.getString("tgl_perawatan"),
                        rsrestorAnti.getString("jam_perawatan"),
                        rsrestorAnti.getString("waktu_eksekusi"),
                        rsrestorAnti.getString("sttsdata"),                        
                        rsrestorAnti.getString("noId"),
                        rsrestorAnti.getString("nama_obat"),
                        rsrestorAnti.getString("keterangan"),
                        rsrestorAnti.getString("hari_ke")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rsrestorAnti != null) {
                    rsrestorAnti.close();
                }
                if (psrestorAnti != null) {
                    psrestorAnti.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount3.setText("" + tabMode2An.getRowCount());
    }
    
    private void kembalikanDataAnti() {
        try {
            if (statusAnti.equals("IGD (Ralan)") || statusAnti.equals("IGD (Ranap)") || statusAnti.equals("ralan")) {
                ps3Anti = koneksi.prepareStatement("select * from catatan_resep_antibiotik_histori where "
                        + "no_rawat='" + tbRiwayat1.getValueAt(tbRiwayat1.getSelectedRow(), 1).toString() + "'");
            } else if (statusAnti.equals("ranap") || statusAnti.equals("vk bersalin")) {
                ps3Anti = koneksi.prepareStatement("select * from catatan_resep_ranap_antibiotik_histori where "
                        + "no_rawat='" + tbRiwayat1.getValueAt(tbRiwayat1.getSelectedRow(), 1).toString() + "'");
            }
            try {
                rs3Anti = ps3Anti.executeQuery();
                while (rs3Anti.next()) {
                    if (statusAnti.equals("IGD (Ralan)") || statusAnti.equals("IGD (Ranap)") || statusAnti.equals("ralan")) {
                        try {
                            Sequel.menyimpanIgnore("catatan_resep_antibiotik",
                                    "'" + rs3Anti.getString("noId") + "',"
                                    + "'" + TNoRw.getText() + "',"
                                    + "'" + rs3Anti.getString("tgl_perawatan") + "',"
                                    + "'" + rs3Anti.getString("jam_perawatan") + "',"
                                    + "'" + rs3Anti.getString("nama_obat") + "',"
                                    + "'" + rs3Anti.getString("status") + "',"
                                    + "'" + rs3Anti.getString("kd_dokter") + "',"
                                    + "'" + rs3Anti.getString("keterangan") + "',"
                                    + "'" + rs3Anti.getString("hari_ke") + "'", "Catatan Resep Antibiotik Rawat Jalan");
                        } catch (Exception e) {
                            System.out.println("Simpan : " + e);
                        }
                        
                    } else if (statusAnti.equals("ranap") || statusAnti.equals("vk bersalin")) {
                        try {
                            Sequel.menyimpanIgnore("catatan_resep_ranap_antibiotik",
                                    "'" + rs3Anti.getString("noId") + "',"
                                    + "'" + TNoRw.getText() + "',"
                                    + "'" + rs3Anti.getString("tgl_perawatan") + "',"
                                    + "'" + rs3Anti.getString("jam_perawatan") + "',"
                                    + "'" + rs3Anti.getString("nama_obat") + "',"
                                    + "'" + rs3Anti.getString("status") + "',"
                                    + "'" + rs3Anti.getString("kd_dokter") + "',"
                                    + "'" + rs3Anti.getString("jenis_resep") + "',"
                                    + "'" + rs3Anti.getString("resep_untuk") + "',"
                                    + "'" + rs3Anti.getString("keterangan") + "',"
                                    + "'" + rs3Anti.getString("hari_ke") + "'", "Catatan Resep Rawat Inap");
                        } catch (Exception e) {
                            System.out.println("Simpan : " + e);
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs3Anti != null) {
                    rs3Anti.close();
                }
                if (ps3Anti != null) {
                    ps3Anti.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilTglResepAnti() {
        Valid.tabelKosong(tabModeResepAAn);
        try {
            if (chkRanapAn.isSelected() == true) {
                psR11Anti = koneksi.prepareStatement("select c.*, date_format(c.tgl_perawatan,'%d-%m-%Y') tglnya, d.nm_dokter "
                        + "from catatan_resep_ranap_antibiotik c inner join dokter d on d.kd_dokter = c.kd_dokter where c.no_rawat='" + TNoRw.getText() + "' "
                        + "GROUP BY c.tgl_perawatan, c.kd_dokter order by c.tgl_perawatan DESC LIMIT 100");
            } else if (chkRalanAn.isSelected() == true) {
                psR11Anti = koneksi.prepareStatement("select c.*, date_format(c.tgl_perawatan,'%d-%m-%Y') tglnya, d.nm_dokter "
                        + "from catatan_resep_antibiotik c inner join dokter d on d.kd_dokter = c.kd_dokter where c.no_rawat='" + TNoRw.getText() + "' "
                        + "GROUP BY c.tgl_perawatan, c.kd_dokter order by c.tgl_perawatan DESC LIMIT 100");
            }
            try {
                rsR11Anti = psR11Anti.executeQuery();
                while (rsR11Anti.next()) {
                    tabModeResepAAn.addRow(new String[]{
                        rsR11Anti.getString("tglnya"),
                        rsR11Anti.getString("nm_dokter"),
                        rsR11Anti.getString("tgl_perawatan"),
                        rsR11Anti.getString("kd_dokter")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsR11Anti != null) {
                    rsR11Anti.close();
                }
                if (psR11Anti != null) {
                    psR11Anti.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilItemResepRiwayatAnti(String tglresep, String norawat, String kddokter) {
        Valid.tabelKosong(tabModeResepBAn);
        try {
            if (chkRanapAn.isSelected() == true) {
                psR22Anti = koneksi.prepareStatement("select *, date_format(tgl_perawatan,'%d-%m-%Y') tgl from catatan_resep_ranap_antibiotik where "
                        + "tgl_perawatan='" + tglresep + "' and no_rawat='" + norawat + "' and kd_dokter='" + kddokter + "' order by noId");
            } else if (chkRalanAn.isSelected() == true) {
                psR22Anti = koneksi.prepareStatement("select *, date_format(tgl_perawatan,'%d-%m-%Y') tgl, 'BIASA' jenis_resep, '-' resep_untuk "
                        + "from catatan_resep_antibiotik where tgl_perawatan='" + tglresep + "' and no_rawat='" + norawat + "' "
                        + "and kd_dokter='" + kddokter + "' order by noId");
            }
            try {
                rsR22Anti = psR22Anti.executeQuery();
                while (rsR22Anti.next()) {
                    tabModeResepBAn.addRow(new Object[]{
                        false,
                        rsR22Anti.getString("no_rawat"),
                        rsR22Anti.getString("tgl"),
                        rsR22Anti.getString("jam_perawatan"),
                        rsR22Anti.getString("nama_obat"),
                        rsR22Anti.getString("status"),
                        rsR22Anti.getString("noId"), 
                        rsR22Anti.getString("jenis_resep"),
                        rsR22Anti.getString("tgl_perawatan"),
                        rsR22Anti.getString("resep_untuk"),
                        rsR22Anti.getString("keterangan"),
                        rsR22Anti.getString("hari_ke")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsR22Anti != null) {
                    rsR22Anti.close();
                }
                if (psR22Anti != null) {
                    psR22Anti.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount4.setText("" + tabModeResepBAn.getRowCount());
    }
    
    private void contengResepAnti() {
        for (i = 0; i < tbResepObat1.getRowCount(); i++) {
            if (tbResepObat1.getValueAt(i, 1).equals(TNoRw.getText())) {
                tbResepObat1.setValueAt(Boolean.TRUE, i, 0);
            }
        }
    }
    
    private void tampilItemResepRalanAnti(String tglresep, String norm, String kdpoli) {
        Valid.tabelKosong(tabModeResep2An);
        try {
            psR2Anti = koneksi.prepareStatement("select c.no_rawat, c.tgl_perawatan, c.jam_perawatan, c.nama_obat, c.status, d.nm_dokter, "
                    + "c.noID, '-' jenis_resep, '-' resep_untuk, c.keterangan, c.hari_ke from catatan_resep_antibiotik c "
                    + "inner join reg_periksa r on r.no_rawat = c.no_rawat inner join dokter d on d.kd_dokter = c.kd_dokter where "
                    + "c.tgl_perawatan='" + tglresep + "' and r.no_rkm_medis='" + norm + "' and r.kd_poli='" + kdpoli + "' order by c.noId");
            try {
                rsR2Anti = psR2Anti.executeQuery();
                while (rsR2Anti.next()) {
                    tabModeResep2An.addRow(new Object[]{
                        false,
                        rsR2Anti.getString(1),
                        rsR2Anti.getString(2),
                        rsR2Anti.getString(3),
                        rsR2Anti.getString(4),
                        rsR2Anti.getString(5),
                        rsR2Anti.getString(6),
                        rsR2Anti.getString(7),
                        rsR2Anti.getString(8),
                        rsR2Anti.getString(9),
                        rsR2Anti.getString(10),
                        rsR2Anti.getString(11)
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsR2Anti != null) {
                    rsR2Anti.close();
                }
                if (psR2Anti != null) {
                    psR2Anti.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void copyResepnyaAnti() {
        if (statusAnti.equals("IGD (Ralan)") || statusAnti.equals("IGD (Ranap)") || statusAnti.equals("ralan")) {
            try {
                j = 0;
                for (i = 0; i < tbItemResepAnti.getRowCount(); i++) {
                    if (tbItemResepAnti.getValueAt(i, 0).toString().equals("true")) {
                        j++;
                    }
                }

                for (i = 0; i < tbItemResepAnti.getRowCount(); i++) {
                    if (tbItemResepAnti.getValueAt(i, 0).toString().equals("true")) {
                        Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(noId,6),signed)),0) from catatan_resep_antibiotik where "
                                + "tgl_perawatan like '%" + Sequel.cariIsi("select year(now())") + "%' ",
                                Sequel.cariIsi("select year(now())"), 6, noIdObatAnti);

                        Sequel.menyimpan("catatan_resep_antibiotik", "'" + noIdObatAnti.getText() + "','" + TNoRw.getText() + "', "
                                + "'" + Sequel.cariIsi("select date(now())") + "',"
                                + "'" + Sequel.cariIsi("SELECT TIME(NOW())") + "',"
                                + "'" + tbItemResepAnti.getValueAt(i, 4).toString() + "','BELUM','" + akses.getkode() + "',"
                                + "'" + tbItemResepAnti.getValueAt(i, 10).toString() + "',"
                                + "'" + tbItemResepAnti.getValueAt(i, 11).toString() + "'", "Copy Resep Antibiotik Sebelumnya");
                    }
                }

                Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + TNoRw.getText() + "' and status_lanjut='ralan'", DTPCariA);
                DTPCariB.setDate(new Date());
                TResepObatAn.setText("");
                Tket.setText("");
                TResepObatAn.requestFocus();
                tampilResepObatAnti();
                Sequel.mengedit("reg_periksa", "no_rawat='" + TNoRw.getText() + "'", "stts='Sudah Diperiksa Dokter'");
                JOptionPane.showMessageDialog(null, "Resep antibiotik sebelumnya pada tgl. " + Valid.SetTglINDONESIA(tglResepAnti) + " berhasil tercopy,..");

            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }
            
        } else if (statusAnti.equals("ranap") || statusAnti.equals("vk bersalin")) {
            try {
                j = 0;
                for (i = 0; i < tbItemResepAnti.getRowCount(); i++) {
                    if (tbItemResepAnti.getValueAt(i, 0).toString().equals("true")) {
                        j++;
                    }
                }

                for (i = 0; i < tbItemResepAnti.getRowCount(); i++) {
                    if (tbItemResepAnti.getValueAt(i, 0).toString().equals("true")) {
                        Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(noId,6),signed)),0) from catatan_resep_ranap_antibiotik where "
                                + "tgl_perawatan like '%" + Sequel.cariIsi("select year(now())") + "%' ",
                                Sequel.cariIsi("select year(now())"), 6, noIdObatAnti);

                        Sequel.menyimpan("catatan_resep_ranap_antibiotik", "'" + noIdObatAnti.getText() + "','" + TNoRw.getText() + "', "
                                + "'" + Sequel.cariIsi("select date(now())") + "',"
                                + "'" + Sequel.cariIsi("SELECT TIME(NOW())") + "',"
                                + "'" + tbItemResepAnti.getValueAt(i, 4).toString() + "','BELUM','" + akses.getkode() + "',"
                                + "'" + tbItemResepAnti.getValueAt(i, 8).toString() + "',"
                                + "'" + tbItemResepAnti.getValueAt(i, 9).toString() + "',"
                                + "'" + tbItemResepAnti.getValueAt(i, 10).toString() + "',"
                                + "'" + tbItemResepAnti.getValueAt(i, 11).toString() + "'", "Copy Resep Antibiotik Sebelumnya");
                    }
                }

                DTPCariA.setDate(new Date());
                DTPCariB.setDate(new Date());
                TResepObatAn.setText("");
                Tket.setText("");
                TResepObatAn.requestFocus();
                tampilResepObatAnti();
                JOptionPane.showMessageDialog(null, "Resep antibiotik sebelumnya pada tgl. " + Valid.SetTglINDONESIA(tglResepAnti) + " berhasil tercopy,..");

            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }
        }
    }
    
    private void tampilItemResepRanapAnti(String tglresep, String norw) {
        Valid.tabelKosong(tabModeResep2An);
        try {
            psR2Anti = koneksi.prepareStatement("select c.no_rawat, c.tgl_perawatan, c.jam_perawatan, c.nama_obat, c.status, "
                    + "d.nm_dokter, c.noID, c.jenis_resep, c.resep_untuk, c.keterangan, c.hari_ke FROM catatan_resep_ranap_antibiotik c "
                    + "INNER JOIN reg_periksa r ON r.no_rawat = c.no_rawat "
                    + "INNER JOIN dokter d ON d.kd_dokter = c.kd_dokter	WHERE "
                    + "r.status_lanjut='ranap' AND c.tgl_perawatan='" + tglresep + "' AND c.no_rawat = '" + norw + "' ORDER BY c.noId");
            try {
                rsR2Anti = psR2Anti.executeQuery();
                while (rsR2Anti.next()) {
                    tabModeResep2An.addRow(new Object[]{
                        false,
                        rsR2Anti.getString(1),
                        rsR2Anti.getString(2),
                        rsR2Anti.getString(3),
                        rsR2Anti.getString(4),
                        rsR2Anti.getString(5),
                        rsR2Anti.getString(6),
                        rsR2Anti.getString(7),
                        rsR2Anti.getString(8),
                        rsR2Anti.getString(9),
                        rsR2Anti.getString(10),
                        rsR2Anti.getString(11)
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsR2Anti != null) {
                    rsR2Anti.close();
                }
                if (psR2Anti != null) {
                    psR2Anti.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void hitungItemResepDiconteng() {
        j = 0;
        for (i = 0; i < tbItemResep1.getRowCount(); i++) {
            if (tbItemResep1.getValueAt(i, 0).toString().equals("true")) {
                j++;
            }
            LCount2.setText(Valid.SetAngka2(j));
        }
    }
    
    private void hitungItemResepDicontengAnti() {
        j = 0;
        for (i = 0; i < tbItemResep2.getRowCount(); i++) {
            if (tbItemResep2.getValueAt(i, 0).toString().equals("true")) {
                j++;
            }
            LCount4.setText(Valid.SetAngka2(j));
        }
    }
}
