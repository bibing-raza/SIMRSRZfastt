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
import fungsi.akses;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author dosen
 */
public class RMGrafikPemantauanHarian24Jam extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1;
    private final Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private final validasi Valid = new validasi();
    private PreparedStatement ps1, ps2;
    private ResultSet rs, rs1, rs2;

    /** Creates new form DlgSpesialis
     * @param parent
     * @param modal */
    public RMGrafikPemantauanHarian24Jam(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        tabMode = new DefaultTableModel(null, new Object[]{
            "Tgl. Pantau", "Jam", "Nadi", "Suhu", "GCS", "Kesadaran", "Tensi", "Frek. Nafas", "SPO2", "Makan Minum", 
            "NGT", "Total Parental", "Total Intake", "Urine", "NGT Darah", "Drain", "Muntah", "BAB", "IWL",
            "Total Output", "Balance", "tglpantau", "kdpantau"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbIntake.setModel(tabMode);
        tbIntake.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbIntake.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 23; i++) {
            TableColumn column = tbIntake.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(75);
            } else if (i == 1) {
                column.setPreferredWidth(30);
            } else if (i == 2) {
                column.setPreferredWidth(50);
            } else if (i == 3) {
                column.setPreferredWidth(50);
            } else if (i == 4) {
                column.setPreferredWidth(45);
            } else if (i == 5) {
                column.setPreferredWidth(75);
            } else if (i == 6) {
                column.setPreferredWidth(45);
            } else if (i == 7) {
                column.setPreferredWidth(75);
            } else if (i == 8) {
                column.setPreferredWidth(45);
            } else if (i == 9) {
                column.setPreferredWidth(80);
            } else if (i == 10) {
                column.setPreferredWidth(45);
            } else if (i == 11) {
                column.setPreferredWidth(85);
            } else if (i == 12) {
                column.setPreferredWidth(80);
            } else if (i == 13) {
                column.setPreferredWidth(50);
            } else if (i == 14) {
                column.setPreferredWidth(70);
            } else if (i == 15) {
                column.setPreferredWidth(50);
            } else if (i == 16) {
                column.setPreferredWidth(60);
            } else if (i == 17) {
                column.setPreferredWidth(40);
            } else if (i == 18) {
                column.setPreferredWidth(40);
            } else if (i == 19) {
                column.setPreferredWidth(80);
            } else if (i == 20) {
                column.setPreferredWidth(60);
            } else if (i == 21) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 22) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } 
        }
        tbIntake.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1 = new DefaultTableModel(null, new Object[]{
            "Tanggal", "Jam", "Nama Obat", "Jml. Beri (cc)"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbParental.setModel(tabMode1);
        tbParental.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbParental.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 4; i++) {
            TableColumn column = tbParental.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(70);
            } else if (i == 1) {
                column.setPreferredWidth(30);
            } else if (i == 2) {
                column.setPreferredWidth(270);
            } else if (i == 3) {
                column.setPreferredWidth(80);
            }
        }
        tbParental.setDefaultRenderer(Object.class, new WarnaTable());
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
        panelBiasa4 = new widget.PanelBiasa();
        jLabel3 = new widget.Label();
        TNoRW = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TNmPasien = new widget.TextBox();
        jLabel13 = new widget.Label();
        nmUnit = new widget.TextBox();
        jLabel4 = new widget.Label();
        TtglMasuk = new widget.TextBox();
        panelBiasa3 = new widget.PanelBiasa();
        panelGlass5 = new widget.panelisi();
        jLabel33 = new widget.Label();
        tglPantau = new widget.Tanggal();
        jLabel34 = new widget.Label();
        BtnGrafik = new widget.Button();
        BtnKeluar3 = new widget.Button();
        panelBiasa5 = new widget.PanelBiasa();
        Scroll1 = new widget.ScrollPane();
        tbIntake = new widget.Table();
        Scroll2 = new widget.ScrollPane();
        tbParental = new widget.Table();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Grafik Pemantauan Harian Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelBiasa4.setMinimumSize(new java.awt.Dimension(2, 200));
        panelBiasa4.setName("panelBiasa4"); // NOI18N
        panelBiasa4.setPreferredSize(new java.awt.Dimension(2, 100));
        panelBiasa4.setLayout(null);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Pasien :");
        jLabel3.setName("jLabel3"); // NOI18N
        panelBiasa4.add(jLabel3);
        jLabel3.setBounds(0, 10, 103, 23);

        TNoRW.setEditable(false);
        TNoRW.setForeground(new java.awt.Color(0, 0, 0));
        TNoRW.setName("TNoRW"); // NOI18N
        panelBiasa4.add(TNoRW);
        TNoRW.setBounds(107, 10, 122, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        panelBiasa4.add(TNoRM);
        TNoRM.setBounds(233, 10, 70, 23);

        TNmPasien.setEditable(false);
        TNmPasien.setForeground(new java.awt.Color(0, 0, 0));
        TNmPasien.setName("TNmPasien"); // NOI18N
        panelBiasa4.add(TNmPasien);
        TNmPasien.setBounds(308, 10, 291, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Rg. Rawat :");
        jLabel13.setName("jLabel13"); // NOI18N
        panelBiasa4.add(jLabel13);
        jLabel13.setBounds(0, 38, 103, 23);

        nmUnit.setEditable(false);
        nmUnit.setForeground(new java.awt.Color(0, 0, 0));
        nmUnit.setName("nmUnit"); // NOI18N
        panelBiasa4.add(nmUnit);
        nmUnit.setBounds(107, 38, 492, 23);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Tgl. Masuk :");
        jLabel4.setName("jLabel4"); // NOI18N
        panelBiasa4.add(jLabel4);
        jLabel4.setBounds(0, 66, 103, 23);

        TtglMasuk.setEditable(false);
        TtglMasuk.setForeground(new java.awt.Color(0, 0, 0));
        TtglMasuk.setName("TtglMasuk"); // NOI18N
        panelBiasa4.add(TtglMasuk);
        TtglMasuk.setBounds(107, 66, 210, 23);

        internalFrame1.add(panelBiasa4, java.awt.BorderLayout.PAGE_START);

        panelBiasa3.setName("panelBiasa3"); // NOI18N
        panelBiasa3.setLayout(new java.awt.BorderLayout());
        internalFrame1.add(panelBiasa3, java.awt.BorderLayout.CENTER);

        panelGlass5.setName("panelGlass5"); // NOI18N
        panelGlass5.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("Tgl. Pemantauan :");
        jLabel33.setName("jLabel33"); // NOI18N
        jLabel33.setPreferredSize(new java.awt.Dimension(105, 23));
        panelGlass5.add(jLabel33);

        tglPantau.setForeground(new java.awt.Color(50, 70, 50));
        tglPantau.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "30-01-2024" }));
        tglPantau.setDisplayFormat("dd-MM-yyyy");
        tglPantau.setName("tglPantau"); // NOI18N
        tglPantau.setOpaque(false);
        tglPantau.setPreferredSize(new java.awt.Dimension(100, 23));
        panelGlass5.add(tglPantau);

        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setName("jLabel34"); // NOI18N
        jLabel34.setPreferredSize(new java.awt.Dimension(25, 23));
        panelGlass5.add(jLabel34);

        BtnGrafik.setForeground(new java.awt.Color(0, 0, 0));
        BtnGrafik.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Bar Chart (copy).png"))); // NOI18N
        BtnGrafik.setMnemonic('G');
        BtnGrafik.setText("Lihat Grafik");
        BtnGrafik.setToolTipText("Alt+G");
        BtnGrafik.setName("BtnGrafik"); // NOI18N
        BtnGrafik.setPreferredSize(new java.awt.Dimension(120, 30));
        BtnGrafik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGrafikActionPerformed(evt);
            }
        });
        panelGlass5.add(BtnGrafik);

        BtnKeluar3.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar3.setMnemonic('K');
        BtnKeluar3.setText("Keluar");
        BtnKeluar3.setToolTipText("Alt+K");
        BtnKeluar3.setName("BtnKeluar3"); // NOI18N
        BtnKeluar3.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar3ActionPerformed(evt);
            }
        });
        panelGlass5.add(BtnKeluar3);

        internalFrame1.add(panelGlass5, java.awt.BorderLayout.PAGE_END);

        panelBiasa5.setName("panelBiasa5"); // NOI18N
        panelBiasa5.setLayout(new java.awt.BorderLayout());

        Scroll1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " [ Data Intake Per Jam ] ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);
        Scroll1.setPreferredSize(new java.awt.Dimension(440, 422));

        tbIntake.setToolTipText("Silahkan klik salah satu data utk. melihat parental/line/obat-obatan");
        tbIntake.setName("tbIntake"); // NOI18N
        tbIntake.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbIntakeMouseClicked(evt);
            }
        });
        Scroll1.setViewportView(tbIntake);

        panelBiasa5.add(Scroll1, java.awt.BorderLayout.CENTER);

        Scroll2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " [ Parental / Line / Obat-obatan ] ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);
        Scroll2.setPreferredSize(new java.awt.Dimension(440, 222));

        tbParental.setToolTipText("");
        tbParental.setName("tbParental"); // NOI18N
        Scroll2.setViewportView(tbParental);

        panelBiasa5.add(Scroll2, java.awt.BorderLayout.PAGE_END);

        internalFrame1.add(panelBiasa5, java.awt.BorderLayout.EAST);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Valid.tabelKosong(tabMode);
        Valid.tabelKosong(tabMode1);
    }//GEN-LAST:event_formWindowOpened

    private void BtnKeluar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar3ActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluar3ActionPerformed

    private void BtnGrafikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGrafikActionPerformed
        if (Sequel.cariInteger("select count(-1) from pemantauan_harian_24jam where no_rawat='" + TNoRW.getText() + "' and tgl_pantau = '" + Valid.SetTgl(tglPantau.getSelectedItem() + "") + "'") == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pemantauan harian pasien pada tgl. " + Valid.SetTglINDONESIA(Valid.SetTgl(tglPantau.getSelectedItem() + "")) + " tdk. ditemukan..!!");
            Valid.tabelKosong(tabMode);
            Valid.tabelKosong(tabMode1);
            tglPantau.requestFocus();
        } else {
            grafikpantau24jam grafik = new grafikpantau24jam("Grafik Hasil Pemantauan Harian Pasien Selama "
                    + Sequel.cariIsi("select count(-1) from pemantauan_harian_24jam where tgl_pantau = '" + Valid.SetTgl(tglPantau.getSelectedItem() + "") + "' and no_rawat ='" + TNoRW.getText() + "'")
                    + " Jam Pada Tgl. " + Valid.SetTglINDONESIA(Valid.SetTgl(tglPantau.getSelectedItem() + "")),
                    "where no_rawat='" + TNoRW.getText() + "' and tgl_pantau = '" + Valid.SetTgl(tglPantau.getSelectedItem() + "") + "'");
            grafik.dispose();
            grafik.setSize(panelBiasa3.getWidth() - 10, panelBiasa3.getHeight() - 10);
            grafik.setModal(false);
            grafik.setAlwaysOnTop(true);
            grafik.setLocationRelativeTo(panelBiasa3);
            grafik.setVisible(true);
            tampil();
        }
    }//GEN-LAST:event_BtnGrafikActionPerformed

    private void tbIntakeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbIntakeMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                tampilParental();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbIntakeMouseClicked

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMGrafikPemantauanHarian24Jam dialog = new RMGrafikPemantauanHarian24Jam(new javax.swing.JFrame(), true);
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
    private widget.Button BtnGrafik;
    private widget.Button BtnKeluar3;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    private widget.TextBox TNmPasien;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRW;
    private widget.TextBox TtglMasuk;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel13;
    private widget.Label jLabel3;
    private widget.Label jLabel33;
    private widget.Label jLabel34;
    private widget.Label jLabel4;
    private widget.TextBox nmUnit;
    private widget.PanelBiasa panelBiasa3;
    private widget.PanelBiasa panelBiasa4;
    private widget.PanelBiasa panelBiasa5;
    private widget.panelisi panelGlass5;
    private widget.Table tbIntake;
    private widget.Table tbParental;
    private widget.Tanggal tglPantau;
    // End of variables declaration//GEN-END:variables

    public void setData(String norw, String nomorrm, String namaPx, String rgrawat, String tglmasuk) {
        TNoRW.setText(norw);
        TNoRM.setText(nomorrm);
        TNmPasien.setText(namaPx);
        nmUnit.setText(rgrawat);
        TtglMasuk.setText(tglmasuk);
        
        if (Sequel.cariInteger("select count(-1) from pemantauan_harian_24jam where no_rawat='" + norw + "'") > 0) {
            Valid.SetTgl(tglPantau, Sequel.cariIsi("select tgl_pantau from pemantauan_harian_24jam where "
                    + "no_rawat='" + norw + "' order by tgl_pantau desc limit 1"));
        } else {
            tglPantau.setDate(new Date());
        }
    }
    
    private void tampil() {
        Valid.tabelKosong(tabMode);        
        try {
            ps1 = koneksi.prepareStatement("select *, date_format(tgl_pantau,'%d/%m/%Y') tglPantau, concat(gcs_e,',',gcs_m,',',gcs_v) gcs "
                    + "from pemantauan_harian_24jam where tgl_pantau='" + Valid.SetTgl(tglPantau.getSelectedItem() + "") + "' "
                    + "and no_rawat='" + TNoRW.getText() + "' order by urutan_jam");
            try {
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    tabMode.addRow(new String[]{
                        rs1.getString("tglPantau"),
                        rs1.getString("jam"),                        
                        rs1.getString("nadi"),
                        rs1.getString("suhu"),
                        rs1.getString("gcs"),
                        rs1.getString("kesadaran"),
                        rs1.getString("td"),
                        rs1.getString("nafas"),
                        rs1.getString("spo2"),
                        rs1.getString("makan_minum"),
                        rs1.getString("ngt"),
                        rs1.getString("total_parental"),
                        rs1.getString("total_intake"),
                        rs1.getString("urine"),
                        rs1.getString("ngt_darah"),
                        rs1.getString("drain"),
                        rs1.getString("muntah"),
                        rs1.getString("bab"),
                        rs1.getString("iwl"),
                        rs1.getString("total_output"),
                        rs1.getString("balance"),
                        rs1.getString("tgl_pantau"),
                        rs1.getString("kd_pantau")
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
    }
    
    private void tampilParental() {
        Valid.tabelKosong(tabMode1);
        try {
            ps2 = koneksi.prepareStatement("select date_format(tgl_pantau,'%d-%m-%Y') tglPantau, nm_obat, jml_pemberian, jam_ke from pemantauan_harian_parental where "
                    + "tgl_pantau='" + tbIntake.getValueAt(tbIntake.getSelectedRow(), 21).toString() + "' and "
                    + "kd_pantau='" + tbIntake.getValueAt(tbIntake.getSelectedRow(), 22).toString() + "'");
            try {
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    tabMode1.addRow(new String[]{
                        rs2.getString("tglPantau"),
                        rs2.getString("jam_ke"),
                        rs2.getString("nm_obat"),
                        rs2.getString("jml_pemberian")
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
}
