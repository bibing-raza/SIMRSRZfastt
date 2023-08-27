package simrskhanza;

import bridging.BPJSCekReferensiFaskes;
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
import simrskhanza.DlgCariDokter;
import simrskhanza.DlgCariPoli;
import simrskhanza.DlgPasien;

/**
 *
 * @author dosen
 */
public class DlgMasterFaskes extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i=0, g=0;
    private DlgCariDokter dokter=new DlgCariDokter(null,false);
    private DlgCariPoli poli=new DlgCariPoli(null,false);
    private DlgPasien pasien=new DlgPasien(null,false);
    private BPJSCekReferensiFaskes faskes_bpjs=new BPJSCekReferensiFaskes(null,false);
    private String URUTNOREG="",status="",no_rawat="",umur="",sttsumur="";

    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public DlgMasterFaskes(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode=new DefaultTableModel(null,new Object[]{
            "No.","Kode Rujukan","Kode Faskes","Nama Faskes/Perujuk","Status","Tipe Faskes","Alamatnya",
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if (colIndex==0) {
                    a=true;
                }
                return a;
             }
             Class[] types = new Class[] {
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                 java.lang.Object.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbFaskes.setModel(tabMode);

        tbFaskes.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbFaskes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 7; i++) {
            TableColumn column = tbFaskes.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(35);
            }else if(i==1){
                column.setPreferredWidth(80);
            }else if(i==2){
                column.setPreferredWidth(80);
            }else if(i==3){
                column.setPreferredWidth(250);
            }else if(i==4){
                column.setPreferredWidth(70);
            }else if(i==5){
                column.setPreferredWidth(120);
            }else if(i==6){
                column.setPreferredWidth(350);
            }
        }
        tbFaskes.setDefaultRenderer(Object.class, new WarnaTable());


        TCari.setDocument(new batasInput((byte)100).getKata(TCari));
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
        
        ChkInput.setSelected(false);
        isForm();
        
        faskes_bpjs.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(faskes_bpjs.getTable().getSelectedRow()!= -1){                    
                    kdfaskes.setText(faskes_bpjs.getTable().getValueAt(faskes_bpjs.getTable().getSelectedRow(), 1).toString());
                    nmfaskes.setText(faskes_bpjs.getTable().getValueAt(faskes_bpjs.getTable().getSelectedRow(), 2).toString());
                    nmfaskes.requestFocus();                    
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
        
        faskes_bpjs.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    faskes_bpjs.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            URUTNOREG=prop.getProperty("URUTNOREG");
        } catch (Exception ex) {
            URUTNOREG="";
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        kdstatus = new widget.TextBox();
        kdrujukan = new widget.TextBox();
        cekkdfaskes = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbFaskes = new widget.Table();
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
        PanelInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        FormInput = new widget.PanelBiasa();
        jLabel4 = new widget.Label();
        kdfaskes = new widget.TextBox();
        jLabel9 = new widget.Label();
        nmfaskes = new widget.TextBox();
        jLabel11 = new widget.Label();
        alamat = new widget.TextBox();
        BtnFaskes = new widget.Button();
        jLabel12 = new widget.Label();
        jLabel13 = new widget.Label();
        cmbTipe = new widget.ComboBox();
        cmbStatus = new widget.ComboBox();

        kdstatus.setForeground(new java.awt.Color(0, 0, 0));
        kdstatus.setHighlighter(null);
        kdstatus.setName("kdstatus"); // NOI18N
        kdstatus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdstatusKeyPressed(evt);
            }
        });

        kdrujukan.setForeground(new java.awt.Color(0, 0, 0));
        kdrujukan.setHighlighter(null);
        kdrujukan.setName("kdrujukan"); // NOI18N
        kdrujukan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdrujukanKeyPressed(evt);
            }
        });

        cekkdfaskes.setForeground(new java.awt.Color(0, 0, 0));
        cekkdfaskes.setHighlighter(null);
        cekkdfaskes.setName("cekkdfaskes"); // NOI18N
        cekkdfaskes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cekkdfaskesKeyPressed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Master Data Faskes/Perujuk ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbFaskes.setAutoCreateRowSorter(true);
        tbFaskes.setToolTipText("Silahkan klik untuk memilih data yang ataupun dihapus");
        tbFaskes.setName("tbFaskes"); // NOI18N
        tbFaskes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbFaskesMouseClicked(evt);
            }
        });
        tbFaskes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbFaskesKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbFaskes);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

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
        TCari.setPreferredSize(new java.awt.Dimension(450, 23));
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

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 185));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        ChkInput.setForeground(new java.awt.Color(0, 0, 0));
        ChkInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setMnemonic('M');
        ChkInput.setText(".: Input Data");
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
        FormInput.setPreferredSize(new java.awt.Dimension(190, 107));
        FormInput.setLayout(null);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Kode Faskes : ");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(0, 10, 100, 23);

        kdfaskes.setEditable(false);
        kdfaskes.setForeground(new java.awt.Color(0, 0, 0));
        kdfaskes.setName("kdfaskes"); // NOI18N
        kdfaskes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdfaskesKeyPressed(evt);
            }
        });
        FormInput.add(kdfaskes);
        kdfaskes.setBounds(102, 10, 90, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Nama Faskes : ");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(0, 40, 100, 23);

        nmfaskes.setForeground(new java.awt.Color(0, 0, 0));
        nmfaskes.setName("nmfaskes"); // NOI18N
        nmfaskes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nmfaskesKeyPressed(evt);
            }
        });
        FormInput.add(nmfaskes);
        nmfaskes.setBounds(102, 40, 410, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Tipe Faskes : ");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(0, 70, 100, 23);

        alamat.setForeground(new java.awt.Color(0, 0, 0));
        alamat.setName("alamat"); // NOI18N
        alamat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                alamatKeyPressed(evt);
            }
        });
        FormInput.add(alamat);
        alamat.setBounds(102, 130, 510, 23);

        BtnFaskes.setForeground(new java.awt.Color(0, 0, 0));
        BtnFaskes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnFaskes.setMnemonic('X');
        BtnFaskes.setText("Faskes dari BPJS");
        BtnFaskes.setToolTipText("Alt+X");
        BtnFaskes.setName("BtnFaskes"); // NOI18N
        BtnFaskes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnFaskesActionPerformed(evt);
            }
        });
        BtnFaskes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnFaskesKeyPressed(evt);
            }
        });
        FormInput.add(BtnFaskes);
        BtnFaskes.setBounds(198, 10, 135, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Status : ");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(0, 100, 100, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Alamatnya : ");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(0, 130, 100, 23);

        cmbTipe.setForeground(new java.awt.Color(0, 0, 0));
        cmbTipe.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Rumah Sakit", "RS TNI/POLRI", "Puskesmas", "Praktek Dokter", "Praktek Dokter Gigi", "Klinik Pratama", "Klinik Utama", "Klinik TNI", "Klinik POLRI", "Apotek", "Optik", "Lainnya", "Raza" }));
        cmbTipe.setName("cmbTipe"); // NOI18N
        cmbTipe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbTipeMouseClicked(evt);
            }
        });
        cmbTipe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipeActionPerformed(evt);
            }
        });
        cmbTipe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbTipeKeyPressed(evt);
            }
        });
        FormInput.add(cmbTipe);
        cmbTipe.setBounds(102, 70, 180, 23);

        cmbStatus.setForeground(new java.awt.Color(0, 0, 0));
        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Aktif", "Non Aktif" }));
        cmbStatus.setName("cmbStatus"); // NOI18N
        cmbStatus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbStatusMouseClicked(evt);
            }
        });
        cmbStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbStatusActionPerformed(evt);
            }
        });
        cmbStatus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbStatusKeyPressed(evt);
            }
        });
        FormInput.add(cmbStatus);
        cmbStatus.setBounds(102, 100, 90, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void kdfaskesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdfaskesKeyPressed
        Valid.pindah(evt,kdfaskes,nmfaskes);        
}//GEN-LAST:event_kdfaskesKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        Sequel.cariIsi("select kode_faskes_bpjs from master_nama_rujukan where kode_faskes_bpjs=? ", cekkdfaskes, kdfaskes.getText());

        if (kdfaskes.getText().trim().equals("") || nmfaskes.getText().trim().equals("")) {
            Valid.textKosong(kdfaskes, "kode faskes");
        } else if (cmbTipe.getSelectedItem().equals("-") || cmbStatus.getSelectedItem().equals("-")) {
            Valid.textKosong(nmfaskes, "tipe atau status");
        } else if (alamat.getText().trim().equals("")) {
            Valid.textKosong(alamat, "alamatnya");
        } else if (!cekkdfaskes.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Faskes/perujuk itu sudah pernah tersimpan didatabase...!!!!");
            BtnFaskes.requestFocus();
        } else {
            Sequel.menyimpan("master_nama_rujukan", "'0','" + nmfaskes.getText() + "','" + kdstatus.getText() + "',"
                    + "'" + kdfaskes.getText() + "','" + cmbTipe.getSelectedItem() + "','" + alamat.getText() + "'", "Master data faskes/perujuk");
            emptTeks();
            tampil();
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
            Valid.pindah(evt,kdfaskes,BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
        ChkInput.setSelected(true);
        isForm(); 
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            emptTeks();
        }else{Valid.pindah(evt, BtnSimpan, BtnGanti);}
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnGantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiActionPerformed
        if (kdfaskes.getText().trim().equals("") || nmfaskes.getText().trim().equals("")) {
            Valid.textKosong(kdfaskes, "kode faskes");
        } else if (cmbTipe.getSelectedItem().equals("-") || cmbStatus.getSelectedItem().equals("-")) {
            Valid.textKosong(nmfaskes, "tipe atau status");
        } else if (alamat.getText().trim().equals("")) {
            Valid.textKosong(alamat, "alamatnya");
        } else {
            Sequel.mengedit("master_nama_rujukan", "kd_rujukan='" + kdrujukan.getText() + "'", "nama_rujukan='" + nmfaskes.getText() + "', "
                    + "status='" + kdstatus.getText() + "', kode_faskes_bpjs='" + kdfaskes.getText() + "', tipe_faskes='" + cmbTipe.getSelectedItem() + "',"
                    + "alamat_faskes='" + alamat.getText() + "' ");
            emptTeks();
            tampil();
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
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
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

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        tampil();
        emptTeks();        
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            tampil();
            TCari.setText("");
        }else{
            Valid.pindah(evt, BtnCari, kdfaskes);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbFaskesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbFaskesMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbFaskesMouseClicked

    private void tbFaskesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbFaskesKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbFaskesKeyPressed

private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
  isForm();
  BtnFaskes.requestFocus();
}//GEN-LAST:event_ChkInputActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void BtnFaskesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnFaskesActionPerformed
        faskes_bpjs.setSize(800, 600);
        faskes_bpjs.setLocationRelativeTo(internalFrame1);
        faskes_bpjs.setVisible(true);
        faskes_bpjs.fokus();
    }//GEN-LAST:event_BtnFaskesActionPerformed

    private void BtnFaskesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnFaskesKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnFaskesActionPerformed(null);
        }else{
            Valid.pindah(evt,TCari,kdfaskes);
        }   
    }//GEN-LAST:event_BtnFaskesKeyPressed

    private void cmbTipeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbTipeKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTipeKeyPressed

    private void cmbTipeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbTipeMouseClicked
        cmbTipe.setEditable(false);
        cmbStatus.requestFocus();
    }//GEN-LAST:event_cmbTipeMouseClicked

    private void cmbStatusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbStatusMouseClicked
        cmbStatus.setEditable(false);

        if (cmbStatus.getSelectedItem().equals("Aktif")) {
            kdstatus.setText("1");
        } else if (cmbStatus.getSelectedItem().equals("Non Aktif")) {
            kdstatus.setText("0");
        } else if (cmbStatus.getSelectedItem().equals("-")) {
            kdstatus.setText("");
        }

        alamat.requestFocus();
    }//GEN-LAST:event_cmbStatusMouseClicked

    private void cmbStatusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbStatusKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbStatusKeyPressed

    private void kdstatusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdstatusKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kdstatusKeyPressed

    private void cmbStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbStatusActionPerformed
        if (cmbStatus.getSelectedItem().equals("Aktif")) {
            kdstatus.setText("1");
        } else if (cmbStatus.getSelectedItem().equals("Non Aktif")) {
            kdstatus.setText("0");
        } else if (cmbStatus.getSelectedItem().equals("-")) {
            kdstatus.setText("");
        }
        
        alamat.requestFocus();
    }//GEN-LAST:event_cmbStatusActionPerformed

    private void nmfaskesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nmfaskesKeyPressed
        Valid.pindah(evt,kdfaskes,cmbTipe);
    }//GEN-LAST:event_nmfaskesKeyPressed

    private void alamatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_alamatKeyPressed
        Valid.pindah(evt,cmbStatus,BtnSimpan);
    }//GEN-LAST:event_alamatKeyPressed

    private void kdrujukanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdrujukanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kdrujukanKeyPressed

    private void cmbTipeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipeActionPerformed
        cmbStatus.requestFocus();
    }//GEN-LAST:event_cmbTipeActionPerformed

    private void cekkdfaskesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cekkdfaskesKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cekkdfaskesKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgMasterFaskes dialog = new DlgMasterFaskes(new javax.swing.JFrame(), true);
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
    private widget.Button BtnFaskes;
    private widget.Button BtnGanti;
    private widget.Button BtnKeluar;
    private widget.Button BtnSimpan;
    private widget.CekBox ChkInput;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll;
    public widget.TextBox TCari;
    private widget.TextBox alamat;
    private javax.swing.ButtonGroup buttonGroup1;
    private widget.TextBox cekkdfaskes;
    private widget.ComboBox cmbStatus;
    private widget.ComboBox cmbTipe;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel4;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private widget.TextBox kdfaskes;
    private widget.TextBox kdrujukan;
    private widget.TextBox kdstatus;
    private widget.TextBox nmfaskes;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass8;
    private widget.Table tbFaskes;
    // End of variables declaration//GEN-END:variables

    public void tampil() {     
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT kd_rujukan, kode_faskes_bpjs, nama_rujukan, "
                    +"IF(status='0','Non Aktif','Aktif') stts, tipe_faskes, alamat_faskes FROM master_nama_rujukan where "
                    + " kode_faskes_bpjs like ? or "
                    + " nama_rujukan like ? or "
                    + " IF(status='0','Non Aktif','Aktif') like ? or "
                    + " tipe_faskes like ? or "
                    + " alamat_faskes like ? "
                    + " ORDER BY kd_rujukan");

            try {                
                ps.setString(1, "%" + TCari.getText().trim() + "%");
                ps.setString(2, "%" + TCari.getText().trim() + "%");
                ps.setString(3, "%" + TCari.getText().trim() + "%");
                ps.setString(4, "%" + TCari.getText().trim() + "%");
                ps.setString(5, "%" + TCari.getText().trim() + "%");
                
                rs = ps.executeQuery();
                g = 1;
                while (rs.next()) {
                    tabMode.addRow(new Object[]{
                        g + ".",
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)
                    });
                    g++;
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println("simrskhanza.DlgMasterFaskes.tampil() : " + e);
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
        kdfaskes.setText("");
        nmfaskes.setText("");
        cmbTipe.setSelectedIndex(0);        
        cmbStatus.setSelectedIndex(0);
        alamat.setText("");
        kdstatus.setText("");
        kdrujukan.setText("");
        cekkdfaskes.setText("");
        BtnFaskes.requestFocus();
    }

    private void getData() {
        if(tbFaskes.getSelectedRow()!= -1){            
            kdfaskes.setText(tbFaskes.getValueAt(tbFaskes.getSelectedRow(),2).toString()); 
            Sequel.cariIsi("select kd_rujukan from master_nama_rujukan where kode_faskes_bpjs=? ", kdrujukan, kdfaskes.getText());
            nmfaskes.setText(tbFaskes.getValueAt(tbFaskes.getSelectedRow(),3).toString());
            cmbTipe.setSelectedItem(tbFaskes.getValueAt(tbFaskes.getSelectedRow(),5).toString());
            cmbStatus.setSelectedItem(tbFaskes.getValueAt(tbFaskes.getSelectedRow(),4).toString());
            alamat.setText(tbFaskes.getValueAt(tbFaskes.getSelectedRow(),6).toString());
            
            if (cmbStatus.getSelectedItem().equals("Aktif")) {
                kdstatus.setText("1");
            } else if (cmbStatus.getSelectedItem().equals("Non Aktif")) {
                kdstatus.setText("0");
            }
        }
    }
    
    private void isForm(){
        if(ChkInput.isSelected()==true){
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH,185));
            FormInput.setVisible(true);      
            ChkInput.setVisible(true);
        }else if(ChkInput.isSelected()==false){           
            ChkInput.setVisible(false);            
            PanelInput.setPreferredSize(new Dimension(WIDTH,20));
            FormInput.setVisible(false);      
            ChkInput.setVisible(true);
        }
    }

}
