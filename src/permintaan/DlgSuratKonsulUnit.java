    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgSuratKeteranganNapza.java
 *
 * Created on Juni 29, 2023, 1:25:13 AM
 */

package permintaan;

import rekammedis.*;
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
import keuangan.DlgKamar;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import simrskhanza.DlgCariDokter;

/**
 *
 * @author dosen
 */
public class DlgSuratKonsulUnit extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps1;
    private ResultSet rs, rs1;
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    private String kddokter = "", wktSimpan = "", sttsJawab = "";
    private int x = 0;

    /** Creates new form DlgSpesialis
     * @param parent
     * @param modal */
    public DlgSuratKonsulUnit(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        Object[] row = {"No. Rawat", "No. RM", "Nama Pasien", "Dari Unit", "Ke Unit", "Permintaan Konsul",
            "Tgl. Permintaan", "Jam Permintaan", "Dengan Dokter", "tgl_minta", "jam_minta", "nip_minta", 
            "waktu_simpan", "Stts. Surat"
        };
        tabMode=new DefaultTableModel(null,row){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };

        tbKonsul.setModel(tabMode);
        tbKonsul.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbKonsul.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 14; i++) {
            TableColumn column = tbKonsul.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(220);
            } else if (i == 3) {
                column.setPreferredWidth(220);
            } else if (i == 4) {
                column.setPreferredWidth(220);
            } else if (i == 5) {
                column.setPreferredWidth(350);
            } else if (i == 6) {
                column.setPreferredWidth(90);
            } else if (i == 7) {
                column.setPreferredWidth(90);
            } else if (i == 8) {
                column.setPreferredWidth(220);
            }  else if (i == 9) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 10) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 11) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 12) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 13) {
                column.setPreferredWidth(80);
            } 
        }

        tbKonsul.setDefaultRenderer(Object.class, new WarnaTable());
        
        TCari.setDocument(new batasInput((int) 100).getKata(TCari));
        
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgSuratKonsulUnit")) {
                    if (dokter.getTable().getSelectedRow() != -1) {
                        kddokter = dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString();
                        TnmdokterMinta.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                        cmbUnitKe.setSelectedItem(Sequel.cariIsi("SELECT ifnull(s.nm_sps,'-') from dokter d inner join spesialis s on s.kd_sps=d.kd_sps "
                                + "where d.kd_dokter='" + kddokter + "'"));
                        BtnDokterMinta.requestFocus();
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

        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbKonsul = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass9 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        panelGlass7 = new widget.panelisi();
        jLabel3 = new widget.Label();
        jLabel4 = new widget.Label();
        TNoRW = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel5 = new widget.Label();
        jLabel10 = new widget.Label();
        TnmdokterMinta = new widget.TextBox();
        BtnDokterMinta = new widget.Button();
        TtglMinta = new widget.Tanggal();
        Scroll7 = new widget.ScrollPane();
        TPermintaan = new widget.TextArea();
        jLabel11 = new widget.Label();
        jLabel12 = new widget.Label();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        jLabel13 = new widget.Label();
        label_status = new widget.Label();
        cmbUnitKe = new widget.ComboBox();
        cmbUnitDari = new widget.ComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Surat Konsul Antar Unit Rawat Inap ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbKonsul.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbKonsul.setName("tbKonsul"); // NOI18N
        tbKonsul.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbKonsulMouseClicked(evt);
            }
        });
        tbKonsul.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbKonsulKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbKonsulKeyReleased(evt);
            }
        });
        Scroll.setViewportView(tbKonsul);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

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
        panelGlass8.add(BtnPrint);

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
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 3, 9));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tgl. Permintaan : ");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(100, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "21-12-2023" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari1);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass9.add(jLabel21);

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "21-12-2023" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari2);

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
        });
        panelGlass9.add(BtnCari);

        BtnAll.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('2');
        BtnAll.setText("Semua Data");
        BtnAll.setToolTipText("Alt+2");
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
        panelGlass9.add(BtnAll);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass9.add(jLabel7);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass9.add(LCount);

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        panelGlass7.setName("panelGlass7"); // NOI18N
        panelGlass7.setPreferredSize(new java.awt.Dimension(44, 293));
        panelGlass7.setLayout(null);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Pasien : ");
        jLabel3.setName("jLabel3"); // NOI18N
        panelGlass7.add(jLabel3);
        jLabel3.setBounds(0, 10, 125, 23);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Dari Unit : ");
        jLabel4.setName("jLabel4"); // NOI18N
        panelGlass7.add(jLabel4);
        jLabel4.setBounds(0, 38, 125, 23);

        TNoRW.setEditable(false);
        TNoRW.setForeground(new java.awt.Color(0, 0, 0));
        TNoRW.setName("TNoRW"); // NOI18N
        panelGlass7.add(TNoRW);
        TNoRW.setBounds(125, 10, 131, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        panelGlass7.add(TNoRM);
        TNoRM.setBounds(260, 10, 70, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setName("TPasien"); // NOI18N
        panelGlass7.add(TPasien);
        TPasien.setBounds(335, 10, 360, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Ke Unit : ");
        jLabel5.setName("jLabel5"); // NOI18N
        panelGlass7.add(jLabel5);
        jLabel5.setBounds(360, 38, 60, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Permintaan Konsul : ");
        jLabel10.setName("jLabel10"); // NOI18N
        panelGlass7.add(jLabel10);
        jLabel10.setBounds(0, 66, 125, 23);

        TnmdokterMinta.setEditable(false);
        TnmdokterMinta.setForeground(new java.awt.Color(0, 0, 0));
        TnmdokterMinta.setName("TnmdokterMinta"); // NOI18N
        panelGlass7.add(TnmdokterMinta);
        TnmdokterMinta.setBounds(125, 260, 540, 23);

        BtnDokterMinta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokterMinta.setMnemonic('2');
        BtnDokterMinta.setToolTipText("Alt+2");
        BtnDokterMinta.setName("BtnDokterMinta"); // NOI18N
        BtnDokterMinta.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokterMinta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokterMintaActionPerformed(evt);
            }
        });
        panelGlass7.add(BtnDokterMinta);
        BtnDokterMinta.setBounds(670, 260, 28, 23);

        TtglMinta.setEditable(false);
        TtglMinta.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "21-12-2023" }));
        TtglMinta.setDisplayFormat("dd-MM-yyyy");
        TtglMinta.setName("TtglMinta"); // NOI18N
        TtglMinta.setOpaque(false);
        panelGlass7.add(TtglMinta);
        TtglMinta.setBounds(125, 232, 100, 23);

        Scroll7.setName("Scroll7"); // NOI18N
        Scroll7.setOpaque(true);

        TPermintaan.setColumns(20);
        TPermintaan.setRows(5);
        TPermintaan.setName("TPermintaan"); // NOI18N
        TPermintaan.setPreferredSize(new java.awt.Dimension(170, 2000));
        TPermintaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPermintaanKeyPressed(evt);
            }
        });
        Scroll7.setViewportView(TPermintaan);

        panelGlass7.add(Scroll7);
        Scroll7.setBounds(125, 66, 570, 160);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Tgl. Permintaan : ");
        jLabel11.setName("jLabel11"); // NOI18N
        panelGlass7.add(jLabel11);
        jLabel11.setBounds(0, 232, 125, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Jam Permintaan : ");
        jLabel12.setName("jLabel12"); // NOI18N
        panelGlass7.add(jLabel12);
        jLabel12.setBounds(230, 232, 100, 23);

        cmbJam.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJamMouseReleased(evt);
            }
        });
        panelGlass7.add(cmbJam);
        cmbJam.setBounds(333, 232, 45, 23);

        cmbMnt.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMntMouseReleased(evt);
            }
        });
        panelGlass7.add(cmbMnt);
        cmbMnt.setBounds(384, 232, 45, 23);

        cmbDtk.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtkMouseReleased(evt);
            }
        });
        panelGlass7.add(cmbDtk);
        cmbDtk.setBounds(436, 232, 45, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Dengan Dokter : ");
        jLabel13.setName("jLabel13"); // NOI18N
        panelGlass7.add(jLabel13);
        jLabel13.setBounds(0, 260, 125, 23);

        label_status.setForeground(new java.awt.Color(0, 0, 0));
        label_status.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_status.setText("Status Surat Konsul : ");
        label_status.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        label_status.setName("label_status"); // NOI18N
        panelGlass7.add(label_status);
        label_status.setBounds(705, 260, 410, 23);

        cmbUnitKe.setForeground(new java.awt.Color(0, 0, 0));
        cmbUnitKe.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        cmbUnitKe.setName("cmbUnitKe"); // NOI18N
        panelGlass7.add(cmbUnitKe);
        cmbUnitKe.setBounds(422, 38, 200, 23);

        cmbUnitDari.setForeground(new java.awt.Color(0, 0, 0));
        cmbUnitDari.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        cmbUnitDari.setName("cmbUnitDari"); // NOI18N
        panelGlass7.add(cmbUnitDari);
        cmbUnitDari.setBounds(125, 38, 190, 23);

        internalFrame1.add(panelGlass7, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRW.getText().trim().equals("")) {
            Valid.textKosong(TNoRW, "Pasien");
        } else if (cmbUnitKe.getSelectedIndex() == 0) {
            Valid.textKosong(cmbUnitKe, "Ke Unit");
            cmbUnitKe.requestFocus();
        } else if (TPermintaan.getText().equals("")) {
            Valid.textKosong(TPermintaan, "Kalimat Permintaan Konsul");
            TPermintaan.requestFocus();
        } else {
            Sequel.menyimpan("surat_konsul_unit_ranap", "'" + TNoRW.getText() + "','" + cmbUnitDari.getSelectedItem().toString() + "',"
                    + "'" + cmbUnitKe.getSelectedItem().toString() + "','" + TPermintaan.getText() + "','" + Valid.SetTgl(TtglMinta.getSelectedItem() + "") + "',"
                    + "'" + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "',"
                    + "'" + kddokter + "','','0000-00-00','00:00:00','-','" + Sequel.cariIsi("select now()") + "','Belum'", "Surat Konsul Antar Unit");
            
            TCari.setText(TNoRW.getText());            
            tbKonsul.requestFocus();
            emptTeks();
            tampil();            
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnEdit);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (TNoRW.getText().trim().equals("")) {
            Valid.textKosong(TNoRW, "Pasien");
        } else if (cmbUnitKe.getSelectedIndex() == 0) {
            Valid.textKosong(cmbUnitKe, "Ke Unit");
            cmbUnitKe.requestFocus();
        } else if (TPermintaan.getText().equals("")) {
            Valid.textKosong(TPermintaan, "Kalimat Permintaan Konsul");
            TPermintaan.requestFocus();
        } else {
            if (tbKonsul.getSelectedRow() > -1) {
                if (sttsJawab.equals("SUDAH")) {
                    JOptionPane.showMessageDialog(null, "Permintaan konsul antar unit sudah dijawab, proses tidak dapat dilanjutkan..!!");
                    tampil();
                    emptTeks();
                } else {
                    Sequel.mengedit("surat_konsul_unit_ranap", "waktu_simpan=?",
                            "unit_dari=?, unit_ke=?, permintaan_konsul=?, tgl_minta=?, jam_minta=?, nip_dokter_minta=?", 7, new String[]{
                                cmbUnitDari.getSelectedItem().toString(), cmbUnitKe.getSelectedItem().toString(), TPermintaan.getText(), 
                                Valid.SetTgl(TtglMinta.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(), 
                                kddokter, wktSimpan
                            });
                    TCari.setText(TNoRW.getText());
                    tbKonsul.requestFocus();
                    emptTeks();
                    tampil();
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih salah satu datanya terlebih dahulu..!!");
            }
        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnEditActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnEdit, BtnKeluar);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        emptTeks();
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
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            tbKonsul.requestFocus();
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

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
       TCari.setText("");
       emptTeks();
       tampil();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnAllActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnCari, TNoRW);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbKonsulMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbKonsulMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbKonsulMouseClicked

    private void tbKonsulKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbKonsulKeyPressed
        if(tabMode.getRowCount()!=0){
            if(evt.getKeyCode()==KeyEvent.VK_SHIFT){
                TCari.setText("");
                TCari.requestFocus();
            }           
        }
}//GEN-LAST:event_tbKonsulKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
        Sequel.cariIsiComboDB("SELECT nm_sps from spesialis WHERE kd_sps not in ('-','S0021') and "
                + "(nm_sps not like '%patologi%' and nm_sps not like '%radiologi%' and nm_sps not like '%anaste%') ORDER BY nm_sps", cmbUnitDari);
        Sequel.cariIsiComboDB("SELECT nm_sps from spesialis WHERE kd_sps not in ('-','S0021') and "
                + "(nm_sps not like '%patologi%' and nm_sps not like '%radiologi%' and nm_sps not like '%anaste%')ORDER BY nm_sps", cmbUnitKe);
    }//GEN-LAST:event_formWindowOpened

    private void tbKonsulKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbKonsulKeyReleased
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }          
        }
    }//GEN-LAST:event_tbKonsulKeyReleased

    private void BtnDokterMintaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterMintaActionPerformed
        akses.setform("DlgSuratKonsulUnit");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDokterMintaActionPerformed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbKonsul.getSelectedRow() > -1) {
            if (sttsJawab.equals("SUDAH")) {
                JOptionPane.showMessageDialog(null, "Permintaan konsul antar unit sudah dijawab, proses tidak dapat dilanjutkan..!!");
                tampil();
                emptTeks();
            } else {
                x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    if (Sequel.queryu2tf("delete from surat_konsul_unit_ranap where waktu_simpan=?", 1, new String[]{wktSimpan
                    }) == true) {
                        TCari.setText("");
                        tampil();
                        emptTeks();
                    } else {
                        JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih salah satu datanya terlebih dahulu..!!");
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnHapusActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
    }//GEN-LAST:event_BtnHapusKeyPressed

    private void TPermintaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPermintaanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            BtnDokterMinta.requestFocus();
        }
    }//GEN-LAST:event_TPermintaanKeyPressed

    private void cmbJamMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJamMouseReleased
        AutoCompleteDecorator.decorate(cmbJam);
    }//GEN-LAST:event_cmbJamMouseReleased

    private void cmbMntMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMntMouseReleased
        AutoCompleteDecorator.decorate(cmbMnt);
    }//GEN-LAST:event_cmbMntMouseReleased

    private void cmbDtkMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtkMouseReleased
        AutoCompleteDecorator.decorate(cmbDtk);
    }//GEN-LAST:event_cmbDtkMouseReleased

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnBatal.requestFocus();
        } else {
            if (tbKonsul.getSelectedRow() > -1) {
                if (wktSimpan.equals("") && sttsJawab.equals("")) {
                    JOptionPane.showMessageDialog(rootPane, "Silahkan pilih salah satu datanya terlebih dahulu..!!");
                } else {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    Map<String, Object> param = new HashMap<>();
                    param.put("namars", akses.getnamars());
                    param.put("logo", Sequel.cariGambar("select logo from setting"));
                    param.put("unit", "Dari Unit : " + cmbUnitDari.getSelectedItem().toString() + ", Ke Unit : " + cmbUnitKe.getSelectedItem().toString());

                    if (sttsJawab.equals("BELUM")) {
                        Valid.MyReport("rptSuratKonsulRanapKosong.jasper", "report", "::[ Cetak Surat Konsul Antar Unit Rawat Inap ]::",
                                "select p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllahir, date_format(sk.tgl_minta,'%d-%m-%Y') tglminta, "
                                + "time_format(sk.jam_minta,'%H:%i Wita') jamminta, sk.permintaan_konsul, d1.nm_dokter drMinta, sk.jawaban_konsul, "
                                + "if(sk.status_dijawab='Belum','TANGGAL : -, JAM : -',concat('TANGGAL : ',date_format(sk.tgl_jawab,'%d-%m-%Y'),', JAM : ',time_format(sk.jam_jawab,'%H:%i'),' Wita')) tgljawab, "
                                + "if(sk.status_dijawab='Belum','( ............................................ )',concat('(',d2.nm_dokter,')')) drJawab from surat_konsul_unit_ranap sk "
                                + "inner join reg_periksa rp on rp.no_rawat=sk.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                                + "inner join dokter d1 on d1.kd_dokter=sk.nip_dokter_minta inner join dokter d2 on d2.kd_dokter=sk.nip_dokter_jawab "
                                + "where sk.waktu_simpan='" + wktSimpan + "'", param);
                    } else {
                        Valid.MyReport("rptSuratKonsulRanap.jasper", "report", "::[ Cetak Surat Konsul Antar Unit Rawat Inap ]::",
                                "select p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllahir, date_format(sk.tgl_minta,'%d-%m-%Y') tglminta, "
                                + "time_format(sk.jam_minta,'%H:%i Wita') jamminta, sk.permintaan_konsul, d1.nm_dokter drMinta, sk.jawaban_konsul, "
                                + "if(sk.status_dijawab='Belum','TANGGAL : -, JAM : -',concat('TANGGAL : ',date_format(sk.tgl_jawab,'%d-%m-%Y'),', JAM : ',time_format(sk.jam_jawab,'%H:%i'),' Wita')) tgljawab, "
                                + "if(sk.status_dijawab='Belum','( ............................................ )',concat('(',d2.nm_dokter,')')) drJawab from surat_konsul_unit_ranap sk "
                                + "inner join reg_periksa rp on rp.no_rawat=sk.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                                + "inner join dokter d1 on d1.kd_dokter=sk.nip_dokter_minta inner join dokter d2 on d2.kd_dokter=sk.nip_dokter_jawab "
                                + "where sk.waktu_simpan='" + wktSimpan + "'", param);
                    }

                    TCari.setText(TNoRW.getText());
                    tbKonsul.requestFocus();
                    emptTeks();
                    tampil();
                    this.setCursor(Cursor.getDefaultCursor());
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih salah satu datanya terlebih dahulu..!!");
            }
        }
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnAll);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgSuratKonsulUnit dialog = new DlgSuratKonsulUnit(new javax.swing.JFrame(), true);
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
    private widget.Button BtnDokterMinta;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Label LCount;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll7;
    public widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRW;
    private widget.TextBox TPasien;
    private widget.TextArea TPermintaan;
    private widget.TextBox TnmdokterMinta;
    private widget.Tanggal TtglMinta;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbMnt;
    private widget.ComboBox cmbUnitDari;
    private widget.ComboBox cmbUnitKe;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel3;
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private javax.swing.JPanel jPanel3;
    private widget.Label label_status;
    private widget.panelisi panelGlass7;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.Table tbKonsul;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT *, p.no_rkm_medis, p.nm_pasien, date_format( sk.tgl_minta, '%d-%m-%Y' ) tglminta, "
                    + "time_format( sk.jam_minta, '%H:%i' ) jamminta, d.nm_dokter FROM surat_konsul_unit_ranap sk "
                    + "INNER JOIN reg_periksa rp ON rp.no_rawat = sk.no_rawat INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                    + "INNER JOIN dokter d ON d.kd_dokter = sk.nip_dokter_minta WHERE "
                    + "sk.tgl_minta between ? and ? and sk.no_rawat like ? or "
                    + "sk.tgl_minta between ? and ? and p.no_rkm_medis like ? or "
                    + "sk.tgl_minta between ? and ? and p.nm_pasien like ? or "
                    + "sk.tgl_minta between ? and ? and d.nm_dokter like ? or "
                    + "sk.tgl_minta between ? and ? and sk.permintaan_konsul like ? or "
                    + "sk.tgl_minta between ? and ? and sk.jawaban_konsul like ? order by sk.tgl_minta desc, sk.no_rawat desc");
            try {
                ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(3, "%" + TCari.getText().trim() + "%");                
                ps.setString(4, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(5, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(6, "%" + TCari.getText().trim() + "%");                
                ps.setString(7, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(8, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(9, "%" + TCari.getText().trim() + "%");                
                ps.setString(10, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(11, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(12, "%" + TCari.getText().trim() + "%");                
                ps.setString(13, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(14, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(15, "%" + TCari.getText().trim() + "%");                
                ps.setString(16, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(17, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(18, "%" + TCari.getText().trim() + "%");                                
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("unit_dari"),
                        rs.getString("unit_ke"),
                        rs.getString("permintaan_konsul"),
                        rs.getString("tglminta"),
                        rs.getString("jamminta"),
                        rs.getString("nm_dokter"),
                        rs.getString("tgl_minta"),
                        rs.getString("jam_minta"),
                        rs.getString("nip_dokter_minta"),
                        rs.getString("waktu_simpan"),
                        rs.getString("status_dijawab").toUpperCase()
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
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode.getRowCount());
    }

    public void emptTeks() {
        cmbUnitDari.setSelectedIndex(0);
        cmbUnitKe.setSelectedIndex(0);
        TPermintaan.setText("");
        TtglMinta.setDate(new Date());
        cmbJam.setSelectedIndex(0);
        cmbMnt.setSelectedIndex(0);
        cmbDtk.setSelectedIndex(0);
        kddokter = "-";
        TnmdokterMinta.setText("-");
        DTPCari1.setDate(new Date());
        DTPCari2.setDate(new Date());
        wktSimpan = "";
        sttsJawab = "";
        label_status.setText("Status Surat Konsul : -");
    }

    private void getData() {
        kddokter = "";
        wktSimpan = "";
        sttsJawab = "";

        if (tbKonsul.getSelectedRow() != -1) {
            TNoRW.setText(tbKonsul.getValueAt(tbKonsul.getSelectedRow(), 0).toString());
            TNoRM.setText(tbKonsul.getValueAt(tbKonsul.getSelectedRow(), 1).toString());
            TPasien.setText(tbKonsul.getValueAt(tbKonsul.getSelectedRow(), 2).toString());
            cmbUnitDari.setSelectedItem(tbKonsul.getValueAt(tbKonsul.getSelectedRow(), 3).toString());
            cmbUnitKe.setSelectedItem(tbKonsul.getValueAt(tbKonsul.getSelectedRow(), 4).toString());
            TPermintaan.setText(tbKonsul.getValueAt(tbKonsul.getSelectedRow(), 5).toString());
            Valid.SetTgl(TtglMinta, tbKonsul.getValueAt(tbKonsul.getSelectedRow(), 9).toString());
            cmbJam.setSelectedItem(tbKonsul.getValueAt(tbKonsul.getSelectedRow(), 10).toString().substring(0, 2));
            cmbMnt.setSelectedItem(tbKonsul.getValueAt(tbKonsul.getSelectedRow(), 10).toString().substring(3, 5));
            cmbDtk.setSelectedItem(tbKonsul.getValueAt(tbKonsul.getSelectedRow(), 10).toString().substring(6, 8));
            kddokter = tbKonsul.getValueAt(tbKonsul.getSelectedRow(), 11).toString();
            TnmdokterMinta.setText(tbKonsul.getValueAt(tbKonsul.getSelectedRow(), 8).toString());
            wktSimpan = tbKonsul.getValueAt(tbKonsul.getSelectedRow(), 12).toString();
            sttsJawab = tbKonsul.getValueAt(tbKonsul.getSelectedRow(), 13).toString();
            label_status.setText("Status Surat Konsul : " + sttsJawab + " TERJAWAB.");
        }
    }
    
    public void isCek(){
       BtnSimpan.setEnabled(akses.getpermintaan_lab());       
       BtnEdit.setEnabled(akses.getpermintaan_lab());
       BtnHapus.setEnabled(akses.getpermintaan_lab());
    }
    
    public void setData(String norw) {
        TNoRW.setText(norw);        
        cmbJam.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk.setSelectedIndex(0);
        TCari.setText(norw);
        Valid.SetTgl(DTPCari1, Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + norw + "'"));
        
        if (Sequel.cariIsi("select ifnull(kd_dokter,'') from dokter where kd_dokter='" + akses.getkode() + "'").equals("")) {
            kddokter = "-";
            TnmdokterMinta.setText("-");
        } else {
            kddokter = akses.getkode();
            TnmdokterMinta.setText(Sequel.cariIsi("select nm_dokter from dokter where kd_dokter='" + akses.getkode() + "'"));
        }
        
        try {
            ps1 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien FROM reg_periksa rp "
                    + "INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis WHERE rp.no_rawat = '" + norw + "'");
            try {
                rs1 = ps1.executeQuery();
                while (rs1.next()) {                    
                    TNoRM.setText(rs1.getString("no_rkm_medis"));
                    TPasien.setText(rs1.getString("nm_pasien"));
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
}
