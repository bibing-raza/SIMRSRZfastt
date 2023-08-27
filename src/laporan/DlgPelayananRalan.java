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
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import simrskhanza.DlgCariPoli;

/**
 *
 * @author perpustakaan
 */
public final class DlgPelayananRalan extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i = 0, limabelas = 0, tigapuluh = 0, satujam = 0, lebihsatujam = 0;
    private DlgCariPoli poli = new DlgCariPoli(null, false);
    private String kdPoli = "", userBerizin= "";
    
    /** Creates new form DlgLhtBiaya
     * @param parent
     * @param modal */
    public DlgPelayananRalan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8,1);
        setSize(885,674);

        Object[] rowRwJlDr={"No.RM","Nama Pasien","Tgl. Reg.","Jam Daftar","Poliklinik/Unit Tujuan","Nama Dokter",
            "Tgl. Rawat","Jam Tindkn. Medis","Wkt. Tunggu (menit)","Ket. Tindakan Medis","Jenis Daftar","Cara Bayar"};
        tabMode=new DefaultTableModel(null,rowRwJlDr){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbDurasi.setModel(tabMode);
        //tbBangsal.setDefaultRenderer(Object.class, new WarnaTable(jPanel2.getBackground(),tbBangsal.getBackground()));
        tbDurasi.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbDurasi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 12; i++) {
            TableColumn column = tbDurasi.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(70);
            } else if (i == 1) {
                column.setPreferredWidth(200);
            } else if (i == 2) {
                column.setPreferredWidth(75);
            } else if (i == 3) {
                column.setPreferredWidth(70);
            } else if (i == 4) {
                column.setPreferredWidth(200);
            } else if (i == 5) {
                column.setPreferredWidth(250);
            } else if (i == 6) {
                column.setPreferredWidth(75);
            } else if (i == 7) {
                column.setPreferredWidth(100);
            } else if (i == 8) {
                column.setPreferredWidth(115);
            } else if (i == 9) {
                column.setPreferredWidth(110);
            } else if (i == 10) {
                column.setPreferredWidth(70);
            } else if (i == 11) {
                column.setPreferredWidth(130);
            }
        }
        tbDurasi.setDefaultRenderer(Object.class, new WarnaTable());

        TKd.setDocument(new batasInput((byte)20).getKata(TKd));
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
        
        poli.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgPelayananRalan")) {
                    if (poli.getTable().getSelectedRow() != -1) {
                        kdPoli = poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 0).toString();
                        nmPoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 1).toString());
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
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbDurasi = new widget.Table();
        panelGlass5 = new widget.panelisi();
        label11 = new widget.Label();
        Tgl1 = new widget.Tanggal();
        label18 = new widget.Label();
        Tgl2 = new widget.Tanggal();
        jLabel8 = new widget.Label();
        nmPoli = new widget.TextBox();
        BtnUnit = new widget.Button();
        cekSemuaPoli = new widget.CekBox();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        jLabel7 = new widget.Label();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();

        TKd.setForeground(new java.awt.Color(255, 255, 255));
        TKd.setName("TKd"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Data Durasi Waktu Pelayanan Rawat Jalan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbDurasi.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbDurasi.setName("tbDurasi"); // NOI18N
        tbDurasi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDurasiMouseClicked(evt);
            }
        });
        tbDurasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDurasiKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbDurasi);

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
        Tgl1.setForeground(new java.awt.Color(0, 0, 0));
        Tgl1.setDisplayFormat("dd-MM-yyyy");
        Tgl1.setName("Tgl1"); // NOI18N
        Tgl1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass5.add(Tgl1);

        label18.setForeground(new java.awt.Color(0, 0, 0));
        label18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label18.setText("s.d.");
        label18.setName("label18"); // NOI18N
        label18.setPreferredSize(new java.awt.Dimension(25, 23));
        panelGlass5.add(label18);

        Tgl2.setBackground(new java.awt.Color(245, 250, 240));
        Tgl2.setEditable(false);
        Tgl2.setForeground(new java.awt.Color(0, 0, 0));
        Tgl2.setDisplayFormat("dd-MM-yyyy");
        Tgl2.setName("Tgl2"); // NOI18N
        Tgl2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass5.add(Tgl2);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Poliklinik/Unit :");
        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass5.add(jLabel8);

        nmPoli.setEditable(false);
        nmPoli.setForeground(new java.awt.Color(0, 0, 0));
        nmPoli.setName("nmPoli"); // NOI18N
        nmPoli.setPreferredSize(new java.awt.Dimension(190, 23));
        nmPoli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nmPoliKeyPressed(evt);
            }
        });
        panelGlass5.add(nmPoli);

        BtnUnit.setForeground(new java.awt.Color(0, 0, 0));
        BtnUnit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnUnit.setMnemonic('4');
        BtnUnit.setToolTipText("ALt+4");
        BtnUnit.setName("BtnUnit"); // NOI18N
        BtnUnit.setPreferredSize(new java.awt.Dimension(28, 26));
        BtnUnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUnitActionPerformed(evt);
            }
        });
        panelGlass5.add(BtnUnit);

        cekSemuaPoli.setBackground(new java.awt.Color(255, 255, 250));
        cekSemuaPoli.setBorder(null);
        cekSemuaPoli.setForeground(new java.awt.Color(0, 0, 0));
        cekSemuaPoli.setSelected(true);
        cekSemuaPoli.setText("Semua Poli/Unit");
        cekSemuaPoli.setBorderPainted(true);
        cekSemuaPoli.setBorderPaintedFlat(true);
        cekSemuaPoli.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cekSemuaPoli.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cekSemuaPoli.setName("cekSemuaPoli"); // NOI18N
        cekSemuaPoli.setOpaque(false);
        cekSemuaPoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cekSemuaPoliActionPerformed(evt);
            }
        });
        panelGlass5.add(cekSemuaPoli);

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
        BtnCari.setToolTipText("Alt+2");
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(26, 23));
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
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(26, 23));
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
        panelGlass5.add(BtnPrint);

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

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
        } else if (tabMode.getRowCount() != 0) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Sequel.AutoComitFalse();
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("periode", "Periode Tgl. " + Tgl1.getSelectedItem() + " S.D." + Tgl2.getSelectedItem());
            param.put("limabelas", "" + limabelas);
            param.put("tigapuluh", "" + tigapuluh);
            param.put("satujam", "" + satujam);
            param.put("lebihsatujam", "" + lebihsatujam);
            if (kdPoli.equals("")) {
                param.put("judul", "Laporan Durasi Waktu Pelayanan Pasien Rawat Jalan SEMUA POLIKLINIK/UNIT");
                Valid.MyReport("rptPelayananRalan.jasper", "report", "::[ Laporan Durasi Waktu Pelayanan Rawat Jalan ]::",
                        "select r.no_rkm_medis, ps.nm_pasien, DATE_FORMAT(r.tgl_registrasi,'%d-%m-%Y') tgl_registrasi,r.jam_reg, "
                        + "pl.nm_poli, d.nm_dokter,ifnull(DATE_FORMAT(n.tgl_perawatan,'%d-%m-%Y'),ifnull(date_format(p.tgl_perawatan,'%d-%m-%Y'),date_format(r.tgl_registrasi,'%d-%m-%Y'))) tgl_rawat, "
                        + "ifnull(n.jam_rawat,ifnull(p.jam_rawat,'00:00:00')) jam_beri_tindk,"
                        + "TIMESTAMPDIFF(MINUTE,concat( r.tgl_registrasi, ' ', r.jam_reg ),concat(ifnull(n.tgl_perawatan,ifnull(p.tgl_perawatan, r.tgl_registrasi )),' ',ifnull(n.jam_rawat,ifnull( p.jam_rawat, r.jam_reg )))) wkt_tunggu,"
                        + "if(ifnull(n.jam_rawat,ifnull(p.jam_rawat,'-')) = '-','Belum Ada','Sudah Ada') Ket_tindkn_medis, "
                        + "if(ifnull(b.kd_booking,'-') = '-','Offline','Online') Jenis_daftar, pj.png_jawab "
                        + "from reg_periksa r INNER JOIN pasien ps on ps.no_rkm_medis=r.no_rkm_medis "
                        + "INNER JOIN poliklinik pl on pl.kd_poli=r.kd_poli INNER JOIN dokter d on d.kd_dokter=r.kd_dokter "
                        + "INNER JOIN penjab pj on pj.kd_pj=r.kd_pj "
                        + "left join booking_registrasi b on b.no_rawat = r.no_rawat left join pemeriksaan_ralan n on r.no_rawat = n.no_rawat "
                        + "left join pemeriksaan_ralan_petugas p on p.no_rawat = r.no_rawat "
                        + "where r.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and r.stts_daftar<>'batal' order by wkt_tunggu desc", param);
            } else {
                param.put("judul", "Laporan Durasi Waktu Pelayanan Pasien Rawat Jalan POLIKLINIK/UNIT " + nmPoli.getText());
                Valid.MyReport("rptPelayananRalan.jasper", "report", "::[ Laporan Durasi Waktu Pelayanan Rawat Jalan ]::",
                        "select r.no_rkm_medis, ps.nm_pasien, DATE_FORMAT(r.tgl_registrasi,'%d-%m-%Y') tgl_registrasi,r.jam_reg, "
                        + "pl.nm_poli, d.nm_dokter,ifnull(DATE_FORMAT(n.tgl_perawatan,'%d-%m-%Y'),ifnull(date_format(p.tgl_perawatan,'%d-%m-%Y'),date_format(r.tgl_registrasi,'%d-%m-%Y'))) tgl_rawat,"
                        + "ifnull(n.jam_rawat,ifnull(p.jam_rawat,'00:00:00')) jam_beri_tindk,"
                        + "TIMESTAMPDIFF(MINUTE,concat( r.tgl_registrasi, ' ', r.jam_reg ),concat(ifnull(n.tgl_perawatan,ifnull(p.tgl_perawatan, r.tgl_registrasi )),' ',ifnull(n.jam_rawat,ifnull( p.jam_rawat, r.jam_reg )))) wkt_tunggu,"
                        + "if(ifnull(n.jam_rawat,ifnull(p.jam_rawat,'-')) = '-','Belum Ada','Sudah Ada') Ket_tindkn_medis, "
                        + "if(ifnull(b.kd_booking,'-') = '-','Offline','Online') Jenis_daftar, pj.png_jawab "
                        + "from reg_periksa r INNER JOIN pasien ps on ps.no_rkm_medis=r.no_rkm_medis "
                        + "INNER JOIN poliklinik pl on pl.kd_poli=r.kd_poli INNER JOIN dokter d on d.kd_dokter=r.kd_dokter "
                        + "INNER JOIN penjab pj on pj.kd_pj=r.kd_pj "
                        + "left join booking_registrasi b on b.no_rawat = r.no_rawat left join pemeriksaan_ralan n on r.no_rawat = n.no_rawat "
                        + "left join pemeriksaan_ralan_petugas p on p.no_rawat = r.no_rawat "
                        + "where r.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                        + "and r.kd_poli = '" + kdPoli + "' and r.stts_daftar<>'batal' order by wkt_tunggu desc", param);
            }
            this.setCursor(Cursor.getDefaultCursor());
        }       
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPrintActionPerformed(null);
        }else{
            //Valid.pindah(evt, BtnHapus, BtnAll);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnKeluar,TKd);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void tbDurasiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDurasiMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbDurasiMouseClicked

    private void tbDurasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDurasiKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbDurasiKeyPressed

private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
    if (kdPoli.equals("") && (cekSemuaPoli.isSelected() == false)) {
        JOptionPane.showMessageDialog(null, "Pilih dulu salah satu atau semua poliklinik/unit nya dulu..!!!!");
    } else {
        tampil();
    }
}//GEN-LAST:event_BtnCariActionPerformed

