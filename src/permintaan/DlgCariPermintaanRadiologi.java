package permintaan;
import keuangan.Jurnal;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
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
import simrskhanza.DlgPeriksaRadiologi;

public class DlgCariPermintaanRadiologi extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode2;
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Jurnal jur = new Jurnal();
    private Connection koneksi = koneksiDB.condb();
    private Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    private int i, x = 0, pilihan =0;
    private PreparedStatement ps, ps2;
    private ResultSet rs, rs2;
    private String norm = "", kamar = "", namakamar = "", diagnosa = "", kdjenis = "", nominta = "";
    
    /** Creates new form DlgProgramStudi
     * @param parent
     * @param modal */
    public DlgCariPermintaanRadiologi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        tabMode = new DefaultTableModel(null, new Object[]{
            "No. Permintaan", "No. Rawat", "Pasien", "Permintaan", "Jam",
            "Kode Dokter", "Dokter Perujuk", "Status"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbPermintaan.setModel(tabMode);
        tbPermintaan.setPreferredScrollableViewportSize(new Dimension(800, 800));
        tbPermintaan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 8; i++) {
            TableColumn column = tbPermintaan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(90);
            } else if (i == 1) {
                column.setPreferredWidth(105);
            } else if (i == 2) {
                column.setPreferredWidth(400);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 6) {
                column.setPreferredWidth(210);
            } else if (i == 7) {
                column.setPreferredWidth(50);
            }
        }
        tbPermintaan.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode2 = new DefaultTableModel(null, new Object[]{
            "No. Permintaan", "No. Rawat", "Pasien", "Pemeriksaan", "Permintaan",
            "Jam", "Kode Dokter", "Dokter Perujuk", "kd_jenis_prw", "Status"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbItem.setModel(tabMode2);
        tbItem.setPreferredScrollableViewportSize(new Dimension(800, 800));
        tbItem.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 10; i++) {
            TableColumn column = tbItem.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(150);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(75);
            } else if (i == 6) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 7) {
                column.setPreferredWidth(210);
            } else if (i == 8) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 9) {
                column.setPreferredWidth(50);
            }
        }
        tbItem.setDefaultRenderer(Object.class, new WarnaTable());
        
        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        if (koneksiDB.cariCepat().equals("aktif")) {
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    tampil();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    tampil();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    tampil();
                }
            });
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Kd2 = new widget.TextBox();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnCetakHasilRadiologi = new javax.swing.JMenuItem();
        MnVerifBelum = new javax.swing.JMenuItem();
        MnVerifSudah = new javax.swing.JMenuItem();
        MnBarcodePermintaan = new javax.swing.JMenuItem();
        MnBarcodePermintaan1 = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        jPanel2 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        label11 = new widget.Label();
        Tgl1 = new widget.Tanggal();
        label18 = new widget.Label();
        Tgl2 = new widget.Tanggal();
        label12 = new widget.Label();
        CmbPeriksa = new widget.ComboBox();
        label10 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel10 = new widget.Label();
        LCount = new widget.Label();
        panelisi1 = new widget.panelisi();
        BtnPeriksa = new widget.Button();
        BtnHapus = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        internalFrame2 = new widget.InternalFrame();
        scrollPane1 = new widget.ScrollPane();
        tbPermintaan = new widget.Table();
        scrollPane2 = new widget.ScrollPane();
        tbItem = new widget.Table();

        Kd2.setName("Kd2"); // NOI18N
        Kd2.setPreferredSize(new java.awt.Dimension(207, 23));

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnCetakHasilRadiologi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakHasilRadiologi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakHasilRadiologi.setText("Cetak Permintaan Radiologi");
        MnCetakHasilRadiologi.setName("MnCetakHasilRadiologi"); // NOI18N
        MnCetakHasilRadiologi.setPreferredSize(new java.awt.Dimension(200, 28));
        MnCetakHasilRadiologi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakHasilRadiologiActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnCetakHasilRadiologi);

        MnVerifBelum.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnVerifBelum.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnVerifBelum.setText("Verifikasi Menjadi BELUM");
        MnVerifBelum.setName("MnVerifBelum"); // NOI18N
        MnVerifBelum.setPreferredSize(new java.awt.Dimension(200, 28));
        MnVerifBelum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnVerifBelumActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnVerifBelum);

        MnVerifSudah.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnVerifSudah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnVerifSudah.setText("Verifikasi Menjadi SUDAH");
        MnVerifSudah.setName("MnVerifSudah"); // NOI18N
        MnVerifSudah.setPreferredSize(new java.awt.Dimension(200, 28));
        MnVerifSudah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnVerifSudahActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnVerifSudah);

        MnBarcodePermintaan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBarcodePermintaan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnBarcodePermintaan.setText("Barcode No.Permintaan");
        MnBarcodePermintaan.setName("MnBarcodePermintaan"); // NOI18N
        MnBarcodePermintaan.setPreferredSize(new java.awt.Dimension(200, 28));
        MnBarcodePermintaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBarcodePermintaanActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnBarcodePermintaan);

        MnBarcodePermintaan1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBarcodePermintaan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnBarcodePermintaan1.setText("Barcode No.Permintaan 2");
        MnBarcodePermintaan1.setName("MnBarcodePermintaan1"); // NOI18N
        MnBarcodePermintaan1.setPreferredSize(new java.awt.Dimension(200, 28));
        MnBarcodePermintaan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBarcodePermintaan1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnBarcodePermintaan1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Permintaan Radiologi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setOpaque(false);
        jPanel2.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 3, 9));

        label11.setForeground(new java.awt.Color(0, 0, 0));
        label11.setText("Tanggal :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(55, 23));
        panelGlass8.add(label11);

        Tgl1.setEditable(false);
        Tgl1.setDisplayFormat("dd-MM-yyyy");
        Tgl1.setName("Tgl1"); // NOI18N
        Tgl1.setPreferredSize(new java.awt.Dimension(90, 23));
        Tgl1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tgl1KeyPressed(evt);
            }
        });
        panelGlass8.add(Tgl1);

        label18.setForeground(new java.awt.Color(0, 0, 0));
        label18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label18.setText("s.d.");
        label18.setName("label18"); // NOI18N
        label18.setPreferredSize(new java.awt.Dimension(25, 23));
        panelGlass8.add(label18);

        Tgl2.setEditable(false);
        Tgl2.setDisplayFormat("dd-MM-yyyy");
        Tgl2.setName("Tgl2"); // NOI18N
        Tgl2.setPreferredSize(new java.awt.Dimension(90, 23));
        Tgl2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tgl2KeyPressed(evt);
            }
        });
        panelGlass8.add(Tgl2);

        label12.setForeground(new java.awt.Color(0, 0, 0));
        label12.setText("Status Periksa :");
        label12.setName("label12"); // NOI18N
        label12.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass8.add(label12);

        CmbPeriksa.setForeground(new java.awt.Color(0, 0, 0));
        CmbPeriksa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Belum", "Sudah" }));
        CmbPeriksa.setName("CmbPeriksa"); // NOI18N
        CmbPeriksa.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass8.add(CmbPeriksa);

        label10.setForeground(new java.awt.Color(0, 0, 0));
        label10.setText("Key Word :");
        label10.setName("label10"); // NOI18N
        label10.setPreferredSize(new java.awt.Dimension(62, 23));
        panelGlass8.add(label10);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(160, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass8.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('5');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+5");
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
        panelGlass8.add(BtnCari);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Record :");
        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setPreferredSize(new java.awt.Dimension(55, 23));
        panelGlass8.add(jLabel10);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(100, 23));
        panelGlass8.add(LCount);

        jPanel2.add(panelGlass8, java.awt.BorderLayout.CENTER);

        panelisi1.setName("panelisi1"); // NOI18N
        panelisi1.setPreferredSize(new java.awt.Dimension(100, 56));
        panelisi1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnPeriksa.setForeground(new java.awt.Color(0, 0, 0));
        BtnPeriksa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Radioactive.png"))); // NOI18N
        BtnPeriksa.setMnemonic('I');
        BtnPeriksa.setText("Periksa Radiologi");
        BtnPeriksa.setToolTipText("Alt+I");
        BtnPeriksa.setName("BtnPeriksa"); // NOI18N
        BtnPeriksa.setPreferredSize(new java.awt.Dimension(160, 30));
        BtnPeriksa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPeriksaActionPerformed(evt);
            }
        });
        panelisi1.add(BtnPeriksa);

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
        panelisi1.add(BtnHapus);

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
        panelisi1.add(BtnAll);

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
        panelisi1.add(BtnKeluar);

        jPanel2.add(panelisi1, java.awt.BorderLayout.PAGE_END);

        internalFrame1.add(jPanel2, java.awt.BorderLayout.PAGE_END);

        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.GridLayout(1, 2));

        scrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Data Permintaan ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        scrollPane1.setName("scrollPane1"); // NOI18N
        scrollPane1.setOpaque(true);

        tbPermintaan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbPermintaan.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbPermintaan.setComponentPopupMenu(jPopupMenu1);
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
        scrollPane1.setViewportView(tbPermintaan);

        internalFrame2.add(scrollPane1);

        scrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Detail Item Permintaan ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        scrollPane2.setName("scrollPane2"); // NOI18N
        scrollPane2.setOpaque(true);

        tbItem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbItem.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbItem.setName("tbItem"); // NOI18N
        tbItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbItemMouseClicked(evt);
            }
        });
        tbItem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbItemKeyPressed(evt);
            }
        });
        scrollPane2.setViewportView(tbItem);

        internalFrame2.add(scrollPane2);

        internalFrame1.add(internalFrame2, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
/*
private void KdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKdKeyPressed
    Valid.pindah(evt,BtnCari,Nm);
}//GEN-LAST:event_TKdKeyPressed
*/

    private void Tgl1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tgl1KeyPressed
        Valid.pindah(evt,BtnKeluar,Tgl2);
    }//GEN-LAST:event_Tgl1KeyPressed

    private void Tgl2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tgl2KeyPressed
        Valid.pindah(evt, Tgl1,TCari);
    }//GEN-LAST:event_Tgl2KeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
        }
    }//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tampil();
        Valid.tabelKosong(tabMode2);
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
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnAllActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnHapus, BtnKeluar);
        }
    }//GEN-LAST:event_BtnAllKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } 
    }//GEN-LAST:event_BtnKeluarKeyPressed

