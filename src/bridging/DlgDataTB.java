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
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import simrskhanza.DlgCariPenyakit;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import simrskhanza.DlgKelurahan;

/**
 *
 * @author dosen
 */
public final class DlgDataTB extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Connection koneksi = koneksiDB.condb();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i = 0;
    private HttpHeaders headers;
    private HttpEntity requestEntity;
    private RestTemplate rest;
    private ObjectMapper mapper = new ObjectMapper();
    private JsonNode root;
    private JsonNode nameNode;
    private JsonNode response;
    private ApiKemenkesSITT api = new ApiKemenkesSITT();
    private DlgCariPenyakit penyakit = new DlgCariPenyakit(null, false);
    public DlgKelurahan kel = new DlgKelurahan(null, false);
    private CoronaReferensiPropinsi prop = new CoronaReferensiPropinsi(null, false);
    private CoronaReferensiKabupaten kab = new CoronaReferensiKabupaten(null, false);
    private CoronaReferensiKecamatan kec = new CoronaReferensiKecamatan(null, false);
    private String status = "", idrs = "", URL = "", requestJson = "", id_tb_03 = "", akhirBerobat = "", 
            tglSimpan1 = "", tglSimpan2 = "", tglSimpan3 = "", ok1 = "", ok2 = "", ok3 = "", ok4 = "", ok5 = "",
            cekDianjurkanHIV = "", cekTglTesHIV = "";
    
    /** Creates new form DlgJnsPerawatan
     * @param parent
     * @param modal */
    public DlgDataTB(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(8,1);
        setSize(628,674);

        tabMode=new DefaultTableModel(null,new Object[]{
                "No.Rawat","No.RM","Nama Pasien","J.K.","Tgl.Lahir","Umur","No.JKN","NIK","Alamat","Kd.Kel","Kelurahan","Kd.Kec","Kecamatan","Kd.Kab","Kabupaten",
                "Kd.Prop","Propinsi","Periode Laporan","Tanggal Laporan","Jenis Rujukan","Keterangan Rujukan","Riwayat Pengobatan","Lokasi TB","Tipe Diagnosis",
                "Status HIV","Skoring Anak","Skoring 5","Skoring 6","Mulai Berobat","Paduan OAT","Sumber Obat","Keterangan Sumber Obat","Mikroskopis Sebelum Pengobatan",
                "Tes Cepat Sebelum Pengobatan","Biakan Sebelum Pengobatan","Mikroskopis Bl.2","No.Reg Lab Bl.2","Mikroskopis Bl.3","No.Reg Lab Bl.3","Mikroskopis Bl.5",
                "No.Reg Lab Bl.5","Mikroskopis Akhir","No.Reg Lab Akhir","Akhir Berobat","Hasil Akhir Pengobatan","Dianjurkan Tes HIV","Tanggal Tes HIV","Hasil Tes HIV",
                "PPK","ART","TB DM","Terapi DM","Pindah RO","Status Pengobatan","Foto Toraks","Toraks Tdk Dilakukan","Keterangan","ICD X","Nama Penyakit","ID TB"
            }){
             @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        
        tbTuberkulosis.setModel(tabMode);
        tbTuberkulosis.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbTuberkulosis.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 60; i++) {
            TableColumn column = tbTuberkulosis.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(70);
            } else if (i == 2) {
                column.setPreferredWidth(150);
            } else if (i == 3) {
                column.setPreferredWidth(25);
            } else if (i == 4) {
                column.setPreferredWidth(65);
            } else if (i == 5) {
                column.setPreferredWidth(40);
            } else if (i == 6) {
                column.setPreferredWidth(90);
            } else if (i == 7) {
                column.setPreferredWidth(105);
            } else if (i == 8) {
                column.setPreferredWidth(165);
            } else if (i == 9) {
                column.setPreferredWidth(45);
            } else if (i == 10) {
                column.setPreferredWidth(110);
            } else if (i == 11) {
                column.setPreferredWidth(45);
            } else if (i == 12) {
                column.setPreferredWidth(110);
            } else if (i == 13) {
                column.setPreferredWidth(45);
            } else if (i == 14) {
                column.setPreferredWidth(110);
            } else if (i == 15) {
                column.setPreferredWidth(110);
            } else if (i == 16) {
                column.setPreferredWidth(120);
            } else if (i == 17) {
                column.setPreferredWidth(110);
            } else if (i == 18) {
                column.setPreferredWidth(120);
            } else if (i == 19) {
                column.setPreferredWidth(140);
            } else if (i == 20) {
                column.setPreferredWidth(120);
            } else if (i == 21) {
                column.setPreferredWidth(170);
            } else if (i == 22) {
                column.setPreferredWidth(90);
            } else if (i == 23) {
                column.setPreferredWidth(140);
            } else if (i == 24) {
                column.setPreferredWidth(100);
            } else if (i == 25) {
                column.setPreferredWidth(70);
            } else if (i == 26) {
                column.setPreferredWidth(140);
            } else if (i == 27) {
                column.setPreferredWidth(140);
            } else if (i == 28) {
                column.setPreferredWidth(75);
            } else if (i == 29) {
                column.setPreferredWidth(150);
            } else if (i == 30) {
                column.setPreferredWidth(90);
            } else if (i == 31) {
                column.setPreferredWidth(140);
            } else if (i == 32) {
                column.setPreferredWidth(170);
            } else if (i == 33) {
                column.setPreferredWidth(165);
            } else if (i == 34) {
                column.setPreferredWidth(150);
            } else if (i == 35) {
                column.setPreferredWidth(90);
            } else if (i == 36) {
                column.setPreferredWidth(85);
            } else if (i == 37) {
                column.setPreferredWidth(90);
            } else if (i == 38) {
                column.setPreferredWidth(85);
            } else if (i == 39) {
                column.setPreferredWidth(90);
            } else if (i == 40) {
                column.setPreferredWidth(85);
            } else if (i == 41) {
                column.setPreferredWidth(95);
            } else if (i == 42) {
                column.setPreferredWidth(90);
            } else if (i == 43) {
                column.setPreferredWidth(75);
            } else if (i == 44) {
                column.setPreferredWidth(120);
            } else if (i == 45) {
                column.setPreferredWidth(100);
            } else if (i == 46) {
                column.setPreferredWidth(90);
            } else if (i == 47) {
                column.setPreferredWidth(80);
            } else if (i == 48) {
                column.setPreferredWidth(50);
            } else if (i == 49) {
                column.setPreferredWidth(50);
            } else if (i == 50) {
                column.setPreferredWidth(50);
            } else if (i == 51) {
                column.setPreferredWidth(70);
            } else if (i == 52) {
                column.setPreferredWidth(60);
            } else if (i == 53) {
                column.setPreferredWidth(110);
            } else if (i == 54) {
                column.setPreferredWidth(90);
            } else if (i == 55) {
                column.setPreferredWidth(170);
            } else if (i == 56) {
                column.setPreferredWidth(140);
            } else if (i == 57) {
                column.setPreferredWidth(50);
            } else if (i == 58) {
                column.setPreferredWidth(170);
            } else if (i == 59) {
                column.setPreferredWidth(100);
            }
        }
        tbTuberkulosis.setDefaultRenderer(Object.class, new WarnaTable());
        
        TCari.setDocument(new batasInput((byte)100).getKata(TCari));
        KdKec.setDocument(new batasInput((byte)10).getOnlyAngka(KdKec));
        KdKab.setDocument(new batasInput((byte)10).getOnlyAngka(KdKab));
        KdKel.setDocument(new batasInput((byte)10).getOnlyAngka(KdKel));
        NIK.setDocument(new batasInput((byte)17).getOnlyAngka(NIK));
        KeteranganRujukan.setDocument(new batasInput((byte)100).getKata(KeteranganRujukan));
        PaduanOAT.setDocument(new batasInput((int)500).getKata(PaduanOAT));
        KeteranganSO.setDocument(new batasInput((int)500).getKata(KeteranganSO));
        Keterangan.setDocument(new batasInput((int)100).getKata(Keterangan));
        PemeriksaanLaboratAkhirNoReg.setDocument(new batasInput((byte)15).getKata(PemeriksaanLaboratAkhirNoReg));
        PemeriksaanLaboratBulan2NoReg.setDocument(new batasInput((byte)15).getKata(PemeriksaanLaboratBulan2NoReg));
        PemeriksaanLaboratBulan3NoReg.setDocument(new batasInput((byte)15).getKata(PemeriksaanLaboratBulan3NoReg));
        PemeriksaanLaboratBulan5NoReg.setDocument(new batasInput((byte)15).getKata(PemeriksaanLaboratBulan5NoReg));
        
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
        
        prop.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgDataTB")) {
                    if (prop.getTable().getSelectedRow() != -1) {
                        KdProp.setText(prop.getTable().getValueAt(prop.getTable().getSelectedRow(), 0).toString());
                        Propinsi.setText(prop.getTable().getValueAt(prop.getTable().getSelectedRow(), 1).toString());
                        BtnPropinsi.requestFocus();
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
        
        prop.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    prop.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        }); 
        
        kab.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgDataTB")) {
                    if (kab.getTable().getSelectedRow() != -1) {
                        KdKab.setText(kab.getTable().getValueAt(kab.getTable().getSelectedRow(), 0).toString());
                        Kabupaten.setText(kab.getTable().getValueAt(kab.getTable().getSelectedRow(), 1).toString());
                        BtnKabupaten.requestFocus();
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
        
        kab.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    kab.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        }); 
        
        kec.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgDataTB")) {
                    if (kec.getTable().getSelectedRow() != -1) {
                        KdKec.setText(kec.getTable().getValueAt(kec.getTable().getSelectedRow(), 0).toString());
                        Kecamatan.setText(kec.getTable().getValueAt(kec.getTable().getSelectedRow(), 1).toString());
                        BtnKecamatan.requestFocus();
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
        
        kec.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    kec.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
        kel.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgDataTB")) {
                    if (kel.getTable().getSelectedRow() != -1) {
                        KdKel.setText(kel.getTable().getValueAt(kel.getTable().getSelectedRow(), 1).toString());
                        Kelurahan.setText(kel.getTable().getValueAt(kel.getTable().getSelectedRow(), 0).toString());
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
        
        kel.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    kel.dispose();
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
                    kdpenyakit.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(),0).toString());
                    nmpenyakit.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(),1).toString());
                }     
                kdpenyakit.requestFocus();
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
        
        try {
            idrs = koneksiDB.IDSITT();
            URL = koneksiDB.URLAPISITT();
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

        internalFrame1 = new widget.InternalFrame();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass9 = new widget.panelisi();
        jLabel58 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel59 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        TabRawat = new javax.swing.JTabbedPane();
        internalFrame2 = new widget.InternalFrame();
        Scroll1 = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        jLabel3 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TNmPasien = new widget.TextBox();
        JK = new widget.TextBox();
        jLabel4 = new widget.Label();
        Tanggal = new widget.TextBox();
        jLabel5 = new widget.Label();
        Umur = new widget.TextBox();
        jLabel8 = new widget.Label();
        jLabel9 = new widget.Label();
        NoKartu = new widget.TextBox();
        jLabel10 = new widget.Label();
        NIK = new widget.TextBox();
        jLabel11 = new widget.Label();
        PeriodeLaporan = new widget.ComboBox();
        jLabel12 = new widget.Label();
        TanggalLaporan = new widget.Tanggal();
        jLabel13 = new widget.Label();
        Alamat = new widget.TextBox();
        Kelurahan = new widget.TextBox();
        BtnKelurahan = new widget.Button();
        Kecamatan = new widget.TextBox();
        BtnKecamatan = new widget.Button();
        BtnKabupaten = new widget.Button();
        Kabupaten = new widget.TextBox();
        KdKel = new widget.TextBox();
        KdKec = new widget.TextBox();
        KdKab = new widget.TextBox();
        Rujukan = new widget.ComboBox();
        jLabel14 = new widget.Label();
        jLabel15 = new widget.Label();
        KeteranganRujukan = new widget.TextBox();
        jLabel16 = new widget.Label();
        TipeDiagnosis = new widget.ComboBox();
        jLabel17 = new widget.Label();
        Lokasi = new widget.ComboBox();
        jLabel18 = new widget.Label();
        Riwayat = new widget.ComboBox();
        jLabel19 = new widget.Label();
        StatusHIV = new widget.ComboBox();
        jLabel20 = new widget.Label();
        SkoringAnak = new widget.ComboBox();
        jLabel21 = new widget.Label();
        Skoring5 = new widget.ComboBox();
        jLabel22 = new widget.Label();
        Skoring6 = new widget.ComboBox();
        tglMulaiBerobat = new widget.Tanggal();
        jLabel24 = new widget.Label();
        PaduanOAT = new widget.TextBox();
        jLabel25 = new widget.Label();
        SumberObat = new widget.ComboBox();
        KeteranganSO = new widget.TextBox();
        jLabel26 = new widget.Label();
        jLabel27 = new widget.Label();
        SebelumPengobatanMikroskopis = new widget.ComboBox();
        jLabel28 = new widget.Label();
        SebelumPengobatanTesCepat = new widget.ComboBox();
        jLabel29 = new widget.Label();
        jLabel30 = new widget.Label();
        SebelumPengobatanBiakan = new widget.ComboBox();
        jLabel34 = new widget.Label();
        jLabel35 = new widget.Label();
        PemeriksaanLaboratBulan2Mikroskopis = new widget.ComboBox();
        jLabel36 = new widget.Label();
        PemeriksaanLaboratBulan2NoReg = new widget.TextBox();
        PemeriksaanLaboratBulan3NoReg = new widget.TextBox();
        jLabel37 = new widget.Label();
        PemeriksaanLaboratBulan3Mikroskopis = new widget.ComboBox();
        jLabel38 = new widget.Label();
        jLabel39 = new widget.Label();
        jLabel40 = new widget.Label();
        jLabel41 = new widget.Label();
        PemeriksaanLaboratBulan5Mikroskopis = new widget.ComboBox();
        jLabel42 = new widget.Label();
        PemeriksaanLaboratBulan5NoReg = new widget.TextBox();
        PemeriksaanLaboratAkhirNoReg = new widget.TextBox();
        jLabel43 = new widget.Label();
        PemeriksaanLaboratAkhirPengobatanMikroskopis = new widget.ComboBox();
        jLabel44 = new widget.Label();
        jLabel45 = new widget.Label();
        jLabel31 = new widget.Label();
        tglAkhirBerobat = new widget.Tanggal();
        jLabel46 = new widget.Label();
        HasilAkhirPengobatan = new widget.ComboBox();
        tglDianjurkanTesHIV = new widget.Tanggal();
        TanggalTesHIV = new widget.Tanggal();
        jLabel47 = new widget.Label();
        HasilTesHIV = new widget.ComboBox();
        PPK = new widget.ComboBox();
        jLabel48 = new widget.Label();
        jLabel49 = new widget.Label();
        ART = new widget.ComboBox();
        jLabel50 = new widget.Label();
        TBDM = new widget.ComboBox();
        jLabel51 = new widget.Label();
        TerapiDM = new widget.ComboBox();
        PindahRO = new widget.ComboBox();
        jLabel52 = new widget.Label();
        Status = new widget.ComboBox();
        jLabel53 = new widget.Label();
        jLabel54 = new widget.Label();
        jLabel55 = new widget.Label();
        ToraksTidakDilakukan = new widget.ComboBox();
        Keterangan = new widget.TextBox();
        jLabel57 = new widget.Label();
        btnICD = new widget.Button();
        KdProp = new widget.TextBox();
        Propinsi = new widget.TextBox();
        BtnPropinsi = new widget.Button();
        ChkAkhirObat = new widget.CekBox();
        ChkDianjurHIV = new widget.CekBox();
        ChkTglTesHIV = new widget.CekBox();
        kodeIDTB03 = new widget.Label();
        jLabel60 = new widget.Label();
        FotoTorak = new widget.ComboBox();
        kdpenyakit = new widget.TextBox();
        nmpenyakit = new widget.TextBox();
        internalFrame3 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbTuberkulosis = new widget.Table();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Data Pasien Teridentifikasi TB ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

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

        jLabel58.setForeground(new java.awt.Color(0, 0, 0));
        jLabel58.setText("Periode :");
        jLabel58.setName("jLabel58"); // NOI18N
        jLabel58.setPreferredSize(new java.awt.Dimension(55, 23));
        panelGlass9.add(jLabel58);

        DTPCari1.setEditable(false);
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "18-01-2022" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari1);

        jLabel59.setForeground(new java.awt.Color(0, 0, 0));
        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel59.setText("s.d");
        jLabel59.setName("jLabel59"); // NOI18N
        jLabel59.setPreferredSize(new java.awt.Dimension(24, 23));
        panelGlass9.add(jLabel59);

        DTPCari2.setEditable(false);
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "18-01-2022" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari2);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(210, 23));
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

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(75, 23));
        panelGlass9.add(jLabel7);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass9.add(LCount);

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        TabRawat.setBackground(new java.awt.Color(254, 255, 254));
        TabRawat.setForeground(new java.awt.Color(0, 0, 0));
        TabRawat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        FormInput.setBackground(new java.awt.Color(255, 255, 255));
        FormInput.setBorder(null);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(100, 257));
        FormInput.setLayout(null);

        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("Pasien :");
        jLabel3.setName("jLabel3"); // NOI18N
        FormInput.add(jLabel3);
        jLabel3.setBounds(0, 10, 81, 23);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        FormInput.add(TNoRw);
        TNoRw.setBounds(84, 10, 140, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(226, 10, 80, 23);

        TNmPasien.setEditable(false);
        TNmPasien.setForeground(new java.awt.Color(0, 0, 0));
        TNmPasien.setName("TNmPasien"); // NOI18N
        FormInput.add(TNmPasien);
        TNmPasien.setBounds(308, 10, 300, 23);

        JK.setEditable(false);
        JK.setForeground(new java.awt.Color(0, 0, 0));
        JK.setName("JK"); // NOI18N
        FormInput.add(JK);
        JK.setBounds(610, 10, 95, 23);

        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        jLabel4.setText("Tgl. Lahir :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(0, 40, 81, 23);

        Tanggal.setEditable(false);
        Tanggal.setForeground(new java.awt.Color(0, 0, 0));
        Tanggal.setName("Tanggal"); // NOI18N
        FormInput.add(Tanggal);
        Tanggal.setBounds(84, 40, 100, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Umur :");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(199, 40, 50, 23);

        Umur.setEditable(false);
        Umur.setForeground(new java.awt.Color(0, 0, 0));
        Umur.setName("Umur"); // NOI18N
        FormInput.add(Umur);
        Umur.setBounds(252, 40, 45, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("Th");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(299, 40, 30, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("No. JKN :");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(330, 40, 55, 23);

        NoKartu.setEditable(false);
        NoKartu.setForeground(new java.awt.Color(0, 0, 0));
        NoKartu.setName("NoKartu"); // NOI18N
        FormInput.add(NoKartu);
        NoKartu.setBounds(388, 40, 120, 23);

        jLabel10.setForeground(new java.awt.Color(255, 0, 0));
        jLabel10.setText("NIK :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(517, 40, 40, 23);

        NIK.setForeground(new java.awt.Color(0, 0, 0));
        NIK.setName("NIK"); // NOI18N
        FormInput.add(NIK);
        NIK.setBounds(560, 40, 145, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Periode Laporan :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(0, 160, 100, 23);

        PeriodeLaporan.setForeground(new java.awt.Color(0, 0, 0));
        PeriodeLaporan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "1=Januari - Maret", "2=April - Juni", "3=Juli - September", "4=Oktober - Desember" }));
        PeriodeLaporan.setName("PeriodeLaporan"); // NOI18N
        FormInput.add(PeriodeLaporan);
        PeriodeLaporan.setBounds(103, 160, 163, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Tanggal :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(270, 160, 54, 23);

        TanggalLaporan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "18-01-2022 09:07:19" }));
        TanggalLaporan.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TanggalLaporan.setName("TanggalLaporan"); // NOI18N
        TanggalLaporan.setOpaque(false);
        TanggalLaporan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalLaporanKeyPressed(evt);
            }
        });
        FormInput.add(TanggalLaporan);
        TanggalLaporan.setBounds(327, 160, 130, 23);

        jLabel13.setForeground(new java.awt.Color(255, 0, 0));
        jLabel13.setText("Alamat :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(0, 70, 81, 23);

        Alamat.setEditable(false);
        Alamat.setForeground(new java.awt.Color(0, 0, 0));
        Alamat.setText("ALAMAT");
        Alamat.setName("Alamat"); // NOI18N
        Alamat.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                AlamatMouseMoved(evt);
            }
        });
        Alamat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                AlamatMouseExited(evt);
            }
        });
        Alamat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AlamatKeyPressed(evt);
            }
        });
        FormInput.add(Alamat);
        Alamat.setBounds(84, 70, 621, 23);

        Kelurahan.setEditable(false);
        Kelurahan.setForeground(new java.awt.Color(0, 0, 0));
        Kelurahan.setText("KELURAHAN");
        Kelurahan.setName("Kelurahan"); // NOI18N
        Kelurahan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KelurahanKeyPressed(evt);
            }
        });
        FormInput.add(Kelurahan);
        Kelurahan.setBounds(463, 130, 210, 23);

        BtnKelurahan.setForeground(new java.awt.Color(0, 0, 0));
        BtnKelurahan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnKelurahan.setMnemonic('2');
        BtnKelurahan.setToolTipText("ALt+2");
        BtnKelurahan.setName("BtnKelurahan"); // NOI18N
        BtnKelurahan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKelurahanActionPerformed(evt);
            }
        });
        FormInput.add(BtnKelurahan);
        BtnKelurahan.setBounds(677, 130, 28, 23);

        Kecamatan.setEditable(false);
        Kecamatan.setForeground(new java.awt.Color(0, 0, 0));
        Kecamatan.setText("KECAMATAN");
        Kecamatan.setName("Kecamatan"); // NOI18N
        Kecamatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KecamatanKeyPressed(evt);
            }
        });
        FormInput.add(Kecamatan);
        Kecamatan.setBounds(147, 130, 210, 23);

        BtnKecamatan.setForeground(new java.awt.Color(0, 0, 0));
        BtnKecamatan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnKecamatan.setMnemonic('3');
        BtnKecamatan.setToolTipText("ALt+3");
        BtnKecamatan.setName("BtnKecamatan"); // NOI18N
        BtnKecamatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKecamatanActionPerformed(evt);
            }
        });
        FormInput.add(BtnKecamatan);
        BtnKecamatan.setBounds(360, 130, 28, 23);

        BtnKabupaten.setForeground(new java.awt.Color(0, 0, 0));
        BtnKabupaten.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnKabupaten.setMnemonic('4');
        BtnKabupaten.setToolTipText("ALt+4");
        BtnKabupaten.setName("BtnKabupaten"); // NOI18N
        BtnKabupaten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKabupatenActionPerformed(evt);
            }
        });
        FormInput.add(BtnKabupaten);
        BtnKabupaten.setBounds(677, 100, 28, 23);

        Kabupaten.setEditable(false);
        Kabupaten.setForeground(new java.awt.Color(0, 0, 0));
        Kabupaten.setText("KABUPATEN");
        Kabupaten.setName("Kabupaten"); // NOI18N
        Kabupaten.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KabupatenKeyPressed(evt);
            }
        });
        FormInput.add(Kabupaten);
        Kabupaten.setBounds(463, 100, 210, 23);

        KdKel.setEditable(false);
        KdKel.setForeground(new java.awt.Color(0, 0, 0));
        KdKel.setName("KdKel"); // NOI18N
        KdKel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdKelKeyPressed(evt);
            }
        });
        FormInput.add(KdKel);
        KdKel.setBounds(401, 130, 60, 23);

        KdKec.setEditable(false);
        KdKec.setForeground(new java.awt.Color(0, 0, 0));
        KdKec.setName("KdKec"); // NOI18N
        KdKec.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdKecKeyPressed(evt);
            }
        });
        FormInput.add(KdKec);
        KdKec.setBounds(84, 130, 60, 23);

        KdKab.setEditable(false);
        KdKab.setForeground(new java.awt.Color(0, 0, 0));
        KdKab.setName("KdKab"); // NOI18N
        FormInput.add(KdKab);
        KdKab.setBounds(401, 100, 60, 23);

        Rujukan.setForeground(new java.awt.Color(0, 0, 0));
        Rujukan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Inisiatif pasien/Keluarga", "Anggota Masyarakat/Kader", "Faskes", "Dokter Praktek Mandiri", "Poli lain", "Lain-lain" }));
        Rujukan.setName("Rujukan"); // NOI18N
        Rujukan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RujukanKeyPressed(evt);
            }
        });
        FormInput.add(Rujukan);
        Rujukan.setBounds(515, 160, 190, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Rujukan :");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(459, 160, 53, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Ket. Rujukan :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(0, 190, 100, 23);

        KeteranganRujukan.setForeground(new java.awt.Color(0, 0, 0));
        KeteranganRujukan.setName("KeteranganRujukan"); // NOI18N
        KeteranganRujukan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganRujukanKeyPressed(evt);
            }
        });
        FormInput.add(KeteranganRujukan);
        KeteranganRujukan.setBounds(103, 190, 140, 23);

        jLabel16.setForeground(new java.awt.Color(255, 0, 0));
        jLabel16.setText("Tipe Diagnosis :");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(0, 220, 100, 23);

        TipeDiagnosis.setForeground(new java.awt.Color(0, 0, 0));
        TipeDiagnosis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Terkonfirmasi bakteriologis", "Terdiagnosis klinis" }));
        TipeDiagnosis.setName("TipeDiagnosis"); // NOI18N
        TipeDiagnosis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TipeDiagnosisKeyPressed(evt);
            }
        });
        FormInput.add(TipeDiagnosis);
        TipeDiagnosis.setBounds(103, 220, 187, 23);

        jLabel17.setForeground(new java.awt.Color(255, 0, 0));
        jLabel17.setText("Lokasi :");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(557, 190, 40, 23);

        Lokasi.setForeground(new java.awt.Color(0, 0, 0));
        Lokasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Paru", "Ekstraparu" }));
        Lokasi.setName("Lokasi"); // NOI18N
        Lokasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LokasiKeyPressed(evt);
            }
        });
        FormInput.add(Lokasi);
        Lokasi.setBounds(600, 190, 105, 23);

        jLabel18.setForeground(new java.awt.Color(255, 0, 0));
        jLabel18.setText("Riwayat :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(248, 190, 50, 23);

        Riwayat.setForeground(new java.awt.Color(0, 0, 0));
        Riwayat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Baru", "Kambuh", "Diobati setelah gagal", "Diobati Setelah Putus Berobat", "Lain-lain", "Riwayat Pengobatan Sebelumnya Tidak Diketahui", "Pindahan" }));
        Riwayat.setName("Riwayat"); // NOI18N
        Riwayat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RiwayatKeyPressed(evt);
            }
        });
        FormInput.add(Riwayat);
        Riwayat.setBounds(301, 190, 250, 23);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Status HIV :");
        jLabel19.setName("jLabel19"); // NOI18N
        FormInput.add(jLabel19);
        jLabel19.setBounds(293, 220, 68, 23);

        StatusHIV.setForeground(new java.awt.Color(0, 0, 0));
        StatusHIV.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Positif", "Negatif", "Tidak diketahui" }));
        StatusHIV.setName("StatusHIV"); // NOI18N
        StatusHIV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                StatusHIVKeyPressed(evt);
            }
        });
        FormInput.add(StatusHIV);
        StatusHIV.setBounds(363, 220, 127, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Skoring Anak :");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(488, 220, 84, 23);

        SkoringAnak.setForeground(new java.awt.Color(0, 0, 0));
        SkoringAnak.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "Tidak dilakukan" }));
        SkoringAnak.setName("SkoringAnak"); // NOI18N
        SkoringAnak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkoringAnakKeyPressed(evt);
            }
        });
        FormInput.add(SkoringAnak);
        SkoringAnak.setBounds(575, 220, 130, 23);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Skoring 5 :");
        jLabel21.setName("jLabel21"); // NOI18N
        FormInput.add(jLabel21);
        jLabel21.setBounds(0, 250, 100, 23);

        Skoring5.setForeground(new java.awt.Color(0, 0, 0));
        Skoring5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Uji Tuberkulin Positif", "Ada Kontak TB Paru", "Uji Tuberkulin Negatif", "Tidak Ada Kontak TB Paru" }));
        Skoring5.setName("Skoring5"); // NOI18N
        Skoring5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Skoring5KeyPressed(evt);
            }
        });
        FormInput.add(Skoring5);
        Skoring5.setBounds(103, 250, 175, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Skoring 6 :");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(282, 250, 60, 23);

        Skoring6.setForeground(new java.awt.Color(0, 0, 0));
        Skoring6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Ada Kontak TB Paru", "Tidak Ada", "Tidak Jelas Kontak TB Paru" }));
        Skoring6.setName("Skoring6"); // NOI18N
        Skoring6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Skoring6KeyPressed(evt);
            }
        });
        FormInput.add(Skoring6);
        Skoring6.setBounds(345, 250, 160, 23);

        tglMulaiBerobat.setEditable(false);
        tglMulaiBerobat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "18-01-2022" }));
        tglMulaiBerobat.setDisplayFormat("dd-MM-yyyy");
        tglMulaiBerobat.setName("tglMulaiBerobat"); // NOI18N
        tglMulaiBerobat.setOpaque(false);
        tglMulaiBerobat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tglMulaiBerobatKeyPressed(evt);
            }
        });
        FormInput.add(tglMulaiBerobat);
        tglMulaiBerobat.setBounds(615, 250, 90, 23);

        jLabel24.setForeground(new java.awt.Color(255, 0, 0));
        jLabel24.setText("Paduan OAT :");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(0, 280, 100, 23);

        PaduanOAT.setForeground(new java.awt.Color(0, 0, 0));
        PaduanOAT.setName("PaduanOAT"); // NOI18N
        PaduanOAT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PaduanOATKeyPressed(evt);
            }
        });
        FormInput.add(PaduanOAT);
        PaduanOAT.setBounds(103, 280, 175, 23);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Sumber Obat :");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput.add(jLabel25);
        jLabel25.setBounds(282, 280, 80, 23);

        SumberObat.setForeground(new java.awt.Color(0, 0, 0));
        SumberObat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Program TB", "Bayar Sendiri", "Asuransi", "Lain-lain" }));
        SumberObat.setName("SumberObat"); // NOI18N
        SumberObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SumberObatKeyPressed(evt);
            }
        });
        FormInput.add(SumberObat);
        SumberObat.setBounds(365, 280, 115, 23);

        KeteranganSO.setForeground(new java.awt.Color(0, 0, 0));
        KeteranganSO.setName("KeteranganSO"); // NOI18N
        KeteranganSO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganSOKeyPressed(evt);
            }
        });
        FormInput.add(KeteranganSO);
        KeteranganSO.setBounds(585, 280, 120, 23);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Ket. Sumber Obat :");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput.add(jLabel26);
        jLabel26.setBounds(482, 280, 100, 23);

        jLabel27.setForeground(new java.awt.Color(255, 0, 0));
        jLabel27.setText("Sebelum Pengobatan :");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(0, 310, 120, 23);

        SebelumPengobatanMikroskopis.setForeground(new java.awt.Color(0, 0, 0));
        SebelumPengobatanMikroskopis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Positif", "Negatif", "Tidak dilakukan" }));
        SebelumPengobatanMikroskopis.setName("SebelumPengobatanMikroskopis"); // NOI18N
        SebelumPengobatanMikroskopis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SebelumPengobatanMikroskopisKeyPressed(evt);
            }
        });
        FormInput.add(SebelumPengobatanMikroskopis);
        SebelumPengobatanMikroskopis.setBounds(194, 310, 125, 23);

        jLabel28.setForeground(new java.awt.Color(255, 0, 0));
        jLabel28.setText("Tes Cepat :");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput.add(jLabel28);
        jLabel28.setBounds(314, 310, 70, 23);

        SebelumPengobatanTesCepat.setForeground(new java.awt.Color(0, 0, 0));
        SebelumPengobatanTesCepat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Rif sensitif", "Rif resisten", "Negatif", "Rif Indeterminated", "Invalid", "Error", "No Result", "Tidak dilakukan" }));
        SebelumPengobatanTesCepat.setName("SebelumPengobatanTesCepat"); // NOI18N
        SebelumPengobatanTesCepat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SebelumPengobatanTesCepatKeyPressed(evt);
            }
        });
        FormInput.add(SebelumPengobatanTesCepat);
        SebelumPengobatanTesCepat.setBounds(387, 310, 143, 23);

        jLabel29.setForeground(new java.awt.Color(255, 0, 0));
        jLabel29.setText("Mikroskopis :");
        jLabel29.setName("jLabel29"); // NOI18N
        FormInput.add(jLabel29);
        jLabel29.setBounds(121, 310, 70, 23);

        jLabel30.setForeground(new java.awt.Color(255, 0, 0));
        jLabel30.setText("Biakan :");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput.add(jLabel30);
        jLabel30.setBounds(527, 310, 50, 23);

        SebelumPengobatanBiakan.setForeground(new java.awt.Color(0, 0, 0));
        SebelumPengobatanBiakan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Negatif", "1-19 BTA", "1+", "2+", "3+", "4+", "NTM", "Kontaminasi", "Tidak dilakukan" }));
        SebelumPengobatanBiakan.setName("SebelumPengobatanBiakan"); // NOI18N
        SebelumPengobatanBiakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SebelumPengobatanBiakanKeyPressed(evt);
            }
        });
        FormInput.add(SebelumPengobatanBiakan);
        SebelumPengobatanBiakan.setBounds(580, 310, 125, 23);

        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Pemeriksaan Laborat Bulan ke 2 :");
        jLabel34.setName("jLabel34"); // NOI18N
        FormInput.add(jLabel34);
        jLabel34.setBounds(0, 340, 174, 23);

        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("Mikroskopis :");
        jLabel35.setName("jLabel35"); // NOI18N
        FormInput.add(jLabel35);
        jLabel35.setBounds(0, 360, 100, 23);

        PemeriksaanLaboratBulan2Mikroskopis.setForeground(new java.awt.Color(0, 0, 0));
        PemeriksaanLaboratBulan2Mikroskopis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Positif", "Negatif", "Tidak dilakukan" }));
        PemeriksaanLaboratBulan2Mikroskopis.setName("PemeriksaanLaboratBulan2Mikroskopis"); // NOI18N
        PemeriksaanLaboratBulan2Mikroskopis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PemeriksaanLaboratBulan2MikroskopisKeyPressed(evt);
            }
        });
        FormInput.add(PemeriksaanLaboratBulan2Mikroskopis);
        PemeriksaanLaboratBulan2Mikroskopis.setBounds(103, 360, 125, 23);

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("No.Reg :");
        jLabel36.setName("jLabel36"); // NOI18N
        FormInput.add(jLabel36);
        jLabel36.setBounds(230, 360, 50, 23);

        PemeriksaanLaboratBulan2NoReg.setForeground(new java.awt.Color(0, 0, 0));
        PemeriksaanLaboratBulan2NoReg.setName("PemeriksaanLaboratBulan2NoReg"); // NOI18N
        PemeriksaanLaboratBulan2NoReg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PemeriksaanLaboratBulan2NoRegKeyPressed(evt);
            }
        });
        FormInput.add(PemeriksaanLaboratBulan2NoReg);
        PemeriksaanLaboratBulan2NoReg.setBounds(283, 360, 72, 23);

        PemeriksaanLaboratBulan3NoReg.setForeground(new java.awt.Color(0, 0, 0));
        PemeriksaanLaboratBulan3NoReg.setName("PemeriksaanLaboratBulan3NoReg"); // NOI18N
        PemeriksaanLaboratBulan3NoReg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PemeriksaanLaboratBulan3NoRegKeyPressed(evt);
            }
        });
        FormInput.add(PemeriksaanLaboratBulan3NoReg);
        PemeriksaanLaboratBulan3NoReg.setBounds(633, 360, 72, 23);

        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setText("No.Reg :");
        jLabel37.setName("jLabel37"); // NOI18N
        FormInput.add(jLabel37);
        jLabel37.setBounds(580, 360, 50, 23);

        PemeriksaanLaboratBulan3Mikroskopis.setForeground(new java.awt.Color(0, 0, 0));
        PemeriksaanLaboratBulan3Mikroskopis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Positif", "Negatif", "Tidak dilakukan" }));
        PemeriksaanLaboratBulan3Mikroskopis.setName("PemeriksaanLaboratBulan3Mikroskopis"); // NOI18N
        PemeriksaanLaboratBulan3Mikroskopis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PemeriksaanLaboratBulan3MikroskopisKeyPressed(evt);
            }
        });
        FormInput.add(PemeriksaanLaboratBulan3Mikroskopis);
        PemeriksaanLaboratBulan3Mikroskopis.setBounds(453, 360, 125, 23);

        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("Mikroskopis :");
        jLabel38.setName("jLabel38"); // NOI18N
        FormInput.add(jLabel38);
        jLabel38.setBounds(350, 360, 100, 23);

        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setText("Pemeriksaan Laborat Bulan ke 3 :");
        jLabel39.setName("jLabel39"); // NOI18N
        FormInput.add(jLabel39);
        jLabel39.setBounds(350, 340, 174, 23);

        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setText("Pemeriksaan Laborat Bulan ke 5 :");
        jLabel40.setName("jLabel40"); // NOI18N
        FormInput.add(jLabel40);
        jLabel40.setBounds(0, 390, 174, 23);

        jLabel41.setForeground(new java.awt.Color(0, 0, 0));
        jLabel41.setText("Mikroskopis :");
        jLabel41.setName("jLabel41"); // NOI18N
        FormInput.add(jLabel41);
        jLabel41.setBounds(0, 410, 100, 23);

        PemeriksaanLaboratBulan5Mikroskopis.setForeground(new java.awt.Color(0, 0, 0));
        PemeriksaanLaboratBulan5Mikroskopis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Positif", "Negatif", "Tidak dilakukan" }));
        PemeriksaanLaboratBulan5Mikroskopis.setName("PemeriksaanLaboratBulan5Mikroskopis"); // NOI18N
        PemeriksaanLaboratBulan5Mikroskopis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PemeriksaanLaboratBulan5MikroskopisKeyPressed(evt);
            }
        });
        FormInput.add(PemeriksaanLaboratBulan5Mikroskopis);
        PemeriksaanLaboratBulan5Mikroskopis.setBounds(103, 410, 125, 23);

        jLabel42.setForeground(new java.awt.Color(0, 0, 0));
        jLabel42.setText("No.Reg :");
        jLabel42.setName("jLabel42"); // NOI18N
        FormInput.add(jLabel42);
        jLabel42.setBounds(230, 410, 50, 23);

        PemeriksaanLaboratBulan5NoReg.setForeground(new java.awt.Color(0, 0, 0));
        PemeriksaanLaboratBulan5NoReg.setName("PemeriksaanLaboratBulan5NoReg"); // NOI18N
        PemeriksaanLaboratBulan5NoReg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PemeriksaanLaboratBulan5NoRegKeyPressed(evt);
            }
        });
        FormInput.add(PemeriksaanLaboratBulan5NoReg);
        PemeriksaanLaboratBulan5NoReg.setBounds(283, 410, 72, 23);

        PemeriksaanLaboratAkhirNoReg.setForeground(new java.awt.Color(0, 0, 0));
        PemeriksaanLaboratAkhirNoReg.setName("PemeriksaanLaboratAkhirNoReg"); // NOI18N
        PemeriksaanLaboratAkhirNoReg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PemeriksaanLaboratAkhirNoRegKeyPressed(evt);
            }
        });
        FormInput.add(PemeriksaanLaboratAkhirNoReg);
        PemeriksaanLaboratAkhirNoReg.setBounds(633, 410, 72, 23);

        jLabel43.setForeground(new java.awt.Color(0, 0, 0));
        jLabel43.setText("No.Reg :");
        jLabel43.setName("jLabel43"); // NOI18N
        FormInput.add(jLabel43);
        jLabel43.setBounds(580, 410, 50, 23);

        PemeriksaanLaboratAkhirPengobatanMikroskopis.setForeground(new java.awt.Color(0, 0, 0));
        PemeriksaanLaboratAkhirPengobatanMikroskopis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Positif", "Negatif", "Tidak dilakukan" }));
        PemeriksaanLaboratAkhirPengobatanMikroskopis.setName("PemeriksaanLaboratAkhirPengobatanMikroskopis"); // NOI18N
        PemeriksaanLaboratAkhirPengobatanMikroskopis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PemeriksaanLaboratAkhirPengobatanMikroskopisKeyPressed(evt);
            }
        });
        FormInput.add(PemeriksaanLaboratAkhirPengobatanMikroskopis);
        PemeriksaanLaboratAkhirPengobatanMikroskopis.setBounds(453, 410, 125, 23);

        jLabel44.setForeground(new java.awt.Color(0, 0, 0));
        jLabel44.setText("Mikroskopis :");
        jLabel44.setName("jLabel44"); // NOI18N
        FormInput.add(jLabel44);
        jLabel44.setBounds(350, 410, 100, 23);

        jLabel45.setForeground(new java.awt.Color(0, 0, 0));
        jLabel45.setText("Pemeriksaan Laborat Akhir Pengobatan :");
        jLabel45.setName("jLabel45"); // NOI18N
        FormInput.add(jLabel45);
        jLabel45.setBounds(314, 390, 245, 23);

        jLabel31.setForeground(new java.awt.Color(255, 0, 0));
        jLabel31.setText("Mulai Berobat :");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput.add(jLabel31);
        jLabel31.setBounds(510, 250, 100, 23);

        tglAkhirBerobat.setEditable(false);
        tglAkhirBerobat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "18-01-2022" }));
        tglAkhirBerobat.setDisplayFormat("dd-MM-yyyy");
        tglAkhirBerobat.setName("tglAkhirBerobat"); // NOI18N
        tglAkhirBerobat.setOpaque(false);
        tglAkhirBerobat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tglAkhirBerobatKeyPressed(evt);
            }
        });
        FormInput.add(tglAkhirBerobat);
        tglAkhirBerobat.setBounds(103, 440, 90, 23);

        jLabel46.setForeground(new java.awt.Color(0, 0, 0));
        jLabel46.setText("Hasil Akhir Pengobatan :");
        jLabel46.setName("jLabel46"); // NOI18N
        FormInput.add(jLabel46);
        jLabel46.setBounds(190, 440, 140, 23);

        HasilAkhirPengobatan.setForeground(new java.awt.Color(0, 0, 0));
        HasilAkhirPengobatan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Sembuh", "Pengobatan Lengkap", "Lost To Follow Up", "Meninggal", "Gagal", "Pindah" }));
        HasilAkhirPengobatan.setName("HasilAkhirPengobatan"); // NOI18N
        HasilAkhirPengobatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                HasilAkhirPengobatanKeyPressed(evt);
            }
        });
        FormInput.add(HasilAkhirPengobatan);
        HasilAkhirPengobatan.setBounds(333, 440, 140, 23);

        tglDianjurkanTesHIV.setEditable(false);
        tglDianjurkanTesHIV.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "18-01-2022" }));
        tglDianjurkanTesHIV.setDisplayFormat("dd-MM-yyyy");
        tglDianjurkanTesHIV.setName("tglDianjurkanTesHIV"); // NOI18N
        tglDianjurkanTesHIV.setOpaque(false);
        tglDianjurkanTesHIV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tglDianjurkanTesHIVKeyPressed(evt);
            }
        });
        FormInput.add(tglDianjurkanTesHIV);
        tglDianjurkanTesHIV.setBounds(615, 440, 90, 23);

        TanggalTesHIV.setEditable(false);
        TanggalTesHIV.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "18-01-2022" }));
        TanggalTesHIV.setDisplayFormat("dd-MM-yyyy");
        TanggalTesHIV.setName("TanggalTesHIV"); // NOI18N
        TanggalTesHIV.setOpaque(false);
        TanggalTesHIV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalTesHIVKeyPressed(evt);
            }
        });
        FormInput.add(TanggalTesHIV);
        TanggalTesHIV.setBounds(103, 470, 90, 23);

        jLabel47.setForeground(new java.awt.Color(0, 0, 0));
        jLabel47.setText("Hasil Tes HIV :");
        jLabel47.setName("jLabel47"); // NOI18N
        FormInput.add(jLabel47);
        jLabel47.setBounds(192, 470, 90, 23);

        HasilTesHIV.setForeground(new java.awt.Color(0, 0, 0));
        HasilTesHIV.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Reaktif", "Non Reaktif", "Indeterminated" }));
        HasilTesHIV.setName("HasilTesHIV"); // NOI18N
        HasilTesHIV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                HasilTesHIVKeyPressed(evt);
            }
        });
        FormInput.add(HasilTesHIV);
        HasilTesHIV.setBounds(285, 470, 140, 23);

        PPK.setForeground(new java.awt.Color(0, 0, 0));
        PPK.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Ya", "Tidak" }));
        PPK.setName("PPK"); // NOI18N
        PPK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PPKKeyPressed(evt);
            }
        });
        FormInput.add(PPK);
        PPK.setBounds(470, 470, 95, 23);

        jLabel48.setForeground(new java.awt.Color(0, 0, 0));
        jLabel48.setText("PPK :");
        jLabel48.setName("jLabel48"); // NOI18N
        FormInput.add(jLabel48);
        jLabel48.setBounds(427, 470, 40, 23);

        jLabel49.setForeground(new java.awt.Color(0, 0, 0));
        jLabel49.setText("ART :");
        jLabel49.setName("jLabel49"); // NOI18N
        FormInput.add(jLabel49);
        jLabel49.setBounds(561, 470, 45, 23);

        ART.setForeground(new java.awt.Color(0, 0, 0));
        ART.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Ya", "Tidak" }));
        ART.setName("ART"); // NOI18N
        ART.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ARTKeyPressed(evt);
            }
        });
        FormInput.add(ART);
        ART.setBounds(609, 470, 95, 23);

        jLabel50.setForeground(new java.awt.Color(0, 0, 0));
        jLabel50.setText("TB DM :");
        jLabel50.setName("jLabel50"); // NOI18N
        FormInput.add(jLabel50);
        jLabel50.setBounds(0, 500, 100, 23);

        TBDM.setForeground(new java.awt.Color(0, 0, 0));
        TBDM.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Ya", "Tidak" }));
        TBDM.setName("TBDM"); // NOI18N
        TBDM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TBDMKeyPressed(evt);
            }
        });
        FormInput.add(TBDM);
        TBDM.setBounds(103, 500, 77, 23);

        jLabel51.setForeground(new java.awt.Color(0, 0, 0));
        jLabel51.setText("Terapi DM :");
        jLabel51.setName("jLabel51"); // NOI18N
        FormInput.add(jLabel51);
        jLabel51.setBounds(183, 500, 63, 23);

        TerapiDM.setForeground(new java.awt.Color(0, 0, 0));
        TerapiDM.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "OHO", "Inj. Insulin" }));
        TerapiDM.setName("TerapiDM"); // NOI18N
        TerapiDM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TerapiDMKeyPressed(evt);
            }
        });
        FormInput.add(TerapiDM);
        TerapiDM.setBounds(249, 500, 102, 23);

        PindahRO.setForeground(new java.awt.Color(0, 0, 0));
        PindahRO.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Ya", "Tidak" }));
        PindahRO.setName("PindahRO"); // NOI18N
        FormInput.add(PindahRO);
        PindahRO.setBounds(421, 500, 77, 23);

        jLabel52.setForeground(new java.awt.Color(0, 0, 0));
        jLabel52.setText("Pindah RO :");
        jLabel52.setName("jLabel52"); // NOI18N
        FormInput.add(jLabel52);
        jLabel52.setBounds(353, 500, 65, 23);

        Status.setForeground(new java.awt.Color(0, 0, 0));
        Status.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Sesuai Standar", "Tidak Sesuai Standar" }));
        Status.setName("Status"); // NOI18N
        FormInput.add(Status);
        Status.setBounds(549, 500, 155, 23);

        jLabel53.setForeground(new java.awt.Color(0, 0, 0));
        jLabel53.setText("Status :");
        jLabel53.setName("jLabel53"); // NOI18N
        FormInput.add(jLabel53);
        jLabel53.setBounds(499, 500, 47, 23);

        jLabel54.setForeground(new java.awt.Color(255, 0, 0));
        jLabel54.setText("Foto Toraks :");
        jLabel54.setName("jLabel54"); // NOI18N
        FormInput.add(jLabel54);
        jLabel54.setBounds(0, 530, 100, 23);

        jLabel55.setForeground(new java.awt.Color(0, 0, 0));
        jLabel55.setText("Toraks Tdk Dilakukan :");
        jLabel55.setName("jLabel55"); // NOI18N
        FormInput.add(jLabel55);
        jLabel55.setBounds(228, 530, 120, 23);

        ToraksTidakDilakukan.setForeground(new java.awt.Color(0, 0, 0));
        ToraksTidakDilakukan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Tidak dilakukan", "Setelah terapi antibioka non OAT: tidak ada perbaikan Klinis, ada faktor resiko TB, dan atas pertimbangan dokter", "Setelah terapi antibioka non OAT: ada Perbaikan Klinis" }));
        ToraksTidakDilakukan.setName("ToraksTidakDilakukan"); // NOI18N
        ToraksTidakDilakukan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ToraksTidakDilakukanKeyPressed(evt);
            }
        });
        FormInput.add(ToraksTidakDilakukan);
        ToraksTidakDilakukan.setBounds(351, 530, 353, 23);

        Keterangan.setForeground(new java.awt.Color(0, 0, 0));
        Keterangan.setName("Keterangan"); // NOI18N
        Keterangan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeteranganKeyPressed(evt);
            }
        });
        FormInput.add(Keterangan);
        Keterangan.setBounds(103, 560, 200, 23);

        jLabel57.setForeground(new java.awt.Color(255, 0, 0));
        jLabel57.setText("ICD 10 :");
        jLabel57.setName("jLabel57"); // NOI18N
        FormInput.add(jLabel57);
        jLabel57.setBounds(298, 560, 50, 23);

        btnICD.setForeground(new java.awt.Color(0, 0, 0));
        btnICD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnICD.setMnemonic('1');
        btnICD.setToolTipText("Alt+1");
        btnICD.setName("btnICD"); // NOI18N
        btnICD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnICDActionPerformed(evt);
            }
        });
        btnICD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnICDKeyPressed(evt);
            }
        });
        FormInput.add(btnICD);
        btnICD.setBounds(676, 560, 28, 23);

        KdProp.setEditable(false);
        KdProp.setForeground(new java.awt.Color(0, 0, 0));
        KdProp.setName("KdProp"); // NOI18N
        FormInput.add(KdProp);
        KdProp.setBounds(84, 100, 60, 23);

        Propinsi.setEditable(false);
        Propinsi.setForeground(new java.awt.Color(0, 0, 0));
        Propinsi.setText("PROPINSI");
        Propinsi.setName("Propinsi"); // NOI18N
        Propinsi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PropinsiKeyPressed(evt);
            }
        });
        FormInput.add(Propinsi);
        Propinsi.setBounds(147, 100, 210, 23);

        BtnPropinsi.setForeground(new java.awt.Color(0, 0, 0));
        BtnPropinsi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPropinsi.setMnemonic('3');
        BtnPropinsi.setToolTipText("ALt+3");
        BtnPropinsi.setName("BtnPropinsi"); // NOI18N
        BtnPropinsi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPropinsiActionPerformed(evt);
            }
        });
        FormInput.add(BtnPropinsi);
        BtnPropinsi.setBounds(360, 100, 28, 23);

        ChkAkhirObat.setBackground(new java.awt.Color(255, 255, 250));
        ChkAkhirObat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkAkhirObat.setForeground(new java.awt.Color(255, 0, 0));
        ChkAkhirObat.setText("Akhir Berobat :");
        ChkAkhirObat.setBorderPainted(true);
        ChkAkhirObat.setBorderPaintedFlat(true);
        ChkAkhirObat.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ChkAkhirObat.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        ChkAkhirObat.setName("ChkAkhirObat"); // NOI18N
        ChkAkhirObat.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkAkhirObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkAkhirObatActionPerformed(evt);
            }
        });
        FormInput.add(ChkAkhirObat);
        ChkAkhirObat.setBounds(0, 440, 100, 23);

        ChkDianjurHIV.setBackground(new java.awt.Color(255, 255, 250));
        ChkDianjurHIV.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkDianjurHIV.setForeground(new java.awt.Color(0, 0, 0));
        ChkDianjurHIV.setText("Dianjurkan Tes HIV :");
        ChkDianjurHIV.setBorderPainted(true);
        ChkDianjurHIV.setBorderPaintedFlat(true);
        ChkDianjurHIV.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ChkDianjurHIV.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        ChkDianjurHIV.setName("ChkDianjurHIV"); // NOI18N
        ChkDianjurHIV.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkDianjurHIV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkDianjurHIVActionPerformed(evt);
            }
        });
        FormInput.add(ChkDianjurHIV);
        ChkDianjurHIV.setBounds(482, 440, 130, 23);

        ChkTglTesHIV.setBackground(new java.awt.Color(255, 255, 250));
        ChkTglTesHIV.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkTglTesHIV.setForeground(new java.awt.Color(0, 0, 0));
        ChkTglTesHIV.setText("Tgl. Tes HIV :");
        ChkTglTesHIV.setBorderPainted(true);
        ChkTglTesHIV.setBorderPaintedFlat(true);
        ChkTglTesHIV.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ChkTglTesHIV.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        ChkTglTesHIV.setName("ChkTglTesHIV"); // NOI18N
        ChkTglTesHIV.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkTglTesHIV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkTglTesHIVActionPerformed(evt);
            }
        });
        FormInput.add(ChkTglTesHIV);
        ChkTglTesHIV.setBounds(0, 470, 100, 23);

        kodeIDTB03.setForeground(new java.awt.Color(0, 0, 0));
        kodeIDTB03.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        kodeIDTB03.setText("kodeIDTB03");
        kodeIDTB03.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        kodeIDTB03.setName("kodeIDTB03"); // NOI18N
        FormInput.add(kodeIDTB03);
        kodeIDTB03.setBounds(715, 10, 510, 23);

        jLabel60.setForeground(new java.awt.Color(0, 0, 0));
        jLabel60.setText("Keterangan :");
        jLabel60.setName("jLabel60"); // NOI18N
        FormInput.add(jLabel60);
        jLabel60.setBounds(0, 560, 100, 23);

        FotoTorak.setForeground(new java.awt.Color(0, 0, 0));
        FotoTorak.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Positif", "Negatif", "Tidak Dilakukan" }));
        FotoTorak.setName("FotoTorak"); // NOI18N
        FotoTorak.setOpaque(false);
        FormInput.add(FotoTorak);
        FotoTorak.setBounds(103, 530, 127, 23);

        kdpenyakit.setEditable(false);
        kdpenyakit.setBackground(new java.awt.Color(245, 250, 240));
        kdpenyakit.setForeground(new java.awt.Color(0, 0, 0));
        kdpenyakit.setName("kdpenyakit"); // NOI18N
        FormInput.add(kdpenyakit);
        kdpenyakit.setBounds(351, 560, 67, 23);

        nmpenyakit.setEditable(false);
        nmpenyakit.setForeground(new java.awt.Color(0, 0, 0));
        nmpenyakit.setName("nmpenyakit"); // NOI18N
        FormInput.add(nmpenyakit);
        nmpenyakit.setBounds(420, 560, 253, 23);

        Scroll1.setViewportView(FormInput);

        internalFrame2.add(Scroll1, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Input Data Pasien Teridentifikasi TB", internalFrame2);

        internalFrame3.setBorder(null);
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbTuberkulosis.setAutoCreateRowSorter(true);
        tbTuberkulosis.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbTuberkulosis.setName("tbTuberkulosis"); // NOI18N
        tbTuberkulosis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTuberkulosisMouseClicked(evt);
            }
        });
        tbTuberkulosis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbTuberkulosisKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbTuberkulosis);

        internalFrame3.add(Scroll, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Daftar Pasien Teridentifikasi TB", internalFrame3);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().trim().equals("") || TNoRM.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else if (KdProp.getText().trim().equals("") || Propinsi.getText().trim().equals("")) {
            Valid.textKosong(BtnPropinsi, "Propinsi");
        } else if (KdKab.getText().trim().equals("") || Kabupaten.getText().trim().equals("")) {
            Valid.textKosong(BtnKabupaten, "Kabupaten");
        } else if (KdKec.getText().trim().equals("") || Kecamatan.getText().trim().equals("")) {
            Valid.textKosong(BtnKecamatan, "Kecamatan");
        } else if (KdKel.getText().trim().equals("") || Kelurahan.getText().trim().equals("")) {
            Valid.textKosong(BtnKelurahan, "Kelurahan");
        } else if (PaduanOAT.getText().trim().equals("")) {
            Valid.textKosong(PaduanOAT, "Paduan OAT");
        } else if (kdpenyakit.getText().trim().equals("") || nmpenyakit.getText().trim().equals("")) {
            Valid.textKosong(btnICD, "Penyakit");            
        } else if (TipeDiagnosis.getSelectedIndex() == 0) {
            Valid.textKosong(TipeDiagnosis, "Tipe Diagnosis");
            TipeDiagnosis.requestFocus();
        } else if (SebelumPengobatanMikroskopis.getSelectedIndex() == 0) {
            Valid.textKosong(SebelumPengobatanMikroskopis, "Sebelum Pengobatan Mikroskopis");
            SebelumPengobatanMikroskopis.requestFocus();
        } else if (SebelumPengobatanTesCepat.getSelectedIndex() == 0) {
            Valid.textKosong(SebelumPengobatanTesCepat, "Sebelum Pengobatan Tes Cepat");
            SebelumPengobatanTesCepat.requestFocus();
        } else if (SebelumPengobatanBiakan.getSelectedIndex() == 0) {
            Valid.textKosong(SebelumPengobatanBiakan, "Sebelum Pengobatan Biakan");
            SebelumPengobatanBiakan.requestFocus();
        } else if (FotoTorak.getSelectedIndex() == 0) {
            Valid.textKosong(FotoTorak, "Foto Thoraks");
            FotoTorak.requestFocus();
        } else if (Lokasi.getSelectedIndex() == 0) {
            Valid.textKosong(Lokasi, "Lokasi");
            Lokasi.requestFocus();
        } else if (Riwayat.getSelectedIndex() == 0) {
            Valid.textKosong(Riwayat, "Riwayat");
            Riwayat.requestFocus();
        } else {
            simpanData();            
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
            Valid.pindah(evt,btnICD,BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();       
        TabRawat.setSelectedIndex(0); 
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            emptTeks();
        }else{Valid.pindah(evt, BtnSimpan, BtnHapus);}
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if(tbTuberkulosis.getSelectedRow()>-1){
            Sequel.meghapus("data_tb","no_rawat",tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),0).toString());
            emptTeks();
            tampil();
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
        if (TNoRw.getText().trim().equals("") || TNoRM.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else if (KdProp.getText().trim().equals("") || Propinsi.getText().trim().equals("")) {
            Valid.textKosong(BtnPropinsi, "Propinsi");
        } else if (KdKab.getText().trim().equals("") || Kabupaten.getText().trim().equals("")) {
            Valid.textKosong(BtnKabupaten, "Kabupaten");
        } else if (KdKec.getText().trim().equals("") || Kecamatan.getText().trim().equals("")) {
            Valid.textKosong(BtnKecamatan, "Kecamatan");
        } else if (KdKel.getText().trim().equals("") || Kelurahan.getText().trim().equals("")) {
            Valid.textKosong(BtnKelurahan, "Kelurahan");
        } else if (PaduanOAT.getText().trim().equals("")) {
            Valid.textKosong(PaduanOAT, "Paduan OAT");
        } else if (kdpenyakit.getText().trim().equals("") || nmpenyakit.getText().trim().equals("")) {
            Valid.textKosong(btnICD, "Penyakit");
        } else if (TipeDiagnosis.getSelectedIndex() == 0) {
            Valid.textKosong(TipeDiagnosis, "Tipe Diagnosis");
            TipeDiagnosis.requestFocus();
        } else if (SebelumPengobatanMikroskopis.getSelectedIndex() == 0) {
            Valid.textKosong(SebelumPengobatanMikroskopis, "Sebelum Pengobatan Mikroskopis");
            SebelumPengobatanMikroskopis.requestFocus();
        } else if (SebelumPengobatanTesCepat.getSelectedIndex() == 0) {
            Valid.textKosong(SebelumPengobatanTesCepat, "Sebelum Pengobatan Tes Cepat");
            SebelumPengobatanTesCepat.requestFocus();
        } else if (SebelumPengobatanBiakan.getSelectedIndex() == 0) {
            Valid.textKosong(SebelumPengobatanBiakan, "Sebelum Pengobatan Biakan");
            SebelumPengobatanBiakan.requestFocus();
        } else if (FotoTorak.getSelectedIndex() == 0) {
            Valid.textKosong(FotoTorak, "Foto Thoraks");
            FotoTorak.requestFocus();
        } else if (Lokasi.getSelectedIndex() == 0) {
            Valid.textKosong(Lokasi, "Lokasi");
            Lokasi.requestFocus();
        } else if (Riwayat.getSelectedIndex() == 0) {
            Valid.textKosong(Riwayat, "Riwayat");
            Riwayat.requestFocus();
        } else {
            editData();
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
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnAll,TCari);}
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
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            tampil();
            TCari.setText("");
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if (TabRawat.getSelectedIndex() == 1) {
            tampil();
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        if (this.getHeight() < 750) {
            Scroll1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            FormInput.setPreferredSize(new Dimension(FormInput.WIDTH, 605));
            if (this.getWidth() < 760) {
                Scroll1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
                FormInput.setPreferredSize(new Dimension(740, 605));
            } else {
                Scroll1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            }
        } else {
            Scroll1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
            if (this.getWidth() < 760) {
                Scroll1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
                FormInput.setPreferredSize(new Dimension(740, FormInput.HEIGHT));
            } else {
                Scroll1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            }
        }
    }//GEN-LAST:event_formWindowActivated

    private void TanggalLaporanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalLaporanKeyPressed
        Valid.pindah(evt,PeriodeLaporan,Rujukan);
    }//GEN-LAST:event_TanggalLaporanKeyPressed

    private void AlamatMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AlamatMouseMoved
        if(Alamat.getText().equals("ALAMAT")){
            Alamat.setText("");
        }
    }//GEN-LAST:event_AlamatMouseMoved

    private void AlamatMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AlamatMouseExited
        if(Alamat.getText().equals("")){
            Alamat.setText("ALAMAT");
        }
    }//GEN-LAST:event_AlamatMouseExited

    private void AlamatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AlamatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (Alamat.getText().equals("")) {
                Alamat.setText("ALAMAT");
            }
            if (Kelurahan.getText().equals("KELURAHAN")) {
                Kelurahan.setText("");
            }
            Kelurahan.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            if (Alamat.getText().equals("")) {
                Alamat.setText("ALAMAT");
            }
        }
    }//GEN-LAST:event_AlamatKeyPressed

    private void BtnKelurahanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKelurahanActionPerformed
        akses.setform("DlgDataTB");
        kel.setSize(703, 384);
        kel.setLocationRelativeTo(internalFrame1);
        kel.setVisible(true);
        kel.TCari.requestFocus();
        kel.isCek();
    }//GEN-LAST:event_BtnKelurahanActionPerformed

    private void KecamatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KecamatanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (Kecamatan.getText().equals("")) {
                Kecamatan.setText("KECAMATAN");
            }
            if (Kabupaten.getText().equals("KABUPATEN")) {
                Kabupaten.setText("");
            }
            Kabupaten.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            if (Kecamatan.getText().equals("")) {
                Kecamatan.setText("KECAMATAN");
            }
            if (Kelurahan.getText().equals("KELURAHAN")) {
                Kelurahan.setText("");
            }
            Kelurahan.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            BtnKecamatanActionPerformed(null);
        }
    }//GEN-LAST:event_KecamatanKeyPressed

    private void BtnKecamatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKecamatanActionPerformed
        if (KdKab.getText().equals("") || Kabupaten.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih kabupaten terlebih dahulu..!!");
            BtnKabupaten.requestFocus();
        } else {
            akses.setform("DlgDataTB");
            kec.SetKab(KdKab.getText());
            kec.setSize(664, 270);
            kec.setLocationRelativeTo(internalFrame1);
            kec.setVisible(true);
            kec.TCari.requestFocus();
            kec.tampil();
        }
    }//GEN-LAST:event_BtnKecamatanActionPerformed

    private void BtnKabupatenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKabupatenActionPerformed
        if (KdProp.getText().equals("") || Propinsi.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih propinsi terlebih dahulu..!!");
            BtnPropinsi.requestFocus();
        } else {
            akses.setform("DlgDataTB");
            kab.SetProp(KdProp.getText());
            kab.setSize(664, 270);
            kab.setLocationRelativeTo(internalFrame1);
            kab.setVisible(true);
            kab.TCari.requestFocus();
            kab.tampil();
        }
    }//GEN-LAST:event_BtnKabupatenActionPerformed

    private void KabupatenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KabupatenKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (Kabupaten.getText().equals("")) {
                Kabupaten.setText("KABUPATEN");
            }
            PeriodeLaporan.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            if (Kabupaten.getText().equals("")) {
                Kabupaten.setText("KABUPATEN");
            }
            if (Kecamatan.getText().equals("KECAMATAN")) {
                Kecamatan.setText("");
            }
            Kecamatan.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            BtnKabupatenActionPerformed(null);
        }
    }//GEN-LAST:event_KabupatenKeyPressed

    private void KdKecKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdKecKeyPressed
        Valid.pindah(evt, KdKel,KdKab);
    }//GEN-LAST:event_KdKecKeyPressed

    private void RujukanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RujukanKeyPressed
        Valid.pindah(evt,TanggalLaporan,KeteranganRujukan);
    }//GEN-LAST:event_RujukanKeyPressed

    private void KeteranganRujukanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganRujukanKeyPressed
        Valid.pindah(evt,Rujukan,Riwayat);
    }//GEN-LAST:event_KeteranganRujukanKeyPressed

    private void TipeDiagnosisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TipeDiagnosisKeyPressed
        Valid.pindah(evt,Lokasi,StatusHIV);
    }//GEN-LAST:event_TipeDiagnosisKeyPressed

    private void LokasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LokasiKeyPressed
        Valid.pindah(evt,Riwayat,TipeDiagnosis);
    }//GEN-LAST:event_LokasiKeyPressed

    private void RiwayatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RiwayatKeyPressed
        Valid.pindah(evt,KeteranganRujukan,Lokasi);
    }//GEN-LAST:event_RiwayatKeyPressed

    private void StatusHIVKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_StatusHIVKeyPressed
        Valid.pindah(evt,TipeDiagnosis,SkoringAnak);
    }//GEN-LAST:event_StatusHIVKeyPressed

    private void SkoringAnakKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkoringAnakKeyPressed
        Valid.pindah(evt,StatusHIV,Skoring5);
    }//GEN-LAST:event_SkoringAnakKeyPressed

    private void Skoring5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Skoring5KeyPressed
        Valid.pindah(evt,SkoringAnak,Skoring6);
    }//GEN-LAST:event_Skoring5KeyPressed

    private void Skoring6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Skoring6KeyPressed
        Valid.pindah(evt,Skoring5,tglMulaiBerobat);
    }//GEN-LAST:event_Skoring6KeyPressed

    private void tglMulaiBerobatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tglMulaiBerobatKeyPressed
        Valid.pindah(evt,Skoring6,PaduanOAT);
    }//GEN-LAST:event_tglMulaiBerobatKeyPressed

    private void PaduanOATKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PaduanOATKeyPressed
        Valid.pindah(evt,tglMulaiBerobat,SumberObat);
    }//GEN-LAST:event_PaduanOATKeyPressed

    private void SumberObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SumberObatKeyPressed
        Valid.pindah(evt,PaduanOAT,KeteranganSO);
    }//GEN-LAST:event_SumberObatKeyPressed

    private void KeteranganSOKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganSOKeyPressed
        Valid.pindah(evt,SumberObat,SebelumPengobatanMikroskopis);
    }//GEN-LAST:event_KeteranganSOKeyPressed

    private void SebelumPengobatanMikroskopisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SebelumPengobatanMikroskopisKeyPressed
        Valid.pindah(evt,KeteranganSO,SebelumPengobatanTesCepat);
    }//GEN-LAST:event_SebelumPengobatanMikroskopisKeyPressed

    private void SebelumPengobatanTesCepatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SebelumPengobatanTesCepatKeyPressed
        Valid.pindah(evt,SebelumPengobatanMikroskopis,SebelumPengobatanBiakan);
    }//GEN-LAST:event_SebelumPengobatanTesCepatKeyPressed

    private void SebelumPengobatanBiakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SebelumPengobatanBiakanKeyPressed
        Valid.pindah(evt,SebelumPengobatanTesCepat,PemeriksaanLaboratBulan2Mikroskopis);
    }//GEN-LAST:event_SebelumPengobatanBiakanKeyPressed

    private void PemeriksaanLaboratBulan2MikroskopisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PemeriksaanLaboratBulan2MikroskopisKeyPressed
        Valid.pindah(evt,SebelumPengobatanBiakan,PemeriksaanLaboratBulan2NoReg);
    }//GEN-LAST:event_PemeriksaanLaboratBulan2MikroskopisKeyPressed

    private void PemeriksaanLaboratBulan2NoRegKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PemeriksaanLaboratBulan2NoRegKeyPressed
        Valid.pindah(evt,PemeriksaanLaboratBulan2Mikroskopis,PemeriksaanLaboratBulan3Mikroskopis);
    }//GEN-LAST:event_PemeriksaanLaboratBulan2NoRegKeyPressed

    private void PemeriksaanLaboratBulan3NoRegKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PemeriksaanLaboratBulan3NoRegKeyPressed
        Valid.pindah(evt,PemeriksaanLaboratBulan3Mikroskopis,PemeriksaanLaboratBulan5Mikroskopis);
    }//GEN-LAST:event_PemeriksaanLaboratBulan3NoRegKeyPressed

    private void PemeriksaanLaboratBulan3MikroskopisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PemeriksaanLaboratBulan3MikroskopisKeyPressed
        Valid.pindah(evt,PemeriksaanLaboratBulan2NoReg,PemeriksaanLaboratBulan3NoReg);
    }//GEN-LAST:event_PemeriksaanLaboratBulan3MikroskopisKeyPressed

    private void PemeriksaanLaboratBulan5MikroskopisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PemeriksaanLaboratBulan5MikroskopisKeyPressed
        Valid.pindah(evt,PemeriksaanLaboratBulan3NoReg,PemeriksaanLaboratBulan5NoReg);
    }//GEN-LAST:event_PemeriksaanLaboratBulan5MikroskopisKeyPressed

    private void PemeriksaanLaboratBulan5NoRegKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PemeriksaanLaboratBulan5NoRegKeyPressed
        Valid.pindah(evt,PemeriksaanLaboratBulan5Mikroskopis,PemeriksaanLaboratAkhirPengobatanMikroskopis);
    }//GEN-LAST:event_PemeriksaanLaboratBulan5NoRegKeyPressed

    private void PemeriksaanLaboratAkhirNoRegKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PemeriksaanLaboratAkhirNoRegKeyPressed
        Valid.pindah(evt,PemeriksaanLaboratAkhirPengobatanMikroskopis,tglAkhirBerobat);
    }//GEN-LAST:event_PemeriksaanLaboratAkhirNoRegKeyPressed

    private void PemeriksaanLaboratAkhirPengobatanMikroskopisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PemeriksaanLaboratAkhirPengobatanMikroskopisKeyPressed
        Valid.pindah(evt,PemeriksaanLaboratAkhirPengobatanMikroskopis,PemeriksaanLaboratAkhirNoReg);
    }//GEN-LAST:event_PemeriksaanLaboratAkhirPengobatanMikroskopisKeyPressed

    private void tglAkhirBerobatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tglAkhirBerobatKeyPressed
        Valid.pindah(evt,PemeriksaanLaboratAkhirNoReg,HasilAkhirPengobatan);
    }//GEN-LAST:event_tglAkhirBerobatKeyPressed

    private void HasilAkhirPengobatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HasilAkhirPengobatanKeyPressed
        Valid.pindah(evt,tglAkhirBerobat,tglDianjurkanTesHIV);
    }//GEN-LAST:event_HasilAkhirPengobatanKeyPressed

    private void tglDianjurkanTesHIVKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tglDianjurkanTesHIVKeyPressed
        Valid.pindah(evt,HasilAkhirPengobatan,TanggalTesHIV);
    }//GEN-LAST:event_tglDianjurkanTesHIVKeyPressed

    private void TanggalTesHIVKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalTesHIVKeyPressed
        Valid.pindah(evt,tglDianjurkanTesHIV,HasilTesHIV);
    }//GEN-LAST:event_TanggalTesHIVKeyPressed

    private void HasilTesHIVKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HasilTesHIVKeyPressed
        Valid.pindah(evt,TanggalTesHIV,PPK);
    }//GEN-LAST:event_HasilTesHIVKeyPressed

    private void PPKKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PPKKeyPressed
        Valid.pindah(evt,HasilTesHIV,ART);
    }//GEN-LAST:event_PPKKeyPressed

    private void ARTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ARTKeyPressed
        Valid.pindah(evt,PPK,TBDM);
    }//GEN-LAST:event_ARTKeyPressed

    private void TBDMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TBDMKeyPressed
        Valid.pindah(evt,ART,TerapiDM);
    }//GEN-LAST:event_TBDMKeyPressed

    private void TerapiDMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TerapiDMKeyPressed
        Valid.pindah(evt,TBDM,PindahRO);
    }//GEN-LAST:event_TerapiDMKeyPressed

    private void ToraksTidakDilakukanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ToraksTidakDilakukanKeyPressed
        Valid.pindah(evt,FotoTorak,Keterangan);
    }//GEN-LAST:event_ToraksTidakDilakukanKeyPressed

    private void KeteranganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeteranganKeyPressed
        Valid.pindah(evt,ToraksTidakDilakukan,btnICD);
    }//GEN-LAST:event_KeteranganKeyPressed

    private void btnICDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnICDActionPerformed
        penyakit.isCek();
        penyakit.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        penyakit.setLocationRelativeTo(internalFrame1);
        penyakit.setVisible(true);
    }//GEN-LAST:event_btnICDActionPerformed

    private void btnICDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnICDKeyPressed
        Valid.pindah(evt,Keterangan,BtnSimpan);
    }//GEN-LAST:event_btnICDKeyPressed

    private void KelurahanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KelurahanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (Kelurahan.getText().equals("")) {
                Kelurahan.setText("KELURAHAN");
            }
            if (Kecamatan.getText().equals("KECAMATAN")) {
                Kecamatan.setText("");
            }
            Kecamatan.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            if (Kelurahan.getText().equals("")) {
                Kelurahan.setText("KELURAHAN");
            }
            if (Alamat.getText().equals("ALAMAT")) {
                Alamat.setText("");
            }
            Alamat.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            BtnKelurahanActionPerformed(null);
        }
    }//GEN-LAST:event_KelurahanKeyPressed

    private void KdKelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdKelKeyPressed
        Valid.pindah(evt, TCari,KdKec);
    }//GEN-LAST:event_KdKelKeyPressed

    private void PropinsiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PropinsiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PropinsiKeyPressed

    private void BtnPropinsiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPropinsiActionPerformed
        akses.setform("DlgDataTB");
        prop.setSize(654, 342);
        prop.setLocationRelativeTo(internalFrame1);
        prop.setVisible(true);
        prop.TCari.requestFocus();
        prop.TCari.setText("Kalimantan");
        prop.tampil();
    }//GEN-LAST:event_BtnPropinsiActionPerformed

    private void ChkAkhirObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkAkhirObatActionPerformed
        if (ChkAkhirObat.isSelected() == true) {
            tglAkhirBerobat.setEnabled(true);
            tglAkhirBerobat.requestFocus();
        } else {
            tglAkhirBerobat.setEnabled(false);
        }
    }//GEN-LAST:event_ChkAkhirObatActionPerformed

    private void tbTuberkulosisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbTuberkulosisKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }else if(evt.getKeyCode()==KeyEvent.VK_SPACE){
                try {
                    getData();
                    TabRawat.setSelectedIndex(0);
                } catch (java.lang.NullPointerException e) {
                }
            }else if(evt.getKeyCode()==KeyEvent.VK_A){
                for(i=0;i<tbTuberkulosis.getRowCount();i++){
                    tbTuberkulosis.setValueAt(true,i,0);
                }
            }
        }
    }//GEN-LAST:event_tbTuberkulosisKeyPressed

    private void tbTuberkulosisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTuberkulosisMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
                if(evt.getClickCount()==2){
                    TabRawat.setSelectedIndex(0);

                }
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbTuberkulosisMouseClicked

    private void ChkDianjurHIVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkDianjurHIVActionPerformed
        if (ChkDianjurHIV.isSelected() == true) {
            tglDianjurkanTesHIV.setEnabled(true);
            tglDianjurkanTesHIV.requestFocus();
        } else {
            tglDianjurkanTesHIV.setEnabled(false);
        }
    }//GEN-LAST:event_ChkDianjurHIVActionPerformed

    private void ChkTglTesHIVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkTglTesHIVActionPerformed
        if (ChkTglTesHIV.isSelected() == true) {
            TanggalTesHIV.setEnabled(true);
            TanggalTesHIV.requestFocus();
        } else {
            TanggalTesHIV.setEnabled(false);
        }
    }//GEN-LAST:event_ChkTglTesHIVActionPerformed

                                 

    private void PindahROKeyPressed(java.awt.event.KeyEvent evt) {                                    
        Valid.pindah(evt,TerapiDM,Status);
    }                                   

    private void StatusKeyPressed(java.awt.event.KeyEvent evt) {                                  
        Valid.pindah(evt,PindahRO,FotoTorak);
    }                                 

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgDataTB dialog = new DlgDataTB(new javax.swing.JFrame(), true);
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
    private widget.ComboBox ART;
    private widget.TextBox Alamat;
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKabupaten;
    private widget.Button BtnKecamatan;
    private widget.Button BtnKeluar;
    private widget.Button BtnKelurahan;
    private widget.Button BtnPropinsi;
    private widget.Button BtnSimpan;
    public widget.CekBox ChkAkhirObat;
    public widget.CekBox ChkDianjurHIV;
    public widget.CekBox ChkTglTesHIV;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.ComboBox FotoTorak;
    private widget.ComboBox HasilAkhirPengobatan;
    private widget.ComboBox HasilTesHIV;
    private widget.TextBox JK;
    private widget.TextBox Kabupaten;
    private widget.TextBox KdKab;
    private widget.TextBox KdKec;
    private widget.TextBox KdKel;
    private widget.TextBox KdProp;
    private widget.TextBox Kecamatan;
    private widget.TextBox Kelurahan;
    private widget.TextBox Keterangan;
    private widget.TextBox KeteranganRujukan;
    private widget.TextBox KeteranganSO;
    private widget.Label LCount;
    private widget.ComboBox Lokasi;
    private widget.TextBox NIK;
    private widget.TextBox NoKartu;
    private widget.ComboBox PPK;
    private widget.TextBox PaduanOAT;
    private widget.TextBox PemeriksaanLaboratAkhirNoReg;
    private widget.ComboBox PemeriksaanLaboratAkhirPengobatanMikroskopis;
    private widget.ComboBox PemeriksaanLaboratBulan2Mikroskopis;
    private widget.TextBox PemeriksaanLaboratBulan2NoReg;
    private widget.ComboBox PemeriksaanLaboratBulan3Mikroskopis;
    private widget.TextBox PemeriksaanLaboratBulan3NoReg;
    private widget.ComboBox PemeriksaanLaboratBulan5Mikroskopis;
    private widget.TextBox PemeriksaanLaboratBulan5NoReg;
    private widget.ComboBox PeriodeLaporan;
    private widget.ComboBox PindahRO;
    private widget.TextBox Propinsi;
    private widget.ComboBox Riwayat;
    private widget.ComboBox Rujukan;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ComboBox SebelumPengobatanBiakan;
    private widget.ComboBox SebelumPengobatanMikroskopis;
    private widget.ComboBox SebelumPengobatanTesCepat;
    private widget.ComboBox Skoring5;
    private widget.ComboBox Skoring6;
    private widget.ComboBox SkoringAnak;
    private widget.ComboBox Status;
    private widget.ComboBox StatusHIV;
    private widget.ComboBox SumberObat;
    private widget.ComboBox TBDM;
    private widget.TextBox TCari;
    private widget.TextBox TNmPasien;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    public javax.swing.JTabbedPane TabRawat;
    private widget.TextBox Tanggal;
    private widget.Tanggal TanggalLaporan;
    private widget.Tanggal TanggalTesHIV;
    private widget.ComboBox TerapiDM;
    private widget.ComboBox TipeDiagnosis;
    private widget.ComboBox ToraksTidakDilakukan;
    private widget.TextBox Umur;
    private widget.Button btnICD;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
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
    private widget.Label jLabel24;
    private widget.Label jLabel25;
    private widget.Label jLabel26;
    private widget.Label jLabel27;
    private widget.Label jLabel28;
    private widget.Label jLabel29;
    private widget.Label jLabel3;
    private widget.Label jLabel30;
    private widget.Label jLabel31;
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
    private widget.Label jLabel5;
    private widget.Label jLabel50;
    private widget.Label jLabel51;
    private widget.Label jLabel52;
    private widget.Label jLabel53;
    private widget.Label jLabel54;
    private widget.Label jLabel55;
    private widget.Label jLabel57;
    private widget.Label jLabel58;
    private widget.Label jLabel59;
    private widget.Label jLabel6;
    private widget.Label jLabel60;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private widget.TextBox kdpenyakit;
    private widget.Label kodeIDTB03;
    private widget.TextBox nmpenyakit;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.Table tbTuberkulosis;
    private widget.Tanggal tglAkhirBerobat;
    private widget.Tanggal tglDianjurkanTesHIV;
    private widget.Tanggal tglMulaiBerobat;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode);
        try{    
            ps = koneksi.prepareStatement(
                    "SELECT rp.no_rawat,rp.no_rkm_medis,p.nm_pasien,p.jk,p.tgl_lahir,IF(rp.sttsumur = 'Th',rp.umurdaftar,0) AS umur,"
                    + "p.no_peserta,dt.no_ktp,p.alamat,dt.id_kelurahan,kl.nm_kel,dt.id_kecamatan,dt.kd_kabupaten,"
                    + "dt.id_periode_laporan,dt.tanggal_buat_laporan,dt.nama_rujukan,dt.sebutkan1,dt.klasifikasi_riwayat_pengobatan,"
                    + "dt.klasifikasi_lokasi_anatomi,dt.tipe_diagnosis,dt.klasifikasi_status_hiv,dt.total_skoring_anak,dt.konfirmasiSkoring5,"
                    + "dt.konfirmasiSkoring6,dt.tanggal_mulai_pengobatan,dt.paduan_oat,dt.sumber_obat,dt.sebutkan,dt.sebelum_pengobatan_hasil_mikroskopis,"
                    + "dt.sebelum_pengobatan_hasil_tes_cepat,dt.sebelum_pengobatan_hasil_biakan,dt.hasil_mikroskopis_bulan_2,dt.noreglab_bulan_2,"
                    + "dt.hasil_mikroskopis_bulan_3,dt.noreglab_bulan_3,dt.hasil_mikroskopis_bulan_5,dt.noreglab_bulan_5,dt.akhir_pengobatan_hasil_mikroskopis,"
                    + "dt.akhir_pengobatan_noreglab,dt.tanggal_hasil_akhir_pengobatan,dt.hasil_akhir_pengobatan,dt.tanggal_dianjurkan_tes,"
                    + "dt.tanggal_tes_hiv,dt.hasil_tes_hiv,dt.ppk,dt.art,dt.tb_dm,dt.terapi_dm,dt.pindah_ro,dt.status_pengobatan,"
                    + "dt.foto_toraks,dt.toraks_tdk_dilakukan,dt.keterangan,dt.kode_icd_x,py.nm_penyakit,dt.id_tb_03,dt.kd_propinsi,"
                    + "dt.nm_propinsi,dt.nm_kabupaten,dt.nm_kecamatan FROM reg_periksa rp INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis "
                    + "INNER JOIN data_tb dt ON dt.no_rawat=rp.no_rawat INNER JOIN kelurahan kl ON kl.kd_kel=p.kd_kel INNER JOIN penyakit py ON py.kd_penyakit=dt.kode_icd_x WHERE "
                    + "dt.tanggal_buat_laporan BETWEEN ? AND ? AND rp.no_rawat LIKE ? OR "
                    + "dt.tanggal_buat_laporan BETWEEN ? AND ? AND rp.no_rkm_medis LIKE ? OR "
                    + "dt.tanggal_buat_laporan BETWEEN ? AND ? AND p.nm_pasien LIKE ? OR "
                    + "dt.tanggal_buat_laporan BETWEEN ? AND ? AND p.alamat LIKE ? OR "
                    + "dt.tanggal_buat_laporan BETWEEN ? AND ? AND dt.no_ktp LIKE ? OR "
                    + "dt.tanggal_buat_laporan BETWEEN ? AND ? AND kl.nm_kel LIKE ? OR "
                    + "dt.tanggal_buat_laporan BETWEEN ? AND ? AND dt.nm_propinsi LIKE ? OR "
                    + "dt.tanggal_buat_laporan BETWEEN ? AND ? AND dt.nm_kabupaten LIKE ? OR "
                    + "dt.tanggal_buat_laporan BETWEEN ? AND ? AND dt.nm_kecamatan LIKE ? OR "
                    + "dt.tanggal_buat_laporan BETWEEN ? AND ? AND dt.nama_rujukan LIKE ? OR "
                    + "dt.tanggal_buat_laporan BETWEEN ? AND ? AND dt.klasifikasi_riwayat_pengobatan LIKE ? OR "
                    + "dt.tanggal_buat_laporan BETWEEN ? AND ? AND dt.klasifikasi_lokasi_anatomi LIKE ? OR "
                    + "dt.tanggal_buat_laporan BETWEEN ? AND ? AND dt.tipe_diagnosis LIKE ? ORDER BY dt.tanggal_buat_laporan");
            try{
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
                ps.setString(25,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                ps.setString(26,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                ps.setString(27,"%"+TCari.getText().trim()+"%");
                ps.setString(28,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                ps.setString(29,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                ps.setString(30,"%"+TCari.getText().trim()+"%");                  
                ps.setString(31,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                ps.setString(32,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                ps.setString(33,"%"+TCari.getText().trim()+"%");                
                ps.setString(34,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                ps.setString(35,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                ps.setString(36,"%"+TCari.getText().trim()+"%");                
                ps.setString(37,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                ps.setString(38,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                ps.setString(39,"%"+TCari.getText().trim()+"%");
                rs=ps.executeQuery();
                while(rs.next()){   
                    tabMode.addRow(new Object[]{
                        rs.getString("no_rawat"), 
                        rs.getString("no_rkm_medis"), 
                        rs.getString("nm_pasien"), 
                        rs.getString("jk"), 
                        rs.getString("tgl_lahir"),
                        rs.getString("umur") + " Th.", 
                        rs.getString("no_peserta"), 
                        rs.getString("no_ktp"), 
                        rs.getString("alamat"), 
                        rs.getString("id_kelurahan"),
                        rs.getString("nm_kel"), 
                        rs.getString("id_kecamatan"), 
                        rs.getString("nm_kecamatan"), 
                        rs.getString("kd_kabupaten"), 
                        rs.getString("nm_kabupaten"),
                        rs.getString("kd_propinsi"), 
                        rs.getString("nm_propinsi"), 
                        rs.getString("id_periode_laporan"), 
                        rs.getString("tanggal_buat_laporan"),
                        rs.getString("nama_rujukan"), 
                        rs.getString("sebutkan1"), 
                        rs.getString("klasifikasi_riwayat_pengobatan"), 
                        rs.getString("klasifikasi_lokasi_anatomi"),
                        rs.getString("tipe_diagnosis"), 
                        rs.getString("klasifikasi_status_hiv"), 
                        rs.getString("total_skoring_anak"), 
                        rs.getString("konfirmasiSkoring5"),
                        rs.getString("konfirmasiSkoring6"), 
                        rs.getString("tanggal_mulai_pengobatan"), 
                        rs.getString("paduan_oat"), 
                        rs.getString("sumber_obat"),
                        rs.getString("sebutkan"), 
                        rs.getString("sebelum_pengobatan_hasil_mikroskopis"), 
                        rs.getString("sebelum_pengobatan_hasil_tes_cepat"),
                        rs.getString("sebelum_pengobatan_hasil_biakan"), 
                        rs.getString("hasil_mikroskopis_bulan_2"), 
                        rs.getString("noreglab_bulan_2"),
                        rs.getString("hasil_mikroskopis_bulan_3"), 
                        rs.getString("noreglab_bulan_3"), 
                        rs.getString("hasil_mikroskopis_bulan_5"),
                        rs.getString("noreglab_bulan_5"), 
                        rs.getString("akhir_pengobatan_hasil_mikroskopis"), 
                        rs.getString("akhir_pengobatan_noreglab"),
                        rs.getString("tanggal_hasil_akhir_pengobatan"), 
                        rs.getString("hasil_akhir_pengobatan"), 
                        rs.getString("tanggal_dianjurkan_tes"),
                        rs.getString("tanggal_tes_hiv"), 
                        rs.getString("hasil_tes_hiv"), 
                        rs.getString("ppk"), 
                        rs.getString("art"), 
                        rs.getString("tb_dm"),
                        rs.getString("terapi_dm"), 
                        rs.getString("pindah_ro"), 
                        rs.getString("status_pengobatan"), 
                        rs.getString("foto_toraks"),
                        rs.getString("toraks_tdk_dilakukan"), 
                        rs.getString("keterangan"), 
                        rs.getString("kode_icd_x"), 
                        rs.getString("nm_penyakit"), 
                        rs.getString("id_tb_03")
                    });
                }  
            } catch(Exception e){
                System.out.println(e);
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
        kodeIDTB03.setText("");
        TNoRw.setText("");
        TNoRM.setText("");
        TNmPasien.setText("");
        JK.setText("");
        Tanggal.setText("");
        Umur.setText("");
        NoKartu.setText("");
        NIK.setText("");
        Alamat.setText("");
        KdKel.setText("");
        Kelurahan.setText("KELURAHAN");
        KdKec.setText("");
        Kecamatan.setText("KECAMATAN");
        KdKab.setText("");
        Kabupaten.setText("KABUPATEN");
        KdProp.setText("");
        Propinsi.setText("PROPINSI");        
        KeteranganRujukan.setText("");
        PaduanOAT.setText("");
        KeteranganSO.setText("");
        ChkAkhirObat.setSelected(false);
        tglAkhirBerobat.setEnabled(false);
        ChkDianjurHIV.setSelected(false);
        tglDianjurkanTesHIV.setEnabled(false);
        ChkTglTesHIV.setSelected(false);
        TanggalTesHIV.setEnabled(false);
        PemeriksaanLaboratAkhirNoReg.setText("");
        PemeriksaanLaboratBulan2NoReg.setText("");
        PemeriksaanLaboratBulan3NoReg.setText("");
        PemeriksaanLaboratBulan5NoReg.setText("");
        Keterangan.setText("");
        kdpenyakit.setText("");
        nmpenyakit.setText("");
        
        PeriodeLaporan.setSelectedIndex(0);
        Rujukan.setSelectedIndex(0);
        Riwayat.setSelectedIndex(0);
        Lokasi.setSelectedIndex(0);
        TipeDiagnosis.setSelectedIndex(0);
        StatusHIV.setSelectedIndex(0);
        SkoringAnak.setSelectedIndex(0);
        Skoring5.setSelectedIndex(0);
        Skoring6.setSelectedIndex(0);
        SumberObat.setSelectedIndex(0);
        SebelumPengobatanMikroskopis.setSelectedIndex(0);
        SebelumPengobatanTesCepat.setSelectedIndex(0);
        SebelumPengobatanBiakan.setSelectedIndex(0);
        PemeriksaanLaboratBulan2Mikroskopis.setSelectedIndex(0);
        PemeriksaanLaboratBulan3Mikroskopis.setSelectedIndex(0);
        PemeriksaanLaboratBulan5Mikroskopis.setSelectedIndex(0);
        PemeriksaanLaboratAkhirPengobatanMikroskopis.setSelectedIndex(0);
        HasilAkhirPengobatan.setSelectedIndex(0);
        HasilTesHIV.setSelectedIndex(0);
        PPK.setSelectedIndex(0);
        ART.setSelectedIndex(0);
        TBDM.setSelectedIndex(0);
        TerapiDM.setSelectedIndex(0);
        PindahRO.setSelectedIndex(0);
        Status.setSelectedIndex(0);
        FotoTorak.setSelectedIndex(0);
        ToraksTidakDilakukan.setSelectedIndex(0);        
        PeriodeLaporan.requestFocus();
    }

    private void getData() {
        emptTeks();
        akhirBerobat = "";
        cekDianjurkanHIV = "";
        cekTglTesHIV = "";
        
        if(tbTuberkulosis.getSelectedRow()!= -1){
            TNoRw.setText(tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),0).toString());
            TNoRM.setText(tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),1).toString());
            TNmPasien.setText(tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),2).toString());
            JK.setText(tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),3).toString().replaceAll("L","Laki-Laki").replaceAll("P","Perempuan"));
            Tanggal.setText(tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),4).toString());
            Umur.setText(tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),5).toString().replaceAll(" Th.",""));
            NoKartu.setText(tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),6).toString());
            NIK.setText(tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),7).toString());
            Alamat.setText(tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),8).toString());
            KdKel.setText(tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),9).toString());
            Kelurahan.setText(tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),10).toString());
            KdKec.setText(tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),11).toString());
            Kecamatan.setText(tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),12).toString());
            KdKab.setText(tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),13).toString());
            Kabupaten.setText(tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),14).toString());            
            KdProp.setText(tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),15).toString());
            Propinsi.setText(tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),16).toString());            
            PeriodeLaporan.setSelectedItem(tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),17).toString());
            Valid.SetTgl2(TanggalLaporan,tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),18).toString());
            Rujukan.setSelectedItem(tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),19).toString());
            KeteranganRujukan.setText(tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),20).toString());
            Riwayat.setSelectedItem(tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),21).toString());
            Lokasi.setSelectedItem(tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),22).toString());
            TipeDiagnosis.setSelectedItem(tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),23).toString());
            StatusHIV.setSelectedItem(tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),24).toString());
            SkoringAnak.setSelectedItem(tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),25).toString());
            Skoring5.setSelectedItem(tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),26).toString());
            Skoring6.setSelectedItem(tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),27).toString());   
            Valid.SetTgl(tglMulaiBerobat,tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),28).toString());
            PaduanOAT.setText(tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),29).toString());
            SumberObat.setSelectedItem(tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),30).toString());
            KeteranganSO.setText(tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),31).toString());
            SebelumPengobatanMikroskopis.setSelectedItem(tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),32).toString());
            SebelumPengobatanTesCepat.setSelectedItem(tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),33).toString());
            SebelumPengobatanBiakan.setSelectedItem(tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),34).toString());
            PemeriksaanLaboratBulan2Mikroskopis.setSelectedItem(tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),35).toString());
            PemeriksaanLaboratBulan2NoReg.setText(tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),36).toString());
            PemeriksaanLaboratBulan3Mikroskopis.setSelectedItem(tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),37).toString());
            PemeriksaanLaboratBulan3NoReg.setText(tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),38).toString());
            PemeriksaanLaboratBulan5Mikroskopis.setSelectedItem(tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),39).toString());
            PemeriksaanLaboratBulan5NoReg.setText(tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),40).toString());
            PemeriksaanLaboratAkhirPengobatanMikroskopis.setSelectedItem(tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),41).toString());
            PemeriksaanLaboratAkhirNoReg.setText(tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),42).toString());            
            HasilAkhirPengobatan.setSelectedItem(tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),44).toString());            
            HasilTesHIV.setSelectedItem(tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),47).toString());
            PPK.setSelectedItem(tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),48).toString());
            ART.setSelectedItem(tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),49).toString());
            TBDM.setSelectedItem(tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),50).toString());
            TerapiDM.setSelectedItem(tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),51).toString());
            PindahRO.setSelectedItem(tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),52).toString());
            Status.setSelectedItem(tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),53).toString());
            FotoTorak.setSelectedItem(tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),54).toString());
            ToraksTidakDilakukan.setSelectedItem(tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),55).toString());
            Keterangan.setText(tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),56).toString());
            kdpenyakit.setText(tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),57).toString());
            nmpenyakit.setText(tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),58).toString());
            kodeIDTB03.setText("ID TB 03 : " + tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(), 59).toString());
            
            akhirBerobat = tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),43).toString();
            cekDianjurkanHIV = tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),45).toString();
            cekTglTesHIV = tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),46).toString();
            
            if (akhirBerobat.equals("")) {
                ChkAkhirObat.setSelected(false);
                tglAkhirBerobat.setEnabled(false);
            } else {
                ChkAkhirObat.setSelected(true);
                tglAkhirBerobat.setEnabled(true);
                Valid.SetTgl(tglAkhirBerobat,tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(),43).toString());
            }
           
            if (cekDianjurkanHIV.equals("")) {
                ChkDianjurHIV.setSelected(false);
                tglDianjurkanTesHIV.setEnabled(false);
            } else {
                ChkDianjurHIV.setSelected(true);
                tglDianjurkanTesHIV.setEnabled(true);
                Valid.SetTgl(tglDianjurkanTesHIV, tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(), 45).toString());
            }
            
            if (cekTglTesHIV.equals("")) {
                ChkTglTesHIV.setSelected(false);
                TanggalTesHIV.setEnabled(false);
            } else {
                ChkTglTesHIV.setSelected(true);
                TanggalTesHIV.setEnabled(true);
                Valid.SetTgl(TanggalTesHIV, tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(), 46).toString());
            }            
        }
    }
    
    public void isCek(){
        TabRawat.setSelectedIndex(1);
        tampil();
        BtnSimpan.setEnabled(akses.getkemenkes_sitt());
        BtnHapus.setEnabled(akses.getkemenkes_sitt());
        BtnEdit.setEnabled(akses.getkemenkes_sitt());
    }
    
    public void setNoRM(String norawat){
        TabRawat.setSelectedIndex(0);
        TNoRw.setText(norawat);
        try {
            ps=koneksi.prepareStatement(
                    "select p.no_rkm_medis,p.nm_pasien,p.jk,p.tgl_lahir,rp.umurdaftar, "
                    + "rp.sttsumur,ifnull(p.no_peserta,'0') no_peserta,ifnull(p.no_ktp,'0') no_ktp,"
                    + "p.alamat,p.kd_kel,kl.nm_kel,p.kd_kec,kc.nm_kec,p.kd_kab,kb.nm_kab from pasien p "
                    + "inner join reg_periksa rp on rp.no_rkm_medis=p.no_rkm_medis "
                    + "inner join kelurahan kl ON kl.kd_kel=p.kd_kel "
                    + "inner join kecamatan kc ON kc.kd_kec=p.kd_kec "
                    + "inner join kabupaten kb ON kb.kd_kab=p.kd_kab where rp.no_rawat = ?"
            );
            try {
                ps.setString(1, norawat);
                rs=ps.executeQuery();
                if(rs.next()){
                    TNoRM.setText(rs.getString("no_rkm_medis"));
                    TNmPasien.setText(rs.getString("nm_pasien"));
                    JK.setText(rs.getString("jk").replaceAll("P", "Perempuan").replaceAll("L", "Laki-Laki"));
                    Tanggal.setText(rs.getString("tgl_lahir"));
                    if (rs.getString("sttsumur").equals("Th")) {
                        Umur.setText(rs.getString("umurdaftar"));
                    } else {
                        Umur.setText("0");
                    }
                    NoKartu.setText(rs.getString("no_peserta"));
                    NIK.setText(rs.getString("no_ktp").replaceAll("", "0").replaceAll("-", "0"));
                    Alamat.setText(rs.getString("alamat"));
                    KdKel.setText(rs.getString("kd_kel"));
                    Kelurahan.setText(rs.getString("nm_kel"));
//                    KdKec.setText(rs.getString("kd_kec"));
//                    Kecamatan.setText(rs.getString("nm_kec"));
//                    KdKab.setText(rs.getString("kd_kab"));
//                    Kabupaten.setText(rs.getString("nm_kab")); 
                }
            } catch (Exception e) {
                System.out.println("Notif : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notif : "+e);
        }
    }
    
    private void editData() {
        if (tbTuberkulosis.getSelectedRow() > -1) {
            status = "";
            id_tb_03 = "";
            cekData();

            try {
                headers = new HttpHeaders();
                headers.add("X-rs-id", idrs);
                headers.add("X-Timestamp", String.valueOf(api.GetUTCdatetimeAsString()));
                headers.add("X-pass", koneksiDB.PASSSITT());
                headers.add("Content-Type", "application/json");
                requestJson = "{"
                        + "\"id_tb_03\":\"" + tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(), 59).toString() + "\","
                        + "\"kd_pasien\":\"" + TNmPasien.getText().toUpperCase() + "\","
                        + "\"nik\":\"" + NIK.getText() + "\","
                        + "\"jenis_kelamin\":\"" + JK.getText().substring(0, 1) + "\","
                        + "\"alamat_lengkap\":\"" + Alamat.getText() + "\","
                        + "\"id_propinsi_faskes\":\"63\","
                        + "\"kd_kabupaten_faskes\":\"6303\","
                        + "\"id_propinsi_pasien\":\"" + KdProp.getText() + "\","
                        + "\"kd_kabupaten_pasien\":\"" + KdKab.getText() + "\","
                        + "\"kd_fasyankes\":\"" + idrs + "\","
                        + "\"kode_icd_x\":\"" + kdpenyakit.getText() + "\","
                        + "\"tipe_diagnosis\":\"" + TipeDiagnosis.getSelectedItem().toString().replaceAll("Terkonfirmasi bakteriologis", "1").toString().replaceAll("Terdiagnosis klinis", "2") + "\","
                        + "\"klasifikasi_lokasi_anatomi\":\"" + Lokasi.getSelectedItem().toString().replaceAll("Paru", "1").toString().replaceAll("Ekstraparu", "2") + "\","
                        + "\"klasifikasi_riwayat_pengobatan\":\"" + Riwayat.getSelectedItem().toString().replaceAll("Baru", "1").toString().replaceAll("Kambuh", "2").toString().replaceAll("Diobati setelah gagal", "3").toString().replaceAll("Diobati Setelah Putus Berobat", "4").toString().replaceAll("Lain-lain", "5").toString().replaceAll("Riwayat Pengobatan Sebelumnya Tidak Diketahui", "6").toString().replaceAll("Pindahan", "7") + "\","
                        + "\"tanggal_mulai_pengobatan\":\"" + Valid.SetTgl4(tglMulaiBerobat.getSelectedItem() + "") + "\","
                        + "\"paduan_oat\":\"" + PaduanOAT.getText() + "\","
                        + "\"sebelum_pengobatan_hasil_mikroskopis\":\"" + SebelumPengobatanMikroskopis.getSelectedItem() + "\","
                        + "\"sebelum_pengobatan_hasil_tes_cepat\":\"" + SebelumPengobatanTesCepat.getSelectedItem() + "\","
                        + "\"sebelum_pengobatan_hasil_biakan\":\"" + SebelumPengobatanBiakan.getSelectedItem() + "\","
                        + "\"hasil_mikroskopis_bulan_2\":\"" + ok1 + "\","
                        + "\"hasil_mikroskopis_bulan_3\":\"" + ok2 + "\","
                        + "\"hasil_mikroskopis_bulan_5\":\"" + ok3 + "\","
                        + "\"akhir_pengobatan_hasil_mikroskopis\":\"" + ok4 + "\","
                        + "\"tanggal_hasil_akhir_pengobatan\":\"" + akhirBerobat + "\","
                        + "\"hasil_akhir_pengobatan\":\"" + ok5 + "\","
                        + "\"tgl_lahir\":\"" + Tanggal.getText().replaceAll("-", "") + "\","
                        + "\"foto_toraks\":\"" + FotoTorak.getSelectedItem() + "\""
                        + "}";

                System.out.println(requestJson);
                requestEntity = new HttpEntity(requestJson, headers);
                requestJson = api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody();
                System.out.println(requestJson);
                root = mapper.readTree(requestJson);
                id_tb_03 = tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(), 59).toString();
                status = root.path("status").asText();

                if (status.contains("sukses") == true || status.contains("berhasil") == true) {
                    if (Sequel.mengedittf("data_tb", "no_rawat=?", "no_rawat=?,id_tb_03=?,id_periode_laporan=?,tanggal_buat_laporan=?,tahun_buat_laporan=?,kd_wasor=?,noregkab=?,kd_propinsi=?,"
                            + "kd_kabupaten=?,id_kecamatan=?,id_kelurahan=?,nama_rujukan=?,sebutkan1=?,tipe_diagnosis=?,klasifikasi_lokasi_anatomi=?,klasifikasi_riwayat_pengobatan=?,"
                            + "klasifikasi_status_hiv=?,total_skoring_anak=?,konfirmasiSkoring5=?,konfirmasiSkoring6=?,tanggal_mulai_pengobatan=?,paduan_oat=?,sumber_obat=?,sebutkan=?,"
                            + "sebelum_pengobatan_hasil_mikroskopis=?,sebelum_pengobatan_hasil_tes_cepat=?,sebelum_pengobatan_hasil_biakan=?,noreglab_bulan_2=?,hasil_mikroskopis_bulan_2=?,"
                            + "noreglab_bulan_3=?,hasil_mikroskopis_bulan_3=?,noreglab_bulan_5=?,hasil_mikroskopis_bulan_5=?,akhir_pengobatan_noreglab=?,akhir_pengobatan_hasil_mikroskopis=?,"
                            + "tanggal_hasil_akhir_pengobatan=?,hasil_akhir_pengobatan=?,tanggal_dianjurkan_tes=?,tanggal_tes_hiv=?,hasil_tes_hiv=?,ppk=?,art=?,tb_dm=?,terapi_dm=?,pindah_ro=?,"
                            + "status_pengobatan=?,foto_toraks=?,toraks_tdk_dilakukan=?,keterangan=?,kode_icd_x=?,nm_propinsi=?,nm_kabupaten=?,nm_kecamatan=?,no_rkm_medis=?,no_ktp=?", 56, new String[]{
                                TNoRw.getText(), id_tb_03, PeriodeLaporan.getSelectedItem().toString(), Valid.SetTgl(TanggalLaporan.getSelectedItem() + "") + " " + TanggalLaporan.getSelectedItem().toString().substring(11, 19),
                                Valid.SetTgl(TanggalLaporan.getSelectedItem() + "").substring(0, 4), "6303", "0", KdProp.getText(), KdKab.getText(), KdKec.getText(), KdKel.getText(), Rujukan.getSelectedItem().toString(),
                                KeteranganRujukan.getText(), TipeDiagnosis.getSelectedItem().toString().replaceAll("1. Terkonfirmasi bakteriologis", "Terkonfirmasi bakteriologis").toString().replaceAll("2. Terdiagnosis klinis", "Terdiagnosis klinis"), Lokasi.getSelectedItem().toString().replaceAll("1. Paru", "Paru").toString().replaceAll("2. Ekstraparu", "Ekstraparu"), Riwayat.getSelectedItem().toString().replaceAll("1. Baru", "Baru").toString().replaceAll("2. Kambuh", "Kambuh").toString().replaceAll("3. Diobati setelah gagal", "Diobati setelah gagal").toString().replaceAll("4. Diobati Setelah Putus Berobat", "Diobati Setelah Putus Berobat").toString().replaceAll("5. Lain-lain", "Lain-lain").toString().replaceAll("6. Riwayat Pengobatan Sebelumnya Tidak Diketahui", "Riwayat Pengobatan Sebelumnya Tidak Diketahui").toString().replaceAll("7. Pindahan", "Pindahan"), StatusHIV.getSelectedItem().toString(),
                                SkoringAnak.getSelectedItem().toString(), Skoring5.getSelectedItem().toString(), Skoring6.getSelectedItem().toString(), Valid.SetTgl4(tglMulaiBerobat.getSelectedItem() + ""),
                                PaduanOAT.getText(), SumberObat.getSelectedItem().toString(), KeteranganSO.getText(), SebelumPengobatanMikroskopis.getSelectedItem().toString(), SebelumPengobatanTesCepat.getSelectedItem().toString(),
                                SebelumPengobatanBiakan.getSelectedItem().toString(), PemeriksaanLaboratBulan2NoReg.getText(), PemeriksaanLaboratBulan2Mikroskopis.getSelectedItem().toString(),
                                PemeriksaanLaboratBulan3NoReg.getText(), PemeriksaanLaboratBulan3Mikroskopis.getSelectedItem().toString(), PemeriksaanLaboratBulan5NoReg.getText(),
                                PemeriksaanLaboratBulan5Mikroskopis.getSelectedItem().toString(), PemeriksaanLaboratAkhirNoReg.getText(), PemeriksaanLaboratAkhirPengobatanMikroskopis.getSelectedItem().toString(),
                                tglSimpan1, HasilAkhirPengobatan.getSelectedItem().toString(), tglSimpan2, tglSimpan3, HasilTesHIV.getSelectedItem().toString(), PPK.getSelectedItem().toString(), ART.getSelectedItem().toString(),
                                TBDM.getSelectedItem().toString(), TerapiDM.getSelectedItem().toString(), PindahRO.getSelectedItem().toString(), Status.getSelectedItem().toString(), FotoTorak.getSelectedItem().toString(),
                                ToraksTidakDilakukan.getSelectedItem().toString(), Keterangan.getText(), kdpenyakit.getText(), Propinsi.getText(), Kabupaten.getText(), Kecamatan.getText(),TNoRM.getText(),NIK.getText(),
                                tbTuberkulosis.getValueAt(tbTuberkulosis.getSelectedRow(), 0).toString()
                            }) == true) {
                        emptTeks();
                        tampil();
                        JOptionPane.showMessageDialog(null, "Pesan dari Bridging SITB : " + status);
                        TabRawat.setSelectedIndex(1);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Pesan dari Bridging SITB : " + status);
                    System.out.println("Yang dikirim : " + requestJson);
                }

            } catch (Exception ex) {
                System.out.println("Notifikasi Bridging : " + ex);
                if (ex.toString().contains("UnknownHostException")) {
                    JOptionPane.showMessageDialog(null, "Koneksi ke server SITT terputus...!");
                } else if (ex.toString().contains("502")) {
                    JOptionPane.showMessageDialog(null, "Connection timed out. Hayati lelah bang...!");
                }
            }
        }
    }

    private void simpanData() {
        status = "";
        id_tb_03 = "";
        cekData();

        try {
            headers = new HttpHeaders();
            headers.add("X-rs-id", idrs);
            headers.add("X-Timestamp", String.valueOf(api.GetUTCdatetimeAsString()));
            headers.add("X-pass", koneksiDB.PASSSITT());
            headers.add("Content-Type", "application/json");

            requestJson = "{"
                    + "\"id_tb_03\":\"\","
                    + "\"kd_pasien\":\"" + TNmPasien.getText().toUpperCase() + "\","
                    + "\"nik\":\"" + NIK.getText() + "\","
                    + "\"jenis_kelamin\":\"" + JK.getText().substring(0, 1) + "\","
                    + "\"alamat_lengkap\":\"" + Alamat.getText() + "\","
                    + "\"id_propinsi_faskes\":\"63\","
                    + "\"kd_kabupaten_faskes\":\"6303\","
                    + "\"id_propinsi_pasien\":\"" + KdProp.getText() + "\","
                    + "\"kd_kabupaten_pasien\":\"" + KdKab.getText() + "\","
                    + "\"kd_fasyankes\":\"" + idrs + "\","
                    + "\"kode_icd_x\":\"" + kdpenyakit.getText() + "\","
                    + "\"tipe_diagnosis\":\"" + TipeDiagnosis.getSelectedItem().toString().replaceAll("Terkonfirmasi bakteriologis", "1").toString().replaceAll("Terdiagnosis klinis", "2") + "\","
                    + "\"klasifikasi_lokasi_anatomi\":\"" + Lokasi.getSelectedItem().toString().replaceAll("Paru", "1").toString().replaceAll("Ekstraparu", "2") + "\","
                    + "\"klasifikasi_riwayat_pengobatan\":\"" + Riwayat.getSelectedItem().toString().replaceAll("Baru", "1").toString().replaceAll("Kambuh", "2").toString().replaceAll("Diobati setelah gagal", "3").toString().replaceAll("Diobati Setelah Putus Berobat", "4").toString().replaceAll("Lain-lain", "5").toString().replaceAll("Riwayat Pengobatan Sebelumnya Tidak Diketahui", "6").toString().replaceAll("Pindahan", "7") + "\","
                    + "\"tanggal_mulai_pengobatan\":\"" + Valid.SetTgl4(tglMulaiBerobat.getSelectedItem() + "") + "\","
                    + "\"paduan_oat\":\"" + PaduanOAT.getText() + "\","
                    + "\"sebelum_pengobatan_hasil_mikroskopis\":\"" + SebelumPengobatanMikroskopis.getSelectedItem() + "\","
                    + "\"sebelum_pengobatan_hasil_tes_cepat\":\"" + SebelumPengobatanTesCepat.getSelectedItem() + "\","
                    + "\"sebelum_pengobatan_hasil_biakan\":\"" + SebelumPengobatanBiakan.getSelectedItem() + "\","
                    + "\"hasil_mikroskopis_bulan_2\":\"" + ok1 + "\","
                    + "\"hasil_mikroskopis_bulan_3\":\"" + ok2 + "\","
                    + "\"hasil_mikroskopis_bulan_5\":\"" + ok3 + "\","
                    + "\"akhir_pengobatan_hasil_mikroskopis\":\"" + ok4 + "\","
                    + "\"tanggal_hasil_akhir_pengobatan\":\"" + akhirBerobat + "\","
                    + "\"hasil_akhir_pengobatan\":\"" + ok5 + "\","
                    + "\"tgl_lahir\":\"" + Tanggal.getText().replaceAll("-", "") + "\","
                    + "\"foto_toraks\":\"" + FotoTorak.getSelectedItem() + "\""
                    + "}";

            System.out.println(requestJson);
            requestEntity = new HttpEntity(requestJson, headers);
            requestJson = api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody();
            System.out.println(requestJson);
            root = mapper.readTree(requestJson);
            status = root.path("status").asText();
            id_tb_03 = root.path("id_tb_03").asText();

            if (status.equals("berhasil") || status.equals("sukses")) {
                if (Sequel.menyimpantf("data_tb", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "Data", 55, new String[]{
                    TNoRw.getText(), id_tb_03, PeriodeLaporan.getSelectedItem().toString(), Valid.SetTgl(TanggalLaporan.getSelectedItem() + "") + " " + TanggalLaporan.getSelectedItem().toString().substring(11, 19),
                    Valid.SetTgl(TanggalLaporan.getSelectedItem() + "").substring(0, 4), "6303", "0", KdProp.getText(), KdKab.getText(), KdKec.getText(), KdKel.getText(), Rujukan.getSelectedItem().toString(),
                    KeteranganRujukan.getText(), TipeDiagnosis.getSelectedItem().toString().replaceAll("1. Terkonfirmasi bakteriologis", "Terkonfirmasi bakteriologis").toString().replaceAll("2. Terdiagnosis klinis", "Terdiagnosis klinis"), Lokasi.getSelectedItem().toString().replaceAll("1. Paru", "Paru").toString().replaceAll("2. Ekstraparu", "Ekstraparu"), Riwayat.getSelectedItem().toString().replaceAll("1. Baru", "Baru").toString().replaceAll("2. Kambuh", "Kambuh").toString().replaceAll("3. Diobati setelah gagal", "Diobati setelah gagal").toString().replaceAll("4. Diobati Setelah Putus Berobat", "Diobati Setelah Putus Berobat").toString().replaceAll("5. Lain-lain", "Lain-lain").toString().replaceAll("6. Riwayat Pengobatan Sebelumnya Tidak Diketahui", "Riwayat Pengobatan Sebelumnya Tidak Diketahui").toString().replaceAll("7. Pindahan", "Pindahan"), StatusHIV.getSelectedItem().toString(),
                    SkoringAnak.getSelectedItem().toString(), Skoring5.getSelectedItem().toString(), Skoring6.getSelectedItem().toString(), Valid.SetTgl4(tglMulaiBerobat.getSelectedItem() + ""),
                    PaduanOAT.getText(), SumberObat.getSelectedItem().toString(), KeteranganSO.getText(), SebelumPengobatanMikroskopis.getSelectedItem().toString(), SebelumPengobatanTesCepat.getSelectedItem().toString(),
                    SebelumPengobatanBiakan.getSelectedItem().toString(), PemeriksaanLaboratBulan2NoReg.getText(), PemeriksaanLaboratBulan2Mikroskopis.getSelectedItem().toString(),
                    PemeriksaanLaboratBulan3NoReg.getText(), PemeriksaanLaboratBulan3Mikroskopis.getSelectedItem().toString(), PemeriksaanLaboratBulan5NoReg.getText(),
                    PemeriksaanLaboratBulan5Mikroskopis.getSelectedItem().toString(), PemeriksaanLaboratAkhirNoReg.getText(), PemeriksaanLaboratAkhirPengobatanMikroskopis.getSelectedItem().toString(),
                    tglSimpan1, HasilAkhirPengobatan.getSelectedItem().toString(), tglSimpan2, tglSimpan3, HasilTesHIV.getSelectedItem().toString(), PPK.getSelectedItem().toString(), ART.getSelectedItem().toString(),
                    TBDM.getSelectedItem().toString(), TerapiDM.getSelectedItem().toString(), PindahRO.getSelectedItem().toString(), Status.getSelectedItem().toString(), FotoTorak.getSelectedItem().toString(),
                    ToraksTidakDilakukan.getSelectedItem().toString(), Keterangan.getText(), kdpenyakit.getText(), Propinsi.getText(), Kabupaten.getText(), Kecamatan.getText(), TNoRM.getText(), NIK.getText()
                }) == true) {
                    emptTeks();
                    tampil();
                    JOptionPane.showMessageDialog(null, "Pesan dari Bridging SITB : " + status);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Pesan dari Bridging SITB : " + status);
                System.out.println("Yang dikirim : " + requestJson);
            }

        } catch (Exception ex) {
            System.out.println("Notifikasi Bridging : " + ex);
            if (ex.toString().contains("UnknownHostException")) {
                JOptionPane.showMessageDialog(null, "Koneksi ke server SITB terputus...!");
            } else if (ex.toString().contains("502")) {
                JOptionPane.showMessageDialog(null, "Connection timed out. Hayati lelah bang...!");
            }
        }
    }
    
    private void cekData() {
        akhirBerobat = "";
        tglSimpan1 = "";
        tglSimpan2 = "";
        tglSimpan3 = "";
        ok1 = "";
        ok2 = "";
        ok3 = "";
        ok4 = "";
        ok5 = "";

        if (ChkAkhirObat.isSelected() == false) {
            akhirBerobat = "";
            tglSimpan1 = "0000-00-00";
        } else {
            akhirBerobat = Valid.SetTgl4(tglAkhirBerobat.getSelectedItem() + "");
            tglSimpan1 = Valid.SetTgl4(tglAkhirBerobat.getSelectedItem() + "");
        }

        if (ChkDianjurHIV.isSelected() == false) {
            tglSimpan2 = "0000-00-00";
        } else {
            tglSimpan2 = Valid.SetTgl4(tglDianjurkanTesHIV.getSelectedItem() + "");
        }

        if (ChkTglTesHIV.isSelected() == false) {
            tglSimpan3 = "0000-00-00";
        } else {
            tglSimpan3 = Valid.SetTgl4(TanggalTesHIV.getSelectedItem() + "");
        }

        if (PemeriksaanLaboratBulan2Mikroskopis.getSelectedIndex() == 0) {
            ok1 = "";
        } else {
            ok1 = PemeriksaanLaboratBulan2Mikroskopis.getSelectedItem().toString();
        }

        if (PemeriksaanLaboratBulan3Mikroskopis.getSelectedIndex() == 0) {
            ok2 = "";
        } else {
            ok2 = PemeriksaanLaboratBulan3Mikroskopis.getSelectedItem().toString();
        }

        if (PemeriksaanLaboratBulan5Mikroskopis.getSelectedIndex() == 0) {
            ok3 = "";
        } else {
            ok3 = PemeriksaanLaboratBulan5Mikroskopis.getSelectedItem().toString();
        }

        if (PemeriksaanLaboratAkhirPengobatanMikroskopis.getSelectedIndex() == 0) {
            ok4 = "";
        } else {
            ok4 = PemeriksaanLaboratAkhirPengobatanMikroskopis.getSelectedItem().toString();
        }

        if (HasilAkhirPengobatan.getSelectedIndex() == 0) {
            ok5 = "";
        } else {
            ok5 = HasilAkhirPengobatan.getSelectedItem().toString();
        }
    }
}