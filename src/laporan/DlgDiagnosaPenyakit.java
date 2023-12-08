package laporan;

import bridging.INACBGDaftarKlaim;
import laporan.DlgPenyakit;
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
import java.sql.Connection;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import javax.swing.event.DocumentEvent;
import javax.swing.event.HyperlinkEvent;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import laporan.DlgICD9;
import simrskhanza.DlgPasien;

/**
 *
 * @author perpustakaan
 */
public class DlgDiagnosaPenyakit extends javax.swing.JDialog {
    private final DefaultTableModel TabModeDiagnosaPasien, tabModeDiagnosa, tabModeProsedur,
            TabModeTindakanPasien, tabModeDiagnosaSekunder, tabMode1, tabMode2;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private DlgPasien pasien = new DlgPasien(null, false);
    private PreparedStatement pspenyakit, psdiagnosapasien, psprosedur, pstindakanpasien, psralan,
            pspenyakitsekunder, pspasien, psdiagnosa, pstindakan, psdokter, psinadrg, psTINinadrg;
    private ResultSet rs, rs1, rspasien, rsdiagnosa, rstindakan, rsdokter, rsralan, rsrad, rshslRad, rsLISMaster, rsLIS1, rsLIS2, rsLIS3;
    private int jml = 0, i = 0, index = 0, jml1 = 0, s = 0, index1 = 0, cek = 0, cekINADRG = 0,
            cekPremier = 0, cekPremierINADRG = 0, lis1 = 0, lis2 = 0, lisM = 0;
    private String[] kode, nama, ciripny, keterangan, kategori, cirium, kode2, panjang, pendek,
            kode1, nama1, ciripny1, keterangan1, kategori1, cirium1;
    private boolean[] pilih, pilih2, pilih3;    
    private String tglklaim = "", drdpjp = "", poli = "", crBayar = "", cekKlaim = "", jlhTindakan = "";