private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
    if (pilihan == 0) {
        JOptionPane.showMessageDialog(null, "Silahkan klik & pilih dulu data permintaannya pada tabel...!!!!");
        tbPermintaan.requestFocus();
    } else {
        if (pilihan == 1) {
            if (tbPermintaan.getSelectedRow() != -1) {
                if (tbPermintaan.getValueAt(tbPermintaan.getSelectedRow(), 0).toString().trim().equals("")) {
                    Valid.textKosong(TCari, "No. Permintaan");
                } else if (Sequel.cariInteger("select count(-1) from permintaan_radiologi where no_rawat='" + tbPermintaan.getValueAt(tbPermintaan.getSelectedRow(), 1).toString() + "' "
                        + "and status='Sudah'") > 0) {
                    JOptionPane.showMessageDialog(null, "Data permintaan pemeriksaan radiologi sudah diperiksa...!!!!");
                } else {
                    x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                    if (x == JOptionPane.YES_OPTION) {
                        Sequel.meghapus("permintaan_radiologi", "noorder", tbPermintaan.getValueAt(tbPermintaan.getSelectedRow(), 0).toString());
                        pilihan = 0;
                        tampil();
                        tampil2(tbPermintaan.getValueAt(tbPermintaan.getSelectedRow(), 0).toString());
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih data permintaan...!!!!");
                pilihan = 0;
                tbPermintaan.requestFocus();
            }
        } else if (pilihan == 2) {
            if (tbItem.getSelectedRow() != -1) {
                if (nominta.equals("") && (kdjenis.equals(""))) {
                    JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu data permintaan periksa Radiologi dengan mengklik data pada tabel...!!!");
                    tbItem.requestFocus();
                } else if (Sequel.cariInteger("select count(-1) from permintaan_pemeriksaan_radiologi pp "
                        + "inner join permintaan_radiologi pr on pr.noorder=pp.noorder where pp.noorder='" + nominta + "' and pr.status='Sudah'") > 0) {
                    JOptionPane.showMessageDialog(null, "Data permintaan pemeriksaan radiologi sudah diperiksa...!!!!");
                } else {
                    x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                    if (x == JOptionPane.YES_OPTION) {
                        Sequel.queryu("DELETE FROM permintaan_pemeriksaan_radiologi WHERE noorder='" + nominta + "' and kd_jenis_prw='" + kdjenis + "'");
                        pilihan = 0;
                        tampil();
                        tampil2(nominta);                        
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu data permintaan periksa Radiologi dengan mengklik data pada tabel...!!!");
                pilihan = 0;
                tbItem.requestFocus();
            }
        }
    }
}//GEN-LAST:event_BtnHapusActionPerformed

private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
        BtnHapusActionPerformed(null);
    } else {
        Valid.pindah(evt, TCari, BtnAll);
    }
}//GEN-LAST:event_BtnHapusKeyPressed

private void tbPermintaanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPermintaanMouseClicked
    if (tabMode.getRowCount() != 0) {
        try {
            getData();
        } catch (java.lang.NullPointerException e) {
        }
    }
}//GEN-LAST:event_tbPermintaanMouseClicked

