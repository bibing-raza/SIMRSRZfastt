package bridging;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Color;
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
public class BPJSSuratKontrol extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private final Properties prop = new Properties();
    private PreparedStatement ps, ps1;
    private ResultSet rs, rs1;
    private int i = 0, x = 0;
    private Date tgl = new Date();
    private BPJSCekReferensiDokterKontrol dokter = new BPJSCekReferensiDokterKontrol(null, false);
    private BPJSCekReferensiSpesialistikKontrol poli = new BPJSCekReferensiSpesialistikKontrol(null, false);
    private HttpHeaders headers;
    private HttpEntity requestEntity;
    private ObjectMapper mapper = new ObjectMapper();    
    private String requestJson = "", URL = "", user = "", utc = "", diagnosa = "", kdICD10 = "", daripolinya = "", SEPkontrol = "";
    public JsonNode respon_lagi;
    private BPJSApi api = new BPJSApi();

    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public BPJSSuratKontrol(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new Object[]{
            "No. Rawat", "No. SEP", "No. Kartu", "No. RM", "Nama Pasien", "Tgl. Lahir", "J.K.", "Diagnosa", "Tgl. Surat", "No. Surat",
            "Tgl. Kontrol", "Kode Dokter", "Nama Dokter/Sepesialis", "Kode Poli", "Nama Poliklinik", "kdpenyakit"
        }) {
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbSuratKontrol.setModel(tabMode);
        tbSuratKontrol.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbSuratKontrol.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 16; i++) {
            TableColumn column = tbSuratKontrol.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(125);
            } else if (i == 2) {
                column.setPreferredWidth(100);
            } else if (i == 3) {
                column.setPreferredWidth(80);
            } else if (i == 4) {
                column.setPreferredWidth(150);
            } else if (i == 5) {
                column.setPreferredWidth(65);
            } else if (i == 6) {
                column.setPreferredWidth(80);
            } else if (i == 7) {
                column.setPreferredWidth(150);
            } else if (i == 8) {
                column.setPreferredWidth(65);
            } else if (i == 9) {
                column.setPreferredWidth(125);
            } else if (i == 10) {
                column.setPreferredWidth(65);
            } else if (i == 11) {
                column.setPreferredWidth(70);
            } else if (i == 12) {
                column.setPreferredWidth(340);
            } else if (i == 13) {
                column.setPreferredWidth(70);
            } else if (i == 14) {
                column.setPreferredWidth(150);
            } else if (i == 15) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbSuratKontrol.setDefaultRenderer(Object.class, new WarnaTable());
        
        //tabel 10 riwayat kunjungan terakhir
        tabMode1=new DefaultTableModel(null,new String[]{"No.","No. RM","Tgl. Kunjungan","Jns. Rawat",
                "Poliklinik","Cara Bayar","No. SEP","No. Rujukan","No. Surat Kontrol/SPRI","Urutan SEP","kdpj","jk",
                "norawat","nokartu","nmpasien","tgllahir","diagnosa"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbRiwayat.setModel(tabMode1);
        tbRiwayat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbRiwayat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 17; i++) {
            TableColumn column = tbRiwayat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(35);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(90);
            } else if (i == 3) {
                column.setPreferredWidth(80);
            } else if (i == 4) {
                column.setPreferredWidth(220);
            } else if (i == 5) {
                column.setPreferredWidth(150);
            } else if (i == 6) {
                column.setPreferredWidth(150);
            } else if (i == 7) {
                column.setPreferredWidth(150);
            } else if (i == 8) {
                column.setPreferredWidth(150);
            } else if (i == 9) {
                column.setPreferredWidth(75);
            } else if (i == 10) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
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
            }
        }
        tbRiwayat.setDefaultRenderer(Object.class, new WarnaTable());

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
                if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER) {
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
                if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER) {
                    poli.dispose();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
        try {
            user=akses.getkode().replace(" ","").substring(0,9);
        } catch (Exception e) {
            user=akses.getkode();
        }
        
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));            
        } catch (Exception e) {
            System.out.println("E : "+e);
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
        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnSurat = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        MnAmbilSuratKontrolVCLAIM = new javax.swing.JMenuItem();
        MnCekRujukanJKN = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbSuratKontrol = new widget.Table();
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
        tglSurat1 = new widget.Tanggal();
        jLabel22 = new widget.Label();
        tglSurat2 = new widget.Tanggal();
        R2 = new widget.RadioButton();
        tglKontrol1 = new widget.Tanggal();
        jLabel25 = new widget.Label();
        tglKontrol2 = new widget.Tanggal();
        PanelInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        FormInput = new widget.TabPane();
        internalFrame2 = new widget.InternalFrame();
        panelisi1 = new widget.panelisi();
        jLabel4 = new widget.Label();
        NoRawat = new widget.TextBox();
        jLabel9 = new widget.Label();
        NmDokter = new widget.TextBox();
        NoSEP = new widget.TextBox();
        KdDokter = new widget.TextBox();
        BtnDokter = new widget.Button();
        jLabel11 = new widget.Label();
        KdPoli = new widget.TextBox();
        NmPoli = new widget.TextBox();
        BtnPoli = new widget.Button();
        label_rencana = new widget.Label();
        TanggalKontrol = new widget.Tanggal();
        NoSurat = new widget.TextBox();
        jLabel5 = new widget.Label();
        jLabel8 = new widget.Label();
        NoKartu = new widget.TextBox();
        jLabel12 = new widget.Label();
        NmPasien = new widget.TextBox();
        NoRM = new widget.TextBox();
        TglLahir = new widget.TextBox();
        jLabel13 = new widget.Label();
        jLabel16 = new widget.Label();
        JK = new widget.TextBox();
        jLabel17 = new widget.Label();
        jLabel18 = new widget.Label();
        label_hari = new widget.TextArea();
        internalFrame3 = new widget.InternalFrame();
        scrollInput2 = new widget.ScrollPane();
        tbRiwayat = new widget.Table();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnSurat.setBackground(new java.awt.Color(255, 255, 254));
        MnSurat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSurat.setForeground(new java.awt.Color(0, 0, 0));
        MnSurat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSurat.setText("Surat Rencana Kontrol");
        MnSurat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSurat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSurat.setName("MnSurat"); // NOI18N
        MnSurat.setPreferredSize(new java.awt.Dimension(180, 26));
        MnSurat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSuratActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnSurat);

        jPopupMenu2.setName("jPopupMenu2"); // NOI18N

        MnAmbilSuratKontrolVCLAIM.setBackground(new java.awt.Color(255, 255, 254));
        MnAmbilSuratKontrolVCLAIM.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnAmbilSuratKontrolVCLAIM.setForeground(new java.awt.Color(0, 0, 0));
        MnAmbilSuratKontrolVCLAIM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnAmbilSuratKontrolVCLAIM.setText("Ambil Surat Kontrol dari VClaim");
        MnAmbilSuratKontrolVCLAIM.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnAmbilSuratKontrolVCLAIM.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnAmbilSuratKontrolVCLAIM.setName("MnAmbilSuratKontrolVCLAIM"); // NOI18N
        MnAmbilSuratKontrolVCLAIM.setPreferredSize(new java.awt.Dimension(200, 26));
        MnAmbilSuratKontrolVCLAIM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnAmbilSuratKontrolVCLAIMActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnAmbilSuratKontrolVCLAIM);

        MnCekRujukanJKN.setBackground(new java.awt.Color(255, 255, 254));
        MnCekRujukanJKN.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCekRujukanJKN.setForeground(new java.awt.Color(0, 0, 0));
        MnCekRujukanJKN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCekRujukanJKN.setText("Cek Rujukan BPJS");
        MnCekRujukanJKN.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCekRujukanJKN.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCekRujukanJKN.setName("MnCekRujukanJKN"); // NOI18N
        MnCekRujukanJKN.setPreferredSize(new java.awt.Dimension(200, 26));
        MnCekRujukanJKN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCekRujukanJKNActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnCekRujukanJKN);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Surat Rencana Kontrol VClaim ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ScrollMouseClicked(evt);
            }
        });

        tbSuratKontrol.setAutoCreateRowSorter(true);
        tbSuratKontrol.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbSuratKontrol.setComponentPopupMenu(jPopupMenu1);
        tbSuratKontrol.setName("tbSuratKontrol"); // NOI18N
        tbSuratKontrol.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSuratKontrolMouseClicked(evt);
            }
        });
        tbSuratKontrol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbSuratKontrolKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbSuratKontrol);

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
        BtnAll.setText("Semua Data");
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(120, 30));
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
        TCari.setPreferredSize(new java.awt.Dimension(300, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass10.add(TCari);

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
        R1.setText("Tgl. Surat : ");
        R1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        R1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        R1.setName("R1"); // NOI18N
        R1.setPreferredSize(new java.awt.Dimension(100, 23));
        panelCari.add(R1);

        tglSurat1.setEditable(false);
        tglSurat1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "19-04-2023" }));
        tglSurat1.setDisplayFormat("dd-MM-yyyy");
        tglSurat1.setName("tglSurat1"); // NOI18N
        tglSurat1.setOpaque(false);
        tglSurat1.setPreferredSize(new java.awt.Dimension(95, 23));
        tglSurat1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tglSurat1ItemStateChanged(evt);
            }
        });
        panelCari.add(tglSurat1);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("s.d");
        jLabel22.setName("jLabel22"); // NOI18N
        jLabel22.setPreferredSize(new java.awt.Dimension(25, 23));
        panelCari.add(jLabel22);

        tglSurat2.setEditable(false);
        tglSurat2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "19-04-2023" }));
        tglSurat2.setDisplayFormat("dd-MM-yyyy");
        tglSurat2.setName("tglSurat2"); // NOI18N
        tglSurat2.setOpaque(false);
        tglSurat2.setPreferredSize(new java.awt.Dimension(95, 23));
        tglSurat2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tglSurat2KeyPressed(evt);
            }
        });
        panelCari.add(tglSurat2);

        R2.setBackground(new java.awt.Color(240, 250, 230));
        R2.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup1.add(R2);
        R2.setText("Tgl. Rencana  Kontrol : ");
        R2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        R2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        R2.setName("R2"); // NOI18N
        R2.setPreferredSize(new java.awt.Dimension(145, 23));
        panelCari.add(R2);

        tglKontrol1.setEditable(false);
        tglKontrol1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "19-04-2023" }));
        tglKontrol1.setDisplayFormat("dd-MM-yyyy");
        tglKontrol1.setName("tglKontrol1"); // NOI18N
        tglKontrol1.setOpaque(false);
        tglKontrol1.setPreferredSize(new java.awt.Dimension(95, 23));
        tglKontrol1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tglKontrol1ItemStateChanged(evt);
            }
        });
        panelCari.add(tglKontrol1);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("s.d");
        jLabel25.setName("jLabel25"); // NOI18N
        jLabel25.setPreferredSize(new java.awt.Dimension(25, 23));
        panelCari.add(jLabel25);

        tglKontrol2.setEditable(false);
        tglKontrol2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "19-04-2023" }));
        tglKontrol2.setDisplayFormat("dd-MM-yyyy");
        tglKontrol2.setName("tglKontrol2"); // NOI18N
        tglKontrol2.setOpaque(false);
        tglKontrol2.setPreferredSize(new java.awt.Dimension(95, 23));
        tglKontrol2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tglKontrol2ItemStateChanged(evt);
            }
        });
        panelCari.add(tglKontrol2);

        jPanel3.add(panelCari, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 320));
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

        FormInput.setForeground(new java.awt.Color(0, 0, 0));
        FormInput.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(400, 210));
        FormInput.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FormInputMouseClicked(evt);
            }
        });

        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout());

        panelisi1.setName("panelisi1"); // NOI18N
        panelisi1.setLayout(null);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("No. SEP :");
        jLabel4.setName("jLabel4"); // NOI18N
        panelisi1.add(jLabel4);
        jLabel4.setBounds(246, 10, 60, 23);

        NoRawat.setEditable(false);
        NoRawat.setForeground(new java.awt.Color(0, 0, 0));
        NoRawat.setName("NoRawat"); // NOI18N
        panelisi1.add(NoRawat);
        NoRawat.setBounds(94, 10, 150, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Nama Dokter :");
        jLabel9.setName("jLabel9"); // NOI18N
        panelisi1.add(jLabel9);
        jLabel9.setBounds(0, 129, 90, 23);

        NmDokter.setEditable(false);
        NmDokter.setForeground(new java.awt.Color(0, 0, 0));
        NmDokter.setHighlighter(null);
        NmDokter.setName("NmDokter"); // NOI18N
        panelisi1.add(NmDokter);
        NmDokter.setBounds(158, 129, 350, 23);

        NoSEP.setEditable(false);
        NoSEP.setForeground(new java.awt.Color(0, 0, 0));
        NoSEP.setComponentPopupMenu(jPopupMenu2);
        NoSEP.setName("NoSEP"); // NOI18N
        panelisi1.add(NoSEP);
        NoSEP.setBounds(310, 10, 150, 23);

        KdDokter.setEditable(false);
        KdDokter.setForeground(new java.awt.Color(0, 0, 0));
        KdDokter.setHighlighter(null);
        KdDokter.setName("KdDokter"); // NOI18N
        panelisi1.add(KdDokter);
        KdDokter.setBounds(94, 129, 60, 23);

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
        panelisi1.add(BtnDokter);
        BtnDokter.setBounds(510, 129, 28, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Spesialis/Sub :");
        jLabel11.setName("jLabel11"); // NOI18N
        panelisi1.add(jLabel11);
        jLabel11.setBounds(0, 100, 90, 23);

        KdPoli.setEditable(false);
        KdPoli.setForeground(new java.awt.Color(0, 0, 0));
        KdPoli.setHighlighter(null);
        KdPoli.setName("KdPoli"); // NOI18N
        panelisi1.add(KdPoli);
        KdPoli.setBounds(94, 100, 50, 23);

        NmPoli.setEditable(false);
        NmPoli.setForeground(new java.awt.Color(0, 0, 0));
        NmPoli.setHighlighter(null);
        NmPoli.setName("NmPoli"); // NOI18N
        panelisi1.add(NmPoli);
        NmPoli.setBounds(147, 100, 360, 23);

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
        panelisi1.add(BtnPoli);
        BtnPoli.setBounds(510, 100, 28, 23);

        label_rencana.setForeground(new java.awt.Color(0, 0, 0));
        label_rencana.setText("Tgl. Rencana Kontrol : ");
        label_rencana.setName("label_rencana"); // NOI18N
        panelisi1.add(label_rencana);
        label_rencana.setBounds(295, 70, 120, 23);

        TanggalKontrol.setEditable(false);
        TanggalKontrol.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "19-04-2023" }));
        TanggalKontrol.setDisplayFormat("dd-MM-yyyy");
        TanggalKontrol.setName("TanggalKontrol"); // NOI18N
        TanggalKontrol.setOpaque(false);
        TanggalKontrol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TanggalKontrolActionPerformed(evt);
            }
        });
        panelisi1.add(TanggalKontrol);
        TanggalKontrol.setBounds(416, 70, 95, 23);

        NoSurat.setEditable(false);
        NoSurat.setForeground(new java.awt.Color(0, 0, 0));
        NoSurat.setName("NoSurat"); // NOI18N
        panelisi1.add(NoSurat);
        NoSurat.setBounds(94, 70, 200, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("No. Rawat :");
        jLabel5.setName("jLabel5"); // NOI18N
        panelisi1.add(jLabel5);
        jLabel5.setBounds(0, 10, 90, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("No. Kartu :");
        jLabel8.setName("jLabel8"); // NOI18N
        panelisi1.add(jLabel8);
        jLabel8.setBounds(463, 10, 70, 23);

        NoKartu.setEditable(false);
        NoKartu.setForeground(new java.awt.Color(0, 0, 0));
        NoKartu.setComponentPopupMenu(jPopupMenu2);
        NoKartu.setName("NoKartu"); // NOI18N
        panelisi1.add(NoKartu);
        NoKartu.setBounds(538, 10, 180, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Pasien :");
        jLabel12.setName("jLabel12"); // NOI18N
        panelisi1.add(jLabel12);
        jLabel12.setBounds(0, 40, 90, 23);

        NmPasien.setEditable(false);
        NmPasien.setForeground(new java.awt.Color(0, 0, 0));
        NmPasien.setHighlighter(null);
        NmPasien.setName("NmPasien"); // NOI18N
        panelisi1.add(NmPasien);
        NmPasien.setBounds(167, 40, 260, 23);

        NoRM.setEditable(false);
        NoRM.setForeground(new java.awt.Color(0, 0, 0));
        NoRM.setName("NoRM"); // NOI18N
        panelisi1.add(NoRM);
        NoRM.setBounds(94, 40, 70, 23);

        TglLahir.setEditable(false);
        TglLahir.setForeground(new java.awt.Color(0, 0, 0));
        TglLahir.setName("TglLahir"); // NOI18N
        panelisi1.add(TglLahir);
        TglLahir.setBounds(495, 40, 95, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Tgl. Lahir :");
        jLabel13.setName("jLabel13"); // NOI18N
        panelisi1.add(jLabel13);
        jLabel13.setBounds(429, 40, 62, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("J.K. : ");
        jLabel16.setName("jLabel16"); // NOI18N
        panelisi1.add(jLabel16);
        jLabel16.setBounds(590, 40, 60, 23);

        JK.setEditable(false);
        JK.setForeground(new java.awt.Color(0, 0, 0));
        JK.setName("JK"); // NOI18N
        panelisi1.add(JK);
        JK.setBounds(650, 40, 95, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("No. Surat :");
        jLabel17.setName("jLabel17"); // NOI18N
        panelisi1.add(jLabel17);
        jLabel17.setBounds(0, 70, 90, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Ket. Hari : ");
        jLabel18.setName("jLabel18"); // NOI18N
        panelisi1.add(jLabel18);
        jLabel18.setBounds(511, 70, 70, 23);

        label_hari.setEditable(false);
        label_hari.setColumns(20);
        label_hari.setRows(5);
        label_hari.setText("-");
        label_hari.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        label_hari.setName("label_hari"); // NOI18N
        panelisi1.add(label_hari);
        label_hari.setBounds(578, 67, 350, 85);

        internalFrame2.add(panelisi1, java.awt.BorderLayout.CENTER);

        FormInput.addTab(".: Input Data", internalFrame2);

        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout());

        scrollInput2.setName("scrollInput2"); // NOI18N
        scrollInput2.setPreferredSize(new java.awt.Dimension(102, 1200));

        tbRiwayat.setToolTipText("");
        tbRiwayat.setName("tbRiwayat"); // NOI18N
        tbRiwayat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRiwayatMouseClicked(evt);
            }
        });
        tbRiwayat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbRiwayatKeyPressed(evt);
            }
        });
        scrollInput2.setViewportView(tbRiwayat);

        internalFrame3.add(scrollInput2, java.awt.BorderLayout.CENTER);

        FormInput.addTab(".: 10 Riwayat Kunjungan Terakhir", internalFrame3);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (NoRawat.getText().trim().equals("")) {
            Valid.textKosong(NoRawat, "pasien");
        } else if (NmDokter.getText().trim().equals("") || KdDokter.getText().trim().equals("")) {
            Valid.textKosong(KdDokter, "Dokter");
        } else if (NmPoli.getText().trim().equals("") || NmPoli.getText().trim().equals("")) {
            Valid.textKosong(KdPoli, "Poli");
        } else if (Sequel.cariInteger("select count(-1) from hari_libur where tgl_libur='" + Valid.SetTgl(TanggalKontrol.getSelectedItem() + "") + "'") > 0 && !daripolinya.equals("HDL")) {
            JOptionPane.showMessageDialog(rootPane, "Pelayanan rawat jalan poliklinik TUTUP, karena sedang/memperingati\n"
                    + Sequel.cariIsi("select keterangan from hari_libur where tgl_libur='" + Valid.SetTgl(TanggalKontrol.getSelectedItem() + "") + "'")
                    + ", silahkan ganti hari lain utk. tgl. rencana kontrolnya");
        } else {
            try {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                headers.add("X-Cons-ID", koneksiDB.CONSIDAPIBPJS());
                utc = String.valueOf(api.GetUTCdatetimeAsString());
                headers.add("X-Timestamp", utc);
                headers.add("X-Signature", api.getHmac(utc));
                headers.add("user_key", koneksiDB.USERKEYAPIBPJS());
                URL = prop.getProperty("URLAPIBPJS") + "/RencanaKontrol/insert";

                requestJson = "{"
                        + "\"request\": {"
                        + "\"noSEP\":\"" + NoSEP.getText() + "\","
                        + "\"kodeDokter\":\"" + KdDokter.getText() + "\","
                        + "\"poliKontrol\":\"" + KdPoli.getText() + "\","
                        + "\"tglRencanaKontrol\":\"" + Valid.SetTgl(TanggalKontrol.getSelectedItem() + "") + "\","
                        + "\"user\":\"" + user + "\""
                        + "}"
                        + "}";

                System.out.println("ini mengirim JSON : " + requestJson);
                HttpEntity requestEntity = new HttpEntity(requestJson, headers);
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                JsonNode nameNode = root.path("metaData");
                System.out.println("code : " + nameNode.path("code").asText());
                System.out.println("message : " + nameNode.path("message").asText());
                JOptionPane.showMessageDialog(rootPane, "Notifikasi WS VClaim 2.0 : Kode " + nameNode.path("code").asText() + ", Isi pesan : " + nameNode.path("message").asText());
//ini yang baru -----------            
                JsonNode response = mapper.readTree(api.Decrypt(root.path("response").asText(), utc));
                System.out.println("Ini responnya : " + mapper.readTree(api.Decrypt(root.path("response").asText(), utc)));
//sampai sini -------------
                if (nameNode.path("code").asText().equals("200")) {
                    if (Sequel.menyimpantf("bridging_surat_kontrol_bpjs", "?,?,?,?,?,?,?,?,?,?", "No. Rencana Kontrol", 10, new String[]{
                        NoSEP.getText(), Sequel.cariIsi("SELECT DATE(NOW())"), response.path("noSuratKontrol").asText(), Valid.SetTgl(TanggalKontrol.getSelectedItem() + ""),
                        KdDokter.getText(), NmDokter.getText(), KdPoli.getText(), NmPoli.getText(), "2: Rencana Kontrol", NoRawat.getText()
                    }) == true) {
                        emptTeks();
                        tampil();
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Notifikasi WS VClaim 2.0 : Kode " + nameNode.path("code").asText() + ", Pesan : " + nameNode.path("message").asText());
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
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnHapus);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbSuratKontrol.getSelectedRow() != -1) {
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
                Valid.MyReport("rptBridgingSuratKontrol.jasper", "report", "::[ Data Surat Kontrol VClaim ]::",
                        "select bridging_sep.no_rawat,bridging_sep.no_sep,bridging_sep.no_kartu,bridging_sep.nomr,bridging_sep.nama_pasien,bridging_sep.tanggal_lahir,"
                        + "bridging_sep.jkel,bridging_sep.diagawal,bridging_sep.nmdiagnosaawal,bridging_surat_kontrol_bpjs.tgl_surat,bridging_surat_kontrol_bpjs.no_surat,"
                        + "bridging_surat_kontrol_bpjs.jenis,bridging_surat_kontrol_bpjs.tgl_rencana,bridging_surat_kontrol_bpjs.kd_dokter_bpjs,bridging_surat_kontrol_bpjs.nm_dokter_bpjs,"
                        + "bridging_surat_kontrol_bpjs.kd_poli_bpjs,bridging_surat_kontrol_bpjs.nm_poli_bpjs from bridging_sep inner join bridging_surat_kontrol_bpjs "
                        + "on bridging_surat_kontrol_bpjs.no_sep=bridging_sep.no_sep where bridging_surat_kontrol_bpjs.tgl_surat between '" + Valid.SetTgl(tglSurat1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(tglSurat2.getSelectedItem() + "") + "' "
                        + (TCari.getText().trim().equals("") ? "" : "and (bridging_sep.no_rawat like '%" + TCari.getText().trim() + "%' or bridging_sep.no_sep like '%" + TCari.getText().trim() + "%' or bridging_sep.no_kartu like '%" + TCari.getText().trim() + "%' or "
                        + "bridging_sep.nomr like '%" + TCari.getText().trim() + "%' or bridging_sep.nama_pasien like '%" + TCari.getText().trim() + "%' or bridging_surat_kontrol_bpjs.jenis like '%" + TCari.getText().trim() + "%' or bridging_surat_kontrol_bpjs.no_surat like '%" + TCari.getText().trim() + "%' or "
                        + "bridging_surat_kontrol_bpjs.nm_poli_bpjs like '%" + TCari.getText().trim() + "%' or bridging_surat_kontrol_bpjs.nm_dokter_bpjs like '%" + TCari.getText().trim() + "%')")
                        + "order by bridging_surat_kontrol_bpjs.tgl_surat", param);
            } else if (R2.isSelected() == true) {
                Map<String, Object> param = new HashMap<>();
                param.put("namars", akses.getnamars());
                param.put("alamatrs", akses.getalamatrs());
                param.put("kotars", akses.getkabupatenrs());
                param.put("propinsirs", akses.getpropinsirs());
                param.put("kontakrs", akses.getkontakrs());
                param.put("emailrs", akses.getemailrs());
                param.put("logo", Sequel.cariGambar("select logo from setting"));
                Valid.MyReport("rptBridgingSuratKontrol.jasper", "report", "::[ Data Surat Kontrol VClaim ]::",
                        "select bridging_sep.no_rawat,bridging_sep.no_sep,bridging_sep.no_kartu,bridging_sep.nomr,bridging_sep.nama_pasien,bridging_sep.tanggal_lahir,"
                        + "bridging_sep.jkel,bridging_sep.diagawal,bridging_sep.nmdiagnosaawal,bridging_surat_kontrol_bpjs.tgl_surat,bridging_surat_kontrol_bpjs.no_surat,"
                        + "bridging_surat_kontrol_bpjs.jenis,bridging_surat_kontrol_bpjs.tgl_rencana,bridging_surat_kontrol_bpjs.kd_dokter_bpjs,bridging_surat_kontrol_bpjs.nm_dokter_bpjs,"
                        + "bridging_surat_kontrol_bpjs.kd_poli_bpjs,bridging_surat_kontrol_bpjs.nm_poli_bpjs from bridging_sep inner join bridging_surat_kontrol_bpjs "
                        + "on bridging_surat_kontrol_bpjs.no_sep=bridging_sep.no_sep where bridging_surat_kontrol_bpjs.tgl_rencana between '" + Valid.SetTgl(tglKontrol1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(tglKontrol2.getSelectedItem() + "") + "' "
                        + (TCari.getText().trim().equals("") ? "" : "and (bridging_sep.no_rawat like '%" + TCari.getText().trim() + "%' or bridging_sep.no_sep like '%" + TCari.getText().trim() + "%' or bridging_sep.no_kartu like '%" + TCari.getText().trim() + "%' or "
                        + "bridging_sep.nomr like '%" + TCari.getText().trim() + "%' or bridging_sep.nama_pasien like '%" + TCari.getText().trim() + "%' or bridging_surat_kontrol_bpjs.jenis like '%" + TCari.getText().trim() + "%' or bridging_surat_kontrol_bpjs.no_surat like '%" + TCari.getText().trim() + "%' or "
                        + "bridging_surat_kontrol_bpjs.nm_poli_bpjs like '%" + TCari.getText().trim() + "%' or bridging_surat_kontrol_bpjs.nm_dokter_bpjs like '%" + TCari.getText().trim() + "%')")
                        + "order by bridging_surat_kontrol_bpjs.tgl_rencana", param);
            }
        }
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPrintActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnHapus, BtnKeluar);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

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
        FormInputMouseClicked(null);
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
        FormInputMouseClicked(null);
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAllActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnCari, NoSEP);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbSuratKontrolMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSuratKontrolMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbSuratKontrolMouseClicked

private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
    if (KdPoli.getText().equals("") || NmPoli.getText().equals("")) {
        Valid.textKosong(BtnPoli, "Spesialis/Sub Spesialis");
    } else {
        dokter.SetKontrol(KdPoli.getText(), "2", Valid.SetTgl(TanggalKontrol.getSelectedItem() + ""));
        dokter.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);
        dokter.tampil();
        dokter.TCari.requestFocus();
    }   
}//GEN-LAST:event_BtnDokterActionPerformed

