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
import laporan.DlgHasilPenunjangMedis;
import simrskhanza.DlgCariDokter;
import simrskhanza.DlgMasterNomorDokumen;

/**
 *
 * @author dosen
 */
public class DlgSuratKeteranganDokter extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps1;
    private ResultSet rs, rs1;
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    private DlgMasterNomorDokumen dokumen = new DlgMasterNomorDokumen(null, false);
    private String thn = "", nosrt = "", kddokter = "", cekTgl = "", kodepoli = "",
            sttsnomor = "", cekCaten = "", noSuratFix = "";
    private int x = 0;

    /** Creates new form DlgSpesialis
     * @param parent
     * @param modal */
    public DlgSuratKeteranganDokter(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        String[] row = {"No. Rawat", "No. Surat", "No. RM", "Nama Pasien", "Tempat Lahir", "Tgl. Lahir",
            "Jns. Kelamin", "Pekerjaan", "Alamat", "Permintaan Dari", "No. Surat Dari", "Tanggalnya",
            "Tgl. No. Surat", "Pemeriksaan Dinyatakan", "Keperluan", "Berlaku", "Satuan", "BB", "TB",
            "Gol. Darah", "Dokter Pemeriksa", "Tgl. Surat", "nosrt", "tgl_surat", "tgl_surat_dari",
            "nip_dokter", "kdpoli", "hasil_pemeriksaan", "diagnosa", "no_dokumen", "cekcaten", "tensi", "nadi", "bmi"
        };
        tabMode=new DefaultTableModel(null,row){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };

        tbSurat.setModel(tabMode);
        tbSurat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbSurat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 34; i++) {
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
                column.setPreferredWidth(80);
            } else if (i == 7) {
                column.setPreferredWidth(150);
            } else if (i == 8) {
                column.setPreferredWidth(250);
            } else if (i == 9) {
                column.setPreferredWidth(150);
            } else if (i == 10) {
                column.setPreferredWidth(150);
            } else if (i == 11) {
                column.setPreferredWidth(70);
            } else if (i == 12) {
                column.setPreferredWidth(85);
            } else if (i == 13) {
                column.setPreferredWidth(250);
            } else if (i == 14) {
                column.setPreferredWidth(250);
            } else if (i == 15) {
                column.setPreferredWidth(60);
            } else if (i == 16) {
                column.setPreferredWidth(60);
            } else if (i == 17) {
                column.setPreferredWidth(50);
            } else if (i == 18) {
                column.setPreferredWidth(50);
            } else if (i == 19) {
                column.setPreferredWidth(75);
            } else if (i == 20) {
                column.setPreferredWidth(250);
            } else if (i == 21) {
                column.setPreferredWidth(75);
            } else if (i == 22) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 23) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 24) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 25) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 26) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 27) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 28) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 29) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 30) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 31) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 32) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 33) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }

        tbSurat.setDefaultRenderer(Object.class, new WarnaTable());
        
        TCari.setDocument(new batasInput((int) 100).getKata(TCari));        
        TTempLahr.setDocument(new batasInput((int) 150).getKata(TTempLahr));        
        TPekerjaan.setDocument(new batasInput((int) 100).getKata(TPekerjaan));
        Tpermintaan.setDocument(new batasInput((int) 180).getKata(Tpermintaan));
        Tno_surat_dari.setDocument(new batasInput((int) 150).getKata(Tno_surat_dari));
        Tberlaku.setDocument(new batasInput((byte) 3).getOnlyAngka(Tberlaku));
        Tbb.setDocument(new batasInput((byte) 3).getOnlyAngka(Tbb));
        Ttb.setDocument(new batasInput((byte) 3).getOnlyAngka(Ttb));
        Tgol.setDocument(new batasInput((int) 3).getKata(Tgol));
        TPasien.setDocument(new batasInput((int) 40).getKata(TPasien));
        Ttensi.setDocument(new batasInput((int) 7).getKata(Ttensi));
        Tnadi.setDocument(new batasInput((int) 7).getKata(Tnadi));
        Tbmi.setDocument(new batasInput((int) 7).getKata(Tbmi));
        
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgSuratKeteranganDokter")) {
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
                if (akses.getform().equals("DlgSuratKeteranganDokter")) {
                    if (dokumen.getTable().getSelectedRow() != -1) {
                        TnoDokumen.setText(dokumen.getTable().getValueAt(dokumen.getTable().getSelectedRow(), 2).toString());
                        sttsnomor = dokumen.getTable().getValueAt(dokumen.getTable().getSelectedRow(), 4).toString();
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
        MnSuratKetDokter = new javax.swing.JMenuItem();
        MnSuratKetDokterCaten = new javax.swing.JMenuItem();
        MnSuratKetDokterMCUhasildiagnosa = new javax.swing.JMenuItem();
        MnSuratKetDokterManual = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        MnHasilPemeriksaanPenunjang = new javax.swing.JMenuItem();
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
        jLabel10 = new widget.Label();
        TPekerjaan = new widget.TextBox();
        jLabel11 = new widget.Label();
        TAlamat = new widget.TextBox();
        Ttgl_no_surat = new widget.Tanggal();
        jLabel15 = new widget.Label();
        Tnmdokter = new widget.TextBox();
        BtnDokter = new widget.Button();
        TtglLahir = new widget.TextBox();
        jLabel17 = new widget.Label();
        Tpermintaan = new widget.TextBox();
        jLabel18 = new widget.Label();
        Tno_surat_dari = new widget.TextBox();
        jLabel24 = new widget.Label();
        Scroll8 = new widget.ScrollPane();
        Tkeperluan = new widget.TextArea();
        jLabel25 = new widget.Label();
        Ttgl_surat = new widget.Tanggal();
        jLabel22 = new widget.Label();
        cmbDinyatakan = new widget.ComboBox();
        jLabel26 = new widget.Label();
        Tberlaku = new widget.TextBox();
        cmbSelama = new widget.ComboBox();
        jLabel27 = new widget.Label();
        Tbb = new widget.TextBox();
        jLabel28 = new widget.Label();
        Ttb = new widget.TextBox();
        jLabel29 = new widget.Label();
        Tgol = new widget.TextBox();
        ChkTglSurat = new widget.CekBox();
        jLabel9 = new widget.Label();
        Tjk = new widget.TextBox();
        jLabel12 = new widget.Label();
        Scroll9 = new widget.ScrollPane();
        Thasil = new widget.TextArea();
        jLabel13 = new widget.Label();
        Scroll10 = new widget.ScrollPane();
        Tdiagnosa = new widget.TextArea();
        TnoDokumen = new widget.TextBox();
        BtnDokumen = new widget.Button();
        BtnPasteHasil = new widget.Button();
        ChkYbs = new widget.CekBox();
        ChkCaten = new widget.CekBox();
        jLabel30 = new widget.Label();
        Ttensi = new widget.TextBox();
        jLabel31 = new widget.Label();
        Tnadi = new widget.TextBox();
        jLabel32 = new widget.Label();
        Tbmi = new widget.TextBox();
        jLabel33 = new widget.Label();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnSuratKetDokter.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSuratKetDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSuratKetDokter.setText("Surat Keterangan Dokter");
        MnSuratKetDokter.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSuratKetDokter.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSuratKetDokter.setName("MnSuratKetDokter"); // NOI18N
        MnSuratKetDokter.setPreferredSize(new java.awt.Dimension(230, 26));
        MnSuratKetDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSuratKetDokterActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnSuratKetDokter);

        MnSuratKetDokterCaten.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSuratKetDokterCaten.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSuratKetDokterCaten.setText("Surat Keterangan Dokter (CATEN)");
        MnSuratKetDokterCaten.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSuratKetDokterCaten.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSuratKetDokterCaten.setName("MnSuratKetDokterCaten"); // NOI18N
        MnSuratKetDokterCaten.setPreferredSize(new java.awt.Dimension(230, 26));
        MnSuratKetDokterCaten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSuratKetDokterCatenActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnSuratKetDokterCaten);

        MnSuratKetDokterMCUhasildiagnosa.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSuratKetDokterMCUhasildiagnosa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSuratKetDokterMCUhasildiagnosa.setText("Suket Dokter (MCU - Hasil Pemeriksaan)");
        MnSuratKetDokterMCUhasildiagnosa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSuratKetDokterMCUhasildiagnosa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSuratKetDokterMCUhasildiagnosa.setName("MnSuratKetDokterMCUhasildiagnosa"); // NOI18N
        MnSuratKetDokterMCUhasildiagnosa.setPreferredSize(new java.awt.Dimension(230, 26));
        MnSuratKetDokterMCUhasildiagnosa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSuratKetDokterMCUhasildiagnosaActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnSuratKetDokterMCUhasildiagnosa);

        MnSuratKetDokterManual.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSuratKetDokterManual.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSuratKetDokterManual.setText("Surat Keterangan Dokter Manual");
        MnSuratKetDokterManual.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSuratKetDokterManual.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSuratKetDokterManual.setName("MnSuratKetDokterManual"); // NOI18N
        MnSuratKetDokterManual.setPreferredSize(new java.awt.Dimension(230, 26));
        MnSuratKetDokterManual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSuratKetDokterManualActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnSuratKetDokterManual);

        jPopupMenu2.setName("jPopupMenu2"); // NOI18N

        MnHasilPemeriksaanPenunjang.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHasilPemeriksaanPenunjang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnHasilPemeriksaanPenunjang.setText("Hasil Pemeriksaan Penunjang");
        MnHasilPemeriksaanPenunjang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHasilPemeriksaanPenunjang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHasilPemeriksaanPenunjang.setIconTextGap(5);
        MnHasilPemeriksaanPenunjang.setName("MnHasilPemeriksaanPenunjang"); // NOI18N
        MnHasilPemeriksaanPenunjang.setPreferredSize(new java.awt.Dimension(195, 26));
        MnHasilPemeriksaanPenunjang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHasilPemeriksaanPenunjangActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnHasilPemeriksaanPenunjang);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Surat Keterangan Dokter ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
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
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "27-08-2024" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "27-08-2024" }));
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

        panelGlass7.setToolTipText("Klik kanan pada area ini untuk melihat hasil pemeriksaan penunjang");
        panelGlass7.setComponentPopupMenu(jPopupMenu2);
        panelGlass7.setName("panelGlass7"); // NOI18N
        panelGlass7.setPreferredSize(new java.awt.Dimension(44, 363));
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
        jLabel8.setBounds(404, 38, 80, 23);

        TTempLahr.setForeground(new java.awt.Color(0, 0, 0));
        TTempLahr.setName("TTempLahr"); // NOI18N
        TTempLahr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTempLahrKeyPressed(evt);
            }
        });
        panelGlass7.add(TTempLahr);
        TTempLahr.setBounds(484, 38, 190, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Pekerjaan : ");
        jLabel10.setName("jLabel10"); // NOI18N
        panelGlass7.add(jLabel10);
        jLabel10.setBounds(0, 66, 105, 23);

        TPekerjaan.setForeground(new java.awt.Color(0, 0, 0));
        TPekerjaan.setName("TPekerjaan"); // NOI18N
        TPekerjaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPekerjaanKeyPressed(evt);
            }
        });
        panelGlass7.add(TPekerjaan);
        TPekerjaan.setBounds(105, 66, 330, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Alamat Domisili : ");
        jLabel11.setName("jLabel11"); // NOI18N
        panelGlass7.add(jLabel11);
        jLabel11.setBounds(0, 94, 105, 23);

        TAlamat.setForeground(new java.awt.Color(0, 0, 0));
        TAlamat.setName("TAlamat"); // NOI18N
        TAlamat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TAlamatKeyPressed(evt);
            }
        });
        panelGlass7.add(TAlamat);
        TAlamat.setBounds(105, 94, 380, 23);

        Ttgl_no_surat.setEditable(false);
        Ttgl_no_surat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "27-08-2024" }));
        Ttgl_no_surat.setDisplayFormat("dd-MM-yyyy");
        Ttgl_no_surat.setName("Ttgl_no_surat"); // NOI18N
        Ttgl_no_surat.setOpaque(false);
        panelGlass7.add(Ttgl_no_surat);
        Ttgl_no_surat.setBounds(585, 150, 90, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Dokter Pemeriksa : ");
        jLabel15.setName("jLabel15"); // NOI18N
        panelGlass7.add(jLabel15);
        jLabel15.setBounds(0, 327, 105, 23);

        Tnmdokter.setEditable(false);
        Tnmdokter.setForeground(new java.awt.Color(0, 0, 0));
        Tnmdokter.setName("Tnmdokter"); // NOI18N
        panelGlass7.add(Tnmdokter);
        Tnmdokter.setBounds(105, 327, 470, 23);

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
        BtnDokter.setBounds(580, 327, 28, 23);

        TtglLahir.setEditable(false);
        TtglLahir.setForeground(new java.awt.Color(0, 0, 0));
        TtglLahir.setName("TtglLahir"); // NOI18N
        panelGlass7.add(TtglLahir);
        TtglLahir.setBounds(514, 66, 160, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Permintaan Dari : ");
        jLabel17.setName("jLabel17"); // NOI18N
        panelGlass7.add(jLabel17);
        jLabel17.setBounds(0, 122, 105, 23);

        Tpermintaan.setForeground(new java.awt.Color(0, 0, 0));
        Tpermintaan.setName("Tpermintaan"); // NOI18N
        Tpermintaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpermintaanKeyPressed(evt);
            }
        });
        panelGlass7.add(Tpermintaan);
        Tpermintaan.setBounds(105, 122, 520, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("No. Surat Dari : ");
        jLabel18.setName("jLabel18"); // NOI18N
        panelGlass7.add(jLabel18);
        jLabel18.setBounds(0, 150, 105, 23);

        Tno_surat_dari.setForeground(new java.awt.Color(0, 0, 0));
        Tno_surat_dari.setName("Tno_surat_dari"); // NOI18N
        Tno_surat_dari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tno_surat_dariKeyPressed(evt);
            }
        });
        panelGlass7.add(Tno_surat_dari);
        Tno_surat_dari.setBounds(105, 150, 360, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Keperluan : ");
        jLabel24.setName("jLabel24"); // NOI18N
        panelGlass7.add(jLabel24);
        jLabel24.setBounds(0, 206, 105, 23);

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
        Scroll8.setBounds(105, 206, 570, 60);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Tgl. Surat : ");
        jLabel25.setName("jLabel25"); // NOI18N
        panelGlass7.add(jLabel25);
        jLabel25.setBounds(505, 270, 80, 23);

        Ttgl_surat.setEditable(false);
        Ttgl_surat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "27-08-2024" }));
        Ttgl_surat.setDisplayFormat("dd-MM-yyyy");
        Ttgl_surat.setName("Ttgl_surat"); // NOI18N
        Ttgl_surat.setOpaque(false);
        panelGlass7.add(Ttgl_surat);
        Ttgl_surat.setBounds(585, 270, 90, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Dinyatakan : ");
        jLabel22.setName("jLabel22"); // NOI18N
        panelGlass7.add(jLabel22);
        jLabel22.setBounds(0, 178, 105, 23);

        cmbDinyatakan.setForeground(new java.awt.Color(0, 0, 0));
        cmbDinyatakan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Baik kesehatannya", "Baik kesehatannya, Tidak bertindik, dan Tidak bertato", "Baik kesehatannya, dapat melangsungkan pernikahan", "Kurang baik kesehatannya, besar harapan dapat diperbaiki", "Tidak baik kesehatannya, dengan diagnosa", "Tidak baik kesehatannya untuk melangsungkan pernikahan", "Lainnya" }));
        cmbDinyatakan.setName("cmbDinyatakan"); // NOI18N
        cmbDinyatakan.setPreferredSize(new java.awt.Dimension(55, 28));
        panelGlass7.add(cmbDinyatakan);
        cmbDinyatakan.setBounds(105, 178, 320, 23);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Berlaku Selama : ");
        jLabel26.setName("jLabel26"); // NOI18N
        panelGlass7.add(jLabel26);
        jLabel26.setBounds(425, 178, 130, 23);
        jLabel26.getAccessibleContext().setAccessibleDescription("");

        Tberlaku.setForeground(new java.awt.Color(0, 0, 0));
        Tberlaku.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tberlaku.setName("Tberlaku"); // NOI18N
        Tberlaku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TberlakuKeyPressed(evt);
            }
        });
        panelGlass7.add(Tberlaku);
        Tberlaku.setBounds(557, 178, 46, 23);

        cmbSelama.setForeground(new java.awt.Color(0, 0, 0));
        cmbSelama.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "hari", "bulan", "tahun" }));
        cmbSelama.setName("cmbSelama"); // NOI18N
        cmbSelama.setPreferredSize(new java.awt.Dimension(55, 28));
        panelGlass7.add(cmbSelama);
        cmbSelama.setBounds(610, 178, 65, 23);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("Berat Badan : ");
        jLabel27.setName("jLabel27"); // NOI18N
        panelGlass7.add(jLabel27);
        jLabel27.setBounds(0, 271, 105, 23);

        Tbb.setForeground(new java.awt.Color(0, 0, 0));
        Tbb.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tbb.setName("Tbb"); // NOI18N
        Tbb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbbKeyPressed(evt);
            }
        });
        panelGlass7.add(Tbb);
        Tbb.setBounds(105, 271, 50, 23);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel28.setText("Kg.     Tinggi Badan : ");
        jLabel28.setName("jLabel28"); // NOI18N
        panelGlass7.add(jLabel28);
        jLabel28.setBounds(160, 271, 103, 23);

        Ttb.setForeground(new java.awt.Color(0, 0, 0));
        Ttb.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Ttb.setName("Ttb"); // NOI18N
        Ttb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtbKeyPressed(evt);
            }
        });
        panelGlass7.add(Ttb);
        Ttb.setBounds(265, 271, 50, 23);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel29.setText("Cm.      Golongan Darah :");
        jLabel29.setName("jLabel29"); // NOI18N
        panelGlass7.add(jLabel29);
        jLabel29.setBounds(320, 271, 125, 23);

        Tgol.setForeground(new java.awt.Color(0, 0, 0));
        Tgol.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tgol.setName("Tgol"); // NOI18N
        Tgol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgolKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TgolKeyTyped(evt);
            }
        });
        panelGlass7.add(Tgol);
        Tgol.setBounds(448, 271, 50, 23);

        ChkTglSurat.setBackground(new java.awt.Color(255, 255, 250));
        ChkTglSurat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkTglSurat.setForeground(new java.awt.Color(0, 0, 0));
        ChkTglSurat.setText("Tgl. Nomor Surat : ");
        ChkTglSurat.setBorderPainted(true);
        ChkTglSurat.setBorderPaintedFlat(true);
        ChkTglSurat.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ChkTglSurat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkTglSurat.setName("ChkTglSurat"); // NOI18N
        ChkTglSurat.setOpaque(false);
        ChkTglSurat.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkTglSurat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkTglSuratActionPerformed(evt);
            }
        });
        panelGlass7.add(ChkTglSurat);
        ChkTglSurat.setBounds(465, 150, 120, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Jenis Kelamin : ");
        jLabel9.setName("jLabel9"); // NOI18N
        panelGlass7.add(jLabel9);
        jLabel9.setBounds(482, 94, 90, 23);

        Tjk.setEditable(false);
        Tjk.setForeground(new java.awt.Color(0, 0, 0));
        Tjk.setName("Tjk"); // NOI18N
        panelGlass7.add(Tjk);
        Tjk.setBounds(574, 94, 100, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Hasil Pemeriksaan : ");
        jLabel12.setName("jLabel12"); // NOI18N
        panelGlass7.add(jLabel12);
        jLabel12.setBounds(680, 10, 120, 23);

        Scroll9.setName("Scroll9"); // NOI18N
        Scroll9.setOpaque(true);

        Thasil.setColumns(20);
        Thasil.setRows(5);
        Thasil.setToolTipText("Klik kanan pada area ini untuk melihat hasil pemeriksaan penunjang");
        Thasil.setComponentPopupMenu(jPopupMenu2);
        Thasil.setName("Thasil"); // NOI18N
        Thasil.setPreferredSize(new java.awt.Dimension(170, 800));
        Thasil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ThasilKeyPressed(evt);
            }
        });
        Scroll9.setViewportView(Thasil);

        panelGlass7.add(Scroll9);
        Scroll9.setBounds(800, 10, 500, 210);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Diagnosa : ");
        jLabel13.setName("jLabel13"); // NOI18N
        panelGlass7.add(jLabel13);
        jLabel13.setBounds(680, 225, 120, 23);

        Scroll10.setName("Scroll10"); // NOI18N
        Scroll10.setOpaque(true);

        Tdiagnosa.setColumns(20);
        Tdiagnosa.setRows(5);
        Tdiagnosa.setName("Tdiagnosa"); // NOI18N
        Tdiagnosa.setPreferredSize(new java.awt.Dimension(170, 800));
        Tdiagnosa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdiagnosaKeyPressed(evt);
            }
        });
        Scroll10.setViewportView(Tdiagnosa);

        panelGlass7.add(Scroll10);
        Scroll10.setBounds(800, 225, 500, 120);

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

        BtnPasteHasil.setForeground(new java.awt.Color(0, 0, 0));
        BtnPasteHasil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/paste.png"))); // NOI18N
        BtnPasteHasil.setMnemonic('L');
        BtnPasteHasil.setText("Paste");
        BtnPasteHasil.setToolTipText("Alt+L");
        BtnPasteHasil.setName("BtnPasteHasil"); // NOI18N
        BtnPasteHasil.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPasteHasil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPasteHasilActionPerformed(evt);
            }
        });
        panelGlass7.add(BtnPasteHasil);
        BtnPasteHasil.setBounds(1315, 10, 90, 23);

        ChkYbs.setBackground(new java.awt.Color(255, 255, 250));
        ChkYbs.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkYbs.setForeground(new java.awt.Color(0, 0, 0));
        ChkYbs.setText("Ybs.");
        ChkYbs.setBorderPainted(true);
        ChkYbs.setBorderPaintedFlat(true);
        ChkYbs.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkYbs.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkYbs.setName("ChkYbs"); // NOI18N
        ChkYbs.setOpaque(false);
        ChkYbs.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkYbs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkYbsActionPerformed(evt);
            }
        });
        panelGlass7.add(ChkYbs);
        ChkYbs.setBounds(630, 122, 50, 23);

        ChkCaten.setBackground(new java.awt.Color(255, 255, 250));
        ChkCaten.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkCaten.setForeground(new java.awt.Color(0, 0, 0));
        ChkCaten.setText("Caten");
        ChkCaten.setBorderPainted(true);
        ChkCaten.setBorderPaintedFlat(true);
        ChkCaten.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkCaten.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkCaten.setName("ChkCaten"); // NOI18N
        ChkCaten.setOpaque(false);
        ChkCaten.setPreferredSize(new java.awt.Dimension(175, 23));
        panelGlass7.add(ChkCaten);
        ChkCaten.setBounds(620, 327, 65, 23);

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Tensi : ");
        jLabel30.setName("jLabel30"); // NOI18N
        panelGlass7.add(jLabel30);
        jLabel30.setBounds(0, 299, 105, 23);

        Ttensi.setForeground(new java.awt.Color(0, 0, 0));
        Ttensi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Ttensi.setName("Ttensi"); // NOI18N
        Ttensi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtensiKeyPressed(evt);
            }
        });
        panelGlass7.add(Ttensi);
        Ttensi.setBounds(105, 299, 70, 23);

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel31.setText("mmHg    Nadi : ");
        jLabel31.setName("jLabel31"); // NOI18N
        panelGlass7.add(jLabel31);
        jLabel31.setBounds(180, 299, 73, 23);

        Tnadi.setForeground(new java.awt.Color(0, 0, 0));
        Tnadi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tnadi.setName("Tnadi"); // NOI18N
        Tnadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnadiKeyPressed(evt);
            }
        });
        panelGlass7.add(Tnadi);
        Tnadi.setBounds(254, 299, 50, 23);

        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel32.setText("x/mnt     BMI :");
        jLabel32.setName("jLabel32"); // NOI18N
        panelGlass7.add(jLabel32);
        jLabel32.setBounds(310, 299, 73, 23);

        Tbmi.setForeground(new java.awt.Color(0, 0, 0));
        Tbmi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tbmi.setName("Tbmi"); // NOI18N
        Tbmi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbmiKeyPressed(evt);
            }
        });
        panelGlass7.add(Tbmi);
        Tbmi.setBounds(384, 299, 50, 23);

        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel33.setText("Kg.");
        jLabel33.setName("jLabel33"); // NOI18N
        panelGlass7.add(jLabel33);
        jLabel33.setBounds(438, 299, 25, 23);

        internalFrame1.add(panelGlass7, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRW.getText().trim().equals("")) {
            Valid.textKosong(TNoRW, "Pasien");
        } else if (kddokter.equals("") || kddokter.equals("-") || kddokter.equals("--")) {
            Valid.textKosong(Tnmdokter, "Dokter Pemeriksa");
            BtnDokter.requestFocus();
        } else if (TnoDokumen.getText().equals("")) {
            Valid.textKosong(TnoDokumen, "Nomor Dokumen");
            BtnDokumen.requestFocus();
        } else if (sttsnomor.equals("Non Aktif")) {
            JOptionPane.showMessageDialog(null, "Nomor dokumen yang dipilih statusnya " + sttsnomor + ", silahkan ulangi lagi...!!");
            BtnDokumen.requestFocus();
        } else {
            if (ChkTglSurat.isSelected() == true) {
                cekTgl = "ya";
            } else {
                cekTgl = "tidak";
            }
            
            if (Tberlaku.getText().equals("")) {
                Tberlaku.setText("0");
            }
            
            if (ChkCaten.isSelected() == true) {
                cekCaten = "ya";
                noSuratFix = " / CATEN / RAZA";
            } else {
                cekCaten = "tidak";
                noSuratFix = " / RAZA";
            }
            
            autoNomorSurat();
            Sequel.menyimpan("surat_keterangan_dokter", "'" + TNoRW.getText() + "','" + TNoSurat.getText() + "',"
                    + "'" + Valid.SetTgl(Ttgl_surat.getSelectedItem() + "") + "','" + TPekerjaan.getText() + "',"
                    + "'" + TAlamat.getText() + "','" + Tpermintaan.getText() + "','" + Tno_surat_dari.getText() + "',"
                    + "'" + cekTgl + "','" + Valid.SetTgl(Ttgl_no_surat.getSelectedItem() + "") + "',"
                    + "'" + cmbDinyatakan.getSelectedItem().toString() + "','" + Tkeperluan.getText() + "',"
                    + "'" + Tberlaku.getText() + "','" + cmbSelama.getSelectedItem().toString() + "','" + Tbb.getText() + "',"
                    + "'" + Ttb.getText() + "','" + Tgol.getText() + "','" + kddokter + "','" + TTempLahr.getText() + "',"
                    + "'" + Thasil.getText() + "','" + Tdiagnosa.getText() + "','" + TPasien.getText() + "','" + TnoDokumen.getText() + "',"
                    + "'" + cekCaten + "','" + Ttensi.getText() + "','" + Tnadi.getText() + "','" + Tbmi.getText() + "'", "Surat Keterangan Dokter");

            TCari.setText(TNoRW.getText());
            tbSurat.requestFocus();
            emptTeks();
            tampil();            
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, TNoSurat, BtnBatal);
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
            Valid.textKosong(Tnmdokter, "Dokter Pemeriksa");
            BtnDokter.requestFocus();
        } else if (Sequel.cariInteger("select count(-1) from surat_keterangan_dokter where no_rawat='" + TNoRW.getText() + "'") == 0) {
            JOptionPane.showMessageDialog(null, "Data surat keterangan dokter utk. pasien yg. bernama " + TPasien.getText() + "    \n"
                    + "dengan No. Rawat " + TNoRW.getText() + " belum tersimpan...!!!!");
        } else if (TnoDokumen.getText().equals("")) {
            Valid.textKosong(TnoDokumen, "Nomor Dokumen");
            BtnDokumen.requestFocus();
        } else if (sttsnomor.equals("Non Aktif")) {
            JOptionPane.showMessageDialog(null, "Nomor dokumen yang dipilih statusnya " + sttsnomor + ", silahkan ulangi lagi...!!");
            BtnDokumen.requestFocus();
        } else {
            if (ChkTglSurat.isSelected() == true) {
                cekTgl = "ya";
            } else {
                cekTgl = "tidak";
            }
            
            if (Tberlaku.getText().equals("")) {
                Tberlaku.setText("0");
            }
            
            if (ChkCaten.isSelected() == true) {
                cekCaten = "ya";
                noSuratFix = tbSurat.getValueAt(tbSurat.getSelectedRow(), 1).toString().substring(0, 4) + " / CATEN / RAZA";
            } else {
                cekCaten = "tidak";
                noSuratFix = tbSurat.getValueAt(tbSurat.getSelectedRow(), 1).toString().substring(0, 4) + " / RAZA";
            }
            
            if (tbSurat.getSelectedRow() > -1) {
                Sequel.mengedit("surat_keterangan_dokter", "no_rawat=?", "no_surat=?, tgl_surat=?, pekerjaan=?, tempat_tinggal=?, permintaan_dari=?, "
                        + "no_surat_dari=?, ada_tgl_nosurat=?, tgl_surat_dari=?, pemeriksaan_dinyatakan=?, keperluan=?, lama_berlaku=?, "
                        + "satuan_lama=?, bb=?, tb=?, gol_darah=?, nip_dokter=?, tmpt_lahir=?, hasil_pemeriksaan=?, diagnosa=?, nm_pasien=?, "
                        + "no_dokumen=?, caten=?, td=?, nadi=?, bmi=?", 26, new String[]{
                            noSuratFix, Valid.SetTgl(Ttgl_surat.getSelectedItem() + ""), TPekerjaan.getText(), TAlamat.getText(), 
                            Tpermintaan.getText(), Tno_surat_dari.getText(), cekTgl, Valid.SetTgl(Ttgl_no_surat.getSelectedItem() + ""),
                            cmbDinyatakan.getSelectedItem().toString(), Tkeperluan.getText(), Tberlaku.getText(),
                            cmbSelama.getSelectedItem().toString(), Tbb.getText(), Ttb.getText(), Tgol.getText(),
                            kddokter, TTempLahr.getText(), Thasil.getText(), Tdiagnosa.getText(), TPasien.getText(), 
                            TnoDokumen.getText(), cekCaten, Ttensi.getText(), Tnadi.getText(), Tbmi.getText(),
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
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            tbSurat.requestFocus();
        }
}//GEN-LAST:event_TCariKeyPressed

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
        akses.setform("DlgSuratKeteranganDokter");
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
                if (Sequel.queryu2tf("delete from surat_keterangan_dokter where no_surat='" + tbSurat.getValueAt(tbSurat.getSelectedRow(), 1).toString() + "' and no_rawat=?", 1, new String[]{
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
        Valid.pindah(evt, TPasien, TPekerjaan);
    }//GEN-LAST:event_TTempLahrKeyPressed

    private void TPekerjaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPekerjaanKeyPressed
        Valid.pindah(evt, TTempLahr, TAlamat);
    }//GEN-LAST:event_TPekerjaanKeyPressed

    private void TAlamatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TAlamatKeyPressed
        Valid.pindah(evt, TPekerjaan, Tpermintaan);
    }//GEN-LAST:event_TAlamatKeyPressed

    private void MnSuratKetDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSuratKetDokterActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu datanya pada tabel...!!!!");
            tbSurat.requestFocus();
        } else if (Sequel.cariInteger("select count(-1) from surat_keterangan_dokter where no_rawat='" + TNoRW.getText() + "'") == 0) {
            JOptionPane.showMessageDialog(null, "Data surat keterangan dokter utk. pasien yg. bernama " + TPasien.getText() + "    \n"
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
                param.put("nmpasien", TPasien.getText());
                param.put("ttl", TTempLahr.getText() + ", " + TtglLahir.getText());
                param.put("jnskelamin", Tjk.getText());
                param.put("pekerjaan", TPekerjaan.getText());
                param.put("alamat", TAlamat.getText());
                param.put("permintaan", Tpermintaan.getText());                
                param.put("bb", Tbb.getText() + " Kg.");
                param.put("tb", Ttb.getText() + " Cm.");
                param.put("goldarah", "- " + Tgol.getText() + " -");
                param.put("berlaku", Tberlaku.getText() + " (" + Sequel.Terbilang(Valid.SetAngka(Tberlaku.getText())) + ") "
                        + cmbSelama.getSelectedItem().toString() + " sejak tanggal dikeluarkan.");                
                param.put("nmdokter", Tnmdokter.getText());
                param.put("nipdokter", kddokter);
                param.put("tglsurat", Ttgl_surat.getSelectedItem().toString().substring(0, 2) + " "
                        + Sequel.bulanINDONESIA("select date_format(tgl_surat,'%m') from surat_keterangan_dokter where "
                                + "no_rawat='" + TNoRW.getText() + "'") + " " + Ttgl_surat.getSelectedItem().toString().substring(6, 10));

                if (cekTgl.equals("ya")) {
                    param.put("nosuratdari", Tno_surat_dari.getText() + ", tanggal " + Ttgl_no_surat.getSelectedItem().toString());
                } else {
                    param.put("nosuratdari", Tno_surat_dari.getText());
                }
                
                if (kodepoli.equals("UMUM")) {
                    param.put("tensi", Ttensi.getText());
                    param.put("nadi", Tnadi.getText());
                    param.put("bmi", Tbmi.getText());
                    
                    if (cmbDinyatakan.getSelectedIndex() == 0 || cmbDinyatakan.getSelectedIndex() == 7) {
                        param.put("dinyatakan", "....................................................................................................."
                                + "..................................................................................................................."
                                + "........................................................................................");
                        param.put("keperluan", "......................................................................................................"
                                + "..................................................................................................................."
                                + "..................................................................................................................."
                                + ".............................................................................................................");
                    } else if (cmbDinyatakan.getSelectedIndex() == 5) {
                        param.put("dinyatakan", cmbDinyatakan.getSelectedItem().toString() + " " + Tdiagnosa.getText());
                        param.put("keperluan", Tkeperluan.getText());
                    } else {
                        param.put("dinyatakan", cmbDinyatakan.getSelectedItem().toString());
                        param.put("keperluan", Tkeperluan.getText());
                    }
                    
                    Valid.MyReport("rptSuratDokter.jasper", "report",
                            "::[ Surat Keterangan Dokter dari Poliklinik " + Sequel.cariIsi("select nm_poli from poliklinik where kd_poli='" + kodepoli + "'") + " ]::",
                            "SELECT date(now())", param);
                   
                } else if (kodepoli.equals("MAT")) {
                    param.put("hasil", Thasil.getText() + "\n");
                    param.put("keperluan", Tkeperluan.getText() + "\n");
                    Valid.MyReport("rptSuratDokterMata.jasper", "report",
                            "::[ Surat Keterangan Dokter dari Poliklinik " + Sequel.cariIsi("select nm_poli from poliklinik where kd_poli='" + kodepoli + "'") + " ]::",
                            "SELECT date(now())", param);
                    
                } else if (kodepoli.equals("THT")) {
                    param.put("hasil", Thasil.getText() + "\n");
                    param.put("dinyatakan", "Kesimpulan : ....................................................................................................."
                            + "..................................................................................................................."
                            + "..................................................................................................................."
                            + "........................................................................................");
                    param.put("keperluan", Tkeperluan.getText() + "\n");
                    Valid.MyReport("rptSuratDokterSemuaPoli.jasper", "report",
                            "::[ Surat Keterangan Dokter dari Poliklinik " + Sequel.cariIsi("select nm_poli from poliklinik where kd_poli='" + kodepoli + "'") + " ]::",
                            "SELECT date(now())", param);
                    
                } else if (kodepoli.equals("KLT")) {
                    param.put("hasil", Thasil.getText() + "\n");
                    param.put("keperluan", Tkeperluan.getText() + "\n");
                    if (cmbDinyatakan.getSelectedIndex() == 5) {
                        param.put("dinyatakan", cmbDinyatakan.getSelectedItem().toString() + " " + Tdiagnosa.getText() + "\n");
                    } else {
                        param.put("dinyatakan", cmbDinyatakan.getSelectedItem().toString() + "\n");
                    }
                    Valid.MyReport("rptSuratDokterKulit.jasper", "report",
                            "::[ Surat Keterangan Dokter dari Poliklinik " + Sequel.cariIsi("select nm_poli from poliklinik where kd_poli='" + kodepoli + "'") + " ]::",
                            "SELECT date(now())", param);
                    
                } else {
                    param.put("hasil", Thasil.getText() + "\n");
                    param.put("dinyatakan", "Kesimpulan : " + cmbDinyatakan.getSelectedItem().toString() + " " + Tdiagnosa.getText());
                    param.put("keperluan", Tkeperluan.getText() + "\n");
                    Valid.MyReport("rptSuratDokterSemuaPoli.jasper", "report",
                            "::[ Surat Keterangan Dokter dari Poliklinik " + Sequel.cariIsi("select nm_poli from poliklinik where kd_poli='" + kodepoli + "'") + " ]::",
                            "SELECT date(now())", param);
                }
            }
            
            TCari.setText(TNoRW.getText());
            tbSurat.requestFocus();
            emptTeks();
            tampil();            
        }
    }//GEN-LAST:event_MnSuratKetDokterActionPerformed

    private void Tno_surat_dariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tno_surat_dariKeyPressed
        Valid.pindah(evt, Tpermintaan, Tberlaku);
    }//GEN-LAST:event_Tno_surat_dariKeyPressed

    private void TkeperluanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkeperluanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Tbb.requestFocus();
        }
    }//GEN-LAST:event_TkeperluanKeyPressed

    private void TpermintaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpermintaanKeyPressed
        Valid.pindah(evt, TAlamat, Tno_surat_dari);
    }//GEN-LAST:event_TpermintaanKeyPressed

    private void TberlakuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TberlakuKeyPressed
        Valid.pindah(evt, cmbDinyatakan, cmbSelama);
    }//GEN-LAST:event_TberlakuKeyPressed

    private void TbbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbbKeyPressed
        Valid.pindah(evt, Tkeperluan, Ttb);
    }//GEN-LAST:event_TbbKeyPressed

    private void TtbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtbKeyPressed
        Valid.pindah(evt, Tbb, Tgol);
    }//GEN-LAST:event_TtbKeyPressed

    private void TgolKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgolKeyPressed
        Valid.pindah(evt, Ttb, Ttgl_surat);
    }//GEN-LAST:event_TgolKeyPressed

    private void ChkTglSuratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkTglSuratActionPerformed
        if (ChkTglSurat.isSelected() == true) {
            Ttgl_no_surat.setEnabled(true);
            Ttgl_no_surat.requestFocus();
        } else {
            Ttgl_no_surat.setEnabled(false);
            Ttgl_no_surat.setDate(new Date());
        }
    }//GEN-LAST:event_ChkTglSuratActionPerformed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tampil();
    }//GEN-LAST:event_BtnCariActionPerformed

    private void TgolKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgolKeyTyped
        evt.setKeyChar(Character.toUpperCase(evt.getKeyChar()));
    }//GEN-LAST:event_TgolKeyTyped

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari, BtnAll);
        }
    }//GEN-LAST:event_BtnCariKeyPressed

    private void ThasilKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ThasilKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ThasilKeyPressed

    private void TdiagnosaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdiagnosaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TdiagnosaKeyPressed

    private void MnSuratKetDokterManualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSuratKetDokterManualActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu datanya pada tabel...!!!!");
            tbSurat.requestFocus();
        } else if (Sequel.cariInteger("select count(-1) from surat_keterangan_dokter where no_rawat='" + TNoRW.getText() + "'") == 0) {
            JOptionPane.showMessageDialog(null, "Data surat keterangan dokter utk. pasien yg. bernama " + TPasien.getText() + "    \n"
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
                param.put("nmpasien", TPasien.getText());
                param.put("ttl", TTempLahr.getText() + ", " + TtglLahir.getText());
                param.put("jnskelamin", Tjk.getText());
                param.put("pekerjaan", TPekerjaan.getText());
                param.put("alamat", TAlamat.getText());
                param.put("permintaan", Tpermintaan.getText());
                param.put("bb", Tbb.getText() + " Kg.");
                param.put("tb", Ttb.getText() + " Cm.");
                param.put("goldarah", "- " + Tgol.getText() + " -");
                param.put("berlaku", Tberlaku.getText() + " (" + Sequel.Terbilang(Valid.SetAngka(Tberlaku.getText())) + ") "
                        + cmbSelama.getSelectedItem().toString() + " sejak tanggal dikeluarkan.");
                param.put("nmdokter", Tnmdokter.getText());
                param.put("nipdokter", kddokter);
                param.put("tglsurat", Ttgl_surat.getSelectedItem().toString().substring(0, 2) + " "
                        + Sequel.bulanINDONESIA("select date_format(tgl_surat,'%m') from surat_keterangan_dokter where "
                                + "no_rawat='" + TNoRW.getText() + "'") + " " + Ttgl_surat.getSelectedItem().toString().substring(6, 10));

                if (cekTgl.equals("ya")) {
                    param.put("nosuratdari", Tno_surat_dari.getText() + ", tanggal " + Ttgl_no_surat.getSelectedItem().toString());
                } else {
                    param.put("nosuratdari", Tno_surat_dari.getText());
                }

                if (kodepoli.equals("UMUM")) {
                    param.put("tensi", Ttensi.getText());
                    param.put("nadi", Tnadi.getText());
                    param.put("bmi", Tbmi.getText());
                    param.put("keperluan", Tkeperluan.getText() + "\n");
                    Valid.MyReport("rptSuratDokterManual.jasper", "report",
                            "::[ Surat Keterangan Dokter dari Poliklinik " + Sequel.cariIsi("select nm_poli from poliklinik where kd_poli='" + kodepoli + "'") + " ]::",
                            "SELECT date(now())", param);

                } else if (kodepoli.equals("MAT")) {
                    param.put("keperluan", Tkeperluan.getText() + "\n");
                    Valid.MyReport("rptSuratDokterMataManual1.jasper", "report",
                            "::[ Surat Keterangan Dokter dari Poliklinik " + Sequel.cariIsi("select nm_poli from poliklinik where kd_poli='" + kodepoli + "'") + " ]::",
                            "SELECT date(now())", param);
                    Valid.MyReport("rptSuratDokterMataManual2.jasper", "report",
                            "::[ Surat Keterangan Dokter dari Poliklinik " + Sequel.cariIsi("select nm_poli from poliklinik where kd_poli='" + kodepoli + "'") + " ]::",
                            "SELECT date(now())", param);

                } else if (kodepoli.equals("THT")) {
                    param.put("keperluan", Tkeperluan.getText() + "\n");
                    Valid.MyReport("rptSuratDokterPoliTHTManual.jasper", "report",
                            "::[ Surat Keterangan Dokter dari Poliklinik " + Sequel.cariIsi("select nm_poli from poliklinik where kd_poli='" + kodepoli + "'") + " ]::",
                            "SELECT date(now())", param);
                    
                } else if (kodepoli.equals("KLT")) {
                    param.put("keperluan", Tkeperluan.getText() + "\n");
                    Valid.MyReport("rptSuratDokterKulitManual.jasper", "report",
                            "::[ Surat Keterangan Dokter dari Poliklinik " + Sequel.cariIsi("select nm_poli from poliklinik where kd_poli='" + kodepoli + "'") + " ]::",
                            "SELECT date(now())", param);
                } else {                    
                    param.put("keperluan", Tkeperluan.getText() + "\n");
                    Valid.MyReport("rptSuratDokterSemuaPoliManual.jasper", "report",
                            "::[ Surat Keterangan Dokter dari Poliklinik " + Sequel.cariIsi("select nm_poli from poliklinik where kd_poli='" + kodepoli + "'") + " ]::",
                            "SELECT date(now())", param);
                }
            }
            
            TCari.setText(TNoRW.getText());
            tbSurat.requestFocus();
            emptTeks();
            tampil();
        }
    }//GEN-LAST:event_MnSuratKetDokterManualActionPerformed

    private void TPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPasienKeyPressed
        Valid.pindah(evt, TPasien, TTempLahr);
    }//GEN-LAST:event_TPasienKeyPressed

    private void BtnDokumenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokumenActionPerformed
        akses.setform("DlgSuratKeteranganDokter");
        dokumen.isCek();
        dokumen.ChkInput.setSelected(false);
        dokumen.isForm();
        dokumen.setSize(650, internalFrame1.getHeight() - 40);
        dokumen.setLocationRelativeTo(internalFrame1);
        dokumen.setAlwaysOnTop(false);
        dokumen.setVisible(true);
    }//GEN-LAST:event_BtnDokumenActionPerformed

    private void MnHasilPemeriksaanPenunjangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHasilPemeriksaanPenunjangActionPerformed
        if (TNoRW.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRW, "Pasien");
        } else {
            akses.setform("DlgSuratKeteranganDokter");
            DlgHasilPenunjangMedis form = new DlgHasilPenunjangMedis(null, false);
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setData(TNoRW.getText(), TPasien.getText(), TNoRM.getText());
            form.setVisible(true);
        }
    }//GEN-LAST:event_MnHasilPemeriksaanPenunjangActionPerformed

    private void BtnPasteHasilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPasteHasilActionPerformed
        if (akses.getPasteData().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan copy dulu data yg. dipilih..!!!!");
        } else {
            if (Thasil.getText().equals("")) {
                Thasil.setText(akses.getPasteData());
                akses.setCopyData("");
            } else {
                Thasil.setText(Thasil.getText() + "\n\n" + akses.getPasteData());
                akses.setCopyData("");
            }
        }
    }//GEN-LAST:event_BtnPasteHasilActionPerformed

    private void ChkYbsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkYbsActionPerformed
        if (ChkYbs.isSelected() == true) {
            Tpermintaan.setText("Ybs. Secara Lisan");
        } else {
            Tpermintaan.setText("");
        }
    }//GEN-LAST:event_ChkYbsActionPerformed

    private void MnSuratKetDokterMCUhasildiagnosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSuratKetDokterMCUhasildiagnosaActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu datanya pada tabel...!!!!");
            tbSurat.requestFocus();
        } else if (Sequel.cariInteger("select count(-1) from surat_keterangan_dokter where no_rawat='" + TNoRW.getText() + "'") == 0) {
            JOptionPane.showMessageDialog(null, "Data surat keterangan dokter utk. pasien yg. bernama " + TPasien.getText() + "    \n"
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
                param.put("nmpasien", TPasien.getText());
                param.put("ttl", TTempLahr.getText() + ", " + TtglLahir.getText());
                param.put("jnskelamin", Tjk.getText());
                param.put("pekerjaan", TPekerjaan.getText());
                param.put("alamat", TAlamat.getText());
                param.put("permintaan", Tpermintaan.getText());                
                param.put("bb", Tbb.getText() + " Kg.");
                param.put("tb", Ttb.getText() + " Cm.");
                param.put("goldarah", "- " + Tgol.getText() + " -");
                param.put("berlaku", Tberlaku.getText() + " (" + Sequel.Terbilang(Valid.SetAngka(Tberlaku.getText())) + ") "
                        + cmbSelama.getSelectedItem().toString() + " sejak tanggal dikeluarkan.");                
                param.put("nmdokter", Tnmdokter.getText());
                param.put("nipdokter", kddokter);
                param.put("tglsurat", Ttgl_surat.getSelectedItem().toString().substring(0, 2) + " "
                        + Sequel.bulanINDONESIA("select date_format(tgl_surat,'%m') from surat_keterangan_dokter where "
                                + "no_rawat='" + TNoRW.getText() + "'") + " " + Ttgl_surat.getSelectedItem().toString().substring(6, 10));

                if (cekTgl.equals("ya")) {
                    param.put("nosuratdari", Tno_surat_dari.getText() + ", tanggal " + Ttgl_no_surat.getSelectedItem().toString());
                } else {
                    param.put("nosuratdari", Tno_surat_dari.getText());
                }

                param.put("hasil", Thasil.getText());
                param.put("diagnosa", Tdiagnosa.getText());
                param.put("keperluan", Tkeperluan.getText());
                Valid.MyReport("rptSuratDokterHasilDiagnosa.jasper", "report",
                        "::[ Surat Keterangan Dokter (MCU) " + Sequel.cariIsi("select nm_poli from poliklinik where kd_poli='" + kodepoli + "'") + " ]::",
                        "SELECT date(now())", param);
            }

            TCari.setText(TNoRW.getText());
            tbSurat.requestFocus();
            emptTeks();
            tampil();
        }
    }//GEN-LAST:event_MnSuratKetDokterMCUhasildiagnosaActionPerformed

    private void MnSuratKetDokterCatenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSuratKetDokterCatenActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu datanya pada tabel...!!!!");
            tbSurat.requestFocus();
        } else if (Sequel.cariInteger("select count(-1) from surat_keterangan_dokter where no_rawat='" + TNoRW.getText() + "'") == 0) {
            JOptionPane.showMessageDialog(null, "Data surat keterangan dokter utk. pasien yg. bernama " + TPasien.getText() + "    \n"
                    + "dg. no. rawat " + TNoRW.getText() + " belum tersimpan...!!!!");
        } else {
            if (tbSurat.getSelectedRow() > -1) {
                if (tbSurat.getValueAt(tbSurat.getSelectedRow(), 30).toString().equals("tidak")) {
                    JOptionPane.showMessageDialog(null, "Jenis surat keterangan dokter yg. tersimpan bukan caten...!!!!");
                } else {
                    Map<String, Object> param = new HashMap<>();
                    param.put("namars", akses.getnamars());
                    param.put("alamatrs", akses.getalamatrs());
                    param.put("kotars", akses.getkabupatenrs());
                    param.put("propinsirs", akses.getpropinsirs());
                    param.put("kontakrs", akses.getkontakrs());
                    param.put("emailrs", akses.getemailrs());
                    param.put("logo", Sequel.cariGambar("select logo_kabupaten from setting"));
                    param.put("nosurat", TnoDokumen.getText() + " / " + TNoSurat.getText());
                    param.put("nmpasien", TPasien.getText());
                    param.put("ttl", TTempLahr.getText() + ", " + TtglLahir.getText());
                    param.put("jnskelamin", Tjk.getText());
                    param.put("pekerjaan", TPekerjaan.getText());
                    param.put("alamat", TAlamat.getText());
                    param.put("permintaan", Tpermintaan.getText());
                    param.put("bb", Tbb.getText() + " Kg.");
                    param.put("tb", Ttb.getText() + " Cm.");
                    param.put("goldarah", "- " + Tgol.getText() + " -");
                    param.put("berlaku", Tberlaku.getText() + " (" + Sequel.Terbilang(Valid.SetAngka(Tberlaku.getText())) + ") "
                            + cmbSelama.getSelectedItem().toString() + " sejak tanggal dikeluarkan.");
                    param.put("nmdokter", Tnmdokter.getText());
                    param.put("nipdokter", kddokter);
                    param.put("tglsurat", Ttgl_surat.getSelectedItem().toString().substring(0, 2) + " "
                            + Sequel.bulanINDONESIA("select date_format(tgl_surat,'%m') from surat_keterangan_dokter where "
                                    + "no_rawat='" + TNoRW.getText() + "'") + " " + Ttgl_surat.getSelectedItem().toString().substring(6, 10));

                    if (cekTgl.equals("ya")) {
                        param.put("nosuratdari", Tno_surat_dari.getText() + ", tanggal " + Ttgl_no_surat.getSelectedItem().toString());
                    } else {
                        param.put("nosuratdari", Tno_surat_dari.getText());
                    }

                    if (kodepoli.equals("UMUM")) {
                        if (cmbDinyatakan.getSelectedIndex() == 0 || cmbDinyatakan.getSelectedIndex() == 7) {
                            param.put("dinyatakan", "....................................................................................................."
                                    + "..................................................................................................................."
                                    + "........................................................................................");
                            param.put("keperluan", "......................................................................................................"
                                    + "..................................................................................................................."
                                    + "..................................................................................................................."
                                    + ".............................................................................................................");
                        } else if (cmbDinyatakan.getSelectedIndex() == 6) {
                            param.put("dinyatakan", cmbDinyatakan.getSelectedItem().toString());
                            param.put("keperluan", Tkeperluan.getText());
                        } else {
                            param.put("dinyatakan", cmbDinyatakan.getSelectedItem().toString());
                            param.put("keperluan", Tkeperluan.getText());
                        }

                        Valid.MyReport("rptSuratDokterCaten.jasper", "report",
                                "::[ Surat Keterangan Dokter dari Poliklinik " + Sequel.cariIsi("select nm_poli from poliklinik where kd_poli='" + kodepoli + "'") + " ]::",
                                "SELECT date(now())", param);

                    } else {
                        param.put("hasil", Thasil.getText() + "\n");
                        param.put("dinyatakan", "Kesimpulan : " + cmbDinyatakan.getSelectedItem().toString() + " " + Tdiagnosa.getText());
                        param.put("keperluan", Tkeperluan.getText() + "\n");
                        Valid.MyReport("rptSuratDokterSemuaPoli.jasper", "report",
                                "::[ Surat Keterangan Dokter dari Poliklinik " + Sequel.cariIsi("select nm_poli from poliklinik where kd_poli='" + kodepoli + "'") + " ]::",
                                "SELECT date(now())", param);
                    }
                }

                TCari.setText(TNoRW.getText());
                tbSurat.requestFocus();
                emptTeks();
                tampil();
            }
        }
    }//GEN-LAST:event_MnSuratKetDokterCatenActionPerformed

    private void TtensiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtensiKeyPressed
        Valid.pindah(evt, Ttgl_surat, Tnadi);
    }//GEN-LAST:event_TtensiKeyPressed

    private void TnadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnadiKeyPressed
        Valid.pindah(evt, Ttensi, Tbmi);
    }//GEN-LAST:event_TnadiKeyPressed

    private void TbmiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbmiKeyPressed
        Valid.pindah(evt, Tnadi, BtnDokter);
    }//GEN-LAST:event_TbmiKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgSuratKeteranganDokter dialog = new DlgSuratKeteranganDokter(new javax.swing.JFrame(), true);
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
    private widget.Button BtnPasteHasil;
    private widget.Button BtnSimpan;
    public widget.CekBox ChkCaten;
    public widget.CekBox ChkTglSurat;
    public widget.CekBox ChkYbs;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnHasilPemeriksaanPenunjang;
    private javax.swing.JMenuItem MnSuratKetDokter;
    private javax.swing.JMenuItem MnSuratKetDokterCaten;
    private javax.swing.JMenuItem MnSuratKetDokterMCUhasildiagnosa;
    private javax.swing.JMenuItem MnSuratKetDokterManual;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll10;
    private widget.ScrollPane Scroll8;
    private widget.ScrollPane Scroll9;
    private widget.TextBox TAlamat;
    public widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRW;
    private widget.TextBox TNoSurat;
    private widget.TextBox TPasien;
    private widget.TextBox TPekerjaan;
    private widget.TextBox TTempLahr;
    private widget.TextBox Tbb;
    private widget.TextBox Tberlaku;
    private widget.TextBox Tbmi;
    private widget.TextArea Tdiagnosa;
    private widget.TextBox Tgol;
    private widget.TextArea Thasil;
    private widget.TextBox Tjk;
    private widget.TextArea Tkeperluan;
    private widget.TextBox Tnadi;
    private widget.TextBox Tnmdokter;
    private widget.TextBox TnoDokumen;
    private widget.TextBox Tno_surat_dari;
    private widget.TextBox Tpermintaan;
    private widget.TextBox Ttb;
    private widget.TextBox Ttensi;
    private widget.TextBox TtglLahir;
    private widget.Tanggal Ttgl_no_surat;
    private widget.Tanggal Ttgl_surat;
    private widget.ComboBox cmbDinyatakan;
    private widget.ComboBox cmbSelama;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel15;
    private widget.Label jLabel17;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel22;
    private widget.Label jLabel24;
    private widget.Label jLabel25;
    private widget.Label jLabel26;
    private widget.Label jLabel27;
    private widget.Label jLabel28;
    private widget.Label jLabel29;
    private widget.Label jLabel3;
    private widget.Label jLabel30;
    private widget.Label jLabel31;
    private widget.Label jLabel32;
    private widget.Label jLabel33;
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private widget.panelisi panelGlass7;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.Table tbSurat;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT rp.no_rawat, sk.no_surat, p.no_rkm_medis, sk.nm_pasien, sk.tmpt_lahir, "
                    + "DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y') tgllahir, sk.pekerjaan, sk.tempat_tinggal, sk.permintaan_dari, sk.no_surat_dari, "
                    + "sk.ada_tgl_nosurat, DATE_FORMAT(sk.tgl_surat_dari,'%d-%m-%Y') tglnosurat, sk.pemeriksaan_dinyatakan, "
                    + "sk.keperluan, sk.lama_berlaku, sk.satuan_lama, sk.bb, sk.tb, sk.gol_darah, pg.nama dokter, if(p.jk='L','Laki-laki','Perempuan') jk, "
                    + "DATE_FORMAT(sk.tgl_surat,'%d-%m-%Y') tglsurat, sk.no_surat, sk.tgl_surat, sk.tgl_surat_dari, sk.nip_dokter, rp.kd_poli, "
                    + "sk.hasil_pemeriksaan, sk.diagnosa, sk.no_dokumen, sk.caten, sk.td, sk.nadi, sk.bmi FROM reg_periksa rp "
                    + "INNER JOIN surat_keterangan_dokter sk ON sk.no_rawat = rp.no_rawat INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                    + "INNER JOIN pegawai pg ON pg.nik = sk.nip_dokter WHERE "
                    + "sk.tgl_surat BETWEEN ? AND ? AND sk.no_rawat LIKE ? OR "
                    + "sk.tgl_surat BETWEEN ? AND ? AND sk.no_surat LIKE ? OR "
                    + "sk.tgl_surat BETWEEN ? AND ? AND p.no_rkm_medis LIKE ? OR "
                    + "sk.tgl_surat BETWEEN ? AND ? AND sk.nm_pasien LIKE ? OR "
                    + "sk.tgl_surat BETWEEN ? AND ? AND sk.tmpt_lahir LIKE ? OR "
                    + "sk.tgl_surat BETWEEN ? AND ? AND sk.no_surat_dari LIKE ? OR "
                    + "sk.tgl_surat BETWEEN ? AND ? AND sk.pekerjaan LIKE ? OR "
                    + "sk.tgl_surat BETWEEN ? AND ? AND pg.nama LIKE ? OR "
                    + "sk.tgl_surat BETWEEN ? AND ? AND sk.tempat_tinggal LIKE ? "
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
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),
                        rs.getString("no_surat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),                        
                        rs.getString("tmpt_lahir"),
                        rs.getString("tgllahir"),
                        rs.getString("jk"),
                        rs.getString("pekerjaan"),
                        rs.getString("tempat_tinggal"),
                        rs.getString("permintaan_dari"),
                        rs.getString("no_surat_dari"),
                        rs.getString("ada_tgl_nosurat"),
                        rs.getString("tglnosurat"),
                        rs.getString("pemeriksaan_dinyatakan"),
                        rs.getString("keperluan"),
                        rs.getString("lama_berlaku"),
                        rs.getString("satuan_lama"),
                        rs.getString("bb"),
                        rs.getString("tb"),
                        rs.getString("gol_darah"),
                        rs.getString("dokter"),
                        rs.getString("tglsurat"),
                        rs.getString("no_surat"),
                        rs.getString("tgl_surat"),
                        rs.getString("tgl_surat_dari"),
                        rs.getString("nip_dokter"),
                        rs.getString("kd_poli"),
                        rs.getString("hasil_pemeriksaan"),
                        rs.getString("diagnosa"),
                        rs.getString("no_dokumen"),
                        rs.getString("caten"),                        
                        rs.getString("td"),
                        rs.getString("nadi"),
                        rs.getString("bmi")                            
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
        TnoDokumen.setText("");
        sttsnomor = "";
        Tjk.setText("");
        TPekerjaan.setText("");
        TAlamat.setText("");
        ChkYbs.setSelected(false);
        Tpermintaan.setText("");
        Tno_surat_dari.setText("");
        ChkTglSurat.setSelected(false);
        Ttgl_no_surat.setEnabled(false);
        Ttgl_no_surat.setDate(new Date());
        cmbDinyatakan.setSelectedIndex(0);
        Tberlaku.setText("");
        cmbSelama.setSelectedIndex(0);
        Tno_surat_dari.setText("");
        Tkeperluan.setText("MELENGKAPI PERSYARATAN ");
        Tbb.setText("");
        Ttb.setText("");
        Tgol.setText("");
        kddokter = "";
        Tnmdokter.setText(""); 
        DTPCari1.setDate(Ttgl_surat.getDate());
        DTPCari2.setDate(new Date());
        Ttgl_surat.setDate(new Date());
        Thasil.setText("");
        Tdiagnosa.setText("");
        ChkCaten.setSelected(false);
        Ttensi.setText("");
        Tnadi.setText("");
        Tbmi.setText("");
        autoNomorSurat();
    }

    private void getData() {
        nosrt = "";
        kddokter = "";
        cekTgl = "";
        kodepoli = "";
        cekCaten = "";
        
        if (tbSurat.getSelectedRow() != -1) {
            TNoRW.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 0).toString());
            TNoSurat.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 1).toString());
            TNoRM.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 2).toString());
            TPasien.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 3).toString());
            TTempLahr.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 4).toString());
            Tjk.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 6).toString());
            TPekerjaan.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 7).toString());
            TAlamat.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 8).toString());
            Tpermintaan.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 9).toString());
            Tno_surat_dari.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 10).toString());
            cekTgl = tbSurat.getValueAt(tbSurat.getSelectedRow(), 11).toString();
            Valid.SetTgl(Ttgl_no_surat, tbSurat.getValueAt(tbSurat.getSelectedRow(), 24).toString());
            cmbDinyatakan.setSelectedItem(tbSurat.getValueAt(tbSurat.getSelectedRow(), 13).toString());
            Tkeperluan.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 14).toString());            
            Tberlaku.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 15).toString());
            cmbSelama.setSelectedItem(tbSurat.getValueAt(tbSurat.getSelectedRow(), 16).toString());            
            Tbb.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 17).toString());
            Ttb.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 18).toString());
            Tgol.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 19).toString());
            Tnmdokter.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 20).toString());
            Valid.SetTgl(Ttgl_surat, tbSurat.getValueAt(tbSurat.getSelectedRow(), 23).toString());
            nosrt = tbSurat.getValueAt(tbSurat.getSelectedRow(), 22).toString();
            kddokter = tbSurat.getValueAt(tbSurat.getSelectedRow(), 25).toString();
            kodepoli = tbSurat.getValueAt(tbSurat.getSelectedRow(), 26).toString();
            Thasil.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 27).toString());
            Tdiagnosa.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 28).toString());
            TnoDokumen.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 29).toString());
            cekCaten = tbSurat.getValueAt(tbSurat.getSelectedRow(), 30).toString();
            TtglLahir.setText(Valid.SetTglINDONESIA(Sequel.cariIsi("select tgl_lahir from pasien where no_rkm_medis='" + TNoRM.getText() + "'")));
            Ttensi.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 31).toString());
            Tnadi.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 32).toString());
            Tbmi.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 33).toString());
            
            if (cekTgl.equals("ya")) {
                ChkTglSurat.setSelected(true);
                Ttgl_no_surat.setEnabled(true);
            } else {
                ChkTglSurat.setSelected(false);
                Ttgl_no_surat.setEnabled(false);
            }
            
            if (cekCaten.equals("ya")) {
                ChkCaten.setSelected(true);
            } else {
                ChkCaten.setSelected(false);
            }
        }
    }
    
    public void isCek(){
       BtnSimpan.setEnabled(akses.getsurat_keterangan_kir_mcu());       
       BtnEdit.setEnabled(akses.getsurat_keterangan_kir_mcu());
       BtnHapus.setEnabled(akses.getsurat_keterangan_kir_mcu());
    }
    
    public void setData(String norw, String kdpoli) {
        kodepoli = kdpoli;
        autoNomorSurat();        
        TPasien.requestFocus();
        
        if (kdpoli.equals("MAT")) {
            cmbDinyatakan.setEnabled(false);
            Thasil.setText("Tidak Juling / Juling\n"
                    + "Tidak / Menggunakan Kaca Mata / Lensa Kontak\n"
                    + "Visus : OD : \n"
                    + "        OS : \n"
                    + "Funduscopy : \n"
                    + "Segmen Anterior : Baik / Normal");
        } else if (kdpoli.equals("THT")) {
            cmbDinyatakan.setEnabled(false);
            Thasil.setText(Sequel.cariIsi("select CONCAT('Telah dilakukan pemeriksaan dokter : ',pemeriksaan,',\n"
                    + "dengan diagnosa : ',pr.diagnosa) from pemeriksaan_ralan where no_rawat='" + norw + "'"));
        } else {
            cmbDinyatakan.setEnabled(true);
            Thasil.setText("");
        }
        
        try {
            ps1 = koneksi.prepareStatement("SELECT rp.no_rawat, p.no_rkm_medis, p.nm_pasien, p.tmp_lahir, p.pekerjaan, DATE_FORMAT(p.tgl_lahir,'%d') hari, "
                    + "YEAR(p.tgl_lahir) thn_lahir, concat(p.alamat,', Kel./Desa ',kl.nm_kel,', Kec. ',kc.nm_kec,', Kab./Kodya ',kb.nm_kab) alamat, "
                    + "p.pekerjaan, d.kd_dokter, d.nm_dokter, rp.tgl_registrasi, if(p.jk='L','Laki-laki','Perempuan') jk FROM reg_periksa rp "
                    + "INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis INNER JOIN kelurahan kl ON kl.kd_kel = p.kd_kel_domisili_pasien "
                    + "INNER JOIN kecamatan kc ON kc.kd_kec = p.kd_kec_domisili_pasien INNER JOIN kabupaten kb ON kb.kd_kab = p.kd_kab_domisili_pasien "
                    + "INNER JOIN dokter d ON d.kd_dokter = rp.kd_dokter WHERE rp.no_rawat = '" + norw + "'");
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
                    Ttgl_no_surat.setDate(rs1.getDate("tgl_registrasi"));
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
        thn = Sequel.cariIsi("select date_format(tgl_surat,'%Y') from surat_keterangan_dokter order by tgl_surat desc limit 1");
        Valid.autoNomer6("select ifnull(MAX(CONVERT(LEFT(no_surat,4),signed)),0) from surat_keterangan_dokter where "
                + "year(tgl_surat) ='" + thn + "'", noSuratFix, 4, TNoSurat);
    }
}
