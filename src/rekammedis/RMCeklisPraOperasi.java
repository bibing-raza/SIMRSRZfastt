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
    private int i = 0, pilihDokter = 0, pilihPerawat = 0;
    private String kode = "";
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public RMCeklisPraOperasi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode=new DefaultTableModel(null,new Object[]{
            "Kode Komite", "Nama Komite"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbCeklis.setModel(tabMode);
        tbCeklis.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbCeklis.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 2; i++) {
            TableColumn column = tbCeklis.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(80);
            } else if (i == 1) {
                column.setPreferredWidth(300);
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

        internalFrame1 = new widget.InternalFrame();
        PanelInput = new javax.swing.JPanel();
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
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnGanti = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass10 = new widget.panelisi();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        Scroll = new widget.ScrollPane();
        tbCeklis = new widget.Table();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Checklist Pra Operasi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 700));
        PanelInput.setLayout(null);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("No. Rawat :");
        jLabel10.setName("jLabel10"); // NOI18N
        PanelInput.add(jLabel10);
        jLabel10.setBounds(0, 10, 140, 23);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        PanelInput.add(TNoRw);
        TNoRw.setBounds(145, 10, 131, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        PanelInput.add(TNoRM);
        TNoRM.setBounds(279, 10, 70, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        PanelInput.add(TPasien);
        TPasien.setBounds(352, 10, 407, 23);

        jLabel63.setForeground(new java.awt.Color(0, 0, 0));
        jLabel63.setText("Ruang Rawat :");
        jLabel63.setName("jLabel63"); // NOI18N
        PanelInput.add(jLabel63);
        jLabel63.setBounds(0, 38, 140, 23);

        TrgRawat.setEditable(false);
        TrgRawat.setForeground(new java.awt.Color(0, 0, 0));
        TrgRawat.setName("TrgRawat"); // NOI18N
        PanelInput.add(TrgRawat);
        TrgRawat.setBounds(145, 38, 615, 23);

        jLabel64.setForeground(new java.awt.Color(0, 0, 0));
        jLabel64.setText("Diagnosa Pra Tindakan :");
        jLabel64.setName("jLabel64"); // NOI18N
        PanelInput.add(jLabel64);
        jLabel64.setBounds(0, 66, 140, 23);

        Tdiagnosa.setForeground(new java.awt.Color(0, 0, 0));
        Tdiagnosa.setName("Tdiagnosa"); // NOI18N
        Tdiagnosa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdiagnosaKeyPressed(evt);
            }
        });
        PanelInput.add(Tdiagnosa);
        Tdiagnosa.setBounds(145, 66, 615, 23);

        jLabel65.setForeground(new java.awt.Color(0, 0, 0));
        jLabel65.setText("Rencana Tindakan :");
        jLabel65.setName("jLabel65"); // NOI18N
        PanelInput.add(jLabel65);
        jLabel65.setBounds(0, 94, 140, 23);

        Trencana.setForeground(new java.awt.Color(0, 0, 0));
        Trencana.setName("Trencana"); // NOI18N
        Trencana.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TrencanaKeyPressed(evt);
            }
        });
        PanelInput.add(Trencana);
        Trencana.setBounds(145, 94, 615, 23);

        label15.setForeground(new java.awt.Color(0, 0, 0));
        label15.setText("Dokter Operator :");
        label15.setName("label15"); // NOI18N
        label15.setPreferredSize(new java.awt.Dimension(70, 23));
        PanelInput.add(label15);
        label15.setBounds(0, 122, 140, 23);

        TnipOperator.setEditable(false);
        TnipOperator.setForeground(new java.awt.Color(0, 0, 0));
        TnipOperator.setName("TnipOperator"); // NOI18N
        TnipOperator.setPreferredSize(new java.awt.Dimension(80, 23));
        PanelInput.add(TnipOperator);
        TnipOperator.setBounds(145, 122, 150, 23);

        TnmOperator.setEditable(false);
        TnmOperator.setForeground(new java.awt.Color(0, 0, 0));
        TnmOperator.setName("TnmOperator"); // NOI18N
        TnmOperator.setPreferredSize(new java.awt.Dimension(207, 23));
        PanelInput.add(TnmOperator);
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
        PanelInput.add(BtnOperator);
        BtnOperator.setBounds(660, 122, 28, 23);

        label16.setForeground(new java.awt.Color(0, 0, 0));
        label16.setText("Dokter Anastesi :");
        label16.setName("label16"); // NOI18N
        label16.setPreferredSize(new java.awt.Dimension(70, 23));
        PanelInput.add(label16);
        label16.setBounds(0, 150, 140, 23);

        TnipAnastesi.setEditable(false);
        TnipAnastesi.setForeground(new java.awt.Color(0, 0, 0));
        TnipAnastesi.setName("TnipAnastesi"); // NOI18N
        TnipAnastesi.setPreferredSize(new java.awt.Dimension(80, 23));
        PanelInput.add(TnipAnastesi);
        TnipAnastesi.setBounds(145, 150, 150, 23);

        TnmAnastesi.setEditable(false);
        TnmAnastesi.setForeground(new java.awt.Color(0, 0, 0));
        TnmAnastesi.setName("TnmAnastesi"); // NOI18N
        TnmAnastesi.setPreferredSize(new java.awt.Dimension(207, 23));
        PanelInput.add(TnmAnastesi);
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
        PanelInput.add(BtnAnastesi);
        BtnAnastesi.setBounds(660, 150, 28, 23);

        label17.setForeground(new java.awt.Color(0, 0, 0));
        label17.setText("KEADAAN UMUM :");
        label17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label17.setName("label17"); // NOI18N
        label17.setPreferredSize(new java.awt.Dimension(70, 23));
        PanelInput.add(label17);
        label17.setBounds(0, 178, 140, 23);

        jLabel66.setForeground(new java.awt.Color(0, 0, 0));
        jLabel66.setText("Kesadaran :");
        jLabel66.setName("jLabel66"); // NOI18N
        PanelInput.add(jLabel66);
        jLabel66.setBounds(0, 206, 140, 23);

        Tkesadaran.setForeground(new java.awt.Color(0, 0, 0));
        Tkesadaran.setName("Tkesadaran"); // NOI18N
        Tkesadaran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkesadaranKeyPressed(evt);
            }
        });
        PanelInput.add(Tkesadaran);
        Tkesadaran.setBounds(145, 206, 615, 23);

        jLabel67.setForeground(new java.awt.Color(0, 0, 0));
        jLabel67.setText("Tekanan Darah :");
        jLabel67.setName("jLabel67"); // NOI18N
        PanelInput.add(jLabel67);
        jLabel67.setBounds(0, 234, 140, 23);

        Ttensi.setForeground(new java.awt.Color(0, 0, 0));
        Ttensi.setName("Ttensi"); // NOI18N
        Ttensi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtensiKeyPressed(evt);
            }
        });
        PanelInput.add(Ttensi);
        Ttensi.setBounds(145, 234, 70, 23);

        label104.setForeground(new java.awt.Color(0, 0, 0));
        label104.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label104.setText("mmHg    Suhu : ");
        label104.setName("label104"); // NOI18N
        label104.setPreferredSize(new java.awt.Dimension(70, 23));
        PanelInput.add(label104);
        label104.setBounds(220, 234, 75, 23);

        Tsuhu.setForeground(new java.awt.Color(0, 0, 0));
        Tsuhu.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tsuhu.setName("Tsuhu"); // NOI18N
        Tsuhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TsuhuKeyPressed(evt);
            }
        });
        PanelInput.add(Tsuhu);
        Tsuhu.setBounds(298, 234, 48, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel23.setText("°C     Nadi :");
        jLabel23.setName("jLabel23"); // NOI18N
        PanelInput.add(jLabel23);
        jLabel23.setBounds(350, 234, 58, 23);

        Tnadi.setForeground(new java.awt.Color(0, 0, 0));
        Tnadi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tnadi.setName("Tnadi"); // NOI18N
        Tnadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnadiKeyPressed(evt);
            }
        });
        PanelInput.add(Tnadi);
        Tnadi.setBounds(409, 234, 70, 23);

        label43.setForeground(new java.awt.Color(0, 0, 0));
        label43.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label43.setText("x/menit      Respirasi : ");
        label43.setName("label43"); // NOI18N
        label43.setPreferredSize(new java.awt.Dimension(70, 23));
        PanelInput.add(label43);
        label43.setBounds(485, 234, 108, 23);

        Trespi.setForeground(new java.awt.Color(0, 0, 0));
        Trespi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Trespi.setName("Trespi"); // NOI18N
        Trespi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TrespiKeyPressed(evt);
            }
        });
        PanelInput.add(Trespi);
        Trespi.setBounds(593, 234, 60, 23);

        label106.setForeground(new java.awt.Color(0, 0, 0));
        label106.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label106.setText("x/menit");
        label106.setName("label106"); // NOI18N
        label106.setPreferredSize(new java.awt.Dimension(70, 23));
        PanelInput.add(label106);
        label106.setBounds(657, 234, 60, 23);

        label18.setForeground(new java.awt.Color(0, 0, 0));
        label18.setText("PERLENGKAPAN :");
        label18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label18.setName("label18"); // NOI18N
        label18.setPreferredSize(new java.awt.Dimension(70, 23));
        PanelInput.add(label18);
        label18.setBounds(0, 262, 140, 23);

        jLabel68.setForeground(new java.awt.Color(0, 0, 0));
        jLabel68.setText("Pasang Infus :");
        jLabel68.setName("jLabel68"); // NOI18N
        PanelInput.add(jLabel68);
        jLabel68.setBounds(0, 290, 140, 23);

        cmbInfus.setForeground(new java.awt.Color(0, 0, 0));
        cmbInfus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbInfus.setName("cmbInfus"); // NOI18N
        cmbInfus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbInfusActionPerformed(evt);
            }
        });
        PanelInput.add(cmbInfus);
        cmbInfus.setBounds(145, 290, 60, 23);

        Tinfus.setForeground(new java.awt.Color(0, 0, 0));
        Tinfus.setName("Tinfus"); // NOI18N
        Tinfus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TinfusKeyPressed(evt);
            }
        });
        PanelInput.add(Tinfus);
        Tinfus.setBounds(210, 290, 550, 23);

        jLabel69.setForeground(new java.awt.Color(0, 0, 0));
        jLabel69.setText("Pasang Kateter :");
        jLabel69.setName("jLabel69"); // NOI18N
        PanelInput.add(jLabel69);
        jLabel69.setBounds(0, 318, 140, 23);

        cmbKateter.setForeground(new java.awt.Color(0, 0, 0));
        cmbKateter.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbKateter.setName("cmbKateter"); // NOI18N
        cmbKateter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKateterActionPerformed(evt);
            }
        });
        PanelInput.add(cmbKateter);
        cmbKateter.setBounds(145, 318, 60, 23);

        Tkateter.setForeground(new java.awt.Color(0, 0, 0));
        Tkateter.setName("Tkateter"); // NOI18N
        Tkateter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkateterKeyPressed(evt);
            }
        });
        PanelInput.add(Tkateter);
        Tkateter.setBounds(210, 318, 550, 23);

        jLabel70.setForeground(new java.awt.Color(0, 0, 0));
        jLabel70.setText("Cukur Daerah Operasi :");
        jLabel70.setName("jLabel70"); // NOI18N
        PanelInput.add(jLabel70);
        jLabel70.setBounds(0, 346, 140, 23);

        cmbCukur.setForeground(new java.awt.Color(0, 0, 0));
        cmbCukur.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbCukur.setName("cmbCukur"); // NOI18N
        cmbCukur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCukurActionPerformed(evt);
            }
        });
        PanelInput.add(cmbCukur);
        cmbCukur.setBounds(145, 346, 60, 23);

        Tcukur.setForeground(new java.awt.Color(0, 0, 0));
        Tcukur.setName("Tcukur"); // NOI18N
        Tcukur.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TcukurKeyPressed(evt);
            }
        });
        PanelInput.add(Tcukur);
        Tcukur.setBounds(210, 346, 550, 23);

        jLabel71.setForeground(new java.awt.Color(0, 0, 0));
        jLabel71.setText("Lavement :");
        jLabel71.setName("jLabel71"); // NOI18N
        PanelInput.add(jLabel71);
        jLabel71.setBounds(0, 374, 140, 23);

        cmbLavemen.setForeground(new java.awt.Color(0, 0, 0));
        cmbLavemen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbLavemen.setName("cmbLavemen"); // NOI18N
        cmbLavemen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbLavemenActionPerformed(evt);
            }
        });
        PanelInput.add(cmbLavemen);
        cmbLavemen.setBounds(145, 374, 60, 23);

        Tlavemen.setForeground(new java.awt.Color(0, 0, 0));
        Tlavemen.setName("Tlavemen"); // NOI18N
        Tlavemen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlavemenKeyPressed(evt);
            }
        });
        PanelInput.add(Tlavemen);
        Tlavemen.setBounds(210, 374, 550, 23);

        jLabel72.setForeground(new java.awt.Color(0, 0, 0));
        jLabel72.setText("Gigi Palsu :");
        jLabel72.setName("jLabel72"); // NOI18N
        PanelInput.add(jLabel72);
        jLabel72.setBounds(0, 402, 140, 23);

        cmbGigi.setForeground(new java.awt.Color(0, 0, 0));
        cmbGigi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada", "Ada" }));
        cmbGigi.setName("cmbGigi"); // NOI18N
        PanelInput.add(cmbGigi);
        cmbGigi.setBounds(145, 402, 80, 23);

        jLabel73.setForeground(new java.awt.Color(0, 0, 0));
        jLabel73.setText("Baju & Topi Operasi :");
        jLabel73.setName("jLabel73"); // NOI18N
        PanelInput.add(jLabel73);
        jLabel73.setBounds(0, 430, 140, 23);

        cmbBaju.setForeground(new java.awt.Color(0, 0, 0));
        cmbBaju.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Belum", "Sudah" }));
        cmbBaju.setName("cmbBaju"); // NOI18N
        PanelInput.add(cmbBaju);
        cmbBaju.setBounds(145, 430, 60, 23);

        jLabel74.setForeground(new java.awt.Color(0, 0, 0));
        jLabel74.setText("Penandaan Daerah Operasi :");
        jLabel74.setName("jLabel74"); // NOI18N
        PanelInput.add(jLabel74);
        jLabel74.setBounds(225, 402, 160, 23);

        cmbPenandaan.setForeground(new java.awt.Color(0, 0, 0));
        cmbPenandaan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Belum", "Sudah" }));
        cmbPenandaan.setName("cmbPenandaan"); // NOI18N
        PanelInput.add(cmbPenandaan);
        cmbPenandaan.setBounds(390, 402, 60, 23);

        jLabel75.setForeground(new java.awt.Color(0, 0, 0));
        jLabel75.setText("Surat Persetujuan Anastesi :");
        jLabel75.setName("jLabel75"); // NOI18N
        PanelInput.add(jLabel75);
        jLabel75.setBounds(225, 430, 160, 23);

        cmbSuperAnastesi.setForeground(new java.awt.Color(0, 0, 0));
        cmbSuperAnastesi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Belum", "Sudah" }));
        cmbSuperAnastesi.setName("cmbSuperAnastesi"); // NOI18N
        PanelInput.add(cmbSuperAnastesi);
        cmbSuperAnastesi.setBounds(390, 430, 60, 23);

        jLabel76.setForeground(new java.awt.Color(0, 0, 0));
        jLabel76.setText("Surat Persetujuan Tindakan :");
        jLabel76.setName("jLabel76"); // NOI18N
        PanelInput.add(jLabel76);
        jLabel76.setBounds(450, 402, 160, 23);

        jLabel77.setForeground(new java.awt.Color(0, 0, 0));
        jLabel77.setText("Surat Persetujuan Transfusi :");
        jLabel77.setName("jLabel77"); // NOI18N
        PanelInput.add(jLabel77);
        jLabel77.setBounds(450, 430, 160, 23);

        cmbSuperTindakan.setForeground(new java.awt.Color(0, 0, 0));
        cmbSuperTindakan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Belum", "Sudah" }));
        cmbSuperTindakan.setName("cmbSuperTindakan"); // NOI18N
        PanelInput.add(cmbSuperTindakan);
        cmbSuperTindakan.setBounds(616, 402, 60, 23);

        cmbSuperTransfusi.setForeground(new java.awt.Color(0, 0, 0));
        cmbSuperTransfusi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Belum", "Sudah" }));
        cmbSuperTransfusi.setName("cmbSuperTransfusi"); // NOI18N
        PanelInput.add(cmbSuperTransfusi);
        cmbSuperTransfusi.setBounds(616, 430, 60, 23);

        jLabel78.setForeground(new java.awt.Color(0, 0, 0));
        jLabel78.setText("Antibiotik Profilaksi :");
        jLabel78.setName("jLabel78"); // NOI18N
        PanelInput.add(jLabel78);
        jLabel78.setBounds(0, 458, 140, 23);

        cmbAntibiotik.setForeground(new java.awt.Color(0, 0, 0));
        cmbAntibiotik.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbAntibiotik.setName("cmbAntibiotik"); // NOI18N
        cmbAntibiotik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAntibiotikActionPerformed(evt);
            }
        });
        PanelInput.add(cmbAntibiotik);
        cmbAntibiotik.setBounds(145, 458, 60, 23);

        Tantibiotik.setForeground(new java.awt.Color(0, 0, 0));
        Tantibiotik.setName("Tantibiotik"); // NOI18N
        Tantibiotik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TantibiotikKeyPressed(evt);
            }
        });
        PanelInput.add(Tantibiotik);
        Tantibiotik.setBounds(210, 458, 340, 23);

        jLabel79.setForeground(new java.awt.Color(0, 0, 0));
        jLabel79.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel79.setText("gr,   Jam :");
        jLabel79.setName("jLabel79"); // NOI18N
        PanelInput.add(jLabel79);
        jLabel79.setBounds(555, 458, 53, 23);

        cmbJam.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJamMouseReleased(evt);
            }
        });
        PanelInput.add(cmbJam);
        cmbJam.setBounds(608, 458, 45, 23);

        cmbMnt.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMntMouseReleased(evt);
            }
        });
        PanelInput.add(cmbMnt);
        cmbMnt.setBounds(659, 458, 45, 23);

        cmbDtk.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtkMouseReleased(evt);
            }
        });
        PanelInput.add(cmbDtk);
        cmbDtk.setBounds(710, 458, 45, 23);

        label19.setForeground(new java.awt.Color(0, 0, 0));
        label19.setText("PEMERIKSAAN PENUNJANG :");
        label19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label19.setName("label19"); // NOI18N
        label19.setPreferredSize(new java.awt.Dimension(70, 23));
        PanelInput.add(label19);
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

        PanelInput.add(scrollPane13);
        scrollPane13.setBounds(185, 486, 575, 80);

        jLabel80.setForeground(new java.awt.Color(0, 0, 0));
        jLabel80.setText("EKG :");
        jLabel80.setName("jLabel80"); // NOI18N
        PanelInput.add(jLabel80);
        jLabel80.setBounds(0, 572, 140, 23);

        cmbEkg.setForeground(new java.awt.Color(0, 0, 0));
        cmbEkg.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbEkg.setName("cmbEkg"); // NOI18N
        PanelInput.add(cmbEkg);
        cmbEkg.setBounds(145, 572, 60, 23);

        jLabel81.setForeground(new java.awt.Color(0, 0, 0));
        jLabel81.setText("Intepretasi EKG : ");
        jLabel81.setName("jLabel81"); // NOI18N
        PanelInput.add(jLabel81);
        jLabel81.setBounds(205, 572, 105, 23);

        TintepretasiEkg.setForeground(new java.awt.Color(0, 0, 0));
        TintepretasiEkg.setName("TintepretasiEkg"); // NOI18N
        TintepretasiEkg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TintepretasiEkgKeyPressed(evt);
            }
        });
        PanelInput.add(TintepretasiEkg);
        TintepretasiEkg.setBounds(310, 572, 450, 23);

        jLabel82.setForeground(new java.awt.Color(0, 0, 0));
        jLabel82.setText("Intepretasi RO/CT-Scan :");
        jLabel82.setName("jLabel82"); // NOI18N
        PanelInput.add(jLabel82);
        jLabel82.setBounds(0, 600, 140, 23);

        cmbIntepretasiRo.setForeground(new java.awt.Color(0, 0, 0));
        cmbIntepretasiRo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbIntepretasiRo.setName("cmbIntepretasiRo"); // NOI18N
        PanelInput.add(cmbIntepretasiRo);
        cmbIntepretasiRo.setBounds(145, 600, 60, 23);

        jLabel83.setForeground(new java.awt.Color(0, 0, 0));
        jLabel83.setText("Persiapan Darah : ");
        jLabel83.setName("jLabel83"); // NOI18N
        PanelInput.add(jLabel83);
        jLabel83.setBounds(205, 600, 105, 23);

        cmbPersiapanDarah.setForeground(new java.awt.Color(0, 0, 0));
        cmbPersiapanDarah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbPersiapanDarah.setName("cmbPersiapanDarah"); // NOI18N
        PanelInput.add(cmbPersiapanDarah);
        cmbPersiapanDarah.setBounds(310, 600, 60, 23);

        jLabel84.setForeground(new java.awt.Color(0, 0, 0));
        jLabel84.setText("Persiapan Puasa : ");
        jLabel84.setName("jLabel84"); // NOI18N
        PanelInput.add(jLabel84);
        jLabel84.setBounds(370, 600, 105, 23);

        cmbPersiapanPuasa.setForeground(new java.awt.Color(0, 0, 0));
        cmbPersiapanPuasa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbPersiapanPuasa.setName("cmbPersiapanPuasa"); // NOI18N
        PanelInput.add(cmbPersiapanPuasa);
        cmbPersiapanPuasa.setBounds(476, 600, 60, 23);

        jLabel85.setForeground(new java.awt.Color(0, 0, 0));
        jLabel85.setText("Tgl. Checlist :");
        jLabel85.setName("jLabel85"); // NOI18N
        PanelInput.add(jLabel85);
        jLabel85.setBounds(535, 600, 80, 23);

        Ttglceklis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "14-09-2024" }));
        Ttglceklis.setDisplayFormat("dd-MM-yyyy");
        Ttglceklis.setName("Ttglceklis"); // NOI18N
        Ttglceklis.setOpaque(false);
        Ttglceklis.setPreferredSize(new java.awt.Dimension(90, 23));
        PanelInput.add(Ttglceklis);
        Ttglceklis.setBounds(620, 600, 90, 23);

        label20.setForeground(new java.awt.Color(0, 0, 0));
        label20.setText("Perawat Bangsal :");
        label20.setName("label20"); // NOI18N
        label20.setPreferredSize(new java.awt.Dimension(70, 23));
        PanelInput.add(label20);
        label20.setBounds(0, 628, 140, 23);

        TnipBangsal.setEditable(false);
        TnipBangsal.setForeground(new java.awt.Color(0, 0, 0));
        TnipBangsal.setName("TnipBangsal"); // NOI18N
        TnipBangsal.setPreferredSize(new java.awt.Dimension(80, 23));
        PanelInput.add(TnipBangsal);
        TnipBangsal.setBounds(145, 628, 150, 23);

        TnmPerawatBangsal.setEditable(false);
        TnmPerawatBangsal.setForeground(new java.awt.Color(0, 0, 0));
        TnmPerawatBangsal.setName("TnmPerawatBangsal"); // NOI18N
        TnmPerawatBangsal.setPreferredSize(new java.awt.Dimension(207, 23));
        PanelInput.add(TnmPerawatBangsal);
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
        PanelInput.add(BtnBangsal);
        BtnBangsal.setBounds(660, 628, 28, 23);

        label21.setForeground(new java.awt.Color(0, 0, 0));
        label21.setText("Perawat Bedah Sentral :");
        label21.setName("label21"); // NOI18N
        label21.setPreferredSize(new java.awt.Dimension(70, 23));
        PanelInput.add(label21);
        label21.setBounds(0, 656, 140, 23);

        TnipIbs.setEditable(false);
        TnipIbs.setForeground(new java.awt.Color(0, 0, 0));
        TnipIbs.setName("TnipIbs"); // NOI18N
        TnipIbs.setPreferredSize(new java.awt.Dimension(80, 23));
        PanelInput.add(TnipIbs);
        TnipIbs.setBounds(145, 656, 150, 23);

        TnmPerawatIbs.setEditable(false);
        TnmPerawatIbs.setForeground(new java.awt.Color(0, 0, 0));
        TnmPerawatIbs.setName("TnmPerawatIbs"); // NOI18N
        TnmPerawatIbs.setPreferredSize(new java.awt.Dimension(207, 23));
        PanelInput.add(TnmPerawatIbs);
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
        PanelInput.add(BtnIbs);
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
        PanelInput.add(chkSaya1);
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
        PanelInput.add(chkSaya2);
        chkSaya2.setBounds(695, 656, 90, 23);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.CENTER);

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 100));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(55, 55));
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

        jPanel3.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(250, 23));
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

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass10.add(jLabel7);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass10.add(LCount);

        jPanel3.add(panelGlass10, java.awt.BorderLayout.CENTER);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        Scroll.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Data Checklist Pra Operasi ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
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

        internalFrame1.add(Scroll, java.awt.BorderLayout.EAST);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
