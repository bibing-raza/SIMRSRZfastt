package rekammedis;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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
import simrskhanza.DlgCariDokter;
import simrskhanza.frmUtama;

/**
 *
 * @author dosen
 */
public class RMRekonsiliasiObat extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1, tabMode2, tabMode3, tabMode4, tabMode5;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps, ps1, ps2, ps3, ps4, ps5;
    private ResultSet rs, rs1, rs2, rs3, rs4, rs5;
    private int i = 0, x = 0, pilihan = 0;
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    private String kdObat = "", nipPereview = "", nipApoteker = "", nmDokter = "", tglreg = "", nipDokter = "",
            ceknmDokter = "", cekNipDokter = "", itemObat = "";
    private frmUtama formUtama;
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public RMRekonsiliasiObat(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new String[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Lahir", "Tgl. Rekon", "Jns. Rekonsiliasi", "Riwayat Alergi", "Ket. Riwayat Alergi", "Obat Dari Luar",
            "Catatan Riw. Obat IGD", "Catatan Riw. Obat Rawat Inap", "Ketidaksesuaian", "Saran", "Keputusan", "Di Review Oleh", "Nama Apoteker",
            "nip_pereview", "nip_apoteker", "tanggal", "waktu_simpan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbRekon.setModel(tabMode);
        tbRekon.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbRekon.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 20; i++) {
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
            } 
        }
        tbRekon.setDefaultRenderer(Object.class, new WarnaTable());
        
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
        
        tabMode2 = new DefaultTableModel(null, new String[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Resep", "Unit", "Nama Obat", "Rute", "Dosis", "Dilanjutkan", "Ket. Dilanjutkan",
            "tgl_resep", "kode_brng", "waktu_simpan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbRekonIgd.setModel(tabMode2);
        tbRekonIgd.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbRekonIgd.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 13; i++) {
            TableColumn column = tbRekonIgd.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(110);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(220);
            } else if (i == 3) {
                column.setPreferredWidth(75);
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
            }
        }
        tbRekonIgd.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode3 = new DefaultTableModel(null, new String[]{
            "Ruang Rawat", "Tgl. Pemberian", "Jam", "Nama Obat", "Rute dan Dosis", "Dokter Meresepkan"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbObatRiwayat.setModel(tabMode3);
        tbObatRiwayat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObatRiwayat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 6; i++) {
            TableColumn column = tbObatRiwayat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(250);
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
        tbObatRiwayat.setDefaultRenderer(Object.class, new WarnaTable());
        
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
                column.setPreferredWidth(180);
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
        
        tabMode5 = new DefaultTableModel(null, new String[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Resep", "Ruang Rawat", "Nama Obat", "Rute", "Dosis", "Aturan Pakai", "Dokter Meresepkan",
            "tgl_resep", "kode_brng", "nip_dokter", "waktu_simpan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbRekonRanap.setModel(tabMode5);
        tbRekonRanap.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbRekonRanap.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 14; i++) {
            TableColumn column = tbRekonRanap.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(110);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(220);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else if (i == 4) {
                column.setPreferredWidth(180);
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
            }
        }
        tbRekonRanap.setDefaultRenderer(Object.class, new WarnaTable());

        TketAlergi.setDocument(new batasInput((int) 200).getKata(TketAlergi));
        Trute.setDocument(new batasInput((int) 140).getKata(Trute));
        Tdosis.setDocument(new batasInput((int) 140).getKata(Tdosis));
        Tdilanjutkan.setDocument(new batasInput((int) 200).getKata(Tdilanjutkan));        
        Trute1.setDocument(new batasInput((int) 140).getKata(Trute));
        Tdosis1.setDocument(new batasInput((int) 140).getKata(Tdosis));
        Taturan.setDocument(new batasInput((int) 200).getKata(Tdosis));
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
        internalFrame2 = new widget.InternalFrame();
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
        PanelInput1 = new javax.swing.JPanel();
        FormInput2 = new widget.PanelBiasa();
        jLabel23 = new widget.Label();
        TNoRw2 = new widget.TextBox();
        TNoRM2 = new widget.TextBox();
        TPasien2 = new widget.TextBox();
        jLabel24 = new widget.Label();
        jLabel25 = new widget.Label();
        TrgRawat = new widget.TextBox();
        TtglMRS = new widget.TextBox();
        Scroll1 = new widget.ScrollPane();
        tbObatRiwayat = new widget.Table();
        panelGlass11 = new widget.panelisi();
        jLabel66 = new widget.Label();
        DTPCari3 = new widget.Tanggal();
        jLabel71 = new widget.Label();
        DTPCari4 = new widget.Tanggal();
        jLabel26 = new widget.Label();
        TCari1 = new widget.TextBox();
        BtnCari1 = new widget.Button();
        BtnAll1 = new widget.Button();
        jLabel27 = new widget.Label();
        LCount1 = new widget.Label();
        PanelInput = new javax.swing.JPanel();
        FormInput = new widget.PanelBiasa();
        jLabel4 = new widget.Label();
        TNoRw3 = new widget.TextBox();
        TNoRM3 = new widget.TextBox();
        TPasien3 = new widget.TextBox();
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
        internalFrame3 = new widget.InternalFrame();
        Scroll2 = new widget.ScrollPane();
        tbRekonIgd = new widget.Table();
        Scroll3 = new widget.ScrollPane();
        tbRiwayatObatIgd = new widget.Table();
        PanelInput2 = new javax.swing.JPanel();
        FormInput3 = new widget.PanelBiasa();
        jLabel28 = new widget.Label();
        TNoRw4 = new widget.TextBox();
        TNoRM4 = new widget.TextBox();
        TPasien4 = new widget.TextBox();
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
        TrgRawat1 = new widget.TextBox();
        Taturan = new widget.TextBox();
        jLabel34 = new widget.Label();
        BtnDokter = new widget.Button();
        jLabel37 = new widget.Label();
        TtglMRS2 = new widget.TextBox();
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

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput1.setName("FormInput1"); // NOI18N
        FormInput1.setPreferredSize(new java.awt.Dimension(190, 410));
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
        jLabel15.setText("Catatan Penggunaan Obat IGD : ");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput1.add(jLabel15);
        jLabel15.setBounds(0, 94, 190, 23);

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
        scrollPane3.setBounds(194, 94, 530, 120);

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
        scrollPane4.setBounds(194, 218, 530, 120);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Cttn. Penggunaan Obat R. Inap : ");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput1.add(jLabel16);
        jLabel16.setBounds(0, 218, 190, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Ketidaksesuaian : ");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput1.add(jLabel17);
        jLabel17.setBounds(740, 10, 110, 23);

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
        scrollPane5.setBounds(855, 10, 530, 120);

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
        scrollPane6.setBounds(855, 135, 530, 120);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Saran : ");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput1.add(jLabel18);
        jLabel18.setBounds(740, 135, 110, 23);

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
        scrollPane7.setBounds(855, 260, 530, 120);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Keputusan : ");
        jLabel19.setName("jLabel19"); // NOI18N
        FormInput1.add(jLabel19);
        jLabel19.setBounds(740, 260, 110, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Direview Oleh : ");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput1.add(jLabel20);
        jLabel20.setBounds(0, 345, 190, 23);

        TnmPereview.setEditable(false);
        TnmPereview.setForeground(new java.awt.Color(0, 0, 0));
        TnmPereview.setToolTipText("Alt+C");
        TnmPereview.setName("TnmPereview"); // NOI18N
        TnmPereview.setPreferredSize(new java.awt.Dimension(140, 23));
        FormInput1.add(TnmPereview);
        TnmPereview.setBounds(194, 345, 430, 23);

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
        BtnPereview.setBounds(626, 345, 28, 23);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Apoteker : ");
        jLabel21.setName("jLabel21"); // NOI18N
        FormInput1.add(jLabel21);
        jLabel21.setBounds(0, 373, 190, 23);

        TnmApoteker.setEditable(false);
        TnmApoteker.setForeground(new java.awt.Color(0, 0, 0));
        TnmApoteker.setToolTipText("Alt+C");
        TnmApoteker.setName("TnmApoteker"); // NOI18N
        TnmApoteker.setPreferredSize(new java.awt.Dimension(140, 23));
        FormInput1.add(TnmApoteker);
        TnmApoteker.setBounds(194, 373, 430, 23);

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
        BtnApoteker.setBounds(626, 373, 28, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Tgl. Rekonsiliasi :");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput1.add(jLabel22);
        jLabel22.setBounds(260, 66, 120, 23);

        TtglRekon.setEditable(false);
        TtglRekon.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "17-07-2026" }));
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
        chkSaya1.setBounds(665, 345, 90, 23);

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
        chkSaya2.setBounds(665, 373, 90, 23);

        BtnCopasIgd.setForeground(new java.awt.Color(0, 0, 0));
        BtnCopasIgd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/paste.png"))); // NOI18N
        BtnCopasIgd.setMnemonic('2');
        BtnCopasIgd.setText("Copy & Paste Obat");
        BtnCopasIgd.setName("BtnCopasIgd"); // NOI18N
        BtnCopasIgd.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCopasIgd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCopasIgdActionPerformed(evt);
            }
        });
        FormInput1.add(BtnCopasIgd);
        BtnCopasIgd.setBounds(20, 120, 160, 30);

        BtnCopasRanap.setForeground(new java.awt.Color(0, 0, 0));
        BtnCopasRanap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/paste.png"))); // NOI18N
        BtnCopasRanap.setMnemonic('2');
        BtnCopasRanap.setText("Copy & Paste Obat");
        BtnCopasRanap.setName("BtnCopasRanap"); // NOI18N
        BtnCopasRanap.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCopasRanap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCopasRanapActionPerformed(evt);
            }
        });
        FormInput1.add(BtnCopasRanap);
        BtnCopasRanap.setBounds(20, 245, 160, 30);

        internalFrame2.add(FormInput1, java.awt.BorderLayout.PAGE_START);

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

        internalFrame2.add(Scroll4, java.awt.BorderLayout.CENTER);

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel65.setForeground(new java.awt.Color(0, 0, 0));
        jLabel65.setText("Tgl. Rekonsiliasi :");
        jLabel65.setName("jLabel65"); // NOI18N
        jLabel65.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass10.add(jLabel65);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "17-07-2026" }));
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

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "17-07-2026" }));
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

        internalFrame2.add(panelGlass10, java.awt.BorderLayout.PAGE_END);

        TabRekon.addTab(".: Rencana Penyesuaian Pengobatan", internalFrame2);

        PanelInput1.setName("PanelInput1"); // NOI18N
        PanelInput1.setOpaque(false);
        PanelInput1.setPreferredSize(new java.awt.Dimension(192, 300));
        PanelInput1.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput2.setName("FormInput2"); // NOI18N
        FormInput2.setPreferredSize(new java.awt.Dimension(190, 75));
        FormInput2.setLayout(null);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("No. Rawat : ");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput2.add(jLabel23);
        jLabel23.setBounds(0, 10, 115, 23);

        TNoRw2.setEditable(false);
        TNoRw2.setBackground(new java.awt.Color(245, 250, 240));
        TNoRw2.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw2.setName("TNoRw2"); // NOI18N
        FormInput2.add(TNoRw2);
        TNoRw2.setBounds(116, 10, 122, 23);

        TNoRM2.setEditable(false);
        TNoRM2.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM2.setName("TNoRM2"); // NOI18N
        FormInput2.add(TNoRM2);
        TNoRM2.setBounds(243, 10, 70, 23);

        TPasien2.setEditable(false);
        TPasien2.setBackground(new java.awt.Color(245, 250, 240));
        TPasien2.setForeground(new java.awt.Color(0, 0, 0));
        TPasien2.setName("TPasien2"); // NOI18N
        FormInput2.add(TPasien2);
        TPasien2.setBounds(317, 10, 415, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Tgl. Masuk RS : ");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput2.add(jLabel24);
        jLabel24.setBounds(0, 38, 115, 23);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Rg. Rawat Terakhir :");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput2.add(jLabel25);
        jLabel25.setBounds(265, 38, 115, 23);

        TrgRawat.setEditable(false);
        TrgRawat.setForeground(new java.awt.Color(0, 0, 0));
        TrgRawat.setName("TrgRawat"); // NOI18N
        FormInput2.add(TrgRawat);
        TrgRawat.setBounds(385, 38, 347, 23);

        TtglMRS.setEditable(false);
        TtglMRS.setBackground(new java.awt.Color(245, 250, 240));
        TtglMRS.setForeground(new java.awt.Color(0, 0, 0));
        TtglMRS.setName("TtglMRS"); // NOI18N
        FormInput2.add(TtglMRS);
        TtglMRS.setBounds(116, 38, 150, 23);

        PanelInput1.add(FormInput2, java.awt.BorderLayout.PAGE_START);

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbObatRiwayat.setName("tbObatRiwayat"); // NOI18N
        tbObatRiwayat.getTableHeader().setReorderingAllowed(false);
        Scroll1.setViewportView(tbObatRiwayat);

        PanelInput1.add(Scroll1, java.awt.BorderLayout.CENTER);

        panelGlass11.setName("panelGlass11"); // NOI18N
        panelGlass11.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel66.setForeground(new java.awt.Color(0, 0, 0));
        jLabel66.setText("Tgl. Pemberian :");
        jLabel66.setName("jLabel66"); // NOI18N
        jLabel66.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass11.add(jLabel66);

        DTPCari3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "17-07-2026" }));
        DTPCari3.setDisplayFormat("dd-MM-yyyy");
        DTPCari3.setName("DTPCari3"); // NOI18N
        DTPCari3.setOpaque(false);
        DTPCari3.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass11.add(DTPCari3);

        jLabel71.setForeground(new java.awt.Color(0, 0, 0));
        jLabel71.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel71.setText("s.d.");
        jLabel71.setName("jLabel71"); // NOI18N
        jLabel71.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass11.add(jLabel71);

        DTPCari4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "17-07-2026" }));
        DTPCari4.setDisplayFormat("dd-MM-yyyy");
        DTPCari4.setName("DTPCari4"); // NOI18N
        DTPCari4.setOpaque(false);
        DTPCari4.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass11.add(DTPCari4);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Key Word :");
        jLabel26.setName("jLabel26"); // NOI18N
        jLabel26.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass11.add(jLabel26);

        TCari1.setForeground(new java.awt.Color(0, 0, 0));
        TCari1.setName("TCari1"); // NOI18N
        TCari1.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari1KeyPressed(evt);
            }
        });
        panelGlass11.add(TCari1);

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
        panelGlass11.add(BtnCari1);

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
        panelGlass11.add(BtnAll1);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("Record :");
        jLabel27.setName("jLabel27"); // NOI18N
        jLabel27.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass11.add(jLabel27);

        LCount1.setForeground(new java.awt.Color(0, 0, 0));
        LCount1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount1.setText("0");
        LCount1.setName("LCount1"); // NOI18N
        LCount1.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass11.add(LCount1);

        PanelInput1.add(panelGlass11, java.awt.BorderLayout.PAGE_END);

        TabRekon.addTab(".: Riwayat Penggunaan Obat Yang Diresepkan", PanelInput1);

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

        TNoRw3.setEditable(false);
        TNoRw3.setBackground(new java.awt.Color(245, 250, 240));
        TNoRw3.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw3.setName("TNoRw3"); // NOI18N
        FormInput.add(TNoRw3);
        TNoRw3.setBounds(136, 10, 122, 23);

        TNoRM3.setEditable(false);
        TNoRM3.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM3.setName("TNoRM3"); // NOI18N
        FormInput.add(TNoRM3);
        TNoRM3.setBounds(263, 10, 70, 23);

        TPasien3.setEditable(false);
        TPasien3.setBackground(new java.awt.Color(245, 250, 240));
        TPasien3.setForeground(new java.awt.Color(0, 0, 0));
        TPasien3.setName("TPasien3"); // NOI18N
        FormInput.add(TPasien3);
        TPasien3.setBounds(337, 10, 390, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Tgl. Resep : ");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(0, 38, 135, 23);

        TtglResep.setEditable(false);
        TtglResep.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "17-07-2026" }));
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

        PanelInput.add(FormInput, java.awt.BorderLayout.PAGE_START);

        internalFrame3.setBorder(null);
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.GridLayout(1, 2));

        Scroll2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Penggunaan Obat IGD ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
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

        Scroll3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Riwayat Peresepan Obat IGD ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
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

        TNoRw4.setEditable(false);
        TNoRw4.setBackground(new java.awt.Color(245, 250, 240));
        TNoRw4.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw4.setName("TNoRw4"); // NOI18N
        FormInput3.add(TNoRw4);
        TNoRw4.setBounds(136, 10, 122, 23);

        TNoRM4.setEditable(false);
        TNoRM4.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM4.setName("TNoRM4"); // NOI18N
        FormInput3.add(TNoRM4);
        TNoRM4.setBounds(263, 10, 70, 23);

        TPasien4.setEditable(false);
        TPasien4.setBackground(new java.awt.Color(245, 250, 240));
        TPasien4.setForeground(new java.awt.Color(0, 0, 0));
        TPasien4.setName("TPasien4"); // NOI18N
        FormInput3.add(TPasien4);
        TPasien4.setBounds(337, 10, 390, 23);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("Tgl. Resep : ");
        jLabel29.setName("jLabel29"); // NOI18N
        FormInput3.add(jLabel29);
        jLabel29.setBounds(0, 66, 135, 23);

        TtglResep1.setEditable(false);
        TtglResep1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "17-07-2026" }));
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

        TrgRawat1.setEditable(false);
        TrgRawat1.setForeground(new java.awt.Color(0, 0, 0));
        TrgRawat1.setName("TrgRawat1"); // NOI18N
        FormInput3.add(TrgRawat1);
        TrgRawat1.setBounds(136, 38, 340, 23);

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

        TtglMRS2.setEditable(false);
        TtglMRS2.setBackground(new java.awt.Color(245, 250, 240));
        TtglMRS2.setForeground(new java.awt.Color(0, 0, 0));
        TtglMRS2.setName("TtglMRS2"); // NOI18N
        FormInput3.add(TtglMRS2);
        TtglMRS2.setBounds(577, 38, 150, 23);

        PanelInput2.add(FormInput3, java.awt.BorderLayout.PAGE_START);

        internalFrame4.setBorder(null);
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.GridLayout(1, 2));

        Scroll5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Penggunaan Obat R. Inap ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
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

        Scroll6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Riwayat Peresepan Obat Selama Di R. Inap ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
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
        panelGlass12.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel67.setForeground(new java.awt.Color(0, 0, 0));
        jLabel67.setText("Tgl. Resep :");
        jLabel67.setName("jLabel67"); // NOI18N
        jLabel67.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass12.add(jLabel67);

        DTPCari5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "17-07-2026" }));
        DTPCari5.setDisplayFormat("dd-MM-yyyy");
        DTPCari5.setName("DTPCari5"); // NOI18N
        DTPCari5.setOpaque(false);
        DTPCari5.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass12.add(DTPCari5);

        jLabel72.setForeground(new java.awt.Color(0, 0, 0));
        jLabel72.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel72.setText("s.d.");
        jLabel72.setName("jLabel72"); // NOI18N
        jLabel72.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass12.add(jLabel72);

        DTPCari6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "17-07-2026" }));
        DTPCari6.setDisplayFormat("dd-MM-yyyy");
        DTPCari6.setName("DTPCari6"); // NOI18N
        DTPCari6.setOpaque(false);
        DTPCari6.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass12.add(DTPCari6);

        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("Key Word :");
        jLabel35.setName("jLabel35"); // NOI18N
        jLabel35.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass12.add(jLabel35);

        TCari2.setForeground(new java.awt.Color(0, 0, 0));
        TCari2.setName("TCari2"); // NOI18N
        TCari2.setPreferredSize(new java.awt.Dimension(200, 23));
        TCari2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari2KeyPressed(evt);
            }
        });
        panelGlass12.add(TCari2);

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

        FormInput4.add(panelGlass12, java.awt.BorderLayout.PAGE_END);

        internalFrame4.add(FormInput4);

        PanelInput2.add(internalFrame4, java.awt.BorderLayout.CENTER);

        TabRekon.addTab(".: Resep R. Inap Yang Dikeluarkan Farmasi", PanelInput2);

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
            if (TNoRw1.getText().equals("")) {
                Valid.textKosong(TNoRw1, "Pasien");
            } else {
                if (Sequel.menyimpantf("rekonsiliasi_obat", "?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 14, new String[]{
                    TNoRw1.getText(), cmbJnsRekon.getSelectedItem().toString(), cmbRiwAlergi.getSelectedItem().toString(),
                    TketAlergi.getText(), cmbBawaObat.getSelectedItem().toString(), TcatatanIgd.getText(), TcatatanRanap.getText(),
                    Tketidaksesuaian.getText(), Tsaran.getText(), Tkeputusan.getText(), nipPereview, nipApoteker, Valid.SetTgl(TtglRekon.getSelectedItem() + ""), 
                    Sequel.cariIsi("select now()")
                }) == true) {
                    emptTeks();
                    tampil();
                }
            }
        } else if (TabRekon.getSelectedIndex() == 2) {
            if (TNoRw3.getText().equals("")) {
                Valid.textKosong(TNoRw3, "Pasien");
            } else if (kdObat.equals("")) {
                Valid.textKosong(TnmObat, "Nama Obat");
            } else {
                if (Sequel.menyimpantf("rekonsiliasi_obat_igd", "?,?,?,?,?,?,?,?", "No.Rawat", 8, new String[]{
                    TNoRw3.getText(), Valid.SetTgl(TtglResep.getSelectedItem() + ""), kdObat, Trute.getText(), Tdosis.getText(),
                    cmbDilanjut.getSelectedItem().toString(), Tdilanjutkan.getText(), Sequel.cariIsi("select now()")
                }) == true) {                    
                    emptTeksRiwayatIGD();
                    tampilRekonIGD(TNoRw3.getText());
                    tampilRiwObatIGD();
                }
            }
        } else if (TabRekon.getSelectedIndex() == 3) {
            if (TNoRw4.getText().equals("")) {
                Valid.textKosong(TNoRw4, "Pasien");
            } else if (kdObat.equals("")) {
                Valid.textKosong(TnmObat1, "Nama Obat");
            } else {
                if (Sequel.menyimpantf("rekonsiliasi_obat_ranap", "?,?,?,?,?,?,?,?,?", "No.Rawat", 9, new String[]{
                    TNoRw4.getText(), TrgRawat1.getText(), Valid.SetTgl(TtglResep1.getSelectedItem() + ""), kdObat, Trute1.getText(),
                    Tdosis1.getText(), Taturan.getText(), nipDokter, Sequel.cariIsi("select now()")
                }) == true) {           
                    emptTeksRiwayatRanap();
                    tampilRekonRanap(TNoRw4.getText());
                    tampilRiwObatRanap();
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
            emptTeks();
            tampil();
        } else if (TabRekon.getSelectedIndex() == 1) {
            tampilRiwayatRanap();            
        } else if (TabRekon.getSelectedIndex() == 2) {
            emptTeksRiwayatIGD();
            tampilRekonIGD(TNoRw1.getText());
            tampilRiwObatIGD();
        } else if (TabRekon.getSelectedIndex() == 3) {
            emptTeksRiwayatRanap();
            tampilRekonRanap(TNoRw1.getText());
            tampilRiwObatRanap();
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
            if (TNoRw1.getText().equals("")) {
                Valid.textKosong(TNoRw1, "Pasien");
            } else {
                if (tbRekon.getSelectedRow() > -1) {
                    if (Sequel.mengedittf("rekonsiliasi_obat", "waktu_simpan=?", "jns_rekon=?, riwayat_alergi=?, "
                            + "ket_riwayat_alergi=?, membawa_obat_dari_luar=?, catatan_obat_igd=?, catatan_obat_ranap=?, ketidaksesuaian=?, "
                            + "saran=?, keputusan=?, nip_pereview=?, nip_apoteker=?, tanggal=?", 13, new String[]{
                                cmbJnsRekon.getSelectedItem().toString(), cmbRiwAlergi.getSelectedItem().toString(),
                                TketAlergi.getText(), cmbBawaObat.getSelectedItem().toString(), TcatatanIgd.getText(), TcatatanRanap.getText(),
                                Tketidaksesuaian.getText(), Tsaran.getText(), Tkeputusan.getText(), nipPereview, nipApoteker, 
                                Valid.SetTgl(TtglRekon.getSelectedItem() + ""),
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
        } else if (TabRekon.getSelectedIndex() == 2) {
            if (tbRekonIgd.getSelectedRow() > -1) {
                if (Sequel.mengedittf("rekonsiliasi_obat_igd", "waktu_simpan=?", "tgl_resep=?, kode_brng=?, rute=?, "
                        + "dosis=?, dilanjutkan=?, ket_dilanjutkan=?", 7, new String[]{
                            Valid.SetTgl(TtglResep.getSelectedItem() + ""), kdObat, Trute.getText(), Tdosis.getText(),
                            cmbDilanjut.getSelectedItem().toString(), Tdilanjutkan.getText(),
                            tbRekonIgd.getValueAt(tbRekonIgd.getSelectedRow(), 12).toString()
                        }) == true) {
                    emptTeksRiwayatIGD();
                    tampilRekonIGD(TNoRw3.getText());
                    tampilRiwObatIGD();
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
                tbRekonIgd.requestFocus();
            }
        } else if (TabRekon.getSelectedIndex() == 3) {
            if (tbRekonRanap.getSelectedRow() > -1) {
                if (Sequel.mengedittf("rekonsiliasi_obat_ranap", "waktu_simpan=?", "ruang_rawat=?, tgl_resep=?, "
                        + "kode_brng=?, rute=?, dosis=?, aturan_pakai=?, nip_dokter=?", 8, new String[]{
                            TrgRawat1.getText(), Valid.SetTgl(TtglResep1.getSelectedItem() + ""), kdObat, Trute1.getText(),
                            Tdosis1.getText(), Taturan.getText(), nipDokter,
                            tbRekonRanap.getValueAt(tbRekonRanap.getSelectedRow(), 13).toString()
                        }) == true) {
                    emptTeksRiwayatRanap();
                    tampilRekonRanap(TNoRw4.getText());
                    tampilRiwObatRanap();
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
                tbRekonRanap.requestFocus();
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
            tampil();
        } else if (TabRekon.getSelectedIndex() == 1) {
            tampilRiwayatRanap();
        } else if (TabRekon.getSelectedIndex() == 2) {
            TNoRw3.setText(TNoRw1.getText());
            TNoRM3.setText(Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat='" + TNoRw1.getText() + "'"));
            TPasien3.setText(Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='" + TNoRM3.getText() + "'"));
            tglreg = Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + TNoRw1.getText() + "'");
            TtglMRS1.setText(Valid.SetTglINDONESIA(tglreg));
            
            tampilRekonIGD(TNoRw1.getText());
            tampilRiwObatIGD();
        } else if (TabRekon.getSelectedIndex() == 3) {
            TNoRw4.setText(TNoRw1.getText());
            TNoRM4.setText(Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat='" + TNoRw1.getText() + "'"));
            TPasien4.setText(Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='" + TNoRM4.getText() + "'"));
            tglreg = Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + TNoRw1.getText() + "'");
            TtglMRS2.setText(Valid.SetTglINDONESIA(tglreg));
            
            tampilRekonRanap(TNoRw1.getText());
            tampilRiwObatRanap();
        }
    }//GEN-LAST:event_TabRekonMouseClicked

    private void TruteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TruteKeyPressed
        Valid.pindah(evt, TtglResep, Tdosis);
    }//GEN-LAST:event_TruteKeyPressed

    private void TdosisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdosisKeyPressed
        Valid.pindah(evt, Trute, Tdilanjutkan);
    }//GEN-LAST:event_TdosisKeyPressed

    private void TdilanjutkanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdilanjutkanKeyPressed
        Valid.pindah(evt, cmbDilanjut, BtnSimpan);
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
            if (tbRekon.getSelectedRow() > -1) {
                x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    if (Sequel.queryu2tf("delete from rekonsiliasi_obat where waktu_simpan=?", 1, new String[]{
                        tbRekon.getValueAt(tbRekon.getSelectedRow(), 19).toString()
                    }) == true) {
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
        } else if (TabRekon.getSelectedIndex() == 2) {
            if (tbRekonIgd.getSelectedRow() > -1) {
                x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    if (Sequel.queryu2tf("delete from rekonsiliasi_obat_igd where waktu_simpan=?", 1, new String[]{
                        tbRekonIgd.getValueAt(tbRekonIgd.getSelectedRow(), 12).toString()
                    }) == true) {
                        emptTeksRiwayatIGD();
                        tampilRekonIGD(TNoRw3.getText());
                        tampilRiwObatIGD();
                    } else {
                        JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                    }
                } else {
                    emptTeksRiwayatIGD();
                    tampilRekonIGD(TNoRw3.getText());
                    tampilRiwObatIGD();
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
                tbRekonIgd.requestFocus();
            }
        } else if (TabRekon.getSelectedIndex() == 3) {
            if (tbRekonRanap.getSelectedRow() > -1) {
                x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    if (Sequel.queryu2tf("delete from rekonsiliasi_obat_ranap where waktu_simpan=?", 1, new String[]{
                        tbRekonRanap.getValueAt(tbRekonRanap.getSelectedRow(), 13).toString()
                    }) == true) {
                        emptTeksRiwayatRanap();
                        tampilRekonRanap(TNoRw4.getText());
                        tampilRiwObatRanap();
                    } else {
                        JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                    }
                } else {
                    emptTeksRiwayatRanap();
                    tampilRekonRanap(TNoRw4.getText());
                    tampilRiwObatRanap();
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
                tbRekonRanap.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
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

    private void TCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari1ActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari1.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        }
    }//GEN-LAST:event_TCari1KeyPressed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        tampilRiwayatRanap();
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void BtnAll1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAll1ActionPerformed
        TCari1.setText("");
        tampilRiwayatRanap();
    }//GEN-LAST:event_BtnAll1ActionPerformed

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
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("norm", TNoRM1.getText());
            param.put("nmpasien", TPasien1.getText());
            param.put("tgllahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d-%m-%Y') from pasien where no_rkm_medis='" + TNoRM1.getText() + "'"));

            param.put("jnsRekon", cmbJnsRekon.getSelectedItem().toString());

            if (cmbRiwAlergi.getSelectedIndex() == 2) {
                if (TketAlergi.getText().equals("")) {
                    param.put("riwAlergi", cmbRiwAlergi.getSelectedItem().toString() + " .............");
                } else {
                    param.put("riwAlergi", cmbRiwAlergi.getSelectedItem().toString() + " : " + TketAlergi.getText());
                }
            } else {
                param.put("riwAlergi", cmbRiwAlergi.getSelectedItem().toString());
            }

            param.put("bawaObatLuar", cmbBawaObat.getSelectedItem().toString());
            param.put("unitRalan", Sequel.cariIsi("select pl.nm_poli from reg_periksa rp inner join poliklinik pl on pl.kd_poli=rp.kd_poli where rp.no_rawat='" + TNoRw1.getText() + "'"));
            param.put("catatanIgd", TcatatanIgd.getText().replaceAll("\n", "<br/>"));
            param.put("catatanRanap", TcatatanRanap.getText().replaceAll("\n", "<br/>"));
            param.put("ketidaksesuaian", Tketidaksesuaian.getText().replaceAll("\n", "<br/>"));
            param.put("saran", Tsaran.getText().replaceAll("\n", "<br/>"));
            param.put("keputusan", Tkeputusan.getText().replaceAll("\n", "<br/>"));
            param.put("direview", TnmPereview.getText());
            param.put("apoteker", TnmApoteker.getText());
            param.put("tglRekon", Valid.SetTglINDONESIA(Valid.SetTgl(TtglRekon.getSelectedItem() + "")));
            
            Valid.MyReport("rptLembarRekonsiliasiObat.jasper", "report", "::[ Lembar Rekonsiliasi Obat ]::",
                    "SELECT COALESCE(CASE WHEN x.jenis = 'IGD' THEN pl.nm_poli ELSE x.ruang_rawat END, '') AS ruang_rawat, "
                    + "    COALESCE(d.nama_brng, '') AS nmObat, "
                    + "    COALESCE(x.rute, '') AS rute, "
                    + "    COALESCE(x.dosis, '') AS dosis, "
                    + "    COALESCE(x.aturan_pakai, '') AS aturanPakai, "
                    + "    COALESCE(pg.nama, '') AS nmDokter, "
                    + "    COALESCE(DATE_FORMAT(x.tgl_resep, '%d/%m/%Y'), '') AS tglResep "
                    + "FROM (SELECT roi.no_rawat, roi.kode_brng, roi.rute, roi.dosis,'' AS aturan_pakai, roi.tgl_resep, NULL AS ruang_rawat, rp.kd_dokter AS nip_dokter, 'IGD' AS jenis "
                    + "    FROM rekonsiliasi_obat_igd roi INNER JOIN reg_periksa rp ON rp.no_rawat = roi.no_rawat WHERE roi.no_rawat = '" + TNoRw1.getText() + "' "
                    + "    UNION ALL "
                    + "    SELECT ror.no_rawat, ror.kode_brng, ror.rute, ror.dosis, ror.aturan_pakai, ror.tgl_resep, ror.ruang_rawat, ror.nip_dokter, 'RANAP' AS jenis "
                    + "    FROM rekonsiliasi_obat_ranap ror WHERE ror.no_rawat = '" + TNoRw1.getText() + "') x "
                    + "INNER JOIN reg_periksa rp ON rp.no_rawat = x.no_rawat "
                    + "INNER JOIN pegawai pg ON pg.nik = x.nip_dokter "
                    + "INNER JOIN databarang d ON d.kode_brng = x.kode_brng "
                    + "INNER JOIN poliklinik pl ON pl.kd_poli = rp.kd_poli "
                    + "UNION ALL "
                    + "SELECT '' AS ruang_rawat, '' AS nmObat, '' AS rute, '' AS dosis, '' AS aturanPakai, '' AS nmDokter, '' AS tglResep "
                    + "FROM rekonsiliasi_obat ro WHERE ro.no_rawat = '" + TNoRw1.getText() + "' "
                    + "AND NOT EXISTS (SELECT 1 FROM rekonsiliasi_obat_igd roi WHERE roi.no_rawat = ro.no_rawat) "
                    + "AND NOT EXISTS (SELECT 1 FROM rekonsiliasi_obat_ranap ror WHERE ror.no_rawat = ro.no_rawat)", param);
            
            BtnBatalActionPerformed(null);
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
    private widget.Button BtnApoteker;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnCari1;
    private widget.Button BtnCari2;
    private widget.Button BtnCopasIgd;
    private widget.Button BtnCopasRanap;
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
    private widget.PanelBiasa FormInput;
    private widget.PanelBiasa FormInput1;
    private widget.PanelBiasa FormInput2;
    private widget.PanelBiasa FormInput3;
    private widget.PanelBiasa FormInput4;
    private widget.Label LCount;
    private widget.Label LCount1;
    private javax.swing.JPanel PanelInput;
    private javax.swing.JPanel PanelInput1;
    private javax.swing.JPanel PanelInput2;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll4;
    private widget.ScrollPane Scroll5;
    private widget.ScrollPane Scroll6;
    public widget.TextBox TCari;
    public widget.TextBox TCari1;
    public widget.TextBox TCari2;
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
    private widget.TextArea TcatatanIgd;
    private widget.TextArea TcatatanRanap;
    private widget.TextBox Tdilanjutkan;
    private widget.TextBox Tdosis;
    private widget.TextBox Tdosis1;
    private widget.TextArea Tkeputusan;
    private widget.TextBox TketAlergi;
    private widget.TextArea Tketidaksesuaian;
    private widget.TextBox TnmApoteker;
    private widget.TextBox TnmDokter;
    private widget.TextBox TnmObat;
    private widget.TextBox TnmObat1;
    private widget.TextBox TnmPereview;
    private widget.TextBox TrgRawat;
    private widget.TextBox TrgRawat1;
    private widget.TextBox Trute;
    private widget.TextBox Trute1;
    private widget.TextArea Tsaran;
    private widget.TextBox TtglMRS;
    private widget.TextBox TtglMRS1;
    private widget.TextBox TtglMRS2;
    private widget.Tanggal TtglRekon;
    private widget.Tanggal TtglResep;
    private widget.Tanggal TtglResep1;
    private widget.CekBox chkSaya1;
    private widget.CekBox chkSaya2;
    private widget.ComboBox cmbBawaObat;
    private widget.ComboBox cmbDilanjut;
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
    private widget.Label jLabel23;
    private widget.Label jLabel24;
    private widget.Label jLabel25;
    private widget.Label jLabel26;
    private widget.Label jLabel27;
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
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel63;
    private widget.Label jLabel65;
    private widget.Label jLabel66;
    private widget.Label jLabel67;
    private widget.Label jLabel7;
    private widget.Label jLabel70;
    private widget.Label jLabel71;
    private widget.Label jLabel72;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass12;
    private widget.panelisi panelGlass8;
    private widget.ScrollPane scrollPane3;
    private widget.ScrollPane scrollPane4;
    private widget.ScrollPane scrollPane5;
    private widget.ScrollPane scrollPane6;
    private widget.ScrollPane scrollPane7;
    private widget.Table tbObatRiwayat;
    private widget.Table tbRekon;
    private widget.Table tbRekonIgd;
    private widget.Table tbRekonRanap;
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
                        rs.getString("waktu_simpan")
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
    }
    
    public void emptTeksRiwayatRanap() {
        TtglResep1.setDate(new Date());
        TrgRawat1.setText("");
        TnmObat1.setText("");
        Trute1.setText("");
        Taturan.setText("");
        Tdosis1.setText("");
        nipDokter = "-";
        TnmDokter.setText("-");
    }

    private void getData() {
        nipPereview = ""; 
        nipApoteker = "";
        if (tbRekon.getSelectedRow() != -1) {
            TNoRw1.setText(tbRekon.getValueAt(tbRekon.getSelectedRow(), 0).toString());
            TNoRM1.setText(tbRekon.getValueAt(tbRekon.getSelectedRow(), 1).toString());
            TPasien1.setText(tbRekon.getValueAt(tbRekon.getSelectedRow(), 2).toString());            
            TNoRw2.setText(TNoRw1.getText());
            TNoRM2.setText(TNoRM1.getText());
            TPasien2.setText(TPasien1.getText());            
            TNoRw3.setText(TNoRw1.getText());
            TNoRM3.setText(TNoRM1.getText());
            TPasien3.setText(TPasien1.getText());            
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
            
            TtglMRS.setText(Valid.SetTglINDONESIA(Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + TNoRw1.getText() + "'")));
            TrgRawat.setText(Sequel.cariIsi("select b.nm_bangsal from kamar_inap ki inner join kamar k on k.kd_kamar=ki.kd_kamar "
                    + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal where ki.no_rawat='" + TNoRw1.getText() + "' "
                    + "order by ki.tgl_masuk desc, ki.jam_masuk desc limit 1"));

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
                    + "WHERE dpo.no_rawat='" + TNoRw3.getText() + "' and dpo.status='ralan' order by dpo.tgl_perawatan, dpo.jam");
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
                    + "dpo.tgl_perawatan between ? and ? and dpo.no_rawat='" + TNoRw4.getText() + "' and dpo.status='ranap' and db.nama_brng like ? or "
                    + "dpo.tgl_perawatan between ? and ? and dpo.no_rawat='" + TNoRw4.getText() + "' and dpo.status='ranap' and b.nm_bangsal like ? "
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
                    + "db.nama_brng FROM rekonsiliasi_obat_igd ro inner join reg_periksa rp on rp.no_rawat=ro.no_rawat "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis inner join poliklinik pl on pl.kd_poli=rp.kd_poli "
                    + "inner join databarang db on db.kode_brng=ro.kode_brng WHERE ro.no_rawat='" + norw + "' order by ro.waktu_simpan");
            try {
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    tabMode2.addRow(new String[]{
                        rs2.getString("no_rawat"),
                        rs2.getString("no_rkm_medis"),
                        rs2.getString("nm_pasien"),
                        rs2.getString("tglResep"),
                        rs2.getString("nm_poli"),
                        rs2.getString("nama_brng"),
                        rs2.getString("rute"),
                        rs2.getString("dosis"),
                        rs2.getString("dilanjutkan"),
                        rs2.getString("ket_dilanjutkan"),
                        rs2.getString("tgl_resep"),
                        rs2.getString("kode_brng"),
                        rs2.getString("waktu_simpan")
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
                    + "db.nama_brng, pg.nama nmDokter FROM rekonsiliasi_obat_ranap ro "
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
                        rs5.getString("tglResep"),
                        rs5.getString("ruang_rawat"),
                        rs5.getString("nama_brng"),
                        rs5.getString("rute"),
                        rs5.getString("dosis"),
                        rs5.getString("aturan_pakai"),
                        rs5.getString("nmDokter"),
                        rs5.getString("tgl_resep"),
                        rs5.getString("kode_brng"),
                        rs5.getString("nip_dokter"),
                        rs5.getString("waktu_simpan")
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
    
    private void tampilRiwayatRanap() {
        nmDokter = "";
        Valid.tabelKosong(tabMode3);
        try {
            ps3 = koneksi.prepareStatement("SELECT *, date_format(tgl_pemberian, '%d/%m/%Y') tglPemberian, concat('Rute : ',cara_pemberian, ', Dosis : ',dosis) deskripsi, "
                    + "time(waktu_simpan) jam, status from pemberian_obat WHERE "
                    + "tgl_pemberian between ? and ? and no_rawat='" + TNoRw2.getText() + "' and nm_unit like ? and nama_obat not in ('','-','.') or "
                    + "tgl_pemberian between ? and ? and no_rawat='" + TNoRw2.getText() + "' and nama_obat like ? and nama_obat not in ('','-','.') or "
                    + "tgl_pemberian between ? and ? and no_rawat='" + TNoRw2.getText() + "' and concat('Rute : ',cara_pemberian,', Dosis : ',dosis) like ? and nama_obat not in ('','-','.') "
                    + "order by tgl_pemberian, time(waktu_simpan)");
            try {
                ps3.setString(1, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                ps3.setString(2, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
                ps3.setString(3, "%" + TCari1.getText().trim() + "%");
                ps3.setString(4, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                ps3.setString(5, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
                ps3.setString(6, "%" + TCari1.getText().trim() + "%");
                ps3.setString(7, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                ps3.setString(8, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
                ps3.setString(9, "%" + TCari1.getText().trim() + "%");
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
                    tabMode3.addRow(new String[]{
                        rs3.getString("nm_unit"),
                        rs3.getString("tglPemberian"),
                        rs3.getString("jam"),
                        rs3.getString("nama_obat"),
                        rs3.getString("deskripsi"),
                        nmDokter
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
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount1.setText("" + tabMode3.getRowCount());
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
            TrgRawat1.setText(tbRiwayatObatRanap.getValueAt(tbRiwayatObatRanap.getSelectedRow(), 2).toString());
            Valid.SetTgl(TtglResep1, tbRiwayatObatRanap.getValueAt(tbRiwayatObatRanap.getSelectedRow(), 7).toString());
            kdObat = tbRiwayatObatRanap.getValueAt(tbRiwayatObatRanap.getSelectedRow(), 6).toString();
            TnmObat1.setText(tbRiwayatObatRanap.getValueAt(tbRiwayatObatRanap.getSelectedRow(), 3).toString());
            nipDokter = tbRiwayatObatRanap.getValueAt(tbRiwayatObatRanap.getSelectedRow(), 8).toString();
            TnmDokter.setText(tbRiwayatObatRanap.getValueAt(tbRiwayatObatRanap.getSelectedRow(), 5).toString());
        }
    }
    
    private void getDataRekonIGD() {
        kdObat = "";
        if (tbRekonIgd.getSelectedRow() != -1) {
            TNoRw3.setText(tbRekonIgd.getValueAt(tbRekonIgd.getSelectedRow(), 0).toString());
            TNoRM3.setText(tbRekonIgd.getValueAt(tbRekonIgd.getSelectedRow(), 1).toString());
            TPasien3.setText(tbRekonIgd.getValueAt(tbRekonIgd.getSelectedRow(), 2).toString());
            Valid.SetTgl(TtglResep, tbRekonIgd.getValueAt(tbRekonIgd.getSelectedRow(), 10).toString());
            kdObat = tbRekonIgd.getValueAt(tbRekonIgd.getSelectedRow(), 11).toString();
            TnmObat.setText(tbRekonIgd.getValueAt(tbRekonIgd.getSelectedRow(), 5).toString());
            Trute.setText(tbRekonIgd.getValueAt(tbRekonIgd.getSelectedRow(), 6).toString());
            Tdosis.setText(tbRekonIgd.getValueAt(tbRekonIgd.getSelectedRow(), 7).toString());
            cmbDilanjut.setSelectedItem(tbRekonIgd.getValueAt(tbRekonIgd.getSelectedRow(), 8).toString());
            Tdilanjutkan.setText(tbRekonIgd.getValueAt(tbRekonIgd.getSelectedRow(), 9).toString());
            
            if (cmbDilanjut.getSelectedIndex() == 1) {
                Tdilanjutkan.setEnabled(true);
            } else {
                Tdilanjutkan.setEnabled(false);
            }
        }
    }
    
    private void getDataRekonRanap() {
        kdObat = "";
        nipDokter = "";
        if (tbRekonRanap.getSelectedRow() != -1) {
            TNoRw4.setText(tbRekonRanap.getValueAt(tbRekonRanap.getSelectedRow(), 0).toString());
            TNoRM4.setText(tbRekonRanap.getValueAt(tbRekonRanap.getSelectedRow(), 1).toString());
            TPasien4.setText(tbRekonRanap.getValueAt(tbRekonRanap.getSelectedRow(), 2).toString());
            TrgRawat1.setText(tbRekonRanap.getValueAt(tbRekonRanap.getSelectedRow(), 4).toString());
            Valid.SetTgl(TtglResep1, tbRekonRanap.getValueAt(tbRekonRanap.getSelectedRow(), 10).toString());
            kdObat = tbRekonRanap.getValueAt(tbRekonRanap.getSelectedRow(), 11).toString();
            TnmObat1.setText(tbRekonRanap.getValueAt(tbRekonRanap.getSelectedRow(), 5).toString());
            Trute1.setText(tbRekonRanap.getValueAt(tbRekonRanap.getSelectedRow(), 6).toString());
            Tdosis1.setText(tbRekonRanap.getValueAt(tbRekonRanap.getSelectedRow(), 7).toString());
            Taturan.setText(tbRekonRanap.getValueAt(tbRekonRanap.getSelectedRow(), 8).toString());
            nipDokter = tbRekonRanap.getValueAt(tbRekonRanap.getSelectedRow(), 12).toString();
            TnmDokter.setText(tbRekonRanap.getValueAt(tbRekonRanap.getSelectedRow(), 9).toString());
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
        tglreg = Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + norwt + "'");
        TtglMRS.setText(Valid.SetTglINDONESIA(tglreg));
        TtglMRS1.setText(Valid.SetTglINDONESIA(tglreg));
        TtglMRS2.setText(Valid.SetTglINDONESIA(tglreg));
        TrgRawat.setText(rgrawat);
        Valid.SetTgl(DTPCari1, tglreg);
        DTPCari2.setDate(new Date());
        Valid.SetTgl(DTPCari3, tglreg);
        DTPCari4.setDate(new Date());
        Valid.SetTgl(DTPCari5, tglreg);
        DTPCari6.setDate(new Date());
        TCari.setText(norwt);
    }
    
    public void awalData() {
        tampil();
    }
}
