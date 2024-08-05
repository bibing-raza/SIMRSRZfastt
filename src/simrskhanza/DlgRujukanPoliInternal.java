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

package simrskhanza;

import fungsi.WarnaTable;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.awt.Cursor;
import java.awt.Dimension;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author perpustakaan
 */
public class DlgRujukanPoliInternal extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps1;
    private ResultSet rs, rs1;
    private DlgCariPoli poli = new DlgCariPoli(null, false);
    private DlgCariPoli2 poli2 = new DlgCariPoli2(null, false);
    private String aktifjadwal = "", tglSimpan = "";
    private Properties prop = new Properties();
    private int i = 0;
    
    /** Creates new form DlgPemberianObat
     * @param parent
     * @param modal */
    public DlgRujukanPoliInternal(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(755,156);
        
        tabMode = new DefaultTableModel(null, new Object[]{"No.", "Tgl. Dirujuk", "Poliklinik Tujuan",
            "kdpolinya", "Ket. Rujukan", "tglsimpan"}) {
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbRujukInternal.setModel(tabMode);
        tbRujukInternal.setPreferredScrollableViewportSize(new Dimension(800, 800));
        tbRujukInternal.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 6; i++) {
            TableColumn column = tbRujukInternal.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(75);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 4) {
                column.setPreferredWidth(250);
            } else if (i == 5) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbRujukInternal.setDefaultRenderer(Object.class, new WarnaTable());
        
        poli.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(poli.getTable().getSelectedRow()!= -1){   
                    kdpoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(),0).toString());
                    TPoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(),1).toString());
                    kdpoli.requestFocus();                        
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
                
        poli2.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(poli2.getTable().getSelectedRow()!= -1){  
                    kdpoli.setText(poli2.getTable().getValueAt(poli2.getTable().getSelectedRow(),0).toString());
                    TPoli.setText(poli2.getTable().getValueAt(poli2.getTable().getSelectedRow(),1).toString());
                    kdpoli.requestFocus();
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
        
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            aktifjadwal=prop.getProperty("JADWALDOKTERDIREGISTRASI");
        } catch (Exception ex) {
            aktifjadwal="";            
        }
    }

    //private DlgCariObatPenyakit dlgobtpny=new DlgCariObatPenyakit(null,false);
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        internalFrame1 = new widget.InternalFrame();
        FormInput = new widget.PanelBiasa();
        BtnUnit = new widget.Button();
        TPoli = new widget.TextBox();
        kdpoli = new widget.TextBox();
        jLabel19 = new widget.Label();
        jLabel3 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel15 = new widget.Label();
        tglDirujuk = new widget.Tanggal();
        jLabel16 = new widget.Label();
        Scroll3 = new widget.ScrollPane();
        keterangan = new widget.TextArea();
        Scroll6 = new widget.ScrollPane();
        tbRujukInternal = new widget.Table();
        jLabel17 = new widget.Label();
        ketHari = new widget.Label();
        panelGlass8 = new widget.panelisi();
        BtnBatal = new widget.Button();
        BtnSimpan = new widget.Button();
        BtnGanti = new widget.Button();
        BtnHapus = new widget.Button();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Rujukan Poliklinik Internal ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setPreferredSize(new java.awt.Dimension(875, 160));
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(865, 110));
        FormInput.setLayout(null);

        BtnUnit.setForeground(new java.awt.Color(0, 0, 0));
        BtnUnit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/download24.png"))); // NOI18N
        BtnUnit.setMnemonic('4');
        BtnUnit.setToolTipText("ALt+4");
        BtnUnit.setName("BtnUnit"); // NOI18N
        BtnUnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUnitActionPerformed(evt);
            }
        });
        FormInput.add(BtnUnit);
        BtnUnit.setBounds(560, 40, 28, 23);

        TPoli.setEditable(false);
        TPoli.setForeground(new java.awt.Color(0, 0, 0));
        TPoli.setName("TPoli"); // NOI18N
        FormInput.add(TPoli);
        TPoli.setBounds(170, 40, 390, 23);

        kdpoli.setEditable(false);
        kdpoli.setForeground(new java.awt.Color(0, 0, 0));
        kdpoli.setHighlighter(null);
        kdpoli.setName("kdpoli"); // NOI18N
        kdpoli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdpoliKeyPressed(evt);
            }
        });
        FormInput.add(kdpoli);
        kdpoli.setBounds(100, 40, 66, 23);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Poliklinik Tujuan : ");
        jLabel19.setName("jLabel19"); // NOI18N
        FormInput.add(jLabel19);
        jLabel19.setBounds(0, 40, 100, 23);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Pasien : ");
        jLabel3.setName("jLabel3"); // NOI18N
        FormInput.add(jLabel3);
        jLabel3.setBounds(0, 12, 100, 23);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        FormInput.add(TNoRw);
        TNoRw.setBounds(100, 12, 153, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(257, 12, 90, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(350, 12, 410, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Tgl. Dirujuk : ");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(0, 68, 100, 23);

        tglDirujuk.setEditable(false);
        tglDirujuk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "13-06-2022" }));
        tglDirujuk.setDisplayFormat("dd-MM-yyyy");
        tglDirujuk.setName("tglDirujuk"); // NOI18N
        tglDirujuk.setOpaque(false);
        tglDirujuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tglDirujukActionPerformed(evt);
            }
        });
        FormInput.add(tglDirujuk);
        tglDirujuk.setBounds(100, 68, 90, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Keterangan : ");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(0, 96, 100, 23);

        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);

        keterangan.setColumns(20);
        keterangan.setRows(5);
        keterangan.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        keterangan.setName("keterangan"); // NOI18N
        keterangan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                keteranganKeyPressed(evt);
            }
        });
        Scroll3.setViewportView(keterangan);

        FormInput.add(Scroll3);
        Scroll3.setBounds(100, 96, 660, 290);

        Scroll6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Daftar Poliklinik Tujuan Rujukan ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        Scroll6.setName("Scroll6"); // NOI18N
        Scroll6.setOpaque(true);

        tbRujukInternal.setAutoCreateRowSorter(true);
        tbRujukInternal.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbRujukInternal.setName("tbRujukInternal"); // NOI18N
        tbRujukInternal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRujukInternalMouseClicked(evt);
            }
        });
        tbRujukInternal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbRujukInternalKeyPressed(evt);
            }
        });
        Scroll6.setViewportView(tbRujukInternal);

        FormInput.add(Scroll6);
        Scroll6.setBounds(100, 390, 660, 120);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Ket. : ");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(190, 68, 40, 23);

        ketHari.setForeground(new java.awt.Color(0, 0, 0));
        ketHari.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ketHari.setText("-");
        ketHari.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ketHari.setName("ketHari"); // NOI18N
        FormInput.add(ketHari);
        ketHari.setBounds(230, 68, 530, 23);

        internalFrame1.add(FormInput, java.awt.BorderLayout.CENTER);
        FormInput.getAccessibleContext().setAccessibleName("");
        FormInput.getAccessibleContext().setAccessibleDescription("");

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(100, 46));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

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
        panelGlass8.add(BtnSimpan);

        BtnGanti.setForeground(new java.awt.Color(0, 0, 0));
        BtnGanti.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnGanti.setMnemonic('G');
        BtnGanti.setText("Ganti");
        BtnGanti.setToolTipText("Alt+G");
        BtnGanti.setName("BtnGanti"); // NOI18N
        BtnGanti.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnGanti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGantiActionPerformed(evt);
            }
        });
        BtnGanti.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnGantiKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnGanti);

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

        BtnPrint.setForeground(new java.awt.Color(0, 0, 0));
        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint.setMnemonic('T');
        BtnPrint.setText("Cetak Surat");
        BtnPrint.setToolTipText("Alt+T");
        BtnPrint.setName("BtnPrint"); // NOI18N
        BtnPrint.setPreferredSize(new java.awt.Dimension(120, 30));
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
        panelGlass8.add(BtnPrint);

        BtnKeluar.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnKeluar.setMnemonic('K');
        BtnKeluar.setText("Tutup");
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

        internalFrame1.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().trim().equals("") || TNoRM.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRM, "Pasien");
        } else if (TPoli.getText().trim().equals("") || kdpoli.getText().trim().equals("")) {
            Valid.textKosong(kdpoli, "poliklinik");
        } else if (keterangan.getText().trim().equals("") || keterangan.getText().trim().length() < 6) {
            JOptionPane.showMessageDialog(null, "Silahkan diisi dulu dg. benar keterangan/deskripsi utk. rujukan internal polikliniknya...!!");
            keterangan.requestFocus();
        } else if (Sequel.cariInteger("select count(-1) from hari_libur where tgl_libur='" + Valid.SetTgl(tglDirujuk.getSelectedItem() + "") + "'") > 0) {
            JOptionPane.showMessageDialog(rootPane, "Pelayanan rawat jalan poliklinik TUTUP, karena sedang/memperingati "
                    + Sequel.cariIsi("select keterangan from hari_libur where tgl_libur='" + Valid.SetTgl(tglDirujuk.getSelectedItem() + "") + "'")
                    + ", silahkan ganti hari lain utk. tgl. rencana dirujuknya");
        } else {
            if (Sequel.menyimpantf("rujukan_internal_poli", "?,?,?,?,?,?,?,?,?,?", "Rujukan Sama", 10, new String[]{
                TNoRw.getText(), Sequel.cariIsi("select kd_poli from reg_periksa where no_rawat='" + TNoRw.getText() + "' and status_lanjut='Ralan'"),
                keterangan.getText(), Valid.SetTgl(tglDirujuk.getSelectedItem() + ""), "Belum", "", kdpoli.getText(), "", Sequel.cariIsi("select now()"),""
            }) == true) {
                tampil();
                inputbaru();
            }
        }  
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        empttext();
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            kdpoli.setText("");
            TPoli.setText("");
            dispose();
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnUnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUnitActionPerformed
        TPoli.setText("");
        kdpoli.setText("");
        akses.setform("DlgRujukanPoliInternal");
        poli.isCek();
        poli.setSize(1074, 662);
        poli.setLocationRelativeTo(internalFrame1);
        poli.setVisible(true);
        poli.emptTeks();
    }//GEN-LAST:event_BtnUnitActionPerformed

    private void kdpoliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdpoliKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            Sequel.cariIsi("select nm_poli from poliklinik where kd_poli=?", TPoli, kdpoli.getText());
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            BtnUnitActionPerformed(null);
        } else {
            Valid.pindah(evt, kdpoli, TNoRM);
        }
    }//GEN-LAST:event_kdpoliKeyPressed

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        
    }//GEN-LAST:event_TNoRwKeyPressed

    private void tglDirujukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tglDirujukActionPerformed
        cekHariLibur();
    }//GEN-LAST:event_tglDirujukActionPerformed

    private void keteranganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_keteranganKeyPressed
        //        Valid.pindah(evt,COB,LokasiLaka);
    }//GEN-LAST:event_keteranganKeyPressed

    private void BtnGantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiActionPerformed
        if (TNoRw.getText().trim().equals("") || TNoRM.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRM, "Pasien");
        } else if (keterangan.getText().trim().equals("") || keterangan.getText().trim().length() < 6) {
            JOptionPane.showMessageDialog(null, "Silahkan diisi dulu dg. benar keterangan/deskripsi utk. rujukan internal polikliniknya...!!");
            keterangan.requestFocus();
        } else if (TPoli.getText().trim().equals("") || kdpoli.getText().trim().equals("")) {
            Valid.textKosong(kdpoli, "poliklinik");
        } else if (tglSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan anda pilih dulu salah satu daftar poliklinik tujuan rujukan...!!!");
            tbRujukInternal.requestFocus();
        } else if (Sequel.cariInteger("select count(-1) from hari_libur where tgl_libur='" + Valid.SetTgl(tglDirujuk.getSelectedItem() + "") + "'") > 0) {
            JOptionPane.showMessageDialog(rootPane, "Pelayanan rawat jalan poliklinik TUTUP, karena sedang/memperingati "
                    + Sequel.cariIsi("select keterangan from hari_libur where tgl_libur='" + Valid.SetTgl(tglDirujuk.getSelectedItem() + "") + "'")
                    + ", silahkan ganti hari lain utk. tgl. rencana dirujuknya");
        } else {
            Sequel.mengedit("rujukan_internal_poli", "no_rawat='" + TNoRw.getText() + "' and tgl_simpan='" + tglSimpan + "'",
                    "kd_poli_pembalas='" + kdpoli.getText() + "', "
                    + "keterangan='" + keterangan.getText() + "', "
                    + "tgl_rencana_dirujuk='" + Valid.SetTgl(tglDirujuk.getSelectedItem() + "") + "',"
                    + "kd_poli='" + Sequel.cariIsi("select kd_poli from reg_periksa where no_rawat='" + TNoRw.getText() + "' and status_lanjut='Ralan'") + "'");
            tampil();
            inputbaru();
        }
    }//GEN-LAST:event_BtnGantiActionPerformed

    private void BtnGantiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnGantiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnGantiKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (TNoRw.getText().trim().equals("") || TNoRM.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRM, "Pasien");
        } else if (TPoli.getText().trim().equals("") || kdpoli.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu datanya pada tabel...!!!!");
            tbRujukInternal.requestFocus();
        } else if (Sequel.cariInteger("select count(-1) from rujukan_internal_poli where no_rawat='" + TNoRw.getText() + "' and kd_poli_pembalas='" + kdpoli.getText() + "'") < 1) {
            JOptionPane.showMessageDialog(null, "Silahkan simpan dulu rujukan internal poliklinik nya...!!!!");
        } else {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("tglSuratPenjawab", "Martapura, " + Valid.SetTglINDONESIA(Sequel.cariIsi("select tgl_rencana_dirujuk from rujukan_internal_poli where no_rawat='" + TNoRw.getText() + "'") + "'"));
            param.put("tglSuratPerujuk", "Martapura, " + Valid.SetTglINDONESIA(Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + TNoRw.getText() + "'")));
            param.put("nmDokterPerujuk", Sequel.cariIsi("SELECT d.nm_dokter FROM rujukan_internal_poli r INNER JOIN reg_periksa rp ON rp.no_rawat = r.no_rawat "
                    + "INNER JOIN dokter d ON d.kd_dokter = rp.kd_dokter WHERE r.no_rawat='" + TNoRw.getText() + "' AND r.kd_poli_pembalas='" + kdpoli.getText() + "'"));                
            
            if (Sequel.cariIsi("select ifnull(keterangan_balasan,'') from rujukan_internal_poli where no_rawat='" + TNoRw.getText() + "'").equals("")) {
                param.put("judul", "SURAT RUJUKAN INTERNAL POLIKLINIK");                
                param.put("nmDokterPenjawab", ".........................................");
            } else {
                param.put("judul", "SURAT BALASAN/JAWABAN RUJUKAN INTERNAL POLIKLINIK");
                param.put("nmDokterPenjawab", Sequel.cariIsi("SELECT d.nm_dokter FROM rujukan_internal_poli r INNER JOIN reg_periksa rp ON rp.no_rawat = r.no_rawat_pembalas "
                        + "INNER JOIN dokter d ON d.kd_dokter = rp.kd_dokter WHERE r.no_rawat='" + TNoRw.getText() + "' AND r.kd_poli_pembalas='" + kdpoli.getText() + "'"));
            }
            Valid.MyReport("rptSuratRujukanInternal.jasper", "report", "::[ Surat Rujukan Poliklinik Internal ]::",
                    "SELECT r.no_rawat, p.no_rkm_medis, p.nm_pasien, pl1.nm_poli asal_poli, pl2.nm_poli ke_poli, "
                    + "DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y') tgl_registrasi, d.nm_dokter dr_perujuk, "
                    + "trim(r.keterangan) keterangan, DATE_FORMAT(r.tgl_rencana_dirujuk,'%d-%m-%Y') tgl_rencana_dirujuk, IF(p.jk='L','Laki-laki','Perempuan') jk, "
                    + "DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y') tgl_lhr, ifnull(r.keterangan_balasan,'-') jawaban FROM rujukan_internal_poli r "
                    + "INNER JOIN reg_periksa rp ON rp.no_rawat = r.no_rawat INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                    + "INNER JOIN poliklinik pl1 ON pl1.kd_poli = r.kd_poli INNER JOIN poliklinik pl2 ON pl2.kd_poli = r.kd_poli_pembalas "
                    + "INNER JOIN dokter d ON d.kd_dokter = rp.kd_dokter WHERE r.no_rawat='" + TNoRw.getText() + "' AND r.kd_poli_pembalas='" + kdpoli.getText() + "'", param);
            
            empttext();
            dispose();
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnSimpan, BtnKeluar);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        inputbaru();
    }//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