//        if (kdkomite.getText().trim().equals("")) {
//            Valid.textKosong(kdkomite, "kode komite");
//            kdkomite.requestFocus();
//        } else if (nmkomite.getText().trim().equals("")) {
//            Valid.textKosong(nmkomite, "nama komite");
//            nmkomite.requestFocus();
//        } else {            
//            Sequel.menyimpan("jabatan_komite", "'" + kdkomite.getText() + "','" + nmkomite.getText() + "'", "Jabatan Komite");
//            emptTeks();
//            BtnCariActionPerformed(null);
//        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
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
            Valid.pindah(evt, BtnSimpan, BtnGanti);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnGantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiActionPerformed
//        if (kdkomite.getText().trim().equals("")) {
//            Valid.textKosong(kdkomite, "kode komite");
//            kdkomite.requestFocus();
//        } else if (nmkomite.getText().trim().equals("")) {
//            Valid.textKosong(nmkomite, "nama komite");
//            nmkomite.requestFocus();
//        } else if (kode.equals("")) {
//            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu jabatan komitenya pada tabel...!!!!");
//            tbCeklis.requestFocus();
//        } else {           
//            Sequel.mengedit("jabatan_komite", "kd_komite='" + kode + "'", "kd_komite='" + kdkomite.getText() + "',nm_komite='" + nmkomite.getText() + "'");
//            emptTeks();
//            BtnCariActionPerformed(null);
//        }
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
    private widget.Button BtnIbs;
    private widget.Button BtnKeluar;
    private widget.Button BtnOperator;
    private widget.Button BtnSimpan;
    private widget.Label LCount;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll;
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
    private javax.swing.JPanel jPanel3;
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
    private widget.panelisi panelGlass8;
    private widget.ScrollPane scrollPane13;
    private widget.Table tbCeklis;
    // End of variables declaration//GEN-END:variables

    public void tampil() {     
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT kd_komite, nm_komite FROM jabatan_komite WHERE "
                    + "kd_komite LIKE ? or "
                    + "nm_komite like ? ORDER BY kd_komite");
            try {
                ps.setString(1, "%" + TCari.getText().trim() + "%");
                ps.setString(2, "%" + TCari.getText().trim() + "%");
                rs = ps.executeQuery();                
                while (rs.next()) {
                    tabMode.addRow(new Object[]{                        
                        rs.getString(1),
                        rs.getString(2)
                    });
                }                
            } catch (Exception e) {
                System.out.println("rekammedis.DlgMasterJabatanKomite.tampil() : " + e);
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
    }

    private void getData() {
        if (tbCeklis.getSelectedRow() != -1) {
//            kdkomite.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 0).toString());
//            nmkomite.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 1).toString());        
        }
    }
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.getpegawai_admin());
        BtnGanti.setEnabled(akses.getpegawai_admin());
        tampil();
    }    
}
