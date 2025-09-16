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
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author dosen
 */
public class DlgResepIterObat extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1, tabMode2;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps, ps1, ps2;
    private ResultSet rs, rs1, rs2;
    private int i = 0, x = 0;
    private String norawat = "", wktSimpan = "", tglAmbilObat = "", ketHari = "", tglAkanDatang = "";
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public DlgResepIterObat(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //data di tabel grid rata tengah
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);

        tabMode = new DefaultTableModel(null, new String[]{
            "Kode Iter", "No. Rawat", "No. SEP", "No. RM", "Nama Pasien", "Poliklinik", "Nama Dokter", "Pengambilan Ke", "Tgl. Ambil Obat", "Proses", "Keterangan",
            "kode_iter", "no_sep", "no_kartu", "no_rkm_medis", "no_rawat", "kunjungan", "tgl_exp_rujukan", "stts_pengambilan", "poli_ke", "tgl_ambil_obat", "selesai", 
            "keterangan", "waktu_simpan", "kd_poli", "tglResep", "Pengambilan Berikutnya", "Ket. Hari Pengambilan Berikutnya"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbIter.setModel(tabMode);
        tbIter.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbIter.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 28; i++) {
            TableColumn column = tbIter.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(120);
            } else if (i == 1) {
                column.setPreferredWidth(120);
            } else if (i == 2) {
                column.setPreferredWidth(135);
            } else if (i == 3) {
                column.setPreferredWidth(55);
            } else if (i == 4) {
                column.setPreferredWidth(250);
            } else if (i == 5) {
                column.setPreferredWidth(250);
            } else if (i == 6) {
                column.setPreferredWidth(250);
            } else if (i == 7) {
                column.setPreferredWidth(115);
            } else if (i == 8) {
                column.setPreferredWidth(90);
            } else if (i == 9) {
                column.setPreferredWidth(85);
            } else if (i == 10) {
                column.setPreferredWidth(140);
            } else if (i == 11) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 12) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 13) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
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
            } else if (i == 19) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 20) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 21) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 22) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 23) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 24) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 25) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 26) {
                column.setPreferredWidth(135);
            } else if (i == 27) {
                column.setPreferredWidth(250);
            } 
        }
        tbIter.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbIter.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbIter.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbIter.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbIter.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbIter.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
        tbIter.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
        tbIter.getColumnModel().getColumn(9).setCellRenderer(centerRenderer);
        tbIter.getColumnModel().getColumn(26).setCellRenderer(centerRenderer);
        
        tabMode1 = new DefaultTableModel(null, new String[]{
            "Tgl. Input", "Jam Input", "Nama Obat", "Status", "Id", "kddokter", "Program PRB", "Kode Resep Iter"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbCatatanResep.setModel(tabMode1);
        tbCatatanResep.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbCatatanResep.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 8; i++) {
            TableColumn column = tbCatatanResep.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(75);
            } else if (i == 1) {
                column.setPreferredWidth(75);
            } else if (i == 2) {
                column.setPreferredWidth(450);
            } else if (i == 3) {
                column.setPreferredWidth(70);
            } else if (i == 4) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 5) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 6) {
                column.setPreferredWidth(80);
            } else if (i == 7) {
                column.setPreferredWidth(125);
            } 
        }
        tbCatatanResep.setDefaultRenderer(Object.class, new WarnaTable());
        tbCatatanResep.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbCatatanResep.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbCatatanResep.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbCatatanResep.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        tbCatatanResep.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
        
        tabMode2 = new DefaultTableModel(null, new Object[]{
            "Tanggal", "Nama Obat/Alkes/BHP", "Jumlah"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbFarmasi.setModel(tabMode2);
        tbFarmasi.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbFarmasi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 3; i++) {
            TableColumn column = tbFarmasi.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(80);
            } else if (i == 1) {
                column.setPreferredWidth(550);
            } else if (i == 2) {
                column.setPreferredWidth(56);
            }
        }
        tbFarmasi.setDefaultRenderer(Object.class, new WarnaTable());
        tbFarmasi.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbFarmasi.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

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

        Popup1 = new javax.swing.JPopupMenu();
        ppCetakKodeIter = new javax.swing.JMenuItem();
        ppBatalPembambilan = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        internalFrame2 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbIter = new widget.Table();
        panelGlass9 = new widget.panelisi();
        Scroll1 = new widget.ScrollPane();
        tbCatatanResep = new widget.Table();
        Scroll2 = new widget.ScrollPane();
        tbFarmasi = new widget.Table();
        panelGlass10 = new widget.panelisi();
        jLabel23 = new widget.Label();
        tglCari1 = new widget.Tanggal();
        jLabel24 = new widget.Label();
        tglCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        BtnKeluar = new widget.Button();

        Popup1.setName("Popup1"); // NOI18N

        ppCetakKodeIter.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppCetakKodeIter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        ppCetakKodeIter.setText("Cetak Kode Iter");
        ppCetakKodeIter.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppCetakKodeIter.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppCetakKodeIter.setIconTextGap(8);
        ppCetakKodeIter.setName("ppCetakKodeIter"); // NOI18N
        ppCetakKodeIter.setPreferredSize(new java.awt.Dimension(200, 25));
        ppCetakKodeIter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppCetakKodeIterActionPerformed(evt);
            }
        });
        Popup1.add(ppCetakKodeIter);

        ppBatalPembambilan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppBatalPembambilan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/delete-16x16.png"))); // NOI18N
        ppBatalPembambilan.setText("Batalkan Pengambilan Obat");
        ppBatalPembambilan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppBatalPembambilan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppBatalPembambilan.setIconTextGap(8);
        ppBatalPembambilan.setName("ppBatalPembambilan"); // NOI18N
        ppBatalPembambilan.setPreferredSize(new java.awt.Dimension(200, 25));
        ppBatalPembambilan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppBatalPembambilanActionPerformed(evt);
            }
        });
        Popup1.add(ppBatalPembambilan);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Resep Iter Obat BPJS ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.GridLayout(1, 2));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbIter.setAutoCreateRowSorter(true);
        tbIter.setToolTipText("Silahkan klik salah satu untuk melihat resep iternya");
        tbIter.setComponentPopupMenu(Popup1);
        tbIter.setName("tbIter"); // NOI18N
        tbIter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbIterMouseClicked(evt);
            }
        });
        tbIter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbIterKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbIter);

        internalFrame2.add(Scroll);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass9.setLayout(new java.awt.GridLayout(2, 1));

        Scroll1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Catatan Resep Dokter ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbCatatanResep.setName("tbCatatanResep"); // NOI18N
        Scroll1.setViewportView(tbCatatanResep);

        panelGlass9.add(Scroll1);

        Scroll2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Pemberian Obat Dari Apotek ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);

        tbFarmasi.setName("tbFarmasi"); // NOI18N
        Scroll2.setViewportView(tbFarmasi);

        panelGlass9.add(Scroll2);

        internalFrame2.add(panelGlass9);

        internalFrame1.add(internalFrame2, java.awt.BorderLayout.CENTER);

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 47));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Tgl. Iter :");
        jLabel23.setName("jLabel23"); // NOI18N
        jLabel23.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(jLabel23);

        tglCari1.setEditable(false);
        tglCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "29-07-2025" }));
        tglCari1.setDisplayFormat("dd-MM-yyyy");
        tglCari1.setName("tglCari1"); // NOI18N
        tglCari1.setOpaque(false);
        tglCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass10.add(tglCari1);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("s.d.");
        jLabel24.setName("jLabel24"); // NOI18N
        jLabel24.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass10.add(jLabel24);

        tglCari2.setEditable(false);
        tglCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "29-07-2025" }));
        tglCari2.setDisplayFormat("dd-MM-yyyy");
        tglCari2.setName("tglCari2"); // NOI18N
        tglCari2.setOpaque(false);
        tglCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass10.add(tglCari2);

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

        BtnKeluar.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('K');
        BtnKeluar.setText("Keluar");
        BtnKeluar.setToolTipText("Alt+K");
        BtnKeluar.setName("BtnKeluar"); // NOI18N
        BtnKeluar.setPreferredSize(new java.awt.Dimension(100, 23));
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
        panelGlass10.add(BtnKeluar);

        internalFrame1.add(panelGlass10, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
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
        Valid.tabelKosong(tabMode1);
        Valid.tabelKosong(tabMode2);
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

    private void tbIterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbIterMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbIterMouseClicked

    private void tbIterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbIterKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbIterKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Valid.tabelKosong(tabMode1);
        Valid.tabelKosong(tabMode2);
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void ppCetakKodeIterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppCetakKodeIterActionPerformed
        if (tbIter.getSelectedRow() > -1) {
            if (Sequel.cariInteger("select count(-1) from catatan_resep where no_rawat='" + norawat + "' and (status='SUDAH' or status='DILUAR')") > 0) {
                if (Sequel.cariInteger("select count(-1) from iter_obat_bpjs where waktu_simpan='" + wktSimpan + "' "
                        + "and kunjungan='1' and stts_pengambilan='Selesai' and selesai='belum'") == 0) {
                    Sequel.mengedit("iter_obat_bpjs", "waktu_simpan='" + wktSimpan + "' and kunjungan='1'",
                            "tgl_ambil_obat='" + tglAmbilObat + "', stts_pengambilan='Selesai', selesai='belum'");
                }
            }

            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("kotars", akses.getkabupatenrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("poliAwal", Sequel.cariIsi("select concat(pl.nm_poli,' (',date_format(rp.tgl_registrasi,'%d/%m/%Y'),')') from iter_obat_bpjs i "
                    + "inner join reg_periksa rp on rp.no_rawat=i.no_rawat inner join poliklinik pl on pl.kd_poli=rp.kd_poli where "
                    + "i.kode_iter='" + tbIter.getValueAt(tbIter.getSelectedRow(), 0).toString() + "' and i.kunjungan='1'"));
            param.put("dokterAwal", Sequel.cariIsi("select pg.nama from iter_obat_bpjs i inner join reg_periksa rp on rp.no_rawat=i.no_rawat "
                    + "inner join pegawai pg on pg.nik=rp.kd_dokter where i.kode_iter='" + tbIter.getValueAt(tbIter.getSelectedRow(), 0).toString() + "' and i.kunjungan='1'"));
            Valid.cetakQr(tbIter.getValueAt(tbIter.getSelectedRow(), 0).toString(), Sequel.cariFolderKodeResepIter(), "QRkodeIter.jpg");
            Sequel.queryu("delete from setting_qr where judul = 'QRkodeIter'");
            Sequel.menyimpanQr("setting_qr", "'QRkodeIter'", "file QRCode Kode Resep Iter", Sequel.cariFolderPrintKodeIter());
            param.put("lokasi", Sequel.cariGambar("select gambar from setting_qr where judul = 'QRkodeIter'"));
            param.put("wktuCetak", Sequel.cariIsi("select date_format(now(),'%d/%m/%Y %H:%i Wita')"));
            param.put("tglDatang", Valid.SetTglINDONESIA(Sequel.cariIsi("SELECT DATE_ADD('" + tbIter.getValueAt(tbIter.getSelectedRow(), 25).toString() + "', INTERVAL 30 DAY)")));

            Valid.MyReport("rptKodeIterThermal.jasper", "report", "::[ Cetak Bukti Pengambilan Resep Iter Yang Ke-" + tbIter.getValueAt(tbIter.getSelectedRow(), 7).toString() + " ]::",
                    "SELECT i.*, p.nm_pasien, pl.nm_poli, CONCAT(DAY(i.tgl_exp_rujukan), ' ', CASE MONTH(i.tgl_exp_rujukan) "
                    + "WHEN 1 THEN 'Januari' "
                    + "WHEN 2 THEN 'Februari' "
                    + "WHEN 3 THEN 'Maret' "
                    + "WHEN 4 THEN 'April' "
                    + "WHEN 5 THEN 'Mei' "
                    + "WHEN 6 THEN 'Juni' "
                    + "WHEN 7 THEN 'Juli' "
                    + "WHEN 8 THEN 'Agustus' "
                    + "WHEN 9 THEN 'September' "
                    + "WHEN 10 THEN 'Oktober' "
                    + "WHEN 11 THEN 'November' "
                    + "WHEN 12 THEN 'Desember' END,' ',YEAR(i.tgl_exp_rujukan)) tglExpRujukan, "
                    + "if(i.kunjungan='1','Kedua (2)',if(i.kunjungan='2','Terakhir/Ketiga (3)','Terakhir/Sudah Selesai')) pengambilan FROM iter_obat_bpjs i "
                    + "inner join reg_periksa rp on rp.no_rawat=i.no_rawat "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "inner join poliklinik pl on pl.kd_poli=rp.kd_poli where i.waktu_simpan='" + wktSimpan + "'", param);

            tampil();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan klik/pilih dulu salah satu datanya pada tabel resep iter obat BPJS ...!!");
            tbIter.requestFocus();
            tampil();
        }
    }//GEN-LAST:event_ppCetakKodeIterActionPerformed

    private void ppBatalPembambilanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppBatalPembambilanActionPerformed
        if (tbIter.getSelectedRow() > -1) {            
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin kode iter " + tbIter.getValueAt(tbIter.getSelectedRow(), 0).toString() + " pasien atas nama " 
                    + tbIter.getValueAt(tbIter.getSelectedRow(), 4).toString() + ", utk. pengambilan yang       \n"
                    + tbIter.getValueAt(tbIter.getSelectedRow(), 16).toString().replaceAll("1", "Pertama (1)").replaceAll("2", "Kedua (2)").replaceAll("3", "Terakhir (3)")
                    + " akan dibatalkan..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.cariInteger("select count(-1) FROM detail_pemberian_obat dpo INNER JOIN databarang db ON dpo.kode_brng=db.kode_brng "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat=dpo.no_rawat WHERE "
                        + "dpo.tgl_perawatan='" + tbIter.getValueAt(tbIter.getSelectedRow(), 25).toString() + "' and "
                        + "rp.no_rkm_medis='" + tbIter.getValueAt(tbIter.getSelectedRow(), 3).toString() + "' and "
                        + "rp.kd_poli='" + tbIter.getValueAt(tbIter.getSelectedRow(), 24).toString() + "' and rp.status_lanjut='ralan'") > 0) {
                    JOptionPane.showMessageDialog(rootPane, "Maaf, kode iter tersebut tidak bisa dibatalkan, karena resep sudah dilayani farmasi...!!");
                    BtnCariActionPerformed(null);
                } else {
                    if (Sequel.cariInteger("select count(-1) from iter_obat_bpjs where waktu_simpan='" + wktSimpan + "' and kunjungan in ('2','3')") > 0) {
                        if (Sequel.queryu2tf("delete from catatan_resep where no_rawat=?", 1, new String[]{norawat}) == true) {
                            Sequel.meghapus("iter_obat_bpjs", "waktu_simpan", wktSimpan);
                            Sequel.meghapus("reg_periksa", "no_rawat", norawat);
                            BtnCariActionPerformed(null);
                        } else {
                            JOptionPane.showMessageDialog(null, "Gagal menghapus/membatalkan kode iter tersebut..!!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Pengambilan pertama (1) hanya bisa dibatalkan oleh dokter..!!");
                        BtnCariActionPerformed(null);
                    }
                }
            } else {
                BtnCariActionPerformed(null);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan klik/pilih dulu salah satu datanya pada tabel resep iter obat BPJS ...!!");
            tbIter.requestFocus();
            tampil();
        }
    }//GEN-LAST:event_ppBatalPembambilanActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgResepIterObat dialog = new DlgResepIterObat(new javax.swing.JFrame(), true);
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
    private widget.Button BtnKeluar;
    private widget.Label LCount;
    private javax.swing.JPopupMenu Popup1;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    public widget.TextBox TCari;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.Label jLabel23;
    private widget.Label jLabel24;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass9;
    private javax.swing.JMenuItem ppBatalPembambilan;
    private javax.swing.JMenuItem ppCetakKodeIter;
    private widget.Table tbCatatanResep;
    private widget.Table tbFarmasi;
    private widget.Table tbIter;
    private widget.Tanggal tglCari1;
    private widget.Tanggal tglCari2;
    // End of variables declaration//GEN-END:variables

    public void tampil() {     
        ketHari = "";
        tglAkanDatang = "";
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT iob.*, p.no_rkm_medis, p.nm_pasien, pl.nm_poli, d.nm_dokter, "
                    + "if(iob.tgl_ambil_obat='0000-00-00','-',DATE_FORMAT(iob.tgl_ambil_obat,'%d-%m-%Y')) tglAmbilObat, "
                    + "rp.kd_poli, date(iob.waktu_simpan) tglResep FROM iter_obat_bpjs iob "
                    + "inner join reg_periksa rp on rp.no_rawat =iob.no_rawat inner join pasien p on p.no_rkm_medis =rp.no_rkm_medis "
                    + "inner join dokter d on d.kd_dokter =rp.kd_dokter inner join poliklinik pl on pl.kd_poli =rp.kd_poli where "
                    + "date(waktu_simpan) between ? and ? and iob.kode_iter like ? or "
                    + "date(waktu_simpan) between ? and ? and iob.no_sep like ? or "
                    + "date(waktu_simpan) between ? and ? and iob.no_kartu like ? or "
                    + "date(waktu_simpan) between ? and ? and iob.no_rkm_medis like ? or "
                    + "date(waktu_simpan) between ? and ? and iob.no_rawat like ? or "
                    + "date(waktu_simpan) between ? and ? and p.nm_pasien like ? or "
                    + "date(waktu_simpan) between ? and ? and pl.nm_poli like ? or "
                    + "date(waktu_simpan) between ? and ? and d.nm_dokter like ? or "
                    + "date(waktu_simpan) between ? and ? and iob.kunjungan like ? or "
                    + "date(waktu_simpan) between ? and ? and iob.stts_pengambilan like ? or "
                    + "date(waktu_simpan) between ? and ? and iob.selesai like ? or "
                    + "date(waktu_simpan) between ? and ? and iob.keterangan like ? order by iob.waktu_simpan");
            try {
                ps.setString(1, Valid.SetTgl(tglCari1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(tglCari2.getSelectedItem() + ""));
                ps.setString(3, "%" + TCari.getText().trim() + "%");
                ps.setString(4, Valid.SetTgl(tglCari1.getSelectedItem() + ""));
                ps.setString(5, Valid.SetTgl(tglCari2.getSelectedItem() + ""));
                ps.setString(6, "%" + TCari.getText().trim() + "%");
                ps.setString(7, Valid.SetTgl(tglCari1.getSelectedItem() + ""));
                ps.setString(8, Valid.SetTgl(tglCari2.getSelectedItem() + ""));
                ps.setString(9, "%" + TCari.getText().trim() + "%");
                ps.setString(10, Valid.SetTgl(tglCari1.getSelectedItem() + ""));
                ps.setString(11, Valid.SetTgl(tglCari2.getSelectedItem() + ""));
                ps.setString(12, "%" + TCari.getText().trim() + "%");
                ps.setString(13, Valid.SetTgl(tglCari1.getSelectedItem() + ""));
                ps.setString(14, Valid.SetTgl(tglCari2.getSelectedItem() + ""));
                ps.setString(15, "%" + TCari.getText().trim() + "%");
                ps.setString(16, Valid.SetTgl(tglCari1.getSelectedItem() + ""));
                ps.setString(17, Valid.SetTgl(tglCari2.getSelectedItem() + ""));
                ps.setString(18, "%" + TCari.getText().trim() + "%");
                ps.setString(19, Valid.SetTgl(tglCari1.getSelectedItem() + ""));
                ps.setString(20, Valid.SetTgl(tglCari2.getSelectedItem() + ""));
                ps.setString(21, "%" + TCari.getText().trim() + "%");
                ps.setString(22, Valid.SetTgl(tglCari1.getSelectedItem() + ""));
                ps.setString(23, Valid.SetTgl(tglCari2.getSelectedItem() + ""));
                ps.setString(24, "%" + TCari.getText().trim() + "%");
                ps.setString(25, Valid.SetTgl(tglCari1.getSelectedItem() + ""));
                ps.setString(26, Valid.SetTgl(tglCari2.getSelectedItem() + ""));
                ps.setString(27, "%" + TCari.getText().trim() + "%");
                ps.setString(28, Valid.SetTgl(tglCari1.getSelectedItem() + ""));
                ps.setString(29, Valid.SetTgl(tglCari2.getSelectedItem() + ""));
                ps.setString(30, "%" + TCari.getText().trim() + "%");
                ps.setString(31, Valid.SetTgl(tglCari1.getSelectedItem() + ""));
                ps.setString(32, Valid.SetTgl(tglCari2.getSelectedItem() + ""));
                ps.setString(33, "%" + TCari.getText().trim() + "%");
                ps.setString(34, Valid.SetTgl(tglCari1.getSelectedItem() + ""));
                ps.setString(35, Valid.SetTgl(tglCari2.getSelectedItem() + ""));
                ps.setString(36, "%" + TCari.getText().trim() + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    tglAkanDatang = Sequel.cariIsi("SELECT DATE_ADD('" + rs.getString("tglResep") + "', INTERVAL 30 DAY)");
                    if (Sequel.cariIsi("select ifnull(tgl_libur,'') from hari_libur where tgl_libur='" + tglAkanDatang + "'").equals("")) {
                        if (tglAkanDatang.equals("Sunday")) {
                            ketHari = "bertepatan dengan hari MINGGU";
                        } else {
                            ketHari = "normal hari kerja seperti biasa";
                        }
                    } else {
                        ketHari = Sequel.cariIsi("select keterangan from hari_libur where tgl_libur='" + tglAkanDatang + "'");
                    }
                    
                    tabMode.addRow(new String[]{
                        rs.getString("kode_iter"),
                        rs.getString("no_rawat"),
                        rs.getString("no_sep"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("nm_poli"),
                        rs.getString("nm_dokter"),
                        rs.getString("kunjungan") + " (" + rs.getString("stts_pengambilan") + ")",
                        rs.getString("tglAmbilObat"),
                        rs.getString("selesai").replaceAll("belum", "Belum Selesai").replaceAll("sudah", "Selesai"),
                        rs.getString("keterangan"),                        
                        rs.getString("kode_iter"),
                        rs.getString("no_sep"),
                        rs.getString("no_kartu"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("no_rawat"),
                        rs.getString("kunjungan"),
                        rs.getString("tgl_exp_rujukan"),
                        rs.getString("stts_pengambilan"),
                        rs.getString("poli_ke"),
                        rs.getString("tgl_ambil_obat"),
                        rs.getString("selesai"),
                        rs.getString("keterangan"),
                        rs.getString("waktu_simpan"),                        
                        rs.getString("kd_poli"),
                        rs.getString("tglResep"),
                        Sequel.cariIsi("SELECT date_format(DATE_ADD('" + rs.getString("tglResep") + "', INTERVAL 30 DAY),'%d-%m-%Y')"),
                        ketHari
                    });
                }                
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
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

    private void tampilCatatan(String norwt) {
        Valid.tabelKosong(tabMode1);
        try {
            ps1 = koneksi.prepareStatement("select date_format(c.tgl_perawatan,'%d-%m-%Y') tgl_perawatan, c.jam_perawatan, c.nama_obat, c.status, "
                    + "c.noID, c.kd_dokter, if(prb.saran is null,'TIDAK','YA') programPrb, ifnull(i.kode_iter,'-') kodeIter from catatan_resep c "
                    + "inner join reg_periksa r on r.no_rawat = c.no_rawat inner join dokter d on d.kd_dokter = c.kd_dokter "
                    + "inner join iter_obat_bpjs i on i.no_rawat=c.no_rawat left join bridging_srb_bpjs prb on prb.no_srb=c.no_rawat and prb.keterangan=c.noID "
                    + "where c.no_rawat='" + norwt + "' order by c.noId");
            try {
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    tabMode1.addRow(new String[]{
                        rs1.getString("tgl_perawatan"),
                        rs1.getString("jam_perawatan"),
                        rs1.getString("nama_obat"),
                        rs1.getString("status"),
                        rs1.getString("noID"),
                        rs1.getString("kd_dokter"),
                        rs1.getString("programPrb"),
                        rs1.getString("kodeIter")
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
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilApotek(String tgl, String norm, String kdPoli) {
        Valid.tabelKosong(tabMode2);
        try {
            ps2 = koneksi.prepareStatement("SELECT DATE_FORMAT(dpo.tgl_perawatan,'%d-%m-%Y') tanggal, db.nama_brng,"
                    + "CONCAT(dpo.jml ,' ',LOWER(db.kode_sat)) jlh FROM detail_pemberian_obat dpo "
                    + "INNER JOIN databarang db ON dpo.kode_brng=db.kode_brng INNER JOIN reg_periksa rp ON rp.no_rawat=dpo.no_rawat WHERE "
                    + "dpo.tgl_perawatan='" + tgl + "' and rp.no_rkm_medis='" + norm + "' and rp.kd_poli='" + kdPoli + "' and rp.status_lanjut='ralan'");
            try {
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    tabMode2.addRow(new String[]{
                        rs2.getString("tanggal"),
                        rs2.getString("nama_brng"),
                        rs2.getString("jlh")
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

    private void getData() {
        norawat = "";
        wktSimpan = "";
        tglAmbilObat = "";
        
        if (tbIter.getSelectedRow() != -1) {
            norawat = tbIter.getValueAt(tbIter.getSelectedRow(), 1).toString();
            wktSimpan = tbIter.getValueAt(tbIter.getSelectedRow(), 23).toString();
            tglAmbilObat = tbIter.getValueAt(tbIter.getSelectedRow(), 20).toString();
            
            Scroll1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Catatan Resep Dokter (" + tbIter.getValueAt(tbIter.getSelectedRow(), 3).toString() + " - " + tbIter.getValueAt(tbIter.getSelectedRow(), 4).toString() + ") ",
                    javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                    javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
            Scroll2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Pemberian Obat Dari Apotek (" + tbIter.getValueAt(tbIter.getSelectedRow(), 3).toString() + " - " + tbIter.getValueAt(tbIter.getSelectedRow(), 4).toString() + ") ",
                    javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                    javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));

            if (Sequel.cariInteger("select count(-1) from iter_obat_bpjs where no_rawat='" + norawat + "' and stts_pengambilan='Proses pelayanan'") > 0) {
                tampilCatatan(norawat);
                Valid.tabelKosong(tabMode2);
            } else {
                tampilCatatan(norawat);
                tampilApotek(tbIter.getValueAt(tbIter.getSelectedRow(), 25).toString(),
                        tbIter.getValueAt(tbIter.getSelectedRow(), 3).toString(),
                        tbIter.getValueAt(tbIter.getSelectedRow(), 24).toString());
            }
        }
    }
}
