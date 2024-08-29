    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgSpesialis.java
 *
 * Created on May 23, 2010, 1:25:13 AM
 */

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
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import kepegawaian.DlgCariPetugas;
import simrskhanza.DlgCariDokter;
import simrskhanza.DlgNotepad;

/**
 *
 * @author dosen
 */
public class RMProtokolKemoterapi extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1, tabMode2, tabMode3;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps1, ps2, ps3, ps4, psrestor;
    private ResultSet rs, rs1, rs2, rs3, rs4, rsrestor;
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private String nipDokter = "", nipPerawat = "", user = "", dataProtokol = "";
    private int i = 0, x = 0;
    
    /** Creates new form DlgSpesialis
     * @param parent
     * @param modal */
    public RMProtokolKemoterapi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        Object[] row = {"No. Rawat", "No. RM", "Nama Pasien", "Umur (Th.)","Nama Protokol", "Siklus Ke", "Tgl. Siklus",
            "Dosis", "TB", "BB", "LPT", "Diagnosis", "Program", "Nama Dokter", "Nama Perawat", "tgl_siklus",             
            "nipDokter", "nipPerawat", "wktsimpan", "keterangan"};
        
        tabMode=new DefaultTableModel(null,row){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };

        tbProtokol.setModel(tabMode);
        tbProtokol.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbProtokol.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 20; i++) {
            TableColumn column = tbProtokol.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(220);
            } else if (i == 3) {
                column.setPreferredWidth(65);
            } else if (i == 4) {
                column.setPreferredWidth(200);
            } else if (i == 5) {
                column.setPreferredWidth(65);
            } else if (i == 6) {
                column.setPreferredWidth(75);
            } else if (i == 7) {
                column.setPreferredWidth(250);
            } else if (i == 8) {
                column.setPreferredWidth(50);
            } else if (i == 9) {
                column.setPreferredWidth(50);
            } else if (i == 10) {
                column.setPreferredWidth(220);
            } else if (i == 11) {
                column.setPreferredWidth(260);
            } else if (i == 12) {
                column.setPreferredWidth(260);
            } else if (i == 13) {
                column.setPreferredWidth(250);
            } else if (i == 14) {
                column.setPreferredWidth(250);
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
            } else if (i == 19) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbProtokol.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1=new DefaultTableModel(null, new Object[]{"Tgl. Mencatat", "Isi Catatan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbNotepad.setModel(tabMode1);
        tbNotepad.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbNotepad.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 2; i++) {
            TableColumn column = tbNotepad.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(110);
            } else if (i == 1) {
                column.setPreferredWidth(220);
            }
        }
        tbNotepad.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode2 = new DefaultTableModel(null, new Object[]{
            "Dilakukan Oleh", "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Protokol", "Tgl. Eksekusi", "Status Data"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbRiwayat.setModel(tabMode2);
        tbRiwayat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbRiwayat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 7; i++) {
            TableColumn column = tbRiwayat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(250);
            } else if (i == 1) {
                column.setPreferredWidth(105);
            } else if (i == 2) {
                column.setPreferredWidth(65);
            } else if (i == 3) {
                column.setPreferredWidth(200);
            } else if (i == 4) {
                column.setPreferredWidth(130);
            } else if (i == 5) {
                column.setPreferredWidth(130);
            } else if (i == 6) {
                column.setPreferredWidth(80);
            } 
        }
        tbRiwayat.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode3 = new DefaultTableModel(null, new Object[]{"No. RM", "Nama Pasien", "Data Template", "Tgl. Siklus"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbTemplate.setModel(tabMode3);
        tbTemplate.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbTemplate.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 4; i++) {
            TableColumn column = tbTemplate.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(60);
            } else if (i == 1) {
                column.setPreferredWidth(200);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            }
        }
        tbTemplate.setDefaultRenderer(Object.class, new WarnaTable());

        TnmProtokol.setDocument(new batasInput((int) 200).getKata(TnmProtokol));
        Tsiklus.setDocument(new batasInput((byte) 3).getOnlyAngka(Tsiklus));
        Tumur.setDocument(new batasInput((int) 3).getKata(Tumur));
        Ttb.setDocument(new batasInput((int) 3).getKata(Ttb));
        Tbb.setDocument(new batasInput((int) 3).getKata(Tbb));
        TCari.setDocument(new batasInput((int)100).getKata(TCari));
        
        if(koneksiDB.cariCepat().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
            });
        }
        
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {;
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("RMProtokolKemoterapi")) {
                    if (dokter.getTable().getSelectedRow() != -1) {
                        nipDokter = dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString();
                        TnmDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                        BtnDokter.requestFocus();
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
        
        petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if (petugas.getTable().getSelectedRow() != -1) {
                    nipPerawat = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                    TnmPerawat.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                    BtnPerawat.requestFocus();
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
        
        ChkAccor.setSelected(false);
        isMenu();
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
        MnRiwayatData = new javax.swing.JMenuItem();
        MnCetakPemberianCairan = new javax.swing.JMenuItem();
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
        WindowTemplate = new javax.swing.JDialog();
        internalFrame5 = new widget.InternalFrame();
        jPanel1 = new javax.swing.JPanel();
        Scroll1 = new widget.ScrollPane();
        tbTemplate = new widget.Table();
        Scroll4 = new widget.ScrollPane();
        Ttemplate = new widget.TextArea();
        panelisi4 = new widget.panelisi();
        jLabel36 = new widget.Label();
        TCari1 = new widget.TextBox();
        BtnCari1 = new widget.Button();
        BtnCopas = new widget.Button();
        BtnCloseIn1 = new widget.Button();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbProtokol = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        BtnNotepad = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass9 = new widget.panelisi();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        panelGlass7 = new widget.panelisi();
        jLabel3 = new widget.Label();
        TnoRW = new widget.TextBox();
        TnoRM = new widget.TextBox();
        TnmPasien = new widget.TextBox();
        jLabel4 = new widget.Label();
        TnmProtokol = new widget.TextBox();
        jLabel5 = new widget.Label();
        Tsiklus = new widget.TextBox();
        jLabel8 = new widget.Label();
        TtglSiklus = new widget.Tanggal();
        jLabel9 = new widget.Label();
        Scroll23 = new widget.ScrollPane();
        Tdosis = new widget.TextArea();
        jLabel10 = new widget.Label();
        Tumur = new widget.TextBox();
        jLabel11 = new widget.Label();
        jLabel12 = new widget.Label();
        Ttb = new widget.TextBox();
        jLabel13 = new widget.Label();
        jLabel14 = new widget.Label();
        Tbb = new widget.TextBox();
        jLabel15 = new widget.Label();
        jLabel16 = new widget.Label();
        Tlpt = new widget.TextBox();
        jLabel17 = new widget.Label();
        Scroll24 = new widget.ScrollPane();
        Tdiagnosis = new widget.TextArea();
        jLabel18 = new widget.Label();
        Scroll25 = new widget.ScrollPane();
        Tprogram = new widget.TextArea();
        jLabel19 = new widget.Label();
        TnmDokter = new widget.TextBox();
        jLabel20 = new widget.Label();
        TnmPerawat = new widget.TextBox();
        BtnDokter = new widget.Button();
        BtnPerawat = new widget.Button();
        chkSaya = new widget.CekBox();
        jLabel23 = new widget.Label();
        Tket = new widget.TextBox();
        BtnProgram = new widget.Button();
        ChkKunjungan = new widget.CekBox();
        PanelAccor = new widget.PanelBiasa();
        ChkAccor = new widget.CekBox();
        FormMenu = new widget.PanelBiasa();
        scrollPane5 = new widget.ScrollPane();
        Tnotepad = new widget.TextArea();
        Scroll3 = new widget.ScrollPane();
        tbNotepad = new widget.Table();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnRiwayatData.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRiwayatData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRiwayatData.setText("Riwayat Data Terhapus/Diganti");
        MnRiwayatData.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRiwayatData.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRiwayatData.setIconTextGap(5);
        MnRiwayatData.setName("MnRiwayatData"); // NOI18N
        MnRiwayatData.setPreferredSize(new java.awt.Dimension(270, 26));
        MnRiwayatData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRiwayatDataActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnRiwayatData);

        MnCetakPemberianCairan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakPemberianCairan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/PrinterSettings.png"))); // NOI18N
        MnCetakPemberianCairan.setText("Cetak Lembar Pemberian Cairan Kemoterapi");
        MnCetakPemberianCairan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCetakPemberianCairan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCetakPemberianCairan.setIconTextGap(5);
        MnCetakPemberianCairan.setName("MnCetakPemberianCairan"); // NOI18N
        MnCetakPemberianCairan.setPreferredSize(new java.awt.Dimension(270, 26));
        MnCetakPemberianCairan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakPemberianCairanActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnCetakPemberianCairan);

        WindowRiwayat.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowRiwayat.setName("WindowRiwayat"); // NOI18N
        WindowRiwayat.setUndecorated(true);
        WindowRiwayat.setResizable(false);

        internalFrame13.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Riwayat Protokol Kemoterapi Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
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

        DTPCari3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "15-08-2024" }));
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

        DTPCari4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "15-08-2024" }));
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
        Scroll6.setViewportView(tbRiwayat);

        internalFrame13.add(Scroll6, java.awt.BorderLayout.CENTER);

        WindowRiwayat.getContentPane().add(internalFrame13, java.awt.BorderLayout.CENTER);

        WindowTemplate.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowTemplate.setName("WindowTemplate"); // NOI18N
        WindowTemplate.setUndecorated(true);
        WindowTemplate.setResizable(false);

        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Program Kemoterapi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame5.setLayout(new java.awt.BorderLayout());

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(816, 250));
        jPanel1.setLayout(new java.awt.GridLayout(1, 2));

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);
        Scroll1.setPreferredSize(new java.awt.Dimension(452, 250));

        tbTemplate.setAutoCreateRowSorter(true);
        tbTemplate.setToolTipText("Silahkan klik salah satu data yang akan dipakai");
        tbTemplate.setName("tbTemplate"); // NOI18N
        tbTemplate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTemplateMouseClicked(evt);
            }
        });
        Scroll1.setViewportView(tbTemplate);

        jPanel1.add(Scroll1);

        Scroll4.setName("Scroll4"); // NOI18N
        Scroll4.setOpaque(true);

        Ttemplate.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Baca Template Dipilih ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Ttemplate.setColumns(20);
        Ttemplate.setRows(5);
        Ttemplate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Ttemplate.setName("Ttemplate"); // NOI18N
        Ttemplate.setPreferredSize(new java.awt.Dimension(210, 4000));
        Scroll4.setViewportView(Ttemplate);

        jPanel1.add(Scroll4);

        internalFrame5.add(jPanel1, java.awt.BorderLayout.CENTER);

        panelisi4.setBackground(new java.awt.Color(255, 150, 255));
        panelisi4.setName("panelisi4"); // NOI18N
        panelisi4.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 9));

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Key Word :");
        jLabel36.setName("jLabel36"); // NOI18N
        jLabel36.setPreferredSize(new java.awt.Dimension(70, 23));
        jLabel36.setRequestFocusEnabled(false);
        panelisi4.add(jLabel36);

        TCari1.setForeground(new java.awt.Color(0, 0, 0));
        TCari1.setName("TCari1"); // NOI18N
        TCari1.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari1KeyPressed(evt);
            }
        });
        panelisi4.add(TCari1);

        BtnCari1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari1.setMnemonic('1');
        BtnCari1.setText("Tampilkan Data");
        BtnCari1.setToolTipText("Alt+1");
        BtnCari1.setName("BtnCari1"); // NOI18N
        BtnCari1.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari1ActionPerformed(evt);
            }
        });
        BtnCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCari1KeyPressed(evt);
            }
        });
        panelisi4.add(BtnCari1);

        BtnCopas.setForeground(new java.awt.Color(0, 0, 0));
        BtnCopas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/paste.png"))); // NOI18N
        BtnCopas.setMnemonic('U');
        BtnCopas.setText("Copy & Paste");
        BtnCopas.setToolTipText("Alt+U");
        BtnCopas.setName("BtnCopas"); // NOI18N
        BtnCopas.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnCopas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCopasActionPerformed(evt);
            }
        });
        panelisi4.add(BtnCopas);

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
        panelisi4.add(BtnCloseIn1);

        internalFrame5.add(panelisi4, java.awt.BorderLayout.PAGE_END);

        WindowTemplate.getContentPane().add(internalFrame5, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Protokol Kemoterapi Hematologi & Onkologi Medik ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setComponentPopupMenu(jPopupMenu1);
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbProtokol.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbProtokol.setComponentPopupMenu(jPopupMenu1);
        tbProtokol.setName("tbProtokol"); // NOI18N
        tbProtokol.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbProtokolMouseClicked(evt);
            }
        });
        tbProtokol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbProtokolKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbProtokol);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 100));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setComponentPopupMenu(jPopupMenu1);
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

        panelGlass9.setComponentPopupMenu(jPopupMenu1);
        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 3, 9));

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('1');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+1");
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

        BtnAll.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('2');
        BtnAll.setText("Semua Data");
        BtnAll.setToolTipText("Alt+2");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(120, 23));
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
        panelGlass9.add(BtnAll);

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

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        panelGlass7.setComponentPopupMenu(jPopupMenu1);
        panelGlass7.setName("panelGlass7"); // NOI18N
        panelGlass7.setPreferredSize(new java.awt.Dimension(44, 360));
        panelGlass7.setLayout(null);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Pasien  : ");
        jLabel3.setName("jLabel3"); // NOI18N
        panelGlass7.add(jLabel3);
        jLabel3.setBounds(2, 10, 110, 23);

        TnoRW.setEditable(false);
        TnoRW.setForeground(new java.awt.Color(0, 0, 0));
        TnoRW.setName("TnoRW"); // NOI18N
        panelGlass7.add(TnoRW);
        TnoRW.setBounds(115, 10, 131, 23);

        TnoRM.setEditable(false);
        TnoRM.setForeground(new java.awt.Color(0, 0, 0));
        TnoRM.setName("TnoRM"); // NOI18N
        panelGlass7.add(TnoRM);
        TnoRM.setBounds(250, 10, 70, 23);

        TnmPasien.setEditable(false);
        TnmPasien.setForeground(new java.awt.Color(0, 0, 0));
        TnmPasien.setName("TnmPasien"); // NOI18N
        panelGlass7.add(TnmPasien);
        TnmPasien.setBounds(323, 10, 347, 23);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Nama Protokol : ");
        jLabel4.setName("jLabel4"); // NOI18N
        panelGlass7.add(jLabel4);
        jLabel4.setBounds(2, 38, 110, 23);

        TnmProtokol.setForeground(new java.awt.Color(0, 0, 0));
        TnmProtokol.setName("TnmProtokol"); // NOI18N
        TnmProtokol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmProtokolKeyPressed(evt);
            }
        });
        panelGlass7.add(TnmProtokol);
        TnmProtokol.setBounds(115, 38, 555, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Siklus Ke : ");
        jLabel5.setName("jLabel5"); // NOI18N
        panelGlass7.add(jLabel5);
        jLabel5.setBounds(2, 66, 110, 23);

        Tsiklus.setForeground(new java.awt.Color(0, 0, 0));
        Tsiklus.setName("Tsiklus"); // NOI18N
        Tsiklus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TsiklusKeyPressed(evt);
            }
        });
        panelGlass7.add(Tsiklus);
        Tsiklus.setBounds(115, 66, 44, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Tgl. Siklus : ");
        jLabel8.setName("jLabel8"); // NOI18N
        panelGlass7.add(jLabel8);
        jLabel8.setBounds(160, 66, 70, 23);

        TtglSiklus.setForeground(new java.awt.Color(50, 70, 50));
        TtglSiklus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "15-08-2024" }));
        TtglSiklus.setDisplayFormat("dd-MM-yyyy");
        TtglSiklus.setName("TtglSiklus"); // NOI18N
        TtglSiklus.setOpaque(false);
        TtglSiklus.setPreferredSize(new java.awt.Dimension(100, 23));
        panelGlass7.add(TtglSiklus);
        TtglSiklus.setBounds(233, 66, 90, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Dosis : ");
        jLabel9.setName("jLabel9"); // NOI18N
        panelGlass7.add(jLabel9);
        jLabel9.setBounds(2, 94, 110, 23);

        Scroll23.setName("Scroll23"); // NOI18N
        Scroll23.setOpaque(true);

        Tdosis.setColumns(20);
        Tdosis.setRows(5);
        Tdosis.setName("Tdosis"); // NOI18N
        Tdosis.setPreferredSize(new java.awt.Dimension(190, 2000));
        Tdosis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdosisKeyPressed(evt);
            }
        });
        Scroll23.setViewportView(Tdosis);

        panelGlass7.add(Scroll23);
        Scroll23.setBounds(115, 94, 555, 130);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Umur : ");
        jLabel10.setName("jLabel10"); // NOI18N
        panelGlass7.add(jLabel10);
        jLabel10.setBounds(2, 230, 110, 23);

        Tumur.setForeground(new java.awt.Color(0, 0, 0));
        Tumur.setName("Tumur"); // NOI18N
        Tumur.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TumurKeyPressed(evt);
            }
        });
        panelGlass7.add(Tumur);
        Tumur.setBounds(115, 230, 44, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setText("Thn.");
        jLabel11.setName("jLabel11"); // NOI18N
        panelGlass7.add(jLabel11);
        jLabel11.setBounds(165, 230, 30, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Tinggi Badan : ");
        jLabel12.setName("jLabel12"); // NOI18N
        panelGlass7.add(jLabel12);
        jLabel12.setBounds(202, 230, 90, 23);

        Ttb.setForeground(new java.awt.Color(0, 0, 0));
        Ttb.setName("Ttb"); // NOI18N
        Ttb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtbKeyPressed(evt);
            }
        });
        panelGlass7.add(Ttb);
        Ttb.setBounds(295, 230, 44, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("Cm.");
        jLabel13.setName("jLabel13"); // NOI18N
        panelGlass7.add(jLabel13);
        jLabel13.setBounds(345, 230, 30, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Berat Badan : ");
        jLabel14.setName("jLabel14"); // NOI18N
        panelGlass7.add(jLabel14);
        jLabel14.setBounds(372, 230, 80, 23);

        Tbb.setForeground(new java.awt.Color(0, 0, 0));
        Tbb.setName("Tbb"); // NOI18N
        Tbb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbbKeyPressed(evt);
            }
        });
        panelGlass7.add(Tbb);
        Tbb.setBounds(455, 230, 44, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setText("Kg.");
        jLabel15.setName("jLabel15"); // NOI18N
        panelGlass7.add(jLabel15);
        jLabel15.setBounds(505, 230, 30, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("LPT : ");
        jLabel16.setName("jLabel16"); // NOI18N
        panelGlass7.add(jLabel16);
        jLabel16.setBounds(2, 258, 110, 23);

        Tlpt.setForeground(new java.awt.Color(0, 0, 0));
        Tlpt.setName("Tlpt"); // NOI18N
        Tlpt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlptKeyPressed(evt);
            }
        });
        panelGlass7.add(Tlpt);
        Tlpt.setBounds(115, 258, 555, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Diagnosis : ");
        jLabel17.setName("jLabel17"); // NOI18N
        panelGlass7.add(jLabel17);
        jLabel17.setBounds(2, 286, 110, 23);

        Scroll24.setName("Scroll24"); // NOI18N
        Scroll24.setOpaque(true);

        Tdiagnosis.setColumns(20);
        Tdiagnosis.setRows(5);
        Tdiagnosis.setName("Tdiagnosis"); // NOI18N
        Tdiagnosis.setPreferredSize(new java.awt.Dimension(190, 2000));
        Tdiagnosis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdiagnosisKeyPressed(evt);
            }
        });
        Scroll24.setViewportView(Tdiagnosis);

        panelGlass7.add(Scroll24);
        Scroll24.setBounds(115, 286, 555, 60);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Program : ");
        jLabel18.setName("jLabel18"); // NOI18N
        panelGlass7.add(jLabel18);
        jLabel18.setBounds(680, 10, 110, 23);

        Scroll25.setName("Scroll25"); // NOI18N
        Scroll25.setOpaque(true);

        Tprogram.setColumns(20);
        Tprogram.setRows(5);
        Tprogram.setName("Tprogram"); // NOI18N
        Tprogram.setPreferredSize(new java.awt.Dimension(190, 2000));
        Tprogram.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TprogramKeyPressed(evt);
            }
        });
        Scroll25.setViewportView(Tprogram);

        panelGlass7.add(Scroll25);
        Scroll25.setBounds(793, 10, 555, 252);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Nama Dokter : ");
        jLabel19.setName("jLabel19"); // NOI18N
        panelGlass7.add(jLabel19);
        jLabel19.setBounds(680, 267, 110, 23);

        TnmDokter.setEditable(false);
        TnmDokter.setForeground(new java.awt.Color(0, 0, 0));
        TnmDokter.setName("TnmDokter"); // NOI18N
        panelGlass7.add(TnmDokter);
        TnmDokter.setBounds(793, 267, 530, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Perawat Ruangan : ");
        jLabel20.setName("jLabel20"); // NOI18N
        panelGlass7.add(jLabel20);
        jLabel20.setBounds(680, 295, 110, 23);

        TnmPerawat.setEditable(false);
        TnmPerawat.setForeground(new java.awt.Color(0, 0, 0));
        TnmPerawat.setName("TnmPerawat"); // NOI18N
        panelGlass7.add(TnmPerawat);
        TnmPerawat.setBounds(793, 295, 420, 23);

        BtnDokter.setForeground(new java.awt.Color(0, 0, 0));
        BtnDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter.setMnemonic('X');
        BtnDokter.setToolTipText("Alt+X");
        BtnDokter.setName("BtnDokter"); // NOI18N
        BtnDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokterActionPerformed(evt);
            }
        });
        panelGlass7.add(BtnDokter);
        BtnDokter.setBounds(1323, 267, 30, 23);

        BtnPerawat.setForeground(new java.awt.Color(0, 0, 0));
        BtnPerawat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPerawat.setMnemonic('1');
        BtnPerawat.setToolTipText("Alt+1");
        BtnPerawat.setName("BtnPerawat"); // NOI18N
        BtnPerawat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPerawatActionPerformed(evt);
            }
        });
        panelGlass7.add(BtnPerawat);
        BtnPerawat.setBounds(1215, 295, 28, 23);

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
        panelGlass7.add(chkSaya);
        chkSaya.setBounds(1250, 295, 90, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Keterangan : ");
        jLabel23.setName("jLabel23"); // NOI18N
        panelGlass7.add(jLabel23);
        jLabel23.setBounds(680, 323, 110, 23);

        Tket.setForeground(new java.awt.Color(0, 0, 0));
        Tket.setName("Tket"); // NOI18N
        panelGlass7.add(Tket);
        Tket.setBounds(793, 323, 530, 23);

        BtnProgram.setForeground(new java.awt.Color(0, 0, 0));
        BtnProgram.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnProgram.setMnemonic('2');
        BtnProgram.setText("Template");
        BtnProgram.setToolTipText("Alt+2");
        BtnProgram.setName("BtnProgram"); // NOI18N
        BtnProgram.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnProgram.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnProgramActionPerformed(evt);
            }
        });
        panelGlass7.add(BtnProgram);
        BtnProgram.setBounds(1357, 10, 100, 23);

        ChkKunjungan.setBackground(new java.awt.Color(255, 255, 250));
        ChkKunjungan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkKunjungan.setForeground(new java.awt.Color(0, 0, 0));
        ChkKunjungan.setText("Protokol Kemoterapi Untuk Kunjungan Saat Ini");
        ChkKunjungan.setBorderPainted(true);
        ChkKunjungan.setBorderPaintedFlat(true);
        ChkKunjungan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkKunjungan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkKunjungan.setName("ChkKunjungan"); // NOI18N
        ChkKunjungan.setOpaque(false);
        ChkKunjungan.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkKunjungan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkKunjunganActionPerformed(evt);
            }
        });
        panelGlass7.add(ChkKunjungan);
        ChkKunjungan.setBounds(330, 66, 340, 23);

        internalFrame1.add(panelGlass7, java.awt.BorderLayout.PAGE_START);

        PanelAccor.setBackground(new java.awt.Color(255, 255, 255));
        PanelAccor.setName("PanelAccor"); // NOI18N
        PanelAccor.setPreferredSize(new java.awt.Dimension(900, 43));
        PanelAccor.setLayout(new java.awt.BorderLayout());

        ChkAccor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/2rightarrow.png"))); // NOI18N
        ChkAccor.setToolTipText("Silahkan Klik Untuk Membaca Catatan pada notepad SIMRS");
        ChkAccor.setFocusable(false);
        ChkAccor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkAccor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkAccor.setName("ChkAccor"); // NOI18N
        ChkAccor.setPreferredSize(new java.awt.Dimension(22, 20));
        ChkAccor.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/2rightarrow.png"))); // NOI18N
        ChkAccor.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/2leftarrow.png"))); // NOI18N
        ChkAccor.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/2leftarrow.png"))); // NOI18N
        ChkAccor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkAccorActionPerformed(evt);
            }
        });
        PanelAccor.add(ChkAccor, java.awt.BorderLayout.WEST);

        FormMenu.setBackground(new java.awt.Color(250, 250, 245));
        FormMenu.setName("FormMenu"); // NOI18N
        FormMenu.setPreferredSize(new java.awt.Dimension(150, 483));
        FormMenu.setLayout(new java.awt.GridLayout(1, 2));

        scrollPane5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Isi Catatan ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        scrollPane5.setName("scrollPane5"); // NOI18N
        scrollPane5.setPreferredSize(new java.awt.Dimension(212, 250));

        Tnotepad.setEditable(false);
        Tnotepad.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tnotepad.setColumns(20);
        Tnotepad.setRows(5);
        Tnotepad.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Tnotepad.setName("Tnotepad"); // NOI18N
        Tnotepad.setPreferredSize(new java.awt.Dimension(202, 4000));
        scrollPane5.setViewportView(Tnotepad);

        FormMenu.add(scrollPane5);

        Scroll3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Notepad SIMRS ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);

        tbNotepad.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbNotepad.setName("tbNotepad"); // NOI18N
        tbNotepad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbNotepadMouseClicked(evt);
            }
        });
        tbNotepad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbNotepadKeyPressed(evt);
            }
        });
        Scroll3.setViewportView(tbNotepad);

        FormMenu.add(Scroll3);

        PanelAccor.add(FormMenu, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelAccor, java.awt.BorderLayout.EAST);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TnoRW.getText().equals("")) {
            Valid.textKosong(TnoRW, "Pasien");            
        } else if (Tsiklus.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Siklus kemoterapinya harus diisi dulu..!!");
            Tsiklus.requestFocus();
        } else if (Tsiklus.getText().equals("0") || Tsiklus.getText().equals("00") || Tsiklus.getText().equals("000")) {
            JOptionPane.showMessageDialog(rootPane, "Siklus kemoterapinya tidak valid, silahkan ulangi lagi..!!");
            Tsiklus.requestFocus();
        } else {
            if (Sequel.cariInteger("select count(-1) from protokol_kemoterapi where no_rkm_medis='" + TnoRM.getText() + "' and siklus_ke='" + Tsiklus.getText() + "'") > 0) {
                JOptionPane.showMessageDialog(rootPane, "Siklus ke " + Tsiklus.getText() + " kemoterapi pasien ini sudah ada..!!");
                Tsiklus.requestFocus();
            } else {
                Sequel.menyimpan("protokol_kemoterapi", "'" + TnoRW.getText() + "','" + TnmProtokol.getText() + "',"
                        + "'" + Tsiklus.getText() + "','" + Valid.SetTgl(TtglSiklus.getSelectedItem() + "") + "','" + Tdosis.getText() + "',"
                        + "'" + Ttb.getText() + "','" + Tbb.getText() + "','" + Tlpt.getText() + "','" + Tdiagnosis.getText() + "',"
                        + "'" + Tprogram.getText() + "','" + nipPerawat + "','" + nipDokter + "','" + Sequel.cariIsi("select now()") + "',"
                        + "'" + TnoRM.getText() + "','" + Tket.getText() + "'", "Protokol Kemoterapi");
                
                TCari.setText(TnoRM.getText());
                tampil();
                emptTeks();
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

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnHapus);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbProtokol.getSelectedRow() > -1) {
            if (akses.getadmin() == true) {
                hapus();
            } else {
                if (akses.getkode().equals(tbProtokol.getValueAt(tbProtokol.getSelectedRow(), 16).toString())) {
                    hapus();
                } else {
                    JOptionPane.showMessageDialog(null, "Hanya bisa dihapus " + TnmDokter.getText() + "..!!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih datanya terlebih dulu..!!");
            tbProtokol.requestFocus();
        }
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnHapusActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (TnoRW.getText().trim().equals("")) {
            Valid.textKosong(TnoRW, "Pasien");
        } else if (Tsiklus.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Siklus kemoterapinya harus diisi dulu..!!");
            Tsiklus.requestFocus();
        } else if (Tsiklus.getText().equals("0") || Tsiklus.getText().equals("00") || Tsiklus.getText().equals("000")) {
            JOptionPane.showMessageDialog(rootPane, "Siklus kemoterapinya tidak valid, silahkan ulangi lagi..!!");
            Tsiklus.requestFocus();
        } else {
            if (tbProtokol.getSelectedRow() > -1) {
                gantiDisimpan();
                Sequel.mengedit("protokol_kemoterapi", "no_rawat='" + TnoRW.getText() + "'",
                        "nm_protokol='" + TnmProtokol.getText() + "',siklus_ke='" + Tsiklus.getText() + "',"
                        + "tgl_siklus='" + Valid.SetTgl(TtglSiklus.getSelectedItem() + "") + "',dosis='" + Tdosis.getText() + "',"
                        + "tb='" + Ttb.getText() + "',bb='" + Tbb.getText() + "',lpt='" + Tlpt.getText() + "',diagnosis='" + Tdiagnosis.getText() + "',"
                        + "program='" + Tprogram.getText() + "',nip_perawat='" + nipPerawat + "',nip_dokter='" + nipDokter + "',"
                        + "no_rkm_medis='" + TnoRM.getText() + "',keterangan='" + Tket.getText() + "'");

                TCari.setText(TnoRM.getText());
                tampil();
                emptTeks();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih datanya terlebih dulu..!!");
                tbProtokol.requestFocus();
            }
        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnEditActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnKeluar);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
        WindowRiwayat.dispose();
        WindowTemplate.dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnEdit, TCari);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            tbProtokol.requestFocus();
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
       emptTeks();
       tampil();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAllActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnCari, TnoRW);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbProtokolMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbProtokolMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbProtokolMouseClicked

    private void tbProtokolKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbProtokolKeyPressed
        if(tabMode.getRowCount()!=0){
            if(evt.getKeyCode()==KeyEvent.VK_SHIFT){
                TCari.setText("");
                TCari.requestFocus();
            }           
        }
}//GEN-LAST:event_tbProtokolKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void TdosisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdosisKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Tumur.requestFocus();
        }
    }//GEN-LAST:event_TdosisKeyPressed

    private void TdiagnosisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdiagnosisKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Tprogram.requestFocus();
        }
    }//GEN-LAST:event_TdiagnosisKeyPressed

    private void TprogramKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TprogramKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            BtnDokter.requestFocus();
        }
    }//GEN-LAST:event_TprogramKeyPressed

    private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
        akses.setform("RMProtokolKemoterapi");
        dokter.isCek();
        dokter.TCari.requestFocus();
        dokter.setSize(1045, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);
        dokter.emptTeks();
    }//GEN-LAST:event_BtnDokterActionPerformed

    private void BtnPerawatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPerawatActionPerformed
        akses.setform("RMProtokolKemoterapi");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPerawatActionPerformed

    private void TnmProtokolKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmProtokolKeyPressed
        Valid.pindah(evt, TnmProtokol, Tsiklus);
    }//GEN-LAST:event_TnmProtokolKeyPressed

    private void TsiklusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TsiklusKeyPressed
        Valid.pindah(evt, TnmProtokol, TtglSiklus);
    }//GEN-LAST:event_TsiklusKeyPressed

    private void TumurKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TumurKeyPressed
        Valid.pindah(evt, Tdosis, Ttb);
    }//GEN-LAST:event_TumurKeyPressed

    private void TtbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtbKeyPressed
        Valid.pindah(evt, Tumur, Tbb);
    }//GEN-LAST:event_TtbKeyPressed

    private void TbbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbbKeyPressed
        Valid.pindah(evt, Tbb, Tlpt);
    }//GEN-LAST:event_TbbKeyPressed

    private void TlptKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlptKeyPressed
        Valid.pindah(evt, Tbb, Tdiagnosis);
    }//GEN-LAST:event_TlptKeyPressed

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

    private void ChkAccorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkAccorActionPerformed
        isMenu();
    }//GEN-LAST:event_ChkAccorActionPerformed

    private void tbNotepadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbNotepadMouseClicked
        if (tabMode1.getRowCount() != 0) {
            try {
                Tnotepad.setText(tbNotepad.getValueAt(tbNotepad.getSelectedRow(), 1).toString());
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbNotepadMouseClicked

    private void tbNotepadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbNotepadKeyPressed
        if (tabMode1.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    Tnotepad.setText(tbNotepad.getValueAt(tbNotepad.getSelectedRow(), 1).toString());
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbNotepadKeyPressed

    private void BtnNotepadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNotepadActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        akses.setform("RMProtokolKemoterapi");
        DlgNotepad form = new DlgNotepad(null, false);
        form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        form.setLocationRelativeTo(internalFrame1);
        form.setData(akses.getkode());
        form.setVisible(true);
        ChkAccor.setSelected(false);
        isMenu();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnNotepadActionPerformed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TnoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu datanya pada tabel...!!!!");
        } else if (Sequel.cariInteger("select count(-1) from protokol_kemoterapi where no_rawat='" + TnoRW.getText() + "' "
                + "and tgl_siklus='" + Valid.SetTgl(TtglSiklus.getSelectedItem() + "") + "'") == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu datanya pada tabel...!!!!");
            tbProtokol.requestFocus();
        } else {
            if (tbProtokol.getSelectedRow() > -1) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Map<String, Object> param = new HashMap<>();
                param.put("namars", akses.getnamars());
                param.put("logo", Sequel.cariGambar("select logo from setting"));
                param.put("norm", TnoRM.getText());
                param.put("nmpasien", TnmPasien.getText());
                param.put("tgllahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d/%m/%Y') from pasien where no_rkm_medis='" + TnoRM.getText() + "'"));
                param.put("nmprotokol", TnmProtokol.getText());
                param.put("siklus", Tsiklus.getText());
                param.put("tglkemoterapi", Valid.SetTglINDONESIA(tbProtokol.getValueAt(tbProtokol.getSelectedRow(), 15).toString()));
                param.put("nmperawat", TnmPerawat.getText());
                param.put("nmdokter", TnmDokter.getText());
                param.put("dosis", Tdosis.getText());
                protokolKemoterapi();
                param.put("protokol", dataProtokol);

                Valid.MyReport("rptProtokolKemoterapi.jasper", "report", "::[ Cetak Protokol Kemoterapi ]::",
                        "SELECT now() tgl", param);
                this.setCursor(Cursor.getDefaultCursor());

                TCari.setText(TnoRM.getText());
                tampil();
                emptTeks();
            } else {
                JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih datanya terlebih dulu pada tabel..!!!!");
                tampil();
                tbProtokol.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void MnRiwayatDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRiwayatDataActionPerformed
        ChkAccor.setSelected(false);
        isMenu();

        DTPCari3.setDate(new Date());
        DTPCari4.setDate(new Date());
        TCari2.setText(TnoRM.getText());
        BtnCari2ActionPerformed(null);
        WindowRiwayat.setSize(985, internalFrame1.getHeight() - 40);
        WindowRiwayat.setLocationRelativeTo(internalFrame1);
        WindowRiwayat.setAlwaysOnTop(false);
        WindowRiwayat.setVisible(true);
    }//GEN-LAST:event_MnRiwayatDataActionPerformed

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
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data yang dipilih & telah " + tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 6).toString()
                    + " akan dikembalikan/restore..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 6).toString().equals("DIHAPUS")) {
                    if (Sequel.cariInteger("select count(-1) from protokol_kemoterapi where "
                            + "no_rawat='" + tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 1).toString() + "'") > 0) {
                        JOptionPane.showMessageDialog(rootPane, "Proses kembalikan/restore data gagal, krn. sudah ada datanya dg. no. rawat yg. sama..!!");
                    } else {
                        kembalikanData();
                        TCari.setText(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 1).toString());
                        BtnCloseIn10ActionPerformed(null);
                        tampil();
                        emptTeks();
                    }
                } else {
                    kembalikanDataDiganti();
                    TCari.setText(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 1).toString());
                    BtnCloseIn10ActionPerformed(null);
                    tampil();
                    emptTeks();
                }
            }
        } else {
            WindowRiwayat.setSize(1043, internalFrame1.getHeight() - 40);
            WindowRiwayat.setLocationRelativeTo(internalFrame1);
            WindowRiwayat.setAlwaysOnTop(false);
            WindowRiwayat.setVisible(true);
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih salah satu datanya terlebih dahulu..!!");
        }
    }//GEN-LAST:event_BtnRestorActionPerformed

    private void BtnCloseIn10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn10ActionPerformed
        WindowRiwayat.dispose();
        TCari2.setText("");
    }//GEN-LAST:event_BtnCloseIn10ActionPerformed

    private void tbTemplateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTemplateMouseClicked
        if(tabMode3.getRowCount() != 0) {
            try {
                if (tbTemplate.getSelectedRow() != -1) {
                    Ttemplate.setText(tbTemplate.getValueAt(tbTemplate.getSelectedRow(), 2).toString());
                }
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbTemplateMouseClicked

    private void TCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari1ActionPerformed(null);
        }
    }//GEN-LAST:event_TCari1KeyPressed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        tampilTemplate();
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void BtnCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCari1ActionPerformed(null);
        }
    }//GEN-LAST:event_BtnCari1KeyPressed

    private void BtnCopasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCopasActionPerformed
        x = JOptionPane.showConfirmDialog(rootPane, "Apakah data ini akan dipakai..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            Tprogram.setText(Ttemplate.getText());
            WindowTemplate.dispose();
        }
    }//GEN-LAST:event_BtnCopasActionPerformed

    private void BtnCloseIn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn1ActionPerformed
        WindowTemplate.dispose();
    }//GEN-LAST:event_BtnCloseIn1ActionPerformed

    private void BtnProgramActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnProgramActionPerformed
        Ttemplate.setText("");
        TCari1.setText("");

        WindowTemplate.setSize(998, internalFrame1.getHeight() - 40);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.setText(TnoRM.getText());
        TCari1.requestFocus();
        tampilTemplate();
    }//GEN-LAST:event_BtnProgramActionPerformed

    private void MnCetakPemberianCairanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakPemberianCairanActionPerformed
        if (tbProtokol.getSelectedRow() > -1) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("norm", TnoRM.getText());
            param.put("nmpasien", TnmPasien.getText());
            param.put("tgllahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d/%m/%Y') from pasien where no_rkm_medis='" + TnoRM.getText() + "'"));
            param.put("program", Tprogram.getText());

            Valid.MyReport("rptPemberianCairanKemoterapi.jasper", "report", "::[ Cetak Lembar Pemberian Cairan Kemoterapi ]::",
                    "select date(now()) tgl", param);
            this.setCursor(Cursor.getDefaultCursor());

            TCari.setText(TnoRM.getText());
            tampil();
            emptTeks();
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih datanya terlebih dulu pada tabel..!!!!");
            tampil();
            tbProtokol.requestFocus();
        }
    }//GEN-LAST:event_MnCetakPemberianCairanActionPerformed

    private void ChkKunjunganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkKunjunganActionPerformed
        if (ChkKunjungan.isSelected() == true) {
            TnoRW.setText(Sequel.cariIsi("select no_rawat from reg_periksa where no_rkm_medis='" + TnoRM.getText() + "' order by tgl_registrasi desc, jam_reg desc limit 1"));
        } else if (ChkKunjungan.isSelected() == false) {
            if (tbProtokol.getRowCount() == 0) {
                TnoRW.setText(TnoRW.getText());
            } else {
                TnoRW.setText(tbProtokol.getValueAt(tbProtokol.getSelectedRow(), 0).toString());
            }
        }
    }//GEN-LAST:event_ChkKunjunganActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMProtokolKemoterapi dialog = new RMProtokolKemoterapi(new javax.swing.JFrame(), true);
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
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnCari1;
    private widget.Button BtnCari2;
    private widget.Button BtnCloseIn1;
    private widget.Button BtnCloseIn10;
    private widget.Button BtnCopas;
    public widget.Button BtnDokter;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnNotepad;
    private widget.Button BtnPerawat;
    private widget.Button BtnPrint;
    private widget.Button BtnProgram;
    private widget.Button BtnRestor;
    private widget.Button BtnSimpan;
    public widget.CekBox ChkAccor;
    public widget.CekBox ChkKunjungan;
    private widget.Tanggal DTPCari3;
    private widget.Tanggal DTPCari4;
    private widget.PanelBiasa FormMenu;
    private widget.Label LCount;
    private widget.Label LCount1;
    private javax.swing.JMenuItem MnCetakPemberianCairan;
    private javax.swing.JMenuItem MnRiwayatData;
    private widget.PanelBiasa PanelAccor;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll23;
    private widget.ScrollPane Scroll24;
    private widget.ScrollPane Scroll25;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll4;
    private widget.ScrollPane Scroll6;
    private widget.TextBox TCari;
    private widget.TextBox TCari1;
    private widget.TextBox TCari2;
    private widget.TextBox Tbb;
    private widget.TextArea Tdiagnosis;
    private widget.TextArea Tdosis;
    private widget.TextBox Tket;
    private widget.TextBox Tlpt;
    private widget.TextBox TnmDokter;
    private widget.TextBox TnmPasien;
    private widget.TextBox TnmPerawat;
    private widget.TextBox TnmProtokol;
    private widget.TextBox TnoRM;
    private widget.TextBox TnoRW;
    private widget.TextArea Tnotepad;
    private widget.TextArea Tprogram;
    private widget.TextBox Tsiklus;
    private widget.TextBox Ttb;
    private widget.TextArea Ttemplate;
    private widget.Tanggal TtglSiklus;
    private widget.TextBox Tumur;
    private javax.swing.JDialog WindowRiwayat;
    private javax.swing.JDialog WindowTemplate;
    private widget.CekBox chkSaya;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame13;
    private widget.InternalFrame internalFrame17;
    private widget.InternalFrame internalFrame18;
    private widget.InternalFrame internalFrame19;
    private widget.InternalFrame internalFrame5;
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
    private widget.Label jLabel23;
    private widget.Label jLabel3;
    private widget.Label jLabel30;
    private widget.Label jLabel31;
    private widget.Label jLabel32;
    private widget.Label jLabel33;
    private widget.Label jLabel36;
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.panelisi panelGlass7;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.panelisi panelisi4;
    private widget.ScrollPane scrollPane5;
    private widget.Table tbNotepad;
    private widget.Table tbProtokol;
    private widget.Table tbRiwayat;
    private widget.Table tbTemplate;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            if (TCari.getText().trim().equals("")) {
                ps = koneksi.prepareStatement("SELECT pk.no_rawat, p.no_rkm_medis, p.nm_pasien, pk.nm_protokol, pk.siklus_ke, date_format(pk.tgl_siklus,'%d/%m/%Y') tglkemo, "
                        + "pk.dosis, pk.tb, pk.bb, pk.lpt, pk.diagnosis, pk.program, pk.nip_perawat, pk.nip_dokter, pk.waktu_simpan, pg1.nama nmperawat, "
                        + "pg2.nama nmdokter, pk.tgl_siklus, rp.umurdaftar, pk.keterangan from protokol_kemoterapi pk "
                        + "inner join reg_periksa rp on rp.no_rawat=pk.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                        + "inner join pegawai pg1 on pg1.nik=pk.nip_perawat inner join pegawai pg2 on pg2.nik=pk.nip_dokter where "
                        + "pk.no_rawat like ? or "
                        + "p.no_rkm_medis like ? or "
                        + "p.nm_pasien like ? or "
                        + "pk.nm_protokol like ? or "
                        + "pk.keterangan like ? or "
                        + "pg1.nama like ? order by pk.siklus_ke desc limit 100");
            } else {
                ps = koneksi.prepareStatement("SELECT pk.no_rawat, p.no_rkm_medis, p.nm_pasien, pk.nm_protokol, pk.siklus_ke, date_format(pk.tgl_siklus,'%d/%m/%Y') tglkemo, "
                        + "pk.dosis, pk.tb, pk.bb, pk.lpt, pk.diagnosis, pk.program, pk.nip_perawat, pk.nip_dokter, pk.waktu_simpan, pg1.nama nmperawat, "
                        + "pg2.nama nmdokter, pk.tgl_siklus, rp.umurdaftar, pk.keterangan from protokol_kemoterapi pk "
                        + "inner join reg_periksa rp on rp.no_rawat=pk.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                        + "inner join pegawai pg1 on pg1.nik=pk.nip_perawat inner join pegawai pg2 on pg2.nik=pk.nip_dokter where "
                        + "pk.no_rawat like ? or "
                        + "p.no_rkm_medis like ? or "
                        + "p.nm_pasien like ? or "
                        + "pk.nm_protokol like ? or "
                        + "pk.keterangan like ? or "
                        + "pg1.nama like ? order by pk.siklus_ke desc");
            }            
            try {
                ps.setString(1, "%" + TCari.getText().trim() + "%");
                ps.setString(2, "%" + TCari.getText().trim() + "%");
                ps.setString(3, "%" + TCari.getText().trim() + "%");
                ps.setString(4, "%" + TCari.getText().trim() + "%");
                ps.setString(5, "%" + TCari.getText().trim() + "%");
                ps.setString(6, "%" + TCari.getText().trim() + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"), 
                        rs.getString("no_rkm_medis"),                        
                        rs.getString("nm_pasien"),
                        rs.getString("umurdaftar"),
                        rs.getString("nm_protokol"),
                        rs.getString("siklus_ke"),
                        rs.getString("tglkemo"),
                        rs.getString("dosis"),
                        rs.getString("tb"),
                        rs.getString("bb"),
                        rs.getString("lpt"),
                        rs.getString("diagnosis"),
                        rs.getString("program"),
                        rs.getString("nmdokter"),
                        rs.getString("nmperawat"),
                        rs.getString("tgl_siklus"),
                        rs.getString("nip_dokter"),
                        rs.getString("nip_perawat"),
                        rs.getString("waktu_simpan"),
                        rs.getString("keterangan")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
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
        LCount.setText("" + tabMode.getRowCount());
    }

    public void emptTeks() {
        TnmProtokol.setText("");
        Tsiklus.setText(Sequel.cariIsi("select ifnull(MAX(siklus_ke)+1,1) from protokol_kemoterapi where no_rkm_medis='" + TnoRM.getText() + "'"));
        TtglSiklus.setDate(new Date());
        Tdosis.setText("");
        Tumur.setText(Sequel.cariIsi("select umurdaftar from reg_periksa where no_rawat='" + TnoRW.getText() + "' and sttsumur='Th'"));
        Ttb.setText(Sequel.cariIsi("select ifnull(tb,'') from penilaian_awal_keperawatan_dewasa_ranap where no_rawat='" + TnoRW.getText() + "'"));
        Tbb.setText(Sequel.cariIsi("select ifnull(bb_msk_rs,'') from penilaian_awal_keperawatan_dewasa_ranap where no_rawat='" + TnoRW.getText() + "'"));
        Tlpt.setText("");
        Tdiagnosis.setText("");
        Tprogram.setText("");
        nipDokter = "D0000020";
        TnmDokter.setText(Sequel.cariIsi("select ifnull(nm_dokter,'') from dokter where kd_dokter='" + nipDokter + "'"));
        nipPerawat = "-";
        TnmPerawat.setText("-");
        chkSaya.setSelected(false);
        Tket.setText("");
        ChkKunjungan.setSelected(false);
    }

    private void getData() {
        nipDokter = "";
        nipPerawat = "";
        if (tbProtokol.getSelectedRow() != -1) {
            TnoRW.setText(tbProtokol.getValueAt(tbProtokol.getSelectedRow(), 0).toString());
            TnoRM.setText(tbProtokol.getValueAt(tbProtokol.getSelectedRow(), 1).toString());
            TnmPasien.setText(tbProtokol.getValueAt(tbProtokol.getSelectedRow(), 2).toString());
            Tumur.setText(tbProtokol.getValueAt(tbProtokol.getSelectedRow(), 3).toString());            
            TnmProtokol.setText(tbProtokol.getValueAt(tbProtokol.getSelectedRow(), 4).toString());
            Tsiklus.setText(tbProtokol.getValueAt(tbProtokol.getSelectedRow(), 5).toString());
            Valid.SetTgl(TtglSiklus, tbProtokol.getValueAt(tbProtokol.getSelectedRow(), 15).toString());
            Tdosis.setText(tbProtokol.getValueAt(tbProtokol.getSelectedRow(), 7).toString());
            Ttb.setText(tbProtokol.getValueAt(tbProtokol.getSelectedRow(), 8).toString());
            Tbb.setText(tbProtokol.getValueAt(tbProtokol.getSelectedRow(), 9).toString());
            Tlpt.setText(tbProtokol.getValueAt(tbProtokol.getSelectedRow(), 10).toString());
            Tdiagnosis.setText(tbProtokol.getValueAt(tbProtokol.getSelectedRow(), 11).toString());
            Tprogram.setText(tbProtokol.getValueAt(tbProtokol.getSelectedRow(), 12).toString());
            TnmDokter.setText(tbProtokol.getValueAt(tbProtokol.getSelectedRow(), 13).toString());
            TnmPerawat.setText(tbProtokol.getValueAt(tbProtokol.getSelectedRow(), 14).toString());
            nipDokter = tbProtokol.getValueAt(tbProtokol.getSelectedRow(), 16).toString();
            nipPerawat = tbProtokol.getValueAt(tbProtokol.getSelectedRow(), 17).toString();
            Tket.setText(tbProtokol.getValueAt(tbProtokol.getSelectedRow(), 19).toString());
        }
    }
    
    public void isCek(){
       BtnSimpan.setEnabled(akses.getkemoterapi());
       BtnHapus.setEnabled(akses.getkemoterapi());
       BtnEdit.setEnabled(akses.getkemoterapi());
       MnRiwayatData.setEnabled(akses.getadmin());
    }
    
    private void hapus() {
        x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            hapusDisimpan();
            if (Sequel.queryu2tf("delete from protokol_kemoterapi where no_rawat=?", 1, new String[]{
                tbProtokol.getValueAt(tbProtokol.getSelectedRow(), 0).toString()
            }) == true) {
                TCari.setText(TnoRM.getText());
                tampil();
                emptTeks();
            } else {
                JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
            }
        }
    }
    
    public void setData(String norw, String norm, String nmpasien, String stts) {
        TnoRW.setText(norw);
        TnoRM.setText(norm);
        TnmPasien.setText(nmpasien);
        Tumur.setText(Sequel.cariIsi("select umurdaftar from reg_periksa where no_rawat='" + norw + "' and sttsumur='Th'"));
        Ttb.setText(Sequel.cariIsi("select ifnull(tb,'') from penilaian_awal_keperawatan_dewasa_ranap where no_rawat='" + norw + "'"));
        Tbb.setText(Sequel.cariIsi("select ifnull(bb_msk_rs,'') from penilaian_awal_keperawatan_dewasa_ranap where no_rawat='" + norw + "'"));
        Tsiklus.setText(Sequel.cariIsi("select ifnull(MAX(siklus_ke)+1,1) from protokol_kemoterapi where no_rkm_medis='" + norm + "'"));
        nipDokter = "D0000020";
        TnmDokter.setText(Sequel.cariIsi("select ifnull(nm_dokter,'') from dokter where kd_dokter='" + nipDokter + "'"));
        TCari.setText(norm);
        
        if (stts.equals("Ralan")) {
            nipDokter = Sequel.cariIsi("select kd_dokter from reg_periksa where no_rawat='" + norw + "'");
            TnmDokter.setText(Sequel.cariIsi("select nama from pegawai where nik='" + nipDokter + "'"));
            Tdiagnosis.setText(Sequel.cariIsi("select ifnull(diagnosa,'') from pemeriksaan_ralan where no_rawat='" + norw + "'"));
            nipPerawat = "-";
            TnmPerawat.setText("-");
        } else {
            nipDokter = "-";
            TnmDokter.setText("-");
            Tdiagnosis.setText("");
            
            if (akses.getadmin() == true) {
                nipPerawat = "-";
                TnmPerawat.setText("-");
            } else {
                nipPerawat = akses.getkode();
                TnmPerawat.setText(Sequel.cariIsi("select nama from pegawai where nik='" + nipPerawat + "'"));
            }
        }
        
        //jika bukan dokter atau admin utama
        if (akses.getadmin() == true) {
            BtnSimpan.setEnabled(true);
            BtnDokter.setEnabled(true);
            BtnHapus.setEnabled(true);
        } else if (Sequel.cariInteger("select count(-1) from dokter where kd_dokter='" + akses.getkode() + "'") == 0) {
            BtnSimpan.setEnabled(false);
            BtnDokter.setEnabled(false);
            BtnHapus.setEnabled(false);
        } else {
            BtnSimpan.setEnabled(true);
            BtnDokter.setEnabled(true);
            BtnHapus.setEnabled(true);
        }
    }
    
    public void isMenu() {
        if (ChkAccor.isSelected() == true) {
            ChkAccor.setVisible(false);
            PanelAccor.setPreferredSize(new Dimension(900, HEIGHT));
            FormMenu.setVisible(true);
            ChkAccor.setVisible(true);
            Tnotepad.setText("");
            tampilNotepad();
        } else if (ChkAccor.isSelected() == false) {
            ChkAccor.setVisible(false);
            PanelAccor.setPreferredSize(new Dimension(22, HEIGHT));
            FormMenu.setVisible(false);
            ChkAccor.setVisible(true);
        }
    }
    
    private void tampilNotepad() {
        Valid.tabelKosong(tabMode1);
        try {
            ps1 = koneksi.prepareStatement("select *, date_format(tgl_catatan,'%d/%m/%Y %H:%i') tgl from notepad "
                    + "where nip_petugas='" + akses.getkode() + "' order by tgl_catatan desc");
            try {
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    tabMode1.addRow(new String[]{
                        rs1.getString("tgl"),
                        rs1.getString("catatan")
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
    }
    
    private void protokolKemoterapi() {
        dataProtokol = "";
        try {
            ps2 = koneksi.prepareStatement("SELECT pk.tb, pk.bb, pk.lpt, pk.diagnosis, rp.umurdaftar, pk.program from protokol_kemoterapi pk "
                    + "inner join reg_periksa rp on rp.no_rawat=pk.no_rawat where pk.no_rawat='" + TnoRW.getText() + "' "
                    + "and pk.tgl_siklus='" + Valid.SetTgl(TtglSiklus.getSelectedItem() + "") + "'");
            try {
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    dataProtokol = 
                            "Umur                   : " + rs2.getString("umurdaftar") + " Tahun\n"
                            + "Tinggi Badan        : " + rs2.getString("tb") + " Cm.\n"
                            + "Berat Badan         : " + rs2.getString("bb") + " Kg.\n"
                            + "LPT                     : " + rs2.getString("lpt") + "\n"
                            + "Diagnosis             : " + rs2.getString("diagnosis") + "\n\n"
                            + "Program :\n" + rs2.getString("program") + "\n";
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
    
    private void hapusDisimpan() {
        user = "";
        if (akses.getadmin() == true) {
            user = "-";
        } else {
            user = akses.getkode();
        }

        Sequel.menyimpanPesanGagalnyaDiTerminal("protokol_kemoterapi_histori", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "Protokol Kemoterapi Histori", 18, new String[]{
            TnoRW.getText(), TnmProtokol.getText(), Tsiklus.getText(), Valid.SetTgl(TtglSiklus.getSelectedItem() + ""), Tdosis.getText(),
            Ttb.getText(), Tbb.getText(), Tlpt.getText(), Tdiagnosis.getText(), Tprogram.getText(), nipPerawat, nipDokter,
            tbProtokol.getValueAt(tbProtokol.getSelectedRow(), 18).toString(), TnoRM.getText(), Tket.getText(), "hapus", user, 
            Sequel.cariIsi("select now()")
        });
    }

    private void gantiDisimpan() {
        user = "";
        if (akses.getadmin() == true) {
            user = "-";
        } else {
            user = akses.getkode();
        }

        Sequel.menyimpanPesanGagalnyaDiTerminal("protokol_kemoterapi_histori", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "Protokol Kemoterapi Histori", 18, new String[]{
            TnoRW.getText(), TnmProtokol.getText(), Tsiklus.getText(), Valid.SetTgl(TtglSiklus.getSelectedItem() + ""), Tdosis.getText(),
            Ttb.getText(), Tbb.getText(), Tlpt.getText(), Tdiagnosis.getText(), Tprogram.getText(), nipPerawat, nipDokter,
            tbProtokol.getValueAt(tbProtokol.getSelectedRow(), 18).toString(), TnoRM.getText(), Tket.getText(), "ganti", user, 
            Sequel.cariIsi("select now()")
        });
    }
    
    private void tampilRiwayat() {
        Valid.tabelKosong(tabMode2);
        try {
            psrestor = koneksi.prepareStatement("SELECT IF(pg.nama='-','Admin Utama',pg.nama) pelaku, a.no_rawat, a.no_rkm_medis, p.nm_pasien, "
                    + "a.tgl_siklus, a.waktu_eksekusi, upper(concat('DI',a.status_data)) sttsdata FROM protokol_kemoterapi_histori a "
                    + "INNER JOIN pasien p ON p.no_rkm_medis = a.no_rkm_medis INNER JOIN pegawai pg ON pg.nik = a.nik_eksekutor WHERE "
                    + "date(a.tgl_siklus) between ? and ? and pg.nama like ? or "
                    + "date(a.tgl_siklus) between ? and ? and a.no_rawat like ? or "
                    + "date(a.tgl_siklus) between ? and ? and a.no_rkm_medis like ? or "
                    + "date(a.tgl_siklus) between ? and ? and a.status_data like ? or "
                    + "date(a.tgl_siklus) between ? and ? and p.nm_pasien like ? order by a.tgl_siklus desc");
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
                        rsrestor.getString("tgl_siklus"),
                        rsrestor.getString("waktu_eksekusi"),
                        rsrestor.getString("sttsdata")
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
            ps3 = koneksi.prepareStatement("select * from protokol_kemoterapi_histori where "
                    + "waktu_eksekusi='" + tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 5).toString() + "'");
            try {
                rs3 = ps3.executeQuery();
                while (rs3.next()) {
                    try {
                        Sequel.menyimpan("protokol_kemoterapi",
                                "'" + rs3.getString("no_rawat") + "',"
                                + "'" + rs3.getString("nm_protokol") + "',"
                                + "'" + rs3.getString("siklus_ke") + "',"
                                + "'" + rs3.getString("tgl_siklus") + "',"
                                + "'" + rs3.getString("dosis") + "',"
                                + "'" + rs3.getString("tb") + "',"
                                + "'" + rs3.getString("bb") + "',"
                                + "'" + rs3.getString("lpt") + "',"
                                + "'" + rs3.getString("diagnosis") + "',"
                                + "'" + rs3.getString("program") + "',"
                                + "'" + rs3.getString("nip_perawat") + "',"
                                + "'" + rs3.getString("nip_dokter") + "',"
                                + "'" + rs3.getString("waktu_simpan") + "',"
                                + "'" + rs3.getString("no_rkm_medis") + "',"
                                + "'" + rs3.getString("keterangan") + "'", "Protokol Kemoterapi");
                    } catch (Exception e) {
                        System.out.println("Simpan : " + e);
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
    
    private void kembalikanDataDiganti() {
        if (Sequel.queryu2tf("delete from protokol_kemoterapi where no_rawat=?", 1, new String[]{
            tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 1).toString()
        }) == true) {
            kembalikanData();
        } else {
            JOptionPane.showMessageDialog(null, "Gagal dikembalikan..!!");
        }
    }
    
    private void tampilTemplate() {
        Valid.tabelKosong(tabMode3);
        try {
            ps4 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, date_format(pa.tgl_siklus,'%d/%m/%Y') tglsiklus, "
                    + "pa.* from protokol_kemoterapi pa inner join reg_periksa rp on rp.no_rawat=pa.no_rawat "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                    + "pa.program<>'' and p.no_rkm_medis like ? OR "
                    + "pa.program<>'' and p.nm_pasien like ? OR "
                    + "pa.program<>'' and pa.program like ? ORDER BY date(pa.waktu_simpan) desc limit 20");
            try {
                ps4.setString(1, "%" + TCari1.getText() + "%");
                ps4.setString(2, "%" + TCari1.getText() + "%");
                ps4.setString(3, "%" + TCari1.getText() + "%");
                rs4 = ps4.executeQuery();
                while (rs4.next()) {
                    tabMode3.addRow(new String[]{
                        rs4.getString("no_rkm_medis"),
                        rs4.getString("nm_pasien"),
                        rs4.getString("program"),
                        rs4.getString("tglsiklus")
                    });
                }            
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs4 != null) {
                    rs4.close();
                } 
                
                if (ps4 != null) {
                    ps4.close();
                }
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
}
