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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import rekammedis.DlgMasterIndikatorMutu;

/**
 *
 * @author dosen
 */
public class DlgLaporanIndikatorMutu extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1, tabMode2, tabMode3, tabMode4;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps, ps1, ps2, ps3, ps4;
    private ResultSet rs, rs1, rs2, rs3, rs4;
    private int i = 0, x = 0, n = 0, ttlNum = 0, ttlDen = 0, ttlSemua = 0;
    private double hitungTot = 0;
    private String angkaBulan = "", cekBulan = "", total = "", sttsData = "", 
            tgl1 = "", tgl2 = "", tgl3 = "", tgl4 = "", tgl5 = "", tgl6 = "", tgl7 = "", tgl8 = "", tgl9 = "", tgl10 = "",
            tgl11 = "", tgl12 = "", tgl13 = "", tgl14 = "", tgl15 = "", tgl16 = "", tgl17 = "", tgl18 = "", tgl19 = "", tgl20 = "",
            tgl21 = "", tgl22 = "", tgl23 = "", tgl24 = "", tgl25 = "", tgl26 = "", tgl27 = "", tgl28 = "", tgl29 = "", tgl30 = "", tgl31 = "",
            dialog_simpan = "", pakaiGedung = "";
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public DlgLaporanIndikatorMutu(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //data di tabel grid rata tengah
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);

        tabMode = new DefaultTableModel(null, new String[]{
            "Ruangan", "No.", "Indikator", "Jenis Indikator", "Kalimat Deskripsi", "Tgl. 1", "Tgl. 2", "Tgl. 3", "Tgl. 4", "Tgl. 5", "Tgl. 6", "Tgl. 7", "Tgl. 8", "Tgl. 9", "Tgl. 10",
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

        for (i = 0; i < 40; i++) {
            TableColumn column = tbIndikator.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(130);
            } else if (i == 1) {
                column.setPreferredWidth(30);
            } else if (i == 2) {
                column.setPreferredWidth(400);
            } else if (i == 3) {
                column.setPreferredWidth(100);
            } else if (i == 4) {
                column.setPreferredWidth(450);
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
                column.setPreferredWidth(48);
            } else if (i == 36) {
                column.setPreferredWidth(55);
            } else if (i == 37) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 38) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 39) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbIndikator.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbIndikator.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbIndikator.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        tbIndikator.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        tbIndikator.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
        tbIndikator.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
        tbIndikator.getColumnModel().getColumn(9).setCellRenderer(centerRenderer);
        tbIndikator.getColumnModel().getColumn(10).setCellRenderer(centerRenderer);
        tbIndikator.getColumnModel().getColumn(11).setCellRenderer(centerRenderer);
        tbIndikator.getColumnModel().getColumn(12).setCellRenderer(centerRenderer);
        tbIndikator.getColumnModel().getColumn(13).setCellRenderer(centerRenderer);
        tbIndikator.getColumnModel().getColumn(14).setCellRenderer(centerRenderer);
        tbIndikator.getColumnModel().getColumn(15).setCellRenderer(centerRenderer);
        tbIndikator.getColumnModel().getColumn(16).setCellRenderer(centerRenderer);
        tbIndikator.getColumnModel().getColumn(17).setCellRenderer(centerRenderer);
        tbIndikator.getColumnModel().getColumn(18).setCellRenderer(centerRenderer);
        tbIndikator.getColumnModel().getColumn(19).setCellRenderer(centerRenderer);        
        tbIndikator.getColumnModel().getColumn(20).setCellRenderer(centerRenderer);
        tbIndikator.getColumnModel().getColumn(21).setCellRenderer(centerRenderer);
        tbIndikator.getColumnModel().getColumn(22).setCellRenderer(centerRenderer);
        tbIndikator.getColumnModel().getColumn(23).setCellRenderer(centerRenderer);
        tbIndikator.getColumnModel().getColumn(24).setCellRenderer(centerRenderer);
        tbIndikator.getColumnModel().getColumn(25).setCellRenderer(centerRenderer);
        tbIndikator.getColumnModel().getColumn(26).setCellRenderer(centerRenderer);
        tbIndikator.getColumnModel().getColumn(27).setCellRenderer(centerRenderer);
        tbIndikator.getColumnModel().getColumn(28).setCellRenderer(centerRenderer);        
        tbIndikator.getColumnModel().getColumn(29).setCellRenderer(centerRenderer);
        tbIndikator.getColumnModel().getColumn(30).setCellRenderer(centerRenderer);
        tbIndikator.getColumnModel().getColumn(31).setCellRenderer(centerRenderer);
        tbIndikator.getColumnModel().getColumn(32).setCellRenderer(centerRenderer);
        tbIndikator.getColumnModel().getColumn(33).setCellRenderer(centerRenderer);
        tbIndikator.getColumnModel().getColumn(34).setCellRenderer(centerRenderer);
        tbIndikator.getColumnModel().getColumn(35).setCellRenderer(centerRenderer);
        tbIndikator.getColumnModel().getColumn(36).setCellRenderer(centerRenderer);
        
        tabMode1 = new DefaultTableModel(null, new String[]{
            "Ruangan", "Indikator", "Jenis Indikator", "Kalimat Deskripsi", "Tgl. 1", "Tgl. 2", "Tgl. 3", "Tgl. 4", "Tgl. 5", "Tgl. 6", "Tgl. 7", "Tgl. 8", "Tgl. 9", "Tgl. 10",
            "Tgl. 11", "Tgl. 12", "Tgl. 13", "Tgl. 14", "Tgl. 15", "Tgl. 16", "Tgl. 17", "Tgl. 18", "Tgl. 19", "Tgl. 20",
            "Tgl. 21", "Tgl. 22", "Tgl. 23", "Tgl. 24", "Tgl. 25", "Tgl. 26", "Tgl. 27", "Tgl. 28", "Tgl. 29", "Tgl. 30", "Tgl. 31", "Total",
            "kdindikator", "tglcatat", "kdnumdemon"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbPrioritas.setModel(tabMode1);
        tbPrioritas.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPrioritas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 39; i++) {
            TableColumn column = tbPrioritas.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(130);
            } else if (i == 1) {
                column.setPreferredWidth(400);
            } else if (i == 2) {
                column.setPreferredWidth(100);
            } else if (i == 3) {
                column.setPreferredWidth(450);
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
        tbPrioritas.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah        
        tbPrioritas.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tbPrioritas.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        tbPrioritas.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        tbPrioritas.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
        tbPrioritas.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
        tbPrioritas.getColumnModel().getColumn(9).setCellRenderer(centerRenderer);
        tbPrioritas.getColumnModel().getColumn(10).setCellRenderer(centerRenderer);
        tbPrioritas.getColumnModel().getColumn(11).setCellRenderer(centerRenderer);
        tbPrioritas.getColumnModel().getColumn(12).setCellRenderer(centerRenderer);
        tbPrioritas.getColumnModel().getColumn(13).setCellRenderer(centerRenderer);
        tbPrioritas.getColumnModel().getColumn(14).setCellRenderer(centerRenderer);
        tbPrioritas.getColumnModel().getColumn(15).setCellRenderer(centerRenderer);
        tbPrioritas.getColumnModel().getColumn(16).setCellRenderer(centerRenderer);
        tbPrioritas.getColumnModel().getColumn(17).setCellRenderer(centerRenderer);
        tbPrioritas.getColumnModel().getColumn(18).setCellRenderer(centerRenderer);        
        tbPrioritas.getColumnModel().getColumn(19).setCellRenderer(centerRenderer);
        tbPrioritas.getColumnModel().getColumn(20).setCellRenderer(centerRenderer);
        tbPrioritas.getColumnModel().getColumn(21).setCellRenderer(centerRenderer);
        tbPrioritas.getColumnModel().getColumn(22).setCellRenderer(centerRenderer);
        tbPrioritas.getColumnModel().getColumn(23).setCellRenderer(centerRenderer);
        tbPrioritas.getColumnModel().getColumn(24).setCellRenderer(centerRenderer);
        tbPrioritas.getColumnModel().getColumn(25).setCellRenderer(centerRenderer);
        tbPrioritas.getColumnModel().getColumn(26).setCellRenderer(centerRenderer);
        tbPrioritas.getColumnModel().getColumn(27).setCellRenderer(centerRenderer);        
        tbPrioritas.getColumnModel().getColumn(28).setCellRenderer(centerRenderer);
        tbPrioritas.getColumnModel().getColumn(29).setCellRenderer(centerRenderer);
        tbPrioritas.getColumnModel().getColumn(30).setCellRenderer(centerRenderer);
        tbPrioritas.getColumnModel().getColumn(31).setCellRenderer(centerRenderer);
        tbPrioritas.getColumnModel().getColumn(32).setCellRenderer(centerRenderer);
        tbPrioritas.getColumnModel().getColumn(33).setCellRenderer(centerRenderer);
        tbPrioritas.getColumnModel().getColumn(34).setCellRenderer(centerRenderer);
        tbPrioritas.getColumnModel().getColumn(35).setCellRenderer(centerRenderer);
        
        tabMode2 = new DefaultTableModel(null, new String[]{
            "Ruangan", "Indikator", "Jenis Indikator", "Kalimat Deskripsi", "Tgl. 1", "Tgl. 2", "Tgl. 3", "Tgl. 4", "Tgl. 5", "Tgl. 6", "Tgl. 7", "Tgl. 8", "Tgl. 9", "Tgl. 10",
            "Tgl. 11", "Tgl. 12", "Tgl. 13", "Tgl. 14", "Tgl. 15", "Tgl. 16", "Tgl. 17", "Tgl. 18", "Tgl. 19", "Tgl. 20",
            "Tgl. 21", "Tgl. 22", "Tgl. 23", "Tgl. 24", "Tgl. 25", "Tgl. 26", "Tgl. 27", "Tgl. 28", "Tgl. 29", "Tgl. 30", "Tgl. 31", "Total",
            "kdindikator", "tglcatat", "kdnumdemon"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbNasional.setModel(tabMode2);
        tbNasional.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbNasional.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 39; i++) {
            TableColumn column = tbNasional.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(130);
            } else if (i == 1) {
                column.setPreferredWidth(400);
            } else if (i == 2) {
                column.setPreferredWidth(100);
            } else if (i == 3) {
                column.setPreferredWidth(450);
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
        tbNasional.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah        
        tbNasional.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tbNasional.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        tbNasional.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        tbNasional.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
        tbNasional.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
        tbNasional.getColumnModel().getColumn(9).setCellRenderer(centerRenderer);
        tbNasional.getColumnModel().getColumn(10).setCellRenderer(centerRenderer);
        tbNasional.getColumnModel().getColumn(11).setCellRenderer(centerRenderer);
        tbNasional.getColumnModel().getColumn(12).setCellRenderer(centerRenderer);
        tbNasional.getColumnModel().getColumn(13).setCellRenderer(centerRenderer);
        tbNasional.getColumnModel().getColumn(14).setCellRenderer(centerRenderer);
        tbNasional.getColumnModel().getColumn(15).setCellRenderer(centerRenderer);
        tbNasional.getColumnModel().getColumn(16).setCellRenderer(centerRenderer);
        tbNasional.getColumnModel().getColumn(17).setCellRenderer(centerRenderer);
        tbNasional.getColumnModel().getColumn(18).setCellRenderer(centerRenderer);        
        tbNasional.getColumnModel().getColumn(19).setCellRenderer(centerRenderer);
        tbNasional.getColumnModel().getColumn(20).setCellRenderer(centerRenderer);
        tbNasional.getColumnModel().getColumn(21).setCellRenderer(centerRenderer);
        tbNasional.getColumnModel().getColumn(22).setCellRenderer(centerRenderer);
        tbNasional.getColumnModel().getColumn(23).setCellRenderer(centerRenderer);
        tbNasional.getColumnModel().getColumn(24).setCellRenderer(centerRenderer);
        tbNasional.getColumnModel().getColumn(25).setCellRenderer(centerRenderer);
        tbNasional.getColumnModel().getColumn(26).setCellRenderer(centerRenderer);
        tbNasional.getColumnModel().getColumn(27).setCellRenderer(centerRenderer);        
        tbNasional.getColumnModel().getColumn(28).setCellRenderer(centerRenderer);
        tbNasional.getColumnModel().getColumn(29).setCellRenderer(centerRenderer);
        tbNasional.getColumnModel().getColumn(30).setCellRenderer(centerRenderer);
        tbNasional.getColumnModel().getColumn(31).setCellRenderer(centerRenderer);
        tbNasional.getColumnModel().getColumn(32).setCellRenderer(centerRenderer);
        tbNasional.getColumnModel().getColumn(33).setCellRenderer(centerRenderer);
        tbNasional.getColumnModel().getColumn(34).setCellRenderer(centerRenderer);
        tbNasional.getColumnModel().getColumn(35).setCellRenderer(centerRenderer);
        
        tabMode3 = new DefaultTableModel(null, new String[]{
            "Ruangan", "Indikator", "Jml. Numerator", "Jml. Denominator", "Total ND"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbTotPrioritas.setModel(tabMode3);
        tbTotPrioritas.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbTotPrioritas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        for (i = 0; i < 5; i++) {
            TableColumn column = tbTotPrioritas.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(130);
            } else if (i == 1) {
                column.setPreferredWidth(500);
            } else if (i == 2) {
                column.setPreferredWidth(100);
            } else if (i == 3) {
                column.setPreferredWidth(100);
            } else if (i == 4) {
                column.setPreferredWidth(70);
            }        
        }
        tbTotPrioritas.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah        
        tbTotPrioritas.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbTotPrioritas.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbTotPrioritas.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        
        tabMode4 = new DefaultTableModel(null, new String[]{
            "Ruangan", "Indikator", "Jml. Numerator", "Jml. Denominator", "Total ND"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbTotNasional.setModel(tabMode4);
        tbTotNasional.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbTotNasional.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        for (i = 0; i < 5; i++) {
            TableColumn column = tbTotNasional.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(130);
            } else if (i == 1) {
                column.setPreferredWidth(500);
            } else if (i == 2) {
                column.setPreferredWidth(100);
            } else if (i == 3) {
                column.setPreferredWidth(100);
            } else if (i == 4) {
                column.setPreferredWidth(70);
            }        
        }
        tbTotNasional.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah        
        tbTotNasional.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbTotNasional.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbTotNasional.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        
        Ttahun.setDocument(new batasInput((byte) 4).getOnlyAngka(Ttahun));
    }
 
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu0 = new javax.swing.JPopupMenu();
        MnExportKeExcel = new javax.swing.JMenuItem();
        MnExportKeExcelSemua = new javax.swing.JMenuItem();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnExportKeExcelMutuPrio = new javax.swing.JMenuItem();
        MnExportKeExcelRekapPrio = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        MnExportKeExcelMutuNas = new javax.swing.JMenuItem();
        MnExportKeExcelRekapNas = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnMasterIndikator = new widget.Button();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass10 = new widget.panelisi();
        jLabel29 = new widget.Label();
        cmbBulan = new widget.ComboBox();
        jLabel35 = new widget.Label();
        Ttahun = new widget.TextBox();
        jLabel6 = new widget.Label();
        cmbGedung = new widget.ComboBox();
        BtnCari = new widget.Button();
        jLabel39 = new widget.Label();
        cmbSttsIndikator = new widget.ComboBox();
        jLabel40 = new widget.Label();
        cmbJnsData = new widget.ComboBox();
        TabMutu = new javax.swing.JTabbedPane();
        Scroll = new widget.ScrollPane();
        tbIndikator = new widget.Table();
        panelGlass9 = new widget.panelisi();
        Scroll2 = new widget.ScrollPane();
        tbPrioritas = new widget.Table();
        Scroll6 = new widget.ScrollPane();
        tbTotPrioritas = new widget.Table();
        panelGlass11 = new widget.panelisi();
        Scroll5 = new widget.ScrollPane();
        tbNasional = new widget.Table();
        Scroll7 = new widget.ScrollPane();
        tbTotNasional = new widget.Table();

        jPopupMenu0.setName("jPopupMenu0"); // NOI18N

        MnExportKeExcel.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnExportKeExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/export-excel.png"))); // NOI18N
        MnExportKeExcel.setText("Export Data Ke Ms. Excel");
        MnExportKeExcel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnExportKeExcel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnExportKeExcel.setIconTextGap(5);
        MnExportKeExcel.setName("MnExportKeExcel"); // NOI18N
        MnExportKeExcel.setPreferredSize(new java.awt.Dimension(210, 26));
        MnExportKeExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnExportKeExcelActionPerformed(evt);
            }
        });
        jPopupMenu0.add(MnExportKeExcel);

        MnExportKeExcelSemua.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnExportKeExcelSemua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/export-excel.png"))); // NOI18N
        MnExportKeExcelSemua.setText("Export Data Ke Excel Semua Unit");
        MnExportKeExcelSemua.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnExportKeExcelSemua.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnExportKeExcelSemua.setIconTextGap(5);
        MnExportKeExcelSemua.setName("MnExportKeExcelSemua"); // NOI18N
        MnExportKeExcelSemua.setPreferredSize(new java.awt.Dimension(210, 26));
        MnExportKeExcelSemua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnExportKeExcelSemuaActionPerformed(evt);
            }
        });
        jPopupMenu0.add(MnExportKeExcelSemua);

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnExportKeExcelMutuPrio.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnExportKeExcelMutuPrio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/export-excel.png"))); // NOI18N
        MnExportKeExcelMutuPrio.setText("Export Data Mutu Prioritas Ke Excel");
        MnExportKeExcelMutuPrio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnExportKeExcelMutuPrio.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnExportKeExcelMutuPrio.setIconTextGap(5);
        MnExportKeExcelMutuPrio.setName("MnExportKeExcelMutuPrio"); // NOI18N
        MnExportKeExcelMutuPrio.setPreferredSize(new java.awt.Dimension(270, 26));
        MnExportKeExcelMutuPrio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnExportKeExcelMutuPrioActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnExportKeExcelMutuPrio);

        MnExportKeExcelRekapPrio.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnExportKeExcelRekapPrio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/export-excel.png"))); // NOI18N
        MnExportKeExcelRekapPrio.setText("Export Rekap Data Mutu Prioritas Ke Ms. Excel");
        MnExportKeExcelRekapPrio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnExportKeExcelRekapPrio.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnExportKeExcelRekapPrio.setIconTextGap(5);
        MnExportKeExcelRekapPrio.setName("MnExportKeExcelRekapPrio"); // NOI18N
        MnExportKeExcelRekapPrio.setPreferredSize(new java.awt.Dimension(270, 26));
        MnExportKeExcelRekapPrio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnExportKeExcelRekapPrioActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnExportKeExcelRekapPrio);

        jPopupMenu2.setName("jPopupMenu2"); // NOI18N

        MnExportKeExcelMutuNas.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnExportKeExcelMutuNas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/export-excel.png"))); // NOI18N
        MnExportKeExcelMutuNas.setText("Export Data Nasional Mutu Ke Excel");
        MnExportKeExcelMutuNas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnExportKeExcelMutuNas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnExportKeExcelMutuNas.setIconTextGap(5);
        MnExportKeExcelMutuNas.setName("MnExportKeExcelMutuNas"); // NOI18N
        MnExportKeExcelMutuNas.setPreferredSize(new java.awt.Dimension(270, 26));
        MnExportKeExcelMutuNas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnExportKeExcelMutuNasActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnExportKeExcelMutuNas);

        MnExportKeExcelRekapNas.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnExportKeExcelRekapNas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/export-excel.png"))); // NOI18N
        MnExportKeExcelRekapNas.setText("Export Rekap Data Nasional Mutu Ke Ms. Excel");
        MnExportKeExcelRekapNas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnExportKeExcelRekapNas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnExportKeExcelRekapNas.setIconTextGap(5);
        MnExportKeExcelRekapNas.setName("MnExportKeExcelRekapNas"); // NOI18N
        MnExportKeExcelRekapNas.setPreferredSize(new java.awt.Dimension(270, 26));
        MnExportKeExcelRekapNas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnExportKeExcelRekapNasActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnExportKeExcelRekapNas);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Laporan Indikator Mutu Rumah Sakit ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 124));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 5, 9));

        BtnMasterIndikator.setForeground(new java.awt.Color(0, 0, 0));
        BtnMasterIndikator.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        BtnMasterIndikator.setMnemonic('T');
        BtnMasterIndikator.setText("Master Indikator MRS");
        BtnMasterIndikator.setToolTipText("Alt+T");
        BtnMasterIndikator.setName("BtnMasterIndikator"); // NOI18N
        BtnMasterIndikator.setPreferredSize(new java.awt.Dimension(170, 30));
        BtnMasterIndikator.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnMasterIndikatorActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnMasterIndikator);

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

        panelGlass10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Filter Data ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
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
        jLabel6.setText("Rg. Perawatan/Unit/Inst./Bidang/Sub :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(210, 23));
        panelGlass10.add(jLabel6);

        cmbGedung.setForeground(new java.awt.Color(0, 0, 0));
        cmbGedung.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "RAWAT JALAN", "IBS", "AR RAUDAH", "HEMODIALISA" }));
        cmbGedung.setName("cmbGedung"); // NOI18N
        cmbGedung.setPreferredSize(new java.awt.Dimension(190, 23));
        panelGlass10.add(cmbGedung);

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

        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setText("Status Indikator :");
        jLabel39.setName("jLabel39"); // NOI18N
        jLabel39.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass10.add(jLabel39);

        cmbSttsIndikator.setForeground(new java.awt.Color(0, 0, 0));
        cmbSttsIndikator.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Aktif", "Non Aktif", "Semua" }));
        cmbSttsIndikator.setName("cmbSttsIndikator"); // NOI18N
        cmbSttsIndikator.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass10.add(cmbSttsIndikator);

        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setText("Jenis Data :");
        jLabel40.setName("jLabel40"); // NOI18N
        jLabel40.setPreferredSize(new java.awt.Dimension(75, 23));
        panelGlass10.add(jLabel40);

        cmbJnsData.setForeground(new java.awt.Color(0, 0, 0));
        cmbJnsData.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Input Data", "Laporan" }));
        cmbJnsData.setSelectedIndex(2);
        cmbJnsData.setName("cmbJnsData"); // NOI18N
        cmbJnsData.setPreferredSize(new java.awt.Dimension(85, 23));
        panelGlass10.add(cmbJnsData);

        jPanel3.add(panelGlass10, java.awt.BorderLayout.CENTER);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        TabMutu.setBackground(new java.awt.Color(254, 255, 254));
        TabMutu.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabMutu.setName("TabMutu"); // NOI18N
        TabMutu.setPreferredSize(new java.awt.Dimension(0, 2000));
        TabMutu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabMutuMouseClicked(evt);
            }
        });

        Scroll.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Judul", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        Scroll.setComponentPopupMenu(jPopupMenu0);
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbIndikator.setName("tbIndikator"); // NOI18N
        tbIndikator.getTableHeader().setReorderingAllowed(false);
        Scroll.setViewportView(tbIndikator);

        TabMutu.addTab("Indikator Mutu Pelayanan", Scroll);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass9.setLayout(new java.awt.GridLayout(1, 0));

        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);

        tbPrioritas.setComponentPopupMenu(jPopupMenu1);
        tbPrioritas.setName("tbPrioritas"); // NOI18N
        tbPrioritas.getTableHeader().setReorderingAllowed(false);
        Scroll2.setViewportView(tbPrioritas);

        panelGlass9.add(Scroll2);

        Scroll6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Rekap Total Indikator Mutu Prioritas ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        Scroll6.setName("Scroll6"); // NOI18N
        Scroll6.setOpaque(true);

        tbTotPrioritas.setComponentPopupMenu(jPopupMenu1);
        tbTotPrioritas.setName("tbTotPrioritas"); // NOI18N
        tbTotPrioritas.getTableHeader().setReorderingAllowed(false);
        Scroll6.setViewportView(tbTotPrioritas);

        panelGlass9.add(Scroll6);

        TabMutu.addTab("Indikator Mutu Prioritas", panelGlass9);

        panelGlass11.setName("panelGlass11"); // NOI18N
        panelGlass11.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass11.setLayout(new java.awt.GridLayout(1, 2));

        Scroll5.setName("Scroll5"); // NOI18N
        Scroll5.setOpaque(true);

        tbNasional.setComponentPopupMenu(jPopupMenu2);
        tbNasional.setName("tbNasional"); // NOI18N
        tbNasional.getTableHeader().setReorderingAllowed(false);
        Scroll5.setViewportView(tbNasional);

        panelGlass11.add(Scroll5);

        Scroll7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Rekap Total Indikator Nasional Mutu ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        Scroll7.setName("Scroll7"); // NOI18N
        Scroll7.setOpaque(true);

        tbTotNasional.setComponentPopupMenu(jPopupMenu2);
        tbTotNasional.setName("tbTotNasional"); // NOI18N
        tbTotNasional.getTableHeader().setReorderingAllowed(false);
        Scroll7.setViewportView(tbTotNasional);

        panelGlass11.add(Scroll7);

        TabMutu.addTab("Indikator Nasional Mutu", panelGlass11);

        internalFrame1.add(TabMutu, java.awt.BorderLayout.CENTER);

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

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        if (TabMutu.getSelectedIndex() == 0) {
            tampil();
        } else if (TabMutu.getSelectedIndex() == 1) {
            tampilPrioritas();
        } else if (TabMutu.getSelectedIndex() == 2) {
            tampilNasional();
        }
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        BtnCariActionPerformed(null);
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Sequel.cariIsiComboDB("SELECT nm_gedung FROM bangsal WHERE nm_gedung<>'-' and nm_gedung not like '%ar-rau%' and status='1' GROUP BY nm_gedung ORDER BY nm_gedung", cmbGedung);
        
        cmbBulan.setSelectedItem(Sequel.bulanINDONESIA("select month(now())"));
        angkaBulan = Sequel.cariIsi("select month(now())");
        Ttahun.setText(Sequel.cariIsi("select year(now())"));
        TabMutu.setSelectedIndex(0);
        tampil();
    }//GEN-LAST:event_formWindowOpened

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

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Data indikator mutu rumah sakit belum ditampilkan pada tabel..!!");
        } else if (Ttahun.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Tahun harus diisi dulu dengan benar..!!");
            Ttahun.requestFocus();
        } else if (cmbGedung.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih salah satu ruang perawatannya dulu..!!");
            cmbGedung.requestFocus();
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
            param.put("judul", "RUANG " + cmbGedung.getSelectedItem().toString().toUpperCase() + " BULAN " + cmbBulan.getSelectedItem().toString().toUpperCase() + " TAHUN " + Ttahun.getText());

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
                        + tabMode.getValueAt(r, 35).toString() + "','"
                        + tabMode.getValueAt(r, 36).toString() + "',''", "Indikator Mutu Rumah Sakit");
            }
            Sequel.AutoComitTrue();
            Valid.MyReport("rptIndikatorNasionalMutu.jasper", "report", "::[ Indikator Mutu Rumah Sakit ]::",
                    "select * from temporary", param);
            this.setCursor(Cursor.getDefaultCursor());
            tampil();
        }
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void BtnMasterIndikatorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnMasterIndikatorActionPerformed
        akses.setform("DlgLaporanIndikatorMutu");
        DlgMasterIndikatorMutu mutu = new DlgMasterIndikatorMutu(null, false);
        mutu.isCek();
        mutu.emptTeks();
        mutu.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        mutu.setLocationRelativeTo(internalFrame1);
        mutu.setVisible(true);
    }//GEN-LAST:event_BtnMasterIndikatorActionPerformed

    private void TabMutuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabMutuMouseClicked
        if (TabMutu.getSelectedIndex() == 0) {
            tampil();
            BtnPrint.setEnabled(true);
        } else if (TabMutu.getSelectedIndex() == 1) {
            tampilPrioritas();
            BtnPrint.setEnabled(false);
        } else if (TabMutu.getSelectedIndex() == 2) {
            tampilNasional();
            BtnPrint.setEnabled(false);
        }
    }//GEN-LAST:event_TabMutuMouseClicked

    private void MnExportKeExcelMutuPrioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnExportKeExcelMutuPrioActionPerformed
        if (tabMode1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Data indikator mutu prioritas rumah sakit masih kosong..!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Sequel.AutoComitFalse();
            Sequel.queryu("delete from temporary");
            int row = tabMode1.getRowCount();
            for (int r = 0; r < row; r++) {
                Sequel.menyimpan("temporary", "'0','"
                    + tabMode1.getValueAt(r, 0).toString() + "','"
                    + tabMode1.getValueAt(r, 1).toString() + "','"
                    + tabMode1.getValueAt(r, 2).toString() + "','"
                    + tabMode1.getValueAt(r, 3).toString() + "','"
                    + tabMode1.getValueAt(r, 4).toString() + "','"
                    + tabMode1.getValueAt(r, 5).toString() + "','"
                    + tabMode1.getValueAt(r, 6).toString() + "','"
                    + tabMode1.getValueAt(r, 7).toString() + "','"
                    + tabMode1.getValueAt(r, 8).toString() + "','"
                    + tabMode1.getValueAt(r, 9).toString() + "','"
                    + tabMode1.getValueAt(r, 10).toString() + "','"
                    + tabMode1.getValueAt(r, 11).toString() + "','"
                    + tabMode1.getValueAt(r, 12).toString() + "','"
                    + tabMode1.getValueAt(r, 13).toString() + "','"
                    + tabMode1.getValueAt(r, 14).toString() + "','"
                    + tabMode1.getValueAt(r, 15).toString() + "','"
                    + tabMode1.getValueAt(r, 16).toString() + "','"
                    + tabMode1.getValueAt(r, 17).toString() + "','"
                    + tabMode1.getValueAt(r, 18).toString() + "','"
                    + tabMode1.getValueAt(r, 19).toString() + "','"
                    + tabMode1.getValueAt(r, 20).toString() + "','"
                    + tabMode1.getValueAt(r, 21).toString() + "','"
                    + tabMode1.getValueAt(r, 22).toString() + "','"
                    + tabMode1.getValueAt(r, 23).toString() + "','"
                    + tabMode1.getValueAt(r, 24).toString() + "','"
                    + tabMode1.getValueAt(r, 25).toString() + "','"
                    + tabMode1.getValueAt(r, 26).toString() + "','"
                    + tabMode1.getValueAt(r, 27).toString() + "','"
                    + tabMode1.getValueAt(r, 28).toString() + "','"
                    + tabMode1.getValueAt(r, 29).toString() + "','"
                    + tabMode1.getValueAt(r, 30).toString() + "','"
                    + tabMode1.getValueAt(r, 31).toString() + "','"
                    + tabMode1.getValueAt(r, 32).toString() + "','"
                    + tabMode1.getValueAt(r, 33).toString() + "','"
                    + tabMode1.getValueAt(r, 34).toString() + "','"
                    + tabMode1.getValueAt(r, 35).toString() + "',''", "Indikator Mutu Rumah Sakit");
            }
            Sequel.AutoComitTrue();
            dialog_simpan = Valid.openDialog();

            StringBuilder sb1 = new StringBuilder();
            sb1.append("select temp1 'Rg. Rawat/Inst./Unit/Bidang/Sub.', temp2 Indikator, temp3 'Jenis Indikator', temp4 'Kalimat Deskripsi', ");
            sb1.append("CONVERT(temp5,int) 'Tgl. 1', CONVERT(temp6,int) 'Tgl. 2', CONVERT(temp7,int) 'Tgl. 3', CONVERT(temp8,int) 'Tgl. 4', CONVERT(temp9,int) 'Tgl. 5', ");
            sb1.append("CONVERT(temp10,int) 'Tgl. 6', CONVERT(temp11,int) 'Tgl. 7', CONVERT(temp12,int) 'Tgl. 8', CONVERT(temp13,int) 'Tgl. 9', CONVERT(temp14,int) 'Tgl. 10', ");
            sb1.append("CONVERT(temp15,int) 'Tgl. 11', CONVERT(temp16,int) 'Tgl. 12', CONVERT(temp17,int) 'Tgl. 13', CONVERT(temp18,int) 'Tgl. 14', CONVERT(temp19,int) 'Tgl. 15', ");
            sb1.append("CONVERT(temp20,int) 'Tgl. 16', CONVERT(temp21,int) 'Tgl. 17', CONVERT(temp22,int) 'Tgl. 18', CONVERT(temp23,int) 'Tgl. 19', CONVERT(temp24,int) 'Tgl. 20', ");
            sb1.append("CONVERT(temp25,int) 'Tgl. 21', CONVERT(temp26,int) 'Tgl. 22', CONVERT(temp27,int) 'Tgl. 23', CONVERT(temp28,int) 'Tgl. 24', CONVERT(temp29,int) 'Tgl. 25', ");
            sb1.append("CONVERT(temp30,int) 'Tgl. 26', CONVERT(temp31,int) 'Tgl. 27', CONVERT(temp32,int) 'Tgl. 28', CONVERT(temp33,int) 'Tgl. 29', CONVERT(temp34,int) 'Tgl. 30', ");
            sb1.append("CONVERT(temp35,int) 'Tgl. 31', temp36 Total from temporary");
            Valid.MyReportToExcel(sb1.toString(), dialog_simpan);

            JOptionPane.showMessageDialog(null, "Data Indikator Mutu Prioritas Rumah Sakit berhasil diexport menjadi file excel,..!!!");
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnExportKeExcelMutuPrioActionPerformed

    private void MnExportKeExcelRekapPrioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnExportKeExcelRekapPrioActionPerformed
        if (tabMode3.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Rekap data indikator mutu prioritas rumah sakit masih kosong..!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Sequel.AutoComitFalse();
            Sequel.queryu("delete from temporary");
            int row = tabMode3.getRowCount();
            for (int r = 0; r < row; r++) {
                Sequel.menyimpan("temporary", "'0','"
                    + tabMode3.getValueAt(r, 0).toString() + "','"
                    + tabMode3.getValueAt(r, 1).toString() + "','"
                    + tabMode3.getValueAt(r, 2).toString().replaceAll(",", "") + "','"
                    + tabMode3.getValueAt(r, 3).toString().replaceAll(",", "") + "','"
                    + tabMode3.getValueAt(r, 4).toString().replaceAll(",", "") + "',"
                    + "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Indikator Mutu Rumah Sakit");
            }
            Sequel.AutoComitTrue();
            dialog_simpan = Valid.openDialog();

            StringBuilder sb1 = new StringBuilder();
            sb1.append("select temp1 'Rg. Rawat/Inst./Unit/Bidang/Sub.', temp2 'Indikator', convert(temp3,int) 'Jml. Numerator', ");
            sb1.append("convert(temp4,int) 'Jml. Denominator', convert(temp5,int) 'Total ND' from temporary");
            Valid.MyReportToExcel(sb1.toString(), dialog_simpan);

            JOptionPane.showMessageDialog(null, "Rekap Data Indikator Mutu Prioritas Rumah Sakit berhasil diexport menjadi file excel,..!!!");
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnExportKeExcelRekapPrioActionPerformed

    private void MnExportKeExcelMutuNasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnExportKeExcelMutuNasActionPerformed
        if (tabMode2.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Data indikator nasional mutu rumah sakit masih kosong..!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Sequel.AutoComitFalse();
            Sequel.queryu("delete from temporary");
            int row = tabMode2.getRowCount();
            for (int r = 0; r < row; r++) {
                Sequel.menyimpan("temporary", "'0','"
                    + tabMode2.getValueAt(r, 0).toString() + "','"
                    + tabMode2.getValueAt(r, 1).toString() + "','"
                    + tabMode2.getValueAt(r, 2).toString() + "','"
                    + tabMode2.getValueAt(r, 3).toString() + "','"
                    + tabMode2.getValueAt(r, 4).toString() + "','"
                    + tabMode2.getValueAt(r, 5).toString() + "','"
                    + tabMode2.getValueAt(r, 6).toString() + "','"
                    + tabMode2.getValueAt(r, 7).toString() + "','"
                    + tabMode2.getValueAt(r, 8).toString() + "','"
                    + tabMode2.getValueAt(r, 9).toString() + "','"
                    + tabMode2.getValueAt(r, 10).toString() + "','"
                    + tabMode2.getValueAt(r, 11).toString() + "','"
                    + tabMode2.getValueAt(r, 12).toString() + "','"
                    + tabMode2.getValueAt(r, 13).toString() + "','"
                    + tabMode2.getValueAt(r, 14).toString() + "','"
                    + tabMode2.getValueAt(r, 15).toString() + "','"
                    + tabMode2.getValueAt(r, 16).toString() + "','"
                    + tabMode2.getValueAt(r, 17).toString() + "','"
                    + tabMode2.getValueAt(r, 18).toString() + "','"
                    + tabMode2.getValueAt(r, 19).toString() + "','"
                    + tabMode2.getValueAt(r, 20).toString() + "','"
                    + tabMode2.getValueAt(r, 21).toString() + "','"
                    + tabMode2.getValueAt(r, 22).toString() + "','"
                    + tabMode2.getValueAt(r, 23).toString() + "','"
                    + tabMode2.getValueAt(r, 24).toString() + "','"
                    + tabMode2.getValueAt(r, 25).toString() + "','"
                    + tabMode2.getValueAt(r, 26).toString() + "','"
                    + tabMode2.getValueAt(r, 27).toString() + "','"
                    + tabMode2.getValueAt(r, 28).toString() + "','"
                    + tabMode2.getValueAt(r, 29).toString() + "','"
                    + tabMode2.getValueAt(r, 30).toString() + "','"
                    + tabMode2.getValueAt(r, 31).toString() + "','"
                    + tabMode2.getValueAt(r, 32).toString() + "','"
                    + tabMode2.getValueAt(r, 33).toString() + "','"
                    + tabMode2.getValueAt(r, 34).toString() + "','"
                    + tabMode2.getValueAt(r, 35).toString() + "',''", "Indikator Mutu Rumah Sakit");
            }
            Sequel.AutoComitTrue();
            dialog_simpan = Valid.openDialog();

            StringBuilder sb1 = new StringBuilder();
            sb1.append("select temp1 'Rg. Rawat/Inst./Unit/Bidang/Sub.', temp2 Indikator, temp3 'Jenis Indikator', temp4 'Kalimat Deskripsi', ");
            sb1.append("CONVERT(temp5,int) 'Tgl. 1', CONVERT(temp6,int) 'Tgl. 2', CONVERT(temp7,int) 'Tgl. 3', CONVERT(temp8,int) 'Tgl. 4', CONVERT(temp9,int) 'Tgl. 5', ");
            sb1.append("CONVERT(temp10,int) 'Tgl. 6', CONVERT(temp11,int) 'Tgl. 7', CONVERT(temp12,int) 'Tgl. 8', CONVERT(temp13,int) 'Tgl. 9', CONVERT(temp14,int) 'Tgl. 10', ");
            sb1.append("CONVERT(temp15,int) 'Tgl. 11', CONVERT(temp16,int) 'Tgl. 12', CONVERT(temp17,int) 'Tgl. 13', CONVERT(temp18,int) 'Tgl. 14', CONVERT(temp19,int) 'Tgl. 15', ");
            sb1.append("CONVERT(temp20,int) 'Tgl. 16', CONVERT(temp21,int) 'Tgl. 17', CONVERT(temp22,int) 'Tgl. 18', CONVERT(temp23,int) 'Tgl. 19', CONVERT(temp24,int) 'Tgl. 20', ");
            sb1.append("CONVERT(temp25,int) 'Tgl. 21', CONVERT(temp26,int) 'Tgl. 22', CONVERT(temp27,int) 'Tgl. 23', CONVERT(temp28,int) 'Tgl. 24', CONVERT(temp29,int) 'Tgl. 25', ");
            sb1.append("CONVERT(temp30,int) 'Tgl. 26', CONVERT(temp31,int) 'Tgl. 27', CONVERT(temp32,int) 'Tgl. 28', CONVERT(temp33,int) 'Tgl. 29', CONVERT(temp34,int) 'Tgl. 30', ");
            sb1.append("CONVERT(temp35,int) 'Tgl. 31', temp36 Total from temporary");
            Valid.MyReportToExcel(sb1.toString(), dialog_simpan);

            JOptionPane.showMessageDialog(null, "Data Indikator Nasional Mutu Rumah Sakit berhasil diexport menjadi file excel,..!!!");
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnExportKeExcelMutuNasActionPerformed

    private void MnExportKeExcelRekapNasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnExportKeExcelRekapNasActionPerformed
        if (tabMode4.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Rekap data indikator nasional mutu rumah sakit masih kosong..!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Sequel.AutoComitFalse();
            Sequel.queryu("delete from temporary");
            int row = tabMode4.getRowCount();
            for (int r = 0; r < row; r++) {
                Sequel.menyimpan("temporary", "'0','"
                    + tabMode4.getValueAt(r, 0).toString() + "','"
                    + tabMode4.getValueAt(r, 1).toString() + "','"
                    + tabMode4.getValueAt(r, 2).toString().replaceAll(",", "") + "','"
                    + tabMode4.getValueAt(r, 3).toString().replaceAll(",", "") + "','"
                    + tabMode4.getValueAt(r, 4).toString().replaceAll(",", "") + "',"
                    + "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Indikator Mutu Rumah Sakit");
            }
            Sequel.AutoComitTrue();
            dialog_simpan = Valid.openDialog();

            StringBuilder sb1 = new StringBuilder();
            sb1.append("select temp1 'Rg. Rawat/Inst./Unit/Bidang/Sub.', temp2 'Indikator', convert(temp3,int) 'Jml. Numerator', ");
            sb1.append("convert(temp4,int) 'Jml. Denominator', convert(temp5,int) 'Total ND' from temporary");
            Valid.MyReportToExcel(sb1.toString(), dialog_simpan);

            JOptionPane.showMessageDialog(null, "Rekap Data Indikator Nasional Mutu Rumah Sakit berhasil diexport menjadi file excel,..!!!");
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnExportKeExcelRekapNasActionPerformed

    private void MnExportKeExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnExportKeExcelActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Data indikator mutu rumah sakit belum ditampilkan pada tabel..!!");
        } else if (Ttahun.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Tahun harus diisi dulu dengan benar..!!");
            Ttahun.requestFocus();
        } else if (cmbGedung.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih salah satu ruang perawatannya dulu..!!");
            cmbGedung.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
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
                        + tabMode.getValueAt(r, 35).toString() + "','"
                        + tabMode.getValueAt(r, 36).toString() + "',''", "Indikator Mutu Rumah Sakit");
            }
            Sequel.AutoComitTrue();
            dialog_simpan = Valid.openDialog();

            StringBuilder sb1 = new StringBuilder();
            sb1.append("select temp1 'No.', temp2 Indikator, temp3 'Jenis Indikator', temp4 'Kalimat Deskripsi', ");
            sb1.append("CONVERT(temp5,int) 'Tgl. 1', CONVERT(temp6,int) 'Tgl. 2', CONVERT(temp7,int) 'Tgl. 3', CONVERT(temp8,int) 'Tgl. 4', CONVERT(temp9,int) 'Tgl. 5', ");
            sb1.append("CONVERT(temp10,int) 'Tgl. 6', CONVERT(temp11,int) 'Tgl. 7', CONVERT(temp12,int) 'Tgl. 8', CONVERT(temp13,int) 'Tgl. 9', CONVERT(temp14,int) 'Tgl. 10', ");
            sb1.append("CONVERT(temp15,int) 'Tgl. 11', CONVERT(temp16,int) 'Tgl. 12', CONVERT(temp17,int) 'Tgl. 13', CONVERT(temp18,int) 'Tgl. 14', CONVERT(temp19,int) 'Tgl. 15', ");
            sb1.append("CONVERT(temp20,int) 'Tgl. 16', CONVERT(temp21,int) 'Tgl. 17', CONVERT(temp22,int) 'Tgl. 18', CONVERT(temp23,int) 'Tgl. 19', CONVERT(temp24,int) 'Tgl. 20', ");
            sb1.append("CONVERT(temp25,int) 'Tgl. 21', CONVERT(temp26,int) 'Tgl. 22', CONVERT(temp27,int) 'Tgl. 23', CONVERT(temp28,int) 'Tgl. 24', CONVERT(temp29,int) 'Tgl. 25', ");
            sb1.append("CONVERT(temp30,int) 'Tgl. 26', CONVERT(temp31,int) 'Tgl. 27', CONVERT(temp32,int) 'Tgl. 28', CONVERT(temp33,int) 'Tgl. 29', CONVERT(temp34,int) 'Tgl. 30', ");
            sb1.append("CONVERT(temp35,int) 'Tgl. 31', temp36 Total from temporary");
            Valid.MyReportToExcel(sb1.toString(), dialog_simpan);

            JOptionPane.showMessageDialog(null, "Data Indikator Mutu Rumah Sakit berhasil diexport menjadi file excel,..!!!");
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnExportKeExcelActionPerformed

    private void MnExportKeExcelSemuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnExportKeExcelSemuaActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (Ttahun.getText().equals("")) {
            Ttahun.setText(Sequel.cariIsi("select year(now())"));
        } else {
            Ttahun.setText(Ttahun.getText());
        }

        if (cmbSttsIndikator.getSelectedIndex() == 2) {
            sttsData = "";
        } else {
            sttsData = "AND m.status_data = '" + cmbSttsIndikator.getSelectedItem().toString().toLowerCase() + "'";
        }

        dialog_simpan = Valid.openDialog();
        Valid.MyReportToExcel("SELECT inm.gedung 'Rg. Rawat/Inst./Unit/Bidang/Sub.', "
                + "CASE WHEN inm.kd_indikator LIKE '%IMU%' THEN CONCAT(m.nm_indikator, ' (IMU)') "
                + "WHEN inm.kd_indikator LIKE '%INM%' THEN CONCAT(m.nm_indikator, ' (INM)') "
                + "WHEN inm.kd_indikator IS NOT NULL AND inm.kd_indikator <> '' THEN CONCAT(m.nm_indikator, ' (', LEFT(inm.kd_indikator,3), ')') "
                + "ELSE m.nm_indikator END 'Indikator', mn.jenis_numdemon 'Jenis Indikator', mn.nm_numdemon 'Kalimat Deskripsi', "
                + "  SUM(CASE WHEN DAY(inm.tgl_catat)=1  THEN COALESCE(inm.jumlah_pertanggal,0) ELSE 0 END) 'Tgl. 1', "
                + "  SUM(CASE WHEN DAY(inm.tgl_catat)=2  THEN COALESCE(inm.jumlah_pertanggal,0) ELSE 0 END) 'Tgl. 2', "
                + "  SUM(CASE WHEN DAY(inm.tgl_catat)=3  THEN COALESCE(inm.jumlah_pertanggal,0) ELSE 0 END) 'Tgl. 3', "
                + "  SUM(CASE WHEN DAY(inm.tgl_catat)=4  THEN COALESCE(inm.jumlah_pertanggal,0) ELSE 0 END) 'Tgl. 4', "
                + "  SUM(CASE WHEN DAY(inm.tgl_catat)=5  THEN COALESCE(inm.jumlah_pertanggal,0) ELSE 0 END) 'Tgl. 5', "
                + "  SUM(CASE WHEN DAY(inm.tgl_catat)=6  THEN COALESCE(inm.jumlah_pertanggal,0) ELSE 0 END) 'Tgl. 6', "
                + "  SUM(CASE WHEN DAY(inm.tgl_catat)=7  THEN COALESCE(inm.jumlah_pertanggal,0) ELSE 0 END) 'Tgl. 7', "
                + "  SUM(CASE WHEN DAY(inm.tgl_catat)=8  THEN COALESCE(inm.jumlah_pertanggal,0) ELSE 0 END) 'Tgl. 8', "
                + "  SUM(CASE WHEN DAY(inm.tgl_catat)=9  THEN COALESCE(inm.jumlah_pertanggal,0) ELSE 0 END) 'Tgl. 9', "
                + "  SUM(CASE WHEN DAY(inm.tgl_catat)=10 THEN COALESCE(inm.jumlah_pertanggal,0) ELSE 0 END) 'Tgl. 10', "
                + "  SUM(CASE WHEN DAY(inm.tgl_catat)=11 THEN COALESCE(inm.jumlah_pertanggal,0) ELSE 0 END) 'Tgl. 11', "
                + "  SUM(CASE WHEN DAY(inm.tgl_catat)=12 THEN COALESCE(inm.jumlah_pertanggal,0) ELSE 0 END) 'Tgl. 12', "
                + "  SUM(CASE WHEN DAY(inm.tgl_catat)=13 THEN COALESCE(inm.jumlah_pertanggal,0) ELSE 0 END) 'Tgl. 13', "
                + "  SUM(CASE WHEN DAY(inm.tgl_catat)=14 THEN COALESCE(inm.jumlah_pertanggal,0) ELSE 0 END) 'Tgl. 14', "
                + "  SUM(CASE WHEN DAY(inm.tgl_catat)=15 THEN COALESCE(inm.jumlah_pertanggal,0) ELSE 0 END) 'Tgl. 15', "
                + "  SUM(CASE WHEN DAY(inm.tgl_catat)=16 THEN COALESCE(inm.jumlah_pertanggal,0) ELSE 0 END) 'Tgl. 16', "
                + "  SUM(CASE WHEN DAY(inm.tgl_catat)=17 THEN COALESCE(inm.jumlah_pertanggal,0) ELSE 0 END) 'Tgl. 17', "
                + "  SUM(CASE WHEN DAY(inm.tgl_catat)=18 THEN COALESCE(inm.jumlah_pertanggal,0) ELSE 0 END) 'Tgl. 18', "
                + "  SUM(CASE WHEN DAY(inm.tgl_catat)=19 THEN COALESCE(inm.jumlah_pertanggal,0) ELSE 0 END) 'Tgl. 19', "
                + "  SUM(CASE WHEN DAY(inm.tgl_catat)=20 THEN COALESCE(inm.jumlah_pertanggal,0) ELSE 0 END) 'Tgl. 20', "
                + "  SUM(CASE WHEN DAY(inm.tgl_catat)=21 THEN COALESCE(inm.jumlah_pertanggal,0) ELSE 0 END) 'Tgl. 21', "
                + "  SUM(CASE WHEN DAY(inm.tgl_catat)=22 THEN COALESCE(inm.jumlah_pertanggal,0) ELSE 0 END) 'Tgl. 22', "
                + "  SUM(CASE WHEN DAY(inm.tgl_catat)=23 THEN COALESCE(inm.jumlah_pertanggal,0) ELSE 0 END) 'Tgl. 23', "
                + "  SUM(CASE WHEN DAY(inm.tgl_catat)=24 THEN COALESCE(inm.jumlah_pertanggal,0) ELSE 0 END) 'Tgl. 24', "
                + "  SUM(CASE WHEN DAY(inm.tgl_catat)=25 THEN COALESCE(inm.jumlah_pertanggal,0) ELSE 0 END) 'Tgl. 25', "
                + "  SUM(CASE WHEN DAY(inm.tgl_catat)=26 THEN COALESCE(inm.jumlah_pertanggal,0) ELSE 0 END) 'Tgl. 26', "
                + "  SUM(CASE WHEN DAY(inm.tgl_catat)=27 THEN COALESCE(inm.jumlah_pertanggal,0) ELSE 0 END) 'Tgl. 27', "
                + "  SUM(CASE WHEN DAY(inm.tgl_catat)=28 THEN COALESCE(inm.jumlah_pertanggal,0) ELSE 0 END) 'Tgl. 28', "
                + "  SUM(CASE WHEN DAY(inm.tgl_catat)=29 THEN COALESCE(inm.jumlah_pertanggal,0) ELSE 0 END) 'Tgl. 29', "
                + "  SUM(CASE WHEN DAY(inm.tgl_catat)=30 THEN COALESCE(inm.jumlah_pertanggal,0) ELSE 0 END) 'Tgl. 30', "
                + "  SUM(CASE WHEN DAY(inm.tgl_catat)=31 THEN COALESCE(inm.jumlah_pertanggal,0) ELSE 0 END) 'Tgl. 31', "
                + "  COALESCE(SUM(inm.jumlah_pertanggal),0) 'Total' FROM indikator_nasional_mutu inm "
                + "INNER JOIN master_indikator_nasional_mutu m ON m.kd_indikator = inm.kd_indikator "
                + "INNER JOIN master_numdemon_indikator_nasional_mutu mn ON mn.kd_numdemon = inm.kd_numdemon WHERE "
                + "MONTH(inm.tgl_catat) = '" + angkaBulan + "' AND YEAR(inm.tgl_catat) = '" + Ttahun.getText() + "' " + sttsData + " "
                + "AND m.tujuan_aktivasi = '" + cmbJnsData.getSelectedItem().toString() + "' "
                + "GROUP BY inm.gedung, inm.kd_indikator, m.nm_indikator, mn.kd_numdemon, mn.nm_numdemon, mn.jenis_numdemon, MONTH(inm.tgl_catat), YEAR(inm.tgl_catat) "
                + "ORDER BY inm.gedung, m.no_urut, mn.no_urut", dialog_simpan);

        JOptionPane.showMessageDialog(null, "Data indikator mutu layanan semua unit berhasil diexport menjadi file excel,..!!!");
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnExportKeExcelSemuaActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgLaporanIndikatorMutu dialog = new DlgLaporanIndikatorMutu(new javax.swing.JFrame(), true);
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
    private widget.Button BtnMasterIndikator;
    private widget.Button BtnPrint;
    private javax.swing.JMenuItem MnExportKeExcel;
    private javax.swing.JMenuItem MnExportKeExcelMutuNas;
    private javax.swing.JMenuItem MnExportKeExcelMutuPrio;
    private javax.swing.JMenuItem MnExportKeExcelRekapNas;
    private javax.swing.JMenuItem MnExportKeExcelRekapPrio;
    private javax.swing.JMenuItem MnExportKeExcelSemua;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll5;
    private widget.ScrollPane Scroll6;
    private widget.ScrollPane Scroll7;
    private javax.swing.JTabbedPane TabMutu;
    private widget.TextBox Ttahun;
    private widget.ComboBox cmbBulan;
    private widget.ComboBox cmbGedung;
    private widget.ComboBox cmbJnsData;
    private widget.ComboBox cmbSttsIndikator;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel29;
    private widget.Label jLabel35;
    private widget.Label jLabel39;
    private widget.Label jLabel40;
    private widget.Label jLabel6;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu0;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.Table tbIndikator;
    private widget.Table tbNasional;
    private widget.Table tbPrioritas;
    private widget.Table tbTotNasional;
    private widget.Table tbTotPrioritas;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        sttsData = "";
        Scroll.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
                ".: Data Indikator Mutu Rumah Sakit Bulan " + cmbBulan.getSelectedItem().toString() + " Tahun " + Ttahun.getText() + " Rg. Perawatan/Unit/Inst./Bidang/Sub " + cmbGedung.getSelectedItem().toString() + " :.",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new java.awt.Font("Tahoma", 0, 13)));

        if (Ttahun.getText().equals("")) {
            Ttahun.setText(Sequel.cariIsi("select year(now())"));
        } else {
            Ttahun.setText(Ttahun.getText());
        }

        if (cmbSttsIndikator.getSelectedIndex() == 2) {
            sttsData = "";
        } else {
            sttsData = "AND m.status_data = '" + cmbSttsIndikator.getSelectedItem().toString().toLowerCase() + "'";
        }

        hitungTot = 0;
        Valid.tabelKosong(tabMode);
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT inm.*, m.no_urut urutInm, mn.no_urut, CASE WHEN inm.kd_indikator LIKE '%IMU%' THEN CONCAT(m.nm_indikator,' (IMU)') ");
            sb.append("WHEN inm.kd_indikator LIKE '%INM%' THEN CONCAT(m.nm_indikator,' (INM)') ");
            sb.append("WHEN inm.kd_indikator IS NOT NULL AND inm.kd_indikator<>'' THEN CONCAT(m.nm_indikator, ' (', LEFT(inm.kd_indikator, 3), ')') ELSE m.nm_indikator END AS nm_indikator, ");
            sb.append("mn.nm_numdemon, MONTH(inm.tgl_catat) bln, YEAR(inm.tgl_catat) thn, mn.jenis_numdemon ,");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=1 THEN inm.jumlah_pertanggal END),'0') tgl1, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=2 THEN inm.jumlah_pertanggal END),'0') tgl2, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=3 THEN inm.jumlah_pertanggal END),'0') tgl3, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=4 THEN inm.jumlah_pertanggal END),'0') tgl4, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=5 THEN inm.jumlah_pertanggal END),'0') tgl5, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=6 THEN inm.jumlah_pertanggal END),'0') tgl6, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=7 THEN inm.jumlah_pertanggal END),'0') tgl7, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=8 THEN inm.jumlah_pertanggal END),'0') tgl8, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=9 THEN inm.jumlah_pertanggal END),'0') tgl9, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=10 THEN inm.jumlah_pertanggal END),'0') tgl10, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=11 THEN inm.jumlah_pertanggal END),'0') tgl11, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=12 THEN inm.jumlah_pertanggal END),'0') tgl12, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=13 THEN inm.jumlah_pertanggal END),'0') tgl13, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=14 THEN inm.jumlah_pertanggal END),'0') tgl14, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=15 THEN inm.jumlah_pertanggal END),'0') tgl15, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=16 THEN inm.jumlah_pertanggal END),'0') tgl16, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=17 THEN inm.jumlah_pertanggal END),'0') tgl17, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=18 THEN inm.jumlah_pertanggal END),'0') tgl18, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=19 THEN inm.jumlah_pertanggal END),'0') tgl19, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=20 THEN inm.jumlah_pertanggal END),'0') tgl20, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=21 THEN inm.jumlah_pertanggal END),'0') tgl21, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=22 THEN inm.jumlah_pertanggal END),'0') tgl22, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=23 THEN inm.jumlah_pertanggal END),'0') tgl23, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=24 THEN inm.jumlah_pertanggal END),'0') tgl24, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=25 THEN inm.jumlah_pertanggal END),'0') tgl25, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=26 THEN inm.jumlah_pertanggal END),'0') tgl26, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=27 THEN inm.jumlah_pertanggal END),'0') tgl27, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=28 THEN inm.jumlah_pertanggal END),'0') tgl28, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=29 THEN inm.jumlah_pertanggal END),'0') tgl29, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=30 THEN inm.jumlah_pertanggal END),'0') tgl30, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=31 THEN inm.jumlah_pertanggal END),'0') tgl31 ");
            sb.append("from indikator_nasional_mutu inm INNER JOIN master_indikator_nasional_mutu m on m.kd_indikator=inm.kd_indikator ");
            sb.append("inner join master_numdemon_indikator_nasional_mutu mn on mn.kd_numdemon=inm.kd_numdemon where ");
            sb.append("MONTH(inm.tgl_catat)='" + angkaBulan + "' and YEAR(inm.tgl_catat)='" + Ttahun.getText() + "' ");
            sb.append("and inm.gedung='" + cmbGedung.getSelectedItem().toString() + "' " + sttsData + " ");
            sb.append("and m.tujuan_aktivasi='" + cmbJnsData.getSelectedItem().toString() + "' ");
            sb.append("GROUP BY inm.kd_indikator, mn.kd_numdemon, MONTH(inm.tgl_catat), YEAR(inm.tgl_catat), inm.gedung ");
            sb.append("order by m.no_urut, mn.no_urut, CASE WHEN LOWER(mn.jenis_numdemon) = 'numerator' THEN 1 WHEN LOWER(mn.jenis_numdemon) = 'denominator' THEN 2 ELSE 3 END");
            ps = koneksi.prepareStatement(sb.toString());
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
                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=1 and "
                            + "MONTH(tgl_catat)='" + rs.getString("bln") + "' and YEAR(tgl_catat)='" + rs.getString("thn") + "' "
                            + "and gedung='" + rs.getString("gedung") + "' and kd_numdemon='" + rs.getString("kd_numdemon") + "'") == 0) {
                        tgl1 = "";
                    } else {
                        tgl1 = rs.getString("tgl1");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=2 and "
                            + "MONTH(tgl_catat)='" + rs.getString("bln") + "' and YEAR(tgl_catat)='" + rs.getString("thn") + "' "
                            + "and gedung='" + rs.getString("gedung") + "' and kd_numdemon='" + rs.getString("kd_numdemon") + "'") == 0) {
                        tgl2 = "";
                    } else {
                        tgl2 = rs.getString("tgl2");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=3 and "
                            + "MONTH(tgl_catat)='" + rs.getString("bln") + "' and YEAR(tgl_catat)='" + rs.getString("thn") + "' "
                            + "and gedung='" + rs.getString("gedung") + "' and kd_numdemon='" + rs.getString("kd_numdemon") + "'") == 0) {
                        tgl3 = "";
                    } else {
                        tgl3 = rs.getString("tgl3");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=4 and "
                            + "MONTH(tgl_catat)='" + rs.getString("bln") + "' and YEAR(tgl_catat)='" + rs.getString("thn") + "' "
                            + "and gedung='" + rs.getString("gedung") + "' and kd_numdemon='" + rs.getString("kd_numdemon") + "'") == 0) {
                        tgl4 = "";
                    } else {
                        tgl4 = rs.getString("tgl4");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=5 and "
                            + "MONTH(tgl_catat)='" + rs.getString("bln") + "' and YEAR(tgl_catat)='" + rs.getString("thn") + "' "
                            + "and gedung='" + rs.getString("gedung") + "' and kd_numdemon='" + rs.getString("kd_numdemon") + "'") == 0) {
                        tgl5 = "";
                    } else {
                        tgl5 = rs.getString("tgl5");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=6 and "
                            + "MONTH(tgl_catat)='" + rs.getString("bln") + "' and YEAR(tgl_catat)='" + rs.getString("thn") + "' "
                            + "and gedung='" + rs.getString("gedung") + "' and kd_numdemon='" + rs.getString("kd_numdemon") + "'") == 0) {
                        tgl6 = "";
                    } else {
                        tgl6 = rs.getString("tgl6");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=7 and "
                            + "MONTH(tgl_catat)='" + rs.getString("bln") + "' and YEAR(tgl_catat)='" + rs.getString("thn") + "' "
                            + "and gedung='" + rs.getString("gedung") + "' and kd_numdemon='" + rs.getString("kd_numdemon") + "'") == 0) {
                        tgl7 = "";
                    } else {
                        tgl7 = rs.getString("tgl7");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=8 and "
                            + "MONTH(tgl_catat)='" + rs.getString("bln") + "' and YEAR(tgl_catat)='" + rs.getString("thn") + "' "
                            + "and gedung='" + rs.getString("gedung") + "' and kd_numdemon='" + rs.getString("kd_numdemon") + "'") == 0) {
                        tgl8 = "";
                    } else {
                        tgl8 = rs.getString("tgl8");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=9 and "
                            + "MONTH(tgl_catat)='" + rs.getString("bln") + "' and YEAR(tgl_catat)='" + rs.getString("thn") + "' "
                            + "and gedung='" + rs.getString("gedung") + "' and kd_numdemon='" + rs.getString("kd_numdemon") + "'") == 0) {
                        tgl9 = "";
                    } else {
                        tgl9 = rs.getString("tgl9");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=10 and "
                            + "MONTH(tgl_catat)='" + rs.getString("bln") + "' and YEAR(tgl_catat)='" + rs.getString("thn") + "' "
                            + "and gedung='" + rs.getString("gedung") + "' and kd_numdemon='" + rs.getString("kd_numdemon") + "'") == 0) {
                        tgl10 = "";
                    } else {
                        tgl10 = rs.getString("tgl10");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=11 and "
                            + "MONTH(tgl_catat)='" + rs.getString("bln") + "' and YEAR(tgl_catat)='" + rs.getString("thn") + "' "
                            + "and gedung='" + rs.getString("gedung") + "' and kd_numdemon='" + rs.getString("kd_numdemon") + "'") == 0) {
                        tgl11 = "";
                    } else {
                        tgl11 = rs.getString("tgl11");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=12 and "
                            + "MONTH(tgl_catat)='" + rs.getString("bln") + "' and YEAR(tgl_catat)='" + rs.getString("thn") + "' "
                            + "and gedung='" + rs.getString("gedung") + "' and kd_numdemon='" + rs.getString("kd_numdemon") + "'") == 0) {
                        tgl12 = "";
                    } else {
                        tgl12 = rs.getString("tgl12");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=13 and "
                            + "MONTH(tgl_catat)='" + rs.getString("bln") + "' and YEAR(tgl_catat)='" + rs.getString("thn") + "' "
                            + "and gedung='" + rs.getString("gedung") + "' and kd_numdemon='" + rs.getString("kd_numdemon") + "'") == 0) {
                        tgl13 = "";
                    } else {
                        tgl13 = rs.getString("tgl13");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=14 and "
                            + "MONTH(tgl_catat)='" + rs.getString("bln") + "' and YEAR(tgl_catat)='" + rs.getString("thn") + "' "
                            + "and gedung='" + rs.getString("gedung") + "' and kd_numdemon='" + rs.getString("kd_numdemon") + "'") == 0) {
                        tgl14 = "";
                    } else {
                        tgl14 = rs.getString("tgl14");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=15 and "
                            + "MONTH(tgl_catat)='" + rs.getString("bln") + "' and YEAR(tgl_catat)='" + rs.getString("thn") + "' "
                            + "and gedung='" + rs.getString("gedung") + "' and kd_numdemon='" + rs.getString("kd_numdemon") + "'") == 0) {
                        tgl15 = "";
                    } else {
                        tgl15 = rs.getString("tgl15");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=16 and "
                            + "MONTH(tgl_catat)='" + rs.getString("bln") + "' and YEAR(tgl_catat)='" + rs.getString("thn") + "' "
                            + "and gedung='" + rs.getString("gedung") + "' and kd_numdemon='" + rs.getString("kd_numdemon") + "'") == 0) {
                        tgl16 = "";
                    } else {
                        tgl16 = rs.getString("tgl16");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=17 and "
                            + "MONTH(tgl_catat)='" + rs.getString("bln") + "' and YEAR(tgl_catat)='" + rs.getString("thn") + "' "
                            + "and gedung='" + rs.getString("gedung") + "' and kd_numdemon='" + rs.getString("kd_numdemon") + "'") == 0) {
                        tgl17 = "";
                    } else {
                        tgl17 = rs.getString("tgl17");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=18 and "
                            + "MONTH(tgl_catat)='" + rs.getString("bln") + "' and YEAR(tgl_catat)='" + rs.getString("thn") + "' "
                            + "and gedung='" + rs.getString("gedung") + "' and kd_numdemon='" + rs.getString("kd_numdemon") + "'") == 0) {
                        tgl18 = "";
                    } else {
                        tgl18 = rs.getString("tgl18");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=19 and "
                            + "MONTH(tgl_catat)='" + rs.getString("bln") + "' and YEAR(tgl_catat)='" + rs.getString("thn") + "' "
                            + "and gedung='" + rs.getString("gedung") + "' and kd_numdemon='" + rs.getString("kd_numdemon") + "'") == 0) {
                        tgl19 = "";
                    } else {
                        tgl19 = rs.getString("tgl19");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=20 and "
                            + "MONTH(tgl_catat)='" + rs.getString("bln") + "' and YEAR(tgl_catat)='" + rs.getString("thn") + "' "
                            + "and gedung='" + rs.getString("gedung") + "' and kd_numdemon='" + rs.getString("kd_numdemon") + "'") == 0) {
                        tgl20 = "";
                    } else {
                        tgl20 = rs.getString("tgl20");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=21 and "
                            + "MONTH(tgl_catat)='" + rs.getString("bln") + "' and YEAR(tgl_catat)='" + rs.getString("thn") + "' "
                            + "and gedung='" + rs.getString("gedung") + "' and kd_numdemon='" + rs.getString("kd_numdemon") + "'") == 0) {
                        tgl21 = "";
                    } else {
                        tgl21 = rs.getString("tgl21");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=22 and "
                            + "MONTH(tgl_catat)='" + rs.getString("bln") + "' and YEAR(tgl_catat)='" + rs.getString("thn") + "' "
                            + "and gedung='" + rs.getString("gedung") + "' and kd_numdemon='" + rs.getString("kd_numdemon") + "'") == 0) {
                        tgl22 = "";
                    } else {
                        tgl22 = rs.getString("tgl22");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=23 and "
                            + "MONTH(tgl_catat)='" + rs.getString("bln") + "' and YEAR(tgl_catat)='" + rs.getString("thn") + "' "
                            + "and gedung='" + rs.getString("gedung") + "' and kd_numdemon='" + rs.getString("kd_numdemon") + "'") == 0) {
                        tgl23 = "";
                    } else {
                        tgl23 = rs.getString("tgl23");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=24 and "
                            + "MONTH(tgl_catat)='" + rs.getString("bln") + "' and YEAR(tgl_catat)='" + rs.getString("thn") + "' "
                            + "and gedung='" + rs.getString("gedung") + "' and kd_numdemon='" + rs.getString("kd_numdemon") + "'") == 0) {
                        tgl24 = "";
                    } else {
                        tgl24 = rs.getString("tgl24");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=25 and "
                            + "MONTH(tgl_catat)='" + rs.getString("bln") + "' and YEAR(tgl_catat)='" + rs.getString("thn") + "' "
                            + "and gedung='" + rs.getString("gedung") + "' and kd_numdemon='" + rs.getString("kd_numdemon") + "'") == 0) {
                        tgl25 = "";
                    } else {
                        tgl25 = rs.getString("tgl25");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=26 and "
                            + "MONTH(tgl_catat)='" + rs.getString("bln") + "' and YEAR(tgl_catat)='" + rs.getString("thn") + "' "
                            + "and gedung='" + rs.getString("gedung") + "' and kd_numdemon='" + rs.getString("kd_numdemon") + "'") == 0) {
                        tgl26 = "";
                    } else {
                        tgl26 = rs.getString("tgl26");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=27 and "
                            + "MONTH(tgl_catat)='" + rs.getString("bln") + "' and YEAR(tgl_catat)='" + rs.getString("thn") + "' "
                            + "and gedung='" + rs.getString("gedung") + "' and kd_numdemon='" + rs.getString("kd_numdemon") + "'") == 0) {
                        tgl27 = "";
                    } else {
                        tgl27 = rs.getString("tgl27");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=28 and "
                            + "MONTH(tgl_catat)='" + rs.getString("bln") + "' and YEAR(tgl_catat)='" + rs.getString("thn") + "' "
                            + "and gedung='" + rs.getString("gedung") + "' and kd_numdemon='" + rs.getString("kd_numdemon") + "'") == 0) {
                        tgl28 = "";
                    } else {
                        tgl28 = rs.getString("tgl28");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=29 and "
                            + "MONTH(tgl_catat)='" + rs.getString("bln") + "' and YEAR(tgl_catat)='" + rs.getString("thn") + "' "
                            + "and gedung='" + rs.getString("gedung") + "' and kd_numdemon='" + rs.getString("kd_numdemon") + "'") == 0) {
                        tgl29 = "";
                    } else {
                        tgl29 = rs.getString("tgl29");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=30 and "
                            + "MONTH(tgl_catat)='" + rs.getString("bln") + "' and YEAR(tgl_catat)='" + rs.getString("thn") + "' "
                            + "and gedung='" + rs.getString("gedung") + "' and kd_numdemon='" + rs.getString("kd_numdemon") + "'") == 0) {
                        tgl30 = "";
                    } else {
                        tgl30 = rs.getString("tgl30");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=31 and "
                            + "MONTH(tgl_catat)='" + rs.getString("bln") + "' and YEAR(tgl_catat)='" + rs.getString("thn") + "' "
                            + "and gedung='" + rs.getString("gedung") + "' and kd_numdemon='" + rs.getString("kd_numdemon") + "'") == 0) {
                        tgl31 = "";
                    } else {
                        tgl31 = rs.getString("tgl31");
                    }
                    
                    tabMode.addRow(new String[]{
                        rs.getString("gedung"),
                        rs.getString("urutInm") + ".",
                        rs.getString("nm_indikator"),                        
                        rs.getString("jenis_numdemon"),
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
    
    public void isCek() {
        BtnMasterIndikator.setEnabled(akses.getpic_kmkp());
    }

    private void tampilPrioritas() {
        sttsData = "";
        pakaiGedung = "";
        if (Ttahun.getText().equals("")) {
            Ttahun.setText(Sequel.cariIsi("select year(now())"));
        } else {
            Ttahun.setText(Ttahun.getText());
        }

        if (cmbSttsIndikator.getSelectedIndex() == 2) {
            sttsData = "";
        } else {
            sttsData = "AND m.status_data = '" + cmbSttsIndikator.getSelectedItem().toString().toLowerCase() + "'";
        }
        
        if (cmbGedung.getSelectedIndex() == 0) {
            pakaiGedung = "";
        } else {
            pakaiGedung = "and inm.gedung='" + cmbGedung.getSelectedItem().toString() + "'";
        }

        hitungTot = 0;
        Valid.tabelKosong(tabMode1);
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT inm.*, m.no_urut urutInm, mn.no_urut, CASE WHEN inm.kd_indikator LIKE '%IMU%' THEN CONCAT(m.nm_indikator,' (IMU)') ");
            sb.append("WHEN inm.kd_indikator LIKE '%INM%' THEN CONCAT(m.nm_indikator,' (INM)') ");
            sb.append("WHEN inm.kd_indikator IS NOT NULL AND inm.kd_indikator<>'' THEN CONCAT(m.nm_indikator, ' (', LEFT(inm.kd_indikator, 3), ')') ELSE m.nm_indikator END AS nm_indikator, ");
            sb.append("mn.nm_numdemon, MONTH(inm.tgl_catat) bln, YEAR(inm.tgl_catat) thn, mn.jenis_numdemon ,");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=1 THEN inm.jumlah_pertanggal END),'0') tgl1, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=2 THEN inm.jumlah_pertanggal END),'0') tgl2, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=3 THEN inm.jumlah_pertanggal END),'0') tgl3, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=4 THEN inm.jumlah_pertanggal END),'0') tgl4, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=5 THEN inm.jumlah_pertanggal END),'0') tgl5, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=6 THEN inm.jumlah_pertanggal END),'0') tgl6, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=7 THEN inm.jumlah_pertanggal END),'0') tgl7, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=8 THEN inm.jumlah_pertanggal END),'0') tgl8, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=9 THEN inm.jumlah_pertanggal END),'0') tgl9, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=10 THEN inm.jumlah_pertanggal END),'0') tgl10, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=11 THEN inm.jumlah_pertanggal END),'0') tgl11, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=12 THEN inm.jumlah_pertanggal END),'0') tgl12, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=13 THEN inm.jumlah_pertanggal END),'0') tgl13, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=14 THEN inm.jumlah_pertanggal END),'0') tgl14, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=15 THEN inm.jumlah_pertanggal END),'0') tgl15, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=16 THEN inm.jumlah_pertanggal END),'0') tgl16, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=17 THEN inm.jumlah_pertanggal END),'0') tgl17, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=18 THEN inm.jumlah_pertanggal END),'0') tgl18, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=19 THEN inm.jumlah_pertanggal END),'0') tgl19, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=20 THEN inm.jumlah_pertanggal END),'0') tgl20, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=21 THEN inm.jumlah_pertanggal END),'0') tgl21, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=22 THEN inm.jumlah_pertanggal END),'0') tgl22, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=23 THEN inm.jumlah_pertanggal END),'0') tgl23, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=24 THEN inm.jumlah_pertanggal END),'0') tgl24, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=25 THEN inm.jumlah_pertanggal END),'0') tgl25, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=26 THEN inm.jumlah_pertanggal END),'0') tgl26, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=27 THEN inm.jumlah_pertanggal END),'0') tgl27, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=28 THEN inm.jumlah_pertanggal END),'0') tgl28, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=29 THEN inm.jumlah_pertanggal END),'0') tgl29, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=30 THEN inm.jumlah_pertanggal END),'0') tgl30, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=31 THEN inm.jumlah_pertanggal END),'0') tgl31 ");
            sb.append("from indikator_nasional_mutu inm INNER JOIN master_indikator_nasional_mutu m on m.kd_indikator=inm.kd_indikator ");
            sb.append("inner join master_numdemon_indikator_nasional_mutu mn on mn.kd_numdemon=inm.kd_numdemon where ");
            sb.append("MONTH(inm.tgl_catat)='" + angkaBulan + "' and YEAR(inm.tgl_catat)='" + Ttahun.getText() + "' " + pakaiGedung + " ");
            sb.append("and m.jenis_indikator='Mutu Prioritas RS' " + sttsData + " and m.tujuan_aktivasi='" + cmbJnsData.getSelectedItem().toString() + "' ");
            sb.append("GROUP BY inm.kd_indikator, mn.kd_numdemon, MONTH(inm.tgl_catat), YEAR(inm.tgl_catat), inm.gedung ");
            sb.append("order by m.no_urut, mn.no_urut, CASE WHEN LOWER(mn.jenis_numdemon) = 'numerator' THEN 1 WHEN LOWER(mn.jenis_numdemon) = 'denominator' THEN 2 ELSE 3 END");
            ps1 = koneksi.prepareStatement(sb.toString());
            try {
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    try {
                        hitungTot = Double.parseDouble(rs1.getString("tgl1"))
                                + Double.parseDouble(rs1.getString("tgl2"))
                                + Double.parseDouble(rs1.getString("tgl3"))
                                + Double.parseDouble(rs1.getString("tgl4"))
                                + Double.parseDouble(rs1.getString("tgl5"))
                                + Double.parseDouble(rs1.getString("tgl6"))
                                + Double.parseDouble(rs1.getString("tgl7"))
                                + Double.parseDouble(rs1.getString("tgl8"))
                                + Double.parseDouble(rs1.getString("tgl9"))
                                + Double.parseDouble(rs1.getString("tgl10"))
                                + Double.parseDouble(rs1.getString("tgl11"))
                                + Double.parseDouble(rs1.getString("tgl12"))
                                + Double.parseDouble(rs1.getString("tgl13"))
                                + Double.parseDouble(rs1.getString("tgl14"))
                                + Double.parseDouble(rs1.getString("tgl15"))
                                + Double.parseDouble(rs1.getString("tgl16"))
                                + Double.parseDouble(rs1.getString("tgl17"))
                                + Double.parseDouble(rs1.getString("tgl18"))
                                + Double.parseDouble(rs1.getString("tgl19"))
                                + Double.parseDouble(rs1.getString("tgl20"))
                                + Double.parseDouble(rs1.getString("tgl21"))
                                + Double.parseDouble(rs1.getString("tgl22"))
                                + Double.parseDouble(rs1.getString("tgl23"))
                                + Double.parseDouble(rs1.getString("tgl24"))
                                + Double.parseDouble(rs1.getString("tgl25"))
                                + Double.parseDouble(rs1.getString("tgl26"))
                                + Double.parseDouble(rs1.getString("tgl27"))
                                + Double.parseDouble(rs1.getString("tgl28"))
                                + Double.parseDouble(rs1.getString("tgl29"))
                                + Double.parseDouble(rs1.getString("tgl30"))
                                + Double.parseDouble(rs1.getString("tgl31"));
                        total = Valid.SetAngka2(hitungTot);
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                        total = "error";
                    }

                    //cek angka jumlah
                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=1 and "
                            + "MONTH(tgl_catat)='" + rs1.getString("bln") + "' and YEAR(tgl_catat)='" + rs1.getString("thn") + "' "
                            + "and kd_numdemon='" + rs1.getString("kd_numdemon") + "'") == 0) {
                        tgl1 = "";
                    } else {
                        tgl1 = rs1.getString("tgl1");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=2 and "
                            + "MONTH(tgl_catat)='" + rs1.getString("bln") + "' and YEAR(tgl_catat)='" + rs1.getString("thn") + "' "
                            + "and kd_numdemon='" + rs1.getString("kd_numdemon") + "'") == 0) {
                        tgl2 = "";
                    } else {
                        tgl2 = rs1.getString("tgl2");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=3 and "
                            + "MONTH(tgl_catat)='" + rs1.getString("bln") + "' and YEAR(tgl_catat)='" + rs1.getString("thn") + "' "
                            + "and kd_numdemon='" + rs1.getString("kd_numdemon") + "'") == 0) {
                        tgl3 = "";
                    } else {
                        tgl3 = rs1.getString("tgl3");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=4 and "
                            + "MONTH(tgl_catat)='" + rs1.getString("bln") + "' and YEAR(tgl_catat)='" + rs1.getString("thn") + "' "
                            + "and kd_numdemon='" + rs1.getString("kd_numdemon") + "'") == 0) {
                        tgl4 = "";
                    } else {
                        tgl4 = rs1.getString("tgl4");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=5 and "
                            + "MONTH(tgl_catat)='" + rs1.getString("bln") + "' and YEAR(tgl_catat)='" + rs1.getString("thn") + "' "
                            + "and kd_numdemon='" + rs1.getString("kd_numdemon") + "'") == 0) {
                        tgl5 = "";
                    } else {
                        tgl5 = rs1.getString("tgl5");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=6 and "
                            + "MONTH(tgl_catat)='" + rs1.getString("bln") + "' and YEAR(tgl_catat)='" + rs1.getString("thn") + "' "
                            + "and kd_numdemon='" + rs1.getString("kd_numdemon") + "'") == 0) {
                        tgl6 = "";
                    } else {
                        tgl6 = rs1.getString("tgl6");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=7 and "
                            + "MONTH(tgl_catat)='" + rs1.getString("bln") + "' and YEAR(tgl_catat)='" + rs1.getString("thn") + "' "
                            + "and kd_numdemon='" + rs1.getString("kd_numdemon") + "'") == 0) {
                        tgl7 = "";
                    } else {
                        tgl7 = rs1.getString("tgl7");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=8 and "
                            + "MONTH(tgl_catat)='" + rs1.getString("bln") + "' and YEAR(tgl_catat)='" + rs1.getString("thn") + "' "
                            + "and kd_numdemon='" + rs1.getString("kd_numdemon") + "'") == 0) {
                        tgl8 = "";
                    } else {
                        tgl8 = rs1.getString("tgl8");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=9 and "
                            + "MONTH(tgl_catat)='" + rs1.getString("bln") + "' and YEAR(tgl_catat)='" + rs1.getString("thn") + "' "
                            + "and kd_numdemon='" + rs1.getString("kd_numdemon") + "'") == 0) {
                        tgl9 = "";
                    } else {
                        tgl9 = rs1.getString("tgl9");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=10 and "
                            + "MONTH(tgl_catat)='" + rs1.getString("bln") + "' and YEAR(tgl_catat)='" + rs1.getString("thn") + "' "
                            + "and kd_numdemon='" + rs1.getString("kd_numdemon") + "'") == 0) {
                        tgl10 = "";
                    } else {
                        tgl10 = rs1.getString("tgl10");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=11 and "
                            + "MONTH(tgl_catat)='" + rs1.getString("bln") + "' and YEAR(tgl_catat)='" + rs1.getString("thn") + "' "
                            + "and kd_numdemon='" + rs1.getString("kd_numdemon") + "'") == 0) {
                        tgl11 = "";
                    } else {
                        tgl11 = rs1.getString("tgl11");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=12 and "
                            + "MONTH(tgl_catat)='" + rs1.getString("bln") + "' and YEAR(tgl_catat)='" + rs1.getString("thn") + "' "
                            + "and kd_numdemon='" + rs1.getString("kd_numdemon") + "'") == 0) {
                        tgl12 = "";
                    } else {
                        tgl12 = rs1.getString("tgl12");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=13 and "
                            + "MONTH(tgl_catat)='" + rs1.getString("bln") + "' and YEAR(tgl_catat)='" + rs1.getString("thn") + "' "
                            + "and kd_numdemon='" + rs1.getString("kd_numdemon") + "'") == 0) {
                        tgl13 = "";
                    } else {
                        tgl13 = rs1.getString("tgl13");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=14 and "
                            + "MONTH(tgl_catat)='" + rs1.getString("bln") + "' and YEAR(tgl_catat)='" + rs1.getString("thn") + "' "
                            + "and kd_numdemon='" + rs1.getString("kd_numdemon") + "'") == 0) {
                        tgl14 = "";
                    } else {
                        tgl14 = rs1.getString("tgl14");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=15 and "
                            + "MONTH(tgl_catat)='" + rs1.getString("bln") + "' and YEAR(tgl_catat)='" + rs1.getString("thn") + "' "
                            + "and kd_numdemon='" + rs1.getString("kd_numdemon") + "'") == 0) {
                        tgl15 = "";
                    } else {
                        tgl15 = rs1.getString("tgl15");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=16 and "
                            + "MONTH(tgl_catat)='" + rs1.getString("bln") + "' and YEAR(tgl_catat)='" + rs1.getString("thn") + "' "
                            + "and kd_numdemon='" + rs1.getString("kd_numdemon") + "'") == 0) {
                        tgl16 = "";
                    } else {
                        tgl16 = rs1.getString("tgl16");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=17 and "
                            + "MONTH(tgl_catat)='" + rs1.getString("bln") + "' and YEAR(tgl_catat)='" + rs1.getString("thn") + "' "
                            + "and kd_numdemon='" + rs1.getString("kd_numdemon") + "'") == 0) {
                        tgl17 = "";
                    } else {
                        tgl17 = rs1.getString("tgl17");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=18 and "
                            + "MONTH(tgl_catat)='" + rs1.getString("bln") + "' and YEAR(tgl_catat)='" + rs1.getString("thn") + "' "
                            + "and kd_numdemon='" + rs1.getString("kd_numdemon") + "'") == 0) {
                        tgl18 = "";
                    } else {
                        tgl18 = rs1.getString("tgl18");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=19 and "
                            + "MONTH(tgl_catat)='" + rs1.getString("bln") + "' and YEAR(tgl_catat)='" + rs1.getString("thn") + "' "
                            + "and kd_numdemon='" + rs1.getString("kd_numdemon") + "'") == 0) {
                        tgl19 = "";
                    } else {
                        tgl19 = rs1.getString("tgl19");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=20 and "
                            + "MONTH(tgl_catat)='" + rs1.getString("bln") + "' and YEAR(tgl_catat)='" + rs1.getString("thn") + "' "
                            + "and kd_numdemon='" + rs1.getString("kd_numdemon") + "'") == 0) {
                        tgl20 = "";
                    } else {
                        tgl20 = rs1.getString("tgl20");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=21 and "
                            + "MONTH(tgl_catat)='" + rs1.getString("bln") + "' and YEAR(tgl_catat)='" + rs1.getString("thn") + "' "
                            + "and kd_numdemon='" + rs1.getString("kd_numdemon") + "'") == 0) {
                        tgl21 = "";
                    } else {
                        tgl21 = rs1.getString("tgl21");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=22 and "
                            + "MONTH(tgl_catat)='" + rs1.getString("bln") + "' and YEAR(tgl_catat)='" + rs1.getString("thn") + "' "
                            + "and kd_numdemon='" + rs1.getString("kd_numdemon") + "'") == 0) {
                        tgl22 = "";
                    } else {
                        tgl22 = rs1.getString("tgl22");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=23 and "
                            + "MONTH(tgl_catat)='" + rs1.getString("bln") + "' and YEAR(tgl_catat)='" + rs1.getString("thn") + "' "
                            + "and kd_numdemon='" + rs1.getString("kd_numdemon") + "'") == 0) {
                        tgl23 = "";
                    } else {
                        tgl23 = rs1.getString("tgl23");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=24 and "
                            + "MONTH(tgl_catat)='" + rs1.getString("bln") + "' and YEAR(tgl_catat)='" + rs1.getString("thn") + "' "
                            + "and kd_numdemon='" + rs1.getString("kd_numdemon") + "'") == 0) {
                        tgl24 = "";
                    } else {
                        tgl24 = rs1.getString("tgl24");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=25 and "
                            + "MONTH(tgl_catat)='" + rs1.getString("bln") + "' and YEAR(tgl_catat)='" + rs1.getString("thn") + "' "
                            + "and kd_numdemon='" + rs1.getString("kd_numdemon") + "'") == 0) {
                        tgl25 = "";
                    } else {
                        tgl25 = rs1.getString("tgl25");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=26 and "
                            + "MONTH(tgl_catat)='" + rs1.getString("bln") + "' and YEAR(tgl_catat)='" + rs1.getString("thn") + "' "
                            + "and kd_numdemon='" + rs1.getString("kd_numdemon") + "'") == 0) {
                        tgl26 = "";
                    } else {
                        tgl26 = rs1.getString("tgl26");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=27 and "
                            + "MONTH(tgl_catat)='" + rs1.getString("bln") + "' and YEAR(tgl_catat)='" + rs1.getString("thn") + "' "
                            + "and kd_numdemon='" + rs1.getString("kd_numdemon") + "'") == 0) {
                        tgl27 = "";
                    } else {
                        tgl27 = rs1.getString("tgl27");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=28 and "
                            + "MONTH(tgl_catat)='" + rs1.getString("bln") + "' and YEAR(tgl_catat)='" + rs1.getString("thn") + "' "
                            + "and kd_numdemon='" + rs1.getString("kd_numdemon") + "'") == 0) {
                        tgl28 = "";
                    } else {
                        tgl28 = rs1.getString("tgl28");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=29 and "
                            + "MONTH(tgl_catat)='" + rs1.getString("bln") + "' and YEAR(tgl_catat)='" + rs1.getString("thn") + "' "
                            + "and kd_numdemon='" + rs1.getString("kd_numdemon") + "'") == 0) {
                        tgl29 = "";
                    } else {
                        tgl29 = rs1.getString("tgl29");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=30 and "
                            + "MONTH(tgl_catat)='" + rs1.getString("bln") + "' and YEAR(tgl_catat)='" + rs1.getString("thn") + "' "
                            + "and kd_numdemon='" + rs1.getString("kd_numdemon") + "'") == 0) {
                        tgl30 = "";
                    } else {
                        tgl30 = rs1.getString("tgl30");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=31 and "
                            + "MONTH(tgl_catat)='" + rs1.getString("bln") + "' and YEAR(tgl_catat)='" + rs1.getString("thn") + "' "
                            + "and kd_numdemon='" + rs1.getString("kd_numdemon") + "'") == 0) {
                        tgl31 = "";
                    } else {
                        tgl31 = rs1.getString("tgl31");
                    }

                    tabMode1.addRow(new String[]{
                        rs1.getString("gedung"),
                        rs1.getString("nm_indikator"),
                        rs1.getString("jenis_numdemon"),
                        rs1.getString("nm_numdemon"),
                        tgl1, tgl2, tgl3, tgl4, tgl5, tgl6, tgl7, tgl8, tgl9, tgl10,
                        tgl11, tgl12, tgl13, tgl14, tgl15, tgl16, tgl17, tgl18, tgl19, tgl20,
                        tgl21, tgl22, tgl23, tgl24, tgl25, tgl26, tgl27, tgl28, tgl29, tgl30, tgl31, total,
                        rs1.getString("kd_indikator"),
                        rs1.getString("tgl_catat"),
                        rs1.getString("kd_numdemon")
                    });
                }
            } catch (Exception e) {
                System.out.println("tampilPrioritas() : " + e);
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
        
        tampilTotPrioritas();
    }

    private void tampilNasional() {
        sttsData = "";
        pakaiGedung = "";
        if (Ttahun.getText().equals("")) {
            Ttahun.setText(Sequel.cariIsi("select year(now())"));
        } else {
            Ttahun.setText(Ttahun.getText());
        }

        if (cmbSttsIndikator.getSelectedIndex() == 2) {
            sttsData = "";
        } else {
            sttsData = "AND m.status_data = '" + cmbSttsIndikator.getSelectedItem().toString().toLowerCase() + "'";
        }
        
        if (cmbGedung.getSelectedIndex() == 0) {
            pakaiGedung = "";
        } else {
            pakaiGedung = "and inm.gedung='" + cmbGedung.getSelectedItem().toString() + "'";
        }
        
        hitungTot = 0;
        Valid.tabelKosong(tabMode2);
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT inm.*, m.no_urut urutInm, mn.no_urut, CASE WHEN inm.kd_indikator LIKE '%IMU%' THEN CONCAT(m.nm_indikator,' (IMU)') ");
            sb.append("WHEN inm.kd_indikator LIKE '%INM%' THEN CONCAT(m.nm_indikator,' (INM)') ");
            sb.append("WHEN inm.kd_indikator IS NOT NULL AND inm.kd_indikator<>'' THEN CONCAT(m.nm_indikator, ' (', LEFT(inm.kd_indikator, 3), ')') ELSE m.nm_indikator END AS nm_indikator, ");
            sb.append("mn.nm_numdemon, MONTH(inm.tgl_catat) bln, YEAR(inm.tgl_catat) thn, mn.jenis_numdemon ,");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=1 THEN inm.jumlah_pertanggal END),'0') tgl1, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=2 THEN inm.jumlah_pertanggal END),'0') tgl2, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=3 THEN inm.jumlah_pertanggal END),'0') tgl3, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=4 THEN inm.jumlah_pertanggal END),'0') tgl4, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=5 THEN inm.jumlah_pertanggal END),'0') tgl5, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=6 THEN inm.jumlah_pertanggal END),'0') tgl6, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=7 THEN inm.jumlah_pertanggal END),'0') tgl7, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=8 THEN inm.jumlah_pertanggal END),'0') tgl8, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=9 THEN inm.jumlah_pertanggal END),'0') tgl9, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=10 THEN inm.jumlah_pertanggal END),'0') tgl10, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=11 THEN inm.jumlah_pertanggal END),'0') tgl11, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=12 THEN inm.jumlah_pertanggal END),'0') tgl12, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=13 THEN inm.jumlah_pertanggal END),'0') tgl13, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=14 THEN inm.jumlah_pertanggal END),'0') tgl14, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=15 THEN inm.jumlah_pertanggal END),'0') tgl15, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=16 THEN inm.jumlah_pertanggal END),'0') tgl16, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=17 THEN inm.jumlah_pertanggal END),'0') tgl17, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=18 THEN inm.jumlah_pertanggal END),'0') tgl18, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=19 THEN inm.jumlah_pertanggal END),'0') tgl19, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=20 THEN inm.jumlah_pertanggal END),'0') tgl20, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=21 THEN inm.jumlah_pertanggal END),'0') tgl21, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=22 THEN inm.jumlah_pertanggal END),'0') tgl22, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=23 THEN inm.jumlah_pertanggal END),'0') tgl23, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=24 THEN inm.jumlah_pertanggal END),'0') tgl24, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=25 THEN inm.jumlah_pertanggal END),'0') tgl25, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=26 THEN inm.jumlah_pertanggal END),'0') tgl26, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=27 THEN inm.jumlah_pertanggal END),'0') tgl27, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=28 THEN inm.jumlah_pertanggal END),'0') tgl28, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=29 THEN inm.jumlah_pertanggal END),'0') tgl29, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=30 THEN inm.jumlah_pertanggal END),'0') tgl30, ");
            sb.append("ifnull(sum(case when day(inm.tgl_catat)=31 THEN inm.jumlah_pertanggal END),'0') tgl31 ");
            sb.append("from indikator_nasional_mutu inm INNER JOIN master_indikator_nasional_mutu m on m.kd_indikator=inm.kd_indikator ");
            sb.append("inner join master_numdemon_indikator_nasional_mutu mn on mn.kd_numdemon=inm.kd_numdemon where ");
            sb.append("MONTH(inm.tgl_catat)='" + angkaBulan + "' and YEAR(inm.tgl_catat)='" + Ttahun.getText() + "' " + pakaiGedung + " ");
            sb.append("and m.jenis_indikator='Mutu Nasional' " + sttsData + " and m.tujuan_aktivasi='" + cmbJnsData.getSelectedItem().toString() + "' ");
            sb.append("GROUP BY inm.kd_indikator, mn.kd_numdemon, MONTH(inm.tgl_catat), YEAR(inm.tgl_catat), inm.gedung ");
            sb.append("order by inm.gedung, m.no_urut, mn.no_urut, CASE WHEN LOWER(mn.jenis_numdemon) = 'numerator' THEN 1 WHEN LOWER(mn.jenis_numdemon) = 'denominator' THEN 2 ELSE 3 END");
            ps2 = koneksi.prepareStatement(sb.toString());
            try {
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    try {
                        hitungTot = Double.parseDouble(rs2.getString("tgl1"))
                                + Double.parseDouble(rs2.getString("tgl2"))
                                + Double.parseDouble(rs2.getString("tgl3"))
                                + Double.parseDouble(rs2.getString("tgl4"))
                                + Double.parseDouble(rs2.getString("tgl5"))
                                + Double.parseDouble(rs2.getString("tgl6"))
                                + Double.parseDouble(rs2.getString("tgl7"))
                                + Double.parseDouble(rs2.getString("tgl8"))
                                + Double.parseDouble(rs2.getString("tgl9"))
                                + Double.parseDouble(rs2.getString("tgl10"))
                                + Double.parseDouble(rs2.getString("tgl11"))
                                + Double.parseDouble(rs2.getString("tgl12"))
                                + Double.parseDouble(rs2.getString("tgl13"))
                                + Double.parseDouble(rs2.getString("tgl14"))
                                + Double.parseDouble(rs2.getString("tgl15"))
                                + Double.parseDouble(rs2.getString("tgl16"))
                                + Double.parseDouble(rs2.getString("tgl17"))
                                + Double.parseDouble(rs2.getString("tgl18"))
                                + Double.parseDouble(rs2.getString("tgl19"))
                                + Double.parseDouble(rs2.getString("tgl20"))
                                + Double.parseDouble(rs2.getString("tgl21"))
                                + Double.parseDouble(rs2.getString("tgl22"))
                                + Double.parseDouble(rs2.getString("tgl23"))
                                + Double.parseDouble(rs2.getString("tgl24"))
                                + Double.parseDouble(rs2.getString("tgl25"))
                                + Double.parseDouble(rs2.getString("tgl26"))
                                + Double.parseDouble(rs2.getString("tgl27"))
                                + Double.parseDouble(rs2.getString("tgl28"))
                                + Double.parseDouble(rs2.getString("tgl29"))
                                + Double.parseDouble(rs2.getString("tgl30"))
                                + Double.parseDouble(rs2.getString("tgl31"));
                        total = Valid.SetAngka2(hitungTot);
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                        total = "error";
                    }

                    //cek angka jumlah
                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=1 and "
                            + "MONTH(tgl_catat)='" + rs2.getString("bln") + "' and YEAR(tgl_catat)='" + rs2.getString("thn") + "' "
                            + "and kd_numdemon='" + rs2.getString("kd_numdemon") + "'") == 0) {
                        tgl1 = "";
                    } else {
                        tgl1 = rs2.getString("tgl1");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=2 and "
                            + "MONTH(tgl_catat)='" + rs2.getString("bln") + "' and YEAR(tgl_catat)='" + rs2.getString("thn") + "' "
                            + "and kd_numdemon='" + rs2.getString("kd_numdemon") + "'") == 0) {
                        tgl2 = "";
                    } else {
                        tgl2 = rs2.getString("tgl2");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=3 and "
                            + "MONTH(tgl_catat)='" + rs2.getString("bln") + "' and YEAR(tgl_catat)='" + rs2.getString("thn") + "' "
                            + "and kd_numdemon='" + rs2.getString("kd_numdemon") + "'") == 0) {
                        tgl3 = "";
                    } else {
                        tgl3 = rs2.getString("tgl3");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=4 and "
                            + "MONTH(tgl_catat)='" + rs2.getString("bln") + "' and YEAR(tgl_catat)='" + rs2.getString("thn") + "' "
                            + "and kd_numdemon='" + rs2.getString("kd_numdemon") + "'") == 0) {
                        tgl4 = "";
                    } else {
                        tgl4 = rs2.getString("tgl4");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=5 and "
                            + "MONTH(tgl_catat)='" + rs2.getString("bln") + "' and YEAR(tgl_catat)='" + rs2.getString("thn") + "' "
                            + "and kd_numdemon='" + rs2.getString("kd_numdemon") + "'") == 0) {
                        tgl5 = "";
                    } else {
                        tgl5 = rs2.getString("tgl5");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=6 and "
                            + "MONTH(tgl_catat)='" + rs2.getString("bln") + "' and YEAR(tgl_catat)='" + rs2.getString("thn") + "' "
                            + "and kd_numdemon='" + rs2.getString("kd_numdemon") + "'") == 0) {
                        tgl6 = "";
                    } else {
                        tgl6 = rs2.getString("tgl6");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=7 and "
                            + "MONTH(tgl_catat)='" + rs2.getString("bln") + "' and YEAR(tgl_catat)='" + rs2.getString("thn") + "' "
                            + "and kd_numdemon='" + rs2.getString("kd_numdemon") + "'") == 0) {
                        tgl7 = "";
                    } else {
                        tgl7 = rs2.getString("tgl7");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=8 and "
                            + "MONTH(tgl_catat)='" + rs2.getString("bln") + "' and YEAR(tgl_catat)='" + rs2.getString("thn") + "' "
                            + "and kd_numdemon='" + rs2.getString("kd_numdemon") + "'") == 0) {
                        tgl8 = "";
                    } else {
                        tgl8 = rs2.getString("tgl8");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=9 and "
                            + "MONTH(tgl_catat)='" + rs2.getString("bln") + "' and YEAR(tgl_catat)='" + rs2.getString("thn") + "' "
                            + "and kd_numdemon='" + rs2.getString("kd_numdemon") + "'") == 0) {
                        tgl9 = "";
                    } else {
                        tgl9 = rs2.getString("tgl9");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=10 and "
                            + "MONTH(tgl_catat)='" + rs2.getString("bln") + "' and YEAR(tgl_catat)='" + rs2.getString("thn") + "' "
                            + "and kd_numdemon='" + rs2.getString("kd_numdemon") + "'") == 0) {
                        tgl10 = "";
                    } else {
                        tgl10 = rs2.getString("tgl10");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=11 and "
                            + "MONTH(tgl_catat)='" + rs2.getString("bln") + "' and YEAR(tgl_catat)='" + rs2.getString("thn") + "' "
                            + "and kd_numdemon='" + rs2.getString("kd_numdemon") + "'") == 0) {
                        tgl11 = "";
                    } else {
                        tgl11 = rs2.getString("tgl11");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=12 and "
                            + "MONTH(tgl_catat)='" + rs2.getString("bln") + "' and YEAR(tgl_catat)='" + rs2.getString("thn") + "' "
                            + "and kd_numdemon='" + rs2.getString("kd_numdemon") + "'") == 0) {
                        tgl12 = "";
                    } else {
                        tgl12 = rs2.getString("tgl12");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=13 and "
                            + "MONTH(tgl_catat)='" + rs2.getString("bln") + "' and YEAR(tgl_catat)='" + rs2.getString("thn") + "' "
                            + "and kd_numdemon='" + rs2.getString("kd_numdemon") + "'") == 0) {
                        tgl13 = "";
                    } else {
                        tgl13 = rs2.getString("tgl13");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=14 and "
                            + "MONTH(tgl_catat)='" + rs2.getString("bln") + "' and YEAR(tgl_catat)='" + rs2.getString("thn") + "' "
                            + "and kd_numdemon='" + rs2.getString("kd_numdemon") + "'") == 0) {
                        tgl14 = "";
                    } else {
                        tgl14 = rs2.getString("tgl14");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=15 and "
                            + "MONTH(tgl_catat)='" + rs2.getString("bln") + "' and YEAR(tgl_catat)='" + rs2.getString("thn") + "' "
                            + "and kd_numdemon='" + rs2.getString("kd_numdemon") + "'") == 0) {
                        tgl15 = "";
                    } else {
                        tgl15 = rs2.getString("tgl15");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=16 and "
                            + "MONTH(tgl_catat)='" + rs2.getString("bln") + "' and YEAR(tgl_catat)='" + rs2.getString("thn") + "' "
                            + "and kd_numdemon='" + rs2.getString("kd_numdemon") + "'") == 0) {
                        tgl16 = "";
                    } else {
                        tgl16 = rs2.getString("tgl16");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=17 and "
                            + "MONTH(tgl_catat)='" + rs2.getString("bln") + "' and YEAR(tgl_catat)='" + rs2.getString("thn") + "' "
                            + "and kd_numdemon='" + rs2.getString("kd_numdemon") + "'") == 0) {
                        tgl17 = "";
                    } else {
                        tgl17 = rs2.getString("tgl17");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=18 and "
                            + "MONTH(tgl_catat)='" + rs2.getString("bln") + "' and YEAR(tgl_catat)='" + rs2.getString("thn") + "' "
                            + "and kd_numdemon='" + rs2.getString("kd_numdemon") + "'") == 0) {
                        tgl18 = "";
                    } else {
                        tgl18 = rs2.getString("tgl18");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=19 and "
                            + "MONTH(tgl_catat)='" + rs2.getString("bln") + "' and YEAR(tgl_catat)='" + rs2.getString("thn") + "' "
                            + "and kd_numdemon='" + rs2.getString("kd_numdemon") + "'") == 0) {
                        tgl19 = "";
                    } else {
                        tgl19 = rs2.getString("tgl19");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=20 and "
                            + "MONTH(tgl_catat)='" + rs2.getString("bln") + "' and YEAR(tgl_catat)='" + rs2.getString("thn") + "' "
                            + "and kd_numdemon='" + rs2.getString("kd_numdemon") + "'") == 0) {
                        tgl20 = "";
                    } else {
                        tgl20 = rs2.getString("tgl20");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=21 and "
                            + "MONTH(tgl_catat)='" + rs2.getString("bln") + "' and YEAR(tgl_catat)='" + rs2.getString("thn") + "' "
                            + "and kd_numdemon='" + rs2.getString("kd_numdemon") + "'") == 0) {
                        tgl21 = "";
                    } else {
                        tgl21 = rs2.getString("tgl21");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=22 and "
                            + "MONTH(tgl_catat)='" + rs2.getString("bln") + "' and YEAR(tgl_catat)='" + rs2.getString("thn") + "' "
                            + "and kd_numdemon='" + rs2.getString("kd_numdemon") + "'") == 0) {
                        tgl22 = "";
                    } else {
                        tgl22 = rs2.getString("tgl22");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=23 and "
                            + "MONTH(tgl_catat)='" + rs2.getString("bln") + "' and YEAR(tgl_catat)='" + rs2.getString("thn") + "' "
                            + "and kd_numdemon='" + rs2.getString("kd_numdemon") + "'") == 0) {
                        tgl23 = "";
                    } else {
                        tgl23 = rs2.getString("tgl23");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=24 and "
                            + "MONTH(tgl_catat)='" + rs2.getString("bln") + "' and YEAR(tgl_catat)='" + rs2.getString("thn") + "' "
                            + "and kd_numdemon='" + rs2.getString("kd_numdemon") + "'") == 0) {
                        tgl24 = "";
                    } else {
                        tgl24 = rs2.getString("tgl24");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=25 and "
                            + "MONTH(tgl_catat)='" + rs2.getString("bln") + "' and YEAR(tgl_catat)='" + rs2.getString("thn") + "' "
                            + "and kd_numdemon='" + rs2.getString("kd_numdemon") + "'") == 0) {
                        tgl25 = "";
                    } else {
                        tgl25 = rs2.getString("tgl25");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=26 and "
                            + "MONTH(tgl_catat)='" + rs2.getString("bln") + "' and YEAR(tgl_catat)='" + rs2.getString("thn") + "' "
                            + "and kd_numdemon='" + rs2.getString("kd_numdemon") + "'") == 0) {
                        tgl26 = "";
                    } else {
                        tgl26 = rs2.getString("tgl26");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=27 and "
                            + "MONTH(tgl_catat)='" + rs2.getString("bln") + "' and YEAR(tgl_catat)='" + rs2.getString("thn") + "' "
                            + "and kd_numdemon='" + rs2.getString("kd_numdemon") + "'") == 0) {
                        tgl27 = "";
                    } else {
                        tgl27 = rs2.getString("tgl27");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=28 and "
                            + "MONTH(tgl_catat)='" + rs2.getString("bln") + "' and YEAR(tgl_catat)='" + rs2.getString("thn") + "' "
                            + "and kd_numdemon='" + rs2.getString("kd_numdemon") + "'") == 0) {
                        tgl28 = "";
                    } else {
                        tgl28 = rs2.getString("tgl28");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=29 and "
                            + "MONTH(tgl_catat)='" + rs2.getString("bln") + "' and YEAR(tgl_catat)='" + rs2.getString("thn") + "' "
                            + "and kd_numdemon='" + rs2.getString("kd_numdemon") + "'") == 0) {
                        tgl29 = "";
                    } else {
                        tgl29 = rs2.getString("tgl29");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=30 and "
                            + "MONTH(tgl_catat)='" + rs2.getString("bln") + "' and YEAR(tgl_catat)='" + rs2.getString("thn") + "' "
                            + "and kd_numdemon='" + rs2.getString("kd_numdemon") + "'") == 0) {
                        tgl30 = "";
                    } else {
                        tgl30 = rs2.getString("tgl30");
                    }

                    if (Sequel.cariInteger("select count(-1) from indikator_nasional_mutu where day(tgl_catat)=31 and "
                            + "MONTH(tgl_catat)='" + rs2.getString("bln") + "' and YEAR(tgl_catat)='" + rs2.getString("thn") + "' "
                            + "and kd_numdemon='" + rs2.getString("kd_numdemon") + "'") == 0) {
                        tgl31 = "";
                    } else {
                        tgl31 = rs2.getString("tgl31");
                    }

                    tabMode2.addRow(new String[]{
                        rs2.getString("gedung"),
                        rs2.getString("nm_indikator"),
                        rs2.getString("jenis_numdemon"),
                        rs2.getString("nm_numdemon"),
                        tgl1, tgl2, tgl3, tgl4, tgl5, tgl6, tgl7, tgl8, tgl9, tgl10,
                        tgl11, tgl12, tgl13, tgl14, tgl15, tgl16, tgl17, tgl18, tgl19, tgl20,
                        tgl21, tgl22, tgl23, tgl24, tgl25, tgl26, tgl27, tgl28, tgl29, tgl30, tgl31, total,
                        rs2.getString("kd_indikator"),
                        rs2.getString("tgl_catat"),
                        rs2.getString("kd_numdemon")
                    });
                }
            } catch (Exception e) {
                System.out.println("laporan.DlgIndikatorNasionalMutu.tampilNasional() : " + e);
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
        
        tampilTotNasional();
    }
    
    private void tampilTotPrioritas() {
        sttsData = "";
        pakaiGedung = "";
        ttlNum = 0;
        ttlDen = 0;
        ttlSemua = 0;
        
        if (Ttahun.getText().equals("")) {
            Ttahun.setText(Sequel.cariIsi("select year(now())"));
        } else {
            Ttahun.setText(Ttahun.getText());
        }

        if (cmbSttsIndikator.getSelectedIndex() == 0) {
            sttsData = "aktif";
        } else if (cmbSttsIndikator.getSelectedIndex() == 1) {
            sttsData = "non aktif";
        } else {
            sttsData = "-";
        }
        
        if (cmbGedung.getSelectedIndex() == 0) {
            pakaiGedung = "";
        } else {
            pakaiGedung = "and inm.gedung='" + cmbGedung.getSelectedItem().toString() + "'";
        }
        
        Valid.tabelKosong(tabMode3);
        try {
            StringBuilder sb = new StringBuilder();
            if (cmbSttsIndikator.getSelectedIndex() == 0 || cmbSttsIndikator.getSelectedIndex() == 1) {
                sb.append("SELECT inm.gedung, CASE ");
                sb.append("WHEN inm.kd_indikator LIKE '%IMU%' THEN CONCAT(m.nm_indikator,' (IMU)') ");
                sb.append("WHEN inm.kd_indikator LIKE '%INM%' THEN CONCAT(m.nm_indikator,' (INM)') ");
                sb.append("WHEN inm.kd_indikator IS NOT NULL AND inm.kd_indikator<>'' THEN CONCAT(m.nm_indikator, ' (', LEFT(inm.kd_indikator, 3), ')') ");
                sb.append("ELSE m.nm_indikator END nama_Indikator, ");
                sb.append("IFNULL(SUM(CASE WHEN mn.jenis_numdemon='Numerator' THEN inm.jumlah_pertanggal END),0) tot_num, ");
                sb.append("IFNULL(SUM(CASE WHEN mn.jenis_numdemon='Denominator' THEN inm.jumlah_pertanggal END),0) tot_den, ");
                sb.append("IFNULL(SUM(inm.jumlah_pertanggal),0) tot_semua FROM indikator_nasional_mutu inm ");
                sb.append("INNER JOIN master_indikator_nasional_mutu m ON m.kd_indikator=inm.kd_indikator ");
                sb.append("INNER JOIN master_numdemon_indikator_nasional_mutu mn ON mn.kd_numdemon=inm.kd_numdemon WHERE ");
                sb.append("MONTH(inm.tgl_catat)='" + angkaBulan + "' and YEAR(inm.tgl_catat)='" + Ttahun.getText() + "' " + pakaiGedung + " ");
                sb.append("and m.jenis_indikator='Mutu Prioritas RS' and m.status_data='" + sttsData + "' ");
                sb.append("and m.tujuan_aktivasi='" + cmbJnsData.getSelectedItem().toString() + "' ");
                sb.append("GROUP BY inm.kd_indikator, MONTH(inm.tgl_catat), YEAR(inm.tgl_catat), inm.gedung order by m.no_urut, mn.no_urut");
            } else {
                sb.append("SELECT inm.gedung, CASE ");
                sb.append("WHEN inm.kd_indikator LIKE '%IMU%' THEN CONCAT(m.nm_indikator,' (IMU)') ");
                sb.append("WHEN inm.kd_indikator LIKE '%INM%' THEN CONCAT(m.nm_indikator,' (INM)') ");
                sb.append("WHEN inm.kd_indikator IS NOT NULL AND inm.kd_indikator<>'' THEN CONCAT(m.nm_indikator, ' (', LEFT(inm.kd_indikator, 3), ')') ");
                sb.append("ELSE m.nm_indikator END nama_Indikator, ");
                sb.append("IFNULL(SUM(CASE WHEN mn.jenis_numdemon='Numerator' THEN inm.jumlah_pertanggal END),0) tot_num, ");
                sb.append("IFNULL(SUM(CASE WHEN mn.jenis_numdemon='Denominator' THEN inm.jumlah_pertanggal END),0) tot_den, ");
                sb.append("IFNULL(SUM(inm.jumlah_pertanggal),0) tot_semua FROM indikator_nasional_mutu inm ");
                sb.append("INNER JOIN master_indikator_nasional_mutu m ON m.kd_indikator=inm.kd_indikator ");
                sb.append("INNER JOIN master_numdemon_indikator_nasional_mutu mn ON mn.kd_numdemon=inm.kd_numdemon WHERE ");
                sb.append("MONTH(inm.tgl_catat)='" + angkaBulan + "' and YEAR(inm.tgl_catat)='" + Ttahun.getText() + "' " + pakaiGedung + " ");
                sb.append("and m.jenis_indikator='Mutu Prioritas RS' and m.tujuan_aktivasi='" + cmbJnsData.getSelectedItem().toString() + "' ");
                sb.append("GROUP BY inm.kd_indikator, MONTH(inm.tgl_catat), YEAR(inm.tgl_catat), inm.gedung order by m.no_urut, mn.no_urut");
            }
            ps3 = koneksi.prepareStatement(sb.toString());
            try {
                rs3 = ps3.executeQuery();
                while (rs3.next()) {
                    ttlNum = ttlNum + Integer.parseInt(rs3.getString("tot_num").toString());
                    ttlDen = ttlDen + Integer.parseInt(rs3.getString("tot_den").toString());
                    ttlSemua = ttlSemua + Integer.parseInt(rs3.getString("tot_semua").toString());
                    
                    tabMode3.addRow(new String[]{
                        rs3.getString("gedung"),
                        rs3.getString("nama_Indikator"),
                        rs3.getString("tot_num"),
                        rs3.getString("tot_den"),
                        rs3.getString("tot_semua")
                    });
                }
                
                if (tabMode1.getRowCount() > 0) {
                    tabMode3.addRow(new String[]{"", "TOTAL : ", Valid.SetAngka(ttlNum), Valid.SetAngka(ttlDen), Valid.SetAngka(ttlSemua)});
                }
            } catch (Exception e) {
                System.out.println("laporan.DlgIndikatorNasionalMutu.tampilTotPrioritas() : " + e);
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
    
    private void tampilTotNasional() {
        sttsData = "";
        pakaiGedung = "";
        ttlNum = 0;
        ttlDen = 0;
        ttlSemua = 0;
        
        if (Ttahun.getText().equals("")) {
            Ttahun.setText(Sequel.cariIsi("select year(now())"));
        } else {
            Ttahun.setText(Ttahun.getText());
        }

        if (cmbSttsIndikator.getSelectedIndex() == 0) {
            sttsData = "aktif";
        } else if (cmbSttsIndikator.getSelectedIndex() == 1) {
            sttsData = "non aktif";
        } else {
            sttsData = "-";
        }
        
        if (cmbGedung.getSelectedIndex() == 0) {
            pakaiGedung = "";
        } else {
            pakaiGedung = "and inm.gedung='" + cmbGedung.getSelectedItem().toString() + "'";
        }
        
        Valid.tabelKosong(tabMode4);
        try {
            StringBuilder sb = new StringBuilder();
            if (cmbSttsIndikator.getSelectedIndex() == 0 || cmbSttsIndikator.getSelectedIndex() == 1) {
                sb.append("SELECT inm.gedung, CASE ");
                sb.append("WHEN inm.kd_indikator LIKE '%IMU%' THEN CONCAT(m.nm_indikator,' (IMU)') ");
                sb.append("WHEN inm.kd_indikator LIKE '%INM%' THEN CONCAT(m.nm_indikator,' (INM)') ");
                sb.append("WHEN inm.kd_indikator IS NOT NULL AND inm.kd_indikator<>'' THEN CONCAT(m.nm_indikator, ' (', LEFT(inm.kd_indikator, 3), ')') ");
                sb.append("ELSE m.nm_indikator END nama_Indikator, ");
                sb.append("IFNULL(SUM(CASE WHEN mn.jenis_numdemon='Numerator' THEN inm.jumlah_pertanggal END),0) tot_num, ");
                sb.append("IFNULL(SUM(CASE WHEN mn.jenis_numdemon='Denominator' THEN inm.jumlah_pertanggal END),0) tot_den, ");
                sb.append("IFNULL(SUM(inm.jumlah_pertanggal),0) tot_semua FROM indikator_nasional_mutu inm ");
                sb.append("INNER JOIN master_indikator_nasional_mutu m ON m.kd_indikator=inm.kd_indikator ");
                sb.append("INNER JOIN master_numdemon_indikator_nasional_mutu mn ON mn.kd_numdemon=inm.kd_numdemon WHERE ");
                sb.append("MONTH(inm.tgl_catat)='" + angkaBulan + "' and YEAR(inm.tgl_catat)='" + Ttahun.getText() + "' " + pakaiGedung + " ");
                sb.append("and m.jenis_indikator='Mutu Nasional' and m.status_data='" + sttsData + "' ");
                sb.append("and m.tujuan_aktivasi='" + cmbJnsData.getSelectedItem().toString() + "' ");
                sb.append("GROUP BY inm.kd_indikator, MONTH(inm.tgl_catat), YEAR(inm.tgl_catat), inm.gedung order by inm.gedung, m.no_urut, mn.no_urut");
            } else {
                sb.append("SELECT inm.gedung, CASE ");
                sb.append("WHEN inm.kd_indikator LIKE '%IMU%' THEN CONCAT(m.nm_indikator,' (IMU)') ");
                sb.append("WHEN inm.kd_indikator LIKE '%INM%' THEN CONCAT(m.nm_indikator,' (INM)') ");
                sb.append("WHEN inm.kd_indikator IS NOT NULL AND inm.kd_indikator<>'' THEN CONCAT(m.nm_indikator, ' (', LEFT(inm.kd_indikator, 3), ')') ");
                sb.append("ELSE m.nm_indikator END nama_Indikator, ");
                sb.append("IFNULL(SUM(CASE WHEN mn.jenis_numdemon='Numerator' THEN inm.jumlah_pertanggal END),0) tot_num, ");
                sb.append("IFNULL(SUM(CASE WHEN mn.jenis_numdemon='Denominator' THEN inm.jumlah_pertanggal END),0) tot_den, ");
                sb.append("IFNULL(SUM(inm.jumlah_pertanggal),0) tot_semua FROM indikator_nasional_mutu inm ");
                sb.append("INNER JOIN master_indikator_nasional_mutu m ON m.kd_indikator=inm.kd_indikator ");
                sb.append("INNER JOIN master_numdemon_indikator_nasional_mutu mn ON mn.kd_numdemon=inm.kd_numdemon WHERE ");
                sb.append("MONTH(inm.tgl_catat)='" + angkaBulan + "' and YEAR(inm.tgl_catat)='" + Ttahun.getText() + "' " + pakaiGedung + " ");
                sb.append("and m.jenis_indikator='Mutu Nasional' and m.tujuan_aktivasi='" + cmbJnsData.getSelectedItem().toString() + "' ");
                sb.append("GROUP BY inm.kd_indikator, MONTH(inm.tgl_catat), YEAR(inm.tgl_catat), inm.gedung order by inm.gedung, m.no_urut, mn.no_urut");
            }
            ps4 = koneksi.prepareStatement(sb.toString());
            try {
                rs4 = ps4.executeQuery();
                while (rs4.next()) {
                    ttlNum = ttlNum + Integer.parseInt(rs4.getString("tot_num").toString());
                    ttlDen = ttlDen + Integer.parseInt(rs4.getString("tot_den").toString());
                    ttlSemua = ttlSemua + Integer.parseInt(rs4.getString("tot_semua").toString());
                    
                    tabMode4.addRow(new String[]{
                        rs4.getString("gedung"),
                        rs4.getString("nama_Indikator"),
                        rs4.getString("tot_num"),
                        rs4.getString("tot_den"),
                        rs4.getString("tot_semua")
                    });
                }
                
                if (tabMode2.getRowCount() > 0) {
                    tabMode4.addRow(new String[]{"", "TOTAL : ", Valid.SetAngka(ttlNum), Valid.SetAngka(ttlDen), Valid.SetAngka(ttlSemua)});
                }
            } catch (Exception e) {
                System.out.println("laporan.DlgIndikatorNasionalMutu.tampilTotNasional() : " + e);
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
}
