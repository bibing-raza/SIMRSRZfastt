package rekammedis;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import kepegawaian.DlgCariPetugas;
import simrskhanza.DlgCariDokter;
import simrskhanza.frmUtama;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 *
 * @author dosen
 */
public class RMRekonsiliasiObat extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1, tabMode2, tabMode3, tabMode4, tabMode5, tabMode6, tabMode7;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps1, ps2, ps3, ps4, ps5, ps6, ps7, ps8, ps9, ps10;
    private ResultSet rs, rs1, rs2, rs3, rs4, rs5, rs6, rs7, rs8, rs9, rs10;
    private int i = 0, x = 0, pilihan = 0;
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    private String kdObat = "", nipPereview = "", nipApoteker = "", nmDokter = "", tglreg = "", nipDokter = "",
            ceknmDokter = "", cekNipDokter = "", itemObat = "", cekTglIGD = "", cekTglRanap = "", kode = "", htmlCetakRekonsiliasi = "";
    private frmUtama formUtama;
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public RMRekonsiliasiObat(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //data di tabel grid rata tengah
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);

        tabMode = new DefaultTableModel(null, new String[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Lahir", "Tgl. Rekon", "Jns. Rekonsiliasi", "Riwayat Alergi", "Ket. Riwayat Alergi", "Obat Dari Luar",
            "Catatan Riw. Obat IGD", "Catatan Riw. Obat Rawat Inap", "Ketidaksesuaian", "Saran", "Keputusan", "Di Review Oleh", "Nama Apoteker",
            "nip_pereview", "nip_apoteker", "tanggal", "waktu_simpan", "catatan_obat_riwayat", "kd_data"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbRekon.setModel(tabMode);
        tbRekon.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbRekon.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 22; i++) {
            TableColumn column = tbRekon.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(110);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(220);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(120);
            } else if (i == 6) {
                column.setPreferredWidth(90);
            } else if (i == 7) {
                column.setPreferredWidth(250);
            } else if (i == 8) {
                column.setPreferredWidth(90);
            } else if (i == 9) {
                column.setPreferredWidth(250);
            } else if (i == 10) {
                column.setPreferredWidth(250);
            } else if (i == 11) {
                column.setPreferredWidth(250);
            } else if (i == 12) {
                column.setPreferredWidth(250);
            } else if (i == 13) {
                column.setPreferredWidth(250);
            } else if (i == 14) {
                column.setPreferredWidth(220);
            } else if (i == 15) {
                column.setPreferredWidth(220);
            } else if (i == 16) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 17) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 18) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 19) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 20) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 21) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } 
        }
        tbRekon.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbRekon.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbRekon.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbRekon.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbRekon.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        
        tabMode1 = new DefaultTableModel(null, new String[]{
            "No.", "Tgl. Resep", "Unit", "Nama Obat", "Jumlah", "Dokter Meresepkan", "kdObat", "tglresep"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbRiwayatObatIgd.setModel(tabMode1);
        tbRiwayatObatIgd.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbRiwayatObatIgd.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 8; i++) {
            TableColumn column = tbRiwayatObatIgd.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(75);
            } else if (i == 2) {
                column.setPreferredWidth(150);
            } else if (i == 3) {
                column.setPreferredWidth(300);
            } else if (i == 4) {
                column.setPreferredWidth(60);
            } else if (i == 5) {
                column.setPreferredWidth(250);
            } else if (i == 6) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 7) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbRiwayatObatIgd.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbRiwayatObatIgd.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbRiwayatObatIgd.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        
        tabMode2 = new DefaultTableModel(null, new String[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Tanggal", "Unit", "Nama Obat", "Rute", "Dosis", "Dilanjutkan", "Ket. Dilanjutkan",
            "tgl_resep", "kode_brng", "waktu_simpan", "cek_stop", "tgl_stop"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbRekonIgd.setModel(tabMode2);
        tbRekonIgd.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbRekonIgd.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 15; i++) {
            TableColumn column = tbRekonIgd.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(110);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(220);
            } else if (i == 3) {
                column.setPreferredWidth(250);
            } else if (i == 4) {
                column.setPreferredWidth(130);
            } else if (i == 5) {
                column.setPreferredWidth(250);
            } else if (i == 6) {
                column.setPreferredWidth(100);
            } else if (i == 7) {
                column.setPreferredWidth(100);
            } else if (i == 8) {
                column.setPreferredWidth(80);
            } else if (i == 9) {
                column.setPreferredWidth(200);
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
            }
        }
        tbRekonIgd.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbRekonIgd.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbRekonIgd.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        
        tabMode3 = new DefaultTableModel(null, new String[]{
            "Ruang Rawat", "Tgl. Pemberian", "Jam", "Nama Obat", "Rute dan Dosis", "Dokter Meresepkan"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbResepIgd.setModel(tabMode3);
        tbResepIgd.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbResepIgd.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 6; i++) {
            TableColumn column = tbResepIgd.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(220);
            } else if (i == 1) {
                column.setPreferredWidth(90);
            } else if (i == 2) {
                column.setPreferredWidth(60);
            } else if (i == 3) {
                column.setPreferredWidth(400);
            } else if (i == 4) {
                column.setPreferredWidth(400);
            } else if (i == 5) {
                column.setPreferredWidth(250);
            } 
        }
        tbResepIgd.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbResepIgd.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbResepIgd.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        
        tabMode4 = new DefaultTableModel(null, new String[]{
            "No.", "Tgl. Resep", "Ruang Rawat", "Nama Obat", "Jumlah", "Dokter Meresepkan", "kdObat", "tglresep", "nip"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbRiwayatObatRanap.setModel(tabMode4);
        tbRiwayatObatRanap.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbRiwayatObatRanap.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 9; i++) {
            TableColumn column = tbRiwayatObatRanap.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(75);
            } else if (i == 2) {
                column.setPreferredWidth(220);
            } else if (i == 3) {
                column.setPreferredWidth(300);
            } else if (i == 4) {
                column.setPreferredWidth(60);
            } else if (i == 5) {
                column.setPreferredWidth(250);
            } else if (i == 6) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 7) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 8) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbRiwayatObatRanap.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbRiwayatObatRanap.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbRiwayatObatRanap.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        
        tabMode5 = new DefaultTableModel(null, new String[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Tanggal", "Ruang Rawat", "Nama Obat", "Rute", "Dosis", "Aturan Pakai", "Dokter Meresepkan",
            "tgl_resep", "kode_brng", "nip_dokter", "waktu_simpan", "cek_stop", "tgl_stop"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbRekonRanap.setModel(tabMode5);
        tbRekonRanap.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbRekonRanap.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 16; i++) {
            TableColumn column = tbRekonRanap.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(110);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(220);
            } else if (i == 3) {
                column.setPreferredWidth(250);
            } else if (i == 4) {
                column.setPreferredWidth(220);
            } else if (i == 5) {
                column.setPreferredWidth(250);
            } else if (i == 6) {
                column.setPreferredWidth(100);
            } else if (i == 7) {
                column.setPreferredWidth(100);
            } else if (i == 8) {
                column.setPreferredWidth(100);
            } else if (i == 9) {
                column.setPreferredWidth(220);
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
            }
        }
        tbRekonRanap.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbRekonRanap.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbRekonRanap.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        
        tabMode6 = new DefaultTableModel(null, new String[]{
            "Ruang Rawat", "Tgl. Pemberian", "Jam", "Nama Obat", "Rute dan Dosis", "Dokter Meresepkan"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbResepRanap.setModel(tabMode6);
        tbResepRanap.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbResepRanap.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 6; i++) {
            TableColumn column = tbResepRanap.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(220);
            } else if (i == 1) {
                column.setPreferredWidth(90);
            } else if (i == 2) {
                column.setPreferredWidth(60);
            } else if (i == 3) {
                column.setPreferredWidth(400);
            } else if (i == 4) {
                column.setPreferredWidth(400);
            } else if (i == 5) {
                column.setPreferredWidth(250);
            } 
        }
        tbResepRanap.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbResepRanap.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbResepRanap.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        
        tabMode7 = new DefaultTableModel(null, new String[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Ruang Rawat", "Nama Obat", "Rute", "Dosis", "Aturan Pakai", "Sumber Obat", "Dilanjutkan", "waktu_simpan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbRiwayat.setModel(tabMode7);
        tbRiwayat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbRiwayat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 11; i++) {
            TableColumn column = tbRiwayat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(110);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(220);
            } else if (i == 3) {
                column.setPreferredWidth(220);
            } else if (i == 4) {
                column.setPreferredWidth(250);
            } else if (i == 5) {
                column.setPreferredWidth(80);
            } else if (i == 6) {
                column.setPreferredWidth(80);
            } else if (i == 7) {
                column.setPreferredWidth(100);
            } else if (i == 8) {
                column.setPreferredWidth(220);
            } else if (i == 9) {
                column.setPreferredWidth(80);
            } else if (i == 10) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } 
        }
        tbRiwayat.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbRiwayat.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbRiwayat.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

        TketAlergi.setDocument(new batasInput((int) 200).getKata(TketAlergi));
        Trute.setDocument(new batasInput((int) 140).getKata(Trute));
        Tdosis.setDocument(new batasInput((int) 140).getKata(Tdosis));
        Tdilanjutkan.setDocument(new batasInput((int) 200).getKata(Tdilanjutkan));
        Trute1.setDocument(new batasInput((int) 140).getKata(Trute));
        Tdosis1.setDocument(new batasInput((int) 140).getKata(Tdosis));
        Taturan.setDocument(new batasInput((int) 200).getKata(Tdosis));
        TnmObat2.setDocument(new batasInput((int) 200).getKata(TnmObat2));
        Trute2.setDocument(new batasInput((int) 20).getKata(Trute2));
        Tdosis2.setDocument(new batasInput((int) 20).getKata(Tdosis2));
        Taturan1.setDocument(new batasInput((int) 100).getKata(Taturan1));
        Tsumber.setDocument(new batasInput((int) 200).getKata(Tsumber));
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
                    if (pilihan == 1) {
                        nipPereview = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                        TnmPereview.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                        BtnPereview.requestFocus();
                    } else if (pilihan == 2) {
                        nipApoteker = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                        TnmApoteker.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                        BtnApoteker.requestFocus();
                    }
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
        
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("RMRekonsiliasiObat")) {
                    if (dokter.getTable().getSelectedRow() != -1) {
                        nipDokter = dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString();
                        TnmDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                    }
                    BtnDokter.requestFocus();
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
        
        HTMLEditorKit kit = new HTMLEditorKit();
        StyleSheet styleSheet = kit.getStyleSheet();
        styleSheet.addRule("body {font-family:Tahoma;font-size:10px;color:#000000;}");
        styleSheet.addRule("table {border-collapse:collapse;}");
        Document doc = kit.createDefaultDocument();

        LoadHTML1.setEditorKit(kit);
        LoadHTML1.setDocument(doc);
        LoadHTML1.setEditable(false);
    }
 
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        internalFrame1 = new widget.InternalFrame();
        TabRekon = new javax.swing.JTabbedPane();
        PanelInput3 = new javax.swing.JPanel();
        FormInput5 = new widget.PanelBiasa();
        jLabel43 = new widget.Label();
        TNoRw4 = new widget.TextBox();
        TNoRM4 = new widget.TextBox();
        TPasien4 = new widget.TextBox();
        jLabel45 = new widget.Label();
        TnmObat2 = new widget.TextBox();
        jLabel46 = new widget.Label();
        Trute2 = new widget.TextBox();
        jLabel47 = new widget.Label();
        Tdosis2 = new widget.TextBox();
        jLabel64 = new widget.Label();
        TrgRawat1 = new widget.TextBox();
        Taturan1 = new widget.TextBox();
        jLabel49 = new widget.Label();
        jLabel50 = new widget.Label();
        TtglMRS2 = new widget.TextBox();
        jLabel54 = new widget.Label();
        Tsumber = new widget.TextBox();
        jLabel51 = new widget.Label();
        cmbDilanjut1 = new widget.ComboBox();
        Scroll10 = new widget.ScrollPane();
        tbRiwayat = new widget.Table();
        PanelInput = new javax.swing.JPanel();
        FormInput = new widget.PanelBiasa();
        jLabel4 = new widget.Label();
        TNoRw2 = new widget.TextBox();
        TNoRM2 = new widget.TextBox();
        TPasien2 = new widget.TextBox();
        jLabel5 = new widget.Label();
        TtglResep = new widget.Tanggal();
        jLabel3 = new widget.Label();
        TnmObat = new widget.TextBox();
        jLabel8 = new widget.Label();
        Trute = new widget.TextBox();
        jLabel9 = new widget.Label();
        Tdosis = new widget.TextBox();
        jLabel10 = new widget.Label();
        cmbDilanjut = new widget.ComboBox();
        Tdilanjutkan = new widget.TextBox();
        jLabel36 = new widget.Label();
        TtglMRS1 = new widget.TextBox();
        chkTglIgd = new widget.CekBox();
        TtglStopIgd = new widget.Tanggal();
        internalFrame3 = new widget.InternalFrame();
        Scroll2 = new widget.ScrollPane();
        tbRekonIgd = new widget.Table();
        Scroll3 = new widget.ScrollPane();
        tbRiwayatObatIgd = new widget.Table();
        panelGlass9 = new widget.panelisi();
        Scroll8 = new widget.ScrollPane();
        tbResepIgd = new widget.Table();
        panelGlass14 = new widget.panelisi();
        jLabel68 = new widget.Label();
        DTPCari3 = new widget.Tanggal();
        jLabel73 = new widget.Label();
        DTPCari4 = new widget.Tanggal();
        jLabel39 = new widget.Label();
        TCari1 = new widget.TextBox();
        BtnCari1 = new widget.Button();
        BtnAll1 = new widget.Button();
        jLabel40 = new widget.Label();
        LCount1 = new widget.Label();
        PanelInput2 = new javax.swing.JPanel();
        FormInput3 = new widget.PanelBiasa();
        jLabel28 = new widget.Label();
        TNoRw3 = new widget.TextBox();
        TNoRM3 = new widget.TextBox();
        TPasien3 = new widget.TextBox();
        jLabel29 = new widget.Label();
        TtglResep1 = new widget.Tanggal();
        jLabel30 = new widget.Label();
        TnmObat1 = new widget.TextBox();
        jLabel31 = new widget.Label();
        Trute1 = new widget.TextBox();
        jLabel32 = new widget.Label();
        Tdosis1 = new widget.TextBox();
        jLabel33 = new widget.Label();
        TnmDokter = new widget.TextBox();
        jLabel63 = new widget.Label();
        TrgRawat = new widget.TextBox();
        Taturan = new widget.TextBox();
        jLabel34 = new widget.Label();
        BtnDokter = new widget.Button();
        jLabel37 = new widget.Label();
        TtglMRS = new widget.TextBox();
        chkTglRanap = new widget.CekBox();
        TtglStopRanap = new widget.Tanggal();
        internalFrame4 = new widget.InternalFrame();
        Scroll5 = new widget.ScrollPane();
        tbRekonRanap = new widget.Table();
        FormInput4 = new widget.PanelBiasa();
        Scroll6 = new widget.ScrollPane();
        tbRiwayatObatRanap = new widget.Table();
        panelGlass12 = new widget.panelisi();
        jLabel67 = new widget.Label();
        DTPCari5 = new widget.Tanggal();
        jLabel72 = new widget.Label();
        DTPCari6 = new widget.Tanggal();
        jLabel35 = new widget.Label();
        TCari2 = new widget.TextBox();
        BtnCari2 = new widget.Button();
        BtnAll2 = new widget.Button();
        panelGlass15 = new widget.panelisi();
        Scroll9 = new widget.ScrollPane();
        tbResepRanap = new widget.Table();
        panelGlass16 = new widget.panelisi();
        jLabel69 = new widget.Label();
        DTPCari7 = new widget.Tanggal();
        jLabel74 = new widget.Label();
        DTPCari8 = new widget.Tanggal();
        jLabel41 = new widget.Label();
        TCari3 = new widget.TextBox();
        BtnCari3 = new widget.Button();
        BtnAll3 = new widget.Button();
        jLabel42 = new widget.Label();
        LCount2 = new widget.Label();
        internalFrame2 = new widget.InternalFrame();
        Scroll7 = new widget.ScrollPane();
        FormInput1 = new widget.PanelBiasa();
        jLabel11 = new widget.Label();
        TNoRw1 = new widget.TextBox();
        TNoRM1 = new widget.TextBox();
        TPasien1 = new widget.TextBox();
        jLabel12 = new widget.Label();
        cmbJnsRekon = new widget.ComboBox();
        jLabel13 = new widget.Label();
        cmbRiwAlergi = new widget.ComboBox();
        TketAlergi = new widget.TextBox();
        jLabel14 = new widget.Label();
        cmbBawaObat = new widget.ComboBox();
        jLabel15 = new widget.Label();
        scrollPane3 = new widget.ScrollPane();
        TcatatanIgd = new widget.TextArea();
        scrollPane4 = new widget.ScrollPane();
        TcatatanRanap = new widget.TextArea();
        jLabel16 = new widget.Label();
        jLabel17 = new widget.Label();
        scrollPane5 = new widget.ScrollPane();
        Tketidaksesuaian = new widget.TextArea();
        scrollPane6 = new widget.ScrollPane();
        Tsaran = new widget.TextArea();
        jLabel18 = new widget.Label();
        scrollPane7 = new widget.ScrollPane();
        Tkeputusan = new widget.TextArea();
        jLabel19 = new widget.Label();
        jLabel20 = new widget.Label();
        TnmPereview = new widget.TextBox();
        BtnPereview = new widget.Button();
        jLabel21 = new widget.Label();
        TnmApoteker = new widget.TextBox();
        BtnApoteker = new widget.Button();
        jLabel22 = new widget.Label();
        TtglRekon = new widget.Tanggal();
        chkSaya1 = new widget.CekBox();
        chkSaya2 = new widget.CekBox();
        BtnCopasIgd = new widget.Button();
        BtnCopasRanap = new widget.Button();
        jLabel38 = new widget.Label();
        scrollPane8 = new widget.ScrollPane();
        TcatatanRiwayat = new widget.TextArea();
        BtnCopasRiwayat = new widget.Button();
        panelGlass13 = new widget.panelisi();
        Scroll4 = new widget.ScrollPane();
        tbRekon = new widget.Table();
        panelGlass10 = new widget.panelisi();
        jLabel65 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel70 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        Scroll11 = new widget.ScrollPane();
        LoadHTML1 = new widget.editorpane();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnGanti = new widget.Button();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Rekonsiliasi Obat ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        TabRekon.setBackground(new java.awt.Color(255, 255, 254));
        TabRekon.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TabRekon.setName("TabRekon"); // NOI18N
        TabRekon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRekonMouseClicked(evt);
            }
        });

        PanelInput3.setName("PanelInput3"); // NOI18N
        PanelInput3.setOpaque(false);
        PanelInput3.setPreferredSize(new java.awt.Dimension(192, 300));
        PanelInput3.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput5.setName("FormInput5"); // NOI18N
        FormInput5.setPreferredSize(new java.awt.Dimension(190, 185));
        FormInput5.setLayout(null);

        jLabel43.setForeground(new java.awt.Color(0, 0, 0));
        jLabel43.setText("No. Rawat : ");
        jLabel43.setName("jLabel43"); // NOI18N
        FormInput5.add(jLabel43);
        jLabel43.setBounds(0, 10, 135, 23);

        TNoRw4.setEditable(false);
        TNoRw4.setBackground(new java.awt.Color(245, 250, 240));
        TNoRw4.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw4.setName("TNoRw4"); // NOI18N
        FormInput5.add(TNoRw4);
        TNoRw4.setBounds(136, 10, 122, 23);

        TNoRM4.setEditable(false);
        TNoRM4.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM4.setName("TNoRM4"); // NOI18N
        FormInput5.add(TNoRM4);
        TNoRM4.setBounds(263, 10, 70, 23);

        TPasien4.setEditable(false);
        TPasien4.setBackground(new java.awt.Color(245, 250, 240));
        TPasien4.setForeground(new java.awt.Color(0, 0, 0));
        TPasien4.setName("TPasien4"); // NOI18N
        FormInput5.add(TPasien4);
        TPasien4.setBounds(337, 10, 390, 23);

        jLabel45.setForeground(new java.awt.Color(0, 0, 0));
        jLabel45.setText("Nama Obat : ");
        jLabel45.setName("jLabel45"); // NOI18N
        FormInput5.add(jLabel45);
        jLabel45.setBounds(0, 66, 135, 23);

        TnmObat2.setForeground(new java.awt.Color(0, 0, 0));
        TnmObat2.setName("TnmObat2"); // NOI18N
        TnmObat2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmObat2KeyPressed(evt);
            }
        });
        FormInput5.add(TnmObat2);
        TnmObat2.setBounds(136, 66, 407, 23);

        jLabel46.setForeground(new java.awt.Color(0, 0, 0));
        jLabel46.setText("Cara Pemberian/Rute : ");
        jLabel46.setName("jLabel46"); // NOI18N
        FormInput5.add(jLabel46);
        jLabel46.setBounds(0, 94, 135, 23);

        Trute2.setForeground(new java.awt.Color(0, 0, 0));
        Trute2.setName("Trute2"); // NOI18N
        Trute2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Trute2KeyPressed(evt);
            }
        });
        FormInput5.add(Trute2);
        Trute2.setBounds(136, 94, 250, 23);

        jLabel47.setForeground(new java.awt.Color(0, 0, 0));
        jLabel47.setText("Dosis : ");
        jLabel47.setName("jLabel47"); // NOI18N
        FormInput5.add(jLabel47);
        jLabel47.setBounds(0, 122, 135, 23);

        Tdosis2.setForeground(new java.awt.Color(0, 0, 0));
        Tdosis2.setName("Tdosis2"); // NOI18N
        Tdosis2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tdosis2KeyPressed(evt);
            }
        });
        FormInput5.add(Tdosis2);
        Tdosis2.setBounds(136, 122, 250, 23);

        jLabel64.setForeground(new java.awt.Color(0, 0, 0));
        jLabel64.setText("Ruang Rawat : ");
        jLabel64.setName("jLabel64"); // NOI18N
        FormInput5.add(jLabel64);
        jLabel64.setBounds(0, 38, 135, 23);

        TrgRawat1.setEditable(false);
        TrgRawat1.setForeground(new java.awt.Color(0, 0, 0));
        TrgRawat1.setName("TrgRawat1"); // NOI18N
        FormInput5.add(TrgRawat1);
        TrgRawat1.setBounds(136, 38, 340, 23);

        Taturan1.setForeground(new java.awt.Color(0, 0, 0));
        Taturan1.setName("Taturan1"); // NOI18N
        Taturan1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Taturan1KeyPressed(evt);
            }
        });
        FormInput5.add(Taturan1);
        Taturan1.setBounds(476, 94, 250, 23);

        jLabel49.setForeground(new java.awt.Color(0, 0, 0));
        jLabel49.setText("Aturan Pakai :");
        jLabel49.setName("jLabel49"); // NOI18N
        FormInput5.add(jLabel49);
        jLabel49.setBounds(386, 94, 86, 23);

        jLabel50.setForeground(new java.awt.Color(0, 0, 0));
        jLabel50.setText("Tgl. Masuk RS : ");
        jLabel50.setName("jLabel50"); // NOI18N
        FormInput5.add(jLabel50);
        jLabel50.setBounds(480, 38, 95, 23);

        TtglMRS2.setEditable(false);
        TtglMRS2.setBackground(new java.awt.Color(245, 250, 240));
        TtglMRS2.setForeground(new java.awt.Color(0, 0, 0));
        TtglMRS2.setName("TtglMRS2"); // NOI18N
        FormInput5.add(TtglMRS2);
        TtglMRS2.setBounds(577, 38, 150, 23);

        jLabel54.setForeground(new java.awt.Color(0, 0, 0));
        jLabel54.setText("Sumber Obat : ");
        jLabel54.setName("jLabel54"); // NOI18N
        FormInput5.add(jLabel54);
        jLabel54.setBounds(0, 150, 135, 23);

        Tsumber.setForeground(new java.awt.Color(0, 0, 0));
        Tsumber.setName("Tsumber"); // NOI18N
        Tsumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TsumberKeyPressed(evt);
            }
        });
        FormInput5.add(Tsumber);
        Tsumber.setBounds(136, 150, 407, 23);

        jLabel51.setForeground(new java.awt.Color(0, 0, 0));
        jLabel51.setText("Dilanjutkan : ");
        jLabel51.setName("jLabel51"); // NOI18N
        FormInput5.add(jLabel51);
        jLabel51.setBounds(545, 150, 80, 23);

        cmbDilanjut1.setForeground(new java.awt.Color(0, 0, 0));
        cmbDilanjut1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbDilanjut1.setName("cmbDilanjut1"); // NOI18N
        FormInput5.add(cmbDilanjut1);
        cmbDilanjut1.setBounds(627, 150, 60, 23);

        PanelInput3.add(FormInput5, java.awt.BorderLayout.PAGE_START);

        Scroll10.setName("Scroll10"); // NOI18N
        Scroll10.setOpaque(true);
        Scroll10.setPreferredSize(new java.awt.Dimension(452, 500));

        tbRiwayat.setToolTipText("Silahkan klik untuk memilih data yang diperbaiki");
        tbRiwayat.setName("tbRiwayat"); // NOI18N
        tbRiwayat.getTableHeader().setReorderingAllowed(false);
        tbRiwayat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRiwayatMouseClicked(evt);
            }
        });
        tbRiwayat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbRiwayatKeyPressed(evt);
            }
        });
        Scroll10.setViewportView(tbRiwayat);

        PanelInput3.add(Scroll10, java.awt.BorderLayout.CENTER);

        TabRekon.addTab(".: Riwayat Penggunaan Obat Hingga Saat Ini", PanelInput3);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 300));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(190, 160));
        FormInput.setLayout(null);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("No. Rawat : ");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(0, 10, 135, 23);

        TNoRw2.setEditable(false);
        TNoRw2.setBackground(new java.awt.Color(245, 250, 240));
        TNoRw2.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw2.setName("TNoRw2"); // NOI18N
        FormInput.add(TNoRw2);
        TNoRw2.setBounds(136, 10, 122, 23);

        TNoRM2.setEditable(false);
        TNoRM2.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM2.setName("TNoRM2"); // NOI18N
        FormInput.add(TNoRM2);
        TNoRM2.setBounds(263, 10, 70, 23);

        TPasien2.setEditable(false);
        TPasien2.setBackground(new java.awt.Color(245, 250, 240));
        TPasien2.setForeground(new java.awt.Color(0, 0, 0));
        TPasien2.setName("TPasien2"); // NOI18N
        FormInput.add(TPasien2);
        TPasien2.setBounds(337, 10, 390, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Tgl. Resep / Mulai : ");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(0, 38, 135, 23);

        TtglResep.setEditable(false);
        TtglResep.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "31-08-2026" }));
        TtglResep.setDisplayFormat("dd-MM-yyyy");
        TtglResep.setName("TtglResep"); // NOI18N
        TtglResep.setOpaque(false);
        TtglResep.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglResep);
        TtglResep.setBounds(136, 38, 90, 23);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Nama Obat :");
        jLabel3.setName("jLabel3"); // NOI18N
        FormInput.add(jLabel3);
        jLabel3.setBounds(225, 38, 90, 23);

        TnmObat.setEditable(false);
        TnmObat.setForeground(new java.awt.Color(0, 0, 0));
        TnmObat.setName("TnmObat"); // NOI18N
        FormInput.add(TnmObat);
        TnmObat.setBounds(320, 38, 407, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Cara Pemberian/Rute : ");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(0, 66, 135, 23);

        Trute.setForeground(new java.awt.Color(0, 0, 0));
        Trute.setName("Trute"); // NOI18N
        Trute.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TruteKeyPressed(evt);
            }
        });
        FormInput.add(Trute);
        Trute.setBounds(136, 66, 315, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Dosis : ");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(0, 94, 135, 23);

        Tdosis.setForeground(new java.awt.Color(0, 0, 0));
        Tdosis.setName("Tdosis"); // NOI18N
        Tdosis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdosisKeyPressed(evt);
            }
        });
        FormInput.add(Tdosis);
        Tdosis.setBounds(136, 94, 590, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Dilanjutkan : ");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 122, 135, 23);

        cmbDilanjut.setForeground(new java.awt.Color(0, 0, 0));
        cmbDilanjut.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbDilanjut.setName("cmbDilanjut"); // NOI18N
        cmbDilanjut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDilanjutActionPerformed(evt);
            }
        });
        FormInput.add(cmbDilanjut);
        cmbDilanjut.setBounds(136, 122, 60, 23);

        Tdilanjutkan.setForeground(new java.awt.Color(0, 0, 0));
        Tdilanjutkan.setName("Tdilanjutkan"); // NOI18N
        Tdilanjutkan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdilanjutkanKeyPressed(evt);
            }
        });
        FormInput.add(Tdilanjutkan);
        Tdilanjutkan.setBounds(202, 122, 524, 23);

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Tgl. Masuk RS : ");
        jLabel36.setName("jLabel36"); // NOI18N
        FormInput.add(jLabel36);
        jLabel36.setBounds(450, 66, 123, 23);

        TtglMRS1.setEditable(false);
        TtglMRS1.setBackground(new java.awt.Color(245, 250, 240));
        TtglMRS1.setForeground(new java.awt.Color(0, 0, 0));
        TtglMRS1.setName("TtglMRS1"); // NOI18N
        FormInput.add(TtglMRS1);
        TtglMRS1.setBounds(575, 66, 152, 23);

        chkTglIgd.setBackground(new java.awt.Color(255, 255, 250));
        chkTglIgd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTglIgd.setForeground(new java.awt.Color(0, 0, 0));
        chkTglIgd.setText("Tgl. Stop :");
        chkTglIgd.setBorderPainted(true);
        chkTglIgd.setBorderPaintedFlat(true);
        chkTglIgd.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkTglIgd.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTglIgd.setName("chkTglIgd"); // NOI18N
        chkTglIgd.setOpaque(false);
        chkTglIgd.setPreferredSize(new java.awt.Dimension(175, 23));
        chkTglIgd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTglIgdActionPerformed(evt);
            }
        });
        FormInput.add(chkTglIgd);
        chkTglIgd.setBounds(730, 10, 90, 23);

        TtglStopIgd.setEditable(false);
        TtglStopIgd.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "31-08-2026" }));
        TtglStopIgd.setDisplayFormat("dd-MM-yyyy");
        TtglStopIgd.setName("TtglStopIgd"); // NOI18N
        TtglStopIgd.setOpaque(false);
        TtglStopIgd.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglStopIgd);
        TtglStopIgd.setBounds(825, 10, 90, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.PAGE_START);

        internalFrame3.setBorder(null);
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.GridLayout(1, 3));

        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);
        Scroll2.setPreferredSize(new java.awt.Dimension(452, 500));

        tbRekonIgd.setToolTipText("Silahkan klik untuk memilih data yang diperbaiki");
        tbRekonIgd.setName("tbRekonIgd"); // NOI18N
        tbRekonIgd.getTableHeader().setReorderingAllowed(false);
        tbRekonIgd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRekonIgdMouseClicked(evt);
            }
        });
        tbRekonIgd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbRekonIgdKeyPressed(evt);
            }
        });
        Scroll2.setViewportView(tbRekonIgd);

        internalFrame3.add(Scroll2);

        Scroll3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Obat IGD Yang Dikeluarkan Farmasi ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);
        Scroll3.setPreferredSize(new java.awt.Dimension(452, 500));

        tbRiwayatObatIgd.setToolTipText("Silahkan klik untuk memilih data yang akan direkonsiliasi");
        tbRiwayatObatIgd.setName("tbRiwayatObatIgd"); // NOI18N
        tbRiwayatObatIgd.getTableHeader().setReorderingAllowed(false);
        tbRiwayatObatIgd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRiwayatObatIgdMouseClicked(evt);
            }
        });
        tbRiwayatObatIgd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbRiwayatObatIgdKeyPressed(evt);
            }
        });
        Scroll3.setViewportView(tbRiwayatObatIgd);

        internalFrame3.add(Scroll3);

        panelGlass9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Catatan/Resep Dokter Selama Dirawat ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass9.setLayout(new java.awt.BorderLayout());

        Scroll8.setName("Scroll8"); // NOI18N
        Scroll8.setOpaque(true);
        Scroll8.setPreferredSize(new java.awt.Dimension(452, 500));

        tbResepIgd.setName("tbResepIgd"); // NOI18N
        tbResepIgd.getTableHeader().setReorderingAllowed(false);
        Scroll8.setViewportView(tbResepIgd);

        panelGlass9.add(Scroll8, java.awt.BorderLayout.CENTER);

        panelGlass14.setName("panelGlass14"); // NOI18N
        panelGlass14.setPreferredSize(new java.awt.Dimension(44, 75));
        panelGlass14.setLayout(null);

        jLabel68.setForeground(new java.awt.Color(0, 0, 0));
        jLabel68.setText("Tanggal : ");
        jLabel68.setName("jLabel68"); // NOI18N
        jLabel68.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass14.add(jLabel68);
        jLabel68.setBounds(0, 10, 80, 23);

        DTPCari3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "31-08-2026" }));
        DTPCari3.setDisplayFormat("dd-MM-yyyy");
        DTPCari3.setName("DTPCari3"); // NOI18N
        DTPCari3.setOpaque(false);
        DTPCari3.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass14.add(DTPCari3);
        DTPCari3.setBounds(85, 10, 85, 23);

        jLabel73.setForeground(new java.awt.Color(0, 0, 0));
        jLabel73.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel73.setText("s.d.");
        jLabel73.setName("jLabel73"); // NOI18N
        jLabel73.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass14.add(jLabel73);
        jLabel73.setBounds(175, 10, 30, 23);

        DTPCari4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "31-08-2026" }));
        DTPCari4.setDisplayFormat("dd-MM-yyyy");
        DTPCari4.setName("DTPCari4"); // NOI18N
        DTPCari4.setOpaque(false);
        DTPCari4.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass14.add(DTPCari4);
        DTPCari4.setBounds(210, 10, 85, 23);

        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setText("Key Word : ");
        jLabel39.setName("jLabel39"); // NOI18N
        jLabel39.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass14.add(jLabel39);
        jLabel39.setBounds(0, 38, 80, 23);

        TCari1.setForeground(new java.awt.Color(0, 0, 0));
        TCari1.setName("TCari1"); // NOI18N
        TCari1.setPreferredSize(new java.awt.Dimension(200, 23));
        TCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari1KeyPressed(evt);
            }
        });
        panelGlass14.add(TCari1);
        TCari1.setBounds(85, 38, 200, 23);

        BtnCari1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari1.setMnemonic('2');
        BtnCari1.setText("Tampilkan Data");
        BtnCari1.setName("BtnCari1"); // NOI18N
        BtnCari1.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari1ActionPerformed(evt);
            }
        });
        panelGlass14.add(BtnCari1);
        BtnCari1.setBounds(290, 38, 130, 23);

        BtnAll1.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll1.setMnemonic('M');
        BtnAll1.setText("Semua");
        BtnAll1.setToolTipText("Alt+M");
        BtnAll1.setName("BtnAll1"); // NOI18N
        BtnAll1.setPreferredSize(new java.awt.Dimension(100, 23));
        BtnAll1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAll1ActionPerformed(evt);
            }
        });
        panelGlass14.add(BtnAll1);
        BtnAll1.setBounds(430, 38, 90, 23);

        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setText("Record : ");
        jLabel40.setName("jLabel40"); // NOI18N
        jLabel40.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass14.add(jLabel40);
        jLabel40.setBounds(300, 10, 60, 23);

        LCount1.setForeground(new java.awt.Color(0, 0, 0));
        LCount1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount1.setText("0");
        LCount1.setName("LCount1"); // NOI18N
        LCount1.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass14.add(LCount1);
        LCount1.setBounds(363, 10, 60, 23);

        panelGlass9.add(panelGlass14, java.awt.BorderLayout.PAGE_END);

        internalFrame3.add(panelGlass9);

        PanelInput.add(internalFrame3, java.awt.BorderLayout.CENTER);

        TabRekon.addTab(".: Resep Dari IGD Yang Dikeluarkan Farmasi", PanelInput);

        PanelInput2.setName("PanelInput2"); // NOI18N
        PanelInput2.setOpaque(false);
        PanelInput2.setPreferredSize(new java.awt.Dimension(192, 300));
        PanelInput2.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput3.setName("FormInput3"); // NOI18N
        FormInput3.setPreferredSize(new java.awt.Dimension(190, 185));
        FormInput3.setLayout(null);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("No. Rawat : ");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput3.add(jLabel28);
        jLabel28.setBounds(0, 10, 135, 23);

        TNoRw3.setEditable(false);
        TNoRw3.setBackground(new java.awt.Color(245, 250, 240));
        TNoRw3.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw3.setName("TNoRw3"); // NOI18N
        FormInput3.add(TNoRw3);
        TNoRw3.setBounds(136, 10, 122, 23);

        TNoRM3.setEditable(false);
        TNoRM3.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM3.setName("TNoRM3"); // NOI18N
        FormInput3.add(TNoRM3);
        TNoRM3.setBounds(263, 10, 70, 23);

        TPasien3.setEditable(false);
        TPasien3.setBackground(new java.awt.Color(245, 250, 240));
        TPasien3.setForeground(new java.awt.Color(0, 0, 0));
        TPasien3.setName("TPasien3"); // NOI18N
        FormInput3.add(TPasien3);
        TPasien3.setBounds(337, 10, 390, 23);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("Tgl. Resep / Mulai : ");
        jLabel29.setName("jLabel29"); // NOI18N
        FormInput3.add(jLabel29);
        jLabel29.setBounds(0, 66, 135, 23);

        TtglResep1.setEditable(false);
        TtglResep1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "31-08-2026" }));
        TtglResep1.setDisplayFormat("dd-MM-yyyy");
        TtglResep1.setName("TtglResep1"); // NOI18N
        TtglResep1.setOpaque(false);
        TtglResep1.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput3.add(TtglResep1);
        TtglResep1.setBounds(136, 66, 90, 23);

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Nama Obat :");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput3.add(jLabel30);
        jLabel30.setBounds(225, 66, 90, 23);

        TnmObat1.setEditable(false);
        TnmObat1.setForeground(new java.awt.Color(0, 0, 0));
        TnmObat1.setName("TnmObat1"); // NOI18N
        FormInput3.add(TnmObat1);
        TnmObat1.setBounds(320, 66, 407, 23);

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("Cara Pemberian/Rute : ");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput3.add(jLabel31);
        jLabel31.setBounds(0, 94, 135, 23);

        Trute1.setForeground(new java.awt.Color(0, 0, 0));
        Trute1.setName("Trute1"); // NOI18N
        Trute1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Trute1KeyPressed(evt);
            }
        });
        FormInput3.add(Trute1);
        Trute1.setBounds(136, 94, 250, 23);

        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Dosis : ");
        jLabel32.setName("jLabel32"); // NOI18N
        FormInput3.add(jLabel32);
        jLabel32.setBounds(0, 122, 135, 23);

        Tdosis1.setForeground(new java.awt.Color(0, 0, 0));
        Tdosis1.setName("Tdosis1"); // NOI18N
        Tdosis1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tdosis1KeyPressed(evt);
            }
        });
        FormInput3.add(Tdosis1);
        Tdosis1.setBounds(136, 122, 590, 23);

        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("Nama Dokter : ");
        jLabel33.setName("jLabel33"); // NOI18N
        FormInput3.add(jLabel33);
        jLabel33.setBounds(0, 150, 135, 23);

        TnmDokter.setEditable(false);
        TnmDokter.setForeground(new java.awt.Color(0, 0, 0));
        TnmDokter.setName("TnmDokter"); // NOI18N
        FormInput3.add(TnmDokter);
        TnmDokter.setBounds(136, 150, 550, 23);

        jLabel63.setForeground(new java.awt.Color(0, 0, 0));
        jLabel63.setText("Ruang Rawat : ");
        jLabel63.setName("jLabel63"); // NOI18N
        FormInput3.add(jLabel63);
        jLabel63.setBounds(0, 38, 135, 23);

        TrgRawat.setEditable(false);
        TrgRawat.setForeground(new java.awt.Color(0, 0, 0));
        TrgRawat.setName("TrgRawat"); // NOI18N
        FormInput3.add(TrgRawat);
        TrgRawat.setBounds(136, 38, 340, 23);

        Taturan.setForeground(new java.awt.Color(0, 0, 0));
        Taturan.setName("Taturan"); // NOI18N
        Taturan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TaturanKeyPressed(evt);
            }
        });
        FormInput3.add(Taturan);
        Taturan.setBounds(476, 94, 250, 23);

        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Aturan Pakai :");
        jLabel34.setName("jLabel34"); // NOI18N
        FormInput3.add(jLabel34);
        jLabel34.setBounds(386, 94, 86, 23);

        BtnDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter.setMnemonic('2');
        BtnDokter.setToolTipText("Alt+2");
        BtnDokter.setName("BtnDokter"); // NOI18N
        BtnDokter.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokterActionPerformed(evt);
            }
        });
        FormInput3.add(BtnDokter);
        BtnDokter.setBounds(690, 150, 28, 23);

        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setText("Tgl. Masuk RS : ");
        jLabel37.setName("jLabel37"); // NOI18N
        FormInput3.add(jLabel37);
        jLabel37.setBounds(480, 38, 95, 23);

        TtglMRS.setEditable(false);
        TtglMRS.setBackground(new java.awt.Color(245, 250, 240));
        TtglMRS.setForeground(new java.awt.Color(0, 0, 0));
        TtglMRS.setName("TtglMRS"); // NOI18N
        FormInput3.add(TtglMRS);
        TtglMRS.setBounds(577, 38, 150, 23);

        chkTglRanap.setBackground(new java.awt.Color(255, 255, 250));
        chkTglRanap.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTglRanap.setForeground(new java.awt.Color(0, 0, 0));
        chkTglRanap.setText("Tgl. Stop :");
        chkTglRanap.setBorderPainted(true);
        chkTglRanap.setBorderPaintedFlat(true);
        chkTglRanap.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkTglRanap.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTglRanap.setName("chkTglRanap"); // NOI18N
        chkTglRanap.setOpaque(false);
        chkTglRanap.setPreferredSize(new java.awt.Dimension(175, 23));
        chkTglRanap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTglRanapActionPerformed(evt);
            }
        });
        FormInput3.add(chkTglRanap);
        chkTglRanap.setBounds(730, 10, 90, 23);

        TtglStopRanap.setEditable(false);
        TtglStopRanap.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "31-08-2026" }));
        TtglStopRanap.setDisplayFormat("dd-MM-yyyy");
        TtglStopRanap.setName("TtglStopRanap"); // NOI18N
        TtglStopRanap.setOpaque(false);
        TtglStopRanap.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput3.add(TtglStopRanap);
        TtglStopRanap.setBounds(825, 10, 90, 23);

        PanelInput2.add(FormInput3, java.awt.BorderLayout.PAGE_START);

        internalFrame4.setBorder(null);
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.GridLayout(1, 3));

        Scroll5.setName("Scroll5"); // NOI18N
        Scroll5.setOpaque(true);
        Scroll5.setPreferredSize(new java.awt.Dimension(452, 500));

        tbRekonRanap.setToolTipText("Silahkan klik untuk memilih data yang diperbaiki");
        tbRekonRanap.setName("tbRekonRanap"); // NOI18N
        tbRekonRanap.getTableHeader().setReorderingAllowed(false);
        tbRekonRanap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRekonRanapMouseClicked(evt);
            }
        });
        tbRekonRanap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbRekonRanapKeyPressed(evt);
            }
        });
        Scroll5.setViewportView(tbRekonRanap);

        internalFrame4.add(Scroll5);

        FormInput4.setName("FormInput4"); // NOI18N
        FormInput4.setPreferredSize(new java.awt.Dimension(190, 185));
        FormInput4.setLayout(new java.awt.BorderLayout());

        Scroll6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Obat Rawat Inap Yang Dikeluarkan Farmasi ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        Scroll6.setName("Scroll6"); // NOI18N
        Scroll6.setOpaque(true);
        Scroll6.setPreferredSize(new java.awt.Dimension(452, 500));

        tbRiwayatObatRanap.setToolTipText("Silahkan klik untuk memilih data yang akan direkonsiliasi");
        tbRiwayatObatRanap.setName("tbRiwayatObatRanap"); // NOI18N
        tbRiwayatObatRanap.getTableHeader().setReorderingAllowed(false);
        tbRiwayatObatRanap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRiwayatObatRanapMouseClicked(evt);
            }
        });
        tbRiwayatObatRanap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbRiwayatObatRanapKeyPressed(evt);
            }
        });
        Scroll6.setViewportView(tbRiwayatObatRanap);

        FormInput4.add(Scroll6, java.awt.BorderLayout.CENTER);

        panelGlass12.setName("panelGlass12"); // NOI18N
        panelGlass12.setPreferredSize(new java.awt.Dimension(44, 75));
        panelGlass12.setLayout(null);

        jLabel67.setForeground(new java.awt.Color(0, 0, 0));
        jLabel67.setText("Tanggal :");
        jLabel67.setName("jLabel67"); // NOI18N
        jLabel67.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass12.add(jLabel67);
        jLabel67.setBounds(0, 10, 85, 23);

        DTPCari5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "31-08-2026" }));
        DTPCari5.setDisplayFormat("dd-MM-yyyy");
        DTPCari5.setName("DTPCari5"); // NOI18N
        DTPCari5.setOpaque(false);
        DTPCari5.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass12.add(DTPCari5);
        DTPCari5.setBounds(91, 10, 90, 23);

        jLabel72.setForeground(new java.awt.Color(0, 0, 0));
        jLabel72.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel72.setText("s.d.");
        jLabel72.setName("jLabel72"); // NOI18N
        jLabel72.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass12.add(jLabel72);
        jLabel72.setBounds(186, 10, 23, 23);

        DTPCari6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "31-08-2026" }));
        DTPCari6.setDisplayFormat("dd-MM-yyyy");
        DTPCari6.setName("DTPCari6"); // NOI18N
        DTPCari6.setOpaque(false);
        DTPCari6.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass12.add(DTPCari6);
        DTPCari6.setBounds(214, 10, 90, 23);

        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("Key Word :");
        jLabel35.setName("jLabel35"); // NOI18N
        jLabel35.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass12.add(jLabel35);
        jLabel35.setBounds(0, 38, 85, 23);

        TCari2.setForeground(new java.awt.Color(0, 0, 0));
        TCari2.setName("TCari2"); // NOI18N
        TCari2.setPreferredSize(new java.awt.Dimension(200, 23));
        TCari2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari2KeyPressed(evt);
            }
        });
        panelGlass12.add(TCari2);
        TCari2.setBounds(91, 38, 200, 23);

        BtnCari2.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari2.setMnemonic('2');
        BtnCari2.setText("Tampilkan Data");
        BtnCari2.setName("BtnCari2"); // NOI18N
        BtnCari2.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCari2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari2ActionPerformed(evt);
            }
        });
        panelGlass12.add(BtnCari2);
        BtnCari2.setBounds(300, 38, 130, 23);

        BtnAll2.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll2.setMnemonic('M');
        BtnAll2.setText("Semua");
        BtnAll2.setToolTipText("Alt+M");
        BtnAll2.setName("BtnAll2"); // NOI18N
        BtnAll2.setPreferredSize(new java.awt.Dimension(100, 23));
        BtnAll2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAll2ActionPerformed(evt);
            }
        });
        panelGlass12.add(BtnAll2);
        BtnAll2.setBounds(440, 38, 100, 23);

        FormInput4.add(panelGlass12, java.awt.BorderLayout.PAGE_END);

        internalFrame4.add(FormInput4);

        panelGlass15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Catatan/Resep Dokter Selama Dirawat ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        panelGlass15.setName("panelGlass15"); // NOI18N
        panelGlass15.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass15.setLayout(new java.awt.BorderLayout());

        Scroll9.setName("Scroll9"); // NOI18N
        Scroll9.setOpaque(true);
        Scroll9.setPreferredSize(new java.awt.Dimension(452, 500));

        tbResepRanap.setName("tbResepRanap"); // NOI18N
        tbResepRanap.getTableHeader().setReorderingAllowed(false);
        Scroll9.setViewportView(tbResepRanap);

        panelGlass15.add(Scroll9, java.awt.BorderLayout.CENTER);

        panelGlass16.setName("panelGlass16"); // NOI18N
        panelGlass16.setPreferredSize(new java.awt.Dimension(44, 75));
        panelGlass16.setLayout(null);

        jLabel69.setForeground(new java.awt.Color(0, 0, 0));
        jLabel69.setText("Tanggal : ");
        jLabel69.setName("jLabel69"); // NOI18N
        jLabel69.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass16.add(jLabel69);
        jLabel69.setBounds(0, 10, 80, 23);

        DTPCari7.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "31-08-2026" }));
        DTPCari7.setDisplayFormat("dd-MM-yyyy");
        DTPCari7.setName("DTPCari7"); // NOI18N
        DTPCari7.setOpaque(false);
        DTPCari7.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass16.add(DTPCari7);
        DTPCari7.setBounds(85, 10, 85, 23);

        jLabel74.setForeground(new java.awt.Color(0, 0, 0));
        jLabel74.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel74.setText("s.d.");
        jLabel74.setName("jLabel74"); // NOI18N
        jLabel74.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass16.add(jLabel74);
        jLabel74.setBounds(175, 10, 30, 23);

        DTPCari8.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "31-08-2026" }));
        DTPCari8.setDisplayFormat("dd-MM-yyyy");
        DTPCari8.setName("DTPCari8"); // NOI18N
        DTPCari8.setOpaque(false);
        DTPCari8.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass16.add(DTPCari8);
        DTPCari8.setBounds(210, 10, 85, 23);

        jLabel41.setForeground(new java.awt.Color(0, 0, 0));
        jLabel41.setText("Key Word : ");
        jLabel41.setName("jLabel41"); // NOI18N
        jLabel41.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass16.add(jLabel41);
        jLabel41.setBounds(0, 38, 80, 23);

        TCari3.setForeground(new java.awt.Color(0, 0, 0));
        TCari3.setName("TCari3"); // NOI18N
        TCari3.setPreferredSize(new java.awt.Dimension(200, 23));
        TCari3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari3KeyPressed(evt);
            }
        });
        panelGlass16.add(TCari3);
        TCari3.setBounds(85, 38, 200, 23);

        BtnCari3.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari3.setMnemonic('2');
        BtnCari3.setText("Tampilkan Data");
        BtnCari3.setName("BtnCari3"); // NOI18N
        BtnCari3.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCari3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari3ActionPerformed(evt);
            }
        });
        panelGlass16.add(BtnCari3);
        BtnCari3.setBounds(290, 38, 130, 23);

        BtnAll3.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll3.setMnemonic('M');
        BtnAll3.setText("Semua");
        BtnAll3.setToolTipText("Alt+M");
        BtnAll3.setName("BtnAll3"); // NOI18N
        BtnAll3.setPreferredSize(new java.awt.Dimension(100, 23));
        BtnAll3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAll3ActionPerformed(evt);
            }
        });
        panelGlass16.add(BtnAll3);
        BtnAll3.setBounds(430, 38, 90, 23);

        jLabel42.setForeground(new java.awt.Color(0, 0, 0));
        jLabel42.setText("Record : ");
        jLabel42.setName("jLabel42"); // NOI18N
        jLabel42.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass16.add(jLabel42);
        jLabel42.setBounds(300, 10, 60, 23);

        LCount2.setForeground(new java.awt.Color(0, 0, 0));
        LCount2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount2.setText("0");
        LCount2.setName("LCount2"); // NOI18N
        LCount2.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass16.add(LCount2);
        LCount2.setBounds(363, 10, 60, 23);

        panelGlass15.add(panelGlass16, java.awt.BorderLayout.PAGE_END);

        internalFrame4.add(panelGlass15);

        PanelInput2.add(internalFrame4, java.awt.BorderLayout.CENTER);

        TabRekon.addTab(".: Resep R. Inap Yang Dikeluarkan Farmasi", PanelInput2);

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll7.setName("Scroll7"); // NOI18N
        Scroll7.setOpaque(true);

        FormInput1.setName("FormInput1"); // NOI18N
        FormInput1.setPreferredSize(new java.awt.Dimension(190, 921));
        FormInput1.setLayout(null);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("No. Rawat : ");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput1.add(jLabel11);
        jLabel11.setBounds(0, 10, 135, 23);

        TNoRw1.setEditable(false);
        TNoRw1.setBackground(new java.awt.Color(245, 250, 240));
        TNoRw1.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw1.setName("TNoRw1"); // NOI18N
        FormInput1.add(TNoRw1);
        TNoRw1.setBounds(136, 10, 122, 23);

        TNoRM1.setEditable(false);
        TNoRM1.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM1.setName("TNoRM1"); // NOI18N
        FormInput1.add(TNoRM1);
        TNoRM1.setBounds(263, 10, 70, 23);

        TPasien1.setEditable(false);
        TPasien1.setBackground(new java.awt.Color(245, 250, 240));
        TPasien1.setForeground(new java.awt.Color(0, 0, 0));
        TPasien1.setName("TPasien1"); // NOI18N
        FormInput1.add(TPasien1);
        TPasien1.setBounds(337, 10, 390, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Jns. Rekonsiliasi : ");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput1.add(jLabel12);
        jLabel12.setBounds(0, 38, 135, 23);

        cmbJnsRekon.setForeground(new java.awt.Color(0, 0, 0));
        cmbJnsRekon.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Masuk RS", "Transfer Antar Ruangan", "Pulang" }));
        cmbJnsRekon.setName("cmbJnsRekon"); // NOI18N
        FormInput1.add(cmbJnsRekon);
        cmbJnsRekon.setBounds(136, 38, 150, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Riwayat Alergi :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput1.add(jLabel13);
        jLabel13.setBounds(290, 38, 90, 23);

        cmbRiwAlergi.setForeground(new java.awt.Color(0, 0, 0));
        cmbRiwAlergi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak", "Ya" }));
        cmbRiwAlergi.setName("cmbRiwAlergi"); // NOI18N
        cmbRiwAlergi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRiwAlergiActionPerformed(evt);
            }
        });
        FormInput1.add(cmbRiwAlergi);
        cmbRiwAlergi.setBounds(385, 38, 60, 23);

        TketAlergi.setBackground(new java.awt.Color(245, 250, 240));
        TketAlergi.setForeground(new java.awt.Color(0, 0, 0));
        TketAlergi.setName("TketAlergi"); // NOI18N
        TketAlergi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketAlergiKeyPressed(evt);
            }
        });
        FormInput1.add(TketAlergi);
        TketAlergi.setBounds(450, 38, 277, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Bawa Obat Dari Luar : ");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput1.add(jLabel14);
        jLabel14.setBounds(0, 66, 135, 23);

        cmbBawaObat.setForeground(new java.awt.Color(0, 0, 0));
        cmbBawaObat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak", "Ya" }));
        cmbBawaObat.setName("cmbBawaObat"); // NOI18N
        FormInput1.add(cmbBawaObat);
        cmbBawaObat.setBounds(136, 66, 60, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("<html><div style=\"text-align: right;\">Catatan Penggunaan :&nbsp;<br>Obat IGD&nbsp;&nbsp;&nbsp;</div></html> ");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput1.add(jLabel15);
        jLabel15.setBounds(0, 220, 135, 30);

        scrollPane3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane3.setName("scrollPane3"); // NOI18N

        TcatatanIgd.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TcatatanIgd.setColumns(20);
        TcatatanIgd.setRows(5);
        TcatatanIgd.setName("TcatatanIgd"); // NOI18N
        TcatatanIgd.setPreferredSize(new java.awt.Dimension(162, 3000));
        TcatatanIgd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TcatatanIgdKeyPressed(evt);
            }
        });
        scrollPane3.setViewportView(TcatatanIgd);

        FormInput1.add(scrollPane3);
        scrollPane3.setBounds(136, 220, 590, 120);

        scrollPane4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane4.setName("scrollPane4"); // NOI18N

        TcatatanRanap.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TcatatanRanap.setColumns(20);
        TcatatanRanap.setRows(5);
        TcatatanRanap.setName("TcatatanRanap"); // NOI18N
        TcatatanRanap.setPreferredSize(new java.awt.Dimension(162, 3000));
        TcatatanRanap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TcatatanRanapKeyPressed(evt);
            }
        });
        scrollPane4.setViewportView(TcatatanRanap);

        FormInput1.add(scrollPane4);
        scrollPane4.setBounds(136, 345, 590, 120);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("<html><div style=\"text-align: right;\">Catatan Penggunaan :&nbsp;<br>Obat Rawat Inap&nbsp;&nbsp;&nbsp;</div></html> ");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput1.add(jLabel16);
        jLabel16.setBounds(0, 345, 135, 30);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Ketidaksesuaian : ");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput1.add(jLabel17);
        jLabel17.setBounds(0, 471, 135, 23);

        scrollPane5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane5.setName("scrollPane5"); // NOI18N

        Tketidaksesuaian.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tketidaksesuaian.setColumns(20);
        Tketidaksesuaian.setRows(5);
        Tketidaksesuaian.setName("Tketidaksesuaian"); // NOI18N
        Tketidaksesuaian.setPreferredSize(new java.awt.Dimension(162, 3000));
        Tketidaksesuaian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketidaksesuaianKeyPressed(evt);
            }
        });
        scrollPane5.setViewportView(Tketidaksesuaian);

        FormInput1.add(scrollPane5);
        scrollPane5.setBounds(136, 471, 590, 120);

        scrollPane6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane6.setName("scrollPane6"); // NOI18N

        Tsaran.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tsaran.setColumns(20);
        Tsaran.setRows(5);
        Tsaran.setName("Tsaran"); // NOI18N
        Tsaran.setPreferredSize(new java.awt.Dimension(162, 3000));
        Tsaran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TsaranKeyPressed(evt);
            }
        });
        scrollPane6.setViewportView(Tsaran);

        FormInput1.add(scrollPane6);
        scrollPane6.setBounds(136, 597, 590, 120);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Saran : ");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput1.add(jLabel18);
        jLabel18.setBounds(0, 597, 135, 23);

        scrollPane7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane7.setName("scrollPane7"); // NOI18N

        Tkeputusan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tkeputusan.setColumns(20);
        Tkeputusan.setRows(5);
        Tkeputusan.setName("Tkeputusan"); // NOI18N
        Tkeputusan.setPreferredSize(new java.awt.Dimension(162, 3000));
        Tkeputusan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkeputusanKeyPressed(evt);
            }
        });
        scrollPane7.setViewportView(Tkeputusan);

        FormInput1.add(scrollPane7);
        scrollPane7.setBounds(136, 724, 590, 120);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Keputusan : ");
        jLabel19.setName("jLabel19"); // NOI18N
        FormInput1.add(jLabel19);
        jLabel19.setBounds(0, 724, 135, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Direview Oleh : ");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput1.add(jLabel20);
        jLabel20.setBounds(0, 850, 135, 23);

        TnmPereview.setEditable(false);
        TnmPereview.setForeground(new java.awt.Color(0, 0, 0));
        TnmPereview.setToolTipText("Alt+C");
        TnmPereview.setName("TnmPereview"); // NOI18N
        TnmPereview.setPreferredSize(new java.awt.Dimension(140, 23));
        FormInput1.add(TnmPereview);
        TnmPereview.setBounds(136, 850, 430, 23);

        BtnPereview.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPereview.setMnemonic('2');
        BtnPereview.setToolTipText("Alt+2");
        BtnPereview.setName("BtnPereview"); // NOI18N
        BtnPereview.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPereview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPereviewActionPerformed(evt);
            }
        });
        FormInput1.add(BtnPereview);
        BtnPereview.setBounds(570, 850, 28, 23);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Apoteker : ");
        jLabel21.setName("jLabel21"); // NOI18N
        FormInput1.add(jLabel21);
        jLabel21.setBounds(0, 878, 135, 23);

        TnmApoteker.setEditable(false);
        TnmApoteker.setForeground(new java.awt.Color(0, 0, 0));
        TnmApoteker.setToolTipText("Alt+C");
        TnmApoteker.setName("TnmApoteker"); // NOI18N
        TnmApoteker.setPreferredSize(new java.awt.Dimension(140, 23));
        FormInput1.add(TnmApoteker);
        TnmApoteker.setBounds(136, 878, 430, 23);

        BtnApoteker.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnApoteker.setMnemonic('2');
        BtnApoteker.setToolTipText("Alt+2");
        BtnApoteker.setName("BtnApoteker"); // NOI18N
        BtnApoteker.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnApoteker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnApotekerActionPerformed(evt);
            }
        });
        FormInput1.add(BtnApoteker);
        BtnApoteker.setBounds(570, 878, 28, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Tgl. Rekonsiliasi :");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput1.add(jLabel22);
        jLabel22.setBounds(260, 66, 120, 23);

        TtglRekon.setEditable(false);
        TtglRekon.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "31-08-2026" }));
        TtglRekon.setDisplayFormat("dd-MM-yyyy");
        TtglRekon.setName("TtglRekon"); // NOI18N
        TtglRekon.setOpaque(false);
        TtglRekon.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput1.add(TtglRekon);
        TtglRekon.setBounds(385, 66, 90, 23);

        chkSaya1.setBackground(new java.awt.Color(242, 242, 242));
        chkSaya1.setForeground(new java.awt.Color(0, 0, 0));
        chkSaya1.setText("Saya Sendiri");
        chkSaya1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSaya1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSaya1.setName("chkSaya1"); // NOI18N
        chkSaya1.setOpaque(false);
        chkSaya1.setPreferredSize(new java.awt.Dimension(220, 23));
        chkSaya1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSaya1ActionPerformed(evt);
            }
        });
        FormInput1.add(chkSaya1);
        chkSaya1.setBounds(606, 850, 90, 23);

        chkSaya2.setBackground(new java.awt.Color(242, 242, 242));
        chkSaya2.setForeground(new java.awt.Color(0, 0, 0));
        chkSaya2.setText("Saya Sendiri");
        chkSaya2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSaya2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSaya2.setName("chkSaya2"); // NOI18N
        chkSaya2.setOpaque(false);
        chkSaya2.setPreferredSize(new java.awt.Dimension(220, 23));
        chkSaya2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSaya2ActionPerformed(evt);
            }
        });
        FormInput1.add(chkSaya2);
        chkSaya2.setBounds(606, 878, 90, 23);

        BtnCopasIgd.setForeground(new java.awt.Color(0, 0, 0));
        BtnCopasIgd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/paste.png"))); // NOI18N
        BtnCopasIgd.setMnemonic('2');
        BtnCopasIgd.setText("<html>Copy &<br>Paste Obat</html>");
        BtnCopasIgd.setName("BtnCopasIgd"); // NOI18N
        BtnCopasIgd.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCopasIgd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCopasIgdActionPerformed(evt);
            }
        });
        FormInput1.add(BtnCopasIgd);
        BtnCopasIgd.setBounds(20, 256, 110, 35);

        BtnCopasRanap.setForeground(new java.awt.Color(0, 0, 0));
        BtnCopasRanap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/paste.png"))); // NOI18N
        BtnCopasRanap.setMnemonic('2');
        BtnCopasRanap.setText("<html>Copy &<br>Paste Obat</html>");
        BtnCopasRanap.setName("BtnCopasRanap"); // NOI18N
        BtnCopasRanap.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCopasRanap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCopasRanapActionPerformed(evt);
            }
        });
        FormInput1.add(BtnCopasRanap);
        BtnCopasRanap.setBounds(20, 381, 110, 35);

        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("<html><div style=\"text-align: right;\">Catatan Riwayat :&nbsp;<br>Penggunaan Obat&nbsp;&nbsp;&nbsp;</div></html> ");
        jLabel38.setName("jLabel38"); // NOI18N
        FormInput1.add(jLabel38);
        jLabel38.setBounds(0, 94, 135, 30);

        scrollPane8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane8.setName("scrollPane8"); // NOI18N

        TcatatanRiwayat.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TcatatanRiwayat.setColumns(20);
        TcatatanRiwayat.setRows(5);
        TcatatanRiwayat.setName("TcatatanRiwayat"); // NOI18N
        TcatatanRiwayat.setPreferredSize(new java.awt.Dimension(162, 3000));
        TcatatanRiwayat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TcatatanRiwayatKeyPressed(evt);
            }
        });
        scrollPane8.setViewportView(TcatatanRiwayat);

        FormInput1.add(scrollPane8);
        scrollPane8.setBounds(136, 94, 590, 120);

        BtnCopasRiwayat.setForeground(new java.awt.Color(0, 0, 0));
        BtnCopasRiwayat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/paste.png"))); // NOI18N
        BtnCopasRiwayat.setMnemonic('2');
        BtnCopasRiwayat.setText("<html>Copy &<br>Paste Obat</html>");
        BtnCopasRiwayat.setName("BtnCopasRiwayat"); // NOI18N
        BtnCopasRiwayat.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCopasRiwayat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCopasRiwayatActionPerformed(evt);
            }
        });
        FormInput1.add(BtnCopasRiwayat);
        BtnCopasRiwayat.setBounds(20, 130, 110, 35);

        Scroll7.setViewportView(FormInput1);

        internalFrame2.add(Scroll7, java.awt.BorderLayout.CENTER);

        panelGlass13.setName("panelGlass13"); // NOI18N
        panelGlass13.setPreferredSize(new java.awt.Dimension(900, 44));
        panelGlass13.setLayout(new java.awt.BorderLayout());

        Scroll4.setName("Scroll4"); // NOI18N
        Scroll4.setOpaque(true);

        tbRekon.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbRekon.setName("tbRekon"); // NOI18N
        tbRekon.getTableHeader().setReorderingAllowed(false);
        tbRekon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRekonMouseClicked(evt);
            }
        });
        tbRekon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbRekonKeyPressed(evt);
            }
        });
        Scroll4.setViewportView(tbRekon);

        panelGlass13.add(Scroll4, java.awt.BorderLayout.CENTER);

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel65.setForeground(new java.awt.Color(0, 0, 0));
        jLabel65.setText("Tgl. Rekonsiliasi :");
        jLabel65.setName("jLabel65"); // NOI18N
        jLabel65.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass10.add(jLabel65);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "31-08-2026" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass10.add(DTPCari1);

        jLabel70.setForeground(new java.awt.Color(0, 0, 0));
        jLabel70.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel70.setText("s.d.");
        jLabel70.setName("jLabel70"); // NOI18N
        jLabel70.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass10.add(jLabel70);

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "31-08-2026" }));
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
        TCari.setPreferredSize(new java.awt.Dimension(200, 23));
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

        BtnAll.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setText("Semua");
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(100, 23));
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
        panelGlass10.add(BtnAll);

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

        panelGlass13.add(panelGlass10, java.awt.BorderLayout.PAGE_END);

        internalFrame2.add(panelGlass13, java.awt.BorderLayout.EAST);

        TabRekon.addTab(".: Rencana Penyesuaian Pengobatan", internalFrame2);

        Scroll11.setName("Scroll11"); // NOI18N
        Scroll11.setOpaque(true);
        Scroll11.setPreferredSize(new java.awt.Dimension(452, 500));

        LoadHTML1.setBorder(null);
        LoadHTML1.setForeground(new java.awt.Color(0, 0, 0));
        LoadHTML1.setName("LoadHTML1"); // NOI18N
        Scroll11.setViewportView(LoadHTML1);

        TabRekon.addTab(".: Kesimpulan/Hasil", Scroll11);

        internalFrame1.add(TabRekon, java.awt.BorderLayout.CENTER);

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
        if (TabRekon.getSelectedIndex() == 0) {
            if (TNoRw4.getText().equals("")) {
                Valid.textKosong(TNoRw4, "Pasien");
            } else if (TnmObat2.getText().equals("")) {
                Valid.textKosong(TnmObat2, "Nama Obat");
            } else {
                if (Sequel.menyimpantf("rekonsiliasi_obat_riwayat", "?,?,?,?,?,?,?,?,?,?", "No.Rawat", 10, new String[]{
                    TNoRw4.getText(), TrgRawat1.getText(), TnmObat2.getText(), Trute2.getText(),
                    Tdosis2.getText(), Taturan1.getText(), Tsumber.getText(), cmbDilanjut1.getSelectedItem().toString(),
                    Sequel.cariIsi("select now()"), kode
                }) == true) {
                    emptTeksRiwayat();
                    tampilRekonRiwayat(TNoRw4.getText());
                }
            }
        } else if (TabRekon.getSelectedIndex() == 1) {
            if (TNoRw2.getText().equals("")) {
                Valid.textKosong(TNoRw2, "Pasien");
            } else if (kdObat.equals("")) {
                Valid.textKosong(TnmObat, "Nama Obat");
            } else {
                if (chkTglIgd.isSelected() == true) {
                    cekTglIGD = "ya";
                } else {
                    cekTglIGD = "tidak";
                }

                if (Sequel.menyimpantf("rekonsiliasi_obat_igd", "?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 11, new String[]{
                    TNoRw2.getText(), Valid.SetTgl(TtglResep.getSelectedItem() + ""), kdObat, Trute.getText(), Tdosis.getText(),
                    cmbDilanjut.getSelectedItem().toString(), Tdilanjutkan.getText(), Sequel.cariIsi("select now()"), cekTglIGD,
                    Valid.SetTgl(TtglStopIgd.getSelectedItem() + ""), kode
                }) == true) {
                    emptTeksRiwayatIGD();
                    tampilRekonIGD(TNoRw2.getText());
                    tampilRiwObatIGD();
                }
            }
        } else if (TabRekon.getSelectedIndex() == 2) {
            if (TNoRw3.getText().equals("")) {
                Valid.textKosong(TNoRw3, "Pasien");
            } else if (kdObat.equals("")) {
                Valid.textKosong(TnmObat1, "Nama Obat");
            } else {
                if (chkTglRanap.isSelected() == true) {
                    cekTglRanap = "ya";
                } else {
                    cekTglRanap = "tidak";
                }

                if (Sequel.menyimpantf("rekonsiliasi_obat_ranap", "?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 12, new String[]{
                    TNoRw3.getText(), TrgRawat.getText(), Valid.SetTgl(TtglResep1.getSelectedItem() + ""), kdObat, Trute1.getText(),
                    Tdosis1.getText(), Taturan.getText(), nipDokter, Sequel.cariIsi("select now()"), cekTglRanap,
                    Valid.SetTgl(TtglStopRanap.getSelectedItem() + ""), kode
                }) == true) {
                    emptTeksRiwayatRanap();
                    tampilRekonRanap(TNoRw3.getText());
                    tampilRiwObatRanap();
                }
            }
        } else if (TabRekon.getSelectedIndex() == 3) {
            if (TNoRw1.getText().equals("")) {
                Valid.textKosong(TNoRw1, "Pasien");
            } else {
                if (Sequel.menyimpantf("rekonsiliasi_obat", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 16, new String[]{
                    TNoRw1.getText(), cmbJnsRekon.getSelectedItem().toString(), cmbRiwAlergi.getSelectedItem().toString(),
                    TketAlergi.getText(), cmbBawaObat.getSelectedItem().toString(), TcatatanIgd.getText(), TcatatanRanap.getText(),
                    Tketidaksesuaian.getText(), Tsaran.getText(), Tkeputusan.getText(), nipPereview, nipApoteker, Valid.SetTgl(TtglRekon.getSelectedItem() + ""),
                    Sequel.cariIsi("select now()"), TcatatanRiwayat.getText(), kode
                }) == true) {
                    emptTeks();
                    tampil();
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
        if (TabRekon.getSelectedIndex() == 0) {
            emptTeksRiwayat();
            tampilRekonRiwayat(TNoRw1.getText());
            BtnPrint.setEnabled(false);
        } else if (TabRekon.getSelectedIndex() == 1) {
            emptTeksRiwayatIGD();
            tampilRekonIGD(TNoRw1.getText());
            tampilRiwObatIGD();
            BtnPrint.setEnabled(false);
        } else if (TabRekon.getSelectedIndex() == 2) {
            emptTeksRiwayatRanap();
            tampilRekonRanap(TNoRw1.getText());
            tampilRiwObatRanap();
            BtnPrint.setEnabled(false);
        } else if (TabRekon.getSelectedIndex() == 3) {
            emptTeks();
            tampil();
            BtnPrint.setEnabled(false);
        } else if (TabRekon.getSelectedIndex() == 4) {
            BtnPrint.setEnabled(true);
        }
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnBatalActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnSimpan, BtnGanti);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnGantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiActionPerformed
        if (TabRekon.getSelectedIndex() == 0) {
            if (tbRiwayat.getSelectedRow() > -1) {
                if (Sequel.mengedittf("rekonsiliasi_obat_riwayat", "waktu_simpan=?", "ruang_rawat=?, nm_obat=?, "
                        + "rute=?, dosis=?, aturan_pakai=?, sumber_obat=?, dilanjutkan=?", 8, new String[]{
                            TrgRawat1.getText(), TnmObat2.getText(), Trute2.getText(), Tdosis2.getText(), Taturan1.getText(), Tsumber.getText(),
                            cmbDilanjut1.getSelectedItem().toString(),
                            tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 10).toString()
                        }) == true) {
                    emptTeksRiwayat();
                    tampilRekonRiwayat(TNoRw4.getText());
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
                tbRiwayat.requestFocus();
            }
        } else if (TabRekon.getSelectedIndex() == 1) {
            if (tbRekonIgd.getSelectedRow() > -1) {
                if (chkTglIgd.isSelected() == true) {
                    cekTglIGD = "ya";
                } else {
                    cekTglIGD = "tidak";
                }

                if (Sequel.mengedittf("rekonsiliasi_obat_igd", "waktu_simpan=?", "tgl_resep=?, kode_brng=?, rute=?, "
                        + "dosis=?, dilanjutkan=?, ket_dilanjutkan=?, cek_stop=?, tgl_stop=?", 9, new String[]{
                            Valid.SetTgl(TtglResep.getSelectedItem() + ""), kdObat, Trute.getText(), Tdosis.getText(),
                            cmbDilanjut.getSelectedItem().toString(), Tdilanjutkan.getText(), cekTglIGD, Valid.SetTgl(TtglStopIgd.getSelectedItem() + ""),
                            tbRekonIgd.getValueAt(tbRekonIgd.getSelectedRow(), 12).toString()
                        }) == true) {
                    emptTeksRiwayatIGD();
                    tampilRekonIGD(TNoRw2.getText());
                    tampilRiwObatIGD();
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
                tbRekonIgd.requestFocus();
            }
        } else if (TabRekon.getSelectedIndex() == 2) {
            if (tbRekonRanap.getSelectedRow() > -1) {
                if (chkTglRanap.isSelected() == true) {
                    cekTglRanap = "ya";
                } else {
                    cekTglRanap = "tidak";
                }

                if (Sequel.mengedittf("rekonsiliasi_obat_ranap", "waktu_simpan=?", "ruang_rawat=?, tgl_resep=?, "
                        + "kode_brng=?, rute=?, dosis=?, aturan_pakai=?, nip_dokter=?, cek_stop=?, tgl_stop=?", 10, new String[]{
                            TrgRawat.getText(), Valid.SetTgl(TtglResep1.getSelectedItem() + ""), kdObat, Trute1.getText(),
                            Tdosis1.getText(), Taturan.getText(), nipDokter, cekTglRanap, Valid.SetTgl(TtglStopRanap.getSelectedItem() + ""),
                            tbRekonRanap.getValueAt(tbRekonRanap.getSelectedRow(), 13).toString()
                        }) == true) {
                    emptTeksRiwayatRanap();
                    tampilRekonRanap(TNoRw3.getText());
                    tampilRiwObatRanap();
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
                tbRekonRanap.requestFocus();
            }
        } else if (TabRekon.getSelectedIndex() == 3) {
            if (TNoRw1.getText().equals("")) {
                Valid.textKosong(TNoRw1, "Pasien");
            } else {
                if (tbRekon.getSelectedRow() > -1) {
                    if (Sequel.mengedittf("rekonsiliasi_obat", "waktu_simpan=?", "jns_rekon=?, riwayat_alergi=?, "
                            + "ket_riwayat_alergi=?, membawa_obat_dari_luar=?, catatan_obat_igd=?, catatan_obat_ranap=?, ketidaksesuaian=?, "
                            + "saran=?, keputusan=?, nip_pereview=?, nip_apoteker=?, tanggal=?, catatan_obat_riwayat=?", 14, new String[]{
                                cmbJnsRekon.getSelectedItem().toString(), cmbRiwAlergi.getSelectedItem().toString(),
                                TketAlergi.getText(), cmbBawaObat.getSelectedItem().toString(), TcatatanIgd.getText(), TcatatanRanap.getText(),
                                Tketidaksesuaian.getText(), Tsaran.getText(), Tkeputusan.getText(), nipPereview, nipApoteker,
                                Valid.SetTgl(TtglRekon.getSelectedItem() + ""), TcatatanRiwayat.getText(),
                                tbRekon.getValueAt(tbRekon.getSelectedRow(), 19).toString()
                            }) == true) {
                        emptTeks();
                        tampil();
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
                    tbRekon.requestFocus();
                }
            }
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
        frmUtama.getInstance().tutupDialogDiPanelUtama(this);
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            frmUtama.getInstance().tutupDialogDiPanelUtama(this);
        } else {
            Valid.pindah(evt, BtnBatal, TCari);
        }
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
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
            TCari.setText("");
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void TabRekonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRekonMouseClicked
        if (TabRekon.getSelectedIndex() == 0) {
            TNoRw4.setText(TNoRw1.getText());
            TNoRM4.setText(Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat='" + TNoRw4.getText() + "'"));
            TPasien4.setText(Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='" + TNoRM4.getText() + "'"));
            tampilRekonRiwayat(TNoRw1.getText());
            BtnPrint.setEnabled(false);
        } else if (TabRekon.getSelectedIndex() == 1) {
            TNoRw2.setText(TNoRw1.getText());
            TNoRM2.setText(Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat='" + TNoRw1.getText() + "'"));
            TPasien2.setText(Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='" + TNoRM2.getText() + "'"));
            tglreg = Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + TNoRw1.getText() + "'");
            TtglMRS1.setText(Valid.SetTglINDONESIA(tglreg));

            tampilRekonIGD(TNoRw1.getText());
            tampilRiwObatIGD();
            tampilResepDokter(TNoRw2.getText(), Valid.SetTgl(DTPCari3.getSelectedItem() + ""), Valid.SetTgl(DTPCari4.getSelectedItem() + ""), TCari1.getText());
            BtnPrint.setEnabled(false);
        } else if (TabRekon.getSelectedIndex() == 2) {
            TNoRw3.setText(TNoRw1.getText());
            TNoRM3.setText(Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat='" + TNoRw1.getText() + "'"));
            TPasien3.setText(Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='" + TNoRM3.getText() + "'"));
            tglreg = Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + TNoRw1.getText() + "'");
            TtglMRS.setText(Valid.SetTglINDONESIA(tglreg));

            tampilRekonRanap(TNoRw1.getText());
            tampilRiwObatRanap();
            tampilResepDokter(TNoRw3.getText(), Valid.SetTgl(DTPCari7.getSelectedItem() + ""), Valid.SetTgl(DTPCari8.getSelectedItem() + ""), TCari3.getText());
            BtnPrint.setEnabled(false);
        } else if (TabRekon.getSelectedIndex() == 3) {
            tampil();
            BtnPrint.setEnabled(false);
        } else if (TabRekon.getSelectedIndex() == 4) {
            if (tbRekon.getSelectedRow() == -1) {
                LoadHTML1.setText("");
                BtnPrint.setEnabled(false);
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel rencana penyesuaian pengobatan..!!");
                TabRekon.setSelectedIndex(3);
                tampil();
            } else {
                BtnPrint.setEnabled(true);
            }
        }
    }//GEN-LAST:event_TabRekonMouseClicked

    private void TruteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TruteKeyPressed
        Valid.pindah(evt, TtglResep, Tdosis);
    }//GEN-LAST:event_TruteKeyPressed

    private void TdosisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdosisKeyPressed
        Valid.pindah(evt, Trute, Tdilanjutkan);
    }//GEN-LAST:event_TdosisKeyPressed

    private void TdilanjutkanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdilanjutkanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkTglIgd.requestFocus();
        }        
    }//GEN-LAST:event_TdilanjutkanKeyPressed

    private void tbRekonIgdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRekonIgdMouseClicked
        if (tabMode2.getRowCount() != 0) {
            try {
                getDataRekonIGD();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbRekonIgdMouseClicked

    private void tbRekonIgdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbRekonIgdKeyPressed
        if (tabMode2.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataRekonIGD();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbRekonIgdKeyPressed

    private void tbRiwayatObatIgdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRiwayatObatIgdMouseClicked
        if (tabMode1.getRowCount() != 0) {
            try {                
                getDataRiwObatIGD();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbRiwayatObatIgdMouseClicked

    private void tbRiwayatObatIgdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbRiwayatObatIgdKeyPressed
        if (tabMode1.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {                    
                    getDataRiwObatIGD();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbRiwayatObatIgdKeyPressed

    private void TcatatanIgdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TcatatanIgdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TcatatanRanap.requestFocus();
        }
    }//GEN-LAST:event_TcatatanIgdKeyPressed

    private void TcatatanRanapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TcatatanRanapKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Tketidaksesuaian.requestFocus();
        }
    }//GEN-LAST:event_TcatatanRanapKeyPressed

    private void TketidaksesuaianKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketidaksesuaianKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Tsaran.requestFocus();
        }
    }//GEN-LAST:event_TketidaksesuaianKeyPressed

    private void TsaranKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TsaranKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Tkeputusan.requestFocus();
        }
    }//GEN-LAST:event_TsaranKeyPressed

    private void TkeputusanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkeputusanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            BtnPereview.requestFocus();
        }
    }//GEN-LAST:event_TkeputusanKeyPressed

    private void BtnPereviewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPereviewActionPerformed
        pilihan = 0;
        pilihan = 1;
        akses.setform("RMRekonsiliasiObat");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPereviewActionPerformed

    private void BtnApotekerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnApotekerActionPerformed
        pilihan = 0;
        pilihan = 2;
        akses.setform("RMRekonsiliasiObat");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnApotekerActionPerformed

    private void tbRekonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRekonMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbRekonMouseClicked

    private void tbRekonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbRekonKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbRekonKeyPressed

    private void TketAlergiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketAlergiKeyPressed
        Valid.pindah(evt, cmbRiwAlergi, cmbBawaObat);
    }//GEN-LAST:event_TketAlergiKeyPressed

    private void cmbRiwAlergiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRiwAlergiActionPerformed
        TketAlergi.setText("");
        if (cmbRiwAlergi.getSelectedIndex() == 2) {
            TketAlergi.setEnabled(true);
            TketAlergi.requestFocus();
        } else {
            TketAlergi.setEnabled(false);
        }
    }//GEN-LAST:event_cmbRiwAlergiActionPerformed

    private void cmbDilanjutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDilanjutActionPerformed
        Tdilanjutkan.setText("");
        if (cmbDilanjut.getSelectedIndex() == 1) {
            Tdilanjutkan.setEnabled(true);
            Tdilanjutkan.requestFocus();
        } else {
            Tdilanjutkan.setEnabled(false);
        }
    }//GEN-LAST:event_cmbDilanjutActionPerformed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (TabRekon.getSelectedIndex() == 0) {
            if (tbRiwayat.getSelectedRow() > -1) {
                x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    if (Sequel.queryu2tf("delete from rekonsiliasi_obat_riwayat where waktu_simpan=?", 1, new String[]{
                        tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 10).toString()
                    }) == true) {
                        emptTeksRiwayat();
                        tampilRekonRiwayat(TNoRw4.getText());
                    } else {
                        JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                    }
                } else {
                    emptTeksRiwayat();
                    tampilRekonRiwayat(TNoRw4.getText());
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
                tbRiwayat.requestFocus();
            }
        } else if (TabRekon.getSelectedIndex() == 1) {
            if (tbRekonIgd.getSelectedRow() > -1) {
                x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    if (Sequel.queryu2tf("delete from rekonsiliasi_obat_igd where waktu_simpan=?", 1, new String[]{
                        tbRekonIgd.getValueAt(tbRekonIgd.getSelectedRow(), 12).toString()
                    }) == true) {
                        emptTeksRiwayatIGD();
                        tampilRekonIGD(TNoRw2.getText());
                        tampilRiwObatIGD();
                    } else {
                        JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                    }
                } else {
                    emptTeksRiwayatIGD();
                    tampilRekonIGD(TNoRw2.getText());
                    tampilRiwObatIGD();
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
                tbRekonIgd.requestFocus();
            }
        } else if (TabRekon.getSelectedIndex() == 2) {
            if (tbRekonRanap.getSelectedRow() > -1) {
                x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    if (Sequel.queryu2tf("delete from rekonsiliasi_obat_ranap where waktu_simpan=?", 1, new String[]{
                        tbRekonRanap.getValueAt(tbRekonRanap.getSelectedRow(), 13).toString()
                    }) == true) {
                        emptTeksRiwayatRanap();
                        tampilRekonRanap(TNoRw3.getText());
                        tampilRiwObatRanap();
                    } else {
                        JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                    }
                } else {
                    emptTeksRiwayatRanap();
                    tampilRekonRanap(TNoRw3.getText());
                    tampilRiwObatRanap();
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
                tbRekonRanap.requestFocus();
            }
        } else if (TabRekon.getSelectedIndex() == 3) {
            if (tbRekon.getSelectedRow() > -1) {
                x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    if (Sequel.queryu2tf("delete from rekonsiliasi_obat where waktu_simpan=?", 1, new String[]{
                        tbRekon.getValueAt(tbRekon.getSelectedRow(), 19).toString()
                    }) == true) {
                        Sequel.queryu("delete from rekonsiliasi_obat_riwayat where kd_data='" + tbRekon.getValueAt(tbRekon.getSelectedRow(), 21).toString() + "'");
                        Sequel.queryu("delete from rekonsiliasi_obat_igd where kd_data='" + tbRekon.getValueAt(tbRekon.getSelectedRow(), 21).toString() + "'");
                        Sequel.queryu("delete from rekonsiliasi_obat_ranap where kd_data='" + tbRekon.getValueAt(tbRekon.getSelectedRow(), 21).toString() + "'");
                        
                        emptTeks();
                        tampil();
                    } else {
                        JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                    }
                } else {
                    emptTeks();
                    tampil();
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
                tbRekon.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampilRekonRiwayat(TNoRw1.getText());
        BtnPrint.setEnabled(false);
    }//GEN-LAST:event_formWindowOpened

    private void chkSaya1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSaya1ActionPerformed
        if (chkSaya1.isSelected() == true) {
            if (akses.getadmin() == true) {
                nipPereview = "-";
                TnmPereview.setText("-");
            } else {
                nipPereview = akses.getkode();
                TnmPereview.setText(Sequel.cariIsi("select nama from pegawai where nik='" + nipPereview + "'"));
            }
        } else {
            nipPereview = "-";
            TnmPereview.setText("-");
        }
    }//GEN-LAST:event_chkSaya1ActionPerformed

    private void chkSaya2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSaya2ActionPerformed
        if (chkSaya2.isSelected() == true) {
            if (akses.getadmin() == true) {
                nipApoteker = "-";
                TnmApoteker.setText("-");
            } else {
                nipApoteker = akses.getkode();
                TnmApoteker.setText(Sequel.cariIsi("select nama from pegawai where nik='" + nipApoteker + "'"));
            }
        } else {
            nipApoteker = "-";
            TnmApoteker.setText("-");
        }
    }//GEN-LAST:event_chkSaya2ActionPerformed

    private void Trute1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Trute1KeyPressed
        Valid.pindah(evt, TtglResep1, Taturan);
    }//GEN-LAST:event_Trute1KeyPressed

    private void Tdosis1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tdosis1KeyPressed
        Valid.pindah(evt, Taturan, BtnDokter);
    }//GEN-LAST:event_Tdosis1KeyPressed

    private void tbRekonRanapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRekonRanapMouseClicked
        if (tabMode5.getRowCount() != 0) {
            try {
                getDataRekonRanap();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbRekonRanapMouseClicked

    private void tbRekonRanapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbRekonRanapKeyPressed
        if (tabMode5.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataRekonRanap();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbRekonRanapKeyPressed

    private void tbRiwayatObatRanapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRiwayatObatRanapMouseClicked
        if (tabMode4.getRowCount() != 0) {
            try {                
                getDataRiwObatRanap();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbRiwayatObatRanapMouseClicked

    private void tbRiwayatObatRanapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbRiwayatObatRanapKeyPressed
        if (tabMode4.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {                    
                    getDataRiwObatRanap();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbRiwayatObatRanapKeyPressed

    private void TaturanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TaturanKeyPressed
        Valid.pindah(evt, Trute1, Tdosis1);
    }//GEN-LAST:event_TaturanKeyPressed

    private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
        akses.setform("RMRekonsiliasiObat");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDokterActionPerformed

    private void TCari2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari2ActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari2.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        }
    }//GEN-LAST:event_TCari2KeyPressed

    private void BtnCari2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari2ActionPerformed
        tampilRiwObatRanap();
    }//GEN-LAST:event_BtnCari2ActionPerformed

    private void BtnAll2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAll2ActionPerformed
        TCari2.setText("");
        tampilRiwObatRanap();
    }//GEN-LAST:event_BtnAll2ActionPerformed

    private void BtnCopasIgdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCopasIgdActionPerformed
        tampilRekonIGD(TNoRw1.getText());
        if (tabMode2.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, Item resep penggunaan/rekonsiliasi obat IGD masih kosong...!!!!");
        } else {
            itemObat = "";
            akses.setCopyData("");
            try {
                for (i = 0; i < tbRekonIgd.getRowCount(); i++) {
                    if (itemObat.equals("")) {
                        itemObat = tbRekonIgd.getValueAt(i, 5).toString() + ", Rute : " + tbRekonIgd.getValueAt(i, 6).toString()
                                + ", Dosis : " + tbRekonIgd.getValueAt(i, 7).toString() + ", Dilanjutkan : " + tbRekonIgd.getValueAt(i, 8).toString()
                                + ", Ket. : " + tbRekonIgd.getValueAt(i, 9).toString();
                    } else {
                        itemObat = itemObat + "\n" + tbRekonIgd.getValueAt(i, 5).toString() + ", Rute : " + tbRekonIgd.getValueAt(i, 6).toString()
                                + ", Dosis : " + tbRekonIgd.getValueAt(i, 7).toString() + ", Dilanjutkan : " + tbRekonIgd.getValueAt(i, 8).toString()
                                + ", Ket. : " + tbRekonIgd.getValueAt(i, 9).toString();
                    }
                }
                
                akses.setCopyData(itemObat);
                if (TcatatanIgd.getText().equals("")) {
                    TcatatanIgd.setText(akses.getPasteData());
                    akses.setCopyData("");
                } else {
                    TcatatanIgd.setText(TcatatanIgd.getText() + "\n\n" + akses.getPasteData());
                    akses.setCopyData("");
                }            
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }            
        }
    }//GEN-LAST:event_BtnCopasIgdActionPerformed

    private void BtnCopasRanapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCopasRanapActionPerformed
        tampilRekonRanap(TNoRw1.getText());
        if (tabMode5.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, Item resep penggunaan/rekonsiliasi obat R. Inap masih kosong...!!!!");
        } else {
            itemObat = "";
            akses.setCopyData("");
            try {
                for (i = 0; i < tbRekonRanap.getRowCount(); i++) {
                    if (itemObat.equals("")) {
                        itemObat = tbRekonRanap.getValueAt(i, 5).toString() + ", Rute : " + tbRekonRanap.getValueAt(i, 6).toString()
                                + ", Dosis : " + tbRekonRanap.getValueAt(i, 7).toString() + ", Aturan Pakai : " + tbRekonRanap.getValueAt(i, 8).toString();
                    } else {
                        itemObat = itemObat + "\n" + tbRekonRanap.getValueAt(i, 5).toString() + ", Rute : " + tbRekonRanap.getValueAt(i, 6).toString()
                                + ", Dosis : " + tbRekonRanap.getValueAt(i, 7).toString() + ", Aturan Pakai : " + tbRekonRanap.getValueAt(i, 8).toString();
                    }
                }
                
                akses.setCopyData(itemObat);
                if (TcatatanRanap.getText().equals("")) {
                    TcatatanRanap.setText(akses.getPasteData());
                    akses.setCopyData("");
                } else {
                    TcatatanRanap.setText(TcatatanRanap.getText() + "\n\n" + akses.getPasteData());
                    akses.setCopyData("");
                }            
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }            
        }
    }//GEN-LAST:event_BtnCopasRanapActionPerformed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tbRekon.getSelectedRow() > -1) {
            previewData();
        } else if (!akses.getPasteData().equals("")) {
            tampilCetak(akses.getPasteData());
            previewData();
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan pilih/klik dulu salah satu datanya pada tabel..!!!");
            tbRekon.requestFocus();
        }
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnGanti, BtnKeluar);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void chkTglIgdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTglIgdActionPerformed
        TtglStopIgd.setDate(new Date());
        if (chkTglIgd.isSelected() == true) {
            TtglStopIgd.setEnabled(true);
        } else {
            TtglStopIgd.setEnabled(false);
        }
    }//GEN-LAST:event_chkTglIgdActionPerformed

    private void chkTglRanapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTglRanapActionPerformed
        TtglStopRanap.setDate(new Date());
        if (chkTglRanap.isSelected() == true) {
            TtglStopRanap.setEnabled(true);
        } else {
            TtglStopRanap.setEnabled(false);
        }
    }//GEN-LAST:event_chkTglRanapActionPerformed

    private void TcatatanRiwayatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TcatatanRiwayatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TcatatanIgd.requestFocus();
        }
    }//GEN-LAST:event_TcatatanRiwayatKeyPressed

    private void BtnCopasRiwayatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCopasRiwayatActionPerformed
        tampilRekonRiwayat(TNoRw1.getText());
        if (tabMode7.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data riwayat penggunaan obat masih kosong/belum ada...!!!!");
        } else {
            itemObat = "";
            akses.setCopyData("");
            try {
                for (i = 0; i < tbRiwayat.getRowCount(); i++) {
                    if (itemObat.equals("")) {
                        itemObat = tbRiwayat.getValueAt(i, 4).toString() + ", Rute : " + tbRiwayat.getValueAt(i, 5).toString()
                                + ", Dosis : " + tbRiwayat.getValueAt(i, 6).toString() + ", Aturan Pakai : " + tbRiwayat.getValueAt(i, 7).toString()
                                + ", Sumber Obat : " + tbRiwayat.getValueAt(i, 8).toString();
                    } else {
                        itemObat = itemObat + "\n" + tbRiwayat.getValueAt(i, 4).toString() + ", Rute : " + tbRiwayat.getValueAt(i, 5).toString()
                                + ", Dosis : " + tbRiwayat.getValueAt(i, 6).toString() + ", Aturan Pakai : " + tbRiwayat.getValueAt(i, 7).toString()
                                + ", Sumber Obat : " + tbRiwayat.getValueAt(i, 8).toString();
                    }
                }
                
                akses.setCopyData(itemObat);
                if (TcatatanRiwayat.getText().equals("")) {
                    TcatatanRiwayat.setText(akses.getPasteData());
                    akses.setCopyData("");
                } else {
                    TcatatanRiwayat.setText(TcatatanRiwayat.getText() + "\n\n" + akses.getPasteData());
                    akses.setCopyData("");
                }            
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }            
        }
    }//GEN-LAST:event_BtnCopasRiwayatActionPerformed

    private void TCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari1ActionPerformed(null);
        }
    }//GEN-LAST:event_TCari1KeyPressed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        tampilResepDokter(TNoRw2.getText(), Valid.SetTgl(DTPCari3.getSelectedItem() + ""), Valid.SetTgl(DTPCari4.getSelectedItem() + ""), TCari1.getText());
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void BtnAll1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAll1ActionPerformed
        TCari1.setText("");
        tampilResepDokter(TNoRw2.getText(), Valid.SetTgl(DTPCari3.getSelectedItem() + ""), Valid.SetTgl(DTPCari4.getSelectedItem() + ""), TCari1.getText());
    }//GEN-LAST:event_BtnAll1ActionPerformed

    private void TCari3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari3KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari3ActionPerformed(null);
        }
    }//GEN-LAST:event_TCari3KeyPressed

    private void BtnCari3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari3ActionPerformed
        tampilResepDokter(TNoRw3.getText(), Valid.SetTgl(DTPCari7.getSelectedItem() + ""), Valid.SetTgl(DTPCari8.getSelectedItem() + ""), TCari3.getText());
    }//GEN-LAST:event_BtnCari3ActionPerformed

    private void BtnAll3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAll3ActionPerformed
        TCari3.setText("");
        tampilResepDokter(TNoRw3.getText(), Valid.SetTgl(DTPCari7.getSelectedItem() + ""), Valid.SetTgl(DTPCari8.getSelectedItem() + ""), TCari3.getText());
    }//GEN-LAST:event_BtnAll3ActionPerformed

    private void TnmObat2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmObat2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Trute2.requestFocus();
        }
    }//GEN-LAST:event_TnmObat2KeyPressed

    private void Trute2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Trute2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Taturan1.requestFocus();
        }
    }//GEN-LAST:event_Trute2KeyPressed

    private void Tdosis2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tdosis2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tsumber.requestFocus();
        }
    }//GEN-LAST:event_Tdosis2KeyPressed

    private void Taturan1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Taturan1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tdosis2.requestFocus();
        }
    }//GEN-LAST:event_Taturan1KeyPressed

    private void tbRiwayatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRiwayatMouseClicked
        if (tabMode7.getRowCount() != 0) {
            try {
                getDataRekonRiwayat();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbRiwayatMouseClicked

    private void tbRiwayatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbRiwayatKeyPressed
        if (tabMode7.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataRekonRiwayat();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbRiwayatKeyPressed

    private void TsumberKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TsumberKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbDilanjut1.requestFocus();
        }
    }//GEN-LAST:event_TsumberKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMRekonsiliasiObat dialog = new RMRekonsiliasiObat(new javax.swing.JFrame(), true);
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
    private widget.Button BtnAll2;
    private widget.Button BtnAll3;
    private widget.Button BtnApoteker;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnCari1;
    private widget.Button BtnCari2;
    private widget.Button BtnCari3;
    private widget.Button BtnCopasIgd;
    private widget.Button BtnCopasRanap;
    private widget.Button BtnCopasRiwayat;
    private widget.Button BtnDokter;
    private widget.Button BtnGanti;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPereview;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Tanggal DTPCari3;
    private widget.Tanggal DTPCari4;
    private widget.Tanggal DTPCari5;
    private widget.Tanggal DTPCari6;
    private widget.Tanggal DTPCari7;
    private widget.Tanggal DTPCari8;
    private widget.PanelBiasa FormInput;
    private widget.PanelBiasa FormInput1;
    private widget.PanelBiasa FormInput3;
    private widget.PanelBiasa FormInput4;
    private widget.PanelBiasa FormInput5;
    private widget.Label LCount;
    private widget.Label LCount1;
    private widget.Label LCount2;
    private widget.editorpane LoadHTML1;
    private javax.swing.JPanel PanelInput;
    private javax.swing.JPanel PanelInput2;
    private javax.swing.JPanel PanelInput3;
    private widget.ScrollPane Scroll10;
    private widget.ScrollPane Scroll11;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll4;
    private widget.ScrollPane Scroll5;
    private widget.ScrollPane Scroll6;
    private widget.ScrollPane Scroll7;
    private widget.ScrollPane Scroll8;
    private widget.ScrollPane Scroll9;
    public widget.TextBox TCari;
    public widget.TextBox TCari1;
    public widget.TextBox TCari2;
    public widget.TextBox TCari3;
    private widget.TextBox TNoRM1;
    private widget.TextBox TNoRM2;
    private widget.TextBox TNoRM3;
    private widget.TextBox TNoRM4;
    private widget.TextBox TNoRw1;
    private widget.TextBox TNoRw2;
    private widget.TextBox TNoRw3;
    private widget.TextBox TNoRw4;
    private widget.TextBox TPasien1;
    private widget.TextBox TPasien2;
    private widget.TextBox TPasien3;
    private widget.TextBox TPasien4;
    private javax.swing.JTabbedPane TabRekon;
    private widget.TextBox Taturan;
    private widget.TextBox Taturan1;
    private widget.TextArea TcatatanIgd;
    private widget.TextArea TcatatanRanap;
    private widget.TextArea TcatatanRiwayat;
    private widget.TextBox Tdilanjutkan;
    private widget.TextBox Tdosis;
    private widget.TextBox Tdosis1;
    private widget.TextBox Tdosis2;
    private widget.TextArea Tkeputusan;
    private widget.TextBox TketAlergi;
    private widget.TextArea Tketidaksesuaian;
    private widget.TextBox TnmApoteker;
    private widget.TextBox TnmDokter;
    private widget.TextBox TnmObat;
    private widget.TextBox TnmObat1;
    private widget.TextBox TnmObat2;
    private widget.TextBox TnmPereview;
    private widget.TextBox TrgRawat;
    private widget.TextBox TrgRawat1;
    private widget.TextBox Trute;
    private widget.TextBox Trute1;
    private widget.TextBox Trute2;
    private widget.TextArea Tsaran;
    private widget.TextBox Tsumber;
    private widget.TextBox TtglMRS;
    private widget.TextBox TtglMRS1;
    private widget.TextBox TtglMRS2;
    private widget.Tanggal TtglRekon;
    private widget.Tanggal TtglResep;
    private widget.Tanggal TtglResep1;
    private widget.Tanggal TtglStopIgd;
    private widget.Tanggal TtglStopRanap;
    private widget.CekBox chkSaya1;
    private widget.CekBox chkSaya2;
    public widget.CekBox chkTglIgd;
    public widget.CekBox chkTglRanap;
    private widget.ComboBox cmbBawaObat;
    private widget.ComboBox cmbDilanjut;
    private widget.ComboBox cmbDilanjut1;
    private widget.ComboBox cmbJnsRekon;
    private widget.ComboBox cmbRiwAlergi;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame4;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel20;
    private widget.Label jLabel21;
    private widget.Label jLabel22;
    private widget.Label jLabel28;
    private widget.Label jLabel29;
    private widget.Label jLabel3;
    private widget.Label jLabel30;
    private widget.Label jLabel31;
    private widget.Label jLabel32;
    private widget.Label jLabel33;
    private widget.Label jLabel34;
    private widget.Label jLabel35;
    private widget.Label jLabel36;
    private widget.Label jLabel37;
    private widget.Label jLabel38;
    private widget.Label jLabel39;
    private widget.Label jLabel4;
    private widget.Label jLabel40;
    private widget.Label jLabel41;
    private widget.Label jLabel42;
    private widget.Label jLabel43;
    private widget.Label jLabel45;
    private widget.Label jLabel46;
    private widget.Label jLabel47;
    private widget.Label jLabel49;
    private widget.Label jLabel5;
    private widget.Label jLabel50;
    private widget.Label jLabel51;
    private widget.Label jLabel54;
    private widget.Label jLabel6;
    private widget.Label jLabel63;
    private widget.Label jLabel64;
    private widget.Label jLabel65;
    private widget.Label jLabel67;
    private widget.Label jLabel68;
    private widget.Label jLabel69;
    private widget.Label jLabel7;
    private widget.Label jLabel70;
    private widget.Label jLabel72;
    private widget.Label jLabel73;
    private widget.Label jLabel74;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass12;
    private widget.panelisi panelGlass13;
    private widget.panelisi panelGlass14;
    private widget.panelisi panelGlass15;
    private widget.panelisi panelGlass16;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollPane3;
    private widget.ScrollPane scrollPane4;
    private widget.ScrollPane scrollPane5;
    private widget.ScrollPane scrollPane6;
    private widget.ScrollPane scrollPane7;
    private widget.ScrollPane scrollPane8;
    private widget.Table tbRekon;
    private widget.Table tbRekonIgd;
    private widget.Table tbRekonRanap;
    private widget.Table tbResepIgd;
    private widget.Table tbResepRanap;
    private widget.Table tbRiwayat;
    private widget.Table tbRiwayatObatIgd;
    private widget.Table tbRiwayatObatRanap;
    // End of variables declaration//GEN-END:variables

    public void tampil() {     
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT ro.*, p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d/%m/%Y') tglLahir, date_format(ro.tanggal,'%d/%m/%Y') tglRekon, "
                    + "pg1.nama nmPereview, pg2.nama nmApoteker FROM rekonsiliasi_obat ro inner join reg_periksa rp on rp.no_rawat=ro.no_rawat "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis inner join pegawai pg1 on pg1.nik=ro.nip_pereview inner join pegawai pg2 on pg2.nik=ro.nip_apoteker where "
                    + "ro.tanggal between ? and ? and ro.no_rawat like ? or "
                    + "ro.tanggal between ? and ? and p.no_rkm_medis like ? or "
                    + "ro.tanggal between ? and ? and p.nm_pasien like ? or "
                    + "ro.tanggal between ? and ? and ro.ket_riwayat_alergi like ? or "
                    + "ro.tanggal between ? and ? and ro.catatan_obat_igd like ? or "
                    + "ro.tanggal between ? and ? and ro.catatan_obat_ranap like ? or "
                    + "ro.tanggal between ? and ? and ro.ketidaksesuaian like ? or "
                    + "ro.tanggal between ? and ? and ro.saran like ? or "
                    + "ro.tanggal between ? and ? and ro.keputusan like ? or "
                    + "ro.tanggal between ? and ? and pg1.nama like ? or "
                    + "ro.tanggal between ? and ? and pg2.nama like ? order by ro.waktu_simpan desc");
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
                ps.setString(16, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(17, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(18, "%" + TCari.getText().trim() + "%");
                ps.setString(19, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(20, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(21, "%" + TCari.getText().trim() + "%");
                ps.setString(22, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(23, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(24, "%" + TCari.getText().trim() + "%");
                ps.setString(25, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(26, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(27, "%" + TCari.getText().trim() + "%");
                ps.setString(28, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(29, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(30, "%" + TCari.getText().trim() + "%");
                ps.setString(31, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(32, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(33, "%" + TCari.getText().trim() + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("tglLahir"),
                        rs.getString("tglRekon"),
                        rs.getString("jns_rekon"),
                        rs.getString("riwayat_alergi"),
                        rs.getString("ket_riwayat_alergi"),
                        rs.getString("membawa_obat_dari_luar"),
                        rs.getString("catatan_obat_igd"),
                        rs.getString("catatan_obat_ranap"),
                        rs.getString("ketidaksesuaian"),
                        rs.getString("saran"),
                        rs.getString("keputusan"),
                        rs.getString("nmPereview"),
                        rs.getString("nmApoteker"),
                        rs.getString("nip_pereview"),
                        rs.getString("nip_apoteker"),
                        rs.getString("tanggal"),
                        rs.getString("waktu_simpan"),
                        rs.getString("catatan_obat_riwayat"),
                        rs.getString("kd_data")
                    });
                }                
            } catch (Exception e) {
                System.out.println("rekammedis.RMRekonsiliasiObat.tampil() : " + e);
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
        cmbJnsRekon.setSelectedIndex(0);
        cmbRiwAlergi.setSelectedIndex(0);
        TketAlergi.setText("");
        TketAlergi.setEnabled(false);
        cmbBawaObat.setSelectedIndex(0);
        TtglRekon.setDate(new Date());
        TcatatanRiwayat.setText("");
        TcatatanIgd.setText("");
        TcatatanRanap.setText("");
        Tketidaksesuaian.setText("");
        Tsaran.setText("");
        Tkeputusan.setText("");
        nipPereview = "-";
        TnmPereview.setText("-");
        nipApoteker = "-";
        TnmApoteker.setText("-");
        chkSaya1.setSelected(false);
        chkSaya2.setSelected(false);
    }
    
    public void emptTeksRiwayatIGD() {
        TtglResep.setDate(new Date());
        TnmObat.setText("");
        Trute.setText("");
        Tdosis.setText("");
        cmbDilanjut.setSelectedIndex(0);
        Tdilanjutkan.setText("");
        Tdilanjutkan.setEnabled(false);
        chkTglIgd.setSelected(false);
        TtglStopIgd.setEnabled(false);
        TtglStopIgd.setDate(new Date());
    }
    
    public void emptTeksRiwayatRanap() {
        TtglResep1.setDate(new Date());
        TrgRawat.setText("");
        TnmObat1.setText("");
        Trute1.setText("");
        Taturan.setText("");
        Tdosis1.setText("");
        nipDokter = "-";
        TnmDokter.setText("-");
        chkTglRanap.setSelected(false);
        TtglStopRanap.setEnabled(false);
        TtglStopRanap.setDate(new Date());
    }

    private void getData() {
        nipPereview = ""; 
        nipApoteker = "";
        akses.setCopyData("");
        
        if (tbRekon.getSelectedRow() != -1) {
            TNoRw1.setText(tbRekon.getValueAt(tbRekon.getSelectedRow(), 0).toString());
            TNoRM1.setText(tbRekon.getValueAt(tbRekon.getSelectedRow(), 1).toString());
            TPasien1.setText(tbRekon.getValueAt(tbRekon.getSelectedRow(), 2).toString());            
            TNoRw2.setText(TNoRw1.getText());
            TNoRM2.setText(TNoRM1.getText());
            TPasien2.setText(TPasien1.getText());            
            cmbJnsRekon.setSelectedItem(tbRekon.getValueAt(tbRekon.getSelectedRow(), 5).toString());
            cmbRiwAlergi.setSelectedItem(tbRekon.getValueAt(tbRekon.getSelectedRow(), 6).toString());
            TketAlergi.setText(tbRekon.getValueAt(tbRekon.getSelectedRow(), 7).toString());
            cmbBawaObat.setSelectedItem(tbRekon.getValueAt(tbRekon.getSelectedRow(), 8).toString());
            TcatatanIgd.setText(tbRekon.getValueAt(tbRekon.getSelectedRow(), 9).toString());
            TcatatanRanap.setText(tbRekon.getValueAt(tbRekon.getSelectedRow(), 10).toString());
            Tketidaksesuaian.setText(tbRekon.getValueAt(tbRekon.getSelectedRow(), 11).toString());
            Tsaran.setText(tbRekon.getValueAt(tbRekon.getSelectedRow(), 12).toString());
            Tkeputusan.setText(tbRekon.getValueAt(tbRekon.getSelectedRow(), 13).toString());
            TnmPereview.setText(tbRekon.getValueAt(tbRekon.getSelectedRow(), 14).toString());
            TnmApoteker.setText(tbRekon.getValueAt(tbRekon.getSelectedRow(), 15).toString());
            nipPereview = tbRekon.getValueAt(tbRekon.getSelectedRow(), 16).toString();
            nipApoteker = tbRekon.getValueAt(tbRekon.getSelectedRow(), 17).toString();
            Valid.SetTgl(TtglRekon, tbRekon.getValueAt(tbRekon.getSelectedRow(), 18).toString());
            TcatatanRiwayat.setText(tbRekon.getValueAt(tbRekon.getSelectedRow(), 20).toString());
            akses.setCopyData(tbRekon.getValueAt(tbRekon.getSelectedRow(), 19).toString());
            tampilCetak(tbRekon.getValueAt(tbRekon.getSelectedRow(), 19).toString());
            
            if (cmbRiwAlergi.getSelectedIndex() == 2) {
                TketAlergi.setEnabled(true);
            } else {
                TketAlergi.setEnabled(false);
            }
        }
    }
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.getberi_obat());
        BtnGanti.setEnabled(akses.getberi_obat());
        BtnHapus.setEnabled(akses.getberi_obat());
    }
    
    private void tampilRiwObatIGD() {
        Valid.tabelKosong(tabMode1);
        try {
            ps1 = koneksi.prepareStatement("SELECT DATE_FORMAT(dpo.tgl_perawatan,'%d-%m-%Y') tglResep, pl.nm_poli, db.nama_brng, "
                    + "CONCAT(dpo.jml ,' ',LOWER(db.kode_sat)) jlh, d.nm_dokter, dpo.kode_brng, dpo.tgl_perawatan "
                    + "FROM detail_pemberian_obat dpo INNER JOIN databarang db ON dpo.kode_brng=db.kode_brng "
                    + "INNER JOIN reg_periksa rp ON rp.no_rawat=dpo.no_rawat INNER JOIN dokter d on d.kd_dokter=rp.kd_dokter INNER JOIN poliklinik pl on pl.kd_poli=rp.kd_poli "
                    + "WHERE dpo.no_rawat='" + TNoRw2.getText() + "' and dpo.status='ralan' order by dpo.tgl_perawatan, dpo.jam");
            try {
                rs1 = ps1.executeQuery();
                x = 1;
                while (rs1.next()) {
                    tabMode1.addRow(new String[]{
                        x + ".",
                        rs1.getString("tglResep"),
                        rs1.getString("nm_poli"),
                        rs1.getString("nama_brng"),
                        rs1.getString("jlh"),
                        rs1.getString("nm_dokter"),
                        rs1.getString("kode_brng"),
                        rs1.getString("tgl_perawatan")
                    });
                    x++;
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
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilRiwObatRanap() {
        ceknmDokter = "";
        cekNipDokter = "";
        Valid.tabelKosong(tabMode4);
        try {
            ps4 = koneksi.prepareStatement("SELECT DATE_FORMAT(dpo.tgl_perawatan,'%d-%m-%Y') tglResep, db.nama_brng, ifnull(b.nm_bangsal,'-') rgrawat, "
                    + "CONCAT(dpo.jml,' ',LOWER(db.kode_sat)) jlh, dpo.kode_brng, dpo.tgl_perawatan, dpo.no_rawat, ro.kode_unit FROM detail_pemberian_obat dpo "
                    + "INNER JOIN databarang db ON dpo.kode_brng=db.kode_brng INNER JOIN resep_obat ro on ro.no_rawat=dpo.no_rawat and ro.tgl_perawatan=dpo.tgl_perawatan and ro.jam=dpo.jam "
                    + "INNER JOIN kamar k on k.kd_kamar=ro.kode_unit INNER JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal WHERE "
                    + "dpo.tgl_perawatan between ? and ? and dpo.no_rawat='" + TNoRw3.getText() + "' and dpo.status='ranap' and db.nama_brng like ? or "
                    + "dpo.tgl_perawatan between ? and ? and dpo.no_rawat='" + TNoRw3.getText() + "' and dpo.status='ranap' and b.nm_bangsal like ? "
                    + "order by dpo.tgl_perawatan, dpo.jam");
            try {
                ps4.setString(1, Valid.SetTgl(DTPCari5.getSelectedItem() + ""));
                ps4.setString(2, Valid.SetTgl(DTPCari6.getSelectedItem() + ""));
                ps4.setString(3, "%" + TCari2.getText().trim() + "%");
                ps4.setString(4, Valid.SetTgl(DTPCari5.getSelectedItem() + ""));
                ps4.setString(5, Valid.SetTgl(DTPCari6.getSelectedItem() + ""));
                ps4.setString(6, "%" + TCari2.getText().trim() + "%");
                rs4 = ps4.executeQuery();                
                x = 1;
                while (rs4.next()) {
                    if (Sequel.cariInteger("select count(-1) from catatan_resep_ranap where no_rawat='" + rs4.getString("no_rawat") + "' "
                            + "and tgl_perawatan='" + rs4.getString("tgl_perawatan") + "'") == 0) {
                        cekNipDokter = "-";
                        ceknmDokter = "-";
                    } else {
                        cekNipDokter = Sequel.cariIsi("select d.kd_dokter from catatan_resep_ranap c inner join dokter d on d.kd_dokter=c.kd_dokter where "
                                + "c.no_rawat='" + rs4.getString("no_rawat") + "' and c.tgl_perawatan='" + rs4.getString("tgl_perawatan") + "' "
                                + "order by c.noId desc limit 1");
                        ceknmDokter = Sequel.cariIsi("select d.nm_dokter from catatan_resep_ranap c inner join dokter d on d.kd_dokter=c.kd_dokter where "
                                + "c.no_rawat='" + rs4.getString("no_rawat") + "' and c.tgl_perawatan='" + rs4.getString("tgl_perawatan") + "' "
                                + "order by c.noId desc limit 1");
                    }

                    tabMode4.addRow(new String[]{
                        x + ".",
                        rs4.getString("tglResep"),
                        rs4.getString("rgrawat"),
                        rs4.getString("nama_brng"),
                        rs4.getString("jlh"),
                        ceknmDokter,
                        rs4.getString("kode_brng"),
                        rs4.getString("tgl_perawatan"),
                        cekNipDokter
                    });
                    x++;
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
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilRekonIGD(String norw) {
        Valid.tabelKosong(tabMode2);
        try {
            ps2 = koneksi.prepareStatement("SELECT ro.*, p.no_rkm_medis, p.nm_pasien, date_format(ro.tgl_resep,'%d/%m/%Y') tglResep, pl.nm_poli, "
                    + "db.nama_brng, if(ro.cek_stop='ya',date_format(ro.tgl_stop,'%d/%m/%Y'),'-') tglStop, "
                    + "if(ro.tgl_stop='0000-00-00',date(now()),ro.tgl_stop) tglStopDefault FROM rekonsiliasi_obat_igd ro "
                    + "inner join reg_periksa rp on rp.no_rawat=ro.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "inner join poliklinik pl on pl.kd_poli=rp.kd_poli inner join databarang db on db.kode_brng=ro.kode_brng WHERE "
                    + "ro.no_rawat='" + norw + "' order by ro.waktu_simpan");
            try {
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    tabMode2.addRow(new String[]{
                        rs2.getString("no_rawat"),
                        rs2.getString("no_rkm_medis"),
                        rs2.getString("nm_pasien"),
                        "Tgl. Mulai : " + rs2.getString("tglResep") + " Tgl. Stop : " + rs2.getString("tglStop"),
                        rs2.getString("nm_poli"),
                        rs2.getString("nama_brng"),
                        rs2.getString("rute"),
                        rs2.getString("dosis"),
                        rs2.getString("dilanjutkan"),
                        rs2.getString("ket_dilanjutkan"),
                        rs2.getString("tgl_resep"),
                        rs2.getString("kode_brng"),
                        rs2.getString("waktu_simpan"),
                        rs2.getString("cek_stop"),
                        rs2.getString("tglStopDefault")
                    });
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
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilRekonRanap(String norw) {
        Valid.tabelKosong(tabMode5);
        try {
            ps5 = koneksi.prepareStatement("SELECT ro.*, p.no_rkm_medis, p.nm_pasien, date_format(ro.tgl_resep,'%d/%m/%Y') tglResep, "
                    + "db.nama_brng, pg.nama nmDokter, if(ro.cek_stop='ya',date_format(ro.tgl_stop,'%d/%m/%Y'),'-') tglStop, "
                    + "if(ro.tgl_stop='0000-00-00',date(now()),ro.tgl_stop) tglStopDefault FROM rekonsiliasi_obat_ranap ro "
                    + "INNER JOIN reg_periksa rp ON rp.no_rawat = ro.no_rawat INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                    + "INNER JOIN pegawai pg on pg.nik = ro.nip_dokter INNER JOIN databarang db ON db.kode_brng = ro.kode_brng WHERE "
                    + "ro.no_rawat='" + norw + "' order by ro.waktu_simpan");
            try {
                rs5 = ps5.executeQuery();
                while (rs5.next()) {
                    tabMode5.addRow(new String[]{
                        rs5.getString("no_rawat"),
                        rs5.getString("no_rkm_medis"),
                        rs5.getString("nm_pasien"),
                        "Tgl. Mulai : " + rs5.getString("tglResep") + " Tgl. Stop : " + rs5.getString("tglStop"),
                        rs5.getString("ruang_rawat"),
                        rs5.getString("nama_brng"),
                        rs5.getString("rute"),
                        rs5.getString("dosis"),
                        rs5.getString("aturan_pakai"),
                        rs5.getString("nmDokter"),
                        rs5.getString("tgl_resep"),
                        rs5.getString("kode_brng"),
                        rs5.getString("nip_dokter"),
                        rs5.getString("waktu_simpan"),
                        rs5.getString("cek_stop"),
                        rs5.getString("tglStopDefault")
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
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilRekonRiwayat(String norw) {
        Valid.tabelKosong(tabMode7);
        try {
            ps6 = koneksi.prepareStatement("SELECT ro.*, p.no_rkm_medis, p.nm_pasien FROM rekonsiliasi_obat_riwayat ro "
                    + "INNER JOIN reg_periksa rp ON rp.no_rawat = ro.no_rawat INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis WHERE "
                    + "ro.no_rawat='" + norw + "' order by ro.waktu_simpan");
            try {
                rs6 = ps6.executeQuery();
                while (rs6.next()) {
                    tabMode7.addRow(new String[]{
                        rs6.getString("no_rawat"),
                        rs6.getString("no_rkm_medis"),
                        rs6.getString("nm_pasien"),
                        rs6.getString("ruang_rawat"),
                        rs6.getString("nm_obat"),
                        rs6.getString("rute"),
                        rs6.getString("dosis"),
                        rs6.getString("aturan_pakai"),
                        rs6.getString("sumber_obat"),
                        rs6.getString("dilanjutkan"),
                        rs6.getString("waktu_simpan")
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
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilResepDokter(String norwt, String tglA, String tglB, String cari) {
        nmDokter = "";
        Valid.tabelKosong(tabMode3);
        Valid.tabelKosong(tabMode6);
        try {
            ps3 = koneksi.prepareStatement("SELECT *, date_format(tgl_pemberian, '%d/%m/%Y') tglPemberian, concat('Rute : ',cara_pemberian, ', Dosis : ',dosis) deskripsi, "
                    + "time(waktu_simpan) jam, status from pemberian_obat WHERE "
                    + "tgl_pemberian between ? and ? and no_rawat='" + norwt + "' and nm_unit like ? and nama_obat not in ('','-','.') or "
                    + "tgl_pemberian between ? and ? and no_rawat='" + norwt + "' and nama_obat like ? and nama_obat not in ('','-','.') or "
                    + "tgl_pemberian between ? and ? and no_rawat='" + norwt + "' and concat('Rute : ',cara_pemberian,', Dosis : ',dosis) like ? and nama_obat not in ('','-','.') "
                    + "order by tgl_pemberian, time(waktu_simpan)");
            try {
                ps3.setString(1, tglA);
                ps3.setString(2, tglB);
                ps3.setString(3, "%" + cari.trim() + "%");
                ps3.setString(4, tglA);
                ps3.setString(5, tglB);
                ps3.setString(6, "%" + cari.trim() + "%");
                ps3.setString(7, tglA);
                ps3.setString(8, tglB);
                ps3.setString(9, "%" + cari.trim() + "%");
                rs3 = ps3.executeQuery();
                while (rs3.next()) {
                    if (rs3.getString("status").equals("Ralan")) {
                        nmDokter = Sequel.cariIsi("select d.nm_dokter from catatan_resep c inner join dokter d on d.kd_dokter=c.kd_dokter where "
                                + "c.no_rawat='" + rs3.getString("no_rawat") + "' and c.tgl_perawatan='" + rs3.getString("tgl_pemberian") + "' "
                                + "order by c.noId desc limit 1");
                    } else {
                        nmDokter = Sequel.cariIsi("select d.nm_dokter from catatan_resep_ranap c inner join dokter d on d.kd_dokter=c.kd_dokter where "
                                + "c.no_rawat='" + rs3.getString("no_rawat") + "' and c.tgl_perawatan='" + rs3.getString("tgl_pemberian") + "' "
                                + "order by c.noId desc limit 1");
                    }

                    if (TabRekon.getSelectedIndex() == 1) {
                        tabMode3.addRow(new String[]{
                            rs3.getString("nm_unit"),
                            rs3.getString("tglPemberian"),
                            rs3.getString("jam"),
                            rs3.getString("nama_obat"),
                            rs3.getString("deskripsi"),
                            nmDokter
                        });
                    } else if (TabRekon.getSelectedIndex() == 2) {
                        tabMode6.addRow(new String[]{
                            rs3.getString("nm_unit"),
                            rs3.getString("tglPemberian"),
                            rs3.getString("jam"),
                            rs3.getString("nama_obat"),
                            rs3.getString("deskripsi"),
                            nmDokter
                        });
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
      
        if (TabRekon.getSelectedIndex() == 1) {
            LCount1.setText("" + tabMode3.getRowCount());
        } else if (TabRekon.getSelectedIndex() == 2) {
            LCount2.setText("" + tabMode6.getRowCount());
        }
    }
    
    private void getDataRiwObatIGD() {
        kdObat = "";
        if (tbRiwayatObatIgd.getSelectedRow() != -1) {
            Valid.SetTgl(TtglResep, tbRiwayatObatIgd.getValueAt(tbRiwayatObatIgd.getSelectedRow(), 7).toString());
            kdObat = tbRiwayatObatIgd.getValueAt(tbRiwayatObatIgd.getSelectedRow(), 6).toString();
            TnmObat.setText(tbRiwayatObatIgd.getValueAt(tbRiwayatObatIgd.getSelectedRow(), 3).toString());
        }
    }
    
    private void getDataRiwObatRanap() {
        kdObat = "";
        nipDokter = "";
        if (tbRiwayatObatRanap.getSelectedRow() != -1) {
            TrgRawat.setText(tbRiwayatObatRanap.getValueAt(tbRiwayatObatRanap.getSelectedRow(), 2).toString());
            Valid.SetTgl(TtglResep1, tbRiwayatObatRanap.getValueAt(tbRiwayatObatRanap.getSelectedRow(), 7).toString());
            kdObat = tbRiwayatObatRanap.getValueAt(tbRiwayatObatRanap.getSelectedRow(), 6).toString();
            TnmObat1.setText(tbRiwayatObatRanap.getValueAt(tbRiwayatObatRanap.getSelectedRow(), 3).toString());
            nipDokter = tbRiwayatObatRanap.getValueAt(tbRiwayatObatRanap.getSelectedRow(), 8).toString();
            TnmDokter.setText(tbRiwayatObatRanap.getValueAt(tbRiwayatObatRanap.getSelectedRow(), 5).toString());
        }
    }
    
    private void getDataRekonIGD() {
        kdObat = "";
        cekTglIGD = "";
        
        if (tbRekonIgd.getSelectedRow() != -1) {
            TNoRw2.setText(tbRekonIgd.getValueAt(tbRekonIgd.getSelectedRow(), 0).toString());
            TNoRM2.setText(tbRekonIgd.getValueAt(tbRekonIgd.getSelectedRow(), 1).toString());
            TPasien2.setText(tbRekonIgd.getValueAt(tbRekonIgd.getSelectedRow(), 2).toString());
            Valid.SetTgl(TtglResep, tbRekonIgd.getValueAt(tbRekonIgd.getSelectedRow(), 10).toString());
            kdObat = tbRekonIgd.getValueAt(tbRekonIgd.getSelectedRow(), 11).toString();
            TnmObat.setText(tbRekonIgd.getValueAt(tbRekonIgd.getSelectedRow(), 5).toString());
            Trute.setText(tbRekonIgd.getValueAt(tbRekonIgd.getSelectedRow(), 6).toString());
            Tdosis.setText(tbRekonIgd.getValueAt(tbRekonIgd.getSelectedRow(), 7).toString());
            cmbDilanjut.setSelectedItem(tbRekonIgd.getValueAt(tbRekonIgd.getSelectedRow(), 8).toString());
            Tdilanjutkan.setText(tbRekonIgd.getValueAt(tbRekonIgd.getSelectedRow(), 9).toString());
            cekTglIGD = tbRekonIgd.getValueAt(tbRekonIgd.getSelectedRow(), 13).toString();
            Valid.SetTgl(TtglStopIgd, tbRekonIgd.getValueAt(tbRekonIgd.getSelectedRow(), 14).toString());
            
            if (cmbDilanjut.getSelectedIndex() == 1) {
                Tdilanjutkan.setEnabled(true);
            } else {
                Tdilanjutkan.setEnabled(false);
            }
            
            if (cekTglIGD.equals("ya")) {
                chkTglIgd.setSelected(true);
                TtglStopIgd.setEnabled(true);
            } else if (cekTglIGD.equals("tidak")) {
                chkTglIgd.setSelected(false);
                TtglStopIgd.setEnabled(false);
            }
        }
    }
    
    private void getDataRekonRiwayat() {        
        if (tbRiwayat.getSelectedRow() != -1) {
            TNoRw4.setText(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 0).toString());
            TNoRM4.setText(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 1).toString());
            TPasien4.setText(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 2).toString());
            TnmObat2.setText(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 4).toString());
            Trute2.setText(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 5).toString());
            Tdosis2.setText(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 6).toString());
            Taturan1.setText(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 7).toString());
            Tsumber.setText(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 8).toString());
            cmbDilanjut1.setSelectedItem(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 9).toString());
        }
    }
    
    private void getDataRekonRanap() {
        kdObat = "";
        nipDokter = "";
        cekTglRanap = "";
        
        if (tbRekonRanap.getSelectedRow() != -1) {
            TNoRw3.setText(tbRekonRanap.getValueAt(tbRekonRanap.getSelectedRow(), 0).toString());
            TNoRM3.setText(tbRekonRanap.getValueAt(tbRekonRanap.getSelectedRow(), 1).toString());
            TPasien3.setText(tbRekonRanap.getValueAt(tbRekonRanap.getSelectedRow(), 2).toString());
            TrgRawat.setText(tbRekonRanap.getValueAt(tbRekonRanap.getSelectedRow(), 4).toString());
            Valid.SetTgl(TtglResep1, tbRekonRanap.getValueAt(tbRekonRanap.getSelectedRow(), 10).toString());
            kdObat = tbRekonRanap.getValueAt(tbRekonRanap.getSelectedRow(), 11).toString();
            TnmObat1.setText(tbRekonRanap.getValueAt(tbRekonRanap.getSelectedRow(), 5).toString());
            Trute1.setText(tbRekonRanap.getValueAt(tbRekonRanap.getSelectedRow(), 6).toString());
            Tdosis1.setText(tbRekonRanap.getValueAt(tbRekonRanap.getSelectedRow(), 7).toString());
            Taturan.setText(tbRekonRanap.getValueAt(tbRekonRanap.getSelectedRow(), 8).toString());
            nipDokter = tbRekonRanap.getValueAt(tbRekonRanap.getSelectedRow(), 12).toString();
            TnmDokter.setText(tbRekonRanap.getValueAt(tbRekonRanap.getSelectedRow(), 9).toString());
            cekTglRanap = tbRekonRanap.getValueAt(tbRekonRanap.getSelectedRow(), 14).toString();
            Valid.SetTgl(TtglStopRanap, tbRekonRanap.getValueAt(tbRekonRanap.getSelectedRow(), 15).toString());

            if (cekTglRanap.equals("ya")) {
                chkTglRanap.setSelected(true);
                TtglStopRanap.setEnabled(true);
            } else if (cekTglRanap.equals("tidak")) {
                chkTglRanap.setSelected(false);
                TtglStopRanap.setEnabled(false);
            }
        }
    }
    
    public void setData(String norwt, String rgrawat, String norm, String nmpasien) {
        TNoRw1.setText(norwt);
        TNoRw2.setText(norwt);
        TNoRw3.setText(norwt);
        TNoRw4.setText(norwt);
        TNoRM1.setText(norm);
        TNoRM2.setText(norm);
        TNoRM3.setText(norm);
        TNoRM4.setText(norm);
        TPasien1.setText(nmpasien);        
        TPasien2.setText(nmpasien);
        TPasien3.setText(nmpasien);
        TPasien4.setText(nmpasien);
        TrgRawat.setText(rgrawat);
        TrgRawat1.setText(rgrawat);
        kode = Sequel.cariIsi("SELECT DATE_FORMAT(NOW(6),'%Y%m%d%H%i%s%f')");
        
        tglreg = Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + norwt + "'");
        TtglMRS.setText(Valid.SetTglINDONESIA(tglreg));
        TtglMRS1.setText(Valid.SetTglINDONESIA(tglreg));
        TtglMRS2.setText(Valid.SetTglINDONESIA(tglreg));        
        
        Valid.SetTgl(DTPCari1, tglreg);
        DTPCari2.setDate(new Date());        
        Valid.SetTgl(DTPCari3, tglreg);
        DTPCari4.setDate(new Date());        
        Valid.SetTgl(DTPCari5, tglreg);
        DTPCari6.setDate(new Date());
        Valid.SetTgl(DTPCari7, tglreg);
        DTPCari8.setDate(new Date());
        TCari.setText(norwt);        
    }
    
    public void awalData() {
        tampilRekonRiwayat(TNoRw1.getText());
        BtnPrint.setEnabled(false);
    }
    
    private void emptTeksRiwayat() {
        TnmObat2.setText("");
        Trute2.setText("");
        Taturan1.setText("");
        Tdosis2.setText("");
        Tsumber.setText("");
        cmbDilanjut1.setSelectedIndex(0);
        TnmObat2.requestFocus();
    }
    
    private void tampilCetak(String wktSimpan) {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            StringBuilder htmlContent = new StringBuilder();
            try {
                ps = koneksi.prepareStatement("select ro.*, p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') as tgllahir, "
                        + "pg1.nama as nmPereview, pg2.nama as nmApoteker from rekonsiliasi_obat ro "
                        + "inner join reg_periksa rp on rp.no_rawat=ro.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                        + "inner join pegawai pg1 on pg1.nik=ro.nip_pereview inner join pegawai pg2 on pg2.nik=ro.nip_apoteker where ro.waktu_simpan=?");
                ps.setString(1, wktSimpan);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String alergi = "", catatanRiwayat = "";
                    if (rs.getString("riwayat_alergi").equals("Ya")) {
                        if (rs.getString("ket_riwayat_alergi").equals("")) {
                            alergi = rs.getString("riwayat_alergi") + " ..........";
                        } else {
                            alergi = rs.getString("riwayat_alergi") + ", " + rs.getString("ket_riwayat_alergi");
                        }
                    } else {
                        alergi = rs.getString("riwayat_alergi");
                    }
                    
                    String logoPath = new File("setting/logo1.jpg").toURI().toString();
                    
                    // DATA RIWAYAT PENGGUNAAN OBAT
                    StringBuilder dataRiwayat = new StringBuilder();
                    int jumlahRiwayat = 0;
                    // catatan berasal dari MASTER rekonsiliasi_obat
                    if (rs.getString("catatan_obat_riwayat") != null) {
                        catatanRiwayat = rs.getString("catatan_obat_riwayat").replace("\n", "<br>");
                    }

                    try {
                        // HITUNG JUMLAH DETAIL UNTUK ROWSPAN CATATAN
                        ps7 = koneksi.prepareStatement("select count(*) as jumlah from rekonsiliasi_obat_riwayat where kd_data=?");
                        ps7.setString(1, rs.getString("kd_data"));
                        rs7 = ps7.executeQuery();

                        if (rs7.next()) {
                            jumlahRiwayat = rs7.getInt("jumlah");
                        }

                        if (rs7 != null) {
                            rs7.close();
                        }

                        if (ps7 != null) {
                            ps7.close();
                        }

                        // AMBIL DATA DETAIL RIWAYAT OBAT
                        ps8 = koneksi.prepareStatement("select ror.nm_obat, ror.rute, ror.dosis, ror.aturan_pakai, ror.sumber_obat, "
                                + "ror.dilanjutkan, ror.waktu_simpan from rekonsiliasi_obat_riwayat ror where ror.kd_data=? order by ror.waktu_simpan");
                        ps8.setString(1, rs.getString("kd_data"));
                        rs8 = ps8.executeQuery();

                        int urutRiwayat = 0;
                        while (rs8.next()) {
                            urutRiwayat++;
                            String ya = "", tidak = "";

                            if ("Ya".equalsIgnoreCase(rs8.getString("dilanjutkan"))) {
                                ya = "V";
                            } else if ("Tidak".equalsIgnoreCase(rs8.getString("dilanjutkan"))) {
                                tidak = "V";
                            }

                            dataRiwayat.append(
                                    "<tr>"
                                    // NAMA OBAT
                                    + "<td valign='top' "
                                    + "style='border-right:1px solid #000000;"
                                    + "border-bottom:1px solid #000000;'>"
                                    + rs8.getString("nm_obat")
                                    + "</td>"
                                    // RUTE
                                    + "<td align='center' valign='top' "
                                    + "style='border-right:1px solid #000000;"
                                    + "border-bottom:1px solid #000000;'>"
                                    + rs8.getString("rute")
                                    + "</td>"
                                    // DOSIS
                                    + "<td align='center' valign='top' "
                                    + "style='border-right:1px solid #000000;"
                                    + "border-bottom:1px solid #000000;'>"
                                    + rs8.getString("dosis")
                                    + "</td>"
                                    // ATURAN PAKAI
                                    + "<td align='center' valign='top' "
                                    + "style='border-right:1px solid #000000;"
                                    + "border-bottom:1px solid #000000;'>"
                                    + rs8.getString("aturan_pakai")
                                    + "</td>"
                                    // SUMBER OBAT
                                    + "<td align='center' valign='top' "
                                    + "style='border-right:1px solid #000000;"
                                    + "border-bottom:1px solid #000000;'>"
                                    + rs8.getString("sumber_obat")
                                    + "</td>"
                                    // DILANJUTKAN YA
                                    + "<td align='center' valign='top' "
                                    + "style='border-right:1px solid #000000;"
                                    + "border-bottom:1px solid #000000;'>"
                                    + ya
                                    + "</td>"
                                    // DILANJUTKAN TIDAK
                                    + "<td align='center' valign='top' "
                                    + "style='border-right:1px solid #000000;"
                                    + "border-bottom:1px solid #000000;'>"
                                    + tidak
                                    + "</td>"
                            );

                            // CATATAN HANYA DIBUAT PADA BARIS PERTAMA
                            // ROWSPAN = JUMLAH DATA RIWAYAT
                            if (urutRiwayat == 1) {
                                dataRiwayat.append("<td rowspan='" + jumlahRiwayat + "' align='left' valign='top' style='border-bottom:1px solid #000000;'>" + catatanRiwayat + "</td>");
                            }

                            dataRiwayat.append("</tr>");
                        }

                        // JIKA DETAIL RIWAYAT KOSONG TETAP TAMPILKAN CATATAN DARI MASTER
                        if (jumlahRiwayat == 0) {
                            dataRiwayat.append(
                                    "<tr>"
                                    // Nama Obat
                                    + "<td valign='top' "
                                    + "style='border-right:1px solid #000000;"
                                    + "border-bottom:1px solid #000000;'>&nbsp;</td>"
                                    // Rute
                                    + "<td align='center' valign='top' "
                                    + "style='border-right:1px solid #000000;"
                                    + "border-bottom:1px solid #000000;'>&nbsp;</td>"
                                    // Dosis
                                    + "<td align='center' valign='top' "
                                    + "style='border-right:1px solid #000000;"
                                    + "border-bottom:1px solid #000000;'>&nbsp;</td>"
                                    // Aturan Pakai
                                    + "<td align='center' valign='top' "
                                    + "style='border-right:1px solid #000000;"
                                    + "border-bottom:1px solid #000000;'>&nbsp;</td>"
                                    // Sumber Obat
                                    + "<td align='center' valign='top' "
                                    + "style='border-right:1px solid #000000;"
                                    + "border-bottom:1px solid #000000;'>&nbsp;</td>"
                                    // Ya
                                    + "<td align='center' valign='top' "
                                    + "style='border-right:1px solid #000000;"
                                    + "border-bottom:1px solid #000000;'>&nbsp;</td>"
                                    // Tidak
                                    + "<td align='center' valign='top' "
                                    + "style='border-right:1px solid #000000;"
                                    + "border-bottom:1px solid #000000;'>&nbsp;</td>"
                                    // CATATAN MASTER
                                    + "<td align='left' valign='top' "
                                    + "style='border-bottom:1px solid #000000;'>"
                                    + catatanRiwayat
                                    + "</td>"
                                    + "</tr>"
                            );
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi tampil riwayat obat : " + e);
                    } finally {
                        if (rs8 != null) {
                            rs8.close();
                        }

                        if (ps8 != null) {
                            ps8.close();
                        }

                        if (rs7 != null) {
                            rs7.close();
                        }

                        if (ps7 != null) {
                            ps7.close();
                        }
                    }                    
                    
                    // DATA OBAT YANG DIRESEPKAN SAAT INI
                    StringBuilder dataObatSaatIni = new StringBuilder();

                    int jumlahObatSaatIni = 0;
                    String catatanObatSaatIni = "";

                    String catatanIgd = "";
                    String catatanRanap = "";

                    if (rs.getString("catatan_obat_igd") != null) {
                        catatanIgd = rs.getString("catatan_obat_igd").trim();
                    }

                    if (rs.getString("catatan_obat_ranap") != null) {
                        catatanRanap = rs.getString("catatan_obat_ranap").trim();
                    }

                    // Gabungkan catatan IGD dan Ranap
                    if (!catatanIgd.equals("") && !catatanRanap.equals("")) {
                        catatanObatSaatIni = catatanIgd.replace("\n", "<br>") + "<br><br>" + catatanRanap.replace("\n", "<br>");
                    } else if (!catatanIgd.equals("")) {
                        catatanObatSaatIni = catatanIgd.replace("\n", "<br>");
                    } else if (!catatanRanap.equals("")) {
                        catatanObatSaatIni = catatanRanap.replace("\n", "<br>");
                    }

                    try {
                        // 1. HITUNG JUMLAH OBAT UNTUK ROWSPAN KOLOM CATATAN
                        ps10 = koneksi.prepareStatement(
                                "SELECT COUNT(*) AS jumlah "
                                + "FROM ( "
                                + "   SELECT roi.kd_data "
                                + "   FROM rekonsiliasi_obat_igd roi "
                                + "   WHERE roi.kd_data=? "
                                + "   UNION ALL "
                                + "   SELECT ror.kd_data "
                                + "   FROM rekonsiliasi_obat_ranap ror "
                                + "   WHERE ror.kd_data=? "
                                + ") x"
                        );

                        ps10.setString(1, rs.getString("kd_data"));
                        ps10.setString(2, rs.getString("kd_data"));
                        rs10 = ps10.executeQuery();

                        if (rs10.next()) {
                            jumlahObatSaatIni = rs10.getInt("jumlah");
                        }

                        rs10.close();
                        ps10.close();

                        // 2. SETELAH JUMLAH DIKETAHUI BARU AMBIL DETAIL OBAT DENGAN ps9
                        ps9 = koneksi.prepareStatement("SELECT "
                                + "COALESCE(CASE WHEN x.jenis='IGD' "
                                + "THEN pl.nm_poli ELSE x.ruang_rawat END,'') AS ruang_rawat, "
                                + "COALESCE(d.nama_brng,'') AS nmObat, "
                                + "COALESCE(x.rute,'') AS rute, "
                                + "COALESCE(x.dosis,'') AS dosis, "
                                + "COALESCE(x.aturan_pakai,'') AS aturanPakai, "
                                + "COALESCE(pg.nama,'') AS nmDokter, "
                                + "COALESCE(DATE_FORMAT(x.tgl_resep,'%d/%m/%Y'),'') AS tglMulai, "
                                + "COALESCE(x.tglStop,'-') AS tglStop "
                                + "FROM ( "
                                // IGD
                                + "SELECT roi.no_rawat, roi.kode_brng, "
                                + "roi.rute, roi.dosis, "
                                + "'' AS aturan_pakai, "
                                + "roi.tgl_resep, "
                                + "NULL AS ruang_rawat, "
                                + "rp.kd_dokter AS nip_dokter, "
                                + "'IGD' AS jenis, "
                                + "IF(roi.cek_stop='ya',"
                                + "DATE_FORMAT(roi.tgl_stop,'%d/%m/%Y'),'-') AS tglStop "
                                + "FROM rekonsiliasi_obat_igd roi "
                                + "INNER JOIN reg_periksa rp "
                                + "ON rp.no_rawat=roi.no_rawat "
                                + "WHERE roi.kd_data=? "
                                + "UNION ALL "
                                // RANAP
                                + "SELECT ror.no_rawat, ror.kode_brng, "
                                + "ror.rute, ror.dosis, "
                                + "ror.aturan_pakai, "
                                + "ror.tgl_resep, "
                                + "ror.ruang_rawat, "
                                + "ror.nip_dokter, "
                                + "'RANAP' AS jenis, "
                                + "IF(ror.cek_stop='ya',"
                                + "DATE_FORMAT(ror.tgl_stop,'%d/%m/%Y'),'-') AS tglStop "
                                + "FROM rekonsiliasi_obat_ranap ror "
                                + "WHERE ror.kd_data=? "
                                + ") x "
                                + "INNER JOIN reg_periksa rp "
                                + "ON rp.no_rawat=x.no_rawat "
                                + "INNER JOIN pegawai pg "
                                + "ON pg.nik=x.nip_dokter "
                                + "INNER JOIN databarang d "
                                + "ON d.kode_brng=x.kode_brng "
                                + "INNER JOIN poliklinik pl "
                                + "ON pl.kd_poli=rp.kd_poli "
                                + "ORDER BY x.tgl_resep"
                        );

                        ps9.setString(1, rs.getString("kd_data"));
                        ps9.setString(2, rs.getString("kd_data"));

                        rs9 = ps9.executeQuery();
                        // 3. SUSUN DATA HTML
                        int urutObatSaatIni = 0;
                        while (rs9.next()) {
                            urutObatSaatIni++;
                            dataObatSaatIni.append(
                                    "<tr>"
                                    + "<td valign='top' "
                                    + "style='border-right:1px solid #000000;"
                                    + "border-bottom:1px solid #000000;'>"
                                    + rs9.getString("nmObat")
                                    + "</td>"
                                    + "<td align='center' valign='top' "
                                    + "style='border-right:1px solid #000000;"
                                    + "border-bottom:1px solid #000000;'>"
                                    + rs9.getString("rute")
                                    + "</td>"
                                    + "<td align='center' valign='top' "
                                    + "style='border-right:1px solid #000000;"
                                    + "border-bottom:1px solid #000000;'>"
                                    + rs9.getString("dosis")
                                    + "</td>"
                                    + "<td align='center' valign='top' "
                                    + "style='border-right:1px solid #000000;"
                                    + "border-bottom:1px solid #000000;'>"
                                    + rs9.getString("aturanPakai")
                                    + "</td>"
                                    + "<td align='left' valign='top' "
                                    + "style='border-right:1px solid #000000;"
                                    + "border-bottom:1px solid #000000;'>"
                                    + rs9.getString("nmDokter")
                                    + "</td>"
                                    + "<td align='center' valign='top' "
                                    + "style='border-right:1px solid #000000;"
                                    + "border-bottom:1px solid #000000;'>"
                                    + rs9.getString("tglMulai")
                                    + "</td>"
                                    + "<td align='center' valign='top' "
                                    + "style='border-right:1px solid #000000;"
                                    + "border-bottom:1px solid #000000;'>"
                                    + rs9.getString("tglStop")
                                    + "</td>"
                            );

                            // CATATAN HANYA PADA BARIS PERTAMA
                            if (urutObatSaatIni == 1) {

                                dataObatSaatIni.append(
                                        "<td rowspan='" + jumlahObatSaatIni + "' "
                                        + "align='left' valign='top' "
                                        + "style='border-bottom:1px solid #000000;'>"
                                        + catatanObatSaatIni
                                        + "</td>"
                                );
                            }

                            dataObatSaatIni.append("</tr>");
                        }
                        
                        // JIKA TIDAK ADA OBAT IGD / RANAP TETAP TAMPILKAN CATATAN MASTER
                        if (jumlahObatSaatIni == 0) {
                            dataObatSaatIni.append(
                                    "<tr>"
                                    // Nama Obat
                                    + "<td valign='top' "
                                    + "style='border-right:1px solid #000000;"
                                    + "border-bottom:1px solid #000000;'>&nbsp;</td>"
                                    // Rute
                                    + "<td align='center' valign='top' "
                                    + "style='border-right:1px solid #000000;"
                                    + "border-bottom:1px solid #000000;'>&nbsp;</td>"
                                    // Dosis
                                    + "<td align='center' valign='top' "
                                    + "style='border-right:1px solid #000000;"
                                    + "border-bottom:1px solid #000000;'>&nbsp;</td>"
                                    // Aturan Pakai
                                    + "<td align='center' valign='top' "
                                    + "style='border-right:1px solid #000000;"
                                    + "border-bottom:1px solid #000000;'>&nbsp;</td>"
                                    // Dokter
                                    + "<td align='left' valign='top' "
                                    + "style='border-right:1px solid #000000;"
                                    + "border-bottom:1px solid #000000;'>&nbsp;</td>"
                                    // Tgl Mulai
                                    + "<td align='center' valign='top' "
                                    + "style='border-right:1px solid #000000;"
                                    + "border-bottom:1px solid #000000;'>&nbsp;</td>"
                                    // Tgl Stop
                                    + "<td align='center' valign='top' "
                                    + "style='border-right:1px solid #000000;"
                                    + "border-bottom:1px solid #000000;'>&nbsp;</td>"
                                    // CATATAN
                                    + "<td align='left' valign='top' "
                                    + "style='border-bottom:1px solid #000000;'>"
                                    + catatanObatSaatIni
                                    + "</td>"
                                    + "</tr>"
                            );
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi tampil obat saat ini : " + e);
                    } finally {
                        if (rs9 != null) {
                            rs9.close();
                        }

                        if (ps9 != null) {
                            ps9.close();
                        }

                        if (rs10 != null) {
                            rs10.close();
                        }

                        if (ps10 != null) {
                            ps10.close();
                        }
                    }

                    String ketidaksesuaian = "", saran = "", keputusan = "";

                    if (rs.getString("ketidaksesuaian") != null) {
                        ketidaksesuaian = rs.getString("ketidaksesuaian").replace("\n", "<br>");
                    }

                    if (rs.getString("saran") != null) {
                        saran = rs.getString("saran").replace("\n", "<br>");
                    }

                    if (rs.getString("keputusan") != null) {
                        keputusan = rs.getString("keputusan").replace("\n", "<br>");
                    }
                    
                    htmlContent.append(
                            "<html>"
                            + "<head>"
                            + "<meta charset='UTF-8'>"
                            + "<style>"
                            + "@page {"
                            + "   size: A4 portrait;"
                            + "   margin: 10mm;"
                            + "}"
                            + "html, body {"
                            + "   margin: 0;"
                            + "   padding: 0;"
                            + "   width: 100%;"
                            + "   font-family: Tahoma, Arial, sans-serif;"
                            + "   color: #000000;"
                            + "}"
                            + "table {"
                            + "   border-collapse: collapse;"
                            + "}"
                            + ".print-area {"
                            + "   width: 190mm;"
                            + "   margin: 0 auto;"
                            + "}"
                            + ".no-print {"
                            + "   margin-bottom: 10px;"
                            + "}"
                            + "@media print {"
                            + "   .no-print {"
                            + "       display: none;"
                            + "   }"
                            + "   body {"
                            + "       margin: 0;"
                            + "   }"
                            + "   .print-area {"
                            + "       width: 100%;"
                            + "       margin: 0;"
                            + "   }"
                            + "}"
                            + "</style>"
                            + "</head>"
                            + "<body>"
                            + "<div class='no-print'>"
                            + "<button onclick='window.print()'>Cetak A4</button>"
                            + "</div>"
                            + "<div class='print-area'>"
                                    
                            + "<table width='100%' border='0' cellpadding='0' cellspacing='0'>"
                            + "<tr>"
                            + "<td align='right' style='font-size:12px;font-weight:bold;'>RM 14 REV.2</td>"
                            + "</tr>"
                            + "</table>"
                                    
                            + "<table width='100%' border='0' cellpadding='3' cellspacing='0' style='border:1px solid #000000;border-collapse:collapse;font-family:Tahoma;font-size:10px;'>"
                            // =========================
                            // BARIS 1
                            // =========================
                            + "<tr>"
                            // LOGO
                            + "<td width='12%' rowspan='3' align='center' valign='middle'><img src='" + logoPath + "' width='60' height='60'></td>"
                            // NAMA RUMAH SAKIT
                            + "<td width='48%' rowspan='3' align='left' valign='middle'><span style='font-size:14px;font-weight:bold;'>RUMAH SAKIT UMUM DAERAH</span>"
                            + "<br>"
                            + "<span style='font-size:14px;font-weight:bold;'>RATU ZALECHA MARTAPURA</span></td>"
                            // NO RM
                            + "<td width='13%' valign='middle' style='border-left:1px solid #000000;'>&nbsp;&nbsp;&nbsp;No. RM</td>"
                            + "<td width='3%' align='right' valign='middle'>:</td>"
                            + "<td width='24%' valign='middle'>" + rs.getString("no_rkm_medis") + "</td>"
                            + "</tr>"
                            // =========================
                            // BARIS 2 - NAMA PASIEN
                            // =========================
                            + "<tr>"
                            + "<td valign='middle' style='border-left:1px solid #000000;'>&nbsp;&nbsp;&nbsp;Nama Pasien</td>"
                            + "<td align='right' valign='middle'>:</td>"
                            + "<td valign='middle'>" + rs.getString("nm_pasien") + "</td>"
                            + "</tr>"
                            // =========================
                            // BARIS 3 - TANGGAL LAHIR
                            // =========================
                            + "<tr>"
                            + "<td valign='middle' style='border-left:1px solid #000000;'>&nbsp;&nbsp;&nbsp;Tanggal Lahir</td>"
                            + "<td align='right' valign='middle'>:</td>"
                            + "<td valign='middle'>" + rs.getString("tgllahir") + "</td>"
                            + "</tr>"
                                    
                            + "</table>"                                
                            + "<table width='100%' border='0' cellpadding='3' cellspacing='0' "
                            + "style='border-left:1px solid #000000;"
                            + "border-right:1px solid #000000;"
                            + "border-bottom:1px solid #000000;"
                            + "border-top:0;"
                            + "border-collapse:collapse;"
                            + "font-family:Tahoma;"
                            + "font-size:11px;'>"
                            + "<tr>"
                            + "<td align='center' style='font-size:11px;font-weight:bold;'>LEMBAR REKONSILIASI OBAT</td>"
                            + "</tr>"
                            + "</table>"
                                    
                            + "<table width='100%' border='0' cellpadding='3' cellspacing='0' "
                            + "style='border-left:1px solid #000000;"
                            + "border-right:1px solid #000000;"
                            + "border-bottom:1px solid #000000;"
                            + "border-top:0;"
                            + "border-collapse:collapse;"
                            + "font-family:Tahoma;"
                            + "font-size:10px;'>"
                            + "<tr>"
                            + "<td width='32%' align='left' valign='top' style='border-right:1px solid #000000;'>" + rs.getString("jns_rekon") + "</td>"
                            + "<td width='34%' align='left' valign='top' style='border-right:1px solid #000000;'>" + "<span style='font-size:10px;font-weight:bold;'>Riwayat Alergi / Intoleransi Obat :</span><br>" + alergi + "</td>"
                            + "<td width='34%' align='left' valign='top'><span style='font-size:10px;font-weight:bold;'>Pasien Membawa Obat Dari Luar :</span><br>" + rs.getString("membawa_obat_dari_luar") + "</td>"
                            + "</tr>"
                            + "</table>"
                                    
                            + "<table width='100%' border='0' cellpadding='3' cellspacing='0' "
                            + "style='border-left:1px solid #000000;"
                            + "border-right:1px solid #000000;"
                            + "border-bottom:1px solid #000000;"
                            + "border-top:0;"
                            + "border-collapse:collapse;"
                            + "font-family:Tahoma;"
                            + "font-size:11px;'>"
                            + "<tr>"
                            + "<td align='left' style='font-size:11px;font-weight:bold;'>RIWAYAT PENGGUNAAN OBAT HINGGA SAAT INI :</td>"
                            + "</tr>"
                            + "</table>"
                                    
                            + "<table width='100%' border='0' cellpadding='3' cellspacing='0' "
                            + "style='border-left:1px solid #000000;"
                            + "border-right:1px solid #000000;"
                            + "border-bottom:1px solid #000000;"
                            + "border-top:0;"
                            + "border-collapse:collapse;"
                            + "font-family:Tahoma;"
                            + "font-size:9px;'>"
                            // HEADER BARIS PERTAMA
                            + "<tr>"
                            + "<td width='18%' rowspan='2' align='center' valign='middle' "
                            + "style='border-right:1px solid #000000;"
                            + "border-bottom:1px solid #000000;"
                            + "font-weight:bold;'>"
                            + "Nama Obat<br>(Paten/Generik)"
                            + "</td>"
                            + "<td width='8%' rowspan='2' align='center' valign='middle' "
                            + "style='border-right:1px solid #000000;"
                            + "border-bottom:1px solid #000000;"
                            + "font-weight:bold;'>"
                            + "Rute"
                            + "</td>"
                            + "<td width='8%' rowspan='2' align='center' valign='middle' "
                            + "style='border-right:1px solid #000000;"
                            + "border-bottom:1px solid #000000;"
                            + "font-weight:bold;'>"
                            + "Dosis"
                            + "</td>"
                            + "<td width='12%' rowspan='2' align='center' valign='middle' "
                            + "style='border-right:1px solid #000000;"
                            + "border-bottom:1px solid #000000;"
                            + "font-weight:bold;'>"
                            + "Aturan<br>Pakai"
                            + "</td>"
                            + "<td width='10%' rowspan='2' align='center' valign='middle' "
                            + "style='border-right:1px solid #000000;"
                            + "border-bottom:1px solid #000000;"
                            + "font-weight:bold;'>"
                            + "Sumber<br>Obat"
                            + "</td>"
                            + "<td width='14%' colspan='2' align='center' valign='middle' "
                            + "style='border-right:1px solid #000000;"
                            + "border-bottom:1px solid #000000;"
                            + "font-weight:bold;'>"
                            + "Dilanjutkan ?"
                            + "</td>"
                            + "<td width='30%' rowspan='2' align='center' valign='middle' "
                            + "style='border-bottom:1px solid #000000;"
                            + "font-weight:bold;'>"
                            + "Catatan"
                            + "</td>"
                            + "</tr>"
                            // HEADER BARIS KEDUA
                            + "<tr>"
                            + "<td width='7%' align='center' "
                            + "style='border-right:1px solid #000000;"
                            + "border-bottom:1px solid #000000;"
                            + "font-weight:bold;'>"
                            + "Ya"
                            + "</td>"
                            + "<td width='7%' align='center' "
                            + "style='border-right:1px solid #000000;"
                            + "border-bottom:1px solid #000000;"
                            + "font-weight:bold;'>"
                            + "Tidak"
                            + "</td>"
                            + "</tr>"                            
                            // DATA DARI DATABASE
                            + dataRiwayat.toString()
                            + "</table>"

                            + "<table width='100%' border='0' cellpadding='3' cellspacing='0' "
                            + "style='border-left:1px solid #000000;"
                            + "border-right:1px solid #000000;"
                            + "border-bottom:1px solid #000000;"
                            + "border-top:0;"
                            + "border-collapse:collapse;"
                            + "font-family:Tahoma;"
                            + "font-size:11px;'>"
                            + "<tr>"
                            + "<td align='left' style='font-size:11px;font-weight:bold;'>OBAT YANG DIRESEPKAN SAAT INI :</td>"
                            + "</tr>"
                            + "</table>"
                                    
                            + "<table width='100%' border='0' cellpadding='3' cellspacing='0' "
                            + "style='border-left:1px solid #000000;"
                            + "border-right:1px solid #000000;"
                            + "border-bottom:1px solid #000000;"
                            + "border-top:0;"
                            + "border-collapse:collapse;"
                            + "font-family:Tahoma;"
                            + "font-size:9px;'>"
                            + "<tr>"
                            // Nama obat
                            + "<td width='18%' rowspan='2' align='center' valign='middle' "
                            + "style='border-right:1px solid #000000;"
                            + "border-bottom:1px solid #000000;"
                            + "font-weight:bold;'>"
                            + "Nama Obat<br>(Paten/Generik)"
                            + "</td>"
                            // Rute
                            + "<td width='8%' rowspan='2' align='center' valign='middle' "
                            + "style='border-right:1px solid #000000;"
                            + "border-bottom:1px solid #000000;"
                            + "font-weight:bold;'>"
                            + "Rute"
                            + "</td>"
                            // Dosis
                            + "<td width='8%' rowspan='2' align='center' valign='middle' "
                            + "style='border-right:1px solid #000000;"
                            + "border-bottom:1px solid #000000;"
                            + "font-weight:bold;'>"
                            + "Dosis"
                            + "</td>"
                            // Aturan pakai
                            + "<td width='12%' rowspan='2' align='center' valign='middle' "
                            + "style='border-right:1px solid #000000;"
                            + "border-bottom:1px solid #000000;"
                            + "font-weight:bold;'>"
                            + "Aturan<br>Pakai"
                            + "</td>"
                            // Dokter
                            + "<td width='14%' rowspan='2' align='center' valign='middle' "
                            + "style='border-right:1px solid #000000;"
                            + "border-bottom:1px solid #000000;"
                            + "font-weight:bold;'>"
                            + "R/<br>Dokter"
                            + "</td>"
                            // Tanggal
                            + "<td width='16%' colspan='2' align='center' valign='middle' "
                            + "style='border-right:1px solid #000000;"
                            + "border-bottom:1px solid #000000;"
                            + "font-weight:bold;'>"
                            + "Tanggal"
                            + "</td>"
                            // Catatan
                            + "<td width='24%' rowspan='2' align='center' valign='middle' "
                            + "style='border-bottom:1px solid #000000;"
                            + "font-weight:bold;'>"
                            + "Catatan"
                            + "</td>"
                            + "</tr>"
                            // HEADER TANGGAL
                            + "<tr>"
                            + "<td width='8%' align='center' "
                            + "style='border-right:1px solid #000000;"
                            + "border-bottom:1px solid #000000;"
                            + "font-weight:bold;'>"
                            + "Mulai"
                            + "</td>"
                            + "<td width='8%' align='center' "
                            + "style='border-right:1px solid #000000;"
                            + "border-bottom:1px solid #000000;"
                            + "font-weight:bold;'>"
                            + "Stop"
                            + "</td>"
                            + "</tr>"
                            + dataObatSaatIni.toString()
                            + "</table>"
                                    
                            + "<table width='100%' border='0' cellpadding='3' cellspacing='0' "
                            + "style='border-left:1px solid #000000;"
                            + "border-right:1px solid #000000;"
                            + "border-bottom:1px solid #000000;"
                            + "border-top:0;"
                            + "border-collapse:collapse;"
                            + "font-family:Tahoma;"
                            + "font-size:11px;'>"
                            + "<tr>"
                            + "<td align='left' style='font-size:11px;font-weight:bold;'>RENCANA PENYESUAIAN PENGOBATAN :</td>"
                            + "</tr>"
                            + "</table>"

                            // RENCANA PENYESUAIAN PENGOBATAN
                            + "<table width='100%' border='0' cellpadding='4' cellspacing='0' "
                            + "style='border-left:1px solid #000000;"
                            + "border-right:1px solid #000000;"
                            + "border-bottom:1px solid #000000;"
                            + "border-top:0;"
                            + "border-collapse:collapse;"
                            + "font-family:Tahoma;"
                            + "font-size:10px;'>"
                            + "<tr>"                            
                            // KETIDAKSESUAIAN
                            + "<td width='33%' height='100' "
                            + "align='left' valign='top' "
                            + "style='border-right:1px solid #000000;'>"
                            + "<span style='font-size:10px;font-weight:bold;'>Ketidaksesuaian :" + "</span>" + "<br>" + ketidaksesuaian + "</td>"
                            // SARAN
                            + "<td width='34%' height='100' "
                            + "align='left' valign='top' "
                            + "style='border-right:1px solid #000000;'>"
                            + "<span style='font-size:10px;font-weight:bold;'>Saran :</span><br>" + saran + "</td>"
                            // KEPUTUSAN
                            + "<td width='33%' height='100' "
                            + "align='left' valign='top'>"
                            + "<span style='font-size:10px;font-weight:bold;'>Keputusan :</span><br>" + keputusan + "</td>" + "</tr>"
                            + "</table>"
                                    
                            + "<table width='100%' border='0' cellpadding='3' cellspacing='0' "
                            + "style='border-left:0px solid #000000;"
                            + "border-right:0px solid #000000;"
                            + "border-bottom:0px solid #000000;"
                            + "border-top:0;"
                            + "border-collapse:collapse;"
                            + "font-family:Tahoma;"
                            + "font-size:10px;'>"
                            + "<tr>"
                            + "<td align='left' style='font-size:10px;'><br><b>Direview Oleh :</b> " + rs.getString("nmPereview") + ""
                            + "<br><br><b>Apoteker :</b> " + rs.getString("nmApoteker")
                            + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Paraf : </b>............."
                            + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Tanggal : </b>" + Valid.SetTglINDONESIA(rs.getString("tanggal")) + "</td>"
                            + "</tr>"
                            + "</table>"
                                    
                            + "</div>"
                            + "</body>"
                            + "</html>"
                    );

                    
                    htmlCetakRekonsiliasi = htmlContent.toString();                    
                    LoadHTML1.setText(htmlContent.toString());
                    LoadHTML1.setCaretPosition(0);
                }

            } catch (Exception e) {
                System.out.println("Notifikasi tampilCetak : " + e);
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
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    private void bukaCetakBrowser(String html) {
        try {
            Path fileHtml = Files.createTempFile("rekonsiliasi_obat_", ".html");
            Files.write(fileHtml, html.getBytes(StandardCharsets.UTF_8));
            File file = fileHtml.toFile();
            file.deleteOnExit();

            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(file.toURI());
            } else {
                JOptionPane.showMessageDialog(null, "Browser tidak dapat dibuka otomatis.\n" + "File cetak tersimpan di :\n" + file.getAbsolutePath());
            }
        } catch (Exception e) {
            System.out.println("Notifikasi buka cetak browser : " + e);
            JOptionPane.showMessageDialog(null, "Gagal membuka halaman cetak :\n" + e.getMessage());
        }
    }
    
    private void previewData() {
        if (!htmlCetakRekonsiliasi.equals("")) {
            bukaCetakBrowser(htmlCetakRekonsiliasi);
        } else {
            JOptionPane.showMessageDialog(null, "Data cetak belum tersedia.");
        }

        emptTeksRiwayat();
        tampilRekonRiwayat(TNoRw1.getText());

        emptTeksRiwayatIGD();
        tampilRekonIGD(TNoRw1.getText());

        tampilRiwObatIGD();
        emptTeksRiwayatRanap();

        tampilRekonRanap(TNoRw1.getText());
        tampilRiwObatRanap();

        emptTeks();
        tampil();
        return;
    }
}