    /**
     * Creates new form DlgPemberianObat
     *
     * @param parent
     * @param modal
     */
    public DlgDiagnosaPenyakit(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        TabModeDiagnosaPasien = new DefaultTableModel(null, new Object[]{
            "P", "Tgl.Rawat", "No.Rawat", "No.R.M.", "Nama Pasien", "Kode", "Nama Penyakit", "Status", "Jenis Diagnosa", "Petugas Coding RM"}) {
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
        tbDiagnosaPasien.setModel(TabModeDiagnosaPasien);
        tbDiagnosaPasien.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbDiagnosaPasien.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 10; i++) {
            TableColumn column = tbDiagnosaPasien.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(80);
            } else if (i == 2) {
                column.setPreferredWidth(110);
            } else if (i == 3) {
                column.setPreferredWidth(70);
            } else if (i == 4) {
                column.setPreferredWidth(200);
            } else if (i == 5) {
                column.setPreferredWidth(50);
            } else if (i == 6) {
                column.setPreferredWidth(200);
            } else if (i == 7) {
                column.setPreferredWidth(100);
            } else if (i == 8) {
                column.setPreferredWidth(100);
            } else if (i == 9) {
                column.setPreferredWidth(250);
            }
        }
        tbDiagnosaPasien.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1 = new DefaultTableModel(null, new Object[]{
            "P", "Tgl.Rawat", "No.Rawat", "No.R.M.", "Nama Pasien", "Kode", "Nama Penyakit", "Status", "Jenis Diagnosa", "Petugas Coding RM"}) {
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
        tbDiagnosaPasien1.setModel(tabMode1);
        tbDiagnosaPasien1.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbDiagnosaPasien1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 10; i++) {
            TableColumn column = tbDiagnosaPasien1.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(80);
            } else if (i == 2) {
                column.setPreferredWidth(110);
            } else if (i == 3) {
                column.setPreferredWidth(70);
            } else if (i == 4) {
                column.setPreferredWidth(200);
            } else if (i == 5) {
                column.setPreferredWidth(50);
            } else if (i == 6) {
                column.setPreferredWidth(200);
            } else if (i == 7) {
                column.setPreferredWidth(100);
            } else if (i == 8) {
                column.setPreferredWidth(100);
            } else if (i == 9) {
                column.setPreferredWidth(250);
            }
        }
        tbDiagnosaPasien1.setDefaultRenderer(Object.class, new WarnaTable());

        tabModeDiagnosa = new DefaultTableModel(null, new Object[]{
            "P", "Kode", "Nama Penyakit", "Ciri-ciri Penyakit", "Keterangan", "Ktg.Penyakit", "Ciri-ciri Umum"}) {
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
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbDiagnosa.setModel(tabModeDiagnosa);
        //tbPenyakit.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbPenyakit.getBackground()));
        tbDiagnosa.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbDiagnosa.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (i = 0; i < 7; i++) {
            TableColumn column = tbDiagnosa.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(50);
            } else if (i == 2) {
                column.setPreferredWidth(300);
            } else if (i == 3) {
                column.setPreferredWidth(700);
            } else if (i == 4) {
//                column.setPreferredWidth(75);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 5) {
//                column.setPreferredWidth(75);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 6) {
//                column.setPreferredWidth(75);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbDiagnosa.setDefaultRenderer(Object.class, new WarnaTable());

        tabModeDiagnosaSekunder = new DefaultTableModel(null, new Object[]{
            "P", "Kode", "Nama Penyakit", "Ciri-ciri Penyakit", "Keterangan", "Ktg.Penyakit", "Ciri-ciri Umum"}) {
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
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbDiagnosa1.setModel(tabModeDiagnosaSekunder);
        //tbPenyakit.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbPenyakit.getBackground()));
        tbDiagnosa1.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbDiagnosa1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (i = 0; i < 7; i++) {
            TableColumn column = tbDiagnosa1.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(50);
            } else if (i == 2) {
                column.setPreferredWidth(300);
            } else if (i == 3) {
                column.setPreferredWidth(700);
            } else if (i == 4) {
//                column.setPreferredWidth(75);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 5) {
//                column.setPreferredWidth(75);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 6) {
//                column.setPreferredWidth(75);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbDiagnosa1.setDefaultRenderer(Object.class, new WarnaTable());

        tabModeProsedur = new DefaultTableModel(null, new Object[]{
            "P", "Kode", "Deskripsi Panjang", "Deskripsi Pendek"}) {
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
        tbProsedur.setModel(tabModeProsedur);
        //tbPenyakit.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbPenyakit.getBackground()));
        tbProsedur.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbProsedur.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 4; i++) {
            TableColumn column = tbProsedur.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(50);
            } else if (i == 2) {
                column.setPreferredWidth(300);
            } else if (i == 3) {
                column.setPreferredWidth(700);
            }
        }
        tbProsedur.setDefaultRenderer(Object.class, new WarnaTable());

        TabModeTindakanPasien = new DefaultTableModel(null, new Object[]{
            "P", "Tgl.Rawat", "No.Rawat", "No.R.M.", "Nama Pasien", "Kode", "Nama Prosedur", "Status", "Petugas Coding RM"}) {
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
                java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbTindakanPasien.setModel(TabModeTindakanPasien);
        tbTindakanPasien.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbTindakanPasien.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 9; i++) {
            TableColumn column = tbTindakanPasien.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(80);
            } else if (i == 2) {
                column.setPreferredWidth(110);
            } else if (i == 3) {
                column.setPreferredWidth(70);
            } else if (i == 4) {
                column.setPreferredWidth(200);
            } else if (i == 5) {
                column.setPreferredWidth(50);
            } else if (i == 6) {
                column.setPreferredWidth(200);
            } else if (i == 7) {
                column.setPreferredWidth(100);
            } else if (i == 8) {
                column.setPreferredWidth(250);
            }
        }
        tbTindakanPasien.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode2 = new DefaultTableModel(null, new Object[]{
            "P", "Tgl.Rawat", "No.Rawat", "No.R.M.", "Nama Pasien", "Kode", "Nama Prosedur", "Status","Jlh.", "Petugas Coding RM"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if ((colIndex == 0) || (colIndex == 8)) {
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
        tbTindakanPasien1.setModel(tabMode2);
        tbTindakanPasien1.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbTindakanPasien1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 10; i++) {
            TableColumn column = tbTindakanPasien1.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(80);
            } else if (i == 2) {
                column.setPreferredWidth(110);
            } else if (i == 3) {
                column.setPreferredWidth(70);
            } else if (i == 4) {
                column.setPreferredWidth(200);
            } else if (i == 5) {
                column.setPreferredWidth(50);
            } else if (i == 6) {
                column.setPreferredWidth(200);
            } else if (i == 7) {
                column.setPreferredWidth(100);
            } else if (i == 8) {
                column.setPreferredWidth(40);
            } else if (i == 9) {
                column.setPreferredWidth(250);
            }
        }
        tbTindakanPasien1.setDefaultRenderer(Object.class, new WarnaTable());

        this.setLocation(8, 1);
        setSize(885, 674);

        TNoRw.setDocument(new batasInput((byte) 17).getKata(TNoRw));
        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        Diagnosa.setDocument(new batasInput((byte) 100).getKata(Diagnosa));
        Diagnosa1.setDocument(new batasInput((byte) 100).getKata(Diagnosa));
        Prosedur.setDocument(new batasInput((byte) 100).getKata(Prosedur));
        TCariPasien.setDocument(new batasInput((byte) 20).getKata(TCariPasien));
        if (koneksiDB.cariCepat().equals("aktif")) {
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    tampilDiagStatistik();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    tampilDiagStatistik();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    tampilDiagStatistik();
                }
            });
        }

        if (koneksiDB.cariCepat().equals("aktif")) {
            Diagnosa.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    tampildiagnosa();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    tampildiagnosa();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    tampildiagnosa();
                }
            });
        }

        if (koneksiDB.cariCepat().equals("aktif")) {
            Diagnosa1.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    tampildiagnosaSekunder();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    tampildiagnosaSekunder();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    tampildiagnosaSekunder();
                }
            });
        }
        ChkInput.setSelected(false);
        isForm();

        pasien.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (pasien.getTable().getSelectedRow() != -1) {
                    TCariPasien.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(), 1).toString());
                }
                TCariPasien.requestFocus();
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

        pasien.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    pasien.dispose();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        
        LoadHTML1.setEditable(true);
        LoadHTML2.setEditable(true);
        
        HTMLEditorKit kit = new HTMLEditorKit();
        LoadHTML1.setEditorKit(kit);
        LoadHTML2.setEditorKit(kit);
        
        StyleSheet styleSheet = kit.getStyleSheet();
        styleSheet.addRule(".isi td{border-right: 1px solid #edf2e8;font: 10px tahoma;height:12px;border-bottom: 1px solid #edf2e8;background: 0000000;color:0000000;}");
        Document doc = kit.createDefaultDocument();
        LoadHTML1.setDocument(doc);
        LoadHTML2.setDocument(doc);
        
        LoadHTML1.setEditable(false);
        LoadHTML2.setEditable(false);
        
        LoadHTML1.addHyperlinkListener(e -> {
            if (HyperlinkEvent.EventType.ACTIVATED.equals(e.getEventType())) {
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.browse(e.getURL().toURI());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        LoadHTML2.addHyperlinkListener(e -> {
            if (HyperlinkEvent.EventType.ACTIVATED.equals(e.getEventType())) {
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.browse(e.getURL().toURI());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    int w = 0, urut;

    //private DlgCariObatPenyakit dlgobtpny=new DlgCariObatPenyakit(null,false);
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnSimpanQTYinadrg = new javax.swing.JMenuItem();
        kdpoli = new widget.TextBox();
        nmpoli = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnPrint = new widget.Button();
        BtnKlaim = new widget.Button();
        BtnAll = new widget.Button();
        jLabel10 = new widget.Label();
        LCount = new widget.Label();
        BtnKeluar = new widget.Button();
        panelGlass9 = new widget.panelisi();
        jLabel14 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel19 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel16 = new widget.Label();
        TCariPasien = new widget.TextBox();
        BtnSeek4 = new widget.Button();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        PanelInput = new javax.swing.JPanel();
        FormInput = new widget.PanelBiasa();
        jLabel3 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel13 = new widget.Label();
        jLabel17 = new widget.Label();
        Scroll1 = new widget.ScrollPane();
        tbDiagnosa = new widget.Table();
        Diagnosa = new widget.TextBox();
        BtnCariPenyakit = new widget.Button();
        btnTambahPenyakit = new widget.Button();
        btnTambahProsedur = new widget.Button();
        BtnCariProsedur = new widget.Button();
        Prosedur = new widget.TextBox();
        jLabel15 = new widget.Label();
        Scroll2 = new widget.ScrollPane();
        tbProsedur = new widget.Table();
        Status = new widget.ComboBox();
        jLabel18 = new widget.Label();
        Diagnosa1 = new widget.TextBox();
        Scroll4 = new widget.ScrollPane();
        tbDiagnosa1 = new widget.Table();
        BtnCariPenyakit1 = new widget.Button();
        btnTambahPenyakit1 = new widget.Button();
        cmbDiagPro = new widget.ComboBox();
        jLabel20 = new widget.Label();
        ChkInput = new widget.CekBox();
        TabRawat = new javax.swing.JTabbedPane();
        internalFrame2 = new widget.InternalFrame();
        internalFrame9 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbDiagnosaPasien = new widget.Table();
        Scroll5 = new widget.ScrollPane();
        tbDiagnosaPasien1 = new widget.Table();
        internalFrame3 = new widget.InternalFrame();
        internalFrame4 = new widget.InternalFrame();
        Scroll3 = new widget.ScrollPane();
        tbTindakanPasien = new widget.Table();
        Scroll6 = new widget.ScrollPane();
        tbTindakanPasien1 = new widget.Table();
        internalFrame6 = new widget.InternalFrame();
        Scroll7 = new widget.ScrollPane();
        FormInput1 = new widget.PanelBiasa();
        jLabel12 = new widget.Label();
        Scroll9 = new widget.ScrollPane();
        TDiagDokter = new widget.TextArea();
        jLabel21 = new widget.Label();
        Scroll10 = new widget.ScrollPane();
        TKeluhan1 = new widget.TextArea();
        jLabel22 = new widget.Label();
        Scroll11 = new widget.ScrollPane();
        TRincianTindakan1 = new widget.TextArea();
        jLabel23 = new widget.Label();
        Scroll8 = new widget.ScrollPane();
        TDiagPerawat = new widget.TextArea();
        jLabel24 = new widget.Label();
        Scroll12 = new widget.ScrollPane();
        TKeluhan2 = new widget.TextArea();
        jLabel25 = new widget.Label();
        Scroll13 = new widget.ScrollPane();
        TRincianTindakan2 = new widget.TextArea();
        internalFrame7 = new widget.InternalFrame();
        Scroll43 = new widget.ScrollPane();
        LoadHTML1 = new widget.editorpane();
        internalFrame8 = new widget.InternalFrame();
        Scroll44 = new widget.ScrollPane();
        LoadHTML2 = new widget.editorpane();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnSimpanQTYinadrg.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSimpanQTYinadrg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSimpanQTYinadrg.setText("Simpan Jumlah Prosedur INADRG");
        MnSimpanQTYinadrg.setName("MnSimpanQTYinadrg"); // NOI18N
        MnSimpanQTYinadrg.setPreferredSize(new java.awt.Dimension(240, 26));
        MnSimpanQTYinadrg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSimpanQTYinadrgActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnSimpanQTYinadrg);

        kdpoli.setEditable(false);
        kdpoli.setForeground(new java.awt.Color(0, 0, 0));
        kdpoli.setHighlighter(null);
        kdpoli.setName("kdpoli"); // NOI18N

        nmpoli.setEditable(false);
        nmpoli.setForeground(new java.awt.Color(0, 0, 0));
        nmpoli.setHighlighter(null);
        nmpoli.setName("nmpoli"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Riwayat Diagnosa & Prosedur Tindakan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 100));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 44));
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

        BtnPrint.setForeground(new java.awt.Color(0, 0, 0));
        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint.setMnemonic('P');
        BtnPrint.setText("Formulir Klaim Pasien");
        BtnPrint.setToolTipText("Alt+P");
        BtnPrint.setName("BtnPrint"); // NOI18N
        BtnPrint.setPreferredSize(new java.awt.Dimension(170, 30));
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

        BtnKlaim.setForeground(new java.awt.Color(0, 0, 0));
        BtnKlaim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        BtnKlaim.setMnemonic('P');
        BtnKlaim.setText("Pengajuan Klaim JKN");
        BtnKlaim.setToolTipText("Alt+P");
        BtnKlaim.setGlassColor(new java.awt.Color(255, 204, 0));
        BtnKlaim.setName("BtnKlaim"); // NOI18N
        BtnKlaim.setPreferredSize(new java.awt.Dimension(170, 30));
        BtnKlaim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKlaimActionPerformed(evt);
            }
        });
        BtnKlaim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKlaimKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnKlaim);

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

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Record :");
        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass8.add(jLabel10);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(110, 23));
        panelGlass8.add(LCount);

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

        jPanel3.add(panelGlass8, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 10));

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Tgl. Rawat :");
        jLabel14.setName("jLabel14"); // NOI18N
        jLabel14.setPreferredSize(new java.awt.Dimension(63, 23));
        panelGlass9.add(jLabel14);

        DTPCari1.setEditable(false);
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "07-09-2023" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(100, 23));
        panelGlass9.add(DTPCari1);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("s.d");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(18, 23));
        panelGlass9.add(jLabel19);

        DTPCari2.setEditable(false);
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "07-09-2023" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(100, 23));
        panelGlass9.add(DTPCari2);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("No.RM :");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass9.add(jLabel16);

        TCariPasien.setForeground(new java.awt.Color(0, 0, 0));
        TCariPasien.setName("TCariPasien"); // NOI18N
        TCariPasien.setPreferredSize(new java.awt.Dimension(130, 23));
        TCariPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariPasienKeyPressed(evt);
            }
        });
        panelGlass9.add(TCariPasien);

        BtnSeek4.setForeground(new java.awt.Color(0, 0, 0));
        BtnSeek4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/download24.png"))); // NOI18N
        BtnSeek4.setMnemonic('5');
        BtnSeek4.setToolTipText("Alt+5");
        BtnSeek4.setName("BtnSeek4"); // NOI18N
        BtnSeek4.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnSeek4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeek4ActionPerformed(evt);
            }
        });
        BtnSeek4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSeek4KeyPressed(evt);
            }
        });
        panelGlass9.add(BtnSeek4);

        jSeparator5.setBackground(new java.awt.Color(220, 225, 215));
        jSeparator5.setForeground(new java.awt.Color(220, 225, 215));
        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator5.setName("jSeparator5"); // NOI18N
        jSeparator5.setOpaque(true);
        jSeparator5.setPreferredSize(new java.awt.Dimension(1, 23));
        panelGlass9.add(jSeparator5);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass9.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(190, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('6');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+6");
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
        panelGlass9.add(BtnCari);

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(865, 350));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(865, 217));
        FormInput.setLayout(null);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("No.Rawat :");
        jLabel3.setName("jLabel3"); // NOI18N
        FormInput.add(jLabel3);
        jLabel3.setBounds(0, 12, 68, 23);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        FormInput.add(TNoRw);
        TNoRw.setBounds(71, 12, 140, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(213, 12, 110, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        TPasien.setPreferredSize(new java.awt.Dimension(25, 28));
        FormInput.add(TPasien);
        TPasien.setBounds(325, 12, 330, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Diagnosa Primer :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(0, 42, 97, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Status :");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(667, 12, 50, 23);

        Scroll1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)));
        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbDiagnosa.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbDiagnosa.setName("tbDiagnosa"); // NOI18N
        tbDiagnosa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDiagnosaKeyPressed(evt);
            }
        });
        Scroll1.setViewportView(tbDiagnosa);

        FormInput.add(Scroll1);
        Scroll1.setBounds(11, 69, 720, 105);

        Diagnosa.setForeground(new java.awt.Color(0, 0, 0));
        Diagnosa.setName("Diagnosa"); // NOI18N
        Diagnosa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiagnosaKeyPressed(evt);
            }
        });
        FormInput.add(Diagnosa);
        Diagnosa.setBounds(101, 42, 270, 23);

        BtnCariPenyakit.setForeground(new java.awt.Color(0, 0, 0));
        BtnCariPenyakit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCariPenyakit.setMnemonic('1');
        BtnCariPenyakit.setToolTipText("Alt+1");
        BtnCariPenyakit.setName("BtnCariPenyakit"); // NOI18N
        BtnCariPenyakit.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCariPenyakit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariPenyakitActionPerformed(evt);
            }
        });
        FormInput.add(BtnCariPenyakit);
        BtnCariPenyakit.setBounds(373, 42, 28, 23);

        btnTambahPenyakit.setForeground(new java.awt.Color(0, 0, 0));
        btnTambahPenyakit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        btnTambahPenyakit.setMnemonic('2');
        btnTambahPenyakit.setToolTipText("Alt+2");
        btnTambahPenyakit.setEnabled(false);
        btnTambahPenyakit.setName("btnTambahPenyakit"); // NOI18N
        btnTambahPenyakit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahPenyakitActionPerformed(evt);
            }
        });
        FormInput.add(btnTambahPenyakit);
        btnTambahPenyakit.setBounds(403, 42, 28, 23);

        btnTambahProsedur.setForeground(new java.awt.Color(0, 0, 0));
        btnTambahProsedur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        btnTambahProsedur.setMnemonic('2');
        btnTambahProsedur.setToolTipText("Alt+2");
        btnTambahProsedur.setEnabled(false);
        btnTambahProsedur.setName("btnTambahProsedur"); // NOI18N
        btnTambahProsedur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahProsedurActionPerformed(evt);
            }
        });
        FormInput.add(btnTambahProsedur);
        btnTambahProsedur.setBounds(1100, 42, 28, 23);

        BtnCariProsedur.setForeground(new java.awt.Color(0, 0, 0));
        BtnCariProsedur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCariProsedur.setMnemonic('1');
        BtnCariProsedur.setToolTipText("Alt+1");
        BtnCariProsedur.setName("BtnCariProsedur"); // NOI18N
        BtnCariProsedur.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCariProsedur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariProsedurActionPerformed(evt);
            }
        });
        FormInput.add(BtnCariProsedur);
        BtnCariProsedur.setBounds(1065, 42, 28, 23);

        Prosedur.setForeground(new java.awt.Color(0, 0, 0));
        Prosedur.setName("Prosedur"); // NOI18N
        Prosedur.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ProsedurKeyPressed(evt);
            }
        });
        FormInput.add(Prosedur);
        Prosedur.setBounds(798, 42, 260, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Prosedur :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(735, 42, 60, 23);

        Scroll2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)));
        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);

        tbProsedur.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbProsedur.setName("tbProsedur"); // NOI18N
        tbProsedur.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbProsedurKeyPressed(evt);
            }
        });
        Scroll2.setViewportView(tbProsedur);

        FormInput.add(Scroll2);
        Scroll2.setBounds(738, 67, 620, 253);

        Status.setForeground(new java.awt.Color(0, 0, 0));
        Status.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ralan", "Ranap" }));
        Status.setEnabled(false);
        Status.setName("Status"); // NOI18N
        Status.setPreferredSize(new java.awt.Dimension(308, 23));
        FormInput.add(Status);
        Status.setBounds(720, 12, 70, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Diagnosa Sekunder :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(0, 180, 110, 23);

        Diagnosa1.setForeground(new java.awt.Color(0, 0, 0));
        Diagnosa1.setName("Diagnosa1"); // NOI18N
        Diagnosa1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Diagnosa1KeyPressed(evt);
            }
        });
        FormInput.add(Diagnosa1);
        Diagnosa1.setBounds(114, 180, 253, 23);

        Scroll4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)));
        Scroll4.setName("Scroll4"); // NOI18N
        Scroll4.setOpaque(true);

        tbDiagnosa1.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbDiagnosa1.setName("tbDiagnosa1"); // NOI18N
        tbDiagnosa1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDiagnosa1KeyPressed(evt);
            }
        });
        Scroll4.setViewportView(tbDiagnosa1);

        FormInput.add(Scroll4);
        Scroll4.setBounds(11, 210, 720, 110);

        BtnCariPenyakit1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCariPenyakit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCariPenyakit1.setMnemonic('1');
        BtnCariPenyakit1.setToolTipText("Alt+1");
        BtnCariPenyakit1.setName("BtnCariPenyakit1"); // NOI18N
        BtnCariPenyakit1.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCariPenyakit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariPenyakit1ActionPerformed(evt);
            }
        });
        FormInput.add(BtnCariPenyakit1);
        BtnCariPenyakit1.setBounds(373, 180, 28, 23);

        btnTambahPenyakit1.setForeground(new java.awt.Color(0, 0, 0));
        btnTambahPenyakit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        btnTambahPenyakit1.setMnemonic('2');
        btnTambahPenyakit1.setToolTipText("Alt+2");
        btnTambahPenyakit1.setEnabled(false);
        btnTambahPenyakit1.setName("btnTambahPenyakit1"); // NOI18N
        btnTambahPenyakit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahPenyakit1ActionPerformed(evt);
            }
        });
        FormInput.add(btnTambahPenyakit1);
        btnTambahPenyakit1.setBounds(403, 180, 28, 23);

        cmbDiagPro.setForeground(new java.awt.Color(0, 0, 0));
        cmbDiagPro.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Semua", "STATISTIK", "INADRG" }));
        cmbDiagPro.setName("cmbDiagPro"); // NOI18N
        cmbDiagPro.setPreferredSize(new java.awt.Dimension(308, 23));
        FormInput.add(cmbDiagPro);
        cmbDiagPro.setBounds(943, 12, 85, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Jns. Diagnosa & Prosedur : ");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(790, 12, 150, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        ChkInput.setForeground(new java.awt.Color(0, 0, 0));
        ChkInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setMnemonic('I');
        ChkInput.setText(".: Input Data");
        ChkInput.setToolTipText("Alt+I");
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

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        TabRawat.setBackground(new java.awt.Color(250, 255, 245));
        TabRawat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 235, 225)));
        TabRawat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        internalFrame9.setBorder(null);
        internalFrame9.setName("internalFrame9"); // NOI18N
        internalFrame9.setLayout(new java.awt.GridLayout(1, 2));

        Scroll.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Diagnosa Statistik ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll.setName("Scroll"); // NOI18N

        tbDiagnosaPasien.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbDiagnosaPasien.setName("tbDiagnosaPasien"); // NOI18N
        tbDiagnosaPasien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDiagnosaPasienMouseClicked(evt);
            }
        });
        tbDiagnosaPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDiagnosaPasienKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbDiagnosaPasien);

        internalFrame9.add(Scroll);

        Scroll5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Diagnosa INADRG ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll5.setName("Scroll5"); // NOI18N

        tbDiagnosaPasien1.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbDiagnosaPasien1.setName("tbDiagnosaPasien1"); // NOI18N
        tbDiagnosaPasien1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDiagnosaPasien1MouseClicked(evt);
            }
        });
        tbDiagnosaPasien1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDiagnosaPasien1KeyPressed(evt);
            }
        });
        Scroll5.setViewportView(tbDiagnosaPasien1);

        internalFrame9.add(Scroll5);

        internalFrame2.add(internalFrame9, java.awt.BorderLayout.PAGE_START);

        TabRawat.addTab("Diagnosa", internalFrame2);

        internalFrame3.setBorder(null);
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        internalFrame4.setBorder(null);
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.GridLayout(1, 2));

        Scroll3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Prosedur Statistik]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll3.setName("Scroll3"); // NOI18N

        tbTindakanPasien.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbTindakanPasien.setName("tbTindakanPasien"); // NOI18N
        tbTindakanPasien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTindakanPasienMouseClicked(evt);
            }
        });
        tbTindakanPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbTindakanPasienKeyPressed(evt);
            }
        });
        Scroll3.setViewportView(tbTindakanPasien);

        internalFrame4.add(Scroll3);

        Scroll6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Prosedur INADRG ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll6.setName("Scroll6"); // NOI18N

        tbTindakanPasien1.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbTindakanPasien1.setComponentPopupMenu(jPopupMenu1);
        tbTindakanPasien1.setName("tbTindakanPasien1"); // NOI18N
        tbTindakanPasien1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTindakanPasien1MouseClicked(evt);
            }
        });
        tbTindakanPasien1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbTindakanPasien1KeyPressed(evt);
            }
        });
        Scroll6.setViewportView(tbTindakanPasien1);

        internalFrame4.add(Scroll6);

        internalFrame3.add(internalFrame4, java.awt.BorderLayout.PAGE_START);

        TabRawat.addTab("Prosedur", internalFrame3);

        internalFrame6.setBorder(null);
        internalFrame6.setName("internalFrame6"); // NOI18N
        internalFrame6.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll7.setName("Scroll7"); // NOI18N
        Scroll7.setOpaque(true);

        FormInput1.setName("FormInput1"); // NOI18N
        FormInput1.setPreferredSize(new java.awt.Dimension(190, 210));
        FormInput1.setLayout(null);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Diagnosa Resume dari Dokter : ");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput1.add(jLabel12);
        jLabel12.setBounds(0, 10, 208, 23);

        Scroll9.setName("Scroll9"); // NOI18N

        TDiagDokter.setEditable(false);
        TDiagDokter.setColumns(20);
        TDiagDokter.setRows(5);
        TDiagDokter.setName("TDiagDokter"); // NOI18N
        TDiagDokter.setPreferredSize(new java.awt.Dimension(170, 600));
        Scroll9.setViewportView(TDiagDokter);

        FormInput1.add(Scroll9);
        Scroll9.setBounds(212, 10, 430, 55);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Catatan Keluhan Dari Dokter : ");
        jLabel21.setName("jLabel21"); // NOI18N
        FormInput1.add(jLabel21);
        jLabel21.setBounds(0, 73, 208, 23);

        Scroll10.setName("Scroll10"); // NOI18N

        TKeluhan1.setEditable(false);
        TKeluhan1.setColumns(20);
        TKeluhan1.setRows(5);
        TKeluhan1.setName("TKeluhan1"); // NOI18N
        TKeluhan1.setPreferredSize(new java.awt.Dimension(170, 600));
        Scroll10.setViewportView(TKeluhan1);

        FormInput1.add(Scroll10);
        Scroll10.setBounds(212, 73, 430, 55);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Catatan Rincian Tindakan Dari Dokter :");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput1.add(jLabel22);
        jLabel22.setBounds(0, 135, 208, 23);

        Scroll11.setName("Scroll11"); // NOI18N

        TRincianTindakan1.setEditable(false);
        TRincianTindakan1.setColumns(20);
        TRincianTindakan1.setRows(5);
        TRincianTindakan1.setName("TRincianTindakan1"); // NOI18N
        TRincianTindakan1.setPreferredSize(new java.awt.Dimension(170, 600));
        Scroll11.setViewportView(TRincianTindakan1);

        FormInput1.add(Scroll11);
        Scroll11.setBounds(212, 135, 430, 55);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Diagnosa Resume dari Perawat/Bidan : ");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput1.add(jLabel23);
        jLabel23.setBounds(640, 10, 240, 23);

        Scroll8.setName("Scroll8"); // NOI18N

        TDiagPerawat.setEditable(false);
        TDiagPerawat.setColumns(20);
        TDiagPerawat.setRows(5);
        TDiagPerawat.setName("TDiagPerawat"); // NOI18N
        TDiagPerawat.setPreferredSize(new java.awt.Dimension(170, 600));
        Scroll8.setViewportView(TDiagPerawat);

        FormInput1.add(Scroll8);
        Scroll8.setBounds(883, 10, 430, 55);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Catatan Keluhan Dari Perawat/Bidan : ");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput1.add(jLabel24);
        jLabel24.setBounds(640, 73, 240, 23);

        Scroll12.setName("Scroll12"); // NOI18N

        TKeluhan2.setEditable(false);
        TKeluhan2.setColumns(20);
        TKeluhan2.setRows(5);
        TKeluhan2.setName("TKeluhan2"); // NOI18N
        TKeluhan2.setPreferredSize(new java.awt.Dimension(170, 600));
        Scroll12.setViewportView(TKeluhan2);

        FormInput1.add(Scroll12);
        Scroll12.setBounds(883, 73, 430, 55);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Catatan Rincian Tindakan Dari Perawat/Bidan : ");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput1.add(jLabel25);
        jLabel25.setBounds(640, 135, 240, 23);

        Scroll13.setName("Scroll13"); // NOI18N

        TRincianTindakan2.setEditable(false);
        TRincianTindakan2.setColumns(20);
        TRincianTindakan2.setRows(5);
        TRincianTindakan2.setName("TRincianTindakan2"); // NOI18N
        TRincianTindakan2.setPreferredSize(new java.awt.Dimension(170, 600));
        Scroll13.setViewportView(TRincianTindakan2);

        FormInput1.add(Scroll13);
        Scroll13.setBounds(883, 135, 430, 55);

        Scroll7.setViewportView(FormInput1);

        internalFrame6.add(Scroll7, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Pemriksn. Poliklinik/Inst.", internalFrame6);

        internalFrame7.setBorder(null);
        internalFrame7.setName("internalFrame7"); // NOI18N
        internalFrame7.setLayout(new java.awt.BorderLayout());

        Scroll43.setName("Scroll43"); // NOI18N
        Scroll43.setOpaque(true);

        LoadHTML1.setBorder(null);
        LoadHTML1.setForeground(new java.awt.Color(0, 0, 0));
        LoadHTML1.setName("LoadHTML1"); // NOI18N
        Scroll43.setViewportView(LoadHTML1);

        internalFrame7.add(Scroll43, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Hasil Pemriksn. Radiologi", internalFrame7);

        internalFrame8.setBorder(null);
        internalFrame8.setName("internalFrame8"); // NOI18N
        internalFrame8.setLayout(new java.awt.BorderLayout());

        Scroll44.setName("Scroll44"); // NOI18N
        Scroll44.setOpaque(true);

        LoadHTML2.setBorder(null);
        LoadHTML2.setForeground(new java.awt.Color(0, 0, 0));
        LoadHTML2.setName("LoadHTML2"); // NOI18N
        Scroll44.setViewportView(LoadHTML2);

        internalFrame8.add(Scroll44, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Hasil Pemriksn. Lab.", internalFrame8);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        if (TabRawat.getSelectedIndex() == 0) {
            tampilDiagStatistik();
            tampilDiagInadrg();
        } else if (TabRawat.getSelectedIndex() == 1) {
            tampilProsStatistik();
            tampilProsInadrg();
        } else if (TabRawat.getSelectedIndex() == 2) {
            if (Status.getSelectedIndex() == 0) {
                tampilPemeriksaan();
            } else {
                TDiagDokter.setText("-");
                TKeluhan1.setText("-");
                TRincianTindakan1.setText("-");

                TDiagPerawat.setText("-");
                TKeluhan2.setText("-");
                TRincianTindakan2.setText("-");
            }
        } else if (TabRawat.getSelectedIndex() == 3) {
            tampilHasilRadiologi();
        } else if (TabRawat.getSelectedIndex() == 4) {
            tampilHasilLaboratorium();
        }
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        cekKlaim = "";
        
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "No.Rawat");
        } else {
            cekKlaim = Sequel.cariIsi("SELECT klaim_final FROM eklaim_new_claim WHERE no_rawat='" + TNoRw.getText() + "'");

            if (cekKlaim.equals("Final")) {
                JOptionPane.showMessageDialog(null, "Proses pengajuan klaim selesai dilakukan, data ICD-10 atau ICD-9-CM pasien ini tdk. dapat disimpan...!!");
            } else {
                if (cmbDiagPro.getSelectedIndex() == 0) {
                    simpan_diagproStatistik();
                    simpan_diagproINADRG();
                } else if (cmbDiagPro.getSelectedIndex() == 1) {
                    simpan_diagproStatistik();
                } else if (cmbDiagPro.getSelectedIndex() == 2) {
                    simpan_diagproINADRG();
                }
           
                BtnCariActionPerformed(null);
                BtnBatalActionPerformed(null);
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, Diagnosa, BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        Diagnosa.setText("");
        Diagnosa1.setText("");
        kdpoli.setText("");
        nmpoli.setText("");
        Prosedur.setText("");
        TNoRw.requestFocus();
        
        for (i = 0; i < tbDiagnosa.getRowCount(); i++) {
            tbDiagnosa.setValueAt(false, i, 0);
        }
        for (s = 0; s < tbDiagnosa1.getRowCount(); s++) {
            tbDiagnosa1.setValueAt(false, s, 0);
        }
        for (i = 0; i < tbProsedur.getRowCount(); i++) {
            tbProsedur.setValueAt(false, i, 0);
        }
        
        if (Status.getSelectedIndex() == 0) {
            cmbDiagPro.setSelectedIndex(1);
        } else {
            cmbDiagPro.setSelectedIndex(0);
        }
        
        ChkInput.setSelected(true);
        isForm();        
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnBatalActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnSimpan, BtnHapus);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        cekKlaim = "";
       
        if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Gagal menghapus. Pilih dulu data yang mau dihapus. Klik data pada tabel untuk memilih...!!!!");
        } else if (!(TPasien.getText().trim().equals(""))) {
            cekKlaim = Sequel.cariIsi("SELECT klaim_final FROM eklaim_new_claim WHERE no_rawat='" + TNoRw.getText() + "'");

            if (cekKlaim.equals("Final")) {
                JOptionPane.showMessageDialog(null, "Proses pengajuan klaim selesai dilakukan, data ICD-10 atau ICD-9-CM pasien ini tdk. dapat dihapus...!!");
            } else {
                if (cmbDiagPro.getSelectedIndex() == 0) {
                    hapus_diagproStatistik();
                    hapus_diagproINADRG();
                } else if (cmbDiagPro.getSelectedIndex() == 1) {
                    hapus_diagproStatistik();
                } else if (cmbDiagPro.getSelectedIndex() == 2) {
                    hapus_diagproINADRG();
                }
            }

            BtnCariActionPerformed(null);
            BtnBatalActionPerformed(null);
        }
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnPrint);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        cekPremier = 0;
        cekPremier = Sequel.cariInteger("select count(-1) from diagnosa_pasien where no_rawat='" + TNoRw.getText() + "' and prioritas=1");

        if (TabRawat.getSelectedIndex() == 0) {
            if (TabModeDiagnosaPasien.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Belum ada diagnosa pasien yang tersimpan...!!!!");
                BtnBatal.requestFocus();
            } else if (TabModeDiagnosaPasien.getRowCount() != 0 && (Status.getSelectedItem().equals("Ralan"))) {
                if (cekPremier == 0) {
                    JOptionPane.showMessageDialog(null, "Diagnosa primer belum tersimpan untuk kunjungan pasien saat ini...");
                } else {
                    formulirKlaim();
                }
            } else if (!Status.getSelectedItem().equals("Ralan")) {
                JOptionPane.showMessageDialog(null, "Untuk saat ini hanya mencetak formulir klaim pasien rawat jalan saja...!!!!");
            }

        } else if (TabRawat.getSelectedIndex() == 1) {
            if (cekPremier == 0 && (Status.getSelectedItem().equals("Ralan"))) {
                JOptionPane.showMessageDialog(null, "Diagnosa primer belum tersimpan untuk kunjungan pasien saat ini...");
            } else if (!Status.getSelectedItem().equals("Ralan")) {
                JOptionPane.showMessageDialog(null, "Untuk saat ini hanya mencetak formulir klaim pasien rawat jalan saja...!!!!");
            } else {
                formulirKlaim();
            }
        }
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnAll);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();        
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnPrint, TCari);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        TCariPasien.setText("");
        BtnCariActionPerformed(null);
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAllActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnPrint, BtnKeluar);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void TCariPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariPasienKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (TabRawat.getSelectedIndex() == 0) {
                tampilDiagStatistik();
                tampilDiagInadrg();
            } else if (TabRawat.getSelectedIndex() == 1) {
                tampilProsStatistik();
                tampilProsInadrg();
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnSeek4.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            DTPCari2.requestFocus();
        }
    }//GEN-LAST:event_TCariPasienKeyPressed

    private void BtnSeek4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeek4ActionPerformed
        pasien.emptTeks();
        pasien.isCek();
        pasien.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        pasien.setLocationRelativeTo(internalFrame1);
        pasien.setVisible(true);
    }//GEN-LAST:event_BtnSeek4ActionPerformed

    private void BtnSeek4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSeek4KeyPressed
        Valid.pindah(evt, TCariPasien, DTPCari1);
    }//GEN-LAST:event_BtnSeek4KeyPressed

    private void tbDiagnosaPasienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDiagnosaPasienMouseClicked
        if (TabModeDiagnosaPasien.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }

        }
}//GEN-LAST:event_tbDiagnosaPasienMouseClicked

    private void tbDiagnosaPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDiagnosaPasienKeyPressed
        if (TabModeDiagnosaPasien.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbDiagnosaPasienKeyPressed

private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
    isForm();
}//GEN-LAST:event_ChkInputActionPerformed

    private void DiagnosaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiagnosaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!TNoRw.getText().equals("")) {
                tampildiagnosa();
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            if (akses.getpenyakit() == true) {
                btnTambahPenyakitActionPerformed(null);
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            tbDiagnosa.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnSimpan.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            TNoRw.requestFocus();
        }
    }//GEN-LAST:event_DiagnosaKeyPressed

    private void BtnCariPenyakitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariPenyakitActionPerformed
        if (!TNoRw.getText().equals("")) {
            tampildiagnosa();
        }
    }//GEN-LAST:event_BtnCariPenyakitActionPerformed

    private void btnTambahPenyakitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahPenyakitActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgPenyakit tariflab = new DlgPenyakit(null, false);
        tariflab.emptTeks();
        tariflab.isCek();
        tariflab.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        tariflab.setLocationRelativeTo(internalFrame1);
        tariflab.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnTambahPenyakitActionPerformed

    private void tbDiagnosaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDiagnosaKeyPressed
        if (tbDiagnosa.getRowCount() != 0) {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                try {
                    i = tbDiagnosa.getSelectedColumn();
                    if (i == 1) {
                        if (tbDiagnosa.getSelectedRow() > -1) {
                            tbDiagnosa.setValueAt(true, tbDiagnosa.getSelectedRow(), 0);
                        }
                        Diagnosa.setText("");
                        Diagnosa.requestFocus();
                    }
                } catch (java.lang.NullPointerException e) {
                }
            } else if (evt.getKeyCode() == KeyEvent.VK_SHIFT) {
                Diagnosa.setText("");
                Diagnosa.requestFocus();
            }
        }
    }//GEN-LAST:event_tbDiagnosaKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampildiagnosa();
        tampildiagnosaSekunder();
        tampilprosedure();
    }//GEN-LAST:event_formWindowOpened

    private void btnTambahProsedurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahProsedurActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgICD9 tariflab = new DlgICD9(null, false);
        tariflab.emptTeks();
        tariflab.isCek();
        tariflab.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        tariflab.setLocationRelativeTo(internalFrame1);
        tariflab.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnTambahProsedurActionPerformed

    private void BtnCariProsedurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariProsedurActionPerformed
        if (!TNoRw.getText().equals("")) {
            tampilprosedure();
        }
    }//GEN-LAST:event_BtnCariProsedurActionPerformed

    private void ProsedurKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ProsedurKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!TNoRw.getText().equals("")) {
                tampilprosedure();
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            if (akses.geticd9() == true) {
                btnTambahProsedurActionPerformed(null);
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            tbProsedur.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnSimpan.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            TNoRw.requestFocus();
        }
    }//GEN-LAST:event_ProsedurKeyPressed

    private void tbProsedurKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbProsedurKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbProsedurKeyPressed

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        BtnCariActionPerformed(null);
    }//GEN-LAST:event_TabRawatMouseClicked

    private void tbTindakanPasienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTindakanPasienMouseClicked
        if (TabModeTindakanPasien.getRowCount() != 0) {
            try {
                getData2();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbTindakanPasienMouseClicked

    private void tbTindakanPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbTindakanPasienKeyPressed
        if (TabModeTindakanPasien.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData2();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbTindakanPasienKeyPressed

    private void Diagnosa1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Diagnosa1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!TNoRw.getText().equals("")) {
                tampildiagnosaSekunder();
            }
        } //        else if (evt.getKeyCode() == KeyEvent.VK_UP) {
        //            if (var.getpenyakit1() == true) {
        //                btnTambahPenyakit1ActionPerformed(null);
        //            }
        //        } 
        else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            tbDiagnosa1.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnSimpan.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            TNoRw.requestFocus();
        }
    }//GEN-LAST:event_Diagnosa1KeyPressed

    private void tbDiagnosa1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDiagnosa1KeyPressed
        if (tbDiagnosa1.getRowCount() != 0) {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                try {
                    i = tbDiagnosa1.getSelectedColumn();
                    if (i == 1) {
                        if (tbDiagnosa1.getSelectedRow() > -1) {
                            tbDiagnosa1.setValueAt(true, tbDiagnosa1.getSelectedRow(), 0);
                        }
                        Diagnosa1.setText("");
                        Diagnosa1.requestFocus();
                    }
                } catch (java.lang.NullPointerException e) {
                }
            } else if (evt.getKeyCode() == KeyEvent.VK_SHIFT) {
                Diagnosa1.setText("");
                Diagnosa1.requestFocus();
            }
        }
    }//GEN-LAST:event_tbDiagnosa1KeyPressed

    private void BtnCariPenyakit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariPenyakit1ActionPerformed
        if (!TNoRw.getText().equals("")) {
            tampildiagnosaSekunder();
        }
    }//GEN-LAST:event_BtnCariPenyakit1ActionPerformed

    private void btnTambahPenyakit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahPenyakit1ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgPenyakit diagsekunder = new DlgPenyakit(null, false);
        diagsekunder.emptTeks();
        diagsekunder.isCek();
        diagsekunder.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        diagsekunder.setLocationRelativeTo(internalFrame1);
        diagsekunder.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnTambahPenyakit1ActionPerformed

    private void tbDiagnosaPasien1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDiagnosaPasien1MouseClicked
        if (tabMode1.getRowCount() != 0) {
            try {
                getData3();
            } catch (java.lang.NullPointerException e) {
            }

        }
    }//GEN-LAST:event_tbDiagnosaPasien1MouseClicked

    private void tbDiagnosaPasien1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDiagnosaPasien1KeyPressed
        if (tabMode1.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData3();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbDiagnosaPasien1KeyPressed

    private void tbTindakanPasien1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTindakanPasien1MouseClicked
        if (tabMode2.getRowCount() != 0) {
            try {
                getData4();
            } catch (java.lang.NullPointerException e) {
            }

        }
    }//GEN-LAST:event_tbTindakanPasien1MouseClicked

    private void tbTindakanPasien1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbTindakanPasien1KeyPressed
        if (tabMode2.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData4();
                } catch (java.lang.NullPointerException e) {
                }
            }

            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                for (i = 0; i < tbTindakanPasien1.getRowCount(); i++) {
                    if (tbTindakanPasien1.getValueAt(i, 8).toString().equals("") || Integer.parseInt(tbTindakanPasien1.getValueAt(i, 8).toString()) == 0) {
                        tabMode2.setValueAt("1", i, 8);
                    }
                }
            }
        }
    }//GEN-LAST:event_tbTindakanPasien1KeyPressed

    private void MnSimpanQTYinadrgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSimpanQTYinadrgActionPerformed
        simpanQTYprosedurINADRG();
        for (i = 0; i < tbTindakanPasien1.getRowCount(); i++) {
            tbTindakanPasien1.setValueAt(false, i, 0);
        }
        tampilProsInadrg();
    }//GEN-LAST:event_MnSimpanQTYinadrgActionPerformed

    private void BtnKlaimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKlaimActionPerformed
        if (TNoRw.getText().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else if (!Sequel.cariIsi("select kd_pj from reg_periksa where no_rawat='" + TNoRw.getText() + "'").equals("B01")) {
            JOptionPane.showMessageDialog(null, "Hanya untuk pengajuan klaim pasien BPJS saja...!!!");
        } else if (tbDiagnosaPasien.getRowCount() == 0 && tbDiagnosaPasien1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Diagnosa utk. proses pengajuan klaim belum tersimpan...!!!");
        } else if (Sequel.cariInteger("select count(-1) from bridging_sep where no_rawat='" + TNoRw.getText() + "' and jnspelayanan='1'") == 0) {
            JOptionPane.showMessageDialog(null, "SEP nya belum ada utk. proses pengajuan klaim...!!!");
        } else {
            INACBGDaftarKlaim diklaim = new INACBGDaftarKlaim(null, false);
            diklaim.isCek();
            diklaim.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            diklaim.setLocationRelativeTo(internalFrame1);
            diklaim.verifData();
            diklaim.KlaimRAZA(TNoRw.getText(), Sequel.cariIsi("select no_sep from bridging_sep where no_rawat='" + TNoRw.getText() + "'"),
                    "JKN", "3", Sequel.cariIsi("select tglsep from bridging_sep where no_rawat='" + TNoRw.getText() + "'"));
            diklaim.setVisible(true);
        }
    }//GEN-LAST:event_BtnKlaimActionPerformed

    private void BtnKlaimKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKlaimKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnKlaimKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgDiagnosaPenyakit dialog = new DlgDiagnosaPenyakit(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCariPenyakit;
    private widget.Button BtnCariPenyakit1;
    private widget.Button BtnCariProsedur;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnKlaim;
    private widget.Button BtnPrint;
    private widget.Button BtnSeek4;
    private widget.Button BtnSimpan;
    private widget.CekBox ChkInput;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.TextBox Diagnosa;
    private widget.TextBox Diagnosa1;
    private widget.PanelBiasa FormInput;
    private widget.PanelBiasa FormInput1;
    private widget.Label LCount;
    private widget.editorpane LoadHTML1;
    private widget.editorpane LoadHTML2;
    private javax.swing.JMenuItem MnSimpanQTYinadrg;
    private javax.swing.JPanel PanelInput;
    private widget.TextBox Prosedur;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll10;
    private widget.ScrollPane Scroll11;
    private widget.ScrollPane Scroll12;
    private widget.ScrollPane Scroll13;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll4;
    private widget.ScrollPane Scroll43;
    private widget.ScrollPane Scroll44;
    private widget.ScrollPane Scroll5;
    private widget.ScrollPane Scroll6;
    private widget.ScrollPane Scroll7;
    private widget.ScrollPane Scroll8;
    private widget.ScrollPane Scroll9;
    private widget.ComboBox Status;
    private widget.TextBox TCari;
    private widget.TextBox TCariPasien;
    private widget.TextArea TDiagDokter;
    private widget.TextArea TDiagPerawat;
    private widget.TextArea TKeluhan1;
    private widget.TextArea TKeluhan2;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextArea TRincianTindakan1;
    private widget.TextArea TRincianTindakan2;
    private javax.swing.JTabbedPane TabRawat;
    private widget.Button btnTambahPenyakit;
    private widget.Button btnTambahPenyakit1;
    private widget.Button btnTambahProsedur;
    private widget.ComboBox cmbDiagPro;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame4;
    private widget.InternalFrame internalFrame6;
    private widget.InternalFrame internalFrame7;
    private widget.InternalFrame internalFrame8;
    private widget.InternalFrame internalFrame9;
    private widget.Label jLabel10;
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
    private widget.Label jLabel23;
    private widget.Label jLabel24;
    private widget.Label jLabel25;
    private widget.Label jLabel3;
    private widget.Label jLabel6;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JSeparator jSeparator5;
    private widget.TextBox kdpoli;
    private widget.TextBox nmpoli;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.Table tbDiagnosa;
    private widget.Table tbDiagnosa1;
    private widget.Table tbDiagnosaPasien;
    private widget.Table tbDiagnosaPasien1;
    private widget.Table tbProsedur;
    private widget.Table tbTindakanPasien;
    private widget.Table tbTindakanPasien1;
    // End of variables declaration//GEN-END:variables

    public void tampilDiagStatistik() {
        Valid.tabelKosong(TabModeDiagnosaPasien);
        try {
            psdiagnosapasien = koneksi.prepareStatement("select reg_periksa.tgl_registrasi,diagnosa_pasien.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"
                    + "diagnosa_pasien.kd_penyakit,penyakit.nm_penyakit, diagnosa_pasien.status, if(diagnosa_pasien.prioritas='1','Primer','Sekunder') prior, "
                    + "if(diagnosa_pasien.nip_petugas='Admin Utama','Admin Utama',ifnull(pg.nama,'-')) nmPetugas from diagnosa_pasien "
                    + "inner join reg_periksa inner join pasien inner join penyakit "
                    + "on diagnosa_pasien.no_rawat=reg_periksa.no_rawat and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "and diagnosa_pasien.kd_penyakit=penyakit.kd_penyakit left join pegawai pg on pg.nik = diagnosa_pasien.nip_petugas "
                    + "where reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and reg_periksa.tgl_registrasi like ? or "
                    + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and diagnosa_pasien.no_rawat like ? or "
                    + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and reg_periksa.no_rkm_medis like ? or "
                    + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and pasien.nm_pasien like ? or "
                    + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and diagnosa_pasien.kd_penyakit like ? or "
                    + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and penyakit.nm_penyakit like ? or "
                    + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and diagnosa_pasien.status like ? "
                    + "order by reg_periksa.tgl_registrasi,diagnosa_pasien.prioritas ");
            try {
                psdiagnosapasien.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psdiagnosapasien.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psdiagnosapasien.setString(3, "%" + TCariPasien.getText().trim() + "%");
                psdiagnosapasien.setString(4, "%" + TCari.getText().trim() + "%");
                psdiagnosapasien.setString(5, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psdiagnosapasien.setString(6, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psdiagnosapasien.setString(7, "%" + TCariPasien.getText().trim() + "%");
                psdiagnosapasien.setString(8, "%" + TCari.getText().trim() + "%");
                psdiagnosapasien.setString(9, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psdiagnosapasien.setString(10, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psdiagnosapasien.setString(11, "%" + TCariPasien.getText().trim() + "%");
                psdiagnosapasien.setString(12, "%" + TCari.getText().trim() + "%");
                psdiagnosapasien.setString(13, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psdiagnosapasien.setString(14, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psdiagnosapasien.setString(15, "%" + TCariPasien.getText().trim() + "%");
                psdiagnosapasien.setString(16, "%" + TCari.getText().trim() + "%");
                psdiagnosapasien.setString(17, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psdiagnosapasien.setString(18, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psdiagnosapasien.setString(19, "%" + TCariPasien.getText().trim() + "%");
                psdiagnosapasien.setString(20, "%" + TCari.getText().trim() + "%");
                psdiagnosapasien.setString(21, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psdiagnosapasien.setString(22, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psdiagnosapasien.setString(23, "%" + TCariPasien.getText().trim() + "%");
                psdiagnosapasien.setString(24, "%" + TCari.getText().trim() + "%");
                psdiagnosapasien.setString(25, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psdiagnosapasien.setString(26, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psdiagnosapasien.setString(27, "%" + TCariPasien.getText().trim() + "%");
                psdiagnosapasien.setString(28, "%" + TCari.getText().trim() + "%");
                rs = psdiagnosapasien.executeQuery();
                while (rs.next()) {
                    TabModeDiagnosaPasien.addRow(new Object[]{
                        false, 
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9)                        
                    });
                }
                LCount.setText("" + TabModeDiagnosaPasien.getRowCount());
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (psdiagnosapasien != null) {
                    psdiagnosapasien.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    public void tampilDiagInadrg() {
        Valid.tabelKosong(tabMode1);
        try {
            psinadrg = koneksi.prepareStatement("select reg_periksa.tgl_registrasi,diagnosa_pasien_inadrg.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien, "
                    + "diagnosa_pasien_inadrg.kd_penyakit,penyakit.nm_penyakit, diagnosa_pasien_inadrg.status, if(diagnosa_pasien_inadrg.prioritas='1','Primer','Sekunder') prior, "
                    + "if(diagnosa_pasien_inadrg.nip_petugas='Admin Utama','Admin Utama',ifnull(pg.nama,'-')) nmPetugas from diagnosa_pasien_inadrg "
                    + "inner join reg_periksa inner join pasien inner join penyakit on diagnosa_pasien_inadrg.no_rawat=reg_periksa.no_rawat "
                    + "and reg_periksa.no_rkm_medis=pasien.no_rkm_medis and diagnosa_pasien_inadrg.kd_penyakit=penyakit.kd_penyakit "
                    + "left join pegawai pg on pg.nik = diagnosa_pasien_inadrg.nip_petugas where "
                    + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and reg_periksa.tgl_registrasi like ? or "
                    + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and diagnosa_pasien_inadrg.no_rawat like ? or "
                    + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and reg_periksa.no_rkm_medis like ? or "
                    + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and pasien.nm_pasien like ? or "
                    + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and diagnosa_pasien_inadrg.kd_penyakit like ? or "
                    + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and penyakit.nm_penyakit like ? or "
                    + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and diagnosa_pasien_inadrg.status like ? "
                    + "order by reg_periksa.tgl_registrasi,diagnosa_pasien_inadrg.prioritas");
            try {
                psinadrg.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psinadrg.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psinadrg.setString(3, "%" + TCariPasien.getText().trim() + "%");
                psinadrg.setString(4, "%" + TCari.getText().trim() + "%");
                psinadrg.setString(5, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psinadrg.setString(6, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psinadrg.setString(7, "%" + TCariPasien.getText().trim() + "%");
                psinadrg.setString(8, "%" + TCari.getText().trim() + "%");
                psinadrg.setString(9, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psinadrg.setString(10, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psinadrg.setString(11, "%" + TCariPasien.getText().trim() + "%");
                psinadrg.setString(12, "%" + TCari.getText().trim() + "%");
                psinadrg.setString(13, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psinadrg.setString(14, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psinadrg.setString(15, "%" + TCariPasien.getText().trim() + "%");
                psinadrg.setString(16, "%" + TCari.getText().trim() + "%");
                psinadrg.setString(17, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psinadrg.setString(18, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psinadrg.setString(19, "%" + TCariPasien.getText().trim() + "%");
                psinadrg.setString(20, "%" + TCari.getText().trim() + "%");
                psinadrg.setString(21, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psinadrg.setString(22, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psinadrg.setString(23, "%" + TCariPasien.getText().trim() + "%");
                psinadrg.setString(24, "%" + TCari.getText().trim() + "%");
                psinadrg.setString(25, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psinadrg.setString(26, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psinadrg.setString(27, "%" + TCariPasien.getText().trim() + "%");
                psinadrg.setString(28, "%" + TCari.getText().trim() + "%");
                rs = psinadrg.executeQuery();
                while (rs.next()) {
                    tabMode1.addRow(new Object[]{
                        false, 
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9)                        
                    });
                }
                
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (psinadrg != null) {
                    psinadrg.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    private void isRawat() {
        Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat=? ", TNoRM, TNoRw.getText());
    }

    private void isPsien() {
        Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis=? ", TPasien, TNoRM.getText());
    }

    private void getData() {
        if (tbDiagnosaPasien.getSelectedRow() != -1) {
            TNoRw.setText(tbDiagnosaPasien.getValueAt(tbDiagnosaPasien.getSelectedRow(), 2).toString());
            isRawat();
            isPsien();
            Status.setSelectedItem(tbDiagnosaPasien.getValueAt(tbDiagnosaPasien.getSelectedRow(), 7).toString());
        }
    }

    private void getData2() {
        if (tbTindakanPasien.getSelectedRow() != -1) {
            TNoRw.setText(tbTindakanPasien.getValueAt(tbTindakanPasien.getSelectedRow(), 2).toString());
            isRawat();
            isPsien();
            Status.setSelectedItem(tbTindakanPasien.getValueAt(tbTindakanPasien.getSelectedRow(), 7).toString());
        }
    }
    
    private void getData3() {
        if (tbDiagnosaPasien1.getSelectedRow() != -1) {
            TNoRw.setText(tbDiagnosaPasien1.getValueAt(tbDiagnosaPasien1.getSelectedRow(), 2).toString());
            isRawat();
            isPsien();
            Status.setSelectedItem(tbDiagnosaPasien1.getValueAt(tbDiagnosaPasien1.getSelectedRow(), 7).toString());
        }
    }
    
    private void getData4() {
        if (tbTindakanPasien1.getSelectedRow() != -1) {
            TNoRw.setText(tbTindakanPasien1.getValueAt(tbTindakanPasien1.getSelectedRow(), 2).toString());
            isRawat();
            isPsien();
            Status.setSelectedItem(tbTindakanPasien1.getValueAt(tbTindakanPasien1.getSelectedRow(), 7).toString());
        }
    }

    public void setNoRm(String norwt, Date tgl, String status) {
        TNoRw.setText(norwt);
        TCari.setText(norwt);
        Status.setSelectedItem(status);
        isRawat();
        isPsien();
        Valid.SetTgl(DTPCari1, Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + norwt + "'"));
        DTPCari2.setDate(tgl);
        ChkInput.setSelected(true);
        isForm();
        
        if (Status.getSelectedIndex() == 0) {
            cmbDiagPro.setSelectedIndex(1);
        } else {
            cmbDiagPro.setSelectedIndex(0);
        }
    }

    private void isForm() {
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 350));
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
        BtnSimpan.setEnabled(akses.getdiagnosa_pasien());
        BtnHapus.setEnabled(akses.getdiagnosa_pasien());
//        btnTambahPenyakit.setEnabled(var.getpenyakit());
        BtnPrint.setEnabled(akses.getdiagnosa_pasien());
        BtnKlaim.setEnabled(akses.getinacbg_klaim_raza());
    }

    private void tampildiagnosa() {
        try {
            jml = 0;
            for (i = 0; i < tbDiagnosa.getRowCount(); i++) {
                if (tbDiagnosa.getValueAt(i, 0).toString().equals("true")) {
                    jml++;
                }
            }

            pilih = null;
            pilih = new boolean[jml];
            kode = null;
            kode = new String[jml];
            nama = null;
            nama = new String[jml];
            ciripny = null;
            ciripny = new String[jml];
            keterangan = null;
            keterangan = new String[jml];
            kategori = null;
            kategori = new String[jml];
            cirium = null;
            cirium = new String[jml];

            index = 0;
            for (i = 0; i < tbDiagnosa.getRowCount(); i++) {
                if (tbDiagnosa.getValueAt(i, 0).toString().equals("true")) {
                    pilih[index] = true;
                    kode[index] = tbDiagnosa.getValueAt(i, 1).toString();
                    nama[index] = tbDiagnosa.getValueAt(i, 2).toString();
                    ciripny[index] = tbDiagnosa.getValueAt(i, 3).toString();
                    keterangan[index] = tbDiagnosa.getValueAt(i, 4).toString();
                    kategori[index] = tbDiagnosa.getValueAt(i, 5).toString();
                    cirium[index] = tbDiagnosa.getValueAt(i, 6).toString();
                    index++;
                }
            }

            Valid.tabelKosong(tabModeDiagnosa);
            for (i = 0; i < jml; i++) {
                tabModeDiagnosa.addRow(new Object[]{pilih[i], kode[i], nama[i], ciripny[i], keterangan[i], kategori[i], cirium[i]});
            }

            pspenyakit = koneksi.prepareStatement("select penyakit.kd_penyakit,penyakit.nm_penyakit,penyakit.ciri_ciri,penyakit.keterangan, "
                    + "kategori_penyakit.nm_kategori,kategori_penyakit.ciri_umum "
                    + "from kategori_penyakit inner join penyakit "
                    + "on penyakit.kd_ktg=kategori_penyakit.kd_ktg where  "
                    + " (penyakit.kd_penyakit like ? or "
                    + " penyakit.nm_penyakit like ? or "
                    + " penyakit.ciri_ciri like ? or "
                    + " penyakit.keterangan like ? or "
                    + " kategori_penyakit.nm_kategori like ? or "
                    + " kategori_penyakit.ciri_umum like ? ) and penyakit.kd_penyakit <> '-' "
                    + "order by penyakit.kd_penyakit  LIMIT 100");
            try {
                pspenyakit.setString(1, "%" + Diagnosa.getText().trim() + "%");
                pspenyakit.setString(2, "%" + Diagnosa.getText().trim() + "%");
                pspenyakit.setString(3, "%" + Diagnosa.getText().trim() + "%");
                pspenyakit.setString(4, "%" + Diagnosa.getText().trim() + "%");
                pspenyakit.setString(5, "%" + Diagnosa.getText().trim() + "%");
                pspenyakit.setString(6, "%" + Diagnosa.getText().trim() + "%");
                rs = pspenyakit.executeQuery();
                while (rs.next()) {
                    tabModeDiagnosa.addRow(new Object[]{false, rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)});
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (pspenyakit != null) {
                    pspenyakit.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    private void tampildiagnosaSekunder() {
        try {
            jml1 = 0;
            for (s = 0; s < tbDiagnosa1.getRowCount(); s++) {
                if (tbDiagnosa1.getValueAt(s, 0).toString().equals("true")) {
                    jml1++;
                }
            }

            pilih3 = null;
            pilih3 = new boolean[jml1];
            kode1 = null;
            kode1 = new String[jml1];
            nama1 = null;
            nama1 = new String[jml1];
            ciripny1 = null;
            ciripny1 = new String[jml1];
            keterangan1 = null;
            keterangan1 = new String[jml1];
            kategori1 = null;
            kategori1 = new String[jml1];
            cirium1 = null;
            cirium1 = new String[jml1];

            index1 = 0;
            for (s = 0; s < tbDiagnosa1.getRowCount(); s++) {
                if (tbDiagnosa1.getValueAt(s, 0).toString().equals("true")) {
                    pilih3[index1] = true;
                    kode1[index1] = tbDiagnosa1.getValueAt(s, 1).toString();
                    nama1[index1] = tbDiagnosa1.getValueAt(s, 2).toString();
                    ciripny1[index1] = tbDiagnosa1.getValueAt(s, 3).toString();
                    keterangan1[index1] = tbDiagnosa1.getValueAt(s, 4).toString();
                    kategori1[index1] = tbDiagnosa1.getValueAt(s, 5).toString();
                    cirium1[index1] = tbDiagnosa1.getValueAt(s, 6).toString();
                    index1++;
                }
            }

            Valid.tabelKosong(tabModeDiagnosaSekunder);
            for (s = 0; s < jml1; s++) {
                tabModeDiagnosaSekunder.addRow(new Object[]{pilih3[s], kode1[s], nama1[s], ciripny1[s], keterangan1[s], kategori1[s], cirium1[s]});
            }

            pspenyakitsekunder = koneksi.prepareStatement("select penyakit.kd_penyakit,penyakit.nm_penyakit,penyakit.ciri_ciri,penyakit.keterangan, "
                    + "kategori_penyakit.nm_kategori,kategori_penyakit.ciri_umum "
                    + "from kategori_penyakit inner join penyakit "
                    + "on penyakit.kd_ktg=kategori_penyakit.kd_ktg where  "
                    + " (penyakit.kd_penyakit like ? or "
                    + " penyakit.nm_penyakit like ? or "
                    + " penyakit.ciri_ciri like ? or "
                    + " penyakit.keterangan like ? or "
                    + " kategori_penyakit.nm_kategori like ? or "
                    + " kategori_penyakit.ciri_umum like ? ) and penyakit.kd_penyakit <> '-' "
                    + "order by penyakit.kd_penyakit LIMIT 100");
            try {
                pspenyakitsekunder.setString(1, "%" + Diagnosa1.getText().trim() + "%");
                pspenyakitsekunder.setString(2, "%" + Diagnosa1.getText().trim() + "%");
                pspenyakitsekunder.setString(3, "%" + Diagnosa1.getText().trim() + "%");
                pspenyakitsekunder.setString(4, "%" + Diagnosa1.getText().trim() + "%");
                pspenyakitsekunder.setString(5, "%" + Diagnosa1.getText().trim() + "%");
                pspenyakitsekunder.setString(6, "%" + Diagnosa1.getText().trim() + "%");
                rs1 = pspenyakitsekunder.executeQuery();
                while (rs1.next()) {
                    tabModeDiagnosaSekunder.addRow(new Object[]{false, rs1.getString(1),
                        rs1.getString(2),
                        rs1.getString(3),
                        rs1.getString(4),
                        rs1.getString(5),
                        rs1.getString(6)});
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs1 != null) {
                    rs1.close();
                }
                if (pspenyakitsekunder != null) {
                    pspenyakitsekunder.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    private void tampilprosedure() {
        try {
            jml = 0;
            for (i = 0; i < tbProsedur.getRowCount(); i++) {
                if (tbProsedur.getValueAt(i, 0).toString().equals("true")) {
                    jml++;
                }
            }

            pilih = null;
            pilih = new boolean[jml];
            kode2 = null;
            kode2 = new String[jml];
            panjang = null;
            panjang = new String[jml];
            pendek = null;
            pendek = new String[jml];

            index = 0;
            for (i = 0; i < tbProsedur.getRowCount(); i++) {
                if (tbProsedur.getValueAt(i, 0).toString().equals("true")) {
                    pilih[index] = true;
                    kode2[index] = tbProsedur.getValueAt(i, 1).toString();
                    panjang[index] = tbProsedur.getValueAt(i, 2).toString();
                    pendek[index] = tbProsedur.getValueAt(i, 3).toString();
                    index++;
                }
            }

            Valid.tabelKosong(tabModeProsedur);
            for (i = 0; i < jml; i++) {
                tabModeProsedur.addRow(new Object[]{pilih[i], kode2[i], panjang[i], pendek[i]});
            }

            psprosedur = koneksi.prepareStatement("select * from icd9 where kode like ? or "
                    + " deskripsi_panjang like ? or  deskripsi_pendek like ? order by kode");
            try {
                psprosedur.setString(1, "%" + Prosedur.getText().trim() + "%");
                psprosedur.setString(2, "%" + Prosedur.getText().trim() + "%");
                psprosedur.setString(3, "%" + Prosedur.getText().trim() + "%");
                rs = psprosedur.executeQuery();
                while (rs.next()) {
                    tabModeProsedur.addRow(new Object[]{
                        false, rs.getString(1), rs.getString(2), rs.getString(3)});
                }
            } catch (Exception ex) {
                System.out.println(ex);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (psprosedur != null) {
                    psprosedur.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void tampilProsStatistik() {
        Valid.tabelKosong(TabModeTindakanPasien);
        try {
            pstindakanpasien = koneksi.prepareStatement("select reg_periksa.tgl_registrasi,prosedur_pasien.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"
                    + "prosedur_pasien.kode,icd9.deskripsi_panjang, prosedur_pasien.status, if(prosedur_pasien.nip_petugas='Admin Utama','Admin Utama',ifnull(pg.nama,'-')) nmPetugas "
                    + "from prosedur_pasien inner join reg_periksa inner join pasien inner join icd9 "
                    + "on prosedur_pasien.no_rawat=reg_periksa.no_rawat and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "and prosedur_pasien.kode=icd9.kode left join pegawai pg on pg.nik = prosedur_pasien.nip_petugas "
                    + "where reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and reg_periksa.tgl_registrasi like ? or "
                    + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and prosedur_pasien.no_rawat like ? or "
                    + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and reg_periksa.no_rkm_medis like ? or "
                    + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and pasien.nm_pasien like ? or "
                    + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and prosedur_pasien.kode like ? or "
                    + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and icd9.deskripsi_panjang like ? or "
                    + "reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? and prosedur_pasien.status like ? "
                    + "order by reg_periksa.tgl_registrasi,prosedur_pasien.prioritas ");
            try {
                pstindakanpasien.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                pstindakanpasien.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                pstindakanpasien.setString(3, "%" + TCariPasien.getText().trim() + "%");
                pstindakanpasien.setString(4, "%" + TCari.getText().trim() + "%");
                pstindakanpasien.setString(5, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                pstindakanpasien.setString(6, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                pstindakanpasien.setString(7, "%" + TCariPasien.getText().trim() + "%");
                pstindakanpasien.setString(8, "%" + TCari.getText().trim() + "%");
                pstindakanpasien.setString(9, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                pstindakanpasien.setString(10, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                pstindakanpasien.setString(11, "%" + TCariPasien.getText().trim() + "%");
                pstindakanpasien.setString(12, "%" + TCari.getText().trim() + "%");
                pstindakanpasien.setString(13, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                pstindakanpasien.setString(14, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                pstindakanpasien.setString(15, "%" + TCariPasien.getText().trim() + "%");
                pstindakanpasien.setString(16, "%" + TCari.getText().trim() + "%");
                pstindakanpasien.setString(17, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                pstindakanpasien.setString(18, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                pstindakanpasien.setString(19, "%" + TCariPasien.getText().trim() + "%");
                pstindakanpasien.setString(20, "%" + TCari.getText().trim() + "%");
                pstindakanpasien.setString(21, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                pstindakanpasien.setString(22, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                pstindakanpasien.setString(23, "%" + TCariPasien.getText().trim() + "%");
                pstindakanpasien.setString(24, "%" + TCari.getText().trim() + "%");
                pstindakanpasien.setString(25, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                pstindakanpasien.setString(26, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                pstindakanpasien.setString(27, "%" + TCariPasien.getText().trim() + "%");
                pstindakanpasien.setString(28, "%" + TCari.getText().trim() + "%");
                rs = pstindakanpasien.executeQuery();
                while (rs.next()) {
                    TabModeTindakanPasien.addRow(new Object[]{
                        false, 
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8)                       
                    });
                }
                LCount.setText("" + TabModeTindakanPasien.getRowCount());
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (pstindakanpasien != null) {
                    pstindakanpasien.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    public void tampilProsInadrg() {
        Valid.tabelKosong(tabMode2);
        try {
            psTINinadrg = koneksi.prepareStatement("SELECT reg_periksa.tgl_registrasi,prosedur_pasien_inadrg.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien, "
                    + "prosedur_pasien_inadrg.kode,icd9.deskripsi_panjang, prosedur_pasien_inadrg.status, prosedur_pasien_inadrg.qty, "
                    + "if(prosedur_pasien_inadrg.nip_petugas='Admin Utama','Admin Utama',ifnull(pg.nama,'-')) nmPetugas "
                    + "FROM prosedur_pasien_inadrg INNER JOIN reg_periksa INNER JOIN pasien INNER JOIN icd9 "
                    + "ON prosedur_pasien_inadrg.no_rawat=reg_periksa.no_rawat AND reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "AND prosedur_pasien_inadrg.kode=icd9.kode left join pegawai pg on pg.nik = prosedur_pasien_inadrg.nip_petugas "
                    + "WHERE reg_periksa.tgl_registrasi BETWEEN ? AND ? AND reg_periksa.no_rkm_medis LIKE ? AND reg_periksa.tgl_registrasi LIKE ? OR "
                    + "reg_periksa.tgl_registrasi BETWEEN ? AND ? AND reg_periksa.no_rkm_medis LIKE ? AND prosedur_pasien_inadrg.no_rawat LIKE ? OR "
                    + "reg_periksa.tgl_registrasi BETWEEN ? AND ? AND reg_periksa.no_rkm_medis LIKE ? AND reg_periksa.no_rkm_medis LIKE ? OR "
                    + "reg_periksa.tgl_registrasi BETWEEN ? AND ? AND reg_periksa.no_rkm_medis LIKE ? AND pasien.nm_pasien LIKE ? OR "
                    + "reg_periksa.tgl_registrasi BETWEEN ? AND ? AND reg_periksa.no_rkm_medis LIKE ? AND prosedur_pasien_inadrg.kode LIKE ? OR "
                    + "reg_periksa.tgl_registrasi BETWEEN ? AND ? AND reg_periksa.no_rkm_medis LIKE ? AND icd9.deskripsi_panjang LIKE ? OR "
                    + "reg_periksa.tgl_registrasi BETWEEN ? AND ? AND reg_periksa.no_rkm_medis LIKE ? AND prosedur_pasien_inadrg.status LIKE ? "
                    + "ORDER BY reg_periksa.tgl_registrasi,prosedur_pasien_inadrg.prioritas");
            try {
                psTINinadrg.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psTINinadrg.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psTINinadrg.setString(3, "%" + TCariPasien.getText().trim() + "%");
                psTINinadrg.setString(4, "%" + TCari.getText().trim() + "%");
                psTINinadrg.setString(5, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psTINinadrg.setString(6, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psTINinadrg.setString(7, "%" + TCariPasien.getText().trim() + "%");
                psTINinadrg.setString(8, "%" + TCari.getText().trim() + "%");
                psTINinadrg.setString(9, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psTINinadrg.setString(10, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psTINinadrg.setString(11, "%" + TCariPasien.getText().trim() + "%");
                psTINinadrg.setString(12, "%" + TCari.getText().trim() + "%");
                psTINinadrg.setString(13, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psTINinadrg.setString(14, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psTINinadrg.setString(15, "%" + TCariPasien.getText().trim() + "%");
                psTINinadrg.setString(16, "%" + TCari.getText().trim() + "%");
                psTINinadrg.setString(17, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psTINinadrg.setString(18, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psTINinadrg.setString(19, "%" + TCariPasien.getText().trim() + "%");
                psTINinadrg.setString(20, "%" + TCari.getText().trim() + "%");
                psTINinadrg.setString(21, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psTINinadrg.setString(22, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psTINinadrg.setString(23, "%" + TCariPasien.getText().trim() + "%");
                psTINinadrg.setString(24, "%" + TCari.getText().trim() + "%");
                psTINinadrg.setString(25, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                psTINinadrg.setString(26, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                psTINinadrg.setString(27, "%" + TCariPasien.getText().trim() + "%");
                psTINinadrg.setString(28, "%" + TCari.getText().trim() + "%");
                rs = psTINinadrg.executeQuery();
                while (rs.next()) {
                    tabMode2.addRow(new Object[]{
                        false, 
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9)                        
                    });
                }
                
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (psTINinadrg != null) {
                    psTINinadrg.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void formulirKlaim() {
        tglklaim = "";
        drdpjp = "";
        poli = "";
        crBayar = "";
        
        try {            
            Sequel.queryu("delete from temporary_formulir_klaim");
            pspasien = koneksi.prepareStatement("SELECT rp.no_rawat, rp.no_rkm_medis, p.nm_pasien, IF (p.jk = 'L','LAKI-LAKI','PEREMPUAN') jk, "
                    + "IF (rp.status_lanjut = 'Ralan','RAWAT JALAN','-') status_kunjungan, DATE_FORMAT(p.tgl_lahir,'%d') tgl_lhr, "
                    + "CONCAT(rp.umurdaftar,' ',rp.sttsumur,'.') umur, DATE_FORMAT(rp.tgl_registrasi,'%d') tgl_kunj, d.nm_dokter, pl.nm_poli, "
                    + "pj.png_jawab, IFNULL(pr.diagnosa,'-') diag_resum, IFNULL(pr.keluhan,'-') keluhan, IFNULL(pr.pemeriksaan,'-') pemeriksaan, "
                    + "IFNULL(pr.alergi,'-') alergi, IFNULL(pr.terapi,'-') terapi, IFNULL(pr.rincian_tindakan,'-') tindakan FROM reg_periksa rp INNER JOIN pasien p ON rp.no_rkm_medis = p.no_rkm_medis "
                    + "INNER JOIN dokter d ON rp.kd_dokter = d.kd_dokter INNER JOIN poliklinik pl ON rp.kd_poli = pl.kd_poli "
                    + "INNER JOIN penjab pj ON rp.kd_pj = pj.kd_pj LEFT JOIN pemeriksaan_ralan pr ON rp.no_rawat = pr.no_rawat "
                    + "WHERE rp.status_lanjut = 'Ralan' AND rp.no_rawat ='" + TNoRw.getText().trim() + "'");

            try {
                rspasien = pspasien.executeQuery();
                while (rspasien.next()) {
                    tglklaim = rspasien.getString("tgl_kunj") + " "+ Sequel.bulanINDONESIA("select month(tgl_registrasi) from reg_periksa where no_rawat='" + rspasien.getString("no_rawat") + "'") + " "
                            + Sequel.cariIsi("select year(tgl_registrasi) from reg_periksa where no_rawat='" + rspasien.getString("no_rawat") + "'");
                    drdpjp = rspasien.getString("nm_dokter");
                    poli = rspasien.getString("nm_poli");
                    crBayar = rspasien.getString("png_jawab");

                    Sequel.menyimpan("temporary_formulir_klaim", "'Kode RS',': 6303015','Nama RS',': RSUD Ratu Zalecha Martapura',"
                            + "'1. No. RM',': " + rspasien.getString("no_rkm_medis") + "',"
                            + "'2. Nama Pasien',': " + rspasien.getString("nm_pasien") + "',"
                            + "'3. Jenis Kelamin',': " + rspasien.getString("jk") + "',"
                            + "'4. Jenis Perawatan',': " + rspasien.getString("status_kunjungan") + "',"
                            + "'5. Tgl. Lahir',': " + rspasien.getString("tgl_lhr") + " " 
                            + Sequel.bulanINDONESIA("select month(tgl_lahir) from pasien where no_rkm_medis='" + rspasien.getString("no_rkm_medis") + "'") + " "
                            + Sequel.cariIsi("select year(tgl_lahir) from pasien where no_rkm_medis='" + rspasien.getString("no_rkm_medis") + "'") + "',"
                            + "'6. Umur',': " + rspasien.getString("umur") + "',"
                            + "'7. Tgl. Kunjungan',': " + rspasien.getString("tgl_kunj") + " "
                            + Sequel.bulanINDONESIA("select month(tgl_registrasi) from reg_periksa where no_rawat='" + rspasien.getString("no_rawat") + "'") + " "
                            + Sequel.cariIsi("select year(tgl_registrasi) from reg_periksa where no_rawat='" + rspasien.getString("no_rawat") + "'") + "',"
                            + "'8. Resume Medis',':','','','','','','','','','','','','','','','','',''", "Formulir Klaim Rawat Jalan");
                    
                    Sequel.menyimpan("temporary_formulir_klaim", "'','','','','','','','','','','','','','','','','','',"
                            + "'      Keluhan',':','" + rspasien.getString("keluhan") + "',"
                            + "'','','','','','','','','','','','','','','',''", "Formulir Klaim Rawat Jalan");
                    
                    Sequel.menyimpan("temporary_formulir_klaim", "'','','','','','','','','','','','','','','','','','',"
                            + "'      Pemeriksaan',':','" + rspasien.getString("pemeriksaan") + "',"
                            + "'','','','','','','','','','','','','','','',''", "Formulir Klaim Rawat Jalan");

                    Sequel.menyimpan("temporary_formulir_klaim", "'','','','','','','','','','','','','','','','','','',"
                            + "'      Alergi',':','" + rspasien.getString("alergi") + "',"
                            + "'','','','','','','','','','','','','','','',''", "Formulir Klaim Rawat Jalan");
                    
                    Sequel.menyimpan("temporary_formulir_klaim", "'','','','','','','','','','','','','','','','','','',"
                            + "'      Terapi',':','" + rspasien.getString("terapi") + "',"
                            + "'','','','','','','','','','','','','','','',''", "Formulir Klaim Rawat Jalan");
                    
                    Sequel.menyimpan("temporary_formulir_klaim", "'','','','','','','','','','','','','','','','','','',"
                            + "'      Rincian Tindakan',':','" + rspasien.getString("tindakan") + "',"
                            + "'','','','','','','','','','','','','','','',''", "Formulir Klaim Rawat Jalan");
                    
                    Sequel.menyimpan("temporary_formulir_klaim", "'','','','','','','','','','','','','','','','','','',"
                            + "'      Diagnosa Resume',':','" + rspasien.getString("diag_resum") + "',"
                            + "'','','','','','','','','','','','','','','',''", "Formulir Klaim Rawat Jalan");

                    //ngambil data diagnosa icd 10     
                    Sequel.menyimpan("temporary_formulir_klaim", "'','','','','','','','','','','','','','','','','','',"
                            + "'9. Diagnosa ICD-10',':','','','','','','','','','','','','','','','','',''", "Formulir Klaim Rawat Jalan");
                    psdiagnosa = koneksi.prepareStatement("SELECT IF (d.prioritas = '1','Primer','Sekunder') stts_diagnosa, IFNULL(d.kd_penyakit,'-') AS ICD_10, "
                            + "IFNULL(p.ciri_ciri, '-') deskripsi_diagnosa FROM diagnosa_pasien d "
                            + "INNER JOIN reg_periksa r ON r.no_rawat=d.no_rawat INNER JOIN penyakit p ON p.kd_penyakit=d.kd_penyakit "
                            + "WHERE d.status='ralan' and r.no_rawat='" + TNoRw.getText().trim() + "' order by d.prioritas");
                    try {
                        rsdiagnosa = psdiagnosa.executeQuery();
                        while (rsdiagnosa.next()) {
                            Sequel.menyimpan("temporary_formulir_klaim", "'','','','','','','','','','','','','','','','','','',"
                                    + "'      " + rsdiagnosa.getString("stts_diagnosa") + "',':','" + rsdiagnosa.getString("deskripsi_diagnosa") + "','Kode : " + rsdiagnosa.getString("ICD_10") + "',"
                                    + "'','','','','','','','','','','','','','',''", "Diagnosa Klaim Rawat Jalan");
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi Formulir Klaim : " + e);
                    } finally {
                        if (rsdiagnosa != null) {
                            rsdiagnosa.close();
                        }
                        if (psdiagnosa != null) {
                            psdiagnosa.close();
                        }
                    }
                    
                    Sequel.menyimpan("temporary_formulir_klaim", "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Formulir Klaim Rawat Jalan");
                    Sequel.menyimpan("temporary_formulir_klaim", "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Formulir Klaim Rawat Jalan");

                    //ngambil data tindakan icd 9
                    Sequel.menyimpan("temporary_formulir_klaim", "'','','','','','','','','','','','','','','','','','',"
                            + "'10. Tindakan ICD-9-CM',':','','','','','','','','','','','','','','','','',''", "Formulir Klaim Rawat Jalan");
                    pstindakan = koneksi.prepareStatement(
                            "SELECT IFNULL(pp.kode,'-') ICD_9, IFNULL(i.deskripsi_panjang,'-') des_prosedur "
                            + "FROM reg_periksa rp INNER JOIN prosedur_pasien pp on pp.no_rawat=rp.no_rawat INNER JOIN icd9 i on i.kode=pp.kode "
                            + "WHERE rp.status_lanjut = 'Ralan' AND rp.no_rawat ='" + TNoRw.getText().trim() + "'");
                    try {
                        rstindakan = pstindakan.executeQuery();
                        while (rstindakan.next()) {
                            Sequel.menyimpan("temporary_formulir_klaim", "'','','','','','','','','','','','','','','','','','',"
                                    + "'      Deskripsi',':','" + rstindakan.getString("des_prosedur") + "','Kode : " + rstindakan.getString("ICD_9") + "',"
                                    + "'','','','','','','','','','','','','','',''", "Tindakan Klaim Rawat Jalan");
                        }
                    } catch (Exception e) {
                        System.out.println("Notif Registrasi : " + e);
                    } finally {
                        if (rstindakan != null) {
                            rstindakan.close();
                        }
                        if (pstindakan != null) {
                            pstindakan.close();
                        }
                    }
                    
                    Sequel.menyimpan("temporary_formulir_klaim", "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Formulir Klaim Rawat Jalan");
                    Sequel.menyimpan("temporary_formulir_klaim", "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Formulir Klaim Rawat Jalan");
                }
            } catch (Exception e) {
                System.out.println("Notifikasi Cari Pasien : " + e);
            } finally {
                if (rspasien != null) {
                    rspasien.close();
                }
                if (pspasien != null) {
                    pspasien.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("tglKlaim", "Martapura, " + tglklaim);
        param.put("drDPJP", "( " + drdpjp + " )");
        param.put("poli", poli);
        param.put("caraBayar", crBayar);
        Valid.MyReport("rptFormulirKlaim.jasper", "report", "::[ Lembar Formulir Klaim Pasien Rawat Jalan ]::",
                "select * from temporary_formulir_klaim", param);
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    private void simpan_diagproStatistik() {
        try {
            cek = 0;
            cekPremier = 0;
            koneksi.setAutoCommit(false);
            cek = Sequel.cariInteger("select count(-1) from diagnosa_pasien where no_rawat='" + TNoRw.getText() + "' and prioritas=1");
            if (cek > 0) {
                for (i = 0; i < tbDiagnosa.getRowCount(); i++) {
                    if (tbDiagnosa.getValueAt(i, 0).toString().equals("true")) {
                        JOptionPane.showMessageDialog(null, "Diagnosa primer sudah tersimpan sebelumnya...");
                    }
                }
                for (i = 0; i < tbDiagnosa1.getRowCount(); i++) {
                    if (tbDiagnosa1.getValueAt(i, 0).toString().equals("true")) {
                        Sequel.menyimpan("diagnosa_pasien", "?,?,?,?,?", "Penyakit", 5, new String[]{
                            TNoRw.getText(), tbDiagnosa1.getValueAt(i, 1).toString(), Status.getSelectedItem().toString(), 
                            Sequel.cariIsi("select ifnull(MAX(prioritas)+1,1) from diagnosa_pasien where no_rawat=? and status='" + Status.getSelectedItem().toString() + "'", TNoRw.getText()), 
                            akses.getkode()
                        });
                    }
                }
            } else {
                for (i = 0; i < tbDiagnosa.getRowCount(); i++) {
                    if (tbDiagnosa.getValueAt(i, 0).toString().equals("true")) {
                        cekPremier++;
                    }
                }

                if (cekPremier > 1) {
                    JOptionPane.showMessageDialog(null, "Diagnosa primer hanya boleh ada 1 saja,...");
                } else {
                    for (i = 0; i < tbDiagnosa.getRowCount(); i++) {
                        if (tbDiagnosa.getValueAt(i, 0).toString().equals("true")) {
                            Sequel.menyimpan("diagnosa_pasien", "?,?,?,?,?", "Penyakit", 5, new String[]{
                                TNoRw.getText(), tbDiagnosa.getValueAt(i, 1).toString(), Status.getSelectedItem().toString(), "1", akses.getkode()
                            });
                        }
                    }
                }

                if (cekPremier == 1) {
                    for (i = 0; i < tbDiagnosa1.getRowCount(); i++) {
                        if (tbDiagnosa1.getValueAt(i, 0).toString().equals("true")) {
                            Sequel.menyimpan("diagnosa_pasien", "?,?,?,?,?", "Penyakit", 5, new String[]{
                                TNoRw.getText(), tbDiagnosa1.getValueAt(i, 1).toString(), Status.getSelectedItem().toString(), 
                                Sequel.cariIsi("select ifnull(MAX(prioritas)+1,1) from diagnosa_pasien where no_rawat=? and status='" + Status.getSelectedItem().toString() + "'", TNoRw.getText()), 
                                akses.getkode()
                            });
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Silakan input diagnosa primer terlebih dulu...");
                }
            }

            koneksi.setAutoCommit(true);            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data. Kemungkinan ada data diagnosa yang sama dimasukkan sebelumnya...!");
        }

        //---------------------------------
        try {
            koneksi.setAutoCommit(false);
            for (i = 0; i < tbProsedur.getRowCount(); i++) {
                if (tbProsedur.getValueAt(i, 0).toString().equals("true")) {
                    Sequel.menyimpan("prosedur_pasien", "?,?,?,?,?", "ICD 9", 5, new String[]{
                        TNoRw.getText(), tbProsedur.getValueAt(i, 1).toString(), Status.getSelectedItem().toString(), 
                        Sequel.cariIsi("select ifnull(MAX(prioritas)+1,1) from prosedur_pasien where no_rawat=? and status='" + Status.getSelectedItem().toString() + "'", TNoRw.getText()), 
                        akses.getkode()
                    });
                }
            }
            koneksi.setAutoCommit(true);            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data. Kemungkinan ada data prosedur/ICD9 yang sama dimasukkan sebelumnya...!");
        }        
    }
    
    private void simpan_diagproINADRG() {
        try {
            cekINADRG = 0;
            cekPremierINADRG = 0;
            koneksi.setAutoCommit(false);
            cekINADRG = Sequel.cariInteger("select count(-1) from diagnosa_pasien_inadrg where no_rawat='" + TNoRw.getText() + "' and prioritas=1");
            if (cekINADRG > 0) {
                for (i = 0; i < tbDiagnosa.getRowCount(); i++) {
                    if (tbDiagnosa.getValueAt(i, 0).toString().equals("true")) {
                        JOptionPane.showMessageDialog(null, "Diagnosa primer INADRG sudah tersimpan sebelumnya...");
                    }
                }
                for (i = 0; i < tbDiagnosa1.getRowCount(); i++) {
                    if (tbDiagnosa1.getValueAt(i, 0).toString().equals("true")) {
                        Sequel.menyimpan("diagnosa_pasien_inadrg", "?,?,?,?,?", "Penyakit", 5, new String[]{
                            TNoRw.getText(), tbDiagnosa1.getValueAt(i, 1).toString(), Status.getSelectedItem().toString(), 
                            Sequel.cariIsi("select ifnull(MAX(prioritas)+1,1) from diagnosa_pasien_inadrg where no_rawat=? and status='" + Status.getSelectedItem().toString() + "'", TNoRw.getText()),
                            akses.getkode()
                        });
                    }
                }
            } else {
                for (i = 0; i < tbDiagnosa.getRowCount(); i++) {
                    if (tbDiagnosa.getValueAt(i, 0).toString().equals("true")) {
                        cekPremierINADRG++;
                    }
                }

                if (cekPremierINADRG > 1) {
                    JOptionPane.showMessageDialog(null, "Diagnosa primer INADRG hanya boleh ada 1 saja,...");
                } else {
                    for (i = 0; i < tbDiagnosa.getRowCount(); i++) {
                        if (tbDiagnosa.getValueAt(i, 0).toString().equals("true")) {
                            Sequel.menyimpan("diagnosa_pasien_inadrg", "?,?,?,?,?", "Penyakit", 5, new String[]{
                                TNoRw.getText(), tbDiagnosa.getValueAt(i, 1).toString(), Status.getSelectedItem().toString(), "1", akses.getkode()
                            });
                        }
                    }
                }

                if (cekPremierINADRG == 1) {
                    for (i = 0; i < tbDiagnosa1.getRowCount(); i++) {
                        if (tbDiagnosa1.getValueAt(i, 0).toString().equals("true")) {
                            Sequel.menyimpan("diagnosa_pasien_inadrg", "?,?,?,?,?", "Penyakit", 5, new String[]{
                                TNoRw.getText(), tbDiagnosa1.getValueAt(i, 1).toString(), Status.getSelectedItem().toString(), 
                                Sequel.cariIsi("select ifnull(MAX(prioritas)+1,1) from diagnosa_pasien_inadrg where no_rawat=? and status='" + Status.getSelectedItem().toString() + "'", TNoRw.getText()),
                                akses.getkode()
                            });
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Silakan input diagnosa primer INADRG terlebih dulu...");
                }
            }

            koneksi.setAutoCommit(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data. Kemungkinan ada data diagnosa INADRG yang sama dimasukkan sebelumnya...!");
        }

        //---------------------------------
        try {
            koneksi.setAutoCommit(false);
            for (i = 0; i < tbProsedur.getRowCount(); i++) {
                if (tbProsedur.getValueAt(i, 0).toString().equals("true")) {
                    Sequel.menyimpan("prosedur_pasien_inadrg", "?,?,?,?,?,?", "ICD 9", 6, new String[]{
                        TNoRw.getText(), tbProsedur.getValueAt(i, 1).toString(), Status.getSelectedItem().toString(), 
                        Sequel.cariIsi("select ifnull(MAX(prioritas)+1,1) from prosedur_pasien_inadrg where no_rawat=? and status='" + Status.getSelectedItem().toString() + "'", TNoRw.getText()), 
                        "1", akses.getkode()
                    });
                }
            }
            koneksi.setAutoCommit(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data. Kemungkinan ada data prosedur/ICD9 INADRG yang sama dimasukkan sebelumnya...!");
        }
    }
    
    private void hapus_diagproStatistik() {
        if (TabRawat.getSelectedIndex() == 0) {
            if (TabModeDiagnosaPasien.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
                TNoRw.requestFocus();
            } else {
                for (i = 0; i < tbDiagnosaPasien.getRowCount(); i++) {
                    if (tbDiagnosaPasien.getValueAt(i, 0).toString().equals("true")) {
                        Sequel.queryu2("delete from diagnosa_pasien where no_rawat=? and kd_penyakit=?", 2, new String[]{
                            tbDiagnosaPasien.getValueAt(i, 2).toString(), tbDiagnosaPasien.getValueAt(i, 5).toString()
                        });
                    }
                }
            }
        } else if (TabRawat.getSelectedIndex() == 1) {
            if (TabModeTindakanPasien.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
                TNoRw.requestFocus();
            } else {
                for (i = 0; i < tbTindakanPasien.getRowCount(); i++) {
                    if (tbTindakanPasien.getValueAt(i, 0).toString().equals("true")) {
                        Sequel.queryu2("delete from prosedur_pasien where no_rawat=? and kode=?", 2, new String[]{
                            tbTindakanPasien.getValueAt(i, 2).toString(), tbTindakanPasien.getValueAt(i, 5).toString()
                        });
                    }
                }
            }
        }
    }
    
    private void hapus_diagproINADRG() {
        if (TabRawat.getSelectedIndex() == 0) {
            if (tabMode1.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, data diagnosa INADRG sudah habis...!!!!");
                TNoRw.requestFocus();
            } else {
                for (i = 0; i < tbDiagnosaPasien1.getRowCount(); i++) {
                    if (tbDiagnosaPasien1.getValueAt(i, 0).toString().equals("true")) {
                        Sequel.queryu2("delete from diagnosa_pasien_inadrg where no_rawat=? and kd_penyakit=?", 2, new String[]{
                            tbDiagnosaPasien1.getValueAt(i, 2).toString(), tbDiagnosaPasien1.getValueAt(i, 5).toString()
                        });
                    }
                }
            }
        } else if (TabRawat.getSelectedIndex() == 1) {
            if (tabMode2.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, data prosedur/tindakan/ICD 9 INADRG sudah habis...!!!!");
                TNoRw.requestFocus();
            } else {
                for (i = 0; i < tbTindakanPasien1.getRowCount(); i++) {
                    if (tbTindakanPasien1.getValueAt(i, 0).toString().equals("true")) {
                        Sequel.queryu2("delete from prosedur_pasien_inadrg where no_rawat=? and kode=?", 2, new String[]{
                            tbTindakanPasien1.getValueAt(i, 2).toString(), tbTindakanPasien1.getValueAt(i, 5).toString()
                        });
                    }
                }
            }
        }
    }   
    
    private void simpanQTYprosedurINADRG() {
        jlhTindakan = ""; 

        for (i = 0; i < tbTindakanPasien1.getRowCount(); i++) {
            if (tbTindakanPasien1.getValueAt(i, 0).toString().equals("true")) {                
                if (!tbTindakanPasien1.getValueAt(i, 8).toString().equals("") && Integer.parseInt(tbTindakanPasien1.getValueAt(i, 8).toString()) > 1) {
                    jlhTindakan = tbTindakanPasien1.getValueAt(i, 8).toString();
                    Sequel.mengedit("prosedur_pasien_inadrg",
                            "no_rawat='" + TNoRw.getText() + "' and kode='" + tbTindakanPasien1.getValueAt(i, 5).toString() + "' and status='" + tbTindakanPasien1.getValueAt(i, 7).toString() + "'",
                            "qty='" + jlhTindakan + "'");
                } else if (Integer.parseInt(tbTindakanPasien1.getValueAt(i, 8).toString()) == 1) {
                    jlhTindakan = tbTindakanPasien1.getValueAt(i, 8).toString();
                    Sequel.mengedit("prosedur_pasien_inadrg",
                            "no_rawat='" + TNoRw.getText() + "' and kode='" + tbTindakanPasien1.getValueAt(i, 5).toString() + "' and status='" + tbTindakanPasien1.getValueAt(i, 7).toString() + "'",
                            "qty='" + jlhTindakan + "'");
                }          
            }
        }
    }
    
    private void tampilPemeriksaan() {
        try {
            psralan = koneksi.prepareStatement("SELECT IFNULL(pr1.diagnosa, '-') diag_resum_dr, IFNULL(pr2.diagnosa, '-') diag_resum_pr, "
                    + "IFNULL(pr1.keluhan, '-') keluhan_dr, IFNULL(pr2.keluhan, '-') keluhan_pr, IFNULL(pr1.rincian_tindakan, '-') tindakan_dr, "
                    + "IFNULL(pr2.rincian_tindakan, '-') tindakan_pr FROM reg_periksa rp "
                    + "LEFT JOIN pemeriksaan_ralan pr1 ON pr1.no_rawat = rp.no_rawat "
                    + "LEFT JOIN pemeriksaan_ralan_petugas pr2 ON pr2.no_rawat = rp.no_rawat "
                    + "WHERE rp.status_lanjut = 'Ralan' AND rp.kd_poli NOT IN ('laa', 'lab', 'rad', '-') AND rp.no_rawat = '" + TNoRw.getText() + "'");
            try {
                rsralan = psralan.executeQuery();
                while (rsralan.next()) {
                    TDiagDokter.setText(rsralan.getString("diag_resum_dr"));
                    TDiagPerawat.setText(rsralan.getString("diag_resum_pr"));
                    TKeluhan1.setText(rsralan.getString("keluhan_dr"));
                    TKeluhan2.setText(rsralan.getString("keluhan_pr"));                    
                    TRincianTindakan1.setText(rsralan.getString("tindakan_dr"));
                    TRincianTindakan2.setText(rsralan.getString("tindakan_pr"));
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsralan != null) {
                    rsralan.close();
                }
                if (psralan != null) {
                    psralan.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilHasilRadiologi() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            StringBuilder htmlContent = new StringBuilder();
            try {
                rsrad = koneksi.prepareStatement("SELECT rp.no_rawat, concat(date_format(rp.tgl_registrasi, '%d-%m-%Y'),', Jam : ',date_format(rp.jam_reg,'%H:%i %p')) tglKun, "
                        + "if(rp.status_lanjut='ralan', concat(ifnull(pl.nm_poli,'-'),' (',ifnull(d.nm_dokter,'-'),')'), "
                        + "CONCAT((SELECT l.nm_bangsal FROM kamar_inap k INNER JOIN kamar b ON b.kd_kamar=k.kd_kamar INNER JOIN bangsal l ON l.kd_bangsal=b.kd_bangsal "
                        + "WHERE k.no_rawat=rp.no_rawat AND k.stts_pulang<>'Pindah Kamar'),' (',dd.nm_dokter,')')) polidokter, concat(ifnull(pj.png_jawab,'-')) crbyr, "
                        + "if(rp.status_lanjut='ralan','Rawat Jalan','Rawat Inap') stts_rawat FROM reg_periksa rp "
                        + "INNER JOIN poliklinik pl on pl.kd_poli=rp.kd_poli INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj "
                        + "INNER JOIN dokter d on d.kd_dokter=rp.kd_dokter INNER JOIN periksa_radiologi pr on pr.no_rawat=rp.no_rawat INNER JOIN dokter dd ON dd.kd_dokter=pr.dokter_perujuk "
                        + "LEFT JOIN hasil_radiologi hr on hr.no_rawat=pr.no_rawat and hr.kd_jenis_prw=pr.kd_jenis_prw AND hr.tgl_periksa=pr.tgl_periksa AND hr.jam=pr.jam WHERE "
                        + "rp.no_rkm_medis = '" + TNoRM.getText() + "' AND pr.tgl_periksa BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                        + "GROUP BY rp.no_rawat ORDER BY pr.tgl_periksa, pr.jam").executeQuery();

                urut = 1;
                while (rsrad.next()) {
                    htmlContent.append(                            
                            "<table width='100%' class='isi'>"
                            + "<thead>"
                            + "<tr class='isi'>"
                            + "    <td valign='top' rowspan='5' width='20px'>" + urut + ".</td>"
                            + "    <td width='160px'>No. Rawat</td>"
                            + "    <td colspan='4'>: " + rsrad.getString("no_rawat") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "    <td>Tgl. Kunj./Tgl. MRS</td>"
                            + "    <td colspan='4'>: " + rsrad.getString("tglKun") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "    <td>Jenis Perawatan</td>"
                            + "    <td colspan='4'>: " + rsrad.getString("stts_rawat") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "    <td>Poliklinik/Inst./Rg. Rawat</td>"
                            + "    <td colspan='4'>: " + rsrad.getString("polidokter") + "</td>"
                            + "</tr>"
                            +"<tr class='isi'>"
                            + "    <td>Cara Bayar</td>"
                            + "    <td colspan='4'>: " + rsrad.getString("crbyr") + "</td>"
                            + "</tr>"
                            + "</thead>"       
                            + "</table>"
                    );
                    urut++;
                    
                    //hasil pemeriksaan radiolgi
                    try {
                        rshslRad = koneksi.prepareStatement("SELECT date_format(pr.tgl_periksa, '%d-%m-%Y') tgl_periksa, date_format(pr.jam, '%h:%i %p') jam, "
                                + "ifnull(jpr.nm_perawatan,'-') nm_pemeriksaan, ifnull(d.nm_dokter,'-') drRad, ifnull(hr.hasil, '-') hasil FROM periksa_radiologi pr "
                                + "INNER JOIN dokter d on d.kd_dokter=pr.kd_dokter INNER JOIN jns_perawatan_radiologi jpr on jpr.kd_jenis_prw=pr.kd_jenis_prw "
                                + "LEFT JOIN hasil_radiologi hr on hr.no_rawat=pr.no_rawat and hr.kd_jenis_prw=pr.kd_jenis_prw AND hr.tgl_periksa=pr.tgl_periksa AND hr.jam=pr.jam "
                                + "WHERE pr.no_rawat='" + rsrad.getString("no_rawat") + "' ORDER BY pr.tgl_periksa, pr.jam").executeQuery();

                        if (rshslRad.next()) {
                            htmlContent.append(
                                    "<table width='100%' class='isi'>"
                                    + "<thead>"
                                    + "<tr class='isi'>"
                                    + "    <td width='20px'></td>"
                                    + "    <td width='100px' bgcolor='#f8fdf3'><b>Tgl. Pemeriksaan</b></td>"
                                    + "    <td width='50px' bgcolor='#f8fdf3'><b>Jam</b></td>"                                    
                                    + "    <td width='150px' bgcolor='#f8fdf3'><b>Nama Pemeriksaan</b></td>"
                                    + "    <td width='180px' bgcolor='#f8fdf3'><b>Dokter Radiologi</b></td>"
                                    + "    <td bgcolor='#f8fdf3'><b>Bacaan/Hasil Pemeriksaan</b></td>"
                                    + "  </tr>"
                                    + "</thead>"
                                    + "<tbody>"
                            );
                            
                            rshslRad.beforeFirst();
                            while (rshslRad.next()) {
                                htmlContent.append(
                                        "<tr class='isi'>"
                                        + "<td width='20px'></td>"
                                        + "<td valign='top' width='100px'>" + rshslRad.getString("tgl_periksa") + "</td>"
                                        + "<td valign='top' width='50px'>" + rshslRad.getString("jam") + "</td>"                                        
                                        + "<td valign='top' width='150px'>" + rshslRad.getString("nm_pemeriksaan") + "</td>"
                                        + "<td valign='top' width='180px'>" + rshslRad.getString("drRad") + "</td>"
                                        + "<td valign='top'>" + rshslRad.getString("hasil").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                        + "</tr>"                                        
                                );
                            }
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td colspan='6' bgcolor='#7eccb9'></td>"
                                    + "</tr>"
                                    + "</tbody>"                                          
                                    + "</table>"
                            );
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rshslRad != null) {
                            rshslRad.close();
                        }
                    }
                }
                
                LoadHTML1.setText(
                        "<html>"
                        + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                        + htmlContent.toString()
                        + "</table>"
                        + "</html>");
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsrad != null) {
                    rsrad.close();
                }
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    private void tampilHasilLaboratorium() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            StringBuilder htmlContent = new StringBuilder();
            rs = koneksi.prepareStatement("select * from ("
                    + "(select *, waktu_reg_lab 'waktu' ,date_format(waktu_reg_lab,'%d-%m-%Y') tgl, time_format(waktu_reg_lab,'%H:%i') jam, date_format(pasien_tgl_lahir,'%d-%m-%Y') tgllhr "
                    + "from lis_hasil_data_pasien where pasien_no_rm='" + TNoRM.getText() + "' and date(waktu_reg_lab) between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' "
                    + "and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' ORDER BY waktu_reg_lab desc, no_lab desc) as a ) order by a.waktu").executeQuery();

            try {
                urut = 1;
                while (rs.next()) {
                    htmlContent.append(
                            "<tr class='isi'>"
                            + "<td valign='top' width='20%'>&nbsp;" + urut + ". No. RM</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("pasien_no_rm") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tgl. Lahir</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("tgllhr") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Cara Bayar/Penjamin</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("penjamin") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Rg. Rawat/Poli./Inst.</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("unit_asal") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Dokter Pengirim</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("dokter_pengirim") + "</td>"
                            + "</tr>"
                    );
                    urut++;

                    //hasil pemeriksaan laboratorium LIS
                    try {
                        rsLISMaster = koneksi.prepareStatement(
                                "select *, date_format(waktu_reg_lab,'%d-%m-%Y') tgl, time_format(waktu_reg_lab,'%H:%i') jam from lis_hasil_data_pasien where "
                                + "no_lab='" + rs.getString("no_lab") + "' and date(waktu_reg_lab) between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' "
                                + "and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' ORDER BY waktu_reg_lab desc, no_lab desc").executeQuery();

                        if (rsLISMaster.next()) {
                            rsLISMaster.beforeFirst();
                            lisM = 1;
                            while (rsLISMaster.next()) {
                                htmlContent.append(
                                        "<tr class='isi'>"
                                        + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Hasil Pemeriksaan Laboratorium</td>"
                                        + "<td valign='top' width='1%' align='center'>:</td>"
                                        + "<td valign='top' width='79%'>"
                                        + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                        + "<tr><td valign='top' colspan='6'>No. Lab. : " + rsLISMaster.getString("no_lab") + ", Tgl. Periksa : " + rsLISMaster.getString("tgl") + ", Jam : " + rsLISMaster.getString("jam") + "</td></td></tr>"
                                        + "<tr align='center'>"
                                        + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Jenis Pemeriksaan/Item</td>"
                                        + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Metode Pemeriksaan</td>"
                                        + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Nilai Hasil</td>"
                                        + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Nilai Rujukan</td>"
                                        + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Satuan</td>"
                                        + "<td valign='top' width='6%' bgcolor='#f8fdf3'>Flag Kode</td>"
                                        + "</tr>"
                                );

                                rsLIS1 = koneksi.prepareStatement(
                                        "SELECT ifnull(kategori_pemeriksaan_nama,'') kategori_pemeriksaan_nama FROM lis_hasil_periksa_lab "
                                        + "WHERE no_lab ='" + rsLISMaster.getString("no_lab") + "' GROUP BY kategori_pemeriksaan_nama "
                                        + "ORDER BY kategori_pemeriksaan_no_urut, sub_kategori_pemeriksaan_no_urut, pemeriksaan_no_urut").executeQuery();

                                if (rsLIS1.next()) {
                                    rsLIS1.beforeFirst();
                                    w = 1;
                                    while (rsLIS1.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top'>" + rsLIS1.getString("kategori_pemeriksaan_nama") + "</td>"
                                                + "</tr>");

                                        rsLIS2 = koneksi.prepareStatement("SELECT ifnull(sub_kategori_pemeriksaan_nama,'') sub_kategori_pemeriksaan_nama FROM lis_hasil_periksa_lab "
                                                + "WHERE no_lab='" + rsLISMaster.getString("no_lab") + "' and kategori_pemeriksaan_nama='" + rsLIS1.getString("kategori_pemeriksaan_nama") + "' "
                                                + "GROUP BY sub_kategori_pemeriksaan_nama ORDER BY kategori_pemeriksaan_no_urut, sub_kategori_pemeriksaan_no_urut, "
                                                + "sub_kategori_pemeriksaan_nama desc, pemeriksaan_no_urut").executeQuery();
                                        if (rsLIS2.next()) {
                                            rsLIS2.beforeFirst();
                                            lis1 = 1;
                                            while (rsLIS2.next()) {
                                                htmlContent.append(
                                                        "<tr>"
                                                        + "<td valign='top'>&emsp;" + rsLIS2.getString("sub_kategori_pemeriksaan_nama") + "</td>"
                                                        + "</tr>");

                                                rsLIS3 = koneksi.prepareStatement("SELECT ifnull(pemeriksaan_nama,'') pemeriksaan_nama, metode, nilai_hasil, nilai_rujukan, "
                                                        + "satuan, flag_kode FROM lis_hasil_periksa_lab WHERE no_lab='" + rsLISMaster.getString("no_lab") + "' and "
                                                        + "sub_kategori_pemeriksaan_nama='" + rsLIS2.getString("sub_kategori_pemeriksaan_nama") + "' and "
                                                        + "kategori_pemeriksaan_nama='" + rsLIS1.getString("kategori_pemeriksaan_nama") + "' GROUP BY pemeriksaan_nama "
                                                        + "ORDER BY kategori_pemeriksaan_no_urut, sub_kategori_pemeriksaan_no_urut, pemeriksaan_no_urut").executeQuery();
                                                if (rsLIS3.next()) {
                                                    rsLIS3.beforeFirst();
                                                    lis2 = 1;
                                                    while (rsLIS3.next()) {
                                                        htmlContent.append(
                                                                "<tr>"
                                                                + "<td valign='top'>&emsp;&emsp;" + rsLIS3.getString("pemeriksaan_nama") + "</td>"
                                                                + "<td valign='top'>" + rsLIS3.getString("metode") + "</td>"
                                                                + "<td valign='top'>" + rsLIS3.getString("nilai_hasil") + "</td>"
                                                                + "<td valign='top'>" + rsLIS3.getString("nilai_rujukan") + "</td>"
                                                                + "<td valign='top'>" + rsLIS3.getString("satuan") + "</td>"
                                                                + "<td valign='top'>" + rsLIS3.getString("flag_kode") + "</td>"
                                                                + "</tr>");
                                                        lis2++;
                                                    }
                                                }
                                                lis1++;
                                            }
                                        }
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table><br/>");
                                }
                            }
                        }

                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rsLIS1 != null) {
                            rsLIS1.close();
                        }
                    }
                    htmlContent.append(
                            "</td>"
                            + "</tr>"
                    );
                    htmlContent.append("<tr class='isi'><td colspan='3' bgcolor='#7eccb9'>&nbsp;</td></tr>");
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
            }
            LoadHTML2.setText(
                    "<html>"
                    + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                    + htmlContent.toString()
                    + "</table>"
                    + "</html>");
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }
}
