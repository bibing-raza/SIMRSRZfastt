/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgPerawatan.java
 *
 * Created on May 23, 2010, 6:36:30 PM
 */

package simrskhanza;

import bridging.ApiLIS;
import keuangan.DlgJnsPerawatanLab;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import kepegawaian.DlgCariPetugas;
import keuangan.Jurnal;

/**
 *
 * @author dosen
 */
public final class DlgPeriksaLaboratorium extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode2, tabMode3;
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Connection koneksi = koneksiDB.condb();
    private Jurnal jur = new Jurnal();
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    private DlgMasterFaskes faskes = new DlgMasterFaskes(null, false);
    private PreparedStatement pstindakan, pstindakan2, pstindakan3, pstindakan4, pstampil, pstampil2, pstampil3, pstampil4,
            pssimpanperiksa, psdetailpriksa, pscariperawatan, psset_tarif, pssetpj, psrekening, psp, psisi, psOrder;
    private ResultSet rstindakan, rstampil, rscari, rsset_tarif, rssetpj, rsrekening, rsp, rsisi, rsOrder;
    private boolean[] pilih, pilih2;
    private String[] kode, nama, pemeriksaan2, hasil2, satuan2, nilai_rujukan2, keterangan2, idtemplate2;
    private double[] total, bagian_rs, bhp, tarif_perujuk, tarif_tindakan_dokter, tarif_tindakan_petugas, kso, menejemen,
            biaya_item2, bagian_rs2, bhp2, bagian_perujuk2, bagian_dokter2, bagian_laborat2, kso2, menejemen2;
    private int jml = 0, i = 0, index = 0, jml2 = 0, i2 = 0, i3 = 0, index2 = 0, cekUtama = 0, cekDetail = 0;
    private String noorder = "", kelas = "", kamar, namakamar, cara_bayar_lab = "Yes", kelas_lab = "Yes",
            pilihan = "", status = "", xmlLIS = "", requestJson, unitnya = "",cekPeriksa="";
    private double ttl = 0, item = 0;
    private boolean sukses = false;
    private final Properties prop = new Properties();
    private ApiLIS mas_lis = new ApiLIS();
    private double ttljmdokter = 0, ttljmpetugas = 0, ttlkso = 0, ttlpendapatan = 0, ttlbhp = 0;
    private String Suspen_Piutang_Laborat_Ranap = "", Laborat_Ranap = "", Beban_Jasa_Medik_Dokter_Laborat_Ranap = "", kdPenjab = "",
            Utang_Jasa_Medik_Dokter_Laborat_Ranap = "", Beban_Jasa_Medik_Petugas_Laborat_Ranap = "", Utang_Jasa_Medik_Petugas_Laborat_Ranap = "",
            Beban_Kso_Laborat_Ranap = "", Utang_Kso_Laborat_Ranap = "", kdUnit = "", HPP_Persediaan_Laborat_Rawat_inap = "", cekPetugas = "",
            Persediaan_BHP_Laborat_Rawat_Inap = "", diagnosa_ralan = "", diagnosa_ranap = "", nm_unit = "", nm_penjab = "", alamatPerujuk = "",
            nmgedung = "", kdptg = "";

    /** Creates new form DlgPerawatan
     * @param parent
     * @param modal */
    public DlgPeriksaLaboratorium(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(8,1);
        setSize(885,674);

        Object[] row={
            "P","Pemeriksaan","Hasil","Satuan","Nilai Rujukan",
            "Keterangan","id_template","Biaya Item","bagian_rs","bhp",
            "bagian_perujuk","bagian_dokter","bagian_laborat","kso","menejemen"};
        
        tabMode=new DefaultTableModel(null,row){
             @Override public boolean isCellEditable(int rowIndex, int colIndex){
                    boolean a = false;
                    if ((colIndex==0)||(colIndex==2)||(colIndex==4)||(colIndex==5)) {
                        a=true;
                    }
                    return a;
             }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class,
                java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class,
                java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }              
        };
        
        tbPemeriksaan.setModel(tabMode);
        tbPemeriksaan.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPemeriksaan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 15; i++) {
            TableColumn column = tbPemeriksaan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(192);
            } else if (i == 2) {
                column.setPreferredWidth(130);
            } else if (i == 3) {
                column.setPreferredWidth(70);
            } else if (i == 4) {
                column.setPreferredWidth(130);
            } else if (i == 5) {
                column.setPreferredWidth(150);
            } else if (i == 6) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 7) {
                column.setPreferredWidth(80);
            } else {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        
        tbPemeriksaan.setDefaultRenderer(Object.class, new WarnaTable());

        Object[] row2 = {"P", "Kode Periksa", "Nama Pemeriksaan", "Tarif", "bagian_rs", "bhp", "tarif_perujuk",
            "tarif_tindakan_dokter", "tarif_tindakan_petugas", "K.S.O.", "Menejemen"};
        tabMode2=new DefaultTableModel(null,row2){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if ((colIndex==0)) {
                    a=true;
                }
                return a;
             }
             Class[] types = new Class[] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class,
                java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class,
                java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };

        tbTarif.setModel(tabMode2);
        tbTarif.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbTarif.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 11; i++) {
            TableColumn column = tbTarif.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(100);
            } else if (i == 2) {
                column.setPreferredWidth(380);
            } else if (i == 3) {
                column.setPreferredWidth(120);
            } else {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbTarif.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode3=new DefaultTableModel(null, new Object[]{
            "No.", "Cek", "CITO", "Nama Pemeriksaan Lab.", "Tanggal", "Jam",
            "Status", "norawat", "nominta", "Dokter Perujuk", "No. Kirim"
        }) {
              @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if (colIndex==1) {
                    a=true;
                }
                return a;
             }
             Class[] types = new Class[] {
                 java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class,
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                 java.lang.Object.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        
        tbMintaPeriksa.setModel(tabMode3);
        tbMintaPeriksa.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbMintaPeriksa.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 11; i++) {
            TableColumn column = tbMintaPeriksa.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(28);
            } else if (i == 1) {
                column.setPreferredWidth(30);
            } else if (i == 2) {
                column.setPreferredWidth(50);
            } else if (i == 3) {
                column.setPreferredWidth(200);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(75);
            } else if (i == 6) {
                column.setPreferredWidth(55);
            } else if (i == 7) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 8) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 9) {
                column.setPreferredWidth(200);
            } else if (i == 10) {
                column.setPreferredWidth(100);
            }
        }
        tbMintaPeriksa.setDefaultRenderer(Object.class, new WarnaTable());

        TNoRw.setDocument(new batasInput((byte) 17).getKata(TNoRw));
        Jk.setDocument(new batasInput((byte) 5).getKata(Jk));
        Umur.setDocument(new batasInput((byte) 5).getKata(Umur));
        KodePerujuk.setDocument(new batasInput((byte) 20).getKata(KodePerujuk));
        Pemeriksaan.setDocument(new batasInput((byte) 100).getKata(Pemeriksaan));        
        TnoTlpnPasien.setDocument(new batasInput((byte) 15).getOnlyAngka(TnoTlpnPasien));
        TnoTlpnDokter.setDocument(new batasInput((byte) 13).getOnlyAngka(TnoTlpnDokter));
        TnoTlpnFaskes.setDocument(new batasInput((byte) 15).getOnlyAngka(TnoTlpnFaskes));        
        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));

        if(koneksiDB.cariCepat().equals("aktif")){
            Pemeriksaan.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {tampiltarif();}
                @Override
                public void removeUpdate(DocumentEvent e) {tampiltarif();}
                @Override
                public void changedUpdate(DocumentEvent e) {tampiltarif();}
            });
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {tampil();}
                @Override
                public void removeUpdate(DocumentEvent e) {tampil();}
                @Override
                public void changedUpdate(DocumentEvent e) {tampil();}
            });
        } 
        ChkJln.setSelected(true);
        jam();   
        
        faskes.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(akses.getform().equals("DlgPeriksaLaboratorium")){
                    if(faskes.getTable().getSelectedRow()!= -1){                   
                        kdFaskes.setText(faskes.getTable().getValueAt(faskes.getTable().getSelectedRow(),1).toString());
                        TnmFaskes.setText(faskes.getTable().getValueAt(faskes.getTable().getSelectedRow(),3).toString());
                        alamatPerujuk = faskes.getTable().getValueAt(faskes.getTable().getSelectedRow(),6).toString();
                        TnoTlpnFaskes.setText(faskes.getTable().getValueAt(faskes.getTable().getSelectedRow(),7).toString());
                        btnFaskes.requestFocus();
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
        
        petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(akses.getform().equals("DlgPeriksaLaboratorium")){
                    if(petugas.getTable().getSelectedRow() != -1) {
                        kdptg = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                        NmPtg.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                        btnPetugas.requestFocus();
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
        
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(akses.getform().equals("DlgPeriksaLaboratorium")) {
                    if (dokter.getTable().getSelectedRow() != -1) {
                        if (pilihan.equals("perujuk")) {
                            KodePerujuk.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                            NmPerujuk.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                            TnoTlpnDokter.setText(Sequel.cariIsi("select no_telp from petugas where nip='" + KodePerujuk.getText() + "'"));
                            KodePerujuk.requestFocus();
                        } else if (pilihan.equals("penjab")) {
                            KodePj.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                            NmDokterPj.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                            KodePj.requestFocus();
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
        
        try {
            psrekening=koneksi.prepareStatement("select * from set_akun_ranap");
            try {
                rsrekening=psrekening.executeQuery();
                while(rsrekening.next()){
                    Suspen_Piutang_Laborat_Ranap=rsrekening.getString("Suspen_Piutang_Laborat_Ranap");
                    Laborat_Ranap=rsrekening.getString("Laborat_Ranap");
                    Beban_Jasa_Medik_Dokter_Laborat_Ranap=rsrekening.getString("Beban_Jasa_Medik_Dokter_Laborat_Ranap");
                    Utang_Jasa_Medik_Dokter_Laborat_Ranap=rsrekening.getString("Utang_Jasa_Medik_Dokter_Laborat_Ranap");
                    Beban_Jasa_Medik_Petugas_Laborat_Ranap=rsrekening.getString("Beban_Jasa_Medik_Petugas_Laborat_Ranap");
                    Utang_Jasa_Medik_Petugas_Laborat_Ranap=rsrekening.getString("Utang_Jasa_Medik_Petugas_Laborat_Ranap");
                    Beban_Kso_Laborat_Ranap=rsrekening.getString("Beban_Kso_Laborat_Ranap");
                    Utang_Kso_Laborat_Ranap=rsrekening.getString("Utang_Kso_Laborat_Ranap");
                    HPP_Persediaan_Laborat_Rawat_inap=rsrekening.getString("HPP_Persediaan_Laborat_Rawat_inap");
                    Persediaan_BHP_Laborat_Rawat_Inap=rsrekening.getString("Persediaan_BHP_Laborat_Rawat_Inap");
                }
            } catch (Exception e) {
                System.out.println("Notif Rekening : "+e);
            } finally{
                if(rsrekening!=null){
                    rsrekening.close();
                }
                if(psrekening!=null){
                    psrekening.close();
                }
            }            
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            psset_tarif=koneksi.prepareStatement("select * from set_tarif");
            try {
                rsset_tarif=psset_tarif.executeQuery();
                if(rsset_tarif.next()){
                    cara_bayar_lab=rsset_tarif.getString("cara_bayar_lab");
                    kelas_lab=rsset_tarif.getString("kelas_lab");
                }else{
                    cara_bayar_lab="Yes";
                    kelas_lab="Yes";
                } 
            } catch (Exception e) {
                System.out.println(e);
            } finally{
                if(rsset_tarif!=null){
                    rsset_tarif.close();
                }
                if(psset_tarif!=null){
                    psset_tarif.close();
                }
            } 
        } catch (Exception e) {
            System.out.println(e);
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

        Penjab = new widget.TextBox();
        Jk = new widget.TextBox();
        Umur = new widget.TextBox();
        buttonGroup1 = new javax.swing.ButtonGroup();
        Alamat = new widget.TextBox();
        Popup = new javax.swing.JPopupMenu();
        ppBersihkan = new javax.swing.JMenuItem();
        ppSemua = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        panelGlass8 = new widget.panelisi();
        jLabel3 = new widget.Label();
        ketAktif = new widget.Label();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnPrint = new widget.Button();
        BtnNota = new widget.Button();
        jLabel10 = new widget.Label();
        BtnCari = new widget.Button();
        BtnKeluar = new widget.Button();
        FormInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        PanelInput = new widget.PanelBiasa();
        TNoRw = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel11 = new widget.Label();
        jLabel7 = new widget.Label();
        jLabel9 = new widget.Label();
        Pemeriksaan = new widget.TextBox();
        jLabel12 = new widget.Label();
        btnPetugas = new widget.Button();
        NmPtg = new widget.TextBox();
        Tanggal = new widget.Tanggal();
        CmbJam = new widget.ComboBox();
        CmbMenit = new widget.ComboBox();
        CmbDetik = new widget.ComboBox();
        ChkJln = new widget.CekBox();
        jLabel16 = new widget.Label();
        BtnCari1 = new widget.Button();
        btnDokter = new widget.Button();
        Scroll1 = new widget.ScrollPane();
        tbTarif = new widget.Table();
        jLabel15 = new widget.Label();
        btnTarif = new widget.Button();
        rbAnak = new widget.RadioButton();
        rbDewasa = new widget.RadioButton();
        NmDokterPj = new widget.TextBox();
        KodePj = new widget.TextBox();
        KodePerujuk = new widget.TextBox();
        NmPerujuk = new widget.TextBox();
        btnDokterPj = new widget.Button();
        jLabel13 = new widget.Label();
        diagnosa_dokter = new widget.TextBox();
        jLabel14 = new widget.Label();
        ketKlinis = new widget.TextBox();
        labelUnit = new widget.Label();
        unitAsal = new widget.TextBox();
        jLabel4 = new widget.Label();
        jLabel17 = new widget.Label();
        TnoTlpnDokter = new widget.TextBox();
        jLabel18 = new widget.Label();
        TnoTlpnPasien = new widget.TextBox();
        jLabel19 = new widget.Label();
        TnmFaskes = new widget.TextBox();
        jLabel20 = new widget.Label();
        TnoTlpnFaskes = new widget.TextBox();
        btnFaskes = new widget.Button();
        kdFaskes = new widget.TextBox();
        PanelCariUtama = new javax.swing.JPanel();
        PanelPermintaan = new javax.swing.JPanel();
        Scroll3 = new widget.ScrollPane();
        tbMintaPeriksa = new widget.Table();
        panelGlass12 = new widget.panelisi();
        chkPermintaan = new widget.CekBox();
        BtnSimpan1 = new widget.Button();
        BtnCari3 = new widget.Button();
        PanelPemeriksaan = new javax.swing.JPanel();
        Scroll = new widget.ScrollPane();
        tbPemeriksaan = new widget.Table();
        panelGlass11 = new widget.panelisi();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari2 = new widget.Button();

        Penjab.setEditable(false);
        Penjab.setFocusTraversalPolicyProvider(true);
        Penjab.setName("Penjab"); // NOI18N
        Penjab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PenjabKeyPressed(evt);
            }
        });

        Jk.setEditable(false);
        Jk.setFocusTraversalPolicyProvider(true);
        Jk.setName("Jk"); // NOI18N

        Umur.setEditable(false);
        Umur.setHighlighter(null);
        Umur.setName("Umur"); // NOI18N

        Alamat.setEditable(false);
        Alamat.setHighlighter(null);
        Alamat.setName("Alamat"); // NOI18N

        Popup.setName("Popup"); // NOI18N

        ppBersihkan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppBersihkan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppBersihkan.setText("Bersihkan Pilihan");
        ppBersihkan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppBersihkan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppBersihkan.setIconTextGap(8);
        ppBersihkan.setName("ppBersihkan"); // NOI18N
        ppBersihkan.setPreferredSize(new java.awt.Dimension(200, 25));
        ppBersihkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppBersihkanActionPerformed(evt);
            }
        });
        Popup.add(ppBersihkan);

        ppSemua.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppSemua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppSemua.setText("Pilih Semua");
        ppSemua.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppSemua.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppSemua.setIconTextGap(8);
        ppSemua.setName("ppSemua"); // NOI18N
        ppSemua.setPreferredSize(new java.awt.Dimension(200, 25));
        ppSemua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppSemuaActionPerformed(evt);
            }
        });
        Popup.add(ppSemua);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Input Data Hasil Periksa Laboratorium ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 5, 9));

        jLabel3.setForeground(new java.awt.Color(0, 51, 255));
        jLabel3.setText("Bridging LIS : ");
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N
        jLabel3.setPreferredSize(new java.awt.Dimension(100, 23));
        panelGlass8.add(jLabel3);

        ketAktif.setForeground(new java.awt.Color(0, 51, 255));
        ketAktif.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ketAktif.setText("-");
        ketAktif.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ketAktif.setName("ketAktif"); // NOI18N
        ketAktif.setPreferredSize(new java.awt.Dimension(250, 23));
        panelGlass8.add(ketAktif);

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

        BtnNota.setForeground(new java.awt.Color(0, 0, 0));
        BtnNota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Agenda-1-16x16.png"))); // NOI18N
        BtnNota.setMnemonic('N');
        BtnNota.setText("Nota");
        BtnNota.setToolTipText("Alt+N");
        BtnNota.setName("BtnNota"); // NOI18N
        BtnNota.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNotaActionPerformed(evt);
            }
        });
        BtnNota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnNotaKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnNota);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setPreferredSize(new java.awt.Dimension(40, 30));
        panelGlass8.add(jLabel10);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnCari.setMnemonic('C');
        BtnCari.setText("Cari");
        BtnCari.setToolTipText("Alt+C");
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(100, 30));
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

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setOpaque(false);
        FormInput.setPreferredSize(new java.awt.Dimension(560, 250));
        FormInput.setLayout(new java.awt.BorderLayout(1, 1));

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
        FormInput.add(ChkInput, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setPreferredSize(new java.awt.Dimension(560, 168));
        PanelInput.setLayout(null);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        PanelInput.add(TNoRw);
        TNoRw.setBounds(105, 12, 122, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        PanelInput.add(TNoRM);
        TNoRM.setBounds(230, 12, 70, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        PanelInput.add(TPasien);
        TPasien.setBounds(303, 12, 250, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Pemeriksaan :");
        jLabel11.setName("jLabel11"); // NOI18N
        PanelInput.add(jLabel11);
        jLabel11.setBounds(780, 42, 87, 23);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Dokter P.J. :");
        jLabel7.setName("jLabel7"); // NOI18N
        PanelInput.add(jLabel7);
        jLabel7.setBounds(0, 42, 102, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Dokter Perujuk :");
        jLabel9.setName("jLabel9"); // NOI18N
        PanelInput.add(jLabel9);
        jLabel9.setBounds(0, 72, 102, 23);

        Pemeriksaan.setForeground(new java.awt.Color(0, 0, 0));
        Pemeriksaan.setHighlighter(null);
        Pemeriksaan.setName("Pemeriksaan"); // NOI18N
        Pemeriksaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PemeriksaanKeyPressed(evt);
            }
        });
        PanelInput.add(Pemeriksaan);
        Pemeriksaan.setBounds(1045, 42, 290, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Petugas Lab :");
        jLabel12.setName("jLabel12"); // NOI18N
        PanelInput.add(jLabel12);
        jLabel12.setBounds(462, 42, 80, 23);

        btnPetugas.setForeground(new java.awt.Color(0, 0, 0));
        btnPetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPetugas.setMnemonic('2');
        btnPetugas.setToolTipText("Alt+2");
        btnPetugas.setName("btnPetugas"); // NOI18N
        btnPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPetugasActionPerformed(evt);
            }
        });
        PanelInput.add(btnPetugas);
        btnPetugas.setBounds(744, 42, 28, 23);

        NmPtg.setEditable(false);
        NmPtg.setForeground(new java.awt.Color(0, 0, 0));
        NmPtg.setName("NmPtg"); // NOI18N
        PanelInput.add(NmPtg);
        NmPtg.setBounds(547, 42, 195, 23);

        Tanggal.setBackground(new java.awt.Color(242, 242, 242));
        Tanggal.setEditable(false);
        Tanggal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "14-11-2023" }));
        Tanggal.setDisplayFormat("dd-MM-yyyy");
        Tanggal.setName("Tanggal"); // NOI18N
        Tanggal.setOpaque(false);
        Tanggal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalKeyPressed(evt);
            }
        });
        PanelInput.add(Tanggal);
        Tanggal.setBounds(873, 12, 90, 23);

        CmbJam.setForeground(new java.awt.Color(0, 0, 0));
        CmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        CmbJam.setName("CmbJam"); // NOI18N
        PanelInput.add(CmbJam);
        CmbJam.setBounds(1010, 12, 45, 23);

        CmbMenit.setForeground(new java.awt.Color(0, 0, 0));
        CmbMenit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        CmbMenit.setName("CmbMenit"); // NOI18N
        PanelInput.add(CmbMenit);
        CmbMenit.setBounds(1060, 12, 45, 23);

        CmbDetik.setForeground(new java.awt.Color(0, 0, 0));
        CmbDetik.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        CmbDetik.setName("CmbDetik"); // NOI18N
        PanelInput.add(CmbDetik);
        CmbDetik.setBounds(1110, 12, 45, 23);

        ChkJln.setBackground(new java.awt.Color(235, 255, 235));
        ChkJln.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(195, 215, 195)));
        ChkJln.setForeground(new java.awt.Color(0, 0, 0));
        ChkJln.setSelected(true);
        ChkJln.setBorderPainted(true);
        ChkJln.setBorderPaintedFlat(true);
        ChkJln.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkJln.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkJln.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkJln.setName("ChkJln"); // NOI18N
        ChkJln.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkJlnActionPerformed(evt);
            }
        });
        PanelInput.add(ChkJln);
        ChkJln.setBounds(1160, 12, 23, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Jam :");
        jLabel16.setName("jLabel16"); // NOI18N
        PanelInput.add(jLabel16);
        jLabel16.setBounds(963, 12, 40, 23);

        BtnCari1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari1.setMnemonic('1');
        BtnCari1.setToolTipText("Alt+1");
        BtnCari1.setName("BtnCari1"); // NOI18N
        BtnCari1.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari1ActionPerformed(evt);
            }
        });
        PanelInput.add(BtnCari1);
        BtnCari1.setBounds(1340, 42, 28, 23);

        btnDokter.setForeground(new java.awt.Color(0, 0, 0));
        btnDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDokter.setMnemonic('4');
        btnDokter.setToolTipText("ALt+4");
        btnDokter.setName("btnDokter"); // NOI18N
        btnDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDokterActionPerformed(evt);
            }
        });
        PanelInput.add(btnDokter);
        btnDokter.setBounds(490, 72, 28, 23);

        Scroll1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)));
        Scroll1.setName("Scroll1"); // NOI18N

        tbTarif.setToolTipText("Silahkan klik untuk memilih data yang mau dipilih");
        tbTarif.setName("tbTarif"); // NOI18N
        tbTarif.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTarifMouseClicked(evt);
            }
        });
        Scroll1.setViewportView(tbTarif);

        PanelInput.add(Scroll1);
        Scroll1.setBounds(780, 72, 650, 140);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Tgl. Periksa :");
        jLabel15.setName("jLabel15"); // NOI18N
        PanelInput.add(jLabel15);
        jLabel15.setBounds(780, 12, 87, 23);

        btnTarif.setForeground(new java.awt.Color(0, 0, 0));
        btnTarif.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        btnTarif.setMnemonic('2');
        btnTarif.setToolTipText("Alt+2");
        btnTarif.setName("btnTarif"); // NOI18N
        btnTarif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTarifActionPerformed(evt);
            }
        });
        PanelInput.add(btnTarif);
        btnTarif.setBounds(1375, 42, 28, 23);

        rbAnak.setBackground(new java.awt.Color(242, 242, 242));
        buttonGroup1.add(rbAnak);
        rbAnak.setText(" Anak-Anak");
        rbAnak.setIconTextGap(1);
        rbAnak.setName("rbAnak"); // NOI18N
        rbAnak.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbAnakMouseClicked(evt);
            }
        });
        PanelInput.add(rbAnak);
        rbAnak.setBounds(955, 42, 80, 23);

        rbDewasa.setBackground(new java.awt.Color(242, 242, 242));
        buttonGroup1.add(rbDewasa);
        rbDewasa.setSelected(true);
        rbDewasa.setText(" Dewasa");
        rbDewasa.setIconTextGap(1);
        rbDewasa.setName("rbDewasa"); // NOI18N
        rbDewasa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbDewasaMouseClicked(evt);
            }
        });
        PanelInput.add(rbDewasa);
        rbDewasa.setBounds(873, 42, 70, 23);

        NmDokterPj.setEditable(false);
        NmDokterPj.setForeground(new java.awt.Color(0, 0, 0));
        NmDokterPj.setName("NmDokterPj"); // NOI18N
        PanelInput.add(NmDokterPj);
        NmDokterPj.setBounds(189, 42, 240, 23);

        KodePj.setEditable(false);
        KodePj.setForeground(new java.awt.Color(0, 0, 0));
        KodePj.setName("KodePj"); // NOI18N
        PanelInput.add(KodePj);
        KodePj.setBounds(105, 42, 80, 23);

        KodePerujuk.setEditable(false);
        KodePerujuk.setForeground(new java.awt.Color(0, 0, 0));
        KodePerujuk.setName("KodePerujuk"); // NOI18N
        PanelInput.add(KodePerujuk);
        KodePerujuk.setBounds(105, 72, 80, 23);

        NmPerujuk.setEditable(false);
        NmPerujuk.setForeground(new java.awt.Color(0, 0, 0));
        NmPerujuk.setName("NmPerujuk"); // NOI18N
        PanelInput.add(NmPerujuk);
        NmPerujuk.setBounds(189, 72, 300, 23);

        btnDokterPj.setForeground(new java.awt.Color(0, 0, 0));
        btnDokterPj.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDokterPj.setMnemonic('4');
        btnDokterPj.setToolTipText("ALt+4");
        btnDokterPj.setName("btnDokterPj"); // NOI18N
        btnDokterPj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDokterPjActionPerformed(evt);
            }
        });
        PanelInput.add(btnDokterPj);
        btnDokterPj.setBounds(430, 42, 28, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Diagnosa :");
        jLabel13.setName("jLabel13"); // NOI18N
        PanelInput.add(jLabel13);
        jLabel13.setBounds(0, 102, 102, 23);

        diagnosa_dokter.setForeground(new java.awt.Color(0, 0, 0));
        diagnosa_dokter.setName("diagnosa_dokter"); // NOI18N
        PanelInput.add(diagnosa_dokter);
        diagnosa_dokter.setBounds(105, 102, 636, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Ket. Klinis :");
        jLabel14.setName("jLabel14"); // NOI18N
        PanelInput.add(jLabel14);
        jLabel14.setBounds(0, 132, 102, 23);

        ketKlinis.setForeground(new java.awt.Color(0, 0, 0));
        ketKlinis.setName("ketKlinis"); // NOI18N
        PanelInput.add(ketKlinis);
        ketKlinis.setBounds(105, 132, 636, 23);

        labelUnit.setForeground(new java.awt.Color(0, 0, 0));
        labelUnit.setText("Unit Asal :");
        labelUnit.setName("labelUnit"); // NOI18N
        PanelInput.add(labelUnit);
        labelUnit.setBounds(0, 162, 102, 23);

        unitAsal.setEditable(false);
        unitAsal.setForeground(new java.awt.Color(0, 0, 0));
        unitAsal.setName("unitAsal"); // NOI18N
        PanelInput.add(unitAsal);
        unitAsal.setBounds(105, 162, 636, 23);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Pasien :");
        jLabel4.setName("jLabel4"); // NOI18N
        PanelInput.add(jLabel4);
        jLabel4.setBounds(0, 12, 102, 23);

        jLabel17.setForeground(new java.awt.Color(255, 0, 51));
        jLabel17.setText("No. Tlpn. Dokter : ");
        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setName("jLabel17"); // NOI18N
        PanelInput.add(jLabel17);
        jLabel17.setBounds(520, 72, 110, 23);

        TnoTlpnDokter.setForeground(new java.awt.Color(255, 0, 51));
        TnoTlpnDokter.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        TnoTlpnDokter.setName("TnoTlpnDokter"); // NOI18N
        PanelInput.add(TnoTlpnDokter);
        TnoTlpnDokter.setBounds(632, 72, 110, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 204));
        jLabel18.setText("No. Tlpn. : ");
        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setName("jLabel18"); // NOI18N
        PanelInput.add(jLabel18);
        jLabel18.setBounds(560, 12, 70, 23);

        TnoTlpnPasien.setForeground(new java.awt.Color(0, 0, 204));
        TnoTlpnPasien.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        TnoTlpnPasien.setName("TnoTlpnPasien"); // NOI18N
        PanelInput.add(TnoTlpnPasien);
        TnoTlpnPasien.setBounds(632, 12, 110, 23);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Faskes Perujuk :");
        jLabel19.setName("jLabel19"); // NOI18N
        PanelInput.add(jLabel19);
        jLabel19.setBounds(0, 192, 102, 23);

        TnmFaskes.setEditable(false);
        TnmFaskes.setForeground(new java.awt.Color(0, 0, 0));
        TnmFaskes.setName("TnmFaskes"); // NOI18N
        PanelInput.add(TnmFaskes);
        TnmFaskes.setBounds(209, 192, 280, 23);

        jLabel20.setForeground(new java.awt.Color(153, 0, 153));
        jLabel20.setText("No. Tlpn. Perujuk : ");
        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setName("jLabel20"); // NOI18N
        PanelInput.add(jLabel20);
        jLabel20.setBounds(520, 192, 110, 23);

        TnoTlpnFaskes.setForeground(new java.awt.Color(153, 0, 153));
        TnoTlpnFaskes.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        TnoTlpnFaskes.setName("TnoTlpnFaskes"); // NOI18N
        PanelInput.add(TnoTlpnFaskes);
        TnoTlpnFaskes.setBounds(632, 192, 110, 23);

        btnFaskes.setForeground(new java.awt.Color(0, 0, 0));
        btnFaskes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnFaskes.setMnemonic('4');
        btnFaskes.setToolTipText("ALt+4");
        btnFaskes.setName("btnFaskes"); // NOI18N
        btnFaskes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFaskesActionPerformed(evt);
            }
        });
        PanelInput.add(btnFaskes);
        btnFaskes.setBounds(490, 192, 28, 23);

        kdFaskes.setEditable(false);
        kdFaskes.setForeground(new java.awt.Color(0, 0, 0));
        kdFaskes.setName("kdFaskes"); // NOI18N
        PanelInput.add(kdFaskes);
        kdFaskes.setBounds(105, 192, 100, 23);

        FormInput.add(PanelInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(FormInput, java.awt.BorderLayout.PAGE_START);

        PanelCariUtama.setName("PanelCariUtama"); // NOI18N
        PanelCariUtama.setOpaque(false);
        PanelCariUtama.setPreferredSize(new java.awt.Dimension(100, 143));
        PanelCariUtama.setLayout(new java.awt.GridLayout(1, 2));

        PanelPermintaan.setName("PanelPermintaan"); // NOI18N
        PanelPermintaan.setOpaque(false);
        PanelPermintaan.setPreferredSize(new java.awt.Dimension(100, 143));
        PanelPermintaan.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Permintaan pemeriksaan Lab. yang diminta ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll3.setComponentPopupMenu(Popup);
        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);

        tbMintaPeriksa.setToolTipText("");
        tbMintaPeriksa.setName("tbMintaPeriksa"); // NOI18N
        tbMintaPeriksa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMintaPeriksaMouseClicked(evt);
            }
        });
        Scroll3.setViewportView(tbMintaPeriksa);

        PanelPermintaan.add(Scroll3, java.awt.BorderLayout.CENTER);

        panelGlass12.setName("panelGlass12"); // NOI18N
        panelGlass12.setPreferredSize(new java.awt.Dimension(44, 43));
        panelGlass12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 9, 9));

        chkPermintaan.setForeground(new java.awt.Color(0, 0, 0));
        chkPermintaan.setText("Conteng semua item permintaan Lab.");
        chkPermintaan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkPermintaan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPermintaan.setName("chkPermintaan"); // NOI18N
        chkPermintaan.setPreferredSize(new java.awt.Dimension(220, 23));
        chkPermintaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPermintaanActionPerformed(evt);
            }
        });
        panelGlass12.add(chkPermintaan);

        BtnSimpan1.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/peminjaman.png"))); // NOI18N
        BtnSimpan1.setMnemonic('C');
        BtnSimpan1.setText("Verifikasi");
        BtnSimpan1.setToolTipText("Alt+C");
        BtnSimpan1.setName("BtnSimpan1"); // NOI18N
        BtnSimpan1.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnSimpan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan1ActionPerformed(evt);
            }
        });
        BtnSimpan1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpan1KeyPressed(evt);
            }
        });
        panelGlass12.add(BtnSimpan1);

        BtnCari3.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari3.setMnemonic('1');
        BtnCari3.setText("Lihat Permintaan");
        BtnCari3.setToolTipText("Alt+1");
        BtnCari3.setName("BtnCari3"); // NOI18N
        BtnCari3.setPreferredSize(new java.awt.Dimension(150, 23));
        BtnCari3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari3ActionPerformed(evt);
            }
        });
        panelGlass12.add(BtnCari3);

        PanelPermintaan.add(panelGlass12, java.awt.BorderLayout.PAGE_START);

        PanelCariUtama.add(PanelPermintaan);

        PanelPemeriksaan.setName("PanelPemeriksaan"); // NOI18N
        PanelPemeriksaan.setOpaque(false);
        PanelPemeriksaan.setPreferredSize(new java.awt.Dimension(100, 143));
        PanelPemeriksaan.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setComponentPopupMenu(Popup);
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbPemeriksaan.setToolTipText("Silahkan klik untuk memilih data yang mau dipilih");
        tbPemeriksaan.setComponentPopupMenu(Popup);
        tbPemeriksaan.setName("tbPemeriksaan"); // NOI18N
        Scroll.setViewportView(tbPemeriksaan);

        PanelPemeriksaan.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass11.setName("panelGlass11"); // NOI18N
        panelGlass11.setPreferredSize(new java.awt.Dimension(44, 43));
        panelGlass11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 3, 9));

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Detail Pemeriksaan :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(110, 23));
        panelGlass11.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(300, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass11.add(TCari);

        BtnCari2.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari2.setMnemonic('4');
        BtnCari2.setToolTipText("Alt+4");
        BtnCari2.setName("BtnCari2"); // NOI18N
        BtnCari2.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCari2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari2ActionPerformed(evt);
            }
        });
        BtnCari2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCari2KeyPressed(evt);
            }
        });
        panelGlass11.add(BtnCari2);

        PanelPemeriksaan.add(panelGlass11, java.awt.BorderLayout.PAGE_START);

        PanelCariUtama.add(PanelPemeriksaan);

        internalFrame1.add(PanelCariUtama, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        jml = 0;
        cekUtama = 0;
        cekDetail = 0;
        for (i = 0; i < tbTarif.getRowCount(); i++) {
            if (tbTarif.getValueAt(i, 0).toString().equals("true")) {
                jml++;
            }
        }
        for (i = 0; i < tbPemeriksaan.getRowCount(); i++) {
            if (tbPemeriksaan.getValueAt(i, 0).toString().equals("true")) {
                jml++;
            }
        }
        if (TNoRw.getText().equals("") || TNoRM.getText().equals("") || TPasien.getText().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else if (kdptg.equals("") || NmPtg.getText().equals("")) {
            Valid.textKosong(NmPtg, "Petugas");
        } else if (KodePerujuk.getText().equals("") || KodePerujuk.getText().equals("-") || KodePerujuk.getText().equals("--")) {
            Valid.textKosong(KodePerujuk, "Dokter Perujuk");
        } else if (KodePj.getText().equals("") || NmDokterPj.getText().equals("")) {
            Valid.textKosong(KodePj, "Dokter Penanggung Jawab");
        } else if (tabMode.getRowCount() == 0) {
            Valid.textKosong(Pemeriksaan, "Data Pemeriksaan");
        } else if (jml == 0) {
            Valid.textKosong(Pemeriksaan, "Data Pemeriksaan");
        } else {
            int reply = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin data akan disimpan ?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                if (tbMintaPeriksa.getRowCount() != 0) {
                    if (Sequel.cariInteger("select count(-1) from permintaan_lab_raza where no_kirim='" + noorder + "' and status_periksa='BELUM'") > 0) {
                        JOptionPane.showMessageDialog(null, "Silakan verifikasi dulu semua item permintaan pemeriksaan laboratoriumnya..!!");
                    } else {
                        simpan();
                        Sequel.mengedit("petugas", "nip='" + KodePerujuk.getText() + "'", "no_telp='" + TnoTlpnDokter.getText() + "'");
                        Sequel.mengedit("pasien", "no_rkm_medis='" + TNoRM.getText() + "'", "no_tlp='" + TnoTlpnPasien.getText() + "'");
                        
                        if (status.equals("Ranap")) {
                            Sequel.mengedit("bangsal", "nm_gedung='" + nmgedung + "'", "no_tlp='" + TnoTlpnFaskes.getText() + "'");
                        }

                        if (!kdUnit.equals(kdFaskes.getText())) {
                            Sequel.mengedit("master_nama_rujukan", "kd_rujukan='" + kdFaskes.getText() + "'", "no_tlp='" + TnoTlpnFaskes.getText() + "'");
                        }
                    }
                } else {
                    simpan();
                    Sequel.mengedit("petugas", "nip='" + KodePerujuk.getText() + "'", "no_telp='" + TnoTlpnDokter.getText() + "'");
                    Sequel.mengedit("pasien", "no_rkm_medis='" + TNoRM.getText() + "'", "no_tlp='" + TnoTlpnPasien.getText() + "'");

                    if (status.equals("Ranap")) {
                        Sequel.mengedit("bangsal", "nm_gedung='" + nmgedung + "'", "no_tlp='" + TnoTlpnFaskes.getText() + "'");
                    }

                    if (!kdUnit.equals(kdFaskes.getText())) {
                        Sequel.mengedit("master_nama_rujukan", "kd_rujukan='" + kdFaskes.getText() + "'", "no_tlp='" + TnoTlpnFaskes.getText() + "'");
                    }
                }
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, Pemeriksaan, BtnBatal);
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

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
       this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        jml=0;
        for(i=0;i<tbTarif.getRowCount();i++){
            if(tbTarif.getValueAt(i,0).toString().equals("true")){
                jml++;
            }
        }
        for(i=0;i<tbPemeriksaan.getRowCount();i++){
            if(tbPemeriksaan.getValueAt(i,0).toString().equals("true")){
                jml++;
            }
        }
        if (TNoRw.getText().equals("") || TNoRM.getText().equals("") || TPasien.getText().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else if (kdptg.equals("") || NmPtg.getText().equals("")) {
            Valid.textKosong(NmPtg, "Petugas");
        } else if (KodePerujuk.getText().equals("") || NmPerujuk.getText().equals("")) {
            Valid.textKosong(KodePerujuk, "Dokter Pengirim");
        } else if (KodePj.getText().equals("") || NmDokterPj.getText().equals("")) {
            Valid.textKosong(KodePj, "Dokter Penanggung Jawab");
        } else if (tabMode.getRowCount() == 0) {
            Valid.textKosong(Pemeriksaan, "Data Pemeriksaan");
        } else if (jml == 0) {
            Valid.textKosong(Pemeriksaan, "Data Pemeriksaan");
        } else {
            Sequel.AutoComitFalse();
            Sequel.queryu("delete from temporary");
            for(i=0;i<tbPemeriksaan.getRowCount();i++){ 
                if(tbPemeriksaan.getValueAt(i,0).toString().equals("true")){
                    Sequel.menyimpan("temporary","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?",38,new String[]{
                        "0",tbPemeriksaan.getValueAt(i,1).toString(),
                        tbPemeriksaan.getValueAt(i,2).toString(),
                        tbPemeriksaan.getValueAt(i,3).toString(),
                        tbPemeriksaan.getValueAt(i,4).toString(),
                        tbPemeriksaan.getValueAt(i,5).toString(),"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""
                    });
                }                
            }
            Sequel.AutoComitTrue();
            Map<String, Object> param = new HashMap<>();
            param.put("noperiksa",TNoRw.getText());
            param.put("norm",TNoRM.getText());
            param.put("pekerjaan",Sequel.cariIsi("select pekerjaan from pasien where no_rkm_medis=?",TNoRM.getText()));
            param.put("noktp",Sequel.cariIsi("select no_ktp from pasien where no_rkm_medis=?",TNoRM.getText()));
            param.put("namapasien",TPasien.getText());
            param.put("jkel",Jk.getText());
            param.put("umur",Umur.getText());
            param.put("lahir",Sequel.cariIsi("select DATE_FORMAT(tgl_lahir,'%d-%m-%Y') from pasien where no_rkm_medis=? ",TNoRM.getText()));
            param.put("pengirim",NmPerujuk.getText());
            param.put("tanggal", Tanggal.getSelectedItem());
            param.put("penjab", NmDokterPj.getText());
            param.put("petugas", NmPtg.getText());
            param.put("alamat", Alamat.getText());
            param.put("kamar", kamar);
            param.put("namakamar", namakamar);
            param.put("diagnosa", diagnosa_dokter.getText());
            param.put("jam", CmbJam.getSelectedItem() + ":" + CmbMenit.getSelectedItem() + ":" + CmbDetik.getSelectedItem());
            param.put("namars", akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());   
            param.put("logo",Sequel.cariGambar("select logo from setting")); 
            
            pilihan = (String)JOptionPane.showInputDialog(null,"Silahkan pilih hasil pemeriksaan..!","Hasil Pemeriksaan",JOptionPane.QUESTION_MESSAGE,null,new Object[]{"Model 1","Model 2", "Model 3", "Model 4", "Model 5", "Model 6", "Model 7", "Model 8", "Model 9", "Model 10", "Model 11"},"Model 1");
            switch (pilihan) {
                case "Model 1":
                      Valid.MyReport("rptPeriksaLab.jasper","report","::[ Pemeriksaan Laboratorium ]::",
                            "select no, temp1, temp2, temp3, temp4, temp5, temp6, temp7, temp8, temp9, temp10, temp11, temp12, temp13, temp14, temp14, temp15, temp16 from temporary order by no asc",param);            
                      break;
                case "Model 2":
                      Valid.MyReport("rptPeriksaLab2.jasper","report","::[ Pemeriksaan Laboratorium ]::",
                            "select no, temp1, temp2, temp3, temp4, temp5, temp6, temp7, temp8, temp9, temp10, temp11, temp12, temp13, temp14, temp14, temp15, temp16 from temporary order by no asc",param);            
                      break;
                case "Model 3":
                      Valid.MyReport("rptPeriksaLab3.jasper","report","::[ Pemeriksaan Laboratorium ]::",
                            "select no, temp1, temp2, temp3, temp4, temp5, temp6, temp7, temp8, temp9, temp10, temp11, temp12, temp13, temp14, temp14, temp15, temp16 from temporary order by no asc",param);            
                      break;
                case "Model 4":
                      Valid.MyReport("rptPeriksaLab4.jasper","report","::[ Pemeriksaan Laboratorium ]::",
                            "select no, temp1, temp2, temp3, temp4, temp5, temp6, temp7, temp8, temp9, temp10, temp11, temp12, temp13, temp14, temp14, temp15, temp16 from temporary order by no asc",param);            
                      break;
                case "Model 5":
                      Valid.MyReport("rptPeriksaLab5.jasper","report","::[ Pemeriksaan Laboratorium ]::",
                            "select no, temp1, temp2, temp3, temp4, temp5, temp6, temp7, temp8, temp9, temp10, temp11, temp12, temp13, temp14, temp14, temp15, temp16 from temporary order by no asc",param);            
                      break;
                case "Model 6":
                      Valid.MyReport("rptPeriksaLab6.jasper","report","::[ Pemeriksaan Laboratorium ]::",
                            "select no, temp1, temp2, temp3, temp4, temp5, temp6, temp7, temp8, temp9, temp10, temp11, temp12, temp13, temp14, temp14, temp15, temp16 from temporary order by no asc",param);            
                      break;
                case "Model 7":
                      Valid.MyReport("rptPeriksaLab7.jasper","report","::[ Pemeriksaan Laboratorium ]::",
                            "select no, temp1, temp2, temp3, temp4, temp5, temp6, temp7, temp8, temp9, temp10, temp11, temp12, temp13, temp14, temp14, temp15, temp16 from temporary order by no asc",param);            
                      break;
                case "Model 8":
                      Valid.MyReport("rptPeriksaLab8.jasper","report","::[ Pemeriksaan Laboratorium ]::",
                            "select no, temp1, temp2, temp3, temp4, temp5, temp6, temp7, temp8, temp9, temp10, temp11, temp12, temp13, temp14, temp14, temp15, temp16 from temporary order by no asc",param);            
                      break;
                case "Model 9":
                      Valid.MyReport("rptPeriksaLab9.jasper","report","::[ Pemeriksaan Laboratorium ]::",
                            "select no, temp1, temp2, temp3, temp4, temp5, temp6, temp7, temp8, temp9, temp10, temp11, temp12, temp13, temp14, temp14, temp15, temp16 from temporary order by no asc",param);            
                      break;
                case "Model 10":
                      Valid.MyReport("rptPeriksaLab10.jasper","report","::[ Pemeriksaan Laboratorium ]::",
                            "select no, temp1, temp2, temp3, temp4, temp5, temp6, temp7, temp8, temp9, temp10, temp11, temp12, temp13, temp14, temp14, temp15, temp16 from temporary order by no asc",param);            
                      break;
                case "Model 11":
                      Valid.MyReport("rptPeriksaLab11.jasper","report","::[ Pemeriksaan Laboratorium ]::",
                            "select no, temp1, temp2, temp3, temp4, temp5, temp6, temp7, temp8, temp9, temp10, temp11, temp12, temp13, temp14, temp14, temp15, temp16 from temporary order by no asc",param);            
                      break;
            }         
            
            ChkJln.setSelected(false);
        }
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnCari);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnNota,Pemeriksaan);}
}//GEN-LAST:event_BtnKeluarKeyPressed

