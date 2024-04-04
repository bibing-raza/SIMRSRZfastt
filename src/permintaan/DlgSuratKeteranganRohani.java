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
import simrskhanza.DlgMasterNomorDokumen;

/**
 *
 * @author dosen
 */
public class DlgSuratKeteranganRohani extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps1;
    private ResultSet rs, rs1;
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    private DlgMasterNomorDokumen dokumen = new DlgMasterNomorDokumen(null, false);
    private String thn = "", nosrt = "", kddokter = "";
    private int x = 0;

    /** Creates new form DlgSpesialis
     * @param parent
     * @param modal */
    public DlgSuratKeteranganRohani(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        Object[] row = {"No. Rawat", "No. Surat", "No. RM", "Nama Pasien", "Tempat Lahir", "Tgl. Lahir",
            "Stts. Pernikahan", "Agama", "Pendidikan", "Pekerjaan", "Alamat", "Tgl. Psikiatrik", "Nama",
            "Jabatan", "Instansi", "No. Surat Dari", "Perihal", "Keperluan", "Dokter Yg. Memeriksa", "Tgl. Surat",
            "nosrt", "tgl_surat", "kd_dokter", "tgl_pemeriksaan", "no_dokumen"};
        tabMode=new DefaultTableModel(null,row){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };

        tbSurat.setModel(tabMode);
        tbSurat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbSurat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 25; i++) {
            TableColumn column = tbSurat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(120);
            } else if (i == 2) {
                column.setPreferredWidth(65);
            } else if (i == 3) {
                column.setPreferredWidth(250);
            } else if (i == 4) {
                column.setPreferredWidth(180);
            } else if (i == 5) {
                column.setPreferredWidth(80);
            } else if (i == 6) {
                column.setPreferredWidth(150);
            } else if (i == 7) {
                column.setPreferredWidth(100);
            } else if (i == 8) {
                column.setPreferredWidth(250);
            } else if (i == 9) {
                column.setPreferredWidth(200);
            } else if (i == 10) {
                column.setPreferredWidth(250);
            } else if (i == 11) {
                column.setPreferredWidth(85);
            } else if (i == 12) {
                column.setPreferredWidth(250);
            } else if (i == 13) {
                column.setPreferredWidth(250);
            } else if (i == 14) {
                column.setPreferredWidth(250);
            } else if (i == 15) {
                column.setPreferredWidth(200);
            } else if (i == 16) {
                column.setPreferredWidth(250);
            } else if (i == 17) {
                column.setPreferredWidth(250);
            } else if (i == 18) {
                column.setPreferredWidth(250);
            } else if (i == 19) {
                column.setPreferredWidth(75);
            } else if (i == 20) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 21) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 22) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 23) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 24) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }

        tbSurat.setDefaultRenderer(Object.class, new WarnaTable());
        
        TCari.setDocument(new batasInput((int) 100).getKata(TCari));        
        TTempLahr.setDocument(new batasInput((int) 150).getKata(TTempLahr));
        TPendidikan.setDocument(new batasInput((int) 100).getKata(TPendidikan));
        TPekerjaan.setDocument(new batasInput((int) 100).getKata(TPekerjaan));
        Tnm_tertulis.setDocument(new batasInput((int) 150).getKata(Tnm_tertulis));
        Tjabatan.setDocument(new batasInput((int) 200).getKata(Tjabatan));
        Tinstansi.setDocument(new batasInput((int) 200).getKata(Tinstansi));
        Tno_surat_dari.setDocument(new batasInput((int) 150).getKata(Tno_surat_dari));
        TPasien.setDocument(new batasInput((int) 40).getKata(TPasien));
        
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgSuratKeteranganRohani")) {
                    if (dokter.getTable().getSelectedRow() != -1) {
                        kddokter = dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString();
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
        
        dokumen.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgSuratKeteranganRohani")) {
                    if (dokumen.getTable().getSelectedRow() != -1) {
                        TnoDokumen.setText(dokumen.getTable().getValueAt(dokumen.getTable().getSelectedRow(), 2).toString());
                    }
                    BtnDokumen.requestFocus();
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
        MnSuratRohani = new javax.swing.JMenuItem();
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
        TPendidikan = new widget.TextBox();
        jLabel10 = new widget.Label();
        TPekerjaan = new widget.TextBox();
        jLabel11 = new widget.Label();
        TAlamat = new widget.TextBox();
        Ttgl_psikiatrik = new widget.Tanggal();
        jLabel15 = new widget.Label();
        Tnmdokter = new widget.TextBox();
        BtnDokter = new widget.Button();
        TtglLahir = new widget.TextBox();
        jLabel16 = new widget.Label();
        jLabel17 = new widget.Label();
        Tnm_tertulis = new widget.TextBox();
        jLabel12 = new widget.Label();
        Tstts_nikah = new widget.TextBox();
        jLabel13 = new widget.Label();
        Tagama = new widget.TextBox();
        jLabel18 = new widget.Label();
        Tjabatan = new widget.TextBox();
        jLabel20 = new widget.Label();
        Tinstansi = new widget.TextBox();
        jLabel22 = new widget.Label();
        Tno_surat_dari = new widget.TextBox();
        jLabel23 = new widget.Label();
        jLabel14 = new widget.Label();
        Scroll7 = new widget.ScrollPane();
        Tperihal = new widget.TextArea();
        jLabel24 = new widget.Label();
        Scroll8 = new widget.ScrollPane();
        Tkeperluan = new widget.TextArea();
        jLabel25 = new widget.Label();
        Ttgl_surat = new widget.Tanggal();
        TnoDokumen = new widget.TextBox();
        BtnDokumen = new widget.Button();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnSuratRohani.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSuratRohani.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSuratRohani.setText("Cetak Surat Keterangan Rohani");
        MnSuratRohani.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSuratRohani.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSuratRohani.setName("MnSuratRohani"); // NOI18N
        MnSuratRohani.setPreferredSize(new java.awt.Dimension(200, 26));
        MnSuratRohani.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSuratRohaniActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnSuratRohani);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Surat Keterangan Pemeriksaan Kesehatan Rohani ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
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
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "06-08-2023" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "06-08-2023" }));
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
        panelGlass7.setPreferredSize(new java.awt.Dimension(44, 300));
        panelGlass7.setLayout(null);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Pasien : ");
        jLabel3.setName("jLabel3"); // NOI18N
        panelGlass7.add(jLabel3);
        jLabel3.setBounds(0, 10, 105, 23);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("No. Surat Rohani : ");
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
        TNoSurat.setBounds(250, 38, 150, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        panelGlass7.add(TNoRM);
        TNoRM.setBounds(240, 10, 70, 23);

        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setName("TPasien"); // NOI18N
        TPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPasienKeyPressed(evt);
            }
        });
        panelGlass7.add(TPasien);
        TPasien.setBounds(315, 10, 360, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Tgl. Lahir : ");
        jLabel5.setName("jLabel5"); // NOI18N
        panelGlass7.add(jLabel5);
        jLabel5.setBounds(437, 66, 76, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Tempat Lahir : ");
        jLabel8.setName("jLabel8"); // NOI18N
        panelGlass7.add(jLabel8);
        jLabel8.setBounds(403, 38, 80, 23);

        TTempLahr.setForeground(new java.awt.Color(0, 0, 0));
        TTempLahr.setName("TTempLahr"); // NOI18N
        TTempLahr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTempLahrKeyPressed(evt);
            }
        });
        panelGlass7.add(TTempLahr);
        TTempLahr.setBounds(484, 38, 190, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Pendidikan : ");
        jLabel9.setName("jLabel9"); // NOI18N
        panelGlass7.add(jLabel9);
        jLabel9.setBounds(0, 66, 105, 23);

        TPendidikan.setForeground(new java.awt.Color(0, 0, 0));
        TPendidikan.setName("TPendidikan"); // NOI18N
        TPendidikan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPendidikanKeyPressed(evt);
            }
        });
        panelGlass7.add(TPendidikan);
        TPendidikan.setBounds(105, 66, 330, 23);

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
        TPekerjaan.setBounds(105, 94, 330, 23);

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
        TAlamat.setBounds(105, 122, 330, 23);

        Ttgl_psikiatrik.setEditable(false);
        Ttgl_psikiatrik.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "06-08-2023" }));
        Ttgl_psikiatrik.setDisplayFormat("dd-MM-yyyy");
        Ttgl_psikiatrik.setName("Ttgl_psikiatrik"); // NOI18N
        Ttgl_psikiatrik.setOpaque(false);
        panelGlass7.add(Ttgl_psikiatrik);
        Ttgl_psikiatrik.setBounds(585, 150, 90, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Dokter Yg. Memeriksa : ");
        jLabel15.setName("jLabel15"); // NOI18N
        panelGlass7.add(jLabel15);
        jLabel15.setBounds(680, 160, 130, 23);

        Tnmdokter.setEditable(false);
        Tnmdokter.setForeground(new java.awt.Color(0, 0, 0));
        Tnmdokter.setName("Tnmdokter"); // NOI18N
        panelGlass7.add(Tnmdokter);
        Tnmdokter.setBounds(812, 160, 460, 23);

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
        BtnDokter.setBounds(1273, 160, 28, 23);

        TtglLahir.setEditable(false);
        TtglLahir.setForeground(new java.awt.Color(0, 0, 0));
        TtglLahir.setName("TtglLahir"); // NOI18N
        panelGlass7.add(TtglLahir);
        TtglLahir.setBounds(514, 66, 160, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Atas Permintaan Tertulis Dari : ");
        jLabel16.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel16.setName("jLabel16"); // NOI18N
        panelGlass7.add(jLabel16);
        jLabel16.setBounds(0, 150, 170, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Nama : ");
        jLabel17.setName("jLabel17"); // NOI18N
        panelGlass7.add(jLabel17);
        jLabel17.setBounds(0, 178, 105, 23);

        Tnm_tertulis.setForeground(new java.awt.Color(0, 0, 0));
        Tnm_tertulis.setName("Tnm_tertulis"); // NOI18N
        Tnm_tertulis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tnm_tertulisKeyPressed(evt);
            }
        });
        panelGlass7.add(Tnm_tertulis);
        Tnm_tertulis.setBounds(105, 178, 570, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Stts. Nikah : ");
        jLabel12.setName("jLabel12"); // NOI18N
        panelGlass7.add(jLabel12);
        jLabel12.setBounds(437, 94, 76, 23);

        Tstts_nikah.setEditable(false);
        Tstts_nikah.setForeground(new java.awt.Color(0, 0, 0));
        Tstts_nikah.setName("Tstts_nikah"); // NOI18N
        panelGlass7.add(Tstts_nikah);
        Tstts_nikah.setBounds(514, 94, 160, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Agama : ");
        jLabel13.setName("jLabel13"); // NOI18N
        panelGlass7.add(jLabel13);
        jLabel13.setBounds(437, 122, 76, 23);

        Tagama.setEditable(false);
        Tagama.setForeground(new java.awt.Color(0, 0, 0));
        Tagama.setName("Tagama"); // NOI18N
        panelGlass7.add(Tagama);
        Tagama.setBounds(514, 122, 160, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Jabatan : ");
        jLabel18.setName("jLabel18"); // NOI18N
        panelGlass7.add(jLabel18);
        jLabel18.setBounds(0, 206, 105, 23);

        Tjabatan.setForeground(new java.awt.Color(0, 0, 0));
        Tjabatan.setName("Tjabatan"); // NOI18N
        Tjabatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TjabatanKeyPressed(evt);
            }
        });
        panelGlass7.add(Tjabatan);
        Tjabatan.setBounds(105, 206, 570, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Instansi : ");
        jLabel20.setName("jLabel20"); // NOI18N
        panelGlass7.add(jLabel20);
        jLabel20.setBounds(0, 234, 105, 23);

        Tinstansi.setForeground(new java.awt.Color(0, 0, 0));
        Tinstansi.setName("Tinstansi"); // NOI18N
        Tinstansi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TinstansiKeyPressed(evt);
            }
        });
        panelGlass7.add(Tinstansi);
        Tinstansi.setBounds(105, 234, 570, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("No. Surat : ");
        jLabel22.setName("jLabel22"); // NOI18N
        panelGlass7.add(jLabel22);
        jLabel22.setBounds(0, 262, 105, 23);

        Tno_surat_dari.setForeground(new java.awt.Color(0, 0, 0));
        Tno_surat_dari.setName("Tno_surat_dari"); // NOI18N
        Tno_surat_dari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tno_surat_dariKeyPressed(evt);
            }
        });
        panelGlass7.add(Tno_surat_dari);
        Tno_surat_dari.setBounds(105, 262, 570, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Perihal Permintaan : ");
        jLabel23.setName("jLabel23"); // NOI18N
        panelGlass7.add(jLabel23);
        jLabel23.setBounds(680, 10, 120, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Tgl. Pemeriksaan Psikiatrik : ");
        jLabel14.setName("jLabel14"); // NOI18N
        panelGlass7.add(jLabel14);
        jLabel14.setBounds(425, 150, 160, 23);

        Scroll7.setName("Scroll7"); // NOI18N
        Scroll7.setOpaque(true);

        Tperihal.setColumns(20);
        Tperihal.setRows(5);
        Tperihal.setName("Tperihal"); // NOI18N
        Tperihal.setPreferredSize(new java.awt.Dimension(170, 200));
        Tperihal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TperihalKeyPressed(evt);
            }
        });
        Scroll7.setViewportView(Tperihal);

        panelGlass7.add(Scroll7);
        Scroll7.setBounds(804, 10, 500, 70);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Keperluan : ");
        jLabel24.setName("jLabel24"); // NOI18N
        panelGlass7.add(jLabel24);
        jLabel24.setBounds(680, 85, 120, 23);

        Scroll8.setName("Scroll8"); // NOI18N
        Scroll8.setOpaque(true);

        Tkeperluan.setColumns(20);
        Tkeperluan.setRows(5);
        Tkeperluan.setName("Tkeperluan"); // NOI18N
        Tkeperluan.setPreferredSize(new java.awt.Dimension(170, 200));
        Tkeperluan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkeperluanKeyPressed(evt);
            }
        });
        Scroll8.setViewportView(Tkeperluan);

        panelGlass7.add(Scroll8);
        Scroll8.setBounds(804, 85, 500, 70);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Tgl. Surat : ");
        jLabel25.setName("jLabel25"); // NOI18N
        panelGlass7.add(jLabel25);
        jLabel25.setBounds(680, 188, 120, 23);

        Ttgl_surat.setEditable(false);
        Ttgl_surat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "06-08-2023" }));
        Ttgl_surat.setDisplayFormat("dd-MM-yyyy");
        Ttgl_surat.setName("Ttgl_surat"); // NOI18N
        Ttgl_surat.setOpaque(false);
        panelGlass7.add(Ttgl_surat);
        Ttgl_surat.setBounds(804, 188, 90, 23);

        TnoDokumen.setEditable(false);
        TnoDokumen.setForeground(new java.awt.Color(0, 0, 0));
        TnoDokumen.setName("TnoDokumen"); // NOI18N
        panelGlass7.add(TnoDokumen);
        TnoDokumen.setBounds(105, 38, 112, 23);

        BtnDokumen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokumen.setMnemonic('2');
        BtnDokumen.setToolTipText("Alt+2");
        BtnDokumen.setName("BtnDokumen"); // NOI18N
        BtnDokumen.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokumen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokumenActionPerformed(evt);
            }
        });
        panelGlass7.add(BtnDokumen);
        BtnDokumen.setBounds(220, 38, 28, 23);

        internalFrame1.add(panelGlass7, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRW.getText().trim().equals("")) {
            Valid.textKosong(TNoRW, "Pasien");
        } else if (kddokter.equals("") || kddokter.equals("-") || kddokter.equals("--")) {
            Valid.textKosong(Tnmdokter, "Dokter Yg. Memeriksa");
            BtnDokter.requestFocus();
        } else if (TnoDokumen.getText().equals("")) {
            Valid.textKosong(TnoDokumen, "Nomor Dokumen");
            BtnDokumen.requestFocus();
        } else {
            autoNomorSurat();
            Sequel.menyimpan("surat_keterangan_rohani", "'" + TNoRW.getText() + "','" + TNoSurat.getText() + "',"
                    + "'" + Valid.SetTgl(Ttgl_surat.getSelectedItem() + "") + "','" + kddokter + "',"
                    + "'" + Tnm_tertulis.getText() + "','" + Tjabatan.getText() + "','" + Tinstansi.getText() + "',"
                    + "'" + Tno_surat_dari.getText() + "','" + Tperihal.getText() + "',"
                    + "'" + Valid.SetTgl(Ttgl_psikiatrik.getSelectedItem() + "") + "','" + TPendidikan.getText() + "',"
                    + "'" + TPekerjaan.getText() + "','" + TAlamat.getText() + "','" + Tkeperluan.getText() + "',"
                    + "'" + TTempLahr.getText() + "','" + TPasien.getText() + "','" + TnoDokumen.getText() + "'", "Surat Keterangan Rohani");
            
            TCari.setText(TNoRW.getText());
            tbSurat.requestFocus();
            emptTeks();
            tampil();            
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
        } else if (kddokter.equals("") || kddokter.equals("-") || kddokter.equals("--")) {
            Valid.textKosong(Tnmdokter, "Dokter Yg. Memeriksa");
            BtnDokter.requestFocus();
        } else if (Sequel.cariInteger("select count(-1) from surat_keterangan_rohani where no_rawat='" + TNoRW.getText() + "'") == 0) {
            JOptionPane.showMessageDialog(null, "Data surat keterangan kesehatan rohani pasien yg. bernama " + TPasien.getText() + "    \n"
                    + "dg. no. rawat " + TNoRW.getText() + " belum tersimpan...!!!!");
        } else if (TnoDokumen.getText().equals("")) {
            Valid.textKosong(TnoDokumen, "Nomor Dokumen");
            BtnDokumen.requestFocus();
        } else {
            if (tbSurat.getSelectedRow() > -1) {
                Sequel.mengedit("surat_keterangan_rohani", "no_rawat=?", "tgl_surat=?,kd_dokter=?,nama_tertulis=?,jabatan=?,instansi=?,"
                        + "no_surat_dari=?,perihal_permintaan=?,tgl_pemeriksaan=?,pendidikan=?,pekerjaan=?,alamat=?,keperluan=?,"
                        + "tmpt_lahir=?,nm_pasien=?, no_dokumen=?", 16, new String[]{
                            Valid.SetTgl(Ttgl_surat.getSelectedItem() + ""), kddokter, Tnm_tertulis.getText(), Tjabatan.getText(),
                            Tinstansi.getText(), Tno_surat_dari.getText(), Tperihal.getText(), Valid.SetTgl(Ttgl_psikiatrik.getSelectedItem() + ""),
                            TPendidikan.getText(), TPekerjaan.getText(), TAlamat.getText(), Tkeperluan.getText(), TTempLahr.getText(),
                            TPasien.getText(), TnoDokumen.getText(),
                            tbSurat.getValueAt(tbSurat.getSelectedRow(), 0).toString()
                        });
                if (tabMode.getRowCount() != 0) {
                    TCari.setText(TNoRW.getText());
                    tbSurat.requestFocus();
                    emptTeks();
                    tampil();
                }
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
        akses.setform("DlgSuratKeteranganRohani");
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
                if (Sequel.queryu2tf("delete from surat_keterangan_rohani where no_rawat=?", 1, new String[]{
                    tbSurat.getValueAt(tbSurat.getSelectedRow(), 0).toString()
                }) == true) {
                    TCari.setText("");
                    emptTeks();
                    tampil();                                  
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

    private void TTempLahrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTempLahrKeyPressed
        Valid.pindah(evt, TPasien, TPendidikan);
    }//GEN-LAST:event_TTempLahrKeyPressed

    private void TPekerjaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPekerjaanKeyPressed
        Valid.pindah(evt, TPendidikan, TAlamat);
    }//GEN-LAST:event_TPekerjaanKeyPressed

    private void TAlamatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TAlamatKeyPressed
        Valid.pindah(evt, TPekerjaan, Ttgl_psikiatrik);
    }//GEN-LAST:event_TAlamatKeyPressed

    private void MnSuratRohaniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSuratRohaniActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu datanya pada tabel...!!!!");
            tbSurat.requestFocus();
        } else if (Sequel.cariInteger("select count(-1) from surat_keterangan_rohani where no_rawat='" + TNoRW.getText() + "'") == 0) {
            JOptionPane.showMessageDialog(null, "Data surat keterangan kesehatan rohani pasien yg. bernama " + TPasien.getText() + "    \n"
                    + "dg. no. rawat " + TNoRW.getText() + " belum tersimpan...!!!!");
        } else {
            if (tbSurat.getSelectedRow() > -1) {
                Map<String, Object> param = new HashMap<>();
                param.put("namars", akses.getnamars());
                param.put("alamatrs", akses.getalamatrs());
                param.put("kotars", akses.getkabupatenrs());
                param.put("propinsirs", akses.getpropinsirs());
                param.put("kontakrs", akses.getkontakrs());
                param.put("emailrs", akses.getemailrs());
                param.put("logo", Sequel.cariGambar("select logo_kabupaten from setting"));
                param.put("nosurat", TnoDokumen.getText() + " / " + TNoSurat.getText());
                param.put("nmdokter", Tnmdokter.getText());
                param.put("sipdokter", Sequel.cariIsi("select no_ijn_praktek from dokter where kd_dokter='" + kddokter + "'"));
                param.put("nmtertulis", Tnm_tertulis.getText());
                param.put("jabatan", Tjabatan.getText());
                param.put("instansi", Tinstansi.getText());
                param.put("nosurattertulis", Tno_surat_dari.getText());
                param.put("perihal", Tperihal.getText());
                param.put("tglpsikiatrik", Ttgl_psikiatrik.getSelectedItem().toString().substring(0, 2) + ", Bulan "
                        + Sequel.bulanINDONESIA("select date_format(tgl_pemeriksaan,'%m') from surat_keterangan_rohani where no_rawat='" + TNoRW.getText() + "'") + ", Tahun "
                        + Ttgl_psikiatrik.getSelectedItem().toString().substring(6, 10) + ", terhadap :");
                param.put("nmpasien", TPasien.getText());
                param.put("ttl", TTempLahr.getText() + ", " + TtglLahir.getText());
                param.put("pendidikan", TPendidikan.getText());
                param.put("pekerjaan", TPekerjaan.getText());
                param.put("statusnikah", Tstts_nikah.getText());
                param.put("agama", Tagama.getText());
                param.put("alamat", TAlamat.getText());
                param.put("keperluan", Tkeperluan.getText());
                param.put("tglsurat", Ttgl_surat.getSelectedItem().toString().substring(0, 2) + " "
                        + Sequel.bulanINDONESIA("select date_format(tgl_surat,'%m') from surat_keterangan_rohani where "
                                + "no_rawat='" + TNoRW.getText() + "'") + " " + Ttgl_surat.getSelectedItem().toString().substring(6, 10));
                param.put("nipdokter", kddokter);
                
                Valid.MyReport("rptSuratRohani.jasper", "report", "::[ Surat Keterangan Kesehatan Rohani ]::","SELECT date(now())", param);
            }
            
            TCari.setText(TNoRW.getText());
            tbSurat.requestFocus();
            emptTeks();
            tampil();            
        }
    }//GEN-LAST:event_MnSuratRohaniActionPerformed

    private void TjabatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TjabatanKeyPressed
        Valid.pindah(evt, Tnm_tertulis, Tinstansi);
    }//GEN-LAST:event_TjabatanKeyPressed

    private void TinstansiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TinstansiKeyPressed
        Valid.pindah(evt, Tjabatan, Tno_surat_dari);
    }//GEN-LAST:event_TinstansiKeyPressed

    private void Tno_surat_dariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tno_surat_dariKeyPressed
        Valid.pindah(evt, Tinstansi, Tperihal);
    }//GEN-LAST:event_Tno_surat_dariKeyPressed

    private void TperihalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TperihalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Tkeperluan.requestFocus();
        }
    }//GEN-LAST:event_TperihalKeyPressed

    private void TkeperluanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkeperluanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            BtnDokter.requestFocus();
        }
    }//GEN-LAST:event_TkeperluanKeyPressed

    private void TPendidikanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPendidikanKeyPressed
        Valid.pindah(evt, TTempLahr, TPekerjaan);
    }//GEN-LAST:event_TPendidikanKeyPressed

    private void Tnm_tertulisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tnm_tertulisKeyPressed
        Valid.pindah(evt, Ttgl_psikiatrik, Tjabatan);
    }//GEN-LAST:event_Tnm_tertulisKeyPressed

    private void TPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPasienKeyPressed
        Valid.pindah(evt, TPasien, TTempLahr);
    }//GEN-LAST:event_TPasienKeyPressed

    private void BtnDokumenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokumenActionPerformed
        akses.setform("DlgSuratKeteranganRohani");
        dokumen.isCek();
        dokumen.ChkInput.setSelected(false);
        dokumen.isForm();
        dokumen.setData("aktif");
        dokumen.setSize(650, internalFrame1.getHeight() - 40);
        dokumen.setLocationRelativeTo(internalFrame1);
        dokumen.setAlwaysOnTop(false);
        dokumen.setVisible(true);
    }//GEN-LAST:event_BtnDokumenActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgSuratKeteranganRohani dialog = new DlgSuratKeteranganRohani(new javax.swing.JFrame(), true);
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
    private widget.Button BtnDokumen;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnSimpan;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnSuratRohani;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll7;
    private widget.ScrollPane Scroll8;
    private widget.TextBox TAlamat;
    public widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRW;
    private widget.TextBox TNoSurat;
    private widget.TextBox TPasien;
    private widget.TextBox TPekerjaan;
    private widget.TextBox TPendidikan;
    private widget.TextBox TTempLahr;
    private widget.TextBox Tagama;
    private widget.TextBox Tinstansi;
    private widget.TextBox Tjabatan;
    private widget.TextArea Tkeperluan;
    private widget.TextBox Tnm_tertulis;
    private widget.TextBox Tnmdokter;
    private widget.TextBox TnoDokumen;
    private widget.TextBox Tno_surat_dari;
    private widget.TextArea Tperihal;
    private widget.TextBox Tstts_nikah;
    private widget.TextBox TtglLahir;
    private widget.Tanggal Ttgl_psikiatrik;
    private widget.Tanggal Ttgl_surat;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel20;
    private widget.Label jLabel21;
    private widget.Label jLabel22;
    private widget.Label jLabel23;
    private widget.Label jLabel24;
    private widget.Label jLabel25;
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
            ps = koneksi.prepareStatement("SELECT rp.no_rawat, sk.no_surat, p.no_rkm_medis, sk.nm_pasien, sk.tmpt_lahir, "
                    + "DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y') tgllahir, p.stts_nikah, p.agama, sk.pendidikan, sk.pekerjaan, sk.alamat, "
                    + "DATE_FORMAT(sk.tgl_pemeriksaan,'%d-%m-%Y') tglpsikiatrik, sk.nama_tertulis, sk.jabatan, sk.instansi, sk.no_surat_dari, "
                    + "sk.perihal_permintaan, sk.keperluan, pg.nama dokter, DATE_FORMAT(sk.tgl_surat,'%d-%m-%Y') tglsurat, "
                    + "sk.no_surat, sk.tgl_surat, sk.kd_dokter, sk.tgl_pemeriksaan, sk.no_dokumen FROM reg_periksa rp "
                    + "INNER JOIN surat_keterangan_rohani sk ON sk.no_rawat = rp.no_rawat "
                    + "INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                    + "INNER JOIN pegawai pg ON pg.nik = sk.kd_dokter WHERE "
                    + "sk.tgl_surat BETWEEN ? AND ? AND rp.no_rawat LIKE ? OR "
                    + "sk.tgl_surat BETWEEN ? AND ? AND sk.no_surat LIKE ? OR "
                    + "sk.tgl_surat BETWEEN ? AND ? AND p.no_rkm_medis LIKE ? OR "
                    + "sk.tgl_surat BETWEEN ? AND ? AND sk.nm_pasien LIKE ? OR "
                    + "sk.tgl_surat BETWEEN ? AND ? AND sk.tmpt_lahir LIKE ? OR "
                    + "sk.tgl_surat BETWEEN ? AND ? AND sk.no_surat_dari LIKE ? OR "
                    + "sk.tgl_surat BETWEEN ? AND ? AND sk.pekerjaan LIKE ? OR "
                    + "sk.tgl_surat BETWEEN ? AND ? AND pg.nama LIKE ? OR "
                    + "sk.tgl_surat BETWEEN ? AND ? AND sk.alamat LIKE ? OR "
                    + "sk.tgl_surat BETWEEN ? AND ? AND CONCAT('KP.12.09 / ',sk.no_surat) LIKE ? "
                    + "ORDER BY sk.tgl_surat DESC, sk.no_surat DESC");
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
                        rs.getString("no_surat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),                        
                        rs.getString("tmpt_lahir"),
                        rs.getString("tgllahir"),
                        rs.getString("stts_nikah"),
                        rs.getString("agama"),
                        rs.getString("pendidikan"),
                        rs.getString("pekerjaan"),
                        rs.getString("alamat"),
                        rs.getString("tglpsikiatrik"),
                        rs.getString("nama_tertulis"),
                        rs.getString("jabatan"),
                        rs.getString("instansi"),
                        rs.getString("no_surat_dari"),
                        rs.getString("perihal_permintaan"),
                        rs.getString("keperluan"),
                        rs.getString("dokter"),
                        rs.getString("tglsurat"),
                        rs.getString("no_surat"),
                        rs.getString("tgl_surat"),
                        rs.getString("kd_dokter"),
                        rs.getString("tgl_pemeriksaan"),
                        rs.getString("no_dokumen")
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
        TnoDokumen.setText("");
        TTempLahr.setText("");
        TtglLahir.setText("");
        TPendidikan.setText("");
        TPekerjaan.setText("");
        TAlamat.setText("");
        Tstts_nikah.setText("");
        Tagama.setText("");
        Ttgl_psikiatrik.setDate(new Date());
        Tnm_tertulis.setText("");
        Tjabatan.setText("");
        Tinstansi.setText("");
        Tno_surat_dari.setText("");
        Tperihal.setText("");
        Tkeperluan.setText("MELENGKAPI PERSYARATAN ");        
        kddokter = "";
        Tnmdokter.setText(""); 
        DTPCari1.setDate(Ttgl_surat.getDate());
        DTPCari2.setDate(new Date());
        Ttgl_surat.setDate(new Date());
        autoNomorSurat();
    }

    private void getData() {
        nosrt = "";
        kddokter = "";
        if (tbSurat.getSelectedRow() != -1) {
            TNoRW.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 0).toString());
            TNoSurat.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 1).toString());
            TNoRM.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 2).toString());
            TPasien.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 3).toString());
            TTempLahr.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 4).toString());
            Tstts_nikah.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 6).toString());
            Tagama.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 7).toString());            
            TPendidikan.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 8).toString());                        
            TPekerjaan.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 9).toString());
            TAlamat.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 10).toString());
            Valid.SetTgl(Ttgl_psikiatrik, tbSurat.getValueAt(tbSurat.getSelectedRow(), 23).toString());
            Tnm_tertulis.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 12).toString());
            Tjabatan.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 13).toString());
            Tinstansi.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 14).toString());
            Tno_surat_dari.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 15).toString());
            Tperihal.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 16).toString());
            Tkeperluan.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 17).toString());
            Tnmdokter.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 18).toString());
            nosrt = tbSurat.getValueAt(tbSurat.getSelectedRow(), 20).toString();
            Valid.SetTgl(Ttgl_surat, tbSurat.getValueAt(tbSurat.getSelectedRow(), 21).toString());
            kddokter = tbSurat.getValueAt(tbSurat.getSelectedRow(), 22).toString();
            TnoDokumen.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 24).toString());
            
            TtglLahir.setText(Sequel.cariIsi("select date_format(tgl_lahir,'%d') from pasien where no_rkm_medis='" + TNoRM.getText() + "'") + " "
                    + Sequel.bulanINDONESIA("select month(tgl_lahir) from pasien where no_rkm_medis='" + TNoRM.getText() + "'") + " "
                    + Sequel.cariIsi("select year(tgl_lahir) from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));            
        }
    }
    
    public void isCek(){
       BtnSimpan.setEnabled(akses.getsurat_keterangan_kir_mcu());       
       BtnEdit.setEnabled(akses.getsurat_keterangan_kir_mcu());
       BtnHapus.setEnabled(akses.getsurat_keterangan_kir_mcu());
    }
    
    public void setData(String norw) {
        autoNomorSurat();
        TPasien.requestFocus();
        
        try {
            ps1 = koneksi.prepareStatement("SELECT rp.no_rawat, p.no_rkm_medis, p.nm_pasien, p.tmp_lahir, p.pekerjaan, DATE_FORMAT(p.tgl_lahir,'%d') hari, "
                    + "YEAR(p.tgl_lahir) thn_lahir, p.pnd, concat(p.alamat,', Kel./Desa ',kl.nm_kel,', Kec. ',kc.nm_kec,', Kab./Kodya ',kb.nm_kab) alamat, "
                    + "p.stts_nikah, p.agama, d.kd_dokter, d.nm_dokter, rp.tgl_registrasi FROM reg_periksa rp INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                    + "INNER JOIN kelurahan kl ON kl.kd_kel = p.kd_kel_domisili_pasien INNER JOIN kecamatan kc ON kc.kd_kec = p.kd_kec_domisili_pasien "
                    + "INNER JOIN kabupaten kb ON kb.kd_kab = p.kd_kab_domisili_pasien INNER JOIN dokter d ON d.kd_dokter=rp.kd_dokter "
                    + "WHERE rp.no_rawat = '" + norw + "'");
            try {
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    TNoRW.setText(rs1.getString("no_rawat"));
                    TNoRM.setText(rs1.getString("no_rkm_medis"));
                    TPasien.setText(rs1.getString("nm_pasien"));
                    TTempLahr.setText(rs1.getString("tmp_lahir"));
                    TPendidikan.setText(rs1.getString("pnd"));
                    TPekerjaan.setText(rs1.getString("pekerjaan"));
                    TAlamat.setText(rs1.getString("alamat"));
                    Tstts_nikah.setText(rs1.getString("stts_nikah"));
                    Tagama.setText(rs1.getString("agama"));    
                    Ttgl_psikiatrik.setDate(rs1.getDate("tgl_registrasi"));                    
                    kddokter = rs1.getString("kd_dokter");
                    Tnmdokter.setText(rs1.getString("nm_dokter"));
                    Ttgl_surat.setDate(new Date());                    
                    DTPCari1.setDate(rs1.getDate("tgl_registrasi"));
                    DTPCari2.setDate(new Date());                    
                    TtglLahir.setText(rs1.getString("hari") + " " + Sequel.bulanINDONESIA("select month(tgl_lahir) from pasien where "
                            + "no_rkm_medis='" + rs1.getString("no_rkm_medis") + "'") + " " + rs1.getString("thn_lahir"));
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
        thn = "";        
        thn = Sequel.cariIsi("select date_format(tgl_surat,'%Y') from surat_keterangan_rohani order by tgl_surat desc limit 1");
        Valid.autoNomer6("select ifnull(MAX(CONVERT(LEFT(no_surat,4),signed)),0) from surat_keterangan_rohani where "
                + "year(tgl_surat) ='" + thn + "'", " / JIWA / RAZA", 4, TNoSurat);
    }
}
