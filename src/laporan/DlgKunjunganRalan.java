/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgLhtBiaya.java
 *
 * Created on 12 Jul 10, 16:21:34
 */

package laporan;

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
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import kepegawaian.DlgCariPetugas;
import simrskhanza.DlgCariPoli;

/**
 *
 * @author perpustakaan
 */
public final class DlgKunjunganRalan extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps2;
    private ResultSet rs, rs2;
    private int i = 0, lama = 0, baru = 0, laki = 0, per = 0, pilihan = 0;
    private DlgCariPoli poli = new DlgCariPoli(null, false);
    private String setbaru = "", setlama = "", umurlk = "", umurpr = "", diagnosa = "";
    
    /** Creates new form DlgLhtBiaya
     * @param parent
     * @param modal */
    
    public DlgKunjunganRalan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8,1);
        setSize(885,674);

        Object[] rowRwJlDr={"No.","Lama","Baru","Nama Pasien","L","P","Alamat","Diagnosa Resume","Dokter Jaga"};
        tabMode=new DefaultTableModel(null,rowRwJlDr){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        
        tbBangsal.setModel(tabMode);        
        tbBangsal.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbBangsal.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 9; i++) {
            TableColumn column = tbBangsal.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(35);
            }else if(i==1){
                column.setPreferredWidth(70);
            }else if(i==2){
                column.setPreferredWidth(70);
            }else if(i==3){
                column.setPreferredWidth(190);
            }else if(i==4){
                column.setPreferredWidth(40);
            }else if(i==5){
                column.setPreferredWidth(40);
            }else if(i==6){
                column.setPreferredWidth(310);
            }else if(i==7){
                column.setPreferredWidth(230);
            }else if(i==8){
                column.setPreferredWidth(190);
            }
        }
        tbBangsal.setDefaultRenderer(Object.class, new WarnaTable());

        TKd.setDocument(new batasInput((byte)20).getKata(TKd));
        if(koneksiDB.cariCepat().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {
                //    tampil();
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    //tampil();
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    //tampil();
                }
            });          
        } 
        
        poli.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgKunjunganRalan")) {
                    if (poli.getTable().getSelectedRow() != -1) {
                        kdpoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 0).toString());
                        TPoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 1).toString());
                        cekSemuaPoli.setSelected(false);
                        tampil();
                    }
                }
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });
        
        emptText();        
    }    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TKd = new widget.TextBox();
        userBerizin = new widget.TextBox();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnKunSemuaPoli = new javax.swing.JMenuItem();
        MnKunPerPoli = new javax.swing.JMenuItem();
        MnKunCB = new javax.swing.JMenuItem();
        MnKunPoliPerBulan = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbBangsal = new widget.Table();
        panelGlass5 = new widget.panelisi();
        label11 = new widget.Label();
        Tgl1 = new widget.Tanggal();
        label18 = new widget.Label();
        Tgl2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        jLabel7 = new widget.Label();
        BtnKeluar = new widget.Button();
        panelisi1 = new widget.panelisi();
        jLabel19 = new widget.Label();
        kdpoli = new widget.TextBox();
        TPoli = new widget.TextBox();
        BtnUnit = new widget.Button();
        cekSemuaPoli = new widget.CekBox();

        TKd.setForeground(new java.awt.Color(255, 255, 255));
        TKd.setName("TKd"); // NOI18N

        userBerizin.setForeground(new java.awt.Color(255, 255, 255));
        userBerizin.setName("userBerizin"); // NOI18N

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnKunSemuaPoli.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnKunSemuaPoli.setForeground(new java.awt.Color(0, 0, 0));
        MnKunSemuaPoli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept_page.png"))); // NOI18N
        MnKunSemuaPoli.setText("Lap. Rekap Knjngn. Semua Poli/Unit");
        MnKunSemuaPoli.setName("MnKunSemuaPoli"); // NOI18N
        MnKunSemuaPoli.setPreferredSize(new java.awt.Dimension(250, 26));
        MnKunSemuaPoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnKunSemuaPoliActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnKunSemuaPoli);

        MnKunPerPoli.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnKunPerPoli.setForeground(new java.awt.Color(0, 0, 0));
        MnKunPerPoli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept_page.png"))); // NOI18N
        MnKunPerPoli.setText("Lap. Rekap Knjngn. Per Poli/Unit");
        MnKunPerPoli.setName("MnKunPerPoli"); // NOI18N
        MnKunPerPoli.setPreferredSize(new java.awt.Dimension(250, 26));
        MnKunPerPoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnKunPerPoliActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnKunPerPoli);

        MnKunCB.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnKunCB.setForeground(new java.awt.Color(0, 0, 0));
        MnKunCB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept_page.png"))); // NOI18N
        MnKunCB.setText("Lap. Rekap Total Kunjgn. Cara Bayar");
        MnKunCB.setName("MnKunCB"); // NOI18N
        MnKunCB.setPreferredSize(new java.awt.Dimension(250, 26));
        MnKunCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnKunCBActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnKunCB);

        MnKunPoliPerBulan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnKunPoliPerBulan.setForeground(new java.awt.Color(0, 0, 0));
        MnKunPoliPerBulan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept_page.png"))); // NOI18N
        MnKunPoliPerBulan.setText("Rekap Kunjgn. Poli Perbulan");
        MnKunPoliPerBulan.setName("MnKunPoliPerBulan"); // NOI18N
        MnKunPoliPerBulan.setPreferredSize(new java.awt.Dimension(250, 26));
        MnKunPoliPerBulan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnKunPoliPerBulanActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnKunPoliPerBulan);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Data Kunjungan Rawat Jalan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setComponentPopupMenu(jPopupMenu1);
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbBangsal.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbBangsal.setComponentPopupMenu(jPopupMenu1);
        tbBangsal.setName("tbBangsal"); // NOI18N
        tbBangsal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbBangsalMouseClicked(evt);
            }
        });
        tbBangsal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbBangsalKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbBangsal);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass5.setName("panelGlass5"); // NOI18N
        panelGlass5.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        label11.setForeground(new java.awt.Color(0, 0, 0));
        label11.setText("Tanggal :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass5.add(label11);

        Tgl1.setBackground(new java.awt.Color(245, 250, 240));
        Tgl1.setEditable(false);
        Tgl1.setDisplayFormat("dd-MM-yyyy");
        Tgl1.setName("Tgl1"); // NOI18N
        Tgl1.setPreferredSize(new java.awt.Dimension(90, 23));
        Tgl1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tgl1MouseClicked(evt);
            }
        });
        panelGlass5.add(Tgl1);

        label18.setForeground(new java.awt.Color(0, 0, 0));
        label18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label18.setText("s.d.");
        label18.setName("label18"); // NOI18N
        label18.setPreferredSize(new java.awt.Dimension(25, 23));
        panelGlass5.add(label18);

        Tgl2.setBackground(new java.awt.Color(245, 250, 240));
        Tgl2.setEditable(false);
        Tgl2.setDisplayFormat("dd-MM-yyyy");
        Tgl2.setName("Tgl2"); // NOI18N
        Tgl2.setPreferredSize(new java.awt.Dimension(90, 23));
        Tgl2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tgl2MouseClicked(evt);
            }
        });
        panelGlass5.add(Tgl2);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass5.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(155, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass5.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('2');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+2");
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
        panelGlass5.add(BtnCari);

        BtnAll.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setText("Semua Data");
        BtnAll.setToolTipText("Alt+M");
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
        panelGlass5.add(BtnAll);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(30, 23));
        panelGlass5.add(jLabel7);

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
        panelGlass5.add(BtnKeluar);

        internalFrame1.add(panelGlass5, java.awt.BorderLayout.PAGE_END);

        panelisi1.setName("panelisi1"); // NOI18N
        panelisi1.setPreferredSize(new java.awt.Dimension(50, 43));
        panelisi1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Poliklinik / Unit :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(90, 14));
        panelisi1.add(jLabel19);

        kdpoli.setEditable(false);
        kdpoli.setForeground(new java.awt.Color(0, 0, 0));
        kdpoli.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        kdpoli.setEnabled(false);
        kdpoli.setHighlighter(null);
        kdpoli.setName("kdpoli"); // NOI18N
        kdpoli.setPreferredSize(new java.awt.Dimension(60, 23));
        kdpoli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdpoliKeyPressed(evt);
            }
        });
        panelisi1.add(kdpoli);

        TPoli.setEditable(false);
        TPoli.setForeground(new java.awt.Color(0, 0, 0));
        TPoli.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        TPoli.setEnabled(false);
        TPoli.setName("TPoli"); // NOI18N
        TPoli.setPreferredSize(new java.awt.Dimension(230, 23));
        panelisi1.add(TPoli);

        BtnUnit.setForeground(new java.awt.Color(0, 0, 0));
        BtnUnit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnUnit.setMnemonic('4');
        BtnUnit.setToolTipText("ALt+4");
        BtnUnit.setName("BtnUnit"); // NOI18N
        BtnUnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUnitActionPerformed(evt);
            }
        });
        panelisi1.add(BtnUnit);

        cekSemuaPoli.setBackground(new java.awt.Color(255, 255, 250));
        cekSemuaPoli.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        cekSemuaPoli.setForeground(new java.awt.Color(0, 0, 0));
        cekSemuaPoli.setText("Semua Poli/Unit");
        cekSemuaPoli.setBorderPainted(true);
        cekSemuaPoli.setBorderPaintedFlat(true);
        cekSemuaPoli.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cekSemuaPoli.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cekSemuaPoli.setName("cekSemuaPoli"); // NOI18N
        cekSemuaPoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cekSemuaPoliActionPerformed(evt);
            }
        });
        panelisi1.add(cekSemuaPoli);

        internalFrame1.add(panelisi1, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnKeluar,TKd);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void tbBangsalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBangsalMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbBangsalMouseClicked

    private void tbBangsalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbBangsalKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbBangsalKeyPressed

