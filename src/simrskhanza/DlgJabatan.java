/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgJabatan.java
 *
 * Created on May 22, 2010, 9:37:21 PM
 */

package simrskhanza;
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
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import rekammedis.DlgMasterJabatanKomite;

/**
 *
 * @author dosen
 */
public final class DlgJabatan extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private DlgMasterJabatanKomite komite = new DlgMasterJabatanKomite(null, false);
    private String status = "", kode = "";

    /** Creates new form DlgJabatan
     * @param parent
     * @param modal */
    public DlgJabatan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(10,10);
        setSize(459,539);

        Object[] row = {"Kode Jabatan", "Nama Jabatan", "Nama Komite", "Status Aktif", "kdkomite"};
        tabMode = new DefaultTableModel(null, row) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbJabatan.setModel(tabMode);
        tbJabatan.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbJabatan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 5; i++) {
            TableColumn column = tbJabatan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(100);
            } else if (i == 1) {
                column.setPreferredWidth(300);
            } else if (i == 2) {
                column.setPreferredWidth(300);
            } else if (i == 3) {
                column.setPreferredWidth(80);
            } else if (i == 4) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbJabatan.setDefaultRenderer(Object.class, new WarnaTable());
        
        komite.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgJabatan")) {
                    if (komite.getTable().getSelectedRow() != -1) {
                        kdKomite.setText(komite.getTable().getValueAt(komite.getTable().getSelectedRow(), 0).toString());
                        nmKomite.setText(komite.getTable().getValueAt(komite.getTable().getSelectedRow(), 1).toString());
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
        
        komite.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (akses.getform().equals("DlgJabatan")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        komite.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        }); 

        TKd.setDocument(new batasInput((byte)4).getKata(TKd));
        TNm.setDocument(new batasInput((byte)25).getKata(TNm));
        TCari.setDocument(new batasInput((byte)100).getKata(TCari));
        
        if(koneksiDB.cariCepat().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {tampil();}
                @Override
                public void removeUpdate(DocumentEvent e) {tampil();}
                @Override
                public void changedUpdate(DocumentEvent e) {tampil();}
            });
        } 
        
        try {
            ps = koneksi.prepareStatement("select j.kd_jbtn, j.nm_jbtn, jk.nm_komite, j.aktif, j.kd_komite from jabatan j "
                    + "inner join jabatan_komite jk on jk.kd_komite=j.kd_komite where "
                    + "j.kd_jbtn like ? or "
                    + "j.nm_jbtn like ? or "
                    + "jk.nm_komite like ? or "
                    + "j.aktif like ? order by j.kd_jbtn");
        } catch (SQLException e) {
            System.out.println(e);
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

        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbJabatan = new widget.Table();
        panelGlass7 = new widget.panelisi();
        jLabel3 = new widget.Label();
        TKd = new widget.TextBox();
        jLabel4 = new widget.Label();
        TNm = new widget.TextBox();
        jLabel5 = new widget.Label();
        kdKomite = new widget.TextBox();
        nmKomite = new widget.TextBox();
        btnJabatan = new widget.Button();
        jLabel8 = new widget.Label();
        cmbStatus = new widget.ComboBox();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass9 = new widget.panelisi();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Data Jabatan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbJabatan.setAutoCreateRowSorter(true);
        tbJabatan.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbJabatan.setName("tbJabatan"); // NOI18N
        tbJabatan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbJabatanMouseClicked(evt);
            }
        });
        tbJabatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbJabatanKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbJabatan);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass7.setName("panelGlass7"); // NOI18N
        panelGlass7.setPreferredSize(new java.awt.Dimension(44, 47));
        panelGlass7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 3, 9));

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Kode Jabatan : ");
        jLabel3.setName("jLabel3"); // NOI18N
        jLabel3.setPreferredSize(new java.awt.Dimension(90, 24));
        panelGlass7.add(jLabel3);

        TKd.setForeground(new java.awt.Color(0, 0, 0));
        TKd.setHighlighter(null);
        TKd.setName("TKd"); // NOI18N
        TKd.setPreferredSize(new java.awt.Dimension(70, 24));
        TKd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKdKeyPressed(evt);
            }
        });
        panelGlass7.add(TKd);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Nama Jabatan : ");
        jLabel4.setName("jLabel4"); // NOI18N
        jLabel4.setPreferredSize(new java.awt.Dimension(90, 24));
        panelGlass7.add(jLabel4);

        TNm.setForeground(new java.awt.Color(0, 0, 0));
        TNm.setName("TNm"); // NOI18N
        TNm.setPreferredSize(new java.awt.Dimension(220, 24));
        TNm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNmKeyPressed(evt);
            }
        });
        panelGlass7.add(TNm);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Jabatan Komite : ");
        jLabel5.setName("jLabel5"); // NOI18N
        jLabel5.setPreferredSize(new java.awt.Dimension(90, 24));
        panelGlass7.add(jLabel5);

        kdKomite.setEditable(false);
        kdKomite.setForeground(new java.awt.Color(0, 0, 0));
        kdKomite.setName("kdKomite"); // NOI18N
        kdKomite.setPreferredSize(new java.awt.Dimension(70, 24));
        panelGlass7.add(kdKomite);

        nmKomite.setEditable(false);
        nmKomite.setForeground(new java.awt.Color(0, 0, 0));
        nmKomite.setName("nmKomite"); // NOI18N
        nmKomite.setPreferredSize(new java.awt.Dimension(220, 24));
        panelGlass7.add(nmKomite);

        btnJabatan.setForeground(new java.awt.Color(0, 0, 0));
        btnJabatan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnJabatan.setMnemonic('X');
        btnJabatan.setToolTipText("Alt+X");
        btnJabatan.setName("btnJabatan"); // NOI18N
        btnJabatan.setPreferredSize(new java.awt.Dimension(28, 24));
        btnJabatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJabatanActionPerformed(evt);
            }
        });
        panelGlass7.add(btnJabatan);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Status Aktif : ");
        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.setPreferredSize(new java.awt.Dimension(70, 24));
        panelGlass7.add(jLabel8);

        cmbStatus.setForeground(new java.awt.Color(0, 0, 0));
        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Aktif", "Non Aktif" }));
        cmbStatus.setName("cmbStatus"); // NOI18N
        cmbStatus.setPreferredSize(new java.awt.Dimension(82, 23));
        panelGlass7.add(cmbStatus);

        internalFrame1.add(panelGlass7, java.awt.BorderLayout.PAGE_START);

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
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

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
        panelGlass9.add(BtnAll);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass9.add(jLabel7);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass9.add(LCount);

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TKdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKdKeyPressed
        Valid.pindah(evt,TCari,TNm);
}//GEN-LAST:event_TKdKeyPressed

    private void TNmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNmKeyPressed
        Valid.pindah(evt,TKd,BtnSimpan);
}//GEN-LAST:event_TNmKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TKd.getText().trim().equals("")) {
            Valid.textKosong(TKd, "kode jabatan");
        } else if (TNm.getText().trim().equals("")) {
            Valid.textKosong(TNm, "nama jabatan");
            TNm.requestFocus();
        } else if (kdKomite.getText().equals("")) {
            Valid.textKosong(kdKomite, "jabatan komite");
            btnJabatan.requestFocus();
        } else {
            status = "";
            if (cmbStatus.getSelectedIndex() == 0) {
                status = "1";
            } else if (cmbStatus.getSelectedIndex() == 1) {
                status = "0";
            }
            
            Sequel.menyimpan("jabatan", "'" + TKd.getText() + "','" + TNm.getText() + "','" + kdKomite.getText() + "','" + status + "'", "Kode Jabatan");
            tampil();
            emptTeks();
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
            Valid.pindah(evt,TNm,BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            emptTeks();
        }else{Valid.pindah(evt, BtnSimpan, BtnHapus);}
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Tabel masih kosong...!!");
            tbJabatan.requestFocus();
        } else if (kode.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu datanya pada tabel...!!");
            tbJabatan.requestFocus();
        } else {
            Sequel.queryu("delete from jabatan where kd_jbtn='" + kode + "'");
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
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Tabel masih kosong...!!");
            tbJabatan.requestFocus();
        } else if (kode.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu datanya pada tabel...!!");
            tbJabatan.requestFocus();
        } else if (TKd.getText().trim().equals("")) {
            Valid.textKosong(TKd, "kode jabatan");
        } else if (TNm.getText().trim().equals("")) {
            Valid.textKosong(TNm, "nama jabatan");
            TNm.requestFocus();
        } else if (kdKomite.getText().equals("")) {
            Valid.textKosong(kdKomite, "jabatan komite");
            btnJabatan.requestFocus();
        } else {
            status = "";
            if (cmbStatus.getSelectedIndex() == 0) {
                status = "1";
            } else if (cmbStatus.getSelectedIndex() == 1) {
                status = "0";
            }
            
            Sequel.mengedit("jabatan", "kd_jbtn='" + kode + "'",
                    "kd_jbtn='" + TKd.getText() + "',"
                    + "nm_jbtn='" + TNm.getText() + "', "
                    + "kd_komite='" + kdKomite.getText() + "', "
                    + "aktif='" + status + "'");            
            
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
        dispose();
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
        }
}//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tampil();
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCariActionPerformed(null);
        }else{
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
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnAllActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnCari, TKd);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbJabatanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbJabatanMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbJabatanMouseClicked

    private void tbJabatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbJabatanKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbJabatanKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void btnJabatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJabatanActionPerformed
        akses.setform("DlgJabatan");
        komite.setSize(666, internalFrame1.getHeight() - 40);
        komite.setLocationRelativeTo(internalFrame1);
        komite.isCek();
        komite.emptTeks();
        komite.ChkInput.setSelected(false);
        komite.isForm();
        komite.setVisible(true);
        komite.TCari.requestFocus();
    }//GEN-LAST:event_btnJabatanActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgJabatan dialog = new DlgJabatan(new javax.swing.JFrame(), true);
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
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnSimpan;
    private widget.Label LCount;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    private widget.TextBox TKd;
    private widget.TextBox TNm;
    private widget.Button btnJabatan;
    private widget.ComboBox cmbStatus;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel3;
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private javax.swing.JPanel jPanel3;
    private widget.TextBox kdKomite;
    private widget.TextBox nmKomite;
    private widget.panelisi panelGlass7;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.Table tbJabatan;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps.setString(1, "%" + TCari.getText().trim() + "%");
            ps.setString(2, "%" + TCari.getText().trim() + "%");
            ps.setString(3, "%" + TCari.getText().trim() + "%");
            ps.setString(4, "%" + TCari.getText().trim() + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                String[] data = {
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4).replaceAll("0", "Non Aktif").replaceAll("1", "Aktif"),
                    rs.getString(5)
                };
                tabMode.addRow(data);
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode.getRowCount());
    }

    private void getData() {     
        kode = "";
        if (tbJabatan.getSelectedRow() != -1) {
            kode = tbJabatan.getValueAt(tbJabatan.getSelectedRow(), 0).toString();
            TKd.setText(tbJabatan.getValueAt(tbJabatan.getSelectedRow(), 0).toString());
            TNm.setText(tbJabatan.getValueAt(tbJabatan.getSelectedRow(), 1).toString());
            kdKomite.setText(tbJabatan.getValueAt(tbJabatan.getSelectedRow(), 4).toString());
            nmKomite.setText(tbJabatan.getValueAt(tbJabatan.getSelectedRow(), 2).toString());
            cmbStatus.setSelectedItem(tbJabatan.getValueAt(tbJabatan.getSelectedRow(), 3).toString());
        }
    }

    public void emptTeks() {
        TKd.setText("");
        TNm.setText("");
        kdKomite.setText("");
        nmKomite.setText("");
        cmbStatus.setSelectedIndex(0);
        TCari.setText("");
        TKd.requestFocus();
        Valid.autoNomer("jabatan", "J", 3, TKd);
    }
    
    public JTextField getTextField(){
        return TKd;
    }

    public JButton getButton(){
        return BtnKeluar;
    }
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.getpegawai_admin());
        BtnHapus.setEnabled(akses.getpegawai_admin());
        BtnEdit.setEnabled(akses.getpegawai_admin());
    }
}
