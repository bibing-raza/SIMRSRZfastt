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
public class DlgSuratKeteranganNapza extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1, tabMode2;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps1, psNoLis, ps11, ps22, ps33;
    private ResultSet rs, rs1, rsNoLis, rs11, rs22, rs33;
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    private DlgMasterNomorDokumen dokumen = new DlgMasterNomorDokumen(null, false);
    private String nosurat = "", thn = "", thc = "", bzo = "", met = "", mop = "", coc = "",
            amp = "", tglsurat = "", kddokter = "", noLIS = "", cekLIS = "", ketLIS = "",
            tglLIS = "", jamLIS = "", drpengirim = "", tglPeriksaLIS = "", jamPeriksaLIS = "", sttsnomor = "";
    private int x = 0;

    /** Creates new form DlgSpesialis
     * @param parent
     * @param modal */
    public DlgSuratKeteranganNapza(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        Object[] row = {"No. Rawat", "No. Surat", "No. RM", "Nama Pasien", "Jns. Kelamin", "Tempat Lahir", "Tgl. Lahir",
            "Pekerjaan", "Tmpt. Tinggal", "Permintaan Dari", "No. Surat Dari", "Tanggalnya", "Tgl. Surat Dari", "Keperluan",
            "NIP", "Dokter Pemeriksa", "Tes THC", "Tes BZO", "Tes MET", "Tes MOP", "Tes COC", "Tes AMP",
            "Hasil THC", "Hasil BZO", "Hasil MET", "Hasil MOP", "Hasil COC", "Hasil AMP", "Hasil Kesimpulan",
            "Tgl. Surat", "nosrt", "tgl_surat", "tgl_surat_dari", "no_dokumen"};
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
                column.setPreferredWidth(90);
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
                column.setPreferredWidth(250);
            } else if (i == 10) {
                column.setPreferredWidth(250);
            } else if (i == 11) {
                column.setPreferredWidth(85);
            } else if (i == 12) {
                column.setPreferredWidth(85);
            } else if (i == 13) {
                column.setPreferredWidth(250);
            } else if (i == 14) {
                column.setPreferredWidth(100);
            } else if (i == 15) {
                column.setPreferredWidth(250);
            } else if (i == 16) {
                column.setPreferredWidth(75);
            } else if (i == 17) {
                column.setPreferredWidth(75);
            } else if (i == 18) {
                column.setPreferredWidth(75);
            } else if (i == 19) {
                column.setPreferredWidth(75);
            } else if (i == 20) {
                column.setPreferredWidth(75);
            } else if (i == 21) {
                column.setPreferredWidth(75);
            } else if (i == 22) {
                column.setPreferredWidth(90);
            } else if (i == 23) {
                column.setPreferredWidth(90);
            } else if (i == 24) {
                column.setPreferredWidth(90);
            } else if (i == 25) {
                column.setPreferredWidth(90);
            } else if (i == 26) {
                column.setPreferredWidth(90);
            } else if (i == 27) {
                column.setPreferredWidth(90);
            } else if (i == 28) {
                column.setPreferredWidth(100);
            } else if (i == 29) {
                column.setPreferredWidth(75);
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
        
        tabMode1 = new DefaultTableModel(null, new Object[]{
            "Pasien", "No. LIS", "Keterangan Hasil Lab.", "cekok", "norawat", "Tgl. Periksa",
            "Jam Periksa", "Dokter Pengirim", "Jns. Rawat"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbLIS.setModel(tabMode1);
        tbLIS.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbLIS.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        for (int i = 0; i < 9; i++) {
            TableColumn column = tbLIS.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(195);
            } else if (i == 1) {
                column.setPreferredWidth(75);
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
                column.setPreferredWidth(210);
            } else if (i == 8) {
                column.setPreferredWidth(65);
            }
        }
        tbLIS.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode2 = new DefaultTableModel(null, new Object[]{
            "Jenis Pemeriksaan/Item", "Nilai Hasil", "Satuan", "Flag Kode", "Nilai Rujukan", "Waktu Selesai", "Metode Pemeriksaan"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbHasil.setModel(tabMode2);
        tbHasil.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbHasil.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 7; i++) {
            TableColumn column = tbHasil.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(250);
            } else if (i == 1) {
                column.setPreferredWidth(150);
            } else if (i == 2) {
                column.setPreferredWidth(90);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else if (i == 4) {
                column.setPreferredWidth(90);
            } else if (i == 5) {
                column.setPreferredWidth(120);
            } else if (i == 6) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbHasil.setDefaultRenderer(Object.class, new WarnaTable());
        
        TCari.setDocument(new batasInput((int) 100).getKata(TCari));        
        TPekerjaan.setDocument(new batasInput((int) 100).getKata(TPekerjaan));
        TPermintaanDari.setDocument(new batasInput((int) 180).getKata(TPermintaanDari));
        TnoSuratDari.setDocument(new batasInput((int) 150).getKata(TnoSuratDari));
        TTempLahr.setDocument(new batasInput((int) 150).getKata(TTempLahr));
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
                if (akses.getform().equals("DlgSuratKeteranganNapza")) {
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
                if (akses.getform().equals("DlgSuratKeteranganNapza")) {
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
        
        try {
            ps11 = koneksi.prepareStatement("SELECT lhp.kategori_pemeriksaan_nama FROM lis_reg lr LEFT JOIN lis_hasil_periksa_lab lhp on lhp.no_lab=lr.no_lab "
                    + "LEFT JOIN lis_hasil_data_pasien lhdp on lhdp.no_lab=lr.no_lab WHERE lr.no_lab=? "
                    + "GROUP BY lhp.kategori_pemeriksaan_nama ORDER BY lhp.kategori_pemeriksaan_no_urut, lhp.sub_kategori_pemeriksaan_no_urut,lhp.pemeriksaan_no_urut");
            
            ps22 = koneksi.prepareStatement("SELECT lhp.sub_kategori_pemeriksaan_nama FROM lis_reg lr LEFT JOIN lis_hasil_periksa_lab lhp on lhp.no_lab=lr.no_lab "
                    + "LEFT JOIN lis_hasil_data_pasien lhdp on lhdp.no_lab=lr.no_lab WHERE lr.no_lab=? "
                    + "and lhp.kategori_pemeriksaan_nama=? GROUP BY lhp.sub_kategori_pemeriksaan_nama "
                    + "ORDER BY lhp.kategori_pemeriksaan_no_urut, lhp.sub_kategori_pemeriksaan_no_urut,lhp.sub_kategori_pemeriksaan_nama desc,lhp.pemeriksaan_no_urut");
            
            ps33 = koneksi.prepareStatement("SELECT lhp.pemeriksaan_nama, lhp.nilai_hasil, lhp.satuan, lhp.flag_kode, "
                    + "lhp.nilai_rujukan, DATE_FORMAT(lhdp.waktu_insert,'%d/%m/%Y - %h:%i') wkt_selesai, lhp.metode "
                    + "FROM lis_reg lr LEFT JOIN lis_hasil_periksa_lab lhp ON lhp.no_lab = lr.no_lab "
                    + "LEFT JOIN lis_hasil_data_pasien lhdp ON lhdp.no_lab=lr.no_lab WHERE lr.no_lab=? "
                    + "and lhp.sub_kategori_pemeriksaan_nama=? and lhp.kategori_pemeriksaan_nama=? GROUP BY lhp.pemeriksaan_nama "
                    + "ORDER BY lhp.kategori_pemeriksaan_no_urut, lhp.sub_kategori_pemeriksaan_no_urut,lhp.pemeriksaan_no_urut");
        } catch (Exception e) {
            System.out.println(e);
        }
        
        ChkAccor.setSelected(false);
        isMenu();
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
        MnCetakSurat = new javax.swing.JMenuItem();
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
        Ttmpt_tinggal = new widget.TextBox();
        jLabel14 = new widget.Label();
        Ttgl_surat = new widget.Tanggal();
        jLabel15 = new widget.Label();
        Tnmdokter = new widget.TextBox();
        BtnDokter = new widget.Button();
        TtglLahir = new widget.TextBox();
        jLabel16 = new widget.Label();
        TPermintaanDari = new widget.TextBox();
        jLabel17 = new widget.Label();
        TnoSuratDari = new widget.TextBox();
        TtglSuratDari = new widget.Tanggal();
        jLabel18 = new widget.Label();
        Scroll7 = new widget.ScrollPane();
        Tkeperluan = new widget.TextArea();
        jLabel13 = new widget.Label();
        ChkTHC = new widget.CekBox();
        ChkBZO = new widget.CekBox();
        ChkMET = new widget.CekBox();
        ChkMOP = new widget.CekBox();
        ChkCOC = new widget.CekBox();
        ChkAMP = new widget.CekBox();
        cmbTHC = new widget.ComboBox();
        cmbBZO = new widget.ComboBox();
        cmbMET = new widget.ComboBox();
        cmbMOP = new widget.ComboBox();
        cmbCOC = new widget.ComboBox();
        cmbAMP = new widget.ComboBox();
        ChkSemuaTes = new widget.CekBox();
        cmbSemuaHasilTes = new widget.ComboBox();
        jLabel20 = new widget.Label();
        cmbKesHasil = new widget.ComboBox();
        ChkTglSurat = new widget.CekBox();
        TnoDokumen = new widget.TextBox();
        BtnDokumen = new widget.Button();
        PanelAccor = new widget.PanelBiasa();
        ChkAccor = new widget.CekBox();
        FormMenu = new widget.PanelBiasa();
        panelGlass10 = new widget.panelisi();
        Scroll3 = new widget.ScrollPane();
        tbLIS = new widget.Table();
        panelGlass11 = new widget.panelisi();
        jLabel12 = new widget.Label();
        TCari1 = new widget.TextBox();
        jLabel22 = new widget.Label();
        cmbHlm = new widget.ComboBox();
        BtnCari1 = new widget.Button();
        Scroll4 = new widget.ScrollPane();
        tbHasil = new widget.Table();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnCetakSurat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakSurat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakSurat.setText("Cetak Surat Keterangan NAPZA/Narkoba");
        MnCetakSurat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCetakSurat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCetakSurat.setName("MnCetakSurat"); // NOI18N
        MnCetakSurat.setPreferredSize(new java.awt.Dimension(250, 26));
        MnCetakSurat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakSuratActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnCetakSurat);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Surat Keterangan NAPZA/Narkoba ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
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
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "04-04-2024" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "04-04-2024" }));
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
        TNoSurat.setBounds(285, 38, 180, 23);

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
        jLabel5.setBounds(0, 66, 105, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Tempat Lahir : ");
        jLabel8.setName("jLabel8"); // NOI18N
        panelGlass7.add(jLabel8);
        jLabel8.setBounds(245, 66, 88, 23);

        TTempLahr.setForeground(new java.awt.Color(0, 0, 0));
        TTempLahr.setName("TTempLahr"); // NOI18N
        TTempLahr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTempLahrKeyPressed(evt);
            }
        });
        panelGlass7.add(TTempLahr);
        TTempLahr.setBounds(334, 66, 185, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Jns. Kelamin : ");
        jLabel9.setName("jLabel9"); // NOI18N
        panelGlass7.add(jLabel9);
        jLabel9.setBounds(525, 66, 70, 23);

        Tjk.setEditable(false);
        Tjk.setForeground(new java.awt.Color(0, 0, 0));
        Tjk.setName("Tjk"); // NOI18N
        panelGlass7.add(Tjk);
        Tjk.setBounds(595, 66, 80, 23);

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
        TPekerjaan.setBounds(105, 94, 570, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Tempat Tinggal : ");
        jLabel11.setName("jLabel11"); // NOI18N
        panelGlass7.add(jLabel11);
        jLabel11.setBounds(0, 122, 105, 23);

        Ttmpt_tinggal.setForeground(new java.awt.Color(0, 0, 0));
        Ttmpt_tinggal.setName("Ttmpt_tinggal"); // NOI18N
        Ttmpt_tinggal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Ttmpt_tinggalKeyPressed(evt);
            }
        });
        panelGlass7.add(Ttmpt_tinggal);
        Ttmpt_tinggal.setBounds(105, 122, 570, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Tgl. Surat : ");
        jLabel14.setName("jLabel14"); // NOI18N
        panelGlass7.add(jLabel14);
        jLabel14.setBounds(700, 262, 150, 23);

        Ttgl_surat.setEditable(false);
        Ttgl_surat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "04-04-2024" }));
        Ttgl_surat.setDisplayFormat("dd-MM-yyyy");
        Ttgl_surat.setName("Ttgl_surat"); // NOI18N
        Ttgl_surat.setOpaque(false);
        panelGlass7.add(Ttgl_surat);
        Ttgl_surat.setBounds(850, 262, 90, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Dokter Pemeriksa : ");
        jLabel15.setName("jLabel15"); // NOI18N
        panelGlass7.add(jLabel15);
        jLabel15.setBounds(0, 267, 105, 23);

        Tnmdokter.setEditable(false);
        Tnmdokter.setForeground(new java.awt.Color(0, 0, 0));
        Tnmdokter.setName("Tnmdokter"); // NOI18N
        panelGlass7.add(Tnmdokter);
        Tnmdokter.setBounds(105, 267, 540, 23);

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
        BtnDokter.setBounds(645, 267, 28, 23);

        TtglLahir.setEditable(false);
        TtglLahir.setForeground(new java.awt.Color(0, 0, 0));
        TtglLahir.setName("TtglLahir"); // NOI18N
        panelGlass7.add(TtglLahir);
        TtglLahir.setBounds(105, 66, 140, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Permintaan Dari : ");
        jLabel16.setName("jLabel16"); // NOI18N
        panelGlass7.add(jLabel16);
        jLabel16.setBounds(0, 150, 105, 23);

        TPermintaanDari.setForeground(new java.awt.Color(0, 0, 0));
        TPermintaanDari.setName("TPermintaanDari"); // NOI18N
        TPermintaanDari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPermintaanDariKeyPressed(evt);
            }
        });
        panelGlass7.add(TPermintaanDari);
        TPermintaanDari.setBounds(105, 150, 570, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("No. Surat Dari : ");
        jLabel17.setName("jLabel17"); // NOI18N
        panelGlass7.add(jLabel17);
        jLabel17.setBounds(0, 178, 105, 23);

        TnoSuratDari.setForeground(new java.awt.Color(0, 0, 0));
        TnoSuratDari.setName("TnoSuratDari"); // NOI18N
        TnoSuratDari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnoSuratDariKeyPressed(evt);
            }
        });
        panelGlass7.add(TnoSuratDari);
        TnoSuratDari.setBounds(105, 178, 387, 23);

        TtglSuratDari.setEditable(false);
        TtglSuratDari.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "04-04-2024" }));
        TtglSuratDari.setDisplayFormat("dd-MM-yyyy");
        TtglSuratDari.setName("TtglSuratDari"); // NOI18N
        TtglSuratDari.setOpaque(false);
        panelGlass7.add(TtglSuratDari);
        TtglSuratDari.setBounds(575, 178, 100, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Keperluan : ");
        jLabel18.setName("jLabel18"); // NOI18N
        panelGlass7.add(jLabel18);
        jLabel18.setBounds(0, 206, 105, 23);

        Scroll7.setName("Scroll7"); // NOI18N
        Scroll7.setOpaque(true);

        Tkeperluan.setColumns(20);
        Tkeperluan.setRows(5);
        Tkeperluan.setName("Tkeperluan"); // NOI18N
        Tkeperluan.setPreferredSize(new java.awt.Dimension(170, 200));
        Tkeperluan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkeperluanKeyPressed(evt);
            }
        });
        Scroll7.setViewportView(Tkeperluan);

        panelGlass7.add(Scroll7);
        Scroll7.setBounds(105, 206, 570, 55);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("Hasil Tes Narkoba : ");
        jLabel13.setName("jLabel13"); // NOI18N
        panelGlass7.add(jLabel13);
        jLabel13.setBounds(700, 10, 130, 23);

        ChkTHC.setBackground(new java.awt.Color(255, 255, 250));
        ChkTHC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkTHC.setForeground(new java.awt.Color(0, 0, 0));
        ChkTHC.setText("Tannabinoids (THC)");
        ChkTHC.setBorderPainted(true);
        ChkTHC.setBorderPaintedFlat(true);
        ChkTHC.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkTHC.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkTHC.setName("ChkTHC"); // NOI18N
        ChkTHC.setOpaque(false);
        ChkTHC.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkTHC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkTHCActionPerformed(evt);
            }
        });
        panelGlass7.add(ChkTHC);
        ChkTHC.setBounds(700, 38, 143, 23);

        ChkBZO.setBackground(new java.awt.Color(255, 255, 250));
        ChkBZO.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkBZO.setForeground(new java.awt.Color(0, 0, 0));
        ChkBZO.setText("Benzodi Azepines (BZO)");
        ChkBZO.setBorderPainted(true);
        ChkBZO.setBorderPaintedFlat(true);
        ChkBZO.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkBZO.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkBZO.setName("ChkBZO"); // NOI18N
        ChkBZO.setOpaque(false);
        ChkBZO.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkBZO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkBZOActionPerformed(evt);
            }
        });
        panelGlass7.add(ChkBZO);
        ChkBZO.setBounds(700, 66, 143, 23);

        ChkMET.setBackground(new java.awt.Color(255, 255, 250));
        ChkMET.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkMET.setForeground(new java.awt.Color(0, 0, 0));
        ChkMET.setText("Methamphetamine (MET)");
        ChkMET.setBorderPainted(true);
        ChkMET.setBorderPaintedFlat(true);
        ChkMET.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkMET.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkMET.setName("ChkMET"); // NOI18N
        ChkMET.setOpaque(false);
        ChkMET.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkMET.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkMETActionPerformed(evt);
            }
        });
        panelGlass7.add(ChkMET);
        ChkMET.setBounds(700, 94, 143, 23);

        ChkMOP.setBackground(new java.awt.Color(255, 255, 250));
        ChkMOP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkMOP.setForeground(new java.awt.Color(0, 0, 0));
        ChkMOP.setText("Morphine (MOP)");
        ChkMOP.setBorderPainted(true);
        ChkMOP.setBorderPaintedFlat(true);
        ChkMOP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkMOP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkMOP.setName("ChkMOP"); // NOI18N
        ChkMOP.setOpaque(false);
        ChkMOP.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkMOP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkMOPActionPerformed(evt);
            }
        });
        panelGlass7.add(ChkMOP);
        ChkMOP.setBounds(700, 122, 143, 23);

        ChkCOC.setBackground(new java.awt.Color(255, 255, 250));
        ChkCOC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkCOC.setForeground(new java.awt.Color(0, 0, 0));
        ChkCOC.setText("Cocaine (COC)");
        ChkCOC.setBorderPainted(true);
        ChkCOC.setBorderPaintedFlat(true);
        ChkCOC.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkCOC.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkCOC.setName("ChkCOC"); // NOI18N
        ChkCOC.setOpaque(false);
        ChkCOC.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkCOC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkCOCActionPerformed(evt);
            }
        });
        panelGlass7.add(ChkCOC);
        ChkCOC.setBounds(700, 150, 143, 23);

        ChkAMP.setBackground(new java.awt.Color(255, 255, 250));
        ChkAMP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkAMP.setForeground(new java.awt.Color(0, 0, 0));
        ChkAMP.setText("Amphetamine (AMP)");
        ChkAMP.setBorderPainted(true);
        ChkAMP.setBorderPaintedFlat(true);
        ChkAMP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkAMP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkAMP.setName("ChkAMP"); // NOI18N
        ChkAMP.setOpaque(false);
        ChkAMP.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkAMP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkAMPActionPerformed(evt);
            }
        });
        panelGlass7.add(ChkAMP);
        ChkAMP.setBounds(700, 178, 143, 23);

        cmbTHC.setForeground(new java.awt.Color(0, 0, 0));
        cmbTHC.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Negatif (-)", "Positif (+)" }));
        cmbTHC.setName("cmbTHC"); // NOI18N
        cmbTHC.setPreferredSize(new java.awt.Dimension(55, 28));
        panelGlass7.add(cmbTHC);
        cmbTHC.setBounds(850, 38, 85, 23);

        cmbBZO.setForeground(new java.awt.Color(0, 0, 0));
        cmbBZO.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Negatif (-)", "Positif (+)" }));
        cmbBZO.setName("cmbBZO"); // NOI18N
        cmbBZO.setPreferredSize(new java.awt.Dimension(55, 28));
        panelGlass7.add(cmbBZO);
        cmbBZO.setBounds(850, 66, 85, 23);

        cmbMET.setForeground(new java.awt.Color(0, 0, 0));
        cmbMET.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Negatif (-)", "Positif (+)" }));
        cmbMET.setName("cmbMET"); // NOI18N
        cmbMET.setPreferredSize(new java.awt.Dimension(55, 28));
        panelGlass7.add(cmbMET);
        cmbMET.setBounds(850, 94, 85, 23);

        cmbMOP.setForeground(new java.awt.Color(0, 0, 0));
        cmbMOP.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Negatif (-)", "Positif (+)" }));
        cmbMOP.setName("cmbMOP"); // NOI18N
        cmbMOP.setPreferredSize(new java.awt.Dimension(55, 28));
        panelGlass7.add(cmbMOP);
        cmbMOP.setBounds(850, 122, 85, 23);

        cmbCOC.setForeground(new java.awt.Color(0, 0, 0));
        cmbCOC.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Negatif (-)", "Positif (+)" }));
        cmbCOC.setName("cmbCOC"); // NOI18N
        cmbCOC.setPreferredSize(new java.awt.Dimension(55, 28));
        panelGlass7.add(cmbCOC);
        cmbCOC.setBounds(850, 150, 85, 23);

        cmbAMP.setForeground(new java.awt.Color(0, 0, 0));
        cmbAMP.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Negatif (-)", "Positif (+)" }));
        cmbAMP.setName("cmbAMP"); // NOI18N
        cmbAMP.setPreferredSize(new java.awt.Dimension(55, 28));
        panelGlass7.add(cmbAMP);
        cmbAMP.setBounds(850, 178, 85, 23);

        ChkSemuaTes.setBackground(new java.awt.Color(255, 255, 250));
        ChkSemuaTes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkSemuaTes.setForeground(new java.awt.Color(0, 0, 0));
        ChkSemuaTes.setText("Semua Tes Narkoba");
        ChkSemuaTes.setBorderPainted(true);
        ChkSemuaTes.setBorderPaintedFlat(true);
        ChkSemuaTes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkSemuaTes.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkSemuaTes.setName("ChkSemuaTes"); // NOI18N
        ChkSemuaTes.setOpaque(false);
        ChkSemuaTes.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkSemuaTes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkSemuaTesActionPerformed(evt);
            }
        });
        panelGlass7.add(ChkSemuaTes);
        ChkSemuaTes.setBounds(700, 206, 130, 23);

        cmbSemuaHasilTes.setForeground(new java.awt.Color(0, 0, 0));
        cmbSemuaHasilTes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Negatif (-)", "Positif (+)" }));
        cmbSemuaHasilTes.setName("cmbSemuaHasilTes"); // NOI18N
        cmbSemuaHasilTes.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbSemuaHasilTes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSemuaHasilTesActionPerformed(evt);
            }
        });
        panelGlass7.add(cmbSemuaHasilTes);
        cmbSemuaHasilTes.setBounds(850, 206, 85, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Kesimpulan Hasil Tes : ");
        jLabel20.setName("jLabel20"); // NOI18N
        panelGlass7.add(jLabel20);
        jLabel20.setBounds(700, 234, 150, 23);

        cmbKesHasil.setForeground(new java.awt.Color(0, 0, 0));
        cmbKesHasil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Negatif (-)", "Positif (+)" }));
        cmbKesHasil.setName("cmbKesHasil"); // NOI18N
        cmbKesHasil.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbKesHasil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKesHasilActionPerformed(evt);
            }
        });
        panelGlass7.add(cmbKesHasil);
        cmbKesHasil.setBounds(850, 234, 85, 23);

        ChkTglSurat.setBackground(new java.awt.Color(255, 255, 250));
        ChkTglSurat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkTglSurat.setForeground(new java.awt.Color(0, 0, 0));
        ChkTglSurat.setText("Tgl. Surat : ");
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
        ChkTglSurat.setBounds(494, 178, 80, 23);

        TnoDokumen.setEditable(false);
        TnoDokumen.setForeground(new java.awt.Color(0, 0, 0));
        TnoDokumen.setName("TnoDokumen"); // NOI18N
        panelGlass7.add(TnoDokumen);
        TnoDokumen.setBounds(105, 38, 131, 23);

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
        BtnDokumen.setBounds(237, 38, 28, 23);

        internalFrame1.add(panelGlass7, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        PanelAccor.setBackground(new java.awt.Color(255, 255, 255));
        PanelAccor.setName("PanelAccor"); // NOI18N
        PanelAccor.setPreferredSize(new java.awt.Dimension(750, 43));
        PanelAccor.setLayout(new java.awt.BorderLayout());

        ChkAccor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/2rightarrow.png"))); // NOI18N
        ChkAccor.setToolTipText("Silahkan Klik Untuk Melihat Hasil Pemeriksaan Lab.");
        ChkAccor.setFocusable(false);
        ChkAccor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkAccor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkAccor.setName("ChkAccor"); // NOI18N
        ChkAccor.setPreferredSize(new java.awt.Dimension(22, 20));
        ChkAccor.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/2rightarrow.png"))); // NOI18N
        ChkAccor.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/2leftarrow.png"))); // NOI18N
        ChkAccor.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/2leftarrow.png"))); // NOI18N
        ChkAccor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkAccorActionPerformed(evt);
            }
        });
        PanelAccor.add(ChkAccor, java.awt.BorderLayout.WEST);

        FormMenu.setBackground(new java.awt.Color(250, 250, 245));
        FormMenu.setName("FormMenu"); // NOI18N
        FormMenu.setPreferredSize(new java.awt.Dimension(150, 483));
        FormMenu.setLayout(new java.awt.BorderLayout());

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 300));
        panelGlass10.setLayout(new java.awt.BorderLayout());

        Scroll3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " No. Pemeriksaan LIS (Laboratory Information System) ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);

        tbLIS.setToolTipText("Silahkan klik untuk memilih data yang akan dibaca hasil lab. nya");
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
        Scroll3.setViewportView(tbLIS);

        panelGlass10.add(Scroll3, java.awt.BorderLayout.CENTER);

        panelGlass11.setName("panelGlass11"); // NOI18N
        panelGlass11.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 3, 9));

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Key Word :");
        jLabel12.setName("jLabel12"); // NOI18N
        jLabel12.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass11.add(jLabel12);

        TCari1.setForeground(new java.awt.Color(0, 0, 0));
        TCari1.setName("TCari1"); // NOI18N
        TCari1.setPreferredSize(new java.awt.Dimension(190, 23));
        TCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari1KeyPressed(evt);
            }
        });
        panelGlass11.add(TCari1);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Limit Data :");
        jLabel22.setName("jLabel22"); // NOI18N
        jLabel22.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass11.add(jLabel22);

        cmbHlm.setForeground(new java.awt.Color(0, 0, 0));
        cmbHlm.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10", "20", "30", "40", "50", "60", "70", "80", "90", "100" }));
        cmbHlm.setName("cmbHlm"); // NOI18N
        cmbHlm.setPreferredSize(new java.awt.Dimension(55, 23));
        panelGlass11.add(cmbHlm);

        BtnCari1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari1.setMnemonic('6');
        BtnCari1.setText("Tampilkan Data");
        BtnCari1.setToolTipText("Alt+6");
        BtnCari1.setName("BtnCari1"); // NOI18N
        BtnCari1.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari1ActionPerformed(evt);
            }
        });
        BtnCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCari1KeyPressed(evt);
            }
        });
        panelGlass11.add(BtnCari1);

        panelGlass10.add(panelGlass11, java.awt.BorderLayout.PAGE_END);

        FormMenu.add(panelGlass10, java.awt.BorderLayout.PAGE_START);

        Scroll4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Hasil Pemeriksaan Laboratorium ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        Scroll4.setName("Scroll4"); // NOI18N
        Scroll4.setOpaque(true);

        tbHasil.setToolTipText("Silahkan klik untuk memilih data yang dibaca cpptnya");
        tbHasil.setName("tbHasil"); // NOI18N
        tbHasil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHasilMouseClicked(evt);
            }
        });
        tbHasil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbHasilKeyPressed(evt);
            }
        });
        Scroll4.setViewportView(tbHasil);

        FormMenu.add(Scroll4, java.awt.BorderLayout.CENTER);

        PanelAccor.add(FormMenu, java.awt.BorderLayout.CENTER);

        getContentPane().add(PanelAccor, java.awt.BorderLayout.EAST);

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
            autoNomorSurat();
            cekData();
            Sequel.menyimpan("surat_keterangan_napza", "'" + TNoRW.getText() + "','" + TNoSurat.getText() + "',"
                    + "'" + Valid.SetTgl(Ttgl_surat.getSelectedItem() + "") + "','" + TPekerjaan.getText() + "','" + Ttmpt_tinggal.getText() + "',"
                    + "'" + TPermintaanDari.getText() + "','" + TnoSuratDari.getText() + "','" + Valid.SetTgl(TtglSuratDari.getSelectedItem() + "") + "',"
                    + "'" + thc + "','" + bzo + "','" + met + "','" + mop + "','" + coc + "','" + amp + "',"
                    + "'" + cmbTHC.getSelectedItem().toString() + "','" + cmbBZO.getSelectedItem().toString() + "','" + cmbMET.getSelectedItem().toString() + "',"
                    + "'" + cmbMOP.getSelectedItem().toString() + "','" + cmbCOC.getSelectedItem().toString() + "','" + cmbAMP.getSelectedItem().toString() + "',"
                    + "'" + cmbKesHasil.getSelectedItem().toString() + "','" + Tkeperluan.getText() + "','" + kddokter + "',"
                    + "'" + tglsurat + "','" + TPasien.getText() + "','" + TTempLahr.getText() + "','" + TnoDokumen.getText() + "'", "Surat Keterangan NAPZA");
            
            TCari.setText(TNoRW.getText());            
            tbSurat.requestFocus();
            emptTeks();
            tampil();
            ChkAccor.setSelected(false);
            isMenu();
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
        ChkAccor.setSelected(false);
        isMenu();
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
        } else if (Sequel.cariInteger("select count(-1) from surat_keterangan_napza where no_rawat='" + TNoRW.getText() + "'") == 0) {
            JOptionPane.showMessageDialog(null, "Data surat keterangan NAPZA, pasien yg. bernama " + TPasien.getText() + "    \n"
                    + "dg. no. rawat " + TNoRW.getText() + " belum ada tersimpan...!!!!");
        } else if (TnoDokumen.getText().equals("")) {
            Valid.textKosong(TnoDokumen, "Nomor Dokumen");
            BtnDokumen.requestFocus();
        } else if (sttsnomor.equals("Non Aktif")) {
            JOptionPane.showMessageDialog(null, "Nomor dokumen yang dipilih statusnya " + sttsnomor + ", silahkan ulangi lagi...!!");
            BtnDokumen.requestFocus();
        } else {
            cekData();
            if (tbSurat.getSelectedRow() > -1) {
                Sequel.mengedit("surat_keterangan_napza", "no_rawat=?",
                        "tgl_surat=?,pekerjaan=?,tempat_tinggal=?,permintaan_dari=?,no_surat_dari=?,tgl_surat_dari=?,thc=?,"
                        + "bzo=?,met=?,mop=?,coc=?,amp=?,hasil_thc=?,hasil_bzo=?,hasil_met=?,hasil_mop=?,hasil_coc=?,hasil_amp=?,"
                        + "kesimpulan_hasil=?,keperluan=?,nip_dokter=?,ada_tgl_nosurat=?,nm_pasien=?,tmpt_lahir=?, no_dokumen=?", 26, new String[]{
                            Valid.SetTgl(Ttgl_surat.getSelectedItem() + ""), TPekerjaan.getText(), Ttmpt_tinggal.getText(), TPermintaanDari.getText(),
                            TnoSuratDari.getText(), Valid.SetTgl(TtglSuratDari.getSelectedItem() + ""), thc, bzo, met, mop, coc, amp,
                            cmbTHC.getSelectedItem().toString(), cmbBZO.getSelectedItem().toString(), cmbMET.getSelectedItem().toString(),
                            cmbMOP.getSelectedItem().toString(), cmbCOC.getSelectedItem().toString(), cmbAMP.getSelectedItem().toString(),
                            cmbKesHasil.getSelectedItem().toString(), Tkeperluan.getText(), kddokter, tglsurat, TPasien.getText(), TTempLahr.getText(), TnoDokumen.getText(),
                            tbSurat.getValueAt(tbSurat.getSelectedRow(), 0).toString()
                });
                if (tabMode.getRowCount() != 0) {
                    TCari.setText(TNoRW.getText());
                    tbSurat.requestFocus();
                    emptTeks();
                    tampil();
                    ChkAccor.setSelected(false);
                    isMenu();
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
        ChkAccor.setSelected(false);
        isMenu();
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
        ChkAccor.setSelected(false);
        isMenu();
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
        akses.setform("DlgSuratKeteranganNapza");
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
                if (Sequel.queryu2tf("delete from surat_keterangan_napza where no_rawat=?", 1, new String[]{
                    tbSurat.getValueAt(tbSurat.getSelectedRow(), 0).toString()
                }) == true) {
                    TCari.setText("");
                    tampil();
                    emptTeks();
                    ChkAccor.setSelected(false);
                    isMenu();
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

    private void TPekerjaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPekerjaanKeyPressed
        Valid.pindah(evt, TTempLahr, Ttmpt_tinggal);
    }//GEN-LAST:event_TPekerjaanKeyPressed

    private void Ttmpt_tinggalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Ttmpt_tinggalKeyPressed
        Valid.pindah(evt, TPekerjaan, TPermintaanDari);
    }//GEN-LAST:event_Ttmpt_tinggalKeyPressed

    private void MnCetakSuratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakSuratActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu datanya pada tabel...!!!!");
            tbSurat.requestFocus();
        } else if (Sequel.cariInteger("select count(-1) from surat_keterangan_napza where no_rawat='" + TNoRW.getText() + "'") == 0) {
            JOptionPane.showMessageDialog(null, "Data surat keterangan NAPZA pasien yg. bernama " + TPasien.getText() + "    \n"
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
                
                param.put("nosurat", "NOMOR : " + TnoDokumen.getText() + " / " + TNoSurat.getText());
                param.put("nama", TPasien.getText());
                param.put("ttl", TTempLahr.getText() + ", " + TtglLahir.getText());
                param.put("jk", Tjk.getText());
                param.put("pekerjaan", TPekerjaan.getText());
                param.put("tempattinggal", Ttmpt_tinggal.getText());
                param.put("permintaandari", TPermintaanDari.getText());
                param.put("kesimpulan", cmbKesHasil.getSelectedItem().toString());
                param.put("keperluan", Tkeperluan.getText());
                param.put("tglsurat", Ttgl_surat.getSelectedItem().toString().substring(0, 2) + " "
                        + Sequel.bulanINDONESIA("select date_format(tgl_surat,'%m') from surat_keterangan_napza where "
                                + "no_rawat='" + TNoRW.getText() + "'") + " " + Ttgl_surat.getSelectedItem().toString().substring(6, 10));
                param.put("nmdokter", Tnmdokter.getText());
                param.put("nipdokter", kddokter);
                
                if (tglsurat.equals("ya")) {
                    param.put("nosurattgl", TnoSuratDari.getText() + " tanggal "
                            + TtglSuratDari.getSelectedItem().toString().substring(0, 2) + " "
                            + Sequel.bulanINDONESIA("select date_format(tgl_surat_dari,'%m') from surat_keterangan_napza where "
                                    + "no_rawat='" + TNoRW.getText() + "'") + " " + TtglSuratDari.getSelectedItem().toString().substring(6, 10));
                } else {
                    param.put("nosurattgl", TnoSuratDari.getText());
                }

                if (Sequel.cariInteger("SELECT count(-1) from surat_keterangan_napza where no_rawat='" + TNoRW.getText() + "' and thc='ya' and bzo='ya' "
                        + "and met='ya' and mop='ya' and coc='ya' and amp='ya'") == 0) {
                    Valid.MyReport("rptSuratNarkoba.jasper", "report", "::[ Surat Keterangan Narkoba ]::",
                            "SELECT IF(thc='ya','Tannabinoids (THC)','') 'tes', hasil_thc 'hasil' FROM surat_keterangan_napza WHERE no_rawat = '" + TNoRW.getText() + "' AND thc='ya' UNION ALL "
                            + "SELECT IF(bzo='ya','Benzodi Azepines (BZO)',''), hasil_bzo FROM surat_keterangan_napza WHERE no_rawat = '" + TNoRW.getText() + "' AND bzo='ya' UNION ALL "
                            + "SELECT IF(met='ya','Methamphetamine (MET)',''), hasil_met FROM surat_keterangan_napza WHERE no_rawat = '" + TNoRW.getText() + "' AND met='ya' UNION ALL "
                            + "SELECT IF(mop='ya','Morphine (MOP)',''), hasil_mop FROM surat_keterangan_napza WHERE no_rawat = '" + TNoRW.getText() + "' AND mop='ya' UNION ALL "
                            + "SELECT IF(coc='ya','Cocaine (COC)',''), hasil_coc FROM surat_keterangan_napza WHERE no_rawat = '" + TNoRW.getText() + "' AND coc='ya' UNION ALL "
                            + "SELECT IF(amp='ya','Amphetamine (AMP)',''), concat(hasil_amp,' *)') hasil_amp FROM surat_keterangan_napza WHERE no_rawat = '" + TNoRW.getText() + "' AND amp='ya'", param);
                } else {
                    Valid.MyReport("rptSuratNapza.jasper", "report", "::[ Surat Keterangan NAPZA ]::",
                            "SELECT IF(thc='ya','Tannabinoids (THC)','') 'tes', hasil_thc 'hasil' FROM surat_keterangan_napza WHERE no_rawat = '" + TNoRW.getText() + "' AND thc='ya' UNION ALL "
                            + "SELECT IF(bzo='ya','Benzodi Azepines (BZO)',''), hasil_bzo FROM surat_keterangan_napza WHERE no_rawat = '" + TNoRW.getText() + "' AND bzo='ya' UNION ALL "
                            + "SELECT IF(met='ya','Methamphetamine (MET)',''), hasil_met FROM surat_keterangan_napza WHERE no_rawat = '" + TNoRW.getText() + "' AND met='ya' UNION ALL "
                            + "SELECT IF(mop='ya','Morphine (MOP)',''), hasil_mop FROM surat_keterangan_napza WHERE no_rawat = '" + TNoRW.getText() + "' AND mop='ya' UNION ALL "
                            + "SELECT IF(coc='ya','Cocaine (COC)',''), hasil_coc FROM surat_keterangan_napza WHERE no_rawat = '" + TNoRW.getText() + "' AND coc='ya' UNION ALL "
                            + "SELECT IF(amp='ya','Amphetamine (AMP)',''), concat(hasil_amp,' *)') hasil_amp FROM surat_keterangan_napza WHERE no_rawat = '" + TNoRW.getText() + "' AND amp='ya'", param);
                }
            }

            TCari.setText(TNoRW.getText());
            tbSurat.requestFocus();
            emptTeks();
            tampil();
        }
    }//GEN-LAST:event_MnCetakSuratActionPerformed

    private void TkeperluanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkeperluanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            BtnDokter.requestFocus();
        }
    }//GEN-LAST:event_TkeperluanKeyPressed

    private void TPermintaanDariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPermintaanDariKeyPressed
        Valid.pindah(evt, Ttmpt_tinggal, TnoSuratDari);
    }//GEN-LAST:event_TPermintaanDariKeyPressed

    private void ChkTHCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkTHCActionPerformed
        if (ChkTHC.isSelected() == true) {
            cmbTHC.setEnabled(true);
            cmbTHC.setSelectedIndex(0);
            cmbTHC.requestFocus();
        } else {
            cmbTHC.setEnabled(false);
            cmbTHC.setSelectedIndex(0);
        }
    }//GEN-LAST:event_ChkTHCActionPerformed

    private void ChkBZOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkBZOActionPerformed
        if (ChkBZO.isSelected() == true) {
            cmbBZO.setEnabled(true);
            cmbBZO.setSelectedIndex(0);
            cmbBZO.requestFocus();
        } else {
            cmbBZO.setEnabled(false);
            cmbBZO.setSelectedIndex(0);
        }
    }//GEN-LAST:event_ChkBZOActionPerformed

    private void ChkMETActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkMETActionPerformed
        if (ChkMET.isSelected() == true) {
            cmbMET.setEnabled(true);
            cmbMET.setSelectedIndex(0);
            cmbMET.requestFocus();
        } else {
            cmbMET.setEnabled(false);
            cmbMET.setSelectedIndex(0);
        }
    }//GEN-LAST:event_ChkMETActionPerformed

    private void ChkMOPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkMOPActionPerformed
        if (ChkMOP.isSelected() == true) {
            cmbMOP.setEnabled(true);
            cmbMOP.setSelectedIndex(0);
            cmbMOP.requestFocus();
        } else {
            cmbMOP.setEnabled(false);
            cmbMOP.setSelectedIndex(0);
        }
    }//GEN-LAST:event_ChkMOPActionPerformed

    private void ChkCOCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkCOCActionPerformed
        if (ChkCOC.isSelected() == true) {
            cmbCOC.setEnabled(true);
            cmbCOC.setSelectedIndex(0);
            cmbCOC.requestFocus();
        } else {
            cmbCOC.setEnabled(false);
            cmbCOC.setSelectedIndex(0);
        }
    }//GEN-LAST:event_ChkCOCActionPerformed

    private void ChkAMPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkAMPActionPerformed
        if (ChkAMP.isSelected() == true) {
            cmbAMP.setEnabled(true);
            cmbAMP.setSelectedIndex(0);
            cmbAMP.requestFocus();
        } else {
            cmbAMP.setEnabled(false);
            cmbAMP.setSelectedIndex(0);
        }
    }//GEN-LAST:event_ChkAMPActionPerformed

    private void ChkSemuaTesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkSemuaTesActionPerformed
        if (ChkSemuaTes.isSelected() == true) {
            ChkTHC.setSelected(true);
            ChkBZO.setSelected(true);
            ChkMET.setSelected(true);
            ChkMOP.setSelected(true);
            ChkCOC.setSelected(true);
            ChkAMP.setSelected(true);
            
            cmbSemuaHasilTes.setEnabled(true);
            cmbTHC.setEnabled(true);
            cmbBZO.setEnabled(true);
            cmbMET.setEnabled(true);
            cmbMOP.setEnabled(true);
            cmbCOC.setEnabled(true);
            cmbAMP.setEnabled(true);
            
            cmbSemuaHasilTes.setSelectedIndex(0);
            cmbTHC.setSelectedIndex(0);
            cmbBZO.setSelectedIndex(0);
            cmbMET.setSelectedIndex(0);
            cmbMOP.setSelectedIndex(0);
            cmbCOC.setSelectedIndex(0);
            cmbAMP.setSelectedIndex(0);
        } else {
            ChkTHC.setSelected(false);
            ChkBZO.setSelected(false);
            ChkMET.setSelected(false);
            ChkMOP.setSelected(false);
            ChkCOC.setSelected(false);
            ChkAMP.setSelected(false);

            cmbSemuaHasilTes.setEnabled(false);
            cmbTHC.setEnabled(false);
            cmbBZO.setEnabled(false);
            cmbMET.setEnabled(false);
            cmbMOP.setEnabled(false);
            cmbCOC.setEnabled(false);
            cmbAMP.setEnabled(false);

            cmbSemuaHasilTes.setSelectedIndex(0);
            cmbTHC.setSelectedIndex(0);
            cmbBZO.setSelectedIndex(0);
            cmbMET.setSelectedIndex(0);
            cmbMOP.setSelectedIndex(0);
            cmbCOC.setSelectedIndex(0);
            cmbAMP.setSelectedIndex(0);
        }
    }//GEN-LAST:event_ChkSemuaTesActionPerformed

    private void cmbSemuaHasilTesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSemuaHasilTesActionPerformed
        if (cmbSemuaHasilTes.getSelectedIndex() == 1) {
            if (ChkTHC.isSelected() == true && ChkBZO.isSelected() == true && ChkMET.isSelected() == true
                    && ChkMOP.isSelected() == true && ChkCOC.isSelected() == true && ChkAMP.isSelected() == true) {
                cmbTHC.setEnabled(true);
                cmbBZO.setEnabled(true);
                cmbMET.setEnabled(true);
                cmbMOP.setEnabled(true);
                cmbCOC.setEnabled(true);
                cmbAMP.setEnabled(true);

                cmbTHC.setSelectedIndex(1);
                cmbBZO.setSelectedIndex(1);
                cmbMET.setSelectedIndex(1);
                cmbMOP.setSelectedIndex(1);
                cmbCOC.setSelectedIndex(1);
                cmbAMP.setSelectedIndex(1);
            } else {
                JOptionPane.showMessageDialog(null, "Semua tes narkoba harus terconteng dulu...!!!!");
                cmbSemuaHasilTes.setSelectedIndex(0);
            }
        } else if (cmbSemuaHasilTes.getSelectedIndex() == 2) {
            if (ChkTHC.isSelected() == true && ChkBZO.isSelected() == true && ChkMET.isSelected() == true
                    && ChkMOP.isSelected() == true && ChkCOC.isSelected() == true && ChkAMP.isSelected() == true) {
                cmbTHC.setEnabled(true);
                cmbBZO.setEnabled(true);
                cmbMET.setEnabled(true);
                cmbMOP.setEnabled(true);
                cmbCOC.setEnabled(true);
                cmbAMP.setEnabled(true);

                cmbTHC.setSelectedIndex(2);
                cmbBZO.setSelectedIndex(2);
                cmbMET.setSelectedIndex(2);
                cmbMOP.setSelectedIndex(2);
                cmbCOC.setSelectedIndex(2);
                cmbAMP.setSelectedIndex(2);
            } else {
                JOptionPane.showMessageDialog(null, "Semua tes narkoba harus terconteng dulu...!!!!");
                cmbSemuaHasilTes.setSelectedIndex(0);
            }
        }
    }//GEN-LAST:event_cmbSemuaHasilTesActionPerformed

    private void cmbKesHasilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKesHasilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbKesHasilActionPerformed

    private void ChkTglSuratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkTglSuratActionPerformed
        if (ChkTglSurat.isSelected() == true) {
            TtglSuratDari.setEnabled(true);
            TtglSuratDari.requestFocus();
        } else {
            TtglSuratDari.setEnabled(false);
        }
    }//GEN-LAST:event_ChkTglSuratActionPerformed

    private void TnoSuratDariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnoSuratDariKeyPressed
        Valid.pindah(evt, TPermintaanDari, Tkeperluan);
    }//GEN-LAST:event_TnoSuratDariKeyPressed

    private void TPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPasienKeyPressed
        Valid.pindah(evt, TPasien, TTempLahr);
    }//GEN-LAST:event_TPasienKeyPressed

    private void TTempLahrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTempLahrKeyPressed
        Valid.pindah(evt, TPasien, TPekerjaan);
    }//GEN-LAST:event_TTempLahrKeyPressed

    private void ChkAccorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkAccorActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu data pasiennya pada tabel...!!!!");            
        } else {
            isMenu();
        }
    }//GEN-LAST:event_ChkAccorActionPerformed

    private void tbLISMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbLISMouseClicked
        if (tabMode1.getRowCount() != 0) {
            try {
                getDataLab();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbLISMouseClicked

    private void tbLISKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbLISKeyPressed
        if (tabMode1.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataLab();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbLISKeyPressed

    private void tbHasilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHasilMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbHasilMouseClicked

    private void tbHasilKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbHasilKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbHasilKeyPressed

    private void TCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari1ActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari1.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        }
    }//GEN-LAST:event_TCari1KeyPressed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        Valid.tabelKosong(tabMode2);
        tampilLIS();
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void BtnCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        }
    }//GEN-LAST:event_BtnCari1KeyPressed

    private void BtnDokumenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokumenActionPerformed
        akses.setform("DlgSuratKeteranganNapza");
        dokumen.isCek();
        dokumen.ChkInput.setSelected(false);
        dokumen.isForm();
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
            DlgSuratKeteranganNapza dialog = new DlgSuratKeteranganNapza(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCari1;
    private widget.Button BtnDokter;
    private widget.Button BtnDokumen;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnSimpan;
    public widget.CekBox ChkAMP;
    public widget.CekBox ChkAccor;
    public widget.CekBox ChkBZO;
    public widget.CekBox ChkCOC;
    public widget.CekBox ChkMET;
    public widget.CekBox ChkMOP;
    public widget.CekBox ChkSemuaTes;
    public widget.CekBox ChkTHC;
    public widget.CekBox ChkTglSurat;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormMenu;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnCetakSurat;
    private widget.PanelBiasa PanelAccor;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll4;
    private widget.ScrollPane Scroll7;
    public widget.TextBox TCari;
    private widget.TextBox TCari1;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRW;
    private widget.TextBox TNoSurat;
    private widget.TextBox TPasien;
    private widget.TextBox TPekerjaan;
    private widget.TextBox TPermintaanDari;
    private widget.TextBox TTempLahr;
    private widget.TextBox Tjk;
    private widget.TextArea Tkeperluan;
    private widget.TextBox Tnmdokter;
    private widget.TextBox TnoDokumen;
    private widget.TextBox TnoSuratDari;
    private widget.TextBox TtglLahir;
    private widget.Tanggal TtglSuratDari;
    private widget.Tanggal Ttgl_surat;
    private widget.TextBox Ttmpt_tinggal;
    private widget.ComboBox cmbAMP;
    private widget.ComboBox cmbBZO;
    private widget.ComboBox cmbCOC;
    private widget.ComboBox cmbHlm;
    private widget.ComboBox cmbKesHasil;
    private widget.ComboBox cmbMET;
    private widget.ComboBox cmbMOP;
    private widget.ComboBox cmbSemuaHasilTes;
    private widget.ComboBox cmbTHC;
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
    private widget.Label jLabel3;
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass7;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.Table tbHasil;
    private widget.Table tbLIS;
    private widget.Table tbSurat;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT rp.no_rawat, sk.no_surat, p.no_rkm_medis, sk.nm_pasien, IF(p.jk='L','Laki-laki','Perempuan') jk, sk.tmpt_lahir, "
                    + "DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y') tgllahir, sk.pekerjaan, sk.tempat_tinggal, sk.permintaan_dari, sk.no_surat_dari, sk.ada_tgl_nosurat, "
                    + "if(sk.ada_tgl_nosurat='ya',DATE_FORMAT(sk.tgl_surat_dari,'%d-%m-%Y'),'-') tglsuratdari, sk.keperluan, sk.nip_dokter, pg.nama nmdokter, "
                    + "sk.thc, sk.bzo, sk.met, sk.mop, sk.coc, sk.amp, sk.hasil_thc, sk.hasil_bzo, sk.hasil_met, sk.hasil_mop, sk.hasil_coc, sk.hasil_amp, "
                    + "sk.kesimpulan_hasil, DATE_FORMAT(sk.tgl_surat,'%d-%m-%Y') tglsurat, sk.no_surat, sk.tgl_surat, sk.tgl_surat_dari, sk.no_dokumen FROM reg_periksa rp "
                    + "INNER JOIN surat_keterangan_napza sk ON sk.no_rawat = rp.no_rawat INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                    + "INNER JOIN kelurahan kl ON kl.kd_kel = p.kd_kel_domisili_pasien INNER JOIN kecamatan kc ON kc.kd_kec = p.kd_kec_domisili_pasien "
                    + "INNER JOIN kabupaten kb ON kb.kd_kab = p.kd_kab_domisili_pasien INNER JOIN pegawai pg ON pg.nik = sk.nip_dokter WHERE "
                    + "sk.tgl_surat BETWEEN ? AND ? AND rp.no_rawat LIKE ? OR "
                    + "sk.tgl_surat BETWEEN ? AND ? AND sk.no_surat LIKE ? OR "
                    + "sk.tgl_surat BETWEEN ? AND ? AND p.no_rkm_medis LIKE ? OR "
                    + "sk.tgl_surat BETWEEN ? AND ? AND sk.nm_pasien LIKE ? OR "
                    + "sk.tgl_surat BETWEEN ? AND ? AND sk.pekerjaan LIKE ? OR "
                    + "sk.tgl_surat BETWEEN ? AND ? AND IF(p.jk='L','Laki-laki','Perempuan') LIKE ? OR "
                    + "sk.tgl_surat BETWEEN ? AND ? AND sk.tempat_tinggal LIKE ? OR "
                    + "sk.tgl_surat BETWEEN ? AND ? AND pg.nama LIKE ? OR "
                    + "sk.tgl_surat BETWEEN ? AND ? AND sk.permintaan_dari LIKE ? OR "
                    + "sk.tgl_surat BETWEEN ? AND ? AND sk.no_surat_dari LIKE ? OR "
                    + "sk.tgl_surat BETWEEN ? AND ? AND sk.keperluan LIKE ? OR "
                    + "sk.tgl_surat BETWEEN ? AND ? AND sk.kesimpulan_hasil LIKE ? OR "
                    + "sk.tgl_surat BETWEEN ? AND ? AND CONCAT('KP.12.09 / ',sk.no_surat) LIKE ? ORDER BY sk.tgl_surat DESC, sk.no_surat DESC");
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
                ps.setString(31, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(32, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(33, "%" + TCari.getText().trim() + "%"); 
                ps.setString(34, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(35, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(36, "%" + TCari.getText().trim() + "%"); 
                ps.setString(37, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(38, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(39, "%" + TCari.getText().trim() + "%"); 
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),
                        rs.getString("no_surat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("jk"),
                        rs.getString("tmpt_lahir"),
                        rs.getString("tgllahir"),
                        rs.getString("pekerjaan"),
                        rs.getString("tempat_tinggal"),
                        rs.getString("permintaan_dari"),
                        rs.getString("no_surat_dari"),
                        rs.getString("ada_tgl_nosurat"),
                        rs.getString("tglsuratdari"),
                        rs.getString("keperluan"),
                        rs.getString("nip_dokter"),
                        rs.getString("nmdokter"),
                        rs.getString("thc"),
                        rs.getString("bzo"),
                        rs.getString("met"),
                        rs.getString("mop"),
                        rs.getString("coc"),
                        rs.getString("amp"),
                        rs.getString("hasil_thc"),
                        rs.getString("hasil_bzo"),
                        rs.getString("hasil_met"),
                        rs.getString("hasil_mop"),
                        rs.getString("hasil_coc"),
                        rs.getString("hasil_amp"),
                        rs.getString("kesimpulan_hasil"),
                        rs.getString("tglsurat"),
                        rs.getString("no_surat"),
                        rs.getString("tgl_surat"),
                        rs.getString("tgl_surat_dari"),
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
        TTempLahr.setText("");
        TtglLahir.setText("");
        Tjk.setText("");
        TPekerjaan.setText("");
        Ttmpt_tinggal.setText("");
        TPermintaanDari.setText("");
        TnoSuratDari.setText("");
        TtglSuratDari.setDate(new Date());
        Tkeperluan.setText("MELENGKAPI PERSYARATAN ");
        kddokter = "";
        Tnmdokter.setText("");
        TnoDokumen.setText("");
        sttsnomor = "";
        
        ChkTHC.setSelected(false);
        ChkBZO.setSelected(false);
        ChkMET.setSelected(false);
        ChkMOP.setSelected(false);
        ChkCOC.setSelected(false);
        ChkAMP.setSelected(false);
        ChkSemuaTes.setSelected(false);
        ChkTglSurat.setSelected(false);
        
        cmbTHC.setEnabled(false);
        cmbBZO.setEnabled(false);
        cmbMET.setEnabled(false);
        cmbMOP.setEnabled(false);
        cmbCOC.setEnabled(false);
        cmbAMP.setEnabled(false);
        cmbSemuaHasilTes.setEnabled(false);
        TtglSuratDari.setEnabled(false);
        
        cmbTHC.setSelectedIndex(0);
        cmbBZO.setSelectedIndex(0);
        cmbMET.setSelectedIndex(0);
        cmbMOP.setSelectedIndex(0);
        cmbCOC.setSelectedIndex(0);
        cmbAMP.setSelectedIndex(0);
        cmbSemuaHasilTes.setSelectedIndex(0);
        cmbKesHasil.setSelectedIndex(0);        
        DTPCari1.setDate(Ttgl_surat.getDate());
        DTPCari2.setDate(new Date());
        Ttgl_surat.setDate(new Date());
        autoNomorSurat();
    }

    private void getData() {
        nosurat = "";
        thn = "";
        thc = "";
        bzo = "";
        met = "";
        mop = "";
        coc = "";
        amp = "";
        tglsurat = "";
        kddokter = "";

        if (tbSurat.getSelectedRow() != -1) {
            TNoRW.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 0).toString());
            TNoSurat.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 1).toString());
            TNoRM.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 2).toString());
            TPasien.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 3).toString());
            Tjk.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 4).toString());
            TTempLahr.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 5).toString());
            TtglLahir.setText(Sequel.cariIsi("select date_format(tgl_lahir,'%d') from pasien where no_rkm_medis='" + TNoRM.getText() + "'") + " "
                    + Sequel.bulanINDONESIA("select month(tgl_lahir) from pasien where no_rkm_medis='" + TNoRM.getText() + "'") + " "
                    + Sequel.cariIsi("select year(tgl_lahir) from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
            TPekerjaan.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 7).toString());
            Ttmpt_tinggal.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 8).toString());
            TPermintaanDari.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 9).toString());
            TnoSuratDari.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 10).toString());
            tglsurat = tbSurat.getValueAt(tbSurat.getSelectedRow(), 11).toString();
            Valid.SetTgl(TtglSuratDari, tbSurat.getValueAt(tbSurat.getSelectedRow(), 32).toString());
            Tkeperluan.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 13).toString());            
            kddokter = tbSurat.getValueAt(tbSurat.getSelectedRow(), 14).toString();
            Tnmdokter.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 15).toString());
            
            thc = tbSurat.getValueAt(tbSurat.getSelectedRow(), 16).toString();
            bzo = tbSurat.getValueAt(tbSurat.getSelectedRow(), 17).toString();
            met = tbSurat.getValueAt(tbSurat.getSelectedRow(), 18).toString();
            mop = tbSurat.getValueAt(tbSurat.getSelectedRow(), 19).toString();
            coc = tbSurat.getValueAt(tbSurat.getSelectedRow(), 20).toString();
            amp = tbSurat.getValueAt(tbSurat.getSelectedRow(), 21).toString();
            
            cmbTHC.setSelectedItem(tbSurat.getValueAt(tbSurat.getSelectedRow(), 22).toString());
            cmbBZO.setSelectedItem(tbSurat.getValueAt(tbSurat.getSelectedRow(), 23).toString());
            cmbMET.setSelectedItem(tbSurat.getValueAt(tbSurat.getSelectedRow(), 24).toString());
            cmbMOP.setSelectedItem(tbSurat.getValueAt(tbSurat.getSelectedRow(), 25).toString());
            cmbCOC.setSelectedItem(tbSurat.getValueAt(tbSurat.getSelectedRow(), 26).toString());
            cmbAMP.setSelectedItem(tbSurat.getValueAt(tbSurat.getSelectedRow(), 27).toString());
            cmbKesHasil.setSelectedItem(tbSurat.getValueAt(tbSurat.getSelectedRow(), 28).toString());
            nosurat = tbSurat.getValueAt(tbSurat.getSelectedRow(), 30).toString();
            Valid.SetTgl(Ttgl_surat, tbSurat.getValueAt(tbSurat.getSelectedRow(), 31).toString());
            TnoDokumen.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 33).toString());
            dataCek();
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
            ps1 = koneksi.prepareStatement("SELECT rp.no_rawat, p.no_rkm_medis, p.nm_pasien, p.tmp_lahir, if(p.jk='L','Laki-laki','Perempuan') jk, p.pekerjaan, "
                    + "concat(p.alamat,', Kel./Desa ',kl.nm_kel,', Kec. ',kc.nm_kec,', Kab./Kodya ',kb.nm_kab) alamat, rp.kd_dokter, d.nm_dokter, "
                    + "date_format(p.tgl_lahir,'%d') hari, year(p.tgl_lahir) thn_lahir FROM reg_periksa rp INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                    + "INNER JOIN kelurahan kl ON kl.kd_kel = p.kd_kel_domisili_pasien INNER JOIN kecamatan kc ON kc.kd_kec = p.kd_kec_domisili_pasien "
                    + "INNER JOIN kabupaten kb ON kb.kd_kab = p.kd_kab_domisili_pasien inner join dokter d on d.kd_dokter=rp.kd_dokter "
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
                    Ttmpt_tinggal.setText(rs1.getString("alamat"));                    
                    kddokter = rs1.getString("kd_dokter");
                    Tnmdokter.setText(rs1.getString("nm_dokter"));
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
        thn = Sequel.cariIsi("select date_format(tgl_surat,'%Y') from surat_keterangan_napza order by tgl_surat desc limit 1");
        Valid.autoNomer6("select ifnull(MAX(CONVERT(LEFT(no_surat,4),signed)),0) from surat_keterangan_napza where "
                + "year(tgl_surat) ='" + thn + "'", " / RAZA", 4, TNoSurat);
    }
    
    private void cekData() {
        if (ChkTHC.isSelected() == true) {
            thc = "ya";
        } else {
            thc = "tidak";
        }
        
        if (ChkBZO.isSelected() == true) {
            bzo = "ya";
        } else {
            bzo = "tidak";
        }
        
        if (ChkMET.isSelected() == true) {
            met = "ya";
        } else {
            met = "tidak";
        }
        
        if (ChkMOP.isSelected() == true) {
            mop = "ya";
        } else {
            mop = "tidak";
        }
        
        if (ChkCOC.isSelected() == true) {
            coc = "ya";
        } else {
            coc = "tidak";
        }
        
        if (ChkAMP.isSelected() == true) {
            amp = "ya";
        } else {
            amp = "tidak";
        }
        
        if (ChkTglSurat.isSelected() == true) {
            tglsurat = "ya";
        } else {
            tglsurat = "tidak";
        }
    }
    
    private void dataCek() {
        if (thc.equals("ya")) {
            ChkTHC.setSelected(true);
            cmbTHC.setEnabled(true);
        } else {
            ChkTHC.setSelected(false);
            cmbTHC.setEnabled(false);
        }
        
        if (bzo.equals("ya")) {
            ChkBZO.setSelected(true);
            cmbBZO.setEnabled(true);
        } else {
            ChkBZO.setSelected(false);
            cmbBZO.setEnabled(false);
        }
        
        if (met.equals("ya")) {
            ChkMET.setSelected(true);
            cmbMET.setEnabled(true);
        } else {
            ChkMET.setSelected(false);
            cmbMET.setEnabled(false);
        }
        
        if (mop.equals("ya")) {
            ChkMOP.setSelected(true);
            cmbMOP.setEnabled(true);
        } else {
            ChkMOP.setSelected(false);
            cmbMOP.setEnabled(false);
        }
        
        if (coc.equals("ya")) {
            ChkCOC.setSelected(true);
            cmbCOC.setEnabled(true);
        } else {
            ChkCOC.setSelected(false);
            cmbCOC.setEnabled(false);
        }
        
        if (amp.equals("ya")) {
            ChkAMP.setSelected(true);
            cmbAMP.setEnabled(true);
        } else {
            ChkAMP.setSelected(false);
            cmbAMP.setEnabled(false);
        }
        
        if (tglsurat.equals("ya")) {
            ChkTglSurat.setSelected(true);
            TtglSuratDari.setEnabled(true);
        } else {
            ChkTglSurat.setSelected(false);
            TtglSuratDari.setEnabled(false);
        }
    }
    
    private void tampilLIS() {
        Valid.tabelKosong(tabMode1);
        try {
            psNoLis = koneksi.prepareStatement("SELECT CONCAT(p.no_rkm_medis,' - ',p.nm_pasien) pasien, lr.no_lab nolis, "
                    + "IF(lh.no_lab IS NULL,'Petugas Lab. belum mengirim hasil','Hasil Lab. bisa dicetak') hasil_lab, "
                    + "IFNULL(lh.no_lab,'') cekOK, pl.no_rawat, IFNULL(DATE_FORMAT(lh.waktu_reg_lab,'%d-%m-%Y'),DATE_FORMAT(pl.tgl_periksa,'%d-%m-%Y')) tgl, "
                    + "IFNULL(DATE_FORMAT(lh.waktu_reg_lab,'%H:%i:%s'),DATE_FORMAT(pl.jam,'%H:%i:%s')) jam, "
                    + "IFNULL(lh.dokter_pengirim,'') dokter_pengirim, if(rp.status_lanjut='Ralan','R. Jalan','R. Inap') stts_lnjut FROM lis_reg lr "
                    + "LEFT JOIN reg_periksa rp ON rp.no_rawat=lr.no_rawat LEFT JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis "
                    + "LEFT JOIN periksa_lab pl ON pl.no_rawat=lr.no_rawat LEFT JOIN lis_hasil_data_pasien lh ON lh.no_lab=lr.no_lab WHERE "
                    + "rp.no_rkm_medis like ? and lr.no_lab like ? or "
                    + "rp.no_rkm_medis like ? and IF(lh.no_lab IS NULL,'Petugas Lab. belum mengirim hasil','Hasil Lab. bisa dicetak') like ? or "
                    + "rp.no_rkm_medis like ? and pl.no_rawat like ? or "
                    + "rp.no_rkm_medis like ? and IFNULL(DATE_FORMAT(lh.waktu_reg_lab,'%d-%m-%Y'),DATE_FORMAT(pl.tgl_periksa,'%d-%m-%Y')) like ? or "
                    + "rp.no_rkm_medis like ? and IFNULL(DATE_FORMAT(lh.waktu_reg_lab,'%H:%i:%s'),DATE_FORMAT(pl.jam,'%H:%i:%s')) like ? or "
                    + "rp.no_rkm_medis like ? and lh.dokter_pengirim like ? or "
                    + "rp.no_rkm_medis like ? and if(rp.status_lanjut='Ralan','R. Jalan','R. Inap') like ? "
                    + "GROUP BY lr.no_lab ORDER BY rp.no_rawat desc, tgl DESC, jam DESC, hasil_lab limit " + cmbHlm.getSelectedItem().toString() + "");
            try {
                psNoLis.setString(1, "%" + TNoRM.getText() + "%");
                psNoLis.setString(2, "%" + TCari1.getText().trim() + "%");
                psNoLis.setString(3, "%" + TNoRM.getText() + "%");
                psNoLis.setString(4, "%" + TCari1.getText().trim() + "%");
                psNoLis.setString(5, "%" + TNoRM.getText() + "%");
                psNoLis.setString(6, "%" + TCari1.getText().trim() + "%");
                psNoLis.setString(7, "%" + TNoRM.getText() + "%");
                psNoLis.setString(8, "%" + TCari1.getText().trim() + "%");
                psNoLis.setString(9, "%" + TNoRM.getText() + "%");
                psNoLis.setString(10, "%" + TCari1.getText().trim() + "%");                
                psNoLis.setString(11, "%" + TNoRM.getText() + "%");
                psNoLis.setString(12, "%" + TCari1.getText().trim() + "%");
                psNoLis.setString(13, "%" + TNoRM.getText() + "%");
                psNoLis.setString(14, "%" + TCari1.getText().trim() + "%");
                rsNoLis = psNoLis.executeQuery();
                while (rsNoLis.next()) {
                    tabMode1.addRow(new Object[]{
                        rsNoLis.getString("pasien"),
                        rsNoLis.getString("nolis"),
                        rsNoLis.getString("hasil_lab"),
                        rsNoLis.getString("cekOK"),
                        rsNoLis.getString("no_rawat"),
                        rsNoLis.getString("tgl"),
                        rsNoLis.getString("jam"),
                        rsNoLis.getString("dokter_pengirim"),
                        rsNoLis.getString("stts_lnjut")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsNoLis != null) {
                    rsNoLis.close();
                }
                if (psNoLis != null) {
                    psNoLis.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    public void isMenu() {
        if (ChkAccor.isSelected() == true) {
            ChkAccor.setVisible(false);
            PanelAccor.setPreferredSize(new Dimension(900, HEIGHT));
            FormMenu.setVisible(true);
            ChkAccor.setVisible(true);
            Valid.tabelKosong(tabMode2);            
            TCari1.setText("");
            tampilLIS();
        } else if (ChkAccor.isSelected() == false) {
            ChkAccor.setVisible(false);
            PanelAccor.setPreferredSize(new Dimension(22, HEIGHT));
            FormMenu.setVisible(false);
            ChkAccor.setVisible(true);
        }
    }
    
    private void getDataLab() {
        Valid.tabelKosong(tabMode2);
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
            Valid.tabelKosong(tabMode2);
            ps11.setString(1, noLIS);
            rs11 = ps11.executeQuery();
            while (rs11.next()) {    
                Sequel.menyimpan("temporary_lis", "'" + rs11.getString("kategori_pemeriksaan_nama") + "','','','',"
                        + "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Kategori Pemeriksaan");
                tabMode2.addRow(new String[]{rs11.getString("kategori_pemeriksaan_nama"), "", "", "", "", "", ""});

                ps22.setString(1, noLIS);
                ps22.setString(2, rs11.getString("kategori_pemeriksaan_nama"));
                rs22 = ps22.executeQuery();
                while (rs22.next()) {
                    Sequel.menyimpan("temporary_lis", "'   " + rs22.getString("sub_kategori_pemeriksaan_nama") + "','','','',"
                            + "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Sub Kategori Pemeriksaan");
                    tabMode2.addRow(new String[]{"   "+rs22.getString("sub_kategori_pemeriksaan_nama"), "", "", "", "", "", ""});
                    
                    ps33.setString(1, noLIS);
                    ps33.setString(2, rs22.getString("sub_kategori_pemeriksaan_nama"));
                    ps33.setString(3, rs11.getString("kategori_pemeriksaan_nama"));
                    
                    rs33 = ps33.executeQuery();
                    while (rs33.next()) {
                        Sequel.menyimpan("temporary_lis", "'     " + Valid.mysql_real_escape_string(rs33.getString("pemeriksaan_nama")) + "',"
                                + "'" + Valid.mysql_real_escape_string(rs33.getString("nilai_hasil")) + "',"
                                + "'" + Valid.mysql_real_escape_string(rs33.getString("satuan")) + "',"
                                + "'" + rs33.getString("flag_kode") + "',"
                                + "'" + Valid.mysql_real_escape_string(rs33.getString("nilai_rujukan")) + "',"
                                + "'" + rs33.getString("wkt_selesai") + "',"
                                + "'" + Valid.mysql_real_escape_string(rs33.getString("metode")) + "',"
                                + "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Hasil Pemeriksaan");
                        tabMode2.addRow(new String[]{
                            "     " + rs33.getString("pemeriksaan_nama"),
                            rs33.getString("nilai_hasil"),
                            rs33.getString("satuan"),
                            rs33.getString("flag_kode"),
                            rs33.getString("nilai_rujukan"),
                            rs33.getString("wkt_selesai"),
                            rs33.getString("metode")
                        });
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