private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
       tampil();
}//GEN-LAST:event_BtnCariActionPerformed

private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); 
            tampil();
            this.setCursor(Cursor.getDefaultCursor());
        }else{
            Valid.pindah(evt, TKd, Tgl1);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
        }
    }//GEN-LAST:event_TCariKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
           TCari.setText("");
           tampil();
    }//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnAllActionPerformed(null);
        }else{
            
        }
    }//GEN-LAST:event_BtnAllKeyPressed

    private void MnKunSemuaPoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnKunSemuaPoliActionPerformed
        if (akses.getkode().equals("Admin Utama") || akses.getpenyakit() == true) {
            if (tbBangsal.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Data kunjungan rawat jalan masih kosong...!!!");
                Tgl1.requestFocus();
            } else {
                ctkKunSemua();
            }
            
        } else {
            JOptionPane.showMessageDialog(null, "Untuk mencetak rekap lap. kunjungan semua poli/unit, silakan koordinasi dg. Inst. Rekam Medik...!!!");
            Tgl1.requestFocus();
        }  
    }//GEN-LAST:event_MnKunSemuaPoliActionPerformed

    private void MnKunCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnKunCBActionPerformed
        if (tbBangsal.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data kunjungan rawat jalan masih kosong...!!!");
            Tgl1.requestFocus();
        } else if (tbBangsal.getRowCount() != 0) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("periode", Tgl1.getSelectedItem() + " s.d " + Tgl2.getSelectedItem());
            Valid.MyReport("rptRekapKunJalanCB.jasper", "report", "::[ Laporan Rekap Total Kunjungan Pasien Rawat Jalan Berdasarkan Cara Bayar ]::",
                    " select a.png_jawab, ifnull(b.total,0) Pasien_Lama, ifnull(c.total,0) Pasien_Baru, (ifnull(b.total,0)+ifnull(c.total,0)) as total_LB from "
                    + " ((select png_jawab, kd_pj from penjab) as a left join (SELECT penjab.png_jawab,penjab.kd_pj, COUNT(penjab.kd_pj) as total "
                    + " FROM reg_periksa INNER JOIN pasien INNER JOIN poliklinik INNER JOIN dokter ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND reg_periksa.kd_poli = poliklinik.kd_poli AND reg_periksa.kd_dokter = dokter.kd_dokter INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " WHERE reg_periksa.status_lanjut = 'Ralan' AND reg_periksa.stts <> 'Batal' "
                    + " AND reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and reg_periksa.stts_daftar = 'Lama' "
                    + " GROUP BY penjab.kd_pj) as b on a.kd_pj = b.kd_pj left join (SELECT penjab.png_jawab,penjab.kd_pj, COUNT(penjab.kd_pj) as total "
                    + " FROM reg_periksa INNER JOIN pasien INNER JOIN poliklinik INNER JOIN dokter ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " AND reg_periksa.kd_poli = poliklinik.kd_poli AND reg_periksa.kd_dokter = dokter.kd_dokter "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " WHERE reg_periksa.status_lanjut = 'Ralan' AND reg_periksa.stts <> 'Batal' "
                    + " AND reg_periksa.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and reg_periksa.stts_daftar = 'Baru' "
                    + " GROUP BY penjab.kd_pj) as c on c.kd_pj = a.kd_pj) WHERE (ifnull(b.total,0)+ifnull(c.total,0))  > 0 ORDER BY a.png_jawab", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnKunCBActionPerformed

    private void kdpoliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdpoliKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            //            Sequel.cariIsi("select nm_poli from poliklinik where kd_poli=?", TPoli, kdpoli.getText());
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            //            BtnUnitActionPerformed(null);
        } else {
            //            Valid.pindah(evt, kddokter, TNoRM);
        }
    }//GEN-LAST:event_kdpoliKeyPressed

    private void BtnUnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUnitActionPerformed
        TPoli.setText("");
        kdpoli.setText("");

        akses.setform("DlgKunjunganRalan");

        poli.isCek();
        poli.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        poli.setLocationRelativeTo(internalFrame1);
        poli.setVisible(true);
    }//GEN-LAST:event_BtnUnitActionPerformed

    private void MnKunPerPoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnKunPerPoliActionPerformed
        if (kdpoli.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silakan pilih dulu salah satu poliklinik/unit yang akan dicetak laporannya...!!!");
            BtnUnit.requestFocus();
        } else if (tbBangsal.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data kunjungan rawat jalan masih kosong...!!!");
            Tgl1.requestFocus();
        } else {
            ctkKunPerPoli();
            Tgl1.requestFocus();
        }
    }//GEN-LAST:event_MnKunPerPoliActionPerformed

    private void cekSemuaPoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cekSemuaPoliActionPerformed
        if (cekSemuaPoli.isSelected() == true) {
            kdpoli.setText("");
            TPoli.setText("");
        } 
    }//GEN-LAST:event_cekSemuaPoliActionPerformed

    private void Tgl1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tgl1MouseClicked
        Tgl1.setEditable(false);
    }//GEN-LAST:event_Tgl1MouseClicked

    private void Tgl2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tgl2MouseClicked
        Tgl2.setEditable(false);
    }//GEN-LAST:event_Tgl2MouseClicked

    private void MnKunPoliPerBulanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnKunPoliPerBulanActionPerformed
        if (kdpoli.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silakan pilih dulu salah satu poliklinik/unit yang akan dicetak laporannya...!!!");
            BtnUnit.requestFocus();
        } else {
            ctkKunjunganPoliPerBulan();
            Tgl1.requestFocus();
        }
    }//GEN-LAST:event_MnKunPoliPerBulanActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        if (akses.getregistrasi() == true || akses.getpenyakit() == true) {
            BtnUnit.setEnabled(true);
            BtnCari.setEnabled(true);
            BtnAll.setEnabled(true);
            cekSemuaPoli.setSelected(true);
            cekSemuaPoli.setEnabled(true);
            kdpoli.setText("");
            TPoli.setText("");
            tampil();
        }
    }//GEN-LAST:event_formWindowOpened

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgKunjunganRalan dialog = new DlgKunjunganRalan(new javax.swing.JFrame(), true);
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
    private widget.Button BtnUnit;
    private javax.swing.JMenuItem MnKunCB;
    private javax.swing.JMenuItem MnKunPerPoli;
    private javax.swing.JMenuItem MnKunPoliPerBulan;
    private javax.swing.JMenuItem MnKunSemuaPoli;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    private widget.TextBox TKd;
    private widget.TextBox TPoli;
    private widget.Tanggal Tgl1;
    private widget.Tanggal Tgl2;
    private widget.CekBox cekSemuaPoli;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel19;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.TextBox kdpoli;
    private widget.Label label11;
    private widget.Label label18;
    private widget.panelisi panelGlass5;
    private widget.panelisi panelisi1;
    private widget.Table tbBangsal;
    private widget.TextBox userBerizin;
    // End of variables declaration//GEN-END:variables

    public void tampil(){        
        try{   
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); 
            Valid.tabelKosong(tabMode); 
            
            if (!kdpoli.getText().equals("")) {
                ps = koneksi.prepareStatement("select reg_periksa.no_reg,reg_periksa.no_rawat,reg_periksa.tgl_registrasi,reg_periksa.jam_reg,"
                        + "reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.no_rkm_medis,pasien.nm_pasien,poliklinik.nm_poli,"
                        + "reg_periksa.p_jawab,reg_periksa.almt_pj,reg_periksa.hubunganpj,reg_periksa.biaya_reg,pasien.jk,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) as umur,pasien.tgl_daftar "
                        + "from reg_periksa inner join dokter inner join pasien inner join poliklinik "
                        + "on reg_periksa.kd_dokter=dokter.kd_dokter "
                        + "and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                        + "and reg_periksa.kd_poli=poliklinik.kd_poli "
                        + "where reg_periksa.status_lanjut='Ralan' and reg_periksa.stts<>'Batal' and reg_periksa.tgl_registrasi between ? and ? and poliklinik.kd_poli = ? and "
                        + "(reg_periksa.status_lanjut='Ralan' and reg_periksa.stts<>'Batal' and reg_periksa.tgl_registrasi between ? and ? and dokter.nm_dokter like ? or "
                        + "reg_periksa.status_lanjut='Ralan' and reg_periksa.stts<>'Batal' and reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? or "
                        + "reg_periksa.status_lanjut='Ralan' and reg_periksa.stts<>'Batal' and reg_periksa.tgl_registrasi between ? and ? and pasien.nm_pasien like ? or "
                        + "reg_periksa.status_lanjut='Ralan' and reg_periksa.stts<>'Batal' and reg_periksa.tgl_registrasi between ? and ? and pasien.alamat like ?) "
                        + "order by reg_periksa.tgl_registrasi,reg_periksa.jam_reg");
            } else {
                ps = koneksi.prepareStatement("select reg_periksa.no_reg,reg_periksa.no_rawat,reg_periksa.tgl_registrasi,reg_periksa.jam_reg,"
                        + "reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.no_rkm_medis,pasien.nm_pasien,poliklinik.nm_poli,"
                        + "reg_periksa.p_jawab,reg_periksa.almt_pj,reg_periksa.hubunganpj,reg_periksa.biaya_reg,pasien.jk,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) as umur,pasien.tgl_daftar "
                        + "from reg_periksa inner join dokter inner join pasien inner join poliklinik "
                        + "on reg_periksa.kd_dokter=dokter.kd_dokter "
                        + "and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                        + "and reg_periksa.kd_poli=poliklinik.kd_poli "
                        + "where reg_periksa.status_lanjut='Ralan' and reg_periksa.stts<>'Batal' and reg_periksa.tgl_registrasi between ? and ? and poliklinik.kd_poli = ? or "
                        + "(reg_periksa.status_lanjut='Ralan' and reg_periksa.stts<>'Batal' and reg_periksa.tgl_registrasi between ? and ? and dokter.nm_dokter like ? or "
                        + "reg_periksa.status_lanjut='Ralan' and reg_periksa.stts<>'Batal' and reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? or "
                        + "reg_periksa.status_lanjut='Ralan' and reg_periksa.stts<>'Batal' and reg_periksa.tgl_registrasi between ? and ? and pasien.nm_pasien like ? or "
                        + "reg_periksa.status_lanjut='Ralan' and reg_periksa.stts<>'Batal' and reg_periksa.tgl_registrasi between ? and ? and pasien.alamat like ?) "
                        + "order by reg_periksa.tgl_registrasi,reg_periksa.jam_reg");
            }
            
            try {
                ps.setString(1,Valid.SetTgl(Tgl1.getSelectedItem()+""));
                ps.setString(2,Valid.SetTgl(Tgl2.getSelectedItem()+""));
                ps.setString(3,kdpoli.getText());
                ps.setString(4,Valid.SetTgl(Tgl1.getSelectedItem()+""));
                ps.setString(5,Valid.SetTgl(Tgl2.getSelectedItem()+""));
                ps.setString(6,"%"+TCari.getText().trim()+"%");
                ps.setString(7,Valid.SetTgl(Tgl1.getSelectedItem()+""));
                ps.setString(8,Valid.SetTgl(Tgl2.getSelectedItem()+""));
                ps.setString(9,"%"+TCari.getText().trim()+"%");
                ps.setString(10,Valid.SetTgl(Tgl1.getSelectedItem()+""));
                ps.setString(11,Valid.SetTgl(Tgl2.getSelectedItem()+""));
                ps.setString(12,"%"+TCari.getText().trim()+"%");
                ps.setString(13,Valid.SetTgl(Tgl1.getSelectedItem()+""));
                ps.setString(14,Valid.SetTgl(Tgl2.getSelectedItem()+""));
                ps.setString(15,"%"+TCari.getText().trim()+"%");
                rs=ps.executeQuery();
                i=1;   
                lama=0;baru=0;laki=0;per=0;
                while(rs.next()){
                    setbaru="";
                    setlama="";
                    if(rs.getString("tgl_registrasi").equals(rs.getString("tgl_daftar"))){
                        setbaru=rs.getString("no_rkm_medis");
                        baru++;
                    }else if(!rs.getString("tgl_registrasi").equals(rs.getString("tgl_daftar"))){
                        setlama=rs.getString("no_rkm_medis");
                        lama++;
                    }
                    umurlk="";
                    umurpr="";
                    switch (rs.getString("jk")) {
                        case "L":
                            umurlk=rs.getString("umur");
                            laki++;
                            break;
                        case "P":
                            umurpr=rs.getString("umur");
                            per++;
                            break;
                    }
                    diagnosa="";
                    ps2=koneksi.prepareStatement("select if(pemeriksaan_ralan.diagnosa='' or pemeriksaan_ralan.diagnosa is null,'- belum diisi -',pemeriksaan_ralan.diagnosa) diagnosa "+
                        "from reg_periksa inner join pemeriksaan_ralan on pemeriksaan_ralan.no_rawat=reg_periksa.no_rawat " +
                        "where pemeriksaan_ralan.no_rawat=? order by pemeriksaan_ralan.no_rawat desc limit 1");
                    try {
                        ps2.setString(1,rs.getString("no_rawat"));
                        rs2=ps2.executeQuery();
                        if(rs2.next()){
                            diagnosa=rs2.getString(1);
                        }
                    } catch (Exception e) {
                        System.out.println("laporan.DlgKunjunganRalan.tampil() 2 :"+e);
                    } finally{
                        if(rs2!=null){
                            rs2.close();
                        }
                        if(ps2!=null){
                            ps2.close();
                        }
                    }                        
                    tabMode.addRow(new Object[]{
                        i,setlama,setbaru,rs.getString("nm_pasien"),umurlk,umurpr,rs.getString("almt_pj"),diagnosa,rs.getString("nm_dokter")
                    });                
                    i++;
                }
                if(i>=2){
                    tabMode.addRow(new Object[]{
                        ">>",lama,baru,"",laki,per,"","",""
                    });
                }
            } catch (Exception e) {
                System.out.println("laporan.DlgKunjunganRalan.tampil() : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }       
            this.setCursor(Cursor.getDefaultCursor());
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
    }

    private void getData() {
        int row=tbBangsal.getSelectedRow();
        if(row!= -1){
            TKd.setText(tabMode.getValueAt(row,0).toString());
        }
    }

    public void UserValid() {
        userBerizin.setText(Sequel.cariIsi("SELECT nip FROM hak_akses_unit WHERE nip='" + akses.getkode() + "' "));

        if (akses.getkode().equals("Admin Utama")) {
            BtnUnit.setEnabled(true);
            BtnCari.setEnabled(true);
            BtnAll.setEnabled(true);
            cekSemuaPoli.setSelected(true);
            cekSemuaPoli.setEnabled(true);
            kdpoli.setText("");
            TPoli.setText("");
            tampil();
            
        } else if (!userBerizin.getText().equals("") || !akses.getkode().equals("Admin Utama")) {
            BtnUnit.setEnabled(false);
            BtnCari.setEnabled(true);
            BtnAll.setEnabled(false);
            cekSemuaPoli.setSelected(false);
            cekSemuaPoli.setEnabled(false);
            kdpoli.setText(Sequel.cariIsi("SELECT kode_unit FROM hak_akses_unit WHERE nip='" + akses.getkode() + "' "));
            TPoli.setText(Sequel.cariIsi("SELECT nm_poli FROM poliklinik WHERE kd_poli='" + kdpoli.getText() + "' "));
            tampil();
        }
    }
    
    public void emptText() {
        userBerizin.setText("");
    }
    
    public void ctkKunSemua() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("periode", Tgl1.getSelectedItem() + " s.d " + Tgl2.getSelectedItem());
        Valid.MyReport("rptKunjunganRalan.jasper", "report", "::[ Laporan Rekap Kunjungan Pasien Rawat Jalan Semua Poli/Unit ]::",
                " SELECT (SELECT count(x.no_rawat) Total  FROM (SELECT no_rawat FROM reg_periksa "
                + " WHERE status_lanjut = 'Ralan' AND stts <> 'Batal' AND tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') AS x) AS Tot_kun,"
                + " (SELECT count(tl.no_rawat) Total  FROM (SELECT no_rawat FROM reg_periksa "
                + " WHERE status_lanjut = 'Ralan' AND stts <> 'Batal' AND tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "'"
                + " AND stts_daftar = 'Lama') AS tl) AS Tot_Lama, "
                + " (SELECT count(tb.no_rawat) Total  FROM (SELECT no_rawat FROM reg_periksa "
                + " WHERE status_lanjut = 'Ralan' AND stts <> 'Batal' AND tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "'"
                + " AND stts_daftar = 'Baru') AS tb) AS Tot_Baru,"
                + " (SELECT count(tk.jk) Total  FROM (SELECT pl.jk FROM reg_periksa rl"
                + " inner join pasien pl on pl.no_rkm_medis=rl.no_rkm_medis"
                + " WHERE rl.status_lanjut = 'Ralan' AND rl.stts <> 'Batal' AND rl.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "'"
                + " AND pl.jk = 'L') AS tk) AS Tot_LK,"
                + " (SELECT count(tp.jk) Total  FROM (SELECT pp.jk FROM reg_periksa rp"
                + " inner join pasien pp on pp.no_rkm_medis=rp.no_rkm_medis"
                + " WHERE rp.status_lanjut = 'Ralan' AND rp.stts <> 'Batal' AND rp.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "'"
                + " AND pp.jk = 'P') AS tp) AS Tot_PR,"
                + " IF (a.stts_daftar = 'Lama', a.no_rkm_medis,'-') AS Lama,"
                + " IF (a.stts_daftar = 'Baru',	a.no_rkm_medis,'-') AS Baru, a.nm_pasien, IF(a.jk='L', a.umur,'-') LK,"
                + " IF(a.jk='P', a.umur,'-') PR, a.alamat, a.diagnosa_resum, a.nm_poli, DATE_FORMAT(a.tgl_registrasi,'%d/%m/%Y') tgl_kun, a.nm_dokter "
                + " FROM (SELECT r.no_rkm_medis, p.nm_pasien, CONCAT(r.umurdaftar,' ',r.sttsumur) umur, p.jk,"
                + " CONCAT(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat, IF(pr.diagnosa is NULL or pr.diagnosa = '','- belum diisi -',pr.diagnosa) diagnosa_resum, "
                + " pl.nm_poli, r.tgl_registrasi, d.nm_dokter,r.stts_daftar FROM reg_periksa r"
                + " INNER JOIN pasien p on p.no_rkm_medis = r.no_rkm_medis"
                + " INNER JOIN poliklinik pl ON pl.kd_poli = r.kd_poli"
                + " INNER JOIN kelurahan kl ON kl.kd_kel = p.kd_kel"
                + " INNER JOIN kecamatan kc ON kc.kd_kec = p.kd_kec"
                + " INNER JOIN kabupaten kb ON kb.kd_kab = p.kd_kab"
                + " INNER JOIN dokter d ON d.kd_dokter = r.kd_dokter"
                + " LEFT JOIN pemeriksaan_ralan pr ON pr.no_rawat = r.no_rawat"
                + " WHERE r.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                + " AND r.stts <> 'Batal' AND r.status_lanjut='Ralan' ORDER BY r.tgl_registrasi) as a", param);
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    public void ctkKunPerPoli() {
    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("periode", Tgl1.getSelectedItem() + " s.d " + Tgl2.getSelectedItem());
        Valid.MyReport("rptKunjunganRalanPerPoli.jasper", "report", "::[ Laporan Rekap Kunjungan Pasien Rawat Jalan Per Poli/Unit ]::",
                " SELECT (SELECT count(x.no_rawat) Total  FROM (SELECT no_rawat FROM reg_periksa "
                + " WHERE status_lanjut = 'Ralan' AND stts <> 'Batal' AND tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and kd_poli='" + kdpoli.getText() + "') AS x) AS Tot_kun,"
                + " (SELECT count(tl.no_rawat) Total  FROM (SELECT no_rawat FROM reg_periksa "
                + " WHERE status_lanjut = 'Ralan' AND stts <> 'Batal' AND tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "'"
                + " AND stts_daftar = 'Lama' and kd_poli='" + kdpoli.getText() + "') AS tl) AS Tot_Lama, "
                + " (SELECT count(tb.no_rawat) Total  FROM (SELECT no_rawat FROM reg_periksa "
                + " WHERE status_lanjut = 'Ralan' AND stts <> 'Batal' AND tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "'"
                + " AND stts_daftar = 'Baru' and kd_poli='" + kdpoli.getText() + "') AS tb) AS Tot_Baru,"
                + " (SELECT count(tk.jk) Total  FROM (SELECT pl.jk FROM reg_periksa rl"
                + " inner join pasien pl on pl.no_rkm_medis=rl.no_rkm_medis"
                + " WHERE rl.status_lanjut = 'Ralan' AND rl.stts <> 'Batal' AND rl.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "'"
                + " AND pl.jk = 'L' and kd_poli='" + kdpoli.getText() + "') AS tk) AS Tot_LK,"
                + " (SELECT count(tp.jk) Total  FROM (SELECT pp.jk FROM reg_periksa rp"
                + " inner join pasien pp on pp.no_rkm_medis=rp.no_rkm_medis"
                + " WHERE rp.status_lanjut = 'Ralan' AND rp.stts <> 'Batal' AND rp.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "'"
                + " AND pp.jk = 'P' and kd_poli='" + kdpoli.getText() + "') AS tp) AS Tot_PR,"
                + " IF (a.stts_daftar = 'Lama', a.no_rkm_medis,'-') AS Lama,"
                + " IF (a.stts_daftar = 'Baru',	a.no_rkm_medis,'-') AS Baru, a.nm_pasien, IF(a.jk='L', a.umur,'-') LK,"
                + " IF(a.jk='P', a.umur,'-') PR, a.alamat, a.diagnosa_resum, a.nm_poli, DATE_FORMAT(a.tgl_registrasi,'%d/%m/%Y') tgl_kun, a.nm_dokter "
                + " FROM (SELECT r.no_rkm_medis, p.nm_pasien, CONCAT(r.umurdaftar,' ',r.sttsumur) umur, p.jk,"
                + " CONCAT(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat, IF(pr.diagnosa is NULL or pr.diagnosa = '','- belum diisi -',pr.diagnosa) diagnosa_resum, "
                + " pl.nm_poli, r.tgl_registrasi, d.nm_dokter,r.stts_daftar FROM reg_periksa r"
                + " INNER JOIN pasien p on p.no_rkm_medis = r.no_rkm_medis"
                + " INNER JOIN poliklinik pl ON pl.kd_poli = r.kd_poli"
                + " INNER JOIN kelurahan kl ON kl.kd_kel = p.kd_kel"
                + " INNER JOIN kecamatan kc ON kc.kd_kec = p.kd_kec"
                + " INNER JOIN kabupaten kb ON kb.kd_kab = p.kd_kab"
                + " INNER JOIN dokter d ON d.kd_dokter = r.kd_dokter"
                + " LEFT JOIN pemeriksaan_ralan pr ON pr.no_rawat = r.no_rawat"
                + " WHERE r.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                + " AND r.stts <> 'Batal' AND r.status_lanjut='Ralan' and r.kd_poli='" + kdpoli.getText() + "' ORDER BY r.tgl_registrasi) as a", param);
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    private void ctkKunjunganPoliPerBulan() {
    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("periode", "POLIKLINIK/UNIT " + TPoli.getText() + " PERIODE TGL. " + Tgl1.getSelectedItem()+" S.D "+Tgl2.getSelectedItem());
        Valid.MyReport("rptrekapkunpoliperbulan.jasper", "report", "::[ Laporan Rekap Kunjungan Poli Per Bulan, Kelompok Pasien, & Jenis Kelamin ]::",
                " SELECT DATE_FORMAT(r.tgl_registrasi,'%M') as Bulan, COUNT(case when r.kd_pj like 'U%' then 1 end) as Umum, "
                + "COUNT(case when r.kd_pj like 'B%' then 1 end) as BPJS, COUNT(case when r.kd_pj like 'D%' then 1 end) as Jamkesda, "
                + "COUNT(case when r.kd_pj like 'A%' then 1 end) as Asuransi, COUNT(case when (r.kd_pj not like 'U%' AND r.kd_pj not like 'B%' AND "
                + "r.kd_pj not like 'D%' AND r.kd_pj not like 'A%')then 1 end) as Lainnya, COUNT(case when pa.jk ='P' then 1 end) as Perempuan, "
                + "COUNT(case when pa.jk ='L' then 1 end) as Lelaki, (COUNT(case when pa.jk ='P' then 1 end) + COUNT(case when pa.jk ='L' then 1 end)) total "
                + "FROM reg_periksa r INNER JOIN poliklinik p ON r.kd_poli = p.kd_poli INNER JOIN pasien pa ON r.no_rkm_medis = pa.no_rkm_medis "
                + "WHERE r.tgl_registrasi between '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                + "AND r.status_lanjut = 'Ralan' AND p.kd_poli = '" + kdpoli.getText() + "' GROUP BY MONTH (r.tgl_registrasi)", param);
        this.setCursor(Cursor.getDefaultCursor());
    }

}
