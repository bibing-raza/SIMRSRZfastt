/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bridging;
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

/**
 *
 * @author dosen
 */
public final class SatuSehatMapingObatAlkes extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1, tabMode2;
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Connection koneksi = koneksiDB.condb();
    private PreparedStatement ps, ps1, ps2;
    private ResultSet rs, rs1, rs2;
    private int i = 0;

    /** Creates new form DlgJnsPerawatanRalan
     * @param parent
     * @param modal */
    public SatuSehatMapingObatAlkes(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(8,1);
        setSize(628,674);

        tabMode= new DefaultTableModel(null, new Object[]{
            "KFA Code", "KFA System", "Kode Barang", "Nama Obat/Alkes/BHP", "KFA Display", "Form Code",
            "Form System", "Form Display", "Numerator Code", "Numerator System", "Denominator Code",
            "Denominator System"
            }){
             @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        
        tbMaping.setModel(tabMode);
        tbMaping.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbMaping.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 12; i++) {
            TableColumn column = tbMaping.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(80);
            } else if (i == 1) {
                column.setPreferredWidth(200);
            } else if (i == 2) {
                column.setPreferredWidth(85);
            } else if (i == 3) {
                column.setPreferredWidth(200);
            } else if (i == 4) {
                column.setPreferredWidth(200);
            } else if (i == 5) {
                column.setPreferredWidth(80);
            } else if (i == 6) {
                column.setPreferredWidth(200);
            } else if (i == 7) {
                column.setPreferredWidth(170);
            } else if (i == 8) {
                column.setPreferredWidth(90);
            } else if (i == 9) {
                column.setPreferredWidth(200);
            } else if (i == 10) {
                column.setPreferredWidth(100);
            } else if (i == 11) {
                column.setPreferredWidth(200);
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
        
        tabMode2= new DefaultTableModel(null, new Object[]{
            "Kode KFA", "Obat Display", "Form Kode", "Form Display", "Numerator Kode",
            "Numerator Sistem", "Denominator Kode", "Denominator Sistem"
            }){
             @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        
        tbKFA.setModel(tabMode2);
        tbKFA.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbKFA.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 8; i++) {
            TableColumn column = tbKFA.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(80);
            } else if (i == 1) {
                column.setPreferredWidth(350);
            } else if (i == 2) {
                column.setPreferredWidth(75);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else if (i == 4) {
                column.setPreferredWidth(106);
            } else if (i == 5) {
                column.setPreferredWidth(140);
            } else if (i == 6) {
                column.setPreferredWidth(115);
            } else if (i == 7) {
                column.setPreferredWidth(400);
            } 
        }
        tbKFA.setDefaultRenderer(Object.class, new WarnaTable());

//        KodeBarang.setDocument(new batasInput((byte) 15).getKata(KodeBarang));
//        KFACode.setDocument(new batasInput((byte) 15).getKata(KFACode));
//        KFASystem.setDocument(new batasInput((byte) 100).getKata(KFASystem));
//        KFADisplay.setDocument(new batasInput((byte) 80).getKata(KFADisplay));
//        FormCode.setDocument(new batasInput((byte) 30).getKata(FormCode));
//        FormSystem.setDocument(new batasInput((byte) 100).getKata(FormSystem));
//        FormDisplay.setDocument(new batasInput((byte) 80).getKata(FormDisplay));
//        NumoratorCode.setDocument(new batasInput((byte) 15).getKata(NumoratorCode));
//        NemeratorSystem.setDocument(new batasInput((byte) 80).getKata(NemeratorSystem));
//        DenominatorCode.setDocument(new batasInput((byte) 15).getKata(DenominatorCode));
//        DenominatorSystem.setDocument(new batasInput((byte) 80).getKata(DenominatorSystem));
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
        
        ChkInput.setSelected(true);
        isForm();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        NamaBarang = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        PanelInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        FormInput = new widget.PanelBiasa();
        jLabel4 = new widget.Label();
        KodeBarang = new widget.TextBox();
        jLabel5 = new widget.Label();
        FormCode = new widget.TextBox();
        jLabel8 = new widget.Label();
        NumoratorCode = new widget.TextBox();
        KFACode = new widget.TextBox();
        jLabel9 = new widget.Label();
        jLabel10 = new widget.Label();
        KFADisplay = new widget.TextBox();
        jLabel11 = new widget.Label();
        FormSystem = new widget.TextBox();
        FormDisplay = new widget.TextBox();
        jLabel12 = new widget.Label();
        jLabel13 = new widget.Label();
        jLabel14 = new widget.Label();
        NemeratorSystem = new widget.TextBox();
        DenominatorCode = new widget.TextBox();
        KFASystem = new widget.TextBox();
        jLabel15 = new widget.Label();
        DenominatorSystem = new widget.TextBox();
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
        Scroll2 = new widget.ScrollPane();
        tbObatRS = new widget.Table();
        panelGlass12 = new widget.panelisi();
        jLabel16 = new widget.Label();
        TCari1 = new widget.TextBox();
        BtnCari1 = new widget.Button();
        panelGlass8 = new widget.panelisi();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();

        NamaBarang.setEditable(false);
        NamaBarang.setHighlighter(null);
        NamaBarang.setName("NamaBarang"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Data Mapping Obat/Alkes/BHP Satu Sehat ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(660, 215));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        ChkInput.setForeground(new java.awt.Color(0, 0, 0));
        ChkInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setMnemonic('I');
        ChkInput.setText(".: Input Data");
        ChkInput.setToolTipText("Alt+I");
        ChkInput.setBorderPainted(true);
        ChkInput.setBorderPaintedFlat(true);
        ChkInput.setFocusable(false);
        ChkInput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInput.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput.setName("ChkInput"); // NOI18N
        ChkInput.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInput.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInputActionPerformed(evt);
            }
        });
        PanelInput.add(ChkInput, java.awt.BorderLayout.PAGE_END);

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(100, 104));
        FormInput.setLayout(null);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("KFA System :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(345, 10, 80, 23);

        KodeBarang.setEditable(false);
        KodeBarang.setForeground(new java.awt.Color(0, 0, 0));
        KodeBarang.setName("KodeBarang"); // NOI18N
        FormInput.add(KodeBarang);
        KodeBarang.setBounds(212, 10, 130, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Form Code :");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(0, 70, 105, 23);

        FormCode.setEditable(false);
        FormCode.setForeground(new java.awt.Color(0, 0, 0));
        FormCode.setName("FormCode"); // NOI18N
        FormCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FormCodeKeyPressed(evt);
            }
        });
        FormInput.add(FormCode);
        FormCode.setBounds(109, 70, 80, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Numerator Code :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(0, 130, 105, 23);

        NumoratorCode.setEditable(false);
        NumoratorCode.setForeground(new java.awt.Color(0, 0, 0));
        NumoratorCode.setName("NumoratorCode"); // NOI18N
        NumoratorCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NumoratorCodeKeyPressed(evt);
            }
        });
        FormInput.add(NumoratorCode);
        NumoratorCode.setBounds(109, 130, 70, 23);

        KFACode.setEditable(false);
        KFACode.setForeground(new java.awt.Color(0, 0, 0));
        KFACode.setName("KFACode"); // NOI18N
        FormInput.add(KFACode);
        KFACode.setBounds(109, 10, 100, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("KFA Code :");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(0, 10, 105, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("KFA Display :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 40, 105, 23);

        KFADisplay.setEditable(false);
        KFADisplay.setForeground(new java.awt.Color(0, 0, 0));
        KFADisplay.setName("KFADisplay"); // NOI18N
        KFADisplay.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KFADisplayKeyPressed(evt);
            }
        });
        FormInput.add(KFADisplay);
        KFADisplay.setBounds(109, 40, 615, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Form System :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(192, 70, 90, 23);

        FormSystem.setEditable(false);
        FormSystem.setForeground(new java.awt.Color(0, 0, 0));
        FormSystem.setName("FormSystem"); // NOI18N
        FormSystem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FormSystemKeyPressed(evt);
            }
        });
        FormInput.add(FormSystem);
        FormSystem.setBounds(286, 70, 438, 23);

        FormDisplay.setEditable(false);
        FormDisplay.setForeground(new java.awt.Color(0, 0, 0));
        FormDisplay.setName("FormDisplay"); // NOI18N
        FormDisplay.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FormDisplayKeyPressed(evt);
            }
        });
        FormInput.add(FormDisplay);
        FormDisplay.setBounds(109, 100, 615, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Form Display :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(0, 100, 105, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Denomina Code :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(0, 160, 105, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Numerator System :");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(180, 130, 130, 23);

        NemeratorSystem.setEditable(false);
        NemeratorSystem.setForeground(new java.awt.Color(0, 0, 0));
        NemeratorSystem.setName("NemeratorSystem"); // NOI18N
        NemeratorSystem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NemeratorSystemKeyPressed(evt);
            }
        });
        FormInput.add(NemeratorSystem);
        NemeratorSystem.setBounds(314, 130, 410, 23);

        DenominatorCode.setEditable(false);
        DenominatorCode.setForeground(new java.awt.Color(0, 0, 0));
        DenominatorCode.setName("DenominatorCode"); // NOI18N
        DenominatorCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DenominatorCodeKeyPressed(evt);
            }
        });
        FormInput.add(DenominatorCode);
        DenominatorCode.setBounds(109, 160, 70, 23);

        KFASystem.setEditable(false);
        KFASystem.setForeground(new java.awt.Color(0, 0, 0));
        KFASystem.setName("KFASystem"); // NOI18N
        KFASystem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KFASystemKeyPressed(evt);
            }
        });
        FormInput.add(KFASystem);
        KFASystem.setBounds(429, 10, 295, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Denominator System :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(180, 160, 130, 23);

        DenominatorSystem.setEditable(false);
        DenominatorSystem.setForeground(new java.awt.Color(0, 0, 0));
        DenominatorSystem.setHighlighter(null);
        DenominatorSystem.setName("DenominatorSystem"); // NOI18N
        DenominatorSystem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DenominatorSystemKeyPressed(evt);
            }
        });
        FormInput.add(DenominatorSystem);
        DenominatorSystem.setBounds(314, 160, 410, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

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
        panelGlass11.setLayout(new java.awt.BorderLayout());

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 300));
        panelGlass9.setLayout(new java.awt.BorderLayout());

        Scroll1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Daftar KFA Satu Sehat Kemenkes ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);
        Scroll1.setPreferredSize(new java.awt.Dimension(452, 255));

        tbKFA.setToolTipText("Silahkan klik untuk memilih data yang mau dimapping dengan obat RS");
        tbKFA.setName("tbKFA"); // NOI18N
        tbKFA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbKFAMouseClicked(evt);
            }
        });
        Scroll1.setViewportView(tbKFA);

        panelGlass9.add(Scroll1, java.awt.BorderLayout.PAGE_START);

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

        panelGlass9.add(panelGlass13, java.awt.BorderLayout.PAGE_END);

        panelGlass11.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        Scroll2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Daftar Obat/Alkes Farmasi RS ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);
        Scroll2.setPreferredSize(new java.awt.Dimension(452, 300));

        tbObatRS.setToolTipText("Silahkan klik untuk memilih data yang mau dimapping dengan standar KFA");
        tbObatRS.setName("tbObatRS"); // NOI18N
        tbObatRS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbObatRSMouseClicked(evt);
            }
        });
        Scroll2.setViewportView(tbObatRS);

        panelGlass11.add(Scroll2, java.awt.BorderLayout.CENTER);

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

        panelGlass11.add(panelGlass12, java.awt.BorderLayout.PAGE_END);

        panelGlass10.add(panelGlass11);

        internalFrame1.add(panelGlass10, java.awt.BorderLayout.CENTER);

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 7));

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

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass8.add(LCount);

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

        internalFrame1.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (KFACode.getText().trim().equals("")) {
            Valid.textKosong(KFACode, "KFA Code");
        } else if (KFASystem.getText().trim().equals("")) {
            Valid.textKosong(KFASystem, "KFA System");
        } else if (NamaBarang.getText().trim().equals("")) {
            Valid.textKosong(NamaBarang, "Obat/Alkes/BHP");
        } else if (KFADisplay.getText().trim().equals("")) {
            Valid.textKosong(KFADisplay, "KFA Display");
        } else if (FormCode.getText().trim().equals("")) {
            Valid.textKosong(FormCode, "Form Code");
        } else if (FormSystem.getText().trim().equals("")) {
            Valid.textKosong(FormSystem, "Form System");
        } else if (FormDisplay.getText().trim().equals("")) {
            Valid.textKosong(FormDisplay, "Form Display");
        } else if (NumoratorCode.getText().trim().equals("")) {
            Valid.textKosong(NumoratorCode, "Numorator Code");
        } else if (NemeratorSystem.getText().trim().equals("")) {
            Valid.textKosong(NemeratorSystem, "Nemerator System");
        } else if (DenominatorCode.getText().trim().equals("")) {
            Valid.textKosong(DenominatorCode, "Denominator Code");
        } else if (DenominatorSystem.getText().trim().equals("")) {
            Valid.textKosong(DenominatorSystem, "Denominator System");
        } else {
            if (Sequel.menyimpantf("satu_sehat_mapping_obat", "?,?,?,?,?,?,?,?,?,?,?", "Mapping KFA", 11, new String[]{
                KodeBarang.getText(), KFACode.getText(), KFASystem.getText(), KFADisplay.getText(), FormCode.getText(),
                FormSystem.getText(), FormDisplay.getText(), NumoratorCode.getText(), NemeratorSystem.getText(), DenominatorCode.getText(),
                DenominatorSystem.getText()
            }) == true) {
                tabMode.addRow(new String[]{
                    KFACode.getText(), KFASystem.getText(), KodeBarang.getText(), NamaBarang.getText(), KFADisplay.getText(), FormCode.getText(),
                    FormSystem.getText(), FormDisplay.getText(), NumoratorCode.getText(), NemeratorSystem.getText(), DenominatorCode.getText(),
                    DenominatorSystem.getText()
                });
                emptTeks();
                LCount.setText("" + tabMode.getRowCount());
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{Valid.pindah(evt,DenominatorCode, BtnBatal);}
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
        if(Valid.hapusTabletf(tabMode,KodeBarang,"satu_sehat_mapping_obat","kode_brng")==true){
            tabMode.removeRow(tbMaping.getSelectedRow());
            emptTeks();
            LCount.setText(""+tabMode.getRowCount());
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
        if (KFACode.getText().trim().equals("")) {
            Valid.textKosong(KFACode, "KFA Code");
        } else if (KFASystem.getText().trim().equals("")) {
            Valid.textKosong(KFASystem, "KFA System");
        } else if (KodeBarang.getText().trim().equals("")) {
            Valid.textKosong(KodeBarang, "Obat/Alkes/BHP");
        } else if (KFADisplay.getText().trim().equals("")) {
            Valid.textKosong(KFADisplay, "KFA Display");
        } else if (FormCode.getText().trim().equals("")) {
            Valid.textKosong(FormCode, "Form Code");
        } else if (FormSystem.getText().trim().equals("")) {
            Valid.textKosong(FormSystem, "Form System");
        } else if (FormDisplay.getText().trim().equals("")) {
            Valid.textKosong(FormDisplay, "Form Display");
        } else if (NumoratorCode.getText().trim().equals("")) {
            Valid.textKosong(NumoratorCode, "Numorator Code");
        } else if (NemeratorSystem.getText().trim().equals("")) {
            Valid.textKosong(NemeratorSystem, "Nemerator System");
        } else if (DenominatorCode.getText().trim().equals("")) {
            Valid.textKosong(DenominatorCode, "Denominator Code");
        } else if (DenominatorSystem.getText().trim().equals("")) {
            Valid.textKosong(DenominatorSystem, "Denominator System");
        } else {
            if (tbMaping.getSelectedRow() > -1) {
                if (Sequel.mengedittf("satu_sehat_mapping_obat", "kode_brng=?", "kode_brng=?,obat_code=?,obat_system=?,obat_display=?,"
                        + "form_code=?,form_system=?,form_display=?,numerator_code=?,numerator_system=?,denominator_code=?,denominator_system=?", 12, new String[]{
                            KodeBarang.getText(), KFACode.getText(), KFASystem.getText(), KFADisplay.getText(), FormCode.getText(),
                            FormSystem.getText(), FormDisplay.getText(), NumoratorCode.getText(), NemeratorSystem.getText(), DenominatorCode.getText(),
                            DenominatorSystem.getText(), tbMaping.getValueAt(tbMaping.getSelectedRow(), 2).toString()
                        }) == true) {
                    tabMode.setValueAt(KFACode.getText(), tbMaping.getSelectedRow(), 0);
                    tabMode.setValueAt(KFASystem.getText(), tbMaping.getSelectedRow(), 1);
                    tabMode.setValueAt(KodeBarang.getText(), tbMaping.getSelectedRow(), 2);
                    tabMode.setValueAt(NamaBarang.getText(), tbMaping.getSelectedRow(), 3);
                    tabMode.setValueAt(KFADisplay.getText(), tbMaping.getSelectedRow(), 4);
                    tabMode.setValueAt(FormCode.getText(), tbMaping.getSelectedRow(), 5);
                    tabMode.setValueAt(FormSystem.getText(), tbMaping.getSelectedRow(), 6);
                    tabMode.setValueAt(FormDisplay.getText(), tbMaping.getSelectedRow(), 7);
                    tabMode.setValueAt(NumoratorCode.getText(), tbMaping.getSelectedRow(), 8);
                    tabMode.setValueAt(NemeratorSystem.getText(), tbMaping.getSelectedRow(), 9);
                    tabMode.setValueAt(DenominatorCode.getText(), tbMaping.getSelectedRow(), 10);
                    tabMode.setValueAt(DenominatorSystem.getText(), tbMaping.getSelectedRow(), 11);
                    emptTeks();
                }
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

    private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
        isForm();
    }//GEN-LAST:event_ChkInputActionPerformed

    private void KFASystemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KFASystemKeyPressed
        Valid.pindah(evt, KFACode, KFASystem);
    }//GEN-LAST:event_KFASystemKeyPressed

    private void KFADisplayKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KFADisplayKeyPressed
        Valid.pindah(evt, KFASystem, FormCode);
    }//GEN-LAST:event_KFADisplayKeyPressed

    private void FormCodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FormCodeKeyPressed
        Valid.pindah(evt, KFADisplay, FormSystem);
    }//GEN-LAST:event_FormCodeKeyPressed

    private void FormSystemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FormSystemKeyPressed
        Valid.pindah(evt, FormCode, FormDisplay);
    }//GEN-LAST:event_FormSystemKeyPressed

    private void FormDisplayKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FormDisplayKeyPressed
        Valid.pindah(evt, FormSystem, NumoratorCode);
    }//GEN-LAST:event_FormDisplayKeyPressed

    private void NumoratorCodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NumoratorCodeKeyPressed
        Valid.pindah(evt, FormDisplay, NemeratorSystem);
    }//GEN-LAST:event_NumoratorCodeKeyPressed

    private void NemeratorSystemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NemeratorSystemKeyPressed
        Valid.pindah(evt, NumoratorCode, DenominatorCode);
    }//GEN-LAST:event_NemeratorSystemKeyPressed

    private void DenominatorCodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DenominatorCodeKeyPressed
        Valid.pindah(evt, NemeratorSystem, BtnSimpan);
    }//GEN-LAST:event_DenominatorCodeKeyPressed

    private void DenominatorSystemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DenominatorSystemKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DenominatorSystemKeyPressed

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
                NamaBarang.setText(tbObatRS.getValueAt(tbObatRS.getSelectedRow(),1).toString());
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
    private widget.Button BtnSimpan;
    private widget.CekBox ChkInput;
    private widget.TextBox DenominatorCode;
    private widget.TextBox DenominatorSystem;
    private widget.TextBox FormCode;
    private widget.TextBox FormDisplay;
    private widget.PanelBiasa FormInput;
    private widget.TextBox FormSystem;
    private widget.TextBox KFACode;
    private widget.TextBox KFADisplay;
    private widget.TextBox KFASystem;
    private widget.TextBox KodeBarang;
    private widget.Label LCount;
    private widget.TextBox NamaBarang;
    private widget.TextBox NemeratorSystem;
    private widget.TextBox NumoratorCode;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    private widget.TextBox TCari;
    private widget.TextBox TCari1;
    private widget.TextBox TCari2;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass12;
    private widget.panelisi panelGlass13;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.Table tbKFA;
    private widget.Table tbMaping;
    private widget.Table tbObatRS;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try{
           ps=koneksi.prepareStatement(
                   "select satu_sehat_mapping_obat.kode_brng,databarang.nama_brng,satu_sehat_mapping_obat.obat_code,satu_sehat_mapping_obat.obat_system,"+
                   "satu_sehat_mapping_obat.obat_display,satu_sehat_mapping_obat.form_code,satu_sehat_mapping_obat.form_system,"+
                   "satu_sehat_mapping_obat.form_display,satu_sehat_mapping_obat.numerator_code,satu_sehat_mapping_obat.numerator_system,"+
                   "satu_sehat_mapping_obat.denominator_code,satu_sehat_mapping_obat.denominator_system from satu_sehat_mapping_obat inner join databarang "+
                   "on satu_sehat_mapping_obat.kode_brng=databarang.kode_brng "+
                   (TCari.getText().equals("")?"":"where satu_sehat_mapping_obat.kode_brng like ? or databarang.nama_brng like ? or "+
                   "satu_sehat_mapping_obat.obat_code like ? or satu_sehat_mapping_obat.obat_display like ? or satu_sehat_mapping_obat.form_display like ? ")+
                   " order by satu_sehat_mapping_obat.obat_code");
            try {
                if(!TCari.getText().equals("")){
                    ps.setString(1,"%"+TCari.getText()+"%");
                    ps.setString(2,"%"+TCari.getText()+"%");
                    ps.setString(3,"%"+TCari.getText()+"%");
                    ps.setString(4,"%"+TCari.getText()+"%");
                    ps.setString(5,"%"+TCari.getText()+"%");
                }
                rs=ps.executeQuery();
                while(rs.next()){
                    tabMode.addRow(new Object[]{
                        rs.getString("obat_code"),rs.getString("obat_system"),rs.getString("kode_brng"),rs.getString("nama_brng"),rs.getString("obat_display"),
                        rs.getString("form_code"),rs.getString("form_system"),rs.getString("form_display"),rs.getString("numerator_code"),rs.getString("numerator_system"),
                        rs.getString("denominator_code"),rs.getString("denominator_system")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif Ketersediaan : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
        LCount.setText(""+tabMode.getRowCount());
    }

    public void emptTeks() {
        KFACode.setText("");
        KFASystem.setText("");
        KodeBarang.setText("");
        NamaBarang.setText("");
        KFADisplay.setText("");
        FormCode.setText("");
        FormSystem.setText("");
        FormDisplay.setText("");
        NumoratorCode.setText("");
        NemeratorSystem.setText("");
        DenominatorCode.setText("");
        DenominatorSystem.setText("");
        ChkInput.setSelected(true);
        TCari1.setText("");
        TCari2.setText("");
        Valid.tabelKosong(tabMode1);
        Valid.tabelKosong(tabMode2);
        isForm();
        KFACode.requestFocus();
    }

    private void getData() {
       if(tbMaping.getSelectedRow()!= -1){
           KFACode.setText(tbMaping.getValueAt(tbMaping.getSelectedRow(),0).toString());
           KFASystem.setText(tbMaping.getValueAt(tbMaping.getSelectedRow(),1).toString());
           KodeBarang.setText(tbMaping.getValueAt(tbMaping.getSelectedRow(),2).toString());
           NamaBarang.setText(tbMaping.getValueAt(tbMaping.getSelectedRow(),3).toString());
           KFADisplay.setText(tbMaping.getValueAt(tbMaping.getSelectedRow(),4).toString());
           FormCode.setText(tbMaping.getValueAt(tbMaping.getSelectedRow(),5).toString());
           FormSystem.setText(tbMaping.getValueAt(tbMaping.getSelectedRow(),6).toString());
           FormDisplay.setText(tbMaping.getValueAt(tbMaping.getSelectedRow(),7).toString());
           NumoratorCode.setText(tbMaping.getValueAt(tbMaping.getSelectedRow(),8).toString());
           NemeratorSystem.setText(tbMaping.getValueAt(tbMaping.getSelectedRow(),9).toString());
           DenominatorCode.setText(tbMaping.getValueAt(tbMaping.getSelectedRow(),10).toString());
           DenominatorSystem.setText(tbMaping.getValueAt(tbMaping.getSelectedRow(),11).toString());
        }
    }
    
    public void isCek(){
        BtnSimpan.setEnabled(akses.getobat());
        BtnHapus.setEnabled(akses.getobat());
        BtnEdit.setEnabled(akses.getobat());
    }
    
    private void isForm() {
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 215));
            FormInput.setVisible(true);
            ChkInput.setVisible(true);
        } else if (ChkInput.isSelected() == false) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 20));
            FormInput.setVisible(false);
            ChkInput.setVisible(true);
        }
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
                    + "db.status='1' and db.kode_brng like ? or "
                    + "db.status='1' and db.nama_brng like ? or "
                    + "db.status='1' and db.kode_sat like ? or "
                    + "db.status='1' and ks.satuan like ? or "
                    + "db.status='1' and db.letak_barang like ? or "
                    + "db.status='1' and db.kdjns like ? or "
                    + "db.status='1' and kb.nama like ? or "
                    + "db.status='1' and gb.nama like ? or "
                    + "db.status='1' and j.nama like ? or "
                    + "db.status='1' and db.kode_industri like ? or "
                    + "db.status='1' and db.tipe_brg like ? or "
                    + "db.status='1' and db.high_alert like ? or "
                    + "db.status='1' and ifm.nama_industri like ? order by db.nama_brng");
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
    }
    
    private void tampilKFA() {
        Valid.tabelKosong(tabMode2);
        try {
            ps2 = koneksi.prepareStatement("SELECT * FROM master_kfa_kemenkes where display_name like ? order by display_name");
            try {
                ps2.setString(1, "%" + TCari2.getText().trim() + "%");
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    tabMode2.addRow(new String[]{
                        rs2.getString("kode_kfa"),
                        rs2.getString("display_name"),
                        rs2.getString("bentuk_sediaan_kode"),
                        rs2.getString("bentuk_sediaan_display_name"),
                        rs2.getString("bba_satuan"),
                        rs2.getString("bba_code_system"),
                        rs2.getString("bba_satuan_disesuaikan"),
                        rs2.getString("bba_code_system_disesuaikan")
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
    }
    
    private void getDataKFA() {
        if (tbKFA.getSelectedRow() != -1) {
            KFACode.setText(tbKFA.getValueAt(tbKFA.getSelectedRow(), 0).toString());
            KFADisplay.setText(tbKFA.getValueAt(tbKFA.getSelectedRow(), 1).toString());
            KFASystem.setText("http://sys-ids.kemkes.go.id/kfa");
            FormCode.setText(tbKFA.getValueAt(tbKFA.getSelectedRow(), 2).toString());
            FormSystem.setText("http://terminology.kemkes.go.id/CodeSystem/medication-form");
            FormDisplay.setText(tbKFA.getValueAt(tbKFA.getSelectedRow(), 3).toString());
            NumoratorCode.setText(tbKFA.getValueAt(tbKFA.getSelectedRow(), 4).toString());
            NemeratorSystem.setText(tbKFA.getValueAt(tbKFA.getSelectedRow(), 5).toString());
            DenominatorCode.setText(tbKFA.getValueAt(tbKFA.getSelectedRow(), 6).toString());
            DenominatorSystem.setText(tbKFA.getValueAt(tbKFA.getSelectedRow(), 7).toString());
        }
    }
}