private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); 
            tampil();
            this.setCursor(Cursor.getDefaultCursor());
        }else{
            Valid.pindah(evt, TKd, BtnPrint);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

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

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        tampil();

    }//GEN-LAST:event_formWindowActivated

    private void nmPoliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nmPoliKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nmPoliKeyPressed

    private void BtnUnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUnitActionPerformed
        nmPoli.setText("");
        kdPoli = "";
        cekSemuaPoli.setSelected(false);
        
        akses.setform("DlgPelayananRalan");
        poli.isCek();
        poli.setSize(1045, 467);
        poli.setLocationRelativeTo(internalFrame1);
        poli.setVisible(true);
    }//GEN-LAST:event_BtnUnitActionPerformed

    private void cekSemuaPoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cekSemuaPoliActionPerformed
        if (cekSemuaPoli.isSelected() == true) {
            kdPoli = "";
            nmPoli.setText("");
        }
    }//GEN-LAST:event_cekSemuaPoliActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgPelayananRalan dialog = new DlgPelayananRalan(new javax.swing.JFrame(), true);
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
    private widget.Button BtnPrint;
    private widget.Button BtnUnit;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    private widget.TextBox TKd;
    private widget.Tanggal Tgl1;
    private widget.Tanggal Tgl2;
    private widget.CekBox cekSemuaPoli;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label label11;
    private widget.Label label18;
    private widget.TextBox nmPoli;
    private widget.panelisi panelGlass5;
    private widget.Table tbDurasi;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode);
        limabelas = 0;
        tigapuluh = 0;
        satujam = 0;
        lebihsatujam = 0;
        
        try {
            if (kdPoli.equals("")) {
                ps = koneksi.prepareStatement("select r.no_rkm_medis, ps.nm_pasien, DATE_FORMAT(r.tgl_registrasi,'%d-%m-%Y') tgl_registrasi,r.jam_reg, "
                        + "pl.nm_poli, d.nm_dokter,ifnull(DATE_FORMAT(n.tgl_perawatan,'%d-%m-%Y'),ifnull(date_format(p.tgl_perawatan,'%d-%m-%Y'),date_format(r.tgl_registrasi,'%d-%m-%Y'))) tgl_rawat, "
                        + "ifnull(n.jam_rawat,ifnull(p.jam_rawat,'00:00:00')) jam_beri_tindk,"
                        + "TIMESTAMPDIFF(MINUTE,concat( r.tgl_registrasi, ' ', r.jam_reg ),concat(ifnull(n.tgl_perawatan,ifnull(p.tgl_perawatan, r.tgl_registrasi )),' ',ifnull(n.jam_rawat,ifnull( p.jam_rawat, r.jam_reg )))) wkt_tunggu,"
                        + "if(ifnull(n.jam_rawat,ifnull(p.jam_rawat,'-')) = '-','Belum Ada','Sudah Ada') Ket_tindkn_medis, "
                        + "if(ifnull(b.kd_booking,'-') = '-','Offline','Online') Jenis_daftar, pj.png_jawab "
                        + "from reg_periksa r INNER JOIN pasien ps on ps.no_rkm_medis=r.no_rkm_medis "
                        + "INNER JOIN poliklinik pl on pl.kd_poli=r.kd_poli INNER JOIN dokter d on d.kd_dokter=r.kd_dokter "
                        + "INNER JOIN penjab pj on pj.kd_pj=r.kd_pj "
                        + "left join booking_registrasi b on b.no_rawat = r.no_rawat left join pemeriksaan_ralan n on r.no_rawat = n.no_rawat "
                        + "left join pemeriksaan_ralan_petugas p on p.no_rawat = r.no_rawat "
                        + "where r.tgl_registrasi BETWEEN ? and ? and r.stts_daftar<>'batal' and r.no_rkm_medis like ? or "
                        + "r.tgl_registrasi BETWEEN ? and ? and r.stts_daftar<>'batal' and ps.nm_pasien like ? or "
                        + "r.tgl_registrasi BETWEEN ? and ? and r.stts_daftar<>'batal' and d.nm_dokter like ? or "
                        + "r.tgl_registrasi BETWEEN ? and ? and r.stts_daftar<>'batal' and if(ifnull(n.jam_rawat,ifnull(p.jam_rawat,'-')) = '-','Belum Ada','Sudah Ada') like ? or "
                        + "r.tgl_registrasi BETWEEN ? and ? and r.stts_daftar<>'batal' and if(ifnull(b.kd_booking,'-') = '-','Offline','Online') like ? or "
                        + "r.tgl_registrasi BETWEEN ? and ? and r.stts_daftar<>'batal' and pj.png_jawab like ? order by wkt_tunggu desc");
                
            } else if (!kdPoli.equals("")) {
                ps = koneksi.prepareStatement("select r.no_rkm_medis, ps.nm_pasien, DATE_FORMAT(r.tgl_registrasi,'%d-%m-%Y') tgl_registrasi,r.jam_reg, "
                        + "pl.nm_poli, d.nm_dokter,ifnull(DATE_FORMAT(n.tgl_perawatan,'%d-%m-%Y'),ifnull(date_format(p.tgl_perawatan,'%d-%m-%Y'),date_format(r.tgl_registrasi,'%d-%m-%Y'))) tgl_rawat,"
                        + "ifnull(n.jam_rawat,ifnull(p.jam_rawat,'00:00:00')) jam_beri_tindk,"
                        + "TIMESTAMPDIFF(MINUTE,concat( r.tgl_registrasi, ' ', r.jam_reg ),concat(ifnull(n.tgl_perawatan,ifnull(p.tgl_perawatan, r.tgl_registrasi )),' ',ifnull(n.jam_rawat,ifnull( p.jam_rawat, r.jam_reg )))) wkt_tunggu,"
                        + "if(ifnull(n.jam_rawat,ifnull(p.jam_rawat,'-')) = '-','Belum Ada','Sudah Ada') Ket_tindkn_medis, "
                        + "if(ifnull(b.kd_booking,'-') = '-','Offline','Online') Jenis_daftar, pj.png_jawab "
                        + "from reg_periksa r INNER JOIN pasien ps on ps.no_rkm_medis=r.no_rkm_medis "
                        + "INNER JOIN poliklinik pl on pl.kd_poli=r.kd_poli INNER JOIN dokter d on d.kd_dokter=r.kd_dokter "
                        + "INNER JOIN penjab pj on pj.kd_pj=r.kd_pj "
                        + "left join booking_registrasi b on b.no_rawat = r.no_rawat left join pemeriksaan_ralan n on r.no_rawat = n.no_rawat "
                        + "left join pemeriksaan_ralan_petugas p on p.no_rawat = r.no_rawat "
                        + "where r.tgl_registrasi BETWEEN ? and ? and r.kd_poli = ? and r.stts_daftar<>'batal' and r.no_rkm_medis like ? or "
                        + "r.tgl_registrasi BETWEEN ? and ? and r.kd_poli = ? and r.stts_daftar<>'batal' and ps.nm_pasien like ? or "
                        + "r.tgl_registrasi BETWEEN ? and ? and r.kd_poli = ? and r.stts_daftar<>'batal' and d.nm_dokter like ? or "
                        + "r.tgl_registrasi BETWEEN ? and ? and r.kd_poli = ? and r.stts_daftar<>'batal' and if(ifnull(n.jam_rawat,ifnull(p.jam_rawat,'-')) = '-','Belum Ada','Sudah Ada') like ? or "
                        + "r.tgl_registrasi BETWEEN ? and ? and r.kd_poli = ? and r.stts_daftar<>'batal' and if(ifnull(b.kd_booking,'-') = '-','Offline','Online') like ? or "
                        + "r.tgl_registrasi BETWEEN ? and ? and r.kd_poli = ? and r.stts_daftar<>'batal' and pj.png_jawab like ? order by wkt_tunggu desc");
            }
            
            if (kdPoli.equals("")) {
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
                ps.setString(16, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(17, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                ps.setString(18, "%" + TCari.getText() + "%");                
                                
            } else if (!kdPoli.equals("")) {
                ps.setString(1, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                ps.setString(3, kdPoli);
                ps.setString(4, "%" + TCari.getText() + "%");                
                ps.setString(5, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(6, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                ps.setString(7, kdPoli);
                ps.setString(8, "%" + TCari.getText() + "%");                
                ps.setString(9, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(10, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                ps.setString(11, kdPoli);
                ps.setString(12, "%" + TCari.getText() + "%");                
                ps.setString(13, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(14, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                ps.setString(15, kdPoli);
                ps.setString(16, "%" + TCari.getText() + "%");                
                ps.setString(17, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(18, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                ps.setString(19, kdPoli);
                ps.setString(20, "%" + TCari.getText() + "%");                
                ps.setString(21, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(22, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                ps.setString(23, kdPoli);
                ps.setString(24, "%" + TCari.getText() + "%");                
            }

            try {
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new Object[]{
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("tgl_registrasi"),
                        rs.getString("jam_reg"),
                        rs.getString("nm_poli"),
                        rs.getString("nm_dokter"),
                        rs.getString("tgl_rawat"),
                        rs.getString("jam_beri_tindk"),
                        rs.getString("wkt_tunggu"),
                        rs.getString("Ket_tindkn_medis"),
                        rs.getString("Jenis_daftar"),
                        rs.getString("png_jawab")
                    });

                    if (rs.getDouble("wkt_tunggu") <= 15) {
                        limabelas++;
                    } else if ((rs.getDouble("wkt_tunggu") > 15) && (rs.getDouble("wkt_tunggu") <= 30)) {
                        tigapuluh++;
                    } else if ((rs.getDouble("wkt_tunggu") > 30) && (rs.getDouble("wkt_tunggu") <= 60)) {
                        satujam++;
                    } else if (rs.getDouble("wkt_tunggu") > 60) {
                        lebihsatujam++;
                    }
                }

                tabMode.addRow(new Object[]{
                    "","","","Keterangan :", "0 s.d 15 Menit", ": " + limabelas + " Orang pasien", "", "", "", ""
                });
                tabMode.addRow(new Object[]{
                    "","","","", "> 15 s.d <=30 Menit", ": " + tigapuluh + " Orang pasien", "", "", "", ""
                });
                tabMode.addRow(new Object[]{
                    "","","","", "> 30 s.d <=60 Menit", ": " + satujam + " Orang pasien", "", "", "", ""
                });
                tabMode.addRow(new Object[]{
                    "","","","", "> 60 Menit", ": " + lebihsatujam + " Orang pasien", "", "", "", ""
                });

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
    }

    private void getData() {
        int row=tbDurasi.getSelectedRow();
        if(row!= -1){
            TKd.setText(tabMode.getValueAt(row,0).toString());
        }
    }
    
    public void UserValid() {
        userBerizin = "";
        userBerizin = Sequel.cariIsi("SELECT nip FROM hak_akses_unit WHERE nip='" + akses.getkode() + "' ");

        if (akses.getkode().equals("Admin Utama") || akses.getpenyakit() == true) {
            BtnUnit.setEnabled(true);
            BtnCari.setEnabled(true);
            BtnAll.setEnabled(true);
            cekSemuaPoli.setSelected(true);
            cekSemuaPoli.setEnabled(true);
            kdPoli = "";
            nmPoli.setText("");
            tampil();
            
        } else if (!userBerizin.equals("") || !akses.getkode().equals("Admin Utama")) {
            BtnUnit.setEnabled(false);
            BtnCari.setEnabled(true);
            BtnAll.setEnabled(false);
            cekSemuaPoli.setSelected(false);
            cekSemuaPoli.setEnabled(false);
            kdPoli = Sequel.cariIsi("SELECT kode_unit FROM hak_akses_unit WHERE nip='" + akses.getkode() + "' ");
            nmPoli.setText(Sequel.cariIsi("SELECT nm_poli FROM poliklinik WHERE kd_poli='" + kdPoli + "' "));
            tampil();
        }
    }
}
