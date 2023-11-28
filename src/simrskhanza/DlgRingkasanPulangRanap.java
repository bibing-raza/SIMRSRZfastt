package simrskhanza;

import com.sun.jna.platform.win32.OaIdl;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import inventory.DlgCatatanResep;
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
import laporan.DlgHasilExpertiseRadiologi;
import laporan.DlgHasilLIS;
import rekammedis.DlgVerifikasiCPPT;
import simrskhanza.DlgCariDokter;

/**
 *
 * @author dosen
 */
public class DlgRingkasanPulangRanap extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabModeResiko;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps, psPasien, psdiag, pspros, psLaprm, psFakIGD, psRes;
    private ResultSet rs, rsPasien, rsdiag, rspros, rsLaprm, rsFakIGD, rsRes;
    private int i = 0, x = 0, totskorTriase = 0, skorGZ1 = 0, skorYaGZ1 = 0, skorGZ2 = 0, skor = 0;
    public DlgCariDokter dokter = new DlgCariDokter(null, false);
    private String kontrolPoli = "", cekTgl = "", diagnosa = "", tindakan = "", kodekamar = "", skorAsesIGD = "", kesimpulanGZanak = "",
            kesimpulanGZDewasa = "", faktorresikoigd = "", TotSkorGZD = "", TotSkorGZA = "", TotSkorRJ = "", kesimpulanResikoJatuh = "";

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
            "Nama DPJP Pasien", "cektgl", "edukasi", "png_jawab_px", "nip_penyimpan", "hasil_pemeriksaan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbRingkasan.setModel(tabMode);
        tbRingkasan.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbRingkasan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 37; i++) {
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
            } else if (i == 36) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbRingkasan.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeResiko=new DefaultTableModel(null,new Object[]{
                "#", "KODE", "ASESMEN", "FAKTOR RESIKO", "SKALA", "SKOR"
            }){
             @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        
        tbFaktorResiko.setModel(tabModeResiko);
        tbFaktorResiko.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbFaktorResiko.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 6; i++) {
            TableColumn column = tbFaktorResiko.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 2) {
                column.setPreferredWidth(67);
            } else if (i == 3) {
                column.setPreferredWidth(265);
            } else if (i == 4) {
                column.setPreferredWidth(265);
            } else if (i == 5) {
                column.setPreferredWidth(40);
            }
        }
        tbFaktorResiko.setDefaultRenderer(Object.class, new WarnaTable());

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
        MnHasilPemeriksaanLab = new javax.swing.JMenuItem();
        MnHasilPemeriksaanRad = new javax.swing.JMenuItem();
        MnIGD = new javax.swing.JMenu();
        MnTriase = new javax.swing.JMenuItem();
        MnAsesmenMedikIGD = new javax.swing.JMenuItem();
        MnAsesmenKeperawatanIGD = new javax.swing.JMenuItem();
        MnAsesmenMedikObstetriIGD = new javax.swing.JMenuItem();
        MnAsesmenKebidanan = new javax.swing.JMenuItem();
        MnDiagnosa = new javax.swing.JMenuItem();
        MnCetakRingkasan = new javax.swing.JMenuItem();
        TabTindakanPencegahan = new javax.swing.JTabbedPane();
        panelBiasa6 = new widget.PanelBiasa();
        TabPencegahanDewasa = new javax.swing.JTabbedPane();
        panelBiasa8 = new widget.PanelBiasa();
        dewasaA = new widget.TextArea();
        panelBiasa9 = new widget.PanelBiasa();
        dewasaB = new widget.TextArea();
        panelBiasa10 = new widget.PanelBiasa();
        dewasaC = new widget.TextArea();
        panelBiasa7 = new widget.PanelBiasa();
        TabPencegahanAnak = new javax.swing.JTabbedPane();
        panelBiasa14 = new widget.PanelBiasa();
        anakA = new widget.TextArea();
        panelBiasa15 = new widget.PanelBiasa();
        anakB = new widget.TextArea();
        Scroll8 = new widget.ScrollPane();
        tbFaktorResiko = new widget.Table();
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
        jLabel57 = new widget.Label();
        BtnPastePenunjang = new widget.Button();
        jLabel38 = new widget.Label();
        jLabel46 = new widget.Label();
        jLabel58 = new widget.Label();
        jLabel59 = new widget.Label();
        Scroll28 = new widget.ScrollPane();
        THasil = new widget.TextArea();
        BtnPasteHasil = new widget.Button();
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
        BtnVerif = new widget.Button();
        BtnResep = new widget.Button();
        BtnKeluar = new widget.Button();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnHasilPemeriksaanLab.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHasilPemeriksaanLab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnHasilPemeriksaanLab.setText("Hasil Pemeriksaan Lab.");
        MnHasilPemeriksaanLab.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHasilPemeriksaanLab.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHasilPemeriksaanLab.setIconTextGap(5);
        MnHasilPemeriksaanLab.setName("MnHasilPemeriksaanLab"); // NOI18N
        MnHasilPemeriksaanLab.setPreferredSize(new java.awt.Dimension(180, 26));
        MnHasilPemeriksaanLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHasilPemeriksaanLabActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnHasilPemeriksaanLab);

        MnHasilPemeriksaanRad.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHasilPemeriksaanRad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnHasilPemeriksaanRad.setText("Hasil Pemeriksaan Radiologi");
        MnHasilPemeriksaanRad.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHasilPemeriksaanRad.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHasilPemeriksaanRad.setIconTextGap(5);
        MnHasilPemeriksaanRad.setName("MnHasilPemeriksaanRad"); // NOI18N
        MnHasilPemeriksaanRad.setPreferredSize(new java.awt.Dimension(180, 26));
        MnHasilPemeriksaanRad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHasilPemeriksaanRadActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnHasilPemeriksaanRad);

        MnIGD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnIGD.setText("RM Gawat Darurat (IGD)");
        MnIGD.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnIGD.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnIGD.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnIGD.setIconTextGap(5);
        MnIGD.setName("MnIGD"); // NOI18N
        MnIGD.setOpaque(true);
        MnIGD.setPreferredSize(new java.awt.Dimension(180, 26));

        MnTriase.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTriase.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnTriase.setText("Triase IGD");
        MnTriase.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnTriase.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnTriase.setIconTextGap(5);
        MnTriase.setName("MnTriase"); // NOI18N
        MnTriase.setPreferredSize(new java.awt.Dimension(190, 26));
        MnTriase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTriaseActionPerformed(evt);
            }
        });
        MnIGD.add(MnTriase);

        MnAsesmenMedikIGD.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnAsesmenMedikIGD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnAsesmenMedikIGD.setText("Assesmen Medik IGD");
        MnAsesmenMedikIGD.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnAsesmenMedikIGD.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnAsesmenMedikIGD.setIconTextGap(5);
        MnAsesmenMedikIGD.setName("MnAsesmenMedikIGD"); // NOI18N
        MnAsesmenMedikIGD.setPreferredSize(new java.awt.Dimension(190, 26));
        MnAsesmenMedikIGD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnAsesmenMedikIGDActionPerformed(evt);
            }
        });
        MnIGD.add(MnAsesmenMedikIGD);

        MnAsesmenKeperawatanIGD.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnAsesmenKeperawatanIGD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnAsesmenKeperawatanIGD.setText("Assesmen Keperawatan IGD");
        MnAsesmenKeperawatanIGD.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnAsesmenKeperawatanIGD.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnAsesmenKeperawatanIGD.setIconTextGap(5);
        MnAsesmenKeperawatanIGD.setName("MnAsesmenKeperawatanIGD"); // NOI18N
        MnAsesmenKeperawatanIGD.setPreferredSize(new java.awt.Dimension(190, 26));
        MnAsesmenKeperawatanIGD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnAsesmenKeperawatanIGDActionPerformed(evt);
            }
        });
        MnIGD.add(MnAsesmenKeperawatanIGD);

        MnAsesmenMedikObstetriIGD.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnAsesmenMedikObstetriIGD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnAsesmenMedikObstetriIGD.setText("Asesmen Medik Obstetri");
        MnAsesmenMedikObstetriIGD.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnAsesmenMedikObstetriIGD.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnAsesmenMedikObstetriIGD.setIconTextGap(5);
        MnAsesmenMedikObstetriIGD.setName("MnAsesmenMedikObstetriIGD"); // NOI18N
        MnAsesmenMedikObstetriIGD.setPreferredSize(new java.awt.Dimension(190, 26));
        MnAsesmenMedikObstetriIGD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnAsesmenMedikObstetriIGDActionPerformed(evt);
            }
        });
        MnIGD.add(MnAsesmenMedikObstetriIGD);

        MnAsesmenKebidanan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnAsesmenKebidanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnAsesmenKebidanan.setText("Asesmen Kebidanan (Ponek)");
        MnAsesmenKebidanan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnAsesmenKebidanan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnAsesmenKebidanan.setIconTextGap(5);
        MnAsesmenKebidanan.setName("MnAsesmenKebidanan"); // NOI18N
        MnAsesmenKebidanan.setPreferredSize(new java.awt.Dimension(190, 26));
        MnAsesmenKebidanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnAsesmenKebidananActionPerformed(evt);
            }
        });
        MnIGD.add(MnAsesmenKebidanan);

        jPopupMenu1.add(MnIGD);

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

        TabTindakanPencegahan.setBackground(new java.awt.Color(255, 255, 254));
        TabTindakanPencegahan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TabTindakanPencegahan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabTindakanPencegahan.setName("TabTindakanPencegahan"); // NOI18N

        panelBiasa6.setName("panelBiasa6"); // NOI18N
        panelBiasa6.setLayout(new java.awt.BorderLayout());

        TabPencegahanDewasa.setBackground(new java.awt.Color(255, 255, 254));
        TabPencegahanDewasa.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TabPencegahanDewasa.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabPencegahanDewasa.setName("TabPencegahanDewasa"); // NOI18N

        panelBiasa8.setName("panelBiasa8"); // NOI18N
        panelBiasa8.setLayout(new java.awt.BorderLayout());

        dewasaA.setEditable(false);
        dewasaA.setBackground(new java.awt.Color(255, 255, 255));
        dewasaA.setColumns(20);
        dewasaA.setRows(5);
        dewasaA.setText("1. Oreintasi lingkungan\n2. Posisi tempat tidur rendah dan terkunci\n3. Rel tempat tidur dipasang (dinaikkan)\n4. Pencahayaan adekuat\n5. Edukasi pencegahan jatuh");
        dewasaA.setName("dewasaA"); // NOI18N
        dewasaA.setOpaque(true);
        panelBiasa8.add(dewasaA, java.awt.BorderLayout.CENTER);

        TabPencegahanDewasa.addTab("Pencegahan Umum (A)", panelBiasa8);

        panelBiasa9.setName("panelBiasa9"); // NOI18N
        panelBiasa9.setLayout(new java.awt.BorderLayout());

        dewasaB.setEditable(false);
        dewasaB.setBackground(new java.awt.Color(255, 255, 255));
        dewasaB.setColumns(20);
        dewasaB.setRows(5);
        dewasaB.setText("1. Lakukan semua pencegahan umum (A)\n2. Menawarkan bantuan untuk ambulansi\n3. Beri tanda identifikasi dengan pin/kancing kuning pada gelang identitas");
        dewasaB.setName("dewasaB"); // NOI18N
        dewasaB.setOpaque(true);
        panelBiasa9.add(dewasaB, java.awt.BorderLayout.CENTER);

        TabPencegahanDewasa.addTab("Pencegahan Resiko Sedang (B)", panelBiasa9);

        panelBiasa10.setName("panelBiasa10"); // NOI18N
        panelBiasa10.setLayout(new java.awt.BorderLayout());

        dewasaC.setEditable(false);
        dewasaC.setBackground(new java.awt.Color(255, 255, 255));
        dewasaC.setColumns(20);
        dewasaC.setRows(5);
        dewasaC.setText("1. Lakukan semua pencegahan umum A dan B\n2. Beri tanda segitiga warna kuning pada bed pasien\n3. Kunjungi dan monitor setiap 1 jam\n4. Pastikan pasien menggunakan alat bantu\n5. Libatkan keluarga untuk mengawasi pasien");
        dewasaC.setName("dewasaC"); // NOI18N
        dewasaC.setOpaque(true);
        panelBiasa10.add(dewasaC, java.awt.BorderLayout.CENTER);

        TabPencegahanDewasa.addTab("Pencegahan Resiko Tinggi (C)", panelBiasa10);

        panelBiasa6.add(TabPencegahanDewasa, java.awt.BorderLayout.CENTER);

        TabTindakanPencegahan.addTab("DEWASA", panelBiasa6);

        panelBiasa7.setName("panelBiasa7"); // NOI18N
        panelBiasa7.setLayout(new java.awt.BorderLayout());

        TabPencegahanAnak.setBackground(new java.awt.Color(255, 255, 254));
        TabPencegahanAnak.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TabPencegahanAnak.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabPencegahanAnak.setName("TabPencegahanAnak"); // NOI18N

        panelBiasa14.setName("panelBiasa14"); // NOI18N
        panelBiasa14.setLayout(new java.awt.BorderLayout());

        anakA.setEditable(false);
        anakA.setBackground(new java.awt.Color(255, 255, 255));
        anakA.setColumns(20);
        anakA.setRows(5);
        anakA.setText("1. Oreintasi lingkungan\n2. Posisi tempat tidur rendah dan terkunci\n3. Rel tempat tidur dipasang (dinaikkan)\n4. Bel & barang pribadi dalam jangkauan\n5. Pencahayaan adekuat\n6. Edukasi pencegahan jatuh");
        anakA.setName("anakA"); // NOI18N
        anakA.setOpaque(true);
        panelBiasa14.add(anakA, java.awt.BorderLayout.CENTER);

        TabPencegahanAnak.addTab("Pencegahan Umum (A)", panelBiasa14);

        panelBiasa15.setName("panelBiasa15"); // NOI18N
        panelBiasa15.setLayout(new java.awt.BorderLayout());

        anakB.setEditable(false);
        anakB.setBackground(new java.awt.Color(255, 255, 255));
        anakB.setColumns(20);
        anakB.setRows(5);
        anakB.setText("1. Lakukan semua pencegahan umum (A)\n2. Beri tanda segitiga warna kuning pada bed/RM\n3. Beri tanda identifikasi dengan pin/kancing kuning pada gelang identitas\n4. Kunjungi dan monitor setiap 1 jam\n5. Libatkan keluarga untuk mengawasi pasien");
        anakB.setName("anakB"); // NOI18N
        anakB.setOpaque(true);
        panelBiasa15.add(anakB, java.awt.BorderLayout.CENTER);

        TabPencegahanAnak.addTab("Pencegahan Resiko Tinggi (B)", panelBiasa15);

        panelBiasa7.add(TabPencegahanAnak, java.awt.BorderLayout.CENTER);

        TabTindakanPencegahan.addTab("ANAK", panelBiasa7);

        Scroll8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " ASSESMEN RESIKO JATUH : ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll8.setName("Scroll8"); // NOI18N
        Scroll8.setOpaque(true);

        tbFaktorResiko.setName("tbFaktorResiko"); // NOI18N
        Scroll8.setViewportView(tbFaktorResiko);

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

        panelisi1.setToolTipText("Klik kanan pada area ini untuk melihat hasil pemeriksaan penunjang medis");
        panelisi1.setComponentPopupMenu(jPopupMenu1);
        panelisi1.setName("panelisi1"); // NOI18N
        panelisi1.setPreferredSize(new java.awt.Dimension(1087, 1340));
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
        TAlasanDirawat.setPreferredSize(new java.awt.Dimension(190, 2000));
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
        TRingkasanRiwayat.setPreferredSize(new java.awt.Dimension(190, 2000));
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
        TPemeriksaanFisik.setPreferredSize(new java.awt.Dimension(190, 2000));
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
        TPemeriksaanPenunjang.setPreferredSize(new java.awt.Dimension(190, 14000));
        TPemeriksaanPenunjang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPemeriksaanPenunjangKeyPressed(evt);
            }
        });
        Scroll18.setViewportView(TPemeriksaanPenunjang);

        panelisi1.add(Scroll18);
        Scroll18.setBounds(135, 445, 590, 880);

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
        TTerapiPengobatan.setPreferredSize(new java.awt.Dimension(190, 2000));
        TTerapiPengobatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTerapiPengobatanKeyPressed(evt);
            }
        });
        Scroll19.setViewportView(TTerapiPengobatan);

        panelisi1.add(Scroll19);
        Scroll19.setBounds(865, 190, 590, 80);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Terapi Pengobatan ");
        jLabel21.setName("jLabel21"); // NOI18N
        panelisi1.add(jLabel21);
        jLabel21.setBounds(730, 190, 130, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("selama dirawat & efek ");
        jLabel22.setName("jLabel22"); // NOI18N
        panelisi1.add(jLabel22);
        jLabel22.setBounds(730, 205, 130, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("samping (bila ada) :");
        jLabel23.setName("jLabel23"); // NOI18N
        panelisi1.add(jLabel23);
        jLabel23.setBounds(730, 220, 130, 23);

        Scroll20.setName("Scroll20"); // NOI18N
        Scroll20.setOpaque(true);

        TDiagUtama.setColumns(20);
        TDiagUtama.setRows(5);
        TDiagUtama.setName("TDiagUtama"); // NOI18N
        TDiagUtama.setPreferredSize(new java.awt.Dimension(190, 2000));
        TDiagUtama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDiagUtamaKeyPressed(evt);
            }
        });
        Scroll20.setViewportView(TDiagUtama);

        panelisi1.add(Scroll20);
        Scroll20.setBounds(865, 275, 590, 80);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Diagnosa Utama/ ");
        jLabel28.setName("jLabel28"); // NOI18N
        panelisi1.add(jLabel28);
        jLabel28.setBounds(730, 275, 130, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Primer :");
        jLabel24.setName("jLabel24"); // NOI18N
        panelisi1.add(jLabel24);
        jLabel24.setBounds(730, 290, 130, 23);

        Scroll21.setName("Scroll21"); // NOI18N
        Scroll21.setOpaque(true);

        TDiagSekunder.setColumns(20);
        TDiagSekunder.setRows(5);
        TDiagSekunder.setName("TDiagSekunder"); // NOI18N
        TDiagSekunder.setPreferredSize(new java.awt.Dimension(190, 2000));
        TDiagSekunder.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDiagSekunderKeyPressed(evt);
            }
        });
        Scroll21.setViewportView(TDiagSekunder);

        panelisi1.add(Scroll21);
        Scroll21.setBounds(865, 360, 590, 70);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Diagnosa Sekunder :");
        jLabel25.setName("jLabel25"); // NOI18N
        panelisi1.add(jLabel25);
        jLabel25.setBounds(730, 360, 130, 23);

        Scroll22.setName("Scroll22"); // NOI18N
        Scroll22.setOpaque(true);

        TTindakan.setColumns(20);
        TTindakan.setRows(5);
        TTindakan.setName("TTindakan"); // NOI18N
        TTindakan.setPreferredSize(new java.awt.Dimension(190, 2000));
        TTindakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTindakanKeyPressed(evt);
            }
        });
        Scroll22.setViewportView(TTindakan);

        panelisi1.add(Scroll22);
        Scroll22.setBounds(865, 495, 590, 80);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Tindakan Prosedur :");
        jLabel26.setName("jLabel26"); // NOI18N
        panelisi1.add(jLabel26);
        jLabel26.setBounds(730, 495, 130, 23);

        Scroll23.setName("Scroll23"); // NOI18N
        Scroll23.setOpaque(true);

        TKeadaanumum.setColumns(20);
        TKeadaanumum.setRows(5);
        TKeadaanumum.setName("TKeadaanumum"); // NOI18N
        TKeadaanumum.setPreferredSize(new java.awt.Dimension(190, 2000));
        TKeadaanumum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKeadaanumumKeyPressed(evt);
            }
        });
        Scroll23.setViewportView(TKeadaanumum);

        panelisi1.add(Scroll23);
        Scroll23.setBounds(865, 580, 590, 80);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("Keadaan Umum :");
        jLabel27.setName("jLabel27"); // NOI18N
        panelisi1.add(jLabel27);
        jLabel27.setBounds(730, 580, 130, 23);

        Scroll24.setName("Scroll24"); // NOI18N
        Scroll24.setOpaque(true);

        TKesadaran.setColumns(20);
        TKesadaran.setRows(5);
        TKesadaran.setName("TKesadaran"); // NOI18N
        TKesadaran.setPreferredSize(new java.awt.Dimension(190, 2000));
        TKesadaran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKesadaranKeyPressed(evt);
            }
        });
        Scroll24.setViewportView(TKesadaran);

        panelisi1.add(Scroll24);
        Scroll24.setBounds(865, 665, 590, 80);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("Kesadaran :");
        jLabel29.setName("jLabel29"); // NOI18N
        panelisi1.add(jLabel29);
        jLabel29.setBounds(730, 665, 130, 23);

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Tanda Vital :");
        jLabel30.setName("jLabel30"); // NOI18N
        panelisi1.add(jLabel30);
        jLabel30.setBounds(730, 780, 130, 23);

        TTensi.setForeground(new java.awt.Color(0, 0, 0));
        TTensi.setToolTipText("");
        TTensi.setName("TTensi"); // NOI18N
        TTensi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTensiKeyPressed(evt);
            }
        });
        panelisi1.add(TTensi);
        TTensi.setBounds(865, 800, 70, 23);

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("Suhu :");
        jLabel31.setName("jLabel31"); // NOI18N
        panelisi1.add(jLabel31);
        jLabel31.setBounds(990, 800, 50, 23);

        TSuhu.setForeground(new java.awt.Color(0, 0, 0));
        TSuhu.setToolTipText("");
        TSuhu.setName("TSuhu"); // NOI18N
        TSuhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TSuhuKeyPressed(evt);
            }
        });
        panelisi1.add(TSuhu);
        TSuhu.setBounds(1045, 800, 70, 23);

        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Nadi :");
        jLabel32.setName("jLabel32"); // NOI18N
        panelisi1.add(jLabel32);
        jLabel32.setBounds(1145, 800, 50, 23);

        TNadi.setForeground(new java.awt.Color(0, 0, 0));
        TNadi.setToolTipText("");
        TNadi.setName("TNadi"); // NOI18N
        TNadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNadiKeyPressed(evt);
            }
        });
        panelisi1.add(TNadi);
        TNadi.setBounds(1200, 800, 70, 23);

        jLabel49.setForeground(new java.awt.Color(0, 0, 0));
        jLabel49.setText("Tekanan darah :");
        jLabel49.setName("jLabel49"); // NOI18N
        panelisi1.add(jLabel49);
        jLabel49.setBounds(730, 800, 130, 23);

        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("Frekuensi Nafas :");
        jLabel33.setName("jLabel33"); // NOI18N
        panelisi1.add(jLabel33);
        jLabel33.setBounds(730, 828, 130, 23);

        TFrekuensiNafas.setForeground(new java.awt.Color(0, 0, 0));
        TFrekuensiNafas.setToolTipText("");
        TFrekuensiNafas.setName("TFrekuensiNafas"); // NOI18N
        TFrekuensiNafas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TFrekuensiNafasKeyPressed(evt);
            }
        });
        panelisi1.add(TFrekuensiNafas);
        TFrekuensiNafas.setBounds(865, 828, 70, 23);

        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setText("GCS :");
        jLabel37.setName("jLabel37"); // NOI18N
        panelisi1.add(jLabel37);
        jLabel37.setBounds(990, 828, 50, 23);

        Tgcs.setForeground(new java.awt.Color(0, 0, 0));
        Tgcs.setToolTipText("");
        Tgcs.setName("Tgcs"); // NOI18N
        Tgcs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgcsKeyPressed(evt);
            }
        });
        panelisi1.add(Tgcs);
        Tgcs.setBounds(1045, 828, 235, 23);

        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Pengobatan Lanjutan :");
        jLabel34.setName("jLabel34"); // NOI18N
        panelisi1.add(jLabel34);
        jLabel34.setBounds(730, 856, 130, 23);

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
        cmbLanjutan.setBounds(865, 856, 110, 23);

        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("Nama Dokter :");
        jLabel35.setName("jLabel35"); // NOI18N
        panelisi1.add(jLabel35);
        jLabel35.setBounds(975, 856, 85, 23);

        TDokterLuar.setForeground(new java.awt.Color(0, 0, 0));
        TDokterLuar.setToolTipText("");
        TDokterLuar.setName("TDokterLuar"); // NOI18N
        TDokterLuar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDokterLuarKeyPressed(evt);
            }
        });
        panelisi1.add(TDokterLuar);
        TDokterLuar.setBounds(1065, 856, 385, 23);

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
        chkTglKontrol.setBounds(730, 884, 130, 23);

        TglKontrol.setEditable(false);
        TglKontrol.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01-11-2023" }));
        TglKontrol.setDisplayFormat("dd-MM-yyyy");
        TglKontrol.setName("TglKontrol"); // NOI18N
        TglKontrol.setOpaque(false);
        TglKontrol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglKontrolKeyPressed(evt);
            }
        });
        panelisi1.add(TglKontrol);
        TglKontrol.setBounds(865, 884, 90, 23);

        jLabel41.setForeground(new java.awt.Color(0, 0, 0));
        jLabel41.setText("Kondisi Waktu Pulang :");
        jLabel41.setName("jLabel41"); // NOI18N
        panelisi1.add(jLabel41);
        jLabel41.setBounds(956, 884, 130, 23);

        TKondisiPulang.setEditable(false);
        TKondisiPulang.setForeground(new java.awt.Color(0, 0, 0));
        TKondisiPulang.setToolTipText("");
        TKondisiPulang.setName("TKondisiPulang"); // NOI18N
        panelisi1.add(TKondisiPulang);
        TKondisiPulang.setBounds(1090, 884, 360, 23);

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Catatan Penting ");
        jLabel36.setName("jLabel36"); // NOI18N
        panelisi1.add(jLabel36);
        jLabel36.setBounds(730, 1075, 130, 23);

        Scroll25.setName("Scroll25"); // NOI18N
        Scroll25.setOpaque(true);

        TCatatan.setColumns(20);
        TCatatan.setRows(5);
        TCatatan.setName("TCatatan"); // NOI18N
        TCatatan.setPreferredSize(new java.awt.Dimension(190, 2000));
        TCatatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCatatanKeyPressed(evt);
            }
        });
        Scroll25.setViewportView(TCatatan);

        panelisi1.add(Scroll25);
        Scroll25.setBounds(865, 1075, 590, 71);

        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setText("(kondisi saat ini) :");
        jLabel39.setName("jLabel39"); // NOI18N
        panelisi1.add(jLabel39);
        jLabel39.setBounds(730, 1090, 130, 23);

        jLabel42.setForeground(new java.awt.Color(0, 0, 0));
        jLabel42.setText("Terapi Pulang :");
        jLabel42.setName("jLabel42"); // NOI18N
        panelisi1.add(jLabel42);
        jLabel42.setBounds(730, 1150, 130, 23);

        Scroll27.setName("Scroll27"); // NOI18N
        Scroll27.setOpaque(true);

        TTerapiPulang.setColumns(20);
        TTerapiPulang.setRows(5);
        TTerapiPulang.setName("TTerapiPulang"); // NOI18N
        TTerapiPulang.setPreferredSize(new java.awt.Dimension(190, 8000));
        TTerapiPulang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTerapiPulangKeyPressed(evt);
            }
        });
        Scroll27.setViewportView(TTerapiPulang);

        panelisi1.add(Scroll27);
        Scroll27.setBounds(865, 1150, 590, 175);

        jLabel50.setForeground(new java.awt.Color(0, 0, 0));
        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel50.setText("mmHg");
        jLabel50.setName("jLabel50"); // NOI18N
        panelisi1.add(jLabel50);
        jLabel50.setBounds(940, 800, 40, 23);

        jLabel51.setForeground(new java.awt.Color(0, 0, 0));
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel51.setText("x/menit");
        jLabel51.setName("jLabel51"); // NOI18N
        panelisi1.add(jLabel51);
        jLabel51.setBounds(940, 828, 40, 23);

        jLabel52.setForeground(new java.awt.Color(0, 0, 0));
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel52.setText("°C");
        jLabel52.setName("jLabel52"); // NOI18N
        panelisi1.add(jLabel52);
        jLabel52.setBounds(1120, 800, 30, 23);

        jLabel53.setForeground(new java.awt.Color(0, 0, 0));
        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel53.setText("x/menit");
        jLabel53.setName("jLabel53"); // NOI18N
        panelisi1.add(jLabel53);
        jLabel53.setBounds(1275, 800, 40, 23);

        jLabel54.setForeground(new java.awt.Color(0, 0, 0));
        jLabel54.setText("Edukasi Yang Diberikan :");
        jLabel54.setName("jLabel54"); // NOI18N
        panelisi1.add(jLabel54);
        jLabel54.setBounds(730, 751, 130, 23);

        Tedukasi.setForeground(new java.awt.Color(0, 0, 0));
        Tedukasi.setToolTipText("");
        Tedukasi.setName("Tedukasi"); // NOI18N
        Tedukasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TedukasiKeyPressed(evt);
            }
        });
        panelisi1.add(Tedukasi);
        Tedukasi.setBounds(865, 751, 590, 23);

        jLabel55.setForeground(new java.awt.Color(0, 0, 0));
        jLabel55.setText("Pasien / Keluarga / :");
        jLabel55.setName("jLabel55"); // NOI18N
        panelisi1.add(jLabel55);
        jLabel55.setBounds(730, 435, 130, 23);

        jLabel56.setForeground(new java.awt.Color(0, 0, 0));
        jLabel56.setText("Penanggung Jawab  ");
        jLabel56.setName("jLabel56"); // NOI18N
        panelisi1.add(jLabel56);
        jLabel56.setBounds(720, 450, 140, 23);

        TKlgPasien.setForeground(new java.awt.Color(0, 0, 0));
        TKlgPasien.setToolTipText("");
        TKlgPasien.setName("TKlgPasien"); // NOI18N
        TKlgPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKlgPasienKeyPressed(evt);
            }
        });
        panelisi1.add(TKlgPasien);
        TKlgPasien.setBounds(865, 435, 590, 23);

        jLabel57.setForeground(new java.awt.Color(0, 0, 0));
        jLabel57.setText("Pasien  ");
        jLabel57.setName("jLabel57"); // NOI18N
        panelisi1.add(jLabel57);
        jLabel57.setBounds(720, 465, 140, 23);

        BtnPastePenunjang.setForeground(new java.awt.Color(0, 0, 0));
        BtnPastePenunjang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/paste.png"))); // NOI18N
        BtnPastePenunjang.setMnemonic('L');
        BtnPastePenunjang.setText("Paste");
        BtnPastePenunjang.setToolTipText("Alt+L");
        BtnPastePenunjang.setName("BtnPastePenunjang"); // NOI18N
        BtnPastePenunjang.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPastePenunjang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPastePenunjangActionPerformed(evt);
            }
        });
        panelisi1.add(BtnPastePenunjang);
        BtnPastePenunjang.setBounds(40, 491, 90, 23);

        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("Hasil Pemeriksaan, :");
        jLabel38.setName("jLabel38"); // NOI18N
        panelisi1.add(jLabel38);
        jLabel38.setBounds(730, 912, 130, 23);

        jLabel46.setForeground(new java.awt.Color(0, 0, 0));
        jLabel46.setText("Analisa, Rencana,  ");
        jLabel46.setName("jLabel46"); // NOI18N
        panelisi1.add(jLabel46);
        jLabel46.setBounds(730, 927, 130, 23);

        jLabel58.setForeground(new java.awt.Color(0, 0, 0));
        jLabel58.setText("Penatalaksanaan  ");
        jLabel58.setName("jLabel58"); // NOI18N
        panelisi1.add(jLabel58);
        jLabel58.setBounds(730, 942, 130, 23);

        jLabel59.setForeground(new java.awt.Color(0, 0, 0));
        jLabel59.setText("Pasien  ");
        jLabel59.setName("jLabel59"); // NOI18N
        panelisi1.add(jLabel59);
        jLabel59.setBounds(730, 957, 130, 23);

        Scroll28.setName("Scroll28"); // NOI18N
        Scroll28.setOpaque(true);

        THasil.setColumns(20);
        THasil.setRows(5);
        THasil.setName("THasil"); // NOI18N
        THasil.setPreferredSize(new java.awt.Dimension(190, 10000));
        THasil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                THasilKeyPressed(evt);
            }
        });
        Scroll28.setViewportView(THasil);

        panelisi1.add(Scroll28);
        Scroll28.setBounds(865, 919, 590, 150);

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
        panelisi1.add(BtnPasteHasil);
        BtnPasteHasil.setBounds(780, 987, 80, 23);

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

        BtnVerif.setForeground(new java.awt.Color(0, 0, 0));
        BtnVerif.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/checked.png"))); // NOI18N
        BtnVerif.setMnemonic('V');
        BtnVerif.setText("Verifikasi CPPT");
        BtnVerif.setToolTipText("Alt+V");
        BtnVerif.setName("BtnVerif"); // NOI18N
        BtnVerif.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnVerif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnVerifActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnVerif);

        BtnResep.setForeground(new java.awt.Color(0, 0, 0));
        BtnResep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Vial-Pills.png"))); // NOI18N
        BtnResep.setMnemonic('R');
        BtnResep.setText("Resep Obat");
        BtnResep.setToolTipText("Alt+R");
        BtnResep.setName("BtnResep"); // NOI18N
        BtnResep.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnResep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnResepActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnResep);

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
            
            try {
                Sequel.menyimpan("ringkasan_pulang_ranap", "'" + TNoRW.getText() + "','" + TAlasanDirawat.getText() + "','" + TRingkasanRiwayat.getText() + "',"
                        + "'" + TPemeriksaanFisik.getText() + "','" + Valid.mysql_real_escape_stringERM(TPemeriksaanPenunjang.getText()) + "','" + TTerapiPengobatan.getText() + "',"
                        + "'" + TDiagUtama.getText() + "','" + TDiagSekunder.getText() + "','" + TKeadaanumum.getText() + "','" + TKesadaran.getText() + "',"
                        + "'" + TTensi.getText() + "','" + TSuhu.getText() + "','" + TNadi.getText() + "','" + TFrekuensiNafas.getText() + "','" + TCatatan.getText() + "',"
                        + "'" + TTerapiPulang.getText() + "','" + cmbLanjutan.getSelectedItem().toString() + "','" + kontrolPoli + "',"
                        + "'" + TNmDokter.getText() + "','" + Tgcs.getText() + "','" + TTindakan.getText() + "','" + TDokterLuar.getText() + "',"
                        + "'" + cekTgl + "','" + Tedukasi.getText() + "','" + TKlgPasien.getText() + "','" + akses.getkode() + "','" + THasil.getText() + "'", "Ringkasan Pulang Pasien Rawat Inap");
            } catch (Exception e) {
                System.out.println("Simpan Ringkasan Pulang Pasien : " + e);
            }
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

            try {
                if (akses.getkode().equals(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 35).toString())) {
                    Sequel.mengedit("ringkasan_pulang_ranap", "no_rawat='" + TNoRW.getText() + "'", "alasan_masuk_dirawat='" + TAlasanDirawat.getText() + "', "
                            + "ringkasan_riwayat_penyakit='" + TRingkasanRiwayat.getText() + "', pemeriksaan_fisik='" + TPemeriksaanFisik.getText() + "', "
                            + "pemeriksaan_penunjang='" + Valid.mysql_real_escape_stringERM(TPemeriksaanPenunjang.getText()) + "',terapi_pengobatan='" + TTerapiPengobatan.getText() + "',"
                            + "diagnosa_utama='" + TDiagUtama.getText() + "',diagnosa_sekunder='" + TDiagSekunder.getText() + "',keadaan_umum='" + TKeadaanumum.getText() + "',"
                            + "kesadaran='" + TKesadaran.getText() + "',tekanan_darah='" + TTensi.getText() + "',suhu='" + TSuhu.getText() + "',nadi='" + TNadi.getText() + "',"
                            + "frekuensi_nafas='" + TFrekuensiNafas.getText() + "',catatan_penting='" + TCatatan.getText() + "',terapi_pulang='" + TTerapiPulang.getText() + "',"
                            + "pengobatan_dilanjutkan='" + cmbLanjutan.getSelectedItem().toString() + "',tgl_kontrol_poliklinik='" + kontrolPoli + "',"
                            + "nm_dokter_pengirim='" + TNmDokter.getText() + "',GCS='" + Tgcs.getText() + "',tindakan_prosedur='" + TTindakan.getText() + "',"
                            + "dokter_luar_lanjutan='" + TDokterLuar.getText() + "',cek_tgl_kontrol='" + cekTgl + "',edukasi='" + Tedukasi.getText() + "',"
                            + "penanggung_jwb_pasien='" + TKlgPasien.getText() + "',hasil_pemeriksaan='" + THasil.getText() + "'");

                    tampil();
                    emptTeks();
                    TabRingkasan.setSelectedIndex(1);
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Maaf, data hanya bisa diperbaiki oleh " + Sequel.cariIsi("select nama from petugas where nip='" + tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 35).toString() + "'"));
                }
            } catch (Exception e) {
                System.out.println("Ganti Ringkasan Pulang Pasien : " + e);
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

            param.put("tglRingkasan", "Martapura, " + Valid.SetTglINDONESIA(Sequel.cariIsi("select tgl_keluar from kamar_inap where "
                    + "no_rawat='" + TNoRW.getText() + "' and stts_pulang<>'Pindah Kamar' order by tgl_masuk desc, jam_masuk desc limit 1")));
            param.put("jamRingkasan", "Jam          : " + Sequel.cariIsi("select time_format(jam_keluar,'%H:%i') from kamar_inap where "
                    + "no_rawat='" + TNoRW.getText() + "' and stts_pulang<>'Pindah Kamar' order by tgl_masuk desc, jam_masuk desc limit 1") + " WITA");

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

    private void MnHasilPemeriksaanLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHasilPemeriksaanLabActionPerformed
        if (TNoRW.getText().trim().equals("") || TNmPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRW, "Pasien");
        } else {
            if (Sequel.cariInteger("select count(1) cek from lis_reg where no_rawat='" + TNoRW.getText() + "'") == 0) {
                JOptionPane.showMessageDialog(null, "Hasil pemeriksaan laboratorium (LIS) tidak ditemukan ...!!!!");
            } else {
                DlgHasilLIS lis = new DlgHasilLIS(null, false);
                lis.setSize(914, internalFrame1.getHeight() - 40);
                lis.setLocationRelativeTo(internalFrame1);
                lis.setData(TNoRW.getText(), TNmPasien.getText(), TNoRM.getText());
                lis.setVisible(true);
            }
        }
    }//GEN-LAST:event_MnHasilPemeriksaanLabActionPerformed

    private void MnHasilPemeriksaanRadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHasilPemeriksaanRadActionPerformed
        if (TNoRW.getText().trim().equals("") || TNmPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRW, "Pasien");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgRingkasanPulangRanap");
            DlgHasilExpertiseRadiologi form = new DlgHasilExpertiseRadiologi(null, false);
            form.setSize(914, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setData(TNoRM.getText());
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnHasilPemeriksaanRadActionPerformed

    private void BtnResepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnResepActionPerformed
        kodekamar = "";
        kodekamar = Sequel.cariIsi("select ki.kd_kamar from kamar_inap ki inner join kamar k on k.kd_kamar=ki.kd_kamar "
                + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal where ki.no_rawat='" + TNoRW.getText() + "' "
                + "order by ki.tgl_masuk desc, ki.jam_masuk desc limit 1");

        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        akses.setform("DlgRingkasanPulangRanap");
        DlgCatatanResep form = new DlgCatatanResep(null, false);
        form.isCek();
        form.setData(TNoRW.getText(), Sequel.cariIsi("SELECT b.nm_gedung FROM kamar k INNER JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal WHERE k.kd_kamar='" + kodekamar + "'"), "ranap");
        form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        form.setLocationRelativeTo(internalFrame1);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnResepActionPerformed

    private void BtnVerifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVerifActionPerformed
        if (TNoRW.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Pasien belum dipilih..!!!!");
        } else {
            if (akses.getadmin() == true) {
                DlgVerifikasiCPPT verif = new DlgVerifikasiCPPT(null, false);
                verif.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                verif.setLocationRelativeTo(internalFrame1);
                verif.setData(TNoRW.getText(), "ranap");
                verif.setVisible(true);
            } else if (akses.getkode().equals(Sequel.cariIsi("select kd_dokter from dpjp_ranap where no_rawat='" + TNoRW.getText() + "'"))) {
                DlgVerifikasiCPPT verif = new DlgVerifikasiCPPT(null, false);
                verif.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                verif.setLocationRelativeTo(internalFrame1);
                verif.setData(TNoRW.getText(), "ranap");
                verif.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Verifikasi CPPT hanya dilakukan oleh DPJP pasien tersebut...!!!");
            }
        }
    }//GEN-LAST:event_BtnVerifActionPerformed

    private void MnTriaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTriaseActionPerformed
        if (TNoRW.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
        } else {
            if (Sequel.cariInteger("select count(-1) from triase_igd where no_rawat='" + TNoRW.getText() + "'") > 0) {
                cetakDataTriase();
            } else {
                JOptionPane.showMessageDialog(null, "Data triase IGD tidak ditemukan...!!!");
            }
        }
    }//GEN-LAST:event_MnTriaseActionPerformed

    private void MnAsesmenMedikIGDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnAsesmenMedikIGDActionPerformed
        if (TNoRW.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
        } else {
            if (Sequel.cariInteger("select count(-1) from penilaian_awal_medis_igd where no_rawat='" + TNoRW.getText() + "'") > 0) {
                cetakAsesMedikIGD();
            } else {
                JOptionPane.showMessageDialog(null, "Data asesmen medik IGD tidak ditemukan...!!!");
            }
        }
    }//GEN-LAST:event_MnAsesmenMedikIGDActionPerformed

    private void MnAsesmenKeperawatanIGDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnAsesmenKeperawatanIGDActionPerformed
        if (TNoRW.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
        } else {
            if (Sequel.cariInteger("select count(-1) from penilaian_awal_keperawatan_igdrz where no_rawat='" + TNoRW.getText() + "'") > 0) {
                cetakAsesKepIGD();
            } else {
                JOptionPane.showMessageDialog(null, "Data asesmen keperawatan IGD tidak ditemukan...!!!");
            }
        }
    }//GEN-LAST:event_MnAsesmenKeperawatanIGDActionPerformed

    private void MnAsesmenMedikObstetriIGDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnAsesmenMedikObstetriIGDActionPerformed
        if (TNoRW.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
        } else {
            if (Sequel.cariInteger("select count(-1) from penilaian_awal_medis_obstetri_ralan where no_rawat='" + TNoRW.getText() + "'") > 0) {
                cetakAsesMedikObs();
            } else {
                JOptionPane.showMessageDialog(null, "Data asesmen medik obstetri pasien IGD tidak ditemukan...!!!");
            }
        }
    }//GEN-LAST:event_MnAsesmenMedikObstetriIGDActionPerformed

    private void MnAsesmenKebidananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnAsesmenKebidananActionPerformed
        JOptionPane.showMessageDialog(null, "Segera tayang (Comming Soon)...!!!");
    }//GEN-LAST:event_MnAsesmenKebidananActionPerformed

    private void BtnPastePenunjangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPastePenunjangActionPerformed
        if (akses.getPasteData().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan copy dulu hasil pemeriksaan lab. yg. dipilih..!!!!");
        } else {
            if (TPemeriksaanPenunjang.getText().equals("")) {
                TPemeriksaanPenunjang.setText(akses.getPasteData());
                akses.setCopyData("");
            } else {
                TPemeriksaanPenunjang.setText(TPemeriksaanPenunjang.getText() + "\n\n" + akses.getPasteData());
                akses.setCopyData("");
            }
        }
    }//GEN-LAST:event_BtnPastePenunjangActionPerformed

    private void THasilKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_THasilKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TCatatan.requestFocus();
        }
    }//GEN-LAST:event_THasilKeyPressed

    private void BtnPasteHasilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPasteHasilActionPerformed
        if (akses.getPasteData().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan copy dulu hasil pemeriksaan..!!!!");
        } else {
            if (THasil.getText().equals("")) {
                THasil.setText(akses.getPasteData());
                akses.setCopyData("");
            } else {
                THasil.setText(THasil.getText() + "\n\n" + akses.getPasteData());
                akses.setCopyData("");
            }
        }
    }//GEN-LAST:event_BtnPasteHasilActionPerformed

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
    private widget.Button BtnPasteHasil;
    private widget.Button BtnPastePenunjang;
    private widget.Button BtnResep;
    private widget.Button BtnSimpan;
    private widget.Button BtnVerif;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnAsesmenKebidanan;
    private javax.swing.JMenuItem MnAsesmenKeperawatanIGD;
    private javax.swing.JMenuItem MnAsesmenMedikIGD;
    private javax.swing.JMenuItem MnAsesmenMedikObstetriIGD;
    private javax.swing.JMenuItem MnCetakRingkasan;
    private javax.swing.JMenuItem MnDiagnosa;
    private javax.swing.JMenuItem MnHasilPemeriksaanLab;
    private javax.swing.JMenuItem MnHasilPemeriksaanRad;
    private javax.swing.JMenu MnIGD;
    private javax.swing.JMenuItem MnTriase;
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
    private widget.ScrollPane Scroll28;
    private widget.ScrollPane Scroll8;
    private widget.TextArea TAlasanDirawat;
    private widget.TextBox TCaraBayar;
    private widget.TextBox TCari;
    private widget.TextArea TCatatan;
    private widget.TextArea TDiagSekunder;
    private widget.TextArea TDiagUtama;
    private widget.TextBox TDokterLuar;
    private widget.TextBox TFrekuensiNafas;
    private widget.TextArea THasil;
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
    private javax.swing.JTabbedPane TabPencegahanAnak;
    private javax.swing.JTabbedPane TabPencegahanDewasa;
    private widget.TabPane TabRingkasan;
    private javax.swing.JTabbedPane TabTindakanPencegahan;
    private widget.TextBox Tdpjp;
    private widget.TextBox Tedukasi;
    private widget.TextBox Tgcs;
    private widget.Tanggal TglKontrol;
    private widget.TextArea anakA;
    private widget.TextArea anakB;
    private widget.CekBox chkTglKontrol;
    private widget.ComboBox cmbLanjutan;
    private widget.TextArea dewasaA;
    private widget.TextArea dewasaB;
    private widget.TextArea dewasaC;
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
    private widget.Label jLabel38;
    private widget.Label jLabel39;
    private widget.Label jLabel4;
    private widget.Label jLabel40;
    private widget.Label jLabel41;
    private widget.Label jLabel42;
    private widget.Label jLabel43;
    private widget.Label jLabel44;
    private widget.Label jLabel45;
    private widget.Label jLabel46;
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
    private widget.Label jLabel57;
    private widget.Label jLabel58;
    private widget.Label jLabel59;
    private widget.Label jLabel8;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.PanelBiasa panelBiasa10;
    private widget.PanelBiasa panelBiasa14;
    private widget.PanelBiasa panelBiasa15;
    private widget.PanelBiasa panelBiasa6;
    private widget.PanelBiasa panelBiasa7;
    private widget.PanelBiasa panelBiasa8;
    private widget.PanelBiasa panelBiasa9;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.panelisi panelisi1;
    private widget.Table tbFaktorResiko;
    private widget.Table tbRingkasan;
    // End of variables declaration//GEN-END:variables

    public void tampil() {     
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT rr.no_rawat, p.no_rkm_medis, p.nm_pasien, DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y') tgl_lhr, IF(p.jk='L','Laki-laki','Perempuan') jk, b.nm_bangsal, DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y') tgl_msk, "
                    + "DATE_FORMAT(ki.tgl_keluar,'%d-%m-%Y') tgl_pulang, IF(rr.nm_dokter_pengirim='','-',rr.nm_dokter_pengirim) dr_pengirim, pj.png_jawab, rr.alasan_masuk_dirawat, rr.ringkasan_riwayat_penyakit, "
                    + "rr.pemeriksaan_fisik, rr.pemeriksaan_penunjang, rr.terapi_pengobatan, rr.diagnosa_utama, rr.diagnosa_sekunder, rr.tindakan_prosedur, ki.stts_pulang, rr.keadaan_umum, rr.kesadaran, rr.gcs, rr.tekanan_darah, "
                    + "rr.suhu, rr.nadi, rr.frekuensi_nafas, rr.catatan_penting, rr.terapi_pulang, rr.pengobatan_dilanjutkan, rr.dokter_luar_lanjutan dr_luar, rr.tgl_kontrol_poliklinik tgl_kontrol, d.nm_dokter dpjp, "
                    + "rr.cek_tgl_kontrol, rr.edukasi, rr.penanggung_jwb_pasien, rr.nip_penyimpan, ifnull(rr.hasil_pemeriksaan,'') hasil_pemeriksaan FROM ringkasan_pulang_ranap rr "
                    + "INNER JOIN kamar_inap ki on ki.no_rawat=rr.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal "
                    + "INNER JOIN reg_periksa rp on rp.no_rawat=rr.no_rawat INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "INNER JOIN dpjp_ranap dr on dr.no_rawat=ki.no_rawat INNER JOIN dokter d on d.kd_dokter=dr.kd_dokter where "
                    + "ki.stts_pulang<>'Pindah Kamar' and rr.no_rawat like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and p.no_rkm_medis like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and p.nm_pasien like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and IF (p.jk = 'L','Laki-laki','Perempuan') like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and b.nm_bangsal like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and IF (rr.nm_dokter_pengirim = '','-',rr.nm_dokter_pengirim) like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and pj.png_jawab like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and rr.alasan_masuk_dirawat like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and rr.ringkasan_riwayat_penyakit like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and rr.pemeriksaan_fisik like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and rr.pemeriksaan_penunjang like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and rr.terapi_pengobatan like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and rr.diagnosa_utama like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and rr.diagnosa_sekunder like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and rr.tindakan_prosedur like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and ki.stts_pulang like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and rr.keadaan_umum like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and rr.kesadaran like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and rr.gcs like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and rr.tekanan_darah like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and rr.suhu like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and rr.nadi like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and rr.frekuensi_nafas like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and rr.catatan_penting like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and rr.terapi_pulang like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and rr.pengobatan_dilanjutkan like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and rr.dokter_luar_lanjutan like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and rr.tgl_kontrol_poliklinik like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and d.nm_dokter like ? or "
                    + "ki.stts_pulang<>'Pindah Kamar' and rr.penanggung_jwb_pasien like ? ORDER BY ki.tgl_masuk desc, ki.jam_masuk desc, rr.no_rawat desc LIMIT 100");
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
                        rs.getString("nip_penyimpan"),
                        rs.getString("hasil_pemeriksaan")
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
        THasil.setText("");
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
            THasil.setText(tbRingkasan.getValueAt(tbRingkasan.getSelectedRow(), 36).toString());
            
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
                    + "rp.no_rawat like '%" + TNoRW.getText() + "%' and ki.stts_pulang<>'Pindah Kamar' order by ki.tgl_masuk desc, ki.jam_masuk desc limit 1");            
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
    
    private void cetakDataTriase() {
        totskorTriase = 0;
        try {
            psLaprm = koneksi.prepareStatement("select * from triase_igd where no_rawat='" + TNoRW.getText() + "'");
            try {
                rsLaprm = psLaprm.executeQuery();
                while (rsLaprm.next()) {
                    Map<String, Object> param = new HashMap<>();
                    param.put("namars", akses.getnamars());
                    param.put("logo", Sequel.cariGambar("select logo from setting"));

                    if (rsLaprm.getString("alasan_kedatangan").equals("")) {
                        param.put("alasan_kedatangan", "-");
                    } else if (rsLaprm.getString("alasan_kedatangan").equals("Datang Sendiri") || rsLaprm.getString("alasan_kedatangan").equals("Polisi")) {
                        param.put("alasan_kedatangan", rsLaprm.getString("alasan_kedatangan"));
                    } else if (rsLaprm.getString("alasan_kedatangan").equals("Rujukan, dari")) {
                        param.put("alasan_kedatangan", "Rujukan, dari : " + rsLaprm.getString("rujukan_dari"));
                    } else if (rsLaprm.getString("alasan_kedatangan").equals("Dijemput oleh")) {
                        param.put("alasan_kedatangan", "Dijemput oleh : " + rsLaprm.getString("dijemput_oleh"));
                    }

                    if (rsLaprm.getString("kendaraan").equals("")) {
                        param.put("kendaraan", "-");
                    } else if (rsLaprm.getString("kendaraan").equals("Ambulance")) {
                        param.put("kendaraan", rsLaprm.getString("kendaraan"));
                    } else if (rsLaprm.getString("kendaraan").equals("Kendaraan bukan ambulance")) {
                        param.put("kendaraan", "Kendaraan bukan ambulance, jelaskan : " + rsLaprm.getString("bukan_ambulan"));
                    }

                    if (rsLaprm.getString("kll_tunggal").equals("ya")) {
                        param.put("kll_tunggal", "KLL Tunggal Tempat Kejadian " + rsLaprm.getString("kll_tunggal_tmpt_kejadian") + " Tanggal Kejadian "
                                + Sequel.cariIsi("select date_format(kll_tunggal_tanggal,'%d-%m-%Y    Pukul : %H:%i') from triase_igd "
                                        + "where no_rawat='" + rsLaprm.getString("no_rawat") + "'"));
                    } else {
                        param.put("kll_tunggal", "KLL Tunggal");
                    }

                    if (rsLaprm.getString("kll_versus").equals("ya")) {
                        param.put("kll", "KLL " + rsLaprm.getString("versus1") + " Vs. " + rsLaprm.getString("versus2") + " Tempat Kejadian "
                                + rsLaprm.getString("kll_tmpt_kejadian") + " Tanggal Kejadian " + Sequel.cariIsi("select date_format(kll_tanggal,'%d-%m-%Y    Pukul : %H:%i') from triase_igd "
                                + "where no_rawat='" + rsLaprm.getString("no_rawat") + "'"));
                    } else {
                        param.put("kll", "KLL");
                    }

                    if (rsLaprm.getString("jatuh").equals("ya")) {
                        param.put("jatuh", "Jatuh dari ketinggian, Jelaskan : " + rsLaprm.getString("ket_jatuh"));
                    } else {
                        param.put("jatuh", "Jatuh dari ketinggian,");
                    }

                    if (rsLaprm.getString("luka_bakar").equals("ya")) {
                        param.put("luka", "Luka bakar, Jelaskan : " + rsLaprm.getString("ket_luka_bakar"));
                    } else {
                        param.put("luka", "Luka bakar,");
                    }

                    if (rsLaprm.getString("trauma_listrik").equals("ya")) {
                        param.put("trauma_listrik", "Trauma listrik, Jelaskan : " + rsLaprm.getString("ket_trauma_listrik"));
                    } else {
                        param.put("trauma_listrik", "Trauma listrik,");
                    }

                    if (rsLaprm.getString("trauma_zat_kimia").equals("ya")) {
                        param.put("trauma_zat", "Trauma zat kimia, Jelaskan : " + rsLaprm.getString("ket_trauma_zat_kimia"));
                    } else {
                        param.put("trauma_zat", "Trauma zat kimia,");
                    }

                    if (rsLaprm.getString("trauma_lain").equals("ya")) {
                        param.put("trauma_lain", "Trauma lainnya (" + rsLaprm.getString("ket_trauma_lain") + ")");
                    } else {
                        param.put("trauma_lain", "Trauma lainnya");
                    }

                    if (rsLaprm.getString("pacs1").equals("ya")) {
                        param.put("pacs", "LEVEL TRIASE (PATIENT'S ACUITY CATEGORIZATION SCALE / PACS) : PACS 1");
                    } else if (rsLaprm.getString("pacs2").equals("ya")) {
                        param.put("pacs", "LEVEL TRIASE (PATIENT'S ACUITY CATEGORIZATION SCALE / PACS) : PACS 2");
                    } else if (rsLaprm.getString("pacs3").equals("ya")) {
                        param.put("pacs", "LEVEL TRIASE (PATIENT'S ACUITY CATEGORIZATION SCALE / PACS) : PACS 3");
                    } else if (rsLaprm.getString("pacs4").equals("ya")) {
                        param.put("pacs", "LEVEL TRIASE (PATIENT'S ACUITY CATEGORIZATION SCALE / PACS) : PACS 4");
                    } else {
                        param.put("pacs", "LEVEL TRIASE (PATIENT'S ACUITY CATEGORIZATION SCALE / PACS) : -");
                    }

                    totskorTriase = Integer.parseInt(rsLaprm.getString("total_skor"));
                    if (totskorTriase >= 5) {
                        param.put("total5", "V");
                        param.put("total24", "");
                        param.put("total01", "");
                    } else if (totskorTriase >= 2 && totskorTriase <= 4) {
                        param.put("total5", "");
                        param.put("total24", "V");
                        param.put("total01", "");
                    } else if (totskorTriase >= 0 && totskorTriase <= 1) {
                        param.put("total5", "");
                        param.put("total24", "");
                        param.put("total01", "V");
                    } else {
                        param.put("total5", "");
                        param.put("total24", "");
                        param.put("total01", "");
                    }

                    Valid.MyReport("rptTriaseIGD.jasper", "report", "::[ Laporan Data Triase IGD ]::",
                            "SELECT ti.no_rawat, p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllahir, date_format(ti.tanggal,'Tanggal : %d-%m-%Y    Pukul : %H:%i') kontak_awal, "
                            + "if(ti.cara_masuk='','-',ti.cara_masuk) cr_msk, ti.sudah_terpasang, concat('Nama : ',ti.nm_pengantar,'    No. Telp : ',ti.telp_pengantar) iden_pengntar, "
                            + "ti.kasus, ti.keluhan_utama, if(ti.kesadaran='','KESADARAN : -',concat('KESADARAN : ',ti.kesadaran)) kesadaran, ti.td, ti.nadi, ti.napas, ti.temperatur, "
                            + "ti.saturasi, ti.nyeri, ti.vas, if(ti.skor0_sadar_penuh='ya','V','') skor0_sadar, if(ti.skor0_100='ya','V','') skor0_100, if(ti.skor0_101='ya','V','') skor0_101, "
                            + "if(ti.skor0_19='ya','V','') skor0_19, if(ti.skor0_35_3='ya','V','') skor0_35, if(ti.skor0_96_100='ya','V','') skor0_96, if(ti.skor1_102='ya','V','') skor1_102, "
                            + "if(ti.skor1_20_21='ya','V','') skor1_20, if(ti.skor1_94_95='ya','V','') skor1_94, if(ti.skor2_99='ya','V','') skor2_99, if(ti.skor2_22='ya','V','') skor2_22, "
                            + "if(ti.skor2_92_93='ya','V','') skor2_92, if(ti.skor3_selain='ya','V','') skor3_selain, if(ti.skor3_35_3='ya','V','') skor3_35, if(ti.skor3_92='ya','V','') skor3_92, "
                            + "ti.catatan, ti.pukul, if(ti.triase_resusitasi='ya','V','') resus, if(ti.triase_non_resusitasi='ya','V','') nonresus, if(ti.triase_klinik='ya','V','') klinik, "
                            + "if(ti.triase_doa='ya','V','') doa, pg.nama petgas, if(ti.kll_tunggal='ya','V','') kll_tunggal, if(ti.kll_versus='ya','V','') kll_versus, if(ti.jatuh='ya','V','') jatuh, "
                            + "if(ti.luka_bakar='ya','V','') luka, if(ti.trauma_listrik='ya','V','') trauma_listrik, if(ti.trauma_zat_kimia='ya','V','') trauma_zat, if(ti.trauma_lain='ya','V','') trauma_lain, "
                            + "ti.bb, ti.tb from triase_igd ti inner join reg_periksa rp on rp.no_rawat=ti.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                            + "INNER JOIN pegawai pg on nik=ti.nip_petugas where ti.no_rawat='" + rsLaprm.getString("no_rawat") + "'", param);
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsLaprm != null) {
                    rsLaprm.close();
                }
                if (psLaprm != null) {
                    psLaprm.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void cetakAsesMedikIGD() {
        skorAsesIGD = "";
        try {
            psLaprm = koneksi.prepareStatement("select *, date_format(tgl_keluar_igd,'%H:%i:%s') jamklr from penilaian_awal_medis_igd where no_rawat='" + TNoRW.getText() + "'");
            try {
                rsLaprm = psLaprm.executeQuery();
                while (rsLaprm.next()) {
                    Map<String, Object> param = new HashMap<>();
                    param.put("namars", akses.getnamars());
                    param.put("logo", Sequel.cariGambar("select logo from setting"));

                    //hitung skor
                    int A, B, C, D, E, hasil;
                    A = Integer.parseInt(rsLaprm.getString("frekuensi_nafas"));
                    B = Integer.parseInt(rsLaprm.getString("retraksi"));
                    C = Integer.parseInt(rsLaprm.getString("sianosis"));
                    D = Integer.parseInt(rsLaprm.getString("air_entry"));
                    E = Integer.parseInt(rsLaprm.getString("merintih"));

                    hasil = 0;
                    hasil = A + B + C + D + E;
                    skorAsesIGD = Valid.SetAngka2(hasil);
                    param.put("jlhSkor", skorAsesIGD);

                    if (rsLaprm.getString("meninggal").equals("ya")) {
                        param.put("jam_meninggal", rsLaprm.getString("jam_meninggal"));
                    } else {
                        param.put("jam_meninggal", "-");
                    }

                    if (rsLaprm.getString("cek_jam_keluar").equals("ya")) {
                        param.put("jam_keluar", rsLaprm.getString("jamklr"));
                    } else {
                        param.put("jam_keluar", "-");
                    }

                    Valid.MyReport("rptCetakPenilaianAwalMedisIGD.jasper", "report", "::[ Laporan Penilaian Awal Medis IGD hal. 1 ]::",
                            "SELECT p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllahir, "
                            + "date_format(pa.tanggal,'Tanggal : %d-%m-%Y    Pukul : %H:%i') mulai_penanganan, if(pa.cervival='ya','V','') cer, "
                            + "if(pa.rjp='ya','V','') rjp, if(pa.defribilasi='ya','V','') def, if(pa.intubasi='ya','V','') intu, if(pa.vtp='ya','V','') vtp, if(pa.dekompresi='ya','V','') dek, "
                            + "if(pa.balut='ya','V','') bal, if(pa.kateter='ya','V','') kat, if(pa.ngt='ya','V','') ngt, if(pa.infus='ya','V','') infs, if(pa.obat='ya','V','') obt, pa.ket_obat, "
                            + "if(pa.tidak_ada='ya','V','') tdk, if(pa.gangguan_jalan_nafas='ya','V','') ggnfs, if(pa.paten='ya','V','') pat, if(pa.obstruksi_partial='ya','V','') obsp, if(pa.data_obstruksi_partial='','-',pa.data_obstruksi_partial) data_obsp, "
                            + "if(pa.obstruksi_total='ya','V','') obst, if(pa.trauma_jalan_nafas='ya','V','') trauma_jln_nfs, pa.trauma, if(pa.resiko_aspirasi='ya','V','') res, pa.aspirasi, "
                            + "if(pa.benda_asing='ya','V','') ben, pa.ket_benda_asing, pa.kesimpulan_jalan_nafas, pa.pernafasan, pa.data_pernafasan, pa.gerakan_dada, pa.tipe_pernafasan, "
                            + "pa.kesimpulan_pernafasan, pa.nadi_1, pa.kulit_mukosa, pa.akral_1, pa.crt, pa.kesimpulan_sirkulasi, pa.cukup_bulan, pa.cairan_amnion, pa.pernafasan_menangis, pa.tonus, "
                            + "pa.skor_apgar, pa.gcs_e, pa.pupil, pa.diameter_kanan, pa.reflek_cahaya, pa.meningeal_sign, pa.literasi, if(pa.deformitas='ya','V','') defo, if(pa.contusio='ya','V','') con, "
                            + "if(pa.penetrasi='ya','V','') pen, if(pa.tenderness='ya','V','') ten, if(pa.swelling='ya','V','') swe, if(pa.ekskoriasi='ya','V','') eks, if(pa.abrasi='ya','V','') abr, "
                            + "if(pa.burn='ya','V','') bur, if(pa.laserasi='ya','V','') las, if(pa.tidak_tampak_jelas='ya','V','') tdk_tmpk, pa.frekuensi_nafas, pa.retraksi, pa.sianosis, pa.air_entry, "
                            + "pa.merintih, pa.Alergi, if(pa.hipertensi='ya','V','') hip, if(pa.diabetes='ya','V','') dm, if(pa.jantung='ya','V','') jan, pa.riwayat_penyakit_lain, "
                            + "if(pa.merokok='ya','V','') mer, pa.kebiasaan_lain, pa.anamnesis, pa.pemeriksaan_fisik, pa.konjungtiva, pa.sklera, pa.bibir_lidah, pa.mukosa, pa.deviasi, pa.jvp, "
                            + "pa.lnn, pa.tiroid, pa.survei_jantung, pa.survei_paru, pa.survei_abdomen, pa.survei_punggung, pa.survei_ekstremitas, pa.laboratorium, pa.x_ray, "
                            + "pa.ecg, pa.ct_scan, pa.usg, pa.lainnya_penunjang, pa.diag_medis_sementara, pa.icd_10, pa.rencana_instruksi, pa.ket_rencana_instruksi, pa.telah_diberikan_informasi_edukasi, "
                            + "pa.rencana_asuhan_diharapkan, pg1.nama pemberi_edukasi, pa.penerima_edukasi, pg2.nama nm_dokter, date_format(pa.tgl_keluar_igd,'%d-%m-%Y') tglkeluar, "
                            + "date_format(pa.tgl_keluar_igd,'%H:%i') jamkeluar, pa.opname_diruang, pa.indikasi_msk, pa.dipulangkan_kontrol_ke, pa.dirujuk_ke, pa.alasan_dirujuk, pa.penyebab, "
                            + "pa.k_u, pa.td, pa.hr, pa.rr, pa.temp, pa.spo2, pa.gcs_pulang, pg3.nama nm_perawat, pg4.nama nm_dpjp, pa.gcs_v, pa.gcs_m, pa.diameter_kiri, pa.nadi_2, pa.akral_2 "
                            + "from penilaian_awal_medis_igd pa inner join reg_periksa rp on rp.no_rawat=pa.no_rawat "
                            + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis left join pegawai pg1 on pg1.nik=pa.nip_pemberi_edukasi "
                            + "left join pegawai pg2 on pg2.nik=pa.nip_dokter left join pegawai pg3 on pg3.nik=pa.nip_perawat "
                            + "left join pegawai pg4 on pg4.nik=pa.nip_dpjp where pa.no_rawat='" + rsLaprm.getString("no_rawat") + "'", param);

                    Valid.MyReport("rptCetakPenilaianAwalMedisIGD1.jasper", "report", "::[ Laporan Penilaian Awal Medis IGD hal. 2 ]::",
                            "SELECT p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllahir, "
                            + "date_format(pa.tanggal,'Tanggal : %d-%m-%Y    Pukul : %H:%i') mulai_penanganan, if(pa.cervival='ya','V','') cer, "
                            + "if(pa.rjp='ya','V','') rjp, if(pa.defribilasi='ya','V','') def, if(pa.intubasi='ya','V','') intu, if(pa.vtp='ya','V','') vtp, if(pa.dekompresi='ya','V','') dek, "
                            + "if(pa.balut='ya','V','') bal, if(pa.kateter='ya','V','') kat, if(pa.ngt='ya','V','') ngt, if(pa.infus='ya','V','') infs, if(pa.obat='ya','V','') obt, pa.ket_obat, "
                            + "if(pa.tidak_ada='ya','V','') tdk, if(pa.gangguan_jalan_nafas='ya','V','') ggnfs, if(pa.paten='ya','V','') pat, if(pa.obstruksi_partial='ya','V','') obsp, if(pa.data_obstruksi_partial='','-',pa.data_obstruksi_partial) data_obsp, "
                            + "if(pa.obstruksi_total='ya','V','') obst, if(pa.trauma_jalan_nafas='ya','V','') trauma_jln_nfs, pa.trauma, if(pa.resiko_aspirasi='ya','V','') res, pa.aspirasi, "
                            + "if(pa.benda_asing='ya','V','') ben, pa.ket_benda_asing, pa.kesimpulan_jalan_nafas, pa.pernafasan, pa.data_pernafasan, pa.gerakan_dada, pa.tipe_pernafasan, "
                            + "pa.kesimpulan_pernafasan, pa.nadi_1, pa.kulit_mukosa, pa.akral_1, pa.crt, pa.kesimpulan_sirkulasi, pa.cukup_bulan, pa.cairan_amnion, pa.pernafasan_menangis, pa.tonus, "
                            + "pa.skor_apgar, pa.gcs_e, pa.pupil, pa.diameter_kanan, pa.reflek_cahaya, pa.meningeal_sign, pa.literasi, if(pa.deformitas='ya','V','') defo, if(pa.contusio='ya','V','') con, "
                            + "if(pa.penetrasi='ya','V','') pen, if(pa.tenderness='ya','V','') ten, if(pa.swelling='ya','V','') swe, if(pa.ekskoriasi='ya','V','') eks, if(pa.abrasi='ya','V','') abr, "
                            + "if(pa.burn='ya','V','') bur, if(pa.laserasi='ya','V','') las, if(pa.tidak_tampak_jelas='ya','V','') tdk_tmpk, pa.frekuensi_nafas, pa.retraksi, pa.sianosis, pa.air_entry, "
                            + "pa.merintih, pa.Alergi, if(pa.hipertensi='ya','V','') hip, if(pa.diabetes='ya','V','') dm, if(pa.jantung='ya','V','') jan, pa.riwayat_penyakit_lain, "
                            + "if(pa.merokok='ya','V','') mer, pa.kebiasaan_lain, pa.anamnesis, pa.pemeriksaan_fisik, pa.konjungtiva, pa.sklera, pa.bibir_lidah, pa.mukosa, pa.deviasi, pa.jvp, "
                            + "pa.lnn, pa.tiroid, pa.survei_jantung, pa.survei_paru, pa.survei_abdomen, pa.survei_punggung, pa.survei_ekstremitas, pa.laboratorium, pa.x_ray, "
                            + "pa.ecg, pa.ct_scan, pa.usg, pa.lainnya_penunjang, pa.diag_medis_sementara, pa.icd_10, pa.rencana_instruksi, pa.ket_rencana_instruksi, pa.telah_diberikan_informasi_edukasi, "
                            + "pa.rencana_asuhan_diharapkan, ifnull(pg1.nama,'-') pemberi_edukasi, pa.penerima_edukasi, ifnull(pg2.nama,'-') nm_dokter, date_format(pa.tgl_keluar_igd,'%d-%m-%Y') tglkeluar, "
                            + "date_format(pa.tgl_keluar_igd,'%H:%i') jamkeluar, pa.opname_diruang, pa.indikasi_msk, pa.dipulangkan_kontrol_ke, pa.dirujuk_ke, pa.alasan_dirujuk, pa.penyebab, "
                            + "pa.k_u, pa.td, pa.hr, pa.rr, pa.temp, pa.spo2, pa.gcs_pulang, pg3.nama nm_perawat, pg4.nama nm_dpjp, ifnull(pa.ket_gambar,'-') ket_gambar, pa.gcs_v, pa.gcs_m, pa.diameter_kiri, "
                            + "SUBSTRING_INDEX( pa.ket_rencana_instruksi, '\n', 5 ) renc1, SUBSTRING_INDEX( pa.ket_rencana_instruksi, SUBSTRING_INDEX( pa.ket_rencana_instruksi, '\n', 5 ),- 1 ) renc2 "
                            + "from penilaian_awal_medis_igd pa inner join reg_periksa rp on rp.no_rawat=pa.no_rawat "
                            + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis left join pegawai pg1 on pg1.nik=pa.nip_pemberi_edukasi "
                            + "left join pegawai pg2 on pg2.nik=pa.nip_dokter left join pegawai pg3 on pg3.nik=pa.nip_perawat "
                            + "left join pegawai pg4 on pg4.nik=pa.nip_dpjp where pa.no_rawat='" + rsLaprm.getString("no_rawat") + "'", param);
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsLaprm != null) {
                    rsLaprm.close();
                }
                if (psLaprm != null) {
                    psLaprm.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void cetakAsesKepIGD() {
        try {
            psLaprm = koneksi.prepareStatement("select * from penilaian_awal_keperawatan_igdrz where no_rawat='" + TNoRW.getText() + "'");
            try {
                rsLaprm = psLaprm.executeQuery();
                while (rsLaprm.next()) {
                    if (rsLaprm.getString("gizi_dewasa1").equals("Tidak")) {
                        skorGZ1 = 0;
                    } else if (rsLaprm.getString("gizi_dewasa1").equals("Tidak Tahu/tidak yakin (ada tanda : baju menjadi longgar)")) {
                        skorGZ1 = 1;
                    } else if (rsLaprm.getString("gizi_dewasa1").equals("Ya, ada penurunan BB sebanyak")) {
                        skorGZ1 = 0;
                    }

                    if (rsLaprm.getString("gizi_dewasa1ya").equals("-")) {
                        skorYaGZ1 = 0;
                    } else if (rsLaprm.getString("gizi_dewasa1ya").equals("1 - 5 Kg")) {
                        skorYaGZ1 = 1;
                    } else if (rsLaprm.getString("gizi_dewasa1ya").equals("6 - 10 Kg")) {
                        skorYaGZ1 = 2;
                    } else if (rsLaprm.getString("gizi_dewasa1ya").equals("11 - 15 Kg")) {
                        skorYaGZ1 = 3;
                    } else if (rsLaprm.getString("gizi_dewasa1ya").equals("15 Kg")) {
                        skorYaGZ1 = 4;
                    } else if (rsLaprm.getString("gizi_dewasa1ya").equals("Tidak tahu berapa Kg penurunanya")) {
                        skorYaGZ1 = 2;
                    }

                    if (rsLaprm.getString("gizi_dewasa2").equals("Tidak")) {
                        skorGZ2 = 0;
                    } else if (rsLaprm.getString("gizi_dewasa2").equals("Ya")) {
                        skorGZ2 = 1;
                    }

                    //hitung skor skrining
                    int A1, B1, C1, TotD, A2, B2, C2, D2, TotA;
                    A1 = skorGZ1;
                    B1 = skorYaGZ1;
                    C1 = skorGZ2;

                    A2 = Integer.parseInt(rsLaprm.getString("gizi_anak1"));
                    B2 = Integer.parseInt(rsLaprm.getString("gizi_anak2"));
                    C2 = Integer.parseInt(rsLaprm.getString("gizi_anak3"));
                    D2 = Integer.parseInt(rsLaprm.getString("gizi_anak_penyakit"));

                    TotD = 0;
                    TotA = 0;

                    TotD = A1 + B1 + C1;
                    TotA = A2 + B2 + C2 + D2;
                    TotSkorGZD = Valid.SetAngka2(TotD);
                    TotSkorGZA = Valid.SetAngka2(TotA);

                    if (rsLaprm.getString("skrining_gizi").equals("dewasa")) {
                        kesimpulanGZanak = "";
                        if (TotD == 0 || TotD == 1) {
                            kesimpulanGZDewasa = "0 - 1 : tidak beresiko malnutrisi";
                        } else if (TotD >= 2) {
                            kesimpulanGZDewasa = ">= 2 : beresiko malnutrisi, perlu pemantauan lanjutan oleh Tim Gizi/Dietisien";
                        }
                    }

                    if (rsLaprm.getString("skrining_gizi").equals("anak")) {
                        kesimpulanGZDewasa = "";
                        if (TotA == 0) {
                            kesimpulanGZanak = "0 : tidak beresiko malnutrisi";
                        } else if (TotA >= 1 && TotA <= 3) {
                            kesimpulanGZanak = "1 - 3 : resiko malnutrisi sedang, perlu pemantauan";
                        } else if (TotA >= 4) {
                            kesimpulanGZanak = ">= 4 : resiko malnutrisi berat, perlu pemantauan lanjutan oleh Tim Gizi/Dietisien";
                        }
                    }

                    //tindakan pencegahan resiko jatuh
                    if (rsLaprm.getString("cegah_resiko_jatuh").equals("Dewasa")) {
                        TabTindakanPencegahan.setSelectedIndex(0);
                        if (rsLaprm.getString("tindakan_pencegahan").equals("A")) {
                            TabPencegahanDewasa.setSelectedIndex(0);
                        } else if (rsLaprm.getString("tindakan_pencegahan").equals("B")) {
                            TabPencegahanDewasa.setSelectedIndex(1);
                        } else if (rsLaprm.getString("tindakan_pencegahan").equals("C")) {
                            TabPencegahanDewasa.setSelectedIndex(2);
                        } else {
                            TabPencegahanDewasa.setSelectedIndex(0);
                        }
                    } else if (rsLaprm.getString("cegah_resiko_jatuh").equals("Anak")) {
                        TabTindakanPencegahan.setSelectedIndex(1);
                        if (rsLaprm.getString("tindakan_pencegahan").equals("A")) {
                            TabPencegahanAnak.setSelectedIndex(0);
                        } else if (rsLaprm.getString("tindakan_pencegahan").equals("B")) {
                            TabPencegahanAnak.setSelectedIndex(1);
                        } else {
                            TabPencegahanAnak.setSelectedIndex(0);
                        }
                    } else {
                        TabTindakanPencegahan.setSelectedIndex(0);
                        TabPencegahanDewasa.setSelectedIndex(0);
                    }

                    Map<String, Object> param = new HashMap<>();
                    param.put("namars", akses.getnamars());
                    param.put("logo", Sequel.cariGambar("select logo from setting"));

                    if (rsLaprm.getString("skrining_gizi").equals("anak")) {
                        param.put("jenisSkrining", "ANAK (berdasarkan modifikasi form STRONG Kids)");
                        param.put("kalimatSkrining", "1. Terdapat penurunan BB atau BB menetap (pada bayi < 1 tahun) selama >= 2 bulan                 Skor (" + rsLaprm.getString("gizi_anak1") + ")\n\n"
                                + "2. Terdapat tanda-tanda klinis gangguan gizi (tampak kurus, gemuk, pendek, edema, moon face,   Skor (" + rsLaprm.getString("gizi_anak2") + ")\n"
                                + "tampak tua, iga gambang, baggy pant, anoreksia) selama 1 bulan terakhir\n\n"
                                + "3. Terdapat salah satu penyakit/kondisi yg. beresiko mengakibatkan malnutrisi berikut :                 Skor (" + rsLaprm.getString("gizi_anak3") + ")\n"
                                + "* Diare berat (> 5x/hari) dan atau muntah (> 3x/hari)\n"
                                + "* Penurunan asupan makanan selama lebih dari 7 hari\n\n"
                                + "Terdapat penyakit-penyakit / keadaan yg. meningkatkan resiko malnutrisi antara lain :                   Skor (" + rsLaprm.getString("gizi_anak_penyakit") + ")\n"
                                + "* Diare kronik > 2 minggu                          * Penyakit hati/ginjal kronik\n"
                                + "* Penyakit jantung bawaan (tersangka)        * TB Paru\n"
                                + "* Infeksi HIV (tersangka)                            * Renca/paska operasimayor\n"
                                + "* Kelainan anatomi bawaan                         * Luka bakar luas\n"
                                + "* Kelainan metabolisme bawaan                  * Terpasang stoma\n"
                                + "* Retardasi mental                                     * Trauma\n"
                                + "* Keterlambatan perkembangan                  * Lain-lain : " + rsLaprm.getString("gizi_anak_penyakit_lain") + "\n"
                                + "* Kanker (tersangka)\n"
                                + "_______________________________________________________________________\n"
                                + "Total Skor : (" + TotSkorGZA + ")\n"
                                + "Kesimpulan Skrining Gizi Anak :\n"
                                + kesimpulanGZanak + "\n");
                        param.put("resikoJatuh", "Anak (Skala Humpty Dumpty)");
                        param.put("tindakanRJ", "ANAK");
                        if (TabPencegahanAnak.getSelectedIndex() == 0) {
                            param.put("JudultindakanRJ", "Pencegahan Umum (A)");
                            param.put("IsitindakanRJ", anakA.getText());
                        } else if (TabPencegahanAnak.getSelectedIndex() == 1) {
                            param.put("JudultindakanRJ", "Pencegahan Resiko Tinggi (B)");
                            param.put("IsitindakanRJ", anakB.getText());
                        }

                    } else if (rsLaprm.getString("skrining_gizi").equals("dewasa")) {
                        param.put("jenisSkrining", "DEWASA (Modifikasi MST)");
                        param.put("kalimatSkrining", "1. Apakah pasien mengalami penurunan BB yang tidak direncanakan/tidak diinginkan dalam 6 bulan terakhir ?\n"
                                + rsLaprm.getString("gizi_dewasa1") + "   Skor (" + skorGZ1 + ")\n"
                                + rsLaprm.getString("gizi_dewasa1ya") + "   Skor (" + skorYaGZ1 + ")\n\n"
                                + "2. Apakah asupan makan pasien berkurang karena penurunan nafsu makan / kesulitan menerima makanan ?\n"
                                + rsLaprm.getString("gizi_dewasa2") + "   Skor (" + skorGZ2 + ")\n"
                                + "_______________________________________________________________________\n"
                                + "Total Skor : (" + TotSkorGZD + ")\n"
                                + "Kesimpulan Skrining Gizi Dewasa :\n"
                                + kesimpulanGZDewasa + "\n");
                        param.put("resikoJatuh", "Dewasa (Skala Morse)");
                        param.put("tindakanRJ", "DEWASA");
                        if (TabPencegahanDewasa.getSelectedIndex() == 0) {
                            param.put("JudultindakanRJ", "Pencegahan Umum (A)");
                            param.put("IsitindakanRJ", dewasaA.getText());
                        } else if (TabPencegahanDewasa.getSelectedIndex() == 1) {
                            param.put("JudultindakanRJ", "Pencegahan Resiko Sedang (B)");
                            param.put("IsitindakanRJ", dewasaB.getText());
                        } else if (TabPencegahanDewasa.getSelectedIndex() == 2) {
                            param.put("JudultindakanRJ", "Pencegahan Resiko Tinggi (C)");
                            param.put("IsitindakanRJ", dewasaC.getText());
                        }

                    } else if (!rsLaprm.getString("skrining_gizi").equals("anak") && !rsLaprm.getString("skrining_gizi").equals("dewasa")) {
                        param.put("jenisSkrining", "");
                        param.put("kalimatSkrining", "");
                        param.put("resikoJatuh", "");
                        param.put("tindakanRJ", "");
                        param.put("JudultindakanRJ", "");
                        param.put("IsitindakanRJ", "");
                    }

                    //data faktor resiko
                    try {
                        faktorresikoigd = "";
                        psFakIGD = koneksi.prepareStatement("select m.kode_resiko, concat('Faktor : ',m.faktor_resiko,', Skala : ',m.skala,', Skor (',m.skor,')') resiko "
                                + "FROM master_faktor_resiko_igd m INNER JOIN penilaian_awal_keperawatan_igd_resiko pm ON pm.kode_resiko = m.kode_resiko "
                                + "WHERE pm.no_rawat=? ORDER BY pm.kode_resiko");
                        try {
                            psFakIGD.setString(1, rsLaprm.getString("no_rawat"));
                            rsFakIGD = psFakIGD.executeQuery();
                            while (rsFakIGD.next()) {
                                faktorresikoigd = rsFakIGD.getString("resiko") + "\n" + faktorresikoigd;
                            }

                            if (faktorresikoigd.endsWith("\n")) {
                                faktorresikoigd = faktorresikoigd.substring(0, faktorresikoigd.length() - 1);
                            }

                        } catch (Exception e) {
                            System.out.println("Notif : " + e);
                        } finally {
                            if (rsFakIGD != null) {
                                rsFakIGD.close();
                            }
                            if (psFakIGD != null) {
                                psFakIGD.close();
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Notif : " + e);
                    }

                    //cek faktor resiko                    
                    try {
                        Valid.tabelKosong(tabModeResiko);
                        if (rsLaprm.getString("skrining_gizi").equals("anak")) {
                            psRes = koneksi.prepareStatement("select m.kode_resiko, m.faktor_resiko, m.skala, m.skor, m.asesmen FROM master_faktor_resiko_igd m "
                                    + "INNER JOIN penilaian_awal_keperawatan_igd_resiko pa ON pa.kode_resiko = m.kode_resiko "
                                    + "WHERE m.asesmen = 'anak' and pa.no_rawat=? ORDER BY pa.kode_resiko");
                        } else if (rsLaprm.getString("skrining_gizi").equals("dewasa")) {
                            psRes = koneksi.prepareStatement("select m.kode_resiko, m.faktor_resiko, m.skala, m.skor, m.asesmen FROM master_faktor_resiko_igd m "
                                    + "INNER JOIN penilaian_awal_keperawatan_igd_resiko pa ON pa.kode_resiko = m.kode_resiko "
                                    + "WHERE m.asesmen = 'dewasa' and pa.no_rawat=? ORDER BY pa.kode_resiko");
                        } else {
                            psRes = koneksi.prepareStatement("select m.kode_resiko, m.faktor_resiko, m.skala, m.skor, m.asesmen FROM master_faktor_resiko_igd m "
                                    + "INNER JOIN penilaian_awal_keperawatan_igd_resiko pa ON pa.kode_resiko = m.kode_resiko "
                                    + "WHERE pa.no_rawat=? ORDER BY pa.kode_resiko");
                        }
                        try {
                            psRes.setString(1, rsLaprm.getString("no_rawat"));
                            rsRes = psRes.executeQuery();
                            while (rsRes.next()) {
                                tabModeResiko.addRow(new Object[]{
                                    true,
                                    rsRes.getString("kode_resiko"),
                                    rsRes.getString("asesmen"),
                                    rsRes.getString("faktor_resiko"),
                                    rsRes.getString("skala"),
                                    rsRes.getString("skor")
                                });
                            }
                        } catch (Exception e) {
                            System.out.println("Notif : " + e);
                        } finally {
                            if (rsRes != null) {
                                rsRes.close();
                            }
                            if (psRes != null) {
                                psRes.close();
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Notif : " + e);
                    }

                    //hitung skor faktor resiko
                    skor = 0;
                    for (i = 0; i < tbFaktorResiko.getRowCount(); i++) {
                        if (tbFaktorResiko.getValueAt(i, 0).toString().equals("true")) {
                            skor = skor + Integer.parseInt(tbFaktorResiko.getValueAt(i, 5).toString());
                        }
                    }

                    TotSkorRJ = Valid.SetAngka2(skor);
                    if (!rsLaprm.getString("skrining_gizi").equals("dewasa") && !rsLaprm.getString("skrining_gizi").equals("anak")) {
                        TotSkorRJ = "0";
                        kesimpulanResikoJatuh = "";
                    }

                    //asesmen dewasa
                    if (rsLaprm.getString("skrining_gizi").equals("dewasa")) {
                        if (skor >= 45) {
                            kesimpulanResikoJatuh = "Resiko Tinggi : >= 45, pasang kancing berwarna kuning";
                        } else if (skor >= 25 && skor <= 44) {
                            kesimpulanResikoJatuh = "Resiko Sedang : 25-44, pasang kancing berwarna kuning";
                        } else if (skor >= 0 && skor <= 24) {
                            kesimpulanResikoJatuh = "Resiko Rendah : 0-24";
                        }
                    }

                    //asesmen anak
                    if (rsLaprm.getString("skrining_gizi").equals("anak")) {
                        if (skor >= 12) {
                            kesimpulanResikoJatuh = "Resiko Tinggi : >= 12, pasang kancing penanda berwarna kuning";
                        } else if (skor >= 7 && skor <= 11) {
                            kesimpulanResikoJatuh = "Resiko Rendah : 7-11";
                        } else if (skor >= 0 && skor <= 6) {
                            kesimpulanResikoJatuh = "";
                        }
                    }

                    if (!rsLaprm.getString("skrining_gizi").equals("anak") && !rsLaprm.getString("skrining_gizi").equals("dewasa")) {
                        param.put("dataResiko", "");
                        param.put("TotSkorResikoJatuh", "");
                        param.put("KesResikoJatuh", "");
                    } else {
                        param.put("dataResiko", faktorresikoigd);
                        param.put("TotSkorResikoJatuh", "Total Skor : (" + TotSkorRJ + ")");
                        param.put("KesResikoJatuh", "Kesimpulan Skor Resiko Jatuh :\n" + kesimpulanResikoJatuh);
                    }

                    Valid.MyReport("rptCetakPenilaianAwalKeperawatanIGD1.jasper", "report", "::[ Laporan Penilaian Awal Keperawatan IGD hal. 2 ]::",
                            "SELECT if(pa.nyeri='Ya',concat(pa.nyeri,', Lokasi : ',pa.ya_nyeri_lokasi),pa.nyeri) nyeri, pa.jenis_nyeri, "
                            + "if(pa.provocation='Lainnya',concat(pa.provocation,', ',pa.provocation_lain),pa.provocation) provokes, if(pa.quality='Lainnya',concat(pa.quality,', ',pa.quality_lain),pa.quality) quality, "
                            + "pa.radiation radiasi, pa.severity severity_nyeri, concat(pa.time,', Lama : ',pa.time_lama) time_nyeri, pa.skala_nyeri, pa.diagnosa_keperawatan, pa.tindakan_keperawatan, pa.evaluasi_keperawatan, "
                            + "if(pa.identifikasi1='ya','V','') iden1, if(pa.identifikasi2='ya','V','') iden2, if(pa.identifikasi3='ya','V','') iden3, if(pa.identifikasi4='ya','V','') iden4, if(pa.identifikasi5='ya','V','') iden5, "
                            + "if(pa.identifikasi6='ya','V','') iden6, if(pa.identifikasi7='ya','V','') iden7, if(pa.identifikasi8='ya','V','') iden8, if(pa.identifikasi9='ya','V','') iden9, if(pa.identifikasi10='ya','V','') iden10, "
                            + "if(pa.manajer_pelayanan='ya','V','') mpp, if(pa.discharge_planing='ya','V','') dp, concat('Tanggal, ',date_format(pa.tgl_verifikasi,'%d-%m-%Y'),'   Jam ',TIME_FORMAT(pa.tgl_verifikasi,'%H:%i:%S')) tglverif, "
                            + "pg1.nama dr_dpjp, pg2.nama perawat from penilaian_awal_keperawatan_igdrz pa inner join reg_periksa rp on rp.no_rawat=pa.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                            + "inner join pegawai pg1 on pg1.nik=pa.nip_dpjp inner join pegawai pg2 on pg2.nik=pa.nip_perawat where "
                            + "pa.no_rawat='" + rsLaprm.getString("no_rawat") + "'", param);

                    Valid.MyReport("rptCetakPenilaianAwalKeperawatanIGD.jasper", "report", "::[ Laporan Penilaian Awal Keperawatan IGD hal. 1 ]::",
                            "SELECT p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllhr, concat('Tanggal : ', date_format(pa.tanggal,'%d-%m-%Y'),', Pukul : ',time_format(pa.tanggal,'%H:%i-%S')) tgl, "
                            + "pa.keluhan_utama, pa.td tensi, pa.nadi, pa.nafas, pa.suhu, pa.bb, pa.tb, pa.asesmen_psikologis psikologis, if(pa.masalah_perilaku='Ada',concat(pa.masalah_perilaku,', Sebutkan : ',pa.sebutkan_perilaku),pa.masalah_perilaku) perilaku, "
                            + "p.stts_nikah, pa.hubungan_pasien hubungan, if(pa.tempat_tinggal='Lainnya',concat(pa.tempat_tinggal,', ',pa.lainya_tempt_tgl),pa.tempat_tinggal) tmpttgl, p.pekerjaan, "
                            + "pa.alat_bantu, if(pa.cacat_tubuh='Ada',concat(pa.cacat_tubuh,', ',pa.ada_cacat_tubuh),pa.cacat_tubuh) cacat, "
                            + "pa.riwayat_alergi, if(pa.alergi_obat='ya','V','') aler_obat, if(pa.alergi_obat='ya',pa.reaksi_alergi_obat,'') reak_obat, if(pa.alergi_makanan='ya','V','') aler_mak, "
                            + "if(pa.alergi_makanan='ya',pa.reaksi_alergi_makanan,'') reak_mak, if(pa.alergi_lainnya='ya','V','') aler_lain, if(pa.alergi_lainnya='ya',pa.reaksi_alergi_lainnya,'') reak_lain, "
                            + "if(pa.pin_kancing='ya','V','') pin, pa.alergi_diberitahukan, if(pa.nyeri='Ya',concat(pa.nyeri,', Lokasi : ',pa.ya_nyeri_lokasi),pa.nyeri) nyeri, pa.jenis_nyeri, "
                            + "if(pa.provocation='Lainnya',concat(pa.provocation,', ',pa.provocation_lain),pa.provocation) provokes, if(pa.quality='Lainnya',concat(pa.quality,', ',pa.quality_lain),pa.quality) quality, "
                            + "pa.radiation radiasi, pa.severity severity_nyeri, concat(pa.time,', Lama : ',pa.time_lama) time_nyeri, pa.diagnosa_keperawatan, pa.tindakan_keperawatan, pa.evaluasi_keperawatan, if(pa.identifikasi1='ya','V','') iden1, "
                            + "if(pa.identifikasi2='ya','V','') iden2, if(pa.identifikasi3='ya','V','') iden3, if(pa.identifikasi4='ya','V','') iden4, if(pa.identifikasi5='ya','V','') iden5, if(pa.identifikasi6='ya','V','') iden6, "
                            + "if(pa.identifikasi7='ya','V','') iden7, if(pa.identifikasi8='ya','V','') iden8, if(pa.identifikasi9='ya','V','') iden9, if(pa.identifikasi10='ya','V','') iden10, "
                            + "if(pa.manajer_pelayanan='ya','V','') mpp, if(pa.discharge_planing='ya','V','') dp from penilaian_awal_keperawatan_igdrz pa "
                            + "inner join reg_periksa rp on rp.no_rawat=pa.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                            + "pa.no_rawat='" + rsLaprm.getString("no_rawat") + "'", param);
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsLaprm != null) {
                    rsLaprm.close();
                }
                if (psLaprm != null) {
                    psLaprm.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void cetakAsesMedikObs() {
        try {
            psLaprm = koneksi.prepareStatement("select *, date_format(tgl_keluar_ponek,'%H:%i:%s') jamklr "
                    + "from penilaian_awal_medis_obstetri_ralan where no_rawat='" + TNoRW.getText() + "'");
            try {
                rsLaprm = psLaprm.executeQuery();
                while (rsLaprm.next()) {
                    Map<String, Object> param = new HashMap<>();
                    param.put("namars", akses.getnamars());
                    param.put("logo", Sequel.cariGambar("select logo from setting"));

                    if (rsLaprm.getString("cek_mengeluh_perut").equals("ya")) {
                        param.put("perut_mules", "Pasien mengeluh perut mules/nyeri mulai tgl. "
                                + Sequel.cariIsi("select concat(date_format(tgl_mengeluh_perut,'%d-%m-%Y'),', jam : ',time_format(tgl_mengeluh_perut,'%H:%i')) "
                                        + "from penilaian_awal_medis_obstetri_ralan where no_rawat='" + rsLaprm.getString("no_rawat") + "'"));
                    } else {
                        param.put("perut_mules", "Pasien mengeluh perut mules/nyeri mulai tgl. -, jam : -");
                    }

                    if (rsLaprm.getString("keluar_lendir").equals("Ya")) {
                        param.put("keluar_lendir", "Keluar lendir darah : " + rsLaprm.getString("keluar_lendir") + ", mulai tgl. "
                                + Sequel.cariIsi("select concat(date_format(tgl_lendir_ya,'%d-%m-%Y'),', jam : ',time_format(tgl_lendir_ya,'%H:%i')) "
                                        + "from penilaian_awal_medis_obstetri_ralan where no_rawat='" + rsLaprm.getString("no_rawat") + "'"));
                    } else {
                        param.put("keluar_lendir", "Keluar lendir darah : " + rsLaprm.getString("keluar_lendir") + ", mulai tgl. -, jam : -");
                    }

                    if (rsLaprm.getString("keluar_air").equals("Ya")) {
                        param.put("keluar_air", "Keluar air-air : " + rsLaprm.getString("keluar_air") + ", " + rsLaprm.getString("keluar_air_ya") + " mulai tgl. "
                                + Sequel.cariIsi("select concat(date_format(tgl_air_ya,'%d-%m-%Y'),', jam : ',time_format(tgl_air_ya,'%H:%i')) "
                                        + "from penilaian_awal_medis_obstetri_ralan where no_rawat='" + rsLaprm.getString("no_rawat") + "'"));
                    } else {
                        param.put("keluar_air", "Keluar air-air : " + rsLaprm.getString("keluar_air") + ", mulai tgl. -, jam : -");
                    }

                    if (rsLaprm.getString("mengeluh_pusing").equals("Ya")) {
                        param.put("mengeluh_pusing", "Pasien mengeluh pusing : Ya, mulai tgl. "
                                + Sequel.cariIsi("select concat(date_format(tgl_pusing,'%d-%m-%Y'),', jam : ',time_format(tgl_pusing,'%H:%i')) "
                                        + "from penilaian_awal_medis_obstetri_ralan where no_rawat='" + rsLaprm.getString("no_rawat") + "'"));
                    } else {
                        param.put("mengeluh_pusing", "Pasien mengeluh pusing : " + rsLaprm.getString("mengeluh_pusing") + ", mulai tgl. -, jam : -");
                    }

                    if (rsLaprm.getString("nyeri_ulu_hati").equals("Ya")) {
                        param.put("nyeri_ulu", "Nyeri Ulu Hati : Ya, mulai tgl. "
                                + Sequel.cariIsi("select concat(date_format(tgl_nyeri,'%d-%m-%Y'),', jam : ',time_format(tgl_nyeri,'%H:%i')) "
                                        + "from penilaian_awal_medis_obstetri_ralan where no_rawat='" + rsLaprm.getString("no_rawat") + "'"));
                    } else {
                        param.put("nyeri_ulu", "Nyeri Ulu Hati : " + rsLaprm.getString("nyeri_ulu_hati") + ", mulai tgl. -, jam : -");
                    }

                    if (rsLaprm.getString("pandangan_kabur").equals("Ya")) {
                        param.put("pandangan", "Pandangan kabur : Ya, mulai tgl. "
                                + Sequel.cariIsi("select concat(date_format(tgl_pandangan,'%d-%m-%Y'),', jam : ',time_format(tgl_pandangan,'%H:%i')) "
                                        + "from penilaian_awal_medis_obstetri_ralan where no_rawat='" + rsLaprm.getString("no_rawat") + "'"));
                    } else {
                        param.put("pandangan", "Pandangan kabur : " + rsLaprm.getString("pandangan_kabur") + ", mulai tgl. -, jam : -");
                    }

                    if (rsLaprm.getString("mual_muntah").equals("Ya")) {
                        param.put("mual", "Mual/Muntah : Ya, mulai tgl. "
                                + Sequel.cariIsi("select concat(date_format(tgl_mual_muntah,'%d-%m-%Y'),', jam : ',time_format(tgl_mual_muntah,'%H:%i')) "
                                        + "from penilaian_awal_medis_obstetri_ralan where no_rawat='" + rsLaprm.getString("no_rawat") + "'"));
                    } else {
                        param.put("mual", "Mual/Muntah : " + rsLaprm.getString("mual_muntah") + ", mulai tgl. -, jam : -");
                    }

                    if (rsLaprm.getString("batuk_pilek").equals("Ya")) {
                        param.put("batuk", "Batuk/Pilek : Ya, mulai tgl. "
                                + Sequel.cariIsi("select concat(date_format(tgl_batuk_pilek,'%d-%m-%Y'),', jam : ',time_format(tgl_batuk_pilek,'%H:%i')) "
                                        + "from penilaian_awal_medis_obstetri_ralan where no_rawat='" + rsLaprm.getString("no_rawat") + "'"));
                    } else {
                        param.put("batuk", "Batuk/Pilek : " + rsLaprm.getString("batuk_pilek") + ", mulai tgl. -, jam : -");
                    }

                    if (rsLaprm.getString("demam").equals("Ya")) {
                        param.put("demam", "Demam : Ya, mulai tgl. "
                                + Sequel.cariIsi("select concat(date_format(tgl_demam,'%d-%m-%Y'),', jam : ',time_format(tgl_demam,'%H:%i')) "
                                        + "from penilaian_awal_medis_obstetri_ralan where no_rawat='" + rsLaprm.getString("no_rawat") + "'"));
                    } else {
                        param.put("demam", "Demam : " + rsLaprm.getString("demam") + ", mulai tgl. -, jam : -");
                    }

                    if (rsLaprm.getString("pil").equals("ya")) {
                        param.put("cek_pil", "V");
                        param.put("pil", "Pil, lama : " + rsLaprm.getString("lama_pil") + " " + rsLaprm.getString("satuan_pil"));
                    } else {
                        param.put("cek_pil", "");
                        param.put("pil", "Pil, lama : -");
                    }

                    if (rsLaprm.getString("suntik_1_bln").equals("ya")) {
                        param.put("cek_suntik1", "V");
                        param.put("suntik1", "Suntik 1 bulan, lama : " + rsLaprm.getString("lama_1_bln") + " " + rsLaprm.getString("satuan_suntik1"));
                    } else {
                        param.put("cek_suntik1", "");
                        param.put("suntik1", "Suntik 1 bulan, lama : -");
                    }

                    if (rsLaprm.getString("suntik_3_bln").equals("ya")) {
                        param.put("cek_suntik3", "V");
                        param.put("suntik3", "Suntik 3 bulan, lama : " + rsLaprm.getString("lama_3_bln") + " " + rsLaprm.getString("satuan_suntik3"));
                    } else {
                        param.put("cek_suntik3", "");
                        param.put("suntik3", "Suntik 3 bulan, lama : -");
                    }

                    if (rsLaprm.getString("implan").equals("ya")) {
                        param.put("cek_implan", "V");
                        param.put("implan", "Implant, lama : " + rsLaprm.getString("lama_implan") + " " + rsLaprm.getString("satuan_implan"));
                    } else {
                        param.put("cek_implan", "");
                        param.put("implan", "Implant, lama : -");
                    }

                    if (!rsLaprm.getString("uk_usg").equals("")) {
                        param.put("uk_usg", "UK (USG) : " + rsLaprm.getString("uk_usg") + " minggu, tgl. USG : "
                                + Sequel.cariIsi("select date_format(tgl_usg,'%d-%m-%Y') from penilaian_awal_medis_obstetri_ralan where no_rawat='" + rsLaprm.getString("no_rawat") + "'"));
                    } else {
                        param.put("uk_usg", "UK (USG) : - minggu, tgl. USG : -");
                    }

                    if (rsLaprm.getString("cara_datang").equals("Sendiri")) {
                        param.put("cara_datang", rsLaprm.getString("cara_datang"));
                    } else if (rsLaprm.getString("cara_datang").equals("Rujukan Bidan/BPM")) {
                        param.put("cara_datang", rsLaprm.getString("cara_datang") + " : " + rsLaprm.getString("ket_rujukan_bidan"));
                    } else if (rsLaprm.getString("cara_datang").equals("PKM")) {
                        param.put("cara_datang", rsLaprm.getString("cara_datang") + " : " + rsLaprm.getString("ket_pkm"));
                    } else if (rsLaprm.getString("cara_datang").equals("SpOG")) {
                        param.put("cara_datang", rsLaprm.getString("ket_spog") + " SPOG");
                    } else if (rsLaprm.getString("cara_datang").equals("RS Lain")) {
                        param.put("cara_datang", rsLaprm.getString("cara_datang") + " : " + rsLaprm.getString("ket_rs_lain"));
                    } else {
                        param.put("cara_datang", "-");
                    }

                    if (rsLaprm.getString("his_kontraksi").equals("ya")) {
                        param.put("cek_his", "V");
                        param.put("his", "His/kontraksi : " + rsLaprm.getString("ket_his_kontraksi") + " x/10 mnt");
                    } else {
                        param.put("cek_his", "");
                        param.put("his", "His/kontraksi : ....  x/10 mnt");
                    }

                    if (rsLaprm.getString("meninggal").equals("ya")) {
                        param.put("jam_meninggal", rsLaprm.getString("jam_meninggal"));
                    } else {
                        param.put("jam_meninggal", "-");
                    }

                    if (rsLaprm.getString("cek_jam_keluar").equals("ya")) {
                        param.put("jam_keluar", rsLaprm.getString("jamklr"));
                    } else {
                        param.put("jam_keluar", "-");
                    }

                    Valid.MyReport("rptCetakPenilaianAwalMedisObstetriRalan.jasper", "report", "::[ Laporan Asesmen Medik Obstetri hal. 1 ]::",
                            "SELECT p.no_rkm_medis, p.nm_pasien, DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y') tgllhr, "
                            + "CONCAT('Tanggal : ',DATE_FORMAT(pa.tanggal,'%d-%m-%Y'),'  Pukul : ',TIME_FORMAT(pa.tanggal,'%H:%i')) mulai_penang, "
                            + "IF(pa.cervival='ya','V','') cer, IF(pa.rjp='ya','V','') rjp, IF(pa.defribilasi='ya','V','') def, IF(pa.intubasi='ya','V','') intu, IF(pa.vtp='ya','V','') vtp, "
                            + "IF(pa.dekompresi='ya','V','') dek, IF(pa.balut='ya','V','') bal, IF(pa.kateter='ya','V','') kat, IF(pa.ngt='ya','V','') ngt, IF(pa.infus='ya','V','') inf, "
                            + "IF(pa.obat='ya','V','') obt, IF(pa.tidak_ada='ya','V','') tdk_ada, pa.ket_obat, IF(pa.gangguan_jalan_nafas='ya','V','') ggnfs, IF(pa.paten='ya','V','') pat, "
                            + "IF(pa.obstruksi_partial='ya','V','') obsp, pa.data_obstruksi_partial, IF(pa.obstruksi_total='ya','V','') obst, IF(pa.trauma_jalan_nafas='ya','V','') traumajln, "
                            + "pa.trauma, IF(pa.resiko_aspirasi='ya','V','') res, pa.aspirasi, IF(pa.benda_asing='ya','V','') ben, pa.ket_benda_asing, pa.kesimpulan_jalan_nafas, "
                            + "pa.pernafasan, pa.data_pernafasan, pa.gerakan_dada, pa.tipe_pernafasan, pa.kesimpulan_pernafasan, pa.nadi_1, pa.kulit_mukosa, "
                            + "pa.akral_1, pa.crt, pa.kesimpulan_sirkulasi, pa.gcs_e, pa.gcs_v, pa.gcs_m, pa.pupil, pa.diameter_kanan, pa.diameter_kiri, pa.reflek_cahaya, "
                            + "pa.meningeal_sign, pa.lateralisasi, IF(pa.deformitas='ya','V','') def, IF(pa.contusio='ya','V','') con, IF(pa.penerima_edukasi='ya','V','') pen, "
                            + "IF(pa.tenderness='ya','V','') ten, IF(pa.swelling='ya','V','') swe, IF(pa.ekskoriasi='ya','V','') eks, IF(pa.abrasi='ya','V','') abr, "
                            + "IF(pa.burn='ya','V','') bur, IF(pa.laserasi='ya','V','') las, IF(pa.tidak_tampak_jelas='ya','V','') tdktampak, pa.alasan_masuk, "
                            + "pa.dengan_gr, pa.dengan_pr, pa.dengan_a, pa.hamil, pa.ket_dengan, "
                            + "IF(pa.periksa_bidan='Ya',CONCAT('Periksa ketempat bidan : Ya, Hasil / Riwayat Pemeriksaan Bidan : ',pa.hasil_periksa_bidan),CONCAT('Periksa ketempat bidan : ',pa.periksa_bidan,', Hasil / Riwayat Pemeriksaan Bidan : -')) prksa_bidan, "
                            + "IF(pa.riw_jalan_jauh='Ya',CONCAT('Riwayat perjalanan jauh : Ya, ',pa.ket_riw_jalan_jauh),CONCAT('Riwayat perjalanan jauh : ',pa.riw_jalan_jauh,', -')) riw_jln_jauh, "
                            + "pa.os_anc_bidan, pa.dengan_spog1, pa.jml_spog1, pa.dengan_spog2, pa.jml_spog2, IF(pa.hipertensi1='ya','V','') hip1, "
                            + "IF(pa.diabetes1='ya','V','') dm1, IF(pa.jantung1='ya','V','') jan1, IF(pa.asma1='ya','V','') asm1, IF(pa.lainnya1='ya','V','') lain1, "
                            + "pa.ket_lainnya1, IF(pa.hipertensi2='ya','V','') hip2, IF(pa.diabetes2='ya','V','') dm2, IF(pa.jantung2='ya','V','') jan2, IF(pa.asma2='ya','V','') asm2, "
                            + "IF(pa.lainnya2='ya','V','') lain2, pa.ket_lainnya2, pa.riw_ginekologi, pa.riwayat_kb_lain, pa.hpht, pa.hpl, pa.uk, pa.bb_blm_hamil, "
                            + "pa.bb_stlh_hamil, pa.tbi, pa.bmi, pa.lila, IF(pa.dismenorhoe='ya','V','') dismen, IF(pa.spoting='ya','V','') spot, IF(pa.menorrhagia='ya','V','') menor, "
                            + "IF(pa.metrohagia='ya','V','') metro, pa.keluhan_lain, pa.leopold1, pa.leopold2, pa.leopold3, pa.leopold4, IF(pa.nyeri_tekan='ya','V','') nyeri, "
                            + "IF(pa.bandle_ring='ya','V','') band, pa.nadi_2, pa.akral_2 FROM penilaian_awal_medis_obstetri_ralan pa INNER JOIN reg_periksa rp ON rp.no_rawat=pa.no_rawat "
                            + "INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis where pa.no_rawat='" + rsLaprm.getString("no_rawat") + "'", param);

                    Valid.MyReport("rptCetakPenilaianAwalMedisObstetriRalan1.jasper", "report", "::[ Laporan Asesmen Medik Obstetri hal. 2 ]::",
                            "SELECT pa.tfu, pa.taksiran_bb_janin, IF(pa.teratur='ya','V','')teratur, IF(pa.tdk_teratur='ya','V','')tdkteratur, IF(pa.trs_menerus='ya','V','')trsmenerus, "
                            + "pa.durasi, IF(pa.kuat='ya','V','')kuat, IF(pa.sedang='ya','V','')sedang, IF(pa.lemah='ya','V','')lemah, pa.auskultasi, IF(pa.bersih='ya','V','')bersih, "
                            + "IF(pa.oedema='ya','V','')odema, IF(pa.ruptur='ya','V','')ruptur, IF(pa.candiloma='ya','V','')candi, pa.pemeriksaan_genitalia_lain, pa.inspeksi, pa.konsistensi, "
                            + "pa.periksa_dlm_obstetri, pa.inspekulum, pa.diagnosis_sementara, pa.icd_10, pa.rencana_instruksi, pa.planing, pa.telah_diberikan_informasi_edukasi, "
                            + "pa.rencana_asuhan_diharapkan, p1.nama pemberi_edukasi, pa.penerima_edukasi, p2.nama nmdokter, DATE_FORMAT(pa.tgl_keluar_ponek,'%d-%m-%Y') tglkeluar, "
                            + "pa.opname_diruang, pa.indikasi_msk, pa.dipulangkan_kontrol_ke, pa.dirujuk_ke, pa.alasan_dirujuk, pa.penyebab, pa.k_u, pa.td, pa.hr, pa.rr, pa.temp, "
                            + "pa.spo2, pa.gcs_pulang, p3.nama nmbidan, p4.nama nmdpjp FROM penilaian_awal_medis_obstetri_ralan pa "
                            + "inner join pegawai p1 on p1.nik=pa.nip_pemberi_edukasi inner join pegawai p2 on p2.nik=pa.nip_dokter "
                            + "inner join pegawai p3 on p3.nik=pa.nip_bidan inner join pegawai p4 on p4.nik=pa.nip_dpjp where "
                            + "pa.no_rawat='" + rsLaprm.getString("no_rawat") + "'", param);
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsLaprm != null) {
                    rsLaprm.close();
                }
                if (psLaprm != null) {
                    psLaprm.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
}