private void BtnDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokterKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
        BtnDokterActionPerformed(null);
    } else {
        Valid.pindah(evt, TanggalKontrol, BtnPoli);
    }
}//GEN-LAST:event_BtnDokterKeyPressed

private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
  isForm();                
}//GEN-LAST:event_ChkInputActionPerformed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (NoRawat.getText().trim().equals("") || NoSEP.getText().trim().equals("")) {
            Valid.textKosong(NoRawat, "pasien");
        } else if (NmDokter.getText().trim().equals("") || KdDokter.getText().trim().equals("")) {
            Valid.textKosong(KdDokter, "Dokter");
        } else if (NmPoli.getText().trim().equals("") || NmPoli.getText().trim().equals("")) {
            Valid.textKosong(KdPoli, "Poli");
        } else if (Sequel.cariInteger("select count(-1) from hari_libur where tgl_libur='" + Valid.SetTgl(TanggalKontrol.getSelectedItem() + "") + "'") > 0 && !daripolinya.equals("HDL")) {
            JOptionPane.showMessageDialog(rootPane, "Pelayanan rawat jalan poliklinik TUTUP, karena sedang/memperingati\n"
                    + Sequel.cariIsi("select keterangan from hari_libur where tgl_libur='" + Valid.SetTgl(TanggalKontrol.getSelectedItem() + "") + "'")
                    + ", silahkan ganti hari lain utk. tgl. rencana kontrolnya");
        } else {
            if (tbSuratKontrol.getSelectedRow() != -1) {
                try {
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                    headers.add("X-Cons-ID", koneksiDB.CONSIDAPIBPJS());
                    utc = String.valueOf(api.GetUTCdatetimeAsString());
                    headers.add("X-Timestamp", utc);
                    headers.add("X-Signature", api.getHmac(utc));
                    headers.add("user_key", koneksiDB.USERKEYAPIBPJS());
                    URL = prop.getProperty("URLAPIBPJS") + "/RencanaKontrol/Update";

                    requestJson = "{"
                            + "\"request\": {"
                            + "\"noSuratKontrol\":\"" + NoSurat.getText() + "\","
                            + "\"noSEP\":\"" + NoSEP.getText() + "\","
                            + "\"kodeDokter\":\"" + KdDokter.getText() + "\","
                            + "\"poliKontrol\":\"" + KdPoli.getText() + "\","
                            + "\"tglRencanaKontrol\":\"" + Valid.SetTgl(TanggalKontrol.getSelectedItem() + "") + "\","
                            + "\"user\":\"" + user + "\""
                            + "}"
                            + "}";

                    System.out.println("Ini mengirim JSON : " + requestJson);
                    HttpEntity requestEntity = new HttpEntity(requestJson, headers);
                    ObjectMapper mapper = new ObjectMapper();
                    JsonNode root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.PUT, requestEntity, String.class).getBody());
                    JsonNode nameNode = root.path("metaData");
                    System.out.println("code : " + nameNode.path("code").asText());
                    System.out.println("message : " + nameNode.path("message").asText());
                    JOptionPane.showMessageDialog(rootPane, "Notifikasi WS VClaim 2.0 : Kode " + nameNode.path("code").asText() + ", Pesan : " + nameNode.path("message").asText());
//ini yang baru -----------            
                    JsonNode response = mapper.readTree(api.Decrypt(root.path("response").asText(), utc));
                    System.out.println("Ini responnya : " + mapper.readTree(api.Decrypt(root.path("response").asText(), utc)));
//sampai sini -------------

                    if (nameNode.path("code").asText().equals("200")) {
                        if (Sequel.mengedittf("bridging_surat_kontrol_bpjs", "no_surat=?",
                                "tgl_rencana=?,kd_dokter_bpjs=?,nm_dokter_bpjs=?,kd_poli_bpjs=?,nm_poli_bpjs=?", 6, new String[]{
                                    Valid.SetTgl(TanggalKontrol.getSelectedItem() + ""), KdDokter.getText(),
                                    NmDokter.getText(), KdPoli.getText(), NmPoli.getText(), NoSurat.getText()
                                }) == true) {
                            emptTeks();
                            tampil();
                        }
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Notifikasi WS VClaim 2.0 : Kode " + nameNode.path("code").asText() + ", Pesan : " + nameNode.path("message").asText());
                    }
                } catch (Exception ex) {
                    System.out.println("Notifikasi Bridging : " + ex);
                    if (ex.toString().contains("UnknownHostException")) {
                        JOptionPane.showMessageDialog(null, "Koneksi ke server BPJS terputus...!");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih terlebih dulu data yang mau anda ganti, Klik data pada table untuk memilih data...!!!!");
            }
        }
    }//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnEditActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnHapus, BtnKeluar);
        }
    }//GEN-LAST:event_BtnEditKeyPressed

    private void tglKontrol1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tglKontrol1ItemStateChanged
        R2.setSelected(true);
    }//GEN-LAST:event_tglKontrol1ItemStateChanged

    private void tglKontrol2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tglKontrol2ItemStateChanged
        R2.setSelected(true);
    }//GEN-LAST:event_tglKontrol2ItemStateChanged

    private void BtnPoliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPoliKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPoliActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnDokter, TanggalKontrol);
        }
    }//GEN-LAST:event_BtnPoliKeyPressed

    private void BtnPoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPoliActionPerformed
        poli.SetKontrol(NoSEP.getText(), "2", Valid.SetTgl(TanggalKontrol.getSelectedItem() + ""));
        poli.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        poli.setLocationRelativeTo(internalFrame1);
        poli.setVisible(true);
        poli.tampil();
        poli.TCari.requestFocus();        
    }//GEN-LAST:event_BtnPoliActionPerformed

    private void MnSuratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSuratActionPerformed
        if (tbSuratKontrol.getSelectedRow() != -1) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("logo", Sequel.cariGambar("select bpjs from gambar"));
            param.put("dokter_layan", Sequel.cariIsi("select ifnull(nmdpjpLayan,'-') from bridging_sep where no_rawat='" + tbSuratKontrol.getValueAt(tbSuratKontrol.getSelectedRow(), 0).toString() + "'"));
            param.put("tgl_lahir", Sequel.cariIsi("select DATE_FORMAT(tgl_lahir,'%d') from pasien where no_rkm_medis='" + tbSuratKontrol.getValueAt(tbSuratKontrol.getSelectedRow(), 3).toString() + "'") + " "
                    + Sequel.bulanINDONESIA("select DATE_FORMAT(tgl_lahir,'%m') from pasien where no_rkm_medis='" + tbSuratKontrol.getValueAt(tbSuratKontrol.getSelectedRow(), 3).toString() + "'") + " "
                    + Sequel.cariIsi("select DATE_FORMAT(tgl_lahir,'%Y') from pasien where no_rkm_medis='" + tbSuratKontrol.getValueAt(tbSuratKontrol.getSelectedRow(), 3).toString() + "'"));
            param.put("tgl_rencana", Sequel.cariIsi("select DATE_FORMAT(tgl_rencana,'%d') from bridging_surat_kontrol_bpjs where no_surat='" + tbSuratKontrol.getValueAt(tbSuratKontrol.getSelectedRow(), 9).toString() + "'") + " "
                    + Sequel.bulanINDONESIA("select DATE_FORMAT(tgl_rencana,'%m') from bridging_surat_kontrol_bpjs where no_surat='" + tbSuratKontrol.getValueAt(tbSuratKontrol.getSelectedRow(), 9).toString() + "'") + " "
                    + Sequel.cariIsi("select DATE_FORMAT(tgl_rencana,'%Y') from bridging_surat_kontrol_bpjs where no_surat='" + tbSuratKontrol.getValueAt(tbSuratKontrol.getSelectedRow(), 9).toString() + "'"));
            
            Valid.MyReport("rptBridgingSuratKontrol2.jasper", "report", "::[ Cetak Surat Rencana Kontrol ]::",
                    " SELECT bsk.no_surat, bsk.nm_dokter_bpjs, bsk.nm_poli_bpjs, bs.no_kartu, "
                    + "CONCAT(bs.nama_pasien,' (',IF(bs.jkel='L','Laki-laki','Perempuan'),')') peserta, "
                    + "IF(rp.kd_poli='HIV',bs.diagawal,CONCAT(bs.diagawal,' - ',bs.nmdiagnosaawal)) diagnosa, bs.nomr FROM bridging_surat_kontrol_bpjs bsk "
                    + "INNER JOIN bridging_sep bs ON bs.no_rawat=bsk.no_rawat INNER JOIN reg_periksa rp ON rp.no_rawat=bsk.no_rawat "
                    + "WHERE bsk.no_surat='" + tbSuratKontrol.getValueAt(tbSuratKontrol.getSelectedRow(), 9).toString() + "'", param);
            this.setCursor(Cursor.getDefaultCursor());
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih data No. Surat nya yang mau dicetak...!!!!");
            BtnBatal.requestFocus();
        }
    }//GEN-LAST:event_MnSuratActionPerformed

    private void tglSurat1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tglSurat1ItemStateChanged
        R1.setSelected(true);
    }//GEN-LAST:event_tglSurat1ItemStateChanged

    private void tglSurat2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tglSurat2KeyPressed
        R1.setSelected(true);
    }//GEN-LAST:event_tglSurat2KeyPressed

    private void tbSuratKontrolKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbSuratKontrolKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_SPACE)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbSuratKontrolKeyPressed

    private void ScrollMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ScrollMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ScrollMouseClicked

    private void MnAmbilSuratKontrolVCLAIMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnAmbilSuratKontrolVCLAIMActionPerformed
        if (NoRawat.getText().equals("") || NoSEP.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, No. Rawat & No. SEP harus terisi dg. benar...!!!");
        } else {
            try {
                prop.loadFromXML(new FileInputStream("setting/database.xml"));
                URL = prop.getProperty("URLAPIBPJS") + "/RencanaKontrol/ListRencanaKontrol/tglAwal/" + Valid.SetTgl(TanggalKontrol.getSelectedItem() + "") + "/tglAkhir/" + Valid.SetTgl(TanggalKontrol.getSelectedItem() + "") + "/filter/2";

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
                            if (list.path("noSepAsalKontrol").asText().toLowerCase().contains(NoSEP.getText().toLowerCase())) {
                                Sequel.menyimpan("bridging_surat_kontrol_bpjs",
                                        "'" + NoSEP.getText() + "',"
                                        + "'" + list.path("tglTerbitKontrol").asText() + "',"
                                        + "'" + list.path("noSuratKontrol").asText() + "',"
                                        + "'" + list.path("tglRencanaKontrol").asText() + "',"
                                        + "'" + list.path("kodeDokter").asText() + "',"
                                        + "'" + list.path("namaDokter").asText().toUpperCase() + "',"
                                        + "'" + list.path("poliTujuan").asText() + "',"
                                        + "'" + list.path("namaPoliTujuan").asText() + "',"
                                        + "'2: Rencana Kontrol',"
                                        + "'" + NoRawat.getText() + "'");

                                Valid.SetTgl(tglSurat1, list.path("tglTerbitKontrol").asText());
                                JOptionPane.showMessageDialog(rootPane, "Surat Rencana Kontrol dari VClaim berhasil disimpan..!!");
                                emptTeks();
                                tampil();                                                                
                                break;
                            }
                        }
                    }

                    if (tabMode.getRowCount() <= 0) {
                        JOptionPane.showMessageDialog(rootPane, "Surat Rencana Kontrol dari VClaim tdk. ditemukan, periksa kembali tgl. rencana kontrol..!!");
                        TanggalKontrol.requestFocus();
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
    }//GEN-LAST:event_MnAmbilSuratKontrolVCLAIMActionPerformed

    private void TanggalKontrolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TanggalKontrolActionPerformed
        cekHariLibur();
    }//GEN-LAST:event_TanggalKontrolActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        cekHariLibur();
    }//GEN-LAST:event_formWindowOpened

    private void tbRiwayatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRiwayatMouseClicked
        if (tabMode1.getRowCount() != 0) {
            try {
                getDataKunjungan();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbRiwayatMouseClicked

    private void tbRiwayatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbRiwayatKeyPressed
        if (tabMode1.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataKunjungan();
                } catch (java.lang.NullPointerException e) {
                }
            } else if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
                try {
                    getDataKunjungan();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbRiwayatKeyPressed

    private void FormInputMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FormInputMouseClicked
        if (FormInput.getSelectedIndex() == 0) {
            TanggalKontrol.requestFocus();
            tampil();
        } else if (FormInput.getSelectedIndex() == 1) {
            if (NoRM.getText().trim().equals("")) {
                Valid.tabelKosong(tabMode1);
            } else {
                SEPkontrol = "";
                tampilKunjungan();
            }
        }
    }//GEN-LAST:event_FormInputMouseClicked

    private void MnCekRujukanJKNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCekRujukanJKNActionPerformed
        if (NoRawat.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbSuratKontrol.requestFocus();
        } else if (Sequel.cariInteger("select count(-1) from pasien_mati where no_rkm_medis='" + NoRM.getText() + "'") > 0) {
            JOptionPane.showMessageDialog(null, "Pasien ini sudah berpulang ke rahmatullah, semoga Husnul Khotimah...!!!");
            tbSuratKontrol.requestFocus();
        } else {
            BPJSCekKartu form = new BPJSCekKartu(null, false);
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.SetNoKartu(NoKartu.getText());            
            form.setVisible(true);
        }
    }//GEN-LAST:event_MnCekRujukanJKNActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            BPJSSuratKontrol dialog = new BPJSSuratKontrol(new javax.swing.JFrame(), true);
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
    public widget.TabPane FormInput;
    private widget.TextBox JK;
    private widget.TextBox KdDokter;
    private widget.TextBox KdPoli;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnAmbilSuratKontrolVCLAIM;
    private javax.swing.JMenuItem MnCekRujukanJKN;
    private javax.swing.JMenuItem MnSurat;
    private widget.TextBox NmDokter;
    private widget.TextBox NmPasien;
    private widget.TextBox NmPoli;
    private widget.TextBox NoKartu;
    private widget.TextBox NoRM;
    private widget.TextBox NoRawat;
    private widget.TextBox NoSEP;
    private widget.TextBox NoSurat;
    private javax.swing.JPanel PanelInput;
    private widget.RadioButton R1;
    private widget.RadioButton R2;
    private widget.ScrollPane Scroll;
    public widget.TextBox TCari;
    private widget.Tanggal TanggalKontrol;
    private widget.TextBox TglLahir;
    private javax.swing.ButtonGroup buttonGroup1;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.Label jLabel18;
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
    private widget.TextArea label_hari;
    private widget.Label label_rencana;
    private widget.panelisi panelCari;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelisi1;
    private widget.ScrollPane scrollInput2;
    public widget.Table tbRiwayat;
    private widget.Table tbSuratKontrol;
    private widget.Tanggal tglKontrol1;
    private widget.Tanggal tglKontrol2;
    private widget.Tanggal tglSurat1;
    private widget.Tanggal tglSurat2;
    // End of variables declaration//GEN-END:variables

    public void tampil() {     
        Valid.tabelKosong(tabMode);
        try {
            if (R1.isSelected() == true) {
                ps = koneksi.prepareStatement("SELECT bsk.no_rawat, bs.no_sep, bs.no_kartu, bs.nomr, bs.nama_pasien, bs.tanggal_lahir, IF(bs.jkel='L','Laki-laki','Perempuan') jkel, "
                        + "bs.nmdiagnosaawal, bsk.tgl_surat, bsk.no_surat, bsk.tgl_rencana, bsk.kd_dokter_bpjs, bsk.nm_dokter_bpjs, "
                        + "bsk.kd_poli_bpjs, bsk.nm_poli_bpjs, bs.diagawal FROM bridging_surat_kontrol_bpjs bsk "
                        + "INNER JOIN bridging_sep bs ON bs.no_rawat=bsk.no_rawat WHERE "
                        + "bsk.tgl_surat BETWEEN ? AND ? AND bsk.no_rawat LIKE ? OR "
                        + "bsk.tgl_surat BETWEEN ? AND ? AND bs.no_sep LIKE ? OR "
                        + "bsk.tgl_surat BETWEEN ? AND ? AND bs.no_kartu LIKE ? OR "
                        + "bsk.tgl_surat BETWEEN ? AND ? AND bs.nomr LIKE ? OR "
                        + "bsk.tgl_surat BETWEEN ? AND ? AND bs.nama_pasien LIKE ? OR "
                        + "bsk.tgl_surat BETWEEN ? AND ? AND bsk.no_surat LIKE ? OR "
                        + "bsk.tgl_surat BETWEEN ? AND ? AND bsk.nm_poli_bpjs LIKE ? OR "
                        + "bsk.tgl_surat BETWEEN ? AND ? AND bsk.nm_dokter_bpjs LIKE ? ORDER BY bsk.tgl_surat");
                
            } else if (R2.isSelected() == true) {
                ps = koneksi.prepareStatement("SELECT bsk.no_rawat, bs.no_sep, bs.no_kartu, bs.nomr, bs.nama_pasien, bs.tanggal_lahir, IF(bs.jkel='L','Laki-laki','Perempuan') jkel, "
                        + "bs.nmdiagnosaawal, bsk.tgl_surat, bsk.no_surat, bsk.tgl_rencana, bsk.kd_dokter_bpjs, bsk.nm_dokter_bpjs, "
                        + "bsk.kd_poli_bpjs, bsk.nm_poli_bpjs, bs.diagawal FROM bridging_surat_kontrol_bpjs bsk "
                        + "INNER JOIN bridging_sep bs ON bs.no_rawat=bsk.no_rawat WHERE "
                        + "bsk.tgl_rencana BETWEEN ? AND ? AND bsk.no_rawat LIKE ? OR "
                        + "bsk.tgl_rencana BETWEEN ? AND ? AND bs.no_sep LIKE ? OR "
                        + "bsk.tgl_rencana BETWEEN ? AND ? AND bs.no_kartu LIKE ? OR "
                        + "bsk.tgl_rencana BETWEEN ? AND ? AND bs.nomr LIKE ? OR "
                        + "bsk.tgl_rencana BETWEEN ? AND ? AND bs.nama_pasien LIKE ? OR "
                        + "bsk.tgl_rencana BETWEEN ? AND ? AND bsk.no_surat LIKE ? OR "
                        + "bsk.tgl_rencana BETWEEN ? AND ? AND bsk.nm_poli_bpjs LIKE ? OR "
                        + "bsk.tgl_rencana BETWEEN ? AND ? AND bsk.nm_dokter_bpjs LIKE ? ORDER BY bsk.tgl_rencana");
            }
            try {
                if (R1.isSelected() == true) {
                    ps.setString(1, Valid.SetTgl(tglSurat1.getSelectedItem() + ""));
                    ps.setString(2, Valid.SetTgl(tglSurat2.getSelectedItem() + ""));
                    ps.setString(3, "%" + TCari.getText().trim() + "%");
                    ps.setString(4, Valid.SetTgl(tglSurat1.getSelectedItem() + ""));
                    ps.setString(5, Valid.SetTgl(tglSurat2.getSelectedItem() + ""));
                    ps.setString(6, "%" + TCari.getText().trim() + "%");
                    ps.setString(7, Valid.SetTgl(tglSurat1.getSelectedItem() + ""));
                    ps.setString(8, Valid.SetTgl(tglSurat2.getSelectedItem() + ""));
                    ps.setString(9, "%" + TCari.getText().trim() + "%");
                    ps.setString(10, Valid.SetTgl(tglSurat1.getSelectedItem() + ""));
                    ps.setString(11, Valid.SetTgl(tglSurat2.getSelectedItem() + ""));
                    ps.setString(12, "%" + TCari.getText().trim() + "%");
                    ps.setString(13, Valid.SetTgl(tglSurat1.getSelectedItem() + ""));
                    ps.setString(14, Valid.SetTgl(tglSurat2.getSelectedItem() + ""));
                    ps.setString(15, "%" + TCari.getText().trim() + "%");
                    ps.setString(16, Valid.SetTgl(tglSurat1.getSelectedItem() + ""));
                    ps.setString(17, Valid.SetTgl(tglSurat2.getSelectedItem() + ""));
                    ps.setString(18, "%" + TCari.getText().trim() + "%");
                    ps.setString(19, Valid.SetTgl(tglSurat1.getSelectedItem() + ""));
                    ps.setString(20, Valid.SetTgl(tglSurat2.getSelectedItem() + ""));
                    ps.setString(21, "%" + TCari.getText().trim() + "%");
                    ps.setString(22, Valid.SetTgl(tglSurat1.getSelectedItem() + ""));
                    ps.setString(23, Valid.SetTgl(tglSurat2.getSelectedItem() + ""));
                    ps.setString(24, "%" + TCari.getText().trim() + "%");
                } else if (R2.isSelected() == true) {
                    ps.setString(1, Valid.SetTgl(tglKontrol1.getSelectedItem() + ""));
                    ps.setString(2, Valid.SetTgl(tglKontrol2.getSelectedItem() + ""));
                    ps.setString(3, "%" + TCari.getText().trim() + "%");
                    ps.setString(4, Valid.SetTgl(tglKontrol1.getSelectedItem() + ""));
                    ps.setString(5, Valid.SetTgl(tglKontrol2.getSelectedItem() + ""));
                    ps.setString(6, "%" + TCari.getText().trim() + "%");
                    ps.setString(7, Valid.SetTgl(tglKontrol1.getSelectedItem() + ""));
                    ps.setString(8, Valid.SetTgl(tglKontrol2.getSelectedItem() + ""));
                    ps.setString(9, "%" + TCari.getText().trim() + "%");
                    ps.setString(10, Valid.SetTgl(tglKontrol1.getSelectedItem() + ""));
                    ps.setString(11, Valid.SetTgl(tglKontrol2.getSelectedItem() + ""));
                    ps.setString(12, "%" + TCari.getText().trim() + "%");
                    ps.setString(13, Valid.SetTgl(tglKontrol1.getSelectedItem() + ""));
                    ps.setString(14, Valid.SetTgl(tglKontrol2.getSelectedItem() + ""));
                    ps.setString(15, "%" + TCari.getText().trim() + "%");
                    ps.setString(16, Valid.SetTgl(tglKontrol1.getSelectedItem() + ""));
                    ps.setString(17, Valid.SetTgl(tglKontrol2.getSelectedItem() + ""));
                    ps.setString(18, "%" + TCari.getText().trim() + "%");
                    ps.setString(19, Valid.SetTgl(tglKontrol1.getSelectedItem() + ""));
                    ps.setString(20, Valid.SetTgl(tglKontrol2.getSelectedItem() + ""));
                    ps.setString(21, "%" + TCari.getText().trim() + "%");
                    ps.setString(22, Valid.SetTgl(tglKontrol1.getSelectedItem() + ""));
                    ps.setString(23, Valid.SetTgl(tglKontrol2.getSelectedItem() + ""));
                    ps.setString(24, "%" + TCari.getText().trim() + "%");
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new Object[]{
                        rs.getString("no_rawat"), rs.getString("no_sep"), rs.getString("no_kartu"), rs.getString("nomr"), rs.getString("nama_pasien"),
                        rs.getString("tanggal_lahir"), rs.getString("jkel"), rs.getString("nmdiagnosaawal").replaceAll("null", ""), 
                        rs.getString("tgl_surat"), rs.getString("no_surat"),
                        rs.getString("tgl_rencana"), rs.getString("kd_dokter_bpjs"), rs.getString("nm_dokter_bpjs"),
                        rs.getString("kd_poli_bpjs"), rs.getString("nm_poli_bpjs"), rs.getString("diagawal")
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
//        tglKontrol1.setDate(tgl);
//        tglKontrol2.setDate(new Date());

        R1.setSelected(true);
        NoRawat.setText("");
        NoSEP.setText("");
        NoKartu.setText("");
        NoRM.setText("");
        NmPasien.setText("");
        TglLahir.setText("");
        JK.setText("");
        NoSurat.setText("");
        TanggalKontrol.setDate(new Date());
        KdDokter.setText("");
        NmDokter.setText("");
        KdPoli.setText("");
        NmPoli.setText("");
        TanggalKontrol.requestFocus();
        cekHariLibur();
    }   

    private void getData() {
        diagnosa = "";
        kdICD10 = "";

        if (tbSuratKontrol.getSelectedRow() != -1) {
            NoRawat.setText(tbSuratKontrol.getValueAt(tbSuratKontrol.getSelectedRow(), 0).toString());
            NoSEP.setText(tbSuratKontrol.getValueAt(tbSuratKontrol.getSelectedRow(), 1).toString());
            NoKartu.setText(tbSuratKontrol.getValueAt(tbSuratKontrol.getSelectedRow(), 2).toString());
            NoRM.setText(tbSuratKontrol.getValueAt(tbSuratKontrol.getSelectedRow(), 3).toString());
            NmPasien.setText(tbSuratKontrol.getValueAt(tbSuratKontrol.getSelectedRow(), 4).toString());
            TglLahir.setText(tbSuratKontrol.getValueAt(tbSuratKontrol.getSelectedRow(), 5).toString());
            JK.setText(tbSuratKontrol.getValueAt(tbSuratKontrol.getSelectedRow(), 6).toString());
            diagnosa = tbSuratKontrol.getValueAt(tbSuratKontrol.getSelectedRow(), 7).toString();
            NoSurat.setText(tbSuratKontrol.getValueAt(tbSuratKontrol.getSelectedRow(), 9).toString());
            Valid.SetTgl(TanggalKontrol, tbSuratKontrol.getValueAt(tbSuratKontrol.getSelectedRow(), 10).toString());
            KdDokter.setText(tbSuratKontrol.getValueAt(tbSuratKontrol.getSelectedRow(), 11).toString());
            NmDokter.setText(tbSuratKontrol.getValueAt(tbSuratKontrol.getSelectedRow(), 12).toString());
            KdPoli.setText(tbSuratKontrol.getValueAt(tbSuratKontrol.getSelectedRow(), 13).toString());
            NmPoli.setText(tbSuratKontrol.getValueAt(tbSuratKontrol.getSelectedRow(), 14).toString());
            kdICD10 = tbSuratKontrol.getValueAt(tbSuratKontrol.getSelectedRow(), 15).toString();
        }
    }
    
    public void setNoRm(String norawat, String nosep, String nokartu, String norm, String namapasien, 
            String tanggallahir, String jk, String diagnosanya, String daripoli) {
        NoRawat.setText(norawat);
        NoSEP.setText(nosep);
        NoKartu.setText(nokartu);
        NoRM.setText(norm);
        NmPasien.setText(namapasien);
        TglLahir.setText(tanggallahir);
        diagnosa = diagnosanya;
        TCari.setText(nosep);
        ChkInput.setSelected(true);
        daripolinya = daripoli;

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
    
    public void isForm() {
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 320));
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
        BtnSimpan.setEnabled(akses.getRencanaKontrolJKN());
        BtnPrint.setEnabled(akses.getRencanaKontrolJKN());
        BtnEdit.setEnabled(akses.getRencanaKontrolJKN());
        
        if (akses.getkode().equals("Admin Utama") || akses.getHapusSEP() == true) {
            BtnHapus.setEnabled(true);
        } else {
            BtnHapus.setEnabled(false);
        }
    }

    public JTable getTable(){
        return tbSuratKontrol;
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
                    return new HttpEntityEnclosingDeleteRequest(uri);
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
            URL = prop.getProperty("URLAPIBPJS") + "/RencanaKontrol/Delete";
            
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
                Sequel.meghapus("bridging_surat_kontrol_bpjs", "no_surat", NoSurat.getText());
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
    
    private void cekHariLibur() {
        label_hari.setText("-");
        
        if (Sequel.cariIsi("select ifnull(tgl_libur,'') from hari_libur where tgl_libur='" + Valid.SetTgl(TanggalKontrol.getSelectedItem() + "") + "'").equals("")) {
            label_hari.setText("Kalender/penanggalan/hari normal seperti biasa.");
            label_hari.setForeground(Color.BLACK);
        } else {
            label_hari.setText(Sequel.cariIsi("select keterangan from hari_libur where tgl_libur='" + Valid.SetTgl(TanggalKontrol.getSelectedItem() + "") + "'"));
            label_hari.setForeground(Color.RED);
        }
    }
    
    public void tampilKunjungan() {
        Valid.tabelKosong(tabMode1);
        try {
            ps1 = koneksi.prepareStatement("SELECT r.no_rkm_medis, date_format(r.tgl_registrasi,'%d-%m-%Y') tglReg, if(r.status_lanjut='Ralan','R. Jalan','R. Inap') status_lanjut, "
                    + "p.nm_poli, pj.png_jawab, ifnull(bs.no_sep,'-') no_sep, ifnull(bs.no_rujukan,'-') no_rujukan, r.kd_pj, ifnull(bs.jkel,'-') jkel, "
                    + "ifnull(bs.noskdp,'-') noskdp, ifnull(bs.urutan_sep,'-') urutan_sep, r.no_rawat, bs.no_kartu,bs.nama_pasien, bs.tanggal_lahir, bs.diagawal, "
                    + "bs.nmdiagnosaawal FROM reg_periksa r INNER JOIN poliklinik p ON p.kd_poli=r.kd_poli INNER JOIN penjab pj ON pj.kd_pj=r.kd_pj "
                    + "LEFT JOIN bridging_sep bs ON bs.no_rawat=r.no_rawat WHERE r.no_rkm_medis ='" + NoRM.getText() + "' ORDER BY r.tgl_registrasi DESC LIMIT 10");
            try {
                rs1 = ps1.executeQuery();
                i = 1;
                while (rs1.next()) {
                    tabMode1.addRow(new String[]{
                        i + ".",
                        rs1.getString("no_rkm_medis"),
                        rs1.getString("tglReg"),
                        rs1.getString("status_lanjut"),
                        rs1.getString("nm_poli"),
                        rs1.getString("png_jawab"),
                        rs1.getString("no_sep"),
                        rs1.getString("no_rujukan"),
                        rs1.getString("noskdp"),
                        rs1.getString("urutan_sep"),
                        rs1.getString("kd_pj"),
                        rs1.getString("jkel"),                        
                        rs1.getString("no_rawat"),
                        rs1.getString("no_kartu"),
                        rs1.getString("nama_pasien"),
                        rs1.getString("tanggal_lahir"),
                        rs1.getString("nmdiagnosaawal")
                    });
                    i++;
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

        if (NoRM.getText().trim().equals("") || tabMode1.getRowCount() < 1) {
            JOptionPane.showMessageDialog(rootPane, "Periksa lagi No. RM pasien & harus terisi dengan benar...!!!");
        }
    }
    
    private void getDataKunjungan() {
        SEPkontrol = "";        
        if (tbRiwayat.getSelectedRow() != -1) {
            SEPkontrol = tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 6).toString();          
         
            if (!tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 10).toString().equals("B01")) {
                JOptionPane.showMessageDialog(null, "Data kunjungan yg. dipilih bukan dg. cara bayar BPJS Kesehatan...!!!");
                tbRiwayat.requestFocus();
            } else {
                setNoRm(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 12).toString(), SEPkontrol,
                        tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 13).toString(), tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 1).toString(),
                        tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 14).toString(), tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 15).toString(),
                        tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 11).toString(), tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 16).toString(),
                        Sequel.cariIsi("select kd_poli from reg_periksa where no_rawat='" + tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 12).toString() + "'"));
                ChkInput.setSelected(true);
                TCari.setText(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 13).toString());
                tampil();
                isForm();                
            }
        }
    }
 }
