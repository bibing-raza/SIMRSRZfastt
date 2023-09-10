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
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import simrskhanza.DlgCariDokter;

/**
 *
 * @author dosen
 */
public class DlgCPPT extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps, pps1, pps2;
    private ResultSet rs, rrs1, rrs2;
    private int x = 0, pilihan = 0;
    private DlgCariDokter dokter = new DlgCariDokter(null, false);  

    /** Creates new form DlgSpesialis
     * @param parent
     * @param modal */
    public DlgCPPT(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        Object[] row = {"No. Rawat", "No. RM", "Nama Pasien", "Tgl. Lahir", "Tgl. CPPT",
            "Bagian", "Hasil Pemeriksaan", "Instruksi Nakes", "Verifikasi", "Nama DPJP",
            "Status", "tanggal", "nip_dpjp", "wkt_simpan"};
        tabMode = new DefaultTableModel(null, row) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbCPPT.setModel(tabMode);
        tbCPPT.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbCPPT.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 14; i++) {
            TableColumn column = tbCPPT.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(220);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else if (i == 4) {
                column.setPreferredWidth(120);
            } else if (i == 5) {
                column.setPreferredWidth(180);
            } else if (i == 6) {
                column.setPreferredWidth(250);
            } else if (i == 7) {
                column.setPreferredWidth(250);
            } else if (i == 8) {
                column.setPreferredWidth(70);
            } else if (i == 9) {
                column.setPreferredWidth(250);
            } else if (i == 10) {
                column.setPreferredWidth(65);
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

        tbCPPT.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1 = new DefaultTableModel(null, new Object[]{"No. RM", "Nama Pasien", "Data Template"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbTemplate.setModel(tabMode1);
        tbTemplate.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbTemplate.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 3; i++) {
            TableColumn column = tbTemplate.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(60);
            } else if (i == 1) {
                column.setPreferredWidth(200);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            }
        }
        tbTemplate.setDefaultRenderer(Object.class, new WarnaTable());

        TBagian.setDocument(new batasInput((int) 100).getKata(TBagian));
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
        
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgCPPT")) {
                    if (dokter.getTable().getSelectedRow() != -1) {
                        kddpjp.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                        nmdpjp.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                    }
                    BtnDPJP.requestFocus();
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

        WindowTemplate = new javax.swing.JDialog();
        internalFrame5 = new widget.InternalFrame();
        panelisi4 = new widget.panelisi();
        jLabel36 = new widget.Label();
        TCari1 = new widget.TextBox();
        BtnCari1 = new widget.Button();
        BtnCopas = new widget.Button();
        BtnCloseIn1 = new widget.Button();
        jPanel1 = new javax.swing.JPanel();
        Scroll1 = new widget.ScrollPane();
        tbTemplate = new widget.Table();
        Scroll3 = new widget.ScrollPane();
        Ttemplate = new widget.TextArea();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbCPPT = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass9 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        panelGlass7 = new widget.panelisi();
        jLabel3 = new widget.Label();
        kddpjp = new widget.TextBox();
        nmdpjp = new widget.TextBox();
        jLabel5 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRm = new widget.TextBox();
        BtnDPJP = new widget.Button();
        TPasien = new widget.TextBox();
        jLabel8 = new widget.Label();
        tglCppt = new widget.Tanggal();
        jLabel9 = new widget.Label();
        TBagian = new widget.TextBox();
        jLabel10 = new widget.Label();
        jLabel11 = new widget.Label();
        scrollPane2 = new widget.ScrollPane();
        THasil = new widget.TextArea();
        jLabel12 = new widget.Label();
        jLabel13 = new widget.Label();
        scrollPane3 = new widget.ScrollPane();
        TInstruksi = new widget.TextArea();
        jLabel14 = new widget.Label();
        cmbVerifikasi = new widget.ComboBox();
        BtnHasil = new widget.Button();
        BtnInstruksi = new widget.Button();

        WindowTemplate.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowTemplate.setName("WindowTemplate"); // NOI18N
        WindowTemplate.setUndecorated(true);
        WindowTemplate.setResizable(false);

        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template CPPT ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame5.setLayout(new java.awt.BorderLayout());

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

        internalFrame5.add(panelisi4, java.awt.BorderLayout.CENTER);

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(816, 250));
        jPanel1.setLayout(new java.awt.GridLayout(1, 2));

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);
        Scroll1.setPreferredSize(new java.awt.Dimension(452, 250));

        tbTemplate.setToolTipText("Silahkan klik salah satu data yang akan dipakai");
        tbTemplate.setName("tbTemplate"); // NOI18N
        tbTemplate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTemplateMouseClicked(evt);
            }
        });
        Scroll1.setViewportView(tbTemplate);

        jPanel1.add(Scroll1);

        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);

        Ttemplate.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Baca Template Dipilih ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Ttemplate.setColumns(20);
        Ttemplate.setRows(5);
        Ttemplate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Ttemplate.setName("Ttemplate"); // NOI18N
        Scroll3.setViewportView(Ttemplate);

        jPanel1.add(Scroll3);

        internalFrame5.add(jPanel1, java.awt.BorderLayout.PAGE_START);

        WindowTemplate.getContentPane().add(internalFrame5, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Catatan Perkembangan Pasien Terintegrasi (CPPT) ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbCPPT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbCPPT.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbCPPT.setName("tbCPPT"); // NOI18N
        tbCPPT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbCPPTMouseClicked(evt);
            }
        });
        tbCPPT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbCPPTKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbCPPTKeyReleased(evt);
            }
        });
        Scroll.setViewportView(tbCPPT);

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
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 3, 9));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tgl. CPPT :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10-09-2023" }));
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

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10-09-2023" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari2);

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

        panelGlass7.setName("panelGlass7"); // NOI18N
        panelGlass7.setPreferredSize(new java.awt.Dimension(44, 340));
        panelGlass7.setLayout(null);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Nama DPJP :");
        jLabel3.setName("jLabel3"); // NOI18N
        panelGlass7.add(jLabel3);
        jLabel3.setBounds(0, 304, 180, 23);

        kddpjp.setEditable(false);
        kddpjp.setForeground(new java.awt.Color(0, 0, 0));
        kddpjp.setName("kddpjp"); // NOI18N
        panelGlass7.add(kddpjp);
        kddpjp.setBounds(186, 304, 130, 23);

        nmdpjp.setEditable(false);
        nmdpjp.setForeground(new java.awt.Color(0, 0, 0));
        nmdpjp.setName("nmdpjp"); // NOI18N
        panelGlass7.add(nmdpjp);
        nmdpjp.setBounds(320, 304, 460, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Pasien :");
        jLabel5.setName("jLabel5"); // NOI18N
        panelGlass7.add(jLabel5);
        jLabel5.setBounds(0, 10, 180, 23);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        panelGlass7.add(TNoRw);
        TNoRw.setBounds(185, 10, 122, 23);

        TNoRm.setEditable(false);
        TNoRm.setForeground(new java.awt.Color(0, 0, 0));
        TNoRm.setName("TNoRm"); // NOI18N
        panelGlass7.add(TNoRm);
        TNoRm.setBounds(312, 10, 70, 23);

        BtnDPJP.setForeground(new java.awt.Color(0, 0, 0));
        BtnDPJP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDPJP.setMnemonic('1');
        BtnDPJP.setToolTipText("Alt+1");
        BtnDPJP.setName("BtnDPJP"); // NOI18N
        BtnDPJP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDPJPActionPerformed(evt);
            }
        });
        panelGlass7.add(BtnDPJP);
        BtnDPJP.setBounds(784, 304, 28, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setName("TPasien"); // NOI18N
        panelGlass7.add(TPasien);
        TPasien.setBounds(386, 10, 430, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Tanggal :");
        jLabel8.setName("jLabel8"); // NOI18N
        panelGlass7.add(jLabel8);
        jLabel8.setBounds(0, 40, 180, 23);

        tglCppt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10-09-2023 21:27:18" }));
        tglCppt.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        tglCppt.setName("tglCppt"); // NOI18N
        tglCppt.setOpaque(false);
        panelGlass7.add(tglCppt);
        tglCppt.setBounds(185, 40, 135, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Bagian :");
        jLabel9.setName("jLabel9"); // NOI18N
        panelGlass7.add(jLabel9);
        jLabel9.setBounds(323, 40, 58, 23);

        TBagian.setForeground(new java.awt.Color(0, 0, 0));
        TBagian.setName("TBagian"); // NOI18N
        TBagian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TBagianKeyPressed(evt);
            }
        });
        panelGlass7.add(TBagian);
        TBagian.setBounds(386, 40, 430, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Hasil Pemeriksaan, Analisa, :");
        jLabel10.setName("jLabel10"); // NOI18N
        panelGlass7.add(jLabel10);
        jLabel10.setBounds(0, 70, 180, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Rencana, Penatalaksanaan Pasien ");
        jLabel11.setName("jLabel11"); // NOI18N
        panelGlass7.add(jLabel11);
        jLabel11.setBounds(0, 90, 180, 23);

        scrollPane2.setName("scrollPane2"); // NOI18N

        THasil.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        THasil.setColumns(20);
        THasil.setRows(5);
        THasil.setName("THasil"); // NOI18N
        THasil.setPreferredSize(new java.awt.Dimension(162, 360));
        THasil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                THasilKeyPressed(evt);
            }
        });
        scrollPane2.setViewportView(THasil);

        panelGlass7.add(scrollPane2);
        scrollPane2.setBounds(185, 70, 630, 110);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Instruksi Tenaga Kesehatan :");
        jLabel12.setName("jLabel12"); // NOI18N
        panelGlass7.add(jLabel12);
        jLabel12.setBounds(0, 186, 180, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Termasuk Pasca Bedah/Prosedur  ");
        jLabel13.setName("jLabel13"); // NOI18N
        panelGlass7.add(jLabel13);
        jLabel13.setBounds(0, 206, 180, 23);

        scrollPane3.setName("scrollPane3"); // NOI18N

        TInstruksi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TInstruksi.setColumns(20);
        TInstruksi.setRows(5);
        TInstruksi.setName("TInstruksi"); // NOI18N
        TInstruksi.setPreferredSize(new java.awt.Dimension(162, 360));
        TInstruksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TInstruksiKeyPressed(evt);
            }
        });
        scrollPane3.setViewportView(TInstruksi);

        panelGlass7.add(scrollPane3);
        scrollPane3.setBounds(185, 186, 630, 110);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Verifikasi :");
        jLabel14.setName("jLabel14"); // NOI18N
        panelGlass7.add(jLabel14);
        jLabel14.setBounds(830, 10, 60, 23);

        cmbVerifikasi.setForeground(new java.awt.Color(0, 0, 0));
        cmbVerifikasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Belum", "Sudah" }));
        cmbVerifikasi.setName("cmbVerifikasi"); // NOI18N
        panelGlass7.add(cmbVerifikasi);
        cmbVerifikasi.setBounds(896, 10, 65, 23);

        BtnHasil.setForeground(new java.awt.Color(0, 0, 0));
        BtnHasil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnHasil.setMnemonic('2');
        BtnHasil.setText("Template");
        BtnHasil.setToolTipText("Alt+2");
        BtnHasil.setName("BtnHasil"); // NOI18N
        BtnHasil.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnHasil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHasilActionPerformed(evt);
            }
        });
        panelGlass7.add(BtnHasil);
        BtnHasil.setBounds(825, 70, 100, 23);

        BtnInstruksi.setForeground(new java.awt.Color(0, 0, 0));
        BtnInstruksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnInstruksi.setMnemonic('2');
        BtnInstruksi.setText("Template");
        BtnInstruksi.setToolTipText("Alt+2");
        BtnInstruksi.setName("BtnInstruksi"); // NOI18N
        BtnInstruksi.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnInstruksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnInstruksiActionPerformed(evt);
            }
        });
        panelGlass7.add(BtnInstruksi);
        BtnInstruksi.setBounds(825, 186, 100, 23);

        internalFrame1.add(panelGlass7, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            if (kddpjp.getText().equals("")) {
                kddpjp.setText("-");
                nmdpjp.setText("-");
            } else {
                kddpjp.setText(kddpjp.getText());
                nmdpjp.setText(nmdpjp.getText());
            }

            Sequel.menyimpan("cppt", "'" + TNoRw.getText() + "',"
                    + "'" + Valid.SetTgl(tglCppt.getSelectedItem() + "") + " " + tglCppt.getSelectedItem().toString().substring(11, 19) + "',"
                    + "'" + TBagian.getText() + "','" + THasil.getText() + "','" + TInstruksi.getText() + "',"
                    + "'" + cmbVerifikasi.getSelectedItem().toString() + "','" + kddpjp.getText() + "','Ralan',"
                    + "'" + Sequel.cariIsi("select now()") + "'", "CPPT IGD");
            tampil();
            emptTeks();
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbCPPT.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.queryu2tf("delete from cppt where waktu_simpan=?", 1, new String[]{
                    tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 13).toString()
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

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            if (kddpjp.getText().equals("")) {
                kddpjp.setText("-");
                nmdpjp.setText("-");
            } else {
                kddpjp.setText(kddpjp.getText());
                nmdpjp.setText(nmdpjp.getText());
            }

            if (tbCPPT.getSelectedRow() > -1) {
                Sequel.mengedit("cppt", "waktu_simpan=?", "tanggal=?, bagian=?, hasil_pemeriksaan=?, "
                        + "instruksi_nakes=?, verifikasi=?, nip_dpjp=?", 7, new String[]{
                            Valid.SetTgl(tglCppt.getSelectedItem() + "") + " " + tglCppt.getSelectedItem().toString().substring(11, 19),
                            TBagian.getText(), THasil.getText(), TInstruksi.getText(), cmbVerifikasi.getSelectedItem().toString(), kddpjp.getText(),
                            tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 13).toString()
                        });
                tampil();
                emptTeks();
            }
        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
        WindowTemplate.dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnEdit,TCari);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            tbCPPT.requestFocus();
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
            Valid.pindah(evt, BtnCari, kddpjp);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbCPPTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCPPTMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbCPPTMouseClicked

    private void tbCPPTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbCPPTKeyPressed
        if(tabMode.getRowCount()!=0){
            if(evt.getKeyCode()==KeyEvent.VK_SHIFT){
                TCari.setText("");
                TCari.requestFocus();
            }           
        }
}//GEN-LAST:event_tbCPPTKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
        emptTeks();
    }//GEN-LAST:event_formWindowOpened

    private void tbCPPTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbCPPTKeyReleased
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }          
        }
    }//GEN-LAST:event_tbCPPTKeyReleased

    private void BtnDPJPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDPJPActionPerformed
        akses.setform("DlgCPPT");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDPJPActionPerformed

    private void THasilKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_THasilKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TInstruksi.requestFocus();
        }
    }//GEN-LAST:event_THasilKeyPressed

    private void TInstruksiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TInstruksiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            BtnDPJP.requestFocus();
        }
    }//GEN-LAST:event_TInstruksiKeyPressed

    private void TBagianKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TBagianKeyPressed
        Valid.pindah(evt, tglCppt, THasil);
    }//GEN-LAST:event_TBagianKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
