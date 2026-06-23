package rekammedis;

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
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import kepegawaian.DlgCariPetugas;
import laporan.DlgHasilPenunjangMedis;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import simrskhanza.DlgCariDokter;

/**
 *
 * @author dosen
 */
public class RMEvaluasiPraAnestesi extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps, ps1, ps2, ps3, ps4, ps5, ps6;
    private ResultSet rs, rs1, rs2, rs3, rs4, rs5, rs6;
    private int i = 0, x = 0, pilihDokter = 0, pilihan = 0;
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    private String nipSpesBedah = "", nipSpesAnes = "";
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public RMEvaluasiPraAnestesi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //data di tabel grid rata tengah
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);

        tabMode = new DefaultTableModel(null, new String[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Lahir", "Ruang Perawatan", "Tanggal", "Jam", "Anamnesa Dari", "Diagnosa", "Dokter Bedah", "Dokter Anestesi", 
            "ruang_rawat", "alergi_obat", "anamnesa_dari", "tgl_ruang", "jam_ruang", "diagnosa", "rencana_tindakan", "td", "bb", "nadi", "rr", "tb", "suhu", "obat_dikonsumsi", 
            "ket_obat_dikonsumsi", "tgl_operasi", "nip_spesialis_bedah", "nip_spesialis_anestesi", "riwayat_anestesi", "bebas", "leher_pendek", "gerak_leher", "sulit_ventilasi", 
            "alat_bantu", "massa", "obesitas", "protusi", "mallampathy", "buka_mulut", "jarak_thyro", "gigi", "ps_asa", "penyulit", "rencana_anestesi", "instruksi_anestesi", 
            "waktu_simpan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbEvaluasi.setModel(tabMode);
        tbEvaluasi.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbEvaluasi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 47; i++) {
            TableColumn column = tbEvaluasi.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(220);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else if (i == 4) {
                column.setPreferredWidth(250);
            } else if (i == 5) {
                column.setPreferredWidth(75);
            } else if (i == 6) {
                column.setPreferredWidth(75);
            } else if (i == 7) {
                column.setPreferredWidth(80);
            } else if (i == 8) {
                column.setPreferredWidth(250);
            } else if (i == 9) {
                column.setPreferredWidth(220);
            } else if (i == 10) {
                column.setPreferredWidth(220);
            } else if (i == 11) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 12) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 13) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
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
            } else if (i == 18) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 19) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
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
            } else if (i == 34) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 35) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 36) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 37) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 38) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 39) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 40) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 41) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 42) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 43) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 44) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 45) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 46) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbEvaluasi.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1 = new DefaultTableModel(null, new Object[]{"No. RM", "Nama Pasien", "Data Template"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbTemplate.setModel(tabMode1);
        tbTemplate.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbTemplate.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 3; i++) {
            TableColumn column = tbTemplate.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(60);
            } else if (i == 1) {
                column.setPreferredWidth(200);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            }
        }
        tbTemplate.setDefaultRenderer(Object.class, new WarnaTable());
        
        Ttd.setDocument(new batasInput((int) 7).getKata(Ttd));
        Tbb.setDocument(new batasInput((int) 7).getKata(Tbb));        
        Tnadi.setDocument(new batasInput((int) 7).getKata(Tnadi));
        Trr.setDocument(new batasInput((int) 7).getKata(Trr));        
        Ttb.setDocument(new batasInput((int) 7).getKata(Ttb));
        Tsuhu.setDocument(new batasInput((int) 7).getKata(Tsuhu));        
        TketObat.setDocument(new batasInput((int) 200).getKata(TketObat));        
        Tjarak.setDocument(new batasInput((int) 7).getKata(Tjarak));        
        Tgigi.setDocument(new batasInput((int) 100).getKata(Tgigi));        
        TpsAsa.setDocument(new batasInput((int) 200).getKata(TpsAsa));        
        Tpenyulit.setDocument(new batasInput((int) 200).getKata(Tpenyulit));        
        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));        
        
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
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("RMEvaluasiPraAnestesi")) {
                    if (pilihDokter == 1) {
                        if (dokter.getTable().getSelectedRow() != -1) {
                            nipSpesBedah  = dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString();
                            TnmSpesBedah.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                            BtnSpesBedah.requestFocus();
                        }                        
                    } else if (pilihDokter == 2) {
                        if (dokter.getTable().getSelectedRow() != -1) {
                            nipSpesAnes = dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString();
                            TnmSpesAnes.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                            BtnSpesAnes.requestFocus();
                        }                        
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
        MnHasilPemeriksaanPenunjang = new javax.swing.JMenuItem();
        MnDokumenJangMed = new javax.swing.JMenuItem();
        WindowTemplate = new javax.swing.JDialog();
        internalFrame5 = new widget.InternalFrame();
        jPanel1 = new javax.swing.JPanel();
        Scroll2 = new widget.ScrollPane();
        tbTemplate = new widget.Table();
        Scroll3 = new widget.ScrollPane();
        Ttemplate = new widget.TextArea();
        panelisi4 = new widget.panelisi();
        jLabel36 = new widget.Label();
        TCari1 = new widget.TextBox();
        BtnCari1 = new widget.Button();
        BtnCopas = new widget.Button();
        BtnCloseIn1 = new widget.Button();
        internalFrame1 = new widget.InternalFrame();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnGanti = new widget.Button();
        jLabel95 = new widget.Label();
        cmbPilihCetak = new widget.ComboBox();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass9 = new widget.panelisi();
        Scroll1 = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        jLabel10 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel63 = new widget.Label();
        TrgRawat = new widget.TextBox();
        jLabel87 = new widget.Label();
        scrollPane11 = new widget.ScrollPane();
        TalergiObat = new widget.TextArea();
        jLabel70 = new widget.Label();
        TnmSpesBedah = new widget.TextBox();
        TnmSpesAnes = new widget.TextBox();
        BtnSpesBedah = new widget.Button();
        BtnSpesAnes = new widget.Button();
        cmbJam1 = new widget.ComboBox();
        cmbMnt1 = new widget.ComboBox();
        cmbDtk1 = new widget.ComboBox();
        jLabel276 = new widget.Label();
        TtglRuang = new widget.Tanggal();
        jLabel77 = new widget.Label();
        cmbAnamnesa = new widget.ComboBox();
        jLabel64 = new widget.Label();
        jLabel65 = new widget.Label();
        scrollPane14 = new widget.ScrollPane();
        Tdiagnosa = new widget.TextArea();
        jLabel94 = new widget.Label();
        scrollPane15 = new widget.ScrollPane();
        TrencanaTindakan = new widget.TextArea();
        jLabel96 = new widget.Label();
        jLabel97 = new widget.Label();
        jLabel98 = new widget.Label();
        Ttd = new widget.TextBox();
        jLabel99 = new widget.Label();
        Tbb = new widget.TextBox();
        jLabel100 = new widget.Label();
        jLabel101 = new widget.Label();
        Tnadi = new widget.TextBox();
        Trr = new widget.TextBox();
        jLabel102 = new widget.Label();
        jLabel103 = new widget.Label();
        Tsuhu = new widget.TextBox();
        Ttb = new widget.TextBox();
        jLabel104 = new widget.Label();
        jLabel105 = new widget.Label();
        jLabel106 = new widget.Label();
        cmbObat = new widget.ComboBox();
        TketObat = new widget.TextBox();
        jLabel107 = new widget.Label();
        TtglOperasi = new widget.Tanggal();
        jLabel76 = new widget.Label();
        scrollPane16 = new widget.ScrollPane();
        TriwAnestesi = new widget.TextArea();
        jLabel108 = new widget.Label();
        jLabel109 = new widget.Label();
        jLabel110 = new widget.Label();
        cmbBebas = new widget.ComboBox();
        jLabel111 = new widget.Label();
        cmbLeher = new widget.ComboBox();
        jLabel112 = new widget.Label();
        cmbGerak = new widget.ComboBox();
        jLabel113 = new widget.Label();
        cmbSulit = new widget.ComboBox();
        jLabel114 = new widget.Label();
        cmbAlat = new widget.ComboBox();
        jLabel115 = new widget.Label();
        cmbMassa = new widget.ComboBox();
        jLabel116 = new widget.Label();
        cmbObes = new widget.ComboBox();
        jLabel117 = new widget.Label();
        cmbProtusi = new widget.ComboBox();
        jLabel118 = new widget.Label();
        cmbMallam = new widget.ComboBox();
        jLabel119 = new widget.Label();
        cmbBuka = new widget.ComboBox();
        jLabel120 = new widget.Label();
        Tjarak = new widget.TextBox();
        jLabel121 = new widget.Label();
        Tgigi = new widget.TextBox();
        jLabel122 = new widget.Label();
        jLabel123 = new widget.Label();
        TpsAsa = new widget.TextBox();
        jLabel124 = new widget.Label();
        Tpenyulit = new widget.TextBox();
        scrollPane17 = new widget.ScrollPane();
        TrencanaAnes = new widget.TextArea();
        jLabel125 = new widget.Label();
        jLabel126 = new widget.Label();
        scrollPane18 = new widget.ScrollPane();
        Tinstruksi = new widget.TextArea();
        BtnAlergiObat = new widget.Button();
        BtnDiagnosa = new widget.Button();
        BtnRencTindakan = new widget.Button();
        BtnRiwAnestesi = new widget.Button();
        BtnRencAnestesi = new widget.Button();
        BtnInstruksi = new widget.Button();
        PanelInput1 = new javax.swing.JPanel();
        Scroll = new widget.ScrollPane();
        tbEvaluasi = new widget.Table();
        panelGlass11 = new widget.panelisi();
        panelGlass12 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        panelGlass10 = new widget.panelisi();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

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
        jPopupMenu1.add(MnHasilPemeriksaanPenunjang);

        MnDokumenJangMed.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDokumenJangMed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDokumenJangMed.setText("Dokumen Penunjang Medis");
        MnDokumenJangMed.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDokumenJangMed.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDokumenJangMed.setIconTextGap(5);
        MnDokumenJangMed.setName("MnDokumenJangMed"); // NOI18N
        MnDokumenJangMed.setPreferredSize(new java.awt.Dimension(195, 26));
        MnDokumenJangMed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDokumenJangMedActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnDokumenJangMed);

        WindowTemplate.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowTemplate.setName("WindowTemplate"); // NOI18N
        WindowTemplate.setUndecorated(true);
        WindowTemplate.setResizable(false);

        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame5.setLayout(new java.awt.BorderLayout());

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(816, 250));
        jPanel1.setLayout(new java.awt.GridLayout(1, 2));

        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);
        Scroll2.setPreferredSize(new java.awt.Dimension(452, 250));

        tbTemplate.setToolTipText("Silahkan klik salah satu data yang akan dipakai");
        tbTemplate.setName("tbTemplate"); // NOI18N
        tbTemplate.getTableHeader().setReorderingAllowed(false);
        tbTemplate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTemplateMouseClicked(evt);
            }
        });
        Scroll2.setViewportView(tbTemplate);

        jPanel1.add(Scroll2);

        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);

        Ttemplate.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Baca Template Dipilih ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Ttemplate.setColumns(20);
        Ttemplate.setRows(5);
        Ttemplate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Ttemplate.setName("Ttemplate"); // NOI18N
        Ttemplate.setPreferredSize(new java.awt.Dimension(210, 4000));
        Scroll3.setViewportView(Ttemplate);

        jPanel1.add(Scroll3);

        internalFrame5.add(jPanel1, java.awt.BorderLayout.CENTER);

        panelisi4.setBackground(new java.awt.Color(255, 150, 255));
        panelisi4.setName("panelisi4"); // NOI18N
        panelisi4.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 9));

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Key Word :");
        jLabel36.setName("jLabel36"); // NOI18N
        jLabel36.setPreferredSize(new java.awt.Dimension(70, 23));
        jLabel36.setRequestFocusEnabled(false);
        panelisi4.add(jLabel36);

        TCari1.setForeground(new java.awt.Color(0, 0, 0));
        TCari1.setName("TCari1"); // NOI18N
        TCari1.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari1KeyPressed(evt);
            }
        });
        panelisi4.add(TCari1);

        BtnCari1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari1.setText("Tampilkan Data");
        BtnCari1.setToolTipText("Alt+1");
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
        panelisi4.add(BtnCari1);

        BtnCopas.setForeground(new java.awt.Color(0, 0, 0));
        BtnCopas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/paste.png"))); // NOI18N
        BtnCopas.setText("Copy & Paste");
        BtnCopas.setToolTipText("Alt+U");
        BtnCopas.setName("BtnCopas"); // NOI18N
        BtnCopas.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnCopas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCopasActionPerformed(evt);
            }
        });
        panelisi4.add(BtnCopas);

        BtnCloseIn1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn1.setText("Tutup");
        BtnCloseIn1.setToolTipText("Alt+U");
        BtnCloseIn1.setName("BtnCloseIn1"); // NOI18N
        BtnCloseIn1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnCloseIn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn1ActionPerformed(evt);
            }
        });
        panelisi4.add(BtnCloseIn1);

        internalFrame5.add(panelisi4, java.awt.BorderLayout.PAGE_END);

        WindowTemplate.getContentPane().add(internalFrame5, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Evaluasi Pra Anestesi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(55, 47));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnSimpan.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
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
        BtnHapus.setText("Hapus");
        BtnHapus.setToolTipText("Alt+H");
        BtnHapus.setName("BtnHapus"); // NOI18N
        BtnHapus.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnHapus);

        BtnGanti.setForeground(new java.awt.Color(0, 0, 0));
        BtnGanti.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
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

        jLabel95.setForeground(new java.awt.Color(0, 0, 0));
        jLabel95.setText("Cetak Dalam Bentuk :");
        jLabel95.setName("jLabel95"); // NOI18N
        jLabel95.setPreferredSize(new java.awt.Dimension(120, 23));
        panelGlass8.add(jLabel95);

        cmbPilihCetak.setForeground(new java.awt.Color(0, 0, 0));
        cmbPilihCetak.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TTE (QR Code)", "TTD Basah" }));
        cmbPilihCetak.setName("cmbPilihCetak"); // NOI18N
        cmbPilihCetak.setPreferredSize(new java.awt.Dimension(105, 23));
        panelGlass8.add(cmbPilihCetak);

        BtnPrint.setForeground(new java.awt.Color(0, 0, 0));
        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
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

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(55, 47));
        panelGlass9.setLayout(new java.awt.BorderLayout());

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);
        Scroll1.setPreferredSize(new java.awt.Dimension(600, 402));

        FormInput.setBackground(new java.awt.Color(255, 255, 255));
        FormInput.setBorder(null);
        FormInput.setToolTipText("Klik kanan untuk melihat hasil pemeriksaan penunjang medis");
        FormInput.setComponentPopupMenu(jPopupMenu1);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(870, 917));
        FormInput.setLayout(null);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("No. Rawat :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 10, 140, 23);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        FormInput.add(TNoRw);
        TNoRw.setBounds(145, 10, 131, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(279, 10, 70, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(352, 10, 407, 23);

        jLabel63.setForeground(new java.awt.Color(0, 0, 0));
        jLabel63.setText("Ruang Rawat :");
        jLabel63.setName("jLabel63"); // NOI18N
        jLabel63.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam1MouseReleased(evt);
            }
        });
        FormInput.add(jLabel63);
        jLabel63.setBounds(235, 154, 90, 23);

        TrgRawat.setEditable(false);
        TrgRawat.setForeground(new java.awt.Color(0, 0, 0));
        TrgRawat.setName("TrgRawat"); // NOI18N
        TrgRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt1MouseReleased(evt);
            }
        });
        FormInput.add(TrgRawat);
        TrgRawat.setBounds(330, 154, 430, 23);

        jLabel87.setForeground(new java.awt.Color(0, 0, 0));
        jLabel87.setText("Alergi Obat :");
        jLabel87.setName("jLabel87"); // NOI18N
        jLabel87.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk1MouseReleased(evt);
            }
        });
        FormInput.add(jLabel87);
        jLabel87.setBounds(0, 38, 140, 23);

        scrollPane11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane11.setName("scrollPane11"); // NOI18N

        TalergiObat.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TalergiObat.setColumns(20);
        TalergiObat.setRows(5);
        TalergiObat.setName("TalergiObat"); // NOI18N
        TalergiObat.setPreferredSize(new java.awt.Dimension(162, 2000));
        TalergiObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TalergiObatKeyPressed(evt);
            }
        });
        scrollPane11.setViewportView(TalergiObat);

        FormInput.add(scrollPane11);
        scrollPane11.setBounds(145, 38, 615, 110);

        jLabel70.setForeground(new java.awt.Color(0, 0, 0));
        jLabel70.setText("Spesialis Bedah :");
        jLabel70.setName("jLabel70"); // NOI18N
        FormInput.add(jLabel70);
        jLabel70.setBounds(235, 452, 110, 23);

        TnmSpesBedah.setEditable(false);
        TnmSpesBedah.setForeground(new java.awt.Color(0, 0, 0));
        TnmSpesBedah.setName("TnmSpesBedah"); // NOI18N
        FormInput.add(TnmSpesBedah);
        TnmSpesBedah.setBounds(350, 452, 410, 23);

        TnmSpesAnes.setEditable(false);
        TnmSpesAnes.setForeground(new java.awt.Color(0, 0, 0));
        TnmSpesAnes.setName("TnmSpesAnes"); // NOI18N
        FormInput.add(TnmSpesAnes);
        TnmSpesAnes.setBounds(350, 480, 410, 23);

        BtnSpesBedah.setForeground(new java.awt.Color(0, 0, 0));
        BtnSpesBedah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSpesBedah.setToolTipText("Alt+1");
        BtnSpesBedah.setName("BtnSpesBedah"); // NOI18N
        BtnSpesBedah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSpesBedahActionPerformed(evt);
            }
        });
        FormInput.add(BtnSpesBedah);
        BtnSpesBedah.setBounds(760, 452, 28, 23);

        BtnSpesAnes.setForeground(new java.awt.Color(0, 0, 0));
        BtnSpesAnes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSpesAnes.setToolTipText("Alt+1");
        BtnSpesAnes.setName("BtnSpesAnes"); // NOI18N
        BtnSpesAnes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSpesAnesActionPerformed(evt);
            }
        });
        FormInput.add(BtnSpesAnes);
        BtnSpesAnes.setBounds(760, 480, 28, 23);

        cmbJam1.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam1.setName("cmbJam1"); // NOI18N
        cmbJam1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam1MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam1);
        cmbJam1.setBounds(476, 182, 45, 23);

        cmbMnt1.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt1.setName("cmbMnt1"); // NOI18N
        cmbMnt1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt1MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt1);
        cmbMnt1.setBounds(527, 182, 45, 23);

        cmbDtk1.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk1.setName("cmbDtk1"); // NOI18N
        cmbDtk1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk1MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk1);
        cmbDtk1.setBounds(579, 182, 45, 23);

        jLabel276.setForeground(new java.awt.Color(0, 0, 0));
        jLabel276.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel276.setText("Wita");
        jLabel276.setName("jLabel276"); // NOI18N
        FormInput.add(jLabel276);
        jLabel276.setBounds(630, 182, 50, 23);

        TtglRuang.setEditable(false);
        TtglRuang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "22-06-2026" }));
        TtglRuang.setDisplayFormat("dd-MM-yyyy");
        TtglRuang.setName("TtglRuang"); // NOI18N
        TtglRuang.setOpaque(false);
        TtglRuang.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglRuang);
        TtglRuang.setBounds(330, 182, 90, 23);

        jLabel77.setForeground(new java.awt.Color(0, 0, 0));
        jLabel77.setText("Anamnesa Dari :");
        jLabel77.setName("jLabel77"); // NOI18N
        FormInput.add(jLabel77);
        jLabel77.setBounds(0, 154, 140, 23);

        cmbAnamnesa.setBackground(new java.awt.Color(245, 253, 240));
        cmbAnamnesa.setForeground(new java.awt.Color(0, 0, 0));
        cmbAnamnesa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Pasien", "Keluarga", "ODC", "Rawat Inap", "Lain-lain" }));
        cmbAnamnesa.setLightWeightPopupEnabled(false);
        cmbAnamnesa.setName("cmbAnamnesa"); // NOI18N
        FormInput.add(cmbAnamnesa);
        cmbAnamnesa.setBounds(145, 154, 90, 23);

        jLabel64.setForeground(new java.awt.Color(0, 0, 0));
        jLabel64.setText("Tanggal :");
        jLabel64.setName("jLabel64"); // NOI18N
        FormInput.add(jLabel64);
        jLabel64.setBounds(235, 182, 90, 23);

        jLabel65.setForeground(new java.awt.Color(0, 0, 0));
        jLabel65.setText("Jam :");
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput.add(jLabel65);
        jLabel65.setBounds(420, 182, 50, 23);

        scrollPane14.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane14.setName("scrollPane14"); // NOI18N

        Tdiagnosa.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tdiagnosa.setColumns(20);
        Tdiagnosa.setRows(5);
        Tdiagnosa.setName("Tdiagnosa"); // NOI18N
        Tdiagnosa.setPreferredSize(new java.awt.Dimension(162, 2000));
        Tdiagnosa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdiagnosaKeyPressed(evt);
            }
        });
        scrollPane14.setViewportView(Tdiagnosa);

        FormInput.add(scrollPane14);
        scrollPane14.setBounds(145, 210, 615, 60);

        jLabel94.setForeground(new java.awt.Color(0, 0, 0));
        jLabel94.setText("Diagnosa :");
        jLabel94.setName("jLabel94"); // NOI18N
        FormInput.add(jLabel94);
        jLabel94.setBounds(0, 210, 140, 23);

        scrollPane15.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane15.setName("scrollPane15"); // NOI18N

        TrencanaTindakan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TrencanaTindakan.setColumns(20);
        TrencanaTindakan.setRows(5);
        TrencanaTindakan.setName("TrencanaTindakan"); // NOI18N
        TrencanaTindakan.setPreferredSize(new java.awt.Dimension(162, 2000));
        TrencanaTindakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TrencanaTindakanKeyPressed(evt);
            }
        });
        scrollPane15.setViewportView(TrencanaTindakan);

        FormInput.add(scrollPane15);
        scrollPane15.setBounds(145, 275, 615, 60);

        jLabel96.setForeground(new java.awt.Color(0, 0, 0));
        jLabel96.setText("Rencana Tindakan :");
        jLabel96.setName("jLabel96"); // NOI18N
        FormInput.add(jLabel96);
        jLabel96.setBounds(0, 275, 140, 23);

        jLabel97.setForeground(new java.awt.Color(0, 0, 0));
        jLabel97.setText("Tanda - tanda Vital Sebelum Tindakan :");
        jLabel97.setName("jLabel97"); // NOI18N
        FormInput.add(jLabel97);
        jLabel97.setBounds(0, 340, 230, 23);

        jLabel98.setForeground(new java.awt.Color(0, 0, 0));
        jLabel98.setText("TD :");
        jLabel98.setName("jLabel98"); // NOI18N
        FormInput.add(jLabel98);
        jLabel98.setBounds(0, 368, 140, 23);

        Ttd.setForeground(new java.awt.Color(0, 0, 0));
        Ttd.setName("Ttd"); // NOI18N
        Ttd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtdKeyPressed(evt);
            }
        });
        FormInput.add(Ttd);
        Ttd.setBounds(145, 368, 70, 23);

        jLabel99.setForeground(new java.awt.Color(0, 0, 0));
        jLabel99.setText("BB :");
        jLabel99.setName("jLabel99"); // NOI18N
        FormInput.add(jLabel99);
        jLabel99.setBounds(0, 396, 140, 23);

        Tbb.setForeground(new java.awt.Color(0, 0, 0));
        Tbb.setName("Tbb"); // NOI18N
        Tbb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbbKeyPressed(evt);
            }
        });
        FormInput.add(Tbb);
        Tbb.setBounds(145, 396, 70, 23);

        jLabel100.setForeground(new java.awt.Color(0, 0, 0));
        jLabel100.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel100.setText("mmHg       Nadi :");
        jLabel100.setName("jLabel100"); // NOI18N
        FormInput.add(jLabel100);
        jLabel100.setBounds(220, 368, 80, 23);

        jLabel101.setForeground(new java.awt.Color(0, 0, 0));
        jLabel101.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel101.setText("Kg.              RR :");
        jLabel101.setName("jLabel101"); // NOI18N
        FormInput.add(jLabel101);
        jLabel101.setBounds(220, 396, 80, 23);

        Tnadi.setForeground(new java.awt.Color(0, 0, 0));
        Tnadi.setName("Tnadi"); // NOI18N
        Tnadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnadiKeyPressed(evt);
            }
        });
        FormInput.add(Tnadi);
        Tnadi.setBounds(303, 368, 70, 23);

        Trr.setForeground(new java.awt.Color(0, 0, 0));
        Trr.setName("Trr"); // NOI18N
        Trr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TrrKeyPressed(evt);
            }
        });
        FormInput.add(Trr);
        Trr.setBounds(303, 396, 70, 23);

        jLabel102.setForeground(new java.awt.Color(0, 0, 0));
        jLabel102.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel102.setText("x/menit       TB :");
        jLabel102.setName("jLabel102"); // NOI18N
        FormInput.add(jLabel102);
        jLabel102.setBounds(380, 368, 80, 23);

        jLabel103.setForeground(new java.awt.Color(0, 0, 0));
        jLabel103.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel103.setText("x/menit   Suhu :");
        jLabel103.setName("jLabel103"); // NOI18N
        FormInput.add(jLabel103);
        jLabel103.setBounds(380, 396, 80, 23);

        Tsuhu.setForeground(new java.awt.Color(0, 0, 0));
        Tsuhu.setName("Tsuhu"); // NOI18N
        Tsuhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TsuhuKeyPressed(evt);
            }
        });
        FormInput.add(Tsuhu);
        Tsuhu.setBounds(463, 396, 70, 23);

        Ttb.setForeground(new java.awt.Color(0, 0, 0));
        Ttb.setName("Ttb"); // NOI18N
        Ttb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtbKeyPressed(evt);
            }
        });
        FormInput.add(Ttb);
        Ttb.setBounds(463, 368, 70, 23);

        jLabel104.setForeground(new java.awt.Color(0, 0, 0));
        jLabel104.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel104.setText("Cm.");
        jLabel104.setName("jLabel104"); // NOI18N
        FormInput.add(jLabel104);
        jLabel104.setBounds(540, 368, 30, 23);

        jLabel105.setForeground(new java.awt.Color(0, 0, 0));
        jLabel105.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel105.setText("°C");
        jLabel105.setName("jLabel105"); // NOI18N
        FormInput.add(jLabel105);
        jLabel105.setBounds(540, 396, 30, 23);

        jLabel106.setForeground(new java.awt.Color(0, 0, 0));
        jLabel106.setText("Obat Yang Dikonsumsi :");
        jLabel106.setName("jLabel106"); // NOI18N
        FormInput.add(jLabel106);
        jLabel106.setBounds(0, 424, 140, 23);

        cmbObat.setBackground(new java.awt.Color(245, 253, 240));
        cmbObat.setForeground(new java.awt.Color(0, 0, 0));
        cmbObat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak Ada", "Ada" }));
        cmbObat.setLightWeightPopupEnabled(false);
        cmbObat.setName("cmbObat"); // NOI18N
        cmbObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbObatActionPerformed(evt);
            }
        });
        FormInput.add(cmbObat);
        cmbObat.setBounds(145, 424, 80, 23);

        TketObat.setForeground(new java.awt.Color(0, 0, 0));
        TketObat.setName("TketObat"); // NOI18N
        TketObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketObatKeyPressed(evt);
            }
        });
        FormInput.add(TketObat);
        TketObat.setBounds(230, 424, 530, 23);

        jLabel107.setForeground(new java.awt.Color(0, 0, 0));
        jLabel107.setText("Tgl. Operasi :");
        jLabel107.setName("jLabel107"); // NOI18N
        FormInput.add(jLabel107);
        jLabel107.setBounds(0, 452, 140, 23);

        TtglOperasi.setEditable(false);
        TtglOperasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "22-06-2026" }));
        TtglOperasi.setDisplayFormat("dd-MM-yyyy");
        TtglOperasi.setName("TtglOperasi"); // NOI18N
        TtglOperasi.setOpaque(false);
        TtglOperasi.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglOperasi);
        TtglOperasi.setBounds(145, 452, 90, 23);

        jLabel76.setForeground(new java.awt.Color(0, 0, 0));
        jLabel76.setText("Spesialis Anestesi :");
        jLabel76.setName("jLabel76"); // NOI18N
        FormInput.add(jLabel76);
        jLabel76.setBounds(235, 480, 110, 23);

        scrollPane16.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane16.setName("scrollPane16"); // NOI18N

        TriwAnestesi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TriwAnestesi.setColumns(20);
        TriwAnestesi.setRows(5);
        TriwAnestesi.setName("TriwAnestesi"); // NOI18N
        TriwAnestesi.setPreferredSize(new java.awt.Dimension(162, 2000));
        TriwAnestesi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TriwAnestesiKeyPressed(evt);
            }
        });
        scrollPane16.setViewportView(TriwAnestesi);

        FormInput.add(scrollPane16);
        scrollPane16.setBounds(145, 508, 615, 60);

        jLabel108.setForeground(new java.awt.Color(0, 0, 0));
        jLabel108.setText("<html><div style='text-align:right'>Riwayat Anestesi & Komplikasi :</div></html>");
        jLabel108.setName("jLabel108"); // NOI18N
        FormInput.add(jLabel108);
        jLabel108.setBounds(0, 508, 140, 30);

        jLabel109.setForeground(new java.awt.Color(0, 0, 0));
        jLabel109.setText("Evaluasi Jalan Nafas / Gigi Geligi / Leher :");
        jLabel109.setName("jLabel109"); // NOI18N
        FormInput.add(jLabel109);
        jLabel109.setBounds(0, 574, 230, 23);

        jLabel110.setForeground(new java.awt.Color(0, 0, 0));
        jLabel110.setText("Bebas :");
        jLabel110.setName("jLabel110"); // NOI18N
        FormInput.add(jLabel110);
        jLabel110.setBounds(0, 602, 140, 23);

        cmbBebas.setBackground(new java.awt.Color(245, 253, 240));
        cmbBebas.setForeground(new java.awt.Color(0, 0, 0));
        cmbBebas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbBebas.setLightWeightPopupEnabled(false);
        cmbBebas.setName("cmbBebas"); // NOI18N
        FormInput.add(cmbBebas);
        cmbBebas.setBounds(145, 602, 60, 23);

        jLabel111.setForeground(new java.awt.Color(0, 0, 0));
        jLabel111.setText("Leher Pendek :");
        jLabel111.setName("jLabel111"); // NOI18N
        FormInput.add(jLabel111);
        jLabel111.setBounds(205, 602, 120, 23);

        cmbLeher.setBackground(new java.awt.Color(245, 253, 240));
        cmbLeher.setForeground(new java.awt.Color(0, 0, 0));
        cmbLeher.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbLeher.setLightWeightPopupEnabled(false);
        cmbLeher.setName("cmbLeher"); // NOI18N
        FormInput.add(cmbLeher);
        cmbLeher.setBounds(330, 602, 60, 23);

        jLabel112.setForeground(new java.awt.Color(0, 0, 0));
        jLabel112.setText("Gerak Pendek :");
        jLabel112.setName("jLabel112"); // NOI18N
        FormInput.add(jLabel112);
        jLabel112.setBounds(390, 602, 100, 23);

        cmbGerak.setBackground(new java.awt.Color(245, 253, 240));
        cmbGerak.setForeground(new java.awt.Color(0, 0, 0));
        cmbGerak.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbGerak.setLightWeightPopupEnabled(false);
        cmbGerak.setName("cmbGerak"); // NOI18N
        FormInput.add(cmbGerak);
        cmbGerak.setBounds(496, 602, 60, 23);

        jLabel113.setForeground(new java.awt.Color(0, 0, 0));
        jLabel113.setText("Sulit Ventilasi :");
        jLabel113.setName("jLabel113"); // NOI18N
        FormInput.add(jLabel113);
        jLabel113.setBounds(0, 630, 140, 23);

        cmbSulit.setBackground(new java.awt.Color(245, 253, 240));
        cmbSulit.setForeground(new java.awt.Color(0, 0, 0));
        cmbSulit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbSulit.setLightWeightPopupEnabled(false);
        cmbSulit.setName("cmbSulit"); // NOI18N
        FormInput.add(cmbSulit);
        cmbSulit.setBounds(145, 630, 60, 23);

        jLabel114.setForeground(new java.awt.Color(0, 0, 0));
        jLabel114.setText("Alat Bantu Nafas :");
        jLabel114.setName("jLabel114"); // NOI18N
        FormInput.add(jLabel114);
        jLabel114.setBounds(205, 630, 120, 23);

        cmbAlat.setBackground(new java.awt.Color(245, 253, 240));
        cmbAlat.setForeground(new java.awt.Color(0, 0, 0));
        cmbAlat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbAlat.setLightWeightPopupEnabled(false);
        cmbAlat.setName("cmbAlat"); // NOI18N
        FormInput.add(cmbAlat);
        cmbAlat.setBounds(330, 630, 60, 23);

        jLabel115.setForeground(new java.awt.Color(0, 0, 0));
        jLabel115.setText("Massa :");
        jLabel115.setName("jLabel115"); // NOI18N
        FormInput.add(jLabel115);
        jLabel115.setBounds(390, 630, 100, 23);

        cmbMassa.setBackground(new java.awt.Color(245, 253, 240));
        cmbMassa.setForeground(new java.awt.Color(0, 0, 0));
        cmbMassa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbMassa.setLightWeightPopupEnabled(false);
        cmbMassa.setName("cmbMassa"); // NOI18N
        FormInput.add(cmbMassa);
        cmbMassa.setBounds(496, 630, 60, 23);

        jLabel116.setForeground(new java.awt.Color(0, 0, 0));
        jLabel116.setText("Obesitas :");
        jLabel116.setName("jLabel116"); // NOI18N
        FormInput.add(jLabel116);
        jLabel116.setBounds(0, 658, 140, 23);

        cmbObes.setBackground(new java.awt.Color(245, 253, 240));
        cmbObes.setForeground(new java.awt.Color(0, 0, 0));
        cmbObes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbObes.setLightWeightPopupEnabled(false);
        cmbObes.setName("cmbObes"); // NOI18N
        FormInput.add(cmbObes);
        cmbObes.setBounds(145, 658, 60, 23);

        jLabel117.setForeground(new java.awt.Color(0, 0, 0));
        jLabel117.setText("Protusi Mandibula :");
        jLabel117.setName("jLabel117"); // NOI18N
        FormInput.add(jLabel117);
        jLabel117.setBounds(205, 658, 120, 23);

        cmbProtusi.setBackground(new java.awt.Color(245, 253, 240));
        cmbProtusi.setForeground(new java.awt.Color(0, 0, 0));
        cmbProtusi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbProtusi.setLightWeightPopupEnabled(false);
        cmbProtusi.setName("cmbProtusi"); // NOI18N
        FormInput.add(cmbProtusi);
        cmbProtusi.setBounds(330, 658, 60, 23);

        jLabel118.setForeground(new java.awt.Color(0, 0, 0));
        jLabel118.setText("Mallampathy :");
        jLabel118.setName("jLabel118"); // NOI18N
        FormInput.add(jLabel118);
        jLabel118.setBounds(390, 658, 100, 23);

        cmbMallam.setBackground(new java.awt.Color(245, 253, 240));
        cmbMallam.setForeground(new java.awt.Color(0, 0, 0));
        cmbMallam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "I", "II", "III", "IV" }));
        cmbMallam.setLightWeightPopupEnabled(false);
        cmbMallam.setName("cmbMallam"); // NOI18N
        FormInput.add(cmbMallam);
        cmbMallam.setBounds(496, 658, 45, 23);

        jLabel119.setForeground(new java.awt.Color(0, 0, 0));
        jLabel119.setText("Buka Mulut :");
        jLabel119.setName("jLabel119"); // NOI18N
        FormInput.add(jLabel119);
        jLabel119.setBounds(540, 658, 90, 23);

        cmbBuka.setBackground(new java.awt.Color(245, 253, 240));
        cmbBuka.setForeground(new java.awt.Color(0, 0, 0));
        cmbBuka.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbBuka.setLightWeightPopupEnabled(false);
        cmbBuka.setName("cmbBuka"); // NOI18N
        FormInput.add(cmbBuka);
        cmbBuka.setBounds(637, 658, 60, 23);

        jLabel120.setForeground(new java.awt.Color(0, 0, 0));
        jLabel120.setText("Jarak Thyro Mentohyoid :");
        jLabel120.setName("jLabel120"); // NOI18N
        FormInput.add(jLabel120);
        jLabel120.setBounds(0, 686, 140, 23);

        Tjarak.setForeground(new java.awt.Color(0, 0, 0));
        Tjarak.setName("Tjarak"); // NOI18N
        Tjarak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TjarakKeyPressed(evt);
            }
        });
        FormInput.add(Tjarak);
        Tjarak.setBounds(145, 686, 70, 23);

        jLabel121.setForeground(new java.awt.Color(0, 0, 0));
        jLabel121.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel121.setText("Cm.       Gigi :");
        jLabel121.setName("jLabel121"); // NOI18N
        FormInput.add(jLabel121);
        jLabel121.setBounds(220, 686, 70, 23);

        Tgigi.setForeground(new java.awt.Color(0, 0, 0));
        Tgigi.setName("Tgigi"); // NOI18N
        Tgigi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgigiKeyPressed(evt);
            }
        });
        FormInput.add(Tgigi);
        Tgigi.setBounds(290, 686, 470, 23);

        jLabel122.setForeground(new java.awt.Color(0, 0, 0));
        jLabel122.setText("KESIMPULAN ANESTESI :");
        jLabel122.setName("jLabel122"); // NOI18N
        FormInput.add(jLabel122);
        jLabel122.setBounds(0, 714, 140, 23);

        jLabel123.setForeground(new java.awt.Color(0, 0, 0));
        jLabel123.setText("PS ASA :");
        jLabel123.setName("jLabel123"); // NOI18N
        FormInput.add(jLabel123);
        jLabel123.setBounds(145, 714, 60, 23);

        TpsAsa.setForeground(new java.awt.Color(0, 0, 0));
        TpsAsa.setName("TpsAsa"); // NOI18N
        TpsAsa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpsAsaKeyPressed(evt);
            }
        });
        FormInput.add(TpsAsa);
        TpsAsa.setBounds(210, 714, 550, 23);

        jLabel124.setForeground(new java.awt.Color(0, 0, 0));
        jLabel124.setText("Penyulit :");
        jLabel124.setName("jLabel124"); // NOI18N
        FormInput.add(jLabel124);
        jLabel124.setBounds(145, 742, 60, 23);

        Tpenyulit.setForeground(new java.awt.Color(0, 0, 0));
        Tpenyulit.setName("Tpenyulit"); // NOI18N
        Tpenyulit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpenyulitKeyPressed(evt);
            }
        });
        FormInput.add(Tpenyulit);
        Tpenyulit.setBounds(210, 742, 550, 23);

        scrollPane17.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane17.setName("scrollPane17"); // NOI18N

        TrencanaAnes.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TrencanaAnes.setColumns(20);
        TrencanaAnes.setRows(5);
        TrencanaAnes.setName("TrencanaAnes"); // NOI18N
        TrencanaAnes.setPreferredSize(new java.awt.Dimension(162, 2000));
        TrencanaAnes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TrencanaAnesKeyPressed(evt);
            }
        });
        scrollPane17.setViewportView(TrencanaAnes);

        FormInput.add(scrollPane17);
        scrollPane17.setBounds(145, 770, 615, 60);

        jLabel125.setForeground(new java.awt.Color(0, 0, 0));
        jLabel125.setText("Rencana Anestesi :");
        jLabel125.setName("jLabel125"); // NOI18N
        FormInput.add(jLabel125);
        jLabel125.setBounds(0, 770, 140, 23);

        jLabel126.setForeground(new java.awt.Color(0, 0, 0));
        jLabel126.setText("<html><div style='text-align:right'>INSTRUKSI PRA<br>ANESTESI :</div></html>");
        jLabel126.setName("jLabel126"); // NOI18N
        FormInput.add(jLabel126);
        jLabel126.setBounds(0, 835, 140, 30);

        scrollPane18.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane18.setName("scrollPane18"); // NOI18N

        Tinstruksi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tinstruksi.setColumns(20);
        Tinstruksi.setRows(5);
        Tinstruksi.setName("Tinstruksi"); // NOI18N
        Tinstruksi.setPreferredSize(new java.awt.Dimension(162, 2000));
        scrollPane18.setViewportView(Tinstruksi);

        FormInput.add(scrollPane18);
        scrollPane18.setBounds(145, 835, 615, 60);

        BtnAlergiObat.setForeground(new java.awt.Color(0, 0, 0));
        BtnAlergiObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnAlergiObat.setText("Template");
        BtnAlergiObat.setToolTipText("Alt+2");
        BtnAlergiObat.setName("BtnAlergiObat"); // NOI18N
        BtnAlergiObat.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnAlergiObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAlergiObatActionPerformed(evt);
            }
        });
        FormInput.add(BtnAlergiObat);
        BtnAlergiObat.setBounds(770, 38, 100, 23);

        BtnDiagnosa.setForeground(new java.awt.Color(0, 0, 0));
        BtnDiagnosa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDiagnosa.setText("Template");
        BtnDiagnosa.setToolTipText("Alt+2");
        BtnDiagnosa.setName("BtnDiagnosa"); // NOI18N
        BtnDiagnosa.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDiagnosa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDiagnosaActionPerformed(evt);
            }
        });
        FormInput.add(BtnDiagnosa);
        BtnDiagnosa.setBounds(770, 210, 100, 23);

        BtnRencTindakan.setForeground(new java.awt.Color(0, 0, 0));
        BtnRencTindakan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnRencTindakan.setText("Template");
        BtnRencTindakan.setToolTipText("Alt+2");
        BtnRencTindakan.setName("BtnRencTindakan"); // NOI18N
        BtnRencTindakan.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnRencTindakan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRencTindakanActionPerformed(evt);
            }
        });
        FormInput.add(BtnRencTindakan);
        BtnRencTindakan.setBounds(770, 275, 100, 23);

        BtnRiwAnestesi.setForeground(new java.awt.Color(0, 0, 0));
        BtnRiwAnestesi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnRiwAnestesi.setText("Template");
        BtnRiwAnestesi.setToolTipText("Alt+2");
        BtnRiwAnestesi.setName("BtnRiwAnestesi"); // NOI18N
        BtnRiwAnestesi.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnRiwAnestesi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRiwAnestesiActionPerformed(evt);
            }
        });
        FormInput.add(BtnRiwAnestesi);
        BtnRiwAnestesi.setBounds(770, 508, 100, 23);

        BtnRencAnestesi.setForeground(new java.awt.Color(0, 0, 0));
        BtnRencAnestesi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnRencAnestesi.setText("Template");
        BtnRencAnestesi.setToolTipText("Alt+2");
        BtnRencAnestesi.setName("BtnRencAnestesi"); // NOI18N
        BtnRencAnestesi.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnRencAnestesi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRencAnestesiActionPerformed(evt);
            }
        });
        FormInput.add(BtnRencAnestesi);
        BtnRencAnestesi.setBounds(770, 770, 100, 23);

        BtnInstruksi.setForeground(new java.awt.Color(0, 0, 0));
        BtnInstruksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnInstruksi.setText("Template");
        BtnInstruksi.setToolTipText("Alt+2");
        BtnInstruksi.setName("BtnInstruksi"); // NOI18N
        BtnInstruksi.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnInstruksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnInstruksiActionPerformed(evt);
            }
        });
        FormInput.add(BtnInstruksi);
        BtnInstruksi.setBounds(770, 835, 100, 23);

        Scroll1.setViewportView(FormInput);

        panelGlass9.add(Scroll1, java.awt.BorderLayout.CENTER);

        PanelInput1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Data Evaluasi Pra Anestesi Operasi ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        PanelInput1.setName("PanelInput1"); // NOI18N
        PanelInput1.setOpaque(false);
        PanelInput1.setPreferredSize(new java.awt.Dimension(900, 700));
        PanelInput1.setLayout(new java.awt.BorderLayout());

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(600, 402));

        tbEvaluasi.setToolTipText("Silahkan klik untuk memilih data yang diperbaiki/dihapus");
        tbEvaluasi.setName("tbEvaluasi"); // NOI18N
        tbEvaluasi.getTableHeader().setReorderingAllowed(false);
        tbEvaluasi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbEvaluasiMouseClicked(evt);
            }
        });
        tbEvaluasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbEvaluasiKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbEvaluasi);

        PanelInput1.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass11.setName("panelGlass11"); // NOI18N
        panelGlass11.setPreferredSize(new java.awt.Dimension(44, 86));
        panelGlass11.setLayout(new java.awt.BorderLayout());

        panelGlass12.setName("panelGlass12"); // NOI18N
        panelGlass12.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 6));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tgl. Operasi :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass12.add(jLabel19);

        DTPCari1.setEditable(false);
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "22-06-2026" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass12.add(DTPCari1);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass12.add(jLabel21);

        DTPCari2.setEditable(false);
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "22-06-2026" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass12.add(DTPCari2);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass12.add(jLabel7);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass12.add(LCount);

        panelGlass11.add(panelGlass12, java.awt.BorderLayout.CENTER);

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 42));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(200, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass10.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setText("Tampilkan Data");
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
        panelGlass10.add(BtnCari);

        BtnAll.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setText("Semua");
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(100, 23));
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
        panelGlass10.add(BtnAll);

        panelGlass11.add(panelGlass10, java.awt.BorderLayout.PAGE_END);

        PanelInput1.add(panelGlass11, java.awt.BorderLayout.PAGE_END);

        panelGlass9.add(PanelInput1, java.awt.BorderLayout.EAST);

        internalFrame1.add(panelGlass9, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().equals("")) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else {
            try {
                if (Sequel.menyimpantf("evaluasi_pra_anestesi_operasi", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No. Rawat", 37, new String[]{
                    TNoRw.getText(), TrgRawat.getText(), TalergiObat.getText(), cmbAnamnesa.getSelectedItem().toString(), Valid.SetTgl(TtglRuang.getSelectedItem() + ""),
                    cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem(), Tdiagnosa.getText(), Valid.mysql_real_escape_stringERM(TrencanaTindakan.getText()),
                    Ttd.getText(), Tbb.getText(), Tnadi.getText(), Trr.getText(), Ttb.getText(), Tsuhu.getText(), cmbObat.getSelectedItem().toString(),
                    TketObat.getText(), Valid.SetTgl(TtglOperasi.getSelectedItem() + ""), nipSpesBedah, nipSpesAnes, Valid.mysql_real_escape_stringERM(TriwAnestesi.getText()),
                    cmbBebas.getSelectedItem().toString(), cmbLeher.getSelectedItem().toString(), cmbGerak.getSelectedItem().toString(), cmbSulit.getSelectedItem().toString(),
                    cmbAlat.getSelectedItem().toString(), cmbMassa.getSelectedItem().toString(), cmbObes.getSelectedItem().toString(), cmbProtusi.getSelectedItem().toString(),
                    cmbMallam.getSelectedItem().toString(), cmbBuka.getSelectedItem().toString(), Tjarak.getText(), Tgigi.getText(), TpsAsa.getText(), Tpenyulit.getText(),
                    Valid.mysql_real_escape_stringERM(TrencanaAnes.getText()), Valid.mysql_real_escape_stringERM(Tinstruksi.getText()), Sequel.cariIsi("select now()")
                }) == true) {

                    Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Evaluasi Pra Anestesi", "Simpan");
                    TCari.setText(TNoRw.getText());
                    emptTeks();
                    tampil();
                }
            } catch (Exception e) {
                System.out.println("Simpan Evaluasi Pra Anestesi : " + e);
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
        tampil();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnGanti);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnGantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiActionPerformed
        if (TNoRw.getText().equals("")) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else {
            if (tbEvaluasi.getSelectedRow() > -1) {
                try {
                    if (Sequel.mengedittf("evaluasi_pra_anestesi_operasi", "waktu_simpan=?", "alergi_obat=?, anamnesa_dari=?, tgl_ruang=?, jam_ruang=?, diagnosa=?, rencana_tindakan=?, "
                            + "td=?, bb=?, nadi=?, rr=?, tb=?, suhu=?, obat_dikonsumsi=?, ket_obat_dikonsumsi=?, tgl_operasi=?, nip_spesialis_bedah=?, nip_spesialis_anestesi=?, "
                            + "riwayat_anestesi=?, bebas=?, leher_pendek=?, gerak_leher=?, sulit_ventilasi=?, alat_bantu=?, massa=?, obesitas=?, protusi=?, mallampathy=?, buka_mulut=?, "
                            + "jarak_thyro=?, gigi=?, ps_asa=?, penyulit=?, rencana_anestesi=?, instruksi_anestesi=?", 35, new String[]{
                                TalergiObat.getText(), cmbAnamnesa.getSelectedItem().toString(), Valid.SetTgl(TtglRuang.getSelectedItem() + ""),
                                cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem(), Tdiagnosa.getText(), Valid.mysql_real_escape_stringERM(TrencanaTindakan.getText()),
                                Ttd.getText(), Tbb.getText(), Tnadi.getText(), Trr.getText(), Ttb.getText(), Tsuhu.getText(), cmbObat.getSelectedItem().toString(),
                                TketObat.getText(), Valid.SetTgl(TtglOperasi.getSelectedItem() + ""), nipSpesBedah, nipSpesAnes, Valid.mysql_real_escape_stringERM(TriwAnestesi.getText()),
                                cmbBebas.getSelectedItem().toString(), cmbLeher.getSelectedItem().toString(), cmbGerak.getSelectedItem().toString(), cmbSulit.getSelectedItem().toString(),
                                cmbAlat.getSelectedItem().toString(), cmbMassa.getSelectedItem().toString(), cmbObes.getSelectedItem().toString(), cmbProtusi.getSelectedItem().toString(),
                                cmbMallam.getSelectedItem().toString(), cmbBuka.getSelectedItem().toString(), Tjarak.getText(), Tgigi.getText(), TpsAsa.getText(), Tpenyulit.getText(),
                                Valid.mysql_real_escape_stringERM(TrencanaAnes.getText()), Valid.mysql_real_escape_stringERM(Tinstruksi.getText()),
                                tbEvaluasi.getValueAt(tbEvaluasi.getSelectedRow(), 46).toString()
                            }) == true) {

                        Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Evaluasi Pra Anestesi", "Ganti");
                        TCari.setText(TNoRw.getText());
                        tampil();
                        emptTeks();
                    }
                } catch (Exception e) {
                    System.out.println("Ganti Evaluasi Pra Anestesi : " + e);
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan klik/pilih dulu salah satu datanya pada tabel..!!");
                tbEvaluasi.requestFocus();
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
        WindowTemplate.dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnBatal, TCari);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

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

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        BtnCariActionPerformed(null);
        emptTeks();        
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
            TCari.setText("");
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbEvaluasiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbEvaluasiMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbEvaluasiMouseClicked

    private void tbEvaluasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbEvaluasiKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbEvaluasiKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbEvaluasi.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.queryu2tf("delete from evaluasi_pra_anestesi_operasi where waktu_simpan=?", 1, new String[]{
                    tbEvaluasi.getValueAt(tbEvaluasi.getSelectedRow(), 46).toString()
                }) == true) {                    
                    tampil();
                    emptTeks();
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                }
            } else {
                tampil();
                emptTeks();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan klik/pilih dulu salah satu datanya pada tabel..!!");
            tbEvaluasi.requestFocus();
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tbEvaluasi.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("norm", TNoRM.getText());
            param.put("nmpasien", TPasien.getText());
            param.put("tgllahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d-%m-%Y') from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
            
            if (TalergiObat.getText().equals("")) {
                param.put("alergiObat", "...........................");
            } else {
                param.put("alergiObat", TalergiObat.getText());
            }
            
            param.put("anamnesa", cmbAnamnesa.getSelectedItem().toString());
            param.put("ruangRwt", TrgRawat.getText());
            param.put("tglRuang", Valid.SetTglINDONESIA(Valid.SetTgl(TtglRuang.getSelectedItem() + "")));
            param.put("jamRuang", cmbJam1.getSelectedItem().toString() + ":" + cmbMnt1.getSelectedItem().toString() + " WITA");
            
            if (Tdiagnosa.getText().equals("")) {
                param.put("diagnosa", "...........................");
            } else {
                param.put("diagnosa", Tdiagnosa.getText());
            }
            
            if (Ttd.getText().equals("")) {
                param.put("td", "......... mmHg");
            } else {
                param.put("td", Ttd.getText() + " mmHg");
            }
            
            if (Tbb.getText().equals("")) {
                param.put("bb", "......... Kg.");
            } else {
                param.put("bb", Tbb.getText() + " Kg.");
            }
            
            if (Tnadi.getText().equals("")) {
                param.put("nadi", "......... x/menit");
            } else {
                param.put("nadi", Tnadi.getText() + " x/menit");
            }
            
            if (Trr.getText().equals("")) {
                param.put("rr", "......... x/menit");
            } else {
                param.put("rr", Trr.getText() + " x/menit");
            }
            
            if (Ttb.getText().equals("")) {
                param.put("tb", "......... Cm.");
            } else {
                param.put("tb", Ttb.getText() + " Cm.");
            }
            
            if (Tsuhu.getText().equals("")) {
                param.put("suhu", "......... °C");
            } else {
                param.put("suhu", Tsuhu.getText() + " °C");
            }
            
            if (TrencanaTindakan.getText().equals("")) {
                param.put("rencanaTindakn", "...........................");
            } else {
                param.put("rencanaTindakn", TrencanaTindakan.getText());
            }
            
            if (cmbObat.getSelectedIndex() == 2) {
                if (TketObat.getText().equals("")) {
                    param.put("obat", cmbObat.getSelectedItem().toString());
                } else {
                    param.put("obat", cmbObat.getSelectedItem().toString() + " : " + TketObat.getText());
                }
            } else {
                param.put("obat", cmbObat.getSelectedItem().toString());
            }            
            
            param.put("tglOperasi", Valid.SetTglINDONESIA(Valid.SetTgl(TtglOperasi.getSelectedItem() + "")));            
            param.put("SpesBedah", TnmSpesBedah.getText());
            param.put("SpesAnes", TnmSpesAnes.getText());
            
            if (TriwAnestesi.getText().equals("")) {
                param.put("riwayatAnestesi", "...........................");
            } else {
                param.put("riwayatAnestesi", TriwAnestesi.getText());
            }
            
            param.put("bebas", cmbBebas.getSelectedItem().toString());
            param.put("leher", cmbLeher.getSelectedItem().toString());
            param.put("gerak", cmbGerak.getSelectedItem().toString());
            param.put("sulit", cmbSulit.getSelectedItem().toString());
            param.put("alat", cmbAlat.getSelectedItem().toString());
            param.put("masa", cmbMassa.getSelectedItem().toString());
            param.put("obes", cmbObes.getSelectedItem().toString());
            param.put("protusi", cmbProtusi.getSelectedItem().toString());
            param.put("mallam", cmbMallam.getSelectedItem().toString());
            param.put("buka", cmbBuka.getSelectedItem().toString());
            
            if (Tjarak.getText().equals("")) {
                param.put("jarak", "......... Cm.");
            } else {
                param.put("jarak", Tjarak.getText() + " Cm.");
            }
            
            if (Tgigi.getText().equals("")) {
                param.put("gigi", "...........................");
            } else {
                param.put("gigi", Tgigi.getText());
            }
            
            if (TpsAsa.getText().equals("")) {
                param.put("psAsa", "...........................");
            } else {
                param.put("psAsa", TpsAsa.getText());
            }
            
            if (Tpenyulit.getText().equals("")) {
                param.put("penyulit", "...........................");
            } else {
                param.put("penyulit", Tpenyulit.getText());
            }
            
            if (TrencanaAnes.getText().equals("")) {
                param.put("rencanaAnes", "...........................");
            } else {
                param.put("rencanaAnes", TrencanaAnes.getText());
            }
            
            if (Tinstruksi.getText().equals("")) {
                param.put("instruksi", "...........................");
            } else {
                param.put("instruksi", Tinstruksi.getText());
            }
            
            if (cmbPilihCetak.getSelectedIndex() == 0) {
                String isi = "";
                if (nipSpesAnes.equals("") || nipSpesAnes.equals("-") || nipSpesAnes.equals("--")) {
                    JOptionPane.showMessageDialog(rootPane, "Maaf, nama dokter anestesi harus diisi dulu,..");
                } else {
                    isi = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                            + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='001'"),
                                    "Evaluasi Pra Anestesi", TnmSpesAnes.getText(),
                                    Sequel.cariIsi("select date_format(waktu_simpan,'%d/%m/%Y') from evaluasi_pra_anestesi_operasi where "
                                            + "waktu_simpan='" + tbEvaluasi.getValueAt(tbEvaluasi.getSelectedRow(), 46).toString() + "'"),
                                    Sequel.cariIsi("select time(waktu_simpan) from evaluasi_pra_anestesi_operasi where "
                                            + "waktu_simpan='" + tbEvaluasi.getValueAt(tbEvaluasi.getSelectedRow(), 46).toString() + "'")) + "') from kalimat_tte where kode='001'");

                    Valid.cetakQrTte(isi, Sequel.cariFolderTte(), "QRTte.jpg", "select logo from setting");
                    Sequel.queryu("delete from setting_qr where judul = 'QRTte'");
                    Sequel.menyimpanQr("setting_qr", "'QRTte'", "file QRCode TTE Evaluasi Pra Anestesi", Sequel.cariFolderPrintTte());
                    param.put("lokasiQr", Sequel.cariGambar("select gambar from setting_qr where judul = 'QRTte'"));
                    param.put("kalimatTte", Sequel.cariIsi("select replace(kalimat_footer,'##jns_dokumen##',jenis_dokumen) from kalimat_tte where kode='001'"));

                    Valid.MyReport("rptEvaluasiPraAnestesiQr.jasper", "report", "::[ Evaluasi Pra Anestesi ]::",
                            "SELECT now() tanggal", param);
                    
                    emptTeks();
                    tampil();
                    Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);
                }
            } else {
                Valid.MyReport("rptEvaluasiPraAnestesi.jasper", "report", "::[ Evaluasi Pra Anestesi ]::",
                        "SELECT now() tanggal", param);
                
                tampil();
                emptTeks();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan klik/pilih salah satu datanya terlebih dulu pada tabel..!!!!");
            tbEvaluasi.requestFocus();
        }
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnGanti, BtnKeluar);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void TalergiObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TalergiObatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            cmbAnamnesa.requestFocus();
        }
    }//GEN-LAST:event_TalergiObatKeyPressed

    private void BtnSpesBedahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSpesBedahActionPerformed
        pilihDokter = 0;
        pilihDokter = 1;
        akses.setform("RMEvaluasiPraAnestesi");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnSpesBedahActionPerformed

    private void BtnSpesAnesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSpesAnesActionPerformed
        pilihDokter = 0;
        pilihDokter = 2;
        akses.setform("RMEvaluasiPraAnestesi");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnSpesAnesActionPerformed

    private void cmbJam1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam1MouseReleased
        AutoCompleteDecorator.decorate(cmbJam1);
    }//GEN-LAST:event_cmbJam1MouseReleased

    private void cmbMnt1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt1MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt1);
    }//GEN-LAST:event_cmbMnt1MouseReleased

    private void cmbDtk1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk1MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk1);
    }//GEN-LAST:event_cmbDtk1MouseReleased

    private void TdiagnosaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdiagnosaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TrencanaTindakan.requestFocus();
        }
    }//GEN-LAST:event_TdiagnosaKeyPressed

    private void TrencanaTindakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TrencanaTindakanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Ttd.requestFocus();
        }
    }//GEN-LAST:event_TrencanaTindakanKeyPressed

    private void TriwAnestesiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TriwAnestesiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            cmbBebas.requestFocus();
        }
    }//GEN-LAST:event_TriwAnestesiKeyPressed

    private void TrencanaAnesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TrencanaAnesKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tinstruksi.requestFocus();
        }
    }//GEN-LAST:event_TrencanaAnesKeyPressed

    private void TtdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tbb.requestFocus();
        }
    }//GEN-LAST:event_TtdKeyPressed

    private void TbbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbbKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tnadi.requestFocus();
        }
    }//GEN-LAST:event_TbbKeyPressed

    private void TnadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnadiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Trr.requestFocus();
        }
    }//GEN-LAST:event_TnadiKeyPressed

    private void TrrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TrrKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Ttb.requestFocus();
        }
    }//GEN-LAST:event_TrrKeyPressed

    private void TtbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtbKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tsuhu.requestFocus();
        }
    }//GEN-LAST:event_TtbKeyPressed

    private void TsuhuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TsuhuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbObat.requestFocus();
        }
    }//GEN-LAST:event_TsuhuKeyPressed

    private void TketObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketObatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TtglOperasi.requestFocus();
        }
    }//GEN-LAST:event_TketObatKeyPressed

    private void TjarakKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TjarakKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tgigi.requestFocus();
        }
    }//GEN-LAST:event_TjarakKeyPressed

    private void TgigiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgigiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TpsAsa.requestFocus();
        }
    }//GEN-LAST:event_TgigiKeyPressed

    private void TpsAsaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpsAsaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tpenyulit.requestFocus();
        }
    }//GEN-LAST:event_TpsAsaKeyPressed

    private void TpenyulitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpenyulitKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TrencanaAnes.requestFocus();
        }
    }//GEN-LAST:event_TpenyulitKeyPressed

    private void cmbObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbObatActionPerformed
        TketObat.setText("");
        if (cmbObat.getSelectedIndex() == 2) {
            TketObat.setEnabled(true);
            TketObat.requestFocus();
        } else {
            TketObat.setEnabled(false);
        }
    }//GEN-LAST:event_cmbObatActionPerformed

    private void MnHasilPemeriksaanPenunjangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHasilPemeriksaanPenunjangActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            akses.setform("RMEvaluasiPraAnestesi");
            DlgHasilPenunjangMedis form = new DlgHasilPenunjangMedis(null, false);
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setData(TNoRw.getText(), TPasien.getText(), TNoRM.getText());
            form.setVisible(true);
        }
    }//GEN-LAST:event_MnHasilPemeriksaanPenunjangActionPerformed

    private void MnDokumenJangMedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDokumenJangMedActionPerformed
        if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih salah satu datanya terlebih dahulu..!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("RMEvaluasiPraAnestesi");
            RMDokumenPenunjangMedis form = new RMDokumenPenunjangMedis(null, false);
            form.setData(TNoRw.getText(), TNoRM.getText(), TPasien.getText());
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnDokumenJangMedActionPerformed

    private void tbTemplateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTemplateMouseClicked
        if(tabMode1.getRowCount() != 0) {
            try {
                if (tbTemplate.getSelectedRow() != -1) {
                    Ttemplate.setText(tbTemplate.getValueAt(tbTemplate.getSelectedRow(), 2).toString());
                }
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbTemplateMouseClicked

    private void TCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari1ActionPerformed(null);
        }
    }//GEN-LAST:event_TCari1KeyPressed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        tampilTemplate();
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void BtnCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCari1ActionPerformed(null);
        }
    }//GEN-LAST:event_BtnCari1KeyPressed

    private void BtnCopasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCopasActionPerformed
        x = JOptionPane.showConfirmDialog(rootPane, "Apakah data ini akan dipakai..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            copas();
            WindowTemplate.dispose();
        }
    }//GEN-LAST:event_BtnCopasActionPerformed

    private void BtnCloseIn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn1ActionPerformed
        WindowTemplate.dispose();
    }//GEN-LAST:event_BtnCloseIn1ActionPerformed

    private void BtnAlergiObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAlergiObatActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 1;
        tampilTemplate();
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3),
                "::[ Data Template Alergi Obat ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, internalFrame1.getHeight() - 40);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnAlergiObatActionPerformed

    private void BtnDiagnosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDiagnosaActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 2;
        tampilTemplate();
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3),
                "::[ Data Template Diagnosa ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, internalFrame1.getHeight() - 40);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnDiagnosaActionPerformed

    private void BtnRencTindakanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRencTindakanActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 3;
        tampilTemplate();
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3),
                "::[ Data Template Rencana Tindakan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, internalFrame1.getHeight() - 40);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnRencTindakanActionPerformed

    private void BtnRiwAnestesiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRiwAnestesiActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 4;
        tampilTemplate();
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3),
                "::[ Data Template Riwayat Anestesi & Komplikasi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, internalFrame1.getHeight() - 40);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnRiwAnestesiActionPerformed

    private void BtnRencAnestesiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRencAnestesiActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 5;
        tampilTemplate();
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3),
                "::[ Data Template Rencana Anestesi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, internalFrame1.getHeight() - 40);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnRencAnestesiActionPerformed

    private void BtnInstruksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnInstruksiActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 6;
        tampilTemplate();
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3),
                "::[ Data Template Instruksi Pra Anestesi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, internalFrame1.getHeight() - 40);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnInstruksiActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMEvaluasiPraAnestesi dialog = new RMEvaluasiPraAnestesi(new javax.swing.JFrame(), true);
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
    private widget.Button BtnAlergiObat;
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnCari1;
    private widget.Button BtnCloseIn1;
    private widget.Button BtnCopas;
    private widget.Button BtnDiagnosa;
    private widget.Button BtnGanti;
    private widget.Button BtnHapus;
    private widget.Button BtnInstruksi;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnRencAnestesi;
    private widget.Button BtnRencTindakan;
    private widget.Button BtnRiwAnestesi;
    private widget.Button BtnSimpan;
    private widget.Button BtnSpesAnes;
    private widget.Button BtnSpesBedah;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnDokumenJangMed;
    private javax.swing.JMenuItem MnHasilPemeriksaanPenunjang;
    private javax.swing.JPanel PanelInput1;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll3;
    public widget.TextBox TCari;
    private widget.TextBox TCari1;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextArea TalergiObat;
    private widget.TextBox Tbb;
    private widget.TextArea Tdiagnosa;
    private widget.TextBox Tgigi;
    private widget.TextArea Tinstruksi;
    private widget.TextBox Tjarak;
    private widget.TextBox TketObat;
    private widget.TextBox Tnadi;
    private widget.TextBox TnmSpesAnes;
    private widget.TextBox TnmSpesBedah;
    private widget.TextBox Tpenyulit;
    private widget.TextBox TpsAsa;
    private widget.TextArea TrencanaAnes;
    private widget.TextArea TrencanaTindakan;
    private widget.TextBox TrgRawat;
    private widget.TextArea TriwAnestesi;
    private widget.TextBox Trr;
    private widget.TextBox Tsuhu;
    private widget.TextBox Ttb;
    private widget.TextBox Ttd;
    private widget.TextArea Ttemplate;
    private widget.Tanggal TtglOperasi;
    private widget.Tanggal TtglRuang;
    private javax.swing.JDialog WindowTemplate;
    private widget.ComboBox cmbAlat;
    private widget.ComboBox cmbAnamnesa;
    private widget.ComboBox cmbBebas;
    private widget.ComboBox cmbBuka;
    private widget.ComboBox cmbDtk1;
    private widget.ComboBox cmbGerak;
    private widget.ComboBox cmbJam1;
    private widget.ComboBox cmbLeher;
    private widget.ComboBox cmbMallam;
    private widget.ComboBox cmbMassa;
    private widget.ComboBox cmbMnt1;
    private widget.ComboBox cmbObat;
    private widget.ComboBox cmbObes;
    private widget.ComboBox cmbPilihCetak;
    private widget.ComboBox cmbProtusi;
    private widget.ComboBox cmbSulit;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame5;
    private widget.Label jLabel10;
    private widget.Label jLabel100;
    private widget.Label jLabel101;
    private widget.Label jLabel102;
    private widget.Label jLabel103;
    private widget.Label jLabel104;
    private widget.Label jLabel105;
    private widget.Label jLabel106;
    private widget.Label jLabel107;
    private widget.Label jLabel108;
    private widget.Label jLabel109;
    private widget.Label jLabel110;
    private widget.Label jLabel111;
    private widget.Label jLabel112;
    private widget.Label jLabel113;
    private widget.Label jLabel114;
    private widget.Label jLabel115;
    private widget.Label jLabel116;
    private widget.Label jLabel117;
    private widget.Label jLabel118;
    private widget.Label jLabel119;
    private widget.Label jLabel120;
    private widget.Label jLabel121;
    private widget.Label jLabel122;
    private widget.Label jLabel123;
    private widget.Label jLabel124;
    private widget.Label jLabel125;
    private widget.Label jLabel126;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel276;
    private widget.Label jLabel36;
    private widget.Label jLabel6;
    private widget.Label jLabel63;
    private widget.Label jLabel64;
    private widget.Label jLabel65;
    private widget.Label jLabel7;
    private widget.Label jLabel70;
    private widget.Label jLabel76;
    private widget.Label jLabel77;
    private widget.Label jLabel87;
    private widget.Label jLabel94;
    private widget.Label jLabel95;
    private widget.Label jLabel96;
    private widget.Label jLabel97;
    private widget.Label jLabel98;
    private widget.Label jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass12;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.panelisi panelisi4;
    private widget.ScrollPane scrollPane11;
    private widget.ScrollPane scrollPane14;
    private widget.ScrollPane scrollPane15;
    private widget.ScrollPane scrollPane16;
    private widget.ScrollPane scrollPane17;
    private widget.ScrollPane scrollPane18;
    private widget.Table tbEvaluasi;
    private widget.Table tbTemplate;
    // End of variables declaration//GEN-END:variables

    public void tampil() {     
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("select ap.*, p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllahir, pg1.nama drBedah, pg2.nama drAnes, "
                    + "date_format(ap.tgl_ruang,'%d-%m-%Y') tglRuang, time_format(ap.jam_ruang,'%H:%i Wita') jamRuang from evaluasi_pra_anestesi_operasi ap "
                    + "inner join reg_periksa rp on rp.no_rawat=ap.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "inner join pegawai pg1 on pg1.nik=ap.nip_spesialis_bedah inner join pegawai pg2 on pg2.nik=ap.nip_spesialis_anestesi where "
                    + "ap.tgl_operasi between ? and ? and ap.no_rawat LIKE ? or "
                    + "ap.tgl_operasi between ? and ? and p.no_rkm_medis LIKE ? or "
                    + "ap.tgl_operasi between ? and ? and p.nm_pasien LIKE ? or "
                    + "ap.tgl_operasi between ? and ? and pg1.nama LIKE ? or "
                    + "ap.tgl_operasi between ? and ? and pg2.nama LIKE ? or "
                    + "ap.tgl_operasi between ? and ? and ap.diagnosa LIKE ? or "
                    + "ap.tgl_operasi between ? and ? and ap.anamnesa_dari LIKE ? or "
                    + "ap.tgl_operasi between ? and ? and ap.ruang_rawat LIKE ? ORDER BY ap.tgl_operasi desc");
            try {
                ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(3, "%" + TCari.getText() + "%");                
                ps.setString(4, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(5, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(6, "%" + TCari.getText() + "%");
                ps.setString(7, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(8, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(9, "%" + TCari.getText() + "%");
                ps.setString(10, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(11, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(12, "%" + TCari.getText() + "%");
                ps.setString(13, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(14, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(15, "%" + TCari.getText() + "%");
                ps.setString(16, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(17, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(18, "%" + TCari.getText() + "%");
                ps.setString(19, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(20, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(21, "%" + TCari.getText() + "%");
                ps.setString(22, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(23, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(24, "%" + TCari.getText() + "%");                
                rs = ps.executeQuery();                
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("tgllahir"),
                        rs.getString("ruang_rawat"),
                        rs.getString("tglRuang"),
                        rs.getString("jamRuang"),
                        rs.getString("anamnesa_dari"),
                        rs.getString("diagnosa"),
                        rs.getString("drBedah"),
                        rs.getString("drAnes"),
                        rs.getString("ruang_rawat"),
                        rs.getString("alergi_obat"),
                        rs.getString("anamnesa_dari"),
                        rs.getString("tgl_ruang"),
                        rs.getString("jam_ruang"),
                        rs.getString("diagnosa"),
                        rs.getString("rencana_tindakan"),
                        rs.getString("td"),
                        rs.getString("bb"),
                        rs.getString("nadi"),
                        rs.getString("rr"),
                        rs.getString("tb"),
                        rs.getString("suhu"),
                        rs.getString("obat_dikonsumsi"),
                        rs.getString("ket_obat_dikonsumsi"),
                        rs.getString("tgl_operasi"),
                        rs.getString("nip_spesialis_bedah"),
                        rs.getString("nip_spesialis_anestesi"),
                        rs.getString("riwayat_anestesi"),
                        rs.getString("bebas"),
                        rs.getString("leher_pendek"),
                        rs.getString("gerak_leher"),
                        rs.getString("sulit_ventilasi"),
                        rs.getString("alat_bantu"),
                        rs.getString("massa"),
                        rs.getString("obesitas"),
                        rs.getString("protusi"),
                        rs.getString("mallampathy"),
                        rs.getString("buka_mulut"),
                        rs.getString("jarak_thyro"),
                        rs.getString("gigi"),
                        rs.getString("ps_asa"),
                        rs.getString("penyulit"),
                        rs.getString("rencana_anestesi"),
                        rs.getString("instruksi_anestesi"),
                        rs.getString("waktu_simpan")
                    });
                }                
            } catch (Exception e) {
                System.out.println("tampil() : " + e);
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
        LCount.setText("" + tabMode.getRowCount());
    }
    
    public void emptTeks() {
        TalergiObat.setText("");
        cmbAnamnesa.setSelectedIndex(0);
        TtglRuang.setDate(new Date());
        cmbJam1.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt1.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk1.setSelectedIndex(0);
        Tdiagnosa.setText("");        
        TrencanaTindakan.setText("");
        Ttd.setText("");
        Tbb.setText("");
        Tnadi.setText("");
        Trr.setText("");
        Ttb.setText("");
        Tsuhu.setText("");
        cmbObat.setSelectedIndex(0);
        TketObat.setEnabled(false);
        TketObat.setText("");
        TtglOperasi.setDate(new Date());
        nipSpesBedah = "-";
        TnmSpesBedah.setText("-");
        nipSpesAnes = "-";
        TnmSpesAnes.setText("-");
        TriwAnestesi.setText("");
        cmbBebas.setSelectedIndex(0);
        cmbSulit.setSelectedIndex(0);
        cmbObes.setSelectedIndex(0);
        cmbLeher.setSelectedIndex(0);
        cmbAlat.setSelectedIndex(0);
        cmbProtusi.setSelectedIndex(0);
        cmbGerak.setSelectedIndex(0);
        cmbMassa.setSelectedIndex(0);
        cmbMallam.setSelectedIndex(0);
        cmbBuka.setSelectedIndex(0);
        Tjarak.setText("");
        Tgigi.setText("");
        TpsAsa.setText("");
        Tpenyulit.setText("");
        TrencanaAnes.setText("");
        Tinstruksi.setText("");
    }

    private void getData() {
        nipSpesBedah = "";
        nipSpesAnes = "";
        
        if (tbEvaluasi.getSelectedRow() != -1) {
            TNoRw.setText(tbEvaluasi.getValueAt(tbEvaluasi.getSelectedRow(), 0).toString());
            TNoRM.setText(tbEvaluasi.getValueAt(tbEvaluasi.getSelectedRow(), 1).toString());
            TPasien.setText(tbEvaluasi.getValueAt(tbEvaluasi.getSelectedRow(), 2).toString());
            TalergiObat.setText(tbEvaluasi.getValueAt(tbEvaluasi.getSelectedRow(), 12).toString());
            cmbAnamnesa.setSelectedItem(tbEvaluasi.getValueAt(tbEvaluasi.getSelectedRow(), 13).toString());
            TrgRawat.setText(tbEvaluasi.getValueAt(tbEvaluasi.getSelectedRow(), 4).toString());
            Valid.SetTgl(TtglRuang, tbEvaluasi.getValueAt(tbEvaluasi.getSelectedRow(), 14).toString());
            cmbJam1.setSelectedItem(tbEvaluasi.getValueAt(tbEvaluasi.getSelectedRow(), 15).toString().substring(0, 2));
            cmbMnt1.setSelectedItem(tbEvaluasi.getValueAt(tbEvaluasi.getSelectedRow(), 15).toString().substring(3, 5));
            cmbDtk1.setSelectedItem(tbEvaluasi.getValueAt(tbEvaluasi.getSelectedRow(), 15).toString().substring(6, 8));
            Tdiagnosa.setText(tbEvaluasi.getValueAt(tbEvaluasi.getSelectedRow(), 16).toString());
            TrencanaTindakan.setText(tbEvaluasi.getValueAt(tbEvaluasi.getSelectedRow(), 17).toString());
            Ttd.setText(tbEvaluasi.getValueAt(tbEvaluasi.getSelectedRow(), 18).toString());
            Tbb.setText(tbEvaluasi.getValueAt(tbEvaluasi.getSelectedRow(), 19).toString());
            Tnadi.setText(tbEvaluasi.getValueAt(tbEvaluasi.getSelectedRow(), 20).toString());
            Trr.setText(tbEvaluasi.getValueAt(tbEvaluasi.getSelectedRow(), 21).toString());
            Ttb.setText(tbEvaluasi.getValueAt(tbEvaluasi.getSelectedRow(), 22).toString());
            Tsuhu.setText(tbEvaluasi.getValueAt(tbEvaluasi.getSelectedRow(), 23).toString());
            cmbObat.setSelectedItem(tbEvaluasi.getValueAt(tbEvaluasi.getSelectedRow(), 24).toString());
            TketObat.setText(tbEvaluasi.getValueAt(tbEvaluasi.getSelectedRow(), 25).toString());
            Valid.SetTgl(TtglOperasi, tbEvaluasi.getValueAt(tbEvaluasi.getSelectedRow(), 26).toString());
            nipSpesBedah = tbEvaluasi.getValueAt(tbEvaluasi.getSelectedRow(), 27).toString();
            TnmSpesBedah.setText(tbEvaluasi.getValueAt(tbEvaluasi.getSelectedRow(), 9).toString());
            nipSpesAnes = tbEvaluasi.getValueAt(tbEvaluasi.getSelectedRow(), 28).toString();
            TnmSpesAnes.setText(tbEvaluasi.getValueAt(tbEvaluasi.getSelectedRow(), 10).toString());
            TriwAnestesi.setText(tbEvaluasi.getValueAt(tbEvaluasi.getSelectedRow(), 29).toString());
            cmbBebas.setSelectedItem(tbEvaluasi.getValueAt(tbEvaluasi.getSelectedRow(), 30).toString());
            cmbLeher.setSelectedItem(tbEvaluasi.getValueAt(tbEvaluasi.getSelectedRow(), 31).toString());
            cmbGerak.setSelectedItem(tbEvaluasi.getValueAt(tbEvaluasi.getSelectedRow(), 32).toString());
            cmbSulit.setSelectedItem(tbEvaluasi.getValueAt(tbEvaluasi.getSelectedRow(), 33).toString());
            cmbAlat.setSelectedItem(tbEvaluasi.getValueAt(tbEvaluasi.getSelectedRow(), 34).toString());
            cmbMassa.setSelectedItem(tbEvaluasi.getValueAt(tbEvaluasi.getSelectedRow(), 35).toString());
            cmbObes.setSelectedItem(tbEvaluasi.getValueAt(tbEvaluasi.getSelectedRow(), 36).toString());
            cmbProtusi.setSelectedItem(tbEvaluasi.getValueAt(tbEvaluasi.getSelectedRow(), 37).toString());
            cmbMallam.setSelectedItem(tbEvaluasi.getValueAt(tbEvaluasi.getSelectedRow(), 38).toString());
            cmbBuka.setSelectedItem(tbEvaluasi.getValueAt(tbEvaluasi.getSelectedRow(), 39).toString());
            Tjarak.setText(tbEvaluasi.getValueAt(tbEvaluasi.getSelectedRow(), 40).toString());
            Tgigi.setText(tbEvaluasi.getValueAt(tbEvaluasi.getSelectedRow(), 41).toString());
            TpsAsa.setText(tbEvaluasi.getValueAt(tbEvaluasi.getSelectedRow(), 42).toString());
            Tpenyulit.setText(tbEvaluasi.getValueAt(tbEvaluasi.getSelectedRow(), 43).toString());
            TrencanaAnes.setText(tbEvaluasi.getValueAt(tbEvaluasi.getSelectedRow(), 44).toString());
            Tinstruksi.setText(tbEvaluasi.getValueAt(tbEvaluasi.getSelectedRow(), 45).toString());
            
            if (cmbObat.getSelectedIndex() == 2) {
                TketObat.setEnabled(true);
            } else {
                TketObat.setEnabled(false);
            }
        }
    }
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.getcppt());
        BtnGanti.setEnabled(akses.getcppt());
        BtnHapus.setEnabled(akses.getcppt());
    }
    
    public void setData(String norw, String norm, String nmpasien, String ruangan) {
        TNoRw.setText(norw);
        TNoRM.setText(norm);
        TPasien.setText(nmpasien);
        TrgRawat.setText(ruangan);
        Valid.SetTgl(DTPCari1, Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + norw + "'"));
        TCari.setText(norw);
    }
    
    private void tampilTemplate() {
        Valid.tabelKosong(tabMode1);
        try {
            if (pilihan == 1) {
                ps1 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, e.* from evaluasi_pra_anestesi_operasi e "
                        + "inner join reg_periksa rp on rp.no_rawat=e.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "e.alergi_obat<>'' and p.no_rkm_medis like ? OR "
                        + "e.alergi_obat<>'' and p.nm_pasien like ? OR "
                        + "e.alergi_obat<>'' and e.alergi_obat like ? ORDER BY e.waktu_simpan desc limit 20");
            } else if (pilihan == 2) {
                ps2 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, e.* from evaluasi_pra_anestesi_operasi e "
                        + "inner join reg_periksa rp on rp.no_rawat=e.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "e.diagnosa<>'' and p.no_rkm_medis like ? OR "
                        + "e.diagnosa<>'' and p.nm_pasien like ? OR "
                        + "e.diagnosa<>'' and e.diagnosa like ? ORDER BY e.waktu_simpan desc limit 20");
            } else if (pilihan == 3) {
                ps3 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, e.* from evaluasi_pra_anestesi_operasi e "
                        + "inner join reg_periksa rp on rp.no_rawat=e.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "e.rencana_tindakan<>'' and p.no_rkm_medis like ? OR "
                        + "e.rencana_tindakan<>'' and p.nm_pasien like ? OR "
                        + "e.rencana_tindakan<>'' and e.rencana_tindakan like ? ORDER BY e.waktu_simpan desc limit 20");
            } else if (pilihan == 4) {
                ps4 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, e.* from evaluasi_pra_anestesi_operasi e "
                        + "inner join reg_periksa rp on rp.no_rawat=e.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "e.riwayat_anestesi<>'' and p.no_rkm_medis like ? OR "
                        + "e.riwayat_anestesi<>'' and p.nm_pasien like ? OR "
                        + "e.riwayat_anestesi<>'' and e.riwayat_anestesi like ? ORDER BY e.waktu_simpan desc limit 20");
            } else if (pilihan == 5) {
                ps5 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, e.* from evaluasi_pra_anestesi_operasi e "
                        + "inner join reg_periksa rp on rp.no_rawat=e.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "e.rencana_anestesi<>'' and p.no_rkm_medis like ? OR "
                        + "e.rencana_anestesi<>'' and p.nm_pasien like ? OR "
                        + "e.rencana_anestesi<>'' and e.rencana_anestesi like ? ORDER BY e.waktu_simpan desc limit 20");
            } else if (pilihan == 6) {
                ps6 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, e.* from evaluasi_pra_anestesi_operasi e "
                        + "inner join reg_periksa rp on rp.no_rawat=e.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "e.instruksi_anestesi<>'' and p.no_rkm_medis like ? OR "
                        + "e.instruksi_anestesi<>'' and p.nm_pasien like ? OR "
                        + "e.instruksi_anestesi<>'' and e.instruksi_anestesi like ? ORDER BY e.waktu_simpan desc limit 20");
            }            
            try {
                if (pilihan == 1) {
                    ps1.setString(1, "%" + TCari1.getText() + "%");
                    ps1.setString(2, "%" + TCari1.getText() + "%");
                    ps1.setString(3, "%" + TCari1.getText() + "%");
                    rs1 = ps1.executeQuery();
                    while (rs1.next()) {
                        tabMode1.addRow(new String[]{
                            rs1.getString("no_rkm_medis"),
                            rs1.getString("nm_pasien"),
                            rs1.getString("alergi_obat")
                        });
                    }
                } else if (pilihan == 2) {
                    ps2.setString(1, "%" + TCari1.getText() + "%");
                    ps2.setString(2, "%" + TCari1.getText() + "%");
                    ps2.setString(3, "%" + TCari1.getText() + "%");
                    rs2 = ps2.executeQuery();
                    while (rs2.next()) {
                        tabMode1.addRow(new String[]{
                            rs2.getString("no_rkm_medis"),
                            rs2.getString("nm_pasien"),
                            rs2.getString("diagnosa")
                        });
                    }
                } else if (pilihan == 3) {
                    ps3.setString(1, "%" + TCari1.getText() + "%");
                    ps3.setString(2, "%" + TCari1.getText() + "%");
                    ps3.setString(3, "%" + TCari1.getText() + "%");
                    rs3 = ps3.executeQuery();
                    while (rs3.next()) {
                        tabMode1.addRow(new String[]{
                            rs3.getString("no_rkm_medis"),
                            rs3.getString("nm_pasien"),
                            rs3.getString("rencana_tindakan")
                        });
                    }
                } else if (pilihan == 4) {
                    ps4.setString(1, "%" + TCari1.getText() + "%");
                    ps4.setString(2, "%" + TCari1.getText() + "%");
                    ps4.setString(3, "%" + TCari1.getText() + "%");
                    rs4 = ps4.executeQuery();
                    while (rs4.next()) {
                        tabMode1.addRow(new String[]{
                            rs4.getString("no_rkm_medis"),
                            rs4.getString("nm_pasien"),
                            rs4.getString("riwayat_anestesi")
                        });
                    }
                } else if (pilihan == 5) {
                    ps5.setString(1, "%" + TCari1.getText() + "%");
                    ps5.setString(2, "%" + TCari1.getText() + "%");
                    ps5.setString(3, "%" + TCari1.getText() + "%");
                    rs5 = ps5.executeQuery();
                    while (rs5.next()) {
                        tabMode1.addRow(new String[]{
                            rs5.getString("no_rkm_medis"),
                            rs5.getString("nm_pasien"),
                            rs5.getString("rencana_anestesi")
                        });
                    }
                } else if (pilihan == 6) {
                    ps6.setString(1, "%" + TCari1.getText() + "%");
                    ps6.setString(2, "%" + TCari1.getText() + "%");
                    ps6.setString(3, "%" + TCari1.getText() + "%");
                    rs6 = ps6.executeQuery();
                    while (rs6.next()) {
                        tabMode1.addRow(new String[]{
                            rs6.getString("no_rkm_medis"),
                            rs6.getString("nm_pasien"),
                            rs6.getString("instruksi_anestesi")
                        });
                    }
                } 
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs1 != null) {
                    rs1.close();
                } else if (rs2 != null) {
                    rs2.close();
                } else if (rs3 != null) {
                    rs3.close();
                } else if (rs4 != null) {
                    rs4.close();
                } else if (rs5 != null) {
                    rs5.close();
                } else if (rs6 != null) {
                    rs6.close();
                } 

                if (ps1 != null) {
                    ps1.close();
                } else if (ps2 != null) {
                    ps2.close();
                } else if (ps3 != null) {
                    ps3.close();
                } else if (ps4 != null) {
                    ps4.close();
                } else if (ps5 != null) {
                    ps5.close();
                } else if (ps6 != null) {
                    ps6.close();
                } 
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void copas() {
        if (pilihan == 1) {
            TalergiObat.setText(Ttemplate.getText());
        } else if (pilihan == 2) {
            Tdiagnosa.setText(Ttemplate.getText());
        } else if (pilihan == 3) {
            TrencanaTindakan.setText(Ttemplate.getText());
        } else if (pilihan == 4) {
            TriwAnestesi.setText(Ttemplate.getText());
        } else if (pilihan == 5) {
            TrencanaAnes.setText(Ttemplate.getText());
        } else if (pilihan == 6) {
            Tinstruksi.setText(Ttemplate.getText());
        } 
    }
}
