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
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author dosen
 */
public class DlgHasilLIS extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps, ps1, ps2, ps3;
    private ResultSet rs, rs1, rs2, rs3;
    private int i = 0;
    private String norawat = "", noLIS = "", cekLIS = "", ketLIS = "", tglLIS = "", jamLIS = "",
            drpengirim = "", tglPeriksaLIS = "", jamPeriksaLIS = "", nmpas = "", nomorrm = "";
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public DlgHasilLIS(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new Object[]{
            "Pasien","No. LIS", "Keterangan Hasil Lab.", "cekok", "norawat", "Tgl. Periksa",
            "Jam Periksa", "Dokter Pengirim"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbLIS.setModel(tabMode);
        tbLIS.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbLIS.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        for (int i = 0; i < 8; i++) {
            TableColumn column = tbLIS.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(230);
            } else if (i == 1) {
                column.setPreferredWidth(85);
            } else if (i == 2) {
                column.setPreferredWidth(180);
            } else if (i == 3) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 4) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 5) {
                column.setPreferredWidth(70);
            } else if (i == 6) {
                column.setPreferredWidth(70);
            } else if (i == 7) {
                column.setPreferredWidth(220);
            }
        }
        tbLIS.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1 = new DefaultTableModel(null, new Object[]{"Jenis Pemeriksaan/Item", "Nilai Hasil", 
            "Satuan", "Flag Kode", "Nilai Rujukan", "Waktu Selesai", "Metode Pemeriksaan"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbHasil.setModel(tabMode1);
        tbHasil.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbHasil.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 7; i++) {
            TableColumn column = tbHasil.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(300);
            } else if (i == 1) {
                column.setPreferredWidth(75);
            } else if (i == 2) {
                column.setPreferredWidth(75);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(120);
            } else if (i == 6) {
                column.setPreferredWidth(120);
            }
        }
        tbHasil.setDefaultRenderer(Object.class, new WarnaTable());
        
        try {
            ps1 = koneksi.prepareStatement("SELECT lhp.kategori_pemeriksaan_nama FROM lis_reg lr LEFT JOIN lis_hasil_periksa_lab lhp on lhp.no_lab=lr.no_lab "
                    + "LEFT JOIN lis_hasil_data_pasien lhdp on lhdp.no_lab=lr.no_lab WHERE lr.no_lab=? "
                    + "GROUP BY lhp.kategori_pemeriksaan_nama ORDER BY lhp.kategori_pemeriksaan_no_urut, lhp.sub_kategori_pemeriksaan_no_urut,lhp.pemeriksaan_no_urut");
            
            ps2 = koneksi.prepareStatement("SELECT lhp.sub_kategori_pemeriksaan_nama FROM lis_reg lr LEFT JOIN lis_hasil_periksa_lab lhp on lhp.no_lab=lr.no_lab "
                    + "LEFT JOIN lis_hasil_data_pasien lhdp on lhdp.no_lab=lr.no_lab WHERE lr.no_lab=? "
                    + "and lhp.kategori_pemeriksaan_nama=? GROUP BY lhp.sub_kategori_pemeriksaan_nama "
                    + "ORDER BY lhp.kategori_pemeriksaan_no_urut, lhp.sub_kategori_pemeriksaan_no_urut,lhp.sub_kategori_pemeriksaan_nama desc,lhp.pemeriksaan_no_urut");
            
            ps3 = koneksi.prepareStatement("SELECT lhp.pemeriksaan_nama, lhp.nilai_hasil, lhp.satuan, lhp.flag_kode, "
                    + "lhp.nilai_rujukan, DATE_FORMAT(lhdp.waktu_insert,'%d/%m/%Y - %h:%i') wkt_selesai, lhp.metode "
                    + "FROM lis_reg lr LEFT JOIN lis_hasil_periksa_lab lhp ON lhp.no_lab = lr.no_lab "
                    + "LEFT JOIN lis_hasil_data_pasien lhdp ON lhdp.no_lab=lr.no_lab WHERE lr.no_lab=? "
                    + "and lhp.sub_kategori_pemeriksaan_nama=? and lhp.kategori_pemeriksaan_nama=? GROUP BY lhp.pemeriksaan_nama "
                    + "ORDER BY lhp.kategori_pemeriksaan_no_urut, lhp.sub_kategori_pemeriksaan_no_urut,lhp.pemeriksaan_no_urut");
        } catch (Exception e) {
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
        tbHasil = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        PanelInput = new javax.swing.JPanel();
        FormInput = new widget.PanelBiasa();
        Scroll1 = new widget.ScrollPane();
        tbLIS = new widget.Table();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Hasil Pemeriksaan LIS (Laboratory Information System) ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Hasil Pemeriksaan Laboratorium :.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbHasil.setAutoCreateRowSorter(true);
        tbHasil.setToolTipText("");
        tbHasil.setName("tbHasil"); // NOI18N
        Scroll.setViewportView(tbHasil);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 57));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 5, 9));

        BtnAll.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/PrinterSettings.png"))); // NOI18N
        BtnAll.setMnemonic('P');
        BtnAll.setText("Print Hasil Lab.");
        BtnAll.setToolTipText("Alt+P");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(150, 30));
        BtnAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAllActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnAll);

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
        panelGlass8.add(BtnKeluar);

        jPanel3.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 160));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(190, 107));
        FormInput.setLayout(new java.awt.BorderLayout());

        Scroll1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Nomor Pemeriksaan Lab. :.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbLIS.setAutoCreateRowSorter(true);
        tbLIS.setToolTipText("");
        tbLIS.setName("tbLIS"); // NOI18N
        tbLIS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbLISMouseClicked(evt);
            }
        });
        tbLIS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbLISKeyPressed(evt);
            }
        });
        Scroll1.setViewportView(tbLIS);

        FormInput.add(Scroll1, java.awt.BorderLayout.CENTER);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        if (tabMode1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnKeluar.requestFocus();
        } else if (noLIS.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu datanya pada tabel");
            tbLIS.requestFocus();
        } else if (tabMode1.getRowCount() != 0) {
            if (cekLIS.equals("")) {
                JOptionPane.showMessageDialog(null, ketLIS + " pemeriksaan dengan No. Lab. " + noLIS + ".");
            } else {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Map<String, Object> param = new HashMap<>();
                param.put("namars", akses.getnamars());
                param.put("alamatrs", akses.getalamatrs());
                param.put("kotars", akses.getkabupatenrs());
                param.put("propinsirs", akses.getpropinsirs());
                param.put("kontakrs", akses.getkontakrs());
                param.put("emailrs", akses.getemailrs());
                param.put("logo", Sequel.cariGambar("select logo from setting"));

                param.put("nmPasien", nmpas);
                param.put("noLab", noLIS);
                param.put("norawat", norawat);
                param.put("norm", nomorrm);
                param.put("jkCaraByr", Sequel.cariIsi("SELECT concat(IF (p.jk = 'L','Laki-laki','Perempuan'),' / ',pj.png_jawab) FROM reg_periksa rp "
                        + "INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis INNER JOIN penjab pj ON pj.kd_pj=rp.kd_pj WHERE rp.no_rawat = '" + norawat + "'"));
                param.put("tglLhr_umur", Sequel.cariIsi("SELECT concat(DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y'),' / ',rp.umurdaftar,' ',rp.sttsumur,'.') "
                        + "FROM reg_periksa rp INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis WHERE rp.no_rawat = '" + norawat + "'"));
                param.put("tglPeriksa", Valid.SetTglINDONESIA(tglPeriksaLIS) + " - " + jamLIS);
                param.put("drPengirim", drpengirim);
                param.put("drLAB", Sequel.cariIsi("SELECT d.nm_dokter FROM set_pjlab s INNER JOIN dokter d ON d.kd_dokter=s.kd_dokterlab"));
                param.put("tglSurat", "Martapura, " + Valid.SetTglINDONESIA(Sequel.cariIsi("SELECT DATE(waktu_insert) FROM lis_hasil_data_pasien WHERE no_lab='" + noLIS + "'")));
                param.put("labelUnit", "Poliklinik/Inst.");
                param.put("nmUnit", Sequel.cariIsi("select pl.nm_poli from poliklinik pl inner join reg_periksa rp on pl.kd_poli=rp.kd_poli where rp.no_rawat='" + norawat + "'"));

                Valid.MyReport("rptHasilLIS.jasper", "report", "::[ Lembar Hasil Pemeriksaan Laboratorium (LIS) ]::", "SELECT * FROM temporary_lis", param);
                BtnKeluarActionPerformed(null);
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
}//GEN-LAST:event_BtnAllActionPerformed

    private void tbLISMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbLISMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbLISMouseClicked

    private void tbLISKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbLISKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbLISKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgHasilLIS dialog = new DlgHasilLIS(new javax.swing.JFrame(), true);
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
    private widget.Button BtnKeluar;
    private widget.PanelBiasa FormInput;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.InternalFrame internalFrame1;
    private javax.swing.JPanel jPanel3;
    private widget.panelisi panelGlass8;
    private widget.Table tbHasil;
    private widget.Table tbLIS;
    // End of variables declaration//GEN-END:variables
 
    private void tampilLIS() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT CONCAT(p.no_rkm_medis,' - ',p.nm_pasien) pasien, lr.no_lab nolis, "
                    + "IF(lh.no_lab IS NULL,'Petugas Lab. belum mengirim hasil','Hasil Lab. bisa dicetak') hasil_lab, "
                    + "IFNULL(lh.no_lab,'') cekOK, pl.no_rawat, IFNULL(DATE_FORMAT(lh.waktu_reg_lab,'%d-%m-%Y'),DATE_FORMAT(pl.tgl_periksa,'%d-%m-%Y')) tgl, "
                    + "IFNULL(DATE_FORMAT(lh.waktu_reg_lab,'%H:%i:%s'),DATE_FORMAT(pl.jam,'%H:%i:%s')) jam, "
                    + "IFNULL(lh.dokter_pengirim,'') dokter_pengirim FROM lis_reg lr LEFT JOIN reg_periksa rp ON rp.no_rawat=lr.no_rawat "
                    + "LEFT JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis LEFT JOIN periksa_lab pl ON pl.no_rawat=lr.no_rawat "
                    + "LEFT JOIN lis_hasil_data_pasien lh ON lh.no_lab=lr.no_lab WHERE lr.no_rawat='" + norawat + "' "
                    + "GROUP BY lr.no_lab ORDER BY hasil_lab, tgl DESC, jam DESC");
            try {
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new Object[]{
                        rs.getString("pasien"),
                        rs.getString("nolis"),
                        rs.getString("hasil_lab"),
                        rs.getString("cekOK"),
                        rs.getString("no_rawat"),
                        rs.getString("tgl"),
                        rs.getString("jam"),
                        rs.getString("dokter_pengirim")
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
    }
    
    public void setData(String norw, String nmpasien, String norm) {
        Valid.tabelKosong(tabMode1);
        norawat = norw;
        nmpas = nmpasien;
        nomorrm = norm;
        tampilLIS();
    }

    private void getData() {
        Valid.tabelKosong(tabMode1);
        noLIS = "";
        cekLIS = "";
        ketLIS = "";
        tglLIS = "";
        jamLIS = "";
        drpengirim = "";
        tglPeriksaLIS = "";
        jamPeriksaLIS = "";

        if (tbLIS.getSelectedRow() != -1) {
            noLIS = tbLIS.getValueAt(tbLIS.getSelectedRow(), 1).toString();
            ketLIS = tbLIS.getValueAt(tbLIS.getSelectedRow(), 2).toString();
            cekLIS = tbLIS.getValueAt(tbLIS.getSelectedRow(), 3).toString();
            tglLIS = tbLIS.getValueAt(tbLIS.getSelectedRow(), 5).toString();
            jamLIS = tbLIS.getValueAt(tbLIS.getSelectedRow(), 6).toString();
            drpengirim = tbLIS.getValueAt(tbLIS.getSelectedRow(), 7).toString();
            tglPeriksaLIS = Sequel.cariIsi("SELECT DATE(waktu_reg_lab) FROM lis_hasil_data_pasien WHERE no_lab='" + noLIS + "'");
            jamPeriksaLIS = Sequel.cariIsi("SELECT TIME(waktu_reg_lab) FROM lis_hasil_data_pasien WHERE no_lab='" + noLIS + "'");
            tampilHasil();
        }
    }
    
    private void tampilHasil() {
        try {
            Sequel.queryu("delete from temporary_lis");
            Valid.tabelKosong(tabMode1);
            ps1.setString(1, noLIS);
            rs1 = ps1.executeQuery();
            while (rs1.next()) {    
                Sequel.menyimpan("temporary_lis", "'" + rs1.getString("kategori_pemeriksaan_nama") + "','','','',"
                        + "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Kategori Pemeriksaan");
                tabMode1.addRow(new Object[]{rs1.getString("kategori_pemeriksaan_nama")});

                ps2.setString(1, noLIS);
                ps2.setString(2, rs1.getString("kategori_pemeriksaan_nama"));
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    Sequel.menyimpan("temporary_lis", "'   " + rs2.getString("sub_kategori_pemeriksaan_nama") + "','','','',"
                            + "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Sub Kategori Pemeriksaan");
                    tabMode1.addRow(new Object[]{"   "+rs2.getString("sub_kategori_pemeriksaan_nama")});
                    
                    ps3.setString(1, noLIS);
                    ps3.setString(2, rs2.getString("sub_kategori_pemeriksaan_nama"));
                    ps3.setString(3, rs1.getString("kategori_pemeriksaan_nama"));
                    
                    rs3 = ps3.executeQuery();
                    while (rs3.next()) {
                        Sequel.menyimpan("temporary_lis", "'     " + Valid.mysql_real_escape_string(rs3.getString("pemeriksaan_nama")) + "',"
                                + "'" + Valid.mysql_real_escape_string(rs3.getString("nilai_hasil")) + "',"
                                + "'" + Valid.mysql_real_escape_string(rs3.getString("satuan")) + "',"
                                + "'" + rs3.getString("flag_kode") + "',"
                                + "'" + Valid.mysql_real_escape_string(rs3.getString("nilai_rujukan")) + "',"
                                + "'" + rs3.getString("wkt_selesai") + "',"
                                + "'" + Valid.mysql_real_escape_string(rs3.getString("metode")) + "',"
                                + "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Hasil Pemeriksaan");
                        tabMode1.addRow(new Object[]{
                            "     " + rs3.getString("pemeriksaan_nama"),
                            rs3.getString("nilai_hasil"),
                            rs3.getString("satuan"),
                            rs3.getString("flag_kode"),
                            rs3.getString("nilai_rujukan"),
                            rs3.getString("wkt_selesai"),
                            rs3.getString("metode")
                        });
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
