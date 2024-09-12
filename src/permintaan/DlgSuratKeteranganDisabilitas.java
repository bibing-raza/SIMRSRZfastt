package permintaan;

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
import simrskhanza.DlgCariDokter;
import simrskhanza.DlgMasterNomorDokumen;

/**
 *
 * @author dosen
 */
public class DlgSuratKeteranganDisabilitas extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i = 0, x = 0;
    private DlgMasterNomorDokumen dokumen = new DlgMasterNomorDokumen(null, false);
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    private String sttsnomor = "", kddokter = "", thn = "", der1 = "", der2 = "", der3 = "", der4 = "", der5 = "", der6 = "",
            fisik = "", sensorik = "", intelek = "", mental = "";
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public DlgSuratKeteranganDisabilitas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new String[]{
            "Kode Komite", "Nama Komite"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbSurat.setModel(tabMode);
        tbSurat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbSurat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 2; i++) {
            TableColumn column = tbSurat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(80);
            } else if (i == 1) {
                column.setPreferredWidth(300);
            }
        }
        tbSurat.setDefaultRenderer(Object.class, new WarnaTable());

        TTempLahr.setDocument(new batasInput((int) 150).getKata(TTempLahr));
        TekstremAtas.setDocument(new batasInput((int) 200).getKata(TekstremAtas));
        TekstremBawah.setDocument(new batasInput((int) 200).getKata(TekstremBawah));
        Talat.setDocument(new batasInput((int) 200).getKata(Talat));
        Ttahun.setDocument(new batasInput((byte) 4).getOnlyAngka(Ttahun));
        Tpenyakit.setDocument(new batasInput((int) 200).getKata(Tpenyakit));
        Tpengobatan.setDocument(new batasInput((int) 200).getKata(Tpengobatan));
        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        
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
                if (akses.getform().equals("DlgSuratKeteranganDisabilitas")) {
                    if (dokter.getTable().getSelectedRow() != -1) {
                        kddokter = dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString();
                        Tnmdokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                    }
                    BtnDokter.requestFocus();
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
        
        dokumen.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgSuratKeteranganDisabilitas")) {
                    if (dokumen.getTable().getSelectedRow() != -1) {
                        TnoDokumen.setText(dokumen.getTable().getValueAt(dokumen.getTable().getSelectedRow(), 2).toString());
                        sttsnomor = dokumen.getTable().getValueAt(dokumen.getTable().getSelectedRow(), 4).toString();
                    }
                    BtnDokumen.requestFocus();
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
        internalFrame1 = new widget.InternalFrame();
        TabSurat = new javax.swing.JTabbedPane();
        internalFrame3 = new widget.InternalFrame();
        scrollInput = new widget.ScrollPane();
        PanelInput = new javax.swing.JPanel();
        jLabel3 = new widget.Label();
        TNoRW = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel8 = new widget.Label();
        TTempLahr = new widget.TextBox();
        jLabel5 = new widget.Label();
        TtglLahir = new widget.TextBox();
        jLabel9 = new widget.Label();
        Tjk = new widget.TextBox();
        jLabel11 = new widget.Label();
        TAlamat = new widget.TextBox();
        jLabel4 = new widget.Label();
        TnoDokumen = new widget.TextBox();
        BtnDokumen = new widget.Button();
        TNoSurat = new widget.TextBox();
        jLabel10 = new widget.Label();
        Tumur = new widget.TextBox();
        jLabel12 = new widget.Label();
        ChkFisik = new widget.CekBox();
        ChkSensorik = new widget.CekBox();
        ChkIntelektual = new widget.CekBox();
        ChkMental = new widget.CekBox();
        cmbFisik = new widget.ComboBox();
        cmbSensorik = new widget.ComboBox();
        cmbIntelektual = new widget.ComboBox();
        cmbMental = new widget.ComboBox();
        cmbTanganKaki = new widget.ComboBox();
        jLabel13 = new widget.Label();
        ChkDerajat1 = new widget.CekBox();
        ChkDerajat2 = new widget.CekBox();
        ChkDerajat3 = new widget.CekBox();
        ChkDerajat4 = new widget.CekBox();
        ChkDerajat5 = new widget.CekBox();
        ChkDerajat6 = new widget.CekBox();
        jLabel14 = new widget.Label();
        cmbKemampuan1 = new widget.ComboBox();
        jLabel15 = new widget.Label();
        cmbKemampuan2 = new widget.ComboBox();
        jLabel16 = new widget.Label();
        TekstremAtas = new widget.TextBox();
        jLabel17 = new widget.Label();
        cmbKananAtas = new widget.ComboBox();
        jLabel18 = new widget.Label();
        cmbKiriAtas = new widget.ComboBox();
        jLabel19 = new widget.Label();
        TekstremBawah = new widget.TextBox();
        jLabel20 = new widget.Label();
        cmbKananBawah = new widget.ComboBox();
        jLabel21 = new widget.Label();
        cmbKiriBawah = new widget.ComboBox();
        jLabel22 = new widget.Label();
        cmbAlat = new widget.ComboBox();
        Talat = new widget.TextBox();
        jLabel23 = new widget.Label();
        cmbPenyebab = new widget.ComboBox();
        jLabel24 = new widget.Label();
        Ttahun = new widget.TextBox();
        jLabel25 = new widget.Label();
        cmbPenyakit = new widget.ComboBox();
        jLabel26 = new widget.Label();
        Tpenyakit = new widget.TextBox();
        jLabel27 = new widget.Label();
        cmbPengobatan = new widget.ComboBox();
        jLabel28 = new widget.Label();
        Tpengobatan = new widget.TextBox();
        jLabel29 = new widget.Label();
        scrollPane13 = new widget.ScrollPane();
        Tcatatan = new widget.TextArea();
        jLabel30 = new widget.Label();
        scrollPane14 = new widget.ScrollPane();
        Tkeperluan = new widget.TextArea();
        jLabel31 = new widget.Label();
        Ttgl_surat = new widget.Tanggal();
        jLabel32 = new widget.Label();
        Tnmdokter = new widget.TextBox();
        BtnDokter = new widget.Button();
        jLabel33 = new widget.Label();
        internalFrame4 = new widget.InternalFrame();
        Scroll1 = new widget.ScrollPane();
        tbSurat = new widget.Table();
        panelGlass9 = new widget.panelisi();
        jLabel46 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel48 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel49 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel50 = new widget.Label();
        LCount = new widget.Label();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnGanti = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Surat Keterangan Dokter (Disabilitas) ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        TabSurat.setBackground(new java.awt.Color(254, 255, 254));
        TabSurat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabSurat.setName("TabSurat"); // NOI18N
        TabSurat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabSuratMouseClicked(evt);
            }
        });

        internalFrame3.setBorder(null);
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        scrollInput.setName("scrollInput"); // NOI18N
        scrollInput.setPreferredSize(new java.awt.Dimension(102, 557));

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 860));
        PanelInput.setLayout(null);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Pasien : ");
        jLabel3.setName("jLabel3"); // NOI18N
        PanelInput.add(jLabel3);
        jLabel3.setBounds(0, 10, 105, 23);

        TNoRW.setEditable(false);
        TNoRW.setForeground(new java.awt.Color(0, 0, 0));
        TNoRW.setName("TNoRW"); // NOI18N
        PanelInput.add(TNoRW);
        TNoRW.setBounds(105, 10, 131, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        PanelInput.add(TNoRM);
        TNoRM.setBounds(240, 10, 70, 23);

        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setName("TPasien"); // NOI18N
        TPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPasienKeyPressed(evt);
            }
        });
        PanelInput.add(TPasien);
        TPasien.setBounds(315, 10, 360, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Tempat Lahir : ");
        jLabel8.setName("jLabel8"); // NOI18N
        PanelInput.add(jLabel8);
        jLabel8.setBounds(404, 38, 80, 23);

        TTempLahr.setForeground(new java.awt.Color(0, 0, 0));
        TTempLahr.setName("TTempLahr"); // NOI18N
        TTempLahr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTempLahrKeyPressed(evt);
            }
        });
        PanelInput.add(TTempLahr);
        TTempLahr.setBounds(484, 38, 190, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Tgl. Lahir : ");
        jLabel5.setName("jLabel5"); // NOI18N
        PanelInput.add(jLabel5);
        jLabel5.setBounds(0, 66, 105, 23);

        TtglLahir.setEditable(false);
        TtglLahir.setForeground(new java.awt.Color(0, 0, 0));
        TtglLahir.setName("TtglLahir"); // NOI18N
        PanelInput.add(TtglLahir);
        TtglLahir.setBounds(105, 66, 160, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Jenis Kelamin : ");
        jLabel9.setName("jLabel9"); // NOI18N
        PanelInput.add(jLabel9);
        jLabel9.setBounds(482, 66, 90, 23);

        Tjk.setEditable(false);
        Tjk.setForeground(new java.awt.Color(0, 0, 0));
        Tjk.setName("Tjk"); // NOI18N
        PanelInput.add(Tjk);
        Tjk.setBounds(574, 66, 100, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Alamat Domisili : ");
        jLabel11.setName("jLabel11"); // NOI18N
        PanelInput.add(jLabel11);
        jLabel11.setBounds(0, 94, 105, 23);

        TAlamat.setForeground(new java.awt.Color(0, 0, 0));
        TAlamat.setName("TAlamat"); // NOI18N
        TAlamat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TAlamatKeyPressed(evt);
            }
        });
        PanelInput.add(TAlamat);
        TAlamat.setBounds(105, 94, 568, 23);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("No. Surat : ");
        jLabel4.setName("jLabel4"); // NOI18N
        PanelInput.add(jLabel4);
        jLabel4.setBounds(0, 38, 105, 23);

        TnoDokumen.setEditable(false);
        TnoDokumen.setForeground(new java.awt.Color(0, 0, 0));
        TnoDokumen.setName("TnoDokumen"); // NOI18N
        PanelInput.add(TnoDokumen);
        TnoDokumen.setBounds(105, 38, 112, 23);

        BtnDokumen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokumen.setMnemonic('2');
        BtnDokumen.setToolTipText("Alt+2");
        BtnDokumen.setName("BtnDokumen"); // NOI18N
        BtnDokumen.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokumen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokumenActionPerformed(evt);
            }
        });
        PanelInput.add(BtnDokumen);
        BtnDokumen.setBounds(220, 38, 28, 23);

        TNoSurat.setEditable(false);
        TNoSurat.setForeground(new java.awt.Color(0, 0, 0));
        TNoSurat.setName("TNoSurat"); // NOI18N
        PanelInput.add(TNoSurat);
        TNoSurat.setBounds(250, 38, 150, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Umur :");
        jLabel10.setName("jLabel10"); // NOI18N
        PanelInput.add(jLabel10);
        jLabel10.setBounds(267, 66, 50, 23);

        Tumur.setEditable(false);
        Tumur.setForeground(new java.awt.Color(0, 0, 0));
        Tumur.setName("Tumur"); // NOI18N
        PanelInput.add(Tumur);
        Tumur.setBounds(320, 66, 90, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Jenis / Ragam Disabilitas :");
        jLabel12.setName("jLabel12"); // NOI18N
        PanelInput.add(jLabel12);
        jLabel12.setBounds(0, 122, 180, 23);

        ChkFisik.setBackground(new java.awt.Color(255, 255, 250));
        ChkFisik.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkFisik.setForeground(new java.awt.Color(0, 0, 0));
        ChkFisik.setText("Disabilitas Fisik");
        ChkFisik.setBorderPainted(true);
        ChkFisik.setBorderPaintedFlat(true);
        ChkFisik.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkFisik.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkFisik.setName("ChkFisik"); // NOI18N
        ChkFisik.setOpaque(false);
        ChkFisik.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkFisik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkFisikActionPerformed(evt);
            }
        });
        PanelInput.add(ChkFisik);
        ChkFisik.setBounds(185, 122, 130, 23);

        ChkSensorik.setBackground(new java.awt.Color(255, 255, 250));
        ChkSensorik.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkSensorik.setForeground(new java.awt.Color(0, 0, 0));
        ChkSensorik.setText("Disabilitas Sensorik");
        ChkSensorik.setBorderPainted(true);
        ChkSensorik.setBorderPaintedFlat(true);
        ChkSensorik.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkSensorik.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkSensorik.setName("ChkSensorik"); // NOI18N
        ChkSensorik.setOpaque(false);
        ChkSensorik.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkSensorik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkSensorikActionPerformed(evt);
            }
        });
        PanelInput.add(ChkSensorik);
        ChkSensorik.setBounds(185, 150, 130, 23);

        ChkIntelektual.setBackground(new java.awt.Color(255, 255, 250));
        ChkIntelektual.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkIntelektual.setForeground(new java.awt.Color(0, 0, 0));
        ChkIntelektual.setText("Disabilitas Intelektual");
        ChkIntelektual.setBorderPainted(true);
        ChkIntelektual.setBorderPaintedFlat(true);
        ChkIntelektual.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkIntelektual.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkIntelektual.setName("ChkIntelektual"); // NOI18N
        ChkIntelektual.setOpaque(false);
        ChkIntelektual.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkIntelektual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkIntelektualActionPerformed(evt);
            }
        });
        PanelInput.add(ChkIntelektual);
        ChkIntelektual.setBounds(185, 178, 130, 23);

        ChkMental.setBackground(new java.awt.Color(255, 255, 250));
        ChkMental.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkMental.setForeground(new java.awt.Color(0, 0, 0));
        ChkMental.setText("Disabilitas Mental");
        ChkMental.setBorderPainted(true);
        ChkMental.setBorderPaintedFlat(true);
        ChkMental.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkMental.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkMental.setName("ChkMental"); // NOI18N
        ChkMental.setOpaque(false);
        ChkMental.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkMental.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkMentalActionPerformed(evt);
            }
        });
        PanelInput.add(ChkMental);
        ChkMental.setBounds(185, 206, 130, 23);

        cmbFisik.setForeground(new java.awt.Color(0, 0, 0));
        cmbFisik.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Amputasi", "Kelemahan atau kekakuan anggota gerak atas dan bawah", "Paraplegi (anggota tubuh bagian bawah yang meliputi kedua tungkai dan organ panggul)", "Celebral Palsy (CP)", "CTEV (Congenital Talipes Equinovarus)" }));
        cmbFisik.setName("cmbFisik"); // NOI18N
        cmbFisik.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbFisik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFisikActionPerformed(evt);
            }
        });
        PanelInput.add(cmbFisik);
        cmbFisik.setBounds(322, 122, 460, 23);

        cmbSensorik.setForeground(new java.awt.Color(0, 0, 0));
        cmbSensorik.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Netral (Buta Total)", "Netral (Persepsi Cahaya / Low Vision)", "Rungu", "Wicara" }));
        cmbSensorik.setName("cmbSensorik"); // NOI18N
        cmbSensorik.setPreferredSize(new java.awt.Dimension(55, 28));
        PanelInput.add(cmbSensorik);
        cmbSensorik.setBounds(322, 150, 215, 23);

        cmbIntelektual.setForeground(new java.awt.Color(0, 0, 0));
        cmbIntelektual.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Disabilitas Grahita", "Disabilitas Syndrome" }));
        cmbIntelektual.setName("cmbIntelektual"); // NOI18N
        cmbIntelektual.setPreferredSize(new java.awt.Dimension(55, 28));
        PanelInput.add(cmbIntelektual);
        cmbIntelektual.setBounds(322, 178, 132, 23);

        cmbMental.setForeground(new java.awt.Color(0, 0, 0));
        cmbMental.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Psikososial (Skizofrenia)", "Psikososial (Bipolar)", "Psikososial (Depresi)", "Psikososial (Anxietas)", "Psikososial (Gangguan Kepribadian)", "Disabilitas (Autis)", "Disabilitas (Hiperaktif)" }));
        cmbMental.setName("cmbMental"); // NOI18N
        cmbMental.setPreferredSize(new java.awt.Dimension(55, 28));
        PanelInput.add(cmbMental);
        cmbMental.setBounds(322, 206, 205, 23);

        cmbTanganKaki.setForeground(new java.awt.Color(0, 0, 0));
        cmbTanganKaki.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tangan Kanan", "Tangan Kiri", "Kaki Kanan", "Kaki Kiri" }));
        cmbTanganKaki.setName("cmbTanganKaki"); // NOI18N
        cmbTanganKaki.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbTanganKaki.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTanganKakiActionPerformed(evt);
            }
        });
        PanelInput.add(cmbTanganKaki);
        cmbTanganKaki.setBounds(788, 122, 102, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Derajat Disabilitas :");
        jLabel13.setName("jLabel13"); // NOI18N
        PanelInput.add(jLabel13);
        jLabel13.setBounds(0, 234, 180, 23);

        ChkDerajat1.setBackground(new java.awt.Color(255, 255, 250));
        ChkDerajat1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup1.add(ChkDerajat1);
        ChkDerajat1.setForeground(new java.awt.Color(0, 0, 0));
        ChkDerajat1.setText("Derajat 1 : Mampu melaksanakan aktivitas atau mempertahankan sikap dengan kesulitan");
        ChkDerajat1.setBorderPainted(true);
        ChkDerajat1.setBorderPaintedFlat(true);
        ChkDerajat1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkDerajat1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkDerajat1.setName("ChkDerajat1"); // NOI18N
        ChkDerajat1.setOpaque(false);
        ChkDerajat1.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput.add(ChkDerajat1);
        ChkDerajat1.setBounds(185, 234, 590, 23);

        ChkDerajat2.setBackground(new java.awt.Color(255, 255, 250));
        ChkDerajat2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup1.add(ChkDerajat2);
        ChkDerajat2.setForeground(new java.awt.Color(0, 0, 0));
        ChkDerajat2.setText("Derajat 2 : Mampu melaksanakan kegiatan atau mempertahankan sikap dengan bantuan alat bantu");
        ChkDerajat2.setBorderPainted(true);
        ChkDerajat2.setBorderPaintedFlat(true);
        ChkDerajat2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkDerajat2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkDerajat2.setName("ChkDerajat2"); // NOI18N
        ChkDerajat2.setOpaque(false);
        ChkDerajat2.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput.add(ChkDerajat2);
        ChkDerajat2.setBounds(185, 262, 590, 23);

        ChkDerajat3.setBackground(new java.awt.Color(255, 255, 250));
        ChkDerajat3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup1.add(ChkDerajat3);
        ChkDerajat3.setForeground(new java.awt.Color(0, 0, 0));
        ChkDerajat3.setText("Derajat 3 : Mampu melaksanakan aktivitas, sebagian memerlukan bantuan orang lain, dengan atau tanpa alat bantu");
        ChkDerajat3.setBorderPainted(true);
        ChkDerajat3.setBorderPaintedFlat(true);
        ChkDerajat3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkDerajat3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkDerajat3.setName("ChkDerajat3"); // NOI18N
        ChkDerajat3.setOpaque(false);
        ChkDerajat3.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput.add(ChkDerajat3);
        ChkDerajat3.setBounds(185, 290, 590, 23);

        ChkDerajat4.setBackground(new java.awt.Color(255, 255, 250));
        ChkDerajat4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup1.add(ChkDerajat4);
        ChkDerajat4.setForeground(new java.awt.Color(0, 0, 0));
        ChkDerajat4.setText("Derajat 4 : Dalam melaksanakan aktivitas, tergantung penuh terhadap pengawasan orang lain");
        ChkDerajat4.setBorderPainted(true);
        ChkDerajat4.setBorderPaintedFlat(true);
        ChkDerajat4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkDerajat4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkDerajat4.setName("ChkDerajat4"); // NOI18N
        ChkDerajat4.setOpaque(false);
        ChkDerajat4.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput.add(ChkDerajat4);
        ChkDerajat4.setBounds(185, 318, 590, 23);

        ChkDerajat5.setBackground(new java.awt.Color(255, 255, 250));
        ChkDerajat5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup1.add(ChkDerajat5);
        ChkDerajat5.setForeground(new java.awt.Color(0, 0, 0));
        ChkDerajat5.setText("Derajat 5 : Tidak mampu melakukan aktivitas tanpa bantuan penuh orang lain dan tersedianya lingkungan khusus");
        ChkDerajat5.setBorderPainted(true);
        ChkDerajat5.setBorderPaintedFlat(true);
        ChkDerajat5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkDerajat5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkDerajat5.setName("ChkDerajat5"); // NOI18N
        ChkDerajat5.setOpaque(false);
        ChkDerajat5.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput.add(ChkDerajat5);
        ChkDerajat5.setBounds(185, 346, 590, 23);

        ChkDerajat6.setBackground(new java.awt.Color(255, 255, 250));
        ChkDerajat6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup1.add(ChkDerajat6);
        ChkDerajat6.setForeground(new java.awt.Color(0, 0, 0));
        ChkDerajat6.setText("Derajat 6 : Tidak mampu penuh melaksanakan kegiatan sehari-hari meskipun dibantu orang lain");
        ChkDerajat6.setBorderPainted(true);
        ChkDerajat6.setBorderPaintedFlat(true);
        ChkDerajat6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkDerajat6.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkDerajat6.setName("ChkDerajat6"); // NOI18N
        ChkDerajat6.setOpaque(false);
        ChkDerajat6.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput.add(ChkDerajat6);
        ChkDerajat6.setBounds(185, 374, 590, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Kemampuan Mobilitas 1.) :");
        jLabel14.setName("jLabel14"); // NOI18N
        PanelInput.add(jLabel14);
        jLabel14.setBounds(0, 402, 180, 23);

        cmbKemampuan1.setForeground(new java.awt.Color(0, 0, 0));
        cmbKemampuan1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Jalan", "Jalan perlahan", "Jalan dengan alat bantu", "Tidak mampu jalan" }));
        cmbKemampuan1.setName("cmbKemampuan1"); // NOI18N
        cmbKemampuan1.setPreferredSize(new java.awt.Dimension(55, 28));
        PanelInput.add(cmbKemampuan1);
        cmbKemampuan1.setBounds(185, 402, 150, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("2.) : ");
        jLabel15.setName("jLabel15"); // NOI18N
        PanelInput.add(jLabel15);
        jLabel15.setBounds(335, 402, 40, 23);

        cmbKemampuan2.setForeground(new java.awt.Color(0, 0, 0));
        cmbKemampuan2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Naik tangga", "Tidak naik tangga perlahan", "Tidak mampu naik tangga" }));
        cmbKemampuan2.setName("cmbKemampuan2"); // NOI18N
        cmbKemampuan2.setPreferredSize(new java.awt.Dimension(55, 28));
        PanelInput.add(cmbKemampuan2);
        cmbKemampuan2.setBounds(377, 402, 163, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Gangguan Ekstremitas Atas :");
        jLabel16.setName("jLabel16"); // NOI18N
        PanelInput.add(jLabel16);
        jLabel16.setBounds(0, 430, 180, 23);

        TekstremAtas.setForeground(new java.awt.Color(0, 0, 0));
        TekstremAtas.setName("TekstremAtas"); // NOI18N
        TekstremAtas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TekstremAtasKeyPressed(evt);
            }
        });
        PanelInput.add(TekstremAtas);
        TekstremAtas.setBounds(185, 430, 568, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("1. Kanan :");
        jLabel17.setName("jLabel17"); // NOI18N
        PanelInput.add(jLabel17);
        jLabel17.setBounds(0, 458, 180, 23);

        cmbKananAtas.setForeground(new java.awt.Color(0, 0, 0));
        cmbKananAtas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Kekuatan 0", "Kekuatan 1", "Kekuatan 2", "Kekuatan 3", "Kekuatan 4", "Kekuatan 5" }));
        cmbKananAtas.setName("cmbKananAtas"); // NOI18N
        cmbKananAtas.setPreferredSize(new java.awt.Dimension(55, 28));
        PanelInput.add(cmbKananAtas);
        cmbKananAtas.setBounds(185, 458, 87, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("2. Kiri : ");
        jLabel18.setName("jLabel18"); // NOI18N
        PanelInput.add(jLabel18);
        jLabel18.setBounds(273, 458, 55, 23);

        cmbKiriAtas.setForeground(new java.awt.Color(0, 0, 0));
        cmbKiriAtas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Kekuatan 0", "Kekuatan 1", "Kekuatan 2", "Kekuatan 3", "Kekuatan 4", "Kekuatan 5" }));
        cmbKiriAtas.setName("cmbKiriAtas"); // NOI18N
        cmbKiriAtas.setPreferredSize(new java.awt.Dimension(55, 28));
        PanelInput.add(cmbKiriAtas);
        cmbKiriAtas.setBounds(330, 458, 87, 23);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Gangguan Ekstremitas Bawah :");
        jLabel19.setName("jLabel19"); // NOI18N
        PanelInput.add(jLabel19);
        jLabel19.setBounds(0, 486, 180, 23);

        TekstremBawah.setForeground(new java.awt.Color(0, 0, 0));
        TekstremBawah.setName("TekstremBawah"); // NOI18N
        TekstremBawah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TekstremBawahKeyPressed(evt);
            }
        });
        PanelInput.add(TekstremBawah);
        TekstremBawah.setBounds(185, 486, 568, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("1. Kanan :");
        jLabel20.setName("jLabel20"); // NOI18N
        PanelInput.add(jLabel20);
        jLabel20.setBounds(0, 514, 180, 23);

        cmbKananBawah.setForeground(new java.awt.Color(0, 0, 0));
        cmbKananBawah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Kekuatan 0", "Kekuatan 1", "Kekuatan 2", "Kekuatan 3", "Kekuatan 4", "Kekuatan 5" }));
        cmbKananBawah.setName("cmbKananBawah"); // NOI18N
        cmbKananBawah.setPreferredSize(new java.awt.Dimension(55, 28));
        PanelInput.add(cmbKananBawah);
        cmbKananBawah.setBounds(185, 514, 87, 23);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("2. Kiri : ");
        jLabel21.setName("jLabel21"); // NOI18N
        PanelInput.add(jLabel21);
        jLabel21.setBounds(273, 514, 55, 23);

        cmbKiriBawah.setForeground(new java.awt.Color(0, 0, 0));
        cmbKiriBawah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Kekuatan 0", "Kekuatan 1", "Kekuatan 2", "Kekuatan 3", "Kekuatan 4", "Kekuatan 5" }));
        cmbKiriBawah.setName("cmbKiriBawah"); // NOI18N
        cmbKiriBawah.setPreferredSize(new java.awt.Dimension(55, 28));
        PanelInput.add(cmbKiriBawah);
        cmbKiriBawah.setBounds(330, 514, 87, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Alat Bantu Yang Digunakan :");
        jLabel22.setName("jLabel22"); // NOI18N
        PanelInput.add(jLabel22);
        jLabel22.setBounds(0, 542, 180, 23);

        cmbAlat.setForeground(new java.awt.Color(0, 0, 0));
        cmbAlat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ada" }));
        cmbAlat.setName("cmbAlat"); // NOI18N
        cmbAlat.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbAlat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAlatActionPerformed(evt);
            }
        });
        PanelInput.add(cmbAlat);
        cmbAlat.setBounds(185, 542, 60, 23);

        Talat.setForeground(new java.awt.Color(0, 0, 0));
        Talat.setName("Talat"); // NOI18N
        Talat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TalatKeyPressed(evt);
            }
        });
        PanelInput.add(Talat);
        Talat.setBounds(323, 542, 430, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Penyebab :");
        jLabel23.setName("jLabel23"); // NOI18N
        PanelInput.add(jLabel23);
        jLabel23.setBounds(0, 570, 180, 23);

        cmbPenyebab.setForeground(new java.awt.Color(0, 0, 0));
        cmbPenyebab.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Sejak lahir", "Kecelakaan dalam pekerjaan", "Kecelakaan lalu lintas", "Penyakit", "Akibat stroke", "Akibat kusta", "Lain - lain" }));
        cmbPenyebab.setName("cmbPenyebab"); // NOI18N
        cmbPenyebab.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbPenyebab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPenyebabActionPerformed(evt);
            }
        });
        PanelInput.add(cmbPenyebab);
        cmbPenyebab.setBounds(185, 570, 170, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Tahun : ");
        jLabel24.setName("jLabel24"); // NOI18N
        PanelInput.add(jLabel24);
        jLabel24.setBounds(356, 570, 55, 23);

        Ttahun.setForeground(new java.awt.Color(0, 0, 0));
        Ttahun.setName("Ttahun"); // NOI18N
        Ttahun.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtahunKeyPressed(evt);
            }
        });
        PanelInput.add(Ttahun);
        Ttahun.setBounds(413, 570, 60, 23);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Penyakit Lain :");
        jLabel25.setName("jLabel25"); // NOI18N
        PanelInput.add(jLabel25);
        jLabel25.setBounds(0, 598, 180, 23);

        cmbPenyakit.setForeground(new java.awt.Color(0, 0, 0));
        cmbPenyakit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ada" }));
        cmbPenyakit.setName("cmbPenyakit"); // NOI18N
        cmbPenyakit.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbPenyakit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPenyakitActionPerformed(evt);
            }
        });
        PanelInput.add(cmbPenyakit);
        cmbPenyakit.setBounds(185, 598, 60, 23);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Sebutkan :");
        jLabel26.setName("jLabel26"); // NOI18N
        PanelInput.add(jLabel26);
        jLabel26.setBounds(247, 598, 70, 23);

        Tpenyakit.setForeground(new java.awt.Color(0, 0, 0));
        Tpenyakit.setName("Tpenyakit"); // NOI18N
        Tpenyakit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpenyakitKeyPressed(evt);
            }
        });
        PanelInput.add(Tpenyakit);
        Tpenyakit.setBounds(323, 598, 430, 23);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("Pengobatan :");
        jLabel27.setName("jLabel27"); // NOI18N
        PanelInput.add(jLabel27);
        jLabel27.setBounds(0, 626, 180, 23);

        cmbPengobatan.setForeground(new java.awt.Color(0, 0, 0));
        cmbPengobatan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ada" }));
        cmbPengobatan.setName("cmbPengobatan"); // NOI18N
        cmbPengobatan.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbPengobatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPengobatanActionPerformed(evt);
            }
        });
        PanelInput.add(cmbPengobatan);
        cmbPengobatan.setBounds(185, 626, 60, 23);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Sebutkan :");
        jLabel28.setName("jLabel28"); // NOI18N
        PanelInput.add(jLabel28);
        jLabel28.setBounds(247, 626, 70, 23);

        Tpengobatan.setForeground(new java.awt.Color(0, 0, 0));
        Tpengobatan.setName("Tpengobatan"); // NOI18N
        Tpengobatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpengobatanKeyPressed(evt);
            }
        });
        PanelInput.add(Tpengobatan);
        Tpengobatan.setBounds(323, 626, 430, 23);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("Catatan Tambahan Lainnya :");
        jLabel29.setName("jLabel29"); // NOI18N
        PanelInput.add(jLabel29);
        jLabel29.setBounds(0, 654, 180, 23);

        scrollPane13.setName("scrollPane13"); // NOI18N

        Tcatatan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tcatatan.setColumns(20);
        Tcatatan.setRows(5);
        Tcatatan.setName("Tcatatan"); // NOI18N
        Tcatatan.setPreferredSize(new java.awt.Dimension(162, 1000));
        Tcatatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TcatatanKeyPressed(evt);
            }
        });
        scrollPane13.setViewportView(Tcatatan);

        PanelInput.add(scrollPane13);
        scrollPane13.setBounds(185, 654, 568, 80);

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Untuk Keperluan :");
        jLabel30.setName("jLabel30"); // NOI18N
        PanelInput.add(jLabel30);
        jLabel30.setBounds(0, 740, 180, 23);

        scrollPane14.setName("scrollPane14"); // NOI18N

        Tkeperluan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tkeperluan.setColumns(20);
        Tkeperluan.setRows(5);
        Tkeperluan.setName("Tkeperluan"); // NOI18N
        Tkeperluan.setPreferredSize(new java.awt.Dimension(162, 1000));
        Tkeperluan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkeperluanKeyPressed(evt);
            }
        });
        scrollPane14.setViewportView(Tkeperluan);

        PanelInput.add(scrollPane14);
        scrollPane14.setBounds(185, 740, 568, 80);

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("Tgl. Surat : ");
        jLabel31.setName("jLabel31"); // NOI18N
        PanelInput.add(jLabel31);
        jLabel31.setBounds(675, 10, 70, 23);

        Ttgl_surat.setEditable(false);
        Ttgl_surat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12-09-2024" }));
        Ttgl_surat.setDisplayFormat("dd-MM-yyyy");
        Ttgl_surat.setName("Ttgl_surat"); // NOI18N
        Ttgl_surat.setOpaque(false);
        PanelInput.add(Ttgl_surat);
        Ttgl_surat.setBounds(747, 10, 90, 23);

        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Dokter Pemeriksa :");
        jLabel32.setName("jLabel32"); // NOI18N
        PanelInput.add(jLabel32);
        jLabel32.setBounds(0, 825, 180, 23);

        Tnmdokter.setEditable(false);
        Tnmdokter.setForeground(new java.awt.Color(0, 0, 0));
        Tnmdokter.setName("Tnmdokter"); // NOI18N
        PanelInput.add(Tnmdokter);
        Tnmdokter.setBounds(185, 825, 470, 23);

        BtnDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter.setMnemonic('2');
        BtnDokter.setToolTipText("Alt+2");
        BtnDokter.setName("BtnDokter"); // NOI18N
        BtnDokter.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokterActionPerformed(evt);
            }
        });
        PanelInput.add(BtnDokter);
        BtnDokter.setBounds(660, 825, 28, 23);

        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("Sebutkan :");
        jLabel33.setName("jLabel33"); // NOI18N
        PanelInput.add(jLabel33);
        jLabel33.setBounds(247, 542, 70, 23);

        scrollInput.setViewportView(PanelInput);

        internalFrame3.add(scrollInput, java.awt.BorderLayout.CENTER);

        TabSurat.addTab("Input Surat", internalFrame3);

        internalFrame4.setBorder(null);
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);
        Scroll1.setPreferredSize(new java.awt.Dimension(452, 200));

        tbSurat.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbSurat.setName("tbSurat"); // NOI18N
        tbSurat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSuratMouseClicked(evt);
            }
        });
        tbSurat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbSuratKeyPressed(evt);
            }
        });
        Scroll1.setViewportView(tbSurat);

        internalFrame4.add(Scroll1, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel46.setForeground(new java.awt.Color(0, 0, 0));
        jLabel46.setText("Tgl. Surat :");
        jLabel46.setName("jLabel46"); // NOI18N
        jLabel46.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel46);

        DTPCari1.setEditable(false);
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12-09-2024" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari1);

        jLabel48.setForeground(new java.awt.Color(0, 0, 0));
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel48.setText("s.d.");
        jLabel48.setName("jLabel48"); // NOI18N
        jLabel48.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass9.add(jLabel48);

        DTPCari2.setEditable(false);
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12-09-2024" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari2);

        jLabel49.setForeground(new java.awt.Color(0, 0, 0));
        jLabel49.setText("Key Word :");
        jLabel49.setName("jLabel49"); // NOI18N
        jLabel49.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass9.add(jLabel49);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(195, 23));
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

        jLabel50.setForeground(new java.awt.Color(0, 0, 0));
        jLabel50.setText("Record :");
        jLabel50.setName("jLabel50"); // NOI18N
        jLabel50.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass9.add(jLabel50);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(LCount);

        internalFrame4.add(panelGlass9, java.awt.BorderLayout.PAGE_END);

        TabSurat.addTab("Data Surat", internalFrame4);

        internalFrame1.add(TabSurat, java.awt.BorderLayout.CENTER);

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
        BtnHapus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnHapusKeyPressed(evt);
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
        if (TNoRW.getText().trim().equals("")) {
            Valid.textKosong(TNoRW, "Pasien");
        } else if (kddokter.equals("") || kddokter.equals("-") || kddokter.equals("--")) {
            Valid.textKosong(Tnmdokter, "Dokter Pemeriksa");
            BtnDokter.requestFocus();
        } else if (TnoDokumen.getText().equals("")) {
            Valid.textKosong(TnoDokumen, "Nomor Dokumen");
            BtnDokumen.requestFocus();
        } else if (sttsnomor.equals("Non Aktif")) {
            JOptionPane.showMessageDialog(null, "Nomor dokumen yang dipilih statusnya " + sttsnomor + ", silahkan ulangi lagi...!!");
            BtnDokumen.requestFocus();
        } else {
            cekData();
            autoNomorSurat();
            if (Sequel.menyimpantf("surat_keterangan_dokter", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 27, new String[]{
                TNoRW.getText(), TNoSurat.getText(), Valid.SetTgl(Ttgl_surat.getSelectedItem() + ""), "", TAlamat.getText(), "", "", "tidak",
                Sequel.cariIsi("select date(now())"), "", Tkeperluan.getText(), "0", "hari", "", "", "", kddokter, TTempLahr.getText(), "", "",
                TPasien.getText(), TnoDokumen.getText(), "tidak", "", "", "", "ya"
            }) == true) {
                Sequel.menyimpan("surat_keterangan_dokter_disabilitas", "'" + TNoRW.getText() + "','" + TNoSurat.getText() + "',"
                        + "'" + fisik + "','" + sensorik + "','" + intelek + "','" + mental + "','" + cmbFisik.getSelectedItem().toString() + "',"
                        + "'" + cmbTanganKaki.getSelectedItem().toString() + "','" + cmbSensorik.getSelectedItem().toString() + "',"
                        + "'" + cmbIntelektual.getSelectedItem().toString() + "','" + cmbMental.getSelectedItem().toString() + "',"
                        + "'" + der1 + "','" + der2 + "','" + der3 + "','" + der4 + "','" + der5 + "','" + der6 + "','" + cmbKemampuan1.getSelectedItem().toString() + "',"
                        + "'" + cmbKemampuan2.getSelectedItem().toString() + "','" + TekstremAtas.getText() + "','" + cmbKananAtas.getSelectedItem().toString() + "',"
                        + "'" + cmbKiriAtas.getSelectedItem().toString() + "','" + TekstremBawah.getText() + "','" + cmbKananBawah.getSelectedItem().toString() + "',"
                        + "'" + cmbKiriBawah.getSelectedItem().toString() + "','" + cmbAlat.getSelectedItem().toString() + "','" + Talat.getText() + "',"
                        + "'" + cmbPenyebab.getSelectedItem().toString() + "','" + Ttahun.getText() + "','" + cmbPenyakit.getSelectedItem().toString() + "',"
                        + "'" + Tpenyakit.getText() + "','" + cmbPengobatan.getSelectedItem().toString() + "','" + Tpengobatan.getText() + "',"
                        + "'" + Tcatatan.getText() + "'", "Surat Keterangan Dokter (Disabilitas)");


                TCari.setText(TNoRW.getText());
                TabSurat.setSelectedIndex(1);
                emptTeks();
                tampil();
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
            Valid.pindah(evt, BtnSimpan, BtnGanti);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnGantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiActionPerformed
        if (TNoRW.getText().trim().equals("")) {
            Valid.textKosong(TNoRW, "Pasien");
        } else if (kddokter.equals("") || kddokter.equals("-") || kddokter.equals("--")) {
            Valid.textKosong(Tnmdokter, "Dokter Pemeriksa");
            BtnDokter.requestFocus();
        } else if (TnoDokumen.getText().equals("")) {
            Valid.textKosong(TnoDokumen, "Nomor Dokumen");
            BtnDokumen.requestFocus();
        } else if (sttsnomor.equals("Non Aktif")) {
            JOptionPane.showMessageDialog(null, "Nomor dokumen yang dipilih statusnya " + sttsnomor + ", silahkan ulangi lagi...!!");
            BtnDokumen.requestFocus();
        } else {
            if (tbSurat.getSelectedRow() > -1) {
                cekData();

            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan klik/pilih dulu salah satu datanya pada tabel..!!");
                TabSurat.setSelectedIndex(1);
                tbSurat.requestFocus();
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

    private void TPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPasienKeyPressed
        Valid.pindah(evt, TPasien, TTempLahr);
    }//GEN-LAST:event_TPasienKeyPressed

    private void TTempLahrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTempLahrKeyPressed
        Valid.pindah(evt, TPasien, TAlamat);
    }//GEN-LAST:event_TTempLahrKeyPressed

    private void TAlamatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TAlamatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ChkFisik.requestFocus();
        }
    }//GEN-LAST:event_TAlamatKeyPressed

    private void BtnDokumenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokumenActionPerformed
        akses.setform("DlgSuratKeteranganDisabilitas");
        dokumen.isCek();
        dokumen.ChkInput.setSelected(false);
        dokumen.isForm();
        dokumen.setSize(650, internalFrame1.getHeight() - 40);
        dokumen.setLocationRelativeTo(internalFrame1);
        dokumen.setAlwaysOnTop(false);
        dokumen.setVisible(true);
    }//GEN-LAST:event_BtnDokumenActionPerformed

    private void ChkFisikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkFisikActionPerformed
        cmbFisik.setSelectedIndex(0);
        cmbTanganKaki.setSelectedIndex(0);
        
        if (ChkFisik.isSelected() == true) {
            cmbFisik.setEnabled(true);
            cmbTanganKaki.setEnabled(false);
            cmbFisik.requestFocus();
        } else {
            cmbFisik.setEnabled(false);
            cmbTanganKaki.setEnabled(false);
        }
    }//GEN-LAST:event_ChkFisikActionPerformed

    private void ChkSensorikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkSensorikActionPerformed
        cmbSensorik.setSelectedIndex(0);
        
        if (ChkSensorik.isSelected() == true) {
            cmbSensorik.setEnabled(true);
            cmbSensorik.requestFocus();
        } else {
            cmbSensorik.setEnabled(false);
        }
    }//GEN-LAST:event_ChkSensorikActionPerformed

    private void ChkIntelektualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkIntelektualActionPerformed
        cmbIntelektual.setSelectedIndex(0);
        
        if (ChkIntelektual.isSelected() == true) {
            cmbIntelektual.setEnabled(true);
            cmbIntelektual.requestFocus();
        } else {
            cmbIntelektual.setEnabled(false);
        }
    }//GEN-LAST:event_ChkIntelektualActionPerformed

    private void ChkMentalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkMentalActionPerformed
        cmbMental.setSelectedIndex(0);
        
        if (ChkMental.isSelected() == true) {
            cmbMental.setEnabled(true);
            cmbMental.requestFocus();
        } else {
            cmbMental.setEnabled(false);
        }
    }//GEN-LAST:event_ChkMentalActionPerformed

    private void cmbFisikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFisikActionPerformed
        cmbTanganKaki.setSelectedIndex(0);
        if (cmbFisik.getSelectedIndex() == 1 || cmbFisik.getSelectedIndex() == 2) {
            cmbTanganKaki.setEnabled(true);
            cmbTanganKaki.requestFocus();
        } else {
            cmbTanganKaki.setEnabled(false);
        }
    }//GEN-LAST:event_cmbFisikActionPerformed

    private void cmbTanganKakiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTanganKakiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTanganKakiActionPerformed

    private void TekstremAtasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TekstremAtasKeyPressed
        Valid.pindah(evt, cmbKemampuan2, cmbKananAtas);
    }//GEN-LAST:event_TekstremAtasKeyPressed

    private void TekstremBawahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TekstremBawahKeyPressed
        Valid.pindah(evt, cmbKiriAtas, cmbKananBawah);
    }//GEN-LAST:event_TekstremBawahKeyPressed

    private void cmbAlatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAlatActionPerformed
        Talat.setText("");
        if (cmbAlat.getSelectedIndex() == 1) {
            Talat.setEnabled(true);
            Talat.requestFocus();
        } else {
            Talat.setEnabled(false);
        }
    }//GEN-LAST:event_cmbAlatActionPerformed

    private void TalatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TalatKeyPressed
        Valid.pindah(evt, cmbAlat, cmbPenyebab);
    }//GEN-LAST:event_TalatKeyPressed

    private void cmbPenyebabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPenyebabActionPerformed
        Ttahun.setText("");
        if (cmbPenyebab.getSelectedIndex() == 7) {
            Ttahun.setEnabled(true);
            Ttahun.requestFocus();
        } else {
            Ttahun.setEnabled(false);
        }
    }//GEN-LAST:event_cmbPenyebabActionPerformed

    private void TtahunKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtahunKeyPressed
        Valid.pindah(evt, cmbPenyebab, cmbPenyakit);
    }//GEN-LAST:event_TtahunKeyPressed

    private void cmbPenyakitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPenyakitActionPerformed
        Tpenyakit.setText("");
        if (cmbPenyakit.getSelectedIndex() == 1) {
            Tpenyakit.setEnabled(true);
            Tpenyakit.requestFocus();
        } else {
            Tpenyakit.setEnabled(false);
        }
    }//GEN-LAST:event_cmbPenyakitActionPerformed

    private void TpenyakitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpenyakitKeyPressed
        Valid.pindah(evt, cmbPenyakit, cmbPengobatan);
    }//GEN-LAST:event_TpenyakitKeyPressed

    private void cmbPengobatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPengobatanActionPerformed
        Tpengobatan.setText("");
        if (cmbPengobatan.getSelectedIndex() == 1) {
            Tpengobatan.setEnabled(true);
            Tpengobatan.requestFocus();
        } else {
            Tpengobatan.setEnabled(false);
        }
    }//GEN-LAST:event_cmbPengobatanActionPerformed

    private void TpengobatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpengobatanKeyPressed
        Valid.pindah(evt, cmbPengobatan, Tcatatan);
    }//GEN-LAST:event_TpengobatanKeyPressed

    private void TcatatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TcatatanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Tkeperluan.requestFocus();
        }
    }//GEN-LAST:event_TcatatanKeyPressed

    private void TkeperluanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkeperluanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            BtnDokter.requestFocus();
        }
    }//GEN-LAST:event_TkeperluanKeyPressed

    private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
        akses.setform("DlgSuratKeteranganDisabilitas");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDokterActionPerformed

    private void tbSuratMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSuratMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
            if ((evt.getClickCount() == 2) && (tbSurat.getSelectedColumn() == 0)) {
                TabSurat.setSelectedIndex(0);
            }
        }
    }//GEN-LAST:event_tbSuratMouseClicked

    private void tbSuratKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbSuratKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbSuratKeyPressed

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
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari, BtnAll);
        }
    }//GEN-LAST:event_BtnCariKeyPressed

    private void TabSuratMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabSuratMouseClicked
        if (TabSurat.getSelectedIndex() == 0) {
            ChkFisik.requestFocus();
        } else if (TabSurat.getSelectedIndex() == 1) {
            tampil();
        }
    }//GEN-LAST:event_TabSuratMouseClicked

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (TNoRW.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data pasiennya pada tabel...!!!!");
            tbSurat.requestFocus();
        } else {
            if (tbSurat.getSelectedRow() > -1) {
                x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    if (Sequel.queryu2tf("delete from surat_keterangan_dokter where no_surat='" + tbSurat.getValueAt(tbSurat.getSelectedRow(), 1).toString() + "' and no_rawat=?", 1, new String[]{
                        tbSurat.getValueAt(tbSurat.getSelectedRow(), 0).toString()
                    }) == true) {
                        Sequel.meghapus("surat_keterangan_dokter_disabilitas", "no_surat='" + tbSurat.getValueAt(tbSurat.getSelectedRow(), 1).toString() + "' and no_rawat=?",
                                tbSurat.getValueAt(tbSurat.getSelectedRow(), 0).toString());

                        TCari.setText("");
                        emptTeks();
                        tampil();
                    } else {
                        JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan klik/pilih dulu salah satu datanya pada tabel..!!");
                TabSurat.setSelectedIndex(1);
                tbSurat.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        }
    }//GEN-LAST:event_BtnHapusKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgSuratKeteranganDisabilitas dialog = new DlgSuratKeteranganDisabilitas(new javax.swing.JFrame(), true);
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
    private widget.Button BtnDokumen;
    private widget.Button BtnGanti;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnSimpan;
    public widget.CekBox ChkDerajat1;
    public widget.CekBox ChkDerajat2;
    public widget.CekBox ChkDerajat3;
    public widget.CekBox ChkDerajat4;
    public widget.CekBox ChkDerajat5;
    public widget.CekBox ChkDerajat6;
    public widget.CekBox ChkFisik;
    public widget.CekBox ChkIntelektual;
    public widget.CekBox ChkMental;
    public widget.CekBox ChkSensorik;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Label LCount;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll1;
    private widget.TextBox TAlamat;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRW;
    private widget.TextBox TNoSurat;
    private widget.TextBox TPasien;
    private widget.TextBox TTempLahr;
    private javax.swing.JTabbedPane TabSurat;
    private widget.TextBox Talat;
    private widget.TextArea Tcatatan;
    private widget.TextBox TekstremAtas;
    private widget.TextBox TekstremBawah;
    private widget.TextBox Tjk;
    private widget.TextArea Tkeperluan;
    private widget.TextBox Tnmdokter;
    private widget.TextBox TnoDokumen;
    private widget.TextBox Tpengobatan;
    private widget.TextBox Tpenyakit;
    private widget.TextBox Ttahun;
    private widget.TextBox TtglLahir;
    private widget.Tanggal Ttgl_surat;
    private widget.TextBox Tumur;
    private javax.swing.ButtonGroup buttonGroup1;
    private widget.ComboBox cmbAlat;
    private widget.ComboBox cmbFisik;
    private widget.ComboBox cmbIntelektual;
    private widget.ComboBox cmbKananAtas;
    private widget.ComboBox cmbKananBawah;
    private widget.ComboBox cmbKemampuan1;
    private widget.ComboBox cmbKemampuan2;
    private widget.ComboBox cmbKiriAtas;
    private widget.ComboBox cmbKiriBawah;
    private widget.ComboBox cmbMental;
    private widget.ComboBox cmbPengobatan;
    private widget.ComboBox cmbPenyakit;
    private widget.ComboBox cmbPenyebab;
    private widget.ComboBox cmbSensorik;
    private widget.ComboBox cmbTanganKaki;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame4;
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
    private widget.Label jLabel23;
    private widget.Label jLabel24;
    private widget.Label jLabel25;
    private widget.Label jLabel26;
    private widget.Label jLabel27;
    private widget.Label jLabel28;
    private widget.Label jLabel29;
    private widget.Label jLabel3;
    private widget.Label jLabel30;
    private widget.Label jLabel31;
    private widget.Label jLabel32;
    private widget.Label jLabel33;
    private widget.Label jLabel4;
    private widget.Label jLabel46;
    private widget.Label jLabel48;
    private widget.Label jLabel49;
    private widget.Label jLabel5;
    private widget.Label jLabel50;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollInput;
    private widget.ScrollPane scrollPane13;
    private widget.ScrollPane scrollPane14;
    private widget.Table tbSurat;
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
        TNoRW.setText("");
        TNoRM.setText("");
        TPasien.setText("");
        Ttgl_surat.setDate(new Date());
        TTempLahr.setText("");
        TtglLahir.setText("");
        Tumur.setText("");
        TnoDokumen.setText("");
        sttsnomor = "";
        Tjk.setText("");
        TAlamat.setText("");
        
        ChkFisik.setSelected(false);
        ChkSensorik.setSelected(false);
        ChkIntelektual.setSelected(false);
        ChkMental.setSelected(false);
        
        cmbFisik.setSelectedIndex(0);
        cmbTanganKaki.setSelectedIndex(0);
        cmbSensorik.setSelectedIndex(0);
        cmbIntelektual.setSelectedIndex(0);
        cmbMental.setSelectedIndex(0);
        
        cmbFisik.setEnabled(false);
        cmbTanganKaki.setEnabled(false);
        cmbSensorik.setEnabled(false);
        cmbIntelektual.setEnabled(false);
        cmbMental.setEnabled(false);
        
        ChkDerajat1.setSelected(false);
        ChkDerajat2.setSelected(false);
        ChkDerajat3.setSelected(false);
        ChkDerajat4.setSelected(false);
        ChkDerajat5.setSelected(false);
        ChkDerajat6.setSelected(false);
        buttonGroup1.clearSelection();
        
        cmbKemampuan1.setSelectedIndex(0);
        cmbKemampuan2.setSelectedIndex(0);
        TekstremAtas.setText("");
        cmbKananAtas.setSelectedIndex(0);
        cmbKiriAtas.setSelectedIndex(0);
        TekstremBawah.setText("");
        cmbKananBawah.setSelectedIndex(0);
        cmbKiriBawah.setSelectedIndex(0);
        
        cmbAlat.setSelectedIndex(0);
        Talat.setText("");
        cmbPenyebab.setSelectedIndex(0);
        Ttahun.setText("");
        cmbPenyakit.setSelectedIndex(0);
        Tpenyakit.setText("");
        cmbPengobatan.setSelectedIndex(0);
        Tpengobatan.setText("");
        Tcatatan.setText("");
        Tkeperluan.setText("MELENGKAPI PERSYARATAN ");
        kddokter = "-";
        Tnmdokter.setText("-");
    }

    private void getData() {
        if (tbSurat.getSelectedRow() != -1) {
//            kdkomite.setText(tbKomite.getValueAt(tbKomite.getSelectedRow(), 0).toString());
//            nmkomite.setText(tbKomite.getValueAt(tbKomite.getSelectedRow(), 1).toString());        
        }
    }
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.getsurat_keterangan_kir_mcu());
        BtnGanti.setEnabled(akses.getsurat_keterangan_kir_mcu());
        BtnHapus.setEnabled(akses.getsurat_keterangan_kir_mcu());
    }
    
    public void autoNomorSurat() {
        thn = "";
        thn = Sequel.cariIsi("select date_format(tgl_surat,'%Y') from surat_keterangan_dokter order by tgl_surat desc limit 1");
        Valid.autoNomer6("select ifnull(MAX(CONVERT(LEFT(no_surat,4),signed)),0) from surat_keterangan_dokter where "
                + "year(tgl_surat) ='" + thn + "'", " / RAZA", 4, TNoSurat);
    }
    
    private void cekData() {
        if (ChkFisik.isSelected() == true) {
            fisik = "ya";
        } else {
            fisik = "tidak";
        }
        
        if (ChkSensorik.isSelected() == true) {
            sensorik = "ya";
        } else {
            sensorik = "tidak";
        }
        
        if (ChkIntelektual.isSelected() == true) {
            intelek = "ya";
        } else {
            intelek = "tidak";
        }
        
        if (ChkMental.isSelected() == true) {
            mental = "ya";
        } else {
            mental = "tidak";
        }
        
        //-----------------------------------------
        if (ChkDerajat1.isSelected() == true) {
            der1 = "ya";
        } else {
            der1 = "tidak";
        }
        
        if (ChkDerajat2.isSelected() == true) {
            der2 = "ya";
        } else {
            der2 = "tidak";
        }
        
        if (ChkDerajat3.isSelected() == true) {
            der3 = "ya";
        } else {
            der3 = "tidak";
        }
        
        if (ChkDerajat4.isSelected() == true) {
            der4 = "ya";
        } else {
            der4 = "tidak";
        }
        
        if (ChkDerajat5.isSelected() == true) {
            der5 = "ya";
        } else {
            der5 = "tidak";
        }
        
        if (ChkDerajat6.isSelected() == true) {
            der6 = "ya";
        } else {
            der6 = "tidak";
        }
    }
}
