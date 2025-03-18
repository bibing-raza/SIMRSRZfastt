/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bridging;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

/**
 *
 * @author dosen
 */
public final class SatuSehatMapingObatAlkes extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1, tabMode2;
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Connection koneksi = koneksiDB.condb();
    private ApiSatuSehat api = new ApiSatuSehat();
    private PreparedStatement ps, ps1, ps2;
    private ResultSet rs, rs1, rs2;
    private int i = 0, x = 0;
    private String link = "", json = "";
    private HttpHeaders headers;
    private HttpEntity requestEntity;
    private ObjectMapper mapper = new ObjectMapper();
    private JsonNode root;
    private JsonNode response;

    /** Creates new form DlgJnsPerawatanRalan
     * @param parent
     * @param modal */
    public SatuSehatMapingObatAlkes(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(8,1);
        setSize(628,674);

        tabMode = new DefaultTableModel(null, new String[]{
            "KFA Code", "KFA System", "Kode Barang", "Nama Obat/Alkes/BHP", "KFA Display", "Form Code",
            "Form System", "Form Display", "Numerator Code", "Numerator System", "Denominator Code",
            "Denominator System"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbMaping.setModel(tabMode);
        tbMaping.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbMaping.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 12; i++) {
            TableColumn column = tbMaping.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(80);
            } else if (i == 1) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 2) {
                column.setPreferredWidth(140);
            } else if (i == 3) {
                column.setPreferredWidth(300);
            } else if (i == 4) {
                column.setPreferredWidth(300);
            } else if (i == 5) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 6) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 7) {
                column.setPreferredWidth(200);
            } else if (i == 8) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 9) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 10) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 11) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbMaping.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1= new DefaultTableModel(null, new Object[]{
            "Kode Barang", "Nama Barang", "Kode Satuan", "Nama Satuan", "Letak Barang",
            "Hrg.Beli(Rp)", "Ralan(Rp)", "Ranap K1(Rp)", "Ranap K2(Rp)", "Ranap K3(Rp)",
            "Kelas Utama/BPJS(Rp)", "Ranap VIP(Rp)", "Ranap VVIP(Rp)", "Beli Luar(Rp)",
            "Jual Bebas(Rp)", "Karyawan(Rp)", "Stok Minimal", "Kode Jenis", "Nama Jenis", "Kapasitas",
            "Kadaluwarsa", "Kode I.F.", "Industri Farmasi", "Kode Kategori", "Kategori", "Kode Golongan", 
            "Golongan", "Tipe Barang", "High Alert"
            }){
             @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        
        tbObatRS.setModel(tabMode1);
        tbObatRS.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObatRS.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 29; i++) {
            TableColumn column = tbObatRS.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(120);
            } else if (i == 1) {
                column.setPreferredWidth(200);
            } else if (i == 2) {
                column.setPreferredWidth(73);
            } else if (i == 3) {
                column.setPreferredWidth(120);
            } else if (i == 4) {
                column.setPreferredWidth(140);
            } else if (i == 5) {
                column.setPreferredWidth(85);
            } else if (i == 6) {
                column.setPreferredWidth(85);
            } else if (i == 7) {
                column.setPreferredWidth(85);
            } else if (i == 8) {
                column.setPreferredWidth(85);
            } else if (i == 9) {
                column.setPreferredWidth(85);
            } else if (i == 10) {
                column.setPreferredWidth(85);
            } else if (i == 11) {
                column.setPreferredWidth(85);
            } else if (i == 12) {
                column.setPreferredWidth(85);
            } else if (i == 13) {
                column.setPreferredWidth(85);
            } else if (i == 14) {
                column.setPreferredWidth(85);
            } else if (i == 15) {
                column.setPreferredWidth(85);
            } else if (i == 16) {
                column.setPreferredWidth(95);
            } else if (i == 17) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 18) {
                column.setPreferredWidth(120);
            } else if (i == 19) {
                column.setPreferredWidth(70);
            } else if (i == 20) {
                column.setPreferredWidth(70);
            } else if (i == 21) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 22) {
                column.setPreferredWidth(120);
            } else if (i == 23) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 24) {
                column.setPreferredWidth(120);
            } else if (i == 25) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 26) {
                column.setPreferredWidth(80);
            } else if (i == 27) {
                column.setPreferredWidth(80);
            } else if (i == 28) {
                column.setPreferredWidth(80);
            }
        }
        tbObatRS.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode2 = new DefaultTableModel(null, new String[]{
            "Kode KFA", "Name/Obat Display", "Nama Dagang", "Manufacture", "Registrar", "Generik",
            "Dosis PerUnit", "Form Kode", "Form Display", "Numerator Kode"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbKFA.setModel(tabMode2);
        tbKFA.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbKFA.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 10; i++) {
            TableColumn column = tbKFA.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(80);
            } else if (i == 1) {
                column.setPreferredWidth(800);
            } else if (i == 2) {
                column.setPreferredWidth(220);
            } else if (i == 3) {
                column.setPreferredWidth(200);
            } else if (i == 4) {
                column.setPreferredWidth(200);
            } else if (i == 5) {
                column.setPreferredWidth(70);
            } else if (i == 6) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 7) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 8) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 9) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbKFA.setDefaultRenderer(Object.class, new WarnaTable());

        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        TCari1.setDocument(new batasInput((byte) 100).getKata(TCari));
        TCari2.setDocument(new batasInput((byte) 100).getKata(TCari));
        
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
        PanelInput = new javax.swing.JPanel();
        KodeBarang = new widget.TextBox();
        jLabel5 = new widget.Label();
        FormCode = new widget.TextBox();
        jLabel8 = new widget.Label();
        NumoratorCode = new widget.TextBox();
        KFACode = new widget.TextBox();
        jLabel9 = new widget.Label();
        jLabel10 = new widget.Label();
        KFADisplay = new widget.TextBox();
        FormDisplay = new widget.TextBox();
        jLabel12 = new widget.Label();
        TnmObatRS = new widget.TextBox();
        panelGlass10 = new widget.panelisi();
        Scroll = new widget.ScrollPane();
        tbMaping = new widget.Table();
        panelGlass11 = new widget.panelisi();
        panelGlass9 = new widget.panelisi();
        Scroll1 = new widget.ScrollPane();
        tbKFA = new widget.Table();
        panelGlass13 = new widget.panelisi();
        jLabel17 = new widget.Label();
        TCari2 = new widget.TextBox();
        BtnCari2 = new widget.Button();
        BtnMining = new widget.Button();
        jLabel11 = new widget.Label();
        LCountKFA = new widget.Label();
        panelGlass14 = new widget.panelisi();
        Scroll2 = new widget.ScrollPane();
        tbObatRS = new widget.Table();
        panelGlass12 = new widget.panelisi();
        jLabel16 = new widget.Label();
        TCari1 = new widget.TextBox();
        BtnCari1 = new widget.Button();
        jLabel13 = new widget.Label();
        LCountObatRS = new widget.Label();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCountMaping = new widget.Label();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Mapping Obat/Alkes/BHP Satu Sehat ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(660, 110));
        PanelInput.setLayout(null);

        KodeBarang.setEditable(false);
        KodeBarang.setForeground(new java.awt.Color(0, 0, 0));
        KodeBarang.setName("KodeBarang"); // NOI18N
        PanelInput.add(KodeBarang);
        KodeBarang.setBounds(139, 10, 130, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Form Code : ");
        jLabel5.setName("jLabel5"); // NOI18N
        PanelInput.add(jLabel5);
        jLabel5.setBounds(0, 70, 135, 23);

        FormCode.setEditable(false);
        FormCode.setForeground(new java.awt.Color(0, 0, 0));
        FormCode.setName("FormCode"); // NOI18N
        PanelInput.add(FormCode);
        FormCode.setBounds(139, 70, 80, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Numerator Code :");
        jLabel8.setName("jLabel8"); // NOI18N
        PanelInput.add(jLabel8);
        jLabel8.setBounds(580, 70, 100, 23);

        NumoratorCode.setEditable(false);
        NumoratorCode.setForeground(new java.awt.Color(0, 0, 0));
        NumoratorCode.setName("NumoratorCode"); // NOI18N
        PanelInput.add(NumoratorCode);
        NumoratorCode.setBounds(684, 70, 70, 23);

        KFACode.setEditable(false);
        KFACode.setForeground(new java.awt.Color(0, 0, 0));
        KFACode.setName("KFACode"); // NOI18N
        PanelInput.add(KFACode);
        KFACode.setBounds(139, 40, 100, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Obat RS (Kode) : ");
        jLabel9.setName("jLabel9"); // NOI18N
        PanelInput.add(jLabel9);
        jLabel9.setBounds(0, 10, 135, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("KFA Display (Code) : ");
        jLabel10.setName("jLabel10"); // NOI18N
        PanelInput.add(jLabel10);
        jLabel10.setBounds(0, 40, 135, 23);

        KFADisplay.setEditable(false);
        KFADisplay.setForeground(new java.awt.Color(0, 0, 0));
        KFADisplay.setName("KFADisplay"); // NOI18N
        PanelInput.add(KFADisplay);
        KFADisplay.setBounds(244, 40, 510, 23);

        FormDisplay.setEditable(false);
        FormDisplay.setForeground(new java.awt.Color(0, 0, 0));
        FormDisplay.setName("FormDisplay"); // NOI18N
        PanelInput.add(FormDisplay);
        FormDisplay.setBounds(314, 70, 260, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Form Display :");
        jLabel12.setName("jLabel12"); // NOI18N
        PanelInput.add(jLabel12);
        jLabel12.setBounds(220, 70, 90, 23);

        TnmObatRS.setEditable(false);
        TnmObatRS.setForeground(new java.awt.Color(0, 0, 0));
        TnmObatRS.setName("TnmObatRS"); // NOI18N
        PanelInput.add(TnmObatRS);
        TnmObatRS.setBounds(274, 10, 480, 23);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass10.setLayout(new java.awt.GridLayout(1, 2));

        Scroll.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Daftar Obat/Alkes Sudah Mapping Dengan KFA Satu Sehat ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbMaping.setAutoCreateRowSorter(true);
        tbMaping.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbMaping.setName("tbMaping"); // NOI18N
        tbMaping.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMapingMouseClicked(evt);
            }
        });
        tbMaping.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbMapingKeyReleased(evt);
            }
        });
        Scroll.setViewportView(tbMaping);

        panelGlass10.add(Scroll);

        panelGlass11.setName("panelGlass11"); // NOI18N
        panelGlass11.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass11.setLayout(new java.awt.GridLayout(2, 0));

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 300));
        panelGlass9.setLayout(new java.awt.BorderLayout());

        Scroll1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Daftar KFA Satu Sehat Kemenkes ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);
        Scroll1.setPreferredSize(new java.awt.Dimension(452, 255));

        tbKFA.setName("tbKFA"); // NOI18N
        tbKFA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbKFAMouseClicked(evt);
            }
        });
        Scroll1.setViewportView(tbKFA);

        panelGlass9.add(Scroll1, java.awt.BorderLayout.CENTER);

        panelGlass13.setName("panelGlass13"); // NOI18N
        panelGlass13.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass13.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Key Word :");
        jLabel17.setName("jLabel17"); // NOI18N
        jLabel17.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass13.add(jLabel17);

        TCari2.setForeground(new java.awt.Color(0, 0, 0));
        TCari2.setName("TCari2"); // NOI18N
        TCari2.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari2KeyPressed(evt);
            }
        });
        panelGlass13.add(TCari2);

        BtnCari2.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari2.setMnemonic('2');
        BtnCari2.setText("Tampilkan Data");
        BtnCari2.setToolTipText("Alt+2");
        BtnCari2.setName("BtnCari2"); // NOI18N
        BtnCari2.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCari2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari2ActionPerformed(evt);
            }
        });
        panelGlass13.add(BtnCari2);

        BtnMining.setForeground(new java.awt.Color(0, 0, 0));
        BtnMining.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/42a.png"))); // NOI18N
        BtnMining.setMnemonic('M');
        BtnMining.setText("Mining KFA Satu Sehat");
        BtnMining.setToolTipText("Alt+M");
        BtnMining.setName("BtnMining"); // NOI18N
        BtnMining.setPreferredSize(new java.awt.Dimension(180, 23));
        BtnMining.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnMiningActionPerformed(evt);
            }
        });
        panelGlass13.add(BtnMining);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Record :");
        jLabel11.setName("jLabel11"); // NOI18N
        jLabel11.setPreferredSize(new java.awt.Dimension(55, 23));
        panelGlass13.add(jLabel11);

        LCountKFA.setForeground(new java.awt.Color(0, 0, 0));
        LCountKFA.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCountKFA.setText("0");
        LCountKFA.setName("LCountKFA"); // NOI18N
        LCountKFA.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass13.add(LCountKFA);

        panelGlass9.add(panelGlass13, java.awt.BorderLayout.PAGE_END);

        panelGlass11.add(panelGlass9);

        panelGlass14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Daftar Obat/Alkes Farmasi RS ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        panelGlass14.setName("panelGlass14"); // NOI18N
        panelGlass14.setPreferredSize(new java.awt.Dimension(44, 300));
        panelGlass14.setLayout(new java.awt.BorderLayout());

        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);
        Scroll2.setPreferredSize(new java.awt.Dimension(452, 300));

        tbObatRS.setName("tbObatRS"); // NOI18N
        tbObatRS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbObatRSMouseClicked(evt);
            }
        });
        Scroll2.setViewportView(tbObatRS);

        panelGlass14.add(Scroll2, java.awt.BorderLayout.CENTER);

        panelGlass12.setName("panelGlass12"); // NOI18N
        panelGlass12.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Key Word :");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass12.add(jLabel16);

        TCari1.setForeground(new java.awt.Color(0, 0, 0));
        TCari1.setName("TCari1"); // NOI18N
        TCari1.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari1KeyPressed(evt);
            }
        });
        panelGlass12.add(TCari1);

        BtnCari1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari1.setMnemonic('2');
        BtnCari1.setText("Tampilkan Data");
        BtnCari1.setToolTipText("Alt+2");
        BtnCari1.setName("BtnCari1"); // NOI18N
        BtnCari1.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari1ActionPerformed(evt);
            }
        });
        panelGlass12.add(BtnCari1);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Record :");
        jLabel13.setName("jLabel13"); // NOI18N
        jLabel13.setPreferredSize(new java.awt.Dimension(55, 23));
        panelGlass12.add(jLabel13);

        LCountObatRS.setForeground(new java.awt.Color(0, 0, 0));
        LCountObatRS.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCountObatRS.setText("0");
        LCountObatRS.setName("LCountObatRS"); // NOI18N
        LCountObatRS.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass12.add(LCountObatRS);

        panelGlass14.add(panelGlass12, java.awt.BorderLayout.PAGE_END);

        panelGlass11.add(panelGlass14);

        panelGlass10.add(panelGlass11);

        internalFrame1.add(panelGlass10, java.awt.BorderLayout.CENTER);

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 7));

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

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass8.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass8.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('2');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+2");
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
        panelGlass8.add(BtnCari);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(55, 23));
        panelGlass8.add(jLabel7);

        LCountMaping.setForeground(new java.awt.Color(0, 0, 0));
        LCountMaping.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCountMaping.setText("0");
        LCountMaping.setName("LCountMaping"); // NOI18N
        LCountMaping.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass8.add(LCountMaping);

        internalFrame1.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (KodeBarang.getText().trim().equals("")) {
            Valid.textKosong(KodeBarang, "Obat RS (Kode)");
        } else if (KFACode.getText().trim().equals("")) {
            Valid.textKosong(KFACode, "KFA Display (Code)");
        } else {
            if (Sequel.menyimpantf("satu_sehat_mapping_obat", "?,?,?,?,?,?,?,?,?,?,?", "Mapping KFA", 11, new String[]{
                KodeBarang.getText(), KFACode.getText(), "-", KFADisplay.getText(), FormCode.getText(), "-", FormDisplay.getText(), NumoratorCode.getText(), "-", "-", "-"
            }) == true) {
                tampil();
                emptTeks();
                LCountMaping.setText("" + tabMode.getRowCount());
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
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnHapus);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbMaping.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Valid.hapusTabletf(tabMode, KodeBarang, "satu_sehat_mapping_obat", "kode_brng") == true) {
                    tampil();
                    emptTeks();
                }
            } else {
                tampil();
                emptTeks();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
            tbMaping.requestFocus();
        }
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (KodeBarang.getText().trim().equals("")) {
            Valid.textKosong(KodeBarang, "Obat RS (Kode)");
        } else if (KFACode.getText().trim().equals("")) {
            Valid.textKosong(KFACode, "KFA Display (Code)");
        } else {
            if (tbMaping.getSelectedRow() > -1) {
                if (Sequel.mengedittf("satu_sehat_mapping_obat", "kode_brng=?", "kode_brng=?, obat_code=?, obat_display=?,"
                        + "form_code=?, form_display=?, numerator_code=?", 7, new String[]{
                            KodeBarang.getText(), KFACode.getText(), KFADisplay.getText(), FormCode.getText(),
                            FormDisplay.getText(), NumoratorCode.getText(),
                            tbMaping.getValueAt(tbMaping.getSelectedRow(), 2).toString()
                        }) == true) {
                    tampil();
                    emptTeks();
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
                tbMaping.requestFocus();
            }
        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnEditActionPerformed(null);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnEdit, TCari);
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
        tampil();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            TCari.setText("");
            tampil();
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbMapingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMapingMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbMapingMouseClicked

    private void tbMapingKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbMapingKeyReleased
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbMapingKeyReleased

    private void tbKFAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbKFAMouseClicked
        if (tabMode2.getRowCount() != 0) {
            try {
                getDataKFA();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbKFAMouseClicked

    private void tbObatRSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObatRSMouseClicked
        if (tabMode1.getRowCount() != 0) {
            try {
                KodeBarang.setText(tbObatRS.getValueAt(tbObatRS.getSelectedRow(),0).toString());
                TnmObatRS.setText(tbObatRS.getValueAt(tbObatRS.getSelectedRow(),1).toString());
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbObatRSMouseClicked

    private void TCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari1ActionPerformed(null);
        } 
    }//GEN-LAST:event_TCari1KeyPressed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        tampilObatRS();
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void TCari2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari2ActionPerformed(null);
        }
    }//GEN-LAST:event_TCari2KeyPressed

    private void BtnCari2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari2ActionPerformed
        tampilKFA();
    }//GEN-LAST:event_BtnCari2ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
        tampilKFA();
        tampilObatRS();
        isCek();
    }//GEN-LAST:event_formWindowOpened

    private void BtnMiningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnMiningActionPerformed
        x = JOptionPane.showConfirmDialog(rootPane, "Yakin akan melakukan mining KFA SatuSehat..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            miningKFA();
        } else {
            tampilKFA();
        }
    }//GEN-LAST:event_BtnMiningActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            SatuSehatMapingObatAlkes dialog = new SatuSehatMapingObatAlkes(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCari2;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnMining;
    private widget.Button BtnSimpan;
    private widget.TextBox FormCode;
    private widget.TextBox FormDisplay;
    private widget.TextBox KFACode;
    private widget.TextBox KFADisplay;
    private widget.TextBox KodeBarang;
    private widget.Label LCountKFA;
    private widget.Label LCountMaping;
    private widget.Label LCountObatRS;
    private widget.TextBox NumoratorCode;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    private widget.TextBox TCari;
    private widget.TextBox TCari1;
    private widget.TextBox TCari2;
    private widget.TextBox TnmObatRS;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass12;
    private widget.panelisi panelGlass13;
    private widget.panelisi panelGlass14;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.Table tbKFA;
    private widget.Table tbMaping;
    private widget.Table tbObatRS;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement(
                    "select satu_sehat_mapping_obat.kode_brng,databarang.nama_brng,satu_sehat_mapping_obat.obat_code,satu_sehat_mapping_obat.obat_system,"
                    + "satu_sehat_mapping_obat.obat_display,satu_sehat_mapping_obat.form_code,satu_sehat_mapping_obat.form_system,"
                    + "satu_sehat_mapping_obat.form_display,satu_sehat_mapping_obat.numerator_code,satu_sehat_mapping_obat.numerator_system,"
                    + "satu_sehat_mapping_obat.denominator_code,satu_sehat_mapping_obat.denominator_system from satu_sehat_mapping_obat inner join databarang "
                    + "on satu_sehat_mapping_obat.kode_brng=databarang.kode_brng "
                    + (TCari.getText().equals("") ? "" : "where satu_sehat_mapping_obat.kode_brng like ? or databarang.nama_brng like ? or "
                    + "satu_sehat_mapping_obat.obat_code like ? or satu_sehat_mapping_obat.obat_display like ? or satu_sehat_mapping_obat.form_display like ? ")
                    + " order by satu_sehat_mapping_obat.obat_code limit 200");
            try {
                if (!TCari.getText().equals("")) {
                    ps.setString(1, "%" + TCari.getText() + "%");
                    ps.setString(2, "%" + TCari.getText() + "%");
                    ps.setString(3, "%" + TCari.getText() + "%");
                    ps.setString(4, "%" + TCari.getText() + "%");
                    ps.setString(5, "%" + TCari.getText() + "%");
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString("obat_code"),
                        rs.getString("obat_system"),
                        rs.getString("kode_brng"),
                        rs.getString("nama_brng"),
                        rs.getString("obat_display"),
                        rs.getString("form_code"),
                        rs.getString("form_system"),
                        rs.getString("form_display"),
                        rs.getString("numerator_code"),
                        rs.getString("numerator_system"),
                        rs.getString("denominator_code"),
                        rs.getString("denominator_system")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif Ketersediaan : " + e);
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
        LCountMaping.setText("" + tabMode.getRowCount());
    }

    public void emptTeks() {
        KFACode.setText("");
        KFACode.requestFocus();
        KodeBarang.setText("");
        TnmObatRS.setText("");
        KFADisplay.setText("");
        FormCode.setText("");
        FormDisplay.setText("");
        NumoratorCode.setText("");
        TCari1.setText("");
        TCari2.setText("");
    }

    private void getData() {
       if(tbMaping.getSelectedRow()!= -1){
           KFACode.setText(tbMaping.getValueAt(tbMaping.getSelectedRow(),0).toString());
           KodeBarang.setText(tbMaping.getValueAt(tbMaping.getSelectedRow(),2).toString());
           TnmObatRS.setText(tbMaping.getValueAt(tbMaping.getSelectedRow(),3).toString());
           KFADisplay.setText(tbMaping.getValueAt(tbMaping.getSelectedRow(),4).toString());
           FormCode.setText(tbMaping.getValueAt(tbMaping.getSelectedRow(),5).toString());
           FormDisplay.setText(tbMaping.getValueAt(tbMaping.getSelectedRow(),7).toString());
           NumoratorCode.setText(tbMaping.getValueAt(tbMaping.getSelectedRow(),8).toString());
        }
    }
    
    public void isCek(){
        BtnSimpan.setEnabled(akses.getstok_obat_pasien());
        BtnHapus.setEnabled(akses.getstok_obat_pasien());
        BtnEdit.setEnabled(akses.getstok_obat_pasien());
        BtnMining.setEnabled(akses.getadmin());
    }
    
    private void tampilObatRS() {
        Valid.tabelKosong(tabMode1);
        try {
            ps1 = koneksi.prepareStatement("SELECT db.kode_brng, db.nama_brng, db.kode_sat, ks.satuan, db.letak_barang, db.h_beli, db.ralan, "
                    + "db.kelas1, db.kelas2, db.kelas3, db.utama, db.vip, db.vvip, db.beliluar, db.jualbebas, db.karyawan, "
                    + "db.stokminimal, db.kdjns, j.nama, kapasitas, db.expire, db.kode_industri, ifm.nama_industri, "
                    + "db.kode_kategori, kb.nama kategori, db.kode_golongan, gb.nama golongan, db.tipe_brg, high_alert "
                    + "FROM databarang db INNER JOIN kodesatuan ks on db.kode_sat = ks.kode_sat "
                    + "INNER JOIN jenis j on db.kdjns = j.kdjns INNER JOIN industrifarmasi ifm on db.kode_industri = ifm.kode_industri "
                    + "INNER JOIN golongan_barang gb on db.kode_golongan = gb.kode INNER JOIN kategori_barang kb ON db.kode_kategori = kb.kode where "
                    + "db.status='1' and db.kode_brng not in (select kode_brng from satu_sehat_mapping_obat) and ("
                    + "db.kode_brng like ? or "
                    + "db.nama_brng like ? or "
                    + "db.kode_sat like ? or "
                    + "ks.satuan like ? or "
                    + "db.letak_barang like ? or "
                    + "db.kdjns like ? or "
                    + "kb.nama like ? or "
                    + "gb.nama like ? or "
                    + "j.nama like ? or "
                    + "db.kode_industri like ? or "
                    + "db.tipe_brg like ? or "
                    + "db.high_alert like ? or "
                    + "ifm.nama_industri like ?) order by db.nama_brng");
            try {
                ps1.setString(1, "%" + TCari1.getText().trim() + "%");
                ps1.setString(2, "%" + TCari1.getText().trim() + "%");
                ps1.setString(3, "%" + TCari1.getText().trim() + "%");
                ps1.setString(4, "%" + TCari1.getText().trim() + "%");
                ps1.setString(5, "%" + TCari1.getText().trim() + "%");
                ps1.setString(6, "%" + TCari1.getText().trim() + "%");
                ps1.setString(7, "%" + TCari1.getText().trim() + "%");
                ps1.setString(8, "%" + TCari1.getText().trim() + "%");
                ps1.setString(9, "%" + TCari1.getText().trim() + "%");
                ps1.setString(10, "%" + TCari1.getText().trim() + "%");
                ps1.setString(11, "%" + TCari1.getText().trim() + "%");
                ps1.setString(12, "%" + TCari1.getText().trim() + "%");
                ps1.setString(13, "%" + TCari1.getText().trim() + "%");
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    tabMode1.addRow(new Object[]{
                        rs1.getString("kode_brng"),
                        rs1.getString("nama_brng"),
                        rs1.getString("kode_sat"),
                        rs1.getString("satuan"),
                        rs1.getString("letak_barang"),
                        rs1.getDouble("h_beli"),
                        rs1.getDouble("ralan"),
                        rs1.getDouble("kelas1"),
                        rs1.getDouble("kelas2"),
                        rs1.getDouble("kelas3"),
                        rs1.getDouble("utama"),
                        rs1.getDouble("vip"),
                        rs1.getDouble("vvip"),
                        rs1.getDouble("beliluar"),
                        rs1.getDouble("jualbebas"),
                        rs1.getDouble("karyawan"),
                        rs1.getString("stokminimal"),
                        rs1.getString("kdjns"),
                        rs1.getString("nama"),
                        rs1.getDouble("kapasitas"),
                        rs1.getString("expire"),
                        rs1.getString("kode_industri"),
                        rs1.getString("nama_industri"),
                        rs1.getString("kode_kategori"),
                        rs1.getString("kategori"),
                        rs1.getString("kode_golongan"),
                        rs1.getString("golongan"),
                        rs1.getString("tipe_brg"),
                        rs1.getString("high_alert")
                    });
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
        LCountObatRS.setText("" + tabMode1.getRowCount());
    }
    
    private void tampilKFA() {
        Valid.tabelKosong(tabMode2);
        try {
            ps2 = koneksi.prepareStatement("SELECT * FROM satu_sehat_kfa_master where kfaCode not in (select obat_code from satu_sehat_mapping_obat) and name like ? order by name");
            try {
                ps2.setString(1, "%" + TCari2.getText().trim() + "%");
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    tabMode2.addRow(new String[]{
                        rs2.getString("kfaCode"),
                        rs2.getString("name"),
                        rs2.getString("namaDagang").replaceAll("null", "-"),
                        rs2.getString("manufacturer").replaceAll("null", "-"),
                        rs2.getString("registrar").replaceAll("null", "-"),
                        rs2.getString("generik").replaceAll("null", "-").replaceAll("true", "Ya").replaceAll("false", "Tidak"),
                        rs2.getString("dosePerUnit").replaceAll("null", "-"),
                        rs2.getString("dosageFormCode").replaceAll("null", "-"),
                        rs2.getString("dosageFormName").replaceAll("null", "-"),
                        rs2.getString("netWeightUomName").replaceAll("null", "-")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs2 != null) {
                    rs2.close();
                }
                if (ps2 != null) {
                    ps2.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCountKFA.setText("" + tabMode2.getRowCount());
    }
    
    private void getDataKFA() {
        if (tbKFA.getSelectedRow() != -1) {
            KFACode.setText(tbKFA.getValueAt(tbKFA.getSelectedRow(), 0).toString());
            KFADisplay.setText(tbKFA.getValueAt(tbKFA.getSelectedRow(), 1).toString());
            FormCode.setText(tbKFA.getValueAt(tbKFA.getSelectedRow(), 7).toString());
            FormDisplay.setText(tbKFA.getValueAt(tbKFA.getSelectedRow(), 8).toString());
            NumoratorCode.setText(tbKFA.getValueAt(tbKFA.getSelectedRow(), 9).toString());
        }
    }
    
    private void miningKFA() {
        link = "https://api-satusehat.kemkes.go.id/kfa-v2/";
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Authorization", "Bearer " + api.TokenSatuSehat());
            requestEntity = new HttpEntity(headers);            
            int limit = 100;
            for (int i = 1; i <= 180; i++) {
                System.out.println("Iterasi ke : " + i);
                json = api.getRest().exchange(link + "products/all?page=" + i + "&size=" + limit + "&product_type=farmasi", HttpMethod.GET, requestEntity, String.class).getBody();
                root = mapper.readTree(json);
                System.out.println("JSON : " + json);
                JsonNode dataArray = root.path("items").path("data");
                for (JsonNode data : dataArray) {
                    // Extract all fields and create a DataObject instance
                    String name = data.path("name").asText();
                    String kfaCode = data.path("kfa_code").asText();
                    String active = data.path("active").asText();
                    String state = data.path("state").asText();
                    String image = data.path("image").asText();
                    String updatedAt = data.path("updated_at").asText();
                    String produksiBuatan = data.path("produksi_buatan").asText();
                    String nie = data.path("nie").asText();
                    String namaDagang = data.path("nama_dagang").asText();
                    String manufacturer = data.path("manufacturer").asText();
                    String registrar = data.path("registrar").asText();
                    String generik = data.path("generik").asText();
                    String rxterm = data.path("rxterm").asText();
                    String dosePerUnit = data.path("dose_per_unit").asText();
                    String fixPrice = data.path("fix_price").asText();
                    String hetPrice = data.path("het_price").asText();
                    String farmalkesHscode = data.path("farmalkes_hscode").asText();
                    String tayangLkpp = data.path("tayang_lkpp").asText();
                    String kodeLkpp = data.path("kode_lkpp").asText();
                    String netWeight = data.path("net_weight").asText();
                    String netWeightUomName = data.path("net_weight_uom_name").asText();
                    String volume = data.path("volume").asText();
                    String volumeUomName = data.path("volume_uom_name").asText();
                    String dosageFormCode = data.path("dosage_form").path("code").asText();
                    String dosageFormName = data.path("dosage_form").path("name").asText();
                    String productTemplateKfaCode = data.path("product_template").path("kfa_code").asText();
                    String productTemplateName = data.path("product_template").path("name").asText();
                    String productTemplateState = data.path("product_template").path("state").asText();
                    String productTemplateActive = data.path("product_template").path("active").asText();
                    String productTemplateDisplayName = data.path("product_template").path("display_name").asText();
                    String productTemplateUpdatedAt = data.path("product_template").path("updated_at").asText();

                    // Store data into MySQL table using Sequel.menyimpan method
                    if (Sequel.menyimpantf2("satu_sehat_kfa_master", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "Kfa Master", 31, new String[]{
                        name, kfaCode, active, state, image, updatedAt, produksiBuatan, nie, namaDagang, manufacturer, registrar, generik, rxterm, dosePerUnit, fixPrice,
                        hetPrice, farmalkesHscode, tayangLkpp, kodeLkpp, netWeight, netWeightUomName, volume, volumeUomName, dosageFormCode, dosageFormName, productTemplateKfaCode,
                        productTemplateName, productTemplateState, productTemplateActive, productTemplateDisplayName, productTemplateUpdatedAt}
                    ) == true) {
                        System.out.println("Sukses menyimpan : " + name);
                    } else {
                        System.out.println("gagal simpan, duplicate");
                    }
                }
                Thread.sleep(5000);
            }
        } catch (Exception ea) {
            System.out.println("Notifikasi Bridging : " + ea);
            miningKFA();
        }
    }
}
