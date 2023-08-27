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
import java.net.URI;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author dosen
 */
public class BPJSSPRI extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private final Properties prop = new Properties();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i = 0, x = 0;
    private BPJSCekReferensiDokterKontrol dokter = new BPJSCekReferensiDokterKontrol(null, false);
    private BPJSCekReferensiSpesialistikKontrol poli = new BPJSCekReferensiSpesialistikKontrol(null, false);
    private BPJSCekReferensiPenyakit penyakit = new BPJSCekReferensiPenyakit(null, false);
    private HttpHeaders headers;
    private HttpEntity requestEntity;
    private ObjectMapper mapper = new ObjectMapper();
    private JsonNode root;
    private JsonNode nameNode;
    private JsonNode response;
    private Date tgl = new Date();
    private String link = "", requestJson = "", URL = "", user = "", utc = "", kdICD10 = "";
    private BPJSApi api = new BPJSApi();

    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public BPJSSPRI(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode=new DefaultTableModel(null, new Object[]{
            "No. Rawat", "No. Kartu", "No. RM", "Nama Pasien", "Tgl. Lahir", "J.K.", "Diagnosa", "Tgl. Surat",
            "No. Surat", "Tgl. Rencana Inap", "Kode Dokter", "Nama Dokter/Sepesialis", "Kode Poli", "Nama Poli/Unit", "kdpenyakit"
        }) {
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        
        tbSPRI.setModel(tabMode);
        tbSPRI.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbSPRI.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 15; i++) {
            TableColumn column = tbSPRI.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(100);
            } else if (i == 2) {
                column.setPreferredWidth(80);
            } else if (i == 3) {
                column.setPreferredWidth(150);
            } else if (i == 4) {
                column.setPreferredWidth(65);
            } else if (i == 5) {
                column.setPreferredWidth(85);
            } else if (i == 6) {
                column.setPreferredWidth(150);
            } else if (i == 7) {
                column.setPreferredWidth(65);
            } else if (i == 8) {
                column.setPreferredWidth(125);
            } else if (i == 9) {
                column.setPreferredWidth(104);
            } else if (i == 10) {
                column.setPreferredWidth(70);
            } else if (i == 11) {
                column.setPreferredWidth(350);
            } else if (i == 12) {
                column.setPreferredWidth(70);
            } else if (i == 13) {
                column.setPreferredWidth(150);
            } else if (i == 14) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbSPRI.setDefaultRenderer(Object.class, new WarnaTable());

        TCari.setDocument(new batasInput((byte)100).getKata(TCari));
        
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
        
        ChkInput.setSelected(false);
        isForm();
        
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {;}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(dokter.getTable().getSelectedRow()!= -1){                    
                    KdDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                    NmDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),2).toString());
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
        
        dokter.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    dokter.dispose();
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
        
        penyakit.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(penyakit.getTable().getSelectedRow()!= -1){
                    kdICD10 = "";
                    kdICD10 = penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(),1).toString();
                    Diagnosa.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(),2).toString());                    
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
        
        try {
            user = akses.getkode().replace(" ", "").substring(0, 9);
        } catch (Exception e) {
            user = akses.getkode();
        }
        
        try {
            link = koneksiDB.URLAPIBPJS();
        } catch (Exception e) {
            System.out.println("E : " + e);
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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnSurat = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        MnAmbilSPRIVCLAIM = new javax.swing.JMenuItem();
        buttonGroup1 = new javax.swing.ButtonGroup();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbSPRI = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass10 = new widget.panelisi();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        panelCari = new widget.panelisi();
        R1 = new widget.RadioButton();
        DTPTanggalSurat1 = new widget.Tanggal();
        jLabel22 = new widget.Label();
        DTPTanggalSurat2 = new widget.Tanggal();
        LCount1 = new widget.Label();
        R2 = new widget.RadioButton();
        tglRencana1 = new widget.Tanggal();
        jLabel25 = new widget.Label();
        tglRencana2 = new widget.Tanggal();
        PanelInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        FormInput = new widget.PanelBiasa();
        jLabel4 = new widget.Label();
        NoRawat = new widget.TextBox();
        jLabel9 = new widget.Label();
        NmDokter = new widget.TextBox();
        NoKartu = new widget.TextBox();
        KdDokter = new widget.TextBox();
        BtnDokter = new widget.Button();
        jLabel11 = new widget.Label();
        KdPoli = new widget.TextBox();
        NmPoli = new widget.TextBox();
        BtnPoli = new widget.Button();
        jLabel14 = new widget.Label();
        TglRencanaInap = new widget.Tanggal();
        jLabel15 = new widget.Label();
        NoSurat = new widget.TextBox();
        jLabel5 = new widget.Label();
        jLabel12 = new widget.Label();
        NmPasien = new widget.TextBox();
        NoRM = new widget.TextBox();
        TglLahir = new widget.TextBox();
        jLabel13 = new widget.Label();
        jLabel16 = new widget.Label();
        JK = new widget.TextBox();
        jLabel17 = new widget.Label();
        Diagnosa = new widget.TextBox();
        btnDiagnosa = new widget.Button();
        jLabel8 = new widget.Label();
        dpjpRanap = new widget.TextBox();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnSurat.setBackground(new java.awt.Color(255, 255, 254));
        MnSurat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSurat.setForeground(new java.awt.Color(0, 0, 0));
        MnSurat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSurat.setText("Cetak SPRI VClaim");
        MnSurat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSurat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSurat.setName("MnSurat"); // NOI18N
        MnSurat.setPreferredSize(new java.awt.Dimension(200, 26));
        MnSurat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSuratActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnSurat);

        jPopupMenu2.setName("jPopupMenu2"); // NOI18N

        MnAmbilSPRIVCLAIM.setBackground(new java.awt.Color(255, 255, 254));
        MnAmbilSPRIVCLAIM.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnAmbilSPRIVCLAIM.setForeground(new java.awt.Color(0, 0, 0));
        MnAmbilSPRIVCLAIM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnAmbilSPRIVCLAIM.setText("Ambil SPRI dari VClaim");
        MnAmbilSPRIVCLAIM.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnAmbilSPRIVCLAIM.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnAmbilSPRIVCLAIM.setName("MnAmbilSPRIVCLAIM"); // NOI18N
        MnAmbilSPRIVCLAIM.setPreferredSize(new java.awt.Dimension(170, 26));
        MnAmbilSPRIVCLAIM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnAmbilSPRIVCLAIMActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnAmbilSPRIVCLAIM);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Surat Perintah Rawat Inap (SPRI) VClaim ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbSPRI.setAutoCreateRowSorter(true);
        tbSPRI.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbSPRI.setComponentPopupMenu(jPopupMenu1);
        tbSPRI.setName("tbSPRI"); // NOI18N
        tbSPRI.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSPRIMouseClicked(evt);
            }
        });
        tbSPRI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbSPRIKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbSPRI);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 144));
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
        BtnCari.setMnemonic('D');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+D");
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

        panelCari.setName("panelCari"); // NOI18N
        panelCari.setPreferredSize(new java.awt.Dimension(44, 43));
        panelCari.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 2, 9));

        R1.setBackground(new java.awt.Color(240, 250, 230));
        R1.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup1.add(R1);
        R1.setSelected(true);
        R1.setText("Tanggal Surat :");
        R1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        R1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        R1.setName("R1"); // NOI18N
        R1.setPreferredSize(new java.awt.Dimension(115, 23));
        panelCari.add(R1);

        DTPTanggalSurat1.setEditable(false);
        DTPTanggalSurat1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10-02-2022" }));
        DTPTanggalSurat1.setDisplayFormat("dd-MM-yyyy");
        DTPTanggalSurat1.setName("DTPTanggalSurat1"); // NOI18N
        DTPTanggalSurat1.setOpaque(false);
        DTPTanggalSurat1.setPreferredSize(new java.awt.Dimension(95, 23));
        DTPTanggalSurat1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DTPTanggalSurat1ItemStateChanged(evt);
            }
        });
        DTPTanggalSurat1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPTanggalSurat1KeyPressed(evt);
            }
        });
        panelCari.add(DTPTanggalSurat1);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("s.d");
        jLabel22.setName("jLabel22"); // NOI18N
        jLabel22.setPreferredSize(new java.awt.Dimension(25, 23));
        panelCari.add(jLabel22);

        DTPTanggalSurat2.setEditable(false);
        DTPTanggalSurat2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10-02-2022" }));
        DTPTanggalSurat2.setDisplayFormat("dd-MM-yyyy");
        DTPTanggalSurat2.setName("DTPTanggalSurat2"); // NOI18N
        DTPTanggalSurat2.setOpaque(false);
        DTPTanggalSurat2.setPreferredSize(new java.awt.Dimension(95, 23));
        DTPTanggalSurat2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPTanggalSurat2KeyPressed(evt);
            }
        });
        panelCari.add(DTPTanggalSurat2);

        LCount1.setForeground(new java.awt.Color(0, 0, 0));
        LCount1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount1.setName("LCount1"); // NOI18N
        LCount1.setPreferredSize(new java.awt.Dimension(45, 23));
        panelCari.add(LCount1);

        R2.setBackground(new java.awt.Color(240, 250, 230));
        R2.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup1.add(R2);
        R2.setText("Tgl. Rencana Inap :");
        R2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        R2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        R2.setName("R2"); // NOI18N
        R2.setPreferredSize(new java.awt.Dimension(120, 23));
        panelCari.add(R2);

        tglRencana1.setEditable(false);
        tglRencana1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10-02-2022" }));
        tglRencana1.setDisplayFormat("dd-MM-yyyy");
        tglRencana1.setName("tglRencana1"); // NOI18N
        tglRencana1.setOpaque(false);
        tglRencana1.setPreferredSize(new java.awt.Dimension(95, 23));
        tglRencana1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tglRencana1ItemStateChanged(evt);
            }
        });
        tglRencana1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tglRencana1KeyPressed(evt);
            }
        });
        panelCari.add(tglRencana1);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("s.d");
        jLabel25.setName("jLabel25"); // NOI18N
        jLabel25.setPreferredSize(new java.awt.Dimension(25, 23));
        panelCari.add(jLabel25);

        tglRencana2.setEditable(false);
        tglRencana2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10-02-2022" }));
        tglRencana2.setDisplayFormat("dd-MM-yyyy");
        tglRencana2.setName("tglRencana2"); // NOI18N
        tglRencana2.setOpaque(false);
        tglRencana2.setPreferredSize(new java.awt.Dimension(95, 23));
        tglRencana2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tglRencana2ItemStateChanged(evt);
            }
        });
        tglRencana2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tglRencana2KeyPressed(evt);
            }
        });
        panelCari.add(tglRencana2);

        jPanel3.add(panelCari, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 156));
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
        jLabel4.setText("No. Kartu :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(226, 10, 60, 23);

        NoRawat.setEditable(false);
        NoRawat.setForeground(new java.awt.Color(0, 0, 0));
        NoRawat.setName("NoRawat"); // NOI18N
        FormInput.add(NoRawat);
        NoRawat.setBounds(94, 10, 130, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Nama Dokter :");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(0, 100, 90, 23);

        NmDokter.setEditable(false);
        NmDokter.setForeground(new java.awt.Color(0, 0, 0));
        NmDokter.setName("NmDokter"); // NOI18N
        FormInput.add(NmDokter);
        NmDokter.setBounds(184, 100, 160, 23);

        NoKartu.setEditable(false);
        NoKartu.setForeground(new java.awt.Color(0, 0, 0));
        NoKartu.setComponentPopupMenu(jPopupMenu2);
        NoKartu.setName("NoKartu"); // NOI18N
        FormInput.add(NoKartu);
        NoKartu.setBounds(291, 10, 110, 23);

        KdDokter.setEditable(false);
        KdDokter.setForeground(new java.awt.Color(0, 0, 0));
        KdDokter.setName("KdDokter"); // NOI18N
        FormInput.add(KdDokter);
        KdDokter.setBounds(94, 100, 87, 23);

        BtnDokter.setForeground(new java.awt.Color(0, 0, 0));
        BtnDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/download24.png"))); // NOI18N
        BtnDokter.setMnemonic('X');
        BtnDokter.setToolTipText("Alt+X");
        BtnDokter.setName("BtnDokter"); // NOI18N
        BtnDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokterActionPerformed(evt);
            }
        });
        BtnDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDokterKeyPressed(evt);
            }
        });
        FormInput.add(BtnDokter);
        BtnDokter.setBounds(347, 100, 28, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Spesialis/Sub :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(374, 100, 80, 23);

        KdPoli.setEditable(false);
        KdPoli.setForeground(new java.awt.Color(0, 0, 0));
        KdPoli.setName("KdPoli"); // NOI18N
        FormInput.add(KdPoli);
        KdPoli.setBounds(458, 100, 70, 23);

        NmPoli.setEditable(false);
        NmPoli.setForeground(new java.awt.Color(0, 0, 0));
        NmPoli.setName("NmPoli"); // NOI18N
        FormInput.add(NmPoli);
        NmPoli.setBounds(531, 100, 212, 23);

        BtnPoli.setForeground(new java.awt.Color(0, 0, 0));
        BtnPoli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/download24.png"))); // NOI18N
        BtnPoli.setMnemonic('X');
        BtnPoli.setToolTipText("Alt+X");
        BtnPoli.setName("BtnPoli"); // NOI18N
        BtnPoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPoliActionPerformed(evt);
            }
        });
        BtnPoli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPoliKeyPressed(evt);
            }
        });
        FormInput.add(BtnPoli);
        BtnPoli.setBounds(745, 100, 28, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Tgl. Rencana Inap :");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(268, 70, 100, 23);

        TglRencanaInap.setEditable(false);
        TglRencanaInap.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10-02-2022" }));
        TglRencanaInap.setDisplayFormat("dd-MM-yyyy");
        TglRencanaInap.setName("TglRencanaInap"); // NOI18N
        TglRencanaInap.setOpaque(false);
        FormInput.add(TglRencanaInap);
        TglRencanaInap.setBounds(374, 70, 95, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("No. Surat :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(0, 70, 90, 23);

        NoSurat.setEditable(false);
        NoSurat.setForeground(new java.awt.Color(0, 0, 0));
        NoSurat.setName("NoSurat"); // NOI18N
        FormInput.add(NoSurat);
        NoSurat.setBounds(94, 70, 170, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("No. Rawat :");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(0, 10, 90, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Pasien :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(0, 40, 90, 23);

        NmPasien.setEditable(false);
        NmPasien.setForeground(new java.awt.Color(0, 0, 0));
        NmPasien.setName("NmPasien"); // NOI18N
        FormInput.add(NmPasien);
        NmPasien.setBounds(167, 40, 270, 23);

        NoRM.setEditable(false);
        NoRM.setForeground(new java.awt.Color(0, 0, 0));
        NoRM.setName("NoRM"); // NOI18N
        FormInput.add(NoRM);
        NoRM.setBounds(94, 40, 70, 23);

        TglLahir.setEditable(false);
        TglLahir.setForeground(new java.awt.Color(0, 0, 0));
        TglLahir.setName("TglLahir"); // NOI18N
        FormInput.add(TglLahir);
        TglLahir.setBounds(510, 40, 95, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Tgl. Lahir :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(441, 40, 62, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("J.K. :");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(608, 40, 35, 23);

        JK.setEditable(false);
        JK.setForeground(new java.awt.Color(0, 0, 0));
        JK.setName("JK"); // NOI18N
        FormInput.add(JK);
        JK.setBounds(648, 40, 95, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Diagnosa :");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(470, 70, 60, 23);

        Diagnosa.setEditable(false);
        Diagnosa.setForeground(new java.awt.Color(0, 0, 0));
        Diagnosa.setName("Diagnosa"); // NOI18N
        FormInput.add(Diagnosa);
        Diagnosa.setBounds(536, 70, 207, 23);

        btnDiagnosa.setForeground(new java.awt.Color(0, 0, 0));
        btnDiagnosa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/download24.png"))); // NOI18N
        btnDiagnosa.setMnemonic('X');
        btnDiagnosa.setToolTipText("Alt+X");
        btnDiagnosa.setName("btnDiagnosa"); // NOI18N
        btnDiagnosa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDiagnosaActionPerformed(evt);
            }
        });
        FormInput.add(btnDiagnosa);
        btnDiagnosa.setBounds(745, 70, 28, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("DPJP Inap : ");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(401, 10, 70, 23);

        dpjpRanap.setEditable(false);
        dpjpRanap.setForeground(new java.awt.Color(0, 0, 0));
        dpjpRanap.setName("dpjpRanap"); // NOI18N
        FormInput.add(dpjpRanap);
        dpjpRanap.setBounds(474, 10, 270, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (NoRawat.getText().trim().equals("") || NoKartu.getText().trim().equals("")) {
            Valid.textKosong(NoRawat, "pasien");
        } else if (NmDokter.getText().trim().equals("") || KdDokter.getText().trim().equals("")) {
            Valid.textKosong(KdDokter, "Dokter");
        } else if (NmPoli.getText().trim().equals("") || NmPoli.getText().trim().equals("")) {
            Valid.textKosong(KdPoli, "Poli");
        } else if (Diagnosa.getText().trim().equals("")) {
            Valid.textKosong(btnDiagnosa, "Diagnosa");
        } else {
            try {
                headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                headers.add("X-Cons-ID", koneksiDB.CONSIDAPIBPJS());
                utc = String.valueOf(api.GetUTCdatetimeAsString());
                headers.add("X-Timestamp", utc);
                headers.add("X-Signature", api.getHmac(utc));
                headers.add("user_key", koneksiDB.USERKEYAPIBPJS());
                URL = link + "/RencanaKontrol/InsertSPRI";
                
                requestJson = "{"
                        + "\"request\": {"
                        + "\"noKartu\":\"" + NoKartu.getText() + "\","
                        + "\"kodeDokter\":\"" + KdDokter.getText() + "\","
                        + "\"poliKontrol\":\"" + KdPoli.getText() + "\","
                        + "\"tglRencanaKontrol\":\"" + Valid.SetTgl(TglRencanaInap.getSelectedItem() + "") + "\","
                        + "\"user\":\"" + user + "\""
                        + "}"
                        + "}";
                
                System.out.println("Ini mengirim JSON : " + requestJson);
                requestEntity = new HttpEntity(requestJson, headers);
                root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                nameNode = root.path("metaData");
                System.out.println("code : " + nameNode.path("code").asText());
                System.out.println("message : " + nameNode.path("message").asText());
                JOptionPane.showMessageDialog(rootPane, "Notifikasi WS VClaim 2.0 : Kode " + nameNode.path("code").asText() + ", Isi pesan : " + nameNode.path("message").asText());
                
                if (nameNode.path("code").asText().equals("200")) {
                    response = mapper.readTree(api.Decrypt(root.path("response").asText(), utc)).path("noSPRI");
                    System.out.println("Ini responnya : " + mapper.readTree(api.Decrypt(root.path("response").asText(), utc)));
                    //response = root.path("response").path("noSPRI");

                    if (Sequel.menyimpantf("bridging_surat_pri_bpjs", "?,?,?,?,?,?,?,?,?,?,?", "SPRI VClaim", 11, new String[]{
                        NoRawat.getText(), NoKartu.getText(), Sequel.cariIsi("SELECT DATE(NOW())"), response.asText(), 
                        Valid.SetTgl(TglRencanaInap.getSelectedItem() + ""), KdDokter.getText(), 
                        NmDokter.getText(), KdPoli.getText(), NmPoli.getText(), Diagnosa.getText(), kdICD10
                    }) == true) {
                        emptTeks();
                        tampil();
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Notifikasi WS VClaim 2.0 : Kode " + nameNode.path("code").asText() + ", Isi pesan : " + nameNode.path("message").asText());
                }
            } catch (Exception ex) {
                System.out.println("Notifikasi Bridging : " + ex);
                if (ex.toString().contains("UnknownHostException")) {
                    JOptionPane.showMessageDialog(null, "Koneksi ke server BPJS terputus...!");
                }
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, NoSurat, BtnBatal);
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
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnPrint, TCari);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnBatal.requestFocus();
        } else if (tabMode.getRowCount() != 0) {
            if (R1.isSelected() == true) {
                Map<String, Object> param = new HashMap<>();
                param.put("namars", akses.getnamars());
                param.put("alamatrs", akses.getalamatrs());
                param.put("kotars", akses.getkabupatenrs());
                param.put("propinsirs", akses.getpropinsirs());
                param.put("kontakrs", akses.getkontakrs());
                param.put("emailrs", akses.getemailrs());
                param.put("logo", Sequel.cariGambar("select logo from setting"));
                Valid.MyReport("rptBridgingSuratPRI.jasper", "report", "::[ Data Surat PRI VClaim ]::",
                        "select bridging_surat_pri_bpjs.no_rawat,bridging_surat_pri_bpjs.no_kartu,reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.tgl_lahir,"
                        + "pasien.jk,bridging_surat_pri_bpjs.diagnosa,bridging_surat_pri_bpjs.tgl_surat,bridging_surat_pri_bpjs.no_surat,"
                        + "bridging_surat_pri_bpjs.tgl_rencana,bridging_surat_pri_bpjs.kd_dokter_bpjs,bridging_surat_pri_bpjs.nm_dokter_bpjs,"
                        + "bridging_surat_pri_bpjs.kd_poli_bpjs,bridging_surat_pri_bpjs.nm_poli_bpjs from reg_periksa inner join bridging_surat_pri_bpjs "
                        + "on bridging_surat_pri_bpjs.no_rawat=reg_periksa.no_rawat inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                        + "where bridging_surat_pri_bpjs.tgl_surat between '" + Valid.SetTgl(DTPTanggalSurat1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPTanggalSurat2.getSelectedItem() + "") + "' "
                        + (TCari.getText().trim().equals("") ? "" : "and (bridging_surat_pri_bpjs.no_rawat like '%" + TCari.getText().trim() + "%' or "
                        + "bridging_surat_pri_bpjs.no_kartu like '%" + TCari.getText().trim() + "%' or reg_periksa.no_rkm_medis like '%" + TCari.getText().trim() + "%' or pasien.nm_pasien like '%" + TCari.getText().trim() + "%' or bridging_surat_pri_bpjs.no_surat like '%" + TCari.getText().trim() + "%' or "
                        + "bridging_surat_pri_bpjs.nm_poli_bpjs like '%" + TCari.getText().trim() + "%' or bridging_surat_pri_bpjs.nm_dokter_bpjs like '%" + TCari.getText().trim() + "%')")
                        + "order by bridging_surat_pri_bpjs.tgl_surat", param);
            } else if (R2.isSelected() == true) {
                Map<String, Object> param = new HashMap<>();
                param.put("namars", akses.getnamars());
                param.put("alamatrs", akses.getalamatrs());
                param.put("kotars", akses.getkabupatenrs());
                param.put("propinsirs", akses.getpropinsirs());
                param.put("kontakrs", akses.getkontakrs());
                param.put("emailrs", akses.getemailrs());
                param.put("logo", Sequel.cariGambar("select logo from setting"));
                Valid.MyReport("rptBridgingSuratPRI.jasper", "report", "::[ Data Surat PRI VClaim ]::",
                        "select bridging_surat_pri_bpjs.no_rawat,bridging_surat_pri_bpjs.no_kartu,reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.tgl_lahir,"
                        + "pasien.jk,bridging_surat_pri_bpjs.diagnosa,bridging_surat_pri_bpjs.tgl_surat,bridging_surat_pri_bpjs.no_surat,"
                        + "bridging_surat_pri_bpjs.tgl_rencana,bridging_surat_pri_bpjs.kd_dokter_bpjs,bridging_surat_pri_bpjs.nm_dokter_bpjs,"
                        + "bridging_surat_pri_bpjs.kd_poli_bpjs,bridging_surat_pri_bpjs.nm_poli_bpjs from reg_periksa inner join bridging_surat_pri_bpjs "
                        + "on bridging_surat_pri_bpjs.no_rawat=reg_periksa.no_rawat inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                        + "where bridging_surat_pri_bpjs.tgl_rencana between '" + Valid.SetTgl(tglRencana1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(tglRencana2.getSelectedItem() + "") + "' "
                        + (TCari.getText().trim().equals("") ? "" : "and (bridging_surat_pri_bpjs.no_rawat like '%" + TCari.getText().trim() + "%' or "
                        + "bridging_surat_pri_bpjs.no_kartu like '%" + TCari.getText().trim() + "%' or reg_periksa.no_rkm_medis like '%" + TCari.getText().trim() + "%' or pasien.nm_pasien like '%" + TCari.getText().trim() + "%' or bridging_surat_pri_bpjs.no_surat like '%" + TCari.getText().trim() + "%' or "
                        + "bridging_surat_pri_bpjs.nm_poli_bpjs like '%" + TCari.getText().trim() + "%' or bridging_surat_pri_bpjs.nm_dokter_bpjs like '%" + TCari.getText().trim() + "%')")
                        + "order by bridging_surat_pri_bpjs.tgl_rencana", param);
            }

        }
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPrintActionPerformed(null);
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
            Valid.pindah(evt, BtnCari, NoKartu);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbSPRIMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSPRIMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbSPRIMouseClicked

private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
    if (KdPoli.getText().equals("") || NmPoli.getText().equals("")) {
        Valid.textKosong(BtnPoli, "Spesialis/Sub Spesialis");
    } else {
        dokter.SetKontrol(KdPoli.getText(), "1", Valid.SetTgl(TglRencanaInap.getSelectedItem() + ""));
        dokter.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);
        dokter.tampil();
        dokter.TCari.requestFocus();
    }
}//GEN-LAST:event_BtnDokterActionPerformed

private void BtnDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokterKeyPressed
    if(evt.getKeyCode()==KeyEvent.VK_SPACE){
        BtnDokterActionPerformed(null);
    }else{
        Valid.pindah(evt,TglRencanaInap,BtnPoli);
    }        
}//GEN-LAST:event_BtnDokterKeyPressed

private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
  isForm();                
}//GEN-LAST:event_ChkInputActionPerformed

    private void tglRencana1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tglRencana1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tglRencana1KeyPressed

    private void tglRencana2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tglRencana2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tglRencana2KeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (NoRawat.getText().trim().equals("") || NoKartu.getText().trim().equals("")) {
            Valid.textKosong(NoRawat, "pasien");
        } else if (NmDokter.getText().trim().equals("") || KdDokter.getText().trim().equals("")) {
            Valid.textKosong(KdDokter, "Dokter");
        } else if (NmPoli.getText().trim().equals("") || NmPoli.getText().trim().equals("")) {
            Valid.textKosong(KdPoli, "Poli");
        } else {
            if (tbSPRI.getSelectedRow() != -1) {
                try {
                    headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                    headers.add("X-Cons-ID", koneksiDB.CONSIDAPIBPJS());
                    utc = String.valueOf(api.GetUTCdatetimeAsString());
                    headers.add("X-Timestamp", utc);
                    headers.add("X-Signature", api.getHmac(utc));
                    headers.add("user_key", koneksiDB.USERKEYAPIBPJS());
                    URL = link + "/RencanaKontrol/UpdateSPRI";
                    
                    requestJson = "{"
                            + "\"request\": {"
                            + "\"noSPRI\":\"" + NoSurat.getText() + "\","
                            + "\"kodeDokter\":\"" + KdDokter.getText() + "\","
                            + "\"poliKontrol\":\"" + KdPoli.getText() + "\","
                            + "\"tglRencanaKontrol\":\"" + Valid.SetTgl(TglRencanaInap.getSelectedItem() + "") + "\","
                            + "\"user\":\"" + user + "\""
                            + "}"
                            + "}";
                    
                    System.out.println("Ini menngirim JSON : " + requestJson);
                    requestEntity = new HttpEntity(requestJson, headers);
                    root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.PUT, requestEntity, String.class).getBody());
                    nameNode = root.path("metaData");
                    System.out.println("code : " + nameNode.path("code").asText());
                    System.out.println("message : " + nameNode.path("message").asText());
                    JOptionPane.showMessageDialog(rootPane, "Notifikasi WS VClaim 2.0 : Kode " + nameNode.path("code").asText() + ", Isi pesan : " + nameNode.path("message").asText());
                    
                    if (nameNode.path("code").asText().equals("200")) {
                        if (Sequel.mengedittf("bridging_surat_pri_bpjs", "no_surat=?",
                                "tgl_rencana=?,kd_dokter_bpjs=?,nm_dokter_bpjs=?,kd_poli_bpjs=?,nm_poli_bpjs=?,diagnosa=?,kd_penyakit=?", 8, new String[]{
                                    Valid.SetTgl(TglRencanaInap.getSelectedItem() + ""), KdDokter.getText(), NmDokter.getText(), KdPoli.getText(),
                                    NmPoli.getText(), Diagnosa.getText(), NoSurat.getText(), kdICD10
                        }) == true) {
                            emptTeks();
                            tampil();
                        }
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Notifikasi WS VClaim 2.0 : Kode " + nameNode.path("code").asText() + ", Isi pesan : " + nameNode.path("message").asText());
                    }
                } catch (Exception ex) {
                    System.out.println("Notifikasi Bridging : " + ex);
                    if (ex.toString().contains("UnknownHostException")) {
                        JOptionPane.showMessageDialog(null, "Koneksi ke server BPJS terputus...!");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih terlebih dulu data yang mau anda ganti... Klik data pada table untuk memilih data...!!!!");
            }
        }
    }//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnEditActionPerformed(null);
        }
    }//GEN-LAST:event_BtnEditKeyPressed

    private void tglRencana1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tglRencana1ItemStateChanged
        R2.setSelected(true);
    }//GEN-LAST:event_tglRencana1ItemStateChanged

    private void tglRencana2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tglRencana2ItemStateChanged
        R2.setSelected(true);
    }//GEN-LAST:event_tglRencana2ItemStateChanged

    private void BtnPoliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPoliKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPoliActionPerformed(null);
        }else{
            Valid.pindah(evt,BtnDokter,TglRencanaInap);
        }
    }//GEN-LAST:event_BtnPoliKeyPressed

    private void BtnPoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPoliActionPerformed
        poli.SetKontrol(NoKartu.getText(), "1", Valid.SetTgl(TglRencanaInap.getSelectedItem() + ""));
        poli.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        poli.setLocationRelativeTo(internalFrame1);
        poli.setVisible(true);
        poli.tampil();
        poli.TCari.requestFocus();
    }//GEN-LAST:event_BtnPoliActionPerformed

    private void MnSuratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSuratActionPerformed
        if (tbSPRI.getSelectedRow() != -1) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("logo", Sequel.cariGambar("select bpjs from gambar"));
            param.put("tgl_lahir", Sequel.cariIsi("select DATE_FORMAT(tgl_lahir,'%d') from pasien where no_rkm_medis='" + tbSPRI.getValueAt(tbSPRI.getSelectedRow(), 2).toString() + "'") + " "
                    + Sequel.bulanINDONESIA("select DATE_FORMAT(tgl_lahir,'%m') from pasien where no_rkm_medis='" + tbSPRI.getValueAt(tbSPRI.getSelectedRow(), 2).toString() + "'") + " "
                    + Sequel.cariIsi("select DATE_FORMAT(tgl_lahir,'%Y') from pasien where no_rkm_medis='" + tbSPRI.getValueAt(tbSPRI.getSelectedRow(), 2).toString() + "'"));
            param.put("tgl_rencana", Sequel.cariIsi("select DATE_FORMAT(tgl_rencana,'%d') from bridging_surat_pri_bpjs where no_surat='" + tbSPRI.getValueAt(tbSPRI.getSelectedRow(), 8).toString() + "'") + " "
                    + Sequel.bulanINDONESIA("select DATE_FORMAT(tgl_rencana,'%m') from bridging_surat_pri_bpjs where no_surat='" + tbSPRI.getValueAt(tbSPRI.getSelectedRow(), 8).toString() + "'") + " "
                    + Sequel.cariIsi("select DATE_FORMAT(tgl_rencana,'%Y') from bridging_surat_pri_bpjs where no_surat='" + tbSPRI.getValueAt(tbSPRI.getSelectedRow(), 8).toString() + "'"));
            
            Valid.MyReport("rptBridgingSuratPRI2.jasper", "report", "::[ Cetak SPRI VClaim ]::",
                    " SELECT bsp.no_surat, p.no_peserta, CONCAT(p.nm_pasien,' (',IF(p.jk='L','Laki-laki','Perempuan'),')') peserta, "
                    + "bsp.diagnosa, bsp.nm_dokter_bpjs FROM bridging_surat_pri_bpjs bsp INNER JOIN reg_periksa rp ON rp.no_rawat=bsp.no_rawat "
                    + "INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis WHERE bsp.no_surat='" + tbSPRI.getValueAt(tbSPRI.getSelectedRow(), 8).toString() + "'", param);
            this.setCursor(Cursor.getDefaultCursor());
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih data Surat PRI yang mau dicetak...!!!!");
            BtnBatal.requestFocus();
        }
    }//GEN-LAST:event_MnSuratActionPerformed

    private void DTPTanggalSurat1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPTanggalSurat1KeyPressed

    }//GEN-LAST:event_DTPTanggalSurat1KeyPressed

    private void DTPTanggalSurat1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DTPTanggalSurat1ItemStateChanged
        R1.setSelected(true);
    }//GEN-LAST:event_DTPTanggalSurat1ItemStateChanged

    private void DTPTanggalSurat2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPTanggalSurat2KeyPressed
        R1.setSelected(true);
    }//GEN-LAST:event_DTPTanggalSurat2KeyPressed

    private void btnDiagnosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDiagnosaActionPerformed
        penyakit.setSize(internalFrame1.getWidth()-40,internalFrame1.getHeight()-40);
        penyakit.setLocationRelativeTo(internalFrame1);
        penyakit.setVisible(true);
        penyakit.diagnosa.requestFocus();
    }//GEN-LAST:event_btnDiagnosaActionPerformed

    private void tbSPRIKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbSPRIKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbSPRIKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbSPRI.getSelectedRow() != -1) {
            try {
                x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    bodyWithDeleteRequest();
                }
            } catch (Exception ex) {
                System.out.println("Notifikasi Bridging : " + ex);
                if (ex.toString().contains("UnknownHostException")) {
                    JOptionPane.showMessageDialog(null, "Koneksi ke server BPJS terputus...!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu data yang mau dihapus..!!");
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnPrint);
        }
    }//GEN-LAST:event_BtnHapusKeyPressed

    private void MnAmbilSPRIVCLAIMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnAmbilSPRIVCLAIMActionPerformed
        if (NoRawat.getText().equals("") && NoKartu.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, No. Rawat & No. Kartu harus terisi dg. benar...!!!");
        } else if (Diagnosa.getText().trim().equals("")) {
            Valid.textKosong(btnDiagnosa, "Diagnosa");
        } else {
            try {
                prop.loadFromXML(new FileInputStream("setting/database.xml"));
                URL = prop.getProperty("URLAPIBPJS") + "/RencanaKontrol/ListRencanaKontrol/tglAwal/" + Valid.SetTgl(TglRencanaInap.getSelectedItem() + "") + "/tglAkhir/" + Valid.SetTgl(TglRencanaInap.getSelectedItem() + "") + "/filter/2";

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.add("X-Cons-ID", Sequel.decXML2(prop.getProperty("CONSIDAPIBPJS"), prop.getProperty("KEY")));
                utc = String.valueOf(api.GetUTCdatetimeAsString());
                headers.add("X-Timestamp", utc);
                headers.add("X-Signature", api.getHmac(utc));
                headers.add("user_key", koneksiDB.USERKEYAPIBPJS());

                HttpEntity requestEntity = new HttpEntity(headers);
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.GET, requestEntity, String.class).getBody());
                JsonNode nameNode = root.path("metaData");
                System.out.println("code : " + nameNode.path("code").asText());
                System.out.println("message : " + nameNode.path("message").asText());

                if (nameNode.path("code").asText().equals("200")) {
                    //ini yang baru -----------
                    JsonNode response = mapper.readTree(api.Decrypt(root.path("response").asText(), utc));
                    if (response.path("list").isArray()) {
                        for (JsonNode list : response.path("list")) {
                            if (list.path("noKartu").asText().toLowerCase().contains(NoKartu.getText().toLowerCase())
                                    && list.path("tglRencanaKontrol").asText().toLowerCase().contains(Valid.SetTgl(TglRencanaInap.getSelectedItem() + ""))
                                    && (list.path("namaJnsKontrol").asText().toLowerCase().contains("SPRI") || list.path("jnsKontrol").asText().toLowerCase().contains("1"))) {                                
                                Sequel.menyimpan("bridging_surat_pri_bpjs",
                                        "'" + NoRawat.getText() + "',"
                                        + "'" + NoKartu.getText() + "',"
                                        + "'" + list.path("tglTerbitKontrol").asText() + "',"
                                        + "'" + list.path("noSuratKontrol").asText() + "',"
                                        + "'" + list.path("tglRencanaKontrol").asText() + "',"
                                        + "'" + list.path("kodeDokter").asText() + "',"
                                        + "'" + list.path("namaDokter").asText().toUpperCase() + "',"                                        
                                        + "'" + list.path("poliTujuan").asText() + "',"
                                        + "'" + list.path("namaPoliTujuan").asText() + "',"
                                        + "'" + Diagnosa.getText() + "',"
                                        + "'" + kdICD10 + "'");

                                Valid.SetTgl(DTPTanggalSurat1, list.path("tglTerbitKontrol").asText());
                                tampil();
                                JOptionPane.showMessageDialog(rootPane, "Surat Perintah Rawat Inap (SPRI) dari VClaim berhasil disimpan..!!");
                                emptTeks();                                                                                                
                                break;
                            }
                        }
                    }

                    if (tabMode.getRowCount() <= 0) {
                        JOptionPane.showMessageDialog(rootPane, "Surat Perintah Rawat Inap (SPRI) dari VClaim tdk. ditemukan, periksa kembali tgl. rencana inap..!!");
                        TglRencanaInap.requestFocus();
                    }
                    //sampai sini -------------
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Notifikasi WS VClaim 2.0 : Kode " + nameNode.path("code").asText() + ", Pesan : " + nameNode.path("message").asText());
                }
            } catch (Exception ex) {
                System.out.println("Notifikasi Peserta : " + ex);
                if (ex.toString().contains("UnknownHostException")) {
                    JOptionPane.showMessageDialog(null, "Koneksi ke server BPJS terputus...!");
                }
            }
        }
    }//GEN-LAST:event_MnAmbilSPRIVCLAIMActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            BPJSSPRI dialog = new BPJSSPRI(new javax.swing.JFrame(), true);
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
    private widget.Button BtnDokter;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPoli;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    public widget.CekBox ChkInput;
    private widget.Tanggal DTPTanggalSurat1;
    private widget.Tanggal DTPTanggalSurat2;
    private widget.TextBox Diagnosa;
    private widget.PanelBiasa FormInput;
    private widget.TextBox JK;
    private widget.TextBox KdDokter;
    private widget.TextBox KdPoli;
    private widget.Label LCount;
    private widget.Label LCount1;
    private javax.swing.JMenuItem MnAmbilSPRIVCLAIM;
    private javax.swing.JMenuItem MnSurat;
    private widget.TextBox NmDokter;
    private widget.TextBox NmPasien;
    private widget.TextBox NmPoli;
    private widget.TextBox NoKartu;
    private widget.TextBox NoRM;
    private widget.TextBox NoRawat;
    private widget.TextBox NoSurat;
    private javax.swing.JPanel PanelInput;
    private widget.RadioButton R1;
    private widget.RadioButton R2;
    private widget.ScrollPane Scroll;
    public widget.TextBox TCari;
    private widget.TextBox TglLahir;
    private widget.Tanggal TglRencanaInap;
    private widget.Button btnDiagnosa;
    private javax.swing.ButtonGroup buttonGroup1;
    private widget.TextBox dpjpRanap;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.Label jLabel22;
    private widget.Label jLabel25;
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private widget.panelisi panelCari;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass8;
    private widget.Table tbSPRI;
    private widget.Tanggal tglRencana1;
    private widget.Tanggal tglRencana2;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            if (R1.isSelected() == true) {
                ps = koneksi.prepareStatement(
                        "SELECT bsp.no_rawat, bsp.no_kartu, rp.no_rkm_medis, p.nm_pasien, p.tgl_lahir, if(p.jk='L','Laki-laki','Perempuan') jk, "
                        + "bsp.diagnosa, bsp.tgl_surat, bsp.no_surat, bsp.tgl_rencana, bsp.kd_dokter_bpjs, bsp.nm_dokter_bpjs, bsp.kd_poli_bpjs, "
                        + "bsp.nm_poli_bpjs, ifnull(bsp.kd_penyakit,'-') icd10 FROM reg_periksa rp INNER JOIN bridging_surat_pri_bpjs bsp ON bsp.no_rawat=rp.no_rawat "
                        + "INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis WHERE "
                        + "bsp.tgl_surat BETWEEN ? AND ? AND bsp.no_rawat LIKE ? OR "
                        + "bsp.tgl_surat BETWEEN ? AND ? AND bsp.no_kartu LIKE ? OR "
                        + "bsp.tgl_surat BETWEEN ? AND ? AND rp.no_rkm_medis LIKE ? OR "
                        + "bsp.tgl_surat BETWEEN ? AND ? AND p.nm_pasien LIKE ? OR "
                        + "bsp.tgl_surat BETWEEN ? AND ? AND bsp.no_surat LIKE ? OR "
                        + "bsp.tgl_surat BETWEEN ? AND ? AND bsp.nm_poli_bpjs LIKE ? OR "
                        + "bsp.tgl_surat BETWEEN ? AND ? AND bsp.nm_dokter_bpjs LIKE ? ORDER BY bsp.tgl_surat");
            } else {
                ps = koneksi.prepareStatement(
                        "SELECT bsp.no_rawat, bsp.no_kartu, rp.no_rkm_medis, p.nm_pasien, p.tgl_lahir, if(p.jk='L','Laki-laki','Perempuan') jk,"
                        + "bsp.diagnosa, bsp.tgl_surat, bsp.no_surat, bsp.tgl_rencana, bsp.kd_dokter_bpjs, bsp.nm_dokter_bpjs, bsp.kd_poli_bpjs, "
                        + "bsp.nm_poli_bpjs, ifnull(bsp.kd_penyakit,'-') icd10 FROM reg_periksa rp INNER JOIN bridging_surat_pri_bpjs bsp ON bsp.no_rawat=rp.no_rawat "
                        + "INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis WHERE "
                        + "bsp.tgl_rencana BETWEEN ? AND ? AND bsp.no_rawat LIKE ? OR "
                        + "bsp.tgl_rencana BETWEEN ? AND ? AND bsp.no_kartu LIKE ? OR "
                        + "bsp.tgl_rencana BETWEEN ? AND ? AND rp.no_rkm_medis LIKE ? OR "
                        + "bsp.tgl_rencana BETWEEN ? AND ? AND p.nm_pasien LIKE ? OR "
                        + "bsp.tgl_rencana BETWEEN ? AND ? AND bsp.no_surat LIKE ? OR "
                        + "bsp.tgl_rencana BETWEEN ? AND ? AND bsp.nm_poli_bpjs LIKE ? OR "
                        + "bsp.tgl_rencana BETWEEN ? AND ? AND bsp.nm_dokter_bpjs LIKE ? ORDER BY bsp.tgl_rencana");
            }

            try {
                if (R1.isSelected() == true) {
                    ps.setString(1, Valid.SetTgl(DTPTanggalSurat1.getSelectedItem() + ""));
                    ps.setString(2, Valid.SetTgl(DTPTanggalSurat2.getSelectedItem() + ""));
                    ps.setString(3, "%" + TCari.getText().trim() + "%");
                    ps.setString(4, Valid.SetTgl(DTPTanggalSurat1.getSelectedItem() + ""));
                    ps.setString(5, Valid.SetTgl(DTPTanggalSurat2.getSelectedItem() + ""));
                    ps.setString(6, "%" + TCari.getText().trim() + "%");
                    ps.setString(7, Valid.SetTgl(DTPTanggalSurat1.getSelectedItem() + ""));
                    ps.setString(8, Valid.SetTgl(DTPTanggalSurat2.getSelectedItem() + ""));
                    ps.setString(9, "%" + TCari.getText().trim() + "%");
                    ps.setString(10, Valid.SetTgl(DTPTanggalSurat1.getSelectedItem() + ""));
                    ps.setString(11, Valid.SetTgl(DTPTanggalSurat2.getSelectedItem() + ""));
                    ps.setString(12, "%" + TCari.getText().trim() + "%");
                    ps.setString(13, Valid.SetTgl(DTPTanggalSurat1.getSelectedItem() + ""));
                    ps.setString(14, Valid.SetTgl(DTPTanggalSurat2.getSelectedItem() + ""));
                    ps.setString(15, "%" + TCari.getText().trim() + "%");
                    ps.setString(16, Valid.SetTgl(DTPTanggalSurat1.getSelectedItem() + ""));
                    ps.setString(17, Valid.SetTgl(DTPTanggalSurat2.getSelectedItem() + ""));
                    ps.setString(18, "%" + TCari.getText().trim() + "%");
                    ps.setString(19, Valid.SetTgl(DTPTanggalSurat1.getSelectedItem() + ""));
                    ps.setString(20, Valid.SetTgl(DTPTanggalSurat2.getSelectedItem() + ""));
                    ps.setString(21, "%" + TCari.getText().trim() + "%");
                } else {
                    ps.setString(1, Valid.SetTgl(tglRencana1.getSelectedItem() + ""));
                    ps.setString(2, Valid.SetTgl(tglRencana2.getSelectedItem() + ""));
                    ps.setString(3, "%" + TCari.getText().trim() + "%");
                    ps.setString(4, Valid.SetTgl(tglRencana1.getSelectedItem() + ""));
                    ps.setString(5, Valid.SetTgl(tglRencana2.getSelectedItem() + ""));
                    ps.setString(6, "%" + TCari.getText().trim() + "%");
                    ps.setString(7, Valid.SetTgl(tglRencana1.getSelectedItem() + ""));
                    ps.setString(8, Valid.SetTgl(tglRencana2.getSelectedItem() + ""));
                    ps.setString(9, "%" + TCari.getText().trim() + "%");
                    ps.setString(10, Valid.SetTgl(tglRencana1.getSelectedItem() + ""));
                    ps.setString(11, Valid.SetTgl(tglRencana2.getSelectedItem() + ""));
                    ps.setString(12, "%" + TCari.getText().trim() + "%");
                    ps.setString(13, Valid.SetTgl(tglRencana1.getSelectedItem() + ""));
                    ps.setString(14, Valid.SetTgl(tglRencana2.getSelectedItem() + ""));
                    ps.setString(15, "%" + TCari.getText().trim() + "%");
                    ps.setString(16, Valid.SetTgl(tglRencana1.getSelectedItem() + ""));
                    ps.setString(17, Valid.SetTgl(tglRencana2.getSelectedItem() + ""));
                    ps.setString(18, "%" + TCari.getText().trim() + "%");
                    ps.setString(19, Valid.SetTgl(tglRencana1.getSelectedItem() + ""));
                    ps.setString(20, Valid.SetTgl(tglRencana2.getSelectedItem() + ""));
                    ps.setString(21, "%" + TCari.getText().trim() + "%");
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new Object[]{
                        rs.getString("no_rawat"), rs.getString("no_kartu"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"),
                        rs.getString("tgl_lahir"), rs.getString("jk"), rs.getString("diagnosa"), rs.getString("tgl_surat"), rs.getString("no_surat"),
                        rs.getString("tgl_rencana"), rs.getString("kd_dokter_bpjs"), rs.getString("nm_dokter_bpjs"), rs.getString("kd_poli_bpjs"),
                        rs.getString("nm_poli_bpjs"), rs.getString("icd10")
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
            System.out.println("Notif : " + e);
        }
        LCount.setText("" + tabMode.getRowCount());
    }

    public void emptTeks() {
//        tgl.setDate(1);
//        tglRencana1.setDate(tgl);
//        tglRencana2.setDate(new Date());

        R1.setSelected(true);
        NoRawat.setText("");
        NoKartu.setText("");
        NoRM.setText("");
        NmPasien.setText("");
        TglLahir.setText("");
        JK.setText("");
        Diagnosa.setText("");
        NoSurat.setText("");
        TglRencanaInap.setDate(new Date());
        KdDokter.setText("");
        NmDokter.setText("");
        KdPoli.setText("");
        NmPoli.setText("");
        dpjpRanap.setText("");
        Diagnosa.requestFocus();
    }   

    private void getData() {
        kdICD10 = "";
        
        if (tbSPRI.getSelectedRow() != -1) {
            NoRawat.setText(tbSPRI.getValueAt(tbSPRI.getSelectedRow(), 0).toString());
            NoKartu.setText(tbSPRI.getValueAt(tbSPRI.getSelectedRow(), 1).toString());
            NoRM.setText(tbSPRI.getValueAt(tbSPRI.getSelectedRow(), 2).toString());
            NmPasien.setText(tbSPRI.getValueAt(tbSPRI.getSelectedRow(), 3).toString());
            TglLahir.setText(tbSPRI.getValueAt(tbSPRI.getSelectedRow(), 4).toString());
            JK.setText(tbSPRI.getValueAt(tbSPRI.getSelectedRow(), 5).toString());
            Diagnosa.setText(tbSPRI.getValueAt(tbSPRI.getSelectedRow(), 6).toString());
            NoSurat.setText(tbSPRI.getValueAt(tbSPRI.getSelectedRow(), 8).toString());
            Valid.SetTgl(TglRencanaInap, tbSPRI.getValueAt(tbSPRI.getSelectedRow(), 9).toString());
            KdDokter.setText(tbSPRI.getValueAt(tbSPRI.getSelectedRow(), 10).toString());
            NmDokter.setText(tbSPRI.getValueAt(tbSPRI.getSelectedRow(), 11).toString());
            KdPoli.setText(tbSPRI.getValueAt(tbSPRI.getSelectedRow(), 12).toString());
            NmPoli.setText(tbSPRI.getValueAt(tbSPRI.getSelectedRow(), 13).toString());
            kdICD10 = tbSPRI.getValueAt(tbSPRI.getSelectedRow(), 14).toString();
        }
    }
    
    public void setNoRm(String norawat, String nokartu, String norm, String namapasien, 
            String tanggallahir, String jk, String kddokterdpjp) {
        NoRawat.setText(norawat);
        NoKartu.setText(nokartu);
        NoRM.setText(norm);
        NmPasien.setText(namapasien);
        TglLahir.setText(tanggallahir);
        TCari.setText(nokartu);
        ChkInput.setSelected(true);
        dpjpRanap.setText(Sequel.cariIsi("select nm_dokter from dokter where kd_dokter='" + kddokterdpjp + "'"));
        
        if (jk.equals("L")) {
            JK.setText("Laki-laki");
        } else if (jk.equals("P")) {
            JK.setText("Perempuan");
        } else {
            JK.setText(jk);
        }
        isForm();
        tampil();
    }
    
    public void setNoRm(String norm) {
        TCari.setText(norm);
        ChkInput.setSelected(false);
        isForm();
        tampil();
    }
    
    public void isForm() {
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 156));
            FormInput.setVisible(true);
            ChkInput.setVisible(true);
        } else if (ChkInput.isSelected() == false) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 20));
            FormInput.setVisible(false);
            ChkInput.setVisible(true);
        }
    }
    
    public void isCek(){
        BtnSimpan.setEnabled(akses.getSPRIJKN());
        BtnPrint.setEnabled(akses.getSPRIJKN());
        BtnEdit.setEnabled(akses.getSPRIJKN());
        
        if (akses.getkode().equals("Admin Utama") || akses.getHapusSEP() == true) {
            BtnHapus.setEnabled(true);
        } else {
            BtnHapus.setEnabled(false);
        }
    }

    public JTable getTable(){
        return tbSPRI;
    }
    
    public static class HttpEntityEnclosingDeleteRequest extends HttpEntityEnclosingRequestBase {
        public HttpEntityEnclosingDeleteRequest(final URI uri) {
            super();
            setURI(uri);
        }

        @Override
        public String getMethod() {
            return "DELETE";
        }
    }
    
    public void bodyWithDeleteRequest() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        SSLContext sslContext = SSLContext.getInstance("SSL");
        javax.net.ssl.TrustManager[] trustManagers= {
            new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {return null;}
                public void checkServerTrusted(X509Certificate[] arg0, String arg1)throws CertificateException {}
                public void checkClientTrusted(X509Certificate[] arg0, String arg1)throws CertificateException {}
            }
        };
        sslContext.init(null,trustManagers , new SecureRandom());
        SSLSocketFactory sslFactory=new SSLSocketFactory(sslContext,SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        Scheme scheme=new Scheme("https",443,sslFactory);
    
        HttpComponentsClientHttpRequestFactory factory=new HttpComponentsClientHttpRequestFactory(){
            @Override
            protected HttpUriRequest createHttpUriRequest(HttpMethod httpMethod, URI uri) {
                if (HttpMethod.DELETE == httpMethod) {
                    return new BPJSSuratKontrol.HttpEntityEnclosingDeleteRequest(uri);
                }
                return super.createHttpUriRequest(httpMethod, uri);
            }
        };
        factory.getHttpClient().getConnectionManager().getSchemeRegistry().register(scheme);
        restTemplate.setRequestFactory(factory);
        
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.add("X-Cons-ID",koneksiDB.CONSIDAPIBPJS());
            utc = String.valueOf(api.GetUTCdatetimeAsString());
	    headers.add("X-Timestamp",utc);            
	    headers.add("X-Signature",api.getHmac(utc));  
            headers.add("user_key",koneksiDB.USERKEYAPIBPJS());
            URL = link + "/RencanaKontrol/Delete";
            
            requestJson = "{"
                    + "\"request\":{"
                    + "\"t_suratkontrol\":{"
                    + "\"noSuratKontrol\":\"" + NoSurat.getText() + "\","
                    + "\"user\":\"" + user + "\""
                    + "}"
                    + "}"
                    + "}";
            
            System.out.println("Ini mengirim : " + requestJson);
            HttpEntity requestEntity = new HttpEntity(requestJson, headers);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(restTemplate.exchange(URL, HttpMethod.DELETE, requestEntity, String.class).getBody());
            JsonNode nameNode = root.path("metaData");
            System.out.println("code : " + nameNode.path("code").asText());
            System.out.println("message : " + nameNode.path("message").asText());
            JOptionPane.showMessageDialog(rootPane, "Notifikasi WS VClaim 2.0 : Kode " + nameNode.path("code").asText() + ", Pesan : " + nameNode.path("message").asText());

            if (nameNode.path("code").asText().equals("200")) {
                Sequel.meghapus("bridging_surat_pri_bpjs", "no_surat", NoSurat.getText());
                tampil();
                emptTeks();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Notifikasi WS VClaim 2.0 : Kode " + nameNode.path("code").asText() + ", Pesan : " + nameNode.path("message").asText());
            }
        } catch (Exception e) {
            System.out.println("Notif : " + e);
            if (e.toString().contains("UnknownHostException")) {
                JOptionPane.showMessageDialog(null, "Koneksi ke server BPJS terputus...!");
            }
        }
    }
}