private void tbPermintaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPermintaanKeyPressed
    if (tabMode.getRowCount() != 0) {
        if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }
}//GEN-LAST:event_tbPermintaanKeyPressed

    private void MnCetakHasilRadiologiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakHasilRadiologiActionPerformed
        if (tbPermintaan.getSelectedRow() != -1) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            if (tbPermintaan.getValueAt(tbPermintaan.getSelectedRow(), 0).toString().trim().equals("")) {
                Valid.textKosong(TCari, "No.Permintaan");
            } else {
                Sequel.AutoComitFalse();
                Sequel.queryu("delete from temporary_permintaan_radiologi");
                try {
                    ps2 = koneksi.prepareStatement(
                            "SELECT pp.kd_jenis_prw, jp.nm_perawatan FROM permintaan_pemeriksaan_radiologi pp "
                            + "INNER JOIN jns_perawatan_radiologi jp ON pp.kd_jenis_prw = jp.kd_jenis_prw WHERE pp.noorder=?");
                    try {
                        ps2.setString(1, tbPermintaan.getValueAt(tbPermintaan.getSelectedRow(), 0).toString());
                        rs2 = ps2.executeQuery();
                        x = 1;
                        while (rs2.next()) {
                            Sequel.menyimpan("temporary_permintaan_radiologi", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", 38, new String[]{
                                "0", rs2.getString("kd_jenis_prw"), x + ".", rs2.getString("nm_perawatan"),
                                "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""
                            });
                            x++;
                        }
                    } catch (Exception e) {
                        System.out.println("Notif 2 : " + e);
                    } finally {
                        if (rs2 != null) {
                            rs2.close();
                        }
                        if (ps2 != null) {
                            ps2.close();
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Notif : " + e);
                }
                Sequel.AutoComitTrue();
                Map<String, Object> param = new HashMap<>();
                param.put("noperiksa", tbPermintaan.getValueAt(tbPermintaan.getSelectedRow(), 0).toString());
                norm = Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat=? ", tbPermintaan.getValueAt(tbPermintaan.getSelectedRow(), 1).toString());
                param.put("norm", norm);
                param.put("pekerjaan", Sequel.cariIsi("select pekerjaan from pasien where no_rkm_medis=?", norm));
                param.put("noktp", Sequel.cariIsi("select no_ktp from pasien where no_rkm_medis=?", norm));
                param.put("namapasien", Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis=? ", norm));
                param.put("jkel", Sequel.cariIsi("select if(jk='L','Laki-laki','Perempuan') jk from pasien where no_rkm_medis=? ", norm));
                param.put("umur", Sequel.cariIsi("select concat(umurdaftar,' ',sttsumur,'.') umur from reg_periksa where no_rkm_medis=?", norm));
                param.put("lahir", Sequel.cariIsi("select DATE_FORMAT(tgl_lahir,'%d-%m-%Y') from pasien where no_rkm_medis=? ", norm));
                param.put("pengirim", tbPermintaan.getValueAt(tbPermintaan.getSelectedRow(), 6).toString());
                param.put("tanggal", Valid.SetTgl3(tbPermintaan.getValueAt(tbPermintaan.getSelectedRow(), 3).toString()));
                param.put("alamat", Sequel.cariIsi("select concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat from pasien "
                        + "inner join kelurahan inner join kecamatan inner join kabupaten on pasien.kd_kel=kelurahan.kd_kel and pasien.kd_kec=kecamatan.kd_kec and "
                        + "pasien.kd_kab=kabupaten.kd_kab where no_rkm_medis=? ", norm));
                kamar = Sequel.cariIsi("select ifnull(kd_kamar,'') from kamar_inap where no_rawat=? order by tgl_masuk desc limit 1", tbPermintaan.getValueAt(tbPermintaan.getSelectedRow(), 1).toString());
                if (!kamar.equals("")) {
                    namakamar = kamar + ", " + Sequel.cariIsi("select nm_bangsal from bangsal inner join kamar on bangsal.kd_bangsal=kamar.kd_bangsal "
                            + " where kamar.kd_kamar=? ", kamar);
                    kamar = "Kamar";
                } else if (kamar.equals("")) {
                    kamar = "Poliklinik";
                    namakamar = Sequel.cariIsi("select nm_poli from poliklinik inner join reg_periksa on poliklinik.kd_poli=reg_periksa.kd_poli "
                            + "where reg_periksa.no_rawat=?", tbPermintaan.getValueAt(tbPermintaan.getSelectedRow(), 1).toString());
                }
                diagnosa = Sequel.cariIsi("select diagnosa_awal from kamar_inap where no_rawat=?", tbPermintaan.getValueAt(tbPermintaan.getSelectedRow(), 1).toString());
                param.put("kamar", kamar);
                param.put("namakamar", namakamar);
                param.put("diagnosa", diagnosa);
                param.put("jam", tbPermintaan.getValueAt(tbPermintaan.getSelectedRow(), 4).toString());
                param.put("namars", akses.getnamars());
                param.put("alamatrs", akses.getalamatrs());
                param.put("kotars", akses.getkabupatenrs());
                param.put("propinsirs", akses.getpropinsirs());
                param.put("kontakrs", akses.getkontakrs());
                param.put("emailrs", akses.getemailrs());
                param.put("logo", Sequel.cariGambar("select logo from setting"));

                Valid.MyReport("rptPermintaanRadiologi.jasper", "report", "::[ Permintaan Radiologi ]::",
                        "select no, temp1, temp2, temp3, temp4, temp5, temp6, temp7, temp8, temp9, temp10, temp11, temp12, "
                        + "temp13, temp14, temp14, temp15, temp16 from temporary_permintaan_radiologi order by no asc", param);
            }
            this.setCursor(Cursor.getDefaultCursor());
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih data permintaan...!!!!");
            TCari.requestFocus();
        }
    }//GEN-LAST:event_MnCetakHasilRadiologiActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
        Valid.tabelKosong(tabMode2);
        Tgl1.setDate(new Date());
        Tgl2.setDate(new Date());
        CmbPeriksa.setSelectedIndex(0);
    }//GEN-LAST:event_formWindowOpened

    private void tbItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbItemMouseClicked
        if (tabMode2.getRowCount() != 0) {
            try {
                getDataItem();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbItemMouseClicked

    private void tbItemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbItemKeyPressed
        if (tabMode2.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataItem();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbItemKeyPressed

    private void BtnPeriksaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPeriksaActionPerformed
        if (pilihan == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan klik & pilih dulu data permintaannya pada tabel...!!!!");
            tbPermintaan.requestFocus();
        } else {
            if (pilihan == 1) {
                if (tbPermintaan.getSelectedRow() != -1) {
                    if (tbPermintaan.getValueAt(tbPermintaan.getSelectedRow(), 0).toString().trim().equals("")) {
                        Valid.textKosong(TCari, "No.Permintaan");
                    } else {
                        if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", tbPermintaan.getValueAt(tbPermintaan.getSelectedRow(), 1).toString()) > 0) {
                            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                            DlgPeriksaRadiologi dlgro = new DlgPeriksaRadiologi(null, false);
                            dlgro.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                            dlgro.setLocationRelativeTo(internalFrame1);
                            dlgro.emptTeks();
                            dlgro.isCek();
                            dlgro.setNoRm(tbPermintaan.getValueAt(tbPermintaan.getSelectedRow(), 1).toString(), "Ranap");
                            dlgro.setOrder(tbPermintaan.getValueAt(tbPermintaan.getSelectedRow(), 0).toString());
                            dlgro.setVisible(true);
                            pilihan = 0;
                            this.setCursor(Cursor.getDefaultCursor());
                        } else {
                            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                            DlgPeriksaRadiologi dlgro = new DlgPeriksaRadiologi(null, false);
                            dlgro.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                            dlgro.setLocationRelativeTo(internalFrame1);
                            dlgro.emptTeks();
                            dlgro.isCek();
                            dlgro.setNoRm(tbPermintaan.getValueAt(tbPermintaan.getSelectedRow(), 1).toString(), "Ralan");
                            dlgro.setOrder(tbPermintaan.getValueAt(tbPermintaan.getSelectedRow(), 0).toString());
                            dlgro.setVisible(true);
                            pilihan = 0;
                            this.setCursor(Cursor.getDefaultCursor());
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih data permintaan...!!!!");
                    TCari.requestFocus();
                }
            } else if (pilihan == 2) {
                JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih Data Permintaan...!!!!");
                pilihan = 0;
                tbPermintaan.requestFocus();
            }
        }        
    }//GEN-LAST:event_BtnPeriksaActionPerformed

    private void MnBarcodePermintaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBarcodePermintaanActionPerformed
        if (tbPermintaan.getSelectedRow() != -1) {
            if (tbPermintaan.getValueAt(tbPermintaan.getSelectedRow(), 0).toString().trim().equals("")) {
                Valid.textKosong(TCari, "No.Permintaan");
            } else {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Map<String, Object> param = new HashMap<>();
                norm = Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat=? ", tbPermintaan.getValueAt(tbPermintaan.getSelectedRow(), 1).toString());
                param.put("nama", Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis=? ", norm));
                param.put("alamat", Sequel.cariIsi("select date_format(tgl_lahir,'%d/%m/%Y') from pasien where no_rkm_medis=?", norm));
                param.put("norm", norm);
                param.put("parameter", "%" + TCari.getText().trim() + "%");
                param.put("namars", akses.getnamars());
                param.put("alamatrs", akses.getalamatrs());
                param.put("kotars", akses.getkabupatenrs());
                param.put("propinsirs", akses.getpropinsirs());
                param.put("kontakrs", akses.getkontakrs());
                param.put("emailrs", akses.getemailrs());
                Valid.MyReport("rptBarcodePermintaanRadiologi.jasper", "report", "::[ Barcode No.Permintaan Radiologi ]::",
                        "select noorder from permintaan_radiologi where no_rawat='" + tbPermintaan.getValueAt(tbPermintaan.getSelectedRow(), 1).toString() + "'", param);
                this.setCursor(Cursor.getDefaultCursor());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih data permintaan...!!!!");
            TCari.requestFocus();
        }
    }//GEN-LAST:event_MnBarcodePermintaanActionPerformed

    private void MnBarcodePermintaan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBarcodePermintaan1ActionPerformed
        if (tbPermintaan.getSelectedRow() != -1) {
            if (tbPermintaan.getValueAt(tbPermintaan.getSelectedRow(), 0).toString().trim().equals("")) {
                Valid.textKosong(TCari, "No.Permintaan");
            } else {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Map<String, Object> param = new HashMap<>();
                norm = Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat=? ", tbPermintaan.getValueAt(tbPermintaan.getSelectedRow(), 1).toString());
                param.put("nama", Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis=? ", norm));
                param.put("alamat", Sequel.cariIsi("select date_format(tgl_lahir,'%d/%m/%Y') from pasien where no_rkm_medis=?", norm));
                param.put("norm", norm);
                param.put("parameter", "%" + TCari.getText().trim() + "%");
                param.put("namars", akses.getnamars());
                param.put("alamatrs", akses.getalamatrs());
                param.put("kotars", akses.getkabupatenrs());
                param.put("propinsirs", akses.getpropinsirs());
                param.put("kontakrs", akses.getkontakrs());
                param.put("emailrs", akses.getemailrs());
                Valid.MyReport("rptBarcodePermintaanRadiologi2.jasper", "report", "::[ Barcode No.Permintaan Radiologi ]::",
                        "select noorder from permintaan_radiologi where no_rawat='" + tbPermintaan.getValueAt(tbPermintaan.getSelectedRow(), 1).toString() + "'", param);
                this.setCursor(Cursor.getDefaultCursor());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih data permintaan...!!!!");
            TCari.requestFocus();
        }
    }//GEN-LAST:event_MnBarcodePermintaan1ActionPerformed

    private void MnVerifBelumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnVerifBelumActionPerformed
        if (pilihan == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan klik & pilih dulu data permintaannya pada tabel...!!!!");
            tbPermintaan.requestFocus();
        } else {
            if (pilihan == 1) {
                if (tbPermintaan.getSelectedRow() != -1) {
                    if (tbPermintaan.getValueAt(tbPermintaan.getSelectedRow(), 0).toString().trim().equals("")) {
                        Valid.textKosong(TCari, "No.Permintaan");
                    } else {
                        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                        Sequel.mengedit("permintaan_radiologi", "noorder=?", "status=?", 2, new String[]{
                            "Belum", tbPermintaan.getValueAt(tbPermintaan.getSelectedRow(), 0).toString()
                        });
                        CmbPeriksa.setSelectedIndex(0);
                        tampil();
                        tampil2(CmbPeriksa.getSelectedItem().toString());
                        pilihan = 0;
                        this.setCursor(Cursor.getDefaultCursor());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih data permintaan...!!!!");
                    TCari.requestFocus();
                }
            } else if (pilihan == 2) {
                JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih Data Permintaan...!!!!");
                pilihan = 0;
                TCari.requestFocus();
            }
        }
    }//GEN-LAST:event_MnVerifBelumActionPerformed

    private void MnVerifSudahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnVerifSudahActionPerformed
        if (pilihan == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan klik & pilih dulu data permintaannya pada tabel...!!!!");
            tbPermintaan.requestFocus();
        } else {
            if (pilihan == 1) {
                if (tbPermintaan.getSelectedRow() != -1) {
                    if (tbPermintaan.getValueAt(tbPermintaan.getSelectedRow(), 0).toString().trim().equals("")) {
                        Valid.textKosong(TCari, "No.Permintaan");
                    } else {
                        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                        Sequel.mengedit("permintaan_radiologi", "noorder=?", "status=?", 2, new String[]{
                            "Sudah", tbPermintaan.getValueAt(tbPermintaan.getSelectedRow(), 0).toString()
                        });
                        CmbPeriksa.setSelectedIndex(1);
                        tampil();
                        tampil2(CmbPeriksa.getSelectedItem().toString());
                        pilihan = 0;
                        this.setCursor(Cursor.getDefaultCursor());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih data permintaan...!!!!");
                    TCari.requestFocus();
                }
            } else if (pilihan == 2) {
                JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih Data Permintaan...!!!!");
                pilihan = 0;
                TCari.requestFocus();
            }
        }
    }//GEN-LAST:event_MnVerifSudahActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgCariPermintaanRadiologi dialog = new DlgCariPermintaanRadiologi(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCari;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPeriksa;
    private widget.ComboBox CmbPeriksa;
    private widget.TextBox Kd2;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnBarcodePermintaan;
    private javax.swing.JMenuItem MnBarcodePermintaan1;
    private javax.swing.JMenuItem MnCetakHasilRadiologi;
    private javax.swing.JMenuItem MnVerifBelum;
    private javax.swing.JMenuItem MnVerifSudah;
    public widget.TextBox TCari;
    private widget.Tanggal Tgl1;
    private widget.Tanggal Tgl2;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.Label jLabel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.Label label10;
    private widget.Label label11;
    private widget.Label label12;
    private widget.Label label18;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelisi1;
    private widget.ScrollPane scrollPane1;
    private widget.ScrollPane scrollPane2;
    private widget.Table tbItem;
    private widget.Table tbPermintaan;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("select pr.noorder, pr.no_rawat, rp.no_rkm_medis, p.nm_pasien, pr.tgl_permintaan, "
                    + "IF(pr.jam_permintaan='00:00:00','',pr.jam_permintaan) jam_permintaan, pr.dokter_perujuk, "
                    + "d.nm_dokter, pr.status FROM permintaan_radiologi pr INNER JOIN reg_periksa rp on rp.no_rawat=pr.no_rawat "
                    + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN dokter d on d.kd_dokter=pr.dokter_perujuk WHERE "
                    + "pr.status='" + CmbPeriksa.getSelectedItem().toString() + "' and pr.tgl_permintaan BETWEEN ? AND ? AND pr.noorder LIKE ? OR "
                    + "pr.status='" + CmbPeriksa.getSelectedItem().toString() + "' and pr.tgl_permintaan BETWEEN ? AND ? AND pr.no_rawat LIKE ? OR "
                    + "pr.status='" + CmbPeriksa.getSelectedItem().toString() + "' and pr.tgl_permintaan BETWEEN ? AND ? AND rp.no_rkm_medis LIKE ? OR "
                    + "pr.status='" + CmbPeriksa.getSelectedItem().toString() + "' and pr.tgl_permintaan BETWEEN ? AND ? AND p.nm_pasien LIKE ? OR "
                    + "pr.status='" + CmbPeriksa.getSelectedItem().toString() + "' and pr.tgl_permintaan BETWEEN ? AND ? AND d.nm_dokter LIKE ? "
                    + "ORDER BY pr.tgl_permintaan, pr.jam_permintaan");
            try {
                ps.setString(1, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                ps.setString(3, "%" + TCari.getText() + "%");
                ps.setString(4, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(5, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                ps.setString(6, "%" + TCari.getText() + "%");
                ps.setString(7, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(8, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                ps.setString(9, "%" + TCari.getText() + "%");
                ps.setString(10, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(11, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                ps.setString(12, "%" + TCari.getText() + "%");
                ps.setString(13, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(14, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                ps.setString(15, "%" + TCari.getText() + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString("noorder"), 
                        rs.getString("no_rawat"), 
                        rs.getString("no_rkm_medis") + " "+ rs.getString("nm_pasien"), 
                        rs.getString("tgl_permintaan"), 
                        rs.getString("jam_permintaan"),                        
                        rs.getString("dokter_perujuk"), 
                        rs.getString("nm_dokter"),
                        rs.getString("status")
                    });
                    
                    ps2 = koneksi.prepareStatement("SELECT pp.kd_jenis_prw, jp.nm_perawatan FROM permintaan_pemeriksaan_radiologi pp "
                            + "INNER JOIN jns_perawatan_radiologi jp ON pp.kd_jenis_prw = jp.kd_jenis_prw WHERE pp.noorder=?");
                    try {
                        ps2.setString(1, rs.getString("noorder"));
                        rs2 = ps2.executeQuery();
                        while (rs2.next()) {
                            tabMode.addRow(new Object[]{
                                "", "", rs2.getString("nm_perawatan"), "", "", "", "", "", "", "", ""
                            });
                        }
                    } catch (Exception e) {
                        System.out.println("Notif 2 : " + e);
                    } finally {
                        if (rs2 != null) {
                            rs2.close();
                        }
                        if (ps2 != null) {
                            ps2.close();
                        }
                    }
                }
                rs.last();
                LCount.setText("" + rs.getRow());
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
            System.out.println("Notif : " + e);
        }
    }
    
    private void tampil2(String order) {
        Valid.tabelKosong(tabMode2);
        try {
            ps = koneksi.prepareStatement("select pr.noorder, pr.no_rawat, rp.no_rkm_medis, p.nm_pasien, jp.nm_perawatan, pr.tgl_permintaan, "
                    + "IF(pr.jam_permintaan = '00:00:00', '', pr.jam_permintaan ) jam_permintaan, pr.dokter_perujuk, d.nm_dokter, pp.kd_jenis_prw, "
                    + "pr.status FROM permintaan_radiologi pr INNER JOIN reg_periksa rp on pr.no_rawat = rp.no_rawat "
                    + "INNER JOIN pasien p on rp.no_rkm_medis = p.no_rkm_medis INNER JOIN permintaan_pemeriksaan_radiologi pp on pr.noorder = pp.noorder "
                    + "INNER JOIN jns_perawatan_radiologi jp on jp.kd_jenis_prw = pp.kd_jenis_prw INNER JOIN dokter d on pr.dokter_perujuk = d.kd_dokter WHERE "
                    + "pr.status='" + CmbPeriksa.getSelectedItem().toString() + "' and pr.tgl_permintaan between ? and ? and pr.noorder='" + order + "' and pr.no_rawat like ? or "
                    + "pr.status='" + CmbPeriksa.getSelectedItem().toString() + "' and pr.tgl_permintaan between ? and ? and pr.noorder='" + order + "' and rp.no_rkm_medis like ? or "
                    + "pr.status='" + CmbPeriksa.getSelectedItem().toString() + "' and pr.tgl_permintaan between ? and ? and pr.noorder='" + order + "' and p.nm_pasien like ? or "
                    + "pr.status='" + CmbPeriksa.getSelectedItem().toString() + "' and pr.tgl_permintaan between ? and ? and pr.noorder='" + order + "' and jp.nm_perawatan like ? or "
                    + "pr.status='" + CmbPeriksa.getSelectedItem().toString() + "' and pr.tgl_permintaan between ? and ? and pr.noorder='" + order + "' and d.nm_dokter like ? "
                    + "order by pr.tgl_permintaan,pr.jam_permintaan");
            try {
                ps.setString(1, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                ps.setString(3, "%" + TCari.getText() + "%");
                ps.setString(4, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(5, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                ps.setString(6, "%" + TCari.getText() + "%");
                ps.setString(7, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(8, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                ps.setString(9, "%" + TCari.getText() + "%");
                ps.setString(10, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(11, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                ps.setString(12, "%" + TCari.getText() + "%");
                ps.setString(13, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(14, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                ps.setString(15, "%" + TCari.getText() + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode2.addRow(new String[]{
                        rs.getString("noorder"),
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis") + " " + rs.getString("nm_pasien"),
                        rs.getString("nm_perawatan"),
                        rs.getString("tgl_permintaan"),
                        rs.getString("jam_permintaan"),
                        rs.getString("dokter_perujuk"),
                        rs.getString("nm_dokter"),
                        rs.getString("kd_jenis_prw"),
                        rs.getString("status")
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
            LCount.setText("" + tabMode2.getRowCount());
        } catch (Exception e) {
            System.out.println("Notif : " + e);
        }
    }    
    
    private void getData() {
        pilihan = 0;
        Kd2.setText("");        
        if(tbPermintaan.getSelectedRow()!= -1){
            Kd2.setText(tbPermintaan.getValueAt(tbPermintaan.getSelectedRow(),0).toString());
            pilihan = 1;
            tampil2(Kd2.getText());
        }
    }
    
    public void isCek(){
        MnCetakHasilRadiologi.setEnabled(akses.getpermintaan_radiologi());
        BtnPeriksa.setEnabled(akses.getperiksa_radiologi());
        MnVerifBelum.setEnabled(akses.getperiksa_radiologi());
        MnVerifSudah.setEnabled(akses.getperiksa_radiologi());
        BtnHapus.setEnabled(akses.getpermintaan_radiologi());
    }
    
    public void setPasien(String pasien){
        TCari.setText(pasien);
    }
    
    private void getDataItem() {
        pilihan = 0;
        nominta = "";
        kdjenis = "";
        if (tbItem.getSelectedRow() != -1) {
            nominta = tbItem.getValueAt(tbItem.getSelectedRow(), 0).toString();
            kdjenis = tbItem.getValueAt(tbItem.getSelectedRow(), 8).toString();
            pilihan = 2;
        }
    }
}