//        if (tbAsesmen.getSelectedRow() > -1) {
//            Map<String, Object> param = new HashMap<>();
//            param.put("namars", akses.getnamars());
//            param.put("logo", Sequel.cariGambar("select logo from setting"));
//
//            if (ChkSkriningA.isSelected() == true) {
//                param.put("jenisSkrining", ChkSkriningA.getText());
//                param.put("kalimatSkrining", "1. Terdapat penurunan BB atau BB menetap (pada bayi < 1 tahun) selama >= 2 bulan                 Skor (" + skorGZ3.getText() + ")\n\n"
//                    + "2. Terdapat tanda-tanda klinis gangguan gizi (tampak kurus, gemuk, pendek, edema, moon face,   Skor (" + skorGZ4.getText() + ")\n"
//                    + "tampak tua, iga gambang, baggy pant, anoreksia) selama 1 bulan terakhir\n\n"
//                    + "3. Terdapat salah satu penyakit/kondisi yg. beresiko mengakibatkan malnutrisi berikut :                 Skor (" + skorGZ5.getText() + ")\n"
//                    + "* Diare berat (> 5x/hari) dan atau muntah (> 3x/hari)\n"
//                    + "* Penurunan asupan makanan selama lebih dari 7 hari\n\n"
//                    + "Terdapat penyakit-penyakit / keadaan yg. meningkatkan resiko malnutrisi antara lain :                   Skor (" + skorGZ6.getText() + ")\n"
//                    + "* Diare kronik > 2 minggu                          * Penyakit hati/ginjal kronik\n"
//                    + "* Penyakit jantung bawaan (tersangka)        * TB Paru\n"
//                    + "* Infeksi HIV (tersangka)                            * Renca/paska operasimayor\n"
//                    + "* Kelainan anatomi bawaan                         * Luka bakar luas\n"
//                    + "* Kelainan metabolisme bawaan                  * Terpasang stoma\n"
//                    + "* Retardasi mental                                     * Trauma\n"
//                    + "* Keterlambatan perkembangan                  * Lain-lain : " + lainGZanak.getText() + "\n"
//                    + "* Kanker (tersangka)\n"
//                    + "_______________________________________________________________________\n"
//                    + "Total Skor : (" + TotSkorGZA.getText() + ")\n"
//                    + "Kesimpulan Skrining Gizi Anak :\n"
//                    + kesimpulanGZanak.getText() + "\n");
//                param.put("resikoJatuh", "Anak (Skala Humpty Dumpty)");
//                param.put("tindakanRJ", "ANAK");
//                if (TabPencegahanAnak.getSelectedIndex() == 0) {
//                    param.put("JudultindakanRJ", "Pencegahan Umum (A)");
//                    param.put("IsitindakanRJ", anakA.getText());
//                } else if (TabPencegahanAnak.getSelectedIndex() == 1) {
//                    param.put("JudultindakanRJ", "Pencegahan Resiko Tinggi (B)");
//                    param.put("IsitindakanRJ", anakB.getText());
//                }
//
//            } else if (ChkSkriningD.isSelected() == true) {
//                param.put("jenisSkrining", ChkSkriningD.getText());
//                param.put("kalimatSkrining", "1. Apakah pasien mengalami penurunan BB yang tidak direncanakan/tidak diinginkan dalam 6 bulan terakhir ?\n"
//                    + cmbDewasaGZ1.getSelectedItem().toString() + "   Skor (" + skorGZ1.getText() + ")\n"
//                    + cmbDewasaYaGZ1.getSelectedItem().toString() + "   Skor (" + skorYaGZ1.getText() + ")\n\n"
//                    + "2. Apakah asupan makan pasien berkurang karena penurunan nafsu makan / kesulitan menerima makanan ?\n"
//                    + cmbDewasaGZ2.getSelectedItem().toString() + "   Skor (" + skorGZ2.getText() + ")\n"
//                    + "_______________________________________________________________________\n"
//                    + "Total Skor : (" + TotSkorGZD.getText() + ")\n"
//                    + "Kesimpulan Skrining Gizi Dewasa :\n"
//                    + kesimpulanGZDewasa.getText() + "\n");
//                param.put("resikoJatuh", "Dewasa (Skala Morse)");
//                param.put("tindakanRJ", "DEWASA");
//                if (TabPencegahanDewasa.getSelectedIndex() == 0) {
//                    param.put("JudultindakanRJ", "Pencegahan Umum (A)");
//                    param.put("IsitindakanRJ", dewasaA.getText());
//                } else if (TabPencegahanDewasa.getSelectedIndex() == 1) {
//                    param.put("JudultindakanRJ", "Pencegahan Resiko Sedang (B)");
//                    param.put("IsitindakanRJ", dewasaB.getText());
//                } else if (TabPencegahanDewasa.getSelectedIndex() == 2) {
//                    param.put("JudultindakanRJ", "Pencegahan Resiko Tinggi (C)");
//                    param.put("IsitindakanRJ", dewasaC.getText());
//                }
//
//            } else if (ChkSkriningA.isSelected() == false && ChkSkriningD.isSelected() == false) {
//                param.put("jenisSkrining", "");
//                param.put("kalimatSkrining", "");
//                param.put("resikoJatuh", "");
//                param.put("tindakanRJ", "");
//                param.put("JudultindakanRJ", "");
//                param.put("IsitindakanRJ", "");
//            }
//
//            //faktor resiko
//            try {
//                faktorresikoigd = "";
//                ps4 = koneksi.prepareStatement("select m.kode_resiko, concat('Faktor : ',m.faktor_resiko,', Skala : ',m.skala,', Skor (',m.skor,')') resiko "
//                    + "FROM master_faktor_resiko_igd m INNER JOIN penilaian_awal_keperawatan_igd_resiko pm ON pm.kode_resiko = m.kode_resiko "
//                    + "WHERE pm.no_rawat=? ORDER BY pm.kode_resiko");
//                try {
//                    ps4.setString(1, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString());
//                    rs4 = ps4.executeQuery();
//                    while (rs4.next()) {
//                        faktorresikoigd = rs4.getString("resiko") + "\n" + faktorresikoigd;
//                    }
//
//                    if (faktorresikoigd.endsWith("\n")) {
//                        faktorresikoigd = faktorresikoigd.substring(0, faktorresikoigd.length() - 1);
//                    }
//
//                } catch (Exception e) {
//                    System.out.println("Notif : " + e);
//                } finally {
//                    if (rs4 != null) {
//                        rs4.close();
//                    }
//                    if (ps4 != null) {
//                        ps4.close();
//                    }
//                }
//            } catch (Exception e) {
//                System.out.println("Notif : " + e);
//            }
//
//            if (ChkSkriningA.isSelected() == false && ChkSkriningD.isSelected() == false) {
//                param.put("dataResiko", "");
//                param.put("TotSkorResikoJatuh", "");
//                param.put("KesResikoJatuh", "");
//            } else {
//                param.put("dataResiko", faktorresikoigd);
//                param.put("TotSkorResikoJatuh", "Total Skor : (" + TotSkorRJ.getText() + ")");
//                param.put("KesResikoJatuh", "Kesimpulan Skor Resiko Jatuh :\n" + kesimpulanResikoJatuh.getText());
//            }
//
//            Valid.MyReport("rptCetakPenilaianAwalKeperawatanIGD1.jasper", "report", "::[ Laporan Penilaian Awal Keperawatan IGD hal. 2 ]::",
//                "SELECT if(pa.nyeri='Ya',concat(pa.nyeri,', Lokasi : ',pa.ya_nyeri_lokasi),pa.nyeri) nyeri, pa.jenis_nyeri, "
//                + "if(pa.provocation='Lainnya',concat(pa.provocation,', ',pa.provocation_lain),pa.provocation) provokes, if(pa.quality='Lainnya',concat(pa.quality,', ',pa.quality_lain),pa.quality) quality, "
//                + "pa.radiation radiasi, pa.severity severity_nyeri, concat(pa.time,', Lama : ',pa.time_lama) time_nyeri, pa.skala_nyeri, pa.diagnosa_keperawatan, pa.tindakan_keperawatan, pa.evaluasi_keperawatan, "
//                + "if(pa.identifikasi1='ya','V','') iden1, if(pa.identifikasi2='ya','V','') iden2, if(pa.identifikasi3='ya','V','') iden3, if(pa.identifikasi4='ya','V','') iden4, if(pa.identifikasi5='ya','V','') iden5, "
//                + "if(pa.identifikasi6='ya','V','') iden6, if(pa.identifikasi7='ya','V','') iden7, if(pa.identifikasi8='ya','V','') iden8, if(pa.identifikasi9='ya','V','') iden9, if(pa.identifikasi10='ya','V','') iden10, "
//                + "if(pa.manajer_pelayanan='ya','V','') mpp, if(pa.discharge_planing='ya','V','') dp, concat('Tanggal, ',date_format(pa.tgl_verifikasi,'%d-%m-%Y'),'   Jam ',TIME_FORMAT(pa.tgl_verifikasi,'%H:%i:%S')) tglverif, "
//                + "pg1.nama dr_dpjp, pg2.nama perawat from penilaian_awal_keperawatan_igdrz pa inner join reg_periksa rp on rp.no_rawat=pa.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
//                + "inner join pegawai pg1 on pg1.nik=pa.nip_dpjp inner join pegawai pg2 on pg2.nik=pa.nip_perawat where "
//                + "pa.no_rawat='" + tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString() + "'", param);
//
//            Valid.MyReport("rptCetakPenilaianAwalKeperawatanIGD.jasper", "report", "::[ Laporan Penilaian Awal Keperawatan IGD hal. 1 ]::",
//                "SELECT p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllhr, concat('Tanggal : ', date_format(pa.tanggal,'%d-%m-%Y'),', Pukul : ',time_format(pa.tanggal,'%H:%i-%S')) tgl, "
//                + "pa.keluhan_utama, pa.td tensi, pa.nadi, pa.nafas, pa.suhu, pa.bb, pa.tb, pa.asesmen_psikologis psikologis, if(pa.masalah_perilaku='Ada',concat(pa.masalah_perilaku,', Sebutkan : ',pa.sebutkan_perilaku),pa.masalah_perilaku) perilaku, "
//                + "p.stts_nikah, pa.hubungan_pasien hubungan, if(pa.tempat_tinggal='Lainnya',concat(pa.tempat_tinggal,', ',pa.lainya_tempt_tgl),pa.tempat_tinggal) tmpttgl, p.pekerjaan, "
//                + "pa.alat_bantu, if(pa.cacat_tubuh='Ada',concat(pa.cacat_tubuh,', ',pa.ada_cacat_tubuh),pa.cacat_tubuh) cacat, "
//                + "pa.riwayat_alergi, if(pa.alergi_obat='ya','V','') aler_obat, if(pa.alergi_obat='ya',pa.reaksi_alergi_obat,'') reak_obat, if(pa.alergi_makanan='ya','V','') aler_mak, "
//                + "if(pa.alergi_makanan='ya',pa.reaksi_alergi_makanan,'') reak_mak, if(pa.alergi_lainnya='ya','V','') aler_lain, if(pa.alergi_lainnya='ya',pa.reaksi_alergi_lainnya,'') reak_lain, "
//                + "if(pa.pin_kancing='ya','V','') pin, pa.alergi_diberitahukan, if(pa.nyeri='Ya',concat(pa.nyeri,', Lokasi : ',pa.ya_nyeri_lokasi),pa.nyeri) nyeri, pa.jenis_nyeri, "
//                + "if(pa.provocation='Lainnya',concat(pa.provocation,', ',pa.provocation_lain),pa.provocation) provokes, if(pa.quality='Lainnya',concat(pa.quality,', ',pa.quality_lain),pa.quality) quality, "
//                + "pa.radiation radiasi, pa.severity severity_nyeri, concat(pa.time,', Lama : ',pa.time_lama) time_nyeri, pa.diagnosa_keperawatan, pa.tindakan_keperawatan, pa.evaluasi_keperawatan, if(pa.identifikasi1='ya','V','') iden1, "
//                + "if(pa.identifikasi2='ya','V','') iden2, if(pa.identifikasi3='ya','V','') iden3, if(pa.identifikasi4='ya','V','') iden4, if(pa.identifikasi5='ya','V','') iden5, if(pa.identifikasi6='ya','V','') iden6, "
//                + "if(pa.identifikasi7='ya','V','') iden7, if(pa.identifikasi8='ya','V','') iden8, if(pa.identifikasi9='ya','V','') iden9, if(pa.identifikasi10='ya','V','') iden10, "
//                + "if(pa.manajer_pelayanan='ya','V','') mpp, if(pa.discharge_planing='ya','V','') dp from penilaian_awal_keperawatan_igdrz pa "
//                + "inner join reg_periksa rp on rp.no_rawat=pa.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
//                + "pa.no_rawat='" + tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString() + "'", param);
//        } else {
//            JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih data terlebih dahulu..!!!!");
//        }
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnHasilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHasilActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 1;
        tampilTemplate();
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Hasil Pemeriksaan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        WindowTemplate.setSize(998, 325);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnHasilActionPerformed

    private void BtnInstruksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnInstruksiActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 2;
        tampilTemplate();
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Instruksi Nakes ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        WindowTemplate.setSize(998, 325);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnInstruksiActionPerformed

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
            copas();
            WindowTemplate.dispose();
        }
    }//GEN-LAST:event_BtnCopasActionPerformed

    private void BtnCloseIn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn1ActionPerformed
        WindowTemplate.dispose();
    }//GEN-LAST:event_BtnCloseIn1ActionPerformed

    private void tbTemplateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTemplateMouseClicked
        if(tabMode1.getRowCount() != 0) {
            try {
                if (tbTemplate.getSelectedRow() != -1) {
                    Ttemplate.setText(tbTemplate.getValueAt(tbTemplate.getSelectedRow(), 2).toString());
                }
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbTemplateMouseClicked

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgCPPT dialog = new DlgCPPT(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCari1;
    private widget.Button BtnCloseIn1;
    private widget.Button BtnCopas;
    private widget.Button BtnDPJP;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnHasil;
    private widget.Button BtnInstruksi;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Label LCount;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll3;
    private widget.TextBox TBagian;
    private widget.TextBox TCari;
    private widget.TextBox TCari1;
    private widget.TextArea THasil;
    private widget.TextArea TInstruksi;
    private widget.TextBox TNoRm;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextArea Ttemplate;
    private javax.swing.JDialog WindowTemplate;
    private widget.ComboBox cmbVerifikasi;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame5;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel3;
    private widget.Label jLabel36;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private widget.TextBox kddpjp;
    private widget.TextBox nmdpjp;
    private widget.panelisi panelGlass7;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.panelisi panelisi4;
    private widget.ScrollPane scrollPane2;
    private widget.ScrollPane scrollPane3;
    private widget.Table tbCPPT;
    private widget.Table tbTemplate;
    private widget.Tanggal tglCppt;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT c.no_rawat, p.no_rkm_medis, p.nm_pasien, DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y') tgllhr, "
                    + "DATE_FORMAT(c.tanggal,'%d-%m-%Y %H:%i:%s') tglcppt, c.bagian, c.hasil_pemeriksaan, c.instruksi_nakes, "
                    + "c.verifikasi, pg.nama nmdpjp, c.status, c.tanggal, c.nip_dpjp, c.waktu_simpan from cppt c "
                    + "inner join reg_periksa rp on rp.no_rawat=c.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "inner join pegawai pg on pg.nik=c.nip_dpjp where "
                    + "date(c.tanggal) between ? and ? and c.no_rawat like ? or "
                    + "date(c.tanggal) between ? and ? and p.no_rkm_medis like ? or "
                    + "date(c.tanggal) between ? and ? and p.nm_pasien like ? or "
                    + "date(c.tanggal) between ? and ? and c.bagian like ? or "
                    + "date(c.tanggal) between ? and ? and c.hasil_pemeriksaan like ? or "
                    + "date(c.tanggal) between ? and ? and c.instruksi_nakes like ? or "
                    + "date(c.tanggal) between ? and ? and c.verifikasi like ? or "
                    + "date(c.tanggal) between ? and ? and pg.nama like ? or "
                    + "date(c.tanggal) between ? and ? and c.status like ? order by c.waktu_simpan desc");
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
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"), 
                        rs.getString("no_rkm_medis"), 
                        rs.getString("nm_pasien"), 
                        rs.getString("tgllhr"),
                        rs.getString("tglcppt"),
                        rs.getString("bagian"),
                        rs.getString("hasil_pemeriksaan"),
                        rs.getString("instruksi_nakes"),
                        rs.getString("verifikasi"),
                        rs.getString("nmdpjp"),
                        rs.getString("status"),
                        rs.getString("tanggal"),
                        rs.getString("nip_dpjp"),
                        rs.getString("waktu_simpan")
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

    private void emptTeks() {
        tglCppt.setDate(new Date());
        TBagian.setText("");
        THasil.setText("");
        TInstruksi.setText("");
        kddpjp.setText("");
        nmdpjp.setText("");
        cmbVerifikasi.setSelectedIndex(0);
        TCari.setText("");
        tglCppt.requestFocus();
    }

    private void getData() {
        if (tbCPPT.getSelectedRow() != -1) {
            TNoRw.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 0).toString());
            TNoRm.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 1).toString());
            TPasien.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 2).toString());
            Valid.SetTgl2(tglCppt, tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 11).toString());
            TBagian.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 5).toString());
            THasil.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 6).toString());
            TInstruksi.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 7).toString());
            kddpjp.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 12).toString());
            nmdpjp.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 9).toString());
            cmbVerifikasi.setSelectedItem(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 8).toString());
        }
    }
    
    public void isCek(){
       BtnSimpan.setEnabled(akses.getcppt());
       BtnHapus.setEnabled(akses.getcppt());
       BtnEdit.setEnabled(akses.getcppt());
    }
    
    private void tampilTemplate() {
        Valid.tabelKosong(tabMode1);
        try {
            if (pilihan == 1) {
                pps1 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, c.* from cppt c "
                        + "inner join reg_periksa rp on rp.no_rawat=c.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "c.hasil_pemeriksaan<>'' and p.no_rkm_medis like ? OR "
                        + "c.hasil_pemeriksaan<>'' and p.nm_pasien like ? OR "
                        + "c.hasil_pemeriksaan<>'' and c.hasil_pemeriksaan like ? ORDER BY c.waktu_simpan desc limit 20");
            } else if (pilihan == 2) {
                pps2 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, c.* from cppt c "
                        + "inner join reg_periksa rp on rp.no_rawat=c.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "c.instruksi_nakes<>'' and p.no_rkm_medis like ? OR "
                        + "c.instruksi_nakes<>'' and p.nm_pasien like ? OR "
                        + "c.instruksi_nakes<>'' and c.instruksi_nakes like ? ORDER BY c.waktu_simpan desc limit 20");
            } 
            try {
                if (pilihan == 1) {
                    pps1.setString(1, "%" + TCari1.getText() + "%");
                    pps1.setString(2, "%" + TCari1.getText() + "%");
                    pps1.setString(3, "%" + TCari1.getText() + "%");
                    rrs1 = pps1.executeQuery();
                    while (rrs1.next()) {
                        tabMode1.addRow(new String[]{
                            rrs1.getString("no_rkm_medis"),
                            rrs1.getString("nm_pasien"),
                            rrs1.getString("hasil_pemeriksaan")
                        });
                    }
                } else if (pilihan == 2) {
                    pps2.setString(1, "%" + TCari1.getText() + "%");
                    pps2.setString(2, "%" + TCari1.getText() + "%");
                    pps2.setString(3, "%" + TCari1.getText() + "%");
                    rrs2 = pps2.executeQuery();
                    while (rrs2.next()) {
                        tabMode1.addRow(new String[]{
                            rrs2.getString("no_rkm_medis"),
                            rrs2.getString("nm_pasien"),
                            rrs2.getString("instruksi_nakes")
                        });
                    }
                } 
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rrs1 != null) {
                    rrs1.close();
                } else if (rrs2 != null) {
                    rrs2.close();
                } 

                if (pps1 != null) {
                    pps1.close();
                } else if (pps2 != null) {
                    pps2.close();
                } 
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void copas() {
        if (pilihan == 1) {
            THasil.setText(Ttemplate.getText());
        } else if (pilihan == 2) {
            TInstruksi.setText(Ttemplate.getText());
        }
    }
    
    public void setData(String norw, String norm, String nmpasien) {
        TNoRw.setText(norw);
        TNoRm.setText(norm);
        TPasien.setText(nmpasien);
    }
}
