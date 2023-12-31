package permintaan;
import fungsi.BackgroundMusic;
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
import simrskhanza.DlgPeriksaLaboratorium;

public class DlgCariPermintaanLab extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1;
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Connection koneksi = koneksiDB.condb();
    private int i = 0, x = 0, cekSudah = 0, pilihan = 0, diagnosa_cek = 0;
    private PreparedStatement ps, ps1;
    private ResultSet rs, rs1;
    private String norw = "", nominta = "", diagnosa_ok = "", cekdokter = "", nokirim = "";
    private BackgroundMusic music;
    
    /** Creates new form DlgProgramStudi
     * @param parent
     * @param modal */
    public DlgCariPermintaanLab(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new Object[]{
            "No.Rawat", "No. RM", "Nama Pasien", "No. Permintaan", "Tgl. Permintaan",
            "Jam", "Jns. Rawat", "sttsperiksa", "Poli/Rg. Rawat", "Cara Bayar", "No. Kirim", "Diterima Oleh"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbPasien.setModel(tabMode);
        tbPasien.setPreferredScrollableViewportSize(new Dimension(800, 800));
        tbPasien.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 12; i++) {
            TableColumn column = tbPasien.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(220);
            } else if (i == 3) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 4) {
                column.setPreferredWidth(100);
            } else if (i == 5) {
                column.setPreferredWidth(70);
            } else if (i == 6) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 7) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 8) {
                column.setPreferredWidth(200);
            } else if (i == 9) {
                column.setPreferredWidth(120);
            } else if (i == 10) {
                column.setPreferredWidth(100);
            } else if (i == 11) {
                column.setPreferredWidth(200);
            }
        }
        tbPasien.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode1 = new DefaultTableModel(null, new Object[]{
            "No.", "Cito", "No. Permintaan", "Nama Pemeriksaan", "Tgl. Permintaan",
            "Jam", "Status", "Dokter Perujuk", "norawat", "No. Kirim"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbPermintaan.setModel(tabMode1);
        tbPermintaan.setPreferredScrollableViewportSize(new Dimension(800, 800));
        tbPermintaan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 10; i++) {
            TableColumn column = tbPermintaan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(40);
            } else if (i == 2) {
                column.setPreferredWidth(115);
            } else if (i == 3) {
                column.setPreferredWidth(250);
            } else if (i == 4) {
                column.setPreferredWidth(100);
            } else if (i == 5) {
                column.setPreferredWidth(75);
            } else if (i == 6) {
                column.setPreferredWidth(75);
            } else if (i == 7) {
                column.setPreferredWidth(220);
            } else if (i == 8) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 9) {
                column.setPreferredWidth(100);
            }
        }
        tbPermintaan.setDefaultRenderer(Object.class, new WarnaTable());
        
        TCari.setDocument(new batasInput((byte)100).getKata(TCari));
        if(koneksiDB.cariCepat().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {tampilPasien();}
                @Override
                public void removeUpdate(DocumentEvent e) {tampilPasien();}
                @Override
                public void changedUpdate(DocumentEvent e) {tampilPasien();}
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
        MnVerifikasiDiterima = new javax.swing.JMenuItem();
        MnVerifikasiBatal = new javax.swing.JMenuItem();
        MnPeriksaLab = new javax.swing.JMenuItem();
        MnCekNotif = new javax.swing.JMenuItem();
        MnCetakPermintaanTermal = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        jPanel3 = new javax.swing.JPanel();
        scrollPane1 = new widget.ScrollPane();
        tbPasien = new widget.Table();
        scrollPane2 = new widget.ScrollPane();
        tbPermintaan = new widget.Table();
        jPanel2 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        label11 = new widget.Label();
        Tgl1 = new widget.Tanggal();
        label18 = new widget.Label();
        Tgl2 = new widget.Tanggal();
        label10 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel10 = new widget.Label();
        LCount = new widget.Label();
        panelisi1 = new widget.panelisi();
        BtnHapus = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnVerifikasiDiterima.setBackground(new java.awt.Color(255, 255, 254));
        MnVerifikasiDiterima.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnVerifikasiDiterima.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnVerifikasiDiterima.setText("Verifikasi Pemeriksaan");
        MnVerifikasiDiterima.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnVerifikasiDiterima.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnVerifikasiDiterima.setName("MnVerifikasiDiterima"); // NOI18N
        MnVerifikasiDiterima.setPreferredSize(new java.awt.Dimension(230, 26));
        MnVerifikasiDiterima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnVerifikasiDiterimaActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnVerifikasiDiterima);

        MnVerifikasiBatal.setBackground(new java.awt.Color(255, 255, 254));
        MnVerifikasiBatal.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnVerifikasiBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnVerifikasiBatal.setText("Batal Verifikasi");
        MnVerifikasiBatal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnVerifikasiBatal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnVerifikasiBatal.setName("MnVerifikasiBatal"); // NOI18N
        MnVerifikasiBatal.setPreferredSize(new java.awt.Dimension(230, 26));
        MnVerifikasiBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnVerifikasiBatalActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnVerifikasiBatal);

        MnPeriksaLab.setBackground(new java.awt.Color(255, 255, 254));
        MnPeriksaLab.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPeriksaLab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPeriksaLab.setText("Periksa Laboratorium");
        MnPeriksaLab.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPeriksaLab.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPeriksaLab.setName("MnPeriksaLab"); // NOI18N
        MnPeriksaLab.setPreferredSize(new java.awt.Dimension(230, 26));
        MnPeriksaLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPeriksaLabActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnPeriksaLab);

        MnCekNotif.setBackground(new java.awt.Color(255, 255, 254));
        MnCekNotif.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCekNotif.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/icons8-audio-24.png"))); // NOI18N
        MnCekNotif.setText("Cek Notif Laboratorium");
        MnCekNotif.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCekNotif.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCekNotif.setName("MnCekNotif"); // NOI18N
        MnCekNotif.setPreferredSize(new java.awt.Dimension(230, 26));
        MnCekNotif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCekNotifActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnCekNotif);

        MnCetakPermintaanTermal.setBackground(new java.awt.Color(255, 255, 254));
        MnCetakPermintaanTermal.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakPermintaanTermal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/PrinterSettings.png"))); // NOI18N
        MnCetakPermintaanTermal.setText("Cetak Permintaan (Kertas Thermal)");
        MnCetakPermintaanTermal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCetakPermintaanTermal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCetakPermintaanTermal.setName("MnCetakPermintaanTermal"); // NOI18N
        MnCetakPermintaanTermal.setPreferredSize(new java.awt.Dimension(230, 26));
        MnCetakPermintaanTermal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakPermintaanTermalActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnCetakPermintaanTermal);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Permintaan Pemeriksaan Laboratorium ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setLayout(new java.awt.GridLayout(1, 2));

        scrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Daftar Pasien Permintaan Pemeriksaan ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        scrollPane1.setName("scrollPane1"); // NOI18N
        scrollPane1.setOpaque(true);

        tbPasien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbPasien.setToolTipText("Silahkan klik untuk memilih data yang mau diverifikasi/dicetak ulang");
        tbPasien.setComponentPopupMenu(jPopupMenu1);
        tbPasien.setName("tbPasien"); // NOI18N
        tbPasien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPasienMouseClicked(evt);
            }
        });
        tbPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPasienKeyPressed(evt);
            }
        });
        scrollPane1.setViewportView(tbPasien);

        jPanel3.add(scrollPane1);

        scrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Item Permintaan Pemeriksaan Lab. ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        scrollPane2.setName("scrollPane2"); // NOI18N
        scrollPane2.setOpaque(true);

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
        tbPermintaan.setToolTipText("");
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
        scrollPane2.setViewportView(tbPermintaan);

        jPanel3.add(scrollPane2);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.CENTER);

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
        panelGlass8.add(Tgl2);

        label10.setForeground(new java.awt.Color(0, 0, 0));
        label10.setText("Key Word :");
        label10.setName("label10"); // NOI18N
        label10.setPreferredSize(new java.awt.Dimension(62, 23));
        panelGlass8.add(label10);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(220, 23));
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
        jLabel10.setText("Jlh. Pasien : ");
        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setPreferredSize(new java.awt.Dimension(75, 23));
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

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
/*
private void KdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKdKeyPressed
    Valid.pindah(evt,BtnCari,Nm);
}//GEN-LAST:event_TKdKeyPressed
*/

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
        tampilPasien();
        Valid.tabelKosong(tabMode1);
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
        tampilPasien();
        Valid.tabelKosong(tabMode1);
    }//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAllActionPerformed(null);
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