//            emptTeks();
        } else {
//            Valid.pindah(evt, BtnSimpan, BtnHapus);
        }
    }//GEN-LAST:event_BtnBatalKeyPressed

    private void tbRujukInternalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRujukInternalMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getdata();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbRujukInternalMouseClicked

    private void tbRujukInternalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbRujukInternalKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getdata();
                } catch (java.lang.NullPointerException e) {
                }
            } else if (evt.getKeyCode() == KeyEvent.VK_V) {
                if (tbRujukInternal.getSelectedColumn() > 4) {
                    if (tbRujukInternal.getSelectedRow() != -1) {
                        if (tbRujukInternal.getValueAt(tbRujukInternal.getSelectedRow(), tbRujukInternal.getSelectedColumn()).toString().equals("false")) {
                            tbRujukInternal.setValueAt(true, tbRujukInternal.getSelectedRow(), tbRujukInternal.getSelectedColumn());
                        } else {
                            tbRujukInternal.setValueAt(false, tbRujukInternal.getSelectedRow(), tbRujukInternal.getSelectedColumn());
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_tbRujukInternalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (TNoRw.getText().trim().equals("") || TNoRM.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRM, "Pasien");
        } else if (TPoli.getText().trim().equals("") || kdpoli.getText().trim().equals("")) {
            Valid.textKosong(kdpoli, "poliklinik");
        } else if (tglSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan anda pilih dulu salah satu daftar poliklinik tujuan rujukan...!!!");
            tbRujukInternal.requestFocus();
        } else {
            Sequel.queryu("delete from rujukan_internal_poli where no_rawat='" + TNoRw.getText() + "' and tgl_simpan='" + tglSimpan + "'");
            inputbaru();
            tampil();
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnKeluar);
        }
    }//GEN-LAST:event_BtnHapusKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        cekHariLibur();
    }//GEN-LAST:event_formWindowOpened

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgRujukanPoliInternal dialog = new DlgRujukanPoliInternal(new javax.swing.JFrame(), true);
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
    public widget.Button BtnBatal;
    private widget.Button BtnGanti;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    public widget.Button BtnUnit;
    private widget.PanelBiasa FormInput;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll6;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox TPoli;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.Label jLabel19;
    private widget.Label jLabel3;
    private widget.TextBox kdpoli;
    private widget.Label ketHari;
    private widget.TextArea keterangan;
    private widget.panelisi panelGlass8;
    private widget.Table tbRujukInternal;
    private widget.Tanggal tglDirujuk;
    // End of variables declaration//GEN-END:variables
    
    public void setNoRm(String norw, String norm, String namapasien) {
        TNoRw.setText(norw);
        TNoRM.setText(norm);
        TPasien.setText(namapasien);
    }  
    
    public void isCek(){
        BtnSimpan.setEnabled(true);
    }
    
    public void empttext() {
        TNoRw.setText("");
        TNoRM.setText("");
        TPasien.setText("");
        kdpoli.setText("");
        TPoli.setText("");
        keterangan.setText("");    
    }
    
    public void inputbaru() {
        kdpoli.setText("");
        TPoli.setText("");
        keterangan.setText("");
        BtnUnit.requestFocus();
        cekHariLibur();
    }
    
    public void SatuTujuanPoli() {
        kdpoli.setText(Sequel.cariIsi("select kd_poli_pembalas from rujukan_internal_poli where no_rawat='" + TNoRw.getText() + "'"));
        TPoli.setText(Sequel.cariIsi("select nm_poli from poliklinik where kd_poli='" + kdpoli.getText() + "'"));
        Valid.SetTgl(tglDirujuk, Sequel.cariIsi("select tgl_rencana_dirujuk from rujukan_internal_poli where no_rawat='" + TNoRw.getText() + "'"));
        keterangan.setText(Sequel.cariIsi("select keterangan from rujukan_internal_poli where no_rawat='" + TNoRw.getText() + "'"));
    }
    
    private void getdata() {
        tglSimpan = "";
        if (tbRujukInternal.getSelectedRow() != -1) {            
            kdpoli.setText(tbRujukInternal.getValueAt(tbRujukInternal.getSelectedRow(), 3).toString());
            TPoli.setText(tbRujukInternal.getValueAt(tbRujukInternal.getSelectedRow(), 2).toString());
            Valid.SetTgl(tglDirujuk, Sequel.cariIsi("select tgl_rencana_dirujuk from rujukan_internal_poli "
                    + "where no_rawat='" + TNoRw.getText() + "' and kd_poli_pembalas='" + kdpoli.getText() + "'"));
            keterangan.setText(tbRujukInternal.getValueAt(tbRujukInternal.getSelectedRow(), 4).toString());
            tglSimpan = tbRujukInternal.getValueAt(tbRujukInternal.getSelectedRow(), 5).toString();
        }
    }
    
    public void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps1 = koneksi.prepareStatement("SELECT date_format(r.tgl_rencana_dirujuk,'%d-%m-%Y') tgl, p.nm_poli, "
                    + "r.kd_poli_pembalas, trim(r.keterangan) keterangan, r.tgl_simpan from rujukan_internal_poli r "
                    + "inner join poliklinik p on p.kd_poli=r.kd_poli_pembalas where r.no_rawat='" + TNoRw.getText() + "'");
            try {                
                rs1 = ps1.executeQuery();
                i = 1;
                while (rs1.next()) {
                    tabMode.addRow(new Object[]{
                        i + ".",
                        rs1.getString("tgl"),
                        rs1.getString("nm_poli"),
                        rs1.getString("kd_poli_pembalas"),
                        rs1.getString("keterangan"),
                        rs1.getString("tgl_simpan")
                    });
                    i++;
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println("simrskhanza.DlgRujukanPoliInternal.tampil() : " + e);
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
    
    private void cekHariLibur() {
        ketHari.setText("-");
        
        if (Sequel.cariIsi("select ifnull(tgl_libur,'') from hari_libur where tgl_libur='" + Valid.SetTgl(tglDirujuk.getSelectedItem() + "") + "'").equals("")) {
            ketHari.setText("Kalender/penanggalan/hari normal seperti biasa.");
            ketHari.setForeground(Color.BLACK);
        } else {
            ketHari.setText(Sequel.cariIsi("select keterangan from hari_libur where tgl_libur='" + Valid.SetTgl(tglDirujuk.getSelectedItem() + "") + "'"));
            ketHari.setForeground(Color.RED);
        }
    }
}
