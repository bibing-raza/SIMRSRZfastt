/*
  Dilarang keras menggandakan/mengcopy/menyebarkan/membajak/mendecompile 
  Software ini dalam bentuk apapun tanpa seijin pembuat software
  (Khanza.Soft Media). Bagi yang sengaja membajak softaware ini ta
  npa ijin, kami sumpahi sial 1000 turunan, miskin sampai 500 turu
  nan. Selalu mendapat kecelakaan sampai 400 turunan. Anak pertama
  nya cacat tidak punya kaki sampai 300 turunan. Susah cari jodoh
  sampai umur 50 tahun sampai 200 turunan. Ya Alloh maafkan kami 
  karena telah berdoa buruk, semua ini kami lakukan karena kami ti
  dak pernah rela karya kami dibajak tanpa ijin.
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
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import simrskhanza.DlgCariDokter;
import simrskhanza.DlgCariPenyakit;


/**
 *
 * @author perpustakaan
 */
public final class InhealthDataSJP extends javax.swing.JDialog {
    private DefaultTableModel tabMode;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private int pilih=0,i=0;
    private final Properties prop = new Properties();
    private SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
    private String no_peserta="", requestJson,URL="",jkel="",duplikat="",user="",kelas="";
    private DlgCariDokter dokter=new DlgCariDokter(null,false);
    private InhealthCariReferensiJenpelRuang kamar=new InhealthCariReferensiJenpelRuang(null,false);
    private InhealthCekReferensiFaskes faskes=new InhealthCekReferensiFaskes(null,false);
    private DlgCariPenyakit penyakit=new DlgCariPenyakit(null,false);
    private InhealthCekReferensiPoli poli=new InhealthCekReferensiPoli(null,false);
    
    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public InhealthDataSJP(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8,1);
        
        WindowUpdatePulang.setSize(487,73);