private void PemeriksaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PemeriksaanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            tampiltarif();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            if(akses.getperiksa_lab()==true){
                btnTarifActionPerformed(null);
            }            
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnSimpan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            Tanggal.requestFocus();
        }    
}//GEN-LAST:event_PemeriksaanKeyPressed

private void btnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPetugasActionPerformed
        akses.setform("DlgPeriksaLaboratorium");
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth()-40,internalFrame1.getHeight()-40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
}//GEN-LAST:event_btnPetugasActionPerformed

private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));  
    DlgCariPeriksaLab form=new DlgCariPeriksaLab(null,false);
    form.setPasien(TNoRw.getText());   
    form.ketAktif.setText(Sequel.cariIsi("select if(aktivasi_LIS='1','TELAH DIAKTIFKAN','NON AKTIF') cek from set_pjlab limit 1"));
    form.setSize(this.getWidth(),this.getHeight());
    form.setLocationRelativeTo(this);
    form.setVisible(true);
    this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnCariActionPerformed

private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
        BtnCariActionPerformed(null);
    } else {
        Valid.pindah(evt, BtnPrint, BtnNota);
    }
}//GEN-LAST:event_BtnCariKeyPressed

private void ChkJlnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkJlnActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_ChkJlnActionPerformed

private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
    try {
        for (i = 0; i < tbPemeriksaan.getRowCount(); i++) {
            if (tbPemeriksaan.getValueAt(i, 0).toString().equals("true")) {
                tabMode.removeRow(i);
            }
        }
    } catch (Exception ex) {
    }
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
       tampiltarif();
       ketAktif.setText(Sequel.cariIsi("select if(aktivasi_LIS='1','TELAH DIAKTIFKAN','NON AKTIF') cek from set_pjlab limit 1"));
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
        isForm();
    }//GEN-LAST:event_ChkInputActionPerformed

    private void btnTarifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTarifActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgJnsPerawatanLab tariflab=new DlgJnsPerawatanLab(null,false);
        tariflab.emptTeks();
        tariflab.isCek();
        tariflab.setSize(internalFrame1.getWidth()-40,internalFrame1.getHeight()-40);
        tariflab.setLocationRelativeTo(internalFrame1);
        tariflab.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnTarifActionPerformed

    private void PenjabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PenjabKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PenjabKeyPressed

    private void tbTarifMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTarifMouseClicked
        if(tabMode2.getRowCount()!=0){
            try {
                Valid.tabelKosong(tabMode);
                tampil();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbTarifMouseClicked

    private void TanggalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalKeyPressed
        Valid.pindah(evt, KodePerujuk, Pemeriksaan);
    }//GEN-LAST:event_TanggalKeyPressed

    private void rbDewasaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbDewasaMouseClicked
        tbTarifMouseClicked(evt);
    }//GEN-LAST:event_rbDewasaMouseClicked

    private void rbAnakMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbAnakMouseClicked
        tbTarifMouseClicked(evt);
    }//GEN-LAST:event_rbAnakMouseClicked

    private void BtnNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNotaActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis...!!!!");
            Pemeriksaan.requestFocus();
        }else {
            Sequel.queryu("delete from temporary");
            ttl=0;
            for(i=0;i<tbTarif.getRowCount();i++){
                if(tbTarif.getValueAt(i,0).toString().equals("true")){                                       
                    item=Double.parseDouble(tbTarif.getValueAt(i,3).toString());
                    ttl=ttl+item;  
                    Sequel.menyimpan("temporary","'0','"+tbTarif.getValueAt(i,1).toString()+"','"+tbTarif.getValueAt(i,2).toString()+"','"+tbTarif.getValueAt(i,3).toString()+"','Pemeriksaan','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''","Transaksi Biaya Lab");
                }                
            }
            for(i=0;i<tbPemeriksaan.getRowCount();i++){
                if(tbPemeriksaan.getValueAt(i,0).toString().equals("true")){                                       
                    try {
                        item=Double.parseDouble(tbPemeriksaan.getValueAt(i,7).toString());
                    } catch (Exception e) {
                        item=0;
                    }                    
                    ttl=ttl+item;  
                    Sequel.menyimpan("temporary","'0','"+Sequel.cariIsi("select kd_jenis_prw from template_laboratorium where id_template=?",tbPemeriksaan.getValueAt(i,6).toString())+"','"+tbPemeriksaan.getValueAt(i,1).toString()+"','"+item+"','Detail Pemeriksaan','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''","Transaksi Biaya Lab");
                }                
            }
            Sequel.menyimpan("temporary","'0','','Total Biaya Pemeriksaan Lab','"+ttl+"','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''","Transaksi Biaya Lab");
            Valid.panggilUrl("billing/LaporanBiayaLab.php?norm="+TNoRM.getText()+"&pasien="+TPasien.getText().replaceAll(" ","_")+"&tanggal="+Tanggal.getSelectedItem()+"&jam="+CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem()+"&pjlab="+NmDokterPj.getText().replaceAll(" ","_")+"&petugas="+NmPtg.getText().replaceAll(" ","_")+"&kasir="+Sequel.cariIsi("select nama from pegawai where nik=?",akses.getkode()));
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnNotaActionPerformed

    private void BtnNotaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnNotaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        } else {
            Valid.pindah(evt, BtnCari, BtnKeluar);
        }
    }//GEN-LAST:event_BtnNotaKeyPressed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnPrint);
        }
    }//GEN-LAST:event_BtnHapusKeyPressed

    private void btnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDokterActionPerformed
        pilihan="perujuk";
        akses.setform("DlgPeriksaLaboratorium");
        dokter.emptTeks();
        dokter.isCek();
        dokter.setSize(internalFrame1.getWidth()-40,internalFrame1.getHeight()-40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);
    }//GEN-LAST:event_btnDokterActionPerformed

    private void btnDokterPjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDokterPjActionPerformed
        pilihan="penjab";
        akses.setform("DlgPeriksaLaboratorium");
        dokter.emptTeks();
        dokter.isCek();
        dokter.setSize(internalFrame1.getWidth()-40,internalFrame1.getHeight()-40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);
    }//GEN-LAST:event_btnDokterPjActionPerformed

    private void ppBersihkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppBersihkanActionPerformed
        for(i=0;i<tbPemeriksaan.getRowCount();i++){
            tbPemeriksaan.setValueAt(false,i,0);
        }
    }//GEN-LAST:event_ppBersihkanActionPerformed

    private void ppSemuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppSemuaActionPerformed
        for(i=0;i<tbPemeriksaan.getRowCount();i++){
            tbPemeriksaan.setValueAt(true,i,0);
        }
    }//GEN-LAST:event_ppSemuaActionPerformed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCari2ActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
        }
    }//GEN-LAST:event_TCariKeyPressed

    private void BtnCari2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari2ActionPerformed
        tampil();
        ketAktif.setText(Sequel.cariIsi("select if(aktivasi_LIS='1','TELAH DIAKTIFKAN','NON AKTIF') cek from set_pjlab limit 1"));
    }//GEN-LAST:event_BtnCari2ActionPerformed

    private void BtnCari2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari2KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCariActionPerformed(null);
        }else{
            Valid.pindah(evt, TCari,Pemeriksaan);
        }
    }//GEN-LAST:event_BtnCari2KeyPressed

    private void tbMintaPeriksaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMintaPeriksaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbMintaPeriksaMouseClicked

    private void BtnCari3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari3ActionPerformed
        chkPermintaan.setSelected(false);
        tampilMintaPeriksa(noorder);
    }//GEN-LAST:event_BtnCari3ActionPerformed

    private void chkPermintaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPermintaanActionPerformed
        for (i3 = 0; i3 < tbMintaPeriksa.getRowCount(); i3++) {
            if (chkPermintaan.isSelected() == true) {
                tbMintaPeriksa.setValueAt(Boolean.TRUE, i3, 1);
            }
        }
        
        for (i3 = 0; i3 < tbMintaPeriksa.getRowCount(); i3++) {
            if (chkPermintaan.isSelected() == false) {
                tbMintaPeriksa.setValueAt(Boolean.FALSE, i3, 1);
            }
        }
        
    }//GEN-LAST:event_chkPermintaanActionPerformed

    private void BtnSimpan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan1ActionPerformed
        cekPetugas = "";
        if (akses.getkode().equals("Admin Utama")) {
            cekPetugas = "-";
        } else {
            cekPetugas = akses.getkode();
        }
        
        try {
            for (i3 = 0; i3 < tbMintaPeriksa.getRowCount(); i3++) {
                if (tbMintaPeriksa.getValueAt(i3, 1).toString().equals("true")) {
                    if (tbMintaPeriksa.getValueAt(i3, 6).toString().equals("Diterima")) {
                        cekPeriksa = "BELUM";
                    } else {
                        cekPeriksa = "Diterima";
                    }
                    Sequel.mengedit("permintaan_lab_raza",
                            "no_rawat='" + tbMintaPeriksa.getValueAt(i3, 7).toString() + "' and "
                            + "no_kirim='" + tbMintaPeriksa.getValueAt(i3, 10).toString() + "'",
                            "status_periksa = '" + cekPeriksa + "', nip_penerima='" + cekPetugas + "'");
                }
            }
            tampilMintaPeriksa(noorder);
            chkPermintaan.setSelected(false);

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }//GEN-LAST:event_BtnSimpan1ActionPerformed

    private void BtnSimpan1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpan1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnSimpan1KeyPressed

    private void btnFaskesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFaskesActionPerformed
        akses.setform("DlgPeriksaLaboratorium");
        faskes.emptTeks();
        faskes.isCek();
        faskes.ChkInput.setSelected(false);
        faskes.tampil();
        faskes.setSize(833, internalFrame1.getHeight() - 40);
        faskes.setLocationRelativeTo(internalFrame1);
        faskes.setVisible(true);
    }//GEN-LAST:event_btnFaskesActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgPeriksaLaboratorium dialog = new DlgPeriksaLaboratorium(new javax.swing.JFrame(), true);
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
    private widget.TextBox Alamat;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnCari1;
    private widget.Button BtnCari2;
    private widget.Button BtnCari3;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnNota;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Button BtnSimpan1;
    private widget.CekBox ChkInput;
    private widget.CekBox ChkJln;
    private widget.ComboBox CmbDetik;
    private widget.ComboBox CmbJam;
    private widget.ComboBox CmbMenit;
    private javax.swing.JPanel FormInput;
    private widget.TextBox Jk;
    public widget.TextBox KodePerujuk;
    private widget.TextBox KodePj;
    private widget.TextBox NmDokterPj;
    private widget.TextBox NmPerujuk;
    private widget.TextBox NmPtg;
    private javax.swing.JPanel PanelCariUtama;
    private widget.PanelBiasa PanelInput;
    private javax.swing.JPanel PanelPemeriksaan;
    private javax.swing.JPanel PanelPermintaan;
    private widget.TextBox Pemeriksaan;
    private widget.TextBox Penjab;
    private javax.swing.JPopupMenu Popup;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll3;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.Tanggal Tanggal;
    private widget.TextBox TnmFaskes;
    private widget.TextBox TnoTlpnDokter;
    private widget.TextBox TnoTlpnFaskes;
    private widget.TextBox TnoTlpnPasien;
    private widget.TextBox Umur;
    private widget.Button btnDokter;
    private widget.Button btnDokterPj;
    private widget.Button btnFaskes;
    private widget.Button btnPetugas;
    private widget.Button btnTarif;
    private javax.swing.ButtonGroup buttonGroup1;
    private widget.CekBox chkPermintaan;
    private widget.TextBox diagnosa_dokter;
    private widget.InternalFrame internalFrame1;
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
    private widget.Label jLabel3;
    private widget.Label jLabel4;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel9;
    private widget.TextBox kdFaskes;
    public widget.Label ketAktif;
    private widget.TextBox ketKlinis;
    private widget.Label labelUnit;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass12;
    private widget.panelisi panelGlass8;
    private javax.swing.JMenuItem ppBersihkan;
    private javax.swing.JMenuItem ppSemua;
    private widget.RadioButton rbAnak;
    private widget.RadioButton rbDewasa;
    private widget.Table tbMintaPeriksa;
    private widget.Table tbPemeriksaan;
    private widget.Table tbTarif;
    private widget.TextBox unitAsal;
    // End of variables declaration//GEN-END:variables

    public void tampil() {        
        try {
            jml2=0;
            for(i2=0;i2<tbPemeriksaan.getRowCount();i2++){
                if(tbPemeriksaan.getValueAt(i2,0).toString().equals("true")){
                    jml2++;
                }
            }
            
            pilih2=null;
            pilih2=new boolean[jml2];
            pemeriksaan2=null;
            pemeriksaan2=new String[jml2];
            hasil2=null;
            hasil2=new String[jml2];
            satuan2=null;
            satuan2=new String[jml2];
            nilai_rujukan2=null;
            nilai_rujukan2=new String[jml2];
            keterangan2=null;
            keterangan2=new String[jml2];
            idtemplate2=null;
            idtemplate2=new String[jml2];
            biaya_item2=null;
            biaya_item2=new double[jml2];
            bagian_rs2=null;
            bagian_rs2=new double[jml2];
            bhp2=null;
            bhp2=new double[jml2];
            bagian_perujuk2=null;
            bagian_perujuk2=new double[jml2];
            bagian_dokter2=null;
            bagian_dokter2=new double[jml2];
            bagian_laborat2=null;
            bagian_laborat2=new double[jml2];
            kso2=null;
            kso2=new double[jml2];
            menejemen2=null;
            menejemen2=new double[jml2];
            
            index2=0; 
            for(i2=0;i2<tbPemeriksaan.getRowCount();i2++){
                if(tbPemeriksaan.getValueAt(i2,0).toString().equals("true")){
                    pilih2[index2]=true;
                    pemeriksaan2[index2]=tbPemeriksaan.getValueAt(i2,1).toString();
                    hasil2[index2]=tbPemeriksaan.getValueAt(i2,2).toString();
                    satuan2[index2]=tbPemeriksaan.getValueAt(i2,3).toString();
                    nilai_rujukan2[index2]=tbPemeriksaan.getValueAt(i2,4).toString();
                    keterangan2[index2]=tbPemeriksaan.getValueAt(i2,5).toString();
                    idtemplate2[index2]=tbPemeriksaan.getValueAt(i2,6).toString();
                    biaya_item2[index2]=Valid.SetAngka(tbPemeriksaan.getValueAt(i2,7).toString());
                    bagian_rs2[index2]=Valid.SetAngka(tbPemeriksaan.getValueAt(i2,8).toString());
                    bhp2[index2]=Valid.SetAngka(tbPemeriksaan.getValueAt(i2,9).toString());
                    bagian_perujuk2[index2]=Valid.SetAngka(tbPemeriksaan.getValueAt(i2,10).toString());
                    bagian_dokter2[index2]=Valid.SetAngka(tbPemeriksaan.getValueAt(i2,11).toString());
                    bagian_laborat2[index2]=Valid.SetAngka(tbPemeriksaan.getValueAt(i2,12).toString());
                    kso2[index2]=Valid.SetAngka(tbPemeriksaan.getValueAt(i2,13).toString());
                    menejemen2[index2]=Valid.SetAngka(tbPemeriksaan.getValueAt(i2,14).toString());                
                    index2++;
                }
            }
            
            Valid.tabelKosong(tabMode);
            
            for(i2=0;i2<jml2;i2++){ 
                tabMode.addRow(new Object[] {
                    pilih2[i2],pemeriksaan2[i2],hasil2[i2],satuan2[i2],
                    nilai_rujukan2[i2],keterangan2[i2],idtemplate2[i2],
                    biaya_item2[i2],bagian_rs2[i2],bhp2[i2],bagian_perujuk2[i2],
                    bagian_dokter2[i2],bagian_laborat2[i2],kso2[i2],menejemen2[i2]
                });
            }  
            
            for(i2=0;i2<tbTarif.getRowCount();i2++){ 
                if(tbTarif.getValueAt(i2,0).toString().equals("true")){
                    tabMode.addRow(new Object[]{false,tbTarif.getValueAt(i2,2).toString(),"","","","","",0,0,0,0,0,0,0,0});
                    if(Jk.getText().equals("L")&&(rbDewasa.isSelected()==true)){
                        pstampil = koneksi.prepareStatement("select id_template, if(keterangan in ('3 diff','5 diff'),concat(Pemeriksaan,' - ',keterangan),Pemeriksaan) Pemeriksaan, "
                                + "satuan, nilai_rujukan_ld,biaya_item, bagian_rs,bhp,bagian_perujuk,bagian_dokter,bagian_laborat,kso,menejemen "
                                + "from template_laboratorium where kd_jenis_prw=? and Pemeriksaan like ? order by urut");
                        try {
                            pstampil.setString(1,tbTarif.getValueAt(i2,1).toString());
                            pstampil.setString(2,"%"+TCari.getText().trim()+"%");
                            rstampil=pstampil.executeQuery();
                            while(rstampil.next()){
                                tabMode.addRow(new Object[]{
                                    false,"   "+rstampil.getString("Pemeriksaan"),"",
                                         rstampil.getString("satuan"),
                                         rstampil.getString("nilai_rujukan_ld"),"",
                                         rstampil.getString("id_template"),
                                         rstampil.getDouble("biaya_item"),
                                         rstampil.getDouble("bagian_rs"),
                                         rstampil.getDouble("bhp"),
                                         rstampil.getDouble("bagian_perujuk"),
                                         rstampil.getDouble("bagian_dokter"),
                                         rstampil.getDouble("bagian_laborat"),
                                         rstampil.getDouble("kso"),
                                         rstampil.getDouble("menejemen")
                                });
                            }
                        } catch (Exception e) {
                            System.out.println("Notifikasi : "+e);
                        } finally{
                            if(rstampil!=null){
                                rstampil.close();
                            }
                            if(pstampil!=null){
                                pstampil.close();
                            }
                        }                        
                    }else if(Jk.getText().equals("L")&&(rbAnak.isSelected()==true)){
                        pstampil2 = koneksi.prepareStatement("select id_template, if(keterangan in ('3 diff','5 diff'),concat(Pemeriksaan,' - ',keterangan),Pemeriksaan) Pemeriksaan, "
                                + "satuan, nilai_rujukan_la,biaya_item, bagian_rs,bhp,bagian_perujuk,bagian_dokter,bagian_laborat,kso,menejemen "
                                + "from template_laboratorium where kd_jenis_prw=? and Pemeriksaan like ? order by urut");
                        try {
                            pstampil2.setString(1,tbTarif.getValueAt(i2,1).toString());                            
                            pstampil2.setString(2,"%"+TCari.getText().trim()+"%");
                            rstampil=pstampil2.executeQuery();
                            while(rstampil.next()){
                                tabMode.addRow(new Object[]{false,"   "+rstampil.getString("Pemeriksaan"),"",
                                         rstampil.getString("satuan"),
                                         rstampil.getString("nilai_rujukan_la"),"",
                                         rstampil.getString("id_template"),
                                         rstampil.getDouble("biaya_item"),
                                         rstampil.getDouble("bagian_rs"),
                                         rstampil.getDouble("bhp"),
                                         rstampil.getDouble("bagian_perujuk"),
                                         rstampil.getDouble("bagian_dokter"),
                                         rstampil.getDouble("bagian_laborat"),
                                         rstampil.getDouble("kso"),
                                         rstampil.getDouble("menejemen")
                                });
                            }
                        } catch (Exception e) {
                            System.out.println("Notifikasi : "+e);
                        } finally{
                            if(rstampil!=null){
                                rstampil.close();
                            }
                            if(pstampil2!=null){
                                pstampil2.close();
                            }
                        }                         
                    }else if(Jk.getText().equals("P")&&(rbDewasa.isSelected()==true)){
                        pstampil3=koneksi.prepareStatement("select id_template, if(keterangan in ('3 diff','5 diff'),concat(Pemeriksaan,' - ',keterangan),Pemeriksaan) Pemeriksaan, "
                                +"satuan, nilai_rujukan_pd,biaya_item, bagian_rs,bhp,bagian_perujuk,bagian_dokter,bagian_laborat,kso,menejemen "
                                + "from template_laboratorium where kd_jenis_prw=? and Pemeriksaan like ? order by urut");
                        try {
                            pstampil3.setString(1,tbTarif.getValueAt(i2,1).toString());
                            pstampil3.setString(2,"%"+TCari.getText().trim()+"%");
                            rstampil=pstampil3.executeQuery();
                            while(rstampil.next()){
                                tabMode.addRow(new Object[]{false,"   "+rstampil.getString("Pemeriksaan"),"",
                                         rstampil.getString("satuan"),
                                         rstampil.getString("nilai_rujukan_pd"),"",
                                         rstampil.getString("id_template"),
                                         rstampil.getDouble("biaya_item"),
                                         rstampil.getDouble("bagian_rs"),
                                         rstampil.getDouble("bhp"),
                                         rstampil.getDouble("bagian_perujuk"),
                                         rstampil.getDouble("bagian_dokter"),
                                         rstampil.getDouble("bagian_laborat"),
                                         rstampil.getDouble("kso"),
                                         rstampil.getDouble("menejemen")
                                });
                            }
                        } catch (Exception e) {
                            System.out.println("Notifikasi : "+e);
                        } finally{
                            if(rstampil!=null){
                                rstampil.close();
                            }
                            if(pstampil3!=null){
                                pstampil3.close();
                            }
                        }                         
                    }else if(Jk.getText().equals("P")&&(rbAnak.isSelected()==true)){
                        pstampil4=koneksi.prepareStatement("select id_template, if(keterangan in ('3 diff','5 diff'),concat(Pemeriksaan,' - ',keterangan),Pemeriksaan) Pemeriksaan, "
                                +"satuan, nilai_rujukan_pa,biaya_item, bagian_rs,bhp,bagian_perujuk,bagian_dokter,bagian_laborat,kso,menejemen "
                                + "from template_laboratorium where kd_jenis_prw=? and Pemeriksaan like ? order by urut");
                        try{
                            pstampil4.setString(1,tbTarif.getValueAt(i2,1).toString());
                            pstampil4.setString(2,"%"+TCari.getText().trim()+"%");
                            rstampil=pstampil4.executeQuery();
                            while(rstampil.next()){
                                tabMode.addRow(new Object[]{false,"   "+rstampil.getString("Pemeriksaan"),"",
                                         rstampil.getString("satuan"),
                                         rstampil.getString("nilai_rujukan_pa"),"",
                                         rstampil.getString("id_template"),
                                         rstampil.getDouble("biaya_item"),
                                         rstampil.getDouble("bagian_rs"),
                                         rstampil.getDouble("bhp"),
                                         rstampil.getDouble("bagian_perujuk"),
                                         rstampil.getDouble("bagian_dokter"),
                                         rstampil.getDouble("bagian_laborat"),
                                         rstampil.getDouble("kso"),
                                         rstampil.getDouble("menejemen")
                                });
                            }
                        } catch (Exception e) {
                            System.out.println("Notifikasi : "+e);
                        } finally{
                            if(rstampil!=null){
                                rstampil.close();
                            }
                            if(pstampil4!=null){
                                pstampil4.close();
                            }
                        }                         
                    }                              
                }
            }
        } catch (Exception e) {
            System.out.println("Error Detail : "+e);
        }        
    }
    
    public void emptTeks() {        
        KodePerujuk.setText("");
        NmPerujuk.setText("");
        KodePj.setText("");
        NmDokterPj.setText("");
        kdptg = "";
        NmPtg.setText("");
        Pemeriksaan.setText("");
        diagnosa_dokter.setText("");
        ketKlinis.setText("-");
        unitAsal.setText("");
        TnoTlpnDokter.setText("");
        TnoTlpnPasien.setText("");
        TnoTlpnFaskes.setText("");
        Valid.tabelKosong(tabMode3);
        KodePj.requestFocus();        
        isReset();
        ketAktif.setText(Sequel.cariIsi("select if(aktivasi_LIS='1','TELAH DIAKTIFKAN','NON AKTIF') cek from set_pjlab limit 1"));
    }
    
    public void onCari(){
        KodePj.requestFocus(); 
    }

    private void isRawat() {
        Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat=? ", TNoRM, TNoRw.getText());
        Sequel.cariIsi("select kd_pj from reg_periksa where no_rawat=? ", Penjab, TNoRw.getText());
        NmPerujuk.setText(Sequel.cariIsi("select nm_dokter from dokter where kd_dokter='" + KodePerujuk.getText() + "'"));
        TnoTlpnDokter.setText(Sequel.cariIsi("select no_telp from petugas where nip='" + KodePerujuk.getText() + "'"));
        kamar = Sequel.cariIsi("select ifnull(kd_kamar,'') from kamar_inap where no_rawat=? order by tgl_masuk desc limit 1", TNoRw.getText());        
        
        if (!kamar.equals("")) {
            namakamar = kamar + ", " + Sequel.cariIsi("select nm_bangsal from bangsal inner join kamar on bangsal.kd_bangsal=kamar.kd_bangsal "
                    + " where kamar.kd_kamar=? ", kamar);
            kamar = "Kamar";
        } else if (kamar.equals("")) {
            kamar = "Poli";
            namakamar = Sequel.cariIsi("select nm_poli from poliklinik inner join reg_periksa on poliklinik.kd_poli=reg_periksa.kd_poli "
                    + "where reg_periksa.no_rawat=?", TNoRw.getText());
        }

        if (status.equals("Ranap")) {
            kelas = Sequel.cariIsi(
                    "select kamar.kelas from kamar inner join kamar_inap "
                    + "on kamar.kd_kamar=kamar_inap.kd_kamar where no_rawat=? "
                    + "and stts_pulang='-' order by STR_TO_DATE(concat(kamar_inap.tgl_masuk,' ',jam_masuk),'%Y-%m-%d %H:%i:%s') desc limit 1", TNoRw.getText());
            kdPenjab = Sequel.cariIsi("select kd_pj from reg_periksa where no_rawat='" + TNoRw.getText() + "' and status_lanjut='Ranap'");
            nm_penjab = Sequel.cariIsi("select png_jawab from penjab where kd_pj='" + kdPenjab + "'");
            
            kdUnit = Sequel.cariIsi("select b.kd_bangsal from kamar_inap ki inner join kamar k on k.kd_kamar=ki.kd_kamar "
                    + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal where ki.no_rawat='" + TNoRw.getText() + "' and ki.stts_pulang='-'");
            nm_unit = Sequel.cariIsi("select nm_bangsal from bangsal where kd_bangsal='" + kdUnit + "'");
            nmgedung = Sequel.cariIsi("select nm_gedung from bangsal where kd_bangsal='" + kdUnit + "' limit 1");
            kdFaskes.setText(kdUnit);
            TnmFaskes.setText(nm_unit);
            alamatPerujuk = "-";
            TnoTlpnFaskes.setText(Sequel.cariIsi("select no_tlp from bangsal where nm_bangsal='" + nm_unit + "' limit 1"));
            labelUnit.setText("Ruang Rawat :");
            unitAsal.setText(unitnya);
            diagnosa_dokter.setText(diagnosa_ranap);
            
        } else if (status.equals("Ralan")) {
            kelas = "Rawat Jalan";
            kdPenjab = Sequel.cariIsi("select kd_pj from reg_periksa where no_rawat='" + TNoRw.getText() + "' and status_lanjut='Ralan'");
            nm_penjab = Sequel.cariIsi("select png_jawab from penjab where kd_pj='" + kdPenjab + "'");
            kdUnit = Sequel.cariIsi("select kd_poli from reg_periksa where no_rawat='" + TNoRw.getText() + "' and status_lanjut='Ralan'");
            nm_unit = Sequel.cariIsi("select nm_poli from poliklinik where kd_poli='" + kdUnit + "'");
            kdFaskes.setText(kdUnit);
            TnmFaskes.setText(nm_unit);
            alamatPerujuk = "-";
            TnoTlpnFaskes.setText("0");
            labelUnit.setText("Poliklinik/Inst. :");
            unitAsal.setText(unitnya);
            diagnosa_dokter.setText(diagnosa_ralan);
        }
    }

    private void isPsien(){
        try {
            psp = koneksi.prepareStatement("select p.nm_pasien, p.jk, concat(rp.umurdaftar,' ',rp.sttsumur,'.') umur, "
                    + "concat(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat, p.no_tlp from pasien p "
                    + "inner join kelurahan kl on kl.kd_kel=p.kd_kel inner join kecamatan kc on kc.kd_kec=p.kd_kec "
                    + "inner join kabupaten kb on kb.kd_kab=p.kd_kab inner join reg_periksa rp on rp.no_rkm_medis=p.no_rkm_medis where "
                    + "rp.no_rkm_medis='" + TNoRM.getText() + "' GROUP BY p.no_rkm_medis");
            try {
                rsp = psp.executeQuery();
                while (rsp.next()) {
                    TPasien.setText(rsp.getString("nm_pasien"));
                    Jk.setText(rsp.getString("jk"));
                    Umur.setText(rsp.getString("umur"));
                    Alamat.setText(rsp.getString("alamat"));
                    TnoTlpnPasien.setText(rsp.getString("no_tlp"));
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsp != null) {
                    rsp.close();
                }
                if (psp != null) {
                    psp.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    public void isReset() {
        jml = tbTarif.getRowCount();
        for (i = 0; i < jml; i++) {
            tbTarif.setValueAt(false, i, 0);
        }
        Valid.tabelKosong(tabMode);
        tampil();
    }
    
    private void jam(){
        ActionListener taskPerformer = new ActionListener(){
            private int nilai_jam;
            private int nilai_menit;
            private int nilai_detik;
            @Override
            public void actionPerformed(ActionEvent e) {
                String nol_jam = "";
                String nol_menit = "";
                String nol_detik = "";
                // Membuat Date
                //Date dt = new Date();
                Date now = Calendar.getInstance().getTime();

                // Mengambil nilaj JAM, MENIT, dan DETIK Sekarang
                if(ChkJln.isSelected()==true){
                    nilai_jam = now.getHours();
                    nilai_menit = now.getMinutes();
                    nilai_detik = now.getSeconds();
                }else if(ChkJln.isSelected()==false){
                    nilai_jam =CmbJam.getSelectedIndex();
                    nilai_menit =CmbMenit.getSelectedIndex();
                    nilai_detik =CmbDetik.getSelectedIndex();
                }

                // Jika nilai JAM lebih kecil dari 10 (hanya 1 digit)
                if (nilai_jam <= 9) {
                    // Tambahkan "0" didepannya
                    nol_jam = "0";
                }
                // Jika nilai MENIT lebih kecil dari 10 (hanya 1 digit)
                if (nilai_menit <= 9) {
                    // Tambahkan "0" didepannya
                    nol_menit = "0";
                }
                // Jika nilai DETIK lebih kecil dari 10 (hanya 1 digit)
                if (nilai_detik <= 9) {
                    // Tambahkan "0" didepannya
                    nol_detik = "0";
                }
                // Membuat String JAM, MENIT, DETIK
                String jam = nol_jam + Integer.toString(nilai_jam);
                String menit = nol_menit + Integer.toString(nilai_menit);
                String detik = nol_detik + Integer.toString(nilai_detik);
                // Menampilkan pada Layar
                //tampil_jam.setText("  " + jam + " : " + menit + " : " + detik + "  ");
                CmbJam.setSelectedItem(jam);
                CmbMenit.setSelectedItem(menit);
                CmbDetik.setSelectedItem(detik);
            }
        };
        // Timer        
        new Timer(1000, taskPerformer).start();
    }

    public void setNoRm(String norwt, String posisi, String diag_ralan, String diag_ranap, String nmunit) {
        TNoRw.setText(norwt);
        diagnosa_ralan = diag_ralan;
        diagnosa_ranap = diag_ranap;
        unitnya = nmunit;
        
        this.status = posisi;
        try {
            pssetpj = koneksi.prepareStatement("select * from set_pjlab");
            try {
                rssetpj = pssetpj.executeQuery();
                while (rssetpj.next()) {
                    KodePj.setText(rssetpj.getString(1));
                    NmDokterPj.setText(Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rssetpj.getString(1)));
                }
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                if (rssetpj != null) {
                    rssetpj.close();
                }
                if (pssetpj != null) {
                    pssetpj.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        isRawat();
        isPsien();
        isReset();
    }
    
    public void isCek() {
        if (akses.getjml2() >= 1) {
            kdptg = akses.getkode();
            Sequel.cariIsi("select nama from petugas where nip=?", NmPtg, kdptg);
        } else {
            kdptg = "";
            NmPtg.setText("");
        }
        BtnSimpan.setEnabled(akses.getperiksa_lab());
        BtnPrint.setEnabled(akses.getperiksa_lab());
        BtnHapus.setEnabled(akses.getperiksa_lab());
        btnTarif.setEnabled(akses.gettarif_lab());
        ChkJln.setSelected(true);
    }
    
    private void isForm() {
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            FormInput.setPreferredSize(new Dimension(WIDTH, 250));
            PanelInput.setVisible(true);
            ChkInput.setVisible(true);
        } else if (ChkInput.isSelected() == false) {
            ChkInput.setVisible(false);
            FormInput.setPreferredSize(new Dimension(WIDTH, 20));
            PanelInput.setVisible(false);
            ChkInput.setVisible(true);
        }
    }
    
    public void tampiltarif() {          
        try{
            jml=0;
            for(i=0;i<tbTarif.getRowCount();i++){
                if(tbTarif.getValueAt(i,0).toString().equals("true")){
                    jml++;
                }
            }

            pilih=null;
            pilih=new boolean[jml];
            kode=null;
            kode=new String[jml];
            nama=null;
            nama=new String[jml];
            total=null;
            total=new double[jml];
            bagian_rs=null;
            bagian_rs=new double[jml];
            bhp=null;
            bhp=new double[jml];
            tarif_perujuk=null;
            tarif_perujuk=new double[jml];
            tarif_tindakan_dokter=null;
            tarif_tindakan_dokter=new double[jml];
            tarif_tindakan_petugas=null;
            tarif_tindakan_petugas=new double[jml];
            kso=null;
            kso=new double[jml];
            menejemen=null;
            menejemen=new double[jml];

            index=0; 
            for(i=0;i<tbTarif.getRowCount();i++){
                if(tbTarif.getValueAt(i,0).toString().equals("true")){
                    pilih[index]=true;
                    kode[index]=tbTarif.getValueAt(i,1).toString();
                    nama[index]=tbTarif.getValueAt(i,2).toString();
                    total[index]=Double.parseDouble(tbTarif.getValueAt(i,3).toString());
                    bagian_rs[index]=Double.parseDouble(tbTarif.getValueAt(i,4).toString());
                    bhp[index]=Double.parseDouble(tbTarif.getValueAt(i,5).toString());
                    tarif_perujuk[index]=Double.parseDouble(tbTarif.getValueAt(i,6).toString());
                    tarif_tindakan_dokter[index]=Double.parseDouble(tbTarif.getValueAt(i,7).toString());
                    tarif_tindakan_petugas[index]=Double.parseDouble(tbTarif.getValueAt(i,8).toString());
                    kso[index]=Double.parseDouble(tbTarif.getValueAt(i,9).toString());
                    menejemen[index]=Double.parseDouble(tbTarif.getValueAt(i,10).toString());
                    index++;
                }
            }

            Valid.tabelKosong(tabMode2);
            for(i=0;i<jml;i++){
                tabMode2.addRow(new Object[] {pilih[i],kode[i],nama[i],total[i],bagian_rs[i],bhp[i],tarif_perujuk[i],tarif_tindakan_dokter[i],tarif_tindakan_petugas[i],kso[i],menejemen[i]});
            }       
        
            pstindakan=koneksi.prepareStatement(
                    "select jns_perawatan_lab.kd_jenis_prw,jns_perawatan_lab.nm_perawatan,jns_perawatan_lab.total_byr,"+
                    "jns_perawatan_lab.bagian_rs,jns_perawatan_lab.bhp,jns_perawatan_lab.tarif_perujuk,"+
                    "jns_perawatan_lab.tarif_tindakan_dokter,jns_perawatan_lab.tarif_tindakan_petugas,"+
                    "jns_perawatan_lab.kso,jns_perawatan_lab.menejemen,penjab.png_jawab "+
                    "from jns_perawatan_lab inner join penjab on penjab.kd_pj=jns_perawatan_lab.kd_pj where "+
                    " jns_perawatan_lab.status='1' and (jns_perawatan_lab.kd_pj=? or jns_perawatan_lab.kd_pj='-') and jns_perawatan_lab.kd_jenis_prw like ? or "+
                    " jns_perawatan_lab.status='1' and (jns_perawatan_lab.kd_pj=? or jns_perawatan_lab.kd_pj='-') and jns_perawatan_lab.nm_perawatan like ? "+
                    "order by jns_perawatan_lab.kd_jenis_prw");
            pstindakan2=koneksi.prepareStatement(
                    "select jns_perawatan_lab.kd_jenis_prw,jns_perawatan_lab.nm_perawatan,jns_perawatan_lab.total_byr,"+
                    "jns_perawatan_lab.bagian_rs,jns_perawatan_lab.bhp,jns_perawatan_lab.tarif_perujuk,"+
                    "jns_perawatan_lab.tarif_tindakan_dokter,jns_perawatan_lab.tarif_tindakan_petugas,"+
                    "jns_perawatan_lab.kso,jns_perawatan_lab.menejemen,penjab.png_jawab "+
                    "from jns_perawatan_lab inner join penjab on penjab.kd_pj=jns_perawatan_lab.kd_pj where "+
                    " jns_perawatan_lab.status='1' and jns_perawatan_lab.kd_jenis_prw like ? or "+
                    " jns_perawatan_lab.status='1' and jns_perawatan_lab.nm_perawatan like ?  "+
                    "order by jns_perawatan_lab.kd_jenis_prw"); 
            pstindakan3=koneksi.prepareStatement(
                    "select jns_perawatan_lab.kd_jenis_prw,jns_perawatan_lab.nm_perawatan,jns_perawatan_lab.total_byr,"+
                    "jns_perawatan_lab.bagian_rs,jns_perawatan_lab.bhp,jns_perawatan_lab.tarif_perujuk,"+
                    "jns_perawatan_lab.tarif_tindakan_dokter,jns_perawatan_lab.tarif_tindakan_petugas,"+
                    "jns_perawatan_lab.kso,jns_perawatan_lab.menejemen,penjab.png_jawab "+
                    "from jns_perawatan_lab inner join penjab on penjab.kd_pj=jns_perawatan_lab.kd_pj where "+
                    " jns_perawatan_lab.status='1' and (jns_perawatan_lab.kd_pj=? or jns_perawatan_lab.kd_pj='-') and (jns_perawatan_lab.kelas=? or jns_perawatan_lab.kelas='-') and jns_perawatan_lab.kd_jenis_prw like ? or "+
                    " jns_perawatan_lab.status='1' and (jns_perawatan_lab.kd_pj=? or jns_perawatan_lab.kd_pj='-') and (jns_perawatan_lab.kelas=? or jns_perawatan_lab.kelas='-') and jns_perawatan_lab.nm_perawatan like ? "+
                    "order by jns_perawatan_lab.kd_jenis_prw");
            pstindakan4=koneksi.prepareStatement(
                    "select jns_perawatan_lab.kd_jenis_prw,jns_perawatan_lab.nm_perawatan,jns_perawatan_lab.total_byr,"+
                    "jns_perawatan_lab.bagian_rs,jns_perawatan_lab.bhp,jns_perawatan_lab.tarif_perujuk,"+
                    "jns_perawatan_lab.tarif_tindakan_dokter,jns_perawatan_lab.tarif_tindakan_petugas,"+
                    "jns_perawatan_lab.kso,jns_perawatan_lab.menejemen,penjab.png_jawab "+
                    "from jns_perawatan_lab inner join penjab on penjab.kd_pj=jns_perawatan_lab.kd_pj where "+
                    " jns_perawatan_lab.status='1' and (jns_perawatan_lab.kelas=? or jns_perawatan_lab.kelas='-') and jns_perawatan_lab.kd_jenis_prw like ? or "+
                    " jns_perawatan_lab.status='1' and (jns_perawatan_lab.kelas=? or jns_perawatan_lab.kelas='-') and jns_perawatan_lab.nm_perawatan like ?  "+
                    "order by jns_perawatan_lab.kd_jenis_prw"); 
            try {
                if(cara_bayar_lab.equals("Yes")&&kelas_lab.equals("No")){
                    pstindakan.setString(1,Penjab.getText().trim());
                    pstindakan.setString(2,"%"+Pemeriksaan.getText().trim()+"%");
                    pstindakan.setString(3,Penjab.getText().trim());
                    pstindakan.setString(4,"%"+Pemeriksaan.getText().trim()+"%");
                    rstindakan=pstindakan.executeQuery();
                }else if(cara_bayar_lab.equals("No")&&kelas_lab.equals("No")){
                    pstindakan2.setString(1,"%"+Pemeriksaan.getText().trim()+"%");                
                    pstindakan2.setString(2,"%"+Pemeriksaan.getText().trim()+"%");
                    rstindakan=pstindakan2.executeQuery();
                }else if(cara_bayar_lab.equals("Yes")&&kelas_lab.equals("Yes")){
                    pstindakan3.setString(1,Penjab.getText().trim());
                    pstindakan3.setString(2,kelas.trim());
                    pstindakan3.setString(3,"%"+Pemeriksaan.getText().trim()+"%");
                    pstindakan3.setString(4,Penjab.getText().trim());
                    pstindakan3.setString(5,kelas.trim());
                    pstindakan3.setString(6,"%"+Pemeriksaan.getText().trim()+"%");
                    rstindakan=pstindakan3.executeQuery();
                }else if(cara_bayar_lab.equals("No")&&kelas_lab.equals("Yes")){
                    pstindakan4.setString(1,kelas.trim());
                    pstindakan4.setString(2,"%"+Pemeriksaan.getText().trim()+"%");
                    pstindakan4.setString(3,kelas.trim());                
                    pstindakan4.setString(4,"%"+Pemeriksaan.getText().trim()+"%");
                    rstindakan=pstindakan4.executeQuery();
                } 
                            
                while(rstindakan.next()){                
                    tabMode2.addRow(new Object[]{false,rstindakan.getString(1),rstindakan.getString(2),rstindakan.getDouble(3),rstindakan.getDouble(4),rstindakan.getDouble(5),rstindakan.getDouble(6),rstindakan.getDouble(7),rstindakan.getDouble(8),rstindakan.getDouble(9),rstindakan.getDouble(10)});
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : "+e);
            } finally{
                if(rstindakan!=null){
                    rstindakan.close();
                }
                if(pstindakan!=null){
                    pstindakan.close();
                }
                if(pstindakan2!=null){
                    pstindakan2.close();
                }
                if(pstindakan3!=null){
                    pstindakan3.close();
                }
                if(pstindakan4!=null){
                    pstindakan4.close();
                }
            }
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
    }

    public void setDokterPerujuk(String kodeperujuk, String namaperujuk) {
        KodePerujuk.setText(kodeperujuk);
        NmPerujuk.setText(namaperujuk);
    }
    
    public void fokus(){
        btnPetugas.requestFocus();
    }
    
    public void setOrder(String order) {
        noorder = order;
        tampilMintaPeriksa(order);
    }
    
    public void kirimDataLIS() {
        mas_lis.kirim(TNoRw.getText(),
                Valid.SetTgl(Tanggal.getSelectedItem() + ""),
                CmbJam.getSelectedItem() + ":" + CmbMenit.getSelectedItem() + ":" + CmbDetik.getSelectedItem(),
                diagnosa_dokter.getText(), ketKlinis.getText(), KodePerujuk.getText(), kdUnit, kdPenjab,
                NmPerujuk.getText(), nm_unit, nm_penjab, TnoTlpnDokter.getText(), kdFaskes.getText(), 
                TnmFaskes.getText(), alamatPerujuk, TnoTlpnFaskes.getText()
        );
    }
    
    private void tampilMintaPeriksa(String nomorKrm) {     
        Valid.tabelKosong(tabMode3);
        try {
            if (status.equals("Ralan")) {
                psOrder = koneksi.prepareStatement("SELECT p.nm_pemeriksaan, date_format(p.tgl_permintaan,'%d-%m-%Y') tglminta, p.jam_permintaan, "
                        + "p.status_periksa, p.no_rawat, p.no_minta, UPPER(p.cito) cito, d.nm_dokter, p.no_kirim FROM permintaan_lab_raza p "
                        + "inner join dokter d on d.kd_dokter=p.dokter_perujuk where "
                        + "p.status_rawat='Ralan' and p.no_kirim ='" + nomorKrm + "' and p.no_rawat='" + TNoRw.getText() + "' "
                        + "order by p.status_periksa, p.tgl_permintaan desc, p.jam_permintaan desc");
            } else if (status.equals("Ranap")) {
                psOrder = koneksi.prepareStatement("SELECT p.nm_pemeriksaan, date_format(p.tgl_permintaan,'%d-%m-%Y') tglminta, p.jam_permintaan, "
                        + "p.status_periksa, p.no_rawat, p.no_minta, UPPER(p.cito) cito, d.nm_dokter, p.no_kirim FROM permintaan_lab_raza p "
                        + "inner join dokter d on d.kd_dokter=p.dokter_perujuk where "
                        + "p.status_rawat='Ranap' and p.no_kirim ='" + nomorKrm + "' and p.no_rawat='" + TNoRw.getText() + "' "
                        + "order by p.status_periksa, p.tgl_permintaan desc, p.jam_permintaan desc");
            }

            try {
                rsOrder = psOrder.executeQuery();
                i3 = 1;
                while (rsOrder.next()) {
                    tabMode3.addRow(new Object[]{
                        i3 + ".", false,
                        rsOrder.getString("cito"),
                        rsOrder.getString("nm_pemeriksaan"),
                        rsOrder.getString("tglminta"),
                        rsOrder.getString("jam_permintaan"),
                        rsOrder.getString("status_periksa"),
                        rsOrder.getString("no_rawat"),
                        rsOrder.getString("no_minta"),
                        rsOrder.getString("nm_dokter"),
                        rsOrder.getString("no_kirim")
                    });
                    i3++;
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println("simrskhanza.DlgPeriksaLaboratorium.tampilMintaPeriksa() : " + e);
            } finally {
                if (rsOrder != null) {
                    rsOrder.close();
                }
                if (psOrder != null) {
                    psOrder.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    private void simpan() {
        ChkJln.setSelected(false);
        for (i = 0; i < tbTarif.getRowCount(); i++) {
            if (tbTarif.getValueAt(i, 0).toString().equals("true")) {
                cekUtama++;
            }
        }

        if (cekUtama == 0) {
            JOptionPane.showMessageDialog(null, "Silakan Pilih Jenis Pemeriksaan!");
        } else {
            for (i = 0; i < tbPemeriksaan.getRowCount(); i++) {
                if ((!tbPemeriksaan.getValueAt(i, 6).toString().equals("")) && tbPemeriksaan.getValueAt(i, 0).toString().equals("true")) {
                    cekDetail++;
                }
            }
            if (cekDetail == 0) {
                JOptionPane.showMessageDialog(null, "Silakan Pilih Detail Pemeriksaan!");
            } else {
                try {
                    ttljmdokter = 0;
                    ttljmpetugas = 0;
                    ttlkso = 0;
                    ttlpendapatan = 0;
                    ttlbhp = 0;
                    koneksi.setAutoCommit(false);
                    for (i = 0; i < tbTarif.getRowCount(); i++) {
                        if (tbTarif.getValueAt(i, 0).toString().equals("true")) {
                            sukses = false;
                            pssimpanperiksa = koneksi.prepareStatement(
                                    "insert into periksa_lab values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                            try {
                                pssimpanperiksa.setString(1, TNoRw.getText());
                                pssimpanperiksa.setString(2, kdptg);
                                pssimpanperiksa.setString(3, tbTarif.getValueAt(i, 1).toString());
                                pssimpanperiksa.setString(4, Valid.SetTgl(Tanggal.getSelectedItem() + ""));
                                pssimpanperiksa.setString(5, CmbJam.getSelectedItem() + ":" + CmbMenit.getSelectedItem() + ":" + CmbDetik.getSelectedItem());
                                pssimpanperiksa.setString(6, KodePerujuk.getText());
                                pssimpanperiksa.setString(7, tbTarif.getValueAt(i, 4).toString());
                                pssimpanperiksa.setString(8, tbTarif.getValueAt(i, 5).toString());
                                pssimpanperiksa.setString(9, tbTarif.getValueAt(i, 6).toString());
                                pssimpanperiksa.setString(10, tbTarif.getValueAt(i, 7).toString());
                                pssimpanperiksa.setString(11, tbTarif.getValueAt(i, 8).toString());
                                pssimpanperiksa.setString(12, tbTarif.getValueAt(i, 9).toString());
                                pssimpanperiksa.setString(13, tbTarif.getValueAt(i, 10).toString());
                                pssimpanperiksa.setString(14, tbTarif.getValueAt(i, 3).toString());
                                pssimpanperiksa.setString(15, KodePj.getText());
                                pssimpanperiksa.setString(16, status);
                                pssimpanperiksa.setString(17, "Belum");
                                pssimpanperiksa.setString(18, "-");
                                pssimpanperiksa.executeUpdate();
                                sukses = true;
                            } catch (Exception e) {
                                sukses = false;
                                System.out.println("Notifikasi 1 : " + e);
                            } finally {
                                if (pssimpanperiksa != null) {
                                    pssimpanperiksa.close();
                                }
                            }
                            if (sukses == true) {
                                ttlbhp = ttlbhp + Double.parseDouble(tbTarif.getValueAt(i, 5).toString());
                                ttljmdokter = ttljmdokter + Double.parseDouble(tbTarif.getValueAt(i, 7).toString()) + Double.parseDouble(tbTarif.getValueAt(i, 6).toString());
                                ttljmpetugas = ttljmpetugas + Double.parseDouble(tbTarif.getValueAt(i, 8).toString());
                                ttlkso = ttlkso + Double.parseDouble(tbTarif.getValueAt(i, 9).toString());
                                ttlpendapatan = ttlpendapatan + Double.parseDouble(tbTarif.getValueAt(i, 3).toString());
                            }
                        }
                    }

                    for (i = 0; i < tbPemeriksaan.getRowCount(); i++) {
                        if ((!tbPemeriksaan.getValueAt(i, 6).toString().equals("")) && tbPemeriksaan.getValueAt(i, 0).toString().equals("true")) {
                            pscariperawatan = koneksi.prepareStatement("select kd_jenis_prw from template_laboratorium where id_template=?");
                            try {
                                pscariperawatan.setString(1, tbPemeriksaan.getValueAt(i, 6).toString());
                                rscari = pscariperawatan.executeQuery();
                                if (rscari.next()) {
                                    sukses = false;
                                    psdetailpriksa = koneksi.prepareStatement(
                                            "insert into detail_periksa_lab values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                                    try {
                                        psdetailpriksa.setString(1, TNoRw.getText());
                                        psdetailpriksa.setString(2, rscari.getString(1));
                                        psdetailpriksa.setString(3, Valid.SetTgl(Tanggal.getSelectedItem() + ""));
                                        psdetailpriksa.setString(4, CmbJam.getSelectedItem() + ":" + CmbMenit.getSelectedItem() + ":" + CmbDetik.getSelectedItem());
                                        psdetailpriksa.setString(5, tbPemeriksaan.getValueAt(i, 6).toString());
                                        psdetailpriksa.setString(6, tbPemeriksaan.getValueAt(i, 2).toString());
                                        psdetailpriksa.setString(7, tbPemeriksaan.getValueAt(i, 4).toString());
                                        psdetailpriksa.setString(8, tbPemeriksaan.getValueAt(i, 5).toString());
                                        psdetailpriksa.setString(9, tbPemeriksaan.getValueAt(i, 8).toString());
                                        psdetailpriksa.setString(10, tbPemeriksaan.getValueAt(i, 9).toString());
                                        psdetailpriksa.setString(11, tbPemeriksaan.getValueAt(i, 10).toString());
                                        psdetailpriksa.setString(12, tbPemeriksaan.getValueAt(i, 11).toString());
                                        psdetailpriksa.setString(13, tbPemeriksaan.getValueAt(i, 12).toString());
                                        psdetailpriksa.setString(14, tbPemeriksaan.getValueAt(i, 13).toString());
                                        psdetailpriksa.setString(15, tbPemeriksaan.getValueAt(i, 14).toString());
                                        psdetailpriksa.setString(16, tbPemeriksaan.getValueAt(i, 7).toString());
                                        psdetailpriksa.setString(17, "-");
                                        psdetailpriksa.executeUpdate();
                                        sukses = true;
                                    } catch (Exception e) {
                                        sukses = false;
                                        System.out.println("Notifikasi : " + e);
                                    } finally {
                                        if (psdetailpriksa != null) {
                                            psdetailpriksa.close();
                                        }
                                    }
                                    if (sukses == true) {
                                        ttlbhp = ttlbhp + Double.parseDouble(tbPemeriksaan.getValueAt(i, 9).toString());
                                        ttljmdokter = ttljmdokter + Double.parseDouble(tbPemeriksaan.getValueAt(i, 11).toString()) + Double.parseDouble(tbPemeriksaan.getValueAt(i, 10).toString());
                                        ttljmpetugas = ttljmpetugas + Double.parseDouble(tbPemeriksaan.getValueAt(i, 12).toString());
                                        ttlkso = ttlkso + Double.parseDouble(tbPemeriksaan.getValueAt(i, 13).toString());
                                        ttlpendapatan = ttlpendapatan + Double.parseDouble(tbPemeriksaan.getValueAt(i, 7).toString());
                                    }
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi 2 : " + e);
                            } finally {
                                if (rscari != null) {
                                    rscari.close();
                                }
                                if (pscariperawatan != null) {
                                    pscariperawatan.close();
                                }
                            }
                        }
                    }

                    if (status.equals("Ranap")) {
                        Sequel.queryu("delete from tampjurnal");
                        if (ttlpendapatan > 0) {
                            Sequel.menyimpan("tampjurnal", "'" + Suspen_Piutang_Laborat_Ranap + "','Suspen Piutang Laborat Ranap','" + ttlpendapatan + "','0'", "Rekening");
                            Sequel.menyimpan("tampjurnal", "'" + Laborat_Ranap + "','Pendapatan Laborat Rawat Inap','0','" + ttlpendapatan + "'", "Rekening");
                        }
                        if (ttljmdokter > 0) {
                            Sequel.menyimpan("tampjurnal", "'" + Beban_Jasa_Medik_Dokter_Laborat_Ranap + "','Beban Jasa Medik Dokter Laborat Ranap','" + ttljmdokter + "','0'", "Rekening");
                            Sequel.menyimpan("tampjurnal", "'" + Utang_Jasa_Medik_Dokter_Laborat_Ranap + "','Utang Jasa Medik Dokter Laborat Ranap','0','" + ttljmdokter + "'", "Rekening");
                        }
                        if (ttljmpetugas > 0) {
                            Sequel.menyimpan("tampjurnal", "'" + Beban_Jasa_Medik_Petugas_Laborat_Ranap + "','Beban Jasa Medik Petugas Laborat Ranap','" + ttljmpetugas + "','0'", "Rekening");
                            Sequel.menyimpan("tampjurnal", "'" + Utang_Jasa_Medik_Petugas_Laborat_Ranap + "','Utang Jasa Medik Petugas Laborat Ranap','0','" + ttljmpetugas + "'", "Rekening");
                        }
                        if (ttlbhp > 0) {
                            Sequel.menyimpan("tampjurnal", "'" + HPP_Persediaan_Laborat_Rawat_inap + "','HPP Persediaan Laborat Rawat Inap','" + ttlbhp + "','0'", "Rekening");
                            Sequel.menyimpan("tampjurnal", "'" + Persediaan_BHP_Laborat_Rawat_Inap + "','Persediaan BHP Laborat Rawat Inap','0','" + ttlbhp + "'", "Rekening");
                        }
                        if (ttlkso > 0) {
                            Sequel.menyimpan("tampjurnal", "'" + Beban_Kso_Laborat_Ranap + "','Beban KSO Laborat Ranap','" + ttlkso + "','0'", "Rekening");
                            Sequel.menyimpan("tampjurnal", "'" + Utang_Kso_Laborat_Ranap + "','Utang KSO Laborat Ranap','0','" + ttlkso + "'", "Rekening");
                        }
                        jur.simpanJurnal(TNoRw.getText(), Valid.SetTgl(Tanggal.getSelectedItem() + ""), "U", "PEMERIKSAAN LABORAT RAWAT INAP PASIEN " + TPasien.getText() + " DIPOSTING OLEH " + akses.getkode());
                    }

                    koneksi.setAutoCommit(true);
                    if (Sequel.cariIsi("select aktivasi_LIS from set_pjlab").equals("1")) {
                        kirimDataLIS();
                    }
                    System.out.println("Proses simpan selesai...!");
                    isReset();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }
}
