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
import laporan.DlgHasilPenunjangMedis;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author dosen
 */
public class RMMonitoringPEWSAnak extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1, tabModeCppt;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps1, pscppt;
    private ResultSet rs, rs1, rscppt;
    private int i = 0, x = 0, skorKeadaan = 0, skorKardio = 0, skorRespirasi = 0, total = 0;
    private String nip = "", dataKonfirmasi = "", enumKeadaan = "", enumKardiovaskular = "", enumRespirasi = "",
            dataKeadaan = "", dataKardio = "", dataRespi = "";
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);

    /** Creates new form DlgSpesialis
     * @param parent
     * @param modal */
    public RMMonitoringPEWSAnak(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        Object[] row = {"No. Rawat", "No. RM", "Nama Pasien", "Tgl. Lahir", "Umur", "Jns. Kelamin", "Ruang Rawat", "Tanggal", "Jam",
            "Keadaan Umum", "Skor", "Kardiovaskular", "Skor", "Respirasi", "Skor", "Tot. Skor", "Nama Perawat", "tanggal", "jam",
            "nip", "waktu_simpan"
        };
        
        tabMode=new DefaultTableModel(null,row){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };

        tbPEWS.setModel(tabMode);
        tbPEWS.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPEWS.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 21; i++) {
            TableColumn column = tbPEWS.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(55);
            } else if (i == 2) {
                column.setPreferredWidth(220);
            } else if (i == 3) {
                column.setPreferredWidth(100);
            } else if (i == 4) {
                column.setPreferredWidth(50);
            } else if (i == 5) {
                column.setPreferredWidth(70);
            } else if (i == 6) {
                column.setPreferredWidth(250);
            } else if (i == 7) {
                column.setPreferredWidth(75);
            } else if (i == 8) {
                column.setPreferredWidth(60);
            } else if (i == 9) {
                column.setPreferredWidth(220);
            } else if (i == 10) {
                column.setPreferredWidth(40);
            } else if (i == 11) {
                column.setPreferredWidth(220);
            } else if (i == 12) {
                column.setPreferredWidth(40);
            } else if (i == 13) {
                column.setPreferredWidth(220);
            } else if (i == 14) {
                column.setPreferredWidth(40);
            } else if (i == 15) {
                column.setPreferredWidth(70);
            } else if (i == 16) {
                column.setPreferredWidth(250);
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
            } 
        }
        tbPEWS.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1=new DefaultTableModel(null, new Object[]{
            "Parameter", "Usia", "Nadi Saat Istirahat (x/menit)", "Napas Saat Istirahat (napas/menit)"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbData.setModel(tabMode1);
        tbData.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbData.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 4; i++) {
            TableColumn column = tbData.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(80);
            } else if (i == 1) {
                column.setPreferredWidth(70);
            } else if (i == 2) {
                column.setPreferredWidth(160);
            } else if (i == 3) {
                column.setPreferredWidth(200);
            }
        }
        tbData.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeCppt=new DefaultTableModel(null, new Object[]{
            "Tgl. CPPT", "Jam CPPT", "Jenis Bagian", "DPJP Konsulen", "Jenis PPA",
            "Nama PPA", "Shift", "hasil", "instruksi", "no_rawat", "tgl_cppt", "jam_cppt"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbCPPT.setModel(tabModeCppt);
        tbCPPT.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbCPPT.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 12; i++) {
            TableColumn column = tbCPPT.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(70);
            } else if (i == 1) {
                column.setPreferredWidth(60);
            } else if (i == 2) {
                column.setPreferredWidth(80);
            } else if (i == 3) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 4) {
                column.setPreferredWidth(80);
            } else if (i == 5) {
                column.setPreferredWidth(200);
            } else if (i == 6) {
                column.setPreferredWidth(40);
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
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 11) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } 
        }
        tbCPPT.setDefaultRenderer(Object.class, new WarnaTable());

        TCari.setDocument(new batasInput((int) 100).getKata(TCari));
        
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
                    nip = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();                    
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

        WindowCetak = new javax.swing.JDialog();
        internalFrame10 = new widget.InternalFrame();
        BtnCloseIn6 = new widget.Button();
        BtnCetak = new widget.Button();
        tglA = new widget.Tanggal();
        jLabel49 = new widget.Label();
        tglB = new widget.Tanggal();
        jLabel50 = new widget.Label();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbPEWS = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass9 = new widget.panelisi();
        jLabel30 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel32 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        panelGlass7 = new widget.panelisi();
        jLabel5 = new widget.Label();
        jLabel15 = new widget.Label();
        TruangRwt = new widget.TextBox();
        TNoRw = new widget.TextBox();
        TNoRm = new widget.TextBox();
        TPasien = new widget.TextBox();
        cmbDtk = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbJam = new widget.ComboBox();
        jLabel9 = new widget.Label();
        Ttgl = new widget.Tanggal();
        jLabel8 = new widget.Label();
        jLabel10 = new widget.Label();
        cmbKeadaan = new widget.ComboBox();
        jLabel11 = new widget.Label();
        cmbKardio = new widget.ComboBox();
        jLabel17 = new widget.Label();
        jLabel18 = new widget.Label();
        TskorKeadaan = new widget.TextBox();
        TskorKardio = new widget.TextBox();
        jLabel21 = new widget.Label();
        TskorRespi = new widget.TextBox();
        jLabel31 = new widget.Label();
        cmbRespirasi = new widget.ComboBox();
        jLabel19 = new widget.Label();
        TtotSkor = new widget.TextBox();
        jLabel20 = new widget.Label();
        TnmPerawat = new widget.TextBox();
        BtnPerawat = new widget.Button();
        chkSaya = new widget.CekBox();
        TabSkor = new javax.swing.JTabbedPane();
        panelBiasa8 = new widget.PanelBiasa();
        skor1 = new widget.TextArea();
        panelBiasa9 = new widget.PanelBiasa();
        skor2 = new widget.TextArea();
        panelBiasa10 = new widget.PanelBiasa();
        skor3 = new widget.TextArea();
        panelBiasa11 = new widget.PanelBiasa();
        skor4 = new widget.TextArea();
        jLabel22 = new widget.Label();
        TtglLahir = new widget.TextBox();
        jLabel23 = new widget.Label();
        Tjenkel = new widget.TextBox();
        jLabel24 = new widget.Label();
        Tumur = new widget.TextBox();
        Scroll1 = new widget.ScrollPane();
        tbData = new widget.Table();
        PanelAccor = new widget.PanelBiasa();
        ChkAccor = new widget.CekBox();
        FormMenu = new widget.PanelBiasa();
        Scroll3 = new widget.ScrollPane();
        tbCPPT = new widget.Table();
        panelGlass14 = new widget.panelisi();
        scrollPane5 = new widget.ScrollPane();
        Thasil = new widget.TextArea();
        scrollPane4 = new widget.ScrollPane();
        Tinstruksi = new widget.TextArea();

        WindowCetak.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowCetak.setName("WindowCetak"); // NOI18N
        WindowCetak.setUndecorated(true);
        WindowCetak.setResizable(false);

        internalFrame10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Cetak Laporan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame10.setName("internalFrame10"); // NOI18N
        internalFrame10.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame10.setLayout(null);

        BtnCloseIn6.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn6.setMnemonic('U');
        BtnCloseIn6.setText("Tutup");
        BtnCloseIn6.setToolTipText("Alt+U");
        BtnCloseIn6.setName("BtnCloseIn6"); // NOI18N
        BtnCloseIn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn6ActionPerformed(evt);
            }
        });
        internalFrame10.add(BtnCloseIn6);
        BtnCloseIn6.setBounds(425, 23, 80, 26);

        BtnCetak.setForeground(new java.awt.Color(0, 0, 0));
        BtnCetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/PrinterSettings.png"))); // NOI18N
        BtnCetak.setMnemonic('U');
        BtnCetak.setText("Cetak");
        BtnCetak.setToolTipText("Alt+U");
        BtnCetak.setName("BtnCetak"); // NOI18N
        BtnCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCetakActionPerformed(evt);
            }
        });
        internalFrame10.add(BtnCetak);
        BtnCetak.setBounds(320, 23, 90, 26);

        tglA.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "28-05-2024" }));
        tglA.setDisplayFormat("dd-MM-yyyy");
        tglA.setName("tglA"); // NOI18N
        tglA.setOpaque(false);
        tglA.setPreferredSize(new java.awt.Dimension(90, 23));
        internalFrame10.add(tglA);
        tglA.setBounds(93, 25, 90, 23);

        jLabel49.setForeground(new java.awt.Color(0, 0, 0));
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel49.setText("s.d");
        jLabel49.setName("jLabel49"); // NOI18N
        internalFrame10.add(jLabel49);
        jLabel49.setBounds(185, 25, 30, 23);

        tglB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "28-05-2024" }));
        tglB.setDisplayFormat("dd-MM-yyyy");
        tglB.setName("tglB"); // NOI18N
        tglB.setOpaque(false);
        tglB.setPreferredSize(new java.awt.Dimension(90, 23));
        internalFrame10.add(tglB);
        tglB.setBounds(218, 25, 90, 23);

        jLabel50.setForeground(new java.awt.Color(0, 0, 0));
        jLabel50.setText("Periode Tgl. :");
        jLabel50.setName("jLabel50"); // NOI18N
        internalFrame10.add(jLabel50);
        jLabel50.setBounds(0, 25, 90, 23);

        WindowCetak.getContentPane().add(internalFrame10, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Monitoring Pediatric Early Warning Score (PEWS) ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbPEWS.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbPEWS.setName("tbPEWS"); // NOI18N
        tbPEWS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPEWSMouseClicked(evt);
            }
        });
        tbPEWS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPEWSKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbPEWS);

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

        jPanel3.add(panelGlass8, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 3, 9));

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Tanggal :");
        jLabel30.setName("jLabel30"); // NOI18N
        jLabel30.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass9.add(jLabel30);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "28-05-2024" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari1);

        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("s.d.");
        jLabel32.setName("jLabel32"); // NOI18N
        jLabel32.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass9.add(jLabel32);

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "28-05-2024" }));
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
            public void keyReleased(java.awt.event.KeyEvent evt) {
                BtnCariKeyReleased(evt);
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

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Pasien :");
        jLabel5.setName("jLabel5"); // NOI18N
        panelGlass7.add(jLabel5);
        jLabel5.setBounds(0, 10, 120, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Ruang Rawat :");
        jLabel15.setName("jLabel15"); // NOI18N
        panelGlass7.add(jLabel15);
        jLabel15.setBounds(0, 66, 120, 23);

        TruangRwt.setEditable(false);
        TruangRwt.setForeground(new java.awt.Color(0, 0, 0));
        TruangRwt.setName("TruangRwt"); // NOI18N
        panelGlass7.add(TruangRwt);
        TruangRwt.setBounds(125, 66, 550, 23);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        panelGlass7.add(TNoRw);
        TNoRw.setBounds(125, 10, 122, 23);

        TNoRm.setEditable(false);
        TNoRm.setForeground(new java.awt.Color(0, 0, 0));
        TNoRm.setName("TNoRm"); // NOI18N
        panelGlass7.add(TNoRm);
        TNoRm.setBounds(252, 10, 70, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setName("TPasien"); // NOI18N
        panelGlass7.add(TPasien);
        TPasien.setBounds(326, 10, 350, 23);

        cmbDtk.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtkMouseReleased(evt);
            }
        });
        panelGlass7.add(cmbDtk);
        cmbDtk.setBounds(371, 94, 45, 23);

        cmbMnt.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMntMouseReleased(evt);
            }
        });
        panelGlass7.add(cmbMnt);
        cmbMnt.setBounds(319, 94, 45, 23);

        cmbJam.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJamMouseReleased(evt);
            }
        });
        panelGlass7.add(cmbJam);
        cmbJam.setBounds(268, 94, 45, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Jam :");
        jLabel9.setName("jLabel9"); // NOI18N
        panelGlass7.add(jLabel9);
        jLabel9.setBounds(220, 94, 40, 23);

        Ttgl.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "28-05-2024" }));
        Ttgl.setDisplayFormat("dd-MM-yyyy");
        Ttgl.setName("Ttgl"); // NOI18N
        Ttgl.setOpaque(false);
        Ttgl.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass7.add(Ttgl);
        Ttgl.setBounds(125, 94, 90, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Tanggal :");
        jLabel8.setName("jLabel8"); // NOI18N
        panelGlass7.add(jLabel8);
        jLabel8.setBounds(0, 94, 120, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Keadaan Umum :");
        jLabel10.setName("jLabel10"); // NOI18N
        panelGlass7.add(jLabel10);
        jLabel10.setBounds(0, 122, 120, 23);

        cmbKeadaan.setForeground(new java.awt.Color(0, 0, 0));
        cmbKeadaan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Interaksi Biasa", "Somnolen", "Iritabel", "Latergi, Gelisah, Penurunan Respon Terhadap Nyeri" }));
        cmbKeadaan.setName("cmbKeadaan"); // NOI18N
        cmbKeadaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKeadaanActionPerformed(evt);
            }
        });
        panelGlass7.add(cmbKeadaan);
        cmbKeadaan.setBounds(125, 122, 290, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Kardiovaskular :");
        jLabel11.setName("jLabel11"); // NOI18N
        panelGlass7.add(jLabel11);
        jLabel11.setBounds(0, 150, 120, 23);

        cmbKardio.setForeground(new java.awt.Color(0, 0, 0));
        cmbKardio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak Sianosis ATAU Pengisian Kapiler < 2 Detik", "Tampak Pucat ATAU Pengisian Kapiler 2 Detik", "Tidak Sianosis ATAU Pengisian Kapiler > 2 Detik ATAU Takikardi > 20x diatas parameter sesuai usia/menit", "Sianotik dan Motlet ATAU Pengisian Kapiler > 5 Detik ATAU Takikardi > 30x diatas parameter sesuai usia/menit ATAU Bradikardi (Sesuai Usia)" }));
        cmbKardio.setName("cmbKardio"); // NOI18N
        cmbKardio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKardioActionPerformed(evt);
            }
        });
        panelGlass7.add(cmbKardio);
        cmbKardio.setBounds(125, 150, 730, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Skor :");
        jLabel17.setName("jLabel17"); // NOI18N
        panelGlass7.add(jLabel17);
        jLabel17.setBounds(1020, 122, 45, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Skor :");
        jLabel18.setName("jLabel18"); // NOI18N
        panelGlass7.add(jLabel18);
        jLabel18.setBounds(1020, 150, 45, 23);

        TskorKeadaan.setEditable(false);
        TskorKeadaan.setForeground(new java.awt.Color(0, 0, 0));
        TskorKeadaan.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TskorKeadaan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TskorKeadaan.setName("TskorKeadaan"); // NOI18N
        panelGlass7.add(TskorKeadaan);
        TskorKeadaan.setBounds(1070, 122, 35, 23);

        TskorKardio.setEditable(false);
        TskorKardio.setForeground(new java.awt.Color(0, 0, 0));
        TskorKardio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TskorKardio.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TskorKardio.setName("TskorKardio"); // NOI18N
        panelGlass7.add(TskorKardio);
        TskorKardio.setBounds(1070, 150, 35, 23);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Skor :");
        jLabel21.setName("jLabel21"); // NOI18N
        panelGlass7.add(jLabel21);
        jLabel21.setBounds(1020, 178, 45, 23);

        TskorRespi.setEditable(false);
        TskorRespi.setForeground(new java.awt.Color(0, 0, 0));
        TskorRespi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TskorRespi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TskorRespi.setName("TskorRespi"); // NOI18N
        panelGlass7.add(TskorRespi);
        TskorRespi.setBounds(1070, 178, 35, 23);

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("Respirasi :");
        jLabel31.setName("jLabel31"); // NOI18N
        panelGlass7.add(jLabel31);
        jLabel31.setBounds(0, 178, 120, 23);

        cmbRespirasi.setForeground(new java.awt.Color(0, 0, 0));
        cmbRespirasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak Sianotik ATAU Bernafas Normal Sesuai Parameter", "Takipnea > 10x diatas Parameter RR Sesuai Usia/Menit ATAU Menggunakan Otot Bantu Pernapasan ATAU Menggunakan FiO2 Lebih Dari 30% (Setara Dg. 3 L/Mnt Nasal Kanul)", "Takipnea > 20x diatas Parameter RR Sesuai Usia/Menit ATAU Ada Retraksi ATAU Menggunakan FiO2 Lebih Dari 40% (Setara Dg. 6 L/Mnt Simple Mask)", "Takipnea >= 25x diatas Parameter RR Sesuai Usia/Menit Dengan Retraksi Dada ATAU Merintih ATAU Menggunakan FiO2 Lebih Dari 50% (Setara Dg. 8 L/Mnt Simple Mask)" }));
        cmbRespirasi.setName("cmbRespirasi"); // NOI18N
        cmbRespirasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRespirasiActionPerformed(evt);
            }
        });
        panelGlass7.add(cmbRespirasi);
        cmbRespirasi.setBounds(125, 178, 890, 23);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Total Skor :");
        jLabel19.setName("jLabel19"); // NOI18N
        panelGlass7.add(jLabel19);
        jLabel19.setBounds(985, 206, 80, 23);

        TtotSkor.setEditable(false);
        TtotSkor.setForeground(new java.awt.Color(0, 0, 0));
        TtotSkor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TtotSkor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TtotSkor.setName("TtotSkor"); // NOI18N
        panelGlass7.add(TtotSkor);
        TtotSkor.setBounds(1070, 206, 35, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Nama Perawat :");
        jLabel20.setName("jLabel20"); // NOI18N
        panelGlass7.add(jLabel20);
        jLabel20.setBounds(0, 206, 120, 23);

        TnmPerawat.setEditable(false);
        TnmPerawat.setForeground(new java.awt.Color(0, 0, 0));
        TnmPerawat.setName("TnmPerawat"); // NOI18N
        panelGlass7.add(TnmPerawat);
        TnmPerawat.setBounds(125, 206, 390, 23);

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
        panelGlass7.add(BtnPerawat);
        BtnPerawat.setBounds(520, 206, 28, 23);

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
        chkSaya.setBounds(560, 206, 100, 23);

        TabSkor.setBackground(new java.awt.Color(255, 255, 254));
        TabSkor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabSkor.setName("TabSkor"); // NOI18N

        panelBiasa8.setName("panelBiasa8"); // NOI18N
        panelBiasa8.setLayout(new java.awt.BorderLayout());

        skor1.setEditable(false);
        skor1.setBackground(new java.awt.Color(204, 255, 204));
        skor1.setColumns(20);
        skor1.setRows(5);
        skor1.setText("Pasien dalam keadaan stabil, monitoring per 4 jam.");
        skor1.setName("skor1"); // NOI18N
        skor1.setOpaque(true);
        panelBiasa8.add(skor1, java.awt.BorderLayout.CENTER);

        TabSkor.addTab("Skor 0 - 2", panelBiasa8);

        panelBiasa9.setName("panelBiasa9"); // NOI18N
        panelBiasa9.setLayout(new java.awt.BorderLayout());

        skor2.setEditable(false);
        skor2.setBackground(new java.awt.Color(255, 255, 102));
        skor2.setColumns(20);
        skor2.setRows(5);
        skor2.setText("Ada perubahan kondisi pasien, monitoring setiap 2 jam atau lebih cepat, asesmen oleh Perawat Senior (respon maksimal 5 menit) lapor ke Dokter Jaga (respon maksimal 5 menit), konsultasi ke DPJP, edukasi keluarga pasien.");
        skor2.setName("skor2"); // NOI18N
        skor2.setOpaque(true);
        panelBiasa9.add(skor2, java.awt.BorderLayout.CENTER);

        TabSkor.addTab("Skor 3 - 4", panelBiasa9);

        panelBiasa10.setName("panelBiasa10"); // NOI18N
        panelBiasa10.setLayout(new java.awt.BorderLayout());

        skor3.setEditable(false);
        skor3.setBackground(new java.awt.Color(255, 153, 153));
        skor3.setColumns(20);
        skor3.setRows(5);
        skor3.setText("Ada perubahan yang signifikan, monitoring secara kontinyu setiap 1 jam atau lebih cepat, konsultasi ke DPJP.\nPanggil Tim Code Blue, respon maksimal 10 menit, informasikan dan konsultasikan ke DPJP, edukasi keluarga pasien.");
        skor3.setName("skor3"); // NOI18N
        skor3.setOpaque(true);
        panelBiasa10.add(skor3, java.awt.BorderLayout.CENTER);

        TabSkor.addTab("Skor 5 Atau Lebih", panelBiasa10);

        panelBiasa11.setName("panelBiasa11"); // NOI18N
        panelBiasa11.setLayout(new java.awt.BorderLayout());

        skor4.setEditable(false);
        skor4.setBackground(new java.awt.Color(51, 153, 255));
        skor4.setColumns(20);
        skor4.setRows(5);
        skor4.setText("Lakukan RJP, Aktivasi Code Blue, Respon Code Blue segera maksimal 5 menit. Resusitasi lanjutan oleh Tim Code Blue, respon segera maksimal 10 menit, edukasi keluarga pasien.");
        skor4.setName("skor4"); // NOI18N
        skor4.setOpaque(true);
        panelBiasa11.add(skor4, java.awt.BorderLayout.CENTER);

        TabSkor.addTab("Kriteria Code Blue, Henti Nafas Atau Jantung", panelBiasa11);

        panelGlass7.add(TabSkor);
        TabSkor.setBounds(125, 234, 557, 95);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Tgl. Lahir :");
        jLabel22.setName("jLabel22"); // NOI18N
        panelGlass7.add(jLabel22);
        jLabel22.setBounds(0, 38, 120, 23);

        TtglLahir.setEditable(false);
        TtglLahir.setForeground(new java.awt.Color(0, 0, 0));
        TtglLahir.setName("TtglLahir"); // NOI18N
        panelGlass7.add(TtglLahir);
        TtglLahir.setBounds(125, 38, 150, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Jns. Kelamin :");
        jLabel23.setName("jLabel23"); // NOI18N
        panelGlass7.add(jLabel23);
        jLabel23.setBounds(275, 38, 85, 23);

        Tjenkel.setEditable(false);
        Tjenkel.setForeground(new java.awt.Color(0, 0, 0));
        Tjenkel.setName("Tjenkel"); // NOI18N
        panelGlass7.add(Tjenkel);
        Tjenkel.setBounds(365, 38, 90, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Umur :");
        jLabel24.setName("jLabel24"); // NOI18N
        panelGlass7.add(jLabel24);
        jLabel24.setBounds(455, 38, 50, 23);

        Tumur.setEditable(false);
        Tumur.setForeground(new java.awt.Color(0, 0, 0));
        Tumur.setName("Tumur"); // NOI18N
        panelGlass7.add(Tumur);
        Tumur.setBounds(510, 38, 60, 23);

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbData.setToolTipText("");
        tbData.setName("tbData"); // NOI18N
        tbData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDataMouseClicked(evt);
            }
        });
        tbData.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDataKeyPressed(evt);
            }
        });
        Scroll1.setViewportView(tbData);

        panelGlass7.add(Scroll1);
        Scroll1.setBounds(690, 234, 530, 95);

        internalFrame1.add(panelGlass7, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        PanelAccor.setBackground(new java.awt.Color(255, 255, 255));
        PanelAccor.setName("PanelAccor"); // NOI18N
        PanelAccor.setPreferredSize(new java.awt.Dimension(900, 43));
        PanelAccor.setLayout(new java.awt.BorderLayout());

        ChkAccor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/2rightarrow.png"))); // NOI18N
        ChkAccor.setToolTipText("Silahkan Klik Untuk Membaca CPPT");
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

        Scroll3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " CPPT ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);

        tbCPPT.setToolTipText("Silahkan klik untuk memilih data yang dibaca cpptnya");
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
        });
        Scroll3.setViewportView(tbCPPT);

        FormMenu.add(Scroll3);

        panelGlass14.setName("panelGlass14"); // NOI18N
        panelGlass14.setPreferredSize(new java.awt.Dimension(44, 300));
        panelGlass14.setLayout(new java.awt.BorderLayout());

        scrollPane5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Hasil Pemeriksaan, Analisa, Rencana, Penatalaksanaan Pasien ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        scrollPane5.setName("scrollPane5"); // NOI18N
        scrollPane5.setPreferredSize(new java.awt.Dimension(212, 350));

        Thasil.setEditable(false);
        Thasil.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Thasil.setColumns(20);
        Thasil.setRows(5);
        Thasil.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Thasil.setName("Thasil"); // NOI18N
        Thasil.setPreferredSize(new java.awt.Dimension(202, 4000));
        scrollPane5.setViewportView(Thasil);

        panelGlass14.add(scrollPane5, java.awt.BorderLayout.PAGE_START);

        scrollPane4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Instruksi Tenaga Kesehatan Termasuk Pasca Bedah/Prosedur ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        scrollPane4.setName("scrollPane4"); // NOI18N
        scrollPane4.setPreferredSize(new java.awt.Dimension(212, 150));

        Tinstruksi.setEditable(false);
        Tinstruksi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tinstruksi.setColumns(20);
        Tinstruksi.setRows(5);
        Tinstruksi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Tinstruksi.setName("Tinstruksi"); // NOI18N
        Tinstruksi.setPreferredSize(new java.awt.Dimension(202, 4000));
        scrollPane4.setViewportView(Tinstruksi);

        panelGlass14.add(scrollPane4, java.awt.BorderLayout.CENTER);

        FormMenu.add(panelGlass14);

        PanelAccor.add(FormMenu, java.awt.BorderLayout.CENTER);

        getContentPane().add(PanelAccor, java.awt.BorderLayout.EAST);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else if (cmbKeadaan.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu keadaan umumnya..!!");
            cmbKeadaan.requestFocus();
        } else if (cmbKardio.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu kardiovaskularnya..!!");
            cmbKardio.requestFocus();
        } else if (cmbRespirasi.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu respirasinya..!!");
            cmbRespirasi.requestFocus();
        } else {
            hitungSkor();
            if (Sequel.menyimpantf("monitoring_pews_anak", "?,?,?,?,?,?,?,?,?,?,?,?,?", "Monitoring PEWS", 13, new String[]{
                TNoRw.getText(), TruangRwt.getText(), Valid.SetTgl(Ttgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                enumKeadaan, enumKardiovaskular, enumRespirasi, TskorKeadaan.getText(), TskorKardio.getText(), TskorRespi.getText(),
                TtotSkor.getText(), nip, Sequel.cariIsi("select now()")
            }) == true) {
                TCari.setText(TNoRw.getText());
                Valid.SetTgl(DTPCari1, Valid.SetTgl(Ttgl.getSelectedItem() + ""));
                emptTeks();
                BtnCariActionPerformed(null);
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
        tampil();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnHapus);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbPEWS.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {                
                if (Sequel.queryu2tf("delete from monitoring_pews_anak where waktu_simpan=?", 1, new String[]{
                    tbPEWS.getValueAt(tbPEWS.getSelectedRow(), 20).toString()
                }) == true) {
                    TCari.setText(TNoRw.getText());
                    Valid.SetTgl(DTPCari1, Valid.SetTgl(Ttgl.getSelectedItem() + ""));
                    emptTeks();
                    BtnCariActionPerformed(null);
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih salh satu datanya terlebih dahulu..!!");
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
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else if (cmbKeadaan.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu keadaan umumnya..!!");
            cmbKeadaan.requestFocus();
        } else if (cmbKardio.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu kardiovaskularnya..!!");
            cmbKardio.requestFocus();
        } else if (cmbRespirasi.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu respirasinya..!!");
            cmbRespirasi.requestFocus();
        } else {
            if (tbPEWS.getSelectedRow() > -1) {
                if (Sequel.mengedittf("monitoring_pews_anak", "waktu_simpan=?", "tanggal=?, jam=?, keadaan_umum=?, kardiovaskular=?, respirasi=?, "
                        + "skor_keadaan_umum=?, skor_kardiovaskular=?, skor_respirasi=?, nilai_skor=?, nip_perawat=?", 11, new String[]{
                            Valid.SetTgl(Ttgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                            enumKeadaan, enumKardiovaskular, enumRespirasi, TskorKeadaan.getText(), TskorKardio.getText(), TskorRespi.getText(),
                            TtotSkor.getText(), nip, tbPEWS.getValueAt(tbPEWS.getSelectedRow(), 20).toString()
                        }) == true) {

                    TCari.setText(TNoRw.getText());
                    Valid.SetTgl(DTPCari1, Valid.SetTgl(Ttgl.getSelectedItem() + ""));
                    emptTeks();
                    BtnCariActionPerformed(null);
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih salah satu datanya terlebih dahulu..!!");
            }
        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnEditActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnHapus, BtnKeluar);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
        WindowCetak.dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnEdit,TCari);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            tbPEWS.requestFocus();
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

    private void BtnCariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyReleased
        // TODO add your handling code here:
}//GEN-LAST:event_BtnCariKeyReleased

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
       emptTeks();
       tampil();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAllActionPerformed(null);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbPEWSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPEWSMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbPEWSMouseClicked

    private void tbPEWSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPEWSKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbPEWSKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void cmbDtkMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtkMouseReleased
        AutoCompleteDecorator.decorate(cmbDtk);
    }//GEN-LAST:event_cmbDtkMouseReleased

    private void cmbMntMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMntMouseReleased
        AutoCompleteDecorator.decorate(cmbMnt);
    }//GEN-LAST:event_cmbMntMouseReleased

    private void cmbJamMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJamMouseReleased
        AutoCompleteDecorator.decorate(cmbJam);
    }//GEN-LAST:event_cmbJamMouseReleased

    private void BtnPerawatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPerawatActionPerformed
        akses.setform("RMMonitoringPEWSAnak");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPerawatActionPerformed

    private void chkSayaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSayaActionPerformed
        if (chkSaya.isSelected() == true) {
            if (akses.getadmin() == true) {
                nip = "-";
                TnmPerawat.setText("-");
            } else {
                nip = akses.getkode();
                TnmPerawat.setText(Sequel.cariIsi("select nama from pegawai where nik='" + nip + "'"));
            }
        } else {
            nip = "-";
            TnmPerawat.setText("-");
        }
    }//GEN-LAST:event_chkSayaActionPerformed

    private void cmbKeadaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKeadaanActionPerformed
        skorKeadaan = 0;
        if (cmbKeadaan.getSelectedIndex() == 0) {
            skorKeadaan = 0;
            TskorKeadaan.setText("");
            enumKeadaan = "-";
        } else if (cmbKeadaan.getSelectedIndex() == 1) {
            skorKeadaan = 0;
            TskorKeadaan.setText("0");
            enumKeadaan = "1";
        } else if (cmbKeadaan.getSelectedIndex() == 2) {
            skorKeadaan = 1;
            TskorKeadaan.setText("1");
            enumKeadaan = "2";
        } else if (cmbKeadaan.getSelectedIndex() == 3) {
            skorKeadaan = 2;
            TskorKeadaan.setText("2");
            enumKeadaan = "3";
        } else if (cmbKeadaan.getSelectedIndex() == 4) {
            skorKeadaan = 3;
            TskorKeadaan.setText("3");
            enumKeadaan = "4";
        }
        hitungSkor();
    }//GEN-LAST:event_cmbKeadaanActionPerformed

    private void cmbKardioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKardioActionPerformed
        skorKardio = 0;
        if (cmbKardio.getSelectedIndex() == 0) {
            skorKardio = 0;
            TskorKardio.setText("");
            enumKardiovaskular = "-";
        } else if (cmbKardio.getSelectedIndex() == 1) {
            skorKardio = 0;
            TskorKardio.setText("0");
            enumKardiovaskular = "1";
        } else if (cmbKardio.getSelectedIndex() == 2) {
            skorKardio = 1;
            TskorKardio.setText("1");
            enumKardiovaskular = "2";
        } else if (cmbKardio.getSelectedIndex() == 3) {
            skorKardio = 2;
            TskorKardio.setText("2");
            enumKardiovaskular = "3";
        } else if (cmbKardio.getSelectedIndex() == 4) {
            skorKardio = 3;
            TskorKardio.setText("3");
            enumKardiovaskular = "4";
        }
        hitungSkor();
    }//GEN-LAST:event_cmbKardioActionPerformed

    private void cmbRespirasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRespirasiActionPerformed
        skorRespirasi = 0;
        if (cmbRespirasi.getSelectedIndex() == 0) {
            skorRespirasi = 0;
            TskorRespi.setText("");
            enumRespirasi = "-";
        } else if (cmbRespirasi.getSelectedIndex() == 1) {
            skorRespirasi = 0;
            TskorRespi.setText("0");
            enumRespirasi = "1";
        } else if (cmbRespirasi.getSelectedIndex() == 2) {
            skorRespirasi = 1;
            TskorRespi.setText("1");
            enumRespirasi = "2";
        } else if (cmbRespirasi.getSelectedIndex() == 3) {
            skorRespirasi = 2;
            TskorRespi.setText("2");
            enumRespirasi = "3";
        } else if (cmbRespirasi.getSelectedIndex() == 4) {
            skorRespirasi = 3;
            TskorRespi.setText("3");
            enumRespirasi = "4";
        }
        hitungSkor();
    }//GEN-LAST:event_cmbRespirasiActionPerformed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tbPEWS.getSelectedRow() > -1) {
            WindowCetak.setSize(533, 70);
            WindowCetak.setLocationRelativeTo(internalFrame1);
            WindowCetak.setAlwaysOnTop(false);
            WindowCetak.setVisible(true);
            Valid.SetTgl(tglA, Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + TNoRw.getText() + "'"));
            tglB.setDate(new Date());
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih salah satu datanya terlebih dulu..!!!!");
            tbPEWS.requestFocus();
        }
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnEdit, BtnKeluar);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void BtnCloseIn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn6ActionPerformed
        WindowCetak.dispose();
        tglA.setDate(new Date());
        tglB.setDate(new Date());
    }//GEN-LAST:event_BtnCloseIn6ActionPerformed

    private void BtnCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCetakActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        Valid.MyReport("rptMonitoringPEWS.jasper", "report", "::[ Lembar Monitoring Pediatric Early Warning Score (PEWS) ]::",
                "SELECT p.no_rkm_medis, p.nm_pasien, concat(date_format(p.tgl_lahir,'%d-%m-%Y'),' (',p.jk,')') tgllahir, concat(rp.umurdaftar,' ',rp.sttsumur) umur, m.ruang_rawat, "
                + "if(m.skor_keadaan_umum='0' and m.keadaan_umum='1',m.skor_keadaan_umum,'') ku1, "
                + "if(m.skor_keadaan_umum='1' and m.keadaan_umum='2',m.skor_keadaan_umum,'') ku2, "
                + "if(m.skor_keadaan_umum='2' and m.keadaan_umum='3',m.skor_keadaan_umum,'') ku3, "
                + "if(m.skor_keadaan_umum='3' and m.keadaan_umum='4',m.skor_keadaan_umum,'') ku4, "
                + "if(m.skor_kardiovaskular='0' and m.kardiovaskular='1',m.skor_kardiovaskular,'') kar1, "
                + "if(m.skor_kardiovaskular='1' and m.kardiovaskular='2',m.skor_kardiovaskular,'') kar2, "
                + "if(m.skor_kardiovaskular='2' and m.kardiovaskular='3',m.skor_kardiovaskular,'') kar3, "
                + "if(m.skor_kardiovaskular='3' and m.kardiovaskular='4',m.skor_kardiovaskular,'') kar4, "
                + "if(m.skor_respirasi='0' and m.respirasi='1',m.skor_respirasi,'') res1, "
                + "if(m.skor_respirasi='1' and m.respirasi='2',m.skor_respirasi,'') res2, "
                + "if(m.skor_respirasi='2' and m.respirasi='3',m.skor_respirasi,'') res3, "
                + "if(m.skor_respirasi='3' and m.respirasi='4',m.skor_respirasi,'') res4, "
                + "m.nilai_skor, date_format(m.tanggal,'%d/%m/%Y') tglews, time_format(m.jam,'%H:%i') jam from monitoring_pews_anak m "
                + "inner join reg_periksa rp on rp.no_rawat=m.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis WHERE "
                + "m.no_rawat = '" + TNoRw.getText() + "' AND m.tanggal between '" + Valid.SetTgl(tglA.getSelectedItem() + "") + "' "
                + "and '" + Valid.SetTgl(tglB.getSelectedItem() + "") + "' ORDER BY m.tanggal, m.jam", param);

        TCari.setText(TNoRw.getText());
        tampil();
        emptTeks();
        BtnCloseIn6ActionPerformed(null);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnCetakActionPerformed

    private void ChkAccorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkAccorActionPerformed
        isMenu();
    }//GEN-LAST:event_ChkAccorActionPerformed

    private void tbCPPTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCPPTMouseClicked
        if (tabModeCppt.getRowCount() != 0) {
            try {
                getDataCppt();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbCPPTMouseClicked

    private void tbCPPTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbCPPTKeyPressed
        if (tabModeCppt.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataCppt();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbCPPTKeyPressed

    private void tbDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDataMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbDataMouseClicked

    private void tbDataKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDataKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbDataKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMMonitoringPEWSAnak dialog = new RMMonitoringPEWSAnak(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCetak;
    private widget.Button BtnCloseIn6;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPerawat;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    public widget.CekBox ChkAccor;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormMenu;
    private widget.Label LCount;
    private widget.PanelBiasa PanelAccor;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll3;
    private widget.TextBox TCari;
    private widget.TextBox TNoRm;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private javax.swing.JTabbedPane TabSkor;
    private widget.TextArea Thasil;
    private widget.TextArea Tinstruksi;
    private widget.TextBox Tjenkel;
    private widget.TextBox TnmPerawat;
    private widget.TextBox TruangRwt;
    private widget.TextBox TskorKardio;
    private widget.TextBox TskorKeadaan;
    private widget.TextBox TskorRespi;
    private widget.Tanggal Ttgl;
    private widget.TextBox TtglLahir;
    private widget.TextBox TtotSkor;
    private widget.TextBox Tumur;
    private javax.swing.JDialog WindowCetak;
    private widget.CekBox chkSaya;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbKardio;
    private widget.ComboBox cmbKeadaan;
    private widget.ComboBox cmbMnt;
    private widget.ComboBox cmbRespirasi;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame10;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel15;
    private widget.Label jLabel17;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel20;
    private widget.Label jLabel21;
    private widget.Label jLabel22;
    private widget.Label jLabel23;
    private widget.Label jLabel24;
    private widget.Label jLabel30;
    private widget.Label jLabel31;
    private widget.Label jLabel32;
    private widget.Label jLabel49;
    private widget.Label jLabel5;
    private widget.Label jLabel50;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private widget.PanelBiasa panelBiasa10;
    private widget.PanelBiasa panelBiasa11;
    private widget.PanelBiasa panelBiasa8;
    private widget.PanelBiasa panelBiasa9;
    private widget.panelisi panelGlass14;
    private widget.panelisi panelGlass7;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollPane4;
    private widget.ScrollPane scrollPane5;
    private widget.TextArea skor1;
    private widget.TextArea skor2;
    private widget.TextArea skor3;
    private widget.TextArea skor4;
    private widget.Table tbCPPT;
    private widget.Table tbData;
    private widget.Table tbPEWS;
    private widget.Tanggal tglA;
    private widget.Tanggal tglB;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        dataKeadaan = "";
        dataKardio = "";
        dataRespi = "";
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("select me.*, p.no_rkm_medis, p.nm_pasien, date_format(me.tanggal,'%d-%m-%Y') tgl, time_format(me.jam,'%H:%i') jamPews, "
                    + "pg.nama perawat, if(p.jk='L','Laki-laki','Perempuan') jk, concat(rp.umurdaftar,' ',rp.sttsumur) umur, p.tgl_lahir "
                    + "from monitoring_pews_anak me inner join reg_periksa rp on rp.no_rawat=me.no_rawat "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis inner join pegawai pg on pg.nik=me.nip_perawat where "
                    + "me.tanggal between ? and ? and me.no_rawat like ? or "
                    + "me.tanggal between ? and ? and p.no_rkm_medis like ? or "
                    + "me.tanggal between ? and ? and p.nm_pasien like ? or "
                    + "me.tanggal between ? and ? and me.nip_perawat like ? or "
                    + "me.tanggal between ? and ? and pg.nama like ? or "
                    + "me.tanggal between ? and ? and me.ruang_rawat like ? order by me.tanggal desc, me.jam desc");
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
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (rs.getString("keadaan_umum").equals("1")) {
                        dataKeadaan = "Interaksi Biasa";
                    } else if (rs.getString("keadaan_umum").equals("2")) {
                        dataKeadaan = "Somnolen";
                    } else if (rs.getString("keadaan_umum").equals("3")) {
                        dataKeadaan = "Iritabel";
                    } else if (rs.getString("keadaan_umum").equals("4")) {
                        dataKeadaan = "Latergi, Gelisah, Penurunan Respon Terhadap Nyeri";
                    } else {
                        dataKeadaan = "-";
                    }
                    
                    if (rs.getString("kardiovaskular").equals("1")) {
                        dataKardio = "Tidak Sianosis ATAU Pengisian Kapiler < 2 Detik";
                    } else if (rs.getString("kardiovaskular").equals("2")) {
                        dataKardio = "Tampak Pucat ATAU Pengisian Kapiler 2 Detik";
                    } else if (rs.getString("kardiovaskular").equals("3")) {
                        dataKardio = "Tidak Sianosis ATAU Pengisian Kapiler > 2 Detik ATAU Takikardi > 20x diatas parameter sesuai usia/menit";
                    } else if (rs.getString("kardiovaskular").equals("4")) {
                        dataKardio = "Sianotik dan Motlet ATAU Pengisian Kapiler > 5 Detik ATAU Takikardi > 30x diatas parameter sesuai usia/menit ATAU Bradikardi (Sesuai Usia)";
                    } else {
                        dataKardio = "-";
                    }
                    
                    if (rs.getString("respirasi").equals("1")) {
                        dataRespi = "Tidak Sianotik ATAU Bernafas Normal Sesuai Parameter";
                    } else if (rs.getString("respirasi").equals("2")) {
                        dataRespi = "Takipnea > 10x diatas Parameter RR Sesuai Usia/Menit ATAU Menggunakan Otot Bantu Pernapasan ATAU Menggunakan FiO2 Lebih Dari 30% (Setara Dg. 3 L/Mnt Nasal Kanul)";
                    } else if (rs.getString("respirasi").equals("3")) {
                        dataRespi = "Takipnea > 20x diatas Parameter RR Sesuai Usia/Menit ATAU Ada Retraksi ATAU Menggunakan FiO2 Lebih Dari 40% (Setara Dg. 6 L/Mnt Simple Mask)";
                    } else if (rs.getString("respirasi").equals("4")) {
                        dataRespi = "Takipnea >= 25x diatas Parameter RR Sesuai Usia/Menit Dengan Retraksi Dada ATAU Merintih ATAU Menggunakan FiO2 Lebih Dari 50% (Setara Dg. 8 L/Mnt Simple Mask)";
                    } else {
                        dataRespi = "-";
                    }
                    
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        Valid.SetTglINDONESIA(rs.getString("tgl_lahir")),
                        rs.getString("umur"),
                        rs.getString("jk"),
                        rs.getString("ruang_rawat"),
                        rs.getString("tgl"),
                        rs.getString("jamPews"),
                        dataKeadaan,
                        rs.getString("skor_keadaan_umum"),
                        dataKardio,
                        rs.getString("skor_kardiovaskular"),
                        dataRespi,
                        rs.getString("skor_respirasi"),
                        rs.getString("nilai_skor"),
                        rs.getString("perawat"),
                        rs.getString("tanggal"),
                        rs.getString("jam"),
                        rs.getString("nip_perawat"),
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

    public void emptTeks() {
        tampilData();
        Ttgl.setDate(new Date());
        cmbJam.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk.setSelectedIndex(0);
        nip = "-";
        TnmPerawat.setText("-");
        
        cmbKeadaan.setSelectedIndex(0);
        cmbKardio.setSelectedIndex(0);
        cmbRespirasi.setSelectedIndex(0);
        
        TskorKeadaan.setText("");
        TskorKardio.setText("");
        TskorRespi.setText("");
        
        skorKeadaan = 0;
        skorKardio = 0;
        skorRespirasi = 0;
        enumKeadaan = "";
        enumKardiovaskular = "";
        enumRespirasi = "";
        hitungSkor();
    }

    private void getData() {
        nip = "";
        enumKeadaan = "";
        enumKardiovaskular = "";
        enumRespirasi = "";
        skorKeadaan = 0;
        skorKardio = 0;
        skorRespirasi = 0;
        total = 0;
        
        if (tbPEWS.getSelectedRow() != -1) {
            TNoRw.setText(tbPEWS.getValueAt(tbPEWS.getSelectedRow(),0).toString());
            TNoRm.setText(tbPEWS.getValueAt(tbPEWS.getSelectedRow(),1).toString());
            TPasien.setText(tbPEWS.getValueAt(tbPEWS.getSelectedRow(),2).toString());
            TtglLahir.setText(tbPEWS.getValueAt(tbPEWS.getSelectedRow(),3).toString());
            Tumur.setText(tbPEWS.getValueAt(tbPEWS.getSelectedRow(),4).toString());
            Tjenkel.setText(tbPEWS.getValueAt(tbPEWS.getSelectedRow(),5).toString());
            TruangRwt.setText(tbPEWS.getValueAt(tbPEWS.getSelectedRow(),6).toString());
            Valid.SetTgl(Ttgl, tbPEWS.getValueAt(tbPEWS.getSelectedRow(),17).toString());
            cmbJam.setSelectedItem(tbPEWS.getValueAt(tbPEWS.getSelectedRow(),18).toString().substring(0, 2));
            cmbMnt.setSelectedItem(tbPEWS.getValueAt(tbPEWS.getSelectedRow(),18).toString().substring(3, 5));
            cmbDtk.setSelectedItem(tbPEWS.getValueAt(tbPEWS.getSelectedRow(),18).toString().substring(6, 8));
            cmbKeadaan.setSelectedItem(tbPEWS.getValueAt(tbPEWS.getSelectedRow(),9).toString());
            TskorKeadaan.setText(tbPEWS.getValueAt(tbPEWS.getSelectedRow(),10).toString());
            cmbKardio.setSelectedItem(tbPEWS.getValueAt(tbPEWS.getSelectedRow(),11).toString());
            TskorKardio.setText(tbPEWS.getValueAt(tbPEWS.getSelectedRow(),12).toString());
            cmbRespirasi.setSelectedItem(tbPEWS.getValueAt(tbPEWS.getSelectedRow(),13).toString());
            TskorRespi.setText(tbPEWS.getValueAt(tbPEWS.getSelectedRow(),14).toString());
            TtotSkor.setText(tbPEWS.getValueAt(tbPEWS.getSelectedRow(),15).toString());
            nip = tbPEWS.getValueAt(tbPEWS.getSelectedRow(),19).toString();
            TnmPerawat.setText(tbPEWS.getValueAt(tbPEWS.getSelectedRow(),16).toString());
            
            skorKeadaan = Integer.parseInt(TskorKeadaan.getText());
            skorKardio = Integer.parseInt(TskorKardio.getText());
            skorRespirasi = Integer.parseInt(TskorRespi.getText());
            total = Integer.parseInt(TtotSkor.getText());
            
            if (cmbKeadaan.getSelectedIndex() == 1) {
                enumKeadaan = "1";
            } else if (cmbKeadaan.getSelectedIndex() == 2) {
                enumKeadaan = "2";
            } else if (cmbKeadaan.getSelectedIndex() == 3) {
                enumKeadaan = "3";
            } else if (cmbKeadaan.getSelectedIndex() == 4) {
                enumKeadaan = "4";
            } else {
                enumKeadaan = "-";
            }
            
            if (cmbKardio.getSelectedIndex() == 1) {
                enumKardiovaskular = "1";
            } else if (cmbKardio.getSelectedIndex() == 2) {
                enumKardiovaskular = "2";
            } else if (cmbKardio.getSelectedIndex() == 3) {
                enumKardiovaskular = "3";
            } else if (cmbKardio.getSelectedIndex() == 4) {
                enumKardiovaskular = "4";
            } else {
                enumKardiovaskular = "-";
            }
            
            if (cmbRespirasi.getSelectedIndex() == 1) {
                enumRespirasi = "1";
            } else if (cmbRespirasi.getSelectedIndex() == 2) {
                enumRespirasi = "2";
            } else if (cmbRespirasi.getSelectedIndex() == 3) {
                enumRespirasi = "3";
            } else if (cmbRespirasi.getSelectedIndex() == 4) {
                enumRespirasi = "4";
            } else {
                enumRespirasi = "-";
            }
        }
    }
    
    public void isCek(){
       BtnSimpan.setEnabled(akses.getcppt());
       BtnHapus.setEnabled(akses.getcppt());
       BtnEdit.setEnabled(akses.getcppt());    
    }
    
    private void hitungSkor() {
        total = 0;
        total = skorKeadaan + skorKardio + skorRespirasi;
        TtotSkor.setText(Valid.SetAngka2(total));
        
        if (total >= 0 && total <= 2) {
            TabSkor.setSelectedIndex(0);
        } else if (total >= 3 && total <= 4) {
            TabSkor.setSelectedIndex(1);
        } else if (total >= 5) {
            TabSkor.setSelectedIndex(2);
        }
    }
    
    public void setData(String norw, String norm, String nmpasien, String ruangan) {
        TNoRw.setText(norw);
        TNoRm.setText(norm);
        TPasien.setText(nmpasien);
        TruangRwt.setText(ruangan);
        TtglLahir.setText(Valid.SetTglINDONESIA(Sequel.cariIsi("select tgl_lahir from pasien where no_rkm_medis='" + norm + "'")));
        Tjenkel.setText(Sequel.cariIsi("select if(jk='L','Laki-laki','Perempuan') from pasien where no_rkm_medis='" + norm + "'"));
        Tumur.setText(Sequel.cariIsi("select concat(umurdaftar,' ',sttsumur) from reg_periksa where no_rawat='" + norw + "'"));
        TCari.setText(norw);
        
        if (Sequel.cariInteger("select count(-1) from monitoring_pews_anak where no_rawat='" + norw + "'") > 0) {
            Valid.SetTgl(DTPCari1, Sequel.cariIsi("select tanggal from monitoring_pews_anak where "
                    + "no_rawat='" + norw + "' order by tanggal desc limit 1"));
        } else {
            DTPCari1.setDate(new Date());
        }
    }
    
    public void isMenu() {
        if (ChkAccor.isSelected() == true) {
            ChkAccor.setVisible(false);
            PanelAccor.setPreferredSize(new Dimension(900, HEIGHT));
            FormMenu.setVisible(true);
            ChkAccor.setVisible(true);
            Thasil.setText("");
            Tinstruksi.setText("");
            tampilCppt();
        } else if (ChkAccor.isSelected() == false) {
            ChkAccor.setVisible(false);
            PanelAccor.setPreferredSize(new Dimension(22, HEIGHT));
            FormMenu.setVisible(false);
            ChkAccor.setVisible(true);
        }
    }
    
    private void tampilCppt() {
        Valid.tabelKosong(tabModeCppt);
        try {
            pscppt = koneksi.prepareStatement("SELECT c.verifikasi, DATE_FORMAT(c.tgl_cppt,'%d-%m-%Y') tgl, if(c.cek_jam='ya',TIME_FORMAT(c.jam_cppt,'%H:%i'),'-') jam, "
                    + "c.jenis_bagian, pg1.nama nmdpjp, c.jenis_ppa, pg2.nama nmppa, c.cppt_shift, c.hasil_pemeriksaan, "
                    + "c.instruksi_nakes, c.waktu_simpan, c.no_rawat, c.tgl_cppt, c.jam_cppt from cppt c "
                    + "inner join pegawai pg1 on pg1.nik=c.nip_konsulen "
                    + "inner join pegawai pg2 on pg2.nik=c.nip_ppa where "
                    + "c.flag_hapus='tidak' and c.status='ranap' and c.no_rawat='" + TNoRw.getText() + "' order by c.tgl_cppt, c.jam_cppt");
            try {
                rscppt = pscppt.executeQuery();                
                while (rscppt.next()) {
                    tabModeCppt.addRow(new String[]{
                        rscppt.getString("tgl"),
                        rscppt.getString("jam"),
                        rscppt.getString("jenis_bagian"),
                        rscppt.getString("nmdpjp"),
                        rscppt.getString("jenis_ppa"),
                        rscppt.getString("nmppa"),
                        rscppt.getString("cppt_shift"),
                        rscppt.getString("hasil_pemeriksaan"),
                        rscppt.getString("instruksi_nakes"),
                        rscppt.getString("no_rawat"),
                        rscppt.getString("tgl_cppt"),
                        rscppt.getString("jam_cppt")
                    });
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rscppt != null) {
                    rscppt.close();
                }
                if (pscppt != null) {
                    pscppt.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void getDataCppt() {
        dataKonfirmasi = "";
        
        if (tbCPPT.getSelectedRow() != -1) {
            Thasil.setText("Tgl. CPPT : " + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 0).toString() + ", Jam : " + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 1).toString() + " WITA\n\n"
                    + "" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 7).toString());
            
            //konfirmasi terapi
            if (Sequel.cariInteger("select count(-1) from cppt_konfirmasi_terapi where "
                    + "no_rawat='" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 9).toString() + "' "
                    + "and tgl_cppt='" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 10).toString() + "' "
                    + "and cppt_shift='" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 6).toString() + "' "
                    + "and jam_cppt='" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 11).toString() + "'") > 0) {

                tampilKonfirmasi(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 9).toString(),
                        tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 10).toString(),
                        tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 6).toString(),
                        tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 11).toString());
                
                if (tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 5).toString().equals("-")) {
                    Tinstruksi.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 8).toString() + "\n\n"
                            + "KONFIRMASI TERAPI VIA TELP. :\n\n" + dataKonfirmasi);
                } else {
                    Tinstruksi.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 8).toString() + "\n\n"
                            + "(" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 5).toString() + ")\n\n"
                            + "KONFIRMASI TERAPI VIA TELP. :\n\n" + dataKonfirmasi);
                }
            } else {
                if (tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 5).toString().equals("-")) {
                    Tinstruksi.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 8).toString());
                } else {
                    Tinstruksi.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 8).toString() + "\n\n"
                            + "(" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 5).toString() + ")");
                }
            }
        }
    }
    
    private void tampilKonfirmasi(String norwt, String tglcppt, String sift, String jamcppt) {
        try {
            ps1 = koneksi.prepareStatement("select pg1.nama ptgs, date_format(ck.tgl_lapor,'%d-%m-%Y') tgllapor, time_format(ck.jam_lapor,'%H:%i') jamlapor, "
                    + "pg2.nama dpjp, date_format(ck.tgl_verifikasi,'%d-%m-%Y') tglverif, time_format(ck.jam_verifikasi,'%H:%i') jamverif from cppt_konfirmasi_terapi ck "
                    + "inner join pegawai pg1 on pg1.nik=ck.nip_petugas_konfir inner join pegawai pg2 on pg2.nik=ck.nip_dpjp_konfir where "
                    + "ck.no_rawat = '" + norwt + "' and ck.tgl_cppt='" + tglcppt + "' and ck.cppt_shift='" + sift + "' "
                    + "and ck.jam_cppt='" + jamcppt + "' order by ck.waktu_simpan");
            try {
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    if (dataKonfirmasi.equals("")) {
                        dataKonfirmasi = "Tgl. Lapor : " + rs1.getString("tgllapor") + ", Jam : " + rs1.getString("jamlapor") + " WITA\n"
                                + "Tgl. Verifikasi : " + rs1.getString("tglverif") + ", Jam : " + rs1.getString("jamverif") + " WITA\n"
                                + "Nama Petugas : " + rs1.getString("ptgs") + "\n"
                                + "Dengan DPJP : " + rs1.getString("dpjp");
                    } else {
                        dataKonfirmasi = dataKonfirmasi + "\n\nTgl. Lapor : " + rs1.getString("tgllapor") + ", Jam : " + rs1.getString("jamlapor") + " WITA\n"
                                + "Tgl. Verifikasi : " + rs1.getString("tglverif") + ", Jam : " + rs1.getString("jamverif") + " WITA\n"
                                + "Nama Petugas : " + rs1.getString("ptgs") + "\n"
                                + "Dengan DPJP : " + rs1.getString("dpjp");
                    }
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
    
    private void tampilData() {
        Valid.tabelKosong(tabMode1);
        tabMode1.addRow(new String[]{"Neonatus", "0-1 bulan", "100 - 180", "40 - 60"});
        tabMode1.addRow(new String[]{"Bayi", "1-12 bulan", "100 - 180", "35 - 40"});
        tabMode1.addRow(new String[]{"Balita", "13-36 bulan", "70 - 110", "25 - 30"});
        tabMode1.addRow(new String[]{"Pra-Sekolah", "4-6 tahun", "70 - 110", "21 - 23"});
        tabMode1.addRow(new String[]{"Sekolah", "7-12 tahun", "70 - 110", "19 - 21"});
        tabMode1.addRow(new String[]{"Remaja", "13-16 tahun", "55 - 90", "16 - 18"});
    }
}