        tabMode=new DefaultTableModel(null,new Object[]{
                "No.SJP","No.Rawat","Tanggal SJP","No.Kartu","No.RM","Nama Pasien","Tgl.Lahir",
                "Jns.Kelamin","Tanggal Rujukan","No.Rujukan","kdppkrujukan","Nama PPK Rujukan", 
                "kdppkpelayanan","Nama PPK Pelayanan","Tanggal Pulang","Jenis Pelayanan","Catatan",
                "ICD X","Diagnosa Awal","ICD X 2","Diagnosa Tambahan","Kode Poli","Nama Poli",
                "Laka Lantas","Lokasi Laka","Petugas","Kelas Rawat","Kelas Desc","Kode BU","Nama BU",                  
                "Plan","Plan Desc","ID Akomodasi","Tipe SJP","Tipe COB",
                "Kelas Rawat","Kode Ruang","Nama Ruangan"
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbObat.setModel(tabMode);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 38; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(125);
            }else if(i==1){
                column.setPreferredWidth(105);
            }else if(i==2){
                column.setPreferredWidth(118);
            }else if(i==3){
                column.setPreferredWidth(95);
            }else if(i==4){
                column.setPreferredWidth(47);
            }else if(i==5){           
                column.setPreferredWidth(200);
            }else if(i==6){
                column.setPreferredWidth(65);
            }else if(i==7){
                column.setPreferredWidth(75);
            }else if(i==8){
                column.setPreferredWidth(118);
            }else if(i==9){
                column.setPreferredWidth(200);
            }else if(i==10){//sembunyi
//                column.setPreferredWidth(140);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==11){
                column.setPreferredWidth(180);
            }else if(i==12){//sembunyi
//                column.setPreferredWidth(150);
                column.setMinWidth(0);
                column.setMaxWidth(0);                
            }else if(i==13){//sembunyi
//                column.setPreferredWidth(100);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==14){
                column.setPreferredWidth(118);
            }else if(i==15){
                column.setPreferredWidth(200);
            }else if(i==16){
                column.setPreferredWidth(200);
            }else if(i==17){
                column.setPreferredWidth(40);
            }else if(i==18){
                column.setPreferredWidth(200);
            }else if(i==19){
                column.setPreferredWidth(45);
            }else if(i==20){
                column.setPreferredWidth(200);
            }else if(i==21){//sembunyi
                //column.setPreferredWidth(150);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==22){
                column.setPreferredWidth(200);
            }else if(i==23){
                column.setPreferredWidth(200);
            }else if(i==24){
                column.setPreferredWidth(200);
            }else if(i==25){
                column.setPreferredWidth(200);
            }else if(i==26){
                column.setPreferredWidth(75);
            }else if(i==27){
                column.setPreferredWidth(75);
            }else if(i==28){//sembunyi
//                column.setPreferredWidth(240);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==29){
                column.setPreferredWidth(250);
            }else if(i==30){//sembunyi
//                column.setPreferredWidth(30);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==31){
                column.setPreferredWidth(65);
            }else if(i==32){
                column.setPreferredWidth(90);
            }else if(i==33){
                column.setPreferredWidth(70);
            }else if(i==34){
                column.setPreferredWidth(200);
            }else if(i==35){
                column.setPreferredWidth(100);
            }else if(i==36){//sembunyi
//                column.setPreferredWidth(100);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==37){//sembunyi
//                column.setPreferredWidth(100);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());
        
        TCari.setDocument(new batasInput((byte)100).getKata(TCari));
        NoRujukan.setDocument(new batasInput((byte)20).getKata(NoRujukan));
        Catatan.setDocument(new batasInput((byte)50).getKata(Catatan));
        LokasiLaka.setDocument(new batasInput((byte)100).getKata(LokasiLaka));
        
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
        
        faskes.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(faskes.getTable().getSelectedRow()!= -1){                   
                    KdPpkRujukan.setText(faskes.getTable().getValueAt(faskes.getTable().getSelectedRow(),1).toString());
                    NmPpkRujukan.setText(faskes.getTable().getValueAt(faskes.getTable().getSelectedRow(),2).toString());
                }  
                KdPpkRujukan.requestFocus();
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
        
        faskes.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    faskes.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
        penyakit.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(penyakit.getTable().getSelectedRow()!= -1){     
                    if(pilih==1){  
                        KdPenyakit.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(),0).toString());
                        NmPenyakit.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(),1).toString());
                    }else if(pilih==2){
                        KdPenyakit2.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(),0).toString());
                        NmPenyakit2.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(),1).toString());
                    }
                        
                }  
                KdPenyakit.requestFocus();
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
        
        penyakit.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    penyakit.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });  
        
        poli.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(poli.getTable().getSelectedRow()!= -1){                   
                    KdPoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(),1).toString());
                    NmPoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(),2).toString());
                    btnPoli.requestFocus();
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
        
        poli.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    poli.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });  
        
        kamar.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(kamar.getTable().getSelectedRow()!= -1){ 
                        KdPoli.setText(kamar.getTable().getValueAt(kamar.getTable().getSelectedRow(),0).toString());  
                        KdRuang.setText(kamar.getTable().getValueAt(kamar.getTable().getSelectedRow(),3).toString());  
                        NmRuang.setText(kamar.getTable().getValueAt(kamar.getTable().getSelectedRow(),4).toString());
                        btnRuang.requestFocus();
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
        
        kamar.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    kamar.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        }); 
        
        try{
            KdPPK.setText(Sequel.cariIsi("select kode_ppkinhealth from setting")); 
            NmPPK.setText(Sequel.cariIsi("select nama_instansi from setting"));           
        }catch(Exception e){
            System.out.println(e);
        }

        try {
            user=akses.getkode().replace(" ","").substring(0,9);
        } catch (Exception e) {
            user=akses.getkode();
        }
        
        cekLAYAN();
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Popup = new javax.swing.JPopupMenu();
        ppSEP = new javax.swing.JMenuItem();
        ppPulang = new javax.swing.JMenuItem();
        ppMapping = new javax.swing.JMenuItem();
        ppDetailSEPPeserta = new javax.swing.JMenuItem();
        WindowUpdatePulang = new javax.swing.JDialog();
        internalFrame5 = new widget.InternalFrame();
        BtnCloseIn4 = new widget.Button();
        BtnSimpan4 = new widget.Button();
        jLabel26 = new widget.Label();
        TanggalPulang = new widget.Tanggal();
        NoBalasan = new widget.TextBox();
        NoSJP = new widget.TextBox();
        idAkomodasi = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbObat = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnPrint = new widget.Button();
        BtnEdit = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass9 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        PanelInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        FormInput = new widget.PanelBiasa();
        jLabel4 = new widget.Label();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        TNoRM = new widget.TextBox();
        jLabel5 = new widget.Label();
        NoKartu = new widget.TextBox();
        jLabel20 = new widget.Label();
        TanggalSEP = new widget.Tanggal();
        jLabel22 = new widget.Label();
        TanggalRujuk = new widget.Tanggal();
        jLabel23 = new widget.Label();
        NoRujukan = new widget.TextBox();
        jLabel8 = new widget.Label();
        TglLahir = new widget.TextBox();
        jLabel18 = new widget.Label();
        JK = new widget.TextBox();
        jLabel10 = new widget.Label();
        KdPPK = new widget.TextBox();
        NmPPK = new widget.TextBox();
        btnPPKRujukan = new widget.Button();
        NmPpkRujukan = new widget.TextBox();
        KdPpkRujukan = new widget.TextBox();
        jLabel11 = new widget.Label();
        jLabel15 = new widget.Label();
        KdPenyakit = new widget.TextBox();
        NmPenyakit = new widget.TextBox();
        btnDiagnosa = new widget.Button();
        btnDiagnosa1 = new widget.Button();
        NmPenyakit2 = new widget.TextBox();
        KdPenyakit2 = new widget.TextBox();
        jLabel39 = new widget.Label();
        LabelRuang = new widget.Label();
        LabelPoli = new widget.Label();
        KdPoli = new widget.TextBox();
        KdRuang = new widget.TextBox();
        labelKElas = new widget.Label();
        Kelas = new widget.ComboBox();
        Catatan = new widget.TextBox();
        jLabel33 = new widget.Label();
        jLabel34 = new widget.Label();
        LakaLantas = new widget.ComboBox();
        LokasiLaka = new widget.TextBox();
        jLabel35 = new widget.Label();
        LabelKelas = new widget.Label();
        JenisPelayanan = new widget.ComboBox();
        NmPoli = new widget.TextBox();
        btnPoli = new widget.Button();
        NmRuang = new widget.TextBox();
        jLabel9 = new widget.Label();
        klsPlanDes = new widget.TextBox();
        jLabel12 = new widget.Label();
        tipeCOB = new widget.TextBox();
        jLabel13 = new widget.Label();
        tipeSJP = new widget.TextBox();
        btnRuang = new widget.Button();

        Popup.setName("Popup"); // NOI18N

        ppSEP.setBackground(new java.awt.Color(242, 242, 242));
        ppSEP.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppSEP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppSEP.setText("Print SJP");
        ppSEP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppSEP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppSEP.setIconTextGap(8);
        ppSEP.setName("ppSEP"); // NOI18N
        ppSEP.setPreferredSize(new java.awt.Dimension(200, 25));
        ppSEP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppSEPBtnPrintActionPerformed(evt);
            }
        });
        Popup.add(ppSEP);

        ppPulang.setBackground(new java.awt.Color(242, 242, 242));
        ppPulang.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppPulang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppPulang.setText("Update Tanggal Pulang");
        ppPulang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppPulang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppPulang.setIconTextGap(8);
        ppPulang.setName("ppPulang"); // NOI18N
        ppPulang.setPreferredSize(new java.awt.Dimension(200, 25));
        ppPulang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppPulangBtnPrintActionPerformed(evt);
            }
        });
        Popup.add(ppPulang);

        ppMapping.setBackground(new java.awt.Color(242, 242, 242));
        ppMapping.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppMapping.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppMapping.setText("Mapping Transaksi SJP");
        ppMapping.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppMapping.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppMapping.setIconTextGap(8);
        ppMapping.setName("ppMapping"); // NOI18N
        ppMapping.setPreferredSize(new java.awt.Dimension(200, 25));
        ppMapping.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppMappingBtnPrintActionPerformed(evt);
            }
        });
        Popup.add(ppMapping);

        ppDetailSEPPeserta.setBackground(new java.awt.Color(242, 242, 242));
        ppDetailSEPPeserta.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppDetailSEPPeserta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppDetailSEPPeserta.setText("Detail SJP Peserta");
        ppDetailSEPPeserta.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppDetailSEPPeserta.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppDetailSEPPeserta.setIconTextGap(8);
        ppDetailSEPPeserta.setName("ppDetailSEPPeserta"); // NOI18N
        ppDetailSEPPeserta.setPreferredSize(new java.awt.Dimension(200, 25));
        ppDetailSEPPeserta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppDetailSEPPesertaBtnPrintActionPerformed(evt);
            }
        });
        Popup.add(ppDetailSEPPeserta);

        WindowUpdatePulang.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowUpdatePulang.setName("WindowUpdatePulang"); // NOI18N
        WindowUpdatePulang.setUndecorated(true);
        WindowUpdatePulang.setResizable(false);

        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Update Tanggal Pulang ]::"));
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame5.setLayout(null);

        BtnCloseIn4.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn4.setMnemonic('U');
        BtnCloseIn4.setText("Tutup");
        BtnCloseIn4.setToolTipText("Alt+U");
        BtnCloseIn4.setName("BtnCloseIn4"); // NOI18N
        BtnCloseIn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn4ActionPerformed(evt);
            }
        });
        internalFrame5.add(BtnCloseIn4);
        BtnCloseIn4.setBounds(370, 30, 100, 30);

        BtnSimpan4.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan4.setMnemonic('S');
        BtnSimpan4.setText("Simpan");
        BtnSimpan4.setToolTipText("Alt+S");
        BtnSimpan4.setName("BtnSimpan4"); // NOI18N
        BtnSimpan4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan4ActionPerformed(evt);
            }
        });
        internalFrame5.add(BtnSimpan4);
        BtnSimpan4.setBounds(260, 30, 100, 30);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Tanggal Pulang :");
        jLabel26.setName("jLabel26"); // NOI18N
        internalFrame5.add(jLabel26);
        jLabel26.setBounds(6, 32, 100, 23);

        TanggalPulang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "22-04-2019 18:15:10" }));
        TanggalPulang.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TanggalPulang.setName("TanggalPulang"); // NOI18N
        TanggalPulang.setOpaque(false);
        TanggalPulang.setPreferredSize(new java.awt.Dimension(95, 23));
        internalFrame5.add(TanggalPulang);
        TanggalPulang.setBounds(110, 32, 140, 23);

        WindowUpdatePulang.getContentPane().add(internalFrame5, java.awt.BorderLayout.CENTER);

        NoBalasan.setHighlighter(null);
        NoBalasan.setName("NoBalasan"); // NOI18N

        NoSJP.setHighlighter(null);
        NoSJP.setName("NoSJP"); // NOI18N

        idAkomodasi.setHighlighter(null);
        idAkomodasi.setName("idAkomodasi"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Bridging SJP Inhealth ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ScrollKeyPressed(evt);
            }
        });

        tbObat.setAutoCreateRowSorter(true);
        tbObat.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbObat.setComponentPopupMenu(Popup);
        tbObat.setName("tbObat"); // NOI18N
        tbObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbObatMouseClicked(evt);
            }
        });
        tbObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbObatKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbObat);

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

        jPanel3.add(panelGlass8, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tgl. SJP :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(55, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setEditable(false);
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "22-04-2019" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass9.add(DTPCari1);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass9.add(jLabel21);

        DTPCari2.setEditable(false);
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "22-04-2019" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass9.add(DTPCari2);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(205, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('3');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+3");
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

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(560, 279));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        ChkInput.setForeground(new java.awt.Color(0, 0, 0));
        ChkInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setMnemonic('I');
        ChkInput.setSelected(true);
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
        FormInput.setPreferredSize(new java.awt.Dimension(100, 257));
        FormInput.setLayout(null);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("No.Rawat :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(0, 12, 87, 23);

        TNoRw.setBackground(new java.awt.Color(245, 250, 240));
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        FormInput.add(TNoRw);
        TNoRw.setBounds(90, 12, 147, 23);

        TPasien.setEditable(false);
        TPasien.setBackground(new java.awt.Color(245, 250, 240));
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(359, 12, 368, 23);

        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(239, 12, 118, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Tipe SJP :");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(740, 72, 100, 23);

        NoKartu.setEditable(false);
        NoKartu.setBackground(new java.awt.Color(245, 250, 240));
        NoKartu.setForeground(new java.awt.Color(0, 0, 0));
        NoKartu.setHighlighter(null);
        NoKartu.setName("NoKartu"); // NOI18N
        FormInput.add(NoKartu);
        NoKartu.setBounds(582, 42, 147, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Tgl.SJP :");
        jLabel20.setName("jLabel20"); // NOI18N
        jLabel20.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(jLabel20);
        jLabel20.setBounds(509, 72, 70, 23);

        TanggalSEP.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "22-04-2019 18:15:10" }));
        TanggalSEP.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TanggalSEP.setName("TanggalSEP"); // NOI18N
        TanggalSEP.setOpaque(false);
        TanggalSEP.setPreferredSize(new java.awt.Dimension(95, 23));
        TanggalSEP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalSEPKeyPressed(evt);
            }
        });
        FormInput.add(TanggalSEP);
        TanggalSEP.setBounds(582, 72, 147, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Tgl.Rujuk :");
        jLabel22.setName("jLabel22"); // NOI18N
        jLabel22.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(jLabel22);
        jLabel22.setBounds(0, 70, 87, 23);

        TanggalRujuk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "22-04-2019 18:15:10" }));
        TanggalRujuk.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TanggalRujuk.setName("TanggalRujuk"); // NOI18N
        TanggalRujuk.setOpaque(false);
        TanggalRujuk.setPreferredSize(new java.awt.Dimension(95, 23));
        TanggalRujuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalRujukKeyPressed(evt);
            }
        });
        FormInput.add(TanggalRujuk);
        TanggalRujuk.setBounds(90, 72, 147, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("No.Rujukan :");
        jLabel23.setName("jLabel23"); // NOI18N
        jLabel23.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(jLabel23);
        jLabel23.setBounds(270, 72, 70, 23);

        NoRujukan.setForeground(new java.awt.Color(0, 0, 0));
        NoRujukan.setHighlighter(null);
        NoRujukan.setName("NoRujukan"); // NOI18N
        NoRujukan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoRujukanKeyPressed(evt);
            }
        });
        FormInput.add(NoRujukan);
        NoRujukan.setBounds(343, 72, 147, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Tgl.Lahir :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(0, 42, 87, 23);

        TglLahir.setEditable(false);
        TglLahir.setBackground(new java.awt.Color(245, 250, 240));
        TglLahir.setForeground(new java.awt.Color(0, 0, 0));
        TglLahir.setHighlighter(null);
        TglLahir.setName("TglLahir"); // NOI18N
        FormInput.add(TglLahir);
        TglLahir.setBounds(90, 42, 147, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("J.K.:");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(270, 42, 70, 23);

        JK.setEditable(false);
        JK.setBackground(new java.awt.Color(245, 250, 240));
        JK.setForeground(new java.awt.Color(0, 0, 0));
        JK.setHighlighter(null);
        JK.setName("JK"); // NOI18N
        FormInput.add(JK);
        JK.setBounds(343, 42, 147, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("PPK Pelayanan :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 102, 87, 23);

        KdPPK.setEditable(false);
        KdPPK.setBackground(new java.awt.Color(245, 250, 240));
        KdPPK.setForeground(new java.awt.Color(0, 0, 0));
        KdPPK.setHighlighter(null);
        KdPPK.setName("KdPPK"); // NOI18N
        FormInput.add(KdPPK);
        KdPPK.setBounds(90, 102, 70, 23);

        NmPPK.setEditable(false);
        NmPPK.setBackground(new java.awt.Color(245, 250, 240));
        NmPPK.setForeground(new java.awt.Color(0, 0, 0));
        NmPPK.setHighlighter(null);
        NmPPK.setName("NmPPK"); // NOI18N
        FormInput.add(NmPPK);
        NmPPK.setBounds(162, 102, 150, 23);

        btnPPKRujukan.setForeground(new java.awt.Color(0, 0, 0));
        btnPPKRujukan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPPKRujukan.setMnemonic('X');
        btnPPKRujukan.setToolTipText("Alt+X");
        btnPPKRujukan.setName("btnPPKRujukan"); // NOI18N
        btnPPKRujukan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPPKRujukanActionPerformed(evt);
            }
        });
        btnPPKRujukan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnPPKRujukanKeyPressed(evt);
            }
        });
        FormInput.add(btnPPKRujukan);
        btnPPKRujukan.setBounds(314, 132, 28, 23);

        NmPpkRujukan.setEditable(false);
        NmPpkRujukan.setBackground(new java.awt.Color(245, 250, 240));
        NmPpkRujukan.setForeground(new java.awt.Color(0, 0, 0));
        NmPpkRujukan.setHighlighter(null);
        NmPpkRujukan.setName("NmPpkRujukan"); // NOI18N
        FormInput.add(NmPpkRujukan);
        NmPpkRujukan.setBounds(162, 132, 150, 23);

        KdPpkRujukan.setEditable(false);
        KdPpkRujukan.setBackground(new java.awt.Color(245, 250, 240));
        KdPpkRujukan.setForeground(new java.awt.Color(0, 0, 0));
        KdPpkRujukan.setHighlighter(null);
        KdPpkRujukan.setName("KdPpkRujukan"); // NOI18N
        KdPpkRujukan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdPpkRujukanKeyPressed(evt);
            }
        });
        FormInput.add(KdPpkRujukan);
        KdPpkRujukan.setBounds(90, 132, 70, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("PPK Rujukan :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(0, 132, 87, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Diag. Awal :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(0, 162, 87, 23);

        KdPenyakit.setEditable(false);
        KdPenyakit.setBackground(new java.awt.Color(245, 250, 240));
        KdPenyakit.setForeground(new java.awt.Color(0, 0, 0));
        KdPenyakit.setHighlighter(null);
        KdPenyakit.setName("KdPenyakit"); // NOI18N
        KdPenyakit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdPenyakitKeyPressed(evt);
            }
        });
        FormInput.add(KdPenyakit);
        KdPenyakit.setBounds(90, 162, 70, 23);

        NmPenyakit.setEditable(false);
        NmPenyakit.setBackground(new java.awt.Color(245, 250, 240));
        NmPenyakit.setForeground(new java.awt.Color(0, 0, 0));
        NmPenyakit.setHighlighter(null);
        NmPenyakit.setName("NmPenyakit"); // NOI18N
        FormInput.add(NmPenyakit);
        NmPenyakit.setBounds(162, 162, 150, 23);

        btnDiagnosa.setForeground(new java.awt.Color(0, 0, 0));
        btnDiagnosa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDiagnosa.setMnemonic('X');
        btnDiagnosa.setToolTipText("Alt+X");
        btnDiagnosa.setName("btnDiagnosa"); // NOI18N
        btnDiagnosa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDiagnosaActionPerformed(evt);
            }
        });
        btnDiagnosa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnDiagnosaKeyPressed(evt);
            }
        });
        FormInput.add(btnDiagnosa);
        btnDiagnosa.setBounds(314, 162, 28, 23);

        btnDiagnosa1.setForeground(new java.awt.Color(0, 0, 0));
        btnDiagnosa1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDiagnosa1.setMnemonic('X');
        btnDiagnosa1.setToolTipText("Alt+X");
        btnDiagnosa1.setName("btnDiagnosa1"); // NOI18N
        btnDiagnosa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDiagnosa1ActionPerformed(evt);
            }
        });
        btnDiagnosa1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnDiagnosa1KeyPressed(evt);
            }
        });
        FormInput.add(btnDiagnosa1);
        btnDiagnosa1.setBounds(314, 192, 28, 23);

        NmPenyakit2.setEditable(false);
        NmPenyakit2.setBackground(new java.awt.Color(245, 250, 240));
        NmPenyakit2.setForeground(new java.awt.Color(0, 0, 0));
        NmPenyakit2.setHighlighter(null);
        NmPenyakit2.setName("NmPenyakit2"); // NOI18N
        FormInput.add(NmPenyakit2);
        NmPenyakit2.setBounds(162, 192, 150, 23);

        KdPenyakit2.setEditable(false);
        KdPenyakit2.setBackground(new java.awt.Color(245, 250, 240));
        KdPenyakit2.setForeground(new java.awt.Color(0, 0, 0));
        KdPenyakit2.setHighlighter(null);
        KdPenyakit2.setName("KdPenyakit2"); // NOI18N
        KdPenyakit2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdPenyakit2KeyPressed(evt);
            }
        });
        FormInput.add(KdPenyakit2);
        KdPenyakit2.setBounds(90, 192, 70, 23);

        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setText("Diag. Tmbhn :");
        jLabel39.setName("jLabel39"); // NOI18N
        FormInput.add(jLabel39);
        jLabel39.setBounds(0, 192, 87, 23);

        LabelRuang.setForeground(new java.awt.Color(0, 0, 0));
        LabelRuang.setText("Ruang :");
        LabelRuang.setName("LabelRuang"); // NOI18N
        FormInput.add(LabelRuang);
        LabelRuang.setBounds(345, 222, 65, 23);

        LabelPoli.setForeground(new java.awt.Color(0, 0, 0));
        LabelPoli.setText("Poli :");
        LabelPoli.setName("LabelPoli"); // NOI18N
        FormInput.add(LabelPoli);
        LabelPoli.setBounds(345, 162, 65, 23);

        KdPoli.setEditable(false);
        KdPoli.setBackground(new java.awt.Color(245, 250, 240));
        KdPoli.setForeground(new java.awt.Color(0, 0, 0));
        KdPoli.setHighlighter(null);
        KdPoli.setName("KdPoli"); // NOI18N
        KdPoli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdPoliKeyPressed(evt);
            }
        });
        FormInput.add(KdPoli);
        KdPoli.setBounds(413, 162, 80, 23);

        KdRuang.setEditable(false);
        KdRuang.setForeground(new java.awt.Color(0, 0, 0));
        KdRuang.setHighlighter(null);
        KdRuang.setName("KdRuang"); // NOI18N
        KdRuang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdRuangKeyPressed(evt);
            }
        });
        FormInput.add(KdRuang);
        KdRuang.setBounds(413, 222, 139, 23);

        labelKElas.setForeground(new java.awt.Color(0, 0, 0));
        labelKElas.setText("Kelas :");
        labelKElas.setName("labelKElas"); // NOI18N
        FormInput.add(labelKElas);
        labelKElas.setBounds(345, 192, 65, 23);

        Kelas.setForeground(new java.awt.Color(0, 0, 0));
        Kelas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "000 Non Kelas", "100 Kelas 1", "101 Kelas 2", "102 Kelas 3", "103 Kelas VIP", "104 Kelas VVIP", "500 KHUSUS" }));
        Kelas.setName("Kelas"); // NOI18N
        Kelas.setOpaque(false);
        Kelas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                KelasMouseClicked(evt);
            }
        });
        Kelas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KelasKeyPressed(evt);
            }
        });
        FormInput.add(Kelas);
        Kelas.setBounds(412, 192, 120, 23);

        Catatan.setForeground(new java.awt.Color(0, 0, 0));
        Catatan.setHighlighter(null);
        Catatan.setName("Catatan"); // NOI18N
        Catatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CatatanKeyPressed(evt);
            }
        });
        FormInput.add(Catatan);
        Catatan.setBounds(90, 222, 222, 23);

        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("Catatan :");
        jLabel33.setName("jLabel33"); // NOI18N
        FormInput.add(jLabel33);
        jLabel33.setBounds(0, 220, 87, 23);

        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Laka Lantas :");
        jLabel34.setName("jLabel34"); // NOI18N
        FormInput.add(jLabel34);
        jLabel34.setBounds(345, 102, 65, 23);

        LakaLantas.setForeground(new java.awt.Color(0, 0, 0));
        LakaLantas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0 Biasa", "1 Kecelakaan Kerja", "2 Kecelakaan Lalu Lintas" }));
        LakaLantas.setName("LakaLantas"); // NOI18N
        LakaLantas.setOpaque(false);
        LakaLantas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LakaLantasMouseClicked(evt);
            }
        });
        LakaLantas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LakaLantasKeyPressed(evt);
            }
        });
        FormInput.add(LakaLantas);
        LakaLantas.setBounds(413, 102, 315, 23);

        LokasiLaka.setForeground(new java.awt.Color(0, 0, 0));
        LokasiLaka.setHighlighter(null);
        LokasiLaka.setName("LokasiLaka"); // NOI18N
        LokasiLaka.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LokasiLakaKeyPressed(evt);
            }
        });
        FormInput.add(LokasiLaka);
        LokasiLaka.setBounds(845, 102, 280, 23);

        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("Lokasi :");
        jLabel35.setName("jLabel35"); // NOI18N
        FormInput.add(jLabel35);
        jLabel35.setBounds(740, 102, 100, 23);

        LabelKelas.setForeground(new java.awt.Color(0, 0, 0));
        LabelKelas.setText("Pelayanan :");
        LabelKelas.setName("LabelKelas"); // NOI18N
        FormInput.add(LabelKelas);
        LabelKelas.setBounds(345, 132, 65, 23);

        JenisPelayanan.setForeground(new java.awt.Color(0, 0, 0));
        JenisPelayanan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "3 RJTL RAWAT JALAN TINGKAT LANJUT", "4 RITL RAWAT INAP TINGKAT LANJUT" }));
        JenisPelayanan.setName("JenisPelayanan"); // NOI18N
        JenisPelayanan.setOpaque(false);
        JenisPelayanan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JenisPelayananItemStateChanged(evt);
            }
        });
        JenisPelayanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JenisPelayananMouseClicked(evt);
            }
        });
        JenisPelayanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JenisPelayananKeyPressed(evt);
            }
        });
        FormInput.add(JenisPelayanan);
        JenisPelayanan.setBounds(413, 132, 240, 23);

        NmPoli.setEditable(false);
        NmPoli.setBackground(new java.awt.Color(245, 250, 240));
        NmPoli.setForeground(new java.awt.Color(0, 0, 0));
        NmPoli.setHighlighter(null);
        NmPoli.setName("NmPoli"); // NOI18N
        FormInput.add(NmPoli);
        NmPoli.setBounds(495, 162, 204, 23);

        btnPoli.setForeground(new java.awt.Color(0, 0, 0));
        btnPoli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPoli.setMnemonic('X');
        btnPoli.setToolTipText("Alt+X");
        btnPoli.setName("btnPoli"); // NOI18N
        btnPoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPoliActionPerformed(evt);
            }
        });
        btnPoli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnPoliKeyPressed(evt);
            }
        });
        FormInput.add(btnPoli);
        btnPoli.setBounds(701, 162, 28, 23);

        NmRuang.setEditable(false);
        NmRuang.setForeground(new java.awt.Color(0, 0, 0));
        NmRuang.setHighlighter(null);
        NmRuang.setName("NmRuang"); // NOI18N
        FormInput.add(NmRuang);
        NmRuang.setBounds(555, 222, 220, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("No.Kartu :");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(509, 42, 70, 23);

        klsPlanDes.setEditable(false);
        klsPlanDes.setBackground(new java.awt.Color(245, 250, 240));
        klsPlanDes.setForeground(new java.awt.Color(0, 0, 0));
        klsPlanDes.setHighlighter(null);
        klsPlanDes.setName("klsPlanDes"); // NOI18N
        FormInput.add(klsPlanDes);
        klsPlanDes.setBounds(845, 12, 180, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Kelas - Plan Desc :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(740, 12, 100, 23);

        tipeCOB.setEditable(false);
        tipeCOB.setBackground(new java.awt.Color(245, 250, 240));
        tipeCOB.setForeground(new java.awt.Color(0, 0, 0));
        tipeCOB.setHighlighter(null);
        tipeCOB.setName("tipeCOB"); // NOI18N
        FormInput.add(tipeCOB);
        tipeCOB.setBounds(845, 42, 270, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Tipe COB :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(740, 42, 100, 23);

        tipeSJP.setEditable(false);
        tipeSJP.setBackground(new java.awt.Color(245, 250, 240));
        tipeSJP.setForeground(new java.awt.Color(0, 0, 0));
        tipeSJP.setHighlighter(null);
        tipeSJP.setName("tipeSJP"); // NOI18N
        FormInput.add(tipeSJP);
        tipeSJP.setBounds(845, 72, 110, 23);

        btnRuang.setForeground(new java.awt.Color(0, 0, 0));
        btnRuang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnRuang.setMnemonic('X');
        btnRuang.setToolTipText("Alt+X");
        btnRuang.setName("btnRuang"); // NOI18N
        btnRuang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRuangActionPerformed(evt);
            }
        });
        btnRuang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnRuangKeyPressed(evt);
            }
        });
        FormInput.add(btnRuang);
        btnRuang.setBounds(780, 222, 28, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().trim().equals("")||TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        }else if (NoKartu.getText().trim().equals("")) {
            Valid.textKosong(NoKartu, "Nomor Kartu");
        }else if (NoRujukan.getText().trim().equals("")) {
            Valid.textKosong(NoRujukan, "Nomor Rujukan");
        }else if (KdPpkRujukan.getText().trim().equals("")||NmPpkRujukan.getText().trim().equals("")) {
            Valid.textKosong(KdPpkRujukan, "PPK Rujukan");
        }else if (KdPPK.getText().trim().equals("")||NmPPK.getText().trim().equals("")) {
            Valid.textKosong(KdPPK, "PPK Pelayanan");
        }else if (KdPenyakit.getText().trim().equals("")||NmPenyakit.getText().trim().equals("")) {
            Valid.textKosong(KdPenyakit, "Diagnosa");
        }else if (Catatan.getText().trim().equals("")) {
            Valid.textKosong(Catatan, "Catatan");
        }else if((JenisPelayanan.getSelectedIndex()==1)&&(KdPoli.getText().trim().equals("")||NmPoli.getText().trim().equals(""))){
            Valid.textKosong(KdPoli, "Poli Tujuan");        
        }else{  
            insertSEP();
        }   
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
            Valid.pindah(evt,LokasiLaka,BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            emptTeks();
        }else{Valid.pindah(evt, BtnSimpan, BtnHapus);}
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if(tbObat.getSelectedRow()!= -1){
            deleteSJP();  
        }else{
            JOptionPane.showMessageDialog(null,"Silahkan pilih dulu data yang mau dihapus..!!");
        }            
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnHapusActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if(tbObat.getSelectedRow()!= -1){
            if (TNoRw.getText().trim().equals("")||TPasien.getText().trim().equals("")) {
                Valid.textKosong(TNoRw, "Pasien");
            }else if (NoKartu.getText().trim().equals("")) {
                Valid.textKosong(NoKartu, "Nomor Kartu");
            }else if (NoRujukan.getText().trim().equals("")) {
                Valid.textKosong(NoRujukan, "Nomor Rujukan");
            }else if (KdPpkRujukan.getText().trim().equals("")||NmPpkRujukan.getText().trim().equals("")) {
                Valid.textKosong(KdPpkRujukan, "PPK Rujukan");
            }else if (KdPPK.getText().trim().equals("")||NmPPK.getText().trim().equals("")) {
                Valid.textKosong(KdPPK, "PPK Pelayanan");
            }else if (KdPenyakit.getText().trim().equals("")||NmPenyakit.getText().trim().equals("")) {
                Valid.textKosong(KdPenyakit, "Diagnosa");
            }else if (Catatan.getText().trim().equals("")) {
                Valid.textKosong(Catatan, "Catatan");
            }else{
                
            }
        }else{
            JOptionPane.showMessageDialog(null,"Silahkan pilih dulu data yang mau diganti..!!");
        } 
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnEditActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnHapus, BtnPrint);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnEdit,TCari);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BtnCariActionPerformed(evt);
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            TCari.requestFocus();
        }else if(tabMode.getRowCount()!=0){            
            Map<String, Object> param = new HashMap<>(); 
                param.put("namars",akses.getnamars());
                param.put("alamatrs",akses.getalamatrs());
                param.put("kotars",akses.getkabupatenrs());
                param.put("propinsirs",akses.getpropinsirs());
                param.put("kontakrs",akses.getkontakrs());
                param.put("emailrs",akses.getemailrs());   
                param.put("logo",Sequel.cariGambar("select logo from setting")); 
            Valid.MyReport("rptBridgingDaftar.jasper","report","::[ Data Bridging SEP ]::",
                    "select bridging_inhealth.no_sjp, bridging_inhealth.no_rawat,bridging_inhealth.nomr,bridging_inhealth.nama_pasien,bridging_inhealth.tglsep,"+
                    "bridging_inhealth.tglrujukan,bridging_inhealth.no_rujukan,bridging_inhealth.kdppkrujukan,"+
                    "bridging_inhealth.nmppkrujukan,bridging_inhealth.kdppkpelayanan,bridging_inhealth.nmppkpelayanan,"+
                    "if(bridging_inhealth.jnspelayanan='1','Rawat Inap','Rawat Jalan'),bridging_inhealth.catatan,bridging_inhealth.diagawal,"+
                    "bridging_inhealth.nmdiagnosaawal,bridging_inhealth.kdpolitujuan,bridging_inhealth.nmpolitujuan,"+
                    "if(bridging_inhealth.klsrawat='1','Kelas 1',if(bridging_inhealth.klsrawat='2','Kelas 2','Kelas 3')),"+
                    "if(bridging_inhealth.lakalantas='1','Kasus Kecelakaan','Bukan Kasus Kecelakaan'),bridging_inhealth.lokasilaka,bridging_inhealth.user, "+
                    "bridging_inhealth.tanggal_lahir,bridging_inhealth.peserta,bridging_inhealth.jkel,bridging_inhealth.no_kartu,bridging_inhealth.tglpulang from bridging_inhealth where "+
                    "bridging_inhealth.tglsep between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59' and bridging_inhealth.no_sjp like '%"+TCari.getText().trim()+"%' or "+
                    "bridging_inhealth.tglsep between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59' and bridging_inhealth.nomr like '%"+TCari.getText().trim()+"%' or "+
                    "bridging_inhealth.tglsep between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59' and bridging_inhealth.nama_pasien like '%"+TCari.getText().trim()+"%' or "+
                    "bridging_inhealth.tglsep between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59' and bridging_inhealth.nmppkrujukan like '%"+TCari.getText().trim()+"%' or "+
                    "bridging_inhealth.tglsep between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59' and bridging_inhealth.diagawal like '%"+TCari.getText().trim()+"%' or "+
                    "bridging_inhealth.tglsep between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59' and bridging_inhealth.nmdiagnosaawal like '%"+TCari.getText().trim()+"%' or "+
                    "bridging_inhealth.tglsep between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59' and bridging_inhealth.no_rawat like '%"+TCari.getText().trim()+"%' or "+
                    "bridging_inhealth.tglsep between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59' and bridging_inhealth.nmpolitujuan like '%"+TCari.getText().trim()+"%' order by bridging_inhealth.tglsep",param);
        }
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPrintActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnEdit, BtnKeluar);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

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
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            tampil();
            TCari.setText("");
        }else{
            Valid.pindah(evt, BtnCari, TPasien);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObatMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
               getData();
            } catch (java.lang.NullPointerException e) {
            }
            if(evt.getClickCount()==2){
                i=tbObat.getSelectedColumn();
                if(i==0){
                    ppSEPBtnPrintActionPerformed(null);
                }else if(i==1){
                    ppPulangBtnPrintActionPerformed(null);
                }else if(i==2){
                    ppDetailSEPPesertaBtnPrintActionPerformed(null);
                }
            }
        }
}//GEN-LAST:event_tbObatMouseClicked

    private void tbObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObatKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                   getData();
                } catch (java.lang.NullPointerException e) {
                }
            }else if(evt.getKeyCode()==KeyEvent.VK_SPACE){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
                i=tbObat.getSelectedColumn();
                if(i==0){
                    ppSEPBtnPrintActionPerformed(null);
                }else if(i==1){
                    ppPulangBtnPrintActionPerformed(null);
                }else if(i==2){
                    ppDetailSEPPesertaBtnPrintActionPerformed(null);
                }
            }
                
        }
}//GEN-LAST:event_tbObatKeyPressed

    private void NoRujukanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoRujukanKeyPressed
        Valid.pindah(evt, TCari, TanggalRujuk);
    }//GEN-LAST:event_NoRujukanKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
        if(akses.getform().equals("DlgReg")||akses.getform().equals("DlgIGD")||akses.getform().equals("DlgKamarInap")){
            no_peserta=Sequel.cariIsi("select no_peserta from pasien where no_rkm_medis=?",TNoRM.getText());
            if(no_peserta.trim().equals("")){
                JOptionPane.showMessageDialog(null,"Pasien tidak mempunyai kepesertaan BPJS");
                dispose();
            }else{
                try {
                    prop.loadFromXML(new FileInputStream("setting/database.xml"));
                    String URL = prop.getProperty("URLAPIINHEALTH")+"/api/EligibilitasPeserta";	
                    HttpHeaders headers = new HttpHeaders();            
                    headers.add("Content-Type","application/json");
                    requestJson ="{ \"token\": \""+prop.getProperty("TOKENINHEALTH")+"\"," +
                                    "\"kodeprovider\": \""+KdPPK.getText()+"\"," +
                                    "\"nokainhealth\": \""+no_peserta+"\"," +
                                    "\"tglpelayanan\": \""+Valid.SetTgl(TanggalSEP.getSelectedItem()+"")+"\"," +
                                    "\"jenispelayanan\": \"3\"," +
                                    "\"poli\": \"IGD\"" +
                                 "}";
                    HttpEntity requestEntity = new HttpEntity(requestJson,headers);
                    RestTemplate rest = new RestTemplate();
                    ObjectMapper mapper = new ObjectMapper();
                    JsonNode root = mapper.readTree(rest.exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                    //System.out.println("JSON : "+rest.exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                    if(root.path("ERRORCODE").asText().equals("00")){
                        NoKartu.setText(root.path("NOKAPST").asText());      
                        TPasien.setText(root.path("NMPST").asText());
                        TglLahir.setText(root.path("TGLLAHIR").asText().substring(0,11));
                        KdPpkRujukan.setText(root.path("KODEPROVIDER").asText());
                        NmPpkRujukan.setText(root.path("NAMAPROVIDER").asText());
                        jkel=Sequel.cariIsi("select jk from pasien where no_rkm_medis=?",TNoRM.getText());
                        JK.setText(jkel.replaceAll("P","PEREMPUAN").replaceAll("L","LAKI-LAKI"));
                        klsPlanDes.setText(root.path("NAMAKELASRAWAT").asText()+" - "+root.path("NAMAPRODUK").asText());                        
                    }else {
                        emptTeks();
                        JOptionPane.showMessageDialog(null,root.path("ERRORDESC").asText());                
                    } 
                } catch (Exception e) {
                    System.out.println("Notifikasi Peserta : "+e);
                    if(e.toString().contains("UnknownHostException")){
                        JOptionPane.showMessageDialog(rootPane,"Koneksi ke server Inhealth terputus...!");
                        dispose();
                    }
                }                 
            } 
        }
    }//GEN-LAST:event_formWindowOpened

    private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
        isForm();
    }//GEN-LAST:event_ChkInputActionPerformed

    private void ppSEPBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppSEPBtnPrintActionPerformed
        if(tbObat.getSelectedRow()!= -1){
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); 
            Map<String, Object> param = new HashMap<>();
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            if(JenisPelayanan.getSelectedIndex()==0){
                Valid.MyReport("rptBridgingSJP.jasper","report","::[ Cetak SJP ]::","select * from bridging_inhealth where no_sjp='"+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"'",param);
            }else{
                Valid.MyReport("rptBridgingSJP2.jasper","report","::[ Cetak SJP ]::",
                        "select * from bridging_inhealth a " +
                        "left join inhealth_jenpel_ruang_rawat b on b.kode_jenpel_ruang_rawat=a.kode_ruang "+
                        "where no_sjp='"+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"'",param);
            }                
            this.setCursor(Cursor.getDefaultCursor());
        }else{
            JOptionPane.showMessageDialog(null,"Maaf, silahkan pilih data SJP yang mau dicetak...!!!!");
            BtnBatal.requestFocus();
        }        
    }//GEN-LAST:event_ppSEPBtnPrintActionPerformed

    private void ppPulangBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppPulangBtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));        
        if(tbObat.getSelectedRow()!= -1){
            WindowUpdatePulang.setLocationRelativeTo(internalFrame1);
            WindowUpdatePulang.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null,"Maaf, silahkan pilih data SEP yang mau diupdate tanggal pulangnya...!!!!");
            BtnBatal.requestFocus();
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_ppPulangBtnPrintActionPerformed

    private void BtnCloseIn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn4ActionPerformed
        WindowUpdatePulang.dispose();
    }//GEN-LAST:event_BtnCloseIn4ActionPerformed

    private void BtnSimpan4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan4ActionPerformed
        if (TNoRw.getText().trim().equals("")||TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        }else if (NoKartu.getText().trim().equals("")) {
            Valid.textKosong(NoKartu, "Nomor Kartu");
        }else if (NoRujukan.getText().trim().equals("")) {
            Valid.textKosong(NoRujukan, "Nomor Rujukan");
        }else if (KdPpkRujukan.getText().trim().equals("")||NmPpkRujukan.getText().trim().equals("")) {
            Valid.textKosong(KdPpkRujukan, "PPK Rujukan");
        }else if (KdPPK.getText().trim().equals("")||NmPPK.getText().trim().equals("")) {
            Valid.textKosong(KdPPK, "PPK Pelayanan");
        }else if (KdPenyakit.getText().trim().equals("")||NmPenyakit.getText().trim().equals("")) {
            Valid.textKosong(KdPenyakit, "Diagnosa");
        }else if (Catatan.getText().trim().equals("")) {
            Valid.textKosong(Catatan, "Catatan");
        }else{
            updateTglPulang();
        }
    }//GEN-LAST:event_BtnSimpan4ActionPerformed

    private void TanggalRujukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalRujukKeyPressed
        Valid.pindah(evt, NoRujukan, TanggalSEP);
    }//GEN-LAST:event_TanggalRujukKeyPressed

    private void TanggalSEPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalSEPKeyPressed
        Valid.pindah(evt, TanggalRujuk,btnPPKRujukan);
    }//GEN-LAST:event_TanggalSEPKeyPressed

    private void ppMappingBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppMappingBtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));        
        if(tbObat.getSelectedRow()!= -1){
            
        }else{
            JOptionPane.showMessageDialog(null,"Maaf, silahkan pilih data SEP yang mau dimapping transaksinya...!!!!");
            BtnBatal.requestFocus();
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_ppMappingBtnPrintActionPerformed

    private void ppDetailSEPPesertaBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppDetailSEPPesertaBtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));        
        if(tbObat.getSelectedRow()!= -1){
            BPJSCekDetailSEP detail=new BPJSCekDetailSEP(null,true);
            detail.tampil(tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
            detail.setSize(internalFrame1.getWidth()-40,internalFrame1.getHeight()-40);
            detail.setLocationRelativeTo(internalFrame1);
            detail.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null,"Maaf, silahkan pilih data SEP ...!!!!");
            BtnBatal.requestFocus();
        }
        this.setCursor(Cursor.getDefaultCursor());            
    }//GEN-LAST:event_ppDetailSEPPesertaBtnPrintActionPerformed

    private void btnPPKRujukanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPPKRujukanActionPerformed
        faskes.setSize(internalFrame1.getWidth()-40,internalFrame1.getHeight()-40);
        faskes.setLocationRelativeTo(internalFrame1);
        faskes.setVisible(true);
        faskes.fokus();
    }//GEN-LAST:event_btnPPKRujukanActionPerformed

    private void btnPPKRujukanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPPKRujukanKeyPressed
        Valid.pindah(evt,TanggalSEP,btnDiagnosa);
    }//GEN-LAST:event_btnPPKRujukanKeyPressed

    private void KdPpkRujukanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdPpkRujukanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            KdPenyakit.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
           // TNoReg.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            btnPPKRujukanActionPerformed(null);
        }
    }//GEN-LAST:event_KdPpkRujukanKeyPressed

    private void KdPenyakitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdPenyakitKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            KdPenyakit2.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            KdPPK.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            btnDiagnosaActionPerformed(null);
        }
    }//GEN-LAST:event_KdPenyakitKeyPressed

    private void btnDiagnosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDiagnosaActionPerformed
        pilih=1;
        penyakit.setSize(internalFrame1.getWidth()-40,internalFrame1.getHeight()-40);
        penyakit.setLocationRelativeTo(internalFrame1);
        penyakit.isCek();
        penyakit.setVisible(true);
        penyakit.emptTeks();
    }//GEN-LAST:event_btnDiagnosaActionPerformed

    private void btnDiagnosaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnDiagnosaKeyPressed
        Valid.pindah(evt,btnPPKRujukan,btnPoli);
    }//GEN-LAST:event_btnDiagnosaKeyPressed

    private void btnDiagnosa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDiagnosa1ActionPerformed
        pilih=2;
        penyakit.setSize(internalFrame1.getWidth()-40,internalFrame1.getHeight()-40);
        penyakit.setLocationRelativeTo(internalFrame1);
        penyakit.isCek();
        penyakit.setVisible(true);
        penyakit.emptTeks();
    }//GEN-LAST:event_btnDiagnosa1ActionPerformed

    private void btnDiagnosa1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnDiagnosa1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDiagnosa1KeyPressed

    private void KdPenyakit2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdPenyakit2KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
          //  kddokter.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            KdPenyakit.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            btnDiagnosa1ActionPerformed(null);
        }
    }//GEN-LAST:event_KdPenyakit2KeyPressed

    private void KdPoliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdPoliKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnSimpan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            JenisPelayanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            btnPoliActionPerformed(null);
        }
    }//GEN-LAST:event_KdPoliKeyPressed

    private void KdRuangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdRuangKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KdRuangKeyPressed

    private void KelasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KelasKeyPressed
        Valid.pindah(evt,LokasiLaka,JenisPelayanan);
    }//GEN-LAST:event_KelasKeyPressed

    private void CatatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CatatanKeyPressed
        Valid.pindah(evt,LakaLantas,LokasiLaka);
    }//GEN-LAST:event_CatatanKeyPressed

    private void LakaLantasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LakaLantasKeyPressed
        Valid.pindah(evt,TanggalSEP,Catatan);
    }//GEN-LAST:event_LakaLantasKeyPressed

    private void LokasiLakaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LokasiLakaKeyPressed
        Valid.pindah(evt,Catatan,Kelas);
    }//GEN-LAST:event_LokasiLakaKeyPressed

    private void JenisPelayananItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JenisPelayananItemStateChanged
        cekLAYAN();
    }//GEN-LAST:event_JenisPelayananItemStateChanged

    private void JenisPelayananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JenisPelayananKeyPressed
        //Valid.pindah(evt,Kelas,kdpoli);
    }//GEN-LAST:event_JenisPelayananKeyPressed

    private void btnPoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPoliActionPerformed
        poli.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        poli.setLocationRelativeTo(internalFrame1);
        poli.setVisible(true);
        poli.fokus();
    }//GEN-LAST:event_btnPoliActionPerformed

    private void btnPoliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPoliKeyPressed
        Valid.pindah(evt,btnDiagnosa,LakaLantas);
    }//GEN-LAST:event_btnPoliKeyPressed

    private void LakaLantasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LakaLantasMouseClicked
        LakaLantas.setEditable(false);
    }//GEN-LAST:event_LakaLantasMouseClicked

    private void KelasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KelasMouseClicked
        Kelas.setEditable(false);
    }//GEN-LAST:event_KelasMouseClicked

    private void JenisPelayananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JenisPelayananMouseClicked
        JenisPelayanan.setEditable(false);
    }//GEN-LAST:event_JenisPelayananMouseClicked

    private void btnRuangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRuangActionPerformed
        kamar.isCek();
        kamar.emptTeks();
        kamar.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        kamar.setLocationRelativeTo(internalFrame1);
        kamar.setVisible(true);
        kamar.emptTeks();
    }//GEN-LAST:event_btnRuangActionPerformed

    private void btnRuangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnRuangKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRuangKeyPressed

    private void ScrollKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ScrollKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ScrollKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            InhealthDataSJP dialog = new InhealthDataSJP(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCloseIn4;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Button BtnSimpan4;
    private widget.TextBox Catatan;
    private widget.CekBox ChkInput;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.TextBox JK;
    private widget.ComboBox JenisPelayanan;
    private widget.TextBox KdPPK;
    private widget.TextBox KdPenyakit;
    private widget.TextBox KdPenyakit2;
    private widget.TextBox KdPoli;
    private widget.TextBox KdPpkRujukan;
    private widget.TextBox KdRuang;
    private widget.ComboBox Kelas;
    private widget.Label LCount;
    private widget.Label LabelKelas;
    private widget.Label LabelPoli;
    private widget.Label LabelRuang;
    private widget.ComboBox LakaLantas;
    private widget.TextBox LokasiLaka;
    private widget.TextBox NmPPK;
    private widget.TextBox NmPenyakit;
    private widget.TextBox NmPenyakit2;
    private widget.TextBox NmPoli;
    private widget.TextBox NmPpkRujukan;
    private widget.TextBox NmRuang;
    private widget.TextBox NoBalasan;
    private widget.TextBox NoKartu;
    private widget.TextBox NoRujukan;
    private widget.TextBox NoSJP;
    private javax.swing.JPanel PanelInput;
    private javax.swing.JPopupMenu Popup;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.Tanggal TanggalPulang;
    private widget.Tanggal TanggalRujuk;
    private widget.Tanggal TanggalSEP;
    private widget.TextBox TglLahir;
    private javax.swing.JDialog WindowUpdatePulang;
    private widget.Button btnDiagnosa;
    private widget.Button btnDiagnosa1;
    private widget.Button btnPPKRujukan;
    private widget.Button btnPoli;
    private widget.Button btnRuang;
    private widget.TextBox idAkomodasi;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame5;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel15;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel20;
    private widget.Label jLabel21;
    private widget.Label jLabel22;
    private widget.Label jLabel23;
    private widget.Label jLabel26;
    private widget.Label jLabel33;
    private widget.Label jLabel34;
    private widget.Label jLabel35;
    private widget.Label jLabel39;
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private widget.TextBox klsPlanDes;
    private widget.Label labelKElas;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private javax.swing.JMenuItem ppDetailSEPPeserta;
    private javax.swing.JMenuItem ppMapping;
    private javax.swing.JMenuItem ppPulang;
    private javax.swing.JMenuItem ppSEP;
    private widget.Table tbObat;
    private widget.TextBox tipeCOB;
    private widget.TextBox tipeSJP;
    // End of variables declaration//GEN-END:variables

    public void tampil() {        
        Valid.tabelKosong(tabMode);
        try{
            ps=koneksi.prepareStatement(
                    " SELECT bi.no_sjp, bi.no_rawat, bi.tglsep, bi.no_kartu, bi.nomr, bi.nama_pasien, DATE_FORMAT(bi.tanggal_lahir,'%d-%m-%Y') tgl_lhr, "
                    + " bi.jkel, bi.tglrujukan, bi.no_rujukan, bi.kdppkrujukan, bi.nmppkrujukan, bi.kdppkpelayanan, bi.nmppkpelayanan, bi.tglpulang, "
                    + " IF (bi.jnspelayanan = '1','1 RJTP RAWAT JALAN TINGKAT PERTAMA',IF (bi.jnspelayanan = '2','2 RITP RAWAT INAP TINGKAT PERTAMA', "
                    + " IF (bi.jnspelayanan = '3','3 RJTL RAWAT JALAN TINGKAT LANJUT','4 RITL RAWAT INAP TINGKAT LANJUT'))) jns_pel, "
                    + " bi.catatan, bi.diagawal, bi.nmdiagnosaawal, bi.diagawal2, bi.nmdiagnosaawal2, bi.kdpolitujuan, bi.nmpolitujuan, "
                    + " IF (bi.lakalantas = '0','0 Biasa',IF (bi.lakalantas = '1','1 Kecelakaan Kerja','2 Kecelakaan Lalu Lintas')) laka_lan, "
                    + " bi.lokasilaka, IFNULL(p.nama,'-') petugas, bi.klsrawat, bi.klsdesc, bi.kdbu, bi.nmbu, bi.plan, bi.plandesc, bi.idakomodasi, bi.tipesjp, bi.tipecob, "
                    + " IFNULL(bi.kelas_rawat,'-') kls_rwt, IFNULL(bi.kode_ruang,'-') kd_ruang, IFNULL(ih.nama_jenpel_ruang_rawat,'-') nm_ruang FROM bridging_inhealth bi "
                    + " LEFT JOIN petugas p ON p.nip=bi.`user` LEFT JOIN inhealth_jenpel_ruang_rawat ih ON ih.kode_jenpel_ruang_rawat = bi.kode_ruang where "
                    + " bi.tglsep between ? and ? and bi.no_sjp like ? or "
                    + " bi.tglsep between ? and ? and bi.nomr like ? or "
                    + " bi.tglsep between ? and ? and bi.nama_pasien like ? or "
                    + " bi.tglsep between ? and ? and bi.nmppkrujukan like ? or "
                    + " bi.tglsep between ? and ? and bi.diagawal like ? or "
                    + " bi.tglsep between ? and ? and bi.nmdiagnosaawal like ? or "
                    + " bi.tglsep between ? and ? and bi.no_rawat like ? or "
                    + " bi.tglsep between ? and ? and bi.nmpolitujuan like ? order by bi.tglsep");
            
            try {
                ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                ps.setString(3,"%"+TCari.getText().trim()+"%");
                ps.setString(4,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                ps.setString(5,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                ps.setString(6,"%"+TCari.getText().trim()+"%");
                ps.setString(7,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                ps.setString(8,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                ps.setString(9,"%"+TCari.getText().trim()+"%");
                ps.setString(10,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                ps.setString(11,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                ps.setString(12,"%"+TCari.getText().trim()+"%");
                ps.setString(13,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                ps.setString(14,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                ps.setString(15,"%"+TCari.getText().trim()+"%");
                ps.setString(16,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                ps.setString(17,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                ps.setString(18,"%"+TCari.getText().trim()+"%");
                ps.setString(19,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                ps.setString(20,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                ps.setString(21,"%"+TCari.getText().trim()+"%");
                ps.setString(22,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                ps.setString(23,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                ps.setString(24,"%"+TCari.getText().trim()+"%");
                rs=ps.executeQuery();
                while(rs.next()){
                    if(rs.getString(19).equals("000")){
                        kelas="000 Non Kelas";
                    }
                    tabMode.addRow(new Object[]{
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15),
                        rs.getString(16),
                        rs.getString(17),
                        rs.getString(18),
                        rs.getString(19),
                        rs.getString(20),
                        rs.getString(21),
                        rs.getString(22),
                        rs.getString(23),
                        rs.getString(24),
                        rs.getString(25),
                        rs.getString(26),
                        kelas,
                        rs.getString(28),
                        rs.getString(29),
                        rs.getString(30),
                        rs.getString(31),
                        rs.getString(32),
                        rs.getString(33),
                        rs.getString(34),
                        rs.getString(35),
                        rs.getString(36),
                        rs.getString(37),
                        rs.getString(38)
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : "+e);
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

    private void isRawat() {
        Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat=? ",TNoRM,TNoRw.getText());  
        TNoRM.setText(TNoRM.getText());
        Catatan.setText("-");
    }
    
    private void emptTeks(){
        NoSJP.setText("");
        TNoRw.setText("");
        TPasien.setText("");
        TNoRM.setText("");
        TanggalSEP.setDate(new Date());
        TanggalRujuk.setDate(new Date());
        TglLahir.setText("");
        NoKartu.setText("");
        JK.setText("");
        NoRujukan.setText("");
        KdPpkRujukan.setText("");
        NmPpkRujukan.setText("");
        KdPPK.setText("");
        NmPPK.setText("");
        JenisPelayanan.setSelectedIndex(0);
        Catatan.setText("");
        KdPenyakit.setText("");
        NmPenyakit.setText("");
        KdPenyakit2.setText("");
        NmPenyakit2.setText("");
        KdRuang.setText("");
        NmRuang.setText("");
        KdPoli.setText("");
        NmPoli.setText("");
        klsPlanDes.setText("");
        tipeCOB.setText("");
        tipeSJP.setText("");
        idAkomodasi.setText("");
        
        Kelas.setSelectedIndex(0);
        LakaLantas.setSelectedIndex(0);
        LokasiLaka.setText("");
        NoRujukan.requestFocus();
        Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(rujuk_masuk.no_rawat,4),signed)),0) from reg_periksa inner join rujuk_masuk on reg_periksa.no_rawat=rujuk_masuk.no_rawat where reg_periksa.tgl_registrasi='"+Valid.SetTgl(TanggalSEP.getSelectedItem()+"")+"' ","BR/"+dateformat.format(TanggalSEP.getDate())+"/",4,NoBalasan);        
    }
    
    public void setNoRm(String norwt, String tgl1, String status, String kdpoli, String namapoli) {
        TNoRw.setText(norwt);
        TCari.setText(norwt);
        KdPoli.setText(kdpoli);
        NmPoli.setText(namapoli);
        JenisPelayanan.setSelectedItem(status);
        JenisPelayananItemStateChanged(null);
        isRawat();      
    }
      
    
    public void isCek(){
        BtnSimpan.setEnabled(akses.getinhealth_sjp());
        BtnHapus.setEnabled(akses.getinhealth_sjp());
        BtnPrint.setEnabled(akses.getinhealth_sjp());
        BtnEdit.setEnabled(akses.getinhealth_sjp());        
    }
    
    private void getData() {
        if(tbObat.getSelectedRow()!= -1){
            NoSJP.setText(tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
            TNoRw.setText(tbObat.getValueAt(tbObat.getSelectedRow(),1).toString());
            Valid.SetTgl2(TanggalSEP,tbObat.getValueAt(tbObat.getSelectedRow(),2).toString());
            TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(),4).toString());
            NoKartu.setText(tbObat.getValueAt(tbObat.getSelectedRow(),3).toString());
            TPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString());
            TglLahir.setText(tbObat.getValueAt(tbObat.getSelectedRow(),6).toString());
            JK.setText(tbObat.getValueAt(tbObat.getSelectedRow(),7).toString());
            Valid.SetTgl2(TanggalRujuk,tbObat.getValueAt(tbObat.getSelectedRow(),8).toString());
            NoRujukan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),9).toString());  
            KdPpkRujukan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),10).toString());  
            NmPpkRujukan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),11).toString());
            KdPPK.setText(tbObat.getValueAt(tbObat.getSelectedRow(),12).toString());  
            NmPPK.setText(tbObat.getValueAt(tbObat.getSelectedRow(),13).toString());      
            JenisPelayanan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),15).toString());             
            Catatan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),16).toString());
            KdPenyakit.setText(tbObat.getValueAt(tbObat.getSelectedRow(),17).toString());  
            NmPenyakit.setText(tbObat.getValueAt(tbObat.getSelectedRow(),18).toString());    
            KdPenyakit2.setText(tbObat.getValueAt(tbObat.getSelectedRow(),19).toString());  
            NmPenyakit2.setText(tbObat.getValueAt(tbObat.getSelectedRow(),20).toString());
            KdPoli.setText(tbObat.getValueAt(tbObat.getSelectedRow(),21).toString());
            NmPoli.setText(tbObat.getValueAt(tbObat.getSelectedRow(),22).toString());
            LakaLantas.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),23).toString());            
            LokasiLaka.setText(tbObat.getValueAt(tbObat.getSelectedRow(),24).toString());  
            klsPlanDes.setText(tbObat.getValueAt(tbObat.getSelectedRow(),27).toString()+" - "+tbObat.getValueAt(tbObat.getSelectedRow(),31).toString());  
            idAkomodasi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),32).toString()); 
            tipeSJP.setText(tbObat.getValueAt(tbObat.getSelectedRow(),33).toString());
            tipeCOB.setText(tbObat.getValueAt(tbObat.getSelectedRow(),34).toString());
            Kelas.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),35).toString());
            KdRuang.setText(tbObat.getValueAt(tbObat.getSelectedRow(),36).toString()); 
            NmRuang.setText(tbObat.getValueAt(tbObat.getSelectedRow(),37).toString());
        }
    }
    
    
    
    private void isForm(){
        if(ChkInput.isSelected()==true){
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH,279));
            FormInput.setVisible(true);      
            ChkInput.setVisible(true);
        }else if(ChkInput.isSelected()==false){           
            ChkInput.setVisible(false);            
            PanelInput.setPreferredSize(new Dimension(WIDTH,20));
            FormInput.setVisible(false);      
            ChkInput.setVisible(true);
        }
    }

    private void insertSEP(){
        try{
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            String URL = prop.getProperty("URLAPIINHEALTH")+"/api/SimpanSJP";	
	    HttpHeaders headers = new HttpHeaders();            
            headers.add("Content-Type","application/json");
	    requestJson ="{ \"token\": \""+prop.getProperty("TOKENINHEALTH")+"\"," +
                            "\"kodeprovider\": \""+KdPPK.getText()+"\"," +
                            "\"tanggalpelayanan\": \""+Valid.SetTgl(TanggalSEP.getSelectedItem()+"")+"\","+
                            "\"jenispelayanan\": \""+JenisPelayanan.getSelectedItem().toString().substring(0,1)+"\","+
                            "\"nokainhealth\": \""+NoKartu.getText()+"\","+
                            "\"nomormedicalreport\": \""+TNoRM.getText()+"\","+
                            "\"nomorasalrujukan\": \""+NoRujukan.getText()+"\","+
                            "\"kodeproviderasalrujukan\": \""+KdPpkRujukan.getText()+"\","+
                            "\"tanggalasalrujukan\": \""+Valid.SetTgl(TanggalRujuk.getSelectedItem()+"")+"\","+
                            "\"kodediagnosautama\": \""+KdPenyakit.getText()+"\","+
                            "\"poli\": \""+KdPoli.getText()+"\","+
                            "\"username\": \""+user+"\","+
                            "\"informasitambahan\": \""+Catatan.getText()+"\","+
                            "\"kodediagnosatambahan\": \""+KdPenyakit2.getText()+"\","+
                            "\"kecelakaankerja\": \""+LakaLantas.getSelectedItem().toString().substring(0,1)+"\","+
                            "\"kelasrawat\": \""+Kelas.getSelectedItem().toString().substring(0,3)+"\","+
                            "\"kodejenpelruangrawat\": \""+KdRuang.getText()+"\""+
                         "}";
            HttpEntity requestEntity = new HttpEntity(requestJson,headers);
            RestTemplate rest = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(rest.exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
            if(root.path("ERRORCODE").asText().equals("00")){
                if(Sequel.menyimpantf("bridging_inhealth","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","Data SJP",37,new String[]{
                    root.path("NOSJP").asText(),
                    TNoRw.getText(),
                    Valid.SetTgl(TanggalSEP.getSelectedItem() + "") + " " + TanggalSEP.getSelectedItem().toString().substring(11, 19),
                    Valid.SetTgl(TanggalRujuk.getSelectedItem() + "") + " " + TanggalRujuk.getSelectedItem().toString().substring(11, 19),
                    NoRujukan.getText(),
                    KdPpkRujukan.getText(),
                    NmPpkRujukan.getText(),
                    KdPPK.getText(),
                    NmPPK.getText(),
                    JenisPelayanan.getSelectedItem().toString().substring(0, 1),
                    Catatan.getText(),
                    KdPenyakit.getText(),
                    NmPenyakit.getText(),
                    KdPenyakit2.getText(),
                    NmPenyakit2.getText(),
                    KdPoli.getText(),
                    NmPoli.getText(),
                    Kelas.getSelectedItem().toString().substring(0, 3),
                    root.path("KELASDESC").asText(),
                    root.path("KDBU").asText(),
                    root.path("NMBU").asText(),
                    LakaLantas.getSelectedItem().toString().substring(0, 1),
                    LokasiLaka.getText(),
                    user,
                    root.path("NOMEDICALRECORD").asText(),
                    root.path("NAMAPESERTA").asText(),
                    root.path("TGLLAHIR").asText().replaceAll("T", " ").replaceAll("Z", ""),
                    JK.getText(),
                    root.path("NOKAPESERTA").asText(),
                    "0000-00-00 00:00:00",
                    root.path("PLAN").asText(),
                    root.path("PLANDESC").asText(),
                    root.path("IDAKOMODASI").asText(),
                    root.path("TIPESJP").asText(),
                    root.path("TIPECOB").asText(),
                    Kelas.getSelectedItem().toString(),
                    KdRuang.getText()
                  })==true){
                     Sequel.menyimpan("rujuk_masuk_inhealth","?,?,?,?,?,?,?,?,?,?,?",11,new String[]{
                         TNoRw.getText(),NmPpkRujukan.getText(),"-",NoRujukan.getText(),"0",NmPpkRujukan.getText(),KdPenyakit.getText(),"-",
                         "-",NoBalasan.getText(),"-"
                     });     
                     tampil();
                     emptTeks();
                }
            }else{
                JOptionPane.showMessageDialog(null,root.path("ERRORDESC").asText());
                NoKartu.requestFocus();
            }
        }catch (Exception ex) {
            System.out.println("Notifikasi Bridging : "+ex);
            if(ex.toString().contains("UnknownHostException")){
                JOptionPane.showMessageDialog(null,"Koneksi ke server Inhealth terputus...!");
            }
        }
    }

    private void deleteSJP(){
        try{
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            String URL = prop.getProperty("URLAPIINHEALTH")+"/api/HapusSJP";	
	    HttpHeaders headers = new HttpHeaders();            
            headers.add("Content-Type","application/json");
	    requestJson ="{ \"token\": \""+prop.getProperty("TOKENINHEALTH")+"\"," +
                            "\"kodeprovider\": \""+KdPPK.getText()+"\"," +
                            "\"nosjp\": \""+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"\"," +
                            "\"alasanhapus\": \""+"kesalahan-input"+"\"," +
                            "\"username\": \""+user+"\""+
                         "}";
            HttpEntity requestEntity = new HttpEntity(requestJson,headers);
            RestTemplate rest = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(rest.exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
            if(root.path("ERRORCODE").asText().equals("00")){
                Sequel.meghapus("bridging_inhealth","no_sjp",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
                tampil();
                emptTeks();
            }else{
                JOptionPane.showMessageDialog(null,root.path("ERRORDESC").asText());
            }
        }catch (Exception ex) {
            System.out.println("Notifikasi Bridging : "+ex);
            if(ex.toString().contains("UnknownHostException")){
                JOptionPane.showMessageDialog(null,"Koneksi ke server Inhealth terputus...!");
            }
        }
    }
    
    public void cekLAYAN() {
        if (JenisPelayanan.getSelectedIndex() == 0) {
            LabelPoli.setVisible(true);
            KdPoli.setVisible(true);
            NmPoli.setVisible(true);
            btnPoli.setVisible(true);

            LabelRuang.setVisible(false);
            KdRuang.setVisible(false);
            NmRuang.setVisible(false);
            btnRuang.setVisible(false);

            labelKElas.setVisible(false);
            Kelas.setVisible(false);
        } else if (JenisPelayanan.getSelectedIndex() == 1) {
            LabelPoli.setVisible(false);
            KdPoli.setVisible(false);
            NmPoli.setVisible(false);
            btnPoli.setVisible(false);

            LabelRuang.setVisible(true);
            KdRuang.setVisible(true);
            NmRuang.setVisible(true);
            btnRuang.setVisible(true);

            labelKElas.setVisible(true);
            Kelas.setVisible(true);
        }
    }
    
    private void updateTglPulang() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            String URL = prop.getProperty("URLAPIINHEALTH") + "/api/UpdateTanggalPulang";
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json");
            requestJson = "{ \"token\": \"" + prop.getProperty("TOKENINHEALTH") + "\","
                        + "\"kodeprovider\": \"" + KdPPK.getText() + "\","
                        + "\"id\": \"" + idAkomodasi.getText() + "\","
                        + "\"nosjp\": \"" + NoSJP.getText() + "\","
                        + "\"tglmasuk\": \"" + Valid.SetTgl(TanggalSEP.getSelectedItem() + "") + "\","
                        + "\"tglkeluar\": \"" + Valid.SetTgl(TanggalPulang.getSelectedItem() + "") + "\","
                        + "}";
            HttpEntity requestEntity = new HttpEntity(requestJson, headers);
            RestTemplate rest = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(rest.exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
            if (root.path("ERRORCODE").asText().equals("00")) {
                Sequel.mengedit("bridging_inhealth", "no_sjp=?", "tglpulang=?", 2, new String[]{
                    Valid.SetTgl(TanggalPulang.getSelectedItem() + "") + " " + TanggalPulang.getSelectedItem().toString().substring(11, 19),
                    NoSJP.getText()});
                
                JOptionPane.showMessageDialog(null, root.path("ERRORDESC").asText());
                tampil();
                emptTeks();
            } else {
                JOptionPane.showMessageDialog(null, root.path("ERRORDESC").asText());
                NoKartu.requestFocus();
            }
        } catch (Exception ex) {
            System.out.println("Notifikasi Bridging : " + ex);
            if (ex.toString().contains("UnknownHostException")) {
                JOptionPane.showMessageDialog(null, "Koneksi ke server Inhealth terputus...!");
            }
        }
    }
}
