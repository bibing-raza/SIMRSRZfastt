    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgSpesialis.java
 *
 * Created on May 23, 2010, 1:25:13 AM
 */

package inventory;

import rekammedis.*;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import kepegawaian.DlgCariPetugas;

/**
 *
 * @author dosen
 */
public class DlgPemberianObatPasien extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps1, ps2, ps3;
    private ResultSet rs, rs1, rs2, rs3;
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private int x = 0, i = 0;
    private String kdobat = "", status = "", statusOK = "", dataDipilih = "", kdobatFix = "", nipPetugas = "", waktuSimpan = "";

    /** Creates new form DlgSpesialis
     * @param parent
     * @param modal */
    public DlgPemberianObatPasien(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        Object[] row = {"Cek", "No. Rawat", "No. RM", "Nama Pasien", "Nama Obat", "Dosis", "Cara Pemberian/Rute", "Jam",
            "Jlh. Sisa Obat", "Status", "wkt_simpan", "Tgl. Pemberian", "kodeobat", "tgl_pemberian", "Rg. Rawat/Inst.", "Jlh. Obat",
            "Pemberian Obat", "Nama Petugas", "nip_petugas", "wkt_simpan"
        };
        tabMode=new DefaultTableModel(null,row){
              @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, 
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };

        tbObat.setModel(tabMode);
        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 20; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(105);
            } else if (i == 2) {
                column.setPreferredWidth(65);
            } else if (i == 3) {
                column.setPreferredWidth(220);
            } else if (i == 4) {
                column.setPreferredWidth(220);
            } else if (i == 5) {
                column.setPreferredWidth(80);
            } else if (i == 6) {
                column.setPreferredWidth(170);
            } else if (i == 7) {
                column.setPreferredWidth(60);
            } else if (i == 8) {
                column.setPreferredWidth(120);
            } else if (i == 9) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 10) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 11) {
                column.setPreferredWidth(85);
            } else if (i == 12) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 13) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 14) {
                column.setPreferredWidth(200);
            } else if (i == 15) {
                column.setPreferredWidth(70);
            } else if (i == 16) {
                column.setPreferredWidth(100);
            } else if (i == 17) {
                column.setPreferredWidth(200);
            } else if (i == 18) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 19) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1 = new DefaultTableModel(null, new Object[]{
            "Kode Obat", "Nama Obat/Alkes", "Satuan"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbFarmasi.setModel(tabMode1);
        tbFarmasi.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbFarmasi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 3; i++) {
            TableColumn column = tbFarmasi.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(100);               
            } else if (i == 1) {
                column.setPreferredWidth(350);
            } else if (i == 2) {
                column.setPreferredWidth(60);
            }
        }
        tbFarmasi.setDefaultRenderer(Object.class, new WarnaTable());

        nmObat.setDocument(new batasInput((int) 255).getKata(nmObat));
        dosis.setDocument(new batasInput((int) 7).getKata(dosis));
        caraPemberian.setDocument(new batasInput((int) 180).getKata(caraPemberian));
        jlhSisaObat.setDocument(new batasInput((int) 20).getKata(jlhSisaObat));
        Tjlh.setDocument(new batasInput((int) 10).getKata(Tjlh));
        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));

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
        
        petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if (petugas.getTable().getSelectedRow() != -1) {
                    nipPetugas = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                    Tpetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                    BtnPetugas.requestFocus();
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
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu = new javax.swing.JPopupMenu();
        MnContengSemua = new javax.swing.JMenuItem();
        MnHapusConteng = new javax.swing.JMenuItem();
        MnCopy = new javax.swing.JMenuItem();
        WindowObat = new javax.swing.JDialog();
        internalFrame6 = new widget.InternalFrame();
        panelisi6 = new widget.panelisi();
        jLabel49 = new widget.Label();
        norm = new widget.TextBox();
        nmpasien = new widget.TextBox();
        jLabel50 = new widget.Label();
        nmdpjp = new widget.TextBox();
        jLabel51 = new widget.Label();
        rencana = new widget.TextBox();
        jLabel52 = new widget.Label();
        scrollPane12 = new widget.ScrollPane();
        TketRencana = new widget.TextArea();
        jLabel53 = new widget.Label();
        tglAsesmen = new widget.TextBox();
        Scroll1 = new widget.ScrollPane();
        tbFarmasi = new widget.Table();
        panelisi5 = new widget.panelisi();
        jLabel48 = new widget.Label();
        TCari1 = new widget.TextBox();
        BtnCari1 = new widget.Button();
        BtnCloseIn1 = new widget.Button();
        WindowCetak = new javax.swing.JDialog();
        internalFrame5 = new widget.InternalFrame();
        BtnCloseIn4 = new widget.Button();
        BtnCetakLap = new widget.Button();
        jLabel18 = new widget.Label();
        cmbJnsRawat = new widget.ComboBox();
        jLabel20 = new widget.Label();
        cmbJnsObat1 = new widget.ComboBox();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbObat = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();
        jLabel11 = new widget.Label();
        cmbHlm = new widget.ComboBox();
        panelGlass9 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel17 = new widget.Label();
        cmbJnsObat = new widget.ComboBox();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        panelGlass7 = new widget.panelisi();
        jLabel3 = new widget.Label();
        jLabel4 = new widget.Label();
        nmObat = new widget.TextBox();
        jLabel5 = new widget.Label();
        TNoRW = new widget.TextBox();
        TNoRM = new widget.TextBox();
        BtnObat = new widget.Button();
        TNmPasien = new widget.TextBox();
        dosis = new widget.TextBox();
        jLabel8 = new widget.Label();
        caraPemberian = new widget.TextBox();
        jLabel9 = new widget.Label();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        jLabel10 = new widget.Label();
        jlhSisaObat = new widget.TextBox();
        jLabel12 = new widget.Label();
        tgl_beri = new widget.Tanggal();
        jLabel13 = new widget.Label();
        nmUnit = new widget.TextBox();
        jLabel14 = new widget.Label();
        Tjlh = new widget.TextBox();
        jLabel15 = new widget.Label();
        cmbObat = new widget.ComboBox();
        jLabel16 = new widget.Label();
        Tpetugas = new widget.TextBox();
        BtnPetugas = new widget.Button();
        BtnSimpanAtas = new widget.Button();
        BtnBaruAtas = new widget.Button();

        jPopupMenu.setName("jPopupMenu"); // NOI18N

        MnContengSemua.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnContengSemua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnContengSemua.setText("Conteng Semua");
        MnContengSemua.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnContengSemua.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnContengSemua.setIconTextGap(5);
        MnContengSemua.setName("MnContengSemua"); // NOI18N
        MnContengSemua.setPreferredSize(new java.awt.Dimension(150, 26));
        MnContengSemua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnContengSemuaActionPerformed(evt);
            }
        });
        jPopupMenu.add(MnContengSemua);

        MnHapusConteng.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusConteng.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnHapusConteng.setText("Hapus Conteng");
        MnHapusConteng.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapusConteng.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapusConteng.setIconTextGap(5);
        MnHapusConteng.setName("MnHapusConteng"); // NOI18N
        MnHapusConteng.setPreferredSize(new java.awt.Dimension(150, 26));
        MnHapusConteng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusContengActionPerformed(evt);
            }
        });
        jPopupMenu.add(MnHapusConteng);

        MnCopy.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCopy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCopy.setText("Copy Pemberian Obat");
        MnCopy.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCopy.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCopy.setIconTextGap(5);
        MnCopy.setName("MnCopy"); // NOI18N
        MnCopy.setPreferredSize(new java.awt.Dimension(150, 26));
        MnCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCopyActionPerformed(evt);
            }
        });
        jPopupMenu.add(MnCopy);

        WindowObat.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowObat.setName("WindowObat"); // NOI18N
        WindowObat.setUndecorated(true);
        WindowObat.setResizable(false);

        internalFrame6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Daftar Nama Obat/Alkes Farmasi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame6.setName("internalFrame6"); // NOI18N
        internalFrame6.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame6.setLayout(new java.awt.BorderLayout());

        panelisi6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Membaca Rencana / Instruksi DPJP ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        panelisi6.setName("panelisi6"); // NOI18N
        panelisi6.setPreferredSize(new java.awt.Dimension(100, 230));
        panelisi6.setLayout(null);

        jLabel49.setForeground(new java.awt.Color(0, 0, 0));
        jLabel49.setText("Pasien : ");
        jLabel49.setName("jLabel49"); // NOI18N
        jLabel49.setPreferredSize(new java.awt.Dimension(70, 23));
        jLabel49.setRequestFocusEnabled(false);
        panelisi6.add(jLabel49);
        jLabel49.setBounds(0, 20, 140, 23);

        norm.setEditable(false);
        norm.setForeground(new java.awt.Color(0, 0, 0));
        norm.setName("norm"); // NOI18N
        norm.setPreferredSize(new java.awt.Dimension(250, 23));
        panelisi6.add(norm);
        norm.setBounds(142, 20, 70, 23);

        nmpasien.setEditable(false);
        nmpasien.setForeground(new java.awt.Color(0, 0, 0));
        nmpasien.setName("nmpasien"); // NOI18N
        nmpasien.setPreferredSize(new java.awt.Dimension(250, 23));
        panelisi6.add(nmpasien);
        nmpasien.setBounds(216, 20, 366, 23);

        jLabel50.setForeground(new java.awt.Color(0, 0, 0));
        jLabel50.setText("Nama DPJP : ");
        jLabel50.setName("jLabel50"); // NOI18N
        jLabel50.setPreferredSize(new java.awt.Dimension(70, 23));
        jLabel50.setRequestFocusEnabled(false);
        panelisi6.add(jLabel50);
        jLabel50.setBounds(0, 48, 140, 23);

        nmdpjp.setEditable(false);
        nmdpjp.setForeground(new java.awt.Color(0, 0, 0));
        nmdpjp.setName("nmdpjp"); // NOI18N
        nmdpjp.setPreferredSize(new java.awt.Dimension(250, 23));
        panelisi6.add(nmdpjp);
        nmdpjp.setBounds(142, 48, 440, 23);

        jLabel51.setForeground(new java.awt.Color(0, 0, 0));
        jLabel51.setText("Rencana / Instruksi : ");
        jLabel51.setName("jLabel51"); // NOI18N
        jLabel51.setPreferredSize(new java.awt.Dimension(70, 23));
        jLabel51.setRequestFocusEnabled(false);
        panelisi6.add(jLabel51);
        jLabel51.setBounds(0, 76, 140, 23);

        rencana.setEditable(false);
        rencana.setForeground(new java.awt.Color(0, 0, 0));
        rencana.setName("rencana"); // NOI18N
        rencana.setPreferredSize(new java.awt.Dimension(250, 23));
        panelisi6.add(rencana);
        rencana.setBounds(142, 76, 120, 23);

        jLabel52.setForeground(new java.awt.Color(0, 0, 0));
        jLabel52.setText("Ket. Rencana / Instruksi : ");
        jLabel52.setName("jLabel52"); // NOI18N
        jLabel52.setPreferredSize(new java.awt.Dimension(70, 23));
        jLabel52.setRequestFocusEnabled(false);
        panelisi6.add(jLabel52);
        jLabel52.setBounds(0, 104, 140, 23);

        scrollPane12.setName("scrollPane12"); // NOI18N

        TketRencana.setEditable(false);
        TketRencana.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TketRencana.setColumns(20);
        TketRencana.setRows(5);
        TketRencana.setName("TketRencana"); // NOI18N
        TketRencana.setPreferredSize(new java.awt.Dimension(162, 290));
        scrollPane12.setViewportView(TketRencana);

        panelisi6.add(scrollPane12);
        scrollPane12.setBounds(142, 104, 440, 110);

        jLabel53.setForeground(new java.awt.Color(0, 0, 0));
        jLabel53.setText("Tgl. Asesmen Medik : ");
        jLabel53.setName("jLabel53"); // NOI18N
        jLabel53.setPreferredSize(new java.awt.Dimension(70, 23));
        jLabel53.setRequestFocusEnabled(false);
        panelisi6.add(jLabel53);
        jLabel53.setBounds(260, 76, 120, 23);

        tglAsesmen.setEditable(false);
        tglAsesmen.setForeground(new java.awt.Color(0, 0, 0));
        tglAsesmen.setName("tglAsesmen"); // NOI18N
        tglAsesmen.setPreferredSize(new java.awt.Dimension(250, 23));
        panelisi6.add(tglAsesmen);
        tglAsesmen.setBounds(382, 76, 200, 23);

        internalFrame6.add(panelisi6, java.awt.BorderLayout.PAGE_START);

        Scroll1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Silahkan pilih nama obatnya ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);
        Scroll1.setPreferredSize(new java.awt.Dimension(452, 250));

        tbFarmasi.setToolTipText("Silahkan klik salah satu data yang akan dipakai");
        tbFarmasi.setName("tbFarmasi"); // NOI18N
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
        Scroll1.setViewportView(tbFarmasi);

        internalFrame6.add(Scroll1, java.awt.BorderLayout.CENTER);

        panelisi5.setName("panelisi5"); // NOI18N
        panelisi5.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 7));

        jLabel48.setForeground(new java.awt.Color(0, 0, 0));
        jLabel48.setText("Key Word :");
        jLabel48.setName("jLabel48"); // NOI18N
        jLabel48.setPreferredSize(new java.awt.Dimension(70, 23));
        jLabel48.setRequestFocusEnabled(false);
        panelisi5.add(jLabel48);

        TCari1.setForeground(new java.awt.Color(0, 0, 0));
        TCari1.setName("TCari1"); // NOI18N
        TCari1.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari1KeyPressed(evt);
            }
        });
        panelisi5.add(TCari1);

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
        panelisi5.add(BtnCari1);

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

        internalFrame6.add(panelisi5, java.awt.BorderLayout.PAGE_END);

        WindowObat.getContentPane().add(internalFrame6, java.awt.BorderLayout.CENTER);

        WindowCetak.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowCetak.setName("WindowCetak"); // NOI18N
        WindowCetak.setUndecorated(true);
        WindowCetak.setResizable(false);

        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Cetak Catatan Laporan Pemberian Obat Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setWarnaBawah(new java.awt.Color(240, 245, 235));
        internalFrame5.setLayout(null);

        BtnCloseIn4.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn4.setMnemonic('U');
        BtnCloseIn4.setText("Tutup");
        BtnCloseIn4.setToolTipText("Alt+U");
        BtnCloseIn4.setName("BtnCloseIn4"); // NOI18N
        BtnCloseIn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn4ActionPerformed(evt);
            }
        });
        internalFrame5.add(BtnCloseIn4);
        BtnCloseIn4.setBounds(280, 60, 100, 26);

        BtnCetakLap.setForeground(new java.awt.Color(0, 0, 0));
        BtnCetakLap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/PrinterSettings.png"))); // NOI18N
        BtnCetakLap.setMnemonic('S');
        BtnCetakLap.setText("Cetak Laporan");
        BtnCetakLap.setToolTipText("Alt+S");
        BtnCetakLap.setName("BtnCetakLap"); // NOI18N
        BtnCetakLap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCetakLapActionPerformed(evt);
            }
        });
        internalFrame5.add(BtnCetakLap);
        BtnCetakLap.setBounds(100, 60, 140, 26);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Jenis Rawat : ");
        jLabel18.setName("jLabel18"); // NOI18N
        internalFrame5.add(jLabel18);
        jLabel18.setBounds(0, 25, 100, 23);

        cmbJnsRawat.setForeground(new java.awt.Color(0, 0, 0));
        cmbJnsRawat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "R. Jalan", "R. Inap", "Semua" }));
        cmbJnsRawat.setName("cmbJnsRawat"); // NOI18N
        cmbJnsRawat.setPreferredSize(new java.awt.Dimension(76, 23));
        internalFrame5.add(cmbJnsRawat);
        cmbJnsRawat.setBounds(103, 25, 76, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Jns. Pemberian Obat :");
        jLabel20.setName("jLabel20"); // NOI18N
        jLabel20.setPreferredSize(new java.awt.Dimension(120, 23));
        internalFrame5.add(jLabel20);
        jLabel20.setBounds(180, 25, 120, 23);

        cmbJnsObat1.setForeground(new java.awt.Color(0, 0, 0));
        cmbJnsObat1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Semua", "-", "ORAL", "INJEKSI" }));
        cmbJnsObat1.setName("cmbJnsObat1"); // NOI18N
        cmbJnsObat1.setPreferredSize(new java.awt.Dimension(76, 23));
        internalFrame5.add(cmbJnsObat1);
        cmbJnsObat1.setBounds(307, 25, 76, 23);

        WindowCetak.getContentPane().add(internalFrame5, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Pemberian Obat Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbObat.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbObat.setComponentPopupMenu(jPopupMenu);
        tbObat.setName("tbObat"); // NOI18N
        tbObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbObatMouseClicked(evt);
            }
        });
        tbObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbObatKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbObatKeyReleased(evt);
            }
        });
        Scroll.setViewportView(tbObat);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

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

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Limit Data :");
        jLabel11.setName("jLabel11"); // NOI18N
        jLabel11.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass8.add(jLabel11);

        cmbHlm.setForeground(new java.awt.Color(0, 0, 0));
        cmbHlm.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "100", "200", "300", "400", "500", "1000", "Semua" }));
        cmbHlm.setName("cmbHlm"); // NOI18N
        cmbHlm.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass8.add(cmbHlm);

        jPanel3.add(panelGlass8, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 3, 9));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tgl. Pemberian :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(100, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "27-11-2023" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari1);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass9.add(jLabel21);

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "27-11-2023" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari2);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Jns. Pemberian Obat :");
        jLabel17.setName("jLabel17"); // NOI18N
        jLabel17.setPreferredSize(new java.awt.Dimension(120, 23));
        panelGlass9.add(jLabel17);

        cmbJnsObat.setForeground(new java.awt.Color(0, 0, 0));
        cmbJnsObat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Semua", "-", "ORAL", "INJEKSI" }));
        cmbJnsObat.setName("cmbJnsObat"); // NOI18N
        cmbJnsObat.setPreferredSize(new java.awt.Dimension(76, 23));
        panelGlass9.add(cmbJnsObat);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(200, 23));
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

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        panelGlass7.setName("panelGlass7"); // NOI18N
        panelGlass7.setPreferredSize(new java.awt.Dimension(44, 198));
        panelGlass7.setLayout(null);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Nama Obat :");
        jLabel3.setName("jLabel3"); // NOI18N
        panelGlass7.add(jLabel3);
        jLabel3.setBounds(0, 40, 110, 23);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Dosis :");
        jLabel4.setName("jLabel4"); // NOI18N
        panelGlass7.add(jLabel4);
        jLabel4.setBounds(0, 70, 110, 23);

        nmObat.setForeground(new java.awt.Color(0, 0, 0));
        nmObat.setName("nmObat"); // NOI18N
        nmObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nmObatKeyPressed(evt);
            }
        });
        panelGlass7.add(nmObat);
        nmObat.setBounds(114, 40, 362, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Pasien :");
        jLabel5.setName("jLabel5"); // NOI18N
        panelGlass7.add(jLabel5);
        jLabel5.setBounds(0, 10, 110, 23);

        TNoRW.setEditable(false);
        TNoRW.setForeground(new java.awt.Color(0, 0, 0));
        TNoRW.setName("TNoRW"); // NOI18N
        panelGlass7.add(TNoRW);
        TNoRW.setBounds(114, 10, 122, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        panelGlass7.add(TNoRM);
        TNoRM.setBounds(240, 10, 70, 23);

        BtnObat.setForeground(new java.awt.Color(0, 0, 0));
        BtnObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnObat.setMnemonic('1');
        BtnObat.setToolTipText("Alt+1");
        BtnObat.setName("BtnObat"); // NOI18N
        BtnObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnObatActionPerformed(evt);
            }
        });
        BtnObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnObatKeyPressed(evt);
            }
        });
        panelGlass7.add(BtnObat);
        BtnObat.setBounds(478, 40, 28, 23);

        TNmPasien.setEditable(false);
        TNmPasien.setForeground(new java.awt.Color(0, 0, 0));
        TNmPasien.setName("TNmPasien"); // NOI18N
        panelGlass7.add(TNmPasien);
        TNmPasien.setBounds(313, 10, 350, 23);

        dosis.setForeground(new java.awt.Color(0, 0, 0));
        dosis.setName("dosis"); // NOI18N
        dosis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dosisKeyPressed(evt);
            }
        });
        panelGlass7.add(dosis);
        dosis.setBounds(114, 70, 100, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Cara Pemberian/Rute :");
        jLabel8.setName("jLabel8"); // NOI18N
        panelGlass7.add(jLabel8);
        jLabel8.setBounds(215, 70, 140, 23);

        caraPemberian.setForeground(new java.awt.Color(0, 0, 0));
        caraPemberian.setName("caraPemberian"); // NOI18N
        caraPemberian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                caraPemberianKeyPressed(evt);
            }
        });
        panelGlass7.add(caraPemberian);
        caraPemberian.setBounds(360, 70, 300, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Jadwal Pemberian (Jam) :");
        jLabel9.setName("jLabel9"); // NOI18N
        panelGlass7.add(jLabel9);
        jLabel9.setBounds(215, 100, 140, 23);

        cmbJam.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbJamMouseClicked(evt);
            }
        });
        cmbJam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbJamKeyPressed(evt);
            }
        });
        panelGlass7.add(cmbJam);
        cmbJam.setBounds(360, 100, 47, 23);

        cmbMnt.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbMntMouseClicked(evt);
            }
        });
        cmbMnt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbMntKeyPressed(evt);
            }
        });
        panelGlass7.add(cmbMnt);
        cmbMnt.setBounds(413, 100, 47, 23);

        cmbDtk.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbDtkMouseClicked(evt);
            }
        });
        cmbDtk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbDtkKeyPressed(evt);
            }
        });
        panelGlass7.add(cmbDtk);
        cmbDtk.setBounds(466, 100, 47, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Jumlah (Sisa Obat) :");
        jLabel10.setName("jLabel10"); // NOI18N
        panelGlass7.add(jLabel10);
        jLabel10.setBounds(0, 130, 110, 23);

        jlhSisaObat.setForeground(new java.awt.Color(0, 0, 0));
        jlhSisaObat.setName("jlhSisaObat"); // NOI18N
        jlhSisaObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jlhSisaObatKeyPressed(evt);
            }
        });
        panelGlass7.add(jlhSisaObat);
        jlhSisaObat.setBounds(114, 130, 100, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Tgl. Pemberian :");
        jLabel12.setName("jLabel12"); // NOI18N
        panelGlass7.add(jLabel12);
        jLabel12.setBounds(0, 100, 110, 23);

        tgl_beri.setEditable(false);
        tgl_beri.setDisplayFormat("dd-MM-yyyy");
        tgl_beri.setName("tgl_beri"); // NOI18N
        panelGlass7.add(tgl_beri);
        tgl_beri.setBounds(114, 100, 100, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Rg. Rawat/Poli/Inst :");
        jLabel13.setName("jLabel13"); // NOI18N
        panelGlass7.add(jLabel13);
        jLabel13.setBounds(215, 130, 140, 23);

        nmUnit.setEditable(false);
        nmUnit.setForeground(new java.awt.Color(0, 0, 0));
        nmUnit.setName("nmUnit"); // NOI18N
        panelGlass7.add(nmUnit);
        nmUnit.setBounds(360, 130, 300, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Jumlah : ");
        jLabel14.setName("jLabel14"); // NOI18N
        panelGlass7.add(jLabel14);
        jLabel14.setBounds(510, 40, 60, 23);

        Tjlh.setForeground(new java.awt.Color(0, 0, 0));
        Tjlh.setName("Tjlh"); // NOI18N
        panelGlass7.add(Tjlh);
        Tjlh.setBounds(573, 40, 90, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Pemberian Obat :");
        jLabel15.setName("jLabel15"); // NOI18N
        panelGlass7.add(jLabel15);
        jLabel15.setBounds(0, 158, 110, 23);

        cmbObat.setForeground(new java.awt.Color(0, 0, 0));
        cmbObat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "ORAL", "INJEKSI" }));
        cmbObat.setName("cmbObat"); // NOI18N
        panelGlass7.add(cmbObat);
        cmbObat.setBounds(114, 158, 76, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Nama Petugas :");
        jLabel16.setName("jLabel16"); // NOI18N
        panelGlass7.add(jLabel16);
        jLabel16.setBounds(215, 158, 140, 23);

        Tpetugas.setEditable(false);
        Tpetugas.setForeground(new java.awt.Color(0, 0, 0));
        Tpetugas.setName("Tpetugas"); // NOI18N
        panelGlass7.add(Tpetugas);
        Tpetugas.setBounds(360, 158, 300, 23);

        BtnPetugas.setForeground(new java.awt.Color(0, 0, 0));
        BtnPetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPetugas.setMnemonic('1');
        BtnPetugas.setToolTipText("Alt+1");
        BtnPetugas.setName("BtnPetugas"); // NOI18N
        BtnPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPetugasActionPerformed(evt);
            }
        });
        panelGlass7.add(BtnPetugas);
        BtnPetugas.setBounds(660, 158, 28, 23);

        BtnSimpanAtas.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpanAtas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpanAtas.setMnemonic('X');
        BtnSimpanAtas.setToolTipText("Alt+X");
        BtnSimpanAtas.setGlassColor(new java.awt.Color(51, 102, 255));
        BtnSimpanAtas.setName("BtnSimpanAtas"); // NOI18N
        BtnSimpanAtas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanAtasActionPerformed(evt);
            }
        });
        panelGlass7.add(BtnSimpanAtas);
        BtnSimpanAtas.setBounds(524, 100, 28, 23);

        BtnBaruAtas.setForeground(new java.awt.Color(0, 0, 0));
        BtnBaruAtas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBaruAtas.setMnemonic('Z');
        BtnBaruAtas.setToolTipText("Alt+Z");
        BtnBaruAtas.setGlassColor(new java.awt.Color(255, 102, 102));
        BtnBaruAtas.setName("BtnBaruAtas"); // NOI18N
        BtnBaruAtas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBaruAtasActionPerformed(evt);
            }
        });
        panelGlass7.add(BtnBaruAtas);
        BtnBaruAtas.setBounds(560, 100, 28, 23);

        internalFrame1.add(panelGlass7, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nmObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nmObatKeyPressed
        Valid.pindah(evt,nmObat,dosis);
}//GEN-LAST:event_nmObatKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRW.getText().trim().equals("")) {
            Valid.textKosong(TNoRW, "Pasien");
        } else if (nmObat.getText().trim().equals("")) {
            Valid.textKosong(nmObat, "Nama Obat");
        } else {
            if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)") || status.equals("ralan")) {
                statusOK = "Ralan";
            } else if (status.equals("ranap")) {
                statusOK = "Ranap";
            }
            
            if (kdobat.equals("")) {
                kdobatFix = "-";
            } else {
                kdobatFix = kdobat;
            }
            
            Sequel.menyimpan("pemberian_obat", "'" + TNoRW.getText() + "','" + nmObat.getText() + "',"
                    + "'" + dosis.getText() + "','" + caraPemberian.getText() + "',"
                    + "'" + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "',"
                    + "'" + jlhSisaObat.getText() + "','" + statusOK + "','" + Sequel.cariIsi("select now()") + "',"
                    + "'" + kdobatFix + "','" + Valid.SetTgl(tgl_beri.getSelectedItem() + "") + "','" + nmUnit.getText() + "',"
                    + "'" + Tjlh.getText() + "','" + cmbObat.getSelectedItem().toString() + "','" + nipPetugas + "'", "Pemberian Obat");
            
            tampil();
            if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)") || status.equals("ralan")) {
                emptTeks();
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, nmObat, BtnBatal);
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
        if (tbObat.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.queryu2tf("delete from pemberian_obat where waktu_simpan=?", 1, new String[]{
                    tbObat.getValueAt(tbObat.getSelectedRow(), 10).toString()
                }) == true) {
                    tampil();
                    emptTeks();
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih salah satu datanya terlebih dahulu..!!");
        }
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (TNoRW.getText().trim().equals("")) {
            Valid.textKosong(TNoRW, "Pasien");
        } else if (nmObat.getText().trim().equals("")) {
            Valid.textKosong(nmObat, "Nama Obat");
        } else {
            if (tbObat.getSelectedRow() > -1) {
                if (Sequel.cariInteger("select count(-1) from databarang where nama_brng like '%" + nmObat.getText() + "%'") == 0) {
                    kdobatFix = "-";
                } else {
                    kdobatFix = kdobat;
                }

                Sequel.mengedit("pemberian_obat", "waktu_simpan=?", "nama_obat=?, dosis=?, cara_pemberian=?, "
                        + "jadwal_pemberian=?, jlh_sisa_obat=?, kode_brng=?, tgl_pemberian=?, nm_unit=?, jlh_obat=?, jenis_obat=?, nip_petugas=?", 12, new String[]{
                            nmObat.getText(), dosis.getText(), caraPemberian.getText(),
                            cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                            jlhSisaObat.getText(), kdobatFix, Valid.SetTgl(tgl_beri.getSelectedItem() + ""), nmUnit.getText(), Tjlh.getText(),
                            cmbObat.getSelectedItem().toString(), nipPetugas,
                            tbObat.getValueAt(tbObat.getSelectedRow(), 10).toString()
                        });

                tampil();
                emptTeks();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih salah satu datanya terlebih dulu..!!");
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
        WindowObat.dispose();
        WindowCetak.dispose();
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
            tbObat.requestFocus();
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
        emptTeks();
        tampil();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAllActionPerformed(null);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObatMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbObatMouseClicked

    private void tbObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObatKeyPressed
        if(tabMode.getRowCount()!=0){
            if(evt.getKeyCode()==KeyEvent.VK_SHIFT){
                TCari.setText("");
                TCari.requestFocus();
            }           
        }
}//GEN-LAST:event_tbObatKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void tbObatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObatKeyReleased
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }          
        }
    }//GEN-LAST:event_tbObatKeyReleased

    private void BtnObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnObatActionPerformed
        WindowObat.setSize(620, internalFrame1.getHeight() - 40);
        WindowObat.setLocationRelativeTo(internalFrame1);
        WindowObat.setAlwaysOnTop(false);
        WindowObat.setVisible(true);
        tampilObat();
        TCari1.requestFocus();
        norm.setText(TNoRM.getText());
        nmpasien.setText(TNmPasien.getText());
        
        if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)")) {
            nmdpjp.setText(Sequel.cariIsi("select ifnull(p.nama,'-') from penilaian_awal_medis_igd pa inner join pegawai p on p.nik=pa.nip_dpjp where pa.no_rawat='" + TNoRW.getText() + "'"));
            rencana.setText(Sequel.cariIsi("select ifnull(rencana_instruksi,'-') from penilaian_awal_medis_igd where no_rawat='" + TNoRW.getText() + "'"));
            TketRencana.setText(Sequel.cariIsi("select ifnull(ket_rencana_instruksi,'-') from penilaian_awal_medis_igd where no_rawat='" + TNoRW.getText() + "'"));
            tglAsesmen.setText(Sequel.cariIsi("select ifnull(date_format(tanggal,'%d-%m-%Y, Jam : %H:%i'),'-') from penilaian_awal_medis_igd where no_rawat='" + TNoRW.getText() + "'"));
        } else if (status.equals("ranap")) {
            nmdpjp.setText("");
            rencana.setText("");
            TketRencana.setText("");
            tglAsesmen.setText("");
        }
    }//GEN-LAST:event_BtnObatActionPerformed

    private void BtnObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnObatKeyPressed
        Valid.pindah(evt,TNoRW,BtnSimpan);
    }//GEN-LAST:event_BtnObatKeyPressed

    private void dosisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dosisKeyPressed
        Valid.pindah(evt, nmObat, caraPemberian);
    }//GEN-LAST:event_dosisKeyPressed

    private void caraPemberianKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_caraPemberianKeyPressed
        Valid.pindah(evt, dosis, cmbJam);
    }//GEN-LAST:event_caraPemberianKeyPressed

    private void cmbJamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJamMouseClicked
        cmbJam.setEditable(false);
    }//GEN-LAST:event_cmbJamMouseClicked

    private void cmbJamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbJamKeyPressed
        Valid.pindah(evt, caraPemberian, cmbMnt);
    }//GEN-LAST:event_cmbJamKeyPressed

    private void cmbMntMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMntMouseClicked
        cmbMnt.setEditable(false);
    }//GEN-LAST:event_cmbMntMouseClicked

    private void cmbMntKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbMntKeyPressed
        Valid.pindah(evt, cmbJam, cmbDtk);
    }//GEN-LAST:event_cmbMntKeyPressed

    private void cmbDtkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtkMouseClicked
        cmbDtk.setEditable(false);
    }//GEN-LAST:event_cmbDtkMouseClicked

    private void cmbDtkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbDtkKeyPressed
        Valid.pindah(evt, cmbMnt, jlhSisaObat);
    }//GEN-LAST:event_cmbDtkKeyPressed

    private void jlhSisaObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jlhSisaObatKeyPressed
        Valid.pindah(evt, cmbDtk, BtnSimpan);
    }//GEN-LAST:event_jlhSisaObatKeyPressed

    private void TCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari1ActionPerformed(null);
        }
    }//GEN-LAST:event_TCari1KeyPressed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        tampilObat();
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void BtnCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCari1ActionPerformed(null);
        }
    }//GEN-LAST:event_BtnCari1KeyPressed

    private void BtnCloseIn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn1ActionPerformed
        WindowObat.dispose();
    }//GEN-LAST:event_BtnCloseIn1ActionPerformed

    private void tbFarmasiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbFarmasiMouseClicked
        if (tabMode1.getRowCount() != 0) {
            if (evt.getClickCount() == 2) {
                if (tbFarmasi.getSelectedRow() != -1) {
                    kdobat = tbFarmasi.getValueAt(tbFarmasi.getSelectedRow(), 0).toString();
                    nmObat.setText(tbFarmasi.getValueAt(tbFarmasi.getSelectedRow(), 1).toString());
                    WindowObat.dispose();
                }
            }
        }
    }//GEN-LAST:event_tbFarmasiMouseClicked

    private void tbFarmasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbFarmasiKeyPressed
        if (tabMode1.getRowCount() != 0) {
            if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
                if (tbFarmasi.getSelectedRow() != -1) {
                    kdobat = tbFarmasi.getValueAt(tbFarmasi.getSelectedRow(), 0).toString();
                    nmObat.setText(tbFarmasi.getValueAt(tbFarmasi.getSelectedRow(), 1).toString());
                    WindowObat.dispose();
                }
            }
        }
    }//GEN-LAST:event_tbFarmasiKeyPressed

    private void MnContengSemuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnContengSemuaActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data pemberian obat pasien belum ada...!!!!");
        } else {            
            tampil();
            for (i = 0; i < tbObat.getRowCount(); i++) {
                tbObat.setValueAt(Boolean.TRUE, i, 0);
            }
        }
    }//GEN-LAST:event_MnContengSemuaActionPerformed

    private void MnHapusContengActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusContengActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data pemberian obat pasien belum ada...!!!!");
        } else {
            tampil();
            for (i = 0; i < tbObat.getRowCount(); i++) {
                tbObat.setValueAt(Boolean.FALSE, i, 0);
            }
        }
    }//GEN-LAST:event_MnHapusContengActionPerformed

    private void MnCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCopyActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data pemberian obat pasien masih kosong...!!!!");
        } else {
            //cek conteng
            x = 0;
            for (i = 0; i < tbObat.getRowCount(); i++) {
                if (tbObat.getValueAt(i, 0).toString().equals("true")) {
                    x++;
                }
            }

            if (x == 0) {
                JOptionPane.showMessageDialog(null, "Silahkan conteng data pemberian obat pasien yang dipilih utk. di copy..!!!!");
                tbObat.requestFocus();
            } else {
                try {
                    for (i = 0; i < tbObat.getRowCount(); i++) {
                        if (tbObat.getValueAt(i, 0).toString().equals("true")) {
                            if (dataDipilih.equals("")) {
                                dataDipilih = tbObat.getValueAt(i, 4).toString() + ", Dosis : "
                                        + tbObat.getValueAt(i, 5).toString() + ", Cara Pemberian : "
                                        + tbObat.getValueAt(i, 6).toString() + ", Jdwl. Pemberian : "
                                        + tbObat.getValueAt(i, 7).toString();
                            } else {
                                dataDipilih = dataDipilih + "\n" + tbObat.getValueAt(i, 4).toString() + ", Dosis : "
                                        + tbObat.getValueAt(i, 5).toString() + ", Cara Pemberian : "
                                        + tbObat.getValueAt(i, 6).toString() + ", Jdwl. Pemberian : "
                                        + tbObat.getValueAt(i, 7).toString();
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                }

                akses.setCopyData(dataDipilih);
                JOptionPane.showMessageDialog(null, "Data pemberian obat berhasil di copy..!!!!");
                MnHapusContengActionPerformed(null);
            }
        }
    }//GEN-LAST:event_MnCopyActionPerformed

    private void BtnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPetugasActionPerformed
        akses.setform("DlgPemberianObatPasien");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugasActionPerformed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnBatal.requestFocus();
        } else if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan klik dulu salah satu datanya pada tabel utk. mencetak laporan...!!!!");
        } else if (Sequel.cariInteger("select count(-1) from pemberian_obat where no_rawat='" + TNoRW.getText() + "'") == 0) {
            JOptionPane.showMessageDialog(null, "Data pemberian obat dg. no. rawat pasien tersebut belum tersimpan...!!!!");
        } else if (tabMode.getRowCount() != 0) {
            WindowCetak.setSize(410, 114);
            WindowCetak.setLocationRelativeTo(internalFrame1);
            WindowCetak.setAlwaysOnTop(false);
            WindowCetak.setVisible(true);
            cmbJnsObat1.setSelectedItem(cmbJnsObat.getSelectedItem());
            
            if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)") || status.equals("ralan")) {
                cmbJnsRawat.setSelectedIndex(0);
            } else if (status.equals("ranap")) {
                cmbJnsRawat.setSelectedIndex(1);
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

    private void BtnCloseIn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn4ActionPerformed
        WindowCetak.dispose();
        tampil();
        emptTeks();
    }//GEN-LAST:event_BtnCloseIn4ActionPerformed

    private void BtnCetakLapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCetakLapActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("logo", Sequel.cariGambar("select logo from setting"));

        //semua rawat
        if (cmbJnsRawat.getSelectedIndex() == 2) {
            param.put("ruangan", "Semua");
            if (cmbJnsObat1.getSelectedIndex() == 0) {
                if (Sequel.cariInteger("select count(-1) from pemberian_obat where tgl_pemberian between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' "
                        + "and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and no_rawat='" + TNoRW.getText() + "'") == 0) {
                    JOptionPane.showMessageDialog(null, "Data tidak ditemukan, silahkan ulangi lagi...!!!!");
                } else {
                    param.put("pemberian", "SEMUA JENIS PEMBERIAN OBAT");
                    Valid.MyReport("rptCatatanBeriObat.jasper", "report", "::[ Catatan Laporan Pemberian Obat Pasien ]::",
                            "SELECT concat(po.jenis_obat,' - ',po.nama_obat) nama_obat, po.jlh_obat, po.dosis, po.cara_pemberian rute, date_format(po.tgl_pemberian,'%d-%m-%Y') tglberi, "
                            + "time_format(po.jadwal_pemberian,'%H:%i') jam, po.jlh_sisa_obat, pg.nama nmpetugas, p.no_rkm_medis, concat(p.nm_pasien,' (',p.jk,')') nmpasien, "
                            + "concat(rp.umurdaftar,' ',rp.sttsumur,', ',date_format(p.tgl_lahir,'%d/%m/%Y')) umur FROM pemberian_obat po "
                            + "inner join reg_periksa rp on rp.no_rawat=po.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                            + "inner join pegawai pg on pg.nik=po.nip_petugas where "
                            + "po.tgl_pemberian between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                            + "and po.no_rawat='" + TNoRW.getText() + "' order by po.tgl_pemberian, po.jadwal_pemberian, po.nama_obat", param);
                    tampil();
                    emptTeks();
                    WindowCetak.dispose();
                }
            } else {
                if (Sequel.cariInteger("select count(-1) from pemberian_obat where tgl_pemberian between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' "
                        + "and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and jenis_obat='" + cmbJnsObat1.getSelectedItem() + "' and no_rawat='" + TNoRW.getText() + "'") == 0) {
                    JOptionPane.showMessageDialog(null, "Data tidak ditemukan, silahkan ulangi lagi...!!!!");
                } else {
                    param.put("pemberian", "PEMBERIAN OBAT " + cmbJnsObat1.getSelectedItem());
                    Valid.MyReport("rptCatatanBeriObat.jasper", "report", "::[ Catatan Laporan Pemberian Obat Pasien ]::",
                            "SELECT concat(po.jenis_obat,' - ',po.nama_obat) nama_obat, po.jlh_obat, po.dosis, po.cara_pemberian rute, date_format(po.tgl_pemberian,'%d-%m-%Y') tglberi, "
                            + "time_format(po.jadwal_pemberian,'%H:%i') jam, po.jlh_sisa_obat, pg.nama nmpetugas, p.no_rkm_medis, concat(p.nm_pasien,' (',p.jk,')') nmpasien, "
                            + "concat(rp.umurdaftar,' ',rp.sttsumur,', ',date_format(p.tgl_lahir,'%d/%m/%Y')) umur FROM pemberian_obat po "
                            + "inner join reg_periksa rp on rp.no_rawat=po.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                            + "inner join pegawai pg on pg.nik=po.nip_petugas where "
                            + "po.tgl_pemberian between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                            + "and po.jenis_obat='" + cmbJnsObat1.getSelectedItem() + "' and po.no_rawat='" + TNoRW.getText() + "' "
                            + "order by po.tgl_pemberian, po.jadwal_pemberian, po.nama_obat", param);
                    tampil();
                    emptTeks();
                    WindowCetak.dispose();
                }
            }
            
            //rawat jalan
        } else if (cmbJnsRawat.getSelectedIndex() == 0) {
            param.put("ruangan", Sequel.cariIsi("select nm_unit from pemberian_obat where tgl_pemberian between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' "
                    + "and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and no_rawat='" + TNoRW.getText() + "' and nm_unit='IGD' order by waktu_simpan desc limit 1"));
            if (cmbJnsObat1.getSelectedIndex() == 0) {
                if (Sequel.cariInteger("select count(-1) from pemberian_obat where nm_unit='IGD' and tgl_pemberian between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' "
                        + "and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and no_rawat='" + TNoRW.getText() + "'") == 0) {
                    JOptionPane.showMessageDialog(null, "Data tidak ditemukan, silahkan ulangi lagi...!!!!");
                } else {
                    param.put("pemberian", "SEMUA JENIS PEMBERIAN OBAT");
                    Valid.MyReport("rptCatatanBeriObat.jasper", "report", "::[ Catatan Laporan Pemberian Obat Pasien ]::",
                            "SELECT concat(po.jenis_obat,' - ',po.nama_obat) nama_obat, po.jlh_obat, po.dosis, po.cara_pemberian rute, date_format(po.tgl_pemberian,'%d-%m-%Y') tglberi, "
                            + "time_format(po.jadwal_pemberian,'%H:%i') jam, po.jlh_sisa_obat, pg.nama nmpetugas, p.no_rkm_medis, concat(p.nm_pasien,' (',p.jk,')') nmpasien, "
                            + "concat(rp.umurdaftar,' ',rp.sttsumur,', ',date_format(p.tgl_lahir,'%d/%m/%Y')) umur FROM pemberian_obat po "
                            + "inner join reg_periksa rp on rp.no_rawat=po.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                            + "inner join pegawai pg on pg.nik=po.nip_petugas where po.nm_unit='IGD' and "
                            + "po.tgl_pemberian between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                            + "and po.no_rawat='" + TNoRW.getText() + "' order by po.tgl_pemberian, po.jadwal_pemberian, po.nama_obat", param);
                    tampil();
                    emptTeks();
                    WindowCetak.dispose();
                }
            } else {
                if (Sequel.cariInteger("select count(-1) from pemberian_obat where nm_unit='IGD' and tgl_pemberian between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' "
                        + "and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and jenis_obat='" + cmbJnsObat1.getSelectedItem() + "' and no_rawat='" + TNoRW.getText() + "'") == 0) {
                    JOptionPane.showMessageDialog(null, "Data tidak ditemukan, silahkan ulangi lagi...!!!!");
                } else {
                    param.put("pemberian", "PEMBERIAN OBAT " + cmbJnsObat1.getSelectedItem());
                    Valid.MyReport("rptCatatanBeriObat.jasper", "report", "::[ Catatan Laporan Pemberian Obat Pasien ]::",
                            "SELECT concat(po.jenis_obat,' - ',po.nama_obat) nama_obat, po.jlh_obat, po.dosis, po.cara_pemberian rute, date_format(po.tgl_pemberian,'%d-%m-%Y') tglberi, "
                            + "time_format(po.jadwal_pemberian,'%H:%i') jam, po.jlh_sisa_obat, pg.nama nmpetugas, p.no_rkm_medis, concat(p.nm_pasien,' (',p.jk,')') nmpasien, "
                            + "concat(rp.umurdaftar,' ',rp.sttsumur,', ',date_format(p.tgl_lahir,'%d/%m/%Y')) umur FROM pemberian_obat po "
                            + "inner join reg_periksa rp on rp.no_rawat=po.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                            + "inner join pegawai pg on pg.nik=po.nip_petugas where po.nm_unit='IGD' and "
                            + "po.tgl_pemberian between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                            + "and po.jenis_obat='" + cmbJnsObat1.getSelectedItem() + "' and po.no_rawat='" + TNoRW.getText() + "' "
                            + "order by po.tgl_pemberian, po.jadwal_pemberian, po.nama_obat", param);
                    tampil();
                    emptTeks();
                    WindowCetak.dispose();
                }
            }
            
            //rawat inap
        } else if (cmbJnsRawat.getSelectedIndex() == 1) {
            param.put("ruangan", Sequel.cariIsi("select nm_unit from pemberian_obat where tgl_pemberian between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' "
                    + "and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and no_rawat='" + TNoRW.getText() + "' and nm_unit<>'IGD' order by waktu_simpan desc limit 1"));
            if (cmbJnsObat1.getSelectedIndex() == 0) {
                if (Sequel.cariInteger("select count(-1) from pemberian_obat where nm_unit<>'IGD' and tgl_pemberian between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' "
                        + "and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and no_rawat='" + TNoRW.getText() + "'") == 0) {
                    JOptionPane.showMessageDialog(null, "Data tidak ditemukan, silahkan ulangi lagi...!!!!");
                } else {
                    param.put("pemberian", "SEMUA JENIS PEMBERIAN OBAT");
                    Valid.MyReport("rptCatatanBeriObat.jasper", "report", "::[ Catatan Laporan Pemberian Obat Pasien ]::",
                            "SELECT concat(po.jenis_obat,' - ',po.nama_obat) nama_obat, po.jlh_obat, po.dosis, po.cara_pemberian rute, date_format(po.tgl_pemberian,'%d-%m-%Y') tglberi, "
                            + "time_format(po.jadwal_pemberian,'%H:%i') jam, po.jlh_sisa_obat, pg.nama nmpetugas, p.no_rkm_medis, concat(p.nm_pasien,' (',p.jk,')') nmpasien, "
                            + "concat(rp.umurdaftar,' ',rp.sttsumur,', ',date_format(p.tgl_lahir,'%d/%m/%Y')) umur FROM pemberian_obat po "
                            + "inner join reg_periksa rp on rp.no_rawat=po.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                            + "inner join pegawai pg on pg.nik=po.nip_petugas where po.nm_unit<>'IGD' and "
                            + "po.tgl_pemberian between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                            + "and po.no_rawat='" + TNoRW.getText() + "' order by po.tgl_pemberian, po.jadwal_pemberian, po.nama_obat", param);
                    tampil();
                    emptTeks();
                    WindowCetak.dispose();
                }
            } else {
                if (Sequel.cariInteger("select count(-1) from pemberian_obat where nm_unit<>'IGD' and tgl_pemberian between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' "
                        + "and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and jenis_obat='" + cmbJnsObat1.getSelectedItem() + "' and no_rawat='" + TNoRW.getText() + "'") == 0) {
                    JOptionPane.showMessageDialog(null, "Data tidak ditemukan, silahkan ulangi lagi...!!!!");
                } else {
                    param.put("pemberian", "PEMBERIAN OBAT " + cmbJnsObat1.getSelectedItem());
                    Valid.MyReport("rptCatatanBeriObat.jasper", "report", "::[ Catatan Laporan Pemberian Obat Pasien ]::",
                            "SELECT concat(po.jenis_obat,' - ',po.nama_obat) nama_obat, po.jlh_obat, po.dosis, po.cara_pemberian rute, date_format(po.tgl_pemberian,'%d-%m-%Y') tglberi, "
                            + "time_format(po.jadwal_pemberian,'%H:%i') jam, po.jlh_sisa_obat, pg.nama nmpetugas, p.no_rkm_medis, concat(p.nm_pasien,' (',p.jk,')') nmpasien, "
                            + "concat(rp.umurdaftar,' ',rp.sttsumur,', ',date_format(p.tgl_lahir,'%d/%m/%Y')) umur FROM pemberian_obat po "
                            + "inner join reg_periksa rp on rp.no_rawat=po.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                            + "inner join pegawai pg on pg.nik=po.nip_petugas where po.nm_unit<>'IGD' and "
                            + "po.tgl_pemberian between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                            + "and po.jenis_obat='" + cmbJnsObat1.getSelectedItem() + "' and po.no_rawat='" + TNoRW.getText() + "' "
                            + "order by po.tgl_pemberian, po.jadwal_pemberian, po.nama_obat", param);
                    tampil();
                    emptTeks();
                    WindowCetak.dispose();
                }
            }
        }
        this.setCursor(Cursor.getDefaultCursor());        
    }//GEN-LAST:event_BtnCetakLapActionPerformed

    private void BtnSimpanAtasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanAtasActionPerformed
        BtnSimpanActionPerformed(null);
    }//GEN-LAST:event_BtnSimpanAtasActionPerformed

    private void BtnBaruAtasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBaruAtasActionPerformed
        emptTeks();
    }//GEN-LAST:event_BtnBaruAtasActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgPemberianObatPasien dialog = new DlgPemberianObatPasien(new javax.swing.JFrame(), true);
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
    private widget.Button BtnBaruAtas;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnCari1;
    private widget.Button BtnCetakLap;
    private widget.Button BtnCloseIn1;
    private widget.Button BtnCloseIn4;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnObat;
    private widget.Button BtnPetugas;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Button BtnSimpanAtas;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private javax.swing.JMenuItem MnContengSemua;
    private javax.swing.JMenuItem MnCopy;
    private javax.swing.JMenuItem MnHapusConteng;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.TextBox TCari;
    private widget.TextBox TCari1;
    private widget.TextBox TNmPasien;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRW;
    private widget.TextBox Tjlh;
    private widget.TextArea TketRencana;
    private widget.TextBox Tpetugas;
    private javax.swing.JDialog WindowCetak;
    private javax.swing.JDialog WindowObat;
    private widget.TextBox caraPemberian;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbHlm;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbJnsObat;
    private widget.ComboBox cmbJnsObat1;
    private widget.ComboBox cmbJnsRawat;
    private widget.ComboBox cmbMnt;
    private widget.ComboBox cmbObat;
    private widget.TextBox dosis;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame5;
    private widget.InternalFrame internalFrame6;
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
    private widget.Label jLabel3;
    private widget.Label jLabel4;
    private widget.Label jLabel48;
    private widget.Label jLabel49;
    private widget.Label jLabel5;
    private widget.Label jLabel50;
    private widget.Label jLabel51;
    private widget.Label jLabel52;
    private widget.Label jLabel53;
    private widget.Label jLabel6;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu;
    private widget.TextBox jlhSisaObat;
    private widget.TextBox nmObat;
    private widget.TextBox nmUnit;
    private widget.TextBox nmdpjp;
    private widget.TextBox nmpasien;
    private widget.TextBox norm;
    private widget.panelisi panelGlass7;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.panelisi panelisi5;
    private widget.panelisi panelisi6;
    private widget.TextBox rencana;
    private widget.ScrollPane scrollPane12;
    private widget.Table tbFarmasi;
    private widget.Table tbObat;
    private widget.TextBox tglAsesmen;
    private widget.Tanggal tgl_beri;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            if (cmbHlm.getSelectedIndex() == 6) {
                if (cmbJnsObat.getSelectedIndex() == 0) {
                    ps = koneksi.prepareStatement("SELECT po.no_rawat, p.no_rkm_medis, p.nm_pasien from pemberian_obat po "
                            + "inner join reg_periksa rp on rp.no_rawat=po.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                            + "po.tgl_pemberian between ? and ? and p.no_rkm_medis like ? or "
                            + "po.tgl_pemberian between ? and ? and p.nm_pasien like ? or "
                            + "po.tgl_pemberian between ? and ? and po.no_rawat like ? "
                            + "group by po.no_rawat order by po.status, po.tgl_pemberian, po.jadwal_pemberian");
                } else {
                    ps = koneksi.prepareStatement("SELECT po.no_rawat, p.no_rkm_medis, p.nm_pasien from pemberian_obat po "
                            + "inner join reg_periksa rp on rp.no_rawat=po.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                            + "po.tgl_pemberian between ? and ? and po.jenis_obat like ? and p.no_rkm_medis like ? or "
                            + "po.tgl_pemberian between ? and ? and po.jenis_obat like ? and p.nm_pasien like ? or "
                            + "po.tgl_pemberian between ? and ? and po.jenis_obat like ? and po.no_rawat like ? "
                            + "group by po.no_rawat order by po.status, po.tgl_pemberian, po.jadwal_pemberian");
                }

            } else {
                if (cmbJnsObat.getSelectedIndex() == 0) {
                    ps = koneksi.prepareStatement("SELECT po.no_rawat, p.no_rkm_medis, p.nm_pasien from pemberian_obat po "
                            + "inner join reg_periksa rp on rp.no_rawat=po.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                            + "po.tgl_pemberian between ? and ? and p.no_rkm_medis like ? or "
                            + "po.tgl_pemberian between ? and ? and p.nm_pasien like ? or "
                            + "po.tgl_pemberian between ? and ? and po.no_rawat like ? "
                            + "group by po.no_rawat order by po.status, po.tgl_pemberian, po.jadwal_pemberian "
                            + "limit " + cmbHlm.getSelectedItem().toString() + "");
                } else {
                    ps = koneksi.prepareStatement("SELECT po.no_rawat, p.no_rkm_medis, p.nm_pasien from pemberian_obat po "
                            + "inner join reg_periksa rp on rp.no_rawat=po.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                            + "po.tgl_pemberian between ? and ? and po.jenis_obat like ? and p.no_rkm_medis like ? or "
                            + "po.tgl_pemberian between ? and ? and po.jenis_obat like ? and p.nm_pasien like ? or "
                            + "po.tgl_pemberian between ? and ? and po.jenis_obat like ? and po.no_rawat like ? "
                            + "group by po.no_rawat order by po.status, po.tgl_pemberian, po.jadwal_pemberian "
                            + "limit " + cmbHlm.getSelectedItem().toString() + "");
                }
            }
            try {
                if (cmbJnsObat.getSelectedIndex() == 0) {
                    ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                    ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                    ps.setString(3, "%" + TCari.getText().trim() + "%");
                    ps.setString(4, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                    ps.setString(5, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                    ps.setString(6, "%" + TCari.getText().trim() + "%");
                    ps.setString(7, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                    ps.setString(8, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                    ps.setString(9, "%" + TCari.getText().trim() + "%");
                } else {
                    ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                    ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                    ps.setString(3, "%" + cmbJnsObat.getSelectedItem().toString() + "%");
                    ps.setString(4, "%" + TCari.getText().trim() + "%");
                    ps.setString(5, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                    ps.setString(6, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                    ps.setString(7, "%" + cmbJnsObat.getSelectedItem().toString() + "%");
                    ps.setString(8, "%" + TCari.getText().trim() + "%");
                    ps.setString(9, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                    ps.setString(10, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                    ps.setString(11, "%" + cmbJnsObat.getSelectedItem().toString() + "%");
                    ps.setString(12, "%" + TCari.getText().trim() + "%");
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new Object[]{
                        false,
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"), ":",
                        "", "", "", "", "", "", "", "", "", "", "", "", "", ""
                    });

                    if (cmbHlm.getSelectedIndex() == 6) {
                        if (cmbJnsObat.getSelectedIndex() == 0) {
                            ps2 = koneksi.prepareStatement("SELECT po.nama_obat, po.dosis, po.cara_pemberian, time_format(po.jadwal_pemberian,'%H:%i') jam, po.jlh_sisa_obat, "
                                    + "po.STATUS, date_format( po.tgl_pemberian, '%d-%m-%Y' ) tglObat, po.kode_brng, po.tgl_pemberian, po.nm_unit, "
                                    + "po.jlh_obat, po.jenis_obat, pg.nama nmpetugas, po.nip_petugas, po.waktu_simpan "
                                    + "FROM pemberian_obat po INNER JOIN pegawai pg ON pg.nik = po.nip_petugas where "
                                    + "po.tgl_pemberian between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                                    + "and po.no_rawat='" + rs.getString("no_rawat") + "' order by po.status, po.tgl_pemberian, po.jadwal_pemberian");
                        } else {
                            ps2 = koneksi.prepareStatement("SELECT po.nama_obat, po.dosis, po.cara_pemberian, time_format(po.jadwal_pemberian,'%H:%i') jam, po.jlh_sisa_obat, "
                                    + "po.STATUS, date_format( po.tgl_pemberian, '%d-%m-%Y' ) tglObat, po.kode_brng, po.tgl_pemberian, po.nm_unit, "
                                    + "po.jlh_obat, po.jenis_obat, pg.nama nmpetugas, po.nip_petugas, po.waktu_simpan "
                                    + "FROM pemberian_obat po INNER JOIN pegawai pg ON pg.nik = po.nip_petugas where "
                                    + "po.tgl_pemberian between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                                    + "and po.jenis_obat='" + cmbJnsObat.getSelectedItem().toString() + "' and po.no_rawat='" + rs.getString("no_rawat") + "' "
                                    + "order by po.status, po.tgl_pemberian, po.jadwal_pemberian");
                        }

                    } else {
                        if (cmbJnsObat.getSelectedIndex() == 0) {
                            ps2 = koneksi.prepareStatement("SELECT po.nama_obat, po.dosis, po.cara_pemberian, time_format(po.jadwal_pemberian,'%H:%i') jam, po.jlh_sisa_obat, "
                                    + "po.STATUS, date_format( po.tgl_pemberian, '%d-%m-%Y' ) tglObat, po.kode_brng, po.tgl_pemberian, po.nm_unit, "
                                    + "po.jlh_obat, po.jenis_obat, pg.nama nmpetugas, po.nip_petugas, po.waktu_simpan "
                                    + "FROM pemberian_obat po INNER JOIN pegawai pg ON pg.nik = po.nip_petugas where "
                                    + "po.tgl_pemberian between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                                    + "and po.no_rawat='" + rs.getString("no_rawat") + "' order by po.status, po.tgl_pemberian, po.jadwal_pemberian "
                                    + "limit " + cmbHlm.getSelectedItem().toString() + "");
                        } else {
                            ps2 = koneksi.prepareStatement("SELECT po.nama_obat, po.dosis, po.cara_pemberian, time_format(po.jadwal_pemberian,'%H:%i') jam, po.jlh_sisa_obat, "
                                    + "po.STATUS, date_format( po.tgl_pemberian, '%d-%m-%Y' ) tglObat, po.kode_brng, po.tgl_pemberian, po.nm_unit, "
                                    + "po.jlh_obat, po.jenis_obat, pg.nama nmpetugas, po.nip_petugas, po.waktu_simpan "
                                    + "FROM pemberian_obat po INNER JOIN pegawai pg ON pg.nik = po.nip_petugas where "
                                    + "po.tgl_pemberian between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                                    + "and po.jenis_obat='" + cmbJnsObat.getSelectedItem().toString() + "' and po.no_rawat='" + rs.getString("no_rawat") + "' "
                                    + "order by po.status, po.tgl_pemberian, po.jadwal_pemberian limit " + cmbHlm.getSelectedItem().toString() + "");
                        }
                    }
                    try {
                        rs2 = ps2.executeQuery();
                        while (rs2.next()) {
                            tabMode.addRow(new Object[]{
                                false, "", "", "",
                                rs2.getString("nama_obat"),
                                rs2.getString("dosis"),
                                rs2.getString("cara_pemberian"),
                                rs2.getString("jam"),
                                rs2.getString("jlh_sisa_obat"),
                                rs2.getString("status"),
                                rs2.getString("waktu_simpan"),
                                rs2.getString("tglObat"),
                                rs2.getString("kode_brng"),
                                rs2.getString("tgl_pemberian"),
                                rs2.getString("nm_unit"),
                                rs2.getString("jlh_obat"),
                                rs2.getString("jenis_obat"),
                                rs2.getString("nmpetugas"),
                                rs2.getString("nip_petugas")
                            });
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    } finally {
                        if (rs2 != null) {
                            rs2.close();
                        }
                        if (ps2 != null) {
                            ps2.close();
                        }
                    }
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
    }

    public void emptTeks() {
        nmObat.setText("");
        dosis.setText("");
        kdobat = "";
        caraPemberian.setText("");
        cmbJam.setSelectedIndex(0);
        cmbMnt.setSelectedIndex(0);
        cmbDtk.setSelectedIndex(0);
        jlhSisaObat.setText("");
        cmbHlm.setSelectedIndex(0);
        tgl_beri.setDate(new Date());
        Tjlh.setText("");
        cmbObat.setSelectedIndex(0);
        cmbJnsObat.setSelectedIndex(0);
        nmObat.requestFocus();
    }

    private void getData() {
        kdobat = "";
        nipPetugas = "";
        waktuSimpan = "";
        
        if(tbObat.getSelectedRow()!= -1){
            waktuSimpan = tbObat.getValueAt(tbObat.getSelectedRow(),10).toString();
            tampilData();
        }
    }
    
    public void isCek(){
       BtnSimpan.setEnabled(akses.getpemberian_obat());
       BtnHapus.setEnabled(akses.getpemberian_obat());
       BtnEdit.setEnabled(akses.getpemberian_obat());
    }
    
    private void tampilObat() {
        Valid.tabelKosong(tabMode1);
        try {
            ps1 = koneksi.prepareStatement("SELECT kode_brng, nama_brng, kode_sat FROM databarang where "
                    + "kode_brng like ? or "
                    + "nama_brng like ? or "
                    + "kode_sat like ? order by nama_brng");
            try {
                ps1.setString(1, "%" + TCari1.getText().trim() + "%");
                ps1.setString(2, "%" + TCari1.getText().trim() + "%");
                ps1.setString(3, "%" + TCari1.getText().trim() + "%");
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    tabMode1.addRow(new Object[]{
                        rs1.getString("kode_brng"),
                        rs1.getString("nama_brng"),
                        rs1.getString("kode_sat")
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
    
    public void setData(String norw, String norm, String nmpasien, String sttsrawat, String unit) {
        TNoRW.setText(norw);
        TNoRM.setText(norm);
        status = sttsrawat;
        TNmPasien.setText(nmpasien);
        nmUnit.setText(unit);
        TCari.setText(norw);
        
        if (akses.getadmin() == true) {
            nipPetugas = "-";
            Tpetugas.setText("-");
        } else {
            nipPetugas = akses.getkode();
            Tpetugas.setText(Sequel.cariIsi("select nama from pegawai where nik='" + nipPetugas + "'"));
        }
    }
    
    private void tampilData() {
        try {
            ps3 = koneksi.prepareStatement("select *, p.no_rkm_medis, p.nm_pasien, pg.nama from pemberian_obat po "
                    + "inner join reg_periksa rp on rp.no_rawat=po.no_rawat "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "INNER JOIN pegawai pg ON pg.nik = po.nip_petugas "
                    + "where po.waktu_simpan='" + waktuSimpan + "'");
            try {
                rs3 = ps3.executeQuery();
                while (rs3.next()) {
                    TNoRW.setText(rs3.getString("no_rawat"));
                    TNoRM.setText(rs3.getString("no_rkm_medis"));
                    TNmPasien.setText(rs3.getString("nm_pasien"));
                    nmObat.setText(rs3.getString("nama_obat"));
                    kdobat = rs3.getString("kode_brng");
                    Tjlh.setText(rs3.getString("jlh_obat"));
                    dosis.setText(rs3.getString("dosis"));
                    caraPemberian.setText(rs3.getString("cara_pemberian"));
                    Valid.SetTgl(tgl_beri, rs3.getString("tgl_pemberian"));
                    cmbJam.setSelectedItem(rs3.getString("jadwal_pemberian").substring(0, 2));
                    cmbMnt.setSelectedItem(rs3.getString("jadwal_pemberian").substring(3, 5));
                    cmbDtk.setSelectedItem(rs3.getString("jadwal_pemberian").substring(6, 8));
                    jlhSisaObat.setText(rs3.getString("jlh_sisa_obat"));
                    nmUnit.setText(rs3.getString("nm_unit"));
                    cmbObat.setSelectedItem(rs3.getString("jenis_obat"));
                    nipPetugas = rs3.getString("nip_petugas");
                    Tpetugas.setText(rs3.getString("nama"));
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
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
}
