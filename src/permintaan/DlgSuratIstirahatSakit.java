    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgSpesialis.java
 *
 * Created on May 23, 2010, 1:25:13 AM
 */

package permintaan;

import rekammedis.*;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
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
import simrskhanza.DlgCariDokter;

/**
 *
 * @author dosen
 */
public class DlgSuratIstirahatSakit extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps1;
    private ResultSet rs, rs1;
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    private String bln = "", thn = "", HRmulai = "", HRselesai = "";
    private int x = 0;

    /** Creates new form DlgSpesialis
     * @param parent
     * @param modal */
    public DlgSuratIstirahatSakit(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        Object[] row = {"No. Rawat", "No. Surat", "No. RM", "Nama Pasien", "Jns. Kelamin", "Tempat Lahir", "Tgl. Lahir", 
            "Pekerjaan", "Alamat", "Lama Izin", "Satuan", "Dari Tgl.", "Sampai Tgl.", "Nama Dokter", "nik",
            "tglmulai", "tglselesai", "nosrt"};
        tabMode=new DefaultTableModel(null,row){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };

        tbSurat.setModel(tabMode);
        tbSurat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbSurat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 18; i++) {
            TableColumn column = tbSurat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(180);
            } else if (i == 2) {
                column.setPreferredWidth(65);
            } else if (i == 3) {
                column.setPreferredWidth(250);
            } else if (i == 4) {
                column.setPreferredWidth(90);
            } else if (i == 5) {
                column.setPreferredWidth(200);
            } else if (i == 6) {
                column.setPreferredWidth(80);
            } else if (i == 7) {
                column.setPreferredWidth(150);
            } else if (i == 8) {
                column.setPreferredWidth(300);
            } else if (i == 9) {
                column.setPreferredWidth(75);
            } else if (i == 10) {
                column.setPreferredWidth(75);
            } else if (i == 11) {
                column.setPreferredWidth(80);
            } else if (i == 12) {
                column.setPreferredWidth(80);
            } else if (i == 13) {
                column.setPreferredWidth(260);
            } else if (i == 14) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 15) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 16) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 17) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }

        tbSurat.setDefaultRenderer(Object.class, new WarnaTable());
        
        TCari.setDocument(new batasInput((int) 100).getKata(TCari));
        TLamaIzin.setDocument(new batasInput((byte) 3).getOnlyAngka(TLamaIzin));
        TTempLahr.setDocument(new batasInput((int) 150).getKata(TTempLahr));
        TPekerjaan.setDocument(new batasInput((int) 150).getKata(TPekerjaan));
        
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgSuratIstirahatSakit")) {
                    if (dokter.getTable().getSelectedRow() != -1) {
                        Tkddokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                        Tnmdokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                    }
                    BtnDokter.requestFocus();
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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnSuratIzin = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbSurat = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
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
        TNoSurat = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel5 = new widget.Label();
        jLabel8 = new widget.Label();
        TTempLahr = new widget.TextBox();
        jLabel9 = new widget.Label();
        Tjk = new widget.TextBox();
        jLabel10 = new widget.Label();
        TPekerjaan = new widget.TextBox();
        jLabel11 = new widget.Label();
        TAlamat = new widget.TextBox();
        jLabel12 = new widget.Label();
        TLamaIzin = new widget.TextBox();
        cmbSatuan = new widget.ComboBox();
        jLabel13 = new widget.Label();
        Tgl1 = new widget.Tanggal();
        jLabel14 = new widget.Label();
        Tgl2 = new widget.Tanggal();
        jLabel15 = new widget.Label();
        Tkddokter = new widget.TextBox();
        Tnmdokter = new widget.TextBox();
        BtnDokter = new widget.Button();
        TtglLahir = new widget.TextBox();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnSuratIzin.setBackground(new java.awt.Color(255, 255, 255));
        MnSuratIzin.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSuratIzin.setForeground(new java.awt.Color(0, 0, 0));
        MnSuratIzin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSuratIzin.setText("Cetak Surat Istirahat Sakit");
        MnSuratIzin.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSuratIzin.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSuratIzin.setName("MnSuratIzin"); // NOI18N
        MnSuratIzin.setPreferredSize(new java.awt.Dimension(190, 26));
        MnSuratIzin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSuratIzinActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnSuratIzin);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Surat Istirahat Sakit ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbSurat.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbSurat.setComponentPopupMenu(jPopupMenu1);
        tbSurat.setName("tbSurat"); // NOI18N
        tbSurat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSuratMouseClicked(evt);
            }
        });
        tbSurat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbSuratKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbSuratKeyReleased(evt);
            }
        });
        Scroll.setViewportView(tbSurat);

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
        jLabel19.setText("Tanggal :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setEditable(false);
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "27-04-2023" }));
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

        DTPCari2.setEditable(false);
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "27-04-2023" }));
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
        panelGlass7.setPreferredSize(new java.awt.Dimension(44, 217));
        panelGlass7.setLayout(null);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Pasien : ");
        jLabel3.setName("jLabel3"); // NOI18N
        panelGlass7.add(jLabel3);
        jLabel3.setBounds(0, 10, 105, 23);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("No. Surat : ");
        jLabel4.setName("jLabel4"); // NOI18N
        panelGlass7.add(jLabel4);
        jLabel4.setBounds(0, 38, 105, 23);

        TNoRW.setEditable(false);
        TNoRW.setForeground(new java.awt.Color(0, 0, 0));
        TNoRW.setName("TNoRW"); // NOI18N
        panelGlass7.add(TNoRW);
        TNoRW.setBounds(105, 10, 131, 23);

        TNoSurat.setEditable(false);
        TNoSurat.setForeground(new java.awt.Color(0, 0, 0));
        TNoSurat.setName("TNoSurat"); // NOI18N
        panelGlass7.add(TNoSurat);
        TNoSurat.setBounds(105, 38, 180, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        panelGlass7.add(TNoRM);
        TNoRM.setBounds(240, 10, 70, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setName("TPasien"); // NOI18N
        panelGlass7.add(TPasien);
        TPasien.setBounds(315, 10, 360, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Tgl. Lahir : ");
        jLabel5.setName("jLabel5"); // NOI18N
        panelGlass7.add(jLabel5);
        jLabel5.setBounds(0, 66, 105, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Tempat Lahir : ");
        jLabel8.setName("jLabel8"); // NOI18N
        panelGlass7.add(jLabel8);
        jLabel8.setBounds(290, 38, 90, 23);

        TTempLahr.setForeground(new java.awt.Color(0, 0, 0));
        TTempLahr.setName("TTempLahr"); // NOI18N
        TTempLahr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTempLahrKeyPressed(evt);
            }
        });
        panelGlass7.add(TTempLahr);
        TTempLahr.setBounds(382, 38, 293, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Jenis Kelamin : ");
        jLabel9.setName("jLabel9"); // NOI18N
        panelGlass7.add(jLabel9);
        jLabel9.setBounds(494, 66, 90, 23);

        Tjk.setEditable(false);
        Tjk.setForeground(new java.awt.Color(0, 0, 0));
        Tjk.setName("Tjk"); // NOI18N
        panelGlass7.add(Tjk);
        Tjk.setBounds(585, 66, 90, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Pekerjaan : ");
        jLabel10.setName("jLabel10"); // NOI18N
        panelGlass7.add(jLabel10);
        jLabel10.setBounds(0, 94, 105, 23);

        TPekerjaan.setForeground(new java.awt.Color(0, 0, 0));
        TPekerjaan.setName("TPekerjaan"); // NOI18N
        TPekerjaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPekerjaanKeyPressed(evt);
            }
        });
        panelGlass7.add(TPekerjaan);
        TPekerjaan.setBounds(105, 94, 420, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Alamat Domisili : ");
        jLabel11.setName("jLabel11"); // NOI18N
        panelGlass7.add(jLabel11);
        jLabel11.setBounds(0, 122, 105, 23);

        TAlamat.setForeground(new java.awt.Color(0, 0, 0));
        TAlamat.setName("TAlamat"); // NOI18N
        TAlamat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TAlamatKeyPressed(evt);
            }
        });
        panelGlass7.add(TAlamat);
        TAlamat.setBounds(105, 122, 570, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Izin Selama : ");
        jLabel12.setName("jLabel12"); // NOI18N
        panelGlass7.add(jLabel12);
        jLabel12.setBounds(0, 150, 105, 23);

        TLamaIzin.setForeground(new java.awt.Color(0, 0, 0));
        TLamaIzin.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TLamaIzin.setName("TLamaIzin"); // NOI18N
        TLamaIzin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TLamaIzinKeyPressed(evt);
            }
        });
        panelGlass7.add(TLamaIzin);
        TLamaIzin.setBounds(105, 150, 40, 23);

        cmbSatuan.setForeground(new java.awt.Color(0, 0, 0));
        cmbSatuan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Hari", "Minggu", "Bulan" }));
        cmbSatuan.setName("cmbSatuan"); // NOI18N
        panelGlass7.add(cmbSatuan);
        cmbSatuan.setBounds(150, 150, 70, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Mulai Tgl. : ");
        jLabel13.setName("jLabel13"); // NOI18N
        panelGlass7.add(jLabel13);
        jLabel13.setBounds(220, 150, 70, 23);

        Tgl1.setEditable(false);
        Tgl1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "27-04-2023" }));
        Tgl1.setDisplayFormat("dd-MM-yyyy");
        Tgl1.setName("Tgl1"); // NOI18N
        Tgl1.setOpaque(false);
        panelGlass7.add(Tgl1);
        Tgl1.setBounds(292, 150, 100, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Sampai Dengan Tgl. : ");
        jLabel14.setName("jLabel14"); // NOI18N
        panelGlass7.add(jLabel14);
        jLabel14.setBounds(394, 150, 120, 23);

        Tgl2.setEditable(false);
        Tgl2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "27-04-2023" }));
        Tgl2.setDisplayFormat("dd-MM-yyyy");
        Tgl2.setName("Tgl2"); // NOI18N
        Tgl2.setOpaque(false);
        panelGlass7.add(Tgl2);
        Tgl2.setBounds(517, 150, 100, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Diketahui Oleh Dokter : ");
        jLabel15.setName("jLabel15"); // NOI18N
        panelGlass7.add(jLabel15);
        jLabel15.setBounds(0, 178, 140, 23);

        Tkddokter.setEditable(false);
        Tkddokter.setForeground(new java.awt.Color(0, 0, 0));
        Tkddokter.setName("Tkddokter"); // NOI18N
        panelGlass7.add(Tkddokter);
        Tkddokter.setBounds(144, 178, 90, 23);

        Tnmdokter.setEditable(false);
        Tnmdokter.setForeground(new java.awt.Color(0, 0, 0));
        Tnmdokter.setName("Tnmdokter"); // NOI18N
        panelGlass7.add(Tnmdokter);
        Tnmdokter.setBounds(237, 178, 390, 23);

        BtnDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter.setMnemonic('2');
        BtnDokter.setToolTipText("Alt+2");
        BtnDokter.setName("BtnDokter"); // NOI18N
        BtnDokter.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokterActionPerformed(evt);
            }
        });
        panelGlass7.add(BtnDokter);
        BtnDokter.setBounds(632, 178, 28, 23);

        TtglLahir.setEditable(false);
        TtglLahir.setForeground(new java.awt.Color(0, 0, 0));
        TtglLahir.setName("TtglLahir"); // NOI18N
        panelGlass7.add(TtglLahir);
        TtglLahir.setBounds(105, 66, 160, 23);

        internalFrame1.add(panelGlass7, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRW.getText().trim().equals("")) {
            Valid.textKosong(TNoRW, "Pasien");
        } else if (TLamaIzin.getText().trim().equals("")) {
            Valid.textKosong(TLamaIzin, "Lama izin istirahat sakit");
            TLamaIzin.requestFocus();
        } else if (Tkddokter.getText().equals("") || Tkddokter.getText().equals("-") || Tkddokter.getText().equals("--")) {
            Valid.textKosong(Tkddokter, "Diketahui Oleh Dokter");
            BtnDokter.requestFocus();
        } else {
            autoNomorSurat();
            Sequel.menyimpan("surat_istirahat_sakit", "'" + TNoRW.getText() + "','" + TNoSurat.getText() + "',"
                    + "'" + TLamaIzin.getText() + "','" + cmbSatuan.getSelectedItem().toString() + "',"
                    + "'" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "','" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "',"
                    + "'" + Tkddokter.getText() + "','" + Sequel.cariIsi("select date(now())") + "','" + TTempLahr.getText() + "',"
                    + "'" + TPekerjaan.getText() + "','" + TAlamat.getText() + "'", "Surat Istirahat Sakit");
            
            TCari.setText(TNoRW.getText());
            tbSurat.requestFocus();
            tampil();
            emptTeks();
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
            Valid.pindah(evt,TNoSurat,BtnBatal);
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
        } else if (TLamaIzin.getText().trim().equals("")) {
            Valid.textKosong(TLamaIzin, "Lama izin istirahat sakit");
            TLamaIzin.requestFocus();
        } else if (Tkddokter.getText().equals("")) {
            Valid.textKosong(Tkddokter, "Diketahui Oleh Dokter");
            BtnDokter.requestFocus();
        } else if (Sequel.cariInteger("select count(-1) from surat_istirahat_sakit where no_rawat='" + TNoRW.getText() + "'") == 0) {
            JOptionPane.showMessageDialog(null, "Data surat istirahat sakit pasien yg. bernama " + TPasien.getText() + "    \n"
                    + "dg. no. rawat " + TNoRW.getText() + " belum ada tersimpan...!!!!");
        } else {
            if (tbSurat.getSelectedRow() > -1) {
                Sequel.mengedit("surat_istirahat_sakit", "no_rawat=?", "lama_izin=?,satuan=?,tgl_mulai=?,sampai_tgl=?,kd_dokter=?,tmpt_lahir=?,pekerjaan=?,alamat_domisili=?", 9, new String[]{
                    TLamaIzin.getText(), cmbSatuan.getSelectedItem().toString(), Valid.SetTgl(Tgl1.getSelectedItem() + ""), 
                    Valid.SetTgl(Tgl2.getSelectedItem() + ""), Tkddokter.getText(), TTempLahr.getText(), TPekerjaan.getText(), TAlamat.getText(),
                    tbSurat.getValueAt(tbSurat.getSelectedRow(), 0).toString()
                });
                if (tabMode.getRowCount() != 0) {
                    TCari.setText(TNoRW.getText());
                    tbSurat.requestFocus();
                    tampil();
                }
                emptTeks();
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
            tbSurat.requestFocus();
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

    private void tbSuratMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSuratMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbSuratMouseClicked

    private void tbSuratKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbSuratKeyPressed
        if(tabMode.getRowCount()!=0){
            if(evt.getKeyCode()==KeyEvent.VK_SHIFT){
                TCari.setText("");
                TCari.requestFocus();
            }           
        }
}//GEN-LAST:event_tbSuratKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void tbSuratKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbSuratKeyReleased
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }          
        }
    }//GEN-LAST:event_tbSuratKeyReleased

    private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
        akses.setform("DlgSuratIstirahatSakit");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDokterActionPerformed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (TNoRW.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data pasiennya pada tabel...!!!!");
            tbSurat.requestFocus();
        } else {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.queryu2tf("delete from surat_istirahat_sakit where no_rawat=?", 1, new String[]{
                    tbSurat.getValueAt(tbSurat.getSelectedRow(), 0).toString()
                }) == true) {
                    TCari.setText("");
                    tampil();
                    emptTeks();                    
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                }
            }
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnHapusActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
    }//GEN-LAST:event_BtnHapusKeyPressed

    private void TLamaIzinKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TLamaIzinKeyPressed
        Valid.pindah(evt, TAlamat, cmbSatuan);
    }//GEN-LAST:event_TLamaIzinKeyPressed

    private void TTempLahrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTempLahrKeyPressed
        Valid.pindah(evt, TTempLahr, TPekerjaan);
    }//GEN-LAST:event_TTempLahrKeyPressed

    private void TPekerjaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPekerjaanKeyPressed
        Valid.pindah(evt, TTempLahr, TAlamat);
    }//GEN-LAST:event_TPekerjaanKeyPressed

    private void TAlamatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TAlamatKeyPressed
        Valid.pindah(evt, TPekerjaan, TLamaIzin);
    }//GEN-LAST:event_TAlamatKeyPressed

    private void MnSuratIzinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSuratIzinActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu datanya pada tabel...!!!!");
            tbSurat.requestFocus();
        } else if (Sequel.cariInteger("select count(-1) from surat_istirahat_sakit where no_rawat='" + TNoRW.getText() + "'") == 0) {
            JOptionPane.showMessageDialog(null, "Data surat istirahat sakit pasien yg. bernama " + TPasien.getText() + "    \n"
                    + "dg. no. rawat " + TNoRW.getText() + " masih belum tersimpan...!!!!");
        } else {
            if (tbSurat.getSelectedRow() > -1) {
                HRmulai = "";
                HRselesai = "";
                HRmulai = Sequel.cariIsi("select day(tgl_mulai) from surat_istirahat_sakit where no_rawat='" + TNoRW.getText() + "'");
                HRselesai = Sequel.cariIsi("select day(sampai_tgl) from surat_istirahat_sakit where no_rawat='" + TNoRW.getText() + "'");
                
                Map<String, Object> param = new HashMap<>();
                param.put("namars", akses.getnamars());
                param.put("alamatrs", akses.getalamatrs());
                param.put("kotars", akses.getkabupatenrs());
                param.put("propinsirs", akses.getpropinsirs());
                param.put("kontakrs", akses.getkontakrs());
                param.put("emailrs", akses.getemailrs());
                param.put("logo", Sequel.cariGambar("select logo_kabupaten from setting"));
                param.put("ttl", TTempLahr.getText() + ", " + TtglLahir.getText());
                param.put("lama_izin", TLamaIzin.getText() + " ( " + Sequel.Terbilang(Valid.SetAngka(TLamaIzin.getText())) + " )");
                param.put("tglmulai", HRmulai + " ( " + Sequel.Terbilang(Valid.SetAngka(HRmulai)) + " ) bulan "
                        + Sequel.bulanINDONESIA("select month(tgl_mulai) from surat_istirahat_sakit where no_rawat='" + TNoRW.getText() + "'") + " tahun "
                        + Sequel.cariIsi("select year(tgl_mulai) from surat_istirahat_sakit where no_rawat='" + TNoRW.getText() + "'"));
                
                param.put("tglselesai", HRselesai + " ( " + Sequel.Terbilang(Valid.SetAngka(HRselesai)) + " ) bulan "
                        + Sequel.bulanINDONESIA("select month(sampai_tgl) from surat_istirahat_sakit where no_rawat='" + TNoRW.getText() + "'") + " tahun "
                        + Sequel.cariIsi("select year(sampai_tgl) from surat_istirahat_sakit where no_rawat='" + TNoRW.getText() + "'"));
                
                param.put("tglsurat", akses.getkabupatenrs() + ", " 
                        + Sequel.cariIsi("select date_format(tgl_simpan,'%d') from surat_istirahat_sakit where no_rawat='" + TNoRW.getText() + "'") + " "
                        + Sequel.bulanINDONESIA("select month(tgl_simpan) from surat_istirahat_sakit where no_rawat='" + TNoRW.getText() + "'") + " "
                        + Sequel.cariIsi("select year(tgl_simpan) from surat_istirahat_sakit where no_rawat='" + TNoRW.getText() + "'"));
                
                Valid.MyReport("rptSuratSakit2.jasper", "report", "::[ Surat Istirahat Sakit ]::",
                        "SELECT rp.no_rawat, concat('848 / ',si.no_surat) nosrt, p.no_rkm_medis, p.nm_pasien, if(p.jk='L','Laki-laki','Perempuan') jk, "
                        + "si.pekerjaan, si.alamat_domisili, si.satuan, pg.nama dokter FROM reg_periksa rp "
                        + "INNER JOIN surat_istirahat_sakit si ON si.no_rawat = rp.no_rawat INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                        + "INNER JOIN pegawai pg ON pg.nik = si.kd_dokter WHERE si.no_rawat = '" + TNoRW.getText() + "'", param);
            }
            
            TCari.setText(TNoRW.getText());
            tbSurat.requestFocus();
            tampil();
            emptTeks();
        }
    }//GEN-LAST:event_MnSuratIzinActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgSuratIstirahatSakit dialog = new DlgSuratIstirahatSakit(new javax.swing.JFrame(), true);
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
    private widget.Button BtnDokter;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnSimpan;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnSuratIzin;
    private widget.ScrollPane Scroll;
    private widget.TextBox TAlamat;
    public widget.TextBox TCari;
    private widget.TextBox TLamaIzin;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRW;
    private widget.TextBox TNoSurat;
    private widget.TextBox TPasien;
    private widget.TextBox TPekerjaan;
    private widget.TextBox TTempLahr;
    private widget.Tanggal Tgl1;
    private widget.Tanggal Tgl2;
    private widget.TextBox Tjk;
    private widget.TextBox Tkddokter;
    private widget.TextBox Tnmdokter;
    private widget.TextBox TtglLahir;
    private widget.ComboBox cmbSatuan;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel3;
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.panelisi panelGlass7;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.Table tbSurat;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT rp.no_rawat, concat('848 / ',si.no_surat) nosrt, p.no_rkm_medis, p.nm_pasien, si.tmpt_lahir, if(p.jk='L','Laki-laki','Perempuan') jk, "
                    + "si.pekerjaan, si.alamat_domisili, si.lama_izin, si.satuan, date_format(si.tgl_mulai,'%d-%m-%Y') tglmulai, date_format(si.sampai_tgl,'%d-%m-%Y') tglselesai, "
                    + "pg.nama dokter, date_format(p.tgl_lahir,'%d-%m-%Y') tgllhr, pg.nik, si.tgl_mulai, si.sampai_tgl, si.no_surat from reg_periksa rp "
                    + "inner join surat_istirahat_sakit si on si.no_rawat=rp.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "inner join kelurahan kl on kl.kd_kel=p.kd_kel_domisili_pasien inner join kecamatan kc on kc.kd_kec=p.kd_kec_domisili_pasien "
                    + "inner join kabupaten kb on kb.kd_kab=p.kd_kab_domisili_pasien inner join pegawai pg on pg.nik=si.kd_dokter where "
                    + "si.tgl_simpan between ? and ? and rp.no_rawat like ? or "
                    + "si.tgl_simpan between ? and ? and si.no_surat like ? or "
                    + "si.tgl_simpan between ? and ? and p.no_rkm_medis like ? or "
                    + "si.tgl_simpan between ? and ? and p.nm_pasien like ? or "
                    + "si.tgl_simpan between ? and ? and si.tmpt_lahir like ? or "
                    + "si.tgl_simpan between ? and ? and if(p.jk='L','Laki-laki','Perempuan') like ? or "
                    + "si.tgl_simpan between ? and ? and si.pekerjaan like ? or "
                    + "si.tgl_simpan between ? and ? and pg.nama like ? or "
                    + "si.tgl_simpan between ? and ? and si.alamat_domisili like ? or "
                    + "si.tgl_simpan between ? and ? and concat('848 / ',si.no_surat) like ? order by si.tgl_simpan desc, si.no_surat desc");
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
                ps.setString(19, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(20, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(21, "%" + TCari.getText().trim() + "%");                
                ps.setString(22, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(23, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(24, "%" + TCari.getText().trim() + "%");                
                ps.setString(25, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(26, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(27, "%" + TCari.getText().trim() + "%");                 
                ps.setString(28, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(29, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(30, "%" + TCari.getText().trim() + "%"); 
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),
                        rs.getString("nosrt"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("jk"),
                        rs.getString("tmpt_lahir"),
                        rs.getString("tgllhr"),
                        rs.getString("pekerjaan"),
                        rs.getString("alamat_domisili"),
                        rs.getString("lama_izin"),
                        rs.getString("satuan"),
                        rs.getString("tglmulai"),
                        rs.getString("tglselesai"),
                        rs.getString("dokter"),
                        rs.getString("nik"),
                        rs.getString("tgl_mulai"),
                        rs.getString("sampai_tgl"),
                        rs.getString("no_surat")                        
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
        TNoRW.setText("");
        TNoRM.setText("");
        TPasien.setText("");
        TTempLahr.setText("");
        TtglLahir.setText("");
        Tjk.setText("");
        TPekerjaan.setText("");
        TAlamat.setText("");
        TLamaIzin.setText("");
        cmbSatuan.setSelectedIndex(0);
        Tgl1.setDate(new Date());
        Tgl2.setDate(new Date());
        Tkddokter.setText("");
        Tnmdokter.setText("");        
        DTPCari1.setDate(new Date());
        DTPCari2.setDate(new Date());
        autoNomorSurat();
    }

    private void getData() {
        if (tbSurat.getSelectedRow() != -1) {
            TNoRW.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 0).toString());
            TNoSurat.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 1).toString());
            TNoRM.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 2).toString());
            TPasien.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 3).toString());
            Tjk.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 4).toString());
            TTempLahr.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 5).toString());            
            TPekerjaan.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 7).toString());
            TAlamat.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 8).toString());
            TLamaIzin.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 9).toString());
            cmbSatuan.setSelectedItem(tbSurat.getValueAt(tbSurat.getSelectedRow(), 10).toString());
            Valid.SetTgl(Tgl1, tbSurat.getValueAt(tbSurat.getSelectedRow(), 15).toString());
            Valid.SetTgl(Tgl2, tbSurat.getValueAt(tbSurat.getSelectedRow(), 16).toString());
            Tnmdokter.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 13).toString());
            Tkddokter.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 14).toString());
            
            TtglLahir.setText(Sequel.cariIsi("select date_format(tgl_lahir,'%d') from pasien where no_rkm_medis='" + TNoRM.getText() + "'") + " "
                    + Sequel.bulanINDONESIA("select month(tgl_lahir) from pasien where no_rkm_medis='" + TNoRM.getText() + "'") + " "
                    + Sequel.cariIsi("select year(tgl_lahir) from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
            
        }
    }
    
    public void isCek(){
       BtnSimpan.setEnabled(akses.getsurat_sakit());       
       BtnEdit.setEnabled(akses.getsurat_sakit());
       BtnHapus.setEnabled(akses.getsurat_sakit());
    }
    
    public void setData(String norw) {
        autoNomorSurat();
        TLamaIzin.requestFocus();
        
        try {
            ps1 = koneksi.prepareStatement("SELECT rp.no_rawat, p.no_rkm_medis, p.nm_pasien, p.tmp_lahir, if(p.jk='L','Laki-laki','Perempuan') jk, p.pekerjaan, "
                    + "concat(p.alamat,', Kel./Desa ',kl.nm_kel,', Kec. ',kc.nm_kec,', Kab./Kodya ',kb.nm_kab) alamat, rp.status_lanjut FROM reg_periksa rp "
                    + "INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis INNER JOIN kelurahan kl ON kl.kd_kel = p.kd_kel_domisili_pasien "
                    + "INNER JOIN kecamatan kc ON kc.kd_kec = p.kd_kec_domisili_pasien INNER JOIN kabupaten kb ON kb.kd_kab = p.kd_kab_domisili_pasien "
                    + "WHERE rp.no_rawat = '" + norw + "'");
            try {
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    TNoRW.setText(rs1.getString("no_rawat"));
                    TNoRM.setText(rs1.getString("no_rkm_medis"));
                    TPasien.setText(rs1.getString("nm_pasien"));
                    TTempLahr.setText(rs1.getString("tmp_lahir"));
                    Tjk.setText(rs1.getString("jk"));
                    TPekerjaan.setText(rs1.getString("pekerjaan"));
                    TAlamat.setText(rs1.getString("alamat"));
                    TLamaIzin.setText("");
                    cmbSatuan.setSelectedIndex(0);
                    Tgl1.setDate(new Date());
                    Tgl2.setDate(new Date());                    
                    DTPCari1.setDate(new Date());
                    DTPCari2.setDate(new Date());
                    
                    if (rs1.getString("status_lanjut").equals("Ralan")) {
                        Tkddokter.setText(Sequel.cariIsi("select kd_dokter from reg_periksa where no_rawat='" + rs1.getString("no_rawat") + "'"));
                    } else {
                        Tkddokter.setText(Sequel.cariIsi("select ifnull(kd_dokter,'-') from dpjp_ranap where no_rawat='" + rs1.getString("no_rawat") + "'"));
                    }
                    
                    Tnmdokter.setText(Sequel.cariIsi("select nama from pegawai where nik='" + Tkddokter.getText() + "'"));
                    TtglLahir.setText(Sequel.cariIsi("select date_format(tgl_lahir,'%d') from pasien where no_rkm_medis='" + rs1.getString("no_rkm_medis") + "'") + " "
                            + Sequel.bulanINDONESIA("select month(tgl_lahir) from pasien where no_rkm_medis='" + rs1.getString("no_rkm_medis") + "'") + " "
                            + Sequel.cariIsi("select year(tgl_lahir) from pasien where no_rkm_medis='" + rs1.getString("no_rkm_medis") + "'"));
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
    
    public void autoNomorSurat() {
        bln = "";
        thn = "";
        bln = Sequel.bulanRomawi("select date_format(NOW(),'%m')");
        thn = Sequel.cariIsi("select date_format(NOW(),'%Y')");
        Valid.autoNomer6("select ifnull(MAX(CONVERT(LEFT(no_surat,4),signed)),0) from surat_istirahat_sakit where "
                + "no_surat like '%/ " + thn + "%' ", " / TU - MR / " + bln + " / " + thn, 4, TNoSurat);
    }
}
