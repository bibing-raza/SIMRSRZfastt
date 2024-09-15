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
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import kepegawaian.DlgCariPetugas;
import laporan.DlgHasilPenunjangMedis;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import simrskhanza.DlgCariDokter;

/**
 *
 * @author dosen
 */
public class RMCeklisPraOperasi extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i = 0, x = 0, pilihDokter = 0, pilihPerawat = 0;
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public RMCeklisPraOperasi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new String[]{
            "waktu_simpan", "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Lahir", "Ruang Perawatan", "Diagnosa Pra Tindakan",
            "Rencana Tindakan", "Dokter Operator", "Dokter Anastesi", "Kesadaran", "Tensi", "Suhu", "Nadi", "Respirasi",
            "Persiapan Darah", "Persiapan Puasa", "Tgl. Ceklis", "Perawat Bangsal", "Perawat IBS",            
            "no_rawat", "ruang_rawat", "diagnosa_pra_tindakan", "rencana_tindakan", "nip_operator", "nip_anastesi",
            "kesadaran", "td", "suhu", "nadi", "respi", "pasang_infus", "ket_infus", "pasang_kateter", "ket_kateter",
            "cukur_daerah_operasi", "ket_cukur", "lavemen", "ket_lavemen", "gigi_palsu", "baju", "penandaan",
            "super_anastesi", "super_tindakan", "super_transfusi", "antibiotik_profilaksi", "ket_antibiotik", "jam_antibiotik",
            "pemeriksaan_penunjang", "ekg", "interpretasi_ekg", "interpretasi_ro", "persiapan_darah", "persiapan_puasa",
            "tgl_ceklis", "nip_perawat_bangsal", "nip_perawat_ibs"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbCeklis.setModel(tabMode);
        tbCeklis.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbCeklis.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 57; i++) {
            TableColumn column = tbCeklis.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(105);
            } else if (i == 2) {
                column.setPreferredWidth(65);
            } else if (i == 3) {
                column.setPreferredWidth(220);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(200);
            } else if (i == 6) {
                column.setPreferredWidth(220);
            } else if (i == 7) {
                column.setPreferredWidth(220);
            } else if (i == 8) {
                column.setPreferredWidth(220);
            } else if (i == 9) {
                column.setPreferredWidth(220);
            } else if (i == 10) {
                column.setPreferredWidth(100);
            } else if (i == 11) {
                column.setPreferredWidth(70);
            } else if (i == 12) {
                column.setPreferredWidth(60);
            } else if (i == 13) {
                column.setPreferredWidth(60);
            } else if (i == 14) {
                column.setPreferredWidth(80);
            } else if (i == 15) {
                column.setPreferredWidth(90);
            } else if (i == 16) {
                column.setPreferredWidth(90);
            } else if (i == 17) {
                column.setPreferredWidth(75);
            } else if (i == 18) {
                column.setPreferredWidth(220);
            } else if (i == 19) {
                column.setPreferredWidth(220);
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
            } else if (i == 47) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 48) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 49) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 50) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 51) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 52) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 53) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 54) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 55) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 56) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbCeklis.setDefaultRenderer(Object.class, new WarnaTable());

        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        Ttensi.setDocument(new batasInput((byte) 7).getKata(Ttensi));
        Tsuhu.setDocument(new batasInput((byte) 7).getKata(Tsuhu));
        Tnadi.setDocument(new batasInput((byte) 7).getKata(Tnadi));
        Trespi.setDocument(new batasInput((byte) 7).getKata(Trespi));        
        Tinfus.setDocument(new batasInput((int) 200).getKata(Tinfus));
        Tkateter.setDocument(new batasInput((int) 200).getKata(Tkateter));
        Tcukur.setDocument(new batasInput((int) 200).getKata(Tcukur));
        Tlavemen.setDocument(new batasInput((int) 200).getKata(Tlavemen));
        Tantibiotik.setDocument(new batasInput((int) 200).getKata(Tantibiotik));
        TintepretasiEkg.setDocument(new batasInput((int) 200).getKata(TintepretasiEkg));
        
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
                if (akses.getform().equals("RMCeklisPraOperasi")) {
                    if (pilihDokter == 1) {
                        if (dokter.getTable().getSelectedRow() != -1) {
                            TnipOperator.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                            TnmOperator.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                            BtnOperator.requestFocus();
                        }                        
                    } else if (pilihDokter == 2) {
                        if (dokter.getTable().getSelectedRow() != -1) {
                            TnipAnastesi.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                            TnmAnastesi.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                            BtnAnastesi.requestFocus();
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
        
        petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("RMCeklisPraOperasi")) {
                    if (pilihPerawat == 1) {
                        if (petugas.getTable().getSelectedRow() != -1) {
                            TnipBangsal.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
                            TnmPerawatBangsal.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                            BtnBangsal.requestFocus();
                        }
                    } else if (pilihPerawat == 2) {
                        if (petugas.getTable().getSelectedRow() != -1) {
                            TnipIbs.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
                            TnmPerawatIbs.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                            BtnIbs.requestFocus();
                        }
                    }
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
        internalFrame1 = new widget.InternalFrame();
        Scroll1 = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        jLabel10 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel63 = new widget.Label();
        TrgRawat = new widget.TextBox();
        jLabel64 = new widget.Label();
        Tdiagnosa = new widget.TextBox();
        jLabel65 = new widget.Label();
        Trencana = new widget.TextBox();
        label15 = new widget.Label();
        TnipOperator = new widget.TextBox();
        TnmOperator = new widget.TextBox();
        BtnOperator = new widget.Button();
        label16 = new widget.Label();
        TnipAnastesi = new widget.TextBox();
        TnmAnastesi = new widget.TextBox();
        BtnAnastesi = new widget.Button();
        label17 = new widget.Label();
        jLabel66 = new widget.Label();
        Tkesadaran = new widget.TextBox();
        jLabel67 = new widget.Label();
        Ttensi = new widget.TextBox();
        label104 = new widget.Label();
        Tsuhu = new widget.TextBox();
        jLabel23 = new widget.Label();
        Tnadi = new widget.TextBox();
        label43 = new widget.Label();
        Trespi = new widget.TextBox();
        label106 = new widget.Label();
        label18 = new widget.Label();
        jLabel68 = new widget.Label();
        cmbInfus = new widget.ComboBox();
        Tinfus = new widget.TextBox();
        jLabel69 = new widget.Label();
        cmbKateter = new widget.ComboBox();
        Tkateter = new widget.TextBox();
        jLabel70 = new widget.Label();
        cmbCukur = new widget.ComboBox();
        Tcukur = new widget.TextBox();
        jLabel71 = new widget.Label();
        cmbLavemen = new widget.ComboBox();
        Tlavemen = new widget.TextBox();
        jLabel72 = new widget.Label();
        cmbGigi = new widget.ComboBox();
        jLabel73 = new widget.Label();
        cmbBaju = new widget.ComboBox();
        jLabel74 = new widget.Label();
        cmbPenandaan = new widget.ComboBox();
        jLabel75 = new widget.Label();
        cmbSuperAnastesi = new widget.ComboBox();
        jLabel76 = new widget.Label();
        jLabel77 = new widget.Label();
        cmbSuperTindakan = new widget.ComboBox();
        cmbSuperTransfusi = new widget.ComboBox();
        jLabel78 = new widget.Label();
        cmbAntibiotik = new widget.ComboBox();
        Tantibiotik = new widget.TextBox();
        jLabel79 = new widget.Label();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        label19 = new widget.Label();
        scrollPane13 = new widget.ScrollPane();
        Tpemeriksaan = new widget.TextArea();
        jLabel80 = new widget.Label();
        cmbEkg = new widget.ComboBox();
        jLabel81 = new widget.Label();
        TintepretasiEkg = new widget.TextBox();
        jLabel82 = new widget.Label();
        cmbIntepretasiRo = new widget.ComboBox();
        jLabel83 = new widget.Label();
        cmbPersiapanDarah = new widget.ComboBox();
        jLabel84 = new widget.Label();
        cmbPersiapanPuasa = new widget.ComboBox();
        jLabel85 = new widget.Label();
        Ttglceklis = new widget.Tanggal();
        label20 = new widget.Label();
        TnipBangsal = new widget.TextBox();
        TnmPerawatBangsal = new widget.TextBox();
        BtnBangsal = new widget.Button();
        label21 = new widget.Label();
        TnipIbs = new widget.TextBox();
        TnmPerawatIbs = new widget.TextBox();
        BtnIbs = new widget.Button();
        chkSaya1 = new widget.CekBox();
        chkSaya2 = new widget.CekBox();
        BtnPaste = new widget.Button();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnGanti = new widget.Button();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();
        PanelInput1 = new javax.swing.JPanel();
        Scroll = new widget.ScrollPane();
        tbCeklis = new widget.Table();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Checklist Pra Operasi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);
        Scroll1.setPreferredSize(new java.awt.Dimension(600, 402));

        FormInput.setBackground(new java.awt.Color(255, 255, 255));
        FormInput.setBorder(null);
        FormInput.setToolTipText("Klik Kanan Pada Area Ini Untuk Melihat Hasil Pemeriksaan Penunjang Medis");
        FormInput.setComponentPopupMenu(jPopupMenu1);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(870, 690));
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
        FormInput.add(jLabel63);
        jLabel63.setBounds(0, 38, 140, 23);

        TrgRawat.setEditable(false);
        TrgRawat.setForeground(new java.awt.Color(0, 0, 0));
        TrgRawat.setName("TrgRawat"); // NOI18N
        FormInput.add(TrgRawat);
        TrgRawat.setBounds(145, 38, 615, 23);

        jLabel64.setForeground(new java.awt.Color(0, 0, 0));
        jLabel64.setText("Diagnosa Pra Tindakan :");
        jLabel64.setName("jLabel64"); // NOI18N
        FormInput.add(jLabel64);
        jLabel64.setBounds(0, 66, 140, 23);

        Tdiagnosa.setForeground(new java.awt.Color(0, 0, 0));
        Tdiagnosa.setName("Tdiagnosa"); // NOI18N
        Tdiagnosa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdiagnosaKeyPressed(evt);
            }
        });
        FormInput.add(Tdiagnosa);
        Tdiagnosa.setBounds(145, 66, 615, 23);

        jLabel65.setForeground(new java.awt.Color(0, 0, 0));
        jLabel65.setText("Rencana Tindakan :");
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput.add(jLabel65);
        jLabel65.setBounds(0, 94, 140, 23);

        Trencana.setForeground(new java.awt.Color(0, 0, 0));
        Trencana.setName("Trencana"); // NOI18N
        Trencana.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TrencanaKeyPressed(evt);
            }
        });
        FormInput.add(Trencana);
        Trencana.setBounds(145, 94, 615, 23);

        label15.setForeground(new java.awt.Color(0, 0, 0));
        label15.setText("Dokter Operator :");
        label15.setName("label15"); // NOI18N
        label15.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label15);
        label15.setBounds(0, 122, 140, 23);

        TnipOperator.setEditable(false);
        TnipOperator.setForeground(new java.awt.Color(0, 0, 0));
        TnipOperator.setName("TnipOperator"); // NOI18N
        TnipOperator.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(TnipOperator);
        TnipOperator.setBounds(145, 122, 150, 23);

        TnmOperator.setEditable(false);
        TnmOperator.setForeground(new java.awt.Color(0, 0, 0));
        TnmOperator.setName("TnmOperator"); // NOI18N
        TnmOperator.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(TnmOperator);
        TnmOperator.setBounds(298, 122, 360, 23);

        BtnOperator.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnOperator.setMnemonic('2');
        BtnOperator.setToolTipText("Alt+2");
        BtnOperator.setName("BtnOperator"); // NOI18N
        BtnOperator.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnOperator.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnOperatorActionPerformed(evt);
            }
        });
        FormInput.add(BtnOperator);
        BtnOperator.setBounds(660, 122, 28, 23);

        label16.setForeground(new java.awt.Color(0, 0, 0));
        label16.setText("Dokter Anastesi :");
        label16.setName("label16"); // NOI18N
        label16.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label16);
        label16.setBounds(0, 150, 140, 23);

        TnipAnastesi.setEditable(false);
        TnipAnastesi.setForeground(new java.awt.Color(0, 0, 0));
        TnipAnastesi.setName("TnipAnastesi"); // NOI18N
        TnipAnastesi.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(TnipAnastesi);
        TnipAnastesi.setBounds(145, 150, 150, 23);

        TnmAnastesi.setEditable(false);
        TnmAnastesi.setForeground(new java.awt.Color(0, 0, 0));
        TnmAnastesi.setName("TnmAnastesi"); // NOI18N
        TnmAnastesi.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(TnmAnastesi);
        TnmAnastesi.setBounds(298, 150, 360, 23);

        BtnAnastesi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnAnastesi.setMnemonic('2');
        BtnAnastesi.setToolTipText("Alt+2");
        BtnAnastesi.setName("BtnAnastesi"); // NOI18N
        BtnAnastesi.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnAnastesi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAnastesiActionPerformed(evt);
            }
        });
        FormInput.add(BtnAnastesi);
        BtnAnastesi.setBounds(660, 150, 28, 23);

        label17.setForeground(new java.awt.Color(0, 0, 0));
        label17.setText("KEADAAN UMUM :");
        label17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label17.setName("label17"); // NOI18N
        label17.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label17);
        label17.setBounds(0, 178, 140, 23);

        jLabel66.setForeground(new java.awt.Color(0, 0, 0));
        jLabel66.setText("Kesadaran :");
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput.add(jLabel66);
        jLabel66.setBounds(0, 206, 140, 23);

        Tkesadaran.setForeground(new java.awt.Color(0, 0, 0));
        Tkesadaran.setName("Tkesadaran"); // NOI18N
        Tkesadaran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkesadaranKeyPressed(evt);
            }
        });
        FormInput.add(Tkesadaran);
        Tkesadaran.setBounds(145, 206, 615, 23);

        jLabel67.setForeground(new java.awt.Color(0, 0, 0));
        jLabel67.setText("Tekanan Darah :");
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput.add(jLabel67);
        jLabel67.setBounds(0, 234, 140, 23);

        Ttensi.setForeground(new java.awt.Color(0, 0, 0));
        Ttensi.setName("Ttensi"); // NOI18N
        Ttensi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtensiKeyPressed(evt);
            }
        });
        FormInput.add(Ttensi);
        Ttensi.setBounds(145, 234, 70, 23);

        label104.setForeground(new java.awt.Color(0, 0, 0));
        label104.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label104.setText("mmHg    Suhu : ");
        label104.setName("label104"); // NOI18N
        label104.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label104);
        label104.setBounds(220, 234, 75, 23);

        Tsuhu.setForeground(new java.awt.Color(0, 0, 0));
        Tsuhu.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tsuhu.setName("Tsuhu"); // NOI18N
        Tsuhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TsuhuKeyPressed(evt);
            }
        });
        FormInput.add(Tsuhu);
        Tsuhu.setBounds(298, 234, 48, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel23.setText("°C     Nadi :");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(350, 234, 58, 23);

        Tnadi.setForeground(new java.awt.Color(0, 0, 0));
        Tnadi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tnadi.setName("Tnadi"); // NOI18N
        Tnadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnadiKeyPressed(evt);
            }
        });
        FormInput.add(Tnadi);
        Tnadi.setBounds(409, 234, 70, 23);

        label43.setForeground(new java.awt.Color(0, 0, 0));
        label43.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label43.setText("x/menit      Respirasi : ");
        label43.setName("label43"); // NOI18N
        label43.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label43);
        label43.setBounds(485, 234, 108, 23);

        Trespi.setForeground(new java.awt.Color(0, 0, 0));
        Trespi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Trespi.setName("Trespi"); // NOI18N
        Trespi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TrespiKeyPressed(evt);
            }
        });
        FormInput.add(Trespi);
        Trespi.setBounds(593, 234, 60, 23);

        label106.setForeground(new java.awt.Color(0, 0, 0));
        label106.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label106.setText("x/menit");
        label106.setName("label106"); // NOI18N
        label106.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label106);
        label106.setBounds(657, 234, 60, 23);

        label18.setForeground(new java.awt.Color(0, 0, 0));
        label18.setText("PERLENGKAPAN :");
        label18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label18.setName("label18"); // NOI18N
        label18.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label18);
        label18.setBounds(0, 262, 140, 23);

        jLabel68.setForeground(new java.awt.Color(0, 0, 0));
        jLabel68.setText("Pasang Infus :");
        jLabel68.setName("jLabel68"); // NOI18N
        FormInput.add(jLabel68);
        jLabel68.setBounds(0, 290, 140, 23);

        cmbInfus.setForeground(new java.awt.Color(0, 0, 0));
        cmbInfus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbInfus.setName("cmbInfus"); // NOI18N
        cmbInfus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbInfusActionPerformed(evt);
            }
        });
        FormInput.add(cmbInfus);
        cmbInfus.setBounds(145, 290, 60, 23);

        Tinfus.setForeground(new java.awt.Color(0, 0, 0));
        Tinfus.setName("Tinfus"); // NOI18N
        Tinfus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TinfusKeyPressed(evt);
            }
        });
        FormInput.add(Tinfus);
        Tinfus.setBounds(210, 290, 550, 23);

        jLabel69.setForeground(new java.awt.Color(0, 0, 0));
        jLabel69.setText("Pasang Kateter :");
        jLabel69.setName("jLabel69"); // NOI18N
        FormInput.add(jLabel69);
        jLabel69.setBounds(0, 318, 140, 23);

        cmbKateter.setForeground(new java.awt.Color(0, 0, 0));
        cmbKateter.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbKateter.setName("cmbKateter"); // NOI18N
        cmbKateter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKateterActionPerformed(evt);
            }
        });
        FormInput.add(cmbKateter);
        cmbKateter.setBounds(145, 318, 60, 23);

        Tkateter.setForeground(new java.awt.Color(0, 0, 0));
        Tkateter.setName("Tkateter"); // NOI18N
        Tkateter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkateterKeyPressed(evt);
            }
        });
        FormInput.add(Tkateter);
        Tkateter.setBounds(210, 318, 550, 23);

        jLabel70.setForeground(new java.awt.Color(0, 0, 0));
        jLabel70.setText("Cukur Daerah Operasi :");
        jLabel70.setName("jLabel70"); // NOI18N
        FormInput.add(jLabel70);
        jLabel70.setBounds(0, 346, 140, 23);

        cmbCukur.setForeground(new java.awt.Color(0, 0, 0));
        cmbCukur.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbCukur.setName("cmbCukur"); // NOI18N
        cmbCukur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCukurActionPerformed(evt);
            }
        });
        FormInput.add(cmbCukur);
        cmbCukur.setBounds(145, 346, 60, 23);

        Tcukur.setForeground(new java.awt.Color(0, 0, 0));
        Tcukur.setName("Tcukur"); // NOI18N
        Tcukur.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TcukurKeyPressed(evt);
            }
        });
        FormInput.add(Tcukur);
        Tcukur.setBounds(210, 346, 550, 23);

        jLabel71.setForeground(new java.awt.Color(0, 0, 0));
        jLabel71.setText("Lavement :");
        jLabel71.setName("jLabel71"); // NOI18N
        FormInput.add(jLabel71);
        jLabel71.setBounds(0, 374, 140, 23);

        cmbLavemen.setForeground(new java.awt.Color(0, 0, 0));
        cmbLavemen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbLavemen.setName("cmbLavemen"); // NOI18N
        cmbLavemen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbLavemenActionPerformed(evt);
            }
        });
        FormInput.add(cmbLavemen);
        cmbLavemen.setBounds(145, 374, 60, 23);

        Tlavemen.setForeground(new java.awt.Color(0, 0, 0));
        Tlavemen.setName("Tlavemen"); // NOI18N
        Tlavemen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlavemenKeyPressed(evt);
            }
        });
        FormInput.add(Tlavemen);
        Tlavemen.setBounds(210, 374, 550, 23);

        jLabel72.setForeground(new java.awt.Color(0, 0, 0));
        jLabel72.setText("Gigi Palsu :");
        jLabel72.setName("jLabel72"); // NOI18N
        FormInput.add(jLabel72);
        jLabel72.setBounds(0, 402, 140, 23);

        cmbGigi.setForeground(new java.awt.Color(0, 0, 0));
        cmbGigi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada", "Ada" }));
        cmbGigi.setName("cmbGigi"); // NOI18N
        FormInput.add(cmbGigi);
        cmbGigi.setBounds(145, 402, 80, 23);

        jLabel73.setForeground(new java.awt.Color(0, 0, 0));
        jLabel73.setText("Baju & Topi Operasi :");
        jLabel73.setName("jLabel73"); // NOI18N
        FormInput.add(jLabel73);
        jLabel73.setBounds(0, 430, 140, 23);

        cmbBaju.setForeground(new java.awt.Color(0, 0, 0));
        cmbBaju.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Belum", "Sudah" }));
        cmbBaju.setName("cmbBaju"); // NOI18N
        FormInput.add(cmbBaju);
        cmbBaju.setBounds(145, 430, 60, 23);

        jLabel74.setForeground(new java.awt.Color(0, 0, 0));
        jLabel74.setText("Penandaan Daerah Operasi :");
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput.add(jLabel74);
        jLabel74.setBounds(225, 402, 160, 23);

        cmbPenandaan.setForeground(new java.awt.Color(0, 0, 0));
        cmbPenandaan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Belum", "Sudah" }));
        cmbPenandaan.setName("cmbPenandaan"); // NOI18N
        FormInput.add(cmbPenandaan);
        cmbPenandaan.setBounds(390, 402, 60, 23);

        jLabel75.setForeground(new java.awt.Color(0, 0, 0));
        jLabel75.setText("Surat Persetujuan Anastesi :");
        jLabel75.setName("jLabel75"); // NOI18N
        FormInput.add(jLabel75);
        jLabel75.setBounds(225, 430, 160, 23);

        cmbSuperAnastesi.setForeground(new java.awt.Color(0, 0, 0));
        cmbSuperAnastesi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Belum", "Sudah" }));
        cmbSuperAnastesi.setName("cmbSuperAnastesi"); // NOI18N
        FormInput.add(cmbSuperAnastesi);
        cmbSuperAnastesi.setBounds(390, 430, 60, 23);

        jLabel76.setForeground(new java.awt.Color(0, 0, 0));
        jLabel76.setText("Surat Persetujuan Tindakan :");
        jLabel76.setName("jLabel76"); // NOI18N
        FormInput.add(jLabel76);
        jLabel76.setBounds(450, 402, 160, 23);

        jLabel77.setForeground(new java.awt.Color(0, 0, 0));
        jLabel77.setText("Surat Persetujuan Transfusi :");
        jLabel77.setName("jLabel77"); // NOI18N
        FormInput.add(jLabel77);
        jLabel77.setBounds(450, 430, 160, 23);

        cmbSuperTindakan.setForeground(new java.awt.Color(0, 0, 0));
        cmbSuperTindakan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Belum", "Sudah" }));
        cmbSuperTindakan.setName("cmbSuperTindakan"); // NOI18N
        FormInput.add(cmbSuperTindakan);
        cmbSuperTindakan.setBounds(616, 402, 60, 23);

        cmbSuperTransfusi.setForeground(new java.awt.Color(0, 0, 0));
        cmbSuperTransfusi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Belum", "Sudah" }));
        cmbSuperTransfusi.setName("cmbSuperTransfusi"); // NOI18N
        FormInput.add(cmbSuperTransfusi);
        cmbSuperTransfusi.setBounds(616, 430, 60, 23);

        jLabel78.setForeground(new java.awt.Color(0, 0, 0));
        jLabel78.setText("Antibiotik Profilaksi :");
        jLabel78.setName("jLabel78"); // NOI18N
        FormInput.add(jLabel78);
        jLabel78.setBounds(0, 458, 140, 23);

        cmbAntibiotik.setForeground(new java.awt.Color(0, 0, 0));
        cmbAntibiotik.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbAntibiotik.setName("cmbAntibiotik"); // NOI18N
        cmbAntibiotik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAntibiotikActionPerformed(evt);
            }
        });
        FormInput.add(cmbAntibiotik);
        cmbAntibiotik.setBounds(145, 458, 60, 23);

        Tantibiotik.setForeground(new java.awt.Color(0, 0, 0));
        Tantibiotik.setName("Tantibiotik"); // NOI18N
        Tantibiotik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TantibiotikKeyPressed(evt);
            }
        });
        FormInput.add(Tantibiotik);
        Tantibiotik.setBounds(210, 458, 340, 23);

        jLabel79.setForeground(new java.awt.Color(0, 0, 0));
        jLabel79.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel79.setText("gr,   Jam :");
        jLabel79.setName("jLabel79"); // NOI18N
        FormInput.add(jLabel79);
        jLabel79.setBounds(555, 458, 53, 23);

        cmbJam.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJamMouseReleased(evt);
            }
        });
        FormInput.add(cmbJam);
        cmbJam.setBounds(608, 458, 45, 23);

        cmbMnt.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMntMouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt);
        cmbMnt.setBounds(659, 458, 45, 23);

        cmbDtk.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtkMouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk);
        cmbDtk.setBounds(710, 458, 45, 23);

        label19.setForeground(new java.awt.Color(0, 0, 0));
        label19.setText("PEMERIKSAAN PENUNJANG :");
        label19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label19.setName("label19"); // NOI18N
        label19.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label19);
        label19.setBounds(0, 486, 180, 23);

        scrollPane13.setName("scrollPane13"); // NOI18N

        Tpemeriksaan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tpemeriksaan.setColumns(20);
        Tpemeriksaan.setRows(5);
        Tpemeriksaan.setName("Tpemeriksaan"); // NOI18N
        Tpemeriksaan.setPreferredSize(new java.awt.Dimension(162, 1500));
        Tpemeriksaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpemeriksaanKeyPressed(evt);
            }
        });
        scrollPane13.setViewportView(Tpemeriksaan);

        FormInput.add(scrollPane13);
        scrollPane13.setBounds(185, 486, 575, 80);

        jLabel80.setForeground(new java.awt.Color(0, 0, 0));
        jLabel80.setText("EKG :");
        jLabel80.setName("jLabel80"); // NOI18N
        FormInput.add(jLabel80);
        jLabel80.setBounds(0, 572, 140, 23);

        cmbEkg.setForeground(new java.awt.Color(0, 0, 0));
        cmbEkg.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbEkg.setName("cmbEkg"); // NOI18N
        FormInput.add(cmbEkg);
        cmbEkg.setBounds(145, 572, 60, 23);

        jLabel81.setForeground(new java.awt.Color(0, 0, 0));
        jLabel81.setText("Intepretasi EKG : ");
        jLabel81.setName("jLabel81"); // NOI18N
        FormInput.add(jLabel81);
        jLabel81.setBounds(205, 572, 105, 23);

        TintepretasiEkg.setForeground(new java.awt.Color(0, 0, 0));
        TintepretasiEkg.setName("TintepretasiEkg"); // NOI18N
        TintepretasiEkg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TintepretasiEkgKeyPressed(evt);
            }
        });
        FormInput.add(TintepretasiEkg);
        TintepretasiEkg.setBounds(310, 572, 450, 23);

        jLabel82.setForeground(new java.awt.Color(0, 0, 0));
        jLabel82.setText("Intepretasi RO/CT-Scan :");
        jLabel82.setName("jLabel82"); // NOI18N
        FormInput.add(jLabel82);
        jLabel82.setBounds(0, 600, 140, 23);

        cmbIntepretasiRo.setForeground(new java.awt.Color(0, 0, 0));
        cmbIntepretasiRo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbIntepretasiRo.setName("cmbIntepretasiRo"); // NOI18N
        FormInput.add(cmbIntepretasiRo);
        cmbIntepretasiRo.setBounds(145, 600, 60, 23);

        jLabel83.setForeground(new java.awt.Color(0, 0, 0));
        jLabel83.setText("Persiapan Darah : ");
        jLabel83.setName("jLabel83"); // NOI18N
        FormInput.add(jLabel83);
        jLabel83.setBounds(205, 600, 105, 23);

        cmbPersiapanDarah.setForeground(new java.awt.Color(0, 0, 0));
        cmbPersiapanDarah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbPersiapanDarah.setName("cmbPersiapanDarah"); // NOI18N
        FormInput.add(cmbPersiapanDarah);
        cmbPersiapanDarah.setBounds(310, 600, 60, 23);

        jLabel84.setForeground(new java.awt.Color(0, 0, 0));
        jLabel84.setText("Persiapan Puasa : ");
        jLabel84.setName("jLabel84"); // NOI18N
        FormInput.add(jLabel84);
        jLabel84.setBounds(370, 600, 105, 23);

        cmbPersiapanPuasa.setForeground(new java.awt.Color(0, 0, 0));
        cmbPersiapanPuasa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbPersiapanPuasa.setName("cmbPersiapanPuasa"); // NOI18N
        FormInput.add(cmbPersiapanPuasa);
        cmbPersiapanPuasa.setBounds(476, 600, 60, 23);

        jLabel85.setForeground(new java.awt.Color(0, 0, 0));
        jLabel85.setText("Tgl. Checlist :");
        jLabel85.setName("jLabel85"); // NOI18N
        FormInput.add(jLabel85);
        jLabel85.setBounds(535, 600, 80, 23);

        Ttglceklis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "15-09-2024" }));
        Ttglceklis.setDisplayFormat("dd-MM-yyyy");
        Ttglceklis.setName("Ttglceklis"); // NOI18N
        Ttglceklis.setOpaque(false);
        Ttglceklis.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(Ttglceklis);
        Ttglceklis.setBounds(620, 600, 90, 23);

        label20.setForeground(new java.awt.Color(0, 0, 0));
        label20.setText("Perawat Bangsal :");
        label20.setName("label20"); // NOI18N
        label20.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label20);
        label20.setBounds(0, 628, 140, 23);

        TnipBangsal.setEditable(false);
        TnipBangsal.setForeground(new java.awt.Color(0, 0, 0));
        TnipBangsal.setName("TnipBangsal"); // NOI18N
        TnipBangsal.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(TnipBangsal);
        TnipBangsal.setBounds(145, 628, 150, 23);

        TnmPerawatBangsal.setEditable(false);
        TnmPerawatBangsal.setForeground(new java.awt.Color(0, 0, 0));
        TnmPerawatBangsal.setName("TnmPerawatBangsal"); // NOI18N
        TnmPerawatBangsal.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(TnmPerawatBangsal);
        TnmPerawatBangsal.setBounds(298, 628, 360, 23);

        BtnBangsal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnBangsal.setMnemonic('2');
        BtnBangsal.setToolTipText("Alt+2");
        BtnBangsal.setName("BtnBangsal"); // NOI18N
        BtnBangsal.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnBangsal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBangsalActionPerformed(evt);
            }
        });
        FormInput.add(BtnBangsal);
        BtnBangsal.setBounds(660, 628, 28, 23);

        label21.setForeground(new java.awt.Color(0, 0, 0));
        label21.setText("Perawat Bedah Sentral :");
        label21.setName("label21"); // NOI18N
        label21.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label21);
        label21.setBounds(0, 656, 140, 23);

        TnipIbs.setEditable(false);
        TnipIbs.setForeground(new java.awt.Color(0, 0, 0));
        TnipIbs.setName("TnipIbs"); // NOI18N
        TnipIbs.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(TnipIbs);
        TnipIbs.setBounds(145, 656, 150, 23);

        TnmPerawatIbs.setEditable(false);
        TnmPerawatIbs.setForeground(new java.awt.Color(0, 0, 0));
        TnmPerawatIbs.setName("TnmPerawatIbs"); // NOI18N
        TnmPerawatIbs.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(TnmPerawatIbs);
        TnmPerawatIbs.setBounds(298, 656, 360, 23);

        BtnIbs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnIbs.setMnemonic('2');
        BtnIbs.setToolTipText("Alt+2");
        BtnIbs.setName("BtnIbs"); // NOI18N
        BtnIbs.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnIbs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnIbsActionPerformed(evt);
            }
        });
        FormInput.add(BtnIbs);
        BtnIbs.setBounds(660, 656, 28, 23);

        chkSaya1.setBackground(new java.awt.Color(242, 242, 242));
        chkSaya1.setForeground(new java.awt.Color(0, 0, 0));
        chkSaya1.setText("Saya Sendiri");
        chkSaya1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSaya1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSaya1.setName("chkSaya1"); // NOI18N
        chkSaya1.setOpaque(false);
        chkSaya1.setPreferredSize(new java.awt.Dimension(220, 23));
        chkSaya1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSaya1ActionPerformed(evt);
            }
        });
        FormInput.add(chkSaya1);
        chkSaya1.setBounds(695, 628, 90, 23);

        chkSaya2.setBackground(new java.awt.Color(242, 242, 242));
        chkSaya2.setForeground(new java.awt.Color(0, 0, 0));
        chkSaya2.setText("Saya Sendiri");
        chkSaya2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSaya2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSaya2.setName("chkSaya2"); // NOI18N
        chkSaya2.setOpaque(false);
        chkSaya2.setPreferredSize(new java.awt.Dimension(220, 23));
        chkSaya2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSaya2ActionPerformed(evt);
            }
        });
        FormInput.add(chkSaya2);
        chkSaya2.setBounds(695, 656, 90, 23);

        BtnPaste.setForeground(new java.awt.Color(0, 0, 0));
        BtnPaste.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/paste.png"))); // NOI18N
        BtnPaste.setMnemonic('L');
        BtnPaste.setText("Paste");
        BtnPaste.setToolTipText("Alt+L");
        BtnPaste.setName("BtnPaste"); // NOI18N
        BtnPaste.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPaste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPasteActionPerformed(evt);
            }
        });
        FormInput.add(BtnPaste);
        BtnPaste.setBounds(85, 516, 90, 23);

        Scroll1.setViewportView(FormInput);

        internalFrame1.add(Scroll1, java.awt.BorderLayout.CENTER);

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(55, 47));
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

        internalFrame1.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        PanelInput1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Data Checklist Pra Operasi ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        PanelInput1.setName("PanelInput1"); // NOI18N
        PanelInput1.setOpaque(false);
        PanelInput1.setPreferredSize(new java.awt.Dimension(700, 700));
        PanelInput1.setLayout(new java.awt.BorderLayout());

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(600, 402));

        tbCeklis.setToolTipText("Silahkan klik untuk memilih data yang diperbaiki/dihapus");
        tbCeklis.setName("tbCeklis"); // NOI18N
        tbCeklis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbCeklisMouseClicked(evt);
            }
        });
        tbCeklis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbCeklisKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbCeklis);

        PanelInput1.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass11.setName("panelGlass11"); // NOI18N
        panelGlass11.setPreferredSize(new java.awt.Dimension(44, 86));
        panelGlass11.setLayout(new java.awt.BorderLayout());

        panelGlass12.setName("panelGlass12"); // NOI18N
        panelGlass12.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 6));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tgl. Checklist :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass12.add(jLabel19);

        DTPCari1.setEditable(false);
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "15-09-2024" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "15-09-2024" }));
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
        BtnCari.setMnemonic('2');
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
        BtnAll.setMnemonic('M');
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

        internalFrame1.add(PanelInput1, java.awt.BorderLayout.EAST);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().equals("")) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else {
            try {
                if (Sequel.menyimpantf("ceklis_pra_operasi", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 38, new String[]{
                    TNoRw.getText(), TrgRawat.getText(), Tdiagnosa.getText(), Trencana.getText(), TnipOperator.getText(), TnipAnastesi.getText(), Tkesadaran.getText(),
                    Ttensi.getText(), Tsuhu.getText(), Tnadi.getText(), Trespi.getText(), cmbInfus.getSelectedItem().toString(), Tinfus.getText(), cmbKateter.getSelectedItem().toString(),
                    Tkateter.getText(), cmbCukur.getSelectedItem().toString(), Tcukur.getText(), cmbLavemen.getSelectedItem().toString(), Tlavemen.getText(),
                    cmbGigi.getSelectedItem().toString(), cmbBaju.getSelectedItem().toString(), cmbPenandaan.getSelectedItem().toString(), cmbSuperAnastesi.getSelectedItem().toString(),
                    cmbSuperTindakan.getSelectedItem().toString(), cmbSuperTransfusi.getSelectedItem().toString(), cmbAntibiotik.getSelectedItem().toString(), Tantibiotik.getText(),
                    cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(), Valid.mysql_real_escape_stringERM(Tpemeriksaan.getText()),
                    cmbEkg.getSelectedItem().toString(), TintepretasiEkg.getText(), cmbIntepretasiRo.getSelectedItem().toString(), cmbPersiapanDarah.getSelectedItem().toString(),
                    cmbPersiapanPuasa.getSelectedItem().toString(), Valid.SetTgl(Ttglceklis.getSelectedItem() + ""), TnipBangsal.getText(), TnipIbs.getText(),
                    Sequel.cariIsi("select now()")
                }) == true) {

                    TCari.setText(TNoRw.getText());
                    emptTeks();
                    tampil();
                }
            } catch (Exception e) {
                System.out.println("Simpan Checklist Pra Operasi : " + e);
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
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
            if (tbCeklis.getSelectedRow() > -1) {
                try {
                    if (Sequel.mengedittf("ceklis_pra_operasi", "waktu_simpan=?", "diagnosa_pra_tindakan=?, rencana_tindakan=?, nip_operator=?, "
                            + "nip_anastesi=?, kesadaran=?, td=?, suhu=?, nadi=?, respi=?, pasang_infus=?, ket_infus=?, pasang_kateter=?, ket_kateter=?, cukur_daerah_operasi=?, "
                            + "ket_cukur=?, lavemen=?, ket_lavemen=?, gigi_palsu=?, baju=?, penandaan=?, super_anastesi=?, super_tindakan=?, super_transfusi=?, "
                            + "antibiotik_profilaksi=?, ket_antibiotik=?, jam_antibiotik=?, pemeriksaan_penunjang=?, ekg=?, interpretasi_ekg=?, "
                            + " interpretasi_ro=?, persiapan_darah=?, persiapan_puasa=?, tgl_ceklis=?, nip_perawat_bangsal=?, nip_perawat_ibs=?", 36, new String[]{
                                Tdiagnosa.getText(), Trencana.getText(), TnipOperator.getText(), TnipAnastesi.getText(), Tkesadaran.getText(),
                                Ttensi.getText(), Tsuhu.getText(), Tnadi.getText(), Trespi.getText(), cmbInfus.getSelectedItem().toString(), Tinfus.getText(),
                                cmbKateter.getSelectedItem().toString(), Tkateter.getText(), cmbCukur.getSelectedItem().toString(), Tcukur.getText(),
                                cmbLavemen.getSelectedItem().toString(), Tlavemen.getText(), cmbGigi.getSelectedItem().toString(), cmbBaju.getSelectedItem().toString(),
                                cmbPenandaan.getSelectedItem().toString(), cmbSuperAnastesi.getSelectedItem().toString(), cmbSuperTindakan.getSelectedItem().toString(),
                                cmbSuperTransfusi.getSelectedItem().toString(), cmbAntibiotik.getSelectedItem().toString(), Tantibiotik.getText(),
                                cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(), Valid.mysql_real_escape_stringERM(Tpemeriksaan.getText()),
                                cmbEkg.getSelectedItem().toString(), TintepretasiEkg.getText(), cmbIntepretasiRo.getSelectedItem().toString(), cmbPersiapanDarah.getSelectedItem().toString(),
                                cmbPersiapanPuasa.getSelectedItem().toString(), Valid.SetTgl(Ttglceklis.getSelectedItem() + ""), TnipBangsal.getText(), TnipIbs.getText(),
                                tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 0).toString()
                            }) == true) {

                        TCari.setText(TNoRw.getText());
                        tampil();
                        emptTeks();
                    }
                } catch (Exception e) {
                    System.out.println("Simpan Checklist Pra Operasi : " + e);
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan klik/pilih dulu salah satu datanya pada tabel..!!");
                tbCeklis.requestFocus();
            }
        }
}//GEN-LAST:event_BtnGantiActionPerformed

    private void BtnGantiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnGantiKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnGantiActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnBatal, BtnKeluar);
        }
}//GEN-LAST:event_BtnGantiKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnBatal,TCari);}
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

    private void tbCeklisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCeklisMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbCeklisMouseClicked

    private void tbCeklisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbCeklisKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbCeklisKeyPressed

    private void BtnOperatorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnOperatorActionPerformed
        pilihDokter = 0;
        pilihDokter = 1;
        akses.setform("RMCeklisPraOperasi");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnOperatorActionPerformed

    private void BtnAnastesiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAnastesiActionPerformed
        pilihDokter = 0;
        pilihDokter = 2;
        akses.setform("RMCeklisPraOperasi");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnAnastesiActionPerformed

    private void TsuhuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TsuhuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tnadi.requestFocus();
        }
    }//GEN-LAST:event_TsuhuKeyPressed

    private void TnadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnadiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Trespi.requestFocus();
        }
    }//GEN-LAST:event_TnadiKeyPressed

    private void TrespiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TrespiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbInfus.requestFocus();
        }
    }//GEN-LAST:event_TrespiKeyPressed

    private void cmbJamMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJamMouseReleased
        AutoCompleteDecorator.decorate(cmbJam);
    }//GEN-LAST:event_cmbJamMouseReleased

    private void cmbMntMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMntMouseReleased
        AutoCompleteDecorator.decorate(cmbMnt);
    }//GEN-LAST:event_cmbMntMouseReleased

    private void cmbDtkMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtkMouseReleased
        AutoCompleteDecorator.decorate(cmbDtk);
    }//GEN-LAST:event_cmbDtkMouseReleased

    private void TpemeriksaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpemeriksaanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            cmbEkg.requestFocus();
        }
    }//GEN-LAST:event_TpemeriksaanKeyPressed

    private void BtnBangsalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBangsalActionPerformed
        pilihPerawat = 0;
        pilihPerawat = 1;
        akses.setform("RMCeklisPraOperasi");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnBangsalActionPerformed

    private void BtnIbsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnIbsActionPerformed
        pilihPerawat = 0;
        pilihPerawat = 2;
        akses.setform("RMCeklisPraOperasi");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnIbsActionPerformed

    private void TdiagnosaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdiagnosaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Trencana.requestFocus();
        }
    }//GEN-LAST:event_TdiagnosaKeyPressed

    private void TrencanaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TrencanaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnOperator.requestFocus();
        }
    }//GEN-LAST:event_TrencanaKeyPressed

    private void TkesadaranKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkesadaranKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Ttensi.requestFocus();
        }
    }//GEN-LAST:event_TkesadaranKeyPressed

    private void TtensiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtensiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tsuhu.requestFocus();
        }
    }//GEN-LAST:event_TtensiKeyPressed

    private void cmbInfusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbInfusActionPerformed
        Tinfus.setText("");
        if (cmbInfus.getSelectedIndex() == 1) {
            Tinfus.setEnabled(true);
            Tinfus.requestFocus();
        } else {
            Tinfus.setEnabled(false);
        }
    }//GEN-LAST:event_cmbInfusActionPerformed

    private void TinfusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TinfusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbKateter.requestFocus();
        }
    }//GEN-LAST:event_TinfusKeyPressed

    private void cmbKateterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKateterActionPerformed
        Tkateter.setText("");
        if (cmbKateter.getSelectedIndex() == 1) {
            Tkateter.setEnabled(true);
            Tkateter.requestFocus();
        } else {
            Tkateter.setEnabled(false);
        }
    }//GEN-LAST:event_cmbKateterActionPerformed

    private void TkateterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkateterKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbCukur.requestFocus();
        }
    }//GEN-LAST:event_TkateterKeyPressed

    private void cmbCukurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCukurActionPerformed
        Tcukur.setText("");
        if (cmbCukur.getSelectedIndex() == 1) {
            Tcukur.setEnabled(true);
            Tcukur.requestFocus();
        } else {
            Tcukur.setEnabled(false);
        }
    }//GEN-LAST:event_cmbCukurActionPerformed

    private void TcukurKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TcukurKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbLavemen.requestFocus();
        }
    }//GEN-LAST:event_TcukurKeyPressed

    private void cmbLavemenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbLavemenActionPerformed
        Tlavemen.setText("");
        if (cmbLavemen.getSelectedIndex() == 1) {
            Tlavemen.setEnabled(true);
            Tlavemen.requestFocus();
        } else {
            Tlavemen.setEnabled(false);
        }
    }//GEN-LAST:event_cmbLavemenActionPerformed

    private void TlavemenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlavemenKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbGigi.requestFocus();
        }
    }//GEN-LAST:event_TlavemenKeyPressed

    private void cmbAntibiotikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAntibiotikActionPerformed
        Tantibiotik.setText("");
        if (cmbAntibiotik.getSelectedIndex() == 1) {
            Tantibiotik.setEnabled(true);
            Tantibiotik.requestFocus();
        } else {
            Tantibiotik.setEnabled(false);
        }
    }//GEN-LAST:event_cmbAntibiotikActionPerformed

    private void TantibiotikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TantibiotikKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbJam.requestFocus();
        }
    }//GEN-LAST:event_TantibiotikKeyPressed

    private void TintepretasiEkgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TintepretasiEkgKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbIntepretasiRo.requestFocus();
        }
    }//GEN-LAST:event_TintepretasiEkgKeyPressed

    private void chkSaya1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSaya1ActionPerformed
        if (chkSaya1.isSelected() == true) {
            if (akses.getadmin() == true) {
                TnipBangsal.setText("-");
                TnmPerawatBangsal.setText("-");
            } else {
                TnipBangsal.setText(akses.getkode());
                TnmPerawatBangsal.setText(Sequel.cariIsi("select nama from pegawai where nik='" + TnipBangsal.getText() + "'"));
            }
        } else {
            TnipBangsal.setText("-");
            TnmPerawatBangsal.setText("-");
        }
    }//GEN-LAST:event_chkSaya1ActionPerformed

    private void chkSaya2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSaya2ActionPerformed
        if (chkSaya2.isSelected() == true) {
            if (akses.getadmin() == true) {
                TnipIbs.setText("-");
                TnmPerawatIbs.setText("-");
            } else {
                TnipIbs.setText(akses.getkode());
                TnmPerawatIbs.setText(Sequel.cariIsi("select nama from pegawai where nik='" + TnipIbs.getText() + "'"));
            }
        } else {
            TnipIbs.setText("-");
            TnmPerawatIbs.setText("-");
        }
    }//GEN-LAST:event_chkSaya2ActionPerformed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbCeklis.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.queryu2tf("delete from ceklis_pra_operasi where waktu_simpan=?", 1, new String[]{
                    tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 0).toString()
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
            tbCeklis.requestFocus();
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
//        skorFix = "";
//
//        if (tbAsesmen.getSelectedRow() > -1) {
//            Map<String, Object> param = new HashMap<>();
//            param.put("namars", akses.getnamars());
//            param.put("logo", Sequel.cariGambar("select logo from setting"));
//            param.put("norm", TNoRM.getText());
//            param.put("nmpasien", TPasien.getText());
//            param.put("tgllahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d-%m-%Y') from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
//            param.put("ruangan", Truangan.getText() + ", Tanggal : " + TtglMsk.getSelectedItem().toString() + ", Pukul : " + cmbJam.getSelectedItem().toString() + ":" + cmbMnt.getSelectedItem().toString() + " WITA");
//
//            if (cmbTiba.getSelectedIndex() == 4) {
//                param.put("tibaDiruang", cmbTiba.getSelectedItem().toString() + " (" + TtibaLain.getText() + ")");
//            } else {
//                param.put("tibaDiruang", cmbTiba.getSelectedItem().toString());
//            }
//
//            param.put("mskMelalui", cmbMasuk.getSelectedItem().toString());
//            param.put("keluhan", Tkeluhan.getText());
//            param.put("riwayatAlergi", cmbRiwAlergi.getSelectedItem().toString());
//
//            if (alergiObat.equals("ya")) {
//                param.put("cekObat", "V");
//                param.put("alergiObat", TnmAlergiObat.getText() + ", Reaksi : " + TreaksiObat.getText());
//            } else {
//                param.put("cekObat", "");
//                param.put("alergiObat", " - , Reaksi : -");
//            }
//
//            if (alergiMakanan.equals("ya")) {
//                param.put("cekMakan", "V");
//                param.put("alergiMakan", TnmAlergiMakanan.getText() + ", Reaksi : " + TreaksiMakanan.getText());
//            } else {
//                param.put("cekMakan", "");
//                param.put("alergiMakan", " - , Reaksi : -");
//            }
//
//            if (alergiLain.equals("ya")) {
//                param.put("cekLain", "V");
//                param.put("alergiLain", TnmAlergiLain.getText() + ", Reaksi : " + TreaksiLain.getText());
//            } else {
//                param.put("cekLain", "");
//                param.put("alergiLain", " - , Reaksi : -");
//            }
//
//            if (gelang.equals("ya")) {
//                param.put("gelang", "V");
//            } else {
//                param.put("gelang", "");
//            }
//
//            param.put("alergiDiberitau", cmbAlergiDiberitahu.getSelectedItem().toString());
//            param.put("riwayatPS", TriwPenyktSkg.getText());
//            param.put("kesadaran", Tkesadaran.getText());
//            param.put("gcs", Tgcse.getText() + " M : " + Tgcsm.getText() + " V : " + Tgcsv.getText());
//            param.put("tensi", Ttensi.getText() + " mmHg");
//            param.put("temp", Ttemp.getText() + " °C");
//            param.put("hr", Thr.getText() + " x/mnt");
//            param.put("rr", Trr.getText() + " x/mnt");
//            param.put("BBbelum", TbbBelum.getText() + " Kg");
//            param.put("BBmasuk", TbbMasuk.getText() + " Kg");
//            param.put("tb", Ttb.getText() + " Cm");
//            param.put("imt", Timt.getText() + " Kg");
//            param.put("crt", Tcrt.getText() + " detik");
//            param.put("spo", Tspo.getText() + " %");
//
//            if (cmbPernafasan.getSelectedIndex() == 4) {
//                param.put("pernafasan", cmbPernafasan.getSelectedItem().toString() + " (" + Tpernafasan.getText() + ")");
//            } else {
//                param.put("pernafasan", cmbPernafasan.getSelectedItem().toString());
//            }
//
//            if (cmbPenglihatan.getSelectedIndex() == 4) {
//                param.put("penglihatan", cmbPenglihatan.getSelectedItem().toString() + " (" + Tpenglihatan.getText() + ")");
//            } else {
//                param.put("penglihatan", cmbPenglihatan.getSelectedItem().toString());
//            }
//
//            if (cmbPendengaran.getSelectedIndex() == 4) {
//                param.put("pendengaran", cmbPendengaran.getSelectedItem().toString() + " (" + Tpendengaran.getText() + ")");
//            } else {
//                param.put("pendengaran", cmbPendengaran.getSelectedItem().toString());
//            }
//
//            if (cmbMulut.getSelectedIndex() == 3) {
//                param.put("mulut", cmbMulut.getSelectedItem().toString() + " (" + Tmulut.getText() + ")");
//            } else {
//                param.put("mulut", cmbMulut.getSelectedItem().toString());
//            }
//
//            if (cmbReflek.getSelectedIndex() == 4) {
//                param.put("reflek", cmbReflek.getSelectedItem().toString() + " (" + Treflek.getText() + ")");
//            } else {
//                param.put("reflek", cmbReflek.getSelectedItem().toString());
//            }
//
//            if (cmbBicara.getSelectedIndex() == 3) {
//                param.put("bicara", cmbBicara.getSelectedItem().toString() + " (" + Tbicara.getText() + ")");
//            } else {
//                param.put("bicara", cmbBicara.getSelectedItem().toString());
//            }
//
//            if (cmbDefekasi.getSelectedIndex() == 5) {
//                param.put("defekasi", cmbDefekasi.getSelectedItem().toString() + " (" + Tdefekasi.getText() + ")");
//            } else {
//                param.put("defekasi", cmbDefekasi.getSelectedItem().toString());
//            }
//
//            if (cmbMiksi.getSelectedIndex() == 4) {
//                param.put("miksi", cmbMiksi.getSelectedItem().toString() + " (" + Tmiksi.getText() + ")");
//            } else {
//                param.put("miksi", cmbMiksi.getSelectedItem().toString());
//            }
//
//            if (cmbGastro.getSelectedIndex() == 5) {
//                param.put("gastro", cmbGastro.getSelectedItem().toString() + " (" + Tgastro.getText() + ")");
//            } else {
//                param.put("gastro", cmbGastro.getSelectedItem().toString());
//            }
//
//            if (cmbPola.getSelectedIndex() == 3) {
//                param.put("pola", cmbPola.getSelectedItem().toString() + " (" + Tpola.getText() + ")");
//            } else {
//                param.put("pola", cmbPola.getSelectedItem().toString());
//            }
//
//            param.put("makan", cmbMakan.getSelectedItem().toString());
//            param.put("berpakaian", cmbBerpakaian.getSelectedItem().toString());
//            param.put("buang", cmbBuang.getSelectedItem().toString());
//            param.put("mandi", cmbMandi.getSelectedItem().toString());
//            param.put("berpindah", cmbBerpindah.getSelectedItem().toString());
//            param.put("kesimpulan", Tkesimpulan.getText());
//            param.put("sttsNikah", TsttsNikah.getText());
//            param.put("keluarga", TKlgDekat.getText());
//            param.put("hubungan", Thubungan.getText());
//            param.put("notelp", TnoTelp.getText());
//
//            if (cmbTinggal.getSelectedIndex() == 7) {
//                param.put("tinggalDengan", cmbTinggal.getSelectedItem().toString() + " (" + TtglDenganLain.getText() + ")");
//            } else {
//                param.put("tinggalDengan", cmbTinggal.getSelectedItem().toString());
//            }
//
//            param.put("curiga", cmbCuriga.getSelectedItem().toString());
//
//            if (ibadah.equals("ya")) {
//                param.put("ibadah", "V");
//            } else {
//                param.put("ibadah", "");
//            }
//
//            param.put("emosi", cmbStatus.getSelectedItem().toString());
//
//            if (cmbGizi1.getSelectedIndex() == 2) {
//                skorFix = "\n" + cmbYaGizi1.getSelectedItem().toString() + "   Skor (" + skorYaGizi1.getText() + ")\n\n";
//            } else {
//                skorFix = "   Skor (" + skorGizi1.getText() + ")\n\n";
//            }
//            param.put("kalimatSkrining", "1. Apakah pasien mengalami penurunan BB yang tidak direncanakan/tidak diinginkan dalam 6 bulan terakhir ?\n"
//                + cmbGizi1.getSelectedItem().toString() + "" + skorFix + ""
//                + "2. Apakah asupan makan pasien berkurang karena penurunan nafsu makan / kesulitan menerima makanan ?\n"
//                + cmbGizi2.getSelectedItem().toString() + "   Skor (" + skorGizi2.getText() + ")\n"
//                + "_______________________________________________________________________\n"
//                + "Total Skor : " + TotSkorGizi.getText() + ", Kesimpulan : " + kesimpulanGizi.getText() + "\n");
//            param.put("skala", cmbSkala.getSelectedItem().toString());
//            param.put("onset", Tonset.getText());
//
//            if (cmbProvo.getSelectedIndex() == 5) {
//                param.put("provo", cmbProvo.getSelectedItem().toString() + " (" + Tprovo.getText() + ")");
//            } else {
//                param.put("provo", cmbProvo.getSelectedItem().toString());
//            }
//
//            if (cmbQuality.getSelectedIndex() == 9) {
//                param.put("quality", cmbQuality.getSelectedItem().toString() + " (" + Tquality.getText() + ")");
//            } else {
//                param.put("quality", cmbQuality.getSelectedItem().toString());
//            }
//
//            param.put("radiation", cmbRadia.getSelectedItem().toString());
//            param.put("sever", cmbSever.getSelectedItem().toString());
//            param.put("time", cmbTime.getSelectedItem().toString() + ", Lama : " + cmbLama.getSelectedItem().toString());
//
//            if (cmbRelief.getSelectedIndex() == 4) {
//                param.put("relief", cmbRelief.getSelectedItem().toString() + " (" + Trelief.getText() + ")");
//            } else {
//                param.put("relief", cmbRelief.getSelectedItem().toString());
//            }
//
//            if (cmbAsso.getSelectedIndex() == 6) {
//                param.put("asso", cmbAsso.getSelectedItem().toString() + " (" + Tasso.getText() + ")");
//            } else {
//                param.put("asso", cmbAsso.getSelectedItem().toString());
//            }
//
//            //faktor resiko jatuh
//            try {
//                resikojatuh = "";
//                ps3 = koneksi.prepareStatement("select m.kode_resiko, concat('Faktor Resiko : ',m.faktor_resiko,', Skala : ',m.skala,', Skor (',m.skor,')') resiko "
//                    + "FROM master_faktor_resiko_igd m INNER JOIN penilaian_awal_keperawatan_dewasa_ranap_resiko pm ON pm.kode_resiko = m.kode_resiko "
//                    + "WHERE m.asesmen = 'Dewasa Ranap' and pm.no_rawat=? ORDER BY convert(pm.kode_resiko, int) desc");
//                try {
//                    ps3.setString(1, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString());
//                    rs3 = ps3.executeQuery();
//                    while (rs3.next()) {
//                        resikojatuh = rs3.getString("resiko") + "\n" + resikojatuh;
//                    }
//
//                    if (resikojatuh.endsWith("\n")) {
//                        resikojatuh = resikojatuh.substring(0, resikojatuh.length() - 1);
//                    }
//
//                } catch (Exception e) {
//                    System.out.println("Notif : " + e);
//                } finally {
//                    if (rs3 != null) {
//                        rs3.close();
//                    }
//                    if (ps3 != null) {
//                        ps3.close();
//                    }
//                }
//            } catch (Exception e) {
//                System.out.println("Notif : " + e);
//            }
//
//            param.put("resikoJatuh", resikojatuh);
//            param.put("TotSkorResikoJatuh", "Total Skor : " + TotSkorRJ.getText());
//            param.put("KesResikoJatuh", "Kesimpulan Skor Resiko Jatuh : " + kesimpulanResikoJatuh.getText());
//
//            if (cmbTindakanCegah.getSelectedIndex() == 0) {
//                param.put("JudultindakanRJ", "Tindakan Pencegahan Resiko Jatuh");
//                param.put("IsitindakanRJ", "-");
//            } else if (cmbTindakanCegah.getSelectedIndex() == 1) {
//                param.put("JudultindakanRJ", "Pencegahan Umum (A)");
//                param.put("IsitindakanRJ", cegahA.getText());
//            } else if (cmbTindakanCegah.getSelectedIndex() == 2) {
//                param.put("JudultindakanRJ", "Pencegahan Resiko Sedang (B)");
//                param.put("IsitindakanRJ", cegahB.getText());
//            } else if (cmbTindakanCegah.getSelectedIndex() == 3) {
//                param.put("JudultindakanRJ", "Pencegahan Resiko Tinggi (C)");
//                param.put("IsitindakanRJ", cegahC.getText());
//            }
//
//            //resiko decbitus
//            try {
//                resikodecubitus = "";
//                ps4 = koneksi.prepareStatement("select m.kode_decubitus, concat(m.parameter,' : ',m.penilaian,', Skor (',m.skor,')') decubitus "
//                    + "FROM master_data_decubitus m INNER JOIN penilaian_awal_keperawatan_dewasa_ranap_decubitus pa ON pa.kode_decubitus = m.kode_decubitus "
//                    + "WHERE pa.no_rawat=? ORDER BY pa.kode_decubitus");
//                try {
//                    ps4.setString(1, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString());
//                    rs4 = ps4.executeQuery();
//                    while (rs4.next()) {
//                        resikodecubitus = rs4.getString("decubitus") + "\n" + resikodecubitus;
//                    }
//
//                    if (resikodecubitus.endsWith("\n")) {
//                        resikodecubitus = resikodecubitus.substring(0, resikodecubitus.length() - 1);
//                    }
//
//                } catch (Exception e) {
//                    System.out.println("Notif : " + e);
//                } finally {
//                    if (rs4 != null) {
//                        rs4.close();
//                    }
//                    if (ps4 != null) {
//                        ps4.close();
//                    }
//                }
//            } catch (Exception e) {
//                System.out.println("Notif : " + e);
//            }
//
//            param.put("resikoDecu", resikodecubitus);
//            param.put("TotSkorResikoDecu", "Total Skor : " + TotSkorDecu.getText());
//            param.put("KesResikoDecu", "Kesimpulan Skor Resiko Decubitus : " + kesimpulanResikoDecu.getText());
//            param.put("nilai", "(" + cmbNilai.getSelectedItem().toString() + ") " + Tketerangan.getText());
//
//            if (obatObatan.equals("ya")) {
//                param.put("obat", "V");
//            } else {
//                param.put("obat", "");
//            }
//
//            if (perawatanLuka.equals("ya")) {
//                param.put("rawatluka", "V");
//            } else {
//                param.put("rawatluka", "");
//            }
//
//            param.put("manajemenLain", TmanajemenLain.getText());
//
//            if (manajemenNyeri.equals("ya")) {
//                param.put("manajNyeri", "V");
//            } else {
//                param.put("manajNyeri", "");
//            }
//
//            if (diet.equals("ya")) {
//                param.put("diet", "V");
//            } else {
//                param.put("diet", "");
//            }
//
//            if (fisio.equals("ya")) {
//                param.put("fisio", "V");
//            } else {
//                param.put("fisio", "");
//            }
//
//            param.put("rehabLain", TrehabLain.getText());
//
//            if (hipertermi.equals("ya")) {
//                param.put("hipertermi", "V");
//            } else {
//                param.put("hipertermi", "");
//            }
//
//            if (nyeri.equals("ya")) {
//                param.put("nyeri", "V");
//            } else {
//                param.put("nyeri", "");
//            }
//
//            if (resiko.equals("ya")) {
//                param.put("resiko", "V");
//            } else {
//                param.put("resiko", "");
//            }
//
//            if (kelebihan.equals("ya")) {
//                param.put("kelebihan", "V");
//            } else {
//                param.put("kelebihan", "");
//            }
//
//            if (bersihkan.equals("ya")) {
//                param.put("bersihkan", "V");
//            } else {
//                param.put("bersihkan", "");
//            }
//
//            if (pola.equals("ya")) {
//                param.put("polaNafas", "V");
//            } else {
//                param.put("polaNafas", "");
//            }
//
//            if (gangguan.equals("ya")) {
//                param.put("gangguan", "V");
//            } else {
//                param.put("gangguan", "");
//            }
//
//            if (cemas.equals("ya")) {
//                param.put("cemas", "V");
//            } else {
//                param.put("cemas", "");
//            }
//
//            if (ketidakseimbangan.equals("ya")) {
//                param.put("ketidakseimbangan", "V");
//            } else {
//                param.put("ketidakseimbangan", "");
//            }
//
//            if (perubahan.equals("ya")) {
//                param.put("perubahan", "V");
//            } else {
//                param.put("perubahan", "");
//            }
//
//            if (penurunan.equals("ya")) {
//                param.put("penurunan", "V");
//            } else {
//                param.put("penurunan", "");
//            }
//
//            if (kerusakan.equals("ya")) {
//                param.put("kerusakan", "V");
//            } else {
//                param.put("kerusakan", "");
//            }
//
//            if (intoleransi.equals("ya")) {
//                param.put("intol", "V");
//            } else {
//                param.put("intol", "");
//            }
//
//            if (kurang.equals("ya")) {
//                param.put("kurang", "V");
//            } else {
//                param.put("kurang", "");
//            }
//
//            if (perluMPP.equals("ya")) {
//                param.put("mpp", "V");
//            } else {
//                param.put("mpp", "");
//            }
//
//            if (perluDP.equals("ya")) {
//                param.put("dp", "V");
//            } else {
//                param.put("dp", "");
//            }
//
//            param.put("masalahLain", TmasalahLain.getText());
//            param.put("tglAsesmen", TtglAses.getSelectedItem().toString());
//            param.put("jamAsesmen", cmbJam1.getSelectedItem().toString() + ":" + cmbMnt1.getSelectedItem().toString() + " WITA");
//            param.put("perawat", "(" + TnmPerawat.getText() + ")");
//
//            Valid.MyReport("rptAsesmenKeperawatanDewasa1.jasper", "report", "::[ Asesmen Keperawatan Dewasa Rawat Inap Hal. 1 ]::",
//                "SELECT now() tanggal", param);
//            Valid.MyReport("rptAsesmenKeperawatanDewasa2.jasper", "report", "::[ Asesmen Keperawatan Dewasa Rawat Inap Hal. 2 ]::",
//                "SELECT now() tanggal", param);
//
//            TabRawat.setSelectedIndex(1);
//            tampilFaktorResiko();
//            tampilResikoDecubitus();
//            tampil();
//            emptTeks();
//        } else {
//            JOptionPane.showMessageDialog(null, "Maaf, silahkan klik/pilih datanya pada tabel terlebih dahulu..!!!!");
//        }
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnGanti, BtnKeluar);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void MnHasilPemeriksaanPenunjangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHasilPemeriksaanPenunjangActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            akses.setform("RMCeklisPraOperasi");
            DlgHasilPenunjangMedis form = new DlgHasilPenunjangMedis(null, false);
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setData(TNoRw.getText(), TPasien.getText(), TNoRM.getText());
            form.setVisible(true);
        }
    }//GEN-LAST:event_MnHasilPemeriksaanPenunjangActionPerformed

    private void BtnPasteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPasteActionPerformed
        if (akses.getPasteData().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan copy dulu data yg. dipilih..!!!!");
        } else {
            if (Tpemeriksaan.getText().equals("")) {
                Tpemeriksaan.setText(akses.getPasteData());
                akses.setCopyData("");
            } else {
                Tpemeriksaan.setText(Tpemeriksaan.getText() + "\n\n" + akses.getPasteData());
                akses.setCopyData("");
            }
        }
    }//GEN-LAST:event_BtnPasteActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMCeklisPraOperasi dialog = new RMCeklisPraOperasi(new javax.swing.JFrame(), true);
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
    private widget.Button BtnAnastesi;
    private widget.Button BtnBangsal;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnGanti;
    private widget.Button BtnHapus;
    private widget.Button BtnIbs;
    private widget.Button BtnKeluar;
    private widget.Button BtnOperator;
    private widget.Button BtnPaste;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnHasilPemeriksaanPenunjang;
    private javax.swing.JPanel PanelInput1;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    public widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox Tantibiotik;
    private widget.TextBox Tcukur;
    private widget.TextBox Tdiagnosa;
    private widget.TextBox Tinfus;
    private widget.TextBox TintepretasiEkg;
    private widget.TextBox Tkateter;
    private widget.TextBox Tkesadaran;
    private widget.TextBox Tlavemen;
    private widget.TextBox Tnadi;
    private widget.TextBox TnipAnastesi;
    private widget.TextBox TnipBangsal;
    private widget.TextBox TnipIbs;
    private widget.TextBox TnipOperator;
    private widget.TextBox TnmAnastesi;
    private widget.TextBox TnmOperator;
    private widget.TextBox TnmPerawatBangsal;
    private widget.TextBox TnmPerawatIbs;
    private widget.TextArea Tpemeriksaan;
    private widget.TextBox Trencana;
    private widget.TextBox Trespi;
    private widget.TextBox TrgRawat;
    private widget.TextBox Tsuhu;
    private widget.TextBox Ttensi;
    private widget.Tanggal Ttglceklis;
    private widget.CekBox chkSaya1;
    private widget.CekBox chkSaya2;
    private widget.ComboBox cmbAntibiotik;
    private widget.ComboBox cmbBaju;
    private widget.ComboBox cmbCukur;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbEkg;
    private widget.ComboBox cmbGigi;
    private widget.ComboBox cmbInfus;
    private widget.ComboBox cmbIntepretasiRo;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbKateter;
    private widget.ComboBox cmbLavemen;
    private widget.ComboBox cmbMnt;
    private widget.ComboBox cmbPenandaan;
    private widget.ComboBox cmbPersiapanDarah;
    private widget.ComboBox cmbPersiapanPuasa;
    private widget.ComboBox cmbSuperAnastesi;
    private widget.ComboBox cmbSuperTindakan;
    private widget.ComboBox cmbSuperTransfusi;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel10;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel23;
    private widget.Label jLabel6;
    private widget.Label jLabel63;
    private widget.Label jLabel64;
    private widget.Label jLabel65;
    private widget.Label jLabel66;
    private widget.Label jLabel67;
    private widget.Label jLabel68;
    private widget.Label jLabel69;
    private widget.Label jLabel7;
    private widget.Label jLabel70;
    private widget.Label jLabel71;
    private widget.Label jLabel72;
    private widget.Label jLabel73;
    private widget.Label jLabel74;
    private widget.Label jLabel75;
    private widget.Label jLabel76;
    private widget.Label jLabel77;
    private widget.Label jLabel78;
    private widget.Label jLabel79;
    private widget.Label jLabel80;
    private widget.Label jLabel81;
    private widget.Label jLabel82;
    private widget.Label jLabel83;
    private widget.Label jLabel84;
    private widget.Label jLabel85;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.Label label104;
    private widget.Label label106;
    private widget.Label label15;
    private widget.Label label16;
    private widget.Label label17;
    private widget.Label label18;
    private widget.Label label19;
    private widget.Label label20;
    private widget.Label label21;
    private widget.Label label43;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass12;
    private widget.panelisi panelGlass8;
    private widget.ScrollPane scrollPane13;
    private widget.Table tbCeklis;
    // End of variables declaration//GEN-END:variables

    public void tampil() {     
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT cp.*, p.no_rkm_medis, p.nm_pasien, DATE_FORMAT(p.tgl_lahir,'%d/%m/%Y') tglLahir, pg1.nama nmOperator, "
                    + "pg2.nama nmAnastesi, DATE_FORMAT(cp.tgl_ceklis,'%d/%m/%Y') tglCeklis, pg3.nama nmPerawatBangsal, pg4.nama nmPerawatIbs from ceklis_pra_operasi cp "
                    + "inner join reg_periksa rp on rp.no_rawat=cp.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "inner join pegawai pg1 on pg1.nik=cp.nip_operator inner join pegawai pg2 on pg2.nik=cp.nip_anastesi "
                    + "inner join pegawai pg3 on pg3.nik=cp.nip_perawat_bangsal inner join pegawai pg4 on pg4.nik=cp.nip_perawat_ibs WHERE "
                    + "cp.tgl_ceklis between ? and ? and cp.no_rawat LIKE ? or "
                    + "cp.tgl_ceklis between ? and ? and p.no_rkm_medis LIKE ? or "
                    + "cp.tgl_ceklis between ? and ? and p.nm_pasien LIKE ? or "
                    + "cp.tgl_ceklis between ? and ? and pg1.nama LIKE ? or "
                    + "cp.tgl_ceklis between ? and ? and pg2.nama LIKE ? or "
                    + "cp.tgl_ceklis between ? and ? and pg3.nama LIKE ? or "
                    + "cp.tgl_ceklis between ? and ? and pg4.nama LIKE ? or "
                    + "cp.tgl_ceklis between ? and ? and cp.diagnosa_pra_tindakan LIKE ? or "
                    + "cp.tgl_ceklis between ? and ? and cp.rencana_tindakan LIKE ? ORDER BY cp.tgl_ceklis desc");
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
                ps.setString(25, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(26, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(27, "%" + TCari.getText() + "%");
                rs = ps.executeQuery();                
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString("waktu_simpan"),
                        rs.getString("no_rawat"),                        
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("tglLahir"),
                        rs.getString("ruang_rawat"),
                        rs.getString("diagnosa_pra_tindakan"),
                        rs.getString("rencana_tindakan"),
                        rs.getString("nmOperator"),
                        rs.getString("nmAnastesi"),
                        rs.getString("kesadaran"),
                        rs.getString("td"),
                        rs.getString("suhu"),
                        rs.getString("nadi"),
                        rs.getString("respi"),
                        rs.getString("persiapan_darah"),
                        rs.getString("persiapan_puasa"),
                        rs.getString("tglCeklis"),
                        rs.getString("nmPerawatBangsal"),
                        rs.getString("nmPerawatIbs"),
                        rs.getString("no_rawat"),
                        rs.getString("ruang_rawat"),
                        rs.getString("diagnosa_pra_tindakan"),
                        rs.getString("rencana_tindakan"),
                        rs.getString("nip_operator"),
                        rs.getString("nip_anastesi"),
                        rs.getString("kesadaran"),
                        rs.getString("td"),
                        rs.getString("suhu"),
                        rs.getString("nadi"),
                        rs.getString("respi"),
                        rs.getString("pasang_infus"),
                        rs.getString("ket_infus"),
                        rs.getString("pasang_kateter"),
                        rs.getString("ket_kateter"),
                        rs.getString("cukur_daerah_operasi"),
                        rs.getString("ket_cukur"),
                        rs.getString("lavemen"),
                        rs.getString("ket_lavemen"),
                        rs.getString("gigi_palsu"),
                        rs.getString("baju"),
                        rs.getString("penandaan"),
                        rs.getString("super_anastesi"),
                        rs.getString("super_tindakan"),
                        rs.getString("super_transfusi"),
                        rs.getString("antibiotik_profilaksi"),
                        rs.getString("ket_antibiotik"),
                        rs.getString("jam_antibiotik"),
                        rs.getString("pemeriksaan_penunjang"),
                        rs.getString("ekg"),
                        rs.getString("interpretasi_ekg"),
                        rs.getString("interpretasi_ro"),
                        rs.getString("persiapan_darah"),
                        rs.getString("persiapan_puasa"),
                        rs.getString("tgl_ceklis"),
                        rs.getString("nip_perawat_bangsal"),
                        rs.getString("nip_perawat_ibs")
                    });
                }                
            } catch (Exception e) {
                System.out.println("rekammedis.RMCeklisPraOperasi.tampil() : " + e);
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
        Tdiagnosa.setText("");
        Tdiagnosa.requestFocus();
        Trencana.setText("");
        TnipOperator.setText("-");
        TnmOperator.setText("-");
        TnipAnastesi.setText("-");
        TnmAnastesi.setText("-");
        Tkesadaran.setText("");
        Ttensi.setText("");
        Tsuhu.setText("");
        Tnadi.setText("");
        Trespi.setText("");
        cmbInfus.setSelectedIndex(0);
        Tinfus.setText("");
        Tinfus.setEnabled(false);
        cmbKateter.setSelectedIndex(0);
        Tkateter.setText("");
        Tkateter.setEnabled(false);
        cmbCukur.setSelectedIndex(0);
        Tcukur.setText("");
        Tcukur.setEnabled(false);
        cmbLavemen.setSelectedIndex(0);
        Tlavemen.setText("");
        Tlavemen.setEnabled(false);
        cmbGigi.setSelectedIndex(0);
        cmbBaju.setSelectedIndex(0);
        cmbPenandaan.setSelectedIndex(0);
        cmbSuperAnastesi.setSelectedIndex(0);
        cmbSuperTindakan.setSelectedIndex(0);
        cmbSuperTransfusi.setSelectedIndex(0);
        cmbAntibiotik.setSelectedIndex(0);
        Tantibiotik.setText("");
        Tantibiotik.setEnabled(false);
        cmbJam.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk.setSelectedIndex(0);
        Tpemeriksaan.setText("");
        cmbEkg.setSelectedIndex(0);
        cmbIntepretasiRo.setSelectedIndex(0);
        TintepretasiEkg.setText("");
        cmbPersiapanDarah.setSelectedIndex(0);
        cmbPersiapanPuasa.setSelectedIndex(0);
        Ttglceklis.setDate(new Date());
        TnipBangsal.setText("-");
        TnmPerawatBangsal.setText("-");
        TnipIbs.setText("-");
        TnmPerawatIbs.setText("-");
        chkSaya1.setSelected(false);
        chkSaya2.setSelected(false);
    }

    private void getData() {
        if (tbCeklis.getSelectedRow() != -1) {
            TNoRw.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 1).toString());
            TNoRM.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 2).toString());
            TPasien.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 3).toString());
            TrgRawat.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 21).toString());
            Tdiagnosa.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 6).toString());
            Trencana.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 7).toString());
            TnipOperator.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 24).toString());
            TnmOperator.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 8).toString());
            TnipAnastesi.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 25).toString());
            TnmAnastesi.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 9).toString());
            Tkesadaran.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 10).toString());
            Ttensi.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 11).toString());
            Tsuhu.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 12).toString());
            Tnadi.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 13).toString());
            Trespi.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 14).toString());
            cmbInfus.setSelectedItem(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 31).toString());
            Tinfus.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 32).toString());            
            cmbKateter.setSelectedItem(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 33).toString());
            Tkateter.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 34).toString());            
            cmbCukur.setSelectedItem(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 35).toString());
            Tcukur.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 36).toString());            
            cmbLavemen.setSelectedItem(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 37).toString());
            Tlavemen.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 38).toString());
            cmbGigi.setSelectedItem(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 39).toString());
            cmbBaju.setSelectedItem(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 40).toString());            
            cmbPenandaan.setSelectedItem(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 41).toString());
            cmbSuperAnastesi.setSelectedItem(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 42).toString());
            cmbSuperTindakan.setSelectedItem(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 43).toString());
            cmbSuperTransfusi.setSelectedItem(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 44).toString());
            cmbAntibiotik.setSelectedItem(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 45).toString());
            Tantibiotik.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 46).toString());
            cmbJam.setSelectedItem(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 47).toString().substring(0, 2));
            cmbMnt.setSelectedItem(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 47).toString().substring(3, 5));
            cmbDtk.setSelectedItem(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 47).toString().substring(6, 8));
            Tpemeriksaan.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 48).toString());
            cmbEkg.setSelectedItem(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 49).toString());
            TintepretasiEkg.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 50).toString());
            cmbIntepretasiRo.setSelectedItem(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 51).toString());
            cmbPersiapanDarah.setSelectedItem(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 52).toString());
            cmbPersiapanPuasa.setSelectedItem(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 53).toString());
            Valid.SetTgl(Ttglceklis, tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 54).toString());
            TnipBangsal.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 55).toString());
            TnmPerawatBangsal.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 18).toString());
            TnipIbs.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 56).toString());
            TnmPerawatIbs.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 19).toString());
            dataCek();
        }
    }
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.getkegiatan_operasi());
        BtnGanti.setEnabled(akses.getkegiatan_operasi());
        BtnHapus.setEnabled(akses.getkegiatan_operasi());
    }
    
    private void dataCek() {
        if (cmbInfus.getSelectedIndex() == 1) {
            Tinfus.setEnabled(true);
        } else {
            Tinfus.setEnabled(false);
        }
        
        if (cmbKateter.getSelectedIndex() == 1) {
            Tkateter.setEnabled(true);
        } else {
            Tkateter.setEnabled(false);
        }
        
        if (cmbCukur.getSelectedIndex() == 1) {
            Tcukur.setEnabled(true);
        } else {
            Tcukur.setEnabled(false);
        }
        
        if (cmbLavemen.getSelectedIndex() == 1) {
            Tlavemen.setEnabled(true);
        } else {
            Tlavemen.setEnabled(false);
        }
        
        if (cmbAntibiotik.getSelectedIndex() == 1) {
            Tantibiotik.setEnabled(true);
        } else {
            Tantibiotik.setEnabled(false);
        }
    }
    
    public void setData(String norw, String norm, String nmpasien, String ruangan) {
        TNoRw.setText(norw);
        TNoRM.setText(norm);
        TPasien.setText(nmpasien);
        TrgRawat.setText(ruangan);
        TCari.setText(norw);        
    }
}
