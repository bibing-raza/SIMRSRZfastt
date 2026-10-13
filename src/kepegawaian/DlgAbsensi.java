/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgPenyakit.java
 *
 * Created on May 23, 2010, 12:57:16 AM
 */

package kepegawaian;
import fungsi.WarnaTable;
import fungsi.akses;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import simrskhanza.frmUtama;

/**
 *
 * @author dosen
 */
public final class DlgAbsensi extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private validasi Valid = new validasi();
    private Connection koneksi = koneksiDB.condb();    
    private PreparedStatement ps;
    private ResultSet rs;
    private sekuel Sequel = new sekuel();
    private int x = 0, i = 0;
    private String dialog_simpan = "";
    private frmUtama formUtama;
    
    /** Creates new form DlgPenyakit
     * @param parent
     * @param modal */
    
    public DlgAbsensi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //data di tabel grid rata tengah
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        
        this.setLocation(10,2);
        setSize(656,250);

        String[] row = {"NIP/NR", "Nama Pegawai/Karyawan", "Kelompok Kerja", "Hari/Sift Kerja", "Momen Kerja", "Lokasi/Tempat Bertugas",
            "Tgl. Absen Dtg.", "Jam Absen Dtg.", "Ket. Absen Datang", "Ket. Terlambat Dtg.", "Tgl. Absen Plg.", "Jam Absen Plg.", "Ket. Absen Pulang",
            "Ket. Terlambat Plg.", "Kualitas Absen", "Jml. Jam Kerja", "Tot. Jam Kerja Periode", "Jenis Absensi", "waktu_simpan"};
        
        tabMode=new DefaultTableModel(null,row){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        
        tbAbsensi.setModel(tabMode);
        tbAbsensi.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbAbsensi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 19; i++) {
            TableColumn column = tbAbsensi.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(130);
            } else if (i == 1) {
                column.setPreferredWidth(250);
            } else if (i == 2) {
                column.setPreferredWidth(220);
            } else if (i == 3) {
                column.setPreferredWidth(100);
            } else if (i == 4) {
                column.setPreferredWidth(140);
            } else if (i == 5) {
                column.setPreferredWidth(250);
            } else if (i == 6) {
                column.setPreferredWidth(90);
            } else if (i == 7) {
                column.setPreferredWidth(90);
            } else if (i == 8) {
                column.setPreferredWidth(140);
            } else if (i == 9) {
                column.setPreferredWidth(140);
            } else if (i == 10) {
                column.setPreferredWidth(90);
            } else if (i == 11) {
                column.setPreferredWidth(90);
            } else if (i == 12) {
                column.setPreferredWidth(130);
            } else if (i == 13) {
                column.setPreferredWidth(140);
            } else if (i == 14) {
                column.setPreferredWidth(100);
            } else if (i == 15) {
                column.setPreferredWidth(100);
            } else if (i == 16) {
                column.setPreferredWidth(140);
            } else if (i == 17) {
                column.setPreferredWidth(100);
            } else if (i == 18) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbAbsensi.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbAbsensi.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        tbAbsensi.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
        tbAbsensi.getColumnModel().getColumn(10).setCellRenderer(centerRenderer);
        tbAbsensi.getColumnModel().getColumn(11).setCellRenderer(centerRenderer);
        
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
        MnHapus = new javax.swing.JMenuItem();
        MnHapusBanyak = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbAbsensi = new widget.Table();
        panelisi5 = new widget.panelisi();
        panelisi3 = new widget.panelisi();
        label11 = new widget.Label();
        Tgl1 = new widget.Tanggal();
        label18 = new widget.Label();
        Tgl2 = new widget.Tanggal();
        label9 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        label10 = new widget.Label();
        LCount = new widget.Label();
        panelisi6 = new widget.panelisi();
        BtnDownload = new widget.Button();
        BtnKeluar = new widget.Button();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnHapus.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/delete-16x16.png"))); // NOI18N
        MnHapus.setText("Hapus Absen Per Data");
        MnHapus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapus.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapus.setIconTextGap(5);
        MnHapus.setName("MnHapus"); // NOI18N
        MnHapus.setPreferredSize(new java.awt.Dimension(220, 26));
        MnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnHapus);

        MnHapusBanyak.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusBanyak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/delete-16x16.png"))); // NOI18N
        MnHapusBanyak.setText("Hapus Sesuai Tgl. Absen Datang");
        MnHapusBanyak.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapusBanyak.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapusBanyak.setIconTextGap(5);
        MnHapusBanyak.setName("MnHapusBanyak"); // NOI18N
        MnHapusBanyak.setPreferredSize(new java.awt.Dimension(220, 26));
        MnHapusBanyak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusBanyakActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnHapusBanyak);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Absensi Harian ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbAbsensi.setComponentPopupMenu(jPopupMenu1);
        tbAbsensi.setName("tbAbsensi"); // NOI18N
        tbAbsensi.getTableHeader().setReorderingAllowed(false);
        Scroll.setViewportView(tbAbsensi);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        panelisi5.setName("panelisi5"); // NOI18N
        panelisi5.setPreferredSize(new java.awt.Dimension(100, 86));
        panelisi5.setLayout(new java.awt.BorderLayout());

        panelisi3.setName("panelisi3"); // NOI18N
        panelisi3.setPreferredSize(new java.awt.Dimension(100, 43));
        panelisi3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 9));

        label11.setForeground(new java.awt.Color(0, 0, 0));
        label11.setText("Tgl. Absen Datang :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(120, 23));
        panelisi3.add(label11);

        Tgl1.setDisplayFormat("dd-MM-yyyy");
        Tgl1.setName("Tgl1"); // NOI18N
        Tgl1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelisi3.add(Tgl1);

        label18.setForeground(new java.awt.Color(0, 0, 0));
        label18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label18.setText("s.d.");
        label18.setName("label18"); // NOI18N
        label18.setPreferredSize(new java.awt.Dimension(30, 23));
        panelisi3.add(label18);

        Tgl2.setDisplayFormat("dd-MM-yyyy");
        Tgl2.setName("Tgl2"); // NOI18N
        Tgl2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelisi3.add(Tgl2);

        label9.setForeground(new java.awt.Color(0, 0, 0));
        label9.setText("Key Word :");
        label9.setName("label9"); // NOI18N
        label9.setPreferredSize(new java.awt.Dimension(68, 23));
        panelisi3.add(label9);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelisi3.add(TCari);

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
        panelisi3.add(BtnCari);

        BtnAll.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('2');
        BtnAll.setText("Semua Data");
        BtnAll.setToolTipText("2Alt+2");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(110, 23));
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
        panelisi3.add(BtnAll);

        label10.setForeground(new java.awt.Color(0, 0, 0));
        label10.setText("Record :");
        label10.setName("label10"); // NOI18N
        label10.setPreferredSize(new java.awt.Dimension(60, 23));
        panelisi3.add(label10);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelisi3.add(LCount);

        panelisi5.add(panelisi3, java.awt.BorderLayout.PAGE_START);

        panelisi6.setName("panelisi6"); // NOI18N
        panelisi6.setPreferredSize(new java.awt.Dimension(100, 43));
        panelisi6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 6, 9));

        BtnDownload.setForeground(new java.awt.Color(0, 0, 0));
        BtnDownload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/export-excel.png"))); // NOI18N
        BtnDownload.setText("Download Data");
        BtnDownload.setName("BtnDownload"); // NOI18N
        BtnDownload.setPreferredSize(new java.awt.Dimension(140, 23));
        BtnDownload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDownloadActionPerformed(evt);
            }
        });
        panelisi6.add(BtnDownload);

        BtnKeluar.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('4');
        BtnKeluar.setText("Keluar");
        BtnKeluar.setToolTipText("Alt+4");
        BtnKeluar.setName("BtnKeluar"); // NOI18N
        BtnKeluar.setPreferredSize(new java.awt.Dimension(90, 23));
        BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarActionPerformed(evt);
            }
        });
        panelisi6.add(BtnKeluar);

        panelisi5.add(panelisi6, java.awt.BorderLayout.CENTER);

        internalFrame1.add(panelisi5, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            tbAbsensi.requestFocus();
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
        tampil();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAllActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnCari, TCari);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        frmUtama.getInstance().tutupDialogDiPanelUtama(this);
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void BtnDownloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDownloadActionPerformed
        if (tabMode.getRowCount() != 0) {
            dialog_simpan = "";
            Sequel.queryu("delete from temporary1");
            Sequel.AutoComitFalse();
            for (i = 0; i < tbAbsensi.getRowCount(); i++) {
                Sequel.menyimpanIgnore("temporary1",
                        "'" + tbAbsensi.getValueAt(i, 0).toString() + "',"
                        + "'" + tbAbsensi.getValueAt(i, 1).toString() + "',"
                        + "'" + tbAbsensi.getValueAt(i, 2).toString() + "',"
                        + "'" + tbAbsensi.getValueAt(i, 3).toString() + "',"
                        + "'" + tbAbsensi.getValueAt(i, 4).toString() + "',"
                        + "'" + tbAbsensi.getValueAt(i, 5).toString() + "',"
                        + "'" + tbAbsensi.getValueAt(i, 6).toString() + "',"
                        + "'" + tbAbsensi.getValueAt(i, 7).toString() + "',"
                        + "'" + tbAbsensi.getValueAt(i, 8).toString() + "',"
                        + "'" + tbAbsensi.getValueAt(i, 9).toString() + "',"
                        + "'" + tbAbsensi.getValueAt(i, 10).toString() + "',"
                        + "'" + tbAbsensi.getValueAt(i, 11).toString() + "',"
                        + "'" + tbAbsensi.getValueAt(i, 12).toString() + "',"
                        + "'" + tbAbsensi.getValueAt(i, 13).toString() + "',"
                        + "'" + tbAbsensi.getValueAt(i, 14).toString() + "',"
                        + "'" + tbAbsensi.getValueAt(i, 15).toString() + "',"
                        + "'" + tbAbsensi.getValueAt(i, 16).toString() + "',"
                        + "'" + tbAbsensi.getValueAt(i, 17).toString() + "','','','','','','','','','','','','','','','','','','',''", "Data Absensi");
            }
            Sequel.AutoComitTrue();
            
            dialog_simpan = Valid.openDialog();
            if (Valid.MyReportToExcelBoolean("select temp1 as 'NIP/NR', temp2 as 'Nama Pegawai/Karyawan', temp3 as 'Kelompok Kerja', temp4 as 'Hari/Sift Kerja', "
                    + "temp5 as 'Momen Kerja', temp6 as 'Lokasi/Tempat Bertugas', temp7 as 'Tgl. Absen Dtg.', temp8 as 'Jam Absen Dtg.', "
                    + "temp9 as 'Ket. Absen Datang', temp10 as 'Ket. Terlambat Dtg.', temp11 as 'Tgl. Absen Plg.', temp12 as 'Jam Absen Plg.', temp13 as 'Ket. Absen Pulang', "
                    + "temp14 as 'Ket. Terlambat Plg.', temp15 as 'Kualitas Absen', temp16 as 'Jml. Jam Kerja', temp17 as 'Tot. Jam Kerja Periode', "
                    + "temp18 as 'Jenis Absensi' from temporary1", dialog_simpan) == true) {
                JOptionPane.showMessageDialog(null, "Data berhasil didownload menjadi file excel,..!!!");
            } else {
                JOptionPane.showMessageDialog(null, "Data gagal didownload menjadi file excel,..!!!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Data absensi tidak ditemukan dengan periode tgl. tersebut...");
        }
    }//GEN-LAST:event_BtnDownloadActionPerformed

    private void MnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusActionPerformed
        if (tbAbsensi.getSelectedRow() != -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                Sequel.queryu("delete from absensi where waktu_simpan='" + tbAbsensi.getValueAt(tbAbsensi.getSelectedRow(), 17).toString() + "'");
                tampil();
            } else {
                tampil();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan klik/pilih salah satu datanya terlebih dulu pada tabel...");
            tampil();
        }
    }//GEN-LAST:event_MnHapusActionPerformed

    private void MnHapusBanyakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusBanyakActionPerformed
        if (Sequel.cariInteger("select count(-1) from absensi where "
                + "tgl_datang_absen between '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "'") == 0) {
            JOptionPane.showMessageDialog(null, "Data absensi dengan periode tgl. tersebut tidak ditemukan...");
        } else {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                Sequel.queryu("delete from absensi where tgl_datang_absen between '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "'");
                tampil();
            } else {
                tampil();
            }
        }
    }//GEN-LAST:event_MnHapusBanyakActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgAbsensi dialog = new DlgAbsensi(new javax.swing.JFrame(), true);
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
    private widget.Button BtnDownload;
    private widget.Button BtnKeluar;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnHapus;
    private javax.swing.JMenuItem MnHapusBanyak;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    private widget.Tanggal Tgl1;
    private widget.Tanggal Tgl2;
    private widget.InternalFrame internalFrame1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.Label label10;
    private widget.Label label11;
    private widget.Label label18;
    private widget.Label label9;
    private widget.panelisi panelisi3;
    private widget.panelisi panelisi5;
    private widget.panelisi panelisi6;
    private widget.Table tbAbsensi;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT "
                    + "    pg.nik, "
                    + "    pg.nama, "
                    + "    ra.kelompok_kerja, "
                    + "    a.hari, "
                    + "    ra.aktivasi_momen, "
                    + "    ra.nm_gedung, "
                    + "    DATE_FORMAT(a.tgl_datang_absen,'%d-%m-%Y') AS tglDatang, "
                    + "    a.jam_datang_absen, "
                    + "    a.ket_absensi_datang, "
                    + "    a.hitungan_keterlambatan_datang AS ketLambatDatang, "
                    + "    DATE_FORMAT(a.tgl_pulang_absen,'%d-%m-%Y') AS tglPulang, "
                    + "    a.jam_pulang_absen, "
                    + "    a.ket_absensi_pulang, "
                    + "    a.hitungan_keterlambatan_pulang AS ketLambatPulang, "
                    + "    IF(a.absen_lengkap='belum','Sedang Bekerja','Lengkap') AS kualitasAbsen, "
                    + "    CASE WHEN a.absen_lengkap <> 'belum' AND a.tgl_pulang_absen IS NOT NULL AND a.jam_pulang_absen IS NOT NULL AND "
                    + "    TIMESTAMP(a.tgl_pulang_absen,a.jam_pulang_absen) >= TIMESTAMP(a.tgl_datang_absen,a.jam_datang_absen) "
                    + "        THEN CONCAT(FLOOR(TIMESTAMPDIFF(MINUTE,TIMESTAMP(a.tgl_datang_absen,a.jam_datang_absen),TIMESTAMP(a.tgl_pulang_absen,a.jam_pulang_absen)) / 60),' Jam ', "
                    + "        MOD(TIMESTAMPDIFF(MINUTE,TIMESTAMP(a.tgl_datang_absen,a.jam_datang_absen),TIMESTAMP(a.tgl_pulang_absen,a.jam_pulang_absen)),60),' Menit') ELSE '-' END AS jmlJamKerja, "
                    + "    CASE WHEN NOT EXISTS (SELECT 1 FROM absensi a2 WHERE a2.nip = a.nip AND a2.tgl_datang_absen BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                    + "          AND (a2.tgl_datang_absen < a.tgl_datang_absen OR (a2.tgl_datang_absen = a.tgl_datang_absen AND a2.jam_datang_absen < a.jam_datang_absen))) "
                    + "    THEN CONCAT(FLOOR(IFNULL(total.total_menit,0) / 60),' Jam ',MOD(IFNULL(total.total_menit,0),60),' Menit') ELSE '-' END AS totJamKerjaPeriode, a.jenis_absensi ,a.waktu_simpan "
                    + "FROM absensi a INNER JOIN pegawai pg ON pg.nik=a.nip INNER JOIN reg_absensi ra ON ra.nip=a.nip AND ra.hari=a.hari "
                    + "INNER JOIN pengaturan_jam_kerja pjk ON pjk.kelompok_kerja=ra.kelompok_kerja AND pjk.hari=ra.hari "
                    + "LEFT JOIN (SELECT nip, SUM(CASE WHEN absen_lengkap <> 'belum' AND tgl_pulang_absen IS NOT NULL AND jam_pulang_absen IS NOT NULL AND "
                    + "TIMESTAMP(tgl_pulang_absen,jam_pulang_absen) >= TIMESTAMP(tgl_datang_absen,jam_datang_absen) "
                    + "THEN TIMESTAMPDIFF(MINUTE, TIMESTAMP(tgl_datang_absen,jam_datang_absen), TIMESTAMP(tgl_pulang_absen,jam_pulang_absen)) ELSE 0 END) AS total_menit "
                    + "FROM absensi WHERE tgl_datang_absen BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' GROUP BY nip) total ON total.nip=a.nip WHERE "
                    + "a.tgl_datang_absen BETWEEN ? AND ? and a.nip like ? or "
                    + "a.tgl_datang_absen BETWEEN ? AND ? and pg.nama like ? or "
                    + "a.tgl_datang_absen BETWEEN ? AND ? and ra.kelompok_kerja like ? or "
                    + "a.tgl_datang_absen BETWEEN ? AND ? and ra.nm_gedung like ? or "
                    + "a.tgl_datang_absen BETWEEN ? AND ? and a.ket_absensi_datang like ? or "
                    + "a.tgl_datang_absen BETWEEN ? AND ? and a.ket_absensi_pulang like ? or "
                    + "a.tgl_datang_absen BETWEEN ? AND ? and IF(a.absen_lengkap='belum','Sedang Bekerja','Lengkap') like ? or "
                    + "a.tgl_datang_absen BETWEEN ? AND ? and a.jenis_absensi like ? "
                    + "ORDER BY pg.nik, a.tgl_datang_absen, a.jam_datang_absen");
            try {
                ps.setString(1, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                ps.setString(3, "%" + TCari.getText().trim() + "%");
                ps.setString(4, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(5, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                ps.setString(6, "%" + TCari.getText().trim() + "%");
                ps.setString(7, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(8, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                ps.setString(9, "%" + TCari.getText().trim() + "%");
                ps.setString(10, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(11, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                ps.setString(12, "%" + TCari.getText().trim() + "%");
                ps.setString(13, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(14, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                ps.setString(15, "%" + TCari.getText().trim() + "%");
                ps.setString(16, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(17, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                ps.setString(18, "%" + TCari.getText().trim() + "%");
                ps.setString(19, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(20, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                ps.setString(21, "%" + TCari.getText().trim() + "%");
                ps.setString(22, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(23, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                ps.setString(24, "%" + TCari.getText().trim() + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString("nik"),
                        rs.getString("nama"),
                        rs.getString("kelompok_kerja"),
                        rs.getString("hari"),
                        rs.getString("aktivasi_momen"),
                        rs.getString("nm_gedung"),
                        rs.getString("tglDatang"),
                        rs.getString("jam_datang_absen"),
                        rs.getString("ket_absensi_datang"),
                        rs.getString("ketLambatPulang"),
                        rs.getString("tglPulang"),
                        rs.getString("jam_pulang_absen"),
                        rs.getString("ket_absensi_pulang"),
                        rs.getString("ketLambatPulang"),
                        rs.getString("kualitasAbsen"),
                        rs.getString("jmlJamKerja"),
                        rs.getString("totJamKerjaPeriode"),
                        rs.getString("jenis_absensi"),
                        rs.getString("waktu_simpan")
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
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode.getRowCount());
    }
    
    public void isCek() {
        MnHapus.setEnabled(akses.getadmin());
        MnHapusBanyak.setEnabled(akses.getadmin());
    }
    
    public void awalData() {
        tampil();
    }
}