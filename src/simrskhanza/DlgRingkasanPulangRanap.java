package simrskhanza;

import com.sun.jna.platform.win32.OaIdl;
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
import laporan.DlgDiagnosaPenyakit;
import simrskhanza.DlgCariDokter;

/**
 *
 * @author dosen
 */
public class DlgRingkasanPulangRanap extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps, psPasien, psdiag, pspros;
    private ResultSet rs, rsPasien, rsdiag, rspros;
    private int i = 0, x = 0;
    public DlgCariDokter dokter = new DlgCariDokter(null, false);
    private String kontrolPoli = "", cekTgl = "", diagnosa = "", tindakan = "";

    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public DlgRingkasanPulangRanap(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new Object[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Lahir", "Jns. Kelamin", "Tgl. Masuk", "Tgl. Pulang", "Ruang/Kelas Rawat",
            "Dokter Pengirim", "Cara Bayar", "Alasan Masuk Dirawat", "Ringkasan Riwayat Penyakit", "Pemeriksaan Fisik", "Pemeriksaan Penunjang Diagnostik",
            "Terapi Pengobatan", "Diagnosa Utama/Primer", "Diagnosa Sekunder", "Tindakan Prosedur", "Kondisi Wkt. Pulang", "Keadaan Umum", "Kesadaran", "GCS",
            "Tekanan Darah", "Suhu", "Nadi", "Frekuensi Nafas", "Catatan Penting", "Terapi Pulang", "Pengobatan Lanjutan", "Dokter Luar", "Tgl. Kontrol Poli",
            "Nama DPJP Pasien", "cektgl", "edukasi", "png_jawab_px", "nip_penyimpan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbRingkasan.setModel(tabMode);
        tbRingkasan.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbRingkasan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 36; i++) {
            TableColumn column = tbRingkasan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(60);
            } else if (i == 2) {
                column.setPreferredWidth(200);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(75);
            } else if (i == 6) {
                column.setPreferredWidth(75);
            } else if (i == 7) {
                column.setPreferredWidth(350);
            } else if (i == 8) {
                column.setPreferredWidth(200);
            } else if (i == 9) {
                column.setPreferredWidth(150);
            } else if (i == 10) {
                column.setPreferredWidth(350);
            } else if (i == 11) {
                column.setPreferredWidth(350);
            } else if (i == 12) {
                column.setPreferredWidth(350);
            } else if (i == 13) {
                column.setPreferredWidth(350);
            } else if (i == 14) {
                column.setPreferredWidth(350);
            } else if (i == 15) {
                column.setPreferredWidth(350);
            } else if (i == 16) {
                column.setPreferredWidth(350);
            } else if (i == 17) {
                column.setPreferredWidth(350);
            } else if (i == 18) {
                column.setPreferredWidth(160);
            } else if (i == 19) {
                column.setPreferredWidth(350);
            } else if (i == 20) {
                column.setPreferredWidth(350);
            } else if (i == 21) {
                column.setPreferredWidth(90);
            } else if (i == 22) {
                column.setPreferredWidth(90);
            } else if (i == 23) {
                column.setPreferredWidth(90);
            } else if (i == 24) {
                column.setPreferredWidth(90);
            } else if (i == 25) {
                column.setPreferredWidth(90);
            } else if (i == 26) {
                column.setPreferredWidth(350);
            } else if (i == 27) {
                column.setPreferredWidth(350);
            } else if (i == 28) {
                column.setPreferredWidth(140);
            } else if (i == 29) {
                column.setPreferredWidth(200);
            } else if (i == 30) {
                column.setPreferredWidth(95);
            } else if (i == 31) {
                column.setPreferredWidth(200);
            } else if (i == 32) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 33) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 34) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 35) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbRingkasan.setDefaultRenderer(Object.class, new WarnaTable());

        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        TTensi.setDocument(new batasInput((int) 15).getKata(TTensi));
        TSuhu.setDocument(new batasInput((int) 15).getKata(TSuhu));
        TNadi.setDocument(new batasInput((int) 15).getKata(TNadi));
        TFrekuensiNafas.setDocument(new batasInput((int) 15).getKata(TFrekuensiNafas));
        Tgcs.setDocument(new batasInput((int) 100).getKata(Tgcs));
        TDokterLuar.setDocument(new batasInput((int) 150).getKata(TDokterLuar));
        TNmDokter.setDocument(new batasInput((int) 150).getKata(TNmDokter));
        TKlgPasien.setDocument(new batasInput((int) 150).getKata(TKlgPasien));

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
        
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {;
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgRingkasanPulangRanap")) {
                    if (dokter.getTable().getSelectedRow() != -1) {
                        TNmDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());                        
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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnDiagnosa = new javax.swing.JMenuItem();
        MnCetakRingkasan = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        TabRingkasan = new widget.TabPane();
        internalFrame2 = new widget.InternalFrame();
        Scroll2 = new widget.ScrollPane();
        panelisi1 = new widget.panelisi();
        jLabel4 = new widget.Label();
        TNoRM = new widget.TextBox();
        TNoRW = new widget.TextBox();
        jLabel8 = new widget.Label();
        TNmPasien = new widget.TextBox();
        TTglLhr = new widget.TextBox();
        jLabel10 = new widget.Label();
        TJK = new widget.TextBox();
        jLabel11 = new widget.Label();
        TTglMsk = new widget.TextBox();
        jLabel12 = new widget.Label();
        TTglPulang = new widget.TextBox();
        jLabel13 = new widget.Label();
        TRuangrawat = new widget.TextBox();
        jLabel14 = new widget.Label();
        TCaraBayar = new widget.TextBox();
        jLabel15 = new widget.Label();
        TNmDokter = new widget.TextBox();
        BtnDokter = new widget.Button();
        jLabel43 = new widget.Label();
        Tdpjp = new widget.TextBox();
        jLabel45 = new widget.Label();
        jLabel44 = new widget.Label();
        Scroll16 = new widget.ScrollPane();
        TAlasanDirawat = new widget.TextArea();
        jLabel16 = new widget.Label();
        Scroll17 = new widget.ScrollPane();
        TRingkasanRiwayat = new widget.TextArea();
        jLabel17 = new widget.Label();
        jLabel18 = new widget.Label();
        Scroll26 = new widget.ScrollPane();
        TPemeriksaanFisik = new widget.TextArea();
        jLabel40 = new widget.Label();
        Scroll18 = new widget.ScrollPane();
        TPemeriksaanPenunjang = new widget.TextArea();
        jLabel19 = new widget.Label();
        jLabel20 = new widget.Label();
        Scroll19 = new widget.ScrollPane();
        TTerapiPengobatan = new widget.TextArea();
        jLabel21 = new widget.Label();
        jLabel22 = new widget.Label();
        jLabel23 = new widget.Label();
        Scroll20 = new widget.ScrollPane();
        TDiagUtama = new widget.TextArea();
        jLabel28 = new widget.Label();
        jLabel24 = new widget.Label();
        Scroll21 = new widget.ScrollPane();
        TDiagSekunder = new widget.TextArea();
        jLabel25 = new widget.Label();
        Scroll22 = new widget.ScrollPane();
        TTindakan = new widget.TextArea();
        jLabel26 = new widget.Label();
        Scroll23 = new widget.ScrollPane();
        TKeadaanumum = new widget.TextArea();
        jLabel27 = new widget.Label();
        Scroll24 = new widget.ScrollPane();
        TKesadaran = new widget.TextArea();
        jLabel29 = new widget.Label();
        jLabel30 = new widget.Label();
        TTensi = new widget.TextBox();
        jLabel31 = new widget.Label();
        TSuhu = new widget.TextBox();
        jLabel32 = new widget.Label();
        TNadi = new widget.TextBox();
        jLabel49 = new widget.Label();
        jLabel33 = new widget.Label();
        TFrekuensiNafas = new widget.TextBox();
        jLabel37 = new widget.Label();
        Tgcs = new widget.TextBox();
        jLabel34 = new widget.Label();
        cmbLanjutan = new widget.ComboBox();
        jLabel35 = new widget.Label();
        TDokterLuar = new widget.TextBox();
        chkTglKontrol = new widget.CekBox();
        TglKontrol = new widget.Tanggal();
        jLabel41 = new widget.Label();
        TKondisiPulang = new widget.TextBox();
        jLabel36 = new widget.Label();
        Scroll25 = new widget.ScrollPane();
        TCatatan = new widget.TextArea();
        jLabel39 = new widget.Label();
        jLabel42 = new widget.Label();
        Scroll27 = new widget.ScrollPane();
        TTerapiPulang = new widget.TextArea();
        jLabel50 = new widget.Label();
        jLabel51 = new widget.Label();
        jLabel52 = new widget.Label();
        jLabel53 = new widget.Label();
        jLabel54 = new widget.Label();
        Tedukasi = new widget.TextBox();
        jLabel55 = new widget.Label();
        jLabel56 = new widget.Label();
        TKlgPasien = new widget.TextBox();
        internalFrame4 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbRingkasan = new widget.Table();
        panelGlass9 = new widget.panelisi();
        jLabel47 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel48 = new widget.Label();
        LCount = new widget.Label();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnGanti = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnDiagnosa.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDiagnosa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDiagnosa.setText("Diagnosa Pasien (ICD)");
        MnDiagnosa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDiagnosa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDiagnosa.setIconTextGap(5);
        MnDiagnosa.setName("MnDiagnosa"); // NOI18N
        MnDiagnosa.setPreferredSize(new java.awt.Dimension(180, 26));
        MnDiagnosa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDiagnosaActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnDiagnosa);

        MnCetakRingkasan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakRingkasan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakRingkasan.setText("Cetak Ringkasan Pulang");
        MnCetakRingkasan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCetakRingkasan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCetakRingkasan.setIconTextGap(5);
        MnCetakRingkasan.setName("MnCetakRingkasan"); // NOI18N
        MnCetakRingkasan.setPreferredSize(new java.awt.Dimension(180, 26));
        MnCetakRingkasan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakRingkasanActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnCetakRingkasan);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Ringkasan Pulang Pasien Rawat Inap ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        TabRingkasan.setForeground(new java.awt.Color(0, 0, 0));
        TabRingkasan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabRingkasan.setName("TabRingkasan"); // NOI18N
        TabRingkasan.setPreferredSize(new java.awt.Dimension(400, 210));
        TabRingkasan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRingkasanMouseClicked(evt);
            }
        });

        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout());

        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);
        Scroll2.setPreferredSize(new java.awt.Dimension(452, 200));

        panelisi1.setComponentPopupMenu(jPopupMenu1);
        panelisi1.setName("panelisi1"); // NOI18N
        panelisi1.setPreferredSize(new java.awt.Dimension(1087, 780));
        panelisi1.setLayout(null);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Pasien :");
        jLabel4.setName("jLabel4"); // NOI18N
        panelisi1.add(jLabel4);
        jLabel4.setBounds(0, 8, 100, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setToolTipText("");
        TNoRM.setName("TNoRM"); // NOI18N
        panelisi1.add(TNoRM);
        TNoRM.setBounds(240, 8, 75, 23);

        TNoRW.setEditable(false);
        TNoRW.setForeground(new java.awt.Color(0, 0, 0));
        TNoRW.setToolTipText("");
        TNoRW.setName("TNoRW"); // NOI18N
        panelisi1.add(TNoRW);
        TNoRW.setBounds(105, 8, 131, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Tgl. Lahir :");
        jLabel8.setName("jLabel8"); // NOI18N
        panelisi1.add(jLabel8);
        jLabel8.setBounds(0, 36, 100, 23);

        TNmPasien.setEditable(false);
        TNmPasien.setForeground(new java.awt.Color(0, 0, 0));
        TNmPasien.setToolTipText("");
        TNmPasien.setName("TNmPasien"); // NOI18N
        panelisi1.add(TNmPasien);
        TNmPasien.setBounds(318, 8, 410, 23);

        TTglLhr.setEditable(false);
        TTglLhr.setForeground(new java.awt.Color(0, 0, 0));
        TTglLhr.setToolTipText("");
        TTglLhr.setHighlighter(null);
        TTglLhr.setName("TTglLhr"); // NOI18N
        panelisi1.add(TTglLhr);
        TTglLhr.setBounds(105, 36, 100, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Jns. Kelamin :");
        jLabel10.setName("jLabel10"); // NOI18N
        panelisi1.add(jLabel10);
        jLabel10.setBounds(210, 36, 70, 23);

        TJK.setEditable(false);
        TJK.setForeground(new java.awt.Color(0, 0, 0));
        TJK.setToolTipText("");
        TJK.setHighlighter(null);
        TJK.setName("TJK"); // NOI18N
        panelisi1.add(TJK);
        TJK.setBounds(285, 36, 80, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Tgl. Masuk :");
        jLabel11.setName("jLabel11"); // NOI18N
        panelisi1.add(jLabel11);
        jLabel11.setBounds(365, 36, 74, 23);

        TTglMsk.setEditable(false);
        TTglMsk.setForeground(new java.awt.Color(0, 0, 0));
        TTglMsk.setToolTipText("");
        TTglMsk.setHighlighter(null);
        TTglMsk.setName("TTglMsk"); // NOI18N
        panelisi1.add(TTglMsk);
        TTglMsk.setBounds(445, 36, 100, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Tgl. Pulang :");
        jLabel12.setName("jLabel12"); // NOI18N
        panelisi1.add(jLabel12);
        jLabel12.setBounds(547, 36, 75, 23);

        TTglPulang.setEditable(false);
        TTglPulang.setForeground(new java.awt.Color(0, 0, 0));
        TTglPulang.setToolTipText("");
        TTglPulang.setHighlighter(null);
        TTglPulang.setName("TTglPulang"); // NOI18N
        panelisi1.add(TTglPulang);
        TTglPulang.setBounds(627, 36, 100, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Ruang/Kelas :");
        jLabel13.setName("jLabel13"); // NOI18N
        panelisi1.add(jLabel13);
        jLabel13.setBounds(0, 64, 100, 23);

        TRuangrawat.setEditable(false);
        TRuangrawat.setForeground(new java.awt.Color(0, 0, 0));
        TRuangrawat.setToolTipText("");
        TRuangrawat.setHighlighter(null);
        TRuangrawat.setName("TRuangrawat"); // NOI18N
        TRuangrawat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TRuangrawatKeyPressed(evt);
            }
        });
        panelisi1.add(TRuangrawat);
        TRuangrawat.setBounds(105, 64, 340, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Cara Bayar :");
        jLabel14.setName("jLabel14"); // NOI18N
        panelisi1.add(jLabel14);
        jLabel14.setBounds(445, 64, 80, 23);

        TCaraBayar.setEditable(false);
        TCaraBayar.setForeground(new java.awt.Color(0, 0, 0));
        TCaraBayar.setToolTipText("");
        TCaraBayar.setHighlighter(null);
        TCaraBayar.setName("TCaraBayar"); // NOI18N
        TCaraBayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCaraBayarKeyPressed(evt);
            }
        });
        panelisi1.add(TCaraBayar);
        TCaraBayar.setBounds(530, 64, 198, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Dokter Pengirim :");
        jLabel15.setName("jLabel15"); // NOI18N
        panelisi1.add(jLabel15);
        jLabel15.setBounds(0, 120, 100, 23);

        TNmDokter.setForeground(new java.awt.Color(0, 0, 0));
        TNmDokter.setToolTipText("");
        TNmDokter.setName("TNmDokter"); // NOI18N
        TNmDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNmDokterKeyPressed(evt);
            }
        });
        panelisi1.add(TNmDokter);
        TNmDokter.setBounds(105, 120, 450, 23);

        BtnDokter.setForeground(new java.awt.Color(0, 0, 0));
        BtnDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter.setMnemonic('X');
        BtnDokter.setToolTipText("Alt+X");
        BtnDokter.setName("BtnDokter"); // NOI18N
        BtnDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokterActionPerformed(evt);
            }
        });
        panelisi1.add(BtnDokter);
        BtnDokter.setBounds(560, 120, 30, 23);

        jLabel43.setForeground(new java.awt.Color(0, 0, 0));
        jLabel43.setText("Nama DPJP :");
        jLabel43.setName("jLabel43"); // NOI18N
        panelisi1.add(jLabel43);
        jLabel43.setBounds(0, 92, 100, 23);

        Tdpjp.setEditable(false);
        Tdpjp.setForeground(new java.awt.Color(0, 0, 0));
        Tdpjp.setToolTipText("");
        Tdpjp.setHighlighter(null);
        Tdpjp.setName("Tdpjp"); // NOI18N
        panelisi1.add(Tdpjp);
        Tdpjp.setBounds(105, 92, 450, 23);

        jLabel45.setForeground(new java.awt.Color(0, 0, 0));
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel45.setText("* MOHON UNTUK TIDAK MENGGUNAKAN SINGKATAN DALAM PENGETIKAN DIAGNOSIS DAN TINDAKAN");
        jLabel45.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel45.setName("jLabel45"); // NOI18N
        panelisi1.add(jLabel45);
        jLabel45.setBounds(10, 148, 530, 23);

        jLabel44.setForeground(new java.awt.Color(0, 0, 0));
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel44.setText("* MOHON UNTUK DIKETIK DENGAN RAPI DAN DAPAT DIBACA DENGAN JELAS");
        jLabel44.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel44.setName("jLabel44"); // NOI18N
        panelisi1.add(jLabel44);
        jLabel44.setBounds(10, 162, 530, 23);

        Scroll16.setName("Scroll16"); // NOI18N
        Scroll16.setOpaque(true);

        TAlasanDirawat.setColumns(20);
        TAlasanDirawat.setRows(5);
        TAlasanDirawat.setName("TAlasanDirawat"); // NOI18N
        TAlasanDirawat.setPreferredSize(new java.awt.Dimension(190, 200));
        TAlasanDirawat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TAlasanDirawatKeyPressed(evt);
            }
        });
        Scroll16.setViewportView(TAlasanDirawat);

        panelisi1.add(Scroll16);
        Scroll16.setBounds(135, 190, 590, 80);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Alasan Masuk Dirawat :");
        jLabel16.setName("jLabel16"); // NOI18N
        panelisi1.add(jLabel16);
        jLabel16.setBounds(0, 190, 130, 23);

        Scroll17.setName("Scroll17"); // NOI18N
        Scroll17.setOpaque(true);

        TRingkasanRiwayat.setColumns(20);
        TRingkasanRiwayat.setRows(5);
        TRingkasanRiwayat.setName("TRingkasanRiwayat"); // NOI18N
        TRingkasanRiwayat.setPreferredSize(new java.awt.Dimension(190, 200));
        TRingkasanRiwayat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TRingkasanRiwayatKeyPressed(evt);
            }
        });
        Scroll17.setViewportView(TRingkasanRiwayat);

        panelisi1.add(Scroll17);
        Scroll17.setBounds(135, 275, 590, 80);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Ringkasan Riwayat ");
        jLabel17.setName("jLabel17"); // NOI18N
        panelisi1.add(jLabel17);
        jLabel17.setBounds(0, 275, 130, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Penyakit :");
        jLabel18.setName("jLabel18"); // NOI18N
        panelisi1.add(jLabel18);
        jLabel18.setBounds(0, 290, 130, 23);

        Scroll26.setName("Scroll26"); // NOI18N
        Scroll26.setOpaque(true);

        TPemeriksaanFisik.setColumns(20);
        TPemeriksaanFisik.setRows(5);
        TPemeriksaanFisik.setName("TPemeriksaanFisik"); // NOI18N
        TPemeriksaanFisik.setPreferredSize(new java.awt.Dimension(190, 200));
        TPemeriksaanFisik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPemeriksaanFisikKeyPressed(evt);
            }
        });
        Scroll26.setViewportView(TPemeriksaanFisik);

        panelisi1.add(Scroll26);
        Scroll26.setBounds(135, 360, 590, 80);

        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setText("Pemeriksaan Fisik :");
        jLabel40.setName("jLabel40"); // NOI18N
        panelisi1.add(jLabel40);
        jLabel40.setBounds(0, 360, 130, 23);

        Scroll18.setName("Scroll18"); // NOI18N
        Scroll18.setOpaque(true);

        TPemeriksaanPenunjang.setColumns(20);
        TPemeriksaanPenunjang.setRows(5);
        TPemeriksaanPenunjang.setName("TPemeriksaanPenunjang"); // NOI18N
        TPemeriksaanPenunjang.setPreferredSize(new java.awt.Dimension(190, 200));
        TPemeriksaanPenunjang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPemeriksaanPenunjangKeyPressed(evt);
            }
        });
        Scroll18.setViewportView(TPemeriksaanPenunjang);

        panelisi1.add(Scroll18);
        Scroll18.setBounds(135, 445, 590, 80);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Pemeriksaan Penunjang");
        jLabel19.setName("jLabel19"); // NOI18N
        panelisi1.add(jLabel19);
        jLabel19.setBounds(0, 445, 130, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Diagnostik/Laboratorium :");
        jLabel20.setName("jLabel20"); // NOI18N
        panelisi1.add(jLabel20);
        jLabel20.setBounds(0, 461, 130, 23);

        Scroll19.setName("Scroll19"); // NOI18N
        Scroll19.setOpaque(true);

        TTerapiPengobatan.setColumns(20);
        TTerapiPengobatan.setRows(5);
        TTerapiPengobatan.setName("TTerapiPengobatan"); // NOI18N
        TTerapiPengobatan.setPreferredSize(new java.awt.Dimension(190, 200));
        TTerapiPengobatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTerapiPengobatanKeyPressed(evt);
            }
        });
        Scroll19.setViewportView(TTerapiPengobatan);

        panelisi1.add(Scroll19);
        Scroll19.setBounds(135, 530, 590, 80);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Terapi Pengobatan ");
        jLabel21.setName("jLabel21"); // NOI18N
        panelisi1.add(jLabel21);
        jLabel21.setBounds(0, 530, 130, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("selama dirawat & efek ");
        jLabel22.setName("jLabel22"); // NOI18N
        panelisi1.add(jLabel22);
        jLabel22.setBounds(0, 544, 130, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("samping (bila ada) :");
        jLabel23.setName("jLabel23"); // NOI18N
        panelisi1.add(jLabel23);
        jLabel23.setBounds(0, 558, 130, 23);

        Scroll20.setName("Scroll20"); // NOI18N
        Scroll20.setOpaque(true);

        TDiagUtama.setColumns(20);
        TDiagUtama.setRows(5);
        TDiagUtama.setName("TDiagUtama"); // NOI18N
        TDiagUtama.setPreferredSize(new java.awt.Dimension(190, 200));
        TDiagUtama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDiagUtamaKeyPressed(evt);
            }
        });
        Scroll20.setViewportView(TDiagUtama);

        panelisi1.add(Scroll20);
        Scroll20.setBounds(135, 615, 590, 80);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Diagnosa Utama/ ");
        jLabel28.setName("jLabel28"); // NOI18N
        panelisi1.add(jLabel28);
        jLabel28.setBounds(0, 615, 130, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Primer :");
        jLabel24.setName("jLabel24"); // NOI18N
        panelisi1.add(jLabel24);
        jLabel24.setBounds(0, 628, 130, 23);

        Scroll21.setName("Scroll21"); // NOI18N
        Scroll21.setOpaque(true);

        TDiagSekunder.setColumns(20);
        TDiagSekunder.setRows(5);
        TDiagSekunder.setName("TDiagSekunder"); // NOI18N
        TDiagSekunder.setPreferredSize(new java.awt.Dimension(190, 200));
        TDiagSekunder.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDiagSekunderKeyPressed(evt);
            }
        });
        Scroll21.setViewportView(TDiagSekunder);

        panelisi1.add(Scroll21);
        Scroll21.setBounds(135, 700, 590, 70);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Diagnosa Sekunder :");
        jLabel25.setName("jLabel25"); // NOI18N
        panelisi1.add(jLabel25);
        jLabel25.setBounds(0, 700, 130, 23);

        Scroll22.setName("Scroll22"); // NOI18N
        Scroll22.setOpaque(true);

        TTindakan.setColumns(20);
        TTindakan.setRows(5);
        TTindakan.setName("TTindakan"); // NOI18N
        TTindakan.setPreferredSize(new java.awt.Dimension(190, 200));
        TTindakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTindakanKeyPressed(evt);
            }
        });
        Scroll22.setViewportView(TTindakan);

        panelisi1.add(Scroll22);
        Scroll22.setBounds(865, 190, 590, 80);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Tindakan Prosedur :");
        jLabel26.setName("jLabel26"); // NOI18N
        panelisi1.add(jLabel26);
        jLabel26.setBounds(730, 190, 130, 23);

        Scroll23.setName("Scroll23"); // NOI18N
        Scroll23.setOpaque(true);

        TKeadaanumum.setColumns(20);
        TKeadaanumum.setRows(5);
        TKeadaanumum.setName("TKeadaanumum"); // NOI18N
        TKeadaanumum.setPreferredSize(new java.awt.Dimension(190, 200));
        TKeadaanumum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKeadaanumumKeyPressed(evt);
            }
        });
        Scroll23.setViewportView(TKeadaanumum);

        panelisi1.add(Scroll23);
        Scroll23.setBounds(865, 275, 590, 80);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("Keadaan Umum :");
        jLabel27.setName("jLabel27"); // NOI18N
        panelisi1.add(jLabel27);
        jLabel27.setBounds(730, 275, 130, 23);

        Scroll24.setName("Scroll24"); // NOI18N
        Scroll24.setOpaque(true);

        TKesadaran.setColumns(20);
        TKesadaran.setRows(5);
        TKesadaran.setName("TKesadaran"); // NOI18N
        TKesadaran.setPreferredSize(new java.awt.Dimension(190, 200));
        TKesadaran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKesadaranKeyPressed(evt);
            }
        });
        Scroll24.setViewportView(TKesadaran);

        panelisi1.add(Scroll24);
        Scroll24.setBounds(865, 360, 590, 80);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("Kesadaran :");
        jLabel29.setName("jLabel29"); // NOI18N
        panelisi1.add(jLabel29);
        jLabel29.setBounds(730, 360, 130, 23);

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Tanda Vital :");
        jLabel30.setName("jLabel30"); // NOI18N
        panelisi1.add(jLabel30);
        jLabel30.setBounds(730, 475, 130, 23);

        TTensi.setForeground(new java.awt.Color(0, 0, 0));
        TTensi.setToolTipText("");
        TTensi.setName("TTensi"); // NOI18N
        TTensi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTensiKeyPressed(evt);
            }
        });
        panelisi1.add(TTensi);
        TTensi.setBounds(865, 495, 70, 23);

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("Suhu :");
        jLabel31.setName("jLabel31"); // NOI18N
        panelisi1.add(jLabel31);
        jLabel31.setBounds(990, 495, 50, 23);

        TSuhu.setForeground(new java.awt.Color(0, 0, 0));
        TSuhu.setToolTipText("");
        TSuhu.setName("TSuhu"); // NOI18N
        TSuhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TSuhuKeyPressed(evt);
            }
        });
        panelisi1.add(TSuhu);
        TSuhu.setBounds(1045, 495, 70, 23);

        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Nadi :");
        jLabel32.setName("jLabel32"); // NOI18N
        panelisi1.add(jLabel32);
        jLabel32.setBounds(1145, 495, 50, 23);

        TNadi.setForeground(new java.awt.Color(0, 0, 0));
        TNadi.setToolTipText("");
        TNadi.setName("TNadi"); // NOI18N
        TNadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNadiKeyPressed(evt);
            }
        });
        panelisi1.add(TNadi);
        TNadi.setBounds(1200, 495, 70, 23);

        jLabel49.setForeground(new java.awt.Color(0, 0, 0));
        jLabel49.setText("Tekanan darah :");
        jLabel49.setName("jLabel49"); // NOI18N
        panelisi1.add(jLabel49);
        jLabel49.setBounds(730, 495, 130, 23);

        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("Frekuensi Nafas :");
        jLabel33.setName("jLabel33"); // NOI18N
        panelisi1.add(jLabel33);
        jLabel33.setBounds(730, 525, 130, 23);

        TFrekuensiNafas.setForeground(new java.awt.Color(0, 0, 0));
        TFrekuensiNafas.setToolTipText("");
        TFrekuensiNafas.setName("TFrekuensiNafas"); // NOI18N
        TFrekuensiNafas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TFrekuensiNafasKeyPressed(evt);
            }
        });
        panelisi1.add(TFrekuensiNafas);
        TFrekuensiNafas.setBounds(865, 525, 70, 23);

        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setText("GCS :");
        jLabel37.setName("jLabel37"); // NOI18N
        panelisi1.add(jLabel37);
        jLabel37.setBounds(990, 525, 50, 23);

        Tgcs.setForeground(new java.awt.Color(0, 0, 0));
        Tgcs.setToolTipText("");
        Tgcs.setName("Tgcs"); // NOI18N
        Tgcs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgcsKeyPressed(evt);
            }
        });
        panelisi1.add(Tgcs);
        Tgcs.setBounds(1045, 525, 235, 23);

        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Pengobatan Lanjutan :");
        jLabel34.setName("jLabel34"); // NOI18N
        panelisi1.add(jLabel34);
        jLabel34.setBounds(730, 555, 130, 23);

        cmbLanjutan.setForeground(new java.awt.Color(0, 0, 0));
        cmbLanjutan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Poliklinik", "RS Lain", "Puskesmas", "Dokter Luar" }));
        cmbLanjutan.setName("cmbLanjutan"); // NOI18N
        cmbLanjutan.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbLanjutan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbLanjutanActionPerformed(evt);
            }
        });
        cmbLanjutan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbLanjutanKeyPressed(evt);
            }
        });
        panelisi1.add(cmbLanjutan);
        cmbLanjutan.setBounds(865, 555, 110, 23);

        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("Nama Dokter :");
        jLabel35.setName("jLabel35"); // NOI18N
        panelisi1.add(jLabel35);
        jLabel35.setBounds(975, 555, 85, 23);

        TDokterLuar.setForeground(new java.awt.Color(0, 0, 0));
        TDokterLuar.setToolTipText("");
        TDokterLuar.setName("TDokterLuar"); // NOI18N
        TDokterLuar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDokterLuarKeyPressed(evt);
            }
        });
        panelisi1.add(TDokterLuar);
        TDokterLuar.setBounds(1065, 555, 385, 23);

        chkTglKontrol.setBackground(new java.awt.Color(242, 242, 242));
        chkTglKontrol.setBorder(null);
        chkTglKontrol.setForeground(new java.awt.Color(0, 0, 0));
        chkTglKontrol.setText("Tgl. Kontrol Poli :");
        chkTglKontrol.setBorderPainted(true);
        chkTglKontrol.setBorderPaintedFlat(true);
        chkTglKontrol.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkTglKontrol.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTglKontrol.setName("chkTglKontrol"); // NOI18N
        chkTglKontrol.setOpaque(false);
        chkTglKontrol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTglKontrolActionPerformed(evt);
            }
        });
        panelisi1.add(chkTglKontrol);
        chkTglKontrol.setBounds(730, 585, 130, 23);

        TglKontrol.setEditable(false);
        TglKontrol.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "19-10-2023" }));
        TglKontrol.setDisplayFormat("dd-MM-yyyy");
        TglKontrol.setName("TglKontrol"); // NOI18N
        TglKontrol.setOpaque(false);
        TglKontrol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglKontrolKeyPressed(evt);
            }
        });
        panelisi1.add(TglKontrol);
        TglKontrol.setBounds(865, 585, 90, 23);

        jLabel41.setForeground(new java.awt.Color(0, 0, 0));
        jLabel41.setText("Kondisi Waktu Pulang :");
        jLabel41.setName("jLabel41"); // NOI18N
        panelisi1.add(jLabel41);
        jLabel41.setBounds(956, 585, 130, 23);

        TKondisiPulang.setEditable(false);
        TKondisiPulang.setForeground(new java.awt.Color(0, 0, 0));
        TKondisiPulang.setToolTipText("");
        TKondisiPulang.setName("TKondisiPulang"); // NOI18N
        panelisi1.add(TKondisiPulang);
        TKondisiPulang.setBounds(1090, 585, 360, 23);

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Catatan Penting ");
        jLabel36.setName("jLabel36"); // NOI18N
        panelisi1.add(jLabel36);
        jLabel36.setBounds(730, 615, 130, 23);

        Scroll25.setName("Scroll25"); // NOI18N
        Scroll25.setOpaque(true);

        TCatatan.setColumns(20);
        TCatatan.setRows(5);
        TCatatan.setName("TCatatan"); // NOI18N
        TCatatan.setPreferredSize(new java.awt.Dimension(190, 200));
        TCatatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCatatanKeyPressed(evt);
            }
        });
        Scroll25.setViewportView(TCatatan);

        panelisi1.add(Scroll25);
        Scroll25.setBounds(865, 615, 590, 70);

        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setText("(kondisi saat ini) :");
        jLabel39.setName("jLabel39"); // NOI18N
        panelisi1.add(jLabel39);
        jLabel39.setBounds(730, 630, 130, 23);

        jLabel42.setForeground(new java.awt.Color(0, 0, 0));
        jLabel42.setText("Terapi Pulang :");
        jLabel42.setName("jLabel42"); // NOI18N
        panelisi1.add(jLabel42);
        jLabel42.setBounds(730, 690, 130, 23);

        Scroll27.setName("Scroll27"); // NOI18N
        Scroll27.setOpaque(true);

        TTerapiPulang.setColumns(20);
        TTerapiPulang.setRows(5);
        TTerapiPulang.setName("TTerapiPulang"); // NOI18N
        TTerapiPulang.setPreferredSize(new java.awt.Dimension(190, 200));
        TTerapiPulang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTerapiPulangKeyPressed(evt);
            }
        });
        Scroll27.setViewportView(TTerapiPulang);

        panelisi1.add(Scroll27);
        Scroll27.setBounds(865, 690, 590, 80);

        jLabel50.setForeground(new java.awt.Color(0, 0, 0));
        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel50.setText("mmHg");
        jLabel50.setName("jLabel50"); // NOI18N
        panelisi1.add(jLabel50);
        jLabel50.setBounds(940, 495, 40, 23);

        jLabel51.setForeground(new java.awt.Color(0, 0, 0));
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel51.setText("x/menit");
        jLabel51.setName("jLabel51"); // NOI18N
        panelisi1.add(jLabel51);
        jLabel51.setBounds(940, 525, 40, 23);

        jLabel52.setForeground(new java.awt.Color(0, 0, 0));
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel52.setText("°C");
        jLabel52.setName("jLabel52"); // NOI18N
        panelisi1.add(jLabel52);
        jLabel52.setBounds(1120, 495, 30, 23);

        jLabel53.setForeground(new java.awt.Color(0, 0, 0));
        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel53.setText("x/menit");
        jLabel53.setName("jLabel53"); // NOI18N
        panelisi1.add(jLabel53);
        jLabel53.setBounds(1275, 495, 40, 23);

        jLabel54.setForeground(new java.awt.Color(0, 0, 0));
        jLabel54.setText("Edukasi Yang Diberikan :");
        jLabel54.setName("jLabel54"); // NOI18N
        panelisi1.add(jLabel54);
        jLabel54.setBounds(730, 446, 130, 23);

        Tedukasi.setForeground(new java.awt.Color(0, 0, 0));
        Tedukasi.setToolTipText("");
        Tedukasi.setName("Tedukasi"); // NOI18N
        Tedukasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TedukasiKeyPressed(evt);
            }
        });
        panelisi1.add(Tedukasi);
        Tedukasi.setBounds(865, 446, 590, 23);

        jLabel55.setForeground(new java.awt.Color(0, 0, 0));
        jLabel55.setText("Pasien / Keluarga /");
        jLabel55.setName("jLabel55"); // NOI18N
        panelisi1.add(jLabel55);
        jLabel55.setBounds(730, 145, 130, 23);

        jLabel56.setForeground(new java.awt.Color(0, 0, 0));
        jLabel56.setText("Penanggung Jawab Pasien :");
        jLabel56.setName("jLabel56"); // NOI18N
        panelisi1.add(jLabel56);
        jLabel56.setBounds(690, 160, 170, 23);

        TKlgPasien.setForeground(new java.awt.Color(0, 0, 0));
        TKlgPasien.setToolTipText("");
        TKlgPasien.setName("TKlgPasien"); // NOI18N
        TKlgPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKlgPasienKeyPressed(evt);
            }
        });
        panelisi1.add(TKlgPasien);
        TKlgPasien.setBounds(865, 160, 590, 23);

        Scroll2.setViewportView(panelisi1);

        internalFrame2.add(Scroll2, java.awt.BorderLayout.CENTER);

        TabRingkasan.addTab("Input Data", internalFrame2);

        internalFrame4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout());

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(452, 200));

        tbRingkasan.setToolTipText("Silahkan klik untuk memilih data yang ataupun dihapus");
        tbRingkasan.setComponentPopupMenu(jPopupMenu1);
        tbRingkasan.setName("tbRingkasan"); // NOI18N
        tbRingkasan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRingkasanMouseClicked(evt);
            }
        });
        tbRingkasan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbRingkasanKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbRingkasan);

        internalFrame4.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel47.setForeground(new java.awt.Color(0, 0, 0));
        jLabel47.setText("Key Word :");
        jLabel47.setName("jLabel47"); // NOI18N
        jLabel47.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass9.add(jLabel47);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(195, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('T');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+T");
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

        jLabel48.setForeground(new java.awt.Color(0, 0, 0));
        jLabel48.setText("Record :");
        jLabel48.setName("jLabel48"); // NOI18N
        jLabel48.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass9.add(jLabel48);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(LCount);

        internalFrame4.add(panelGlass9, java.awt.BorderLayout.PAGE_END);

        TabRingkasan.addTab("Data Ringkasan Pulang", internalFrame4);

        internalFrame1.add(TabRingkasan, java.awt.BorderLayout.CENTER);

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(55, 54));
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

        BtnAll.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setText("Semua");
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(100, 30));
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
        if (TNoRW.getText().equals("")) {
            Valid.textKosong(TNoRW, "nomor rawat");
        } else if (TNmDokter.getText().trim().equals("")) {
            Valid.textKosong(TNmDokter, "dokter pengirim");
        } else {
            kontrolPoli = "";
            cekTgl = "";
            if (chkTglKontrol.isSelected() == false) {
                kontrolPoli = "0000-00-00";
                cekTgl = "tidak";
            } else {
                kontrolPoli = Valid.SetTgl(TglKontrol.getSelectedItem() + "");
                cekTgl = "ya";
            }
            
            if (TKlgPasien.getText().equals("")) {
                TKlgPasien.setText("-");
            } else {
                TKlgPasien.setText(TKlgPasien.getText());
            }
            
            Sequel.menyimpan("ringkasan_pulang_ranap", "'" + TNoRW.getText() + "','" + TAlasanDirawat.getText() + "','" + TRingkasanRiwayat.getText() + "',"
                    + "'" + TPemeriksaanFisik.getText() + "','" + TPemeriksaanPenunjang.getText() + "','" + TTerapiPengobatan.getText() + "',"
                    + "'" + TDiagUtama.getText() + "','" + TDiagSekunder.getText() + "','" + TKeadaanumum.getText() + "','" + TKesadaran.getText() + "',"
                    + "'" + TTensi.getText() + "','" + TSuhu.getText() + "','" + TNadi.getText() + "','" + TFrekuensiNafas.getText() + "','" + TCatatan.getText() + "',"
                    + "'" + TTerapiPulang.getText() + "','" + cmbLanjutan.getSelectedItem().toString() + "','" + kontrolPoli + "',"
                    + "'" + TNmDokter.getText() + "','" + Tgcs.getText() + "','" + TTindakan.getText() + "','" + TDokterLuar.getText() + "',"
                    + "'" + cekTgl + "','" + Tedukasi.getText() + "','" + TKlgPasien.getText() + "','" + akses.getkode() + "'", "Ringkasan Pulang Pasien Rawat Inap");
            
            emptTeks();
            tampil();
            TabRingkasan.setSelectedIndex(1);
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, TNmDokter, BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnGanti);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnGantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiActionPerformed
        if (TNoRW.getText().equals("")) {
            Valid.textKosong(TNoRW, "nomor rawat");
        } else if (TNmDokter.getText().trim().equals("")) {
            Valid.textKosong(TNmDokter, "dokter pengirim");
        } else {
            kontrolPoli = "";
            cekTgl = "";
            if (chkTglKontrol.isSelected() == false) {
                kontrolPoli = "0000-00-00";
                cekTgl = "tidak";
            } else {
                kontrolPoli = Valid.SetTgl(TglKontrol.getSelectedItem() + "");
                cekTgl = "ya";
            }
            
            if (TKlgPasien.getText().equals("")) {
                TKlgPasien.setText("-");
            } else {
                TKlgPasien.setText(TKlgPasien.getText());
            }

            if (akses.getkode().equals(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 35).toString())) {
                Sequel.mengedit("ringkasan_pulang_ranap", "no_rawat='" + TNoRW.getText() + "'", "alasan_masuk_dirawat='" + TAlasanDirawat.getText() + "', "
                        + "ringkasan_riwayat_penyakit='" + TRingkasanRiwayat.getText() + "', pemeriksaan_fisik='" + TPemeriksaanFisik.getText() + "', "
                        + "pemeriksaan_penunjang='" + TPemeriksaanPenunjang.getText() + "',terapi_pengobatan='" + TTerapiPengobatan.getText() + "',"
                        + "diagnosa_utama='" + TDiagUtama.getText() + "',diagnosa_sekunder='" + TDiagSekunder.getText() + "',keadaan_umum='" + TKeadaanumum.getText() + "',"
                        + "kesadaran='" + TKesadaran.getText() + "',tekanan_darah='" + TTensi.getText() + "',suhu='" + TSuhu.getText() + "',nadi='" + TNadi.getText() + "',"
                        + "frekuensi_nafas='" + TFrekuensiNafas.getText() + "',catatan_penting='" + TCatatan.getText() + "',terapi_pulang='" + TTerapiPulang.getText() + "',"
                        + "pengobatan_dilanjutkan='" + cmbLanjutan.getSelectedItem().toString() + "',tgl_kontrol_poliklinik='" + kontrolPoli + "',"
                        + "nm_dokter_pengirim='" + TNmDokter.getText() + "',GCS='" + Tgcs.getText() + "',tindakan_prosedur='" + TTindakan.getText() + "',"
                        + "dokter_luar_lanjutan='" + TDokterLuar.getText() + "',cek_tgl_kontrol='" + cekTgl + "',edukasi='" + Tedukasi.getText() + "',"
                        + "penanggung_jwb_pasien='" + TKlgPasien.getText() + "'");

                tampil();
                emptTeks();
                TabRingkasan.setSelectedIndex(1);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Maaf, data hanya bisa diperbaiki oleh " + Sequel.cariIsi("select nama from petugas where nip='" + tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 35).toString() + "'"));
            }
        }
}//GEN-LAST:event_BtnGantiActionPerformed

    private void BtnGantiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnGantiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnGantiActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnKeluar);
        }
}//GEN-LAST:event_BtnGantiKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnBatal, TCari);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        tampil();
        emptTeks();        
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            tampil();
            TCari.setText("");
        } else {
            Valid.pindah(evt, BtnCari, TNmDokter);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbRingkasanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRingkasanMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbRingkasanMouseClicked

    private void tbRingkasanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbRingkasanKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbRingkasanKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        if (Sequel.cariInteger("select count(-1) from ringkasan_pulang_ranap where no_rawat='" + TNoRW.getText() + "'") > 0) {
            TabRingkasan.setSelectedIndex(1);
            tampil();
        } else if (Sequel.cariInteger("select count(-1) from ringkasan_pulang_ranap where no_rawat='" + TNoRW.getText() + "'") == 0) {
            TabRingkasan.setSelectedIndex(0);
        }
    }//GEN-LAST:event_formWindowOpened

    private void TabRingkasanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRingkasanMouseClicked
        if (TabRingkasan.getSelectedIndex() == 1) {
            tampil();
        }
    }//GEN-LAST:event_TabRingkasanMouseClicked

    private void TRuangrawatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TRuangrawatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TRuangrawatKeyPressed

    private void TCaraBayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCaraBayarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TCaraBayarKeyPressed

    private void TNmDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNmDokterKeyPressed
        Valid.pindah(evt, TNmDokter, TAlasanDirawat);
    }//GEN-LAST:event_TNmDokterKeyPressed

    private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
        akses.setform("DlgRingkasanPulangRanap");
        dokter.isCek();
        dokter.TCari.requestFocus();
        dokter.setSize(1045, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);
        dokter.emptTeks();
    }//GEN-LAST:event_BtnDokterActionPerformed

    private void TAlasanDirawatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TAlasanDirawatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TRingkasanRiwayat.requestFocus();
        }
    }//GEN-LAST:event_TAlasanDirawatKeyPressed

    private void TRingkasanRiwayatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TRingkasanRiwayatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TPemeriksaanFisik.requestFocus();
        }
    }//GEN-LAST:event_TRingkasanRiwayatKeyPressed

    private void TPemeriksaanPenunjangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPemeriksaanPenunjangKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TTerapiPengobatan.requestFocus();
        }
    }//GEN-LAST:event_TPemeriksaanPenunjangKeyPressed

    private void TTerapiPengobatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTerapiPengobatanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TDiagUtama.requestFocus();
        }
    }//GEN-LAST:event_TTerapiPengobatanKeyPressed

    private void TDiagUtamaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TDiagUtamaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TDiagSekunder.requestFocus();
        }
    }//GEN-LAST:event_TDiagUtamaKeyPressed

    private void TDiagSekunderKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TDiagSekunderKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TKlgPasien.requestFocus();
        }
    }//GEN-LAST:event_TDiagSekunderKeyPressed

    private void TTindakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTindakanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TKeadaanumum.requestFocus();
        }
    }//GEN-LAST:event_TTindakanKeyPressed

    private void TKeadaanumumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKeadaanumumKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TKesadaran.requestFocus();
        }
    }//GEN-LAST:event_TKeadaanumumKeyPressed

    private void TKesadaranKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKesadaranKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Tedukasi.requestFocus();
        }
    }//GEN-LAST:event_TKesadaranKeyPressed

    private void TTensiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTensiKeyPressed
        Valid.pindah(evt, TTensi, TSuhu);
    }//GEN-LAST:event_TTensiKeyPressed

    private void TSuhuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TSuhuKeyPressed
        Valid.pindah(evt, TTensi, TNadi);
    }//GEN-LAST:event_TSuhuKeyPressed

    private void TNadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNadiKeyPressed
        Valid.pindah(evt, TSuhu, TFrekuensiNafas);
    }//GEN-LAST:event_TNadiKeyPressed

    private void TFrekuensiNafasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TFrekuensiNafasKeyPressed
        Valid.pindah(evt, TNadi, Tgcs);
    }//GEN-LAST:event_TFrekuensiNafasKeyPressed

    private void cmbLanjutanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbLanjutanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbLanjutanKeyPressed

    private void TglKontrolKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglKontrolKeyPressed
//        Valid.pindah(evt, TKdPrw, cmbJam);
    }//GEN-LAST:event_TglKontrolKeyPressed

    private void TgcsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgcsKeyPressed
        Valid.pindah(evt, TFrekuensiNafas, cmbLanjutan);
    }//GEN-LAST:event_TgcsKeyPressed

    private void TCatatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCatatanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TTerapiPulang.requestFocus();
        }
    }//GEN-LAST:event_TCatatanKeyPressed

    private void TPemeriksaanFisikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPemeriksaanFisikKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TPemeriksaanPenunjang.requestFocus();
        }
    }//GEN-LAST:event_TPemeriksaanFisikKeyPressed

    private void TTerapiPulangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTerapiPulangKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            BtnSimpan.requestFocus();
        }
    }//GEN-LAST:event_TTerapiPulangKeyPressed

    private void cmbLanjutanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbLanjutanActionPerformed
        if (cmbLanjutan.getSelectedItem().toString().equals("Dokter Luar")) {
            TDokterLuar.requestFocus();
            TDokterLuar.setEditable(true);
        } else {
            TDokterLuar.setEditable(false);
            TDokterLuar.setText("");
        }
    }//GEN-LAST:event_cmbLanjutanActionPerformed

    private void MnDiagnosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDiagnosaActionPerformed
        if (TNoRW.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu ringkasan pulang pasiennya...!!!");
            TCari.requestFocus();
        } else {
            DlgDiagnosaPenyakit resep = new DlgDiagnosaPenyakit(null, false);
            resep.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            resep.setLocationRelativeTo(internalFrame1);
            resep.isCek();
            resep.setNoRm(TNoRW.getText(), Valid.SetTgl2(Sequel.cariIsi("select date(now())")), "Ranap");
            resep.tampilDiagStatistik();
            resep.tampilDiagInadrg();
            resep.setVisible(true);
        }
    }//GEN-LAST:event_MnDiagnosaActionPerformed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbRingkasan.getSelectedRow() > -1) {
            if (akses.getadmin() == true) {
                x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    if (Sequel.queryu2tf("delete from ringkasan_pulang_ranap where no_rawat=?", 1, new String[]{
                        tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 0).toString()
                    }) == true) {
                        tampil();
                        emptTeks();
                        TabRingkasan.setSelectedIndex(1);
                    } else {
                        JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                    }
                }
            } else {
                if (akses.getkode().equals(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 35).toString())) {
                    x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                    if (x == JOptionPane.YES_OPTION) {
                        if (Sequel.queryu2tf("delete from ringkasan_pulang_ranap where no_rawat=?", 1, new String[]{
                            tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 0).toString()
                        }) == true) {
                            tampil();
                            emptTeks();
                            TabRingkasan.setSelectedIndex(1);
                        } else {
                            JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Maaf, hanya bisa dihapus oleh " + Sequel.cariIsi("select nama from petugas where nip='" + tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 35).toString() + "'"));
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu pada tabel..!!");
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnKeluar);
        }
    }//GEN-LAST:event_BtnHapusKeyPressed

    private void MnCetakRingkasanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakRingkasanActionPerformed
        if (tbRingkasan.getSelectedRow() > -1) {
            diagnosa = "";
            tindakan = "";
            
            //simpan diagnosa sekunder ICD-10------------->>
            try {
                psdiag = koneksi.prepareStatement("SELECT dp.kd_penyakit icd_sekunder, py.ciri_ciri diag_sekunder FROM diagnosa_pasien dp "
                        + "INNER JOIN penyakit py ON py.kd_penyakit = dp.kd_penyakit "
                        + "WHERE dp.no_rawat like '%" + TNoRW.getText() + "%' AND dp.prioritas <> 1 AND dp. STATUS = 'ranap'");
                try {
                    rsdiag = psdiag.executeQuery();
                    i = 1;
                    while (rsdiag.next()) {
                        if (diagnosa.equals("")) {
                            diagnosa = i + ". " + rsdiag.getString("diag_sekunder") + " (ICD 10 : " + rsdiag.getString("icd_sekunder") + ")";
                        } else {
                            diagnosa = diagnosa + "\n" + i + ". " + rsdiag.getString("diag_sekunder") + " (ICD 10 : " + rsdiag.getString("icd_sekunder") + ")";
                        }
                        i++;
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }

            //simpan tindakan prosedur ------------->>
            try {
                pspros = koneksi.prepareStatement("SELECT pp.kode, i.deskripsi_panjang FROM prosedur_pasien pp INNER JOIN icd9 i ON i.kode = pp.kode "
                        + "WHERE pp.no_rawat like '%" + TNoRW.getText() + "%' AND pp. STATUS = 'ranap'");
                try {
                    rspros = pspros.executeQuery();
                    i = 1;
                    while (rspros.next()) {
                        if (tindakan.equals("")) {
                            tindakan = i + ". " + rspros.getString("deskripsi_panjang") + " (ICD 9 CM : " + rspros.getString("kode") + ")";
                        } else {
                            tindakan = tindakan + "\n" + i + ". " + rspros.getString("deskripsi_panjang") + " (ICD 9 CM : " + rspros.getString("kode") + ")";
                        }
                        i++;
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }
            
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));                    
            param.put("norm", TNoRM.getText());
            param.put("nmpasien", TNmPasien.getText());
            param.put("tgllahir", TTglLhr.getText());
            param.put("jk", TJK.getText());
            param.put("tglmsk", TTglMsk.getText());
            param.put("tglplg", TTglPulang.getText());
            param.put("rgrawat", TRuangrawat.getText());
            param.put("crbayar", TCaraBayar.getText());
            param.put("drDPJP", Tdpjp.getText());
            param.put("nmdokter", TNmDokter.getText());            
            param.put("alasan", TAlasanDirawat.getText());
            param.put("ringkasan", TRingkasanRiwayat.getText());
            param.put("fisik", TPemeriksaanFisik.getText());
            param.put("penunjang", TPemeriksaanPenunjang.getText());
            param.put("terapi", TTerapiPengobatan.getText());            
            param.put("diagnosaUtama", TDiagUtama.getText());
            param.put("diagnosaSekunder", TDiagSekunder.getText());
            param.put("diagnosaSekunderList", diagnosa);
            param.put("tindakan", TTindakan.getText());
            param.put("tindakanList", tindakan);            
            param.put("png_jawab_px", TKlgPasien.getText());
            param.put("kondisiPlg", TKondisiPulang.getText());
            param.put("keadaanumum", TKeadaanumum.getText());
            param.put("kesadaran", TKesadaran.getText() + ", GCS : " + Tgcs.getText());
            param.put("tandavital", "Tekanan Darah : " + TTensi.getText() + " mmHg, Suhu : " + TSuhu.getText() + " °C, Nadi : " + TNadi.getText() + " x/mnt, Frekuensi Nafas : " + TFrekuensiNafas.getText() + " x/mnt");
            param.put("edukasi", Tedukasi.getText());
            param.put("terapiPlg", TTerapiPulang.getText());
            param.put("pengobatan", cmbLanjutan.getSelectedItem().toString() + " " + TDokterLuar.getText());

            if (chkTglKontrol.isSelected() == false) {
                param.put("tglkontrolpoli", "-");
            } else {
                param.put("tglkontrolpoli", Valid.SetTglINDONESIA(Sequel.cariIsi("select tgl_kontrol_poliklinik from ringkasan_pulang_ranap where no_rawat='" + TNoRW.getText() + "'")));
            }

            param.put("tglRingkasan", "Martapura, " + Valid.SetTglINDONESIA(Sequel.cariIsi("select date(now())")));
            param.put("jamRingkasan", "Jam          : " + Sequel.cariIsi("select time_format(now(),'%H:%i')") + " WITA");

            Valid.MyReport("rptRingkasanPulangRanap.jasper", "report", "::[ Lembar Ringkasan Pulang Pasien Rawat Inap ]::",
                    "select date(now())", param);
            this.setCursor(Cursor.getDefaultCursor());
            
            tampil();
            emptTeks();
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih salah satu data pada tabel terlebih dahulu..!!!!");
        }
    }//GEN-LAST:event_MnCetakRingkasanActionPerformed

    private void chkTglKontrolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTglKontrolActionPerformed
        if (chkTglKontrol.isSelected() == true) {
            TglKontrol.setEnabled(true);
            TglKontrol.requestFocus();
            TglKontrol.setDate(new Date());
        } else {
            TglKontrol.setEnabled(false);
            TglKontrol.setDate(new Date());
        }
    }//GEN-LAST:event_chkTglKontrolActionPerformed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        }
    }//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tampil();
    }//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari, BtnAll);
        }
    }//GEN-LAST:event_BtnCariKeyPressed

    private void TDokterLuarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TDokterLuarKeyPressed
        Valid.pindah(evt, cmbLanjutan, TCatatan);
    }//GEN-LAST:event_TDokterLuarKeyPressed

    private void TedukasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TedukasiKeyPressed
        Valid.pindah(evt, TKesadaran, TTensi);
    }//GEN-LAST:event_TedukasiKeyPressed

    private void TKlgPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKlgPasienKeyPressed
        Valid.pindah(evt, TDiagSekunder, TTindakan);
    }//GEN-LAST:event_TKlgPasienKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgRingkasanPulangRanap dialog = new DlgRingkasanPulangRanap(new javax.swing.JFrame(), true);
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
    public widget.Button BtnDokter;
    private widget.Button BtnGanti;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnSimpan;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnCetakRingkasan;
    private javax.swing.JMenuItem MnDiagnosa;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll16;
    private widget.ScrollPane Scroll17;
    private widget.ScrollPane Scroll18;
    private widget.ScrollPane Scroll19;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll20;
    private widget.ScrollPane Scroll21;
    private widget.ScrollPane Scroll22;
    private widget.ScrollPane Scroll23;
    private widget.ScrollPane Scroll24;
    private widget.ScrollPane Scroll25;
    private widget.ScrollPane Scroll26;
    private widget.ScrollPane Scroll27;
    private widget.TextArea TAlasanDirawat;
    private widget.TextBox TCaraBayar;
    private widget.TextBox TCari;
    private widget.TextArea TCatatan;
    private widget.TextArea TDiagSekunder;
    private widget.TextArea TDiagUtama;
    private widget.TextBox TDokterLuar;
    private widget.TextBox TFrekuensiNafas;
    private widget.TextBox TJK;
    private widget.TextArea TKeadaanumum;
    private widget.TextArea TKesadaran;
    private widget.TextBox TKlgPasien;
    private widget.TextBox TKondisiPulang;
    private widget.TextBox TNadi;
    public widget.TextBox TNmDokter;
    private widget.TextBox TNmPasien;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRW;
    private widget.TextArea TPemeriksaanFisik;
    private widget.TextArea TPemeriksaanPenunjang;
    private widget.TextArea TRingkasanRiwayat;
    private widget.TextBox TRuangrawat;
    private widget.TextBox TSuhu;
    private widget.TextBox TTensi;
    private widget.TextArea TTerapiPengobatan;
    private widget.TextArea TTerapiPulang;
    private widget.TextBox TTglLhr;
    private widget.TextBox TTglMsk;
    private widget.TextBox TTglPulang;
    private widget.TextArea TTindakan;
    private widget.TabPane TabRingkasan;
    private widget.TextBox Tdpjp;
    private widget.TextBox Tedukasi;
    private widget.TextBox Tgcs;
    private widget.Tanggal TglKontrol;
    private widget.CekBox chkTglKontrol;
    private widget.ComboBox cmbLanjutan;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame4;
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
    private widget.Label jLabel26;
    private widget.Label jLabel27;
    private widget.Label jLabel28;
    private widget.Label jLabel29;
    private widget.Label jLabel30;
    private widget.Label jLabel31;
    private widget.Label jLabel32;
    private widget.Label jLabel33;
    private widget.Label jLabel34;
    private widget.Label jLabel35;
    private widget.Label jLabel36;
    private widget.Label jLabel37;
    private widget.Label jLabel39;
    private widget.Label jLabel4;
    private widget.Label jLabel40;
    private widget.Label jLabel41;
    private widget.Label jLabel42;
    private widget.Label jLabel43;
    private widget.Label jLabel44;
    private widget.Label jLabel45;
    private widget.Label jLabel47;
    private widget.Label jLabel48;
    private widget.Label jLabel49;
    private widget.Label jLabel50;
    private widget.Label jLabel51;
    private widget.Label jLabel52;
    private widget.Label jLabel53;
    private widget.Label jLabel54;
    private widget.Label jLabel55;
    private widget.Label jLabel56;
    private widget.Label jLabel8;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.panelisi panelisi1;
    private widget.Table tbRingkasan;
    // End of variables declaration//GEN-END:variables

    public void tampil() {     
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT rr.no_rawat, p.no_rkm_medis, p.nm_pasien, DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y') tgl_lhr, IF(p.jk='L','Laki-laki','Perempuan') jk, b.nm_bangsal, DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y') tgl_msk, "
                    + "DATE_FORMAT(ki.tgl_keluar,'%d-%m-%Y') tgl_pulang, IF(rr.nm_dokter_pengirim='','-',rr.nm_dokter_pengirim) dr_pengirim, pj.png_jawab, rr.alasan_masuk_dirawat, rr.ringkasan_riwayat_penyakit, "
                    + "rr.pemeriksaan_fisik, rr.pemeriksaan_penunjang, rr.terapi_pengobatan, rr.diagnosa_utama, rr.diagnosa_sekunder, rr.tindakan_prosedur, ki.stts_pulang, rr.keadaan_umum, rr.kesadaran, rr.gcs, rr.tekanan_darah, "
                    + "rr.suhu, rr.nadi, rr.frekuensi_nafas, rr.catatan_penting, rr.terapi_pulang, rr.pengobatan_dilanjutkan, rr.dokter_luar_lanjutan dr_luar, rr.tgl_kontrol_poliklinik tgl_kontrol, d.nm_dokter dpjp, "
                    + "rr.cek_tgl_kontrol, rr.edukasi, rr.penanggung_jwb_pasien, rr.nip_penyimpan FROM ringkasan_pulang_ranap rr "
                    + "INNER JOIN kamar_inap ki on ki.no_rawat=rr.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal "
                    + "INNER JOIN reg_periksa rp on rp.no_rawat=rr.no_rawat INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "INNER JOIN dpjp_ranap dr on dr.no_rawat=ki.no_rawat INNER JOIN dokter d on d.kd_dokter=dr.kd_dokter where "
                    + "rr.no_rawat like ? or "
                    + "p.no_rkm_medis like ? or "
                    + "p.nm_pasien like ? or "
                    + "IF (p.jk = 'L','Laki-laki','Perempuan') like ? or "
                    + "b.nm_bangsal like ? or "
                    + "IF (rr.nm_dokter_pengirim = '','-',rr.nm_dokter_pengirim) like ? or "
                    + "pj.png_jawab like ? or "
                    + "rr.alasan_masuk_dirawat like ? or "
                    + "rr.ringkasan_riwayat_penyakit like ? or "
                    + "rr.pemeriksaan_fisik like ? or "
                    + "rr.pemeriksaan_penunjang like ? or "
                    + "rr.terapi_pengobatan like ? or "
                    + "rr.diagnosa_utama like ? or "
                    + "rr.diagnosa_sekunder like ? or "
                    + "rr.tindakan_prosedur like ? or "
                    + "ki.stts_pulang like ? or "
                    + "rr.keadaan_umum like ? or "
                    + "rr.kesadaran like ? or "
                    + "rr.gcs like ? or "
                    + "rr.tekanan_darah like ? or "
                    + "rr.suhu like ? or "
                    + "rr.nadi like ? or "
                    + "rr.frekuensi_nafas like ? or "
                    + "rr.catatan_penting like ? or "
                    + "rr.terapi_pulang like ? or "
                    + "rr.pengobatan_dilanjutkan like ? or "
                    + "rr.dokter_luar_lanjutan like ? or "
                    + "rr.tgl_kontrol_poliklinik like ? or "
                    + "d.nm_dokter like ? or "
                    + "rr.penanggung_jwb_pasien like ? ORDER BY rr.no_rawat DESC LIMIT 50");
            try {
                ps.setString(1, "%" + TCari.getText().trim() + "%");
                ps.setString(2, "%" + TCari.getText().trim() + "%");
                ps.setString(3, "%" + TCari.getText().trim() + "%");
                ps.setString(4, "%" + TCari.getText().trim() + "%");
                ps.setString(5, "%" + TCari.getText().trim() + "%");
                ps.setString(6, "%" + TCari.getText().trim() + "%");
                ps.setString(7, "%" + TCari.getText().trim() + "%");
                ps.setString(8, "%" + TCari.getText().trim() + "%");
                ps.setString(9, "%" + TCari.getText().trim() + "%");
                ps.setString(10, "%" + TCari.getText().trim() + "%");                
                ps.setString(11, "%" + TCari.getText().trim() + "%");
                ps.setString(12, "%" + TCari.getText().trim() + "%");
                ps.setString(13, "%" + TCari.getText().trim() + "%");
                ps.setString(14, "%" + TCari.getText().trim() + "%");
                ps.setString(15, "%" + TCari.getText().trim() + "%");
                ps.setString(16, "%" + TCari.getText().trim() + "%");
                ps.setString(17, "%" + TCari.getText().trim() + "%");
                ps.setString(18, "%" + TCari.getText().trim() + "%");
                ps.setString(19, "%" + TCari.getText().trim() + "%");
                ps.setString(20, "%" + TCari.getText().trim() + "%");                
                ps.setString(21, "%" + TCari.getText().trim() + "%");
                ps.setString(22, "%" + TCari.getText().trim() + "%");
                ps.setString(23, "%" + TCari.getText().trim() + "%");
                ps.setString(24, "%" + TCari.getText().trim() + "%");
                ps.setString(25, "%" + TCari.getText().trim() + "%");
                ps.setString(26, "%" + TCari.getText().trim() + "%");
                ps.setString(27, "%" + TCari.getText().trim() + "%");
                ps.setString(28, "%" + TCari.getText().trim() + "%");
                ps.setString(29, "%" + TCari.getText().trim() + "%");
                ps.setString(30, "%" + TCari.getText().trim() + "%");
                rs = ps.executeQuery();               
                while (rs.next()) {
                    tabMode.addRow(new Object[]{
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("tgl_lhr"),
                        rs.getString("jk"),                        
                        rs.getString("tgl_msk"),
                        rs.getString("tgl_pulang"),
                        rs.getString("nm_bangsal"),
                        rs.getString("dr_pengirim"),
                        rs.getString("png_jawab"),
                        rs.getString("alasan_masuk_dirawat"),
                        rs.getString("ringkasan_riwayat_penyakit"),
                        rs.getString("pemeriksaan_fisik"),
                        rs.getString("pemeriksaan_penunjang"),
                        rs.getString("terapi_pengobatan"),
                        rs.getString("diagnosa_utama"),
                        rs.getString("diagnosa_sekunder"),
                        rs.getString("tindakan_prosedur"),
                        rs.getString("stts_pulang"),
                        rs.getString("keadaan_umum"),
                        rs.getString("kesadaran"),
                        rs.getString("gcs"),
                        rs.getString("tekanan_darah"),
                        rs.getString("suhu"),
                        rs.getString("nadi"),
                        rs.getString("frekuensi_nafas"),
                        rs.getString("catatan_penting"),
                        rs.getString("terapi_pulang"),
                        rs.getString("pengobatan_dilanjutkan"),
                        rs.getString("dr_luar"),
                        rs.getString("tgl_kontrol"),
                        rs.getString("dpjp"),
                        rs.getString("cek_tgl_kontrol"),
                        rs.getString("edukasi"),
                        rs.getString("penanggung_jwb_pasien"),
                        rs.getString("nip_penyimpan")
                    });                    
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println("simrskhanza.DlgRingkasanPulangRanap.tampil() : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText(""+tabMode.getRowCount());
    }

    public void emptTeks() {  
        TNoRM.setText("");
        TNoRW.setText("");
        TNmPasien.setText("");
        TTglLhr.setText("");
        TJK.setText("");
        TTglMsk.setText("");
        TTglPulang.setText("");
        TRuangrawat.setText("");
        TCaraBayar.setText("");
        Tdpjp.setText("");
        TNmDokter.setText("");
        TAlasanDirawat.setText("");
        TRingkasanRiwayat.setText("");
        TPemeriksaanFisik.setText("");
        TPemeriksaanPenunjang.setText("");
        TTerapiPengobatan.setText("");
        TDiagUtama.setText("");
        TDiagSekunder.setText("");
        TTindakan.setText("");
        TKeadaanumum.setText("");
        TKesadaran.setText("");
        TTensi.setText("");
        TSuhu.setText("");
        TNadi.setText("");
        TFrekuensiNafas.setText("");
        Tgcs.setText("");
        cmbLanjutan.setSelectedIndex(0);
        TDokterLuar.setText("");
        TKondisiPulang.setText("");
        TCatatan.setText("");
        TTerapiPulang.setText("");
        chkTglKontrol.setSelected(false);
        TglKontrol.setDate(new Date());
        TglKontrol.setEnabled(false);
        Tedukasi.setText("");
        TKlgPasien.setText("");
    }

    private void getData() {
        cekTgl = "";
        
        if (tbRingkasan.getSelectedRow() != -1) {
            TNoRW.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 0).toString());
            TNoRM.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 1).toString());
            TNmPasien.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 2).toString());
            TTglLhr.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 3).toString());
            TJK.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 4).toString());
            TTglMsk.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 5).toString());
            TTglPulang.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 6).toString().replaceAll("00-00-0000", "-"));
            TRuangrawat.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 7).toString());
            TCaraBayar.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 9).toString());
            TNmDokter.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 8).toString());
            TAlasanDirawat.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 10).toString());
            TRingkasanRiwayat.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 11).toString());
            TPemeriksaanFisik.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 12).toString());
            TPemeriksaanPenunjang.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 13).toString());
            TTerapiPengobatan.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 14).toString());
            TDiagUtama.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 15).toString());
            TDiagSekunder.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 16).toString());
            TTindakan.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 17).toString());
            TKondisiPulang.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 18).toString());
            TKeadaanumum.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 19).toString());
            TKesadaran.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 20).toString());
            TTensi.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 22).toString());
            TSuhu.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 23).toString());
            TNadi.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 24).toString());
            TFrekuensiNafas.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 25).toString());
            Tgcs.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 21).toString());
            cmbLanjutan.setSelectedItem(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 28).toString());
            TDokterLuar.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 29).toString());            
            TCatatan.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 26).toString());
            TTerapiPulang.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 27).toString());
            Tdpjp.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 31).toString());
            cekTgl = tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 32).toString();            
            Tedukasi.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 33).toString());
            TKlgPasien.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 34).toString());
            
            if (cekTgl.equals("tidak")) {
                chkTglKontrol.setSelected(false);
                TglKontrol.setDate(new Date());
                TglKontrol.setEnabled(false);
            } else {
                chkTglKontrol.setSelected(true);
                Valid.SetTgl(TglKontrol, tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 30).toString());
                TglKontrol.setEnabled(true);
            }            
        }
    }
    
    public void setPasien(String norawat) {
        TNoRW.setText(norawat);
        TCari.setText(norawat);
        cekPasien();
    }
    
    public void cekPasien() {
        try {
            psPasien = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y') tgl_lhr, "
                    + "IF(p.jk='L','Laki-laki','Perempuan') jk, DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y') tgl_msk, DATE_FORMAT(ki.tgl_keluar,'%d-%m-%Y') tgl_pulang, "
                    + "b.nm_bangsal, pj.png_jawab, ki.stts_pulang, ifnull(d.nm_dokter,'-') dpjp from reg_periksa rp inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "inner join kamar_inap ki on ki.no_rawat=rp.no_rawat inner join kamar k on k.kd_kamar=ki.kd_kamar inner join bangsal b on b.kd_bangsal=k.kd_bangsal "
                    + "inner join penjab pj ON pj.kd_pj = rp.kd_pj left join dpjp_ranap dr on dr.no_rawat=ki.no_rawat left join dokter d on d.kd_dokter=dr.kd_dokter where "
                    + "rp.no_rawat like '%" + TNoRW.getText() + "%' order by ki.tgl_masuk desc, ki.jam_masuk desc limit 1");            
            try {
                rsPasien = psPasien.executeQuery();              
                while (rsPasien.next()) {
                    TNoRM.setText(rsPasien.getString("no_rkm_medis"));
                    TNmPasien.setText(rsPasien.getString("nm_pasien"));
                    TTglLhr.setText(rsPasien.getString("tgl_lhr"));
                    TJK.setText(rsPasien.getString("jk"));
                    TTglMsk.setText(rsPasien.getString("tgl_msk"));
                    TTglPulang.setText(rsPasien.getString("tgl_pulang").replaceAll("00-00-0000", "-"));
                    TRuangrawat.setText(rsPasien.getString("nm_bangsal"));
                    TCaraBayar.setText(rsPasien.getString("png_jawab"));
                    TKondisiPulang.setText(rsPasien.getString("stts_pulang"));
                    Tdpjp.setText(rsPasien.getString("dpjp"));
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsPasien != null) {
                    rsPasien.close();
                }
                if (psPasien != null) {
                    psPasien.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.getringkasanpulangranap());
        BtnHapus.setEnabled(akses.getringkasanpulangranap());
        BtnGanti.setEnabled(akses.getringkasanpulangranap());
    }
}