private void tbPasienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPasienMouseClicked
    if (tabMode.getRowCount() != 0) {
        try {
            getData();
        } catch (java.lang.NullPointerException e) {
        }
    }
}//GEN-LAST:event_tbPasienMouseClicked

private void tbPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPasienKeyPressed
    if (tabMode.getRowCount() != 0) {
        if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }
}//GEN-LAST:event_tbPasienKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampilPasien();
        Valid.tabelKosong(tabMode1);
    }//GEN-LAST:event_formWindowOpened

    private void MnVerifikasiDiterimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnVerifikasiDiterimaActionPerformed
        if (nokirim.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data permintaan pemeriksaan Lab. yg. akan diverifikasi..!!!");
            tbPermintaan.requestFocus();
        } else {
            Sequel.mengedit("permintaan_lab_raza", "no_kirim='" + nokirim + "'", "status_periksa='Diterima'");
            tampilPasien();
            tampilPemeriksaan();
        }
    }//GEN-LAST:event_MnVerifikasiDiterimaActionPerformed

    private void tbPermintaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPermintaanKeyPressed
        if (tabMode1.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getdataItem();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbPermintaanKeyPressed

    private void tbPermintaanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPermintaanMouseClicked
        if (tabMode1.getRowCount() != 0) {
            try {
                getdataItem();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbPermintaanMouseClicked

    private void MnVerifikasiBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnVerifikasiBatalActionPerformed
        if (nokirim.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data permintaan pemeriksaan Lab. yg. akan diverifikasi..!!!");
            tbPermintaan.requestFocus();
        } else {
            Sequel.mengedit("permintaan_lab_raza", "no_kirim='" + nokirim + "'", "status_periksa='BELUM'");
            tampilPasien();
            tampilPemeriksaan();
        }
    }//GEN-LAST:event_MnVerifikasiBatalActionPerformed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (pilihan == 1) {
            if (tbPasien.getSelectedRow() != -1) {
                if (nokirim.equals("")) {
                    JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu datanya pada tabel...!!!!");
                    tbPasien.requestFocus();
                } else if (Sequel.cariInteger("select count(-1) from permintaan_lab_raza where no_rawat='" + norw + "' and "
                        + "status_periksa='Diterima' and no_kirim='" + nokirim + "'") > 0) {
                    JOptionPane.showMessageDialog(null, "Data permintaan pemeriksaan laboratorium sudah diterima...!!!!");
                } else {
                    x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                    if (x == JOptionPane.YES_OPTION) {
                        Sequel.queryu("delete from permintaan_lab_raza where no_kirim='" + nokirim + "'");
                        tampilPasien();
                        Valid.tabelKosong(tabMode1);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih data permintaan pemeriksaan laboratorium...!!!!");
                TCari.requestFocus();
            }
        } else if (pilihan == 2) {
            if (tbPermintaan.getSelectedRow() != -1) {
                if (nominta.equals("")) {
                    JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu datanya pada tabel...!!!!");
                    tbPermintaan.requestFocus();
                } else if (tbPermintaan.getValueAt(tbPermintaan.getSelectedRow(),6).toString().equals("Diterima")) {
                    JOptionPane.showMessageDialog(null, "Data permintaan pemeriksaan laboratorium sudah diterima...!!!!");
                } else {
                    x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                    if (x == JOptionPane.YES_OPTION) {
                        Sequel.queryu("delete from permintaan_lab_raza where no_rawat='" + tbPermintaan.getValueAt(tbPermintaan.getSelectedRow(), 8).toString() + "' "
                                + "and no_minta='" + nominta + "'");
                        tampilPasien();
                        tampilPemeriksaan();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih data permintaan pemeriksaan laboratorium...!!!!");
                TCari.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Data permintaan pemeriksaan laboratorium belum dipilih...!!!!");
            TCari.requestFocus();
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari, BtnAll);
        }
    }//GEN-LAST:event_BtnHapusKeyPressed

    private void MnPeriksaLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPeriksaLabActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (nokirim.equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbPasien.requestFocus();
        } else {
            diagnosa_ok = "";
            cekdokter = "";
            diagnosa_cek = 0;

            if (tbPasien.getValueAt(tbPasien.getSelectedRow(), 6).toString().equals("Ranap")) {
                diagnosa_cek = Sequel.cariInteger("select count(1) cek from kamar_inap where no_rawat='" + norw + "'");
                if (diagnosa_cek == 0) {
                    diagnosa_ok = "-";
                } else {
                    diagnosa_ok = Sequel.cariIsi("select diagnosa_awal from kamar_inap where no_rawat='" + norw + "'");
                }

                if (Sequel.cariInteger("select count(-1) from permintaan_lab_raza where no_rawat='" + norw + "' and no_kirim='" + nokirim + "'") > 0) {
                    cekdokter = Sequel.cariIsi("select dokter_perujuk from permintaan_lab_raza where no_rawat='" + norw + "'");
                } else {
                    cekdokter = Sequel.cariIsi("select kd_dokter from dpjp_ranap where no_rawat='" + norw + "'");
                }

                if (Sequel.cariRegistrasi(tbPasien.getValueAt(tbPasien.getSelectedRow(), 0).toString()) > 0) {
                    JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi, data tidak boleh dihapus/diubah. Silahkan hubungi bagian kasir/keuangan ..!!");
                } else {
                    akses.setform("DlgCariPermintaanLab");
                    DlgPeriksaLaboratorium periksalab = new DlgPeriksaLaboratorium(null, false);
                    periksalab.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                    periksalab.setLocationRelativeTo(internalFrame1);
                    periksalab.emptTeks();
                    periksalab.KodePerujuk.setText(cekdokter);
                    periksalab.setNoRm(norw, "Ranap", "-", diagnosa_ok, Sequel.cariIsi("select b.nm_bangsal from kamar_inap ki "
                            + "inner join kamar k on k.kd_kamar=ki.kd_kamar inner join bangsal b on b.kd_bangsal=k.kd_bangsal where "
                            + "ki.no_rawat='" + norw + "' order by ki.tgl_masuk desc, ki.jam_masuk desc limit 1"));
                    periksalab.tampiltarif();
                    periksalab.tampil();
                    periksalab.setOrder(nokirim);
                    periksalab.isCek();
                    periksalab.setVisible(true);
                    periksalab.fokus();
                }
            } else {
                diagnosa_cek = Sequel.cariInteger("select count(1) cek from pemeriksaan_ralan where no_rawat='" + norw + "'");
                if (diagnosa_cek == 0) {
                    diagnosa_ok = "-";
                } else {
                    diagnosa_ok = Sequel.cariIsi("select diagnosa from pemeriksaan_ralan where no_rawat='" + norw + "'");
                }

                akses.setform("DlgCariPermintaanLab");
                DlgPeriksaLaboratorium periksalab = new DlgPeriksaLaboratorium(null, false);
                periksalab.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                periksalab.setLocationRelativeTo(internalFrame1);
                periksalab.emptTeks();
                periksalab.KodePerujuk.setText(Sequel.cariIsi("select kd_dokter from reg_periksa where no_rawat='" + norw + "'"));
                periksalab.setNoRm(norw, "Ralan", diagnosa_ok, "-", Sequel.cariIsi("select p.nm_poli from reg_periksa r "
                        + "inner join poliklinik p on p.kd_poli=r.kd_poli where r.no_rawat='" + norw + "'"));
                periksalab.tampiltarif();
                periksalab.setOrder(nokirim);
                periksalab.tampil();
                periksalab.isCek();
                periksalab.setVisible(true);
                periksalab.fokus();
            }
        }
    }//GEN-LAST:event_MnPeriksaLabActionPerformed

    private void MnCekNotifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCekNotifActionPerformed
        notifAlarmLab();
    }//GEN-LAST:event_MnCekNotifActionPerformed

    private void MnCetakPermintaanTermalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakPermintaanTermalActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (nokirim.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data permintaan pemeriksaan Lab. yg. akan dicetak..!!!");
            tbPermintaan.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("norawat", norw);
            param.put("nokirim", nokirim);
            param.put("norm", tbPasien.getValueAt(tbPasien.getSelectedRow(), 1).toString());
            param.put("nmpasien", tbPasien.getValueAt(tbPasien.getSelectedRow(), 2).toString());
            param.put("tgl", tbPasien.getValueAt(tbPasien.getSelectedRow(), 4).toString());
            param.put("jam", tbPasien.getValueAt(tbPasien.getSelectedRow(), 5).toString());
            param.put("unit", tbPasien.getValueAt(tbPasien.getSelectedRow(), 8).toString());
            param.put("carabayar", tbPasien.getValueAt(tbPasien.getSelectedRow(), 9).toString());
            Valid.MyReport("rptStrukPermintaanLab.jasper", "report", "::[ Struk Permintaan Pemeriksaan Lab. (Kertas Thermal) ]::",
                    "SELECT nm_pemeriksaan from permintaan_lab_raza where no_kirim='" + nokirim + "' order by no_minta", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnCetakPermintaanTermalActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgCariPermintaanLab dialog = new DlgCariPermintaanLab(new javax.swing.JFrame(), true);
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
    private widget.Label LCount;
    private javax.swing.JMenuItem MnCekNotif;
    private javax.swing.JMenuItem MnCetakPermintaanTermal;
    private javax.swing.JMenuItem MnPeriksaLab;
    private javax.swing.JMenuItem MnVerifikasiBatal;
    private javax.swing.JMenuItem MnVerifikasiDiterima;
    public widget.TextBox TCari;
    private widget.Tanggal Tgl1;
    private widget.Tanggal Tgl2;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.Label label10;
    private widget.Label label11;
    private widget.Label label18;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelisi1;
    private widget.ScrollPane scrollPane1;
    private widget.ScrollPane scrollPane2;
    private widget.Table tbPasien;
    private widget.Table tbPermintaan;
    // End of variables declaration//GEN-END:variables

    private void tampilPasien() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT pl.no_rawat, p.no_rkm_medis, p.nm_pasien, pl.no_minta, date_format(pl.tgl_permintaan,'%d-%m-%Y') tglmnta, "
                    + "pl.jam_permintaan, pl.status_rawat, pl.status_periksa, pl.dari_unit, pj.png_jawab, pl.no_kirim, "
                    + "if(pl.status_periksa='Diterima',pg.nama,'-') nmPetugas FROM permintaan_lab_raza pl "
                    + "INNER JOIN reg_periksa rp ON rp.no_rawat=pl.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis "
                    + "INNER JOIN penjab pj ON pj.kd_pj=rp.kd_pj inner join pegawai pg on pg.nik=pl.nip_penerima WHERE "
                    + "pl.no_kirim<>'Menunggu' and pl.tgl_permintaan between ? and ? and pl.no_rawat like ? or "
                    + "pl.no_kirim<>'Menunggu' and pl.tgl_permintaan between ? and ? and p.no_rkm_medis like ? or "
                    + "pl.no_kirim<>'Menunggu' and pl.tgl_permintaan between ? and ? and p.nm_pasien like ? or "
                    + "pl.no_kirim<>'Menunggu' and pl.tgl_permintaan between ? and ? and pl.no_minta like ? "
                    + "GROUP BY pl.no_rawat, pl.no_kirim order by pl.tgl_permintaan desc, pl.jam_permintaan desc");
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
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("no_minta"),
                        rs.getString("tglmnta"),
                        rs.getString("jam_permintaan"),
                        rs.getString("status_rawat"),
                        rs.getString("status_periksa"),
                        rs.getString("dari_unit"),
                        rs.getString("png_jawab"),
                        rs.getString("no_kirim"),
                        rs.getString("nmPetugas")
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
            System.out.println("Notif : " + e);
        }
        LCount.setText("" + tabMode.getRowCount());
    }
    
    private void tampilPemeriksaan() {
        Valid.tabelKosong(tabMode1);
        try {
            ps1 = koneksi.prepareStatement("SELECT UPPER(p.cito) cito, p.nm_pemeriksaan, date_format(p.tgl_permintaan,'%d-%m-%Y') tglminta, "
                    + "p.jam_permintaan, p.status_periksa, p.no_rawat, pg.nama nmdokter, p.no_minta, p.no_kirim FROM permintaan_lab_raza p "
                    + "inner join pegawai pg on pg.nik=p.dokter_perujuk where p.no_rawat like ? and p.no_kirim like ? "
                    + "order by p.tgl_permintaan desc, p.jam_permintaan desc");
            try {
                ps1.setString(1, "%" + norw + "%");
                ps1.setString(2, "%" + nokirim + "%");
                rs1 = ps1.executeQuery();
                x = 1;
                while (rs1.next()) {
                    tabMode1.addRow(new Object[]{
                        x + ".",
                        rs1.getString("cito"),
                        rs1.getString("no_minta"),
                        rs1.getString("nm_pemeriksaan"),
                        rs1.getString("tglminta"),
                        rs1.getString("jam_permintaan"),
                        rs1.getString("status_periksa"),
                        rs1.getString("nmdokter"),
                        rs1.getString("no_rawat"),
                        rs1.getString("no_kirim")
                    });
                    x++;
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs1 != null) {
                    rs1.close();
                }
                if (ps1 != null) {
                    ps1.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notif : "+e);
        }            
    }
    
    private void getData() {
        pilihan = 0;
        norw = "";
        nokirim = "";
        pilihan = 1;
        
        if(tbPasien.getSelectedRow()!= -1){
            norw = tbPasien.getValueAt(tbPasien.getSelectedRow(), 0).toString();
            nokirim = tbPasien.getValueAt(tbPasien.getSelectedRow(),10).toString();
            tampilPemeriksaan();
        }
    }
    
    public void isCek(String norwt){
        MnVerifikasiDiterima.setEnabled(akses.getperiksa_lab());
        MnVerifikasiBatal.setEnabled(akses.getperiksa_lab());
        BtnHapus.setEnabled(akses.getpermintaan_lab());
        MnCekNotif.setEnabled(akses.getadmin());
        
        TCari.setText(norwt);
        Valid.SetTgl(Tgl1, Sequel.cariIsi("select ifnull(tgl_registrasi,date(now())) from reg_periksa where no_rawat='" + norwt + "'"));
        Tgl2.setDate(new Date());
    }
    
    private void getdataItem() {
        pilihan = 0;
        nominta = "";
        pilihan = 2;
        if (tbPermintaan.getSelectedRow() != -1) {
            nominta = tbPermintaan.getValueAt(tbPermintaan.getSelectedRow(), 2).toString();
        }
    }
    
    private void notifAlarmLab() {
        try {
            music = new BackgroundMusic("./suara/permintaan_periksa_laboratorium_diterima.mp3");
            music.start();
            Thread.sleep(700);            
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
