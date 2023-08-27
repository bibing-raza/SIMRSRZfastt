/*
  Dilarang keras menggandakan/mengcopy/menyebarkan/membajak/mendecompile 
  Software ini dalam bentuk apapun tanpa seijin pembuat software
  (Khanza.Soft Media). Bagi yang sengaja membajak softaware ini ta
  npa ijin, kami sumpahi sial 1000 turunan, miskin sampai 500 turu
  nan. Selalu mendapat kecelakaan sampai 400 turunan. Anak pertama
  nya cacat tidak punya kaki sampai 300 turunan. Susah cari jodoh
  sampai umur 50 tahun sampai 200 turunan. Ya Alloh maafkan kami 
  karena telah berdoa buruk, semua ini kami lakukan karena kami ti
  dak pernah rela karya kami dibajak tanpa ijin.
 */


package setting;

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
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import kepegawaian.DlgCariPegawai;

/**
 *
 * @author perpustakaan
 */
public class DlgAdmin extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private DlgCariPegawai pegawai = new DlgCariPegawai(null, false);
    private int pilihan = 0;

    /** Creates new form DlgAdmin
     * @param parent
     * @param modal */
    public DlgAdmin(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(10,10);
        setSize(457,249);

        Object[] row = {"ID Admin", "Password", "NIP/NR Pet. Farmasi", "Nama Pet. Farmasi", "NIP Kabid Yanmed", "Nama Pejabat Yanmed"};
        tabMode = new DefaultTableModel(null, row) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbAdmin.setModel(tabMode);
        tbAdmin.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbAdmin.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 6; i++) {
            TableColumn column = tbAdmin.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(250);
            } else if (i == 1) {
                column.setPreferredWidth(250);
            } else if (i == 2) {
                column.setPreferredWidth(150);
            } else if (i == 3) {
                column.setPreferredWidth(250);
            } else if (i == 4) {
                column.setPreferredWidth(150);
            } else if (i == 5) {
                column.setPreferredWidth(250);
            }
        }

        tbAdmin.setDefaultRenderer(Object.class, new WarnaTable());

        TIDadmin.setDocument(new batasInput((byte)30).getKata(TIDadmin));
        Tpassword.setDocument(new batasInput((byte)30).getKata(Tpassword));
        
        pegawai.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {                
                if (akses.getform().equals("DlgAdmin")) {
                    if (pilihan == 1) {
                        if (pegawai.getTable().getSelectedRow() != -1) {
                            TnipFarmasi.setText(pegawai.tbPegawai.getValueAt(pegawai.tbPegawai.getSelectedRow(), 0).toString());
                            TnmFarmasi.setText(pegawai.tbPegawai.getValueAt(pegawai.tbPegawai.getSelectedRow(), 1).toString());
                            BtnPegawai1.requestFocus();
                        }
                    } else if (pilihan == 2) {
                        if (pegawai.getTable().getSelectedRow() != -1) {
                            TnipYanmed.setText(pegawai.tbPegawai.getValueAt(pegawai.tbPegawai.getSelectedRow(), 0).toString());
                            TnmYanmed.setText(pegawai.tbPegawai.getValueAt(pegawai.tbPegawai.getSelectedRow(), 1).toString());
                            BtnPegawai2.requestFocus();
                        }
                    }
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
        
        pegawai.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    pegawai.dispose();
                }                
            }
            @Override
            public void keyReleased(KeyEvent e) {}
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
        Scroll = new widget.ScrollPane();
        tbAdmin = new widget.Table();
        panelGlass7 = new widget.panelisi();
        jLabel3 = new widget.Label();
        TIDadmin = new widget.TextBox();
        jLabel4 = new widget.Label();
        Tpassword = new widget.TextBox();
        jLabel5 = new widget.Label();
        TnipFarmasi = new widget.TextBox();
        jLabel6 = new widget.Label();
        TnmFarmasi = new widget.TextBox();
        BtnPegawai1 = new widget.Button();
        jLabel7 = new widget.Label();
        TnipYanmed = new widget.TextBox();
        jLabel8 = new widget.Label();
        TnmYanmed = new widget.TextBox();
        BtnPegawai2 = new widget.Button();
        panelGlass5 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnKeluar = new widget.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Setup Admin / Petugas Khusus / Pejabat ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbAdmin.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbAdmin.setName("tbAdmin"); // NOI18N
        tbAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbAdminMouseClicked(evt);
            }
        });
        tbAdmin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbAdminKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbAdmin);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass7.setName("panelGlass7"); // NOI18N
        panelGlass7.setPreferredSize(new java.awt.Dimension(44, 105));
        panelGlass7.setLayout(null);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("ID Admin : ");
        jLabel3.setName("jLabel3"); // NOI18N
        jLabel3.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass7.add(jLabel3);
        jLabel3.setBounds(0, 12, 140, 23);

        TIDadmin.setForeground(new java.awt.Color(0, 0, 0));
        TIDadmin.setName("TIDadmin"); // NOI18N
        TIDadmin.setPreferredSize(new java.awt.Dimension(100, 24));
        panelGlass7.add(TIDadmin);
        TIDadmin.setBounds(141, 12, 200, 24);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Password :");
        jLabel4.setName("jLabel4"); // NOI18N
        jLabel4.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass7.add(jLabel4);
        jLabel4.setBounds(345, 12, 110, 23);

        Tpassword.setForeground(new java.awt.Color(0, 0, 0));
        Tpassword.setName("Tpassword"); // NOI18N
        Tpassword.setPreferredSize(new java.awt.Dimension(200, 24));
        panelGlass7.add(Tpassword);
        Tpassword.setBounds(460, 12, 250, 24);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("NIP / NR Pet. Farmasi : ");
        jLabel5.setName("jLabel5"); // NOI18N
        jLabel5.setPreferredSize(new java.awt.Dimension(120, 23));
        panelGlass7.add(jLabel5);
        jLabel5.setBounds(0, 40, 140, 23);

        TnipFarmasi.setEditable(false);
        TnipFarmasi.setForeground(new java.awt.Color(0, 0, 0));
        TnipFarmasi.setName("TnipFarmasi"); // NOI18N
        TnipFarmasi.setPreferredSize(new java.awt.Dimension(100, 24));
        panelGlass7.add(TnipFarmasi);
        TnipFarmasi.setBounds(141, 40, 200, 24);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Nama Pet. Farmasi :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(110, 23));
        panelGlass7.add(jLabel6);
        jLabel6.setBounds(345, 40, 110, 23);

        TnmFarmasi.setEditable(false);
        TnmFarmasi.setForeground(new java.awt.Color(0, 0, 0));
        TnmFarmasi.setName("TnmFarmasi"); // NOI18N
        TnmFarmasi.setPreferredSize(new java.awt.Dimension(250, 24));
        panelGlass7.add(TnmFarmasi);
        TnmFarmasi.setBounds(460, 40, 250, 24);

        BtnPegawai1.setForeground(new java.awt.Color(0, 0, 0));
        BtnPegawai1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPegawai1.setMnemonic('1');
        BtnPegawai1.setToolTipText("ALt+1");
        BtnPegawai1.setName("BtnPegawai1"); // NOI18N
        BtnPegawai1.setPreferredSize(new java.awt.Dimension(28, 26));
        BtnPegawai1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPegawai1ActionPerformed(evt);
            }
        });
        panelGlass7.add(BtnPegawai1);
        BtnPegawai1.setBounds(710, 40, 28, 26);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("NIP Kabid Yanmed : ");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(120, 23));
        panelGlass7.add(jLabel7);
        jLabel7.setBounds(0, 68, 140, 23);

        TnipYanmed.setEditable(false);
        TnipYanmed.setForeground(new java.awt.Color(0, 0, 0));
        TnipYanmed.setName("TnipYanmed"); // NOI18N
        TnipYanmed.setPreferredSize(new java.awt.Dimension(100, 24));
        panelGlass7.add(TnipYanmed);
        TnipYanmed.setBounds(141, 68, 200, 24);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Nama Kabid Yanmed :");
        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.setPreferredSize(new java.awt.Dimension(110, 23));
        panelGlass7.add(jLabel8);
        jLabel8.setBounds(345, 68, 110, 23);

        TnmYanmed.setEditable(false);
        TnmYanmed.setForeground(new java.awt.Color(0, 0, 0));
        TnmYanmed.setName("TnmYanmed"); // NOI18N
        TnmYanmed.setPreferredSize(new java.awt.Dimension(250, 24));
        panelGlass7.add(TnmYanmed);
        TnmYanmed.setBounds(460, 68, 250, 24);

        BtnPegawai2.setForeground(new java.awt.Color(0, 0, 0));
        BtnPegawai2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPegawai2.setMnemonic('1');
        BtnPegawai2.setToolTipText("ALt+1");
        BtnPegawai2.setName("BtnPegawai2"); // NOI18N
        BtnPegawai2.setPreferredSize(new java.awt.Dimension(28, 26));
        BtnPegawai2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPegawai2ActionPerformed(evt);
            }
        });
        panelGlass7.add(BtnPegawai2);
        BtnPegawai2.setBounds(710, 68, 28, 26);

        internalFrame1.add(panelGlass7, java.awt.BorderLayout.PAGE_START);

        panelGlass5.setName("panelGlass5"); // NOI18N
        panelGlass5.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

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
        panelGlass5.add(BtnSimpan);

        BtnBatal.setForeground(new java.awt.Color(0, 0, 0));
        BtnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBatal.setMnemonic('B');
        BtnBatal.setText("Baru");
        BtnBatal.setToolTipText("Alt+B");
        BtnBatal.setIconTextGap(3);
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
        panelGlass5.add(BtnBatal);

        BtnHapus.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapus.setMnemonic('H');
        BtnHapus.setText("Hapus");
        BtnHapus.setToolTipText("Alt+H");
        BtnHapus.setIconTextGap(3);
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
        panelGlass5.add(BtnHapus);

        BtnEdit.setForeground(new java.awt.Color(0, 0, 0));
        BtnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnEdit.setMnemonic('G');
        BtnEdit.setText("Ganti");
        BtnEdit.setToolTipText("Alt+G");
        BtnEdit.setIconTextGap(3);
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
        panelGlass5.add(BtnEdit);

        BtnKeluar.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('K');
        BtnKeluar.setText("Keluar");
        BtnKeluar.setToolTipText("Alt+K");
        BtnKeluar.setIconTextGap(3);
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
        panelGlass5.add(BtnKeluar);

        internalFrame1.add(panelGlass5, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TIDadmin.getText().trim().equals("")) {
            Valid.textKosong(TIDadmin, "ID Admin");
        } else if (Tpassword.getText().trim().equals("")) {
            Valid.textKosong(Tpassword, "Password");
        } else if (TnipFarmasi.getText().trim().equals("")) {
            Valid.textKosong(TnipFarmasi, "Petugas Farmasi");
        } else if (TnipYanmed.getText().trim().equals("")) {
            Valid.textKosong(TnipYanmed, "Kabid Yanmed");
        } else if (tabMode.getRowCount() == 0) {
            Sequel.menyimpan("admin", "AES_ENCRYPT('" + TIDadmin.getText() + "','nur'),"
                    + "AES_ENCRYPT('" + Tpassword.getText() + "','windi'),"
                    + "'" + TnipFarmasi.getText() + "','" + TnipYanmed.getText() + "'", "Admin / Petugas Khusus / Pejabat");
            tampil();
            emptTeks();
        } else if (tabMode.getRowCount() > 0) {
            JOptionPane.showMessageDialog(null, "Maaf, Hanya diijinkan satu Admin Utama & Petugas Farmasi...!!!!");
            TIDadmin.requestFocus();
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnSimpanActionPerformed(null);
        }else{
            Valid.pindah(evt,Tpassword,BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
        tampil();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            emptTeks();
        }else{Valid.pindah(evt, BtnSimpan, BtnHapus);}
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            TIDadmin.requestFocus();
        } else if (Tpassword.getText().trim().equals("") || TnipFarmasi.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Gagal menghapus. Pilih dulu data yang mau dihapus. Klik data pada tabel untuk memilih...!!!!");
        } else if (!Tpassword.getText().trim().equals("")) {
            Sequel.queryu("delete from admin");
            tampil();
            emptTeks();
        }
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnHapusActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (TIDadmin.getText().trim().equals("")) {
            Valid.textKosong(TIDadmin, "kode jabatan");
        } else if (Tpassword.getText().trim().equals("")) {
            Valid.textKosong(Tpassword, "nama jabatan");
        } else if (TnipFarmasi.getText().trim().equals("")) {
            Valid.textKosong(TnipFarmasi, "Petugas Farmasi");
        } else if (TnipYanmed.getText().trim().equals("")) {
            Valid.textKosong(TnipYanmed, "Kabid Yanmed");
        } else {
            Sequel.queryu("delete from admin");
            Sequel.menyimpan("admin", "AES_ENCRYPT('" + TIDadmin.getText() + "','nur'),"
                    + "AES_ENCRYPT('" + Tpassword.getText() + "','windi'),"
                    + "'" + TnipFarmasi.getText() + "','" + TnipYanmed.getText() + "'", "Admin / Petugas Khusus / Pejabat");
            if (tabMode.getRowCount() != 0) {
                tampil();
            }
            emptTeks();
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
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data admin tidak boleh kosong ...!!!!");
            TIDadmin.requestFocus();
        } else if (!(tabMode.getRowCount() == 0)) {
            dispose();
        }
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnEdit,BtnKeluar);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void tbAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAdminMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbAdminMouseClicked

    private void tbAdminKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbAdminKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbAdminKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
        emptTeks();        
    }//GEN-LAST:event_formWindowOpened

    private void BtnPegawai1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPegawai1ActionPerformed
        pilihan = 1;
        akses.setform("DlgAdmin");
        pegawai.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        pegawai.setLocationRelativeTo(internalFrame1);
        pegawai.setAlwaysOnTop(false);
        pegawai.isCek();
        pegawai.emptTeks();
        pegawai.setVisible(true);
        pegawai.TCari.requestFocus();
    }//GEN-LAST:event_BtnPegawai1ActionPerformed

    private void BtnPegawai2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPegawai2ActionPerformed
        pilihan = 2;
        akses.setform("DlgAdmin");
        pegawai.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        pegawai.setLocationRelativeTo(internalFrame1);
        pegawai.setAlwaysOnTop(false);
        pegawai.isCek();
        pegawai.emptTeks();
        pegawai.setVisible(true);
        pegawai.TCari.requestFocus();
    }//GEN-LAST:event_BtnPegawai2ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgAdmin dialog = new DlgAdmin(new javax.swing.JFrame(), true);
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
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPegawai1;
    private widget.Button BtnPegawai2;
    private widget.Button BtnSimpan;
    private widget.ScrollPane Scroll;
    private widget.TextBox TIDadmin;
    private widget.TextBox TnipFarmasi;
    private widget.TextBox TnipYanmed;
    private widget.TextBox TnmFarmasi;
    private widget.TextBox TnmYanmed;
    private widget.TextBox Tpassword;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel3;
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.panelisi panelGlass5;
    private widget.panelisi panelGlass7;
    private widget.Table tbAdmin;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        String sql = "select AES_DECRYPT(usere,'nur'),AES_DECRYPT(passworde,'windi'), nip_admin_farmasi, nip_kabid_yanmed from admin";
        prosesCari(sql);
    }

    private void prosesCari(String sql) {
        Valid.tabelKosong(tabMode);
        try {
            java.sql.Statement stat = koneksi.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                String kd = rs.getString(1);
                String nm = rs.getString(2);
                String nip = rs.getString(3);
                String nm_petugas = Sequel.cariIsi("select nama from pegawai where nik='" + rs.getString(3) + "'");
                String nip_kabid = rs.getString("nip_kabid_yanmed");
                String nm_pejabat = Sequel.cariIsi("select nama from pegawai where nik='" + rs.getString("nip_kabid_yanmed") + "'");
                String[] data = {kd, nm, nip, nm_petugas, nip_kabid, nm_pejabat};
                tabMode.addRow(data);
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    private void getData() {
        int row = tbAdmin.getSelectedRow();
        if (row != -1) {
            TIDadmin.setText(tabMode.getValueAt(row, 0).toString());
            Tpassword.setText(tabMode.getValueAt(row, 1).toString());
            TnipFarmasi.setText(tabMode.getValueAt(row, 2).toString());
            TnmFarmasi.setText(tabMode.getValueAt(row, 3).toString());
            TnipYanmed.setText(tabMode.getValueAt(row, 4).toString());
            TnmYanmed.setText(tabMode.getValueAt(row, 5).toString());
        }
    }

    public void emptTeks() {
        TIDadmin.setText("");
        Tpassword.setText("");
        TnipFarmasi.setText("");
        TnmFarmasi.setText("");
        TnipYanmed.setText("");
        TnmYanmed.setText("");
        TIDadmin.requestFocus();
    }
}
