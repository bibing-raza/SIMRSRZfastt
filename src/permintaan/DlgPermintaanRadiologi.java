/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgPerawatan.java
 *
 * Created on May 23, 2010, 6:36:30 PM
 */

package permintaan;

import simrskhanza.*;
import simrskhanza.DlgCariDokter;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author dosen
 */
public final class DlgPermintaanRadiologi extends javax.swing.JDialog {
    private DefaultTableModel tabMode, tabMode1;
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Connection koneksi = koneksiDB.condb();
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    private PreparedStatement ps1, ps;
    private ResultSet rs, rs1;
    private int i = 0, x = 0;
    private String cekNORW = "", cekNOMINTA = "", diperiksa = "", waktuSimpan = "";

    /** Creates new form DlgPerawatan
     * @param parent
     * @param modal */
    public DlgPermintaanRadiologi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        Object[] row = {"Kode Periksa", "Nama Pemeriksaan", "Tarif PerBup (Rp.)"};
        tabMode=new DefaultTableModel(null,row){
              @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbPemeriksaan.setModel(tabMode);
        tbPemeriksaan.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPemeriksaan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 3; i++) {
            TableColumn column = tbPemeriksaan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(300);
            } else if (i == 2) {
                column.setPreferredWidth(100);
            }
        }
        tbPemeriksaan.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1 = new DefaultTableModel(null, new Object[]{
            "No.", "Nama Pemeriksaan", "Tgl. Permintaan", "Jam Permintaan", "Status", 
            "norawat", "kddokter", "No. Permintaan", "waktu_simpan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbPermintaan.setModel(tabMode1);
        tbPermintaan.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbPermintaan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 9; i++) {
            TableColumn column = tbPermintaan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(35);
            } else if (i == 1) {
                column.setPreferredWidth(200);
            } else if (i == 2) {
                column.setPreferredWidth(90);
            } else if (i == 3) {
                column.setPreferredWidth(90);
            } else if (i == 4) {
                column.setPreferredWidth(65);
            } else if (i == 5) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 6) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 7) {
                column.setPreferredWidth(115);
            } else if (i == 8) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbPermintaan.setDefaultRenderer(Object.class, new WarnaTable());

        TnmPemeriksaan.setDocument(new batasInput((int) 150).getKata(TnmPemeriksaan));
        kdDokter.setDocument(new batasInput((byte) 20).getKata(kdDokter));
        
        if(koneksiDB.cariCepat().equals("aktif")){
            TCariPeriksa.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {tampilPermintaan();}
                @Override
                public void removeUpdate(DocumentEvent e) {tampilPermintaan();}
                @Override
                public void changedUpdate(DocumentEvent e) {tampilPermintaan();}
            });
        }  
        
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(dokter.getTable().getSelectedRow()!= -1){
                    kdDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                    nmDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                    btnDokter.requestFocus();
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

        internalFrame1 = new widget.InternalFrame();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnGanti = new widget.Button();
        BtnHapus = new widget.Button();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();
        FormInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        PanelInput = new widget.PanelBiasa();
        jLabel3 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel9 = new widget.Label();
        kdDokter = new widget.TextBox();
        nmDokter = new widget.TextBox();
        btnDokter = new widget.Button();
        jLabel4 = new widget.Label();
        TNoPermintaan = new widget.TextBox();
        jLabel5 = new widget.Label();
        TnmUnit = new widget.TextBox();
        jLabel6 = new widget.Label();
        TnmPemeriksaan = new widget.TextBox();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        Scroll3 = new widget.ScrollPane();
        tbPermintaan = new widget.Table();
        jPanel5 = new javax.swing.JPanel();
        Scroll2 = new widget.ScrollPane();
        tbPemeriksaan = new widget.Table();
        panelisi5 = new widget.panelisi();
        label10 = new widget.Label();
        TCariPeriksa = new widget.TextBox();
        btnCariPeriksa = new widget.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Input Data Permintaan Radiologi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

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

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setOpaque(false);
        FormInput.setPreferredSize(new java.awt.Dimension(560, 160));
        FormInput.setLayout(new java.awt.BorderLayout(1, 1));

        ChkInput.setForeground(new java.awt.Color(0, 0, 0));
        ChkInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setMnemonic('I');
        ChkInput.setSelected(true);
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
        FormInput.add(ChkInput, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setPreferredSize(new java.awt.Dimension(560, 108));
        PanelInput.setLayout(null);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("No. Rawat :");
        jLabel3.setName("jLabel3"); // NOI18N
        PanelInput.add(jLabel3);
        jLabel3.setBounds(0, 12, 127, 23);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        PanelInput.add(TNoRw);
        TNoRw.setBounds(130, 12, 128, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        PanelInput.add(TNoRM);
        TNoRM.setBounds(260, 12, 105, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setName("TPasien"); // NOI18N
        PanelInput.add(TPasien);
        TPasien.setBounds(367, 12, 300, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Dokter Perujuk :");
        jLabel9.setName("jLabel9"); // NOI18N
        PanelInput.add(jLabel9);
        jLabel9.setBounds(0, 42, 127, 23);

        kdDokter.setEditable(false);
        kdDokter.setForeground(new java.awt.Color(0, 0, 0));
        kdDokter.setName("kdDokter"); // NOI18N
        PanelInput.add(kdDokter);
        kdDokter.setBounds(130, 42, 128, 23);

        nmDokter.setEditable(false);
        nmDokter.setForeground(new java.awt.Color(0, 0, 0));
        nmDokter.setName("nmDokter"); // NOI18N
        PanelInput.add(nmDokter);
        nmDokter.setBounds(260, 42, 377, 23);

        btnDokter.setForeground(new java.awt.Color(0, 0, 0));
        btnDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/download24.png"))); // NOI18N
        btnDokter.setMnemonic('4');
        btnDokter.setToolTipText("ALt+4");
        btnDokter.setName("btnDokter"); // NOI18N
        btnDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDokterActionPerformed(evt);
            }
        });
        PanelInput.add(btnDokter);
        btnDokter.setBounds(639, 42, 28, 23);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("No. Permintaan :");
        jLabel4.setName("jLabel4"); // NOI18N
        PanelInput.add(jLabel4);
        jLabel4.setBounds(0, 72, 127, 23);

        TNoPermintaan.setEditable(false);
        TNoPermintaan.setForeground(new java.awt.Color(0, 0, 0));
        TNoPermintaan.setName("TNoPermintaan"); // NOI18N
        PanelInput.add(TNoPermintaan);
        TNoPermintaan.setBounds(130, 72, 130, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Poli / Ruangan : ");
        jLabel5.setName("jLabel5"); // NOI18N
        PanelInput.add(jLabel5);
        jLabel5.setBounds(260, 72, 92, 23);

        TnmUnit.setEditable(false);
        TnmUnit.setForeground(new java.awt.Color(0, 0, 0));
        TnmUnit.setName("TnmUnit"); // NOI18N
        PanelInput.add(TnmUnit);
        TnmUnit.setBounds(355, 72, 310, 23);

        jLabel6.setForeground(new java.awt.Color(0, 0, 204));
        jLabel6.setText("Pemeriksaan Diminta : ");
        jLabel6.setName("jLabel6"); // NOI18N
        PanelInput.add(jLabel6);
        jLabel6.setBounds(0, 102, 127, 23);

        TnmPemeriksaan.setForeground(new java.awt.Color(0, 0, 0));
        TnmPemeriksaan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TnmPemeriksaan.setName("TnmPemeriksaan"); // NOI18N
        TnmPemeriksaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmPemeriksaanKeyPressed(evt);
            }
        });
        PanelInput.add(TnmPemeriksaan);
        TnmPemeriksaan.setBounds(130, 102, 535, 23);

        FormInput.add(PanelInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(FormInput, java.awt.BorderLayout.PAGE_START);

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(816, 102));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(300, 102));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        jPanel4.setName("jPanel4"); // NOI18N
        jPanel4.setOpaque(false);
        jPanel4.setPreferredSize(new java.awt.Dimension(300, 102));
        jPanel4.setLayout(new java.awt.GridLayout(1, 2));

        Scroll3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Item Pemeriksaan Radiologi yang diminta ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);

        tbPermintaan.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbPermintaan.setName("tbPermintaan"); // NOI18N
        tbPermintaan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPermintaanMouseClicked(evt);
            }
        });
        tbPermintaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPermintaanKeyPressed(evt);
            }
        });
        Scroll3.setViewportView(tbPermintaan);

        jPanel4.add(Scroll3);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Daftar Nama Pemeriksaan Radiologi Sesuai PerBup ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        jPanel5.setName("jPanel5"); // NOI18N
        jPanel5.setOpaque(false);
        jPanel5.setPreferredSize(new java.awt.Dimension(300, 102));
        jPanel5.setLayout(new java.awt.BorderLayout());

        Scroll2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);

        tbPemeriksaan.setToolTipText("");
        tbPemeriksaan.setName("tbPemeriksaan"); // NOI18N
        tbPemeriksaan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPemeriksaanMouseClicked(evt);
            }
        });
        tbPemeriksaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPemeriksaanKeyPressed(evt);
            }
        });
        Scroll2.setViewportView(tbPemeriksaan);

        jPanel5.add(Scroll2, java.awt.BorderLayout.CENTER);

        panelisi5.setName("panelisi5"); // NOI18N
        panelisi5.setPreferredSize(new java.awt.Dimension(100, 43));
        panelisi5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 9));

        label10.setForeground(new java.awt.Color(0, 0, 0));
        label10.setText("Cari Item Pemeriksaan :");
        label10.setName("label10"); // NOI18N
        label10.setPreferredSize(new java.awt.Dimension(130, 23));
        panelisi5.add(label10);

        TCariPeriksa.setForeground(new java.awt.Color(0, 0, 0));
        TCariPeriksa.setToolTipText("Alt+C");
        TCariPeriksa.setName("TCariPeriksa"); // NOI18N
        TCariPeriksa.setPreferredSize(new java.awt.Dimension(190, 23));
        TCariPeriksa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariPeriksaKeyPressed(evt);
            }
        });
        panelisi5.add(TCariPeriksa);

        btnCariPeriksa.setForeground(new java.awt.Color(0, 0, 0));
        btnCariPeriksa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        btnCariPeriksa.setMnemonic('C');
        btnCariPeriksa.setText("Cek");
        btnCariPeriksa.setToolTipText("Alt+C");
        btnCariPeriksa.setName("btnCariPeriksa"); // NOI18N
        btnCariPeriksa.setPreferredSize(new java.awt.Dimension(70, 23));
        btnCariPeriksa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariPeriksaActionPerformed(evt);
            }
        });
        panelisi5.add(btnCariPeriksa);

        jPanel5.add(panelisi5, java.awt.BorderLayout.PAGE_START);

        jPanel4.add(jPanel5);

        jPanel3.add(jPanel4, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

        internalFrame1.add(jPanel1, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dokter.dispose();
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
            dokter.dispose();
        } 
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
        isForm();
    }//GEN-LAST:event_ChkInputActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampilPermintaan();
        tampilDaftar();
    }//GEN-LAST:event_formWindowOpened

    private void TCariPeriksaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariPeriksaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnCariPeriksaActionPerformed(null);
        }
    }//GEN-LAST:event_TCariPeriksaKeyPressed

    private void btnCariPeriksaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariPeriksaActionPerformed
        tampilDaftar();
    }//GEN-LAST:event_btnCariPeriksaActionPerformed

    private void tbPemeriksaanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPemeriksaanMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getDataDaftar();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbPemeriksaanMouseClicked

    private void tbPemeriksaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPemeriksaanKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataDaftar();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbPemeriksaanKeyPressed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, TCariPeriksa, BtnBatal);
        }
    }//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else if (kdDokter.getText().equals("")) {
            Valid.textKosong(kdDokter, "Dokter Perujuk");
        } else if (TnmPemeriksaan.getText().equals("")) {
            Valid.textKosong(TnmPemeriksaan, "nama pemeriksaan radiologi");
        } else {
            if (Sequel.cariRegistrasi(TNoRw.getText()) > 0) {
                JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi. Silahkan hubungi bagian kasir/keuangan ..!!");
                TCariPeriksa.requestFocus();
            } else {
                if (Sequel.menyimpantf("permintaan_radiologi", "?,?,?,?,?,?,?,?,?,?", "No.Permintaan", 10, new String[]{
                    TNoPermintaan.getText(), TNoRw.getText(), Sequel.cariIsi("select date(now())"),
                    Sequel.cariIsi("select time(now())"), kdDokter.getText(), "Belum", TnmUnit.getText(), TnmPemeriksaan.getText(), "-",
                    Sequel.cariIsi("select now()")
                }) == true) {
                    tampilPermintaan();
                    TnmPemeriksaan.setText("");
                    TCariPeriksa.setText("");
                    tampilDaftar();
                }
            }
        }
    }//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnBatalActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnSimpan, BtnPrint);
        }
    }//GEN-LAST:event_BtnBatalKeyPressed

    private void btnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDokterActionPerformed
        dokter.emptTeks();
        dokter.isCek();
        dokter.setSize(1060, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);
    }//GEN-LAST:event_btnDokterActionPerformed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (TNoRw.getText().equals("") || TNoRM.getText().equals("") || TPasien.getText().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else if (kdDokter.getText().equals("") || nmDokter.getText().equals("")) {
            Valid.textKosong(kdDokter, "Dokter Pengirim");
        } else if (tabMode.getRowCount() == 0) {
            Valid.textKosong(TCariPeriksa, "Data Permintaan");
        } else if (Sequel.cariInteger("select count(-1) from permintaan_radiologi where no_rawat='" + TNoRw.getText() + "'") == 0) {
            JOptionPane.showMessageDialog(null, "Data permintaan pemeriksaan radiologi belum tersimpan...!!!!");
        } else if (Sequel.cariInteger("select count(-1) from permintaan_radiologi where no_rawat='" + TNoRw.getText() + "' and status='Sudah'") > 0) {
            JOptionPane.showMessageDialog(null, "Data permintaan pemeriksaan radiologi sudah diperiksa...!!!!");
        } else {
            Sequel.AutoComitFalse();
            Sequel.queryu("delete from temporary_permintaan_radiologi");
            try {
                ps1 = koneksi.prepareStatement("SELECT pp.kd_jenis_prw, jp.nm_perawatan FROM permintaan_pemeriksaan_radiologi pp "
                        + "INNER JOIN jns_perawatan_radiologi jp ON pp.kd_jenis_prw = jp.kd_jenis_prw "
                        + "inner join permintaan_radiologi pr on pr.noorder=pp.noorder where pr.status='Belum' and pr.no_rawat = '" + TNoRw.getText() + "'");
                try {
                    rs1 = ps1.executeQuery();
                    x = 1;
                    while (rs1.next()) {
                        Sequel.menyimpan("temporary_permintaan_radiologi", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", 38, new String[]{
                            "0", rs1.getString("kd_jenis_prw"), x + ".", rs1.getString("nm_perawatan"),
                            "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""
                        });
                        x++;
                    }
                } catch (Exception e) {
                    System.out.println("Notif 2 : " + e);
                } finally {
                    if (rs1 != null) {
                        rs1.close();
                    }
                    if (ps1 != null) {
                        ps1.close();
                    }
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            }
            Sequel.AutoComitTrue();
//            Map<String, Object> param = new HashMap<>();
//            param.put("noperiksa", Sequel.cariIsi("select noorder from permintaan_radiologi where no_rawat='" + TNoRw.getText() + "' and status='Belum'"));
//            param.put("norm", TNoRM.getText());
//            param.put("pekerjaan", Sequel.cariIsi("select pekerjaan from pasien where no_rkm_medis=?", TNoRM.getText()));
//            param.put("noktp", Sequel.cariIsi("select no_ktp from pasien where no_rkm_medis=?", TNoRM.getText()));
//            param.put("namapasien", TPasien.getText());
//            param.put("jkel", Jk.getText());
//            param.put("umur", Umur.getText());
//            param.put("lahir", Sequel.cariIsi("select DATE_FORMAT(tgl_lahir,'%d-%m-%Y') from pasien where no_rkm_medis=? ", TNoRM.getText()));
//            param.put("pengirim", NmPerujuk.getText());
//            param.put("tanggal", Valid.SetTglINDONESIA(Sequel.cariIsi("select tgl_permintaan from permintaan_radiologi where "
//                    + "no_rawat='" + TNoRw.getText() + "' and status='Belum' limit 1")));
//            param.put("alamat", Alamat.getText());
//            param.put("kamar", kamar);
//            param.put("namakamar", namakamar);
//            param.put("jam", Sequel.cariIsi("select date_format(jam_permintaan,'%H:%i') from permintaan_radiologi where "
//                    + "no_rawat='" + TNoRw.getText() + "' and status='Belum' limit 1"));
//            param.put("namars", akses.getnamars());
//            param.put("alamatrs", akses.getalamatrs());
//            param.put("kotars", akses.getkabupatenrs());
//            param.put("propinsirs", akses.getpropinsirs());
//            param.put("kontakrs", akses.getkontakrs());
//            param.put("emailrs", akses.getemailrs());
//            param.put("logo", Sequel.cariGambar("select logo from setting"));
//
//            Valid.MyReport("rptPermintaanRadiologi.jasper", "report", "::[ Permintaan Radiologi ]::",
//                    "select no, temp1, temp2, temp3, temp4, temp5, temp6, temp7, temp8, temp9, temp10, temp11, temp12, "
//                    + "temp13, temp14, temp14, temp15, temp16 from temporary_permintaan_radiologi order by no asc", param);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void tbPermintaanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPermintaanMouseClicked
        if (tabMode1.getRowCount() != 0) {
            try {
                getDataMinta();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbPermintaanMouseClicked

    private void tbPermintaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPermintaanKeyPressed
        if (tabMode1.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataMinta();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbPermintaanKeyPressed

    private void BtnGantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else if (TnmPemeriksaan.getText().trim().equals("")) {
            Valid.textKosong(TnmPemeriksaan, "nama pemeriksaan radiologi yang diminta");
        } else if (waktuSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Item permintaan pemeriksaan radiologi pada tabel belum dipilih..!!!");
            tbPermintaan.requestFocus();
        } else if (diperiksa.equals("Diterima")) {
            JOptionPane.showMessageDialog(null, "Item permintaan pemeriksaan radiologi yg. sdh. diterima tidak dapat diganti..!!!");
            tbPermintaan.requestFocus();
        } else {
            Sequel.mengedit("permintaan_radiologi", "waktu_simpan='" + waktuSimpan + "'",
                    "dokter_perujuk='" + kdDokter.getText() + "',nm_pemeriksaan='" + TnmPemeriksaan.getText() + "'");

            tampilPermintaan();
            TnmPemeriksaan.setText("");
            TCariPeriksa.setText("");
            tampilDaftar();
        }
    }//GEN-LAST:event_BtnGantiActionPerformed

    private void BtnGantiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnGantiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnGantiActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnSimpan, BtnKeluar);
        }
    }//GEN-LAST:event_BtnGantiKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else if (waktuSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Item permintaan pemeriksaan radiologi pada tabel belum dipilih..!!!");
            tbPermintaan.requestFocus();
        } else if (diperiksa.equals("Diterima")) {
            JOptionPane.showMessageDialog(null, "Item permintaan pemeriksaan radiologi yg. sdh. diterima tidak dapat dihapus..!!!");
            tbPermintaan.requestFocus();
        } else {
            Sequel.queryu("delete from permintaan_radiologi where waktu_simpan='" + waktuSimpan + "'");
            
            tampilPermintaan();
            TnmPemeriksaan.setText("");
            TCariPeriksa.setText("");
            tampilDaftar();
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void TnmPemeriksaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmPemeriksaanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnSimpanActionPerformed(null);
        }
    }//GEN-LAST:event_TnmPemeriksaanKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgPermintaanRadiologi dialog = new DlgPermintaanRadiologi(new javax.swing.JFrame(), true);
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
    private widget.Button BtnBatal;
    private widget.Button BtnGanti;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.CekBox ChkInput;
    private javax.swing.JPanel FormInput;
    private widget.PanelBiasa PanelInput;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll3;
    private widget.TextBox TCariPeriksa;
    private widget.TextBox TNoPermintaan;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox TnmPemeriksaan;
    private widget.TextBox TnmUnit;
    private widget.Button btnCariPeriksa;
    private widget.Button btnDokter;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel3;
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private widget.TextBox kdDokter;
    private widget.Label label10;
    private widget.TextBox nmDokter;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelisi5;
    private widget.Table tbPemeriksaan;
    private widget.Table tbPermintaan;
    // End of variables declaration//GEN-END:variables
    
    
    private void tampilDaftar() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT jp.kd_jenis_prw, jp.nm_perawatan, format( jp.total_byr,0) total_byr "
                    + "FROM jns_perawatan_radiologi jp INNER JOIN penjab pj ON pj.kd_pj = jp.kd_pj WHERE "
                    + "jp.STATUS='1' AND jp.nm_perawatan LIKE ? ORDER BY jp.kd_jenis_prw");
            try {
                ps.setString(1, "%" + TCariPeriksa.getText().trim() + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString("kd_jenis_prw"),
                        rs.getString("nm_perawatan"),
                        rs.getString("total_byr")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi 1 : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi 2 : " + e);
        }
    }
    
    public void emptTeks() {
        TCariPeriksa.setText("");
        TnmPemeriksaan.setText("");
    }
    
    private void AutoNomerMinta() {
        Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(noorder,4),signed)),0) from permintaan_radiologi where "
                + "tgl_permintaan='" + Sequel.cariIsi("select date(now())") + "' ", "PR" + Sequel.cariIsi("select DATE_FORMAT(now(),'%Y%m%d')"), 4, TNoPermintaan);
    }
    
    public void setNoRm(String norwt, String unit) {
        TNoRw.setText(norwt);
        TNoRM.setText(Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat='" + norwt + "'"));
        TPasien.setText(Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
        TnmUnit.setText(unit);
        AutoNomerMinta();

        if (Sequel.cariIsi("select status_lanjut from reg_periksa where no_rawat='" + norwt + "'").equals("Ralan")) {
            kdDokter.setText(Sequel.cariIsi("select kd_dokter from reg_periksa where no_rawat='" + norwt + "'"));
            nmDokter.setText(Sequel.cariIsi("select nm_dokter from dokter where kd_dokter='" + kdDokter.getText() + "'"));
        } else {
            if (Sequel.cariInteger("select count(-1) from dpjp_ranap where no_rawat='" + norwt + "'") == 0) {
                kdDokter.setText("-");
                nmDokter.setText("-");
            } else {
                kdDokter.setText(Sequel.cariIsi("select kd_dokter from dpjp_ranap where no_rawat='" + norwt + "'"));
                nmDokter.setText(Sequel.cariIsi("select nm_dokter from dokter where kd_dokter='" + kdDokter.getText() + "'"));
            }
        }        
    }
    
    public void isCek(){        
        BtnSimpan.setEnabled(akses.getpermintaan_radiologi());
        BtnPrint.setEnabled(akses.getpermintaan_radiologi());
        BtnGanti.setEnabled(akses.getpermintaan_radiologi());
        BtnHapus.setEnabled(akses.getpermintaan_radiologi());
    }
    
    private void isForm() {
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            FormInput.setPreferredSize(new Dimension(WIDTH, 160));
            PanelInput.setVisible(true);
            ChkInput.setVisible(true);
        } else if (ChkInput.isSelected() == false) {
            ChkInput.setVisible(false);
            FormInput.setPreferredSize(new Dimension(WIDTH, 20));
            PanelInput.setVisible(false);
            ChkInput.setVisible(true);
        }
    }
    
    public void setDokterPerujuk(String kodeperujuk,String namaperujuk){
        kdDokter.setText(kodeperujuk);
        nmDokter.setText(namaperujuk);
    }
    
    private void getDataDaftar() {
        if (tbPemeriksaan.getSelectedRow() != -1) {
            TnmPemeriksaan.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 1).toString());
            TnmPemeriksaan.requestFocus();
        }
    }

    private void tampilPermintaan() {
        Valid.tabelKosong(tabMode1);
        try {
            ps1 = koneksi.prepareStatement("SELECT pr.nm_pemeriksaan, DATE_FORMAT(pr.tgl_permintaan,'%d-%m-%Y') tglminta, "
                    + "pr.jam_permintaan jam, pr.status, pr.no_rawat, d.nm_dokter, pr.noorder, pr.waktu_simpan "
                    + "from permintaan_radiologi pr inner join dokter d on d.kd_dokter=pr.dokter_perujuk where "
                    + "pr.no_rawat='" + TNoRw.getText() + "' order by pr.status, pr.tgl_permintaan desc, pr.jam_permintaan desc");
            try {
                rs1 = ps1.executeQuery();
                x = 1;
                while (rs1.next()) {
                    tabMode1.addRow(new Object[]{
                        x + ".",
                        rs1.getString("nm_pemeriksaan"),
                        rs1.getString("tglminta"),
                        rs1.getString("jam"),
                        rs1.getString("status"),
                        rs1.getString("no_rawat"),
                        rs1.getString("nm_dokter"),
                        rs1.getString("noorder"),
                        rs1.getString("waktu_simpan")
                    });
                    x++;
                }                
            } catch (Exception e) {
                System.out.println("simrskhanza.DlgPermintaanRadiologi.tampilPermintaan() : " + e);
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
    
    private void getDataMinta() {
        cekNORW = "";
        cekNOMINTA = "";
        diperiksa = "";
        waktuSimpan = "";

        if (tbPermintaan.getSelectedRow() != -1) {
            TNoRw.setText(tbPermintaan.getValueAt(tbPermintaan.getSelectedRow(), 5).toString());
            diperiksa = tbPermintaan.getValueAt(tbPermintaan.getSelectedRow(), 4).toString();
            cekNORW = tbPermintaan.getValueAt(tbPermintaan.getSelectedRow(), 5).toString();
            cekNOMINTA = tbPermintaan.getValueAt(tbPermintaan.getSelectedRow(), 7).toString();            
            TnmPemeriksaan.setText(tbPermintaan.getValueAt(tbPermintaan.getSelectedRow(), 1).toString());
            waktuSimpan = tbPermintaan.getValueAt(tbPermintaan.getSelectedRow(), 8).toString();    
            TnmPemeriksaan.requestFocus();
        }
    }
}
