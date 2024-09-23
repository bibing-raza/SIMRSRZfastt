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
public class RMMonitoringEWSDewasa extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabModeCppt;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps1, pscppt;
    private ResultSet rs, rs1, rscppt;
    private int i = 0, x = 0, respirasi = 0, saturasi = 0, suplemen = 0, tensi = 0, nadi = 0, kesadaran = 0, temperatur = 0, total = 0;
    private String nip = "", dataKonfirmasi = "";
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);

    /** Creates new form DlgSpesialis
     * @param parent
     * @param modal */
    public RMMonitoringEWSDewasa(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        Object[] row = {"No. Rawat", "No. RM", "Nama Pasien", "Ruang Rawat", "Tanggal", "Jam", "Laju Respirasi", "Skor",
            "Saturasi", "Skor", "Suplemen", "Skor", "Tensi", "Skor", "Laju Jantung", "Skor", "Kesadaran", "Skor", 
            "Temperatur", "Skor", "Tot. Skor", "GDS", "Skor Nyeri", "Urin Output", "Nama Perawat", "tanggal", "jam", 
            "nip", "waktu_simpan"
        };
        
        tabMode=new DefaultTableModel(null,row){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };

        tbEWS.setModel(tabMode);
        tbEWS.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbEWS.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 29; i++) {
            TableColumn column = tbEWS.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(55);
            } else if (i == 2) {
                column.setPreferredWidth(220);
            } else if (i == 3) {
                column.setPreferredWidth(250);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(50);
            } else if (i == 6) {
                column.setPreferredWidth(80);
            } else if (i == 7) {
                column.setPreferredWidth(40);
            } else if (i == 8) {
                column.setPreferredWidth(80);
            } else if (i == 9) {
                column.setPreferredWidth(40);
            } else if (i == 10) {
                column.setPreferredWidth(80);
            } else if (i == 11) {
                column.setPreferredWidth(40);
            } else if (i == 12) {
                column.setPreferredWidth(80);
            } else if (i == 13) {
                column.setPreferredWidth(40);
            } else if (i == 14) {
                column.setPreferredWidth(80);
            } else if (i == 15) {
                column.setPreferredWidth(40);
            } else if (i == 16) {
                column.setPreferredWidth(80);
            } else if (i == 17) {
                column.setPreferredWidth(40);
            } else if (i == 18) {
                column.setPreferredWidth(80);
            } else if (i == 19) {
                column.setPreferredWidth(40);
            } else if (i == 20) {
                column.setPreferredWidth(80);
            } else if (i == 21) {
                column.setPreferredWidth(60);
            } else if (i == 22) {
                column.setPreferredWidth(75);
            } else if (i == 23) {
                column.setPreferredWidth(75);
            } else if (i == 24) {
                column.setPreferredWidth(220);
            } else if (i == 25) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 26) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 27) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 28) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbEWS.setDefaultRenderer(Object.class, new WarnaTable());
        
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

        Tgds.setDocument(new batasInput((int) 10).getKata(Tgds));
        TskorNyeri.setDocument(new batasInput((int) 5).getKata(TskorNyeri));
        Turin.setDocument(new batasInput((int) 5).getKata(Turin));
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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnHasilPemeriksaanPenunjang = new javax.swing.JMenuItem();
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
        tbEWS = new widget.Table();
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
        cmbRespirasi = new widget.ComboBox();
        jLabel11 = new widget.Label();
        cmbSaturasi = new widget.ComboBox();
        jLabel12 = new widget.Label();
        cmbTensi = new widget.ComboBox();
        jLabel13 = new widget.Label();
        cmbNadi = new widget.ComboBox();
        jLabel14 = new widget.Label();
        cmbKesadaran = new widget.ComboBox();
        jLabel16 = new widget.Label();
        cmbTempe = new widget.ComboBox();
        jLabel17 = new widget.Label();
        jLabel18 = new widget.Label();
        TskorRespi = new widget.TextBox();
        TskorSatu = new widget.TextBox();
        jLabel21 = new widget.Label();
        TskorSuple = new widget.TextBox();
        jLabel23 = new widget.Label();
        TskorTensi = new widget.TextBox();
        jLabel25 = new widget.Label();
        TskorNadi = new widget.TextBox();
        jLabel27 = new widget.Label();
        TskorKesadaran = new widget.TextBox();
        jLabel29 = new widget.Label();
        TskorTempe = new widget.TextBox();
        jLabel31 = new widget.Label();
        cmbSuplemen = new widget.ComboBox();
        jLabel19 = new widget.Label();
        TtotSkor = new widget.TextBox();
        jLabel20 = new widget.Label();
        TnmPerawat = new widget.TextBox();
        jLabel22 = new widget.Label();
        Tgds = new widget.TextBox();
        jLabel24 = new widget.Label();
        TskorNyeri = new widget.TextBox();
        jLabel26 = new widget.Label();
        Turin = new widget.TextBox();
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
        jLabel28 = new widget.Label();
        kesimpulanEWS = new widget.TextArea();
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

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnHasilPemeriksaanPenunjang.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHasilPemeriksaanPenunjang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnHasilPemeriksaanPenunjang.setText("Hasil Pemeriksaan Penunjang");
        MnHasilPemeriksaanPenunjang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHasilPemeriksaanPenunjang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHasilPemeriksaanPenunjang.setIconTextGap(5);
        MnHasilPemeriksaanPenunjang.setName("MnHasilPemeriksaanPenunjang"); // NOI18N
        MnHasilPemeriksaanPenunjang.setPreferredSize(new java.awt.Dimension(190, 26));
        MnHasilPemeriksaanPenunjang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHasilPemeriksaanPenunjangActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnHasilPemeriksaanPenunjang);

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

        tglA.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-03-2024" }));
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

        tglB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-03-2024" }));
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

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Monitoring Early Warning Score (EWS) Dewasa ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setComponentPopupMenu(jPopupMenu1);
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbEWS.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbEWS.setComponentPopupMenu(jPopupMenu1);
        tbEWS.setName("tbEWS"); // NOI18N
        tbEWS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbEWSMouseClicked(evt);
            }
        });
        tbEWS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbEWSKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbEWS);

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

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-03-2024" }));
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

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-03-2024" }));
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

        panelGlass7.setComponentPopupMenu(jPopupMenu1);
        panelGlass7.setName("panelGlass7"); // NOI18N
        panelGlass7.setPreferredSize(new java.awt.Dimension(44, 328));
        panelGlass7.setLayout(null);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Pasien :");
        jLabel5.setName("jLabel5"); // NOI18N
        panelGlass7.add(jLabel5);
        jLabel5.setBounds(0, 10, 160, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Ruang Rawat :");
        jLabel15.setName("jLabel15"); // NOI18N
        panelGlass7.add(jLabel15);
        jLabel15.setBounds(0, 38, 160, 23);

        TruangRwt.setEditable(false);
        TruangRwt.setForeground(new java.awt.Color(0, 0, 0));
        TruangRwt.setName("TruangRwt"); // NOI18N
        panelGlass7.add(TruangRwt);
        TruangRwt.setBounds(165, 38, 390, 23);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        panelGlass7.add(TNoRw);
        TNoRw.setBounds(165, 10, 122, 23);

        TNoRm.setEditable(false);
        TNoRm.setForeground(new java.awt.Color(0, 0, 0));
        TNoRm.setName("TNoRm"); // NOI18N
        panelGlass7.add(TNoRm);
        TNoRm.setBounds(292, 10, 70, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setName("TPasien"); // NOI18N
        panelGlass7.add(TPasien);
        TPasien.setBounds(366, 10, 350, 23);

        cmbDtk.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtkMouseReleased(evt);
            }
        });
        panelGlass7.add(cmbDtk);
        cmbDtk.setBounds(411, 66, 45, 23);

        cmbMnt.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMntMouseReleased(evt);
            }
        });
        panelGlass7.add(cmbMnt);
        cmbMnt.setBounds(359, 66, 45, 23);

        cmbJam.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJamMouseReleased(evt);
            }
        });
        panelGlass7.add(cmbJam);
        cmbJam.setBounds(308, 66, 45, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Jam :");
        jLabel9.setName("jLabel9"); // NOI18N
        panelGlass7.add(jLabel9);
        jLabel9.setBounds(260, 66, 40, 23);

        Ttgl.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-03-2024" }));
        Ttgl.setDisplayFormat("dd-MM-yyyy");
        Ttgl.setName("Ttgl"); // NOI18N
        Ttgl.setOpaque(false);
        Ttgl.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass7.add(Ttgl);
        Ttgl.setBounds(165, 66, 90, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Tanggal :");
        jLabel8.setName("jLabel8"); // NOI18N
        panelGlass7.add(jLabel8);
        jLabel8.setBounds(0, 66, 160, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Laju Respirasi (/menit) :");
        jLabel10.setName("jLabel10"); // NOI18N
        panelGlass7.add(jLabel10);
        jLabel10.setBounds(0, 94, 160, 23);

        cmbRespirasi.setForeground(new java.awt.Color(0, 0, 0));
        cmbRespirasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "<= 5", "6 - 8", "9 - 11", "12 - 20", "21 - 24", "25 - 34", ">= 35" }));
        cmbRespirasi.setName("cmbRespirasi"); // NOI18N
        cmbRespirasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRespirasiActionPerformed(evt);
            }
        });
        panelGlass7.add(cmbRespirasi);
        cmbRespirasi.setBounds(165, 94, 70, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Saturasi (O2) :");
        jLabel11.setName("jLabel11"); // NOI18N
        panelGlass7.add(jLabel11);
        jLabel11.setBounds(0, 122, 160, 23);

        cmbSaturasi.setForeground(new java.awt.Color(0, 0, 0));
        cmbSaturasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", ">= 96", "94 - 95", "92 - 93", "<= 91" }));
        cmbSaturasi.setName("cmbSaturasi"); // NOI18N
        cmbSaturasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSaturasiActionPerformed(evt);
            }
        });
        panelGlass7.add(cmbSaturasi);
        cmbSaturasi.setBounds(165, 122, 70, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("T. Darah Sistole (mmHg) :");
        jLabel12.setName("jLabel12"); // NOI18N
        panelGlass7.add(jLabel12);
        jLabel12.setBounds(0, 178, 160, 23);

        cmbTensi.setForeground(new java.awt.Color(0, 0, 0));
        cmbTensi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", ">= 220", "181 - 220", "111 - 180", "101 - 110", "91 - 100", "71 - 90", "<= 70" }));
        cmbTensi.setName("cmbTensi"); // NOI18N
        cmbTensi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTensiActionPerformed(evt);
            }
        });
        panelGlass7.add(cmbTensi);
        cmbTensi.setBounds(165, 178, 83, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Laju Jantung/Nadi (/menit) :");
        jLabel13.setName("jLabel13"); // NOI18N
        panelGlass7.add(jLabel13);
        jLabel13.setBounds(0, 206, 160, 23);

        cmbNadi.setForeground(new java.awt.Color(0, 0, 0));
        cmbNadi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", ">= 140", "131 - 140", "111 - 130", "91 - 110", "51 - 90", "41 - 50", "<= 40" }));
        cmbNadi.setName("cmbNadi"); // NOI18N
        cmbNadi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbNadiActionPerformed(evt);
            }
        });
        panelGlass7.add(cmbNadi);
        cmbNadi.setBounds(165, 206, 80, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Kesadaran :");
        jLabel14.setName("jLabel14"); // NOI18N
        panelGlass7.add(jLabel14);
        jLabel14.setBounds(0, 234, 160, 23);

        cmbKesadaran.setForeground(new java.awt.Color(0, 0, 0));
        cmbKesadaran.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Sadar", "Nyeri/Verbal", "Unrespon" }));
        cmbKesadaran.setName("cmbKesadaran"); // NOI18N
        cmbKesadaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKesadaranActionPerformed(evt);
            }
        });
        panelGlass7.add(cmbKesadaran);
        cmbKesadaran.setBounds(165, 234, 95, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Temperatur (°C) :");
        jLabel16.setName("jLabel16"); // NOI18N
        panelGlass7.add(jLabel16);
        jLabel16.setBounds(0, 262, 160, 23);

        cmbTempe.setForeground(new java.awt.Color(0, 0, 0));
        cmbTempe.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "<= 35", "35,1 - 36", "36,1 - 38", "38,1 - 39", ">= 39" }));
        cmbTempe.setName("cmbTempe"); // NOI18N
        cmbTempe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTempeActionPerformed(evt);
            }
        });
        panelGlass7.add(cmbTempe);
        cmbTempe.setBounds(165, 262, 83, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Skor :");
        jLabel17.setName("jLabel17"); // NOI18N
        panelGlass7.add(jLabel17);
        jLabel17.setBounds(260, 94, 45, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Skor :");
        jLabel18.setName("jLabel18"); // NOI18N
        panelGlass7.add(jLabel18);
        jLabel18.setBounds(260, 122, 45, 23);

        TskorRespi.setEditable(false);
        TskorRespi.setForeground(new java.awt.Color(0, 0, 0));
        TskorRespi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TskorRespi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TskorRespi.setName("TskorRespi"); // NOI18N
        panelGlass7.add(TskorRespi);
        TskorRespi.setBounds(309, 94, 65, 23);

        TskorSatu.setEditable(false);
        TskorSatu.setForeground(new java.awt.Color(0, 0, 0));
        TskorSatu.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TskorSatu.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TskorSatu.setName("TskorSatu"); // NOI18N
        panelGlass7.add(TskorSatu);
        TskorSatu.setBounds(309, 122, 65, 23);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Skor :");
        jLabel21.setName("jLabel21"); // NOI18N
        panelGlass7.add(jLabel21);
        jLabel21.setBounds(260, 150, 45, 23);

        TskorSuple.setEditable(false);
        TskorSuple.setForeground(new java.awt.Color(0, 0, 0));
        TskorSuple.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TskorSuple.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TskorSuple.setName("TskorSuple"); // NOI18N
        panelGlass7.add(TskorSuple);
        TskorSuple.setBounds(309, 150, 65, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Skor :");
        jLabel23.setName("jLabel23"); // NOI18N
        panelGlass7.add(jLabel23);
        jLabel23.setBounds(260, 178, 45, 23);

        TskorTensi.setEditable(false);
        TskorTensi.setForeground(new java.awt.Color(0, 0, 0));
        TskorTensi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TskorTensi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TskorTensi.setName("TskorTensi"); // NOI18N
        panelGlass7.add(TskorTensi);
        TskorTensi.setBounds(309, 178, 65, 23);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Skor :");
        jLabel25.setName("jLabel25"); // NOI18N
        panelGlass7.add(jLabel25);
        jLabel25.setBounds(260, 206, 45, 23);

        TskorNadi.setEditable(false);
        TskorNadi.setForeground(new java.awt.Color(0, 0, 0));
        TskorNadi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TskorNadi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TskorNadi.setName("TskorNadi"); // NOI18N
        panelGlass7.add(TskorNadi);
        TskorNadi.setBounds(309, 206, 65, 23);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("Skor :");
        jLabel27.setName("jLabel27"); // NOI18N
        panelGlass7.add(jLabel27);
        jLabel27.setBounds(260, 234, 45, 23);

        TskorKesadaran.setEditable(false);
        TskorKesadaran.setForeground(new java.awt.Color(0, 0, 0));
        TskorKesadaran.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TskorKesadaran.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TskorKesadaran.setName("TskorKesadaran"); // NOI18N
        panelGlass7.add(TskorKesadaran);
        TskorKesadaran.setBounds(309, 234, 65, 23);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("Skor :");
        jLabel29.setName("jLabel29"); // NOI18N
        panelGlass7.add(jLabel29);
        jLabel29.setBounds(260, 262, 45, 23);

        TskorTempe.setEditable(false);
        TskorTempe.setForeground(new java.awt.Color(0, 0, 0));
        TskorTempe.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TskorTempe.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TskorTempe.setName("TskorTempe"); // NOI18N
        panelGlass7.add(TskorTempe);
        TskorTempe.setBounds(309, 262, 65, 23);

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("Suplemen (O2) :");
        jLabel31.setName("jLabel31"); // NOI18N
        panelGlass7.add(jLabel31);
        jLabel31.setBounds(0, 150, 160, 23);

        cmbSuplemen.setForeground(new java.awt.Color(0, 0, 0));
        cmbSuplemen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbSuplemen.setName("cmbSuplemen"); // NOI18N
        cmbSuplemen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSuplemenActionPerformed(evt);
            }
        });
        panelGlass7.add(cmbSuplemen);
        cmbSuplemen.setBounds(165, 150, 60, 23);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Total Skor :");
        jLabel19.setName("jLabel19"); // NOI18N
        panelGlass7.add(jLabel19);
        jLabel19.setBounds(375, 262, 80, 23);

        TtotSkor.setEditable(false);
        TtotSkor.setForeground(new java.awt.Color(0, 0, 0));
        TtotSkor.setName("TtotSkor"); // NOI18N
        panelGlass7.add(TtotSkor);
        TtotSkor.setBounds(460, 262, 55, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Nama Perawat :");
        jLabel20.setName("jLabel20"); // NOI18N
        panelGlass7.add(jLabel20);
        jLabel20.setBounds(0, 290, 160, 23);

        TnmPerawat.setEditable(false);
        TnmPerawat.setForeground(new java.awt.Color(0, 0, 0));
        TnmPerawat.setName("TnmPerawat"); // NOI18N
        panelGlass7.add(TnmPerawat);
        TnmPerawat.setBounds(165, 290, 350, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("GDS :");
        jLabel22.setName("jLabel22"); // NOI18N
        panelGlass7.add(jLabel22);
        jLabel22.setBounds(375, 94, 80, 23);

        Tgds.setForeground(new java.awt.Color(0, 0, 0));
        Tgds.setName("Tgds"); // NOI18N
        Tgds.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgdsKeyPressed(evt);
            }
        });
        panelGlass7.add(Tgds);
        Tgds.setBounds(460, 94, 55, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Skor Nyeri :");
        jLabel24.setName("jLabel24"); // NOI18N
        panelGlass7.add(jLabel24);
        jLabel24.setBounds(375, 122, 80, 23);

        TskorNyeri.setForeground(new java.awt.Color(0, 0, 0));
        TskorNyeri.setName("TskorNyeri"); // NOI18N
        TskorNyeri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TskorNyeriKeyPressed(evt);
            }
        });
        panelGlass7.add(TskorNyeri);
        TskorNyeri.setBounds(460, 122, 55, 23);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Urin Output :");
        jLabel26.setName("jLabel26"); // NOI18N
        panelGlass7.add(jLabel26);
        jLabel26.setBounds(375, 150, 80, 23);

        Turin.setForeground(new java.awt.Color(0, 0, 0));
        Turin.setName("Turin"); // NOI18N
        Turin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TurinKeyPressed(evt);
            }
        });
        panelGlass7.add(Turin);
        Turin.setBounds(460, 150, 55, 23);

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
        BtnPerawat.setBounds(520, 290, 28, 23);

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
        chkSaya.setBounds(560, 290, 100, 23);

        TabSkor.setBackground(new java.awt.Color(255, 255, 254));
        TabSkor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabSkor.setName("TabSkor"); // NOI18N

        panelBiasa8.setName("panelBiasa8"); // NOI18N
        panelBiasa8.setLayout(new java.awt.BorderLayout());

        skor1.setEditable(false);
        skor1.setBackground(new java.awt.Color(255, 255, 102));
        skor1.setColumns(20);
        skor1.setRows(5);
        skor1.setText("Assesmen segera oleh Perawat Senior (Respon maksimal 5 menit), eskalasi perawatan dan frekuensi monitoring per 4 - 6 jam, jika diperlukan konsultasi ke Dokter Jaga.");
        skor1.setName("skor1"); // NOI18N
        skor1.setOpaque(true);
        panelBiasa8.add(skor1, java.awt.BorderLayout.CENTER);

        TabSkor.addTab("Skor 1-4 (Resiko Ringan)", panelBiasa8);

        panelBiasa9.setName("panelBiasa9"); // NOI18N
        panelBiasa9.setLayout(new java.awt.BorderLayout());

        skor2.setEditable(false);
        skor2.setBackground(new java.awt.Color(250, 176, 101));
        skor2.setColumns(20);
        skor2.setRows(5);
        skor2.setText("Assesmen segera oleh Dokter Jaga (Respon maksimal 5 menit), konsultasi ke DPJP dan atau Dokter Spesialis terkait, eskalasi perawatan dan frekuensi monitoring tiap jam, pertimbangan eskalasi perawatan ke unit intensif care, edukasi keluarga pasien.");
        skor2.setName("skor2"); // NOI18N
        skor2.setOpaque(true);
        panelBiasa9.add(skor2, java.awt.BorderLayout.CENTER);

        TabSkor.addTab("Skor 5-6 (Resiko Sedang)", panelBiasa9);

        panelBiasa10.setName("panelBiasa10"); // NOI18N
        panelBiasa10.setLayout(new java.awt.BorderLayout());

        skor3.setEditable(false);
        skor3.setBackground(new java.awt.Color(255, 87, 87));
        skor3.setColumns(20);
        skor3.setRows(5);
        skor3.setText("Monitoring secara kontinyu oleh Dokter Jaga dan Perawat Senior, panggil Tim Code Blue, Respon maksimal 10 menit, informasikan dan konsultasikan ke DPJP, Edukasi keluarga pasien.");
        skor3.setName("skor3"); // NOI18N
        skor3.setOpaque(true);
        panelBiasa10.add(skor3, java.awt.BorderLayout.CENTER);

        TabSkor.addTab("Skor 7/Lebih (Resiko Tinggi)", panelBiasa10);

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

        TabSkor.addTab("Kriteria Code Blue", panelBiasa11);

        panelGlass7.add(TabSkor);
        TabSkor.setBounds(530, 90, 640, 100);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel28.setText("%");
        jLabel28.setName("jLabel28"); // NOI18N
        panelGlass7.add(jLabel28);
        jLabel28.setBounds(230, 150, 25, 23);

        kesimpulanEWS.setEditable(false);
        kesimpulanEWS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Kesimpulan EWS Sesuai Dengan Total Skor : ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11))); // NOI18N
        kesimpulanEWS.setColumns(20);
        kesimpulanEWS.setRows(5);
        kesimpulanEWS.setName("kesimpulanEWS"); // NOI18N
        panelGlass7.add(kesimpulanEWS);
        kesimpulanEWS.setBounds(530, 200, 640, 80);

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
        } else {
            hitungSkor();
            if (Sequel.menyimpantf("monitoring_ews_dewasa", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "Monitoring EWS", 24, new String[]{
                TNoRw.getText(), TruangRwt.getText(), Valid.SetTgl(Ttgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                cmbRespirasi.getSelectedItem().toString(), cmbSaturasi.getSelectedItem().toString(), cmbSuplemen.getSelectedItem().toString(), cmbTensi.getSelectedItem().toString(),
                cmbNadi.getSelectedItem().toString(), cmbKesadaran.getSelectedItem().toString(), cmbTempe.getSelectedItem().toString(),
                TtotSkor.getText(), Tgds.getText(), TskorNyeri.getText(), Turin.getText(), nip, Sequel.cariIsi("select now()"), TskorRespi.getText(), TskorSatu.getText(),
                TskorSuple.getText(), TskorTensi.getText(), TskorNadi.getText(), TskorKesadaran.getText(), TskorTempe.getText()
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
        if (tbEWS.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.queryu2tf("delete from monitoring_ews_dewasa where waktu_simpan=?", 1, new String[]{
                    tbEWS.getValueAt(tbEWS.getSelectedRow(), 28).toString()
                }) == true) {
                    TCari.setText(TNoRw.getText());
                    Valid.SetTgl(DTPCari1, Valid.SetTgl(Ttgl.getSelectedItem() + ""));
                    emptTeks();
                    BtnCariActionPerformed(null);
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                }
            } else {
                TCari.setText(TNoRw.getText());
                Valid.SetTgl(DTPCari1, Valid.SetTgl(Ttgl.getSelectedItem() + ""));
                emptTeks();
                BtnCariActionPerformed(null);
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
        } else {
            if (tbEWS.getSelectedRow() > -1) {
                if (Sequel.mengedittf("monitoring_ews_dewasa", "waktu_simpan=?", "tanggal=?, jam=?, laju_respirasi=?, saturasi=?, suplemen=?, "
                        + "tensi=?, nadi=?, kesadaran=?, temperatur=?, nilai_skor=?, gds=?, skor_nyeri=?, urin_output=?, nip_perawat=?, skor_respirasi=?, "
                        + "skor_saturasi=?, skor_suplemen=?, skor_tensi=?, skor_nadi=?, skor_kesadaran=?, skor_temperatur=?", 22, new String[]{
                            Valid.SetTgl(Ttgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                            cmbRespirasi.getSelectedItem().toString(), cmbSaturasi.getSelectedItem().toString(), cmbSuplemen.getSelectedItem().toString(), 
                            cmbTensi.getSelectedItem().toString(), cmbNadi.getSelectedItem().toString(), cmbKesadaran.getSelectedItem().toString(), 
                            cmbTempe.getSelectedItem().toString(), TtotSkor.getText(), Tgds.getText(), TskorNyeri.getText(), Turin.getText(), nip,
                            TskorRespi.getText(), TskorSatu.getText(), TskorSuple.getText(), TskorTensi.getText(), TskorNadi.getText(), TskorKesadaran.getText(), 
                            TskorTempe.getText(), tbEWS.getValueAt(tbEWS.getSelectedRow(), 28).toString()
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
            tbEWS.requestFocus();
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

    private void tbEWSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbEWSMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbEWSMouseClicked

    private void tbEWSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbEWSKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbEWSKeyPressed

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
        akses.setform("RMMonitoringEWSDewasa");
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

    private void cmbRespirasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRespirasiActionPerformed
        respirasi = 0;
        if (cmbRespirasi.getSelectedIndex() == 0) {
            respirasi = 0;
            TskorRespi.setText("");            
        } else if (cmbRespirasi.getSelectedIndex() == 1 || cmbRespirasi.getSelectedIndex() == 7) {
            respirasi = 0;
            TskorRespi.setText("C. Blue");            
        } else if (cmbRespirasi.getSelectedIndex() == 2 || cmbRespirasi.getSelectedIndex() == 6) {
            respirasi = 3;
            TskorRespi.setText("3");            
        } else if (cmbRespirasi.getSelectedIndex() == 3) {
            respirasi = 1;
            TskorRespi.setText("1");            
        } else if (cmbRespirasi.getSelectedIndex() == 4) {
            respirasi = 0;
            TskorRespi.setText("0");            
        } else if (cmbRespirasi.getSelectedIndex() == 5) {
            respirasi = 2;
            TskorRespi.setText("2");
        }
        hitungSkor();
    }//GEN-LAST:event_cmbRespirasiActionPerformed

    private void cmbSaturasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSaturasiActionPerformed
        saturasi = 0;
        if (cmbSaturasi.getSelectedIndex() == 0) {
            saturasi = 0;
            TskorSatu.setText("");            
        } else if (cmbSaturasi.getSelectedIndex() == 1) {
            saturasi = 0;
            TskorSatu.setText("0");            
        } else if (cmbSaturasi.getSelectedIndex() == 2) {
            saturasi = 1;
            TskorSatu.setText("1");            
        } else if (cmbSaturasi.getSelectedIndex() == 3) {
            saturasi = 2;
            TskorSatu.setText("2");            
        } else if (cmbSaturasi.getSelectedIndex() == 4) {
            saturasi = 3;
            TskorSatu.setText("3");            
        }
        hitungSkor();
    }//GEN-LAST:event_cmbSaturasiActionPerformed

    private void cmbSuplemenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSuplemenActionPerformed
        suplemen = 0;
        if (cmbSuplemen.getSelectedIndex() == 0) {
            suplemen = 0;
            TskorSuple.setText("");            
        } else if (cmbSuplemen.getSelectedIndex() == 1) {
            suplemen = 2;
            TskorSuple.setText("2");            
        }
        hitungSkor();
    }//GEN-LAST:event_cmbSuplemenActionPerformed

    private void cmbTensiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTensiActionPerformed
        tensi = 0;
        if (cmbTensi.getSelectedIndex() == 0) {
            tensi = 0;
            TskorTensi.setText("");            
        } else if (cmbTensi.getSelectedIndex() == 1 || cmbTensi.getSelectedIndex() == 6) {
            tensi = 3;
            TskorTensi.setText("3");            
        } else if (cmbTensi.getSelectedIndex() == 2 || cmbTensi.getSelectedIndex() == 4) {
            tensi = 1;
            TskorTensi.setText("1");            
        } else if (cmbTensi.getSelectedIndex() == 3) {
            tensi = 0;
            TskorTensi.setText("0");            
        } else if (cmbTensi.getSelectedIndex() == 5) {
            tensi = 2;
            TskorTensi.setText("2");            
        } else if (cmbTensi.getSelectedIndex() == 7) {
            tensi = 0;
            TskorTensi.setText("C. Blue");            
        }
        hitungSkor();
    }//GEN-LAST:event_cmbTensiActionPerformed

    private void cmbNadiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbNadiActionPerformed
        nadi = 0;
        if (cmbNadi.getSelectedIndex() == 0) {
            nadi = 0;
            TskorNadi.setText("");            
        } else if (cmbNadi.getSelectedIndex() == 1 || cmbNadi.getSelectedIndex() == 7) {
            nadi = 0;
            TskorNadi.setText("C. Blue");            
        } else if (cmbNadi.getSelectedIndex() == 2) {
            nadi = 3;
            TskorNadi.setText("3");            
        } else if (cmbNadi.getSelectedIndex() == 3) {
            nadi = 2;
            TskorNadi.setText("2");            
        } else if (cmbNadi.getSelectedIndex() == 4 || cmbNadi.getSelectedIndex() == 6) {
            nadi = 1;
            TskorNadi.setText("1");            
        } else if (cmbNadi.getSelectedIndex() == 5) {
            nadi = 0;
            TskorNadi.setText("0");            
        }
        hitungSkor();
    }//GEN-LAST:event_cmbNadiActionPerformed

    private void cmbKesadaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKesadaranActionPerformed
        kesadaran = 0;
        if (cmbKesadaran.getSelectedIndex() == 0) {
            kesadaran = 0;
            TskorKesadaran.setText("");            
        } else if (cmbKesadaran.getSelectedIndex() == 1) {
            kesadaran = 0;
            TskorKesadaran.setText("0");            
        } else if (cmbKesadaran.getSelectedIndex() == 2) {
            kesadaran = 3;
            TskorKesadaran.setText("3");            
        } else if (cmbKesadaran.getSelectedIndex() == 3) {
            kesadaran = 0;
            TskorKesadaran.setText("C. Blue");            
        }
        hitungSkor();
    }//GEN-LAST:event_cmbKesadaranActionPerformed

    private void cmbTempeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTempeActionPerformed
        temperatur = 0;
        if (cmbTempe.getSelectedIndex() == 0) {
            temperatur = 0;
            TskorTempe.setText("");            
        } else if (cmbTempe.getSelectedIndex() == 1) {
            temperatur = 3;
            TskorTempe.setText("3");            
        } else if (cmbTempe.getSelectedIndex() == 2 || cmbTempe.getSelectedIndex() == 4) {
            temperatur = 1;
            TskorTempe.setText("1");            
        } else if (cmbTempe.getSelectedIndex() == 3) {
            temperatur = 0;
            TskorTempe.setText("0");            
        } else if (cmbTempe.getSelectedIndex() == 5) {
            temperatur = 2;
            TskorTempe.setText("2");            
        }
        hitungSkor();
    }//GEN-LAST:event_cmbTempeActionPerformed

    private void TgdsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgdsKeyPressed
        Valid.pindah(evt, Tgds, TskorNyeri);
    }//GEN-LAST:event_TgdsKeyPressed

    private void TskorNyeriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TskorNyeriKeyPressed
        Valid.pindah(evt, TskorNyeri, Turin);
    }//GEN-LAST:event_TskorNyeriKeyPressed

    private void TurinKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TurinKeyPressed
        Valid.pindah(evt, TskorNyeri, BtnPerawat);
    }//GEN-LAST:event_TurinKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tbEWS.getSelectedRow() > -1) {
            WindowCetak.setSize(533, 70);
            WindowCetak.setLocationRelativeTo(internalFrame1);
            WindowCetak.setAlwaysOnTop(false);
            WindowCetak.setVisible(true);
            Valid.SetTgl(tglA, Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + TNoRw.getText() + "'"));
            tglB.setDate(new Date());
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih salah satu datanya terlebih dulu..!!!!");
            tbEWS.requestFocus();
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
        Valid.MyReport("rptMonitoringEWSDewasa.jasper", "report", "::[ Lembar Monitoring Early Warning Score (EWS) Dewasa ]::",
                "SELECT p.no_rkm_medis, p.nm_pasien, concat(date_format(p.tgl_lahir,'%d-%m-%Y'),' (',p.jk,')') tgllahir, concat(rp.umurdaftar,' ',rp.sttsumur) umur, m.ruang_rawat, "
                + "if(m.skor_respirasi='C. Blue' and m.laju_respirasi='<= 5',m.skor_respirasi,'') res1, "
                + "if(m.skor_respirasi='3' and m.laju_respirasi='6 - 8',m.skor_respirasi,'') res2, "
                + "if(m.skor_respirasi='1' and m.laju_respirasi='9 - 11',m.skor_respirasi,'') res3, "
                + "if(m.skor_respirasi='0' and m.laju_respirasi='12 - 20',m.skor_respirasi,'') res4, "
                + "if(m.skor_respirasi='2' and m.laju_respirasi='21 - 24',m.skor_respirasi,'') res5, "
                + "if(m.skor_respirasi='3' and m.laju_respirasi='25 - 34',m.skor_respirasi,'') res6, "
                + "if(m.skor_respirasi='C. Blue' and m.laju_respirasi='>= 35',m.skor_respirasi,'') res7, "
                + "if(m.skor_saturasi='0' and m.saturasi='>= 96',m.skor_saturasi,'') sat1, "
                + "if(m.skor_saturasi='1' and m.saturasi='94 - 95',m.skor_saturasi,'') sat2, "
                + "if(m.skor_saturasi='2' and m.saturasi='92 - 93',m.skor_saturasi,'') sat3, "
                + "if(m.skor_saturasi='3' and m.saturasi='<= 91',m.skor_saturasi,'') sat4, "
                + "if(m.skor_suplemen='2' and m.suplemen='Ya',m.skor_suplemen,'') sup, "
                + "if(m.skor_tensi='3' and m.tensi='>= 220',m.skor_tensi,'') ten1, "
                + "if(m.skor_tensi='1' and m.tensi='181 - 220',m.skor_tensi,'') ten2, "
                + "if(m.skor_tensi='0' and m.tensi='111 - 180',m.skor_tensi,'') ten3, "
                + "if(m.skor_tensi='1' and m.tensi='101 - 110',m.skor_tensi,'') ten4, "
                + "if(m.skor_tensi='2' and m.tensi='91 - 100',m.skor_tensi,'') ten5, "
                + "if(m.skor_tensi='3' and m.tensi='71 - 90',m.skor_tensi,'') ten6, "
                + "if(m.skor_tensi='C. Blue' and m.tensi='<= 70',m.skor_tensi,'') ten7, "
                + "if(m.skor_nadi='C. Blue' and m.nadi='>= 140',m.skor_nadi,'') nad1, "
                + "if(m.skor_nadi='3' and m.nadi='131 - 140',m.skor_nadi,'') nad2, "
                + "if(m.skor_nadi='2' and m.nadi='111 - 130',m.skor_nadi,'') nad3, "
                + "if(m.skor_nadi='1' and m.nadi='91 - 110',m.skor_nadi,'') nad4, "
                + "if(m.skor_nadi='0' and m.nadi='51 - 90',m.skor_nadi,'') nad5, "
                + "if(m.skor_nadi='1' and m.nadi='41 - 50',m.skor_nadi,'') nad6, "
                + "if(m.skor_nadi='C. Blue' and m.nadi='<= 40',m.skor_nadi,'') nad7, "
                + "if(m.skor_kesadaran='0' and m.kesadaran='Sadar',m.skor_kesadaran,'') kes1, "
                + "if(m.skor_kesadaran='3' and m.kesadaran='Nyeri/Verbal',m.skor_kesadaran,'') kes2, "
                + "if(m.skor_kesadaran='C. Blue' and m.kesadaran='Unrespon',m.skor_kesadaran,'') kes3, "
                + "if(m.skor_temperatur='3' and m.temperatur='<= 35',m.skor_temperatur,'') tem1, "
                + "if(m.skor_temperatur='1' and m.temperatur='35,1 - 36',m.skor_temperatur,'') tem2, "
                + "if(m.skor_temperatur='0' and m.temperatur='36,1 - 38',m.skor_temperatur,'') tem3, "
                + "if(m.skor_temperatur='1' and m.temperatur='38,1 - 39',m.skor_temperatur,'') tem4, "
                + "if(m.skor_temperatur='2' and m.temperatur='>= 39',m.skor_temperatur,'') tem5, "
                + "m.nilai_skor, m.gds, m.skor_nyeri, m.urin_output, date_format(m.tanggal,'%d/%m/%Y') tglews, time_format(m.jam,'%H:%i Wita') jam from monitoring_ews_dewasa m "
                + "inner join reg_periksa rp on rp.no_rawat=m.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis WHERE "
                + "m.no_rawat = '" + TNoRw.getText() + "' AND m.tanggal between '" + Valid.SetTgl(tglA.getSelectedItem() + "") + "' "
                + "and '" + Valid.SetTgl(tglB.getSelectedItem() + "") + "' ORDER BY m.tanggal, m.jam", param);

        TCari.setText(TNoRw.getText());
        tampil();
        emptTeks();
        BtnCloseIn6ActionPerformed(null);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnCetakActionPerformed

    private void MnHasilPemeriksaanPenunjangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHasilPemeriksaanPenunjangActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            akses.setform("RMMonitoringEWSDewasa");
            DlgHasilPenunjangMedis form = new DlgHasilPenunjangMedis(null, false);
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setData(TNoRw.getText(), TPasien.getText(), TNoRm.getText());
            form.setVisible(true);
        }
    }//GEN-LAST:event_MnHasilPemeriksaanPenunjangActionPerformed

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

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMMonitoringEWSDewasa dialog = new RMMonitoringEWSDewasa(new javax.swing.JFrame(), true);
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
    private javax.swing.JMenuItem MnHasilPemeriksaanPenunjang;
    private widget.PanelBiasa PanelAccor;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll3;
    private widget.TextBox TCari;
    private widget.TextBox TNoRm;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private javax.swing.JTabbedPane TabSkor;
    private widget.TextBox Tgds;
    private widget.TextArea Thasil;
    private widget.TextArea Tinstruksi;
    private widget.TextBox TnmPerawat;
    private widget.TextBox TruangRwt;
    private widget.TextBox TskorKesadaran;
    private widget.TextBox TskorNadi;
    private widget.TextBox TskorNyeri;
    private widget.TextBox TskorRespi;
    private widget.TextBox TskorSatu;
    private widget.TextBox TskorSuple;
    private widget.TextBox TskorTempe;
    private widget.TextBox TskorTensi;
    private widget.Tanggal Ttgl;
    private widget.TextBox TtotSkor;
    private widget.TextBox Turin;
    private javax.swing.JDialog WindowCetak;
    private widget.CekBox chkSaya;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbKesadaran;
    private widget.ComboBox cmbMnt;
    private widget.ComboBox cmbNadi;
    private widget.ComboBox cmbRespirasi;
    private widget.ComboBox cmbSaturasi;
    private widget.ComboBox cmbSuplemen;
    private widget.ComboBox cmbTempe;
    private widget.ComboBox cmbTensi;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame10;
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
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.TextArea kesimpulanEWS;
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
    private widget.Table tbEWS;
    private widget.Tanggal tglA;
    private widget.Tanggal tglB;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("select me.*, p.no_rkm_medis, p.nm_pasien, date_format(me.tanggal,'%d-%m-%Y') tgl, time_format(me.jam,'%H:%i') jamEws, "
                    + "pg.nama perawat from monitoring_ews_dewasa me inner join reg_periksa rp on rp.no_rawat=me.no_rawat "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis inner join pegawai pg on pg.nik=me.nip_perawat where "
                    + "me.tanggal between ? and ? and me.no_rawat like ? or "
                    + "me.tanggal between ? and ? and p.no_rkm_medis like ? or "
                    + "me.tanggal between ? and ? and p.nm_pasien like ? or "
                    + "me.tanggal between ? and ? and me.nip_perawat like ? or "
                    + "me.tanggal between ? and ? and pg.nama like ? or "
                    + "me.tanggal between ? and ? and me.ruang_rawat like ? or "
                    + "me.tanggal between ? and ? and me.kesadaran like ? order by me.tanggal desc, me.jam desc");
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
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("ruang_rawat"),
                        rs.getString("tgl"),
                        rs.getString("jamEws"),
                        rs.getString("laju_respirasi"),
                        rs.getString("skor_respirasi"),
                        rs.getString("saturasi"),
                        rs.getString("skor_saturasi"),
                        rs.getString("suplemen"),
                        rs.getString("skor_suplemen"),
                        rs.getString("tensi"),
                        rs.getString("skor_tensi"),
                        rs.getString("nadi"),
                        rs.getString("skor_nadi"),
                        rs.getString("kesadaran"),
                        rs.getString("skor_kesadaran"),
                        rs.getString("temperatur"),
                        rs.getString("skor_temperatur"),
                        rs.getString("nilai_skor"),
                        rs.getString("gds"),
                        rs.getString("skor_nyeri"),
                        rs.getString("urin_output"),
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
        Ttgl.setDate(new Date());
        cmbJam.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk.setSelectedIndex(0);
        nip = "-";
        TnmPerawat.setText("-");
        
        cmbRespirasi.setSelectedIndex(0);
        cmbSaturasi.setSelectedIndex(0);
        cmbSuplemen.setSelectedIndex(0);
        cmbTensi.setSelectedIndex(0);
        cmbNadi.setSelectedIndex(0);
        cmbKesadaran.setSelectedIndex(0);
        cmbTempe.setSelectedIndex(0);
        
        TskorRespi.setText("");
        TskorSatu.setText("");
        TskorSuple.setText("");
        TskorTensi.setText("");
        TskorNadi.setText("");
        TskorKesadaran.setText("");
        TskorTempe.setText("");
        
        respirasi = 0;
        saturasi = 0;
        suplemen = 0;
        tensi = 0;
        nadi = 0;
        kesadaran = 0;
        temperatur = 0;
        hitungSkor();
        Tgds.setText("");
        TskorNyeri.setText("");
        Turin.setText("");
    }

    private void getData() {
        nip = "";
        if (tbEWS.getSelectedRow() != -1) {
            TNoRw.setText(tbEWS.getValueAt(tbEWS.getSelectedRow(),0).toString());
            TNoRm.setText(tbEWS.getValueAt(tbEWS.getSelectedRow(),1).toString());
            TPasien.setText(tbEWS.getValueAt(tbEWS.getSelectedRow(),2).toString());
            TruangRwt.setText(tbEWS.getValueAt(tbEWS.getSelectedRow(),3).toString());
            Valid.SetTgl(Ttgl, tbEWS.getValueAt(tbEWS.getSelectedRow(),25).toString());
            cmbJam.setSelectedItem(tbEWS.getValueAt(tbEWS.getSelectedRow(),26).toString().substring(0, 2));
            cmbMnt.setSelectedItem(tbEWS.getValueAt(tbEWS.getSelectedRow(),26).toString().substring(3, 5));
            cmbDtk.setSelectedItem(tbEWS.getValueAt(tbEWS.getSelectedRow(),26).toString().substring(6, 8));
            cmbRespirasi.setSelectedItem(tbEWS.getValueAt(tbEWS.getSelectedRow(),6).toString());
            TskorRespi.setText(tbEWS.getValueAt(tbEWS.getSelectedRow(),7).toString());
            cmbSaturasi.setSelectedItem(tbEWS.getValueAt(tbEWS.getSelectedRow(),8).toString());
            TskorSatu.setText(tbEWS.getValueAt(tbEWS.getSelectedRow(),9).toString());
            cmbSuplemen.setSelectedItem(tbEWS.getValueAt(tbEWS.getSelectedRow(),10).toString());
            TskorSuple.setText(tbEWS.getValueAt(tbEWS.getSelectedRow(),11).toString());
            cmbTensi.setSelectedItem(tbEWS.getValueAt(tbEWS.getSelectedRow(),12).toString());
            TskorTensi.setText(tbEWS.getValueAt(tbEWS.getSelectedRow(),13).toString());
            cmbNadi.setSelectedItem(tbEWS.getValueAt(tbEWS.getSelectedRow(),14).toString());
            TskorNadi.setText(tbEWS.getValueAt(tbEWS.getSelectedRow(),15).toString());
            cmbKesadaran.setSelectedItem(tbEWS.getValueAt(tbEWS.getSelectedRow(),16).toString());
            TskorKesadaran.setText(tbEWS.getValueAt(tbEWS.getSelectedRow(),17).toString());
            cmbTempe.setSelectedItem(tbEWS.getValueAt(tbEWS.getSelectedRow(),18).toString());
            TskorTempe.setText(tbEWS.getValueAt(tbEWS.getSelectedRow(),19).toString());
            TtotSkor.setText(tbEWS.getValueAt(tbEWS.getSelectedRow(),20).toString());
            Tgds.setText(tbEWS.getValueAt(tbEWS.getSelectedRow(),21).toString());
            TskorNyeri.setText(tbEWS.getValueAt(tbEWS.getSelectedRow(),22).toString());
            Turin.setText(tbEWS.getValueAt(tbEWS.getSelectedRow(),23).toString());
            nip = tbEWS.getValueAt(tbEWS.getSelectedRow(),27).toString();
            TnmPerawat.setText(tbEWS.getValueAt(tbEWS.getSelectedRow(),24).toString());
        }
    }
    
    public void isCek(){
       BtnSimpan.setEnabled(akses.getcppt());
       BtnHapus.setEnabled(akses.getcppt());
       BtnEdit.setEnabled(akses.getcppt());    
    }
    
    private void hitungSkor() {
        total = 0;
        total = respirasi + saturasi + suplemen + tensi + nadi + kesadaran + temperatur;
        TtotSkor.setText(Valid.SetAngka2(total));
        
        if (total == 0) {
            TabSkor.setSelectedIndex(0);
            kesimpulanEWS.setText("-");
        } else if (total >= 1 && total <= 4) {
            TabSkor.setSelectedIndex(0);
            kesimpulanEWS.setText("RESIKO RINGAN :\n" + skor1.getText());
        } else if (total >= 5 && total <= 6) {
            TabSkor.setSelectedIndex(1);
            kesimpulanEWS.setText("RESIKO SEDANG :\n" + skor2.getText());
        } else if (total >= 7) {
            TabSkor.setSelectedIndex(2);
            kesimpulanEWS.setText("RESIKO TINGGI :\n" + skor3.getText());
        }
    }
    
    public void setData(String norw, String norm, String nmpasien, String ruangan) {
        TNoRw.setText(norw);
        TNoRm.setText(norm);
        TPasien.setText(nmpasien);
        TruangRwt.setText(ruangan);
        TCari.setText(norw);
        
        if (Sequel.cariInteger("select count(-1) from monitoring_ews_dewasa where no_rawat='" + norw + "'") > 0) {
            Valid.SetTgl(DTPCari1, Sequel.cariIsi("select tanggal from monitoring_ews_dewasa where "
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
}
