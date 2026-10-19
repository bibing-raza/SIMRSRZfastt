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
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import simrskhanza.frmUtama;

/**
 *
 * @author dosen
 */
public final class DlgAbsensi extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1, tabMode2;
    private validasi Valid = new validasi();
    private Connection koneksi = koneksiDB.condb();    
    private PreparedStatement ps, ps1, ps2;
    private ResultSet rs, rs1, rs2;
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
            "Tgl. Kerja/Absen Dtg.", "Jam Absen Dtg.", "Ket. Absen Datang", "Ket. Terlambat Dtg.", "Tgl. Absen Plg.", "Jam Absen Plg.", "Ket. Absen Pulang",
            "Ket. Terlambat Plg.", "Kualitas Absen", "Jenis Absensi", "waktu_simpan"};
        
        tabMode=new DefaultTableModel(null,row){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        
        tbAbsensi.setModel(tabMode);
        tbAbsensi.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbAbsensi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 17; i++) {
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
                column.setPreferredWidth(120);
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
        
        tabMode1 = new DefaultTableModel(null, new String[]{
            "NIP/NR", "Nama Pegawai/Karyawan", "Tgl. Periode Awal", "Tgl. Periode Akhir", "Jml. Hari Absen", "Total Jam Kerja", "Keterangan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbJamKerja.setModel(tabMode1);
        tbJamKerja.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbJamKerja.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 7; i++) {
            TableColumn column = tbJamKerja.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(130);
            } else if (i == 1) {
                column.setPreferredWidth(250);
            } else if (i == 2) {
                column.setPreferredWidth(110);
            } else if (i == 3) {
                column.setPreferredWidth(110);
            } else if (i == 4) {
                column.setPreferredWidth(100);
            } else if (i == 5) {
                column.setPreferredWidth(110);
            } else if (i == 6) {
                column.setPreferredWidth(220);
            }
        }
        tbJamKerja.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbJamKerja.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbJamKerja.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbJamKerja.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        
        tabMode2 = new DefaultTableModel(null, new Object[]{"Cek", "Lokasi/Tempat Bertugas"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        
        tbLokasi.setModel(tabMode2);
        tbLokasi.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbLokasi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 2; i++) {
            TableColumn column = tbLokasi.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(35);
            } else if (i == 1) {
                column.setPreferredWidth(350);
            } 
        }
        tbLokasi.setDefaultRenderer(Object.class, new WarnaTable());
        
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
        TabAbsensi = new javax.swing.JTabbedPane();
        internalFrame3 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbAbsensi = new widget.Table();
        Scroll2 = new widget.ScrollPane();
        tbLokasi = new widget.Table();
        Scroll1 = new widget.ScrollPane();
        tbJamKerja = new widget.Table();
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

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Absensi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        TabAbsensi.setBackground(new java.awt.Color(254, 255, 254));
        TabAbsensi.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        TabAbsensi.setName("TabAbsensi"); // NOI18N
        TabAbsensi.setPreferredSize(new java.awt.Dimension(0, 2000));
        TabAbsensi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabAbsensiMouseClicked(evt);
            }
        });

        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame3.setLayout(new java.awt.BorderLayout());

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbAbsensi.setComponentPopupMenu(jPopupMenu1);
        tbAbsensi.setName("tbAbsensi"); // NOI18N
        tbAbsensi.getTableHeader().setReorderingAllowed(false);
        Scroll.setViewportView(tbAbsensi);

        internalFrame3.add(Scroll, java.awt.BorderLayout.CENTER);

        Scroll2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Filter Data Berdasarkan ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13))); // NOI18N
        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);
        Scroll2.setPreferredSize(new java.awt.Dimension(452, 380));

        tbLokasi.setComponentPopupMenu(jPopupMenu1);
        tbLokasi.setName("tbLokasi"); // NOI18N
        tbLokasi.getTableHeader().setReorderingAllowed(false);
        Scroll2.setViewportView(tbLokasi);

        internalFrame3.add(Scroll2, java.awt.BorderLayout.EAST);

        TabAbsensi.addTab("Rekap Absensi Karyawan", internalFrame3);

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbJamKerja.setName("tbJamKerja"); // NOI18N
        tbJamKerja.getTableHeader().setReorderingAllowed(false);
        Scroll1.setViewportView(tbJamKerja);

        TabAbsensi.addTab("Rekap Jam Kerja Absensi", Scroll1);

        internalFrame1.add(TabAbsensi, java.awt.BorderLayout.CENTER);

        panelisi5.setName("panelisi5"); // NOI18N
        panelisi5.setPreferredSize(new java.awt.Dimension(100, 86));
        panelisi5.setLayout(new java.awt.BorderLayout());

        panelisi3.setName("panelisi3"); // NOI18N
        panelisi3.setPreferredSize(new java.awt.Dimension(100, 43));
        panelisi3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 9));

        label11.setForeground(new java.awt.Color(0, 0, 0));
        label11.setText("Tgl. Kerja/Absen Datang :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(140, 23));
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
        TCari.setPreferredSize(new java.awt.Dimension(200, 23));
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
        if (TabAbsensi.getSelectedIndex() == 0) {
            tampil();
        } else if (TabAbsensi.getSelectedIndex() == 1) {
            tampilJamKerja();
        }
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
        tampilLokasi();
        BtnCariActionPerformed(null);
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            TCari.setText("");
            BtnAllActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnCari, TCari);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        frmUtama.getInstance().tutupDialogDiPanelUtama(this);
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        TabAbsensi.setSelectedIndex(0);
        tampil();
        tampilLokasi();
    }//GEN-LAST:event_formWindowOpened

    private void BtnDownloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDownloadActionPerformed
        if (TabAbsensi.getSelectedIndex() == 0) {
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
                            + "'" + tbAbsensi.getValueAt(i, 15).toString() + "','','','','','','','','','','','','','','','','','','','','',''", "Data Absensi");
                }
                Sequel.AutoComitTrue();

                dialog_simpan = Valid.openDialog();
                if (Valid.MyReportToExcelBoolean("select temp1 as 'NIP/NR', temp2 as 'Nama Pegawai/Karyawan', temp3 as 'Kelompok Kerja', temp4 as 'Hari/Sift Kerja', "
                        + "temp5 as 'Momen Kerja', temp6 as 'Lokasi/Tempat Bertugas', temp7 as 'Tgl. Kerja/Absen Dtg.', temp8 as 'Jam Absen Dtg.', "
                        + "temp9 as 'Ket. Absen Datang', temp10 as 'Ket. Terlambat Dtg.', temp11 as 'Tgl. Absen Plg.', temp12 as 'Jam Absen Plg.', temp13 as 'Ket. Absen Pulang', "
                        + "temp14 as 'Ket. Terlambat Plg.', temp15 as 'Kualitas Absen', temp16 as 'Jenis Absensi' from temporary1", dialog_simpan) == true) {
                    JOptionPane.showMessageDialog(null, "Data berhasil didownload menjadi file excel,..!!!");
                    tampil();
                } else {
                    JOptionPane.showMessageDialog(null, "Data gagal didownload menjadi file excel,..!!!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Data absensi tidak ditemukan dengan periode tgl. tersebut...");
            }
        } else {
            if (tabMode1.getRowCount() != 0) {
                dialog_simpan = "";
                Sequel.queryu("delete from temporary1");
                Sequel.AutoComitFalse();
                for (i = 0; i < tbJamKerja.getRowCount(); i++) {
                    Sequel.menyimpanIgnore("temporary1",
                            "'" + tbJamKerja.getValueAt(i, 0).toString() + "',"
                            + "'" + tbJamKerja.getValueAt(i, 1).toString() + "',"
                            + "'" + tbJamKerja.getValueAt(i, 2).toString() + "',"
                            + "'" + tbJamKerja.getValueAt(i, 3).toString() + "',"
                            + "'" + tbJamKerja.getValueAt(i, 4).toString() + "',"
                            + "'" + tbJamKerja.getValueAt(i, 5).toString() + "',"
                            + "'" + tbJamKerja.getValueAt(i, 6).toString() + "','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Data Jam Kerja");
                }
                Sequel.AutoComitTrue();
                
                dialog_simpan = Valid.openDialog();
                if (Valid.MyReportToExcelBoolean("select temp1 as 'NIP/NR', temp2 as 'Nama Pegawai/Karyawan', temp3 as 'Tgl. Periode Awal', temp4 as 'Tgl. Periode Akhir', "
                        + "temp5 as 'Jml. Hari Absen', temp6 as 'Total Jam Kerja', temp7 as 'Keterangan' from temporary1", dialog_simpan) == true) {
                    JOptionPane.showMessageDialog(null, "Data berhasil didownload menjadi file excel,..!!!");
                    tampil();
                } else {
                    JOptionPane.showMessageDialog(null, "Data gagal didownload menjadi file excel,..!!!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Data jam kerja tidak ditemukan dengan periode tgl. tersebut...");
            }
        }
    }//GEN-LAST:event_BtnDownloadActionPerformed

    private void MnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusActionPerformed
        if (tbAbsensi.getSelectedRow() != -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                Sequel.queryu("delete from absensi where waktu_simpan='" + tbAbsensi.getValueAt(tbAbsensi.getSelectedRow(), 16).toString() + "'");
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

    private void TabAbsensiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabAbsensiMouseClicked
        if (TabAbsensi.getSelectedIndex() == 0) {
            tampilLokasi();
            tampil();
        } else if (TabAbsensi.getSelectedIndex() == 1) {
            tampilJamKerja();
        }
    }//GEN-LAST:event_TabAbsensiMouseClicked

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
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    private widget.TextBox TCari;
    private javax.swing.JTabbedPane TabAbsensi;
    private widget.Tanggal Tgl1;
    private widget.Tanggal Tgl2;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.Label label10;
    private widget.Label label11;
    private widget.Label label18;
    private widget.Label label9;
    private widget.panelisi panelisi3;
    private widget.panelisi panelisi5;
    private widget.panelisi panelisi6;
    private widget.Table tbAbsensi;
    private widget.Table tbJamKerja;
    private widget.Table tbLokasi;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            String lokasi = "", lokasiDipilih = "";
            //cek contengnya
            x = 0;
            for (i = 0; i < tbLokasi.getRowCount(); i++) {
                if (tbLokasi.getValueAt(i, 0).toString().equals("true")) {
                    x++;
                }
            }

            if (x > 0) {
                for (i = 0; i < tbLokasi.getRowCount(); i++) {
                    if (tbLokasi.getValueAt(i, 0).toString().equals("true")) {
                        if (lokasi.equals("")) {
                            lokasi = "'" + tbLokasi.getValueAt(i, 1).toString() + "'";
                        } else {
                            lokasi = lokasi + ",'" + tbLokasi.getValueAt(i, 1).toString() + "'";
                        }
                    }
                }
                lokasiDipilih = "dk.nm_gedung IN (" + lokasi + ") AND";
            } else {
                lokasiDipilih = "";
            }
            
            ps = koneksi.prepareStatement("SELECT "
                    + "    dk.nip AS nik, "
                    + "    dk.nama, "
                    + "    IFNULL(dk.kelompok_kerja, '-') AS kelompok_kerja, "
                    + "    IFNULL(a.hari, '-') AS hari, "
                    + "    IFNULL(dk.aktivasi_momen, '-') AS aktivasi_momen, "
                    + "    IFNULL(dk.nm_gedung, '-') AS nm_gedung, "
                    + "    DATE_FORMAT(k.tanggal, '%d-%m-%Y') AS tglDatang, "
                    + "    IFNULL(CAST(a.jam_datang_absen AS CHAR), '-') AS jam_datang_absen, "
                    + "    IFNULL(a.ket_absensi_datang, '-') AS ket_absensi_datang, "
                    + "    IFNULL(CAST(a.hitungan_keterlambatan_datang AS CHAR), '-') AS ketLambatDatang, "
                    + "    IFNULL(DATE_FORMAT(a.tgl_pulang_absen, '%d-%m-%Y'), '-') AS tglPulang, "
                    + "    IFNULL(CAST(a.jam_pulang_absen AS CHAR), '-') AS jam_pulang_absen, "
                    + "    IFNULL(a.ket_absensi_pulang, '-') AS ket_absensi_pulang, "
                    + "    IFNULL(CAST(a.hitungan_keterlambatan_pulang AS CHAR), '-') AS ketLambatPulang, "
                    + "    CASE "
                    + "        WHEN a.nip IS NULL THEN '-' "
                    + "        WHEN a.absen_lengkap = 'belum' THEN 'Sedang Bekerja' "
                    + "        ELSE 'Lengkap' END AS kualitasAbsen, "
                    + "    IFNULL(a.jenis_absensi, '-') AS jenis_absensi, "
                    + "    IFNULL(CAST(a.waktu_simpan AS CHAR), '-') AS waktu_simpan "
                    + "FROM (SELECT ra.nip, pg.nama, MAX(ra.kelompok_kerja) AS kelompok_kerja, MAX(ra.aktivasi_momen) AS aktivasi_momen, MAX(ra.nm_gedung) AS nm_gedung FROM reg_absensi ra "
                    + "    INNER JOIN pegawai pg ON pg.nik = ra.nip GROUP BY ra.nip, pg.nama) dk "
                    + "CROSS JOIN (SELECT DATE_ADD(?, INTERVAL angka.n DAY) AS tanggal "
                    + "    FROM (SELECT satu.n + puluh.n * 10 + ratus.n * 100 AS n "
                    + "        FROM (SELECT 0 AS n UNION ALL SELECT 1 UNION ALL SELECT 2 "
                    + "              UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 "
                    + "              UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 "
                    + "              UNION ALL SELECT 9) satu "
                    + "        CROSS JOIN (SELECT 0 AS n UNION ALL SELECT 1 UNION ALL SELECT 2 "
                    + "                    UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 "
                    + "                    UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 "
                    + "                    UNION ALL SELECT 9) puluh "
                    + "        CROSS JOIN (SELECT 0 AS n UNION ALL SELECT 1 UNION ALL SELECT 2 "
                    + "                    UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 "
                    + "                    UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 "
                    + "                    UNION ALL SELECT 9) ratus "
                    + "    ) angka WHERE angka.n <= DATEDIFF(?, ?)) k "
                    + "LEFT JOIN absensi a ON a.nip = dk.nip AND a.tgl_datang_absen = k.tanggal "
                    + "WHERE k.tanggal BETWEEN ? AND ? AND " + lokasiDipilih + " ( "
                    + "        dk.nip LIKE ? "
                    + "        OR dk.nama LIKE ? "
                    + "        OR dk.kelompok_kerja LIKE ? "
                    + "        OR a.ket_absensi_datang LIKE ? "
                    + "        OR a.ket_absensi_pulang LIKE ? "
                    + "        OR (CASE WHEN a.nip IS NULL THEN '-' WHEN a.absen_lengkap = 'belum' THEN 'Sedang Bekerja' ELSE 'Lengkap' END) LIKE ? "
                    + "        OR a.jenis_absensi LIKE ?) ORDER BY dk.nip, k.tanggal, a.jam_datang_absen");
            try {
                String kataCari = "%" + TCari.getText().trim() + "%";
                String tglAwal = Valid.SetTgl(Tgl1.getSelectedItem() + "");
                String tglAkhir = Valid.SetTgl(Tgl2.getSelectedItem() + "");

                ps.setString(1, tglAwal);   // DATE_ADD tanggal awal
                ps.setString(2, tglAkhir);  // DATEDIFF tanggal akhir
                ps.setString(3, tglAwal);   // DATEDIFF tanggal awal
                ps.setString(4, tglAwal);   // BETWEEN tanggal awal
                ps.setString(5, tglAkhir);  // BETWEEN tanggal akhir
                ps.setString(6, kataCari);  // NIP
                ps.setString(7, kataCari);  // Nama
                ps.setString(8, kataCari);  // Kelompok kerja    
                ps.setString(9, kataCari); // Keterangan datang
                ps.setString(10, kataCari); // Keterangan pulang
                ps.setString(11, kataCari); // Kualitas absensi
                ps.setString(12, kataCari); // Jenis absensi
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

    private void tampilJamKerja() {
        Valid.tabelKosong(tabMode1);
        try {
            ps1 = koneksi.prepareStatement("SELECT "
                    + "    pg.nik, "
                    + "    pg.nama, "
                    + "    DATE_FORMAT('" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "', '%d-%m-%Y') AS periodeAwal, "
                    + "    DATE_FORMAT('" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "', '%d-%m-%Y') AS periodeAkhir, "
                    + "    IFNULL(total.jumlah_hari, 0) AS jumlahHariAbsen, "
                    + "    CONCAT(FLOOR(IFNULL(total.total_menit, 0) / 60),' Jam ',MOD(IFNULL(total.total_menit, 0), 60),' Menit') AS totJamKerjaAktual, "
                    + "    CASE "
                    + "        WHEN IFNULL(total.jumlah_terlalu_panjang, 0) > 0 THEN 'Kemungkinan Lupa Absen' "
                    + "        WHEN IFNULL(total.jumlah_data_bermasalah, 0) > 0 OR IFNULL(total.jumlah_hari, 0) = 0 THEN 'Perlu Pemeriksaan Data' "
                    + "        WHEN IFNULL(total.jumlah_terlalu_pendek, 0) > 0 THEN 'Jumlah Jam Kerja Kurang' ELSE 'Jumlah Jam Kerja Normal/Wajar' END AS keterangan "
                    + "FROM (SELECT DISTINCT nip FROM reg_absensi) ra "
                    + "INNER JOIN pegawai pg ON pg.nik = ra.nip "
                    + "LEFT JOIN (SELECT nip, COUNT(DISTINCT CASE WHEN menit_aktual IS NOT NULL THEN tgl_datang_absen ELSE NULL END) AS jumlah_hari, "
                    + "        SUM(IFNULL(menit_aktual, 0)) AS total_menit, "
                    + "        SUM(CASE WHEN menit_aktual > 960 THEN 1 ELSE 0 END) AS jumlah_terlalu_panjang, "
                    + "        SUM(CASE WHEN menit_aktual < 240 THEN 1 ELSE 0 END) AS jumlah_terlalu_pendek, "
                    + "        SUM(CASE WHEN menit_aktual IS NULL THEN 1 ELSE 0 END) AS jumlah_data_bermasalah "
                    + "    FROM (SELECT a.nip, a.tgl_datang_absen, "
                    + "            CASE WHEN a.absen_lengkap <> 'belum' "
                    + "                 AND a.tgl_datang_absen IS NOT NULL "
                    + "                 AND a.jam_datang_absen IS NOT NULL "
                    + "                 AND a.tgl_pulang_absen IS NOT NULL "
                    + "                 AND a.jam_pulang_absen IS NOT NULL "
                    + "                 AND TIMESTAMP(a.tgl_pulang_absen, a.jam_pulang_absen) >= TIMESTAMP(a.tgl_datang_absen,a.jam_datang_absen) "
                    + "                THEN TIMESTAMPDIFF(MINUTE, TIMESTAMP(a.tgl_datang_absen, a.jam_datang_absen), "
                    + "                    TIMESTAMP(a.tgl_pulang_absen, a.jam_pulang_absen)) ELSE NULL END AS menit_aktual FROM absensi a "
                    + "        WHERE a.tgl_datang_absen BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') data_absensi "
                    + "    GROUP BY nip) total ON total.nip = ra.nip WHERE pg.nik LIKE '%' "
                    + "HAVING pg.nik LIKE ? OR pg.nama LIKE ? OR keterangan LIKE ? ORDER BY pg.nik");
            try {
                String cari = "%" + TCari.getText().trim() + "%";
                ps1.setString(1, cari);
                ps1.setString(2, cari);
                ps1.setString(3, cari);
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    tabMode1.addRow(new String[]{
                        rs1.getString("nik"),
                        rs1.getString("nama"),
                        rs1.getString("periodeAwal"),
                        rs1.getString("periodeAkhir"),
                        rs1.getString("jumlahHariAbsen"),
                        rs1.getString("totJamKerjaAktual"),
                        rs1.getString("keterangan")
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
        LCount.setText("" + tabMode1.getRowCount());
    }

    private void tampilLokasi() {
        Valid.tabelKosong(tabMode2);
        try {
            ps2 = koneksi.prepareStatement("SELECT CASE "
                    + "WHEN nm_gedung LIKE '%INST%' OR nm_gedung = '-' OR nm_gedung LIKE '%UNIT%' OR nm_gedung = 'IPSRS' THEN nm_gedung ELSE concat( 'Rg. Rawat ', nm_gedung ) END unit "
                    + "FROM bangsal "
                    + "WHERE nm_gedung NOT IN ('SUB. BAG. SDM', 'SUB. BAG. HUKUM', 'BAYI SEHAT', 'BAGIAN KEUANGAN', 'BID. PELAYANAN MEDIK', 'IGD', 'UPM', 'INST. PEM. JENAZAH') AND nm_gedung <> '-' "
                    + "GROUP BY nm_gedung UNION ALL SELECT 'MANAJEMEN' UNION ALL SELECT 'RUMAH SAKIT' UNION ALL SELECT DISTINCT "
                    + "CASE WHEN nm_poli = 'IGD'  OR nm_poli LIKE '%UNIT%' THEN nm_poli "
                    + "     WHEN UPPER(nm_poli) LIKE '%BEDAH%' THEN 'Poliklinik BEDAH' "
                    + "     WHEN UPPER(nm_poli) LIKE '%GIGI%' THEN 'Poliklinik GIGI' "
                    + "     WHEN UPPER(nm_poli) LIKE '%ORTHOPEDI%' THEN 'Poliklinik ORTHOPEDI' "
                    + "     WHEN UPPER(nm_poli) LIKE '%REHABILITASI MEDIK%' THEN 'Poliklinik REHABILITASI MEDIK' ELSE CONCAT('Poliklinik ', nm_poli) END AS unit "
                    + "	FROM poliklinik WHERE kd_poli NOT IN ('-', 'LAA', 'LAB', 'RAD', 'KJH', 'IOB', 'UMUM', 'MCHJ', 'PON') ORDER BY unit");
            try {
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    tabMode2.addRow(new Object[]{
                        false,
                        rs2.getString("unit")
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
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    public void isCek() {
        MnHapus.setEnabled(akses.getadmin());
        MnHapusBanyak.setEnabled(akses.getadmin());
    }

    public void awalData() {
        TabAbsensi.setSelectedIndex(0);
        tampil();
        tampilLokasi();
    }
}