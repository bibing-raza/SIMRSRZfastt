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
import simrskhanza.DlgCariDokter;

/**
 *
 * @author dosen
 */
public class RMLembarObservasi extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1, tabMode2;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps, ps1, ps2;
    private ResultSet rs, rs1, rs2;
    private int i = 0, x = 0;
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    private String nipPerawat = "", nipDokter = "", wktSimpanObs = "";
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public RMLembarObservasi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new String[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Prioritas", "Ruangan", "Tgl. Observasi", "Jam", "A", "B", "C", "D",
            "Diagnosis Kerja", "Perawat/Bidan", "Nama Dokter", "tgl_observasi", "jam_observasi", "nik_petugas", "nik_dokter",
            "wktSimpan"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbObservasi.setModel(tabMode);
        tbObservasi.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObservasi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 19; i++) {
            TableColumn column = tbObservasi.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(230);
            } else if (i == 3) {
                column.setPreferredWidth(60);
            } else if (i == 4) {
                column.setPreferredWidth(200);
            } else if (i == 5) {
                column.setPreferredWidth(80);
            } else if (i == 6) {
                column.setPreferredWidth(50);
            } else if (i == 7) {
                column.setPreferredWidth(250);
            } else if (i == 8) {
                column.setPreferredWidth(250);
            } else if (i == 9) {
                column.setPreferredWidth(250);
            } else if (i == 10) {
                column.setPreferredWidth(250);
            } else if (i == 11) {
                column.setPreferredWidth(250);
            } else if (i == 12) {
                column.setPreferredWidth(210);
            } else if (i == 13) {
                column.setPreferredWidth(210);
            } else if (i == 14) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 15) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 16) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 17) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 18) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbObservasi.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1=new DefaultTableModel(null,new String[]{
            "No. Rawat", "Tgl. Observasi", "Jam", "GCS", "TD", "N", "R", "Temp", "SpO2", "Cairan", "Lain-lain",
            "Tindakan", "Ruangan", "jam", "wktSimpan"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbObsPasien.setModel(tabMode1);
        tbObsPasien.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObsPasien.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 15; i++) {
            TableColumn column = tbObsPasien.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(80);
            } else if (i == 2) {
                column.setPreferredWidth(58);
            } else if (i == 3) {
                column.setPreferredWidth(65);
            } else if (i == 4) {
                column.setPreferredWidth(65);
            } else if (i == 5) {
                column.setPreferredWidth(65);
            } else if (i == 6) {
                column.setPreferredWidth(65);
            } else if (i == 7) {
                column.setPreferredWidth(65);
            } else if (i == 8) {
                column.setPreferredWidth(65);
            } else if (i == 9) {
                column.setPreferredWidth(200);
            } else if (i == 10) {
                column.setPreferredWidth(200);
            } else if (i == 11) {
                column.setPreferredWidth(200);
            } else if (i == 12) {
                column.setPreferredWidth(200);
            } else if (i == 13) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 14) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } 
        }
        tbObsPasien.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode2=new DefaultTableModel(null,new String[]{
            "No. Rawat", "Tgl. Observasi", "Jam", "GCS", "TD", "N", "R", "Temp", "SpO2", "Cairan", "Lain-lain",
            "Tindakan", "Ruangan", "jam", "wktSimpan"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbDetailObs.setModel(tabMode2);
        tbDetailObs.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbDetailObs.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 15; i++) {
            TableColumn column = tbDetailObs.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(80);
            } else if (i == 2) {
                column.setPreferredWidth(58);
            } else if (i == 3) {
                column.setPreferredWidth(65);
            } else if (i == 4) {
                column.setPreferredWidth(65);
            } else if (i == 5) {
                column.setPreferredWidth(65);
            } else if (i == 6) {
                column.setPreferredWidth(65);
            } else if (i == 7) {
                column.setPreferredWidth(65);
            } else if (i == 8) {
                column.setPreferredWidth(65);
            } else if (i == 9) {
                column.setPreferredWidth(200);
            } else if (i == 10) {
                column.setPreferredWidth(200);
            } else if (i == 11) {
                column.setPreferredWidth(200);
            } else if (i == 12) {
                column.setPreferredWidth(200);
            } else if (i == 13) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 14) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbDetailObs.setDefaultRenderer(Object.class, new WarnaTable());

        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        TdataA.setDocument(new batasInput((int) 200).getKata(TdataA));
        TdataB.setDocument(new batasInput((int) 200).getKata(TdataB));
        TdataC.setDocument(new batasInput((int) 200).getKata(TdataC));
        TdataD.setDocument(new batasInput((int) 200).getKata(TdataD));
        Tgcs.setDocument(new batasInput((byte) 8).getKata(Tgcs));
        Ttensi.setDocument(new batasInput((byte) 8).getKata(Ttensi));
        Tnadi.setDocument(new batasInput((byte) 8).getKata(Tnadi));
        Trespi.setDocument(new batasInput((byte) 8).getKata(Trespi));
        Ttemp.setDocument(new batasInput((byte) 8).getKata(Ttemp));
        Tspo.setDocument(new batasInput((byte) 8).getKata(Tspo));
        Tcairan.setDocument(new batasInput((int) 100).getKata(Tcairan));
        Tlain.setDocument(new batasInput((int) 100).getKata(Tlain));
        
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
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (petugas.getTable().getSelectedRow() != -1) {
                    nipPerawat = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                    TnmPerawat.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                    chkSaya.setSelected(false);
                    BtnPerawat.requestFocus();
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
        
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("RMLembarObservasi")) {
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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnObservasi = new javax.swing.JMenuItem();
        MnCetak = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        MnHapus = new javax.swing.JMenuItem();
        WindowObservasi = new javax.swing.JDialog();
        internalFrame5 = new widget.InternalFrame();
        panelGlass11 = new widget.panelisi();
        jLabel3 = new widget.Label();
        cmbJam1 = new widget.ComboBox();
        cmbMnt1 = new widget.ComboBox();
        cmbDtk1 = new widget.ComboBox();
        Tlain = new widget.TextBox();
        jLabel14 = new widget.Label();
        jLabel16 = new widget.Label();
        jLabel4 = new widget.Label();
        Tgcs = new widget.TextBox();
        jLabel5 = new widget.Label();
        Ttensi = new widget.TextBox();
        jLabel27 = new widget.Label();
        Tnadi = new widget.TextBox();
        jLabel29 = new widget.Label();
        Trespi = new widget.TextBox();
        jLabel30 = new widget.Label();
        Ttemp = new widget.TextBox();
        jLabel28 = new widget.Label();
        Tspo = new widget.TextBox();
        jLabel37 = new widget.Label();
        Tcairan = new widget.TextBox();
        jLabel17 = new widget.Label();
        Ttindakan = new widget.TextBox();
        jLabel18 = new widget.Label();
        Truangan2 = new widget.TextBox();
        Scroll1 = new widget.ScrollPane();
        tbObsPasien = new widget.Table();
        panelGlass9 = new widget.panelisi();
        BtnSimpan1 = new widget.Button();
        BtnBatal1 = new widget.Button();
        BtnHapus1 = new widget.Button();
        BtnEdit = new widget.Button();
        BtnKeluar1 = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        internalFrame1 = new widget.InternalFrame();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnGanti = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass10 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel8 = new widget.Label();
        cmbObservasi = new widget.ComboBox();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        PanelInput = new javax.swing.JPanel();
        FormInput = new widget.PanelBiasa();
        jLabel10 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel12 = new widget.Label();
        tglObservasi = new widget.Tanggal();
        jLabel13 = new widget.Label();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        jLabel63 = new widget.Label();
        TdataA = new widget.TextBox();
        jLabel64 = new widget.Label();
        TdataB = new widget.TextBox();
        jLabel65 = new widget.Label();
        TdataC = new widget.TextBox();
        TdataD = new widget.TextBox();
        jLabel66 = new widget.Label();
        jLabel67 = new widget.Label();
        scrollPane13 = new widget.ScrollPane();
        Tdiagnosis = new widget.TextArea();
        jLabel68 = new widget.Label();
        BtnPerawat = new widget.Button();
        TnmPerawat = new widget.TextBox();
        jLabel69 = new widget.Label();
        TnmDokter = new widget.TextBox();
        BtnDokter = new widget.Button();
        chkSaya = new widget.CekBox();
        jLabel70 = new widget.Label();
        Truangan1 = new widget.TextBox();
        cmbPrioritas = new widget.ComboBox();
        jLabel15 = new widget.Label();
        internalFrame2 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbObservasi = new widget.Table();
        Scroll2 = new widget.ScrollPane();
        tbDetailObs = new widget.Table();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnObservasi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnObservasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnObservasi.setText("Observasi Dilakukan");
        MnObservasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnObservasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnObservasi.setIconTextGap(5);
        MnObservasi.setName("MnObservasi"); // NOI18N
        MnObservasi.setPreferredSize(new java.awt.Dimension(170, 26));
        MnObservasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnObservasiActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnObservasi);

        MnCetak.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/PrinterSettings.png"))); // NOI18N
        MnCetak.setText("Cetak Lembar Observasi");
        MnCetak.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCetak.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCetak.setIconTextGap(5);
        MnCetak.setName("MnCetak"); // NOI18N
        MnCetak.setPreferredSize(new java.awt.Dimension(170, 26));
        MnCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnCetak);

        jPopupMenu2.setName("jPopupMenu2"); // NOI18N

        MnHapus.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        MnHapus.setText("Hapus Observasi Dilakukan");
        MnHapus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapus.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapus.setIconTextGap(5);
        MnHapus.setName("MnHapus"); // NOI18N
        MnHapus.setPreferredSize(new java.awt.Dimension(170, 26));
        MnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnHapus);

        WindowObservasi.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowObservasi.setName("WindowObservasi"); // NOI18N
        WindowObservasi.setUndecorated(true);
        WindowObservasi.setResizable(false);

        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Input Observasi Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setWarnaBawah(new java.awt.Color(240, 245, 235));
        internalFrame5.setLayout(new java.awt.BorderLayout());

        panelGlass11.setName("panelGlass11"); // NOI18N
        panelGlass11.setPreferredSize(new java.awt.Dimension(44, 183));
        panelGlass11.setLayout(null);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Jam :");
        jLabel3.setName("jLabel3"); // NOI18N
        panelGlass11.add(jLabel3);
        jLabel3.setBounds(2, 10, 100, 23);

        cmbJam1.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam1.setName("cmbJam1"); // NOI18N
        cmbJam1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam1MouseReleased(evt);
            }
        });
        panelGlass11.add(cmbJam1);
        cmbJam1.setBounds(107, 10, 45, 23);

        cmbMnt1.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt1.setName("cmbMnt1"); // NOI18N
        cmbMnt1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt1MouseReleased(evt);
            }
        });
        panelGlass11.add(cmbMnt1);
        cmbMnt1.setBounds(158, 10, 45, 23);

        cmbDtk1.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk1.setName("cmbDtk1"); // NOI18N
        cmbDtk1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk1MouseReleased(evt);
            }
        });
        panelGlass11.add(cmbDtk1);
        cmbDtk1.setBounds(210, 10, 45, 23);

        Tlain.setForeground(new java.awt.Color(0, 0, 0));
        Tlain.setName("Tlain"); // NOI18N
        Tlain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlainKeyPressed(evt);
            }
        });
        panelGlass11.add(Tlain);
        Tlain.setBounds(107, 94, 666, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Cairan :");
        jLabel14.setName("jLabel14"); // NOI18N
        panelGlass11.add(jLabel14);
        jLabel14.setBounds(2, 66, 100, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Lain - lain :");
        jLabel16.setName("jLabel16"); // NOI18N
        panelGlass11.add(jLabel16);
        jLabel16.setBounds(2, 94, 100, 23);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("TD :");
        jLabel4.setName("jLabel4"); // NOI18N
        panelGlass11.add(jLabel4);
        jLabel4.setBounds(2, 38, 100, 23);

        Tgcs.setForeground(new java.awt.Color(0, 0, 0));
        Tgcs.setName("Tgcs"); // NOI18N
        Tgcs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgcsKeyPressed(evt);
            }
        });
        panelGlass11.add(Tgcs);
        Tgcs.setBounds(312, 10, 70, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("GCS : ");
        jLabel5.setName("jLabel5"); // NOI18N
        panelGlass11.add(jLabel5);
        jLabel5.setBounds(258, 10, 50, 23);

        Ttensi.setBackground(new java.awt.Color(245, 250, 240));
        Ttensi.setForeground(new java.awt.Color(0, 0, 0));
        Ttensi.setName("Ttensi"); // NOI18N
        Ttensi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtensiKeyPressed(evt);
            }
        });
        panelGlass11.add(Ttensi);
        Ttensi.setBounds(107, 38, 70, 23);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel27.setText("mmHg       Nadi : ");
        jLabel27.setName("jLabel27"); // NOI18N
        panelGlass11.add(jLabel27);
        jLabel27.setBounds(183, 38, 90, 23);

        Tnadi.setBackground(new java.awt.Color(245, 250, 240));
        Tnadi.setForeground(new java.awt.Color(0, 0, 0));
        Tnadi.setName("Tnadi"); // NOI18N
        Tnadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnadiKeyPressed(evt);
            }
        });
        panelGlass11.add(Tnadi);
        Tnadi.setBounds(270, 38, 70, 23);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel29.setText("x/mnt      R : ");
        jLabel29.setName("jLabel29"); // NOI18N
        panelGlass11.add(jLabel29);
        jLabel29.setBounds(345, 38, 65, 23);

        Trespi.setBackground(new java.awt.Color(245, 250, 240));
        Trespi.setForeground(new java.awt.Color(0, 0, 0));
        Trespi.setName("Trespi"); // NOI18N
        Trespi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TrespiKeyPressed(evt);
            }
        });
        panelGlass11.add(Trespi);
        Trespi.setBounds(410, 38, 70, 23);

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel30.setText("x/mnt      Temp : ");
        jLabel30.setName("jLabel30"); // NOI18N
        panelGlass11.add(jLabel30);
        jLabel30.setBounds(487, 38, 85, 23);

        Ttemp.setBackground(new java.awt.Color(245, 250, 240));
        Ttemp.setForeground(new java.awt.Color(0, 0, 0));
        Ttemp.setName("Ttemp"); // NOI18N
        Ttemp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtempKeyPressed(evt);
            }
        });
        panelGlass11.add(Ttemp);
        Ttemp.setBounds(573, 38, 70, 23);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel28.setText("°C      SPO2 : ");
        jLabel28.setName("jLabel28"); // NOI18N
        panelGlass11.add(jLabel28);
        jLabel28.setBounds(648, 38, 68, 23);

        Tspo.setBackground(new java.awt.Color(245, 250, 240));
        Tspo.setForeground(new java.awt.Color(0, 0, 0));
        Tspo.setName("Tspo"); // NOI18N
        Tspo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TspoKeyPressed(evt);
            }
        });
        panelGlass11.add(Tspo);
        Tspo.setBounds(717, 38, 55, 23);

        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel37.setText("%");
        jLabel37.setName("jLabel37"); // NOI18N
        panelGlass11.add(jLabel37);
        jLabel37.setBounds(778, 38, 30, 23);

        Tcairan.setBackground(new java.awt.Color(245, 250, 240));
        Tcairan.setForeground(new java.awt.Color(0, 0, 0));
        Tcairan.setName("Tcairan"); // NOI18N
        Tcairan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TcairanKeyPressed(evt);
            }
        });
        panelGlass11.add(Tcairan);
        Tcairan.setBounds(107, 66, 666, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Tindakan :");
        jLabel17.setName("jLabel17"); // NOI18N
        panelGlass11.add(jLabel17);
        jLabel17.setBounds(2, 122, 100, 23);

        Ttindakan.setForeground(new java.awt.Color(0, 0, 0));
        Ttindakan.setName("Ttindakan"); // NOI18N
        Ttindakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtindakanKeyPressed(evt);
            }
        });
        panelGlass11.add(Ttindakan);
        Ttindakan.setBounds(107, 122, 666, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Ruangan :");
        jLabel18.setName("jLabel18"); // NOI18N
        panelGlass11.add(jLabel18);
        jLabel18.setBounds(2, 150, 100, 23);

        Truangan2.setEditable(false);
        Truangan2.setForeground(new java.awt.Color(0, 0, 0));
        Truangan2.setName("Truangan2"); // NOI18N
        panelGlass11.add(Truangan2);
        Truangan2.setBounds(107, 150, 380, 23);

        internalFrame5.add(panelGlass11, java.awt.BorderLayout.PAGE_START);

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbObsPasien.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbObsPasien.setName("tbObsPasien"); // NOI18N
        tbObsPasien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbObsPasienMouseClicked(evt);
            }
        });
        tbObsPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbObsPasienKeyPressed(evt);
            }
        });
        Scroll1.setViewportView(tbObsPasien);

        internalFrame5.add(Scroll1, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 54));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnSimpan1.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan1.setMnemonic('S');
        BtnSimpan1.setText("Simpan");
        BtnSimpan1.setToolTipText("Alt+S");
        BtnSimpan1.setName("BtnSimpan1"); // NOI18N
        BtnSimpan1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnSimpan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan1ActionPerformed(evt);
            }
        });
        panelGlass9.add(BtnSimpan1);

        BtnBatal1.setForeground(new java.awt.Color(0, 0, 0));
        BtnBatal1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBatal1.setMnemonic('B');
        BtnBatal1.setText("Baru");
        BtnBatal1.setToolTipText("Alt+B");
        BtnBatal1.setName("BtnBatal1"); // NOI18N
        BtnBatal1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnBatal1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatal1ActionPerformed(evt);
            }
        });
        BtnBatal1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnBatal1KeyPressed(evt);
            }
        });
        panelGlass9.add(BtnBatal1);

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
        panelGlass9.add(BtnHapus1);

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
        panelGlass9.add(BtnEdit);

        BtnKeluar1.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar1.setMnemonic('K');
        BtnKeluar1.setText("Keluar");
        BtnKeluar1.setToolTipText("Alt+K");
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
        panelGlass9.add(BtnKeluar1);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass9.add(jLabel7);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass9.add(LCount);

        internalFrame5.add(panelGlass9, java.awt.BorderLayout.PAGE_END);

        WindowObservasi.getContentPane().add(internalFrame5, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Lembar Observasi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
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
        jLabel19.setText("Tanggal :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass10.add(jLabel19);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "14-09-2024" }));
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

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "14-09-2024" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass10.add(DTPCari2);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Jenis Observasi :");
        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.setPreferredSize(new java.awt.Dimension(100, 23));
        panelGlass10.add(jLabel8);

        cmbObservasi.setForeground(new java.awt.Color(0, 0, 0));
        cmbObservasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Semua", "Rawat Jalan", "Rawat Inap" }));
        cmbObservasi.setName("cmbObservasi"); // NOI18N
        cmbObservasi.setPreferredSize(new java.awt.Dimension(97, 28));
        panelGlass10.add(cmbObservasi);

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

        jPanel3.add(panelGlass10, java.awt.BorderLayout.CENTER);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(195, 358));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(190, 157));
        FormInput.setLayout(null);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("No. Rawat :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 10, 110, 23);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        FormInput.add(TNoRw);
        TNoRw.setBounds(114, 10, 131, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(247, 10, 70, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(319, 10, 410, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Tgl. Observasi :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(0, 66, 110, 23);

        tglObservasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "14-09-2024" }));
        tglObservasi.setDisplayFormat("dd-MM-yyyy");
        tglObservasi.setName("tglObservasi"); // NOI18N
        tglObservasi.setOpaque(false);
        tglObservasi.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(tglObservasi);
        tglObservasi.setBounds(115, 66, 90, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Jam Observasi :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(205, 66, 100, 23);

        cmbJam.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJamMouseReleased(evt);
            }
        });
        FormInput.add(cmbJam);
        cmbJam.setBounds(312, 66, 45, 23);

        cmbMnt.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMntMouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt);
        cmbMnt.setBounds(365, 66, 45, 23);

        cmbDtk.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtkMouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk);
        cmbDtk.setBounds(418, 66, 45, 23);

        jLabel63.setForeground(new java.awt.Color(0, 0, 0));
        jLabel63.setText("A :");
        jLabel63.setName("jLabel63"); // NOI18N
        FormInput.add(jLabel63);
        jLabel63.setBounds(0, 94, 110, 23);

        TdataA.setForeground(new java.awt.Color(0, 0, 0));
        TdataA.setName("TdataA"); // NOI18N
        TdataA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdataAKeyPressed(evt);
            }
        });
        FormInput.add(TdataA);
        TdataA.setBounds(115, 94, 615, 23);

        jLabel64.setForeground(new java.awt.Color(0, 0, 0));
        jLabel64.setText("B :");
        jLabel64.setName("jLabel64"); // NOI18N
        FormInput.add(jLabel64);
        jLabel64.setBounds(0, 122, 110, 23);

        TdataB.setForeground(new java.awt.Color(0, 0, 0));
        TdataB.setName("TdataB"); // NOI18N
        TdataB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdataBKeyPressed(evt);
            }
        });
        FormInput.add(TdataB);
        TdataB.setBounds(115, 122, 615, 23);

        jLabel65.setForeground(new java.awt.Color(0, 0, 0));
        jLabel65.setText("C :");
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput.add(jLabel65);
        jLabel65.setBounds(0, 150, 110, 23);

        TdataC.setForeground(new java.awt.Color(0, 0, 0));
        TdataC.setName("TdataC"); // NOI18N
        TdataC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdataCKeyPressed(evt);
            }
        });
        FormInput.add(TdataC);
        TdataC.setBounds(115, 150, 615, 23);

        TdataD.setForeground(new java.awt.Color(0, 0, 0));
        TdataD.setName("TdataD"); // NOI18N
        TdataD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdataDKeyPressed(evt);
            }
        });
        FormInput.add(TdataD);
        TdataD.setBounds(115, 178, 615, 23);

        jLabel66.setForeground(new java.awt.Color(0, 0, 0));
        jLabel66.setText("D :");
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput.add(jLabel66);
        jLabel66.setBounds(0, 178, 110, 23);

        jLabel67.setForeground(new java.awt.Color(0, 0, 0));
        jLabel67.setText("Diagnosis Kerja :");
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput.add(jLabel67);
        jLabel67.setBounds(0, 206, 110, 23);

        scrollPane13.setName("scrollPane13"); // NOI18N

        Tdiagnosis.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tdiagnosis.setColumns(20);
        Tdiagnosis.setRows(5);
        Tdiagnosis.setName("Tdiagnosis"); // NOI18N
        Tdiagnosis.setPreferredSize(new java.awt.Dimension(162, 200));
        Tdiagnosis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdiagnosisKeyPressed(evt);
            }
        });
        scrollPane13.setViewportView(Tdiagnosis);

        FormInput.add(scrollPane13);
        scrollPane13.setBounds(115, 206, 615, 80);

        jLabel68.setForeground(new java.awt.Color(0, 0, 0));
        jLabel68.setText("Perawat :");
        jLabel68.setName("jLabel68"); // NOI18N
        FormInput.add(jLabel68);
        jLabel68.setBounds(0, 293, 110, 23);

        BtnPerawat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPerawat.setMnemonic('2');
        BtnPerawat.setToolTipText("Alt+2");
        BtnPerawat.setName("BtnPerawat"); // NOI18N
        BtnPerawat.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPerawat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPerawatActionPerformed(evt);
            }
        });
        FormInput.add(BtnPerawat);
        BtnPerawat.setBounds(550, 293, 28, 23);

        TnmPerawat.setEditable(false);
        TnmPerawat.setForeground(new java.awt.Color(0, 0, 0));
        TnmPerawat.setToolTipText("Alt+C");
        TnmPerawat.setName("TnmPerawat"); // NOI18N
        TnmPerawat.setPreferredSize(new java.awt.Dimension(140, 23));
        FormInput.add(TnmPerawat);
        TnmPerawat.setBounds(115, 293, 430, 23);

        jLabel69.setForeground(new java.awt.Color(0, 0, 0));
        jLabel69.setText("Dokter :");
        jLabel69.setName("jLabel69"); // NOI18N
        FormInput.add(jLabel69);
        jLabel69.setBounds(0, 321, 110, 23);

        TnmDokter.setEditable(false);
        TnmDokter.setForeground(new java.awt.Color(0, 0, 0));
        TnmDokter.setToolTipText("Alt+C");
        TnmDokter.setName("TnmDokter"); // NOI18N
        TnmDokter.setPreferredSize(new java.awt.Dimension(140, 23));
        FormInput.add(TnmDokter);
        TnmDokter.setBounds(115, 321, 430, 23);

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
        FormInput.add(BtnDokter);
        BtnDokter.setBounds(550, 321, 28, 23);

        chkSaya.setBackground(new java.awt.Color(242, 242, 242));
        chkSaya.setForeground(new java.awt.Color(0, 0, 0));
        chkSaya.setText("Saya Sendiri");
        chkSaya.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSaya.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSaya.setName("chkSaya"); // NOI18N
        chkSaya.setOpaque(false);
        chkSaya.setPreferredSize(new java.awt.Dimension(220, 23));
        chkSaya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSayaActionPerformed(evt);
            }
        });
        FormInput.add(chkSaya);
        chkSaya.setBounds(590, 293, 90, 23);

        jLabel70.setForeground(new java.awt.Color(0, 0, 0));
        jLabel70.setText("Ruangan :");
        jLabel70.setName("jLabel70"); // NOI18N
        FormInput.add(jLabel70);
        jLabel70.setBounds(0, 38, 110, 23);

        Truangan1.setEditable(false);
        Truangan1.setForeground(new java.awt.Color(0, 0, 0));
        Truangan1.setToolTipText("Alt+C");
        Truangan1.setName("Truangan1"); // NOI18N
        Truangan1.setPreferredSize(new java.awt.Dimension(140, 23));
        FormInput.add(Truangan1);
        Truangan1.setBounds(115, 38, 430, 23);

        cmbPrioritas.setForeground(new java.awt.Color(0, 0, 0));
        cmbPrioritas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "P1", "P2", "P3" }));
        cmbPrioritas.setName("cmbPrioritas"); // NOI18N
        cmbPrioritas.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput.add(cmbPrioritas);
        cmbPrioritas.setBounds(572, 66, 50, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Pilihan Prioritas :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(465, 66, 100, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.GridLayout(1, 2));

        Scroll.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Observasi Pasien ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbObservasi.setToolTipText("Silahkan klik untuk memilih data yang diperbaiki atau dihapus");
        tbObservasi.setComponentPopupMenu(jPopupMenu1);
        tbObservasi.setName("tbObservasi"); // NOI18N
        tbObservasi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbObservasiMouseClicked(evt);
            }
        });
        tbObservasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbObservasiKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbObservasi);

        internalFrame2.add(Scroll);

        Scroll2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Observasi Yang Dilakukan ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);

        tbDetailObs.setToolTipText("Silahkan klik untuk memilih data yang diperbaiki atau dihapus");
        tbDetailObs.setComponentPopupMenu(jPopupMenu2);
        tbDetailObs.setName("tbDetailObs"); // NOI18N
        Scroll2.setViewportView(tbDetailObs);

        internalFrame2.add(Scroll2);

        internalFrame1.add(internalFrame2, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {            
            if (Sequel.menyimpantf("lembar_observasi", "?,?,?,?,?,?,?,?,?,?,?,?,?", "No. Rawat & Ruang Rawat", 13, new String[]{
                TNoRw.getText(), Truangan1.getText(), Valid.SetTgl(tglObservasi.getSelectedItem() + ""),
                cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(), TdataA.getText(), TdataB.getText(),
                TdataC.getText(), TdataD.getText(), Tdiagnosis.getText(), nipPerawat, nipDokter, Sequel.cariIsi("select now()"),
                cmbPrioritas.getSelectedItem().toString()
            }) == true) {

                TCari.setText(TNoRw.getText());
                emptTeks();
                tampil();
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
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnGantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            if (tbObservasi.getSelectedRow() > -1) {
                if (akses.getadmin() == true) {
                    ganti();
                } else {
                    if (nipPerawat.equals(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 16).toString())) {
                        ganti();
                    } else {
                        JOptionPane.showMessageDialog(null, "Hanya bisa diganti oleh petugas yang melakukan observasi pasien..!!");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
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
        dispose();
        WindowObservasi.dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
            WindowObservasi.dispose();
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
        emptTeks();        
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
            TCari.setText("");
        } 
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbObservasiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObservasiMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbObservasiMouseClicked

    private void tbObservasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObservasiKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbObservasiKeyPressed

    private void cmbJamMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJamMouseReleased
        AutoCompleteDecorator.decorate(cmbJam);
    }//GEN-LAST:event_cmbJamMouseReleased

    private void cmbMntMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMntMouseReleased
        AutoCompleteDecorator.decorate(cmbMnt);
    }//GEN-LAST:event_cmbMntMouseReleased

    private void cmbDtkMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtkMouseReleased
        AutoCompleteDecorator.decorate(cmbDtk);
    }//GEN-LAST:event_cmbDtkMouseReleased

    private void TdiagnosisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdiagnosisKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            BtnPerawat.requestFocus();
        }
    }//GEN-LAST:event_TdiagnosisKeyPressed

    private void BtnPerawatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPerawatActionPerformed
        akses.setform("RMLembarObservasi");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPerawatActionPerformed

    private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
        akses.setform("RMLembarObservasi");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDokterActionPerformed

    private void chkSayaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSayaActionPerformed
        if (chkSaya.isSelected() == true) {
            if (akses.getadmin() == true) {
                nipPerawat = "-";
                TnmPerawat.setText("-");
            } else {
                nipPerawat = akses.getkode();
                TnmPerawat.setText(Sequel.cariIsi("select nama from pegawai where nik='" + nipPerawat + "'"));
            }
        } else {
            nipPerawat = "-";
            TnmPerawat.setText("-");
        }
    }//GEN-LAST:event_chkSayaActionPerformed

    private void TdataAKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdataAKeyPressed
        Valid.pindah(evt, TdataA, TdataB);
    }//GEN-LAST:event_TdataAKeyPressed

    private void TdataBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdataBKeyPressed
        Valid.pindah(evt, TdataA, TdataC);
    }//GEN-LAST:event_TdataBKeyPressed

    private void TdataCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdataCKeyPressed
        Valid.pindah(evt, TdataB, TdataD);
    }//GEN-LAST:event_TdataCKeyPressed

    private void TdataDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdataDKeyPressed
        Valid.pindah(evt, TdataC, Tdiagnosis);
    }//GEN-LAST:event_TdataDKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbObservasi.getSelectedRow() > -1) {
            if (akses.getadmin() == true) {
                hapus();
            } else {
                if (nipPerawat.equals(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 16).toString())) {
                    hapus();
                } else {
                    JOptionPane.showMessageDialog(null, "Hanya bisa dihapus oleh petugas yang melakukan observasi pasien..!!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
            tbObservasi.requestFocus();
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnGanti);
        }
    }//GEN-LAST:event_BtnHapusKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void MnObservasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnObservasiActionPerformed
        if (tbObservasi.getSelectedRow() > -1) {
            WindowObservasi.setSize(848, internalFrame1.getHeight() - 40);
            WindowObservasi.setLocationRelativeTo(internalFrame1);
            WindowObservasi.setAlwaysOnTop(false);
            WindowObservasi.setVisible(true);
            
            Truangan2.setText(Truangan1.getText());
            emptTeksObs();
            tampilObs2(wktSimpanObs);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan klik/pilih dulu salah satu datanya pada tabel..!!");
            tbObservasi.requestFocus();
        }
    }//GEN-LAST:event_MnObservasiActionPerformed

    private void cmbJam1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam1MouseReleased
        AutoCompleteDecorator.decorate(cmbJam1);
    }//GEN-LAST:event_cmbJam1MouseReleased

    private void cmbMnt1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt1MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt1);
    }//GEN-LAST:event_cmbMnt1MouseReleased

    private void cmbDtk1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk1MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk1);
    }//GEN-LAST:event_cmbDtk1MouseReleased

    private void TtensiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtensiKeyPressed
        Valid.pindah(evt, Tgcs, Tnadi);
    }//GEN-LAST:event_TtensiKeyPressed

    private void TnadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnadiKeyPressed
        Valid.pindah(evt, Ttensi, Trespi);
    }//GEN-LAST:event_TnadiKeyPressed

    private void TrespiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TrespiKeyPressed
        Valid.pindah(evt, Tnadi, Ttemp);
    }//GEN-LAST:event_TrespiKeyPressed

    private void TtempKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtempKeyPressed
        Valid.pindah(evt, Trespi, Tspo);
    }//GEN-LAST:event_TtempKeyPressed

    private void TspoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TspoKeyPressed
        Valid.pindah(evt, Ttemp, Tcairan);
    }//GEN-LAST:event_TspoKeyPressed

    private void TgcsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgcsKeyPressed
        Valid.pindah(evt, Tgcs, Ttensi);
    }//GEN-LAST:event_TgcsKeyPressed

    private void TcairanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TcairanKeyPressed
        Valid.pindah(evt, Tspo, Tlain);
    }//GEN-LAST:event_TcairanKeyPressed

    private void TlainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlainKeyPressed
        Valid.pindah(evt, Tcairan, Ttindakan);
    }//GEN-LAST:event_TlainKeyPressed

    private void TtindakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtindakanKeyPressed
        Valid.pindah(evt, Tlain, Truangan2);
    }//GEN-LAST:event_TtindakanKeyPressed

    private void tbObsPasienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObsPasienMouseClicked
        if (tabMode1.getRowCount() != 0) {
            try {
                getDataObs2();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbObsPasienMouseClicked

    private void tbObsPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObsPasienKeyPressed
        if (tabMode1.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataObs2();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbObsPasienKeyPressed

    private void BtnSimpan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan1ActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            if (Sequel.menyimpantf("detail_lembar_observasi", "?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 14, new String[]{
                TNoRw.getText(), Truangan2.getText(), cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem(),
                Tgcs.getText(), Ttensi.getText(), Tnadi.getText(), Trespi.getText(), Ttemp.getText(), Tspo.getText(), Tcairan.getText(), Tlain.getText(),
                Ttindakan.getText(), Sequel.cariIsi("select now()"), wktSimpanObs
            }) == true) {

                emptTeksObs();
                tampilObs2(wktSimpanObs);
                tampilObs1(wktSimpanObs);
            }
        }
    }//GEN-LAST:event_BtnSimpan1ActionPerformed

    private void BtnBatal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatal1ActionPerformed
        emptTeksObs();
    }//GEN-LAST:event_BtnBatal1ActionPerformed

    private void BtnBatal1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatal1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeksObs();
        } else {
            Valid.pindah(evt, BtnSimpan1, BtnHapus1);
        }
    }//GEN-LAST:event_BtnBatal1KeyPressed

    private void BtnHapus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapus1ActionPerformed
        if (tbObsPasien.getSelectedRow() > -1) {
            if (akses.getadmin() == true) {
                hapusObs2();
            } else {
                if (nipPerawat.equals(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 16).toString())) {
                    hapusObs2();
                } else {
                    JOptionPane.showMessageDialog(null, "Hanya bisa dihapus oleh petugas yang melakukan observasi pasien..!!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu..!!");
            tbObsPasien.requestFocus();
        }
    }//GEN-LAST:event_BtnHapus1ActionPerformed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            if (tbObsPasien.getSelectedRow() > -1) {
                if (akses.getadmin() == true) {
                    gantiObs();
                } else {
                    if (nipPerawat.equals(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 16).toString())) {
                        gantiObs();
                    } else {
                        JOptionPane.showMessageDialog(null, "Hanya bisa diganti oleh petugas yang melakukan observasi pasien..!!");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu..!!");
                tbObsPasien.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnEditActionPerformed

    private void BtnKeluar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar1ActionPerformed
        WindowObservasi.dispose();
    }//GEN-LAST:event_BtnKeluar1ActionPerformed

    private void BtnKeluar1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluar1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnKeluar1ActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnEdit, TCari);
        }
    }//GEN-LAST:event_BtnKeluar1KeyPressed

    private void MnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusActionPerformed
        if (tbDetailObs.getSelectedRow() > -1) {
            if (akses.getadmin() == true) {
                hapusObs1();
            } else {
                if (nipPerawat.equals(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 16).toString())) {
                    hapusObs1();
                } else {
                    JOptionPane.showMessageDialog(null, "Hanya bisa dihapus oleh petugas yang melakukan observasi pasien..!!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan klik/pilih salah satu datanya terlebih dulu pada tabel..!!");
            tbDetailObs.requestFocus();
        }
    }//GEN-LAST:event_MnHapusActionPerformed

    private void MnCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            if (tbObservasi.getSelectedRow() > -1) {
                if (Sequel.cariInteger("select count(-1) from detail_lembar_observasi where no_rawat='" + TNoRw.getText() + "'") > 0) {
                    Map<String, Object> param = new HashMap<>();
                    param.put("namars", akses.getnamars());
                    param.put("logo", Sequel.cariGambar("select logo from setting"));
                    param.put("norm", TNoRM.getText());
                    param.put("nmpasien", TPasien.getText());
                    param.put("tgllahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d-%m-%Y') from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));

                    if (cmbObservasi.getSelectedIndex() == 0) {
                        param.put("judul", "LEMBAR OBSERVASI SEMUA RUANG PERAWATAN");
                        param.put("tglObservasi", "Tanggal : - ,  Jam : -");
                        param.put("prioritas", "-");
                        param.put("dataA", "-");
                        param.put("dataB", "-");
                        param.put("dataC", "-");
                        param.put("dataD", "-");
                        param.put("diagnosa", "-");
                        param.put("perawat", "-");
                        param.put("dokter", "-");
                        Valid.MyReport("rptLembarObservasi.jasper", "report", "::[ Lembar Observasi Pasien ]::",
                                "SELECT d.*, time_format(d.jam,'%H:%i') jamnya, d.ruang_rawat ruangan FROM detail_lembar_observasi d "
                                + "INNER JOIN lembar_observasi lo on lo.no_rawat=d.no_rawat where "
                                + "d.no_rawat='" + TNoRw.getText() + "' order by d.jam", param);

                    } else {
                        if (cmbObservasi.getSelectedIndex() == 1) {
                            param.put("judul", "LEMBAR OBSERVASI " + Truangan1.getText());
                        } else if (cmbObservasi.getSelectedIndex() == 2) {
                            param.put("judul", "LEMBAR OBSERVASI RUANG " + Sequel.cariIsi("select nm_gedung from bangsal where nm_bangsal='" + Truangan1.getText() + "'"));
                        }

                        param.put("tglObservasi", "Tanggal : " + Valid.SetTglINDONESIA(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 14).toString())
                                + ",  Jam : " + tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 15).toString().substring(0, 5) + " Wita");
                        param.put("prioritas", tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 3).toString());
                        param.put("dataA", TdataA.getText());
                        param.put("dataB", TdataB.getText());
                        param.put("dataC", TdataC.getText());
                        param.put("dataD", TdataD.getText());
                        param.put("diagnosa", Tdiagnosis.getText());
                        param.put("perawat", TnmPerawat.getText());
                        param.put("dokter", TnmDokter.getText());
                        Valid.MyReport("rptLembarObservasi.jasper", "report", "::[ Lembar Observasi Pasien ]::",
                                "SELECT d.*, time_format(d.jam,'%H:%i') jamnya, d.ruang_rawat ruangan FROM detail_lembar_observasi d "
                                + "INNER JOIN lembar_observasi lo on lo.no_rawat=d.no_rawat and lo.waktu_simpan=d.waktu_simpan_lembar_obs where "
                                + "d.waktu_simpan_lembar_obs='" + wktSimpanObs + "' order by d.jam", param);
                    }

                    TCari.setText(TNoRw.getText());
                    emptTeks();
                    tampil();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Observasi pasien yang dilakukan belum tersimpan..!!");
                    tbObservasi.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel observasi pasien..!!");
                tbObservasi.requestFocus();
            }
        }
    }//GEN-LAST:event_MnCetakActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMLembarObservasi dialog = new RMLembarObservasi(new javax.swing.JFrame(), true);
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
    private widget.Button BtnBatal1;
    private widget.Button BtnCari;
    private widget.Button BtnDokter;
    private widget.Button BtnEdit;
    private widget.Button BtnGanti;
    private widget.Button BtnHapus;
    private widget.Button BtnHapus1;
    private widget.Button BtnKeluar;
    private widget.Button BtnKeluar1;
    private widget.Button BtnPerawat;
    private widget.Button BtnSimpan;
    private widget.Button BtnSimpan1;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnCetak;
    private javax.swing.JMenuItem MnHapus;
    private javax.swing.JMenuItem MnObservasi;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    public widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox Tcairan;
    private widget.TextBox TdataA;
    private widget.TextBox TdataB;
    private widget.TextBox TdataC;
    private widget.TextBox TdataD;
    private widget.TextArea Tdiagnosis;
    private widget.TextBox Tgcs;
    private widget.TextBox Tlain;
    private widget.TextBox Tnadi;
    private widget.TextBox TnmDokter;
    private widget.TextBox TnmPerawat;
    private widget.TextBox Trespi;
    private widget.TextBox Truangan1;
    private widget.TextBox Truangan2;
    private widget.TextBox Tspo;
    private widget.TextBox Ttemp;
    private widget.TextBox Ttensi;
    private widget.TextBox Ttindakan;
    private javax.swing.JDialog WindowObservasi;
    private widget.CekBox chkSaya;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbDtk1;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbJam1;
    private widget.ComboBox cmbMnt;
    private widget.ComboBox cmbMnt1;
    private widget.ComboBox cmbObservasi;
    private widget.ComboBox cmbPrioritas;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame5;
    private widget.Label jLabel10;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel27;
    private widget.Label jLabel28;
    private widget.Label jLabel29;
    private widget.Label jLabel3;
    private widget.Label jLabel30;
    private widget.Label jLabel37;
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel63;
    private widget.Label jLabel64;
    private widget.Label jLabel65;
    private widget.Label jLabel66;
    private widget.Label jLabel67;
    private widget.Label jLabel68;
    private widget.Label jLabel69;
    private widget.Label jLabel7;
    private widget.Label jLabel70;
    private widget.Label jLabel8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollPane13;
    private widget.Table tbDetailObs;
    private widget.Table tbObsPasien;
    private widget.Table tbObservasi;
    private widget.Tanggal tglObservasi;
    // End of variables declaration//GEN-END:variables

    public void tampil() {     
        Valid.tabelKosong(tabMode2);
        Valid.tabelKosong(tabMode);
        try {
            if (cmbObservasi.getSelectedIndex() == 0) {
                ps = koneksi.prepareStatement("select lo.*, p.no_rkm_medis, p.nm_pasien, date_format(lo.tgl_observasi,'%d-%m-%Y') tglobs, "
                        + "time_format(lo.jam_observasi,'%H:%i') jamobs, p1.nama nmpetugas, p2.nama nmdokter from lembar_observasi lo "
                        + "inner join reg_periksa rp on rp.no_rawat=lo.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                        + "inner join pegawai p1 on p1.nik=lo.nik_petugas inner join pegawai p2 on p2.nik=lo.nik_dokter where "
                        + "lo.tgl_observasi between ? and ? and lo.no_rawat like ? or "
                        + "lo.tgl_observasi between ? and ? and p.no_rkm_medis like ? or "
                        + "lo.tgl_observasi between ? and ? and p.nm_pasien like ? or "
                        + "lo.tgl_observasi between ? and ? and lo.data_a like ? or "
                        + "lo.tgl_observasi between ? and ? and lo.data_b like ? or "
                        + "lo.tgl_observasi between ? and ? and lo.data_c like ? or "
                        + "lo.tgl_observasi between ? and ? and lo.data_d like ? or "
                        + "lo.tgl_observasi between ? and ? and lo.diagnosis_kerja like ? or "
                        + "lo.tgl_observasi between ? and ? and p1.nama like ? or "
                        + "lo.tgl_observasi between ? and ? and p2.nama like ? order by lo.tgl_observasi desc, lo.jam_observasi desc");
            } else if (cmbObservasi.getSelectedIndex() == 1) {
                ps = koneksi.prepareStatement("select lo.*, p.no_rkm_medis, p.nm_pasien, date_format(lo.tgl_observasi,'%d-%m-%Y') tglobs, "
                        + "time_format(lo.jam_observasi,'%H:%i') jamobs, p1.nama nmpetugas, p2.nama nmdokter from lembar_observasi lo "
                        + "inner join reg_periksa rp on rp.no_rawat=lo.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                        + "inner join pegawai p1 on p1.nik=lo.nik_petugas inner join pegawai p2 on p2.nik=lo.nik_dokter where "
                        + "lo.tgl_observasi between ? and ? and lo.ruang_rawat like '%IGD%' and lo.no_rawat like ? or "
                        + "lo.tgl_observasi between ? and ? and lo.ruang_rawat like '%IGD%' and p.no_rkm_medis like ? or "
                        + "lo.tgl_observasi between ? and ? and lo.ruang_rawat like '%IGD%' and p.nm_pasien like ? or "
                        + "lo.tgl_observasi between ? and ? and lo.ruang_rawat like '%IGD%' and lo.data_a like ? or "
                        + "lo.tgl_observasi between ? and ? and lo.ruang_rawat like '%IGD%' and lo.data_b like ? or "
                        + "lo.tgl_observasi between ? and ? and lo.ruang_rawat like '%IGD%' and lo.data_c like ? or "
                        + "lo.tgl_observasi between ? and ? and lo.ruang_rawat like '%IGD%' and lo.data_d like ? or "
                        + "lo.tgl_observasi between ? and ? and lo.ruang_rawat like '%IGD%' and lo.diagnosis_kerja like ? or "
                        + "lo.tgl_observasi between ? and ? and lo.ruang_rawat like '%IGD%' and p1.nama like ? or "
                        + "lo.tgl_observasi between ? and ? and lo.ruang_rawat like '%IGD%' and p2.nama like ? "
                        + "order by lo.tgl_observasi desc, lo.jam_observasi desc");
            } else if (cmbObservasi.getSelectedIndex() == 2) {
                ps = koneksi.prepareStatement("select lo.*, p.no_rkm_medis, p.nm_pasien, date_format(lo.tgl_observasi,'%d-%m-%Y') tglobs, "
                        + "time_format(lo.jam_observasi,'%H:%i') jamobs, p1.nama nmpetugas, p2.nama nmdokter from lembar_observasi lo "
                        + "inner join reg_periksa rp on rp.no_rawat=lo.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                        + "inner join pegawai p1 on p1.nik=lo.nik_petugas inner join pegawai p2 on p2.nik=lo.nik_dokter where "
                        + "lo.tgl_observasi between ? and ? and lo.ruang_rawat not like '%IGD%' and lo.no_rawat like ? or "
                        + "lo.tgl_observasi between ? and ? and lo.ruang_rawat not like '%IGD%' and p.no_rkm_medis like ? or "
                        + "lo.tgl_observasi between ? and ? and lo.ruang_rawat not like '%IGD%' and p.nm_pasien like ? or "
                        + "lo.tgl_observasi between ? and ? and lo.ruang_rawat not like '%IGD%' and lo.data_a like ? or "
                        + "lo.tgl_observasi between ? and ? and lo.ruang_rawat not like '%IGD%' and lo.data_b like ? or "
                        + "lo.tgl_observasi between ? and ? and lo.ruang_rawat not like '%IGD%' and lo.data_c like ? or "
                        + "lo.tgl_observasi between ? and ? and lo.ruang_rawat not like '%IGD%' and lo.data_d like ? or "
                        + "lo.tgl_observasi between ? and ? and lo.ruang_rawat not like '%IGD%' and lo.diagnosis_kerja like ? or "
                        + "lo.tgl_observasi between ? and ? and lo.ruang_rawat not like '%IGD%' and p1.nama like ? or "
                        + "lo.tgl_observasi between ? and ? and lo.ruang_rawat not like '%IGD%' and p2.nama like ? "
                        + "order by lo.tgl_observasi desc, lo.jam_observasi desc");
            }

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
                rs = ps.executeQuery();                
                while (rs.next()) {
                    tabMode.addRow(new String[]{                        
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("prioritas_pilihan"),
                        rs.getString("ruang_rawat"),
                        rs.getString("tglobs"),
                        rs.getString("jamobs"),
                        rs.getString("data_a"),
                        rs.getString("data_b"),
                        rs.getString("data_c"),
                        rs.getString("data_d"),
                        rs.getString("diagnosis_kerja"),
                        rs.getString("nmpetugas"),
                        rs.getString("nmdokter"),
                        rs.getString("tgl_observasi"),
                        rs.getString("jam_observasi"),
                        rs.getString("nik_petugas"),
                        rs.getString("nik_dokter"),
                        rs.getString("waktu_simpan")
                    });
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println("rekammedis.RMLembarObservasi.tampil() : " + e);
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
    
    public void emptTeks() {  
        tglObservasi.setDate(new Date());
        cmbJam.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk.setSelectedIndex(0);
        TdataA.setText("");
        TdataB.setText("");
        TdataC.setText("");
        TdataD.setText("");
        Tdiagnosis.setText("");
        nipPerawat = "-";
        TnmPerawat.setText("-");
        nipDokter = "-";
        TnmDokter.setText("-");
        chkSaya.setSelected(false);
        cmbPrioritas.setSelectedIndex(0);
    }

    private void getData() {
        nipPerawat = "";
        nipDokter = "";
        wktSimpanObs = "";
        if (tbObservasi.getSelectedRow() != -1) {
            TNoRw.setText(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 0).toString());
            TNoRM.setText(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 1).toString());
            TPasien.setText(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 2).toString());
            cmbPrioritas.setSelectedItem(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 3).toString());
            Truangan1.setText(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 4).toString());
            Valid.SetTgl(tglObservasi, tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 14).toString());
            cmbJam.setSelectedItem(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 15).toString().substring(0, 2));
            cmbMnt.setSelectedItem(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 15).toString().substring(3, 5));
            cmbDtk.setSelectedItem(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 15).toString().substring(6, 8));
            TdataA.setText(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 7).toString());
            TdataB.setText(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 8).toString());
            TdataC.setText(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 9).toString());
            TdataD.setText(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 10).toString());
            Tdiagnosis.setText(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 11).toString());
            nipPerawat = tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 16).toString();
            nipDokter = tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 17).toString();
            TnmPerawat.setText(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 12).toString());
            TnmDokter.setText(tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 13).toString());
            wktSimpanObs = tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 18).toString().substring(0, 19);
            tampilObs1(wktSimpanObs);
        }
    }
    
    private void getDataObs2() {
        if (tbObsPasien.getSelectedRow() != -1) {
            cmbJam1.setSelectedItem(tbObsPasien.getValueAt(tbObsPasien.getSelectedRow(), 13).toString().substring(0, 2));
            cmbMnt1.setSelectedItem(tbObsPasien.getValueAt(tbObsPasien.getSelectedRow(), 13).toString().substring(3, 5));
            cmbDtk1.setSelectedItem(tbObsPasien.getValueAt(tbObsPasien.getSelectedRow(), 13).toString().substring(6, 8));
            Tgcs.setText(tbObsPasien.getValueAt(tbObsPasien.getSelectedRow(), 3).toString());
            Ttensi.setText(tbObsPasien.getValueAt(tbObsPasien.getSelectedRow(), 4).toString());
            Tnadi.setText(tbObsPasien.getValueAt(tbObsPasien.getSelectedRow(), 5).toString());
            Trespi.setText(tbObsPasien.getValueAt(tbObsPasien.getSelectedRow(), 6).toString());
            Ttemp.setText(tbObsPasien.getValueAt(tbObsPasien.getSelectedRow(), 7).toString());
            Tspo.setText(tbObsPasien.getValueAt(tbObsPasien.getSelectedRow(), 8).toString());
            Tcairan.setText(tbObsPasien.getValueAt(tbObsPasien.getSelectedRow(), 9).toString());
            Tlain.setText(tbObsPasien.getValueAt(tbObsPasien.getSelectedRow(), 10).toString());
            Ttindakan.setText(tbObsPasien.getValueAt(tbObsPasien.getSelectedRow(), 11).toString());
            Truangan2.setText(tbObsPasien.getValueAt(tbObsPasien.getSelectedRow(), 12).toString());
        }
    }
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.getcppt());
        BtnGanti.setEnabled(akses.getcppt());
        BtnHapus.setEnabled(akses.getcppt());
    }
    
    private void ganti() {
        if (Sequel.mengedittf("lembar_observasi", "waktu_simpan=?", "tgl_observasi=?, jam_observasi=?, data_a=?, data_b=?, data_c=?, data_d=?, "
                + "diagnosis_kerja=?, nik_petugas=?, nik_dokter=?, prioritas_pilihan=?", 11, new String[]{
                    Valid.SetTgl(tglObservasi.getSelectedItem() + ""),
                    cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(), TdataA.getText(), 
                    TdataB.getText(), TdataC.getText(), TdataD.getText(), Tdiagnosis.getText(), nipPerawat, nipDokter,
                    cmbPrioritas.getSelectedItem().toString(),
                    tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 18).toString()
                }) == true) {

            TCari.setText(TNoRw.getText());
            tampil();
            emptTeks();
        }
    }
    
    private void hapus() {
        x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            if (Sequel.queryu2tf("delete from lembar_observasi where waktu_simpan=?", 1, new String[]{
                tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 18).toString()
            }) == true) {
                Sequel.meghapus2("detail_lembar_observasi", "waktu_simpan_lembar_obs",
                        tbObservasi.getValueAt(tbObservasi.getSelectedRow(), 18).toString().substring(0, 19));

                TCari.setText(TNoRw.getText());
                emptTeks();
                tampil();
            } else {
                JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
            }
        }
    }
    
    public void setData(String norw, String norm, String nmpasien, String ruangan) {
        TNoRw.setText(norw);
        TNoRM.setText(norm);
        TPasien.setText(nmpasien);
        Valid.SetTgl(tglObservasi, Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + norw + "'"));
        cmbJam.setSelectedItem(Sequel.cariIsi("select jam_reg from reg_periksa where no_rawat='" + norw + "'").substring(0, 2));
        cmbMnt.setSelectedItem(Sequel.cariIsi("select jam_reg from reg_periksa where no_rawat='" + norw + "'").substring(3, 5));
        Valid.SetTgl(DTPCari1, Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + norw + "'"));
        Truangan1.setText(ruangan);
        TCari.setText(norw);
        
        if (ruangan.equals("IGD")) {
            cmbObservasi.setSelectedIndex(1);
        } else if (!ruangan.equals("IGD")) {
            cmbObservasi.setSelectedIndex(2);
        } else {
            cmbObservasi.setSelectedIndex(0);
        }
        
        if (akses.getadmin() == true) {
            nipPerawat = "-";
            TnmPerawat.setText("-");
        } else {
            nipPerawat = akses.getkode();
            TnmPerawat.setText(Sequel.cariIsi("select nama from pegawai where nik='" + nipPerawat + "'"));
        }
    }
    
    private void emptTeksObs() {
        cmbJam1.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt1.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk1.setSelectedIndex(0);
        Tgcs.setText("");
        Ttensi.setText("");
        Tnadi.setText("");
        Trespi.setText("");
        Ttemp.setText("");
        Tspo.setText("");
        Tcairan.setText("");
        Tlain.setText("");
        Ttindakan.setText("");
    }
    
    public void tampilObs1(String tgl) {     
        Valid.tabelKosong(tabMode2);
        try {
            ps2 = koneksi.prepareStatement("SELECT d.*, time_format(d.jam,'%H:%i') jamnya, d.ruang_rawat ruangan, "
                    + "date_format(lo.tgl_observasi,'%d-%m-%Y') tglobs FROM detail_lembar_observasi d "
                    + "INNER JOIN lembar_observasi lo on lo.no_rawat=d.no_rawat and lo.waktu_simpan=d.waktu_simpan_lembar_obs where "
                    + "d.waktu_simpan_lembar_obs='" + tgl + "' order by d.jam");
            try {
                rs2 = ps2.executeQuery();                
                while (rs2.next()) {
                    tabMode2.addRow(new String[]{                        
                        rs2.getString("no_rawat"),
                        rs2.getString("tglobs"),
                        rs2.getString("jamnya"),
                        rs2.getString("gcs"),
                        rs2.getString("td"),
                        rs2.getString("nadi"),
                        rs2.getString("respirasi"),
                        rs2.getString("temp"),
                        rs2.getString("spo2"),
                        rs2.getString("cairan"),
                        rs2.getString("lain_lain"),
                        rs2.getString("tindakan"),
                        rs2.getString("ruangan"),
                        rs2.getString("jam"),
                        rs2.getString("waktu_simpan")
                    });
                }
            } catch (Exception e) {
                System.out.println("rekammedis.RMLembarObservasi.tampilObs1() : " + e);
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
    
    public void tampilObs2(String tgl) {     
        Valid.tabelKosong(tabMode1);
        try {
            ps1 = koneksi.prepareStatement("SELECT d.*, time_format(d.jam,'%H:%i') jamnya, d.ruang_rawat ruangan, "
                    + "date_format(lo.tgl_observasi,'%d-%m-%Y') tglobs FROM detail_lembar_observasi d "
                    + "INNER JOIN lembar_observasi lo on lo.no_rawat=d.no_rawat and lo.waktu_simpan=d.waktu_simpan_lembar_obs where "
                    + "d.waktu_simpan_lembar_obs='" + tgl + "' order by d.jam");
            try {
                rs1 = ps1.executeQuery();                
                while (rs1.next()) {
                    tabMode1.addRow(new String[]{                        
                        rs1.getString("no_rawat"),
                        rs1.getString("tglobs"),
                        rs1.getString("jamnya"),
                        rs1.getString("gcs"),
                        rs1.getString("td"),
                        rs1.getString("nadi"),
                        rs1.getString("respirasi"),
                        rs1.getString("temp"),
                        rs1.getString("spo2"),
                        rs1.getString("cairan"),
                        rs1.getString("lain_lain"),
                        rs1.getString("tindakan"),
                        rs1.getString("ruangan"),
                        rs1.getString("jam"),
                        rs1.getString("waktu_simpan")
                    });
                }                
            } catch (Exception e) {
                System.out.println("rekammedis.RMLembarObservasi.tampilObs2() : " + e);
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
        LCount.setText("" + tabMode1.getRowCount());
    }
    
    private void hapusObs1() {
        x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            if (Sequel.queryu2tf("delete from detail_lembar_observasi where waktu_simpan=?", 1, new String[]{
                tbDetailObs.getValueAt(tbDetailObs.getSelectedRow(), 14).toString()
            }) == true) {
                tampilObs1(wktSimpanObs);
            } else {
                JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
            }
        }
    }
    
    private void hapusObs2() {
        x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            if (Sequel.queryu2tf("delete from detail_lembar_observasi where waktu_simpan=?", 1, new String[]{
                tbObsPasien.getValueAt(tbObsPasien.getSelectedRow(), 14).toString()
            }) == true) {
                tampilObs2(wktSimpanObs);
                tampilObs1(wktSimpanObs);
                emptTeksObs();
            } else {
                JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
            }
        }
    }
    
    private void gantiObs() {
        if (Sequel.mengedittf("detail_lembar_observasi", "waktu_simpan=?", "jam=?, gcs=?, td=?, nadi=?, respirasi=?, temp=?, "
                + "spo2=?, cairan=?, lain_lain=?, tindakan=?", 11, new String[]{
                    cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem(),
                    Tgcs.getText(), Ttensi.getText(), Tnadi.getText(), Trespi.getText(), Ttemp.getText(), Tspo.getText(),
                    Tcairan.getText(), Tlain.getText(), Ttindakan.getText(),
                    tbObsPasien.getValueAt(tbObsPasien.getSelectedRow(), 14).toString()
                }) == true) {

            tampilObs2(wktSimpanObs);
            tampilObs1(wktSimpanObs);
            emptTeksObs();
        }
    }
}
